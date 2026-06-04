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
// DISABLE_NATIVE: gcType=NOOP
// TARGET_BACKEND: NATIVE
// SR006: RegistDumpListenerIfNeeded / OH_HiDebug_RegisterMemDumpListener (commits 6b25f82..faffb199).
// Black-box: mirror Runtime.cpp callback; exercise Debugging.dumpMemory on DUMP_SNAPSHOT path.
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.ExperimentalStdlibApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.test.*
import kotlin.native.runtime.Debugging
import kotlin.native.runtime.GC
import kotlin.native.runtime.NativeRuntimeApi
import kotlinx.cinterop.*
import platform.posix.*

/**
 * Tests for OHOS SR006: register HiDebug memory-dump listener during [initRuntime].
 *
 * Runtime (KONAN_OHOS, Runtime.cpp):
 *   RegistDumpListenerIfNeeded() at end of initRuntime()
 *   - skip when OH_GetSdkApiVersion() < OHOS_DUMPLISTNER_MIN_API (26)
 *   - skip when OH_HiDebug_RegisterMemDumpListener weak symbol is null
 *   - OH_HiDebug_RegisterMemDumpListener("KMP", callback)
 *   - callback: DO_NOTHING/RUNNING_GC -> true; DUMP_SNAPSHOT + !mayReportToOEM -> Debugging.dumpMemory(fd)
 *   - log HIDEBUG_SUCCESS vs failure
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
    NativeRuntimeApi::class,
)
class OhosMemDumpListenerTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- Mirrors Runtime.cpp / hidebug.h (API 26+) ----------

    private val ohosDumpListenerMinApi = 26
    private val memDumpListenerName = "KMP"

    /** OH_HiDebug_MemListenerType (@since API 26). */
    private val memListenerDoNothing = 0
    private val memListenerRunningGc = 1
    private val memListenerDumpSnapshot = 2

    /** OH_HiDebug_ErrorCode.HIDEBUG_SUCCESS */
    private val hidebugSuccess = 0

    /**
     * Mirrors RegistDumpListenerIfNeeded early exits (current Runtime.cpp):
     * register only when api >= 26 and weak symbol OH_HiDebug_RegisterMemDumpListener is resolved.
     */
    private fun shouldRegisterMemDumpListener(sdkApiVersion: Int, symbolResolved: Boolean): Boolean =
        sdkApiVersion >= ohosDumpListenerMinApi && symbolResolved

    /**
     * Mirrors the lambda passed to OH_HiDebug_RegisterMemDumpListener in Runtime.cpp.
     * Return value is propagated to hidumper (dumpMemory bool on DUMP_SNAPSHOT).
     */
    private fun memDumpListenerCallback(
        fd: Int,
        tag: Int,
        mayReportToOEM: Boolean,
        dumpMemory: (Long) -> Boolean,
    ): Boolean = when (tag) {
        memListenerDoNothing -> true
        memListenerRunningGc -> true
        memListenerDumpSnapshot ->
            if (!mayReportToOEM) dumpMemory(fd.toLong()) else true
        else -> true
    }

    private fun dumpToTmpFile(): Pair<Boolean, Long> {
        val file = tmpfile()
        assertNotNull(file)
        val fd = fileno(file)
        assertTrue(fd >= 0)
        val ok = Debugging.dumpMemory(fd.toLong())
        fflush(file)
        fseek(file, 0, SEEK_END)
        val size = ftell(file)
        fclose(file)
        return ok to size
    }

    // ---------- Constants & registration gate ----------

    @Test
    fun testDumpListenerMinApiConstant() {
        assertEquals(26, ohosDumpListenerMinApi)
    }

    @Test
    fun testMemDumpListenerNameConstant() {
        assertEquals("KMP", memDumpListenerName)
    }

    @Test
    fun testMemListenerTypeConstants_api26() {
        assertEquals(0, memListenerDoNothing)
        assertEquals(1, memListenerRunningGc)
        assertEquals(2, memListenerDumpSnapshot)
    }

    @Test
    fun testHidebugSuccessConstant() {
        assertEquals(0, hidebugSuccess)
    }

    @Test
    fun testShouldRegisterMemDumpListener_apiGate() {
        assertFalse(shouldRegisterMemDumpListener(25, true))
        assertFalse(shouldRegisterMemDumpListener(26, false))
        assertTrue(shouldRegisterMemDumpListener(26, true))
        assertTrue(shouldRegisterMemDumpListener(30, true))
        logLine("registration gate mirror ok")
    }

    // ---------- Callback branches (Runtime.cpp switch) ----------

    @Test
    fun testCallback_doNothingAndRunningGc_noDump() {
        var dumpInvoked = false
        val dump: (Long) -> Boolean = { dumpInvoked = true; true }
        assertTrue(memDumpListenerCallback(-1, memListenerDoNothing, false, dump))
        assertTrue(memDumpListenerCallback(-1, memListenerRunningGc, false, dump))
        assertFalse(dumpInvoked)
    }

    @Test
    fun testCallback_dumpSnapshot_invokesDumpWhenNotOemReport() {
        var capturedFd = -1
        val dump: (Long) -> Boolean = { fd ->
            capturedFd = fd.toInt()
            true
        }
        assertTrue(memDumpListenerCallback(42, memListenerDumpSnapshot, false, dump))
        assertEquals(42, capturedFd)
    }

    @Test
    fun testCallback_dumpSnapshot_skipsDumpWhenMayReportToOem() {
        var dumpInvoked = false
        val dump: (Long) -> Boolean = { dumpInvoked = true; true }
        assertTrue(memDumpListenerCallback(42, memListenerDumpSnapshot, true, dump))
        assertFalse(dumpInvoked)
    }

    @Test
    fun testCallback_unknownTag_returnsTrue() {
        var dumpInvoked = false
        val dump: (Long) -> Boolean = { dumpInvoked = true; true }
        assertTrue(memDumpListenerCallback(1, 99, false, dump))
        assertFalse(dumpInvoked)
    }

    // ---------- End-to-end dumpMemory on DUMP_SNAPSHOT path ----------

    @Test
    fun testCallback_dumpSnapshot_withDebuggingDumpMemory() {
        val file = tmpfile()
        assertNotNull(file)
        val fd = fileno(file)
        assertTrue(fd >= 0)
        val ok = memDumpListenerCallback(fd, memListenerDumpSnapshot, false) {
            Debugging.dumpMemory(it)
        }
        assertTrue(ok)
        fflush(file)
        fseek(file, 0, SEEK_END)
        assertTrue(ftell(file) > 0L)
        fclose(file)
        logLine("DUMP_SNAPSHOT -> Debugging.dumpMemory ok")
    }

    @Test
    fun testDebuggingDumpMemory_producesNonEmptyGzipDump() {
        GC.collect()
        val (ok, size) = dumpToTmpFile()
        assertTrue(ok)
        assertTrue(size > 0L)
    }

    @Test
    fun testDumpSnapshotPath_afterGcCollect() {
        GC.collect()
        var ok = false
        val file = tmpfile()
        assertNotNull(file)
        val fd = fileno(file)
        ok = memDumpListenerCallback(fd, memListenerDumpSnapshot, false) {
            Debugging.dumpMemory(it)
        }
        assertTrue(ok)
        fclose(file)
    }

    /**
     * Black-box stand-in for initRuntime() having called RegistDumpListenerIfNeeded:
     * STANDALONE ktest already initialized the runtime; we only verify the listener contract
     * that init registration relies on (API gate + dumpMemory on DUMP_SNAPSHOT).
     */
    @Test
    fun testRuntimeInitListenerContract_mirror() {
        assertTrue(shouldRegisterMemDumpListener(ohosDumpListenerMinApi, true))
        val (dumpOk, _) = dumpToTmpFile()
        assertTrue(dumpOk, "dump path used by registered listener must succeed")
        logLine("init-time listener contract ok name=$memDumpListenerName")
    }

    @Test
    fun testRegistrationResultLogging_mirrorSuccessCode() {
        val simulatedSuccess = hidebugSuccess
        val simulatedFailure = -1
        assertEquals(0, simulatedSuccess)
        assertTrue(simulatedFailure != hidebugSuccess)
        logLine("registration result codes mirrored (runtime logs on HIDEBUG_SUCCESS)")
    }
}
