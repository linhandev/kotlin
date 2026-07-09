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
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.ByteArrayOutputStream
import java.nio.file.Path
import kotlin.time.Duration.Companion.ZERO

class OhosExecutorTest {

    @Test
    fun `execute pushes binary via hdc and runs on device`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor()
        val localExe = tempDir.resolve("test.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording).execute(
            ExecuteRequest(
                executableAbsolutePath = localExe.absolutePath,
                args = listOf("arg1"),
            )
        )

        assertEquals(4, recording.requests.size)
        assertEquals(listOf("shell", "rm", "/data/local/tmp/test.kexe"), recording.requests[0].args)
        assertEquals(
            listOf("file", "send", localExe.absolutePath, "/data/local/tmp/test.kexe"),
            recording.requests[1].args,
        )
        assertEquals(listOf("shell", "chmod", "a+x", "/data/local/tmp/test.kexe"), recording.requests[2].args)

        val runRequest = recording.requests[3]
        assertEquals("shell", runRequest.args[0])
        assertEquals("sh", runRequest.args[1])
        assertEquals("-c", runRequest.args[2])
        val deviceCmd = runRequest.args[3]
        assertTrue(deviceCmd.contains("LD_PRELOAD=/data/local/tmp/libc++_shared.so"))
        assertTrue(deviceCmd.contains("/data/local/tmp/test.kexe arg1"))
    }

    @Test
    fun `execute treats runtime error output as failure even when hdc exits zero`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(
            outputs = listOf("", "", "", "symbol not found: foo"),
        )
        val localExe = tempDir.resolve("fail.kexe").toFile().apply { writeText("bin") }

        val response = OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        )

        assertEquals(1, response.exitCode)
    }

    @Test
    fun `execute escapes single quotes in device command arguments`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor()
        val localExe = tempDir.resolve("test.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording).execute(
            ExecuteRequest(
                executableAbsolutePath = localExe.absolutePath,
                args = listOf("it's"),
            )
        )

        val deviceCmd = recording.requests.last().args[3]
        assertTrue(deviceCmd.contains("it'\\''s"))
    }

    @Test
    fun `execute treats segmentation fault output as failure`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "Segmentation fault"))
        val localExe = tempDir.resolve("crash.kexe").toFile().apply { writeText("bin") }

        assertEquals(1, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute treats fatal signal output as failure`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "Fatal signal 11 (SIGSEGV)"))
        val localExe = tempDir.resolve("crash.kexe").toFile().apply { writeText("bin") }

        assertEquals(1, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute preserves non-zero hdc exit code`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(
            exitCodes = listOf(0, 0, 0, 2),
        )
        val localExe = tempDir.resolve("test.kexe").toFile().apply { writeText("bin") }

        assertEquals(2, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute uses local executable file name on device`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor()
        val localExe = tempDir.resolve("my-test.kexe").toFile().apply { writeText("bin") }

        OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        )

        assertEquals(
            listOf("file", "send", localExe.absolutePath, "/data/local/tmp/my-test.kexe"),
            recording.requests[1].args,
        )
    }

    @Test
    fun `execute treats undefined symbol output as failure`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "undefined symbol: foo"))
        val localExe = tempDir.resolve("fail.kexe").toFile().apply { writeText("bin") }

        assertEquals(1, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute treats error loading shared library as failure`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "Error loading shared library"))
        val localExe = tempDir.resolve("fail.kexe").toFile().apply { writeText("bin") }

        assertEquals(1, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute treats fatal exception output as failure`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "Fatal exception in runtime"))
        val localExe = tempDir.resolve("fail.kexe").toFile().apply { writeText("bin") }

        assertEquals(1, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute preserves success when hdc and output are clean`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "hello from device"))
        val localExe = tempDir.resolve("ok.kexe").toFile().apply { writeText("bin") }

        assertEquals(0, OhosExecutor(recording).execute(
            ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
        ).exitCode)
    }

    @Test
    fun `execute fails fast when hdc reports no device`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("no device"))
        val localExe = tempDir.resolve("test.kexe").toFile().apply { writeText("bin") }

        assertThrows<IllegalStateException> {
            OhosExecutor(recording).execute(
                ExecuteRequest(executableAbsolutePath = localExe.absolutePath)
            )
        }
    }

    @Test
    fun `execute forwards captured stdout to request stream`(@TempDir tempDir: Path) {
        val recording = RecordingExecutor(outputs = listOf("", "", "", "device output"))
        val localExe = tempDir.resolve("test.kexe").toFile().apply { writeText("bin") }
        val stdout = ByteArrayOutputStream()

        OhosExecutor(recording).execute(
            ExecuteRequest(
                executableAbsolutePath = localExe.absolutePath,
                stdout = stdout,
            )
        )

        assertTrue(stdout.toString().contains("device output"))
    }

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
