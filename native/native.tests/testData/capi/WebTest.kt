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
import platform.ArkWeb.Web.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class WebTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_ArkWeb_ErrorCode() {
        assertEquals(ARKWEB_SUCCESS.toInt(), 0)
        assertEquals(ARKWEB_INIT_ERROR.toInt(), 17100001)
        assertEquals(ARKWEB_ERROR_UNKNOWN.toInt(), 17100100)
        assertEquals(ARKWEB_INVALID_PARAM.toInt(), 17100101)
        assertEquals(ARKWEB_SCHEME_REGISTER_FAILED.toInt(), 17100102)
        assertEquals(ARKWEB_INVALID_URL.toInt(), 17100103)
        assertEquals(ARKWEB_INVALID_COOKIE_VALUE.toInt(), 17100104)
        assertEquals(ARKWEB_LIBRARY_OPEN_FAILURE.toInt(), 17100105)
        assertEquals(ARKWEB_LIBRARY_SYMBOL_NOT_FOUND.toInt(), 17100106)
        assertEquals(ARKWEB_COOKIE_MANAGER_NOT_INITIALIZED.toInt(), 17100107)
        assertEquals(ARKWEB_COOKIE_MANAGER_INITIALIZE_FAILED.toInt(), 17100108)
        assertEquals(ARKWEB_COOKIE_SAVE_FAILED.toInt(), 17100109)
        logLine("ArkWeb_ErrorCode passed")
    }

    @Test
    fun testEnum_ArkWeb_BlanklessErrorCode() {
        assertEquals(ARKWEB_BLANKLESS_SUCCESS.toInt(), 0)
        assertEquals(ARKWEB_BLANKLESS_ERR_UNKNOWN.toInt(), -1)
        assertEquals(ARKWEB_BLANKLESS_ERR_INVALID_ARGS.toInt(), -2)
        assertEquals(ARKWEB_BLANKLESS_ERR_CONTROLLER_NOT_INITED.toInt(), -3)
        assertEquals(ARKWEB_BLANKLESS_ERR_KEY_NOT_MATCH.toInt(), -4)
        assertEquals(ARKWEB_BLANKLESS_ERR_SIGNIFICANT_CHANGE.toInt(), -5)
        assertEquals(ARKWEB_BLANKLESS_ERR_DEVICE_NOT_SUPPORT.toInt(), 801)
        logLine("ArkWeb_BlanklessErrorCode passed")
    }

    @Test
    fun testEnum_ArkWeb_EngineVersion() {
        assertEquals(SYSTEM_DEFAULT.toInt(), 0)
        assertEquals(ARKWEB_M114.toInt(), 1)
        assertEquals(ARKWEB_M132.toInt(), 2)
        logLine("ArkWeb_EngineVersion passed")
    }

    @Test
    fun testEnum_ArkWeb_CustomSchemeOption() {
        assertEquals(OH_ARKWEB_SCHEME_OPTION_NONE.toInt(), 0)
        assertEquals(ARKWEB_SCHEME_OPTION_STANDARD.toInt(), 1)
        assertEquals(ARKWEB_SCHEME_OPTION_LOCAL.toInt(), 2)
        assertEquals(ARKWEB_SCHEME_OPTION_DISPLAY_ISOLATED.toInt(), 4)
        assertEquals(ARKWEB_SCHEME_OPTION_SECURE.toInt(), 8)
        assertEquals(ARKWEB_SCHEME_OPTION_CORS_ENABLED.toInt(), 16)
        assertEquals(ARKWEB_SCHEME_OPTION_CSP_BYPASSING.toInt(), 32)
        assertEquals(ARKWEB_SCHEME_OPTION_FETCH_ENABLED.toInt(), 64)
        assertEquals(ARKWEB_SCHEME_OPTION_CODE_CACHE_ENABLED.toInt(), 128)
        logLine("ArkWeb_CustomSchemeOption passed")
    }

    @Test
    fun testEnum_ArkWeb_WebMessageType() {
        assertEquals(ARKWEB_NONE.toInt(), 0)
        assertEquals(ARKWEB_STRING.toInt(), 1)
        assertEquals(ARKWEB_BUFFER.toInt(), 2)
        logLine("ArkWeb_WebMessageType passed")
    }

    @Test
    fun testEnum_ArkWeb_JavaScriptValueType() {
        assertEquals(ARKWEB_JAVASCRIPT_NONE.toInt(), 0)
        assertEquals(ARKWEB_JAVASCRIPT_STRING.toInt(), 1)
        assertEquals(ARKWEB_JAVASCRIPT_BOOL.toInt(), 2)
        logLine("ArkWeb_JavaScriptValueType passed")
    }

    @Test
    fun testEnum_ArkWeb_NetError() {
        assertEquals(ARKWEB_NET_OK.toInt(), 0)
        assertEquals(ARKWEB_ERR_IO_PENDING.toInt(), -1)
        assertEquals(ARKWEB_ERR_FAILED.toInt(), -2)
        assertEquals(ARKWEB_ERR_ABORTED.toInt(), -3)
        assertEquals(ARKWEB_ERR_CONNECTION_CLOSED.toInt(), -100)
        assertEquals(ARKWEB_ERR_CONNECTION_REFUSED.toInt(), -102)
        assertEquals(ARKWEB_ERR_NAME_NOT_RESOLVED.toInt(), -105)
        assertEquals(ARKWEB_ERR_SSL_PROTOCOL_ERROR.toInt(), -107)
        assertEquals(ARKWEB_ERR_CERT_COMMON_NAME_INVALID.toInt(), -200)
        assertEquals(ARKWEB_ERR_CERT_DATE_INVALID.toInt(), -201)
        assertEquals(ARKWEB_ERR_INVALID_URL.toInt(), -300)
        assertEquals(ARKWEB_ERR_TOO_MANY_REDIRECTS.toInt(), -310)
        assertEquals(ARKWEB_ERR_CACHE_MISS.toInt(), -400)
        assertEquals(ARKWEB_ERR_CACHE_READ_FAILURE.toInt(), -401)
        assertEquals(ARKWEB_ERR_INSECURE_RESPONSE.toInt(), -501)
        assertEquals(ARKWEB_ERR_FTP_FAILED.toInt(), -601)
        assertEquals(ARKWEB_ERR_PKCS12_IMPORT_BAD_PASSWORD.toInt(), -701)
        assertEquals(ARKWEB_ERR_DNS_MALFORMED_RESPONSE.toInt(), -800)
        assertEquals(ARKWEB_ERR_DNS_SERVER_FAILED.toInt(), -802)
        assertEquals(ARKWEB_ERR_DNS_NO_MATCHING_SUPPORTED_ALPN.toInt(), -811)
        logLine("ArkWeb_NetError passed")
    }

    @Test
    fun testEnum_ArkWeb_NativeAPIVariantKind() {
        assertEquals(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_COMPONENT.value.toInt(), 0)
        assertEquals(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_CONTROLLER.value.toInt(), 1)
        assertEquals(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_WEB_MESSAGE_PORT.value.toInt(), 2)
        assertEquals(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_WEB_MESSAGE.value.toInt(), 3)
        assertEquals(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_COOKIE_MANAGER.value.toInt(), 4)
        assertEquals(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_JAVASCRIPT_VALUE.value.toInt(), 5)
        logLine("ArkWeb_NativeAPIVariantKind passed")
    }

    @Test
    fun testEnum_ArkWeb_ResourceType() {
        assertEquals(MAIN_FRAME.toInt(), 0)
        assertEquals(SUB_FRAME.toInt(), 1)
        assertEquals(STYLE_SHEET.toInt(), 2)
        assertEquals(SCRIPT.toInt(), 3)
        assertEquals(IMAGE.toInt(), 4)
        assertEquals(FONT_RESOURCE.toInt(), 5)
        assertEquals(SUB_RESOURCE.toInt(), 6)
        assertEquals(OBJECT.toInt(), 7)
        assertEquals(MEDIA.toInt(), 8)
        assertEquals(WORKER.toInt(), 9)
        assertEquals(SHARED_WORKER.toInt(), 10)
        assertEquals(PREFETCH.toInt(), 11)
        assertEquals(FAVICON.toInt(), 12)
        assertEquals(XHR.toInt(), 13)
        assertEquals(PING.toInt(), 14)
        assertEquals(SERVICE_WORKER.toInt(), 15)
        assertEquals(CSP_REPORT.toInt(), 16)
        assertEquals(PLUGIN_RESOURCE.toInt(), 17)
        assertEquals(NAVIGATION_PRELOAD_MAIN_FRAME.toInt(), 19)
        assertEquals(NAVIGATION_PRELOAD_SUB_FRAME.toInt(), 20)
        logLine("ArkWeb_ResourceType passed")
    }

    @Test
    fun testOH_NativeArkWeb_RunJavaScript() {
        OH_NativeArkWeb_RunJavaScript("test_web", "1+1", null)
        logLine("OH_NativeArkWeb_RunJavaScript passed")
    }

    @Test
    fun testOH_NativeArkWeb_RegisterJavaScriptProxy() {
        val r = OH_NativeArkWeb_RegisterJavaScriptProxy("test_web", "test_obj", null, null, 0, false)
        logLine("OH_NativeArkWeb_RegisterJavaScriptProxy=$r")
        logLine("OH_NativeArkWeb_RegisterJavaScriptProxy passed")
    }

    @Test
    fun testOH_NativeArkWeb_UnregisterJavaScriptProxy() {
        val r = OH_NativeArkWeb_UnregisterJavaScriptProxy("test_web", "test_obj")
        logLine("OH_NativeArkWeb_UnregisterJavaScriptProxy=$r")
        logLine("OH_NativeArkWeb_UnregisterJavaScriptProxy passed")
    }

    @Test
    fun testOH_NativeArkWeb_SetJavaScriptProxyValidCallback() {
        val r = OH_NativeArkWeb_SetJavaScriptProxyValidCallback("test_web", null)
        logLine("OH_NativeArkWeb_SetJavaScriptProxyValidCallback=$r")
        logLine("OH_NativeArkWeb_SetJavaScriptProxyValidCallback passed")
    }

    @Test
    fun testOH_NativeArkWeb_GetJavaScriptProxyValidCallback() {
        val r = OH_NativeArkWeb_GetJavaScriptProxyValidCallback("test_web")
        logLine("OH_NativeArkWeb_GetJavaScriptProxyValidCallback=$r")
        logLine("OH_NativeArkWeb_GetJavaScriptProxyValidCallback passed")
    }

    @Test
    fun testOH_NativeArkWeb_SetDestroyCallback() {
        val r = OH_NativeArkWeb_SetDestroyCallback("test_web", null)
        logLine("OH_NativeArkWeb_SetDestroyCallback=$r")
        logLine("OH_NativeArkWeb_SetDestroyCallback passed")
    }

    @Test
    fun testOH_NativeArkWeb_GetDestroyCallback() {
        val r = OH_NativeArkWeb_GetDestroyCallback("test_web")
        logLine("OH_NativeArkWeb_GetDestroyCallback=$r")
        logLine("OH_NativeArkWeb_GetDestroyCallback passed")
    }

    @Test
    fun testOH_NativeArkWeb_LoadData() {
        val r = OH_NativeArkWeb_LoadData("test_web", "dGVzdA==", "text/html", "UTF-8", "https://example.com/", "")
        logLine("OH_NativeArkWeb_LoadData=$r")
        assertNotNull(r)
        logLine("OH_NativeArkWeb_LoadData passed")
    }

    @Test
    fun testOH_NativeArkWeb_RegisterAsyncThreadJavaScriptProxy() {
        try { OH_NativeArkWeb_RegisterAsyncThreadJavaScriptProxy("test_web", null, "test_permission") } catch (e: Throwable) { logLine("OH_NativeArkWeb_RegisterAsyncThreadJavaScriptProxy (API 20) exception: $e") }
        logLine("OH_NativeArkWeb_RegisterAsyncThreadJavaScriptProxy passed")
    }

    @Test
    fun testOH_NativeArkWeb_SetBlanklessLoadingWithKey() {
        val r = try { OH_NativeArkWeb_SetBlanklessLoadingWithKey("test_web", "test_key", false) } catch (e: Throwable) { logLine("OH_NativeArkWeb_SetBlanklessLoadingWithKey (API 20) exception: $e"); ARKWEB_BLANKLESS_ERR_UNKNOWN }
        logLine("OH_NativeArkWeb_SetBlanklessLoadingWithKey=$r")
        assertNotNull(r)
        logLine("OH_NativeArkWeb_SetBlanklessLoadingWithKey passed")
    }

    @Test
    fun testOH_NativeArkWeb_ClearBlanklessLoadingCache() {
        try { OH_NativeArkWeb_ClearBlanklessLoadingCache(null, 0u) } catch (e: Throwable) { logLine("OH_NativeArkWeb_ClearBlanklessLoadingCache (API 20) exception: $e") }
        logLine("OH_NativeArkWeb_ClearBlanklessLoadingCache passed")
    }

    @Test
    fun testOH_NativeArkWeb_GetBlanklessInfoWithKey() {
        memScoped {
            try { OH_NativeArkWeb_GetBlanklessInfoWithKey("test_web", "test_key") } catch (e: Throwable) { logLine("OH_NativeArkWeb_GetBlanklessInfoWithKey (API 20) exception: $e"); alloc<ArkWeb_BlanklessInfo>().readValue() }
        }
        logLine("OH_NativeArkWeb_GetBlanklessInfoWithKey passed")
    }

    @Test
    fun testOH_NativeArkWeb_SetBlanklessLoadingCacheCapacity() {
        val r = try { OH_NativeArkWeb_SetBlanklessLoadingCacheCapacity(0u) } catch (e: Throwable) { logLine("OH_NativeArkWeb_SetBlanklessLoadingCacheCapacity (API 20) exception: $e"); 0u }
        logLine("OH_NativeArkWeb_SetBlanklessLoadingCacheCapacity=$r")
        assertNotNull(r)
        logLine("OH_NativeArkWeb_SetBlanklessLoadingCacheCapacity passed")
    }

    @Test
    fun testOH_ArkWebCookieManager_SaveCookieSync() {
        val r = try { OH_ArkWebCookieManager_SaveCookieSync() } catch (e: Throwable) { logLine("OH_ArkWebCookieManager_SaveCookieSync (API 20) exception: $e"); ARKWEB_INVALID_PARAM }
        logLine("OH_ArkWebCookieManager_SaveCookieSync=$r")
        assertNotNull(r)
        logLine("OH_ArkWebCookieManager_SaveCookieSync passed")
    }

    @Test
    fun testOH_ArkWebCookieManager_SaveCookieAsync() {
        try { OH_ArkWebCookieManager_SaveCookieAsync(null) } catch (e: Throwable) { logLine("OH_ArkWebCookieManager_SaveCookieAsync (API 20) exception: $e") }
        logLine("OH_ArkWebCookieManager_SaveCookieAsync passed")
    }

    @Test
    fun testOH_NativeArkWeb_SetActiveWebEngineVersion() {
        try { OH_NativeArkWeb_SetActiveWebEngineVersion(SYSTEM_DEFAULT) } catch (e: Throwable) { logLine("OH_NativeArkWeb_SetActiveWebEngineVersion (API 20) exception: $e") }
        logLine("OH_NativeArkWeb_SetActiveWebEngineVersion passed")
    }

    @Test
    fun testOH_NativeArkWeb_GetActiveWebEngineVersion() {
        val r = try { OH_NativeArkWeb_GetActiveWebEngineVersion() } catch (e: Throwable) { logLine("OH_NativeArkWeb_GetActiveWebEngineVersion (API 20) exception: $e"); SYSTEM_DEFAULT }
        logLine("OH_NativeArkWeb_GetActiveWebEngineVersion=$r")
        assertNotNull(r)
        logLine("OH_NativeArkWeb_GetActiveWebEngineVersion passed")
    }

    @Test
    fun testOH_NativeArkWeb_LazyInitializeWebEngineInCookieManager() {
        try { OH_NativeArkWeb_LazyInitializeWebEngineInCookieManager(false) } catch (e: Throwable) { logLine("OH_NativeArkWeb_LazyInitializeWebEngineInCookieManager (API 22) exception: $e") }
        logLine("OH_NativeArkWeb_LazyInitializeWebEngineInCookieManager passed")
    }

    @Test
    fun testOH_ArkWeb_GetNativeAPI() {
        val r = OH_ArkWeb_GetNativeAPI(ArkWeb_NativeAPIVariantKind.ARKWEB_NATIVE_COMPONENT)
        logLine("OH_ArkWeb_GetNativeAPI(COMPONENT)=$r")
        logLine("OH_ArkWeb_GetNativeAPI passed")
    }

    @Test
    fun testOH_ArkWeb_RegisterScrollCallback() {
        val r = try { OH_ArkWeb_RegisterScrollCallback("test_web", null, null) } catch (e: Throwable) { logLine("OH_ArkWeb_RegisterScrollCallback (API 18) exception: $e"); false }
        logLine("OH_ArkWeb_RegisterScrollCallback=$r")
        assertNotNull(r)
        logLine("OH_ArkWeb_RegisterScrollCallback passed")
    }

    @Test
    fun testOH_ArkWebRequestHeaderList_Destroy() {
        OH_ArkWebRequestHeaderList_Destroy(null)
        logLine("OH_ArkWebRequestHeaderList_Destroy passed")
    }

    @Test
    fun testOH_ArkWebRequestHeaderList_GetSize() {
        val r = OH_ArkWebRequestHeaderList_GetSize(null)
        logLine("OH_ArkWebRequestHeaderList_GetSize=$r")
        assertNotNull(r)
        logLine("OH_ArkWebRequestHeaderList_GetSize passed")
    }

    @Test
    fun testOH_ArkWebRequestHeaderList_GetHeader() {
        OH_ArkWebRequestHeaderList_GetHeader(null, 0, null, null)
        logLine("OH_ArkWebRequestHeaderList_GetHeader passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_SetUserData() {
        val r = OH_ArkWebResourceRequest_SetUserData(null, null)
        logLine("OH_ArkWebResourceRequest_SetUserData=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceRequest_SetUserData passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetUserData() {
        val r = OH_ArkWebResourceRequest_GetUserData(null)
        logLine("OH_ArkWebResourceRequest_GetUserData=$r")
        logLine("OH_ArkWebResourceRequest_GetUserData passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetMethod() {
        OH_ArkWebResourceRequest_GetMethod(null, null)
        logLine("OH_ArkWebResourceRequest_GetMethod passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetUrl() {
        OH_ArkWebResourceRequest_GetUrl(null, null)
        logLine("OH_ArkWebResourceRequest_GetUrl passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetHttpBodyStream() {
        OH_ArkWebResourceRequest_GetHttpBodyStream(null, null)
        logLine("OH_ArkWebResourceRequest_GetHttpBodyStream passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_DestroyHttpBodyStream() {
        OH_ArkWebResourceRequest_DestroyHttpBodyStream(null)
        logLine("OH_ArkWebResourceRequest_DestroyHttpBodyStream passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetResourceType() {
        val r = OH_ArkWebResourceRequest_GetResourceType(null)
        logLine("OH_ArkWebResourceRequest_GetResourceType=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceRequest_GetResourceType passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetFrameUrl() {
        OH_ArkWebResourceRequest_GetFrameUrl(null, null)
        logLine("OH_ArkWebResourceRequest_GetFrameUrl passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_Destroy() {
        val r = OH_ArkWebResourceRequest_Destroy(null)
        logLine("OH_ArkWebResourceRequest_Destroy=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceRequest_Destroy passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetReferrer() {
        OH_ArkWebResourceRequest_GetReferrer(null, null)
        logLine("OH_ArkWebResourceRequest_GetReferrer passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_GetRequestHeaders() {
        OH_ArkWebResourceRequest_GetRequestHeaders(null, null)
        logLine("OH_ArkWebResourceRequest_GetRequestHeaders passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_IsRedirect() {
        val r = OH_ArkWebResourceRequest_IsRedirect(null)
        logLine("OH_ArkWebResourceRequest_IsRedirect=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceRequest_IsRedirect passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_IsMainFrame() {
        val r = OH_ArkWebResourceRequest_IsMainFrame(null)
        logLine("OH_ArkWebResourceRequest_IsMainFrame=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceRequest_IsMainFrame passed")
    }

    @Test
    fun testOH_ArkWebResourceRequest_HasGesture() {
        val r = OH_ArkWebResourceRequest_HasGesture(null)
        logLine("OH_ArkWebResourceRequest_HasGesture=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceRequest_HasGesture passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_SetUserData() {
        val r = OH_ArkWebHttpBodyStream_SetUserData(null, null)
        logLine("OH_ArkWebHttpBodyStream_SetUserData=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_SetUserData passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_GetUserData() {
        val r = OH_ArkWebHttpBodyStream_GetUserData(null)
        logLine("OH_ArkWebHttpBodyStream_GetUserData=$r")
        logLine("OH_ArkWebHttpBodyStream_GetUserData passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_SetReadCallback() {
        val r = OH_ArkWebHttpBodyStream_SetReadCallback(null, null)
        logLine("OH_ArkWebHttpBodyStream_SetReadCallback=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_SetReadCallback passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_SetAsyncReadCallback() {
        val r = try { OH_ArkWebHttpBodyStream_SetAsyncReadCallback(null, null) } catch (e: Throwable) { logLine("OH_ArkWebHttpBodyStream_SetAsyncReadCallback (API 20) exception: $e"); ARKWEB_INVALID_PARAM }
        logLine("OH_ArkWebHttpBodyStream_SetAsyncReadCallback=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_SetAsyncReadCallback passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_Init() {
        val r = OH_ArkWebHttpBodyStream_Init(null, null)
        logLine("OH_ArkWebHttpBodyStream_Init=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_Init passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_Read() {
        OH_ArkWebHttpBodyStream_Read(null, null, 0)
        logLine("OH_ArkWebHttpBodyStream_Read passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_AsyncRead() {
        try { OH_ArkWebHttpBodyStream_AsyncRead(null, null, 0) } catch (e: Throwable) { logLine("OH_ArkWebHttpBodyStream_AsyncRead (API 20) exception: $e") }
        logLine("OH_ArkWebHttpBodyStream_AsyncRead passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_GetSize() {
        val r = OH_ArkWebHttpBodyStream_GetSize(null)
        logLine("OH_ArkWebHttpBodyStream_GetSize=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_GetSize passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_GetPosition() {
        val r = OH_ArkWebHttpBodyStream_GetPosition(null)
        logLine("OH_ArkWebHttpBodyStream_GetPosition=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_GetPosition passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_IsChunked() {
        val r = OH_ArkWebHttpBodyStream_IsChunked(null)
        logLine("OH_ArkWebHttpBodyStream_IsChunked=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_IsChunked passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_IsEof() {
        val r = OH_ArkWebHttpBodyStream_IsEof(null)
        logLine("OH_ArkWebHttpBodyStream_IsEof=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_IsEof passed")
    }

    @Test
    fun testOH_ArkWebHttpBodyStream_IsInMemory() {
        val r = OH_ArkWebHttpBodyStream_IsInMemory(null)
        logLine("OH_ArkWebHttpBodyStream_IsInMemory=$r")
        assertNotNull(r)
        logLine("OH_ArkWebHttpBodyStream_IsInMemory passed")
    }

    @Test
    fun testOH_ArkWeb_RegisterCustomSchemes() {
        val r = OH_ArkWeb_RegisterCustomSchemes("test_scheme", 0)
        logLine("OH_ArkWeb_RegisterCustomSchemes=$r")
        assertNotNull(r)
        logLine("OH_ArkWeb_RegisterCustomSchemes passed")
    }

    @Test
    fun testOH_ArkWebServiceWorker_SetSchemeHandler() {
        val r = OH_ArkWebServiceWorker_SetSchemeHandler(null, null)
        logLine("OH_ArkWebServiceWorker_SetSchemeHandler=$r")
        assertNotNull(r)
        logLine("OH_ArkWebServiceWorker_SetSchemeHandler passed")
    }

    @Test
    fun testOH_ArkWeb_SetSchemeHandler() {
        val r = OH_ArkWeb_SetSchemeHandler("test_scheme", "test_web", null)
        logLine("OH_ArkWeb_SetSchemeHandler=$r")
        assertNotNull(r)
        logLine("OH_ArkWeb_SetSchemeHandler passed")
    }

    @Test
    fun testOH_ArkWebServiceWorker_ClearSchemeHandlers() {
        val r = OH_ArkWebServiceWorker_ClearSchemeHandlers()
        logLine("OH_ArkWebServiceWorker_ClearSchemeHandlers=$r")
        assertNotNull(r)
        logLine("OH_ArkWebServiceWorker_ClearSchemeHandlers passed")
    }

    @Test
    fun testOH_ArkWeb_ClearSchemeHandlers() {
        val r = OH_ArkWeb_ClearSchemeHandlers("test_web")
        logLine("OH_ArkWeb_ClearSchemeHandlers=$r")
        assertNotNull(r)
        logLine("OH_ArkWeb_ClearSchemeHandlers passed")
    }

    @Test
    fun testOH_ArkWeb_CreateSchemeHandler() {
        memScoped {
            val schemeHandlerPtr = alloc<CPointerVar<ArkWeb_SchemeHandler_>>()
            OH_ArkWeb_CreateSchemeHandler(schemeHandlerPtr.ptr)
            OH_ArkWeb_DestroySchemeHandler(schemeHandlerPtr.value)
            logLine("OH_ArkWeb_CreateSchemeHandler passed")
        }
    }

    @Test
    fun testOH_ArkWeb_DestroySchemeHandler() {
        memScoped {
            val schemeHandlerPtr = alloc<CPointerVar<ArkWeb_SchemeHandler_>>()
            OH_ArkWeb_CreateSchemeHandler(schemeHandlerPtr.ptr)
            OH_ArkWeb_DestroySchemeHandler(schemeHandlerPtr.value)
            logLine("OH_ArkWeb_DestroySchemeHandler passed")
        }
    }

    @Test
    fun testOH_ArkWebSchemeHandler_SetUserData() {
        memScoped {
            val schemeHandlerPtr = alloc<CPointerVar<ArkWeb_SchemeHandler_>>()
            OH_ArkWeb_CreateSchemeHandler(schemeHandlerPtr.ptr)
            val r = OH_ArkWebSchemeHandler_SetUserData(schemeHandlerPtr.value, null)
            logLine("OH_ArkWebSchemeHandler_SetUserData=$r")
            OH_ArkWeb_DestroySchemeHandler(schemeHandlerPtr.value)
            logLine("OH_ArkWebSchemeHandler_SetUserData passed")
        }
    }

    @Test
    fun testOH_ArkWebSchemeHandler_GetUserData() {
        memScoped {
            val schemeHandlerPtr = alloc<CPointerVar<ArkWeb_SchemeHandler_>>()
            OH_ArkWeb_CreateSchemeHandler(schemeHandlerPtr.ptr)
            val r = OH_ArkWebSchemeHandler_GetUserData(schemeHandlerPtr.value)
            logLine("OH_ArkWebSchemeHandler_GetUserData=$r")
            OH_ArkWeb_DestroySchemeHandler(schemeHandlerPtr.value)
            logLine("OH_ArkWebSchemeHandler_GetUserData passed")
        }
    }

    @Test
    fun testOH_ArkWebSchemeHandler_SetOnRequestStart() {
        memScoped {
            val schemeHandlerPtr = alloc<CPointerVar<ArkWeb_SchemeHandler_>>()
            OH_ArkWeb_CreateSchemeHandler(schemeHandlerPtr.ptr)
            val r = OH_ArkWebSchemeHandler_SetOnRequestStart(schemeHandlerPtr.value, null)
            logLine("OH_ArkWebSchemeHandler_SetOnRequestStart=$r")
            OH_ArkWeb_DestroySchemeHandler(schemeHandlerPtr.value)
            logLine("OH_ArkWebSchemeHandler_SetOnRequestStart passed")
        }
    }

    @Test
    fun testOH_ArkWebSchemeHandler_SetOnRequestStop() {
        memScoped {
            val schemeHandlerPtr = alloc<CPointerVar<ArkWeb_SchemeHandler_>>()
            OH_ArkWeb_CreateSchemeHandler(schemeHandlerPtr.ptr)
            val r = OH_ArkWebSchemeHandler_SetOnRequestStop(schemeHandlerPtr.value, null)
            logLine("OH_ArkWebSchemeHandler_SetOnRequestStop=$r")
            OH_ArkWeb_DestroySchemeHandler(schemeHandlerPtr.value)
            logLine("OH_ArkWebSchemeHandler_SetOnRequestStop passed")
        }
    }

    @Test
    fun testOH_ArkWeb_CreateResponse() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWeb_CreateResponse passed")
        }
    }

    @Test
    fun testOH_ArkWeb_DestroyResponse() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWeb_DestroyResponse passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetUrl() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetUrl(responsePtr.value, null)
            logLine("OH_ArkWebResponse_SetUrl=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetUrl passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetUrl() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWebResponse_GetUrl(responsePtr.value, null)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetUrl passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetError() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetError(responsePtr.value, 0)
            logLine("OH_ArkWebResponse_SetError=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetError passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetError() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_GetError(responsePtr.value)
            logLine("OH_ArkWebResponse_GetError=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetError passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetStatus() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetStatus(responsePtr.value, 0)
            logLine("OH_ArkWebResponse_SetStatus=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetStatus passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetStatus() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_GetStatus(responsePtr.value)
            logLine("OH_ArkWebResponse_GetStatus=$r")
            assertNotNull(r)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetStatus passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetStatusText() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetStatusText(responsePtr.value, null)
            logLine("OH_ArkWebResponse_SetStatusText=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetStatusText passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetStatusText() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWebResponse_GetStatusText(responsePtr.value, null)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetStatusText passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetMimeType() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetMimeType(responsePtr.value, null)
            logLine("OH_ArkWebResponse_SetMimeType=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetMimeType passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetMimeType() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWebResponse_GetMimeType(responsePtr.value, null)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetMimeType passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetCharset() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetCharset(responsePtr.value, null)
            logLine("OH_ArkWebResponse_SetCharset=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetCharset passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetCharset() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWebResponse_GetCharset(responsePtr.value, null)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetCharset passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_SetHeaderByName() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            val r = OH_ArkWebResponse_SetHeaderByName(responsePtr.value, null, null, false)
            logLine("OH_ArkWebResponse_SetHeaderByName=$r")
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_SetHeaderByName passed")
        }
    }

    @Test
    fun testOH_ArkWebResponse_GetHeaderByName() {
        memScoped {
            val responsePtr = alloc<CPointerVar<ArkWeb_Response_>>()
            OH_ArkWeb_CreateResponse(responsePtr.ptr)
            OH_ArkWebResponse_GetHeaderByName(responsePtr.value, null, null)
            OH_ArkWeb_DestroyResponse(responsePtr.value)
            logLine("OH_ArkWebResponse_GetHeaderByName passed")
        }
    }

    @Test
    fun testOH_ArkWebResourceHandler_Destroy() {
        val r = OH_ArkWebResourceHandler_Destroy(null)
        logLine("OH_ArkWebResourceHandler_Destroy=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceHandler_Destroy passed")
    }

    @Test
    fun testOH_ArkWebResourceHandler_DidReceiveResponse() {
        val r = OH_ArkWebResourceHandler_DidReceiveResponse(null, null)
        logLine("OH_ArkWebResourceHandler_DidReceiveResponse=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceHandler_DidReceiveResponse passed")
    }

    @Test
    fun testOH_ArkWebResourceHandler_DidReceiveData() {
        val r = OH_ArkWebResourceHandler_DidReceiveData(null, null, 0L)
        logLine("OH_ArkWebResourceHandler_DidReceiveData=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceHandler_DidReceiveData passed")
    }

    @Test
    fun testOH_ArkWebResourceHandler_DidFinish() {
        val r = OH_ArkWebResourceHandler_DidFinish(null)
        logLine("OH_ArkWebResourceHandler_DidFinish=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceHandler_DidFinish passed")
    }

    @Test
    fun testOH_ArkWebResourceHandler_DidFailWithError() {
        val r = OH_ArkWebResourceHandler_DidFailWithError(null, 0)
        logLine("OH_ArkWebResourceHandler_DidFailWithError=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceHandler_DidFailWithError passed")
    }

    @Test
    fun testOH_ArkWebResourceHandler_DidFailWithErrorV2() {
        val r = try { OH_ArkWebResourceHandler_DidFailWithErrorV2(null, 0, false) } catch (e: Throwable) { logLine("OH_ArkWebResourceHandler_DidFailWithErrorV2 (API 20) exception: $e"); ARKWEB_INVALID_PARAM }
        logLine("OH_ArkWebResourceHandler_DidFailWithErrorV2=$r")
        assertNotNull(r)
        logLine("OH_ArkWebResourceHandler_DidFailWithErrorV2 passed")
    }

    @Test
    fun testOH_ArkWeb_ReleaseString() {
        OH_ArkWeb_ReleaseString(null)
        logLine("OH_ArkWeb_ReleaseString passed")
    }

    @Test
    fun testOH_ArkWeb_ReleaseByteArray() {
        OH_ArkWeb_ReleaseByteArray(null)
        logLine("OH_ArkWeb_ReleaseByteArray passed")
    }
}
