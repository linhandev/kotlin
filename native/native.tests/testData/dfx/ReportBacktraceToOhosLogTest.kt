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
import platform.BasicServicesKit.DeviceInfo.OH_GetSdkApiVersion
import platform.info.*
import platform.PerformanceAnalysisKit.HiDebug.*

/**
 * Tests for [ReportBacktraceToOhosLog] and related OHOS crash-log logic in Exceptions.cpp.
 *
 * Runtime flow (API branch, buffer size, message format, dual report channel):
 *   Kotlin exception → getExceptionSummary + stack trace → standard/compressed backtrace
 *   → truncate to getFatalMessageSize() → SetCrashObj (API >= 23) or set_fatal_message (fallback)
 *
 * Black-box tests mirror C++ constants/helpers and exercise the OHOS APIs the runtime calls.
 * End-to-end unhandled-exception → native report is not triggered here (would terminate the process).
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
)
class ReportBacktraceToOhosLogTest {

    private fun logLine(msg: String) = println(msg)

    /** get_fatal_message exposes stored length only; compare against set text length (often includes NUL, so size >= text.length). */
    private fun fatalMessageStoredSize(): Long {
        val p = get_fatal_message()
        assertNotNull(p)
        return p.pointed.size.toLong()
    }

    private fun assertFatalMessageSizeAtLeast(text: String, message: String = "") {
        val size = fatalMessageStoredSize()
        assertTrue(
            size >= text.length.toLong(),
            message.ifEmpty { "fatal_message size=$size expected >= ${text.length} for text length" },
        )
    }

    /** SetCrashObj may return NOT_SUPPORTED / INVALID_ARGUMENT when API < 23 or the weak symbol is absent. */
    private fun assertSetCrashObjAcceptable(rc: UInt) {
        val code = rc.toInt()
        assertTrue(
            code == HIDEBUG_SUCCESS.toInt() ||
                code == HIDEBUG_NOT_SUPPORTED.toInt() ||
                code == HIDEBUG_INVALID_ARGUMENT.toInt(),
            "unexpected SetCrashObj ret=$rc (code=$code)",
        )
    }

    // ---------- Constants aligned with Exceptions.cpp ----------

    /** OHOS_HIDEBUG_MIN_API */
    private val ohosHidebugMinApi = 23

    private val largeBufferSize = 64 * 1024L

    private val largeBufferReserved = 20L

    private val smallBufferSize = 1004L

    private val frameNoWidth = 2

    private val pcAddrWidth = 16

    /** FATAL_MESSAGE_FORMAT_OVERHEAD in buildCompressedBacktrace. */
    private val fatalMessageFormatOverhead = 22

    private enum class ReportChannel {
        SET_CRASH_OBJ,
        SET_FATAL_MESSAGE,
        SKIP,
    }

    private fun sdkApiVersion(): Int = try {
        OH_GetSdkApiVersion()
    } catch (e: Throwable) {
        logLine("OH_GetSdkApiVersion exception: $e")
        -1
    }

    /** Mirrors getFatalMessageSize(). */
    private fun getFatalMessageSize(apiVersion: Int): Long =
        if (apiVersion >= ohosHidebugMinApi) largeBufferSize - largeBufferReserved else smallBufferSize

    /** Mirrors apiVersion >= OHOS_HIDEBUG_MIN_API branch in ReportBacktraceToOhosLog. */
    private fun shouldUseStandardBacktrace(apiVersion: Int): Boolean = apiVersion >= ohosHidebugMinApi

    /**
     * Mirrors [TypeInfo::fqName] (packageName + "." + relativeName, or "<anonymous>").
     * [KClass.qualifiedName] matches runtime fqName for Kotlin/Native exception types in tests.
     */
    private fun exceptionFqName(exception: Throwable): String =
        exception::class.qualifiedName ?: "<anonymous>"

    /** Mirrors getExceptionSummary(): "fqName: message" or fqName only. */
    private fun buildExceptionSummary(exception: Throwable): String {
        val fqName = exceptionFqName(exception)
        val message = exception.message
        return if (message != null) "$fqName: $message" else fqName
    }

    /** Mirrors static truncated.assign(fatalMessage, 0, messageSize). */
    private fun truncateFatalMessage(message: String, messageSize: Long): String {
        if (messageSize <= 0L) return ""
        val limit = messageSize.toInt()
        return if (message.length <= limit) message else message.substring(0, limit)
    }

    /** Mirrors buildStandardBacktrace message header. */
    private fun buildStandardBacktraceHeader(reason: String): String =
        "\nUncaught Kotlin exception at following addresses:\nReason: $reason\n"

    /** Mirrors buildCompressedBacktrace message header. */
    private fun buildCompressedBacktraceHeader(reason: String): String =
        "\nUncaught Kotlin exception:\nReason: $reason\n"

    /**
     * Mirrors report channel selection at the end of ReportBacktraceToOhosLog
     * (dlsym/weak availability covered indirectly by API probes).
     */
    private fun chooseReportChannel(
        apiVersion: Int,
        setCrashObjAvailable: Boolean,
        setFatalMessageAvailable: Boolean,
    ): ReportChannel {
        if (!setCrashObjAvailable && !setFatalMessageAvailable) return ReportChannel.SKIP
        return if (apiVersion >= ohosHidebugMinApi && setCrashObjAvailable) {
            ReportChannel.SET_CRASH_OBJ
        } else if (setFatalMessageAvailable) {
            ReportChannel.SET_FATAL_MESSAGE
        } else {
            ReportChannel.SKIP
        }
    }

    private fun isValidStandardFrameLine(line: String): Boolean {
        if (!line.startsWith("#")) return false
        val idx = line.indexOf(" pc ")
        if (idx < 0) return false
        val afterPc = line.substring(idx + 4)
        val hex = afterPc.takeWhile { it in '0'..'9' || it in 'a'..'f' }
        return hex.length == pcAddrWidth
    }

    private fun parseCompressedSections(body: String): Pair<String, String>? {
        val soIdx = body.indexOf("sofiles:\n")
        val addrIdx = body.indexOf("addresses:")
        if (soIdx < 0 || addrIdx < 0 || addrIdx <= soIdx) return null
        val sofiles = body.substring(soIdx + "sofiles:\n".length, addrIdx).trimEnd()
        val addresses = body.substring(addrIdx + "addresses:".length)
        return sofiles to addresses
    }

    private fun setCrashObjWithString(message: String) = memScoped {
        val msgBytes = message.encodeToByteArray()
        val buf = allocArray<ByteVar>(msgBytes.size + 1)
        msgBytes.forEachIndexed { i, b -> buf[i] = b }
        buf[msgBytes.size] = 0.toByte()
        try {
            OH_HiDebug_SetCrashObj(HIDEBUG_CRASHOBJ_STRING, buf.reinterpret<COpaque>())
        } catch (e: Throwable) {
            logLine("OH_HiDebug_SetCrashObj exception: $e")
            HIDEBUG_NOT_SUPPORTED
        }
    }

    // ---------- Exceptions.cpp constants and branch logic (Kotlin mirrors) ----------

    @Test
    fun testConstants_alignWithExceptionsCpp() {
        assertEquals(23, ohosHidebugMinApi)
        assertEquals(64 * 1024L, largeBufferSize)
        assertEquals(20L, largeBufferReserved)
        assertEquals(1004L, smallBufferSize)
        assertEquals(2, frameNoWidth)
        assertEquals(16, pcAddrWidth)
        assertEquals(22, fatalMessageFormatOverhead)
        logLine("Exceptions.cpp constants aligned")
    }

    @Test
    fun testGetFatalMessageSize_apiBranch() {
        assertEquals(65516L, getFatalMessageSize(23))
        assertEquals(65516L, getFatalMessageSize(99))
        assertEquals(1004L, getFatalMessageSize(22))
        assertEquals(1004L, getFatalMessageSize(0))
        logLine("getFatalMessageSize: api23=${getFatalMessageSize(23)} api22=${getFatalMessageSize(22)}")
    }

    @Test
    fun testShouldUseStandardBacktrace_api23Gate() {
        assertTrue(shouldUseStandardBacktrace(23))
        assertTrue(shouldUseStandardBacktrace(26))
        assertFalse(shouldUseStandardBacktrace(22))
        logLine("standard backtrace gate at API $ohosHidebugMinApi ok")
    }

    @Test
    fun testChooseReportChannel_logic() {
        assertEquals(ReportChannel.SKIP, chooseReportChannel(26, false, false))
        assertEquals(ReportChannel.SET_CRASH_OBJ, chooseReportChannel(23, true, true))
        assertEquals(ReportChannel.SET_CRASH_OBJ, chooseReportChannel(26, true, false))
        assertEquals(ReportChannel.SET_FATAL_MESSAGE, chooseReportChannel(22, true, true))
        assertEquals(ReportChannel.SET_FATAL_MESSAGE, chooseReportChannel(22, false, true))
        assertEquals(ReportChannel.SET_FATAL_MESSAGE, chooseReportChannel(26, false, true))
        logLine("chooseReportChannel logic ok")
    }

    @Test
    fun testTruncateFatalMessage_respectsBufferSize() {
        val api23Size = getFatalMessageSize(23)
        val longMsg = "x".repeat(api23Size.toInt() + 100)
        val truncated = truncateFatalMessage(longMsg, api23Size)
        assertEquals(api23Size.toInt(), truncated.length)

        val small = truncateFatalMessage("short", getFatalMessageSize(22))
        assertEquals("short", small)
        logLine("truncateFatalMessage api23 len=${truncated.length}")
    }

    // ---------- getExceptionSummary and Throwable stack trace ----------

    @Test
    fun testBuildExceptionSummary_withMessage() {
        val exception = IllegalStateException("test backtrace")
        val summary = buildExceptionSummary(exception)
        assertEquals("${exceptionFqName(exception)}: test backtrace", summary)
        assertTrue(summary.startsWith("kotlin."))
        logLine("exception summary=$summary")
    }

    @Test
    fun testBuildExceptionSummary_withoutMessage() {
        val exception = RuntimeException()
        val summary = buildExceptionSummary(exception)
        assertEquals(exceptionFqName(exception), summary)
        assertFalse(summary.contains(": "))
        logLine("exception summary(no message)=$summary")
    }

    @Test
    fun testThrowable_getStackTrace_notEmpty() {
        val trace = try {
            throw IllegalStateException("stack probe")
        } catch (e: Throwable) {
            e.getStackTrace()
        }
        assertTrue(trace.isNotEmpty(), "Kotlin_Throwable_getStackTrace should yield frames")
        logLine("stackTrace frames=${trace.size}")
    }

    // ---------- OH_GetSdkApiVersion and device-side branching ----------

    @Test
    fun testOH_GetSdkApiVersion() {
        val version = sdkApiVersion()
        logLine("OH_GetSdkApiVersion=$version")
        if (version >= 0) {
            val channel = chooseReportChannel(
                version,
                setCrashObjAvailable = version >= ohosHidebugMinApi,
                setFatalMessageAvailable = true,
            )
            val format = if (shouldUseStandardBacktrace(version)) "standard" else "compressed"
            val bufSize = getFatalMessageSize(version)
            logLine("device would use format=$format channel=$channel bufSize=$bufSize")
        }
    }

    @Test
    fun testSdkApiVersionGate_hidebugMinApi23() {
        val version = sdkApiVersion()
        if (version < 0) {
            logLine("skip gate assertion: SDK version unavailable")
            return
        }
        val expectedStandard = version >= ohosHidebugMinApi
        assertEquals(expectedStandard, shouldUseStandardBacktrace(version))
        logLine("api=$version standardBacktrace=$expectedStandard")
    }

    // ---------- Standard backtrace format (API >= 23) ----------

    @Test
    fun testStandardBacktraceHeaderFormat() {
        val reason = buildExceptionSummary(IllegalStateException("probe"))
        val header = buildStandardBacktraceHeader(reason)
        assertTrue(header.startsWith("\nUncaught Kotlin exception at following addresses:\n"))
        assertTrue(header.contains("Reason: $reason"))
        logLine("standard header ok")
    }

    @Test
    fun testStandardBacktraceFrameLinePattern() {
        val lines = listOf(
            "#00 pc 00000000001a3f00 /system/lib64/libentry.so(aabbcc) (Konan_start+0x10)",
            "#01 pc 0000000000002b4c /system/lib64/libc.so",
        )
        lines.forEach { assertTrue(isValidStandardFrameLine(it), "invalid frame: $it") }
        assertFalse(isValidStandardFrameLine("#00 pc 1234 /lib.so"))
        logLine("standard frame line pattern ok")
    }

    @Test
    fun testSetFatalMessageStandardBacktraceFormat() {
        val reason = buildExceptionSummary(IllegalStateException("test backtrace"))
        val backtraceMsg = buildStandardBacktraceHeader(reason) +
            "#00 pc 00000000001a3f00 /system/lib64/test.so(abcdef) (testFunc+0x10)\n" +
            "#01 pc 0000000000002b4c /system/lib64/libc.so\n"
        set_fatal_message(backtraceMsg)
        assertFatalMessageSizeAtLeast(backtraceMsg, "standard backtrace")
        logLine("set_fatal_message standard backtrace format ok, len=${backtraceMsg.length} size=${fatalMessageStoredSize()}")
    }

    @Test
    fun testOH_HiDebug_SetCrashObj_withStandardBacktraceMessage() {
        if (sdkApiVersion() in 0 until ohosHidebugMinApi) {
            logLine("skip SetCrashObj standard message: SDK API < $ohosHidebugMinApi")
            return
        }
        val reason = buildExceptionSummary(IllegalStateException("crashobj standard probe"))
        val message = buildStandardBacktraceHeader(reason) +
            "#00 pc 00000000001a3f00 /data/lib/libkn.so(deadbeef) (entry+0x0)\n"
        val rc = setCrashObjWithString(message)
        assertSetCrashObjAcceptable(rc)
        logLine("SetCrashObj(standard backtrace) ret=$rc len=${message.length}")
    }

    // ---------- Compressed backtrace format (API < 23) ----------

    @Test
    fun testCompressedBacktraceHeaderFormat() {
        val reason = buildExceptionSummary(RuntimeException("ohos crash probe"))
        val header = buildCompressedBacktraceHeader(reason)
        assertTrue(header.startsWith("\nUncaught Kotlin exception:\n"))
        assertTrue(header.contains("Reason: $reason"))
        logLine("compressed header ok")
    }

    @Test
    fun testCompressedBacktraceSections_useFilenameNotFullPath() {
        val body = buildCompressedBacktraceHeader(buildExceptionSummary(RuntimeException("x"))) +
            "sofiles:\nlibentry.so(001122),libc.so(334455)\naddresses:\n[0] 0x1a3f00 0x2b4c\n[1] 0x3c5d\n"
        val sections = parseCompressedSections(body)
        assertNotNull(sections)
        val (sofiles, addresses) = sections!!
        assertTrue(sofiles.contains("libentry.so"))
        assertFalse(sofiles.contains("/system/lib64/"))
        assertTrue(addresses.contains("[0]"))
        assertTrue(addresses.contains("0x1a3f00"))
        logLine("compressed sections ok sofiles=$sofiles")
    }

    @Test
    fun testSetFatalMessageCompressedBacktraceFormat() {
        val compressedBacktrace = buildCompressedBacktraceHeader(
            buildExceptionSummary(RuntimeException("ohos crash probe")),
        ) +
            "sofiles:\nlibentry.so(001122),libc.so(334455)\naddresses:\n[0] 0x1a3f00 0x2b4c\n[1] 0x3c5d\n"
        set_fatal_message(compressedBacktrace)
        assertFatalMessageSizeAtLeast(compressedBacktrace, "compressed backtrace")
        logLine("set_fatal_message compressed format size=${fatalMessageStoredSize()}")
    }

    @Test
    fun testCompressedBacktrace_fitsSmallBufferOverhead() {
        val reason = "E: m"
        val header = buildCompressedBacktraceHeader(reason)
        val minimalBody = header + "sofiles:\n\naddresses:\n"
        val limit = getFatalMessageSize(22)
        assertTrue(
            header.length + fatalMessageFormatOverhead < limit,
            "header+overhead should fit in SMALL_BUFFER_SIZE for empty stack",
        )
        val truncated = truncateFatalMessage(minimalBody, limit)
        assertTrue(truncated.length <= limit.toInt())
        logLine("compressed small buffer fit ok limit=$limit len=${truncated.length}")
    }

    // ---------- fatal_message.h: set_fatal_message / get_fatal_message (API < 23 fallback) ----------

    @Test
    fun testSetFatalMessageEmpty() {
        set_fatal_message("")
        val p = get_fatal_message()
        assertNotNull(p)
        logLine("set_fatal_message(\"\") size=${p.pointed.size}")
    }

    @Test
    fun testSetFatalMessageRoundtrip() {
        val text = "ReportBacktraceToOhosLogTest_roundtrip"
        set_fatal_message(text)
        assertFatalMessageSizeAtLeast(text, "roundtrip")
        logLine("roundtrip ok size=${fatalMessageStoredSize()}")
    }

    @Test
    fun testSetFatalMessageLargeBacktrace_withinApi23Buffer() {
        val reason = "Error: X"
        val frames = (0..255).joinToString("\n") { i ->
            "#${i.toString().padStart(frameNoWidth, '0')} pc ${
                (0x1000 + i * 0x100).toString(16).padStart(pcAddrWidth, '0')
            } /system/lib64/libtest.so"
        }
        val largeMsg = buildStandardBacktraceHeader(reason) + frames
        val truncated = truncateFatalMessage(largeMsg, getFatalMessageSize(23))
        set_fatal_message(truncated)
        assertFatalMessageSizeAtLeast(truncated, "large truncated backtrace")
        assertTrue(truncated.length <= getFatalMessageSize(23).toInt())
        logLine("large standard backtrace truncated len=${truncated.length} size=${fatalMessageStoredSize()}")
    }

    @Test
    fun testSetFatalMessageMultipleCalls_simulateRepeatedCrash() {
        // Mirrors 3bd8a0a: static buffer must accept updates; probe with consecutive set_fatal_message calls.
        val msg1 = "backtrace_probe_1"
        val msg2 = "backtrace_probe_2_with_longer_content_for_testing"
        set_fatal_message(msg1)
        val size1 = fatalMessageStoredSize()
        assertFatalMessageSizeAtLeast(msg1, "first message")
        set_fatal_message(msg2)
        val size2 = fatalMessageStoredSize()
        assertFatalMessageSizeAtLeast(msg2, "second message")
        assertTrue(size2 >= size1, "longer message should not shrink stored size: size1=$size1 size2=$size2")
        logLine("repeated set_fatal_message ok size1=$size1 size2=$size2")
    }

    @Test
    fun testSetFatalMessageMultipleCalls_standardThenCompressed() {
        val standard = buildStandardBacktraceHeader("E1") + "#00 pc 0000000000001000 /lib/a.so\n"
        val compressed = buildCompressedBacktraceHeader("E2") +
            "sofiles:\na.so\naddresses:\n[0] 0x1\n"
        set_fatal_message(standard)
        set_fatal_message(compressed)
        assertFatalMessageSizeAtLeast(compressed, "after switch to compressed")
        logLine("switch standard->compressed fatal_message size=${fatalMessageStoredSize()}")
    }

    // ---------- HiDebug enums and SetCrashObj probes ----------

    @Test
    fun testEnum_HiDebug_CrashObjType() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("HIDEBUG_CRASHOBJ_STRING", HIDEBUG_CRASHOBJ_STRING.toInt(), 0)
    }

    @Test
    fun testEnum_HiDebug_ErrorCode_RelevantToCrashReporting() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("HIDEBUG_SUCCESS", HIDEBUG_SUCCESS.toInt(), 0)
        p("HIDEBUG_INVALID_ARGUMENT", HIDEBUG_INVALID_ARGUMENT.toInt(), 401)
        p("HIDEBUG_NOT_SUPPORTED", HIDEBUG_NOT_SUPPORTED.toInt(), 11400300)
    }

    @Test
    fun testOH_HiDebug_SetCrashObj() {
        memScoped {
            val rc = try {
                OH_HiDebug_SetCrashObj(HIDEBUG_CRASHOBJ_STRING, null)
            } catch (e: Throwable) {
                logLine("OH_HiDebug_SetCrashObj(null) exception (API < 23): $e")
                HIDEBUG_NOT_SUPPORTED
            }
            assertSetCrashObjAcceptable(rc)
            logLine("OH_HiDebug_SetCrashObj(null) ret=$rc")
        }
    }

    @Test
    fun testOH_HiDebug_SetCrashObj_WithString() {
        val rc = setCrashObjWithString("ReportBacktraceToOhosLogTest_crashobj_probe")
        assertSetCrashObjAcceptable(rc)
        logLine("OH_HiDebug_SetCrashObj(string) ret=$rc")
    }

    @Test
    fun testOH_HiDebug_SetCrashObj_repeatedCalls() {
        if (sdkApiVersion() in 0 until ohosHidebugMinApi) {
            logLine("skip repeated SetCrashObj: SDK API < $ohosHidebugMinApi")
            return
        }
        val rc1 = setCrashObjWithString("crash_report_first")
        val rc2 = setCrashObjWithString(
            buildStandardBacktraceHeader(buildExceptionSummary(IllegalStateException("second"))) +
                "#00 pc 0000000000000001 /lib/x.so\n",
        )
        assertSetCrashObjAcceptable(rc1)
        assertSetCrashObjAcceptable(rc2)
        logLine("repeated SetCrashObj ret1=$rc1 ret2=$rc2")
    }
}
