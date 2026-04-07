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
import platform.NetworkBoostKit.NetworkBoost.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NetworkBoostTest {

    private fun logLine(msg: String) = println(msg)

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_NetworkBoost_SceneEvent() {
        assertEquals(NB_SCENE_EVENT_ENTER.toInt(), 0)
        assertEquals(NB_SCENE_EVENT_UPDATE.toInt(), 1)
        assertEquals(NB_SCENE_EVENT_LEAVE.toInt(), 2)
        logLine("NetworkBoost_SceneEvent passed")
    }

    @Test
    fun testEnum_NetworkBoost_RecommendedAction() {
        assertEquals(NB_ACTION_DO_CACHING.toInt(), 0)
        assertEquals(NB_ACTION_SUSPEND_DATA.toInt(), 1)
        assertEquals(NB_ACTION_DECREASE_DATA.toInt(), 2)
        assertEquals(NB_ACTION_INCREASE_DATA.toInt(), 3)
        assertEquals(NB_ACTION_KEEP_DATA.toInt(), 4)
        logLine("NetworkBoost_RecommendedAction passed")
    }

    @Test
    fun testEnum_NetworkBoost_PathType() {
        assertEquals(NB_PATH_CELLULAR_PRIMARY.toInt(), 0)
        assertEquals(NB_PATH_CELLULAR_SECONDARY.toInt(), 1)
        assertEquals(NB_PATH_WIFI_PRIMARY.toInt(), 2)
        assertEquals(NB_PATH_WIFI_SECONDARY.toInt(), 3)
        logLine("NetworkBoost_PathType passed")
    }

    @Test
    fun testEnum_NetworkBoost_Scene() {
        assertEquals(NB_SCENE_NORMAL.toInt(), 0)
        assertEquals(NB_SCENE_CONGESTION.toInt(), 1)
        assertEquals(NB_SCENE_FREQUENT_HANDOVER.toInt(), 2)
        assertEquals(NB_SCENE_WEAK_SIGNAL.toInt(), 3)
        logLine("NetworkBoost_Scene passed")
    }

    @Test
    fun testEnum_NetworkBoost_ServiceType() {
        assertEquals(NB_SERVICE_DEFAULT.toInt(), 0)
        assertEquals(NB_SERVICE_BACKGROUND.toInt(), 1)
        assertEquals(NB_SERVICE_REAL_TIME_VOICE.toInt(), 2)
        assertEquals(NB_SERVICE_REAL_TIME_VIDEO.toInt(), 3)
        assertEquals(NB_SERVICE_CALL_SIGNALING.toInt(), 4)
        assertEquals(NB_SERVICE_REAL_TIME_GAME.toInt(), 5)
        assertEquals(NB_SERVICE_NORMAL_GAME.toInt(), 6)
        assertEquals(NB_SERVICE_SHORT_VIDEO.toInt(), 7)
        assertEquals(NB_SERVICE_LONG_VIDEO.toInt(), 8)
        assertEquals(NB_SERVICE_LIVE_STREAMING_ANCHOR.toInt(), 9)
        assertEquals(NB_SERVICE_LIVE_STREAMING_WATCHER.toInt(), 10)
        assertEquals(NB_SERVICE_DOWNLOAD.toInt(), 11)
        assertEquals(NB_SERVICE_UPLOAD.toInt(), 12)
        assertEquals(NB_SERVICE_BROWSER.toInt(), 13)
        assertEquals(NB_SERVICE_TRANSACTION.toInt(), 14)
        assertEquals(NB_SERVICE_DETECTION.toInt(), 15)
        assertEquals(NB_SERVICE_CLOUDSERVICE.toInt(), 16)
        assertEquals(NB_SERVICE_VOICE_CONFERENCE.toInt(), 17)
        assertEquals(NB_SERVICE_VIDEO_CONFERENCE.toInt(), 18)
        assertEquals(NB_SERVICE_NAVIGATION.toInt(), 19)
        assertEquals(NB_SERVICE_SECKILL_SERVICE.toInt(), 20)
        assertEquals(NB_SERVICE_LOGIN.toInt(), 21)
        assertEquals(NB_SERVICE_AUDIO.toInt(), 22)
        assertEquals(NB_SERVICE_SHOPPING.toInt(), 23)
        logLine("NetworkBoost_ServiceType passed")
    }

    @Test
    fun testEnum_NetworkBoost_QoeType() {
        assertEquals(NB_QOE_GOOD.toInt(), 0)
        assertEquals(NB_QOE_BAD_UNKNOWN.toInt(), 1)
        assertEquals(NB_QOE_BAD_SERVER_ERROR.toInt(), 2)
        assertEquals(NB_QOE_BAD_NO_DATA.toInt(), 3)
        assertEquals(NB_QOE_BAD_PACKET_LOST.toInt(), 4)
        assertEquals(NB_QOE_BAD_PACKET_OUT_OF_ORDER.toInt(), 5)
        assertEquals(NB_QOE_BAD_HIGH_JITTER.toInt(), 6)
        assertEquals(NB_QOE_BAD_HIGH_LATENCY.toInt(), 7)
        logLine("NetworkBoost_QoeType passed")
    }

    @Test
    fun testEnum_NetworkBoost_DataSpeedSimpleAction() {
        assertEquals(NB_SIMPLEACTION_SUSPEND_DATA.toInt(), 1)
        assertEquals(NB_SIMPLEACTION_DECREASE_DATA.toInt(), 2)
        assertEquals(NB_SIMPLEACTION_INCREASE_DATA.toInt(), 3)
        assertEquals(NB_SIMPLEACTION_KEEP_DATA.toInt(), 4)
        logLine("NetworkBoost_DataSpeedSimpleAction passed")
    }

    @Test
    fun testEnum_NetworkBoost_ErrorResult() {
        assertEquals(NB_ERROR_NONE.toInt(), 0)
        assertEquals(NB_ERROR_HANDOVER_TIMEOUT.toInt(), 1)
        assertEquals(NB_ERROR_NEW_PATH_ACTIVATION_FAILED.toInt(), 2)
        assertEquals(NB_ERROR_ABORT.toInt(), 3)
        logLine("NetworkBoost_ErrorResult passed")
    }

    @Test
    fun testEnum_NetworkBoost_ReEstAction() {
        assertEquals(NB_REEST_DEFAULT.toInt(), 0)
        assertEquals(NB_REEST_QUERY_DNS.toInt(), 1)
        assertEquals(NB_REEST_CHANGE_REMOTE_IP.toInt(), 2)
        assertEquals(NB_REEST_CHANGE_IP_VERSION.toInt(), 3)
        assertEquals(NB_NO_EST.toInt(), 4)
        logLine("NetworkBoost_ReEstAction passed")
    }

    @Test
    fun testEnum_NetworkBoost_HandoverMode() {
        assertEquals(NB_MODE_DELEGATION.toInt(), 0)
        assertEquals(NB_MODE_DISCRETION.toInt(), 1)
        logLine("NetworkBoost_HandoverMode passed")
    }

    @Test
    fun testEnum_NetworkBoost_PathState() {
        assertEquals(NB_PATH_IDLE.toInt(), 0)
        assertEquals(NB_PATH_CONNECTED.toInt(), 1)
        assertEquals(NB_PATH_SUSPENDED.toInt(), 2)
        logLine("NetworkBoost_PathState passed")
    }

    @Test
    fun testEnum_NetworkBoost_MultiPathErrorResult() {
        assertEquals(NB_MULTIPATH_ERROR_NONE.toInt(), 0)
        assertEquals(NB_MULTIPATH_ERROR_NETWORK_REFUSED.toInt(), 1)
        assertEquals(NB_MULTIPATH_ERROR_TIMEOUT.toInt(), 2)
        assertEquals(NB_MULTIPATH_ERROR_LOCAL.toInt(), 3)
        logLine("NetworkBoost_MultiPathErrorResult passed")
    }

    @Test
    fun testEnum_NetworkBoost_MultiPathChangeCause() {
        assertEquals(NB_MULTIPATH_CAUSE_REQUEST_NORMAL.toInt(), 0)
        assertEquals(NB_MULTIPATH_CAUSE_RELEASE_NORMAL.toInt(), 50)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_RELEASE_NETWORK.toInt(), 51)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_RELEASE_USER_REFUSED.toInt(), 52)
        assertEquals(NB_MULTIPATH_CAUSE_RELEASE_NO_QUOTA.toInt(), 53)
        assertEquals(NB_MULTIPATH_CAUSE_RELEASE_POWER_CONSUMPTION.toInt(), 54)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_RELEASE_INSUFFICIENT_TRAFFIC.toInt(), 55)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_RELEASE_CONFLICT.toInt(), 56)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_RELEASE_SYS_FUSING.toInt(), 57)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_RELEASE_SYS_DEFAULT.toInt(), 99)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_SUSPEND_ENTER.toInt(), 100)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_SUSPEND_LEAVE.toInt(), 101)
        assertEquals(NB_MULTIPATH_CHANGE_CAUSE_CONN_PROPERTIES_UPDATE.toInt(), 102)
        logLine("NetworkBoost_MultiPathChangeCause passed")
    }

    @Test
    fun testEnum_NetworkBoost_MultiPathState() {
        assertEquals(NB_MULTIPATH_IDLE.toInt(), 0)
        assertEquals(NB_MULTIPATH_CREATING.toInt(), 1)
        assertEquals(NB_MULTIPATH_CREATED.toInt(), 2)
        assertEquals(NB_MULTIPATH_RELEASING.toInt(), 3)
        logLine("NetworkBoost_MultiPathState passed")
    }

    @Test
    fun testEnum_NetworkBoost_MultiPathAction() {
        assertEquals(NB_MULTIPATH_ACTION_REQUEST.toInt(), 0)
        assertEquals(NB_MULTIPATH_ACTION_RELEASE.toInt(), 1)
        logLine("NetworkBoost_MultiPathAction passed")
    }

    @Test
    fun testHMS_NetworkBoost_SetSceneDesc() { memScoped {
        val desc = alloc<NetworkBoost_SceneDesc>().apply {
            scene = NB_SERVICE_DEFAULT
            sceneEvent = NB_SCENE_EVENT_ENTER
            startTime = 0u
            duration = 0u
        }
        val ret = try { HMS_NetworkBoost_SetSceneDesc(desc.readValue()) } catch (e: Throwable) { logLine("HMS_NetworkBoost_SetSceneDesc (API 22) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("HMS_NetworkBoost_SetSceneDesc=$ret")
    } }

    @Test
    fun testHMS_NetworkBoost_RegisterUnregisterNetQosCallback() { memScoped {
        val callbackId = alloc<UIntVar>()
        val ret = try { HMS_NetworkBoost_RegisterNetQosCallback(null, callbackId.ptr) } catch (e: Throwable) { logLine("HMS_NetworkBoost_RegisterNetQosCallback (API 18) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("HMS_NetworkBoost_RegisterNetQosCallback=$ret")
        val unregRet = try { HMS_NetworkBoost_UnregisterNetQosCallback(0u) } catch (e: Throwable) { logLine("HMS_NetworkBoost_UnregisterNetQosCallback (API 18) exception: $e"); -1 }
        assertNotNull(unregRet)
        logLine("HMS_NetworkBoost_UnregisterNetQosCallback=$unregRet")
    } }

    @Test
    fun testHMS_NetworkBoost_RegisterUnregisterNetSceneCallback() { memScoped {
        val callbackId = alloc<UIntVar>()
        val ret = try { HMS_NetworkBoost_RegisterNetSceneCallback(null, callbackId.ptr) } catch (e: Throwable) { logLine("HMS_NetworkBoost_RegisterNetSceneCallback (API 18) exception: $e"); -1 }
        assertNotNull(ret)
        val unregRet = try { HMS_NetworkBoost_UnregisterNetSceneCallback(0u) } catch (e: Throwable) { logLine("HMS_NetworkBoost_UnregisterNetSceneCallback (API 18) exception: $e"); -1 }
        assertNotNull(unregRet)
        logLine("Register/UnregisterNetSceneCallback=$ret,$unregRet")
    } }

    @Test
    fun testHMS_NetworkBoost_ReportQoe() {
        val ret = try { HMS_NetworkBoost_ReportQoe(NB_SERVICE_DEFAULT, NB_QOE_GOOD) } catch (e: Throwable) { logLine("HMS_NetworkBoost_ReportQoe (API 18) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("HMS_NetworkBoost_ReportQoe=$ret")
    }

    @Test
    fun testHMS_NetworkBoost_GetMultiPathQuotaStats() { memScoped {
        val quota = alloc<NetworkBoost_MultiPathQuota>()
        val ret = try { HMS_NetworkBoost_GetMultiPathQuotaStats(quota.ptr) } catch (e: Throwable) { logLine("HMS_NetworkBoost_GetMultiPathQuotaStats (API 22) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("HMS_NetworkBoost_GetMultiPathQuotaStats=$ret")
    } }

    @Test
    fun testHMS_NetworkBoost_RequestMultiPathAndReleaseMultiPath() { memScoped {
        val ret = try { HMS_NetworkBoost_RequestMultiPath(null) } catch (e: Throwable) { logLine("HMS_NetworkBoost_RequestMultiPath (API 22) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("HMS_NetworkBoost_RequestMultiPath=$ret")
        val releaseRet = try { HMS_NetworkBoost_ReleaseMultiPath() } catch (e: Throwable) { logLine("HMS_NetworkBoost_ReleaseMultiPath (API 22) exception: $e"); -1 }
        assertNotNull(releaseRet)
        logLine("HMS_NetworkBoost_ReleaseMultiPath=$releaseRet")
    } }

    @Test
    fun testHMS_NetworkBoost_RegisterUnregisterMultiPathCallbacks() { memScoped {
        val id = alloc<UIntVar>()
        val ret = try { HMS_NetworkBoost_RegisterMultiPathStateChangeCallback(null, id.ptr) } catch (e: Throwable) { logLine("HMS_NetworkBoost_RegisterMultiPathStateChangeCallback (API 22) exception: $e"); -1 }
        assertNotNull(ret)
        val unregRet = try { HMS_NetworkBoost_UnregisterMultiPathStateChangeCallback(0u) } catch (e: Throwable) { logLine("HMS_NetworkBoost_UnregisterMultiPathStateChangeCallback (API 22) exception: $e"); -1 }
        assertNotNull(unregRet)
        val id2 = alloc<UIntVar>()
        val ret2 = try { HMS_NetworkBoost_RegisterMultiPathRecommendationCallback(null, id2.ptr) } catch (e: Throwable) { logLine("HMS_NetworkBoost_RegisterMultiPathRecommendationCallback (API 22) exception: $e"); -1 }
        assertNotNull(ret2)
        val unreg2 = try { HMS_NetworkBoost_UnregisterMultiPathRecommendationCallback(0u) } catch (e: Throwable) { logLine("HMS_NetworkBoost_UnregisterMultiPathRecommendationCallback (API 22) exception: $e"); -1 }
        assertNotNull(unreg2)
        logLine("MultiPath callbacks=$ret,$unregRet,$ret2,$unreg2")
    } }

    @Test
    fun testHMS_NetworkBoost_RegisterUnregisterHandoverAndSetHandoverMode() { memScoped {
        val id = alloc<UIntVar>()
        val ret = try { HMS_NetworkBoost_RegisterHandoverChangeCallback(null, id.ptr) } catch (e: Throwable) { logLine("HMS_NetworkBoost_RegisterHandoverChangeCallback (API 18) exception: $e"); -1 }
        assertNotNull(ret)
        val unregRet = try { HMS_NetworkBoost_UnregisterHandoverChangeCallback(0u) } catch (e: Throwable) { logLine("HMS_NetworkBoost_UnregisterHandoverChangeCallback (API 18) exception: $e"); -1 }
        assertNotNull(unregRet)
        val modeRet = try { HMS_NetworkBoost_SetHandoverMode(NB_MODE_DELEGATION) } catch (e: Throwable) { logLine("HMS_NetworkBoost_SetHandoverMode (API 18) exception: $e"); -1 }
        assertNotNull(modeRet)
        logLine("Handover=$ret,$unregRet,$modeRet")
    } }
}
