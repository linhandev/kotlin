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
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class ChildProcessTest {

    private fun logLine(message: String) {
        println("[stdout] ChildProcessTest $message")
    }

    @Test
    fun testErrCodeEnums() {
        logLine("--- Ability_NativeChildProcess_ErrCode ---")
        try {
            logLine("NCP_NO_ERROR=${platform.AbilityKit.ChildProcess.NCP_NO_ERROR}")
            logLine("NCP_ERR_INVALID_PARAM=${platform.AbilityKit.ChildProcess.NCP_ERR_INVALID_PARAM}")
            logLine("NCP_ERR_NOT_SUPPORTED=${platform.AbilityKit.ChildProcess.NCP_ERR_NOT_SUPPORTED}")
            logLine("NCP_ERR_INTERNAL=${platform.AbilityKit.ChildProcess.NCP_ERR_INTERNAL}")
            logLine("NCP_ERR_BUSY=${platform.AbilityKit.ChildProcess.NCP_ERR_BUSY}")
            logLine("NCP_ERR_TIMEOUT=${platform.AbilityKit.ChildProcess.NCP_ERR_TIMEOUT}")
            logLine("NCP_ERR_SERVICE_ERROR=${platform.AbilityKit.ChildProcess.NCP_ERR_SERVICE_ERROR}")
            logLine("NCP_ERR_MULTI_PROCESS_DISABLED=${platform.AbilityKit.ChildProcess.NCP_ERR_MULTI_PROCESS_DISABLED}")
            logLine("NCP_ERR_ALREADY_IN_CHILD=${platform.AbilityKit.ChildProcess.NCP_ERR_ALREADY_IN_CHILD}")
            logLine("NCP_ERR_MAX_CHILD_PROCESSES_REACHED=${platform.AbilityKit.ChildProcess.NCP_ERR_MAX_CHILD_PROCESSES_REACHED}")
            logLine("NCP_ERR_LIB_LOADING_FAILED=${platform.AbilityKit.ChildProcess.NCP_ERR_LIB_LOADING_FAILED}")
            logLine("NCP_ERR_CONNECTION_FAILED=${platform.AbilityKit.ChildProcess.NCP_ERR_CONNECTION_FAILED}")
            logLine("NCP_ERR_CALLBACK_NOT_EXIST=${platform.AbilityKit.ChildProcess.NCP_ERR_CALLBACK_NOT_EXIST}")
            assertNotEquals(
                platform.AbilityKit.ChildProcess.NCP_NO_ERROR,
                platform.AbilityKit.ChildProcess.NCP_ERR_INVALID_PARAM
            )
        } catch (e: Throwable) {
            logLine("ErrCode enums (API 20/22 values) exception: $e")
        }
    }

    @Test
    fun testIsolationModeEnums() {
        logLine("--- NativeChildProcess_IsolationMode ---")
        logLine("NCP_ISOLATION_MODE_NORMAL=${platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_NORMAL}")
        logLine("NCP_ISOLATION_MODE_ISOLATED=${platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_ISOLATED}")
        assertNotNull(platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_NORMAL)
        assertNotNull(platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_ISOLATED)
    }

    @Test
    fun testOH_Ability_CreateChildProcessConfigs() {
        logLine("--- OH_Ability_CreateChildProcessConfigs ---")
        try {
            val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
            logLine("OH_Ability_CreateChildProcessConfigs() result: $configs")
            assertNotNull(configs)
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
        } catch (e: Throwable) {
            logLine("OH_Ability_CreateChildProcessConfigs (API 20/21) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_DestroyChildProcessConfigs() {
        logLine("--- OH_Ability_DestroyChildProcessConfigs ---")
        try {
            val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
            logLine("OH_Ability_DestroyChildProcessConfigs(configs) called")
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(null)
            logLine("OH_Ability_DestroyChildProcessConfigs(null) called")
        } catch (e: Throwable) {
            logLine("OH_Ability_DestroyChildProcessConfigs (API 20/21) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_ChildProcessConfigs_SetIsolationMode() {
        logLine("--- OH_Ability_ChildProcessConfigs_SetIsolationMode ---")
        try {
            val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
            val result1 = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetIsolationMode(
                configs,
                platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_NORMAL
            )
            logLine("OH_Ability_ChildProcessConfigs_SetIsolationMode(NORMAL) result: $result1")
            assertNotNull(result1)
            val result2 = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetIsolationMode(
                configs,
                platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_ISOLATED
            )
            logLine("OH_Ability_ChildProcessConfigs_SetIsolationMode(ISOLATED) result: $result2")
            assertNotNull(result2)
            val nullResult = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetIsolationMode(
                null,
                platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_NORMAL
            )
            logLine("OH_Ability_ChildProcessConfigs_SetIsolationMode(null) result: $nullResult")
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
        } catch (e: Throwable) {
            logLine("OH_Ability_ChildProcessConfigs_SetIsolationMode (API 20/21) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_ChildProcessConfigs_SetIsolationUid() {
        logLine("--- OH_Ability_ChildProcessConfigs_SetIsolationUid ---")
        try {
            val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
            val result = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetIsolationUid(configs, false)
            logLine("OH_Ability_ChildProcessConfigs_SetIsolationUid(configs, false) result: $result")
            assertNotNull(result)
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
        } catch (e: Throwable) {
            logLine("OH_Ability_ChildProcessConfigs_SetIsolationUid (API 20/21) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_ChildProcessConfigs_SetProcessName() {
        logLine("--- OH_Ability_ChildProcessConfigs_SetProcessName ---")
        try {
            val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
            val result1 = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetProcessName(configs, "test_process")
            logLine("OH_Ability_ChildProcessConfigs_SetProcessName(configs, test_process) result: $result1")
            assertNotNull(result1)
            val result2 = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetProcessName(configs, null)
            logLine("OH_Ability_ChildProcessConfigs_SetProcessName(configs, null) result: $result2")
            assertNotNull(result2)
            val nullResult = platform.AbilityKit.ChildProcess.OH_Ability_ChildProcessConfigs_SetProcessName(null, "test_process")
            logLine("OH_Ability_ChildProcessConfigs_SetProcessName(null, ...) result: $nullResult")
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
        } catch (e: Throwable) {
            logLine("OH_Ability_ChildProcessConfigs_SetProcessName (API 20/21) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_CreateNativeChildProcess() {
        logLine("--- OH_Ability_CreateNativeChildProcess ---")
        val createResult = platform.AbilityKit.ChildProcess.OH_Ability_CreateNativeChildProcess(null, null)
        logLine("OH_Ability_CreateNativeChildProcess(null, null) result: $createResult")
        assertNotNull(createResult)
    }

    @Test
    fun testOH_Ability_CreateNativeChildProcessWithConfigs() {
        logLine("--- OH_Ability_CreateNativeChildProcessWithConfigs ---")
        try {
            val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
            val createWithConfigsResult = platform.AbilityKit.ChildProcess.OH_Ability_CreateNativeChildProcessWithConfigs(
                "libtest.so",
                configs,
                null
            )
            logLine("OH_Ability_CreateNativeChildProcessWithConfigs(libtest.so, configs, null) result: $createWithConfigsResult")
            assertNotNull(createWithConfigsResult)
            val createNullResult = platform.AbilityKit.ChildProcess.OH_Ability_CreateNativeChildProcessWithConfigs(null, configs, null)
            logLine("OH_Ability_CreateNativeChildProcessWithConfigs(null, configs, null) result: $createNullResult")
            platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
        } catch (e: Throwable) {
            logLine("OH_Ability_CreateNativeChildProcessWithConfigs (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_StartNativeChildProcess() {
        memScoped {
            logLine("--- OH_Ability_StartNativeChildProcess ---")
            val args = cValue<platform.AbilityKit.ChildProcess.NativeChildProcess_Args> {
                entryParams = null
                fdList.head = null
            }
            val options = cValue<platform.AbilityKit.ChildProcess.NativeChildProcess_Options> {
                isolationMode = platform.AbilityKit.ChildProcess.NCP_ISOLATION_MODE_NORMAL
                reserved = 0L
            }
            val pid = alloc<IntVar>()
            val startResult = platform.AbilityKit.ChildProcess.OH_Ability_StartNativeChildProcess(
                "libtest.so:Main",
                args,
                options,
                pid.ptr
            )
            logLine("OH_Ability_StartNativeChildProcess(libtest.so:Main, args, options, pid) result: $startResult pid=${pid.value}")
            assertNotNull(startResult)
            val startNullResult = platform.AbilityKit.ChildProcess.OH_Ability_StartNativeChildProcess(
                null,
                args,
                options,
                pid.ptr
            )
            logLine("OH_Ability_StartNativeChildProcess(null, ...) result: $startNullResult")
            assertNotNull(startNullResult)
        }
    }

    @Test
    fun testOH_Ability_StartNativeChildProcessWithConfigs() {
        memScoped {
            logLine("--- OH_Ability_StartNativeChildProcessWithConfigs ---")
            try {
                val configs = platform.AbilityKit.ChildProcess.OH_Ability_CreateChildProcessConfigs()
                val args = cValue<platform.AbilityKit.ChildProcess.NativeChildProcess_Args> {
                    entryParams = null
                    fdList.head = null
                }
                val pid = alloc<IntVar>()
                val startWithConfigsResult = platform.AbilityKit.ChildProcess.OH_Ability_StartNativeChildProcessWithConfigs(
                    "libtest.so:Main",
                    args,
                    configs,
                    pid.ptr
                )
                logLine("OH_Ability_StartNativeChildProcessWithConfigs(libtest.so:Main, args, configs, pid) result: $startWithConfigsResult")
                assertNotNull(startWithConfigsResult)
                platform.AbilityKit.ChildProcess.OH_Ability_DestroyChildProcessConfigs(configs)
            } catch (e: Throwable) {
                logLine("OH_Ability_StartNativeChildProcessWithConfigs (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Ability_GetCurrentChildProcessArgs() {
        logLine("--- OH_Ability_GetCurrentChildProcessArgs ---")
        try {
            val currentArgs = platform.AbilityKit.ChildProcess.OH_Ability_GetCurrentChildProcessArgs()
            logLine("OH_Ability_GetCurrentChildProcessArgs() result: $currentArgs (API 17)")
            assertNotNull(currentArgs)
        } catch (e: Throwable) {
            logLine("OH_Ability_GetCurrentChildProcessArgs (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_RegisterNativeChildProcessExitCallback() {
        logLine("--- OH_Ability_RegisterNativeChildProcessExitCallback ---")
        try {
            val registerResult = platform.AbilityKit.ChildProcess.OH_Ability_RegisterNativeChildProcessExitCallback(null)
            logLine("OH_Ability_RegisterNativeChildProcessExitCallback(null) result: $registerResult (API 20)")
            assertNotNull(registerResult)
        } catch (e: Throwable) {
            logLine("OH_Ability_RegisterNativeChildProcessExitCallback (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_UnregisterNativeChildProcessExitCallback() {
        logLine("--- OH_Ability_UnregisterNativeChildProcessExitCallback ---")
        try {
            val unregisterResult = platform.AbilityKit.ChildProcess.OH_Ability_UnregisterNativeChildProcessExitCallback(null)
            logLine("OH_Ability_UnregisterNativeChildProcessExitCallback(null) result: $unregisterResult")
            assertNotNull(unregisterResult)
        } catch (e: Throwable) {
            logLine("OH_Ability_UnregisterNativeChildProcessExitCallback (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_Ability_KillChildProcess() {
        logLine("--- OH_Ability_KillChildProcess ---")
        try {
            val killResult = platform.AbilityKit.ChildProcess.OH_Ability_KillChildProcess(-1)
            logLine("OH_Ability_KillChildProcess(-1) result: $killResult (API 22)")
            assertNotNull(killResult)
        } catch (e: Throwable) {
            logLine("OH_Ability_KillChildProcess (API 22) exception: $e")
        }
    }
}
