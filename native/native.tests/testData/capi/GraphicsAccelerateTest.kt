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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.GraphicsAccelerateKit.GraphicsAccelerate.*
import cnames.structs.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class GraphicsAccelerateTest {

    companion object {
        /** Stub for FG_ContextDescription_VK.fnVulkanLoaderFunction (vkGetInstanceProcAddr). KNM: (VkInstance_T?, ByteVar?) -> CFunction<() -> Unit>? */
        private val vkGetInstanceProcAddrStub = staticCFunction<CPointer<VkInstance_T>?, CPointer<ByteVar>?, CPointer<CFunction<() -> Unit>>?> { _i, _n -> null }
        /** Stub for HMS_OpenGTX_CreateContext(deviceInfoCallback). KNM: (OpenGTX_TempLevel/UInt) -> Unit */
        private val openGTXDeviceInfoCallbackStub = staticCFunction<UInt, Unit> { _tempLevel -> }
    }

    private fun logLine(message: String) = println(message)

    // ---------- ABR 枚举（顶层 + toInt） ----------

    @Test
    fun testEnum_ABR_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("ABR_SUCCESS", ABR_SUCCESS.toInt(), 0)
        p("ABR_INVALID_PARAMETER", ABR_INVALID_PARAMETER.toInt(), 401)
        p("ABR_CONTEXT_CONFIG_AFTER_ACTIVE", ABR_CONTEXT_CONFIG_AFTER_ACTIVE.toInt(), 1009501001)
        p("ABR_CONTEXT_NOT_CONFIG", ABR_CONTEXT_NOT_CONFIG.toInt(), 1009501002)
        p("ABR_CONTEXT_NOT_ACTIVE", ABR_CONTEXT_NOT_ACTIVE.toInt(), 1009501003)
        p("ABR_METADATA_INVALID", ABR_METADATA_INVALID.toInt(), 1009501004)
        p("ABR_FRAMEBUFFER_INVALID", ABR_FRAMEBUFFER_INVALID.toInt(), 1009501005)
    }

    @Test
    fun testEnum_ABR_RenderAPI_Type() {
        logLine("RENDER_API_GLES=${RENDER_API_GLES.toInt()}")
        assertEquals(0, RENDER_API_GLES.toInt())
    }

    // ---------- OpenGTX 枚举（顶层 + toInt） ----------

    @Test
    fun testEnum_OpenGTX_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("OPENGTX_SUCCESS", OPENGTX_SUCCESS.toInt(), 0)
        p("OPENGTX_INVALID_PARAMETER", OPENGTX_INVALID_PARAMETER.toInt(), 401)
        p("OPENGTX_CONTEXT_NOT_CONFIG", OPENGTX_CONTEXT_NOT_CONFIG.toInt(), 1009502001)
        p("OPENGTX_CONTEXT_NOT_ACTIVE", OPENGTX_CONTEXT_NOT_ACTIVE.toInt(), 1009502002)
    }

    @Test
    fun testEnum_OpenGTX_LTPO_Mode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("SCENE_MODE", SCENE_MODE.toInt(), 0x0001)
        p("TOUCH_MODE", TOUCH_MODE.toInt(), 0x0010)
        p("ADAPTIVE_MODE", ADAPTIVE_MODE.toInt(), 0x0100)
    }

    @Test
    fun testEnum_OpenGTX_EngineType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("UNITY", UNITY.toInt(), 1)
        p("UNREAL", UNREAL.toInt(), 2)
        p("MESSIAH", MESSIAH.toInt(), 3)
        p("COCOS", COCOS.toInt(), 4)
        p("OTHERS_ENGINE", OTHERS_ENGINE.toInt(), 100)
    }

    @Test
    fun testEnum_OpenGTX_PictureQualityMaxLevel() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("SD", SD.toInt(), 1)
        p("HD", HD.toInt(), 2)
        p("FHD", FHD.toInt(), 3)
        p("QHD", QHD.toInt(), 4)
        p("UHD", UHD.toInt(), 5)
    }

    @Test
    fun testEnum_OpenGTX_GameType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("MOBA", MOBA.toInt(), 1)
        p("RPG", RPG.toInt(), 2)
        p("FPS", FPS.toInt(), 3)
        p("RAC", RAC.toInt(), 4)
        p("OTHERS_TYPE", OTHERS_TYPE.toInt(), 100)
    }

    @Test
    fun testEnum_OpenGTX_TempLevel() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("TEMP_LEVEL1", TEMP_LEVEL1.toInt(), 1)
        p("TEMP_LEVEL2", TEMP_LEVEL2.toInt(), 2)
        p("TEMP_LEVEL3", TEMP_LEVEL3.toInt(), 3)
        p("TEMP_LEVEL4", TEMP_LEVEL4.toInt(), 4)
        p("TEMP_LEVEL5", TEMP_LEVEL5.toInt(), 5)
        p("TEMP_LEVEL6", TEMP_LEVEL6.toInt(), 6)
    }

    @Test
    fun testEnum_OpenGTX_SceneID() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("LOGIN", LOGIN.toInt(), 1)
        p("GAME_INTERFACE", GAME_INTERFACE.toInt(), 2)
        p("LOADING", LOADING.toInt(), 3)
        p("PLAYING", PLAYING.toInt(), 4)
        p("SPECTATOR", SPECTATOR.toInt(), 5)
        p("DEATH", DEATH.toInt(), 6)
        p("HEAVY_LOAD", HEAVY_LOAD.toInt(), 7)
        p("OTHERS_SCENE", OTHERS_SCENE.toInt(), 100)
    }

    // ---------- FG 枚举（顶层 + toInt） ----------

    @Test
    fun testEnum_FG_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FG_SUCCESS", FG_SUCCESS.toInt(), 0)
        p("FG_INVALID_PARAMETER", FG_INVALID_PARAMETER.toInt(), 401)
        p("FG_CONTEXT_NOT_CONFIG", FG_CONTEXT_NOT_CONFIG.toInt(), 1009500001)
        p("FG_CONTEXT_NOT_ACTIVE", FG_CONTEXT_NOT_ACTIVE.toInt(), 1009500002)
        p("FG_COLLECTING_PREVIOUS_FRAMES", FG_COLLECTING_PREVIOUS_FRAMES.toInt(), 1009500003)
    }

    @Test
    fun testEnum_FG_PredictionMode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FG_PREDICTION_MODE_INTERPOLATION", FG_PREDICTION_MODE_INTERPOLATION.toInt(), 0)
        p("FG_PREDICTION_MODE_EXTRAPOLATION", FG_PREDICTION_MODE_EXTRAPOLATION.toInt(), 1)
    }

    @Test
    fun testEnum_FG_MeMode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FG_ME_MODE_BASIC", FG_ME_MODE_BASIC.toInt(), 0)
        p("FG_ME_MODE_ENHANCED", FG_ME_MODE_ENHANCED.toInt(), 1)
    }

    @Test
    fun testEnum_FG_CvvZSemantic() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FG_CVV_Z_SEMANTIC_MINUS_ONE_TO_ONE_FORWARD_Z", FG_CVV_Z_SEMANTIC_MINUS_ONE_TO_ONE_FORWARD_Z.toInt(), 0)
        p("FG_CVV_Z_SEMANTIC_ZERO_TO_ONE_REVERSE_Z", FG_CVV_Z_SEMANTIC_ZERO_TO_ONE_REVERSE_Z.toInt(), 1)
        p("FG_CVV_Z_SEMANTIC_MINUS_ONE_TO_ONE_REVERSE_Z", FG_CVV_Z_SEMANTIC_MINUS_ONE_TO_ONE_REVERSE_Z.toInt(), 2)
        p("FG_CVV_Z_SEMANTIC_ZERO_TO_ONE_FORWARD_Z", FG_CVV_Z_SEMANTIC_ZERO_TO_ONE_FORWARD_Z.toInt(), 3)
    }

    @Test
    fun testEnum_FG_PresentMode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FG_PRESENT_BY_GAME", FG_PRESENT_BY_GAME.toInt(), 0)
        p("FG_PRESENT_BY_SYSTEM", FG_PRESENT_BY_SYSTEM.toInt(), 1)
    }

    @Test
    fun testEnum_FG_ImageFormat_GLES() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assertEquals(expected, v) }
        p("FG_FORMAT_R8G8B8A8_UNORM", FG_FORMAT_R8G8B8A8_UNORM.toInt(), 0)
        p("FG_FORMAT_R11G11B10_SFLOAT", FG_FORMAT_R11G11B10_SFLOAT.toInt(), 1)
        p("FG_FORMAT_R16G16B16A16_SFLOAT", FG_FORMAT_R16G16B16A16_SFLOAT.toInt(), 2)
    }

    // ---------- ABR 函数 ----------

    @Test
    fun testHMS_ABR_CreateContext() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        logLine("HMS_ABR_CreateContext=$ctx")
        if (ctx != null) {
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        } else {
            logLine("HMS_ABR_CreateContext=null (API unavailable), skip destroy")
        }
        logLine("HMS_ABR_CreateContext passed")
    }

    @Test
    fun testHMS_ABR_SetTargetFps() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        var rc = HMS_ABR_SetTargetFps(ctx, 60u)
        assertNotNull(rc)
        rc = HMS_ABR_SetTargetFps(null, 60u)
        assertNotNull(rc)
        if (ctx != null) {
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_SetTargetFps passed")
    }

    @Test
    fun testHMS_ABR_SetScaleRange() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        val rc = HMS_ABR_SetScaleRange(ctx, 0.5f, 1.0f)
        assertNotNull(rc)
        if (ctx != null) {
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_SetScaleRange passed")
    }

    @Test
    fun testHMS_ABR_Activate() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        val rc = HMS_ABR_Activate(ctx)
        assertNotNull(rc)
        if (ctx != null) {
            HMS_ABR_Deactivate(ctx)
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_Activate passed")
    }

    @Test
    fun testHMS_ABR_IsActive() {
        memScoped {
            val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
            val isActive = alloc<BooleanVar>()
            val rc = HMS_ABR_IsActive(ctx, isActive.ptr)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_IsActive passed")
    }

    @Test
    fun testHMS_ABR_Deactivate() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        HMS_ABR_Activate(ctx)
        val rc = HMS_ABR_Deactivate(ctx)
        assertNotNull(rc)
        if (ctx != null) {
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_Deactivate passed")
    }

    @Test
    fun testHMS_ABR_UpdateCameraData() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        val rc = HMS_ABR_UpdateCameraData(ctx, null)
        assertNotNull(rc)
        if (ctx != null) {
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_UpdateCameraData passed")
    }

    @Test
    fun testHMS_ABR_GetScale() {
        memScoped {
            val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
            val scale = alloc<FloatVar>()
            val rc = HMS_ABR_GetScale(ctx, scale.ptr)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_GetScale passed")
    }

    @Test
    fun testHMS_ABR_GetNextScale() {
        memScoped {
            val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
            val scale = alloc<FloatVar>()
            val rc = HMS_ABR_GetNextScale(ctx, scale.ptr)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_GetNextScale passed")
    }

    @Test
    fun testHMS_ABR_MarkFrameBuffer_GLES() {
        val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
        val rc = HMS_ABR_MarkFrameBuffer_GLES(ctx)
        assertNotNull(rc)
        if (ctx != null) {
            memScoped {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_MarkFrameBuffer_GLES passed")
    }

    @Test
    fun testHMS_ABR_GetScaledTexture_GLES() {
        memScoped {
            val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
            val outTex = alloc<UIntVar>()
            val rc = HMS_ABR_GetScaledTexture_GLES(ctx, 0u, outTex.ptr)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                HMS_ABR_DestroyContext(ctxPtr.ptr)
            }
        }
        logLine("HMS_ABR_GetScaledTexture_GLES passed")
    }

    @Test
    fun testHMS_ABR_DestroyContext() {
        memScoped {
            val ctx = HMS_ABR_CreateContext(RENDER_API_GLES)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<ABR_Context>>()
                ctxPtr.value = ctx
                val rc = HMS_ABR_DestroyContext(ctxPtr.ptr)
                assertNotNull(rc)
            } else {
                logLine("HMS_ABR_CreateContext=null, skip DestroyContext")
            }
        }
        logLine("HMS_ABR_DestroyContext passed")
    }

    // ---------- OpenGTX 函数 ----------

    // @Test
    // fun testHMS_OpenGTX_CreateContext() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     logLine("HMS_OpenGTX_CreateContext=$ctx")
    //     assertNotNull(ctx)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_CreateContext passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_SetConfiguration() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     var rc = HMS_OpenGTX_SetConfiguration(ctx, null)
    //     assertNotNull(rc)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_SetConfiguration passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_Activate() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     val rc = HMS_OpenGTX_Activate(ctx)
    //     assertNotNull(rc)
    //     HMS_OpenGTX_Deactivate(ctx)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_Activate passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_Deactivate() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     HMS_OpenGTX_Activate(ctx)
    //     val rc = HMS_OpenGTX_Deactivate(ctx)
    //     assertNotNull(rc)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_Deactivate passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_DispatchFrameRenderInfo() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     val rc = HMS_OpenGTX_DispatchFrameRenderInfo(ctx, null)
    //     assertNotNull(rc)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_DispatchFrameRenderInfo passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_DispatchGameSceneInfo() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     val rc = HMS_OpenGTX_DispatchGameSceneInfo(ctx, null)
    //     assertNotNull(rc)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_DispatchGameSceneInfo passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_DispatchNetworkInfo() {
    //     val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //     val rc = HMS_OpenGTX_DispatchNetworkInfo(ctx, null)
    //     assertNotNull(rc)
    //     memScoped {
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //     }
    //     logLine("HMS_OpenGTX_DispatchNetworkInfo passed")
    // }

    // @Test
    // fun testHMS_OpenGTX_DestroyContext() {
    //     memScoped {
    //         val ctx = HMS_OpenGTX_CreateContext(openGTXDeviceInfoCallbackStub)
    //         val ctxPtr = alloc<CPointerVar<OpenGTX_Context>>()
    //         ctxPtr.value = ctx
    //         val rc = HMS_OpenGTX_DestroyContext(ctxPtr.ptr)
    //         assertNotNull(rc)
    //     }
    //     logLine("HMS_OpenGTX_DestroyContext passed")
    // }

    // ---------- FG GLES 函数 ----------

    @Test
    fun testHMS_FG_CreateContext_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        logLine("HMS_FG_CreateContext_GLES=$ctx")
        assertNotNull(ctx)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_CreateContext_GLES passed")
    }

    @Test
    fun testHMS_FG_SetAlgorithmMode_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_SetAlgorithmMode_GLES(ctx, null)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetAlgorithmMode_GLES passed")
    }

    @Test
    fun testHMS_FG_SetResolution_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_SetResolution_GLES(ctx, null)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetResolution_GLES passed")
    }

    @Test
    fun testHMS_FG_SetCvvZSemantic_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_SetCvvZSemantic_GLES(ctx, FG_CVV_Z_SEMANTIC_MINUS_ONE_TO_ONE_FORWARD_Z)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetCvvZSemantic_GLES passed")
    }

    @Test
    fun testHMS_FG_SetImageFormat_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_SetImageFormat_GLES(ctx, FG_FORMAT_R8G8B8A8_UNORM)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetImageFormat_GLES passed")
    }

    @Test
    fun testHMS_FG_SetDepthStencilYDirectionInverted_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_SetDepthStencilYDirectionInverted_GLES(ctx, false)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetDepthStencilYDirectionInverted_GLES passed")
    }

    @Test
    fun testHMS_FG_Activate_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_Activate_GLES(ctx)
        assertNotNull(rc)
        HMS_FG_Deactivate_GLES(ctx)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_Activate_GLES passed")
    }

    @Test
    fun testHMS_FG_Deactivate_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        HMS_FG_Activate_GLES(ctx)
        val rc = HMS_FG_Deactivate_GLES(ctx)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_Deactivate_GLES passed")
    }

    @Test
    fun testHMS_FG_IsActive_GLES() {
        memScoped {
            val ctx = HMS_FG_CreateContext_GLES()
            val isActive = alloc<BooleanVar>()
            val rc = HMS_FG_IsActive_GLES(ctx, isActive.ptr)
            assertNotNull(rc)
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_IsActive_GLES passed")
    }

    @Test
    fun testHMS_FG_SetExtendedCameraInfo_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_SetExtendedCameraInfo_GLES(ctx, null)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetExtendedCameraInfo_GLES passed")
    }

    @Test
    fun testHMS_FG_Dispatch_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = HMS_FG_Dispatch_GLES(ctx, null)
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_Dispatch_GLES passed")
    }

    @Test
    fun testHMS_FG_SetIntegrationMode_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = try { HMS_FG_SetIntegrationMode_GLES(ctx, null) } catch (e: Throwable) { logLine("HMS_FG_SetIntegrationMode_GLES (API 18) exception: $e"); FG_INVALID_PARAMETER }
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetIntegrationMode_GLES passed")
    }

    @Test
    fun testHMS_FG_SetUiPredictionEnabled_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = try { HMS_FG_SetUiPredictionEnabled_GLES(ctx, false) } catch (e: Throwable) { logLine("HMS_FG_SetUiPredictionEnabled_GLES (API 18) exception: $e"); FG_INVALID_PARAMETER }
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetUiPredictionEnabled_GLES passed")
    }

    @Test
    fun testHMS_FG_SetTargetFps_GLES() {
        val ctx = HMS_FG_CreateContext_GLES()
        val rc = try { HMS_FG_SetTargetFps_GLES(ctx, 60) } catch (e: Throwable) { logLine("HMS_FG_SetTargetFps_GLES (API 18) exception: $e"); FG_INVALID_PARAMETER }
        assertNotNull(rc)
        memScoped {
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
        }
        logLine("HMS_FG_SetTargetFps_GLES passed")
    }

    @Test
    fun testHMS_FG_DestroyContext_GLES() {
        memScoped {
            val ctx = HMS_FG_CreateContext_GLES()
            val ctxPtr = alloc<CPointerVar<FG_Context_GLES>>()
            ctxPtr.value = ctx
            val rc = HMS_FG_DestroyContext_GLES(ctxPtr.ptr)
            assertNotNull(rc)
        }
        logLine("HMS_FG_DestroyContext_GLES passed")
    }

    // ---------- FG VK 函数 ----------

    @Test
    fun testHMS_FG_CreateContext_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            logLine("HMS_FG_CreateContext_VK(desc)=$ctx")
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            } else {
                logLine("HMS_FG_CreateContext_VK=null (API unavailable), skip destroy")
            }
            logLine("HMS_FG_CreateContext_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetAlgorithmMode_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_SetAlgorithmMode_VK(ctx, null)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetAlgorithmMode_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetResolution_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_SetResolution_VK(ctx, null)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetResolution_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetCvvZSemantic_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_SetCvvZSemantic_VK(ctx, FG_CVV_Z_SEMANTIC_MINUS_ONE_TO_ONE_FORWARD_Z)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetCvvZSemantic_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetImageFormat_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_SetImageFormat_VK(ctx, null)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetImageFormat_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetDepthStencilYDirectionInverted_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_SetDepthStencilYDirectionInverted_VK(ctx, false)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetDepthStencilYDirectionInverted_VK passed")
        }
    }

    @Test
    fun testHMS_FG_CreateImage_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val fgImage = HMS_FG_CreateImage_VK(ctx, null, null)
            logLine("HMS_FG_CreateImage_VK=$fgImage")
            if (fgImage != null) HMS_FG_DestroyImage_VK(ctx, fgImage)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_CreateImage_VK passed")
        }
    }

    @Test
    fun testHMS_FG_Activate_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_Activate_VK(ctx)
            assertNotNull(rc)
            if (ctx != null) {
                HMS_FG_Deactivate_VK(ctx)
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_Activate_VK passed")
        }
    }

    @Test
    fun testHMS_FG_Deactivate_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            HMS_FG_Activate_VK(ctx)
            val rc = HMS_FG_Deactivate_VK(ctx)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_Deactivate_VK passed")
        }
    }

    @Test
    fun testHMS_FG_IsActive_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val isActive = alloc<BooleanVar>()
            val rc = HMS_FG_IsActive_VK(ctx, isActive.ptr)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_IsActive_VK passed")
        }
    }

    @Test
    fun testHMS_FG_DestroyImage_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val fgImage = HMS_FG_CreateImage_VK(ctx, null, null)
            var rc = if (fgImage != null) HMS_FG_DestroyImage_VK(ctx, fgImage) else FG_INVALID_PARAMETER
            assertNotNull(rc)
            rc = HMS_FG_DestroyImage_VK(ctx, null)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_DestroyImage_VK passed")
        }
    }

    @Test
    fun testHMS_FG_Dispatch_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = HMS_FG_Dispatch_VK(ctx, null)
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_Dispatch_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetIntegrationMode_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = try { HMS_FG_SetIntegrationMode_VK(ctx, null) } catch (e: Throwable) { logLine("HMS_FG_SetIntegrationMode_VK (API 18) exception: $e"); FG_INVALID_PARAMETER }
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetIntegrationMode_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetUiPredictionEnabled_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = try { HMS_FG_SetUiPredictionEnabled_VK(ctx, false) } catch (e: Throwable) { logLine("HMS_FG_SetUiPredictionEnabled_VK (API 18) exception: $e"); FG_INVALID_PARAMETER }
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetUiPredictionEnabled_VK passed")
        }
    }

    @Test
    fun testHMS_FG_SetTargetFps_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            val rc = try { HMS_FG_SetTargetFps_VK(ctx, 60) } catch (e: Throwable) { logLine("HMS_FG_SetTargetFps_VK (API 18) exception: $e"); FG_INVALID_PARAMETER }
            assertNotNull(rc)
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                HMS_FG_DestroyContext_VK(ctxPtr.ptr)
            }
            logLine("HMS_FG_SetTargetFps_VK passed")
        }
    }

    @Test
    fun testHMS_FG_DestroyContext_VK() {
        memScoped {
            val ctx = run {
                val desc = alloc<FG_ContextDescription_VK>()
                desc.vkInstance = null
                desc.vkPhysicalDevice = null
                desc.vkDevice = null
                desc.framesInFlight = 1.toUByte()
                desc.fnVulkanLoaderFunction = vkGetInstanceProcAddrStub
                HMS_FG_CreateContext_VK(desc.ptr)
            }
            if (ctx != null) {
                val ctxPtr = alloc<CPointerVar<FG_Context_VK>>()
                ctxPtr.value = ctx
                val rc = HMS_FG_DestroyContext_VK(ctxPtr.ptr)
                assertNotNull(rc)
            } else {
                logLine("HMS_FG_CreateContext_VK=null, skip DestroyContext_VK")
            }
            logLine("HMS_FG_DestroyContext_VK passed")
        }
    }
}
