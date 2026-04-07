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
import platform.DeviceSecurityKit.SecurityAudit.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class SecurityAuditTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_SecurityAudit_Notify_Event() {
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD.toInt(), 0x27000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_FILE.toInt(), 0x1C000007)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_FILE_INTERCEPTED.toInt(), 0x1C001100)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_ACCOUNT.toInt(), 0x10000100)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_WINDOW.toInt(), 0x07000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_VOLUME.toInt(), 0x0F000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_PRINTER.toInt(), 0x2E000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_PROCESS.toInt(), 0x1C000008)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_NETWORK_TRAFFIC.toInt(), 0x1C00000E)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_NETWORK_CONN.toInt(), 0x1C00000F)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_CAMERA.toInt(), 0x2D000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_APP.toInt(), 0x10000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_EDM.toInt(), 0x11000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_CERT.toInt(), 0x12003000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_KIA_CREATE.toInt(), 0x1C00000B)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_KIA_READ.toInt(), 0x1C000012)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_KIA_VARIANT.toInt(), 0x1C00000C)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_KIA_INTERCEPT.toInt(), 0x1C00000A)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_PERMISSION.toInt(), 0x0B000000)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_DNS.toInt(), 0x03000001)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_APP_INSTALL_INTERCEPTED.toInt(), 0x18000100)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_APP_UNINSTALL_INTERCEPTED.toInt(), 0x18000101)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_APP_UPDATE_INTERCEPTED.toInt(), 0x18000102)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_APP_RECOVER_INTERCEPTED.toInt(), 0x18000103)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_APP_START_INTERCEPTED.toInt(), 0x18000104)
        assertEquals(SECURITY_AUDIT_NOTIFY_EVENT_USB_ACCESS_INTERCEPTED.toInt(), 0x30000000)
        logLine("SecurityAudit_Notify_Event passed")
    }

    @Test
    fun testEnum_SecurityAudit_Auth_Event() {
        assertEquals(SECURITY_AUDIT_AUTH_EVENT_FILE_CREATE.toInt(), 0x1C801100)
        assertEquals(SECURITY_AUDIT_AUTH_EVENT_FILE_OPEN.toInt(), 0x1C801101)
        assertEquals(SECURITY_AUDIT_AUTH_EVENT_FILE_RENAME.toInt(), 0x1C801102)
        assertEquals(SECURITY_AUDIT_AUTH_EVENT_FILE_DELETE.toInt(), 0x1C801103)
        assertEquals(SECURITY_AUDIT_AUTH_EVENT_FILE_SETEXTATTR.toInt(), 0x1C801104)
        assertEquals(SECURITY_AUDIT_AUTH_EVENT_FILE_DELETEEXTATTR.toInt(), 0x1C801105)
        logLine("SecurityAudit_Auth_Event passed")
    }

    @Test
    fun testEnum_SecurityAudit_FilterType() {
        assertEquals(EVENT_TYPE_EQUAL.toInt(), 0x00000100)
        assertEquals(EVENT_SUBTYPE_EQUAL.toInt(), 0x00000200)
        assertEquals(FILE_PATH_EQUAL.toInt(), 0x00010000)
        assertEquals(FILE_PATH_PREFIX.toInt(), 0x00010001)
        assertEquals(FILE_PATH_SUFFIX.toInt(), 0x00010002)
        assertEquals(PROCESS_UID_EQUAL.toInt(), 0x00020000)
        assertEquals(PROCESS_PID_EQUAL.toInt(), 0x00020100)
        assertEquals(PROCESS_NAME_EQUAL.toInt(), 0x00020200)
        assertEquals(PROCESS_NAME_PREFIX.toInt(), 0x00020201)
        assertEquals(PROCESS_NAME_SUFFIX.toInt(), 0x00020202)
        logLine("SecurityAudit_FilterType passed")
    }

    @Test
    fun testEnum_SecurityAudit_AuthResult() {
        assertEquals(SECURITY_AUDIT_AUTH_RESULT_ALLOW.toInt(), 0)
        assertEquals(SECURITY_AUDIT_AUTH_RESULT_DENY.toInt(), 1)
        logLine("SecurityAudit_AuthResult passed")
    }

    @Test
    fun testHMS_SecurityAudit_NewClient() {
        memScoped {
            val clientPtr = alloc<CPointerVar<SecurityAudit_Client_Impl>>()
            val ret = try { HMS_SecurityAudit_NewClient(clientPtr.ptr, null) } catch (e: Throwable) { logLine("HMS_SecurityAudit_NewClient (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_NewClient=$ret")
            if (clientPtr.value != null) {
                try { HMS_SecurityAudit_DeleteClient(clientPtr.value) } catch (_: Throwable) { }
            }
        }
    }

    @Test
    fun testHMS_SecurityAudit_DeleteClient() {
        memScoped {
            val clientPtr = alloc<CPointerVar<SecurityAudit_Client_Impl>>()
            try { HMS_SecurityAudit_NewClient(clientPtr.ptr, null) } catch (_: Throwable) { }
            val delRet = try { HMS_SecurityAudit_DeleteClient(clientPtr.value) } catch (e: Throwable) { logLine("HMS_SecurityAudit_DeleteClient (API 20) exception: $e"); -1 }
            assertNotNull(delRet)
            logLine("HMS_SecurityAudit_DeleteClient=$delRet")
        }
    }

    @Test
    fun testHMS_SecurityAudit_Subscribe() {
        memScoped {
            val clientPtr = alloc<CPointerVar<SecurityAudit_Client_Impl>>()
            try { HMS_SecurityAudit_NewClient(clientPtr.ptr, null) } catch (_: Throwable) { }
            val client = clientPtr.value
            val eventVar = alloc<IntVar>().apply { value = SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD.toInt() }
            val ret = try { HMS_SecurityAudit_Subscribe(client, eventVar.ptr.reinterpret<SecurityAudit_Notify_EventVar>(), 1u) } catch (e: Throwable) { logLine("HMS_SecurityAudit_Subscribe (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_Subscribe=$ret")
            try { HMS_SecurityAudit_Unsubscribe(client, eventVar.ptr.reinterpret<SecurityAudit_Notify_EventVar>(), 1u) } catch (_: Throwable) { }
            try { HMS_SecurityAudit_DeleteClient(client) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_Unsubscribe() {
        memScoped {
            val clientPtr = alloc<CPointerVar<SecurityAudit_Client_Impl>>()
            try { HMS_SecurityAudit_NewClient(clientPtr.ptr, null) } catch (_: Throwable) { }
            val client = clientPtr.value
            val eventVar = alloc<IntVar>().apply { value = SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD.toInt() }
            try { HMS_SecurityAudit_Subscribe(client, eventVar.ptr.reinterpret<SecurityAudit_Notify_EventVar>(), 1u) } catch (_: Throwable) { }
            val ret = try { HMS_SecurityAudit_Unsubscribe(client, eventVar.ptr.reinterpret<SecurityAudit_Notify_EventVar>(), 1u) } catch (e: Throwable) { logLine("HMS_SecurityAudit_Unsubscribe (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_Unsubscribe=$ret")
            try { HMS_SecurityAudit_DeleteClient(client) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_AddFilter() {
        memScoped {
            val clientPtr = alloc<CPointerVar<SecurityAudit_Client_Impl>>()
            try { HMS_SecurityAudit_NewClient(clientPtr.ptr, null) } catch (_: Throwable) { }
            val client = clientPtr.value
            val filter = alloc<SecurityAudit_Filter>().apply {
                isInclude = true
                type = EVENT_TYPE_EQUAL
                valueCount = 0u
            }
            val ret = try { HMS_SecurityAudit_AddFilter(client, SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD, filter.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAudit_AddFilter (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_AddFilter=$ret")
            try { HMS_SecurityAudit_RemoveFilter(client, SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD, filter.ptr) } catch (_: Throwable) { }
            try { HMS_SecurityAudit_DeleteClient(client) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_RemoveFilter() {
        memScoped {
            val clientPtr = alloc<CPointerVar<SecurityAudit_Client_Impl>>()
            try { HMS_SecurityAudit_NewClient(clientPtr.ptr, null) } catch (_: Throwable) { }
            val client = clientPtr.value
            val filter = alloc<SecurityAudit_Filter>().apply {
                isInclude = true
                type = EVENT_TYPE_EQUAL
                valueCount = 0u
            }
            try { HMS_SecurityAudit_AddFilter(client, SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD, filter.ptr) } catch (_: Throwable) { }
            val ret = try { HMS_SecurityAudit_RemoveFilter(client, SECURITY_AUDIT_NOTIFY_EVENT_PASTEBOARD, filter.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAudit_RemoveFilter (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_RemoveFilter=$ret")
            try { HMS_SecurityAudit_DeleteClient(client) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_NewAuthClient() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            val ret = try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (e: Throwable) { logLine("HMS_SecurityAudit_NewAuthClient (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_NewAuthClient=$ret")
            if (authClientPtr.value != null) {
                try { HMS_SecurityAudit_DeleteAuthClient(authClientPtr.value) } catch (_: Throwable) { }
            }
        }
    }

    @Test
    fun testHMS_SecurityAudit_DeleteAuthClient() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (_: Throwable) { }
            val ret = try { HMS_SecurityAudit_DeleteAuthClient(authClientPtr.value) } catch (e: Throwable) { logLine("HMS_SecurityAudit_DeleteAuthClient (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_DeleteAuthClient=$ret")
        }
    }

    @Test
    fun testHMS_SecurityAudit_SubscribeAuthEvent() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (_: Throwable) { }
            val authClient = authClientPtr.value
            val authEventVar = alloc<IntVar>().apply { value = SECURITY_AUDIT_AUTH_EVENT_FILE_CREATE.toInt() }
            val ret = try { HMS_SecurityAudit_SubscribeAuthEvent(authClient, authEventVar.ptr.reinterpret<SecurityAudit_Auth_EventVar>(), 1u) } catch (e: Throwable) { logLine("HMS_SecurityAudit_SubscribeAuthEvent (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_SubscribeAuthEvent=$ret")
            try { HMS_SecurityAudit_UnsubscribeAuthEvent(authClient, authEventVar.ptr.reinterpret<SecurityAudit_Auth_EventVar>(), 1u) } catch (_: Throwable) { }
            try { HMS_SecurityAudit_DeleteAuthClient(authClient) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_UnsubscribeAuthEvent() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (_: Throwable) { }
            val authClient = authClientPtr.value
            val authEventVar = alloc<IntVar>().apply { value = SECURITY_AUDIT_AUTH_EVENT_FILE_CREATE.toInt() }
            try { HMS_SecurityAudit_SubscribeAuthEvent(authClient, authEventVar.ptr.reinterpret<SecurityAudit_Auth_EventVar>(), 1u) } catch (_: Throwable) { }
            val ret = try { HMS_SecurityAudit_UnsubscribeAuthEvent(authClient, authEventVar.ptr.reinterpret<SecurityAudit_Auth_EventVar>(), 1u) } catch (e: Throwable) { logLine("HMS_SecurityAudit_UnsubscribeAuthEvent (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_UnsubscribeAuthEvent=$ret")
            try { HMS_SecurityAudit_DeleteAuthClient(authClient) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_AddAuthEventFilter() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (_: Throwable) { }
            val authClient = authClientPtr.value
            val filter = alloc<SecurityAudit_Filter>().apply {
                isInclude = true
                type = FILE_PATH_EQUAL
                valueCount = 0u
            }
            val ret = try { HMS_SecurityAudit_AddAuthEventFilter(authClient, SECURITY_AUDIT_AUTH_EVENT_FILE_OPEN, filter.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAudit_AddAuthEventFilter (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_AddAuthEventFilter=$ret")
            try { HMS_SecurityAudit_RemoveAuthEventFilter(authClient, SECURITY_AUDIT_AUTH_EVENT_FILE_OPEN, filter.ptr) } catch (_: Throwable) { }
            try { HMS_SecurityAudit_DeleteAuthClient(authClient) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_RemoveAuthEventFilter() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (_: Throwable) { }
            val authClient = authClientPtr.value
            val filter = alloc<SecurityAudit_Filter>().apply {
                isInclude = true
                type = FILE_PATH_EQUAL
                valueCount = 0u
            }
            try { HMS_SecurityAudit_AddAuthEventFilter(authClient, SECURITY_AUDIT_AUTH_EVENT_FILE_OPEN, filter.ptr) } catch (_: Throwable) { }
            val ret = try { HMS_SecurityAudit_RemoveAuthEventFilter(authClient, SECURITY_AUDIT_AUTH_EVENT_FILE_OPEN, filter.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAudit_RemoveAuthEventFilter (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_RemoveAuthEventFilter=$ret")
            try { HMS_SecurityAudit_DeleteAuthClient(authClient) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_Auth() {
        memScoped {
            val authClientPtr = alloc<CPointerVar<SecurityAudit_AuthClient_Impl>>()
            try { HMS_SecurityAudit_NewAuthClient(authClientPtr.ptr, null) } catch (_: Throwable) { }
            val authClient = authClientPtr.value
            val event = alloc<SecurityAudit_Event>().apply { eventId = 0L }
            val ret = try { HMS_SecurityAudit_Auth(authClient, event.ptr, SECURITY_AUDIT_AUTH_RESULT_ALLOW) } catch (e: Throwable) { logLine("HMS_SecurityAudit_Auth (API 20) exception: $e"); -1 }
            assertNotNull(ret)
            logLine("HMS_SecurityAudit_Auth=$ret")
            try { HMS_SecurityAudit_DeleteAuthClient(authClient) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_SecurityAudit_QueryAllProcesses() {
        memScoped {
            val resultPtr = alloc<CPointerVar<ByteVar>>()
            val ret1 = try { HMS_SecurityAudit_QueryAllProcesses(resultPtr.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAudit_QueryAllProcesses (API 20) exception: $e"); -1 }
            assertNotNull(ret1)
            logLine("HMS_SecurityAudit_QueryAllProcesses=$ret1")
        }
    }

    @Test
    fun testHMS_SecurityAudit_QueryProcesses() {
        memScoped {
            val resultPtr = alloc<CPointerVar<ByteVar>>()
            val pids = allocArray<ULongVar>(1).apply { this[0] = 0uL }
            val ret2 = try { HMS_SecurityAudit_QueryProcesses(pids, 1u, resultPtr.ptr) } catch (e: Throwable) { logLine("HMS_SecurityAudit_QueryProcesses (API 20) exception: $e"); -1 }
            assertNotNull(ret2)
            logLine("HMS_SecurityAudit_QueryProcesses=$ret2")
        }
    }
}
