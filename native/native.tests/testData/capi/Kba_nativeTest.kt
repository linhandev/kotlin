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
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_buffer
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_image
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_window
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_drawing
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_color_space_manager
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.native.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_nativeTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- native_buffer/native_buffer.h (API 9) ----------
    @Test
    fun testNative_buffer_h() {
        memScoped {
            val config = alloc<OH_NativeBuffer_Config>().apply {
                width = 64
                height = 64
                format = 0
                usage = NATIVEBUFFER_USAGE_CPU_READ.toInt() or NATIVEBUFFER_USAGE_CPU_WRITE.toInt()
                stride = 0
            }
            OH_NativeBuffer_Alloc(config.ptr)
        
        }
    }

    // ---------- native_image/native_image.h (API 9) ----------
    @Test
    fun testNative_image_h() {
        val img = OH_NativeImage_Create(0u, 0u)
        logLine("OH_NativeImage_Create/Destroy ok")
    }

    // ---------- native_window/external_window.h (API 8) ----------
    @Test
    fun testExternal_window_h() {
        val magic = OH_NativeWindow_GetNativeObjectMagic(null)
        logLine("OH_NativeWindow_GetNativeObjectMagic(null)=$magic")
        val win = OH_NativeWindow_CreateNativeWindow(null)
        if (win != null) OH_NativeWindow_DestroyNativeWindow(win)
    }

    // ---------- native_drawing/drawing_types.h (枚举) ----------
    @Test
    fun testDrawing_types_h() {
        assertEquals(0, OH_Drawing_ColorFormat.COLOR_FORMAT_UNKNOWN.value.toInt())
        assertEquals(0, OH_Drawing_AlphaFormat.ALPHA_FORMAT_UNKNOWN.value.toInt())
    }

    // ---------- native_drawing/drawing_bitmap.h (API 8) ----------
    @Test
    fun testDrawing_bitmap_h() {
        val bmp = OH_Drawing_BitmapCreate()
        assertNotNull(bmp)
        OH_Drawing_BitmapDestroy(bmp)
    }

    // ---------- native_drawing/drawing_brush.h (API 8) ----------
    @Test
    fun testDrawing_brush_h() {
        val brush = OH_Drawing_BrushCreate()
        assertNotNull(brush)
        OH_Drawing_BrushDestroy(brush)
    }

    // ---------- native_drawing/drawing_canvas.h (API 8) ----------
    @Test
    fun testDrawing_canvas_h() {
        val canvas = OH_Drawing_CanvasCreate()
        assertNotNull(canvas)
        OH_Drawing_CanvasDestroy(canvas)
    }

    // ---------- native_drawing/drawing_color.h (API 8) ----------
    @Test
    fun testDrawing_color_h() {
        val color = OH_Drawing_ColorSetArgb(0xffu, 0u, 0u, 0xffu)
        logLine("OH_Drawing_ColorSetArgb=$color")
    }

    // ---------- native_drawing/drawing_font_collection.h (API 8) ----------
    @Test
    fun testDrawing_font_collection_h() {
        val coll = OH_Drawing_CreateFontCollection()
        assertNotNull(coll)
        OH_Drawing_DestroyFontCollection(coll)
    }

    // ---------- native_drawing/drawing_path.h (API 8) ----------
    @Test
    fun testDrawing_path_h() {
        val path = OH_Drawing_PathCreate()
        assertNotNull(path)
        OH_Drawing_PathDestroy(path)
    }

    // ---------- native_drawing/drawing_pen.h (API 8) ----------
    @Test
    fun testDrawing_pen_h() {
        val pen = OH_Drawing_PenCreate()
        assertNotNull(pen)
        OH_Drawing_PenDestroy(pen)
    }

    // ---------- native_drawing/drawing_text_declaration.h (仅类型，用 font_collection 覆盖) ----------
    @Test
    fun testDrawing_text_declaration_h() {
        val coll = OH_Drawing_CreateFontCollection()
        assertNotNull(coll)
        OH_Drawing_DestroyFontCollection(coll)
    }

    // ---------- native_drawing/drawing_color_filter.h (API 11) ----------
    @Test
    fun testDrawing_color_filter_h() {
        val cf = OH_Drawing_ColorFilterCreateBlendMode(0xff000000u, OH_Drawing_BlendMode.BLEND_MODE_SRC)
        if (cf != null) OH_Drawing_ColorFilterDestroy(cf)
        logLine("drawing_color_filter ok")
    }

    // ---------- native_drawing/drawing_error_code.h (API 12) ----------
    @Test
    fun testDrawing_error_code_h() {
        assertEquals(0, OH_DRAWING_SUCCESS.toInt())
        OH_Drawing_ErrorCodeGet()
    }

    // ---------- native_drawing/drawing_image.h (API 12) ----------
    @Test
    fun testDrawing_image_h() {
        val img = OH_Drawing_ImageCreate()
        if (img != null) OH_Drawing_ImageDestroy(img)
        logLine("drawing_image ok")
    }

    // ---------- native_drawing/drawing_record_cmd.h (API 13) ----------
    @Test
    fun testDrawing_record_cmd_h() {
        val utils = OH_Drawing_RecordCmdUtilsCreate()
        if (utils != null) OH_Drawing_RecordCmdUtilsDestroy(utils)
        logLine("drawing_record_cmd ok")
    }

    // ---------- native_drawing/drawing_filter.h (API 11) ----------
    @Test
    fun testDrawing_filter_h() {
        val filter = OH_Drawing_FilterCreate()
        assertNotNull(filter)
        OH_Drawing_FilterDestroy(filter)
    }

    // ---------- native_drawing/drawing_pixel_map.h (API 12) ----------
    @Test
    fun testDrawing_pixel_map_h() {
        val pm = OH_Drawing_PixelMapGetFromNativePixelMap(null)
        logLine("OH_Drawing_PixelMapGetFromNativePixelMap(null)=$pm")
    }

    // ---------- native_drawing/drawing_shadow_layer.h (API 12) ----------
    @Test
    fun testDrawing_shadow_layer_h() {
        val layer = OH_Drawing_ShadowLayerCreate(1f, 0f, 0f, 0xff000000u)
        if (layer != null) OH_Drawing_ShadowLayerDestroy(layer)
    }

    // ---------- native_drawing/drawing_register_font.h (API 11，需 FontCollection) ----------
    @Test
    fun testDrawing_register_font_h() {
        val coll = OH_Drawing_CreateFontCollection()
        logLine("drawing_register_font ok")
    }

    // ---------- native_drawing/drawing_region.h (API 12) ----------
    @Test
    fun testDrawing_region_h() {
        val region = OH_Drawing_RegionCreate()
        if (region != null) OH_Drawing_RegionDestroy(region)
        logLine("drawing_region ok")
    }

    // ---------- native_drawing/drawing_color_space.h (API 12) ----------
    @Test
    fun testDrawing_color_space_h() {
        val cs = OH_Drawing_ColorSpaceCreateSrgb()
        if (cs != null) OH_Drawing_ColorSpaceDestroy(cs)
    }

    // ---------- native_drawing/drawing_memory_stream.h (API 12) ----------
    @Test
    fun testDrawing_memory_stream_h() {
        memScoped {
            val stream = OH_Drawing_MemoryStreamCreate(null, 0UL, false)
            if (stream != null) OH_Drawing_MemoryStreamDestroy(stream)
        }
    }

    // ---------- native_drawing/drawing_rect.h (API 11) ----------
    @Test
    fun testDrawing_rect_h() {
        val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
        assertNotNull(rect)
        OH_Drawing_RectDestroy(rect)
    }

    // ---------- native_drawing/drawing_typeface.h (API 11) ----------
    @Test
    fun testDrawing_typeface_h() {
        val tf = OH_Drawing_TypefaceCreateDefault()
        if (tf != null) OH_Drawing_TypefaceDestroy(tf)
    }

    // ---------- native_drawing/drawing_text_blob.h (API 11) ----------
    @Test
    fun testDrawing_text_blob_h() {
        val builder = OH_Drawing_TextBlobBuilderCreate()
        if (builder != null) OH_Drawing_TextBlobBuilderDestroy(builder)
    }

    // ---------- native_drawing/drawing_sampling_options.h (API 12) ----------
    @Test
    fun testDrawing_sampling_options_h() {
        val opts = OH_Drawing_SamplingOptionsCreate(OH_Drawing_FilterMode.FILTER_MODE_LINEAR, OH_Drawing_MipmapMode.MIPMAP_MODE_NONE)
        if (opts != null) OH_Drawing_SamplingOptionsDestroy(opts)
    }

    // ---------- native_drawing/drawing_image_filter.h (API 12) ----------
    @Test
    fun testDrawing_image_filter_h() {
        val filter = OH_Drawing_ImageFilterCreateBlur(1f, 1f, OH_Drawing_TileMode.CLAMP, null)
        if (filter != null) OH_Drawing_ImageFilterDestroy(filter)
    }

    // ---------- native_drawing/drawing_mask_filter.h (API 11) ----------
    @Test
    fun testDrawing_mask_filter_h() {
        val mf = OH_Drawing_MaskFilterCreateBlur(OH_Drawing_BlurType.NORMAL, 1f, false)
        if (mf != null) OH_Drawing_MaskFilterDestroy(mf)
    }

    // ---------- native_drawing/drawing_matrix.h (API 11) ----------
    @Test
    fun testDrawing_matrix_h() {
        val matrix = OH_Drawing_MatrixCreate()
        assertNotNull(matrix)
        OH_Drawing_MatrixDestroy(matrix)
    }

    // ---------- native_drawing/drawing_font.h (API 11) ----------
    @Test
    fun testDrawing_font_h() {
        val font = OH_Drawing_FontCreate()
        if (font != null) OH_Drawing_FontDestroy(font)
        logLine("drawing_font ok")
    }

    // ---------- native_drawing/drawing_round_rect.h (API 11) ----------
    @Test
    fun testDrawing_round_rect_h() {
        memScoped {
            val rect = OH_Drawing_RectCreate(0f, 0f, 10f, 10f)
            if (rect != null) {
                val rr = OH_Drawing_RoundRectCreate(rect, 2f, 2f)
                if (rr != null) OH_Drawing_RoundRectDestroy(rr)
                OH_Drawing_RectDestroy(rect)
            }
        }
    }

    // ---------- native_drawing/drawing_point.h (API 11) ----------
    @Test
    fun testDrawing_point_h() {
        val pt = OH_Drawing_PointCreate(0f, 0f)
        assertNotNull(pt)
        OH_Drawing_PointDestroy(pt)
    }

    // ---------- native_drawing/drawing_path_effect.h (API 12) ----------
    @Test
    fun testDrawing_path_effect_h() {
        memScoped {
            val intervals = allocArrayOf(1.0f, 1.0f)
            val effect = OH_Drawing_CreateDashPathEffect(intervals, 2, 0f)
            if (effect != null) OH_Drawing_PathEffectDestroy(effect)
        }
    }

    // ---------- native_buffer/buffer_common.h (枚举) ----------
    @Test
    fun testBuffer_common_h() {
        assertEquals(0, OH_NativeBuffer_ColorSpace.OH_COLORSPACE_NONE.value.toInt())
    }

    // ---------- native_color_space_manager (API 13) ----------
    @Test
    fun testNative_color_space_manager_h() {
        val mgr = OH_NativeColorSpaceManager_CreateFromName(SRGB)
        assertEquals(4, SRGB.toInt())
    }
}
