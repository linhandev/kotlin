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

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class EffectKitTest {

    private fun logLine(message: String) = println(message)

    // ---------- 枚举 ----------

    @Test
    fun testEffectErrorCodeEnums() {
        logLine("--- EffectErrorCode ---")
        val v0 = platform.ArkGraphics2D.EffectKit.EFFECT_SUCCESS
        logLine("EFFECT_SUCCESS=$v0")
        assertEquals<Int>(0, v0.toInt())
        val v1 = platform.ArkGraphics2D.EffectKit.EFFECT_BAD_PARAMETER
        logLine("EFFECT_BAD_PARAMETER=$v1")
        assertEquals<Int>(401, v1.toInt())
        val v2 = platform.ArkGraphics2D.EffectKit.EFFECT_UNSUPPORTED_OPERATION
        logLine("EFFECT_UNSUPPORTED_OPERATION=$v2")
        assertEquals<Int>(7600201, v2.toInt())
        val v3 = platform.ArkGraphics2D.EffectKit.EFFECT_UNKNOWN_ERROR
        logLine("EFFECT_UNKNOWN_ERROR=$v3")
        assertEquals<Int>(7600901, v3.toInt())
        logLine("EffectErrorCode values ok")
    }

    @Test
    fun testEffectTileModeEnums() {
        logLine("--- EffectTileMode ---")
        val t0 = platform.ArkGraphics2D.EffectKit.CLAMP
        logLine("CLAMP=$t0")
        assertEquals<Int>(0, t0.toInt())
        val t1 = platform.ArkGraphics2D.EffectKit.REPEAT
        logLine("REPEAT=$t1")
        assertEquals<Int>(1, t1.toInt())
        val t2 = platform.ArkGraphics2D.EffectKit.MIRROR
        logLine("MIRROR=$t2")
        assertEquals<Int>(2, t2.toInt())
        val t3 = platform.ArkGraphics2D.EffectKit.DECAL
        logLine("DECAL=$t3")
        assertEquals<Int>(3, t3.toInt())
        logLine("EffectTileMode values ok")
    }

    // ---------- effect_filter.h (每个函数一个 @Test) ----------

    @Test
    fun testOH_Filter_CreateEffect() {
        logLine("--- OH_Filter_CreateEffect ---")
        memScoped {
            val outFilter = alloc<CPointerVar<cnames.structs.OH_Filter>>()
            val result = platform.ArkGraphics2D.EffectKit.OH_Filter_CreateEffect(null, outFilter.ptr)
            assertNotNull(result)
            logLine("OH_Filter_CreateEffect result: $result, filter=${outFilter.value}")
        }
    }

    @Test
    fun testOH_Filter_Release() {
        logLine("--- OH_Filter_Release ---")
        val result = platform.ArkGraphics2D.EffectKit.OH_Filter_Release(null)
        assertNotNull(result)
        logLine("OH_Filter_Release result: $result")
    }

    @Test
    fun testOH_Filter_Blur() {
        logLine("--- OH_Filter_Blur ---")
        val result = platform.ArkGraphics2D.EffectKit.OH_Filter_Blur(null, 1.0f)
        assertNotNull(result)
        logLine("OH_Filter_Blur result: $result")
    }

    @Test
    fun testOH_Filter_BlurWithTileMode() {
        logLine("--- OH_Filter_BlurWithTileMode ---")
        val result = platform.ArkGraphics2D.EffectKit.OH_Filter_BlurWithTileMode(
            null,
            1.0f,
            platform.ArkGraphics2D.EffectKit.CLAMP
        )
        assertNotNull(result)
        logLine("OH_Filter_BlurWithTileMode result: $result")
    }

    @Test
    fun testOH_Filter_Brighten() {
        logLine("--- OH_Filter_Brighten ---")
        val result = platform.ArkGraphics2D.EffectKit.OH_Filter_Brighten(null, 0.5f)
        assertNotNull(result)
        logLine("OH_Filter_Brighten result: $result")
    }

    @Test
    fun testOH_Filter_GrayScale() {
        logLine("--- OH_Filter_GrayScale ---")
        val result = platform.ArkGraphics2D.EffectKit.OH_Filter_GrayScale(null)
        assertNotNull(result)
        logLine("OH_Filter_GrayScale result: $result")
    }

    @Test
    fun testOH_Filter_Invert() {
        logLine("--- OH_Filter_Invert ---")
        val result = platform.ArkGraphics2D.EffectKit.OH_Filter_Invert(null)
        assertNotNull(result)
        logLine("OH_Filter_Invert result: $result")
    }

    @Test
    fun testOH_Filter_SetColorMatrix() {
        logLine("--- OH_Filter_SetColorMatrix ---")
        memScoped {
            val matrix = alloc<platform.ArkGraphics2D.EffectKit.OH_Filter_ColorMatrix>().apply {
                for (i in 0 until 20) {
                    `val`[i] = 0f
                }
            }
            val result = platform.ArkGraphics2D.EffectKit.OH_Filter_SetColorMatrix(null, matrix.ptr)
            assertNotNull(result)
            logLine("OH_Filter_SetColorMatrix result: $result")
        }
    }

    @Test
    fun testOH_Filter_GetEffectPixelMap() {
        logLine("--- OH_Filter_GetEffectPixelMap ---")
        memScoped {
            val outPixelmap = alloc<CPointerVar<cnames.structs.OH_PixelmapNative>>()
            val result = platform.ArkGraphics2D.EffectKit.OH_Filter_GetEffectPixelMap(null, outPixelmap.ptr)
            assertNotNull(result)
            logLine("OH_Filter_GetEffectPixelMap result: $result, pixelmap=${outPixelmap.value}")
        }
    }
}
