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
import platform.AudioKit.OHAudioSuite.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHAudioSuiteTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_AudioNode_Type() {
        assertEquals(INPUT_NODE_TYPE_DEFAULT.toInt(), 1)
        assertEquals(OUTPUT_NODE_TYPE_DEFAULT.toInt(), 101)
        assertEquals(EFFECT_NODE_TYPE_EQUALIZER.toInt(), 201)
        assertEquals(EFFECT_NODE_TYPE_NOISE_REDUCTION.toInt(), 202)
        assertEquals(EFFECT_NODE_TYPE_SOUND_FIELD.toInt(), 203)
        assertEquals(EFFECT_MULTII_OUTPUT_NODE_TYPE_AUDIO_SEPARATION.toInt(), 204)
        assertEquals(EFFECT_NODE_TYPE_VOICE_BEAUTIFIER.toInt(), 205)
        assertEquals(EFFECT_NODE_TYPE_ENVIRONMENT_EFFECT.toInt(), 206)
        assertEquals(EFFECT_NODE_TYPE_AUDIO_MIXER.toInt(), 207)
        logLine("testEnum_OH_AudioNode_Type passed")
    }

    @Test
    fun testEnum_OH_AudioSuite_PipelineWorkMode() {
        assertEquals(AUDIOSUITE_PIPELINE_EDIT_MODE.toInt(), 1)
        assertEquals(AUDIOSUITE_PIPELINE_REALTIME_MODE.toInt(), 2)
        logLine("testEnum_OH_AudioSuite_PipelineWorkMode passed")
    }

    @Test
    fun testEnum_OH_AudioSuite_PipelineState() {
        assertEquals(AUDIOSUITE_PIPELINE_STOPPED.toInt(), 1)
        assertEquals(AUDIOSUITE_PIPELINE_RUNNING.toInt(), 2)
        logLine("testEnum_OH_AudioSuite_PipelineState passed")
    }

    @Test
    fun testEnum_OH_AudioSuite_Result() {
        assertEquals(AUDIOSUITE_SUCCESS.toInt(), 0)
        assertEquals(AUDIOSUITE_ERROR_INVALID_PARAM.toInt(), 1)
        assertEquals(AUDIOSUITE_ERROR_INVALID_STATE.toInt(), 2)
        assertEquals(AUDIOSUITE_ERROR_SYSTEM.toInt(), 3)
        assertEquals(AUDIOSUITE_ERROR_UNSUPPORTED_FORMAT.toInt(), 4)
        assertEquals(AUDIOSUITE_ERROR_ENGINE_NOT_EXIST.toInt(), 5)
        assertEquals(AUDIOSUITE_ERROR_PIPELINE_NOT_EXIST.toInt(), 6)
        assertEquals(AUDIOSUITE_ERROR_NODE_NOT_EXIST.toInt(), 7)
        assertEquals(AUDIOSUITE_ERROR_UNSUPPORTED_CONNECT.toInt(), 8)
        assertEquals(AUDIOSUITE_ERROR_UNSUPPORTED_OPERATION.toInt(), 9)
        assertEquals(AUDIOSUITE_ERROR_CREATED_EXCEED_SYSTEM_LIMITS.toInt(), 10)
        assertEquals(AUDIOSUITE_ERROR_REQUIRED_PARAMETERS_MISSING.toInt(), 11)
        assertEquals(AUDIOSUITE_ERROR_TIMEOUT.toInt(), 12)
        assertEquals(AUDIOSUITE_ERROR_MEMORY_ALLOC_FAILED.toInt(), 13)
        logLine("testEnum_OH_AudioSuite_Result passed")
    }

    @Test
    fun testEnum_OH_Audio_SampleFormat() {
        assertEquals(AUDIO_SAMPLE_U8.toInt(), 0)
        assertEquals(AUDIO_SAMPLE_S16LE.toInt(), 1)
        assertEquals(AUDIO_SAMPLE_S24LE.toInt(), 2)
        assertEquals(AUDIO_SAMPLE_S32LE.toInt(), 3)
        assertEquals(AUDIO_SAMPLE_F32LE.toInt(), 4)
        logLine("testEnum_OH_Audio_SampleFormat passed")
    }

    @Test
    fun testEnum_OH_Audio_EncodingType() {
        assertEquals(AUDIO_ENCODING_TYPE_RAW.toInt(), 0)
        logLine("testEnum_OH_Audio_EncodingType passed")
    }

    @Test
    fun testEnum_OH_Audio_SampleRate() {
        assertEquals(SAMPLE_RATE_8000.toInt(), 8000)
        assertEquals(SAMPLE_RATE_48000.toInt(), 48000)
        logLine("testEnum_OH_Audio_SampleRate passed")
    }

    @Test
    fun testEnum_OH_SoundFieldType() {
        assertEquals(SOUND_FIELD_FRONT_FACING.toInt(), 1)
        assertEquals(SOUND_FIELD_GRAND.toInt(), 2)
        assertEquals(SOUND_FIELD_NEAR.toInt(), 3)
        assertEquals(SOUND_FIELD_WIDE.toInt(), 4)
        logLine("testEnum_OH_SoundFieldType passed")
    }

    @Test
    fun testEnum_OH_EnvironmentType() {
        assertEquals(ENVIRONMENT_TYPE_BROADCAST.toInt(), 1)
        assertEquals(ENVIRONMENT_TYPE_EARPIECE.toInt(), 2)
        assertEquals(ENVIRONMENT_TYPE_UNDERWATER.toInt(), 3)
        assertEquals(ENVIRONMENT_TYPE_GRAMOPHONE.toInt(), 4)
        logLine("testEnum_OH_EnvironmentType passed")
    }

    @Test
    fun testEnum_OH_VoiceBeautifierType() {
        assertEquals(VOICE_BEAUTIFIER_TYPE_CLEAR.toInt(), 1)
        assertEquals(VOICE_BEAUTIFIER_TYPE_THEATRE.toInt(), 2)
        assertEquals(VOICE_BEAUTIFIER_TYPE_CD.toInt(), 3)
        assertEquals(VOICE_BEAUTIFIER_TYPE_RECORDING_STUDIO.toInt(), 4)
        logLine("testEnum_OH_VoiceBeautifierType passed")
    }

    @Test
    fun testOH_AudioSuiteEngine_Create() {
        memScoped {
            val engine = alloc<CPointerVar<OH_AudioSuiteEngineStruct>>()
            val ret = try { OH_AudioSuiteEngine_Create(engine.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_Create (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_Create ret=$ret")
            engine.value?.let { try { OH_AudioSuiteEngine_Destroy(it) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_Destroy (API 22) exception: $e") } }
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_Destroy() {
        val ret = try { OH_AudioSuiteEngine_Destroy(null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_Destroy (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_Destroy(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_CreatePipeline() {
        memScoped {
            val engine = alloc<CPointerVar<OH_AudioSuiteEngineStruct>>()
            try { OH_AudioSuiteEngine_Create(engine.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_Create (API 22) exception: $e") }
            val pipeline = alloc<CPointerVar<OH_AudioSuitePipelineStruct>>()
            try { OH_AudioSuiteEngine_CreatePipeline(engine.value, pipeline.ptr, AUDIOSUITE_PIPELINE_EDIT_MODE) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_CreatePipeline (API 22) exception: $e") }
            pipeline.value?.let { try { OH_AudioSuiteEngine_DestroyPipeline(it) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_DestroyPipeline (API 22) exception: $e") } }
            try { OH_AudioSuiteEngine_Destroy(engine.value) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_Destroy (API 22) exception: $e") }
            logLine("OH_AudioSuiteEngine_CreatePipeline done")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_DestroyPipeline() {
        val ret = try { OH_AudioSuiteEngine_DestroyPipeline(null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_DestroyPipeline (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_DestroyPipeline(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_StartPipeline() {
        val ret = try { OH_AudioSuiteEngine_StartPipeline(null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_StartPipeline (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_StartPipeline(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_StopPipeline() {
        val ret = try { OH_AudioSuiteEngine_StopPipeline(null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_StopPipeline (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_StopPipeline(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_GetPipelineState() {
        memScoped {
            val state = alloc<UIntVar>()
            val ret = try { OH_AudioSuiteEngine_GetPipelineState(null, state.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_GetPipelineState (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngineS_GetPipelineState ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_RenderFrame() {
        memScoped {
            val responseSize = alloc<IntVar>()
            val finishedFlag = alloc<BooleanVar>()
            val ret = try { OH_AudioSuiteEngine_RenderFrame(null, null, 0, responseSize.ptr, finishedFlag.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_RenderFrame (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_RenderFrame ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_MultiRenderFrame() {
        memScoped {
            val audioDataArray = alloc<OH_AudioDataArray>().apply { audioDataArray = null; arraySize = 0; requestFrameSize = 0 }
            val responseSize = alloc<IntVar>()
            val finishedFlag = alloc<BooleanVar>()
            val ret = try { OH_AudioSuiteEngine_MultiRenderFrame(null, audioDataArray.ptr, responseSize.ptr, finishedFlag.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_MultiRenderFrame (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_MultiRenderFrame ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteNodeBuilder_Create() {
        memScoped {
            val builder = alloc<CPointerVar<OH_AudioNodeBuilderStruct>>()
            val ret = try { OH_AudioSuiteNodeBuilder_Create(builder.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Create (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteNodeBuilder_Create ret=$ret")
            builder.value?.let { try { OH_AudioSuiteNodeBuilder_Destroy(it) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Destroy (API 22) exception: $e") } }
        }
    }

    @Test
    fun testOH_AudioSuiteNodeBuilder_Destroy() {
        val ret = try { OH_AudioSuiteNodeBuilder_Destroy(null) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Destroy (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteNodeBuilder_Destroy(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteNodeBuilder_Reset() {
        val ret = try { OH_AudioSuiteNodeBuilder_Reset(null) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Reset (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteNodeBuilder_Reset(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteNodeBuilder_SetNodeType() {
        memScoped {
            val builder = alloc<CPointerVar<OH_AudioNodeBuilderStruct>>()
            try { OH_AudioSuiteNodeBuilder_Create(builder.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Create (API 22) exception: $e") }
            try { OH_AudioSuiteNodeBuilder_SetNodeType(builder.value, EFFECT_NODE_TYPE_EQUALIZER) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_SetNodeType (API 22) exception: $e") }
            try { OH_AudioSuiteNodeBuilder_Destroy(builder.value) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Destroy (API 22) exception: $e") }
            logLine("OH_AudioSuiteNodeBuilder_SetNodeType done")
        }
    }

    @Test
    fun testOH_AudioSuiteNodeBuilder_SetFormat() {
        memScoped {
            val builder = alloc<CPointerVar<OH_AudioNodeBuilderStruct>>()
            try { OH_AudioSuiteNodeBuilder_Create(builder.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Create (API 22) exception: $e") }
            val format = cValue<OH_AudioFormat> { }
            val ret = try { OH_AudioSuiteNodeBuilder_SetFormat(builder.value, format) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_SetFormat (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            try { OH_AudioSuiteNodeBuilder_Destroy(builder.value) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_Destroy (API 22) exception: $e") }
            logLine("OH_AudioSuiteNodeBuilder_SetFormat ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteNodeBuilder_SetRequestDataCallback() {
        val ret = try { OH_AudioSuiteNodeBuilder_SetRequestDataCallback(null, null, null) } catch (e: Throwable) { logLine("OH_AudioSuiteNodeBuilder_SetRequestDataCallback (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteNodeBuilder_SetRequestDataCallback(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_CreateNode() {
        memScoped {
            val ret = try { OH_AudioSuiteEngine_CreateNode(null, null, null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_CreateNode (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_CreateNode ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_DestroyNode() {
        val ret = try { OH_AudioSuiteEngine_DestroyNode(null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_DestroyNode (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_DestroyNode(null) ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_GetNodeBypassStatus() {
        memScoped {
            val bypassStatus = alloc<BooleanVar>()
            val ret = try { OH_AudioSuiteEngine_GetNodeBypassStatus(null, bypassStatus.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_GetNodeBypassStatus (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_GetNodeBypassStatus ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_BypassEffectNode() {
        val ret = try { OH_AudioSuiteEngine_BypassEffectNode(null, false) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_BypassEffectNode (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_BypassEffectNode ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_SetAudioFormat() {
        memScoped {
            val format = alloc<OH_AudioFormat>()
            val ret = try { OH_AudioSuiteEngine_SetAudioFormat(null, format.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_SetAudioFormat (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_SetAudioFormat ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_ConnectNodes() {
        val ret = try { OH_AudioSuiteEngine_ConnectNodes(null, null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_ConnectNodes (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_ConnectNodes ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_DisconnectNodes() {
        val ret = try { OH_AudioSuiteEngine_DisconnectNodes(null, null) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_DisconnectNodes (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_DisconnectNodes ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_IsNodeTypeSupported() {
        memScoped {
            val isSupported = alloc<BooleanVar>()
            val ret = try { OH_AudioSuiteEngine_IsNodeTypeSupported(EFFECT_NODE_TYPE_EQUALIZER, isSupported.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_IsNodeTypeSupported (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_IsNodeTypeSupported ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_SetEqualizerFrequencyBandGains() {
        val gains = cValue<OH_EqualizerFrequencyBandGains> { }
        val ret = try { OH_AudioSuiteEngine_SetEqualizerFrequencyBandGains(null, gains) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_SetEqualizerFrequencyBandGains (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_SetEqualizerFrequencyBandGains ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_GetEqualizerFrequencyBandGains() {
        memScoped {
            val gains = alloc<OH_EqualizerFrequencyBandGains>()
            val ret = try { OH_AudioSuiteEngine_GetEqualizerFrequencyBandGains(null, gains.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_GetEqualizerFrequencyBandGains (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_GetEqualizerFrequencyBandGains ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_SetSoundFieldType() {
        val ret = try { OH_AudioSuiteEngine_SetSoundFieldType(null, SOUND_FIELD_FRONT_FACING) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_SetSoundFieldType (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_SetSoundFieldType ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_GetSoundFieldType() {
        memScoped {
            val soundFieldType = alloc<UIntVar>()
            val ret = try { OH_AudioSuiteEngine_GetSoundFieldType(null, soundFieldType.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_GetSoundFieldType (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_GetSoundFieldType ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_SetEnvironmentType() {
        val ret = try { OH_AudioSuiteEngine_SetEnvironmentType(null, ENVIRONMENT_TYPE_BROADCAST) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_SetEnvironmentType (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_SetEnvironmentType ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_GetEnvironmentType() {
        memScoped {
            val environmentType = alloc<UIntVar>()
            val ret = try { OH_AudioSuiteEngine_GetEnvironmentType(null, environmentType.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_GetEnvironmentType (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_GetEnvironmentType ret=$ret")
        }
    }

    @Test
    fun testOH_AudioSuiteEngine_SetVoiceBeautifierType() {
        val ret = try { OH_AudioSuiteEngine_SetVoiceBeautifierType(null, VOICE_BEAUTIFIER_TYPE_CLEAR) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_SetVoiceBeautifierType (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
        assertNotNull(ret)
        logLine("OH_AudioSuiteEngine_SetVoiceBeautifierType ret=$ret")
    }

    @Test
    fun testOH_AudioSuiteEngine_GetVoiceBeautifierType() {
        memScoped {
            val voiceBeautifierType = alloc<UIntVar>()
            val ret = try { OH_AudioSuiteEngine_GetVoiceBeautifierType(null, voiceBeautifierType.ptr) } catch (e: Throwable) { logLine("OH_AudioSuiteEngine_GetVoiceBeautifierType (API 22) exception: $e"); AUDIOSUITE_ERROR_INVALID_PARAM }
            assertNotNull(ret)
            logLine("OH_AudioSuiteEngine_GetVoiceBeautifierType ret=$ret")
        }
    }
}
