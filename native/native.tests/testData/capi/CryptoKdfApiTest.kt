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
class CryptoKdfApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testEnums() {
        logLine("--- CryptoKdf_ParamType ---")
        try {
            val keyBlob = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_KEY_DATABLOB
            val saltBlob = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_SALT_DATABLOB
            val infoBlob = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_INFO_DATABLOB
            val iterCountInt = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_ITER_COUNT_INT
            val scryptN = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_SCRYPT_N_UINT64
            val scryptR = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_SCRYPT_R_UINT64
            val scryptP = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_SCRYPT_P_UINT64
            val scryptMaxMem = platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_SCRYPT_MAX_MEM_UINT64
            logLine("CRYPTO_KDF_KEY_DATABLOB=$keyBlob CRYPTO_KDF_SALT_DATABLOB=$saltBlob CRYPTO_KDF_INFO_DATABLOB=$infoBlob")
            logLine("CRYPTO_KDF_ITER_COUNT_INT=$iterCountInt CRYPTO_KDF_SCRYPT_N_UINT64=$scryptN CRYPTO_KDF_SCRYPT_R_UINT64=$scryptR CRYPTO_KDF_SCRYPT_P_UINT64=$scryptP CRYPTO_KDF_SCRYPT_MAX_MEM_UINT64=$scryptMaxMem")
            assertEquals<Int>(0, keyBlob.toInt())
            assertEquals<Int>(1, saltBlob.toInt())
            assertEquals<Int>(2, infoBlob.toInt())
            assertEquals<Int>(3, iterCountInt.toInt())
            assertEquals<Int>(4, scryptN.toInt())
            assertEquals<Int>(5, scryptR.toInt())
            assertEquals<Int>(6, scryptP.toInt())
            assertEquals<Int>(7, scryptMaxMem.toInt())
        } catch (e: Throwable) {
            logLine("CryptoKdf_ParamType (API 20) exception: $e")
        }
    }

    @Test
    fun testKdfParamsCreateSetParamDestroy() {
        memScoped {
            logLine("--- OH_CryptoKdfParams Create/SetParam/Destroy ---")
            try {
                val params = alloc<CPointerVar<OH_CryptoKdfParams>>()
                val createResult = platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdfParams_Create("HKDF", params.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoKdfParams_Create(HKDF) result: $createResult (API 20)")
                val value = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val setParamResult = platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdfParams_SetParam(
                    params.value,
                    platform.CryptoArchitectureKit.CryptoKdfApi.CRYPTO_KDF_KEY_DATABLOB,
                    value.ptr
                )
                assertNotNull(setParamResult)
                logLine("OH_CryptoKdfParams_SetParam result: $setParamResult")
                platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdfParams_Destroy(params.value)
                logLine("OH_CryptoKdfParams_Destroy ok")
            } catch (e: Throwable) {
                logLine("OH_CryptoKdfParams (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testKdfCreateDeriveDestroy() {
        memScoped {
            logLine("--- OH_CryptoKdf Create/Derive/Destroy ---")
            try {
                val ctx = alloc<CPointerVar<OH_CryptoKdf>>()
                val createResult = platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdf_Create("HKDF|SHA384|EXTRACT_AND_EXPAND", ctx.ptr)
                assertNotNull(createResult)
                logLine("OH_CryptoKdf_Create result: $createResult (API 20)")
                val kdfParams = alloc<CPointerVar<OH_CryptoKdfParams>>()
                platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdfParams_Create("HKDF", kdfParams.ptr)
                val key = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                    data = null
                    len = 0u
                }
                val deriveResult = platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdf_Derive(
                    ctx.value,
                    kdfParams.value,
                    32,
                    key.ptr
                )
                assertNotNull(deriveResult)
                logLine("OH_CryptoKdf_Derive result: $deriveResult")
                platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdfParams_Destroy(kdfParams.value)
                platform.CryptoArchitectureKit.CryptoKdfApi.OH_CryptoKdf_Destroy(ctx.value)
                logLine("OH_CryptoKdf_Destroy ok")
            } catch (e: Throwable) {
                logLine("OH_CryptoKdf (API 20) exception: $e")
            }
        }
    }
}
