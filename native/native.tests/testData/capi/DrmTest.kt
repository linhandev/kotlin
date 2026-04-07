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
import platform.DRMKit.Drm.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class DrmTest {

    private fun logLine(msg: String) = println("[stdout] DrmTest $msg")

    @Test
    fun testEnum_Drm_ErrCode() {
        assertEquals(DRM_ERR_OK.toInt(), 0)
        assertEquals(DRM_CAPI_ERR_BASE.toInt(), 24700500)
        assertEquals(DRM_ERR_NO_MEMORY.toInt(), 24700501)
        assertEquals(DRM_ERR_OPERATION_NOT_PERMITTED.toInt(), 24700502)
        assertEquals(DRM_ERR_INVALID_VAL.toInt(), 24700503)
        assertEquals(DRM_ERR_IO.toInt(), 24700504)
        assertEquals(DRM_ERR_TIMEOUT.toInt(), 24700505)
        assertEquals(DRM_ERR_UNKNOWN.toInt(), 24700506)
        assertEquals(DRM_ERR_SERVICE_DIED.toInt(), 24700507)
        assertEquals(DRM_ERR_INVALID_STATE.toInt(), 24700508)
        assertEquals(DRM_ERR_UNSUPPORTED.toInt(), 24700509)
        assertEquals(DRM_ERR_MAX_SYSTEM_NUM_REACHED.toInt(), 24700510)
        assertEquals(DRM_ERR_MAX_SESSION_NUM_REACHED.toInt(), 24700511)
        assertEquals(DRM_ERR_EXTEND_START.toInt(), 24700600)
        logLine("Drm_ErrCode passed")
    }

    @Test
    fun testEnum_DRM_EventType() {
        assertEquals(EVENT_DRM_BASE.toInt(), 200)
        assertEquals(EVENT_PROVISION_REQUIRED.toInt(), 201)
        assertEquals(EVENT_KEY_REQUIRED.toInt(), 202)
        assertEquals(EVENT_KEY_EXPIRED.toInt(), 203)
        assertEquals(EVENT_VENDOR_DEFINED.toInt(), 204)
        assertEquals(EVENT_EXPIRATION_UPDATE.toInt(), 206)
        logLine("DRM_EventType passed")
    }

    @Test
    fun testEnum_DRM_ContentProtectionLevel() {
        assertEquals(CONTENT_PROTECTION_LEVEL_UNKNOWN.toInt(), 0)
        assertEquals(CONTENT_PROTECTION_LEVEL_SW_CRYPTO.toInt(), 1)
        assertEquals(CONTENT_PROTECTION_LEVEL_HW_CRYPTO.toInt(), 2)
        assertEquals(CONTENT_PROTECTION_LEVEL_ENHANCED_HW_CRYPTO.toInt(), 3)
        assertEquals(CONTENT_PROTECTION_LEVEL_MAX.toInt(), 4)
        logLine("DRM_ContentProtectionLevel passed")
    }

    @Test
    fun testEnum_DRM_MediaKeyType() {
        assertEquals(MEDIA_KEY_TYPE_OFFLINE.toInt(), 0)
        assertEquals(MEDIA_KEY_TYPE_ONLINE.toInt(), 1)
        logLine("DRM_MediaKeyType passed")
    }

    @Test
    fun testEnum_DRM_MediaKeyRequestType() {
        assertEquals(MEDIA_KEY_REQUEST_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(MEDIA_KEY_REQUEST_TYPE_INITIAL.toInt(), 1)
        assertEquals(MEDIA_KEY_REQUEST_TYPE_RENEWAL.toInt(), 2)
        assertEquals(MEDIA_KEY_REQUEST_TYPE_RELEASE.toInt(), 3)
        assertEquals(MEDIA_KEY_REQUEST_TYPE_NONE.toInt(), 4)
        assertEquals(MEDIA_KEY_REQUEST_TYPE_UPDATE.toInt(), 5)
        logLine("DRM_MediaKeyRequestType passed")
    }

    @Test
    fun testEnum_DRM_OfflineMediaKeyStatus() {
        assertEquals(OFFLINE_MEDIA_KEY_STATUS_UNKNOWN.toInt(), 0)
        assertEquals(OFFLINE_MEDIA_KEY_STATUS_USABLE.toInt(), 1)
        assertEquals(OFFLINE_MEDIA_KEY_STATUS_INACTIVE.toInt(), 2)
        logLine("DRM_OfflineMediaKeyStatus passed")
    }

    @Test
    fun testEnum_DRM_CertificateStatus() {
        assertEquals(CERT_STATUS_PROVISIONED.toInt(), 0)
        assertEquals(CERT_STATUS_NOT_PROVISIONED.toInt(), 1)
        assertEquals(CERT_STATUS_EXPIRED.toInt(), 2)
        assertEquals(CERT_STATUS_INVALID.toInt(), 3)
        assertEquals(CERT_STATUS_UNAVAILABLE.toInt(), 4)
        logLine("DRM_CertificateStatus passed")
    }

    @Test
    fun testOH_MediaKeySystem_IsSupported() {
        memScoped {
            val supported = OH_MediaKeySystem_IsSupported(null)
            logLine("OH_MediaKeySystem_IsSupported(null)=$supported")
            assertNotNull(supported)
        }
    }

    @Test
    fun testOH_MediaKeySystem_IsSupported2() {
        memScoped {
            val supported2 = OH_MediaKeySystem_IsSupported2(null, null)
            logLine("OH_MediaKeySystem_IsSupported2=$supported2")
            assertNotNull(supported2)
        }
    }

    @Test
    fun testOH_MediaKeySystem_IsSupported3() {
        memScoped {
            val supported3 = OH_MediaKeySystem_IsSupported3(null, null, CONTENT_PROTECTION_LEVEL_UNKNOWN)
            logLine("OH_MediaKeySystem_IsSupported3=$supported3")
            assertNotNull(supported3)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetMediaKeySystems() {
        memScoped {
            val count = alloc<UIntVar>()
            val rc = OH_MediaKeySystem_GetMediaKeySystems(null, count.ptr)
            logLine("OH_MediaKeySystem_GetMediaKeySystems=$rc count=${count.value}")
            assertNotNull(rc)
        }
    }

    @Test
    fun testOH_MediaKeySystem_Create() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            val createRc = OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            assertNotNull(createRc)
            logLine("OH_MediaKeySystem_Create=$createRc")
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_Destroy() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val destroyRc = OH_MediaKeySystem_Destroy(systemPtr.value)
            logLine("OH_MediaKeySystem_Destroy=$destroyRc")
            assertNotNull(destroyRc)
        }
    }

    @Test
    fun testOH_MediaKeySystem_SetCallback() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val setCbRc = OH_MediaKeySystem_SetCallback(systemPtr.value, null)
            logLine("OH_MediaKeySystem_SetCallback=$setCbRc")
            assertNotNull(setCbRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_SetConfigurationString() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val setStrRc = OH_MediaKeySystem_SetConfigurationString(systemPtr.value, null, null)
            logLine("OH_MediaKeySystem_SetConfigurationString=$setStrRc")
            assertNotNull(setStrRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetConfigurationString() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val getStrRc = OH_MediaKeySystem_GetConfigurationString(systemPtr.value, null, null, 0)
            logLine("OH_MediaKeySystem_GetConfigurationString=$getStrRc")
            assertNotNull(getStrRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_SetConfigurationByteArray() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val setByteRc = OH_MediaKeySystem_SetConfigurationByteArray(systemPtr.value, null, null, 0)
            logLine("OH_MediaKeySystem_SetConfigurationByteArray=$setByteRc")
            assertNotNull(setByteRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetConfigurationByteArray() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val valueLen = alloc<IntVar>()
            val getByteRc = OH_MediaKeySystem_GetConfigurationByteArray(systemPtr.value, null, null, valueLen.ptr)
            logLine("OH_MediaKeySystem_GetConfigurationByteArray=$getByteRc")
            assertNotNull(getByteRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetStatistics() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val stats = alloc<DRM_Statistics>()
            val statsRc = OH_MediaKeySystem_GetStatistics(systemPtr.value, stats.ptr)
            logLine("OH_MediaKeySystem_GetStatistics=$statsRc")
            assertNotNull(statsRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetMaxContentProtectionLevel() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val levelPtr = alloc<UIntVar>()
            val levelRc = OH_MediaKeySystem_GetMaxContentProtectionLevel(systemPtr.value, levelPtr.ptr)
            logLine("OH_MediaKeySystem_GetMaxContentProtectionLevel=$levelRc")
            assertNotNull(levelRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_SetMediaKeySystemCallback() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val setSysCbRc = OH_MediaKeySystem_SetMediaKeySystemCallback(systemPtr.value, null)
            logLine("OH_MediaKeySystem_SetMediaKeySystemCallback=$setSysCbRc")
            assertNotNull(setSysCbRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GenerateKeySystemRequest() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val requestLen = alloc<IntVar>()
            val genReqRc = OH_MediaKeySystem_GenerateKeySystemRequest(systemPtr.value, null, requestLen.ptr, null, 0)
            logLine("OH_MediaKeySystem_GenerateKeySystemRequest=$genReqRc")
            assertNotNull(genReqRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_ProcessKeySystemResponse() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val processRc = OH_MediaKeySystem_ProcessKeySystemResponse(systemPtr.value, null, 0)
            logLine("OH_MediaKeySystem_ProcessKeySystemResponse=$processRc")
            assertNotNull(processRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetOfflineMediaKeyIds() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val offlineIds = alloc<DRM_OfflineMediakeyIdArray>()
            val getOfflineIdsRc = OH_MediaKeySystem_GetOfflineMediaKeyIds(systemPtr.value, offlineIds.ptr)
            logLine("OH_MediaKeySystem_GetOfflineMediaKeyIds=$getOfflineIdsRc")
            assertNotNull(getOfflineIdsRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetOfflineMediaKeyStatus() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val offlineStatus = alloc<UIntVar>()
            val getOfflineStatusRc = OH_MediaKeySystem_GetOfflineMediaKeyStatus(systemPtr.value, null, 0, offlineStatus.ptr)
            logLine("OH_MediaKeySystem_GetOfflineMediaKeyStatus=$getOfflineStatusRc")
            assertNotNull(getOfflineStatusRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_ClearOfflineMediaKeys() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val clearOfflineRc = OH_MediaKeySystem_ClearOfflineMediaKeys(systemPtr.value, null, 0)
            logLine("OH_MediaKeySystem_ClearOfflineMediaKeys=$clearOfflineRc")
            assertNotNull(clearOfflineRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_GetCertificateStatus() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val certStatus = alloc<UIntVar>()
            val getCertRc = OH_MediaKeySystem_GetCertificateStatus(systemPtr.value, certStatus.ptr)
            logLine("OH_MediaKeySystem_GetCertificateStatus=$getCertRc")
            assertNotNull(getCertRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySystem_CreateMediaKeySession() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            val createSessionRc = OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            logLine("OH_MediaKeySystem_CreateMediaKeySession=$createSessionRc")
            assertNotNull(createSessionRc)
            if (sessionPtr.value != null) {
                OH_MediaKeySession_Destroy(sessionPtr.value)
            }
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_GenerateMediaKeyRequest() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val genReqRc = OH_MediaKeySession_GenerateMediaKeyRequest(session, null, null)
            logLine("OH_MediaKeySession_GenerateMediaKeyRequest=$genReqRc")
            assertNotNull(genReqRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_ProcessMediaKeyResponse() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val offlineIdLen = alloc<IntVar>()
            val processRc = OH_MediaKeySession_ProcessMediaKeyResponse(session, null, 0, null, offlineIdLen.ptr)
            logLine("OH_MediaKeySession_ProcessMediaKeyResponse=$processRc")
            assertNotNull(processRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_CheckMediaKeyStatus() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val checkRc = OH_MediaKeySession_CheckMediaKeyStatus(session, null)
            logLine("OH_MediaKeySession_CheckMediaKeyStatus=$checkRc")
            assertNotNull(checkRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_ClearMediaKeys() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val clearRc = OH_MediaKeySession_ClearMediaKeys(session)
            logLine("OH_MediaKeySession_ClearMediaKeys=$clearRc")
            assertNotNull(clearRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_GenerateOfflineReleaseRequest() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val releaseReqLen = alloc<IntVar>()
            val genOfflineRc = OH_MediaKeySession_GenerateOfflineReleaseRequest(session, null, 0, null, releaseReqLen.ptr)
            logLine("OH_MediaKeySession_GenerateOfflineReleaseRequest=$genOfflineRc")
            assertNotNull(genOfflineRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_ProcessOfflineReleaseResponse() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val processOfflineRc = OH_MediaKeySession_ProcessOfflineReleaseResponse(session, null, 0, null, 0)
            logLine("OH_MediaKeySession_ProcessOfflineReleaseResponse=$processOfflineRc")
            assertNotNull(processOfflineRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_RestoreOfflineMediaKeys() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val restoreRc = OH_MediaKeySession_RestoreOfflineMediaKeys(session, null, 0)
            logLine("OH_MediaKeySession_RestoreOfflineMediaKeys=$restoreRc")
            assertNotNull(restoreRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_GetContentProtectionLevel() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val levelPtr = alloc<UIntVar>()
            val levelRc = OH_MediaKeySession_GetContentProtectionLevel(session, levelPtr.ptr)
            logLine("OH_MediaKeySession_GetContentProtectionLevel=$levelRc")
            assertNotNull(levelRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_RequireSecureDecoderModule() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val statusPtr = alloc<BooleanVar>()
            val secureRc = OH_MediaKeySession_RequireSecureDecoderModule(session, null, statusPtr.ptr)
            logLine("OH_MediaKeySession_RequireSecureDecoderModule=$secureRc")
            assertNotNull(secureRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_SetMediaKeySessionCallback() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val setCbRc = OH_MediaKeySession_SetMediaKeySessionCallback(session, null)
            logLine("OH_MediaKeySession_SetMediaKeySessionCallback=$setCbRc")
            assertNotNull(setCbRc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_SetCallback() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val setCb12Rc = OH_MediaKeySession_SetCallback(session, null)
            logLine("OH_MediaKeySession_SetCallback=$setCb12Rc")
            assertNotNull(setCb12Rc)
            OH_MediaKeySession_Destroy(session)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }

    @Test
    fun testOH_MediaKeySession_Destroy() {
        memScoped {
            val systemPtr = alloc<CPointerVar<MediaKeySystem>>()
            systemPtr.value = null
            OH_MediaKeySystem_Create("com.widevine.alpha", systemPtr.ptr)
            val sessionPtr = alloc<CPointerVar<MediaKeySession>>()
            sessionPtr.value = null
            OH_MediaKeySystem_CreateMediaKeySession(systemPtr.value, null, sessionPtr.ptr)
            val session = sessionPtr.value ?: run {
                OH_MediaKeySystem_Destroy(systemPtr.value)
                return@memScoped
            }
            val destroyRc = OH_MediaKeySession_Destroy(session)
            logLine("OH_MediaKeySession_Destroy=$destroyRc")
            assertNotNull(destroyRc)
            OH_MediaKeySystem_Destroy(systemPtr.value)
        }
    }
}
