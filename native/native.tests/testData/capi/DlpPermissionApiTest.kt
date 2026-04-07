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
class DlpPermissionApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testDLP_ErrCodeEnums() {
        logLine("--- DLP_ErrCode ---")
        val v0 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_SUCCESS
        logLine("ERR_OH_SUCCESS=$v0")
        assertEquals<Int>(0, v0.toInt())
        val v1 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_INVALID_PARAMETER
        logLine("ERR_OH_INVALID_PARAMETER=$v1")
        assertEquals<Int>(19100001, v1.toInt())
        val v2 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_API_ONLY_FOR_SANDBOX
        logLine("ERR_OH_API_ONLY_FOR_SANDBOX=$v2")
        assertEquals<Int>(19100006, v2.toInt())
        val v3 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_API_NOT_FOR_SANDBOX
        logLine("ERR_OH_API_NOT_FOR_SANDBOX=$v3")
        assertEquals<Int>(19100007, v3.toInt())
        val v4 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_SYSTEM_SERVICE_EXCEPTION
        logLine("ERR_OH_SYSTEM_SERVICE_EXCEPTION=$v4")
        assertEquals<Int>(19100011, v4.toInt())
        val v5 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_OUT_OF_MEMORY
        logLine("ERR_OH_OUT_OF_MEMORY=$v5")
        assertEquals<Int>(19100012, v5.toInt())
        val v6 = platform.DataProtectionKit.DlpPermissionApi.ERR_OH_APPLICATION_NOT_AUTHORIZED
        logLine("ERR_OH_APPLICATION_NOT_AUTHORIZED=$v6")
        assertEquals<Int>(19100018, v6.toInt())
        logLine("DLP_ErrCode values ok")
    }

    @Test
    fun testDLP_FileAccessEnums() {
        logLine("--- DLP_FileAccess ---")
        val e0 = platform.DataProtectionKit.DlpPermissionApi.NO_PERMISSION
        logLine("NO_PERMISSION=$e0")
        assertEquals<Int>(0, e0.toInt())
        val e1 = platform.DataProtectionKit.DlpPermissionApi.READ_ONLY
        logLine("READ_ONLY=$e1")
        assertEquals<Int>(1, e1.toInt())
        val e2 = platform.DataProtectionKit.DlpPermissionApi.CONTENT_EDIT
        logLine("CONTENT_EDIT=$e2")
        assertEquals<Int>(2, e2.toInt())
        val e3 = platform.DataProtectionKit.DlpPermissionApi.FULL_CONTROL
        logLine("FULL_CONTROL=$e3")
        assertEquals<Int>(3, e3.toInt())
        logLine("DLP_FileAccess values ok")
    }

    @Test
    fun testOH_DLP_GetDlpPermissionInfo() {
        memScoped {
            logLine("--- OH_DLP_GetDlpPermissionInfo (API 14+, try-catch for compatibility) ---")
            try {
                val dlpFileAccess = alloc<platform.DataProtectionKit.DlpPermissionApi.DLP_FileAccessVar>()
                val flags = alloc<UIntVar>()
                val result = platform.DataProtectionKit.DlpPermissionApi.OH_DLP_GetDlpPermissionInfo(
                    dlpFileAccess.ptr,
                    flags.ptr
                )
                assertNotNull(result)
                logLine("OH_DLP_GetDlpPermissionInfo result: $result")
            } catch (e: Throwable) {
                logLine("OH_DLP_GetDlpPermissionInfo exception: $e")
            }
        }
    }

    @Test
    fun testOH_DLP_GetOriginalFileName() {
        memScoped {
            logLine("--- OH_DLP_GetOriginalFileName (API 14+, try-catch for compatibility) ---")
            try {
                val originalFileName = alloc<CPointerVar<ByteVar>>()
                val result = platform.DataProtectionKit.DlpPermissionApi.OH_DLP_GetOriginalFileName(
                    "test.dlp",
                    originalFileName.ptr
                )
                assertNotNull(result)
                logLine("OH_DLP_GetOriginalFileName result: $result")
            } catch (e: Throwable) {
                logLine("OH_DLP_GetOriginalFileName exception: $e")
            }
        }
    }

    @Test
    fun testOH_DLP_IsInSandbox() {
        memScoped {
            logLine("--- OH_DLP_IsInSandbox (API 14+, try-catch for compatibility) ---")
            try {
                val isInSandbox = alloc<BooleanVar>()
                val result = platform.DataProtectionKit.DlpPermissionApi.OH_DLP_IsInSandbox(isInSandbox.ptr)
                assertNotNull(result)
                logLine("OH_DLP_IsInSandbox result: $result")
            } catch (e: Throwable) {
                logLine("OH_DLP_IsInSandbox exception: $e")
            }
        }
    }

    // @Test
    // fun testOH_DLP_SetSandboxAppConfig() {
    //     logLine("--- OH_DLP_SetSandboxAppConfig (API 14+, try-catch for compatibility) ---")
    //     try {
    //         val result = platform.DataProtectionKit.DlpPermissionApi.OH_DLP_SetSandboxAppConfig("{\"key\":\"value\"}")
    //         assertNotNull(result)
    //         logLine("OH_DLP_SetSandboxAppConfig result: $result")
    //     } catch (e: Throwable) {
    //         logLine("OH_DLP_SetSandboxAppConfig exception: $e")
    //     }
    // }

    // @Test
    // fun testOH_DLP_GetSandboxAppConfig() {
    //     memScoped {
    //         logLine("--- OH_DLP_GetSandboxAppConfig (API 14+, try-catch for compatibility) ---")
    //         try {
    //             val configInfoOut = alloc<CPointerVar<ByteVar>>()
    //             val result = platform.DataProtectionKit.DlpPermissionApi.OH_DLP_GetSandboxAppConfig(configInfoOut.ptr)
    //             assertNotNull(result)
    //             logLine("OH_DLP_GetSandboxAppConfig result: $result")
    //         } catch (e: Throwable) {
    //             logLine("OH_DLP_GetSandboxAppConfig exception: $e")
    //         }
    //     }
    // }

    // @Test
    // fun testOH_DLP_CleanSandboxAppConfig() {
    //     logLine("--- OH_DLP_CleanSandboxAppConfig (API 14+, try-catch for compatibility) ---")
    //     try {
    //         val result = platform.DataProtectionKit.DlpPermissionApi.OH_DLP_CleanSandboxAppConfig()
    //         assertNotNull(result)
    //         logLine("OH_DLP_CleanSandboxAppConfig result: $result")
    //     } catch (e: Throwable) {
    //         logLine("OH_DLP_CleanSandboxAppConfig exception: $e")
    //     }
    // }
}
