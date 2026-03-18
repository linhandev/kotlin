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
import platform.ArkGraphics2D.NativeColorSpaceManager.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NativeColorSpaceManagerTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_ColorSpaceName() {
        assertEquals(NONE.toInt(), 0)
        assertEquals(ADOBE_RGB.toInt(), 1)
        assertEquals(DCI_P3.toInt(), 2)
        assertEquals(DISPLAY_P3.toInt(), 3)
        assertEquals(SRGB.toInt(), 4)
        assertEquals(CUSTOM.toInt(), 5)
        assertEquals(BT709.toInt(), 6)
        assertEquals(BT601_EBU.toInt(), 7)
        assertEquals(BT601_SMPTE_C.toInt(), 8)
        assertEquals(BT2020_HLG.toInt(), 9)
        assertEquals(BT2020_PQ.toInt(), 10)
        assertEquals(P3_HLG.toInt(), 11)
        assertEquals(P3_PQ.toInt(), 12)
        assertEquals(ADOBE_RGB_LIMIT.toInt(), 13)
        assertEquals(DISPLAY_P3_LIMIT.toInt(), 14)
        assertEquals(SRGB_LIMIT.toInt(), 15)
        assertEquals(BT709_LIMIT.toInt(), 16)
        assertEquals(BT601_EBU_LIMIT.toInt(), 17)
        assertEquals(BT601_SMPTE_C_LIMIT.toInt(), 18)
        assertEquals(BT2020_HLG_LIMIT.toInt(), 19)
        assertEquals(BT2020_PQ_LIMIT.toInt(), 20)
        assertEquals(P3_HLG_LIMIT.toInt(), 21)
        assertEquals(P3_PQ_LIMIT.toInt(), 22)
        assertEquals(LINEAR_P3.toInt(), 23)
        assertEquals(LINEAR_SRGB.toInt(), 24)
        assertEquals(LINEAR_BT709.toInt(), 24)
        assertEquals(LINEAR_BT2020.toInt(), 25)
        assertEquals(DISPLAY_SRGB.toInt(), 4)
        assertEquals(DISPLAY_P3_SRGB.toInt(), 3)
        assertEquals(DISPLAY_P3_HLG.toInt(), 11)
        assertEquals(DISPLAY_P3_PQ.toInt(), 12)
        logLine("ColorSpaceName passed")
    }

    @Test
    fun testCreateFromNameAndGetters() { memScoped {
        val mgr = OH_NativeColorSpaceManager_CreateFromName(SRGB)
        logLine("OH_NativeColorSpaceManager_CreateFromName=$mgr")
        val name = OH_NativeColorSpaceManager_GetColorSpaceName(mgr)
        logLine("OH_NativeColorSpaceManager_GetColorSpaceName=$name")
        val whitePoint = OH_NativeColorSpaceManager_GetWhitePoint(mgr)
        logLine("GetWhitePoint arr[0]=${whitePoint.useContents { arr[0] }}")
        val gamma = OH_NativeColorSpaceManager_GetGamma(mgr)
        logLine("OH_NativeColorSpaceManager_GetGamma=$gamma")
        OH_NativeColorSpaceManager_Destroy(mgr)
        logLine("OH_NativeColorSpaceManager_Destroy done")
    } }

    @Test
    fun testCreateFromPrimariesAndGamma() { memScoped {
        val primaries = alloc<ColorSpacePrimaries>().apply {
            rX = 0.64f; rY = 0.33f; gX = 0.3f; gY = 0.6f
            bX = 0.15f; bY = 0.06f; wX = 0.3127f; wY = 0.329f
        }
        val mgr = OH_NativeColorSpaceManager_CreateFromPrimariesAndGamma(primaries.readValue(), 2.2f)
        logLine("OH_NativeColorSpaceManager_CreateFromPrimariesAndGamma=$mgr")
        OH_NativeColorSpaceManager_Destroy(mgr)
    } }
}
