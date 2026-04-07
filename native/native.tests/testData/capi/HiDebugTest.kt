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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.PerformanceAnalysisKit.HiDebug.*

/**
 * HiDebug C API：每个函数仅踩一次，分多组 @Test；不单独测结构体。
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HiDebugTest {

    private fun logLine(message: String) = println("[stdout] HiDebugTest $message")

    @Test
    fun testEnum_HiDebug_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HIDEBUG_SUCCESS", HIDEBUG_SUCCESS.toInt(), 0)
        p("HIDEBUG_INVALID_ARGUMENT", HIDEBUG_INVALID_ARGUMENT.toInt(), 401)
        p("HIDEBUG_TRACE_CAPTURED_ALREADY", HIDEBUG_TRACE_CAPTURED_ALREADY.toInt(), 11400102)
        p("HIDEBUG_NO_PERMISSION", HIDEBUG_NO_PERMISSION.toInt(), 11400103)
        p("HIDEBUG_TRACE_ABNORMAL", HIDEBUG_TRACE_ABNORMAL.toInt(), 11400104)
        p("HIDEBUG_NO_TRACE_RUNNING", HIDEBUG_NO_TRACE_RUNNING.toInt(), 11400105)
        p("HIDEBUG_INVALID_SYMBOLIC_PC_ADDRESS", HIDEBUG_INVALID_SYMBOLIC_PC_ADDRESS.toInt(), 11400200)
        p("HIDEBUG_NOT_SUPPORTED", HIDEBUG_NOT_SUPPORTED.toInt(), 11400300)
        p("HIDEBUG_UNDER_SAMPLING", HIDEBUG_UNDER_SAMPLING.toInt(), 11400301)
        p("HIDEBUG_RESOURCE_UNAVAILABLE", HIDEBUG_RESOURCE_UNAVAILABLE.toInt(), 11400302)
    }

    @Test
    fun testEnum_HiDebug_TraceFlag() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HIDEBUG_TRACE_FLAG_MAIN_THREAD", HIDEBUG_TRACE_FLAG_MAIN_THREAD.toInt(), 1)
        p("HIDEBUG_TRACE_FLAG_ALL_THREADS", HIDEBUG_TRACE_FLAG_ALL_THREADS.toInt(), 2)
    }

    @Test
    fun testEnum_HiDebug_StackFrameType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HIDEBUG_STACK_FRAME_TYPE_JS", HIDEBUG_STACK_FRAME_TYPE_JS.toInt(), 1)
        p("HIDEBUG_STACK_FRAME_TYPE_NATIVE", HIDEBUG_STACK_FRAME_TYPE_NATIVE.toInt(), 2)
    }

    // ---------- CpuUsage ----------

    @Test
    fun testOH_HiDebug_GetSystemCpuUsage() {
        val sysCpu = OH_HiDebug_GetSystemCpuUsage()
        assertNotNull(sysCpu)
        logLine("OH_HiDebug_GetSystemCpuUsage=$sysCpu")
    }

    @Test
    fun testOH_HiDebug_GetAppCpuUsage() {
        val appCpu = OH_HiDebug_GetAppCpuUsage()
        assertNotNull(appCpu)
        logLine("OH_HiDebug_GetAppCpuUsage=$appCpu")
    }

    @Test
    fun testOH_HiDebug_GetAppThreadCpuUsage() {
        memScoped {
            val threadCpu = OH_HiDebug_GetAppThreadCpuUsage()
            assertNotNull(threadCpu)
            logLine("OH_HiDebug_GetAppThreadCpuUsage=ok")
            val threadCpuPtr = alloc<CPointerVar<HiDebug_ThreadCpuUsage>>().apply { value = threadCpu }
            OH_HiDebug_FreeThreadCpuUsage(threadCpuPtr.ptr)
        }
    }

    @Test
    fun testOH_HiDebug_FreeThreadCpuUsage() {
        memScoped {
            val threadCpu = OH_HiDebug_GetAppThreadCpuUsage()
            assertNotNull(threadCpu)
            val threadCpuPtr = alloc<CPointerVar<HiDebug_ThreadCpuUsage>>().apply { value = threadCpu }
            OH_HiDebug_FreeThreadCpuUsage(threadCpuPtr.ptr)
            logLine("OH_HiDebug_FreeThreadCpuUsage=called")
        }
    }

    // ---------- MemoryInfo ----------

    @Test
    fun testOH_HiDebug_GetSystemMemInfo() {
        memScoped {
            val systemMemInfo = alloc<HiDebug_SystemMemInfo>()
            OH_HiDebug_GetSystemMemInfo(systemMemInfo.ptr)
            logLine("OH_HiDebug_GetSystemMemInfo=called")
        }
    }

    @Test
    fun testOH_HiDebug_GetAppNativeMemInfo() {
        memScoped {
            val nativeMemInfo = alloc<HiDebug_NativeMemInfo>()
            OH_HiDebug_GetAppNativeMemInfo(nativeMemInfo.ptr)
            logLine("OH_HiDebug_GetAppNativeMemInfo=called")
        }
    }

    @Test
    fun testOH_HiDebug_GetAppNativeMemInfoWithCache() {
        memScoped {
            val nativeMemInfo = alloc<HiDebug_NativeMemInfo>()
            try { OH_HiDebug_GetAppNativeMemInfoWithCache(nativeMemInfo.ptr, false) } catch (e: Throwable) { logLine("OH_HiDebug_GetAppNativeMemInfoWithCache (API 20) exception: $e") }
            logLine("OH_HiDebug_GetAppNativeMemInfoWithCache=called")
        }
    }

    @Test
    fun testOH_HiDebug_GetAppMemoryLimit() {
        memScoped {
            val memoryLimit = alloc<HiDebug_MemoryLimit>()
            OH_HiDebug_GetAppMemoryLimit(memoryLimit.ptr)
            logLine("OH_HiDebug_GetAppMemoryLimit=called")
        }
    }

    // ---------- TraceAndGraphics ----------

    @Test
    fun testOH_HiDebug_StartAppTraceCapture() {
        memScoped {
            val fileNameBuf = allocArray<ByteVar>(256)
            val rc = OH_HiDebug_StartAppTraceCapture(
                HIDEBUG_TRACE_FLAG_MAIN_THREAD,
                0uL,
                1024u,
                fileNameBuf,
                256u
            )
            assertNotNull(rc)
            logLine("OH_HiDebug_StartAppTraceCapture=$rc")
            OH_HiDebug_StopAppTraceCapture()
        }
    }

    @Test
    fun testOH_HiDebug_StopAppTraceCapture() {
        val rc = OH_HiDebug_StopAppTraceCapture()
        assertNotNull(rc)
        logLine("OH_HiDebug_StopAppTraceCapture=$rc")
    }

    @Test
    fun testOH_HiDebug_GetGraphicsMemory() {
        memScoped {
            val graphicsMem = alloc<UIntVar>()
            val rc = OH_HiDebug_GetGraphicsMemory(graphicsMem.ptr)
            assertNotNull(rc)
            logLine("OH_HiDebug_GetGraphicsMemory=$rc")
        }
    }

    // ---------- MallocDispatch (API 20) ----------

    @Test
    fun testOH_HiDebug_GetDefaultMallocDispatchTable() {
        val defaultDispatch = try { OH_HiDebug_GetDefaultMallocDispatchTable() } catch (e: Throwable) { logLine("OH_HiDebug_GetDefaultMallocDispatchTable (API 20) exception: $e"); null }
        logLine("OH_HiDebug_GetDefaultMallocDispatchTable=ok")
    }

    @Test
    fun testOH_HiDebug_SetMallocDispatchTable() {
        memScoped {
            val defaultDispatch = try { OH_HiDebug_GetDefaultMallocDispatchTable() } catch (e: Throwable) { logLine("OH_HiDebug_GetDefaultMallocDispatchTable (API 20) exception: $e"); null }
            val rc = try { OH_HiDebug_SetMallocDispatchTable(defaultDispatch) } catch (e: Throwable) { logLine("OH_HiDebug_SetMallocDispatchTable (API 20) exception: $e"); HIDEBUG_INVALID_ARGUMENT }
            logLine("OH_HiDebug_SetMallocDispatchTable=$rc")
            try { OH_HiDebug_RestoreMallocDispatchTable() } catch (e: Throwable) { logLine("OH_HiDebug_RestoreMallocDispatchTable (API 20) exception: $e") }
        }
    }

    @Test
    fun testOH_HiDebug_RestoreMallocDispatchTable() {
        memScoped {
            val defaultDispatch = try { OH_HiDebug_GetDefaultMallocDispatchTable() } catch (e: Throwable) { logLine("OH_HiDebug_GetDefaultMallocDispatchTable (API 20) exception: $e"); null }
            try { OH_HiDebug_SetMallocDispatchTable(defaultDispatch) } catch (e: Throwable) { }
            try { OH_HiDebug_RestoreMallocDispatchTable() } catch (e: Throwable) { logLine("OH_HiDebug_RestoreMallocDispatchTable (API 20) exception: $e") }
            logLine("OH_HiDebug_RestoreMallocDispatchTable=called")
        }
    }

    // ---------- Backtrace (API 20) ----------

    @Test
    fun testOH_HiDebug_CreateBacktraceObject() {
        memScoped {
            val backtraceObj = try { OH_HiDebug_CreateBacktraceObject() } catch (e: Throwable) { logLine("OH_HiDebug_CreateBacktraceObject (API 20) exception: $e"); null }
            logLine("OH_HiDebug_CreateBacktraceObject=ok")
            try { OH_HiDebug_DestroyBacktraceObject(backtraceObj) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiDebug_BacktraceFromFp() {
        memScoped {
            val backtraceObj = try { OH_HiDebug_CreateBacktraceObject() } catch (e: Throwable) { logLine("OH_HiDebug_CreateBacktraceObject (API 20) exception: $e"); null }
            val pcArray = allocArray<COpaquePointerVar>(16)
            val n = try { OH_HiDebug_BacktraceFromFp(backtraceObj, null, pcArray, 16) } catch (e: Throwable) { logLine("OH_HiDebug_BacktraceFromFp (API 20) exception: $e"); -1 }
            logLine("OH_HiDebug_BacktraceFromFp n=$n")
            try { OH_HiDebug_DestroyBacktraceObject(backtraceObj) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiDebug_SymbolicAddress() {
        memScoped {
            val backtraceObj = try { OH_HiDebug_CreateBacktraceObject() } catch (e: Throwable) { logLine("OH_HiDebug_CreateBacktraceObject (API 20) exception: $e"); null }
            val rc = try { OH_HiDebug_SymbolicAddress(backtraceObj, null, null, null) } catch (e: Throwable) { logLine("OH_HiDebug_SymbolicAddress (API 20) exception: $e"); HIDEBUG_INVALID_ARGUMENT }
            logLine("OH_HiDebug_SymbolicAddress=$rc")
            try { OH_HiDebug_DestroyBacktraceObject(backtraceObj) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_HiDebug_DestroyBacktraceObject() {
        memScoped {
            val backtraceObj = try { OH_HiDebug_CreateBacktraceObject() } catch (e: Throwable) { logLine("OH_HiDebug_CreateBacktraceObject (API 20) exception: $e"); null }
            try { OH_HiDebug_DestroyBacktraceObject(backtraceObj) } catch (e: Throwable) { logLine("OH_HiDebug_DestroyBacktraceObject (API 20) exception: $e") }
            logLine("OH_HiDebug_DestroyBacktraceObject=called")
        }
    }

    @Test
    fun testOH_HiDebug_DestroyBacktraceObject_null() {
        try { OH_HiDebug_DestroyBacktraceObject(null) } catch (e: Throwable) { logLine("OH_HiDebug_DestroyBacktraceObject(null) (API 20) exception: $e") }
        logLine("OH_HiDebug_DestroyBacktraceObject(null)=called")
    }

    // ---------- GraphicsSummary / Sampling (API 21/22) ----------

    @Test
    fun testOH_HiDebug_GetGraphicsMemorySummary() {
        memScoped {
            val graphicsSummary = alloc<HiDebug_GraphicsMemorySummary>()
            val rc = try { OH_HiDebug_GetGraphicsMemorySummary(300u, graphicsSummary.ptr) } catch (e: Throwable) { logLine("OH_HiDebug_GetGraphicsMemorySummary (API 21) exception: $e"); HIDEBUG_INVALID_ARGUMENT }
            assertNotNull(rc)
            logLine("OH_HiDebug_GetGraphicsMemorySummary=$rc")
        }
    }

    @Test
    fun testOH_HiDebug_RequestThreadLiteSampling() {
        memScoped {
            val samplerConfig = alloc<HiDebug_ProcessSamplerConfig>().apply {
                tids = null
                size = 0u
                frequency = 0u
                duration = 0u
                reserved = 0u
            }
            val rc = try { OH_HiDebug_RequestThreadLiteSampling(samplerConfig.ptr, null) } catch (e: Throwable) { logLine("OH_HiDebug_RequestThreadLiteSampling (API 22) exception: $e"); HIDEBUG_INVALID_ARGUMENT }
            assertNotNull(rc)
            logLine("OH_HiDebug_RequestThreadLiteSampling=$rc")
        }
    }
}
