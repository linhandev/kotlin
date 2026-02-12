 /** 
   Eazytec is pleased to support the open source community by making CPF-KMP-CMP available.
   Copyright (C) 2026 Eazytec. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

 package org.jetbrains.kotlin.native.executors

 import kotlinx.coroutines.TimeoutCancellationException
 import kotlinx.coroutines.withTimeout
 import java.io.ByteArrayOutputStream
 import java.io.File
 import java.nio.file.Paths
 import java.util.logging.Logger
 import kotlin.time.Duration
 import kotlin.time.ExperimentalTime
 import kotlin.time.measureTimedValue
 
 /**
  * [Executor] that runs the process on a HarmonyOS device using hdc commands.
  * HDC path resolution is done by scripts/test-capi.sh; here we use HDC_PATH when set, else "hdc".
  */
 @OptIn(ExperimentalTime::class)
 class OhosExecutor : Executor {
     private val logger = Logger.getLogger(OhosExecutor::class.java.name)
     private val hostExecutor: Executor = HostExecutor()
     private val hdcPath = findHdcPath()
     private val deviceExePath = "/data/local/tmp/test.kexe"
 
     /** Use HDC_PATH if set and executable; otherwise "hdc" (script already does full discovery). */
     private fun findHdcPath(): String {
         val env = System.getenv("HDC_PATH")
         if (env != null && File(env).canExecute()) return env
         return "hdc"
     }
  
     private val LD_PRELOAD = "LD_PRELOAD=/data/app/el1/bundle/public/com.huawei.hmos.location/libs/arm64/libc++_shared.so"
 
     /**
      * When set to "true", run with LD_LIBRARY_PATH=/system/lib64:/vendor/lib64 so the process
      * loads only system-provided .so (e.g. to verify "missing symbol" crash without weak fallback).
      * Optional override: OHOS_LD_LIBRARY_PATH (e.g. /system/lib64:/vendor/lib64).
      */
     private fun shellEnvForExecution(): List<String> {
         val useSystemLibs = System.getenv("OHOS_USE_SYSTEM_LIBS") == "true"
         val libPath = System.getenv("OHOS_LD_LIBRARY_PATH")
             ?: if (useSystemLibs) "/system/lib64:/vendor/lib64" else null
         val envs = mutableListOf<String>()
         if (libPath != null) {
             envs.add("LD_LIBRARY_PATH=$libPath")
             logger.info("OHOS execution: using LD_LIBRARY_PATH=$libPath (system libs only)")
         }
         envs.add(LD_PRELOAD)
         return envs
     }
  
     override fun execute(request: ExecuteRequest): ExecuteResponse {
         val localExePath = request.executableAbsolutePath
         val workingDirectory = request.workingDirectory ?: File(localExePath).parentFile
 
         logger.info("Starting HarmonyOS execution flow for $localExePath")
 
         executeHdcCommand("shell", "rm", deviceExePath)
         executeHdcCommand("file", "send", localExePath, deviceExePath)
         executeHdcCommand("shell", "chmod", "a+x", deviceExePath)
 
         // 用 sh -c "cmd 2>&1" 在设备上合并 stderr 到 stdout，否则动态链接器错误（如 Error loading shared library）
         // 只写 stderr 时 hdc 可能不转发，导致主机端捕获不到
         val envPart = shellEnvForExecution().joinToString(" ")
         val exeAndArgs = listOf(deviceExePath) + request.args
         val cmdOnDevice = exeAndArgs.joinToString(" ") { arg -> shellEscape(arg) }
         val fullCmd = "$envPart $cmdOnDevice 2>&1"
         val args = listOf("shell", "sh", "-c", fullCmd)
 
         val captureOut = ByteArrayOutputStream()
         val captureErr = ByteArrayOutputStream()
         val executionRequest = ExecuteRequest(
             executableAbsolutePath = hdcPath,
             args = args.toMutableList(),
             workingDirectory = workingDirectory,
             stdin = request.stdin,
             stdout = captureOut,
             stderr = captureErr,
             environment = request.environment,
             timeout = request.timeout
         )
 
         val response = hostExecutor.execute(executionRequest)
         val outBytes = captureOut.toByteArray()
         val errBytes = captureErr.toByteArray()
         request.stdout?.apply { write(outBytes); flush() }
         request.stderr?.apply { write(errBytes); flush() }
 
         val output = captureOut.toString() + captureErr.toString()
         val runtimeFailed = hasRuntimeErrorInOutput(output)
         val exitFailed = response.exitCode != null && response.exitCode != 0
         return when {
             exitFailed -> response
             runtimeFailed -> {
                 logger.warning("Device output indicates runtime failure (e.g. symbol not found); treating as exit 1")
                 response.copy(exitCode = 1)
             }
             else -> response
         }
     }
 
     /**
      * Escape a single argument for use inside sh -c '...' (single-quoted string).
      * Replaces ' by '\'' so the argument can be safely concatenated into the command.
      */
     private fun shellEscape(arg: String): String = arg.replace("'", "'\\''")
 
     /**
      * Detect device-side runtime failures that may not be reflected in hdc/shell exit code
      * (e.g. symbol not found, dlopen failure, "Error loading shared library", crash).
      * When present, we report failure so the test suite does not incorrectly pass.
      */
     private fun hasRuntimeErrorInOutput(output: String): Boolean {
         val lower = output.lowercase()
         val patterns = listOf(
             "symbol not found", "undefined symbol", "undefined symbol:",
             "dlopen failed", "error while loading shared libraries", "error loading shared library",
             "no such file or directory", "needed by",
             "segmentation fault", "fatal signal", "fatal exception",
             "killed", "abort", "cannot locate symbol"
         )
         return patterns.any { lower.contains(it) }
     }
      
     private fun executeHdcCommand(vararg commandArgs: String) {
         val stdout = ByteArrayOutputStream()
         val stderr = ByteArrayOutputStream()
         val req = ExecuteRequest(
             executableAbsolutePath = hdcPath,
             args = commandArgs.toMutableList(),
             workingDirectory = Paths.get("").toAbsolutePath().toFile(),
             stdout = stdout,
             stderr = stderr,
         )
         val response = hostExecutor.execute(req)
         response.assertSuccess()
         val output = stdout.toString() + stderr.toString()
         if (output.contains("[Fail]") || output.contains("need connect-key") || output.contains("no device")) {
             throw IllegalStateException(
                 "hdc command failed: ${commandArgs.joinToString(" ")}\n" +
                 "Output: $output\n" +
                 "Please ensure a HarmonyOS device is connected. Run 'hdc list targets' to check."
             )
         }
     }
 }