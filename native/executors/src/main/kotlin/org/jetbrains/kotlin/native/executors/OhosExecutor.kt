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
 * - hdc command exits 0 on the host regardless of the inner command's exit code on device. [OhosExecutor] appends printing $? after actual
 *     execution command to get the inner exit code
 * - Run inner command with `hdc shell <script>` (not `hdc shell sh -c <script>`) so the probe’s `$?` matches the test binary exit code
 * - Host stdin is not forwarded to the binary on device by hdc; non-empty [ExecuteRequest.stdin] is uploaded and applied with shell
 *     redirection in inner command. All other tc takes input from /dev/null
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
) : Executor {
    private val logger = Logger.getLogger(OhosExecutor::class.java.name)
    private val deviceExeDir = "/data/local/tmp/native.tests"

    /** Shared .so files (CRT runtime, future runtimes) pushed once under this directory. */
    private val deviceSharedLibDir = "$deviceExeDir/lib"

    companion object {
        private const val HDC_CONNECT_KEY_FAILURE = "[Fail]ExecuteCommand need connect-key? please confirm a device by help info"

        private const val HDC_CONNECT_KEY_RETRY_DELAY_MS = 2_000L

        /** At most this many hdc invocations when [HDC_CONNECT_KEY_FAILURE] is reported to prevent hdc channel flakiness */
        private const val HDC_CONNECT_KEY_MAX_ATTEMPTS = 3

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
        val executionScript = buildString {
            append("chmod u+x '${shellEscape(deviceExePath)}' ; ")
            append("LD_LIBRARY_PATH='${shellEscape(ldLibraryPath)}' ")
            append(onDeviceExeAndArgs)
            append(" < ")
            append(stdinRedirect)
            append("; printf '\\n__OHOS_HDC_EXIT__:%d' $?")
        }
        val executionRequest = ExecuteRequest(
            executableAbsolutePath = hdcAbsolutePath,
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
