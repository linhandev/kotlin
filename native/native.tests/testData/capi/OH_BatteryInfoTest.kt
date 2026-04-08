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
import platform.BasicServicesKit.OH_BatteryInfo.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_BatteryInfoTest {

    private fun logLine(msg: String) = println(msg)

    // ==================== 枚举 ====================
    @Test
    fun testEnum_BatteryInfo_BatteryPluggedType() {
        assertEquals(BatteryInfo_BatteryPluggedType.PLUGGED_TYPE_NONE.value.toInt(), 0)
        assertEquals(BatteryInfo_BatteryPluggedType.PLUGGED_TYPE_AC.value.toInt(), 1)
        assertEquals(BatteryInfo_BatteryPluggedType.PLUGGED_TYPE_USB.value.toInt(), 2)
        assertEquals(BatteryInfo_BatteryPluggedType.PLUGGED_TYPE_WIRELESS.value.toInt(), 3)
        assertEquals(BatteryInfo_BatteryPluggedType.PLUGGED_TYPE_BUTT.value.toInt(), 4)
        logLine("BatteryInfo_BatteryPluggedType passed")
    }

    @Test
    fun testOH_BatteryInfo_GetCapacity() {
        val cap = OH_BatteryInfo_GetCapacity()
        assertNotNull(cap)
        logLine("OH_BatteryInfo_GetCapacity=$cap")
    }

    @Test
    fun testOH_BatteryInfo_GetPluggedType() {
        val type = OH_BatteryInfo_GetPluggedType()
        assertNotNull(type)
        logLine("OH_BatteryInfo_GetPluggedType=$type")
    }
}
