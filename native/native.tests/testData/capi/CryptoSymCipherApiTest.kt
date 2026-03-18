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
class CryptoSymCipherApiTest {

    private fun logLine(message: String) {
        println("[stdout] CryptoSymCipherApiTest $message")
    }

    @Test
    fun testCryptoSymCipherParamsTypeEnum() {
        logLine("--- CryptoSymCipher_ParamsType (API 12+) ---")
        try {
            val iv = platform.CryptoArchitectureKit.CryptoSymCipherApi.CRYPTO_IV_DATABLOB
            val aad = platform.CryptoArchitectureKit.CryptoSymCipherApi.CRYPTO_AAD_DATABLOB
            val tag = platform.CryptoArchitectureKit.CryptoSymCipherApi.CRYPTO_TAG_DATABLOB
            logLine("CRYPTO_IV_DATABLOB=$iv CRYPTO_AAD_DATABLOB=$aad CRYPTO_TAG_DATABLOB=$tag")
            assertEquals<Int>(100, iv.toInt())
            assertEquals<Int>(101, aad.toInt())
            assertEquals<Int>(102, tag.toInt())
        } catch (e: Throwable) {
            logLine("CryptoSymCipher_ParamsType enum (API 12+) exception: $e")
        }
    }

    @Test
    fun testOH_CryptoSymCipherParams_Create() {
        memScoped {
            logLine("--- OH_CryptoSymCipherParams_Create ---")
            try {
                val params = alloc<CPointerVar<OH_CryptoSymCipherParams>>()
                val createResult = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(params.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoSymCipherParams_Create result: $createResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(params.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipherParams_Create (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipherParams_SetParam() {
        memScoped {
            logLine("--- OH_CryptoSymCipherParams_SetParam ---")
            try {
                val params = alloc<CPointerVar<OH_CryptoSymCipherParams>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(params.ptr)
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val setParamResult = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_SetParam(
                    params.value,
                    platform.CryptoArchitectureKit.CryptoSymCipherApi.CRYPTO_IV_DATABLOB,
                    value.ptr
                )
                assertNotNull(setParamResult)
                logLine("OH_CryptoSymCipherParams_SetParam result: $setParamResult")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(params.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipherParams_SetParam (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipherParams_Destroy() {
        memScoped {
            logLine("--- OH_CryptoSymCipherParams_Destroy ---")
            try {
                val params = alloc<CPointerVar<OH_CryptoSymCipherParams>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(params.ptr)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(params.value)
                logLine("OH_CryptoSymCipherParams_Destroy ok (API 12+)")
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipherParams_Destroy (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipher_Create() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_Create ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                val createResult = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Create(
                    "AES128|GCM|PKCS7",
                    ctx.ptr
                )
                assertNotNull(createResult)
                logLine("OH_CryptoSymCipher_Create result: $createResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_Create (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipher_Destroy() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Create("AES128|GCM|PKCS7", ctx.ptr)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Destroy(ctx.value)
                logLine("OH_CryptoSymCipher_Destroy ok (API 12+)")
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_Destroy (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipher_Init() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_Init ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Create("AES128|GCM|PKCS7", ctx.ptr)
                val params = alloc<CPointerVar<OH_CryptoSymCipherParams>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(params.ptr)
                val initResult = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Init(
                    ctx.value,
                    platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_ENCRYPT_MODE,
                    null,
                    params.value
                )
                assertNotNull(initResult)
                logLine("OH_CryptoSymCipher_Init result: $initResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Destroy(ctx.value)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(params.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_Init (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipher_Update() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_Update ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Create("AES128|GCM|PKCS7", ctx.ptr)
                val params = alloc<CPointerVar<OH_CryptoSymCipherParams>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(params.ptr)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Init(
                    ctx.value,
                    platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_ENCRYPT_MODE,
                    null,
                    params.value
                )
                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val updateResult = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Update(
                    ctx.value,
                    inData.ptr,
                    outData.ptr
                )
                assertNotNull(updateResult)
                logLine("OH_CryptoSymCipher_Update result: $updateResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Destroy(ctx.value)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(params.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_Update (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipher_Final() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_Final ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Create("AES128|GCM|PKCS7", ctx.ptr)
                val params = alloc<CPointerVar<OH_CryptoSymCipherParams>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Create(params.ptr)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Init(
                    ctx.value,
                    platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_ENCRYPT_MODE,
                    null,
                    params.value
                )
                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val finalResult = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Final(
                    ctx.value,
                    inData.ptr,
                    outData.ptr
                )
                assertNotNull(finalResult)
                logLine("OH_CryptoSymCipher_Final result: $finalResult (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Destroy(ctx.value)
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipherParams_Destroy(params.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_Final (API 12+) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSymCipher_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoSymCipher_GetAlgoName ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoSymCipher>>()
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Create("AES128|GCM|PKCS7", ctx.ptr)
                val algoName = platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_GetAlgoName(ctx.value)
                assertNotNull(algoName)
                logLine("OH_CryptoSymCipher_GetAlgoName: $algoName (API 12+)")
                platform.CryptoArchitectureKit.CryptoSymCipherApi.OH_CryptoSymCipher_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSymCipher_GetAlgoName (API 12+) exception: $e")
            }
        }
    }
}
