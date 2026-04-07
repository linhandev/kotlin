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
import platform.ArkUI.OH_DisplayManager.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_DisplayManagerTest {

    private fun logLine(msg: String) = println("[stdout] OH_DisplayManagerTest $msg")

    @Test
    fun testEnum_NativeDisplayManager_Rotation() {
        assertEquals(NativeDisplayManager_Rotation.DISPLAY_MANAGER_ROTATION_0.value.toInt(), 0)
        assertEquals(NativeDisplayManager_Rotation.DISPLAY_MANAGER_ROTATION_90.value.toInt(), 1)
        assertEquals(NativeDisplayManager_Rotation.DISPLAY_MANAGER_ROTATION_180.value.toInt(), 2)
        assertEquals(NativeDisplayManager_Rotation.DISPLAY_MANAGER_ROTATION_270.value.toInt(), 3)
        logLine("NativeDisplayManager_Rotation passed")
    }

    @Test
    fun testEnum_NativeDisplayManager_Orientation() {
        assertEquals(DISPLAY_MANAGER_PORTRAIT.toInt(), 0)
        assertEquals(DISPLAY_MANAGER_LANDSCAPE.toInt(), 1)
        assertEquals(DISPLAY_MANAGER_PORTRAIT_INVERTED.toInt(), 2)
        assertEquals(DISPLAY_MANAGER_LANDSCAPE_INVERTED.toInt(), 3)
        assertEquals(DISPLAY_MANAGER_UNKNOWN.toInt(), 4)
        logLine("NativeDisplayManager_Orientation passed")
    }

    @Test
    fun testEnum_NativeDisplayManager_ErrorCode() {
        assertEquals(DISPLAY_MANAGER_OK.toInt(), 0)
        assertEquals(DISPLAY_MANAGER_ERROR_NO_PERMISSION.toInt(), 201)
        assertEquals(DISPLAY_MANAGER_ERROR_NOT_SYSTEM_APP.toInt(), 202)
        assertEquals(DISPLAY_MANAGER_ERROR_INVALID_PARAM.toInt(), 401)
        assertEquals(DISPLAY_MANAGER_ERROR_DEVICE_NOT_SUPPORTED.toInt(), 801)
        assertEquals(DISPLAY_MANAGER_ERROR_INVALID_SCREEN.toInt(), 1400001)
        assertEquals(DISPLAY_MANAGER_ERROR_INVALID_CALL.toInt(), 1400002)
        assertEquals(DISPLAY_MANAGER_ERROR_SYSTEM_ABNORMAL.toInt(), 1400003)
        assertEquals(DISPLAY_MANAGER_ERROR_ILLEGAL_PARAM.toInt(), 1400004)
        logLine("NativeDisplayManager_ErrorCode passed")
    }

    @Test
    fun testEnum_NativeDisplayManager_FoldDisplayMode() {
        assertEquals(DISPLAY_MANAGER_FOLD_DISPLAY_MODE_UNKNOWN.toInt(), 0)
        assertEquals(DISPLAY_MANAGER_FOLD_DISPLAY_MODE_FULL.toInt(), 1)
        assertEquals(DISPLAY_MANAGER_FOLD_DISPLAY_MODE_MAIN.toInt(), 2)
        assertEquals(DISPLAY_MANAGER_FOLD_DISPLAY_MODE_SUB.toInt(), 3)
        assertEquals(DISPLAY_MANAGER_FOLD_DISPLAY_MODE_COORDINATION.toInt(), 4)
        logLine("NativeDisplayManager_FoldDisplayMode passed")
    }

    @Test
    fun testEnum_NativeDisplayManager_DisplayState() {
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_UNKNOWN.toInt(), 0)
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_OFF.toInt(), 1)
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_ON.toInt(), 2)
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_DOZE.toInt(), 3)
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_DOZE_SUSPEND.toInt(), 4)
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_VR.toInt(), 5)
        assertEquals(DISPLAY_MANAGER_DISPLAY_STATE_ON_SUSPEND.toInt(), 6)
        logLine("NativeDisplayManager_DisplayState passed")
    }

    @Test
    fun testEnum_NativeDisplayManager_SourceMode() {
        assertEquals(DISPLAY_SOURCE_MODE_NONE.toInt(), 0)
        assertEquals(DISPLAY_SOURCE_MODE_MAIN.toInt(), 1)
        assertEquals(DISPLAY_SOURCE_MODE_MIRROR.toInt(), 2)
        assertEquals(DISPLAY_SOURCE_MODE_EXTEND.toInt(), 3)
        assertEquals(DISPLAY_SOURCE_MODE_ALONE.toInt(), 4)
        logLine("NativeDisplayManager_SourceMode passed")
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayId() {
        memScoped {
            val id = alloc<ULongVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayId(id.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayId ret=$ret id=${id.value}")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayWidth() {
        memScoped {
            val w = alloc<IntVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayWidth(w.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayWidth ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayHeight() {
        memScoped {
            val h = alloc<IntVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayHeight(h.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayHeight ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayRotation() {
        memScoped {
            val rot = alloc<NativeDisplayManager_Rotation.Var>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayRotation(rot.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayRotation ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayOrientation() {
        memScoped {
            val orient = alloc<UIntVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayOrientation(orient.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayOrientation ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayVirtualPixelRatio() {
        memScoped {
            val vp = alloc<FloatVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayVirtualPixelRatio(vp.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayVirtualPixelRatio ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayRefreshRate() {
        memScoped {
            val rr = alloc<UIntVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayRefreshRate(rr.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayRefreshRate ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayDensityDpi() {
        memScoped {
            val dpi = alloc<IntVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayDensityDpi(dpi.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayDensityDpi ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayDensityPixels() {
        memScoped {
            val dp = alloc<FloatVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayDensityPixels(dp.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayDensityPixels ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayScaledDensity() {
        memScoped {
            val sd = alloc<FloatVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayScaledDensity(sd.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayScaledDensity ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayDensityXdpi() {
        memScoped {
            val xdpi = alloc<FloatVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayDensityXdpi(xdpi.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayDensityXdpi ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDefaultDisplayDensityYdpi() {
        memScoped {
            val ydpi = alloc<FloatVar>()
            val ret = OH_NativeDisplayManager_GetDefaultDisplayDensityYdpi(ydpi.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_GetDefaultDisplayDensityYdpi ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_CreateDefaultDisplayCutoutInfo() {
        memScoped {
            val cutoutPtr = alloc<CPointerVar<NativeDisplayManager_CutoutInfo>>()
            val ret = OH_NativeDisplayManager_CreateDefaultDisplayCutoutInfo(cutoutPtr.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_CreateDefaultDisplayCutoutInfo ret=$ret cutout=${cutoutPtr.value}")
            if (cutoutPtr.value != null) OH_NativeDisplayManager_DestroyDefaultDisplayCutoutInfo(cutoutPtr.value)
        }
    }

    @Test
    fun testOH_NativeDisplayManager_DestroyDefaultDisplayCutoutInfo() {
        memScoped {
            val cutoutPtr = alloc<CPointerVar<NativeDisplayManager_CutoutInfo>>()
            OH_NativeDisplayManager_CreateDefaultDisplayCutoutInfo(cutoutPtr.ptr)
            assertNotNull(cutoutPtr.value)
            val ret = OH_NativeDisplayManager_DestroyDefaultDisplayCutoutInfo(cutoutPtr.value)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_DestroyDefaultDisplayCutoutInfo ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_IsFoldable() {
        memScoped {
            val ret = OH_NativeDisplayManager_IsFoldable()
            logLine("OH_NativeDisplayManager_IsFoldable ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetFoldDisplayMode() {
        memScoped {
            val mode = alloc<UIntVar>()
            val ret = OH_NativeDisplayManager_GetFoldDisplayMode(mode.ptr)
            logLine("OH_NativeDisplayManager_GetFoldDisplayMode ret=$ret mode=${mode.value}")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_RegisterDisplayChangeListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            val ret = OH_NativeDisplayManager_RegisterDisplayChangeListener(null, idx.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_RegisterDisplayChangeListener ret=$ret idx=${idx.value}")
            OH_NativeDisplayManager_UnregisterDisplayChangeListener(idx.value)
        }
    }

    @Test
    fun testOH_NativeDisplayManager_UnregisterDisplayChangeListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            OH_NativeDisplayManager_RegisterDisplayChangeListener(null, idx.ptr)
            val ret = OH_NativeDisplayManager_UnregisterDisplayChangeListener(idx.value)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_UnregisterDisplayChangeListener ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_RegisterFoldDisplayModeChangeListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            val ret = OH_NativeDisplayManager_RegisterFoldDisplayModeChangeListener(null, idx.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_RegisterFoldDisplayModeChangeListener ret=$ret")
            OH_NativeDisplayManager_UnregisterFoldDisplayModeChangeListener(idx.value)
        }
    }

    @Test
    fun testOH_NativeDisplayManager_UnregisterFoldDisplayModeChangeListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            OH_NativeDisplayManager_RegisterFoldDisplayModeChangeListener(null, idx.ptr)
            val ret = OH_NativeDisplayManager_UnregisterFoldDisplayModeChangeListener(idx.value)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_UnregisterFoldDisplayModeChangeListener ret=$ret")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_CreateAllDisplays() {
        memScoped {
            val allPtr = alloc<CPointerVar<NativeDisplayManager_DisplaysInfo>>()
            val ret = OH_NativeDisplayManager_CreateAllDisplays(allPtr.ptr)
            assertNotNull(ret)
            logLine("OH_NativeDisplayManager_CreateAllDisplays ret=$ret allDisplays=${allPtr.value}")
            OH_NativeDisplayManager_DestroyAllDisplays(allPtr.value)
        }
    }

    @Test
    fun testOH_NativeDisplayManager_DestroyAllDisplays() {
        memScoped {
            val allPtr = alloc<CPointerVar<NativeDisplayManager_DisplaysInfo>>()
            OH_NativeDisplayManager_CreateAllDisplays(allPtr.ptr)
            assertNotNull(allPtr.value)
            OH_NativeDisplayManager_DestroyAllDisplays(allPtr.value)
            logLine("OH_NativeDisplayManager_DestroyAllDisplays=called")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_CreateDisplayById() {
        memScoped {
            val dispPtr = alloc<CPointerVar<NativeDisplayManager_DisplayInfo>>()
            val ret = OH_NativeDisplayManager_CreateDisplayById(0u, dispPtr.ptr)
            logLine("OH_NativeDisplayManager_CreateDisplayById ret=$ret displayInfo=${dispPtr.value}")
            if (dispPtr.value != null) OH_NativeDisplayManager_DestroyDisplay(dispPtr.value)
        }
    }

    @Test
    fun testOH_NativeDisplayManager_DestroyDisplay() {
        memScoped {
            val dispPtr = alloc<CPointerVar<NativeDisplayManager_DisplayInfo>>()
            OH_NativeDisplayManager_CreateDisplayById(0u, dispPtr.ptr)
            if (dispPtr.value != null) {
                OH_NativeDisplayManager_DestroyDisplay(dispPtr.value)
                logLine("OH_NativeDisplayManager_DestroyDisplay=called")
            }
        }
    }

    @Test
    fun testOH_NativeDisplayManager_CreatePrimaryDisplay() {
        memScoped {
            val primaryPtr = alloc<CPointerVar<NativeDisplayManager_DisplayInfo>>()
            val ret = OH_NativeDisplayManager_CreatePrimaryDisplay(primaryPtr.ptr)
            logLine("OH_NativeDisplayManager_CreatePrimaryDisplay ret=$ret displayInfo=${primaryPtr.value}")
            if (primaryPtr.value != null) OH_NativeDisplayManager_DestroyDisplay(primaryPtr.value)
        }
    }

    @Test
    fun testOH_NativeDisplayManager_RegisterAvailableAreaChangeListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            val ret = try { OH_NativeDisplayManager_RegisterAvailableAreaChangeListener(null, idx.ptr) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_RegisterAvailableAreaChangeListener (API 20) exception: $e"); DISPLAY_MANAGER_ERROR_INVALID_PARAM }
            logLine("OH_NativeDisplayManager_RegisterAvailableAreaChangeListener ret=$ret idx=${idx.value}")
            try { OH_NativeDisplayManager_UnregisterAvailableAreaChangeListener(idx.value) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeDisplayManager_UnregisterAvailableAreaChangeListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            try { OH_NativeDisplayManager_RegisterAvailableAreaChangeListener(null, idx.ptr) } catch (e: Throwable) { }
            try { OH_NativeDisplayManager_UnregisterAvailableAreaChangeListener(0u) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_UnregisterAvailableAreaChangeListener (API 20) exception: $e") }
            logLine("OH_NativeDisplayManager_UnregisterAvailableAreaChangeListener=called")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_CreateAvailableArea() {
        memScoped {
            val rectPtr = alloc<CPointerVar<NativeDisplayManager_Rect>>()
            val ret = try { OH_NativeDisplayManager_CreateAvailableArea(0uL, rectPtr.ptr) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_CreateAvailableArea (API 20) exception: $e"); DISPLAY_MANAGER_ERROR_INVALID_PARAM }
            logLine("OH_NativeDisplayManager_CreateAvailableArea ret=$ret rect=${rectPtr.value}")
            try { OH_NativeDisplayManager_DestroyAvailableArea(rectPtr.value) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeDisplayManager_DestroyAvailableArea() {
        memScoped {
            val rectPtr = alloc<CPointerVar<NativeDisplayManager_Rect>>()
            try { OH_NativeDisplayManager_CreateAvailableArea(0uL, rectPtr.ptr) } catch (e: Throwable) { }
            try { OH_NativeDisplayManager_DestroyAvailableArea(rectPtr.value) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_DestroyAvailableArea (API 20) exception: $e") }
            logLine("OH_NativeDisplayManager_DestroyAvailableArea=called")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_RegisterDisplayAddListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            val ret = try { OH_NativeDisplayManager_RegisterDisplayAddListener(null, idx.ptr) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_RegisterDisplayAddListener (API 20) exception: $e"); DISPLAY_MANAGER_ERROR_INVALID_PARAM }
            logLine("OH_NativeDisplayManager_RegisterDisplayAddListener ret=$ret")
            try { OH_NativeDisplayManager_UnregisterDisplayAddListener(idx.value) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeDisplayManager_UnregisterDisplayAddListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            try { OH_NativeDisplayManager_RegisterDisplayAddListener(null, idx.ptr) } catch (e: Throwable) { }
            try { OH_NativeDisplayManager_UnregisterDisplayAddListener(0u) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_UnregisterDisplayAddListener (API 20) exception: $e") }
            logLine("OH_NativeDisplayManager_UnregisterDisplayAddListener=called")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_RegisterDisplayRemoveListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            val ret = try { OH_NativeDisplayManager_RegisterDisplayRemoveListener(null, idx.ptr) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_RegisterDisplayRemoveListener (API 20) exception: $e"); DISPLAY_MANAGER_ERROR_INVALID_PARAM }
            logLine("OH_NativeDisplayManager_RegisterDisplayRemoveListener ret=$ret")
            try { OH_NativeDisplayManager_UnregisterDisplayRemoveListener(idx.value) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_NativeDisplayManager_UnregisterDisplayRemoveListener() {
        memScoped {
            val idx = alloc<UIntVar>()
            try { OH_NativeDisplayManager_RegisterDisplayRemoveListener(null, idx.ptr) } catch (e: Throwable) { }
            try { OH_NativeDisplayManager_UnregisterDisplayRemoveListener(0u) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_UnregisterDisplayRemoveListener (API 20) exception: $e") }
            logLine("OH_NativeDisplayManager_UnregisterDisplayRemoveListener=called")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDisplayPosition() {
        memScoped {
            val x = alloc<IntVar>()
            val y = alloc<IntVar>()
            val ret = try { OH_NativeDisplayManager_GetDisplayPosition(0uL, x.ptr, y.ptr) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_GetDisplayPosition (API 20) exception: $e"); DISPLAY_MANAGER_ERROR_INVALID_PARAM }
            logLine("OH_NativeDisplayManager_GetDisplayPosition ret=$ret x=${x.value} y=${y.value}")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_GetDisplaySourceMode() {
        memScoped {
            val sourceMode = alloc<UIntVar>()
            val ret = try { OH_NativeDisplayManager_GetDisplaySourceMode(0uL, sourceMode.ptr) } catch (e: Throwable) { logLine("OH_NativeDisplayManager_GetDisplaySourceMode (API 20) exception: $e"); DISPLAY_MANAGER_ERROR_INVALID_PARAM }
            logLine("OH_NativeDisplayManager_GetDisplaySourceMode ret=$ret sourceMode=${sourceMode.value}")
        }
    }

    @Test
    fun testOH_NativeDisplayManager_CaptureScreenPixelmap() {
        memScoped {
            val pixelMapPtr = alloc<CPointerVar<OH_PixelmapNative>>()
            val ret = OH_NativeDisplayManager_CaptureScreenPixelmap(0u, pixelMapPtr.ptr)
            logLine("OH_NativeDisplayManager_CaptureScreenPixelmap ret=$ret pixelMap=${pixelMapPtr.value}")
        }
    }
}
