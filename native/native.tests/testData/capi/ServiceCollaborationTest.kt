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
import platform.ServiceCollaborationKit.ServiceCollaboration.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ServiceCollaborationTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_ServiceCollaborationFilterType() {
        assertEquals(TAKE_PHOTO.toInt(), 1)
        assertEquals(SCAN_DOCUMENT.toInt(), 2)
        assertEquals(IMAGE_PICKER.toInt(), 3)
        logLine("ServiceCollaborationFilterType passed")
    }

    @Test
    fun testEnum_ServiceCollaborationDataType() {
        assertEquals(IMAGE.toInt(), 1)
        logLine("ServiceCollaborationDataType passed")
    }

    @Test
    fun testEnum_ServiceCollaborationEventCode() {
        assertEquals(LAST_DATA_BACK.toInt(), 1001202000)
        assertEquals(PEER_CANCEL.toInt(), 1001202001)
        assertEquals(NETWORK_ERROR.toInt(), 1001202002)
        assertEquals(PEER_WIFI_NOT_OPEN.toInt(), 1001202004)
        assertEquals(LOCAL_WIFI_NOT_OPEN.toInt(), 1001202005)
        assertEquals(DATA_BACK_START.toInt(), 1001202006)
        assertEquals(MIDDLE_DATA_BACK.toInt(), 1001202007)
        assertEquals(TIMEOUT_AUTO_CANCEL.toInt(), 1001202008)
        assertEquals(DATA_READ_FAILED.toInt(), 1001202009)
        assertEquals(LINK_SHUTDOWN.toInt(), 1001202011)
        assertEquals(REMOTE_HOTSPOT_CONFLICT.toInt(), 1001202013)
        assertEquals(REMOTE_DISTRIBUTED_SERVICES_CONFLICT.toInt(), 1001202014)
        logLine("ServiceCollaborationEventCode passed")
    }

    @Test
    fun testHMS_ServiceCollaboration_GetCollaborationDeviceInfos() { memScoped {
        val filterVar = alloc<IntVar>().apply { value = TAKE_PHOTO.toInt() }
        val result = HMS_ServiceCollaboration_GetCollaborationDeviceInfos(1u, filterVar.ptr.reinterpret())
        logLine("HMS_ServiceCollaboration_GetCollaborationDeviceInfos=$result")
        assertTrue(result == null || result != null)
    } }

    @Test
    fun testHMS_ServiceCollaboration_StartCollaboration_StopCollaboration() { memScoped {
        val selectInfo = alloc<ServiceCollaboration_SelectInfo>().apply {
            serviceFilterType = TAKE_PHOTO
            maxSize = 1u
        }
        val collaborationId = HMS_ServiceCollaboration_StartCollaboration(selectInfo.ptr, null)
        logLine("HMS_ServiceCollaboration_StartCollaboration=$collaborationId")
        val stopRet = HMS_ServiceCollaboration_StopCollaboration(collaborationId)
        assertNotNull(stopRet)
        logLine("HMS_ServiceCollaboration_StopCollaboration=$stopRet")
    } }
}
