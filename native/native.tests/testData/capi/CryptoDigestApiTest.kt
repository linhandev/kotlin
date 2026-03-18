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
class CryptoDigestApiTest {

    private fun logLine(message: String) {
        println("[stdout] CryptoDigestApiTest $message")
    }

    @Test
    fun testOH_CryptoDigest_Create() {
        memScoped {
            logLine("--- OH_CryptoDigest_Create ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            val createResult = platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Create("SHA256", ctx.ptr)
            assertNotNull(createResult)
            logLine("OH_CryptoDigest_Create(SHA256) result: $createResult")
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_DigestCrypto_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_DigestCrypto_Destroy() {
        memScoped {
            logLine("--- OH_DigestCrypto_Destroy ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Create("SHA256", ctx.ptr)
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_DigestCrypto_Destroy(ctx.value)
            logLine("OH_DigestCrypto_Destroy ok")
        }
    }

    @Test
    fun testOH_CryptoDigest_Update() {
        memScoped {
            logLine("--- OH_CryptoDigest_Update ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Create("SHA256", ctx.ptr)
            val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                data = null
                len = 0u
            }
            val updateResult = platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Update(ctx.value, inData.ptr)
            assertNotNull(updateResult)
            logLine("OH_CryptoDigest_Update result: $updateResult")
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_DigestCrypto_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoDigest_Final() {
        memScoped {
            logLine("--- OH_CryptoDigest_Final ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Create("SHA256", ctx.ptr)
            val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                data = null
                len = 0u
            }
            val finalResult = platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Final(ctx.value, outData.ptr)
            assertNotNull(finalResult)
            logLine("OH_CryptoDigest_Final result: $finalResult")
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_DigestCrypto_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoDigest_GetLength() {
        memScoped {
            logLine("--- OH_CryptoDigest_GetLength ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Create("SHA256", ctx.ptr)
            val length = platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_GetLength(ctx.value)
            assertNotNull(length)
            logLine("OH_CryptoDigest_GetLength: $length")
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_DigestCrypto_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoDigest_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoDigest_GetAlgoName ---")
            val ctx = alloc<CPointerVar<OH_CryptoDigest>>()
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_Create("SHA256", ctx.ptr)
            val algoName = platform.CryptoArchitectureKit.CryptoDigestApi.OH_CryptoDigest_GetAlgoName(ctx.value)
            assertNotNull(algoName)
            logLine("OH_CryptoDigest_GetAlgoName: $algoName")
            platform.CryptoArchitectureKit.CryptoDigestApi.OH_DigestCrypto_Destroy(ctx.value)
        }
    }
}
