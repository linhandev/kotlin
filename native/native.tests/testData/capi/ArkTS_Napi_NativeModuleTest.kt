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
import platform.ArkTS.ArkTS_Napi_NativeModule.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ArkTS_Napi_NativeModuleTest {

    private fun logLine(msg: String) = println(msg)

    /** 记录函数返回值并 assertNotNull，assert 前也 log。 */
    private fun <T> logAndAssertNotNull(name: String, block: () -> T?) {
        val r = block()
        logLine("$name: $r")
        logLine("assertNotNull($name)")
        assertNotNull(r)
    }

    // ==================== 枚举：common.h ====================

    @Test
    fun testEnum_napi_qos_t() {
        var v: Int
        v = napi_qos_background.toInt(); logLine("napi_qos_background.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_qos_utility.toInt(); logLine("napi_qos_utility.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_qos_default.toInt(); logLine("napi_qos_default.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_qos_user_initiated.toInt(); logLine("napi_qos_user_initiated.toInt() = $v"); logLine("assertEquals($v, 3)"); assertEquals(v, 3)
        logLine("napi_qos_t passed")
    }

    @Test
    fun testEnum_napi_event_mode() {
        var v: Int
        v = napi_event_mode_default.toInt(); logLine("napi_event_mode_default.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_event_mode_nowait.toInt(); logLine("napi_event_mode_nowait.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        logLine("napi_event_mode passed")
    }

    @Test
    fun testEnum_napi_task_priority() {
        var v: Int
        v = napi_priority_immediate.toInt(); logLine("napi_priority_immediate.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_priority_high.toInt(); logLine("napi_priority_high.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_priority_low.toInt(); logLine("napi_priority_low.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_priority_idle.toInt(); logLine("napi_priority_idle.toInt() = $v"); logLine("assertEquals($v, 3)"); assertEquals(v, 3)
        logLine("napi_task_priority passed")
    }


    @Test
    fun testEnum_napi_status() {
        var v: Int
        v = napi_ok.toInt(); logLine("napi_ok.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_invalid_arg.toInt(); logLine("napi_invalid_arg.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_object_expected.toInt(); logLine("napi_object_expected.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_string_expected.toInt(); logLine("napi_string_expected.toInt() = $v"); logLine("assertEquals($v, 3)"); assertEquals(v, 3)
        v = napi_name_expected.toInt(); logLine("napi_name_expected.toInt() = $v"); logLine("assertEquals($v, 4)"); assertEquals(v, 4)
        v = napi_function_expected.toInt(); logLine("napi_function_expected.toInt() = $v"); logLine("assertEquals($v, 5)"); assertEquals(v, 5)
        v = napi_number_expected.toInt(); logLine("napi_number_expected.toInt() = $v"); logLine("assertEquals($v, 6)"); assertEquals(v, 6)
        v = napi_boolean_expected.toInt(); logLine("napi_boolean_expected.toInt() = $v"); logLine("assertEquals($v, 7)"); assertEquals(v, 7)
        v = napi_array_expected.toInt(); logLine("napi_array_expected.toInt() = $v"); logLine("assertEquals($v, 8)"); assertEquals(v, 8)
        v = napi_generic_failure.toInt(); logLine("napi_generic_failure.toInt() = $v"); logLine("assertEquals($v, 9)"); assertEquals(v, 9)
        v = napi_pending_exception.toInt(); logLine("napi_pending_exception.toInt() = $v"); logLine("assertEquals($v, 10)"); assertEquals(v, 10)
        v = napi_cancelled.toInt(); logLine("napi_cancelled.toInt() = $v"); logLine("assertEquals($v, 11)"); assertEquals(v, 11)
        v = napi_escape_called_twice.toInt(); logLine("napi_escape_called_twice.toInt() = $v"); logLine("assertEquals($v, 12)"); assertEquals(v, 12)
        v = napi_handle_scope_mismatch.toInt(); logLine("napi_handle_scope_mismatch.toInt() = $v"); logLine("assertEquals($v, 13)"); assertEquals(v, 13)
        v = napi_callback_scope_mismatch.toInt(); logLine("napi_callback_scope_mismatch.toInt() = $v"); logLine("assertEquals($v, 14)"); assertEquals(v, 14)
        v = napi_queue_full.toInt(); logLine("napi_queue_full.toInt() = $v"); logLine("assertEquals($v, 15)"); assertEquals(v, 15)
        v = napi_closing.toInt(); logLine("napi_closing.toInt() = $v"); logLine("assertEquals($v, 16)"); assertEquals(v, 16)
        v = napi_bigint_expected.toInt(); logLine("napi_bigint_expected.toInt() = $v"); logLine("assertEquals($v, 17)"); assertEquals(v, 17)
        v = napi_date_expected.toInt(); logLine("napi_date_expected.toInt() = $v"); logLine("assertEquals($v, 18)"); assertEquals(v, 18)
        v = napi_arraybuffer_expected.toInt(); logLine("napi_arraybuffer_expected.toInt() = $v"); logLine("assertEquals($v, 19)"); assertEquals(v, 19)
        v = napi_detachable_arraybuffer_expected.toInt(); logLine("napi_detachable_arraybuffer_expected.toInt() = $v"); logLine("assertEquals($v, 20)"); assertEquals(v, 20)
        v = napi_would_deadlock.toInt(); logLine("napi_would_deadlock.toInt() = $v"); logLine("assertEquals($v, 21)"); assertEquals(v, 21)
        v = napi_create_ark_runtime_too_many_envs.toInt(); logLine("napi_create_ark_runtime_too_many_envs.toInt() = $v"); logLine("assertEquals($v, 22)"); assertEquals(v, 22)
        v = napi_create_ark_runtime_only_one_env_per_thread.toInt(); logLine("napi_create_ark_runtime_only_one_env_per_thread.toInt() = $v"); logLine("assertEquals($v, 23)"); assertEquals(v, 23)
        v = napi_destroy_ark_runtime_env_not_exist.toInt(); logLine("napi_destroy_ark_runtime_env_not_exist.toInt() = $v"); logLine("assertEquals($v, 24)"); assertEquals(v, 24)
        logLine("napi_status passed")
    }

    @Test
    fun testEnum_napi_valuetype() {
        var v: Int
        v = napi_valuetype.napi_undefined.value.toInt(); logLine("napi_undefined.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_valuetype.napi_null.value.toInt(); logLine("napi_null.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_valuetype.napi_boolean.value.toInt(); logLine("napi_boolean.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_valuetype.napi_number.value.toInt(); logLine("napi_number.toInt() = $v"); logLine("assertEquals($v, 3)"); assertEquals(v, 3)
        v = napi_valuetype.napi_string.value.toInt(); logLine("napi_string.toInt() = $v"); logLine("assertEquals($v, 4)"); assertEquals(v, 4)
        v = napi_valuetype.napi_symbol.value.toInt(); logLine("napi_symbol.toInt() = $v"); logLine("assertEquals($v, 5)"); assertEquals(v, 5)
        v = napi_valuetype.napi_object.value.toInt(); logLine("napi_object.toInt() = $v"); logLine("assertEquals($v, 6)"); assertEquals(v, 6)
        v = napi_valuetype.napi_function.value.toInt(); logLine("napi_function.toInt() = $v"); logLine("assertEquals($v, 7)"); assertEquals(v, 7)
        v = napi_valuetype.napi_external.value.toInt(); logLine("napi_external.toInt() = $v"); logLine("assertEquals($v, 8)"); assertEquals(v, 8)
        v = napi_valuetype.napi_bigint.value.toInt(); logLine("napi_bigint.toInt() = $v"); logLine("assertEquals($v, 9)"); assertEquals(v, 9)
        logLine("napi_valuetype passed")
    }

    @Test
    fun testEnum_napi_property_attributes() {
        var v: Int
        v = napi_default.toInt(); logLine("napi_default.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_writable.toInt(); logLine("napi_writable.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_enumerable.toInt(); logLine("napi_enumerable.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_configurable.toInt(); logLine("napi_configurable.toInt() = $v"); logLine("assertEquals($v, 4)"); assertEquals(v, 4)
        v = napi_static.toInt(); logLine("napi_static.toInt() = $v"); logLine("assertEquals($v, 1024)"); assertEquals(v, 1024)
        logLine("napi_property_attributes passed")
    }

    @Test
    fun testEnum_napi_typedarray_type() {
        var v: Int
        v = napi_typedarray_type.napi_int8_array.value.toInt(); logLine("napi_int8_array.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_typedarray_type.napi_uint8_array.value.toInt(); logLine("napi_uint8_array.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_typedarray_type.napi_uint8_clamped_array.value.toInt(); logLine("napi_uint8_clamped_array.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_typedarray_type.napi_int16_array.value.toInt(); logLine("napi_int16_array.toInt() = $v"); logLine("assertEquals($v, 3)"); assertEquals(v, 3)
        v = napi_typedarray_type.napi_uint16_array.value.toInt(); logLine("napi_uint16_array.toInt() = $v"); logLine("assertEquals($v, 4)"); assertEquals(v, 4)
        v = napi_typedarray_type.napi_int32_array.value.toInt(); logLine("napi_int32_array.toInt() = $v"); logLine("assertEquals($v, 5)"); assertEquals(v, 5)
        v = napi_typedarray_type.napi_uint32_array.value.toInt(); logLine("napi_uint32_array.toInt() = $v"); logLine("assertEquals($v, 6)"); assertEquals(v, 6)
        v = napi_typedarray_type.napi_float32_array.value.toInt(); logLine("napi_float32_array.toInt() = $v"); logLine("assertEquals($v, 7)"); assertEquals(v, 7)
        v = napi_typedarray_type.napi_float64_array.value.toInt(); logLine("napi_float64_array.toInt() = $v"); logLine("assertEquals($v, 8)"); assertEquals(v, 8)
        v = napi_typedarray_type.napi_bigint64_array.value.toInt(); logLine("napi_bigint64_array.toInt() = $v"); logLine("assertEquals($v, 9)"); assertEquals(v, 9)
        v = napi_typedarray_type.napi_biguint64_array.value.toInt(); logLine("napi_biguint64_array.toInt() = $v"); logLine("assertEquals($v, 10)"); assertEquals(v, 10)
        logLine("napi_typedarray_type passed")
    }

    @Test
    fun testEnum_napi_threadsafe_function_modes() {
        var v: Int
        v = napi_threadsafe_function_release_mode.napi_tsfn_release.value.toInt(); logLine("napi_tsfn_release.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_threadsafe_function_release_mode.napi_tsfn_abort.value.toInt(); logLine("napi_tsfn_abort.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_threadsafe_function_call_mode.napi_tsfn_nonblocking.value.toInt(); logLine("napi_tsfn_nonblocking.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_threadsafe_function_call_mode.napi_tsfn_blocking.value.toInt(); logLine("napi_tsfn_blocking.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        logLine("napi_threadsafe_function_modes passed")
    }

    @Test
    fun testEnum_napi_key_filter() {
        var v: Int
        v = napi_key_all_properties.toInt(); logLine("napi_key_all_properties.toInt() = $v"); logLine("assertEquals($v, 0)"); assertEquals(v, 0)
        v = napi_key_writable.toInt(); logLine("napi_key_writable.toInt() = $v"); logLine("assertEquals($v, 1)"); assertEquals(v, 1)
        v = napi_key_enumerable.toInt(); logLine("napi_key_enumerable.toInt() = $v"); logLine("assertEquals($v, 2)"); assertEquals(v, 2)
        v = napi_key_configurable.toInt(); logLine("napi_key_configurable.toInt() = $v"); logLine("assertEquals($v, 4)"); assertEquals(v, 4)
        v = napi_key_skip_strings.toInt(); logLine("napi_key_skip_strings.toInt() = $v"); logLine("assertEquals($v, 8)"); assertEquals(v, 8)
        v = napi_key_skip_symbols.toInt(); logLine("napi_key_skip_symbols.toInt() = $v"); logLine("assertEquals($v, 16)"); assertEquals(v, 16)
        logLine("napi_key_filter passed")
    }

    // ==================== 函数：以 null env 调用，assertNotNull(return) ====================

    @Test fun testNapi_get_undefined() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_undefined") { napi_get_undefined(null, outVal.ptr) } } }
    @Test fun testNapi_get_null() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_null") { napi_get_null(null, outVal.ptr) } } }
    @Test fun testNapi_get_global() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_global") { napi_get_global(null, outVal.ptr) } } }
    @Test fun testNapi_get_boolean() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_boolean") { napi_get_boolean(null, false, outVal.ptr) } } }
    @Test fun testNapi_create_object() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_object") { napi_create_object(null, outVal.ptr) } } }
    @Test fun testNapi_create_array() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_array") { napi_create_array(null, outVal.ptr) } } }
    @Test fun testNapi_create_array_with_length() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_array_with_length") { napi_create_array_with_length(null, 0uL, outVal.ptr) } } }
    @Test fun testNapi_create_double() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_double") { napi_create_double(null, 0.0, outVal.ptr) } } }
    @Test fun testNapi_create_int32() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_int32") { napi_create_int32(null, 0, outVal.ptr) } } }
    @Test fun testNapi_create_uint32() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_uint32") { napi_create_uint32(null, 0u, outVal.ptr) } } }
    @Test fun testNapi_create_int64() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_int64") { napi_create_int64(null, 0L, outVal.ptr) } } }
    @Test fun testNapi_get_value_bool() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_get_value_bool") { napi_get_value_bool(null, null, outBool.ptr) } } }
    @Test fun testNapi_get_value_double() { memScoped { val outDouble = alloc<DoubleVar>(); logAndAssertNotNull("napi_get_value_double") { napi_get_value_double(null, null, outDouble.ptr) } } }
    @Test fun testNapi_get_value_int32() { memScoped { val outInt32 = alloc<IntVar>(); logAndAssertNotNull("napi_get_value_int32") { napi_get_value_int32(null, null, outInt32.ptr) } } }
    @Test fun testNapi_get_value_uint32() { memScoped { val outUint32 = alloc<UIntVar>(); logAndAssertNotNull("napi_get_value_uint32") { napi_get_value_uint32(null, null, outUint32.ptr) } } }
    @Test fun testNapi_get_value_int64() { memScoped { val outInt64 = alloc<LongVar>(); logAndAssertNotNull("napi_get_value_int64") { napi_get_value_int64(null, null, outInt64.ptr) } } }
    @Test fun testNapi_typeof() { memScoped { val outType = alloc<napi_valuetype.Var>(); logAndAssertNotNull("napi_typeof") { napi_typeof(null, null, outType.ptr) } } }

    @Test fun testNapi_create_string_latin1() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_string_latin1") { napi_create_string_latin1(null, null, 0u, outVal.ptr) } } }
    @Test fun testNapi_create_string_utf8() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_string_utf8") { napi_create_string_utf8(null, null, 0u, outVal.ptr) } } }
    @Test fun testNapi_create_string_utf16() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_string_utf16") { napi_create_string_utf16(null, null, 0u, outVal.ptr) } } }
    @Test fun testNapi_get_value_string_latin1() { memScoped { val outLen = alloc<ULongVar>(); logAndAssertNotNull("napi_get_value_string_latin1") { napi_get_value_string_latin1(null, null, null, 0u, outLen.ptr) } } }
    @Test fun testNapi_get_value_string_utf8() { memScoped { val outLen = alloc<ULongVar>(); logAndAssertNotNull("napi_get_value_string_utf8") { napi_get_value_string_utf8(null, null, null, 0u, outLen.ptr) } } }
    @Test fun testNapi_get_value_string_utf16() { memScoped { val outLen = alloc<ULongVar>(); logAndAssertNotNull("napi_get_value_string_utf16") { napi_get_value_string_utf16(null, null, null, 0u, outLen.ptr) } } }
    @Test fun testNapi_create_symbol() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_symbol") { napi_create_symbol(null, null, outVal.ptr) } } }

    @Test fun testNapi_get_prototype() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_prototype") { napi_get_prototype(null, null, outVal.ptr) } } }
    @Test fun testNapi_get_property_names() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_property_names") { napi_get_property_names(null, null, outVal.ptr) } } }
    @Test fun testNapi_set_property() { memScoped { logAndAssertNotNull("napi_set_property") { napi_set_property(null, null, null, null) } } }
    @Test fun testNapi_get_property() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_property") { napi_get_property(null, null, null, outVal.ptr) } } }
    @Test fun testNapi_has_property() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_has_property") { napi_has_property(null, null, null, outBool.ptr) } } }
    @Test fun testNapi_delete_property() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_delete_property") { napi_delete_property(null, null, null, outBool.ptr) } } }
    @Test fun testNapi_set_named_property() { memScoped { logAndAssertNotNull("napi_set_named_property") { napi_set_named_property(null, null, null, null) } } }
    @Test fun testNapi_get_named_property() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_named_property") { napi_get_named_property(null, null, null, outVal.ptr) } } }
    @Test fun testNapi_has_named_property() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_has_named_property") { napi_has_named_property(null, null, null, outBool.ptr) } } }
    @Test fun testNapi_has_own_property() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_has_own_property") { napi_has_own_property(null, null, null, outBool.ptr) } } }
    @Test fun testNapi_set_element() { memScoped { logAndAssertNotNull("napi_set_element") { napi_set_element(null, null, 0u, null) } } }
    @Test fun testNapi_get_element() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_element") { napi_get_element(null, null, 0u, outVal.ptr) } } }
    @Test fun testNapi_has_element() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_has_element") { napi_has_element(null, null, 0u, outBool.ptr) } } }
    @Test fun testNapi_delete_element() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_delete_element") { napi_delete_element(null, null, 0u, outBool.ptr) } } }
    @Test fun testNapi_get_array_length() { memScoped { val outLength = alloc<UIntVar>(); logAndAssertNotNull("napi_get_array_length") { napi_get_array_length(null, null, outLength.ptr) } } }

    @Test fun testNapi_coerce_to_bool() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_coerce_to_bool") { napi_coerce_to_bool(null, null, outVal.ptr) } } }
    @Test fun testNapi_coerce_to_number() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_coerce_to_number") { napi_coerce_to_number(null, null, outVal.ptr) } } }
    @Test fun testNapi_coerce_to_object() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_coerce_to_object") { napi_coerce_to_object(null, null, outVal.ptr) } } }
    @Test fun testNapi_coerce_to_string() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_coerce_to_string") { napi_coerce_to_string(null, null, outVal.ptr) } } }
    @Test fun testNapi_instanceof() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_instanceof") { napi_instanceof(null, null, null, outBool.ptr) } } }
    @Test fun testNapi_is_array() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_array") { napi_is_array(null, null, outBool.ptr) } } }
    @Test fun testNapi_strict_equals() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_strict_equals") { napi_strict_equals(null, null, null, outBool.ptr) } } }

    @Test fun testNapi_create_reference() { memScoped { val outRef = alloc<CPointerVar<napi_ref__>>(); logAndAssertNotNull("napi_create_reference") { napi_create_reference(null, null, 0u, outRef.ptr) } } }
    @Test fun testNapi_delete_reference() { memScoped { logAndAssertNotNull("napi_delete_reference") { napi_delete_reference(null, null) } } }
    @Test fun testNapi_reference_ref() { memScoped { val outRefCount = alloc<UIntVar>(); logAndAssertNotNull("napi_reference_ref") { napi_reference_ref(null, null, outRefCount.ptr) } } }
    @Test fun testNapi_reference_unref() { memScoped { val outRefCount = alloc<UIntVar>(); logAndAssertNotNull("napi_reference_unref") { napi_reference_unref(null, null, outRefCount.ptr) } } }
    @Test fun testNapi_get_reference_value() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_reference_value") { napi_get_reference_value(null, null, outVal.ptr) } } }
    @Test fun testNapi_open_handle_scope() { memScoped { val outScope = alloc<CPointerVar<napi_handle_scope__>>(); logAndAssertNotNull("napi_open_handle_scope") { napi_open_handle_scope(null, outScope.ptr) } } }
    @Test fun testNapi_close_handle_scope() { memScoped { logAndAssertNotNull("napi_close_handle_scope") { napi_close_handle_scope(null, null) } } }
    @Test fun testNapi_open_escapable_handle_scope() { memScoped { val outEscapable = alloc<CPointerVar<napi_escapable_handle_scope__>>(); logAndAssertNotNull("napi_open_escapable_handle_scope") { napi_open_escapable_handle_scope(null, outEscapable.ptr) } } }
    @Test fun testNapi_close_escapable_handle_scope() { memScoped { logAndAssertNotNull("napi_close_escapable_handle_scope") { napi_close_escapable_handle_scope(null, null) } } }
    @Test fun testNapi_escape_handle() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_escape_handle") { napi_escape_handle(null, null, null, outVal.ptr) } } }

    @Test fun testNapi_throw() { memScoped { logAndAssertNotNull("napi_throw") { napi_throw(null, null) } } }
    @Test fun testNapi_throw_error() { memScoped { logAndAssertNotNull("napi_throw_error") { napi_throw_error(null, null, null) } } }
    @Test fun testNapi_throw_type_error() { memScoped { logAndAssertNotNull("napi_throw_type_error") { napi_throw_type_error(null, null, null) } } }
    @Test fun testNapi_throw_range_error() { memScoped { logAndAssertNotNull("napi_throw_range_error") { napi_throw_range_error(null, null, null) } } }
    @Test fun testNapi_is_error() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_error") { napi_is_error(null, null, outBool.ptr) } } }
    @Test fun testNapi_create_error() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_error") { napi_create_error(null, null, null, outVal.ptr) } } }
    @Test fun testNapi_create_type_error() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_type_error") { napi_create_type_error(null, null, null, outVal.ptr) } } }
    @Test fun testNapi_create_range_error() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_range_error") { napi_create_range_error(null, null, null, outVal.ptr) } } }
    @Test fun testNapi_is_exception_pending() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_exception_pending") { napi_is_exception_pending(null, outBool.ptr) } } }
    @Test fun testNapi_get_and_clear_last_exception() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_and_clear_last_exception") { napi_get_and_clear_last_exception(null, outVal.ptr) } } }

    @Test fun testNapi_is_arraybuffer() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_arraybuffer") { napi_is_arraybuffer(null, null, outBool.ptr) } } }
    @Test fun testNapi_create_arraybuffer() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_create_arraybuffer") { napi_create_arraybuffer(null, 0uL, outData.ptr, outVal.ptr) } } }
    @Test fun testNapi_create_external_arraybuffer() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_external_arraybuffer") { napi_create_external_arraybuffer(null, null, 0uL, null, null, outVal.ptr) } } }
    @Test fun testNapi_create_buffer_copy() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_create_buffer_copy") { napi_create_buffer_copy(null, 0uL, null, outData.ptr, outVal.ptr) } } }
    @Test fun testNapi_get_arraybuffer_info() { memScoped { val outData = alloc<COpaquePointerVar>(); val outSize = alloc<ULongVar>(); logAndAssertNotNull("napi_get_arraybuffer_info") { napi_get_arraybuffer_info(null, null, outData.ptr, outSize.ptr) } } }
    @Test fun testNapi_is_typedarray() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_typedarray") { napi_is_typedarray(null, null, outBool.ptr) } } }
    @Test fun testNapi_create_typedarray() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_typedarray") { napi_create_typedarray(null, napi_typedarray_type.napi_uint8_array, 0uL, null, 0uL, outVal.ptr) } } }
    @Test fun testNapi_get_typedarray_info() { memScoped { val outTypedType = alloc<napi_typedarray_type.Var>(); val outSize = alloc<ULongVar>(); val outData = alloc<COpaquePointerVar>(); val outArrBuf = alloc<CPointerVar<napi_value__>>(); val outByteOff = alloc<ULongVar>(); logAndAssertNotNull("napi_get_typedarray_info") { napi_get_typedarray_info(null, null, outTypedType.ptr, outSize.ptr, outData.ptr, outArrBuf.ptr, outByteOff.ptr) } } }
    @Test fun testNapi_create_dataview() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_dataview") { napi_create_dataview(null, 0uL, null, 0uL, outVal.ptr) } } }
    @Test fun testNapi_is_dataview() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_dataview") { napi_is_dataview(null, null, outBool.ptr) } } }
    @Test fun testNapi_get_dataview_info() { memScoped { val outSize = alloc<ULongVar>(); val outData = alloc<COpaquePointerVar>(); val outArrBuf = alloc<CPointerVar<napi_value__>>(); val outByteOff = alloc<ULongVar>(); logAndAssertNotNull("napi_get_dataview_info") { napi_get_dataview_info(null, null, outSize.ptr, outData.ptr, outArrBuf.ptr, outByteOff.ptr) } } }
    @Test fun testNapi_get_buffer_info() { memScoped { val outData = alloc<COpaquePointerVar>(); val outSize = alloc<ULongVar>(); logAndAssertNotNull("napi_get_buffer_info") { napi_get_buffer_info(null, null, outData.ptr, outSize.ptr) } } }
    @Test fun testNapi_is_buffer() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_buffer") { napi_is_buffer(null, null, outBool.ptr) } } }
    @Test fun testNapi_detach_arraybuffer() { memScoped { logAndAssertNotNull("napi_detach_arraybuffer") { napi_detach_arraybuffer(null, null) } } }
    @Test fun testNapi_is_detached_arraybuffer() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_detached_arraybuffer") { napi_is_detached_arraybuffer(null, null, outBool.ptr) } } }
    @Test fun testNapi_object_freeze() { memScoped { logAndAssertNotNull("napi_object_freeze") { napi_object_freeze(null, null) } } }
    @Test fun testNapi_object_seal() { memScoped { logAndAssertNotNull("napi_object_seal") { napi_object_seal(null, null) } } }

    @Test fun testNapi_create_external() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_external") { napi_create_external(null, null, null, null, outVal.ptr) } } }
    @Test fun testNapi_get_value_external() { memScoped { val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_get_value_external") { napi_get_value_external(null, null, outData.ptr) } } }
    @Test fun testNapi_wrap() { memScoped { val outRef = alloc<CPointerVar<napi_ref__>>(); logAndAssertNotNull("napi_wrap") { napi_wrap(null, null, null, null, null, outRef.ptr) } } }
    @Test fun testNapi_unwrap() { memScoped { val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_unwrap") { napi_unwrap(null, null, outData.ptr) } } }
    @Test fun testNapi_remove_wrap() { memScoped { val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_remove_wrap") { napi_remove_wrap(null, null, outData.ptr) } } }

    @Test fun testNapi_call_function() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_call_function") { napi_call_function(null, null, null, 0uL, null, outVal.ptr) } } }
    @Test fun testNapi_get_new_target() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_new_target") { napi_get_new_target(null, null, outVal.ptr) } } }
    @Test fun testNapi_new_instance() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_new_instance") { napi_new_instance(null, null, 0uL, null, outVal.ptr) } } }
    @Test fun testNapi_define_class() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_define_class") { napi_define_class(null, null, 0uL, null, null, 0uL, null, outVal.ptr) } } }
    @Test fun testNapi_create_function() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_function") { napi_create_function(null, null, 0uL, null, null, outVal.ptr) } } }

    @Test fun testNapi_create_async_work() { memScoped { val outWork = alloc<CPointerVar<napi_async_work__>>(); logAndAssertNotNull("napi_create_async_work") { napi_create_async_work(null, null, null, null, null, null, outWork.ptr) } } }
    @Test fun testNapi_delete_async_work() { memScoped { logAndAssertNotNull("napi_delete_async_work") { napi_delete_async_work(null, null) } } }
    @Test fun testNapi_queue_async_work() { memScoped { logAndAssertNotNull("napi_queue_async_work") { napi_queue_async_work(null, null) } } }
    @Test fun testNapi_cancel_async_work() { memScoped { logAndAssertNotNull("napi_cancel_async_work") { napi_cancel_async_work(null, null) } } }
    @Test fun testNapi_create_promise() { memScoped { val outDeferred = alloc<CPointerVar<napi_deferred__>>(); val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_promise") { napi_create_promise(null, outDeferred.ptr, outVal.ptr) } } }
    @Test fun testNapi_resolve_deferred() { memScoped { logAndAssertNotNull("napi_resolve_deferred") { napi_resolve_deferred(null, null, null) } } }
    @Test fun testNapi_reject_deferred() { memScoped { logAndAssertNotNull("napi_reject_deferred") { napi_reject_deferred(null, null, null) } } }
    @Test fun testNapi_is_promise() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_promise") { napi_is_promise(null, null, outBool.ptr) } } }

    @Test fun testNapi_run_script_path() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_run_script_path") { napi_run_script_path(null, null, outVal.ptr) } } }
    @Test fun testNapi_queue_async_work_with_qos() { memScoped { logAndAssertNotNull("napi_queue_async_work_with_qos") { napi_queue_async_work_with_qos(null, null, napi_qos_default) } } }
    @Test fun testNapi_load_module() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_load_module") { napi_load_module(null, null, outVal.ptr) } } }
    @Test fun testNapi_set_instance_data() { memScoped { logAndAssertNotNull("napi_set_instance_data") { napi_set_instance_data(null, null, null, null) } } }
    @Test fun testNapi_get_instance_data() { memScoped { val outInstanceData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_get_instance_data") { napi_get_instance_data(null, outInstanceData.ptr) } } }
    @Test fun testNapi_add_env_cleanup_hook() { memScoped { logAndAssertNotNull("napi_add_env_cleanup_hook") { napi_add_env_cleanup_hook(null, null, null) } } }
    @Test fun testNapi_remove_env_cleanup_hook() { memScoped { logAndAssertNotNull("napi_remove_env_cleanup_hook") { napi_remove_env_cleanup_hook(null, null, null) } } }
    @Test fun testNapi_add_async_cleanup_hook() { memScoped { val outHandle = alloc<CPointerVar<napi_async_cleanup_hook_handle__>>(); logAndAssertNotNull("napi_add_async_cleanup_hook") { napi_add_async_cleanup_hook(null, null, null, outHandle.ptr) } } }
    @Test fun testNapi_remove_async_cleanup_hook() { memScoped { logAndAssertNotNull("napi_remove_async_cleanup_hook") { napi_remove_async_cleanup_hook(null) } } }

    @Test fun testNapi_async_init() { memScoped { val outCtx = alloc<CPointerVar<napi_async_context__>>(); logAndAssertNotNull("napi_async_init") { napi_async_init(null, null, null, outCtx.ptr) } } }
    @Test fun testNapi_async_destroy() { memScoped { logAndAssertNotNull("napi_async_destroy") { napi_async_destroy(null, null) } } }
    @Test fun testNapi_open_callback_scope() { memScoped { val outScope = alloc<CPointerVar<napi_callback_scope__>>(); logAndAssertNotNull("napi_open_callback_scope") { napi_open_callback_scope(null, null, null, outScope.ptr) } } }
    @Test fun testNapi_close_callback_scope() { memScoped { logAndAssertNotNull("napi_close_callback_scope") { napi_close_callback_scope(null, null) } } }
    @Test fun testNapi_make_callback() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_make_callback") { napi_make_callback(null, null, null, null, 0uL, null, outVal.ptr) } } }

    @Test fun testNapi_define_properties() { memScoped { logAndAssertNotNull("napi_define_properties") { napi_define_properties(null, null, 0uL, null) } } }
    @Test fun testNapi_get_cb_info() { memScoped { val outArgc = alloc<ULongVar>(); val outArgv = alloc<CPointerVar<napi_value__>>(); val outThis = alloc<CPointerVar<napi_value__>>(); val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_get_cb_info") { napi_get_cb_info(null, null, outArgc.ptr, outArgv.ptr, outThis.ptr, outData.ptr) } } }

    @Test fun testNapi_create_buffer() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); val outData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_create_buffer") { napi_create_buffer(null, 0uL, outData.ptr, outVal.ptr) } } }
    @Test fun testNapi_create_external_buffer() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_external_buffer") { napi_create_external_buffer(null, 0uL, null, null, null, outVal.ptr) } } }
    @Test fun testNapi_create_date() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_date") { napi_create_date(null, 0.0, outVal.ptr) } } }
    @Test fun testNapi_is_date() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_date") { napi_is_date(null, null, outBool.ptr) } } }
    @Test fun testNapi_get_date_value() { memScoped { val outDouble = alloc<DoubleVar>(); logAndAssertNotNull("napi_get_date_value") { napi_get_date_value(null, null, outDouble.ptr) } } }
    @Test fun testNapi_create_bigint_int64() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_bigint_int64") { napi_create_bigint_int64(null, 0L, outVal.ptr) } } }
    @Test fun testNapi_create_bigint_uint64() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_bigint_uint64") { napi_create_bigint_uint64(null, 0uL, outVal.ptr) } } }
    @Test fun testNapi_create_bigint_words() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); val outWords = allocArray<ULongVar>(1); logAndAssertNotNull("napi_create_bigint_words") { napi_create_bigint_words(null, 0, 0uL, outWords, outVal.ptr) } } }
    @Test fun testNapi_get_value_bigint_int64() { memScoped { val outInt64 = alloc<LongVar>(); val outLossless = alloc<BooleanVar>(); logAndAssertNotNull("napi_get_value_bigint_int64") { napi_get_value_bigint_int64(null, null, outInt64.ptr, outLossless.ptr) } } }
    @Test fun testNapi_get_value_bigint_uint64() { memScoped { val outUint64 = alloc<ULongVar>(); val outLossless = alloc<BooleanVar>(); logAndAssertNotNull("napi_get_value_bigint_uint64") { napi_get_value_bigint_uint64(null, null, outUint64.ptr, outLossless.ptr) } } }
    @Test fun testNapi_get_value_bigint_words() { memScoped { val outSignBit = alloc<IntVar>(); val outWordCount = alloc<ULongVar>(); val outWords = allocArray<ULongVar>(1); logAndAssertNotNull("napi_get_value_bigint_words") { napi_get_value_bigint_words(null, null, outSignBit.ptr, outWordCount.ptr, outWords) } } }

    @Test fun testNapi_create_threadsafe_function() { memScoped { val outTsfn = alloc<CPointerVar<napi_threadsafe_function__>>(); logAndAssertNotNull("napi_create_threadsafe_function") { napi_create_threadsafe_function(null, null, null, null, 0uL, 0uL, null, null, null, null, outTsfn.ptr) } } }
    @Test fun testNapi_get_threadsafe_function_context() { memScoped { val outTsfnCtx = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_get_threadsafe_function_context") { napi_get_threadsafe_function_context(null, outTsfnCtx.ptr) } } }
    @Test fun testNapi_call_threadsafe_function() { memScoped { logAndAssertNotNull("napi_call_threadsafe_function") { napi_call_threadsafe_function(null, null, napi_threadsafe_function_call_mode.napi_tsfn_blocking) } } }
    @Test fun testNapi_acquire_threadsafe_function() { memScoped { logAndAssertNotNull("napi_acquire_threadsafe_function") { napi_acquire_threadsafe_function(null) } } }
    @Test fun testNapi_release_threadsafe_function() { memScoped { logAndAssertNotNull("napi_release_threadsafe_function") { napi_release_threadsafe_function(null, napi_threadsafe_function_release_mode.napi_tsfn_release) } } }
    @Test fun testNapi_unref_threadsafe_function() { memScoped { logAndAssertNotNull("napi_unref_threadsafe_function") { napi_unref_threadsafe_function(null, null) } } }
    @Test fun testNapi_ref_threadsafe_function() { memScoped { logAndAssertNotNull("napi_ref_threadsafe_function") { napi_ref_threadsafe_function(null, null) } } }
    @Test fun testNapi_call_threadsafe_function_with_priority() { memScoped { logAndAssertNotNull("napi_call_threadsafe_function_with_priority") { napi_call_threadsafe_function_with_priority(null, null, napi_priority_immediate, false) } } }
    @Test fun testNapi_get_uv_event_loop() { memScoped { val outLoop = alloc<CPointerVar<uv_loop_s>>(); logAndAssertNotNull("napi_get_uv_event_loop") { napi_get_uv_event_loop(null, outLoop.ptr) } } }
    @Test fun testNapi_fatal_exception() { memScoped { logAndAssertNotNull("napi_fatal_exception") { napi_fatal_exception(null, null) } } }
    @Test fun testNapi_get_all_property_names() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_all_property_names") { napi_get_all_property_names(null, null, napi_key_collection_mode.napi_key_own_only, napi_key_enumerable, napi_key_conversion.napi_key_numbers_to_strings, outVal.ptr) } } }
    @Test fun testNapi_get_last_error_info() { memScoped { val outErrorInfo = alloc<CPointerVar<napi_extended_error_info>>(); logAndAssertNotNull("napi_get_last_error_info") { napi_get_last_error_info(null, outErrorInfo.ptr) } } }

    @Test fun testNapi_create_object_with_properties() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_object_with_properties") { napi_create_object_with_properties(null, outVal.ptr, 0uL, null) } } }
    @Test fun testNapi_create_object_with_named_properties() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_object_with_named_properties") { napi_create_object_with_named_properties(null, outVal.ptr, 0uL, null, null) } } }
    @Test fun testNapi_coerce_to_native_binding_object() { memScoped { logAndAssertNotNull("napi_coerce_to_native_binding_object") { napi_coerce_to_native_binding_object(null, null, null, null, null, null) } } }
    @Test fun testNapi_add_finalizer() { memScoped { val outAddFinalizerRef = alloc<CPointerVar<napi_ref__>>(); logAndAssertNotNull("napi_add_finalizer") { napi_add_finalizer(null, null, null, null, null, outAddFinalizerRef.ptr) } } }
    @Test fun testNapi_load_module_with_info() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_load_module_with_info") { napi_load_module_with_info(null, null, null, outVal.ptr) } } }

    @Test fun testNapi_create_ark_runtime() { memScoped { val outEnv = alloc<CPointerVar<napi_env__>>(); logAndAssertNotNull("napi_create_ark_runtime") { napi_create_ark_runtime(outEnv.ptr) } } }
    @Test fun testNapi_destroy_ark_runtime() { memScoped { val outEnv = alloc<CPointerVar<napi_env__>>(); logAndAssertNotNull("napi_destroy_ark_runtime") { napi_destroy_ark_runtime(outEnv.ptr) } } }
    
    @Test fun testNapi_create_ark_context() { 
        memScoped {
            try { val outEnv = alloc<CPointerVar<napi_env__>>(); logAndAssertNotNull("napi_create_ark_context (API 20)") { napi_create_ark_context(null, outEnv.ptr) } }
            catch (e: Throwable) { logLine("napi_create_ark_context (API 20) exception: $e") }
        }
    }
    @Test fun testNapi_switch_ark_context() {
        memScoped {
            try { logAndAssertNotNull("napi_switch_ark_context (API 20)") { napi_switch_ark_context(null) } }
            catch (e: Throwable) { logLine("napi_switch_ark_context (API 20) exception: $e") }
        }
    }
    @Test fun testNapi_destroy_ark_context() {
        memScoped {
            try { logAndAssertNotNull("napi_destroy_ark_context (API 20)") { napi_destroy_ark_context(null) } }
            catch (e: Throwable) { logLine("napi_destroy_ark_context (API 20) exception: $e") }
        }
    }

    @Test fun testNapi_define_sendable_class() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_define_sendable_class") { napi_define_sendable_class(null, null, 0uL, null, null, 0uL, null, null, outVal.ptr) } } }
    @Test fun testNapi_is_sendable() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_is_sendable") { napi_is_sendable(null, null, outBool.ptr) } } }
    @Test fun testNapi_create_sendable_object_with_properties() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_sendable_object_with_properties") { napi_create_sendable_object_with_properties(null, 0uL, null, outVal.ptr) } } }
    @Test fun testNapi_wrap_sendable() { memScoped { logAndAssertNotNull("napi_wrap_sendable") { napi_wrap_sendable(null, null, null, null, null) } } }
    @Test fun testNapi_wrap_sendable_with_size() { memScoped { logAndAssertNotNull("napi_wrap_sendable_with_size") { napi_wrap_sendable_with_size(null, null, null, null, null, 0uL) } } }
    @Test fun testNapi_unwrap_sendable() { memScoped { val outSendableData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_unwrap_sendable") { napi_unwrap_sendable(null, null, outSendableData.ptr) } } }
    @Test fun testNapi_remove_wrap_sendable() { memScoped { val outSendableData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_remove_wrap_sendable") { napi_remove_wrap_sendable(null, null, outSendableData.ptr) } } }
    @Test fun testNapi_create_sendable_array() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_sendable_array") { napi_create_sendable_array(null, outVal.ptr) } } }
    @Test fun testNapi_create_sendable_array_with_length() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_sendable_array_with_length") { napi_create_sendable_array_with_length(null, 0uL, outVal.ptr) } } }
    @Test fun testNapi_create_sendable_arraybuffer() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_sendable_arraybuffer") { napi_create_sendable_arraybuffer(null, 0uL, null, outVal.ptr) } } }
    @Test fun testNapi_create_sendable_typedarray() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_sendable_typedarray") { napi_create_sendable_typedarray(null, napi_typedarray_type.napi_uint8_array, 0uL, null, 0uL, outVal.ptr) } } }

    @Test fun testNapi_run_event_loop() { memScoped { logAndAssertNotNull("napi_run_event_loop") { napi_run_event_loop(null, napi_event_mode_default) } } }
    @Test fun testNapi_stop_event_loop() { memScoped { logAndAssertNotNull("napi_stop_event_loop") { napi_stop_event_loop(null) } } }
    @Test fun testNapi_serialize() { memScoped { val outSerializeData = alloc<COpaquePointerVar>(); logAndAssertNotNull("napi_serialize") { napi_serialize(null, null, null, null, outSerializeData.ptr) } } }
    @Test fun testNapi_deserialize() { memScoped { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_deserialize") { napi_deserialize(null, null, outVal.ptr) } } }
    @Test fun testNapi_delete_serialization_data() { memScoped { logAndAssertNotNull("napi_delete_serialization_data") { napi_delete_serialization_data(null, null) } } }

    @Test fun testNapi_open_critical_scope() { memScoped { try { val outScope = alloc<CPointerVar<napi_critical_scope__>>(); logAndAssertNotNull("napi_open_critical_scope (API 21/22)") { napi_open_critical_scope(null, outScope.ptr) } } catch (e: Throwable) { logLine("napi_open_critical_scope (API 21/22) exception: $e") } } }
    @Test fun testNapi_close_critical_scope() { memScoped { try { logAndAssertNotNull("napi_close_critical_scope (API 21/22)") { napi_close_critical_scope(null, null) } } catch (e: Throwable) { logLine("napi_close_critical_scope (API 21/22) exception: $e") } } }
    @Test fun testNapi_get_buffer_string_utf16_in_critical_scope() { memScoped { try { val outUtf16Buf = alloc<CPointerVar<UShortVar>>(); val outBufSize = alloc<ULongVar>(); logAndAssertNotNull("napi_get_buffer_string_utf16_in_critical_scope (API 21/22)") { napi_get_buffer_string_utf16_in_critical_scope(null, null, outUtf16Buf.ptr, outBufSize.ptr) } } catch (e: Throwable) { logLine("napi_get_buffer_string_utf16_in_critical_scope (API 21/22) exception: $e") } } }
    @Test fun testNapi_create_strong_reference() { memScoped { try { val outRef = alloc<CPointerVar<napi_strong_ref__>>(); logAndAssertNotNull("napi_create_strong_reference (API 21/22)") { napi_create_strong_reference(null, null, outRef.ptr) } } catch (e: Throwable) { logLine("napi_create_strong_reference (API 21/22) exception: $e") } } }
    @Test fun testNapi_delete_strong_reference() { memScoped { try { logAndAssertNotNull("napi_delete_strong_reference (API 21/22)") { napi_delete_strong_reference(null, null) } } catch (e: Throwable) { logLine("napi_delete_strong_reference (API 21/22) exception: $e") } } }
    @Test fun testNapi_get_strong_reference_value() { memScoped { try { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_strong_reference_value (API 21/22)") { napi_get_strong_reference_value(null, null, outVal.ptr) } } catch (e: Throwable) { logLine("napi_get_strong_reference_value (API 21/22) exception: $e") } } }
    @Test fun testNapi_create_external_string_utf16() { memScoped { try { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_external_string_utf16 (API 21/22)") { napi_create_external_string_utf16(null, null, 0uL, null, null, outVal.ptr) } } catch (e: Throwable) { logLine("napi_create_external_string_utf16 (API 21/22) exception: $e") } } }
    @Test fun testNapi_create_external_string_ascii() { memScoped { try { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_create_external_string_ascii (API 21/22)") { napi_create_external_string_ascii(null, null, 0uL, null, null, outVal.ptr) } } catch (e: Throwable) { logLine("napi_create_external_string_ascii (API 21/22) exception: $e") } } }
    @Test fun testNapi_create_strong_sendable_reference() { memScoped { try { val outSendableRef = alloc<CPointerVar<napi_sendable_ref__>>(); logAndAssertNotNull("napi_create_strong_sendable_reference (API 21/22)") { napi_create_strong_sendable_reference(null, null, outSendableRef.ptr) } } catch (e: Throwable) { logLine("napi_create_strong_sendable_reference (API 21/22) exception: $e") } } }
    @Test fun testNapi_delete_strong_sendable_reference() { memScoped { try { logAndAssertNotNull("napi_delete_strong_sendable_reference (API 21/22)") { napi_delete_strong_sendable_reference(null, null) } } catch (e: Throwable) { logLine("napi_delete_strong_sendable_reference (API 21/22) exception: $e") } } }
    @Test fun testNapi_get_strong_sendable_reference_value() { memScoped { try { val outVal = alloc<CPointerVar<napi_value__>>(); logAndAssertNotNull("napi_get_strong_sendable_reference_value (API 21/22)") { napi_get_strong_sendable_reference_value(null, null, outVal.ptr) } } catch (e: Throwable) { logLine("napi_get_strong_sendable_reference_value (API 21/22) exception: $e") } } }
 
    @Test fun testNapi_type_tag_object() { memScoped { logAndAssertNotNull("napi_type_tag_object") { napi_type_tag_object(null, null, null) } } }
    @Test fun testNapi_check_object_type_tag() { memScoped { val outBool = alloc<BooleanVar>(); logAndAssertNotNull("napi_check_object_type_tag") { napi_check_object_type_tag(null, null, null, outBool.ptr) } } }
    @Test fun testNapi_wrap_enhance() { memScoped { try { val outWrapRef = alloc<CPointerVar<napi_ref__>>(); logAndAssertNotNull("napi_wrap_enhance (API 18)") { napi_wrap_enhance(null, null, null, null, false, null, 0uL, outWrapRef.ptr) } } catch (e: Throwable) { logLine("napi_wrap_enhance (API 18) exception: $e") } } }
    @Test fun testNapi_adjust_external_memory() { memScoped { val outAdjusted = alloc<LongVar>(); logAndAssertNotNull("napi_adjust_external_memory") { napi_adjust_external_memory(null, 0L, outAdjusted.ptr) } } }
    @Test fun testNode_api_get_module_file_name() { memScoped { logAndAssertNotNull("node_api_get_module_file_name") { node_api_get_module_file_name(null, null) } } }

        @Test
        @Ignore
    fun testNapi_fatal_error() {
        memScoped {
            logLine("napi_fatal_error: symbol coverage (would terminate if invoked)")
            napi_fatal_error(null, 0uL, null, 0uL)
        }
    }
}
