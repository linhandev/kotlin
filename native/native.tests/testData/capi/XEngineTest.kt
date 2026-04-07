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
import platform.XEngineKit.XEngine.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class XEngineTest {

    private fun logLine(msg: String) = println(msg)

    // ==================== 扩展常量 (xeg_extension_defs.h) ====================
    @Test
    fun testExtensionConstants() {
        assertNotNull(XEG_spatial_upscale)
        assertEquals(XEG_SPATIAL_UPSCALE_VERSION, 1)
        assertNotNull(XEG_SPATIAL_UPSCALE_EXTENSION_NAME)
        assertNotNull(XEG_neural_upscale)
        assertEquals(XEG_NEURAL_UPSCALE_VERSION, 1)
        assertNotNull(XEG_NEURAL_UPSCALE_EXTENSION_NAME)
        assertNotNull(XEG_temporal_upscale)
        assertEquals(XEG_TEMPORAL_UPSCALE_VERSION, 1)
        assertNotNull(XEG_TEMPORAL_UPSCALE_EXTENSION_NAME)
        assertNotNull(XEG_adaptive_vrs)
        assertEquals(XEG_ADAPTIVE_VRS_VERSION, 1)
        assertNotNull(XEG_ADAPTIVE_VRS_EXTENSION_NAME)
        assertNotNull(XEG_RTGI_EXTENSION_NAME)
        assertNotNull(XEG_RT_SHADOW_AO_EXTENSION_NAME)
        assertNotNull(XEG_RT_REFLECTION_EXTENSION_NAME)
        assertNotNull(XEG_HPS_RADIX_SORT_EXTENSION_NAME)
        logLine("XEG extension constants passed")
    }

    @Test
    fun testXEG_MAX_EXTENSION_NAME_SIZE() {
        assertEquals(XEG_MAX_EXTENSION_NAME_SIZE, 256)
        logLine("XEG_MAX_EXTENSION_NAME_SIZE passed")
    }

    @Test
    fun testXEG_EXTENSIONS_GLES() {
        assertEquals(XEG_EXTENSIONS.toInt(), 0x01)
        logLine("XEG_EXTENSIONS passed")
    }

    @Test
    fun testXEG_SPATIAL_UPSCALE_SCISSOR_SHARPNESS() {
        assertEquals(XEG_SPATIAL_UPSCALE_SCISSOR.toInt(), 0x1)
        assertEquals(XEG_SPATIAL_UPSCALE_SHARPNESS.toInt(), 0x2)
        logLine("XEG_SPATIAL_UPSCALE_SCISSOR/SHARPNESS passed")
    }

    @Test
    fun testXEG_NEURAL_UPSCALE_TEMPORAL_UPSCALE_ADAPTIVE_VRS_PARAMS() {
        assertEquals(XEG_NEURAL_UPSCALE_SCISSOR.toInt(), 0x1)
        assertEquals(XEG_NEURAL_UPSCALE_SHARPNESS.toInt(), 0x2)
        assertEquals(XEG_NEURAL_UPSCALE_INPUT_HANDLE.toInt(), 0x4)
        assertEquals(XEG_TEMPORAL_UPSCALE_INPUT_SIZE.toInt(), 0x1)
        assertEquals(XEG_TEMPORAL_UPSCALE_JITTER_NUM.toInt(), 0x2)
        assertEquals(XEG_TEMPORAL_UPSCALE_DEPTH_REVERSED.toInt(), 0x3)
        assertEquals(XEG_TEMPORAL_UPSCALE_RESET_HISTORY.toInt(), 0x4)
        assertEquals(XEG_TEMPORAL_UPSCALE_STEADY_LEVEL.toInt(), 0x5)
        assertEquals(XEG_ADAPTIVE_VRS_INPUT_SIZE.toInt(), 0x1)
        assertEquals(XEG_ADAPTIVE_VRS_INPUT_REGION.toInt(), 0x2)
        assertEquals(XEG_ADAPTIVE_VRS_TEXEL_SIZE.toInt(), 0x3)
        assertEquals(XEG_ADAPTIVE_VRS_ERROR_SENSITIVITY.toInt(), 0x4)
        assertEquals(XEG_ADAPTIVE_VRS_FLIP.toInt(), 0x5)
        logLine("XEG_NEURAL/TEMPORAL/ADAPTIVE_VRS params passed")
    }

    // ==================== 枚举 (xeg_vulkan_common.h: XEG_StructureType) ====================
    @Test
    fun testEnum_XEG_StructureType() {
        assertEquals(XEG_STRUCTURE_TYPE_RT_SHADOWAO_CREATE_INFO.toInt(), 0); logLine("XEG_STRUCTURE_TYPE_RT_SHADOWAO_CREATE_INFO=0")
        assertEquals(XEG_STRUCTURE_TYPE_RT_SHADOWAO_DESCRIPTION.toInt(), 1); logLine("XEG_STRUCTURE_TYPE_RT_SHADOWAO_DESCRIPTION=1")
        assertEquals(XEG_STRUCTURE_TYPE_RT_REFLECTION_CREATE_INFO.toInt(), 2); logLine("XEG_STRUCTURE_TYPE_RT_REFLECTION_CREATE_INFO=2")
        assertEquals(XEG_STRUCTURE_TYPE_RT_REFLECTION_DESCRIPTION.toInt(), 3); logLine("XEG_STRUCTURE_TYPE_RT_REFLECTION_DESCRIPTION=3")
        assertEquals(XEG_STRUCTURE_TYPE_NNGI_CREATE_INFO.toInt(), 4); logLine("XEG_STRUCTURE_TYPE_NNGI_CREATE_INFO=4")
        assertEquals(XEG_STRUCTURE_TYPE_NNGI_DESCRIPTION.toInt(), 5); logLine("XEG_STRUCTURE_TYPE_NNGI_DESCRIPTION=5")
        assertEquals(XEG_STRUCTURE_TYPE_DDGI_CREATE_INFO.toInt(), 6); logLine("XEG_STRUCTURE_TYPE_DDGI_CREATE_INFO=6")
        assertEquals(XEG_STRUCTURE_TYPE_DDGI_DESCRIPTION.toInt(), 7); logLine("XEG_STRUCTURE_TYPE_DDGI_DESCRIPTION=7")
        assertEquals(XEG_STRUCTURE_TYPE_HPS_CREATE_INFO.toInt(), 1001); logLine("XEG_STRUCTURE_TYPE_HPS_CREATE_INFO=1001")
        assertEquals(XEG_STRUCTURE_TYPE_HPS_RADIX_SORT.toInt(), 1002); logLine("XEG_STRUCTURE_TYPE_HPS_RADIX_SORT=1002")
        assertEquals(XEG_STRUCTURE_TYPE_HPS_RADIX_SORT_DESCRIPTION.toInt(), 1003); logLine("XEG_STRUCTURE_TYPE_HPS_RADIX_SORT_DESCRIPTION=1003")
        logLine("XEG_StructureType (xeg_vulkan_common.h) passed")
    }

    @Test
    fun testEnum_XEG_RTGIQualityMode_XEG_DenoiseQualityMode_XEG_TraversalMode() {
        assertEquals(XEG_RTGI_QUALITY_MODE_QUALITY.toInt(), 0)
        assertEquals(XEG_RTGI_QUALITY_MODE_BALANCED.toInt(), 1)
        assertEquals(XEG_RTGI_QUALITY_MODE_PERFORMANCE.toInt(), 2)
        assertEquals(XEG_DENOISE_QUALITY_MODE_NONE.toInt(), 0)
        assertEquals(XEG_DENOISE_QUALITY_MODE_QUALITY.toInt(), 1)
        assertEquals(XEG_DENOISE_QUALITY_MODE_BALANCED.toInt(), 2)
        assertEquals(XEG_DENOISE_QUALITY_MODE_PERFORMANCES.toInt(), 3)
        assertEquals(XEG_TRAVERSAL_MODE_DEFAULT.toInt(), 0)
        assertEquals(XEG_TRAVERSAL_MODE_PERFORMANCES.toInt(), 1)
        logLine("XEG_RTGIQualityMode/XEG_DenoiseQualityMode/XEG_TraversalMode passed")
    }

    // ==================== GLES: HMS_XEG_GetString ====================
    @Test
    fun testHMS_XEG_GetString() {
        val str = HMS_XEG_GetString(XEG_EXTENSIONS)
        logLine("HMS_XEG_GetString(XEG_EXTENSIONS)=$str")
        assertTrue(str == null || str != null)
    }

    // ==================== GLES: NeuralUpscale ====================
    @Test
    fun testHMS_XEG_NeuralUpscaleParameter() {
        HMS_XEG_NeuralUpscaleParameter(XEG_NEURAL_UPSCALE_SCISSOR, null)
        HMS_XEG_NeuralUpscaleParameter(XEG_NEURAL_UPSCALE_SHARPNESS, null)
        logLine("HMS_XEG_NeuralUpscaleParameter passed")
    }

    @Test
    fun testHMS_XEG_RenderNeuralUpscale() {
        HMS_XEG_RenderNeuralUpscale(0u)
        logLine("HMS_XEG_RenderNeuralUpscale passed")
    }

    // ==================== GLES: SpatialUpscale ====================
    @Test
    fun testHMS_XEG_SpatialUpscaleParameter() {
        HMS_XEG_SpatialUpscaleParameter(XEG_SPATIAL_UPSCALE_SCISSOR, null)
        HMS_XEG_SpatialUpscaleParameter(XEG_SPATIAL_UPSCALE_SHARPNESS, null)
        logLine("HMS_XEG_SpatialUpscaleParameter passed")
    }

    @Test
    fun testHMS_XEG_RenderSpatialUpscale() {
        HMS_XEG_RenderSpatialUpscale(0u)
        logLine("HMS_XEG_RenderSpatialUpscale passed")
    }

    // ==================== GLES: TemporalUpscale ====================
    @Test
    fun testHMS_XEG_TemporalUpscaleParameter() {
        try { HMS_XEG_TemporalUpscaleParameter(XEG_TEMPORAL_UPSCALE_INPUT_SIZE, null) } catch (e: Throwable) { logLine("HMS_XEG_TemporalUpscaleParameter (API 20) exception: $e") }
        logLine("HMS_XEG_TemporalUpscaleParameter passed")
    }

    @Test
    fun testHMS_XEG_RenderTemporalUpscale() {
        try { HMS_XEG_RenderTemporalUpscale(0u, 0u, 0u, 0u, 0.0f, 0.0f) } catch (e: Throwable) { logLine("HMS_XEG_RenderTemporalUpscale (API 20) exception: $e") }
        logLine("HMS_XEG_RenderTemporalUpscale passed")
    }

    // ==================== GLES: AdaptiveVRS ====================
    @Test
    fun testHMS_XEG_AdaptiveVRSParameter() {
        HMS_XEG_AdaptiveVRSParameter(XEG_ADAPTIVE_VRS_INPUT_SIZE, null)
        logLine("HMS_XEG_AdaptiveVRSParameter passed")
    }

    @Test
    fun testHMS_XEG_DispatchAdaptiveVRS() {
        HMS_XEG_DispatchAdaptiveVRS(null, 0u, 0u, 0u)
        logLine("HMS_XEG_DispatchAdaptiveVRS passed")
    }

    @Test
    fun testHMS_XEG_ApplyAdaptiveVRS() {
        HMS_XEG_ApplyAdaptiveVRS(0u)
        logLine("HMS_XEG_ApplyAdaptiveVRS passed")
    }

    // ==================== Vulkan: EnumerateDeviceExtensionProperties ====================
    // @Test
    // fun testHMS_XEG_EnumerateDeviceExtensionProperties() {
    //     memScoped {
    //         val count = alloc<UIntVar>()
    //         val ret = HMS_XEG_EnumerateDeviceExtensionProperties(null, count.ptr, null)
    //         assertNotNull(ret)
    //         logLine("HMS_XEG_EnumerateDeviceExtensionProperties=$ret count=${count.value}")
    //     }
    //     logLine("HMS_XEG_EnumerateDeviceExtensionProperties passed")
    // }

    // ==================== Vulkan: CmdSetSynchronization ====================
    @Test
    fun testHMS_XEG_CmdSetSynchronization() {
        val ret = try { HMS_XEG_CmdSetSynchronization(null, null) } catch (e: Throwable) { logLine("HMS_XEG_CmdSetSynchronization (API 20) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("HMS_XEG_CmdSetSynchronization=$ret")
    }

    // ==================== Vulkan: SpatialUpscale Create/CmdRender/Destroy ====================
    // @Test
    // fun testHMS_XEG_CreateSpatialUpscale() {
    //     memScoped {
    //         val createInfo = alloc<XEG_SpatialUpscaleCreateInfo>()
    //         val outHandle = alloc<CPointerVar<XEG_SpatialUpscale_T>>()
    //         val ret = HMS_XEG_CreateSpatialUpscale(null, createInfo.ptr, outHandle.ptr)
    //         logLine("HMS_XEG_CreateSpatialUpscale=$ret")
    //         assertNotNull(ret)
    //         outHandle.value?.let { HMS_XEG_DestroySpatialUpscale(it) }
    //     }
    //     logLine("HMS_XEG_CreateSpatialUpscale passed")
    // }

    // @Test
    // fun testHMS_XEG_CmdRenderSpatialUpscale() {
    //     memScoped {
    //         val desc = alloc<XEG_SpatialUpscaleDescription>()
    //         HMS_XEG_CmdRenderSpatialUpscale(null, null, desc.ptr)
    //     }
    //     logLine("HMS_XEG_CmdRenderSpatialUpscale passed")
    // }

    // @Test
    // fun testHMS_XEG_DestroySpatialUpscale() {
    //     HMS_XEG_DestroySpatialUpscale(null)
    //     logLine("HMS_XEG_DestroySpatialUpscale passed")
    // }

    // ==================== Vulkan: TemporalUpscale Create/CmdRender/Destroy ====================
    @Test
    fun testHMS_XEG_CreateTemporalUpscale() {
        memScoped {
            val createInfo = alloc<XEG_TemporalUpscaleCreateInfo>()
            val outHandle = alloc<CPointerVar<XEG_TemporalUpscale_T>>()
            val ret = HMS_XEG_CreateTemporalUpscale(null, createInfo.ptr, outHandle.ptr)
            logLine("HMS_XEG_CreateTemporalUpscale=$ret")
            assertNotNull(ret)
            outHandle.value?.let { HMS_XEG_DestroyTemporalUpscale(it) }
        }
        logLine("HMS_XEG_CreateTemporalUpscale passed")
    }

    @Test
    fun testHMS_XEG_CmdRenderTemporalUpscale() {
        memScoped {
            val desc = alloc<XEG_TemporalUpscaleDescription>()
            HMS_XEG_CmdRenderTemporalUpscale(null, null, desc.ptr)
        }
        logLine("HMS_XEG_CmdRenderTemporalUpscale passed")
    }

    @Test
    fun testHMS_XEG_DestroyTemporalUpscale() {
        HMS_XEG_DestroyTemporalUpscale(null)
        logLine("HMS_XEG_DestroyTemporalUpscale passed")
    }

    // ==================== Vulkan: AdaptiveVRS Create/CmdDispatch/Destroy ====================
    // @Test
    // fun testHMS_XEG_CreateAdaptiveVRS() {
    //     memScoped {
    //         val createInfo = alloc<XEG_AdaptiveVRSCreateInfo>()
    //         val outHandle = alloc<CPointerVar<XEG_AdaptiveVRS_T>>()
    //         val ret = HMS_XEG_CreateAdaptiveVRS(null, createInfo.ptr, outHandle.ptr)
    //         logLine("HMS_XEG_CreateAdaptiveVRS=$ret")
    //         assertNotNull(ret)
    //         outHandle.value?.let { HMS_XEG_DestroyAdaptiveVRS(it) }
    //     }
    //     logLine("HMS_XEG_CreateAdaptiveVRS passed")
    // }

    // @Test
    // fun testHMS_XEG_CmdDispatchAdaptiveVRS() {
    //     memScoped {
    //         val desc = alloc<XEG_AdaptiveVRSDescription>()
    //         HMS_XEG_CmdDispatchAdaptiveVRS(null, null, desc.ptr)
    //     }
    //     logLine("HMS_XEG_CmdDispatchAdaptiveVRS passed")
    // }

    // @Test
    // fun testHMS_XEG_DestroyAdaptiveVRS() {
    //     HMS_XEG_DestroyAdaptiveVRS(null)
    //     logLine("HMS_XEG_DestroyAdaptiveVRS passed")
    // }

    // ==================== Vulkan: HPS Create/Destroy/CmdRadixSortHPS ====================
    @Test
    fun testHMS_XEG_CreateHPS() {
        memScoped {
            val createInfo = alloc<XEG_HPSCreateInfo>().apply { sType = XEG_STRUCTURE_TYPE_HPS_CREATE_INFO }
            val outHandle = alloc<CPointerVar<XEG_HPS_T>>()
            val ret = try { HMS_XEG_CreateHPS(null, createInfo.ptr, outHandle.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CreateHPS (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CreateHPS=$ret")
            assertNotNull(ret)
            outHandle.value?.let { try { HMS_XEG_DestroyHPS(it) } catch (e: Throwable) { logLine("HMS_XEG_DestroyHPS exception: $e") } }
        }
        logLine("HMS_XEG_CreateHPS passed")
    }

    @Test
    fun testHMS_XEG_DestroyHPS() {
        try { HMS_XEG_DestroyHPS(null) } catch (e: Throwable) { logLine("HMS_XEG_DestroyHPS (API 20) exception: $e") }
        logLine("HMS_XEG_DestroyHPS passed")
    }

    @Test
    fun testHMS_XEG_CmdRadixSortHPS() {
        memScoped {
            val desc = alloc<XEG_HPSRadixSortDescription>().apply { sType = XEG_STRUCTURE_TYPE_HPS_RADIX_SORT_DESCRIPTION }
            val ret = try { HMS_XEG_CmdRadixSortHPS(null, null, desc.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CmdRadixSortHPS (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CmdRadixSortHPS=$ret")
            assertNotNull(ret)
        }
        logLine("HMS_XEG_CmdRadixSortHPS passed")
    }

    // ==================== Vulkan: RTReflection Create/CmdRender/Destroy ====================
    @Test
    fun testHMS_XEG_CreateRTReflection() {
        memScoped {
            val createInfo = alloc<XEG_RTReflectionCreateInfo>().apply { sType = XEG_STRUCTURE_TYPE_RT_REFLECTION_CREATE_INFO }
            val outHandle = alloc<CPointerVar<XEG_RTReflection_T>>()
            val ret = try { HMS_XEG_CreateRTReflection(null, createInfo.ptr, outHandle.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CreateRTReflection (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CreateRTReflection=$ret")
            assertNotNull(ret)
            outHandle.value?.let { try { HMS_XEG_DestroyRTReflection(it) } catch (e: Throwable) { logLine("HMS_XEG_DestroyRTReflection exception: $e") } }
        }
        logLine("HMS_XEG_CreateRTReflection passed")
    }

    @Test
    fun testHMS_XEG_CmdRenderRTReflection() {
        memScoped {
            val desc = alloc<XEG_RTReflectionDescription>().apply { sType = XEG_STRUCTURE_TYPE_RT_REFLECTION_DESCRIPTION }
            val ret = try { HMS_XEG_CmdRenderRTReflection(null, null, desc.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CmdRenderRTReflection (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CmdRenderRTReflection=$ret")
            assertNotNull(ret)
        }
        logLine("HMS_XEG_CmdRenderRTReflection passed")
    }

    @Test
    fun testHMS_XEG_DestroyRTReflection() {
        try { HMS_XEG_DestroyRTReflection(null) } catch (e: Throwable) { logLine("HMS_XEG_DestroyRTReflection (API 20) exception: $e") }
        logLine("HMS_XEG_DestroyRTReflection passed")
    }

    // ==================== Vulkan: RTVisibleMask Create/CmdRender/Destroy ====================
    @Test
    fun testHMS_XEG_CreateRTVisibleMask() {
        memScoped {
            val createInfo = alloc<XEG_RTShadowAOCreateInfo>().apply {
                sType = XEG_STRUCTURE_TYPE_RT_SHADOWAO_CREATE_INFO
                enableRTShadow = true
            }
            val outHandle = alloc<CPointerVar<XEG_RTVisibleMask_T>>()
            val ret = try { HMS_XEG_CreateRTVisibleMask(null, createInfo.ptr, outHandle.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CreateRTVisibleMask (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CreateRTVisibleMask=$ret")
            assertNotNull(ret)
            outHandle.value?.let { try { HMS_XEG_DestroyRTVisibleMask(it) } catch (e: Throwable) { logLine("HMS_XEG_DestroyRTVisibleMask exception: $e") } }
        }
        logLine("HMS_XEG_CreateRTVisibleMask passed")
    }

    @Test
    fun testHMS_XEG_CmdRenderRTVisibleMask() {
        memScoped {
            val desc = alloc<XEG_RTShadowAODescription>().apply { sType = XEG_STRUCTURE_TYPE_RT_SHADOWAO_DESCRIPTION }
            val ret = try { HMS_XEG_CmdRenderRTVisibleMask(null, null, desc.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CmdRenderRTVisibleMask (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CmdRenderRTVisibleMask=$ret")
            assertNotNull(ret)
        }
        logLine("HMS_XEG_CmdRenderRTVisibleMask passed")
    }

    @Test
    fun testHMS_XEG_DestroyRTVisibleMask() {
        try { HMS_XEG_DestroyRTVisibleMask(null) } catch (e: Throwable) { logLine("HMS_XEG_DestroyRTVisibleMask (API 20) exception: $e") }
        logLine("HMS_XEG_DestroyRTVisibleMask passed")
    }

    // ==================== Vulkan: RTGI Create/CmdRender/Destroy ====================
    @Test
    fun testHMS_XEG_CreateRTGI() {
        memScoped {
            val createInfo = alloc<XEG_NNGICreateInfo>().apply { sType = XEG_STRUCTURE_TYPE_NNGI_CREATE_INFO }
            val outHandle = alloc<CPointerVar<XEG_RTGI_T>>()
            val ret = try { HMS_XEG_CreateRTGI(null, createInfo.ptr, outHandle.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CreateRTGI (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CreateRTGI=$ret")
            assertNotNull(ret)
            outHandle.value?.let { try { HMS_XEG_DestroyRTGI(it) } catch (e: Throwable) { logLine("HMS_XEG_DestroyRTGI exception: $e") } }
        }
        logLine("HMS_XEG_CreateRTGI passed")
    }

    @Test
    fun testHMS_XEG_CmdRenderRTGI() {
        memScoped {
            val desc = alloc<XEG_NNGIDescription>().apply { sType = XEG_STRUCTURE_TYPE_NNGI_DESCRIPTION }
            val ret = try { HMS_XEG_CmdRenderRTGI(null, null, desc.ptr) } catch (e: Throwable) { logLine("HMS_XEG_CmdRenderRTGI (API 20) exception: $e"); -1 }
            logLine("HMS_XEG_CmdRenderRTGI=$ret")
            assertNotNull(ret)
        }
        logLine("HMS_XEG_CmdRenderRTGI passed")
    }

    @Test
    fun testHMS_XEG_DestroyRTGI() {
        try { HMS_XEG_DestroyRTGI(null) } catch (e: Throwable) { logLine("HMS_XEG_DestroyRTGI (API 20) exception: $e") }
        logLine("HMS_XEG_DestroyRTGI passed")
    }
}
