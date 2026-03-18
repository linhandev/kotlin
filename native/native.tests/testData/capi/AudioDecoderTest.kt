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
class AudioDecoderTest {

    private fun logLine(message: String) {
        println("[stdout] AudioDecoderTest $message")
    }

    @Test
    fun testCreateByMime() {
        logLine("--- OH_AudioDecoder_CreateByMime ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByMime("audio/mp3")
        logLine("OH_AudioDecoder_CreateByMime(\"audio/mp3\") result: $decoder")
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testCreateByName() {
        logLine("--- OH_AudioDecoder_CreateByName ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        logLine("OH_AudioDecoder_CreateByName(\"audio/mp3\") result: $decoder")
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testDestroy() {
        logLine("--- OH_AudioDecoder_Destroy ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val destroyResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
        logLine("OH_AudioDecoder_Destroy result: $destroyResult")
        assertNotNull(destroyResult)
    }

    @Test
    fun testSetCallback() {
        logLine("--- OH_AudioDecoder_SetCallback ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val callback = cValue<platform.AVCodecKit.CodecBase.OH_AVCodecAsyncCallback> {
            onError = null
            onStreamChanged = null
            onNeedInputData = null
            onNeedOutputData = null
        }
        val setCallbackResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_SetCallback(decoder, callback, null)
        logLine("OH_AudioDecoder_SetCallback(decoder, callback, null) result: $setCallbackResult")
        assertNotNull(setCallbackResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testConfigure() {
        logLine("--- OH_AudioDecoder_Configure ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val configureResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Configure(decoder, null)
        logLine("OH_AudioDecoder_Configure(decoder, null) result: $configureResult")
        assertNotNull(configureResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testPrepare() {
        logLine("--- OH_AudioDecoder_Prepare ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val prepareResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Prepare(decoder)
        logLine("OH_AudioDecoder_Prepare(decoder) result: $prepareResult")
        assertNotNull(prepareResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testStart() {
        logLine("--- OH_AudioDecoder_Start ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val startResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Start(decoder)
        logLine("OH_AudioDecoder_Start(decoder) result: $startResult")
        assertNotNull(startResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Stop(decoder)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testStop() {
        logLine("--- OH_AudioDecoder_Stop ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Start(decoder)
        val stopResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Stop(decoder)
        logLine("OH_AudioDecoder_Stop(decoder) result: $stopResult")
        assertNotNull(stopResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testFlush() {
        logLine("--- OH_AudioDecoder_Flush ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val flushResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Flush(decoder)
        logLine("OH_AudioDecoder_Flush(decoder) result: $flushResult")
        assertNotNull(flushResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testReset() {
        logLine("--- OH_AudioDecoder_Reset ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val resetResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Reset(decoder)
        logLine("OH_AudioDecoder_Reset(decoder) result: $resetResult")
        assertNotNull(resetResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testGetOutputDescription() {
        logLine("--- OH_AudioDecoder_GetOutputDescription ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val outputDescription = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_GetOutputDescription(decoder)
        logLine("OH_AudioDecoder_GetOutputDescription(decoder) result: $outputDescription")
        // assertNotNull(outputDescription)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testSetParameter() {
        logLine("--- OH_AudioDecoder_SetParameter ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val setParameterResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_SetParameter(decoder, null)
        logLine("OH_AudioDecoder_SetParameter(decoder, null) result: $setParameterResult")
        assertNotNull(setParameterResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testPushInputData() {
        logLine("--- OH_AudioDecoder_PushInputData ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        memScoped {
            val bufferAttr = alloc<platform.AVCodecKit.Core.OH_AVCodecBufferAttr>().apply {
                pts = 0L
                size = 0
                offset = 0
                flags = 0u
            }
            val pushInputResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_PushInputData(decoder, 0u, bufferAttr.readValue())
            logLine("OH_AudioDecoder_PushInputData(decoder, 0u, attr) result: $pushInputResult")
            assertNotNull(pushInputResult)
        }
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testFreeOutputData() {
        logLine("--- OH_AudioDecoder_FreeOutputData ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val freeOutputResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_FreeOutputData(decoder, 0u)
        logLine("OH_AudioDecoder_FreeOutputData(decoder, 0u) result: $freeOutputResult")
        assertNotNull(freeOutputResult)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }

    @Test
    fun testIsValid() {
        logLine("--- OH_AudioDecoder_IsValid ---")
        val decoder = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_CreateByName("audio/mp3")
        val isValidVar = nativeHeap.alloc<BooleanVar>()
        val isValidResult = platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_IsValid(decoder, isValidVar.ptr)
        logLine("OH_AudioDecoder_IsValid(decoder, ptr) result: $isValidResult, isValid: ${isValidVar.value}")
        assertNotNull(isValidResult)
        nativeHeap.free(isValidVar)
        platform.AVCodecKit.AudioDecoder.OH_AudioDecoder_Destroy(decoder)
    }
}
