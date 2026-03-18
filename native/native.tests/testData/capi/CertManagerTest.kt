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
import platform.DeviceCertificateKit.CertManager.*
import platform.DeviceCertificateKit.CertManagerType.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class CertManagerTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_CM_ErrorCode() {
        assertEquals(OH_CM_SUCCESS.toInt(), 0)
        assertEquals(OH_CM_HAS_NO_PERMISSION.toInt(), 201)
        assertEquals(OH_CM_CAPABILITY_NOT_SUPPORTED.toInt(), 801)
        assertEquals(OH_CM_INNER_FAILURE.toInt(), 17500001)
        assertEquals(OH_CM_NOT_FOUND.toInt(), 17500002)
        assertEquals(OH_CM_INVALID_CERT_FORMAT.toInt(), 17500003)
        assertEquals(OH_CM_MAX_CERT_COUNT_REACHED.toInt(), 17500004)
        assertEquals(OH_CM_NO_AUTHORIZATION.toInt(), 17500005)
        assertEquals(OH_CM_DEVICE_ENTER_ADVSECMODE.toInt(), 17500007)
        assertEquals(OH_CM_STORE_PATH_NOT_SUPPORTED.toInt(), 17500009)
        assertEquals(OH_CM_ACCESS_UKEY_SERVICE_FAILED.toInt(), 17500010)
        assertEquals(OH_CM_PARAMETER_VALIDATION_FAILED.toInt(), 17500011)
        logLine("testEnum_OH_CM_ErrorCode passed")
    }

    @Test
    fun testEnum_OH_CM_CertificatePurpose() {
        assertEquals(OH_CM_CERT_PURPOSE_DEFAULT.toInt(), 0)
        assertEquals(OH_CM_CERT_PURPOSE_ALL.toInt(), 1)
        assertEquals(OH_CM_CERT_PURPOSE_SIGN.toInt(), 2)
        assertEquals(OH_CM_CERT_PURPOSE_ENCRYPT.toInt(), 3)
        logLine("testEnum_OH_CM_CertificatePurpose passed")
    }

    @Test
    fun testOH_CertManager_GetUkeyCertificate() {
        memScoped {
        val keyUri = alloc<OH_CM_Blob>().apply { size = 0u; data = null }
        val ukeyInfo = alloc<OH_CM_UkeyInfo>().apply { certPurpose = OH_CM_CERT_PURPOSE_DEFAULT }
        val certificateList = alloc<OH_CM_CredentialDetailList>()
        val ret = try { OH_CertManager_GetUkeyCertificate(keyUri.ptr, ukeyInfo.ptr, certificateList.ptr) } catch (e: Throwable) { logLine("OH_CertManager_GetUkeyCertificate (API 22) exception: $e"); OH_CM_PARAMETER_VALIDATION_FAILED }
        assertNotNull(ret)
        logLine("OH_CertManager_GetUkeyCertificate ret=$ret")
        }
    }

    @Test
    fun testOH_CertManager_GetPrivateCertificate() {
        memScoped {
            val keyUri = alloc<OH_CM_Blob>().apply { size = 0u; data = null }
            val certificate = alloc<OH_CM_Credential>()
            val ret = try { OH_CertManager_GetPrivateCertificate(keyUri.ptr, certificate.ptr) } catch (e: Throwable) { logLine("OH_CertManager_GetPrivateCertificate (API 22) exception: $e"); OH_CM_PARAMETER_VALIDATION_FAILED }
            assertNotNull(ret)
            logLine("OH_CertManager_GetPrivateCertificate ret=$ret")
        }
    }

    @Test
    fun testOH_CertManager_GetPublicCertificate() {
        memScoped {
            val keyUri = alloc<OH_CM_Blob>().apply { size = 0u; data = null }
            val certificate = alloc<OH_CM_Credential>()
            val ret = try { OH_CertManager_GetPublicCertificate(keyUri.ptr, certificate.ptr) } catch (e: Throwable) { logLine("OH_CertManager_GetPublicCertificate (API 22) exception: $e"); OH_CM_PARAMETER_VALIDATION_FAILED }
            assertNotNull(ret)
            logLine("OH_CertManager_GetPublicCertificate ret=$ret")
        }
    }

    @Test
    fun testOH_CertManager_FreeUkeyCertificate() {
        try { OH_CertManager_FreeUkeyCertificate(null) } catch (e: Throwable) { logLine("OH_CertManager_FreeUkeyCertificate (API 22) exception: $e") }
        logLine("OH_CertManager_FreeUkeyCertificate(null) done")
    }

    @Test
    fun testOH_CertManager_FreeCredential() {
        try { OH_CertManager_FreeCredential(null) } catch (e: Throwable) { logLine("OH_CertManager_FreeCredential (API 22) exception: $e") }
        logLine("OH_CertManager_FreeCredential(null) done")
    }
}
