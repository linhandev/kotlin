/*
 * Copyright 2010-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#ifndef LIBLLVMEXT_REMOVE_REDUNDANT_SAFEPOINTS_H
#define LIBLLVMEXT_REMOVE_REDUNDANT_SAFEPOINTS_H

#endif // LIBLLVMEXT_REMOVE_REDUNDANT_SAFEPOINTS_H

#include <llvm-c/Core.h>
#include <llvm-c/Target.h>

# ifdef __cplusplus
extern "C" {
# endif

// Lowering strategy for the surviving safepoint poll. As a plain (non-typedef) C enum,
// cinterop exposes it to Kotlin as `typealias SafepointExpansionMode = Int` plus named
// top-level constants (SAFEPOINT_EXPANSION_NONE, ...) — not a strong enum class — so both
// sides share the same names and int values.
enum SafepointExpansionMode {
    SAFEPOINT_EXPANSION_NONE            = 0, // RUNTIME_SWITCH / OFF: keep the surviving stub call
    SAFEPOINT_EXPANSION_NATIVE          = 1, // global safePointAction; cold edge slowPathStub
    SAFEPOINT_EXPANSION_CRT_FASTPATH    = 2, // inline x28 TLS read;   cold edge SafePointSlowPathStub
    SAFEPOINT_EXPANSION_CRT_NO_FASTPATH = 3, // Kotlin_mm_safePointCheckCRT helper; cold SafePointSlowPathStub
};

// `enableStackmap` (third arg): runtime switch replacing the legacy `#ifndef
// ENABLE_STACKMAP` compile-time gate. Pass non-zero (precise-stackmap ON) to expand
// the surviving safepoint per block into the inline fast/slow poll; pass zero (OFF /
// shadow-stack) to force-inline the first eligible bare prologue instead.
// `safepointExpansionMode` (fourth arg): which expanded fast check / cold edge to emit;
// SAFEPOINT_EXPANSION_NONE keeps the surviving stub call (no expansion).
void LLVMKotlinRemoveRedundantSafepoints(LLVMModuleRef module,
                                         int isSafePointInliningAllowed,
                                         int enableStackmap,
                                         enum SafepointExpansionMode safepointExpansionMode);

# ifdef __cplusplus
}
# endif
