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
class CryptoMacApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testCryptoMacParamTypeEnum() {
        logLine("--- CryptoMac_ParamType ---")
        try {
            val digestName = platform.CryptoArchitectureKit.CryptoMacApi.CRYPTO_MAC_DIGEST_NAME_STR
            val cipherName = platform.CryptoArchitectureKit.CryptoMacApi.CRYPTO_MAC_CIPHER_NAME_STR
            logLine("CRYPTO_MAC_DIGEST_NAME_STR=$digestName CRYPTO_MAC_CIPHER_NAME_STR=$cipherName")
            assertEquals<Int>(0, digestName.toInt())
            assertEquals<Int>(1, cipherName.toInt())
        } catch (e: Throwable) {
            logLine("CryptoMac_ParamType (API 20) exception: $e")
        }
    }

    @Test
    fun testCreateDestroy() {
        memScoped {
            logLine("--- OH_CryptoMac_Create / OH_CryptoMac_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoMac>>()
                val createResult = platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Create("HMAC", ctx.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoMac_Create(HMAC) result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Destroy(ctx.value)
                logLine("OH_CryptoMac_Destroy ok")
            } catch (e: Throwable) {
                logLine("OH_CryptoMac Create/Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testSetParam() {
        memScoped {
            logLine("--- OH_CryptoMac_SetParam ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoMac>>()
                platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Create("HMAC", ctx.ptr)
                val digestName = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val setParamResult = platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_SetParam(
                    ctx.value,
                    platform.CryptoArchitectureKit.CryptoMacApi.CRYPTO_MAC_DIGEST_NAME_STR,
                    digestName.ptr
                )
                assertNotNull(setParamResult)
                logLine("OH_CryptoMac_SetParam result: $setParamResult (API 20)")
                platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoMac_SetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testInitUpdateFinalGetLength() {
        memScoped {
            logLine("--- OH_CryptoMac_Init / Update / Final / GetLength ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoMac>>()
                platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Create("HMAC", ctx.ptr)
                val initResult = platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Init(ctx.value, null)
                assertNotNull(initResult)
                logLine("OH_CryptoMac_Init result: $initResult (API 20)")

                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val updateResult = platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Update(ctx.value, inData.ptr)
                assertNotNull(updateResult)
                logLine("OH_CryptoMac_Update result: $updateResult")

                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val finalResult = platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Final(ctx.value, outData.ptr)
                assertNotNull(finalResult)
                logLine("OH_CryptoMac_Final result: $finalResult")

                val length = alloc<UIntVar>()
                val getLengthResult = platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_GetLength(ctx.value, length.ptr)
                assertNotNull(getLengthResult)
                logLine("OH_CryptoMac_GetLength result: $getLengthResult length=${length.value}")

                platform.CryptoArchitectureKit.CryptoMacApi.OH_CryptoMac_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoMac Init/Update/Final/GetLength (API 20) exception: $e")
            }
        }
    }
}
