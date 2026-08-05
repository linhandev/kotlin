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
 * - hdc command has higher communication cost; this executor skips re-sync when the device destination was already sent in this JVM
 *     and the executed host executable's [File.lastModified] is unchanged (rebuilds with a newer mtime re-sync)
 * - hdc command exits 0 on the host regardless of the inner command's exit code on device. [OhosExecutor] records $? via a trailing
 *     `__OHOS_HDC_EXIT__:<code>` probe on the shell stdout/stderr path and a device-side exit file (same code). The host hdc exit
 *     must never be treated as the device exit: if both probes are missing after hdc finishes, execution fails hard
 * - Run inner command with `hdc shell <script>` (not `hdc shell sh -c <script>`) so the probe’s `$?` matches the test binary exit code
 * - Host stdin is not forwarded to the binary on device by hdc; non-empty [ExecuteRequest.stdin] is uploaded and applied with shell
 *     redirection in inner command. All other tc takes input from /dev/null
 * - [ExecuteRequest.timeout]: when unset ([Duration.INFINITE]), hdc host processes default to 30s; when explicitly set
 *     (e.g. native.tests EXECUTION_TIMEOUT / CLI `--timeout`), that value is honored for sync and on-device execution
 * - On-device binary is wrapped with toybox `/bin/timeout -k tg (hdcTimeout+tg)` where [TIMEOUT_GRACE] (`tg`)
 *     both delays device TERM past the host hdc limit (so hdc usually times out first) and is the TERM→KILL wait.
 */

class OhosExecutor(
    private val hostExecutor: Executor = HostExecutor(),
    /** Absolute path to `hdc`. The first executable `hdc` on `PATH`. */
    private val hdcAbsolutePath: String = findHdcOnPath(),
    /**
     * `libcrt.so` from the same KN dist/target that native.tests used to compile.
     * Supplied by the test harness from KotlinNativeHome + test target; not read from system properties here.
     */
    private val libCrtSo: File? = null,
    private val deviceId: String? = null,
) : Executor {
    private val logger = Logger.getLogger(OhosExecutor::class.java.name)
    private val deviceExeDir = "/data/local/tmp/native.tests"

    /** Shared .so files (CRT runtime, future runtimes) pushed once under this directory. */
    private val deviceSharedLibDir = "$deviceExeDir/lib"

    /** Returns hdc args for target device selection: ["-t", deviceId] or empty list. */
    private val deviceArgs: List<String>
        get() = if (deviceId != null) listOf("-t", deviceId) else emptyList()

    companion object {
        private const val HDC_CONNECT_KEY_FAILURE = "[Fail]ExecuteCommand need connect-key? please confirm a device by help info"

        private const val HDC_CONNECT_KEY_RETRY_DELAY_MS = 2_000L

        /** At most this many hdc invocations when [HDC_CONNECT_KEY_FAILURE] is reported to prevent hdc channel flakiness */
        private const val HDC_CONNECT_KEY_MAX_ATTEMPTS = 3

        // alloc_dealloc_mismatch is suppressed. On-device measurement (aarch64 OHOS):
        //   - libffrt.so (/system/lib64/ndk/libffrt.so) does operator-new-then-free during its
        //     init, tripping ASAN alloc-dealloc-mismatch and aborting otherwise-clean (negative)
        //     test binaries. The mismatch stack frames all land inside libffrt.so, outside the
        //     test binary, so it is system-lib noise, not a test bug.
        // TODO: drop this option once libffrt.so stops mixing new/free (tracked per OHOS release);
        //       then a clean ASAN run needs no ASAN_OPTIONS at all.
        private const val ASAN_OPTIONS = "alloc_dealloc_mismatch=0"

        /** Stream probe prefix; last line of captured hdc shell output after a successful on-device run. */
        private const val HDC_EXIT_MARKER = "__OHOS_HDC_EXIT__:"

        /**
         * Shared grace for on-device kexe execution timeout:
         * - start execution + test case timeout + TIMEOUT_GRACE: send TERM signal
         * - start execution + test case timeout + 2 * TIMEOUT_GRACE: send hard KILL signal
         */
        private val TIMEOUT_GRACE = 10.seconds

        /**
         * Device destination → lastModified of the host executable that was synced for that destination.
         * Re-sync when the executable mtime changes (same path after rebuild); skip when unchanged.
         */
        private val syncedExeMtimeByDestination = ConcurrentHashMap<String, Long>()

        /**
         * Runs [sync] when [destinationKey] was never synced, or when [executedExeLastModified] differs
         * from the mtime recorded for the previous sync. Concurrent callers for the same key are serialized by [ConcurrentHashMap.compute].
         */
        private fun syncToDeviceOnce(destinationKey: String, executedExeLastModified: Long, sync: () -> Unit) {
            syncedExeMtimeByDestination.compute(destinationKey) { _, previousMtime ->
                if (previousMtime == executedExeLastModified) {
                    previousMtime
                } else {
                    sync()
                    executedExeLastModified
                }
            }
        }

        /** First executable named `hdc` on `PATH`, as an absolute path. */
        private fun findHdcOnPath(): String {
            for (dir in (System.getenv("PATH") ?: "").split(File.pathSeparatorChar)) {
                if (dir.isEmpty()) continue
                val candidate = File(dir, "hdc")
                try {
                    if (candidate.isFile && candidate.canExecute()) return candidate.absolutePath
                } catch (_: SecurityException) {
                    // Skip PATH entries the SecurityManager does not allow reading.
                }
            }
            error("hdc not found on PATH")
        }
    }

    /**
     * Final gatekeeper for timeout, if nothing is set, cap it at 30s
     */
    private fun hdcCommandTimeout(outerTimeout: Duration): Duration =
        if (outerTimeout.isInfinite()) 30.seconds else outerTimeout

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
            executedExeLastModified = File(localExePath).lastModified(),
        )

        // Always stage libcrt.so from the KN dist under test into [deviceSharedLibDir]
        // (mtime-keyed syncToDeviceOnce). libc++ is already on device. Search path always
        // includes [deviceSharedLibDir] so CRT / cinterop+CMC can resolve it.
        syncLibCrtToDeviceOnce(hdcTimeout)

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
        val ldLibraryPath = "$deviceWorkDir:$deviceSharedLibDir"
        val timeoutGraceArg = formatToyboxTimeoutDuration(TIMEOUT_GRACE)
        val deviceTimeoutArg = formatToyboxTimeoutDuration(hdcTimeout + TIMEOUT_GRACE)
        // Side-file survives hdc shell stdout truncation that can drop the trailing stream probe.
        val deviceExitFile = "$deviceExePath.hdc_exit"
        val executionScript = buildString {
            append("chmod u+x '${shellEscape(deviceExePath)}' ; ")
            append("LD_LIBRARY_PATH='${shellEscape(ldLibraryPath)}' ")
            append("ASAN_OPTIONS='${shellEscape(ASAN_OPTIONS)}' ")
            append("/bin/timeout -k $timeoutGraceArg $deviceTimeoutArg ")
            append(onDeviceExeAndArgs)
            append(" < ")
            append(stdinRedirect)
            append("; ec=$?; printf '%d' \"\$ec\" > '${shellEscape(deviceExitFile)}'; ")
            append("printf '\\n$HDC_EXIT_MARKER%d' \"\$ec\"")
        }
        val executionRequest = ExecuteRequest(
            executableAbsolutePath = hdcAbsolutePath,
            args = (deviceArgs + listOf("shell", executionScript)).toMutableList(),
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
        var deviceExitCode = codeOut ?: codeErr
        request.stdout.apply { write(outText.toByteArray(Charsets.UTF_8)); flush() }
        request.stderr.apply { write(errText.toByteArray(Charsets.UTF_8)); flush() }

        // Host timeout: preserve null exit (RunnerWithExecutor treats as timed out).
        if (response.exitCode == null) {
            return response
        }

        // Never fall back to hdc host exit (almost always 0). Prefer stream probe, then side-file.
        if (deviceExitCode == null) {
            deviceExitCode = readDeviceExitSideFile(hdcTimeout, deviceExitFile)
        }
        if (deviceExitCode == null) {
            val got = (outText + errText).trim().ifEmpty { "<empty>" }
            throw IllegalStateException(
                "Could not determine device exit code (hdc never ran the exit probe). Got: $got"
            )
        }

        return response.copy(exitCode = deviceExitCode)
    }

    /** Formats [timeout] for toybox `timeout` / `-k` duration arguments. */
    private fun formatToyboxTimeoutDuration(timeout: Duration): String {
        val seconds = timeout.inWholeSeconds.coerceAtLeast(1)
        return "${seconds}s"
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
     * Syncs [libCrtSo] once per JVM (mtime-keyed) to [deviceSharedLibDir]. No-op when unset / missing.
     */
    private fun syncLibCrtToDeviceOnce(hdcTimeout: Duration) {
        val libCrt = libCrtSo ?: return
        if (!libCrt.isFile) {
            logger.warning("libcrt.so not found at ${libCrt.absolutePath}; skipping CRT runtime sync")
            return
        }
        val deviceLibCrtPath = "$deviceSharedLibDir/libcrt.so"
        syncToDevice(
            hdcTimeout = hdcTimeout,
            destinationKey = deviceLibCrtPath,
            localSourcePath = libCrt.absolutePath,
            deviceDestinationPath = deviceLibCrtPath,
            removeRecursively = false,
            executedExeLastModified = libCrt.lastModified(),
        )
    }

    /**
     * @param skipSynced when `false` (default), sync at most once per [destinationKey] while the
     *   executed host binary's [executedExeLastModified] is unchanged. When `true`, always sync
     *   (e.g. stdin: same path may get new bytes each run).
     * @param executedExeLastModified [File.lastModified] of the host test executable; ignored when [skipSynced] is true.
     */
    private fun syncToDevice(
        hdcTimeout: Duration,
        destinationKey: String,
        localSourcePath: String,
        deviceDestinationPath: String,
        removeRecursively: Boolean,
        skipSynced: Boolean = false,
        executedExeLastModified: Long = 0L,
    ) {
        val sync: () -> Unit = {
            val destinationParent = deviceDestinationPath.substringBeforeLast('/', "")
            val rmFlag = if (removeRecursively) "-rf" else "-f"
            // One hdc shell for mkdir+rm to avoid an extra host↔device connect.
            val prepareScript = buildString {
                if (destinationParent.isNotEmpty()) {
                    append("mkdir -p '${shellEscape(destinationParent)}' && ")
                }
                append("rm $rmFlag '${shellEscape(deviceDestinationPath)}'")
            }
            executeHdcCommand(hdcTimeout, "shell", prepareScript)
            executeHdcCommand(hdcTimeout, "file", "send", localSourcePath, deviceDestinationPath)
        }
        if (skipSynced) {
            sync()
        } else {
            syncToDeviceOnce(destinationKey, executedExeLastModified, sync)
        }
    }

    private fun executeHdcCommand(hdcTimeout: Duration, vararg commandArgs: String) {
        var lastOutput = ""
        repeat(HDC_CONNECT_KEY_MAX_ATTEMPTS) { attemptIndex ->
            val stdout = ByteArrayOutputStream()
            val stderr = ByteArrayOutputStream()
            val req = ExecuteRequest(
                executableAbsolutePath = hdcAbsolutePath,
                args = (deviceArgs + commandArgs).toMutableList(),
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
     * Reads [deviceExitFile] written by the on-device script. Used when the stream probe was lost
     * (hdc shell stdout truncation on large dumps). Returns null if cat fails or content is not an int.
     */
    private fun readDeviceExitSideFile(hdcTimeout: Duration, deviceExitFile: String): Int? {
        val stdout = ByteArrayOutputStream()
        val stderr = ByteArrayOutputStream()
        val req = ExecuteRequest(
            executableAbsolutePath = hdcAbsolutePath,
            args = (deviceArgs + listOf("shell", "cat '${shellEscape(deviceExitFile)}'")).toMutableList(),
            workingDirectory = Paths.get("").toAbsolutePath().toFile(),
            stdout = stdout,
            stderr = stderr,
            timeout = hdcCommandTimeout(hdcTimeout),
        )
        val response = hostExecutor.execute(req)
        if (response.exitCode != 0 && response.exitCode != null) {
            return null
        }
        val output = (stdout.toString() + stderr.toString()).trim()
        // Wrong-device / connect failures print [Fail]... rather than a numeric exit code.
        if (output.contains("[Fail]") || output.contains(HDC_CONNECT_KEY_FAILURE)) {
            return null
        }
        return output.lineSequence().map { it.trim() }.firstOrNull { it.isNotEmpty() }?.toIntOrNull()
    }

    /**
     * Removes the hdc exit probe: last line must be `__OHOS_HDC_EXIT__:<code>` (after dropping one trailing empty line
     * if present, e.g. hdc adding `\n` after the probe).
     */
    private fun stripHdcExitMarkers(text: String): Pair<String, Int?> {
        val lines = text.split('\n')
        val content = if (lines.isNotEmpty() && lines.last().isEmpty()) lines.dropLast(1) else lines
        if (content.isEmpty()) return text to null
        val lastLine = content.last().trimEnd('\r')
        if (!lastLine.startsWith(HDC_EXIT_MARKER)) return text to null
        val code = lastLine.substring(HDC_EXIT_MARKER.length).trim().toIntOrNull() ?: return text to null
        return content.dropLast(1).joinToString("\n") to code
    }

    /**
     * Escape a string for embedding inside a POSIX single-quoted segment:
     * replace `'` with `'\''`. Wrap the result in single quotes at the call site, e.g. `'${shellEscape(path)}'`.
     */
    private fun shellEscape(arg: String): String = arg.replace("'", "'\\''")
}
