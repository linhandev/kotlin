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

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    ExperimentalForeignApi::class
)
class FIDO2Test {
    private fun logLine(message: String) = println("[stdout] FIDO2Test $message")

    // ---------- enums: OnlineAuthenticationKit/fido2_api.h ----------

    @Test
    fun testEnum_FIDO2_TokenBindingStatus() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PRESENT
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_SUPPORTED
        logLine("FIDO2_PRESENT=$v0"); assert(v0.toInt() == 0)
        logLine("FIDO2_SUPPORTED=$v1"); assert(v1.toInt() == 1)
    }

    @Test
    fun testEnum_FIDO2_AttestationConveyancePreference() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_NONE
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_INDIRECT
        val v2 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_DIRECT
        val v3 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_ENTERPRISE
        logLine("FIDO2_NONE=$v0"); assert(v0.toInt() == 0)
        logLine("FIDO2_INDIRECT=$v1"); assert(v1.toInt() == 1)
        logLine("FIDO2_DIRECT=$v2"); assert(v2.toInt() == 2)
        logLine("FIDO2_ENTERPRISE=$v3"); assert(v3.toInt() == 3)
    }

    @Test
    fun testEnum_FIDO2_UserVerificationRequirement() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_REQUIRED
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PREFERRED
        val v2 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_DISCOURAGED
        logLine("FIDO2_REQUIRED=$v0"); assert(v0.toInt() == 0)
        logLine("FIDO2_PREFERRED=$v1"); assert(v1.toInt() == 1)
        logLine("FIDO2_DISCOURAGED=$v2"); assert(v2.toInt() == 2)
    }

    @Test
    fun testEnum_FIDO2_AuthenticatorAttachment() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PLATFORM
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_CROSS_PLATFORM
        logLine("FIDO2_PLATFORM=$v0"); assert(v0.toInt() == 0)
        logLine("FIDO2_CROSS_PLATFORM=$v1"); assert(v1.toInt() == 1)
    }

    @Test
    fun testEnum_FIDO2_AuthenticatorTransport() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_USB
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_NFC
        val v2 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_BLE
        val v3 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_SMART_CARD
        val v4 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_HYBRID
        val v5 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_INTERNAL
        logLine("FIDO2_USB=$v0"); assert(v0.toInt()  == 0)
        logLine("FIDO2_NFC=$v1"); assert(v1.toInt() == 1)
        logLine("FIDO2_BLE=$v2"); assert(v2.toInt() == 2)
        logLine("FIDO2_SMART_CARD=$v3"); assert(v3.toInt() == 3)
        logLine("FIDO2_HYBRID=$v4"); assert(v4.toInt() == 4)
        logLine("FIDO2_INTERNAL=$v5"); assert(v5.toInt() == 5)
    }

    @Test
    fun testEnum_FIDO2_Algorithm() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assert(v == expected)
        }
        p("FIDO2_ES256", platform.OnlineAuthenticationKit.FIDO2.FIDO2_ES256, -7)
        p("FIDO2_ES384", platform.OnlineAuthenticationKit.FIDO2.FIDO2_ES384, -35)
        p("FIDO2_ES512", platform.OnlineAuthenticationKit.FIDO2.FIDO2_ES512, -36)
        p("FIDO2_RS256", platform.OnlineAuthenticationKit.FIDO2.FIDO2_RS256, -257)
        p("FIDO2_RS384", platform.OnlineAuthenticationKit.FIDO2.FIDO2_RS384, -258)
        p("FIDO2_RS512", platform.OnlineAuthenticationKit.FIDO2.FIDO2_RS512, -259)
        p("FIDO2_PS256", platform.OnlineAuthenticationKit.FIDO2.FIDO2_PS256, -37)
        p("FIDO2_PS384", platform.OnlineAuthenticationKit.FIDO2.FIDO2_PS384, -38)
        p("FIDO2_PS512", platform.OnlineAuthenticationKit.FIDO2.FIDO2_PS512, -39)
    }

    @Test
    fun testEnum_FIDO2_PublicKeyCredentialHint() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_SECURITY_KEY
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_CLIENT_DEVICE
        val v2 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_HINT_HYBRID
        logLine("FIDO2_SECURITY_KEY=$v0"); assert(v0.toInt() == 0)
        logLine("FIDO2_CLIENT_DEVICE=$v1"); assert(v1.toInt() == 1)
        logLine("FIDO2_HINT_HYBRID=$v2"); assert(v2.toInt() == 2)
    }

    @Test
    fun testEnum_FIDO2_PublicKeyCredentialType() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PUBLIC_KEY
        logLine("FIDO2_PUBLIC_KEY=$v0"); assert(v0.toInt() == 0)
    }

    @Test
    fun testEnum_FIDO2_Uvm() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_UVM_FINGERPRINT
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_UVM_PIN
        val v2 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_UVM_FACEPRINT
        logLine("FIDO2_UVM_FINGERPRINT=$v0"); assert(v0.toInt() == 2)
        logLine("FIDO2_UVM_PIN=$v1"); assert(v1.toInt() == 4)
        logLine("FIDO2_UVM_FACEPRINT=$v2"); assert(v2.toInt() == 16)
    }

    @Test
    fun testEnum_FIDO2_ClientCapability() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assert(v == expected)
        }
        p("FIDO2_CONDITIONAL_CREATE", platform.OnlineAuthenticationKit.FIDO2.FIDO2_CONDITIONAL_CREATE.toInt(), 0)
        p("FIDO2_CONDITIONAL_GET", platform.OnlineAuthenticationKit.FIDO2.FIDO2_CONDITIONAL_GET.toInt(), 1)
        p("FIDO2_HYBRID_TRANSPORT", platform.OnlineAuthenticationKit.FIDO2.FIDO2_HYBRID_TRANSPORT.toInt(), 2)
        p("FIDO2_PASSKEY_PLATFORM_AUTHENTICATOR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_PASSKEY_PLATFORM_AUTHENTICATOR.toInt(), 3)
        p("FIDO2_USER_VERIFYING_PLATFORM_AUTHENTICATOR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_USER_VERIFYING_PLATFORM_AUTHENTICATOR.toInt(), 4)
        p("FIDO2_RELATED_ORIGINS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_RELATED_ORIGINS.toInt(), 5)
        p("FIDO2_SIGNAL_ALL_ACCEPTED_CREDENTIALS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_SIGNAL_ALL_ACCEPTED_CREDENTIALS.toInt(), 6)
        p("FIDO2_SIGNAL_CURRENT_USER_DETAILS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_SIGNAL_CURRENT_USER_DETAILS.toInt(), 7)
        p("FIDO2_SIGNAL_UNKNOWN_CREDENTIAL", platform.OnlineAuthenticationKit.FIDO2.FIDO2_SIGNAL_UNKNOWN_CREDENTIAL.toInt(), 8)
        p("FIDO2_EXTENSION_UVI", platform.OnlineAuthenticationKit.FIDO2.FIDO2_EXTENSION_UVI.toInt(), 9)
    }

    @Test
    fun testEnum_FIDO2_CredentialMediationRequirement() {
        val v0 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_SILENT
        val v1 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_OPTIONAL
        val v2 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_CONDITIONAL
        val v3 = platform.OnlineAuthenticationKit.FIDO2.FIDO2_MEDIATION_REQUIRED
        logLine("FIDO2_SILENT=$v0"); assert(v0.toInt() == 0)
        logLine("FIDO2_OPTIONAL=$v1"); assert(v1.toInt() == 1)
        logLine("FIDO2_CONDITIONAL=$v2"); assert(v2.toInt() == 2)
        logLine("FIDO2_MEDIATION_REQUIRED=$v3"); assert(v3.toInt() == 3)
    }

    @Test
    fun testEnum_FIDO2_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assert(v == expected)
        }

        p("FIDO2_SUCCESS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_SUCCESS.toInt(), 0)
        p("FIDO2_PERMISSION_DENIED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_PERMISSION_DENIED.toInt(), 201)
        p("FIDO2_NOT_SUPPORT", platform.OnlineAuthenticationKit.FIDO2.FIDO2_NOT_SUPPORT.toInt(), 1021300001)
        p("FIDO2_INVALID_STATE", platform.OnlineAuthenticationKit.FIDO2.FIDO2_INVALID_STATE.toInt(), 1021300002)
        p("FIDO2_INTEGRITY_CHECK_FAILED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_INTEGRITY_CHECK_FAILED.toInt(), 1021300003)
        p("FIDO2_USER_ABORT", platform.OnlineAuthenticationKit.FIDO2.FIDO2_USER_ABORT.toInt(), 1021300004)
        p("FIDO2_TIMEOUT", platform.OnlineAuthenticationKit.FIDO2.FIDO2_TIMEOUT.toInt(), 1021300005)
        p("FIDO2_ENCODING_ERROR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_ENCODING_ERROR.toInt(), 1021300006)
        p("FIDO2_UNKNOWN_ERROR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_UNKNOWN_ERROR.toInt(), 1021300007)
        p("FIDO2_CONSTRAINT_ERROR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_CONSTRAINT_ERROR.toInt(), 1021300008)
        p("FIDO2_DATA_ERROR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_DATA_ERROR.toInt(), 1021300009)
        p("FIDO2_USER_REJECTS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_USER_REJECTS.toInt(), 1021300010)
        p("FIDO2_CONNECT_SERVICE_FAILED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_CONNECT_SERVICE_FAILED.toInt(), 1021300011)
        p("FIDO2_MAX_CRED_NUM_REACHED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_MAX_CRED_NUM_REACHED.toInt(), 1021300012)
        p("FIDO2_INVALID_CTAP_COMMAND", platform.OnlineAuthenticationKit.FIDO2.FIDO2_INVALID_CTAP_COMMAND.toInt(), 1021310001)
        p("FIDO2_INVALID_PARAMETERS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_INVALID_PARAMETERS.toInt(), 1021310002)
        p("FIDO2_INVALID_MESSAGE_OR_ATTRIBUTE_LENGTH",
            platform.OnlineAuthenticationKit.FIDO2.FIDO2_INVALID_MESSAGE_OR_ATTRIBUTE_LENGTH.toInt(), 1021310003)
        p("FIDO2_INVALID_CBOR_OR_UNPREDICTABLE",
            platform.OnlineAuthenticationKit.FIDO2.FIDO2_INVALID_CBOR_OR_UNPREDICTABLE.toInt(), 1021310004)
        p("FIDO2_PARSE_CBOR_FAILED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_PARSE_CBOR_FAILED.toInt(), 1021310005)
        p("FIDO2_INVALID_CREDENTIALS", platform.OnlineAuthenticationKit.FIDO2.FIDO2_INVALID_CREDENTIALS.toInt(), 1021310006)
        p("FIDO2_NOT_ALLOWED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_NOT_ALLOWED.toInt(), 1021310007)
        p("FIDO2_USER_VERIFICATION_FAILED", platform.OnlineAuthenticationKit.FIDO2.FIDO2_USER_VERIFICATION_FAILED.toInt(), 1021310008)
        p("FIDO2_OTHER_ERROR", platform.OnlineAuthenticationKit.FIDO2.FIDO2_OTHER_ERROR.toInt(), 1021310009)
        p("FIDO2_DEVICE_NOT_SUPPORT", platform.OnlineAuthenticationKit.FIDO2.FIDO2_DEVICE_NOT_SUPPORT.toInt(), 801)
    }

    // ---------- functions: OnlineAuthenticationKit/fido2_api.h ----------

    @Test
    fun testHMS_FIDO2_initCreationOptions() {
        memScoped {
            val options = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_CredentialCreationOptions>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initCreationOptions(options.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initCreationOptions (API 20) exception: $e") }
            logLine("HMS_FIDO2_initCreationOptions=called")
        }
    }

    @Test
    fun testHMS_FIDO2_initTokenBinding() {
        memScoped {
            val tokenBinding = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_TokenBinding>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initTokenBinding(tokenBinding.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initTokenBinding (API 20) exception: $e") }
            logLine("HMS_FIDO2_initTokenBinding=called")
        }
    }

    @Test
    fun testHMS_FIDO2_initRequestOptions() {
        memScoped {
            val options = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_CredentialRequestOptions>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initRequestOptions(options.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initRequestOptions (API 20) exception: $e") }
            logLine("HMS_FIDO2_initRequestOptions=called")
        }
    }

    @Test
    fun testHMS_FIDO2_getClientCapability() {
        memScoped {
            val out = alloc<kotlinx.cinterop.CPointerVar<platform.OnlineAuthenticationKit.FIDO2.FIDO2_CapabilityArray>>()
            val rc = try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_getClientCapability(out.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_getClientCapability (API 20) exception: $e"); platform.OnlineAuthenticationKit.FIDO2.FIDO2_NOT_SUPPORT }
            assertNotNull(rc)
            logLine("HMS_FIDO2_getClientCapability=$rc")
            logLine("FIDO2_CapabilityArray** out=${out.value}")

            if (rc.toInt() == platform.OnlineAuthenticationKit.FIDO2.FIDO2_SUCCESS.toInt() && out.value != null) {
                try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_CapabilityArray_Destroy(out.value) } catch (e: Throwable) { logLine("HMS_FIDO2_CapabilityArray_Destroy (API 20) exception: $e") }
                logLine("HMS_FIDO2_CapabilityArray_Destroy=called")
            }
        }
    }

    @Test
    fun testHMS_FIDO2_getPlatformAuthenticator() {
        memScoped {
            val out = alloc<kotlinx.cinterop.CPointerVar<platform.OnlineAuthenticationKit.FIDO2.FIDO2_AuthenticatorMetadataArray>>()
            val rc = try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_getPlatformAuthenticator(out.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_getPlatformAuthenticator (API 20) exception: $e"); platform.OnlineAuthenticationKit.FIDO2.FIDO2_NOT_SUPPORT }
            assertNotNull(rc)
            logLine("HMS_FIDO2_getPlatformAuthenticator=$rc")
            logLine("FIDO2_AuthenticatorMetadataArray** out=${out.value}")
        }
    }

    @Test
    fun testHMS_FIDO2_register() {
        memScoped {
            val creation = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_CredentialCreationOptions>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initCreationOptions(creation.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initCreationOptions (API 20) exception: $e") }

            creation.mediation = platform.OnlineAuthenticationKit.FIDO2.FIDO2_OPTIONAL
            creation.publicKey.rp.id = "example.com".cstr.getPointer(this@memScoped)
            creation.publicKey.rp.name = "Example RP".cstr.getPointer(this@memScoped)

            val userIdBuf = allocArray<UByteVar>(1)
            userIdBuf[0] = 1.toUByte()
            creation.publicKey.user.id.length = 1u
            creation.publicKey.user.id.`val` = userIdBuf
            creation.publicKey.user.name = "user@example.com".cstr.getPointer(this@memScoped)
            creation.publicKey.user.displayName = "Test User".cstr.getPointer(this@memScoped)

            val challengeBuf = allocArray<UByteVar>(32)
            for (i in 0..31) challengeBuf[i] = (i and 0xff).toUByte()
            creation.publicKey.challenge.length = 32u
            creation.publicKey.challenge.`val` = challengeBuf

            val oneParam = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_PublicKeyCredentialParameters>().apply {
                type = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PUBLIC_KEY
                alg = platform.OnlineAuthenticationKit.FIDO2.FIDO2_ES256
            }
            creation.publicKey.pubKeyCredParams.pubKeyCredParamNum = 1u
            creation.publicKey.pubKeyCredParams.pubKeyCredParams = oneParam.ptr

            creation.publicKey.timeout = 60000u
            creation.publicKey.authenticatorSelection.authenticatorAttachment = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PLATFORM
            creation.publicKey.authenticatorSelection.userVerification = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PREFERRED
            creation.publicKey.authenticatorSelection.requireResidentKey = false
            creation.publicKey.authenticatorSelection.residentKey = "discouraged".cstr.getPointer(this@memScoped)

            val tokenBinding = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_TokenBinding>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initTokenBinding(tokenBinding.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initTokenBinding (API 20) exception: $e") }

            val outCred = alloc<CPointerVar<platform.OnlineAuthenticationKit.FIDO2.FIDO2_PublicKeyAttestationCredential>>()
            val rc = try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_register(
                creation.readValue(),
                tokenBinding.readValue(),
                "https://example.com",
                outCred.ptr
            ) } catch (e: Throwable) { logLine("HMS_FIDO2_register (API 20) exception: $e"); platform.OnlineAuthenticationKit.FIDO2.FIDO2_NOT_SUPPORT }
            assertNotNull(rc)
            logLine("HMS_FIDO2_register=$rc")
            if (rc.toInt() == platform.OnlineAuthenticationKit.FIDO2.FIDO2_SUCCESS.toInt() && outCred.value != null) {
                try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_PublicKeyAttestationCredential_Destroy(outCred.value) } catch (e: Throwable) { logLine("HMS_FIDO2_PublicKeyAttestationCredential_Destroy (API 20) exception: $e") }
            }
        }
    }

    @Test
    fun testHMS_FIDO2_authenticate() {
        memScoped {
            val request = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_CredentialRequestOptions>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initRequestOptions(request.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initRequestOptions (API 20) exception: $e") }

            request.mediation = platform.OnlineAuthenticationKit.FIDO2.FIDO2_OPTIONAL
            val challengeBuf = allocArray<UByteVar>(32)
            for (i in 0..31) challengeBuf[i] = (i and 0xff).toUByte()
            request.publicKey.challenge.length = 32u
            request.publicKey.challenge.`val` = challengeBuf
            request.publicKey.timeout = 60000u
            request.publicKey.rpId = "example.com".cstr.getPointer(this@memScoped)
            request.publicKey.userVerification = platform.OnlineAuthenticationKit.FIDO2.FIDO2_PREFERRED
            request.publicKey.allowCredentials.allowCredentiallNum = 0u
            request.publicKey.allowCredentials.allowCredentials = null

            val tokenBinding = alloc<platform.OnlineAuthenticationKit.FIDO2.FIDO2_TokenBinding>()
            try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_initTokenBinding(tokenBinding.ptr) } catch (e: Throwable) { logLine("HMS_FIDO2_initTokenBinding (API 20) exception: $e") }

            val outCred = alloc<CPointerVar<platform.OnlineAuthenticationKit.FIDO2.FIDO2_PublicKeyAssertionCredential>>()
            val rc = try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_authenticate(
                request.readValue(),
                tokenBinding.readValue(),
                "https://example.com",
                outCred.ptr
            ) } catch (e: Throwable) { logLine("HMS_FIDO2_authenticate (API 20) exception: $e"); platform.OnlineAuthenticationKit.FIDO2.FIDO2_NOT_SUPPORT }
            assertNotNull(rc)
            logLine("HMS_FIDO2_authenticate=$rc")
            if (rc.toInt() == platform.OnlineAuthenticationKit.FIDO2.FIDO2_SUCCESS.toInt() && outCred.value != null) {
                try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_PublicKeyAssertionCredential_Destroy(outCred.value) } catch (e: Throwable) { logLine("HMS_FIDO2_PublicKeyAssertionCredential_Destroy (API 20) exception: $e") }
            }
        }
    }

    @Test
    fun testHMS_FIDO2_CapabilityArray_Destroy() {
        try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_CapabilityArray_Destroy(null) } catch (e: Throwable) { logLine("HMS_FIDO2_CapabilityArray_Destroy (API 20) exception: $e") }
        logLine("HMS_FIDO2_CapabilityArray_Destroy(null)=called")
    }

    @Test
    fun testHMS_FIDO2_AuthenticatorMetadataArray_Destroy() {
        try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_AuthenticatorMetadataArray_Destroy(null) } catch (e: Throwable) { logLine("HMS_FIDO2_AuthenticatorMetadataArray_Destroy (API 20) exception: $e") }
        logLine("HMS_FIDO2_AuthenticatorMetadataArray_Destroy(null)=called")
    }

    @Test
    fun testHMS_FIDO2_PublicKeyAttestationCredential_Destroy() {
        try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_PublicKeyAttestationCredential_Destroy(null) } catch (e: Throwable) { logLine("HMS_FIDO2_PublicKeyAttestationCredential_Destroy (API 20) exception: $e") }
        logLine("HMS_FIDO2_PublicKeyAttestationCredential_Destroy(null)=called")
    }

    @Test
    fun testHMS_FIDO2_PublicKeyAssertionCredential_Destroy() {
        try { platform.OnlineAuthenticationKit.FIDO2.HMS_FIDO2_PublicKeyAssertionCredential_Destroy(null) } catch (e: Throwable) { logLine("HMS_FIDO2_PublicKeyAssertionCredential_Destroy (API 20) exception: $e") }
        logLine("HMS_FIDO2_PublicKeyAssertionCredential_Destroy(null)=called")
    }
}

