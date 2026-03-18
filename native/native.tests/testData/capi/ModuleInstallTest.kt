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
import platform.AppGalleryKit.ModuleInstall.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ModuleInstallTest {

    private fun logLine(msg: String) = println("[stdout] ModuleInstallTest $msg")

    @Test
    fun testEnum_ModuleInstall_ErrCode() {
        assertEquals(E_NO_ERROR.toInt(), 0)
        assertEquals(E_PARAMS.toInt(), 401)
        assertEquals(E_QUERY_MODULE.toInt(), 1006500001)
        assertEquals(E_REPEATED_CALL.toInt(), 1006500002)
        assertEquals(E_CONNECT_SA.toInt(), 1006500004)
        assertEquals(E_OFF_WITHOUT_ON.toInt(), 1006500006)
        assertEquals(E_CONNECT_SERVICE_EXTENSION.toInt(), 1006500007)
        assertEquals(E_WRITE_PARAM.toInt(), 1006500008)
        assertEquals(E_REQUEST_SERVER.toInt(), 1006500009)
        assertEquals(E_RESPONSE_INVALID.toInt(), 1006500010)
        assertEquals(E_INNER_ERROR.toInt(), 1006500011)
        logLine("testEnum_ModuleInstall_ErrCode passed")
    }

    @Test
    fun testEnum_ModuleInstall_InstallStatus() {
        assertEquals(INSTALLED.toInt(), 0)
        assertEquals(NOT_INSTALLED.toInt(), 1)
        logLine("testEnum_ModuleInstall_InstallStatus passed")
    }

    @Test
    fun testEnum_ModuleInstall_RequestCode() {
        assertEquals(MODULE_ALREADY_EXISTS.toInt(), -8)
        assertEquals(MODULE_UNAVAILABLE.toInt(), -7)
        assertEquals(INVALID_REQUEST.toInt(), -6)
        assertEquals(NETWORK_ERROR.toInt(), -5)
        assertEquals(INVOKER_VERIFICATION_FAILED.toInt(), -4)
        assertEquals(FOREGROUND_REQUIRED.toInt(), -3)
        assertEquals(ACTIVE_SESSION_LIMIT_EXCEEDED.toInt(), -2)
        assertEquals(FAILURE.toInt(), -1)
        assertEquals(SUCCESS.toInt(), 0)
        assertEquals(DOWNLOAD_WAIT_WIFI.toInt(), 1)
        logLine("testEnum_ModuleInstall_RequestCode passed")
    }

    @Test
    fun testEnum_ModuleInstall_TaskStatus() {
        assertEquals(CREATE_TASK_FAILED.toInt(), -4)
        assertEquals(HIGHER_VERSION_INSTALLED.toInt(), -3)
        assertEquals(TASK_ALREADY_EXISTS.toInt(), -2)
        assertEquals(TASK_UNFOUND.toInt(), -1)
        assertEquals(TASK_CREATED.toInt(), 0)
        assertEquals(DOWNLOADING.toInt(), 1)
        assertEquals(DOWNLOAD_PAUSED.toInt(), 2)
        assertEquals(DOWNLOAD_WAITING.toInt(), 3)
        assertEquals(DOWNLOAD_SUCCESSFUL.toInt(), 4)
        assertEquals(DOWNLOAD_FAILED.toInt(), 5)
        assertEquals(DOWNLOAD_WAIT_FOR_WIFI.toInt(), 6)
        assertEquals(INSTALL_WAITING.toInt(), 20)
        assertEquals(INSTALLING.toInt(), 21)
        assertEquals(INSTALL_SUCCESSFUL.toInt(), 22)
        assertEquals(INSTALL_FAILED.toInt(), 23)
        logLine("testEnum_ModuleInstall_TaskStatus passed")
    }

    @Test
    fun testHMS_ModuleInstall_GetInstalledModule() {
        memScoped {
            val installedModulePtr = alloc<CPointerVar<ModuleInstall_InstalledModule>>()
            installedModulePtr.value = null
            val rc = HMS_ModuleInstall_GetInstalledModule(null, 0u, installedModulePtr.ptr)
            assertNotNull(rc)
            logLine("HMS_ModuleInstall_GetInstalledModule=$rc")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetInstalledModuleName() {
        memScoped {
            val installedModulePtr = alloc<CPointerVar<ModuleInstall_InstalledModule>>()
            installedModulePtr.value = null
            HMS_ModuleInstall_GetInstalledModule(null, 0u, installedModulePtr.ptr)
            val mod = installedModulePtr.value
            val name = HMS_ModuleInstall_GetInstalledModuleName(mod)
            logLine("HMS_ModuleInstall_GetInstalledModuleName=$name")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetInstalledModuleType() {
        memScoped {
            val installedModulePtr = alloc<CPointerVar<ModuleInstall_InstalledModule>>()
            installedModulePtr.value = null
            HMS_ModuleInstall_GetInstalledModule(null, 0u, installedModulePtr.ptr)
            val mod = installedModulePtr.value
            val type = HMS_ModuleInstall_GetInstalledModuleType(mod)
            logLine("HMS_ModuleInstall_GetInstalledModuleType=$type")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetModuleInstallStatus() {
        memScoped {
            val installedModulePtr = alloc<CPointerVar<ModuleInstall_InstalledModule>>()
            installedModulePtr.value = null
            HMS_ModuleInstall_GetInstalledModule(null, 0u, installedModulePtr.ptr)
            val mod = installedModulePtr.value
            val status = HMS_ModuleInstall_GetModuleInstallStatus(mod)
            logLine("HMS_ModuleInstall_GetModuleInstallStatus=$status")
        }
    }

    @Test
    fun testHMS_ModuleInstall_FetchModules() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            val rc = HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            assertNotNull(rc)
            logLine("HMS_ModuleInstall_FetchModules=$rc")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModulesRequestCode() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val reqCode = HMS_ModuleInstall_GetFetchModulesRequestCode(result)
            logLine("HMS_ModuleInstall_GetFetchModulesRequestCode=$reqCode")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModulesTaskStatus() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val taskStatus = HMS_ModuleInstall_GetFetchModulesTaskStatus(result)
            logLine("HMS_ModuleInstall_GetFetchModulesTaskStatus=$taskStatus")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModulesTaskId() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val taskId = HMS_ModuleInstall_GetFetchModulesTaskId(result)
            logLine("HMS_ModuleInstall_GetFetchModulesTaskId=$taskId")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModulesDesc() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val desc = HMS_ModuleInstall_GetFetchModulesDesc(result)
            logLine("HMS_ModuleInstall_GetFetchModulesDesc=$desc")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModules() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val modules = HMS_ModuleInstall_GetFetchModules(result)
            logLine("HMS_ModuleInstall_GetFetchModules=$modules")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModulesTotalSize() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val totalSize = HMS_ModuleInstall_GetFetchModulesTotalSize(result)
            logLine("HMS_ModuleInstall_GetFetchModulesTotalSize=$totalSize")
        }
    }

    @Test
    fun testHMS_ModuleInstall_GetFetchModulesDownloadedSize() {
        memScoped {
            val fetchResultPtr = alloc<CPointerVar<ModuleInstall_FetchModulesResult>>()
            fetchResultPtr.value = null
            HMS_ModuleInstall_FetchModules(null, 0u, null, 0u, fetchResultPtr.ptr)
            val result = fetchResultPtr.value
            val downloadedSize = HMS_ModuleInstall_GetFetchModulesDownloadedSize(result)
            logLine("HMS_ModuleInstall_GetFetchModulesDownloadedSize=$downloadedSize")
        }
    }

    @Test
    fun testHMS_ModuleInstall_CancelTask() {
        val rc = HMS_ModuleInstall_CancelTask(null, 0u, 0u)
        assertNotNull(rc)
        logLine("HMS_ModuleInstall_CancelTask=$rc")
    }

    @Test
    fun testHMS_ModuleInstall_ShowCellularDataConfirmation() {
        val rc = HMS_ModuleInstall_ShowCellularDataConfirmation(null, 0u, 0u)
        assertNotNull(rc)
        logLine("HMS_ModuleInstall_ShowCellularDataConfirmation=$rc")
    }

    @Test
    fun testHMS_ModuleInstall_CreateStatusCallback() {
        memScoped {
            val callback = HMS_ModuleInstall_CreateStatusCallback(null)
            logLine("HMS_ModuleInstall_CreateStatusCallback=$callback")
            HMS_ModuleInstall_ReleaseStatusCallback(callback)
        }
    }

    @Test
    fun testHMS_ModuleInstall_ReleaseStatusCallback() {
        memScoped {
            val callback = HMS_ModuleInstall_CreateStatusCallback(null)
            HMS_ModuleInstall_ReleaseStatusCallback(callback)
            logLine("HMS_ModuleInstall_ReleaseStatusCallback=called")
        }
    }

    @Test
    fun testHMS_ModuleInstall_On() {
        memScoped {
            val rc = HMS_ModuleInstall_On(null, 0u, 0u, 0u, null)
            assertNotNull(rc)
            logLine("HMS_ModuleInstall_On=$rc")
        }
    }

    @Test
    fun testHMS_ModuleInstall_Off() {
        memScoped {
            val rc = HMS_ModuleInstall_Off(null, 0u, 0u)
            assertNotNull(rc)
            logLine("HMS_ModuleInstall_Off=$rc")
        }
    }
}
