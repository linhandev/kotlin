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
import platform.CameraKit.OH_Camera.*
import platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_NONE
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_CameraTest {

    private fun logLine(msg: String) = println("[stdout] OH_CameraTest $msg")

    @Test
    fun testEnum_Camera_ErrorCode() {
        assertEquals(CAMERA_OK.toInt(), 0)
        assertEquals(CAMERA_INVALID_ARGUMENT.toInt(), 7400101)
        assertEquals(CAMERA_OPERATION_NOT_ALLOWED.toInt(), 7400102)
        assertEquals(CAMERA_SESSION_NOT_CONFIG.toInt(), 7400103)
        assertEquals(CAMERA_SESSION_NOT_RUNNING.toInt(), 7400104)
        assertEquals(CAMERA_SESSION_CONFIG_LOCKED.toInt(), 7400105)
        assertEquals(CAMERA_DEVICE_SETTING_LOCKED.toInt(), 7400106)
        assertEquals(CAMERA_CONFLICT_CAMERA.toInt(), 7400107)
        assertEquals(CAMERA_DEVICE_DISABLED.toInt(), 7400108)
        assertEquals(CAMERA_DEVICE_PREEMPTED.toInt(), 7400109)
        assertEquals(CAMERA_UNRESOLVED_CONFLICTS_WITH_CURRENT_CONFIGURATIONS.toInt(), 7400110)
        assertEquals(CAMERA_SERVICE_FATAL_ERROR.toInt(), 7400201)
        logLine("Camera_ErrorCode passed")
    }

    @Test
    fun testEnum_Camera_Status() {
        assertEquals(CAMERA_STATUS_APPEAR.toInt(), 0)
        assertEquals(CAMERA_STATUS_DISAPPEAR.toInt(), 1)
        assertEquals(CAMERA_STATUS_AVAILABLE.toInt(), 2)
        assertEquals(CAMERA_STATUS_UNAVAILABLE.toInt(), 3)
        logLine("Camera_Status passed")
    }

    @Test
    fun testEnum_Camera_SceneMode() {
        assertEquals(NORMAL_PHOTO.toInt(), 1)
        assertEquals(NORMAL_VIDEO.toInt(), 2)
        assertEquals(SECURE_PHOTO.toInt(), 12)
        logLine("Camera_SceneMode passed")
    }

    @Test
    fun testEnum_Camera_Position() {
        assertEquals(CAMERA_POSITION_UNSPECIFIED.toInt(), 0)
        assertEquals(CAMERA_POSITION_BACK.toInt(), 1)
        assertEquals(CAMERA_POSITION_FRONT.toInt(), 2)
        logLine("Camera_Position passed")
    }

    @Test
    fun testEnum_Camera_Type() {
        assertEquals(CAMERA_TYPE_DEFAULT.toInt(), 0)
        assertEquals(CAMERA_TYPE_WIDE_ANGLE.toInt(), 1)
        assertEquals(CAMERA_TYPE_ULTRA_WIDE.toInt(), 2)
        assertEquals(CAMERA_TYPE_TELEPHOTO.toInt(), 3)
        assertEquals(CAMERA_TYPE_TRUE_DEPTH.toInt(), 4)
        logLine("Camera_Type passed")
    }

    @Test
    fun testEnum_Camera_Connection() {
        assertEquals(CAMERA_CONNECTION_BUILT_IN.toInt(), 0)
        assertEquals(CAMERA_CONNECTION_USB_PLUGIN.toInt(), 1)
        assertEquals(CAMERA_CONNECTION_REMOTE.toInt(), 2)
        logLine("Camera_Connection passed")
    }

    @Test
    fun testEnum_Camera_Format() {
        assertEquals(CAMERA_FORMAT_RGBA_8888.toInt(), 3)
        assertEquals(CAMERA_FORMAT_YUV_420_SP.toInt(), 1003)
        assertEquals(CAMERA_FORMAT_JPEG.toInt(), 2000)
        assertEquals(CAMERA_FORMAT_YCBCR_P010.toInt(), 2001)
        assertEquals(CAMERA_FORMAT_YCRCB_P010.toInt(), 2002)
        logLine("Camera_Format passed")
    }

    @Test
    fun testEnum_Camera_FlashMode() {
        assertEquals(FLASH_MODE_CLOSE.toInt(), 0)
        assertEquals(FLASH_MODE_OPEN.toInt(), 1)
        assertEquals(FLASH_MODE_AUTO.toInt(), 2)
        assertEquals(FLASH_MODE_ALWAYS_OPEN.toInt(), 3)
        logLine("Camera_FlashMode passed")
    }

    @Test
    fun testEnum_Camera_ExposureMode() {
        assertEquals(EXPOSURE_MODE_LOCKED.toInt(), 0)
        assertEquals(EXPOSURE_MODE_AUTO.toInt(), 1)
        assertEquals(EXPOSURE_MODE_CONTINUOUS_AUTO.toInt(), 2)
        logLine("Camera_ExposureMode passed")
    }

    @Test
    fun testEnum_Camera_FocusMode() {
        assertEquals(FOCUS_MODE_MANUAL.toInt(), 0)
        assertEquals(FOCUS_MODE_CONTINUOUS_AUTO.toInt(), 1)
        assertEquals(FOCUS_MODE_AUTO.toInt(), 2)
        assertEquals(FOCUS_MODE_LOCKED.toInt(), 3)
        logLine("Camera_FocusMode passed")
    }

    @Test
    fun testEnum_Camera_FocusState() {
        assertEquals(FOCUS_STATE_SCAN.toInt(), 0)
        assertEquals(FOCUS_STATE_FOCUSED.toInt(), 1)
        assertEquals(FOCUS_STATE_UNFOCUSED.toInt(), 2)
        logLine("Camera_FocusState passed")
    }

    @Test
    fun testEnum_Camera_VideoStabilizationMode() {
        assertEquals(STABILIZATION_MODE_OFF.toInt(), 0)
        assertEquals(STABILIZATION_MODE_LOW.toInt(), 1)
        assertEquals(STABILIZATION_MODE_MIDDLE.toInt(), 2)
        assertEquals(STABILIZATION_MODE_HIGH.toInt(), 3)
        assertEquals(STABILIZATION_MODE_AUTO.toInt(), 4)
        logLine("Camera_VideoStabilizationMode passed")
    }

    @Test
    fun testEnum_Camera_ImageRotation() {
        assertEquals(IAMGE_ROTATION_0.toInt(), 0)
        assertEquals(IAMGE_ROTATION_90.toInt(), 90)
        assertEquals(IAMGE_ROTATION_180.toInt(), 180)
        assertEquals(IAMGE_ROTATION_270.toInt(), 270)
        logLine("Camera_ImageRotation passed")
    }

    @Test
    fun testEnum_Camera_QualityLevel() {
        assertEquals(QUALITY_LEVEL_HIGH.toInt(), 0)
        assertEquals(QUALITY_LEVEL_MEDIUM.toInt(), 1)
        assertEquals(QUALITY_LEVEL_LOW.toInt(), 2)
        logLine("Camera_QualityLevel passed")
    }

    @Test
    fun testEnum_Camera_MetadataObjectType() {
        assertEquals(FACE_DETECTION.toInt(), 0)
        logLine("Camera_MetadataObjectType passed")
    }

    @Test
    fun testEnum_Camera_TorchMode() {
        assertEquals(OFF.toInt(), 0)
        assertEquals(ON.toInt(), 1)
        assertEquals(AUTO.toInt(), 2)
        logLine("Camera_TorchMode passed")
    }

    @Test
    fun testEnum_Camera_SmoothZoomMode() {
        assertEquals(NORMAL.toInt(), 0)
        logLine("Camera_SmoothZoomMode passed")
    }

    @Test
    fun testEnum_Camera_PreconfigType() {
        assertEquals(PRECONFIG_720P.toInt(), 0)
        assertEquals(PRECONFIG_1080P.toInt(), 1)
        assertEquals(PRECONFIG_4K.toInt(), 2)
        assertEquals(PRECONFIG_HIGH_QUALITY.toInt(), 3)
        logLine("Camera_PreconfigType passed")
    }

    @Test
    fun testEnum_Camera_PreconfigRatio() {
        assertEquals(PRECONFIG_RATIO_1_1.toInt(), 0)
        assertEquals(PRECONFIG_RATIO_4_3.toInt(), 1)
        assertEquals(PRECONFIG_RATIO_16_9.toInt(), 2)
        logLine("Camera_PreconfigRatio passed")
    }

    @Test
    fun testEnum_Camera_HostDeviceType() {
        assertEquals(HOST_DEVICE_TYPE_UNKNOWN_TYPE.toInt(), 0)
        assertEquals(HOST_DEVICE_TYPE_PHONE.toInt(), 0x0E)
        assertEquals(HOST_DEVICE_TYPE_TABLET.toInt(), 0x11)
        logLine("Camera_HostDeviceType passed")
    }

    @Test
    fun testEnum_Camera_FoldStatus() {
        assertEquals(NON_FOLDABLE.toInt(), 0)
        assertEquals(EXPANDED.toInt(), 1)
        assertEquals(FOLDED.toInt(), 2)
        logLine("Camera_FoldStatus passed")
    }

    @Test
    fun testEnum_Camera_QualityPrioritization() {
        assertEquals(HIGH_QUALITY.toInt(), 0)
        assertEquals(POWER_BALANCE.toInt(), 1)
        logLine("Camera_QualityPrioritization passed")
    }

    @Test
    fun testEnum_Camera_ConcurrentType() {
        assertEquals(CAMERA_CONCURRENT_TYPE_LIMITED_CAPABILITY.toInt(), 0)
        assertEquals(CAMERA_CONCURRENT_TYPE_FULL_CAPABILITY.toInt(), 1)
        logLine("Camera_ConcurrentType passed")
    }

    @Test
    fun testEnum_Camera_WhiteBalanceMode() {
        assertEquals(CAMERA_WHITE_BALANCE_MODE_AUTO.toInt(), 0)
        assertEquals(CAMERA_WHITE_BALANCE_MODE_CLOUDY.toInt(), 1)
        assertEquals(CAMERA_WHITE_BALANCE_MODE_INCANDESCENT.toInt(), 2)
        assertEquals(CAMERA_WHITE_BALANCE_MODE_FLUORESCENT.toInt(), 3)
        assertEquals(CAMERA_WHITE_BALANCE_MODE_DAYLIGHT.toInt(), 4)
        assertEquals(CAMERA_WHITE_BALANCE_MODE_MANUAL.toInt(), 5)
        assertEquals(CAMERA_WHITE_BALANCE_MODE_LOCKED.toInt(), 6)
        logLine("Camera_WhiteBalanceMode passed")
    }

    @Test
    fun testEnum_Camera_SystemPressureLevel() {
        assertEquals(SYSTEM_PRESSURE_NORMAL.toInt(), 0)
        assertEquals(SYSTEM_PRESSURE_MILD.toInt(), 1)
        assertEquals(SYSTEM_PRESSURE_SEVERE.toInt(), 2)
        assertEquals(SYSTEM_PRESSURE_CRITICAL.toInt(), 3)
        assertEquals(SYSTEM_PRESSURE_SHUTDOWN.toInt(), 4)
        logLine("Camera_SystemPressureLevel passed")
    }

    @Test
    fun testEnum_Camera_ControlCenterEffectType() {
        assertEquals(CONTROL_CENTER_EFFECT_TYPE_BEAUTY.toInt(), 0)
        assertEquals(CONTROL_CENTER_EFFECT_TYPE_PORTRAIT.toInt(), 1)
        logLine("Camera_ControlCenterEffectType passed")
    }

    @Test
    fun testEnum_Camera_PhotoQualityPrioritization() {
        assertEquals(CAMERA_PHOTO_QUALITY_PRIORITIZATION_HIGH_QUALITY.toInt(), 0)
        assertEquals(CAMERA_PHOTO_QUALITY_PRIORITIZATION_SPEED.toInt(), 1)
        logLine("Camera_PhotoQualityPrioritization passed")
    }

    @Test
    fun testOH_Camera_GetCameraManager() {
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

    @Test
    fun testOH_Camera_DeleteCameraManager() {
        memScoped {
            val mgrPtr = alloc<CPointerVar<Camera_Manager>>()
            OH_Camera_GetCameraManager(mgrPtr.ptr)
            assertNotNull(mgrPtr.value)
            val delRet = OH_Camera_DeleteCameraManager(mgrPtr.value)
            assertNotNull(delRet)
            logLine("OH_Camera_DeleteCameraManager ret=$delRet")
        }
    }

    @Test
    fun testOH_CameraManager_RegisterCallback() {
        memScoped {
            val mgrPtr = alloc<CPointerVar<Camera_Manager>>()
            val getMgrRet = OH_Camera_GetCameraManager(mgrPtr.ptr)
            assertNotNull(getMgrRet)
            assertNotNull(mgrPtr.value)
            val callbacks = alloc<CameraManager_Callbacks>().apply { onCameraStatus = null }
            val regRet = OH_CameraManager_RegisterCallback(mgrPtr.value, callbacks.ptr)
            assertNotNull(regRet)
            logLine("OH_CameraManager_RegisterCallback ret=$regRet")
            OH_CameraManager_UnregisterCallback(mgrPtr.value, callbacks.ptr)
            OH_Camera_DeleteCameraManager(mgrPtr.value)
        }
    }

    @Test
    fun testOH_CameraManager_UnregisterCallback() {
        memScoped {
            val mgrPtr = alloc<CPointerVar<Camera_Manager>>()
            OH_Camera_GetCameraManager(mgrPtr.ptr)
            assertNotNull(mgrPtr.value)
            val callbacks = alloc<CameraManager_Callbacks>().apply { onCameraStatus = null }
            OH_CameraManager_RegisterCallback(mgrPtr.value, callbacks.ptr)
            val ret = OH_CameraManager_UnregisterCallback(mgrPtr.value, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_UnregisterCallback ret=$ret")
            OH_Camera_DeleteCameraManager(mgrPtr.value)
        }
    }

    @Test
    fun testOH_CameraDevice_GetCameraOrientation() {
        memScoped {
            val orientationVar = alloc<UIntVar>()
            val ret = OH_CameraDevice_GetCameraOrientation(null, orientationVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraDevice_GetCameraOrientation ret=$ret")
        }
    }

    @Test
    fun testOH_CameraDevice_GetHostDeviceName() {
        memScoped {
            val hostDeviceNamePtr = alloc<CPointerVar<ByteVar>>()
            val ret = OH_CameraDevice_GetHostDeviceName(null, hostDeviceNamePtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraDevice_GetHostDeviceName ret=$ret")
        }
    }

    @Test
    fun testOH_CameraDevice_GetHostDeviceType() {
        memScoped {
            val hostDeviceTypeVar = alloc<Camera_HostDeviceTypeVar>()
            val ret = OH_CameraDevice_GetHostDeviceType(null, hostDeviceTypeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraDevice_GetHostDeviceType ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_RegisterCallback() {
        memScoped {
            val callbacks = alloc<PreviewOutput_Callbacks>().apply { onFrameStart = null; onFrameEnd = null; onError = null }
            val ret = OH_PreviewOutput_RegisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_RegisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_UnregisterCallback() {
        memScoped {
            val ret = OH_PreviewOutput_UnregisterCallback(null, null)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_UnregisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_Start() {
        memScoped {
            val ret = OH_PreviewOutput_Start(null)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_Start ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_Stop() {
        memScoped {
            val ret = OH_PreviewOutput_Stop(null)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_Stop ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_Release() {
        memScoped {
            val ret = OH_PreviewOutput_Release(null)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_Release ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_GetActiveProfile() {
        memScoped {
            val profilePtr = alloc<CPointerVar<Camera_Profile>>()
            val ret = OH_PreviewOutput_GetActiveProfile(null, profilePtr.ptr)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_GetActiveProfile ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_DeleteProfile() {
        memScoped {
            val ret = OH_PreviewOutput_DeleteProfile(null)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_DeleteProfile ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_GetPreviewRotation() {
        memScoped {
            val imageRotation = alloc<Camera_ImageRotationVar>()
            val ret = OH_PreviewOutput_GetPreviewRotation(null, 0, imageRotation.ptr)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_GetPreviewRotation ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_SetPreviewRotation() {
        memScoped {
            val ret = OH_PreviewOutput_SetPreviewRotation(null, IAMGE_ROTATION_0, false)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_SetPreviewRotation ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_GetSupportedFrameRates() {
        memScoped {
            val frameRateRangePtr = alloc<CPointerVar<Camera_FrameRateRange>>()
            val sizeVar = alloc<UIntVar>()
            val ret = OH_PreviewOutput_GetSupportedFrameRates(null, frameRateRangePtr.ptr, sizeVar.ptr)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_GetSupportedFrameRates ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_DeleteFrameRates() {
        memScoped {
            val ret = OH_PreviewOutput_DeleteFrameRates(null, null)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_DeleteFrameRates ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_SetFrameRate() {
        memScoped {
            val ret = OH_PreviewOutput_SetFrameRate(null, 30, 30)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_SetFrameRate ret=$ret")
        }
    }

    @Test
    fun testOH_PreviewOutput_GetActiveFrameRate() {
        memScoped {
            val activeFrameRate = alloc<Camera_FrameRateRange>()
            val ret = OH_PreviewOutput_GetActiveFrameRate(null, activeFrameRate.ptr)
            assertNotNull(ret)
            logLine("OH_PreviewOutput_GetActiveFrameRate ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_RegisterCallback() {
        memScoped {
            val callbacks = alloc<VideoOutput_Callbacks>().apply { onFrameStart = null; onFrameEnd = null; onError = null }
            val ret = OH_VideoOutput_RegisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_RegisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_UnregisterCallback() {
        memScoped {
            val callbacks = alloc<VideoOutput_Callbacks>().apply { onFrameStart = null; onFrameEnd = null; onError = null }
            val ret = OH_VideoOutput_UnregisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_UnregisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_Start() {
        memScoped {
            val ret = OH_VideoOutput_Start(null)
            assertNotNull(ret)
            logLine("OH_VideoOutput_Start ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_Stop() {
        memScoped {
            val ret = OH_VideoOutput_Stop(null)
            assertNotNull(ret)
            logLine("OH_VideoOutput_Stop ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_Release() {
        memScoped {
            val ret = OH_VideoOutput_Release(null)
            assertNotNull(ret)
            logLine("OH_VideoOutput_Release ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_GetActiveProfile() {
        memScoped {
            val profilePtr = alloc<CPointerVar<Camera_VideoProfile>>()
            val ret = OH_VideoOutput_GetActiveProfile(null, profilePtr.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_GetActiveProfile ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_DeleteProfile() {
        memScoped {
            val ret = OH_VideoOutput_DeleteProfile(null)
            assertNotNull(ret)
            logLine("OH_VideoOutput_DeleteProfile ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_IsMirrorSupported() {
        memScoped {
            val mirrorSupportedVar = alloc<BooleanVar>()
            val ret = OH_VideoOutput_IsMirrorSupported(null, mirrorSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_IsMirrorSupported ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_EnableMirror() {
        memScoped {
            val ret = OH_VideoOutput_EnableMirror(null, false)
            assertNotNull(ret)
            logLine("OH_VideoOutput_EnableMirror ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_GetVideoRotation() {
        memScoped {
            val imageRotation = alloc<Camera_ImageRotationVar>()
            val ret = OH_VideoOutput_GetVideoRotation(null, 0, imageRotation.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_GetVideoRotation ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_GetSupportedFrameRates() {
        memScoped {
            val frameRateRangePtr = alloc<CPointerVar<Camera_FrameRateRange>>()
            val sizeVar = alloc<UIntVar>()
            val ret = OH_VideoOutput_GetSupportedFrameRates(null, frameRateRangePtr.ptr, sizeVar.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_GetSupportedFrameRates ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_DeleteFrameRates() {
        memScoped {
            val ret = OH_VideoOutput_DeleteFrameRates(null, null)
            assertNotNull(ret)
            logLine("OH_VideoOutput_DeleteFrameRates ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_SetFrameRate() {
        memScoped {
            val ret = OH_VideoOutput_SetFrameRate(null, 30, 30)
            assertNotNull(ret)
            logLine("OH_VideoOutput_SetFrameRate ret=$ret")
        }
    }

    @Test
    fun testOH_VideoOutput_GetActiveFrameRate() {
        memScoped {
            val activeFrameRate = alloc<Camera_FrameRateRange>()
            val ret = OH_VideoOutput_GetActiveFrameRate(null, activeFrameRate.ptr)
            assertNotNull(ret)
            logLine("OH_VideoOutput_GetActiveFrameRate ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_RegisterTorchStatusCallback() {
        memScoped {
            val torchCb = staticCFunction { _mgr: CPointer<Camera_Manager>?, _status: CPointer<Camera_TorchStatusInfo>? -> }
            val ret = OH_CameraManager_RegisterTorchStatusCallback(null, torchCb)
            assertNotNull(ret)
            logLine("OH_CameraManager_RegisterTorchStatusCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_UnregisterTorchStatusCallback() {
        memScoped {
            val torchCb = staticCFunction { _mgr: CPointer<Camera_Manager>?, _status: CPointer<Camera_TorchStatusInfo>? -> }
            val ret = OH_CameraManager_UnregisterTorchStatusCallback(null, torchCb)
            assertNotNull(ret)
            logLine("OH_CameraManager_UnregisterTorchStatusCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_RegisterFoldStatusInfoCallback() {
        memScoped {
            val foldCb = staticCFunction { _mgr: CPointer<Camera_Manager>?, _info: CPointer<Camera_FoldStatusInfo>? -> }
            val ret = OH_CameraManager_RegisterFoldStatusInfoCallback(null, foldCb)
            assertNotNull(ret)
            logLine("OH_CameraManager_RegisterFoldStatusInfoCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_UnregisterFoldStatusInfoCallback() {
        memScoped {
            val foldCb = staticCFunction { _mgr: CPointer<Camera_Manager>?, _info: CPointer<Camera_FoldStatusInfo>? -> }
            val ret = OH_CameraManager_UnregisterFoldStatusInfoCallback(null, foldCb)
            assertNotNull(ret)
            logLine("OH_CameraManager_UnregisterFoldStatusInfoCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_GetSupportedCameras() {
        memScoped {
            val camerasPtr = alloc<CPointerVar<Camera_Device>>()
            val sizeVar = alloc<UIntVar>()
            val ret = OH_CameraManager_GetSupportedCameras(null, camerasPtr.ptr, sizeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_GetSupportedCameras ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_DeleteSupportedCameras() {
        memScoped {
            val ret = OH_CameraManager_DeleteSupportedCameras(null, null, 0u)
            assertNotNull(ret)
            logLine("OH_CameraManager_DeleteSupportedCameras ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_GetSupportedCameraOutputCapability() {
        memScoped {
            val capPtr = alloc<CPointerVar<Camera_OutputCapability>>()
            val ret = OH_CameraManager_GetSupportedCameraOutputCapability(null, null, capPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_GetSupportedCameraOutputCapability ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_GetSupportedCameraOutputCapabilityWithSceneMode() {
        memScoped {
            val capPtr = alloc<CPointerVar<Camera_OutputCapability>>()
            val ret = OH_CameraManager_GetSupportedCameraOutputCapabilityWithSceneMode(null, null, NORMAL_PHOTO, capPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_GetSupportedCameraOutputCapabilityWithSceneMode ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_DeleteSupportedCameraOutputCapability() {
        memScoped {
            val ret = OH_CameraManager_DeleteSupportedCameraOutputCapability(null, null)
            assertNotNull(ret)
            logLine("OH_CameraManager_DeleteSupportedCameraOutputCapability ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_IsCameraMuted() {
        memScoped {
            val mutedVar = alloc<BooleanVar>()
            val ret = OH_CameraManager_IsCameraMuted(null, mutedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_IsCameraMuted ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreateCaptureSession() {
        memScoped {
            val sessionPtr = alloc<CPointerVar<Camera_CaptureSession>>()
            val ret = OH_CameraManager_CreateCaptureSession(null, sessionPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreateCaptureSession ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreateCameraInput() {
        memScoped {
            val inputPtr = alloc<CPointerVar<Camera_Input>>()
            val ret = OH_CameraManager_CreateCameraInput(null, null, inputPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreateCameraInput ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreateCameraInput_WithPositionAndType() {
        memScoped {
            val inputPtr = alloc<CPointerVar<Camera_Input>>()
            val ret = OH_CameraManager_CreateCameraInput_WithPositionAndType(null, CAMERA_POSITION_UNSPECIFIED, CAMERA_TYPE_DEFAULT, inputPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreateCameraInput_WithPositionAndType ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreatePreviewOutput() {
        memScoped {
            val previewPtr = alloc<CPointerVar<Camera_PreviewOutput>>()
            val ret = OH_CameraManager_CreatePreviewOutput(null, null, null, previewPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreatePreviewOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreatePreviewOutputUsedInPreconfig() {
        memScoped {
            val previewPtr = alloc<CPointerVar<Camera_PreviewOutput>>()
            val ret = OH_CameraManager_CreatePreviewOutputUsedInPreconfig(null, null, previewPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreatePreviewOutputUsedInPreconfig ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreatePhotoOutput() {
        memScoped {
            val photoPtr = alloc<CPointerVar<Camera_PhotoOutput>>()
            val ret = OH_CameraManager_CreatePhotoOutput(null, null, null, photoPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreatePhotoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreatePhotoOutputUsedInPreconfig() {
        memScoped {
            val photoPtr = alloc<CPointerVar<Camera_PhotoOutput>>()
            val ret = OH_CameraManager_CreatePhotoOutputUsedInPreconfig(null, null, photoPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreatePhotoOutputUsedInPreconfig ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreatePhotoOutputWithoutSurface() {
        memScoped {
            val photoPtr = alloc<CPointerVar<Camera_PhotoOutput>>()
            val ret = OH_CameraManager_CreatePhotoOutputWithoutSurface(null, null, photoPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreatePhotoOutputWithoutSurface ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreateVideoOutput() {
        memScoped {
            val videoPtr = alloc<CPointerVar<Camera_VideoOutput>>()
            val ret = OH_CameraManager_CreateVideoOutput(null, null, null, videoPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreateVideoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreateVideoOutputUsedInPreconfig() {
        memScoped {
            val videoPtr = alloc<CPointerVar<Camera_VideoOutput>>()
            val ret = OH_CameraManager_CreateVideoOutputUsedInPreconfig(null, null, videoPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreateVideoOutputUsedInPreconfig ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_CreateMetadataOutput() {
        memScoped {
            val metadataPtr = alloc<CPointerVar<Camera_MetadataOutput>>()
            val ret = OH_CameraManager_CreateMetadataOutput(null, null, metadataPtr.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_CreateMetadataOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_GetSupportedSceneModes() {
        memScoped {
            val sceneModesPtr = alloc<CPointerVar<ByteVar>>()
            val sceneModesSizeVar = alloc<UIntVar>()
            val ret = OH_CameraManager_GetSupportedSceneModes(null, sceneModesPtr.ptr.reinterpret(), sceneModesSizeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_GetSupportedSceneModes ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_DeleteSceneModes() {
        memScoped {
            val ret = OH_CameraManager_DeleteSceneModes(null, null)
            assertNotNull(ret)
            logLine("OH_CameraManager_DeleteSceneModes ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_IsTorchSupported() {
        memScoped {
            val torchSupportedVar = alloc<BooleanVar>()
            val ret = OH_CameraManager_IsTorchSupported(null, torchSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_IsTorchSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_IsTorchSupportedByTorchMode() {
        memScoped {
            val torchSupportedVar = alloc<BooleanVar>()
            val ret = OH_CameraManager_IsTorchSupportedByTorchMode(null, OFF, torchSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraManager_IsTorchSupportedByTorchMode ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_SetTorchMode() {
        memScoped {
            val ret = OH_CameraManager_SetTorchMode(null, OFF)
            assertNotNull(ret)
            logLine("OH_CameraManager_SetTorchMode ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_GetCameraDevice() {
        memScoped {
            val cameraDevice = alloc<Camera_Device>()
            val ret = try { OH_CameraManager_GetCameraDevice(null, CAMERA_POSITION_UNSPECIFIED, CAMERA_TYPE_DEFAULT, cameraDevice.ptr) } catch (e: Throwable) { logLine("OH_CameraManager_GetCameraDevice (API 18) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CameraManager_GetCameraDevice ret=$ret")
        }
    }

    @Test
    fun testOH_CameraManager_GetCameraConcurrentInfos() {
        memScoped {
            val concurrentInfoPtr = alloc<CPointerVar<Camera_ConcurrentInfo>>()
            val sizeVar = alloc<UIntVar>()
            val ret = try { OH_CameraManager_GetCameraConcurrentInfos(null, null, 0u, concurrentInfoPtr.ptr, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_CameraManager_GetCameraConcurrentInfos (API 18) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CameraManager_GetCameraConcurrentInfos ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_RegisterCallback() {
        memScoped {
            val callbacks = alloc<CameraInput_Callbacks>().apply { onError = null }
            val ret = OH_CameraInput_RegisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_CameraInput_RegisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_UnregisterCallback() {
        memScoped {
            val callbacks = alloc<CameraInput_Callbacks>().apply { onError = null }
            val ret = OH_CameraInput_UnregisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_CameraInput_UnregisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_Open() {
        memScoped {
            val ret = OH_CameraInput_Open(null)
            assertNotNull(ret)
            logLine("OH_CameraInput_Open ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_OpenSecureCamera() {
        memScoped {
            val secureSeqIdVar = alloc<ULongVar>()
            val ret = OH_CameraInput_OpenSecureCamera(null, secureSeqIdVar.ptr)
            assertNotNull(ret)
            logLine("OH_CameraInput_OpenSecureCamera ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_OpenConcurrentCameras() {
        memScoped {
            val ret = try { OH_CameraInput_OpenConcurrentCameras(null, CAMERA_CONCURRENT_TYPE_LIMITED_CAPABILITY) } catch (e: Throwable) { logLine("OH_CameraInput_OpenConcurrentCameras (API 18) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CameraInput_OpenConcurrentCameras ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_Close() {
        memScoped {
            val ret = OH_CameraInput_Close(null)
            assertNotNull(ret)
            logLine("OH_CameraInput_Close ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_Release() {
        memScoped {
            val ret = OH_CameraInput_Release(null)
            assertNotNull(ret)
            logLine("OH_CameraInput_Release ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_IsPhysicalCameraOrientationVariable() {
        memScoped {
            val isVariableVar = alloc<BooleanVar>()
            val ret = try { OH_CameraInput_IsPhysicalCameraOrientationVariable(null, isVariableVar.ptr) } catch (e: Throwable) { logLine("OH_CameraInput_IsPhysicalCameraOrientationVariable (API 22) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CameraInput_IsPhysicalCameraOrientationVariable ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_GetPhysicalCameraOrientation() {
        memScoped {
            val orientationVar = alloc<UIntVar>()
            val ret = try { OH_CameraInput_GetPhysicalCameraOrientation(null, orientationVar.ptr) } catch (e: Throwable) { logLine("OH_CameraInput_GetPhysicalCameraOrientation (API 22) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CameraInput_GetPhysicalCameraOrientation ret=$ret")
        }
    }

    @Test
    fun testOH_CameraInput_UsePhysicalCameraOrientation() {
        memScoped {
            val ret = try { OH_CameraInput_UsePhysicalCameraOrientation(null, false) } catch (e: Throwable) { logLine("OH_CameraInput_UsePhysicalCameraOrientation (API 22) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CameraInput_UsePhysicalCameraOrientation ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterCallback() {
        memScoped {
            val callbacks = alloc<PhotoOutput_Callbacks>().apply { onFrameStart = null; onFrameShutter = null; onFrameEnd = null; onError = null }
            val ret = OH_PhotoOutput_RegisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterCallback() {
        memScoped {
            val callbacks = alloc<PhotoOutput_Callbacks>().apply { onFrameStart = null; onFrameShutter = null; onFrameEnd = null; onError = null }
            val ret = OH_PhotoOutput_UnregisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterCaptureStartWithInfoCallback() {
        memScoped {
            val captureStartCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _info: CPointer<Camera_CaptureStartInfo>? -> }
            val ret = OH_PhotoOutput_RegisterCaptureStartWithInfoCallback(null, captureStartCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterCaptureStartWithInfoCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_GetPhotoRotation() {
        memScoped {
            val imageRotation = alloc<Camera_ImageRotationVar>()
            val ret = OH_PhotoOutput_GetPhotoRotation(null, 0, imageRotation.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_GetPhotoRotation ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterCaptureStartWithInfoCallback() {
        memScoped {
            val captureStartCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _info: CPointer<Camera_CaptureStartInfo>? -> }
            val ret = OH_PhotoOutput_UnregisterCaptureStartWithInfoCallback(null, captureStartCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterCaptureStartWithInfoCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterCaptureEndCallback() {
        memScoped {
            val captureEndCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _count: Int -> }
            val ret = OH_PhotoOutput_RegisterCaptureEndCallback(null, captureEndCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterCaptureEndCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterCaptureEndCallback() {
        memScoped {
            val captureEndCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _count: Int -> }
            val ret = OH_PhotoOutput_UnregisterCaptureEndCallback(null, captureEndCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterCaptureEndCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterFrameShutterEndCallback() {
        memScoped {
            val frameShutterEndCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _info: CPointer<Camera_FrameShutterInfo>? -> }
            val ret = OH_PhotoOutput_RegisterFrameShutterEndCallback(null, frameShutterEndCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterFrameShutterEndCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterFrameShutterEndCallback() {
        memScoped {
            val frameShutterEndCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _info: CPointer<Camera_FrameShutterInfo>? -> }
            val ret = OH_PhotoOutput_UnregisterFrameShutterEndCallback(null, frameShutterEndCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterFrameShutterEndCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterCaptureReadyCallback() {
        memScoped {
            val captureReadyCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>? -> }
            val ret = OH_PhotoOutput_RegisterCaptureReadyCallback(null, captureReadyCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterCaptureReadyCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterCaptureReadyCallback() {
        memScoped {
            val captureReadyCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>? -> }
            val ret = OH_PhotoOutput_UnregisterCaptureReadyCallback(null, captureReadyCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterCaptureReadyCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterEstimatedCaptureDurationCallback() {
        memScoped {
            val estDurationCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _dur: Long -> }
            val ret = OH_PhotoOutput_RegisterEstimatedCaptureDurationCallback(null, estDurationCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterEstimatedCaptureDurationCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterEstimatedCaptureDurationCallback() {
        memScoped {
            val estDurationCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _dur: Long -> }
            val ret = OH_PhotoOutput_UnregisterEstimatedCaptureDurationCallback(null, estDurationCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterEstimatedCaptureDurationCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterPhotoAvailableCallback() {
        memScoped {
            val photoAvailableCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _photo: CPointer<OH_PhotoNative>? -> }
            val ret = OH_PhotoOutput_RegisterPhotoAvailableCallback(null, photoAvailableCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterPhotoAvailableCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterPhotoAvailableCallback() {
        memScoped {
            val photoAvailableCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _photo: CPointer<OH_PhotoNative>? -> }
            val ret = OH_PhotoOutput_UnregisterPhotoAvailableCallback(null, photoAvailableCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterPhotoAvailableCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_RegisterPhotoAssetAvailableCallback() {
        memScoped {
            val photoAssetCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _asset: CPointer<OH_MediaAsset>? -> }
            val ret = OH_PhotoOutput_RegisterPhotoAssetAvailableCallback(null, photoAssetCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_RegisterPhotoAssetAvailableCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_UnregisterPhotoAssetAvailableCallback() {
        memScoped {
            val photoAssetCb = staticCFunction { _out: CPointer<Camera_PhotoOutput>?, _asset: CPointer<OH_MediaAsset>? -> }
            val ret = OH_PhotoOutput_UnregisterPhotoAssetAvailableCallback(null, photoAssetCb)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_UnregisterPhotoAssetAvailableCallback ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_Capture() {
        memScoped {
            val ret = OH_PhotoOutput_Capture(null)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_Capture ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_Capture_WithCaptureSetting() {
        memScoped {
            val captureSetting = alloc<Camera_PhotoCaptureSetting>().readValue()
            val ret = OH_PhotoOutput_Capture_WithCaptureSetting(null, captureSetting)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_Capture_WithCaptureSetting ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_Release() {
        memScoped {
            val ret = OH_PhotoOutput_Release(null)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_Release ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_IsMirrorSupported() {
        memScoped {
            val mirrorSupportedVar = alloc<BooleanVar>()
            val ret = OH_PhotoOutput_IsMirrorSupported(null, mirrorSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_IsMirrorSupported ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_EnableMirror() {
        memScoped {
            val ret = OH_PhotoOutput_EnableMirror(null, false)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_EnableMirror ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_GetActiveProfile() {
        memScoped {
            val profilePtr = alloc<CPointerVar<Camera_Profile>>()
            val ret = OH_PhotoOutput_GetActiveProfile(null, profilePtr.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_GetActiveProfile ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_DeleteProfile() {
        memScoped {
            val ret = OH_PhotoOutput_DeleteProfile(null)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_DeleteProfile ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_IsMovingPhotoSupported() {
        memScoped {
            val movingPhotoVar = alloc<BooleanVar>()
            val ret = OH_PhotoOutput_IsMovingPhotoSupported(null, movingPhotoVar.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_IsMovingPhotoSupported ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_EnableMovingPhoto() {
        memScoped {
            val ret = OH_PhotoOutput_EnableMovingPhoto(null, false)
            assertNotNull(ret)
            logLine("OH_PhotoOutput_EnableMovingPhoto ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_IsPhotoQualityPrioritizationSupported() {
        memScoped {
            val qualityPrioritizationVar = alloc<BooleanVar>()
            val ret = try { OH_PhotoOutput_IsPhotoQualityPrioritizationSupported(null, CAMERA_PHOTO_QUALITY_PRIORITIZATION_HIGH_QUALITY, qualityPrioritizationVar.ptr) } catch (e: Throwable) { logLine("OH_PhotoOutput_IsPhotoQualityPrioritizationSupported (API 21) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_PhotoOutput_IsPhotoQualityPrioritizationSupported ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoOutput_SetPhotoQualityPrioritization() {
        memScoped {
            val ret = try { OH_PhotoOutput_SetPhotoQualityPrioritization(null, CAMERA_PHOTO_QUALITY_PRIORITIZATION_HIGH_QUALITY) } catch (e: Throwable) { logLine("OH_PhotoOutput_SetPhotoQualityPrioritization (API 21) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_PhotoOutput_SetPhotoQualityPrioritization ret=$ret")
        }
    }

    @Test
    fun testOH_MetadataOutput_RegisterCallback() {
        memScoped {
            val callbacks = alloc<MetadataOutput_Callbacks>().apply { onMetadataObjectAvailable = null; onError = null }
            val ret = OH_MetadataOutput_RegisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_MetadataOutput_RegisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_MetadataOutput_UnregisterCallback() {
        memScoped {
            val callbacks = alloc<MetadataOutput_Callbacks>().apply { onMetadataObjectAvailable = null; onError = null }
            val ret = OH_MetadataOutput_UnregisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_MetadataOutput_UnregisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_MetadataOutput_Start() {
        memScoped {
            val ret = OH_MetadataOutput_Start(null)
            assertNotNull(ret)
            logLine("OH_MetadataOutput_Start ret=$ret")
        }
    }

    @Test
    fun testOH_MetadataOutput_Stop() {
        memScoped {
            val ret = OH_MetadataOutput_Stop(null)
            assertNotNull(ret)
            logLine("OH_MetadataOutput_Stop ret=$ret")
        }
    }

    @Test
    fun testOH_MetadataOutput_Release() {
        memScoped {
            val ret = OH_MetadataOutput_Release(null)
            assertNotNull(ret)
            logLine("OH_MetadataOutput_Release ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoNative_GetMainImage() {
        memScoped {
            val mainImagePtr = alloc<CPointerVar<OH_ImageNative>>()
            val ret = OH_PhotoNative_GetMainImage(null, mainImagePtr.ptr)
            assertNotNull(ret)
            logLine("OH_PhotoNative_GetMainImage ret=$ret")
        }
    }

    @Test
    fun testOH_PhotoNative_Release() {
        memScoped {
            val ret = OH_PhotoNative_Release(null)
            assertNotNull(ret)
            logLine("OH_PhotoNative_Release ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterCallback() {
        memScoped {
            val callbacks = alloc<CaptureSession_Callbacks>().apply { onFocusStateChange = null; onError = null }
            val ret = OH_CaptureSession_RegisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterCallback() {
        memScoped {
            val callbacks = alloc<CaptureSession_Callbacks>().apply { onFocusStateChange = null; onError = null }
            val ret = OH_CaptureSession_UnregisterCallback(null, callbacks.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterSmoothZoomInfoCallback() {
        memScoped {
            val smoothZoomCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _info: CPointer<Camera_SmoothZoomInfo>? -> }
            val ret = OH_CaptureSession_RegisterSmoothZoomInfoCallback(null, smoothZoomCb)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterSmoothZoomInfoCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterSmoothZoomInfoCallback() {
        memScoped {
            val smoothZoomCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _info: CPointer<Camera_SmoothZoomInfo>? -> }
            val ret = OH_CaptureSession_UnregisterSmoothZoomInfoCallback(null, smoothZoomCb)
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterSmoothZoomInfoCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetSessionMode() {
        memScoped {
            val ret = OH_CaptureSession_SetSessionMode(null, NORMAL_PHOTO)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetSessionMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_AddSecureOutput() {
        memScoped {
            val ret = OH_CaptureSession_AddSecureOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_AddSecureOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_BeginConfig() {
        memScoped {
            val ret = OH_CaptureSession_BeginConfig(null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_BeginConfig ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CommitConfig() {
        memScoped {
            val ret = OH_CaptureSession_CommitConfig(null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CommitConfig ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_AddInput() {
        memScoped {
            val ret = OH_CaptureSession_AddInput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_AddInput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RemoveInput() {
        memScoped {
            val ret = OH_CaptureSession_RemoveInput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RemoveInput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_AddPreviewOutput() {
        memScoped {
            val ret = OH_CaptureSession_AddPreviewOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_AddPreviewOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RemovePreviewOutput() {
        memScoped {
            val ret = OH_CaptureSession_RemovePreviewOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RemovePreviewOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_AddPhotoOutput() {
        memScoped {
            val ret = OH_CaptureSession_AddPhotoOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_AddPhotoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RemovePhotoOutput() {
        memScoped {
            val ret = OH_CaptureSession_RemovePhotoOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RemovePhotoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_AddVideoOutput() {
        memScoped {
            val ret = OH_CaptureSession_AddVideoOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_AddVideoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RemoveVideoOutput() {
        memScoped {
            val ret = OH_CaptureSession_RemoveVideoOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RemoveVideoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_AddMetadataOutput() {
        memScoped {
            val ret = OH_CaptureSession_AddMetadataOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_AddMetadataOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RemoveMetadataOutput() {
        memScoped {
            val ret = OH_CaptureSession_RemoveMetadataOutput(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RemoveMetadataOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_Start() {
        memScoped {
            val ret = OH_CaptureSession_Start(null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_Start ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_Stop() {
        memScoped {
            val ret = OH_CaptureSession_Stop(null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_Stop ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_Release() {
        memScoped {
            val ret = OH_CaptureSession_Release(null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_Release ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_HasFlash() {
        memScoped {
            val hasFlashVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_HasFlash(null, hasFlashVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_HasFlash ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsFlashModeSupported() {
        memScoped {
            val flashSupportedVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_IsFlashModeSupported(null, FLASH_MODE_CLOSE, flashSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsFlashModeSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetFlashMode() {
        memScoped {
            val flashModeVar = alloc<Camera_FlashModeVar>()
            val ret = OH_CaptureSession_GetFlashMode(null, flashModeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetFlashMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetFlashMode() {
        memScoped {
            val ret = OH_CaptureSession_SetFlashMode(null, FLASH_MODE_CLOSE)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetFlashMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsExposureModeSupported() {
        memScoped {
            val expSupportedVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_IsExposureModeSupported(null, EXPOSURE_MODE_AUTO, expSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsExposureModeSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetExposureMode() {
        memScoped {
            val expModeVar = alloc<Camera_ExposureModeVar>()
            val ret = OH_CaptureSession_GetExposureMode(null, expModeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetExposureMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetExposureMode() {
        memScoped {
            val ret = OH_CaptureSession_SetExposureMode(null, EXPOSURE_MODE_AUTO)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetExposureMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetMeteringPoint() {
        memScoped {
            val point = alloc<Camera_Point>()
            val ret = OH_CaptureSession_GetMeteringPoint(null, point.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetMeteringPoint ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetMeteringPoint() {
        memScoped {
            val pointVal = alloc<Camera_Point>().readValue()
            val ret = OH_CaptureSession_SetMeteringPoint(null, pointVal)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetMeteringPoint ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetExposureBiasRange() {
        memScoped {
            val minBias = alloc<FloatVar>(); val maxBias = alloc<FloatVar>(); val stepVar = alloc<FloatVar>()
            val ret = OH_CaptureSession_GetExposureBiasRange(null, minBias.ptr, maxBias.ptr, stepVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetExposureBiasRange ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetExposureBias() {
        memScoped {
            val ret = OH_CaptureSession_SetExposureBias(null, 0f)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetExposureBias ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetExposureBias() {
        memScoped {
            val expBiasVar = alloc<FloatVar>()
            val ret = OH_CaptureSession_GetExposureBias(null, expBiasVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetExposureBias ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsFocusModeSupported() {
        memScoped {
            val focusSupportedVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_IsFocusModeSupported(null, FOCUS_MODE_MANUAL, focusSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsFocusModeSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetFocusMode() {
        memScoped {
            val focusModeVar = alloc<Camera_FocusModeVar>()
            val ret = OH_CaptureSession_GetFocusMode(null, focusModeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetFocusMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetFocusMode() {
        memScoped {
            val ret = OH_CaptureSession_SetFocusMode(null, FOCUS_MODE_MANUAL)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetFocusMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetFocusPoint() {
        memScoped {
            val focusPoint = alloc<Camera_Point>()
            val ret = OH_CaptureSession_GetFocusPoint(null, focusPoint.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetFocusPoint ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetFocusPoint() {
        memScoped {
            val focusPointVal = alloc<Camera_Point>().readValue()
            val ret = OH_CaptureSession_SetFocusPoint(null, focusPointVal)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetFocusPoint ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetZoomRatioRange() {
        memScoped {
            val minZoom = alloc<FloatVar>(); val maxZoom = alloc<FloatVar>()
            val ret = OH_CaptureSession_GetZoomRatioRange(null, minZoom.ptr, maxZoom.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetZoomRatioRange ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetZoomRatio() {
        memScoped {
            val zoomVar = alloc<FloatVar>()
            val ret = OH_CaptureSession_GetZoomRatio(null, zoomVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetZoomRatio ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetZoomRatio() {
        memScoped {
            val ret = OH_CaptureSession_SetZoomRatio(null, 1f)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetZoomRatio ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsVideoStabilizationModeSupported() {
        memScoped {
            val stabSupportedVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_IsVideoStabilizationModeSupported(null, STABILIZATION_MODE_OFF, stabSupportedVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsVideoStabilizationModeSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetVideoStabilizationMode() {
        memScoped {
            val stabModeVar = alloc<Camera_VideoStabilizationModeVar>()
            val ret = OH_CaptureSession_GetVideoStabilizationMode(null, stabModeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetVideoStabilizationMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetVideoStabilizationMode() {
        memScoped {
            val ret = OH_CaptureSession_SetVideoStabilizationMode(null, STABILIZATION_MODE_OFF)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetVideoStabilizationMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CanAddInput() {
        memScoped {
            val canAddInputVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_CanAddInput(null, null, canAddInputVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CanAddInput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CanAddPreviewOutput() {
        memScoped {
            val canAddPreviewVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_CanAddPreviewOutput(null, null, canAddPreviewVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CanAddPreviewOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CanAddPhotoOutput() {
        memScoped {
            val canAddPhotoVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_CanAddPhotoOutput(null, null, canAddPhotoVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CanAddPhotoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CanAddVideoOutput() {
        memScoped {
            val canAddVideoVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_CanAddVideoOutput(null, null, canAddVideoVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CanAddVideoOutput ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CanPreconfig() {
        memScoped {
            val canPreconfigVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_CanPreconfig(null, PRECONFIG_720P, canPreconfigVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CanPreconfig ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_CanPreconfigWithRatio() {
        memScoped {
            val canPreconfigRatioVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_CanPreconfigWithRatio(null, PRECONFIG_720P, PRECONFIG_RATIO_1_1, canPreconfigRatioVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_CanPreconfigWithRatio ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_Preconfig() {
        memScoped {
            val ret = OH_CaptureSession_Preconfig(null, PRECONFIG_720P)
            assertNotNull(ret)
            logLine("OH_CaptureSession_Preconfig ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_PreconfigWithRatio() {
        memScoped {
            val ret = OH_CaptureSession_PreconfigWithRatio(null, PRECONFIG_720P, PRECONFIG_RATIO_1_1)
            assertNotNull(ret)
            logLine("OH_CaptureSession_PreconfigWithRatio ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetExposureValue() {
        memScoped {
            val expValueVar = alloc<FloatVar>()
            val ret = OH_CaptureSession_GetExposureValue(null, expValueVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetExposureValue ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetFocalLength() {
        memScoped {
            val focalLengthVar = alloc<FloatVar>()
            val ret = OH_CaptureSession_GetFocalLength(null, focalLengthVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetFocalLength ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetSmoothZoom() {
        memScoped {
            val ret = OH_CaptureSession_SetSmoothZoom(null, 1f, NORMAL)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetSmoothZoom ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetSupportedColorSpaces() {
        memScoped {
            val sizeVar = alloc<UIntVar>()
            val ret = OH_CaptureSession_GetSupportedColorSpaces(null, null, sizeVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetSupportedColorSpaces ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_DeleteColorSpaces() {
        memScoped {
            val ret = OH_CaptureSession_DeleteColorSpaces(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_DeleteColorSpaces ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetActiveColorSpace() {
        memScoped {
            val ret = OH_CaptureSession_GetActiveColorSpace(null, null)
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetActiveColorSpace ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetActiveColorSpace() {
        memScoped {
            val ret = OH_CaptureSession_SetActiveColorSpace(null, OH_COLORSPACE_NONE)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetActiveColorSpace ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterAutoDeviceSwitchStatusCallback() {
        memScoped {
            val deviceSwitchCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _info: CPointer<Camera_AutoDeviceSwitchStatusInfo>? -> }
            val ret = OH_CaptureSession_RegisterAutoDeviceSwitchStatusCallback(null, deviceSwitchCb)
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterAutoDeviceSwitchStatusCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterAutoDeviceSwitchStatusCallback() {
        memScoped {
            val deviceSwitchCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _info: CPointer<Camera_AutoDeviceSwitchStatusInfo>? -> }
            val ret = OH_CaptureSession_UnregisterAutoDeviceSwitchStatusCallback(null, deviceSwitchCb)
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterAutoDeviceSwitchStatusCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsAutoDeviceSwitchSupported() {
        memScoped {
            val autoSwitchVar = alloc<BooleanVar>()
            val ret = OH_CaptureSession_IsAutoDeviceSwitchSupported(null, autoSwitchVar.ptr)
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsAutoDeviceSwitchSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_EnableAutoDeviceSwitch() {
        memScoped {
            val ret = OH_CaptureSession_EnableAutoDeviceSwitch(null, false)
            assertNotNull(ret)
            logLine("OH_CaptureSession_EnableAutoDeviceSwitch ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetQualityPrioritization() {
        memScoped {
            val ret = OH_CaptureSession_SetQualityPrioritization(null, HIGH_QUALITY)
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetQualityPrioritization ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsMacroSupported() {
        memScoped {
            val macroSupportedVar = alloc<BooleanVar>()
            val ret = try { OH_CaptureSession_IsMacroSupported(null, macroSupportedVar.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_IsMacroSupported (API 19) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsMacroSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_EnableMacro() {
        memScoped {
            val ret = try { OH_CaptureSession_EnableMacro(null, false) } catch (e: Throwable) { logLine("OH_CaptureSession_EnableMacro (API 19) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_EnableMacro ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsWhiteBalanceModeSupported() {
        memScoped {
            val wbSupportedVar = alloc<BooleanVar>()
            val ret = try { OH_CaptureSession_IsWhiteBalanceModeSupported(null, CAMERA_WHITE_BALANCE_MODE_AUTO, wbSupportedVar.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_IsWhiteBalanceModeSupported (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsWhiteBalanceModeSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetWhiteBalanceMode() {
        memScoped {
            val wbModeVar = alloc<Camera_WhiteBalanceModeVar>()
            val ret = try { OH_CaptureSession_GetWhiteBalanceMode(null, wbModeVar.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_GetWhiteBalanceMode (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetWhiteBalanceMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetWhiteBalanceRange() {
        memScoped {
            val minTemp = alloc<IntVar>(); val maxTemp = alloc<IntVar>()
            val ret = try { OH_CaptureSession_GetWhiteBalanceRange(null, minTemp.ptr, maxTemp.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_GetWhiteBalanceRange (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetWhiteBalanceRange ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetWhiteBalance() {
        memScoped {
            val colorTempVar = alloc<IntVar>()
            val ret = try { OH_CaptureSession_GetWhiteBalance(null, colorTempVar.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_GetWhiteBalance (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetWhiteBalance ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetWhiteBalance() {
        memScoped {
            val ret = try { OH_CaptureSession_SetWhiteBalance(null, 0) } catch (e: Throwable) { logLine("OH_CaptureSession_SetWhiteBalance (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetWhiteBalance ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_SetWhiteBalanceMode() {
        memScoped {
            val ret = try { OH_CaptureSession_SetWhiteBalanceMode(null, CAMERA_WHITE_BALANCE_MODE_AUTO) } catch (e: Throwable) { logLine("OH_CaptureSession_SetWhiteBalanceMode (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_SetWhiteBalanceMode ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterSystemPressureLevelChangeCallback() {
        memScoped {
            val pressureCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _level: Camera_SystemPressureLevel -> }
            val ret = try { OH_CaptureSession_RegisterSystemPressureLevelChangeCallback(null, pressureCb) } catch (e: Throwable) { logLine("OH_CaptureSession_RegisterSystemPressureLevelChangeCallback (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterSystemPressureLevelChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterSystemPressureLevelChangeCallback() {
        memScoped {
            val pressureCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _level: Camera_SystemPressureLevel -> }
            val ret = try { OH_CaptureSession_UnregisterSystemPressureLevelChangeCallback(null, pressureCb) } catch (e: Throwable) { logLine("OH_CaptureSession_UnregisterSystemPressureLevelChangeCallback (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterSystemPressureLevelChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_IsControlCenterSupported() {
        memScoped {
            val ctrlCenterVar = alloc<BooleanVar>()
            val ret = try { OH_CaptureSession_IsControlCenterSupported(null, ctrlCenterVar.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_IsControlCenterSupported (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_IsControlCenterSupported ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_GetSupportedEffectTypes() {
        memScoped {
            val effectTypesPtr = alloc<CPointerVar<ByteVar>>()
            val effectTypesSizeVar = alloc<UIntVar>()
            val ret = try { OH_CaptureSession_GetSupportedEffectTypes(null, effectTypesPtr.ptr.reinterpret(), effectTypesSizeVar.ptr) } catch (e: Throwable) { logLine("OH_CaptureSession_GetSupportedEffectTypes (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_GetSupportedEffectTypes ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_DeleteSupportedEffectTypes() {
        memScoped {
            val ret = try { OH_CaptureSession_DeleteSupportedEffectTypes(null, null, 0u) } catch (e: Throwable) { logLine("OH_CaptureSession_DeleteSupportedEffectTypes (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_DeleteSupportedEffectTypes ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_EnableControlCenter() {
        memScoped {
            val ret = try { OH_CaptureSession_EnableControlCenter(null, false) } catch (e: Throwable) { logLine("OH_CaptureSession_EnableControlCenter (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_EnableControlCenter ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterControlCenterEffectStatusChangeCallback() {
        memScoped {
            val ctrlEffectCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _info: CPointer<Camera_ControlCenterStatusInfo>? -> }
            val ret = try { OH_CaptureSession_RegisterControlCenterEffectStatusChangeCallback(null, ctrlEffectCb) } catch (e: Throwable) { logLine("OH_CaptureSession_RegisterControlCenterEffectStatusChangeCallback (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterControlCenterEffectStatusChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterControlCenterEffectStatusChangeCallback() {
        memScoped {
            val ctrlEffectCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _info: CPointer<Camera_ControlCenterStatusInfo>? -> }
            val ret = try { OH_CaptureSession_UnregisterControlCenterEffectStatusChangeCallback(null, ctrlEffectCb) } catch (e: Throwable) { logLine("OH_CaptureSession_UnregisterControlCenterEffectStatusChangeCallback (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterControlCenterEffectStatusChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterMacroStatusChangeCallback() {
        memScoped {
            val macroStatusCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _detected: Boolean -> }
            val ret = try { OH_CaptureSession_RegisterMacroStatusChangeCallback(null, macroStatusCb) } catch (e: Throwable) { logLine("OH_CaptureSession_RegisterMacroStatusChangeCallback (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterMacroStatusChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterMacroStatusChangeCallback() {
        memScoped {
            val macroStatusCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _detected: Boolean -> }
            val ret = try { OH_CaptureSession_UnregisterMacroStatusChangeCallback(null, macroStatusCb) } catch (e: Throwable) { logLine("OH_CaptureSession_UnregisterMacroStatusChangeCallback (API 20) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterMacroStatusChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_RegisterIsoChangeCallback() {
        memScoped {
            val isoChangeCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _iso: Int -> }
            val ret = try { OH_CaptureSession_RegisterIsoChangeCallback(null, isoChangeCb) } catch (e: Throwable) { logLine("OH_CaptureSession_RegisterIsoChangeCallback (API 22) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_RegisterIsoChangeCallback ret=$ret")
        }
    }

    @Test
    fun testOH_CaptureSession_UnregisterIsoChangeCallback() {
        memScoped {
            val isoChangeCb = staticCFunction { _s: CPointer<Camera_CaptureSession>?, _iso: Int -> }
            val ret = try { OH_CaptureSession_UnregisterIsoChangeCallback(null, isoChangeCb) } catch (e: Throwable) { logLine("OH_CaptureSession_UnregisterIsoChangeCallback (API 22) exception: $e"); CAMERA_INVALID_ARGUMENT }
            assertNotNull(ret)
            logLine("OH_CaptureSession_UnregisterIsoChangeCallback ret=$ret")
        }
    }
}
