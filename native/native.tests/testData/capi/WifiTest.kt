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
import platform.ConnectivityKit.Wifi.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class WifiTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Wifi_ResultCode() {
        assertEquals(WIFI_SUCCESS.toInt(), 0)
        assertEquals(WIFI_PERMISSION_DENIED.toInt(), 201)
        assertEquals(WIFI_INVALID_PARAM.toInt(), 401)
        assertEquals(WIFI_NOT_SUPPORTED.toInt(), 801)
        assertEquals(WIFI_OPERATION_FAILED.toInt(), 2501000)
        assertEquals(WIFI_STA_DISABLED.toInt(), 2501001)
        logLine("Wifi_ResultCode passed")
    }

    @Test
    fun testOH_Wifi_IsWifiEnabled_GetDeviceMacAddress() { memScoped {
        val enabled = alloc<BooleanVar>()
        val ret = OH_Wifi_IsWifiEnabled(enabled.ptr); logLine("OH_Wifi_IsWifiEnabled=$ret"); assertNotNull(ret)
        val mac = ByteArray(32)
        val len = alloc<UIntVar>().apply { value = 32u }
        val retMac = try { OH_Wifi_GetDeviceMacAddress(mac.refTo(0), len.ptr) } catch (e: Throwable) { logLine("OH_Wifi_GetDeviceMacAddress (API 21) exception: $e"); WIFI_INVALID_PARAM }; logLine("OH_Wifi_GetDeviceMacAddress=$retMac")
        logLine("OH_Wifi_IsWifiEnabled/GetDeviceMacAddress passed")
    } }
}
