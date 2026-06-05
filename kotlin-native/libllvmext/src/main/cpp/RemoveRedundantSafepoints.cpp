/*
 * Copyright 2010-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include <CAPIExtensions.h>
#include <RemoveRedundantSafepoints.h>
#include <llvm/IR/Constants.h>
#include <llvm/IR/Instructions.h>

using namespace llvm;

static constexpr const char* functionPrologueSafepointName = "Kotlin_mm_safePointFunctionPrologue";

static constexpr size_t functionPrologueSafepointNameLength =
    std::char_traits<char>::length(functionPrologueSafepointName);

static bool InstructionIsPrologueSafepoint(LLVMValueRef instruction) {
    if (LLVMIsACallInst(instruction) || LLVMIsAInvokeInst(instruction)) {
        size_t calledNameLength = 0;
        const char* calledName = LLVMGetValueName2(LLVMGetCalledValue(instruction), &calledNameLength);
        return functionPrologueSafepointNameLength == calledNameLength &&
            strncmp(calledName, functionPrologueSafepointName, calledNameLength) == 0;
    }
    return false;
}

static bool BlockHasSafepointInstruction(LLVMBasicBlockRef block) {
    LLVMValueRef current = LLVMGetFirstInstruction(block);
    while (current) {
        if (InstructionIsPrologueSafepoint(current)) {
            return true;
        }
        current = LLVMGetNextInstruction(current);
    }
    return false;
}

static void RemoveOrInlinePrologueSafepointInstructions(
    LLVMBasicBlockRef block, bool removeFirst, bool isSafepointInliningAllowed,
    bool forceInlineFirstEligible) {
    // Collect safepoint calls to erase and inline.
    LLVMValueRef toInline = nullptr;
    std::vector<LLVMValueRef> toErase;
    bool first = true;
    LLVMValueRef current = LLVMGetFirstInstruction(block);
    while (current) {
        if (InstructionIsPrologueSafepoint(current)) {
            if (!first || removeFirst) {
                toErase.push_back(current);
            }
            // OFF path (shadow-stack baseline) restores the upstream safepoint
            // inlining branch that the precise-stackmap path removes. It falls
            // back to the baseline behaviour where the *first eligible safepoint*
            // is inlined (perf optimisation).
            //
            // This branch was previously gated by `#ifndef ENABLE_STACKMAP` at
            // compile time. To support per-target default (arm64 ON + x86 OFF in
            // one dist), the gate is now a RUNTIME parameter `forceInlineFirstEligible`
            // controlled by RemoveRedundantSafepointsPass.kt (which reads
            // KonanConfig.enableStackmap). Behavioural equivalence vs the old
            // compile-time `#ifndef`:
            //   forceInlineFirstEligible == true   ≡  ENABLE_STACKMAP not defined (OFF)
            //   forceInlineFirstEligible == false  ≡  ENABLE_STACKMAP=1         (ON)
            //
            // The else if MUST sit inside the outer
            // `InstructionIsPrologueSafepoint(current)` so it only matches
            // safepoint instructions; placing it outside would make it pick the
            // *last* non-safepoint, non-declaration call in every basic block and
            // force-inline it via LLVMInlineCall, corrupting SSA and calling
            // conventions across the entire module (crashing the non-stackmap
            // distribution at runtime).
            else if (forceInlineFirstEligible && isSafepointInliningAllowed
                     && !LLVMIsDeclaration(LLVMGetCalledValue(current))) {
                toInline = current;
            }
            first = false;
        }
        current = LLVMGetNextInstruction(current);
    }
    // Perform actual modifications after iteration.
    for (auto it = toErase.cbegin(); it != toErase.cend(); ++it) {
        LLVMInstructionEraseFromParent(*it);
    }
    if (toInline) {
        LLVMInlineCall(toInline);
    }
}

// `forceInlineFirstEligible` (new): when non-zero, force-inline the first eligible
// safepoint per basic block (equivalent to the legacy `#ifndef ENABLE_STACKMAP`
// branch). Caller is RemoveRedundantSafepointsPass.kt, passes !enableStackmap.
void LLVMKotlinRemoveRedundantSafepoints(LLVMModuleRef module, int isSafepointInliningAllowed,
                                         int forceInlineFirstEligible) {
    bool inliningAllowed = isSafepointInliningAllowed != 0;
    bool forceInline = forceInlineFirstEligible != 0;
    for (LLVMValueRef currentFunction = LLVMGetFirstFunction(module); currentFunction;
         currentFunction = LLVMGetNextFunction(currentFunction)) {
        if (LLVMIsDeclaration(currentFunction)) {
            continue;
        }
        LLVMBasicBlockRef firstBlock = LLVMGetFirstBasicBlock(currentFunction);
        if (!firstBlock) {
            continue;
        }
        bool firstBlockHasSafepoint = BlockHasSafepointInstruction(firstBlock);
        RemoveOrInlinePrologueSafepointInstructions(firstBlock, false, inliningAllowed, forceInline);
        for (LLVMBasicBlockRef currentBlock = LLVMGetNextBasicBlock(firstBlock); currentBlock;
             currentBlock = LLVMGetNextBasicBlock(currentBlock)) {
            RemoveOrInlinePrologueSafepointInstructions(
                currentBlock, firstBlockHasSafepoint, inliningAllowed, forceInline);
        }
    }
}
