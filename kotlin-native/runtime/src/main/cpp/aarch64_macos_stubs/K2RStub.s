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
    stp  x29, x30, [sp,  #-StubFrameContextSize]!
    cfi_adjust_cfa_offset (StubFrameContextSize)
    cfi_rel_offset (x29, 0)
    cfi_rel_offset (x30, 8)

    mov  x29, sp
    cfi_def_cfa_register (sp)

    // save all used callee-saved registers.
    stp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_rel_offset (x19, StubCalleeSaveAreaSize)
    cfi_rel_offset (x20, StubCalleeSaveAreaSize+8)

    stp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_rel_offset (x21, StubCalleeSaveAreaSize+0x10)
    cfi_rel_offset (x22, StubCalleeSaveAreaSize+0x18)

    stp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_rel_offset (x23, StubCalleeSaveAreaSize+0x20)
    cfi_rel_offset (x24, StubCalleeSaveAreaSize+0x28)

    stp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_rel_offset (x25, StubCalleeSaveAreaSize+0x30)
    cfi_rel_offset (x26, StubCalleeSaveAreaSize+0x38)

    stp  x27, x28, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_rel_offset (x27, StubCalleeSaveAreaSize+0x40)
    cfi_rel_offset (x28, StubCalleeSaveAreaSize+0x48)

    bl   _\funcName
    str  x0,  [sp, #StubCalleeSaveAreaSize+0x48]

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_restore (x19)
    cfi_restore (x20)
    ldp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_restore (x21)
    cfi_restore (x22)
    ldp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_restore (x23)
    cfi_restore (x24)
    ldp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_restore (x25)
    cfi_restore (x26)
    ldr  x27, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_restore (x27)
    ldr  x0,  [sp, #StubCalleeSaveAreaSize+0x48]
    cfi_restore (x0)

    ldp  x29, x30, [sp], #StubFrameContextSize
    cfi_adjust_cfa_offset (-StubFrameContextSize)
    cfi_restore (x29)
    cfi_restore (x30)
    ret
    .cfi_endproc
.endm

.macro CalleeSavedRegistersStubNew, funcName
    .cfi_startproc
    ldp  x29, x30, [sp], #16
    stp  x29, x30, [sp,  #-StubFrameContextSize]!
    cfi_adjust_cfa_offset (StubFrameContextSize)
    cfi_rel_offset (x29, 0)
    cfi_rel_offset (x30, 8)

    mov  x29, sp
    cfi_def_cfa_register (sp)

    // save all used callee-saved registers.
    stp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_rel_offset (x19, StubCalleeSaveAreaSize)
    cfi_rel_offset (x20, StubCalleeSaveAreaSize+8)

    stp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_rel_offset (x21, StubCalleeSaveAreaSize+0x10)
    cfi_rel_offset (x22, StubCalleeSaveAreaSize+0x18)

    stp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_rel_offset (x23, StubCalleeSaveAreaSize+0x20)
    cfi_rel_offset (x24, StubCalleeSaveAreaSize+0x28)

    stp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_rel_offset (x25, StubCalleeSaveAreaSize+0x30)
    cfi_rel_offset (x26, StubCalleeSaveAreaSize+0x38)

    stp  x27, x28, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_rel_offset (x27, StubCalleeSaveAreaSize+0x40)
    cfi_rel_offset (x28, StubCalleeSaveAreaSize+0x48)

    bl   _\funcName
    str  x0,  [sp, #StubCalleeSaveAreaSize+0x48]

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_restore (x19)
    cfi_restore (x20)
    ldp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_restore (x21)
    cfi_restore (x22)
    ldp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_restore (x23)
    cfi_restore (x24)
    ldp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_restore (x25)
    cfi_restore (x26)
    ldr  x27, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_restore (x27)
    ldr  x0,  [sp, #StubCalleeSaveAreaSize+0x48]
    cfi_restore (x0)

    ldp  x29, x30, [sp], #StubFrameContextSize
    cfi_adjust_cfa_offset (-StubFrameContextSize)
    cfi_restore (x29)
    cfi_restore (x30)
    ret
    .cfi_endproc
.endm

    .global _unwindPCForK2RStubStart
_unwindPCForK2RStubStart:

    .text
    .align 2
    .global _AllocInstanceStub
_AllocInstanceStub:
    CalleeSavedRegistersStub AllocInstance

    .global _Kotlin_native_internal_GC_collectStub
_Kotlin_native_internal_GC_collectStub:
    CalleeSavedRegistersStub Kotlin_native_internal_GC_collect

    .global _Kotlin_mm_safePointFunctionPrologueStub
_Kotlin_mm_safePointFunctionPrologueStub:
    CalleeSavedRegistersStub Kotlin_mm_safePointFunctionPrologue

    .global _Kotlin_mm_safePointWhileLoopBodyStub
_Kotlin_mm_safePointWhileLoopBodyStub:
    CalleeSavedRegistersStub Kotlin_mm_safePointWhileLoopBody

    .global _Kotlin_Worker_consumeFutureStub
_Kotlin_Worker_consumeFutureStub:
    CalleeSavedRegistersStub Kotlin_Worker_consumeFuture

    .global _Kotlin_io_Console_printStub
_Kotlin_io_Console_printStub:
    CalleeSavedRegistersStub Kotlin_io_Console_print

    .global _Kotlin_getStackTraceStringsStub
_Kotlin_getStackTraceStringsStub:
    CalleeSavedRegistersStub Kotlin_getStackTraceStrings

    .global _Kotlin_Internal_GC_GCInfoBuilder_FillStub
_Kotlin_Internal_GC_GCInfoBuilder_FillStub:
    CalleeSavedRegistersStub Kotlin_Internal_GC_GCInfoBuilder_Fill

    .global _Kotlin_CharArray_copyOfStub
_Kotlin_CharArray_copyOfStub:
    CalleeSavedRegistersStub Kotlin_CharArray_copyOf

    .global _Kotlin_ImmutableBlob_toByteArrayStub
_Kotlin_ImmutableBlob_toByteArrayStub:
    CalleeSavedRegistersStub Kotlin_ImmutableBlob_toByteArray

    .global _Kotlin_io_Console_printToStdErrStub
_Kotlin_io_Console_printToStdErrStub:
    CalleeSavedRegistersStub Kotlin_io_Console_printToStdErr

    .global _Kotlin_io_Console_printlnStub
_Kotlin_io_Console_printlnStub:
    CalleeSavedRegistersStub Kotlin_io_Console_println

    .global _Kotlin_io_Console_printlnToStdErrStub
_Kotlin_io_Console_printlnToStdErrStub:
    CalleeSavedRegistersStub Kotlin_io_Console_printlnToStdErr

    .global _Kotlin_io_Console_println0Stub
_Kotlin_io_Console_println0Stub:
    CalleeSavedRegistersStub Kotlin_io_Console_println0

    .global _Kotlin_io_Console_println0ToStdErrStub
_Kotlin_io_Console_println0ToStdErrStub:
    CalleeSavedRegistersStub Kotlin_io_Console_println0ToStdErr

    .global _Kotlin_io_Console_readLineStub
_Kotlin_io_Console_readLineStub:
    CalleeSavedRegistersStub Kotlin_io_Console_readLine

    .global _Kotlin_io_Console_readlnOrNullStub
_Kotlin_io_Console_readlnOrNullStub:
    CalleeSavedRegistersStub Kotlin_io_Console_readlnOrNull

    .global _Kotlin_CString_toKStringFromUtf8ImplStub
_Kotlin_CString_toKStringFromUtf8ImplStub:
    CalleeSavedRegistersStub Kotlin_CString_toKStringFromUtf8Impl

    .global _CreateStringFromCStringStub
_CreateStringFromCStringStub:
    CalleeSavedRegistersStub CreateStringFromCString

    .global _CreateStringFromUtf8Stub
_CreateStringFromUtf8Stub:
    CalleeSavedRegistersStub CreateStringFromUtf8

    .global _Kotlin_String_replaceStub
_Kotlin_String_replaceStub:
    CalleeSavedRegistersStub Kotlin_String_replace

    .global _Kotlin_String_plusImplStub
_Kotlin_String_plusImplStub:
    CalleeSavedRegistersStub Kotlin_String_plusImpl

    .global _Kotlin_String_unsafeStringFromCharArrayStub
_Kotlin_String_unsafeStringFromCharArrayStub:
    CalleeSavedRegistersStub Kotlin_String_unsafeStringFromCharArray

    .global _Kotlin_String_subSequenceStub
_Kotlin_String_subSequenceStub:
    CalleeSavedRegistersStub Kotlin_String_subSequence

    .global _Kotlin_ByteArray_unsafeStringFromUtf8OrThrowStub
_Kotlin_ByteArray_unsafeStringFromUtf8OrThrowStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_unsafeStringFromUtf8OrThrow

    .global _Kotlin_ByteArray_unsafeStringFromUtf8Stub
_Kotlin_ByteArray_unsafeStringFromUtf8Stub:
    CalleeSavedRegistersStub Kotlin_ByteArray_unsafeStringFromUtf8

    .global _Kotlin_String_unsafeStringToUtf8Stub
_Kotlin_String_unsafeStringToUtf8Stub:
    CalleeSavedRegistersStub Kotlin_String_unsafeStringToUtf8

    .global _Kotlin_String_unsafeStringToUtf8OrThrowStub
_Kotlin_String_unsafeStringToUtf8OrThrowStub:
    CalleeSavedRegistersStub Kotlin_String_unsafeStringToUtf8OrThrow

    .global _Kotlin_Any_hashCodeStub
_Kotlin_Any_hashCodeStub:
    CalleeSavedRegistersStub Kotlin_Any_hashCode

    .global _Kotlin_getCurrentStackTraceStub
_Kotlin_getCurrentStackTraceStub:
    CalleeSavedRegistersStub Kotlin_getCurrentStackTrace

    .global _Kotlin_Uuid_getRandomBytesStub
_Kotlin_Uuid_getRandomBytesStub:
    CalleeSavedRegistersStub Kotlin_Uuid_getRandomBytes

    .global _Kotlin_text_regex_getDecompositionInternalStub
_Kotlin_text_regex_getDecompositionInternalStub:
    CalleeSavedRegistersStub Kotlin_text_regex_getDecompositionInternal

    .global _Kotlin_Byte_toStringStub
_Kotlin_Byte_toStringStub:
    CalleeSavedRegistersStub Kotlin_Byte_toString

    .global _Kotlin_Char_toStringStub
_Kotlin_Char_toStringStub:
    CalleeSavedRegistersStub Kotlin_Char_toString

    .global _Kotlin_Short_toStringStub
_Kotlin_Short_toStringStub:
    CalleeSavedRegistersStub Kotlin_Short_toString

    .global _Kotlin_Int_toStringStub
_Kotlin_Int_toStringStub:
    CalleeSavedRegistersStub Kotlin_Int_toString

    .global _Kotlin_Int_toStringRadixStub
_Kotlin_Int_toStringRadixStub:
    CalleeSavedRegistersStub Kotlin_Int_toStringRadix

    .global _Kotlin_Long_toStringStub
_Kotlin_Long_toStringStub:
    CalleeSavedRegistersStub Kotlin_Long_toString

    .global _Kotlin_DurationValue_formatToExactDecimalsStub
_Kotlin_DurationValue_formatToExactDecimalsStub:
    CalleeSavedRegistersStub Kotlin_DurationValue_formatToExactDecimals

    .global _Kotlin_Worker_startInternalStub
_Kotlin_Worker_startInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_startInternal

    .global _Kotlin_Worker_currentInternalStub
_Kotlin_Worker_currentInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_currentInternal

    .global _Kotlin_Worker_requestTerminationWorkerInternalStub
_Kotlin_Worker_requestTerminationWorkerInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_requestTerminationWorkerInternal

    .global _Kotlin_Worker_executeInternalStub
_Kotlin_Worker_executeInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_executeInternal

    .global _Kotlin_Worker_executeAfterInternalStub
_Kotlin_Worker_executeAfterInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_executeAfterInternal

    .global _Kotlin_Worker_processQueueInternalStub
_Kotlin_Worker_processQueueInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_processQueueInternal

    .global _Kotlin_Worker_parkInternalStub
_Kotlin_Worker_parkInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_parkInternal

    .global _Kotlin_Worker_getNameInternalStub
_Kotlin_Worker_getNameInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_getNameInternal

    .global _Kotlin_Worker_stateOfFutureStub
_Kotlin_Worker_stateOfFutureStub:
    CalleeSavedRegistersStub Kotlin_Worker_stateOfFuture

    .global _Kotlin_Worker_waitForAnyFutureStub
_Kotlin_Worker_waitForAnyFutureStub:
    CalleeSavedRegistersStub Kotlin_Worker_waitForAnyFuture

    .global _Kotlin_Worker_versionTokenStub
_Kotlin_Worker_versionTokenStub:
    CalleeSavedRegistersStub Kotlin_Worker_versionToken

    .global _Kotlin_Worker_waitTerminationStub
_Kotlin_Worker_waitTerminationStub:
    CalleeSavedRegistersStub Kotlin_Worker_waitTermination

    .global _Kotlin_Worker_getPlatformThreadIdInternalStub
_Kotlin_Worker_getPlatformThreadIdInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_getPlatformThreadIdInternal

    .global _Kotlin_Worker_getActiveWorkersInternalStub
_Kotlin_Worker_getActiveWorkersInternalStub:
    CalleeSavedRegistersStub Kotlin_Worker_getActiveWorkersInternal

    .global _AllocArrayInstanceStub
_AllocArrayInstanceStub:
    CalleeSavedRegistersStub AllocArrayInstance

    .global _Kotlin_native_internal_GC_scheduleStub
_Kotlin_native_internal_GC_scheduleStub:
    CalleeSavedRegistersStub Kotlin_native_internal_GC_schedule

    .global _PerformFullGCStub
_PerformFullGCStub:
    CalleeSavedRegistersStub PerformFullGC

    .global _Kotlin_mm_switchThreadStateNativeStub
_Kotlin_mm_switchThreadStateNativeStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateNative

    .global _Kotlin_mm_switchThreadStateNative_debugStub
_Kotlin_mm_switchThreadStateNative_debugStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateNative_debug

    .global _Kotlin_mm_switchThreadStateRunnableStub
_Kotlin_mm_switchThreadStateRunnableStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateRunnable

    .global _Kotlin_mm_switchThreadStateRunnable_debugStub
_Kotlin_mm_switchThreadStateRunnable_debugStub:
    CalleeSavedRegistersStub Kotlin_mm_switchThreadStateRunnable_debug

    .global _Konan_getWeakReferenceImplStub
_Konan_getWeakReferenceImplStub:
    CalleeSavedRegistersStub Konan_getWeakReferenceImpl

    .global _Kotlin_Long_toStringRadixStub
_Kotlin_Long_toStringRadixStub:
    CalleeSavedRegistersStub Kotlin_Long_toStringRadix

    .global _Kotlin_getEmptyStackTraceStub
_Kotlin_getEmptyStackTraceStub:
    CalleeSavedRegistersStub Kotlin_getEmptyStackTrace

    .global _CallInitGlobalPossiblyLockStub
_CallInitGlobalPossiblyLockStub:
    CalleeSavedRegistersStub CallInitGlobalPossiblyLock

    .global _Kotlin_TypeInfo_findAssociatedObjectStub
_Kotlin_TypeInfo_findAssociatedObjectStub:
    CalleeSavedRegistersStub Kotlin_TypeInfo_findAssociatedObject

    .global _CallInitThreadLocalStub
_CallInitThreadLocalStub:
    CalleeSavedRegistersStub CallInitThreadLocal
    .global _Kotlin_Array_getStub
_Kotlin_Array_getStub:
    CalleeSavedRegistersStub Kotlin_Array_get

    .global _Kotlin_Array_get_without_BoundCheckStub
_Kotlin_Array_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_Array_get_without_BoundCheck

    .global _Kotlin_Array_setStub
_Kotlin_Array_setStub:
    CalleeSavedRegistersStub Kotlin_Array_set

    .global _Kotlin_Array_set_without_BoundCheckStub
_Kotlin_Array_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_Array_set_without_BoundCheck

    .global _Kotlin_Array_fillImplStub
_Kotlin_Array_fillImplStub:
    CalleeSavedRegistersStub Kotlin_Array_fillImpl

    .global _Kotlin_Array_copyImplStub
_Kotlin_Array_copyImplStub:
    CalleeSavedRegistersStub Kotlin_Array_copyImpl

    .global _Kotlin_ByteArray_getStub
_Kotlin_ByteArray_getStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_get

    .global _Kotlin_ByteArray_get_without_BoundCheckStub
_Kotlin_ByteArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_get_without_BoundCheck

    .global _Kotlin_ByteArray_setStub
_Kotlin_ByteArray_setStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_set

    .global _Kotlin_ByteArray_set_without_BoundCheckStub
_Kotlin_ByteArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_set_without_BoundCheck

    .global _Kotlin_ByteArray_getCharAtStub
_Kotlin_ByteArray_getCharAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getCharAt

    .global _Kotlin_ByteArray_getShortAtStub
_Kotlin_ByteArray_getShortAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getShortAt

    .global _Kotlin_ByteArray_getIntAtStub
_Kotlin_ByteArray_getIntAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getIntAt

    .global _Kotlin_ByteArray_getLongAtStub
_Kotlin_ByteArray_getLongAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getLongAt

    .global _Kotlin_ByteArray_getFloatAtStub
_Kotlin_ByteArray_getFloatAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getFloatAt

    .global _Kotlin_ByteArray_getDoubleAtStub
_Kotlin_ByteArray_getDoubleAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_getDoubleAt

    .global _Kotlin_ByteArray_setCharAtStub
_Kotlin_ByteArray_setCharAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setCharAt

    .global _Kotlin_ByteArray_setShortAtStub
_Kotlin_ByteArray_setShortAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setShortAt

    .global _Kotlin_ByteArray_setIntAtStub
_Kotlin_ByteArray_setIntAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setIntAt

    .global _Kotlin_ByteArray_setLongAtStub
_Kotlin_ByteArray_setLongAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setLongAt

    .global _Kotlin_ByteArray_setFloatAtStub
_Kotlin_ByteArray_setFloatAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setFloatAt

    .global _Kotlin_ByteArray_setDoubleAtStub
_Kotlin_ByteArray_setDoubleAtStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_setDoubleAt

    .global _Kotlin_CharArray_getStub
_Kotlin_CharArray_getStub:
    CalleeSavedRegistersStub Kotlin_CharArray_get

    .global _Kotlin_CharArray_get_without_BoundCheckStub
_Kotlin_CharArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_CharArray_get_without_BoundCheck

    .global _Kotlin_CharArray_setStub
_Kotlin_CharArray_setStub:
    CalleeSavedRegistersStub Kotlin_CharArray_set

    .global _Kotlin_CharArray_set_without_BoundCheckStub
_Kotlin_CharArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_CharArray_set_without_BoundCheck

    .global _Kotlin_ShortArray_getStub
_Kotlin_ShortArray_getStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_get

    .global _Kotlin_ShortArray_get_without_BoundCheckStub
_Kotlin_ShortArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_get_without_BoundCheck

    .global _Kotlin_ShortArray_setStub
_Kotlin_ShortArray_setStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_set

    .global _Kotlin_ShortArray_set_without_BoundCheckStub
_Kotlin_ShortArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_set_without_BoundCheck

    .global _Kotlin_IntArray_getStub
_Kotlin_IntArray_getStub:
    CalleeSavedRegistersStub Kotlin_IntArray_get

    .global _Kotlin_IntArray_get_without_BoundCheckStub
_Kotlin_IntArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_IntArray_get_without_BoundCheck

    .global _Kotlin_IntArray_setStub
_Kotlin_IntArray_setStub:
    CalleeSavedRegistersStub Kotlin_IntArray_set

    .global _Kotlin_IntArray_set_without_BoundCheckStub
_Kotlin_IntArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_IntArray_set_without_BoundCheck

    .global _Kotlin_ByteArray_fillImplStub
_Kotlin_ByteArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_fillImpl

    .global _Kotlin_ShortArray_fillImplStub
_Kotlin_ShortArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_fillImpl

    .global _Kotlin_CharArray_fillImplStub
_Kotlin_CharArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_CharArray_fillImpl

    .global _Kotlin_IntArray_fillImplStub
_Kotlin_IntArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_IntArray_fillImpl

    .global _Kotlin_LongArray_fillImplStub
_Kotlin_LongArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_LongArray_fillImpl

    .global _Kotlin_FloatArray_fillImplStub
_Kotlin_FloatArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_fillImpl

    .global _Kotlin_DoubleArray_fillImplStub
_Kotlin_DoubleArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_fillImpl

    .global _Kotlin_BooleanArray_fillImplStub
_Kotlin_BooleanArray_fillImplStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_fillImpl

    .global _Kotlin_ByteArray_copyImplStub
_Kotlin_ByteArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_ByteArray_copyImpl

    .global _Kotlin_ShortArray_copyImplStub
_Kotlin_ShortArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_ShortArray_copyImpl

    .global _Kotlin_CharArray_copyImplStub
_Kotlin_CharArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_CharArray_copyImpl

    .global _Kotlin_IntArray_copyImplStub
_Kotlin_IntArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_IntArray_copyImpl

    .global _Kotlin_LongArray_copyImplStub
_Kotlin_LongArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_LongArray_copyImpl

    .global _Kotlin_FloatArray_copyImplStub
_Kotlin_FloatArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_copyImpl

    .global _Kotlin_DoubleArray_copyImplStub
_Kotlin_DoubleArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_copyImpl

    .global _Kotlin_BooleanArray_copyImplStub
_Kotlin_BooleanArray_copyImplStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_copyImpl

    .global _Kotlin_LongArray_getStub
_Kotlin_LongArray_getStub:
    CalleeSavedRegistersStub Kotlin_LongArray_get

    .global _Kotlin_LongArray_get_without_BoundCheckStub
_Kotlin_LongArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_LongArray_get_without_BoundCheck

    .global _Kotlin_LongArray_setStub
_Kotlin_LongArray_setStub:
    CalleeSavedRegistersStub Kotlin_LongArray_set

    .global _Kotlin_LongArray_set_without_BoundCheckStub
_Kotlin_LongArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_LongArray_set_without_BoundCheck

    .global _Kotlin_FloatArray_getStub
_Kotlin_FloatArray_getStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_get

    .global _Kotlin_FloatArray_get_without_BoundCheckStub
_Kotlin_FloatArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_get_without_BoundCheck

    .global _Kotlin_FloatArray_setStub
_Kotlin_FloatArray_setStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_set

    .global _Kotlin_FloatArray_set_without_BoundCheckStub
_Kotlin_FloatArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_FloatArray_set_without_BoundCheck

    .global _Kotlin_DoubleArray_getStub
_Kotlin_DoubleArray_getStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_get

    .global _Kotlin_DoubleArray_get_without_BoundCheckStub
_Kotlin_DoubleArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_get_without_BoundCheck

    .global _Kotlin_DoubleArray_setStub
_Kotlin_DoubleArray_setStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_set

    .global _Kotlin_DoubleArray_set_without_BoundCheckStub
_Kotlin_DoubleArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_DoubleArray_set_without_BoundCheck

    .global _Kotlin_BooleanArray_getStub
_Kotlin_BooleanArray_getStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_get

    .global _Kotlin_BooleanArray_get_without_BoundCheckStub
_Kotlin_BooleanArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_get_without_BoundCheck

    .global _Kotlin_BooleanArray_setStub
_Kotlin_BooleanArray_setStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_set

    .global _Kotlin_BooleanArray_set_without_BoundCheckStub
_Kotlin_BooleanArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_BooleanArray_set_without_BoundCheck

    .global _Kotlin_NativePtrArray_getStub
_Kotlin_NativePtrArray_getStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_get

    .global _Kotlin_NativePtrArray_get_without_BoundCheckStub
_Kotlin_NativePtrArray_get_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_get_without_BoundCheck

    .global _Kotlin_NativePtrArray_setStub
_Kotlin_NativePtrArray_setStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_set

    .global _Kotlin_NativePtrArray_set_without_BoundCheckStub
_Kotlin_NativePtrArray_set_without_BoundCheckStub:
    CalleeSavedRegistersStub Kotlin_NativePtrArray_set_without_BoundCheck

    .global _Kotlin_ImmutableBlob_asCPointerImplStub
_Kotlin_ImmutableBlob_asCPointerImplStub:
    CalleeSavedRegistersStub Kotlin_ImmutableBlob_asCPointerImpl

    .global _Kotlin_Arrays_getByteArrayAddressOfElementStub
_Kotlin_Arrays_getByteArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getByteArrayAddressOfElement

    .global _Kotlin_Arrays_getCharArrayAddressOfElementStub
_Kotlin_Arrays_getCharArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getCharArrayAddressOfElement

    .global _Kotlin_Arrays_getStringAddressOfElementStub
_Kotlin_Arrays_getStringAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getStringAddressOfElement

    .global _Kotlin_Arrays_getShortArrayAddressOfElementStub
_Kotlin_Arrays_getShortArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getShortArrayAddressOfElement

    .global _Kotlin_Arrays_getIntArrayAddressOfElementStub
_Kotlin_Arrays_getIntArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getIntArrayAddressOfElement

    .global _Kotlin_Arrays_getLongArrayAddressOfElementStub
_Kotlin_Arrays_getLongArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getLongArrayAddressOfElement

    .global _Kotlin_Arrays_getFloatArrayAddressOfElementStub
_Kotlin_Arrays_getFloatArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getFloatArrayAddressOfElement

    .global _Kotlin_Arrays_getDoubleArrayAddressOfElementStub
_Kotlin_Arrays_getDoubleArrayAddressOfElementStub:
    CalleeSavedRegistersStub Kotlin_Arrays_getDoubleArrayAddressOfElement

    .global _Kotlin_String_compareToStub
_Kotlin_String_compareToStub:
    CalleeSavedRegistersStub Kotlin_String_compareTo

    .global _Kotlin_String_getStub
_Kotlin_String_getStub:
    CalleeSavedRegistersStub Kotlin_String_get

    .global _Kotlin_native_FloatingPointParser_parseFloatImplStub
_Kotlin_native_FloatingPointParser_parseFloatImplStub:
    CalleeSavedRegistersStub Kotlin_native_FloatingPointParser_parseFloatImpl

    .global _Kotlin_Worker_invokeCFunctionStub
_Kotlin_Worker_invokeCFunctionStub:
    CalleeSavedRegistersStub Kotlin_Worker_invokeCFunction

    .global _CreateStringFromUtf16Stub
_CreateStringFromUtf16Stub:
    CalleeSavedRegistersStub CreateStringFromUtf16

    .global _CreateStringFromUtf8OrThrowStub
_CreateStringFromUtf8OrThrowStub:
    CalleeSavedRegistersStub CreateStringFromUtf8OrThrow

    .global _CreateUninitializedStringStub
_CreateUninitializedStringStub:
    CalleeSavedRegistersStub CreateUninitializedString

// CRT-mode entry points. Each wraps a runtime function through CalleeSavedRegistersStub
// so that fp-unwind K2RStub mechanism handles the K→Runtime transition when CRT is active.

    .global _ReadHeapRefStub
_ReadHeapRefStub:
    CalleeSavedRegistersStub ReadHeapRef

    .global _ReadVolatileHeapRefStub
_ReadVolatileHeapRefStub:
    CalleeSavedRegistersStub ReadVolatileHeapRef

    .global _AllocInstanceForCIStub
_AllocInstanceForCIStub:
    CalleeSavedRegistersStub AllocInstanceForCI

    .global _AllocArrayInstanceForCIStub
_AllocArrayInstanceForCIStub:
    CalleeSavedRegistersStub AllocArrayInstanceForCI

    // Ported from mpcore/crt_fp_unwind 7e581cd. Asm trampoline for the safe-point
    // slow path. Must explicitly spill callee-saved registers (x19-x27) onto its
    // own frame before calling _CSafePointSlowPath, so that any Kotlin object
    // pointers cached in those registers become visible to the GC walker during
    // STW. x28 is preserved separately by the CRT (carries the TLS pointer) so we
    // do not save it here. The CalleeSavedRegistersStub macro can't be used
    // because SafePointSlowPath is static (internal-linkage); we go through the
    // extern "C" wrapper _CSafePointSlowPath instead.
    .global _SafePointSlowPathStub
_SafePointSlowPathStub:
    .cfi_startproc
    stp  x29, x30, [sp,  #-StubFrameContextSize]!
    cfi_adjust_cfa_offset (StubFrameContextSize)
    cfi_rel_offset (x29, 0)
    cfi_rel_offset (x30, 8)

    mov  x29, sp
    cfi_def_cfa_register (sp)

    // save all used callee-saved registers (x28 is preserved by CRT, so skip it).
    stp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_rel_offset (x19, StubCalleeSaveAreaSize)
    cfi_rel_offset (x20, StubCalleeSaveAreaSize+8)

    stp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_rel_offset (x21, StubCalleeSaveAreaSize+0x10)
    cfi_rel_offset (x22, StubCalleeSaveAreaSize+0x18)

    stp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_rel_offset (x23, StubCalleeSaveAreaSize+0x20)
    cfi_rel_offset (x24, StubCalleeSaveAreaSize+0x28)

    stp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_rel_offset (x25, StubCalleeSaveAreaSize+0x30)
    cfi_rel_offset (x26, StubCalleeSaveAreaSize+0x38)

    str  x27, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_rel_offset (x27, StubCalleeSaveAreaSize+0x40)

    bl   _CSafePointSlowPath

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_restore (x19)
    cfi_restore (x20)
    ldp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_restore (x21)
    cfi_restore (x22)
    ldp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_restore (x23)
    cfi_restore (x24)
    ldp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_restore (x25)
    cfi_restore (x26)
    ldr  x27, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_restore (x27)

    ldp  x29, x30, [sp], #StubFrameContextSize
    cfi_adjust_cfa_offset (-StubFrameContextSize)
    cfi_restore (x29)
    cfi_restore (x30)
    ret
    .cfi_endproc


    .global _slowPathStub
_slowPathStub:
    .cfi_startproc
    stp  x29, x30, [sp,  #-StubFrameContextSize]!
    cfi_adjust_cfa_offset (StubFrameContextSize)
    cfi_rel_offset (x29, 0)
    cfi_rel_offset (x30, 8)

    mov  x29, sp
    cfi_def_cfa_register (sp)

    // save all used callee-saved registers.
    stp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_rel_offset (x19, StubCalleeSaveAreaSize)
    cfi_rel_offset (x20, StubCalleeSaveAreaSize+8)

    stp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_rel_offset (x21, StubCalleeSaveAreaSize+0x10)
    cfi_rel_offset (x22, StubCalleeSaveAreaSize+0x18)

    stp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_rel_offset (x23, StubCalleeSaveAreaSize+0x20)
    cfi_rel_offset (x24, StubCalleeSaveAreaSize+0x28)

    stp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_rel_offset (x25, StubCalleeSaveAreaSize+0x30)
    cfi_rel_offset (x26, StubCalleeSaveAreaSize+0x38)

    stp  x27, x28, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_rel_offset (x27, StubCalleeSaveAreaSize+0x40)
    cfi_rel_offset (x28, StubCalleeSaveAreaSize+0x48)

    bl   _CslowPath
    str  x0,  [sp, #StubCalleeSaveAreaSize+0x48]

    // restore all used callee-saved registers.
    ldp  x19, x20, [sp, #StubCalleeSaveAreaSize]
    cfi_restore (x19)
    cfi_restore (x20)
    ldp  x21, x22, [sp, #StubCalleeSaveAreaSize+0x10]
    cfi_restore (x21)
    cfi_restore (x22)
    ldp  x23, x24, [sp, #StubCalleeSaveAreaSize+0x20]
    cfi_restore (x23)
    cfi_restore (x24)
    ldp  x25, x26, [sp, #StubCalleeSaveAreaSize+0x30]
    cfi_restore (x25)
    cfi_restore (x26)
    ldr  x27, [sp, #StubCalleeSaveAreaSize+0x40]
    cfi_restore (x27)
    ldr  x0,  [sp, #StubCalleeSaveAreaSize+0x48]
    cfi_restore (x0)

    ldp  x29, x30, [sp], #StubFrameContextSize
    cfi_adjust_cfa_offset (-StubFrameContextSize)
    cfi_restore (x29)
    cfi_restore (x30)
    ret
    .cfi_endproc

    .global _unwindPCForK2RStubEnd
_unwindPCForK2RStubEnd:

