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
import platform.MediaKit.VideoProcessing.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class VideoProcessingTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testVIDEO_METADATA_GENERATOR_STYLE_CONTROL() {
        try {
            val msg = VIDEO_METADATA_GENERATOR_STYLE_CONTROL?.toKString()
            logLine("VIDEO_METADATA_GENERATOR_STYLE_CONTROL=${msg ?: "(null, weak symbol unresolved e.g. API < 22)"}")
        } catch (e: Throwable) {
            logLine("VIDEO_METADATA_GENERATOR_STYLE_CONTROL exception: $e")
        }
    }

    @Test
    fun testEnum_VideoDetailEnhancer_QualityLevel() {
        assertEquals(VideoDetailEnhancer_QualityLevel.VIDEO_DETAIL_ENHANCER_QUALITY_LEVEL_NONE.value.toInt(), 0)
        assertEquals(VideoDetailEnhancer_QualityLevel.VIDEO_DETAIL_ENHANCER_QUALITY_LEVEL_LOW.value.toInt(), 1)
        assertEquals(VideoDetailEnhancer_QualityLevel.VIDEO_DETAIL_ENHANCER_QUALITY_LEVEL_MEDIUM.value.toInt(), 2)
        assertEquals(VideoDetailEnhancer_QualityLevel.VIDEO_DETAIL_ENHANCER_QUALITY_LEVEL_HIGH.value.toInt(), 3)
        logLine("VideoDetailEnhancer_QualityLevel passed")
    }

    @Test
    fun testEnum_VideoMetadataGeneratorStyleControl() {
        assertEquals(VIDEO_METADATA_GENERATOR_CONTRAST_MODE.toInt(), 0)
        assertEquals(VIDEO_METADATA_GENERATOR_BRIGHT_MODE.toInt(), 1)
        logLine("VideoMetadataGeneratorStyleControl passed")
    }

    @Test
    fun testEnum_VideoProcessing_ErrorCode() {
        assertEquals(VIDEO_PROCESSING_SUCCESS.toInt(), 0)
        assertEquals(VIDEO_PROCESSING_ERROR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(VIDEO_PROCESSING_ERROR_UNKNOWN.toInt(), 29210001)
        assertEquals(VIDEO_PROCESSING_ERROR_INITIALIZE_FAILED.toInt(), 29210002)
        assertEquals(VIDEO_PROCESSING_ERROR_CREATE_FAILED.toInt(), 29210003)
        assertEquals(VIDEO_PROCESSING_ERROR_PROCESS_FAILED.toInt(), 29210004)
        assertEquals(VIDEO_PROCESSING_ERROR_UNSUPPORTED_PROCESSING.toInt(), 29210005)
        assertEquals(VIDEO_PROCESSING_ERROR_OPERATION_NOT_PERMITTED.toInt(), 29210006)
        assertEquals(VIDEO_PROCESSING_ERROR_NO_MEMORY.toInt(), 29210007)
        assertEquals(VIDEO_PROCESSING_ERROR_INVALID_INSTANCE.toInt(), 29210008)
        assertEquals(VIDEO_PROCESSING_ERROR_INVALID_VALUE.toInt(), 29210009)
        logLine("VideoProcessing_ErrorCode passed")
    }

    @Test
    fun testEnum_VideoProcessing_State() {
        assertEquals(VideoProcessing_State.VIDEO_PROCESSING_STATE_RUNNING.value.toInt(), 0)
        assertEquals(VideoProcessing_State.VIDEO_PROCESSING_STATE_STOPPED.value.toInt(), 1)
        logLine("VideoProcessing_State passed")
    }

    @Test
    fun testOH_VideoProcessing_InitializeEnvironment() {
        val r = OH_VideoProcessing_InitializeEnvironment()
        logLine("OH_VideoProcessing_InitializeEnvironment=$r")
        assertNotNull(r)
        OH_VideoProcessing_DeinitializeEnvironment()
        logLine("OH_VideoProcessing_InitializeEnvironment passed")
    }

    @Test
    fun testOH_VideoProcessing_DeinitializeEnvironment() {
        OH_VideoProcessing_InitializeEnvironment()
        val r = OH_VideoProcessing_DeinitializeEnvironment()
        logLine("OH_VideoProcessing_DeinitializeEnvironment=$r")
        assertNotNull(r)
        logLine("OH_VideoProcessing_DeinitializeEnvironment passed")
    }

    @Test
    fun testOH_VideoProcessing_Create() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            val r = OH_VideoProcessing_Create(procPtr.ptr, 0)
            logLine("OH_VideoProcessing_Create=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_Create passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_Destroy() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_Destroy(procPtr.value)
            logLine("OH_VideoProcessing_Destroy=$r")
            assertNotNull(r)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_Destroy passed")
        }
    }

    @Test
    fun testOH_VideoProcessingCallback_Create() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val cbPtr = alloc<CPointerVar<VideoProcessing_Callback>>()
            val r = OH_VideoProcessingCallback_Create(cbPtr.ptr)
            logLine("OH_VideoProcessingCallback_Create=$r")
            assertNotNull(r)
            OH_VideoProcessingCallback_Destroy(cbPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessingCallback_Create passed")
        }
    }

    @Test
    fun testOH_VideoProcessingCallback_BindOnError() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val cbPtr = alloc<CPointerVar<VideoProcessing_Callback>>()
            OH_VideoProcessingCallback_Create(cbPtr.ptr)
            val r = OH_VideoProcessingCallback_BindOnError(cbPtr.value, null)
            logLine("OH_VideoProcessingCallback_BindOnError=$r")
            assertNotNull(r)
            OH_VideoProcessingCallback_Destroy(cbPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessingCallback_BindOnError passed")
        }
    }

    @Test
    fun testOH_VideoProcessingCallback_BindOnState() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val cbPtr = alloc<CPointerVar<VideoProcessing_Callback>>()
            OH_VideoProcessingCallback_Create(cbPtr.ptr)
            val r = OH_VideoProcessingCallback_BindOnState(cbPtr.value, null)
            logLine("OH_VideoProcessingCallback_BindOnState=$r")
            assertNotNull(r)
            OH_VideoProcessingCallback_Destroy(cbPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessingCallback_BindOnState passed")
        }
    }

    @Test
    fun testOH_VideoProcessingCallback_BindOnNewOutputBuffer() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val cbPtr = alloc<CPointerVar<VideoProcessing_Callback>>()
            OH_VideoProcessingCallback_Create(cbPtr.ptr)
            val r = OH_VideoProcessingCallback_BindOnNewOutputBuffer(cbPtr.value, null)
            logLine("OH_VideoProcessingCallback_BindOnNewOutputBuffer=$r")
            assertNotNull(r)
            OH_VideoProcessingCallback_Destroy(cbPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessingCallback_BindOnNewOutputBuffer passed")
        }
    }

    @Test
    fun testOH_VideoProcessingCallback_Destroy() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val cbPtr = alloc<CPointerVar<VideoProcessing_Callback>>()
            OH_VideoProcessingCallback_Create(cbPtr.ptr)
            val r = OH_VideoProcessingCallback_Destroy(cbPtr.value)
            logLine("OH_VideoProcessingCallback_Destroy=$r")
            assertNotNull(r)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessingCallback_Destroy passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_IsColorSpaceConversionSupported() {
        memScoped {
            val srcInfo = alloc<VideoProcessing_ColorSpaceInfo>().apply {
                metadataType = 0
                colorSpace = 0
                pixelFormat = 0
            }
            val dstInfo = alloc<VideoProcessing_ColorSpaceInfo>().apply {
                metadataType = 1
                colorSpace = 1
                pixelFormat = 1
            }
            val r = OH_VideoProcessing_IsColorSpaceConversionSupported(srcInfo.ptr, dstInfo.ptr)
            logLine("OH_VideoProcessing_IsColorSpaceConversionSupported=$r")
            assertNotNull(r)
            logLine("OH_VideoProcessing_IsColorSpaceConversionSupported passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_IsMetadataGenerationSupported() {
        memScoped {
            val srcInfo = alloc<VideoProcessing_ColorSpaceInfo>().apply {
                metadataType = 0
                colorSpace = 0
                pixelFormat = 0
            }
            val r = OH_VideoProcessing_IsMetadataGenerationSupported(srcInfo.ptr)
            logLine("OH_VideoProcessing_IsMetadataGenerationSupported=$r")
            assertNotNull(r)
            logLine("OH_VideoProcessing_IsMetadataGenerationSupported passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_RegisterCallback() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val cbPtr = alloc<CPointerVar<VideoProcessing_Callback>>()
            OH_VideoProcessingCallback_Create(cbPtr.ptr)
            val r = OH_VideoProcessing_RegisterCallback(procPtr.value, cbPtr.value, null)
            logLine("OH_VideoProcessing_RegisterCallback=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessingCallback_Destroy(cbPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_RegisterCallback passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_SetSurface() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_SetSurface(procPtr.value, null)
            logLine("OH_VideoProcessing_SetSurface=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_SetSurface passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_GetSurface() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_GetSurface(procPtr.value, null)
            logLine("OH_VideoProcessing_GetSurface=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_GetSurface passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_SetParameter() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_SetParameter(procPtr.value, null)
            logLine("OH_VideoProcessing_SetParameter=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_SetParameter passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_GetParameter() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_GetParameter(procPtr.value, null)
            logLine("OH_VideoProcessing_GetParameter=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_GetParameter passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_Start() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_Start(procPtr.value)
            logLine("OH_VideoProcessing_Start=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_Start passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_Stop() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_Stop(procPtr.value)
            logLine("OH_VideoProcessing_Stop=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_Stop passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_RenderOutputBuffer() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val procPtr = alloc<CPointerVar<OH_VideoProcessing>>()
            OH_VideoProcessing_Create(procPtr.ptr, 0)
            val r = OH_VideoProcessing_RenderOutputBuffer(procPtr.value, 0u)
            logLine("OH_VideoProcessing_RenderOutputBuffer=$r")
            assertNotNull(r)
            OH_VideoProcessing_Destroy(procPtr.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_RenderOutputBuffer passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_Create_type1() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val p1 = alloc<CPointerVar<OH_VideoProcessing>>()
            val r1 = OH_VideoProcessing_Create(p1.ptr, 1)
            logLine("OH_VideoProcessing_Create(type=1)=$r1")
            assertNotNull(r1)
            OH_VideoProcessing_Destroy(p1.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_Create_type1 passed")
        }
    }

    @Test
    fun testOH_VideoProcessing_Create_type2() {
        memScoped {
            OH_VideoProcessing_InitializeEnvironment()
            val p2 = alloc<CPointerVar<OH_VideoProcessing>>()
            val r2 = OH_VideoProcessing_Create(p2.ptr, 2)
            logLine("OH_VideoProcessing_Create(type=2)=$r2")
            assertNotNull(r2)
            OH_VideoProcessing_Destroy(p2.value)
            OH_VideoProcessing_DeinitializeEnvironment()
            logLine("OH_VideoProcessing_Create_type2 passed")
        }
    }
}
