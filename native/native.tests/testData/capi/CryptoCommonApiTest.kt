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
class CryptoCommonApiTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testEnums() {
        logLine("--- OH_Crypto_ErrCode ---")
        val success = platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_SUCCESS
        val invalidParams = platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_INVALID_PARAMS
        logLine("CRYPTO_SUCCESS=$success CRYPTO_INVALID_PARAMS=$invalidParams")
        assertNotEquals(success, invalidParams)
        logLine("--- Crypto_CipherMode ---")
        val encryptMode = platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_ENCRYPT_MODE
        val decryptMode = platform.CryptoArchitectureKit.CryptoCommonApi.CRYPTO_DECRYPT_MODE
        logLine("CRYPTO_ENCRYPT_MODE=$encryptMode CRYPTO_DECRYPT_MODE=$decryptMode")
        assertNotEquals(encryptMode, decryptMode)
    }

    @Test
    fun testFreeDataBlob() {
        memScoped {
            logLine("--- OH_Crypto_FreeDataBlob ---")
            platform.CryptoArchitectureKit.CryptoCommonApi.OH_Crypto_FreeDataBlob(null)
            logLine("OH_Crypto_FreeDataBlob(null) ok")
            val dataBlob = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                data = null
                len = 0u
            }
            platform.CryptoArchitectureKit.CryptoCommonApi.OH_Crypto_FreeDataBlob(dataBlob.ptr)
            logLine("OH_Crypto_FreeDataBlob(ptr) ok")
        }
    }

    @Test
    fun testStructCrypto_DataBlob() {
        memScoped {
            logLine("--- Crypto_DataBlob ---")
            val dataBlob = alloc<platform.CryptoArchitectureKit.CryptoCommonApi.Crypto_DataBlob>().apply {
                data = null
                len = 0u
            }
            logLine("Crypto_DataBlob len=${dataBlob.len}")
            assertNotEquals(1u, dataBlob.len)
        }
    }
}
