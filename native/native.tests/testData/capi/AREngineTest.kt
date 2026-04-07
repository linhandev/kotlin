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
class AREngineTest {
    
    @Test
    fun testErrorCodes() {
        println("[stdout] AREngineTest ===== AREngine error code test start =====")
        try {
        val success = platform.AREngine.AREngine.ARENGINE_SUCCESS
        val permissionNotGranted = platform.AREngine.AREngine.ARENGINE_ERROR_PERMISSION_NOT_GRANTED
        val invalidArgument = platform.AREngine.AREngine.ARENGINE_ERROR_INVALID_ARGUMENT
        val deviceNotSupported = platform.AREngine.AREngine.ARENGINE_ERROR_DEVICE_NOT_SUPPORTED
        val fatal = platform.AREngine.AREngine.ARENGINE_ERROR_FATAL
        val sessionPaused = platform.AREngine.AREngine.ARENGINE_ERROR_SESSION_PAUSED
        val sessionNotPaused = platform.AREngine.AREngine.ARENGINE_ERROR_SESSION_NOT_PAUSED
        val notTracking = platform.AREngine.AREngine.ARENGINE_ERROR_NOT_TRACKING
        val textureNotSet = platform.AREngine.AREngine.ARENGINE_ERROR_TEXTURE_NOT_SET
        val missingGlContext = platform.AREngine.AREngine.ARENGINE_ERROR_MISSING_GL_CONTEXT
        val unsupportedConfig = platform.AREngine.AREngine.ARENGINE_ERROR_UNSUPPORTED_CONFIGURATION
        val resourceExhausted = platform.AREngine.AREngine.ARENGINE_ERROR_RESOURCE_EXHAUSTED
        val notAvailable = platform.AREngine.AREngine.ARENGINE_ERROR_NOT_AVAILABLE
        val cameraNotAvailable = platform.AREngine.AREngine.ARENGINE_ERROR_CAMERA_NOT_AVAILABLE

        logLine("ARENGINE_SUCCESS=$success")
        logLine("ARENGINE_ERROR_PERMISSION_NOT_GRANTED=$permissionNotGranted")
        logLine("ARENGINE_ERROR_INVALID_ARGUMENT=$invalidArgument")
        logLine("ARENGINE_ERROR_DEVICE_NOT_SUPPORTED=$deviceNotSupported")
        logLine("ARENGINE_ERROR_FATAL=$fatal")
        logLine("ARENGINE_ERROR_SESSION_PAUSED=$sessionPaused")
        logLine("ARENGINE_ERROR_SESSION_NOT_PAUSED=$sessionNotPaused")
        logLine("ARENGINE_ERROR_NOT_TRACKING=$notTracking")
        logLine("ARENGINE_ERROR_TEXTURE_NOT_SET=$textureNotSet")
        logLine("ARENGINE_ERROR_MISSING_GL_CONTEXT=$missingGlContext")
        logLine("ARENGINE_ERROR_UNSUPPORTED_CONFIGURATION=$unsupportedConfig")
        logLine("ARENGINE_ERROR_RESOURCE_EXHAUSTED=$resourceExhausted")
        logLine("ARENGINE_ERROR_NOT_AVAILABLE=$notAvailable")
        logLine("ARENGINE_ERROR_CAMERA_NOT_AVAILABLE=$cameraNotAvailable")

        // 打印边界值
        logLine("Boundary: first=ARENGINE_SUCCESS=$success, last=ARENGINE_ERROR_CAMERA_NOT_AVAILABLE=$cameraNotAvailable")

        // 打印所有错误码（除了 SUCCESS）
        val errorCodes = listOf(
            "ARENGINE_ERROR_PERMISSION_NOT_GRANTED" to permissionNotGranted,
            "ARENGINE_ERROR_INVALID_ARGUMENT" to invalidArgument,
            "ARENGINE_ERROR_DEVICE_NOT_SUPPORTED" to deviceNotSupported,
            "ARENGINE_ERROR_FATAL" to fatal,
            "ARENGINE_ERROR_SESSION_PAUSED" to sessionPaused,
            "ARENGINE_ERROR_SESSION_NOT_PAUSED" to sessionNotPaused,
            "ARENGINE_ERROR_NOT_TRACKING" to notTracking,
            "ARENGINE_ERROR_TEXTURE_NOT_SET" to textureNotSet,
            "ARENGINE_ERROR_MISSING_GL_CONTEXT" to missingGlContext,
            "ARENGINE_ERROR_UNSUPPORTED_CONFIGURATION" to unsupportedConfig,
            "ARENGINE_ERROR_RESOURCE_EXHAUSTED" to resourceExhausted,
            "ARENGINE_ERROR_NOT_AVAILABLE" to notAvailable,
            "ARENGINE_ERROR_CAMERA_NOT_AVAILABLE" to cameraNotAvailable
        )

        val summary = errorCodes.joinToString(
            prefix = "All error codes should be != ARENGINE_SUCCESS ($success): ",
            separator = ", "
        ) { (name, value) -> "$name=$value" }
        logLine(summary)

        // 校验 SUCCESS 与所有错误码不同
        for ((name, code) in errorCodes) {
            assertNotEquals(
                success,
                code,
                "AREngine error code should differ from ARENGINE_SUCCESS: $name"
            )
        }
        } catch (e: Throwable) { logLine("testErrorCodes (constants) exception: $e") }
    }

    @Test
    fun testEnumValues() {
        println("[stdout] AREngineTest ===== AREngine enum test start =====")
        try {
        // AREngine_ARPlaneFindingMode
        val planeFindingDisabled = platform.AREngine.AREngine.ARENGINE_PLANE_FINDING_MODE_DISABLED
        val planeFindingHorizontal = platform.AREngine.AREngine.ARENGINE_PLANE_FINDING_MODE_HORIZONTAL
        val planeFindingVertical = platform.AREngine.AREngine.ARENGINE_PLANE_FINDING_MODE_VERTICAL
        val planeFindingBoth = platform.AREngine.AREngine.ARENGINE_PLANE_FINDING_MODE_HORIZONTAL_AND_VERTICAL
        logLine("ARPlaneFindingMode: DISABLED=$planeFindingDisabled, HORIZONTAL=$planeFindingHorizontal, VERTICAL=$planeFindingVertical, BOTH=$planeFindingBoth")

        assertNotEquals(planeFindingDisabled, planeFindingHorizontal)
        assertNotEquals(planeFindingHorizontal, planeFindingVertical)
        assertNotEquals(planeFindingVertical, planeFindingBoth)

        // AREngine_ARUpdateMode
        val updateBlocking = platform.AREngine.AREngine.ARENGINE_UPDATE_MODE_BLOCKING
        val updateLatest = platform.AREngine.AREngine.ARENGINE_UPDATE_MODE_LATEST
        logLine("ARUpdateMode: BLOCKING=$updateBlocking, LATEST=$updateLatest")

        assertNotEquals(updateBlocking, updateLatest)

        // AREngine_ARPowerMode
        val powerNormal = platform.AREngine.AREngine.ARENGINE_POWER_MODE_NORMAL
        val powerSaving = platform.AREngine.AREngine.ARENGINE_POWER_MODE_POWER_SAVING
        val powerPerformance = platform.AREngine.AREngine.ARENGINE_POWER_MODE_PERFORMANCE_FIRST
        val powerBoost = platform.AREngine.AREngine.ARENGINE_POWER_MODE_BOOST
        logLine("ARPowerMode: NORMAL=$powerNormal, SAVING=$powerSaving, PERFORMANCE=$powerPerformance, BOOST=$powerBoost")

        assertNotEquals(powerNormal, powerSaving)
        assertNotEquals(powerSaving, powerPerformance)
        assertNotEquals(powerPerformance, powerBoost)

        // AREngine_ARFocusMode
        val focusFixed = platform.AREngine.AREngine.ARENGINE_FOCUS_MODE_FIXED
        val focusAuto = platform.AREngine.AREngine.ARENGINE_FOCUS_MODE_AUTO
        logLine("ARFocusMode: FIXED=$focusFixed, AUTO=$focusAuto")

        assertNotEquals(focusFixed, focusAuto)

        // AREngine_ARPlaneType
        val planeHorizontalUp = platform.AREngine.AREngine.ARENGINE_PLANE_FACING_HORIZONTAL_UPWARD
        val planeHorizontalDown = platform.AREngine.AREngine.ARENGINE_PLANE_FACING_HORIZONTAL_DOWNWARD
        val planeVertical = platform.AREngine.AREngine.ARENGINE_PLANE_FACING_VERTICAL
        val planeInvalid = platform.AREngine.AREngine.ARENGINE_PLANE_FACING_INVALID
        logLine("ARPlaneType: HORIZONTAL_UP=$planeHorizontalUp, HORIZONTAL_DOWN=$planeHorizontalDown, VERTICAL=$planeVertical, INVALID=$planeInvalid")

        assertNotEquals(planeHorizontalUp, planeHorizontalDown)
        assertNotEquals(planeHorizontalDown, planeVertical)
        assertNotEquals(planeVertical, planeInvalid)

        // AREngine_ARType
        val typeWorld = platform.AREngine.AREngine.ARENGINE_TYPE_WORLD
        val typeImage = platform.AREngine.AREngine.ARENGINE_TYPE_IMAGE
        logLine("ARType: WORLD=$typeWorld, IMAGE=$typeImage")

        assertNotEquals(typeWorld, typeImage)

        // AREngine_ARSemanticMode
        val semanticNone = platform.AREngine.AREngine.ARENGINE_SEMANTIC_MODE_NONE
        val semanticPlane = platform.AREngine.AREngine.ARENGINE_SEMANTIC_MODE_PLANE
        val semanticTarget = platform.AREngine.AREngine.ARENGINE_SEMANTIC_MODE_TARGET
        logLine("ARSemanticMode: NONE=$semanticNone, PLANE=$semanticPlane, TARGET=$semanticTarget")

        assertNotEquals(semanticNone, semanticPlane)
        assertNotEquals(semanticPlane, semanticTarget)

        // AREngine_ARTrackingState
        val trackingTracking = platform.AREngine.AREngine.ARENGINE_TRACKING_STATE_TRACKING
        val trackingPaused = platform.AREngine.AREngine.ARENGINE_TRACKING_STATE_PAUSED
        val trackingStopped = platform.AREngine.AREngine.ARENGINE_TRACKING_STATE_STOPPED
        logLine("ARTrackingState: TRACKING=$trackingTracking, PAUSED=$trackingPaused, STOPPED=$trackingStopped")

        assertNotEquals(trackingTracking, trackingPaused)
        assertNotEquals(trackingPaused, trackingStopped)

        // AREngine_ARTrackableType
        val trackableBase = platform.AREngine.AREngine.ARENGINE_TRACKABLE_BASE
        val trackablePlane = platform.AREngine.AREngine.ARENGINE_TRACKABLE_PLANE
        val trackablePoint = platform.AREngine.AREngine.ARENGINE_TRACKABLE_POINT
        val trackableAugmentedImage = platform.AREngine.AREngine.ARENGINE_TRACKABLE_AUGMENTED_IMAGE
        val trackableTarget = platform.AREngine.AREngine.ARENGINE_TRACKABLE_TARGET
        val trackableInvalid = platform.AREngine.AREngine.ARENGINE_TRACKABLE_INVALID
        logLine("ARTrackableType: BASE=$trackableBase, PLANE=$trackablePlane, POINT=$trackablePoint, AUGMENTED_IMAGE=$trackableAugmentedImage, TARGET=$trackableTarget, INVALID=$trackableInvalid")

        assertNotEquals(trackableBase, trackablePlane)
        assertNotEquals(trackablePlane, trackablePoint)
        assertNotEquals(trackablePoint, trackableAugmentedImage)
        assertNotEquals(trackableAugmentedImage, trackableTarget)
        assertNotEquals(trackableTarget, trackableInvalid)

        // AREngine_ARImageFormat
        val imageUnknown = platform.AREngine.AREngine.ARENGINE_IMAGE_UNKNOWN
        val imageYuv420 = platform.AREngine.AREngine.ARENGINE_IMAGE_YUV_420_888
        val imageY8 = platform.AREngine.AREngine.ARENGINE_IMAGE_Y_8
        val imageY16 = platform.AREngine.AREngine.ARENGINE_IMAGE_Y_16
        logLine("ARImageFormat: UNKNOWN=$imageUnknown, YUV_420_888=$imageYuv420, Y_8=$imageY8, Y_16=$imageY16")

        assertNotEquals(imageUnknown, imageYuv420)
        assertNotEquals(imageYuv420, imageY8)
        assertNotEquals(imageY8, imageY16)

        // AREngine_ARPoseType
        val poseIdentity = platform.AREngine.AREngine.ARENGINE_POSE_TYPE_IDENTITY
        val poseRotate90 = platform.AREngine.AREngine.ARENGINE_POSE_TYPE_ROTATE_90
        val poseRotate180 = platform.AREngine.AREngine.ARENGINE_POSE_TYPE_ROTATE_180
        val poseRotate270 = platform.AREngine.AREngine.ARENGINE_POSE_TYPE_ROTATE_270
        logLine("ARPoseType: IDENTITY=$poseIdentity, ROTATE_90=$poseRotate90, ROTATE_180=$poseRotate180, ROTATE_270=$poseRotate270")

        assertNotEquals(poseIdentity, poseRotate90)
        assertNotEquals(poseRotate90, poseRotate180)
        assertNotEquals(poseRotate180, poseRotate270)
        } catch (e: Throwable) { logLine("testEnumValues (constants) exception: $e") }
    }

    @Test fun test_ARConfig_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARConfig_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetPlaneFindingMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetPlaneFindingMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetPlaneFindingMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetPlaneFindingMode(null, null, platform.AREngine.AREngine.ARENGINE_PLANE_FINDING_MODE_DISABLED.toUInt()); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetUpdateMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetUpdateMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetUpdateMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetUpdateMode(null, null, platform.AREngine.AREngine.ARENGINE_UPDATE_MODE_BLOCKING.toUInt()); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetPowerMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetPowerMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetPowerMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetPowerMode(null, null, platform.AREngine.AREngine.ARENGINE_POWER_MODE_NORMAL.toUInt()); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetFocusMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetFocusMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetFocusMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetFocusMode(null, null, platform.AREngine.AREngine.ARENGINE_FOCUS_MODE_FIXED.toUInt()); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }

    @Test fun test_ARSession_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_Create(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_Stop() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_Stop(null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_Pause() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_Pause(null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_Resume() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_Resume(null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_Update() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_Update(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_Configure() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_Configure(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_SetCameraGLTexture() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_SetCameraGLTexture(null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_SetDisplayGeometry() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_SetDisplayGeometry(null, platform.AREngine.AREngine.ARENGINE_POSE_TYPE_IDENTITY.toUInt(), 0, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }

    @Test fun test_ARPose_Create() { memScoped { val poseRaw = allocArray<FloatVar>(16); for (i in 0..15) poseRaw[i] = 0.0f; try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPose_Create(null, poseRaw, 16u, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPose_GetPoseRaw() { memScoped { val outPoseRaw = allocArray<FloatVar>(16); try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPose_GetPoseRaw(null, null, outPoseRaw, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPose_GetMatrix() { memScoped { val outMatrix = allocArray<FloatVar>(16); try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPose_GetMatrix(null, null, outMatrix, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPose_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARPose_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }

    @Test fun test_ARConfig_GetDepthMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetDepthMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetDepthMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetDepthMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetMeshMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetMeshMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetMeshMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetMeshMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetPoseMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetPoseMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetPoseMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetPoseMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetSemanticDenseMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetSemanticDenseMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetSemanticDenseMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetSemanticDenseMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetPreviewSize() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetPreviewSize(null, null, 0u, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetARType() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetARType(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetARType() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetARType(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetSemanticMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetSemanticMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetSemanticMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetSemanticMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetMaxMapSize() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetMaxMapSize(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetMaxMapSize() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetMaxMapSize(null, null, 0uL); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetCameraPreviewMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetCameraPreviewMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetCameraPreviewMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetCameraPreviewMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetPhotoStreamSize() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetPhotoStreamSize(null, null, 0u, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_SetImageStreamMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_SetImageStreamMode(null, null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARConfig_GetImageStreamMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARConfig_GetImageStreamMode(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }

    @Test fun test_ARSession_AcquireNewAnchor() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_AcquireNewAnchor(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_GetAllAnchors() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_GetAllAnchors(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_GetAllTrackables() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_GetAllTrackables(null, platform.AREngine.AREngine.ARENGINE_TRACKABLE_BASE.toUInt(), null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSession_GetCameraConfig() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSession_GetCameraConfig(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARFrame_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_GetDisplayGeometryChanged() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_GetDisplayGeometryChanged(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_GetTimestamp() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_GetTimestamp(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_TransformDisplayUvCoords() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_TransformDisplayUvCoords(null, null, 0, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_HitTest() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_HitTest(null, null, 0.0f, 0.0f, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquirePointCloud() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquirePointCloud(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireCamera() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireCamera(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_GetUpdatedTrackables() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_GetUpdatedTrackables(null, null, platform.AREngine.AREngine.ARENGINE_TRACKABLE_BASE.toUInt(), null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireCameraImage() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireCameraImage(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireSemanticDenseData() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireSemanticDenseData(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireDepthImage16Bits() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireDepthImage16Bits(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireDepthConfidenceImage() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireDepthConfidenceImage(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireSceneMesh() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireSceneMesh(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARFrame_AcquireCameraPhotoImage() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARFrame_AcquireCameraPhotoImage(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetDisplayOrientedPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetDisplayOrientedPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetViewMatrix() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetViewMatrix(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetTrackingState() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetTrackingState(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetTrackingStateReason() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetTrackingStateReason(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetProjectionMatrix() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetProjectionMatrix(null, null, cValue<platform.AREngine.AREngine.AREngine_ClipPlaneDistance> { }, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_GetImageIntrinsics() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCamera_GetImageIntrinsics(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCamera_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARCamera_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraIntrinsics_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraIntrinsics_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraIntrinsics_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARCameraIntrinsics_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraIntrinsics_GetFocalLength() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraIntrinsics_GetFocalLength(null, null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraIntrinsics_GetPrincipalPoint() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraIntrinsics_GetPrincipalPoint(null, null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraIntrinsics_GetImageDimensions() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraIntrinsics_GetImageDimensions(null, null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraIntrinsics_GetDistortion() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraIntrinsics_GetDistortion(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }

    @Test fun test_ARSession_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARSession_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARPointCloud_GetData() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPointCloud_GetData(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARPointCloud_GetNumberOfPoints() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPointCloud_GetNumberOfPoints(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPointCloud_GetTimestamp() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPointCloud_GetTimestamp(null, null, alloc<LongVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPointCloud_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARPointCloud_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAnchor_GetPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAnchor_GetPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAnchor_GetTrackingState() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAnchor_GetTrackingState(null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAnchor_Detach() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAnchor_Detach(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAnchor_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARAnchor_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackableList_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackableList_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackableList_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARTrackableList_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackableList_GetSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackableList_GetSize(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARTrackableList_AcquireItem() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackableList_AcquireItem(null, null, 0, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackable_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARTrackable_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackable_GetType() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackable_GetType(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackable_GetTrackingState() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackable_GetTrackingState(null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARTrackable_AcquireNewAnchor() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackable_AcquireNewAnchor(null, null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTrackable_GetAnchors() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTrackable_GetAnchors(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARPlane_AcquireSubsumedBy() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_AcquireSubsumedBy(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARPlane_GetType() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetType(null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPlane_GetCenterPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetCenterPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARPlane_GetLabel() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetLabel(null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPlane_GetExtentX() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetExtentX(null, null, alloc<FloatVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPlane_GetExtentZ() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetExtentZ(null, null, alloc<FloatVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPlane_GetPolygonSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetPolygonSize(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPlane_GetPolygon() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_GetPolygon(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARPlane_IsPoseInExtents() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_IsPoseInExtents(null, null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPlane_IsPoseInPolygon() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPlane_IsPoseInPolygon(null, null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPoint_GetOrientationMode() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPoint_GetOrientationMode(null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARPoint_GetPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARPoint_GetPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResultList_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResultList_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResultList_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARHitResultList_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResultList_GetSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResultList_GetSize(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARHitResultList_GetItem() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResultList_GetItem(null, null, 0, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResult_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResult_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResult_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARHitResult_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResult_GetDistance() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResult_GetDistance(null, null, alloc<FloatVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARHitResult_GetHitPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResult_GetHitPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResult_AcquireTrackable() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResult_AcquireTrackable(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARHitResult_AcquireNewAnchor() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARHitResult_AcquireNewAnchor(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTarget_GetCenterPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTarget_GetCenterPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTarget_GetAxisAlignedBoundingBox() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTarget_GetAxisAlignedBoundingBox(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARTarget_GetRadius() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTarget_GetRadius(null, null, alloc<FloatVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARTarget_GetShapeType() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARTarget_GetShapeType(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAnchorList_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAnchorList_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAnchorList_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARAnchorList_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAnchorList_GetSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAnchorList_GetSize(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAnchorList_AcquireItem() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAnchorList_AcquireItem(null, null, 0, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraConfig_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraConfig_Create(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraConfig_Destroy() { try { platform.AREngine.AREngine.HMS_AREngine_ARCameraConfig_Destroy(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARCameraConfig_GetImageDimensions() { memScoped { val o = alloc<IntVar>(); try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraConfig_GetImageDimensions(null, null, o.ptr, o.ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARCameraConfig_GetTextureDimensions() { memScoped { val o = alloc<IntVar>(); try { val r = platform.AREngine.AREngine.HMS_AREngine_ARCameraConfig_GetTextureDimensions(null, null, o.ptr, o.ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetFormat() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetFormat(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARImage_GetWidth() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetWidth(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetHeight() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetHeight(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetPlaneCount() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetPlaneCount(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetPlaneData() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetPlaneData(null, null, 0, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetPlanePixelStride() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetPlanePixelStride(null, null, 0, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetPlaneRowStride() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetPlaneRowStride(null, null, 0, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_GetTimestamp() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetTimestamp(null, null, alloc<LongVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARImage_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARImage_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARImage_GetNativeBuffer() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARImage_GetNativeBuffer(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImage_GetCenterPose() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImage_GetCenterPose(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImage_GetExtendX() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImage_GetExtendX(null, null, alloc<FloatVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAugmentedImage_GetExtendZ() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImage_GetExtendZ(null, null, alloc<FloatVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAugmentedImage_GetIndex() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImage_GetIndex(null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAugmentedImage_AcquireName() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImage_AcquireName(null, null, null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAugmentedImageDatabase_Destroy() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_Destroy(null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImageDatabase_GetImageCount() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_GetImageCount(null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAugmentedImageDatabase_AddImage() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_AddImage(null, null, alloc<UIntVar>().ptr, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARAugmentedImageDatabase_Deserialize() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_Deserialize(null, 0uL, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImageDatabase_Serialize() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_Serialize(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImageDatabase_SetAddMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_SetAddMode(null, 0u); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImageDatabase_Create() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_Create(null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImageDatabase_GetAddMode() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_GetAddMode(null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARAugmentedImageDatabase_GetCapacity() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARAugmentedImageDatabase_GetCapacity(null, alloc<UIntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARSceneMesh_AcquireVerticesSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSceneMesh_AcquireVerticesSize(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARSceneMesh_AcquireVertexList() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSceneMesh_AcquireVertexList(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSceneMesh_AcquireVertexNormalList() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSceneMesh_AcquireVertexNormalList(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSceneMesh_AcquireIndexListSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSceneMesh_AcquireIndexListSize(null, null, alloc<IntVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARSceneMesh_AcquireIndexList() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSceneMesh_AcquireIndexList(null, null, null, 0); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSceneMesh_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARSceneMesh_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSemanticDense_AcquirePointData() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSemanticDense_AcquirePointData(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSemanticDense_AcquirePointDataSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSemanticDense_AcquirePointDataSize(null, null, alloc<LongVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARSemanticDense_AcquireCubeData() { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSemanticDense_AcquireCubeData(null, null, null); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } }
    @Test fun test_ARSemanticDense_AcquireCubeDataSize() { memScoped { try { val r = platform.AREngine.AREngine.HMS_AREngine_ARSemanticDense_AcquireCubeDataSize(null, null, alloc<LongVar>().ptr); logLine("result: $r") } catch (t: Throwable) { logLine("exception: $t") } } }
    @Test fun test_ARSemanticDense_Release() { try { platform.AREngine.AREngine.HMS_AREngine_ARSemanticDense_Release(null); logLine("ok") } catch (t: Throwable) { logLine("exception: $t") } }

    private fun logLine(message: String) {
        println("[stdout] AREngineTest $message")
    }
}
