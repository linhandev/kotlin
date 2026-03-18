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
import platform.RemoteCommunicationKit.RemoteCommunication.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class RemoteCommunicationTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Rcp_FormValueType() {
        assertEquals(Rcp_FormValueType.RCP_FORM_VALUE_TYPE_INT32.value.toInt(), 0)
        assertEquals(Rcp_FormValueType.RCP_FORM_VALUE_TYPE_INT64.value.toInt(), 1)
        assertEquals(Rcp_FormValueType.RCP_FORM_VALUE_TYPE_BOOL.value.toInt(), 2)
        assertEquals(Rcp_FormValueType.RCP_FORM_VALUE_TYPE_STRING.value.toInt(), 3)
        assertEquals(Rcp_FormValueType.RCP_FORM_VALUE_TYPE_DOUBLE.value.toInt(), 4)
        logLine("testEnum_Rcp_FormValueType passed")
    }

    @Test
    fun testEnum_Rcp_ContentOrPathOrCallbackType() {
        assertEquals(Rcp_ContentOrPathOrCallbackType.RCP_FILE_VALUE_TYPE_CONTENT.value.toInt(), 0)
        assertEquals(Rcp_ContentOrPathOrCallbackType.RCP_FILE_VALUE_TYPE_PATH.value.toInt(), 1)
        assertEquals(Rcp_ContentOrPathOrCallbackType.RCP_FILE_VALUE_TYPE_CALLBACK.value.toInt(), 2)
        logLine("testEnum_Rcp_ContentOrPathOrCallbackType passed")
    }

    @Test
    fun testEnum_Rcp_MultipartValueType() {
        assertEquals(Rcp_MultipartValueType.RCP_TYPE_FORM_FIELD_VALUE.value.toInt(), 0)
        assertEquals(Rcp_MultipartValueType.RCP_TYPE_FORM_FIELD_FILE_VALUE.value.toInt(), 1)
        logLine("testEnum_Rcp_MultipartValueType passed")
    }

    @Test
    fun testEnum_Rcp_ContentType() {
        assertEquals(Rcp_ContentType.RCP_CONTENT_TYPE_STRING.value.toInt(), 0)
        assertEquals(Rcp_ContentType.RCP_CONTENT_TYPE_FORM.value.toInt(), 1)
        assertEquals(Rcp_ContentType.RCP_CONTENT_TYPE_MULTIPARTFORM.value.toInt(), 2)
        assertEquals(Rcp_ContentType.RCP_CONTENT_TYPE_GETCALLBACK.value.toInt(), 3)
        logLine("testEnum_Rcp_ContentType passed")
    }

    @Test
    fun testEnum_Rcp_AuthenticationType() {
        assertEquals(Rcp_AuthenticationType.RCP_AUTHENTICATION_AUTO.value.toInt(), 0)
        assertEquals(Rcp_AuthenticationType.RCP_AUTHENTICATION_BASIC.value.toInt(), 1)
        assertEquals(Rcp_AuthenticationType.RCP_AUTHENTICATION_NTLM.value.toInt(), 2)
        assertEquals(Rcp_AuthenticationType.RCP_AUTHENTICATION_DIGEST.value.toInt(), 3)
        logLine("testEnum_Rcp_AuthenticationType passed")
    }

    @Test
    fun testEnum_Rcp_StatusCode() {
        assertEquals(RCP_NONE.toInt(), 0)
        assertEquals(RCP_OK.toInt(), 200)
        assertEquals(RCP_BAD_REQUEST.toInt(), 400)
        assertEquals(RCP_INTERNAL_ERROR.toInt(), 500)
        logLine("testEnum_Rcp_StatusCode passed")
    }

    @Test
    fun testEnum_Rcp_ExclusionsValueType() {
        assertEquals(Rcp_ExclusionsValueType.RCP_EXCLUSION_USE_URL_ARRAY.value.toInt(), 0)
        assertEquals(Rcp_ExclusionsValueType.RCP_EXCLUSION_USE_CALLBACK.value.toInt(), 1)
        logLine("testEnum_Rcp_ExclusionsValueType passed")
    }

    @Test
    fun testEnum_Rcp_CertType() {
        assertEquals(Rcp_CertType.RCP_CERT_PEM.value.toInt(), 0)
        assertEquals(Rcp_CertType.RCP_CERT_DER.value.toInt(), 1)
        assertEquals(Rcp_CertType.RCP_CERT_P12.value.toInt(), 2)
        logLine("testEnum_Rcp_CertType passed")
    }

    @Test
    fun testEnum_Rcp_RemoteValidationType() {
        assertEquals(Rcp_RemoteValidationType.RCP_REMOTE_VALIDATION_SYSTEM.value.toInt(), 0)
        assertEquals(Rcp_RemoteValidationType.RCP_REMOTE_VALIDATION_SKIP.value.toInt(), 1)
        assertEquals(Rcp_RemoteValidationType.RCP_REMOTE_VALIDATION_CERTIFICATE_AUTHORITY.value.toInt(), 2)
        logLine("testEnum_Rcp_RemoteValidationType passed")
    }

    @Test
    fun testEnum_Rcp_ProxyTunnelMode() {
        assertEquals(Rcp_ProxyTunnelMode.RCP_PROXY_TUNNEL_AUTO.value.toInt(), 0)
        assertEquals(Rcp_ProxyTunnelMode.RCP_PROXY_TUNNEL_ALWAYS.value.toInt(), 1)
        logLine("testEnum_Rcp_ProxyTunnelMode passed")
    }

    @Test
    fun testEnum_Rcp_DnsRuleType() {
        assertEquals(Rcp_DnsRuleType.RCP_DNS_RULE_DNS_SERVERS.value.toInt(), 0)
        assertEquals(Rcp_DnsRuleType.RCP_DNS_RULE_STATIC.value.toInt(), 1)
        assertEquals(Rcp_DnsRuleType.RCP_DNS_RULE_DYNAMIC.value.toInt(), 2)
        logLine("testEnum_Rcp_DnsRuleType passed")
    }

    @Test
    fun testEnum_Rcp_PathPreference() {
        assertEquals(Rcp_PathPreference.RCP_PATH_PREFERENCE_AUTO.value.toInt(), 0)
        assertEquals(Rcp_PathPreference.RCP_PATH_PREFERENCE_WIFI.value.toInt(), 1)
        assertEquals(Rcp_PathPreference.RCP_PATH_PREFERENCE_CELLULAR.value.toInt(), 2)
        logLine("testEnum_Rcp_PathPreference passed")
    }

    @Test
    fun testEnum_Rcp_ProxyType() {
        assertEquals(Rcp_ProxyType.RCP_PROXY_SYSTEM.value.toInt(), 0)
        assertEquals(Rcp_ProxyType.RCP_PROXY_CUSTOM.value.toInt(), 1)
        assertEquals(Rcp_ProxyType.RCP_PROXY_NO_PROXY.value.toInt(), 2)
        logLine("testEnum_Rcp_ProxyType passed")
    }

    @Test
    fun testEnum_Rcp_DebugEvent() {
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_TEXT.value.toInt(), 0)
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_HEADER_IN.value.toInt(), 1)
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_HEADER_OUT.value.toInt(), 2)
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_DATA_IN.value.toInt(), 3)
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_DATA_OUT.value.toInt(), 4)
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_SSL_DATA_IN.value.toInt(), 5)
        assertEquals(Rcp_DebugEvent.RCP_DEBUG_EVENT_SSL_DATA_OUT.value.toInt(), 6)
        logLine("testEnum_Rcp_DebugEvent passed")
    }

    @Test
    fun testEnum_Rcp_SessionType() {
        assertEquals(RCP_SESSION_TYPE_HTTP.toInt(), 0)
        assertEquals(RCP_SESSION_TYPE_MAX.toInt(), 100)
        logLine("testEnum_Rcp_SessionType passed")
    }

    @Test
    fun testHMS_Rcp_CreateForm() {
        val form = HMS_Rcp_CreateForm()
        assertNotNull(form)
        logLine("HMS_Rcp_CreateForm result=$form")
        HMS_Rcp_DestroyForm(form)
    }

    @Test
    fun testHMS_Rcp_DestroyForm() {
        HMS_Rcp_DestroyForm(null)
        logLine("HMS_Rcp_DestroyForm(null) done")
    }

    @Test
    fun testHMS_Rcp_SetFormValue() {
        val form = HMS_Rcp_CreateForm()
        assertNotNull(form)
        val ret = HMS_Rcp_SetFormValue(form, "k", null)
        assertNotNull(ret)
        HMS_Rcp_DestroyForm(form)
        logLine("HMS_Rcp_SetFormValue done")
    }

    @Test
    fun testHMS_Rcp_GetFormValue() {
        val form = HMS_Rcp_CreateForm()
        assertNotNull(form)
        val value = HMS_Rcp_GetFormValue(form, "k")
        logLine("HMS_Rcp_GetFormValue result=$value")
        HMS_Rcp_DestroyForm(form)
    }

    @Test
    fun testHMS_Rcp_CreateMultipartForm() {
        val form = HMS_Rcp_CreateMultipartForm()
        assertNotNull(form)
        HMS_Rcp_DestroyMultipartForm(form)
        logLine("HMS_Rcp_CreateMultipartForm done")
    }

    @Test
    fun testHMS_Rcp_DestroyMultipartForm() {
        HMS_Rcp_DestroyMultipartForm(null)
        logLine("HMS_Rcp_DestroyMultipartForm(null) done")
    }

    @Test
    fun testHMS_Rcp_SetMultipartFormValue() {
        val form = HMS_Rcp_CreateMultipartForm()
        assertNotNull(form)
        val ret = HMS_Rcp_SetMultipartFormValue(form, "k", null)
        assertNotNull(ret)
        HMS_Rcp_DestroyMultipartForm(form)
        logLine("HMS_Rcp_SetMultipartFormValue done")
    }

    @Test
    fun testHMS_Rcp_GetMultipartFormValue() {
        val form = HMS_Rcp_CreateMultipartForm()
        assertNotNull(form)
        val value = HMS_Rcp_GetMultipartFormValue(form, "k")
        logLine("HMS_Rcp_GetMultipartFormValue result=$value")
        HMS_Rcp_DestroyMultipartForm(form)
    }

    @Test
    fun testHMS_Rcp_CreateHeaders() {
        val headers = HMS_Rcp_CreateHeaders()
        assertNotNull(headers)
        HMS_Rcp_DestroyHeaders(headers)
        logLine("HMS_Rcp_CreateHeaders done")
    }

    @Test
    fun testHMS_Rcp_DestroyHeaders() {
        HMS_Rcp_DestroyHeaders(null)
        logLine("HMS_Rcp_DestroyHeaders(null) done")
    }

    @Test
    fun testHMS_Rcp_SetHeaderValue() {
        val headers = HMS_Rcp_CreateHeaders()
        assertNotNull(headers)
        val ret = HMS_Rcp_SetHeaderValue(headers, "Name", "Value")
        assertNotNull(ret)
        HMS_Rcp_DestroyHeaders(headers)
        logLine("HMS_Rcp_SetHeaderValue done")
    }

    @Test
    fun testHMS_Rcp_GetHeaderValue() {
        val headers = HMS_Rcp_CreateHeaders()
        assertNotNull(headers)
        val value = HMS_Rcp_GetHeaderValue(headers, "Name")
        logLine("HMS_Rcp_GetHeaderValue result=$value")
        HMS_Rcp_DestroyHeaders(headers)
    }

    @Test
    fun testHMS_Rcp_GetHeaderEntries() {
        val headers = HMS_Rcp_CreateHeaders()
        assertNotNull(headers)
        val entries = HMS_Rcp_GetHeaderEntries(headers)
        logLine("HMS_Rcp_GetHeaderEntries result=$entries")
        HMS_Rcp_DestroyHeaders(headers)
    }

    @Test
    fun testHMS_Rcp_DestroyHeaderEntries() {
        HMS_Rcp_DestroyHeaderEntries(null)
        logLine("HMS_Rcp_DestroyHeaderEntries(null) done")
    }

    @Test
    fun testHMS_Rcp_CreateRequest() {
        val request = HMS_Rcp_CreateRequest("https://example.com")
        assertNotNull(request)
        HMS_Rcp_DestroyRequest(request)
        logLine("HMS_Rcp_CreateRequest done")
    }

    @Test
    fun testHMS_Rcp_DestroyRequest() {
        HMS_Rcp_DestroyRequest(null)
        logLine("HMS_Rcp_DestroyRequest(null) done")
    }

    @Test
    fun testHMS_Rcp_CreateRequestCookies() {
        val cookies = HMS_Rcp_CreateRequestCookies()
        assertNotNull(cookies)
        HMS_Rcp_DestroyRequestCookies(cookies)
        logLine("HMS_Rcp_CreateRequestCookies done")
    }

    @Test
    fun testHMS_Rcp_DestroyRequestCookies() {
        HMS_Rcp_DestroyRequestCookies(null)
        logLine("HMS_Rcp_DestroyRequestCookies(null) done")
    }

    @Test
    fun testHMS_Rcp_SetRequestCookieValue() {
        val cookies = HMS_Rcp_CreateRequestCookies()
        assertNotNull(cookies)
        val ret = HMS_Rcp_SetRequestCookieValue(cookies, "name", "value")
        assertNotNull(ret)
        HMS_Rcp_DestroyRequestCookies(cookies)
        logLine("HMS_Rcp_SetRequestCookieValue done")
    }

    @Test
    fun testHMS_Rcp_GetRequestCookieValue() {
        val cookies = HMS_Rcp_CreateRequestCookies()
        assertNotNull(cookies)
        val value = HMS_Rcp_GetRequestCookieValue(cookies, "name")
        logLine("HMS_Rcp_GetRequestCookieValue result=$value")
        HMS_Rcp_DestroyRequestCookies(cookies)
    }

    @Test
    fun testHMS_Rcp_GetRequestCookieEntries() {
        val cookies = HMS_Rcp_CreateRequestCookies()
        assertNotNull(cookies)
        val entries = HMS_Rcp_GetRequestCookieEntries(cookies)
        logLine("HMS_Rcp_GetRequestCookieEntries result=$entries")
        HMS_Rcp_DestroyRequestCookies(cookies)
    }

    @Test
    fun testHMS_Rcp_DestroyRequestCookieEntries() {
        HMS_Rcp_DestroyRequestCookieEntries(null)
        logLine("HMS_Rcp_DestroyRequestCookieEntries(null) done")
    }

    @Test
    fun testHMS_Rcp_GetResponseCookieAttrValue() {
        val value = HMS_Rcp_GetResponseCookieAttrValue(null, "name")
        logLine("HMS_Rcp_GetResponseCookieAttrValue result=$value")
    }

    @Test
    fun testHMS_Rcp_GetResponseCookieAttrEntries() {
        val entries = HMS_Rcp_GetResponseCookieAttrEntries(null)
        logLine("HMS_Rcp_GetResponseCookieAttrEntries result=$entries")
    }

    @Test
    fun testHMS_Rcp_DestroyResponseCookieAttrEntries() {
        HMS_Rcp_DestroyResponseCookieAttrEntries(null)
        logLine("HMS_Rcp_DestroyResponseCookieAttrEntries(null) done")
    }

    @Test
    fun testHMS_Rcp_CreateSession() {
        memScoped {
            val config = alloc<Rcp_SessionConfiguration>().apply {
                type = RCP_SESSION_TYPE_HTTP
                interceptors.interceptors = null
                interceptors.size = 0
                syncInterceptors.interceptors = null
                syncInterceptors.size = 0
                baseUrl = null
                headers = null
                cookies = null
                requestConfiguration = null
                connectionConfiguration.maxConnectionsPerHost = 0L
                connectionConfiguration.maxTotalConnections = 0L
                connectionConfiguration.maxCacheConnections = 0L
            }
            val errCode = alloc<UIntVar>()
            val session = HMS_Rcp_CreateSession(config.ptr, errCode.ptr)
            logLine("HMS_Rcp_CreateSession errCode=${errCode.value} session=$session")
        }
    }

    @Test
    fun testHMS_Rcp_GetSessionId() {
        val id = HMS_Rcp_GetSessionId(null)
        logLine("HMS_Rcp_GetSessionId(null) result=$id")
    }

    @Test
    fun testHMS_Rcp_GetSessionConfiguration() {
        val config = HMS_Rcp_GetSessionConfiguration(null)
        logLine("HMS_Rcp_GetSessionConfiguration(null) result=$config")
    }

    @Test
    fun testHMS_Rcp_FetchSync() {
        memScoped {
            val errCode = alloc<UIntVar>()
            val response = HMS_Rcp_FetchSync(null, null, errCode.ptr)
            logLine("HMS_Rcp_FetchSync errCode=${errCode.value} response=$response")
        }
    }

    @Test
    fun testHMS_Rcp_CancelRequest() {
        val ret = HMS_Rcp_CancelRequest(null, null)
        assertNotNull(ret)
        logLine("HMS_Rcp_CancelRequest ret=$ret")
    }

    @Test
    fun testHMS_Rcp_CancelSession() {
        val ret = HMS_Rcp_CancelSession(null)
        assertNotNull(ret)
        logLine("HMS_Rcp_CancelSession ret=$ret")
    }

    @Test
    fun testHMS_Rcp_CloseSession() {
        memScoped {
            val session = alloc<CPointerVar<Rcp_Session>>()
            session.value = null
            val ret = HMS_Rcp_CloseSession(session.ptr)
            assertNotNull(ret)
            logLine("HMS_Rcp_CloseSession ret=$ret")
        }
    }

    @Test
    fun testHMS_Rcp_CallNextRequestHandler() {
        val ret = HMS_Rcp_CallNextRequestHandler(null, null, null)
        assertNotNull(ret)
        logLine("HMS_Rcp_CallNextRequestHandler ret=$ret")
    }

    @Test
    fun testHMS_Rcp_CallNextSyncRequestHandler() {
        memScoped {
            val errCode = alloc<UIntVar>()
            val response = HMS_Rcp_CallNextSyncRequestHandler(null, null, errCode.ptr)
            logLine("HMS_Rcp_CallNextSyncRequestHandler errCode=${errCode.value} response=$response")
        }
    }

    @Test
    fun testHMS_Rcp_Fetch() {
        val ret = HMS_Rcp_Fetch(null, null, null)
        assertNotNull(ret)
        logLine("HMS_Rcp_Fetch ret=$ret")
    }

    @Test
    fun testHMS_Rcp_SetRequestOnBinaryDataRecvCallback() {
        val request = HMS_Rcp_CreateRequest("https://example.com")
        assertNotNull(request)
        val callback = cValue<Rcp_OnBinaryReceiveCallback> { callback = null; usrObject = null }
        val ret = HMS_Rcp_SetRequestOnBinaryDataRecvCallback(request, callback)
        assertNotNull(ret)
        HMS_Rcp_DestroyRequest(request)
        logLine("HMS_Rcp_SetRequestOnBinaryDataRecvCallback ret=$ret")
    }

    @Test
    fun testHMS_Rcp_SetRequestOnStatusCodeReceiveCallback() {
        val request = HMS_Rcp_CreateRequest("https://example.com")
        assertNotNull(request)
        val callback = cValue<Rcp_OnStatusCodeReceiveCallback> { callback = null; usrObject = null }
        val ret = try { HMS_Rcp_SetRequestOnStatusCodeReceiveCallback(request, callback) } catch (e: Throwable) { logLine("HMS_Rcp_SetRequestOnStatusCodeReceiveCallback (API 21) exception: $e"); 401u }
        assertNotNull(ret)
        HMS_Rcp_DestroyRequest(request)
        logLine("HMS_Rcp_SetRequestOnStatusCodeReceiveCallback ret=$ret")
    }
}
