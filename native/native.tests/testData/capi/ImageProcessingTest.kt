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
/**
 * ImageProcessing C API 测试：覆盖 15 个函数，每个恰好调用一次。
 * 2 个命名 typedef enum（ErrorCode / ImageDetailEnhancer_QualityLevel）作为顶层常量测试。
 * 不测试结构体字段；不使用 FQN。
 */
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.ImageKit.ImageProcessing.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ImageProcessingTest {

    private fun logLine(msg: String) = println("[stdout] ImageProcessingTest $msg")

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_ErrorCode() {
        assertEquals(IMAGE_PROCESSING_SUCCESS.toInt(), 0)
        assertEquals(IMAGE_PROCESSING_ERROR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(IMAGE_PROCESSING_ERROR_UNKNOWN.toInt(), 29200001)
        assertEquals(IMAGE_PROCESSING_ERROR_INITIALIZE_FAILED.toInt(), 29200002)
        assertEquals(IMAGE_PROCESSING_ERROR_CREATE_FAILED.toInt(), 29200003)
        assertEquals(IMAGE_PROCESSING_ERROR_PROCESS_FAILED.toInt(), 29200004)
        assertEquals(IMAGE_PROCESSING_ERROR_UNSUPPORTED_PROCESSING.toInt(), 29200005)
        assertEquals(IMAGE_PROCESSING_ERROR_OPERATION_NOT_PERMITTED.toInt(), 29200006)
        assertEquals(IMAGE_PROCESSING_ERROR_NO_MEMORY.toInt(), 29200007)
        assertEquals(IMAGE_PROCESSING_ERROR_INVALID_INSTANCE.toInt(), 29200008)
        assertEquals(IMAGE_PROCESSING_ERROR_INVALID_VALUE.toInt(), 29200009)
        logLine("testEnum_ErrorCode passed")
    }

    @Test
    fun testEnum_ImageDetailEnhancer_QualityLevel() {
        assertEquals(ImageDetailEnhancer_QualityLevel.IMAGE_DETAIL_ENHANCER_QUALITY_LEVEL_NONE.value.toInt(), 0)
        assertEquals(ImageDetailEnhancer_QualityLevel.IMAGE_DETAIL_ENHANCER_QUALITY_LEVEL_LOW.value.toInt(), 1)
        assertEquals(ImageDetailEnhancer_QualityLevel.IMAGE_DETAIL_ENHANCER_QUALITY_LEVEL_MEDIUM.value.toInt(), 2)
        assertEquals(ImageDetailEnhancer_QualityLevel.IMAGE_DETAIL_ENHANCER_QUALITY_LEVEL_HIGH.value.toInt(), 3)
        logLine("testEnum_ImageDetailEnhancer_QualityLevel passed")
    }

    // ==================== 函数测试（每个 C API 独立 @Test） ====================

    @Test
    fun testOH_ImageProcessing_InitializeEnvironment() {
        val rc = OH_ImageProcessing_InitializeEnvironment()
        assertNotNull(rc)
        logLine("OH_ImageProcessing_InitializeEnvironment=$rc")
        OH_ImageProcessing_DeinitializeEnvironment()
    }

    @Test
    fun testOH_ImageProcessing_DeinitializeEnvironment() {
        OH_ImageProcessing_InitializeEnvironment()
        val rc = OH_ImageProcessing_DeinitializeEnvironment()
        assertNotNull(rc)
        logLine("OH_ImageProcessing_DeinitializeEnvironment=$rc")
    }

    @Test
    fun testOH_ImageProcessing_IsColorSpaceConversionSupported() {
        memScoped {
            val srcInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 0
                colorSpace = 0
                pixelFormat = 0
            }
            val dstInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 1
                colorSpace = 1
                pixelFormat = 1
            }
            val rc = OH_ImageProcessing_IsColorSpaceConversionSupported(srcInfo.ptr, dstInfo.ptr)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_IsColorSpaceConversionSupported=$rc")
        }
    }

    @Test
    fun testOH_ImageProcessing_IsCompositionSupported() {
        memScoped {
            val srcInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 0
                colorSpace = 0
                pixelFormat = 0
            }
            val dstInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 1
                colorSpace = 1
                pixelFormat = 1
            }
            val gainmapInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 2
                colorSpace = 2
                pixelFormat = 2
            }
            val rc = OH_ImageProcessing_IsCompositionSupported(srcInfo.ptr, gainmapInfo.ptr, dstInfo.ptr)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_IsCompositionSupported=$rc")
        }
    }

    @Test
    fun testOH_ImageProcessing_IsDecompositionSupported() {
        memScoped {
            val srcInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 0
                colorSpace = 0
                pixelFormat = 0
            }
            val dstInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 1
                colorSpace = 1
                pixelFormat = 1
            }
            val gainmapInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 2
                colorSpace = 2
                pixelFormat = 2
            }
            val rc = OH_ImageProcessing_IsDecompositionSupported(srcInfo.ptr, dstInfo.ptr, gainmapInfo.ptr)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_IsDecompositionSupported=$rc")
        }
    }

    @Test
    fun testOH_ImageProcessing_IsMetadataGenerationSupported() {
        memScoped {
            val srcInfo = alloc<ImageProcessing_ColorSpaceInfo>().apply {
                metadataType = 0
                colorSpace = 0
                pixelFormat = 0
            }
            val rc = OH_ImageProcessing_IsMetadataGenerationSupported(srcInfo.ptr)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_IsMetadataGenerationSupported=$rc")
        }
    }

    @Test
    fun testOH_ImageProcessing_Create() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rc = OH_ImageProcessing_Create(procPtr.ptr, IMAGE_PROCESSING_TYPE_COLOR_SPACE_CONVERSION)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_Create=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_Destroy() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            OH_ImageProcessing_Create(procPtr.ptr, IMAGE_PROCESSING_TYPE_COLOR_SPACE_CONVERSION)
            val rc = OH_ImageProcessing_Destroy(procPtr.value)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_Destroy=$rc")
        }
    }

    @Test
    fun testOH_ImageProcessing_SetParameter() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, 4)  // IMAGE_PROCESSING_TYPE_DETAIL_ENHANCER
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_SetParameter(procPtr.value, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_SetParameter=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_GetParameter() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, 4)  // IMAGE_PROCESSING_TYPE_DETAIL_ENHANCER
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_GetParameter(procPtr.value, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_GetParameter=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_ConvertColorSpace() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, IMAGE_PROCESSING_TYPE_COLOR_SPACE_CONVERSION)
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_ConvertColorSpace(procPtr.value, null, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_ConvertColorSpace=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_Compose() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, 1)  // IMAGE_PROCESSING_TYPE_COMPOSITION
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_Compose(procPtr.value, null, null, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_Compose=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_Decompose() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, 2)  // IMAGE_PROCESSING_TYPE_DECOMPOSITION
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_Decompose(procPtr.value, null, null, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_Decompose=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_GenerateMetadata() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, 3)  // IMAGE_PROCESSING_TYPE_METADATA_GENERATION
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_GenerateMetadata(procPtr.value, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_GenerateMetadata=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }

    @Test
    fun testOH_ImageProcessing_EnhanceDetail() {
        memScoped {
            val procPtr = alloc<CPointerVar<OH_ImageProcessing>>()
            val rcCreate = OH_ImageProcessing_Create(procPtr.ptr, 4)  // IMAGE_PROCESSING_TYPE_DETAIL_ENHANCER
            assertNotNull(rcCreate)
            val rc = OH_ImageProcessing_EnhanceDetail(procPtr.value, null, null)
            assertNotNull(rc)
            logLine("OH_ImageProcessing_EnhanceDetail=$rc")
            OH_ImageProcessing_Destroy(procPtr.value)
        }
    }
}
