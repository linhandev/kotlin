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
import platform.ArkGraphics2D.OH_NativeBuffer.*
import platform.ArkGraphics2D.BufferCommon.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_NativeBufferTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_NativeBuffer_Usage() {
        assertTrue((NATIVEBUFFER_USAGE_CPU_READ.toLong() and (1L shl 0)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_CPU_WRITE.toLong() and (1L shl 1)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_MEM_DMA.toLong() and (1L shl 3)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_MEM_MMZ_CACHE.toLong() and (1L shl 5)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_HW_RENDER.toLong() and (1L shl 8)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_HW_TEXTURE.toLong() and (1L shl 9)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_CPU_READ_OFTEN.toLong() and (1L shl 16)) != 0L)
        assertTrue((NATIVEBUFFER_USAGE_ALIGNMENT_512.toLong() and (1L shl 18)) != 0L)
        logLine("OH_NativeBuffer_Usage passed")
    }

    @Test
    fun testEnum_OH_NativeBuffer_ColorGamut() {
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_NATIVE.toInt(), 0)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_STANDARD_BT601.toInt(), 1)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_STANDARD_BT709.toInt(), 2)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_DCI_P3.toInt(), 3)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_SRGB.toInt(), 4)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_ADOBE_RGB.toInt(), 5)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_DISPLAY_P3.toInt(), 6)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_BT2020.toInt(), 7)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_BT2100_PQ.toInt(), 8)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_BT2100_HLG.toInt(), 9)
        assertEquals(NATIVEBUFFER_COLOR_GAMUT_DISPLAY_BT2020.toInt(), 10)
        logLine("OH_NativeBuffer_ColorGamut passed")
    }

    private fun allocBuffer(memScope: MemScope): CPointer<OH_NativeBuffer>? {
        val config = memScope.alloc<OH_NativeBuffer_Config>().apply {
            width = 64
            height = 64
            format = 0
            usage = NATIVEBUFFER_USAGE_CPU_READ.toInt() or NATIVEBUFFER_USAGE_CPU_WRITE.toInt()
            stride = 0
        }
        return OH_NativeBuffer_Alloc(config.ptr)
    }

    @Test
    fun testOH_NativeBuffer_Alloc() {
        memScoped {
            val buf = allocBuffer(this)
            logLine("OH_NativeBuffer_Alloc buf=$buf")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_GetConfig() {
        memScoped {
            val buf = allocBuffer(this)
            val outConfig = alloc<OH_NativeBuffer_Config>()
            OH_NativeBuffer_GetConfig(buf, outConfig.ptr)
            logLine("OH_NativeBuffer_GetConfig width=${outConfig.width} height=${outConfig.height}")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_Reference() {
        memScoped {
            val buf = allocBuffer(this)
            val refRet = OH_NativeBuffer_Reference(buf)
            logLine("OH_NativeBuffer_Reference ret=$refRet")
            assertNotNull(refRet)
            OH_NativeBuffer_Unreference(buf)
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_Unreference() {
        memScoped {
            val buf = allocBuffer(this)
            val unrefRet = OH_NativeBuffer_Unreference(buf)
            logLine("OH_NativeBuffer_Unreference ret=$unrefRet")
            assertNotNull(unrefRet)
        }
    }

    @Test
    fun testOH_NativeBuffer_Map() {
        memScoped {
            val buf = allocBuffer(this)
            val virAddr = alloc<CPointerVar<ByteVar>>()
            val mapRet = OH_NativeBuffer_Map(buf, virAddr.ptr.reinterpret())
            logLine("OH_NativeBuffer_Map ret=$mapRet virAddr=${virAddr.value}")
            assertNotNull(mapRet)
            OH_NativeBuffer_Unmap(buf)
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_Unmap() {
        memScoped {
            val buf = allocBuffer(this)
            val virAddr = alloc<CPointerVar<ByteVar>>()
            OH_NativeBuffer_Map(buf, virAddr.ptr.reinterpret())
            val unmapRet = OH_NativeBuffer_Unmap(buf)
            logLine("OH_NativeBuffer_Unmap ret=$unmapRet")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_GetSeqNum() {
        memScoped {
            val buf = allocBuffer(this)
            val seq = OH_NativeBuffer_GetSeqNum(buf)
            logLine("OH_NativeBuffer_GetSeqNum ret=$seq")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_MapPlanes() {
        memScoped {
            val buf = allocBuffer(this)
            val virAddr2 = alloc<CPointerVar<ByteVar>>()
            val planes = alloc<OH_NativeBuffer_Planes>()
            val mapPlanesRet = OH_NativeBuffer_MapPlanes(buf, virAddr2.ptr.reinterpret(), planes.ptr)
            logLine("OH_NativeBuffer_MapPlanes ret=$mapPlanesRet planeCount=${planes.planeCount}")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_FromNativeWindowBuffer() {
        memScoped {
            val fromNwbBuf = alloc<CPointerVar<OH_NativeBuffer>>()
            val fromNwbRet = OH_NativeBuffer_FromNativeWindowBuffer(null, fromNwbBuf.ptr)
            logLine("OH_NativeBuffer_FromNativeWindowBuffer ret=$fromNwbRet buffer=${fromNwbBuf.value}")
        }
    }

    @Test
    fun testOH_NativeBuffer_SetColorSpace() {
        memScoped {
            val buf = allocBuffer(this)
            val setCsRet = OH_NativeBuffer_SetColorSpace(buf, OH_NativeBuffer_ColorSpace.OH_COLORSPACE_NONE)
            logLine("OH_NativeBuffer_SetColorSpace ret=$setCsRet")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_GetColorSpace() {
        memScoped {
            val buf = allocBuffer(this)
            val outCs = alloc<OH_NativeBuffer_ColorSpace.Var>()
            val getCsRet = OH_NativeBuffer_GetColorSpace(buf, outCs.ptr)
            logLine("OH_NativeBuffer_GetColorSpace ret=$getCsRet colorSpace=${outCs.value}")
            assertNotNull(getCsRet)
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_SetMetadataValue() {
        memScoped {
            val buf = allocBuffer(this)
            val metaCValues = cValuesOf(0.toUByte(), 0.toUByte(), 0.toUByte(), 0.toUByte())
            val setMetaRet = OH_NativeBuffer_SetMetadataValue(buf, OH_NativeBuffer_MetadataKey.OH_HDR_METADATA_TYPE, 4, metaCValues)
            logLine("OH_NativeBuffer_SetMetadataValue ret=$setMetaRet")
            OH_NativeBuffer_Unreference(buf)
        }
    }

    @Test
    fun testOH_NativeBuffer_GetMetadataValue() {
        memScoped {
            val buf = allocBuffer(this)
            val sizeVar = alloc<IntVar>()
            val metaPtr = alloc<CPointerVar<ByteVar>>()
            val getMetaRet = OH_NativeBuffer_GetMetadataValue(buf, OH_NativeBuffer_MetadataKey.OH_HDR_METADATA_TYPE, sizeVar.ptr, metaPtr.ptr.reinterpret())
            logLine("OH_NativeBuffer_GetMetadataValue ret=$getMetaRet size=${sizeVar.value} metadata=${metaPtr.value}")
            OH_NativeBuffer_Unreference(buf)
        }
    }
}
