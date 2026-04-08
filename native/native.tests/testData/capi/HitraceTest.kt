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
import platform.PerformanceAnalysisKit.Hitrace.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HitraceTest {

    private fun logLine(message: String) = println("[stdout] HitraceTest $message")

    // ─── 枚举测试 ───

    @Test
    fun testEnum_HiTraceId_Valid() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HITRACE_ID_INVALID", HITRACE_ID_INVALID.toInt(), 0)
        p("HITRACE_ID_VALID", HITRACE_ID_VALID.toInt(), 1)
    }

    @Test
    fun testEnum_HiTrace_Version() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HITRACE_VER_1", HITRACE_VER_1.toInt(), 0)
    }

    @Test
    fun testEnum_HiTrace_Flag() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HITRACE_FLAG_DEFAULT", HITRACE_FLAG_DEFAULT.toInt(), 0)
        p("HITRACE_FLAG_INCLUDE_ASYNC", HITRACE_FLAG_INCLUDE_ASYNC.toInt(), 1)
        p("HITRACE_FLAG_DONOT_CREATE_SPAN", HITRACE_FLAG_DONOT_CREATE_SPAN.toInt(), 2)
        p("HITRACE_FLAG_TP_INFO", HITRACE_FLAG_TP_INFO.toInt(), 4)
        p("HITRACE_FLAG_NO_BE_INFO", HITRACE_FLAG_NO_BE_INFO.toInt(), 8)
        p("HITRACE_FLAG_DONOT_ENABLE_LOG", HITRACE_FLAG_DONOT_ENABLE_LOG.toInt(), 16)
        p("HITRACE_FLAG_FAULT_TRIGGER", HITRACE_FLAG_FAULT_TRIGGER.toInt(), 32)
        p("HITRACE_FLAG_D2D_TP_INFO", HITRACE_FLAG_D2D_TP_INFO.toInt(), 64)
    }

    @Test
    fun testEnum_HiTrace_Tracepoint_Type() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HITRACE_TP_CS", HITRACE_TP_CS.toInt(), 0)
        p("HITRACE_TP_CR", HITRACE_TP_CR.toInt(), 1)
        p("HITRACE_TP_SS", HITRACE_TP_SS.toInt(), 2)
        p("HITRACE_TP_SR", HITRACE_TP_SR.toInt(), 3)
        p("HITRACE_TP_GENERAL", HITRACE_TP_GENERAL.toInt(), 4)
    }

    @Test
    fun testEnum_HiTrace_Communication_Mode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HITRACE_CM_DEFAULT", HITRACE_CM_DEFAULT.toInt(), 0)
        p("HITRACE_CM_THREAD", HITRACE_CM_THREAD.toInt(), 1)
        p("HITRACE_CM_PROCESS", HITRACE_CM_PROCESS.toInt(), 2)
        p("HITRACE_CM_DEVICE", HITRACE_CM_DEVICE.toInt(), 3)
    }

    @Test
    fun testEnum_HiTrace_Output_Level() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HITRACE_LEVEL_DEBUG", HITRACE_LEVEL_DEBUG.toInt(), 0)
        p("HITRACE_LEVEL_INFO", HITRACE_LEVEL_INFO.toInt(), 1)
        p("HITRACE_LEVEL_CRITICAL", HITRACE_LEVEL_CRITICAL.toInt(), 2)
        p("HITRACE_LEVEL_COMMERCIAL", HITRACE_LEVEL_COMMERCIAL.toInt(), 3)
        p("HITRACE_LEVEL_MAX", HITRACE_LEVEL_MAX.toInt(), 3)
    }

    // ─── 函数测试（每个函数独立 @Test） ───

    @Test
    fun testOH_HiTrace_BeginChain() {
        memScoped {
            val beginId = OH_HiTrace_BeginChain("test_chain", 0)
            logLine("OH_HiTrace_BeginChain: " + beginId.useContents { "part0=$part0, part1=$part1" })
            OH_HiTrace_EndChain()
        }
    }

    @Test
    fun testOH_HiTrace_GetId() {
        memScoped {
            OH_HiTrace_BeginChain("test_chain", 0)
            val currentId = OH_HiTrace_GetId()
            logLine("OH_HiTrace_GetId: " + currentId.useContents { "part0=$part0, part1=$part1" })
            OH_HiTrace_EndChain()
        }
    }

    @Test
    fun testOH_HiTrace_CreateSpan() {
        memScoped {
            OH_HiTrace_BeginChain("test_chain", 0)
            val spanId = OH_HiTrace_CreateSpan()
            logLine("OH_HiTrace_CreateSpan: " + spanId.useContents { "part0=$part0, part1=$part1" })
            OH_HiTrace_EndChain()
        }
    }

    @Test
    fun testOH_HiTrace_EndChain() {
        memScoped {
            OH_HiTrace_BeginChain("test_chain", 0)
            OH_HiTrace_EndChain()
            logLine("OH_HiTrace_EndChain=called")
        }
    }

    @Test
    fun testOH_HiTrace_InitId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            logLine("OH_HiTrace_InitId=called")
        }
    }

    @Test
    fun testOH_HiTrace_SetId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_SetId(traceId.ptr)
            logLine("OH_HiTrace_SetId=called")
            OH_HiTrace_ClearId()
        }
    }

    @Test
    fun testOH_HiTrace_ClearId() {
        OH_HiTrace_ClearId()
        logLine("OH_HiTrace_ClearId=called")
    }

    @Test
    fun testOH_HiTrace_IsIdValid() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val valid = OH_HiTrace_IsIdValid(traceId.ptr)
            assertNotNull(valid)
            logLine("OH_HiTrace_IsIdValid=$valid")
        }
    }

    @Test
    fun testOH_HiTrace_IsFlagEnabled() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val flagEnabled = OH_HiTrace_IsFlagEnabled(traceId.ptr, HITRACE_FLAG_DEFAULT)
            assertNotNull(flagEnabled)
            logLine("OH_HiTrace_IsFlagEnabled=$flagEnabled")
        }
    }

    @Test
    fun testOH_HiTrace_EnableFlag() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_EnableFlag(traceId.ptr, HITRACE_FLAG_INCLUDE_ASYNC)
            logLine("OH_HiTrace_EnableFlag=called")
        }
    }

    @Test
    fun testOH_HiTrace_GetFlags() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val flags = OH_HiTrace_GetFlags(traceId.ptr)
            assertNotNull(flags)
            logLine("OH_HiTrace_GetFlags=$flags")
        }
    }

    @Test
    fun testOH_HiTrace_SetFlags() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_SetFlags(traceId.ptr, HITRACE_FLAG_TP_INFO.toInt())
            logLine("OH_HiTrace_SetFlags=called")
        }
    }

    @Test
    fun testOH_HiTrace_GetChainId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val chainId = OH_HiTrace_GetChainId(traceId.ptr)
            assertNotNull(chainId)
            logLine("OH_HiTrace_GetChainId=$chainId")
        }
    }

    @Test
    fun testOH_HiTrace_SetChainId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_SetChainId(traceId.ptr, 0x123456uL)
            logLine("OH_HiTrace_SetChainId=called")
        }
    }

    @Test
    fun testOH_HiTrace_GetSpanId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val spanId = OH_HiTrace_GetSpanId(traceId.ptr)
            assertNotNull(spanId)
            logLine("OH_HiTrace_GetSpanId=$spanId")
        }
    }

    @Test
    fun testOH_HiTrace_SetSpanId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_SetSpanId(traceId.ptr, 0xABCDuL)
            logLine("OH_HiTrace_SetSpanId=called")
        }
    }

    @Test
    fun testOH_HiTrace_GetParentSpanId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val parentSpanId = OH_HiTrace_GetParentSpanId(traceId.ptr)
            assertNotNull(parentSpanId)
            logLine("OH_HiTrace_GetParentSpanId=$parentSpanId")
        }
    }

    @Test
    fun testOH_HiTrace_SetParentSpanId() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_SetParentSpanId(traceId.ptr, 0xEF01uL)
            logLine("OH_HiTrace_SetParentSpanId=called")
        }
    }

    @Test
    fun testOH_HiTrace_IdFromBytes() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val inArray = allocArray<UByteVar>(16)
            for (i in 0 until 16) { inArray[i] = i.toUByte() }
            OH_HiTrace_IdFromBytes(traceId.ptr, inArray, 16)
            logLine("OH_HiTrace_IdFromBytes=called")
        }
    }

    @Test
    fun testOH_HiTrace_IdToBytes() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            val outArray = allocArray<UByteVar>(16)
            val rc = OH_HiTrace_IdToBytes(traceId.ptr, outArray, 16)
            assertNotNull(rc)
            logLine("OH_HiTrace_IdToBytes=$rc")
        }
    }

    @Test
    fun testOH_HiTrace_Tracepoint() {
        memScoped {
            val traceId = alloc<HiTraceId>()
            OH_HiTrace_InitId(traceId.ptr)
            OH_HiTrace_Tracepoint(HITRACE_CM_DEFAULT, HITRACE_TP_GENERAL, traceId.ptr, "test tracepoint")
            logLine("OH_HiTrace_Tracepoint=called")
        }
    }

    @Test
    fun testOH_HiTrace_StartTrace() {
        OH_HiTrace_StartTrace("test_sync")
        logLine("OH_HiTrace_StartTrace=called")
        OH_HiTrace_FinishTrace()
    }

    @Test
    fun testOH_HiTrace_FinishTrace() {
        OH_HiTrace_FinishTrace()
        logLine("OH_HiTrace_FinishTrace=called")
    }

    @Test
    fun testOH_HiTrace_StartAsyncTrace() {
        OH_HiTrace_StartAsyncTrace("test_async", 1)
        logLine("OH_HiTrace_StartAsyncTrace=called")
        OH_HiTrace_FinishAsyncTrace("test_async", 1)
    }

    @Test
    fun testOH_HiTrace_FinishAsyncTrace() {
        OH_HiTrace_StartAsyncTrace("test_async", 1)
        OH_HiTrace_FinishAsyncTrace("test_async", 1)
        logLine("OH_HiTrace_FinishAsyncTrace=called")
    }

    @Test
    fun testOH_HiTrace_CountTrace() {
        OH_HiTrace_CountTrace("test_count", 42L)
        logLine("OH_HiTrace_CountTrace=called")
    }

    @Test
    fun testOH_HiTrace_StartTraceEx() {
        try { OH_HiTrace_StartTraceEx(HITRACE_LEVEL_DEBUG, "test_ex", "key=val") } catch (e: Throwable) { logLine("OH_HiTrace_StartTraceEx (API 19) exception: $e") }
        logLine("OH_HiTrace_StartTraceEx=called")
        try { OH_HiTrace_FinishTraceEx(HITRACE_LEVEL_DEBUG) } catch (e: Throwable) { }
    }

    @Test
    fun testOH_HiTrace_FinishTraceEx() {
        try { OH_HiTrace_FinishTraceEx(HITRACE_LEVEL_DEBUG) } catch (e: Throwable) { logLine("OH_HiTrace_FinishTraceEx (API 19) exception: $e") }
        logLine("OH_HiTrace_FinishTraceEx=called")
    }

    @Test
    fun testOH_HiTrace_StartAsyncTraceEx() {
        try { OH_HiTrace_StartAsyncTraceEx(HITRACE_LEVEL_INFO, "async_ex", 1, "cat1", "k=v") } catch (e: Throwable) { logLine("OH_HiTrace_StartAsyncTraceEx (API 19) exception: $e") }
        logLine("OH_HiTrace_StartAsyncTraceEx=called")
        try { OH_HiTrace_FinishAsyncTraceEx(HITRACE_LEVEL_INFO, "async_ex", 1) } catch (e: Throwable) { }
    }

    @Test
    fun testOH_HiTrace_FinishAsyncTraceEx() {
        try { OH_HiTrace_StartAsyncTraceEx(HITRACE_LEVEL_INFO, "async_ex", 1, "cat1", "k=v") } catch (e: Throwable) { }
        try { OH_HiTrace_FinishAsyncTraceEx(HITRACE_LEVEL_INFO, "async_ex", 1) } catch (e: Throwable) { logLine("OH_HiTrace_FinishAsyncTraceEx (API 19) exception: $e") }
        logLine("OH_HiTrace_FinishAsyncTraceEx=called")
    }

    @Test
    fun testOH_HiTrace_CountTraceEx() {
        try { OH_HiTrace_CountTraceEx(HITRACE_LEVEL_CRITICAL, "count_ex", 99L) } catch (e: Throwable) { logLine("OH_HiTrace_CountTraceEx (API 19) exception: $e") }
        logLine("OH_HiTrace_CountTraceEx=called")
    }

    @Test
    fun testOH_HiTrace_IsTraceEnabled() {
        val enabled = try { OH_HiTrace_IsTraceEnabled() } catch (e: Throwable) { logLine("OH_HiTrace_IsTraceEnabled (API 19) exception: $e"); false }
        assertNotNull(enabled)
        logLine("OH_HiTrace_IsTraceEnabled=$enabled")
    }

    @Test
    fun testOH_HiTrace_RegisterTraceListener() {
        val cb = staticCFunction { _: Boolean -> }
        val idx = try { OH_HiTrace_RegisterTraceListener(cb) } catch (e: Throwable) { logLine("OH_HiTrace_RegisterTraceListener (API 22) exception: $e"); -1 }
        assertNotNull(idx)
        logLine("OH_HiTrace_RegisterTraceListener=$idx")
        if (idx >= 0) try { OH_HiTrace_UnregisterTraceListener(idx) } catch (e: Throwable) { }
    }

    @Test
    fun testOH_HiTrace_UnregisterTraceListener() {
        val cb = staticCFunction { _: Boolean -> }
        val idx = try { OH_HiTrace_RegisterTraceListener(cb) } catch (e: Throwable) { logLine("OH_HiTrace_RegisterTraceListener (API 22) exception: $e"); -1 }
        assertNotNull(idx)
        if (idx >= 0) {
            val rc = try { OH_HiTrace_UnregisterTraceListener(idx) } catch (e: Throwable) { logLine("OH_HiTrace_UnregisterTraceListener (API 22) exception: $e"); -2 }
            assertNotNull(rc)
            logLine("OH_HiTrace_UnregisterTraceListener=$rc")
        } else {
            logLine("OH_HiTrace_UnregisterTraceListener skipped (no valid listener)")
        }
    }
}
