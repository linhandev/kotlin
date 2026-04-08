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
class DeviceSecurityModeTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testDSM_DeviceSecurityModeEnums() {
        logLine("--- DSM_DeviceSecurityMode ---")
        val v0 = platform.DeviceSecurityKit.DeviceSecurityMode.DSM_NORMAL_MODE
        logLine("DSM_NORMAL_MODE=$v0")
        assertEquals<Int>(0, v0.toInt())
        val v1 = platform.DeviceSecurityKit.DeviceSecurityMode.DSM_SECURE_SHIELD_MODE
        logLine("DSM_SECURE_SHIELD_MODE=$v1")
        assertEquals<Int>(1, v1.toInt())
        logLine("DSM_DeviceSecurityMode values ok")
    }

    @Test
    fun testHMS_DSM_GetDeviceSecurityMode() {
        logLine("--- HMS_DSM_GetDeviceSecurityMode ---")
        val result = platform.DeviceSecurityKit.DeviceSecurityMode.HMS_DSM_GetDeviceSecurityMode()
        assertNotNull(result)
        logLine("HMS_DSM_GetDeviceSecurityMode result: $result")
    }
}
