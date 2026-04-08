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
import platform.LocationKit.Location.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class LocationTest {

    private fun logLine(msg: String) = println("[stdout] LocationTest $msg")

    @Test
    fun testEnum_Location_ResultCode() {
        assertEquals(LOCATION_SUCCESS.toInt(), 0)
        assertEquals(LOCATION_PERMISSION_DENIED.toInt(), 201)
        assertEquals(LOCATION_INVALID_PARAM.toInt(), 401)
        assertEquals(LOCATION_NOT_SUPPORTED.toInt(), 801)
        assertEquals(LOCATION_SERVICE_UNAVAILABLE.toInt(), 3301000)
        assertEquals(LOCATION_SWITCH_OFF.toInt(), 3301100)
        logLine("Location_ResultCode passed")
    }

    @Test
    fun testEnum_Location_UseScene() {
        assertEquals(LOCATION_USE_SCENE_NAVIGATION.toInt(), 0x0401)
        assertEquals(LOCATION_USE_SCENE_SPORT.toInt(), 0x0402)
        assertEquals(LOCATION_USE_SCENE_TRANSPORT.toInt(), 0x0403)
        assertEquals(LOCATION_USE_SCENE_DAILY_LIFE_SERVICE.toInt(), 0x0404)
        logLine("Location_UseScene passed")
    }

    @Test
    fun testEnum_Location_PowerConsumptionScene() {
        assertEquals(LOCATION_HIGH_POWER_CONSUMPTION.toInt(), 0x0601)
        assertEquals(LOCATION_LOW_POWER_CONSUMPTION.toInt(), 0x0602)
        assertEquals(LOCATION_NO_POWER_CONSUMPTION.toInt(), 0x0603)
        logLine("Location_PowerConsumptionScene passed")
    }

    @Test
    fun testEnum_Location_SourceType() {
        assertEquals(LOCATION_SOURCE_TYPE_GNSS.toInt(), 1)
        assertEquals(LOCATION_SOURCE_TYPE_NETWORK.toInt(), 2)
        assertEquals(LOCATION_SOURCE_TYPE_INDOOR.toInt(), 3)
        assertEquals(LOCATION_SOURCE_TYPE_RTK.toInt(), 4)
        logLine("Location_SourceType passed")
    }

    // ==================== RequestConfig ====================

    @Test
    fun testOH_Location_CreateRequestConfig() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            logLine("OH_Location_CreateRequestConfig=$config")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_Location_DestroyRequestConfig() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            OH_Location_DestroyRequestConfig(config)
            logLine("OH_Location_DestroyRequestConfig=called")
        }
    }

    @Test
    fun testOH_LocationRequestConfig_SetUseScene() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            OH_LocationRequestConfig_SetUseScene(config, LOCATION_USE_SCENE_NAVIGATION)
            logLine("OH_LocationRequestConfig_SetUseScene=called")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_LocationRequestConfig_SetPowerConsumptionScene() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            OH_LocationRequestConfig_SetPowerConsumptionScene(config, LOCATION_LOW_POWER_CONSUMPTION)
            logLine("OH_LocationRequestConfig_SetPowerConsumptionScene=called")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_LocationRequestConfig_SetInterval() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            OH_LocationRequestConfig_SetInterval(config, 1)
            logLine("OH_LocationRequestConfig_SetInterval=called")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_LocationRequestConfig_SetCallback() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            OH_LocationRequestConfig_SetCallback(config, null, null)
            logLine("OH_LocationRequestConfig_SetCallback=called")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_Location_IsLocatingEnabled() {
        memScoped {
            val enabled = alloc<BooleanVar>()
            val rc = OH_Location_IsLocatingEnabled(enabled.ptr)
            assertNotNull(rc)
            logLine("OH_Location_IsLocatingEnabled=$rc")
        }
    }

    @Test
    fun testOH_Location_StartLocating() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            val rc = OH_Location_StartLocating(config)
            assertNotNull(rc)
            logLine("OH_Location_StartLocating=$rc")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_Location_StopLocating() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            OH_Location_StartLocating(config)
            val rc = OH_Location_StopLocating(config)
            assertNotNull(rc)
            logLine("OH_Location_StopLocating=$rc")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    @Test
    fun testOH_LocationInfo_GetAdditionalInfo() {
        memScoped {
            val additionalInfo = allocArray<ByteVar>(256)
            val rc = OH_LocationInfo_GetAdditionalInfo(null, additionalInfo, 256u)
            assertNotNull(rc)
            logLine("OH_LocationInfo_GetAdditionalInfo=$rc")
        }
    }

    @Test
    fun testOH_LocationInfo_GetBasicInfo() {
        memScoped {
            val basicInfo = OH_LocationInfo_GetBasicInfo(null)
            assertNotNull(basicInfo)
            logLine("OH_LocationInfo_GetBasicInfo latitude=${basicInfo.useContents { latitude }}")
        }
    }
}
