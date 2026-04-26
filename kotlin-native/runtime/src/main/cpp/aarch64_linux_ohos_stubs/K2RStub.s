// Copyright (C) 2026 Huawei Device Co., Ltd.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

#define cfi_adjust_cfa_offset(off)      .cfi_adjust_cfa_offset off
#define cfi_rel_offset(reg, off)        .cfi_rel_offset reg, off
#define cfi_restore(reg)                .cfi_restore reg
#define cfi_def_cfa_register(reg)       .cfi_def_cfa_register reg

////////////////////////////////////////////////////////////////////////////////
// CalleeSavedRegistersStub simply forwards arguments passed by runtime, i.e., arguments for compiled method are passed
// according to C/C++ calling convention, which usually means efficiency.
////////////////////////////////////////////////////////////////////////////////

#define StubFrameContextSize      (8 * 14)
#define StubCalleeSaveAreaSize (8 * 4)

// the frame layout of stack(growing downwards) after MCC_C2NStub frame is built looks like:

// caller sp  -->  | arg7         |
// callee saved    | avalid null  |
//                 | x28          |
//                 | x27          |
//                 | x26          |
//                 | x25          |
//                 | x24          |
//                 | x23          |
//                 | x22          |
//                 | x21          |
//                 | x20          |
// callee saved    | x19          |
//                 | x30          |
//   stub fp  -->  | caller fp    |
//                 |  ...         |
//                 | arg10        |
//                 | arg9         |
//                 | arg8         |
//   stub sp  -->  | arg7         | <== MCC_C2NStub frame ends at here

.macro CalleeSavedRegistersStub, funcName
    .cfi_startproc
    stp  x29, x30, [sp,  #-112]!
    .cfi_adjust_cfa_offset 112
    .cfi_rel_offset x29, 0
    .cfi_rel_offset x30, 8

    mov  x29, sp
    .cfi_def_cfa_register sp

    // save all used callee-saved registers.
    stp  x19, x20, [sp, #32]
    .cfi_rel_offset x19, 32
    .cfi_rel_offset x20, 32+8

    stp  x21, x22, [sp, #32+0x10]
    .cfi_rel_offset x21, 32+0x10
    .cfi_rel_offset x22, 32+0x18

    stp  x23, x24, [sp, #32+0x20]
    .cfi_rel_offset x23, 32+0x20
    .cfi_rel_offset x24, 32+0x28

    stp  x25, x26, [sp, #32+0x30]
    .cfi_rel_offset x25, 32+0x30
    .cfi_rel_offset x26, 32+0x38

    stp  x27, x28, [sp, #32+0x40]
    .cfi_rel_offset x27, 32+0x40
    .cfi_rel_offset x28, 32+0x48

    bl   \funcName
    str  x0,  [sp, #32+0x48]

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #32]
    .cfi_restore x19
    .cfi_restore x20
    ldp  x21, x22, [sp, #32+0x10]
    .cfi_restore x21
    .cfi_restore x22
    ldp  x23, x24, [sp, #32+0x20]
    .cfi_restore x23
    .cfi_restore x24
    ldp  x25, x26, [sp, #32+0x30]
    .cfi_restore x25
    .cfi_restore x26
    ldr  x27, [sp, #32+0x40]
    .cfi_restore x27
    ldr  x0,  [sp, #32+0x48]
    .cfi_restore x0

    ldp  x29, x30, [sp], #112
    .cfi_adjust_cfa_offset -112
    .cfi_restore x29
    .cfi_restore x30
    ret
    .cfi_endproc
.endm

.macro CalleeSavedRegistersStubNew, funcName
    .cfi_startproc
    ldp  x29, x30, [sp], #16
    stp  x29, x30, [sp,  #-112]!
    .cfi_adjust_cfa_offset 112
    .cfi_rel_offset x29, 0
    .cfi_rel_offset x30, 8

    mov  x29, sp
    .cfi_def_cfa_register sp

    // save all used callee-saved registers.
    stp  x19, x20, [sp, #32]
    .cfi_rel_offset x19, 32
    .cfi_rel_offset x20, 32+8

    stp  x21, x22, [sp, #32+0x10]
    .cfi_rel_offset x21, 32+0x10
    .cfi_rel_offset x22, 32+0x18

    stp  x23, x24, [sp, #32+0x20]
    .cfi_rel_offset x23, 32+0x20
    .cfi_rel_offset x24, 32+0x28

    stp  x25, x26, [sp, #32+0x30]
    .cfi_rel_offset x25, 32+0x30
    .cfi_rel_offset x26, 32+0x38

    stp  x27, x28, [sp, #32+0x40]
    .cfi_rel_offset x27, 32+0x40
    .cfi_rel_offset x28, 32+0x48

    bl   \funcName
    str  x0,  [sp, #32+0x48]

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #32]
    .cfi_restore x19
    .cfi_restore x20
    ldp  x21, x22, [sp, #32+0x10]
    .cfi_restore x21
    .cfi_restore x22
    ldp  x23, x24, [sp, #32+0x20]
    .cfi_restore x23
    .cfi_restore x24
    ldp  x25, x26, [sp, #32+0x30]
    .cfi_restore x25
    .cfi_restore x26
    ldr  x27, [sp, #32+0x40]
    .cfi_restore x27
    ldr  x0,  [sp, #32+0x48]
    .cfi_restore x0

    ldp  x29, x30, [sp], #112
    .cfi_adjust_cfa_offset -112
    .cfi_restore x29
    .cfi_restore x30
    ret
    .cfi_endproc
.endm

    .global unwindPCForK2RStubStart
unwindPCForK2RStubStart:

    .text
    .align 2
    .global AllocInstanceStub
AllocInstanceStub:
    CalleeSavedRegistersStub AllocInstance

    .global Kotlin_native_internal_GC_collectStub
Kotlin_native_internal_GC_collectStub:
    CalleeSavedRegistersStub Kotlin_native_internal_GC_collect

    .global Kotlin_Worker_consumeFutureStub
Kotlin_Worker_consumeFutureStub:
    CalleeSavedRegistersStub Kotlin_Worker_consumeFuture

    .global Kotlin_io_Console_printStub
Kotlin_io_Console_printStub:
    CalleeSavedRegistersStub Kotlin_io_Console_print

    .global Kotlin_getStackTraceStringsStub
Kotlin_getStackTraceStringsStub:
    CalleeSavedRegistersStub Kotlin_getStackTraceStrings

    .global Kotlin_Internal_GC_GCInfoBuilder_FillStub
Kotlin_Internal_GC_GCInfoBuilder_FillStub:
    CalleeSavedRegistersStub Kotlin_Internal_GC_GCInfoBuilder_Fill

    .global Kotlin_CharArray_copyOfStub
Kotlin_CharArray_copyOfStub:
    CalleeSavedRegistersStub Kotlin_CharArray_copyOf

    .global Kotlin_ImmutableBlob_toByteArrayStub
Kotlin_ImmutableBlob_toByteArrayStub:
    CalleeSavedRegistersStub Kotlin_ImmutableBlob_toByteArray

    .global Kotlin_io_Console_printToStdErrStub
Kotlin_io_Console_printToStdErrStub:
    CalleeSavedRegistersStub Kotlin_io_Console_printToStdErr

    .global Kotlin_io_Console_printlnStub
Kotlin_io_Console_printlnStub:
    CalleeSavedRegistersStub Kotlin_io_Console_println

    .global Kotlin_io_Console_printlnToStdErrStub
Kotlin_io_Console_printlnToStdErrStub:
    CalleeSavedRegistersStub Kotlin_io_Console_printlnToStdErr

    .global Kotlin_io_Console_println0Stub
Kotlin_io_Console_println0Stub:
    CalleeSavedRegistersStub Kotlin_io_Console_println0

    .global Kotlin_io_Console_println0ToStdErrStub
Kotlin_io_Console_println0ToStdErrStub:
    CalleeSavedRegistersStub Kotlin_io_Console_println0ToStdErr

    .global Kotlin_io_Console_readLineStub
Kotlin_io_Console_readLineStub:
    CalleeSavedRegistersStub Kotlin_io_Console_readLine

    .global Kotlin_io_Console_readlnOrNullStub
Kotlin_io_Console_readlnOrNullStub:
    CalleeSavedRegistersStub Kotlin_io_Console_readlnOrNull

    .global Kotlin_CString_toKStringFromUtf8ImplStub
Kotlin_CString_toKStringFromUtf8ImplStub:
    CalleeSavedRegistersStub Kotlin_CString_toKStringFromUtf8Impl

    .global CreateStringFromCStringStub
CreateStringFromCStringStub:
    CalleeSavedRegistersStub CreateStringFromCString

    .global CreateStringFromUtf8Stub
CreateStringFromUtf8Stub:
    CalleeSavedRegistersStub CreateStringFromUtf8

    .global Kotlin_String_replaceStub
Kotlin_String_replaceStub:
    CalleeSavedRegistersStub Kotlin_String_replace

    .global Kotlin_String_plusImplStub
Kotlin_String_plusImplStub:
    CalleeSavedRegistersStub Kotlin_String_plusImpl

    .global Kotlin_String_unsafeStringFromCharArrayStub
Kotlin_String_unsafeStringFromCharArrayStub:
    CalleeSavedRegistersStub Kotlin_String_unsafeStringFromCharArray

    .global Kotlin_String_subSequenceStub
Kotlin_String_subSequenceStub:
    CalleeSavedRegistersStub Kotlin_String_subSequence

    .global Kotlin_ByteArray_unsafeStringFromUtf8OrThrowStub
Kotlin_ByteArray_unsafeStringFromUtf8OrThrowStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_unsafeStringFromUtf8OrThrow

    .global Kotlin_ByteArray_unsafeStringFromUtf8Stub
Kotlin_ByteArray_unsafeStringFromUtf8Stub:
    CalleeSavedRegistersStub Kotlin_ByteArray_unsafeStringFromUtf8

    .global Kotlin_String_unsafeStringToUtf8Stub
Kotlin_String_unsafeStringToUtf8Stub:
    CalleeSavedRegistersStub Kotlin_String_unsafeStringToUtf8

    .global Kotlin_String_unsafeStringToUtf8OrThrowStub
Kotlin_String_unsafeStringToUtf8OrThrowStub:
    CalleeSavedRegistersStub Kotlin_String_unsafeStringToUtf8OrThrow

    .global Kotlin_Any_hashCodeStub
Kotlin_Any_hashCodeStub:
    CalleeSavedRegistersStub Kotlin_Any_hashCode

    .global Kotlin_getCurrentStackTraceStub
Kotlin_getCurrentStackTraceStub:
    CalleeSavedRegistersStub Kotlin_getCurrentStackTrace

    .global Kotlin_Uuid_getRandomBytesStub
Kotlin_Uuid_getRandomBytesStub:
    CalleeSavedRegistersStub Kotlin_Uuid_getRandomBytes

    .global Kotlin_text_regex_getDecompositionInternalStub
Kotlin_text_regex_getDecompositionInternalStub:
    CalleeSavedRegistersStub Kotlin_text_regex_getDecompositionInternal

    .global Kotlin_Byte_toStringStub
Kotlin_Byte_toStringStub:
    CalleeSavedRegistersStub Kotlin_Byte_toString

    .global Kotlin_Char_toStringStub
Kotlin_Char_toStringStub:
    CalleeSavedRegistersStub Kotlin_Char_toString

    .global Kotlin_Short_toStringStub
Kotlin_Short_toStringStub:
    CalleeSavedRegistersStub Kotlin_Short_toString

    .global Kotlin_Int_toStringStub
Kotlin_Int_toStringStub:
    CalleeSavedRegistersStub Kotlin_Int_toString

    .global Kotlin_Int_toStringRadixStub
Kotlin_Int_toStringRadixStub:
    CalleeSavedRegistersStub Kotlin_Int_toStringRadix

    .global Kotlin_Long_toStringStub
Kotlin_Long_toStringStub:
    CalleeSavedRegistersStub Kotlin_Long_toString

    .global Kotlin_DurationValue_formatToExactDecimalsStub
Kotlin_DurationValue_formatToExactDecimalsStub:
    CalleeSavedRegistersStub Kotlin_DurationValue_formatToExactDecimals

    .global Kotlin_Worker_startInternalStub
Kotlin_Worker_startInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_startInternal

    .global Kotlin_Worker_currentInternalStub
Kotlin_Worker_currentInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_currentInternal

    .global Kotlin_Worker_requestTerminationWorkerInternalStub
Kotlin_Worker_requestTerminationWorkerInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_requestTerminationWorkerInternal

    .global Kotlin_Worker_executeInternalStub
Kotlin_Worker_executeInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_executeInternal

    .global Kotlin_Worker_executeAfterInternalStub
Kotlin_Worker_executeAfterInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_executeAfterInternal

    .global Kotlin_Worker_processQueueInternalStub
Kotlin_Worker_processQueueInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_processQueueInternal

    .global Kotlin_Worker_parkInternalStub
Kotlin_Worker_parkInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_parkInternal

    .global Kotlin_Worker_getNameInternalStub
Kotlin_Worker_getNameInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_getNameInternal

    .global Kotlin_Worker_stateOfFutureStub
Kotlin_Worker_stateOfFutureStub:
    CalleeSavedRegistersStub Kotlin_Worker_stateOfFuture

    .global Kotlin_Worker_waitForAnyFutureStub
Kotlin_Worker_waitForAnyFutureStub:
    CalleeSavedRegistersStub Kotlin_Worker_waitForAnyFuture

    .global Kotlin_Worker_versionTokenStub
Kotlin_Worker_versionTokenStub:
    CalleeSavedRegistersStub Kotlin_Worker_versionToken

    .global Kotlin_Worker_waitTerminationStub
Kotlin_Worker_waitTerminationStub:
    CalleeSavedRegistersStub Kotlin_Worker_waitTermination

    .global Kotlin_Worker_getPlatformThreadIdInternalStub
Kotlin_Worker_getPlatformThreadIdInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_getPlatformThreadIdInternal

    .global Kotlin_Worker_getActiveWorkersInternalStub
Kotlin_Worker_getActiveWorkersInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_getActiveWorkersInternal

    .global AllocArrayInstanceStub
AllocArrayInstanceStub:
    CalleeSavedRegistersStub AllocArrayInstance

    .global Kotlin_native_internal_GC_scheduleStub
Kotlin_native_internal_GC_scheduleStub:
    CalleeSavedRegistersStub Kotlin_native_internal_GC_schedule

    .global PerformFullGCStub
PerformFullGCStub:
    CalleeSavedRegistersStub PerformFullGC

    .global Kotlin_mm_switchThreadStateNativeStub
Kotlin_mm_switchThreadStateNativeStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateNative

    .global Kotlin_mm_switchThreadStateNative_debugStub
Kotlin_mm_switchThreadStateNative_debugStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateNative_debug

    .global Kotlin_mm_switchThreadStateRunnableStub
Kotlin_mm_switchThreadStateRunnableStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateRunnable

    .global Kotlin_mm_switchThreadStateRunnable_debugStub
Kotlin_mm_switchThreadStateRunnable_debugStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateRunnable_debug

    .global Konan_getWeakReferenceImplStub
Konan_getWeakReferenceImplStub:
    CalleeSavedRegistersStub Konan_getWeakReferenceImpl

    .global Kotlin_Long_toStringRadixStub
Kotlin_Long_toStringRadixStub:
    CalleeSavedRegistersStub Kotlin_Long_toStringRadix

    .global Kotlin_getEmptyStackTraceStub
Kotlin_getEmptyStackTraceStub:
    CalleeSavedRegistersStub Kotlin_getEmptyStackTrace

    .global CallInitGlobalPossiblyLockStub
CallInitGlobalPossiblyLockStub:
    CalleeSavedRegistersStub CallInitGlobalPossiblyLock

    .global Kotlin_TypeInfo_findAssociatedObjectStub
Kotlin_TypeInfo_findAssociatedObjectStub:
    CalleeSavedRegistersStub Kotlin_TypeInfo_findAssociatedObject

    .global CallInitThreadLocalStub
CallInitThreadLocalStub:
    CalleeSavedRegistersStub CallInitThreadLocal

    .global Kotlin_Array_getStub
Kotlin_Array_getStub:
    CalleeSavedRegistersStub Kotlin_Array_get

    .global Kotlin_Array_get_without_BoundCheckStub
Kotlin_Array_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_Array_get_without_BoundCheck

    .global Kotlin_Array_setStub
Kotlin_Array_setStub:
    CalleeSavedRegistersStub Kotlin_Array_set

    .global Kotlin_Array_set_without_BoundCheckStub
Kotlin_Array_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_Array_set_without_BoundCheck

    .global Kotlin_Array_fillImplStub
Kotlin_Array_fillImplStub:
    CalleeSavedRegistersStub Kotlin_Array_fillImpl

    .global Kotlin_Array_copyImplStub
Kotlin_Array_copyImplStub:
    CalleeSavedRegistersStub Kotlin_Array_copyImpl

    .global Kotlin_ByteArray_getStub
Kotlin_ByteArray_getStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_get

    .global Kotlin_ByteArray_get_without_BoundCheckStub
Kotlin_ByteArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_get_without_BoundCheck

    .global Kotlin_ByteArray_setStub
Kotlin_ByteArray_setStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_set

    .global Kotlin_ByteArray_set_without_BoundCheckStub
Kotlin_ByteArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_set_without_BoundCheck

    .global Kotlin_ByteArray_getCharAtStub
Kotlin_ByteArray_getCharAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getCharAt

    .global Kotlin_ByteArray_getShortAtStub
Kotlin_ByteArray_getShortAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getShortAt

    .global Kotlin_ByteArray_getIntAtStub
Kotlin_ByteArray_getIntAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getIntAt

    .global Kotlin_ByteArray_getLongAtStub
Kotlin_ByteArray_getLongAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getLongAt

    .global Kotlin_ByteArray_getFloatAtStub
Kotlin_ByteArray_getFloatAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getFloatAt

    .global Kotlin_ByteArray_getDoubleAtStub
Kotlin_ByteArray_getDoubleAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getDoubleAt

    .global Kotlin_ByteArray_setCharAtStub
Kotlin_ByteArray_setCharAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setCharAt

    .global Kotlin_ByteArray_setShortAtStub
Kotlin_ByteArray_setShortAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setShortAt

    .global Kotlin_ByteArray_setIntAtStub
Kotlin_ByteArray_setIntAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setIntAt

    .global Kotlin_ByteArray_setLongAtStub
Kotlin_ByteArray_setLongAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setLongAt

    .global Kotlin_ByteArray_setFloatAtStub
Kotlin_ByteArray_setFloatAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setFloatAt

    .global Kotlin_ByteArray_setDoubleAtStub
Kotlin_ByteArray_setDoubleAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setDoubleAt

    .global Kotlin_CharArray_getStub
Kotlin_CharArray_getStub:
    CalleeSavedRegistersStub Kotlin_CharArray_get

    .global Kotlin_CharArray_get_without_BoundCheckStub
Kotlin_CharArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_CharArray_get_without_BoundCheck

    .global Kotlin_CharArray_setStub
Kotlin_CharArray_setStub:
    CalleeSavedRegistersStub Kotlin_CharArray_set

    .global Kotlin_CharArray_set_without_BoundCheckStub
Kotlin_CharArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_CharArray_set_without_BoundCheck

    .global Kotlin_ShortArray_getStub
Kotlin_ShortArray_getStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_get

    .global Kotlin_ShortArray_get_without_BoundCheckStub
Kotlin_ShortArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_get_without_BoundCheck

    .global Kotlin_ShortArray_setStub
Kotlin_ShortArray_setStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_set

    .global Kotlin_ShortArray_set_without_BoundCheckStub
Kotlin_ShortArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_set_without_BoundCheck

    .global Kotlin_IntArray_getStub
Kotlin_IntArray_getStub:
    CalleeSavedRegistersStub Kotlin_IntArray_get

    .global Kotlin_IntArray_get_without_BoundCheckStub
Kotlin_IntArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_IntArray_get_without_BoundCheck

    .global Kotlin_IntArray_setStub
Kotlin_IntArray_setStub:
    CalleeSavedRegistersStub Kotlin_IntArray_set

    .global Kotlin_IntArray_set_without_BoundCheckStub
Kotlin_IntArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_IntArray_set_without_BoundCheck

    .global Kotlin_ByteArray_fillImplStub
Kotlin_ByteArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_fillImpl

    .global Kotlin_ShortArray_fillImplStub
Kotlin_ShortArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_fillImpl

    .global Kotlin_CharArray_fillImplStub
Kotlin_CharArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_CharArray_fillImpl

    .global Kotlin_IntArray_fillImplStub
Kotlin_IntArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_IntArray_fillImpl

    .global Kotlin_LongArray_fillImplStub
Kotlin_LongArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_LongArray_fillImpl

    .global Kotlin_FloatArray_fillImplStub
Kotlin_FloatArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_fillImpl

    .global Kotlin_DoubleArray_fillImplStub
Kotlin_DoubleArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_fillImpl

    .global Kotlin_BooleanArray_fillImplStub
Kotlin_BooleanArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_fillImpl

    .global Kotlin_ByteArray_copyImplStub
Kotlin_ByteArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_copyImpl

    .global Kotlin_ShortArray_copyImplStub
Kotlin_ShortArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_copyImpl

    .global Kotlin_CharArray_copyImplStub
Kotlin_CharArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_CharArray_copyImpl

    .global Kotlin_IntArray_copyImplStub
Kotlin_IntArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_IntArray_copyImpl

    .global Kotlin_LongArray_copyImplStub
Kotlin_LongArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_LongArray_copyImpl

    .global Kotlin_FloatArray_copyImplStub
Kotlin_FloatArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_copyImpl

    .global Kotlin_DoubleArray_copyImplStub
Kotlin_DoubleArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_copyImpl

    .global Kotlin_BooleanArray_copyImplStub
Kotlin_BooleanArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_copyImpl

    .global Kotlin_LongArray_getStub
Kotlin_LongArray_getStub:
    CalleeSavedRegistersStub Kotlin_LongArray_get

    .global Kotlin_LongArray_get_without_BoundCheckStub
Kotlin_LongArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_LongArray_get_without_BoundCheck

    .global Kotlin_LongArray_setStub
Kotlin_LongArray_setStub:
    CalleeSavedRegistersStub Kotlin_LongArray_set

    .global Kotlin_LongArray_set_without_BoundCheckStub
Kotlin_LongArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_LongArray_set_without_BoundCheck

    .global Kotlin_FloatArray_getStub
Kotlin_FloatArray_getStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_get

    .global Kotlin_FloatArray_get_without_BoundCheckStub
Kotlin_FloatArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_get_without_BoundCheck

    .global Kotlin_FloatArray_setStub
Kotlin_FloatArray_setStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_set

    .global Kotlin_FloatArray_set_without_BoundCheckStub
Kotlin_FloatArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_set_without_BoundCheck

    .global Kotlin_DoubleArray_getStub
Kotlin_DoubleArray_getStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_get

    .global Kotlin_DoubleArray_get_without_BoundCheckStub
Kotlin_DoubleArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_get_without_BoundCheck

    .global Kotlin_DoubleArray_setStub
Kotlin_DoubleArray_setStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_set

    .global Kotlin_DoubleArray_set_without_BoundCheckStub
Kotlin_DoubleArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_set_without_BoundCheck

    .global Kotlin_BooleanArray_getStub
Kotlin_BooleanArray_getStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_get

    .global Kotlin_BooleanArray_get_without_BoundCheckStub
Kotlin_BooleanArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_get_without_BoundCheck

    .global Kotlin_BooleanArray_setStub
Kotlin_BooleanArray_setStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_set

    .global Kotlin_BooleanArray_set_without_BoundCheckStub
Kotlin_BooleanArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_set_without_BoundCheck

    .global Kotlin_NativePtrArray_setStub
Kotlin_NativePtrArray_setStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_set

    .global Kotlin_NativePtrArray_get_without_BoundCheckStub
Kotlin_NativePtrArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_get_without_BoundCheck

    .global Kotlin_NativePtrArray_getStub
Kotlin_NativePtrArray_getStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_get

    .global Kotlin_NativePtrArray_set_without_BoundCheckStub
Kotlin_NativePtrArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_set_without_BoundCheck

    .global Kotlin_ImmutableBlob_asCPointerImplStub
Kotlin_ImmutableBlob_asCPointerImplStub:
    CalleeSavedRegistersStub Kotlin_ImmutableBlob_asCPointerImpl

    .global Kotlin_Arrays_getByteArrayAddressOfElementStub
Kotlin_Arrays_getByteArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getByteArrayAddressOfElement

    .global Kotlin_Arrays_getCharArrayAddressOfElementStub
Kotlin_Arrays_getCharArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getCharArrayAddressOfElement

    .global Kotlin_Arrays_getStringAddressOfElementStub
Kotlin_Arrays_getStringAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getStringAddressOfElement

    .global Kotlin_Arrays_getShortArrayAddressOfElementStub
Kotlin_Arrays_getShortArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getShortArrayAddressOfElement

    .global Kotlin_Arrays_getIntArrayAddressOfElementStub
Kotlin_Arrays_getIntArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getIntArrayAddressOfElement

    .global Kotlin_Arrays_getLongArrayAddressOfElementStub
Kotlin_Arrays_getLongArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getLongArrayAddressOfElement

    .global Kotlin_Arrays_getFloatArrayAddressOfElementStub
Kotlin_Arrays_getFloatArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getFloatArrayAddressOfElement

    .global Kotlin_Arrays_getDoubleArrayAddressOfElementStub
Kotlin_Arrays_getDoubleArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getDoubleArrayAddressOfElement

    .global Kotlin_String_compareToStub
Kotlin_String_compareToStub:
    CalleeSavedRegistersStub Kotlin_String_compareTo

    .global Kotlin_String_getStub
Kotlin_String_getStub:
    CalleeSavedRegistersStub Kotlin_String_get

    .global Kotlin_native_FloatingPointParser_parseFloatImplStub
Kotlin_native_FloatingPointParser_parseFloatImplStub:
    CalleeSavedRegistersStub Kotlin_native_FloatingPointParser_parseFloatImpl

    .global Kotlin_Worker_invokeCFunctionStub
Kotlin_Worker_invokeCFunctionStub:
    CalleeSavedRegistersStub Kotlin_Worker_invokeCFunction

    .global CreateStringFromUtf16Stub
CreateStringFromUtf16Stub:
    CalleeSavedRegistersStub CreateStringFromUtf16

    .global CreateStringFromUtf8OrThrowStub
CreateStringFromUtf8OrThrowStub:
    CalleeSavedRegistersStub CreateStringFromUtf8OrThrow

    .global CreateUninitializedStringStub
CreateUninitializedStringStub:
    CalleeSavedRegistersStub CreateUninitializedString

    .global slowPathStub
slowPathStub:
    .cfi_startproc
    stp  x29, x30, [sp,  #-112]!
    .cfi_adjust_cfa_offset 112
    .cfi_rel_offset x29, 0
    .cfi_rel_offset x30, 8

    mov  x29, sp
    .cfi_def_cfa_register sp

    // save all used callee-saved registers.
    stp  x19, x20, [sp, #32]
    .cfi_rel_offset x19, 32
    .cfi_rel_offset x20, 32+8

    stp  x21, x22, [sp, #32+0x10]
    .cfi_rel_offset x21, 32+0x10
    .cfi_rel_offset x22, 32+0x18

    stp  x23, x24, [sp, #32+0x20]
    .cfi_rel_offset x23, 32+0x20
    .cfi_rel_offset x24, 32+0x28

    stp  x25, x26, [sp, #32+0x30]
    .cfi_rel_offset x25, 32+0x30
    .cfi_rel_offset x26, 32+0x38

    stp  x27, x28, [sp, #32+0x40]
    .cfi_rel_offset x27, 32+0x40
    .cfi_rel_offset x28, 32+0x48

    bl   CslowPath
    str  x0,  [sp, #32+0x48]

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #32]
    .cfi_restore x19
    .cfi_restore x20
    ldp  x21, x22, [sp, #32+0x10]
    .cfi_restore x21
    .cfi_restore x22
    ldp  x23, x24, [sp, #32+0x20]
    .cfi_restore x23
    .cfi_restore x24
    ldp  x25, x26, [sp, #32+0x30]
    .cfi_restore x25
    .cfi_restore x26
    ldr  x27, [sp, #32+0x40]
    .cfi_restore x27
    ldr  x0,  [sp, #32+0x48]
    .cfi_restore x0

    ldp  x29, x30, [sp], #112
    .cfi_adjust_cfa_offset -112
    .cfi_restore x29
    .cfi_restore x30
    ret
    .cfi_endproc

    .global unwindPCForK2RStubEnd
unwindPCForK2RStubEnd:

