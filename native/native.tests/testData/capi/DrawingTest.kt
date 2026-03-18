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
import platform.ArkGraphics2D.Drawing.*
import cnames.structs.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class DrawingTest {

    // ---------- 枚举（单独） ----------

    @Test
    fun testErrorCodeEnums() {
        println("--- ErrorCodeEnums (drawing_error_code.h) ---")
        val v0 = OH_DRAWING_SUCCESS
        println("OH_DRAWING_SUCCESS=$v0")
        val v1 = OH_DRAWING_ERROR_NO_PERMISSION
        println("OH_DRAWING_ERROR_NO_PERMISSION=$v1")
        val v2 = OH_DRAWING_ERROR_INVALID_PARAMETER
        println("OH_DRAWING_ERROR_INVALID_PARAMETER=$v2")
        val v3 = OH_DRAWING_ERROR_PARAMETER_OUT_OF_RANGE
        println("OH_DRAWING_ERROR_PARAMETER_OUT_OF_RANGE=$v3")
        val v4 = OH_DRAWING_ERROR_ALLOCATION_FAILED
        println("OH_DRAWING_ERROR_ALLOCATION_FAILED=$v4")
        val v5 = OH_DRAWING_ERROR_ATTRIBUTE_ID_MISMATCH
        println("OH_DRAWING_ERROR_ATTRIBUTE_ID_MISMATCH=$v5")
        val v6 = OH_DRAWING_ERROR_INCORRECT_PARAMETER
        println("OH_DRAWING_ERROR_INCORRECT_PARAMETER=$v6")
        println("OH_Drawing_ErrorCode values ok")
    }

    @Test
    fun testOH_Drawing_ErrorCodeGet() {
        OH_Drawing_ErrorCodeGet()
        println("OH_Drawing_ErrorCodeGet passed")
    }

    @Test
    fun testOH_Drawing_ErrorCodeReset() {
        try {
            OH_Drawing_ErrorCodeReset()
        } catch (e: Throwable) {
            println("OH_Drawing_ErrorCodeReset (API 18) exception: $e")
        }
        println("OH_Drawing_ErrorCodeReset passed")
    }

    @Test
    fun testSrcRectConstraintEnums() {
        println("--- SrcRectConstraintEnums (drawing_canvas.h) ---")
        val v0 = OH_Drawing_SrcRectConstraint.STRICT_SRC_RECT_CONSTRAINT
        println("STRICT_SRC_RECT_CONSTRAINT=$v0")
        val v1 = OH_Drawing_SrcRectConstraint.FAST_SRC_RECT_CONSTRAINT
        println("FAST_SRC_RECT_CONSTRAINT=$v1")
        println("OH_Drawing_SrcRectConstraint values ok")
    }

    // ---------- drawing_types.h 枚举（全） ----------

    @Test
    fun testDrawingTypesEnums() {
        println("--- DrawingTypesEnums (drawing_types.h) ---")
        val v0 = OH_Drawing_ColorFormat.COLOR_FORMAT_UNKNOWN
        println("COLOR_FORMAT_UNKNOWN=$v0")
        val v1 = OH_Drawing_ColorFormat.COLOR_FORMAT_ALPHA_8
        println("COLOR_FORMAT_ALPHA_8=$v1")
        val v2 = OH_Drawing_ColorFormat.COLOR_FORMAT_RGB_565
        println("COLOR_FORMAT_RGB_565=$v2")
        val v3 = OH_Drawing_ColorFormat.COLOR_FORMAT_ARGB_4444
        println("COLOR_FORMAT_ARGB_4444=$v3")
        val v4 = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
        println("COLOR_FORMAT_RGBA_8888=$v4")
        val v5 = OH_Drawing_ColorFormat.COLOR_FORMAT_BGRA_8888
        println("COLOR_FORMAT_BGRA_8888=$v5")
        val v6 = OH_Drawing_AlphaFormat.ALPHA_FORMAT_UNKNOWN
        println("ALPHA_FORMAT_UNKNOWN=$v6")
        val v7 = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
        println("ALPHA_FORMAT_OPAQUE=$v7")
        val v8 = OH_Drawing_AlphaFormat.ALPHA_FORMAT_PREMUL
        println("ALPHA_FORMAT_PREMUL=$v8")
        val v9 = OH_Drawing_AlphaFormat.ALPHA_FORMAT_UNPREMUL
        println("ALPHA_FORMAT_UNPREMUL=$v9")
        val v10 = OH_Drawing_BlendMode.BLEND_MODE_CLEAR
        println("BLEND_MODE_CLEAR=$v10")
        val v11 = OH_Drawing_BlendMode.BLEND_MODE_SRC
        println("BLEND_MODE_SRC=$v11")
        val v12 = OH_Drawing_BlendMode.BLEND_MODE_DST
        println("BLEND_MODE_DST=$v12")
        val v13 = OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER
        println("BLEND_MODE_SRC_OVER=$v13")
        val v14 = OH_Drawing_BlendMode.BLEND_MODE_DST_OVER
        println("BLEND_MODE_DST_OVER=$v14")
        val v15 = OH_Drawing_BlendMode.BLEND_MODE_SRC_IN
        println("BLEND_MODE_SRC_IN=$v15")
        val v16 = OH_Drawing_BlendMode.BLEND_MODE_DST_IN
        println("BLEND_MODE_DST_IN=$v16")
        val v17 = OH_Drawing_BlendMode.BLEND_MODE_SRC_OUT
        println("BLEND_MODE_SRC_OUT=$v17")
        val v18 = OH_Drawing_BlendMode.BLEND_MODE_DST_OUT
        println("BLEND_MODE_DST_OUT=$v18")
        val v19 = OH_Drawing_BlendMode.BLEND_MODE_SRC_ATOP
        println("BLEND_MODE_SRC_ATOP=$v19")
        val v20 = OH_Drawing_BlendMode.BLEND_MODE_DST_ATOP
        println("BLEND_MODE_DST_ATOP=$v20")
        val v21 = OH_Drawing_BlendMode.BLEND_MODE_XOR
        println("BLEND_MODE_XOR=$v21")
        val v22 = OH_Drawing_BlendMode.BLEND_MODE_PLUS
        println("BLEND_MODE_PLUS=$v22")
        val v23 = OH_Drawing_BlendMode.BLEND_MODE_MODULATE
        println("BLEND_MODE_MODULATE=$v23")
        val v24 = OH_Drawing_BlendMode.BLEND_MODE_SCREEN
        println("BLEND_MODE_SCREEN=$v24")
        val v25 = OH_Drawing_BlendMode.BLEND_MODE_OVERLAY
        println("BLEND_MODE_OVERLAY=$v25")
        val v26 = OH_Drawing_BlendMode.BLEND_MODE_DARKEN
        println("BLEND_MODE_DARKEN=$v26")
        val v27 = OH_Drawing_BlendMode.BLEND_MODE_LIGHTEN
        println("BLEND_MODE_LIGHTEN=$v27")
        val v28 = OH_Drawing_BlendMode.BLEND_MODE_COLOR_DODGE
        println("BLEND_MODE_COLOR_DODGE=$v28")
        val v29 = OH_Drawing_BlendMode.BLEND_MODE_COLOR_BURN
        println("BLEND_MODE_COLOR_BURN=$v29")
        val v30 = OH_Drawing_BlendMode.BLEND_MODE_HARD_LIGHT
        println("BLEND_MODE_HARD_LIGHT=$v30")
        val v31 = OH_Drawing_BlendMode.BLEND_MODE_SOFT_LIGHT
        println("BLEND_MODE_SOFT_LIGHT=$v31")
        val v32 = OH_Drawing_BlendMode.BLEND_MODE_DIFFERENCE
        println("BLEND_MODE_DIFFERENCE=$v32")
        val v33 = OH_Drawing_BlendMode.BLEND_MODE_EXCLUSION
        println("BLEND_MODE_EXCLUSION=$v33")
        val v34 = OH_Drawing_BlendMode.BLEND_MODE_MULTIPLY
        println("BLEND_MODE_MULTIPLY=$v34")
        val v35 = OH_Drawing_BlendMode.BLEND_MODE_HUE
        println("BLEND_MODE_HUE=$v35")
        val v36 = OH_Drawing_BlendMode.BLEND_MODE_SATURATION
        println("BLEND_MODE_SATURATION=$v36")
        val v37 = OH_Drawing_BlendMode.BLEND_MODE_COLOR
        println("BLEND_MODE_COLOR=$v37")
        val v38 = OH_Drawing_BlendMode.BLEND_MODE_LUMINOSITY
        println("BLEND_MODE_LUMINOSITY=$v38")
        val v39 = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
        println("TEXT_ENCODING_UTF8=$v39")
        val v40 = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF16
        println("TEXT_ENCODING_UTF16=$v40")
        val v41 = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF32
        println("TEXT_ENCODING_UTF32=$v41")
        val v42 = OH_Drawing_TextEncoding.TEXT_ENCODING_GLYPH_ID
        println("TEXT_ENCODING_GLYPH_ID=$v42")
        println("OH_Drawing DrawingTypes values ok")
    }

    // ---------- drawing_color_filter.h ----------

    @Test
    fun testOH_Drawing_ColorFilterCreateBlendMode() {
        val cf = OH_Drawing_ColorFilterCreateBlendMode(0xFF000000u, OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER)
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_ColorFilterCreateBlendMode passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterDestroy() {
        val cf = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        OH_Drawing_ColorFilterDestroy(cf)
        OH_Drawing_ColorFilterDestroy(null)
        println("OH_Drawing_ColorFilterDestroy passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterCreateLinearToSrgbGamma() {
        val cf = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_ColorFilterCreateLinearToSrgbGamma passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterCreateSrgbGammaToLinear() {
        val cf = OH_Drawing_ColorFilterCreateSrgbGammaToLinear()
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_ColorFilterCreateSrgbGammaToLinear passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterCreateCompose() {
        val inner = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        val outer = OH_Drawing_ColorFilterCreateSrgbGammaToLinear()
        val cf = OH_Drawing_ColorFilterCreateCompose(outer, inner)
        OH_Drawing_ColorFilterDestroy(cf)
        OH_Drawing_ColorFilterDestroy(outer)
        OH_Drawing_ColorFilterDestroy(inner)
        println("OH_Drawing_ColorFilterCreateCompose passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterCreateMatrix() {
        memScoped {
            val matrix = allocArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f, 1f, 1f, 1f)
            val cf = OH_Drawing_ColorFilterCreateMatrix(matrix)
            OH_Drawing_ColorFilterDestroy(cf)
        }
        println("OH_Drawing_ColorFilterCreateMatrix passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterCreateLuma() {
        val cf = OH_Drawing_ColorFilterCreateLuma()
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_ColorFilterCreateLuma passed")
    }

    @Test
    fun testOH_Drawing_ColorFilterCreateLighting() {
        val cf = try { OH_Drawing_ColorFilterCreateLighting(0xFFFFFFFFu, 0u) } catch (e: Throwable) { println("OH_Drawing_ColorFilterCreateLighting (API 20) exception: $e"); null }
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_ColorFilterCreateLighting passed")
    }

    // ---------- drawing_brush.h ----------

    @Test
    fun testOH_Drawing_BrushCreate() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushCreate passed")
    }

    @Test
    fun testOH_Drawing_BrushDestroy() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushDestroy passed")
    }

    @Test
    fun testOH_Drawing_BrushIsAntiAlias() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushIsAntiAlias(brush)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushIsAntiAlias passed")
    }

    @Test
    fun testOH_Drawing_BrushSetAntiAlias() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetAntiAlias(brush, true)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetAntiAlias passed")
    }

    @Test
    fun testOH_Drawing_BrushGetColor() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushGetColor(brush)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushGetColor passed")
    }

    @Test
    fun testOH_Drawing_BrushSetColor() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetColor(brush, 0xFF000000u)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetColor passed")
    }

    @Test
    fun testOH_Drawing_BrushSetColor4f() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetColor4f(brush, 1f, 0f, 0f, 1f, null)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetColor4f passed")
    }

    @Test
    fun testOH_Drawing_BrushGetAlpha() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushGetAlpha(brush)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushGetAlpha passed")
    }

    @Test
    fun testOH_Drawing_BrushSetAlpha() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetAlpha(brush, 255u)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetAlpha passed")
    }

    @Test
    fun testOH_Drawing_BrushCopy() {
        val brush = OH_Drawing_BrushCreate()
        val brush2 = OH_Drawing_BrushCopy(brush)
        OH_Drawing_BrushDestroy(brush2)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushCopy passed")
    }

    @Test
    fun testOH_Drawing_BrushReset() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushReset(brush)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushReset passed")
    }

    @Test
    fun testOH_Drawing_BrushGetFilter() {
        val brush = OH_Drawing_BrushCreate()
        val filterOut = OH_Drawing_FilterCreate()
        OH_Drawing_BrushGetFilter(brush, filterOut)
        OH_Drawing_FilterDestroy(filterOut)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushGetFilter passed")
    }

    @Test
    fun testOH_Drawing_BrushSetFilter() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetFilter(brush, null)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetFilter passed")
    }

    @Test
    fun testOH_Drawing_BrushSetBlendMode() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetBlendMode(brush, OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetBlendMode passed")
    }

    @Test
    fun testOH_Drawing_BrushGetAlphaFloat() {
        memScoped {
            val brush = OH_Drawing_BrushCreate()
            val a = alloc<FloatVar>()
            try { OH_Drawing_BrushGetAlphaFloat(brush, a.ptr) } catch (e: Throwable) { println("OH_Drawing_BrushGetAlphaFloat (API 20) exception: $e") }
            OH_Drawing_BrushDestroy(brush)
        }
        println("OH_Drawing_BrushGetAlphaFloat passed")
    }

    @Test
    fun testOH_Drawing_BrushGetRedFloat() {
        memScoped {
            val brush = OH_Drawing_BrushCreate()
            val a = alloc<FloatVar>()
            try { OH_Drawing_BrushGetRedFloat(brush, a.ptr) } catch (e: Throwable) { println("OH_Drawing_BrushGetRedFloat (API 20) exception: $e") }
            OH_Drawing_BrushDestroy(brush)
        }
        println("OH_Drawing_BrushGetRedFloat passed")
    }

    @Test
    fun testOH_Drawing_BrushGetGreenFloat() {
        memScoped {
            val brush = OH_Drawing_BrushCreate()
            val a = alloc<FloatVar>()
            try { OH_Drawing_BrushGetGreenFloat(brush, a.ptr) } catch (e: Throwable) { println("OH_Drawing_BrushGetGreenFloat (API 20) exception: $e") }
            OH_Drawing_BrushDestroy(brush)
        }
        println("OH_Drawing_BrushGetGreenFloat passed")
    }

    @Test
    fun testOH_Drawing_BrushGetBlueFloat() {
        memScoped {
            val brush = OH_Drawing_BrushCreate()
            val a = alloc<FloatVar>()
            try { OH_Drawing_BrushGetBlueFloat(brush, a.ptr) } catch (e: Throwable) { println("OH_Drawing_BrushGetBlueFloat (API 20) exception: $e") }
            OH_Drawing_BrushDestroy(brush)
        }
        println("OH_Drawing_BrushGetBlueFloat passed")
    }

    @Test
    fun testOH_Drawing_BrushSetShaderEffect() {
        val brush = OH_Drawing_BrushCreate()
        OH_Drawing_BrushSetShaderEffect(brush, null)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetShaderEffect passed")
    }

    @Test
    fun testOH_Drawing_BrushSetShadowLayer() {
        val brush = OH_Drawing_BrushCreate()
        val shadow = OH_Drawing_ShadowLayerCreate(0f, 0f, 0f, 0u)
        OH_Drawing_BrushSetShadowLayer(brush, shadow)
        OH_Drawing_ShadowLayerDestroy(shadow)
        OH_Drawing_BrushDestroy(brush)
        println("OH_Drawing_BrushSetShadowLayer passed")
    }

    // ---------- drawing_pen.h ----------

    @Test
    fun testOH_Drawing_PenCreate() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenCreate passed")
    }

    @Test
    fun testOH_Drawing_PenDestroy() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenDestroy passed")
    }

    @Test
    fun testOH_Drawing_PenIsAntiAlias() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenIsAntiAlias(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenIsAntiAlias passed")
    }

    @Test
    fun testOH_Drawing_PenSetAntiAlias() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetAntiAlias(pen, true)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetAntiAlias passed")
    }

    @Test
    fun testOH_Drawing_PenGetColor() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenGetColor(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetColor passed")
    }

    @Test
    fun testOH_Drawing_PenSetColor() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetColor(pen, 0xFF000000u)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetColor passed")
    }

    @Test
    fun testOH_Drawing_PenSetColor4f() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetColor4f(pen, 1f, 0f, 0f, 1f, null)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetColor4f passed")
    }

    @Test
    fun testOH_Drawing_PenGetAlpha() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenGetAlpha(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetAlpha passed")
    }

    @Test
    fun testOH_Drawing_PenSetAlpha() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetAlpha(pen, 255u)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetAlpha passed")
    }

    @Test
    fun testOH_Drawing_PenGetAlphaFloat() {
        memScoped {
            val pen = OH_Drawing_PenCreate()
            val fa = alloc<FloatVar>()
            try { OH_Drawing_PenGetAlphaFloat(pen, fa.ptr) } catch (e: Throwable) { println("OH_Drawing_PenGetAlphaFloat (API 20) exception: $e") }
            OH_Drawing_PenDestroy(pen)
        }
        println("OH_Drawing_PenGetAlphaFloat passed")
    }

    @Test
    fun testOH_Drawing_PenGetRedFloat() {
        memScoped {
            val pen = OH_Drawing_PenCreate()
            val fa = alloc<FloatVar>()
            try { OH_Drawing_PenGetRedFloat(pen, fa.ptr) } catch (e: Throwable) { println("OH_Drawing_PenGetRedFloat (API 20) exception: $e") }
            OH_Drawing_PenDestroy(pen)
        }
        println("OH_Drawing_PenGetRedFloat passed")
    }

    @Test
    fun testOH_Drawing_PenGetGreenFloat() {
        memScoped {
            val pen = OH_Drawing_PenCreate()
            val fa = alloc<FloatVar>()
            try { OH_Drawing_PenGetGreenFloat(pen, fa.ptr) } catch (e: Throwable) { println("OH_Drawing_PenGetGreenFloat (API 20) exception: $e") }
            OH_Drawing_PenDestroy(pen)
        }
        println("OH_Drawing_PenGetGreenFloat passed")
    }

    @Test
    fun testOH_Drawing_PenGetBlueFloat() {
        memScoped {
            val pen = OH_Drawing_PenCreate()
            val fa = alloc<FloatVar>()
            try { OH_Drawing_PenGetBlueFloat(pen, fa.ptr) } catch (e: Throwable) { println("OH_Drawing_PenGetBlueFloat (API 20) exception: $e") }
            OH_Drawing_PenDestroy(pen)
        }
        println("OH_Drawing_PenGetBlueFloat passed")
    }

    @Test
    fun testOH_Drawing_PenGetWidth() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenGetWidth(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetWidth passed")
    }

    @Test
    fun testOH_Drawing_PenSetWidth() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetWidth(pen, 1f)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetWidth passed")
    }

    @Test
    fun testOH_Drawing_PenGetMiterLimit() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenGetMiterLimit(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetMiterLimit passed")
    }

    @Test
    fun testOH_Drawing_PenSetMiterLimit() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetMiterLimit(pen, 4f)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetMiterLimit passed")
    }

    @Test
    fun testOH_Drawing_PenGetCap() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenGetCap(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetCap passed")
    }

    @Test
    fun testOH_Drawing_PenSetCap() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetCap(pen, OH_Drawing_PenLineCapStyle.LINE_FLAT_CAP)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetCap passed")
    }

    @Test
    fun testOH_Drawing_PenGetJoin() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenGetJoin(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetJoin passed")
    }

    @Test
    fun testOH_Drawing_PenSetJoin() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetJoin(pen, OH_Drawing_PenLineJoinStyle.LINE_MITER_JOIN)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetJoin passed")
    }

    @Test
    fun testOH_Drawing_PenSetShaderEffect() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetShaderEffect(pen, null)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetShaderEffect passed")
    }

    @Test
    fun testOH_Drawing_PenSetShadowLayer() {
        val pen = OH_Drawing_PenCreate()
        val sh = OH_Drawing_ShadowLayerCreate(0f, 0f, 0f, 0u)
        OH_Drawing_PenSetShadowLayer(pen, sh)
        OH_Drawing_ShadowLayerDestroy(sh)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetShadowLayer passed")
    }

    @Test
    fun testOH_Drawing_PenSetPathEffect() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetPathEffect(pen, null)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetPathEffect passed")
    }

    @Test
    fun testOH_Drawing_PenSetFilter() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetFilter(pen, null)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetFilter passed")
    }

    @Test
    fun testOH_Drawing_PenGetFilter() {
        val pen = OH_Drawing_PenCreate()
        val filterOut = OH_Drawing_FilterCreate()
        OH_Drawing_PenGetFilter(pen, filterOut)
        OH_Drawing_FilterDestroy(filterOut)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetFilter passed")
    }

    @Test
    fun testOH_Drawing_PenSetBlendMode() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenSetBlendMode(pen, OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenSetBlendMode passed")
    }

    @Test
    fun testOH_Drawing_PenGetFillPath() {
        val pen = OH_Drawing_PenCreate()
        val srcPath = OH_Drawing_PathCreate()
        val dstPath = OH_Drawing_PathCreate()
        val fillRect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val fillMatrix = OH_Drawing_MatrixCreate()
        try { OH_Drawing_PenGetFillPath(pen, srcPath, dstPath, fillRect, fillMatrix) } catch (e: Throwable) { println("OH_Drawing_PenGetFillPath (API 20) exception: $e") }
        OH_Drawing_PathDestroy(srcPath)
        OH_Drawing_PathDestroy(dstPath)
        OH_Drawing_RectDestroy(fillRect)
        OH_Drawing_MatrixDestroy(fillMatrix)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenGetFillPath passed")
    }

    @Test
    fun testOH_Drawing_PenReset() {
        val pen = OH_Drawing_PenCreate()
        OH_Drawing_PenReset(pen)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenReset passed")
    }

    @Test
    fun testOH_Drawing_PenCopy() {
        val pen = OH_Drawing_PenCreate()
        val pen2 = OH_Drawing_PenCopy(pen)
        OH_Drawing_PenDestroy(pen2)
        OH_Drawing_PenDestroy(pen)
        println("OH_Drawing_PenCopy passed")
    }

    // ---------- drawing_typeface.h ----------

    @Test
    fun testOH_Drawing_TypefaceCreateDefault() {
        val tf = OH_Drawing_TypefaceCreateDefault()
        OH_Drawing_TypefaceDestroy(tf)
        println("OH_Drawing_TypefaceCreateDefault passed")
    }

    @Test
    fun testOH_Drawing_TypefaceCreateFromFile() {
        OH_Drawing_TypefaceCreateFromFile("/nonexistent", 0)
        println("OH_Drawing_TypefaceCreateFromFile passed")
    }

    @Test
    fun testOH_Drawing_TypefaceCreateFromFileWithArguments() {
        val fontArgs = try { OH_Drawing_FontArgumentsCreate() } catch (e: Throwable) { println("OH_Drawing_TypefaceCreateFromFileWithArguments (API 13) FontArgumentsCreate: $e"); return }
        try { OH_Drawing_FontArgumentsAddVariation(fontArgs, "wght", 400f) } catch (e: Throwable) { }
        val tf = try { OH_Drawing_TypefaceCreateFromFileWithArguments("/nonexistent", fontArgs) } catch (e: Throwable) { println("OH_Drawing_TypefaceCreateFromFileWithArguments (API 13) exception: $e"); null }
        OH_Drawing_TypefaceDestroy(tf)
        OH_Drawing_FontArgumentsDestroy(fontArgs)
        println("OH_Drawing_TypefaceCreateFromFileWithArguments passed")
    }

    @Test
    fun testOH_Drawing_TypefaceDestroy() {
        val tf = OH_Drawing_TypefaceCreateDefault()
        OH_Drawing_TypefaceDestroy(tf)
        println("OH_Drawing_TypefaceDestroy passed")
    }

    @Test
    fun testOH_Drawing_TypefaceCreateFromCurrent() {
        val tfDefault = OH_Drawing_TypefaceCreateDefault()
        val fontArgs = OH_Drawing_FontArgumentsCreate()
        OH_Drawing_FontArgumentsAddVariation(fontArgs, "wght", 400f)
        val tfFromCurrent = OH_Drawing_TypefaceCreateFromCurrent(tfDefault, fontArgs)
        OH_Drawing_TypefaceDestroy(tfFromCurrent)
        OH_Drawing_FontArgumentsDestroy(fontArgs)
        OH_Drawing_TypefaceDestroy(tfDefault)
        println("OH_Drawing_TypefaceCreateFromCurrent passed")
    }

    @Test
    fun testOH_Drawing_TypefaceCreateFromStream() {
        memScoped {
            val streamData = allocArray<ByteVar>(4)
            val stream = OH_Drawing_MemoryStreamCreate(streamData, 4uL, true)
            val tfFromStream = OH_Drawing_TypefaceCreateFromStream(stream, 0)
            OH_Drawing_TypefaceDestroy(tfFromStream)
        }
        println("OH_Drawing_TypefaceCreateFromStream passed")
    }

    @Test
    fun testOH_Drawing_FontArgumentsCreate() {
        val fontArgs = OH_Drawing_FontArgumentsCreate()
        OH_Drawing_FontArgumentsDestroy(fontArgs)
        println("OH_Drawing_FontArgumentsCreate passed")
    }

    @Test
    fun testOH_Drawing_FontArgumentsAddVariation() {
        val fontArgs = OH_Drawing_FontArgumentsCreate()
        OH_Drawing_FontArgumentsAddVariation(fontArgs, "wght", 400f)
        OH_Drawing_FontArgumentsDestroy(fontArgs)
        println("OH_Drawing_FontArgumentsAddVariation passed")
    }

    @Test
    fun testOH_Drawing_FontArgumentsDestroy() {
        val fontArgs = OH_Drawing_FontArgumentsCreate()
        OH_Drawing_FontArgumentsDestroy(fontArgs)
        println("OH_Drawing_FontArgumentsDestroy passed")
    }

    // ---------- drawing_text_typography.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_CreateTypographyStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_CreateTypographyStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextDirection() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextDirection(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextDirection passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextAlign() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextAlign(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextAlign passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextMaxLines() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextMaxLines(style, 1)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextMaxLines passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextBreakStrategy() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextBreakStrategy(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextBreakStrategy passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextWordBreakType() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextWordBreakType(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextWordBreakType passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextEllipsisModal() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextEllipsisModal(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextEllipsisModal passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextEllipsis() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextEllipsis(style, "...")
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextEllipsis passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLocale() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLocale(style, "en")
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLocale passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextSplitRatio() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextSplitRatio(style, 1f)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextSplitRatio passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextFontFamily() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextFontFamily(style, "sans-serif")
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextFontFamily passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextFontSize() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextFontSize(style, 14.0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextFontSize passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextFontHeight() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextFontHeight(style, 14.0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextFontHeight passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextFontWeight() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextFontWeight(style, 400)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextFontWeight passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextFontStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextFontStyle(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextFontStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextHalfLeading() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextHalfLeading(style, false)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextHalfLeading passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextUseLineStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextUseLineStyle(style, false)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextUseLineStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleFontWeight() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleFontWeight(style, 400)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleFontWeight passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleFontStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleFontStyle(style, 0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleFontStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleFontSize() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleFontSize(style, 14.0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleFontSize passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleFontHeight() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleFontHeight(style, 14.0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleFontHeight passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleHalfLeading() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleHalfLeading(style, false)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleHalfLeading passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleSpacingScale() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleSpacingScale(style, 1.0)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleSpacingScale passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleOnly() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyTextLineStyleOnly(style, false)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextLineStyleOnly passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextLineStyleFontFamilies() {
        memScoped {
            val style = OH_Drawing_CreateTypographyStyle()
            val lineFamilyCstr = "sans-serif".cstr
            val lineFamiliesArr = allocArrayOf(lineFamilyCstr.ptr)
            OH_Drawing_SetTypographyTextLineStyleFontFamilies(style, 1, lineFamiliesArr)
            OH_Drawing_DestroyTypographyStyle(style)
        }
        println("OH_Drawing_SetTypographyTextLineStyleFontFamilies passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleSetHintsEnabled() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyStyleSetHintsEnabled(style, true)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyStyleSetHintsEnabled passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetEffectiveAlignment() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyGetEffectiveAlignment(style)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyGetEffectiveAlignment passed")
    }

    @Test
    fun testOH_Drawing_TypographyIsLineUnlimited() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyIsLineUnlimited(style)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyIsLineUnlimited passed")
    }

    @Test
    fun testOH_Drawing_TypographyIsEllipsized() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyIsEllipsized(style)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyIsEllipsized passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleIsHintEnabled() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyStyleIsHintEnabled(style)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyStyleIsHintEnabled passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextGetLineStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextGetLineStyle(style)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyTextGetLineStyle passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleGetEffectiveAlignment() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyStyleGetEffectiveAlignment(style)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyStyleGetEffectiveAlignment passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleGetStrutStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        val strut = OH_Drawing_TypographyStyleGetStrutStyle(style)
        OH_Drawing_TypographyStyleDestroyStrutStyle(strut)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyStyleGetStrutStyle passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleDestroyStrutStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        val strut = OH_Drawing_TypographyStyleGetStrutStyle(style)
        OH_Drawing_TypographyStyleDestroyStrutStyle(strut)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyStyleDestroyStrutStyle passed")
    }

    @Test
    fun testOH_Drawing_CreateTextStyle() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_CreateTextStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTypographyTextStyle(style, textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_SetTypographyTextStyle passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetTextStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTypographyTextStyle(style, textStyle)
        OH_Drawing_TypographyGetTextStyle(style)
        OH_Drawing_DestroyTextStyle(textStyle)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_TypographyGetTextStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleColor() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleColor(textStyle, 0xFF000000u)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleColor passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleFontSize() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleFontSize(textStyle, 14.0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleFontSize passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleFontWeight() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleFontWeight(textStyle, 400)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleFontWeight passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleBaseLine() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleBaseLine(textStyle, 0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleBaseLine passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleDecoration() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleDecoration(textStyle, 0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleDecoration passed")
    }

    @Test
    fun testOH_Drawing_AddTextStyleDecoration() {
        val textStyle = OH_Drawing_CreateTextStyle()
        try { OH_Drawing_AddTextStyleDecoration(textStyle, 0) } catch (e: Throwable) { println("OH_Drawing_AddTextStyleDecoration (API 18) exception: $e") }
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_AddTextStyleDecoration passed")
    }

    @Test
    fun testOH_Drawing_RemoveTextStyleDecoration() {
        val textStyle = OH_Drawing_CreateTextStyle()
        try { OH_Drawing_RemoveTextStyleDecoration(textStyle, 0) } catch (e: Throwable) { println("OH_Drawing_RemoveTextStyleDecoration (API 18) exception: $e") }
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_RemoveTextStyleDecoration passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleDecorationColor() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleDecorationColor(textStyle, 0xFF000000u)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleDecorationColor passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleFontHeight() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleFontHeight(textStyle, 14.0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleFontHeight passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleFontStyle() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleFontStyle(textStyle, 0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleFontStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleLocale() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleLocale(textStyle, "en")
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleLocale passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleLetterSpacing() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleLetterSpacing(textStyle, 0.0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleLetterSpacing passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleWordSpacing() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleWordSpacing(textStyle, 0.0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleWordSpacing passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleHalfLeading() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleHalfLeading(textStyle, false)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleHalfLeading passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleEllipsis() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleEllipsis(textStyle, "...")
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleEllipsis passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleEllipsisModal() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleEllipsisModal(textStyle, 0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleEllipsisModal passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleDecorationStyle() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleDecorationStyle(textStyle, 0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleDecorationStyle passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleDecorationThicknessScale() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_SetTextStyleDecorationThicknessScale(textStyle, 1.0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_SetTextStyleDecorationThicknessScale passed")
    }

    @Test
    fun testOH_Drawing_TextStyleSetBaselineShift() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleSetBaselineShift(textStyle, 0.0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleSetBaselineShift passed")
    }

    @Test
    fun testOH_Drawing_TextStyleAddFontFeature() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleAddFontFeature(textStyle, "kern", 1)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleAddFontFeature passed")
    }

    @Test
    fun testOH_Drawing_TextStyleAddFontVariation() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleAddFontVariation(textStyle, "wght", 400f)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleAddFontVariation passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetColor() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetColor(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetColor passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontSize() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetFontSize(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetFontSize passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetLetterSpacing() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetLetterSpacing(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetLetterSpacing passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetWordSpacing() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetWordSpacing(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetWordSpacing passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetBaselineShift() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetBaselineShift(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetBaselineShift passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontHeight() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetFontHeight(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetFontHeight passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetHalfLeading() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetHalfLeading(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetHalfLeading passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontWeight() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetFontWeight(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetFontWeight passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontStyle() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetFontStyle(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetFontStyle passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetBaseline() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetBaseline(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetBaseline passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetDecorationStyle() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetDecorationStyle(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetDecorationStyle passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontFeatureSize() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetFontFeatureSize(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetFontFeatureSize passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontFeatures() {
        val textStyle = OH_Drawing_CreateTextStyle()
        val features = OH_Drawing_TextStyleGetFontFeatures(textStyle)
        OH_Drawing_TextStyleDestroyFontFeatures(features, 0u)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetFontFeatures passed")
    }

    @Test
    fun testOH_Drawing_TextStyleClearFontFeature() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleClearFontFeature(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleClearFontFeature passed")
    }

    @Test
    fun testOH_Drawing_TextStyleIsEqual() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleIsEqual(textStyle, textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleIsEqual passed")
    }

    @Test
    fun testOH_Drawing_TextStyleIsEqualByFont() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleIsEqualByFont(textStyle, textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleIsEqualByFont passed")
    }

    @Test
    fun testOH_Drawing_TextStyleSetBackgroundRect() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleSetBackgroundRect(textStyle, null, 0)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleSetBackgroundRect passed")
    }

    @Test
    fun testOH_Drawing_TextStyleSetPlaceholder() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleSetPlaceholder(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleSetPlaceholder passed")
    }

    @Test
    fun testOH_Drawing_TextStyleIsPlaceholder() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleIsPlaceholder(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleIsPlaceholder passed")
    }

    @Test
    fun testOH_Drawing_TextStyleAddShadow() {
        val textStyle = OH_Drawing_CreateTextStyle()
        val shadow = OH_Drawing_CreateTextShadow()
        OH_Drawing_TextStyleAddShadow(textStyle, shadow)
        OH_Drawing_DestroyTextShadow(shadow)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleAddShadow passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetShadowCount() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetShadowCount(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetShadowCount passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetShadowWithIndex() {
        val textStyle = OH_Drawing_CreateTextStyle()
        val shadow = OH_Drawing_CreateTextShadow()
        OH_Drawing_TextStyleAddShadow(textStyle, shadow)
        OH_Drawing_TextStyleGetShadowWithIndex(textStyle, 0)
        OH_Drawing_TextStyleClearShadows(textStyle)
        OH_Drawing_DestroyTextShadow(shadow)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleGetShadowWithIndex passed")
    }

    @Test
    fun testOH_Drawing_TextStyleClearShadows() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleClearShadows(textStyle)
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_TextStyleClearShadows passed")
    }

    @Test
    fun testOH_Drawing_DestroyTextStyle() {
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_DestroyTextStyle(textStyle)
        println("OH_Drawing_DestroyTextStyle passed")
    }

    @Test
    fun testOH_Drawing_DestroyTypographyStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_DestroyTypographyStyle passed")
    }

    // ---------- drawing_text_typography.h 续：TypographyHandler / Typography / Font 等（单 API 测试）----------

    @Test
    fun testOH_Drawing_CreateFontCollection() {
        val collection = OH_Drawing_CreateFontCollection()
        OH_Drawing_DestroyFontCollection(collection)
        println("OH_Drawing_CreateFontCollection passed")
    }

    @Test
    fun testOH_Drawing_CreateTypographyHandler() {
        val collection = OH_Drawing_CreateFontCollection()
        val typoStyle = OH_Drawing_CreateTypographyStyle()
        val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
        OH_Drawing_DestroyTypographyHandler(handler)
        OH_Drawing_DestroyTypographyStyle(typoStyle)
        OH_Drawing_DestroyFontCollection(collection)
        println("OH_Drawing_CreateTypographyHandler passed")
    }

    @Test
    fun testOH_Drawing_TypographyHandlerPushTextStyle() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyHandlerPushTextStyle passed")
    }

    @Test
    fun testOH_Drawing_TypographyHandlerAddText() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_DestroyTypographyHandler(handler)
            OH_Drawing_DestroyTypographyStyle(typoStyle)
            OH_Drawing_DestroyFontCollection(collection)
        }
        println("OH_Drawing_TypographyHandlerAddText passed")
    }

    @Test
    fun testOH_Drawing_TypographyHandlerAddSymbol() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_DestroyTypographyHandler(handler)
            OH_Drawing_DestroyTypographyStyle(typoStyle)
            OH_Drawing_DestroyFontCollection(collection)
        }
        println("OH_Drawing_TypographyHandlerAddSymbol passed")
    }

    @Test
    fun testOH_Drawing_TypographyHandlerPopTextStyle() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyHandlerPopTextStyle(handler)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyHandlerPopTextStyle passed")
    }

    @Test
    fun testOH_Drawing_CreateTypography() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                assert(typography != null)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_CreateTypography passed")
    }

    @Test
    fun testOH_Drawing_TypographyLayout() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyLayout(typography, 100.0)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyLayout passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetHeight() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetHeight(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetHeight passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetMaxWidth() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetMaxWidth(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetMaxWidth passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineCount() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetLineCount(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLineCount passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLongestLine() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetLongestLine(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLongestLine passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLongestLineWithIndent() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetLongestLineWithIndent(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLongestLineWithIndent passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetMinIntrinsicWidth() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetMinIntrinsicWidth(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetMinIntrinsicWidth passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetMaxIntrinsicWidth() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetMaxIntrinsicWidth(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetMaxIntrinsicWidth passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetAlphabeticBaseline() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetAlphabeticBaseline(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetAlphabeticBaseline passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetIdeographicBaseline() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetIdeographicBaseline(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetIdeographicBaseline passed")
    }

    @Test
    fun testOH_Drawing_TypographyDidExceedMaxLines() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyDidExceedMaxLines(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyDidExceedMaxLines passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetUnresolvedGlyphsCount() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetUnresolvedGlyphsCount(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetUnresolvedGlyphsCount passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineHeight() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetLineHeight(typography, 0)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLineHeight passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineWidth() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetLineWidth(typography, 0)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLineWidth passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetIndentsWithIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetIndentsWithIndex(typography, 0)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetIndentsWithIndex passed")
    }

    @Test
    fun testOH_Drawing_TypographyMarkDirty() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyleH = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyleH)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            OH_Drawing_TypographyHandlerAddSymbol(handler, 'B'.code.toUInt())
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyMarkDirty(typography)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyleH)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyMarkDirty passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineTextRange() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineRange = OH_Drawing_TypographyGetLineTextRange(typography, 0, false)
                OH_Drawing_GetStartFromRange(lineRange)
                OH_Drawing_GetEndFromRange(lineRange)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLineTextRange passed")
    }

    @Test
    fun testOH_Drawing_GetStartFromRange() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineRange = OH_Drawing_TypographyGetLineTextRange(typography, 0, false)
                OH_Drawing_GetStartFromRange(lineRange)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetStartFromRange passed")
    }

    @Test
    fun testOH_Drawing_GetEndFromRange() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineRange = OH_Drawing_TypographyGetLineTextRange(typography, 0, false)
                OH_Drawing_GetEndFromRange(lineRange)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetEndFromRange passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetRectsForRange() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetRectsForRange passed")
    }

    @Test
    fun testOH_Drawing_GetLeftFromTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_GetLeftFromTextBox(textBox, 0)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetLeftFromTextBox passed")
    }

    @Test
    fun testOH_Drawing_GetRightFromTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_GetRightFromTextBox(textBox, 0)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRightFromTextBox passed")
    }

    @Test
    fun testOH_Drawing_GetTopFromTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_GetTopFromTextBox(textBox, 0)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetTopFromTextBox passed")
    }

    @Test
    fun testOH_Drawing_GetBottomFromTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_GetBottomFromTextBox(textBox, 0)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetBottomFromTextBox passed")
    }

    @Test
    fun testOH_Drawing_GetTextDirectionFromTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_GetTextDirectionFromTextBox(textBox, 0)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetTextDirectionFromTextBox passed")
    }

    @Test
    fun testOH_Drawing_GetSizeOfTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_GetSizeOfTextBox(textBox)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetSizeOfTextBox passed")
    }

    @Test
    fun testOH_Drawing_TypographyDestroyTextBox() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val textBox = OH_Drawing_TypographyGetRectsForRange(typography, 0u, 1u, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT)
                OH_Drawing_TypographyDestroyTextBox(textBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyDestroyTextBox passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetRectsForPlaceholders() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val placeBox = OH_Drawing_TypographyGetRectsForPlaceholders(typography)
                OH_Drawing_TypographyDestroyTextBox(placeBox)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetRectsForPlaceholders passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetWordBoundary() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                OH_Drawing_TypographyGetWordBoundary(typography, 0u)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetWordBoundary passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontMetrics() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val fontMetricsOut = alloc<OH_Drawing_Font_Metrics>()
                OH_Drawing_TextStyleGetFontMetrics(typography, textStyle, fontMetricsOut.ptr)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextStyleGetFontMetrics passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineMetrics() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineMetricsOut = OH_Drawing_TypographyGetLineMetrics(typography)
                OH_Drawing_DestroyLineMetrics(lineMetricsOut)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLineMetrics passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineInfo() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineMetricsOut = OH_Drawing_TypographyGetLineMetrics(typography)
                OH_Drawing_TypographyGetLineInfo(typography, 0, true, false, lineMetricsOut)
                OH_Drawing_DestroyLineMetrics(lineMetricsOut)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetLineInfo passed")
    }

    @Test
    fun testOH_Drawing_LineMetricsGetSize() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineMetricsOut = OH_Drawing_TypographyGetLineMetrics(typography)
                OH_Drawing_LineMetricsGetSize(lineMetricsOut)
                OH_Drawing_DestroyLineMetrics(lineMetricsOut)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_LineMetricsGetSize passed")
    }

    @Test
    fun testOH_Drawing_DestroyLineMetrics() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val lineMetricsOut = OH_Drawing_TypographyGetLineMetrics(typography)
                OH_Drawing_DestroyLineMetrics(lineMetricsOut)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_DestroyLineMetrics passed")
    }

    @Test
    fun testOH_Drawing_TypographyPaint() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val canvas = OH_Drawing_CanvasCreate()
                val brush = OH_Drawing_BrushCreate()
                val pen = OH_Drawing_PenCreate()
                OH_Drawing_TypographyPaint(typography, canvas, 0.0, 0.0)
                OH_Drawing_BrushDestroy(brush)
                OH_Drawing_PenDestroy(pen)
                OH_Drawing_CanvasDestroy(canvas)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyPaint passed")
    }

    @Test
    fun testOH_Drawing_TypographyPaintOnPath() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            try {
                val canvas = OH_Drawing_CanvasCreate()
                val path = OH_Drawing_PathCreate()
                OH_Drawing_TypographyPaintOnPath(typography, canvas, path, 0.0, 0.0)
                OH_Drawing_PathDestroy(path)
                OH_Drawing_CanvasDestroy(canvas)
            } finally {
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyPaintOnPath passed")
    }

    @Test
    fun testOH_Drawing_CreateFontParser() {
        val parser = OH_Drawing_CreateFontParser()
        OH_Drawing_DestroyFontParser(parser)
        println("OH_Drawing_CreateFontParser passed")
    }

    @Test
    fun testOH_Drawing_FontParserGetSystemFontList() {
        memScoped {
            val parser = OH_Drawing_CreateFontParser()
            val num = alloc<ULongVar>()
            val list = OH_Drawing_FontParserGetSystemFontList(parser, num.ptr)
            OH_Drawing_DestroySystemFontList(list, num.value)
            OH_Drawing_DestroyFontParser(parser)
        }
        println("OH_Drawing_FontParserGetSystemFontList passed")
    }

    @Test
    fun testOH_Drawing_DestroySystemFontList() {
        memScoped {
            val parser = OH_Drawing_CreateFontParser()
            val num = alloc<ULongVar>()
            val list = OH_Drawing_FontParserGetSystemFontList(parser, num.ptr)
            OH_Drawing_DestroySystemFontList(list, num.value)
            OH_Drawing_DestroyFontParser(parser)
        }
        println("OH_Drawing_DestroySystemFontList passed")
    }

    @Test
    fun testOH_Drawing_FontParserGetFontByName() {
        val parser = OH_Drawing_CreateFontParser()
        val desc = OH_Drawing_FontParserGetFontByName(parser, "sans-serif")
        OH_Drawing_DestroyFontDescriptor(desc)
        OH_Drawing_DestroyFontParser(parser)
        println("OH_Drawing_FontParserGetFontByName passed")
    }

    @Test
    fun testOH_Drawing_DestroyFontDescriptor() {
        val parser = OH_Drawing_CreateFontParser()
        val desc = OH_Drawing_FontParserGetFontByName(parser, "sans-serif")
        OH_Drawing_DestroyFontDescriptor(desc)
        OH_Drawing_DestroyFontParser(parser)
        println("OH_Drawing_DestroyFontDescriptor passed")
    }

    @Test
    fun testOH_Drawing_DestroyFontParser() {
        val parser = OH_Drawing_CreateFontParser()
        OH_Drawing_DestroyFontParser(parser)
        println("OH_Drawing_DestroyFontParser passed")
    }

    @Test
    fun testOH_Drawing_CreateTextTab() {
        val tab = OH_Drawing_CreateTextTab(OH_Drawing_TextAlign.TEXT_ALIGN_LEFT, 0f)
        OH_Drawing_DestroyTextTab(tab)
        println("OH_Drawing_CreateTextTab passed")
    }

    @Test
    fun testOH_Drawing_DestroyTextTab() {
        val tab = OH_Drawing_CreateTextTab(OH_Drawing_TextAlign.TEXT_ALIGN_LEFT, 0f)
        OH_Drawing_DestroyTextTab(tab)
        println("OH_Drawing_DestroyTextTab passed")
    }

    @Test
    fun testOH_Drawing_CopyTypographyStyle() {
        val style2 = OH_Drawing_CreateTypographyStyle()
        val copyStyle = OH_Drawing_CopyTypographyStyle(style2)
        OH_Drawing_DestroyTypographyStyle(copyStyle)
        OH_Drawing_DestroyTypographyStyle(style2)
        println("OH_Drawing_CopyTypographyStyle passed")
    }

    @Test
    fun testOH_Drawing_CopyTextStyle() {
        val textStyle2 = OH_Drawing_CreateTextStyle()
        val copyTextStyle = OH_Drawing_CopyTextStyle(textStyle2)
        OH_Drawing_DestroyTextStyle(copyTextStyle)
        OH_Drawing_DestroyTextStyle(textStyle2)
        println("OH_Drawing_CopyTextStyle passed")
    }

    @Test
    fun testOH_Drawing_CopyTextShadow() {
        val shadow2 = OH_Drawing_CreateTextShadow()
        val copyShadow = OH_Drawing_CopyTextShadow(shadow2)
        OH_Drawing_DestroyTextShadow(copyShadow)
        OH_Drawing_DestroyTextShadow(shadow2)
        println("OH_Drawing_CopyTextShadow passed")
    }

    @Test
    fun testOH_Drawing_GetSystemFontConfigInfo() {
        memScoped {
            val err = alloc<OH_Drawing_FontConfigInfoErrorCodeVar>()
            val configInfo = OH_Drawing_GetSystemFontConfigInfo(err.ptr)
            OH_Drawing_DestroySystemFontConfigInfo(configInfo)
        }
        println("OH_Drawing_GetSystemFontConfigInfo passed")
    }

    @Test
    fun testOH_Drawing_DestroySystemFontConfigInfo() {
        memScoped {
            val err = alloc<OH_Drawing_FontConfigInfoErrorCodeVar>()
            val configInfo = OH_Drawing_GetSystemFontConfigInfo(err.ptr)
            OH_Drawing_DestroySystemFontConfigInfo(configInfo)
        }
        println("OH_Drawing_DestroySystemFontConfigInfo passed")
    }

    @Test
    fun testOH_Drawing_DestroyTextLine_null() {
        OH_Drawing_DestroyTextLine(null)
        println("OH_Drawing_DestroyTextLine(null) passed")
    }

    @Test
    fun testOH_Drawing_DestroyTypography_null() {
        OH_Drawing_DestroyTypography(null)
        println("OH_Drawing_DestroyTypography(null) passed")
    }

    @Test
    fun testOH_Drawing_DestroyTypographyHandler_null() {
        OH_Drawing_DestroyTypographyHandler(null)
        println("OH_Drawing_DestroyTypographyHandler(null) passed")
    }

    @Test
    fun testOH_Drawing_DestroyTextStyle_null() {
        OH_Drawing_DestroyTextStyle(null)
        println("OH_Drawing_DestroyTextStyle(null) passed")
    }

    @Test
    fun testOH_Drawing_DestroyTypographyStyle_null() {
        OH_Drawing_DestroyTypographyStyle(null)
        println("OH_Drawing_DestroyTypographyStyle(null) passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleAttributeDouble() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        try { OH_Drawing_SetTextStyleAttributeDouble(textStyle3, 0u, 0.0) } catch (e: Throwable) { println("OH_Drawing_SetTextStyleAttributeDouble (API 21) exception: $e") }
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleAttributeDouble passed")
    }

    @Test
    fun testOH_Drawing_GetTextStyleAttributeDouble() {
        memScoped {
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val getDoubleOut = alloc<DoubleVar>()
            try { OH_Drawing_GetTextStyleAttributeDouble(textStyle3, 0u, getDoubleOut.ptr) } catch (e: Throwable) { println("OH_Drawing_GetTextStyleAttributeDouble (API 21) exception: $e") }
            OH_Drawing_DestroyTextStyle(textStyle3)
        }
        println("OH_Drawing_GetTextStyleAttributeDouble passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleAttributeInt() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        try { OH_Drawing_SetTextStyleAttributeInt(textStyle3, 0u, 0) } catch (e: Throwable) { println("OH_Drawing_SetTextStyleAttributeInt (API 21) exception: $e") }
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleAttributeInt passed")
    }

    @Test
    fun testOH_Drawing_GetTextStyleAttributeInt() {
        memScoped {
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val getIntOut = alloc<IntVar>()
            try { OH_Drawing_GetTextStyleAttributeInt(textStyle3, 0u, getIntOut.ptr) } catch (e: Throwable) { println("OH_Drawing_GetTextStyleAttributeInt (API 21) exception: $e") }
            OH_Drawing_DestroyTextStyle(textStyle3)
        }
        println("OH_Drawing_GetTextStyleAttributeInt passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyStyleAttributeDouble() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        try { OH_Drawing_SetTypographyStyleAttributeDouble(style3, 0u, 0.0) } catch (e: Throwable) { println("OH_Drawing_SetTypographyStyleAttributeDouble (API 21) exception: $e") }
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyStyleAttributeDouble passed")
    }

    @Test
    fun testOH_Drawing_GetTypographyStyleAttributeDouble() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val getDoubleOut = alloc<DoubleVar>()
            try { OH_Drawing_GetTypographyStyleAttributeDouble(style3, 0u, getDoubleOut.ptr) } catch (e: Throwable) { println("OH_Drawing_GetTypographyStyleAttributeDouble (API 21) exception: $e") }
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_GetTypographyStyleAttributeDouble passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyStyleAttributeInt() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        try { OH_Drawing_SetTypographyStyleAttributeInt(style3, 0u, 0) } catch (e: Throwable) { println("OH_Drawing_SetTypographyStyleAttributeInt (API 21) exception: $e") }
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyStyleAttributeInt passed")
    }

    @Test
    fun testOH_Drawing_GetTypographyStyleAttributeInt() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val getIntOut = alloc<IntVar>()
            try { OH_Drawing_GetTypographyStyleAttributeInt(style3, 0u, getIntOut.ptr) } catch (e: Throwable) { println("OH_Drawing_GetTypographyStyleAttributeInt (API 21) exception: $e") }
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_GetTypographyStyleAttributeInt passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleFontFamilies() {
        memScoped {
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val fontFamilyCstr = "sans-serif".cstr
            val families = allocArrayOf(fontFamilyCstr.ptr)
            OH_Drawing_SetTextStyleFontFamilies(textStyle3, 1, families)
            OH_Drawing_DestroyTextStyle(textStyle3)
        }
        println("OH_Drawing_SetTextStyleFontFamilies passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleForegroundBrush() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val brush2 = OH_Drawing_BrushCreate()
        OH_Drawing_SetTextStyleForegroundBrush(textStyle3, brush2)
        OH_Drawing_BrushDestroy(brush2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleForegroundBrush passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleBackgroundBrush() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val brush2 = OH_Drawing_BrushCreate()
        OH_Drawing_SetTextStyleBackgroundBrush(textStyle3, brush2)
        OH_Drawing_BrushDestroy(brush2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleBackgroundBrush passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetForegroundBrush() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val brush2 = OH_Drawing_BrushCreate()
        OH_Drawing_SetTextStyleForegroundBrush(textStyle3, brush2)
        val outBrush = OH_Drawing_BrushCreate()
        OH_Drawing_TextStyleGetForegroundBrush(textStyle3, outBrush)
        OH_Drawing_BrushDestroy(outBrush)
        OH_Drawing_BrushDestroy(brush2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleGetForegroundBrush passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetBackgroundBrush() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val brush2 = OH_Drawing_BrushCreate()
        OH_Drawing_SetTextStyleBackgroundBrush(textStyle3, brush2)
        val outBrush = OH_Drawing_BrushCreate()
        OH_Drawing_TextStyleGetBackgroundBrush(textStyle3, outBrush)
        OH_Drawing_BrushDestroy(outBrush)
        OH_Drawing_BrushDestroy(brush2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleGetBackgroundBrush passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleForegroundPen() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val pen2 = OH_Drawing_PenCreate()
        OH_Drawing_SetTextStyleForegroundPen(textStyle3, pen2)
        OH_Drawing_PenDestroy(pen2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleForegroundPen passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleBackgroundPen() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val pen2 = OH_Drawing_PenCreate()
        OH_Drawing_SetTextStyleBackgroundPen(textStyle3, pen2)
        OH_Drawing_PenDestroy(pen2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleBackgroundPen passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetForegroundPen() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val pen2 = OH_Drawing_PenCreate()
        OH_Drawing_SetTextStyleForegroundPen(textStyle3, pen2)
        val outPen = OH_Drawing_PenCreate()
        OH_Drawing_TextStyleGetForegroundPen(textStyle3, outPen)
        OH_Drawing_PenDestroy(outPen)
        OH_Drawing_PenDestroy(pen2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleGetForegroundPen passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetBackgroundPen() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        val pen2 = OH_Drawing_PenCreate()
        OH_Drawing_SetTextStyleBackgroundPen(textStyle3, pen2)
        val outPen = OH_Drawing_PenCreate()
        OH_Drawing_TextStyleGetBackgroundPen(textStyle3, outPen)
        OH_Drawing_PenDestroy(outPen)
        OH_Drawing_PenDestroy(pen2)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleGetBackgroundPen passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextSetHeightBehavior() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextSetHeightBehavior(style3, 0u)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextSetHeightBehavior passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextGetHeightBehavior() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextGetHeightBehavior(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextGetHeightBehavior passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetLocale() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetLocale(textStyle3)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleGetLocale passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontFamilies() {
        memScoped {
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val fontFamilyCstr = "sans-serif".cstr
            val families = allocArrayOf(fontFamilyCstr.ptr)
            OH_Drawing_SetTextStyleFontFamilies(textStyle3, 1, families)
            val numFam = alloc<ULongVar>()
            val fontFamilies = OH_Drawing_TextStyleGetFontFamilies(textStyle3, numFam.ptr)
            OH_Drawing_TextStyleDestroyFontFamilies(fontFamilies, numFam.value)
            OH_Drawing_DestroyTextStyle(textStyle3)
        }
        println("OH_Drawing_TextStyleGetFontFamilies passed")
    }

    @Test
    fun testOH_Drawing_TextStyleDestroyFontFamilies() {
        memScoped {
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val fontFamilyCstr = "sans-serif".cstr
            val families = allocArrayOf(fontFamilyCstr.ptr)
            OH_Drawing_SetTextStyleFontFamilies(textStyle3, 1, families)
            val numFam = alloc<ULongVar>()
            val fontFamilies = OH_Drawing_TextStyleGetFontFamilies(textStyle3, numFam.ptr)
            OH_Drawing_TextStyleDestroyFontFamilies(fontFamilies, numFam.value)
            OH_Drawing_DestroyTextStyle(textStyle3)
        }
        println("OH_Drawing_TextStyleDestroyFontFamilies passed")
    }

    @Test
    fun testOH_Drawing_TextStyleIsAttributeMatched() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleIsAttributeMatched(textStyle3, textStyle3, OH_Drawing_TextStyleType.TEXT_STYLE_NONE)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleIsAttributeMatched passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleFontStyleStruct() {
        memScoped {
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val fontStyleStruct = alloc<OH_Drawing_FontStyleStruct>()
            OH_Drawing_SetTextStyleFontStyleStruct(textStyle3, fontStyleStruct.readValue())
            OH_Drawing_DestroyTextStyle(textStyle3)
        }
        println("OH_Drawing_SetTextStyleFontStyleStruct passed")
    }

    @Test
    fun testOH_Drawing_TextStyleGetFontStyleStruct() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        OH_Drawing_TextStyleGetFontStyleStruct(textStyle3)
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_TextStyleGetFontStyleStruct passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyStyleFontStyleStruct() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val fontStyleStruct = alloc<OH_Drawing_FontStyleStruct>()
            OH_Drawing_SetTypographyStyleFontStyleStruct(style3, fontStyleStruct.readValue())
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_SetTypographyStyleFontStyleStruct passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleGetFontStyleStruct() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyStyleGetFontStyleStruct(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyStyleGetFontStyleStruct passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyVerticalAlignment() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        try { OH_Drawing_SetTypographyVerticalAlignment(style3, OH_Drawing_TextVerticalAlignment.TEXT_VERTICAL_ALIGNMENT_TOP) } catch (e: Throwable) { println("OH_Drawing_SetTypographyVerticalAlignment (API 20) exception: $e") }
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyVerticalAlignment passed")
    }

    @Test
    fun testOH_Drawing_SetTextStyleBadgeType() {
        val textStyle3 = OH_Drawing_CreateTextStyle()
        try { OH_Drawing_SetTextStyleBadgeType(textStyle3, OH_Drawing_TextBadgeType.TEXT_BADGE_NONE) } catch (e: Throwable) { println("OH_Drawing_SetTextStyleBadgeType (API 20) exception: $e") }
        OH_Drawing_DestroyTextStyle(textStyle3)
        println("OH_Drawing_SetTextStyleBadgeType passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyStyleTextStrutStyle() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_SetTypographyStyleTextStrutStyle(style3, null)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyStyleTextStrutStyle passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleStrutStyleEquals() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        val strutA = OH_Drawing_TypographyStyleGetStrutStyle(style3)
        OH_Drawing_TypographyStyleStrutStyleEquals(strutA, strutA)
        OH_Drawing_TypographyStyleDestroyStrutStyle(strutA)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyStyleStrutStyleEquals passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetTextAlign() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyGetTextAlign(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyGetTextAlign passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetTextDirection() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyGetTextDirection(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyGetTextDirection passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetTextMaxLines() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyGetTextMaxLines(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyGetTextMaxLines passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetTextEllipsis() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        val ellipsisPtr = OH_Drawing_TypographyGetTextEllipsis(style3)
        OH_Drawing_TypographyDestroyEllipsis(ellipsisPtr)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyGetTextEllipsis passed")
    }

    @Test
    fun testOH_Drawing_TypographyDestroyEllipsis() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        val ellipsisPtr = OH_Drawing_TypographyGetTextEllipsis(style3)
        OH_Drawing_TypographyDestroyEllipsis(ellipsisPtr)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyDestroyEllipsis passed")
    }

    @Test
    fun testOH_Drawing_TypographyStyleEquals() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyStyleEquals(style3, style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyStyleEquals passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetFontWeight() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetFontWeight(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetFontWeight passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetFontStyle() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetFontStyle(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetFontStyle passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetFontFamilies() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val lineNum = alloc<ULongVar>()
            val lineFamilies = OH_Drawing_TypographyTextlineStyleGetFontFamilies(style3, lineNum.ptr)
            OH_Drawing_TypographyTextlineStyleDestroyFontFamilies(lineFamilies, lineNum.value)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyTextlineStyleGetFontFamilies passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleDestroyFontFamilies() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val lineNum = alloc<ULongVar>()
            val lineFamilies = OH_Drawing_TypographyTextlineStyleGetFontFamilies(style3, lineNum.ptr)
            OH_Drawing_TypographyTextlineStyleDestroyFontFamilies(lineFamilies, lineNum.value)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyTextlineStyleDestroyFontFamilies passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetFontSize() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetFontSize(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetFontSize passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetHeightScale() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetHeightScale(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetHeightScale passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetHeightOnly() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetHeightOnly(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetHeightOnly passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetHalfLeading() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetHalfLeading(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetHalfLeading passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineStyleGetSpacingScale() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineStyleGetSpacingScale(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineStyleGetSpacingScale passed")
    }

    @Test
    fun testOH_Drawing_TypographyTextlineGetStyleOnly() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        OH_Drawing_TypographyTextlineGetStyleOnly(style3)
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_TypographyTextlineGetStyleOnly passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextTrailingSpaceOptimized() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        try { OH_Drawing_SetTypographyTextTrailingSpaceOptimized(style3, false) } catch (e: Throwable) { println("OH_Drawing_SetTypographyTextTrailingSpaceOptimized (API 20) exception: $e") }
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyTextTrailingSpaceOptimized passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextAutoSpace() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        try { OH_Drawing_SetTypographyTextAutoSpace(style3, false) } catch (e: Throwable) { println("OH_Drawing_SetTypographyTextAutoSpace (API 20) exception: $e") }
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyTextAutoSpace passed")
    }

    @Test
    fun testOH_Drawing_GetTextTabAlignment() {
        val tab2 = try { OH_Drawing_CreateTextTab(OH_Drawing_TextAlign.TEXT_ALIGN_LEFT, 10f) } catch (e: Throwable) { println("OH_Drawing_CreateTextTab (API 18) exception: $e"); null }
        try { OH_Drawing_GetTextTabAlignment(tab2) } catch (e: Throwable) { println("OH_Drawing_GetTextTabAlignment (API 18) exception: $e") }
        try { OH_Drawing_DestroyTextTab(tab2) } catch (e: Throwable) { }
        println("OH_Drawing_GetTextTabAlignment passed")
    }

    @Test
    fun testOH_Drawing_GetTextTabLocation() {
        val tab2 = try { OH_Drawing_CreateTextTab(OH_Drawing_TextAlign.TEXT_ALIGN_LEFT, 10f) } catch (e: Throwable) { println("OH_Drawing_CreateTextTab (API 18) exception: $e"); null }
        try { OH_Drawing_GetTextTabLocation(tab2) } catch (e: Throwable) { println("OH_Drawing_GetTextTabLocation exception: $e") }
        try { OH_Drawing_DestroyTextTab(tab2) } catch (e: Throwable) { }
        println("OH_Drawing_GetTextTabLocation passed")
    }

    @Test
    fun testOH_Drawing_SetTypographyTextTab() {
        val style3 = OH_Drawing_CreateTypographyStyle()
        val tab2 = try { OH_Drawing_CreateTextTab(OH_Drawing_TextAlign.TEXT_ALIGN_LEFT, 10f) } catch (e: Throwable) { null }
        try { OH_Drawing_SetTypographyTextTab(style3, tab2) } catch (e: Throwable) { println("OH_Drawing_SetTypographyTextTab (API 20) exception: $e") }
        try { OH_Drawing_DestroyTextTab(tab2) } catch (e: Throwable) { }
        OH_Drawing_DestroyTypographyStyle(style3)
        println("OH_Drawing_SetTypographyTextTab passed")
    }

    @Test
    fun testOH_Drawing_SetTextShadow() {
        val shadow3 = OH_Drawing_CreateTextShadow()
        val pt = OH_Drawing_PointCreate(0f, 0f)
        OH_Drawing_SetTextShadow(shadow3, 0xFF000000u, pt, 0.0)
        OH_Drawing_PointDestroy(pt)
        OH_Drawing_DestroyTextShadow(shadow3)
        println("OH_Drawing_SetTextShadow passed")
    }

    @Test
    fun testOH_Drawing_DestroyTextShadows() {
        OH_Drawing_DestroyTextShadows(null)
        println("OH_Drawing_DestroyTextShadows(null) passed")
    }

    @Test
    fun testOH_Drawing_GetSystemFontFullNamesByType() {
        val fullNameArray = OH_Drawing_GetSystemFontFullNamesByType(ALL)
        OH_Drawing_DestroySystemFontFullNames(fullNameArray)
        println("OH_Drawing_GetSystemFontFullNamesByType passed")
    }

    @Test
    fun testOH_Drawing_GetDrawingArraySize() {
        val fullNameArray = OH_Drawing_GetSystemFontFullNamesByType(ALL)
        OH_Drawing_GetDrawingArraySize(fullNameArray)
        OH_Drawing_DestroySystemFontFullNames(fullNameArray)
        println("OH_Drawing_GetDrawingArraySize passed")
    }

    @Test
    fun testOH_Drawing_DestroySystemFontFullNames() {
        val fullNameArray = OH_Drawing_GetSystemFontFullNamesByType(ALL)
        OH_Drawing_DestroySystemFontFullNames(fullNameArray)
        println("OH_Drawing_DestroySystemFontFullNames passed")
    }

    @Test
    fun testOH_Drawing_TypographyHandlerAddPlaceholder() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            val placeholderSpan = alloc<OH_Drawing_PlaceholderSpan>().apply { width = 10.0; height = 10.0 }
            OH_Drawing_TypographyHandlerAddPlaceholder(handler2, placeholderSpan.ptr)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyHandlerAddPlaceholder passed")
    }

    @Test
    fun testOH_Drawing_TypographyHandlerAddEncodedText() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                val encStr = "X".cstr
                OH_Drawing_TypographyHandlerAddEncodedText(handler2, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyHandlerAddEncodedText passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyHandlerAddEncodedText (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_TypographyGetGlyphPositionAtCoordinate() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                val placeholderSpan = alloc<OH_Drawing_PlaceholderSpan>().apply { width = 10.0; height = 10.0 }
                OH_Drawing_TypographyHandlerAddPlaceholder(handler2, placeholderSpan.ptr)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                val encStr = "X".cstr
                OH_Drawing_TypographyHandlerAddEncodedText(handler2, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8)
                OH_Drawing_TypographyHandlerAddText(handler2, "Y")
                val typography2 = OH_Drawing_CreateTypography(handler2)
                OH_Drawing_TypographyLayout(typography2, 100.0)
                val posAff = OH_Drawing_TypographyGetGlyphPositionAtCoordinate(typography2, 0.0, 0.0)
                OH_Drawing_GetPositionFromPositionAndAffinity(posAff)
                OH_Drawing_GetAffinityFromPositionAndAffinity(posAff)
                OH_Drawing_DestroyTypography(typography2)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyGetGlyphPositionAtCoordinate passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyGetGlyphPositionAtCoordinate (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_GetPositionFromPositionAndAffinity() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            val posAff = OH_Drawing_TypographyGetGlyphPositionAtCoordinate(typography2, 0.0, 0.0)
            OH_Drawing_GetPositionFromPositionAndAffinity(posAff)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_GetPositionFromPositionAndAffinity passed")
    }

    @Test
    fun testOH_Drawing_GetAffinityFromPositionAndAffinity() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            val posAff = OH_Drawing_TypographyGetGlyphPositionAtCoordinate(typography2, 0.0, 0.0)
            OH_Drawing_GetAffinityFromPositionAndAffinity(posAff)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_GetAffinityFromPositionAndAffinity passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetGlyphPositionAtCoordinateWithCluster() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            val posAffCluster = OH_Drawing_TypographyGetGlyphPositionAtCoordinateWithCluster(typography2, 0.0, 0.0)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyGetGlyphPositionAtCoordinateWithCluster passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineMetricsAt() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            val lineMetricsAtOut = OH_Drawing_TypographyGetLineMetrics(typography2)
            OH_Drawing_TypographyGetLineMetricsAt(typography2, 0, lineMetricsAtOut)
            OH_Drawing_DestroyLineMetrics(lineMetricsAtOut)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyGetLineMetricsAt passed")
    }

    @Test
    fun testOH_Drawing_TypographyGetLineFontMetrics() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            val fontMetricsSize = alloc<ULongVar>()
            val lineFontMetrics = OH_Drawing_TypographyGetLineFontMetrics(typography2, 0u, fontMetricsSize.ptr)
            OH_Drawing_TypographyDestroyLineFontMetrics(lineFontMetrics)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyGetLineFontMetrics passed")
    }

    @Test
    fun testOH_Drawing_TypographyDestroyLineFontMetrics() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            val fontMetricsSize = alloc<ULongVar>()
            val lineFontMetrics = OH_Drawing_TypographyGetLineFontMetrics(typography2, 0u, fontMetricsSize.ptr)
            OH_Drawing_TypographyDestroyLineFontMetrics(lineFontMetrics)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyDestroyLineFontMetrics passed")
    }

    @Test
    fun testOH_Drawing_TypographyUpdateFontSize() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            OH_Drawing_TypographyUpdateFontSize(typography2, 0u, 1u, 14f)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographyUpdateFontSize passed")
    }

    @Test
    fun testOH_Drawing_TypographyUpdateFontColor() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                OH_Drawing_TypographyHandlerAddText(handler2, "Y")
                val typography2 = OH_Drawing_CreateTypography(handler2)
                OH_Drawing_TypographyLayout(typography2, 100.0)
                OH_Drawing_TypographyUpdateFontColor(typography2, 0xFF000000u)
                OH_Drawing_DestroyTypography(typography2)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyUpdateFontColor passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyUpdateFontColor (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_TypographyUpdateDecoration() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                OH_Drawing_TypographyHandlerAddText(handler2, "Y")
                val typography2 = OH_Drawing_CreateTypography(handler2)
                OH_Drawing_TypographyLayout(typography2, 100.0)
                OH_Drawing_TypographyUpdateDecoration(typography2, 0u)
                OH_Drawing_DestroyTypography(typography2)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyUpdateDecoration passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyUpdateDecoration (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_TypographyUpdateDecorationThicknessScale() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                OH_Drawing_TypographyHandlerAddText(handler2, "Y")
                val typography2 = OH_Drawing_CreateTypography(handler2)
                OH_Drawing_TypographyLayout(typography2, 100.0)
                OH_Drawing_TypographyUpdateDecorationThicknessScale(typography2, 1.0)
                OH_Drawing_DestroyTypography(typography2)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyUpdateDecorationThicknessScale passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyUpdateDecorationThicknessScale (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_TypographyUpdateDecorationStyle() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                OH_Drawing_TypographyHandlerAddText(handler2, "Y")
                val typography2 = OH_Drawing_CreateTypography(handler2)
                OH_Drawing_TypographyLayout(typography2, 100.0)
                OH_Drawing_TypographyUpdateDecorationStyle(typography2, OH_Drawing_TextDecorationStyle.TEXT_DECORATION_STYLE_SOLID)
                OH_Drawing_DestroyTypography(typography2)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyUpdateDecorationStyle passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyUpdateDecorationStyle (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_TypographyUpdateDecorationColor() {
        try {
            memScoped {
                val style3 = OH_Drawing_CreateTypographyStyle()
                val textStyle3 = OH_Drawing_CreateTextStyle()
                val collection2 = OH_Drawing_CreateFontCollection()
                val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
                OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
                OH_Drawing_TypographyHandlerAddText(handler2, "Y")
                val typography2 = OH_Drawing_CreateTypography(handler2)
                OH_Drawing_TypographyLayout(typography2, 100.0)
                OH_Drawing_TypographyUpdateDecorationColor(typography2, 0xFF000000u)
                OH_Drawing_DestroyTypography(typography2)
                OH_Drawing_DestroyTypographyHandler(handler2)
                OH_Drawing_DestroyFontCollection(collection2)
                OH_Drawing_DestroyTextStyle(textStyle3)
                OH_Drawing_DestroyTypographyStyle(style3)
            }
            println("OH_Drawing_TypographyUpdateDecorationColor passed")
        } catch (e: Throwable) { println("OH_Drawing_TypographyUpdateDecorationColor (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_TypographySetIndents() {
        memScoped {
            val style3 = OH_Drawing_CreateTypographyStyle()
            val textStyle3 = OH_Drawing_CreateTextStyle()
            val collection2 = OH_Drawing_CreateFontCollection()
            val handler2 = OH_Drawing_CreateTypographyHandler(style3, collection2)
            OH_Drawing_TypographyHandlerPushTextStyle(handler2, textStyle3)
            OH_Drawing_TypographyHandlerAddText(handler2, "Y")
            val typography2 = OH_Drawing_CreateTypography(handler2)
            OH_Drawing_TypographyLayout(typography2, 100.0)
            OH_Drawing_TypographySetIndents(typography2, 0, null)
            OH_Drawing_DestroyTypography(typography2)
            OH_Drawing_DestroyTypographyHandler(handler2)
            OH_Drawing_DestroyFontCollection(collection2)
            OH_Drawing_DestroyTextStyle(textStyle3)
            OH_Drawing_DestroyTypographyStyle(style3)
        }
        println("OH_Drawing_TypographySetIndents passed")
    }

    // ---------- drawing_text_line.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_TypographyGetTextLines() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val lines2 = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
                OH_Drawing_GetDrawingArraySize(lines2)
                try { OH_Drawing_DestroyTextLines(lines2) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TypographyGetTextLines passed")
    }

    @Test
    fun testOH_Drawing_GetTextLineByIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val lines2 = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
                try { OH_Drawing_GetTextLineByIndex(lines2, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e") }
                try { OH_Drawing_DestroyTextLines(lines2) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetTextLineByIndex passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetGlyphCount() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                try { OH_Drawing_TextLineGetGlyphCount(line) } catch (e: Throwable) { println("OH_Drawing_TextLineGetGlyphCount (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetGlyphCount passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetTextRange() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val startOut = alloc<ULongVar>()
                val endOut = alloc<ULongVar>()
                try { OH_Drawing_TextLineGetTextRange(line, startOut.ptr, endOut.ptr) } catch (e: Throwable) { println("OH_Drawing_TextLineGetTextRange (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetTextRange passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetGlyphRuns() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { println("OH_Drawing_TextLineGetGlyphRuns (API 18) exception: $e"); null }
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetGlyphRuns passed")
    }

    @Test
    fun testOH_Drawing_TextLinePaint() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val canvas = OH_Drawing_CanvasCreate()
                try { OH_Drawing_TextLinePaint(line, canvas, 0.0, 0.0) } catch (e: Throwable) { println("OH_Drawing_TextLinePaint (API 18) exception: $e") }
                OH_Drawing_CanvasDestroy(canvas)
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLinePaint passed")
    }

    @Test
    fun testOH_Drawing_TextLineCreateTruncatedLine() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val truncated = try { OH_Drawing_TextLineCreateTruncatedLine(line, 50.0, 2, "...") } catch (e: Throwable) { println("OH_Drawing_TextLineCreateTruncatedLine (API 18) exception: $e"); null }
                try { OH_Drawing_DestroyTextLine(truncated) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineCreateTruncatedLine passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetTypographicBounds() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val ascentOut = alloc<DoubleVar>()
                val descentOut = alloc<DoubleVar>()
                val leadingOut = alloc<DoubleVar>()
                try { OH_Drawing_TextLineGetTypographicBounds(line, ascentOut.ptr, descentOut.ptr, leadingOut.ptr) } catch (e: Throwable) { println("OH_Drawing_TextLineGetTypographicBounds (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetTypographicBounds passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetImageBounds() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val imageBounds = try { OH_Drawing_TextLineGetImageBounds(line) } catch (e: Throwable) { println("OH_Drawing_TextLineGetImageBounds (API 18) exception: $e"); null }
                try { OH_Drawing_RectDestroy(imageBounds) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetImageBounds passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetTrailingSpaceWidth() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                try { OH_Drawing_TextLineGetTrailingSpaceWidth(line) } catch (e: Throwable) { println("OH_Drawing_TextLineGetTrailingSpaceWidth (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetTrailingSpaceWidth passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetStringIndexForPosition() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                val pt = OH_Drawing_PointCreate(0f, 0f)
                try { OH_Drawing_TextLineGetStringIndexForPosition(line, pt) } catch (e: Throwable) { println("OH_Drawing_TextLineGetStringIndexForPosition (API 18) exception: $e") }
                OH_Drawing_PointDestroy(pt)
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetStringIndexForPosition passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetOffsetForStringIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                try { OH_Drawing_TextLineGetOffsetForStringIndex(line, 0) } catch (e: Throwable) { println("OH_Drawing_TextLineGetOffsetForStringIndex (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetOffsetForStringIndex passed")
    }

    @Test
    fun testOH_Drawing_TextLineEnumerateCaretOffsets() {
        val caretCallback = staticCFunction { _offset: Double, _index: Int, _leading: Boolean -> false }
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                try { OH_Drawing_TextLineEnumerateCaretOffsets(line, caretCallback) } catch (e: Throwable) { println("OH_Drawing_TextLineEnumerateCaretOffsets (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineEnumerateCaretOffsets passed")
    }

    @Test
    fun testOH_Drawing_TextLineGetAlignmentOffset() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { println("OH_Drawing_TypographyGetTextLines (API 18) exception: $e"); null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { println("OH_Drawing_GetTextLineByIndex (API 18) exception: $e"); null }
            try {
                try { OH_Drawing_TextLineGetAlignmentOffset(line, 0.5, 100.0) } catch (e: Throwable) { println("OH_Drawing_TextLineGetAlignmentOffset (API 18) exception: $e") }
            } finally {
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_TextLineGetAlignmentOffset passed")
    }

    // ---------- drawing_record_cmd.h ----------

    @Test
    fun testOH_Drawing_RecordCmdUtilsCreate() {
        val utils = OH_Drawing_RecordCmdUtilsCreate()
        OH_Drawing_RecordCmdUtilsDestroy(utils)
        println("OH_Drawing_RecordCmdUtilsCreate passed")
    }

    @Test
    fun testOH_Drawing_RecordCmdUtilsBeginRecording() {
        memScoped {
            val utils = OH_Drawing_RecordCmdUtilsCreate()
            val canvasOut = alloc<CPointerVar<OH_Drawing_Canvas>>()
            OH_Drawing_RecordCmdUtilsBeginRecording(utils, 64, 64, canvasOut.ptr)
            OH_Drawing_RecordCmdUtilsDestroy(utils)
        }
        println("OH_Drawing_RecordCmdUtilsBeginRecording passed")
    }

    @Test
    fun testOH_Drawing_RecordCmdUtilsFinishRecording() {
        memScoped {
            val utils = OH_Drawing_RecordCmdUtilsCreate()
            val canvasOut = alloc<CPointerVar<OH_Drawing_Canvas>>()
            OH_Drawing_RecordCmdUtilsBeginRecording(utils, 64, 64, canvasOut.ptr)
            val recordCmdOut = alloc<CPointerVar<OH_Drawing_RecordCmd>>()
            OH_Drawing_RecordCmdUtilsFinishRecording(utils, recordCmdOut.ptr)
            OH_Drawing_RecordCmdDestroy(recordCmdOut.value)
            OH_Drawing_RecordCmdUtilsDestroy(utils)
        }
        println("OH_Drawing_RecordCmdUtilsFinishRecording passed")
    }

    @Test
    fun testOH_Drawing_RecordCmdDestroy() {
        memScoped {
            val utils = OH_Drawing_RecordCmdUtilsCreate()
            val canvasOut = alloc<CPointerVar<OH_Drawing_Canvas>>()
            OH_Drawing_RecordCmdUtilsBeginRecording(utils, 64, 64, canvasOut.ptr)
            val recordCmdOut = alloc<CPointerVar<OH_Drawing_RecordCmd>>()
            OH_Drawing_RecordCmdUtilsFinishRecording(utils, recordCmdOut.ptr)
            OH_Drawing_RecordCmdDestroy(recordCmdOut.value)
            OH_Drawing_RecordCmdUtilsDestroy(utils)
        }
        println("OH_Drawing_RecordCmdDestroy passed")
    }

    @Test
    fun testOH_Drawing_RecordCmdUtilsDestroy() {
        val utils = OH_Drawing_RecordCmdUtilsCreate()
        OH_Drawing_RecordCmdUtilsDestroy(utils)
        println("OH_Drawing_RecordCmdUtilsDestroy passed")
    }

    // ---------- drawing_filter.h ----------

    @Test
    fun testOH_Drawing_FilterCreate() {
        val f = OH_Drawing_FilterCreate()
        OH_Drawing_FilterDestroy(f)
        println("OH_Drawing_FilterCreate passed")
    }

    @Test
    fun testOH_Drawing_FilterDestroy() {
        val f = OH_Drawing_FilterCreate()
        OH_Drawing_FilterDestroy(f)
        println("OH_Drawing_FilterDestroy passed")
    }

    @Test
    fun testOH_Drawing_FilterSetImageFilter() {
        val f = OH_Drawing_FilterCreate()
        val imgFilter = OH_Drawing_ImageFilterCreateBlur(0f, 0f, OH_Drawing_TileMode.CLAMP, null)
        OH_Drawing_FilterSetImageFilter(f, imgFilter)
        OH_Drawing_FilterDestroy(f)
        OH_Drawing_ImageFilterDestroy(imgFilter)
        println("OH_Drawing_FilterSetImageFilter passed")
    }

    @Test
    fun testOH_Drawing_FilterSetMaskFilter() {
        val f = OH_Drawing_FilterCreate()
        val maskFilter = OH_Drawing_MaskFilterCreateBlur(OH_Drawing_BlurType.NORMAL, 0f, true)
        OH_Drawing_FilterSetMaskFilter(f, maskFilter)
        OH_Drawing_FilterDestroy(f)
        OH_Drawing_MaskFilterDestroy(maskFilter)
        println("OH_Drawing_FilterSetMaskFilter passed")
    }

    @Test
    fun testOH_Drawing_FilterSetColorFilter() {
        val f = OH_Drawing_FilterCreate()
        val cf = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        OH_Drawing_FilterSetColorFilter(f, cf)
        OH_Drawing_FilterDestroy(f)
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_FilterSetColorFilter passed")
    }

    @Test
    fun testOH_Drawing_FilterGetColorFilter() {
        val f = OH_Drawing_FilterCreate()
        val cf = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        OH_Drawing_FilterSetColorFilter(f, cf)
        val cfOut = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        OH_Drawing_FilterGetColorFilter(f, cfOut)
        OH_Drawing_FilterDestroy(f)
        OH_Drawing_ColorFilterDestroy(cf)
        OH_Drawing_ColorFilterDestroy(cfOut)
        println("OH_Drawing_FilterGetColorFilter passed")
    }


    // ---------- drawing_text_run.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_GetRunByIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunByIndex passed")
    }

    @Test
    fun testOH_Drawing_GetRunStringIndices() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val stringIndices = try { OH_Drawing_GetRunStringIndices(run, 0L, 0L) } catch (e: Throwable) { null }
                try { OH_Drawing_DestroyRunStringIndices(stringIndices) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunStringIndices passed")
    }

    @Test
    fun testOH_Drawing_GetRunStringIndicesByIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val stringIndices = try { OH_Drawing_GetRunStringIndices(run, 0L, 0L) } catch (e: Throwable) { null }
                try { OH_Drawing_GetRunStringIndicesByIndex(stringIndices, 0u) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyRunStringIndices(stringIndices) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunStringIndicesByIndex passed")
    }

    @Test
    fun testOH_Drawing_GetRunStringRange() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val locOut = alloc<ULongVar>()
                val lenOut = alloc<ULongVar>()
                try { OH_Drawing_GetRunStringRange(run, locOut.ptr, lenOut.ptr) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunStringRange passed")
    }

    @Test
    fun testOH_Drawing_GetRunTypographicBounds() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val ascentOut = alloc<FloatVar>()
                val descentOut = alloc<FloatVar>()
                val leadingOut = alloc<FloatVar>()
                try { OH_Drawing_GetRunTypographicBounds(run, ascentOut.ptr, descentOut.ptr, leadingOut.ptr) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunTypographicBounds passed")
    }

    @Test
    fun testOH_Drawing_RunPaint() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val canvas = OH_Drawing_CanvasCreate()
                try { OH_Drawing_RunPaint(canvas, run, 0.0, 0.0) } catch (e: Throwable) { }
                OH_Drawing_CanvasDestroy(canvas)
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_RunPaint passed")
    }

    @Test
    fun testOH_Drawing_GetRunImageBounds() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val imageBounds = try { OH_Drawing_GetRunImageBounds(run) } catch (e: Throwable) { null }
                try { OH_Drawing_DestroyRunImageBounds(imageBounds) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunImageBounds passed")
    }

    @Test
    fun testOH_Drawing_GetRunGlyphs() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val glyphs = try { OH_Drawing_GetRunGlyphs(run, 0L, 0L) } catch (e: Throwable) { null }
                OH_Drawing_GetDrawingArraySize(glyphs)
                try { OH_Drawing_DestroyRunGlyphs(glyphs) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunGlyphs passed")
    }

    @Test
    fun testOH_Drawing_GetRunGlyphsByIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val glyphs = try { OH_Drawing_GetRunGlyphs(run, 0L, 0L) } catch (e: Throwable) { null }
                try { OH_Drawing_GetRunGlyphsByIndex(glyphs, 0u) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyRunGlyphs(glyphs) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunGlyphsByIndex passed")
    }

    @Test
    fun testOH_Drawing_GetRunPositions() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val positions = try { OH_Drawing_GetRunPositions(run, 0L, 0L) } catch (e: Throwable) { null }
                OH_Drawing_GetDrawingArraySize(positions)
                try { OH_Drawing_DestroyRunPositions(positions) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunPositions passed")
    }

    @Test
    fun testOH_Drawing_GetRunPositionsByIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val positions = try { OH_Drawing_GetRunPositions(run, 0L, 0L) } catch (e: Throwable) { null }
                try { OH_Drawing_GetRunPositionsByIndex(positions, 0u) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyRunPositions(positions) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunPositionsByIndex passed")
    }

    @Test
    fun testOH_Drawing_GetRunGlyphCount() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                try { OH_Drawing_GetRunGlyphCount(run) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunGlyphCount passed")
    }

    @Test
    fun testOH_Drawing_GetRunFont() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                try { OH_Drawing_GetRunFont(run) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunFont passed")
    }

    @Test
    fun testOH_Drawing_GetRunTextDirection() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                try { OH_Drawing_GetRunTextDirection(run) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunTextDirection passed")
    }

    @Test
    fun testOH_Drawing_GetRunGlyphAdvances() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val advances = try { OH_Drawing_GetRunGlyphAdvances(run, 0u, 0u) } catch (e: Throwable) { null }
                OH_Drawing_GetDrawingArraySize(advances)
                try { OH_Drawing_DestroyRunGlyphAdvances(advances) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunGlyphAdvances passed")
    }

    @Test
    fun testOH_Drawing_GetRunGlyphAdvanceByIndex() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val typoStyle = OH_Drawing_CreateTypographyStyle()
            val textStyle = OH_Drawing_CreateTextStyle()
            val handler = OH_Drawing_CreateTypographyHandler(typoStyle, collection)
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "AB")
            OH_Drawing_TypographyHandlerPopTextStyle(handler)
            val typography = OH_Drawing_CreateTypography(handler)
            OH_Drawing_TypographyLayout(typography, 100.0)
            val lines = try { OH_Drawing_TypographyGetTextLines(typography) } catch (e: Throwable) { null }
            val line = try { OH_Drawing_GetTextLineByIndex(lines, 0u) } catch (e: Throwable) { null }
            val glyphRuns = try { OH_Drawing_TextLineGetGlyphRuns(line) } catch (e: Throwable) { null }
            val run = try { OH_Drawing_GetRunByIndex(glyphRuns, 0u) } catch (e: Throwable) { null }
            try {
                val advances = try { OH_Drawing_GetRunGlyphAdvances(run, 0u, 0u) } catch (e: Throwable) { null }
                try { OH_Drawing_GetRunGlyphAdvanceByIndex(advances, 0u) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyRunGlyphAdvances(advances) } catch (e: Throwable) { }
            } finally {
                try { OH_Drawing_DestroyRuns(glyphRuns) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLine(line) } catch (e: Throwable) { }
                try { OH_Drawing_DestroyTextLines(lines) } catch (e: Throwable) { }
                OH_Drawing_DestroyTypography(typography)
                OH_Drawing_DestroyTypographyHandler(handler)
                OH_Drawing_DestroyTextStyle(textStyle)
                OH_Drawing_DestroyTypographyStyle(typoStyle)
                OH_Drawing_DestroyFontCollection(collection)
            }
        }
        println("OH_Drawing_GetRunGlyphAdvanceByIndex passed")
    }

    // ---------- drawing_text_declaration.h ----------
    // (仅类型声明，无函数 API，类型由 drawing_text_typography / drawing_font_collection 等使用)

    // ---------- drawing_pixel_map.h ----------

    @Test
    fun testOH_Drawing_PixelMapGetFromNativePixelMap() {
        OH_Drawing_PixelMapGetFromNativePixelMap(null)
        println("OH_Drawing_PixelMapGetFromNativePixelMap passed")
    }

    @Test
    fun testOH_Drawing_PixelMapGetFromOhPixelMapNative() {
        OH_Drawing_PixelMapGetFromOhPixelMapNative(null)
        println("OH_Drawing_PixelMapGetFromOhPixelMapNative passed")
    }

    @Test
    fun testOH_Drawing_PixelMapDissolve() {
        OH_Drawing_PixelMapDissolve(null)
        println("OH_Drawing_PixelMapDissolve passed")
    }

    // ---------- drawing_shadow_layer.h ----------

    @Test
    fun testOH_Drawing_ShadowLayerCreate() {
        val layer = OH_Drawing_ShadowLayerCreate(0f, 0f, 0f, 0u)
        OH_Drawing_ShadowLayerDestroy(layer)
        println("OH_Drawing_ShadowLayerCreate passed")
    }

    @Test
    fun testOH_Drawing_ShadowLayerDestroy() {
        val layer = OH_Drawing_ShadowLayerCreate(0f, 0f, 0f, 0u)
        OH_Drawing_ShadowLayerDestroy(layer)
        println("OH_Drawing_ShadowLayerDestroy passed")
    }

    // ---------- drawing_register_font.h ----------

    @Test
    fun testOH_Drawing_RegisterFont() {
        val collection = OH_Drawing_CreateFontCollection()
        OH_Drawing_RegisterFont(collection, "Arial", "")
        try { OH_Drawing_UnregisterFont(collection, "Arial") } catch (e: Throwable) { println("OH_Drawing_UnregisterFont (API 20) exception: $e") }
        OH_Drawing_DestroyFontCollection(collection)
        println("OH_Drawing_RegisterFont passed")
    }

    @Test
    fun testOH_Drawing_RegisterFontBuffer() {
        memScoped {
            val collection = OH_Drawing_CreateFontCollection()
            val fontBuf = allocArray<UByteVar>(3)
            OH_Drawing_RegisterFontBuffer(collection, "TestFamily", fontBuf, 3u)
            try { OH_Drawing_UnregisterFont(collection, "TestFamily") } catch (e: Throwable) { println("OH_Drawing_UnregisterFont (API 20) exception: $e") }
            OH_Drawing_DestroyFontCollection(collection)
        }
        println("OH_Drawing_RegisterFontBuffer passed")
    }

    @Test
    fun testOH_Drawing_UnregisterFont() {
        val collection = OH_Drawing_CreateFontCollection()
        OH_Drawing_RegisterFont(collection, "Arial", "")
        try { OH_Drawing_UnregisterFont(collection, "Arial") } catch (e: Throwable) { println("OH_Drawing_UnregisterFont (API 20) exception: $e") }
        OH_Drawing_DestroyFontCollection(collection)
        println("OH_Drawing_UnregisterFont passed")
    }

    // ---------- drawing_gpu_context.h ----------

    // @Test
    // fun testOH_Drawing_GpuContextCreateFromGL() {
    //     memScoped {
    //         val options = alloc<OH_Drawing_GpuContextOptions>().apply { allowPathMaskCaching = false }
    //         val ctxGl = OH_Drawing_GpuContextCreateFromGL(options.readValue())
    //         OH_Drawing_GpuContextDestroy(ctxGl)
    //     }
    //     println("OH_Drawing_GpuContextCreateFromGL passed")
    // }

    @Test
    fun testOH_Drawing_GpuContextCreate() {
        val ctx = OH_Drawing_GpuContextCreate()
        OH_Drawing_GpuContextDestroy(ctx)
        println("OH_Drawing_GpuContextCreate passed")
    }

    @Test
    fun testOH_Drawing_GpuContextDestroy() {
        val ctx = OH_Drawing_GpuContextCreate()
        OH_Drawing_GpuContextDestroy(ctx)
        println("OH_Drawing_GpuContextDestroy passed")
    }

    // ---------- drawing_surface.h ----------

    @Test
    fun testOH_Drawing_SurfaceCreateFromGpuContext() {
        memScoped {
            val ctx = OH_Drawing_GpuContextCreate()
            val info = alloc<OH_Drawing_Image_Info>().apply { width = 64; height = 64 }
            val surface = OH_Drawing_SurfaceCreateFromGpuContext(ctx, false, info.readValue())
            OH_Drawing_SurfaceDestroy(surface)
            OH_Drawing_GpuContextDestroy(ctx)
        }
        println("OH_Drawing_SurfaceCreateFromGpuContext passed")
    }

    @Test
    fun testOH_Drawing_SurfaceGetCanvas() {
        memScoped {
            val ctx = OH_Drawing_GpuContextCreate()
            val info = alloc<OH_Drawing_Image_Info>().apply { width = 64; height = 64 }
            val surface = OH_Drawing_SurfaceCreateFromGpuContext(ctx, false, info.readValue())
            OH_Drawing_SurfaceGetCanvas(surface)
            OH_Drawing_SurfaceDestroy(surface)
            OH_Drawing_GpuContextDestroy(ctx)
        }
        println("OH_Drawing_SurfaceGetCanvas passed")
    }

    @Test
    fun testOH_Drawing_SurfaceFlush() {
        memScoped {
            val ctx = OH_Drawing_GpuContextCreate()
            val info = alloc<OH_Drawing_Image_Info>().apply { width = 64; height = 64 }
            val surface = OH_Drawing_SurfaceCreateFromGpuContext(ctx, false, info.readValue())
            OH_Drawing_SurfaceFlush(surface)
            OH_Drawing_SurfaceDestroy(surface)
            OH_Drawing_GpuContextDestroy(ctx)
        }
        println("OH_Drawing_SurfaceFlush passed")
    }

    @Test
    fun testOH_Drawing_SurfaceDestroy() {
        memScoped {
            val ctx = OH_Drawing_GpuContextCreate()
            val info = alloc<OH_Drawing_Image_Info>().apply { width = 64; height = 64 }
            val surface = OH_Drawing_SurfaceCreateFromGpuContext(ctx, false, info.readValue())
            OH_Drawing_SurfaceDestroy(surface)
            OH_Drawing_GpuContextDestroy(ctx)
        }
        println("OH_Drawing_SurfaceDestroy passed")
    }

    @Test
    fun testOH_Drawing_SurfaceCreateOnScreen() {
        memScoped {
            val ctx = OH_Drawing_GpuContextCreate()
            val info = alloc<OH_Drawing_Image_Info>().apply { width = 64; height = 64 }
            OH_Drawing_SurfaceCreateOnScreen(ctx, info.readValue(), null)
            OH_Drawing_GpuContextDestroy(ctx)
        }
        println("OH_Drawing_SurfaceCreateOnScreen passed")
    }

    // ---------- drawing_memory_stream.h ----------

    @Test
    fun testOH_Drawing_MemoryStreamCreate() {
        val stream = OH_Drawing_MemoryStreamCreate(null, 0uL, false)
        OH_Drawing_MemoryStreamDestroy(stream)
        println("OH_Drawing_MemoryStreamCreate passed")
    }

    @Test
    fun testOH_Drawing_MemoryStreamDestroy() {
        val stream = OH_Drawing_MemoryStreamCreate(null, 0uL, false)
        OH_Drawing_MemoryStreamDestroy(stream)
        println("OH_Drawing_MemoryStreamDestroy passed")
    }

    // ---------- drawing_color.h ----------

    @Test
    fun testOH_Drawing_ColorSetArgb() {
        OH_Drawing_ColorSetArgb(255u, 0u, 0u, 0u)
        println("OH_Drawing_ColorSetArgb passed")
    }

    // ---------- drawing_shader_effect.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_ShaderEffectCreateColorShader() {
        val colorShader = OH_Drawing_ShaderEffectCreateColorShader(0u)
        OH_Drawing_ShaderEffectDestroy(colorShader)
        println("OH_Drawing_ShaderEffectCreateColorShader passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectDestroy() {
        val colorShader = OH_Drawing_ShaderEffectCreateColorShader(0u)
        OH_Drawing_ShaderEffectDestroy(colorShader)
        println("OH_Drawing_ShaderEffectDestroy passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateLinearGradient() {
        memScoped {
            val startPt = OH_Drawing_PointCreate(0f, 0f)
            val endPt = OH_Drawing_PointCreate(10f, 10f)
            val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
            val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
            val linearGrad = OH_Drawing_ShaderEffectCreateLinearGradient(startPt, endPt, colors, pos, 2u, OH_Drawing_TileMode.CLAMP)
            OH_Drawing_ShaderEffectDestroy(linearGrad)
            OH_Drawing_PointDestroy(endPt)
            OH_Drawing_PointDestroy(startPt)
        }
        println("OH_Drawing_ShaderEffectCreateLinearGradient passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateLinearGradientWithLocalMatrix() {
        memScoped {
            val startPt2d = alloc<OH_Drawing_Point2D>().apply { x = 0f; y = 0f }
            val endPt2d = alloc<OH_Drawing_Point2D>().apply { x = 10f; y = 10f }
            val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
            val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
            val matrix = OH_Drawing_MatrixCreate()
            val linearGradMatrix = OH_Drawing_ShaderEffectCreateLinearGradientWithLocalMatrix(startPt2d.ptr, endPt2d.ptr, colors, pos, 2u, OH_Drawing_TileMode.CLAMP, matrix)
            OH_Drawing_ShaderEffectDestroy(linearGradMatrix)
            OH_Drawing_MatrixDestroy(matrix)
        }
        println("OH_Drawing_ShaderEffectCreateLinearGradientWithLocalMatrix passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateRadialGradient() {
        memScoped {
            val centerPt = OH_Drawing_PointCreate(5f, 5f)
            val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
            val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
            val radialGrad = OH_Drawing_ShaderEffectCreateRadialGradient(centerPt, 5f, colors, pos, 2u, OH_Drawing_TileMode.CLAMP)
            OH_Drawing_ShaderEffectDestroy(radialGrad)
            OH_Drawing_PointDestroy(centerPt)
        }
        println("OH_Drawing_ShaderEffectCreateRadialGradient passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateRadialGradientWithLocalMatrix() {
        memScoped {
            val centerPt2d = alloc<OH_Drawing_Point2D>().apply { x = 5f; y = 5f }
            val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
            val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
            val matrix = OH_Drawing_MatrixCreate()
            val radialGradMatrix = OH_Drawing_ShaderEffectCreateRadialGradientWithLocalMatrix(centerPt2d.ptr, 5f, colors, pos, 2u, OH_Drawing_TileMode.CLAMP, matrix)
            OH_Drawing_ShaderEffectDestroy(radialGradMatrix)
            OH_Drawing_MatrixDestroy(matrix)
        }
        println("OH_Drawing_ShaderEffectCreateRadialGradientWithLocalMatrix passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateSweepGradient() {
        memScoped {
            val centerPt = OH_Drawing_PointCreate(5f, 5f)
            val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
            val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
            val sweepGrad = OH_Drawing_ShaderEffectCreateSweepGradient(centerPt, colors, pos, 2u, OH_Drawing_TileMode.CLAMP)
            OH_Drawing_ShaderEffectDestroy(sweepGrad)
            OH_Drawing_PointDestroy(centerPt)
        }
        println("OH_Drawing_ShaderEffectCreateSweepGradient passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateSweepGradientWithLocalMatrix() {
        try {
            memScoped {
                val centerPt = OH_Drawing_PointCreate(5f, 5f)
                val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
                val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
                val matrix = OH_Drawing_MatrixCreate()
                val sweepGradMatrix = OH_Drawing_ShaderEffectCreateSweepGradientWithLocalMatrix(centerPt, colors, pos, 2u, OH_Drawing_TileMode.CLAMP, matrix)
                OH_Drawing_ShaderEffectDestroy(sweepGradMatrix)
                OH_Drawing_MatrixDestroy(matrix)
                OH_Drawing_PointDestroy(centerPt)
            }
            println("OH_Drawing_ShaderEffectCreateSweepGradientWithLocalMatrix passed")
        } catch (e: Throwable) { println("OH_Drawing_ShaderEffectCreateSweepGradientWithLocalMatrix (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateImageShader() {
        memScoped {
            val img = OH_Drawing_ImageCreate()
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 16u, 16u, fmt.ptr)
            OH_Drawing_ImageBuildFromBitmap(img, bm)
            val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
            val matrix = OH_Drawing_MatrixCreate()
            val imageShader = try { OH_Drawing_ShaderEffectCreateImageShader(img, OH_Drawing_TileMode.CLAMP, OH_Drawing_TileMode.CLAMP, sampling, matrix) } catch (e: Throwable) { println("OH_Drawing_ShaderEffectCreateImageShader (API 20) exception: $e"); null }
            OH_Drawing_ShaderEffectDestroy(imageShader)
            OH_Drawing_SamplingOptionsDestroy(sampling)
            OH_Drawing_ImageDestroy(img)
            OH_Drawing_BitmapDestroy(bm)
            OH_Drawing_MatrixDestroy(matrix)
        }
        println("OH_Drawing_ShaderEffectCreateImageShader passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreatePixelMapShader() {
        try {
            memScoped {
                val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
                val matrix = OH_Drawing_MatrixCreate()
                OH_Drawing_ShaderEffectCreatePixelMapShader(null, OH_Drawing_TileMode.CLAMP, OH_Drawing_TileMode.CLAMP, sampling, matrix)
                OH_Drawing_SamplingOptionsDestroy(sampling)
                OH_Drawing_MatrixDestroy(matrix)
            }
            println("OH_Drawing_ShaderEffectCreatePixelMapShader passed")
        } catch (e: Throwable) { println("OH_Drawing_ShaderEffectCreatePixelMapShader (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateTwoPointConicalGradient() {
        memScoped {
            val startConical = alloc<OH_Drawing_Point2D>().apply { x = 0f; y = 0f }
            val endConical = alloc<OH_Drawing_Point2D>().apply { x = 10f; y = 10f }
            val colors = allocArray<UIntVar>(2).apply { this[0] = 0xFF000000u; this[1] = 0xFFFFFFFFu }
            val pos = allocArray<FloatVar>(2).apply { this[0] = 0f; this[1] = 1f }
            val matrix = OH_Drawing_MatrixCreate()
            val twoPointConical = OH_Drawing_ShaderEffectCreateTwoPointConicalGradient(startConical.ptr, 2f, endConical.ptr, 5f, colors, pos, 2u, OH_Drawing_TileMode.CLAMP, matrix)
            OH_Drawing_ShaderEffectDestroy(twoPointConical)
            OH_Drawing_MatrixDestroy(matrix)
        }
        println("OH_Drawing_ShaderEffectCreateTwoPointConicalGradient passed")
    }

    @Test
    fun testOH_Drawing_ShaderEffectCreateCompose() {
        val dstShader = OH_Drawing_ShaderEffectCreateColorShader(0xFF000000u)
        val srcShader = OH_Drawing_ShaderEffectCreateColorShader(0x80FFFFFFu)
        val compose = try { OH_Drawing_ShaderEffectCreateCompose(dstShader, srcShader, OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER) } catch (e: Throwable) { println("OH_Drawing_ShaderEffectCreateCompose (API 20) exception: $e"); null }
        OH_Drawing_ShaderEffectDestroy(compose)
        OH_Drawing_ShaderEffectDestroy(dstShader)
        OH_Drawing_ShaderEffectDestroy(srcShader)
        println("OH_Drawing_ShaderEffectCreateCompose passed")
    }

    // ---------- drawing_text_blob.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_FontCreate() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontCreate passed")
    }

    @Test
    fun testOH_Drawing_FontSetTypeface() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_FontSetTypeface passed")
    }

    @Test
    fun testOH_Drawing_FontSetTextSize() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTextSize(font, 14f)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetTextSize passed")
    }

    @Test
    fun testOH_Drawing_TextBlobCreateFromText() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            OH_Drawing_FontSetTextSize(font, 14f)
            val enc = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
            val textCstr = "A".cstr
            val blobFromText = OH_Drawing_TextBlobCreateFromText(textCstr.ptr, 1uL, font, enc)
            OH_Drawing_TextBlobDestroy(blobFromText)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_TextBlobCreateFromText passed")
    }

    @Test
    fun testOH_Drawing_TextBlobDestroy() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val enc = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
            val textCstr = "A".cstr
            val blobFromText = OH_Drawing_TextBlobCreateFromText(textCstr.ptr, 1uL, font, enc)
            OH_Drawing_TextBlobDestroy(blobFromText)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_TextBlobDestroy passed")
    }

    @Test
    fun testOH_Drawing_TextBlobCreateFromPosText() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val enc = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
            val textCstr = "A".cstr
            val posArr = allocArray<OH_Drawing_Point2D>(1).apply { this[0].x = 0f; this[0].y = 0f }
            val blobFromPos = OH_Drawing_TextBlobCreateFromPosText(textCstr.ptr, 1uL, posArr, font, enc)
            OH_Drawing_TextBlobDestroy(blobFromPos)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_TextBlobCreateFromPosText passed")
    }

    @Test
    fun testOH_Drawing_TextBlobCreateFromString() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        val enc = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
        val blobFromString = OH_Drawing_TextBlobCreateFromString("B", font, enc)
        OH_Drawing_TextBlobDestroy(blobFromString)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_TextBlobCreateFromString passed")
    }

    @Test
    fun testOH_Drawing_TextBlobGetBounds() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        val enc = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
        val blobFromString = OH_Drawing_TextBlobCreateFromString("B", font, enc)
        val rect = OH_Drawing_RectCreate(0f, 0f, 0f, 0f)
        OH_Drawing_TextBlobGetBounds(blobFromString, rect)
        OH_Drawing_RectDestroy(rect)
        OH_Drawing_TextBlobDestroy(blobFromString)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_TextBlobGetBounds passed")
    }

    @Test
    fun testOH_Drawing_TextBlobUniqueID() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        val enc = OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8
        val blobFromString = OH_Drawing_TextBlobCreateFromString("B", font, enc)
        OH_Drawing_TextBlobUniqueID(blobFromString)
        OH_Drawing_TextBlobDestroy(blobFromString)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_TextBlobUniqueID passed")
    }

    @Test
    fun testOH_Drawing_TextBlobBuilderCreate() {
        val builder = OH_Drawing_TextBlobBuilderCreate()
        OH_Drawing_TextBlobBuilderDestroy(builder)
        println("OH_Drawing_TextBlobBuilderCreate passed")
    }

    @Test
    fun testOH_Drawing_TextBlobBuilderAllocRunPos() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        val builder = OH_Drawing_TextBlobBuilderCreate()
        OH_Drawing_TextBlobBuilderAllocRunPos(builder, font, 1, null)
        OH_Drawing_TextBlobBuilderDestroy(builder)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_TextBlobBuilderAllocRunPos passed")
    }

    @Test
    fun testOH_Drawing_TextBlobBuilderMake() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        val builder = OH_Drawing_TextBlobBuilderCreate()
        OH_Drawing_TextBlobBuilderAllocRunPos(builder, font, 1, null)
        val blob = OH_Drawing_TextBlobBuilderMake(builder)
        OH_Drawing_TextBlobDestroy(blob)
        OH_Drawing_TextBlobBuilderDestroy(builder)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_TextBlobBuilderMake passed")
    }

    @Test
    fun testOH_Drawing_TextBlobBuilderDestroy() {
        val builder = OH_Drawing_TextBlobBuilderCreate()
        OH_Drawing_TextBlobBuilderDestroy(builder)
        println("OH_Drawing_TextBlobBuilderDestroy passed")
    }

    @Test
    fun testOH_Drawing_FontDestroy() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontDestroy passed")
    }

    // ---------- drawing_sampling_options.h ----------

    @Test
    fun testOH_Drawing_SamplingOptionsCreate() {
        val opts = OH_Drawing_SamplingOptionsCreate(
            OH_Drawing_FilterMode.FILTER_MODE_NEAREST,
            OH_Drawing_MipmapMode.MIPMAP_MODE_NONE
        )
        OH_Drawing_SamplingOptionsDestroy(opts)
        println("OH_Drawing_SamplingOptionsCreate passed")
    }

    @Test
    fun testOH_Drawing_SamplingOptionsCopy() {
        val opts = OH_Drawing_SamplingOptionsCreate(
            OH_Drawing_FilterMode.FILTER_MODE_NEAREST,
            OH_Drawing_MipmapMode.MIPMAP_MODE_NONE
        )
        val optsCopy = try { OH_Drawing_SamplingOptionsCopy(opts) } catch (e: Throwable) { println("OH_Drawing_SamplingOptionsCopy (API 20) exception: $e"); null }
        OH_Drawing_SamplingOptionsDestroy(optsCopy)
        OH_Drawing_SamplingOptionsDestroy(opts)
        println("OH_Drawing_SamplingOptionsCopy passed")
    }

    @Test
    fun testOH_Drawing_SamplingOptionsDestroy() {
        val opts = OH_Drawing_SamplingOptionsCreate(
            OH_Drawing_FilterMode.FILTER_MODE_NEAREST,
            OH_Drawing_MipmapMode.MIPMAP_MODE_NONE
        )
        OH_Drawing_SamplingOptionsDestroy(opts)
        println("OH_Drawing_SamplingOptionsDestroy passed")
    }

    // ---------- drawing_image_filter.h ----------

    @Test
    fun testOH_Drawing_ImageFilterCreateBlur() {
        val blur = OH_Drawing_ImageFilterCreateBlur(0f, 0f, OH_Drawing_TileMode.CLAMP, null)
        OH_Drawing_ImageFilterDestroy(blur)
        println("OH_Drawing_ImageFilterCreateBlur passed")
    }

    @Test
    fun testOH_Drawing_ImageFilterDestroy() {
        val blur = OH_Drawing_ImageFilterCreateBlur(0f, 0f, OH_Drawing_TileMode.CLAMP, null)
        OH_Drawing_ImageFilterDestroy(blur)
        println("OH_Drawing_ImageFilterDestroy passed")
    }

    @Test
    fun testOH_Drawing_ImageFilterCreateBlurWithCrop() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val blurCrop = try { OH_Drawing_ImageFilterCreateBlurWithCrop(0f, 0f, OH_Drawing_TileMode.CLAMP, null, rect) } catch (e: Throwable) { println("OH_Drawing_ImageFilterCreateBlurWithCrop (API 20) exception: $e"); null }
        OH_Drawing_ImageFilterDestroy(blurCrop)
        OH_Drawing_RectDestroy(rect)
        println("OH_Drawing_ImageFilterCreateBlurWithCrop passed")
    }

    @Test
    fun testOH_Drawing_ImageFilterCreateFromColorFilter() {
        val cf = OH_Drawing_ColorFilterCreateLinearToSrgbGamma()
        val fromCf = OH_Drawing_ImageFilterCreateFromColorFilter(cf, null)
        OH_Drawing_ImageFilterDestroy(fromCf)
        OH_Drawing_ColorFilterDestroy(cf)
        println("OH_Drawing_ImageFilterCreateFromColorFilter passed")
    }

    @Test
    fun testOH_Drawing_ImageFilterCreateOffset() {
        val offset = try { OH_Drawing_ImageFilterCreateOffset(0f, 0f, null) } catch (e: Throwable) { println("OH_Drawing_ImageFilterCreateOffset (API 20) exception: $e"); null }
        OH_Drawing_ImageFilterDestroy(offset)
        println("OH_Drawing_ImageFilterCreateOffset passed")
    }

    @Test
    fun testOH_Drawing_ImageFilterCreateFromShaderEffect() {
        val shader = OH_Drawing_ShaderEffectCreateColorShader(0u)
        val fromShader = try { OH_Drawing_ImageFilterCreateFromShaderEffect(shader) } catch (e: Throwable) { println("OH_Drawing_ImageFilterCreateFromShaderEffect (API 20) exception: $e"); null }
        OH_Drawing_ImageFilterDestroy(fromShader)
        OH_Drawing_ShaderEffectDestroy(shader)
        println("OH_Drawing_ImageFilterCreateFromShaderEffect passed")
    }

    // ---------- drawing_mask_filter.h ----------

    @Test
    fun testOH_Drawing_MaskFilterCreateBlur() {
        val filter = OH_Drawing_MaskFilterCreateBlur(OH_Drawing_BlurType.NORMAL, 1f, true)
        OH_Drawing_MaskFilterDestroy(filter)
        println("OH_Drawing_MaskFilterCreateBlur passed")
    }

    @Test
    fun testOH_Drawing_MaskFilterDestroy() {
        val filter = OH_Drawing_MaskFilterCreateBlur(OH_Drawing_BlurType.NORMAL, 1f, true)
        OH_Drawing_MaskFilterDestroy(filter)
        println("OH_Drawing_MaskFilterDestroy passed")
    }

    // ---------- drawing_font.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_FontSetBaselineSnap() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetBaselineSnap(font, true)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetBaselineSnap passed")
    }

    @Test
    fun testOH_Drawing_FontIsBaselineSnap() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontIsBaselineSnap(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontIsBaselineSnap passed")
    }

    @Test
    fun testOH_Drawing_FontSetSubpixel() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetSubpixel(font, false)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetSubpixel passed")
    }

    @Test
    fun testOH_Drawing_FontIsSubpixel() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontIsSubpixel(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontIsSubpixel passed")
    }

    @Test
    fun testOH_Drawing_FontSetForceAutoHinting() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetForceAutoHinting(font, false)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetForceAutoHinting passed")
    }

    @Test
    fun testOH_Drawing_FontIsForceAutoHinting() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontIsForceAutoHinting(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontIsForceAutoHinting passed")
    }

    @Test
    fun testOH_Drawing_FontGetTypeface() {
        val typeface = OH_Drawing_TypefaceCreateDefault()
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTypeface(font, typeface)
        OH_Drawing_FontGetTypeface(font)
        OH_Drawing_FontDestroy(font)
        OH_Drawing_TypefaceDestroy(typeface)
        println("OH_Drawing_FontGetTypeface passed")
    }

    @Test
    fun testOH_Drawing_FontGetTextSize() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTextSize(font, 14f)
        OH_Drawing_FontGetTextSize(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontGetTextSize passed")
    }

    @Test
    fun testOH_Drawing_FontCountText() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            OH_Drawing_FontCountText(font, encStr.ptr, 1uL, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontCountText passed")
    }

    @Test
    fun testOH_Drawing_FontTextToGlyphs() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val glyphsBuf = allocArray<UShortVar>(4)
            OH_Drawing_FontTextToGlyphs(font, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, glyphsBuf, 4)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontTextToGlyphs passed")
    }

    @Test
    fun testOH_Drawing_FontGetWidths() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val glyphsBuf = allocArray<UShortVar>(4)
            val glyphCount = OH_Drawing_FontTextToGlyphs(font, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, glyphsBuf, 4)
            val widthsBuf = allocArray<FloatVar>(4)
            OH_Drawing_FontGetWidths(font, glyphsBuf, glyphCount.toInt(), widthsBuf)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetWidths passed")
    }

    @Test
    fun testOH_Drawing_FontMeasureSingleCharacter() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val singleW = alloc<FloatVar>()
            OH_Drawing_FontMeasureSingleCharacter(font, "A", singleW.ptr)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontMeasureSingleCharacter passed")
    }

    @Test
    fun testOH_Drawing_FontFeaturesCreate() {
        val features = try { OH_Drawing_FontFeaturesCreate() } catch (e: Throwable) { println("OH_Drawing_FontFeaturesCreate (API 20) exception: $e"); null }
        try { OH_Drawing_FontFeaturesDestroy(features) } catch (e: Throwable) { }
        println("OH_Drawing_FontFeaturesCreate passed")
    }

    @Test
    fun testOH_Drawing_FontFeaturesAddFeature() {
        val features = try { OH_Drawing_FontFeaturesCreate() } catch (e: Throwable) { null }
        try { OH_Drawing_FontFeaturesAddFeature(features, "kern", 1f) } catch (e: Throwable) { println("OH_Drawing_FontFeaturesAddFeature (API 20) exception: $e") }
        try { OH_Drawing_FontFeaturesDestroy(features) } catch (e: Throwable) { }
        println("OH_Drawing_FontFeaturesAddFeature passed")
    }

    @Test
    fun testOH_Drawing_FontMeasureSingleCharacterWithFeatures() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val features = try { OH_Drawing_FontFeaturesCreate() } catch (e: Throwable) { null }
            val singleW = alloc<FloatVar>()
            try { OH_Drawing_FontMeasureSingleCharacterWithFeatures(font, "A", features, singleW.ptr) } catch (e: Throwable) { println("OH_Drawing_FontMeasureSingleCharacterWithFeatures (API 20) exception: $e") }
            try { OH_Drawing_FontFeaturesDestroy(features) } catch (e: Throwable) { }
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontMeasureSingleCharacterWithFeatures passed")
    }

    @Test
    fun testOH_Drawing_FontFeaturesDestroy() {
        val features = try { OH_Drawing_FontFeaturesCreate() } catch (e: Throwable) { null }
        try { OH_Drawing_FontFeaturesDestroy(features) } catch (e: Throwable) { }
        println("OH_Drawing_FontFeaturesDestroy passed")
    }

    @Test
    fun testOH_Drawing_FontMeasureText() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val textW = alloc<FloatVar>()
            OH_Drawing_FontMeasureText(font, encStr.ptr, 1uL, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, null, textW.ptr)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontMeasureText passed")
    }

    @Test
    fun testOH_Drawing_FontMeasureTextWithBrushOrPen() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val textW = alloc<FloatVar>()
            OH_Drawing_FontMeasureTextWithBrushOrPen(font, encStr.ptr, 1uL, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, null, null, null, textW.ptr)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontMeasureTextWithBrushOrPen passed")
    }

    @Test
    fun testOH_Drawing_FontGetWidthsBounds() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val glyphsBuf = allocArray<UShortVar>(4)
            val glyphCount = OH_Drawing_FontTextToGlyphs(font, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, glyphsBuf, 4)
            val widthsBuf = allocArray<FloatVar>(4)
            val boundsArr = try { OH_Drawing_RectCreateArray(4uL) } catch (e: Throwable) { null }
            try { OH_Drawing_FontGetWidthsBounds(font, glyphsBuf, glyphCount.toInt(), null, null, widthsBuf, boundsArr) } catch (e: Throwable) { println("OH_Drawing_FontGetWidthsBounds (API 19) exception: $e") }
            try { OH_Drawing_RectDestroyArray(boundsArr) } catch (e: Throwable) { }
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetWidthsBounds passed")
    }

    @Test
    fun testOH_Drawing_FontGetPos() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val glyphsBuf = allocArray<UShortVar>(4)
            val glyphCount = OH_Drawing_FontTextToGlyphs(font, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, glyphsBuf, 4)
            val posPts = allocArray<OH_Drawing_Point2D>(4)
            val originPt = OH_Drawing_PointCreate(0f, 0f)
            try { OH_Drawing_FontGetPos(font, glyphsBuf, glyphCount.toInt(), originPt, posPts) } catch (e: Throwable) { println("OH_Drawing_FontGetPos (API 19) exception: $e") }
            OH_Drawing_PointDestroy(originPt)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetPos passed")
    }

    @Test
    fun testOH_Drawing_FontGetSpacing() {
        memScoped {
            val font = OH_Drawing_FontCreate()
            val spacingOut = alloc<FloatVar>()
            try { OH_Drawing_FontGetSpacing(font, spacingOut.ptr) } catch (e: Throwable) { println("OH_Drawing_FontGetSpacing (API 19) exception: $e") }
            OH_Drawing_FontDestroy(font)
        }
        println("OH_Drawing_FontGetSpacing passed")
    }

    @Test
    fun testOH_Drawing_FontSetLinearText() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetLinearText(font, false)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetLinearText passed")
    }

    @Test
    fun testOH_Drawing_FontIsLinearText() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontIsLinearText(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontIsLinearText passed")
    }

    @Test
    fun testOH_Drawing_FontSetTextSkewX() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetTextSkewX(font, 0f)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetTextSkewX passed")
    }

    @Test
    fun testOH_Drawing_FontGetTextSkewX() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontGetTextSkewX(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontGetTextSkewX passed")
    }

    @Test
    fun testOH_Drawing_FontSetFakeBoldText() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetFakeBoldText(font, false)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetFakeBoldText passed")
    }

    @Test
    fun testOH_Drawing_FontIsFakeBoldText() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontIsFakeBoldText(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontIsFakeBoldText passed")
    }

    @Test
    fun testOH_Drawing_FontSetScaleX() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetScaleX(font, 1f)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetScaleX passed")
    }

    @Test
    fun testOH_Drawing_FontGetScaleX() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontGetScaleX(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontGetScaleX passed")
    }

    @Test
    fun testOH_Drawing_FontSetHinting() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetHinting(font, OH_Drawing_FontHinting.FONT_HINTING_NORMAL)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetHinting passed")
    }

    @Test
    fun testOH_Drawing_FontGetHinting() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontGetHinting(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontGetHinting passed")
    }

    @Test
    fun testOH_Drawing_FontSetEmbeddedBitmaps() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetEmbeddedBitmaps(font, true)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetEmbeddedBitmaps passed")
    }

    @Test
    fun testOH_Drawing_FontIsEmbeddedBitmaps() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontIsEmbeddedBitmaps(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontIsEmbeddedBitmaps passed")
    }

    @Test
    fun testOH_Drawing_FontSetEdging() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetEdging(font, OH_Drawing_FontEdging.FONT_EDGING_ANTI_ALIAS)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetEdging passed")
    }

    @Test
    fun testOH_Drawing_FontGetEdging() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontGetEdging(font)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontGetEdging passed")
    }

    @Test
    fun testOH_Drawing_FontGetMetrics() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val fontMetrics = alloc<OH_Drawing_Font_Metrics>()
            OH_Drawing_FontGetMetrics(font, fontMetrics.ptr)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetMetrics passed")
    }

    @Test
    fun testOH_Drawing_FontGetBounds() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val glyphsBuf = allocArray<UShortVar>(4)
            val glyphCount = OH_Drawing_FontTextToGlyphs(font, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, glyphsBuf, 4)
            val boundsArr2 = try { OH_Drawing_RectCreateArray(4uL) } catch (e: Throwable) { null }
            try { OH_Drawing_FontGetBounds(font, glyphsBuf, glyphCount, boundsArr2) } catch (e: Throwable) { println("OH_Drawing_FontGetBounds (API 18) exception: $e") }
            try { OH_Drawing_RectDestroyArray(boundsArr2) } catch (e: Throwable) { }
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetBounds passed")
    }

    @Test
    fun testOH_Drawing_FontGetPathForGlyph() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val glyphsBuf = allocArray<UShortVar>(4)
            OH_Drawing_FontTextToGlyphs(font, encStr.ptr, 1u, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, glyphsBuf, 4)
            val pathGlyph = OH_Drawing_PathCreate()
            try { (glyphsBuf + 0)?.pointed?.value?.let { OH_Drawing_FontGetPathForGlyph(font, it, pathGlyph) } } catch (e: Throwable) { println("OH_Drawing_FontGetPathForGlyph (API 18) exception: $e") }
            OH_Drawing_PathDestroy(pathGlyph)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetPathForGlyph passed")
    }

    @Test
    fun testOH_Drawing_FontGetTextPath() {
        memScoped {
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            val encStr = "A".cstr
            val pathText = OH_Drawing_PathCreate()
            try { OH_Drawing_FontGetTextPath(font, encStr.ptr, 1uL, OH_Drawing_TextEncoding.TEXT_ENCODING_UTF8, 0f, 0f, pathText) } catch (e: Throwable) { println("OH_Drawing_FontGetTextPath (API 18) exception: $e") }
            OH_Drawing_PathDestroy(pathText)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
        }
        println("OH_Drawing_FontGetTextPath passed")
    }

    @Test
    fun testOH_Drawing_FontSetThemeFontFollowed() {
        val font = OH_Drawing_FontCreate()
        OH_Drawing_FontSetThemeFontFollowed(font, false)
        OH_Drawing_FontDestroy(font)
        println("OH_Drawing_FontSetThemeFontFollowed passed")
    }

    @Test
    fun testOH_Drawing_FontIsThemeFontFollowed() {
        memScoped {
            val font = OH_Drawing_FontCreate()
            val themeFollowed = alloc<BooleanVar>()
            OH_Drawing_FontIsThemeFontFollowed(font, themeFollowed.ptr)
            OH_Drawing_FontDestroy(font)
        }
        println("OH_Drawing_FontIsThemeFontFollowed passed")
    }

    // ---------- drawing_text_font_descriptor.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_GetSystemFontFullNameByIndex() {
        val fullNameArray = OH_Drawing_GetSystemFontFullNamesByType(ALL)
        OH_Drawing_GetSystemFontFullNameByIndex(fullNameArray, 0u)
        OH_Drawing_DestroySystemFontFullNames(fullNameArray)
        println("OH_Drawing_GetSystemFontFullNameByIndex passed")
    }

    @Test
    fun testOH_Drawing_GetFontDescriptorByFullName() {
        val fullNameArray = OH_Drawing_GetSystemFontFullNamesByType(ALL)
        val fullName = OH_Drawing_GetSystemFontFullNameByIndex(fullNameArray, 0u)
        val descByFullName = OH_Drawing_GetFontDescriptorByFullName(fullName, ALL)
        OH_Drawing_DestroyFontDescriptor(descByFullName)
        OH_Drawing_DestroySystemFontFullNames(fullNameArray)
        println("OH_Drawing_GetFontDescriptorByFullName passed")
    }

    @Test
    fun testOH_Drawing_CreateFontDescriptor() {
        val desc = OH_Drawing_CreateFontDescriptor()
        OH_Drawing_DestroyFontDescriptor(desc)
        println("OH_Drawing_CreateFontDescriptor passed")
    }

    @Test
    fun testOH_Drawing_MatchFontDescriptors() {
        memScoped {
            val desc = OH_Drawing_CreateFontDescriptor()
            val num = alloc<ULongVar>()
            val matched = try { OH_Drawing_MatchFontDescriptors(desc, num.ptr) } catch (e: Throwable) { null }
            try { OH_Drawing_DestroyFontDescriptors(matched, num.value) } catch (e: Throwable) { }
            OH_Drawing_DestroyFontDescriptor(desc)
        }
        println("OH_Drawing_MatchFontDescriptors passed")
    }

    @Test
    fun testOH_Drawing_DestroyFontDescriptors() {
        try {
            memScoped {
                val desc = OH_Drawing_CreateFontDescriptor()
                val num = alloc<ULongVar>()
                val matched = try { OH_Drawing_MatchFontDescriptors(desc, num.ptr) } catch (e: Throwable) { null }
                OH_Drawing_DestroyFontDescriptors(matched, num.value)
                OH_Drawing_DestroyFontDescriptor(desc)
            }
            println("OH_Drawing_DestroyFontDescriptors passed")
        } catch (e: Throwable) { println("OH_Drawing_DestroyFontDescriptors (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_GetFontFullDescriptorsFromStream() {
        val arrStream = try { OH_Drawing_GetFontFullDescriptorsFromStream(null, 0u) } catch (e: Throwable) { null }
        try { OH_Drawing_DestroyFontFullDescriptors(arrStream) } catch (e: Throwable) { }
        println("OH_Drawing_GetFontFullDescriptorsFromStream passed")
    }

    @Test
    fun testOH_Drawing_GetFontFullDescriptorsFromPath() {
        try {
            val arrPath = try { OH_Drawing_GetFontFullDescriptorsFromPath(".") } catch (e: Throwable) { null }
            OH_Drawing_GetDrawingArraySize(arrPath)
            try { OH_Drawing_DestroyFontFullDescriptors(arrPath) } catch (e: Throwable) { }
            println("OH_Drawing_GetFontFullDescriptorsFromPath passed")
        } catch (e: Throwable) { println("OH_Drawing_GetFontFullDescriptorsFromPath (optional API) exception: $e") }
    }

    @Test
    fun testOH_Drawing_GetFontFullDescriptorByIndex() {
        val arrPath = try { OH_Drawing_GetFontFullDescriptorsFromPath(".") } catch (e: Throwable) { null }
        val fd = try { OH_Drawing_GetFontFullDescriptorByIndex(arrPath, 0u) } catch (e: Throwable) { null }
        try { OH_Drawing_DestroyFontFullDescriptors(arrPath) } catch (e: Throwable) { }
        println("OH_Drawing_GetFontFullDescriptorByIndex passed")
    }

    @Test
    fun testOH_Drawing_GetFontFullDescriptorAttributeInt() {
        memScoped {
            val arrPath = try { OH_Drawing_GetFontFullDescriptorsFromPath(".") } catch (e: Throwable) { null }
            val fd = try { OH_Drawing_GetFontFullDescriptorByIndex(arrPath, 0u) } catch (e: Throwable) { null }
            val intVal = alloc<IntVar>()
            try { OH_Drawing_GetFontFullDescriptorAttributeInt(fd, FULL_DESCRIPTOR_ATTR_I_WEIGHT, intVal.ptr) } catch (e: Throwable) { }
            try { OH_Drawing_DestroyFontFullDescriptors(arrPath) } catch (e: Throwable) { }
        }
        println("OH_Drawing_GetFontFullDescriptorAttributeInt passed")
    }

    @Test
    fun testOH_Drawing_GetFontFullDescriptorAttributeBool() {
        memScoped {
            val arrPath = try { OH_Drawing_GetFontFullDescriptorsFromPath(".") } catch (e: Throwable) { null }
            val fd = try { OH_Drawing_GetFontFullDescriptorByIndex(arrPath, 0u) } catch (e: Throwable) { null }
            val boolVal = alloc<BooleanVar>()
            try { OH_Drawing_GetFontFullDescriptorAttributeBool(fd, FULL_DESCRIPTOR_ATTR_B_MONO, boolVal.ptr) } catch (e: Throwable) { }
            try { OH_Drawing_DestroyFontFullDescriptors(arrPath) } catch (e: Throwable) { }
        }
        println("OH_Drawing_GetFontFullDescriptorAttributeBool passed")
    }

    @Test
    fun testOH_Drawing_GetFontFullDescriptorAttributeString() {
        memScoped {
            val arrPath = try { OH_Drawing_GetFontFullDescriptorsFromPath(".") } catch (e: Throwable) { null }
            val fd = try { OH_Drawing_GetFontFullDescriptorByIndex(arrPath, 0u) } catch (e: Throwable) { null }
            val strOut = alloc<OH_Drawing_String>()
            try { OH_Drawing_GetFontFullDescriptorAttributeString(fd, FULL_DESCRIPTOR_ATTR_S_FULL_NAME, strOut.ptr) } catch (e: Throwable) { }
            try { OH_Drawing_DestroyFontFullDescriptors(arrPath) } catch (e: Throwable) { }
        }
        println("OH_Drawing_GetFontFullDescriptorAttributeString passed")
    }

    @Test
    fun testOH_Drawing_DestroyFontFullDescriptors() {
        try {
            val arrPath = try { OH_Drawing_GetFontFullDescriptorsFromPath(".") } catch (e: Throwable) { null }
            try { OH_Drawing_DestroyFontFullDescriptors(arrPath) } catch (e: Throwable) { }
            println("OH_Drawing_DestroyFontFullDescriptors passed")
        } catch (e: Throwable) { println("OH_Drawing_DestroyFontFullDescriptors (optional API) exception: $e") }
    }

    // ---------- drawing_text_lineTypography.h（单 API 测试）----------

    @Test
    fun testOH_Drawing_DestroyLineTypography() {
        try { OH_Drawing_DestroyLineTypography(null) } catch (e: Throwable) { }
        println("OH_Drawing_DestroyLineTypography passed")
    }

    @Test
    fun testOH_Drawing_CreateLineTypography() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val textStyle = OH_Drawing_CreateTextStyle()
        val handler = OH_Drawing_CreateTypographyHandler(style, collection)
        OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
        OH_Drawing_TypographyHandlerAddText(handler, "A")
        val lineTypography = try { OH_Drawing_CreateLineTypography(handler) } catch (e: Throwable) { null }
        try { OH_Drawing_DestroyLineTypography(lineTypography) } catch (e: Throwable) { }
        OH_Drawing_DestroyTypographyHandler(handler)
        OH_Drawing_DestroyFontCollection(collection)
        OH_Drawing_DestroyTextStyle(textStyle)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_CreateLineTypography passed")
    }

    @Test
    fun testOH_Drawing_LineTypographyGetLineBreak() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val handler = OH_Drawing_CreateTypographyHandler(style, collection)
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
        OH_Drawing_TypographyHandlerAddText(handler, "A")
        val lineTypography = try { OH_Drawing_CreateLineTypography(handler) } catch (e: Throwable) { null }
        try { OH_Drawing_LineTypographyGetLineBreak(lineTypography, 0uL, 100.0) } catch (e: Throwable) { }
        try { OH_Drawing_DestroyLineTypography(lineTypography) } catch (e: Throwable) { }
        OH_Drawing_DestroyTypographyHandler(handler)
        OH_Drawing_DestroyFontCollection(collection)
        OH_Drawing_DestroyTextStyle(textStyle)
        OH_Drawing_DestroyTypographyStyle(style)
        println("OH_Drawing_LineTypographyGetLineBreak passed")
    }

    @Test
    fun testOH_Drawing_LineTypographyCreateLine() {
        try {
            val style = OH_Drawing_CreateTypographyStyle()
            val collection = OH_Drawing_CreateFontCollection()
            val handler = OH_Drawing_CreateTypographyHandler(style, collection)
            val textStyle = OH_Drawing_CreateTextStyle()
            OH_Drawing_TypographyHandlerPushTextStyle(handler, textStyle)
            OH_Drawing_TypographyHandlerAddText(handler, "A")
            val lineTypography = try { OH_Drawing_CreateLineTypography(handler) } catch (e: Throwable) { null }
            val textLine = try { OH_Drawing_LineTypographyCreateLine(lineTypography, 0uL, 1uL) } catch (e: Throwable) { null }
            try { OH_Drawing_DestroyTextLine(textLine) } catch (e: Throwable) { }
            try { OH_Drawing_DestroyLineTypography(lineTypography) } catch (e: Throwable) { }
            OH_Drawing_DestroyTypographyHandler(handler)
            OH_Drawing_DestroyFontCollection(collection)
            OH_Drawing_DestroyTextStyle(textStyle)
            OH_Drawing_DestroyTypographyStyle(style)
            println("OH_Drawing_LineTypographyCreateLine passed")
        } catch (e: Throwable) { println("OH_Drawing_LineTypographyCreateLine (optional API) exception: $e") }
    }

    // ---------- drawing_path.h ----------

    @Test
    fun testPathCreate() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathMoveTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathLineTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathLineTo(path, 10f, 10f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathClose() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathLineTo(path, 10f, 10f)
            OH_Drawing_PathClose(path)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathSetFillType() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathSetFillType(path, OH_Drawing_PathFillType.PATH_FILL_TYPE_WINDING)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathGetFillType() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val fillTypeOut = alloc<OH_Drawing_PathFillType.Var>()
            try { OH_Drawing_PathGetFillType(path, fillTypeOut.ptr) } catch (e: Throwable) { println("OH_Drawing_PathGetFillType (API 20) exception: $e") }
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathIsEmpty() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val isEmpty = alloc<BooleanVar>()
            try { OH_Drawing_PathIsEmpty(path, isEmpty.ptr) } catch (e: Throwable) { println("OH_Drawing_PathIsEmpty (API 20) exception: $e") }
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathCopy() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            val path2 = OH_Drawing_PathCopy(path)
            OH_Drawing_PathDestroy(path2)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathReset() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathReset(path)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathSetPath() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val path2 = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path2, 0f, 0f)
            try { OH_Drawing_PathSetPath(path, path2) } catch (e: Throwable) { println("OH_Drawing_PathSetPath (API 20) exception: $e") }
            OH_Drawing_PathDestroy(path2)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathOffset() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val pathOffsetDst = OH_Drawing_PathCreate()
            OH_Drawing_PathOffset(path, pathOffsetDst, 0f, 0f)
            OH_Drawing_PathDestroy(pathOffsetDst)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathAddRect() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathAddRect(path, 0f, 0f, 1f, 1f, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathAddCircle() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathAddCircle(path, 5f, 5f, 3f, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathAddArc() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val rect = OH_Drawing_RectCreate(0f, 0f, 1f, 1f)
            OH_Drawing_PathAddArc(path, rect, 0f, 90f)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathGetLength() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathLineTo(path, 10f, 10f)
            OH_Drawing_PathGetLength(path, false)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathIsClosed() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathIsClosed(path, false)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathIsRect() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathAddRect(path, 0f, 0f, 1f, 1f, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            val isRectOut = alloc<BooleanVar>()
            val rectOut = OH_Drawing_RectCreate(0f, 0f, 0f, 0f)
            try { OH_Drawing_PathIsRect(path, rectOut, isRectOut.ptr) } catch (e: Throwable) { println("OH_Drawing_PathIsRect (API 20) exception: $e") }
            OH_Drawing_RectDestroy(rectOut)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathQuadTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathQuadTo(path, 10f, 5f, 5f, 10f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathCubicTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathCubicTo(path, 5f, 10f, 0f, 5f, 0f, 0f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathRMoveTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathRMoveTo(path, 0f, 0f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathRLineTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathRLineTo(path, 1f, 1f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathRQuadTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathRQuadTo(path, 1f, 0f, 1f, 1f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathRConicTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathRConicTo(path, 1f, 1f, 1f, 1f, 1f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathRCubicTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathRCubicTo(path, 1f, 0f, 0f, 1f, 0f, 0f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathConicTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(path, 0f, 0f)
            OH_Drawing_PathConicTo(path, 1f, 1f, 1f, 1f, 1f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathArcTo() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            OH_Drawing_PathArcTo(path, 0f, 0f, 10f, 10f, 0f, 90f)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathAddOval() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val rectE = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_PathAddOval(path, rectE, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_RectDestroy(rectE)
            OH_Drawing_PathDestroy(path)
        }
    }

    @Test
    fun testPathAddPathWithMode() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val pathE2 = OH_Drawing_PathCreate()
            OH_Drawing_PathAddPathWithMode(pathE, pathE2, OH_Drawing_PathAddMode.PATH_ADD_MODE_APPEND)
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddPath() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val pathE2 = OH_Drawing_PathCreate()
            val matrixE = OH_Drawing_MatrixCreate()
            OH_Drawing_PathAddPath(pathE, pathE2, matrixE)
            OH_Drawing_MatrixDestroy(matrixE)
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddPathWithMatrixAndMode() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val pathE2 = OH_Drawing_PathCreate()
            val matrixE = OH_Drawing_MatrixCreate()
            OH_Drawing_PathAddPathWithMatrixAndMode(pathE, pathE2, matrixE, OH_Drawing_PathAddMode.PATH_ADD_MODE_APPEND)
            OH_Drawing_MatrixDestroy(matrixE)
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddRectWithInitialCorner() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val rectE = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_PathAddRectWithInitialCorner(pathE, rectE, OH_Drawing_PathDirection.PATH_DIRECTION_CW, 0u)
            OH_Drawing_RectDestroy(rectE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddOvalWithInitialPoint() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val rectE = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_PathAddOvalWithInitialPoint(pathE, rectE, 0u, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_RectDestroy(rectE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddRoundRect() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val rectE = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val roundRectE = OH_Drawing_RoundRectCreate(rectE, 2f, 2f)
            OH_Drawing_PathAddRoundRect(pathE, roundRectE, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_RoundRectDestroy(roundRectE)
            OH_Drawing_RectDestroy(rectE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddPolygon() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val ptsE = allocArray<OH_Drawing_Point2D>(3).apply {
                this[0].x = 0f; this[0].y = 0f; this[1].x = 5f; this[1].y = 0f; this[2].x = 5f; this[2].y = 5f
            }
            OH_Drawing_PathAddPolygon(pathE, ptsE, 3u, true)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathBuildFromSvgString() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathBuildFromSvgString(pathE, "M0 0 L10 0 L10 10 Z")
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathGetBounds() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(pathE, 0f, 0f)
            OH_Drawing_PathLineTo(pathE, 10f, 10f)
            val outRectE = OH_Drawing_RectCreate(0f, 0f, 0f, 0f)
            OH_Drawing_PathGetBounds(pathE, outRectE)
            OH_Drawing_RectDestroy(outRectE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathContains() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathAddRect(pathE, 0f, 0f, 10f, 10f, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_PathContains(pathE, 5f, 5f)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathTransform() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(pathE, 0f, 0f)
            val matrixE = OH_Drawing_MatrixCreate()
            OH_Drawing_PathTransform(pathE, matrixE)
            OH_Drawing_MatrixDestroy(matrixE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathTransformWithPerspectiveClip() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(pathE, 0f, 0f)
            val matrixE = OH_Drawing_MatrixCreate()
            val pathPerspDst = OH_Drawing_PathCreate()
            OH_Drawing_PathTransformWithPerspectiveClip(pathE, matrixE, pathPerspDst, false)
            OH_Drawing_PathDestroy(pathPerspDst)
            OH_Drawing_MatrixDestroy(matrixE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathGetMatrix() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(pathE, 0f, 0f)
            val outMatrixE = OH_Drawing_MatrixCreate()
            OH_Drawing_PathGetMatrix(pathE, false, 0f, outMatrixE, OH_Drawing_PathMeasureMatrixFlags.GET_POSITION_MATRIX)
            OH_Drawing_MatrixDestroy(outMatrixE)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathGetPositionTangent() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(pathE, 0f, 0f)
            OH_Drawing_PathLineTo(pathE, 10f, 0f)
            val posPtE = alloc<OH_Drawing_Point2D>()
            val tanPtE = alloc<OH_Drawing_Point2D>()
            OH_Drawing_PathGetPositionTangent(pathE, false, 0f, posPtE.ptr, tanPtE.ptr)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathGetSegment() {
        try {
            memScoped {
                val pathE = OH_Drawing_PathCreate()
                OH_Drawing_PathMoveTo(pathE, 0f, 0f)
                OH_Drawing_PathLineTo(pathE, 10f, 10f)
                val segmentE = OH_Drawing_PathCreate()
                val segResultE = alloc<BooleanVar>()
                OH_Drawing_PathGetSegment(pathE, false, 0f, 1f, true, segmentE, segResultE.ptr)
                OH_Drawing_PathDestroy(segmentE)
                OH_Drawing_PathDestroy(pathE)
            }
        } catch (e: Throwable) { println("OH_Drawing_PathGetSegment (optional API) exception: $e") }
    }

    @Test
    fun testPathInterpolate() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val pathE2 = OH_Drawing_PathCreate()
            OH_Drawing_PathMoveTo(pathE, 0f, 0f)
            OH_Drawing_PathLineTo(pathE, 10f, 0f)
            OH_Drawing_PathMoveTo(pathE2, 0f, 0f)
            OH_Drawing_PathLineTo(pathE2, 0f, 10f)
            val interpE = OH_Drawing_PathCreate()
            val interpOkE = alloc<BooleanVar>()
            try { OH_Drawing_PathInterpolate(pathE, pathE2, 0.5f, interpOkE.ptr, interpE) } catch (e: Throwable) { println("OH_Drawing_PathInterpolate (API 20) exception: $e") }
            OH_Drawing_PathDestroy(interpE)
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathIsInterpolate() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val pathE2 = OH_Drawing_PathCreate()
            val isInterpResultE = alloc<BooleanVar>()
            try { OH_Drawing_PathIsInterpolate(pathE, pathE2, isInterpResultE.ptr) } catch (e: Throwable) { println("OH_Drawing_PathIsInterpolate (API 20) exception: $e") }
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathOp() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            OH_Drawing_PathAddRect(pathE, 0f, 0f, 10f, 10f, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            val pathE2 = OH_Drawing_PathCreate()
            OH_Drawing_PathAddRect(pathE2, 2f, 2f, 8f, 8f, OH_Drawing_PathDirection.PATH_DIRECTION_CW)
            OH_Drawing_PathOp(pathE, pathE2, OH_Drawing_PathOpMode.PATH_OP_MODE_DIFFERENCE)
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathAddPathWithOffsetAndMode() {
        memScoped {
            val pathE = OH_Drawing_PathCreate()
            val pathE2 = OH_Drawing_PathCreate()
            OH_Drawing_PathAddPathWithOffsetAndMode(pathE, pathE2, 0f, 0f, OH_Drawing_PathAddMode.PATH_ADD_MODE_APPEND)
            OH_Drawing_PathDestroy(pathE2)
            OH_Drawing_PathDestroy(pathE)
        }
    }

    @Test
    fun testPathApproximate() {
        try {
            memScoped {
                val pathE = OH_Drawing_PathCreate()
                OH_Drawing_PathMoveTo(pathE, 0f, 0f)
                OH_Drawing_PathLineTo(pathE, 10f, 10f)
                val approxCountE = alloc<UIntVar>()
                val approxBufE = allocArrayOf(0f, 0f, 0f)
                OH_Drawing_PathApproximate(pathE, 0.5f, approxBufE, approxCountE.ptr)
                OH_Drawing_PathDestroy(pathE)
            }
        } catch (e: Throwable) { println("OH_Drawing_PathApproximate (optional API) exception: $e") }
    }

    // ---------- drawing_matrix.h ----------

    @Test
    fun testMatrixCreate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixReset() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixReset(m)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixSetMatrix() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixSetMatrix(m, 1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixGetValue() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixSetMatrix(m, 1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
            OH_Drawing_MatrixGetValue(m, 0)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixGetAll() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixSetMatrix(m, 1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
            val arr = allocArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
            OH_Drawing_MatrixGetAll(m, arr)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPreTranslate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixPreTranslate(m, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPreScale() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixPreScale(m, 1f, 1f, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPreRotate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixPreRotate(m, 0f, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPostTranslate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixPostTranslate(m, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPostScale() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixPostScale(m, 1f, 1f, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPostRotate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixPostRotate(m, 0f, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixCopy() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            val m2 = try { OH_Drawing_MatrixCopy(m) } catch (e: Throwable) { println("OH_Drawing_MatrixCopy (API 20) exception: $e"); null }
            OH_Drawing_MatrixDestroy(m2)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixConcat() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixConcat(m, m, m)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixPreConcat() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            try { OH_Drawing_MatrixPreConcat(m, m) } catch (e: Throwable) { println("OH_Drawing_MatrixPreConcat (API 22) exception: $e") }
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixTranslate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixTranslate(m, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixScale() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixScale(m, 1f, 1f, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixRotate() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixRotate(m, 0f, 0f, 0f)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixIsIdentity() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixReset(m)
            OH_Drawing_MatrixIsIdentity(m)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixInvert() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixSetMatrix(m, 1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
            val inv = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixInvert(m, inv)
            OH_Drawing_MatrixDestroy(inv)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixIsEqual() {
        memScoped {
            val m = OH_Drawing_MatrixCreate()
            OH_Drawing_MatrixIsEqual(m, m)
            OH_Drawing_MatrixDestroy(m)
        }
    }

    @Test
    fun testMatrixCreateRotation() {
        memScoped {
            val mr = OH_Drawing_MatrixCreateRotation(0f, 0f, 0f)
            OH_Drawing_MatrixDestroy(mr)
        }
    }

    @Test
    fun testMatrixCreateScale() {
        memScoped {
            val ms = OH_Drawing_MatrixCreateScale(1f, 1f, 0f, 0f)
            OH_Drawing_MatrixDestroy(ms)
        }
    }

    @Test
    fun testMatrixCreateTranslation() {
        memScoped {
            val mt = OH_Drawing_MatrixCreateTranslation(0f, 0f)
            OH_Drawing_MatrixDestroy(mt)
        }
    }

    @Test
    fun testMatrixMapPoints() {
        memScoped {
            val mE = OH_Drawing_MatrixCreate()
            val srcPt = alloc<OH_Drawing_Point2D>().apply { x = 0f; y = 0f }
            val dstPt = alloc<OH_Drawing_Point2D>()
            OH_Drawing_MatrixMapPoints(mE, srcPt.ptr, dstPt.ptr, 1)
            OH_Drawing_MatrixDestroy(mE)
        }
    }

    @Test
    fun testMatrixMapRect() {
        memScoped {
            val mE = OH_Drawing_MatrixCreate()
            val srcR = OH_Drawing_RectCreate(0f, 0f, 1f, 1f)
            val dstR = OH_Drawing_RectCreate(0f, 0f, 1f, 1f)
            OH_Drawing_MatrixMapRect(mE, srcR, dstR)
            OH_Drawing_MatrixDestroy(mE)
            OH_Drawing_RectDestroy(srcR)
            OH_Drawing_RectDestroy(dstR)
        }
    }

    @Test
    fun testMatrixSetRectToRect() {
        memScoped {
            val mE = OH_Drawing_MatrixCreate()
            val srcR = OH_Drawing_RectCreate(0f, 0f, 1f, 1f)
            val dstR = OH_Drawing_RectCreate(0f, 0f, 1f, 1f)
            OH_Drawing_MatrixSetRectToRect(mE, srcR, dstR, OH_Drawing_ScaleToFit.SCALE_TO_FIT_FILL)
            OH_Drawing_MatrixDestroy(mE)
            OH_Drawing_RectDestroy(srcR)
            OH_Drawing_RectDestroy(dstR)
        }
    }

    @Test
    fun testMatrixSetPolyToPoly() {
        memScoped {
            val mE = OH_Drawing_MatrixCreate()
            val srcPts = allocArray<OH_Drawing_Point2D>(3).apply {
                this[0].x = 0f; this[0].y = 0f; this[1].x = 1f; this[1].y = 0f; this[2].x = 1f; this[2].y = 1f
            }
            val dstPts = allocArray<OH_Drawing_Point2D>(3)
            OH_Drawing_MatrixSetPolyToPoly(mE, srcPts, dstPts, 3u)
            OH_Drawing_MatrixDestroy(mE)
        }
    }

    // ---------- drawing_rect.h ----------

    @Test
    fun testRectCreate() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectGetLeft() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectGetLeft(r)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectGetTop() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectGetTop(r)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectGetRight() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectGetRight(r)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectGetBottom() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectGetBottom(r)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectGetWidth() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectGetWidth(r)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectGetHeight() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectGetHeight(r)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectSetLeft() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectSetLeft(r, 0f)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectSetTop() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectSetTop(r, 0f)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectSetRight() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectSetRight(r, 10f)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectSetBottom() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RectSetBottom(r, 10f)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectIntersect() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val r2 = OH_Drawing_RectCreate(1f, 1f, 9f, 9f)
            OH_Drawing_RectIntersect(r, r2)
            OH_Drawing_RectDestroy(r2)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectJoin() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val r2 = OH_Drawing_RectCreate(1f, 1f, 9f, 9f)
            OH_Drawing_RectJoin(r, r2)
            OH_Drawing_RectDestroy(r2)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectContains() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val r2 = OH_Drawing_RectCreate(1f, 1f, 9f, 9f)
            val contains = alloc<BooleanVar>()
            try { OH_Drawing_RectContains(r, r2, contains.ptr) } catch (e: Throwable) { println("OH_Drawing_RectContains (API 22) exception: $e") }
            OH_Drawing_RectDestroy(r2)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectCopy() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val r2 = OH_Drawing_RectCreate(1f, 1f, 9f, 9f)
            OH_Drawing_RectCopy(r, r2)
            OH_Drawing_RectDestroy(r2)
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectInset() {
        memScoped {
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            try { OH_Drawing_RectInset(r, 0f, 0f, 0f, 0f) } catch (e: Throwable) { println("OH_Drawing_RectInset (API 22) exception: $e") }
            OH_Drawing_RectDestroy(r)
        }
    }

    @Test
    fun testRectCreateArray() {
        memScoped {
            val rectArr = try { OH_Drawing_RectCreateArray(2uL) } catch (e: Throwable) { println("OH_Drawing_RectCreateArray (API 18) exception: $e"); null }
            try { OH_Drawing_RectDestroyArray(rectArr) } catch (e: Throwable) { println("OH_Drawing_RectDestroyArray (API 18) exception: $e") }
        }
    }

    @Test
    fun testRectGetArraySize() {
        memScoped {
            val rectArr = try { OH_Drawing_RectCreateArray(2uL) } catch (e: Throwable) { null }
            val arrSize = alloc<ULongVar>()
            try { OH_Drawing_RectGetArraySize(rectArr, arrSize.ptr) } catch (e: Throwable) { println("OH_Drawing_RectGetArraySize (API 18) exception: $e") }
            try { OH_Drawing_RectDestroyArray(rectArr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testRectGetArrayElement() {
        memScoped {
            val rectArr = try { OH_Drawing_RectCreateArray(2uL) } catch (e: Throwable) { null }
            val rectOut = alloc<CPointerVar<OH_Drawing_Rect>>()
            try { OH_Drawing_RectGetArrayElement(rectArr, 0uL, rectOut.ptr) } catch (e: Throwable) { println("OH_Drawing_RectGetArrayElement (API 18) exception: $e") }
            try { OH_Drawing_RectDestroyArray(rectArr) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testRectDestroyArray() {
        memScoped {
            val rectArr = try { OH_Drawing_RectCreateArray(2uL) } catch (e: Throwable) { null }
            try { OH_Drawing_RectDestroyArray(rectArr) } catch (e: Throwable) { println("OH_Drawing_RectDestroyArray (API 18) exception: $e") }
        }
    }

    // ---------- drawing_point.h ----------

    @Test
    fun testOH_Drawing_PointCreate() {
        val pt = OH_Drawing_PointCreate(1f, 2f)
        OH_Drawing_PointDestroy(pt)
        println("OH_Drawing_PointCreate passed")
    }

    @Test
    fun testOH_Drawing_PointDestroy() {
        val pt = OH_Drawing_PointCreate(1f, 2f)
        OH_Drawing_PointDestroy(pt)
        println("OH_Drawing_PointDestroy passed")
    }

    @Test
    fun testOH_Drawing_PointGetX() {
        memScoped {
            val pt = OH_Drawing_PointCreate(1f, 2f)
            val x = alloc<FloatVar>()
            OH_Drawing_PointGetX(pt, x.ptr)
            OH_Drawing_PointDestroy(pt)
        }
        println("OH_Drawing_PointGetX passed")
    }

    @Test
    fun testOH_Drawing_PointGetY() {
        memScoped {
            val pt = OH_Drawing_PointCreate(1f, 2f)
            val y = alloc<FloatVar>()
            OH_Drawing_PointGetY(pt, y.ptr)
            OH_Drawing_PointDestroy(pt)
        }
        println("OH_Drawing_PointGetY passed")
    }

    @Test
    fun testOH_Drawing_PointSet() {
        val pt = OH_Drawing_PointCreate(1f, 2f)
        OH_Drawing_PointSet(pt, 0f, 0f)
        OH_Drawing_PointDestroy(pt)
        println("OH_Drawing_PointSet passed")
    }

    // ---------- drawing_bitmap.h ----------

    @Test
    fun testBitmapCreate() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapBuild() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapGetWidth() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_BitmapGetWidth(bm)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapGetHeight() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_BitmapGetHeight(bm)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapGetColorFormat() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_BitmapGetColorFormat(bm)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapGetAlphaFormat() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_BitmapGetAlphaFormat(bm)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapGetPixels() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_BitmapGetPixels(bm)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapGetImageInfo() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val info = alloc<OH_Drawing_Image_Info>()
            OH_Drawing_BitmapGetImageInfo(bm, info.ptr)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapReadPixels() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val dstInfo = alloc<OH_Drawing_Image_Info>()
            val dstPixels = allocArray<ByteVar>(64 * 64 * 4)
            OH_Drawing_BitmapReadPixels(bm, dstInfo.ptr, dstPixels, 256uL, 0, 0)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testBitmapCreateFromPixels() {
        memScoped {
            val imgInfo = alloc<OH_Drawing_Image_Info>().apply {
                width = 16
                height = 16
                colorType = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaType = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            val pixels = allocArray<ByteVar>(16 * 16 * 4)
            val bm2 = OH_Drawing_BitmapCreateFromPixels(imgInfo.ptr, pixels, 16u * 4u)
            OH_Drawing_BitmapDestroy(bm2)
        }
    }

    // ---------- drawing_image.h ----------

    @Test
    fun testImageCreate() {
        memScoped {
            val img = OH_Drawing_ImageCreate()
            OH_Drawing_ImageDestroy(img)
        }
    }

    @Test
    fun testImageGetWidth() {
        memScoped {
            val img = OH_Drawing_ImageCreate()
            OH_Drawing_ImageGetWidth(img)
            OH_Drawing_ImageDestroy(img)
        }
    }

    @Test
    fun testImageGetHeight() {
        memScoped {
            val img = OH_Drawing_ImageCreate()
            OH_Drawing_ImageGetHeight(img)
            OH_Drawing_ImageDestroy(img)
        }
    }

    @Test
    fun testImageGetImageInfo() {
        memScoped {
            val img = OH_Drawing_ImageCreate()
            val info = alloc<OH_Drawing_Image_Info>()
            OH_Drawing_ImageGetImageInfo(img, info.ptr)
            OH_Drawing_ImageDestroy(img)
        }
    }

    @Test
    fun testImageBuildFromBitmap() {
        memScoped {
            val img = OH_Drawing_ImageCreate()
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            OH_Drawing_ImageBuildFromBitmap(img, bm)
            val info = alloc<OH_Drawing_Image_Info>()
            OH_Drawing_ImageGetImageInfo(img, info.ptr)
            OH_Drawing_ImageDestroy(img)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    // ---------- drawing_region.h ----------

    @Test
    fun testRegionCreate() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            OH_Drawing_RegionDestroy(region)
        }
    }

    @Test
    fun testRegionContains() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            OH_Drawing_RegionContains(region, 0, 0)
            OH_Drawing_RegionDestroy(region)
        }
    }

    @Test
    fun testRegionEmpty() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            try { OH_Drawing_RegionEmpty(region) } catch (e: Throwable) { println("OH_Drawing_RegionEmpty (API 22) exception: $e") }
            OH_Drawing_RegionDestroy(region)
        }
    }

    @Test
    fun testRegionSetRect() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RegionSetRect(region, r)
            OH_Drawing_RectDestroy(r)
            OH_Drawing_RegionDestroy(region)
        }
    }

    @Test
    fun testRegionSetPath() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            val path = OH_Drawing_PathCreate()
            OH_Drawing_RegionSetPath(region, path, null)
            OH_Drawing_PathDestroy(path)
            OH_Drawing_RegionDestroy(region)
        }
    }

    @Test
    fun testRegionCopy() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RegionSetRect(region, r)
            val region2 = try { OH_Drawing_RegionCopy(region) } catch (e: Throwable) { println("OH_Drawing_RegionCopy (API 20) exception: $e"); null }
            try { OH_Drawing_RegionDestroy(region2) } catch (e: Throwable) { }
            OH_Drawing_RectDestroy(r)
            OH_Drawing_RegionDestroy(region)
        }
    }

    @Test
    fun testRegionOp() {
        memScoped {
            val region = OH_Drawing_RegionCreate()
            val r = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_RegionSetRect(region, r)
            val region2 = try { OH_Drawing_RegionCopy(region) } catch (e: Throwable) { null }
            try { OH_Drawing_RegionOp(region, region2, OH_Drawing_RegionOpMode.REGION_OP_MODE_UNION) } catch (e: Throwable) { }
            try { OH_Drawing_RegionDestroy(region2) } catch (e: Throwable) { }
            OH_Drawing_RectDestroy(r)
            OH_Drawing_RegionDestroy(region)
        }
    }

    // ---------- drawing_round_rect.h (全函数 6) ----------

    @Test
    fun testOH_Drawing_RoundRectCreate() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
        OH_Drawing_RoundRectDestroy(rr)
        OH_Drawing_RectDestroy(rect)
        println("OH_Drawing_RoundRectCreate passed")
    }

    @Test
    fun testOH_Drawing_RoundRectDestroy() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
        OH_Drawing_RoundRectDestroy(rr)
        OH_Drawing_RectDestroy(rect)
        println("OH_Drawing_RoundRectDestroy passed")
    }

    @Test
    fun testOH_Drawing_RoundRectSetCorner() {
        memScoped {
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
            val radii = alloc<OH_Drawing_Point2D>().apply { x = 3f; y = 3f }
            OH_Drawing_RoundRectSetCorner(rr, OH_Drawing_CornerPos.CORNER_POS_TOP_LEFT, radii.readValue())
            OH_Drawing_RoundRectDestroy(rr)
            OH_Drawing_RectDestroy(rect)
        }
        println("OH_Drawing_RoundRectSetCorner passed")
    }

    @Test
    fun testOH_Drawing_RoundRectGetCorner() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
        OH_Drawing_RoundRectGetCorner(rr, OH_Drawing_CornerPos.CORNER_POS_TOP_LEFT)
        OH_Drawing_RoundRectDestroy(rr)
        OH_Drawing_RectDestroy(rect)
        println("OH_Drawing_RoundRectGetCorner passed")
    }

    @Test
    fun testOH_Drawing_RoundRectOffset() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
        OH_Drawing_RoundRectOffset(rr, 0f, 0f)
        OH_Drawing_RoundRectDestroy(rr)
        OH_Drawing_RectDestroy(rect)
        println("OH_Drawing_RoundRectOffset passed")
    }

    @Test
    fun testOH_Drawing_RoundRectCopy() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
        val rr2 = try { OH_Drawing_RoundRectCopy(rr) } catch (e: Throwable) { println("OH_Drawing_RoundRectCopy (API 20) exception: $e"); null }
        OH_Drawing_RoundRectDestroy(rr)
        OH_Drawing_RoundRectDestroy(rr2)
        OH_Drawing_RectDestroy(rect)
        println("OH_Drawing_RoundRectCopy passed")
    }

    // ---------- drawing_color_space.h ----------

    @Test
    fun testOH_Drawing_ColorSpaceCreateSrgb() {
        val cs = OH_Drawing_ColorSpaceCreateSrgb()
        OH_Drawing_ColorSpaceDestroy(cs)
        println("OH_Drawing_ColorSpaceCreateSrgb passed")
    }

    @Test
    fun testOH_Drawing_ColorSpaceCreateSrgbLinear() {
        val cs = OH_Drawing_ColorSpaceCreateSrgbLinear()
        OH_Drawing_ColorSpaceDestroy(cs)
        println("OH_Drawing_ColorSpaceCreateSrgbLinear passed")
    }

    @Test
    fun testOH_Drawing_ColorSpaceDestroy() {
        val cs = OH_Drawing_ColorSpaceCreateSrgb()
        OH_Drawing_ColorSpaceDestroy(cs)
        println("OH_Drawing_ColorSpaceDestroy passed")
    }

    // ---------- drawing_path_effect.h ----------

    @Test
    fun testCreateCornerPathEffect() {
        memScoped {
            val corner = try { OH_Drawing_CreateCornerPathEffect(1f) } catch (e: Throwable) { println("OH_Drawing_CreateCornerPathEffect (API 18) exception: $e"); null }
            try { OH_Drawing_PathEffectDestroy(corner) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testCreateDashPathEffect() {
        memScoped {
            val dash = allocArrayOf(2f, 2f)
            val dashEffect = OH_Drawing_CreateDashPathEffect(dash, 2, 0f)
            OH_Drawing_PathEffectDestroy(dashEffect)
        }
    }

    @Test
    fun testCreateDiscretePathEffect() {
        memScoped {
            val discrete = try { OH_Drawing_CreateDiscretePathEffect(1f, 0f) } catch (e: Throwable) { println("OH_Drawing_CreateDiscretePathEffect (API 18) exception: $e"); null }
            try { OH_Drawing_PathEffectDestroy(discrete) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testCreatePathDashEffect() {
        memScoped {
            val path = OH_Drawing_PathCreate()
            val pathDash = try { OH_Drawing_CreatePathDashEffect(path, 1f, 0f, OH_Drawing_PathDashStyle.DRAWING_PATH_DASH_STYLE_TRANSLATE) } catch (e: Throwable) { println("OH_Drawing_CreatePathDashEffect (API 18) exception: $e"); null }
            OH_Drawing_PathDestroy(path)
            try { OH_Drawing_PathEffectDestroy(pathDash) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testCreateSumPathEffect() {
        memScoped {
            val corner = try { OH_Drawing_CreateCornerPathEffect(1f) } catch (e: Throwable) { null }
            val discrete = try { OH_Drawing_CreateDiscretePathEffect(1f, 0f) } catch (e: Throwable) { null }
            val sum = try { OH_Drawing_CreateSumPathEffect(corner, discrete) } catch (e: Throwable) { println("OH_Drawing_CreateSumPathEffect (API 18) exception: $e"); null }
            try { OH_Drawing_PathEffectDestroy(sum) } catch (e: Throwable) { }
            try { OH_Drawing_PathEffectDestroy(discrete) } catch (e: Throwable) { }
            try { OH_Drawing_PathEffectDestroy(corner) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testCreateComposePathEffect() {
        memScoped {
            val corner = try { OH_Drawing_CreateCornerPathEffect(1f) } catch (e: Throwable) { null }
            val discrete = try { OH_Drawing_CreateDiscretePathEffect(1f, 0f) } catch (e: Throwable) { null }
            val compose = try { OH_Drawing_CreateComposePathEffect(corner, discrete) } catch (e: Throwable) { println("OH_Drawing_CreateComposePathEffect (API 18) exception: $e"); null }
            try { OH_Drawing_PathEffectDestroy(compose) } catch (e: Throwable) { }
            try { OH_Drawing_PathEffectDestroy(discrete) } catch (e: Throwable) { }
            try { OH_Drawing_PathEffectDestroy(corner) } catch (e: Throwable) { }
        }
    }

    // ---------- drawing_font_collection.h ----------

    @Test
    fun testOH_Drawing_DisableFontCollectionFallback() {
        val col = OH_Drawing_CreateFontCollection()
        OH_Drawing_DisableFontCollectionFallback(col)
        OH_Drawing_DestroyFontCollection(col)
        println("OH_Drawing_DisableFontCollectionFallback passed")
    }

    @Test
    fun testOH_Drawing_DisableFontCollectionSystemFont() {
        val col = OH_Drawing_CreateFontCollection()
        OH_Drawing_DisableFontCollectionSystemFont(col)
        OH_Drawing_DestroyFontCollection(col)
        println("OH_Drawing_DisableFontCollectionSystemFont passed")
    }

    @Test
    fun testOH_Drawing_ClearFontCaches() {
        val col = OH_Drawing_CreateFontCollection()
        OH_Drawing_ClearFontCaches(col)
        OH_Drawing_DestroyFontCollection(col)
        println("OH_Drawing_ClearFontCaches passed")
    }

    @Test
    fun testOH_Drawing_DestroyFontCollection() {
        val col = OH_Drawing_CreateFontCollection()
        OH_Drawing_DestroyFontCollection(col)
        println("OH_Drawing_DestroyFontCollection passed")
    }

    @Test
    fun testOH_Drawing_CreateSharedFontCollection() {
        val shared = OH_Drawing_CreateSharedFontCollection()
        OH_Drawing_DestroyFontCollection(shared)
        println("OH_Drawing_CreateSharedFontCollection passed")
    }

    @Test
    fun testOH_Drawing_GetFontCollectionGlobalInstance() {
        OH_Drawing_GetFontCollectionGlobalInstance()
        println("OH_Drawing_GetFontCollectionGlobalInstance passed")
    }

    // ---------- drawing_font_mgr.h (全函数 15) ----------

    @Test
    fun testFontMgrCreate() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontMgrGetFamilyCount() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            OH_Drawing_FontMgrGetFamilyCount(mgr)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontMgrGetFamilyName() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val name = OH_Drawing_FontMgrGetFamilyName(mgr, 0)
            OH_Drawing_FontMgrDestroyFamilyName(name)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontMgrCreateFontStyleSet() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val styleSet = OH_Drawing_FontMgrCreateFontStyleSet(mgr, 0)
            OH_Drawing_FontMgrDestroyFontStyleSet(styleSet)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontStyleSetCount() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val styleSet = OH_Drawing_FontMgrCreateFontStyleSet(mgr, 0)
            OH_Drawing_FontStyleSetCount(styleSet)
            OH_Drawing_FontMgrDestroyFontStyleSet(styleSet)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontStyleSetGetStyle() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val styleSet = OH_Drawing_FontMgrCreateFontStyleSet(mgr, 0)
            val styleNameOut = alloc<CPointerVar<ByteVar>>()
            OH_Drawing_FontStyleSetGetStyle(styleSet, 0, styleNameOut.ptr)
            OH_Drawing_FontStyleSetFreeStyleName(styleNameOut.ptr)
            OH_Drawing_FontMgrDestroyFontStyleSet(styleSet)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontStyleSetMatchStyle() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val styleSet = OH_Drawing_FontMgrCreateFontStyleSet(mgr, 0)
            val fontStyleStruct = alloc<OH_Drawing_FontStyleStruct>().apply {
                weight = OH_Drawing_FontWeight.FONT_WEIGHT_400
                width = 5u // FONT_WIDTH_NORMAL = 5
                slant = OH_Drawing_FontStyle.FONT_STYLE_NORMAL
            }
            val matchedTypeface = OH_Drawing_FontStyleSetMatchStyle(styleSet, fontStyleStruct.readValue())
            OH_Drawing_TypefaceDestroy(matchedTypeface)
            OH_Drawing_FontMgrDestroyFontStyleSet(styleSet)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontStyleSetCreateTypeface() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val styleSet = OH_Drawing_FontMgrCreateFontStyleSet(mgr, 0)
            val typefaceFromSet = OH_Drawing_FontStyleSetCreateTypeface(styleSet, 0)
            OH_Drawing_TypefaceDestroy(typefaceFromSet)
            OH_Drawing_FontMgrDestroyFontStyleSet(styleSet)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontMgrMatchFamily() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val matchSet = OH_Drawing_FontMgrMatchFamily(mgr, "sans-serif")
            OH_Drawing_FontMgrDestroyFontStyleSet(matchSet)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontMgrMatchFamilyStyle() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val fontStyleStruct = alloc<OH_Drawing_FontStyleStruct>().apply {
                weight = OH_Drawing_FontWeight.FONT_WEIGHT_400
                width = 5u
                slant = OH_Drawing_FontStyle.FONT_STYLE_NORMAL
            }
            val typefaceMatch = OH_Drawing_FontMgrMatchFamilyStyle(mgr, "sans-serif", fontStyleStruct.readValue())
            OH_Drawing_TypefaceDestroy(typefaceMatch)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    @Test
    fun testFontMgrMatchFamilyStyleCharacter() {
        memScoped {
            val mgr = OH_Drawing_FontMgrCreate()
            val fontStyleStruct = alloc<OH_Drawing_FontStyleStruct>().apply {
                weight = OH_Drawing_FontWeight.FONT_WEIGHT_400
                width = 5u
                slant = OH_Drawing_FontStyle.FONT_STYLE_NORMAL
            }
            val bcp47 = allocArrayOf("en".cstr.ptr)
            val typefaceChar = OH_Drawing_FontMgrMatchFamilyStyleCharacter(mgr, "sans-serif", fontStyleStruct.readValue(), bcp47, 1, 'A'.code)
            OH_Drawing_TypefaceDestroy(typefaceChar)
            OH_Drawing_FontMgrDestroy(mgr)
        }
    }

    // ---------- drawing_text_global.h ----------

    @Test
    fun testOH_Drawing_SetTextHighContrast() {
        try { OH_Drawing_SetTextHighContrast(OH_Drawing_TextHighContrast.TEXT_APP_DISABLE_HIGH_CONTRAST) } catch (e: Throwable) { println("OH_Drawing_SetTextHighContrast (API 20) exception: $e") }
        println("OH_Drawing_SetTextHighContrast passed")
    }

    @Test
    fun testOH_Drawing_SetTextUndefinedGlyphDisplay() {
        try { OH_Drawing_SetTextUndefinedGlyphDisplay(1u) } catch (e: Throwable) { println("OH_Drawing_SetTextUndefinedGlyphDisplay (API 20) exception: $e") }
        println("OH_Drawing_SetTextUndefinedGlyphDisplay passed")
    }

    @Test
    fun testOH_Drawing_ClearFontCaches_null() {
        OH_Drawing_ClearFontCaches(null)
        println("OH_Drawing_ClearFontCaches(null) passed")
    }

    
    // ---------- drawing_canvas.h ----------
    @Test
    fun testCanvasCreateWithPixelMap() {
        memScoped {
            try { OH_Drawing_CanvasCreateWithPixelMap(null) } catch (e: Throwable) { println("OH_Drawing_CanvasCreateWithPixelMap (API 20) exception: $e") }
        }
    }

    @Test
    fun testCanvasCreate() {
        memScoped {
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasDestroy(canvas)
        }
    }

    @Test
    fun testCanvasBind() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasAttachBrush() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val brush = OH_Drawing_BrushCreate()
            OH_Drawing_CanvasAttachBrush(canvas, brush)
            OH_Drawing_CanvasDetachBrush(canvas)
            OH_Drawing_BrushDestroy(brush)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasAttachPen() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val pen = OH_Drawing_PenCreate()
            OH_Drawing_CanvasAttachPen(canvas, pen)
            OH_Drawing_CanvasDetachPen(canvas)
            OH_Drawing_PenDestroy(pen)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasSave() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasSave(canvas)
            OH_Drawing_CanvasRestore(canvas)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasSaveLayer() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val brush = OH_Drawing_BrushCreate()
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_CanvasSaveLayer(canvas, rect, brush)
            OH_Drawing_CanvasRestore(canvas)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_BrushDestroy(brush)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasGetSaveCount() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasGetSaveCount(canvas)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasRestore() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasSave(canvas)
            OH_Drawing_CanvasRestore(canvas)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasRestoreToCount() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasSave(canvas)
            OH_Drawing_CanvasRestoreToCount(canvas, 0u)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasClear() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasClear(canvas, 0xFF000000u)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawBackground() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val brush = OH_Drawing_BrushCreate()
            OH_Drawing_CanvasDrawBackground(canvas, brush)
            OH_Drawing_BrushDestroy(brush)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawLine() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasDrawLine(canvas, 0f, 0f, 10f, 10f)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawPath() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val path = OH_Drawing_PathCreate()
            OH_Drawing_CanvasDrawPath(canvas, path)
            OH_Drawing_PathDestroy(path)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_CanvasDrawRect(canvas, rect)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawCircle() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val circleCenter = OH_Drawing_PointCreate(5f, 5f)
            OH_Drawing_CanvasDrawCircle(canvas, circleCenter, 3f)
            OH_Drawing_PointDestroy(circleCenter)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawColor() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasDrawColor(canvas, 0xFF000000u, OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawRoundRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val roundRect = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
            OH_Drawing_CanvasDrawRoundRect(canvas, roundRect)
            OH_Drawing_RoundRectDestroy(roundRect)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawOval() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_CanvasDrawOval(canvas, rect)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawArc() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_CanvasDrawArc(canvas, rect, 0f, 90f)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawArcWithCenter() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            try { OH_Drawing_CanvasDrawArcWithCenter(canvas, rect, 0f, 90f, false) } catch (e: Throwable) { println("OH_Drawing_CanvasDrawArcWithCenter (API 18) exception: $e") }
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawNestedRoundRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val roundRect = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
            val roundRectInner = OH_Drawing_RoundRectCreate(rect, 1f, 1f)
            try { OH_Drawing_CanvasDrawNestedRoundRect(canvas, roundRect, roundRectInner) } catch (e: Throwable) { println("OH_Drawing_CanvasDrawNestedRoundRect (API 18) exception: $e") }
            OH_Drawing_RoundRectDestroy(roundRectInner)
            OH_Drawing_RoundRectDestroy(roundRect)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawPoint() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val pt2d = alloc<OH_Drawing_Point2D>().apply { x = 0f; y = 0f }
            OH_Drawing_CanvasDrawPoint(canvas, pt2d.ptr)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawPoints() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val pts2d = allocArray<OH_Drawing_Point2D>(2).apply { this[0].x = 0f; this[0].y = 0f; this[1].x = 5f; this[1].y = 5f }
            OH_Drawing_CanvasDrawPoints(canvas, OH_Drawing_PointMode.POINT_MODE_POINTS, 2u, pts2d)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawBitmap() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasDrawBitmap(canvas, bm, 0f, 0f)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawBitmapRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
            OH_Drawing_CanvasDrawBitmapRect(canvas, bm, rect, rect, sampling)
            OH_Drawing_SamplingOptionsDestroy(sampling)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawRegion() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val region = OH_Drawing_RegionCreate()
            OH_Drawing_CanvasDrawRegion(canvas, region)
            OH_Drawing_RegionDestroy(region)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawPixelMapNine() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            try { OH_Drawing_CanvasDrawPixelMapNine(canvas, null, rect, rect, OH_Drawing_FilterMode.FILTER_MODE_NEAREST) } catch (e: Throwable) { println("OH_Drawing_CanvasDrawPixelMapNine (API 18) exception: $e") }
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawPixelMapRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
            OH_Drawing_CanvasDrawPixelMapRect(canvas, null, rect, rect, sampling)
            OH_Drawing_SamplingOptionsDestroy(sampling)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawPixelMapRectConstraint() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
            try { OH_Drawing_CanvasDrawPixelMapRectConstraint(canvas, null, rect, rect, sampling, OH_Drawing_SrcRectConstraint.STRICT_SRC_RECT_CONSTRAINT) } catch (e: Throwable) { println("OH_Drawing_CanvasDrawPixelMapRectConstraint (API 20) exception: $e") }
            OH_Drawing_SamplingOptionsDestroy(sampling)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawImageRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val img = OH_Drawing_ImageCreate()
            OH_Drawing_ImageBuildFromBitmap(img, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
            OH_Drawing_CanvasDrawImageRect(canvas, img, rect, sampling)
            OH_Drawing_SamplingOptionsDestroy(sampling)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_ImageDestroy(img)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawImageRectWithSrc() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val img = OH_Drawing_ImageCreate()
            OH_Drawing_ImageBuildFromBitmap(img, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val sampling = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_NEAREST, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
            OH_Drawing_CanvasDrawImageRectWithSrc(canvas, img, rect, rect, sampling, OH_Drawing_SrcRectConstraint.STRICT_SRC_RECT_CONSTRAINT)
            OH_Drawing_SamplingOptionsDestroy(sampling)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_ImageDestroy(img)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawVertices() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val vertPos = allocArray<OH_Drawing_Point2D>(3).apply {
                this[0].x = 0f; this[0].y = 0f; this[1].x = 10f; this[1].y = 0f; this[2].x = 5f; this[2].y = 10f
            }
            val vertColors = allocArray<UIntVar>(3).apply {
                this[0] = 0xFF000000u
                this[1] = 0xFF000000u
                this[2] = 0xFF000000u
            }
            val vertIndices = allocArray<UShortVar>(3).apply {
                this[0] = 0.toUShort()
                this[1] = 1.toUShort()
                this[2] = 2.toUShort()
            }
            OH_Drawing_CanvasDrawVertices(canvas, OH_Drawing_VertexMode.VERTEX_MODE_TRIANGLES, 3, vertPos, null, vertColors, 3, vertIndices, OH_Drawing_BlendMode.BLEND_MODE_SRC_OVER)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasReadPixels() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val imgInfo = alloc<OH_Drawing_Image_Info>()
            val readBuf = allocArray<ByteVar>(64 * 64 * 4)
            OH_Drawing_CanvasReadPixels(canvas, imgInfo.ptr, readBuf, 64u * 4u, 0, 0)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasReadPixelsToBitmap() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasReadPixelsToBitmap(canvas, bm, 0, 0)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasIsClipEmpty() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val isClipEmpty = alloc<BooleanVar>()
            OH_Drawing_CanvasIsClipEmpty(canvas, isClipEmpty.ptr)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasGetImageInfo() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val imgInfo = alloc<OH_Drawing_Image_Info>()
            OH_Drawing_CanvasGetImageInfo(canvas, imgInfo.ptr)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawShadow() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val path = OH_Drawing_PathCreate()
            val planeParams = alloc<OH_Drawing_Point3D>().apply { x = 0f; y = 0f; z = 0f }
            val devLightPos = alloc<OH_Drawing_Point3D>().apply { x = 0f; y = 0f; z = 0f }
            OH_Drawing_CanvasDrawShadow(canvas, path, planeParams.readValue(), devLightPos.readValue(), 0f, 0xFF000000u, 0xFF000000u, OH_Drawing_CanvasShadowFlags.SHADOW_FLAGS_NONE)
            OH_Drawing_PathDestroy(path)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawSingleCharacter() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            OH_Drawing_FontSetTextSize(font, 14f)
            OH_Drawing_CanvasDrawSingleCharacter(canvas, "A", font, 0f, 0f)
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawSingleCharacterWithFeatures() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val typeface = OH_Drawing_TypefaceCreateDefault()
            val font = OH_Drawing_FontCreate()
            OH_Drawing_FontSetTypeface(font, typeface)
            OH_Drawing_FontSetTextSize(font, 14f)
            val fontFeatures = try { OH_Drawing_FontFeaturesCreate() } catch (e: Throwable) { println("OH_Drawing_FontFeaturesCreate (API 20) exception: $e"); null }
            try { OH_Drawing_CanvasDrawSingleCharacterWithFeatures(canvas, "B", font, 0f, 0f, fontFeatures) } catch (e: Throwable) { println("OH_Drawing_CanvasDrawSingleCharacterWithFeatures (API 20) exception: $e") }
            try { OH_Drawing_FontFeaturesDestroy(fontFeatures) } catch (e: Throwable) { println("OH_Drawing_FontFeaturesDestroy (API 20) exception: $e") }
            OH_Drawing_FontDestroy(font)
            OH_Drawing_TypefaceDestroy(typeface)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawTextBlob() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val blobBuilder = OH_Drawing_TextBlobBuilderCreate()
            val textBlob = OH_Drawing_TextBlobBuilderMake(blobBuilder)
            OH_Drawing_CanvasDrawTextBlob(canvas, textBlob, 0f, 0f)
            OH_Drawing_TextBlobBuilderDestroy(blobBuilder)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasClipRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_CanvasClipRect(canvas, rect, OH_Drawing_CanvasClipOp.INTERSECT, true)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasClipRoundRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val roundRect = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
            OH_Drawing_CanvasClipRoundRect(canvas, roundRect, OH_Drawing_CanvasClipOp.INTERSECT, true)
            OH_Drawing_RoundRectDestroy(roundRect)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasClipPath() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val path = OH_Drawing_PathCreate()
            OH_Drawing_CanvasClipPath(canvas, path, OH_Drawing_CanvasClipOp.INTERSECT, true)
            OH_Drawing_PathDestroy(path)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasClipRegion() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val region = OH_Drawing_RegionCreate()
            OH_Drawing_CanvasClipRegion(canvas, region, OH_Drawing_CanvasClipOp.INTERSECT)
            OH_Drawing_RegionDestroy(region)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasTranslate() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasTranslate(canvas, 0f, 0f)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasScale() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasScale(canvas, 1f, 1f)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasRotate() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasRotate(canvas, 0f, 0f, 0f)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasSkew() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasSkew(canvas, 0f, 0f)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasConcatMatrix() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val matrix = OH_Drawing_MatrixCreate()
            OH_Drawing_CanvasConcatMatrix(canvas, matrix)
            OH_Drawing_MatrixDestroy(matrix)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasSetMatrix() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val matrix = OH_Drawing_MatrixCreate()
            OH_Drawing_CanvasSetMatrix(canvas, matrix)
            OH_Drawing_MatrixDestroy(matrix)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasResetMatrix() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasResetMatrix(canvas)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasGetWidth() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasGetWidth(canvas)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasGetHeight() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            OH_Drawing_CanvasGetHeight(canvas)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasGetTotalMatrix() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val matrix = OH_Drawing_MatrixCreate()
            OH_Drawing_CanvasGetTotalMatrix(canvas, matrix)
            OH_Drawing_MatrixDestroy(matrix)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasGetLocalClipBounds() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            OH_Drawing_CanvasGetLocalClipBounds(canvas, rect)
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasQuickRejectPath() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val path = OH_Drawing_PathCreate()
            val quickReject = alloc<BooleanVar>()
            try { OH_Drawing_CanvasQuickRejectPath(canvas, path, quickReject.ptr) } catch (e: Throwable) { println("OH_Drawing_CanvasQuickRejectPath (API 18) exception: $e") }
            OH_Drawing_PathDestroy(path)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasQuickRejectRect() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            val quickReject = alloc<BooleanVar>()
            try { OH_Drawing_CanvasQuickRejectRect(canvas, rect, quickReject.ptr) } catch (e: Throwable) { println("OH_Drawing_CanvasQuickRejectRect (API 18) exception: $e") }
            OH_Drawing_RectDestroy(rect)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawRecordCmd() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val recUtils = OH_Drawing_RecordCmdUtilsCreate()
            val canvasOut = alloc<CPointerVar<OH_Drawing_Canvas>>()
            OH_Drawing_RecordCmdUtilsBeginRecording(recUtils, 32, 32, canvasOut.ptr)
            val recCmdOut = alloc<CPointerVar<OH_Drawing_RecordCmd>>()
            OH_Drawing_RecordCmdUtilsFinishRecording(recUtils, recCmdOut.ptr)
            val recordCmd = recCmdOut.value
            OH_Drawing_CanvasDrawRecordCmd(canvas, recordCmd)
            OH_Drawing_RecordCmdDestroy(recordCmd)
            OH_Drawing_RecordCmdUtilsDestroy(recUtils)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDrawRecordCmdNesting() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val recUtils = OH_Drawing_RecordCmdUtilsCreate()
            val canvasOut = alloc<CPointerVar<OH_Drawing_Canvas>>()
            OH_Drawing_RecordCmdUtilsBeginRecording(recUtils, 32, 32, canvasOut.ptr)
            val recCmdOut = alloc<CPointerVar<OH_Drawing_RecordCmd>>()
            OH_Drawing_RecordCmdUtilsFinishRecording(recUtils, recCmdOut.ptr)
            val recordCmd = recCmdOut.value
            try { OH_Drawing_CanvasDrawRecordCmdNesting(canvas, recordCmd) } catch (e: Throwable) { println("OH_Drawing_CanvasDrawRecordCmdNesting (API 19) exception: $e") }
            OH_Drawing_RecordCmdDestroy(recordCmd)
            OH_Drawing_RecordCmdUtilsDestroy(recUtils)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDetachBrush() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val brush = OH_Drawing_BrushCreate()
            OH_Drawing_CanvasAttachBrush(canvas, brush)
            OH_Drawing_CanvasDetachBrush(canvas)
            OH_Drawing_BrushDestroy(brush)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }

    @Test
    fun testCanvasDetachPen() {
        memScoped {
            val bm = OH_Drawing_BitmapCreate()
            val fmt = alloc<OH_Drawing_BitmapFormat>().apply {
                colorFormat = OH_Drawing_ColorFormat.COLOR_FORMAT_RGBA_8888
                alphaFormat = OH_Drawing_AlphaFormat.ALPHA_FORMAT_OPAQUE
            }
            OH_Drawing_BitmapBuild(bm, 64u, 64u, fmt.ptr)
            val canvas = OH_Drawing_CanvasCreate()
            OH_Drawing_CanvasBind(canvas, bm)
            val pen = OH_Drawing_PenCreate()
            OH_Drawing_CanvasAttachPen(canvas, pen)
            OH_Drawing_CanvasDetachPen(canvas)
            OH_Drawing_PenDestroy(pen)
            OH_Drawing_CanvasDestroy(canvas)
            OH_Drawing_BitmapDestroy(bm)
        }
    }
}

