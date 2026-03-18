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
import platform.ArkUI.ArkUI_NativeModule.*
import platform.ArkGraphics2D.Drawing.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ArkUI_NativeModuleTest {

    private fun logLine(msg: String) = println(msg)


    // ==================== native_dialog.h ====================

    @Test
    fun testEnum_ArkUI_DismissReason() {
        assertEquals(DIALOG_DISMISS_BACK_PRESS.toInt(), 0)
        assertEquals(DIALOG_DISMISS_TOUCH_OUTSIDE.toInt(), 1)
        assertEquals(DIALOG_DISMISS_CLOSE_BUTTON.toInt(), 2)
        assertEquals(DIALOG_DISMISS_SLIDE_DOWN.toInt(), 3)
        logLine("ArkUI_DismissReason passed")
    }

    @Test
    fun testEnum_ArkUI_DialogState() {
        assertEquals(DIALOG_UNINITIALIZED.toInt(), 0)
        assertEquals(DIALOG_INITIALIZED.toInt(), 1)
        assertEquals(DIALOG_APPEARING.toInt(), 2)
        assertEquals(DIALOG_APPEARED.toInt(), 3)
        assertEquals(DIALOG_DISAPPEARING.toInt(), 4)
        assertEquals(DIALOG_DISAPPEARED.toInt(), 5)
        logLine("ArkUI_DialogState passed")
    }

    @Test
    fun testEnum_ArkUI_LevelMode() {
        assertEquals(ARKUI_LEVEL_MODE_OVERLAY.toInt(), 0)
        assertEquals(ARKUI_LEVEL_MODE_EMBEDDED.toInt(), 1)
        logLine("ArkUI_LevelMode passed")
    }

    @Test
    fun testEnum_ArkUI_ImmersiveMode() {
        assertEquals(ARKUI_IMMERSIVE_MODE_DEFAULT.toInt(), 0)
        assertEquals(ARKUI_IMMERSIVE_MODE_EXTEND.toInt(), 1)
        logLine("ArkUI_ImmersiveMode passed")
    }

    // ==================== drag_and_drop.h ====================

    @Test
    fun testEnum_ArkUI_DragResult() {
        assertEquals(ARKUI_DRAG_RESULT_SUCCESSFUL.toInt(), 0)
        assertEquals(ARKUI_DRAG_RESULT_FAILED.toInt(), 1)
        assertEquals(ARKUI_DRAG_RESULT_CANCELED.toInt(), 2)
        logLine("ArkUI_DragResult passed")
    }

    @Test
    fun testEnum_ArkUI_DropOperation() {
        assertEquals(ARKUI_DROP_OPERATION_COPY.toInt(), 0)
        assertEquals(ARKUI_DROP_OPERATION_MOVE.toInt(), 1)
        logLine("ArkUI_DropOperation passed")
    }

    @Test
    fun testEnum_ArkUI_PreDragStatus() {
        assertEquals(ARKUI_PRE_DRAG_STATUS_UNKNOWN.toInt(), -1)
        assertEquals(ARKUI_PRE_DRAG_STATUS_ACTION_DETECTING.toInt(), 0)
        assertEquals(ARKUI_PRE_DRAG_STATUS_READY_TO_TRIGGER_DRAG.toInt(), 1)
        assertEquals(ARKUI_PRE_DRAG_STATUS_PREVIEW_LIFT_STARTED.toInt(), 2)
        assertEquals(ARKUI_PRE_DRAG_STATUS_PREVIEW_LIFT_FINISHED.toInt(), 3)
        assertEquals(ARKUI_PRE_DRAG_STATUS_PREVIEW_LANDING_STARTED.toInt(), 4)
        assertEquals(ARKUI_PRE_DRAG_STATUS_PREVIEW_LANDING_FINISHED.toInt(), 5)
        assertEquals(ARKUI_PRE_DRAG_STATUS_CANCELED_BEFORE_DRAG.toInt(), 6)
        logLine("ArkUI_PreDragStatus passed")
    }

    @Test
    fun testEnum_ArkUI_DragPreviewScaleMode() {
        assertEquals(ARKUI_DRAG_PREVIEW_SCALE_AUTO.toInt(), 0)
        assertEquals(ARKUI_DRAG_PREVIEW_SCALE_DISABLED.toInt(), 1)
        logLine("ArkUI_DragPreviewScaleMode passed")
    }

    @Test
    fun testEnum_ArkUI_DragStatus() {
        assertEquals(ARKUI_DRAG_STATUS_UNKNOWN.toInt(), -1)
        assertEquals(ARKUI_DRAG_STATUS_STARTED.toInt(), 0)
        assertEquals(ARKUI_DRAG_STATUS_ENDED.toInt(), 1)
        logLine("ArkUI_DragStatus passed")
    }

    // ==================== native_key_event.h ====================

    @Test
    fun testEnum_ArkUI_KeyEvent() {
        assertEquals(ARKUI_KEY_EVENT_UNKNOWN.toInt(), -1)
        assertEquals(ARKUI_KEY_EVENT_DOWN.toInt(), 0)
        assertEquals(ARKUI_KEY_EVENT_UP.toInt(), 1)
        assertEquals(ARKUI_KEY_EVENT_LONG_PRESS.toInt(), 2)
        assertEquals(ARKUI_KEY_EVENT_CLICK.toInt(), 3)
        logLine("ArkUI_KeyEvent passed")
    }

    @Test
    fun testEnum_ArkUI_KeySource() {
        assertEquals(ARKUI_KEY_SOURCE_UNKNOWN.toInt(), 0)
        assertEquals(ARKUI_KEY_SOURCE_TYPE_MOUSE.toInt(), 1)
        assertEquals(ARKUI_KEY_SOURCE_TYPE_KEYBOARD.toInt(), 4)
        assertEquals(ARKUI_KEY_SOURCE_TYPE_JOYSTICK.toInt(), 5)
        logLine("ArkUI_KeySource passed")
    }

    @Test
    fun testEnum_ArkUI_KeyCode_sample() {
        assertEquals(ARKUI_KEYCODE_UNKNOWN.toInt(), -1)
        assertEquals(ARKUI_KEYCODE_FN.toInt(), 0)
        assertEquals(ARKUI_KEYCODE_VOLUME_UP.toInt(), 16)
        assertEquals(ARKUI_KEYCODE_ENTER.toInt(), 2054)
        assertEquals(ARKUI_KEYCODE_ESCAPE.toInt(), 2070)
        logLine("ArkUI_KeyCode sample passed")
    }

    @Test
    fun testEnum_ArkUI_KeyIntension() {
        assertEquals(ARKUI_KEY_INTENSION_UNKNOWN.toInt(), -1)
        assertEquals(ARKUI_KEY_INTENSION_UP.toInt(), 1)
        assertEquals(ARKUI_KEY_INTENSION_DOWN.toInt(), 2)
        assertEquals(ARKUI_KEY_INTENSION_LEFT.toInt(), 3)
        assertEquals(ARKUI_KEY_INTENSION_RIGHT.toInt(), 4)
        assertEquals(ARKUI_KEY_INTENSION_SELECT.toInt(), 5)
        assertEquals(ARKUI_KEY_INTENSION_ESCAPE.toInt(), 6)
        assertEquals(ARKUI_KEY_INTENSION_BACK.toInt(), 7)
        assertEquals(ARKUI_KEY_INTENSION_FORWARD.toInt(), 8)
        assertEquals(ARKUI_KEY_INTENSION_MENU.toInt(), 9)
        assertEquals(ARKUI_KEY_INTENSION_HOME.toInt(), 10)
        assertEquals(ARKUI_KEY_INTENSION_PAGE_UP.toInt(), 11)
        assertEquals(ARKUI_KEY_INTENSION_PAGE_DOWN.toInt(), 12)
        assertEquals(ARKUI_KEY_INTENSION_ZOOM_OUT.toInt(), 13)
        assertEquals(ARKUI_KEY_INTENSION_ZOOM_IN.toInt(), 14)
        assertEquals(ARKUI_KEY_INTENTION_MEDIA_PLAY_PAUSE.toInt(), 100)
        assertEquals(ARKUI_KEY_INTENTION_MEDIA_FAST_FORWARD.toInt(), 101)
        assertEquals(ARKUI_KEY_INTENTION_MEDIA_FAST_PLAYBACK.toInt(), 103)
        assertEquals(ARKUI_KEY_INTENTION_MEDIA_NEXT.toInt(), 104)
        assertEquals(ARKUI_KEY_INTENTION_MEDIA_PREVIOUS.toInt(), 105)
        assertEquals(ARKUI_KEY_INTENTION_MEDIA_MUTE.toInt(), 106)
        assertEquals(ARKUI_KEY_INTENTION_VOLUME_UP.toInt(), 107)
        assertEquals(ARKUI_KEY_INTENTION_VOLUME_DOWN.toInt(), 108)
        assertEquals(ARKUI_KEY_INTENTION_CALL.toInt(), 200)
        assertEquals(ARKUI_KEY_INTENTION_CAMERA.toInt(), 300)
        logLine("ArkUI_KeyIntension passed")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_GetType() {
        val ret = OH_ArkUI_KeyEvent_GetType(null)
        logLine("OH_ArkUI_KeyEvent_GetType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_GetKeyCode() {
        val ret = OH_ArkUI_KeyEvent_GetKeyCode(null)
        logLine("OH_ArkUI_KeyEvent_GetKeyCode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_GetKeyText() {
        val ret = OH_ArkUI_KeyEvent_GetKeyText(null)
        logLine("OH_ArkUI_KeyEvent_GetKeyText(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_GetKeySource() {
        val ret = OH_ArkUI_KeyEvent_GetKeySource(null)
        logLine("OH_ArkUI_KeyEvent_GetKeySource(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_StopPropagation() {
        OH_ArkUI_KeyEvent_StopPropagation(null, false)
        OH_ArkUI_KeyEvent_StopPropagation(null, true)
        logLine("OH_ArkUI_KeyEvent_StopPropagation(null,...) done")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_GetKeyIntensionCode() {
        val ret = OH_ArkUI_KeyEvent_GetKeyIntensionCode(null)
        logLine("OH_ArkUI_KeyEvent_GetKeyIntensionCode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_GetUnicode() {
        val ret = OH_ArkUI_KeyEvent_GetUnicode(null)
        logLine("OH_ArkUI_KeyEvent_GetUnicode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_SetConsumed() {
        OH_ArkUI_KeyEvent_SetConsumed(null, false)
        OH_ArkUI_KeyEvent_SetConsumed(null, true)
        logLine("OH_ArkUI_KeyEvent_SetConsumed(null,...) done")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_Dispatch() {
        OH_ArkUI_KeyEvent_Dispatch(null, null)
        logLine("OH_ArkUI_KeyEvent_Dispatch(null,null) done")
    }

    @Test
    fun testOH_ArkUI_KeyEvent_IsNumLockOn() {
        memScoped {
            val state = alloc<BooleanVar>()
            val ret = try { OH_ArkUI_KeyEvent_IsNumLockOn(null, state.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_KeyEvent_IsNumLockOn (API 19) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_KeyEvent_IsNumLockOn(null,&state)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_KeyEvent_IsCapsLockOn() {
        memScoped {
            val state = alloc<BooleanVar>()
            val ret = try { OH_ArkUI_KeyEvent_IsCapsLockOn(null, state.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_KeyEvent_IsCapsLockOn (API 19) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_KeyEvent_IsCapsLockOn(null,&state)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_KeyEvent_IsScrollLockOn() {
        memScoped {
            val state = alloc<BooleanVar>()
            val ret = try { OH_ArkUI_KeyEvent_IsScrollLockOn(null, state.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_KeyEvent_IsScrollLockOn (API 19) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_KeyEvent_IsScrollLockOn(null,&state)=$ret")
        }
    }

    // ==================== native_gesture.h ====================

    @Test
    fun testEnum_ArkUI_GestureEventActionType() {
        assertEquals(GESTURE_EVENT_ACTION_ACCEPT.toInt(), 0x01)
        assertEquals(GESTURE_EVENT_ACTION_UPDATE.toInt(), 0x02)
        assertEquals(GESTURE_EVENT_ACTION_END.toInt(), 0x04)
        assertEquals(GESTURE_EVENT_ACTION_CANCEL.toInt(), 0x08)
        logLine("ArkUI_GestureEventActionType passed")
    }

    @Test
    fun testEnum_ArkUI_GesturePriority() {
        assertEquals(NORMAL.toInt(), 0)
        assertEquals(PRIORITY.toInt(), 1)
        assertEquals(PARALLEL.toInt(), 2)
        logLine("ArkUI_GesturePriority passed")
    }

    @Test
    fun testEnum_ArkUI_GroupGestureMode() {
        assertEquals(SEQUENTIAL_GROUP.toInt(), 0)
        assertEquals(PARALLEL_GROUP.toInt(), 1)
        assertEquals(EXCLUSIVE_GROUP.toInt(), 2)
        logLine("ArkUI_GroupGestureMode passed")
    }

    @Test
    fun testEnum_ArkUI_GestureDirection() {
        assertEquals(GESTURE_DIRECTION_ALL.toInt(), 0b1111)
        assertEquals(GESTURE_DIRECTION_HORIZONTAL.toInt(), 0b0011)
        assertEquals(GESTURE_DIRECTION_VERTICAL.toInt(), 0b1100)
        assertEquals(GESTURE_DIRECTION_LEFT.toInt(), 0b0001)
        assertEquals(GESTURE_DIRECTION_RIGHT.toInt(), 0b0010)
        assertEquals(GESTURE_DIRECTION_UP.toInt(), 0b0100)
        assertEquals(GESTURE_DIRECTION_DOWN.toInt(), 0b1000)
        assertEquals(GESTURE_DIRECTION_NONE.toInt(), 0)
        logLine("ArkUI_GestureDirection passed")
    }

    @Test
    fun testEnum_ArkUI_GestureMask() {
        assertEquals(NORMAL_GESTURE_MASK.toInt(), 0)
        assertEquals(IGNORE_INTERNAL_GESTURE_MASK.toInt(), 1)
        logLine("ArkUI_GestureMask passed")
    }

    @Test
    fun testEnum_ArkUI_GestureRecognizerType() {
        assertEquals(TAP_GESTURE.toInt(), 0)
        assertEquals(LONG_PRESS_GESTURE.toInt(), 1)
        assertEquals(PAN_GESTURE.toInt(), 2)
        assertEquals(PINCH_GESTURE.toInt(), 3)
        assertEquals(ROTATION_GESTURE.toInt(), 4)
        assertEquals(SWIPE_GESTURE.toInt(), 5)
        assertEquals(GROUP_GESTURE.toInt(), 6)
        assertEquals(CLICK_GESTURE.toInt(), 7)
        assertEquals(DRAG_DROP.toInt(), 8)
        logLine("ArkUI_GestureRecognizerType passed")
    }

    @Test
    fun testEnum_ArkUI_GestureInterruptResult() {
        assertEquals(GESTURE_INTERRUPT_RESULT_CONTINUE.toInt(), 0)
        assertEquals(GESTURE_INTERRUPT_RESULT_REJECT.toInt(), 1)
        logLine("ArkUI_GestureInterruptResult passed")
    }

    @Test
    fun testEnum_ArkUI_GestureRecognizerState() {
        assertEquals(ARKUI_GESTURE_RECOGNIZER_STATE_READY.toInt(), 0)
        assertEquals(ARKUI_GESTURE_RECOGNIZER_STATE_DETECTING.toInt(), 1)
        assertEquals(ARKUI_GESTURE_RECOGNIZER_STATE_PENDING.toInt(), 2)
        assertEquals(ARKUI_GESTURE_RECOGNIZER_STATE_BLOCKED.toInt(), 3)
        assertEquals(ARKUI_GESTURE_RECOGNIZER_STATE_SUCCESSFUL.toInt(), 4)
        assertEquals(ARKUI_GESTURE_RECOGNIZER_STATE_FAILED.toInt(), 5)
        logLine("ArkUI_GestureRecognizerState passed")
    }

    // @Test
    // fun testOH_ArkUI_ParallelInnerGestureEvent_GetUserData() {
    //     val ret = OH_ArkUI_ParallelInnerGestureEvent_GetUserData(null)
    //     logLine("OH_ArkUI_ParallelInnerGestureEvent_GetUserData(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureInterrupter_GetUserData() {
    //     val ret = try { OH_ArkUI_GestureInterrupter_GetUserData(null) } catch (e: Throwable) { logLine("OH_ArkUI_GestureInterrupter_GetUserData (API 18) exception: $e"); null }
    //     logLine("OH_ArkUI_GestureInterrupter_GetUserData(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureInterruptInfo_GetSystemFlag() {
    //     val ret = OH_ArkUI_GestureInterruptInfo_GetSystemFlag(null)
    //     logLine("OH_ArkUI_GestureInterruptInfo_GetSystemFlag(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureInterruptInfo_GetRecognizer() {
    //     val ret = OH_ArkUI_GestureInterruptInfo_GetRecognizer(null)
    //     logLine("OH_ArkUI_GestureInterruptInfo_GetRecognizer(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureInterruptInfo_GetGestureEvent() {
    //     val ret = OH_ArkUI_GestureInterruptInfo_GetGestureEvent(null)
    //     logLine("OH_ArkUI_GestureInterruptInfo_GetGestureEvent(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureInterruptInfo_GetSystemRecognizerType() {
    //     val ret = OH_ArkUI_GestureInterruptInfo_GetSystemRecognizerType(null)
    //     logLine("OH_ArkUI_GestureInterruptInfo_GetSystemRecognizerType(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureInterruptInfo_GetTouchRecognizers() = memScoped {
    //     val recognizers = alloc<CPointerVar<ByteVar>>()
    //     val size = alloc<IntVar>()
    //     val ret = OH_ArkUI_GestureInterruptInfo_GetTouchRecognizers(null, recognizers.ptr.reinterpret(), size.ptr)
    //     logLine("OH_ArkUI_GestureInterruptInfo_GetTouchRecognizers(null,&recognizers,&size)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_TouchRecognizer_GetNodeHandle() {
    //     val ret = OH_ArkUI_TouchRecognizer_GetNodeHandle(null)
    //     logLine("OH_ArkUI_TouchRecognizer_GetNodeHandle(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_TouchRecognizer_CancelTouch() {
    //     val ret = OH_ArkUI_TouchRecognizer_CancelTouch(null, null)
    //     logLine("OH_ArkUI_TouchRecognizer_CancelTouch(null,null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureEvent_GetActionType() {
    //     val ret = OH_ArkUI_GestureEvent_GetActionType(null)
    //     logLine("OH_ArkUI_GestureEvent_GetActionType(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureEvent_GetRawInputEvent() {
    //     val ret = OH_ArkUI_GestureEvent_GetRawInputEvent(null)
    //     logLine("OH_ArkUI_GestureEvent_GetRawInputEvent(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_LongPress_GetRepeatCount() {
    //     val ret = OH_ArkUI_LongPress_GetRepeatCount(null)
    //     logLine("OH_ArkUI_LongPress_GetRepeatCount(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_GetVelocity() {
    //     val ret = OH_ArkUI_PanGesture_GetVelocity(null)
    //     logLine("OH_ArkUI_PanGesture_GetVelocity(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_GetVelocityX() {
    //     val ret = OH_ArkUI_PanGesture_GetVelocityX(null)
    //     logLine("OH_ArkUI_PanGesture_GetVelocityX(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_GetVelocityY() {
    //     val ret = OH_ArkUI_PanGesture_GetVelocityY(null)
    //     logLine("OH_ArkUI_PanGesture_GetVelocityY(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_GetOffsetX() {
    //     val ret = OH_ArkUI_PanGesture_GetOffsetX(null)
    //     logLine("OH_ArkUI_PanGesture_GetOffsetX(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_GetOffsetY() {
    //     val ret = OH_ArkUI_PanGesture_GetOffsetY(null)
    //     logLine("OH_ArkUI_PanGesture_GetOffsetY(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SwipeGesture_GetAngle() {
    //     val ret = OH_ArkUI_SwipeGesture_GetAngle(null)
    //     logLine("OH_ArkUI_SwipeGesture_GetAngle(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SwipeGesture_GetVelocity() {
    //     val ret = OH_ArkUI_SwipeGesture_GetVelocity(null)
    //     logLine("OH_ArkUI_SwipeGesture_GetVelocity(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_RotationGesture_GetAngle() {
    //     val ret = OH_ArkUI_RotationGesture_GetAngle(null)
    //     logLine("OH_ArkUI_RotationGesture_GetAngle(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PinchGesture_GetScale() {
    //     val ret = OH_ArkUI_PinchGesture_GetScale(null)
    //     logLine("OH_ArkUI_PinchGesture_GetScale(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PinchGesture_GetCenterX() {
    //     val ret = OH_ArkUI_PinchGesture_GetCenterX(null)
    //     logLine("OH_ArkUI_PinchGesture_GetCenterX(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PinchGesture_GetCenterY() {
    //     val ret = OH_ArkUI_PinchGesture_GetCenterY(null)
    //     logLine("OH_ArkUI_PinchGesture_GetCenterY(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureEvent_GetNode() {
    //     val ret = OH_ArkUI_GestureEvent_GetNode(null)
    //     logLine("OH_ArkUI_GestureEvent_GetNode(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetResponseRecognizersFromInterruptInfo() = memScoped {
    //     val responseChain = alloc<CPointerVar<ByteVar>>()
    //     val count = alloc<IntVar>()
    //     val ret = OH_ArkUI_GetResponseRecognizersFromInterruptInfo(null, responseChain.ptr.reinterpret(), count.ptr)
    //     logLine("OH_ArkUI_GetResponseRecognizersFromInterruptInfo(null,&responseChain,&count)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SetGestureRecognizerEnabled() {
    //     val ret = OH_ArkUI_SetGestureRecognizerEnabled(null, true)
    //     logLine("OH_ArkUI_SetGestureRecognizerEnabled(null,true)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SetGestureRecognizerLimitFingerCount() {
    //     val ret = OH_ArkUI_SetGestureRecognizerLimitFingerCount(null, false)
    //     logLine("OH_ArkUI_SetGestureRecognizerLimitFingerCount(null,false)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureRecognizerEnabled() {
    //     val ret = OH_ArkUI_GetGestureRecognizerEnabled(null)
    //     logLine("OH_ArkUI_GetGestureRecognizerEnabled(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureRecognizerState() = memScoped {
    //     val state = alloc<IntVar>()
    //     val ret = OH_ArkUI_GetGestureRecognizerState(null, state.ptr.reinterpret())
    //     logLine("OH_ArkUI_GetGestureRecognizerState(null,&state)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureEventTargetInfo() = memScoped {
    //     val info = alloc<CPointerVar<ByteVar>>()
    //     val ret = OH_ArkUI_GetGestureEventTargetInfo(null, info.ptr.reinterpret())
    //     logLine("OH_ArkUI_GetGestureEventTargetInfo(null,&info)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GestureEventTargetInfo_IsScrollBegin() = memScoped {
    //     val ret = alloc<BooleanVar>()
    //     val code = OH_ArkUI_GestureEventTargetInfo_IsScrollBegin(null, ret.ptr)
    //     logLine("OH_ArkUI_GestureEventTargetInfo_IsScrollBegin(null,&ret)=$code")
    // }

    // @Test
    // fun testOH_ArkUI_GestureEventTargetInfo_IsScrollEnd() = memScoped {
    //     val ret = alloc<BooleanVar>()
    //     val code = OH_ArkUI_GestureEventTargetInfo_IsScrollEnd(null, ret.ptr)
    //     logLine("OH_ArkUI_GestureEventTargetInfo_IsScrollEnd(null,&ret)=$code")
    // }

    // @Test
    // fun testOH_ArkUI_GetPanGestureDirectionMask() = memScoped {
    //     val directionMask = alloc<UIntVar>()
    //     val ret = OH_ArkUI_GetPanGestureDirectionMask(null, directionMask.ptr.reinterpret())
    //     logLine("OH_ArkUI_GetPanGestureDirectionMask(null,&directionMask)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_IsBuiltInGesture() {
    //     val ret = OH_ArkUI_IsBuiltInGesture(null)
    //     logLine("OH_ArkUI_IsBuiltInGesture(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureTag() = memScoped {
    //     val buf = allocArray<ByteVar>(1)
    //     val result = alloc<IntVar>()
    //     val ret = OH_ArkUI_GetGestureTag(null, buf, 1, result.ptr)
    //     logLine("OH_ArkUI_GetGestureTag(null,buf,1,&result)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureBindNodeId() = memScoped {
    //     val nodeId = allocArray<ByteVar>(1)
    //     val result = alloc<IntVar>()
    //     val ret = OH_ArkUI_GetGestureBindNodeId(null, nodeId, 1, result.ptr)
    //     logLine("OH_ArkUI_GetGestureBindNodeId(null,nodeId,1,&result)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_IsGestureRecognizerValid() {
    //     val ret = OH_ArkUI_IsGestureRecognizerValid(null)
    //     logLine("OH_ArkUI_IsGestureRecognizerValid(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_ParallelInnerGestureEvent_GetCurrentRecognizer() {
    //     val ret = OH_ArkUI_ParallelInnerGestureEvent_GetCurrentRecognizer(null)
    //     logLine("OH_ArkUI_ParallelInnerGestureEvent_GetCurrentRecognizer(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_ParallelInnerGestureEvent_GetConflictRecognizers() = memScoped {
    //     val array = alloc<CPointerVar<ByteVar>>()
    //     val size = alloc<IntVar>()
    //     val ret = OH_ArkUI_ParallelInnerGestureEvent_GetConflictRecognizers(null, array.ptr.reinterpret(), size.ptr)
    //     logLine("OH_ArkUI_ParallelInnerGestureEvent_GetConflictRecognizers(null,&array,&size)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SetArkUIGestureRecognizerDisposeNotify() {
    //     val ret = OH_ArkUI_SetArkUIGestureRecognizerDisposeNotify(null, null, null)
    //     logLine("OH_ArkUI_SetArkUIGestureRecognizerDisposeNotify(null,null,null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_DirectMask() = memScoped {
    //     val directMask = alloc<UIntVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_DirectMask(null, directMask.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_DirectMask (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_DirectMask(null,&directMask)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_FingerCount() = memScoped {
    //     val finger = alloc<IntVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_FingerCount(null, finger.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_FingerCount (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_FingerCount(null,&finger)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_limitFingerCount() = memScoped {
    //     val isLimited = alloc<BooleanVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_limitFingerCount(null, isLimited.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_limitFingerCount (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_limitFingerCount(null,&isLimited)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_repeat() = memScoped {
    //     val isRepeat = alloc<BooleanVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_repeat(null, isRepeat.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_repeat (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_repeat(null,&isRepeat)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_distance() = memScoped {
    //     val distance = alloc<DoubleVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_distance(null, distance.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_distance (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_distance(null,&distance)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_speed() = memScoped {
    //     val speed = alloc<DoubleVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_speed(null, speed.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_speed (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_speed(null,&speed)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_duration() = memScoped {
    //     val duration = alloc<IntVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_duration(null, duration.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_duration (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_duration(null,&duration)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_angle() = memScoped {
    //     val angle = alloc<DoubleVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_angle(null, angle.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_angle (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_angle(null,&angle)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_GetGestureParam_distanceThreshold() = memScoped {
    //     val distanceThreshold = alloc<DoubleVar>()
    //     val ret = try { OH_ArkUI_GetGestureParam_distanceThreshold(null, distanceThreshold.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GetGestureParam_distanceThreshold (API 18) exception: $e"); -1 }
    //     logLine("OH_ArkUI_GetGestureParam_distanceThreshold(null,&distanceThreshold)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_LongPressGesture_SetAllowableMovement() {
    //     val ret = try { OH_ArkUI_LongPressGesture_SetAllowableMovement(null, 0.0) } catch (e: Throwable) { logLine("OH_ArkUI_LongPressGesture_SetAllowableMovement (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
    //     logLine("OH_ArkUI_LongPressGesture_SetAllowableMovement(null,0.0)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_LongPressGesture_GetAllowableMovement() = memScoped {
    //     val allowableMovement = alloc<DoubleVar>()
    //     val ret = try { OH_ArkUI_LongPressGesture_GetAllowableMovement(null, allowableMovement.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_LongPressGesture_GetAllowableMovement (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
    //     logLine("OH_ArkUI_LongPressGesture_GetAllowableMovement(null,&allowableMovement)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_SetDistanceMap() {
    //     val ret = try { OH_ArkUI_PanGesture_SetDistanceMap(null, 0, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_PanGesture_SetDistanceMap (API 19) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
    //     logLine("OH_ArkUI_PanGesture_SetDistanceMap(null,0,null,null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PanGesture_GetDistanceByToolType() = memScoped {
    //     val distance = alloc<DoubleVar>()
    //     val ret = try { OH_ArkUI_PanGesture_GetDistanceByToolType(null, 0, distance.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_PanGesture_GetDistanceByToolType (API 19) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
    //     logLine("OH_ArkUI_PanGesture_GetDistanceByToolType(null,0,&distance)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SetTouchTestDoneCallback() {
    //     val ret = try { OH_ArkUI_SetTouchTestDoneCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_SetTouchTestDoneCallback (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
    //     logLine("OH_ArkUI_SetTouchTestDoneCallback(null,null,null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_PreventGestureRecognizerBegin() {
    //     val ret = try { OH_ArkUI_PreventGestureRecognizerBegin(null) } catch (e: Throwable) { logLine("OH_ArkUI_PreventGestureRecognizerBegin (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
    //     logLine("OH_ArkUI_PreventGestureRecognizerBegin(null)=$ret")
    // }

    // // ==================== drawable_descriptor.h ====================

    @Test
    fun testEnum_DrawableDescriptor_AnimationStatus() {
        assertEquals(DRAWABLE_DESCRIPTOR_ANIMATION_STATUS_INITIAL.toInt(), 0)
        assertEquals(DRAWABLE_DESCRIPTOR_ANIMATION_STATUS_RUNNING.toInt(), 1)
        assertEquals(DRAWABLE_DESCRIPTOR_ANIMATION_STATUS_PAUSED.toInt(), 2)
        assertEquals(DRAWABLE_DESCRIPTOR_ANIMATION_STATUS_STOPPED.toInt(), 3)
        logLine("DrawableDescriptor_AnimationStatus passed")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_CreateFromPixelMap() {
        val ret = OH_ArkUI_DrawableDescriptor_CreateFromPixelMap(null)
        logLine("OH_ArkUI_DrawableDescriptor_CreateFromPixelMap(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_CreateFromAnimatedPixelMap() {
        val ret = OH_ArkUI_DrawableDescriptor_CreateFromAnimatedPixelMap(null, 0)
        logLine("OH_ArkUI_DrawableDescriptor_CreateFromAnimatedPixelMap(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_Dispose() {
        OH_ArkUI_DrawableDescriptor_Dispose(null)
        logLine("OH_ArkUI_DrawableDescriptor_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetStaticPixelMap() {
        val ret = OH_ArkUI_DrawableDescriptor_GetStaticPixelMap(null)
        logLine("OH_ArkUI_DrawableDescriptor_GetStaticPixelMap(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimatedPixelMapArray() {
        val ret = OH_ArkUI_DrawableDescriptor_GetAnimatedPixelMapArray(null)
        logLine("OH_ArkUI_DrawableDescriptor_GetAnimatedPixelMapArray(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimatedPixelMapArraySize() {
        val ret = OH_ArkUI_DrawableDescriptor_GetAnimatedPixelMapArraySize(null)
        logLine("OH_ArkUI_DrawableDescriptor_GetAnimatedPixelMapArraySize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_SetAnimationDuration() {
        OH_ArkUI_DrawableDescriptor_SetAnimationDuration(null, 0)
        logLine("OH_ArkUI_DrawableDescriptor_SetAnimationDuration(null,0) done")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimationDuration() {
        val ret = OH_ArkUI_DrawableDescriptor_GetAnimationDuration(null)
        logLine("OH_ArkUI_DrawableDescriptor_GetAnimationDuration(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_SetAnimationIteration() {
        OH_ArkUI_DrawableDescriptor_SetAnimationIteration(null, 0)
        logLine("OH_ArkUI_DrawableDescriptor_SetAnimationIteration(null,0) done")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimationIteration() {
        val ret = OH_ArkUI_DrawableDescriptor_GetAnimationIteration(null)
        logLine("OH_ArkUI_DrawableDescriptor_GetAnimationIteration(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_SetAnimationFrameDurations() {
        memScoped {
            val arr = allocArray<UIntVar>(0)
            val ret = try { OH_ArkUI_DrawableDescriptor_SetAnimationFrameDurations(null, arr, 0uL) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_SetAnimationFrameDurations (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_DrawableDescriptor_SetAnimationFrameDurations(null,arr,0)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimationFrameDurations() {
        memScoped {
            val size = alloc<ULongVar>()
            val ret = try { OH_ArkUI_DrawableDescriptor_GetAnimationFrameDurations(null, null, size.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_GetAnimationFrameDurations (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_DrawableDescriptor_GetAnimationFrameDurations(null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_SetAnimationAutoPlay() {
        val ret = try { OH_ArkUI_DrawableDescriptor_SetAnimationAutoPlay(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_SetAnimationAutoPlay (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_DrawableDescriptor_SetAnimationAutoPlay(null,0u)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimationAutoPlay() {
        memScoped {
            val autoPlay = alloc<UIntVar>()
            val ret = try { OH_ArkUI_DrawableDescriptor_GetAnimationAutoPlay(null, autoPlay.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_GetAnimationAutoPlay (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_DrawableDescriptor_GetAnimationAutoPlay(null,&autoPlay)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_CreateAnimationController() {
        memScoped {
            val controller = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ArkUI_DrawableDescriptor_CreateAnimationController(null, null, controller.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_CreateAnimationController (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_DrawableDescriptor_CreateAnimationController(null,null,&controller)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_DisposeAnimationController() {
        try { OH_ArkUI_DrawableDescriptor_DisposeAnimationController(null) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_DisposeAnimationController (API 22) exception: $e") }
        logLine("OH_ArkUI_DrawableDescriptor_DisposeAnimationController(null) done")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_StartAnimation() {
        val ret = try { OH_ArkUI_DrawableDescriptor_StartAnimation(null) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_StartAnimation (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_DrawableDescriptor_StartAnimation(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_StopAnimation() {
        val ret = try { OH_ArkUI_DrawableDescriptor_StopAnimation(null) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_StopAnimation (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_DrawableDescriptor_StopAnimation(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_ResumeAnimation() {
        val ret = try { OH_ArkUI_DrawableDescriptor_ResumeAnimation(null) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_ResumeAnimation (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_DrawableDescriptor_ResumeAnimation(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_PauseAnimation() {
        val ret = try { OH_ArkUI_DrawableDescriptor_PauseAnimation(null) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_PauseAnimation (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_DrawableDescriptor_PauseAnimation(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawableDescriptor_GetAnimationStatus() {
        memScoped {
            val status = alloc<IntVar>()
            val ret = try { OH_ArkUI_DrawableDescriptor_GetAnimationStatus(null, status.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ArkUI_DrawableDescriptor_GetAnimationStatus (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_DrawableDescriptor_GetAnimationStatus(null,&status)=$ret")
        }
    }

    // ==================== native_interface_focus.h ====================

    @Test
    fun testEnum_ArkUI_KeyProcessingMode() {
        assertEquals(ARKUI_KEY_PROCESSING_MODE_FOCUS_NAVIGATION.toInt(), 0)
        assertEquals(ARKUI_KEY_PROCESSING_MODE_FOCUS_ANCESTOR_EVENT.toInt(), 1)
        logLine("ArkUI_KeyProcessingMode passed")
    }

    @Test
    fun testOH_ArkUI_FocusRequest() {
        val ret = OH_ArkUI_FocusRequest(null)
        logLine("OH_ArkUI_FocusRequest(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_FocusClear() {
        OH_ArkUI_FocusClear(null)
        logLine("OH_ArkUI_FocusClear(null) done")
    }

    @Test
    fun testOH_ArkUI_FocusActivate() {
        OH_ArkUI_FocusActivate(null, false, false)
        OH_ArkUI_FocusActivate(null, true, true)
        logLine("OH_ArkUI_FocusActivate(null,...) done")
    }

    @Test
    fun testOH_ArkUI_FocusSetAutoTransfer() {
        OH_ArkUI_FocusSetAutoTransfer(null, false)
        OH_ArkUI_FocusSetAutoTransfer(null, true)
        logLine("OH_ArkUI_FocusSetAutoTransfer(null,...) done")
    }

    @Test
    fun testOH_ArkUI_FocusSetKeyProcessingMode() {
        OH_ArkUI_FocusSetKeyProcessingMode(null, ARKUI_KEY_PROCESSING_MODE_FOCUS_NAVIGATION)
        OH_ArkUI_FocusSetKeyProcessingMode(null, ARKUI_KEY_PROCESSING_MODE_FOCUS_ANCESTOR_EVENT)
        logLine("OH_ArkUI_FocusSetKeyProcessingMode(null,...) done")
    }

    // ==================== native_interface.h ====================

    @Test
    fun testEnum_ArkUI_NativeAPIVariantKind() {
        assertEquals(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_NODE.value.toInt(), 0)
        assertEquals(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_DIALOG.value.toInt(), 1)
        assertEquals(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_GESTURE.value.toInt(), 2)
        assertEquals(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_ANIMATE.value.toInt(), 3)
        assertEquals(ArkUI_NativeAPIVariantKind.ARKUI_MULTI_THREAD_NATIVE_NODE.value.toInt(), 4)
        logLine("ArkUI_NativeAPIVariantKind passed")
    }

    @Test
    fun testOH_ArkUI_QueryModuleInterfaceByName_Node() {
        val api = OH_ArkUI_QueryModuleInterfaceByName(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_NODE, "ArkUI_NativeNodeAPI_1")
        logLine("OH_ArkUI_QueryModuleInterfaceByName(NATIVE_NODE,ArkUI_NativeNodeAPI_1)=$api")
    }

    // ==================== ui_input_event.h ====================

    @Test
    fun testEnum_ArkUI_UIInputEvent_Type() {
        assertEquals(ARKUI_UIINPUTEVENT_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(ARKUI_UIINPUTEVENT_TYPE_TOUCH.toInt(), 1)
        assertEquals(ARKUI_UIINPUTEVENT_TYPE_AXIS.toInt(), 2)
        assertEquals(ARKUI_UIINPUTEVENT_TYPE_MOUSE.toInt(), 3)
        assertEquals(ARKUI_UIINPUTEVENT_TYPE_KEY.toInt(), 4)
        logLine("ArkUI_UIInputEvent_Type passed")
    }

    @Test
    fun testEnum_ArkUI_CoastingAxisEventPhase() {
        assertEquals(ARKUI_COASTING_AXIS_EVENT_PHASE_NONE.toInt(), 0)
        assertEquals(ARKUI_COASTING_AXIS_EVENT_PHASE_BEGIN.toInt(), 1)
        assertEquals(ARKUI_COASTING_AXIS_EVENT_PHASE_UPDATE.toInt(), 2)
        assertEquals(ARKUI_COASTING_AXIS_EVENT_PHASE_END.toInt(), 3)
        logLine("ArkUI_CoastingAxisEventPhase passed")
    }

    @Test
    fun testEnum_ArkUI_ModifierKeyName() {
        assertEquals(ARKUI_MODIFIER_KEY_CTRL.toInt(), 1)
        assertEquals(ARKUI_MODIFIER_KEY_SHIFT.toInt(), 2)
        assertEquals(ARKUI_MODIFIER_KEY_ALT.toInt(), 4)
        assertEquals(ARKUI_MODIFIER_KEY_FN.toInt(), 8)
        logLine("ArkUI_ModifierKeyName passed")
    }

    @Test
    fun testEnum_ArkUI_InteractionHand() {
        assertEquals(ARKUI_EVENT_HAND_NONE.toInt(), 0)
        assertEquals(ARKUI_EVENT_HAND_LEFT.toInt(), 1)
        assertEquals(ARKUI_EVENT_HAND_RIGHT.toInt(), 2)
        logLine("ArkUI_InteractionHand passed")
    }

    @Test
    fun testEnum_ArkUI_TouchTestStrategy() {
        assertEquals(ARKUI_TOUCH_TEST_STRATEGY_DEFAULT.toInt(), 0)
        assertEquals(ARKUI_TOUCH_TEST_STRATEGY_FORWARD_COMPETITION.toInt(), 1)
        assertEquals(ARKUI_TOUCH_TEST_STRATEGY_FORWARD.toInt(), 2)
        logLine("ArkUI_TouchTestStrategy passed")
    }

    // ---- ui_input_event.h: UIInputEvent ----
    @Test
    fun testOH_ArkUI_UIInputEvent_GetType() {
        val ret = OH_ArkUI_UIInputEvent_GetType(null)
        logLine("OH_ArkUI_UIInputEvent_GetType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetAction() {
        val ret = OH_ArkUI_UIInputEvent_GetAction(null)
        logLine("OH_ArkUI_UIInputEvent_GetAction(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetSourceType() {
        val ret = OH_ArkUI_UIInputEvent_GetSourceType(null)
        logLine("OH_ArkUI_UIInputEvent_GetSourceType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetToolType() {
        val ret = OH_ArkUI_UIInputEvent_GetToolType(null)
        logLine("OH_ArkUI_UIInputEvent_GetToolType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTime() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTime(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTime(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetDeviceId() {
        val ret = OH_ArkUI_UIInputEvent_GetDeviceId(null)
        logLine("OH_ArkUI_UIInputEvent_GetDeviceId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetPressedKeys() {
        memScoped {
            val length = alloc<IntVar>()
            val ret = OH_ArkUI_UIInputEvent_GetPressedKeys(null, null, length.ptr)
            logLine("OH_ArkUI_UIInputEvent_GetPressedKeys(null,null,&length)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTargetWidth() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTargetWidth(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTargetWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTargetHeight() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTargetHeight(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTargetHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTargetPositionX() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTargetPositionX(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTargetPositionX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTargetPositionY() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTargetPositionY(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTargetPositionY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTargetGlobalPositionX() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTargetGlobalPositionX(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTargetGlobalPositionX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetEventTargetGlobalPositionY() {
        val ret = OH_ArkUI_UIInputEvent_GetEventTargetGlobalPositionY(null)
        logLine("OH_ArkUI_UIInputEvent_GetEventTargetGlobalPositionY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetModifierKeyStates() {
        memScoped {
            val keys = alloc<ULongVar>()
            val ret = OH_ArkUI_UIInputEvent_GetModifierKeyStates(null, keys.ptr)
            logLine("OH_ArkUI_UIInputEvent_GetModifierKeyStates(null,&keys)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetTargetDisplayId() {
        val ret = OH_ArkUI_UIInputEvent_GetTargetDisplayId(null)
        logLine("OH_ArkUI_UIInputEvent_GetTargetDisplayId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetLatestStatus() {
        val ret = try { OH_ArkUI_UIInputEvent_GetLatestStatus() } catch (e: Throwable) { logLine("OH_ArkUI_UIInputEvent_GetLatestStatus (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_UIInputEvent_GetLatestStatus()=$ret")
    }

    @Test
    fun testOH_ArkUI_UIInputEvent_GetCoastingAxisEvent() {
        val ret = try { OH_ArkUI_UIInputEvent_GetCoastingAxisEvent(null) } catch (e: Throwable) { logLine("OH_ArkUI_UIInputEvent_GetCoastingAxisEvent (API 22) exception: $e"); null }
        logLine("OH_ArkUI_UIInputEvent_GetCoastingAxisEvent(null)=$ret")
    }

    // ---- PointerEvent ----
    @Test
    fun testOH_ArkUI_PointerEvent_GetPointerCount() {
        val ret = OH_ArkUI_PointerEvent_GetPointerCount(null)
        logLine("OH_ArkUI_PointerEvent_GetPointerCount(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetPointerId() {
        val ret = OH_ArkUI_PointerEvent_GetPointerId(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetPointerId(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetChangedPointerId() {
        memScoped {
            val pointerIndex = alloc<UIntVar>()
            val ret = OH_ArkUI_PointerEvent_GetChangedPointerId(null, pointerIndex.ptr)
            logLine("OH_ArkUI_PointerEvent_GetChangedPointerId(null,&pointerIndex)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetX() {
        val ret = OH_ArkUI_PointerEvent_GetX(null)
        logLine("OH_ArkUI_PointerEvent_GetX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetXByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetXByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetXByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetY() {
        val ret = OH_ArkUI_PointerEvent_GetY(null)
        logLine("OH_ArkUI_PointerEvent_GetY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetYByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetYByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetYByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetWindowX() {
        val ret = OH_ArkUI_PointerEvent_GetWindowX(null)
        logLine("OH_ArkUI_PointerEvent_GetWindowX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetWindowXByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetWindowXByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetWindowXByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetWindowY() {
        val ret = OH_ArkUI_PointerEvent_GetWindowY(null)
        logLine("OH_ArkUI_PointerEvent_GetWindowY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetWindowYByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetWindowYByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetWindowYByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetDisplayX() {
        val ret = OH_ArkUI_PointerEvent_GetDisplayX(null)
        logLine("OH_ArkUI_PointerEvent_GetDisplayX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetDisplayXByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetDisplayXByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetDisplayXByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetDisplayY() {
        val ret = OH_ArkUI_PointerEvent_GetDisplayY(null)
        logLine("OH_ArkUI_PointerEvent_GetDisplayY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetDisplayYByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetDisplayYByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetDisplayYByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetGlobalDisplayX() {
        val ret = try { OH_ArkUI_PointerEvent_GetGlobalDisplayX(null) } catch (e: Throwable) { logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayX (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetGlobalDisplayXByIndex() {
        val ret = try { OH_ArkUI_PointerEvent_GetGlobalDisplayXByIndex(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayXByIndex (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayXByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetGlobalDisplayY() {
        val ret = try { OH_ArkUI_PointerEvent_GetGlobalDisplayY(null) } catch (e: Throwable) { logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayY (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetGlobalDisplayYByIndex() {
        val ret = try { OH_ArkUI_PointerEvent_GetGlobalDisplayYByIndex(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayYByIndex (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_PointerEvent_GetGlobalDisplayYByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetPressure() {
        val ret = OH_ArkUI_PointerEvent_GetPressure(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetPressure(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetTiltX() {
        val ret = OH_ArkUI_PointerEvent_GetTiltX(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetTiltX(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetTiltY() {
        val ret = OH_ArkUI_PointerEvent_GetTiltY(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetTiltY(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetRollAngle() {
        memScoped {
            val rollAngle = alloc<DoubleVar>()
            val ret = OH_ArkUI_PointerEvent_GetRollAngle(null, rollAngle.ptr)
            logLine("OH_ArkUI_PointerEvent_GetRollAngle(null,&rollAngle)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetTouchAreaWidth() {
        val ret = OH_ArkUI_PointerEvent_GetTouchAreaWidth(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetTouchAreaWidth(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetTouchAreaHeight() {
        val ret = OH_ArkUI_PointerEvent_GetTouchAreaHeight(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetTouchAreaHeight(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetInteractionHand() {
        memScoped {
            val hand = alloc<IntVar>()
            val ret = OH_ArkUI_PointerEvent_GetInteractionHand(null, hand.ptr.reinterpret())
            logLine("OH_ArkUI_PointerEvent_GetInteractionHand(null,&hand)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetInteractionHandByIndex() {
        memScoped {
            val hand = alloc<IntVar>()
            val ret = OH_ArkUI_PointerEvent_GetInteractionHandByIndex(null, 0, hand.ptr.reinterpret())
            logLine("OH_ArkUI_PointerEvent_GetInteractionHandByIndex(null,0,&hand)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistorySize() {
        val ret = OH_ArkUI_PointerEvent_GetHistorySize(null)
        logLine("OH_ArkUI_PointerEvent_GetHistorySize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryEventTime() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryEventTime(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryEventTime(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryPointerCount() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryPointerCount(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryPointerCount(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryPointerId() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryPointerId(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryPointerId(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryX() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryX(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryX(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryY() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryY(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryY(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryWindowX() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryWindowX(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryWindowX(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryWindowY() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryWindowY(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryWindowY(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryDisplayX() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryDisplayX(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryDisplayX(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryDisplayY() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryDisplayY(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryDisplayY(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryGlobalDisplayX() {
        val ret = try { OH_ArkUI_PointerEvent_GetHistoryGlobalDisplayX(null, 0u, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_PointerEvent_GetHistoryGlobalDisplayX (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_PointerEvent_GetHistoryGlobalDisplayX(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryGlobalDisplayY() {
        val ret = try { OH_ArkUI_PointerEvent_GetHistoryGlobalDisplayY(null, 0u, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_PointerEvent_GetHistoryGlobalDisplayY (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_PointerEvent_GetHistoryGlobalDisplayY(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryPressure() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryPressure(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryPressure(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryTiltX() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryTiltX(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryTiltX(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryTiltY() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryTiltY(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryTiltY(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryTouchAreaWidth() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryTouchAreaWidth(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryTouchAreaWidth(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetHistoryTouchAreaHeight() {
        val ret = OH_ArkUI_PointerEvent_GetHistoryTouchAreaHeight(null, 0u, 0u)
        logLine("OH_ArkUI_PointerEvent_GetHistoryTouchAreaHeight(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_GetPressedTimeByIndex() {
        val ret = OH_ArkUI_PointerEvent_GetPressedTimeByIndex(null, 0u)
        logLine("OH_ArkUI_PointerEvent_GetPressedTimeByIndex(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetInterceptHitTestMode() {
        val ret = OH_ArkUI_PointerEvent_SetInterceptHitTestMode(null, HTM_DEFAULT)
        logLine("OH_ArkUI_PointerEvent_SetInterceptHitTestMode(null,HTM_DEFAULT)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetStopPropagation() {
        val ret = OH_ArkUI_PointerEvent_SetStopPropagation(null, false)
        logLine("OH_ArkUI_PointerEvent_SetStopPropagation(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_CreateClonedEvent() {
        memScoped {
            val clonedEvent = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_PointerEvent_CreateClonedEvent(null, clonedEvent.ptr.reinterpret())
            logLine("OH_ArkUI_PointerEvent_CreateClonedEvent(null,&clonedEvent)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PointerEvent_DestroyClonedEvent() {
        val ret = OH_ArkUI_PointerEvent_DestroyClonedEvent(null)
        logLine("OH_ArkUI_PointerEvent_DestroyClonedEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetClonedEventLocalPosition() {
        val ret = OH_ArkUI_PointerEvent_SetClonedEventLocalPosition(null, 0f, 0f)
        logLine("OH_ArkUI_PointerEvent_SetClonedEventLocalPosition(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetClonedEventLocalPositionByIndex() {
        val ret = OH_ArkUI_PointerEvent_SetClonedEventLocalPositionByIndex(null, 0f, 0f, 0)
        logLine("OH_ArkUI_PointerEvent_SetClonedEventLocalPositionByIndex(null,0,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetClonedEventActionType() {
        val ret = OH_ArkUI_PointerEvent_SetClonedEventActionType(null, 0)
        logLine("OH_ArkUI_PointerEvent_SetClonedEventActionType(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetClonedEventChangedFingerId() {
        val ret = OH_ArkUI_PointerEvent_SetClonedEventChangedFingerId(null, 0)
        logLine("OH_ArkUI_PointerEvent_SetClonedEventChangedFingerId(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_SetClonedEventFingerIdByIndex() {
        val ret = OH_ArkUI_PointerEvent_SetClonedEventFingerIdByIndex(null, 0, 0)
        logLine("OH_ArkUI_PointerEvent_SetClonedEventFingerIdByIndex(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_PointerEvent_PostClonedEvent() {
        val ret = OH_ArkUI_PointerEvent_PostClonedEvent(null, null)
        logLine("OH_ArkUI_PointerEvent_PostClonedEvent(null,null)=$ret")
    }

    // ---- AxisEvent / MouseEvent / FocusAxisEvent / HoverEvent ----
    @Test
    fun testOH_ArkUI_AxisEvent_GetVerticalAxisValue() {
        val ret = OH_ArkUI_AxisEvent_GetVerticalAxisValue(null)
        logLine("OH_ArkUI_AxisEvent_GetVerticalAxisValue(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AxisEvent_GetHorizontalAxisValue() {
        val ret = OH_ArkUI_AxisEvent_GetHorizontalAxisValue(null)
        logLine("OH_ArkUI_AxisEvent_GetHorizontalAxisValue(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AxisEvent_GetPinchAxisScaleValue() {
        val ret = OH_ArkUI_AxisEvent_GetPinchAxisScaleValue(null)
        logLine("OH_ArkUI_AxisEvent_GetPinchAxisScaleValue(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AxisEvent_GetAxisAction() {
        val ret = OH_ArkUI_AxisEvent_GetAxisAction(null)
        logLine("OH_ArkUI_AxisEvent_GetAxisAction(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AxisEvent_HasAxis() {
        val ret = try { OH_ArkUI_AxisEvent_HasAxis(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_AxisEvent_HasAxis (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_AxisEvent_HasAxis(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AxisEvent_SetPropagation() {
        val ret = OH_ArkUI_AxisEvent_SetPropagation(null, false)
        logLine("OH_ArkUI_AxisEvent_SetPropagation(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_AxisEvent_GetScrollStep() {
        val ret = OH_ArkUI_AxisEvent_GetScrollStep(null)
        logLine("OH_ArkUI_AxisEvent_GetScrollStep(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_MouseEvent_GetMouseButton() {
        val ret = OH_ArkUI_MouseEvent_GetMouseButton(null)
        logLine("OH_ArkUI_MouseEvent_GetMouseButton(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_MouseEvent_GetMouseAction() {
        val ret = OH_ArkUI_MouseEvent_GetMouseAction(null)
        logLine("OH_ArkUI_MouseEvent_GetMouseAction(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_MouseEvent_GetRawDeltaX() {
        val ret = OH_ArkUI_MouseEvent_GetRawDeltaX(null)
        logLine("OH_ArkUI_MouseEvent_GetRawDeltaX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_MouseEvent_GetRawDeltaY() {
        val ret = OH_ArkUI_MouseEvent_GetRawDeltaY(null)
        logLine("OH_ArkUI_MouseEvent_GetRawDeltaY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_MouseEvent_GetPressedButtons() {
        memScoped {
            val length = alloc<IntVar>()
            val ret = OH_ArkUI_MouseEvent_GetPressedButtons(null, null, length.ptr)
            logLine("OH_ArkUI_MouseEvent_GetPressedButtons(null,null,&length)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_FocusAxisEvent_GetAxisValue() {
        val ret = OH_ArkUI_FocusAxisEvent_GetAxisValue(null, 0)
        logLine("OH_ArkUI_FocusAxisEvent_GetAxisValue(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_FocusAxisEvent_SetStopPropagation() {
        val ret = OH_ArkUI_FocusAxisEvent_SetStopPropagation(null, false)
        logLine("OH_ArkUI_FocusAxisEvent_SetStopPropagation(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_HoverEvent_IsHovered() {
        val ret = OH_ArkUI_HoverEvent_IsHovered(null)
        logLine("OH_ArkUI_HoverEvent_IsHovered(null)=$ret")
    }

    // ---- CoastingAxisEvent ----
    @Test
    fun testOH_ArkUI_CoastingAxisEvent_GetEventTime() {
        val ret = try { OH_ArkUI_CoastingAxisEvent_GetEventTime(null) } catch (e: Throwable) { logLine("OH_ArkUI_CoastingAxisEvent_GetEventTime (API 22) exception: $e"); 0L }
        logLine("OH_ArkUI_CoastingAxisEvent_GetEventTime(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CoastingAxisEvent_GetPhase() {
        val ret = try { OH_ArkUI_CoastingAxisEvent_GetPhase(null) } catch (e: Throwable) { logLine("OH_ArkUI_CoastingAxisEvent_GetPhase (API 22) exception: $e"); ARKUI_COASTING_AXIS_EVENT_PHASE_NONE }
        logLine("OH_ArkUI_CoastingAxisEvent_GetPhase(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CoastingAxisEvent_GetDeltaX() {
        val ret = try { OH_ArkUI_CoastingAxisEvent_GetDeltaX(null) } catch (e: Throwable) { logLine("OH_ArkUI_CoastingAxisEvent_GetDeltaX (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_CoastingAxisEvent_GetDeltaX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CoastingAxisEvent_GetDeltaY() {
        val ret = try { OH_ArkUI_CoastingAxisEvent_GetDeltaY(null) } catch (e: Throwable) { logLine("OH_ArkUI_CoastingAxisEvent_GetDeltaY (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_CoastingAxisEvent_GetDeltaY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CoastingAxisEvent_SetPropagation() {
        val ret = try { OH_ArkUI_CoastingAxisEvent_SetPropagation(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_CoastingAxisEvent_SetPropagation (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_CoastingAxisEvent_SetPropagation(null,false)=$ret")
    }

    // ---- TouchTestInfo / TouchTestInfoItem ----
    @Test
    fun testOH_ArkUI_TouchTestInfo_GetTouchTestInfoList() {
        memScoped {
            val size = alloc<IntVar>()
            val ret = try { OH_ArkUI_TouchTestInfo_GetTouchTestInfoList(null, null, size.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfo_GetTouchTestInfoList (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_TouchTestInfo_GetTouchTestInfoList(null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetX() {
        val ret = try { OH_ArkUI_TouchTestInfoItem_GetX(null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetX (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_TouchTestInfoItem_GetX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetY() {
        val ret = try { OH_ArkUI_TouchTestInfoItem_GetY(null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetY (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_TouchTestInfoItem_GetY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetWindowX() {
        val ret = try { OH_ArkUI_TouchTestInfoItem_GetWindowX(null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetWindowX (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_TouchTestInfoItem_GetWindowX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetWindowY() {
        val ret = try { OH_ArkUI_TouchTestInfoItem_GetWindowY(null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetWindowY (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_TouchTestInfoItem_GetWindowY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetXRelativeToParent() {
        val ret = try { OH_ArkUI_TouchTestInfoItem_GetXRelativeToParent(null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetXRelativeToParent (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_TouchTestInfoItem_GetXRelativeToParent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetYRelativeToParent() {
        val ret = try { OH_ArkUI_TouchTestInfoItem_GetYRelativeToParent(null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetYRelativeToParent (API 22) exception: $e"); 0f }
        logLine("OH_ArkUI_TouchTestInfoItem_GetYRelativeToParent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetChildRect() {
        memScoped {
            val childRect = alloc<ArkUI_Rect>()
            val ret = try { OH_ArkUI_TouchTestInfoItem_GetChildRect(null, childRect.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetChildRect (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_TouchTestInfoItem_GetChildRect(null,&childRect)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TouchTestInfoItem_GetChildId() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val ret = try { OH_ArkUI_TouchTestInfoItem_GetChildId(null, buf, 1) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfoItem_GetChildId (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_TouchTestInfoItem_GetChildId(null,buf,1)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TouchTestInfo_SetTouchResultStrategy() {
        val ret = try { OH_ArkUI_TouchTestInfo_SetTouchResultStrategy(null, ARKUI_TOUCH_TEST_STRATEGY_DEFAULT) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfo_SetTouchResultStrategy (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_TouchTestInfo_SetTouchResultStrategy(null,DEFAULT)=$ret")
    }

    @Test
    fun testOH_ArkUI_TouchTestInfo_SetTouchResultId() {
        val ret = try { OH_ArkUI_TouchTestInfo_SetTouchResultId(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_TouchTestInfo_SetTouchResultId (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_TouchTestInfo_SetTouchResultId(null,null)=$ret")
    }

    // ==================== native_type.h (selected enums) ====================

    @Test
    fun testEnum_ArkUI_ErrorCode() {
        assertEquals(ARKUI_ERROR_CODE_NO_ERROR.toInt(), 0)
        assertEquals(ARKUI_ERROR_CODE_PARAM_INVALID.toInt(), 401)
        assertEquals(ARKUI_ERROR_CODE_CAPI_INIT_ERROR.toInt(), 500)
        assertEquals(ARKUI_ERROR_CODE_INTERNAL_ERROR.toInt(), 100001)
        assertEquals(ARKUI_ERROR_CODE_PARAM_ERROR.toInt(), 100023)
        logLine("ArkUI_ErrorCode sample passed")
    }

    @Test
    fun testEnum_ArkUI_Alignment() {
        assertEquals(ARKUI_ALIGNMENT_TOP_START.toInt(), 0)
        assertEquals(ARKUI_ALIGNMENT_TOP.toInt(), 1)
        assertEquals(ARKUI_ALIGNMENT_TOP_END.toInt(), 2)
        assertEquals(ARKUI_ALIGNMENT_START.toInt(), 3)
        assertEquals(ARKUI_ALIGNMENT_CENTER.toInt(), 4)
        assertEquals(ARKUI_ALIGNMENT_END.toInt(), 5)
        assertEquals(ARKUI_ALIGNMENT_BOTTOM_START.toInt(), 6)
        assertEquals(ARKUI_ALIGNMENT_BOTTOM.toInt(), 7)
        assertEquals(ARKUI_ALIGNMENT_BOTTOM_END.toInt(), 8)
        logLine("ArkUI_Alignment passed")
    }

    @Test
    fun testEnum_ArkUI_AnimationCurve() {
        assertEquals(ARKUI_CURVE_LINEAR.toInt(), 0)
        assertEquals(ARKUI_CURVE_EASE.toInt(), 1)
        assertEquals(ARKUI_CURVE_EASE_IN.toInt(), 2)
        assertEquals(ARKUI_CURVE_EASE_OUT.toInt(), 3)
        assertEquals(ARKUI_CURVE_EASE_IN_OUT.toInt(), 4)
        assertEquals(ARKUI_CURVE_FRICTION.toInt(), 12)
        logLine("ArkUI_AnimationCurve passed")
    }

    @Test
    fun testEnum_ArkUI_AnimationPlayMode() {
        assertEquals(ARKUI_ANIMATION_PLAY_MODE_NORMAL.toInt(), 0)
        assertEquals(ARKUI_ANIMATION_PLAY_MODE_REVERSE.toInt(), 1)
        assertEquals(ARKUI_ANIMATION_PLAY_MODE_ALTERNATE.toInt(), 2)
        assertEquals(ARKUI_ANIMATION_PLAY_MODE_ALTERNATE_REVERSE.toInt(), 3)
        logLine("ArkUI_AnimationPlayMode passed")
    }

    @Test
    fun testEnum_ArkUI_AnimationFillMode() {
        assertEquals(ArkUI_AnimationFillMode.ARKUI_ANIMATION_FILL_MODE_NONE.value.toInt(), 0)
        assertEquals(ArkUI_AnimationFillMode.ARKUI_ANIMATION_FILL_MODE_FORWARDS.value.toInt(), 1)
        assertEquals(ArkUI_AnimationFillMode.ARKUI_ANIMATION_FILL_MODE_BACKWARDS.value.toInt(), 2)
        assertEquals(ArkUI_AnimationFillMode.ARKUI_ANIMATION_FILL_MODE_BOTH.value.toInt(), 3)
        logLine("ArkUI_AnimationFillMode passed")
    }

    @Test
    fun testEnum_ArkUI_AnimationDirection() {
        assertEquals(ARKUI_ANIMATION_DIRECTION_NORMAL.toInt(), 0)
        assertEquals(ARKUI_ANIMATION_DIRECTION_REVERSE.toInt(), 1)
        logLine("ArkUI_AnimationDirection passed")
    }

    @Test
    fun testEnum_ArkUI_AnimationStatus() {
        assertEquals(ArkUI_AnimationStatus.ARKUI_ANIMATION_STATUS_INITIAL.value.toInt(), 0)
        assertEquals(ArkUI_AnimationStatus.ARKUI_ANIMATION_STATUS_RUNNING.value.toInt(), 1)
        assertEquals(ArkUI_AnimationStatus.ARKUI_ANIMATION_STATUS_PAUSED.value.toInt(), 2)
        assertEquals(ArkUI_AnimationStatus.ARKUI_ANIMATION_STATUS_STOPPED.value.toInt(), 3)
        logLine("ArkUI_AnimationStatus passed")
    }

    // ==================== native_type.h – enums (all) ====================

    @Test
    fun testEnum_ArkUI_ImageRepeat() {
        assertEquals(ARKUI_IMAGE_REPEAT_NONE.toInt(), 0)
        assertEquals(ARKUI_IMAGE_REPEAT_X.toInt(), 1)
        assertEquals(ARKUI_IMAGE_REPEAT_Y.toInt(), 2)
        assertEquals(ARKUI_IMAGE_REPEAT_XY.toInt(), 3)
        logLine("ArkUI_ImageRepeat passed")
    }

    @Test
    fun testEnum_ArkUI_FontStyle() {
        assertEquals(ARKUI_FONT_STYLE_NORMAL.toInt(), 0)
        assertEquals(ARKUI_FONT_STYLE_ITALIC.toInt(), 1)
        logLine("ArkUI_FontStyle passed")
    }

    @Test
    fun testEnum_ArkUI_FontWeight() {
        assertEquals(ARKUI_FONT_WEIGHT_W100.toInt(), 0)
        assertEquals(ARKUI_FONT_WEIGHT_W200.toInt(), 1)
        assertEquals(ARKUI_FONT_WEIGHT_W300.toInt(), 2)
        assertEquals(ARKUI_FONT_WEIGHT_W400.toInt(), 3)
        assertEquals(ARKUI_FONT_WEIGHT_W500.toInt(), 4)
        assertEquals(ARKUI_FONT_WEIGHT_W600.toInt(), 5)
        assertEquals(ARKUI_FONT_WEIGHT_W700.toInt(), 6)
        assertEquals(ARKUI_FONT_WEIGHT_W800.toInt(), 7)
        assertEquals(ARKUI_FONT_WEIGHT_W900.toInt(), 8)
        assertEquals(ARKUI_FONT_WEIGHT_BOLD.toInt(), 9)
        assertEquals(ARKUI_FONT_WEIGHT_NORMAL.toInt(), 10)
        assertEquals(ARKUI_FONT_WEIGHT_BOLDER.toInt(), 11)
        assertEquals(ARKUI_FONT_WEIGHT_LIGHTER.toInt(), 12)
        assertEquals(ARKUI_FONT_WEIGHT_MEDIUM.toInt(), 13)
        assertEquals(ARKUI_FONT_WEIGHT_REGULAR.toInt(), 14)
        logLine("ArkUI_FontWeight passed")
    }

    @Test
    fun testEnum_ArkUI_TextAlignment() {
        assertEquals(ARKUI_TEXT_ALIGNMENT_START.toInt(), 0)
        assertEquals(ARKUI_TEXT_ALIGNMENT_CENTER.toInt(), 1)
        assertEquals(ARKUI_TEXT_ALIGNMENT_END.toInt(), 2)
        assertEquals(ARKUI_TEXT_ALIGNMENT_JUSTIFY.toInt(), 3)
        logLine("ArkUI_TextAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_TextVerticalAlignment() {
        assertEquals(ARKUI_TEXT_VERTICAL_ALIGNMENT_BASELINE.toInt(), 0)
        assertEquals(ARKUI_TEXT_VERTICAL_ALIGNMENT_BOTTOM.toInt(), 1)
        assertEquals(ARKUI_TEXT_VERTICAL_ALIGNMENT_CENTER.toInt(), 2)
        assertEquals(ARKUI_TEXT_VERTICAL_ALIGNMENT_TOP.toInt(), 3)
        logLine("ArkUI_TextVerticalAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_TextContentAlign() {
        assertEquals(ARKUI_TEXT_CONTENT_ALIGN_TOP.toInt(), 0)
        assertEquals(ARKUI_TEXT_CONTENT_ALIGN_CENTER.toInt(), 1)
        assertEquals(ARKUI_TEXT_CONTENT_ALIGN_BOTTOM.toInt(), 2)
        logLine("ArkUI_TextContentAlign passed")
    }

    @Test
    fun testEnum_ArkUI_EnterKeyType() {
        assertEquals(ARKUI_ENTER_KEY_TYPE_GO.toInt(), 2)
        assertEquals(ARKUI_ENTER_KEY_TYPE_SEARCH.toInt(), 3)
        assertEquals(ARKUI_ENTER_KEY_TYPE_SEND.toInt(), 4)
        assertEquals(ARKUI_ENTER_KEY_TYPE_NEXT.toInt(), 5)
        assertEquals(ARKUI_ENTER_KEY_TYPE_DONE.toInt(), 6)
        assertEquals(ARKUI_ENTER_KEY_TYPE_PREVIOUS.toInt(), 7)
        assertEquals(ARKUI_ENTER_KEY_TYPE_NEW_LINE.toInt(), 8)
        logLine("ArkUI_EnterKeyType passed")
    }

    @Test
    fun testEnum_ArkUI_TextInputType() {
        assertEquals(ARKUI_TEXTINPUT_TYPE_NORMAL.toInt(), 0)
        assertEquals(ARKUI_TEXTINPUT_TYPE_NUMBER.toInt(), 2)
        assertEquals(ARKUI_TEXTINPUT_TYPE_PHONE_NUMBER.toInt(), 3)
        assertEquals(ARKUI_TEXTINPUT_TYPE_EMAIL.toInt(), 5)
        assertEquals(ARKUI_TEXTINPUT_TYPE_PASSWORD.toInt(), 7)
        assertEquals(ARKUI_TEXTINPUT_TYPE_NUMBER_PASSWORD.toInt(), 8)
        assertEquals(ARKUI_TEXTINPUT_TYPE_SCREEN_LOCK_PASSWORD.toInt(), 9)
        assertEquals(ARKUI_TEXTINPUT_TYPE_USER_NAME.toInt(), 10)
        assertEquals(ARKUI_TEXTINPUT_TYPE_NEW_PASSWORD.toInt(), 11)
        assertEquals(ARKUI_TEXTINPUT_TYPE_NUMBER_DECIMAL.toInt(), 12)
        assertEquals(ARKUI_TEXTINPUT_TYPE_ONE_TIME_CODE.toInt(), 14)
        logLine("ArkUI_TextInputType passed")
    }

    @Test
    fun testEnum_ArkUI_TextAreaType() {
        assertEquals(ARKUI_TEXTAREA_TYPE_NORMAL.toInt(), 0)
        assertEquals(ARKUI_TEXTAREA_TYPE_NUMBER.toInt(), 2)
        assertEquals(ARKUI_TEXTAREA_TYPE_PHONE_NUMBER.toInt(), 3)
        assertEquals(ARKUI_TEXTAREA_TYPE_EMAIL.toInt(), 5)
        assertEquals(ARKUI_TEXTAREA_TYPE_ONE_TIME_CODE.toInt(), 14)
        logLine("ArkUI_TextAreaType passed")
    }

    @Test
    fun testEnum_ArkUI_CancelButtonStyle() {
        assertEquals(ARKUI_CANCELBUTTON_STYLE_CONSTANT.toInt(), 0)
        assertEquals(ARKUI_CANCELBUTTON_STYLE_INVISIBLE.toInt(), 1)
        assertEquals(ARKUI_CANCELBUTTON_STYLE_INPUT.toInt(), 2)
        logLine("ArkUI_CancelButtonStyle passed")
    }

    @Test
    fun testEnum_ArkUI_XComponentType() {
        assertEquals(ARKUI_XCOMPONENT_TYPE_SURFACE.toInt(), 0)
        assertEquals(ARKUI_XCOMPONENT_TYPE_TEXTURE.toInt(), 2)
        logLine("ArkUI_XComponentType passed")
    }

    @Test
    fun testEnum_ArkUI_ProgressType() {
        assertEquals(ARKUI_PROGRESS_TYPE_LINEAR.toInt(), 0)
        assertEquals(ARKUI_PROGRESS_TYPE_RING.toInt(), 1)
        assertEquals(ARKUI_PROGRESS_TYPE_ECLIPSE.toInt(), 2)
        assertEquals(ARKUI_PROGRESS_TYPE_SCALE_RING.toInt(), 3)
        assertEquals(ARKUI_PROGRESS_TYPE_CAPSULE.toInt(), 4)
        logLine("ArkUI_ProgressType passed")
    }

    @Test
    fun testEnum_ArkUI_TextDecorationType() {
        assertEquals(ARKUI_TEXT_DECORATION_TYPE_NONE.toInt(), 0)
        assertEquals(ARKUI_TEXT_DECORATION_TYPE_UNDERLINE.toInt(), 1)
        assertEquals(ARKUI_TEXT_DECORATION_TYPE_OVERLINE.toInt(), 2)
        assertEquals(ARKUI_TEXT_DECORATION_TYPE_LINE_THROUGH.toInt(), 3)
        logLine("ArkUI_TextDecorationType passed")
    }

    @Test
    fun testEnum_ArkUI_TextDecorationStyle() {
        assertEquals(ARKUI_TEXT_DECORATION_STYLE_SOLID.toInt(), 0)
        assertEquals(ARKUI_TEXT_DECORATION_STYLE_DOUBLE.toInt(), 1)
        assertEquals(ARKUI_TEXT_DECORATION_STYLE_DOTTED.toInt(), 2)
        assertEquals(ARKUI_TEXT_DECORATION_STYLE_DASHED.toInt(), 3)
        assertEquals(ARKUI_TEXT_DECORATION_STYLE_WAVY.toInt(), 4)
        logLine("ArkUI_TextDecorationStyle passed")
    }

    @Test
    fun testEnum_ArkUI_TextCase() {
        assertEquals(ARKUI_TEXT_CASE_NORMAL.toInt(), 0)
        assertEquals(ARKUI_TEXT_CASE_LOWER.toInt(), 1)
        assertEquals(ARKUI_TEXT_CASE_UPPER.toInt(), 2)
        logLine("ArkUI_TextCase passed")
    }

    @Test
    fun testEnum_ArkUI_CopyOptions() {
        assertEquals(ARKUI_COPY_OPTIONS_NONE.toInt(), 0)
        assertEquals(ARKUI_COPY_OPTIONS_IN_APP.toInt(), 1)
        assertEquals(ARKUI_COPY_OPTIONS_LOCAL_DEVICE.toInt(), 2)
        assertEquals(ARKUI_COPY_OPTIONS_CROSS_DEVICE.toInt(), 3)
        logLine("ArkUI_CopyOptions passed")
    }

    @Test
    fun testEnum_ArkUI_ShadowType() {
        assertEquals(ARKUI_SHADOW_TYPE_COLOR.toInt(), 0)
        assertEquals(ARKUI_SHADOW_TYPE_BLUR.toInt(), 1)
        logLine("ArkUI_ShadowType passed")
    }

    @Test
    fun testEnum_ArkUI_DatePickerMode() {
        assertEquals(ARKUI_DATEPICKER_MODE_DATE.toInt(), 0)
        assertEquals(ARKUI_DATEPICKER_YEAR_AND_MONTH.toInt(), 1)
        assertEquals(ARKUI_DATEPICKER_MONTH_AND_DAY.toInt(), 2)
        logLine("ArkUI_DatePickerMode passed")
    }

    @Test
    fun testEnum_ArkUI_TextPickerRangeType() {
        assertEquals(ARKUI_TEXTPICKER_RANGETYPE_SINGLE.toInt(), 0)
        assertEquals(ARKUI_TEXTPICKER_RANGETYPE_MULTI.toInt(), 1)
        assertEquals(ARKUI_TEXTPICKER_RANGETYPE_RANGE_CONTENT.toInt(), 2)
        assertEquals(ARKUI_TEXTPICKER_RANGETYPE_CASCADE_RANGE_CONTENT.toInt(), 3)
        logLine("ArkUI_TextPickerRangeType passed")
    }

    @Test
    fun testEnum_ArkUI_EdgeEffect() {
        assertEquals(ARKUI_EDGE_EFFECT_SPRING.toInt(), 0)
        assertEquals(ARKUI_EDGE_EFFECT_FADE.toInt(), 1)
        assertEquals(ARKUI_EDGE_EFFECT_NONE.toInt(), 2)
        logLine("ArkUI_EdgeEffect passed")
    }

    @Test
    fun testEnum_ArkUI_BarState() {
        assertEquals(ARKUI_BAR_STATE_OFF.toInt(), 0)
        assertEquals(ARKUI_BAR_STATE_AUTO.toInt(), 1)
        assertEquals(ARKUI_BAR_STATE_ON.toInt(), 2)
        logLine("ArkUI_BarState passed")
    }

    @Test
    fun testEnum_ArkUI_EffectEdge() {
        assertEquals(ARKUI_EFFECT_EDGE_START.toInt(), 1)
        assertEquals(ARKUI_EFFECT_EDGE_END.toInt(), 2)
        logLine("ArkUI_EffectEdge passed")
    }

    @Test
    fun testEnum_ArkUI_FocusWrapMode() {
        assertEquals(ARKUI_FOCUS_WRAP_MODE_DEFAULT.toInt(), 0)
        assertEquals(ARKUI_FOCUS_WRAP_WITH_ARROW.toInt(), 1)
        logLine("ArkUI_FocusWrapMode passed")
    }

    @Test
    fun testEnum_ArkUI_ItemFillPolicy() {
        assertEquals(ARKUI_ITEMFILLPOLICY_NONE.toInt(), -1)
        assertEquals(ARKUI_ITEMFILLPOLICY_DEFAULT.toInt(), 0)
        assertEquals(ARKUI_ITEMFILLPOLICY_SM1MD2LG3.toInt(), 1)
        assertEquals(ARKUI_ITEMFILLPOLICY_SM2MD3LG5.toInt(), 2)
        logLine("ArkUI_ItemFillPolicy passed")
    }

    @Test
    fun testEnum_ArkUI_GridItemAlignment() {
        assertEquals(GRID_ITEM_ALIGNMENT_DEFAULT.toInt(), 0)
        assertEquals(GRID_ITEM_ALIGNMENT_STRETCH.toInt(), 1)
        logLine("ArkUI_GridItemAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_GridItemStyle() {
        assertEquals(GRID_ITEM_STYLE_NONE.toInt(), 0)
        assertEquals(GRID_ITEM_STYLE_PLAIN.toInt(), 1)
        logLine("ArkUI_GridItemStyle passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollDirection() {
        assertEquals(ARKUI_SCROLL_DIRECTION_VERTICAL.toInt(), 0)
        assertEquals(ARKUI_SCROLL_DIRECTION_HORIZONTAL.toInt(), 1)
        assertEquals(ARKUI_SCROLL_DIRECTION_NONE.toInt(), 3)
        assertEquals(ARKUI_SCROLL_DIRECTION_FREE.toInt(), 4)
        logLine("ArkUI_ScrollDirection passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollSnapAlign() {
        assertEquals(ARKUI_SCROLL_SNAP_ALIGN_NONE.toInt(), 0)
        assertEquals(ARKUI_SCROLL_SNAP_ALIGN_START.toInt(), 1)
        assertEquals(ARKUI_SCROLL_SNAP_ALIGN_CENTER.toInt(), 2)
        assertEquals(ARKUI_SCROLL_SNAP_ALIGN_END.toInt(), 3)
        logLine("ArkUI_ScrollSnapAlign passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollSnapAnimationSpeed() {
        assertEquals(ARKUI_SCROLL_SNAP_ANIMATION_NORMAL.toInt(), 0)
        assertEquals(ARKUI_SCROLL_SNAP_ANIMATION_SLOW.toInt(), 1)
        logLine("ArkUI_ScrollSnapAnimationSpeed passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollBarDisplayMode() {
        assertEquals(ARKUI_SCROLL_BAR_DISPLAY_MODE_OFF.toInt(), 0)
        assertEquals(ARKUI_SCROLL_BAR_DISPLAY_MODE_AUTO.toInt(), 1)
        assertEquals(ARKUI_SCROLL_BAR_DISPLAY_MODE_ON.toInt(), 2)
        logLine("ArkUI_ScrollBarDisplayMode passed")
    }

    @Test
    fun testEnum_ArkUI_Axis() {
        assertEquals(ARKUI_AXIS_VERTICAL.toInt(), 0)
        assertEquals(ARKUI_AXIS_HORIZONTAL.toInt(), 1)
        logLine("ArkUI_Axis passed")
    }

    @Test
    fun testEnum_ArkUI_StickyStyle() {
        assertEquals(ARKUI_STICKY_STYLE_NONE.toInt(), 0)
        assertEquals(ARKUI_STICKY_STYLE_HEADER.toInt(), 1)
        assertEquals(ARKUI_STICKY_STYLE_FOOTER.toInt(), 2)
        assertEquals(ARKUI_STICKY_STYLE_BOTH.toInt(), 3)
        logLine("ArkUI_StickyStyle passed")
    }

    @Test
    fun testEnum_ArkUI_ContentClipMode() {
        assertEquals(ARKUI_CONTENT_CLIP_MODE_CONTENT_ONLY.toInt(), 0)
        assertEquals(ARKUI_CONTENT_CLIP_MODE_BOUNDARY.toInt(), 1)
        assertEquals(ARKUI_CONTENT_CLIP_MODE_SAFE_AREA.toInt(), 2)
        logLine("ArkUI_ContentClipMode passed")
    }

    @Test
    fun testEnum_ArkUI_WaterFlowLayoutMode() {
        assertEquals(ARKUI_WATER_FLOW_LAYOUT_MODE_ALWAYS_TOP_DOWN.toInt(), 0)
        assertEquals(ARKUI_WATER_FLOW_LAYOUT_MODE_SLIDING_WINDOW.toInt(), 1)
        logLine("ArkUI_WaterFlowLayoutMode passed")
    }

    @Test
    fun testEnum_ArkUI_BorderStyle() {
        assertEquals(ARKUI_BORDER_STYLE_SOLID.toInt(), 0)
        assertEquals(ARKUI_BORDER_STYLE_DASHED.toInt(), 1)
        assertEquals(ARKUI_BORDER_STYLE_DOTTED.toInt(), 2)
        logLine("ArkUI_BorderStyle passed")
    }

    @Test
    fun testEnum_ArkUI_HitTestMode() {
        assertEquals(ARKUI_HIT_TEST_MODE_DEFAULT.toInt(), 0)
        assertEquals(ARKUI_HIT_TEST_MODE_BLOCK.toInt(), 1)
        assertEquals(ARKUI_HIT_TEST_MODE_TRANSPARENT.toInt(), 2)
        assertEquals(ARKUI_HIT_TEST_MODE_NONE.toInt(), 3)
        assertEquals(ARKUI_HIT_TEST_MODE_BLOCK_HIERARCHY.toInt(), 4)
        assertEquals(ARKUI_HIT_TEST_MODE_BLOCK_DESCENDANTS.toInt(), 5)
        logLine("ArkUI_HitTestMode passed")
    }

    @Test
    fun testEnum_ArkUI_SwiperArrow() {
        assertEquals(ARKUI_SWIPER_ARROW_HIDE.toInt(), 0)
        assertEquals(ARKUI_SWIPER_ARROW_SHOW.toInt(), 1)
        assertEquals(ARKUI_SWIPER_ARROW_SHOW_ON_HOVER.toInt(), 2)
        logLine("ArkUI_SwiperArrow passed")
    }

    @Test
    fun testEnum_ArkUI_SwiperNestedScrollMode() {
        assertEquals(ARKUI_SWIPER_NESTED_SRCOLL_SELF_ONLY.toInt(), 0)
        assertEquals(ARKUI_SWIPER_NESTED_SRCOLL_SELF_FIRST.toInt(), 1)
        logLine("ArkUI_SwiperNestedScrollMode passed")
    }

    @Test
    fun testEnum_ArkUI_PageFlipMode() {
        assertEquals(ARKUI_PAGE_FLIP_MODE_CONTINUOUS.toInt(), 0)
        assertEquals(ARKUI_PAGE_FLIP_MODE_SINGLE.toInt(), 1)
        logLine("ArkUI_PageFlipMode passed")
    }

    @Test
    fun testEnum_ArkUI_SwiperAnimationMode() {
        assertEquals(ARKUI_SWIPER_NO_ANIMATION.toInt(), 0)
        assertEquals(ARKUI_SWIPER_DEFAULT_ANIMATION.toInt(), 1)
        assertEquals(ARKUI_SWIPER_FAST_ANIMATION.toInt(), 2)
        logLine("ArkUI_SwiperAnimationMode passed")
    }

    @Test
    fun testEnum_ArkUI_AccessibilityMode() {
        assertEquals(ARKUI_ACCESSIBILITY_MODE_AUTO.toInt(), 0)
        assertEquals(ARKUI_ACCESSIBILITY_MODE_ENABLED.toInt(), 1)
        assertEquals(ARKUI_ACCESSIBILITY_MODE_DISABLED.toInt(), 2)
        assertEquals(ARKUI_ACCESSIBILITY_MODE_DISABLED_FOR_DESCENDANTS.toInt(), 3)
        logLine("ArkUI_AccessibilityMode passed")
    }

    @Test
    fun testEnum_ArkUI_TextCopyOptions() {
        assertEquals(ARKUI_TEXT_COPY_OPTIONS_NONE.toInt(), 0)
        assertEquals(ARKUI_TEXT_COPY_OPTIONS_IN_APP.toInt(), 1)
        assertEquals(ARKUI_TEXT_COPY_OPTIONS_LOCAL_DEVICE.toInt(), 2)
        assertEquals(ARKUI_TEXT_COPY_OPTIONS_CROSS_DEVICE.toInt(), 3)
        logLine("ArkUI_TextCopyOptions passed")
    }

    @Test
    fun testEnum_ArkUI_TextHeightAdaptivePolicy() {
        assertEquals(ARKUI_TEXT_HEIGHT_ADAPTIVE_POLICY_MAX_LINES_FIRST.toInt(), 0)
        assertEquals(ARKUI_TEXT_HEIGHT_ADAPTIVE_POLICY_MIN_FONT_SIZE_FIRST.toInt(), 1)
        assertEquals(ARKUI_TEXT_HEIGHT_ADAPTIVE_POLICY_LAYOUT_CONSTRAINT_FIRST.toInt(), 2)
        logLine("ArkUI_TextHeightAdaptivePolicy passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollNestedMode() {
        assertEquals(ARKUI_SCROLL_NESTED_MODE_SELF_ONLY.toInt(), 0)
        assertEquals(ARKUI_SCROLL_NESTED_MODE_SELF_FIRST.toInt(), 1)
        assertEquals(ARKUI_SCROLL_NESTED_MODE_PARENT_FIRST.toInt(), 2)
        logLine("ArkUI_ScrollNestedMode passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollEdge() {
        assertEquals(ARKUI_SCROLL_EDGE_TOP.toInt(), 0)
        logLine("ArkUI_ScrollEdge passed")
    }

    @Test
    fun testEnum_ArkUI_ScrollState() {
        assertEquals(ARKUI_SCROLL_STATE_IDLE.toInt(), 0)
        logLine("ArkUI_ScrollState passed")
    }


    @Test
    fun testEnum_ArkUI_SliderDirection() {
        assertEquals(ARKUI_SLIDER_DIRECTION_VERTICAL.toInt(), 0)
        logLine("ArkUI_SliderDirection passed")
    }

    @Test
    fun testEnum_ArkUI_SliderStyle() {
        assertEquals(ARKUI_SLIDER_STYLE_IN_SET.toInt(), 1)
        logLine("ArkUI_SliderStyle passed")
    }

    @Test
    fun testEnum_ArkUI_CheckboxShape() {
        assertEquals(ArkUI_CHECKBOX_SHAPE_CIRCLE.toInt(), 0)
        logLine("ArkUI_CheckboxShape passed")
    }

    @Test
    fun testEnum_ArkUI_ImageSize() {
        assertEquals(ARKUI_IMAGE_SIZE_AUTO.toInt(), 0)
        logLine("ArkUI_ImageSize passed")
    }

    @Test
    fun testEnum_ArkUI_AdaptiveColor() {
        assertEquals(ARKUI_ADAPTIVE_COLOR_DEFAULT.toInt(), 0)
        logLine("ArkUI_AdaptiveColor passed")
    }


    @Test
    fun testEnum_ArkUI_SystemColorMode() {
        assertEquals(ARKUI_SYSTEM_COLOR_MODE_LIGHT.toInt(), 0)
        logLine("ArkUI_SystemColorMode passed")
    }

    @Test
    fun testEnum_ArkUI_BlurStyle() {
        assertEquals(ARKUI_BLUR_STYLE_THIN.toInt(), 0)
        logLine("ArkUI_BlurStyle passed")
    }

    @Test
    fun testEnum_ArkUI_VerticalAlignment() {
        assertEquals(ARKUI_VERTICAL_ALIGNMENT_TOP.toInt(), 0)
        logLine("ArkUI_VerticalAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_HorizontalAlignment() {
        assertEquals(ARKUI_HORIZONTAL_ALIGNMENT_START.toInt(), 0)
        logLine("ArkUI_HorizontalAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_TextOverflow() {
        assertEquals(ARKUI_TEXT_OVERFLOW_NONE.toInt(), 0)
        logLine("ArkUI_TextOverflow passed")
    }

    @Test
    fun testEnum_ArkUI_ImageSpanAlignment() {
        assertEquals(ARKUI_IMAGE_SPAN_ALIGNMENT_BASELINE.toInt(), 0)
        logLine("ArkUI_ImageSpanAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_ObjectFit() {
        assertEquals(ARKUI_OBJECT_FIT_COVER.toInt(), 1)
        logLine("ArkUI_ObjectFit passed")
    }

    @Test
    fun testEnum_ArkUI_ImageInterpolation() {
        assertEquals(ARKUI_IMAGE_INTERPOLATION_NONE.toInt(), 0)
        logLine("ArkUI_ImageInterpolation passed")
    }

    @Test
    fun testEnum_ArkUI_BlendMode() {
        assertEquals(ARKUI_BLEND_MODE_NONE.toInt(), 0)
        logLine("ArkUI_BlendMode passed")
    }

    @Test
    fun testEnum_ArkUI_Direction() {
        assertEquals(ARKUI_DIRECTION_LTR.toInt(), 0)
        logLine("ArkUI_Direction passed")
    }

    @Test
    fun testEnum_ArkUI_FlexAlignment() {
        assertEquals(ARKUI_FLEX_ALIGNMENT_START.toInt(), 1)
        logLine("ArkUI_FlexAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_FlexDirection() {
        assertEquals(ARKUI_FLEX_DIRECTION_ROW.toInt(), 0)
        logLine("ArkUI_FlexDirection passed")
    }


    @Test
    fun testEnum_ArkUI_Visibility() {
        assertEquals(ARKUI_VISIBILITY_VISIBLE.toInt(), 0)
        logLine("ArkUI_Visibility passed")
    }

    @Test
    fun testEnum_ArkUI_TransitionEdge() {
        assertEquals(ARKUI_TRANSITION_EDGE_TOP.toInt(), 0)
        assertEquals(ARKUI_TRANSITION_EDGE_END.toInt(), 3)
        logLine("ArkUI_TransitionEdge passed")
    }

    @Test
    fun testEnum_ArkUI_BlendApplyType() {
        assertEquals(BLEND_APPLY_TYPE_FAST.toInt(), 0)
        assertEquals(BLEND_APPLY_TYPE_OFFSCREEN.toInt(), 1)
        logLine("ArkUI_BlendApplyType passed")
    }

    @Test
    fun testEnum_ArkUI_FinishCallbackType() {
        assertEquals(ARKUI_FINISH_CALLBACK_REMOVED.toInt(), 0)
        logLine("ArkUI_FinishCallbackType passed")
    }

    @Test
    fun testEnum_ArkUI_ListItemAlignment() {
        assertEquals(ARKUI_LIST_ITEM_ALIGNMENT_START.toInt(), 0)
        assertEquals(ARKUI_LIST_ITEM_ALIGNMENT_END.toInt(), 2)
        logLine("ArkUI_ListItemAlignment passed")
    }

    @Test
    fun testEnum_ArkUI_BarrierDirection() {
        assertEquals(ARKUI_BARRIER_DIRECTION_START.toInt(), 0)
        logLine("ArkUI_BarrierDirection passed")
    }

    @Test
    fun testEnum_ArkUI_LengthMetricUnit() {
        assertEquals(ARKUI_LENGTH_METRIC_UNIT_DEFAULT.toInt(), -1)
        logLine("ArkUI_LengthMetricUnit passed")
    }


    @Test
    fun testEnum_ArkUI_ButtonType() {
        assertEquals(ARKUI_BUTTON_TYPE_NORMAL.toInt(), 0)
        logLine("ArkUI_ButtonType passed")
    }

    @Test
    fun testEnum_ArkUI_TextDataDetectorType() {
        assertEquals(ARKUI_TEXT_DATA_DETECTOR_TYPE_PHONE_NUMBER.toInt(), 0)
        logLine("ArkUI_TextDataDetectorType passed")
    }

    @Test
    fun testEnum_ArkUI_SwiperIndicatorType() {
        assertEquals(ArkUI_SwiperIndicatorType.ARKUI_SWIPER_INDICATOR_TYPE_DOT.value.toInt(), 0)
        assertEquals(ArkUI_SwiperIndicatorType.ARKUI_SWIPER_INDICATOR_TYPE_DIGIT.value.toInt(), 1)
        logLine("ArkUI_SwiperIndicatorType passed")
    }

    @Test
    fun testEnum_ArkUI_ListItemSwipeActionState() {
        assertEquals(ARKUI_LIST_ITEM_SWIPE_ACTION_STATE_COLLAPSED.toInt(), 0)
        logLine("ArkUI_ListItemSwipeActionState passed")
    }

    @Test
    fun testEnum_ArkUI_ListItemSwipeEdgeEffect() {
        assertEquals(ARKUI_LIST_ITEM_SWIPE_EDGE_EFFECT_SPRING.toInt(), 0)
        logLine("ArkUI_ListItemSwipeEdgeEffect passed")
    }

    @Test
    fun testEnum_ArkUI_RouterPageState() {
        assertEquals(ARKUI_ROUTER_PAGE_STATE_ABOUT_TO_APPEAR.toInt(), 0)
        logLine("ArkUI_RouterPageState passed")
    }

    @Test
    fun testEnum_ArkUI_SafeAreaType() {
        assertEquals(ARKUI_SAFE_AREA_TYPE_SYSTEM.toInt(), 1)
        logLine("ArkUI_SafeAreaType passed")
    }

    @Test
    fun testEnum_ArkUI_ListItemGroupArea() {
        assertEquals(ARKUI_LIST_ITEM_GROUP_AREA_OUTSIDE.toInt(), 0)
        logLine("ArkUI_ListItemGroupArea passed")
    }

    @Test
    fun testEnum_ArkUI_SafeAreaEdge() {
        assertEquals(ARKUI_SAFE_AREA_EDGE_TOP.toInt(), 1)
        logLine("ArkUI_SafeAreaEdge passed")
    }

    @Test
    fun testEnum_ArkUI_FocusMove() {
        assertEquals(ARKUI_FOCUS_MOVE_FORWARD.toInt(), 0)
        logLine("ArkUI_FocusMove passed")
    }

    @Test
    fun testEnum_ArkUI_EdgeDirection() {
        assertEquals(ARKUI_EDGE_DIRECTION_ALL.toInt(), 0)
        logLine("ArkUI_EdgeDirection passed")
    }

    @Test
    fun testEnum_ArkUI_CornerDirection() {
        assertEquals(ARKUI_CORNER_DIRECTION_ALL.toInt(), 0)
        logLine("ArkUI_CornerDirection passed")
    }

    @Test
    fun testEnum_ArkUI_ListItemSwipeActionDirection() {
        assertEquals(ARKUI_LIST_ITEM_SWIPE_ACTION_DIRECTION_START.toInt(), 0)
        logLine("ArkUI_ListItemSwipeActionDirection passed")
    }


    // ==================== Functions (null/minimal args, log return) ====================

    @Test
    fun testOH_ArkUI_CustomDialog_CloseDialog() {
        val ret = try { OH_ArkUI_CustomDialog_CloseDialog(0) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_CloseDialog (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_CloseDialog(0) ret=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_CreateOptions() {
        val opts = try { OH_ArkUI_CustomDialog_CreateOptions(null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_CreateOptions (API 19) exception: $e"); null }
        logLine("OH_ArkUI_CustomDialog_CreateOptions(null)=$opts")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_DisposeOptions() {
        try { OH_ArkUI_CustomDialog_DisposeOptions(null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_DisposeOptions (API 19) exception: $e") }
        logLine("OH_ArkUI_CustomDialog_DisposeOptions(null) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_CreateDispose() {
        val opt = OH_ArkUI_AnimateOption_Create()
        OH_ArkUI_AnimateOption_Dispose(opt)
        logLine("OH_ArkUI_AnimateOption Create/Dispose passed")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetDuration() {
        val ret = OH_ArkUI_AnimateOption_GetDuration(null)
        logLine("OH_ArkUI_AnimateOption_GetDuration(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetTempo() {
        val ret = OH_ArkUI_AnimateOption_GetTempo(null)
        logLine("OH_ArkUI_AnimateOption_GetTempo(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetCurve() {
        val ret = OH_ArkUI_AnimateOption_GetCurve(null)
        logLine("OH_ArkUI_AnimateOption_GetCurve(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetDelay() {
        val ret = OH_ArkUI_AnimateOption_GetDelay(null)
        logLine("OH_ArkUI_AnimateOption_GetDelay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetIterations() {
        val ret = OH_ArkUI_AnimateOption_GetIterations(null)
        logLine("OH_ArkUI_AnimateOption_GetIterations(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetPlayMode() {
        val ret = OH_ArkUI_AnimateOption_GetPlayMode(null)
        logLine("OH_ArkUI_AnimateOption_GetPlayMode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetExpectedFrameRateRange() {
        val ret = OH_ArkUI_AnimateOption_GetExpectedFrameRateRange(null)
        logLine("OH_ArkUI_AnimateOption_GetExpectedFrameRateRange(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetDuration() {
        OH_ArkUI_AnimateOption_SetDuration(null, 0)
        logLine("OH_ArkUI_AnimateOption_SetDuration(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetTempo() {
        OH_ArkUI_AnimateOption_SetTempo(null, 0f)
        logLine("OH_ArkUI_AnimateOption_SetTempo(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetCurve() {
        OH_ArkUI_AnimateOption_SetCurve(null, ARKUI_CURVE_LINEAR)
        logLine("OH_ArkUI_AnimateOption_SetCurve(null,LINEAR) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetDelay() {
        OH_ArkUI_AnimateOption_SetDelay(null, 0)
        logLine("OH_ArkUI_AnimateOption_SetDelay(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetIterations() {
        OH_ArkUI_AnimateOption_SetIterations(null, 1)
        logLine("OH_ArkUI_AnimateOption_SetIterations(null,1) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetPlayMode() {
        OH_ArkUI_AnimateOption_SetPlayMode(null, ARKUI_ANIMATION_PLAY_MODE_NORMAL)
        logLine("OH_ArkUI_AnimateOption_SetPlayMode(null,NORMAL) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetExpectedFrameRateRange() {
        OH_ArkUI_AnimateOption_SetExpectedFrameRateRange(null, null)
        logLine("OH_ArkUI_AnimateOption_SetExpectedFrameRateRange(null,null) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_SetICurve() {
        OH_ArkUI_AnimateOption_SetICurve(null, null)
        logLine("OH_ArkUI_AnimateOption_SetICurve(null,null) done")
    }

    @Test
    fun testOH_ArkUI_AnimateOption_GetICurve() {
        val ret = OH_ArkUI_AnimateOption_GetICurve(null)
        logLine("OH_ArkUI_AnimateOption_GetICurve(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Curve_CreateDispose() {
        val curve = OH_ArkUI_Curve_CreateCurveByType(ARKUI_CURVE_LINEAR)
        OH_ArkUI_Curve_DisposeCurve(curve)
        val steps = OH_ArkUI_Curve_CreateStepsCurve(2, true)
        OH_ArkUI_Curve_DisposeCurve(steps)
        logLine("OH_ArkUI_Curve Create/Dispose passed")
    }

    @Test
    fun testOH_ArkUI_Curve_CreateCubicBezierCurve() {
        val curve = OH_ArkUI_Curve_CreateCubicBezierCurve(0f, 0f, 1f, 1f)
        logLine("OH_ArkUI_Curve_CreateCubicBezierCurve(0,0,1,1)=$curve")
        OH_ArkUI_Curve_DisposeCurve(curve) 
    }

    @Test
    fun testOH_ArkUI_Curve_CreateSpringCurve() {
        val curve = OH_ArkUI_Curve_CreateSpringCurve(0f, 1f, 1f, 1f)
        logLine("OH_ArkUI_Curve_CreateSpringCurve(...)=$curve")
        OH_ArkUI_Curve_DisposeCurve(curve)
    }

    @Test
    fun testOH_ArkUI_Curve_CreateSpringMotion() {
        val curve = OH_ArkUI_Curve_CreateSpringMotion(1f, 1f, 0f)
        logLine("OH_ArkUI_Curve_CreateSpringMotion(...)=$curve")
        OH_ArkUI_Curve_DisposeCurve(curve) 
    }

    @Test
    fun testOH_ArkUI_Curve_CreateResponsiveSpringMotion() {
        val curve = OH_ArkUI_Curve_CreateResponsiveSpringMotion(1f, 1f, 0f)
        logLine("OH_ArkUI_Curve_CreateResponsiveSpringMotion(...)=$curve")
        OH_ArkUI_Curve_DisposeCurve(curve)
    }

    @Test
    fun testOH_ArkUI_Curve_CreateInterpolatingSpring() {
        val curve = OH_ArkUI_Curve_CreateInterpolatingSpring(0f, 1f, 1f, 1f)
        logLine("OH_ArkUI_Curve_CreateInterpolatingSpring(...)=$curve")
        OH_ArkUI_Curve_DisposeCurve(curve)
    }

    @Test
    fun testOH_ArkUI_Curve_CreateCustomCurve() {
        val curve = OH_ArkUI_Curve_CreateCustomCurve(null, null)
        logLine("OH_ArkUI_Curve_CreateCustomCurve(null,null)=$curve")
        OH_ArkUI_Curve_DisposeCurve(curve)
    }

    @Test
    fun testOH_ArkUI_QueryModuleInterfaceByName() {
        val api = OH_ArkUI_QueryModuleInterfaceByName(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_NODE, "ArkUI_NativeNodeAPI_1")
        logLine("OH_ArkUI_QueryModuleInterfaceByName=$api")
    }

    // ==================== styled_string.h ====================

    @Test
    fun testOH_ArkUI_StyledString_Create() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val ret = OH_ArkUI_StyledString_Create(style, collection)
        logLine("OH_ArkUI_StyledString_Create(style,collection)=$ret")
        OH_ArkUI_StyledString_Destroy(ret)
        OH_Drawing_DestroyTypographyStyle(style)
        OH_Drawing_DestroyFontCollection(collection)
    }

    @Test
    fun testOH_ArkUI_StyledString_PushTextStyle() {
        val styled = OH_ArkUI_StyledString_Create(null, null)
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_ArkUI_StyledString_PushTextStyle(styled, textStyle)
        logLine("OH_ArkUI_StyledString_PushTextStyle(styled,textStyle) done")
        OH_Drawing_DestroyTextStyle(textStyle)
        OH_ArkUI_StyledString_Destroy(styled)
    }

    @Test
    fun testOH_ArkUI_StyledString_AddText() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val styled = OH_ArkUI_StyledString_Create(style, collection)
        OH_ArkUI_StyledString_AddText(styled, "hello")
        logLine("OH_ArkUI_StyledString_AddText(styled,\"hello\") done")
        OH_ArkUI_StyledString_Destroy(styled)
        OH_Drawing_DestroyTypographyStyle(style)
        OH_Drawing_DestroyFontCollection(collection)
    }

    @Test
    fun testOH_ArkUI_StyledString_PopTextStyle() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val styled = OH_ArkUI_StyledString_Create(style, collection)
        val textStyle = OH_Drawing_CreateTextStyle()
        OH_ArkUI_StyledString_PushTextStyle(styled, textStyle)
        OH_ArkUI_StyledString_PopTextStyle(styled)
        logLine("OH_ArkUI_StyledString_PopTextStyle(styled) done")
        OH_Drawing_DestroyTextStyle(textStyle)
        OH_ArkUI_StyledString_Destroy(styled)
        OH_Drawing_DestroyTypographyStyle(style)
        OH_Drawing_DestroyFontCollection(collection)
    }

    @Test
    fun testOH_ArkUI_StyledString_CreateTypography() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val styled = OH_ArkUI_StyledString_Create(style, collection)
        val ret = OH_ArkUI_StyledString_CreateTypography(styled)
        logLine("OH_ArkUI_StyledString_CreateTypography(styled)=$ret")
        OH_ArkUI_StyledString_Destroy(styled)
        OH_Drawing_DestroyTypographyStyle(style)
        OH_Drawing_DestroyFontCollection(collection)
    }

    @Test
    fun testOH_ArkUI_StyledString_AddPlaceholder() {
        val style = OH_Drawing_CreateTypographyStyle()
        val collection = OH_Drawing_CreateFontCollection()
        val styled = OH_ArkUI_StyledString_Create(style, collection)
        memScoped {
            val placeholder = alloc<OH_Drawing_PlaceholderSpan>().apply {
                width = 10.0
                height = 10.0
            }
            OH_ArkUI_StyledString_AddPlaceholder(styled, placeholder.ptr)
        }
        logLine("OH_ArkUI_StyledString_AddPlaceholder(styled,placeholder) done")
        OH_ArkUI_StyledString_Destroy(styled)
        OH_Drawing_DestroyTypographyStyle(style)
        OH_Drawing_DestroyFontCollection(collection)
    }

    // @Test
    // fun testOH_ArkUI_StyledString_Descriptor_Destroy() {
    //     val desc = OH_ArkUI_StyledString_Descriptor_Create()
    //     OH_ArkUI_StyledString_Descriptor_Destroy(desc)
    //     logLine("OH_ArkUI_StyledString_Descriptor_Destroy(desc) done")
    // }

    @Test
    fun testOH_ArkUI_ConvertToHtml() {
        val ret = OH_ArkUI_ConvertToHtml(null)
        logLine("OH_ArkUI_ConvertToHtml(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UnmarshallStyledStringDescriptor() {
        val ret = OH_ArkUI_UnmarshallStyledStringDescriptor(null, 0u, null)
        logLine("OH_ArkUI_UnmarshallStyledStringDescriptor(null,0,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_MarshallStyledStringDescriptor() {
        memScoped {
            val resultSize = alloc<ULongVar>()
            val ret = OH_ArkUI_MarshallStyledStringDescriptor(null, 0u, null, resultSize.ptr)
            logLine("OH_ArkUI_MarshallStyledStringDescriptor(null,0,null,&resultSize)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TextLayoutManager_Dispose() {
        try { OH_ArkUI_TextLayoutManager_Dispose(null) } catch (e: Throwable) { logLine("OH_ArkUI_TextLayoutManager_Dispose (API 22) exception: $e") }
        logLine("OH_ArkUI_TextLayoutManager_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_TextLayoutManager_GetLineCount() {
        memScoped {
            val outLineCount = alloc<IntVar>()
            val ret = try { OH_ArkUI_TextLayoutManager_GetLineCount(null, outLineCount.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_TextLayoutManager_GetLineCount (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_TextLayoutManager_GetLineCount(null,&outLineCount)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TextLayoutManager_GetRectsForRange() {
        memScoped {
            val outTextBoxes = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ArkUI_TextLayoutManager_GetRectsForRange(
                null, 0, 0, OH_Drawing_RectWidthStyle.RECT_WIDTH_STYLE_TIGHT, OH_Drawing_RectHeightStyle.RECT_HEIGHT_STYLE_TIGHT, outTextBoxes.ptr.reinterpret()
            ) } catch (e: Throwable) { logLine("OH_ArkUI_TextLayoutManager_GetRectsForRange (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_TextLayoutManager_GetRectsForRange(null,0,0,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TextLayoutManager_GetGlyphPositionAtCoordinate() {
        memScoped {
            val outPos = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ArkUI_TextLayoutManager_GetGlyphPositionAtCoordinate(null, 0.0, 0.0, outPos.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ArkUI_TextLayoutManager_GetGlyphPositionAtCoordinate (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_TextLayoutManager_GetGlyphPositionAtCoordinate(null,0,0,&outPos)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_TextLayoutManager_GetLineMetrics() {
        val ret = try { OH_ArkUI_TextLayoutManager_GetLineMetrics(null, 0, null) } catch (e: Throwable) { logLine("OH_ArkUI_TextLayoutManager_GetLineMetrics (API 22) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_TextLayoutManager_GetLineMetrics(null,0,null)=$ret")
    }

    // ==================== native_node_napi.h ====================

    @Test
    fun testOH_ArkUI_GetNodeHandleFromNapiValue() {
        memScoped {
            val handle = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_GetNodeHandleFromNapiValue(null, null, handle.ptr.reinterpret())
            logLine("OH_ArkUI_GetNodeHandleFromNapiValue(null,null,&handle)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetContextFromNapiValue() {
        memScoped {
            val context = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_GetContextFromNapiValue(null, null, context.ptr.reinterpret())
            logLine("OH_ArkUI_GetContextFromNapiValue(null,null,&context)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNodeContentFromNapiValue() {
        memScoped {
            val content = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_GetNodeContentFromNapiValue(null, null, content.ptr.reinterpret())
            logLine("OH_ArkUI_GetNodeContentFromNapiValue(null,null,&content)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetDrawableDescriptorFromNapiValue() {
        memScoped {
            val drawableDescriptor = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_GetDrawableDescriptorFromNapiValue(null, null, drawableDescriptor.ptr.reinterpret())
            logLine("OH_ArkUI_GetDrawableDescriptorFromNapiValue(null,null,&drawableDescriptor)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetDrawableDescriptorFromResourceNapiValue() {
        memScoped {
            val drawableDescriptor = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_GetDrawableDescriptorFromResourceNapiValue(null, null, drawableDescriptor.ptr.reinterpret())
            logLine("OH_ArkUI_GetDrawableDescriptorFromResourceNapiValue(null,null,&drawableDescriptor)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavigationId() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavigationId(null, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetNavigationId(null,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavDestinationName() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavDestinationName(null, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetNavDestinationName(null,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavStackLength() {
        memScoped {
            val length = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavStackLength(null, length.ptr)
            logLine("OH_ArkUI_GetNavStackLength(null,&length)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavDestinationNameByIndex() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavDestinationNameByIndex(null, 0, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetNavDestinationNameByIndex(null,0,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavDestinationId() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavDestinationId(null, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetNavDestinationId(null,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavDestinationState() {
        memScoped {
            val state = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavDestinationState(null, state.ptr.reinterpret())
            logLine("OH_ArkUI_GetNavDestinationState(null,&state)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavDestinationIndex() {
        memScoped {
            val index = alloc<IntVar>()
            val ret = OH_ArkUI_GetNavDestinationIndex(null, index.ptr)
            logLine("OH_ArkUI_GetNavDestinationIndex(null,&index)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNavDestinationParam() {
        val ret = OH_ArkUI_GetNavDestinationParam(null)
        logLine("OH_ArkUI_GetNavDestinationParam(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_GetRouterPageIndex() {
        memScoped {
            val index = alloc<IntVar>()
            val ret = OH_ArkUI_GetRouterPageIndex(null, index.ptr)
            logLine("OH_ArkUI_GetRouterPageIndex(null,&index)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetRouterPageName() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetRouterPageName(null, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetRouterPageName(null,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetRouterPagePath() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetRouterPagePath(null, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetRouterPagePath(null,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetRouterPageState() {
        memScoped {
            val state = alloc<IntVar>()
            val ret = OH_ArkUI_GetRouterPageState(null, state.ptr.reinterpret())
            logLine("OH_ArkUI_GetRouterPageState(null,&state)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetRouterPageId() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            val writeLen = alloc<IntVar>()
            val ret = OH_ArkUI_GetRouterPageId(null, buf, 1, writeLen.ptr)
            logLine("OH_ArkUI_GetRouterPageId(null,buf,1,&writeLen)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PostFrameCallback() {
        val ret = try { OH_ArkUI_PostFrameCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_PostFrameCallback (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_PostFrameCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_InitModuleForArkTSEnv() {
        val ret = try { OH_ArkUI_InitModuleForArkTSEnv(null) } catch (e: Throwable) { logLine("OH_ArkUI_InitModuleForArkTSEnv (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_InitModuleForArkTSEnv(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NotifyArkTSEnvDestroy() {
        try { OH_ArkUI_NotifyArkTSEnvDestroy(null) } catch (e: Throwable) { logLine("OH_ArkUI_NotifyArkTSEnvDestroy (API 20) exception: $e") }
        logLine("OH_ArkUI_NotifyArkTSEnvDestroy(null) done")
    }

    @Test
    fun testOH_ArkUI_PostIdleCallback() {
        val ret = try { OH_ArkUI_PostIdleCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_PostIdleCallback (API 20) exception: $e"); -1 }
        logLine("OH_ArkUI_PostIdleCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption() {
        val opt = OH_ArkUI_CreateDragPreviewOption()
        assertNotNull(opt)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption passed")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption() {
        val opt = OH_ArkUI_KeyframeAnimateOption_Create(0)
        logLine("OH_ArkUI_KeyframeAnimateOption_Create(0)=$opt")
        OH_ArkUI_KeyframeAnimateOption_Dispose(opt)
        val opt2 = OH_ArkUI_KeyframeAnimateOption_Create(2)
        assertNotNull(opt2)
        OH_ArkUI_KeyframeAnimateOption_Dispose(opt2)
        logLine("OH_ArkUI_KeyframeAnimateOption passed")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_SetDelay() {
        val ret = OH_ArkUI_KeyframeAnimateOption_SetDelay(null, 0)
        logLine("OH_ArkUI_KeyframeAnimateOption_SetDelay(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_SetIterations() {
        val ret = OH_ArkUI_KeyframeAnimateOption_SetIterations(null, 1)
        logLine("OH_ArkUI_KeyframeAnimateOption_SetIterations(null,1)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_RegisterOnFinishCallback() {
        val ret = OH_ArkUI_KeyframeAnimateOption_RegisterOnFinishCallback(null, null, null)
        logLine("OH_ArkUI_KeyframeAnimateOption_RegisterOnFinishCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_SetExpectedFrameRate() {
        val ret = try { OH_ArkUI_KeyframeAnimateOption_SetExpectedFrameRate(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_KeyframeAnimateOption_SetExpectedFrameRate (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_KeyframeAnimateOption_SetExpectedFrameRate(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_SetDuration() {
        val ret = OH_ArkUI_KeyframeAnimateOption_SetDuration(null, 0, 0)
        logLine("OH_ArkUI_KeyframeAnimateOption_SetDuration(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_SetCurve() {
        val ret = OH_ArkUI_KeyframeAnimateOption_SetCurve(null, null, 0)
        logLine("OH_ArkUI_KeyframeAnimateOption_SetCurve(null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_RegisterOnEventCallback() {
        val ret = OH_ArkUI_KeyframeAnimateOption_RegisterOnEventCallback(null, null, null, 0)
        logLine("OH_ArkUI_KeyframeAnimateOption_RegisterOnEventCallback(null,null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_GetDelay() {
        val ret = OH_ArkUI_KeyframeAnimateOption_GetDelay(null)
        logLine("OH_ArkUI_KeyframeAnimateOption_GetDelay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_GetIterations() {
        val ret = OH_ArkUI_KeyframeAnimateOption_GetIterations(null)
        logLine("OH_ArkUI_KeyframeAnimateOption_GetIterations(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_GetExpectedFrameRate() {
        val ret = try { OH_ArkUI_KeyframeAnimateOption_GetExpectedFrameRate(null) } catch (e: Throwable) { logLine("OH_ArkUI_KeyframeAnimateOption_GetExpectedFrameRate (API 19) exception: $e"); null }
        logLine("OH_ArkUI_KeyframeAnimateOption_GetExpectedFrameRate(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_GetDuration() {
        val ret = OH_ArkUI_KeyframeAnimateOption_GetDuration(null, 0)
        logLine("OH_ArkUI_KeyframeAnimateOption_GetDuration(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_KeyframeAnimateOption_GetCurve() {
        val ret = OH_ArkUI_KeyframeAnimateOption_GetCurve(null, 0)
        logLine("OH_ArkUI_KeyframeAnimateOption_GetCurve(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption() {
        val opt = OH_ArkUI_AnimatorOption_Create(0)
        OH_ArkUI_AnimatorOption_Dispose(opt)
        logLine("OH_ArkUI_AnimatorOption passed")
    }

    @Test
    fun testOH_ArkUI_AnimatorEvent_GetUserData() {
        val ret = OH_ArkUI_AnimatorEvent_GetUserData(null)
        logLine("OH_ArkUI_AnimatorEvent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOnFrameEvent_GetUserData() {
        val ret = OH_ArkUI_AnimatorOnFrameEvent_GetUserData(null)
        logLine("OH_ArkUI_AnimatorOnFrameEvent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOnFrameEvent_GetValue() {
        val ret = OH_ArkUI_AnimatorOnFrameEvent_GetValue(null)
        logLine("OH_ArkUI_AnimatorOnFrameEvent_GetValue(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetDuration() {
        val ret = OH_ArkUI_AnimatorOption_SetDuration(null, 0)
        logLine("OH_ArkUI_AnimatorOption_SetDuration(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetDelay() {
        val ret = OH_ArkUI_AnimatorOption_SetDelay(null, 0)
        logLine("OH_ArkUI_AnimatorOption_SetDelay(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetIterations() {
        val ret = OH_ArkUI_AnimatorOption_SetIterations(null, 1)
        logLine("OH_ArkUI_AnimatorOption_SetIterations(null,1)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetFill() {
        val ret = OH_ArkUI_AnimatorOption_SetFill(null, ArkUI_AnimationFillMode.ARKUI_ANIMATION_FILL_MODE_NONE)
        logLine("OH_ArkUI_AnimatorOption_SetFill(null,NONE)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetDirection() {
        val ret = OH_ArkUI_AnimatorOption_SetDirection(null, ARKUI_ANIMATION_DIRECTION_NORMAL)
        logLine("OH_ArkUI_AnimatorOption_SetDirection(null,NORMAL)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetCurve() {
        val ret = OH_ArkUI_AnimatorOption_SetCurve(null, null)
        logLine("OH_ArkUI_AnimatorOption_SetCurve(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetBegin() {
        val ret = OH_ArkUI_AnimatorOption_SetBegin(null, 0f)
        logLine("OH_ArkUI_AnimatorOption_SetBegin(null,0f)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetEnd() {
        val ret = OH_ArkUI_AnimatorOption_SetEnd(null, 1f)
        logLine("OH_ArkUI_AnimatorOption_SetEnd(null,1f)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetExpectedFrameRateRange() {
        val ret = OH_ArkUI_AnimatorOption_SetExpectedFrameRateRange(null, null)
        logLine("OH_ArkUI_AnimatorOption_SetExpectedFrameRateRange(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetKeyframe() {
        val ret = OH_ArkUI_AnimatorOption_SetKeyframe(null, 0f, 0f, 0)
        logLine("OH_ArkUI_AnimatorOption_SetKeyframe(null,0f,0f,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_SetKeyframeCurve() {
        val ret = OH_ArkUI_AnimatorOption_SetKeyframeCurve(null, null, 0)
        logLine("OH_ArkUI_AnimatorOption_SetKeyframeCurve(null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetDuration() {
        val ret = OH_ArkUI_AnimatorOption_GetDuration(null)
        logLine("OH_ArkUI_AnimatorOption_GetDuration(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetDelay() {
        val ret = OH_ArkUI_AnimatorOption_GetDelay(null)
        logLine("OH_ArkUI_AnimatorOption_GetDelay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetIterations() {
        val ret = OH_ArkUI_AnimatorOption_GetIterations(null)
        logLine("OH_ArkUI_AnimatorOption_GetIterations(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetFill() {
        // 有NPE异常
        try {
            val ret = OH_ArkUI_AnimatorOption_GetFill(null)
            logLine("OH_ArkUI_AnimatorOption_GetFill(null)=$ret")
        } catch (e: Throwable) {
            logLine("OH_ArkUI_AnimatorOption_GetFill(null) exception: $e")
        }
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetDirection() {
        val ret = OH_ArkUI_AnimatorOption_GetDirection(null)
        logLine("OH_ArkUI_AnimatorOption_GetDirection(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetCurve() {
        val ret = OH_ArkUI_AnimatorOption_GetCurve(null)
        logLine("OH_ArkUI_AnimatorOption_GetCurve(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetBegin() {
        val ret = OH_ArkUI_AnimatorOption_GetBegin(null)
        logLine("OH_ArkUI_AnimatorOption_GetBegin(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetEnd() {
        val ret = OH_ArkUI_AnimatorOption_GetEnd(null)
        logLine("OH_ArkUI_AnimatorOption_GetEnd(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetExpectedFrameRateRange() {
        val ret = OH_ArkUI_AnimatorOption_GetExpectedFrameRateRange(null)
        logLine("OH_ArkUI_AnimatorOption_GetExpectedFrameRateRange(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetKeyframeTime() {
        val ret = OH_ArkUI_AnimatorOption_GetKeyframeTime(null, 0)
        logLine("OH_ArkUI_AnimatorOption_GetKeyframeTime(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetKeyframeValue() {
        val ret = OH_ArkUI_AnimatorOption_GetKeyframeValue(null, 0)
        logLine("OH_ArkUI_AnimatorOption_GetKeyframeValue(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_GetKeyframeCurve() {
        val ret = OH_ArkUI_AnimatorOption_GetKeyframeCurve(null, 0)
        logLine("OH_ArkUI_AnimatorOption_GetKeyframeCurve(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_RegisterOnFrameCallback() {
        val ret = OH_ArkUI_AnimatorOption_RegisterOnFrameCallback(null, null, null)
        logLine("OH_ArkUI_AnimatorOption_RegisterOnFrameCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_RegisterOnFinishCallback() {
        val ret = OH_ArkUI_AnimatorOption_RegisterOnFinishCallback(null, null, null)
        logLine("OH_ArkUI_AnimatorOption_RegisterOnFinishCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_RegisterOnCancelCallback() {
        val ret = OH_ArkUI_AnimatorOption_RegisterOnCancelCallback(null, null, null)
        logLine("OH_ArkUI_AnimatorOption_RegisterOnCancelCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AnimatorOption_RegisterOnRepeatCallback() {
        val ret = OH_ArkUI_AnimatorOption_RegisterOnRepeatCallback(null, null, null)
        logLine("OH_ArkUI_AnimatorOption_RegisterOnRepeatCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Animator_ResetAnimatorOption() {
        val ret = OH_ArkUI_Animator_ResetAnimatorOption(null, null)
        logLine("OH_ArkUI_Animator_ResetAnimatorOption(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Animator_Play() {
        val ret = OH_ArkUI_Animator_Play(null)
        logLine("OH_ArkUI_Animator_Play(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Animator_Finish() {
        val ret = OH_ArkUI_Animator_Finish(null)
        logLine("OH_ArkUI_Animator_Finish(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Animator_Pause() {
        val ret = OH_ArkUI_Animator_Pause(null)
        logLine("OH_ArkUI_Animator_Pause(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Animator_Cancel() {
        val ret = OH_ArkUI_Animator_Cancel(null)
        logLine("OH_ArkUI_Animator_Cancel(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Animator_Reverse() {
        val ret = OH_ArkUI_Animator_Reverse(null)
        logLine("OH_ArkUI_Animator_Reverse(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CreateOpacityTransitionEffect() {
        val ret = OH_ArkUI_CreateOpacityTransitionEffect(0f)
        logLine("OH_ArkUI_CreateOpacityTransitionEffect(0f)=$ret")
        OH_ArkUI_TransitionEffect_Dispose(ret) 
    }

    @Test
    fun testOH_ArkUI_CreateTranslationTransitionEffect() {
        val ret = OH_ArkUI_CreateTranslationTransitionEffect(null)
        logLine("OH_ArkUI_CreateTranslationTransitionEffect(null)=$ret")
        OH_ArkUI_TransitionEffect_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_CreateScaleTransitionEffect() {
        val ret = OH_ArkUI_CreateScaleTransitionEffect(null)
        logLine("OH_ArkUI_CreateScaleTransitionEffect(null)=$ret")
        OH_ArkUI_TransitionEffect_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_CreateRotationTransitionEffect() {
        val ret = OH_ArkUI_CreateRotationTransitionEffect(null)
        logLine("OH_ArkUI_CreateRotationTransitionEffect(null)=$ret")
        OH_ArkUI_TransitionEffect_Dispose(ret)

    }

    @Test
    fun testOH_ArkUI_CreateMovementTransitionEffect() {
        val ret = OH_ArkUI_CreateMovementTransitionEffect(ARKUI_TRANSITION_EDGE_TOP)
        logLine("OH_ArkUI_CreateMovementTransitionEffect(TOP)=$ret")
        OH_ArkUI_TransitionEffect_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_CreateAsymmetricTransitionEffect() {
        val ret = OH_ArkUI_CreateAsymmetricTransitionEffect(null, null)
        logLine("OH_ArkUI_CreateAsymmetricTransitionEffect(null,null)=$ret")
        OH_ArkUI_TransitionEffect_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_TransitionEffect_Dispose() {
        OH_ArkUI_TransitionEffect_Dispose(null)
        logLine("OH_ArkUI_TransitionEffect_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_TransitionEffect_Combine() {
        val ret = OH_ArkUI_TransitionEffect_Combine(null, null)
        logLine("OH_ArkUI_TransitionEffect_Combine(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TransitionEffect_SetAnimation() {
        val ret = OH_ArkUI_TransitionEffect_SetAnimation(null, null)
        logLine("OH_ArkUI_TransitionEffect_SetAnimation(null,null)=$ret")
    }

    // ==================== native_type.h – functions ====================

    @Test
    fun testOH_ArkUI_LayoutConstraint_Create() {
        val ret = OH_ArkUI_LayoutConstraint_Create()
        logLine("OH_ArkUI_LayoutConstraint_Create()=$ret")
        OH_ArkUI_LayoutConstraint_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_Copy() {
        val ret = OH_ArkUI_LayoutConstraint_Copy(null)
        logLine("OH_ArkUI_LayoutConstraint_Copy(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_Dispose() {
        OH_ArkUI_LayoutConstraint_Dispose(null)
        logLine("OH_ArkUI_LayoutConstraint_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_GetMaxWidth() {
        val ret = OH_ArkUI_LayoutConstraint_GetMaxWidth(null)
        logLine("OH_ArkUI_LayoutConstraint_GetMaxWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_GetMinWidth() {
        val ret = OH_ArkUI_LayoutConstraint_GetMinWidth(null)
        logLine("OH_ArkUI_LayoutConstraint_GetMinWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_GetMaxHeight() {
        val ret = OH_ArkUI_LayoutConstraint_GetMaxHeight(null)
        logLine("OH_ArkUI_LayoutConstraint_GetMaxHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_GetMinHeight() {
        val ret = OH_ArkUI_LayoutConstraint_GetMinHeight(null)
        logLine("OH_ArkUI_LayoutConstraint_GetMinHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_GetPercentReferenceWidth() {
        val ret = OH_ArkUI_LayoutConstraint_GetPercentReferenceWidth(null)
        logLine("OH_ArkUI_LayoutConstraint_GetPercentReferenceWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_GetPercentReferenceHeight() {
        val ret = OH_ArkUI_LayoutConstraint_GetPercentReferenceHeight(null)
        logLine("OH_ArkUI_LayoutConstraint_GetPercentReferenceHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_SetMaxWidth() {
        OH_ArkUI_LayoutConstraint_SetMaxWidth(null, 0)
        logLine("OH_ArkUI_LayoutConstraint_SetMaxWidth(null,0) done")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_SetMinWidth() {
        OH_ArkUI_LayoutConstraint_SetMinWidth(null, 0)
        logLine("OH_ArkUI_LayoutConstraint_SetMinWidth(null,0) done")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_SetMaxHeight() {
        OH_ArkUI_LayoutConstraint_SetMaxHeight(null, 0)
        logLine("OH_ArkUI_LayoutConstraint_SetMaxHeight(null,0) done")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_SetMinHeight() {
        OH_ArkUI_LayoutConstraint_SetMinHeight(null, 0)
        logLine("OH_ArkUI_LayoutConstraint_SetMinHeight(null,0) done")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_SetPercentReferenceWidth() {
        OH_ArkUI_LayoutConstraint_SetPercentReferenceWidth(null, 0)
        logLine("OH_ArkUI_LayoutConstraint_SetPercentReferenceWidth(null,0) done")
    }

    @Test
    fun testOH_ArkUI_LayoutConstraint_SetPercentReferenceHeight() {
        OH_ArkUI_LayoutConstraint_SetPercentReferenceHeight(null, 0)
        logLine("OH_ArkUI_LayoutConstraint_SetPercentReferenceHeight(null,0) done")
    }

    @Test
    fun testOH_ArkUI_DrawContext_GetCanvas() {
        val ret = OH_ArkUI_DrawContext_GetCanvas(null)
        logLine("OH_ArkUI_DrawContext_GetCanvas(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DrawContext_GetSize() {
        memScoped {
            val ret = OH_ArkUI_DrawContext_GetSize(null)
            logLine("OH_ArkUI_DrawContext_GetSize(null)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GridLayoutOptions_Create() {
        val ret = try { OH_ArkUI_GridLayoutOptions_Create() } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_Create (API 22) exception: $e"); null }
        logLine("OH_ArkUI_GridLayoutOptions_Create()=$ret")
        try { OH_ArkUI_GridLayoutOptions_Dispose(ret) } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_Dispose (API 22) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_GridLayoutOptions_Dispose() {
        try { OH_ArkUI_GridLayoutOptions_Dispose(null) } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_Dispose (API 22) exception: $e") }
        logLine("OH_ArkUI_GridLayoutOptions_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_GridLayoutOptions_SetIrregularIndexes() {
        memScoped {
            val arr = allocArray<UIntVar>(0)
            val ret = try { OH_ArkUI_GridLayoutOptions_SetIrregularIndexes(null, arr, 0) } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_SetIrregularIndexes (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_GridLayoutOptions_SetIrregularIndexes(null,arr,0)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GridLayoutOptions_GetIrregularIndexes() {
        memScoped {
            val size = alloc<IntVar>()
            val ret = try { OH_ArkUI_GridLayoutOptions_GetIrregularIndexes(null, null, size.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_GetIrregularIndexes (API 22) exception: $e"); -1 }
            logLine("OH_ArkUI_GridLayoutOptions_GetIrregularIndexes(null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GridLayoutOptions_RegisterGetIrregularSizeByIndexCallback() {
        try { OH_ArkUI_GridLayoutOptions_RegisterGetIrregularSizeByIndexCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_RegisterGetIrregularSizeByIndexCallback (API 22) exception: $e") }
        logLine("OH_ArkUI_GridLayoutOptions_RegisterGetIrregularSizeByIndexCallback(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_GridLayoutOptions_RegisterGetRectByIndexCallback() {
        try { OH_ArkUI_GridLayoutOptions_RegisterGetRectByIndexCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_GridLayoutOptions_RegisterGetRectByIndexCallback (API 22) exception: $e") }
        logLine("OH_ArkUI_GridLayoutOptions_RegisterGetRectByIndexCallback(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_Create() {
        val ret = OH_ArkUI_WaterFlowSectionOption_Create()
        logLine("OH_ArkUI_WaterFlowSectionOption_Create()=$ret")
        OH_ArkUI_WaterFlowSectionOption_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_Dispose() {
        OH_ArkUI_WaterFlowSectionOption_Dispose(null)
        logLine("OH_ArkUI_WaterFlowSectionOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_SetSize() {
        OH_ArkUI_WaterFlowSectionOption_SetSize(null, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_SetSize(null,0) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_GetSize() {
        val ret = OH_ArkUI_WaterFlowSectionOption_GetSize(null)
        logLine("OH_ArkUI_WaterFlowSectionOption_GetSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_SetItemCount() {
        OH_ArkUI_WaterFlowSectionOption_SetItemCount(null, 0, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_SetItemCount(null,0,0) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_GetItemCount() {
        val ret = OH_ArkUI_WaterFlowSectionOption_GetItemCount(null, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_GetItemCount(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_SetCrossCount() {
        OH_ArkUI_WaterFlowSectionOption_SetCrossCount(null, 0, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_SetCrossCount(null,0,0) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_GetCrossCount() {
        val ret = OH_ArkUI_WaterFlowSectionOption_GetCrossCount(null, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_GetCrossCount(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_SetColumnGap() {
        OH_ArkUI_WaterFlowSectionOption_SetColumnGap(null, 0, 0f)
        logLine("OH_ArkUI_WaterFlowSectionOption_SetColumnGap(null,0,0f) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_GetColumnGap() {
        val ret = OH_ArkUI_WaterFlowSectionOption_GetColumnGap(null, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_GetColumnGap(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_SetRowGap() {
        OH_ArkUI_WaterFlowSectionOption_SetRowGap(null, 0, 0f)
        logLine("OH_ArkUI_WaterFlowSectionOption_SetRowGap(null,0,0f) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_GetRowGap() {
        val ret = OH_ArkUI_WaterFlowSectionOption_GetRowGap(null, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_GetRowGap(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_SetMargin() {
        OH_ArkUI_WaterFlowSectionOption_SetMargin(null, 0, 0f, 0f, 0f, 0f)
        logLine("OH_ArkUI_WaterFlowSectionOption_SetMargin(null,0,0f,0f,0f,0f) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_GetMargin() {
        val ret = OH_ArkUI_WaterFlowSectionOption_GetMargin(null, 0)
        logLine("OH_ArkUI_WaterFlowSectionOption_GetMargin(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_RegisterGetItemMainSizeCallbackByIndex() {
        OH_ArkUI_WaterFlowSectionOption_RegisterGetItemMainSizeCallbackByIndex(null, 0, null)
        logLine("OH_ArkUI_WaterFlowSectionOption_RegisterGetItemMainSizeCallbackByIndex(null,0,null) done")
    }

    @Test
    fun testOH_ArkUI_WaterFlowSectionOption_RegisterGetItemMainSizeCallbackByIndexWithUserData() {
        OH_ArkUI_WaterFlowSectionOption_RegisterGetItemMainSizeCallbackByIndexWithUserData(null, 0, null, null)
        logLine("OH_ArkUI_WaterFlowSectionOption_RegisterGetItemMainSizeCallbackByIndexWithUserData(null,0,null,null) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_Create() {
        val ret = OH_ArkUI_SwiperIndicator_Create(ArkUI_SwiperIndicatorType.ARKUI_SWIPER_INDICATOR_TYPE_DOT)
        logLine("OH_ArkUI_SwiperIndicator_Create(DOT)=$ret")
        ret?.let {  }
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_Dispose() {
        OH_ArkUI_SwiperIndicator_Dispose(null)
        logLine("OH_ArkUI_SwiperIndicator_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetStartPosition() {
        OH_ArkUI_SwiperIndicator_SetStartPosition(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetStartPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetStartPosition() {
        val ret = OH_ArkUI_SwiperIndicator_GetStartPosition(null)
        logLine("OH_ArkUI_SwiperIndicator_GetStartPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetTopPosition() {
        OH_ArkUI_SwiperIndicator_SetTopPosition(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetTopPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetTopPosition() {
        val ret = OH_ArkUI_SwiperIndicator_GetTopPosition(null)
        logLine("OH_ArkUI_SwiperIndicator_GetTopPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetEndPosition() {
        OH_ArkUI_SwiperIndicator_SetEndPosition(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetEndPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetEndPosition() {
        val ret = OH_ArkUI_SwiperIndicator_GetEndPosition(null)
        logLine("OH_ArkUI_SwiperIndicator_GetEndPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetBottomPosition() {
        OH_ArkUI_SwiperIndicator_SetBottomPosition(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetBottomPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetBottomPosition() {
        val ret = OH_ArkUI_SwiperIndicator_GetBottomPosition(null)
        logLine("OH_ArkUI_SwiperIndicator_GetBottomPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetItemWidth() {
        OH_ArkUI_SwiperIndicator_SetItemWidth(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetItemWidth(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetItemWidth() {
        val ret = OH_ArkUI_SwiperIndicator_GetItemWidth(null)
        logLine("OH_ArkUI_SwiperIndicator_GetItemWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetItemHeight() {
        OH_ArkUI_SwiperIndicator_SetItemHeight(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetItemHeight(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetItemHeight() {
        val ret = OH_ArkUI_SwiperIndicator_GetItemHeight(null)
        logLine("OH_ArkUI_SwiperIndicator_GetItemHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetSelectedItemWidth() {
        OH_ArkUI_SwiperIndicator_SetSelectedItemWidth(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetSelectedItemWidth(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetSelectedItemWidth() {
        val ret = OH_ArkUI_SwiperIndicator_GetSelectedItemWidth(null)
        logLine("OH_ArkUI_SwiperIndicator_GetSelectedItemWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetSelectedItemHeight() {
        OH_ArkUI_SwiperIndicator_SetSelectedItemHeight(null, 0f)
        logLine("OH_ArkUI_SwiperIndicator_SetSelectedItemHeight(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetSelectedItemHeight() {
        val ret = OH_ArkUI_SwiperIndicator_GetSelectedItemHeight(null)
        logLine("OH_ArkUI_SwiperIndicator_GetSelectedItemHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetMask() {
        OH_ArkUI_SwiperIndicator_SetMask(null, 0)
        logLine("OH_ArkUI_SwiperIndicator_SetMask(null,0) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetMask() {
        val ret = OH_ArkUI_SwiperIndicator_GetMask(null)
        logLine("OH_ArkUI_SwiperIndicator_GetMask(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetColor() {
        OH_ArkUI_SwiperIndicator_SetColor(null, 0u)
        logLine("OH_ArkUI_SwiperIndicator_SetColor(null,0u) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetColor() {
        val ret = OH_ArkUI_SwiperIndicator_GetColor(null)
        logLine("OH_ArkUI_SwiperIndicator_GetColor(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetSelectedColor() {
        OH_ArkUI_SwiperIndicator_SetSelectedColor(null, 0u)
        logLine("OH_ArkUI_SwiperIndicator_SetSelectedColor(null,0u) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetSelectedColor() {
        val ret = OH_ArkUI_SwiperIndicator_GetSelectedColor(null)
        logLine("OH_ArkUI_SwiperIndicator_GetSelectedColor(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetMaxDisplayCount() {
        val ret = OH_ArkUI_SwiperIndicator_SetMaxDisplayCount(null, 0)
        logLine("OH_ArkUI_SwiperIndicator_SetMaxDisplayCount(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetMaxDisplayCount() {
        val ret = OH_ArkUI_SwiperIndicator_GetMaxDisplayCount(null)
        logLine("OH_ArkUI_SwiperIndicator_GetMaxDisplayCount(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetIgnoreSizeOfBottom() {
        try { OH_ArkUI_SwiperIndicator_SetIgnoreSizeOfBottom(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperIndicator_SetIgnoreSizeOfBottom (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperIndicator_SetIgnoreSizeOfBottom(null,0) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetIgnoreSizeOfBottom() {
        val ret = try { OH_ArkUI_SwiperIndicator_GetIgnoreSizeOfBottom(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperIndicator_GetIgnoreSizeOfBottom (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SwiperIndicator_GetIgnoreSizeOfBottom(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_SetSpace() {
        try { OH_ArkUI_SwiperIndicator_SetSpace(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperIndicator_SetSpace (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperIndicator_SetSpace(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperIndicator_GetSpace() {
        val ret = try { OH_ArkUI_SwiperIndicator_GetSpace(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperIndicator_GetSpace (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperIndicator_GetSpace(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_Create() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_Create() } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_Create (API 19) exception: $e"); null }
        logLine("OH_ArkUI_SwiperDigitIndicator_Create()=$ret")
        try { OH_ArkUI_SwiperDigitIndicator_Destroy(ret) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_Destroy (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetStartPosition() {
        try { OH_ArkUI_SwiperDigitIndicator_SetStartPosition(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetStartPosition (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetStartPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetStartPosition() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetStartPosition(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetStartPosition (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetStartPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetTopPosition() {
        try { OH_ArkUI_SwiperDigitIndicator_SetTopPosition(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetTopPosition (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetTopPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetTopPosition() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetTopPosition(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetTopPosition (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetTopPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetEndPosition() {
        try { OH_ArkUI_SwiperDigitIndicator_SetEndPosition(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetEndPosition (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetEndPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetEndPosition() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetEndPosition(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetEndPosition (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetEndPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetBottomPosition() {
        try { OH_ArkUI_SwiperDigitIndicator_SetBottomPosition(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetBottomPosition (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetBottomPosition(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetBottomPosition() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetBottomPosition(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetBottomPosition (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetBottomPosition(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetFontColor() {
        try { OH_ArkUI_SwiperDigitIndicator_SetFontColor(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetFontColor (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetFontColor(null,0u) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetFontColor() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetFontColor(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetFontColor (API 19) exception: $e"); 0u }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetFontColor(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetSelectedFontColor() {
        try { OH_ArkUI_SwiperDigitIndicator_SetSelectedFontColor(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetSelectedFontColor (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetSelectedFontColor(null,0u) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetSelectedFontColor() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetSelectedFontColor(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetSelectedFontColor (API 19) exception: $e"); 0u }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetSelectedFontColor(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetFontSize() {
        try { OH_ArkUI_SwiperDigitIndicator_SetFontSize(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetFontSize (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetFontSize(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetFontSize() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetFontSize(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetFontSize (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetFontSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetSelectedFontSize() {
        try { OH_ArkUI_SwiperDigitIndicator_SetSelectedFontSize(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetSelectedFontSize (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetSelectedFontSize(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetSelectedFontSize() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetSelectedFontSize(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetSelectedFontSize (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetSelectedFontSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetFontWeight() {
        try { OH_ArkUI_SwiperDigitIndicator_SetFontWeight(null, ARKUI_FONT_WEIGHT_NORMAL) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetFontWeight (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetFontWeight(null,NORMAL) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetFontWeight() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetFontWeight(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetFontWeight (API 19) exception: $e"); ARKUI_FONT_WEIGHT_NORMAL }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetFontWeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetSelectedFontWeight() {
        try { OH_ArkUI_SwiperDigitIndicator_SetSelectedFontWeight(null, ARKUI_FONT_WEIGHT_NORMAL) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetSelectedFontWeight (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetSelectedFontWeight(null,NORMAL) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetSelectedFontWeight() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetSelectedFontWeight(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetSelectedFontWeight (API 19) exception: $e"); ARKUI_FONT_WEIGHT_NORMAL }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetSelectedFontWeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_Destroy() {
        try { OH_ArkUI_SwiperDigitIndicator_Destroy(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_Destroy (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_Destroy(null) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_SetIgnoreSizeOfBottom() {
        try { OH_ArkUI_SwiperDigitIndicator_SetIgnoreSizeOfBottom(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_SetIgnoreSizeOfBottom (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperDigitIndicator_SetIgnoreSizeOfBottom(null,0) done")
    }

    @Test
    fun testOH_ArkUI_SwiperDigitIndicator_GetIgnoreSizeOfBottom() {
        val ret = try { OH_ArkUI_SwiperDigitIndicator_GetIgnoreSizeOfBottom(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperDigitIndicator_GetIgnoreSizeOfBottom (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SwiperDigitIndicator_GetIgnoreSizeOfBottom(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_Create() {
        val ret = OH_ArkUI_GuidelineOption_Create(0)
        logLine("OH_ArkUI_GuidelineOption_Create(0)=$ret")
        OH_ArkUI_GuidelineOption_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_Dispose() {
        OH_ArkUI_GuidelineOption_Dispose(null)
        logLine("OH_ArkUI_GuidelineOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_SetId() {
        OH_ArkUI_GuidelineOption_SetId(null, null, 0)
        logLine("OH_ArkUI_GuidelineOption_SetId(null,null,0) done")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_GetId() {
        val ret = OH_ArkUI_GuidelineOption_GetId(null, 0)
        logLine("OH_ArkUI_GuidelineOption_GetId(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_GetDirection() {
        val ret = OH_ArkUI_GuidelineOption_GetDirection(null, 0)
        logLine("OH_ArkUI_GuidelineOption_GetDirection(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_SetDirection() {
        OH_ArkUI_GuidelineOption_SetDirection(null, ARKUI_AXIS_VERTICAL, 0)
        logLine("OH_ArkUI_GuidelineOption_SetDirection(null,VERTICAL,0) done")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_SetPositionStart() {
        OH_ArkUI_GuidelineOption_SetPositionStart(null, 0f, 0)
        logLine("OH_ArkUI_GuidelineOption_SetPositionStart(null,0f,0) done")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_SetPositionEnd() {
        OH_ArkUI_GuidelineOption_SetPositionEnd(null, 0f, 0)
        logLine("OH_ArkUI_GuidelineOption_SetPositionEnd(null,0f,0) done")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_GetPositionStart() {
        val ret = OH_ArkUI_GuidelineOption_GetPositionStart(null, 0)
        logLine("OH_ArkUI_GuidelineOption_GetPositionStart(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_GuidelineOption_GetPositionEnd() {
        val ret = OH_ArkUI_GuidelineOption_GetPositionEnd(null, 0)
        logLine("OH_ArkUI_GuidelineOption_GetPositionEnd(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_Create() {
        val ret = OH_ArkUI_BarrierOption_Create(0)
        logLine("OH_ArkUI_BarrierOption_Create(0)=$ret")
        OH_ArkUI_BarrierOption_Dispose(ret) 
    }

    @Test
    fun testOH_ArkUI_BarrierOption_Dispose() {
        OH_ArkUI_BarrierOption_Dispose(null)
        logLine("OH_ArkUI_BarrierOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_GetDirection() {
        val ret = OH_ArkUI_BarrierOption_GetDirection(null, 0)
        logLine("OH_ArkUI_BarrierOption_GetDirection(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_SetId() {
        OH_ArkUI_BarrierOption_SetId(null, null, 0)
        logLine("OH_ArkUI_BarrierOption_SetId(null,null,0) done")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_SetDirection() {
        OH_ArkUI_BarrierOption_SetDirection(null, ARKUI_BARRIER_DIRECTION_START, 0)
        logLine("OH_ArkUI_BarrierOption_SetDirection(null,START,0) done")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_SetReferencedId() {
        OH_ArkUI_BarrierOption_SetReferencedId(null, null, 0)
        logLine("OH_ArkUI_BarrierOption_SetReferencedId(null,null,0) done")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_GetId() {
        val ret = OH_ArkUI_BarrierOption_GetId(null, 0)
        logLine("OH_ArkUI_BarrierOption_GetId(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_GetReferencedId() {
        val ret = OH_ArkUI_BarrierOption_GetReferencedId(null, 0, 0)
        logLine("OH_ArkUI_BarrierOption_GetReferencedId(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_BarrierOption_GetReferencedIdSize() {
        val ret = OH_ArkUI_BarrierOption_GetReferencedIdSize(null, 0)
        logLine("OH_ArkUI_BarrierOption_GetReferencedIdSize(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_ContentTransitionEffect_Create() {
        val ret = try { OH_ArkUI_ContentTransitionEffect_Create(0) } catch (e: Throwable) { logLine("OH_ArkUI_ContentTransitionEffect_Create (API 21) exception: $e"); null }
        logLine("OH_ArkUI_ContentTransitionEffect_Create(0)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_Create() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_Create() } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_Create (API 19) exception: $e"); null }
        logLine("OH_ArkUI_SwiperArrowStyle_Create()=$ret")
        try { OH_ArkUI_SwiperArrowStyle_Destroy(ret) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_Destroy (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_SetShowBackground() {
        try { OH_ArkUI_SwiperArrowStyle_SetShowBackground(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_SetShowBackground (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_SetShowBackground(null,0) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_GetShowBackground() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_GetShowBackground(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_GetShowBackground (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SwiperArrowStyle_GetShowBackground(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_SetShowSidebarMiddle() {
        try { OH_ArkUI_SwiperArrowStyle_SetShowSidebarMiddle(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_SetShowSidebarMiddle (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_SetShowSidebarMiddle(null,0) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_GetShowSidebarMiddle() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_GetShowSidebarMiddle(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_GetShowSidebarMiddle (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_SwiperArrowStyle_GetShowSidebarMiddle(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_SetBackgroundSize() {
        try { OH_ArkUI_SwiperArrowStyle_SetBackgroundSize(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_SetBackgroundSize (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_SetBackgroundSize(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_GetBackgroundSize() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_GetBackgroundSize(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_GetBackgroundSize (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperArrowStyle_GetBackgroundSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_Destroy() {
        try { OH_ArkUI_SwiperArrowStyle_Destroy(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_Destroy (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_Destroy(null) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_SetBackgroundColor() {
        try { OH_ArkUI_SwiperArrowStyle_SetBackgroundColor(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_SetBackgroundColor (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_SetBackgroundColor(null,0u) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_GetBackgroundColor() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_GetBackgroundColor(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_GetBackgroundColor (API 19) exception: $e"); 0u }
        logLine("OH_ArkUI_SwiperArrowStyle_GetBackgroundColor(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_SetArrowSize() {
        try { OH_ArkUI_SwiperArrowStyle_SetArrowSize(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_SetArrowSize (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_SetArrowSize(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_GetArrowSize() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_GetArrowSize(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_GetArrowSize (API 19) exception: $e"); 0f }
        logLine("OH_ArkUI_SwiperArrowStyle_GetArrowSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_SetArrowColor() {
        try { OH_ArkUI_SwiperArrowStyle_SetArrowColor(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_SetArrowColor (API 19) exception: $e") }
        logLine("OH_ArkUI_SwiperArrowStyle_SetArrowColor(null,0u) done")
    }

    @Test
    fun testOH_ArkUI_SwiperArrowStyle_GetArrowColor() {
        val ret = try { OH_ArkUI_SwiperArrowStyle_GetArrowColor(null) } catch (e: Throwable) { logLine("OH_ArkUI_SwiperArrowStyle_GetArrowColor (API 19) exception: $e"); 0u }
        logLine("OH_ArkUI_SwiperArrowStyle_GetArrowColor(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_Create() {
        val ret = OH_ArkUI_AlignmentRuleOption_Create()
        logLine("OH_ArkUI_AlignmentRuleOption_Create()=$ret")
        OH_ArkUI_AlignmentRuleOption_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_Dispose() {
        OH_ArkUI_AlignmentRuleOption_Dispose(null)
        logLine("OH_ArkUI_AlignmentRuleOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetBiasHorizontal() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetBiasHorizontal(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetBiasHorizontal(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetStart() {
        OH_ArkUI_AlignmentRuleOption_SetStart(null, null, ARKUI_HORIZONTAL_ALIGNMENT_START)
        logLine("OH_ArkUI_AlignmentRuleOption_SetStart(null,null,START) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetEnd() {
        OH_ArkUI_AlignmentRuleOption_SetEnd(null, null, ARKUI_HORIZONTAL_ALIGNMENT_END)
        logLine("OH_ArkUI_AlignmentRuleOption_SetEnd(null,null,END) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetCenterHorizontal() {
        OH_ArkUI_AlignmentRuleOption_SetCenterHorizontal(null, null, ARKUI_HORIZONTAL_ALIGNMENT_CENTER)
        logLine("OH_ArkUI_AlignmentRuleOption_SetCenterHorizontal(null,null,CENTER) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetTop() {
        OH_ArkUI_AlignmentRuleOption_SetTop(null, null, ARKUI_VERTICAL_ALIGNMENT_TOP)
        logLine("OH_ArkUI_AlignmentRuleOption_SetTop(null,null,TOP) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetBottom() {
        OH_ArkUI_AlignmentRuleOption_SetBottom(null, null, ARKUI_VERTICAL_ALIGNMENT_BOTTOM)
        logLine("OH_ArkUI_AlignmentRuleOption_SetBottom(null,null,BOTTOM) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetCenterVertical() {
        OH_ArkUI_AlignmentRuleOption_SetCenterVertical(null, null, ARKUI_VERTICAL_ALIGNMENT_CENTER)
        logLine("OH_ArkUI_AlignmentRuleOption_SetCenterVertical(null,null,CENTER) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetBiasHorizontal() {
        OH_ArkUI_AlignmentRuleOption_SetBiasHorizontal(null, 0f)
        logLine("OH_ArkUI_AlignmentRuleOption_SetBiasHorizontal(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_SetBiasVertical() {
        OH_ArkUI_AlignmentRuleOption_SetBiasVertical(null, 0f)
        logLine("OH_ArkUI_AlignmentRuleOption_SetBiasVertical(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetStartId() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetStartId(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetStartId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetStartAlignment() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetStartAlignment(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetStartAlignment(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetEndId() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetEndId(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetEndId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetEndAlignment() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetEndAlignment(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetEndAlignment(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetCenterIdHorizontal() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetCenterIdHorizontal(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetCenterIdHorizontal(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetCenterAlignmentHorizontal() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetCenterAlignmentHorizontal(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetCenterAlignmentHorizontal(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetTopId() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetTopId(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetTopId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetTopAlignment() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetTopAlignment(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetTopAlignment(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetBottomId() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetBottomId(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetBottomId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetBottomAlignment() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetBottomAlignment(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetBottomAlignment(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetCenterIdVertical() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetCenterIdVertical(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetCenterIdVertical(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetCenterAlignmentVertical() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetCenterAlignmentVertical(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetCenterAlignmentVertical(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AlignmentRuleOption_GetBiasVertical() {
        val ret = OH_ArkUI_AlignmentRuleOption_GetBiasVertical(null)
        logLine("OH_ArkUI_AlignmentRuleOption_GetBiasVertical(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_Create() {
        val ret = OH_ArkUI_ListItemSwipeActionItem_Create()
        logLine("OH_ArkUI_ListItemSwipeActionItem_Create()=$ret")
        OH_ArkUI_ListItemSwipeActionItem_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_Dispose() {
        OH_ArkUI_ListItemSwipeActionItem_Dispose(null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetContent() {
        OH_ArkUI_ListItemSwipeActionItem_SetContent(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetContent(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetActionAreaDistance() {
        OH_ArkUI_ListItemSwipeActionItem_SetActionAreaDistance(null, 0f)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetActionAreaDistance(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_GetActionAreaDistance() {
        val ret = OH_ArkUI_ListItemSwipeActionItem_GetActionAreaDistance(null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_GetActionAreaDistance(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnEnterActionArea() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnEnterActionArea(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnEnterActionArea(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnEnterActionAreaWithUserData() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnEnterActionAreaWithUserData(null, null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnEnterActionAreaWithUserData(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnAction() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnAction(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnAction(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnActionWithUserData() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnActionWithUserData(null, null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnActionWithUserData(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnExitActionArea() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnExitActionArea(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnExitActionArea(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnExitActionAreaWithUserData() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnExitActionAreaWithUserData(null, null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnExitActionAreaWithUserData(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnStateChange() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnStateChange(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnStateChange(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionItem_SetOnStateChangeWithUserData() {
        OH_ArkUI_ListItemSwipeActionItem_SetOnStateChangeWithUserData(null, null, null)
        logLine("OH_ArkUI_ListItemSwipeActionItem_SetOnStateChangeWithUserData(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_Create() {
        val ret = OH_ArkUI_ListItemSwipeActionOption_Create()
        logLine("OH_ArkUI_ListItemSwipeActionOption_Create()=$ret")
        OH_ArkUI_ListItemSwipeActionOption_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_Dispose() {
        OH_ArkUI_ListItemSwipeActionOption_Dispose(null)
        logLine("OH_ArkUI_ListItemSwipeActionOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_SetStart() {
        OH_ArkUI_ListItemSwipeActionOption_SetStart(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionOption_SetStart(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_SetEnd() {
        OH_ArkUI_ListItemSwipeActionOption_SetEnd(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionOption_SetEnd(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_SetEdgeEffect() {
        OH_ArkUI_ListItemSwipeActionOption_SetEdgeEffect(null, ARKUI_LIST_ITEM_SWIPE_EDGE_EFFECT_SPRING)
        logLine("OH_ArkUI_ListItemSwipeActionOption_SetEdgeEffect(null,SPRING) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_GetEdgeEffect() {
        val ret = OH_ArkUI_ListItemSwipeActionOption_GetEdgeEffect(null)
        logLine("OH_ArkUI_ListItemSwipeActionOption_GetEdgeEffect(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_SetOnOffsetChange() {
        OH_ArkUI_ListItemSwipeActionOption_SetOnOffsetChange(null, null)
        logLine("OH_ArkUI_ListItemSwipeActionOption_SetOnOffsetChange(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeActionOption_SetOnOffsetChangeWithUserData() {
        OH_ArkUI_ListItemSwipeActionOption_SetOnOffsetChangeWithUserData(null, null, null)
        logLine("OH_ArkUI_ListItemSwipeActionOption_SetOnOffsetChangeWithUserData(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_Create() {
        val ret = OH_ArkUI_ListChildrenMainSizeOption_Create()
        logLine("OH_ArkUI_ListChildrenMainSizeOption_Create()=$ret")
        OH_ArkUI_ListChildrenMainSizeOption_Dispose(ret)
    }

    fun testOH_ArkUI_ListChildrenMainSizeOption_Dispose() {
        OH_ArkUI_ListChildrenMainSizeOption_Dispose(null)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_SetDefaultMainSize() {
        val ret = OH_ArkUI_ListChildrenMainSizeOption_SetDefaultMainSize(null, 0f)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_SetDefaultMainSize(null,0f)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_GetDefaultMainSize() {
        val ret = OH_ArkUI_ListChildrenMainSizeOption_GetDefaultMainSize(null)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_GetDefaultMainSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_Resize() {
        OH_ArkUI_ListChildrenMainSizeOption_Resize(null, 0)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_Resize(null,0) done")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_Splice() {
        val ret = OH_ArkUI_ListChildrenMainSizeOption_Splice(null, 0, 0, 0)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_Splice(null,0,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_UpdateSize() {
        val ret = OH_ArkUI_ListChildrenMainSizeOption_UpdateSize(null, 0, 0f)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_UpdateSize(null,0,0f)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListChildrenMainSizeOption_GetMainSize() {
        val ret = OH_ArkUI_ListChildrenMainSizeOption_GetMainSize(null, 0)
        logLine("OH_ArkUI_ListChildrenMainSizeOption_GetMainSize(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomSpanMeasureInfo_Create() {
        val ret = OH_ArkUI_CustomSpanMeasureInfo_Create()
        logLine("OH_ArkUI_CustomSpanMeasureInfo_Create()=$ret")
        OH_ArkUI_CustomSpanMeasureInfo_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_CustomSpanMeasureInfo_Dispose() {
        OH_ArkUI_CustomSpanMeasureInfo_Dispose(null)
        logLine("OH_ArkUI_CustomSpanMeasureInfo_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_CustomSpanMeasureInfo_GetFontSize() {
        val ret = OH_ArkUI_CustomSpanMeasureInfo_GetFontSize(null)
        logLine("OH_ArkUI_CustomSpanMeasureInfo_GetFontSize(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomSpanMetrics_Create() {
        val ret = OH_ArkUI_CustomSpanMetrics_Create()
        logLine("OH_ArkUI_CustomSpanMetrics_Create()=$ret")
        OH_ArkUI_CustomSpanMetrics_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_CustomSpanMetrics_Dispose() {
        OH_ArkUI_CustomSpanMetrics_Dispose(null)
        logLine("OH_ArkUI_CustomSpanMetrics_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_CustomSpanMetrics_SetWidth() {
        OH_ArkUI_CustomSpanMetrics_SetWidth(null, 0f)
        logLine("OH_ArkUI_CustomSpanMetrics_SetWidth(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_CustomSpanMetrics_SetHeight() {
        OH_ArkUI_CustomSpanMetrics_SetHeight(null, 0f)
        logLine("OH_ArkUI_CustomSpanMetrics_SetHeight(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_CustomSpanDrawInfo_Create() {
        val ret = OH_ArkUI_CustomSpanDrawInfo_Create()
        logLine("OH_ArkUI_CustomSpanDrawInfo_Create()=$ret")
        OH_ArkUI_CustomSpanDrawInfo_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_CustomSpanDrawInfo_Dispose() {
        OH_ArkUI_CustomSpanDrawInfo_Dispose(null)
        logLine("OH_ArkUI_CustomSpanDrawInfo_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_CustomSpanDrawInfo_GetXOffset() {
        val ret = OH_ArkUI_CustomSpanDrawInfo_GetXOffset(null)
        logLine("OH_ArkUI_CustomSpanDrawInfo_GetXOffset(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomSpanDrawInfo_GetLineTop() {
        val ret = OH_ArkUI_CustomSpanDrawInfo_GetLineTop(null)
        logLine("OH_ArkUI_CustomSpanDrawInfo_GetLineTop(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomSpanDrawInfo_GetLineBottom() {
        val ret = OH_ArkUI_CustomSpanDrawInfo_GetLineBottom(null)
        logLine("OH_ArkUI_CustomSpanDrawInfo_GetLineBottom(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomSpanDrawInfo_GetBaseline() {
        val ret = OH_ArkUI_CustomSpanDrawInfo_GetBaseline(null)
        logLine("OH_ArkUI_CustomSpanDrawInfo_GetBaseline(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_CreateFromString() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_CreateFromString(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_CreateFromString(null)=$ret")
        OH_ArkUI_ImageAnimatorFrameInfo_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_CreateFromDrawableDescriptor() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_CreateFromDrawableDescriptor(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_CreateFromDrawableDescriptor(null)=$ret")
        OH_ArkUI_ImageAnimatorFrameInfo_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_Dispose() {
        OH_ArkUI_ImageAnimatorFrameInfo_Dispose(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_SetWidth() {
        OH_ArkUI_ImageAnimatorFrameInfo_SetWidth(null, 0)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_SetWidth(null,0) done")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_GetWidth() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_GetWidth(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_GetWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_SetHeight() {
        OH_ArkUI_ImageAnimatorFrameInfo_SetHeight(null, 0)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_SetHeight(null,0) done")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_GetHeight() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_GetHeight(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_GetHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_SetTop() {
        OH_ArkUI_ImageAnimatorFrameInfo_SetTop(null, 0)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_SetTop(null,0) done")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_GetTop() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_GetTop(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_GetTop(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_SetLeft() {
        OH_ArkUI_ImageAnimatorFrameInfo_SetLeft(null, 0)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_SetLeft(null,0) done")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_GetLeft() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_GetLeft(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_GetLeft(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_SetDuration() {
        OH_ArkUI_ImageAnimatorFrameInfo_SetDuration(null, 0)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_SetDuration(null,0) done")
    }

    @Test
    fun testOH_ArkUI_ImageAnimatorFrameInfo_GetDuration() {
        val ret = OH_ArkUI_ImageAnimatorFrameInfo_GetDuration(null)
        logLine("OH_ArkUI_ImageAnimatorFrameInfo_GetDuration(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_Create() {
        val ret = OH_ArkUI_AccessibilityState_Create()
        logLine("OH_ArkUI_AccessibilityState_Create()=$ret")
        OH_ArkUI_AccessibilityState_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_Dispose() {
        OH_ArkUI_AccessibilityState_Dispose(null)
        logLine("OH_ArkUI_AccessibilityState_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_SetDisabled() {
        OH_ArkUI_AccessibilityState_SetDisabled(null, 0)
        logLine("OH_ArkUI_AccessibilityState_SetDisabled(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_IsDisabled() {
        val ret = OH_ArkUI_AccessibilityState_IsDisabled(null)
        logLine("OH_ArkUI_AccessibilityState_IsDisabled(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_SetSelected() {
        OH_ArkUI_AccessibilityState_SetSelected(null, 0)
        logLine("OH_ArkUI_AccessibilityState_SetSelected(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_IsSelected() {
        val ret = OH_ArkUI_AccessibilityState_IsSelected(null)
        logLine("OH_ArkUI_AccessibilityState_IsSelected(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_SetCheckedState() {
        OH_ArkUI_AccessibilityState_SetCheckedState(null, 0)
        logLine("OH_ArkUI_AccessibilityState_SetCheckedState(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityState_GetCheckedState() {
        val ret = OH_ArkUI_AccessibilityState_GetCheckedState(null)
        logLine("OH_ArkUI_AccessibilityState_GetCheckedState(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_Create() {
        val ret = OH_ArkUI_AccessibilityValue_Create()
        logLine("OH_ArkUI_AccessibilityValue_Create()=$ret")
        OH_ArkUI_AccessibilityValue_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_Dispose() {
        OH_ArkUI_AccessibilityValue_Dispose(null)
        logLine("OH_ArkUI_AccessibilityValue_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetMin() {
        OH_ArkUI_AccessibilityValue_SetMin(null, 0)
        logLine("OH_ArkUI_AccessibilityValue_SetMin(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetMin() {
        val ret = OH_ArkUI_AccessibilityValue_GetMin(null)
        logLine("OH_ArkUI_AccessibilityValue_GetMin(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetMax() {
        OH_ArkUI_AccessibilityValue_SetMax(null, 0)
        logLine("OH_ArkUI_AccessibilityValue_SetMax(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetMax() {
        val ret = OH_ArkUI_AccessibilityValue_GetMax(null)
        logLine("OH_ArkUI_AccessibilityValue_GetMax(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetCurrent() {
        OH_ArkUI_AccessibilityValue_SetCurrent(null, 0)
        logLine("OH_ArkUI_AccessibilityValue_SetCurrent(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetCurrent() {
        val ret = OH_ArkUI_AccessibilityValue_GetCurrent(null)
        logLine("OH_ArkUI_AccessibilityValue_GetCurrent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetRangeMin() {
        try { OH_ArkUI_AccessibilityValue_SetRangeMin(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityValue_SetRangeMin (API 18) exception: $e") }
        logLine("OH_ArkUI_AccessibilityValue_SetRangeMin(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetRangeMin() {
        val ret = try { OH_ArkUI_AccessibilityValue_GetRangeMin(null) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityValue_GetRangeMin (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_AccessibilityValue_GetRangeMin(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetRangeMax() {
        try { OH_ArkUI_AccessibilityValue_SetRangeMax(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityValue_SetRangeMax (API 18) exception: $e") }
        logLine("OH_ArkUI_AccessibilityValue_SetRangeMax(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetRangeMax() {
        val ret = try { OH_ArkUI_AccessibilityValue_GetRangeMax(null) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityValue_GetRangeMax (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_AccessibilityValue_GetRangeMax(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetRangeCurrent() {
        try { OH_ArkUI_AccessibilityValue_SetRangeCurrent(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityValue_SetRangeCurrent (API 18) exception: $e") }
        logLine("OH_ArkUI_AccessibilityValue_SetRangeCurrent(null,0) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetRangeCurrent() {
        val ret = try { OH_ArkUI_AccessibilityValue_GetRangeCurrent(null) } catch (e: Throwable) { logLine("OH_ArkUI_AccessibilityValue_GetRangeCurrent (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_AccessibilityValue_GetRangeCurrent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_SetText() {
        OH_ArkUI_AccessibilityValue_SetText(null, null)
        logLine("OH_ArkUI_AccessibilityValue_SetText(null,null) done")
    }

    @Test
    fun testOH_ArkUI_AccessibilityValue_GetText() {
        val ret = OH_ArkUI_AccessibilityValue_GetText(null)
        logLine("OH_ArkUI_AccessibilityValue_GetText(null)=$ret")
    }

    // @Test
    // fun testOH_ArkUI_CustomProperty_GetStringValue() {
    //     val ret = OH_ArkUI_CustomProperty_GetStringValue(null)
    //     logLine("OH_ArkUI_CustomProperty_GetStringValue(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_HostWindowInfo_GetName() {
    //     val ret = OH_ArkUI_HostWindowInfo_GetName(null)
    //     logLine("OH_ArkUI_HostWindowInfo_GetName(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_HostWindowInfo_Destroy() {
    //     OH_ArkUI_HostWindowInfo_Destroy(null)
    //     logLine("OH_ArkUI_HostWindowInfo_Destroy(null) done")
    // }

    // @Test
    // fun testOH_ArkUI_ActiveChildrenInfo_Destroy() {
    //     OH_ArkUI_ActiveChildrenInfo_Destroy(null)
    //     logLine("OH_ArkUI_ActiveChildrenInfo_Destroy(null) done")
    // }

    // @Test
    // fun testOH_ArkUI_ActiveChildrenInfo_GetNodeByIndex() {
    //     val ret = OH_ArkUI_ActiveChildrenInfo_GetNodeByIndex(null, 0)
    //     logLine("OH_ArkUI_ActiveChildrenInfo_GetNodeByIndex(null,0)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_ActiveChildrenInfo_GetCount() {
    //     val ret = OH_ArkUI_ActiveChildrenInfo_GetCount(null)
    //     logLine("OH_ArkUI_ActiveChildrenInfo_GetCount(null)=$ret")
    // }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_Create() {
        val ret = OH_ArkUI_ProgressLinearStyleOption_Create()
        logLine("OH_ArkUI_ProgressLinearStyleOption_Create()=$ret")
        OH_ArkUI_ProgressLinearStyleOption_Destroy(ret)
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_Destroy() {
        OH_ArkUI_ProgressLinearStyleOption_Destroy(null)
        logLine("OH_ArkUI_ProgressLinearStyleOption_Destroy(null) done")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_SetScanEffectEnabled() {
        OH_ArkUI_ProgressLinearStyleOption_SetScanEffectEnabled(null, false)
        logLine("OH_ArkUI_ProgressLinearStyleOption_SetScanEffectEnabled(null,false) done")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_SetSmoothEffectEnabled() {
        OH_ArkUI_ProgressLinearStyleOption_SetSmoothEffectEnabled(null, false)
        logLine("OH_ArkUI_ProgressLinearStyleOption_SetSmoothEffectEnabled(null,false) done")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_SetStrokeWidth() {
        OH_ArkUI_ProgressLinearStyleOption_SetStrokeWidth(null, 0f)
        logLine("OH_ArkUI_ProgressLinearStyleOption_SetStrokeWidth(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_SetStrokeRadius() {
        OH_ArkUI_ProgressLinearStyleOption_SetStrokeRadius(null, 0f)
        logLine("OH_ArkUI_ProgressLinearStyleOption_SetStrokeRadius(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_GetScanEffectEnabled() {
        val ret = OH_ArkUI_ProgressLinearStyleOption_GetScanEffectEnabled(null)
        logLine("OH_ArkUI_ProgressLinearStyleOption_GetScanEffectEnabled(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_GetSmoothEffectEnabled() {
        val ret = OH_ArkUI_ProgressLinearStyleOption_GetSmoothEffectEnabled(null)
        logLine("OH_ArkUI_ProgressLinearStyleOption_GetSmoothEffectEnabled(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_GetStrokeWidth() {
        val ret = OH_ArkUI_ProgressLinearStyleOption_GetStrokeWidth(null)
        logLine("OH_ArkUI_ProgressLinearStyleOption_GetStrokeWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_ProgressLinearStyleOption_GetStrokeRadius() {
        val ret = OH_ArkUI_ProgressLinearStyleOption_GetStrokeRadius(null)
        logLine("OH_ArkUI_ProgressLinearStyleOption_GetStrokeRadius(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CrossLanguageOption_Create() {
        val ret = OH_ArkUI_CrossLanguageOption_Create()
        logLine("OH_ArkUI_CrossLanguageOption_Create()=$ret")
        OH_ArkUI_CrossLanguageOption_Destroy(ret)
    }

    @Test
    fun testOH_ArkUI_CrossLanguageOption_Destroy() {
        OH_ArkUI_CrossLanguageOption_Destroy(null)
        logLine("OH_ArkUI_CrossLanguageOption_Destroy(null) done")
    }

    @Test
    fun testOH_ArkUI_CrossLanguageOption_SetAttributeSettingStatus() {
        OH_ArkUI_CrossLanguageOption_SetAttributeSettingStatus(null, false)
        logLine("OH_ArkUI_CrossLanguageOption_SetAttributeSettingStatus(null,false) done")
    }

    @Test
    fun testOH_ArkUI_CrossLanguageOption_GetAttributeSettingStatus() {
        val ret = OH_ArkUI_CrossLanguageOption_GetAttributeSettingStatus(null)
        logLine("OH_ArkUI_CrossLanguageOption_GetAttributeSettingStatus(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CreateSnapshotOptions() {
        val ret = OH_ArkUI_CreateSnapshotOptions()
        logLine("OH_ArkUI_CreateSnapshotOptions()=$ret")
        OH_ArkUI_DestroySnapshotOptions(ret)
    }

    @Test
    fun testOH_ArkUI_DestroySnapshotOptions() {
        OH_ArkUI_DestroySnapshotOptions(null)
        logLine("OH_ArkUI_DestroySnapshotOptions(null) done")
    }

    @Test
    fun testOH_ArkUI_SnapshotOptions_SetScale() {
        val ret = OH_ArkUI_SnapshotOptions_SetScale(null, 0f)
        logLine("OH_ArkUI_SnapshotOptions_SetScale(null,0f)=$ret")
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_Create() {
        val ret = OH_ArkUI_VisibleAreaEventOptions_Create()
        logLine("OH_ArkUI_VisibleAreaEventOptions_Create()=$ret")
        OH_ArkUI_VisibleAreaEventOptions_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_Dispose() {
        OH_ArkUI_VisibleAreaEventOptions_Dispose(null)
        logLine("OH_ArkUI_VisibleAreaEventOptions_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_SetRatios() {
        val ret = OH_ArkUI_VisibleAreaEventOptions_SetRatios(null, null, 0)
        logLine("OH_ArkUI_VisibleAreaEventOptions_SetRatios(null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_SetExpectedUpdateInterval() {
        val ret = OH_ArkUI_VisibleAreaEventOptions_SetExpectedUpdateInterval(null, 0)
        logLine("OH_ArkUI_VisibleAreaEventOptions_SetExpectedUpdateInterval(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_SetMeasureFromViewport() {
        val ret = try { OH_ArkUI_VisibleAreaEventOptions_SetMeasureFromViewport(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_VisibleAreaEventOptions_SetMeasureFromViewport (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_VisibleAreaEventOptions_SetMeasureFromViewport(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_GetRatios() {
        memScoped {
            val size = alloc<IntVar>()
            val ret = OH_ArkUI_VisibleAreaEventOptions_GetRatios(null, null, size.ptr)
            logLine("OH_ArkUI_VisibleAreaEventOptions_GetRatios(null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_GetExpectedUpdateInterval() {
        val ret = OH_ArkUI_VisibleAreaEventOptions_GetExpectedUpdateInterval(null)
        logLine("OH_ArkUI_VisibleAreaEventOptions_GetExpectedUpdateInterval(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_VisibleAreaEventOptions_GetMeasureFromViewport() {
        val ret = try { OH_ArkUI_VisibleAreaEventOptions_GetMeasureFromViewport(null) } catch (e: Throwable) { logLine("OH_ArkUI_VisibleAreaEventOptions_GetMeasureFromViewport (API 22) exception: $e"); false }
        logLine("OH_ArkUI_VisibleAreaEventOptions_GetMeasureFromViewport(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_TextPickerRangeContentArray_Create() {
        val ret = try { OH_ArkUI_TextPickerRangeContentArray_Create(0) } catch (e: Throwable) { logLine("OH_ArkUI_TextPickerRangeContentArray_Create (API 19) exception: $e"); null }
        logLine("OH_ArkUI_TextPickerRangeContentArray_Create(0)=$ret")
        try { OH_ArkUI_TextPickerRangeContentArray_Destroy(ret) } catch (e: Throwable) { logLine("OH_ArkUI_TextPickerRangeContentArray_Destroy (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextPickerRangeContentArray_SetIconAtIndex() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            try { OH_ArkUI_TextPickerRangeContentArray_SetIconAtIndex(null, buf, 0) } catch (e: Throwable) { logLine("OH_ArkUI_TextPickerRangeContentArray_SetIconAtIndex (API 19) exception: $e") }
            logLine("OH_ArkUI_TextPickerRangeContentArray_SetIconAtIndex(null,buf,0) done")
        }
    }

    @Test
    fun testOH_ArkUI_TextPickerRangeContentArray_SetTextAtIndex() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            try { OH_ArkUI_TextPickerRangeContentArray_SetTextAtIndex(null, buf, 0) } catch (e: Throwable) { logLine("OH_ArkUI_TextPickerRangeContentArray_SetTextAtIndex (API 19) exception: $e") }
            logLine("OH_ArkUI_TextPickerRangeContentArray_SetTextAtIndex(null,buf,0) done")
        }
    }

    @Test
    fun testOH_ArkUI_TextPickerRangeContentArray_Destroy() {
        try { OH_ArkUI_TextPickerRangeContentArray_Destroy(null) } catch (e: Throwable) { logLine("OH_ArkUI_TextPickerRangeContentArray_Destroy (API 19) exception: $e") }
        logLine("OH_ArkUI_TextPickerRangeContentArray_Destroy(null) done")
    }

    @Test
    fun testOH_ArkUI_TextCascadePickerRangeContentArray_Create() {
        val ret = try { OH_ArkUI_TextCascadePickerRangeContentArray_Create(0) } catch (e: Throwable) { logLine("OH_ArkUI_TextCascadePickerRangeContentArray_Create (API 19) exception: $e"); null }
        logLine("OH_ArkUI_TextCascadePickerRangeContentArray_Create(0)=$ret")
        try { OH_ArkUI_TextCascadePickerRangeContentArray_Destroy(ret) } catch (e: Throwable) { logLine("OH_ArkUI_TextCascadePickerRangeContentArray_Destroy (API 19) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextCascadePickerRangeContentArray_SetTextAtIndex() {
        memScoped {
            val buf = allocArray<ByteVar>(1)
            try { OH_ArkUI_TextCascadePickerRangeContentArray_SetTextAtIndex(null, buf, 0) } catch (e: Throwable) { logLine("OH_ArkUI_TextCascadePickerRangeContentArray_SetTextAtIndex (API 19) exception: $e") }
            logLine("OH_ArkUI_TextCascadePickerRangeContentArray_SetTextAtIndex(null,buf,0) done")
        }
    }

    @Test
    fun testOH_ArkUI_TextCascadePickerRangeContentArray_SetChildAtIndex() {
        try { OH_ArkUI_TextCascadePickerRangeContentArray_SetChildAtIndex(null, null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_TextCascadePickerRangeContentArray_SetChildAtIndex (API 19) exception: $e") }
        logLine("OH_ArkUI_TextCascadePickerRangeContentArray_SetChildAtIndex(null,null,0) done")
    }

    @Test
    fun testOH_ArkUI_TextCascadePickerRangeContentArray_Destroy() {
        try { OH_ArkUI_TextCascadePickerRangeContentArray_Destroy(null) } catch (e: Throwable) { logLine("OH_ArkUI_TextCascadePickerRangeContentArray_Destroy (API 19) exception: $e") }
        logLine("OH_ArkUI_TextCascadePickerRangeContentArray_Destroy(null) done")
    }

    @Test
    fun testOH_ArkUI_EmbeddedComponentOption_Create() {
        val ret = try { OH_ArkUI_EmbeddedComponentOption_Create() } catch (e: Throwable) { logLine("OH_ArkUI_EmbeddedComponentOption_Create (API 20) exception: $e"); null }
        logLine("OH_ArkUI_EmbeddedComponentOption_Create()=$ret")
        try { OH_ArkUI_EmbeddedComponentOption_Dispose(ret) } catch (e: Throwable) { logLine("OH_ArkUI_EmbeddedComponentOption_Dispose (API 20) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_EmbeddedComponentOption_Dispose() {
        try { OH_ArkUI_EmbeddedComponentOption_Dispose(null) } catch (e: Throwable) { logLine("OH_ArkUI_EmbeddedComponentOption_Dispose (API 20) exception: $e") }
        logLine("OH_ArkUI_EmbeddedComponentOption_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_EmbeddedComponentOption_SetOnError() {
        try { OH_ArkUI_EmbeddedComponentOption_SetOnError(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_EmbeddedComponentOption_SetOnError (API 20) exception: $e") }
        logLine("OH_ArkUI_EmbeddedComponentOption_SetOnError(null,null) done")
    }

    @Test
    fun testOH_ArkUI_EmbeddedComponentOption_SetOnTerminated() {
        try { OH_ArkUI_EmbeddedComponentOption_SetOnTerminated(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_EmbeddedComponentOption_SetOnTerminated (API 20) exception: $e") }
        logLine("OH_ArkUI_EmbeddedComponentOption_SetOnTerminated(null,null) done")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeAction_Expand() {
        val ret = try { OH_ArkUI_ListItemSwipeAction_Expand(null, ARKUI_LIST_ITEM_SWIPE_ACTION_DIRECTION_START) } catch (e: Throwable) { logLine("OH_ArkUI_ListItemSwipeAction_Expand (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_ListItemSwipeAction_Expand(null,START)=$ret")
    }

    @Test
    fun testOH_ArkUI_ListItemSwipeAction_Collapse() {
        val ret = try { OH_ArkUI_ListItemSwipeAction_Collapse(null) } catch (e: Throwable) { logLine("OH_ArkUI_ListItemSwipeAction_Collapse (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_ListItemSwipeAction_Collapse(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_Create() {
        val ret = try { OH_ArkUI_PositionEdges_Create() } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_Create (API 21) exception: $e"); null }
        logLine("OH_ArkUI_PositionEdges_Create()=$ret")
        try { OH_ArkUI_PositionEdges_Dispose(ret) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_Dispose (API 21) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PositionEdges_Copy() {
        val ret = try { OH_ArkUI_PositionEdges_Copy(null) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_Copy (API 21) exception: $e"); null }
        logLine("OH_ArkUI_PositionEdges_Copy(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_Dispose() {
        try { OH_ArkUI_PositionEdges_Dispose(null) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_Dispose (API 21) exception: $e") }
        logLine("OH_ArkUI_PositionEdges_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_SetTop() {
        try { OH_ArkUI_PositionEdges_SetTop(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_SetTop (API 21) exception: $e") }
        logLine("OH_ArkUI_PositionEdges_SetTop(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_GetTop() {
        memScoped {
            val v = alloc<FloatVar>()
            val ret = try { OH_ArkUI_PositionEdges_GetTop(null, v.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_GetTop (API 21) exception: $e"); -1 }
            logLine("OH_ArkUI_PositionEdges_GetTop(null,&v)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PositionEdges_SetLeft() {
        try { OH_ArkUI_PositionEdges_SetLeft(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_SetLeft (API 21) exception: $e") }
        logLine("OH_ArkUI_PositionEdges_SetLeft(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_GetLeft() {
        memScoped {
            val v = alloc<FloatVar>()
            val ret = try { OH_ArkUI_PositionEdges_GetLeft(null, v.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_GetLeft (API 21) exception: $e"); -1 }
            logLine("OH_ArkUI_PositionEdges_GetLeft(null,&v)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PositionEdges_SetBottom() {
        try { OH_ArkUI_PositionEdges_SetBottom(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_SetBottom (API 21) exception: $e") }
        logLine("OH_ArkUI_PositionEdges_SetBottom(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_GetBottom() {
        memScoped {
            val v = alloc<FloatVar>()
            val ret = try { OH_ArkUI_PositionEdges_GetBottom(null, v.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_GetBottom (API 21) exception: $e"); -1 }
            logLine("OH_ArkUI_PositionEdges_GetBottom(null,&v)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PositionEdges_SetRight() {
        try { OH_ArkUI_PositionEdges_SetRight(null, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_SetRight (API 21) exception: $e") }
        logLine("OH_ArkUI_PositionEdges_SetRight(null,0f) done")
    }

    @Test
    fun testOH_ArkUI_PositionEdges_GetRight() {
        memScoped {
            val v = alloc<FloatVar>()
            val ret = try { OH_ArkUI_PositionEdges_GetRight(null, v.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_PositionEdges_GetRight (API 21) exception: $e"); -1 }
            logLine("OH_ArkUI_PositionEdges_GetRight(null,&v)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_Create() {
        try {
            val ret = OH_ArkUI_PixelRoundPolicy_Create()
            logLine("OH_ArkUI_PixelRoundPolicy_Create()=$ret")
            OH_ArkUI_PixelRoundPolicy_Dispose(ret)
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_Create (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_Dispose() {
        try {
            OH_ArkUI_PixelRoundPolicy_Dispose(null)
            logLine("OH_ArkUI_PixelRoundPolicy_Dispose(null) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_Dispose (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_SetTop() {
        try {
            OH_ArkUI_PixelRoundPolicy_SetTop(null, ARKUI_PIXELROUNDCALCPOLICY_NOFORCEROUND)
            logLine("OH_ArkUI_PixelRoundPolicy_SetTop(null,NOFORCEROUND) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_SetTop (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_GetTop() {
        try {
            memScoped {
                val v = alloc<IntVar>()
                val ret = OH_ArkUI_PixelRoundPolicy_GetTop(null, v.ptr.reinterpret())
                logLine("OH_ArkUI_PixelRoundPolicy_GetTop(null,&v)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_GetTop (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_SetStart() {
        try {
            OH_ArkUI_PixelRoundPolicy_SetStart(null, ARKUI_PIXELROUNDCALCPOLICY_NOFORCEROUND)
            logLine("OH_ArkUI_PixelRoundPolicy_SetStart(null,NOFORCEROUND) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_SetStart (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_GetStart() {
        try {
            memScoped {
                val v = alloc<IntVar>()
                val ret = OH_ArkUI_PixelRoundPolicy_GetStart(null, v.ptr.reinterpret())
                logLine("OH_ArkUI_PixelRoundPolicy_GetStart(null,&v)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_GetStart (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_SetBottom() {
        try {
            OH_ArkUI_PixelRoundPolicy_SetBottom(null, ARKUI_PIXELROUNDCALCPOLICY_NOFORCEROUND)
            logLine("OH_ArkUI_PixelRoundPolicy_SetBottom(null,NOFORCEROUND) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_SetBottom (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_GetBottom() {
        try {
            memScoped {
                val v = alloc<IntVar>()
                val ret = OH_ArkUI_PixelRoundPolicy_GetBottom(null, v.ptr.reinterpret())
                logLine("OH_ArkUI_PixelRoundPolicy_GetBottom(null,&v)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_GetBottom (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_SetEnd() {
        try {
            OH_ArkUI_PixelRoundPolicy_SetEnd(null, ARKUI_PIXELROUNDCALCPOLICY_NOFORCEROUND)
            logLine("OH_ArkUI_PixelRoundPolicy_SetEnd(null,NOFORCEROUND) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_SetEnd (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_PixelRoundPolicy_GetEnd() {
        try {
            memScoped {
                val v = alloc<IntVar>()
                val ret = OH_ArkUI_PixelRoundPolicy_GetEnd(null, v.ptr.reinterpret())
                logLine("OH_ArkUI_PixelRoundPolicy_GetEnd(null,&v)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_PixelRoundPolicy_GetEnd (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_Create() {
        try {
            val ret = OH_ArkUI_TextMenuItem_Create()
            logLine("OH_ArkUI_TextMenuItem_Create()=$ret")
            OH_ArkUI_TextMenuItem_Dispose(ret)
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_Create (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_Dispose() {
        try {
            OH_ArkUI_TextMenuItem_Dispose(null)
            logLine("OH_ArkUI_TextMenuItem_Dispose(null) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_Dispose (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_SetContent() {
        try {
            val ret = OH_ArkUI_TextMenuItem_SetContent(null, null)
            logLine("OH_ArkUI_TextMenuItem_SetContent(null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_SetContent (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_SetIcon() {
        try {
            val ret = OH_ArkUI_TextMenuItem_SetIcon(null, null)
            logLine("OH_ArkUI_TextMenuItem_SetIcon(null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_SetIcon (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_SetLabelInfo() {
        try {
            val ret = OH_ArkUI_TextMenuItem_SetLabelInfo(null, null)
            logLine("OH_ArkUI_TextMenuItem_SetLabelInfo(null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_SetLabelInfo (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_SetId() {
        try {
            val ret = OH_ArkUI_TextMenuItem_SetId(null, 0)
            logLine("OH_ArkUI_TextMenuItem_SetId(null,0)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_SetId (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_GetContent() {
        try {
            memScoped {
                val buf = allocArray<ByteVar>(1)
                val writeLen = alloc<IntVar>()
                val ret = OH_ArkUI_TextMenuItem_GetContent(null, buf, 1, writeLen.ptr)
                logLine("OH_ArkUI_TextMenuItem_GetContent(null,buf,1,&writeLen)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_GetContent (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_GetIcon() {
        try {
            memScoped {
                val buf = allocArray<ByteVar>(1)
                val writeLen = alloc<IntVar>()
                val ret = OH_ArkUI_TextMenuItem_GetIcon(null, buf, 1, writeLen.ptr)
                logLine("OH_ArkUI_TextMenuItem_GetIcon(null,buf,1,&writeLen)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_GetIcon (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_GetLabelInfo() {
        try {
            memScoped {
                val buf = allocArray<ByteVar>(1)
                val writeLen = alloc<IntVar>()
                val ret = OH_ArkUI_TextMenuItem_GetLabelInfo(null, buf, 1, writeLen.ptr)
                logLine("OH_ArkUI_TextMenuItem_GetLabelInfo(null,buf,1,&writeLen)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_GetLabelInfo (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItem_GetId() {
        try {
            memScoped {
                val id = alloc<IntVar>()
                val ret = OH_ArkUI_TextMenuItem_GetId(null, id.ptr)
                logLine("OH_ArkUI_TextMenuItem_GetId(null,&id)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItem_GetId (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItemArray_GetSize() {
        try {
            memScoped {
                val size = alloc<IntVar>()
                val ret = OH_ArkUI_TextMenuItemArray_GetSize(null, size.ptr)
                logLine("OH_ArkUI_TextMenuItemArray_GetSize(null,&size)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItemArray_GetSize (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItemArray_GetItem() {
        try {
            memScoped {
                val item = alloc<CPointerVar<ByteVar>>()
                val ret = OH_ArkUI_TextMenuItemArray_GetItem(null, 0, item.ptr.reinterpret())
                logLine("OH_ArkUI_TextMenuItemArray_GetItem(null,0,&item)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItemArray_GetItem (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItemArray_Insert() {
        try {
            val ret = OH_ArkUI_TextMenuItemArray_Insert(null, null, 0)
            logLine("OH_ArkUI_TextMenuItemArray_Insert(null,null,0)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItemArray_Insert (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItemArray_Erase() {
        try {
            val ret = OH_ArkUI_TextMenuItemArray_Erase(null, 0)
            logLine("OH_ArkUI_TextMenuItemArray_Erase(null,0)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItemArray_Erase (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextMenuItemArray_Clear() {
        try {
            val ret = OH_ArkUI_TextMenuItemArray_Clear(null)
            logLine("OH_ArkUI_TextMenuItemArray_Clear(null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextMenuItemArray_Clear (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextEditMenuOptions_Create() {
        try {
            val ret = OH_ArkUI_TextEditMenuOptions_Create()
            logLine("OH_ArkUI_TextEditMenuOptions_Create()=$ret")
            OH_ArkUI_TextEditMenuOptions_Dispose(ret)
        } catch (e: Throwable) { logLine("OH_ArkUI_TextEditMenuOptions_Create (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextEditMenuOptions_Dispose() {
        try {
            OH_ArkUI_TextEditMenuOptions_Dispose(null)
            logLine("OH_ArkUI_TextEditMenuOptions_Dispose(null) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextEditMenuOptions_Dispose (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextEditMenuOptions_RegisterOnCreateMenuCallback() {
        try {
            val ret = OH_ArkUI_TextEditMenuOptions_RegisterOnCreateMenuCallback(null, null, null)
            logLine("OH_ArkUI_TextEditMenuOptions_RegisterOnCreateMenuCallback(null,null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextEditMenuOptions_RegisterOnCreateMenuCallback (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextEditMenuOptions_RegisterOnPrepareMenuCallback() {
        try {
            val ret = OH_ArkUI_TextEditMenuOptions_RegisterOnPrepareMenuCallback(null, null, null)
            logLine("OH_ArkUI_TextEditMenuOptions_RegisterOnPrepareMenuCallback(null,null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextEditMenuOptions_RegisterOnPrepareMenuCallback (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextEditMenuOptions_RegisterOnMenuItemClickCallback() {
        try {
            val ret = OH_ArkUI_TextEditMenuOptions_RegisterOnMenuItemClickCallback(null, null, null)
            logLine("OH_ArkUI_TextEditMenuOptions_RegisterOnMenuItemClickCallback(null,null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextEditMenuOptions_RegisterOnMenuItemClickCallback (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_Create() {
        try {
            val ret = OH_ArkUI_TextSelectionMenuOptions_Create()
            logLine("OH_ArkUI_TextSelectionMenuOptions_Create()=$ret")
            OH_ArkUI_TextSelectionMenuOptions_Dispose(ret)
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_Create (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_Dispose() {
        try {
            OH_ArkUI_TextSelectionMenuOptions_Dispose(null)
            logLine("OH_ArkUI_TextSelectionMenuOptions_Dispose(null) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_Dispose (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_SetSpanType() {
        try {
            val ret = OH_ArkUI_TextSelectionMenuOptions_SetSpanType(null, 0u)
            logLine("OH_ArkUI_TextSelectionMenuOptions_SetSpanType(null,0)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_SetSpanType (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_GetSpanType() {
        try {
            memScoped {
                val spanType = alloc<IntVar>()
                val ret = OH_ArkUI_TextSelectionMenuOptions_GetSpanType(null, spanType.ptr.reinterpret())
                logLine("OH_ArkUI_TextSelectionMenuOptions_GetSpanType(null,&spanType)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_GetSpanType (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_SetContentNode() {
        try {
            val ret = OH_ArkUI_TextSelectionMenuOptions_SetContentNode(null, null)
            logLine("OH_ArkUI_TextSelectionMenuOptions_SetContentNode(null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_SetContentNode (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_GetContentNode() {
        try {
            memScoped {
                val node = alloc<CPointerVar<ByteVar>>()
                val ret = OH_ArkUI_TextSelectionMenuOptions_GetContentNode(null, node.ptr.reinterpret())
                logLine("OH_ArkUI_TextSelectionMenuOptions_GetContentNode(null,&node)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_GetContentNode (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_SetResponseType() {
        try {
            val ret = OH_ArkUI_TextSelectionMenuOptions_SetResponseType(null, 0u)
            logLine("OH_ArkUI_TextSelectionMenuOptions_SetResponseType(null,0u)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_SetResponseType (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_GetResponseType() {
        try {
            memScoped {
                val responseType = alloc<UIntVar>()
                val ret = OH_ArkUI_TextSelectionMenuOptions_GetResponseType(null, responseType.ptr)
                logLine("OH_ArkUI_TextSelectionMenuOptions_GetResponseType(null,&responseType)=$ret")
            }
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_GetResponseType (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuShowCallback() {
        try {
            val ret = OH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuShowCallback(null, null, null)
            logLine("OH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuShowCallback(null,null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuShowCallback (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuHideCallback() {
        try {
            val ret = OH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuHideCallback(null, null, null)
            logLine("OH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuHideCallback(null,null,null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_TextSelectionMenuOptions_RegisterOnMenuHideCallback (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_ShowCounterConfig_Create() {
        try {
            val ret = OH_ArkUI_ShowCounterConfig_Create()
            logLine("OH_ArkUI_ShowCounterConfig_Create()=$ret")
            OH_ArkUI_ShowCounterConfig_Dispose(ret)
        } catch (e: Throwable) { logLine("OH_ArkUI_ShowCounterConfig_Create (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_ShowCounterConfig_Dispose() {
        try {
            OH_ArkUI_ShowCounterConfig_Dispose(null)
            logLine("OH_ArkUI_ShowCounterConfig_Dispose(null) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_ShowCounterConfig_Dispose (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_ShowCounterConfig_SetCounterTextColor() {
        try {
            OH_ArkUI_ShowCounterConfig_SetCounterTextColor(null, 0u)
            logLine("OH_ArkUI_ShowCounterConfig_SetCounterTextColor(null,0u) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_ShowCounterConfig_SetCounterTextColor (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_ShowCounterConfig_SetCounterTextOverflowColor() {
        try {
            OH_ArkUI_ShowCounterConfig_SetCounterTextOverflowColor(null, 0u)
            logLine("OH_ArkUI_ShowCounterConfig_SetCounterTextOverflowColor(null,0u) done")
        } catch (e: Throwable) { logLine("OH_ArkUI_ShowCounterConfig_SetCounterTextOverflowColor (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_ShowCounterConfig_GetCounterTextColor() {
        try {
            val ret = OH_ArkUI_ShowCounterConfig_GetCounterTextColor(null)
            logLine("OH_ArkUI_ShowCounterConfig_GetCounterTextColor(null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_ShowCounterConfig_GetCounterTextColor (optional API) exception: $e") }
    }

    @Test
    fun testOH_ArkUI_ShowCounterConfig_GetCounterTextOverflowColor() {
        try {
            val ret = OH_ArkUI_ShowCounterConfig_GetCounterTextOverflowColor(null)
            logLine("OH_ArkUI_ShowCounterConfig_GetCounterTextOverflowColor(null)=$ret")
        } catch (e: Throwable) { logLine("OH_ArkUI_ShowCounterConfig_GetCounterTextOverflowColor (optional API) exception: $e") }
    }

    // ==================== drag_and_drop.h – all functions ====================

    @Test
    fun testOH_ArkUI_NodeEvent_GetDragEvent() {
        val ret = OH_ArkUI_NodeEvent_GetDragEvent(null)
        logLine("OH_ArkUI_NodeEvent_GetDragEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetPreDragStatus() {
        val ret = OH_ArkUI_NodeEvent_GetPreDragStatus(null)
        logLine("OH_ArkUI_NodeEvent_GetPreDragStatus(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_DisableDefaultDropAnimation() {
        val ret = OH_ArkUI_DragEvent_DisableDefaultDropAnimation(null, false)
        logLine("OH_ArkUI_DragEvent_DisableDefaultDropAnimation(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_SetSuggestedDropOperation() {
        val ret = OH_ArkUI_DragEvent_SetSuggestedDropOperation(null, ARKUI_DROP_OPERATION_COPY)
        logLine("OH_ArkUI_DragEvent_SetSuggestedDropOperation(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_SetDragResult() {
        val ret = OH_ArkUI_DragEvent_SetDragResult(null, ARKUI_DRAG_RESULT_CANCELED)
        logLine("OH_ArkUI_DragEvent_SetDragResult(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_SetData() {
        val ret = OH_ArkUI_DragEvent_SetData(null, null)
        logLine("OH_ArkUI_DragEvent_SetData(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_SetDataLoadParams() {
        val ret = try { OH_ArkUI_DragEvent_SetDataLoadParams(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_SetDataLoadParams (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_DragEvent_SetDataLoadParams(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetUdmfData() {
        memScoped {
            val data = alloc<COpaquePointerVar>()
            val ret = OH_ArkUI_DragEvent_GetUdmfData(null, data.ptr.reinterpret())
            logLine("OH_ArkUI_DragEvent_GetUdmfData(null,data)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetDataTypeCount() {
        memScoped {
            val count = alloc<IntVar>()
            val ret = OH_ArkUI_DragEvent_GetDataTypeCount(null, count.ptr)
            logLine("OH_ArkUI_DragEvent_GetDataTypeCount(null,count)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetDataTypes() {
        val ret = OH_ArkUI_DragEvent_GetDataTypes(null, null, 0, 0)
        logLine("OH_ArkUI_DragEvent_GetDataTypes(null,null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetDragResult() {
        memScoped {
            val result = alloc<IntVar>()
            val ret = OH_ArkUI_DragEvent_GetDragResult(null, result.ptr.reinterpret())
            logLine("OH_ArkUI_DragEvent_GetDragResult(null,result)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetDropOperation() {
        memScoped {
            val operation = alloc<IntVar>()
            val ret = OH_ArkUI_DragEvent_GetDropOperation(null, operation.ptr.reinterpret())
            logLine("OH_ArkUI_DragEvent_GetDropOperation(null,operation)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetPreviewTouchPointX() {
        val ret = OH_ArkUI_DragEvent_GetPreviewTouchPointX(null)
        logLine("OH_ArkUI_DragEvent_GetPreviewTouchPointX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetPreviewTouchPointY() {
        val ret = OH_ArkUI_DragEvent_GetPreviewTouchPointY(null)
        logLine("OH_ArkUI_DragEvent_GetPreviewTouchPointY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetPreviewRectWidth() {
        val ret = OH_ArkUI_DragEvent_GetPreviewRectWidth(null)
        logLine("OH_ArkUI_DragEvent_GetPreviewRectWidth(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetPreviewRectHeight() {
        val ret = OH_ArkUI_DragEvent_GetPreviewRectHeight(null)
        logLine("OH_ArkUI_DragEvent_GetPreviewRectHeight(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetTouchPointXToWindow() {
        val ret = OH_ArkUI_DragEvent_GetTouchPointXToWindow(null)
        logLine("OH_ArkUI_DragEvent_GetTouchPointXToWindow(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetTouchPointYToWindow() {
        val ret = OH_ArkUI_DragEvent_GetTouchPointYToWindow(null)
        logLine("OH_ArkUI_DragEvent_GetTouchPointYToWindow(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetTouchPointXToDisplay() {
        val ret = OH_ArkUI_DragEvent_GetTouchPointXToDisplay(null)
        logLine("OH_ArkUI_DragEvent_GetTouchPointXToDisplay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetTouchPointYToDisplay() {
        val ret = OH_ArkUI_DragEvent_GetTouchPointYToDisplay(null)
        logLine("OH_ArkUI_DragEvent_GetTouchPointYToDisplay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetTouchPointXToGlobalDisplay() {
        val ret = try { OH_ArkUI_DragEvent_GetTouchPointXToGlobalDisplay(null) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_GetTouchPointXToGlobalDisplay (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_DragEvent_GetTouchPointXToGlobalDisplay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetTouchPointYToGlobalDisplay() {
        val ret = try { OH_ArkUI_DragEvent_GetTouchPointYToGlobalDisplay(null) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_GetTouchPointYToGlobalDisplay (API 20) exception: $e"); 0f }
        logLine("OH_ArkUI_DragEvent_GetTouchPointYToGlobalDisplay(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetVelocityX() {
        val ret = OH_ArkUI_DragEvent_GetVelocityX(null)
        logLine("OH_ArkUI_DragEvent_GetVelocityX(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetVelocityY() {
        val ret = OH_ArkUI_DragEvent_GetVelocityY(null)
        logLine("OH_ArkUI_DragEvent_GetVelocityY(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetVelocity() {
        val ret = OH_ArkUI_DragEvent_GetVelocity(null)
        logLine("OH_ArkUI_DragEvent_GetVelocity(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetModifierKeyStates() {
        memScoped {
            val keys = alloc<ULongVar>()
            val ret = OH_ArkUI_DragEvent_GetModifierKeyStates(null, keys.ptr)
            logLine("OH_ArkUI_DragEvent_GetModifierKeyStates(null,keys)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetDisplayId() {
        memScoped {
            val displayId = alloc<IntVar>()
            val ret = try { OH_ArkUI_DragEvent_GetDisplayId(null, displayId.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_GetDisplayId (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_DragEvent_GetDisplayId(null,displayId)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_StartDataLoading() {
        memScoped {
            val key = allocArray<ByteVar>(128)
            val ret = try { OH_ArkUI_DragEvent_StartDataLoading(null, null, key, 128u) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_StartDataLoading (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_DragEvent_StartDataLoading(null,null,key,128u)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_CancelDataLoading() {
        val ret = OH_ArkUI_CancelDataLoading(null, null)
        logLine("OH_ArkUI_CancelDataLoading(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DisableDropDataPrefetchOnNode() {
        val ret = OH_ArkUI_DisableDropDataPrefetchOnNode(null, true)
        logLine("OH_ArkUI_DisableDropDataPrefetchOnNode(null,true)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetDragEventStrictReportWithNode() {
        val ret = OH_ArkUI_SetDragEventStrictReportWithNode(null, false)
        logLine("OH_ArkUI_SetDragEventStrictReportWithNode(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetDragEventStrictReportWithContext() {
        val ret = OH_ArkUI_SetDragEventStrictReportWithContext(null, false)
        logLine("OH_ArkUI_SetDragEventStrictReportWithContext(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetNodeAllowedDropDataTypes() {
        val ret = OH_ArkUI_SetNodeAllowedDropDataTypes(null, null, 0)
        logLine("OH_ArkUI_SetNodeAllowedDropDataTypes(null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DisallowNodeAnyDropDataTypes() {
        val ret = OH_ArkUI_DisallowNodeAnyDropDataTypes(null)
        logLine("OH_ArkUI_DisallowNodeAnyDropDataTypes(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_AllowNodeAllDropDataTypes() {
        val ret = OH_ArkUI_AllowNodeAllDropDataTypes(null)
        logLine("OH_ArkUI_AllowNodeAllDropDataTypes(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetNodeDraggable() {
        val ret = OH_ArkUI_SetNodeDraggable(null, false)
        logLine("OH_ArkUI_SetNodeDraggable(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetNodeDragPreview() {
        val ret = OH_ArkUI_SetNodeDragPreview(null, null)
        logLine("OH_ArkUI_SetNodeDragPreview(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption_SetScaleMode() {
        val opt = OH_ArkUI_CreateDragPreviewOption() ?: return
        val ret = OH_ArkUI_DragPreviewOption_SetScaleMode(opt, ARKUI_DRAG_PREVIEW_SCALE_AUTO)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption_SetScaleMode(opt,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption_SetDefaultShadowEnabled() {
        val opt = OH_ArkUI_CreateDragPreviewOption() ?: return
        val ret = OH_ArkUI_DragPreviewOption_SetDefaultShadowEnabled(opt, true)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption_SetDefaultShadowEnabled(opt,true)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption_SetDefaultRadiusEnabled() {
        val opt = OH_ArkUI_CreateDragPreviewOption() ?: return
        val ret = OH_ArkUI_DragPreviewOption_SetDefaultRadiusEnabled(opt, true)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption_SetDefaultRadiusEnabled(opt,true)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption_SetNumberBadgeEnabled() {
        val opt = OH_ArkUI_CreateDragPreviewOption() ?: return
        val ret = OH_ArkUI_DragPreviewOption_SetNumberBadgeEnabled(opt, false)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption_SetNumberBadgeEnabled(opt,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption_SetBadgeNumber() {
        val opt = OH_ArkUI_CreateDragPreviewOption() ?: return
        val ret = OH_ArkUI_DragPreviewOption_SetBadgeNumber(opt, 0u)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption_SetBadgeNumber(opt,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragPreviewOption_SetDefaultAnimationBeforeLiftingEnabled() {
        val opt = OH_ArkUI_CreateDragPreviewOption() ?: return
        val ret = OH_ArkUI_DragPreviewOption_SetDefaultAnimationBeforeLiftingEnabled(opt, true)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_DragPreviewOption_SetDefaultAnimationBeforeLiftingEnabled(opt,true)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetNodeDragPreviewOption() {
        val opt = OH_ArkUI_CreateDragPreviewOption()
        val ret = OH_ArkUI_SetNodeDragPreviewOption(null, opt)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        logLine("OH_ArkUI_SetNodeDragPreviewOption(null,opt)=$ret")
    }

    @Test
    fun testOH_ArkUI_CreateDragActionWithNode() {
        val ret = OH_ArkUI_CreateDragActionWithNode(null)
        logLine("OH_ArkUI_CreateDragActionWithNode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CreateDragActionWithContext() {
        val ret = OH_ArkUI_CreateDragActionWithContext(null)
        logLine("OH_ArkUI_CreateDragActionWithContext(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_Dispose() {
        OH_ArkUI_DragAction_Dispose(null)
        logLine("OH_ArkUI_DragAction_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetPointerId() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_DragAction_SetPointerId(action, 0)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetPointerId(action,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetPixelMaps() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_DragAction_SetPixelMaps(action, null, 0)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetPixelMaps(action,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetTouchPointX() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_DragAction_SetTouchPointX(action, 0f)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetTouchPointX(action,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetTouchPointY() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_DragAction_SetTouchPointY(action, 0f)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetTouchPointY(action,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetData() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_DragAction_SetData(action, null)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetData(action,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetDataLoadParams() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = try { OH_ArkUI_DragAction_SetDataLoadParams(action, null) } catch (e: Throwable) { logLine("OH_ArkUI_DragAction_SetDataLoadParams (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetDataLoadParams(action,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_SetDragPreviewOption() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val opt = OH_ArkUI_CreateDragPreviewOption()
        val ret = OH_ArkUI_DragAction_SetDragPreviewOption(action, opt)
        OH_ArkUI_DragPreviewOption_Dispose(opt)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_SetDragPreviewOption(action,opt)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_RegisterStatusListener() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_DragAction_RegisterStatusListener(action, null, null)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_RegisterStatusListener(action,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAction_UnregisterStatusListener() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        OH_ArkUI_DragAction_UnregisterStatusListener(action)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_DragAction_UnregisterStatusListener(action) done")
    }

    @Test
    fun testOH_ArkUI_DragAndDropInfo_GetDragStatus() {
        val ret = OH_ArkUI_DragAndDropInfo_GetDragStatus(null)
        logLine("OH_ArkUI_DragAndDropInfo_GetDragStatus(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragAndDropInfo_GetDragEvent() {
        val ret = OH_ArkUI_DragAndDropInfo_GetDragEvent(null)
        logLine("OH_ArkUI_DragAndDropInfo_GetDragEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_StartDrag() {
        val action = OH_ArkUI_CreateDragActionWithContext(null) ?: return
        val ret = OH_ArkUI_StartDrag(action)
        OH_ArkUI_DragAction_Dispose(action)
        logLine("OH_ArkUI_StartDrag(action)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_RequestDragEndPending() {
        memScoped {
            val requestIdentify = alloc<IntVar>()
            val ret = try { OH_ArkUI_DragEvent_RequestDragEndPending(null, requestIdentify.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_RequestDragEndPending (API 19) exception: $e"); -1 }
            logLine("OH_ArkUI_DragEvent_RequestDragEndPending(null,requestIdentify)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NotifyDragResult() {
        val ret = try { OH_ArkUI_NotifyDragResult(-1, ARKUI_DRAG_RESULT_CANCELED) } catch (e: Throwable) { logLine("OH_ArkUI_NotifyDragResult (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_NotifyDragResult(-1,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_NotifyDragEndPendingDone() {
        val ret = try { OH_ArkUI_NotifyDragEndPendingDone(-1) } catch (e: Throwable) { logLine("OH_ArkUI_NotifyDragEndPendingDone (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_NotifyDragEndPendingDone(-1)=$ret")
    }

    @Test
    fun testOH_ArkUI_DragEvent_GetDragSource() {
        memScoped {
            val bundleName = allocArray<ByteVar>(128)
            val ret = try { OH_ArkUI_DragEvent_GetDragSource(null, bundleName, 128) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_GetDragSource (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_DragEvent_GetDragSource(null,bundleName,128u)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_DragEvent_IsRemote() {
        memScoped {
            val isRemote = alloc<BooleanVar>()
            val ret = try { OH_ArkUI_DragEvent_IsRemote(null, isRemote.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_DragEvent_IsRemote (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
            logLine("OH_ArkUI_DragEvent_IsRemote(null,isRemote)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_EnableDropDisallowedBadge() {
        val ret = try { OH_ArkUI_EnableDropDisallowedBadge(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_EnableDropDisallowedBadge (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_EnableDropDisallowedBadge(null,false)=$ret")
    }

    // ==================== native_dialog.h (743-1279) ====================

    @Test
    fun testOH_ArkUI_DialogDismissEvent_SetShouldBlockDismiss() {
        OH_ArkUI_DialogDismissEvent_SetShouldBlockDismiss(null, false)
        OH_ArkUI_DialogDismissEvent_SetShouldBlockDismiss(null, true)
        logLine("OH_ArkUI_DialogDismissEvent_SetShouldBlockDismiss(null,...) done")
    }

    @Test
    fun testOH_ArkUI_DialogDismissEvent_GetUserData() {
        val ret = OH_ArkUI_DialogDismissEvent_GetUserData(null)
        logLine("OH_ArkUI_DialogDismissEvent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_DialogDismissEvent_GetDismissReason() {
        val ret = OH_ArkUI_DialogDismissEvent_GetDismissReason(null)
        logLine("OH_ArkUI_DialogDismissEvent_GetDismissReason(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_OpenDialog() {
        val ret = try { OH_ArkUI_CustomDialog_OpenDialog(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_OpenDialog (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_OpenDialog(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_UpdateDialog() {
        val ret = try { OH_ArkUI_CustomDialog_UpdateDialog(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_UpdateDialog (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_UpdateDialog(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetLevelMode() {
        val ret = try { OH_ArkUI_CustomDialog_SetLevelMode(null, ARKUI_LEVEL_MODE_OVERLAY) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetLevelMode (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetLevelMode(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetLevelUniqueId() {
        val ret = try { OH_ArkUI_CustomDialog_SetLevelUniqueId(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetLevelUniqueId (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetLevelUniqueId(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetImmersiveMode() {
        val ret = try { OH_ArkUI_CustomDialog_SetImmersiveMode(null, ARKUI_IMMERSIVE_MODE_DEFAULT) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetImmersiveMode (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetImmersiveMode(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBackgroundColor() {
        val ret = try { OH_ArkUI_CustomDialog_SetBackgroundColor(null, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBackgroundColor (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetBackgroundColor(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetCornerRadius() {
        val ret = try { OH_ArkUI_CustomDialog_SetCornerRadius(null, 0f, 0f, 0f, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetCornerRadius (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetCornerRadius(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBorderWidth() {
        memScoped {
            val ret = try { OH_ArkUI_CustomDialog_SetBorderWidth(null, 0f, 0f, 0f, 0f, ARKUI_LENGTH_METRIC_UNIT_VP) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBorderWidth (API 19) exception: $e"); -1 }
            logLine("OH_ArkUI_CustomDialog_SetBorderWidth(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBorderColor() {
        val ret = try { OH_ArkUI_CustomDialog_SetBorderColor(null, 0u, 0u, 0u, 0u) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBorderColor (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetBorderColor(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBorderStyle() {
        val ret = try { OH_ArkUI_CustomDialog_SetBorderStyle(null, 0, 0, 0, 0) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBorderStyle (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetBorderStyle(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetWidth() {
        val ret = try { OH_ArkUI_CustomDialog_SetWidth(null, 0f, ARKUI_LENGTH_METRIC_UNIT_VP) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetWidth (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetWidth(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetHeight() {
        val ret = try { OH_ArkUI_CustomDialog_SetHeight(null, 0f, ARKUI_LENGTH_METRIC_UNIT_VP) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetHeight (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetHeight(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetShadow() {
        val ret = try { OH_ArkUI_CustomDialog_SetShadow(null, ARKUI_SHADOW_STYLE_OUTER_DEFAULT_XS) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetShadow (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetShadow(null,...)=$ret")
    }

    // @Test
    // fun testOH_ArkUI_CustomDialog_SetCustomShadow() {
    //     val ret = try { OH_ArkUI_CustomDialog_SetCustomShadow(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetCustomShadow (API 19) exception: $e"); -1 }
    //     logLine("OH_ArkUI_CustomDialog_SetCustomShadow(null,null)=$ret")
    // }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBackgroundBlurStyle() {
        val ret = try { OH_ArkUI_CustomDialog_SetBackgroundBlurStyle(null, ARKUI_BLUR_STYLE_THIN) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBackgroundBlurStyle (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetBackgroundBlurStyle(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetAlignment() {
        val ret = try { OH_ArkUI_CustomDialog_SetAlignment(null, ARKUI_ALIGNMENT_CENTER.toInt(), 0f, 0f) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetAlignment (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetAlignment(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetModalMode() {
        val ret = try { OH_ArkUI_CustomDialog_SetModalMode(null, true) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetModalMode (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetModalMode(null,true)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetAutoCancel() {
        val ret = try { OH_ArkUI_CustomDialog_SetAutoCancel(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetAutoCancel (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetAutoCancel(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetSubwindowMode() {
        val ret = try { OH_ArkUI_CustomDialog_SetSubwindowMode(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetSubwindowMode (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetSubwindowMode(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetMask() {
        memScoped {
            val ret = try { OH_ArkUI_CustomDialog_SetMask(null, 0u, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetMask (API 19) exception: $e"); -1 }
            logLine("OH_ArkUI_CustomDialog_SetMask(null,0,null)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetKeyboardAvoidMode() {
        val ret = try { OH_ArkUI_CustomDialog_SetKeyboardAvoidMode(null, ARKUI_KEYBOARD_AVOID_MODE_NONE) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetKeyboardAvoidMode (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetKeyboardAvoidMode(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetHoverModeEnabled() {
        val ret = try { OH_ArkUI_CustomDialog_SetHoverModeEnabled(null, false) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetHoverModeEnabled (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetHoverModeEnabled(null,false)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetHoverModeArea() {
        val ret = try { OH_ArkUI_CustomDialog_SetHoverModeArea(null, ARKUI_HOVER_MODE_AREA_TYPE_TOP) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetHoverModeArea (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetHoverModeArea(null,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_RegisterOnWillDismissCallback() {
        val ret = try { OH_ArkUI_CustomDialog_RegisterOnWillDismissCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_RegisterOnWillDismissCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_RegisterOnWillDismissCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_RegisterOnWillAppearCallback() {
        val ret = try { OH_ArkUI_CustomDialog_RegisterOnWillAppearCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_RegisterOnWillAppearCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_RegisterOnWillAppearCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_RegisterOnDidAppearCallback() {
        val ret = try { OH_ArkUI_CustomDialog_RegisterOnDidAppearCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_RegisterOnDidAppearCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_RegisterOnDidAppearCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_RegisterOnWillDisappearCallback() {
        val ret = try { OH_ArkUI_CustomDialog_RegisterOnWillDisappearCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_RegisterOnWillDisappearCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_RegisterOnWillDisappearCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_RegisterOnDidDisappearCallback() {
        val ret = try { OH_ArkUI_CustomDialog_RegisterOnDidDisappearCallback(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_RegisterOnDidDisappearCallback (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_RegisterOnDidDisappearCallback(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_GetState() {
        memScoped {
            val state = alloc<IntVar>()
            val ret = try { OH_ArkUI_CustomDialog_GetState(null, state.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_GetState (API 20) exception: $e"); -1 }
            logLine("OH_ArkUI_CustomDialog_GetState(null,state)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBackgroundBlurStyleOptions() {
        val ret = try { OH_ArkUI_CustomDialog_SetBackgroundBlurStyleOptions(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBackgroundBlurStyleOptions (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetBackgroundBlurStyleOptions(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_CustomDialog_SetBackgroundEffect() {
        val ret = try { OH_ArkUI_CustomDialog_SetBackgroundEffect(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_CustomDialog_SetBackgroundEffect (API 19) exception: $e"); -1 }
        logLine("OH_ArkUI_CustomDialog_SetBackgroundEffect(null,null)=$ret")
    }

    // ==================== native_node.h – enums ====================

    @Test
    fun testEnum_ArkUI_NodeType() {
        assertEquals(ARKUI_NODE_CUSTOM.toInt(), 0)
        assertEquals(ARKUI_NODE_TEXT.toInt(), 1)
        assertEquals(ARKUI_NODE_SPAN.toInt(), 2)
        assertEquals(ARKUI_NODE_IMAGE_SPAN.toInt(), 3)
        assertEquals(ARKUI_NODE_IMAGE.toInt(), 4)
        assertEquals(ARKUI_NODE_TOGGLE.toInt(), 5)
        assertEquals(ARKUI_NODE_LOADING_PROGRESS.toInt(), 6)
        assertEquals(ARKUI_NODE_TEXT_INPUT.toInt(), 7)
        assertEquals(ARKUI_NODE_TEXT_AREA.toInt(), 8)
        assertEquals(ARKUI_NODE_BUTTON.toInt(), 9)
        assertEquals(ARKUI_NODE_PROGRESS.toInt(), 10)
        assertEquals(ARKUI_NODE_CHECKBOX.toInt(), 11)
        assertEquals(ARKUI_NODE_XCOMPONENT.toInt(), 12)
        assertEquals(ARKUI_NODE_DATE_PICKER.toInt(), 13)
        assertEquals(ARKUI_NODE_TIME_PICKER.toInt(), 14)
        assertEquals(ARKUI_NODE_TEXT_PICKER.toInt(), 15)
        assertEquals(ARKUI_NODE_CALENDAR_PICKER.toInt(), 16)
        assertEquals(ARKUI_NODE_SLIDER.toInt(), 17)
        assertEquals(ARKUI_NODE_RADIO.toInt(), 18)
        assertEquals(ARKUI_NODE_IMAGE_ANIMATOR.toInt(), 19)
        assertEquals(ARKUI_NODE_XCOMPONENT_TEXTURE.toInt(), 20)
        assertEquals(ARKUI_NODE_CHECKBOX_GROUP.toInt(), 21)
        assertEquals(ARKUI_NODE_STACK.toInt(), 1000)
        assertEquals(ARKUI_NODE_SWIPER.toInt(), 1001)
        assertEquals(ARKUI_NODE_SCROLL.toInt(), 1002)
        assertEquals(ARKUI_NODE_LIST.toInt(), 1003)
        assertEquals(ARKUI_NODE_LIST_ITEM.toInt(), 1004)
        assertEquals(ARKUI_NODE_COLUMN.toInt(), 1006)
        assertEquals(ARKUI_NODE_ROW.toInt(), 1010)
        logLine("ArkUI_NodeType passed")
    }

    @Test
    fun testEnum_ArkUI_NodeEventType() {
        assertEquals(NODE_WIDTH.toInt(), 0)
        assertEquals(NODE_HEIGHT.toInt(), 1)
        logLine("ArkUI_NodeEventType sample passed")
    }

    @Test
    fun testEnum_ArkUI_NodeDirtyFlag() {
        assertEquals(NODE_NEED_MEASURE.toInt(), 1)
        assertEquals(NODE_NEED_LAYOUT.toInt(), 2)
        assertEquals(NODE_NEED_RENDER.toInt(), 3)
        logLine("ArkUI_NodeDirtyFlag passed")
    }

    @Test
    fun testEnum_ArkUI_NodeCustomEventType() {
        assertEquals(ARKUI_NODE_CUSTOM_EVENT_ON_MEASURE.toInt(), 1 shl 0)
        assertEquals(ARKUI_NODE_CUSTOM_EVENT_ON_LAYOUT.toInt(), 1 shl 1)
        assertEquals(ARKUI_NODE_CUSTOM_EVENT_ON_DRAW.toInt(), 1 shl 2)
        assertEquals(ARKUI_NODE_CUSTOM_EVENT_ON_DRAW_BEHIND.toInt(), 1 shl 6)
        logLine("ArkUI_NodeCustomEventType passed")
    }

    @Test
    fun testEnum_ArkUI_NodeAdapterEventType() {
        assertEquals(NODE_ADAPTER_EVENT_WILL_ATTACH_TO_NODE.toInt(), 1)
        assertEquals(NODE_ADAPTER_EVENT_WILL_DETACH_FROM_NODE.toInt(), 2)
        assertEquals(NODE_ADAPTER_EVENT_ON_GET_NODE_ID.toInt(), 3)
        assertEquals(NODE_ADAPTER_EVENT_ON_ADD_NODE_TO_ADAPTER.toInt(), 4)
        assertEquals(NODE_ADAPTER_EVENT_ON_REMOVE_NODE_FROM_ADAPTER.toInt(), 5)
        logLine("ArkUI_NodeAdapterEventType passed")
    }

    @Test
    fun testEnum_ArkUI_NodeContentEventType() {
        assertEquals(NODE_CONTENT_EVENT_ON_ATTACH_TO_WINDOW.toInt(), 0)
        assertEquals(NODE_CONTENT_EVENT_ON_DETACH_FROM_WINDOW.toInt(), 1)
        logLine("ArkUI_NodeContentEventType passed")
    }

    // ==================== native_node.h – functions ====================

    @Test
    fun testOH_ArkUI_NodeEvent_GetEventType() {
        val ret = OH_ArkUI_NodeEvent_GetEventType(null)
        logLine("OH_ArkUI_NodeEvent_GetEventType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetTargetId() {
        val ret = OH_ArkUI_NodeEvent_GetTargetId(null)
        logLine("OH_ArkUI_NodeEvent_GetTargetId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetNodeHandle() {
        val ret = OH_ArkUI_NodeEvent_GetNodeHandle(null)
        logLine("OH_ArkUI_NodeEvent_GetNodeHandle(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetInputEvent() {
        val ret = OH_ArkUI_NodeEvent_GetInputEvent(null)
        logLine("OH_ArkUI_NodeEvent_GetInputEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetNodeComponentEvent() {
        val ret = OH_ArkUI_NodeEvent_GetNodeComponentEvent(null)
        logLine("OH_ArkUI_NodeEvent_GetNodeComponentEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetStringAsyncEvent() {
        val ret = OH_ArkUI_NodeEvent_GetStringAsyncEvent(null)
        logLine("OH_ArkUI_NodeEvent_GetStringAsyncEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetTextChangeEvent() {
        val ret = OH_ArkUI_NodeEvent_GetTextChangeEvent(null)
        logLine("OH_ArkUI_NodeEvent_GetTextChangeEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetUserData() {
        val ret = OH_ArkUI_NodeEvent_GetUserData(null)
        logLine("OH_ArkUI_NodeEvent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetNumberValue() {
        memScoped {
            val value = alloc<ArkUI_NumberValue>()
            val ret = OH_ArkUI_NodeEvent_GetNumberValue(null, 0, value.ptr)
            logLine("OH_ArkUI_NodeEvent_GetNumberValue(null,0,value)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetStringValue() {
        memScoped {
            val string = alloc<CPointerVar<ByteVar>>()
            val stringSize = alloc<IntVar>()
            val ret = OH_ArkUI_NodeEvent_GetStringValue(null, 0, string.ptr, stringSize.ptr)
            logLine("OH_ArkUI_NodeEvent_GetStringValue(null,0,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeEvent_SetReturnNumberValue() {
        memScoped {
            val value = alloc<ArkUI_NumberValue>()
            val ret = OH_ArkUI_NodeEvent_SetReturnNumberValue(null, value.ptr, 0)
            logLine("OH_ArkUI_NodeEvent_SetReturnNumberValue(null,value,0)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeEvent_GetTouchTestInfo() {
        val ret = try { OH_ArkUI_NodeEvent_GetTouchTestInfo(null) } catch (e: Throwable) { logLine("OH_ArkUI_NodeEvent_GetTouchTestInfo (API 22) exception: $e"); null }
        logLine("OH_ArkUI_NodeEvent_GetTouchTestInfo(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_Create() {
        val ret = OH_ArkUI_NodeAdapter_Create()
        logLine("OH_ArkUI_NodeAdapter_Create()=$ret")
        OH_ArkUI_NodeAdapter_Dispose(ret)
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_Dispose() {
        OH_ArkUI_NodeAdapter_Dispose(null)
        logLine("OH_ArkUI_NodeAdapter_Dispose(null) done")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_SetTotalNodeCount() {
        val ret = OH_ArkUI_NodeAdapter_SetTotalNodeCount(null, 0u)
        logLine("OH_ArkUI_NodeAdapter_SetTotalNodeCount(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_GetTotalNodeCount() {
        val ret = OH_ArkUI_NodeAdapter_GetTotalNodeCount(null)
        logLine("OH_ArkUI_NodeAdapter_GetTotalNodeCount(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_RegisterEventReceiver() {
        val ret = OH_ArkUI_NodeAdapter_RegisterEventReceiver(null, null, null)
        logLine("OH_ArkUI_NodeAdapter_RegisterEventReceiver(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_UnregisterEventReceiver() {
        OH_ArkUI_NodeAdapter_UnregisterEventReceiver(null)
        logLine("OH_ArkUI_NodeAdapter_UnregisterEventReceiver(null) done")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_ReloadAllItems() {
        val ret = OH_ArkUI_NodeAdapter_ReloadAllItems(null)
        logLine("OH_ArkUI_NodeAdapter_ReloadAllItems(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_ReloadItem() {
        val ret = OH_ArkUI_NodeAdapter_ReloadItem(null, 0u, 0u)
        logLine("OH_ArkUI_NodeAdapter_ReloadItem(null,0u,0u)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_RemoveItem() {
        val ret = OH_ArkUI_NodeAdapter_RemoveItem(null, 0u, 0u)
        logLine("OH_ArkUI_NodeAdapter_RemoveItem(null,0u,0u)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_InsertItem() {
        val ret = OH_ArkUI_NodeAdapter_InsertItem(null, 0u, 0u)
        logLine("OH_ArkUI_NodeAdapter_InsertItem(null,0u,0u)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_MoveItem() {
        val ret = OH_ArkUI_NodeAdapter_MoveItem(null, 0u, 0u)
        logLine("OH_ArkUI_NodeAdapter_MoveItem(null,0,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapter_GetAllItems() {
        memScoped {
            val size = alloc<UIntVar>()
            val ret = OH_ArkUI_NodeAdapter_GetAllItems(null, null, size.ptr)
            logLine("OH_ArkUI_NodeAdapter_GetAllItems(null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_GetType() {
        val ret = OH_ArkUI_NodeAdapterEvent_GetType(null)
        logLine("OH_ArkUI_NodeAdapterEvent_GetType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_GetRemovedNode() {
        val ret = OH_ArkUI_NodeAdapterEvent_GetRemovedNode(null)
        logLine("OH_ArkUI_NodeAdapterEvent_GetRemovedNode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_GetItemIndex() {
        val ret = OH_ArkUI_NodeAdapterEvent_GetItemIndex(null)
        logLine("OH_ArkUI_NodeAdapterEvent_GetItemIndex(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_GetHostNode() {
        val ret = OH_ArkUI_NodeAdapterEvent_GetHostNode(null)
        logLine("OH_ArkUI_NodeAdapterEvent_GetHostNode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_SetItem() {
        val ret = OH_ArkUI_NodeAdapterEvent_SetItem(null, null)
        logLine("OH_ArkUI_NodeAdapterEvent_SetItem(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_SetNodeId() {
        val ret = OH_ArkUI_NodeAdapterEvent_SetNodeId(null, 0)
        logLine("OH_ArkUI_NodeAdapterEvent_SetNodeId(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeAdapterEvent_GetUserData() {
        val ret = OH_ArkUI_NodeAdapterEvent_GetUserData(null)
        logLine("OH_ArkUI_NodeAdapterEvent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetLayoutConstraintInMeasure() {
        val ret = OH_ArkUI_NodeCustomEvent_GetLayoutConstraintInMeasure(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetLayoutConstraintInMeasure(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetPositionInLayout() {
        val ret = OH_ArkUI_NodeCustomEvent_GetPositionInLayout(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetPositionInLayout(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetDrawContextInDraw() {
        val ret = OH_ArkUI_NodeCustomEvent_GetDrawContextInDraw(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetDrawContextInDraw(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetEventTargetId() {
        val ret = OH_ArkUI_NodeCustomEvent_GetEventTargetId(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetEventTargetId(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetNodeHandle() {
        val ret = OH_ArkUI_NodeCustomEvent_GetNodeHandle(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetNodeHandle(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetEventType() {
        val ret = OH_ArkUI_NodeCustomEvent_GetEventType(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetEventType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetUserData() {
        val ret = OH_ArkUI_NodeCustomEvent_GetUserData(null)
        logLine("OH_ArkUI_NodeCustomEvent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetCustomSpanMeasureInfo() {
        val ret = OH_ArkUI_NodeCustomEvent_GetCustomSpanMeasureInfo(null, null)
        logLine("OH_ArkUI_NodeCustomEvent_GetCustomSpanMeasureInfo(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_SetCustomSpanMetrics() {
        val ret = OH_ArkUI_NodeCustomEvent_SetCustomSpanMetrics(null, null)
        logLine("OH_ArkUI_NodeCustomEvent_SetCustomSpanMetrics(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeCustomEvent_GetCustomSpanDrawInfo() {
        val ret = OH_ArkUI_NodeCustomEvent_GetCustomSpanDrawInfo(null, null)
        logLine("OH_ArkUI_NodeCustomEvent_GetCustomSpanDrawInfo(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContent_RegisterCallback() {
        val ret = OH_ArkUI_NodeContent_RegisterCallback(null, null)
        logLine("OH_ArkUI_NodeContent_RegisterCallback(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContentEvent_GetEventType() {
        val ret = OH_ArkUI_NodeContentEvent_GetEventType(null)
        logLine("OH_ArkUI_NodeContentEvent_GetEventType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContentEvent_GetNodeContentHandle() {
        val ret = OH_ArkUI_NodeContentEvent_GetNodeContentHandle(null)
        logLine("OH_ArkUI_NodeContentEvent_GetNodeContentHandle(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContent_GetUserData() {
        val ret = OH_ArkUI_NodeContent_GetUserData(null)
        logLine("OH_ArkUI_NodeContent_GetUserData(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContent_SetUserData() {
        val ret = OH_ArkUI_NodeContent_SetUserData(null, null)
        logLine("OH_ArkUI_NodeContent_SetUserData(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContent_AddNode() {
        val ret = OH_ArkUI_NodeContent_AddNode(null, null)
        logLine("OH_ArkUI_NodeContent_AddNode(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContent_RemoveNode() {
        val ret = OH_ArkUI_NodeContent_RemoveNode(null, null)
        logLine("OH_ArkUI_NodeContent_RemoveNode(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeContent_InsertNode() {
        val ret = OH_ArkUI_NodeContent_InsertNode(null, null, 0)
        logLine("OH_ArkUI_NodeContent_InsertNode(null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetLayoutSize() {
        memScoped {
            val size = alloc<ArkUI_IntSize>()
            val ret = OH_ArkUI_NodeUtils_GetLayoutSize(null, size.ptr)
            logLine("OH_ArkUI_NodeUtils_GetLayoutSize(null,size)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetLayoutPosition() {
        memScoped {
            val localOffset = alloc<ArkUI_IntOffset>()
            val ret = OH_ArkUI_NodeUtils_GetLayoutPosition(null, localOffset.ptr)
            logLine("OH_ArkUI_NodeUtils_GetLayoutPosition(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetLayoutPositionInWindow() {
        memScoped {
            val globalOffset = alloc<ArkUI_IntOffset>()
            val ret = OH_ArkUI_NodeUtils_GetLayoutPositionInWindow(null, globalOffset.ptr)
            logLine("OH_ArkUI_NodeUtils_GetLayoutPositionInWindow(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetLayoutPositionInScreen() {
        memScoped {
            val screenOffset = alloc<ArkUI_IntOffset>()
            val ret = OH_ArkUI_NodeUtils_GetLayoutPositionInScreen(null, screenOffset.ptr)
            logLine("OH_ArkUI_NodeUtils_GetLayoutPositionInScreen(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetLayoutPositionInGlobalDisplay() {
        memScoped {
            val offset = alloc<ArkUI_IntOffset>()
            val ret = try { OH_ArkUI_NodeUtils_GetLayoutPositionInGlobalDisplay(null, offset.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_NodeUtils_GetLayoutPositionInGlobalDisplay (API 20) exception: $e"); -1 }
            logLine("OH_ArkUI_NodeUtils_GetLayoutPositionInGlobalDisplay(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetPositionWithTranslateInWindow() {
        memScoped {
            val translateOffset = alloc<ArkUI_IntOffset>()
            val ret = OH_ArkUI_NodeUtils_GetPositionWithTranslateInWindow(null, translateOffset.ptr)
            logLine("OH_ArkUI_NodeUtils_GetPositionWithTranslateInWindow(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetPositionWithTranslateInScreen() {
        memScoped {
            val translateOffset = alloc<ArkUI_IntOffset>()
            val ret = OH_ArkUI_NodeUtils_GetPositionWithTranslateInScreen(null, translateOffset.ptr)
            logLine("OH_ArkUI_NodeUtils_GetPositionWithTranslateInScreen(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_AddCustomProperty() {
        OH_ArkUI_NodeUtils_AddCustomProperty(null, null, null)
        logLine("OH_ArkUI_NodeUtils_AddCustomProperty(null,null,null) done")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_RemoveCustomProperty() {
        OH_ArkUI_NodeUtils_RemoveCustomProperty(null, null)
        logLine("OH_ArkUI_NodeUtils_RemoveCustomProperty(null,null) done")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetCustomProperty() {
        memScoped {
            val handle = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_NodeUtils_GetCustomProperty(null, null, handle.ptr.reinterpret())
            logLine("OH_ArkUI_NodeUtils_GetCustomProperty(null,null,handle)=$ret")
            //OH_ArkUI_CustomProperty_Destroy( handle.value?.reinterpret<ArkUI_CustomProperty>())
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetParentInPageTree() {
        val ret = OH_ArkUI_NodeUtils_GetParentInPageTree(null)
        logLine("OH_ArkUI_NodeUtils_GetParentInPageTree(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetActiveChildrenInfo() {
        memScoped {
            val handle = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_NodeUtils_GetActiveChildrenInfo(null, handle.ptr.reinterpret())
            logLine("OH_ArkUI_NodeUtils_GetActiveChildrenInfo(null,handle)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetCurrentPageRootNode() {
        val ret = OH_ArkUI_NodeUtils_GetCurrentPageRootNode(null)
        logLine("OH_ArkUI_NodeUtils_GetCurrentPageRootNode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_IsCreatedByNDK() {
        val ret = OH_ArkUI_NodeUtils_IsCreatedByNDK(null)
        logLine("OH_ArkUI_NodeUtils_IsCreatedByNDK(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetNodeType() {
        val ret = OH_ArkUI_NodeUtils_GetNodeType(null)
        logLine("OH_ArkUI_NodeUtils_GetNodeType(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetWindowInfo() {
        memScoped {
            val info = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_NodeUtils_GetWindowInfo(null, info.ptr.reinterpret())
            logLine("OH_ArkUI_NodeUtils_GetWindowInfo(null,info)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetFirstChildIndexWithoutExpand() {
        memScoped {
            val index = alloc<UIntVar>()
            val ret = OH_ArkUI_NodeUtils_GetFirstChildIndexWithoutExpand(null, index.ptr)
            logLine("OH_ArkUI_NodeUtils_GetFirstChildIndexWithoutExpand(null,index)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetLastChildIndexWithoutExpand() {
        memScoped {
            val index = alloc<UIntVar>()
            val ret = OH_ArkUI_NodeUtils_GetLastChildIndexWithoutExpand(null, index.ptr)
            logLine("OH_ArkUI_NodeUtils_GetLastChildIndexWithoutExpand(null,index)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetChildWithExpandMode() {
        memScoped {
            val subnode = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_NodeUtils_GetChildWithExpandMode(null, 0, subnode.ptr.reinterpret(), 0u)
            logLine("OH_ArkUI_NodeUtils_GetChildWithExpandMode(null,0,subnode,0)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_List_CloseAllSwipeActions() {
        val ret = OH_ArkUI_List_CloseAllSwipeActions(null, null, null)
        logLine("OH_ArkUI_List_CloseAllSwipeActions(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_GetContextByNode() {
        val ret = OH_ArkUI_GetContextByNode(null)
        logLine("OH_ArkUI_GetContextByNode(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_RegisterSystemColorModeChangeEvent() {
        val ret = OH_ArkUI_RegisterSystemColorModeChangeEvent(null, null, null)
        logLine("OH_ArkUI_RegisterSystemColorModeChangeEvent(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UnregisterSystemColorModeChangeEvent() {
        OH_ArkUI_UnregisterSystemColorModeChangeEvent(null)
        logLine("OH_ArkUI_UnregisterSystemColorModeChangeEvent(null) done")
    }

    @Test
    fun testOH_ArkUI_RegisterSystemFontStyleChangeEvent() {
        val ret = OH_ArkUI_RegisterSystemFontStyleChangeEvent(null, null, null)
        logLine("OH_ArkUI_RegisterSystemFontStyleChangeEvent(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UnregisterSystemFontStyleChangeEvent() {
        OH_ArkUI_UnregisterSystemFontStyleChangeEvent(null)
        logLine("OH_ArkUI_UnregisterSystemFontStyleChangeEvent(null) done")
    }

    // @Test
    // fun testOH_ArkUI_SystemFontStyleEvent_GetFontSizeScale() {
    //     val ret = OH_ArkUI_SystemFontStyleEvent_GetFontSizeScale(null)
    //     logLine("OH_ArkUI_SystemFontStyleEvent_GetFontSizeScale(null)=$ret")
    // }

    // @Test
    // fun testOH_ArkUI_SystemFontStyleEvent_GetFontWeightScale() {
    //     val ret = OH_ArkUI_SystemFontStyleEvent_GetFontWeightScale(null)
    //     logLine("OH_ArkUI_SystemFontStyleEvent_GetFontWeightScale(null)=$ret")
    // }

    @Test
    fun testOH_ArkUI_NodeUtils_MoveTo() {
        val ret = try { OH_ArkUI_NodeUtils_MoveTo(null, null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_NodeUtils_MoveTo (API 18) exception: $e"); -1 }
        logLine("OH_ArkUI_NodeUtils_MoveTo(null,null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_NativeModule_InvalidateAttributes() {
        val ret = try { OH_ArkUI_NativeModule_InvalidateAttributes(null) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_InvalidateAttributes (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_InvalidateAttributes(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_SetCrossLanguageOption() {
        val ret = OH_ArkUI_NodeUtils_SetCrossLanguageOption(null, null)
        logLine("OH_ArkUI_NodeUtils_SetCrossLanguageOption(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetCrossLanguageOption() {
        val ret = OH_ArkUI_NodeUtils_GetCrossLanguageOption(null, null)
        logLine("OH_ArkUI_NodeUtils_GetCrossLanguageOption(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_RegisterLayoutCallbackOnNodeHandle() {
        val ret = OH_ArkUI_RegisterLayoutCallbackOnNodeHandle(null, null, null)
        logLine("OH_ArkUI_RegisterLayoutCallbackOnNodeHandle(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_RegisterDrawCallbackOnNodeHandle() {
        val ret = OH_ArkUI_RegisterDrawCallbackOnNodeHandle(null, null, null)
        logLine("OH_ArkUI_RegisterDrawCallbackOnNodeHandle(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UnregisterLayoutCallbackOnNodeHandle() {
        val ret = OH_ArkUI_UnregisterLayoutCallbackOnNodeHandle(null)
        logLine("OH_ArkUI_UnregisterLayoutCallbackOnNodeHandle(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_UnregisterDrawCallbackOnNodeHandle() {
        val ret = OH_ArkUI_UnregisterDrawCallbackOnNodeHandle(null)
        logLine("OH_ArkUI_UnregisterDrawCallbackOnNodeHandle(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetAttachedNodeHandleById() {
        memScoped {
            val node = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ArkUI_NodeUtils_GetAttachedNodeHandleById(null, node.ptr.reinterpret())
            logLine("OH_ArkUI_NodeUtils_GetAttachedNodeHandleById(null,node)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_GetNodeSnapshot() {
        val ret = OH_ArkUI_GetNodeSnapshot(null, null, null)
        logLine("OH_ArkUI_GetNodeSnapshot(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetPositionToParent() {
        memScoped {
            val globalOffset = alloc<ArkUI_IntOffset>()
            val ret = OH_ArkUI_NodeUtils_GetPositionToParent(null, globalOffset.ptr)
            logLine("OH_ArkUI_NodeUtils_GetPositionToParent(null,...)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_AddSupportedUIStates() {
        val ret = try { OH_ArkUI_AddSupportedUIStates(null, 0, null, false, null) } catch (e: Throwable) { logLine("OH_ArkUI_AddSupportedUIStates (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_AddSupportedUIStates(null,0,null,false,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_RemoveSupportedUIStates() {
        val ret = try { OH_ArkUI_RemoveSupportedUIStates(null, 0) } catch (e: Throwable) { logLine("OH_ArkUI_RemoveSupportedUIStates (API 20) exception: $e"); ARKUI_ERROR_CODE_PARAM_INVALID }
        logLine("OH_ArkUI_RemoveSupportedUIStates(null,0)=$ret")
    }

    @Test
    fun testOH_ArkUI_RunTaskInScope() {
        val ret = try { OH_ArkUI_RunTaskInScope(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_RunTaskInScope (API 20) exception: $e"); -1 }
        logLine("OH_ArkUI_RunTaskInScope(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetNodeHandleByUniqueId() {
        memScoped {
            val node = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ArkUI_NodeUtils_GetNodeHandleByUniqueId(0u, node.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ArkUI_NodeUtils_GetNodeHandleByUniqueId (API 20) exception: $e"); -1 }
            logLine("OH_ArkUI_NodeUtils_GetNodeHandleByUniqueId(0,node)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NodeUtils_GetNodeUniqueId() {
        memScoped {
            val uniqueId = alloc<IntVar>()
            val ret = try { OH_ArkUI_NodeUtils_GetNodeUniqueId(null, uniqueId.ptr) } catch (e: Throwable) { logLine("OH_ArkUI_NodeUtils_GetNodeUniqueId (API 20) exception: $e"); -1 }
            logLine("OH_ArkUI_NodeUtils_GetNodeUniqueId(null,uniqueId)=$ret")
        }
    }

    @Test
    fun testOH_ArkUI_NativeModule_AdoptChild() {
        val ret = try { OH_ArkUI_NativeModule_AdoptChild(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_AdoptChild (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_AdoptChild(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NativeModule_RemoveAdoptedChild() {
        val ret = try { OH_ArkUI_NativeModule_RemoveAdoptedChild(null, null) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_RemoveAdoptedChild (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_RemoveAdoptedChild(null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_SetForceDarkConfig() {
        val ret = try { OH_ArkUI_SetForceDarkConfig(null, false, ARKUI_NODE_CUSTOM, null) } catch (e: Throwable) { logLine("OH_ArkUI_SetForceDarkConfig (API 20) exception: $e"); -1 }
        logLine("OH_ArkUI_SetForceDarkConfig(null,false,...)=$ret")
    }

    @Test
    fun testOH_ArkUI_PostAsyncUITask() {
        val ret = try { OH_ArkUI_PostAsyncUITask(null, null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_PostAsyncUITask (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_PostAsyncUITask(null,null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PostUITask() {
        val ret = try { OH_ArkUI_PostUITask(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_PostUITask (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_PostUITask(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_PostUITaskAndWait() {
        val ret = try { OH_ArkUI_PostUITaskAndWait(null, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_PostUITaskAndWait (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_PostUITaskAndWait(null,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NativeModule_RegisterCommonEvent() {
        val ret = try { OH_ArkUI_NativeModule_RegisterCommonEvent(null, NODE_WIDTH, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_RegisterCommonEvent (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_RegisterCommonEvent(null,NODE_WIDTH,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NativeModule_UnregisterCommonEvent() {
        val ret = try { OH_ArkUI_NativeModule_UnregisterCommonEvent(null, NODE_WIDTH) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_UnregisterCommonEvent (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_UnregisterCommonEvent(null,NODE_WIDTH)=$ret")
    }

    @Test
    fun testOH_ArkUI_NativeModule_RegisterCommonVisibleAreaApproximateChangeEvent() {
        val ret = try { OH_ArkUI_NativeModule_RegisterCommonVisibleAreaApproximateChangeEvent(null, null, 0, 0f, null, null) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_RegisterCommonVisibleAreaApproximateChangeEvent (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_RegisterCommonVisibleAreaApproximateChangeEvent(null,null,0,0f,null,null)=$ret")
    }

    @Test
    fun testOH_ArkUI_NativeModule_UnregisterCommonVisibleAreaApproximateChangeEvent() {
        val ret = try { OH_ArkUI_NativeModule_UnregisterCommonVisibleAreaApproximateChangeEvent(null) } catch (e: Throwable) { logLine("OH_ArkUI_NativeModule_UnregisterCommonVisibleAreaApproximateChangeEvent (API 21) exception: $e"); -1 }
        logLine("OH_ArkUI_NativeModule_UnregisterCommonVisibleAreaApproximateChangeEvent(null)=$ret")
    }

    @Test
    fun testOH_ArkUI_Swiper_FinishAnimation() {
        val ret = try { OH_ArkUI_Swiper_FinishAnimation(null) } catch (e: Throwable) { logLine("OH_ArkUI_Swiper_FinishAnimation (API 22) exception: $e"); -1 }
        logLine("OH_ArkUI_Swiper_FinishAnimation(null)=$ret")
    }
}
