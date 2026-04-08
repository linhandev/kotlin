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
class AudioCodecTest {
    private fun logLine(message: String) {
        println("[stdout] AudioCodecTest $message")
    }

    @Test
    fun testAudioCodecCreateAndDestroy() {
        logLine("--- Testing AudioCodec create & destroy ---")
        memScoped {
            val codecByName = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")
            logLine("OH_AudioCodec_CreateByName(\"audio/mp3\") codec=$codecByName")

            val encoderByMime = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByMime("audio/mp3", true)
            logLine("OH_AudioCodec_CreateByMime(\"audio/mp3\",true) codec=$encoderByMime")

            val destroyResult1 = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Destroy(codecByName)
            logLine("OH_AudioCodec_Destroy(codecByName) result=$destroyResult1")
        }
    }

    @Test
    fun testAudioCodecConfigureAndPrepare() {
        logLine("--- Testing AudioCodec configure & prepare ---")
        memScoped {
            val codec = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")

            val configureResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Configure(codec, null)
            val prepareResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Prepare(codec)

            logLine("Configure/Prepare codec=$codec configure=$configureResult prepare=$prepareResult")

            platform.AVCodecKit.AudioCodec.OH_AudioCodec_Destroy(codec)
        }
    }

    @Test
    fun testAudioCodecControl() {
        logLine("--- Testing AudioCodec control (start/stop/flush/reset) ---")
        memScoped {
            val codec = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")

            val startResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Start(codec)
            val stopResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Stop(codec)
            val flushResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Flush(codec)
            val resetResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_Reset(codec)

            logLine("Start/Stop/Flush/Reset codec=$codec start=$startResult stop=$stopResult flush=$flushResult reset=$resetResult")
            platform.AVCodecKit.AudioCodec.OH_AudioCodec_Destroy(codec)
        }
    }

    @Test
    fun testAudioCodecBufferOperations() {
        logLine("--- Testing AudioCodec buffer operations ---")
        memScoped {
            val codec = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")

            val pushInputResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_PushInputBuffer(codec, 0u)
            val freeOutputResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_FreeOutputBuffer(codec, 0u)
            val outputDescription = platform.AVCodecKit.AudioCodec.OH_AudioCodec_GetOutputDescription(codec)

            logLine("PushInput/FreeOutput/GetOutputDescription codec=$codec push=$pushInputResult free=$freeOutputResult desc=$outputDescription")
            platform.AVCodecKit.AudioCodec.OH_AudioCodec_Destroy(codec)
        }
    }

    @Test
    fun testAudioCodecCallbackAndParameter() {
        logLine("--- Testing AudioCodec callback & parameters ---")
        memScoped {
            val codec = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")

            val callback = cValue<platform.AVCodecKit.CodecBase.OH_AVCodecCallback> {
                onError = null
                onStreamChanged = null
                onNeedInputBuffer = null
                onNewOutputBuffer = null
            }
            val registerCallbackResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_RegisterCallback(
                codec,
                callback,
                null   // userData
            )
            println("OH_AudioCodec_RegisterCallback called successfully, result: $registerCallbackResult")
            
            val setParameterResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_SetParameter(codec, null)

            val isValid = alloc<BooleanVar>()
            val isValidResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_IsValid(codec, isValid.ptr)

            val setDecryptionResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_SetDecryptionConfig(
                codec,
                null,  // mediaKeySession
                false  // secureAudio
            )

            logLine("SetParameter/IsValid/SetDecryption codec=$codec setParam=$setParameterResult isValidResult=$isValidResult isValid=${isValid.value} setDecrypt=$setDecryptionResult")
            platform.AVCodecKit.AudioCodec.OH_AudioCodec_Destroy(codec)
        }
    }

    @Test
    fun testAudioCodecSyncMode() {
        logLine("--- Testing AudioCodec sync mode (QueryInputBuffer/GetInputBuffer/QueryOutputBuffer/GetOutputBuffer API 20) ---")
        memScoped {
            val codec = platform.AVCodecKit.AudioCodec.OH_AudioCodec_CreateByName("audio/mp3")

            try {
                val inputIndex = alloc<UIntVar>()
                val queryInputResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_QueryInputBuffer(
                    codec,
                    inputIndex.ptr,
                    0L  // timeoutUs
                )

                val inputBuffer = platform.AVCodecKit.AudioCodec.OH_AudioCodec_GetInputBuffer(codec, 0u)

                val outputIndex = alloc<UIntVar>()
                val queryOutputResult = platform.AVCodecKit.AudioCodec.OH_AudioCodec_QueryOutputBuffer(
                    codec,
                    outputIndex.ptr,
                    0L  // timeoutUs
                )

                val outputBuffer = platform.AVCodecKit.AudioCodec.OH_AudioCodec_GetOutputBuffer(codec, 0u)

                logLine("Sync mode codec=$codec queryIn=$queryInputResult inIdx=${inputIndex.value} inBuf=$inputBuffer queryOut=$queryOutputResult outIdx=${outputIndex.value} outBuf=$outputBuffer")
            } catch (e: Throwable) {
                logLine("AudioCodec sync mode (API 20) exception: $e")
            }
            platform.AVCodecKit.AudioCodec.OH_AudioCodec_Destroy(codec)
        }
    }
}
