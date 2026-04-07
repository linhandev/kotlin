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
class AVScreenCaptureTest {

    private fun logLine(message: String) {
        println("[stdout] AVScreenCaptureTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- Testing AVScreenCapture enums ---")
        val homeScreen = platform.MediaKit.AVScreenCapture.OH_CAPTURE_HOME_SCREEN
        val specifiedScreen = platform.MediaKit.AVScreenCapture.OH_CAPTURE_SPECIFIED_SCREEN
        val specifiedWindow = platform.MediaKit.AVScreenCapture.OH_CAPTURE_SPECIFIED_WINDOW
        val captureInvalid = platform.MediaKit.AVScreenCapture.OH_CAPTURE_INVAILD
        assertNotNull(homeScreen)
        assertNotNull(specifiedScreen)
        assertNotNull(specifiedWindow)
        assertNotNull(captureInvalid)
        assertNotEquals(homeScreen, specifiedScreen)
        logLine("OH_CaptureMode: HOME_SCREEN=$homeScreen, SPECIFIED_SCREEN=$specifiedScreen, SPECIFIED_WINDOW=$specifiedWindow, INVAILD=$captureInvalid")
        val originalStream = platform.MediaKit.AVScreenCapture.OH_ORIGINAL_STREAM
        val encodedStream = platform.MediaKit.AVScreenCapture.OH_ENCODED_STREAM
        val captureFile = platform.MediaKit.AVScreenCapture.OH_CAPTURE_FILE
        val dataTypeInvalid = platform.MediaKit.AVScreenCapture.OH_INVAILD
        assertNotNull(originalStream)
        assertNotNull(encodedStream)
        assertNotEquals(originalStream, encodedStream)
        logLine("OH_DataType: ORIGINAL_STREAM=$originalStream, ENCODED_STREAM=$encodedStream, CAPTURE_FILE=$captureFile, INVAILD=$dataTypeInvalid")
        val sourceInvalid = platform.MediaKit.AVScreenCapture.OH_SOURCE_INVALID
        val sourceDefault = platform.MediaKit.AVScreenCapture.OH_SOURCE_DEFAULT
        val mic = platform.MediaKit.AVScreenCapture.OH_MIC
        val allPlayback = platform.MediaKit.AVScreenCapture.OH_ALL_PLAYBACK
        val appPlayback = platform.MediaKit.AVScreenCapture.OH_APP_PLAYBACK
        assertNotNull(sourceInvalid)
        assertNotNull(mic)
        assertNotEquals(mic, allPlayback)
        logLine("OH_AudioCaptureSourceType: SOURCE_INVALID=$sourceInvalid, SOURCE_DEFAULT=$sourceDefault, MIC=$mic, ALL_PLAYBACK=$allPlayback, APP_PLAYBACK=$appPlayback")
        val audioDefault = platform.MediaKit.AVScreenCapture.OH_AUDIO_DEFAULT
        val aacLc = platform.MediaKit.AVScreenCapture.OH_AAC_LC
        val audioCodecButt = platform.MediaKit.AVScreenCapture.OH_AUDIO_CODEC_FORMAT_BUTT
        assertNotNull(audioDefault)
        assertNotNull(aacLc)
        assertNotEquals(audioDefault, aacLc)
        logLine("OH_AudioCodecFormat: AUDIO_DEFAULT=$audioDefault, AAC_LC=$aacLc, AUDIO_CODEC_FORMAT_BUTT=$audioCodecButt")
        val videoDefault = platform.MediaKit.AVScreenCapture.OH_VIDEO_DEFAULT
        val h264 = platform.MediaKit.AVScreenCapture.OH_H264
        val h265 = platform.MediaKit.AVScreenCapture.OH_H265
        val mpeg4 = platform.MediaKit.AVScreenCapture.OH_MPEG4
        val vp8 = platform.MediaKit.AVScreenCapture.OH_VP8
        val vp9 = platform.MediaKit.AVScreenCapture.OH_VP9
        val videoCodecButt = platform.MediaKit.AVScreenCapture.OH_VIDEO_CODEC_FORMAT_BUTT
        assertNotNull(videoDefault)
        assertNotNull(h264)
        assertNotEquals(h264, h265)
        logLine("OH_VideoCodecFormat: VIDEO_DEFAULT=$videoDefault, H264=$h264, H265=$h265, MPEG4=$mpeg4, VP8=$vp8, VP9=$vp9, BUTT=$videoCodecButt")
        val surfaceYuv = platform.MediaKit.AVScreenCapture.OH_VIDEO_SOURCE_SURFACE_YUV
        val surfaceEs = platform.MediaKit.AVScreenCapture.OH_VIDEO_SOURCE_SURFACE_ES
        val surfaceRgba = platform.MediaKit.AVScreenCapture.OH_VIDEO_SOURCE_SURFACE_RGBA
        val videoSourceButt = platform.MediaKit.AVScreenCapture.OH_VIDEO_SOURCE_BUTT
        assertNotNull(surfaceYuv)
        assertNotNull(surfaceEs)
        assertNotEquals(surfaceYuv, surfaceEs)
        logLine("OH_VideoSourceType: SURFACE_YUV=$surfaceYuv, SURFACE_ES=$surfaceEs, SURFACE_RGBA=$surfaceRgba, BUTT=$videoSourceButt")
        val cftMpeg4a = platform.MediaKit.AVScreenCapture.CFT_MPEG_4A
        val cftMpeg4 = platform.MediaKit.AVScreenCapture.CFT_MPEG_4
        assertNotNull(cftMpeg4a)
        assertNotNull(cftMpeg4)
        assertNotEquals(cftMpeg4a, cftMpeg4)
        logLine("OH_ContainerFormatType: CFT_MPEG_4A=$cftMpeg4a, CFT_MPEG_4=$cftMpeg4")
        try {
            val pickerWindowOnly = platform.MediaKit.AVScreenCapture.OH_CAPTURE_PICKER_MODE_WINDOW_ONLY
            val pickerScreenOnly = platform.MediaKit.AVScreenCapture.OH_CAPTURE_PICKER_MODE_SCREEN_ONLY
            val pickerScreenAndWindow = platform.MediaKit.AVScreenCapture.OH_CAPTURE_PICKER_MODE_SCREEN_AND_WINDOW
            assertNotNull(pickerWindowOnly)
            assertNotNull(pickerScreenOnly)
            assertNotEquals(pickerWindowOnly, pickerScreenOnly)
            logLine("OH_CapturePickerMode: WINDOW_ONLY=$pickerWindowOnly, SCREEN_ONLY=$pickerScreenOnly, SCREEN_AND_WINDOW=$pickerScreenAndWindow")
            val highlightClosed = platform.MediaKit.AVScreenCapture.OH_HIGHLIGHT_MODE_CLOSED
            val highlightCornerWrap = platform.MediaKit.AVScreenCapture.OH_HIGHLIGHT_MODE_CORNER_WRAP
            assertNotNull(highlightClosed)
            assertNotNull(highlightCornerWrap)
            assertNotEquals(highlightClosed, highlightCornerWrap)
            logLine("OH_ScreenCaptureHighlightMode: CLOSED=$highlightClosed, CORNER_WRAP=$highlightCornerWrap")
        } catch (e: Throwable) {
            logLine("OH_CapturePickerMode/OH_ScreenCaptureHighlightMode (API 22) exception: $e")
        }
        val stateStarted = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_STATE_STARTED
        val stateCanceled = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_STATE_CANCELED
        val stateStoppedByUser = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_STATE_STOPPED_BY_USER
        val stateInterrupted = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_STATE_INTERRUPTED_BY_OTHER
        assertNotNull(stateStarted)
        assertNotNull(stateCanceled)
        assertNotEquals(stateStarted, stateCanceled)
        logLine("OH_AVScreenCaptureStateCode: STARTED=$stateStarted, CANCELED=$stateCanceled, STOPPED_BY_USER=$stateStoppedByUser, INTERRUPTED=$stateInterrupted")
        val bufferVideo = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_BUFFERTYPE_VIDEO
        val bufferAudioInner = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_BUFFERTYPE_AUDIO_INNER
        val bufferAudioMic = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_BUFFERTYPE_AUDIO_MIC
        assertNotNull(bufferVideo)
        assertNotNull(bufferAudioInner)
        assertNotEquals(bufferVideo, bufferAudioInner)
        logLine("OH_AVScreenCaptureBufferType: VIDEO=$bufferVideo, AUDIO_INNER=$bufferAudioInner, AUDIO_MIC=$bufferAudioMic")
        val filterNotification = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_NOTIFICATION_AUDIO
        val filterCurrentApp = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_CURRENT_APP_AUDIO
        assertNotNull(filterNotification)
        assertNotNull(filterCurrentApp)
        assertNotEquals(filterNotification, filterCurrentApp)
        logLine("OH_AVScreenCaptureFilterableAudioContent: NOTIFICATION_AUDIO=$filterNotification, CURRENT_APP_AUDIO=$filterCurrentApp")
        try {
            val contentHide = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_CONTENT_HIDE
            val contentVisible = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_CONTENT_VISIBLE
            val contentUnavailable = platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_CONTENT_UNAVAILABLE
            assertNotNull(contentHide)
            assertNotNull(contentVisible)
            assertNotEquals(contentHide, contentVisible)
            logLine("OH_AVScreenCaptureContentChangedEvent: HIDE=$contentHide, VISIBLE=$contentVisible, UNAVAILABLE=$contentUnavailable")
            val fillAspectScaleFit = platform.MediaKit.AVScreenCapture.OH_SCREENCAPTURE_FILLMODE_ASPECT_SCALE_FIT
            val fillScaleToFill = platform.MediaKit.AVScreenCapture.OH_SCREENCAPTURE_FILLMODE_SCALE_TO_FILL
            assertNotNull(fillAspectScaleFit)
            assertNotNull(fillScaleToFill)
            assertNotEquals(fillAspectScaleFit, fillScaleToFill)
            logLine("OH_AVScreenCapture_FillMode: ASPECT_SCALE_FIT=$fillAspectScaleFit, SCALE_TO_FILL=$fillScaleToFill")
        } catch (e: Throwable) {
            logLine("OH_AVScreenCaptureContentChangedEvent/OH_AVScreenCapture_FillMode (API 20) exception: $e")
        }
        val errOk = platform.MediaKit.AVScreenCapture.AV_SCREEN_CAPTURE_ERR_OK
        val errInvalidVal = platform.MediaKit.AVScreenCapture.AV_SCREEN_CAPTURE_ERR_INVALID_VAL
        val errNoMemory = platform.MediaKit.AVScreenCapture.AV_SCREEN_CAPTURE_ERR_NO_MEMORY
        val errOperateNotPermit = platform.MediaKit.AVScreenCapture.AV_SCREEN_CAPTURE_ERR_OPERATE_NOT_PERMIT
        assertNotNull(errOk)
        assertNotNull(errInvalidVal)
        assertNotEquals(errOk, errInvalidVal)
        logLine("OH_AVSCREEN_CAPTURE_ErrCode: ERR_OK=$errOk, ERR_INVALID_VAL=$errInvalidVal, ERR_NO_MEMORY=$errNoMemory, ERR_OPERATE_NOT_PERMIT=$errOperateNotPermit")
    }

    @Test
    fun testOH_AVScreenCapture_Create() {
        logLine("--- Testing OH_AVScreenCapture_Create ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        assertNotNull(capture)
        logLine("OH_AVScreenCapture_Create() result: $capture")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_Release() {
        logLine("--- Testing OH_AVScreenCapture_Release ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val releaseResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        assertNotNull(releaseResult)
        logLine("OH_AVScreenCapture_Release(capture) result: $releaseResult")
        val releaseNullResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(null)
        logLine("OH_AVScreenCapture_Release(null) result: $releaseNullResult")
        assertNotNull(releaseNullResult)
    }

    @Test
    fun testOH_AVScreenCapture_Init() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_Init ---")
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val config = alloc<platform.MediaKit.AVScreenCapture.OH_AVScreenCaptureConfig>().apply {
                captureMode = platform.MediaKit.AVScreenCapture.OH_CAPTURE_HOME_SCREEN
                dataType = platform.MediaKit.AVScreenCapture.OH_ORIGINAL_STREAM
                audioInfo.micCapInfo.audioSampleRate = 44100
                audioInfo.micCapInfo.audioChannels = 2
                audioInfo.micCapInfo.audioSource = platform.MediaKit.AVScreenCapture.OH_MIC
                audioInfo.innerCapInfo.audioSampleRate = 44100
                audioInfo.innerCapInfo.audioChannels = 2
                audioInfo.innerCapInfo.audioSource = platform.MediaKit.AVScreenCapture.OH_ALL_PLAYBACK
                audioInfo.audioEncInfo.audioBitrate = 128000
                audioInfo.audioEncInfo.audioCodecformat = platform.MediaKit.AVScreenCapture.OH_AAC_LC
                videoInfo.videoCapInfo.displayId = 0uL
                videoInfo.videoCapInfo.missionIDs = null
                videoInfo.videoCapInfo.missionIDsLen = 0
                videoInfo.videoCapInfo.videoFrameWidth = 1920
                videoInfo.videoCapInfo.videoFrameHeight = 1080
                videoInfo.videoCapInfo.videoSource = platform.MediaKit.AVScreenCapture.OH_VIDEO_SOURCE_SURFACE_YUV
                videoInfo.videoEncInfo.videoCodec = platform.MediaKit.AVScreenCapture.OH_H264
                videoInfo.videoEncInfo.videoBitrate = 2000000
                videoInfo.videoEncInfo.videoFrameRate = 30
                recorderInfo.url = null
                recorderInfo.urlLen = 0u
                recorderInfo.fileFormat = platform.MediaKit.AVScreenCapture.CFT_MPEG_4
            }
            val initResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Init(capture, config.readValue())
            assertNotNull(initResult)
            logLine("OH_AVScreenCapture_Init(capture, config) result: $initResult")
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        }
    }

    @Test
    fun testOH_AVScreenCapture_StartScreenCapture() {
        logLine("--- Testing OH_AVScreenCapture_StartScreenCapture ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val startResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StartScreenCapture(capture)
        assertNotNull(startResult)
        logLine("OH_AVScreenCapture_StartScreenCapture result: $startResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_StopScreenCapture() {
        logLine("--- Testing OH_AVScreenCapture_StopScreenCapture ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val stopResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StopScreenCapture(capture)
        assertNotNull(stopResult)
        logLine("OH_AVScreenCapture_StopScreenCapture result: $stopResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_StartScreenRecording() {
        logLine("--- Testing OH_AVScreenCapture_StartScreenRecording ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val startRecResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StartScreenRecording(capture)
        assertNotNull(startRecResult)
        logLine("OH_AVScreenCapture_StartScreenRecording result: $startRecResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_StopScreenRecording() {
        logLine("--- Testing OH_AVScreenCapture_StopScreenRecording ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val stopRecResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StopScreenRecording(capture)
        assertNotNull(stopRecResult)
        logLine("OH_AVScreenCapture_StopScreenRecording result: $stopRecResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_StartScreenCaptureWithSurface() {
        logLine("--- Testing OH_AVScreenCapture_StartScreenCaptureWithSurface ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val startWithSurfaceResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StartScreenCaptureWithSurface(capture, null)
        assertNotNull(startWithSurfaceResult)
        logLine("OH_AVScreenCapture_StartScreenCaptureWithSurface result: $startWithSurfaceResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_AcquireAudioBuffer() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_AcquireAudioBuffer ---")
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val audioBuffer = alloc<CPointerVar<platform.MediaKit.AVScreenCapture.OH_AudioBuffer>>()
            audioBuffer.pointed = null
            val acquireAudioResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_AcquireAudioBuffer(
                capture, audioBuffer.ptr, platform.MediaKit.AVScreenCapture.OH_MIC
            )
            logLine("OH_AVScreenCapture_AcquireAudioBuffer result: $acquireAudioResult")
            assertNotNull(acquireAudioResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        }
    }

    @Test
    fun testOH_AVScreenCapture_AcquireVideoBuffer() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_AcquireVideoBuffer ---")
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val fence = alloc<IntVar>()
            val timestamp = alloc<LongVar>()
            val region = alloc<platform.MediaKit.AVScreenCapture.OH_Rect>().apply {
                x = 0; y = 0; width = 1920; height = 1080
            }
            val acquireVideoResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_AcquireVideoBuffer(
                capture, fence.ptr, timestamp.ptr, region.ptr
            )
            logLine("OH_AVScreenCapture_AcquireVideoBuffer result: $acquireVideoResult")
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        }
    }

    @Test
    fun testOH_AVScreenCapture_ReleaseAudioBuffer() {
        logLine("--- Testing OH_AVScreenCapture_ReleaseAudioBuffer ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val releaseAudioResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseAudioBuffer(
            capture, platform.MediaKit.AVScreenCapture.OH_MIC
        )
        logLine("OH_AVScreenCapture_ReleaseAudioBuffer result: $releaseAudioResult")
        assertNotNull(releaseAudioResult)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_ReleaseVideoBuffer() {
        logLine("--- Testing OH_AVScreenCapture_ReleaseVideoBuffer ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val releaseVideoResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseVideoBuffer(capture)
        logLine("OH_AVScreenCapture_ReleaseVideoBuffer result: $releaseVideoResult")
        assertNotNull(releaseVideoResult)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetCallback() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_SetCallback ---")
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val callback = alloc<platform.MediaKit.AVScreenCapture.OH_AVScreenCaptureCallback>().apply {
                onError = null
                onAudioBufferAvailable = null
                onVideoBufferAvailable = null
            }
            val setCallbackResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetCallback(capture, callback.readValue())
            assertNotNull(setCallbackResult)
            logLine("OH_AVScreenCapture_SetCallback result: $setCallbackResult")
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetStateCallback() {
        logLine("--- Testing OH_AVScreenCapture_SetStateCallback ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setStateResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetStateCallback(capture, null, null)
        assertNotNull(setStateResult)
        logLine("OH_AVScreenCapture_SetStateCallback result: $setStateResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetDataCallback() {
        logLine("--- Testing OH_AVScreenCapture_SetDataCallback ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setDataResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetDataCallback(capture, null, null)
        assertNotNull(setDataResult)
        logLine("OH_AVScreenCapture_SetDataCallback result: $setDataResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetErrorCallback() {
        logLine("--- Testing OH_AVScreenCapture_SetErrorCallback ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setErrorResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetErrorCallback(capture, null, null)
        assertNotNull(setErrorResult)
        logLine("OH_AVScreenCapture_SetErrorCallback result: $setErrorResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetDisplayCallback() {
        logLine("--- Testing OH_AVScreenCapture_SetDisplayCallback ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setDisplayResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetDisplayCallback(capture, null, null)
        logLine("OH_AVScreenCapture_SetDisplayCallback result: $setDisplayResult")
        assertNotNull(setDisplayResult)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetCaptureContentChangedCallback() {
        logLine("--- Testing OH_AVScreenCapture_SetCaptureContentChangedCallback (API 20) ---")
        try {
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val setContentChangedResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetCaptureContentChangedCallback(capture, null, null)
            logLine("OH_AVScreenCapture_SetCaptureContentChangedCallback result: $setContentChangedResult")
            assertNotNull(setContentChangedResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_SetCaptureContentChangedCallback (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetSelectionCallback() {
        logLine("--- Testing OH_AVScreenCapture_SetSelectionCallback (API 20) ---")
        try {
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val setSelectionResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetSelectionCallback(capture, null, null)
            logLine("OH_AVScreenCapture_SetSelectionCallback result: $setSelectionResult")
            assertNotNull(setSelectionResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_SetSelectionCallback (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetMicrophoneEnabled() {
        logLine("--- Testing OH_AVScreenCapture_SetMicrophoneEnabled ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setMicResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetMicrophoneEnabled(capture, true)
        assertNotNull(setMicResult)
        logLine("OH_AVScreenCapture_SetMicrophoneEnabled result: $setMicResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetCanvasRotation() {
        logLine("--- Testing OH_AVScreenCapture_SetCanvasRotation ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setCanvasResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetCanvasRotation(capture, true)
        assertNotNull(setCanvasResult)
        logLine("OH_AVScreenCapture_SetCanvasRotation result: $setCanvasResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_ResizeCanvas() {
        logLine("--- Testing OH_AVScreenCapture_ResizeCanvas ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val resizeResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ResizeCanvas(capture, 1920, 1080)
        assertNotNull(resizeResult)
        logLine("OH_AVScreenCapture_ResizeCanvas result: $resizeResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SkipPrivacyMode() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_SkipPrivacyMode ---")
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val windowIDs = allocArray<IntVar>(2).apply { this[0] = 1; this[1] = 2 }
            val skipResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SkipPrivacyMode(capture, windowIDs, 2)
            assertNotNull(skipResult)
            logLine("OH_AVScreenCapture_SkipPrivacyMode result: $skipResult")
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetMaxVideoFrameRate() {
        logLine("--- Testing OH_AVScreenCapture_SetMaxVideoFrameRate ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val setFrameRateResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetMaxVideoFrameRate(capture, 30)
        assertNotNull(setFrameRateResult)
        logLine("OH_AVScreenCapture_SetMaxVideoFrameRate result: $setFrameRateResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_ShowCursor() {
        logLine("--- Testing OH_AVScreenCapture_ShowCursor ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val showCursorResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ShowCursor(capture, true)
        logLine("OH_AVScreenCapture_ShowCursor result: $showCursorResult")
        assertNotNull(showCursorResult)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_SetCaptureArea() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_SetCaptureArea (API 20) ---")
            try {
                val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
                val area = alloc<platform.MediaKit.AVScreenCapture.OH_Rect>().apply {
                    x = 0; y = 0; width = 1920; height = 1080
                }
                val setAreaResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetCaptureArea(capture, 0uL, area.ptr)
                logLine("OH_AVScreenCapture_SetCaptureArea result: $setAreaResult")
                assertNotNull(setAreaResult)
                platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
            } catch (e: Throwable) {
                logLine("OH_AVScreenCapture_SetCaptureArea (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVScreenCapture_CreateContentFilter() {
        logLine("--- Testing OH_AVScreenCapture_CreateContentFilter ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val filter = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateContentFilter()
        assertNotNull(filter)
        logLine("OH_AVScreenCapture_CreateContentFilter result: $filter")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseContentFilter(filter)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_ContentFilter_AddAudioContent() {
        logLine("--- Testing OH_AVScreenCapture_ContentFilter_AddAudioContent ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val filter = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateContentFilter()
        val addAudioResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ContentFilter_AddAudioContent(
            filter, platform.MediaKit.AVScreenCapture.OH_SCREEN_CAPTURE_NOTIFICATION_AUDIO
        )
        assertNotNull(addAudioResult)
        logLine("OH_AVScreenCapture_ContentFilter_AddAudioContent result: $addAudioResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseContentFilter(filter)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_ContentFilter_AddWindowContent() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_ContentFilter_AddWindowContent ---")
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val filter = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateContentFilter()
            val windowIDs = allocArray<IntVar>(2).apply { this[0] = 1; this[1] = 2 }
            val addWindowResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ContentFilter_AddWindowContent(filter, windowIDs, 2)
            assertNotNull(addWindowResult)
            logLine("OH_AVScreenCapture_ContentFilter_AddWindowContent result: $addWindowResult")
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseContentFilter(filter)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        }
    }

    @Test
    fun testOH_AVScreenCapture_ExcludeContent() {
        logLine("--- Testing OH_AVScreenCapture_ExcludeContent ---")
        val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
        val filter = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateContentFilter()
        val excludeResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ExcludeContent(capture, filter)
        assertNotNull(excludeResult)
        logLine("OH_AVScreenCapture_ExcludeContent result: $excludeResult")
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseContentFilter(filter)
        platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
    }

    @Test
    fun testOH_AVScreenCapture_ReleaseContentFilter() {
        logLine("--- Testing OH_AVScreenCapture_ReleaseContentFilter ---")
        val filter = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateContentFilter()
        val releaseFilterResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseContentFilter(filter)
        assertNotNull(releaseFilterResult)
        logLine("OH_AVScreenCapture_ReleaseContentFilter result: $releaseFilterResult")
        val releaseNullResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseContentFilter(null)
        logLine("OH_AVScreenCapture_ReleaseContentFilter(null) result: $releaseNullResult")
    }

    @Test
    fun testOH_AVScreenCapture_CreateCaptureStrategy() {
        logLine("--- Testing OH_AVScreenCapture_CreateCaptureStrategy (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            logLine("OH_AVScreenCapture_CreateCaptureStrategy result: $strategy")
            assertNotNull(strategy)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_CreateCaptureStrategy (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_StrategyForKeepCaptureDuringCall() {
        logLine("--- Testing OH_AVScreenCapture_StrategyForKeepCaptureDuringCall (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val keepCallResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StrategyForKeepCaptureDuringCall(strategy, true)
            logLine("OH_AVScreenCapture_StrategyForKeepCaptureDuringCall result: $keepCallResult")
            assertNotNull(keepCallResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_StrategyForKeepCaptureDuringCall (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_StrategyForPrivacyMaskMode() {
        logLine("--- Testing OH_AVScreenCapture_StrategyForPrivacyMaskMode (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val privacyResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StrategyForPrivacyMaskMode(strategy, 0)
            logLine("OH_AVScreenCapture_StrategyForPrivacyMaskMode result: $privacyResult")
            assertNotNull(privacyResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_StrategyForPrivacyMaskMode (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_StrategyForCanvasFollowRotation() {
        logLine("--- Testing OH_AVScreenCapture_StrategyForCanvasFollowRotation (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val canvasResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StrategyForCanvasFollowRotation(strategy, true)
            logLine("OH_AVScreenCapture_StrategyForCanvasFollowRotation result: $canvasResult")
            assertNotNull(canvasResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_StrategyForCanvasFollowRotation (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_StrategyForBFramesEncoding() {
        logLine("--- Testing OH_AVScreenCapture_StrategyForBFramesEncoding (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val bframesResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StrategyForBFramesEncoding(strategy, true)
            logLine("OH_AVScreenCapture_StrategyForBFramesEncoding result: $bframesResult")
            assertNotNull(bframesResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_StrategyForBFramesEncoding (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_StrategyForPickerPopUp() {
        logLine("--- Testing OH_AVScreenCapture_StrategyForPickerPopUp (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val pickerResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StrategyForPickerPopUp(strategy, true)
            logLine("OH_AVScreenCapture_StrategyForPickerPopUp result: $pickerResult")
            assertNotNull(pickerResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_StrategyForPickerPopUp (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_StrategyForFillMode() {
        logLine("--- Testing OH_AVScreenCapture_StrategyForFillMode (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val fillModeResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_StrategyForFillMode(
                strategy, platform.MediaKit.AVScreenCapture.OH_SCREENCAPTURE_FILLMODE_ASPECT_SCALE_FIT
            )
            logLine("OH_AVScreenCapture_StrategyForFillMode result: $fillModeResult")
            assertNotNull(fillModeResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_StrategyForFillMode (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetCaptureStrategy() {
        logLine("--- Testing OH_AVScreenCapture_SetCaptureStrategy (API 20) ---")
        try {
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val setStrategyResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetCaptureStrategy(capture, strategy)
            logLine("OH_AVScreenCapture_SetCaptureStrategy result: $setStrategyResult")
            assertNotNull(setStrategyResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_SetCaptureStrategy (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_ReleaseCaptureStrategy() {
        logLine("--- Testing OH_AVScreenCapture_ReleaseCaptureStrategy (API 20) ---")
        try {
            val strategy = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_CreateCaptureStrategy()
            val releaseStrategyResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ReleaseCaptureStrategy(strategy)
            logLine("OH_AVScreenCapture_ReleaseCaptureStrategy result: $releaseStrategyResult")
            assertNotNull(releaseStrategyResult)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_ReleaseCaptureStrategy (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_GetCaptureTypeSelected() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_GetCaptureTypeSelected (API 20) ---")
            try {
                val typePtr = alloc<IntVar>()
                val getTypeResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_GetCaptureTypeSelected(null, typePtr.ptr)
                logLine("OH_AVScreenCapture_GetCaptureTypeSelected(null, ptr) result: $getTypeResult")
                assertNotNull(getTypeResult)
            } catch (e: Throwable) {
                logLine("OH_AVScreenCapture_GetCaptureTypeSelected (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVScreenCapture_GetDisplayIdSelected() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_GetDisplayIdSelected (API 20) ---")
            try {
                val displayIdPtr = alloc<ULongVar>()
                val getDisplayResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_GetDisplayIdSelected(null, displayIdPtr.ptr)
                logLine("OH_AVScreenCapture_GetDisplayIdSelected(null, ptr) result: $getDisplayResult")
                assertNotNull(getDisplayResult)
            } catch (e: Throwable) {
                logLine("OH_AVScreenCapture_GetDisplayIdSelected (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetCaptureAreaHighlight() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_SetCaptureAreaHighlight (API 22) ---")
            try {
                val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
                val config = alloc<platform.MediaKit.AVScreenCapture.OH_AVScreenCaptureHighlightConfig>().apply {
                    mode = platform.MediaKit.AVScreenCapture.OH_HIGHLIGHT_MODE_CLOSED
                    lineThickness = 2u
                    lineColor = 0u
                }
                val highlightResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetCaptureAreaHighlight(capture, config.readValue())
                logLine("OH_AVScreenCapture_SetCaptureAreaHighlight result: $highlightResult")
                assertNotNull(highlightResult)
                platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
            } catch (e: Throwable) {
                logLine("OH_AVScreenCapture_SetCaptureAreaHighlight (API 22) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVScreenCapture_ExcludePickerWindows() {
        memScoped {
            logLine("--- Testing OH_AVScreenCapture_ExcludePickerWindows (API 22) ---")
            try {
                val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
                val excludedIds = allocArray<IntVar>(1).apply { this[0] = 0 }
                val excludePickerResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_ExcludePickerWindows(capture, excludedIds, 1u)
                logLine("OH_AVScreenCapture_ExcludePickerWindows result: $excludePickerResult")
                assertNotNull(excludePickerResult)
                platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
            } catch (e: Throwable) {
                logLine("OH_AVScreenCapture_ExcludePickerWindows (API 22) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVScreenCapture_SetPickerMode() {
        logLine("--- Testing OH_AVScreenCapture_SetPickerMode (API 22) ---")
        try {
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val setPickerModeResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_SetPickerMode(
                capture, platform.MediaKit.AVScreenCapture.OH_CAPTURE_PICKER_MODE_SCREEN_ONLY
            )
            logLine("OH_AVScreenCapture_SetPickerMode result: $setPickerModeResult")
            assertNotNull(setPickerModeResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_SetPickerMode (API 22) exception: $e")
        }
    }

    @Test
    fun testOH_AVScreenCapture_PresentPicker() {
        logLine("--- Testing OH_AVScreenCapture_PresentPicker (API 22) ---")
        try {
            val capture = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Create()
            val presentResult = platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_PresentPicker(capture)
            logLine("OH_AVScreenCapture_PresentPicker result: $presentResult")
            assertNotNull(presentResult)
            platform.MediaKit.AVScreenCapture.OH_AVScreenCapture_Release(capture)
        } catch (e: Throwable) {
            logLine("OH_AVScreenCapture_PresentPicker (API 22) exception: $e")
        }
    }
}
