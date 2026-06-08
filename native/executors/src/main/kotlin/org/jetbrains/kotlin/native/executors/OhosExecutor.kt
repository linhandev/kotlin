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

import org.jetbrains.kotlin.native.executors.OhosExecutor.Companion.HDC_CONNECT_KEY_FAILURE
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * [Executor] that runs the process on a HarmonyOS device using hdc commands.
 *
 * notes:
 * - hdc command has higher communication cost, this executor avoid sending the same file/folder to device repeatedly by keeping track of
 *     what's already sent in this session and skipping subsequent send
 * - hdc command exits 0 on the host regardless of the inner command's exit code on device. [OhosExecutor] appends printing $? after actual
 *     execution command to get the inner exit code
 * - Run inner command with `hdc shell <script>` (not `hdc shell sh -c <script>`) so the probe’s `$?` matches the test binary exit code
 * - Host stdin is not forwarded to the binary on device by hdc; non-empty [ExecuteRequest.stdin] is uploaded and applied with shell
 *     redirection in inner command. All other tc takes input from /dev/null
 */

class OhosExecutor(
    private val hostExecutor: Executor = HostExecutor(),
) : Executor {
    private val logger = Logger.getLogger(OhosExecutor::class.java.name)
    private val deviceExeDir = "/data/local/tmp/native.tests"

    companion object {
        private const val HDC_CONNECT_KEY_FAILURE = "[Fail]ExecuteCommand need connect-key? please confirm a device by help info"

        private const val HDC_CONNECT_KEY_RETRY_DELAY_MS = 2_000L

        /** At most this many hdc invocations when [HDC_CONNECT_KEY_FAILURE] is reported to prevent hdc channel flakiness */
        private const val HDC_CONNECT_KEY_MAX_ATTEMPTS = 3

        /**
         * Records destinations already synced this JVM run.
         * [ConcurrentHashMap.computeIfAbsent] runs at most once per key (atomically); concurrent callers for the same key block until [sync] finishes.
         */
        private val syncOnceCompleted = ConcurrentHashMap<String, Boolean>()

        /**
         * Runs [sync] at most once per [destinationKey] for the lifetime of this process.
         * Concurrent callers for the same key block until the first sync finishes, then skip.
         */
        private fun syncToDeviceOnce(destinationKey: String, sync: () -> Unit) {
            syncOnceCompleted.computeIfAbsent(destinationKey) {
                sync()
                true
            }
        }
    }

    private fun hdcCommandTimeout(outerTimeout: Duration): Duration = minOf(outerTimeout, 30.seconds)

    override fun execute(request: ExecuteRequest): ExecuteResponse {
        val hdcTimeout = hdcCommandTimeout(request.timeout)
        val localExePath = request.executableAbsolutePath
        val workingDirectory = request.workingDirectory ?: File(localExePath).parentFile
        val exeName = File(localExePath).name
        val deviceExePath = deviceExecutablePath(workingDirectory, exeName)

        logger.info("Executing $localExePath")

        // Interop tests rely on more than just [request.executableAbsolutePath], sync the whole folder for these tc
        val deviceWorkDir = File(deviceExePath).parent.replace(File.separatorChar, '/')
        val syncWholeWorkingDir = exeName == "clangMain" && pathSuffixAfterNativeTests(workingDirectory) != null
        val syncDestinationKey = if (syncWholeWorkingDir) {
            deviceWorkDir
        } else {
            deviceExePath
        }.replace(File.separatorChar, '/')

        syncToDevice(
            hdcTimeout = hdcTimeout,
            destinationKey = syncDestinationKey,
            localSourcePath = if (syncWholeWorkingDir) workingDirectory.normalize().absolutePath else localExePath,
            deviceDestinationPath = if (syncWholeWorkingDir) deviceWorkDir else deviceExePath,
            removeRecursively = syncWholeWorkingDir,
        )

        val stdinBytes = request.stdin.readBytes()
        val deviceStdinPath = "${deviceExePath}.stdin".replace(File.separatorChar, '/')
        val stdinRedirect: String = when {
            stdinBytes.isNotEmpty() -> {
                val stdinTemp = File.createTempFile("ohos_stdin_", ".bin", workingDirectory)
                try {
                    stdinTemp.writeBytes(stdinBytes)
                    syncToDevice(
                        hdcTimeout = hdcTimeout,
                        destinationKey = deviceStdinPath,
                        localSourcePath = stdinTemp.absolutePath,
                        deviceDestinationPath = deviceStdinPath,
                        removeRecursively = false,
                        skipSynced = true,
                    )
                } finally {
                    stdinTemp.delete()
                }
                "'${shellEscape(deviceStdinPath)}'"
            }
            else -> "/dev/null"
        }

        val captureOut = ByteArrayOutputStream()
        val captureErr = ByteArrayOutputStream()
        val onDeviceExeAndArgs = (listOf(deviceExePath) + request.args).joinToString(" ") { "'${shellEscape(it)}'" }
        val executionScript = buildString {
            append("chmod u+x '${shellEscape(deviceExePath)}' ; ")
            append("LD_LIBRARY_PATH='${shellEscape(deviceWorkDir)}' ")
            append(onDeviceExeAndArgs)
            append(" < ")
            append(stdinRedirect)
            append("; printf '\\n__OHOS_HDC_EXIT__:%d' $?")
        }
        val executionRequest = ExecuteRequest(
            executableAbsolutePath = "hdc",
            args = mutableListOf("shell", executionScript),
            workingDirectory = workingDirectory,
            stdin = ByteArrayInputStream(byteArrayOf()),
            stdout = captureOut,
            stderr = captureErr,
            environment = request.environment,
            timeout = hdcTimeout,
        )

        val response = hostExecutor.execute(executionRequest)
        val (outText, codeOut) = stripHdcExitMarkers(captureOut.toString())
        val (errText, codeErr) = stripHdcExitMarkers(captureErr.toString())
        val deviceExitCode = codeOut ?: codeErr
        request.stdout.apply { write(outText.toByteArray(Charsets.UTF_8)); flush() }
        request.stderr.apply { write(errText.toByteArray(Charsets.UTF_8)); flush() }

        val effectiveExitCode =
            if (response.exitCode == null) null
            else deviceExitCode ?: response.exitCode
        val exitFailed = effectiveExitCode != null && effectiveExitCode != 0
        return when {
            response.exitCode == null -> response
            exitFailed -> response.copy(exitCode = effectiveExitCode)
            else -> response.copy(exitCode = effectiveExitCode)
        }
    }

    /**
     * Returns the path under [deviceExeDir] for [exeName] so parallel test runs do not overwrite
     * the same device path. Uses the portion of [workingDirectory] after the `native.tests` segment
     * (e.g. `build/t/s/.../testSimple/simple`), mirroring the host layout.
     */
    private fun deviceExecutablePath(workingDirectory: File, exeName: String): String {
        val suffix = pathSuffixAfterNativeTests(workingDirectory)
        return if (suffix != null) "$deviceExeDir/$suffix/$exeName" else "$deviceExeDir/$exeName"
    }

    private fun pathSuffixAfterNativeTests(workingDirectory: File): String? {
        val path = workingDirectory.normalize().absolutePath.replace(File.separatorChar, '/')
        val marker = "/native.tests/"
        val idx = path.indexOf(marker)
        if (idx < 0) return null
        return path.substring(idx + marker.length).trim('/').takeIf { it.isNotEmpty() }
    }

    /**
     * @param skipSynced when `false` (default), at most one upload per [destinationKey] in this JVM.
     *   When `true`, always sync (e.g. stdin: same path may get new bytes each run).
     */
    private fun syncToDevice(
        hdcTimeout: Duration,
        destinationKey: String,
        localSourcePath: String,
        deviceDestinationPath: String,
        removeRecursively: Boolean,
        skipSynced: Boolean = false,
    ) {
        val sync: () -> Unit = {
            val destinationParent = deviceDestinationPath.substringBeforeLast('/', "")
            if (destinationParent.isNotEmpty()) {
                executeHdcCommand(hdcTimeout, "shell", "mkdir", "-p", destinationParent)
            }
            executeHdcCommand(
                hdcTimeout,
                "shell",
                "rm",
                if (removeRecursively) "-rf" else "-f",
                deviceDestinationPath,
            )
            executeHdcCommand(hdcTimeout, "file", "send", localSourcePath, deviceDestinationPath)
        }
        if (skipSynced) {
            sync()
        } else {
            syncToDeviceOnce(destinationKey, sync)
        }
    }

    private fun executeHdcCommand(hdcTimeout: Duration, vararg commandArgs: String) {
        var lastOutput = ""
        repeat(HDC_CONNECT_KEY_MAX_ATTEMPTS) { attemptIndex ->
            val stdout = ByteArrayOutputStream()
            val stderr = ByteArrayOutputStream()
            val req = ExecuteRequest(
                executableAbsolutePath = "hdc",
                args = commandArgs.toMutableList(),
                workingDirectory = Paths.get("").toAbsolutePath().toFile(),
                stdout = stdout,
                stderr = stderr,
                timeout = hdcCommandTimeout(hdcTimeout),
            )
            val response = hostExecutor.execute(req)
            response.assertSuccess()
            val output = stdout.toString() + stderr.toString()
            lastOutput = output
            if (!output.contains(HDC_CONNECT_KEY_FAILURE)) {
                return
            }
            val isLastAttempt = attemptIndex == HDC_CONNECT_KEY_MAX_ATTEMPTS - 1
            if (!isLastAttempt) {
                logger.warning(
                    "hdc connect-key/device not ready (attempt ${attemptIndex + 1}/$HDC_CONNECT_KEY_MAX_ATTEMPTS); " +
                            "retrying in ${HDC_CONNECT_KEY_RETRY_DELAY_MS}ms: ${commandArgs.joinToString(" ")}"
                )
                Thread.sleep(HDC_CONNECT_KEY_RETRY_DELAY_MS)
            }
        }
        throw IllegalStateException(
            "hdc command failed after $HDC_CONNECT_KEY_MAX_ATTEMPTS attempts: ${commandArgs.joinToString(" ")}\nOutput: $lastOutput\n"
        )
    }

    /**
     * Removes the hdc exit probe: last line must be `__OHOS_HDC_EXIT__:<code>` (after dropping one trailing empty line
     * if present, e.g. hdc adding `\n` after the probe).
     */
    private fun stripHdcExitMarkers(text: String): Pair<String, Int?> {
        val marker = "__OHOS_HDC_EXIT__:"
        val lines = text.split('\n')
        val content = if (lines.isNotEmpty() && lines.last().isEmpty()) lines.dropLast(1) else lines
        if (content.isEmpty()) return text to null
        val lastLine = content.last().trimEnd('\r')
        if (!lastLine.startsWith(marker)) return text to null
        val code = lastLine.substring(marker.length).trim().toIntOrNull() ?: return text to null
        return content.dropLast(1).joinToString("\n") to code
    }

    /**
     * Escape a string for embedding inside a POSIX single-quoted segment:
     * replace `'` with `'\''`. Wrap the result in single quotes at the call site, e.g. `'${shellEscape(path)}'`.
     */
    private fun shellEscape(arg: String): String = arg.replace("'", "'\\''")
}
