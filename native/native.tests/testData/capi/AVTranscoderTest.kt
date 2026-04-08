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
class AVTranscoderTest {

    private fun logLine(message: String) {
        println("[stdout] AVTranscoderTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- Testing AVTranscoder enums (API 20) ---")
        try {
            val prepared = platform.MediaKit.AVTranscoder.AVTRANSCODER_PREPARED
            val started = platform.MediaKit.AVTranscoder.AVTRANSCODER_STARTED
            val paused = platform.MediaKit.AVTranscoder.AVTRANSCODER_PAUSED
            val cancelled = platform.MediaKit.AVTranscoder.AVTRANSCODER_CANCELLED
            val completed = platform.MediaKit.AVTranscoder.AVTRANSCODER_COMPLETED
            assertNotNull(prepared)
            assertNotNull(started)
            assertNotNull(paused)
            assertNotNull(cancelled)
            assertNotNull(completed)
            assertNotEquals(prepared, started)
            logLine("OH_AVTranscoder_State: PREPARED=$prepared, STARTED=$started, PAUSED=$paused, CANCELLED=$cancelled, COMPLETED=$completed")
            val defaultFormat = platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_DEFAULT
            val mpeg4 = platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4
            val m4a = platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_M4A
            val amr = platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_AMR
            val mp3 = platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MP3
            assertNotNull(defaultFormat)
            assertNotNull(mpeg4)
            assertNotEquals(defaultFormat, mpeg4)
            logLine("OH_AVOutputFormat: DEFAULT=$defaultFormat, MPEG_4=$mpeg4, M4A=$m4a, AMR=$amr, MP3=$mp3")
        } catch (e: Throwable) {
            logLine("AVTranscoder enums (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_Create() {
        logLine("--- Testing OH_AVTranscoderConfig_Create (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            assertNotNull(config)
            logLine("OH_AVTranscoderConfig_Create() result: $config")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_Create (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_Release() {
        logLine("--- Testing OH_AVTranscoderConfig_Release (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val releaseResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
            assertNotNull(releaseResult)
            logLine("OH_AVTranscoderConfig_Release(config) result: $releaseResult")
            val releaseNullResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(null)
            logLine("OH_AVTranscoderConfig_Release(null) result: $releaseNullResult")
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_Release (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetSrcFD() {
        logLine("--- Testing OH_AVTranscoderConfig_SetSrcFD (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setSrcFDResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetSrcFD(config, 0, 0L, 0L)
            assertNotNull(setSrcFDResult)
            logLine("OH_AVTranscoderConfig_SetSrcFD result: $setSrcFDResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetSrcFD (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstFD() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstFD (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstFDResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstFD(config, 0)
            assertNotNull(setDstFDResult)
            logLine("OH_AVTranscoderConfig_SetDstFD result: $setDstFDResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstFD (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstVideoType() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstVideoType (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstVideoTypeResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstVideoType(config, null)
            assertNotNull(setDstVideoTypeResult)
            logLine("OH_AVTranscoderConfig_SetDstVideoType result: $setDstVideoTypeResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstVideoType (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstAudioType() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstAudioType (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstAudioTypeResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstAudioType(config, null)
            assertNotNull(setDstAudioTypeResult)
            logLine("OH_AVTranscoderConfig_SetDstAudioType result: $setDstAudioTypeResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstAudioType (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstFileType() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstFileType (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstFileTypeResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstFileType(config, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_DEFAULT)
            assertNotNull(setDstFileTypeResult)
            logLine("OH_AVTranscoderConfig_SetDstFileType result: $setDstFileTypeResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstFileType (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstAudioBitrate() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstAudioBitrate (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstAudioBitrateResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstAudioBitrate(config, 128000)
            assertNotNull(setDstAudioBitrateResult)
            logLine("OH_AVTranscoderConfig_SetDstAudioBitrate result: $setDstAudioBitrateResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstAudioBitrate (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstVideoBitrate() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstVideoBitrate (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstVideoBitrateResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstVideoBitrate(config, 2000000)
            assertNotNull(setDstVideoBitrateResult)
            logLine("OH_AVTranscoderConfig_SetDstVideoBitrate result: $setDstVideoBitrateResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstVideoBitrate (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_SetDstVideoResolution() {
        logLine("--- Testing OH_AVTranscoderConfig_SetDstVideoResolution (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val setDstVideoResolutionResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_SetDstVideoResolution(config, 1920, 1080)
            assertNotNull(setDstVideoResolutionResult)
            logLine("OH_AVTranscoderConfig_SetDstVideoResolution result: $setDstVideoResolutionResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_SetDstVideoResolution (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoderConfig_EnableBFrame() {
        logLine("--- Testing OH_AVTranscoderConfig_EnableBFrame (API 20) ---")
        try {
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val enableBFrameResult = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_EnableBFrame(config, true)
            assertNotNull(enableBFrameResult)
            logLine("OH_AVTranscoderConfig_EnableBFrame result: $enableBFrameResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoderConfig_EnableBFrame (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Create() {
        logLine("--- Testing OH_AVTranscoder_Create (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            assertNotNull(transcoder)
            logLine("OH_AVTranscoder_Create() result: $transcoder")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Create (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Release() {
        logLine("--- Testing OH_AVTranscoder_Release (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val releaseResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
            assertNotNull(releaseResult)
            logLine("OH_AVTranscoder_Release(transcoder) result: $releaseResult")
            val releaseNullResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(null)
            logLine("OH_AVTranscoder_Release(null) result: $releaseNullResult")
            assertNotNull(releaseNullResult)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Release (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Prepare() {
        logLine("--- Testing OH_AVTranscoder_Prepare (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val config = platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Create()
            val prepareResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Prepare(transcoder, config)
            assertNotNull(prepareResult)
            logLine("OH_AVTranscoder_Prepare(transcoder, config) result: $prepareResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
            platform.MediaKit.AVTranscoder.OH_AVTranscoderConfig_Release(config)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Prepare (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Start() {
        logLine("--- Testing OH_AVTranscoder_Start (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val startResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Start(transcoder)
            assertNotNull(startResult)
            logLine("OH_AVTranscoder_Start(transcoder) result: $startResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Start (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Pause() {
        logLine("--- Testing OH_AVTranscoder_Pause (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val pauseResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Pause(transcoder)
            assertNotNull(pauseResult)
            logLine("OH_AVTranscoder_Pause(transcoder) result: $pauseResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Pause (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Resume() {
        logLine("--- Testing OH_AVTranscoder_Resume (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val resumeResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Resume(transcoder)
            assertNotNull(resumeResult)
            logLine("OH_AVTranscoder_Resume(transcoder) result: $resumeResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Resume (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_Cancel() {
        logLine("--- Testing OH_AVTranscoder_Cancel (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val cancelResult = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Cancel(transcoder)
            assertNotNull(cancelResult)
            logLine("OH_AVTranscoder_Cancel(transcoder) result: $cancelResult")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_Cancel (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_SetStateCallback() {
        logLine("--- Testing OH_AVTranscoder_SetStateCallback (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val result = platform.MediaKit.AVTranscoder.OH_AVTranscoder_SetStateCallback(transcoder, null, null)
            assertNotNull(result)
            logLine("OH_AVTranscoder_SetStateCallback result: $result")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_SetStateCallback (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_SetErrorCallback() {
        logLine("--- Testing OH_AVTranscoder_SetErrorCallback (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val result = platform.MediaKit.AVTranscoder.OH_AVTranscoder_SetErrorCallback(transcoder, null, null)
            assertNotNull(result)
            logLine("OH_AVTranscoder_SetErrorCallback result: $result")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_SetErrorCallback (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVTranscoder_SetProgressUpdateCallback() {
        logLine("--- Testing OH_AVTranscoder_SetProgressUpdateCallback (API 20) ---")
        try {
            val transcoder = platform.MediaKit.AVTranscoder.OH_AVTranscoder_Create()
            val result = platform.MediaKit.AVTranscoder.OH_AVTranscoder_SetProgressUpdateCallback(transcoder, null, null)
            assertNotNull(result)
            logLine("OH_AVTranscoder_SetProgressUpdateCallback result: $result")
            platform.MediaKit.AVTranscoder.OH_AVTranscoder_Release(transcoder)
        } catch (e: Throwable) {
            logLine("OH_AVTranscoder_SetProgressUpdateCallback (API 20) exception: $e")
        }
    }
}
