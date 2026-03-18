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
import platform.InputKit.Input.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class InputTest {

    private fun logLine(msg: String) = println("[stdout] InputTest $msg")

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_Input_Result() {
        logLine("INPUT_SUCCESS=${INPUT_SUCCESS.toInt()}, INPUT_PERMISSION_DENIED=${INPUT_PERMISSION_DENIED.toInt()}, INPUT_PARAMETER_ERROR=${INPUT_PARAMETER_ERROR.toInt()}")
        assertEquals(INPUT_SUCCESS.toInt(), 0)
        assertEquals(INPUT_PERMISSION_DENIED.toInt(), 201)
        assertEquals(INPUT_PARAMETER_ERROR.toInt(), 401)
        logLine("testEnum_Input_Result passed")
    }

    @Test
    fun testEnum_InputEvent_AxisAction() {
        logLine("AXIS_ACTION_CANCEL=${AXIS_ACTION_CANCEL.toInt()}, AXIS_ACTION_BEGIN=${AXIS_ACTION_BEGIN.toInt()}, AXIS_ACTION_UPDATE=${AXIS_ACTION_UPDATE.toInt()}, AXIS_ACTION_END=${AXIS_ACTION_END.toInt()}")
        assertEquals(AXIS_ACTION_CANCEL.toInt(), 0)
        assertEquals(AXIS_ACTION_BEGIN.toInt(), 1)
        assertEquals(AXIS_ACTION_UPDATE.toInt(), 2)
        assertEquals(AXIS_ACTION_END.toInt(), 3)
        logLine("testEnum_InputEvent_AxisAction passed")
    }

    @Test
    fun testEnum_InputEvent_AxisEventType() {
        logLine("AXIS_EVENT_TYPE_PINCH=${AXIS_EVENT_TYPE_PINCH.toInt()}, AXIS_EVENT_TYPE_SCROLL=${AXIS_EVENT_TYPE_SCROLL.toInt()}")
        assertEquals(AXIS_EVENT_TYPE_PINCH.toInt(), 1)
        assertEquals(AXIS_EVENT_TYPE_SCROLL.toInt(), 2)
        logLine("testEnum_InputEvent_AxisEventType passed")
    }

    @Test
    fun testEnum_InputEvent_AxisType() {
        logLine("AXIS_TYPE_UNKNOWN=${InputEvent_AxisType.AXIS_TYPE_UNKNOWN.value.toInt()}, AXIS_TYPE_SCROLL_VERTICAL=${InputEvent_AxisType.AXIS_TYPE_SCROLL_VERTICAL.value.toInt()}, AXIS_TYPE_SCROLL_HORIZONTAL=${InputEvent_AxisType.AXIS_TYPE_SCROLL_HORIZONTAL.value.toInt()}, AXIS_TYPE_PINCH=${InputEvent_AxisType.AXIS_TYPE_PINCH.value.toInt()}, AXIS_TYPE_ROTATE=${InputEvent_AxisType.AXIS_TYPE_ROTATE.value.toInt()}")
        assertEquals(InputEvent_AxisType.AXIS_TYPE_UNKNOWN.value.toInt(), 0)
        assertEquals(InputEvent_AxisType.AXIS_TYPE_SCROLL_VERTICAL.value.toInt(), 1)
        assertEquals(InputEvent_AxisType.AXIS_TYPE_SCROLL_HORIZONTAL.value.toInt(), 2)
        assertEquals(InputEvent_AxisType.AXIS_TYPE_PINCH.value.toInt(), 3)
        assertEquals(InputEvent_AxisType.AXIS_TYPE_ROTATE.value.toInt(), 4)
        logLine("testEnum_InputEvent_AxisType passed")
    }

    @Test
    fun testEnum_InputEvent_SourceType() {
        logLine("SOURCE_TYPE_MOUSE=${SOURCE_TYPE_MOUSE.toInt()}, SOURCE_TYPE_TOUCHSCREEN=${SOURCE_TYPE_TOUCHSCREEN.toInt()}, SOURCE_TYPE_TOUCHPAD=${SOURCE_TYPE_TOUCHPAD.toInt()}")
        assertEquals(SOURCE_TYPE_MOUSE.toInt(), 1)
        assertEquals(SOURCE_TYPE_TOUCHSCREEN.toInt(), 2)
        assertEquals(SOURCE_TYPE_TOUCHPAD.toInt(), 3)
        logLine("testEnum_InputEvent_SourceType passed")
    }

    @Test
    fun testEnum_Input_KeyStateAction() {
        logLine("KEY_DEFAULT=${KEY_DEFAULT.toInt()}, KEY_PRESSED=${KEY_PRESSED.toInt()}, KEY_RELEASED=${KEY_RELEASED.toInt()}, KEY_SWITCH_ON=${KEY_SWITCH_ON.toInt()}, KEY_SWITCH_OFF=${KEY_SWITCH_OFF.toInt()}")
        assertEquals(KEY_DEFAULT.toInt(), -1)
        assertEquals(KEY_PRESSED.toInt(), 0)
        assertEquals(KEY_RELEASED.toInt(), 1)
        assertEquals(KEY_SWITCH_ON.toInt(), 2)
        assertEquals(KEY_SWITCH_OFF.toInt(), 3)
        logLine("testEnum_Input_KeyStateAction passed")
    }

    @Test
    fun testEnum_Input_KeyEventAction() {
        logLine("KEY_ACTION_CANCEL=${KEY_ACTION_CANCEL.toInt()}, KEY_ACTION_DOWN=${KEY_ACTION_DOWN.toInt()}, KEY_ACTION_UP=${KEY_ACTION_UP.toInt()}")
        assertEquals(KEY_ACTION_CANCEL.toInt(), 0)
        assertEquals(KEY_ACTION_DOWN.toInt(), 1)
        assertEquals(KEY_ACTION_UP.toInt(), 2)
        logLine("testEnum_Input_KeyEventAction passed")
    }

    @Test
    fun testEnum_Input_MouseEventAction() {
        logLine("MOUSE_ACTION_CANCEL=${MOUSE_ACTION_CANCEL.toInt()}, MOUSE_ACTION_MOVE=${MOUSE_ACTION_MOVE.toInt()}, MOUSE_ACTION_BUTTON_DOWN=${MOUSE_ACTION_BUTTON_DOWN.toInt()}, MOUSE_ACTION_BUTTON_UP=${MOUSE_ACTION_BUTTON_UP.toInt()}, MOUSE_ACTION_AXIS_BEGIN=${MOUSE_ACTION_AXIS_BEGIN.toInt()}, MOUSE_ACTION_AXIS_UPDATE=${MOUSE_ACTION_AXIS_UPDATE.toInt()}, MOUSE_ACTION_AXIS_END=${MOUSE_ACTION_AXIS_END.toInt()}")
        assertEquals(MOUSE_ACTION_CANCEL.toInt(), 0)
        assertEquals(MOUSE_ACTION_MOVE.toInt(), 1)
        assertEquals(MOUSE_ACTION_BUTTON_DOWN.toInt(), 2)
        assertEquals(MOUSE_ACTION_BUTTON_UP.toInt(), 3)
        assertEquals(MOUSE_ACTION_AXIS_BEGIN.toInt(), 4)
        assertEquals(MOUSE_ACTION_AXIS_UPDATE.toInt(), 5)
        assertEquals(MOUSE_ACTION_AXIS_END.toInt(), 6)
        logLine("testEnum_Input_MouseEventAction passed")
    }

    @Test
    fun testEnum_InputEvent_MouseAxis() {
        logLine("MOUSE_AXIS_SCROLL_VERTICAL=${MOUSE_AXIS_SCROLL_VERTICAL.toInt()}, MOUSE_AXIS_SCROLL_HORIZONTAL=${MOUSE_AXIS_SCROLL_HORIZONTAL.toInt()}")
        assertEquals(MOUSE_AXIS_SCROLL_VERTICAL.toInt(), 0)
        assertEquals(MOUSE_AXIS_SCROLL_HORIZONTAL.toInt(), 1)
        logLine("testEnum_InputEvent_MouseAxis passed")
    }

    @Test
    fun testEnum_Input_MouseEventButton() {
        logLine("MOUSE_BUTTON_NONE=${MOUSE_BUTTON_NONE.toInt()}, MOUSE_BUTTON_LEFT=${MOUSE_BUTTON_LEFT.toInt()}, MOUSE_BUTTON_MIDDLE=${MOUSE_BUTTON_MIDDLE.toInt()}, MOUSE_BUTTON_RIGHT=${MOUSE_BUTTON_RIGHT.toInt()}, MOUSE_BUTTON_FORWARD=${MOUSE_BUTTON_FORWARD.toInt()}, MOUSE_BUTTON_BACK=${MOUSE_BUTTON_BACK.toInt()}")
        assertEquals(MOUSE_BUTTON_NONE.toInt(), -1)
        assertEquals(MOUSE_BUTTON_LEFT.toInt(), 0)
        assertEquals(MOUSE_BUTTON_MIDDLE.toInt(), 1)
        assertEquals(MOUSE_BUTTON_RIGHT.toInt(), 2)
        assertEquals(MOUSE_BUTTON_FORWARD.toInt(), 3)
        assertEquals(MOUSE_BUTTON_BACK.toInt(), 4)
        logLine("testEnum_Input_MouseEventButton passed")
    }

    @Test
    fun testEnum_Input_TouchEventAction() {
        logLine("TOUCH_ACTION_CANCEL=${TOUCH_ACTION_CANCEL.toInt()}, TOUCH_ACTION_DOWN=${TOUCH_ACTION_DOWN.toInt()}, TOUCH_ACTION_MOVE=${TOUCH_ACTION_MOVE.toInt()}, TOUCH_ACTION_UP=${TOUCH_ACTION_UP.toInt()}")
        assertEquals(TOUCH_ACTION_CANCEL.toInt(), 0)
        assertEquals(TOUCH_ACTION_DOWN.toInt(), 1)
        assertEquals(TOUCH_ACTION_MOVE.toInt(), 2)
        assertEquals(TOUCH_ACTION_UP.toInt(), 3)
        logLine("testEnum_Input_TouchEventAction passed")
    }

    @Test
    fun testEnum_Input_KeyboardType() {
        logLine("KEYBOARD_TYPE_NONE=${KEYBOARD_TYPE_NONE.toInt()}, KEYBOARD_TYPE_UNKNOWN=${KEYBOARD_TYPE_UNKNOWN.toInt()}, KEYBOARD_TYPE_ALPHABETIC=${KEYBOARD_TYPE_ALPHABETIC.toInt()}, KEYBOARD_TYPE_DIGITAL=${KEYBOARD_TYPE_DIGITAL.toInt()}, KEYBOARD_TYPE_STYLUS=${KEYBOARD_TYPE_STYLUS.toInt()}, KEYBOARD_TYPE_REMOTE_CONTROL=${KEYBOARD_TYPE_REMOTE_CONTROL.toInt()}")
        assertEquals(KEYBOARD_TYPE_NONE.toInt(), 0)
        assertEquals(KEYBOARD_TYPE_UNKNOWN.toInt(), 1)
        assertEquals(KEYBOARD_TYPE_ALPHABETIC.toInt(), 2)
        assertEquals(KEYBOARD_TYPE_DIGITAL.toInt(), 3)
        assertEquals(KEYBOARD_TYPE_STYLUS.toInt(), 4)
        assertEquals(KEYBOARD_TYPE_REMOTE_CONTROL.toInt(), 5)
        logLine("testEnum_Input_KeyboardType passed")
    }

    @Test
    fun testEnum_Input_InjectionStatus() {
        logLine("UNAUTHORIZED=${UNAUTHORIZED.toInt()}, AUTHORIZING=${AUTHORIZING.toInt()}, AUTHORIZED=${AUTHORIZED.toInt()}")
        assertEquals(UNAUTHORIZED.toInt(), 0)
        assertEquals(AUTHORIZING.toInt(), 1)
        assertEquals(AUTHORIZED.toInt(), 2)
        logLine("testEnum_Input_InjectionStatus passed")
    }

    @Test
    fun testEnum_Input_PointerStyle() {
        logLine("DEFAULT=${DEFAULT.toInt()}, EAST=${EAST.toInt()}, CROSS=${CROSS.toInt()}")
        assertEquals(DEFAULT.toInt(), 0)
        assertEquals(EAST.toInt(), 1)
        assertEquals(CROSS.toInt(), 13)
        logLine("testEnum_Input_PointerStyle passed")
    }

    @Test
    fun testEnum_Input_KeyCode() {
        logLine("KEYCODE_UNKNOWN=${KEYCODE_UNKNOWN.toInt()}, KEYCODE_FN=${KEYCODE_FN.toInt()}, KEYCODE_0=${KEYCODE_0.toInt()}, KEYCODE_A=${KEYCODE_A.toInt()}")
        assertEquals(KEYCODE_UNKNOWN.toInt(), -1)
        assertEquals(KEYCODE_FN.toInt(), 0)
        assertEquals(KEYCODE_0.toInt(), 2000)
        assertEquals(KEYCODE_A.toInt(), 2017)
        logLine("testEnum_Input_KeyCode passed")
    }

    @Test
    fun testEnum_Input_Result_Full() {
        logLine("INPUT_SUCCESS=${INPUT_SUCCESS.toInt()}, INPUT_PERMISSION_DENIED=${INPUT_PERMISSION_DENIED.toInt()}, INPUT_NOT_SYSTEM_APPLICATION=${INPUT_NOT_SYSTEM_APPLICATION.toInt()}, INPUT_PARAMETER_ERROR=${INPUT_PARAMETER_ERROR.toInt()}, INPUT_DEVICE_NOT_SUPPORTED=${INPUT_DEVICE_NOT_SUPPORTED.toInt()}, INPUT_SERVICE_EXCEPTION=${INPUT_SERVICE_EXCEPTION.toInt()}, INPUT_REPEAT_INTERCEPTOR=${INPUT_REPEAT_INTERCEPTOR.toInt()}, INPUT_OCCUPIED_BY_SYSTEM=${INPUT_OCCUPIED_BY_SYSTEM.toInt()}, INPUT_OCCUPIED_BY_OTHER=${INPUT_OCCUPIED_BY_OTHER.toInt()}, INPUT_KEYBOARD_DEVICE_NOT_EXIST=${INPUT_KEYBOARD_DEVICE_NOT_EXIST.toInt()}, INPUT_INJECTION_AUTHORIZING=${INPUT_INJECTION_AUTHORIZING.toInt()}, INPUT_INJECTION_OPERATION_FREQUENT=${INPUT_INJECTION_OPERATION_FREQUENT.toInt()}, INPUT_INJECTION_AUTHORIZED=${INPUT_INJECTION_AUTHORIZED.toInt()}, INPUT_INJECTION_AUTHORIZED_OTHERS=${INPUT_INJECTION_AUTHORIZED_OTHERS.toInt()}, INPUT_APP_NOT_FOCUSED=${INPUT_APP_NOT_FOCUSED.toInt()}, INPUT_DEVICE_NO_POINTER=${INPUT_DEVICE_NO_POINTER.toInt()}, INPUT_INVALID_WINDOWID=${INPUT_INVALID_WINDOWID.toInt()}")
        assertEquals(INPUT_SUCCESS.toInt(), 0)
        assertEquals(INPUT_PERMISSION_DENIED.toInt(), 201)
        assertEquals(INPUT_NOT_SYSTEM_APPLICATION.toInt(), 202)
        assertEquals(INPUT_PARAMETER_ERROR.toInt(), 401)
        assertEquals(INPUT_DEVICE_NOT_SUPPORTED.toInt(), 801)
        assertEquals(INPUT_SERVICE_EXCEPTION.toInt(), 3800001)
        assertEquals(INPUT_REPEAT_INTERCEPTOR.toInt(), 4200001)
        assertEquals(INPUT_OCCUPIED_BY_SYSTEM.toInt(), 4200002)
        assertEquals(INPUT_OCCUPIED_BY_OTHER.toInt(), 4200003)
        assertEquals(INPUT_KEYBOARD_DEVICE_NOT_EXIST.toInt(), 3900002)
        assertEquals(INPUT_INJECTION_AUTHORIZING.toInt(), 3900005)
        assertEquals(INPUT_INJECTION_OPERATION_FREQUENT.toInt(), 3900006)
        assertEquals(INPUT_INJECTION_AUTHORIZED.toInt(), 3900007)
        assertEquals(INPUT_INJECTION_AUTHORIZED_OTHERS.toInt(), 3900008)
        assertEquals(INPUT_APP_NOT_FOCUSED.toInt(), 3900009)
        assertEquals(INPUT_DEVICE_NO_POINTER.toInt(), 3900010)
        assertEquals(INPUT_INVALID_WINDOWID.toInt(), 26500001)
        logLine("testEnum_Input_Result_Full passed")
    }

    // ==================== KeyState（每个 C API 独立 @Test） ====================

    @Test
    fun testOH_Input_CreateKeyState() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            logLine("OH_Input_CreateKeyState=$ks")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyCode() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            OH_Input_SetKeyCode(ks, 2017)
            logLine("OH_Input_SetKeyCode=called")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyCode() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            OH_Input_SetKeyCode(ks, 2017)
            val kc = OH_Input_GetKeyCode(ks)
            assertNotNull(kc)
            logLine("OH_Input_GetKeyCode=$kc")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyPressed() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            OH_Input_SetKeyPressed(ks, 1)
            logLine("OH_Input_SetKeyPressed=called")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyPressed() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            OH_Input_SetKeyPressed(ks, 1)
            val pressed = OH_Input_GetKeyPressed(ks)
            assertNotNull(pressed)
            logLine("OH_Input_GetKeyPressed=$pressed")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeySwitch() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            OH_Input_SetKeySwitch(ks, 2)
            logLine("OH_Input_SetKeySwitch=called")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeySwitch() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            OH_Input_SetKeySwitch(ks, 2)
            val sw = OH_Input_GetKeySwitch(ks)
            assertNotNull(sw)
            logLine("OH_Input_GetKeySwitch=$sw")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyState() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            val rc = OH_Input_GetKeyState(ks)
            assertNotNull(rc)
            logLine("OH_Input_GetKeyState=$rc")
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_DestroyKeyState() {
        memScoped {
            val ks = OH_Input_CreateKeyState()
            assertNotNull(ks)
            val ptr = alloc<CPointerVar<Input_KeyState>>()
            ptr.value = ks
            OH_Input_DestroyKeyState(ptr.ptr)
            logLine("OH_Input_DestroyKeyState=called")
        }
    }

    // ==================== KeyEvent ====================

    @Test
    fun testOH_Input_CreateKeyEvent() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            logLine("OH_Input_CreateKeyEvent=$ke")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyEventAction() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventAction(ke, 1)
            logLine("OH_Input_SetKeyEventAction=called")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyEventAction() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventAction(ke, 1)
            val a = OH_Input_GetKeyEventAction(ke)
            assertNotNull(a)
            logLine("OH_Input_GetKeyEventAction=$a")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyEventKeyCode() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventKeyCode(ke, 2017)
            logLine("OH_Input_SetKeyEventKeyCode=called")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyEventKeyCode() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventKeyCode(ke, 2017)
            val kc = OH_Input_GetKeyEventKeyCode(ke)
            assertNotNull(kc)
            logLine("OH_Input_GetKeyEventKeyCode=$kc")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyEventActionTime() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventActionTime(ke, 1000L)
            logLine("OH_Input_SetKeyEventActionTime=called")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyEventActionTime() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventActionTime(ke, 1000L)
            val t = OH_Input_GetKeyEventActionTime(ke)
            assertNotNull(t)
            logLine("OH_Input_GetKeyEventActionTime=$t")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyEventWindowId() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventWindowId(ke, 1)
            logLine("OH_Input_SetKeyEventWindowId=called")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyEventWindowId() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventWindowId(ke, 1)
            val wid = OH_Input_GetKeyEventWindowId(ke)
            assertNotNull(wid)
            logLine("OH_Input_GetKeyEventWindowId=$wid")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetKeyEventDisplayId() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventDisplayId(ke, 0)
            logLine("OH_Input_SetKeyEventDisplayId=called")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyEventDisplayId() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            OH_Input_SetKeyEventDisplayId(ke, 0)
            val did = OH_Input_GetKeyEventDisplayId(ke)
            assertNotNull(did)
            logLine("OH_Input_GetKeyEventDisplayId=$did")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_InjectKeyEvent() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            val inj = OH_Input_InjectKeyEvent(ke)
            assertNotNull(inj)
            logLine("OH_Input_InjectKeyEvent=$inj")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyEventId() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            val eventIdVar = alloc<IntVar>()
            val rc = try { OH_Input_GetKeyEventId(ke, eventIdVar.ptr) } catch (e: Throwable) { logLine("OH_Input_GetKeyEventId (API 21) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_GetKeyEventId=$rc")
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_DestroyKeyEvent() {
        memScoped {
            val ke = OH_Input_CreateKeyEvent()
            assertNotNull(ke)
            val ptr = alloc<CPointerVar<Input_KeyEvent>>()
            ptr.value = ke
            OH_Input_DestroyKeyEvent(ptr.ptr)
            logLine("OH_Input_DestroyKeyEvent=called")
        }
    }

    // ==================== MouseEvent ====================

    @Test
    fun testOH_Input_CreateMouseEvent() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            logLine("OH_Input_CreateMouseEvent=$me")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventAction() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventAction(me, 1)
            logLine("OH_Input_SetMouseEventAction=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventAction() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventAction(me, 1)
            val a = OH_Input_GetMouseEventAction(me)
            assertNotNull(a)
            logLine("OH_Input_GetMouseEventAction=$a")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventDisplayX() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventDisplayX(me, 100)
            logLine("OH_Input_SetMouseEventDisplayX=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventDisplayX() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventDisplayX(me, 100)
            val x = OH_Input_GetMouseEventDisplayX(me)
            assertNotNull(x)
            logLine("OH_Input_GetMouseEventDisplayX=$x")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventDisplayY() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventDisplayY(me, 150)
            logLine("OH_Input_SetMouseEventDisplayY=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventDisplayY() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventDisplayY(me, 150)
            val y = OH_Input_GetMouseEventDisplayY(me)
            assertNotNull(y)
            logLine("OH_Input_GetMouseEventDisplayY=$y")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventButton() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventButton(me, 0)
            logLine("OH_Input_SetMouseEventButton=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventButton() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventButton(me, 0)
            val b = OH_Input_GetMouseEventButton(me)
            assertNotNull(b)
            logLine("OH_Input_GetMouseEventButton=$b")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventAxisType() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventAxisType(me, 0)
            logLine("OH_Input_SetMouseEventAxisType=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventAxisType() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventAxisType(me, 0)
            val t = OH_Input_GetMouseEventAxisType(me)
            assertNotNull(t)
            logLine("OH_Input_GetMouseEventAxisType=$t")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventAxisValue() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventAxisValue(me, 1.0f)
            logLine("OH_Input_SetMouseEventAxisValue=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventAxisValue() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventAxisValue(me, 1.0f)
            val v = OH_Input_GetMouseEventAxisValue(me)
            assertNotNull(v)
            logLine("OH_Input_GetMouseEventAxisValue=$v")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventActionTime() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventActionTime(me, 1000L)
            logLine("OH_Input_SetMouseEventActionTime=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventActionTime() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventActionTime(me, 1000L)
            val t = OH_Input_GetMouseEventActionTime(me)
            assertNotNull(t)
            logLine("OH_Input_GetMouseEventActionTime=$t")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventWindowId() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventWindowId(me, 1)
            logLine("OH_Input_SetMouseEventWindowId=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventWindowId() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventWindowId(me, 1)
            val wid = OH_Input_GetMouseEventWindowId(me)
            assertNotNull(wid)
            logLine("OH_Input_GetMouseEventWindowId=$wid")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventDisplayId() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventDisplayId(me, 0)
            logLine("OH_Input_SetMouseEventDisplayId=called")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetMouseEventDisplayId() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            OH_Input_SetMouseEventDisplayId(me, 0)
            val did = OH_Input_GetMouseEventDisplayId(me)
            assertNotNull(did)
            logLine("OH_Input_GetMouseEventDisplayId=$did")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetMouseEventGlobalX() {
        try {
            memScoped {
                val me = OH_Input_CreateMouseEvent()
                assertNotNull(me)
                OH_Input_SetMouseEventGlobalX(me, 100)
                logLine("OH_Input_SetMouseEventGlobalX=called")
                val ptr = alloc<CPointerVar<Input_MouseEvent>>()
                ptr.value = me
                OH_Input_DestroyMouseEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_SetMouseEventGlobalX (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_GetMouseEventGlobalX() {
        try {
            memScoped {
                val me = OH_Input_CreateMouseEvent()
                assertNotNull(me)
                OH_Input_SetMouseEventGlobalX(me, 100)
                val x = OH_Input_GetMouseEventGlobalX(me)
                assertNotNull(x)
                logLine("OH_Input_GetMouseEventGlobalX=$x")
                val ptr = alloc<CPointerVar<Input_MouseEvent>>()
                ptr.value = me
                OH_Input_DestroyMouseEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_GetMouseEventGlobalX (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_SetMouseEventGlobalY() {
        try {
            memScoped {
                val me = OH_Input_CreateMouseEvent()
                assertNotNull(me)
                OH_Input_SetMouseEventGlobalY(me, 150)
                logLine("OH_Input_SetMouseEventGlobalY=called")
                val ptr = alloc<CPointerVar<Input_MouseEvent>>()
                ptr.value = me
                OH_Input_DestroyMouseEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_SetMouseEventGlobalY (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_GetMouseEventGlobalY() {
        try {
            memScoped {
                val me = OH_Input_CreateMouseEvent()
                assertNotNull(me)
                OH_Input_SetMouseEventGlobalY(me, 150)
                val y = OH_Input_GetMouseEventGlobalY(me)
                assertNotNull(y)
                logLine("OH_Input_GetMouseEventGlobalY=$y")
                val ptr = alloc<CPointerVar<Input_MouseEvent>>()
                ptr.value = me
                OH_Input_DestroyMouseEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_GetMouseEventGlobalY (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_InjectMouseEvent() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            val rc = OH_Input_InjectMouseEvent(me)
            assertNotNull(rc)
            logLine("OH_Input_InjectMouseEvent=$rc")
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_InjectMouseEventGlobal() {
        try {
            memScoped {
                val me = OH_Input_CreateMouseEvent()
                assertNotNull(me)
                val rc = OH_Input_InjectMouseEventGlobal(me)
                assertNotNull(rc)
                logLine("OH_Input_InjectMouseEventGlobal=$rc")
                val ptr = alloc<CPointerVar<Input_MouseEvent>>()
                ptr.value = me
                OH_Input_DestroyMouseEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_InjectMouseEventGlobal (API 20 / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_DestroyMouseEvent() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            val ptr = alloc<CPointerVar<Input_MouseEvent>>()
            ptr.value = me
            OH_Input_DestroyMouseEvent(ptr.ptr)
            logLine("OH_Input_DestroyMouseEvent=called")
        }
    }

    // ==================== TouchEvent ====================

    @Test
    fun testOH_Input_CreateTouchEvent() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            logLine("OH_Input_CreateTouchEvent=$te")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventAction() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventAction(te, 1)
            logLine("OH_Input_SetTouchEventAction=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventAction() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventAction(te, 1)
            val a = OH_Input_GetTouchEventAction(te)
            assertNotNull(a)
            logLine("OH_Input_GetTouchEventAction=$a")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventFingerId() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventFingerId(te, 0)
            logLine("OH_Input_SetTouchEventFingerId=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventFingerId() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventFingerId(te, 0)
            val id = OH_Input_GetTouchEventFingerId(te)
            assertNotNull(id)
            logLine("OH_Input_GetTouchEventFingerId=$id")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventDisplayX() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventDisplayX(te, 100)
            logLine("OH_Input_SetTouchEventDisplayX=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventDisplayX() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventDisplayX(te, 100)
            val x = OH_Input_GetTouchEventDisplayX(te)
            assertNotNull(x)
            logLine("OH_Input_GetTouchEventDisplayX=$x")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventDisplayY() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventDisplayY(te, 150)
            logLine("OH_Input_SetTouchEventDisplayY=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventDisplayY() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventDisplayY(te, 150)
            val y = OH_Input_GetTouchEventDisplayY(te)
            assertNotNull(y)
            logLine("OH_Input_GetTouchEventDisplayY=$y")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventActionTime() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventActionTime(te, 1000L)
            logLine("OH_Input_SetTouchEventActionTime=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventActionTime() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventActionTime(te, 1000L)
            val t = OH_Input_GetTouchEventActionTime(te)
            assertNotNull(t)
            logLine("OH_Input_GetTouchEventActionTime=$t")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventWindowId() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventWindowId(te, 1)
            logLine("OH_Input_SetTouchEventWindowId=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventWindowId() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventWindowId(te, 1)
            val wid = OH_Input_GetTouchEventWindowId(te)
            assertNotNull(wid)
            logLine("OH_Input_GetTouchEventWindowId=$wid")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventDisplayId() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventDisplayId(te, 0)
            logLine("OH_Input_SetTouchEventDisplayId=called")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetTouchEventDisplayId() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            OH_Input_SetTouchEventDisplayId(te, 0)
            val did = OH_Input_GetTouchEventDisplayId(te)
            assertNotNull(did)
            logLine("OH_Input_GetTouchEventDisplayId=$did")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetTouchEventGlobalX() {
        try {
            memScoped {
                val te = OH_Input_CreateTouchEvent()
                assertNotNull(te)
                OH_Input_SetTouchEventGlobalX(te, 100)
                logLine("OH_Input_SetTouchEventGlobalX=called")
                val ptr = alloc<CPointerVar<Input_TouchEvent>>()
                ptr.value = te
                OH_Input_DestroyTouchEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_SetTouchEventGlobalX (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_GetTouchEventGlobalX() {
        try {
            memScoped {
                val te = OH_Input_CreateTouchEvent()
                assertNotNull(te)
                OH_Input_SetTouchEventGlobalX(te, 100)
                val x = OH_Input_GetTouchEventGlobalX(te)
                assertNotNull(x)
                logLine("OH_Input_GetTouchEventGlobalX=$x")
                val ptr = alloc<CPointerVar<Input_TouchEvent>>()
                ptr.value = te
                OH_Input_DestroyTouchEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_GetTouchEventGlobalX (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_SetTouchEventGlobalY() {
        try {
            memScoped {
                val te = OH_Input_CreateTouchEvent()
                assertNotNull(te)
                OH_Input_SetTouchEventGlobalY(te, 150)
                logLine("OH_Input_SetTouchEventGlobalY=called")
                val ptr = alloc<CPointerVar<Input_TouchEvent>>()
                ptr.value = te
                OH_Input_DestroyTouchEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_SetTouchEventGlobalY (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_GetTouchEventGlobalY() {
        try {
            memScoped {
                val te = OH_Input_CreateTouchEvent()
                assertNotNull(te)
                OH_Input_SetTouchEventGlobalY(te, 150)
                val y = OH_Input_GetTouchEventGlobalY(te)
                assertNotNull(y)
                logLine("OH_Input_GetTouchEventGlobalY=$y")
                val ptr = alloc<CPointerVar<Input_TouchEvent>>()
                ptr.value = te
                OH_Input_DestroyTouchEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_GetTouchEventGlobalY (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_InjectTouchEvent() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            val rc = OH_Input_InjectTouchEvent(te)
            assertNotNull(rc)
            logLine("OH_Input_InjectTouchEvent=$rc")
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_InjectTouchEventGlobal() {
        try {
            memScoped {
                val te = OH_Input_CreateTouchEvent()
                assertNotNull(te)
                val rc = OH_Input_InjectTouchEventGlobal(te)
                assertNotNull(rc)
                logLine("OH_Input_InjectTouchEventGlobal=$rc")
                val ptr = alloc<CPointerVar<Input_TouchEvent>>()
                ptr.value = te
                OH_Input_DestroyTouchEvent(ptr.ptr)
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_InjectTouchEventGlobal (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_DestroyTouchEvent() {
        memScoped {
            val te = OH_Input_CreateTouchEvent()
            assertNotNull(te)
            val ptr = alloc<CPointerVar<Input_TouchEvent>>()
            ptr.value = te
            OH_Input_DestroyTouchEvent(ptr.ptr)
            logLine("OH_Input_DestroyTouchEvent=called")
        }
    }

    // ==================== AxisEvent ====================

    @Test
    fun testOH_Input_CreateAxisEvent() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            logLine("OH_Input_CreateAxisEvent=$ae")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventAction() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventAction(ae, AXIS_ACTION_BEGIN)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventAction=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventAction() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventAction(ae, AXIS_ACTION_BEGIN)
            val actVar = alloc<InputEvent_AxisActionVar>()
            val rc = OH_Input_GetAxisEventAction(ae, actVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventAction=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventDisplayX() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventDisplayX(ae, 100.0f)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventDisplayX=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventDisplayX() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventDisplayX(ae, 100.0f)
            val dxVar = alloc<FloatVar>()
            val rc = OH_Input_GetAxisEventDisplayX(ae, dxVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventDisplayX=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventDisplayY() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventDisplayY(ae, 150.0f)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventDisplayY=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventDisplayY() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventDisplayY(ae, 150.0f)
            val dyVar = alloc<FloatVar>()
            val rc = OH_Input_GetAxisEventDisplayY(ae, dyVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventDisplayY=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventAxisValue() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventAxisValue(ae, InputEvent_AxisType.AXIS_TYPE_SCROLL_VERTICAL, 1.0)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventAxisValue=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventAxisValue() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventAxisValue(ae, InputEvent_AxisType.AXIS_TYPE_SCROLL_VERTICAL, 1.0)
            val avVar = alloc<DoubleVar>()
            val rc = OH_Input_GetAxisEventAxisValue(ae, InputEvent_AxisType.AXIS_TYPE_SCROLL_VERTICAL, avVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventAxisValue=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventActionTime() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventActionTime(ae, 1000L)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventActionTime=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventActionTime() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventActionTime(ae, 1000L)
            val atVar = alloc<LongVar>()
            val rc = OH_Input_GetAxisEventActionTime(ae, atVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventActionTime=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventType() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventType(ae, AXIS_EVENT_TYPE_SCROLL)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventType=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventType() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventType(ae, AXIS_EVENT_TYPE_SCROLL)
            val etVar = alloc<InputEvent_AxisEventTypeVar>()
            val rc = OH_Input_GetAxisEventType(ae, etVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventType=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventSourceType() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventSourceType(ae, SOURCE_TYPE_MOUSE)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventSourceType=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventSourceType() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventSourceType(ae, SOURCE_TYPE_MOUSE)
            val stVar = alloc<InputEvent_SourceTypeVar>()
            val rc = OH_Input_GetAxisEventSourceType(ae, stVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventSourceType=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventWindowId() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventWindowId(ae, 1)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventWindowId=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventWindowId() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventWindowId(ae, 1)
            val widVar = alloc<IntVar>()
            val rc = OH_Input_GetAxisEventWindowId(ae, widVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventWindowId=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventDisplayId() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val rc = OH_Input_SetAxisEventDisplayId(ae, 0)
            assertNotNull(rc)
            logLine("OH_Input_SetAxisEventDisplayId=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_GetAxisEventDisplayId() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            OH_Input_SetAxisEventDisplayId(ae, 0)
            val didVar = alloc<IntVar>()
            val rc = OH_Input_GetAxisEventDisplayId(ae, didVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetAxisEventDisplayId=$rc")
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
        }
    }

    @Test
    fun testOH_Input_SetAxisEventGlobalX() {
        try {
            memScoped {
                val ae = OH_Input_CreateAxisEvent()
                assertNotNull(ae)
                val rc = OH_Input_SetAxisEventGlobalX(ae, 100)
                assertNotNull(rc)
                logLine("OH_Input_SetAxisEventGlobalX=$rc")
                val ptr = alloc<CPointerVar<Input_AxisEvent>>()
                ptr.value = ae
                assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_SetAxisEventGlobalX (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_GetAxisEventGlobalX() {
        try {
            memScoped {
                val ae = OH_Input_CreateAxisEvent()
                assertNotNull(ae)
                OH_Input_SetAxisEventGlobalX(ae, 100)
                val gxVar = alloc<IntVar>()
                val rc = OH_Input_GetAxisEventGlobalX(ae, gxVar.ptr)
                assertNotNull(rc)
                logLine("OH_Input_GetAxisEventGlobalX=$rc")
                val ptr = alloc<CPointerVar<Input_AxisEvent>>()
                ptr.value = ae
                assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_GetAxisEventGlobalX (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_SetAxisEventGlobalY() {
        try {
            memScoped {
                val ae = OH_Input_CreateAxisEvent()
                assertNotNull(ae)
                val rc = OH_Input_SetAxisEventGlobalY(ae, 150)
                assertNotNull(rc)
                logLine("OH_Input_SetAxisEventGlobalY=$rc")
                val ptr = alloc<CPointerVar<Input_AxisEvent>>()
                ptr.value = ae
                assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_SetAxisEventGlobalY (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_GetAxisEventGlobalY() {
        try {
            memScoped {
                val ae = OH_Input_CreateAxisEvent()
                assertNotNull(ae)
                OH_Input_SetAxisEventGlobalY(ae, 150)
                val gyVar = alloc<IntVar>()
                val rc = OH_Input_GetAxisEventGlobalY(ae, gyVar.ptr)
                assertNotNull(rc)
                logLine("OH_Input_GetAxisEventGlobalY=$rc")
                val ptr = alloc<CPointerVar<Input_AxisEvent>>()
                ptr.value = ae
                assertNotNull(OH_Input_DestroyAxisEvent(ptr.ptr))
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_GetAxisEventGlobalY (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_DestroyAxisEvent() {
        memScoped {
            val ae = OH_Input_CreateAxisEvent()
            assertNotNull(ae)
            val ptr = alloc<CPointerVar<Input_AxisEvent>>()
            ptr.value = ae
            val rc = OH_Input_DestroyAxisEvent(ptr.ptr)
            assertNotNull(rc)
            logLine("OH_Input_DestroyAxisEvent=$rc")
        }
    }

    // ==================== EventInjection ====================

    @Test
    fun testOH_Input_RequestInjection() {
        try {
            memScoped {
                val rc = OH_Input_RequestInjection(null)
                assertNotNull(rc)
                logLine("OH_Input_RequestInjection=$rc")
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_RequestInjection (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_QueryAuthorizedStatus() {
        try {
            memScoped {
                val statusVar = alloc<Input_InjectionStatusVar>()
                val rc = OH_Input_QueryAuthorizedStatus(statusVar.ptr)
                assertNotNull(rc)
                logLine("OH_Input_QueryAuthorizedStatus=$rc")
            }
        } catch (e: Throwable) {
            logLine("InputTest testOH_Input_QueryAuthorizedStatus (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_Input_CancelInjection() {
        memScoped {
            OH_Input_CancelInjection()
            logLine("OH_Input_CancelInjection=called")
        }
    }

    // ==================== EventMonitors ====================

    @Test
    fun testOH_Input_AddKeyEventMonitor() {
        memScoped {
            val rc = OH_Input_AddKeyEventMonitor(null)
            assertNotNull(rc)
            logLine("OH_Input_AddKeyEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveKeyEventMonitor() {
        memScoped {
            val rc = OH_Input_RemoveKeyEventMonitor(null)
            assertNotNull(rc)
            logLine("OH_Input_RemoveKeyEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_AddMouseEventMonitor() {
        memScoped {
            val rc = OH_Input_AddMouseEventMonitor(null)
            assertNotNull(rc)
            logLine("OH_Input_AddMouseEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveMouseEventMonitor() {
        memScoped {
            val rc = OH_Input_RemoveMouseEventMonitor(null)
            assertNotNull(rc)
            logLine("OH_Input_RemoveMouseEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_AddTouchEventMonitor() {
        memScoped {
            val rc = OH_Input_AddTouchEventMonitor(null)
            assertNotNull(rc)
            logLine("OH_Input_AddTouchEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveTouchEventMonitor() {
        memScoped {
            val rc = OH_Input_RemoveTouchEventMonitor(null)
            assertNotNull(rc)
            logLine("OH_Input_RemoveTouchEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_AddAxisEventMonitorForAll() {
        memScoped {
            val rc = OH_Input_AddAxisEventMonitorForAll(null)
            assertNotNull(rc)
            logLine("OH_Input_AddAxisEventMonitorForAll=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveAxisEventMonitorForAll() {
        memScoped {
            val rc = OH_Input_RemoveAxisEventMonitorForAll(null)
            assertNotNull(rc)
            logLine("OH_Input_RemoveAxisEventMonitorForAll=$rc")
        }
    }

    @Test
    fun testOH_Input_AddAxisEventMonitor() {
        memScoped {
            val rc = OH_Input_AddAxisEventMonitor(AXIS_EVENT_TYPE_SCROLL, null)
            assertNotNull(rc)
            logLine("OH_Input_AddAxisEventMonitor=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveAxisEventMonitor() {
        memScoped {
            val rc = OH_Input_RemoveAxisEventMonitor(AXIS_EVENT_TYPE_SCROLL, null)
            assertNotNull(rc)
            logLine("OH_Input_RemoveAxisEventMonitor=$rc")
        }
    }

    // ==================== EventInterceptors ====================

    @Test
    fun testOH_Input_AddKeyEventInterceptor() {
        memScoped {
            val rc = OH_Input_AddKeyEventInterceptor(null, null)
            assertNotNull(rc)
            logLine("OH_Input_AddKeyEventInterceptor=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveKeyEventInterceptor() {
        memScoped {
            val rc = OH_Input_RemoveKeyEventInterceptor()
            assertNotNull(rc)
            logLine("OH_Input_RemoveKeyEventInterceptor=$rc")
        }
    }

    @Test
    fun testOH_Input_AddInputEventInterceptor() {
        memScoped {
            val rc = OH_Input_AddInputEventInterceptor(null, null)
            assertNotNull(rc)
            logLine("OH_Input_AddInputEventInterceptor=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveInputEventInterceptor() {
        memScoped {
            val rc = OH_Input_RemoveInputEventInterceptor()
            assertNotNull(rc)
            logLine("OH_Input_RemoveInputEventInterceptor=$rc")
        }
    }

    // ==================== KeyEvent Hook ====================

    @Test
    fun testOH_Input_AddKeyEventHook() {
        memScoped {
            val rc = try { OH_Input_AddKeyEventHook(null) } catch (e: Throwable) { logLine("OH_Input_AddKeyEventHook (API 21) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_AddKeyEventHook=$rc")
        }
    }

    @Test
    fun testOH_Input_RemoveKeyEventHook() {
        memScoped {
            val rc = try { OH_Input_RemoveKeyEventHook(null) } catch (e: Throwable) { logLine("OH_Input_RemoveKeyEventHook (API 21) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_RemoveKeyEventHook=$rc")
        }
    }

    @Test
    fun testOH_Input_DispatchToNextHandler() {
        memScoped {
            val rc = try { OH_Input_DispatchToNextHandler(0) } catch (e: Throwable) { logLine("OH_Input_DispatchToNextHandler (API 21) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_DispatchToNextHandler=$rc")
        }
    }

    // ==================== CustomCursor / CursorConfig / CursorInfo (API 22) ====================

    @Test
    fun testOH_Input_CustomCursor_Create() {
        memScoped {
            val cc = try { OH_Input_CustomCursor_Create(null, 0, 0) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_Create (API 22) exception: $e"); null }
            logLine("OH_Input_CustomCursor_Create=$cc")
            if (cc != null) {
                val cptr = alloc<CPointerVar<Input_CustomCursor>>()
                cptr.value = cc
                try { OH_Input_CustomCursor_Destroy(cptr.ptr) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_Destroy (API 22) exception: $e") }
            }
        }
    }

    @Test
    fun testOH_Input_CustomCursor_GetPixelMap() {
        memScoped {
            val cc = try { OH_Input_CustomCursor_Create(null, 0, 0) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_Create (API 22) exception: $e"); null }
            val pmVar = alloc<CPointerVar<OH_PixelmapNative>>()
            val rc = try { OH_Input_CustomCursor_GetPixelMap(cc, pmVar.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_GetPixelMap (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (cc != null) {
                val cptr = alloc<CPointerVar<Input_CustomCursor>>()
                cptr.value = cc
                try { OH_Input_CustomCursor_Destroy(cptr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CustomCursor_GetAnchor() {
        memScoped {
            val cc = try { OH_Input_CustomCursor_Create(null, 0, 0) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_Create (API 22) exception: $e"); null }
            val axVar = alloc<IntVar>()
            val ayVar = alloc<IntVar>()
            val rc = try { OH_Input_CustomCursor_GetAnchor(cc, axVar.ptr, ayVar.ptr) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_GetAnchor (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (cc != null) {
                val cptr = alloc<CPointerVar<Input_CustomCursor>>()
                cptr.value = cc
                try { OH_Input_CustomCursor_Destroy(cptr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CustomCursor_Destroy() {
        memScoped {
            val cc = try { OH_Input_CustomCursor_Create(null, 0, 0) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_Create (API 22) exception: $e"); null }
            if (cc != null) {
                val cptr = alloc<CPointerVar<Input_CustomCursor>>()
                cptr.value = cc
                try { OH_Input_CustomCursor_Destroy(cptr.ptr) } catch (e: Throwable) { logLine("OH_Input_CustomCursor_Destroy (API 22) exception: $e") }
                logLine("OH_Input_CustomCursor_Destroy=called")
            }
        }
    }

    @Test
    fun testOH_Input_CursorConfig_Create() {
        memScoped {
            val cfg = try { OH_Input_CursorConfig_Create(false) } catch (e: Throwable) { logLine("OH_Input_CursorConfig_Create (API 22) exception: $e"); null }
            logLine("OH_Input_CursorConfig_Create=$cfg")
            if (cfg != null) {
                val cfgPtr = alloc<CPointerVar<Input_CursorConfig>>()
                cfgPtr.value = cfg
                try { OH_Input_CursorConfig_Destroy(cfgPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorConfig_IsFollowSystem() {
        memScoped {
            val cfg = try { OH_Input_CursorConfig_Create(false) } catch (e: Throwable) { logLine("OH_Input_CursorConfig_Create (API 22) exception: $e"); null }
            val fsVar = alloc<BooleanVar>()
            val rc = try { OH_Input_CursorConfig_IsFollowSystem(cfg, fsVar.ptr) } catch (e: Throwable) { logLine("OH_Input_CursorConfig_IsFollowSystem (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (cfg != null) {
                val cfgPtr = alloc<CPointerVar<Input_CursorConfig>>()
                cfgPtr.value = cfg
                try { OH_Input_CursorConfig_Destroy(cfgPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_SetCustomCursor() {
        memScoped {
            val cfg = try { OH_Input_CursorConfig_Create(false) } catch (e: Throwable) { logLine("OH_Input_CursorConfig_Create (API 22) exception: $e"); null }
            val rc = try { OH_Input_SetCustomCursor(0, null, cfg) } catch (e: Throwable) { logLine("OH_Input_SetCustomCursor (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (cfg != null) {
                val cfgPtr = alloc<CPointerVar<Input_CursorConfig>>()
                cfgPtr.value = cfg
                try { OH_Input_CursorConfig_Destroy(cfgPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorConfig_Destroy() {
        memScoped {
            val cfg = try { OH_Input_CursorConfig_Create(false) } catch (e: Throwable) { logLine("OH_Input_CursorConfig_Create (API 22) exception: $e"); null }
            if (cfg != null) {
                val cfgPtr = alloc<CPointerVar<Input_CursorConfig>>()
                cfgPtr.value = cfg
                try { OH_Input_CursorConfig_Destroy(cfgPtr.ptr) } catch (e: Throwable) { logLine("OH_Input_CursorConfig_Destroy (API 22) exception: $e") }
                logLine("OH_Input_CursorConfig_Destroy=called")
            }
        }
    }

    @Test
    fun testOH_Input_CursorInfo_Create() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            logLine("OH_Input_CursorInfo_Create=$ci")
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorInfo_IsVisible() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            val visVar = alloc<BooleanVar>()
            val rc = try { OH_Input_CursorInfo_IsVisible(ci, visVar.ptr) } catch (e: Throwable) { logLine("OH_Input_CursorInfo_IsVisible (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorInfo_GetStyle() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            val styleVar = alloc<IntVar>()
            val rc = try { OH_Input_CursorInfo_GetStyle(ci, styleVar.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_Input_CursorInfo_GetStyle (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorInfo_GetSizeLevel() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            val sizeVar = alloc<IntVar>()
            val rc = try { OH_Input_CursorInfo_GetSizeLevel(ci, sizeVar.ptr) } catch (e: Throwable) { logLine("OH_Input_CursorInfo_GetSizeLevel (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorInfo_GetColor() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            val colorVar = alloc<UIntVar>()
            val rc = try { OH_Input_CursorInfo_GetColor(ci, colorVar.ptr) } catch (e: Throwable) { logLine("OH_Input_CursorInfo_GetColor (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_GetCursorInfo() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            val rc = try { OH_Input_GetCursorInfo(ci, null) } catch (e: Throwable) { logLine("OH_Input_GetCursorInfo (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { }
            }
        }
    }

    @Test
    fun testOH_Input_CursorInfo_Destroy() {
        memScoped {
            val ci = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            if (ci != null) {
                val ciPtr = alloc<CPointerVar<Input_CursorInfo>>()
                ciPtr.value = ci
                try { OH_Input_CursorInfo_Destroy(ciPtr.ptr) } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Destroy (API 22) exception: $e") }
                logLine("OH_Input_CursorInfo_Destroy=called")
            }
        }
    }

    @Test
    fun testOH_Input_GetMouseEventCursorInfo() {
        memScoped {
            val me = OH_Input_CreateMouseEvent()
            assertNotNull(me)
            val ci2 = try { OH_Input_CursorInfo_Create() } catch (e: Throwable) { logLine("OH_Input_CursorInfo_Create (API 22) exception: $e"); null }
            val rc = try { OH_Input_GetMouseEventCursorInfo(me, ci2) } catch (e: Throwable) { logLine("OH_Input_GetMouseEventCursorInfo (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            if (ci2 != null) {
                val ci2Ptr = alloc<CPointerVar<Input_CursorInfo>>()
                ci2Ptr.value = ci2
                try { OH_Input_CursorInfo_Destroy(ci2Ptr.ptr) } catch (e: Throwable) { }
            }
            val mePtr = alloc<CPointerVar<Input_MouseEvent>>()
            mePtr.value = me
            OH_Input_DestroyMouseEvent(mePtr.ptr)
        }
    }

    // ==================== Hotkey ====================

    @Test
    fun testOH_Input_CreateHotkey() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            logLine("OH_Input_CreateHotkey=$hk")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetPreKeys() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            val preKeys = allocArray<IntVar>(2)
            preKeys[0] = 2047
            preKeys[1] = 2017
            OH_Input_SetPreKeys(hk, preKeys, 2)
            logLine("OH_Input_SetPreKeys=called")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetPreKeys() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            val preKeys = allocArray<IntVar>(2)
            preKeys[0] = 2047
            preKeys[1] = 2017
            OH_Input_SetPreKeys(hk, preKeys, 2)
            val pkOut = alloc<CPointerVar<IntVar>>()
            val pkCnt = alloc<IntVar>()
            val rc = OH_Input_GetPreKeys(hk, pkOut.ptr, pkCnt.ptr)
            logLine("OH_Input_GetPreKeys=$rc")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetFinalKey() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            OH_Input_SetFinalKey(hk, 2017)
            logLine("OH_Input_SetFinalKey=called")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetFinalKey() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            OH_Input_SetFinalKey(hk, 2017)
            val fkVar = alloc<IntVar>()
            val rc = OH_Input_GetFinalKey(hk, fkVar.ptr)
            logLine("OH_Input_GetFinalKey=$rc")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_SetRepeat() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            OH_Input_SetRepeat(hk, false)
            logLine("OH_Input_SetRepeat=called")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetRepeat() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            OH_Input_SetRepeat(hk, false)
            val repVar = alloc<BooleanVar>()
            val rc = OH_Input_GetRepeat(hk, repVar.ptr)
            logLine("OH_Input_GetRepeat=$rc")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_CreateAllSystemHotkeys() {
        memScoped {
            val sysHk = OH_Input_CreateAllSystemHotkeys(0)
            logLine("OH_Input_CreateAllSystemHotkeys=$sysHk")
            OH_Input_DestroyAllSystemHotkeys(sysHk, 0)
        }
    }

    @Test
    fun testOH_Input_GetAllSystemHotkeys() {
        memScoped {
            val sysHk = OH_Input_CreateAllSystemHotkeys(0)
            val hkArr = alloc<CPointerVar<Input_Hotkey>>()
            val cntVar = alloc<IntVar>()
            val rc = OH_Input_GetAllSystemHotkeys(hkArr.ptr, cntVar.ptr)
            logLine("OH_Input_GetAllSystemHotkeys=$rc")
            OH_Input_DestroyAllSystemHotkeys(sysHk, 0)
        }
    }

    @Test
    fun testOH_Input_DestroyAllSystemHotkeys() {
        memScoped {
            val sysHk = OH_Input_CreateAllSystemHotkeys(0)
            OH_Input_DestroyAllSystemHotkeys(sysHk, 0)
            logLine("OH_Input_DestroyAllSystemHotkeys=called")
        }
    }

    @Test
    fun testOH_Input_AddHotkeyMonitor() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            val rc = OH_Input_AddHotkeyMonitor(hk, null)
            assertNotNull(rc)
            logLine("OH_Input_AddHotkeyMonitor=$rc")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_RemoveHotkeyMonitor() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            val rc = OH_Input_RemoveHotkeyMonitor(hk, null)
            logLine("OH_Input_RemoveHotkeyMonitor=$rc")
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
        }
    }

    @Test
    fun testOH_Input_DestroyHotkey() {
        memScoped {
            val hk = OH_Input_CreateHotkey()
            val ptr = alloc<CPointerVar<Input_Hotkey>>()
            ptr.value = hk
            OH_Input_DestroyHotkey(ptr.ptr)
            logLine("OH_Input_DestroyHotkey=called")
        }
    }

    // ==================== Device ====================

    @Test
    fun testOH_Input_GetDeviceIds() {
        memScoped {
            val ids = allocArray<IntVar>(10)
            val outSz = alloc<IntVar>()
            val rc = OH_Input_GetDeviceIds(ids, 10, outSz.ptr)
            logLine("OH_Input_GetDeviceIds=$rc")
        }
    }

    @Test
    fun testOH_Input_GetDevice() {
        memScoped {
            val devPtr = alloc<CPointerVar<Input_DeviceInfo>>()
            val rc = OH_Input_GetDevice(0, devPtr.ptr)
            logLine("OH_Input_GetDevice=$rc")
        }
    }

    @Test
    fun testOH_Input_CreateDeviceInfo() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            logLine("OH_Input_CreateDeviceInfo=$dev")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetDeviceId() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            val idVar = alloc<IntVar>()
            val rc = OH_Input_GetDeviceId(dev, idVar.ptr)
            logLine("OH_Input_GetDeviceId=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetDeviceName() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            val namePtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_Input_GetDeviceName(dev, namePtr.ptr)
            logLine("OH_Input_GetDeviceName=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetCapabilities() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            val capVar = alloc<IntVar>()
            val rc = OH_Input_GetCapabilities(dev, capVar.ptr)
            logLine("OH_Input_GetCapabilities=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetDeviceVersion() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            val verVar = alloc<IntVar>()
            val rc = OH_Input_GetDeviceVersion(dev, verVar.ptr)
            logLine("OH_Input_GetDeviceVersion=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetDeviceProduct() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            val prodVar = alloc<IntVar>()
            val rc = OH_Input_GetDeviceProduct(dev, prodVar.ptr)
            logLine("OH_Input_GetDeviceProduct=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetDeviceVendor() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()       
            val vendVar = alloc<IntVar>()
            val rc = OH_Input_GetDeviceVendor(dev, vendVar.ptr)
            logLine("OH_Input_GetDeviceVendor=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetDeviceAddress() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            val addrPtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_Input_GetDeviceAddress(dev, addrPtr.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetDeviceAddress=$rc")
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
        }
    }

    @Test
    fun testOH_Input_GetKeyboardType() {
        memScoped {
            val ktVar = alloc<IntVar>()
            val rc = OH_Input_GetKeyboardType(0, ktVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetKeyboardType=$rc")
        }
    }

    @Test
    fun testOH_Input_RegisterDeviceListener() {
        memScoped {
            val listener = alloc<Input_DeviceListener>().apply {
                deviceAddedCallback = null
                deviceRemovedCallback = null
            }
            val rc = OH_Input_RegisterDeviceListener(listener.ptr)
            assertNotNull(rc)
            logLine("OH_Input_RegisterDeviceListener=$rc")
        }
    }

    @Test
    fun testOH_Input_UnregisterDeviceListener() {
        memScoped {
            val listener = alloc<Input_DeviceListener>().apply {
                deviceAddedCallback = null
                deviceRemovedCallback = null
            }
            val rc = OH_Input_UnregisterDeviceListener(listener.ptr)
            assertNotNull(rc)
            logLine("OH_Input_UnregisterDeviceListener=$rc")
        }
    }

    @Test
    fun testOH_Input_UnregisterDeviceListeners() {
        memScoped {
            val rc = OH_Input_UnregisterDeviceListeners()
            assertNotNull(rc)
            logLine("OH_Input_UnregisterDeviceListeners=$rc")
        }
    }

    @Test
    fun testOH_Input_DestroyDeviceInfo() {
        memScoped {
            val dev = OH_Input_CreateDeviceInfo()
            assertNotNull(dev)
            val dptr = alloc<CPointerVar<Input_DeviceInfo>>()
            dptr.value = dev
            OH_Input_DestroyDeviceInfo(dptr.ptr)
            logLine("OH_Input_DestroyDeviceInfo=called")
        }
    }

    // ==================== Other ====================

    @Test
    fun testOH_Input_GetIntervalSinceLastInput() {
        memScoped {
            val tiVar = alloc<LongVar>()
            val rc = OH_Input_GetIntervalSinceLastInput(tiVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetIntervalSinceLastInput=$rc")
        }
    }

    @Test
    fun testOH_Input_GetFunctionKeyState() {
        memScoped {
            val stVar = alloc<IntVar>()
            val rc = OH_Input_GetFunctionKeyState(2074, stVar.ptr)
            assertNotNull(rc)
            logLine("OH_Input_GetFunctionKeyState=$rc")
        }
    }

    @Test
    fun testOH_Input_QueryMaxTouchPoints() {
        memScoped {
            val cntVar = alloc<IntVar>()
            val rc = try { OH_Input_QueryMaxTouchPoints(cntVar.ptr) } catch (e: Throwable) { logLine("OH_Input_QueryMaxTouchPoints (API 20) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_QueryMaxTouchPoints=$rc")
        }
    }

    @Test
    fun testOH_Input_GetPointerLocation() {
        memScoped {
            val didVar = alloc<IntVar>()
            val dxVar = alloc<DoubleVar>()
            val dyVar = alloc<DoubleVar>()
            val rc = try { OH_Input_GetPointerLocation(didVar.ptr, dxVar.ptr, dyVar.ptr) } catch (e: Throwable) { logLine("OH_Input_GetPointerLocation (API 20) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_GetPointerLocation=$rc")
        }
    }

    @Test
    fun testOH_Input_SetPointerVisible() {
        memScoped {
            val rc = try { OH_Input_SetPointerVisible(true) } catch (e: Throwable) { logLine("OH_Input_SetPointerVisible (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_SetPointerVisible=$rc")
        }
    }

    @Test
    fun testOH_Input_GetPointerStyle() {
        memScoped {
            val psVar = alloc<IntVar>()
            val rc = try { OH_Input_GetPointerStyle(0, psVar.ptr) } catch (e: Throwable) { logLine("OH_Input_GetPointerStyle (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_GetPointerStyle=$rc")
        }
    }

    @Test
    fun testOH_Input_SetPointerStyle() {
        memScoped {
            val rc = try { OH_Input_SetPointerStyle(0, 0) } catch (e: Throwable) { logLine("OH_Input_SetPointerStyle (API 22) exception: $e"); INPUT_PARAMETER_ERROR }
            assertNotNull(rc)
            logLine("OH_Input_SetPointerStyle=$rc")
        }
    }
}
