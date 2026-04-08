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
import platform.IPCKit.OHIPCRemoteObject.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHIPCRemoteObjectTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_IPC_RequestMode() {
        assertEquals(OH_IPC_REQUEST_MODE_SYNC.toInt(), 0)
        assertEquals(OH_IPC_REQUEST_MODE_ASYNC.toInt(), 1)
        logLine("OH_IPC_RequestMode passed")
    }

    @Test
    fun testOH_IPCRemoteStub_Create() {
        memScoped {
            val requestCb = staticCFunction { _code: UInt, _data: CPointer<OHIPCParcel>?, _reply: CPointer<OHIPCParcel>?, _userData: COpaquePointer? -> 0 }
            val destroyCb = staticCFunction { _userData: COpaquePointer? -> }
            val stub = OH_IPCRemoteStub_Create("test.iface", requestCb, destroyCb, null)
            assertNotNull(stub)
            logLine("OH_IPCRemoteStub_Create=$stub")
            OH_IPCRemoteStub_Destroy(stub)
        }
    }

    @Test
    fun testOH_IPCRemoteStub_Destroy() {
        memScoped {
            val requestCb = staticCFunction { _code: UInt, _data: CPointer<OHIPCParcel>?, _reply: CPointer<OHIPCParcel>?, _userData: COpaquePointer? -> 0 }
            val destroyCb = staticCFunction { _userData: COpaquePointer? -> }
            val stub = OH_IPCRemoteStub_Create("test.iface", requestCb, destroyCb, null)
            assertNotNull(stub)
            OH_IPCRemoteStub_Destroy(stub)
            logLine("OH_IPCRemoteStub_Destroy done")
        }
    }

    @Test
    fun testOH_IPCRemoteProxy_Destroy() {
        OH_IPCRemoteProxy_Destroy(null)
        logLine("OH_IPCRemoteProxy_Destroy(null) done")
    }

    @Test
    fun testOH_IPCRemoteProxy_SendRequest() {
        val sendRet = OH_IPCRemoteProxy_SendRequest(null, 1u, null, null, null)
        assertNotNull(sendRet)
        logLine("OH_IPCRemoteProxy_SendRequest ret=$sendRet")
    }

    @Test
    fun testOH_IPCRemoteProxy_GetInterfaceDescriptor() {
        memScoped {
            val descSlot = alloc<CPointerVar<ByteVar>>()
            val lenVar = alloc<IntVar>()
            val allocator = staticCFunction { _: Int -> null as COpaquePointer? }
            val getDescRet = OH_IPCRemoteProxy_GetInterfaceDescriptor(null, descSlot.ptr, lenVar.ptr, allocator)
            assertNotNull(getDescRet)
            logLine("OH_IPCRemoteProxy_GetInterfaceDescriptor ret=$getDescRet")
        }
    }

    @Test
    fun testOH_IPCDeathRecipient_Create() {
        memScoped {
            val deathCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipDestroyCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipient = OH_IPCDeathRecipient_Create(deathCb, recipDestroyCb, null)
            assertNotNull(recipient)
            logLine("OH_IPCDeathRecipient_Create=$recipient")
            OH_IPCDeathRecipient_Destroy(recipient)
        }
    }

    @Test
    fun testOH_IPCRemoteProxy_AddDeathRecipient() {
        memScoped {
            val deathCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipDestroyCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipient = OH_IPCDeathRecipient_Create(deathCb, recipDestroyCb, null)
            assertNotNull(recipient)
            val addRet = OH_IPCRemoteProxy_AddDeathRecipient(null, recipient)
            assertNotNull(addRet)
            logLine("OH_IPCRemoteProxy_AddDeathRecipient ret=$addRet")
            OH_IPCDeathRecipient_Destroy(recipient)
        }
    }

    @Test
    fun testOH_IPCRemoteProxy_RemoveDeathRecipient() {
        memScoped {
            val deathCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipDestroyCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipient = OH_IPCDeathRecipient_Create(deathCb, recipDestroyCb, null)
            assertNotNull(recipient)
            val removeRet = OH_IPCRemoteProxy_RemoveDeathRecipient(null, recipient)
            assertNotNull(removeRet)
            logLine("OH_IPCRemoteProxy_RemoveDeathRecipient ret=$removeRet")
            OH_IPCDeathRecipient_Destroy(recipient)
        }
    }

    @Test
    fun testOH_IPCDeathRecipient_Destroy() {
        memScoped {
            val deathCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipDestroyCb = staticCFunction { _userData: COpaquePointer? -> }
            val recipient = OH_IPCDeathRecipient_Create(deathCb, recipDestroyCb, null)
            assertNotNull(recipient)
            OH_IPCDeathRecipient_Destroy(recipient)
            logLine("OH_IPCDeathRecipient_Destroy done")
        }
    }

    @Test
    fun testOH_IPCRemoteProxy_IsRemoteDead() {
        val dead = OH_IPCRemoteProxy_IsRemoteDead(null)
        assertNotNull(dead)
        logLine("OH_IPCRemoteProxy_IsRemoteDead(null)=$dead")
    }
}
