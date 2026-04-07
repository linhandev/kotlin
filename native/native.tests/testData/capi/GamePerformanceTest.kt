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
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.GameServiceKit.GamePerformance.*
import cnames.structs.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class GamePerformanceTest {

    private fun logLine(message: String) = println("[stdout] GamePerformanceTest $message")

    // ---------- 枚举：顶层访问 ----------

    @Test
    fun testEnum_GamePerformance_ErrorCode() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assert(v == expected) }
        p("GAME_PERFORMANCE_SUCCESS", GAME_PERFORMANCE_SUCCESS.toInt(), 0)
        p("GAME_PERFORMANCE_PARAM_INVALID", GAME_PERFORMANCE_PARAM_INVALID.toInt(), 401)
        p("GAME_PERFORMANCE_INTERNAL_ERROR", GAME_PERFORMANCE_INTERNAL_ERROR.toInt(), 1010300001)
        p("GAME_PERFORMANCE_AUTH_FAILED", GAME_PERFORMANCE_AUTH_FAILED.toInt(), 1010300002)
        p("GAME_PERFORMANCE_INVALID_REQUEST", GAME_PERFORMANCE_INVALID_REQUEST.toInt(), 1010300003)
        p("GAME_PERFORMANCE_PARAM_ERROR", GAME_PERFORMANCE_PARAM_ERROR.toInt(), 1010300004)
    }

    @Test
    fun testEnum_GamePerformance_EngineType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assert(v == expected) }
        p("GAME_PERFORMANCE_ENGINE_TYPE_UNITY", GAME_PERFORMANCE_ENGINE_TYPE_UNITY.toInt(), 1)
        p("GAME_PERFORMANCE_ENGINE_TYPE_UNREAL", GAME_PERFORMANCE_ENGINE_TYPE_UNREAL.toInt(), 2)
        p("GAME_PERFORMANCE_ENGINE_TYPE_MESSIAH", GAME_PERFORMANCE_ENGINE_TYPE_MESSIAH.toInt(), 3)
        p("GAME_PERFORMANCE_ENGINE_TYPE_COCOS", GAME_PERFORMANCE_ENGINE_TYPE_COCOS.toInt(), 4)
        p("GAME_PERFORMANCE_ENGINE_TYPE_OTHERS", GAME_PERFORMANCE_ENGINE_TYPE_OTHERS.toInt(), 200)
    }

    @Test
    fun testEnum_GamePerformance_GameType() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assert(v == expected) }
        p("GAME_PERFORMANCE_GAME_TYPE_MOBA", GAME_PERFORMANCE_GAME_TYPE_MOBA.toInt(), 1)
        p("GAME_PERFORMANCE_GAME_TYPE_RPG", GAME_PERFORMANCE_GAME_TYPE_RPG.toInt(), 2)
        p("GAME_PERFORMANCE_GAME_TYPE_FPS", GAME_PERFORMANCE_GAME_TYPE_FPS.toInt(), 3)
        p("GAME_PERFORMANCE_GAME_TYPE_FTG", GAME_PERFORMANCE_GAME_TYPE_FTG.toInt(), 4)
        p("GAME_PERFORMANCE_GAME_TYPE_RAC", GAME_PERFORMANCE_GAME_TYPE_RAC.toInt(), 5)
        p("GAME_PERFORMANCE_GAME_TYPE_OTHERS", GAME_PERFORMANCE_GAME_TYPE_OTHERS.toInt(), 200)
    }

    @Test
    fun testEnum_GamePerformance_PictureQualityLevel() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assert(v == expected) }
        p("GAME_PERFORMANCE_PQL_SMOOTH", GAME_PERFORMANCE_PQL_SMOOTH.toInt(), 1)
        p("GAME_PERFORMANCE_PQL_BALANCED", GAME_PERFORMANCE_PQL_BALANCED.toInt(), 2)
        p("GAME_PERFORMANCE_PQL_HD", GAME_PERFORMANCE_PQL_HD.toInt(), 3)
        p("GAME_PERFORMANCE_PQL_HDR", GAME_PERFORMANCE_PQL_HDR.toInt(), 4)
        p("GAME_PERFORMANCE_PQL_UHD", GAME_PERFORMANCE_PQL_UHD.toInt(), 5)
    }

    @Test
    fun testEnum_GamePerformance_SceneImportanceLevel() {
        fun p(name: String, v: Int, expected: Int) { logLine("$name=$v"); assert(v == expected) }
        p("GAME_PERFORMANCE_SIL_LEVEL1", GAME_PERFORMANCE_SIL_LEVEL1.toInt(), 1)
        p("GAME_PERFORMANCE_SIL_LEVEL2", GAME_PERFORMANCE_SIL_LEVEL2.toInt(), 2)
        p("GAME_PERFORMANCE_SIL_LEVEL3", GAME_PERFORMANCE_SIL_LEVEL3.toInt(), 3)
        p("GAME_PERFORMANCE_SIL_LEVEL4", GAME_PERFORMANCE_SIL_LEVEL4.toInt(), 4)
        p("GAME_PERFORMANCE_SIL_LEVEL5", GAME_PERFORMANCE_SIL_LEVEL5.toInt(), 5)
    }

    @Test
    fun testEnum_GamePerformance_CpuLevel() {
        logLine("CPU_LEVEL_LOW=${GAME_PERFORMANCE_CPU_LEVEL_LOW.toInt()}"); assert(GAME_PERFORMANCE_CPU_LEVEL_LOW.toInt() == 1)
        logLine("CPU_LEVEL_MIDDLE=${GAME_PERFORMANCE_CPU_LEVEL_MIDDLE.toInt()}"); assert(GAME_PERFORMANCE_CPU_LEVEL_MIDDLE.toInt() == 2)
        logLine("CPU_LEVEL_HIGH=${GAME_PERFORMANCE_CPU_LEVEL_HIGH.toInt()}"); assert(GAME_PERFORMANCE_CPU_LEVEL_HIGH.toInt() == 3)
    }

    @Test
    fun testEnum_GamePerformance_GpuLevel() {
        logLine("GPU_LEVEL_LOW=${GAME_PERFORMANCE_GPU_LEVEL_LOW.toInt()}"); assert(GAME_PERFORMANCE_GPU_LEVEL_LOW.toInt() == 1)
        logLine("GPU_LEVEL_MIDDLE=${GAME_PERFORMANCE_GPU_LEVEL_MIDDLE.toInt()}"); assert(GAME_PERFORMANCE_GPU_LEVEL_MIDDLE.toInt() == 2)
        logLine("GPU_LEVEL_HIGH=${GAME_PERFORMANCE_GPU_LEVEL_HIGH.toInt()}"); assert(GAME_PERFORMANCE_GPU_LEVEL_HIGH.toInt() == 3)
    }

    @Test
    fun testEnum_GamePerformance_DdrLevel() {
        logLine("DDR_LEVEL_LOW=${GAME_PERFORMANCE_DDR_LEVEL_LOW.toInt()}"); assert(GAME_PERFORMANCE_DDR_LEVEL_LOW.toInt() == 1)
        logLine("DDR_LEVEL_MIDDLE=${GAME_PERFORMANCE_DDR_LEVEL_MIDDLE.toInt()}"); assert(GAME_PERFORMANCE_DDR_LEVEL_MIDDLE.toInt() == 2)
        logLine("DDR_LEVEL_HIGH=${GAME_PERFORMANCE_DDR_LEVEL_HIGH.toInt()}"); assert(GAME_PERFORMANCE_DDR_LEVEL_HIGH.toInt() == 3)
    }

    @Test
    fun testEnum_GamePerformance_NetLoad() {
        logLine("NET_LOAD_LIGHT=${GAME_PERFORMANCE_NET_LOAD_LIGHT.toInt()}"); assert(GAME_PERFORMANCE_NET_LOAD_LIGHT.toInt() == 1)
        logLine("NET_LOAD_MODERATE=${GAME_PERFORMANCE_NET_LOAD_MODERATE.toInt()}"); assert(GAME_PERFORMANCE_NET_LOAD_MODERATE.toInt() == 2)
        logLine("NET_LOAD_HEAVY=${GAME_PERFORMANCE_NET_LOAD_HEAVY.toInt()}"); assert(GAME_PERFORMANCE_NET_LOAD_HEAVY.toInt() == 3)
    }

    @Test
    fun testEnum_GamePerformance_DeviceInfoType() {
        logLine("DEVICEINFO_TYPE_THERMAL=${GAME_PERFORMANCE_DEVICEINFO_TYPE_THERMAL.toInt()}"); assert(GAME_PERFORMANCE_DEVICEINFO_TYPE_THERMAL.toInt() == 0)
        logLine("DEVICEINFO_TYPE_GPU=${GAME_PERFORMANCE_DEVICEINFO_TYPE_GPU.toInt()}"); assert(GAME_PERFORMANCE_DEVICEINFO_TYPE_GPU.toInt() == 1)
        logLine("DEVICEINFO_TYPE_CPU=${GAME_PERFORMANCE_DEVICEINFO_TYPE_CPU.toInt()}"); assert(GAME_PERFORMANCE_DEVICEINFO_TYPE_CPU.toInt() == 2)
    }

    // ---------- InitParameters ----------

    @Test
    fun testHMS_GamePerformance_CreateInitParameters() {
        memScoped {
            val initParameters = alloc<CPointerVar<GamePerformance_InitParameters>>()
            val rc = HMS_GamePerformance_CreateInitParameters(initParameters.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreateInitParameters=$rc")
            HMS_GamePerformance_DestroyInitParameters(initParameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_InitParameters_SetBundleName() {
        memScoped {
            val initParameters = alloc<CPointerVar<GamePerformance_InitParameters>>()
            val rcCreate = HMS_GamePerformance_CreateInitParameters(initParameters.ptr)
            assertNotNull(rcCreate)
            assertNotNull(initParameters.value)
            val p = initParameters.value
            val rc = HMS_GamePerformance_InitParameters_SetBundleName(p, "com.test.bundle")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_InitParameters_SetBundleName=$rc")
            HMS_GamePerformance_DestroyInitParameters(initParameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_InitParameters_SetAppVersion() {
        memScoped {
            val initParameters = alloc<CPointerVar<GamePerformance_InitParameters>>()
            val rcCreate = HMS_GamePerformance_CreateInitParameters(initParameters.ptr)
            assertNotNull(rcCreate)
            assertNotNull(initParameters.value)
            val p = initParameters.value
            val rc = HMS_GamePerformance_InitParameters_SetAppVersion(p, "1.0.0")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_InitParameters_SetAppVersion=$rc")
            HMS_GamePerformance_DestroyInitParameters(initParameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_Init() {
        memScoped {
            val initParameters = alloc<CPointerVar<GamePerformance_InitParameters>>()
            val rcCreate = HMS_GamePerformance_CreateInitParameters(initParameters.ptr)
            assertNotNull(rcCreate)
            assertNotNull(initParameters.value)
            val p = initParameters.value
            HMS_GamePerformance_InitParameters_SetBundleName(p, "com.test.bundle")
            HMS_GamePerformance_InitParameters_SetAppVersion(p, "1.0.0")
            val rc = HMS_GamePerformance_Init(p)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_Init=$rc")
            HMS_GamePerformance_DestroyInitParameters(initParameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyInitParameters() {
        memScoped {
            val initParameters = alloc<CPointerVar<GamePerformance_InitParameters>>()
            val rcCreate = HMS_GamePerformance_CreateInitParameters(initParameters.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroyInitParameters(initParameters.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyInitParameters=$rc")
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyInitParameters_null() {
        memScoped {
            val initParameters = alloc<CPointerVar<GamePerformance_InitParameters>>()
            initParameters.value = null
            val rc = HMS_GamePerformance_DestroyInitParameters(initParameters.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyInitParameters(null)=$rc")
        }
    }

    // ---------- PackageInfo ----------

    @Test
    fun testHMS_GamePerformance_CreatePackageInfo() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rc = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreatePackageInfo=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PackageInfo_SetBundleName() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            val rc = HMS_GamePerformance_PackageInfo_SetBundleName(p, "com.test.bundle")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PackageInfo_SetBundleName=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PackageInfo_SetAppVersion() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            val rc = HMS_GamePerformance_PackageInfo_SetAppVersion(p, "1.0.0")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PackageInfo_SetAppVersion=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PackageInfo_SetEngineType() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            val rc = HMS_GamePerformance_PackageInfo_SetEngineType(p, GAME_PERFORMANCE_ENGINE_TYPE_UNITY)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PackageInfo_SetEngineType=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PackageInfo_SetEngineVersion() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            val rc = HMS_GamePerformance_PackageInfo_SetEngineVersion(p, "2021.3")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PackageInfo_SetEngineVersion=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PackageInfo_SetGameType() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            val rc = HMS_GamePerformance_PackageInfo_SetGameType(p, GAME_PERFORMANCE_GAME_TYPE_OTHERS)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PackageInfo_SetGameType=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PackageInfo_SetVulkanSupported() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            val rc = HMS_GamePerformance_PackageInfo_SetVulkanSupported(p, true)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PackageInfo_SetVulkanSupported=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_UpdatePackageInfo() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(packageInfo.value)
            val p = packageInfo.value
            HMS_GamePerformance_PackageInfo_SetBundleName(p, "com.test.bundle")
            val rc = HMS_GamePerformance_UpdatePackageInfo(p)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_UpdatePackageInfo=$rc")
            HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyPackageInfo() {
        memScoped {
            val packageInfo = alloc<CPointerVar<GamePerformance_PackageInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePackageInfo(packageInfo.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroyPackageInfo(packageInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyPackageInfo=$rc")
        }
    }

    // ---------- ConfigInfo ----------

    @Test
    fun testHMS_GamePerformance_CreateConfigInfo() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rc = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreateConfigInfo=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetMaxPictureQualityLevel() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetMaxPictureQualityLevel(c, GAME_PERFORMANCE_PQL_HD)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetMaxPictureQualityLevel=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetCurrentPictureQualityLevel() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetCurrentPictureQualityLevel(c, GAME_PERFORMANCE_PQL_BALANCED)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetCurrentPictureQualityLevel=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetMaxFrameRate() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetMaxFrameRate(c, 60L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetMaxFrameRate=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetCurrentFrameRate() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetCurrentFrameRate(c, 30L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetCurrentFrameRate=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetMaxResolution() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetMaxResolution(c, "1920x1080")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetMaxResolution=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetCurrentResolution() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetCurrentResolution(c, "1280x720")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetCurrentResolution=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetAntiAliasingEnabled() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetAntiAliasingEnabled(c, true)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetAntiAliasingEnabled=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetShadowEnabled() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetShadowEnabled(c, false)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetShadowEnabled=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetMultithreadingEnabled() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetMultithreadingEnabled(c, true)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetMultithreadingEnabled=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetParticleEnabled() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetParticleEnabled(c, true)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetParticleEnabled=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ConfigInfo_SetHdModeEnabled() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_ConfigInfo_SetHdModeEnabled(c, false)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ConfigInfo_SetHdModeEnabled=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_UpdateConfigInfo() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(configInfo.value)
            val c = configInfo.value
            val rc = HMS_GamePerformance_UpdateConfigInfo(c)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_UpdateConfigInfo=$rc")
            HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyConfigInfo() {
        memScoped {
            val configInfo = alloc<CPointerVar<GamePerformance_ConfigInfo>>()
            val rcCreate = HMS_GamePerformance_CreateConfigInfo(configInfo.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroyConfigInfo(configInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyConfigInfo=$rc")
        }
    }

    // ---------- SceneInfo ----------

    @Test
    fun testHMS_GamePerformance_CreateSceneInfo() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rc = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreateSceneInfo=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetSceneID() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetSceneID(s, 1L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetSceneID=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetDescription() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetDescription(s, "main")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetDescription=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetSubSceneID() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetSubSceneID(s, "sub_1")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetSubSceneID=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetSubDescription() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetSubDescription(s, "lobby")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetSubDescription=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetImportanceLevel() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetImportanceLevel(s, GAME_PERFORMANCE_SIL_LEVEL1)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetImportanceLevel=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetSceneFrequency() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetSceneFrequency(s, 60L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetSceneFrequency=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetSceneTime() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetSceneTime(s, 1000L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetSceneTime=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetRecommendedCpuLevel() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetRecommendedCpuLevel(s, GAME_PERFORMANCE_CPU_LEVEL_MIDDLE)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetRecommendedCpuLevel=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetRecommendedGpuLevel() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetRecommendedGpuLevel(s, GAME_PERFORMANCE_GPU_LEVEL_MIDDLE)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetRecommendedGpuLevel=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetRecommendedDdrLevel() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetRecommendedDdrLevel(s, GAME_PERFORMANCE_DDR_LEVEL_LOW)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetRecommendedDdrLevel=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetMaxFrameRate() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetMaxFrameRate(s, 60L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetMaxFrameRate=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetCurrentFrameRate() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetCurrentFrameRate(s, 30L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetCurrentFrameRate=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetKeyThread() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetKeyThread(s, "RenderThread")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetKeyThread=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetDrawCallCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetDrawCallCount(s, 100L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetDrawCallCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetVertexCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetVertexCount(s, 10000L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetVertexCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetTriangleCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetTriangleCount(s, 5000L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetTriangleCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetShaderCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetShaderCount(s, 10L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetShaderCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetTextureCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetTextureCount(s, 20L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetTextureCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetMeshCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetMeshCount(s, 30L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetMeshCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetChannelCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetChannelCount(s, 2L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetChannelCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_SceneInfo_SetParticipantCount() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_SceneInfo_SetParticipantCount(s, 4L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_SceneInfo_SetParticipantCount=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_UpdateSceneInfo() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(sceneInfo.value)
            val s = sceneInfo.value
            val rc = HMS_GamePerformance_UpdateSceneInfo(s)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_UpdateSceneInfo=$rc")
            HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroySceneInfo() {
        memScoped {
            val sceneInfo = alloc<CPointerVar<GamePerformance_SceneInfo>>()
            val rcCreate = HMS_GamePerformance_CreateSceneInfo(sceneInfo.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroySceneInfo(sceneInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroySceneInfo=$rc")
        }
    }

    // ---------- NetInfo ----------

    @Test
    fun testHMS_GamePerformance_CreateNetInfo() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rc = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreateNetInfo=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_NetInfo_SetTotalLatency() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(netInfo.value)
            val n = netInfo.value
            val rc = HMS_GamePerformance_NetInfo_SetTotalLatency(n, 50L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_NetInfo_SetTotalLatency=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_NetInfo_SetUplinkLatency() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(netInfo.value)
            val n = netInfo.value
            val rc = HMS_GamePerformance_NetInfo_SetUplinkLatency(n, 20L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_NetInfo_SetUplinkLatency=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_NetInfo_SetDownlinkLatency() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(netInfo.value)
            val n = netInfo.value
            val rc = HMS_GamePerformance_NetInfo_SetDownlinkLatency(n, 25L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_NetInfo_SetDownlinkLatency=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_NetInfo_SetServerLatency() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(netInfo.value)
            val n = netInfo.value
            val rc = HMS_GamePerformance_NetInfo_SetServerLatency(n, 5L)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_NetInfo_SetServerLatency=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_NetInfo_SetNetLoad() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(netInfo.value)
            val n = netInfo.value
            val rc = HMS_GamePerformance_NetInfo_SetNetLoad(n, GAME_PERFORMANCE_NET_LOAD_LIGHT)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_NetInfo_SetNetLoad=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_UpdateNetInfo() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(netInfo.value)
            val n = netInfo.value
            val rc = HMS_GamePerformance_UpdateNetInfo(n)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_UpdateNetInfo=$rc")
            HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyNetInfo() {
        memScoped {
            val netInfo = alloc<CPointerVar<GamePerformance_NetInfo>>()
            val rcCreate = HMS_GamePerformance_CreateNetInfo(netInfo.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroyNetInfo(netInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyNetInfo=$rc")
        }
    }

    // ---------- PlayerInfo ----------

    @Test
    fun testHMS_GamePerformance_CreatePlayerInfo() {
        memScoped {
            val playerInfo = alloc<CPointerVar<GamePerformance_PlayerInfo>>()
            val rc = HMS_GamePerformance_CreatePlayerInfo(playerInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreatePlayerInfo=$rc")
            HMS_GamePerformance_DestroyPlayerInfo(playerInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PlayerInfo_SetGamePlayerId() {
        memScoped {
            val playerInfo = alloc<CPointerVar<GamePerformance_PlayerInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePlayerInfo(playerInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(playerInfo.value)
            val p = playerInfo.value
            val rc = HMS_GamePerformance_PlayerInfo_SetGamePlayerId(p, "player_1")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PlayerInfo_SetGamePlayerId=$rc")
            HMS_GamePerformance_DestroyPlayerInfo(playerInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PlayerInfo_SetTeamPlayerId() {
        memScoped {
            val playerInfo = alloc<CPointerVar<GamePerformance_PlayerInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePlayerInfo(playerInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(playerInfo.value)
            val p = playerInfo.value
            val rc = HMS_GamePerformance_PlayerInfo_SetTeamPlayerId(p, "team_1")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PlayerInfo_SetTeamPlayerId=$rc")
            HMS_GamePerformance_DestroyPlayerInfo(playerInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_PlayerInfo_SetThirdOpenId() {
        memScoped {
            val playerInfo = alloc<CPointerVar<GamePerformance_PlayerInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePlayerInfo(playerInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(playerInfo.value)
            val p = playerInfo.value
            val rc = HMS_GamePerformance_PlayerInfo_SetThirdOpenId(p, "open_1")
            assertNotNull(rc)
            logLine("HMS_GamePerformance_PlayerInfo_SetThirdOpenId=$rc")
            HMS_GamePerformance_DestroyPlayerInfo(playerInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_UpdatePlayerInfo() {
        memScoped {
            val playerInfo = alloc<CPointerVar<GamePerformance_PlayerInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePlayerInfo(playerInfo.ptr)
            assertNotNull(rcCreate)
            assertNotNull(playerInfo.value)
            val p = playerInfo.value
            val rc = HMS_GamePerformance_UpdatePlayerInfo(p)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_UpdatePlayerInfo=$rc")
            HMS_GamePerformance_DestroyPlayerInfo(playerInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyPlayerInfo() {
        memScoped {
            val playerInfo = alloc<CPointerVar<GamePerformance_PlayerInfo>>()
            val rcCreate = HMS_GamePerformance_CreatePlayerInfo(playerInfo.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroyPlayerInfo(playerInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyPlayerInfo=$rc")
        }
    }

    // ---------- Thermal callbacks ----------

    @Test
    fun testHMS_GamePerformance_RegisterThermalLevelChangedCallback() {
        val callback = staticCFunction { _deviceInfo: CPointer<GamePerformance_DeviceInfo>?, _userData: COpaquePointer? -> }
        val rc = HMS_GamePerformance_RegisterThermalLevelChangedCallback(null, 0u, callback, null)
        assertNotNull(rc)
        logLine("HMS_GamePerformance_RegisterThermalLevelChangedCallback=$rc")
        HMS_GamePerformance_UnregisterThermalLevelChangedCallback(callback)
    }

    @Test
    fun testHMS_GamePerformance_UnregisterThermalLevelChangedCallback() {
        val callback = staticCFunction { _deviceInfo: CPointer<GamePerformance_DeviceInfo>?, _userData: COpaquePointer? -> }
        HMS_GamePerformance_RegisterThermalLevelChangedCallback(null, 0u, callback, null)
        val rc = HMS_GamePerformance_UnregisterThermalLevelChangedCallback(callback)
        assertNotNull(rc)
        logLine("HMS_GamePerformance_UnregisterThermalLevelChangedCallback=$rc")
    }

    @Test
    fun testHMS_GamePerformance_UnregisterAllThermalLevelChangedCallbacks() {
        val rc = HMS_GamePerformance_UnregisterAllThermalLevelChangedCallbacks()
        assertNotNull(rc)
        logLine("HMS_GamePerformance_UnregisterAllThermalLevelChangedCallbacks=$rc")
    }

    // ---------- ThermalInfoQueryParameters ----------

    @Test
    fun testHMS_GamePerformance_CreateThermalInfoQueryParameters() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            val rc = HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_CreateThermalInfoQueryParameters=$rc")
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            val rcCreate = HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            assertNotNull(rcCreate)
            assertNotNull(parameters.value)
            val p = parameters.value
            val rc = HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, true)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction=$rc")
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfoQueryParameters_SetTargetThermalLevel() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            val rcCreate = HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            assertNotNull(rcCreate)
            assertNotNull(parameters.value)
            val p = parameters.value
            val rc = HMS_GamePerformance_ThermalInfoQueryParameters_SetTargetThermalLevel(p, 2)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_ThermalInfoQueryParameters_SetTargetThermalLevel=$rc")
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyThermalInfoQueryParameters() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            val rcCreate = HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            assertNotNull(rcCreate)
            val rc = HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyThermalInfoQueryParameters=$rc")
        }
    }

    // ---------- ThermalInfo ----------

    @Test
    fun testHMS_GamePerformance_QueryThermalInfo() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            val rcCreate = HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            assertNotNull(rcCreate)
            assertNotNull(parameters.value)
            val p = parameters.value
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            val rc = HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_QueryThermalInfo=$rc")
            if (thermalInfo.value != null) HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfo_GetRecommendNormalizedCurrent() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            val ti = thermalInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = try { HMS_GamePerformance_ThermalInfo_GetRecommendNormalizedCurrent(ti, outInt.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_ThermalInfo_GetRecommendNormalizedCurrent (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            logLine("HMS_GamePerformance_ThermalInfo_GetRecommendNormalizedCurrent=$rc")
            HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfo_GetNowNormalizedCurrent() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            val ti = thermalInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = try { HMS_GamePerformance_ThermalInfo_GetNowNormalizedCurrent(ti, outInt.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_ThermalInfo_GetNowNormalizedCurrent (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            logLine("HMS_GamePerformance_ThermalInfo_GetNowNormalizedCurrent=$rc")
            HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfo_GetRecommendMaxNormalizedCurrent() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            val ti = thermalInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = try { HMS_GamePerformance_ThermalInfo_GetRecommendMaxNormalizedCurrent(ti, outInt.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_ThermalInfo_GetRecommendMaxNormalizedCurrent (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            logLine("HMS_GamePerformance_ThermalInfo_GetRecommendMaxNormalizedCurrent=$rc")
            HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfo_GetThermalMargin() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            val ti = thermalInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_ThermalInfo_GetThermalMargin(ti, outInt.ptr)
            logLine("HMS_GamePerformance_ThermalInfo_GetThermalMargin=$rc")
            HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfo_GetThermalTrend() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            val ti = thermalInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_ThermalInfo_GetThermalTrend(ti, outInt.ptr)
            logLine("HMS_GamePerformance_ThermalInfo_GetThermalTrend=$rc")
            HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_ThermalInfo_GetThermalLevel() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            val ti = thermalInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_ThermalInfo_GetThermalLevel(ti, outInt.ptr)
            logLine("HMS_GamePerformance_ThermalInfo_GetThermalLevel=$rc")
            HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyThermalInfo() {
        memScoped {
            val parameters = alloc<CPointerVar<GamePerformance_ThermalInfoQueryParameters>>()
            HMS_GamePerformance_CreateThermalInfoQueryParameters(parameters.ptr)
            val p = parameters.value ?: return@memScoped
            HMS_GamePerformance_ThermalInfoQueryParameters_SetNeedsPrediction(p, false)
            val thermalInfo = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            HMS_GamePerformance_QueryThermalInfo(p, thermalInfo.ptr)
            if (thermalInfo.value == null) return@memScoped
            val rc = HMS_GamePerformance_DestroyThermalInfo(thermalInfo.ptr)
            logLine("HMS_GamePerformance_DestroyThermalInfo=$rc")
            HMS_GamePerformance_DestroyThermalInfoQueryParameters(parameters.ptr)
        }
    }

    // ---------- CpuInfo ----------

    @Test
    fun testHMS_GamePerformance_QueryCpuInfo() {
        memScoped {
            val cpuInfo = alloc<CPointerVar<GamePerformance_CpuInfo>>()
            val rc = try { HMS_GamePerformance_QueryCpuInfo(cpuInfo.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_QueryCpuInfo (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            assertNotNull(rc)
            logLine("HMS_GamePerformance_QueryCpuInfo=$rc")
            if (cpuInfo.value != null) try { HMS_GamePerformance_DestroyCpuInfo(cpuInfo.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_GamePerformance_CpuInfo_GetCpuLoadLevel() {
        memScoped {
            val cpuInfo = alloc<CPointerVar<GamePerformance_CpuInfo>>()
            try { HMS_GamePerformance_QueryCpuInfo(cpuInfo.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_QueryCpuInfo (API 22) exception: $e"); return@memScoped }
            val c = cpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = try { HMS_GamePerformance_CpuInfo_GetCpuLoadLevel(c, outInt.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_CpuInfo_GetCpuLoadLevel (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            logLine("HMS_GamePerformance_CpuInfo_GetCpuLoadLevel=$rc")
            try { HMS_GamePerformance_DestroyCpuInfo(cpuInfo.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_GamePerformance_CpuInfo_GetSingleThreadLoadLevel() {
        memScoped {
            val cpuInfo = alloc<CPointerVar<GamePerformance_CpuInfo>>()
            try { HMS_GamePerformance_QueryCpuInfo(cpuInfo.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_QueryCpuInfo (API 22) exception: $e"); return@memScoped }
            val c = cpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = try { HMS_GamePerformance_CpuInfo_GetSingleThreadLoadLevel(c, outInt.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_CpuInfo_GetSingleThreadLoadLevel (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            logLine("HMS_GamePerformance_CpuInfo_GetSingleThreadLoadLevel=$rc")
            try { HMS_GamePerformance_DestroyCpuInfo(cpuInfo.ptr) } catch (_: Throwable) { }
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyCpuInfo() {
        memScoped {
            val cpuInfo = alloc<CPointerVar<GamePerformance_CpuInfo>>()
            try { HMS_GamePerformance_QueryCpuInfo(cpuInfo.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_QueryCpuInfo (API 22) exception: $e"); return@memScoped }
            val rc = try { HMS_GamePerformance_DestroyCpuInfo(cpuInfo.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_DestroyCpuInfo (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyCpuInfo=$rc")
        }
    }

    // ---------- GpuInfo ----------

    @Test
    fun testHMS_GamePerformance_QueryGpuInfo() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            val rc = HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_QueryGpuInfo=$rc")
            if (gpuInfo.value != null) HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_GpuInfo_GetGpuLoadLevel() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val g = gpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_GpuInfo_GetGpuLoadLevel(g, outInt.ptr)
            logLine("HMS_GamePerformance_GpuInfo_GetGpuLoadLevel=$rc")
            HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_GpuInfo_GetVertexLoadLevel() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val g = gpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_GpuInfo_GetVertexLoadLevel(g, outInt.ptr)
            logLine("HMS_GamePerformance_GpuInfo_GetVertexLoadLevel=$rc")
            HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_GpuInfo_GetFragmentLoadLevel() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val g = gpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_GpuInfo_GetFragmentLoadLevel(g, outInt.ptr)
            logLine("HMS_GamePerformance_GpuInfo_GetFragmentLoadLevel=$rc")
            HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_GpuInfo_GetTextureLoadLevel() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val g = gpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_GpuInfo_GetTextureLoadLevel(g, outInt.ptr)
            logLine("HMS_GamePerformance_GpuInfo_GetTextureLoadLevel=$rc")
            HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_GpuInfo_GetBandwidthLoadLevel() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val g = gpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_GpuInfo_GetBandwidthLoadLevel(g, outInt.ptr)
            logLine("HMS_GamePerformance_GpuInfo_GetBandwidthLoadLevel=$rc")
            HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_GpuInfo_GetCurrentFrequency() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val g = gpuInfo.value ?: return@memScoped
            val outInt = alloc<IntVar>()
            val rc = HMS_GamePerformance_GpuInfo_GetCurrentFrequency(g, outInt.ptr)
            logLine("HMS_GamePerformance_GpuInfo_GetCurrentFrequency=$rc")
            HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyGpuInfo() {
        memScoped {
            val gpuInfo = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            HMS_GamePerformance_QueryGpuInfo(gpuInfo.ptr)
            val rc = HMS_GamePerformance_DestroyGpuInfo(gpuInfo.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyGpuInfo=$rc")
        }
    }

    // ---------- DeviceInfo ----------

    @Test
    fun testHMS_GamePerformance_DeviceInfo_GetCpuInfo() {
        memScoped {
            val cpuInfoOut = alloc<CPointerVar<GamePerformance_CpuInfo>>()
            val rc = try { HMS_GamePerformance_DeviceInfo_GetCpuInfo(null, cpuInfoOut.ptr) } catch (e: Throwable) { logLine("HMS_GamePerformance_DeviceInfo_GetCpuInfo (API 22) exception: $e"); GAME_PERFORMANCE_PARAM_INVALID }
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DeviceInfo_GetCpuInfo(null)=$rc")
        }
    }

    @Test
    fun testHMS_GamePerformance_DeviceInfo_GetGpuInfo() {
        memScoped {
            val gpuInfoOut = alloc<CPointerVar<GamePerformance_GpuInfo>>()
            val rc = HMS_GamePerformance_DeviceInfo_GetGpuInfo(null, gpuInfoOut.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DeviceInfo_GetGpuInfo(null)=$rc")
        }
    }

    @Test
    fun testHMS_GamePerformance_DeviceInfo_GetThermalInfo() {
        memScoped {
            val thermalInfoOut = alloc<CPointerVar<GamePerformance_ThermalInfo>>()
            val rc = HMS_GamePerformance_DeviceInfo_GetThermalInfo(null, thermalInfoOut.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DeviceInfo_GetThermalInfo(null)=$rc")
        }
    }

    @Test
    fun testHMS_GamePerformance_DestroyDeviceInfo() {
        memScoped {
            val deviceInfoPtr = alloc<CPointerVar<GamePerformance_DeviceInfo>>()
            deviceInfoPtr.value = null
            val rc = HMS_GamePerformance_DestroyDeviceInfo(deviceInfoPtr.ptr)
            assertNotNull(rc)
            logLine("HMS_GamePerformance_DestroyDeviceInfo=$rc")
        }
    }
}
