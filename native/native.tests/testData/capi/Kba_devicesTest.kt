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
// FREE_COMPILER_ARGS: -lbluetooth_ndk
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohsensor
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohvibrator.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lmindspore_lite_ndk
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lability_runtime
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lability_base_want
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lchild_process
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lddk_base.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lqos
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -llocation_ndk
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohinput
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -ltelephony_radio
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -ltelephony_data
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohcrypto
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohdlp_permission
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lusb_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lhuks_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lhid.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -ltransient_task
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lpurgeable_memory_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_common
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_packer
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_receiver
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_source
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lpixelmap
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_window
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lability_access_control
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohimage
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohcamera
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lipc_capi
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: --unresolved-symbols=ignore-all
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.devices.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_devicesTest {

    private fun logLine(msg: String) = println(msg)

    // ConnectivityKit/bluetooth/oh_bluetooth.h
    @Test
    fun testOh_bluetooth_h() {
        logLine("--- OH_Bluetooth_GetBluetoothSwitchState ---")
        val stateVar = nativeHeap.alloc<platform.ConnectivityKit.Bluetooth.Bluetooth_SwitchStateVar>()
        val result = platform.ConnectivityKit.Bluetooth.OH_Bluetooth_GetBluetoothSwitchState(stateVar.ptr)
        logLine("OH_Bluetooth_GetBluetoothSwitchState(ptr) result: $result, state: ${stateVar.value}")
        assertNotNull(result)
        nativeHeap.free(stateVar)
    }

    // sensors/oh_sensor_type.h
    @Test
    fun testOh_sensor_type_h() {
        assertEquals<Int>(1, SENSOR_TYPE_ACCELEROMETER.toInt())
        logLine("SENSOR_TYPE_ACCELEROMETER=$SENSOR_TYPE_ACCELEROMETER")
    }

    // sensors/oh_sensor.h
    @Test
    fun testOh_sensor_h() {
        val infos = OH_Sensor_CreateInfos(1u)
        assertNotNull(infos)
        logLine("OH_Sensor_CreateInfos=$infos")
        if (infos != null) {
            OH_Sensor_DestroyInfos(infos, 1u)
        }
    }

    // mindspore/status.h
    @Test
    fun testMindspore_status_h() {
        assertEquals<Int>(0, OH_AI_STATUS_SUCCESS.toInt())
        logLine("OH_AI_STATUS_SUCCESS=$OH_AI_STATUS_SUCCESS")
    }

    // mindspore/types.h
    @Test
    fun testMindspore_types_h() {
        assertEquals<Int>(0, OH_AI_MODELTYPE_MINDIR.toInt())
        logLine("OH_AI_MODELTYPE_MINDIR=$OH_AI_MODELTYPE_MINDIR")
    }

    // mindspore/context.h
    @Test
    fun testMindspore_context_h() {
        memScoped {
            val nnrtByName = OH_AI_CreateNNRTDeviceInfoByName("CPU1")
            logLine("OH_AI_CreateNNRTDeviceInfoByName=$nnrtByName")
        }
    }

    // mindspore/data_type.h
    @Test
    fun testMindspore_data_type_h() {
        assertEquals<Int>(0, OH_AI_DATATYPE_UNKNOWN.toInt())
        logLine("OH_AI_DATATYPE_UNKNOWN=$OH_AI_DATATYPE_UNKNOWN")
    }

    // mindspore/model.h
    @Test
    fun testMindspore_model_h() {
        memScoped {
            val model = OH_AI_ModelCreate()
            logLine("OH_AI_ModelCreate()=$model")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    // mindspore/format.h
    @Test
    fun testMindspore_format_h() {
        assertEquals<Int>(0, OH_AI_FORMAT_NCHW.toInt())
        logLine("OH_AI_FORMAT_NCHW=$OH_AI_FORMAT_NCHW")
    }

    // mindspore/tensor.h
    @Test
    fun testMindspore_tensor_h() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            logLine("OH_AI_TensorCreate=$tensor")
            if (tensor != null) {
                val ptr = alloc<COpaquePointerVar>()
                ptr.value = tensor
                OH_AI_TensorDestroy(ptr.ptr)
            }
        }
    }

    // AbilityKit/native_child_process.h
    @Test
    fun testNative_child_process_h() {
        val rc = OH_Ability_CreateNativeChildProcess(null, null)
        logLine("OH_Ability_CreateNativeChildProcess(null,null)=$rc")
    }

    // AbilityKit/ability_base/want.h
    @Test
    fun testAbility_base_want_h() {
        OH_AbilityBase_DestroyWant(null)
        logLine("OH_AbilityBase_DestroyWant(null) ok")
    }

    // AbilityKit/ability_runtime/application_context.h
    @Test
    fun testApplication_context_h() {
        memScoped {
            val mode = alloc<AbilityRuntime_AreaModeVar>()
            val rc = OH_AbilityRuntime_ApplicationContextGetAreaMode(mode.ptr)
            assertNotNull(rc)
            logLine("OH_AbilityRuntime_ApplicationContextGetAreaMode(ptr)=$rc")
        }
    }

    //accesstoken/ability_access_control.h
    @Test
    fun testAbility_access_control_h() {
        val ok = OH_AT_CheckSelfPermission("ohos.permission.GET_BUNDLE_INFO")
        logLine("OH_AT_CheckSelfPermission()=$ok") 
    }

    // ddk/ddk_api.h
    @Test
    fun testDdk_api_h() {
        memScoped {
            val nameBytes = "test_ashmem_destroy".encodeToByteArray()
            val name = allocArray<UByteVar>(nameBytes.size + 1).also { arr ->
                nameBytes.forEachIndexed { i, b -> arr[i] = b.toUByte() }
                arr[nameBytes.size] = 0u
            }
            val ashmemPtr = alloc<CPointerVar<DDK_Ashmem>>()
            OH_DDK_CreateAshmem(name, 4096u, ashmemPtr.ptr)
            val destroyRet = OH_DDK_DestroyAshmem(ashmemPtr.value)
            assertNotNull(destroyRet)
            logLine("OH_DDK_DestroyAshmem ret=$destroyRet")
        }
        logLine("OH_DDK_DestroyAshmem passed")
    }

    // qos/qos.h
    @Test
    fun testQos_h() {
        val rc = OH_QoS_ResetThreadQoS()
        assertNotNull(rc)
        logLine("OH_QoS_ResetThreadQoS()=$rc")
    }

    // LocationKit/oh_location.h
    @Test
    fun testOh_location_h() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            logLine("OH_Location_CreateRequestConfig()=$config")
            if (config != null) {
                OH_Location_DestroyRequestConfig(config)
                logLine("OH_Location_DestroyRequestConfig(config) ok")
            }
        }
    }

    // LocationKit/oh_location_type.h
    @Test
    fun testOh_location_type_h() {
        memScoped {
            val config = OH_Location_CreateRequestConfig()
            assertNotNull(config)
            logLine("OH_Location_CreateRequestConfig=$config")
            OH_Location_DestroyRequestConfig(config)
        }
    }

    // multimodalinput/oh_input_manager.h
    @Test
    fun testOh_input_manager_h() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            logLine("OH_Input_CreateKeyState()=$ks")
            ks?.let {
                val ptr = alloc<CPointerVar<Input_KeyState>>()
                ptr.value = it
                OH_Input_DestroyKeyState(ptr.ptr)
            }
        }
    }

    // multimodalinput/oh_axis_type.h
    @Test
    fun testOh_axis_type_h() {
        assertNotNull(AXIS_ACTION_BEGIN)
        logLine("AXIS_ACTION_BEGIN=$AXIS_ACTION_BEGIN")
    }

    // multimodalinput/oh_key_code.h
    @Test
    fun testOh_key_code_h() {
        assertNotNull(KEYCODE_UNKNOWN)
        logLine("KEYCODE_UNKNOWN=$KEYCODE_UNKNOWN")
    }

    // telephony/cellular_data/telephony_data.h
    @Test
    fun testTelephony_data_h() {
        val slotId = OH_Telephony_GetDefaultCellularDataSlotId()
        logLine("OH_Telephony_GetDefaultCellularDataSlotId()=$slotId")
    }

    // telephony/core_service/telephony_radio.h
    @Test
    fun testTelephony_radio_h() {
        memScoped {
            val state = alloc<Telephony_NetworkState>()
            val rc = OH_Telephony_GetNetworkState(state.ptr)
            assertNotNull(rc)
            logLine("OH_Telephony_GetNetworkState(ptr)=$rc")
        }
    }

    // CryptoArchitectureKit/crypto_digest.h
    @Test
    fun testCrypto_digest_h() {
        memScoped {
            logLine("--- OH_DigestCrypto_Destroy ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            OH_CryptoDigest_Create("SHA256", ctx.ptr)
            OH_DigestCrypto_Destroy(ctx.value)
            logLine("OH_DigestCrypto_Destroy ok")
        }
    }

    // CryptoArchitectureKit/crypto_sym_key.h
    @Test
    fun testCrypto_sym_key_h() {
        memScoped {
            val ctx = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
            OH_CryptoSymKeyGenerator_Create("AES256", ctx.ptr)
            OH_CryptoSymKeyGenerator_Destroy(ctx.value)
        }
    }

    // CryptoArchitectureKit/crypto_sym_cipher.h
    @Test
    fun testCrypto_sym_cipher_h() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                OH_CryptoSymCipher_Create("AES128|GCM|PKCS7", ctx.ptr)
                OH_CryptoSymCipher_Destroy(ctx.value)
                logLine("OH_CryptoSymCipher_Destroy ok (API 12+)")
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_Destroy (API 12+) exception: $e")
            }
        }
    }

    // CryptoArchitectureKit/crypto_common.h
    @Test
    fun testCrypto_common_h() {
        memScoped {
            logLine("--- OH_Crypto_FreeDataBlob ---")
            OH_Crypto_FreeDataBlob(null)
            logLine("OH_Crypto_FreeDataBlob(null) ok")
            val dataBlob = alloc<Crypto_DataBlob>().apply {
                data = null
                len = 0u
            }
            OH_Crypto_FreeDataBlob(dataBlob.ptr)
            logLine("OH_Crypto_FreeDataBlob(ptr) ok")
        }
    }

    // CryptoArchitectureKit/crypto_asym_key.h
    @Test
    fun testCrypto_asym_key_h() {
        OH_CryptoKeyPair_Destroy(null)
        logLine("OH_CryptoKeyPair_Destroy(null) ok")
    }

    // CryptoArchitectureKit/crypto_signature.h
    @Test
    fun testCrypto_signature_h() {
        memScoped {
            logLine("--- OH_CryptoVerify_Destroy ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            OH_CryptoVerify_Destroy(verify.value)
            logLine("OH_CryptoVerify_Destroy ok")
        }
    }

    // DataProtectionKit/dlp_permission_api.h
    @Test
    fun testDlp_permission_api_h() {
        memScoped {
            try {
                val isInSandbox = alloc<BooleanVar>()
                val result = OH_DLP_IsInSandbox(isInSandbox.ptr)
                assertNotNull(result)
                logLine("OH_DLP_IsInSandbox result: $result")
            } catch (e: Throwable) {
                logLine("OH_DLP_IsInSandbox exception: $e")
            }
        }
    }

    // usb/usb_ddk_api.h
    @Test
    fun testUsb_ddk_api_h() {
        logLine("OH_Usb_Init=${OH_Usb_Init()}")
        OH_Usb_Release()
    }


    // huks/native_huks_type.h
    @Test
    fun testNative_huks_type_h() {
        assertEquals<Int>(16, OH_HUKS_AE_TAG_LEN)
        logLine("OH_HUKS_AE_TAG_LEN=$OH_HUKS_AE_TAG_LEN")
    }

    // huks/native_huks_param.h
    @Test
    fun testNative_huks_param_h() {
        memScoped {
            val paramSet = alloc<CPointerVar<OH_Huks_ParamSet>>()
            OH_Huks_FreeParamSet(paramSet.ptr)
            logLine("OH_Huks_FreeParamSet(null) ok")
        }
    }

    // huks/native_huks_api.h
    @Test
    fun testGetSdkVersion() { memScoped {
        // OH_Huks_GetSdkVersion
        val sdkVersion = alloc<OH_Huks_Blob>().apply {
            val buf = allocArray<UByteVar>(256)
            data = buf
            size = 256u
        }
        val rc = OH_Huks_GetSdkVersion(sdkVersion.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_GetSdkVersion errorCode=${rc.useContents { errorCode }}")
    } }

    // hid/hid_ddk_api.h
    @Test
    fun testHid_ddk_api_h() {
        val rc = OH_Hid_DestroyDevice(-1)
        logLine("OH_Hid_DestroyDevice(-1)=$rc")
    }

    // transient_task/transient_task_api.h
    @Test
    fun testTransient_task_api_h() {
        val rc = OH_BackgroundTaskManager_CancelSuspendDelay(0)
        logLine("OH_BackgroundTaskManager_CancelSuspendDelay(0)=$rc")
    }

    // purgeable_memory/purgeable_memory.h
    @Test
    fun testPurgeable_memory_h() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(0u, null, null)
            logLine("OH_PurgeableMemory_Create=$purg")
            if (purg != null) OH_PurgeableMemory_Destroy(purg)
        }
    }

    // ohcamera/camera_device.h
    @Test
    fun testCamera_device_h() {
        memScoped {
            val orientationVar = alloc<UIntVar>()
            val ret = OH_CameraDevice_GetCameraOrientation(null, orientationVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraDevice_GetCameraOrientation ret=$ret")
        }
    }

    // ohcamera/camera.h
    @Test
    fun testOhcamera_camera_h() {
        memScoped {
            val mgrPtr = alloc<CPointerVar<Camera_Manager>>()
            val ret = OH_Camera_GetCameraManager(mgrPtr.ptr)
            assertNotNull(ret)
            logLine("OH_Camera_GetCameraManager ret=$ret")
            if (mgrPtr.value != null) {
                OH_Camera_DeleteCameraManager(mgrPtr.value)
            }
        }
    }

    // ohcamera/camera_input.h
    @Test
    fun testCamera_input_h() {
        memScoped {
            val callbacks = alloc<VideoOutput_Callbacks>().apply { onFrameStart = null; onFrameEnd = null; onError = null }
            val ret = OH_VideoOutput_UnregisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_UnregisterCallback ret=$ret")
        }
    }

    // ohcamera/preview_output.h
    @Test
    fun testPreview_output_h() {
        val rc = OH_PreviewOutput_Release(null)
        logLine("OH_PreviewOutput_Release(null)=$rc")
    }

    // ohcamera/photo_native.h
    @Test
    fun testPhoto_native_h() {
        val rc = OH_PhotoNative_Release(null)
        logLine("OH_PhotoNative_Release(null)=$rc")
    }
}
