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
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lwifi_ndk
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_window
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnet_ssl
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnet_websocket
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohinputmethod
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lace_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lace_napi.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_window_manager
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.basic.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_basicTest {

    private fun logLine(msg: String) = println(msg)

    // ConnectivityKit/wifi/oh_wifi.h
    @Test
    fun testOh_wifi_h() {
        memScoped {
            val enabled = alloc<BooleanVar>()
            val rc = OH_Wifi_IsWifiEnabled(enabled.ptr)
            assertNotNull(rc)
            logLine("OH_Wifi_IsWifiEnabled()=$rc enabled=${enabled.value}")
        }
    }

    // window_manager/oh_display_info.h — 无常量外的函数，覆盖常量
    @Test
    fun testOh_display_info_h() {
        assertEquals(32, OH_DISPLAY_NAME_LENGTH)
        logLine("OH_DISPLAY_NAME_LENGTH=$OH_DISPLAY_NAME_LENGTH")
    }

    // window_manager/oh_window_event_filter.h
    @Test
    fun testOh_window_event_filter_h() {
        val rc = OH_NativeWindowManager_UnregisterKeyEventFilter(0)
        assertNotNull(rc)
        logLine("OH_NativeWindowManager_UnregisterKeyEventFilter(0)=$rc")
    }

    // network/netstack/net_ssl/net_ssl_c.h
    @Test
    fun testNet_ssl_c_h() {
        val rc = OH_NetStack_CertVerification(null, null)
        assertNotNull(rc)
        logLine("OH_NetStack_CertVerification(null,null)=$rc")
    }

    // network/netstack/net_websocket.h
    @Test
    fun testNet_websocket_h() {
        memScoped {
            val client = OH_WebSocketClient_Constructor(null, null, null, null)
            logLine("OH_WebSocketClient_Constructor(...)=$client")
            client?.let { OH_WebSocketClient_Destroy(it) }
        }
    }

    // inputmethod/inputmethod_private_command_capi.h
    @Test
    fun testInputmethod_private_command_capi_h() {
        OH_PrivateCommand_Destroy(null)
        logLine("OH_PrivateCommand_Destroy(null) ok")
    }

    // inputmethod/inputmethod_text_avoid_info_capi.h
    @Test
    fun testInputmethod_text_avoid_info_capi_h() {
        OH_TextAvoidInfo_Destroy(null)
        logLine("OH_TextAvoidInfo_Destroy(null) ok")
    }

    // inputmethod/inputmethod_text_editor_proxy_capi.h
    @Test
    fun testInputmethod_text_editor_proxy_capi_h() {
        OH_TextEditorProxy_Destroy(null)
        logLine("OH_TextEditorProxy_Destroy(null) ok")
    }

    // inputmethod/inputmethod_attach_options_capi.h
    @Test
    fun testInputmethod_attach_options_capi_h() {
        OH_AttachOptions_Destroy(null)
        logLine("OH_AttachOptions_Destroy(null) ok")
    }

    // inputmethod/inputmethod_cursor_info_capi.h
    @Test
    fun testInputmethod_cursor_info_capi_h() {
        memScoped {
            val info = OH_CursorInfo_Create(0.0, 0.0, 0.0, 0.0)
            logLine("OH_CursorInfo_Create(...)=$info")
            info?.let { OH_CursorInfo_Destroy(it) }
        }
    }

    // inputmethod/inputmethod_text_config_capi.h
    @Test
    fun testInputmethod_text_config_capi_h() {
        memScoped {
            val config = OH_TextConfig_Create()
            logLine("OH_TextConfig_Create()=$config")
            config?.let { OH_TextConfig_Destroy(it) }
        }
    }

    // inputmethod/inputmethod_inputmethod_proxy_capi.h
    @Test
    fun testInputmethod_inputmethod_proxy_capi_h() {
        val rc = OH_InputMethodProxy_ShowKeyboard(null)
        assertNotNull(rc)
        logLine("OH_InputMethodProxy_ShowKeyboard(null)=$rc")
    }

    // inputmethod/inputmethod_controller_capi.h
    @Test
    fun testInputmethod_controller_capi_h() {
        val rc = OH_InputMethodController_Detach(null)
        assertNotNull(rc)
        logLine("OH_InputMethodController_Detach(null)=$rc")
    }

    // arkui/native_dialog.h
    @Test
    fun testNative_dialog_h() {
        val reason = OH_ArkUI_DialogDismissEvent_GetDismissReason(null)
        logLine("OH_ArkUI_DialogDismissEvent_GetDismissReason(null)=$reason")
    }

    // arkui/drag_and_drop.h
    @Test
    fun testDrag_and_drop_h() {
        OH_ArkUI_DragPreviewOption_Dispose(null)
        logLine("OH_ArkUI_DragPreviewOption_Dispose(null) ok")
    }

    // arkui/ui_input_event.h
    @Test
    fun testUi_input_event_h() {
        val type = OH_ArkUI_UIInputEvent_GetType(null)
        logLine("OH_ArkUI_UIInputEvent_GetType(null)=$type")
    }

    // arkui/native_node.h — 覆盖枚举常量
    @Test
    fun testNative_node_h() {
        logLine("ARKUI_NODE_CUSTOM=$ARKUI_NODE_CUSTOM")
    }

    // arkui/native_animate.h
    @Test
    fun testNative_animate_h() {
        val opt = OH_ArkUI_AnimateOption_Create()
        logLine("OH_ArkUI_AnimateOption_Create()=$opt")
    }

    // arkui/native_interface_accessibility.h
    @Test
    fun testNative_interface_accessibility_h() {
        OH_ArkUI_SendAccessibilityAsyncEvent(null, null, null)
        logLine("OH_ArkUI_SendAccessibilityAsyncEvent(null,null,null) ok")
    }

    // arkui/native_key_event.h
    @Test
    fun testNative_key_event_h() {
        val code = OH_ArkUI_KeyEvent_GetKeyCode(null)
        logLine("OH_ArkUI_KeyEvent_GetKeyCode(null)=$code")
    }

    // arkui/native_interface_focus.h
    @Test
    fun testNative_interface_focus_h() {
        val rc = OH_ArkUI_FocusRequest(null)
        assertNotNull(rc)
        logLine("OH_ArkUI_FocusRequest(null)=$rc")
    }

    // arkui/native_node_napi.h
    @Test
    fun testNative_node_napi_h() {
        val rc = OH_ArkUI_GetNodeHandleFromNapiValue(null, null, null)
        assertNotNull(rc)
        logLine("OH_ArkUI_GetNodeHandleFromNapiValue(null,null,null)=$rc")
    }

    // arkui/native_interface.h
    @Test
    fun testNative_interface_h() {
        val api = OH_ArkUI_QueryModuleInterfaceByName(ArkUI_NativeAPIVariantKind.ARKUI_NATIVE_NODE, "ArkUI_NativeNodeAPI_1")
        logLine("OH_ArkUI_QueryModuleInterfaceByName(NATIVE_NODE,ArkUI_NativeNodeAPI_1)=$api")
    }

    // arkui/drawable_descriptor.h
    @Test
    fun testDrawable_descriptor_h() {
        OH_ArkUI_DrawableDescriptor_Dispose(null)
        logLine("OH_ArkUI_DrawableDescriptor_Dispose(null) ok")
    }
}
