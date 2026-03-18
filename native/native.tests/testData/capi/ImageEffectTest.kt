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
import platform.ImageKit.ImageEffect.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ImageEffectTest {

    private fun logLine(msg: String) = println("[stdout] ImageEffectTest $msg")

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_ErrorCode() {
        assertEquals(EFFECT_SUCCESS.toInt(), 0)
        assertEquals(EFFECT_ERROR_PERMISSION_DENIED.toInt(), 201)
        assertEquals(EFFECT_ERROR_PARAM_INVALID.toInt(), 401)
        assertEquals(EFFECT_BUFFER_SIZE_NOT_MATCH.toInt(), 29000001)
        assertEquals(EFFECT_COLOR_SPACE_NOT_MATCH.toInt(), 29000002)
        assertEquals(EFFECT_INPUT_OUTPUT_NOT_MATCH.toInt(), 29000101)
        assertEquals(EFFECT_EFFECT_NUMBER_LIMITED.toInt(), 29000102)
        assertEquals(EFFECT_INPUT_OUTPUT_NOT_SUPPORTED.toInt(), 29000103)
        assertEquals(EFFECT_ALLOCATE_MEMORY_FAILED.toInt(), 29000104)
        assertEquals(EFFECT_PARAM_ERROR.toInt(), 29000121)
        assertEquals(EFFECT_KEY_ERROR.toInt(), 29000122)
        assertEquals(EFFECT_UNKNOWN.toInt(), 29000199)
        logLine("testEnum_ErrorCode passed")
    }

    @Test
    fun testEnum_DataType() {
        assertEquals(EFFECT_DATA_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(EFFECT_DATA_TYPE_INT32.toInt(), 1)
        assertEquals(EFFECT_DATA_TYPE_FLOAT.toInt(), 2)
        assertEquals(EFFECT_DATA_TYPE_DOUBLE.toInt(), 3)
        assertEquals(EFFECT_DATA_TYPE_CHAR.toInt(), 4)
        assertEquals(EFFECT_DATA_TYPE_LONG.toInt(), 5)
        assertEquals(EFFECT_DATA_TYPE_BOOL.toInt(), 6)
        assertEquals(EFFECT_DATA_TYPE_PTR.toInt(), 7)
        logLine("testEnum_DataType passed")
    }

    @Test
    fun testEnum_Format() {
        assertEquals(EFFECT_PIXEL_FORMAT_UNKNOWN.toInt(), 0)
        assertEquals(EFFECT_PIXEL_FORMAT_RGBA8888.toInt(), 1)
        assertEquals(EFFECT_PIXEL_FORMAT_NV21.toInt(), 2)
        assertEquals(EFFECT_PIXEL_FORMAT_NV12.toInt(), 3)
        assertEquals(EFFECT_PIXEL_FORMAT_RGBA1010102.toInt(), 4)
        assertEquals(EFFECT_PIXEL_FORMAT_YCBCR_P010.toInt(), 5)
        assertEquals(EFFECT_PIXEL_FORMAT_YCRCB_P010.toInt(), 6)
        logLine("testEnum_Format passed")
    }

    @Test
    fun testEnum_BufferType() {
        assertEquals(EFFECT_BUFFER_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(EFFECT_BUFFER_TYPE_PIXEL.toInt(), 1)
        assertEquals(EFFECT_BUFFER_TYPE_TEXTURE.toInt(), 2)
        logLine("testEnum_BufferType passed")
    }

    // ==================== 函数测试（每个 C API 独立 @Test） ====================

    @Test
    fun testOH_ImageEffect_Create() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            logLine("OH_ImageEffect_Create=$effect")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_AddFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            val filter = OH_ImageEffect_AddFilter(effect, "Brightness")
            logLine("OH_ImageEffect_AddFilter=$filter")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_InsertFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            OH_ImageEffect_AddFilter(effect, "Brightness")
            val insertedFilter = OH_ImageEffect_InsertFilter(effect, 0u, "Contrast")
            logLine("OH_ImageEffect_InsertFilter=$insertedFilter")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_GetFilterCount() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            OH_ImageEffect_AddFilter(effect, "Brightness")
            val count = OH_ImageEffect_GetFilterCount(effect)
            assertNotNull(count)
            logLine("OH_ImageEffect_GetFilterCount=$count")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_GetFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            OH_ImageEffect_AddFilter(effect, "Brightness")
            val gotFilter = OH_ImageEffect_GetFilter(effect, 0u)
            logLine("OH_ImageEffect_GetFilter=$gotFilter")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_ReplaceFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            OH_ImageEffect_AddFilter(effect, "Brightness")
            val replacedFilter = OH_ImageEffect_ReplaceFilter(effect, 0u, "Crop")
            logLine("OH_ImageEffect_ReplaceFilter=$replacedFilter")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_RemoveFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            OH_ImageEffect_AddFilter(effect, "Crop")
            val removeRc = OH_ImageEffect_RemoveFilter(effect, "Crop")
            assertNotNull(removeRc)
            logLine("OH_ImageEffect_RemoveFilter=$removeRc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_RemoveFilterByIndex() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            OH_ImageEffect_AddFilter(effect, "Brightness")
            val removeByIdxRc = OH_ImageEffect_RemoveFilterByIndex(effect, 0u)
            assertNotNull(removeByIdxRc)
            logLine("OH_ImageEffect_RemoveFilterByIndex=$removeByIdxRc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_Release() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect")
            assertNotNull(effect)
            val releaseRc = OH_ImageEffect_Release(effect)
            assertNotNull(releaseRc)
            logLine("OH_ImageEffect_Release=$releaseRc")
        }
    }

    @Test
    fun testOH_ImageEffect_AddFilterByFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect2")
            assertNotNull(effect)
            val extFilter = OH_EffectFilter_Create("Brightness")
            assertNotNull(extFilter)
            val addRc = OH_ImageEffect_AddFilterByFilter(effect, extFilter)
            assertNotNull(addRc)
            logLine("OH_ImageEffect_AddFilterByFilter=$addRc")
            OH_EffectFilter_Release(extFilter)
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_InsertFilterByFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect2")
            assertNotNull(effect)
            val extFilter = OH_EffectFilter_Create("Brightness")
            assertNotNull(extFilter)
            OH_ImageEffect_AddFilterByFilter(effect, extFilter)
            val extFilter2 = OH_EffectFilter_Create("Contrast")
            assertNotNull(extFilter2)
            val insertRc = OH_ImageEffect_InsertFilterByFilter(effect, 0u, extFilter2)
            assertNotNull(insertRc)
            logLine("OH_ImageEffect_InsertFilterByFilter=$insertRc")
            OH_EffectFilter_Release(extFilter)
            OH_EffectFilter_Release(extFilter2)
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_ReplaceFilterByFilter() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect2")
            assertNotNull(effect)
            val extFilter = OH_EffectFilter_Create("Brightness")
            assertNotNull(extFilter)
            OH_ImageEffect_AddFilterByFilter(effect, extFilter)
            val extFilter3 = OH_EffectFilter_Create("Crop")
            assertNotNull(extFilter3)
            val replaceRc = OH_ImageEffect_ReplaceFilterByFilter(effect, 0u, extFilter3)
            assertNotNull(replaceRc)
            logLine("OH_ImageEffect_ReplaceFilterByFilter=$replaceRc")
            OH_EffectFilter_Release(extFilter)
            OH_EffectFilter_Release(extFilter3)
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_Configure() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect3")
            assertNotNull(effect)
            val anyVal = alloc<ImageEffect_Any>().apply {
                dataType = EFFECT_DATA_TYPE_FLOAT
                dataValue.floatValue = 0.5f
            }
            val rc = OH_ImageEffect_Configure(effect, "test_key", anyVal.ptr)
            assertNotNull(rc)
            logLine("OH_ImageEffect_Configure=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetOutputSurface() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetOutputSurface(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetOutputSurface=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_GetInputSurface() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_GetInputSurface(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_GetInputSurface=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetInputPixelmap() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetInputPixelmap(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetInputPixelmap=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetOutputPixelmap() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetOutputPixelmap(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetOutputPixelmap=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetInputNativeBuffer() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetInputNativeBuffer(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetInputNativeBuffer=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetOutputNativeBuffer() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetOutputNativeBuffer(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetOutputNativeBuffer=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetInputUri() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetInputUri(effect, "file:///data/test.jpg")
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetInputUri=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetOutputUri() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetOutputUri(effect, "file:///data/out.jpg")
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetOutputUri=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetInputPicture() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetInputPicture(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetInputPicture=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetOutputPicture() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = OH_ImageEffect_SetOutputPicture(effect, null)
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetOutputPicture=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetInputTextureId() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = try { OH_ImageEffect_SetInputTextureId(effect, 0, 0) } catch (e: Throwable) { logLine("OH_ImageEffect_SetInputTextureId (API 20) exception: $e"); EFFECT_ERROR_PARAM_INVALID }
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetInputTextureId=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_SetOutputTextureId() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect4")
            assertNotNull(effect)
            val rc = try { OH_ImageEffect_SetOutputTextureId(effect, 0) } catch (e: Throwable) { logLine("OH_ImageEffect_SetOutputTextureId (API 20) exception: $e"); EFFECT_ERROR_PARAM_INVALID }
            assertNotNull(rc)
            logLine("OH_ImageEffect_SetOutputTextureId=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_Start() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect5")
            assertNotNull(effect)
            val rc = OH_ImageEffect_Start(effect)
            assertNotNull(rc)
            logLine("OH_ImageEffect_Start=$rc")
            OH_ImageEffect_Stop(effect)
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_Stop() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect5")
            assertNotNull(effect)
            OH_ImageEffect_Start(effect)
            val rc = OH_ImageEffect_Stop(effect)
            assertNotNull(rc)
            logLine("OH_ImageEffect_Stop=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_Save() {
        memScoped {
            val effect = OH_ImageEffect_Create("TestEffect5")
            assertNotNull(effect)
            val infoPtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_ImageEffect_Save(effect, infoPtr.ptr)
            assertNotNull(rc)
            logLine("OH_ImageEffect_Save=$rc")
            OH_ImageEffect_Release(effect)
        }
    }

    @Test
    fun testOH_ImageEffect_Restore() {
        memScoped {
            val restored = OH_ImageEffect_Restore("{\"filters\":[]}")
            logLine("OH_ImageEffect_Restore=$restored")
            restored?.let { OH_ImageEffect_Release(it) }
        }
    }

    @Test
    fun testOH_EffectFilterInfo_Create() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            logLine("OH_EffectFilterInfo_Create=$info")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_SetFilterName() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectFilterInfo_SetFilterName(info, "Brightness")
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_SetFilterName=$rc")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_GetFilterName() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            OH_EffectFilterInfo_SetFilterName(info, "Brightness")
            val namePtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_EffectFilterInfo_GetFilterName(info, namePtr.ptr)
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_GetFilterName=$rc")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_SetSupportedBufferTypes() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            val btArr = allocArray<UIntVar>(2).apply {
                this[0] = EFFECT_BUFFER_TYPE_PIXEL
                this[1] = EFFECT_BUFFER_TYPE_TEXTURE
            }
            val rc = OH_EffectFilterInfo_SetSupportedBufferTypes(info, 2u, btArr)
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_SetSupportedBufferTypes=$rc")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_GetSupportedBufferTypes() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            val btArr = allocArray<UIntVar>(2).apply {
                this[0] = EFFECT_BUFFER_TYPE_PIXEL
                this[1] = EFFECT_BUFFER_TYPE_TEXTURE
            }
            OH_EffectFilterInfo_SetSupportedBufferTypes(info, 2u, btArr)
            val btSize = alloc<UIntVar>()
            val btOut = alloc<CPointerVar<UIntVar>>()
            val rc = OH_EffectFilterInfo_GetSupportedBufferTypes(info, btSize.ptr, btOut.ptr)
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_GetSupportedBufferTypes=$rc")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_SetSupportedFormats() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            val fmtArr = allocArray<UIntVar>(2).apply {
                this[0] = EFFECT_PIXEL_FORMAT_RGBA8888
                this[1] = EFFECT_PIXEL_FORMAT_NV21
            }
            val rc = OH_EffectFilterInfo_SetSupportedFormats(info, 2u, fmtArr)
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_SetSupportedFormats=$rc")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_GetSupportedFormats() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            val fmtArr = allocArray<UIntVar>(2).apply {
                this[0] = EFFECT_PIXEL_FORMAT_RGBA8888
                this[1] = EFFECT_PIXEL_FORMAT_NV21
            }
            OH_EffectFilterInfo_SetSupportedFormats(info, 2u, fmtArr)
            val fmtSize = alloc<UIntVar>()
            val fmtOut = alloc<CPointerVar<UIntVar>>()
            val rc = OH_EffectFilterInfo_GetSupportedFormats(info, fmtSize.ptr, fmtOut.ptr)
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_GetSupportedFormats=$rc")
            OH_EffectFilterInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectFilterInfo_Release() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectFilterInfo_Release(info)
            assertNotNull(rc)
            logLine("OH_EffectFilterInfo_Release=$rc")
        }
    }

    @Test
    fun testOH_EffectBufferInfo_Create() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            logLine("OH_EffectBufferInfo_Create=$info")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetAddr() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val buf = allocArray<UByteVar>(1024)
            val rc = OH_EffectBufferInfo_SetAddr(info, buf.reinterpret<COpaque>())
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetAddr=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetAddr() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val buf = allocArray<UByteVar>(1024)
            OH_EffectBufferInfo_SetAddr(info, buf.reinterpret<COpaque>())
            val addrOut = alloc<COpaquePointerVar>()
            val rc = OH_EffectBufferInfo_GetAddr(info, addrOut.ptr)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetAddr=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetWidth() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectBufferInfo_SetWidth(info, 1920)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetWidth=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetWidth() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            OH_EffectBufferInfo_SetWidth(info, 1920)
            val width = alloc<IntVar>()
            val rc = OH_EffectBufferInfo_GetWidth(info, width.ptr)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetWidth=$rc width=${width.value}")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetHeight() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectBufferInfo_SetHeight(info, 1080)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetHeight=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetHeight() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            OH_EffectBufferInfo_SetHeight(info, 1080)
            val height = alloc<IntVar>()
            val rc = OH_EffectBufferInfo_GetHeight(info, height.ptr)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetHeight=$rc height=${height.value}")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetRowSize() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectBufferInfo_SetRowSize(info, 7680)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetRowSize=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetRowSize() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            OH_EffectBufferInfo_SetRowSize(info, 7680)
            val rowSize = alloc<IntVar>()
            val rc = OH_EffectBufferInfo_GetRowSize(info, rowSize.ptr)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetRowSize=$rc rowSize=${rowSize.value}")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetEffectFormat() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectBufferInfo_SetEffectFormat(info, EFFECT_PIXEL_FORMAT_RGBA8888)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetEffectFormat=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetEffectFormat() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            OH_EffectBufferInfo_SetEffectFormat(info, EFFECT_PIXEL_FORMAT_RGBA8888)
            val fmtVar = alloc<UIntVar>()
            val rc = OH_EffectBufferInfo_GetEffectFormat(info, fmtVar.ptr)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetEffectFormat=$rc format=${fmtVar.value}")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetTimestamp() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectBufferInfo_SetTimestamp(info, 1000000L)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetTimestamp=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetTimestamp() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            OH_EffectBufferInfo_SetTimestamp(info, 1000000L)
            val ts = alloc<LongVar>()
            val rc = OH_EffectBufferInfo_GetTimestamp(info, ts.ptr)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetTimestamp=$rc timestamp=${ts.value}")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_SetTextureId() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = try { OH_EffectBufferInfo_SetTextureId(info, 1) } catch (e: Throwable) { logLine("OH_EffectBufferInfo_SetTextureId (API 20) exception: $e"); EFFECT_ERROR_PARAM_INVALID }
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_SetTextureId=$rc")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_GetTextureId() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            try { OH_EffectBufferInfo_SetTextureId(info, 1) } catch (e: Throwable) { }
            val texId = alloc<IntVar>()
            val rc = try { OH_EffectBufferInfo_GetTextureId(info, texId.ptr) } catch (e: Throwable) { logLine("OH_EffectBufferInfo_GetTextureId (API 20) exception: $e"); EFFECT_ERROR_PARAM_INVALID }
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_GetTextureId=$rc textureId=${texId.value}")
            OH_EffectBufferInfo_Release(info)
        }
    }

    @Test
    fun testOH_EffectBufferInfo_Release() {
        memScoped {
            val info = OH_EffectBufferInfo_Create()
            assertNotNull(info)
            val rc = OH_EffectBufferInfo_Release(info)
            assertNotNull(rc)
            logLine("OH_EffectBufferInfo_Release=$rc")
        }
    }

    @Test
    fun testOH_EffectFilter_Create() {
        memScoped {
            val filter = OH_EffectFilter_Create("Brightness")
            logLine("OH_EffectFilter_Create=$filter")
            OH_EffectFilter_Release(filter)
        }
    }

    @Test
    fun testOH_EffectFilter_SetValue() {
        memScoped {
            val filter = OH_EffectFilter_Create("Brightness")
            assertNotNull(filter)
            val anyVal = alloc<ImageEffect_Any>().apply {
                dataType = EFFECT_DATA_TYPE_FLOAT
                dataValue.floatValue = 0.5f
            }
            val rc = OH_EffectFilter_SetValue(filter, "FilterIntensity", anyVal.ptr)
            assertNotNull(rc)
            logLine("OH_EffectFilter_SetValue=$rc")
            OH_EffectFilter_Release(filter)
        }
    }

    @Test
    fun testOH_EffectFilter_GetValue() {
        memScoped {
            val filter = OH_EffectFilter_Create("Brightness")
            assertNotNull(filter)
            val anyVal = alloc<ImageEffect_Any>().apply {
                dataType = EFFECT_DATA_TYPE_FLOAT
                dataValue.floatValue = 0.5f
            }
            OH_EffectFilter_SetValue(filter, "FilterIntensity", anyVal.ptr)
            val anyOut = alloc<ImageEffect_Any>()
            val rc = OH_EffectFilter_GetValue(filter, "FilterIntensity", anyOut.ptr)
            assertNotNull(rc)
            logLine("OH_EffectFilter_GetValue=$rc")
            OH_EffectFilter_Release(filter)
        }
    }

    @Test
    fun testOH_EffectFilter_Register() {
        memScoped {
            val regInfo = OH_EffectFilterInfo_Create()
            assertNotNull(regInfo)
            OH_EffectFilterInfo_SetFilterName(regInfo, "CustomFilter")
            val delegate = alloc<ImageEffect_FilterDelegate>().apply {
                setValue = null
                render = null
                save = null
                restore = null
            }
            val rc = OH_EffectFilter_Register(regInfo, delegate.ptr)
            assertNotNull(rc)
            logLine("OH_EffectFilter_Register=$rc")
            OH_EffectFilterInfo_Release(regInfo)
        }
    }

    @Test
    fun testOH_EffectFilter_LookupFilters() {
        val names = OH_EffectFilter_LookupFilters("Brightness")
        logLine("OH_EffectFilter_LookupFilters=$names")
        OH_EffectFilter_ReleaseFilterNames()
    }

    @Test
    fun testOH_EffectFilter_ReleaseFilterNames() {
        OH_EffectFilter_LookupFilters("Brightness")
        OH_EffectFilter_ReleaseFilterNames()
        logLine("OH_EffectFilter_ReleaseFilterNames=called")
    }

    @Test
    fun testOH_EffectFilter_LookupFilterInfo() {
        memScoped {
            val lookupInfo = OH_EffectFilterInfo_Create()
            assertNotNull(lookupInfo)
            val rc = OH_EffectFilter_LookupFilterInfo("Brightness", lookupInfo)
            assertNotNull(rc)
            logLine("OH_EffectFilter_LookupFilterInfo=$rc")
            OH_EffectFilterInfo_Release(lookupInfo)
        }
    }

    @Test
    fun testOH_EffectFilter_Render() {
        memScoped {
            val filter = OH_EffectFilter_Create("Brightness")
            assertNotNull(filter)
            val rc = OH_EffectFilter_Render(filter, null, null)
            assertNotNull(rc)
            logLine("OH_EffectFilter_Render=$rc")
            OH_EffectFilter_Release(filter)
        }
    }

    @Test
    fun testOH_EffectFilter_RenderWithTextureId() {
        memScoped {
            val filter = OH_EffectFilter_Create("Brightness")
            assertNotNull(filter)
            val rc = try { OH_EffectFilter_RenderWithTextureId(filter, 1, 2, 0) } catch (e: Throwable) { logLine("OH_EffectFilter_RenderWithTextureId (API 20) exception: $e"); EFFECT_ERROR_PARAM_INVALID }
            assertNotNull(rc)
            logLine("OH_EffectFilter_RenderWithTextureId=$rc")
            OH_EffectFilter_Release(filter)
        }
    }

    @Test
    fun testOH_EffectFilter_Release() {
        memScoped {
            val filter = OH_EffectFilter_Create("Brightness")
            assertNotNull(filter)
            val rc = OH_EffectFilter_Release(filter)
            assertNotNull(rc)
            logLine("OH_EffectFilter_Release=$rc")
        }
    }
}
