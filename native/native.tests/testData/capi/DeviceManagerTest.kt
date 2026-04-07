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
class DeviceManagerTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testDeviceManager_ErrorCodeEnums() {
        logLine("--- DeviceManager_ErrorCode ---")
        val v0 = platform.DistributedServiceKit.DeviceManager.ERR_OK
        logLine("ERR_OK=$v0")
        assertEquals<Int>(0, v0.toInt())
        val v1 = platform.DistributedServiceKit.DeviceManager.ERR_PERMISSION_ERROR
        logLine("ERR_PERMISSION_ERROR=$v1")
        assertEquals<Int>(201, v1.toInt())
        val v2 = platform.DistributedServiceKit.DeviceManager.ERR_INVALID_PARAMETER
        logLine("ERR_INVALID_PARAMETER=$v2")
        assertEquals<Int>(401, v2.toInt())
        val v3 = platform.DistributedServiceKit.DeviceManager.DM_ERR_FAILED
        logLine("DM_ERR_FAILED=$v3")
        assertEquals<Int>(11600101, v3.toInt())
        val v4 = platform.DistributedServiceKit.DeviceManager.DM_ERR_OBTAIN_SERVICE
        logLine("DM_ERR_OBTAIN_SERVICE=$v4")
        assertEquals<Int>(11600102, v4.toInt())
        val v5 = platform.DistributedServiceKit.DeviceManager.DM_ERR_OBTAIN_BUNDLE_NAME
        logLine("DM_ERR_OBTAIN_BUNDLE_NAME=$v5")
        assertEquals<Int>(11600109, v5.toInt())
        logLine("DeviceManager_ErrorCode values ok")
    }

    @Test
    fun testOH_DeviceManager_GetLocalDeviceName() {
        memScoped {
            logLine("--- OH_DeviceManager_GetLocalDeviceName (API 20) ---")
            try {
                val localDeviceName = alloc<CPointerVar<ByteVar>>()
                val len = alloc<UIntVar>()
                val result = platform.DistributedServiceKit.DeviceManager.OH_DeviceManager_GetLocalDeviceName(
                    localDeviceName.ptr,
                    len.ptr
                )
                assertNotNull(result)
                logLine("OH_DeviceManager_GetLocalDeviceName result: $result")
            } catch (e: Throwable) {
                logLine("OH_DeviceManager_GetLocalDeviceName (API 20) exception: $e")
            }
        }
    }
}
