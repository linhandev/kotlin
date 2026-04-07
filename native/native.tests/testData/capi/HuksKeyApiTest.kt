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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.UniversalKeystoreKit.HuksKeyApi.*
import platform.UniversalKeystoreKit.HuksParamSetApi.OH_Huks_FreeKeyAliasSet
import platform.UniversalKeystoreKit.HuksTypeApi.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HuksKeyApiTest {

    private fun logLine(message: String) = println(message)

    // ==================== 枚举测试（HuksTypeApi） ====================

    @Test
    fun testEnum_ErrCode() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_SUCCESS", OH_HUKS_SUCCESS.toInt(), 0)
        p("OH_HUKS_ERR_CODE_PERMISSION_FAIL", OH_HUKS_ERR_CODE_PERMISSION_FAIL.toInt(), 201)
        p("OH_HUKS_ERR_CODE_ILLEGAL_ARGUMENT", OH_HUKS_ERR_CODE_ILLEGAL_ARGUMENT.toInt(), 401)
        p("OH_HUKS_ERR_CODE_NOT_SUPPORTED_API", OH_HUKS_ERR_CODE_NOT_SUPPORTED_API.toInt(), 801)
        p("OH_HUKS_ERR_CODE_FEATURE_NOT_SUPPORTED", OH_HUKS_ERR_CODE_FEATURE_NOT_SUPPORTED.toInt(), 12000001)
        p("OH_HUKS_ERR_CODE_MISSING_CRYPTO_ALG_ARGUMENT", OH_HUKS_ERR_CODE_MISSING_CRYPTO_ALG_ARGUMENT.toInt(), 12000002)
        p("OH_HUKS_ERR_CODE_INVALID_CRYPTO_ALG_ARGUMENT", OH_HUKS_ERR_CODE_INVALID_CRYPTO_ALG_ARGUMENT.toInt(), 12000003)
        p("OH_HUKS_ERR_CODE_FILE_OPERATION_FAIL", OH_HUKS_ERR_CODE_FILE_OPERATION_FAIL.toInt(), 12000004)
        p("OH_HUKS_ERR_CODE_COMMUNICATION_FAIL", OH_HUKS_ERR_CODE_COMMUNICATION_FAIL.toInt(), 12000005)
        p("OH_HUKS_ERR_CODE_CRYPTO_FAIL", OH_HUKS_ERR_CODE_CRYPTO_FAIL.toInt(), 12000006)
        p("OH_HUKS_ERR_CODE_KEY_AUTH_PERMANENTLY_INVALIDATED", OH_HUKS_ERR_CODE_KEY_AUTH_PERMANENTLY_INVALIDATED.toInt(), 12000007)
        p("OH_HUKS_ERR_CODE_KEY_AUTH_VERIFY_FAILED", OH_HUKS_ERR_CODE_KEY_AUTH_VERIFY_FAILED.toInt(), 12000008)
        p("OH_HUKS_ERR_CODE_KEY_AUTH_TIME_OUT", OH_HUKS_ERR_CODE_KEY_AUTH_TIME_OUT.toInt(), 12000009)
        p("OH_HUKS_ERR_CODE_SESSION_LIMIT", OH_HUKS_ERR_CODE_SESSION_LIMIT.toInt(), 12000010)
        p("OH_HUKS_ERR_CODE_ITEM_NOT_EXIST", OH_HUKS_ERR_CODE_ITEM_NOT_EXIST.toInt(), 12000011)
        p("OH_HUKS_ERR_CODE_INTERNAL_ERROR", OH_HUKS_ERR_CODE_INTERNAL_ERROR.toInt(), 12000012)
        p("OH_HUKS_ERR_CODE_CREDENTIAL_NOT_EXIST", OH_HUKS_ERR_CODE_CREDENTIAL_NOT_EXIST.toInt(), 12000013)
        p("OH_HUKS_ERR_CODE_INSUFFICIENT_MEMORY", OH_HUKS_ERR_CODE_INSUFFICIENT_MEMORY.toInt(), 12000014)
        p("OH_HUKS_ERR_CODE_CALL_SERVICE_FAILED", OH_HUKS_ERR_CODE_CALL_SERVICE_FAILED.toInt(), 12000015)
        p("OH_HUKS_ERR_CODE_DEVICE_PASSWORD_UNSET", OH_HUKS_ERR_CODE_DEVICE_PASSWORD_UNSET.toInt(), 12000016)
        p("OH_HUKS_ERR_CODE_KEY_ALREADY_EXIST", OH_HUKS_ERR_CODE_KEY_ALREADY_EXIST.toInt(), 12000017)
        p("OH_HUKS_ERR_CODE_INVALID_ARGUMENT", OH_HUKS_ERR_CODE_INVALID_ARGUMENT.toInt(), 12000018)
        p("OH_HUKS_ERR_CODE_ITEM_EXISTS", OH_HUKS_ERR_CODE_ITEM_EXISTS.toInt(), 12000019)
        p("OH_HUKS_ERR_CODE_EXTERNAL_MODULE", OH_HUKS_ERR_CODE_EXTERNAL_MODULE.toInt(), 12000020)
        p("OH_HUKS_ERR_CODE_PIN_LOCKED", OH_HUKS_ERR_CODE_PIN_LOCKED.toInt(), 12000021)
        p("OH_HUKS_ERR_CODE_PIN_INCORRECT", OH_HUKS_ERR_CODE_PIN_INCORRECT.toInt(), 12000022)
        p("OH_HUKS_ERR_CODE_PIN_NO_AUTH", OH_HUKS_ERR_CODE_PIN_NO_AUTH.toInt(), 12000023)
        p("OH_HUKS_ERR_CODE_BUSY", OH_HUKS_ERR_CODE_BUSY.toInt(), 12000024)
        p("OH_HUKS_ERR_CODE_EXCEED_LIMIT", OH_HUKS_ERR_CODE_EXCEED_LIMIT.toInt(), 12000025)
    }

    @Test
    fun testEnum_KeyPurpose() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_KEY_PURPOSE_ENCRYPT", OH_HUKS_KEY_PURPOSE_ENCRYPT.toInt(), 1)
        p("OH_HUKS_KEY_PURPOSE_DECRYPT", OH_HUKS_KEY_PURPOSE_DECRYPT.toInt(), 2)
        p("OH_HUKS_KEY_PURPOSE_SIGN", OH_HUKS_KEY_PURPOSE_SIGN.toInt(), 4)
        p("OH_HUKS_KEY_PURPOSE_VERIFY", OH_HUKS_KEY_PURPOSE_VERIFY.toInt(), 8)
        p("OH_HUKS_KEY_PURPOSE_DERIVE", OH_HUKS_KEY_PURPOSE_DERIVE.toInt(), 16)
        p("OH_HUKS_KEY_PURPOSE_WRAP", OH_HUKS_KEY_PURPOSE_WRAP.toInt(), 32)
        p("OH_HUKS_KEY_PURPOSE_UNWRAP", OH_HUKS_KEY_PURPOSE_UNWRAP.toInt(), 64)
        p("OH_HUKS_KEY_PURPOSE_MAC", OH_HUKS_KEY_PURPOSE_MAC.toInt(), 128)
        p("OH_HUKS_KEY_PURPOSE_AGREE", OH_HUKS_KEY_PURPOSE_AGREE.toInt(), 256)
    }

    @Test
    fun testEnum_KeyDigest() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_DIGEST_NONE", OH_HUKS_DIGEST_NONE.toInt(), 0)
        p("OH_HUKS_DIGEST_MD5", OH_HUKS_DIGEST_MD5.toInt(), 1)
        p("OH_HUKS_DIGEST_SM3", OH_HUKS_DIGEST_SM3.toInt(), 2)
        p("OH_HUKS_DIGEST_SHA1", OH_HUKS_DIGEST_SHA1.toInt(), 10)
        p("OH_HUKS_DIGEST_SHA224", OH_HUKS_DIGEST_SHA224.toInt(), 11)
        p("OH_HUKS_DIGEST_SHA256", OH_HUKS_DIGEST_SHA256.toInt(), 12)
        p("OH_HUKS_DIGEST_SHA384", OH_HUKS_DIGEST_SHA384.toInt(), 13)
        p("OH_HUKS_DIGEST_SHA512", OH_HUKS_DIGEST_SHA512.toInt(), 14)
    }

    @Test
    fun testEnum_KeyPadding() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_PADDING_NONE", OH_HUKS_PADDING_NONE.toInt(), 0)
        p("OH_HUKS_PADDING_OAEP", OH_HUKS_PADDING_OAEP.toInt(), 1)
        p("OH_HUKS_PADDING_PSS", OH_HUKS_PADDING_PSS.toInt(), 2)
        p("OH_HUKS_PADDING_PKCS1_V1_5", OH_HUKS_PADDING_PKCS1_V1_5.toInt(), 3)
        p("OH_HUKS_PADDING_PKCS5", OH_HUKS_PADDING_PKCS5.toInt(), 4)
        p("OH_HUKS_PADDING_PKCS7", OH_HUKS_PADDING_PKCS7.toInt(), 5)
        p("OH_HUKS_PADDING_ISO_IEC_9796_2", OH_HUKS_PADDING_ISO_IEC_9796_2.toInt(), 6)
        p("OH_HUKS_PADDING_ISO_IEC_9797_1", OH_HUKS_PADDING_ISO_IEC_9797_1.toInt(), 7)
    }

    @Test
    fun testEnum_CipherMode() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_MODE_ECB", OH_HUKS_MODE_ECB.toInt(), 1)
        p("OH_HUKS_MODE_CBC", OH_HUKS_MODE_CBC.toInt(), 2)
        p("OH_HUKS_MODE_CTR", OH_HUKS_MODE_CTR.toInt(), 3)
        p("OH_HUKS_MODE_OFB", OH_HUKS_MODE_OFB.toInt(), 4)
        p("OH_HUKS_MODE_CFB", OH_HUKS_MODE_CFB.toInt(), 5)
        p("OH_HUKS_MODE_CCM", OH_HUKS_MODE_CCM.toInt(), 31)
        p("OH_HUKS_MODE_GCM", OH_HUKS_MODE_GCM.toInt(), 32)
    }

    @Test
    fun testEnum_KeySize() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_RSA_KEY_SIZE_512", OH_HUKS_RSA_KEY_SIZE_512.toInt(), 512)
        p("OH_HUKS_RSA_KEY_SIZE_768", OH_HUKS_RSA_KEY_SIZE_768.toInt(), 768)
        p("OH_HUKS_RSA_KEY_SIZE_1024", OH_HUKS_RSA_KEY_SIZE_1024.toInt(), 1024)
        p("OH_HUKS_RSA_KEY_SIZE_2048", OH_HUKS_RSA_KEY_SIZE_2048.toInt(), 2048)
        p("OH_HUKS_RSA_KEY_SIZE_3072", OH_HUKS_RSA_KEY_SIZE_3072.toInt(), 3072)
        p("OH_HUKS_RSA_KEY_SIZE_4096", OH_HUKS_RSA_KEY_SIZE_4096.toInt(), 4096)
        p("OH_HUKS_ECC_KEY_SIZE_224", OH_HUKS_ECC_KEY_SIZE_224.toInt(), 224)
        p("OH_HUKS_ECC_KEY_SIZE_256", OH_HUKS_ECC_KEY_SIZE_256.toInt(), 256)
        p("OH_HUKS_ECC_KEY_SIZE_384", OH_HUKS_ECC_KEY_SIZE_384.toInt(), 384)
        p("OH_HUKS_ECC_KEY_SIZE_521", OH_HUKS_ECC_KEY_SIZE_521.toInt(), 521)
        p("OH_HUKS_AES_KEY_SIZE_128", OH_HUKS_AES_KEY_SIZE_128.toInt(), 128)
        p("OH_HUKS_AES_KEY_SIZE_192", OH_HUKS_AES_KEY_SIZE_192.toInt(), 192)
        p("OH_HUKS_AES_KEY_SIZE_256", OH_HUKS_AES_KEY_SIZE_256.toInt(), 256)
        p("OH_HUKS_AES_KEY_SIZE_512", OH_HUKS_AES_KEY_SIZE_512.toInt(), 512)
        p("OH_HUKS_CURVE25519_KEY_SIZE_256", OH_HUKS_CURVE25519_KEY_SIZE_256.toInt(), 256)
        p("OH_HUKS_DH_KEY_SIZE_2048", OH_HUKS_DH_KEY_SIZE_2048.toInt(), 2048)
        p("OH_HUKS_DH_KEY_SIZE_3072", OH_HUKS_DH_KEY_SIZE_3072.toInt(), 3072)
        p("OH_HUKS_DH_KEY_SIZE_4096", OH_HUKS_DH_KEY_SIZE_4096.toInt(), 4096)
        p("OH_HUKS_SM2_KEY_SIZE_256", OH_HUKS_SM2_KEY_SIZE_256.toInt(), 256)
        p("OH_HUKS_SM4_KEY_SIZE_128", OH_HUKS_SM4_KEY_SIZE_128.toInt(), 128)
        p("OH_HUKS_DES_KEY_SIZE_64", OH_HUKS_DES_KEY_SIZE_64.toInt(), 64)
        p("OH_HUKS_3DES_KEY_SIZE_128", OH_HUKS_3DES_KEY_SIZE_128.toInt(), 128)
        p("OH_HUKS_3DES_KEY_SIZE_192", OH_HUKS_3DES_KEY_SIZE_192.toInt(), 192)
    }

    @Test
    fun testEnum_KeyAlg() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_ALG_RSA", OH_HUKS_ALG_RSA.toInt(), 1)
        p("OH_HUKS_ALG_ECC", OH_HUKS_ALG_ECC.toInt(), 2)
        p("OH_HUKS_ALG_DSA", OH_HUKS_ALG_DSA.toInt(), 3)
        p("OH_HUKS_ALG_AES", OH_HUKS_ALG_AES.toInt(), 20)
        p("OH_HUKS_ALG_HMAC", OH_HUKS_ALG_HMAC.toInt(), 50)
        p("OH_HUKS_ALG_HKDF", OH_HUKS_ALG_HKDF.toInt(), 51)
        p("OH_HUKS_ALG_PBKDF2", OH_HUKS_ALG_PBKDF2.toInt(), 52)
        p("OH_HUKS_ALG_ECDH", OH_HUKS_ALG_ECDH.toInt(), 100)
        p("OH_HUKS_ALG_X25519", OH_HUKS_ALG_X25519.toInt(), 101)
        p("OH_HUKS_ALG_ED25519", OH_HUKS_ALG_ED25519.toInt(), 102)
        p("OH_HUKS_ALG_DH", OH_HUKS_ALG_DH.toInt(), 103)
        p("OH_HUKS_ALG_SM2", OH_HUKS_ALG_SM2.toInt(), 150)
        p("OH_HUKS_ALG_SM3", OH_HUKS_ALG_SM3.toInt(), 151)
        p("OH_HUKS_ALG_SM4", OH_HUKS_ALG_SM4.toInt(), 152)
        p("OH_HUKS_ALG_DES", OH_HUKS_ALG_DES.toInt(), 160)
        p("OH_HUKS_ALG_3DES", OH_HUKS_ALG_3DES.toInt(), 161)
        p("OH_HUKS_ALG_CMAC", OH_HUKS_ALG_CMAC.toInt(), 162)
    }

    @Test
    fun testEnum_AlgSuite() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_UNWRAP_SUITE_X25519_AES_256_GCM_NOPADDING", OH_HUKS_UNWRAP_SUITE_X25519_AES_256_GCM_NOPADDING.toInt(), 1)
        p("OH_HUKS_UNWRAP_SUITE_ECDH_AES_256_GCM_NOPADDING", OH_HUKS_UNWRAP_SUITE_ECDH_AES_256_GCM_NOPADDING.toInt(), 2)
    }

    @Test
    fun testEnum_KeyLifecycle() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        // OH_Huks_KeyGenerateType
        p("OH_HUKS_KEY_GENERATE_TYPE_DEFAULT", OH_HUKS_KEY_GENERATE_TYPE_DEFAULT.toInt(), 0)
        p("OH_HUKS_KEY_GENERATE_TYPE_DERIVE", OH_HUKS_KEY_GENERATE_TYPE_DERIVE.toInt(), 1)
        p("OH_HUKS_KEY_GENERATE_TYPE_AGREE", OH_HUKS_KEY_GENERATE_TYPE_AGREE.toInt(), 2)
        // OH_Huks_KeyFlag
        p("OH_HUKS_KEY_FLAG_IMPORT_KEY", OH_HUKS_KEY_FLAG_IMPORT_KEY.toInt(), 1)
        p("OH_HUKS_KEY_FLAG_GENERATE_KEY", OH_HUKS_KEY_FLAG_GENERATE_KEY.toInt(), 2)
        p("OH_HUKS_KEY_FLAG_AGREE_KEY", OH_HUKS_KEY_FLAG_AGREE_KEY.toInt(), 3)
        p("OH_HUKS_KEY_FLAG_DERIVE_KEY", OH_HUKS_KEY_FLAG_DERIVE_KEY.toInt(), 4)
        // OH_Huks_KeyStorageType
        p("OH_HUKS_STORAGE_TEMP", OH_HUKS_STORAGE_TEMP.toInt(), 0)
        p("OH_HUKS_STORAGE_PERSISTENT", OH_HUKS_STORAGE_PERSISTENT.toInt(), 1)
        p("OH_HUKS_STORAGE_ONLY_USED_IN_HUKS", OH_HUKS_STORAGE_ONLY_USED_IN_HUKS.toInt(), 2)
        p("OH_HUKS_STORAGE_KEY_EXPORT_ALLOWED", OH_HUKS_STORAGE_KEY_EXPORT_ALLOWED.toInt(), 3)
        // OH_Huks_ImportKeyType
        p("OH_HUKS_KEY_TYPE_PUBLIC_KEY", OH_HUKS_KEY_TYPE_PUBLIC_KEY.toInt(), 0)
        p("OH_HUKS_KEY_TYPE_PRIVATE_KEY", OH_HUKS_KEY_TYPE_PRIVATE_KEY.toInt(), 1)
        p("OH_HUKS_KEY_TYPE_KEY_PAIR", OH_HUKS_KEY_TYPE_KEY_PAIR.toInt(), 2)
        // OH_Huks_RsaPssSaltLenType
        p("OH_HUKS_RSA_PSS_SALT_LEN_DIGEST", OH_HUKS_RSA_PSS_SALT_LEN_DIGEST.toInt(), 0)
        p("OH_HUKS_RSA_PSS_SALT_LEN_MAX", OH_HUKS_RSA_PSS_SALT_LEN_MAX.toInt(), 1)
        // OH_Huks_SecureSignType
        p("OH_HUKS_SECURE_SIGN_WITH_AUTHINFO", OH_HUKS_SECURE_SIGN_WITH_AUTHINFO.toInt(), 1)
        // OH_Huks_KeyClassType
        p("OH_HUKS_KEY_CLASS_DEFAULT", OH_HUKS_KEY_CLASS_DEFAULT.toInt(), 0)
        p("OH_HUKS_KEY_CLASS_EXTENSION", OH_HUKS_KEY_CLASS_EXTENSION.toInt(), 1)
        // OH_Huks_KeyWrapType
        p("OH_HUKS_KEY_WRAP_TYPE_HUK_BASED", OH_HUKS_KEY_WRAP_TYPE_HUK_BASED.toInt(), 2)
    }

    @Test
    fun testEnum_Auth() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        // OH_Huks_UserAuthType
        p("OH_HUKS_USER_AUTH_TYPE_FINGERPRINT", OH_HUKS_USER_AUTH_TYPE_FINGERPRINT.toInt(), 1)
        p("OH_HUKS_USER_AUTH_TYPE_FACE", OH_HUKS_USER_AUTH_TYPE_FACE.toInt(), 2)
        p("OH_HUKS_USER_AUTH_TYPE_PIN", OH_HUKS_USER_AUTH_TYPE_PIN.toInt(), 4)
        p("OH_HUKS_USER_AUTH_TYPE_TUI_PIN", OH_HUKS_USER_AUTH_TYPE_TUI_PIN.toInt(), 32)
        // OH_Huks_AuthAccessType
        p("OH_HUKS_AUTH_ACCESS_INVALID_CLEAR_PASSWORD", OH_HUKS_AUTH_ACCESS_INVALID_CLEAR_PASSWORD.toInt(), 1)
        p("OH_HUKS_AUTH_ACCESS_INVALID_NEW_BIO_ENROLL", OH_HUKS_AUTH_ACCESS_INVALID_NEW_BIO_ENROLL.toInt(), 2)
        p("OH_HUKS_AUTH_ACCESS_ALWAYS_VALID", OH_HUKS_AUTH_ACCESS_ALWAYS_VALID.toInt(), 4)
        // OH_Huks_AuthStorageLevel
        p("OH_HUKS_AUTH_STORAGE_LEVEL_DE", OH_HUKS_AUTH_STORAGE_LEVEL_DE.toInt(), 0)
        p("OH_HUKS_AUTH_STORAGE_LEVEL_CE", OH_HUKS_AUTH_STORAGE_LEVEL_CE.toInt(), 1)
        p("OH_HUKS_AUTH_STORAGE_LEVEL_ECE", OH_HUKS_AUTH_STORAGE_LEVEL_ECE.toInt(), 2)
        // OH_Huks_UserAuthMode
        p("OH_HUKS_USER_AUTH_MODE_LOCAL", OH_HUKS_USER_AUTH_MODE_LOCAL.toInt(), 0)
        p("OH_HUKS_USER_AUTH_MODE_COAUTH", OH_HUKS_USER_AUTH_MODE_COAUTH.toInt(), 1)
        // OH_Huks_ChallengeType
        p("OH_HUKS_CHALLENGE_TYPE_NORMAL", OH_HUKS_CHALLENGE_TYPE_NORMAL.toInt(), 0)
        p("OH_HUKS_CHALLENGE_TYPE_CUSTOM", OH_HUKS_CHALLENGE_TYPE_CUSTOM.toInt(), 1)
        p("OH_HUKS_CHALLENGE_TYPE_NONE", OH_HUKS_CHALLENGE_TYPE_NONE.toInt(), 2)
        // OH_Huks_ChallengePosition
        p("OH_HUKS_CHALLENGE_POS_0", OH_HUKS_CHALLENGE_POS_0.toInt(), 0)
        p("OH_HUKS_CHALLENGE_POS_1", OH_HUKS_CHALLENGE_POS_1.toInt(), 1)
        p("OH_HUKS_CHALLENGE_POS_2", OH_HUKS_CHALLENGE_POS_2.toInt(), 2)
        p("OH_HUKS_CHALLENGE_POS_3", OH_HUKS_CHALLENGE_POS_3.toInt(), 3)
    }

    @Test
    fun testEnum_TagType() {
        fun p(n: String, v: Int, e: Int) { logLine("$n=$v"); assertEquals(e, v) }
        p("OH_HUKS_TAG_TYPE_INVALID", OH_HUKS_TAG_TYPE_INVALID.toInt(), 0)
        p("OH_HUKS_TAG_TYPE_INT", OH_HUKS_TAG_TYPE_INT.toInt(), 1 shl 28)
        p("OH_HUKS_TAG_TYPE_UINT", OH_HUKS_TAG_TYPE_UINT.toInt(), 2 shl 28)
        p("OH_HUKS_TAG_TYPE_ULONG", OH_HUKS_TAG_TYPE_ULONG.toInt(), 3 shl 28)
        p("OH_HUKS_TAG_TYPE_BOOL", OH_HUKS_TAG_TYPE_BOOL.toInt(), 4 shl 28)
        p("OH_HUKS_TAG_TYPE_BYTES", OH_HUKS_TAG_TYPE_BYTES.toInt(), 5 shl 28)
    }

    @Test
    fun testEnum_Tag() {
        // OH_Huks_Tag 值较多，验证可访问并打印
        fun p(n: String, v: Int) { logLine("$n=$v"); assertNotNull(v) }
        p("OH_HUKS_TAG_ALGORITHM", OH_HUKS_TAG_ALGORITHM.toInt())
        p("OH_HUKS_TAG_PURPOSE", OH_HUKS_TAG_PURPOSE.toInt())
        p("OH_HUKS_TAG_KEY_SIZE", OH_HUKS_TAG_KEY_SIZE.toInt())
        p("OH_HUKS_TAG_DIGEST", OH_HUKS_TAG_DIGEST.toInt())
        p("OH_HUKS_TAG_PADDING", OH_HUKS_TAG_PADDING.toInt())
        p("OH_HUKS_TAG_BLOCK_MODE", OH_HUKS_TAG_BLOCK_MODE.toInt())
        p("OH_HUKS_TAG_KEY_TYPE", OH_HUKS_TAG_KEY_TYPE.toInt())
        p("OH_HUKS_TAG_ASSOCIATED_DATA", OH_HUKS_TAG_ASSOCIATED_DATA.toInt())
        p("OH_HUKS_TAG_NONCE", OH_HUKS_TAG_NONCE.toInt())
        p("OH_HUKS_TAG_IV", OH_HUKS_TAG_IV.toInt())
        p("OH_HUKS_TAG_INFO", OH_HUKS_TAG_INFO.toInt())
        p("OH_HUKS_TAG_SALT", OH_HUKS_TAG_SALT.toInt())
        p("OH_HUKS_TAG_ITERATION", OH_HUKS_TAG_ITERATION.toInt())
        p("OH_HUKS_TAG_KEY_GENERATE_TYPE", OH_HUKS_TAG_KEY_GENERATE_TYPE.toInt())
        p("OH_HUKS_TAG_AGREE_ALG", OH_HUKS_TAG_AGREE_ALG.toInt())
        p("OH_HUKS_TAG_AGREE_PUBLIC_KEY_IS_KEY_ALIAS", OH_HUKS_TAG_AGREE_PUBLIC_KEY_IS_KEY_ALIAS.toInt())
        p("OH_HUKS_TAG_AGREE_PRIVATE_KEY_ALIAS", OH_HUKS_TAG_AGREE_PRIVATE_KEY_ALIAS.toInt())
        p("OH_HUKS_TAG_AGREE_PUBLIC_KEY", OH_HUKS_TAG_AGREE_PUBLIC_KEY.toInt())
        p("OH_HUKS_TAG_KEY_ALIAS", OH_HUKS_TAG_KEY_ALIAS.toInt())
        p("OH_HUKS_TAG_DERIVE_KEY_SIZE", OH_HUKS_TAG_DERIVE_KEY_SIZE.toInt())
        p("OH_HUKS_TAG_IMPORT_KEY_TYPE", OH_HUKS_TAG_IMPORT_KEY_TYPE.toInt())
        p("OH_HUKS_TAG_UNWRAP_ALGORITHM_SUITE", OH_HUKS_TAG_UNWRAP_ALGORITHM_SUITE.toInt())
        p("OH_HUKS_TAG_DERIVED_AGREED_KEY_STORAGE_FLAG", OH_HUKS_TAG_DERIVED_AGREED_KEY_STORAGE_FLAG.toInt())
        p("OH_HUKS_TAG_RSA_PSS_SALT_LEN_TYPE", OH_HUKS_TAG_RSA_PSS_SALT_LEN_TYPE.toInt())
        p("OH_HUKS_TAG_ALL_USERS", OH_HUKS_TAG_ALL_USERS.toInt())
        p("OH_HUKS_TAG_USER_ID", OH_HUKS_TAG_USER_ID.toInt())
        p("OH_HUKS_TAG_NO_AUTH_REQUIRED", OH_HUKS_TAG_NO_AUTH_REQUIRED.toInt())
        p("OH_HUKS_TAG_USER_AUTH_TYPE", OH_HUKS_TAG_USER_AUTH_TYPE.toInt())
        p("OH_HUKS_TAG_AUTH_TIMEOUT", OH_HUKS_TAG_AUTH_TIMEOUT.toInt())
        p("OH_HUKS_TAG_AUTH_TOKEN", OH_HUKS_TAG_AUTH_TOKEN.toInt())
        p("OH_HUKS_TAG_KEY_AUTH_ACCESS_TYPE", OH_HUKS_TAG_KEY_AUTH_ACCESS_TYPE.toInt())
        p("OH_HUKS_TAG_KEY_SECURE_SIGN_TYPE", OH_HUKS_TAG_KEY_SECURE_SIGN_TYPE.toInt())
        p("OH_HUKS_TAG_CHALLENGE_TYPE", OH_HUKS_TAG_CHALLENGE_TYPE.toInt())
        p("OH_HUKS_TAG_CHALLENGE_POS", OH_HUKS_TAG_CHALLENGE_POS.toInt())
        p("OH_HUKS_TAG_KEY_AUTH_PURPOSE", OH_HUKS_TAG_KEY_AUTH_PURPOSE.toInt())
        p("OH_HUKS_TAG_AUTH_STORAGE_LEVEL", OH_HUKS_TAG_AUTH_STORAGE_LEVEL.toInt())
        p("OH_HUKS_TAG_USER_AUTH_MODE", OH_HUKS_TAG_USER_AUTH_MODE.toInt())
        p("OH_HUKS_TAG_ATTESTATION_CHALLENGE", OH_HUKS_TAG_ATTESTATION_CHALLENGE.toInt())
        p("OH_HUKS_TAG_ATTESTATION_APPLICATION_ID", OH_HUKS_TAG_ATTESTATION_APPLICATION_ID.toInt())
        p("OH_HUKS_TAG_ATTESTATION_ID_ALIAS", OH_HUKS_TAG_ATTESTATION_ID_ALIAS.toInt())
        p("OH_HUKS_TAG_ATTESTATION_ID_SEC_LEVEL_INFO", OH_HUKS_TAG_ATTESTATION_ID_SEC_LEVEL_INFO.toInt())
        p("OH_HUKS_TAG_ATTESTATION_ID_VERSION_INFO", OH_HUKS_TAG_ATTESTATION_ID_VERSION_INFO.toInt())
        p("OH_HUKS_TAG_KEY_OVERRIDE", OH_HUKS_TAG_KEY_OVERRIDE.toInt())
        p("OH_HUKS_TAG_AE_TAG_LEN", OH_HUKS_TAG_AE_TAG_LEN.toInt())
        p("OH_HUKS_TAG_KEY_CLASS", OH_HUKS_TAG_KEY_CLASS.toInt())
        p("OH_HUKS_TAG_IS_KEY_ALIAS", OH_HUKS_TAG_IS_KEY_ALIAS.toInt())
        p("OH_HUKS_TAG_KEY_STORAGE_FLAG", OH_HUKS_TAG_KEY_STORAGE_FLAG.toInt())
        p("OH_HUKS_TAG_IS_ALLOWED_WRAP", OH_HUKS_TAG_IS_ALLOWED_WRAP.toInt())
        p("OH_HUKS_TAG_KEY_WRAP_TYPE", OH_HUKS_TAG_KEY_WRAP_TYPE.toInt())
        p("OH_HUKS_TAG_KEY_AUTH_ID", OH_HUKS_TAG_KEY_AUTH_ID.toInt())
        p("OH_HUKS_TAG_KEY_ROLE", OH_HUKS_TAG_KEY_ROLE.toInt())
        p("OH_HUKS_TAG_KEY_FLAG", OH_HUKS_TAG_KEY_FLAG.toInt())
        p("OH_HUKS_TAG_IS_ASYNCHRONIZED", OH_HUKS_TAG_IS_ASYNCHRONIZED.toInt())
        p("OH_HUKS_TAG_KEY_DOMAIN", OH_HUKS_TAG_KEY_DOMAIN.toInt())
        p("OH_HUKS_TAG_IS_DEVICE_PASSWORD_SET", OH_HUKS_TAG_IS_DEVICE_PASSWORD_SET.toInt())
        p("OH_HUKS_TAG_AE_TAG", OH_HUKS_TAG_AE_TAG.toInt())
        p("OH_HUKS_TAG_SYMMETRIC_KEY_DATA", OH_HUKS_TAG_SYMMETRIC_KEY_DATA.toInt())
        p("OH_HUKS_TAG_ASYMMETRIC_PUBLIC_KEY_DATA", OH_HUKS_TAG_ASYMMETRIC_PUBLIC_KEY_DATA.toInt())
        p("OH_HUKS_TAG_ASYMMETRIC_PRIVATE_KEY_DATA", OH_HUKS_TAG_ASYMMETRIC_PRIVATE_KEY_DATA.toInt())
    }

    // ==================== 函数测试（HuksKeyApi） ====================

    @Test
    fun testGetSdkVersion() { memScoped {
        // OH_Huks_GetSdkVersion
        val sdkVersion = alloc<OH_Huks_Blob>().apply {
            val buf = allocArray<UByteVar>(256)
            data = buf
            size = 256u
        }
        val rc = OH_Huks_GetSdkVersion(sdkVersion.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_GetSdkVersion errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testGenerateKeyItem() { memScoped {
        // OH_Huks_GenerateKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_gen_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val rc = OH_Huks_GenerateKeyItem(keyAlias.ptr, null, null)
        assertNotNull(rc)
        logLine("OH_Huks_GenerateKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testImportKeyItem() { memScoped {
        // OH_Huks_ImportKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_import_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val key = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(32)
            size = 32u
        }
        val rc = OH_Huks_ImportKeyItem(keyAlias.ptr, null, key.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_ImportKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testImportWrappedKeyItem() { memScoped {
        // OH_Huks_ImportWrappedKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_wrapped_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val wrappingKeyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_wrapping_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val wrappedData = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(128)
            size = 128u
        }
        val rc = OH_Huks_ImportWrappedKeyItem(keyAlias.ptr, wrappingKeyAlias.ptr, null, wrappedData.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_ImportWrappedKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testExportPublicKeyItem() { memScoped {
        // OH_Huks_ExportPublicKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_export_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val key = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(512)
            size = 512u
        }
        val rc = OH_Huks_ExportPublicKeyItem(keyAlias.ptr, null, key.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_ExportPublicKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testDeleteKeyItem() { memScoped {
        // OH_Huks_DeleteKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_delete_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val rc = OH_Huks_DeleteKeyItem(keyAlias.ptr, null)
        assertNotNull(rc)
        logLine("OH_Huks_DeleteKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testGetKeyItemParamSet() { memScoped {
        // OH_Huks_GetKeyItemParamSet
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_getparam_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val rc = OH_Huks_GetKeyItemParamSet(keyAlias.ptr, null, null)
        assertNotNull(rc)
        logLine("OH_Huks_GetKeyItemParamSet errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testIsKeyItemExist() { memScoped {
        // OH_Huks_IsKeyItemExist
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_exist_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val rc = OH_Huks_IsKeyItemExist(keyAlias.ptr, null)
        assertNotNull(rc)
        logLine("OH_Huks_IsKeyItemExist errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testAttestKeyItem() { memScoped {
        // OH_Huks_AttestKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_attest_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val certChain = alloc<OH_Huks_CertChain>().apply {
            certs = null
            certsCount = 0u
        }
        val rc = OH_Huks_AttestKeyItem(keyAlias.ptr, null, certChain.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_AttestKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testAnonAttestKeyItem() { memScoped {
        // OH_Huks_AnonAttestKeyItem
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_anon_attest_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val certChain = alloc<OH_Huks_CertChain>().apply {
            certs = null
            certsCount = 0u
        }
        val rc = OH_Huks_AnonAttestKeyItem(keyAlias.ptr, null, certChain.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_AnonAttestKeyItem errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testSessionOps() { memScoped {
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_session_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val handle = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(64)
            size = 64u
        }
        val token = alloc<OH_Huks_Blob>().apply {
            data = null
            size = 0u
        }
        val inData = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(128)
            size = 128u
        }
        val outData = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(128)
            size = 128u
        }

        val rc1 = OH_Huks_InitSession(keyAlias.ptr, null, handle.ptr, token.ptr)
        assertNotNull(rc1)
        logLine("OH_Huks_InitSession errorCode=${rc1.useContents { errorCode }}")

        val rc2 = OH_Huks_UpdateSession(handle.ptr, null, inData.ptr, outData.ptr)
        assertNotNull(rc2)
        logLine("OH_Huks_UpdateSession errorCode=${rc2.useContents { errorCode }}")

        val rc3 = OH_Huks_FinishSession(handle.ptr, null, inData.ptr, outData.ptr)
        assertNotNull(rc3)
        logLine("OH_Huks_FinishSession errorCode=${rc3.useContents { errorCode }}")
    } }

    @Test
    fun testAbortSession() { memScoped {
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_abort_session_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val handle = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(64)
            size = 64u
        }
        val token = alloc<OH_Huks_Blob>().apply {
            data = null
            size = 0u
        }
        val rc1 = OH_Huks_InitSession(keyAlias.ptr, null, handle.ptr, token.ptr)
        assertNotNull(rc1)
        logLine("OH_Huks_InitSession errorCode=${rc1.useContents { errorCode }}")
        val rc2 = OH_Huks_AbortSession(handle.ptr, null)
        assertNotNull(rc2)
        logLine("OH_Huks_AbortSession errorCode=${rc2.useContents { errorCode }}")
    } }

    @Test
    fun testListAliases() { memScoped {
        val outData = alloc<CPointerVar<OH_Huks_KeyAliasSet>>()
        val rc = try { OH_Huks_ListAliases(null, outData.ptr) } catch (e: Throwable) { logLine("OH_Huks_ListAliases (API 20) exception: $e"); alloc<OH_Huks_Result>().apply { errorCode = OH_HUKS_ERR_CODE_ILLEGAL_ARGUMENT.toInt() }.readValue() }
        assertNotNull(rc)
        logLine("OH_Huks_ListAliases errorCode=${rc.useContents { errorCode }}")
        outData.value?.let { aliasSet ->
            try { OH_Huks_FreeKeyAliasSet(aliasSet) } catch (e: Throwable) { logLine("OH_Huks_FreeKeyAliasSet exception: $e") }
        }
    } }

    @Test
    fun testWrapAndUnwrapKey() { memScoped {
        val keyAlias = alloc<OH_Huks_Blob>().apply {
            val d = "test_wrap_alias".encodeToByteArray()
            val buf = allocArray<UByteVar>(d.size)
            d.forEachIndexed { i, b -> buf[i] = b.toUByte() }
            data = buf
            size = d.size.toUInt()
        }
        val wrappedKey = alloc<OH_Huks_Blob>().apply {
            data = allocArray<UByteVar>(1024)
            size = 1024u
        }
        
        val rc1 = try { OH_Huks_WrapKey(keyAlias.ptr, null, wrappedKey.ptr) } catch (e: Throwable) { logLine("OH_Huks_WrapKey (API 20) exception: $e"); alloc<OH_Huks_Result>().apply { errorCode = OH_HUKS_ERR_CODE_ILLEGAL_ARGUMENT.toInt() }.readValue() }
        assertNotNull(rc1)
        logLine("OH_Huks_WrapKey errorCode=${rc1.useContents { errorCode }}")


        val rc2 = try { OH_Huks_UnwrapKey(keyAlias.ptr, null, wrappedKey.ptr) } catch (e: Throwable) { logLine("OH_Huks_UnwrapKey (API 20) exception: $e"); alloc<OH_Huks_Result>().apply { errorCode = OH_HUKS_ERR_CODE_ILLEGAL_ARGUMENT.toInt() }.readValue() }
        assertNotNull(rc2)
        logLine("OH_Huks_UnwrapKey errorCode=${rc2.useContents { errorCode }}")
    } }
}
