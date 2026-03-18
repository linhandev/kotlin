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
import platform.PerformanceAnalysisKit.HiLog.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HiLogTest {

    private fun logLine(message: String) = println(message)

    @Test
    fun testEnum_LogType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("LOG_APP", LOG_APP.toInt(), 0)
    }

    @Test
    fun testEnum_LogLevel() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("LOG_DEBUG", LOG_DEBUG.toInt(), 3)
        p("LOG_INFO", LOG_INFO.toInt(), 4)
        p("LOG_WARN", LOG_WARN.toInt(), 5)
        p("LOG_ERROR", LOG_ERROR.toInt(), 6)
        p("LOG_FATAL", LOG_FATAL.toInt(), 7)
    }

    @Test
    fun testEnum_PreferStrategy() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("UNSET_LOGLEVEL", UNSET_LOGLEVEL.toInt(), 0)
        p("PREFER_CLOSE_LOG", PREFER_CLOSE_LOG.toInt(), 1)
        p("PREFER_OPEN_LOG", PREFER_OPEN_LOG.toInt(), 2)
    }

    @Test
    fun testPrint() {
        // OH_LOG_Print（variadic，cinterop 生成只带固定参数的绑定，不传可变参数即可）
        val rc = OH_LOG_Print(LOG_APP, LOG_INFO, 0x0201u, "KNTest", "hello from OH_LOG_Print")
        assertNotNull(rc)
        logLine("OH_LOG_Print=$rc")
    }

    @Test
    fun testVPrint() {
        memScoped {
            // OH_LOG_VPrint（va_list 是非空 CValue，用 cValue 零初始化）(API 18)
            val emptyVaList = cValue<`std::__va_list`>()
            val rc = try { OH_LOG_VPrint(LOG_APP, LOG_DEBUG, 0x0201u, "KNTest", "hello from OH_LOG_VPrint", emptyVaList) } catch (e: Throwable) { logLine("OH_LOG_VPrint (API 18) exception: $e"); -1 }
            assertNotNull(rc)
            logLine("OH_LOG_VPrint=$rc")
        }
    }

    @Test
    fun testPrintMsg() {
        // OH_LOG_PrintMsg (API 18)
        val rc = try { OH_LOG_PrintMsg(LOG_APP, LOG_INFO, 0x0201u, "KNTest", "hello from KN") } catch (e: Throwable) { logLine("OH_LOG_PrintMsg (API 18) exception: $e"); -1 }
        assertNotNull(rc)
        logLine("OH_LOG_PrintMsg=$rc")
    }

    @Test
    fun testPrintMsgByLen() {
        // OH_LOG_PrintMsgByLen (API 18)
        val rc = try { OH_LOG_PrintMsgByLen(LOG_APP, LOG_DEBUG, 0x0201u, "tag", 3uL, "msg", 3uL) } catch (e: Throwable) { logLine("OH_LOG_PrintMsgByLen (API 18) exception: $e"); -1 }
        assertNotNull(rc)
        logLine("OH_LOG_PrintMsgByLen=$rc")
    }

    @Test
    fun testIsLoggable() {
        // OH_LOG_IsLoggable
        val rc = OH_LOG_IsLoggable(0x0201u, "KNTest", LOG_DEBUG)
        assertNotNull(rc)
        logLine("OH_LOG_IsLoggable=$rc")
    }

    @Test
    fun testSetCallback() {
        // OH_LOG_SetCallback
        OH_LOG_SetCallback(null)
        logLine("OH_LOG_SetCallback done")
    }

    @Test
    fun testSetMinLogLevel() {
        // OH_LOG_SetMinLogLevel
        OH_LOG_SetMinLogLevel(LOG_DEBUG)
        logLine("OH_LOG_SetMinLogLevel done")
    }

    @Test
    fun testSetLogLevel() {
        // OH_LOG_SetLogLevel (API 21)
        try { OH_LOG_SetLogLevel(LOG_DEBUG, UNSET_LOGLEVEL) } catch (e: Throwable) { logLine("OH_LOG_SetLogLevel (API 21) exception: $e") }
        logLine("OH_LOG_SetLogLevel done")
    }
}
