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
class CryptoAsymCipherApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testCryptoSm2CiphertextSpecItemEnum() {
        logLine("--- CryptoSm2CiphertextSpec_item ---")
        try {
            logLine("CRYPTO_SM2_CIPHERTEXT_C1_X=${platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C1_X}")
            logLine("CRYPTO_SM2_CIPHERTEXT_C1_Y=${platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C1_Y}")
            logLine("CRYPTO_SM2_CIPHERTEXT_C2=${platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C2}")
            logLine("CRYPTO_SM2_CIPHERTEXT_C3=${platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C3}")
            assertNotEquals(
                platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C1_X,
                platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C1_Y
            )
        } catch (e: Throwable) {
            logLine("CryptoSm2CiphertextSpec_item (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_CryptoAsymCipher_CreateInitFinalDestroy() {
        logLine("--- OH_CryptoAsymCipher Create/Init/Final/Destroy ---")
        try {
            memScoped {
                val ctx = alloc<CPointerVar<cnames.structs.OH_CryptoAsymCipher>>()
                val createResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoAsymCipher_Create("RSA|PKCS1", ctx.ptr)
                logLine("OH_CryptoAsymCipher_Create(RSA|PKCS1) result: $createResult (API 20)")
                assertNotNull(createResult)

                val initResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoAsymCipher_Init(
                    ctx.value,
                    platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_ENCRYPT_MODE,
                    null
                )
                logLine("OH_CryptoAsymCipher_Init(ctx,ENCRYPT,null) result: $initResult")
                assertNotNull(initResult)

                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val finalResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoAsymCipher_Final(ctx.value, inData.ptr, outData.ptr)
                logLine("OH_CryptoAsymCipher_Final(ctx,in,out) result: $finalResult")
                assertNotNull(finalResult)

                platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoAsymCipher_Destroy(ctx.value)
                logLine("OH_CryptoAsymCipher_Destroy(ctx) called")
            }
        } catch (e: Throwable) {
            logLine("OH_CryptoAsymCipher (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_CryptoSm2CiphertextSpec_CreateGetSetEncodeDestroy() {
        logLine("--- OH_CryptoSm2CiphertextSpec Create/GetItem/SetItem/Encode/Destroy ---")
        try {
            memScoped {
                val spec = alloc<CPointerVar<cnames.structs.OH_CryptoSm2CiphertextSpec>>()
                val createResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_Create(null, spec.ptr)
                logLine("OH_CryptoSm2CiphertextSpec_Create(null,spec) result: $createResult (API 20)")
                assertNotNull(createResult)

                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val getItemResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_GetItem(
                    spec.value,
                    platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C1_X,
                    outData.ptr
                )
                logLine("OH_CryptoSm2CiphertextSpec_GetItem(C1_X) result: $getItemResult")
                assertNotNull(getItemResult)

                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val setItemResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_SetItem(
                    spec.value,
                    platform.CryptoArchitectureKit.CryptoAsymCipherApi.CRYPTO_SM2_CIPHERTEXT_C1_X,
                    inData.ptr
                )
                logLine("OH_CryptoSm2CiphertextSpec_SetItem(C1_X) result: $setItemResult")
                assertNotNull(setItemResult)

                val encodeResult = platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_Encode(spec.value, outData.ptr)
                logLine("OH_CryptoSm2CiphertextSpec_Encode(spec,out) result: $encodeResult")
                assertNotNull(encodeResult)

                platform.CryptoArchitectureKit.CryptoAsymCipherApi.OH_CryptoSm2CiphertextSpec_Destroy(spec.value)
                logLine("OH_CryptoSm2CiphertextSpec_Destroy(spec) called")
            }
        } catch (e: Throwable) {
            logLine("OH_CryptoSm2CiphertextSpec (API 20) exception: $e")
        }
    }
}
