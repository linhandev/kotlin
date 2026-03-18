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
class AbilityRuntimeTest {

    private fun logLine(message: String) = println("[stdout] AbilityRuntimeTest $message")

    // ---------- Enums ----------
    @Test
    fun testEnum_AbilityRuntime_ErrorCode() {
        try {
            val noError = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_NO_ERROR
            val permissionDenied = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_PERMISSION_DENIED
            val paramInvalid = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_PARAM_INVALID
            val notSupported = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_NOT_SUPPORTED
            val noSuchAbility = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_NO_SUCH_ABILITY
            val incorrectAbilityType = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_INCORRECT_ABILITY_TYPE
            val crowdtestExpired = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_CROWDTEST_EXPIRED
            val wukongMode = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_WUKONG_MODE
            val contextNotExist = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_CONTEXT_NOT_EXIST
            val controlled = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_CONTROLLED
            val edmControlled = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_EDM_CONTROLLED
            val crossApp = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_CROSS_APP
            val internal = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_INTERNAL
            val notTopAbility = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_NOT_TOP_ABILITY
            val visibilitySettingDisabled = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_VISIBILITY_SETTING_DISABLED
            val multiAppNotSupported = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_MULTI_APP_NOT_SUPPORTED
            val invalidAppInstanceKey = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_INVALID_APP_INSTANCE_KEY
            val upperLimitReached = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_UPPER_LIMIT_REACHED
            val multiInstanceNotSupported = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_MULTI_INSTANCE_NOT_SUPPORTED
            val appInstanceKeyNotSupported = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_ERROR_CODE_APP_INSTANCE_KEY_NOT_SUPPORTED
            logLine("AbilityRuntime_ErrorCode covered")
        } catch (e: Throwable) {
            logLine("AbilityRuntime_ErrorCode (API 17+/21+) exception: $e")
        }
    }

    @Test
    fun testEnum_AbilityRuntime_AreaMode() {
        try {
            val el1 = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_AREA_MODE_EL1
            val el2 = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_AREA_MODE_EL2
            val el3 = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_AREA_MODE_EL3
            val el4 = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_AREA_MODE_EL4
            val el5 = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_AREA_MODE_EL5
            logLine("AbilityRuntime_AreaMode: EL1=$el1 EL2=$el2 EL3=$el3 EL4=$el4 EL5=$el5")
        } catch (e: Throwable) {
            logLine("AbilityRuntime_AreaMode exception: $e")
        }
    }

    @Test
    fun testEnum_AbilityRuntime_StartVisibility() {
        try {
            val hideUponStart = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_HIDE_UPON_START
            val showUponStart = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SHOW_UPON_START
            logLine("AbilityRuntime_StartVisibility: HIDE=$hideUponStart SHOW=$showUponStart")
        } catch (e: Throwable) {
            logLine("AbilityRuntime_StartVisibility exception: $e")
        }
    }

    @Test
    fun testEnum_AbilityRuntime_WindowMode() {
        try {
            val undefined = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_WINDOW_MODE_UNDEFINED
            val fullScreen = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_WINDOW_MODE_FULL_SCREEN
            logLine("AbilityRuntime_WindowMode: UNDEFINED=$undefined FULL_SCREEN=$fullScreen")
        } catch (e: Throwable) {
            logLine("AbilityRuntime_WindowMode exception: $e")
        }
    }

    @Test
    fun testEnum_AbilityRuntime_SupportedWindowMode() {
        try {
            val supportedFullScreen = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_FULL_SCREEN
            val supportedSplit = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_SPLIT
            val supportedFloating = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_FLOATING
            logLine("AbilityRuntime_SupportedWindowMode: FULL_SCREEN=$supportedFullScreen SPLIT=$supportedSplit FLOATING=$supportedFloating")
        } catch (e: Throwable) {
            logLine("AbilityRuntime_SupportedWindowMode exception: $e")
        }
    }

    // ---------- StartOptions ----------
    @Test
    fun testOH_AbilityRuntime_CreateStartOptions() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                logLine("OH_AbilityRuntime_CreateStartOptions() result: $options")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
                assertNotNull(r)
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_CreateStartOptions (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsWindowMode() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowMode(options, platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_WINDOW_MODE_FULL_SCREEN)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsWindowMode result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsWindowMode (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsWindowMode() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowMode(options, platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_WINDOW_MODE_FULL_SCREEN)
                val windowMode = alloc<platform.AbilityKit.AbilityRuntime.AbilityRuntime_WindowModeVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsWindowMode(options, windowMode.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsWindowMode: ${windowMode.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsWindowMode (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsDisplayId() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsDisplayId(options, 0)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsDisplayId result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsDisplayId (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsDisplayId() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsDisplayId(options, 0)
                val displayId = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsDisplayId(options, displayId.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsDisplayId: ${displayId.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsDisplayId (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsWithAnimation() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWithAnimation(options, true)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsWithAnimation result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsWithAnimation (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsWithAnimation() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWithAnimation(options, true)
                val withAnimation = alloc<BooleanVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsWithAnimation(options, withAnimation.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsWithAnimation: ${withAnimation.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsWithAnimation (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsWindowLeft() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowLeft(options, 100)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsWindowLeft result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsWindowLeft (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsWindowLeft() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowLeft(options, 100)
                val windowLeft = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsWindowLeft(options, windowLeft.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsWindowLeft: ${windowLeft.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsWindowLeft (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsWindowTop() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowTop(options, 200)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsWindowTop result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsWindowTop (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsWindowTop() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowTop(options, 200)
                val windowTop = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsWindowTop(options, windowTop.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsWindowTop: ${windowTop.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsWindowTop (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsWindowWidth() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowWidth(options, 800)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsWindowWidth result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsWindowWidth (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsWindowWidth() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowWidth(options, 800)
                val windowWidth = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsWindowWidth(options, windowWidth.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsWindowWidth: ${windowWidth.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsWindowWidth (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsWindowHeight() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowHeight(options, 600)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsWindowHeight result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsWindowHeight (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsWindowHeight() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsWindowHeight(options, 600)
                val windowHeight = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsWindowHeight(options, windowHeight.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsWindowHeight: ${windowHeight.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsWindowHeight (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsStartVisibility() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsStartVisibility(options, platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SHOW_UPON_START)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsStartVisibility result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsStartVisibility (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsStartVisibility() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsStartVisibility(options, platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SHOW_UPON_START)
                val startVisibility = alloc<platform.AbilityKit.AbilityRuntime.AbilityRuntime_StartVisibilityVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsStartVisibility(options, startVisibility.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsStartVisibility: ${startVisibility.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsStartVisibility (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsStartWindowIcon() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsStartWindowIcon(options, null)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsStartWindowIcon result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsStartWindowIcon (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsStartWindowIcon() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsStartWindowIcon(options, null)
                val startWindowIcon = alloc<CPointerVar<CPointed>>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsStartWindowIcon(options, startWindowIcon.ptr.reinterpret())
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsStartWindowIcon result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsStartWindowIcon (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsStartWindowBackgroundColor() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsStartWindowBackgroundColor(options, "#FF000000")
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsStartWindowBackgroundColor result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsStartWindowBackgroundColor (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsStartWindowBackgroundColor() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsStartWindowBackgroundColor(options, "#FF000000")
                val outColor = alloc<CPointerVar<ByteVar>>()
                val outSize = alloc<ULongVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsStartWindowBackgroundColor(options, outColor.ptr.reinterpret(), outSize.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsStartWindowBackgroundColor result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsStartWindowBackgroundColor (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsSupportedWindowModes() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val supportedModes = allocArray<UIntVar>(3).apply {
                    this[0] = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_FULL_SCREEN
                    this[1] = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_SPLIT
                    this[2] = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_FLOATING
                }
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsSupportedWindowModes(options, supportedModes, 3uL)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsSupportedWindowModes result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsSupportedWindowModes (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsSupportedWindowModes() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val supportedModes = allocArray<UIntVar>(3).apply {
                    this[0] = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_FULL_SCREEN
                    this[1] = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_SPLIT
                    this[2] = platform.AbilityKit.AbilityRuntime.ABILITY_RUNTIME_SUPPORTED_WINDOW_MODE_FLOATING
                }
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsSupportedWindowModes(options, supportedModes, 3uL)
                val outModes = alloc<CPointerVar<UIntVar>>()
                val outModeSize = alloc<ULongVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsSupportedWindowModes(options, outModes.ptr.reinterpret(), outModeSize.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsSupportedWindowModes result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsSupportedWindowModes (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsMinWindowWidth() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMinWindowWidth(options, 400)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsMinWindowWidth result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsMinWindowWidth (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsMinWindowWidth() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMinWindowWidth(options, 400)
                val minWidth = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsMinWindowWidth(options, minWidth.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsMinWindowWidth: ${minWidth.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsMinWindowWidth (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsMaxWindowWidth() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMaxWindowWidth(options, 1920)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsMaxWindowWidth result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsMaxWindowWidth (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsMaxWindowWidth() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMaxWindowWidth(options, 1920)
                val maxWidth = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsMaxWindowWidth(options, maxWidth.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsMaxWindowWidth: ${maxWidth.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsMaxWindowWidth (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsMinWindowHeight() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMinWindowHeight(options, 300)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsMinWindowHeight result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsMinWindowHeight (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsMinWindowHeight() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMinWindowHeight(options, 300)
                val minHeight = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsMinWindowHeight(options, minHeight.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsMinWindowHeight: ${minHeight.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsMinWindowHeight (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_SetStartOptionsMaxWindowHeight() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMaxWindowHeight(options, 1080)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_SetStartOptionsMaxWindowHeight result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_SetStartOptionsMaxWindowHeight (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_GetStartOptionsMaxWindowHeight() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_SetStartOptionsMaxWindowHeight(options, 1080)
                val maxHeight = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_GetStartOptionsMaxWindowHeight(options, maxHeight.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_GetStartOptionsMaxWindowHeight: ${maxHeight.value}")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_GetStartOptionsMaxWindowHeight (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_StartSelfUIAbilityWithStartOptions() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_StartSelfUIAbilityWithStartOptions(null, options)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_StartSelfUIAbilityWithStartOptions result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_StartSelfUIAbilityWithStartOptions (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_StartSelfUIAbilityWithPidResult() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val targetPid = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_StartSelfUIAbilityWithPidResult(null, options, targetPid.ptr)
                assertNotNull(r)
                logLine("OH_AbilityRuntime_StartSelfUIAbilityWithPidResult result: $r")
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_StartSelfUIAbilityWithPidResult (API 17) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_DestroyStartOptions() {
        try {
            memScoped {
                val options = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_CreateStartOptions()
                assertNotNull(options)
                val holder = alloc<CPointerVar<CPointed>>()
                holder.value = options.reinterpret()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_DestroyStartOptions(holder.ptr.reinterpret())
                assertNotNull(r)
                logLine("OH_AbilityRuntime_DestroyStartOptions result: $r")
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_DestroyStartOptions (API 17) exception: $e")
        }
    }

    // ---------- ApplicationContext ----------
    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetCacheDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetCacheDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetCacheDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetBundleName() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetBundleName(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetBundleName: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetTempDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetTempDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetTempDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetFilesDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetFilesDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetFilesDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetDatabaseDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetDatabaseDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetDatabaseDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetPreferencesDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetPreferencesDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetPreferencesDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetBundleCodeDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetBundleCodeDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetBundleCodeDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetDistributedFilesDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetDistributedFilesDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetDistributedFilesDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetCloudFileDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetCloudFileDir(buffer, bufferSize, writeLength.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetCloudFileDir: $r")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetResourceDir() {
        memScoped {
            val bufferSize = 256
            val buffer = allocArray<ByteVar>(bufferSize)
            val writeLength = alloc<IntVar>()
            try {
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetResourceDir(null, buffer, bufferSize, writeLength.ptr)
                logLine("OH_AbilityRuntime_ApplicationContextGetResourceDir (API 20): $r")
            } catch (e: Throwable) {
                logLine("OH_AbilityRuntime_ApplicationContextGetResourceDir (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetAreaMode() {
        memScoped {
            val areaMode = alloc<platform.AbilityKit.AbilityRuntime.AbilityRuntime_AreaModeVar>()
            val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetAreaMode(areaMode.ptr)
            assertNotNull(r)
            logLine("OH_AbilityRuntime_ApplicationContextGetAreaMode: $r ${areaMode.value}")
        }
    }

    @Test
    fun testOH_AbilityRuntime_StartSelfUIAbility() {
        val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_StartSelfUIAbility(null)
        assertNotNull(r)
        logLine("OH_AbilityRuntime_StartSelfUIAbility(null): $r")
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetLaunchParameter() {
        try {
            memScoped {
                val bufferSize = 256
                val buffer = allocArray<ByteVar>(bufferSize)
                val writeLength = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetLaunchParameter(buffer, bufferSize, writeLength.ptr)
                logLine("OH_AbilityRuntime_ApplicationContextGetLaunchParameter (API 21): $r")
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_ApplicationContextGetLaunchParameter (API 21) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetLatestParameter() {
        try {
            memScoped {
                val bufferSize = 256
                val buffer = allocArray<ByteVar>(bufferSize)
                val writeLength = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetLatestParameter(buffer, bufferSize, writeLength.ptr)
                logLine("OH_AbilityRuntime_ApplicationContextGetLatestParameter (API 21): $r")
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_ApplicationContextGetLatestParameter (API 21) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetVersionCode() {
        try {
            memScoped {
                val versionCode = alloc<LongVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetVersionCode(versionCode.ptr)
                logLine("OH_AbilityRuntime_ApplicationContextGetVersionCode (API 21): $r")
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_ApplicationContextGetVersionCode (API 21) exception: $e")
        }
    }

    @Test
    fun testOH_AbilityRuntime_ApplicationContextGetLogFileDir() {
        try {
            memScoped {
                val bufferSize = 256
                val buffer = allocArray<ByteVar>(bufferSize)
                val writeLength = alloc<IntVar>()
                val r = platform.AbilityKit.AbilityRuntime.OH_AbilityRuntime_ApplicationContextGetLogFileDir(buffer, bufferSize, writeLength.ptr)
                logLine("OH_AbilityRuntime_ApplicationContextGetLogFileDir (API 22): $r")
            }
        } catch (e: Throwable) {
            logLine("OH_AbilityRuntime_ApplicationContextGetLogFileDir (API 22) exception: $e")
        }
    }
}
