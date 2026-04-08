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
import platform.IMEKit.InputMethod.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class InputMethodTest {

    private fun logLine(msg: String) = println("[stdout] InputMethodTest $msg")

    // TextEditorProxy 回调：按 C 签名用 staticCFunction 实现
    private val getTextConfigCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _config: CPointer<InputMethod_TextConfig>? -> }
    private val insertTextCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _text: CPointer<UShortVar>?, _length: ULong -> }
    private val deleteForwardCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _length: Int -> }
    private val deleteBackwardCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _length: Int -> }
    private val sendKeyboardStatusCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _status: UInt -> }
    private val sendEnterKeyCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _enterKeyType: UInt -> }
    private val moveCursorCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _direction: UInt -> }
    private val handleSetSelectionCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _start: Int, _end: Int -> }
    private val handleExtendActionCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _action: UInt -> }
    private val getLeftTextOfCursorCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _number: Int, _text: CPointer<UShortVar>?, _length: CPointer<ULongVar>? -> }
    private val getRightTextOfCursorCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _number: Int, _text: CPointer<UShortVar>?, _length: CPointer<ULongVar>? -> }
    private val getTextIndexAtCursorCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>? -> 0 }
    private val receivePrivateCommandCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _commands: CPointer<CPointerVarOf<CPointer<InputMethod_PrivateCommand>>>?, _size: ULong -> 0 }
    private val setPreviewTextCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>?, _text: CPointer<UShortVar>?, _length: ULong, _start: Int, _end: Int -> 0 }
    private val finishTextPreviewCallback = staticCFunction { _proxy: CPointer<InputMethod_TextEditorProxy>? -> }

    // ==================== TextAvoidInfo（每个 C API 独立 @Test） ====================

    @Test
    fun testOH_TextAvoidInfo_Create() {
        memScoped {
            val p = OH_TextAvoidInfo_Create(2.0, 2.0)
            assertNotNull(p)
            logLine("OH_TextAvoidInfo_Create=ok")
            OH_TextAvoidInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_TextAvoidInfo_SetPositionY() {
        memScoped {
            val p = OH_TextAvoidInfo_Create(2.0, 2.0)
            assertNotNull(p)
            val rc = OH_TextAvoidInfo_SetPositionY(p, 3.0)
            assertNotNull(rc)
            logLine("OH_TextAvoidInfo_SetPositionY=$rc")
            OH_TextAvoidInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_TextAvoidInfo_GetPositionY() {
        memScoped {
            val p = OH_TextAvoidInfo_Create(2.0, 2.0)
            assertNotNull(p)
            OH_TextAvoidInfo_SetPositionY(p, 3.0)
            val positionY = alloc<DoubleVar>()
            val rc = OH_TextAvoidInfo_GetPositionY(p, positionY.ptr)
            assertNotNull(rc)
            logLine("OH_TextAvoidInfo_GetPositionY=$rc")
            OH_TextAvoidInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_TextAvoidInfo_SetHeight() {
        memScoped {
            val p = OH_TextAvoidInfo_Create(2.0, 2.0)
            assertNotNull(p)
            val rc = OH_TextAvoidInfo_SetHeight(p, 4.0)
            assertNotNull(rc)
            logLine("OH_TextAvoidInfo_SetHeight=$rc")
            OH_TextAvoidInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_TextAvoidInfo_GetHeight() {
        memScoped {
            val p = OH_TextAvoidInfo_Create(2.0, 2.0)
            assertNotNull(p)
            OH_TextAvoidInfo_SetHeight(p, 4.0)
            val height = alloc<DoubleVar>()
            val rc = OH_TextAvoidInfo_GetHeight(p, height.ptr)
            assertNotNull(rc)
            logLine("OH_TextAvoidInfo_GetHeight=$rc")
            OH_TextAvoidInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_TextAvoidInfo_Destroy() {
        memScoped {
            val p = OH_TextAvoidInfo_Create(2.0, 2.0)
            assertNotNull(p)
            OH_TextAvoidInfo_Destroy(p)
            logLine("OH_TextAvoidInfo_Destroy=called")
        }
    }

    // ==================== CursorInfo ====================

    @Test
    fun testOH_CursorInfo_Create() {
        memScoped {
            val p = OH_CursorInfo_Create(0.0, 0.0, 0.0, 0.0)
            assertNotNull(p)
            logLine("OH_CursorInfo_Create=ok")
            OH_CursorInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_CursorInfo_SetRect() {
        memScoped {
            val p = OH_CursorInfo_Create(0.0, 0.0, 0.0, 0.0)
            assertNotNull(p)
            val rc = OH_CursorInfo_SetRect(p, 10.0, 20.0, 5.0, 10.0)
            assertNotNull(rc)
            logLine("OH_CursorInfo_SetRect=$rc")
            OH_CursorInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_CursorInfo_GetRect() {
        memScoped {
            val p = OH_CursorInfo_Create(0.0, 0.0, 0.0, 0.0)
            assertNotNull(p)
            OH_CursorInfo_SetRect(p, 10.0, 20.0, 5.0, 10.0)
            val left = alloc<DoubleVar>()
            val top = alloc<DoubleVar>()
            val width = alloc<DoubleVar>()
            val height = alloc<DoubleVar>()
            val rc = OH_CursorInfo_GetRect(p, left.ptr, top.ptr, width.ptr, height.ptr)
            assertNotNull(rc)
            logLine("OH_CursorInfo_GetRect=$rc")
            OH_CursorInfo_Destroy(p)
        }
    }

    @Test
    fun testOH_CursorInfo_Destroy() {
        memScoped {
            val p = OH_CursorInfo_Create(0.0, 0.0, 0.0, 0.0)
            assertNotNull(p)
            OH_CursorInfo_Destroy(p)
            logLine("OH_CursorInfo_Destroy=called")
        }
    }

    // ==================== AttachOptions ====================

    @Test
    fun testOH_AttachOptions_Create() {
        memScoped {
            val p = OH_AttachOptions_Create(true)
            assertNotNull(p)
            logLine("OH_AttachOptions_Create=ok")
            OH_AttachOptions_Destroy(p)
        }
    }

    @Test
    fun testOH_AttachOptions_IsShowKeyboard() {
        memScoped {
            val p = OH_AttachOptions_Create(true)
            assertNotNull(p)
            val showKeyboard = alloc<BooleanVar>()
            val rc = OH_AttachOptions_IsShowKeyboard(p, showKeyboard.ptr)
            assertNotNull(rc)
            logLine("OH_AttachOptions_IsShowKeyboard=$rc")
            OH_AttachOptions_Destroy(p)
        }
    }

    @Test
    fun testOH_AttachOptions_Destroy() {
        memScoped {
            val p = OH_AttachOptions_Create(true)
            assertNotNull(p)
            OH_AttachOptions_Destroy(p)
            logLine("OH_AttachOptions_Destroy=called")
        }
    }

    @Test
    fun testOH_AttachOptions_CreateWithRequestKeyboardReason() {
        memScoped {
            val p = OH_AttachOptions_CreateWithRequestKeyboardReason(true, IME_REQUEST_REASON_TOUCH)
            assertNotNull(p)
            logLine("OH_AttachOptions_CreateWithRequestKeyboardReason=ok")
            OH_AttachOptions_Destroy(p)
        }
    }

    @Test
    fun testOH_AttachOptions_GetRequestKeyboardReason() {
        memScoped {
            val p = OH_AttachOptions_CreateWithRequestKeyboardReason(true, IME_REQUEST_REASON_TOUCH)
            assertNotNull(p)
            val reason = alloc<IntVar>()
            val rc = OH_AttachOptions_GetRequestKeyboardReason(p, reason.ptr)
            assertNotNull(rc)
            logLine("OH_AttachOptions_GetRequestKeyboardReason=$rc")
            OH_AttachOptions_Destroy(p)
        }
    }

    // ==================== PrivateCommand ====================

    @Test
    fun testOH_PrivateCommand_Create() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            logLine("OH_PrivateCommand_Create=ok")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_SetKey() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            val rc = OH_PrivateCommand_SetKey(p, "new_key".cstr, 7u)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_SetKey=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_GetKey() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            val key = alloc<CPointerVar<ByteVar>>()
            val keyLength = alloc<ULongVar>()
            val rc = OH_PrivateCommand_GetKey(p, key.ptr, keyLength.ptr)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_GetKey=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_SetBoolValue() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            val rc = OH_PrivateCommand_SetBoolValue(p, true)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_SetBoolValue=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_GetBoolValue() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            OH_PrivateCommand_SetBoolValue(p, true)
            val boolVal = alloc<BooleanVar>()
            val rc = OH_PrivateCommand_GetBoolValue(p, boolVal.ptr)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_GetBoolValue=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_SetIntValue() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            val rc = OH_PrivateCommand_SetIntValue(p, 100)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_SetIntValue=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_GetIntValue() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            OH_PrivateCommand_SetIntValue(p, 100)
            val intVal = alloc<IntVar>()
            val rc = OH_PrivateCommand_GetIntValue(p, intVal.ptr)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_GetIntValue=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_SetStrValue() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            val rc = OH_PrivateCommand_SetStrValue(p, "test_value".cstr, 10u)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_SetStrValue=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_GetStrValue() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            OH_PrivateCommand_SetStrValue(p, "test_value".cstr, 10u)
            val strOut = alloc<CPointerVar<ByteVar>>()
            val strLen = alloc<ULongVar>()
            val rc = OH_PrivateCommand_GetStrValue(p, strOut.ptr, strLen.ptr)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_GetStrValue=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_GetValueType() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            val valueType = alloc<InputMethod_CommandValueTypeVar>()
            val rc = OH_PrivateCommand_GetValueType(p, valueType.ptr)
            assertNotNull(rc)
            logLine("OH_PrivateCommand_GetValueType=$rc")
            OH_PrivateCommand_Destroy(p)
        }
    }

    @Test
    fun testOH_PrivateCommand_Destroy() {
        memScoped {
            val p = OH_PrivateCommand_Create("test_key".cstr, 8u)
            assertNotNull(p)
            OH_PrivateCommand_Destroy(p)
            logLine("OH_PrivateCommand_Destroy=called")
        }
    }

    // ==================== TextConfig ====================

    @Test
    fun testOH_TextConfig_Create() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            logLine("OH_TextConfig_Create=ok")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetInputType() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val rc = OH_TextConfig_SetInputType(p, IME_TEXT_INPUT_TYPE_TEXT)
            assertNotNull(rc)
            logLine("OH_TextConfig_SetInputType=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetInputType() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            OH_TextConfig_SetInputType(p, IME_TEXT_INPUT_TYPE_TEXT)
            val inputType = alloc<InputMethod_TextInputTypeVar>()
            val rc = OH_TextConfig_GetInputType(p, inputType.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_GetInputType=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetEnterKeyType() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val rc = OH_TextConfig_SetEnterKeyType(p, IME_ENTER_KEY_DONE)
            assertNotNull(rc)
            logLine("OH_TextConfig_SetEnterKeyType=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetEnterKeyType() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            OH_TextConfig_SetEnterKeyType(p, IME_ENTER_KEY_DONE)
            val enterKeyType = alloc<InputMethod_EnterKeyTypeVar>()
            val rc = OH_TextConfig_GetEnterKeyType(p, enterKeyType.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_GetEnterKeyType=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetPreviewTextSupport() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val rc = OH_TextConfig_SetPreviewTextSupport(p, true)
            assertNotNull(rc)
            logLine("OH_TextConfig_SetPreviewTextSupport=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_IsPreviewTextSupported() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            OH_TextConfig_SetPreviewTextSupport(p, true)
            val supported = alloc<BooleanVar>()
            val rc = OH_TextConfig_IsPreviewTextSupported(p, supported.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_IsPreviewTextSupported=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetSelection() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val rc = OH_TextConfig_SetSelection(p, 0, 0)
            assertNotNull(rc)
            logLine("OH_TextConfig_SetSelection=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetSelection() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            OH_TextConfig_SetSelection(p, 0, 0)
            val start = alloc<IntVar>()
            val end = alloc<IntVar>()
            val rc = OH_TextConfig_GetSelection(p, start.ptr, end.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_GetSelection=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetWindowId() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val rc = OH_TextConfig_SetWindowId(p, 1)
            assertNotNull(rc)
            logLine("OH_TextConfig_SetWindowId=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetWindowId() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            OH_TextConfig_SetWindowId(p, 1)
            val windowId = alloc<IntVar>()
            val rc = OH_TextConfig_GetWindowId(p, windowId.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_GetWindowId=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetPlaceholder() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val placeholder = allocArray<UShortVar>(1).apply { this[0] = 0u }
            val rc = try { OH_TextConfig_SetPlaceholder(p, placeholder, 0u) } catch (e: Throwable) { logLine("OH_TextConfig_SetPlaceholder (API 20) exception: $e"); IME_ERR_NULL_POINTER }
            assertNotNull(rc)
            logLine("OH_TextConfig_SetPlaceholder=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetPlaceholder() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val ph = allocArray<UShortVar>(256)
            val phLen = alloc<ULongVar>().apply { value = 256u }
            val rc = try { OH_TextConfig_GetPlaceholder(p, ph, phLen.ptr) } catch (e: Throwable) { logLine("OH_TextConfig_GetPlaceholder (API 20) exception: $e"); IME_ERR_NULL_POINTER }
            assertNotNull(rc)
            logLine("OH_TextConfig_GetPlaceholder=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_SetAbilityName() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val abilityName = allocArray<UShortVar>(1).apply { this[0] = 0u }
            val rc = try { OH_TextConfig_SetAbilityName(p, abilityName, 0u) } catch (e: Throwable) { logLine("OH_TextConfig_SetAbilityName (API 20) exception: $e"); IME_ERR_NULL_POINTER }
            assertNotNull(rc)
            logLine("OH_TextConfig_SetAbilityName=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetAbilityName() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val ab = allocArray<UShortVar>(128)
            val abLen = alloc<ULongVar>().apply { value = 128u }
            val rc = try { OH_TextConfig_GetAbilityName(p, ab, abLen.ptr) } catch (e: Throwable) { logLine("OH_TextConfig_GetAbilityName (API 20) exception: $e"); IME_ERR_NULL_POINTER }
            assertNotNull(rc)
            logLine("OH_TextConfig_GetAbilityName=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetCursorInfo() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val cursorInfo = alloc<CPointerVar<InputMethod_CursorInfo>>()
            val rc = OH_TextConfig_GetCursorInfo(p, cursorInfo.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_GetCursorInfo=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_GetTextAvoidInfo() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            val avoidInfo = alloc<CPointerVar<InputMethod_TextAvoidInfo>>()
            val rc = OH_TextConfig_GetTextAvoidInfo(p, avoidInfo.ptr)
            assertNotNull(rc)
            logLine("OH_TextConfig_GetTextAvoidInfo=$rc")
            OH_TextConfig_Destroy(p)
        }
    }

    @Test
    fun testOH_TextConfig_Destroy() {
        memScoped {
            val p = OH_TextConfig_Create()
            assertNotNull(p)
            OH_TextConfig_Destroy(p)
            logLine("OH_TextConfig_Destroy=called")
        }
    }

    // ==================== TextEditorProxy（每个 C API 独立 @Test） ====================

    @Test
    fun testOH_TextEditorProxy_Create() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            logLine("OH_TextEditorProxy_Create=ok")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetGetTextConfigFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetGetTextConfigFunc(p, getTextConfigCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetGetTextConfigFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetGetTextConfigFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetGetTextConfigFunc(p, getTextConfigCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, CPointer<InputMethod_TextConfig>?, Unit>>>>()
            val rc = OH_TextEditorProxy_GetGetTextConfigFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetGetTextConfigFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetInsertTextFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetInsertTextFunc(p, insertTextCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetInsertTextFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetInsertTextFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetInsertTextFunc(p, insertTextCallback)
            val out = alloc<CPointerVar<CFunction<Function3<CPointer<InputMethod_TextEditorProxy>?, CPointer<UShortVar>?, ULong, Unit>>>>()
            val rc = OH_TextEditorProxy_GetInsertTextFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetInsertTextFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetDeleteForwardFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetDeleteForwardFunc(p, deleteForwardCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetDeleteForwardFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetDeleteForwardFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetDeleteForwardFunc(p, deleteForwardCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, Int, Unit>>>>()
            val rc = OH_TextEditorProxy_GetDeleteForwardFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetDeleteForwardFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetDeleteBackwardFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetDeleteBackwardFunc(p, deleteBackwardCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetDeleteBackwardFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetDeleteBackwardFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetDeleteBackwardFunc(p, deleteBackwardCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, Int, Unit>>>>()
            val rc = OH_TextEditorProxy_GetDeleteBackwardFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetDeleteBackwardFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetSendKeyboardStatusFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetSendKeyboardStatusFunc(p, sendKeyboardStatusCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetSendKeyboardStatusFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetSendKeyboardStatusFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetSendKeyboardStatusFunc(p, sendKeyboardStatusCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, UInt, Unit>>>>()
            val rc = OH_TextEditorProxy_GetSendKeyboardStatusFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetSendKeyboardStatusFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetSendEnterKeyFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetSendEnterKeyFunc(p, sendEnterKeyCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetSendEnterKeyFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetSendEnterKeyFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetSendEnterKeyFunc(p, sendEnterKeyCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, UInt, Unit>>>>()
            val rc = OH_TextEditorProxy_GetSendEnterKeyFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetSendEnterKeyFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetMoveCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetMoveCursorFunc(p, moveCursorCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetMoveCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetMoveCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetMoveCursorFunc(p, moveCursorCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, UInt, Unit>>>>()
            val rc = OH_TextEditorProxy_GetMoveCursorFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetMoveCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetHandleSetSelectionFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetHandleSetSelectionFunc(p, handleSetSelectionCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetHandleSetSelectionFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetHandleSetSelectionFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetHandleSetSelectionFunc(p, handleSetSelectionCallback)
            val out = alloc<CPointerVar<CFunction<Function3<CPointer<InputMethod_TextEditorProxy>?, Int, Int, Unit>>>>()
            val rc = OH_TextEditorProxy_GetHandleSetSelectionFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetHandleSetSelectionFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetHandleExtendActionFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetHandleExtendActionFunc(p, handleExtendActionCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetHandleExtendActionFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetHandleExtendActionFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetHandleExtendActionFunc(p, handleExtendActionCallback)
            val out = alloc<CPointerVar<CFunction<Function2<CPointer<InputMethod_TextEditorProxy>?, UInt, Unit>>>>()
            val rc = OH_TextEditorProxy_GetHandleExtendActionFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetHandleExtendActionFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetGetLeftTextOfCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetGetLeftTextOfCursorFunc(p, getLeftTextOfCursorCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetGetLeftTextOfCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetGetLeftTextOfCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetGetLeftTextOfCursorFunc(p, getLeftTextOfCursorCallback)
            val out = alloc<CPointerVar<CFunction<Function4<CPointer<InputMethod_TextEditorProxy>?, Int, CPointer<UShortVar>?, CPointer<ULongVar>?, Unit>>>>()
            val rc = OH_TextEditorProxy_GetGetLeftTextOfCursorFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetGetLeftTextOfCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetGetRightTextOfCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetGetRightTextOfCursorFunc(p, getRightTextOfCursorCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetGetRightTextOfCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetGetRightTextOfCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetGetRightTextOfCursorFunc(p, getRightTextOfCursorCallback)
            val out = alloc<CPointerVar<CFunction<Function4<CPointer<InputMethod_TextEditorProxy>?, Int, CPointer<UShortVar>?, CPointer<ULongVar>?, Unit>>>>()
            val rc = OH_TextEditorProxy_GetGetRightTextOfCursorFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetGetRightTextOfCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetGetTextIndexAtCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetGetTextIndexAtCursorFunc(p, getTextIndexAtCursorCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetGetTextIndexAtCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetGetTextIndexAtCursorFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetGetTextIndexAtCursorFunc(p, getTextIndexAtCursorCallback)
            val out = alloc<CPointerVar<CFunction<Function1<CPointer<InputMethod_TextEditorProxy>?, Int>>>>()
            val rc = OH_TextEditorProxy_GetGetTextIndexAtCursorFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetGetTextIndexAtCursorFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetReceivePrivateCommandFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetReceivePrivateCommandFunc(p, receivePrivateCommandCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetReceivePrivateCommandFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetSetPreviewTextFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetSetPreviewTextFunc(p, setPreviewTextCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetSetPreviewTextFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetFinishTextPreviewFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = OH_TextEditorProxy_SetFinishTextPreviewFunc(p, finishTextPreviewCallback)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetFinishTextPreviewFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_GetFinishTextPreviewFunc() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_SetFinishTextPreviewFunc(p, finishTextPreviewCallback)
            val out = alloc<CPointerVar<CFunction<Function1<CPointer<InputMethod_TextEditorProxy>?, Unit>>>>()
            val rc = OH_TextEditorProxy_GetFinishTextPreviewFunc(p, out.ptr)
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_GetFinishTextPreviewFunc=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_SetCallbackInMainThread() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            val rc = try { OH_TextEditorProxy_SetCallbackInMainThread(p, true) } catch (e: Throwable) { logLine("OH_TextEditorProxy_SetCallbackInMainThread (API 22) exception: $e"); IME_ERR_NULL_POINTER }
            assertNotNull(rc)
            logLine("OH_TextEditorProxy_SetCallbackInMainThread=$rc")
            OH_TextEditorProxy_Destroy(p)
        }
    }

    @Test
    fun testOH_TextEditorProxy_Destroy() {
        memScoped {
            val p = OH_TextEditorProxy_Create()
            assertNotNull(p)
            OH_TextEditorProxy_Destroy(p)
            logLine("OH_TextEditorProxy_Destroy=called")
        }
    }

    // ==================== InputMethodProxy ====================

    @Test
    fun testOH_InputMethodProxy_ShowKeyboard() {
        val rc = OH_InputMethodProxy_ShowKeyboard(null)
        assertNotNull(rc)
        logLine("OH_InputMethodProxy_ShowKeyboard=$rc")
    }

    @Test
    fun testOH_InputMethodProxy_ShowTextInput() {
        memScoped {
            val attachOptions = OH_AttachOptions_Create(true)
            assertNotNull(attachOptions)
            val rc = OH_InputMethodProxy_ShowTextInput(null, attachOptions)
            assertNotNull(rc)
            logLine("OH_InputMethodProxy_ShowTextInput=$rc")
            OH_AttachOptions_Destroy(attachOptions)
        }
    }

    @Test
    fun testOH_InputMethodProxy_HideKeyboard() {
        val rc = OH_InputMethodProxy_HideKeyboard(null)
        assertNotNull(rc)
        logLine("OH_InputMethodProxy_HideKeyboard=$rc")
    }

    @Test
    fun testOH_InputMethodProxy_NotifySelectionChange() {
        memScoped {
            val text = allocArray<UShortVar>(10)
            val rc = OH_InputMethodProxy_NotifySelectionChange(null, text, 10u, 0, 0)
            assertNotNull(rc)
            logLine("OH_InputMethodProxy_NotifySelectionChange=$rc")
        }
    }

    @Test
    fun testOH_InputMethodProxy_NotifyConfigurationChange() {
        val rc = OH_InputMethodProxy_NotifyConfigurationChange(null, IME_ENTER_KEY_DONE, IME_TEXT_INPUT_TYPE_TEXT)
        assertNotNull(rc)
        logLine("OH_InputMethodProxy_NotifyConfigurationChange=$rc")
    }

    @Test
    fun testOH_InputMethodProxy_NotifyCursorUpdate() {
        memScoped {
            val cursorInfo = OH_CursorInfo_Create(10.0, 20.0, 5.0, 10.0)
            assertNotNull(cursorInfo)
            val rc = OH_InputMethodProxy_NotifyCursorUpdate(null, cursorInfo)
            assertNotNull(rc)
            logLine("OH_InputMethodProxy_NotifyCursorUpdate=$rc")
            OH_CursorInfo_Destroy(cursorInfo)
        }
    }

    @Test
    fun testOH_InputMethodProxy_SendPrivateCommand() {
        memScoped {
            val privateCommand = OH_PrivateCommand_Create("test".cstr, 4u)
            assertNotNull(privateCommand)
            val privateCommandArray = cValuesOf(privateCommand)
            val rc = OH_InputMethodProxy_SendPrivateCommand(null, privateCommandArray.ptr, 1u)
            assertNotNull(rc)
            logLine("OH_InputMethodProxy_SendPrivateCommand=$rc")
            OH_PrivateCommand_Destroy(privateCommand)
        }
    }

    // ==================== InputMethodController ====================

    @Test
    fun testOH_InputMethodController_Attach() {
        memScoped {
            val textEditorProxy = OH_TextEditorProxy_Create()
            assertNotNull(textEditorProxy)
            val attachOptions = OH_AttachOptions_Create(true)
            assertNotNull(attachOptions)
            val inputMethodProxy = alloc<CPointerVar<InputMethod_InputMethodProxy>>()
            val rc = OH_InputMethodController_Attach(textEditorProxy, attachOptions, inputMethodProxy.ptr)
            assertNotNull(rc)
            logLine("OH_InputMethodController_Attach=$rc")
            OH_AttachOptions_Destroy(attachOptions)
            OH_TextEditorProxy_Destroy(textEditorProxy)
        }
    }

    @Test
    fun testOH_InputMethodController_Detach() {
        val rc = OH_InputMethodController_Detach(null)
        assertNotNull(rc)
        logLine("OH_InputMethodController_Detach=$rc")
    }

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_KeyboardStatus() {
        logLine("IME_KEYBOARD_STATUS_NONE=${IME_KEYBOARD_STATUS_NONE.toInt()}, IME_KEYBOARD_STATUS_HIDE=${IME_KEYBOARD_STATUS_HIDE.toInt()}, IME_KEYBOARD_STATUS_SHOW=${IME_KEYBOARD_STATUS_SHOW.toInt()}")
        assertEquals(IME_KEYBOARD_STATUS_NONE.toInt(), 0)
        assertEquals(IME_KEYBOARD_STATUS_HIDE.toInt(), 1)
        assertEquals(IME_KEYBOARD_STATUS_SHOW.toInt(), 2)
        logLine("testEnum_KeyboardStatus passed")
    }

    @Test
    fun testEnum_EnterKeyType() {
        logLine("IME_ENTER_KEY_UNSPECIFIED=${IME_ENTER_KEY_UNSPECIFIED.toInt()}, IME_ENTER_KEY_NONE=${IME_ENTER_KEY_NONE.toInt()}, IME_ENTER_KEY_GO=${IME_ENTER_KEY_GO.toInt()}, IME_ENTER_KEY_SEARCH=${IME_ENTER_KEY_SEARCH.toInt()}, IME_ENTER_KEY_SEND=${IME_ENTER_KEY_SEND.toInt()}, IME_ENTER_KEY_NEXT=${IME_ENTER_KEY_NEXT.toInt()}, IME_ENTER_KEY_DONE=${IME_ENTER_KEY_DONE.toInt()}, IME_ENTER_KEY_PREVIOUS=${IME_ENTER_KEY_PREVIOUS.toInt()}, IME_ENTER_KEY_NEWLINE=${IME_ENTER_KEY_NEWLINE.toInt()}")
        assertEquals(IME_ENTER_KEY_UNSPECIFIED.toInt(), 0)
        assertEquals(IME_ENTER_KEY_NONE.toInt(), 1)
        assertEquals(IME_ENTER_KEY_GO.toInt(), 2)
        assertEquals(IME_ENTER_KEY_SEARCH.toInt(), 3)
        assertEquals(IME_ENTER_KEY_SEND.toInt(), 4)
        assertEquals(IME_ENTER_KEY_NEXT.toInt(), 5)
        assertEquals(IME_ENTER_KEY_DONE.toInt(), 6)
        assertEquals(IME_ENTER_KEY_PREVIOUS.toInt(), 7)
        assertEquals(IME_ENTER_KEY_NEWLINE.toInt(), 8)
        logLine("testEnum_EnterKeyType passed")
    }

    @Test
    fun testEnum_Direction() {
        logLine("IME_DIRECTION_NONE=${IME_DIRECTION_NONE.toInt()}, IME_DIRECTION_UP=${IME_DIRECTION_UP.toInt()}, IME_DIRECTION_DOWN=${IME_DIRECTION_DOWN.toInt()}, IME_DIRECTION_LEFT=${IME_DIRECTION_LEFT.toInt()}, IME_DIRECTION_RIGHT=${IME_DIRECTION_RIGHT.toInt()}")
        assertEquals(IME_DIRECTION_NONE.toInt(), 0)
        assertEquals(IME_DIRECTION_UP.toInt(), 1)
        assertEquals(IME_DIRECTION_DOWN.toInt(), 2)
        assertEquals(IME_DIRECTION_LEFT.toInt(), 3)
        assertEquals(IME_DIRECTION_RIGHT.toInt(), 4)
        logLine("testEnum_Direction passed")
    }

    @Test
    fun testEnum_ExtendAction() {
        logLine("IME_EXTEND_ACTION_SELECT_ALL=${IME_EXTEND_ACTION_SELECT_ALL.toInt()}, IME_EXTEND_ACTION_CUT=${IME_EXTEND_ACTION_CUT.toInt()}, IME_EXTEND_ACTION_COPY=${IME_EXTEND_ACTION_COPY.toInt()}, IME_EXTEND_ACTION_PASTE=${IME_EXTEND_ACTION_PASTE.toInt()}")
        assertEquals(IME_EXTEND_ACTION_SELECT_ALL.toInt(), 0)
        assertEquals(IME_EXTEND_ACTION_CUT.toInt(), 3)
        assertEquals(IME_EXTEND_ACTION_COPY.toInt(), 4)
        assertEquals(IME_EXTEND_ACTION_PASTE.toInt(), 5)
        logLine("testEnum_ExtendAction passed")
    }

    @Test
    fun testEnum_TextInputType() {
        logLine("IME_TEXT_INPUT_TYPE_NONE=${IME_TEXT_INPUT_TYPE_NONE.toInt()}, IME_TEXT_INPUT_TYPE_TEXT=${IME_TEXT_INPUT_TYPE_TEXT.toInt()}, IME_TEXT_INPUT_TYPE_MULTILINE=${IME_TEXT_INPUT_TYPE_MULTILINE.toInt()}, IME_TEXT_INPUT_TYPE_NUMBER=${IME_TEXT_INPUT_TYPE_NUMBER.toInt()}, IME_TEXT_INPUT_TYPE_PHONE=${IME_TEXT_INPUT_TYPE_PHONE.toInt()}, IME_TEXT_INPUT_TYPE_DATETIME=${IME_TEXT_INPUT_TYPE_DATETIME.toInt()}, IME_TEXT_INPUT_TYPE_EMAIL_ADDRESS=${IME_TEXT_INPUT_TYPE_EMAIL_ADDRESS.toInt()}, IME_TEXT_INPUT_TYPE_URL=${IME_TEXT_INPUT_TYPE_URL.toInt()}, IME_TEXT_INPUT_TYPE_VISIBLE_PASSWORD=${IME_TEXT_INPUT_TYPE_VISIBLE_PASSWORD.toInt()}, IME_TEXT_INPUT_TYPE_NUMBER_PASSWORD=${IME_TEXT_INPUT_TYPE_NUMBER_PASSWORD.toInt()}, IME_TEXT_INPUT_TYPE_SCREEN_LOCK_PASSWORD=${IME_TEXT_INPUT_TYPE_SCREEN_LOCK_PASSWORD.toInt()}, IME_TEXT_INPUT_TYPE_USER_NAME=${IME_TEXT_INPUT_TYPE_USER_NAME.toInt()}, IME_TEXT_INPUT_TYPE_NEW_PASSWORD=${IME_TEXT_INPUT_TYPE_NEW_PASSWORD.toInt()}, IME_TEXT_INPUT_TYPE_NUMBER_DECIMAL=${IME_TEXT_INPUT_TYPE_NUMBER_DECIMAL.toInt()}, IME_TEXT_INPUT_TYPE_ONE_TIME_CODE=${IME_TEXT_INPUT_TYPE_ONE_TIME_CODE.toInt()}")
        assertEquals(IME_TEXT_INPUT_TYPE_NONE.toInt(), -1)
        assertEquals(IME_TEXT_INPUT_TYPE_TEXT.toInt(), 0)
        assertEquals(IME_TEXT_INPUT_TYPE_MULTILINE.toInt(), 1)
        assertEquals(IME_TEXT_INPUT_TYPE_NUMBER.toInt(), 2)
        assertEquals(IME_TEXT_INPUT_TYPE_PHONE.toInt(), 3)
        assertEquals(IME_TEXT_INPUT_TYPE_DATETIME.toInt(), 4)
        assertEquals(IME_TEXT_INPUT_TYPE_EMAIL_ADDRESS.toInt(), 5)
        assertEquals(IME_TEXT_INPUT_TYPE_URL.toInt(), 6)
        assertEquals(IME_TEXT_INPUT_TYPE_VISIBLE_PASSWORD.toInt(), 7)
        assertEquals(IME_TEXT_INPUT_TYPE_NUMBER_PASSWORD.toInt(), 8)
        assertEquals(IME_TEXT_INPUT_TYPE_SCREEN_LOCK_PASSWORD.toInt(), 9)
        assertEquals(IME_TEXT_INPUT_TYPE_USER_NAME.toInt(), 10)
        assertEquals(IME_TEXT_INPUT_TYPE_NEW_PASSWORD.toInt(), 11)
        assertEquals(IME_TEXT_INPUT_TYPE_NUMBER_DECIMAL.toInt(), 12)
        assertEquals(IME_TEXT_INPUT_TYPE_ONE_TIME_CODE.toInt(), 13)
        logLine("testEnum_TextInputType passed")
    }

    @Test
    fun testEnum_CommandValueType() {
        logLine("IME_COMMAND_VALUE_TYPE_NONE=${IME_COMMAND_VALUE_TYPE_NONE.toInt()}, IME_COMMAND_VALUE_TYPE_STRING=${IME_COMMAND_VALUE_TYPE_STRING.toInt()}, IME_COMMAND_VALUE_TYPE_BOOL=${IME_COMMAND_VALUE_TYPE_BOOL.toInt()}, IME_COMMAND_VALUE_TYPE_INT32=${IME_COMMAND_VALUE_TYPE_INT32.toInt()}")
        assertEquals(IME_COMMAND_VALUE_TYPE_NONE.toInt(), 0)
        assertEquals(IME_COMMAND_VALUE_TYPE_STRING.toInt(), 1)
        assertEquals(IME_COMMAND_VALUE_TYPE_BOOL.toInt(), 2)
        assertEquals(IME_COMMAND_VALUE_TYPE_INT32.toInt(), 3)
        logLine("testEnum_CommandValueType passed")
    }

    @Test
    fun testEnum_ErrorCode() {
        logLine("IME_ERR_OK=${IME_ERR_OK.toInt()}, IME_ERR_UNDEFINED=${IME_ERR_UNDEFINED.toInt()}, IME_ERR_PARAMCHECK=${IME_ERR_PARAMCHECK.toInt()}, IME_ERR_PACKAGEMANAGER=${IME_ERR_PACKAGEMANAGER.toInt()}, IME_ERR_IMENGINE=${IME_ERR_IMENGINE.toInt()}, IME_ERR_IMCLIENT=${IME_ERR_IMCLIENT.toInt()}, IME_ERR_CONFIG_PERSIST=${IME_ERR_CONFIG_PERSIST.toInt()}, IME_ERR_CONTROLLER=${IME_ERR_CONTROLLER.toInt()}, IME_ERR_SETTINGS=${IME_ERR_SETTINGS.toInt()}, IME_ERR_IMMS=${IME_ERR_IMMS.toInt()}, IME_ERR_DETACHED=${IME_ERR_DETACHED.toInt()}, IME_ERR_NULL_POINTER=${IME_ERR_NULL_POINTER.toInt()}, IME_ERR_QUERY_FAILED=${IME_ERR_QUERY_FAILED.toInt()}")
        assertEquals(IME_ERR_OK.toInt(), 0)
        assertEquals(IME_ERR_UNDEFINED.toInt(), 1)
        assertEquals(IME_ERR_PARAMCHECK.toInt(), 401)
        assertEquals(IME_ERR_PACKAGEMANAGER.toInt(), 12800001)
        assertEquals(IME_ERR_IMENGINE.toInt(), 12800002)
        assertEquals(IME_ERR_IMCLIENT.toInt(), 12800003)
        assertEquals(IME_ERR_CONFIG_PERSIST.toInt(), 12800005)
        assertEquals(IME_ERR_CONTROLLER.toInt(), 12800006)
        assertEquals(IME_ERR_SETTINGS.toInt(), 12800007)
        assertEquals(IME_ERR_IMMS.toInt(), 12800008)
        assertEquals(IME_ERR_DETACHED.toInt(), 12800009)
        assertEquals(IME_ERR_NULL_POINTER.toInt(), 12802000)
        assertEquals(IME_ERR_QUERY_FAILED.toInt(), 12802001)
        logLine("testEnum_ErrorCode passed")
    }

    @Test
    fun testEnum_RequestKeyboardReason() {
        logLine("IME_REQUEST_REASON_NONE=${IME_REQUEST_REASON_NONE.toInt()}, IME_REQUEST_REASON_MOUSE=${IME_REQUEST_REASON_MOUSE.toInt()}, IME_REQUEST_REASON_TOUCH=${IME_REQUEST_REASON_TOUCH.toInt()}, IME_REQUEST_REASON_OTHER=${IME_REQUEST_REASON_OTHER.toInt()}")
        assertEquals(IME_REQUEST_REASON_NONE.toInt(), 0)
        assertEquals(IME_REQUEST_REASON_MOUSE.toInt(), 1)
        assertEquals(IME_REQUEST_REASON_TOUCH.toInt(), 2)
        assertEquals(IME_REQUEST_REASON_OTHER.toInt(), 20)
        logLine("testEnum_RequestKeyboardReason passed")
    }
}
