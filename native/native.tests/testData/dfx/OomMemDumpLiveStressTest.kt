/*
 * Copyright (C) 2026 Eazytec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// TARGET_BACKEND: NATIVE
// LIVE_STRESS: requires DFX_OOM_STRESS=1; retains ~1.5GB heap; device needs >=2GB free RAM (else kernel OOM killer).
// Do not set gcType=NOOP — OOM path uses custom allocator (AllocatedSizeTracker.cpp).
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.native.runtime.GC
import kotlin.test.*
import kotlinx.cinterop.*
import platform.posix.*
import platform.zlib.*

/**
 * Live OOM stress for IR004/SR004: cross the ~1.5GB (1536 MiB) Kotlin heap threshold and verify the
 * auto-generated kdump under [oomDumpDir].
 *
 * When runtime emits gzip (strip + gzip), asserts size &lt;100MB. With plaintext [MemoryDump.cpp] (fdopen),
 * validates the kdump header only — SR004 size bound is reported in logs, not enforced.
 *
 * Mirror tests live in [OomMemDumpHiAppEventTest]; this file is **opt-in** only:
 *   ./scripts/test-dfx.sh -c OomMemDumpLiveStressTest
 *
 * Enable on device (hdc black-box runs do not inherit host env):
 *   hdc shell touch /data/local/tmp/dfx_oom_stress_enable
 * Or set `DFX_OOM_STRESS=1` in the test process environment when the runner supports it.
 *
 * Prepare writable dump dir (AllocatedSizeTracker hardcoded path):
 *   hdc shell mkdir -p /data/storage/el2/base/haps/entry/temp \&\& chmod 777 /data/storage/el2/base/haps/entry/temp
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
    kotlin.native.runtime.NativeRuntimeApi::class,
)
class OomMemDumpLiveStressTest {

    private fun logLine(msg: String) = println(msg)

    private val oomDumpDir = "/data/storage/el2/base/haps/entry/temp"
    /** Same hardcoded path as AllocatedSizeTracker; must exist and be writable on device (see class KDoc). */
    private val stressEnableMarkerFile = "/data/local/tmp/dfx_oom_stress_enable"
    private val kdumpHeaderPrefix = "Kotlin/Native dump 1.0.9"
    private val oomThresholdBytes = 1_610_612_736L // 1536 * 1024 * 1024
    private val maxGzipDumpFileBytes = 100L * 1024 * 1024
    private val chunkBytes = 1024 * 1024
    /**
     * Upper bound on 1 MiB [ByteArray] chunks retained in the allocation loop — not a fixed target.
     * [AllocatedSizeTracker] fires when tracked heap bytes exceed 1536 MiB; counting MiB-sized chunks
     * here does not match that accounting byte-for-byte, so 1600 (=1536 + 64 MiB slack) avoids stopping
     * the loop before the tracker triggers. The loop breaks as soon as a new oom_dump_*.dump is seen,
     * which is usually well before 1600 chunks.
     */
    private val maxChunks = 1600
    /** How often to scan dump dir while retaining heap (MiB per poll); one new dump expected per run. */
    private val pollEveryChunks = 64
    /** Poll interval while waiting for AllocatedSizeTracker to finish dump write and close(fd). */
    private val dumpStablePollIntervalMs = 300
    /** Consecutive unchanged stat sizes before a confirm delay (guards brief IO stalls mid-write). */
    private val dumpStablePollsRequired = 12
    /** After stable polls, wait and re-stat once more before treating the dump as complete. */
    private val dumpStableConfirmDelayMs = 1_500L
    private val dumpCompleteTimeoutMs = 300_000L
    /** Reject header-only or truncated files that happened to stop growing briefly. */
    private val minCompleteDumpBytes = 512L * 1024

    private data class DumpFileInfo(val path: String, val sizeBytes: Long, val mtime: Long)

    private fun isOomStressEnabled(): Boolean {
        if (getenv("DFX_OOM_STRESS")?.toKString() == "1") return true
        return access(stressEnableMarkerFile, F_OK) == 0
    }

    private fun isOomDumpFileName(filename: String): Boolean {
        val ext = ".dump"
        return filename.startsWith("oom_dump_") &&
            filename.endsWith(ext) &&
            filename.length >= "oom_dump_".length + ext.length
    }

    private fun fileStat(path: String): Pair<Long, Long>? = memScoped {
        val st = alloc<stat>()
        if (stat(path, st.ptr) != 0) return null
        // Linux/OHOS (musl/bionic): st_mtim; macOS/BSD uses st_mtimespec — not available on ohos_arm64.
        st.st_size to st.st_mtim.tv_sec
    }

    private fun listOomDumpFiles(dir: String): List<DumpFileInfo> {
        val dp = opendir(dir) ?: return emptyList()
        val files = mutableListOf<DumpFileInfo>()
        try {
            while (true) {
                val ent = readdir(dp) ?: break
                val name = ent.pointed.d_name.toKString()
                if (name == "." || name == "..") continue
                if (!isOomDumpFileName(name)) continue
                val path = "$dir/$name"
                val (size, mtime) = fileStat(path) ?: continue
                files.add(DumpFileInfo(path, size, mtime))
            }
        } finally {
            closedir(dp)
        }
        return files
    }

    private fun newestDumpNotIn(before: Set<String>, dir: String): DumpFileInfo? =
        listOomDumpFiles(dir)
            .filter { it.path !in before }
            .maxByOrNull { it.mtime }

    /**
     * AllocatedSizeTracker opens the file, streams via dumpMemory, then close(fd). Detection of a new
     * path does not mean the write finished — early bytes can look valid while the file is still growing.
     */
    private fun waitForDumpWriteComplete(path: String): DumpFileInfo {
        var lastSize = -1L
        var stablePolls = 0
        var zeroSizePolls = 0
        val maxPolls = (dumpCompleteTimeoutMs / dumpStablePollIntervalMs).toInt()
        repeat(maxPolls) { pollIndex ->
            val stat = fileStat(path) ?: fail("lost dump file while waiting for write complete: $path errno=$errno")
            val (size, mtime) = stat
            when {
                size == 0L -> {
                    zeroSizePolls++
                    stablePolls = 0
                    lastSize = 0L
                    if (zeroSizePolls == 1 || zeroSizePolls % 20 == 0) {
                        logLine("OOM dump waiting for first write: $path size=0 polls=$zeroSizePolls")
                    }
                }
                size == lastSize -> {
                    zeroSizePolls = 0
                    stablePolls++
                    if (stablePolls >= dumpStablePollsRequired) {
                        logLine(
                            "OOM dump size stable at $size bytes (stablePolls=$stablePolls); " +
                                "confirming after ${dumpStableConfirmDelayMs}ms",
                        )
                        usleep((dumpStableConfirmDelayMs * 1000).toUInt())
                        val confirm = fileStat(path)
                            ?: fail("lost dump file during stable confirm: $path errno=$errno")
                        val (confirmSize, confirmMtime) = confirm
                        if (confirmSize == size) {
                            logLine("OOM dump write complete: $path size=$confirmSize")
                            return DumpFileInfo(path, confirmSize, confirmMtime)
                        }
                        logLine("OOM dump size resumed after stable window: $size -> $confirmSize")
                        stablePolls = 1
                        lastSize = confirmSize
                    }
                }
                else -> {
                    zeroSizePolls = 0
                    stablePolls = 1
                    lastSize = size
                    if (pollIndex == 0 || pollIndex % 10 == 0) {
                        logLine("OOM dump growing: $path size=$size")
                    }
                }
            }
            usleep((dumpStablePollIntervalMs * 1000).toUInt())
        }
        fail(
            "timed out after ${dumpCompleteTimeoutMs}ms waiting for stable dump size: $path lastSize=$lastSize " +
                "zeroSizePolls=$zeroSizePolls",
        )
    }

    /** When opt-in is enabled, dump dir must be readable/writable; fail instead of silent skip. */
    private fun assertDumpDirReady(dir: String) {
        val dp = opendir(dir)
        if (dp == null) {
            fail(
                "OOM stress enabled but cannot opendir $dir errno=$errno; " +
                    "mkdir/chmod on device (see class KDoc) or deploy under HAP sandbox",
            )
        }
        closedir(dp)

        val probe = "$dir/.oom_stress_write_probe_${getpid()}"
        val fd = open(probe, O_CREAT or O_WRONLY or O_TRUNC, 0x1B6)
        if (fd < 0) {
            fail(
                "OOM stress enabled but cannot write under $dir errno=$errno; " +
                    "runtime dumpDir must be writable for AllocatedSizeTracker",
            )
        }
        close(fd)
        unlink(probe)
    }

    private fun readFilePrefix(path: String, byteCount: Int): ByteArray {
        val fd = open(path, O_RDONLY)
        assertTrue(fd >= 0, "cannot open dump file: $path errno=$errno")
        try {
            return memScoped {
                val buf = allocArray<ByteVar>(byteCount)
                val nread = read(fd, buf, byteCount.toULong())
                assertTrue(nread > 0u, "expected prefix bytes at $path errno=$errno")
                ByteArray(nread.toInt()) { i -> buf[i] }
            }
        } finally {
            close(fd)
        }
    }

    private fun readDumpFileBytes(path: String, maxBytes: Long): ByteArray {
        val (size, _) = fileStat(path) ?: fail("cannot stat dump file: $path errno=$errno")
        assertTrue(size > 0L, "dump file must be non-empty: $path")
        assertTrue(
            size <= maxBytes,
            "dump file $size bytes exceeds read cap $maxBytes at $path",
        )
        assertTrue(
            size <= Int.MAX_VALUE.toLong(),
            "dump file size $size exceeds Int.MAX_VALUE at $path",
        )
        val sizeInt = size.toInt()
        val fd = open(path, O_RDONLY)
        assertTrue(fd >= 0, "cannot open dump file: $path errno=$errno")
        try {
            return memScoped {
                val buf = allocArray<ByteVar>(sizeInt)
                val nread = read(fd, buf, sizeInt.toULong())
                assertEquals(sizeInt.toULong(), nread, "read dump file $path")
                ByteArray(sizeInt) { i -> buf[i] }
            }
        } finally {
            close(fd)
        }
    }

    private fun isGzipMagic(bytes: ByteArray): Boolean =
        bytes.size >= 2 &&
            (bytes[0].toInt() and 0xFF) == 0x1f &&
            (bytes[1].toInt() and 0xFF) == 0x8b

    private fun assertPlaintextKdumpHeader(prefix: ByteArray, path: String) {
        val headerBytes = kdumpHeaderPrefix.encodeToByteArray()
        assertTrue(
            prefix.size >= headerBytes.size,
            "dump too short for kdump header at $path (got ${prefix.size} prefix bytes)",
        )
        for (i in headerBytes.indices) {
            assertEquals(
                headerBytes[i],
                prefix[i],
                "kdump header byte mismatch at offset $i in $path",
            )
        }
    }

    /**
     * Stream-inflate until [Z_STREAM_END] to prove the gzip member is complete.
     * Decompressed bytes are discarded; only reset the output window when it is full.
     */
    private fun assertGzipMemberComplete(compressed: ByteArray, path: String) {
        val outChunkBytes = 1024 * 1024
        memScoped {
            compressed.usePinned { inPinned ->
                val stream = alloc<z_stream>().apply {
                    next_in = inPinned.addressOf(0).reinterpret()
                    avail_in = compressed.size.toUInt()
                }
                assertEquals(Z_OK, inflateInit2(stream.ptr, 15 + 16), "inflateInit2(gzip) for $path")
                val outBuf = allocArray<ByteVar>(outChunkBytes)
                stream.next_out = outBuf
                stream.avail_out = outChunkBytes.toUInt()
                var streamEnded = false
                while (!streamEnded) {
                    when (val inflateRc = inflate(stream.ptr, Z_NO_FLUSH)) {
                        Z_STREAM_END -> streamEnded = true
                        Z_OK -> {
                            when {
                                stream.avail_out == 0u -> {
                                    // Buffer full — discard decompressed chunk and continue.
                                    stream.next_out = outBuf
                                    stream.avail_out = outChunkBytes.toUInt()
                                }
                                stream.avail_in == 0u -> {
                                    fail("gzip inflate stalled before Z_STREAM_END at $path")
                                }
                                // else: partial output; zlib advanced next_out — keep draining.
                            }
                        }
                        else -> fail(
                            "gzip member must be complete (got rc=$inflateRc) at $path; " +
                                "file may still be growing or truncated",
                        )
                    }
                }
                inflateEnd(stream.ptr)
            }
        }
    }

    /**
     * Gzip kdump (SR004): complete member and &lt;100MB. Plaintext [MemoryDump.cpp]: header only (may be &gt;&gt;100MB).
     */
    private fun assertCompletedKdump(path: String, sizeBytes: Long) {
        val prefix = readFilePrefix(path, 64)
        if (isGzipMagic(prefix)) {
            assertTrue(
                sizeBytes < maxGzipDumpFileBytes,
                "gzip kdump should be <100MB (strip+gzip); got $sizeBytes bytes at $path",
            )
            val compressed = readDumpFileBytes(path, maxGzipDumpFileBytes)
            assertGzipMemberComplete(compressed, path)
            logLine("OOM dump validated as complete gzip kdump (${sizeBytes} bytes)")
        } else {
            assertPlaintextKdumpHeader(prefix, path)
            logLine(
                "OOM dump is plaintext kdump; size=$sizeBytes bytes — " +
                    "SR004 <100MB applies when runtime emits gzip+strip",
            )
        }
    }

    @Test
    fun testLiveOomTrigger_producesValidKdumpAfterThreshold() {
        if (!isOomStressEnabled()) {
            logLine(
                "skip live OOM stress (touch $stressEnableMarkerFile on device or DFX_OOM_STRESS=1 in process env; >=2GB free RAM)",
            )
            return
        }

        assertDumpDirReady(oomDumpDir)

        // Local retention only — cleared in finally so co-located tests in the same process can reclaim heap.
        val retainedChunks = mutableListOf<ByteArray>()
        try {
            val pathsBefore = listOomDumpFiles(oomDumpDir).map { it.path }.toSet()
            logLine("OOM stress start: threshold=$oomThresholdBytes chunk=${chunkBytes}B maxChunks=$maxChunks")

            var newDumpPath: String? = null
            // Retain up to maxChunks × 1 MiB; poll dump dir every pollEveryChunks and break on first new dump.
            for (i in 1..maxChunks) {
                retainedChunks.add(ByteArray(chunkBytes) { (it and 0xFF).toByte() })
                if (i % pollEveryChunks == 0 || i == maxChunks) {
                    val candidate = newestDumpNotIn(pathsBefore, oomDumpDir)
                    if (candidate != null) {
                        newDumpPath = candidate.path
                        logLine(
                            "OOM dump candidate after retaining ${i}MiB: ${candidate.path} " +
                                "size=${candidate.sizeBytes} (waiting for write complete)",
                        )
                        break
                    }
                    logLine("OOM stress progress retainedMiB=$i")
                }
            }

            val dumpPath = checkNotNull(newDumpPath) {
                "expected new oom_dump_*.dump under $oomDumpDir after retaining up to $maxChunks × 1 MiB " +
                    "(retained=${retainedChunks.size} chunks; loop cap=$maxChunks; baseline dumps=${pathsBefore.size})"
            }
            val dump = waitForDumpWriteComplete(dumpPath)
            assertTrue(
                dump.sizeBytes >= minCompleteDumpBytes,
                "dump looks too small for a completed heap kdump: ${dump.sizeBytes} bytes at ${dump.path}",
            )

            assertCompletedKdump(dump.path, dump.sizeBytes)

            logLine(
                "OOM stress ok retainedMiB=${retainedChunks.size} dumpBytes=${dump.sizeBytes} " +
                    "ratio=${dump.sizeBytes.toDouble() / (retainedChunks.size * chunkBytes)}",
            )
        } finally {
            val releasedMiB = retainedChunks.size
            retainedChunks.clear()
            GC.collect()
            logLine("OOM stress released retainedMiB=$releasedMiB")
        }
    }
}
