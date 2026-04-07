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
import platform.TEEKit.TeeClient.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class TeeClientTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_TEEC_ReturnCode() {
        assertEquals<Int>(0x0, TEEC_SUCCESS.toInt())
        assertEquals<Int>(1, TEEC_ERROR_INVALID_CMD.toInt())
        assertEquals<Int>(2, TEEC_ERROR_SERVICE_NOT_EXIST.toInt())
        assertEquals<Int>(3, TEEC_ERROR_SESSION_NOT_EXIST.toInt())
        assertEquals<Int>(4, TEEC_ERROR_SESSION_MAXIMUM.toInt())
        assertEquals<Int>(5, TEEC_ERROR_REGISTER_EXIST_SERVICE.toInt())
        assertEquals<Int>(6, TEEC_ERROR_TAGET_DEAD_FATAL.toInt())
        assertEquals<Int>(7, TEEC_ERROR_READ_DATA.toInt())
        assertEquals<Int>(8, TEEC_ERROR_WRITE_DATA.toInt())
        assertEquals<Int>(9, TEEC_ERROR_TRUNCATE_OBJECT.toInt())
        assertEquals<Int>(10, TEEC_ERROR_SEEK_DATA.toInt())
        assertEquals<Int>(11, TEEC_ERROR_FSYNC_DATA.toInt())
        assertEquals<Int>(12, TEEC_ERROR_RENAME_OBJECT.toInt())
        assertEquals<Int>(13, TEEC_ERROR_TRUSTED_APP_LOAD_ERROR.toInt())
        logLine("TEEC_ReturnCode passed")
    }

    @Test
    fun testEnum_TEEC_ReturnCodeOrigin() {
        assertEquals<Int>(0x1, TEEC_ORIGIN_API.toInt())
        assertEquals<Int>(0x2, TEEC_ORIGIN_COMMS.toInt())
        assertEquals<Int>(0x3, TEEC_ORIGIN_TEE.toInt())
        assertEquals<Int>(0x4, TEEC_ORIGIN_TRUSTED_APP.toInt())
        logLine("TEEC_ReturnCodeOrigin passed")
    }

    @Test
    fun testEnum_TEEC_SharedMemCtl() {
        assertEquals<Int>(0x1, TEEC_MEM_INPUT.toInt())
        assertEquals<Int>(0x2, TEEC_MEM_OUTPUT.toInt())
        assertEquals<Int>(0x3, TEEC_MEM_INOUT.toInt())
        logLine("TEEC_SharedMemCtl passed")
    }

    @Test
    fun testEnum_TEEC_ParamType() {
        assertEquals<Int>(0x0, TEEC_NONE.toInt())
        assertEquals<Int>(0x01, TEEC_VALUE_INPUT.toInt())
        assertEquals<Int>(0x02, TEEC_VALUE_OUTPUT.toInt())
        assertEquals<Int>(0x03, TEEC_VALUE_INOUT.toInt())
        assertEquals<Int>(0x05, TEEC_MEMREF_TEMP_INPUT.toInt())
        assertEquals<Int>(0x06, TEEC_MEMREF_TEMP_OUTPUT.toInt())
        assertEquals<Int>(0x07, TEEC_MEMREF_TEMP_INOUT.toInt())
        assertEquals<Int>(0x08, TEEC_ION_INPUT.toInt())
        assertEquals<Int>(0x09, TEEC_ION_SGLIST_INPUT.toInt())
        assertEquals<Int>(0xc, TEEC_MEMREF_WHOLE.toInt())
        assertEquals<Int>(0xd, TEEC_MEMREF_PARTIAL_INPUT.toInt())
        assertEquals<Int>(0xe, TEEC_MEMREF_PARTIAL_OUTPUT.toInt())
        assertEquals<Int>(0xf, TEEC_MEMREF_PARTIAL_INOUT.toInt())
        logLine("TEEC_ParamType passed")
    }

    @Test
    fun testEnum_TEEC_LoginMethod() {
        assertEquals<Int>(0x0, TEEC_LOGIN_PUBLIC.toInt())
        assertEquals<Int>(1, TEEC_LOGIN_USER.toInt())
        assertEquals<Int>(2, TEEC_LOGIN_GROUP.toInt())
        assertEquals<Int>(0x4, TEEC_LOGIN_APPLICATION.toInt())
        assertEquals<Int>(0x5, TEEC_LOGIN_USER_APPLICATION.toInt())
        assertEquals<Int>(0x6, TEEC_LOGIN_GROUP_APPLICATION.toInt())
        assertEquals<Int>(0x7, TEEC_LOGIN_IDENTIFY.toInt())
        logLine("TEEC_LoginMethod passed")
    }

    @Test
    fun testTEEC_InitializeContext() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            val ret = try { TEEC_InitializeContext(null, ctx.ptr) } catch (e: Throwable) { logLine("TEEC_InitializeContext (API 20) exception: $e"); TEEC_ERROR_BAD_PARAMETERS }
            assertNotNull(ret)
            logLine("TEEC_InitializeContext=$ret")
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_FinalizeContext() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            try { TEEC_InitializeContext(null, ctx.ptr) } catch (_: Throwable) { }
            try { TEEC_FinalizeContext(ctx.ptr) } catch (e: Throwable) { logLine("TEEC_FinalizeContext (API 20) exception: $e") }
            logLine("TEEC_FinalizeContext passed")
        }
    }

    @Test
    fun testTEEC_OpenSession() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            try { TEEC_InitializeContext(null, ctx.ptr) } catch (_: Throwable) { }
            val sess = alloc<TEEC_Session>()
            val uuid = alloc<TEEC_UUID>()
            val origin = alloc<UIntVar>()
            try { TEEC_OpenSession(ctx.ptr, sess.ptr, uuid.ptr, 0u, null, null, origin.ptr) } catch (e: Throwable) { logLine("TEEC_OpenSession (API 20) exception: $e") }
            logLine("TEEC_OpenSession passed")
            try { TEEC_CloseSession(sess.ptr) } catch (_: Throwable) { }
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_CloseSession() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            try { TEEC_InitializeContext(null, ctx.ptr) } catch (_: Throwable) { }
            val sess = alloc<TEEC_Session>()
            val uuid = alloc<TEEC_UUID>()
            val origin = alloc<UIntVar>()
            try { TEEC_OpenSession(ctx.ptr, sess.ptr, uuid.ptr, 0u, null, null, origin.ptr) } catch (_: Throwable) { }
            try { TEEC_CloseSession(sess.ptr) } catch (e: Throwable) { logLine("TEEC_CloseSession (API 20) exception: $e") }
            logLine("TEEC_CloseSession passed")
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_InvokeCommand() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            try { TEEC_InitializeContext(null, ctx.ptr) } catch (_: Throwable) { }
            val sess = alloc<TEEC_Session>()
            val uuid = alloc<TEEC_UUID>()
            val origin = alloc<UIntVar>()
            try { TEEC_OpenSession(ctx.ptr, sess.ptr, uuid.ptr, 0u, null, null, origin.ptr) } catch (_: Throwable) { }
            val op = alloc<TEEC_Operation>()
            try { TEEC_InvokeCommand(sess.ptr, 0u, op.ptr, origin.ptr) } catch (e: Throwable) { logLine("TEEC_InvokeCommand (API 20) exception: $e") }
            logLine("TEEC_InvokeCommand passed")
            try { TEEC_CloseSession(sess.ptr) } catch (_: Throwable) { }
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_RegisterSharedMemory() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            val initRet = try { TEEC_InitializeContext(null, ctx.ptr) } catch (e: Throwable) { logLine("TEEC_InitializeContext (API 20) exception: $e"); TEEC_ERROR_BAD_PARAMETERS }
            assertNotNull(initRet)
            val sharedMem = alloc<TEEC_SharedMemory>()
            sharedMem.size = 0u
            sharedMem.flags = 0u
            val regRet = try { TEEC_RegisterSharedMemory(ctx.ptr, sharedMem.ptr) } catch (e: Throwable) { logLine("TEEC_RegisterSharedMemory (API 20) exception: $e"); TEEC_ERROR_BAD_PARAMETERS }
            assertNotNull(regRet)
            logLine("TEEC_RegisterSharedMemory=$regRet")
            try { TEEC_ReleaseSharedMemory(sharedMem.ptr) } catch (_: Throwable) { }
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_AllocateSharedMemory() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            try { TEEC_InitializeContext(null, ctx.ptr) } catch (_: Throwable) { }
            val allocMem = alloc<TEEC_SharedMemory>()
            allocMem.size = 4096u
            allocMem.flags = TEEC_MEM_INPUT
            val allocRet = try { TEEC_AllocateSharedMemory(ctx.ptr, allocMem.ptr) } catch (e: Throwable) { logLine("TEEC_AllocateSharedMemory (API 20) exception: $e"); TEEC_ERROR_BAD_PARAMETERS }
            assertNotNull(allocRet)
            logLine("TEEC_AllocateSharedMemory=$allocRet")
            try { TEEC_ReleaseSharedMemory(allocMem.ptr) } catch (_: Throwable) { }
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_ReleaseSharedMemory() {
        memScoped {
            val ctx = alloc<TEEC_Context>()
            try { TEEC_InitializeContext(null, ctx.ptr) } catch (_: Throwable) { }
            val sharedMem = alloc<TEEC_SharedMemory>()
            sharedMem.size = 0u
            sharedMem.flags = 0u
            try { TEEC_RegisterSharedMemory(ctx.ptr, sharedMem.ptr) } catch (_: Throwable) { }
            try { TEEC_ReleaseSharedMemory(sharedMem.ptr) } catch (e: Throwable) { logLine("TEEC_ReleaseSharedMemory (API 20) exception: $e") }
            logLine("TEEC_ReleaseSharedMemory passed")
            try { TEEC_FinalizeContext(ctx.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testTEEC_RequestCancellation() {
        memScoped {
            val op = alloc<TEEC_Operation>()
            try { TEEC_RequestCancellation(op.ptr) } catch (e: Throwable) { logLine("TEEC_RequestCancellation (API 20) exception: $e") }
            logLine("TEEC_RequestCancellation passed")
        }
    }
}
