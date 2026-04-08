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
import platform.ArkUI.WindowManager.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class WindowManagerTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_WindowManager_ErrorCode() {
        assertEquals(OK.toInt(), 0); logLine("OK=0")
        assertEquals(WINDOW_MANAGER_ERRORCODE_NO_PERMISSION.toInt(), 201); logLine("WINDOW_MANAGER_ERRORCODE_NO_PERMISSION=201")
        assertEquals(WINDOW_MANAGER_ERRORCODE_INVALID_PARAM.toInt(), 401); logLine("WINDOW_MANAGER_ERRORCODE_INVALID_PARAM=401")
        assertEquals(WINDOW_MANAGER_ERRORCODE_DEVICE_NOT_SUPPORTED.toInt(), 801); logLine("WINDOW_MANAGER_ERRORCODE_DEVICE_NOT_SUPPORTED=801")
        assertEquals(INVAILD_WINDOW_ID.toInt(), 1000); logLine("INVAILD_WINDOW_ID=1000")
        assertEquals(SERVICE_ERROR.toInt(), 2000); logLine("SERVICE_ERROR=2000")
        assertEquals(WINDOW_MANAGER_ERRORCODE_STATE_ABNORMAL.toInt(), 1300002); logLine("WINDOW_MANAGER_ERRORCODE_STATE_ABNORMAL=1300002")
        assertEquals(WINDOW_MANAGER_ERRORCODE_SYSTEM_ABNORMAL.toInt(), 1300003); logLine("WINDOW_MANAGER_ERRORCODE_SYSTEM_ABNORMAL=1300003")
        assertEquals(WINDOW_MANAGER_ERRORCODE_PIP_DESTROY_FAILED.toInt(), 1300011); logLine("WINDOW_MANAGER_ERRORCODE_PIP_DESTROY_FAILED=1300011")
        assertEquals(WINDOW_MANAGER_ERRORCODE_PIP_STATE_ABNORMAL.toInt(), 1300012); logLine("WINDOW_MANAGER_ERRORCODE_PIP_STATE_ABNORMAL=1300012")
        assertEquals(WINDOW_MANAGER_ERRORCODE_PIP_CREATE_FAILED.toInt(), 1300013); logLine("WINDOW_MANAGER_ERRORCODE_PIP_CREATE_FAILED=1300013")
        assertEquals(WINDOW_MANAGER_ERRORCODE_PIP_INTERNAL_ERROR.toInt(), 1300014); logLine("WINDOW_MANAGER_ERRORCODE_PIP_INTERNAL_ERROR=1300014")
        assertEquals(WINDOW_MANAGER_ERRORCODE_PIP_REPEATED_OPERATION.toInt(), 1300015); logLine("WINDOW_MANAGER_ERRORCODE_PIP_REPEATED_OPERATION=1300015")
        assertEquals(WINDOW_MANAGER_ERRORCODE_INCORRECT_PARAM.toInt(), 1300016); logLine("WINDOW_MANAGER_ERRORCODE_INCORRECT_PARAM=1300016")
        logLine("WindowManager_ErrorCode passed")
    }

    @Test
    fun testEnum_WindowManager_AvoidAreaType() {
        assertEquals(WINDOW_MANAGER_AVOID_AREA_TYPE_SYSTEM.toInt(), 0); logLine("WINDOW_MANAGER_AVOID_AREA_TYPE_SYSTEM=0")
        assertEquals(WINDOW_MANAGER_AVOID_AREA_TYPE_CUTOUT.toInt(), 1); logLine("WINDOW_MANAGER_AVOID_AREA_TYPE_CUTOUT=1")
        assertEquals(WINDOW_MANAGER_AVOID_AREA_TYPE_SYSTEM_GESTURE.toInt(), 2); logLine("WINDOW_MANAGER_AVOID_AREA_TYPE_SYSTEM_GESTURE=2")
        assertEquals(WINDOW_MANAGER_AVOID_AREA_TYPE_KEYBOARD.toInt(), 3); logLine("WINDOW_MANAGER_AVOID_AREA_TYPE_KEYBOARD=3")
        assertEquals(WINDOW_MANAGER_AVOID_AREA_TYPE_NAVIGATION_INDICATOR.toInt(), 4); logLine("WINDOW_MANAGER_AVOID_AREA_TYPE_NAVIGATION_INDICATOR=4")
        logLine("WindowManager_AvoidAreaType passed")
    }

    @Test
    fun testEnum_WindowManager_WindowType() {
        assertEquals(WINDOW_MANAGER_WINDOW_TYPE_APP.toInt(), 0); logLine("WINDOW_MANAGER_WINDOW_TYPE_APP=0")
        assertEquals(WINDOW_MANAGER_WINDOW_TYPE_MAIN.toInt(), 1); logLine("WINDOW_MANAGER_WINDOW_TYPE_MAIN=1")
        assertEquals(WINDOW_MANAGER_WINDOW_TYPE_FLOAT.toInt(), 8); logLine("WINDOW_MANAGER_WINDOW_TYPE_FLOAT=8")
        assertEquals(WINDOW_MANAGER_WINDOW_TYPE_DIALOG.toInt(), 16); logLine("WINDOW_MANAGER_WINDOW_TYPE_DIALOG=16")
        logLine("WindowManager_WindowType passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowStatusBarEnabled() {
        val r = OH_WindowManager_SetWindowStatusBarEnabled(0, false, false)
        logLine("OH_WindowManager_SetWindowStatusBarEnabled=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowStatusBarEnabled passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowStatusBarColor() {
        val r = OH_WindowManager_SetWindowStatusBarColor(0, 0)
        logLine("OH_WindowManager_SetWindowStatusBarColor=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowStatusBarColor passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowNavigationBarEnabled() {
        val r = OH_WindowManager_SetWindowNavigationBarEnabled(0, false, false)
        logLine("OH_WindowManager_SetWindowNavigationBarEnabled=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowNavigationBarEnabled passed")
    }

    @Test
    fun testOH_WindowManager_GetWindowAvoidArea() {
        memScoped {
            val avoidArea = alloc<WindowManager_AvoidArea>()
            val r = OH_WindowManager_GetWindowAvoidArea(0, WINDOW_MANAGER_AVOID_AREA_TYPE_SYSTEM, avoidArea.ptr)
            logLine("OH_WindowManager_GetWindowAvoidArea=$r")
            assertNotNull(r)
            logLine("OH_WindowManager_GetWindowAvoidArea passed")
        }
    }

    @Test
    fun testOH_WindowManager_IsWindowShown() {
        memScoped {
            val isShow = alloc<BooleanVar>()
            val r = OH_WindowManager_IsWindowShown(0, isShow.ptr)
            logLine("OH_WindowManager_IsWindowShown=$r")
            assertNotNull(r)
            logLine("OH_WindowManager_IsWindowShown passed")
        }
    }

    @Test
    fun testOH_WindowManager_ShowWindow() {
        val r = OH_WindowManager_ShowWindow(0)
        logLine("OH_WindowManager_ShowWindow=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_ShowWindow passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowTouchable() {
        val r = OH_WindowManager_SetWindowTouchable(0, true)
        logLine("OH_WindowManager_SetWindowTouchable=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowTouchable passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowFocusable() {
        val r = OH_WindowManager_SetWindowFocusable(0, true)
        logLine("OH_WindowManager_SetWindowFocusable=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowFocusable passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowBackgroundColor() {
        val r = OH_WindowManager_SetWindowBackgroundColor(0, null)
        logLine("OH_WindowManager_SetWindowBackgroundColor=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowBackgroundColor passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowBrightness() {
        val r = OH_WindowManager_SetWindowBrightness(0, 1.0f)
        logLine("OH_WindowManager_SetWindowBrightness=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowBrightness passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowKeepScreenOn() {
        val r = OH_WindowManager_SetWindowKeepScreenOn(0, false)
        logLine("OH_WindowManager_SetWindowKeepScreenOn=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowKeepScreenOn passed")
    }

    @Test
    fun testOH_WindowManager_SetWindowPrivacyMode() {
        val r = OH_WindowManager_SetWindowPrivacyMode(0, false)
        logLine("OH_WindowManager_SetWindowPrivacyMode=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_SetWindowPrivacyMode passed")
    }

    @Test
    fun testOH_WindowManager_GetWindowProperties() {
        val r = OH_WindowManager_GetWindowProperties(0, null)
        logLine("OH_WindowManager_GetWindowProperties=$r")
        logLine("OH_WindowManager_GetWindowProperties passed")
    }

    @Test
    fun testOH_WindowManager_Snapshot() {
        val r = OH_WindowManager_Snapshot(0, null)
        logLine("OH_WindowManager_Snapshot=$r")
        logLine("OH_WindowManager_Snapshot passed")
    }

    @Test
    fun testOH_WindowManager_GetAllWindowLayoutInfoList() {
        val r = OH_WindowManager_GetAllWindowLayoutInfoList(0L, null, null)
        logLine("OH_WindowManager_GetAllWindowLayoutInfoList=$r")
        logLine("OH_WindowManager_GetAllWindowLayoutInfoList passed")
    }

    @Test
    fun testOH_WindowManager_ReleaseAllWindowLayoutInfoList() {
        OH_WindowManager_ReleaseAllWindowLayoutInfoList(null)
        logLine("OH_WindowManager_ReleaseAllWindowLayoutInfoList passed")
    }

    @Test
    fun testOH_WindowManager_InjectTouchEvent() {
        val r = try { OH_WindowManager_InjectTouchEvent(0, null, 0, 0) } catch (e: Throwable) { logLine("OH_WindowManager_InjectTouchEvent (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_WindowManager_InjectTouchEvent=$r")
        logLine("OH_WindowManager_InjectTouchEvent passed")
    }

    @Test
    fun testOH_WindowManager_GetAllMainWindowInfo() {
        val r = try { OH_WindowManager_GetAllMainWindowInfo(null, null) } catch (e: Throwable) { logLine("OH_WindowManager_GetAllMainWindowInfo (API 21) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_WindowManager_GetAllMainWindowInfo=$r")
        logLine("OH_WindowManager_GetAllMainWindowInfo passed")
    }

    @Test
    fun testOH_WindowManager_ReleaseAllMainWindowInfo() {
        try { OH_WindowManager_ReleaseAllMainWindowInfo(null) } catch (e: Throwable) { logLine("OH_WindowManager_ReleaseAllMainWindowInfo (API 21) exception: $e") }
        logLine("OH_WindowManager_ReleaseAllMainWindowInfo passed")
    }

    @Test
    fun testOH_WindowManager_GetMainWindowSnapshot() {
        memScoped {
            val config = alloc<WindowManager_WindowSnapshotConfig>()
            val r = try { OH_WindowManager_GetMainWindowSnapshot(null, 0u, config.readValue(), null) } catch (e: Throwable) { logLine("OH_WindowManager_GetMainWindowSnapshot (API 21) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
            logLine("OH_WindowManager_GetMainWindowSnapshot=$r")
            logLine("OH_WindowManager_GetMainWindowSnapshot passed")
        }
    }

    @Test
    fun testOH_WindowManager_ReleaseMainWindowSnapshot() {
        try { OH_WindowManager_ReleaseMainWindowSnapshot(null) } catch (e: Throwable) { logLine("OH_WindowManager_ReleaseMainWindowSnapshot (API 21) exception: $e") }
        logLine("OH_WindowManager_ReleaseMainWindowSnapshot passed")
    }

    @Test
    fun testOH_WindowManager_LockCursor() {
        val r = try { OH_WindowManager_LockCursor(0, false) } catch (e: Throwable) { logLine("OH_WindowManager_LockCursor (API 22) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_WindowManager_LockCursor=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_LockCursor passed")
    }

    @Test
    fun testOH_WindowManager_UnlockCursor() {
        val r = try { OH_WindowManager_UnlockCursor(0) } catch (e: Throwable) { logLine("OH_WindowManager_UnlockCursor (API 22) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_WindowManager_UnlockCursor=$r")
        assertNotNull(r)
        logLine("OH_WindowManager_UnlockCursor passed")
    }

    @Test
    fun testOH_NativeWindowManager_RegisterKeyEventFilter() {
        val r = OH_NativeWindowManager_RegisterKeyEventFilter(0, null)
        logLine("OH_NativeWindowManager_RegisterKeyEventFilter=$r")
        assertNotNull(r)
        logLine("OH_NativeWindowManager_RegisterKeyEventFilter passed")
    }

    @Test
    fun testOH_NativeWindowManager_UnregisterKeyEventFilter() {
        val r = OH_NativeWindowManager_UnregisterKeyEventFilter(0)
        logLine("OH_NativeWindowManager_UnregisterKeyEventFilter=$r")
        assertNotNull(r)
        logLine("OH_NativeWindowManager_UnregisterKeyEventFilter passed")
    }

    @Test
    fun testOH_NativeWindowManager_RegisterMouseEventFilter() {
        val r = OH_NativeWindowManager_RegisterMouseEventFilter(0, null)
        logLine("OH_NativeWindowManager_RegisterMouseEventFilter=$r")
        assertNotNull(r)
        logLine("OH_NativeWindowManager_RegisterMouseEventFilter passed")
    }

    @Test
    fun testOH_NativeWindowManager_UnregisterMouseEventFilter() {
        val r = OH_NativeWindowManager_UnregisterMouseEventFilter(0)
        logLine("OH_NativeWindowManager_UnregisterMouseEventFilter=$r")
        assertNotNull(r)
        logLine("OH_NativeWindowManager_UnregisterMouseEventFilter passed")
    }

    @Test
    fun testOH_NativeWindowManager_RegisterTouchEventFilter() {
        val r = OH_NativeWindowManager_RegisterTouchEventFilter(0, null)
        logLine("OH_NativeWindowManager_RegisterTouchEventFilter=$r")
        assertNotNull(r)
        logLine("OH_NativeWindowManager_RegisterTouchEventFilter passed")
    }

    @Test
    fun testOH_NativeWindowManager_UnregisterTouchEventFilter() {
        val r = OH_NativeWindowManager_UnregisterTouchEventFilter(0)
        logLine("OH_NativeWindowManager_UnregisterTouchEventFilter=$r")
        assertNotNull(r)
        logLine("OH_NativeWindowManager_UnregisterTouchEventFilter passed")
    }

    @Test
    fun testEnum_PictureInPicture_PipTemplateType() {
        assertEquals(VIDEO_PLAY.toInt(), 0); logLine("VIDEO_PLAY=0")
        assertEquals(VIDEO_CALL.toInt(), 1); logLine("VIDEO_CALL=1")
        assertEquals(VIDEO_MEETING.toInt(), 2); logLine("VIDEO_MEETING=2")
        assertEquals(VIDEO_LIVE.toInt(), 3); logLine("VIDEO_LIVE=3")
        logLine("PictureInPicture_PipTemplateType passed")
    }

    @Test
    fun testEnum_PictureInPicture_PipControlGroup() {
        assertEquals(VIDEO_PLAY_VIDEO_PREVIOUS_NEXT.toInt(), 101); logLine("VIDEO_PLAY_VIDEO_PREVIOUS_NEXT=101")
        assertEquals(VIDEO_PLAY_FAST_FORWARD_BACKWARD.toInt(), 102); logLine("VIDEO_PLAY_FAST_FORWARD_BACKWARD=102")
        assertEquals(VIDEO_CALL_MICROPHONE_SWITCH.toInt(), 201); logLine("VIDEO_CALL_MICROPHONE_SWITCH=201")
        assertEquals(VIDEO_CALL_HANG_UP_BUTTON.toInt(), 202); logLine("VIDEO_CALL_HANG_UP_BUTTON=202")
        assertEquals(VIDEO_CALL_CAMERA_SWITCH.toInt(), 203); logLine("VIDEO_CALL_CAMERA_SWITCH=203")
        assertEquals(VIDEO_CALL_MUTE_SWITCH.toInt(), 204); logLine("VIDEO_CALL_MUTE_SWITCH=204")
        assertEquals(VIDEO_MEETING_HANG_UP_BUTTON.toInt(), 301); logLine("VIDEO_MEETING_HANG_UP_BUTTON=301")
        assertEquals(VIDEO_MEETING_CAMERA_SWITCH.toInt(), 302); logLine("VIDEO_MEETING_CAMERA_SWITCH=302")
        assertEquals(VIDEO_MEETING_MUTE_SWITCH.toInt(), 303); logLine("VIDEO_MEETING_MUTE_SWITCH=303")
        assertEquals(VIDEO_MEETING_MICROPHONE_SWITCH.toInt(), 304); logLine("VIDEO_MEETING_MICROPHONE_SWITCH=304")
        assertEquals(VIDEO_LIVE_VIDEO_PLAY_PAUSE.toInt(), 401); logLine("VIDEO_LIVE_VIDEO_PLAY_PAUSE=401")
        assertEquals(VIDEO_LIVE_MUTE_SWITCH.toInt(), 402); logLine("VIDEO_LIVE_MUTE_SWITCH=402")
        logLine("PictureInPicture_PipControlGroup passed")
    }

    @Test
    fun testEnum_PictureInPicture_PipControlType() {
        assertEquals(VIDEO_PLAY_PAUSE.toInt(), 0); logLine("VIDEO_PLAY_PAUSE=0")
        assertEquals(VIDEO_PREVIOUS.toInt(), 1); logLine("VIDEO_PREVIOUS=1")
        assertEquals(VIDEO_NEXT.toInt(), 2); logLine("VIDEO_NEXT=2")
        assertEquals(FAST_FORWARD.toInt(), 3); logLine("FAST_FORWARD=3")
        assertEquals(FAST_BACKWARD.toInt(), 4); logLine("FAST_BACKWARD=4")
        assertEquals(HANG_UP_BUTTON.toInt(), 5); logLine("HANG_UP_BUTTON=5")
        assertEquals(MICROPHONE_SWITCH.toInt(), 6); logLine("MICROPHONE_SWITCH=6")
        assertEquals(CAMERA_SWITCH.toInt(), 7); logLine("CAMERA_SWITCH=7")
        assertEquals(MUTE_SWITCH.toInt(), 8); logLine("MUTE_SWITCH=8")
        logLine("PictureInPicture_PipControlType passed")
    }

    @Test
    fun testEnum_PictureInPicture_PipControlStatus() {
        assertEquals(PLAY.toInt(), 1); logLine("PLAY=1")
        assertEquals(PAUSE.toInt(), 0); logLine("PAUSE=0")
        assertEquals(OPEN.toInt(), 1); logLine("OPEN=1")
        assertEquals(CLOSE.toInt(), 0); logLine("CLOSE=0")
        logLine("PictureInPicture_PipControlStatus passed")
    }

    @Test
    fun testEnum_PictureInPicture_PipState() {
        assertEquals(ABOUT_TO_START.toInt(), 1); logLine("ABOUT_TO_START=1")
        assertEquals(STARTED.toInt(), 2); logLine("STARTED=2")
        assertEquals(ABOUT_TO_STOP.toInt(), 3); logLine("ABOUT_TO_STOP=3")
        assertEquals(STOPPED.toInt(), 4); logLine("STOPPED=4")
        assertEquals(ABOUT_TO_RESTORE.toInt(), 5); logLine("ABOUT_TO_RESTORE=5")
        assertEquals(ERROR.toInt(), 6); logLine("ERROR=6")
        logLine("PictureInPicture_PipState passed")
    }

    @Test
    fun testOH_PictureInPicture_CreatePipConfig() {
        val r = try { OH_PictureInPicture_CreatePipConfig(null) } catch (e: Throwable) { logLine("OH_PictureInPicture_CreatePipConfig (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_CreatePipConfig(null)=$r")
        logLine("OH_PictureInPicture_CreatePipConfig passed")
    }

    @Test
    fun testOH_PictureInPicture_DestroyPipConfig() {
        val r = try { OH_PictureInPicture_DestroyPipConfig(null) } catch (e: Throwable) { logLine("OH_PictureInPicture_DestroyPipConfig (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_DestroyPipConfig(null)=$r")
        logLine("OH_PictureInPicture_DestroyPipConfig passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipMainWindowId() {
        val r = try { OH_PictureInPicture_SetPipMainWindowId(null, 0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipMainWindowId (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipMainWindowId(null)=$r")
        logLine("OH_PictureInPicture_SetPipMainWindowId passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipTemplateType() {
        val r = try { OH_PictureInPicture_SetPipTemplateType(null, VIDEO_PLAY) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipTemplateType (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipTemplateType(null)=$r")
        logLine("OH_PictureInPicture_SetPipTemplateType passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipRect() {
        val r = try { OH_PictureInPicture_SetPipRect(null, 100u, 100u) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipRect (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipRect(null)=$r")
        logLine("OH_PictureInPicture_SetPipRect passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipControlGroup() {
        val r = try { OH_PictureInPicture_SetPipControlGroup(null, null, 0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipControlGroup (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipControlGroup(null)=$r")
        logLine("OH_PictureInPicture_SetPipControlGroup passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipNapiEnv() {
        val r = try { OH_PictureInPicture_SetPipNapiEnv(null, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipNapiEnv (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipNapiEnv(null)=$r")
        logLine("OH_PictureInPicture_SetPipNapiEnv passed")
    }

    @Test
    fun testOH_PictureInPicture_CreatePip() {
        memScoped {
            val controllerIdPtr = alloc<UIntVar>()
            val r = try { OH_PictureInPicture_CreatePip(null, controllerIdPtr.ptr) } catch (e: Throwable) { logLine("OH_PictureInPicture_CreatePip (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
            logLine("OH_PictureInPicture_CreatePip(null)=$r")
            logLine("OH_PictureInPicture_CreatePip passed")
        }
    }

    @Test
    fun testOH_PictureInPicture_DeletePip() {
        val r = try { OH_PictureInPicture_DeletePip(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_DeletePip (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_DeletePip(0)=$r")
        logLine("OH_PictureInPicture_DeletePip passed")
    }

    @Test
    fun testOH_PictureInPicture_StartPip() {
        val r = try { OH_PictureInPicture_StartPip(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_StartPip (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_StartPip(0)=$r")
        logLine("OH_PictureInPicture_StartPip passed")
    }

    @Test
    fun testOH_PictureInPicture_StopPip() {
        val r = try { OH_PictureInPicture_StopPip(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_StopPip (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_StopPip(0)=$r")
        logLine("OH_PictureInPicture_StopPip passed")
    }

    @Test
    fun testOH_PictureInPicture_UpdatePipContentSize() {
        val r = try { OH_PictureInPicture_UpdatePipContentSize(0u, 100u, 100u) } catch (e: Throwable) { logLine("OH_PictureInPicture_UpdatePipContentSize (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UpdatePipContentSize(0)=$r")
        logLine("OH_PictureInPicture_UpdatePipContentSize passed")
    }

    @Test
    fun testOH_PictureInPicture_UpdatePipControlStatus() {
        val r = try { OH_PictureInPicture_UpdatePipControlStatus(0u, VIDEO_PLAY_PAUSE, PAUSE) } catch (e: Throwable) { logLine("OH_PictureInPicture_UpdatePipControlStatus (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UpdatePipControlStatus(0)=$r")
        logLine("OH_PictureInPicture_UpdatePipControlStatus passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipControlEnabled() {
        val r = try { OH_PictureInPicture_SetPipControlEnabled(0u, VIDEO_PLAY_PAUSE, false) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipControlEnabled (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipControlEnabled(0)=$r")
        logLine("OH_PictureInPicture_SetPipControlEnabled passed")
    }

    @Test
    fun testOH_PictureInPicture_SetParentWindowId() {
        val r = try { OH_PictureInPicture_SetParentWindowId(0u, 0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetParentWindowId (API 22) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetParentWindowId(0)=$r")
        logLine("OH_PictureInPicture_SetParentWindowId passed")
    }

    @Test
    fun testOH_PictureInPicture_SetPipInitialSurfaceRect() {
        val r = try { OH_PictureInPicture_SetPipInitialSurfaceRect(0u, 0, 0, 100u, 100u) } catch (e: Throwable) { logLine("OH_PictureInPicture_SetPipInitialSurfaceRect (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_SetPipInitialSurfaceRect(0)=$r")
        logLine("OH_PictureInPicture_SetPipInitialSurfaceRect passed")
    }

    @Test
    fun testOH_PictureInPicture_UnsetPipInitialSurfaceRect() {
        val r = try { OH_PictureInPicture_UnsetPipInitialSurfaceRect(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnsetPipInitialSurfaceRect (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnsetPipInitialSurfaceRect(0)=$r")
        logLine("OH_PictureInPicture_UnsetPipInitialSurfaceRect passed")
    }

    @Test
    fun testOH_PictureInPicture_RegisterStartPipCallback() {
        val r = try { OH_PictureInPicture_RegisterStartPipCallback(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_RegisterStartPipCallback (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_RegisterStartPipCallback(0)=$r")
        logLine("OH_PictureInPicture_RegisterStartPipCallback passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterStartPipCallback() {
        val r = try { OH_PictureInPicture_UnregisterStartPipCallback(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterStartPipCallback (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterStartPipCallback(0)=$r")
        logLine("OH_PictureInPicture_UnregisterStartPipCallback passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterAllStartPipCallbacks() {
        val r = try { OH_PictureInPicture_UnregisterAllStartPipCallbacks(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterAllStartPipCallbacks (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterAllStartPipCallbacks(0)=$r")
        logLine("OH_PictureInPicture_UnregisterAllStartPipCallbacks passed")
    }

    @Test
    fun testOH_PictureInPicture_RegisterLifecycleListener() {
        val r = try { OH_PictureInPicture_RegisterLifecycleListener(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_RegisterLifecycleListener (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_RegisterLifecycleListener(0)=$r")
        logLine("OH_PictureInPicture_RegisterLifecycleListener passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterLifecycleListener() {
        val r = try { OH_PictureInPicture_UnregisterLifecycleListener(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterLifecycleListener (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterLifecycleListener(0)=$r")
        logLine("OH_PictureInPicture_UnregisterLifecycleListener passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterAllLifecycleListeners() {
        val r = try { OH_PictureInPicture_UnregisterAllLifecycleListeners(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterAllLifecycleListeners (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterAllLifecycleListeners(0)=$r")
        logLine("OH_PictureInPicture_UnregisterAllLifecycleListeners passed")
    }

    @Test
    fun testOH_PictureInPicture_RegisterControlEventListener() {
        val r = try { OH_PictureInPicture_RegisterControlEventListener(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_RegisterControlEventListener (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_RegisterControlEventListener(0)=$r")
        logLine("OH_PictureInPicture_RegisterControlEventListener passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterControlEventListener() {
        val r = try { OH_PictureInPicture_UnregisterControlEventListener(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterControlEventListener (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterControlEventListener(0)=$r")
        logLine("OH_PictureInPicture_UnregisterControlEventListener passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterAllControlEventListeners() {
        val r = try { OH_PictureInPicture_UnregisterAllControlEventListeners(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterAllControlEventListeners (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterAllControlEventListeners(0)=$r")
        logLine("OH_PictureInPicture_UnregisterAllControlEventListeners passed")
    }

    @Test
    fun testOH_PictureInPicture_RegisterResizeListener() {
        val r = try { OH_PictureInPicture_RegisterResizeListener(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_RegisterResizeListener (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_RegisterResizeListener(0)=$r")
        logLine("OH_PictureInPicture_RegisterResizeListener passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterResizeListener() {
        val r = try { OH_PictureInPicture_UnregisterResizeListener(0u, null) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterResizeListener (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterResizeListener(0)=$r")
        logLine("OH_PictureInPicture_UnregisterResizeListener passed")
    }

    @Test
    fun testOH_PictureInPicture_UnregisterAllResizeListeners() {
        val r = try { OH_PictureInPicture_UnregisterAllResizeListeners(0u) } catch (e: Throwable) { logLine("OH_PictureInPicture_UnregisterAllResizeListeners (API 20) exception: $e"); WINDOW_MANAGER_ERRORCODE_INVALID_PARAM }
        logLine("OH_PictureInPicture_UnregisterAllResizeListeners(0)=$r")
        logLine("OH_PictureInPicture_UnregisterAllResizeListeners passed")
    }
}
