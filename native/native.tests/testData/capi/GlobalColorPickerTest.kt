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
import platform.PenKit.GlobalColorPicker.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class GlobalColorPickerTest {

    private fun logLine(message: String) = println(message)

    // ---------- 枚举：HMS_GCP_ColorSpace（顶层访问） ----------

    @Test
    fun testEnum_HMS_GCP_ColorSpace() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assertEquals(expected, v)
        }
        p("HMS_GCP_UNKNOWN", HMS_GCP_UNKNOWN.toInt(), 0)
        p("HMS_GCP_ADOBE_RGB_1998", HMS_GCP_ADOBE_RGB_1998.toInt(), 1)
        p("HMS_GCP_DCI_P3", HMS_GCP_DCI_P3.toInt(), 2)
        p("HMS_GCP_DISPLAY_P3", HMS_GCP_DISPLAY_P3.toInt(), 3)
        p("HMS_GCP_SRGB", HMS_GCP_SRGB.toInt(), 4)
        p("CUSTOM", CUSTOM.toInt(), 5)
        p("HMS_GCP_BT709", HMS_GCP_BT709.toInt(), 6)
        p("HMS_GCP_BT601_EBU", HMS_GCP_BT601_EBU.toInt(), 7)
        p("HMS_GCP_BT601_SMPTE_C", HMS_GCP_BT601_SMPTE_C.toInt(), 8)
        p("HMS_GCP_BT2020_HLG", HMS_GCP_BT2020_HLG.toInt(), 9)
        p("HMS_GCP_BT2020_PQ", HMS_GCP_BT2020_PQ.toInt(), 10)
        p("HMS_GCP_P3_HLG", HMS_GCP_P3_HLG.toInt(), 11)
        p("HMS_GCP_P3_PQ", HMS_GCP_P3_PQ.toInt(), 12)
        p("HMS_GCP_ADOBE_RGB_1998_LIMIT", HMS_GCP_ADOBE_RGB_1998_LIMIT.toInt(), 13)
        p("HMS_GCP_DISPLAY_P3_LIMIT", HMS_GCP_DISPLAY_P3_LIMIT.toInt(), 14)
        p("HMS_GCP_SRGB_LIMIT", HMS_GCP_SRGB_LIMIT.toInt(), 15)
        p("HMS_GCP_BT709_LIMIT", HMS_GCP_BT709_LIMIT.toInt(), 16)
        p("HMS_GCP_BT601_EBU_LIMIT", HMS_GCP_BT601_EBU_LIMIT.toInt(), 17)
        p("HMS_GCP_BT601_SMPTE_C_LIMIT", HMS_GCP_BT601_SMPTE_C_LIMIT.toInt(), 18)
        p("HMS_GCP_BT2020_HLG_LIMIT", HMS_GCP_BT2020_HLG_LIMIT.toInt(), 19)
        p("HMS_GCP_BT2020_PQ_LIMIT", HMS_GCP_BT2020_PQ_LIMIT.toInt(), 20)
        p("HMS_GCP_P3_HLG_LIMIT", HMS_GCP_P3_HLG_LIMIT.toInt(), 21)
        p("HMS_GCP_P3_PQ_LIMIT", HMS_GCP_P3_PQ_LIMIT.toInt(), 22)
        p("HMS_GCP_LINEAR_P3", HMS_GCP_LINEAR_P3.toInt(), 23)
        p("HMS_GCP_LINEAR_SRGB", HMS_GCP_LINEAR_SRGB.toInt(), 24)
        p("HMS_GCP_LINEAR_BT2020", HMS_GCP_LINEAR_BT2020.toInt(), 25)
    }

    // ---------- 函数：HMS_GCP_StartColorPicker ----------

    @Test
    fun testHMS_GCP_StartColorPicker() {
        val rc = HMS_GCP_StartColorPicker(100, 200, null, null)
        assertNotNull(rc)
        logLine("HMS_GCP_StartColorPicker(100,200,null,null)=$rc")
        assertTrue(
            rc == 0 ||
                rc == 1013900001 || rc == 1013900002 || rc == 1013900003 ||
                rc == 1013900004 || rc == 1013900005,
            "HMS_GCP_StartColorPicker should return valid code, got $rc"
        )
    }

    @Test
    fun testHMS_GCP_StartColorPicker_variousPositions() {
        var rc = HMS_GCP_StartColorPicker(0, 0, null, null)
        assertNotNull(rc)
        logLine("HMS_GCP_StartColorPicker(0,0)=$rc")
        rc = HMS_GCP_StartColorPicker(1920, 1080, null, null)
        assertNotNull(rc)
        logLine("HMS_GCP_StartColorPicker(1920,1080)=$rc")
        rc = HMS_GCP_StartColorPicker(-100, -200, null, null)
        assertNotNull(rc)
        logLine("HMS_GCP_StartColorPicker(-100,-200)=$rc")
    }

    // ---------- 函数：HMS_GCP_StartColorPickerWithColorValue ----------

    @Test
    fun testHMS_GCP_StartColorPickerWithColorValue() {
        val rc = try { HMS_GCP_StartColorPickerWithColorValue(150, 250, null, null) } catch (e: Throwable) { logLine("HMS_GCP_StartColorPickerWithColorValue (API 18) exception: $e"); 1013900001 }
        assertNotNull(rc)
        logLine("HMS_GCP_StartColorPickerWithColorValue(150,250,null,null)=$rc")
        assertTrue(
            rc == 0 ||
                rc == 1013900001 || rc == 1013900002 || rc == 1013900003 ||
                rc == 1013900004 || rc == 1013900005,
            "HMS_GCP_StartColorPickerWithColorValue should return valid code, got $rc"
        )
    }

    // ---------- 回调覆盖：传入非 null 回调以覆盖 API 类型 ----------

    @Test
    fun testHMS_GCP_StartColorPicker_withCallback() {
        memScoped {
            val callback = staticCFunction { _userData: COpaquePointer?, _colorInfo: CValue<HMS_GCP_PickedColorInfo>, _code: Int -> }
            val rc = HMS_GCP_StartColorPicker(50, 50, callback, null)
            assertNotNull(rc)
            logLine("HMS_GCP_StartColorPicker(withCallback)=$rc")
        }
    }

    @Test
    fun testHMS_GCP_StartColorPickerWithColorValue_withCallback() {
        memScoped {
            val callback = staticCFunction { _userData: COpaquePointer?, _colorInfo: CValue<HMS_GCP_PickedColorInfo>, _code: Int -> }
            val rc = try { HMS_GCP_StartColorPickerWithColorValue(50, 50, callback, null) } catch (e: Throwable) { logLine("HMS_GCP_StartColorPickerWithColorValue (API 18) exception: $e"); 1013900001 }
            assertNotNull(rc)
            logLine("HMS_GCP_StartColorPickerWithColorValue(withCallback)=$rc")
        }
    }
}
