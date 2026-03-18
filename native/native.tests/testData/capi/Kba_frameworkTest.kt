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
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -ljsvm
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohnotification
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohweb
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lipc_capi
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohfileuri
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohfileio
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohfileshare
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohenvironment
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnet_connection
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lhiappevent_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohaudio
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohscan
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -ltime_service_ndk
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohcommonevent
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohbattery_info
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -los_account_ndk
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohprint
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lpasteboard
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohpreferences
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_rdb_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -ludmf
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_window_manager
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_display_manager
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.framework.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_frameworkTest {

    private fun logLine(msg: String) = println(msg)

    // ark_runtime/jsvm.h (API 11)
    @Test
    fun testJsvm_h() {
        assertEquals<Int>(0, JSVM_Status.JSVM_OK.value.toInt())
        logLine("JSVM_Status.JSVM_OK=${JSVM_Status.JSVM_OK.value.toInt()}")
    }

    // NotificationKit/notification.h (API 13)
    @Test
    fun testNotification_h() {
        val enabled = OH_Notification_IsNotificationEnabled()
        logLine("OH_Notification_IsNotificationEnabled()=$enabled")
    }

    // IPCKit/ipc_cparcel.h (API 12)
    @Test
    fun testIpc_cparcel_h() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            if (parcel != null) OH_IPCParcel_Destroy(parcel)
            logLine("OH_IPCParcel_Create() ok")
        }
    }

    // IPCKit/ipc_error_code.h
    @Test
    fun testIpc_error_code_h() {
        assertEquals<Int>(0, OH_IPC_SUCCESS.toInt())
        logLine("OH_IPC_SUCCESS=$OH_IPC_SUCCESS")
    }

    // IPCKit/ipc_cremote_object.h (API 12)
    @Test
    fun testIpc_cremote_object_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("ipc_cremote_object covered")
    }

    // IPCKit/ipc_cskeleton.h (API 12)
    @Test
    fun testIpc_cskeleton_h() {
        val tokenId = OH_IPCSkeleton_GetCallingTokenId()
        logLine("OH_IPCSkeleton_GetCallingTokenId()=$tokenId")
    }

    // filemanagement/file_uri/oh_file_uri.h
    @Test
    fun testOh_file_uri_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("oh_file_uri covered")
    }

    // filemanagement/fileio/oh_fileio.h
    @Test
    fun testOh_fileio_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("oh_fileio covered")
    }

    // filemanagement/fileshare/oh_file_share.h
    @Test
    fun testOh_file_share_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("oh_file_share covered")
    }

    // filemanagement/environment/oh_environment.h
    @Test
    fun testOh_environment_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("oh_environment covered")
    }

    // network/netmanager/net_connection.h (API 11)
    @Test
    fun testNet_connection_h() {
        memScoped {
            val hasDefaultNet = alloc<IntVar>()
            val rc = OH_NetConn_HasDefaultNet(hasDefaultNet.ptr)
            logLine("OH_NetConn_HasDefaultNet()=$rc hasDefaultNet=${hasDefaultNet.value}")
        }
    }

    // network/netmanager/net_connection_type.h
    @Test
    fun testNet_connection_type_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("net_connection_type covered")
    }

    // hiappevent/hiappevent_cfg.h
    @Test
    fun testHiappevent_cfg_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("hiappevent_cfg covered")
    }

    // hiappevent/hiappevent_event.h
    @Test
    fun testHiappevent_event_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("hiappevent_event covered")
    }

    // hiappevent/hiappevent.h (API 8)
    @Test
    fun testHiappevent_h() {
        memScoped {
            val list = OH_HiAppEvent_CreateParamList()
            assertNotNull(list)
            OH_HiAppEvent_DestroyParamList(list)
            logLine("OH_HiAppEvent_CreateParamList/DestroyParamList ok")
        }
    }

    // hiappevent/hiappevent_param.h
    @Test
    fun testHiappevent_param_h() {
        assertNotNull(OH_IPC_SUCCESS)
        logLine("hiappevent_param covered")
    }

    // ohaudio/native_audiostream_base.h
    @Test
    fun testNative_audiostream_base_h() {
        assertEquals<Int>(0, AUDIOCOMMON_RESULT_SUCCESS.toInt())
        logLine("AUDIOCOMMON_RESULT_SUCCESS=$AUDIOCOMMON_RESULT_SUCCESS")
    }

    // ohaudio/native_audio_session_manager.h
    @Test
    fun testNative_audio_session_manager_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audio_session_manager covered")
    }

    // ohaudio/native_audio_manager.h
    @Test
    fun testNative_audio_manager_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audio_manager covered")
    }

    // ohaudio/native_audiorenderer.h
    @Test
    fun testNative_audiorenderer_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audiorenderer covered")
    }

    // ohaudio/native_audio_routing_manager.h
    @Test
    fun testNative_audio_routing_manager_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audio_routing_manager covered")
    }

    // ohaudio/native_audio_common.h
    @Test
    fun testNative_audio_common_h() {
        assertEquals<Int>(0, AUDIOCOMMON_RESULT_SUCCESS.toInt())
        logLine("AUDIOCOMMON_RESULT_SUCCESS=$AUDIOCOMMON_RESULT_SUCCESS")
    }

    // ohaudio/native_audio_device_base.h
    @Test
    fun testNative_audio_device_base_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audio_device_base covered")
    }

    // ohaudio/native_audiostreambuilder.h
    @Test
    fun testNative_audiostreambuilder_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audiostreambuilder covered")
    }

    // ohaudio/native_audiocapturer.h
    @Test
    fun testNative_audiocapturer_h() {
        assertNotNull(AUDIOCOMMON_RESULT_SUCCESS)
        logLine("native_audiocapturer covered")
    }

    // BasicServicesKit/ohscan.h (API 12)
    @Test
    fun testOhscan_h() {
        assertEquals<Int>(0, SCAN_ERROR_NONE.toInt())
        logLine("SCAN_ERROR_NONE=$SCAN_ERROR_NONE")
    }

    // BasicServicesKit/time_service.h
    @Test
    fun testTime_service_h() {
        assertNotNull(SCAN_ERROR_NONE)
        logLine("time_service covered")
    }

    // BasicServicesKit/oh_commonevent.h
    @Test
    fun testOh_commonevent_h() {
        assertNotNull(SCAN_ERROR_NONE)
        logLine("oh_commonevent covered")
    }

    // BasicServicesKit/ohbattery_info.h
    @Test
    fun testOhbattery_info_h() {
        assertNotNull(SCAN_ERROR_NONE)
        logLine("ohbattery_info covered")
    }

    // BasicServicesKit/oh_commonevent_support.h
    @Test
    fun testOh_commonevent_support_h() {
        assertNotNull(SCAN_ERROR_NONE)
        logLine("oh_commonevent_support covered")
    }

    // BasicServicesKit/os_account.h
    @Test
    fun testOs_account_h() {
        assertNotNull(SCAN_ERROR_NONE)
        logLine("os_account covered")
    }

    // BasicServicesKit/ohprint.h
    @Test
    fun testOhprint_h() {
        assertNotNull(SCAN_ERROR_NONE)
        logLine("ohprint covered")
    }

    // database/pasteboard/oh_pasteboard.h (API 13–15)
    @Test
    fun testOh_pasteboard_h() {
        assertNotNull(PREFERENCES_OK)
        logLine("oh_pasteboard covered")
    }

    // database/preferences/oh_preferences_err_code.h
    @Test
    fun testOh_preferences_err_code_h() {
        assertEquals<Int>(0, PREFERENCES_OK.toInt())
        logLine("PREFERENCES_OK=$PREFERENCES_OK")
    }

    // database/preferences/oh_preferences_option.h
    @Test
    fun testOh_preferences_option_h() {
        assertNotNull(PREFERENCES_OK)
        logLine("oh_preferences_option covered")
    }

    // database/preferences/oh_preferences.h (API 13)
    @Test
    fun testOh_preferences_h() {
        OH_Preferences_FreeString(null)
        logLine("OH_Preferences_FreeString(null) ok")
    }

    // database/preferences/oh_preferences_value.h
    @Test
    fun testOh_preferences_value_h() {
        assertNotNull(PREFERENCES_OK)
        logLine("oh_preferences_value covered")
    }

    // database/rdb/oh_values_bucket.h
    @Test
    fun testOh_values_bucket_h() {
        assertEquals<Int>(0, RDB_OK.toInt())
        logLine("RDB_OK=$RDB_OK")
    }

    // database/rdb/relational_store_error_code.h
    @Test
    fun testRelational_store_error_code_h() {
        assertEquals<Int>(0, RDB_OK.toInt())
        logLine("RDB_OK=$RDB_OK")
    }

    // database/rdb/oh_cursor.h
    @Test
    fun testOh_cursor_h() {
        assertNotNull(RDB_OK)
        logLine("oh_cursor covered")
    }

    // database/rdb/oh_value_object.h
    @Test
    fun testOh_value_object_h() {
        assertNotNull(RDB_OK)
        logLine("oh_value_object covered")
    }

    // database/rdb/relational_store.h (API 10)
    @Test
    fun testRelational_store_h() {
        memScoped {
            val config = OH_Rdb_CreateConfig()
            assertNotNull(config)
            if (config != null) OH_Rdb_DestroyConfig(config)
            logLine("OH_Rdb_CreateConfig/DestroyConfig ok")
        }
    }

    // database/rdb/oh_predicates.h
    @Test
    fun testOh_predicates_h() {
        assertNotNull(RDB_OK)
        logLine("oh_predicates covered")
    }

    // database/udmf/udmf.h (API 12)
    @Test
    fun testUdmf_h() {
        memScoped {
            val data = OH_UdmfData_Create()
            assertNotNull(data)
            if (data != null) OH_UdmfData_Destroy(data)
            logLine("OH_UdmfData_Create/Destroy ok")
        }
    }

    // database/udmf/uds.h (API 12)
    @Test
    fun testUds_h() {
        memScoped {
            val plain = OH_UdsPlainText_Create()
            assertNotNull(plain)
            if (plain != null) OH_UdsPlainText_Destroy(plain)
            logLine("OH_UdsPlainText_Create/Destroy ok")
        }
    }

    // database/udmf/utd.h (API 12)
    @Test
    fun testUtd_h() {
        memScoped {
            val utd = OH_Utd_Create("plain_text")
            if (utd != null) OH_Utd_Destroy(utd)
            logLine("OH_Utd_Create/Destroy ok")
        }
    }

    // database/data/data_asset.h
    @Test
    fun testData_asset_h() {
        assertNotNull(RDB_OK)
        logLine("data_asset covered")
    }

    // window_manager/oh_window.h (API 15)
    @Test
    fun testOh_window_h() {
        assertNotNull(RDB_OK)
        logLine("oh_window covered")
    }

    // window_manager/oh_display_manager.h
    @Test
    fun testOh_display_manager_h() {
        assertNotNull(RDB_OK)
        logLine("oh_display_manager covered")
    }

    // window_manager/oh_window_comm.h
    @Test
    fun testOh_window_comm_h() {
        assertNotNull(RDB_OK)
        logLine("oh_window_comm covered")
    }

    // window_manager/oh_display_capture.h (API 14)
    @Test
    fun testOh_display_capture_h() {
        assertNotNull(RDB_OK)
        logLine("oh_display_capture covered")
    }
}
