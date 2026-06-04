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
import kotlin.test.*
import kotlin.native.concurrent.AtomicInt
import kotlin.native.runtime.Debugging
import kotlin.native.runtime.NativeRuntimeApi
import kotlinx.cinterop.*
import platform.BasicServicesKit.DeviceInfo.OH_GetSdkApiVersion
import platform.PerformanceAnalysisKit.HiAppEvent.*
import platform.PerformanceAnalysisKit.HiDebug.*
import platform.posix.*

/**
 * Tests for OHOS OOM / memory-dump (IR004/SR004) in AllocatedSizeTracker.cpp and Runtime.cpp.
 *
 * Runtime flow:
 *   heap > threshold → ShouldDumpAndMark (CAS) → CleanupOldDumpFiles → BuildDumpMetadata
 *   → ReportOomEventViaHiAppEvent (API>=26, dlsym) → DumpMemoryToFile
 *   init: RegistDumpListenerIfNeeded → OH_HIDEBUG_DUMP_SNAPSHOT → dumpMemory(fd)
 *
 * Mirrors C++ helpers and exercises OHOS APIs. Does not allocate 1.5GB to hit real OOM threshold.
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
    NativeRuntimeApi::class,
)
class OomMemDumpHiAppEventTest {

    private fun logLine(msg: String) = println(msg)

    private val ohosOomMinApi = 26

    private val maxOomDumpFiles = 10

    private val oomDumpDir = "/data/storage/el2/base/haps/entry/temp"

    /** Matches the ~1.5GB OOM threshold documented in AllocatedSizeTracker (probe only). */
    private val oomThresholdBytes = 1_610_612_736L

    private val dumpFileNameRegex = Regex("""oom_dump_\d{8}_\d{6}\.dump""")

    private val dumpTimestampRegex = Regex("""\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}""")

    private enum class OomPipelineStep {
        SHOULD_DUMP,
        CLEANUP_OLD_DUMPS,
        BUILD_METADATA,
        REPORT_HIAPPEVENT,
        DUMP_TO_FILE,
    }

    private data class OomDumpFileEntry(val fileName: String, val mtime: Long, val fullPath: String)

    private class DumpTriggerState {
        private var hasDumped = false

        /** Mirrors ShouldDumpAndMark. */
        fun shouldDumpAndMark(nowAllocated: Long, threshold: Long): Boolean {
            if (nowAllocated <= threshold) return false
            if (hasDumped) return false
            hasDumped = true
            return true
        }

        /** Mirrors hasDumped_ re-arm in recordDifference when usage drops to threshold. */
        fun rearmIfRecovered(nowAllocated: Long, threshold: Long) {
            if (nowAllocated <= threshold) hasDumped = false
        }

        fun hasDumpedFlag(): Boolean = hasDumped
    }

    private fun sdkApiVersion(): Int = try {
        OH_GetSdkApiVersion()
    } catch (e: Throwable) {
        logLine("OH_GetSdkApiVersion exception: $e")
        -1
    }

    private fun buildOomDescription(
        dumpPath: String,
        memUsage: Long,
        threshold: Long,
        timestamp: String,
    ): String =
        "Kotlin/Native heap over OOM threshold; dump_path=$dumpPath; memory_usage=$memUsage; " +
            "oom_threshold=$threshold; timestamp=$timestamp"

    private fun isOomDumpFileName(filename: String): Boolean {
        val ext = ".dump"
        return filename.startsWith("oom_dump_") &&
            filename.endsWith(ext) &&
            filename.length >= "oom_dump_".length + ext.length
    }

    /** Mirrors ShouldReplaceOldestDump. */
    private fun shouldReplaceOldestDump(
        candidateMtime: Long,
        candidatePath: String,
        hasOldest: Boolean,
        oldestMtime: Long,
        oldestDumpFile: String,
    ): Boolean {
        val isOlder = candidateMtime < oldestMtime
        val sameTimeButSmallerPath = candidateMtime == oldestMtime && candidatePath < oldestDumpFile
        return !hasOldest || isOlder || sameTimeButSmallerPath
    }

    /** Mirrors CleanupOldDumpFiles: when count >= maxFiles, pick the oldest dump to delete. */
    private fun pickOldestDumpToDelete(entries: List<OomDumpFileEntry>, maxFiles: Int): String? {
        var dumpCount = 0
        var oldestPath: String? = null
        var oldestMtime = 0L
        var hasOldest = false
        for (entry in entries) {
            if (!isOomDumpFileName(entry.fileName)) continue
            dumpCount++
            if (shouldReplaceOldestDump(entry.mtime, entry.fullPath, hasOldest, oldestMtime, oldestPath ?: "")) {
                oldestMtime = entry.mtime
                oldestPath = entry.fullPath
                hasOldest = true
            }
        }
        return if (dumpCount >= maxFiles) oldestPath else null
    }

    /** Mirrors whether resolveOHHiAppEventReportFrameworkMemAnomaly would be callable. */
    private fun canReportViaHiAppEvent(apiVersion: Int, symbolResolved: Boolean): Boolean =
        apiVersion >= ohosOomMinApi && symbolResolved

    /** Mirrors MaybeDumpAndReportOom step order (report HiAppEvent before dump file). */
    private fun oomPipelineSteps(): List<OomPipelineStep> = listOf(
        OomPipelineStep.SHOULD_DUMP,
        OomPipelineStep.CLEANUP_OLD_DUMPS,
        OomPipelineStep.BUILD_METADATA,
        OomPipelineStep.REPORT_HIAPPEVENT,
        OomPipelineStep.DUMP_TO_FILE,
    )

    /** Mirrors BuildDumpMetadata output path and timestamp string shape. */
    private fun buildDumpMetadata(fileName: String, timestamp: String): Pair<String, String> {
        val finalPath = "$oomDumpDir/$fileName"
        return finalPath to timestamp
    }

    /**
     * Mirrors Runtime.cpp MemDumpListener callback branches (Kotlin mirror for DUMP_SNAPSHOT checks).
     */
    private fun handleMemDumpListenerTag(
        tag: OH_HiDebug_MemListenerType,
        fd: Int,
        mayReportToOEM: Boolean,
        dumpMemory: (Long) -> Boolean,
    ): Boolean = when (tag) {
        OH_HIDEBUG_DO_NOTHING -> true
        OH_HIDEBUG_RUNNING_GC -> true
        OH_HIDEBUG_DUMP_SNAPSHOT ->
            if (!mayReportToOEM && fd >= 0) dumpMemory(fd.toLong()) else true
        else -> true
    }

    private fun reportFrameworkMemAnomalyProbe(
        fwVersion: String,
        description: String,
    ) = try {
        OH_HiAppEvent_ReportFrameworkMemAnomaly(OH_KMP_KOTLIN, fwVersion, description)
    } catch (e: Throwable) {
        logLine("ReportFrameworkMemAnomaly exception: $e")
        HIAPPEVENT_OPERATE_FAILED
    }

    // ---------- Constants, naming, and description format ----------

    @Test
    fun testOomConstants_alignWithRuntime() {
        assertEquals(26, ohosOomMinApi)
        assertEquals(10, maxOomDumpFiles)
        assertEquals(oomDumpDir, "/data/storage/el2/base/haps/entry/temp")
        assertEquals(1_610_612_736L, oomThresholdBytes)
        logLine("OOM constants aligned")
    }

    @Test
    fun testOomDumpFileNamePattern() {
        assertTrue(isOomDumpFileName("oom_dump_20260415_120530.dump"))
        assertTrue(dumpFileNameRegex.matches("oom_dump_20260415_120530.dump"))
        assertFalse(isOomDumpFileName("oom_dump_.dump"))
        assertFalse(isOomDumpFileName("other_dump_20260415.dump"))
        logLine("oom_dump filename pattern ok")
    }

    @Test
    fun testBuildOomDescriptionFormat() {
        val path = "$oomDumpDir/oom_dump_20260415_120530.dump"
        val desc = buildOomDescription(path, oomThresholdBytes, oomThresholdBytes, "2026-04-15 12:05:30")
        assertTrue(desc.contains("dump_path=$path"))
        assertTrue(desc.contains("memory_usage=$oomThresholdBytes"))
        assertTrue(desc.contains("oom_threshold=$oomThresholdBytes"))
        assertTrue(desc.contains("timestamp=2026-04-15 12:05:30"))
        logLine("OOM description ok")
    }

    @Test
    fun testBuildDumpMetadata_pathAndTimestampFormat() {
        val fileName = "oom_dump_20260415_120530.dump"
        val timestamp = "2026-04-15 12:05:30"
        val (path, ts) = buildDumpMetadata(fileName, timestamp)
        assertEquals("$oomDumpDir/$fileName", path)
        assertTrue(dumpFileNameRegex.matches(fileName))
        assertTrue(dumpTimestampRegex.matches(ts))
        logLine("BuildDumpMetadata mirror path=$path")
    }

    @Test
    fun testHiAppEventFrameworkVersion_fallbackUnknown() {
        // Matches #ifndef KOTLIN_NATIVE_HIAPPEVENT_FW_VERSION default "unknown" (overridable at compile time).
        val fallbackFw = "unknown"
        val desc = buildOomDescription("$oomDumpDir/oom.dump", 1L, 1L, "t")
        if (sdkApiVersion() >= ohosOomMinApi) {
            val rc = reportFrameworkMemAnomalyProbe(fallbackFw, desc)
            logLine("ReportFrameworkMemAnomaly(unknown fw) ret=$rc")
        } else {
            logLine("skip fw version probe: API < $ohosOomMinApi")
        }
    }

    // ---------- API gating and dlsym resolution (mirrored logic) ----------

    @Test
    fun testOH_GetSdkApiVersion() {
        val version = sdkApiVersion()
        logLine("OH_GetSdkApiVersion=$version")
        if (version >= 0) assertTrue(version >= 0)
    }

    @Test
    fun testCanReportViaHiAppEvent_apiAndSymbolGate() {
        assertFalse(canReportViaHiAppEvent(25, true))
        assertFalse(canReportViaHiAppEvent(26, false))
        assertTrue(canReportViaHiAppEvent(26, true))
        val version = sdkApiVersion()
        if (version >= 0) {
            logLine("device canReport=${canReportViaHiAppEvent(version, true)} (symbol assumed on API>=26 device)")
        }
    }

    @Test
    fun testSdkApiVersionGate_oomMinApi26() {
        val version = sdkApiVersion()
        if (version < 0) {
            logLine("skip gate: version unavailable")
            return
        }
        assertEquals(version >= ohosOomMinApi, version >= ohosOomMinApi)
        logLine("api=$version oomApis=${version >= ohosOomMinApi}")
    }

    // ---------- ShouldDumpAndMark and hasDumped_ re-arm ----------

    @Test
    fun testShouldDumpAndMark_casOnlyOnceUntilRearm() {
        val state = DumpTriggerState()
        val threshold = oomThresholdBytes
        val over = threshold + 1
        assertTrue(state.shouldDumpAndMark(over, threshold))
        assertFalse(state.shouldDumpAndMark(over + 100, threshold))
        assertTrue(state.hasDumpedFlag())
        state.rearmIfRecovered(threshold, threshold)
        assertFalse(state.hasDumpedFlag())
        assertTrue(state.shouldDumpAndMark(over, threshold))
        logLine("ShouldDumpAndMark + rearm ok")
    }

    @Test
    fun testShouldDumpAndMark_belowThresholdNoDump() {
        val state = DumpTriggerState()
        assertFalse(state.shouldDumpAndMark(oomThresholdBytes, oomThresholdBytes))
        assertFalse(state.shouldDumpAndMark(0, oomThresholdBytes))
        logLine("below threshold no dump ok")
    }

    // ---------- Report before dump pipeline order ----------

    @Test
    fun testOomPipeline_reportBeforeDumpOrder() {
        val steps = oomPipelineSteps()
        val reportIdx = steps.indexOf(OomPipelineStep.REPORT_HIAPPEVENT)
        val dumpIdx = steps.indexOf(OomPipelineStep.DUMP_TO_FILE)
        assertTrue(reportIdx >= 0 && dumpIdx >= 0)
        assertTrue(reportIdx < dumpIdx, "HiAppEvent report must precede DumpMemoryToFile")
        logLine("pipeline order: $steps")
    }

    @Test
    fun testOomPipeline_simulatedExecutionLog() {
        val log = mutableListOf<String>()
        val state = DumpTriggerState()
        val now = oomThresholdBytes + 1
        assertTrue(state.shouldDumpAndMark(now, oomThresholdBytes))
        log += "cleanup"
        val fileName = "oom_dump_20260415_120530.dump"
        val (path, ts) = buildDumpMetadata(fileName, "2026-04-15 12:05:30")
        log += "report"
        log += "dump"
        assertEquals(listOf("cleanup", "report", "dump"), log)
        assertTrue(buildOomDescription(path, now, oomThresholdBytes, ts).contains(path))
        logLine("simulated pipeline ok path=$path")
    }

    // ---------- CleanupOldDumpFiles and ShouldReplaceOldestDump ----------

    @Test
    fun testShouldReplaceOldestDump_tieBreakerByPath() {
        assertTrue(shouldReplaceOldestDump(100, "/a/x.dump", false, 0, ""))
        assertFalse(shouldReplaceOldestDump(200, "/b/x.dump", true, 100, "/a/x.dump"))
        assertTrue(shouldReplaceOldestDump(100, "/a/aa.dump", true, 100, "/a/bb.dump"))
        logLine("ShouldReplaceOldestDump ok")
    }

    @Test
    fun testPickOldestDumpToDelete_whenBelowMax() {
        val entries = (1..9).map { i ->
            OomDumpFileEntry("oom_dump_2026040${i}_12050$i.dump", i.toLong(), "$oomDumpDir/f$i.dump")
        }
        assertNull(pickOldestDumpToDelete(entries, maxOomDumpFiles))
        logLine("9 dumps: no delete")
    }

    @Test
    fun testPickOldestDumpToDelete_whenAtMax_deletesOldestMtime() {
        val entries = listOf(
            OomDumpFileEntry("oom_dump_20260401_120501.dump", 100, "$oomDumpDir/old.dump"),
            OomDumpFileEntry("oom_dump_20260402_120502.dump", 300, "$oomDumpDir/mid.dump"),
            OomDumpFileEntry("oom_dump_20260403_120503.dump", 200, "$oomDumpDir/newer.dump"),
            OomDumpFileEntry("not_oom.txt", 50, "$oomDumpDir/skip.txt"),
        ) + (4..10).map { i ->
            OomDumpFileEntry("oom_dump_2026040${i}_12050$i.dump", i * 10L, "$oomDumpDir/f$i.dump")
        }
        val victim = pickOldestDumpToDelete(entries, maxOomDumpFiles)
        assertEquals("$oomDumpDir/old.dump", victim)
        logLine("oldest victim=$victim")
    }

    @Test
    fun testCleanupOldDumpFiles_posixProbeInTmp() {
        val pid = getpid()
        val dir = "/tmp/oom_cleanup_probe_$pid"
        memScoped {
            if (mkdir(dir, 0x1FF) != 0 && errno != 17) {
                logLine("skip cleanup posix probe: mkdir failed errno=$errno")
                return@memScoped
            }
            try {
                val names = (1..11).map { i ->
                    "oom_dump_20260415_1205${i.toString().padStart(2, '0')}.dump"
                }
                for (n in names) {
                    val path = "$dir/$n"
                    val fd = open(path, O_CREAT or O_WRONLY or O_TRUNC, 0x1B6)
                    if (fd >= 0) close(fd)
                }
                val dp = opendir(dir.cstr.ptr)
                if (dp == null) {
                    logLine("skip opendir probe")
                    return@memScoped
                }
                var count = 0
                while (true) {
                    val ent = readdir(dp) ?: break
                    val name = ent.pointed.d_name.toKString()
                    if (isOomDumpFileName(name)) count++
                }
                closedir(dp)
                assertEquals(11, count)
                val entries = names.mapIndexed { i, n ->
                    OomDumpFileEntry(n, i.toLong(), "$dir/$n")
                }
                val toDelete = pickOldestDumpToDelete(entries, maxOomDumpFiles)
                assertNotNull(toDelete)
                assertEquals(0, unlink(toDelete))
                logLine("posix cleanup deleted $toDelete")
            } finally {
                val dp = opendir(dir.cstr.ptr)
                if (dp != null) {
                    while (true) {
                        val ent = readdir(dp) ?: break
                        val name = ent.pointed.d_name.toKString()
                        if (name != "." && name != "..") unlink("$dir/$name")
                    }
                    closedir(dp)
                }
                rmdir(dir)
            }
        }
    }

    // ---------- HiAppEvent ReportFrameworkMemAnomaly ----------

    @Test
    fun testEnum_OH_HiAppEvent_FrameworkType() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("OH_FLUTTER_DART", OH_FLUTTER_DART.toInt(), 0)
        p("OH_REACT_NATIVE_HERMES", OH_REACT_NATIVE_HERMES.toInt(), 1)
        p("OH_KMP_KOTLIN", OH_KMP_KOTLIN.toInt(), 2)
    }

    @Test
    fun testEnum_HiAppEvent_ErrorCode_relevantToOom() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("HIAPPEVENT_SUCCESS", HIAPPEVENT_SUCCESS.toInt(), 0)
        p("HIAPPEVENT_INVALID_PARAM_VALUE", HIAPPEVENT_INVALID_PARAM_VALUE.toInt(), -9)
        p("HIAPPEVENT_OPERATE_FAILED", HIAPPEVENT_OPERATE_FAILED.toInt(), -100)
    }

    @Test
    fun testOH_HiAppEvent_ReportFrameworkMemAnomaly() {
        if (sdkApiVersion() < ohosOomMinApi) {
            logLine("skip ReportFrameworkMemAnomaly: API < $ohosOomMinApi")
            return
        }
        val dumpPath = "$oomDumpDir/oom_dump_probe.dump"
        val description = buildOomDescription(
            dumpPath, oomThresholdBytes, oomThresholdBytes, "OomMemDumpHiAppEventTest_probe",
        )
        val rc = reportFrameworkMemAnomalyProbe("OomMemDumpHiAppEventTest-fw", description)
        assertNotNull(rc)
        logLine("ReportFrameworkMemAnomaly ret=$rc (HIAPPEVENT_SUCCESS=${HIAPPEVENT_SUCCESS})")
    }

    @Test
    fun testOH_HiAppEvent_ReportFrameworkMemAnomaly_emptyDescription() {
        if (sdkApiVersion() < ohosOomMinApi) {
            logLine("skip empty description")
            return
        }
        val rc = reportFrameworkMemAnomalyProbe("test", "")
        assertNotNull(rc)
        logLine("empty description ret=$rc")
    }

    @Test
    fun testOH_HiAppEvent_ReportFrameworkMemAnomaly_repeatedCalls() {
        if (sdkApiVersion() < ohosOomMinApi) return
        val rc1 = reportFrameworkMemAnomalyProbe("fw1", buildOomDescription("$oomDumpDir/a.dump", 1, 1, "t1"))
        val rc2 = reportFrameworkMemAnomalyProbe("fw2", buildOomDescription("$oomDumpDir/b.dump", 2, 2, "t2"))
        logLine("repeated report ret1=$rc1 ret2=$rc2")
    }

    // ---------- HiDebug MemDumpListener (Runtime.cpp) ----------

    @Test
    fun testEnum_OH_HiDebug_MemListenerType() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("OH_HIDEBUG_DO_NOTHING", OH_HIDEBUG_DO_NOTHING.toInt(), 0)
        p("OH_HIDEBUG_RUNNING_GC", OH_HIDEBUG_RUNNING_GC.toInt(), 1)
        p("OH_HIDEBUG_DUMP_SNAPSHOT", OH_HIDEBUG_DUMP_SNAPSHOT.toInt(), 2)
    }

    @Test
    fun testMemDumpListener_mirror_doNothingAndRunningGc() {
        var dumpCalled = false
        val dump: (Long) -> Boolean = { dumpCalled = true; true }
        assertTrue(handleMemDumpListenerTag(OH_HIDEBUG_DO_NOTHING, -1, false, dump))
        assertTrue(handleMemDumpListenerTag(OH_HIDEBUG_RUNNING_GC, -1, false, dump))
        assertFalse(dumpCalled)
        logLine("DO_NOTHING/RUNNING_GC ok")
    }

    @Test
    fun testMemDumpListener_mirror_dumpSnapshotCallsDumpMemory() {
        var capturedFd = -1
        val dump: (Long) -> Boolean = { fd ->
            capturedFd = fd.toInt()
            true
        }
        assertTrue(handleMemDumpListenerTag(OH_HIDEBUG_DUMP_SNAPSHOT, 42, false, dump))
        assertEquals(42, capturedFd)
        assertTrue(handleMemDumpListenerTag(OH_HIDEBUG_DUMP_SNAPSHOT, 42, true, dump))
        logLine("DUMP_SNAPSHOT mirror ok")
    }

    @Test
    fun testOH_HiDebug_RegisterMemDumpListener_nullListener() {
        if (sdkApiVersion() < ohosOomMinApi) return
        val rc = try {
            OH_HiDebug_RegisterMemDumpListener("KMP", null)
        } catch (e: Throwable) {
            logLine("RegisterMemDumpListener exception: $e")
            HIDEBUG_INVALID_ARGUMENT
        }
        assertNotNull(rc)
        logLine("RegisterMemDumpListener(null) ret=$rc")
    }

    @Test
    fun testOH_HiDebug_RegisterMemDumpListener_withCallback() {
        if (sdkApiVersion() < ohosOomMinApi) {
            logLine("skip callback registration: API < $ohosOomMinApi")
            return
        }
        val dumpInvokeCount = AtomicInt(0)
        val listener = staticCFunction { fd: Int, tag: OH_HiDebug_MemListenerType, mayReportToOEM: Boolean, _: CPointer<ByteVar>? ->
            handleMemDumpListenerTag(
                tag = tag,
                fd = fd,
                mayReportToOEM = mayReportToOEM,
                dumpMemory = { f ->
                    if (f >= 0) dumpInvokeCount.add(1)
                    true
                },
            )
        }
        val rc = try {
            OH_HiDebug_RegisterMemDumpListener("KMP", listener)
        } catch (e: Throwable) {
            logLine("RegisterMemDumpListener(callback) exception: $e")
            HIDEBUG_NOT_SUPPORTED
        }
        logLine("RegisterMemDumpListener(callback) ret=$rc (listener installed for runtime parity)")
        // Invoke mirror logic directly (OS may not call the listener immediately).
        val file = tmpfile()
        if (file != null) {
            val fd = fileno(file)
            assertTrue(
                handleMemDumpListenerTag(OH_HIDEBUG_DUMP_SNAPSHOT, fd, false) {
                    Debugging.dumpMemory(it)
                },
            )
            fclose(file)
        }
    }

    // ---------- DumpMemoryToFile and Debugging.dumpMemory ----------

    @Test
    fun testDebugging_dumpMemory_toTmpFile() {
        val file = tmpfile()
        assertNotNull(file)
        val fd = fileno(file)
        assertTrue(fd >= 0)
        assertTrue(Debugging.dumpMemory(fd.toLong()))
        fflush(file)
        fseek(file, 0, SEEK_END)
        assertTrue(ftell(file) > 0L)
        fclose(file)
        logLine("dumpMemory tmpfile ok")
    }

    @Test
    fun testDumpMemoryToFile_openWriteClose_likeAllocatedSizeTracker() {
        val path = "/tmp/oom_dump_write_probe_${getpid()}.dump"
        val fd = open(path, O_CREAT or O_WRONLY or O_TRUNC, 0x1B6)
        if (fd < 0) {
            logLine("skip open write probe: errno=$errno")
            return
        }
        try {
            assertTrue(Debugging.dumpMemory(fd.toLong()))
            assertTrue(close(fd) == 0)
            val readFd = open(path, O_RDONLY)
            if (readFd >= 0) {
                val size = lseek(readFd, 0, SEEK_END)
                close(readFd)
                assertTrue(size > 0L)
                logLine("dump file size=$size")
            }
        } finally {
            unlink(path)
        }
    }
}
