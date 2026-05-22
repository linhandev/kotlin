/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.optimizations

import llvm.*

/**
 * Removes all Kotlin_mm_safePointFunctionPrologue from basic block except the first one.
 * Also, if first basic block in function contains call to Kotlin_mm_safePointFunctionPrologue, all other calls would be removed.
 * Also, calls, which are not removed are inlined (except arm32 apple targets)
 */
internal class RemoveRedundantSafepointsPass {
    /**
     * @param isSafepointInliningAllowed Whether the `LLVMInlineCall` of the chosen
     *   safepoint is allowed (false for arm32 apple targets).
     * @param forceInlineFirstEligible Whether to force-inline the first eligible
     *   safepoint per basic block (baseline shadow-stack OFF mode perf
     *   optimisation). Replaces the legacy compile-time `#ifndef ENABLE_STACKMAP`
     *   gate so a single libllvmext binary supports both ON (false) and OFF (true)
     *   at runtime. Caller passes `!config.enableStackmap` which is per-target.
     */
    fun runOnModule(module: LLVMModuleRef, isSafepointInliningAllowed: Boolean,
                    forceInlineFirstEligible: Boolean) {
        LLVMKotlinRemoveRedundantSafepoints(
                module,
                if (isSafepointInliningAllowed) 1 else 0,
                if (forceInlineFirstEligible) 1 else 0,
        )
    }
}