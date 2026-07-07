/*
 * Copyright (c) 2026 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0.
 */

package org.jetbrains.kotlin.backend.konan.llvm

/**
 * Whitelist of runtime helpers that have a corresponding hand-written `<name>Stub`
 * trampoline defined in `kotlin-native/runtime/src/main/cpp/<target>_stubs/K2RStub.s`.
 *
 * When the Kotlin codegen emits a call from user code (`functype=kfunc`) to one of
 * these helpers, it should call `<name>Stub` directly instead of the bare helper,
 * so that `FpUnwind.cpp` sees the return address inside the
 * `unwindPCForK2RStubStart..End` range and switches to the KOTLIN_FRAME walk mode.
 *
 * **Why per-module IR-codegen emission**:
 * Historically the bare-call → Stub-call rewrite was done by KSG step 1 inside
 * `runBitcodePostProcessing`, which reads `@llvm.global.annotations` to discover the
 * helper set. That GV only lives in the runtime .bc module. Under
 * `kotlin.incremental.native=true`, cached per-file user .bc modules carry no
 * annotation, KSG step 1 finds an empty helper set and skips, leaving cached call
 * sites unrewritten. The fp-unwind walker then fails to recognise the frame as
 * K2R_STUB and GC root scanning breaks intermittently.
 *
 * Mirroring kotlin2.0 commit `859cd2003c40`, we now emit the Stub call directly at
 * IR codegen time (see `ContextUtils.importRtFunction` and
 * `IrToBitcode.generateGCUnsafeCallStub`), making the per-file .bc cache-coherent.
 * KSG step 1 remains as a safety net for any call site this IR rewrite misses.
 *
 * **Source of truth**:
 * `kotlin-native/runtime/src/main/cpp/aarch64_linux_ohos_stubs/K2RStub.s` (OHOS) and
 * `aarch64_macos_stubs/K2RStub.s` (macOS) — the two files declare the same logical
 * set (macOS just has the Mach-O `_` symbol prefix).
 *
 * **Sync rule**: the build-time `verifyK2RStubFunctions` Gradle task (declared in
 * `backend.native/compiler/ir/backend.native/build.gradle.kts`) re-parses K2RStub.s
 * on every `compileKotlin` and fails the build if either set drifts. So this file
 * is hand-edited but drift-protected.
 *
 * **Two sets, two consumers**:
 * - [names]: the 156 `CalleeSavedRegistersStub <name>` macro invocations in K2RStub.s.
 *   These are the runtime helpers that user code calls — KSG step 1 / IR-codegen
 *   rewrite `<name>` → `<name>Stub` at the call site.
 * - [linkRootSet]: a superset of [names] containing every external symbol that
 *   K2RStub.o references at link time. Currently `names + {"CSafePointSlowPath"}`
 *   — the `SafePointSlowPathStub` block is hand-written (not macro-generated)
 *   and calls `CSafePointSlowPath` directly, which is not in [names] because
 *   no user code calls it. `pinK2RStubCalleesInLlvmUsed` in Bitcode.kt feeds this
 *   set into each split=N part's `@llvm.used` so GlobalDCE keeps these
 *   K2RStub.o-referenced runtime helpers alive in every part.
 */
internal object K2RStubFunctions {
    val names: Set<String> = setOf(
        "AllocArrayInstance",
        "AllocArrayInstanceForCI",
        "AllocInstance",
        "AllocInstanceForCI",
        "CallInitGlobalPossiblyLock",
        "CallInitThreadLocal",
        "CreateStringFromCString",
        "CreateStringFromUtf16",
        "CreateStringFromUtf8",
        "CreateStringFromUtf8OrThrow",
        "CreateUninitializedString",
        "Konan_getWeakReferenceImpl",
        "Kotlin_Any_hashCode",
        "Kotlin_Array_copyImpl",
        "Kotlin_Array_fillImpl",
        "Kotlin_Array_get",
        "Kotlin_Array_get_without_BoundCheck",
        "Kotlin_Array_set",
        "Kotlin_Array_set_without_BoundCheck",
        "Kotlin_Arrays_getByteArrayAddressOfElement",
        "Kotlin_Arrays_getCharArrayAddressOfElement",
        "Kotlin_Arrays_getDoubleArrayAddressOfElement",
        "Kotlin_Arrays_getFloatArrayAddressOfElement",
        "Kotlin_Arrays_getIntArrayAddressOfElement",
        "Kotlin_Arrays_getLongArrayAddressOfElement",
        "Kotlin_Arrays_getShortArrayAddressOfElement",
        "Kotlin_Arrays_getStringAddressOfElement",
        "Kotlin_BooleanArray_copyImpl",
        "Kotlin_BooleanArray_fillImpl",
        "Kotlin_BooleanArray_get",
        "Kotlin_BooleanArray_get_without_BoundCheck",
        "Kotlin_BooleanArray_set",
        "Kotlin_BooleanArray_set_without_BoundCheck",
        "Kotlin_Byte_toString",
        "Kotlin_ByteArray_copyImpl",
        "Kotlin_ByteArray_fillImpl",
        "Kotlin_ByteArray_get",
        "Kotlin_ByteArray_get_without_BoundCheck",
        "Kotlin_ByteArray_getCharAt",
        "Kotlin_ByteArray_getDoubleAt",
        "Kotlin_ByteArray_getFloatAt",
        "Kotlin_ByteArray_getIntAt",
        "Kotlin_ByteArray_getLongAt",
        "Kotlin_ByteArray_getShortAt",
        "Kotlin_ByteArray_set",
        "Kotlin_ByteArray_set_without_BoundCheck",
        "Kotlin_ByteArray_setCharAt",
        "Kotlin_ByteArray_setDoubleAt",
        "Kotlin_ByteArray_setFloatAt",
        "Kotlin_ByteArray_setIntAt",
        "Kotlin_ByteArray_setLongAt",
        "Kotlin_ByteArray_setShortAt",
        "Kotlin_ByteArray_unsafeStringFromUtf8",
        "Kotlin_ByteArray_unsafeStringFromUtf8OrThrow",
        "Kotlin_Char_toString",
        "Kotlin_CharArray_copyImpl",
        "Kotlin_CharArray_copyOf",
        "Kotlin_CharArray_fillImpl",
        "Kotlin_CharArray_get",
        "Kotlin_CharArray_get_without_BoundCheck",
        "Kotlin_CharArray_set",
        "Kotlin_CharArray_set_without_BoundCheck",
        "Kotlin_CString_toKStringFromUtf8Impl",
        "Kotlin_DoubleArray_copyImpl",
        "Kotlin_DoubleArray_fillImpl",
        "Kotlin_DoubleArray_get",
        "Kotlin_DoubleArray_get_without_BoundCheck",
        "Kotlin_DoubleArray_set",
        "Kotlin_DoubleArray_set_without_BoundCheck",
        "Kotlin_DurationValue_formatToExactDecimals",
        "Kotlin_FloatArray_copyImpl",
        "Kotlin_FloatArray_fillImpl",
        "Kotlin_FloatArray_get",
        "Kotlin_FloatArray_get_without_BoundCheck",
        "Kotlin_FloatArray_set",
        "Kotlin_FloatArray_set_without_BoundCheck",
        "Kotlin_getCurrentStackTrace",
        "Kotlin_getEmptyStackTrace",
        "Kotlin_getStackTraceStrings",
        "Kotlin_ImmutableBlob_asCPointerImpl",
        "Kotlin_ImmutableBlob_toByteArray",
        "Kotlin_Int_toString",
        "Kotlin_Int_toStringRadix",
        "Kotlin_IntArray_copyImpl",
        "Kotlin_IntArray_fillImpl",
        "Kotlin_IntArray_get",
        "Kotlin_IntArray_get_without_BoundCheck",
        "Kotlin_IntArray_set",
        "Kotlin_IntArray_set_without_BoundCheck",
        "Kotlin_Internal_GC_GCInfoBuilder_Fill",
        "Kotlin_io_Console_print",
        "Kotlin_io_Console_println",
        "Kotlin_io_Console_println0",
        "Kotlin_io_Console_println0ToStdErr",
        "Kotlin_io_Console_printlnToStdErr",
        "Kotlin_io_Console_printToStdErr",
        "Kotlin_io_Console_readLine",
        "Kotlin_io_Console_readlnOrNull",
        "Kotlin_Long_toString",
        "Kotlin_Long_toStringRadix",
        "Kotlin_LongArray_copyImpl",
        "Kotlin_LongArray_fillImpl",
        "Kotlin_LongArray_get",
        "Kotlin_LongArray_get_without_BoundCheck",
        "Kotlin_LongArray_set",
        "Kotlin_LongArray_set_without_BoundCheck",
        "Kotlin_mm_safePointFunctionPrologue",
        "Kotlin_mm_safePointWhileLoopBody",
        "Kotlin_mm_switchThreadStateNative",
        "Kotlin_mm_switchThreadStateNative_debug",
        "Kotlin_mm_switchThreadStateRunnable",
        "Kotlin_mm_switchThreadStateRunnable_debug",
        "Kotlin_native_FloatingPointParser_parseFloatImpl",
        "Kotlin_native_internal_GC_collect",
        "Kotlin_native_internal_GC_schedule",
        "Kotlin_NativePtrArray_get",
        "Kotlin_NativePtrArray_get_without_BoundCheck",
        "Kotlin_NativePtrArray_set",
        "Kotlin_NativePtrArray_set_without_BoundCheck",
        "Kotlin_Short_toString",
        "Kotlin_ShortArray_copyImpl",
        "Kotlin_ShortArray_fillImpl",
        "Kotlin_ShortArray_get",
        "Kotlin_ShortArray_get_without_BoundCheck",
        "Kotlin_ShortArray_set",
        "Kotlin_ShortArray_set_without_BoundCheck",
        "Kotlin_String_compareTo",
        "Kotlin_String_get",
        "Kotlin_String_plusImpl",
        "Kotlin_String_replace",
        "Kotlin_String_subSequence",
        "Kotlin_String_unsafeStringFromCharArray",
        "Kotlin_String_unsafeStringToUtf8",
        "Kotlin_String_unsafeStringToUtf8OrThrow",
        "Kotlin_text_regex_getDecompositionInternal",
        "Kotlin_TypeInfo_findAssociatedObject",
        "Kotlin_Uuid_getRandomBytes",
        "Kotlin_Worker_consumeFuture",
        "Kotlin_Worker_currentInternal",
        "Kotlin_Worker_executeAfterInternal",
        "Kotlin_Worker_executeInternal",
        "Kotlin_Worker_getActiveWorkersInternal",
        "Kotlin_Worker_getNameInternal",
        "Kotlin_Worker_getPlatformThreadIdInternal",
        "Kotlin_Worker_invokeCFunction",
        "Kotlin_Worker_parkInternal",
        "Kotlin_Worker_processQueueInternal",
        "Kotlin_Worker_requestTerminationWorkerInternal",
        "Kotlin_Worker_startInternal",
        "Kotlin_Worker_stateOfFuture",
        "Kotlin_Worker_versionToken",
        "Kotlin_Worker_waitForAnyFuture",
        "Kotlin_Worker_waitTermination",
        "PerformFullGC",
        "ReadHeapRef",
        "ReadVolatileHeapRef",
    )

    /** Append `Stub` suffix; assumes the caller has already verified membership. */
    fun stubNameOf(name: String): String = "${name}Stub"

    /**
     * Strict superset of [names]. Adds external symbols that K2RStub.o references
     * but that aren't user-callable (so KSG rewrite doesn't touch them):
     *  - `CSafePointSlowPath`: called from the hand-written `SafePointSlowPathStub`
     *    block (CRT cold edge — see `aarch64_*_stubs/K2RStub.s` line ~810).
     *  - `CslowPath`: called from the hand-written `slowPathStub` block (NATIVE cold
     *    edge — the expanded NATIVE safepoint poll's direct stub call).
     *  - `Kotlin_mm_safePointCheckCRT`: lean C++ helper called from the CrtNoFastpath
     *    expanded poll's fast check. Pinned so it survives DCE during linking
     *    (the call is inserted by the post-link RemoveRedundantSafepoints pass). NOT in
     *    [names]: it must stay a plain C++ call, never redirected to a `...Stub` K2R
     *    trampoline (the whole point is that it is not a K2R boundary). Absent in
     *    fastpath builds, where pinning skips it silently.
     *
     * Consumer: `pinK2RStubCalleesInLlvmUsed` in Bitcode.kt.
     */
    val linkRootSet: Set<String> = names + setOf("CSafePointSlowPath", "CslowPath", "Kotlin_mm_safePointCheckCRT")
}
