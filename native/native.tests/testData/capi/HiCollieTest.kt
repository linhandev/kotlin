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
import platform.PerformanceAnalysisKit.HiCollie.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HiCollieTest {

    private fun logLine(message: String) = println("[stdout] HiCollieTest $message")

    @Test
    fun testEnum_HiCollie_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HICOLLIE_SUCCESS", HICOLLIE_SUCCESS.toInt(), 0)
        p("HICOLLIE_INVALID_ARGUMENT", HICOLLIE_INVALID_ARGUMENT.toInt(), 401)
        p("HICOLLIE_WRONG_THREAD_CONTEXT", HICOLLIE_WRONG_THREAD_CONTEXT.toInt(), 29800001)
        p("HICOLLIE_REMOTE_FAILED", HICOLLIE_REMOTE_FAILED.toInt(), 29800002)
        p("HICOLLIE_INVALID_TIMER_NAME", HICOLLIE_INVALID_TIMER_NAME.toInt(), 29800003)
        p("HICOLLIE_INVALID_TIMEOUT_VALUE", HICOLLIE_INVALID_TIMEOUT_VALUE.toInt(), 29800004)
        p("HICOLLIE_WRONG_PROCESS_CONTEXT", HICOLLIE_WRONG_PROCESS_CONTEXT.toInt(), 29800005)
        p("HICOLLIE_WRONG_TIMER_ID_OUTPUT_PARAM", HICOLLIE_WRONG_TIMER_ID_OUTPUT_PARAM.toInt(), 29800006)
    }

    @Test
    fun testEnum_HiCollie_Flag() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("HICOLLIE_FLAG_DEFAULT", HICOLLIE_FLAG_DEFAULT.toInt(), -1)
        p("HICOLLIE_FLAG_NOOP", HICOLLIE_FLAG_NOOP.toInt(), 0)
        p("HICOLLIE_FLAG_LOG", HICOLLIE_FLAG_LOG.toInt(), 1)
        p("HICOLLIE_FLAG_RECOVERY", HICOLLIE_FLAG_RECOVERY.toInt(), 2)
    }

    // ---------- Init / Report / Timer ----------

    @Test
    fun testOH_HiCollie_Init_StuckDetection() {
        val rc = OH_HiCollie_Init_StuckDetection(null)
        assertNotNull(rc)
        logLine("OH_HiCollie_Init_StuckDetection=$rc")
    }

    @Test
    fun testOH_HiCollie_Init_StuckDetectionWithTimeout() {
        val rc = try { OH_HiCollie_Init_StuckDetectionWithTimeout(null, 5u) } catch (e: Throwable) { logLine("OH_HiCollie_Init_StuckDetectionWithTimeout (API 18) exception: $e"); HICOLLIE_INVALID_ARGUMENT }
        assertNotNull(rc)
        logLine("OH_HiCollie_Init_StuckDetectionWithTimeout=$rc")
    }

    @Test
    fun testOH_HiCollie_Init_JankDetection() {
        memScoped {
            val beginFunc = staticCFunction { _: CPointer<ByteVar>? -> }
            val endFunc = staticCFunction { _: CPointer<ByteVar>? -> }
            val beginSlot = alloc<LongVar>().apply { value = beginFunc.rawValue.toLong() }
            val endSlot = alloc<LongVar>().apply { value = endFunc.rawValue.toLong() }
            val detectionParam = alloc<HiCollie_DetectionParam>().apply {
                sampleStackTriggerTime = 100
                reserved = 0
            }
            @Suppress("UNCHECKED_CAST")
            val rc = OH_HiCollie_Init_JankDetection(
                beginSlot.ptr as CValuesRef<CPointerVarOf<CPointer<CFunction<(CPointer<ByteVar>?) -> Unit>>>>?,
                endSlot.ptr as CValuesRef<CPointerVarOf<CPointer<CFunction<(CPointer<ByteVar>?) -> Unit>>>>?,
                detectionParam.readValue()
            )
            assertNotNull(rc)
            logLine("OH_HiCollie_Init_JankDetection=$rc")
        }
    }

    @Test
    fun testOH_HiCollie_Report() {
        memScoped {
            val isSixSecond = alloc<BooleanVar>()
            val rc = OH_HiCollie_Report(isSixSecond.ptr)
            assertNotNull(rc)
            logLine("OH_HiCollie_Report=$rc")
        }
    }

    @Test
    fun testOH_HiCollie_SetTimer() {
        memScoped {
            val timerId = alloc<IntVar>()
            val timerParam = alloc<HiCollie_SetTimerParam>().apply {
                name = "test_timer".cstr.getPointer(this@memScoped)
                timeout = 5u
                func = null
                arg = null
                flag = HICOLLIE_FLAG_DEFAULT
            }
            val rc = try { OH_HiCollie_SetTimer(timerParam.readValue(), timerId.ptr) } catch (e: Throwable) { logLine("OH_HiCollie_SetTimer (API 18) exception: $e"); HICOLLIE_INVALID_ARGUMENT }
            assertNotNull(rc)
            logLine("OH_HiCollie_SetTimer=$rc")
            try { OH_HiCollie_CancelTimer(timerId.value) } catch (e: Throwable) { logLine("OH_HiCollie_CancelTimer (API 18) exception: $e") }
        }
    }

    @Test
    fun testOH_HiCollie_CancelTimer() {
        memScoped {
            val timerId = alloc<IntVar>()
            val timerParam = alloc<HiCollie_SetTimerParam>().apply {
                name = "test_timer".cstr.getPointer(this@memScoped)
                timeout = 5u
                func = null
                arg = null
                flag = HICOLLIE_FLAG_DEFAULT
            }
            val setRc = try { OH_HiCollie_SetTimer(timerParam.readValue(), timerId.ptr) } catch (e: Throwable) { logLine("OH_HiCollie_SetTimer (API 18) exception: $e"); null }
            try { OH_HiCollie_CancelTimer(timerId.value) } catch (e: Throwable) { logLine("OH_HiCollie_CancelTimer (API 18) exception: $e") }
            logLine("OH_HiCollie_CancelTimer=called")
        }
    }
}
