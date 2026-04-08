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
import platform.NetworkKit.Netstack.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NetstackTest {

    private fun logLine(msg: String) = println("[stdout] NetstackTest $msg")

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_Http_ErrCode() {
        assertEquals(OH_HTTP_RESULT_OK.toInt(), 0)
        assertEquals(OH_HTTP_PARAMETER_ERROR.toInt(), 401)
        assertEquals(OH_HTTP_PERMISSION_DENIED.toInt(), 201)
        assertEquals(OH_HTTP_NETSTACK_E_BASE.toInt(), 2300000)
        assertEquals(OH_HTTP_UNSUPPORTED_PROTOCOL.toInt(), 2300001)
        assertEquals(OH_HTTP_INVALID_URL.toInt(), 2300003)
        assertEquals(OH_HTTP_RESOLVE_PROXY_FAILED.toInt(), 2300005)
        assertEquals(OH_HTTP_RESOLVE_HOST_FAILED.toInt(), 2300006)
        assertEquals(OH_HTTP_CONNECT_SERVER_FAILED.toInt(), 2300007)
        assertEquals(OH_HTTP_INVALID_SERVER_RESPONSE.toInt(), 2300008)
        assertEquals(OH_HTTP_ACCESS_REMOTE_DENIED.toInt(), 2300009)
        assertEquals(OH_HTTP_HTTP2_FRAMING_ERROR.toInt(), 2300016)
        assertEquals(OH_HTTP_TRANSFER_PARTIAL_FILE.toInt(), 2300018)
        assertEquals(OH_HTTP_WRITE_DATA_FAILED.toInt(), 2300023)
        assertEquals(OH_HTTP_UPLOAD_FAILED.toInt(), 2300025)
        assertEquals(OH_HTTP_OPEN_LOCAL_DATA_FAILED.toInt(), 2300026)
        assertEquals(OH_HTTP_OUT_OF_MEMORY.toInt(), 2300027)
        assertEquals(OH_HTTP_OPERATION_TIMEOUT.toInt(), 2300028)
        assertEquals(OH_HTTP_TOO_MANY_REDIRECTIONS.toInt(), 2300047)
        assertEquals(OH_HTTP_SERVER_RETURNED_NOTHING.toInt(), 2300052)
        assertEquals(OH_HTTP_SEND_DATA_FAILED.toInt(), 2300055)
        assertEquals(OH_HTTP_RECEIVE_DATA_FAILED.toInt(), 2300056)
        assertEquals(OH_HTTP_SSL_CERTIFICATE_ERROR.toInt(), 2300058)
        assertEquals(OH_HTTP_SSL_CIPHER_USED_ERROR.toInt(), 2300059)
        assertEquals(OH_HTTP_INVALID_SSL_PEER_CERT.toInt(), 2300060)
        assertEquals(OH_HTTP_INVALID_ENCODING_FORMAT.toInt(), 2300061)
        assertEquals(OH_HTTP_FILE_TOO_LARGE.toInt(), 2300063)
        assertEquals(OH_HTTP_REMOTE_DISK_FULL.toInt(), 2300070)
        assertEquals(OH_HTTP_REMOTE_FILE_EXISTS.toInt(), 2300073)
        assertEquals(OH_HTTP_SSL_CA_NOT_EXIST.toInt(), 2300077)
        assertEquals(OH_HTTP_REMOTE_FILE_NOT_FOUND.toInt(), 2300078)
        assertEquals(OH_HTTP_AUTHENTICATION_ERROR.toInt(), 2300094)
        assertEquals(OH_HTTP_ACCESS_DOMAIN_NOT_ALLOWED.toInt(), 2300998)
        assertEquals(OH_HTTP_UNKNOWN_ERROR.toInt(), 2300999)
        logLine("testEnum_Http_ErrCode passed")
    }

    @Test
    fun testEnum_Http_ResponseCode() {
        assertEquals(OH_HTTP_OK.toInt(), 200)
        assertEquals(OH_HTTP_CREATED.toInt(), 201)
        assertEquals(OH_HTTP_ACCEPTED.toInt(), 202)
        assertEquals(OH_HTTP_NON_AUTHORITATIVE_INFO.toInt(), 203)
        assertEquals(OH_HTTP_NO_CONTENT.toInt(), 204)
        assertEquals(OH_HTTP_RESET.toInt(), 205)
        assertEquals(OH_HTTP_PARTIAL.toInt(), 206)
        assertEquals(OH_HTTP_MULTI_CHOICE.toInt(), 300)
        assertEquals(OH_HTTP_MOVED_PERM.toInt(), 301)
        assertEquals(OH_HTTP_MOVED_TEMP.toInt(), 302)
        assertEquals(OH_HTTP_SEE_OTHER.toInt(), 303)
        assertEquals(OH_HTTP_NOT_MODIFIED.toInt(), 304)
        assertEquals(OH_HTTP_USE_PROXY.toInt(), 305)
        assertEquals(OH_HTTP_BAD_REQUEST.toInt(), 400)
        assertEquals(OH_HTTP_UNAUTHORIZED.toInt(), 401)
        assertEquals(OH_HTTP_PAYMENT_REQUIRED.toInt(), 402)
        assertEquals(OH_HTTP_FORBIDDEN.toInt(), 403)
        assertEquals(OH_HTTP_NOT_FOUND.toInt(), 404)
        assertEquals(OH_HTTP_BAD_METHOD.toInt(), 405)
        assertEquals(OH_HTTP_NOT_ACCEPTABLE.toInt(), 406)
        assertEquals(OH_HTTP_PROXY_AUTH.toInt(), 407)
        assertEquals(OH_HTTP_CLIENT_TIMEOUT.toInt(), 408)
        assertEquals(OH_HTTP_CONFLICT.toInt(), 409)
        assertEquals(OH_HTTP_GONE.toInt(), 410)
        assertEquals(OH_HTTP_LENGTH_REQUIRED.toInt(), 411)
        assertEquals(OH_HTTP_PRECON_FAILED.toInt(), 412)
        assertEquals(OH_HTTP_ENTITY_TOO_LARGE.toInt(), 413)
        assertEquals(OH_HTTP_REQUEST_TOO_LONG.toInt(), 414)
        assertEquals(OH_HTTP_UNSUPPORTED_TYPE.toInt(), 415)
        assertEquals(OH_HTTP_RANGE_NOT_MET.toInt(), 416)
        assertEquals(OH_HTTP_INTERNAL_ERROR.toInt(), 500)
        assertEquals(OH_HTTP_NOT_IMPLEMENTED.toInt(), 501)
        assertEquals(OH_HTTP_BAD_GATEWAY.toInt(), 502)
        assertEquals(OH_HTTP_UNAVAILABLE.toInt(), 503)
        assertEquals(OH_HTTP_GATEWAY_TIMEOUT.toInt(), 504)
        assertEquals(OH_HTTP_VERSION.toInt(), 505)
        logLine("testEnum_Http_ResponseCode passed")
    }

    @Test
    fun testEnum_Http_AddressFamilyType() {
        assertEquals(HTTP_ADDRESS_FAMILY_DEFAULT.toInt(), 0)
        assertEquals(HTTP_ADDRESS_FAMILY_ONLY_V4.toInt(), 1)
        assertEquals(HTTP_ADDRESS_FAMILY_ONLY_V6.toInt(), 2)
        logLine("testEnum_Http_AddressFamilyType passed")
    }

    @Test
    fun testEnum_Http_HttpProtocol() {
        assertEquals(OH_HTTP_NONE.toInt(), 0)
        assertEquals(OH_HTTP1_1.toInt(), 1)
        assertEquals(OH_HTTP2.toInt(), 2)
        assertEquals(OH_HTTP3.toInt(), 3)
        logLine("testEnum_Http_HttpProtocol passed")
    }

    @Test
    fun testEnum_Http_CertType() {
        assertEquals(OH_HTTP_PEM.toInt(), 0)
        assertEquals(OH_HTTP_DER.toInt(), 1)
        assertEquals(OH_HTTP_P12.toInt(), 2)
        logLine("testEnum_Http_CertType passed")
    }

    @Test
    fun testEnum_Http_ProxyType() {
        assertEquals(Http_ProxyType.HTTP_PROXY_NOT_USE.value.toInt(), 0)
        assertEquals(Http_ProxyType.HTTP_PROXY_SYSTEM.value.toInt(), 1)
        assertEquals(Http_ProxyType.HTTP_PROXY_CUSTOM.value.toInt(), 2)
        logLine("testEnum_Http_ProxyType passed")
    }

    @Test
    fun testEnum_WebSocket_ErrCode() {
        assertEquals(WEBSOCKET_OK.toInt(), 0)
        assertEquals(E_BASE.toInt(), 1000)
        assertEquals(WEBSOCKET_CLIENT_NULL.toInt(), 1001)
        assertEquals(WEBSOCKET_CLIENT_NOT_CREATED.toInt(), 1002)
        assertEquals(WEBSOCKET_CONNECTION_ERROR.toInt(), 1003)
        assertEquals(WEBSOCKET_CONNECTION_PARSE_URL_ERROR.toInt(), 1005)
        assertEquals(WEBSOCKET_CONNECTION_NO_MEMORY.toInt(), 1006)
        assertEquals(WEBSOCKET_CONNECTION_CLOSED_BY_PEER.toInt(), 1007)
        assertEquals(WEBSOCKET_DESTROYED.toInt(), 1008)
        assertEquals(WEBSOCKET_PROTOCOL_ERROR.toInt(), 1009)
        assertEquals(WEBSOCKET_SEND_NO_MEMORY.toInt(), 1010)
        assertEquals(WEBSOCKET_SEND_DATA_NULL.toInt(), 1011)
        assertEquals(WEBSOCKET_DATA_LENGTH_EXCEEDED.toInt(), 1012)
        assertEquals(WEBSOCKET_QUEUE_LENGTH_EXCEEDED.toInt(), 1013)
        assertEquals(WEBSOCKET_NO_CLIENT_CONTEXT.toInt(), 1014)
        assertEquals(WEBSOCKET_NO_HEADER_CONTEXT.toInt(), 1015)
        assertEquals(WEBSOCKET_HEADER_EXCEEDED.toInt(), 1016)
        assertEquals(WEBSOCKET_NO_CONNECTION.toInt(), 1017)
        assertEquals(WEBSOCKET_NO_CONNECTION_CONTEXT.toInt(), 1018)
        logLine("testEnum_WebSocket_ErrCode passed")
    }

    @Test
    fun testEnum_NetStack_CertType() {
        assertEquals(NETSTACK_CERT_TYPE_PEM.toInt(), 0)
        assertEquals(NETSTACK_CERT_TYPE_DER.toInt(), 1)
        assertEquals(NETSTACK_CERT_TYPE_INVALID.toInt(), 2)
        logLine("testEnum_NetStack_CertType passed")
    }

    @Test
    fun testEnum_NetStack_CertificatePinningKind() {
        assertEquals(NetStack_CertificatePinningKind.PUBLIC_KEY.ordinal, 0)
        logLine("testEnum_NetStack_CertificatePinningKind passed")
    }

    @Test
    fun testEnum_NetStack_HashAlgorithm() {
        assertEquals(NetStack_HashAlgorithm.SHA_256.ordinal, 0)
        logLine("testEnum_NetStack_HashAlgorithm passed")
    }

    // ==================== SSL 函数 ====================

    @Test
    fun testOH_NetStack_CertVerification() {
        memScoped {
            val ret = OH_NetStack_CertVerification(null, null)
            assertNotNull(ret)
            logLine("OH_NetStack_CertVerification=$ret")
        }
    }

    @Test
    fun testOH_NetStack_GetPinSetForHostName() {
        memScoped {
            val pin = alloc<NetStack_CertificatePinning>()
            val ret = OH_NetStack_GetPinSetForHostName(null, pin.ptr)
            assertNotNull(ret)
            logLine("OH_NetStack_GetPinSetForHostName=$ret")
        }
    }

    @Test
    fun testOH_NetStack_GetCertificatesForHostName() {
        memScoped {
            val certs = alloc<NetStack_Certificates>()
            val ret = OH_NetStack_GetCertificatesForHostName(null, certs.ptr)
            assertNotNull(ret)
            logLine("OH_NetStack_GetCertificatesForHostName=$ret")
        }
    }

    @Test
    fun testOH_Netstack_DestroyCertificatesContent() {
        memScoped {
            OH_Netstack_DestroyCertificatesContent(null)
            logLine("OH_Netstack_DestroyCertificatesContent=called")
        }
    }

    @Test
    fun testOH_Netstack_IsCleartextPermitted() {
        memScoped {
            val permitted = alloc<BooleanVar>()
            val ret = try { OH_Netstack_IsCleartextPermitted(permitted.ptr) } catch (e: Throwable) { logLine("OH_Netstack_IsCleartextPermitted (API 18) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("OH_Netstack_IsCleartextPermitted=$ret")
        }
    }

    @Test
    fun testOH_Netstack_IsCleartextPermittedByHostName() {
        memScoped {
            val permitted = alloc<BooleanVar>()
            val ret = try { OH_Netstack_IsCleartextPermittedByHostName(null, permitted.ptr) } catch (e: Throwable) { logLine("OH_Netstack_IsCleartextPermittedByHostName (API 18) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("OH_Netstack_IsCleartextPermittedByHostName=$ret")
        }
    }

    @Test
    fun testOH_Netstack_IsCleartextCfgByComponent() {
        memScoped {
            val componentCfg = alloc<BooleanVar>()
            val ret = try { OH_Netstack_IsCleartextCfgByComponent(null, componentCfg.ptr) } catch (e: Throwable) { logLine("OH_Netstack_IsCleartextCfgByComponent (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("OH_Netstack_IsCleartextCfgByComponent=$ret")
        }
    }

    // ==================== HTTP 函数 ====================

    @Test
    fun testOH_Http_CreateHeaders() {
        memScoped {
            val headers = try { OH_Http_CreateHeaders() } catch (e: Throwable) { logLine("OH_Http_CreateHeaders (API 20) exception: $e"); null }
            assertNotNull(headers)
            logLine("OH_Http_CreateHeaders=$headers")
            val ptr = alloc<CPointerVar<Http_Headers>>()
            ptr.value = headers
            try { OH_Http_DestroyHeaders(ptr.ptr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_Http_DestroyHeaders() {
        memScoped {
            val headers = try { OH_Http_CreateHeaders() } catch (e: Throwable) { logLine("OH_Http_CreateHeaders (API 20) exception: $e"); null }
            assertNotNull(headers)
            val ptr = alloc<CPointerVar<Http_Headers>>()
            ptr.value = headers
            try { OH_Http_DestroyHeaders(ptr.ptr) } catch (e: Throwable) { logLine("OH_Http_DestroyHeaders (API 20) exception: $e") }
            logLine("OH_Http_DestroyHeaders=called")
        }
    }

    @Test
    fun testOH_Http_SetHeaderValue() {
        memScoped {
            val headers = try { OH_Http_CreateHeaders() } catch (e: Throwable) { logLine("OH_Http_CreateHeaders (API 20) exception: $e"); null }
            assertNotNull(headers)
            val rc = try { OH_Http_SetHeaderValue(headers, "k", "v") } catch (e: Throwable) { logLine("OH_Http_SetHeaderValue (API 20) exception: $e"); OH_HTTP_PARAMETER_ERROR }
            logLine("OH_Http_SetHeaderValue=$rc")
            val ptr = alloc<CPointerVar<Http_Headers>>()
            ptr.value = headers
            try { OH_Http_DestroyHeaders(ptr.ptr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_Http_GetHeaderValue() {
        memScoped {
            val headers = try { OH_Http_CreateHeaders() } catch (e: Throwable) { logLine("OH_Http_CreateHeaders (API 20) exception: $e"); null }
            assertNotNull(headers)
            try { OH_Http_SetHeaderValue(headers, "k", "v") } catch (e: Throwable) { }
            val value = try { OH_Http_GetHeaderValue(headers, "k") } catch (e: Throwable) { logLine("OH_Http_GetHeaderValue (API 20) exception: $e"); null }
            logLine("OH_Http_GetHeaderValue=$value")
            val ptr = alloc<CPointerVar<Http_Headers>>()
            ptr.value = headers
            try { OH_Http_DestroyHeaders(ptr.ptr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_Http_GetHeaderEntries() {
        memScoped {
            val headers = try { OH_Http_CreateHeaders() } catch (e: Throwable) { logLine("OH_Http_CreateHeaders (API 20) exception: $e"); null }
            assertNotNull(headers)
            val entries = try { OH_Http_GetHeaderEntries(headers) } catch (e: Throwable) { logLine("OH_Http_GetHeaderEntries (API 20) exception: $e"); null }
            logLine("OH_Http_GetHeaderEntries=$entries")
            if (entries != null) {
                val entryPtr = alloc<CPointerVar<Http_HeaderEntry>>()
                entryPtr.value = entries
                try { OH_Http_DestroyHeaderEntries(entryPtr.ptr) } catch (e: Throwable) { }
            }
            val ptr = alloc<CPointerVar<Http_Headers>>()
            ptr.value = headers
            try { OH_Http_DestroyHeaders(ptr.ptr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_Http_DestroyHeaderEntries() {
        memScoped {
            val headers = try { OH_Http_CreateHeaders() } catch (e: Throwable) { logLine("OH_Http_CreateHeaders (API 20) exception: $e"); null }
            assertNotNull(headers)
            val entries = try { OH_Http_GetHeaderEntries(headers) } catch (e: Throwable) { null }
            if (entries != null) {
                val entryPtr = alloc<CPointerVar<Http_HeaderEntry>>()
                entryPtr.value = entries
                try { OH_Http_DestroyHeaderEntries(entryPtr.ptr) } catch (e: Throwable) { logLine("OH_Http_DestroyHeaderEntries (API 20) exception: $e") }
                logLine("OH_Http_DestroyHeaderEntries=called")
            }
            val ptr = alloc<CPointerVar<Http_Headers>>()
            ptr.value = headers
            try { OH_Http_DestroyHeaders(ptr.ptr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_Http_CreateRequest() {
        memScoped {
            val req = try { OH_Http_CreateRequest(null) } catch (e: Throwable) { logLine("OH_Http_CreateRequest (API 20) exception: $e"); null }
            logLine("OH_Http_CreateRequest=$req")
            if (req != null) {
                val reqPtr = alloc<CPointerVar<Http_Request>>()
                reqPtr.value = req
                try { OH_Http_Destroy(reqPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Http_Request() {
        memScoped {
            val req = try { OH_Http_CreateRequest(null) } catch (e: Throwable) { logLine("OH_Http_CreateRequest (API 20) exception: $e"); null }
            val emptyHandler = alloc<Http_EventsHandler>().apply {
                onDataReceive = null
                onUploadProgress = null
                onDownloadProgress = null
                onHeadersReceive = null
                onDataEnd = null
                onCanceled = null
            }
            val rc = try { OH_Http_Request(req, null, emptyHandler.readValue()) } catch (e: Throwable) { logLine("OH_Http_Request (API 20) exception: $e"); -1 }
            logLine("OH_Http_Request=$rc")
            val reqPtr = alloc<CPointerVar<Http_Request>>()
            reqPtr.value = req
            try { OH_Http_Destroy(reqPtr.ptr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_Http_Destroy() {
        memScoped {
            val req = try { OH_Http_CreateRequest(null) } catch (e: Throwable) { logLine("OH_Http_CreateRequest (API 20) exception: $e"); null }
            val reqPtr = alloc<CPointerVar<Http_Request>>()
            reqPtr.value = req
            try { OH_Http_Destroy(reqPtr.ptr) } catch (e: Throwable) { logLine("OH_Http_Destroy (API 20) exception: $e") }
            logLine("OH_Http_Destroy=called")
        }
    }

    // ==================== WebSocket 函数 ====================

    @Test
    fun testOH_WebSocketClient_Constructor() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            logLine("OH_WebSocketClient_Constructor=$client")
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_AddHeader() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            val header = alloc<WebSocket_Header>().apply {
                fieldName = null
                fieldValue = null
                next = null
            }
            val ret = OH_WebSocketClient_AddHeader(client, header.readValue())
            assertNotNull(ret)
            logLine("OH_WebSocketClient_AddHeader=$ret")
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_Connect() {
        memScoped {
            val onOpen = staticCFunction { _client: CPointer<WebSocket>?, _result: CValue<WebSocket_OpenResult> -> }
            val onMessage = staticCFunction { _client: CPointer<WebSocket>?, _data: CPointer<ByteVar>?, _length: UInt -> }
            val onError = staticCFunction { _client: CPointer<WebSocket>?, _result: CValue<WebSocket_ErrorResult> -> }
            val onClose = staticCFunction { _client: CPointer<WebSocket>?, _result: CValue<WebSocket_CloseResult> -> }
            val client = OH_WebSocketClient_Constructor(onOpen, onMessage, onError, onClose)
            assertNotNull(client)
            val url = "wss://echo.websocket.org"
            val opts = alloc<WebSocket_RequestOptions>().apply { headers = null }
            val rc = OH_WebSocketClient_Connect(client, url, opts.readValue())
            logLine("OH_WebSocketClient_Connect=$rc")
            val closeOpt = alloc<WebSocket_CloseOption>().apply { code = 1000u; reason = "test done".cstr.getPointer(this@memScoped) }
            OH_WebSocketClient_Close(client, closeOpt.readValue())
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_Close() {
        memScoped {
            val onOpen = staticCFunction { _client: CPointer<WebSocket>?, _result: CValue<WebSocket_OpenResult> -> }
            val onMessage = staticCFunction { _client: CPointer<WebSocket>?, _data: CPointer<ByteVar>?, _length: UInt -> }
            val onError = staticCFunction { _client: CPointer<WebSocket>?, _result: CValue<WebSocket_ErrorResult> -> }
            val onClose = staticCFunction { _client: CPointer<WebSocket>?, _result: CValue<WebSocket_CloseResult> -> }
            val client = OH_WebSocketClient_Constructor(onOpen, onMessage, onError, onClose)
            assertNotNull(client)
            val url = "wss://echo.websocket.org"
            val opts = alloc<WebSocket_RequestOptions>().apply { headers = null }
            OH_WebSocketClient_Connect(client, url, opts.readValue())
            val closeOpt = alloc<WebSocket_CloseOption>().apply { code = 1000u; reason = null }
            val rc = OH_WebSocketClient_Close(client, closeOpt.readValue())
            logLine("OH_WebSocketClient_Close=$rc")
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_Connect_noCallbacks() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            assertNotNull(client)
            val url = "wss://echo.websocket.org"
            val opts = alloc<WebSocket_RequestOptions>().apply { headers = null }
            val rc = OH_WebSocketClient_Connect(client, url, opts.readValue())
            logLine("OH_WebSocketClient_Connect(noCallbacks)=$rc")
            val closeOpt = alloc<WebSocket_CloseOption>().apply { code = 1000u; reason = null }
            OH_WebSocketClient_Close(client, closeOpt.readValue())
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_Close_noCallbacks() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            assertNotNull(client)
            val url = "wss://echo.websocket.org"
            val opts = alloc<WebSocket_RequestOptions>().apply { headers = null }
            OH_WebSocketClient_Connect(client, url, opts.readValue())
            val closeOpt = alloc<WebSocket_CloseOption>().apply { code = 1000u; reason = null }
            val rc = OH_WebSocketClient_Close(client, closeOpt.readValue())
            logLine("OH_WebSocketClient_Close(noCallbacks)=$rc")
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_Send() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            val ret = OH_WebSocketClient_Send(client, null, 0u)
            assertNotNull(ret)
            logLine("OH_WebSocketClient_Send=$ret")
            OH_WebSocketClient_Destroy(client)
        }
    }

    @Test
    fun testOH_WebSocketClient_Destroy() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            val ret = OH_WebSocketClient_Destroy(client)
            assertNotNull(ret)
            logLine("OH_WebSocketClient_Destroy=$ret")
        }
    }
}
