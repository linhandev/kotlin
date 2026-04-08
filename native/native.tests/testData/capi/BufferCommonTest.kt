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
class BufferCommonTest {

    private fun logLine(message: String) {
        println("[stdout] BufferCommonTest $message")
    }

    @Test
    fun testColorSpaceEnums() {
        logLine("--- OH_NativeBuffer_ColorSpace ---")
        logLine("OH_COLORSPACE_NONE=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_NONE}")
        logLine("OH_COLORSPACE_BT601_EBU_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT601_EBU_FULL}")
        logLine("OH_COLORSPACE_BT601_SMPTE_C_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT601_SMPTE_C_FULL}")
        logLine("OH_COLORSPACE_BT709_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT709_FULL}")
        logLine("OH_COLORSPACE_BT2020_HLG_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT2020_HLG_FULL}")
        logLine("OH_COLORSPACE_BT2020_PQ_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT2020_PQ_FULL}")
        logLine("OH_COLORSPACE_BT601_EBU_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT601_EBU_LIMIT}")
        logLine("OH_COLORSPACE_BT601_SMPTE_C_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT601_SMPTE_C_LIMIT}")
        logLine("OH_COLORSPACE_BT709_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT709_LIMIT}")
        logLine("OH_COLORSPACE_BT2020_HLG_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT2020_HLG_LIMIT}")
        logLine("OH_COLORSPACE_BT2020_PQ_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_BT2020_PQ_LIMIT}")
        logLine("OH_COLORSPACE_SRGB_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_SRGB_FULL}")
        logLine("OH_COLORSPACE_P3_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_P3_FULL}")
        logLine("OH_COLORSPACE_P3_HLG_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_P3_HLG_FULL}")
        logLine("OH_COLORSPACE_P3_PQ_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_P3_PQ_FULL}")
        logLine("OH_COLORSPACE_ADOBERGB_FULL=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_ADOBERGB_FULL}")
        logLine("OH_COLORSPACE_SRGB_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_SRGB_LIMIT}")
        logLine("OH_COLORSPACE_P3_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_P3_LIMIT}")
        logLine("OH_COLORSPACE_P3_HLG_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_P3_HLG_LIMIT}")
        logLine("OH_COLORSPACE_P3_PQ_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_P3_PQ_LIMIT}")
        logLine("OH_COLORSPACE_ADOBERGB_LIMIT=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_ADOBERGB_LIMIT}")
        logLine("OH_COLORSPACE_LINEAR_SRGB=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_LINEAR_SRGB}")
        logLine("OH_COLORSPACE_LINEAR_BT709=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_LINEAR_BT709}")
        logLine("OH_COLORSPACE_LINEAR_P3=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_LINEAR_P3}")
        logLine("OH_COLORSPACE_LINEAR_BT2020=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_LINEAR_BT2020}")
        logLine("OH_COLORSPACE_DISPLAY_SRGB=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_SRGB}")
        logLine("OH_COLORSPACE_DISPLAY_P3_SRGB=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_P3_SRGB}")
        logLine("OH_COLORSPACE_DISPLAY_P3_HLG=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_P3_HLG}")
        logLine("OH_COLORSPACE_DISPLAY_P3_PQ=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_P3_PQ}")
        logLine("OH_COLORSPACE_DISPLAY_BT2020_SRGB=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_BT2020_SRGB}")
        logLine("OH_COLORSPACE_DISPLAY_BT2020_HLG=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_BT2020_HLG}")
        logLine("OH_COLORSPACE_DISPLAY_BT2020_PQ=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorSpace.OH_COLORSPACE_DISPLAY_BT2020_PQ}")
    }

    @Test
    fun testMetadataTypeEnums() {
        logLine("--- OH_NativeBuffer_MetadataType ---")
        logLine("OH_VIDEO_HDR_HLG=${platform.ArkGraphics2D.BufferCommon.OH_VIDEO_HDR_HLG}")
        logLine("OH_VIDEO_HDR_HDR10=${platform.ArkGraphics2D.BufferCommon.OH_VIDEO_HDR_HDR10}")
        logLine("OH_VIDEO_HDR_VIVID=${platform.ArkGraphics2D.BufferCommon.OH_VIDEO_HDR_VIVID}")
        logLine("OH_IMAGE_HDR_VIVID_DUAL=${platform.ArkGraphics2D.BufferCommon.OH_IMAGE_HDR_VIVID_DUAL}")
        logLine("OH_IMAGE_HDR_VIVID_SINGLE=${platform.ArkGraphics2D.BufferCommon.OH_IMAGE_HDR_VIVID_SINGLE}")
        logLine("OH_VIDEO_NONE=${platform.ArkGraphics2D.BufferCommon.OH_VIDEO_NONE}")
        assertNotEquals(
            platform.ArkGraphics2D.BufferCommon.OH_VIDEO_HDR_HLG,
            platform.ArkGraphics2D.BufferCommon.OH_VIDEO_NONE
        )
    }

    @Test
    fun testMetadataKeyEnums() {
        logLine("--- OH_NativeBuffer_MetadataKey ---")
        logLine("OH_HDR_METADATA_TYPE=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_MetadataKey.OH_HDR_METADATA_TYPE}")
        logLine("OH_HDR_STATIC_METADATA=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_MetadataKey.OH_HDR_STATIC_METADATA}")
        logLine("OH_HDR_DYNAMIC_METADATA=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_MetadataKey.OH_HDR_DYNAMIC_METADATA}")
        logLine("OH_REGION_OF_INTEREST_METADATA=${platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_MetadataKey.OH_REGION_OF_INTEREST_METADATA}")
    }

    @Test
    fun testFormatEnums() {
        logLine("--- OH_NativeBuffer_Format ---")
        logLine("NATIVEBUFFER_PIXEL_FMT_CLUT8=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_CLUT8}")
        logLine("NATIVEBUFFER_PIXEL_FMT_CLUT1=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_CLUT1}")
        logLine("NATIVEBUFFER_PIXEL_FMT_CLUT4=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_CLUT4}")
        logLine("NATIVEBUFFER_PIXEL_FMT_RGB_565=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_RGB_565}")
        logLine("NATIVEBUFFER_PIXEL_FMT_RGBA_8888=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_RGBA_8888}")
        logLine("NATIVEBUFFER_PIXEL_FMT_YCBCR_420_SP=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_YCBCR_420_SP}")
        logLine("NATIVEBUFFER_PIXEL_FMT_BUTT=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_PIXEL_FMT_BUTT}")

    }

    @Test
    fun testTransformTypeEnums() {
        logLine("--- OH_NativeBuffer_TransformType ---")
        logLine("NATIVEBUFFER_ROTATE_NONE=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_ROTATE_NONE}")
        logLine("NATIVEBUFFER_ROTATE_90=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_ROTATE_90}")
        logLine("NATIVEBUFFER_ROTATE_180=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_ROTATE_180}")
        logLine("NATIVEBUFFER_ROTATE_270=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_ROTATE_270}")
        logLine("NATIVEBUFFER_FLIP_H=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_H}")
        logLine("NATIVEBUFFER_FLIP_V=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_V}")
        logLine("NATIVEBUFFER_FLIP_H_ROT90=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_H_ROT90}")
        logLine("NATIVEBUFFER_FLIP_V_ROT90=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_V_ROT90}")
        logLine("NATIVEBUFFER_FLIP_H_ROT180=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_H_ROT180}")
        logLine("NATIVEBUFFER_FLIP_V_ROT180=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_V_ROT180}")
        logLine("NATIVEBUFFER_FLIP_H_ROT270=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_H_ROT270}")
        logLine("NATIVEBUFFER_FLIP_V_ROT270=${platform.ArkGraphics2D.BufferCommon.NATIVEBUFFER_FLIP_V_ROT270}")

    }

    @Test
    fun testStructTypes() {
        logLine("--- OH_NativeBuffer structs ---")
        memScoped {
            val colorXY = alloc<platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_ColorXY>().apply {
                x = 0.5f
                y = 0.5f
            }
            logLine("OH_NativeBuffer_ColorXY x=${colorXY.x} y=${colorXY.y}")

            val smpte2086 = alloc<platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_Smpte2086>().apply {
                displayPrimaryRed.x = 0.64f
                displayPrimaryRed.y = 0.33f
                displayPrimaryGreen.x = 0.30f
                displayPrimaryGreen.y = 0.60f
                displayPrimaryBlue.x = 0.15f
                displayPrimaryBlue.y = 0.06f
                whitePoint.x = 0.3127f
                whitePoint.y = 0.3290f
                maxLuminance = 1000.0f
                minLuminance = 0.1f
            }
            logLine("OH_NativeBuffer_Smpte2086 maxL=${smpte2086.maxLuminance} minL=${smpte2086.minLuminance}")

            val cta861 = alloc<platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_Cta861>().apply {
                maxContentLightLevel = 1000.0f
                maxFrameAverageLightLevel = 400.0f
            }
            logLine("OH_NativeBuffer_Cta861 maxContent=${cta861.maxContentLightLevel} maxFrameAvg=${cta861.maxFrameAverageLightLevel}")

            val staticMetadata = alloc<platform.ArkGraphics2D.BufferCommon.OH_NativeBuffer_StaticMetadata>().apply {
                smpte2086.maxLuminance = 1000.0f
                smpte2086.minLuminance = 0.1f
                cta861.maxContentLightLevel = 1000.0f
                cta861.maxFrameAverageLightLevel = 400.0f
            }
            logLine("OH_NativeBuffer_StaticMetadata smpte2086.maxL=${staticMetadata.smpte2086.maxLuminance} cta861.maxContent=${staticMetadata.cta861.maxContentLightLevel}")
        }
    }
}
