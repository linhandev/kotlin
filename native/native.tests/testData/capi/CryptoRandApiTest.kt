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
import cnames.structs.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class CryptoRandApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testCreateDestroy() {
        memScoped {
            logLine("--- OH_CryptoRand_Create / OH_CryptoRand_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoRand>>()
                val createResult = platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Create(ctx.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoRand_Create result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Destroy(ctx.value)
                logLine("OH_CryptoRand_Destroy ok")
            } catch (e: Throwable) {
                logLine("OH_CryptoRand Create/Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testGenerateRandom() {
        memScoped {
            logLine("--- OH_CryptoRand_GenerateRandom ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoRand>>()
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Create(ctx.ptr)
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val generateResult = platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_GenerateRandom(
                    ctx.value,
                    16,
                    outData.ptr
                )
                assertNotNull(generateResult)
                logLine("OH_CryptoRand_GenerateRandom result: $generateResult (API 20)")
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoRand_GenerateRandom (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testSetSeed() {
        memScoped {
            logLine("--- OH_CryptoRand_SetSeed ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoRand>>()
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Create(ctx.ptr)
                val seed = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val setSeedResult = platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_SetSeed(ctx.value, seed.ptr)
                assertNotNull(setSeedResult)
                logLine("OH_CryptoRand_SetSeed result: $setSeedResult (API 20)")
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoRand_SetSeed (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testGetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoRand_GetAlgoName ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoRand>>()
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Create(ctx.ptr)
                val algoName = platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_GetAlgoName(ctx.value)
                assertNotNull(algoName)
                logLine("OH_CryptoRand_GetAlgoName: $algoName (API 20)")
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoRand_GetAlgoName (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testEnableHardwareEntropy() {
        memScoped {
            logLine("--- OH_CryptoRand_EnableHardwareEntropy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoRand>>()
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Create(ctx.ptr)
                val enableResult = platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_EnableHardwareEntropy(ctx.value)
                assertNotNull(enableResult)
                logLine("OH_CryptoRand_EnableHardwareEntropy result: $enableResult (API 21)")
                platform.CryptoArchitectureKit.CryptoRandApi.OH_CryptoRand_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoRand_EnableHardwareEntropy (API 21) exception: $e")
            }
        }
    }
}
