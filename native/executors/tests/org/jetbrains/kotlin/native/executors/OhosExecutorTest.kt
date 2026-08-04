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

package org.jetbrains.kotlin.native.executors

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Path
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Unit tests for [OhosExecutor].
 *
 * Device sync is cached per destination keyed with the host executable's [java.io.File.lastModified].
 * Most tests use a unique local executable name so destinations do not collide across cases.
 * Sync-skip / resync tests intentionally reuse one path and control mtime.
 */
class OhosExecutorTest {

    companion object {
        // Absolute path so unit tests do not require a real hdc on PATH.
        private const val FAKE_HDC = "/tmp/fake-hdc"

        private const val HDC_CONNECT_KEY_FAILURE =
            "[Fail]ExecuteCommand need connect-key? please confirm a device by help info"

        private const val DEVICE_EXE_PREFIX = "/data/local/tmp/native.tests/"
    }

    @Test
    fun `execute uses absolute hdc path for all host invocations`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0, runPayload = "ok\n"))
        val localExe = tempDir.resolve("abs_path.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        )

        assertTrue(recording.requests.isNotEmpty())
        recording.requests.forEach { req ->
            assertEquals(FAKE_HDC, req.executableAbsolutePath)
        }
    }

    @Test
    fun `execute prepends -t deviceId to all hdc commands when deviceId is set`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0, runPayload = "ok\n"))
        val localExe = tempDir.resolve("device_id.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC, deviceId = "3DK0124730000497").execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        )

        assertTrue(recording.requests.isNotEmpty())
        recording.requests.forEach { request ->
            assertEquals(FAKE_HDC, request.executableAbsolutePath)
            assertEquals("-t", request.args[0])
            assertEquals("3DK0124730000497", request.args[1])
        }
    }

    @Test
    fun `execute pushes binary via hdc and runs on device`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0))
        val localExe = tempDir.resolve("push_arg1.kexe").toFile().apply { writeText("bin") }
        val deviceExe = DEVICE_EXE_PREFIX + "push_arg1.kexe"

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(
                executableAbsolutePath = localExe.absolutePath,
                args = mutableListOf("arg1"),
            )
        )

        assertEquals(3, recording.requests.size)

        val prepare = recording.requests[0]
        assertEquals("shell", prepare.args[0])
        assertEquals(2, prepare.args.size)
        val prepareScript = prepare.args[1]
        assertTrue(prepareScript.contains("mkdir -p "))
        assertTrue(prepareScript.contains("rm -f "))
        assertTrue(prepareScript.contains(deviceExe))

        assertEquals(
            listOf("file", "send", localExe.absolutePath, deviceExe),
            recording.requests[1].args,
        )

        val run = recording.requests[2]
        assertEquals("shell", run.args[0])
        val runScript = run.args[1]
        assertTrue(runScript.contains("chmod u+x "))
        assertTrue(runScript.contains("LD_LIBRARY_PATH="))
        assertTrue(runScript.contains("/bin/timeout -k 10s 40s "))
        assertTrue(runScript.contains("'$deviceExe' 'arg1'"))
        assertTrue(runScript.contains("< /dev/null"))
        assertTrue(runScript.contains("__OHOS_HDC_EXIT__"))
    }

    @Test
    fun `execute uses local executable file name on device`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0))
        val localExe = tempDir.resolve("my_unique_name.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        )

        assertEquals(
            listOf("file", "send", localExe.absolutePath, DEVICE_EXE_PREFIX + "my_unique_name.kexe"),
            recording.requests[1].args,
        )
    }

    @Test
    fun `execute escapes single quotes in device command arguments`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0))
        val localExe = tempDir.resolve("quote_its.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(
                executableAbsolutePath = localExe.absolutePath,
                args = mutableListOf("it's"),
            )
        )

        val runScript = recording.requests.last().args[1]
        assertTrue(runScript.contains("it'\\''s"))
    }

    @Test
    fun `execute skips sync when destination already synced and exe mtime unchanged`(@TempDir tempDir: Path) {
        // First execute: prepare + send + run; second: run only (same device path, same mtime).
        val recording = RecordingExecutor(
            outputs = listOf(
                "OK", // prepare
                "OK", // file send
                "first\n__OHOS_HDC_EXIT__:0\n",
                "second\n__OHOS_HDC_EXIT__:0\n",
            )
        )
        val localExe = tempDir.resolve("sync_skip.kexe").toFile().apply { writeText("bin") }
        val executor = OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC)

        executor.execute(ExecuteRequest(executableAbsolutePath = localExe.absolutePath))
        assertEquals(3, recording.requests.size)
        assertEquals("file", recording.requests[1].args[0])

        executor.execute(ExecuteRequest(executableAbsolutePath = localExe.absolutePath))
        assertEquals(4, recording.requests.size)
        assertEquals("shell", recording.requests[3].args[0])
        assertEquals(1, recording.requests.count { it.args.getOrNull(0) == "file" })
    }

    @Test
    fun `execute resyncs when executed kexe mtime changes`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(
            outputs = listOf(
                "OK", "OK", "first\n__OHOS_HDC_EXIT__:0\n",
                "OK", "OK", "second\n__OHOS_HDC_EXIT__:0\n",
            )
        )
        val localExe = tempDir.resolve("sync_mtime.kexe").toFile().apply { writeText("bin-v1") }
        val executor = OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC)

        executor.execute(ExecuteRequest(executableAbsolutePath = localExe.absolutePath))
        assertEquals(3, recording.requests.size)

        // Ensure mtime advances even on coarse filesystems.
        val previous = localExe.lastModified()
        localExe.writeText("bin-v2")
        if (localExe.lastModified() <= previous) {
            assertTrue(localExe.setLastModified(previous + 1000L))
        }

        executor.execute(ExecuteRequest(executableAbsolutePath = localExe.absolutePath))
        assertEquals(6, recording.requests.size)
        assertEquals(2, recording.requests.count { it.args.getOrNull(0) == "file" })
        assertEquals(
            listOf("file", "send", localExe.absolutePath, DEVICE_EXE_PREFIX + "sync_mtime.kexe"),
            recording.requests[4].args,
        )
    }

    @Test
    fun `execute preserves success when hdc and output are clean`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0, runPayload = "hello from device\n"))
        val localExe = tempDir.resolve("ok_clean.kexe").toFile().apply { writeText("bin") }

        assertEquals(
            0,
            OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
                ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
            ).exitCode
        )
    }

    @Test
    fun `execute preserves non-zero device exit code from probe`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 2))
        val localExe = tempDir.resolve("exit_probe_2.kexe").toFile().apply { writeText("bin") }

        assertEquals(
            2,
            OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
                ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
            ).exitCode
        )
    }

    @Test
    fun `execute reports device exit code from probe`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 139))
        val localExe = tempDir.resolve("exit_probe_139.kexe").toFile().apply { writeText("bin") }

        assertEquals(
            139,
            OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
                ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
            ).exitCode
        )
    }

    @Test
    fun `execute fails after repeated hdc connect-key failures`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(
            outputs = List(3) { HDC_CONNECT_KEY_FAILURE }
        )
        val localExe = tempDir.resolve("no_device.kexe").toFile().apply { writeText("bin") }

        val error = assertThrows<IllegalStateException> {
            OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
                ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
            )
        }
        assertTrue(error.message!!.contains("hdc command failed"))
        assertEquals(3, recording.requests.size)
    }

    @Test
    fun `execute forwards captured stdout to request stream`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(
            successOutputs(syncSteps = 2, runExit = 0, runPayload = "device output\n")
        )
        val localExe = tempDir.resolve("stdout_forward.kexe").toFile().apply { writeText("bin") }
        val stdout = ByteArrayOutputStream()

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(
                executableAbsolutePath = localExe.absolutePath,
                stdout = stdout,
            )
        )

        val text = stdout.toString()
        assertTrue(text.contains("device output"))
        assertFalse(text.contains("__OHOS_HDC_EXIT__"))
    }

    @Test
    fun `execute always syncs libCrt so once and adds shared lib search path`(@TempDir tempDir: Path) {
        val libCrtDir = tempDir.resolve("nativeHome/konan/targets/ohos_arm64/native").toFile().apply { mkdirs() }
        val libCrt = libCrtDir.resolve("libcrt.so").apply { writeText("crt-runtime") }

        val workDir = tempDir.resolve("native.tests/bb.out/ha64_NatCrtGCTesGen/crt_cached_boxed_value").toFile().apply { mkdirs() }
        val localExe = File(workDir, "crt_cached_boxed_value.kexe").apply { writeText("bin") }
        val otherExe = File(workDir, "other.kexe").apply { writeText("bin2") }

        // first: prepare+send exe, prepare+send libCrt, run (5)
        // second: prepare+send other exe, run; libCrt skipped (3)
        val recording = RecordingExecutor(
            outputs = List(5) { "OK" } + listOf("__OHOS_HDC_EXIT__:0\n") +
                    List(2) { "OK" } + listOf("__OHOS_HDC_EXIT__:0\n")
        )
        val executor = OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC, libCrtSo = libCrt)
        executor.execute(ExecuteRequest(executableAbsolutePath = localExe.absolutePath))
        executor.execute(ExecuteRequest(executableAbsolutePath = otherExe.absolutePath))

        val sendRequests = recording.requests.filter { it.args.getOrNull(0) == "file" && it.args.getOrNull(1) == "send" }
        assertEquals(
            1,
            sendRequests.count { it.args[2] == libCrt.absolutePath && it.args[3] == "/data/local/tmp/native.tests/lib/libcrt.so" },
            "libcrt.so should be synced exactly once; sends=$sendRequests",
        )
        assertTrue(sendRequests.any { it.args[2] == localExe.absolutePath })
        assertTrue(sendRequests.any { it.args[2] == otherExe.absolutePath })

        val runScripts = recording.requests.filter {
            it.args.getOrNull(0) == "shell" && it.args.getOrNull(1)?.contains("__OHOS_HDC_EXIT__") == true
        }.map { it.args[1] }
        assertTrue(runScripts.isNotEmpty())
        assertTrue(
            runScripts.all {
                it.contains("LD_LIBRARY_PATH='/data/local/tmp/native.tests/bb.out/ha64_NatCrtGCTesGen/crt_cached_boxed_value:/data/local/tmp/native.tests/lib'")
            },
        )
    }

    @Test
    fun `unset ExecuteRequest timeout defaults hdc host processes to 30s`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0))
        val localExe = tempDir.resolve("timeout_default.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        )

        assertTrue(recording.requests.isNotEmpty())
        recording.requests.forEach { req ->
            assertEquals(30.seconds, req.timeout, "args=${req.args}")
        }
    }

    @Test
    fun `explicit ExecuteRequest timeout is honored without 30s clamp`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(successOutputs(syncSteps = 2, runExit = 0))
        val localExe = tempDir.resolve("timeout_explicit.kexe").toFile().apply { writeText("bin") }
        val explicit = 15.minutes

        OhosExecutor(recording, hdcAbsolutePath = FAKE_HDC).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath, timeout = explicit)
        )

        assertTrue(recording.requests.isNotEmpty())
        recording.requests.forEach { req ->
            assertEquals(explicit, req.timeout, "args=${req.args}")
        }
        val runScript = recording.requests.last().args[1]
        assertTrue(
            runScript.contains("/bin/timeout -k 10s 910s "),
            "device /bin/timeout must be -k tg (hdc+tg), got: $runScript",
        )
    }

    /** syncSteps successful host calls (prepare/send/...), then one run with exit probe. */
    private fun successOutputs(syncSteps: Int, runExit: Int, runPayload: String = ""): List<String> =
        List(syncSteps) { "OK" } + listOf("${runPayload}__OHOS_HDC_EXIT__:$runExit\n")

    private class RecordingExecutor(
        private val outputs: List<String>? = null,
        private val exitCodes: List<Int>? = null,
    ) : Executor {
        val requests = mutableListOf<ExecuteRequest>()
        private var callIndex = 0

        override fun execute(request: ExecuteRequest): ExecuteResponse {
            requests.add(request)
            val output = outputs?.getOrNull(callIndex) ?: "OK"
            val exitCode = exitCodes?.getOrNull(callIndex) ?: 0
            callIndex++
            request.stdout.write(output.toByteArray())
            return ExecuteResponse(exitCode = exitCode, executionTime = ZERO)
        }
    }
}
