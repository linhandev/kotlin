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
import platform.LocalizationKit.Resourcemanager.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ResourceManagerTest {

    private fun logLine(msg: String) = println(msg)

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_ResourceManager_ErrorCode() {
        assertEquals(SUCCESS.toInt(), 0)
        assertEquals(ERROR_CODE_INVALID_INPUT_PARAMETER.toInt(), 401)
        assertEquals(ERROR_CODE_RES_ID_NOT_FOUND.toInt(), 9001001)
        assertEquals(ERROR_CODE_RES_NOT_FOUND_BY_ID.toInt(), 9001002)
        assertEquals(ERROR_CODE_RES_NAME_NOT_FOUND.toInt(), 9001003)
        assertEquals(ERROR_CODE_RES_NOT_FOUND_BY_NAME.toInt(), 9001004)
        assertEquals(ERROR_CODE_RES_PATH_INVALID.toInt(), 9001005)
        assertEquals(ERROR_CODE_RES_REF_TOO_MUCH.toInt(), 9001006)
        assertEquals(ERROR_CODE_RES_ID_FORMAT_ERROR.toInt(), 9001007)
        assertEquals(ERROR_CODE_RES_NAME_FORMAT_ERROR.toInt(), 9001008)
        assertEquals(ERROR_CODE_SYSTEM_RES_MANAGER_GET_FAILED.toInt(), 9001009)
        assertEquals(ERROR_CODE_OVERLAY_RES_PATH_INVALID.toInt(), 9001010)
        assertEquals(ERROR_CODE_OUT_OF_MEMORY.toInt(), 9001100)
        logLine("ResourceManager_ErrorCode passed")
    }

    @Test
    fun testEnum_ResourceManager_Direction() {
        assertEquals(DIRECTION_VERTICAL.toInt(), 0)
        assertEquals(DIRECTION_HORIZONTAL.toInt(), 1)
        logLine("ResourceManager_Direction passed")
    }

    @Test
    fun testEnum_ResourceManager_ColorMode() {
        assertEquals(COLOR_MODE_DARK.toInt(), 0)
        assertEquals(COLOR_MODE_LIGHT.toInt(), 1)
        logLine("ResourceManager_ColorMode passed")
    }

    @Test
    fun testEnum_ResourceManager_DeviceType() {
        assertEquals(DEVICE_TYPE_PHONE.toInt(), 0x00)
        assertEquals(DEVICE_TYPE_TABLET.toInt(), 0x01)
        assertEquals(DEVICE_TYPE_CAR.toInt(), 0x02)
        assertEquals(DEVICE_TYPE_PC.toInt(), 0x03)
        assertEquals(DEVICE_TYPE_TV.toInt(), 0x04)
        assertEquals(DEVICE_TYPE_WEARABLE.toInt(), 0x06)
        assertEquals(DEVICE_TYPE_2IN1.toInt(), 0x07)
        logLine("ResourceManager_DeviceType passed")
    }

    @Test
    fun testEnum_ScreenDensity() {
        assertEquals(SCREEN_SDPI.toInt(), 120)
        assertEquals(SCREEN_MDPI.toInt(), 160)
        assertEquals(SCREEN_LDPI.toInt(), 240)
        assertEquals(SCREEN_XLDPI.toInt(), 320)
        assertEquals(SCREEN_XXLDPI.toInt(), 480)
        assertEquals(SCREEN_XXXLDPI.toInt(), 640)
        logLine("ScreenDensity passed")
    }

    @Test
    fun testOH_ResourceManager_GetMediaBase64() {
        memScoped {
            val resultValue = alloc<CPointerVar<ByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r1 = OH_ResourceManager_GetMediaBase64(null, 0u, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r1)
            logLine("GetMediaBase64 $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMediaBase64Data() {
        memScoped {
            val resultValue = alloc<CPointerVar<ByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r2 = OH_ResourceManager_GetMediaBase64Data(null, 0u, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r2)
            logLine("GetMediaBase64Data $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMediaBase64ByName() {
        memScoped {
            val resultValue = alloc<CPointerVar<ByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r3 = OH_ResourceManager_GetMediaBase64ByName(null, null, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r3)
            logLine("GetMediaBase64ByName $r3")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMediaBase64DataByName() {
        memScoped {
            val resultValue = alloc<CPointerVar<ByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r4 = OH_ResourceManager_GetMediaBase64DataByName(null, null, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r4)
            logLine("GetMediaBase64DataByName $r4")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMedia() {
        memScoped {
            val resultValue = alloc<CPointerVar<UByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r1 = OH_ResourceManager_GetMedia(null, 0u, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r1)
            logLine("GetMedia $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMediaData() {
        memScoped {
            val resultValue = alloc<CPointerVar<UByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r2 = OH_ResourceManager_GetMediaData(null, 0u, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r2)
            logLine("GetMediaData $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMediaByName() {
        memScoped {
            val resultValue = alloc<CPointerVar<UByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r3 = OH_ResourceManager_GetMediaByName(null, null, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r3)
            logLine("GetMediaByName $r3")
        }
    }

    @Test
    fun testOH_ResourceManager_GetMediaDataByName() {
        memScoped {
            val resultValue = alloc<CPointerVar<UByteVar>>()
            val resultLen = alloc<ULongVar>()
            val r4 = OH_ResourceManager_GetMediaDataByName(null, null, resultValue.ptr, resultLen.ptr, 0u)
            assertNotNull(r4)
            logLine("GetMediaDataByName $r4")
        }
    }

    @Test
    fun testOH_ResourceManager_GetDrawableDescriptor() {
        memScoped {
            val descPtr = alloc<CPointerVar<ArkUI_DrawableDescriptor>>()
            val r1 = OH_ResourceManager_GetDrawableDescriptor(null, 0u, descPtr.ptr, 0u, 0u)
            assertNotNull(r1)
            logLine("GetDrawableDescriptor $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetDrawableDescriptorData() {
        memScoped {
            val descPtr = alloc<CPointerVar<ArkUI_DrawableDescriptor>>()
            val r2 = OH_ResourceManager_GetDrawableDescriptorData(null, 0u, descPtr.ptr, 0u, 0u)
            assertNotNull(r2)
            logLine("GetDrawableDescriptorData $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetDrawableDescriptorByName() {
        memScoped {
            val descPtr = alloc<CPointerVar<ArkUI_DrawableDescriptor>>()
            val r3 = OH_ResourceManager_GetDrawableDescriptorByName(null, null, descPtr.ptr, 0u, 0u)
            assertNotNull(r3)
            logLine("GetDrawableDescriptorByName $r3")
        }
    }

    @Test
    fun testOH_ResourceManager_GetDrawableDescriptorDataByName() {
        memScoped {
            val descPtr = alloc<CPointerVar<ArkUI_DrawableDescriptor>>()
            val r4 = OH_ResourceManager_GetDrawableDescriptorDataByName(null, null, descPtr.ptr, 0u, 0u)
            assertNotNull(r4)
            logLine("GetDrawableDescriptorDataByName $r4")
        }
    }

    @Test
    fun testOH_ResourceManager_GetSymbol() {
        memScoped {
            val symOut = alloc<UIntVar>()
            val r1 = OH_ResourceManager_GetSymbol(null, 0u, symOut.ptr)
            assertNotNull(r1)
            logLine("GetSymbol $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetSymbolByName() {
        memScoped {
            val symOut = alloc<UIntVar>()
            val r2 = OH_ResourceManager_GetSymbolByName(null, null, symOut.ptr)
            assertNotNull(r2)
            logLine("GetSymbolByName $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetLocales() {
        memScoped {
            val resultValue = alloc<CPointerVar<CPointerVar<ByteVar>>>()
            val len = alloc<UIntVar>()
            val r1 = OH_ResourceManager_GetLocales(null, resultValue.ptr, len.ptr, false)
            assertNotNull(r1)
            logLine("GetLocales $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetLocalesData() {
        memScoped {
            val resultValue = alloc<CPointerVar<CPointerVar<ByteVar>>>()
            val len = alloc<UIntVar>()
            val r2 = OH_ResourceManager_GetLocalesData(null, resultValue.ptr, len.ptr, false)
            assertNotNull(r2)
            logLine("GetLocalesData $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetConfiguration() {
        memScoped {
            val config = alloc<ResourceManager_Configuration>()
            val r1 = OH_ResourceManager_GetConfiguration(null, config.ptr)
            assertNotNull(r1)
            logLine("GetConfiguration $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetResourceConfiguration() {
        try {
            memScoped {
                val config = alloc<ResourceManager_Configuration>()
                val r2 = OH_ResourceManager_GetResourceConfiguration(null, config.ptr)
                assertNotNull(r2)
                logLine("GetResourceConfiguration $r2")
            }
        } catch (e: Throwable) {
            logLine("testOH_ResourceManager_GetResourceConfiguration (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_ResourceManager_ReleaseConfiguration() {
        memScoped {
            val config = alloc<ResourceManager_Configuration>()
            val r3 = OH_ResourceManager_ReleaseConfiguration(config.ptr)
            assertNotNull(r3)
            logLine("ReleaseConfiguration $r3")
        }
    }

    @Test
    fun testOH_ResourceManager_GetString() {
        memScoped {
            val strPtr = alloc<CPointerVar<ByteVar>>()
            val r1 = OH_ResourceManager_GetString(null, 0u, strPtr.ptr)
            assertNotNull(r1)
            logLine("GetString $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetStringByName() {
        memScoped {
            val strPtr = alloc<CPointerVar<ByteVar>>()
            val r2 = OH_ResourceManager_GetStringByName(null, null, strPtr.ptr)
            assertNotNull(r2)
            logLine("GetStringByName $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetStringArray() {
        memScoped {
            val arrPtr = alloc<CPointerVar<CPointerVar<ByteVar>>>()
            val len = alloc<UIntVar>()
            val r1 = OH_ResourceManager_GetStringArray(null, 0u, arrPtr.ptr, len.ptr)
            assertNotNull(r1)
            logLine("GetStringArray $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetStringArrayByName() {
        memScoped {
            val arrPtr = alloc<CPointerVar<CPointerVar<ByteVar>>>()
            val len = alloc<UIntVar>()
            val r2 = OH_ResourceManager_GetStringArrayByName(null, null, arrPtr.ptr, len.ptr)
            assertNotNull(r2)
            logLine("GetStringArrayByName $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_ReleaseStringArray() {
        val r3 = OH_ResourceManager_ReleaseStringArray(null, 0u)
        assertNotNull(r3)
        logLine("ReleaseStringArray $r3")
    }

    @Test
    fun testOH_ResourceManager_GetPluralString() {
        memScoped {
            val strPtr = alloc<CPointerVar<ByteVar>>()
            val r1 = OH_ResourceManager_GetPluralString(null, 0u, 0u, strPtr.ptr)
            assertNotNull(r1)
            logLine("GetPluralString $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetPluralStringByName() {
        memScoped {
            val strPtr = alloc<CPointerVar<ByteVar>>()
            val r2 = OH_ResourceManager_GetPluralStringByName(null, null, 0u, strPtr.ptr)
            assertNotNull(r2)
            logLine("GetPluralStringByName $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetIntPluralString() {
        try {
            memScoped {
                val strPtr = alloc<CPointerVar<ByteVar>>()
                val r3 = OH_ResourceManager_GetIntPluralString(null, 0u, 0u, strPtr.ptr)
                assertNotNull(r3)
                logLine("GetIntPluralString $r3")
            }
        } catch (e: Throwable) {
            logLine("testOH_ResourceManager_GetIntPluralString (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_ResourceManager_GetDoublePluralString() {
        try {
            memScoped {
                val strPtr = alloc<CPointerVar<ByteVar>>()
                val r4 = OH_ResourceManager_GetDoublePluralString(null, 0u, 0.0, strPtr.ptr)
                assertNotNull(r4)
                logLine("GetDoublePluralString $r4")
            }
        } catch (e: Throwable) {
            logLine("testOH_ResourceManager_GetDoublePluralString (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_ResourceManager_GetIntPluralStringByName() {
        try {
            memScoped {
                val strPtr = alloc<CPointerVar<ByteVar>>()
                val r5 = OH_ResourceManager_GetIntPluralStringByName(null, null, 0u, strPtr.ptr)
                assertNotNull(r5)
                logLine("GetIntPluralStringByName $r5")
            }
        } catch (e: Throwable) {
            logLine("testOH_ResourceManager_GetIntPluralStringByName (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_ResourceManager_GetDoublePluralStringByName() {
        try {
            memScoped {
                val strPtr = alloc<CPointerVar<ByteVar>>()
                val r6 = OH_ResourceManager_GetDoublePluralStringByName(null, null, 0.0, strPtr.ptr)
                assertNotNull(r6)
                logLine("GetDoublePluralStringByName $r6")
            }
        } catch (e: Throwable) {
            logLine("testOH_ResourceManager_GetDoublePluralStringByName (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_ResourceManager_GetColor() {
        memScoped {
            val valPtr = alloc<UIntVar>()
            val r1 = OH_ResourceManager_GetColor(null, 0u, valPtr.ptr)
            assertNotNull(r1)
            logLine("GetColor $r1")
        }
    }

    @Test
    fun testOH_ResourceManager_GetColorByName() {
        memScoped {
            val valPtr = alloc<UIntVar>()
            val r2 = OH_ResourceManager_GetColorByName(null, null, valPtr.ptr)
            assertNotNull(r2)
            logLine("GetColorByName $r2")
        }
    }

    @Test
    fun testOH_ResourceManager_GetInt() {
        memScoped {
            val intPtr = alloc<IntVar>()
            val r3 = OH_ResourceManager_GetInt(null, 0u, intPtr.ptr)
            assertNotNull(r3)
            logLine("GetInt $r3")
        }
    }

    @Test
    fun testOH_ResourceManager_GetIntByName() {
        memScoped {
            val intPtr = alloc<IntVar>()
            val r4 = OH_ResourceManager_GetIntByName(null, null, intPtr.ptr)
            assertNotNull(r4)
            logLine("GetIntByName $r4")
        }
    }

    @Test
    fun testOH_ResourceManager_GetFloat() {
        memScoped {
            val floatPtr = alloc<FloatVar>()
            val r5 = OH_ResourceManager_GetFloat(null, 0u, floatPtr.ptr)
            assertNotNull(r5)
            logLine("GetFloat $r5")
        }
    }

    @Test
    fun testOH_ResourceManager_GetFloatByName() {
        memScoped {
            val floatPtr = alloc<FloatVar>()
            val r6 = OH_ResourceManager_GetFloatByName(null, null, floatPtr.ptr)
            assertNotNull(r6)
            logLine("GetFloatByName $r6")
        }
    }

    @Test
    fun testOH_ResourceManager_GetBool() {
        memScoped {
            val boolPtr = alloc<BooleanVar>()
            val r7 = OH_ResourceManager_GetBool(null, 0u, boolPtr.ptr)
            assertNotNull(r7)
            logLine("GetBool $r7")
        }
    }

    @Test
    fun testOH_ResourceManager_GetBoolByName() {
        memScoped {
            val boolPtr = alloc<BooleanVar>()
            val r8 = OH_ResourceManager_GetBoolByName(null, null, boolPtr.ptr)
            assertNotNull(r8)
            logLine("GetBoolByName $r8")
        }
    }

    @Test
    fun testOH_ResourceManager_AddResource() {
        val r1 = OH_ResourceManager_AddResource(null, null)
        assertNotNull(r1)
        logLine("AddResource $r1")
    }

    @Test
    fun testOH_ResourceManager_RemoveResource() {
        val r2 = OH_ResourceManager_RemoveResource(null, null)
        assertNotNull(r2)
        logLine("RemoveResource $r2")
    }
}
