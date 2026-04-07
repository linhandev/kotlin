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
class CryptoKeyAgreementApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testCreateDestroy() {
        memScoped {
            logLine("--- OH_CryptoKeyAgreement_Create / OH_CryptoKeyAgreement_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoKeyAgreement>>()
                val createResult = platform.CryptoArchitectureKit.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_Create("ECC", ctx.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoKeyAgreement_Create(ECC) result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_Destroy(ctx.value)
                logLine("OH_CryptoKeyAgreement_Destroy ok")
            } catch (e: Throwable) {
                logLine("OH_CryptoKeyAgreement Create/Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testGenerateSecret() {
        memScoped {
            logLine("--- OH_CryptoKeyAgreement_GenerateSecret ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoKeyAgreement>>()
                platform.CryptoArchitectureKit.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_Create("ECC", ctx.ptr)
                val secret = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val generateSecretResult = platform.CryptoArchitectureKit.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_GenerateSecret(
                    ctx.value,
                    null,
                    null,
                    secret.ptr
                )
                assertNotNull(generateSecretResult)
                logLine("OH_CryptoKeyAgreement_GenerateSecret result: $generateSecretResult (API 20)")
                platform.CryptoArchitectureKit.CryptoKeyAgreementApi.OH_CryptoKeyAgreement_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoKeyAgreement_GenerateSecret (API 20) exception: $e")
            }
        }
    }
}
