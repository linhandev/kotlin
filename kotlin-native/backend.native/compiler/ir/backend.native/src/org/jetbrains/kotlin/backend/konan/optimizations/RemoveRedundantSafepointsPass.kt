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
     * @param enableStackmap Precise-stackmap dist (true) -> expand the surviving safepoint
     *   per block into the inline fast/slow poll; OFF / shadow-stack (false) -> force-inline
     *   the first eligible bare prologue. Replaces the legacy compile-time `#ifndef
     *   ENABLE_STACKMAP` gate so one libllvmext binary supports both at runtime; the caller
     *   passes `config.enableStackmap` (per-target).
     */
    fun runOnModule(module: LLVMModuleRef, isSafepointInliningAllowed: Boolean,
                    enableStackmap: Boolean, safepointExpansionMode: SafepointExpansionMode) {
        LLVMKotlinRemoveRedundantSafepoints(
                module,
                if (isSafepointInliningAllowed) 1 else 0,
                if (enableStackmap) 1 else 0,
                safepointExpansionMode,
        )
    }
}