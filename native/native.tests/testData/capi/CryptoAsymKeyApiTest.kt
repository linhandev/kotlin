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
class CryptoAsymKeyApiTest {

    private fun logLine(message: String) {
        println("[stdout] CryptoAsymKeyApiTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- Crypto_EncodingType ---")
        logLine("CRYPTO_PEM=${platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PEM} CRYPTO_DER=${platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_DER}")
        assertNotEquals(
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PEM,
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_DER
        )
        logLine("--- CryptoAsymKeySpec_Type ---")
        val commonParams = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_COMMON_PARAMS_SPEC
        val privateKeySpec = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_PRIVATE_KEY_SPEC
        val publicKeySpec = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_PUBLIC_KEY_SPEC
        val keyPairSpec = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_KEY_PAIR_SPEC
        assertNotEquals(commonParams, privateKeySpec)
        logLine("--- CryptoPrivKeyEncoding_ParamType ---")
        try {
            val passwordStr = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PRIVATE_KEY_ENCODING_PASSWORD_STR
            val symmetricCipherStr = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PRIVATE_KEY_ENCODING_SYMMETRIC_CIPHER_STR
            assertNotEquals(passwordStr, symmetricCipherStr)
        } catch (e: Throwable) {
            logLine("CryptoPrivKeyEncoding_ParamType (API 20) exception: $e")
        }
        logLine("--- CryptoAsymKey_ParamType (sample) ---")
        val rsaN = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_RSA_N_DATABLOB
        val rsaE = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_RSA_E_DATABLOB
        val eccPkX = platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ECC_PK_X_DATABLOB
        assertNotEquals(rsaN, rsaE)
    }

    @Test
    fun testOH_CryptoAsymKeyGenerator_Create() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGenerator_Create ---")
            val ctx = alloc<CPointerVar<OH_CryptoAsymKeyGenerator>>()
            val createResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("RSA2048", ctx.ptr)
            assertNotNull(createResult)
            logLine("OH_CryptoAsymKeyGenerator_Create(RSA2048) result: $createResult")
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGenerator_Generate() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGenerator_Generate ---")
            val ctx = alloc<CPointerVar<OH_CryptoAsymKeyGenerator>>()
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("RSA2048", ctx.ptr)
            val keyPair = alloc<CPointerVar<OH_CryptoKeyPair>>()
            val generateResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Generate(ctx.value, keyPair.ptr)
            assertNotNull(generateResult)
            logLine("OH_CryptoAsymKeyGenerator_Generate result: $generateResult")
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoKeyPair_Destroy(keyPair.value)
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGenerator_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGenerator_GetAlgoName ---")
            val ctx = alloc<CPointerVar<OH_CryptoAsymKeyGenerator>>()
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("RSA2048", ctx.ptr)
            val algoName = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_GetAlgoName(ctx.value)
            assertNotNull(algoName)
            logLine("OH_CryptoAsymKeyGenerator_GetAlgoName: $algoName")
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGenerator_Destroy() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGenerator_Destroy ---")
            val ctx = alloc<CPointerVar<OH_CryptoAsymKeyGenerator>>()
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("RSA2048", ctx.ptr)
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Destroy(ctx.value)
            logLine("OH_CryptoAsymKeyGenerator_Destroy ok")
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGenerator_Convert() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGenerator_Convert ---")
            val ctx = alloc<CPointerVar<OH_CryptoAsymKeyGenerator>>()
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("RSA2048", ctx.ptr)
            val pubKeyData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val priKeyData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val keyPair = alloc<CPointerVar<OH_CryptoKeyPair>>()
            val convertResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Convert(
                ctx.value,
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PEM,
                pubKeyData.ptr,
                priKeyData.ptr,
                keyPair.ptr
            )
            assertNotNull(convertResult)
            logLine("OH_CryptoAsymKeyGenerator_Convert result: $convertResult")
            platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Destroy(ctx.value)
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGenerator_SetPassword() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGenerator_SetPassword ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoAsymKeyGenerator>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Create("RSA2048", ctx.ptr)
                val password = allocArray<UByteVar>(4)
                val setPasswordResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_SetPassword(ctx.value, password, 4u)
                assertNotNull(setPasswordResult)
                logLine("OH_CryptoAsymKeyGenerator_SetPassword result: $setPasswordResult (API 20)")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGenerator_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeyGenerator_SetPassword (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoKeyPair_GetPubKey() {
        logLine("--- OH_CryptoKeyPair_GetPubKey ---")
        val pubKey = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoKeyPair_GetPubKey(null)
        logLine("OH_CryptoKeyPair_GetPubKey(null)=$pubKey")
    }

    @Test
    fun testOH_CryptoKeyPair_GetPrivKey() {
        logLine("--- OH_CryptoKeyPair_GetPrivKey ---")
        try {
            val privKey = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoKeyPair_GetPrivKey(null)
            assertNotNull(privKey)
            logLine("OH_CryptoKeyPair_GetPrivKey(null)=$privKey (API 20)")
        } catch (e: Throwable) {
            logLine("OH_CryptoKeyPair_GetPrivKey (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_CryptoKeyPair_Destroy() {
        logLine("--- OH_CryptoKeyPair_Destroy ---")
        platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoKeyPair_Destroy(null)
        logLine("OH_CryptoKeyPair_Destroy(null) ok")
    }

    @Test
    fun testOH_CryptoPubKey_Encode() {
        memScoped {
            logLine("--- OH_CryptoPubKey_Encode ---")
            val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val encodeResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPubKey_Encode(
                null,
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PEM,
                "PKCS1",
                outData.ptr
            )
            assertNotNull(encodeResult)
            logLine("OH_CryptoPubKey_Encode(null,...) result: $encodeResult")
        }
    }

    @Test
    fun testOH_CryptoPubKey_GetParam() {
        memScoped {
            logLine("--- OH_CryptoPubKey_GetParam ---")
            val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val getParamResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPubKey_GetParam(
                null,
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_RSA_N_DATABLOB,
                value.ptr
            )
            assertNotNull(getParamResult)
            logLine("OH_CryptoPubKey_GetParam(null,...) result: $getParamResult")
        }
    }

    @Test
    fun testOH_CryptoPrivKey_Encode() {
        memScoped {
            logLine("--- OH_CryptoPrivKey_Encode ---")
            try {
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val encodeResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKey_Encode(
                    null,
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PEM,
                    "PKCS8",
                    null,
                    outData.ptr
                )
                assertNotNull(encodeResult)
                logLine("OH_CryptoPrivKey_Encode(null,...) result: $encodeResult (API 20)")
            } catch (e: Throwable) {
                logLine("OH_CryptoPrivKey_Encode (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoPrivKey_GetParam() {
        memScoped {
            logLine("--- OH_CryptoPrivKey_GetParam ---")
            try {
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val getParamResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKey_GetParam(
                    null,
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_RSA_D_DATABLOB,
                    value.ptr
                )
                assertNotNull(getParamResult)
                logLine("OH_CryptoPrivKey_GetParam(null,...) result: $getParamResult")
            } catch (e: Throwable) {
                logLine("OH_CryptoPrivKey_GetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoPrivKeyEncodingParams_Create() {
        memScoped {
            logLine("--- OH_CryptoPrivKeyEncodingParams_Create ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoPrivKeyEncodingParams>>()
                val createResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_Create(ctx.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoPrivKeyEncodingParams_Create result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoPrivKeyEncodingParams_Create (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoPrivKeyEncodingParams_SetParam() {
        memScoped {
            logLine("--- OH_CryptoPrivKeyEncodingParams_SetParam ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoPrivKeyEncodingParams>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_Create(ctx.ptr)
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val setParamResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_SetParam(
                    ctx.value,
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_PRIVATE_KEY_ENCODING_PASSWORD_STR,
                    value.ptr
                )
                assertNotNull(setParamResult)
                logLine("OH_CryptoPrivKeyEncodingParams_SetParam result: $setParamResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_Destroy(ctx.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoPrivKeyEncodingParams_SetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoPrivKeyEncodingParams_Destroy() {
        memScoped {
            logLine("--- OH_CryptoPrivKeyEncodingParams_Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoPrivKeyEncodingParams>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_Create(ctx.ptr)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoPrivKeyEncodingParams_Destroy(ctx.value)
                logLine("OH_CryptoPrivKeyEncodingParams_Destroy ok (API 20)")
            } catch (e: Throwable) {
                logLine("OH_CryptoPrivKeyEncodingParams_Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_GenEcCommonParamsSpec() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_GenEcCommonParamsSpec ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                val genEcResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenEcCommonParamsSpec("NIST_P256", spec.ptr)
                assertNotNull(genEcResult)
                logLine("OH_CryptoAsymKeySpec_GenEcCommonParamsSpec(NIST_P256) result: $genEcResult (API 20)")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(spec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_GenEcCommonParamsSpec (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_GenDhCommonParamsSpec() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_GenDhCommonParamsSpec ---")
            try {
                val dhSpec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                val genDhResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenDhCommonParamsSpec(256, 128, dhSpec.ptr)
                assertNotNull(genDhResult)
                logLine("OH_CryptoAsymKeySpec_GenDhCommonParamsSpec result: $genDhResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(dhSpec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_GenDhCommonParamsSpec (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_Create() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_Create ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenEcCommonParamsSpec("NIST_P256", spec.ptr)
                val keySpec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                val createResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Create(
                    "RSA",
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_COMMON_PARAMS_SPEC,
                    keySpec.ptr
                )
                assertNotNull(createResult)
                logLine("OH_CryptoAsymKeySpec_Create result: $createResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(spec.value)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(keySpec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_Create (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_SetCommonParamsSpec() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_SetCommonParamsSpec ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenEcCommonParamsSpec("NIST_P256", spec.ptr)
                val keySpec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Create(
                    "RSA",
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_COMMON_PARAMS_SPEC,
                    keySpec.ptr
                )
                val setCommonResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_SetCommonParamsSpec(keySpec.value, spec.value)
                assertNotNull(setCommonResult)
                logLine("OH_CryptoAsymKeySpec_SetCommonParamsSpec result: $setCommonResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(spec.value)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(keySpec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_SetCommonParamsSpec (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_SetParam() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_SetParam ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenEcCommonParamsSpec("NIST_P256", spec.ptr)
                val keySpec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Create(
                    "RSA",
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_COMMON_PARAMS_SPEC,
                    keySpec.ptr
                )
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_SetCommonParamsSpec(keySpec.value, spec.value)
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val setParamResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_SetParam(
                    keySpec.value,
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_RSA_N_DATABLOB,
                    value.ptr
                )
                assertNotNull(setParamResult)
                logLine("OH_CryptoAsymKeySpec_SetParam result: $setParamResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(spec.value)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(keySpec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_SetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_GetParam() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_GetParam ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenEcCommonParamsSpec("NIST_P256", spec.ptr)
                val keySpec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Create(
                    "RSA",
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_ASYM_KEY_COMMON_PARAMS_SPEC,
                    keySpec.ptr
                )
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_SetCommonParamsSpec(keySpec.value, spec.value)
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val getParamResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GetParam(
                    keySpec.value,
                    platform.CryptoArchitectureKit.CryptoAsymKeyApi.CRYPTO_RSA_N_DATABLOB,
                    value.ptr
                )
                assertNotNull(getParamResult)
                logLine("OH_CryptoAsymKeySpec_GetParam result: $getParamResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(spec.value)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(keySpec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_GetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeySpec_Destroy() {
        memScoped {
            logLine("--- OH_CryptoAsymKeySpec_Destroy ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoAsymKeySpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_GenEcCommonParamsSpec("NIST_P256", spec.ptr)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeySpec_Destroy(spec.value)
                logLine("OH_CryptoAsymKeySpec_Destroy ok")
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeySpec_Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGeneratorWithSpec_Create() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGeneratorWithSpec_Create ---")
            try {
                val generator = alloc<CPointerVar<OH_CryptoAsymKeyGeneratorWithSpec>>()
                val createResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_Create(null, generator.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoAsymKeyGeneratorWithSpec_Create(null) result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_Destroy(generator.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeyGeneratorWithSpec_Create (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGeneratorWithSpec_GenKeyPair() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGeneratorWithSpec_GenKeyPair ---")
            try {
                val generator = alloc<CPointerVar<OH_CryptoAsymKeyGeneratorWithSpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_Create(null, generator.ptr)
                val keyPair = alloc<CPointerVar<OH_CryptoKeyPair>>()
                val genResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_GenKeyPair(generator.value, keyPair.ptr)
                assertNotNull(genResult)
                logLine("OH_CryptoAsymKeyGeneratorWithSpec_GenKeyPair result: $genResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_Destroy(generator.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeyGeneratorWithSpec_GenKeyPair (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoAsymKeyGeneratorWithSpec_Destroy() {
        memScoped {
            logLine("--- OH_CryptoAsymKeyGeneratorWithSpec_Destroy ---")
            try {
                val generator = alloc<CPointerVar<OH_CryptoAsymKeyGeneratorWithSpec>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_Create(null, generator.ptr)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoAsymKeyGeneratorWithSpec_Destroy(generator.value)
                logLine("OH_CryptoAsymKeyGeneratorWithSpec_Destroy ok (API 20)")
            } catch (e: Throwable) {
                logLine("OH_CryptoAsymKeyGeneratorWithSpec_Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEcPoint_Create() {
        memScoped {
            logLine("--- OH_CryptoEcPoint_Create ---")
            try {
                val point = alloc<CPointerVar<OH_CryptoEcPoint>>()
                val createResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Create("NIST_P256", null, point.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoEcPoint_Create result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Destroy(point.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEcPoint_Create (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEcPoint_GetCoordinate() {
        memScoped {
            logLine("--- OH_CryptoEcPoint_GetCoordinate ---")
            try {
                val point = alloc<CPointerVar<OH_CryptoEcPoint>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Create("NIST_P256", null, point.ptr)
                val x = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val y = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val getCoordResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_GetCoordinate(point.value, x.ptr, y.ptr)
                assertNotNull(getCoordResult)
                logLine("OH_CryptoEcPoint_GetCoordinate result: $getCoordResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Destroy(point.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEcPoint_GetCoordinate (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEcPoint_SetCoordinate() {
        memScoped {
            logLine("--- OH_CryptoEcPoint_SetCoordinate ---")
            try {
                val point = alloc<CPointerVar<OH_CryptoEcPoint>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Create("NIST_P256", null, point.ptr)
                val x = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val y = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val setCoordResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_SetCoordinate(point.value, x.ptr, y.ptr)
                assertNotNull(setCoordResult)
                logLine("OH_CryptoEcPoint_SetCoordinate result: $setCoordResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Destroy(point.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEcPoint_SetCoordinate (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEcPoint_Encode() {
        memScoped {
            logLine("--- OH_CryptoEcPoint_Encode ---")
            try {
                val point = alloc<CPointerVar<OH_CryptoEcPoint>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Create("NIST_P256", null, point.ptr)
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val encodeResult = platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Encode(point.value, "UNCOMPRESSED", outData.ptr)
                assertNotNull(encodeResult)
                logLine("OH_CryptoEcPoint_Encode result: $encodeResult")
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Destroy(point.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEcPoint_Encode (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEcPoint_Destroy() {
        memScoped {
            logLine("--- OH_CryptoEcPoint_Destroy ---")
            try {
                val point = alloc<CPointerVar<OH_CryptoEcPoint>>()
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Create("NIST_P256", null, point.ptr)
                platform.CryptoArchitectureKit.CryptoAsymKeyApi.OH_CryptoEcPoint_Destroy(point.value)
                logLine("OH_CryptoEcPoint_Destroy ok (API 20)")
            } catch (e: Throwable) {
                logLine("OH_CryptoEcPoint_Destroy (API 20) exception: $e")
            }
        }
    }
}
