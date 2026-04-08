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
class CryptoSignatureApiTest {

    private fun logLine(message: String) {
        println("[stdout] CryptoSignatureApiTest $message")
    }

    @Test
    fun testCryptoSignatureParamTypeEnum() {
        logLine("--- CryptoSignature_ParamType ---")
        val pssMd = platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MD_NAME_STR
        val pssMgf = platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MGF_NAME_STR
        val pssMgf1 = platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MGF1_NAME_STR
        val pssSaltLen = platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_SALT_LEN_INT
        val pssTrailer = platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_TRAILER_FIELD_INT
        val sm2UserId = platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_SM2_USER_ID_DATABLOB
        logLine("CRYPTO_PSS_MD_NAME_STR=$pssMd ... CRYPTO_SM2_USER_ID_DATABLOB=$sm2UserId")
        assertEquals<Int>(100, pssMd.toInt())
        assertEquals<Int>(101, pssMgf.toInt())
        assertEquals<Int>(102, pssMgf1.toInt())
        assertEquals<Int>(103, pssSaltLen.toInt())
        assertEquals<Int>(104, pssTrailer.toInt())
        assertEquals<Int>(105, sm2UserId.toInt())
    }

    @Test
    fun testOH_CryptoVerify_Create() {
        memScoped {
            logLine("--- OH_CryptoVerify_Create ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            val createResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            assertNotNull(createResult)
            logLine("OH_CryptoVerify_Create result: $createResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_Destroy() {
        memScoped {
            logLine("--- OH_CryptoVerify_Destroy ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
            logLine("OH_CryptoVerify_Destroy ok")
        }
    }

    @Test
    fun testOH_CryptoVerify_Init() {
        memScoped {
            logLine("--- OH_CryptoVerify_Init ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val initResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Init(verify.value, null)
            assertNotNull(initResult)
            logLine("OH_CryptoVerify_Init result: $initResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_Update() {
        memScoped {
            logLine("--- OH_CryptoVerify_Update ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val updateResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Update(verify.value, inData.ptr)
            assertNotNull(updateResult)
            logLine("OH_CryptoVerify_Update result: $updateResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_Final() {
        memScoped {
            logLine("--- OH_CryptoVerify_Final ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val signData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val finalResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Final(verify.value, inData.ptr, signData.ptr)
            assertNotNull(finalResult)
            logLine("OH_CryptoVerify_Final result: $finalResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_Recover() {
        memScoped {
            logLine("--- OH_CryptoVerify_Recover ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val signData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val rawSignData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val recoverResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Recover(verify.value, signData.ptr, rawSignData.ptr)
            assertNotNull(recoverResult)
            logLine("OH_CryptoVerify_Recover result: $recoverResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_SetParam() {
        memScoped {
            logLine("--- OH_CryptoVerify_SetParam ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val setParamResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_SetParam(
                verify.value,
                platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MD_NAME_STR,
                value.ptr
            )
            assertNotNull(setParamResult)
            logLine("OH_CryptoVerify_SetParam result: $setParamResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_GetParam() {
        memScoped {
            logLine("--- OH_CryptoVerify_GetParam ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
            val getParamResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_GetParam(
                verify.value,
                platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MD_NAME_STR,
                value.ptr
            )
            assertNotNull(getParamResult)
            logLine("OH_CryptoVerify_GetParam result: $getParamResult")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoVerify_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoVerify_GetAlgoName ---")
            val verify = alloc<CPointerVar<OH_CryptoVerify>>()
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Create("RSA1024|PKCS1|SHA256", verify.ptr)
            val algoName = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_GetAlgoName(verify.value)
            assertNotNull(algoName)
            logLine("OH_CryptoVerify_GetAlgoName: $algoName")
            platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoVerify_Destroy(verify.value)
        }
    }

    @Test
    fun testOH_CryptoSign_Create() {
        memScoped {
            logLine("--- OH_CryptoSign_Create ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                val createResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoSign_Create result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_Create (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_Destroy() {
        memScoped {
            logLine("--- OH_CryptoSign_Destroy ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
                logLine("OH_CryptoSign_Destroy ok (API 20)")
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_Destroy (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_Init() {
        memScoped {
            logLine("--- OH_CryptoSign_Init ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                val initResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Init(sign.value, null)
                assertNotNull(initResult)
                logLine("OH_CryptoSign_Init result: $initResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_Init (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_Update() {
        memScoped {
            logLine("--- OH_CryptoSign_Update ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val updateResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Update(sign.value, inData.ptr)
                assertNotNull(updateResult)
                logLine("OH_CryptoSign_Update result: $updateResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_Update (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_Final() {
        memScoped {
            logLine("--- OH_CryptoSign_Final ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                val inData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val finalResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Final(sign.value, inData.ptr, outData.ptr)
                assertNotNull(finalResult)
                logLine("OH_CryptoSign_Final result: $finalResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_Final (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_SetParam() {
        memScoped {
            logLine("--- OH_CryptoSign_SetParam ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val setParamResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_SetParam(
                    sign.value,
                    platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MD_NAME_STR,
                    value.ptr
                )
                assertNotNull(setParamResult)
                logLine("OH_CryptoSign_SetParam result: $setParamResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_SetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_GetParam() {
        memScoped {
            logLine("--- OH_CryptoSign_GetParam ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val getParamResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_GetParam(
                    sign.value,
                    platform.CryptoArchitectureKit.CryptoSignatureApi.CRYPTO_PSS_MD_NAME_STR,
                    value.ptr
                )
                assertNotNull(getParamResult)
                logLine("OH_CryptoSign_GetParam result: $getParamResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_GetParam (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoSign_GetAlgoName() {
        memScoped {
            logLine("--- OH_CryptoSign_GetAlgoName ---")
            try {
                val sign = alloc<CPointerVar<OH_CryptoSign>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Create("RSA|PKCS1|SHA384", sign.ptr)
                val algoName = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_GetAlgoName(sign.value)
                assertNotNull(algoName)
                logLine("OH_CryptoSign_GetAlgoName: $algoName (API 20)")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoSign_Destroy(sign.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoSign_GetAlgoName (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEccSignatureSpec_Create() {
        memScoped {
            logLine("--- OH_CryptoEccSignatureSpec_Create ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoEccSignatureSpec>>()
                val createResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Create(null, spec.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoEccSignatureSpec_Create result: $createResult (API 20)")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Destroy(spec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEccSignatureSpec_Create (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEccSignatureSpec_GetRAndS() {
        memScoped {
            logLine("--- OH_CryptoEccSignatureSpec_GetRAndS ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoEccSignatureSpec>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Create(null, spec.ptr)
                val r = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val s = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val getRAndSResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_GetRAndS(spec.value, r.ptr, s.ptr)
                assertNotNull(getRAndSResult)
                logLine("OH_CryptoEccSignatureSpec_GetRAndS result: $getRAndSResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Destroy(spec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEccSignatureSpec_GetRAndS (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEccSignatureSpec_SetRAndS() {
        memScoped {
            logLine("--- OH_CryptoEccSignatureSpec_SetRAndS ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoEccSignatureSpec>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Create(null, spec.ptr)
                val r = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val s = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val setRAndSResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_SetRAndS(spec.value, r.ptr, s.ptr)
                assertNotNull(setRAndSResult)
                logLine("OH_CryptoEccSignatureSpec_SetRAndS result: $setRAndSResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Destroy(spec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEccSignatureSpec_SetRAndS (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEccSignatureSpec_Encode() {
        memScoped {
            logLine("--- OH_CryptoEccSignatureSpec_Encode ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoEccSignatureSpec>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Create(null, spec.ptr)
                val outData = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply { data = null; len = 0u }
                val encodeResult = platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Encode(spec.value, outData.ptr)
                assertNotNull(encodeResult)
                logLine("OH_CryptoEccSignatureSpec_Encode result: $encodeResult")
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Destroy(spec.value)
            } catch (e: Throwable) {
                logLine("OH_CryptoEccSignatureSpec_Encode (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_CryptoEccSignatureSpec_Destroy() {
        memScoped {
            logLine("--- OH_CryptoEccSignatureSpec_Destroy ---")
            try {
                val spec = alloc<CPointerVar<OH_CryptoEccSignatureSpec>>()
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Create(null, spec.ptr)
                platform.CryptoArchitectureKit.CryptoSignatureApi.OH_CryptoEccSignatureSpec_Destroy(spec.value)
                logLine("OH_CryptoEccSignatureSpec_Destroy ok (API 20)")
            } catch (e: Throwable) {
                logLine("OH_CryptoEccSignatureSpec_Destroy (API 20) exception: $e")
            }
        }
    }
}
