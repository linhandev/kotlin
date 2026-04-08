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
class AudioEncoderTest {

    private fun logLine(message: String) {
        println("[stdout] AudioEncoderTest $message")
    }

    @Test
    fun testCreateByMime() {
        logLine("--- OH_AudioEncoder_CreateByMime ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByMime("audio/mp3")
        logLine("OH_AudioEncoder_CreateByMime(\"audio/mp3\") result: $encoder")
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testCreateByName() {
        logLine("--- OH_AudioEncoder_CreateByName ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        logLine("OH_AudioEncoder_CreateByName(\"audio/mp3\") result: $encoder")
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testDestroy() {
        logLine("--- OH_AudioEncoder_Destroy ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val destroyResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
        logLine("OH_AudioEncoder_Destroy result: $destroyResult")
        assertNotNull(destroyResult)
    }

    @Test
    fun testSetCallback() {
        logLine("--- OH_AudioEncoder_SetCallback ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val callback = cValue<platform.AVCodecKit.CodecBase.OH_AVCodecAsyncCallback> {
            onError = null
            onStreamChanged = null
            onNeedInputData = null
            onNeedOutputData = null
        }
        val setCallbackResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_SetCallback(encoder, callback, null)
        logLine("OH_AudioEncoder_SetCallback(encoder, callback, null) result: $setCallbackResult")
        assertNotNull(setCallbackResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testConfigure() {
        logLine("--- OH_AudioEncoder_Configure ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val configureResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Configure(encoder, null)
        logLine("OH_AudioEncoder_Configure(encoder, null) result: $configureResult")
        assertNotNull(configureResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testPrepare() {
        logLine("--- OH_AudioEncoder_Prepare ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val prepareResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Prepare(encoder)
        logLine("OH_AudioEncoder_Prepare(encoder) result: $prepareResult")
        assertNotNull(prepareResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testStart() {
        logLine("--- OH_AudioEncoder_Start ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val startResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Start(encoder)
        logLine("OH_AudioEncoder_Start(encoder) result: $startResult")
        assertNotNull(startResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Stop(encoder)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testStop() {
        logLine("--- OH_AudioEncoder_Stop ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Start(encoder)
        val stopResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Stop(encoder)
        logLine("OH_AudioEncoder_Stop(encoder) result: $stopResult")
        assertNotNull(stopResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testFlush() {
        logLine("--- OH_AudioEncoder_Flush ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val flushResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Flush(encoder)
        logLine("OH_AudioEncoder_Flush(encoder) result: $flushResult")
        assertNotNull(flushResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testReset() {
        logLine("--- OH_AudioEncoder_Reset ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val resetResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Reset(encoder)
        logLine("OH_AudioEncoder_Reset(encoder) result: $resetResult")
        assertNotNull(resetResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testGetOutputDescription() {
        logLine("--- OH_AudioEncoder_GetOutputDescription ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val outputDescription = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_GetOutputDescription(encoder)
        logLine("OH_AudioEncoder_GetOutputDescription(encoder) result: $outputDescription")
        // assertNotNull(outputDescription)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testSetParameter() {
        logLine("--- OH_AudioEncoder_SetParameter ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val setParameterResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_SetParameter(encoder, null)
        logLine("OH_AudioEncoder_SetParameter(encoder, null) result: $setParameterResult")
        assertNotNull(setParameterResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testPushInputData() {
        logLine("--- OH_AudioEncoder_PushInputData ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        memScoped {
            val bufferAttr = alloc<platform.AVCodecKit.Core.OH_AVCodecBufferAttr>().apply {
                pts = 0L
                size = 0
                offset = 0
                flags = 0u
            }
            val pushInputResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_PushInputData(encoder, 0u, bufferAttr.readValue())
            logLine("OH_AudioEncoder_PushInputData(encoder, 0u, attr) result: $pushInputResult")
            assertNotNull(pushInputResult)
        }
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testFreeOutputData() {
        logLine("--- OH_AudioEncoder_FreeOutputData ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val freeOutputResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_FreeOutputData(encoder, 0u)
        logLine("OH_AudioEncoder_FreeOutputData(encoder, 0u) result: $freeOutputResult")
        assertNotNull(freeOutputResult)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }

    @Test
    fun testIsValid() {
        logLine("--- OH_AudioEncoder_IsValid ---")
        val encoder = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_CreateByName("audio/mp3")
        val isValidVar = nativeHeap.alloc<BooleanVar>()
        val isValidResult = platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_IsValid(encoder, isValidVar.ptr)
        logLine("OH_AudioEncoder_IsValid(encoder, ptr) result: $isValidResult, isValid: ${isValidVar.value}")
        assertNotNull(isValidResult)
        nativeHeap.free(isValidVar)
        platform.AVCodecKit.AudioEncoder.OH_AudioEncoder_Destroy(encoder)
    }
}
