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
class AVRecorderTest {

    private fun logLine(message: String) {
        println("[stdout] AVRecorderTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- Testing AVRecorder enums (API 18) ---")
        try {
            val default = platform.MediaKit.AVRecorder.AVRECORDER_DEFAULT
            val mic = platform.MediaKit.AVRecorder.AVRECORDER_MIC
            val voiceRecognition = platform.MediaKit.AVRecorder.AVRECORDER_VOICE_RECOGNITION
            val voiceCommunication = platform.MediaKit.AVRecorder.AVRECORDER_VOICE_COMMUNICATION
            val voiceMessage = platform.MediaKit.AVRecorder.AVRECORDER_VOICE_MESSAGE
            val camcorder = platform.MediaKit.AVRecorder.AVRECORDER_CAMCORDER
            assertNotNull(default)
            assertNotNull(mic)
            assertNotEquals(default, mic)
            logLine("OH_AVRecorder_AudioSourceType: DEFAULT=$default, MIC=$mic, VOICE_RECOGNITION=$voiceRecognition, VOICE_COMMUNICATION=$voiceCommunication, VOICE_MESSAGE=$voiceMessage, CAMCORDER=$camcorder")
            val surfaceYuv = platform.MediaKit.AVRecorder.AVRECORDER_SURFACE_YUV
            val surfaceEs = platform.MediaKit.AVRecorder.AVRECORDER_SURFACE_ES
            assertNotNull(surfaceYuv)
            assertNotNull(surfaceEs)
            assertNotEquals(surfaceYuv, surfaceEs)
            logLine("OH_AVRecorder_VideoSourceType: SURFACE_YUV=$surfaceYuv, SURFACE_ES=$surfaceEs")
            val videoAvc = platform.MediaKit.AVRecorder.AVRECORDER_VIDEO_AVC
            val audioAac = platform.MediaKit.AVRecorder.AVRECORDER_AUDIO_AAC
            val audioMp3 = platform.MediaKit.AVRecorder.AVRECORDER_AUDIO_MP3
            val audioG711mu = platform.MediaKit.AVRecorder.AVRECORDER_AUDIO_G711MU
            val videoMpeg4 = platform.MediaKit.AVRecorder.AVRECORDER_VIDEO_MPEG4
            val videoHevc = platform.MediaKit.AVRecorder.AVRECORDER_VIDEO_HEVC
            val audioAmrNb = platform.MediaKit.AVRecorder.AVRECORDER_AUDIO_AMR_NB
            val audioAmrWb = platform.MediaKit.AVRecorder.AVRECORDER_AUDIO_AMR_WB
            logLine("OH_AVRecorder_CodecMimeType: VIDEO_AVC=$videoAvc, AUDIO_AAC=$audioAac, AUDIO_MP3=$audioMp3, AUDIO_G711MU=$audioG711mu, VIDEO_MPEG4=$videoMpeg4, VIDEO_HEVC=$videoHevc, AUDIO_AMR_NB=$audioAmrNb, AUDIO_AMR_WB=$audioAmrWb")
            val cftMpeg4 = platform.MediaKit.AVRecorder.AVRECORDER_CFT_MPEG_4
            val cftMpeg4a = platform.MediaKit.AVRecorder.AVRECORDER_CFT_MPEG_4A
            val cftAmr = platform.MediaKit.AVRecorder.AVRECORDER_CFT_AMR
            val cftMp3 = platform.MediaKit.AVRecorder.AVRECORDER_CFT_MP3
            val cftWav = platform.MediaKit.AVRecorder.AVRECORDER_CFT_WAV
            val cftAac = platform.MediaKit.AVRecorder.AVRECORDER_CFT_AAC
            logLine("OH_AVRecorder_ContainerFormatType: CFT_MPEG_4=$cftMpeg4, CFT_MPEG_4A=$cftMpeg4a, CFT_AMR=$cftAmr, CFT_MP3=$cftMp3, CFT_WAV=$cftWav, CFT_AAC=$cftAac")
            val idle = platform.MediaKit.AVRecorder.AVRECORDER_IDLE
            val prepared = platform.MediaKit.AVRecorder.AVRECORDER_PREPARED
            val started = platform.MediaKit.AVRecorder.AVRECORDER_STARTED
            val paused = platform.MediaKit.AVRecorder.AVRECORDER_PAUSED
            val stopped = platform.MediaKit.AVRecorder.AVRECORDER_STOPPED
            val released = platform.MediaKit.AVRecorder.AVRECORDER_RELEASED
            val error = platform.MediaKit.AVRecorder.AVRECORDER_ERROR
            assertNotNull(idle)
            assertNotNull(started)
            assertNotEquals(idle, started)
            logLine("OH_AVRecorder_State: IDLE=$idle, PREPARED=$prepared, STARTED=$started, PAUSED=$paused, STOPPED=$stopped, RELEASED=$released, ERROR=$error")
            val user = platform.MediaKit.AVRecorder.AVRECORDER_USER
            val background = platform.MediaKit.AVRecorder.AVRECORDER_BACKGROUND
            logLine("OH_AVRecorder_StateChangeReason: USER=$user, BACKGROUND=$background")
            val appCreate = platform.MediaKit.AVRecorder.AVRECORDER_APP_CREATE
            val autoCreateCameraScene = platform.MediaKit.AVRecorder.AVRECORDER_AUTO_CREATE_CAMERA_SCENE
            logLine("OH_AVRecorder_FileGenerationMode: APP_CREATE=$appCreate, AUTO_CREATE_CAMERA_SCENE=$autoCreateCameraScene")
        } catch (e: Throwable) {
            logLine("AVRecorder enums (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Create() {
        logLine("--- Testing OH_AVRecorder_Create (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            logLine("OH_AVRecorder_Create() result: ${if (recorder != null) "non-null" else "null"}")
            assertNotNull(recorder)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Create (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Release() {
        logLine("--- Testing OH_AVRecorder_Release (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val releaseResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
            logLine("OH_AVRecorder_Release(recorder) result: $releaseResult")
            assertNotNull(releaseResult)
            val releaseNullResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Release(null)
            logLine("OH_AVRecorder_Release(null) result: $releaseNullResult")
            assertNotNull(releaseNullResult)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Release (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Prepare() {
        memScoped {
            logLine("--- Testing OH_AVRecorder_Prepare (API 18) ---")
            try {
                val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
                val config = alloc<platform.MediaKit.AVRecorder.OH_AVRecorder_Config>().apply {
                    audioSourceType = platform.MediaKit.AVRecorder.AVRECORDER_DEFAULT
                    videoSourceType = platform.MediaKit.AVRecorder.AVRECORDER_SURFACE_YUV
                    profile.audioBitrate = 128000
                    profile.audioChannels = 2
                    profile.audioCodec = platform.MediaKit.AVRecorder.AVRECORDER_AUDIO_AAC
                    profile.audioSampleRate = 44100
                    profile.fileFormat = platform.MediaKit.AVRecorder.AVRECORDER_CFT_MPEG_4
                    profile.videoBitrate = 2000000
                    profile.videoCodec = platform.MediaKit.AVRecorder.AVRECORDER_VIDEO_AVC
                    profile.videoFrameWidth = 1920
                    profile.videoFrameHeight = 1080
                    profile.videoFrameRate = 30
                    profile.isHdr = false
                    profile.enableTemporalScale = false
                    url = null
                    fileGenerationMode = platform.MediaKit.AVRecorder.AVRECORDER_APP_CREATE
                    metadata.genre = null
                    metadata.videoOrientation = null
                    metadata.location.latitude = 0.0f
                    metadata.location.longitude = 0.0f
                    metadata.customInfo.key = null
                    metadata.customInfo.value = null
                    maxDuration = 0
                }
                val prepareResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Prepare(recorder, config.ptr)
                logLine("OH_AVRecorder_Prepare(recorder, config) result: $prepareResult")
                assertNotNull(prepareResult)
                platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
            } catch (e: Throwable) {
                logLine("OH_AVRecorder_Prepare (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVRecorder_GetAVRecorderConfig() {
        memScoped {
            logLine("--- Testing OH_AVRecorder_GetAVRecorderConfig (API 18) ---")
            try {
                val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
                val configPtr = alloc<CPointerVar<platform.MediaKit.AVRecorder.OH_AVRecorder_Config>>()
                val getConfigResult = platform.MediaKit.AVRecorder.OH_AVRecorder_GetAVRecorderConfig(recorder, configPtr.ptr)
                logLine("OH_AVRecorder_GetAVRecorderConfig(recorder, configPtr) result: $getConfigResult")
                assertNotNull(getConfigResult)
                platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
            } catch (e: Throwable) {
                logLine("OH_AVRecorder_GetAVRecorderConfig (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVRecorder_Start() {
        logLine("--- Testing OH_AVRecorder_Start (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val startResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Start(recorder)
            logLine("OH_AVRecorder_Start(recorder) result: $startResult")
            assertNotNull(startResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Start (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Pause() {
        logLine("--- Testing OH_AVRecorder_Pause (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val pauseResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Pause(recorder)
            logLine("OH_AVRecorder_Pause(recorder) result: $pauseResult")
            assertNotNull(pauseResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Pause (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Resume() {
        logLine("--- Testing OH_AVRecorder_Resume (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val resumeResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Resume(recorder)
            logLine("OH_AVRecorder_Resume(recorder) result: $resumeResult")
            assertNotNull(resumeResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Resume (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Stop() {
        logLine("--- Testing OH_AVRecorder_Stop (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val stopResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Stop(recorder)
            logLine("OH_AVRecorder_Stop(recorder) result: $stopResult")
            assertNotNull(stopResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Stop (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_Reset() {
        logLine("--- Testing OH_AVRecorder_Reset (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val resetResult = platform.MediaKit.AVRecorder.OH_AVRecorder_Reset(recorder)
            logLine("OH_AVRecorder_Reset(recorder) result: $resetResult")
            assertNotNull(resetResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_Reset (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_UpdateRotation() {
        logLine("--- Testing OH_AVRecorder_UpdateRotation (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val updateRotationResult = platform.MediaKit.AVRecorder.OH_AVRecorder_UpdateRotation(recorder, 90)
            logLine("OH_AVRecorder_UpdateRotation(recorder, 90) result: $updateRotationResult")
            assertNotNull(updateRotationResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_UpdateRotation (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_GetInputSurface() {
        logLine("--- Testing OH_AVRecorder_GetInputSurface (API 18) ---")
        try {
            val getInputSurfaceResult = platform.MediaKit.AVRecorder.OH_AVRecorder_GetInputSurface(null, null)
            logLine("OH_AVRecorder_GetInputSurface(null, null) result: $getInputSurfaceResult")
            assertNotNull(getInputSurfaceResult)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_GetInputSurface (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_GetAvailableEncoder() {
        memScoped {
            logLine("--- Testing OH_AVRecorder_GetAvailableEncoder (API 18) ---")
            try {
                val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
                val length = alloc<IntVar>()
                length.value = 0
                val getAvailableEncoderResult = platform.MediaKit.AVRecorder.OH_AVRecorder_GetAvailableEncoder(
                    recorder,
                    null,
                    length.ptr
                )
                logLine("OH_AVRecorder_GetAvailableEncoder(recorder, null, length) result: $getAvailableEncoderResult, length: ${length.value}")
                assertNotNull(getAvailableEncoderResult)
                platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
            } catch (e: Throwable) {
                logLine("OH_AVRecorder_GetAvailableEncoder (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AVRecorder_SetStateCallback() {
        logLine("--- Testing OH_AVRecorder_SetStateCallback (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val setStateCallbackResult = platform.MediaKit.AVRecorder.OH_AVRecorder_SetStateCallback(recorder, null, null)
            logLine("OH_AVRecorder_SetStateCallback(recorder, null, null) result: $setStateCallbackResult")
            assertNotNull(setStateCallbackResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_SetStateCallback (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_SetErrorCallback() {
        logLine("--- Testing OH_AVRecorder_SetErrorCallback (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val setErrorCallbackResult = platform.MediaKit.AVRecorder.OH_AVRecorder_SetErrorCallback(recorder, null, null)
            logLine("OH_AVRecorder_SetErrorCallback(recorder, null, null) result: $setErrorCallbackResult")
            assertNotNull(setErrorCallbackResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_SetErrorCallback (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_SetUriCallback() {
        logLine("--- Testing OH_AVRecorder_SetUriCallback (API 18) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val setUriCallbackResult = platform.MediaKit.AVRecorder.OH_AVRecorder_SetUriCallback(recorder, null, null)
            logLine("OH_AVRecorder_SetUriCallback(recorder, null, null) result: $setUriCallbackResult")
            assertNotNull(setUriCallbackResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_SetUriCallback (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_AVRecorder_SetWillMuteWhenInterrupted() {
        logLine("--- Testing OH_AVRecorder_SetWillMuteWhenInterrupted (API 20) ---")
        try {
            val recorder = platform.MediaKit.AVRecorder.OH_AVRecorder_Create()
            val setWillMuteResult = platform.MediaKit.AVRecorder.OH_AVRecorder_SetWillMuteWhenInterrupted(recorder, true)
            logLine("OH_AVRecorder_SetWillMuteWhenInterrupted(recorder, true) result: $setWillMuteResult")
            assertNotNull(setWillMuteResult)
            platform.MediaKit.AVRecorder.OH_AVRecorder_Release(recorder)
        } catch (e: Throwable) {
            logLine("OH_AVRecorder_SetWillMuteWhenInterrupted (API 20) exception: $e")
        }
    }
}
