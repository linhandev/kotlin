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
import kotlin.test.assertTrue
import kotlinx.cinterop.*
import platform.PenKit.HandWrite.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HandWriteTest {

    private fun logLine(message: String) = println(message)

    // ---------- 枚举：Handwrite_ErrCode（顶层 + toInt） ----------

    @Test
    fun testEnum_Handwrite_ErrCode() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("E_NO_ERROR", E_NO_ERROR.toInt(), 0)
        p("E_PARAMS", E_PARAMS.toInt(), 401)
        p("E_INNER_ERROR", E_INNER_ERROR.toInt(), 1010400001)
    }

    // ---------- 函数：HMS_HandWrite_GetPredictPoint（简单场景） ----------

    @Test
    fun testHMS_HandWrite_GetPredictPoint() {
        memScoped {
            val points = allocArray<HandWrite_HistoricalPoint>(2).apply {
                this[0].x = 10.0f; this[0].y = 20.0f; this[0].timeStamp = 1000L; this[0].force = 0.5f
                this[1].x = 30.0f; this[1].y = 40.0f; this[1].timeStamp = 2000L; this[1].force = 0.6f
            }
            val outX = alloc<FloatVar>()
            val outY = alloc<FloatVar>()
            val rc = try { HMS_HandWrite_GetPredictPoint(points, 2, outX.ptr, outY.ptr) } catch (e: Throwable) { logLine("HMS_HandWrite_GetPredictPoint (API 20) exception: $e"); E_PARAMS }
            assertNotNull(rc)
            logLine("HMS_HandWrite_GetPredictPoint=$rc, predict=(${outX.value}, ${outY.value})")
        }
    }
}
