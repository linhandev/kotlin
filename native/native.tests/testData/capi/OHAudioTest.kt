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
import platform.AudioKit.OHAudio.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHAudioTest {

    fun logLine(msg: String) = println("[stdout] OHAudioTest $msg")

    // ==================== 枚举 native_audio_common ====================
    @Test
    fun testEnum_OH_AudioCommon_Result() {
        assertEquals(AUDIOCOMMON_RESULT_SUCCESS.toInt(), 0)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM.toInt(), 6800101)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_NO_MEMORY.toInt(), 6800102)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_ILLEGAL_STATE.toInt(), 6800103)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_UNSUPPORTED.toInt(), 6800104)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_TIMEOUT.toInt(), 6800105)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_STREAM_LIMIT.toInt(), 6800201)
        assertEquals(AUDIOCOMMON_RESULT_ERROR_SYSTEM.toInt(), 6800301)
        logLine("OH_AudioCommon_Result passed")
    }

    @Test
    fun testEnum_OH_AudioScene() {
        assertEquals(AUDIO_SCENE_DEFAULT.toInt(), 0)
        assertEquals(AUDIO_SCENE_RINGING.toInt(), 1)
        assertEquals(AUDIO_SCENE_PHONE_CALL.toInt(), 2)
        assertEquals(AUDIO_SCENE_VOICE_CHAT.toInt(), 3)
        logLine("OH_AudioScene passed")
    }

    @Test
    fun testEnum_OH_AudioRingerMode() {
        assertEquals(AUDIO_RINGER_MODE_SILENT.toInt(), 0)
        assertEquals(AUDIO_RINGER_MODE_VIBRATE.toInt(), 1)
        assertEquals(AUDIO_RINGER_MODE_NORMAL.toInt(), 2)
        logLine("OH_AudioRingerMode passed")
    }

    // ==================== 枚举 native_audiostream_base ====================
    @Test
    fun testEnum_OH_AudioStream_Result() {
        assertEquals(AUDIOSTREAM_SUCCESS.toInt(), 0)
        assertEquals(AUDIOSTREAM_ERROR_INVALID_PARAM.toInt(), 1)
        assertEquals(AUDIOSTREAM_ERROR_ILLEGAL_STATE.toInt(), 2)
        assertEquals(AUDIOSTREAM_ERROR_SYSTEM.toInt(), 3)
        assertEquals(AUDIOSTREAM_ERROR_UNSUPPORTED_FORMAT.toInt(), 4)
        logLine("OH_AudioStream_Result passed")
    }

    @Test
    fun testEnum_OH_AudioStream_Type() {
        assertEquals(AUDIOSTREAM_TYPE_RENDERER.toInt(), 1)
        assertEquals(AUDIOSTREAM_TYPE_CAPTURER.toInt(), 2)
        logLine("OH_AudioStream_Type passed")
    }

    @Test
    fun testEnum_OH_AudioStream_SampleFormat() {
        assertEquals(AUDIOSTREAM_SAMPLE_U8.toInt(), 0)
        assertEquals(AUDIOSTREAM_SAMPLE_S16LE.toInt(), 1)
        assertEquals(AUDIOSTREAM_SAMPLE_S24LE.toInt(), 2)
        assertEquals(AUDIOSTREAM_SAMPLE_S32LE.toInt(), 3)
        assertEquals(AUDIOSTREAM_SAMPLE_F32LE.toInt(), 4)
        logLine("OH_AudioStream_SampleFormat passed")
    }

    @Test
    fun testEnum_OH_AudioStream_EncodingType() {
        assertEquals(AUDIOSTREAM_ENCODING_TYPE_RAW.toInt(), 0)
        assertEquals(AUDIOSTREAM_ENCODING_TYPE_AUDIOVIVID.toInt(), 1)
        assertEquals(AUDIOSTREAM_ENCODING_TYPE_E_AC3.toInt(), 2)
        logLine("OH_AudioStream_EncodingType passed")
    }

    @Test
    fun testEnum_OH_AudioStream_Usage() {
        assertEquals(AUDIOSTREAM_USAGE_UNKNOWN.toInt(), 0)
        assertEquals(AUDIOSTREAM_USAGE_MUSIC.toInt(), 1)
        assertEquals(AUDIOSTREAM_USAGE_VOICE_COMMUNICATION.toInt(), 2)
        assertEquals(AUDIOSTREAM_USAGE_VOICE_ASSISTANT.toInt(), 3)
        assertEquals(AUDIOSTREAM_USAGE_ALARM.toInt(), 4)
        assertEquals(AUDIOSTREAM_USAGE_VOICE_MESSAGE.toInt(), 5)
        assertEquals(AUDIOSTREAM_USAGE_RINGTONE.toInt(), 6)
        assertEquals(AUDIOSTREAM_USAGE_NOTIFICATION.toInt(), 7)
        assertEquals(AUDIOSTREAM_USAGE_ACCESSIBILITY.toInt(), 8)
        assertEquals(AUDIOSTREAM_USAGE_MOVIE.toInt(), 10)
        assertEquals(AUDIOSTREAM_USAGE_GAME.toInt(), 11)
        assertEquals(AUDIOSTREAM_USAGE_AUDIOBOOK.toInt(), 12)
        assertEquals(AUDIOSTREAM_USAGE_NAVIGATION.toInt(), 13)
        assertEquals(AUDIOSTREAM_USAGE_VIDEO_COMMUNICATION.toInt(), 17)
        logLine("OH_AudioStream_Usage passed")
    }

    @Test
    fun testEnum_OH_AudioStream_LatencyMode() {
        assertEquals(AUDIOSTREAM_LATENCY_MODE_NORMAL.toInt(), 0)
        assertEquals(AUDIOSTREAM_LATENCY_MODE_FAST.toInt(), 1)
        logLine("OH_AudioStream_LatencyMode passed")
    }

    @Test
    fun testEnum_OH_AudioStream_DirectPlaybackMode() {
        assertEquals(AUDIOSTREAM_DIRECT_PLAYBACK_NOT_SUPPORTED.toInt(), 0)
        assertEquals(AUDIOSTREAM_DIRECT_PLAYBACK_BITSTREAM_SUPPORTED.toInt(), 1)
        assertEquals(AUDIOSTREAM_DIRECT_PLAYBACK_PCM_SUPPORTED.toInt(), 2)
        logLine("OH_AudioStream_DirectPlaybackMode passed")
    }

    @Test
    fun testEnum_OH_AudioStream_Event() {
        assertEquals(AUDIOSTREAM_EVENT_ROUTING_CHANGED.toInt(), 0)
        logLine("OH_AudioStream_Event passed")
    }

    @Test
    fun testEnum_OH_AudioStream_State() {
        assertEquals(AUDIOSTREAM_STATE_INVALID.toInt(), -1)
        assertEquals(AUDIOSTREAM_STATE_NEW.toInt(), 0)
        assertEquals(AUDIOSTREAM_STATE_PREPARED.toInt(), 1)
        assertEquals(AUDIOSTREAM_STATE_RUNNING.toInt(), 2)
        assertEquals(AUDIOSTREAM_STATE_STOPPED.toInt(), 3)
        assertEquals(AUDIOSTREAM_STATE_RELEASED.toInt(), 4)
        assertEquals(AUDIOSTREAM_STATE_PAUSED.toInt(), 5)
        logLine("OH_AudioStream_State passed")
    }

    @Test
    fun testEnum_OH_AudioInterrupt_ForceType() {
        assertEquals(AUDIOSTREAM_INTERRUPT_FORCE.toInt(), 0)
        assertEquals(AUDIOSTREAM_INTERRUPT_SHARE.toInt(), 1)
        logLine("OH_AudioInterrupt_ForceType passed")
    }

    @Test
    fun testEnum_OH_AudioInterrupt_Hint() {
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_NONE.toInt(), 0)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_RESUME.toInt(), 1)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_PAUSE.toInt(), 2)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_STOP.toInt(), 3)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_DUCK.toInt(), 4)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_UNDUCK.toInt(), 5)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_MUTE.toInt(), 6)
        assertEquals(AUDIOSTREAM_INTERRUPT_HINT_UNMUTE.toInt(), 7)
        logLine("OH_AudioInterrupt_Hint passed")
    }

    @Test
    fun testEnum_OH_AudioStream_SourceType() {
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_INVALID.toInt(), -1)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_MIC.toInt(), 0)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_VOICE_RECOGNITION.toInt(), 1)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_PLAYBACK_CAPTURE.toInt(), 2)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_VOICE_COMMUNICATION.toInt(), 7)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_VOICE_MESSAGE.toInt(), 10)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_CAMCORDER.toInt(), 13)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_UNPROCESSED.toInt(), 14)
        assertEquals(AUDIOSTREAM_SOURCE_TYPE_LIVE.toInt(), 17)
        logLine("OH_AudioStream_SourceType passed")
    }

    @Test
    fun testEnum_OH_AudioInterrupt_Mode() {
        assertEquals(AUDIOSTREAM_INTERRUPT_MODE_SHARE.toInt(), 0)
        assertEquals(AUDIOSTREAM_INTERRUPT_MODE_INDEPENDENT.toInt(), 1)
        logLine("OH_AudioInterrupt_Mode passed")
    }

    @Test
    fun testEnum_OH_AudioStream_AudioEffectMode() {
        assertEquals(EFFECT_NONE.toInt(), 0)
        assertEquals(EFFECT_DEFAULT.toInt(), 1)
        logLine("OH_AudioStream_AudioEffectMode passed")
    }

    @Test
    fun testEnum_OH_AudioStream_FastStatus() {
        assertEquals(AUDIOSTREAM_FASTSTATUS_NORMAL.toInt(), 0)
        assertEquals(AUDIOSTREAM_FASTSTATUS_FAST.toInt(), 1)
        logLine("OH_AudioStream_FastStatus passed")
    }

    @Test
    fun testEnum_OH_AudioStream_DeviceChangeReason() {
        assertEquals(REASON_UNKNOWN.toInt(), 0)
        assertEquals(REASON_NEW_DEVICE_AVAILABLE.toInt(), 1)
        assertEquals(REASON_OLD_DEVICE_UNAVAILABLE.toInt(), 2)
        assertEquals(REASON_OVERRODE.toInt(), 3)
        assertEquals(REASON_SESSION_ACTIVATED.toInt(), 4)
        assertEquals(REASON_STREAM_PRIORITY_CHANGED.toInt(), 5)
        logLine("OH_AudioStream_DeviceChangeReason passed")
    }

    @Test
    fun testEnum_OH_AudioStream_PrivacyType() {
        assertEquals(AUDIO_STREAM_PRIVACY_TYPE_PUBLIC.toInt(), 0)
        assertEquals(AUDIO_STREAM_PRIVACY_TYPE_PRIVATE.toInt(), 1)
        assertEquals(AUDIO_STREAM_PRIVACY_TYPE_SHARED.toInt(), 2)
        logLine("OH_AudioStream_PrivacyType passed")
    }

    @Test
    fun testEnum_OH_AudioData_Callback_Result() {
        assertEquals(AUDIO_DATA_CALLBACK_RESULT_INVALID.toInt(), -1)
        assertEquals(AUDIO_DATA_CALLBACK_RESULT_VALID.toInt(), 0)
        logLine("OH_AudioData_Callback_Result passed")
    }

    @Test
    fun testEnum_OH_AudioStream_VolumeMode() {
        assertEquals(AUDIOSTREAM_VOLUMEMODE_SYSTEM_GLOBAL.toInt(), 0)
        assertEquals(AUDIOSTREAM_VOLUMEMODE_APP_INDIVIDUAL.toInt(), 1)
        logLine("OH_AudioStream_VolumeMode passed")
    }

    // ==================== 枚举 native_audio_session_manager ====================
    @Test
    fun testEnum_OH_AudioSession_ConcurrencyMode() {
        assertEquals(CONCURRENCY_DEFAULT.toInt(), 0)
        assertEquals(CONCURRENCY_MIX_WITH_OTHERS.toInt(), 1)
        assertEquals(CONCURRENCY_DUCK_OTHERS.toInt(), 2)
        assertEquals(CONCURRENCY_PAUSE_OTHERS.toInt(), 3)
        logLine("OH_AudioSession_ConcurrencyMode passed")
    }

    @Test
    fun testEnum_OH_AudioSession_Scene() {
        assertEquals(AUDIO_SESSION_SCENE_MEDIA.toInt(), 0)
        assertEquals(AUDIO_SESSION_SCENE_GAME.toInt(), 1)
        assertEquals(AUDIO_SESSION_SCENE_VOICE_COMMUNICATION.toInt(), 2)
        logLine("OH_AudioSession_Scene passed")
    }

    @Test
    fun testEnum_OH_AudioSession_StateChangeHint() {
        assertEquals(AUDIO_SESSION_STATE_CHANGE_HINT_RESUME.toInt(), 0)
        assertEquals(AUDIO_SESSION_STATE_CHANGE_HINT_PAUSE.toInt(), 1)
        assertEquals(AUDIO_SESSION_STATE_CHANGE_HINT_STOP.toInt(), 2)
        assertEquals(AUDIO_SESSION_STATE_CHANGE_HINT_TIME_OUT_STOP.toInt(), 3)
        assertEquals(AUDIO_SESSION_STATE_CHANGE_HINT_DUCK.toInt(), 4)
        assertEquals(AUDIO_SESSION_STATE_CHANGE_HINT_UNDUCK.toInt(), 5)
        logLine("OH_AudioSession_StateChangeHint passed")
    }

    @Test
    fun testEnum_OH_AudioSession_OutputDeviceChangeRecommendedAction() {
        assertEquals(DEVICE_CHANGE_RECOMMEND_TO_CONTINUE.toInt(), 0)
        assertEquals(DEVICE_CHANGE_RECOMMEND_TO_STOP.toInt(), 1)
        logLine("OH_AudioSession_OutputDeviceChangeRecommendedAction passed")
    }

    @Test
    fun testEnum_OH_AudioSession_DeactivatedReason() {
        assertEquals(DEACTIVATED_LOWER_PRIORITY.toInt(), 0)
        assertEquals(DEACTIVATED_TIMEOUT.toInt(), 1)
        logLine("OH_AudioSession_DeactivatedReason passed")
    }

    @Test
    fun testEnum_OH_AudioSession_BluetoothAndNearlinkPreferredRecordCategory() {
        assertEquals(PREFERRED_NONE.toInt(), 0)
        assertEquals(PREFERRED_DEFAULT.toInt(), 1)
        assertEquals(PREFERRED_LOW_LATENCY.toInt(), 2)
        assertEquals(PREFERRED_HIGH_QUALITY.toInt(), 3)
        logLine("OH_AudioSession_BluetoothAndNearlinkPreferredRecordCategory passed")
    }

    // ==================== 枚举 native_audio_device_base ====================
    @Test
    fun testEnum_OH_AudioDevice_ChangeType() {
        assertEquals(AUDIO_DEVICE_CHANGE_TYPE_CONNECT.toInt(), 0)
        assertEquals(AUDIO_DEVICE_CHANGE_TYPE_DISCONNECT.toInt(), 1)
        logLine("OH_AudioDevice_ChangeType passed")
    }

    @Test
    fun testEnum_OH_AudioDevice_Role() {
        assertEquals(AUDIO_DEVICE_ROLE_INPUT.toInt(), 1)
        assertEquals(AUDIO_DEVICE_ROLE_OUTPUT.toInt(), 2)
        logLine("OH_AudioDevice_Role passed")
    }

    @Test
    fun testEnum_OH_AudioDevice_Type() {
        assertEquals(AUDIO_DEVICE_TYPE_INVALID.toInt(), 0)
        assertEquals(AUDIO_DEVICE_TYPE_EARPIECE.toInt(), 1)
        assertEquals(AUDIO_DEVICE_TYPE_SPEAKER.toInt(), 2)
        assertEquals(AUDIO_DEVICE_TYPE_WIRED_HEADSET.toInt(), 3)
        assertEquals(AUDIO_DEVICE_TYPE_WIRED_HEADPHONES.toInt(), 4)
        assertEquals(AUDIO_DEVICE_TYPE_BLUETOOTH_SCO.toInt(), 7)
        assertEquals(AUDIO_DEVICE_TYPE_BLUETOOTH_A2DP.toInt(), 8)
        assertEquals(AUDIO_DEVICE_TYPE_MIC.toInt(), 15)
        assertEquals(AUDIO_DEVICE_TYPE_USB_HEADSET.toInt(), 22)
        assertEquals(AUDIO_DEVICE_TYPE_DISPLAY_PORT.toInt(), 23)
        assertEquals(AUDIO_DEVICE_TYPE_REMOTE_CAST.toInt(), 24)
        assertEquals(AUDIO_DEVICE_TYPE_USB_DEVICE.toInt(), 25)
        assertEquals(AUDIO_DEVICE_TYPE_ACCESSORY.toInt(), 26)
        assertEquals(AUDIO_DEVICE_TYPE_HDMI.toInt(), 27)
        assertEquals(AUDIO_DEVICE_TYPE_LINE_DIGITAL.toInt(), 28)
        assertEquals(AUDIO_DEVICE_TYPE_HEARING_AID.toInt(), 30)
        assertEquals(AUDIO_DEVICE_TYPE_NEARLINK.toInt(), 31)
        assertEquals(AUDIO_DEVICE_TYPE_DEFAULT.toInt(), 1000)
        logLine("OH_AudioDevice_Type passed")
    }

    @Test
    fun testEnum_OH_AudioDevice_Flag() {
        assertEquals(AUDIO_DEVICE_FLAG_NONE.toInt(), 0)
        assertEquals(AUDIO_DEVICE_FLAG_OUTPUT.toInt(), 1)
        assertEquals(AUDIO_DEVICE_FLAG_INPUT.toInt(), 2)
        assertEquals(AUDIO_DEVICE_FLAG_ALL.toInt(), 3)
        logLine("OH_AudioDevice_Flag passed")
    }

    @Test
    fun testEnum_OH_AudioDevice_Usage() {
        assertEquals(AUDIO_DEVICE_USAGE_MEDIA_OUTPUT.toInt(), 1)
        assertEquals(AUDIO_DEVICE_USAGE_MEDIA_INPUT.toInt(), 2)
        assertEquals(AUDIO_DEVICE_USAGE_MEDIA_ALL.toInt(), 3)
        assertEquals(AUDIO_DEVICE_USAGE_CALL_OUTPUT.toInt(), 4)
        assertEquals(AUDIO_DEVICE_USAGE_CALL_INPUT.toInt(), 8)
        assertEquals(AUDIO_DEVICE_USAGE_CALL_ALL.toInt(), 12)
        logLine("OH_AudioDevice_Usage passed")
    }

    @Test
    fun testEnum_OH_AudioDevice_BlockStatus() {
        assertEquals(AUDIO_DEVICE_UNBLOCKED.toInt(), 0)
        assertEquals(AUDIO_DEVICE_BLOCKED.toInt(), 1)
        logLine("OH_AudioDevice_BlockStatus passed")
    }

    // ==================== OH_AudioStreamBuilderStruct ====================

    @Test
    fun testOH_AudioStreamBuilder_Create() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        val ret = OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        logLine("OH_AudioStreamBuilder_Create ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_Destroy() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_Destroy(builderPtr.value)
        logLine("OH_AudioStreamBuilder_Destroy ret=$ret")
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetSamplingRate() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetSamplingRate(builderPtr.value, 48000)
        logLine("OH_AudioStreamBuilder_SetSamplingRate ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetChannelCount() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetChannelCount(builderPtr.value, 2)
        logLine("OH_AudioStreamBuilder_SetChannelCount ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetSampleFormat() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetSampleFormat(builderPtr.value, AUDIOSTREAM_SAMPLE_S16LE)
        logLine("OH_AudioStreamBuilder_SetSampleFormat ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetEncodingType() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetEncodingType(builderPtr.value, AUDIOSTREAM_ENCODING_TYPE_RAW)
        logLine("OH_AudioStreamBuilder_SetEncodingType ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetLatencyMode() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetLatencyMode(builderPtr.value, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        logLine("OH_AudioStreamBuilder_SetLatencyMode ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererInfo() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetRendererInfo(builderPtr.value, AUDIOSTREAM_USAGE_MUSIC)
        logLine("OH_AudioStreamBuilder_SetRendererInfo ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_GenerateRenderer() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        logLine("OH_AudioStreamBuilder_GenerateRenderer renderer=${rendererPtr.value}")
        if (rendererPtr.value != null) OH_AudioRenderer_Release(rendererPtr.value)
        OH_AudioStreamBuilder_Destroy(b)
    } }

    // ==================== OH_AudioRendererStruct (native_audiorenderer.h) ====================

    @Test
    fun testOH_AudioRenderer_Start() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_Start(r)
        logLine("OH_AudioRenderer_Start ret=$ret")
        OH_AudioRenderer_Stop(r)
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_Pause() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        OH_AudioRenderer_Start(r)
        val ret = OH_AudioRenderer_Pause(r)
        logLine("OH_AudioRenderer_Pause ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_Stop() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_Stop(r)
        logLine("OH_AudioRenderer_Stop ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_Flush() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_Flush(r)
        logLine("OH_AudioRenderer_Flush ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetCurrentState() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val stateVar = alloc<IntVar>()
        val ret = OH_AudioRenderer_GetCurrentState(r, stateVar.ptr)
        logLine("OH_AudioRenderer_GetCurrentState ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetSamplingRate() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val rateVar = alloc<IntVar>()
        val ret = OH_AudioRenderer_GetSamplingRate(r, rateVar.ptr)
        logLine("OH_AudioRenderer_GetSamplingRate ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetStreamId() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val streamIdVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetStreamId(r, streamIdVar.ptr)
        logLine("OH_AudioRenderer_GetStreamId ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetChannelCount() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val channelCountVar = alloc<IntVar>()
        val ret = OH_AudioRenderer_GetChannelCount(r, channelCountVar.ptr)
        logLine("OH_AudioRenderer_GetChannelCount ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetSampleFormat() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val sampleFormatVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetSampleFormat(r, sampleFormatVar.ptr)
        logLine("OH_AudioRenderer_GetSampleFormat ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetLatencyMode() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val latencyModeVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetLatencyMode(r, latencyModeVar.ptr)
        logLine("OH_AudioRenderer_GetLatencyMode ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetRendererInfo() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val usageVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetRendererInfo(r, usageVar.ptr)
        logLine("OH_AudioRenderer_GetRendererInfo ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetEncodingType() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val encodingTypeVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetEncodingType(r, encodingTypeVar.ptr)
        logLine("OH_AudioRenderer_GetEncodingType ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetFramesWritten() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val framesVar = alloc<LongVar>()
        val ret = OH_AudioRenderer_GetFramesWritten(r, framesVar.ptr)
        logLine("OH_AudioRenderer_GetFramesWritten ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetTimestamp() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val framePosVar = alloc<LongVar>()
        val timestampVar = alloc<LongVar>()
        val ret = OH_AudioRenderer_GetTimestamp(r, 1, framePosVar.ptr, timestampVar.ptr)
        logLine("OH_AudioRenderer_GetTimestamp ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetFrameSizeInCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val frameSizeVar = alloc<IntVar>()
        val ret = OH_AudioRenderer_GetFrameSizeInCallback(r, frameSizeVar.ptr)
        logLine("OH_AudioRenderer_GetFrameSizeInCallback ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetSpeed() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val speedVar = alloc<FloatVar>()
        val ret = OH_AudioRenderer_GetSpeed(r, speedVar.ptr)
        logLine("OH_AudioRenderer_GetSpeed ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetSpeed() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetSpeed(r, 1.0f)
        logLine("OH_AudioRenderer_SetSpeed ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetVolume() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetVolume(r, 0.5f)
        logLine("OH_AudioRenderer_SetVolume ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetVolumeWithRamp() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetVolumeWithRamp(r, 0.5f, 100)
        logLine("OH_AudioRenderer_SetVolumeWithRamp ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetVolume() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val volumeVar = alloc<FloatVar>()
        val ret = OH_AudioRenderer_GetVolume(r, volumeVar.ptr)
        logLine("OH_AudioRenderer_GetVolume ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetMarkPosition() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val markCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _samplePos: UInt, _userData: COpaquePointer? -> }
        val ret = OH_AudioRenderer_SetMarkPosition(r, 0u, markCb, null)
        logLine("OH_AudioRenderer_SetMarkPosition ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_CancelMark() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_CancelMark(r)
        logLine("OH_AudioRenderer_CancelMark ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetUnderflowCount() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val underflowVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetUnderflowCount(r, underflowVar.ptr)
        logLine("OH_AudioRenderer_GetUnderflowCount ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetChannelLayout() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val channelLayoutVar = alloc<ULongVar>()
        val ret = OH_AudioRenderer_GetChannelLayout(r, channelLayoutVar.ptr)
        logLine("OH_AudioRenderer_GetChannelLayout ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetEffectMode() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val effectModeVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetEffectMode(r, effectModeVar.ptr)
        logLine("OH_AudioRenderer_GetEffectMode ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetEffectMode() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetEffectMode(r, EFFECT_NONE)
        logLine("OH_AudioRenderer_SetEffectMode ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetRendererPrivacy() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val privacyVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetRendererPrivacy(r, privacyVar.ptr)
        logLine("OH_AudioRenderer_GetRendererPrivacy ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetSilentModeAndMixWithOthers() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetSilentModeAndMixWithOthers(r, false)
        logLine("OH_AudioRenderer_SetSilentModeAndMixWithOthers ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetSilentModeAndMixWithOthers() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val silentMixVar = alloc<BooleanVar>()
        val ret = OH_AudioRenderer_GetSilentModeAndMixWithOthers(r, silentMixVar.ptr)
        logLine("OH_AudioRenderer_GetSilentModeAndMixWithOthers ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_SetDefaultOutputDevice() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetDefaultOutputDevice(r, AUDIO_DEVICE_TYPE_SPEAKER)
        logLine("OH_AudioRenderer_SetDefaultOutputDevice ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetAudioTimestampInfo() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val framePosInfoVar = alloc<LongVar>()
        val timestampInfoVar = alloc<LongVar>()
        val ret = OH_AudioRenderer_GetAudioTimestampInfo(r, framePosInfoVar.ptr, timestampInfoVar.ptr)
        logLine("OH_AudioRenderer_GetAudioTimestampInfo ret=$ret")
        OH_AudioRenderer_Release(r)
    } }

    @Test
    fun testOH_AudioRenderer_GetFastStatus() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val fastStatusVar = alloc<UIntVar>()
        val ret = OH_AudioRenderer_GetFastStatus(r, fastStatusVar.ptr)
        logLine("OH_AudioRenderer_GetFastStatus ret=$ret")
        OH_AudioRenderer_Release(r)
        } catch (e: Throwable) { logLine("testOH_AudioRenderer_GetFastStatus (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioRenderer_SetLoudnessGain() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_SetLoudnessGain(r, 0.0f)
        logLine("OH_AudioRenderer_SetLoudnessGain ret=$ret")
        OH_AudioRenderer_Release(r)
        } catch (e: Throwable) { logLine("testOH_AudioRenderer_SetLoudnessGain (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioRenderer_GetLoudnessGain() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val loudnessVar = alloc<FloatVar>()
        val ret = OH_AudioRenderer_GetLoudnessGain(r, loudnessVar.ptr)
        logLine("OH_AudioRenderer_GetLoudnessGain ret=$ret")
        OH_AudioRenderer_Release(r)
        } catch (e: Throwable) { logLine("testOH_AudioRenderer_GetLoudnessGain (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioRenderer_Release() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val rendererPtr = alloc<CPointerVar<OH_AudioRendererStruct>>()
        OH_AudioStreamBuilder_GenerateRenderer(b, rendererPtr.ptr)
        val r = rendererPtr.value
        val ret = OH_AudioRenderer_Release(r)
        logLine("OH_AudioRenderer_Release ret=$ret")
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerInfo() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val ret = OH_AudioStreamBuilder_SetCapturerInfo(builderPtr.value, AUDIOSTREAM_SOURCE_TYPE_MIC)
        logLine("OH_AudioStreamBuilder_SetCapturerInfo ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_GenerateCapturer() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        logLine("OH_AudioStreamBuilder_GenerateCapturer capturer=${capturerPtr.value}")
        if (capturerPtr.value != null) OH_AudioCapturer_Release(capturerPtr.value)
        OH_AudioStreamBuilder_Destroy(b)
    } }

    // ==================== OH_AudioCapturerStruct (native_audiocapturer.h) ====================

    @Test
    fun testOH_AudioCapturer_Start() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val ret = OH_AudioCapturer_Start(c)
        logLine("OH_AudioCapturer_Start ret=$ret")
        OH_AudioCapturer_Stop(c)
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_Pause() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        OH_AudioCapturer_Start(c)
        val ret = OH_AudioCapturer_Pause(c)
        logLine("OH_AudioCapturer_Pause ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_Stop() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val ret = OH_AudioCapturer_Stop(c)
        logLine("OH_AudioCapturer_Stop ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_Flush() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val ret = OH_AudioCapturer_Flush(c)
        logLine("OH_AudioCapturer_Flush ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetCurrentState() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val stateVar = alloc<IntVar>()
        val ret = OH_AudioCapturer_GetCurrentState(c, stateVar.ptr)
        logLine("OH_AudioCapturer_GetCurrentState ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetLatencyMode() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val latencyModeVar = alloc<UIntVar>()
        val ret = OH_AudioCapturer_GetLatencyMode(c, latencyModeVar.ptr)
        logLine("OH_AudioCapturer_GetLatencyMode ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetStreamId() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val streamIdVar = alloc<UIntVar>()
        val ret = OH_AudioCapturer_GetStreamId(c, streamIdVar.ptr)
        logLine("OH_AudioCapturer_GetStreamId ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetSamplingRate() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val rateVar = alloc<IntVar>()
        val ret = OH_AudioCapturer_GetSamplingRate(c, rateVar.ptr)
        logLine("OH_AudioCapturer_GetSamplingRate ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetChannelCount() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val channelCountVar = alloc<IntVar>()
        val ret = OH_AudioCapturer_GetChannelCount(c, channelCountVar.ptr)
        logLine("OH_AudioCapturer_GetChannelCount ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetSampleFormat() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val sampleFormatVar = alloc<UIntVar>()
        val ret = OH_AudioCapturer_GetSampleFormat(c, sampleFormatVar.ptr)
        logLine("OH_AudioCapturer_GetSampleFormat ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetEncodingType() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val encodingTypeVar = alloc<UIntVar>()
        val ret = OH_AudioCapturer_GetEncodingType(c, encodingTypeVar.ptr)
        logLine("OH_AudioCapturer_GetEncodingType ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetCapturerInfo() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val sourceTypeVar = alloc<IntVar>()
        val ret = OH_AudioCapturer_GetCapturerInfo(c, sourceTypeVar.ptr)
        logLine("OH_AudioCapturer_GetCapturerInfo ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetFrameSizeInCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val frameSizeVar = alloc<IntVar>()
        val ret = OH_AudioCapturer_GetFrameSizeInCallback(c, frameSizeVar.ptr)
        logLine("OH_AudioCapturer_GetFrameSizeInCallback ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetTimestamp() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val framePosVar = alloc<LongVar>()
        val timestampVar = alloc<LongVar>()
        val ret = OH_AudioCapturer_GetTimestamp(c, 1, framePosVar.ptr, timestampVar.ptr)
        logLine("OH_AudioCapturer_GetTimestamp ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetFramesRead() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val framesVar = alloc<LongVar>()
        val ret = OH_AudioCapturer_GetFramesRead(c, framesVar.ptr)
        logLine("OH_AudioCapturer_GetFramesRead ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetOverflowCount() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val overflowCountVar = alloc<UIntVar>()
        val ret = OH_AudioCapturer_GetOverflowCount(c, overflowCountVar.ptr)
        logLine("OH_AudioCapturer_GetOverflowCount ret=$ret")
        OH_AudioCapturer_Release(c)
    } }

    @Test
    fun testOH_AudioCapturer_GetFastStatus() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val fastStatusVar = alloc<UIntVar>()
        val ret = OH_AudioCapturer_GetFastStatus(c, fastStatusVar.ptr)
        logLine("OH_AudioCapturer_GetFastStatus ret=$ret")
        OH_AudioCapturer_Release(c)
        } catch (e: Throwable) { logLine("testOH_AudioCapturer_GetFastStatus (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioCapturer_Release() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerPtr = alloc<CPointerVar<OH_AudioCapturerStruct>>()
        OH_AudioStreamBuilder_GenerateCapturer(b, capturerPtr.ptr)
        val c = capturerPtr.value
        val ret = OH_AudioCapturer_Release(c)
        logLine("OH_AudioCapturer_Release ret=$ret")
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetChannelLayout() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val ret = OH_AudioStreamBuilder_SetChannelLayout(builderPtr.value, 0uL)
        logLine("OH_AudioStreamBuilder_SetChannelLayout ret=$ret")
        OH_AudioStreamBuilder_Destroy(builderPtr.value)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val onWriteData = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _buf: COpaquePointer?, _len: Int -> 0 }
        val onStreamEvent = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _e: UInt -> 0 }
        val onInterruptEvent = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _t: UInt, _h: UInt -> 0 }
        val onError = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _err: UInt -> 0 }
        val rendererCallbacks = alloc<OH_AudioRenderer_Callbacks_Struct>().apply {
            OH_AudioRenderer_OnWriteData = onWriteData
            OH_AudioRenderer_OnStreamEvent = onStreamEvent
            OH_AudioRenderer_OnInterruptEvent = onInterruptEvent
            OH_AudioRenderer_OnError = onError
        }
        val ret = OH_AudioStreamBuilder_SetRendererCallback(b, rendererCallbacks.readValue(), null)
        logLine("OH_AudioStreamBuilder_SetRendererCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererOutputDeviceChangeCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val outDeviceChangeCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _reason: UInt -> }
        val ret = OH_AudioStreamBuilder_SetRendererOutputDeviceChangeCallback(b, outDeviceChangeCb, null)
        logLine("OH_AudioStreamBuilder_SetRendererOutputDeviceChangeCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererPrivacy() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val ret = OH_AudioStreamBuilder_SetRendererPrivacy(b, AUDIO_STREAM_PRIVACY_TYPE_PUBLIC)
        logLine("OH_AudioStreamBuilder_SetRendererPrivacy ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetFrameSizeInCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val ret = OH_AudioStreamBuilder_SetFrameSizeInCallback(b, 1024)
        logLine("OH_AudioStreamBuilder_SetFrameSizeInCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetWriteDataWithMetadataCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val writeDataWithMetaCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _data: COpaquePointer?, _dataLen: Int, _meta: COpaquePointer?, _metaLen: Int -> 0 }
        val ret = OH_AudioStreamBuilder_SetWriteDataWithMetadataCallback(b, writeDataWithMetaCb, null)
        logLine("OH_AudioStreamBuilder_SetWriteDataWithMetadataCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererInterruptMode() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val ret = OH_AudioStreamBuilder_SetRendererInterruptMode(b, AUDIOSTREAM_INTERRUPT_MODE_SHARE)
        logLine("OH_AudioStreamBuilder_SetRendererInterruptMode ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererWriteDataCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val writeDataCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _data: COpaquePointer?, _len: Int -> AUDIO_DATA_CALLBACK_RESULT_VALID }
        val ret = OH_AudioStreamBuilder_SetRendererWriteDataCallback(b, writeDataCb, null)
        logLine("OH_AudioStreamBuilder_SetRendererWriteDataCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererWriteDataCallbackAdvanced() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val writeDataAdvCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _data: COpaquePointer?, _len: Int -> 0 }
        val ret = OH_AudioStreamBuilder_SetRendererWriteDataCallbackAdvanced(b, writeDataAdvCb, null)
        logLine("OH_AudioStreamBuilder_SetRendererWriteDataCallbackAdvanced ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetRendererWriteDataCallbackAdvanced (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetVolumeMode() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val ret = OH_AudioStreamBuilder_SetVolumeMode(b, AUDIOSTREAM_VOLUMEMODE_SYSTEM_GLOBAL)
        logLine("OH_AudioStreamBuilder_SetVolumeMode ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetVolumeMode (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererInterruptCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val interruptCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _t: UInt, _h: UInt -> }
        val ret = OH_AudioStreamBuilder_SetRendererInterruptCallback(b, interruptCb, null)
        logLine("OH_AudioStreamBuilder_SetRendererInterruptCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetRendererInterruptCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererErrorCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val errorCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _err: UInt -> }
        val ret = OH_AudioStreamBuilder_SetRendererErrorCallback(b, errorCb, null)
        logLine("OH_AudioStreamBuilder_SetRendererErrorCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetRendererErrorCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetRendererFastStatusChangeCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_RENDERER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 2)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetLatencyMode(b, AUDIOSTREAM_LATENCY_MODE_NORMAL)
        OH_AudioStreamBuilder_SetRendererInfo(b, AUDIOSTREAM_USAGE_MUSIC)
        val fastStatusCb = staticCFunction { _r: CPointer<OH_AudioRendererStruct>?, _u: COpaquePointer?, _s: UInt -> }
        val ret = OH_AudioStreamBuilder_SetRendererFastStatusChangeCallback(b, fastStatusCb, null)
        logLine("OH_AudioStreamBuilder_SetRendererFastStatusChangeCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetRendererFastStatusChangeCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerCallback() { memScoped {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val onReadData = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _buf: COpaquePointer?, _len: Int -> 0 }
        val onCapturerStreamEvent = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _e: UInt -> 0 }
        val onCapturerInterrupt = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _t: UInt, _h: UInt -> 0 }
        val onCapturerError = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _err: UInt -> 0 }
        val capturerCallbacks = alloc<OH_AudioCapturer_Callbacks_Struct>().apply {
            OH_AudioCapturer_OnReadData = onReadData
            OH_AudioCapturer_OnStreamEvent = onCapturerStreamEvent
            OH_AudioCapturer_OnInterruptEvent = onCapturerInterrupt
            OH_AudioCapturer_OnError = onCapturerError
        }
        val ret = OH_AudioStreamBuilder_SetCapturerCallback(b, capturerCallbacks.readValue(), null)
        logLine("OH_AudioStreamBuilder_SetCapturerCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerReadDataCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerReadCb = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _data: COpaquePointer?, _len: Int -> Unit }
        val ret = OH_AudioStreamBuilder_SetCapturerReadDataCallback(b, capturerReadCb, null)
        logLine("OH_AudioStreamBuilder_SetCapturerReadDataCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetCapturerReadDataCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerDeviceChangeCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerDeviceChangeCb = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _arr: CPointer<OH_AudioDeviceDescriptorArray>? -> }
        val ret = OH_AudioStreamBuilder_SetCapturerDeviceChangeCallback(b, capturerDeviceChangeCb, null)
        logLine("OH_AudioStreamBuilder_SetCapturerDeviceChangeCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetCapturerDeviceChangeCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerInterruptCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerInterruptCb = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _t: UInt, _h: UInt -> }
        val ret = OH_AudioStreamBuilder_SetCapturerInterruptCallback(b, capturerInterruptCb, null)
        logLine("OH_AudioStreamBuilder_SetCapturerInterruptCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetCapturerInterruptCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerErrorCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerErrorCb = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _err: UInt -> }
        val ret = OH_AudioStreamBuilder_SetCapturerErrorCallback(b, capturerErrorCb, null)
        logLine("OH_AudioStreamBuilder_SetCapturerErrorCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetCapturerErrorCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerWillMuteWhenInterrupted() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val ret = OH_AudioStreamBuilder_SetCapturerWillMuteWhenInterrupted(b, false)
        logLine("OH_AudioStreamBuilder_SetCapturerWillMuteWhenInterrupted ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetCapturerWillMuteWhenInterrupted (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioStreamBuilder_SetCapturerFastStatusChangeCallback() { memScoped {
        try {
        val builderPtr = alloc<CPointerVar<OH_AudioStreamBuilderStruct>>()
        OH_AudioStreamBuilder_Create(builderPtr.ptr, AUDIOSTREAM_TYPE_CAPTURER)
        val b = builderPtr.value
        OH_AudioStreamBuilder_SetSamplingRate(b, 48000)
        OH_AudioStreamBuilder_SetChannelCount(b, 1)
        OH_AudioStreamBuilder_SetSampleFormat(b, AUDIOSTREAM_SAMPLE_S16LE)
        OH_AudioStreamBuilder_SetEncodingType(b, AUDIOSTREAM_ENCODING_TYPE_RAW)
        OH_AudioStreamBuilder_SetCapturerInfo(b, AUDIOSTREAM_SOURCE_TYPE_MIC)
        val capturerFastStatusCb = staticCFunction { _c: CPointer<OH_AudioCapturerStruct>?, _u: COpaquePointer?, _s: UInt -> }
        val ret = OH_AudioStreamBuilder_SetCapturerFastStatusChangeCallback(b, capturerFastStatusCb, null)
        logLine("OH_AudioStreamBuilder_SetCapturerFastStatusChangeCallback ret=$ret")
        OH_AudioStreamBuilder_Destroy(b)
        } catch (e: Throwable) { logLine("testOH_AudioStreamBuilder_SetCapturerFastStatusChangeCallback (API >17) exception: $e") }
    } }

    // ==================== OH_AudioManager / OH_AudioSessionManager (native_audio_session_manager.h) ====================
    @Test
    fun testOH_AudioManager_GetAudioSessionManager() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        val ret = OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        logLine("OH_AudioManager_GetAudioSessionManager ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_ActivateAudioSession() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val strategy = alloc<OH_AudioSession_Strategy>().apply { concurrencyMode = CONCURRENCY_DEFAULT }
        val ret = OH_AudioSessionManager_ActivateAudioSession(mgr, strategy.ptr)
        logLine("OH_AudioSessionManager_ActivateAudioSession ret=$ret")
        OH_AudioSessionManager_DeactivateAudioSession(mgr)
    } }

    @Test
    fun testOH_AudioSessionManager_DeactivateAudioSession() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val ret = OH_AudioSessionManager_DeactivateAudioSession(mgr)
        logLine("OH_AudioSessionManager_DeactivateAudioSession ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_IsAudioSessionActivated() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        OH_AudioSessionManager_IsAudioSessionActivated(mgr)
        logLine("OH_AudioSessionManager_IsAudioSessionActivated called")
    } }

    @Test
    fun testOH_AudioSessionManager_RegisterSessionDeactivatedCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val deactivatedCb = staticCFunction { _e: CValue<OH_AudioSession_DeactivatedEvent> -> 0 }
        val ret = OH_AudioSessionManager_RegisterSessionDeactivatedCallback(mgr, deactivatedCb)
        OH_AudioSessionManager_UnregisterSessionDeactivatedCallback(mgr, deactivatedCb)
        logLine("OH_AudioSessionManager_RegisterSessionDeactivatedCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_UnregisterSessionDeactivatedCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val deactivatedCb = staticCFunction { _e: CValue<OH_AudioSession_DeactivatedEvent> -> 0 }
        OH_AudioSessionManager_RegisterSessionDeactivatedCallback(mgr, deactivatedCb)
        val ret = OH_AudioSessionManager_UnregisterSessionDeactivatedCallback(mgr, deactivatedCb)
        logLine("OH_AudioSessionManager_UnregisterSessionDeactivatedCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_SetScene() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val ret = try { OH_AudioSessionManager_SetScene(mgr, AUDIO_SESSION_SCENE_MEDIA) } catch (e: Throwable) { logLine("OH_AudioSessionManager_SetScene (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_SetScene ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_RegisterStateChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val stateChangeCb = staticCFunction { _e: CValue<OH_AudioSession_StateChangedEvent> -> }
        val ret = try { OH_AudioSessionManager_RegisterStateChangeCallback(mgr, stateChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_RegisterStateChangeCallback (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioSessionManager_UnregisterStateChangeCallback(mgr, stateChangeCb) } catch (_: Throwable) { }
        logLine("OH_AudioSessionManager_RegisterStateChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_UnregisterStateChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val stateChangeCb = staticCFunction { _e: CValue<OH_AudioSession_StateChangedEvent> -> }
        try { OH_AudioSessionManager_RegisterStateChangeCallback(mgr, stateChangeCb) } catch (_: Throwable) { }
        val ret = try { OH_AudioSessionManager_UnregisterStateChangeCallback(mgr, stateChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_UnregisterStateChangeCallback (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_UnregisterStateChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_SetDefaultOutputDevice() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val ret = try { OH_AudioSessionManager_SetDefaultOutputDevice(mgr, AUDIO_DEVICE_TYPE_SPEAKER) } catch (e: Throwable) { logLine("OH_AudioSessionManager_SetDefaultOutputDevice (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_SetDefaultOutputDevice ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_GetDefaultOutputDevice() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val deviceTypeVar = alloc<UIntVar>()
        val ret = try { OH_AudioSessionManager_GetDefaultOutputDevice(mgr, deviceTypeVar.ptr) } catch (e: Throwable) { logLine("OH_AudioSessionManager_GetDefaultOutputDevice (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_GetDefaultOutputDevice ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_RegisterCurrentOutputDeviceChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val outputDeviceChangeCb = staticCFunction { _devices: CPointer<OH_AudioDeviceDescriptorArray>?, _reason: OH_AudioStream_DeviceChangeReason, _action: OH_AudioSession_OutputDeviceChangeRecommendedAction -> }
        val ret = try { OH_AudioSessionManager_RegisterCurrentOutputDeviceChangeCallback(mgr, outputDeviceChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_RegisterCurrentOutputDeviceChangeCallback (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioSessionManager_UnregisterCurrentOutputDeviceChangeCallback(mgr, outputDeviceChangeCb) } catch (_: Throwable) { }
        logLine("OH_AudioSessionManager_RegisterCurrentOutputDeviceChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_UnregisterCurrentOutputDeviceChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val outputDeviceChangeCb = staticCFunction { _devices: CPointer<OH_AudioDeviceDescriptorArray>?, _reason: OH_AudioStream_DeviceChangeReason, _action: OH_AudioSession_OutputDeviceChangeRecommendedAction -> }
        try { OH_AudioSessionManager_RegisterCurrentOutputDeviceChangeCallback(mgr, outputDeviceChangeCb) } catch (_: Throwable) { }
        val ret = try { OH_AudioSessionManager_UnregisterCurrentOutputDeviceChangeCallback(mgr, outputDeviceChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_UnregisterCurrentOutputDeviceChangeCallback (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_UnregisterCurrentOutputDeviceChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_GetAvailableDevices() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val availableArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        val ret = try { OH_AudioSessionManager_GetAvailableDevices(mgr, AUDIO_DEVICE_USAGE_MEDIA_OUTPUT, availableArrayPtr.ptr) } catch (e: Throwable) { logLine("OH_AudioSessionManager_GetAvailableDevices (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        if (availableArrayPtr.value != null) try { OH_AudioSessionManager_ReleaseDevices(mgr, availableArrayPtr.value) } catch (_: Throwable) { }
        logLine("OH_AudioSessionManager_GetAvailableDevices ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_ReleaseDevices() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val availableArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        try { OH_AudioSessionManager_GetAvailableDevices(mgr, AUDIO_DEVICE_USAGE_MEDIA_OUTPUT, availableArrayPtr.ptr) } catch (_: Throwable) { }
        val ret = try { OH_AudioSessionManager_ReleaseDevices(mgr, availableArrayPtr.value) } catch (e: Throwable) { logLine("OH_AudioSessionManager_ReleaseDevices (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_ReleaseDevices ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_RegisterAvailableDevicesChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val availableDeviceChangeCb = staticCFunction { _type: OH_AudioDevice_ChangeType, _arr: CPointer<OH_AudioDeviceDescriptorArray>? -> }
        val ret = try { OH_AudioSessionManager_RegisterAvailableDevicesChangeCallback(mgr, AUDIO_DEVICE_USAGE_MEDIA_OUTPUT, availableDeviceChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_RegisterAvailableDevicesChangeCallback (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioSessionManager_UnregisterAvailableDevicesChangeCallback(mgr, availableDeviceChangeCb) } catch (_: Throwable) { }
        logLine("OH_AudioSessionManager_RegisterAvailableDevicesChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_UnregisterAvailableDevicesChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val availableDeviceChangeCb = staticCFunction { _type: OH_AudioDevice_ChangeType, _arr: CPointer<OH_AudioDeviceDescriptorArray>? -> }
        try { OH_AudioSessionManager_RegisterAvailableDevicesChangeCallback(mgr, AUDIO_DEVICE_USAGE_MEDIA_OUTPUT, availableDeviceChangeCb) } catch (_: Throwable) { }
        val ret = try { OH_AudioSessionManager_UnregisterAvailableDevicesChangeCallback(mgr, availableDeviceChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_UnregisterAvailableDevicesChangeCallback (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_UnregisterAvailableDevicesChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_SelectMediaInputDevice() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val ret = try { OH_AudioSessionManager_SelectMediaInputDevice(mgr, null) } catch (e: Throwable) { logLine("OH_AudioSessionManager_SelectMediaInputDevice (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_SelectMediaInputDevice ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_GetSelectedMediaInputDevice() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val selectedDevicePtr = alloc<CPointerVar<OH_AudioDeviceDescriptor>>()
        val ret = try { OH_AudioSessionManager_GetSelectedMediaInputDevice(mgr, selectedDevicePtr.ptr) } catch (e: Throwable) { logLine("OH_AudioSessionManager_GetSelectedMediaInputDevice (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        if (selectedDevicePtr.value != null) try { OH_AudioSessionManager_ReleaseDevice(mgr, selectedDevicePtr.value) } catch (_: Throwable) { }
        logLine("OH_AudioSessionManager_GetSelectedMediaInputDevice ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_SetBluetoothAndNearlinkPreferredRecordCategory() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val ret = try { OH_AudioSessionManager_SetBluetoothAndNearlinkPreferredRecordCategory(mgr, PREFERRED_NONE) } catch (e: Throwable) { logLine("OH_AudioSessionManager_SetBluetoothAndNearlinkPreferredRecordCategory (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_SetBluetoothAndNearlinkPreferredRecordCategory ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_GetBluetoothAndNearlinkPreferredRecordCategory() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val categoryVar = alloc<UIntVar>()
        val ret = try { OH_AudioSessionManager_GetBluetoothAndNearlinkPreferredRecordCategory(mgr, categoryVar.ptr) } catch (e: Throwable) { logLine("OH_AudioSessionManager_GetBluetoothAndNearlinkPreferredRecordCategory (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_GetBluetoothAndNearlinkPreferredRecordCategory ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_RegisterCurrentInputDeviceChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val inputDeviceChangeCb = staticCFunction { _devices: CPointer<OH_AudioDeviceDescriptorArray>?, _reason: OH_AudioStream_DeviceChangeReason -> }
        val ret = try { OH_AudioSessionManager_RegisterCurrentInputDeviceChangeCallback(mgr, inputDeviceChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_RegisterCurrentInputDeviceChangeCallback (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioSessionManager_UnregisterCurrentInputDeviceChangeCallback(mgr, inputDeviceChangeCb) } catch (_: Throwable) { }
        logLine("OH_AudioSessionManager_RegisterCurrentInputDeviceChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_UnregisterCurrentInputDeviceChangeCallback() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val inputDeviceChangeCb = staticCFunction { _devices: CPointer<OH_AudioDeviceDescriptorArray>?, _reason: OH_AudioStream_DeviceChangeReason -> }
        try { OH_AudioSessionManager_RegisterCurrentInputDeviceChangeCallback(mgr, inputDeviceChangeCb) } catch (_: Throwable) { }
        val ret = try { OH_AudioSessionManager_UnregisterCurrentInputDeviceChangeCallback(mgr, inputDeviceChangeCb) } catch (e: Throwable) { logLine("OH_AudioSessionManager_UnregisterCurrentInputDeviceChangeCallback (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_UnregisterCurrentInputDeviceChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioSessionManager_ReleaseDevice() { memScoped {
        val mgrPtr = alloc<CPointerVar<OH_AudioSessionManager>>()
        OH_AudioManager_GetAudioSessionManager(mgrPtr.ptr)
        val mgr = mgrPtr.value
        val selectedDevicePtr = alloc<CPointerVar<OH_AudioDeviceDescriptor>>()
        try { OH_AudioSessionManager_GetSelectedMediaInputDevice(mgr, selectedDevicePtr.ptr) } catch (_: Throwable) { }
        val ret = try { OH_AudioSessionManager_ReleaseDevice(mgr, selectedDevicePtr.value) } catch (e: Throwable) { logLine("OH_AudioSessionManager_ReleaseDevice (API 21) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioSessionManager_ReleaseDevice ret=$ret")
    } }

    // ==================== OH_AudioStreamManager (native_audio_stream_manager.h) ====================
    @Test
    fun testOH_AudioManager_GetAudioStreamManager() { memScoped {
        val streamMgrPtr = alloc<CPointerVar<OH_AudioStreamManager>>()
        val ret = try { OH_AudioManager_GetAudioStreamManager(streamMgrPtr.ptr) } catch (e: Throwable) { logLine("OH_AudioManager_GetAudioStreamManager (API 19) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioManager_GetAudioStreamManager ret=$ret")
    } }

    @Test
    fun testOH_AudioStreamManager_GetDirectPlaybackSupport() { memScoped {
        val streamMgrPtr = alloc<CPointerVar<OH_AudioStreamManager>>()
        try { OH_AudioManager_GetAudioStreamManager(streamMgrPtr.ptr) } catch (_: Throwable) { }
        val streamMgr = streamMgrPtr.value
        val streamInfo = alloc<OH_AudioStreamInfo>().apply {
            samplingRate = 48000
            channelLayout = 0uL
            encodingType = AUDIOSTREAM_ENCODING_TYPE_RAW
            sampleFormat = AUDIOSTREAM_SAMPLE_S16LE
        }
        val directPlaybackModeVar = alloc<UIntVar>()
        val ret = try { OH_AudioStreamManager_GetDirectPlaybackSupport(streamMgr, streamInfo.ptr, AUDIOSTREAM_USAGE_MUSIC, directPlaybackModeVar.ptr) } catch (e: Throwable) { logLine("OH_AudioStreamManager_GetDirectPlaybackSupport (API 19) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioStreamManager_GetDirectPlaybackSupport ret=$ret")
    } }

    @Test
    fun testOH_AudioStreamManager_IsAcousticEchoCancelerSupported() { memScoped {
        val streamMgrPtr = alloc<CPointerVar<OH_AudioStreamManager>>()
        try { OH_AudioManager_GetAudioStreamManager(streamMgrPtr.ptr) } catch (_: Throwable) { }
        val streamMgr = streamMgrPtr.value
        val supportedVar = alloc<BooleanVar>()
        val ret = try { OH_AudioStreamManager_IsAcousticEchoCancelerSupported(streamMgr, AUDIOSTREAM_SOURCE_TYPE_MIC, supportedVar.ptr) } catch (e: Throwable) { logLine("OH_AudioStreamManager_IsAcousticEchoCancelerSupported (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioStreamManager_IsAcousticEchoCancelerSupported ret=$ret")
    } }

    @Test
    fun testOH_AudioStreamManager_IsFastPlaybackSupported() { memScoped {
        val streamMgrPtr = alloc<CPointerVar<OH_AudioStreamManager>>()
        try { OH_AudioManager_GetAudioStreamManager(streamMgrPtr.ptr) } catch (_: Throwable) { }
        val streamMgr = streamMgrPtr.value
        val streamInfo = alloc<OH_AudioStreamInfo>().apply {
            samplingRate = 48000
            channelLayout = 0uL
            encodingType = AUDIOSTREAM_ENCODING_TYPE_RAW
            sampleFormat = AUDIOSTREAM_SAMPLE_S16LE
        }
        try { OH_AudioStreamManager_IsFastPlaybackSupported(streamMgr, streamInfo.ptr, AUDIOSTREAM_USAGE_MUSIC) } catch (e: Throwable) { logLine("OH_AudioStreamManager_IsFastPlaybackSupported (API 20) exception: $e") }
        logLine("OH_AudioStreamManager_IsFastPlaybackSupported called")
    } }

    @Test
    fun testOH_AudioStreamManager_IsFastRecordingSupported() { memScoped {
        val streamMgrPtr = alloc<CPointerVar<OH_AudioStreamManager>>()
        try { OH_AudioManager_GetAudioStreamManager(streamMgrPtr.ptr) } catch (_: Throwable) { }
        val streamMgr = streamMgrPtr.value
        val streamInfo = alloc<OH_AudioStreamInfo>().apply {
            samplingRate = 48000
            channelLayout = 0uL
            encodingType = AUDIOSTREAM_ENCODING_TYPE_RAW
            sampleFormat = AUDIOSTREAM_SAMPLE_S16LE
        }
        try { OH_AudioStreamManager_IsFastRecordingSupported(streamMgr, streamInfo.ptr, AUDIOSTREAM_SOURCE_TYPE_MIC) } catch (e: Throwable) { logLine("OH_AudioStreamManager_IsFastRecordingSupported (API 20) exception: $e") }
        logLine("OH_AudioStreamManager_IsFastRecordingSupported called")
    } }

    @Test
    fun testOH_AudioStreamManager_IsIntelligentNoiseReductionEnabledForCurrentDevice() { memScoped {
        val streamMgrPtr = alloc<CPointerVar<OH_AudioStreamManager>>()
        try { OH_AudioManager_GetAudioStreamManager(streamMgrPtr.ptr) } catch (_: Throwable) { }
        val streamMgr = streamMgrPtr.value
        try { OH_AudioStreamManager_IsIntelligentNoiseReductionEnabledForCurrentDevice(streamMgr, AUDIOSTREAM_SOURCE_TYPE_MIC) } catch (e: Throwable) { logLine("OH_AudioStreamManager_IsIntelligentNoiseReductionEnabledForCurrentDevice (API 21) exception: $e") }
        logLine("OH_AudioStreamManager_IsIntelligentNoiseReductionEnabledForCurrentDevice called")
    } }

    // ==================== OH_AudioVolumeManager (native_audio_volume_manager.h) ====================
    @Test
    fun testOH_AudioManager_GetAudioVolumeManager() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        val ret = OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        logLine("OH_AudioManager_GetAudioVolumeManager ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioManager_GetAudioVolumeManager (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_GetMaxVolumeByUsage() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val maxVolVar = alloc<IntVar>()
        val ret = OH_AudioVolumeManager_GetMaxVolumeByUsage(volMgr, AUDIOSTREAM_USAGE_MUSIC, maxVolVar.ptr)
        logLine("OH_AudioVolumeManager_GetMaxVolumeByUsage ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_GetMaxVolumeByUsage (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_GetMinVolumeByUsage() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val minVolVar = alloc<IntVar>()
        val ret = OH_AudioVolumeManager_GetMinVolumeByUsage(volMgr, AUDIOSTREAM_USAGE_MUSIC, minVolVar.ptr)
        logLine("OH_AudioVolumeManager_GetMinVolumeByUsage ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_GetMinVolumeByUsage (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_GetVolumeByUsage() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val volVar = alloc<IntVar>()
        val ret = OH_AudioVolumeManager_GetVolumeByUsage(volMgr, AUDIOSTREAM_USAGE_MUSIC, volVar.ptr)
        logLine("OH_AudioVolumeManager_GetVolumeByUsage ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_GetVolumeByUsage (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_IsMuteByUsage() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val mutedVar = alloc<BooleanVar>()
        val ret = OH_AudioVolumeManager_IsMuteByUsage(volMgr, AUDIOSTREAM_USAGE_MUSIC, mutedVar.ptr)
        logLine("OH_AudioVolumeManager_IsMuteByUsage ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_IsMuteByUsage (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_RegisterStreamVolumeChangeCallback() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val streamVolCb = staticCFunction { _userData: COpaquePointer?, _usage: OH_AudioStream_Usage, _volumeLevel: Int, _updateUi: Boolean -> }
        val ret = OH_AudioVolumeManager_RegisterStreamVolumeChangeCallback(volMgr, AUDIOSTREAM_USAGE_MUSIC, streamVolCb, null)
        OH_AudioVolumeManager_UnregisterStreamVolumeChangeCallback(volMgr, streamVolCb)
        logLine("OH_AudioVolumeManager_RegisterStreamVolumeChangeCallback ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_RegisterStreamVolumeChangeCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_UnregisterStreamVolumeChangeCallback() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val streamVolCb = staticCFunction { _userData: COpaquePointer?, _usage: OH_AudioStream_Usage, _volumeLevel: Int, _updateUi: Boolean -> }
        OH_AudioVolumeManager_RegisterStreamVolumeChangeCallback(volMgr, AUDIOSTREAM_USAGE_MUSIC, streamVolCb, null)
        val ret = OH_AudioVolumeManager_UnregisterStreamVolumeChangeCallback(volMgr, streamVolCb)
        logLine("OH_AudioVolumeManager_UnregisterStreamVolumeChangeCallback ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_UnregisterStreamVolumeChangeCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_GetRingerMode() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val ringerModeVar = alloc<UIntVar>()
        val ret = OH_AudioVolumeManager_GetRingerMode(volMgr, ringerModeVar.ptr)
        logLine("OH_AudioVolumeManager_GetRingerMode ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_GetRingerMode (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_RegisterRingerModeChangeCallback() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val ringerModeCb = staticCFunction { _userData: COpaquePointer?, _ringerMode: UInt -> }
        val ret = OH_AudioVolumeManager_RegisterRingerModeChangeCallback(volMgr, ringerModeCb, null)
        OH_AudioVolumeManager_UnregisterRingerModeChangeCallback(volMgr, ringerModeCb)
        logLine("OH_AudioVolumeManager_RegisterRingerModeChangeCallback ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_RegisterRingerModeChangeCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioVolumeManager_UnregisterRingerModeChangeCallback() { memScoped {
        try {
        val volMgrPtr = alloc<CPointerVar<OH_AudioVolumeManager>>()
        OH_AudioManager_GetAudioVolumeManager(volMgrPtr.ptr)
        val volMgr = volMgrPtr.value
        val ringerModeCb = staticCFunction { _userData: COpaquePointer?, _ringerMode: UInt -> }
        OH_AudioVolumeManager_RegisterRingerModeChangeCallback(volMgr, ringerModeCb, null)
        val ret = OH_AudioVolumeManager_UnregisterRingerModeChangeCallback(volMgr, ringerModeCb)
        logLine("OH_AudioVolumeManager_UnregisterRingerModeChangeCallback ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioVolumeManager_UnregisterRingerModeChangeCallback (API >17) exception: $e") }
    } }

    // ==================== OH_AudioManager (native_audio_manager.h) ====================
    @Test
    fun testOH_GetAudioManager() { memScoped {
        val audioMgrPtr = alloc<CPointerVar<OH_AudioManager>>()
        val ret = OH_GetAudioManager(audioMgrPtr.ptr)
        logLine("OH_GetAudioManager ret=$ret")
    } }

    @Test
    fun testOH_GetAudioScene() { memScoped {
        val audioMgrPtr = alloc<CPointerVar<OH_AudioManager>>()
        OH_GetAudioManager(audioMgrPtr.ptr)
        val mgr = audioMgrPtr.value
        val sceneVar = alloc<UIntVar>()
        val ret = OH_GetAudioScene(mgr, sceneVar.ptr)
        logLine("OH_GetAudioScene ret=$ret")
    } }

    @Test
    fun testOH_AudioManager_RegisterAudioSceneChangeCallback() { memScoped {
        try {
        val audioMgrPtr = alloc<CPointerVar<OH_AudioManager>>()
        OH_GetAudioManager(audioMgrPtr.ptr)
        val mgr = audioMgrPtr.value
        val sceneChangeCb = staticCFunction { _userData: COpaquePointer?, _scene: OH_AudioScene -> }
        val ret = OH_AudioManager_RegisterAudioSceneChangeCallback(mgr, sceneChangeCb, null)
        OH_AudioManager_UnregisterAudioSceneChangeCallback(mgr, sceneChangeCb)
        logLine("OH_AudioManager_RegisterAudioSceneChangeCallback ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioManager_RegisterAudioSceneChangeCallback (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioManager_UnregisterAudioSceneChangeCallback() { memScoped {
        try {
        val audioMgrPtr = alloc<CPointerVar<OH_AudioManager>>()
        OH_GetAudioManager(audioMgrPtr.ptr)
        val mgr = audioMgrPtr.value
        val sceneChangeCb = staticCFunction { _userData: COpaquePointer?, _scene: OH_AudioScene -> }
        OH_AudioManager_RegisterAudioSceneChangeCallback(mgr, sceneChangeCb, null)
        val ret = OH_AudioManager_UnregisterAudioSceneChangeCallback(mgr, sceneChangeCb)
        logLine("OH_AudioManager_UnregisterAudioSceneChangeCallback ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioManager_UnregisterAudioSceneChangeCallback (API >17) exception: $e") }
    } }

    // ==================== OH_AudioResourceManager (native_audio_resource_manager.h) ====================
    @Test
    fun testOH_AudioManager_GetAudioResourceManager() { memScoped {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        val ret = try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (e: Throwable) { logLine("OH_AudioManager_GetAudioResourceManager (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioManager_GetAudioResourceManager ret=$ret")
    } }

    @Test
    fun testOH_AudioResourceManager_CreateWorkgroup() { memScoped {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (_: Throwable) { }
        val resMgr = resMgrPtr.value
        val groupPtr = alloc<CPointerVar<OH_AudioWorkgroup>>()
        try {
            OH_AudioResourceManager_CreateWorkgroup(resMgr, "test", groupPtr.ptr)
            logLine("OH_AudioResourceManager_CreateWorkgroup ok")
            if (groupPtr.value != null) OH_AudioResourceManager_ReleaseWorkgroup(resMgr, groupPtr.value)
        } catch (e: Throwable) { logLine("OH_AudioResourceManager_CreateWorkgroup (API 20) exception: $e") }
    } }

    @Test
    fun testOH_AudioWorkgroup_AddCurrentThread() { memScoped {
        try {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (_: Throwable) { }
        val resMgr = resMgrPtr.value
        val groupPtr = alloc<CPointerVar<OH_AudioWorkgroup>>()
        try { OH_AudioResourceManager_CreateWorkgroup(resMgr, "test", groupPtr.ptr) } catch (_: Throwable) { }
        val group = groupPtr.value
        val tokenIdVar = alloc<IntVar>()
        val ret = try { OH_AudioWorkgroup_AddCurrentThread(group, tokenIdVar.ptr) } catch (e: Throwable) { logLine("OH_AudioWorkgroup_AddCurrentThread (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioWorkgroup_RemoveThread(group, tokenIdVar.value) } catch (_: Throwable) { }
        OH_AudioResourceManager_ReleaseWorkgroup(resMgr, group)
        logLine("OH_AudioWorkgroup_AddCurrentThread ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioWorkgroup_AddCurrentThread (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioWorkgroup_Start() { memScoped {
        try {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (_: Throwable) { }
        val resMgr = resMgrPtr.value
        val groupPtr = alloc<CPointerVar<OH_AudioWorkgroup>>()
        try { OH_AudioResourceManager_CreateWorkgroup(resMgr, "test", groupPtr.ptr) } catch (_: Throwable) { }
        val group = groupPtr.value
        val tokenIdVar = alloc<IntVar>()
        try { OH_AudioWorkgroup_AddCurrentThread(group, tokenIdVar.ptr) } catch (_: Throwable) { }
        val ret = try { OH_AudioWorkgroup_Start(group, 0uL, 1000uL) } catch (e: Throwable) { logLine("OH_AudioWorkgroup_Start (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioWorkgroup_Stop(group) } catch (_: Throwable) { }
        try { OH_AudioWorkgroup_RemoveThread(group, tokenIdVar.value) } catch (_: Throwable) { }
        OH_AudioResourceManager_ReleaseWorkgroup(resMgr, group)
        logLine("OH_AudioWorkgroup_Start ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioWorkgroup_Start (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioWorkgroup_Stop() { memScoped {
        try {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (_: Throwable) { }
        val resMgr = resMgrPtr.value
        val groupPtr = alloc<CPointerVar<OH_AudioWorkgroup>>()
        try { OH_AudioResourceManager_CreateWorkgroup(resMgr, "test", groupPtr.ptr) } catch (_: Throwable) { }
        val group = groupPtr.value
        val tokenIdVar = alloc<IntVar>()
        try { OH_AudioWorkgroup_AddCurrentThread(group, tokenIdVar.ptr) } catch (_: Throwable) { }
        try { OH_AudioWorkgroup_Start(group, 0uL, 1000uL) } catch (_: Throwable) { }
        val ret = try { OH_AudioWorkgroup_Stop(group) } catch (e: Throwable) { logLine("OH_AudioWorkgroup_Stop (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        try { OH_AudioWorkgroup_RemoveThread(group, tokenIdVar.value) } catch (_: Throwable) { }
        OH_AudioResourceManager_ReleaseWorkgroup(resMgr, group)
        logLine("OH_AudioWorkgroup_Stop ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioWorkgroup_Stop (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioWorkgroup_RemoveThread() { memScoped {
        try {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (_: Throwable) { }
        val resMgr = resMgrPtr.value
        val groupPtr = alloc<CPointerVar<OH_AudioWorkgroup>>()
        try { OH_AudioResourceManager_CreateWorkgroup(resMgr, "test", groupPtr.ptr) } catch (_: Throwable) { }
        val group = groupPtr.value
        val tokenIdVar = alloc<IntVar>()
        try { OH_AudioWorkgroup_AddCurrentThread(group, tokenIdVar.ptr) } catch (_: Throwable) { }
        val ret = try { OH_AudioWorkgroup_RemoveThread(group, tokenIdVar.value) } catch (e: Throwable) { logLine("OH_AudioWorkgroup_RemoveThread (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        OH_AudioResourceManager_ReleaseWorkgroup(resMgr, group)
        logLine("OH_AudioWorkgroup_RemoveThread ret=$ret")
        } catch (e: Throwable) { logLine("testOH_AudioWorkgroup_RemoveThread (API >17) exception: $e") }
    } }

    @Test
    fun testOH_AudioResourceManager_ReleaseWorkgroup() { memScoped {
        val resMgrPtr = alloc<CPointerVar<OH_AudioResourceManager>>()
        try { OH_AudioManager_GetAudioResourceManager(resMgrPtr.ptr) } catch (_: Throwable) { }
        val resMgr = resMgrPtr.value
        val groupPtr = alloc<CPointerVar<OH_AudioWorkgroup>>()
        try { OH_AudioResourceManager_CreateWorkgroup(resMgr, "test", groupPtr.ptr) } catch (_: Throwable) { }
        val group = groupPtr.value
        val ret = try { OH_AudioResourceManager_ReleaseWorkgroup(resMgr, group) } catch (e: Throwable) { logLine("OH_AudioResourceManager_ReleaseWorkgroup (API 20) exception: $e"); AUDIOCOMMON_RESULT_ERROR_INVALID_PARAM }
        logLine("OH_AudioResourceManager_ReleaseWorkgroup ret=$ret")
    } }

    // ==================== OH_AudioRoutingManager (native_audio_routing_manager.h) ====================
    @Test
    fun testOH_AudioManager_GetAudioRoutingManager() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        val ret = OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        logLine("OH_AudioManager_GetAudioRoutingManager ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_GetDevices() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        val ret = OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        if (devicesArrayPtr.value != null) OH_AudioRoutingManager_ReleaseDevices(routingMgr, devicesArrayPtr.value)
        logLine("OH_AudioRoutingManager_GetDevices ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_ReleaseDevices() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val ret = OH_AudioRoutingManager_ReleaseDevices(routingMgr, devicesArrayPtr.value)
        logLine("OH_AudioRoutingManager_ReleaseDevices ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_GetAvailableDevices() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val availableArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        val ret = OH_AudioRoutingManager_GetAvailableDevices(routingMgr, AUDIO_DEVICE_USAGE_MEDIA_OUTPUT, availableArrayPtr.ptr)
        if (availableArrayPtr.value != null) OH_AudioRoutingManager_ReleaseDevices(routingMgr, availableArrayPtr.value)
        logLine("OH_AudioRoutingManager_GetAvailableDevices ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_GetPreferredOutputDevice() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val preferredOutPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        val ret = OH_AudioRoutingManager_GetPreferredOutputDevice(routingMgr, AUDIOSTREAM_USAGE_MUSIC, preferredOutPtr.ptr)
        if (preferredOutPtr.value != null) OH_AudioRoutingManager_ReleaseDevices(routingMgr, preferredOutPtr.value)
        logLine("OH_AudioRoutingManager_GetPreferredOutputDevice ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_GetPreferredInputDevice() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val preferredInPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        val ret = OH_AudioRoutingManager_GetPreferredInputDevice(routingMgr, AUDIOSTREAM_SOURCE_TYPE_MIC, preferredInPtr.ptr)
        if (preferredInPtr.value != null) OH_AudioRoutingManager_ReleaseDevices(routingMgr, preferredInPtr.value)
        logLine("OH_AudioRoutingManager_GetPreferredInputDevice ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_RegisterDeviceChangeCallback() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val deviceChangeCb = staticCFunction { _type: OH_AudioDevice_ChangeType, _arr: CPointer<OH_AudioDeviceDescriptorArray>? -> 0 }
        val ret = OH_AudioRoutingManager_RegisterDeviceChangeCallback(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, deviceChangeCb)
        OH_AudioRoutingManager_UnregisterDeviceChangeCallback(routingMgr, deviceChangeCb)
        logLine("OH_AudioRoutingManager_RegisterDeviceChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_UnregisterDeviceChangeCallback() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val deviceChangeCb = staticCFunction { _type: OH_AudioDevice_ChangeType, _arr: CPointer<OH_AudioDeviceDescriptorArray>? -> 0 }
        OH_AudioRoutingManager_RegisterDeviceChangeCallback(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, deviceChangeCb)
        val ret = OH_AudioRoutingManager_UnregisterDeviceChangeCallback(routingMgr, deviceChangeCb)
        logLine("OH_AudioRoutingManager_UnregisterDeviceChangeCallback ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_IsMicBlockDetectionSupported() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val supportedVar = alloc<BooleanVar>()
        val ret = OH_AudioRoutingManager_IsMicBlockDetectionSupported(routingMgr, supportedVar.ptr)
        logLine("OH_AudioRoutingManager_IsMicBlockDetectionSupported ret=$ret")
    } }

    @Test
    fun testOH_AudioRoutingManager_SetMicBlockStatusCallback() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val blockStatusCb = staticCFunction { _arr: CPointer<OH_AudioDeviceDescriptorArray>?, _status: OH_AudioDevice_BlockStatus, _userData: COpaquePointer? -> }
        val ret = OH_AudioRoutingManager_SetMicBlockStatusCallback(routingMgr, blockStatusCb, null)
        logLine("OH_AudioRoutingManager_SetMicBlockStatusCallback ret=$ret")
    } }

    // ==================== OH_AudioDeviceDescriptor (native_audio_device_base.h) ====================

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceRole() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val roleVar = alloc<UIntVar>()
        val ret = OH_AudioDeviceDescriptor_GetDeviceRole(firstDescPtr, roleVar.ptr)
        logLine("OH_AudioDeviceDescriptor_GetDeviceRole ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceType() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val typeVar = alloc<UIntVar>()
        val ret = OH_AudioDeviceDescriptor_GetDeviceType(firstDescPtr, typeVar.ptr)
        logLine("OH_AudioDeviceDescriptor_GetDeviceType ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceId() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val idVar = alloc<UIntVar>()
        val ret = OH_AudioDeviceDescriptor_GetDeviceId(firstDescPtr, idVar.ptr)
        logLine("OH_AudioDeviceDescriptor_GetDeviceId ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceName() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val ret = OH_AudioDeviceDescriptor_GetDeviceName(firstDescPtr, null)
        logLine("OH_AudioDeviceDescriptor_GetDeviceName ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceAddress() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val ret = OH_AudioDeviceDescriptor_GetDeviceAddress(firstDescPtr, null)
        logLine("OH_AudioDeviceDescriptor_GetDeviceAddress ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceSampleRates() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val sampleRatesSizeVar = alloc<UIntVar>()
        val ret = OH_AudioDeviceDescriptor_GetDeviceSampleRates(firstDescPtr, null, sampleRatesSizeVar.ptr)
        logLine("OH_AudioDeviceDescriptor_GetDeviceSampleRates ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceChannelCounts() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val channelCountsSizeVar = alloc<UIntVar>()
        val ret = OH_AudioDeviceDescriptor_GetDeviceChannelCounts(firstDescPtr, null, channelCountsSizeVar.ptr)
        logLine("OH_AudioDeviceDescriptor_GetDeviceChannelCounts ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceDisplayName() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val ret = OH_AudioDeviceDescriptor_GetDeviceDisplayName(firstDescPtr, null)
        logLine("OH_AudioDeviceDescriptor_GetDeviceDisplayName ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }

    @Test
    fun testOH_AudioDeviceDescriptor_GetDeviceEncodingTypes() { memScoped {
        val routingMgrPtr = alloc<CPointerVar<OH_AudioRoutingManager>>()
        OH_AudioManager_GetAudioRoutingManager(routingMgrPtr.ptr)
        val routingMgr = routingMgrPtr.value
        val devicesArrayPtr = alloc<CPointerVar<OH_AudioDeviceDescriptorArray>>()
        OH_AudioRoutingManager_GetDevices(routingMgr, AUDIO_DEVICE_FLAG_OUTPUT, devicesArrayPtr.ptr)
        val arr = devicesArrayPtr.value
        val firstDescPtr = arr?.pointed?.descriptors?.reinterpret<CPointerVar<OH_AudioDeviceDescriptor>>()?.pointed?.value
        val encodingTypesSizeVar = alloc<UIntVar>()
        val ret = OH_AudioDeviceDescriptor_GetDeviceEncodingTypes(firstDescPtr, null, encodingTypesSizeVar.ptr)
        logLine("OH_AudioDeviceDescriptor_GetDeviceEncodingTypes ret=$ret")
        OH_AudioRoutingManager_ReleaseDevices(routingMgr, arr)
    } }
}
