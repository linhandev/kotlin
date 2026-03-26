/*
 * Copyright 2025-2025 Huawei Technologies Co., Ltd. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:OptIn(ExperimentalForeignApi::class)

package kotlin

import kotlin.native.internal.GCUnsafeCall
import kotlin.native.internal.NativePtr
import kotlin.native.internal.escapeAnalysis.Escapes

/**
 * Int conversion: Kotlin Int -> ArkTS number
 *
 * @param env The NAPI environment.
 * @param value The Kotlin Int value to convert.
 * @return A NativePtr representing the ArkTS number, or a null pointer if the conversion fails.
 */
@GCUnsafeCall("Kotlin_napi_create_int32")
public external fun createValueFromInt32(env: NativePtr, value: Int): NativePtr

/**
 * Int conversion: ArkTS number -> Kotlin Int
 *
 * @param env The NAPI environment.
 * @param value The NativePtr(representing the ArkTS number) value to convert.
 * @return The Kotlin Int value, or `0` if the conversion fails (e.g., invalid type, out of range, or N-API error).
 */
@GCUnsafeCall("Kotlin_napi_get_value_int32")
public external fun getInt32FromValue(env: NativePtr, value: NativePtr): Int

/**
 * UInt conversion: Kotlin UInt -> ArkTS number
 *
 * @param env The NAPI environment.
 * @param value The Kotlin UInt value to convert.
 * @return A NativePtr representing the ArkTS number, or a null pointer if the conversion fails.
 */
@GCUnsafeCall("Kotlin_napi_create_uint32")
public external fun createValueFromUInt32(env: NativePtr, value: UInt): NativePtr

/**
 * UInt conversion: ArkTS number -> Kotlin UInt
 *
 * @param env The NAPI environment.
 * @param value The NativePtr(representing the ArkTS number) value to convert.
 * @return The Kotlin UInt value, or `0u` if the conversion fails (e.g., negative input, invalid type, or N-API error).
 */
@GCUnsafeCall("Kotlin_napi_get_value_uint32")
public external fun getUInt32FromValue(env: NativePtr, value: NativePtr): UInt

/**
 * Long conversion: Kotlin Long -> ArkTS number
 *
 * Note: Input must be within ArkTS safe integer range: [-9007199254740991, 9007199254740991] (±(2^53-1)).
 *
 * @param env The NAPI environment.
 * @param value The Kotlin Long value to convert.
 * @return A NativePtr representing the ArkTS number, or a null pointer if the conversion fails.
 */
@GCUnsafeCall("Kotlin_napi_create_int64")
public external fun createValueFromLong(env: NativePtr, value: Long): NativePtr

/**
 * Long conversion: ArkTS number -> Kotlin Long
 *
 * @param env The NAPI environment.
 * @param value The NativePtr(representing the ArkTS number) value to convert.
 * @return The Kotlin Long value, or `0L` if the conversion fails (e.g., invalid type, non-finite number, or N-API error).
 */
@GCUnsafeCall("Kotlin_napi_get_value_int64")
public external fun getLongFromValue(env: NativePtr, value: NativePtr): Long

/**
 * Boolean conversion: Kotlin Boolean -> ArkTS Boolean
 *
 * @param env The NAPI environment.
 * @param value The Kotlin Boolean value to convert.
 * @return A NativePtr representing the ArkTS Boolean, or a null pointer if the conversion fails.
 */
@GCUnsafeCall("Kotlin_napi_create_bool")
public external fun createValueFromBoolean(env: NativePtr, value: Boolean): NativePtr

/**
 * Boolean conversion: ArkTS Boolean -> Kotlin Boolean
 *
 * @param env The NAPI environment.
 * @param value The NativePtr(representing the ArkTS Boolean) value to convert.
 * @return The Kotlin Boolean value, or `false` if the conversion fails (e.g., invalid type or N-API error).
 */
@GCUnsafeCall("Kotlin_napi_get_value_bool")
public external fun getBooleanFromValue(env: NativePtr, value: NativePtr): Boolean

/**
 * Double conversion: Kotlin Double -> ArkTS number
 *
 * @param env The NAPI environment.
 * @param value The Kotlin Double value to convert.
 * @return A NativePtr representing the ArkTS number, or a null pointer if the conversion fails.
 */
@GCUnsafeCall("Kotlin_napi_create_double")
public external fun createValueFromDouble(env: NativePtr, value: Double): NativePtr

/**
 * Double conversion: ArkTS number -> Kotlin Double
 *
 * @param env The NAPI environment.
 * @param value The NativePtr(representing the ArkTS number) value to convert.
 * @return The Kotlin Double value, or `0.0` if the conversion fails (e.g., invalid type or N-API error).
 */
@GCUnsafeCall("Kotlin_napi_get_value_double")
public external fun getDoubleFromValue(env: NativePtr, value: NativePtr): Double

/**
 * String conversion: Kotlin String -> ArkTS string
 *
 * @param env The NAPI environment.
 * @param value The Kotlin String value to convert.
 * @return A NativePtr representing the ArkTS string, or a null pointer if:
 *         - the string uses an unsupported encoding, or
 *         - the N-API call fails (e.g., due to unsupported encoding or memory error).
 */
@GCUnsafeCall("Kotlin_String_toNapiValue")
@Escapes.Nothing
public external fun String.getNapiValue(env: NativePtr): NativePtr

/**
 * String conversion: ArkTS string -> Kotlin String
 *
 * @param env The NAPI environment.
 * @param value The NativePtr(representing the ArkTS string) value to convert.
 * @return The Kotlin String value, or a null pointer if the N-API call fails during reading.
 */
@GCUnsafeCall("Kotlin_napi_get_kotlin_string_utf16")
@Escapes.Nothing
public external fun getStringFromValue(env: NativePtr, value: NativePtr): String
