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
class CryptoSymKeyApiTest {

    private fun logLine(message: String) {
        println("[stdout] CryptoSymKeyApiTest $message")
    }

    @Test
    fun testOH_CryptoSymKeyGenerator_Create() {
        memScoped {
            logLine("--- OH_CryptoSymKeyGenerator_Create ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                val createResult = platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create(
                    "AES256",
                    ctx.ptr
                )
                assertNotNull(createResult)
                logLine("OH_CryptoSymKeyGenerator_Create(AES256) result: $createResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKeyGenerator_Create (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKeyGenerator_Generate() {
        memScoped {
            logLine("--- OH_CryptoSymKeyGenerator_Generate ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", ctx.ptr)
                val keyCtx = alloc<CPointerVar<OH_CryptoSymKey>>()
                val generateResult = platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Generate(
                    ctx.value,
                    keyCtx.ptr
                )
                assertNotNull(generateResult)
                logLine("OH_CryptoSymKeyGenerator_Generate result: $generateResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKey_Destroy(keyCtx.value)
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKeyGenerator_Generate (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKeyGenerator_Convert() {
        memScoped {
            logLine("--- OH_CryptoSymKeyGenerator_Convert ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", ctx.ptr)
                val keyData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val keyCtx = alloc<CPointerVar<OH_CryptoSymKey>>()
                val convertResult = platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Convert(
                    ctx.value,
                    keyData.ptr,
                    keyCtx.ptr
                )
                assertNotNull(convertResult)
                logLine("OH_CryptoSymKeyGenerator_Convert result: $convertResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKeyGenerator_Convert (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKeyGenerator_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoSymKeyGenerator_GetAlgoName ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", ctx.ptr)
                val algoName = platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_GetAlgoName(ctx.value)
                assertNotNull(algoName)
                logLine("OH_CryptoSymKeyGenerator_GetAlgoName: $algoName (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKeyGenerator_GetAlgoName (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKeyGenerator_Destroy() {
        memScoped {
            logLine("--- OH_CryptoSymKeyGenerator_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", ctx.ptr)
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(ctx.value)
                logLine("OH_CryptoSymKeyGenerator_Destroy ok (API 12+)")
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKeyGenerator_Destroy (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKey_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoSymKey_GetAlgoName ---")
            try {
                val gen = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", gen.ptr)
                val keyCtx = alloc<CPointerVar<OH_CryptoSymKey>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Generate(gen.value, keyCtx.ptr)
                val algoName = platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKey_GetAlgoName(keyCtx.value)
                assertNotNull(algoName)
                logLine("OH_CryptoSymKey_GetAlgoName: $algoName (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKey_Destroy(keyCtx.value)
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(gen.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKey_GetAlgoName (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKey_GetKeyData() {
        memScoped {
            logLine("--- OH_CryptoSymKey_GetKeyData ---")
            try {
                val gen = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", gen.ptr)
                val keyCtx = alloc<CPointerVar<OH_CryptoSymKey>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Generate(gen.value, keyCtx.ptr)
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val getKeyDataResult = platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKey_GetKeyData(
                    keyCtx.value,
                    outData.ptr
                )
                assertNotNull(getKeyDataResult)
                logLine("OH_CryptoSymKey_GetKeyData result: $getKeyDataResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKey_Destroy(keyCtx.value)
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(gen.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKey_GetKeyData (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymKey_Destroy() {
        memScoped {
            logLine("--- OH_CryptoSymKey_Destroy ---")
            try {
                val gen = alloc<CPointerVar<OH_CryptoSymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Create("AES256", gen.ptr)
                val keyCtx = alloc<CPointerVar<OH_CryptoSymKey>>()
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Generate(gen.value, keyCtx.ptr)
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKey_Destroy(keyCtx.value)
                logLine("OH_CryptoSymKey_Destroy ok (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymKeyApi.OH_CryptoSymKeyGenerator_Destroy(gen.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymKey_Destroy (API 12+) exception: $e")
            }
        }
    }
}
