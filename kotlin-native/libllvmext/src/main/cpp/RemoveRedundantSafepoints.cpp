/*
 * Copyright 2010-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include <CAPIExtensions.h>
#include <RemoveRedundantSafepoints.h>
#include <llvm/IR/Constants.h>
#include <llvm/IR/Instructions.h>
#include <llvm/IR/IRBuilder.h>
#include <llvm/IR/InlineAsm.h>
#include <llvm/IR/MDBuilder.h>
#include <llvm/IR/Module.h>
#include <llvm/Transforms/Utils/BasicBlockUtils.h>

using namespace llvm;

// Both names are recognised as the prologue safepoint. The bare
// `Kotlin_mm_safePointFunctionPrologue` is the upstream baseline emit form; the
// `*Stub`-suffixed variant is what the Kotlin backend emits at IR codegen for the
// precise-stackmap dist. Since `-enable-rewrite-statepoints-for-gc` is now OFF in
// the konanc in-process pipeline (RSP runs only at the clang stage), this pass sees
// PLAIN calls again — so the by-name match below fires and the per-BB dedup works
// (it was a silent no-op while RSP wrapped the calls into gc.statepoint first).
static constexpr const char* functionPrologueSafepointName = "Kotlin_mm_safePointFunctionPrologue";
static constexpr const char* functionPrologueSafepointStubName = "Kotlin_mm_safePointFunctionPrologueStub";

static constexpr size_t functionPrologueSafepointNameLength =
    std::char_traits<char>::length(functionPrologueSafepointName);
static constexpr size_t functionPrologueSafepointStubNameLength =
    std::char_traits<char>::length(functionPrologueSafepointStubName);

// Mutator* offset (bytes) within the TLS block — same value as the runtime's
// common::TLS_MUTATOR_OFF (== sizeof(void*)). The i32 safepoint flag and the
// pointer-sized safepoint action use these natural byte alignments.
static constexpr uint64_t tlsMutatorOffset = 8;
static constexpr unsigned safepointFlagAlign = 4;
static constexpr unsigned safepointActionAlign = 8;

static bool InstructionIsPrologueSafepoint(LLVMValueRef instruction) {
    if (LLVMIsACallInst(instruction) || LLVMIsAInvokeInst(instruction)) {
        size_t calledNameLength = 0;
        const char* calledName = LLVMGetValueName2(LLVMGetCalledValue(instruction), &calledNameLength);
        const bool isBare = functionPrologueSafepointNameLength == calledNameLength &&
            strncmp(calledName, functionPrologueSafepointName, calledNameLength) == 0;
        const bool isStub = functionPrologueSafepointStubNameLength == calledNameLength &&
            strncmp(calledName, functionPrologueSafepointStubName, calledNameLength) == 0;
        return isBare || isStub;
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

// Inline safepoint poll expansion (precise-stackmap dist only). Split into two small
// emit helpers; the orchestration is integrated into
// RemoveOrInlinePrologueSafepointInstructions (the single expansion site).

// `RewriteStatepointsForGC` runs ONLY at the clang `-cc1` stage, so here the safepoints
// are still plain `call @Kotlin_mm_safePoint*Stub()`. We materialise mm::safePoint()'s
// fast path INLINE in the kfunc, leaving only the cold slow edge as a call. Doing the
// split HERE (before clang-stage RSP) keeps the fast path a straight-line load+branch and
// lets clang's RSP wrap only the cold call into a gc.statepoint. The flag is a monotonic
// atomic so LICM cannot hoist it out of a call-free tight loop and starve STW.

// Emit the safepoint fast-path condition at builder `B`; returns the i1 `active` flag.
// For the CRT modes also writes the Mutator* to `mutPtrOut` (consumed by the cold edge).
//   CrtFastpath  : inline `mov $0, x28` TLS read, then per-mutator flag.
//   CrtNoFastpath: x28 is not the reserved TLS pointer, so the C++-internal check goes
//                  through the lean helper Kotlin_mm_safePointCheckCRT() (plain C++, NOT a
//                  K2R stub), which returns the Mutator* iff a safepoint is pending else null.
//   Native       : global *Kotlin_mm_safePointActionAddr != null.
static Value* emitSafepointCondition(IRBuilder<>& B, SafepointExpansionMode mode, Module& M, Value*& mutPtrOut) {
    LLVMContext& Ctx = M.getContext();
    Type* I8 = Type::getInt8Ty(Ctx);
    Type* I32 = Type::getInt32Ty(Ctx);
    Type* I64 = Type::getInt64Ty(Ctx);
    PointerType* Ptr = PointerType::get(Ctx, 0);
    mutPtrOut = nullptr;
    if (mode == SAFEPOINT_EXPANSION_CRT_FASTPATH) {
        FunctionType* AsmTy = FunctionType::get(I64, false);
        InlineAsm* ReadX28 = InlineAsm::get(AsmTy, "mov $0, x28", "=r", true);
        Value* Tls = B.CreateCall(AsmTy, ReadX28, {}, "tls");
        Value* Base = B.CreateIntToPtr(Tls, Ptr);
        Value* MutAddr = B.CreateConstInBoundsGEP1_64(I8, Base, tlsMutatorOffset, "mutatorAddr");
        mutPtrOut = B.CreateLoad(Ptr, MutAddr, "mutatorPtr");
        LoadInst* Flag = B.CreateLoad(I32, mutPtrOut, "spActive");
        Flag->setAtomic(AtomicOrdering::Monotonic);
        Flag->setAlignment(Align(safepointFlagAlign));
        return B.CreateICmpNE(Flag, B.getInt32(0), "spNeeded");
    }
    if (mode == SAFEPOINT_EXPANSION_CRT_NO_FASTPATH) {
        FunctionCallee Check = M.getOrInsertFunction(
            "Kotlin_mm_safePointCheckCRT", FunctionType::get(Ptr, false));
        mutPtrOut = B.CreateCall(Check, {}, "spMutator");
        return B.CreateICmpNE(mutPtrOut, ConstantPointerNull::get(Ptr), "spNeeded");
    }
    Constant* AddrG = M.getOrInsertGlobal("Kotlin_mm_safePointActionAddr", Ptr);
    Value* ActionAddr = B.CreateLoad(Ptr, AddrG, "spActionAddr"); // &safePointAction (invariant)
    LoadInst* Action = B.CreateLoad(Ptr, ActionAddr, "spAction");
    Action->setAtomic(AtomicOrdering::Monotonic);
    Action->setAlignment(Align(safepointActionAlign));
    return B.CreateICmpNE(Action, ConstantPointerNull::get(Ptr), "spNeeded");
}

// Emit the cold slow-edge call at `thenTerm`. SINGLE K2R boundary per mode:
//   CrtFastpath / CrtNoFastpath: DIRECT `SafePointSlowPathStub(mutator)` — the asm stub
//              spills x19-x28 and is the lone K2R boundary `kfunc -> SafePointSlowPathStub`
//              (no PrologueStub trampoline, since the fast check was inlined). Routing through
//              the trampoline would leave TWO K2R boundaries with C++ frames between -> the
//              fp-unwind walker misclassifies them as Kotlin frames and reads a bogus stackmap
//              -> GC SIGSEGV.
//   Native    : DIRECT `slowPathStub()` — spills x19-x28 and runs the no-arg C++ slow path.
static void emitSafepointSlowCall(Instruction* thenTerm, SafepointExpansionMode mode, Value* mutPtr, Module& M) {
    LLVMContext& Ctx = M.getContext();
    PointerType* Ptr = PointerType::get(Ctx, 0);
    Type* Void = Type::getVoidTy(Ctx);
    IRBuilder<> SB(thenTerm);
    if (mode == SAFEPOINT_EXPANSION_CRT_FASTPATH || mode == SAFEPOINT_EXPANSION_CRT_NO_FASTPATH) {
        FunctionCallee Stub = M.getOrInsertFunction(
            "SafePointSlowPathStub", FunctionType::get(Void, {Ptr}, false));
        SB.CreateCall(Stub, {mutPtr});
    } else {
        FunctionCallee Stub = M.getOrInsertFunction(
            "slowPathStub", FunctionType::get(Void, false));
        SB.CreateCall(Stub, {});
    }
}

// Expand one surviving safepoint CALL into the inline fast/slow poll (orchestration: fast
// check via emitSafepointCondition, cold slow edge via emitSafepointSlowCall).
// SAFEPOINT_EXPANSION_NONE keeps the call as-is (RUNTIME_SWITCH / OFF). Produces a SINGLE
// K2R boundary (`kfunc -> SafePointSlowPathStub` for CRT, `-> slowPathStub` for NATIVE).
static void expandSafepointCall(CallInst* CI, SafepointExpansionMode mode, Module& M) {
    if (mode == SAFEPOINT_EXPANSION_NONE) {
        return;
    }
    IRBuilder<> B(CI);
    Value* MutPtr = nullptr;
    Value* active = emitSafepointCondition(B, mode, M, MutPtr);
    // Cold slow edge weights {then=1, else=2000}. SplitBlockAndInsertIfThen splits the block
    // at CI; everything after CI stays in the continuation block.
    MDNode* Weights = MDBuilder(M.getContext()).createBranchWeights(1, 2000);
    Instruction* ThenTerm =
        SplitBlockAndInsertIfThen(active, CI->getIterator(), false, Weights);
    emitSafepointSlowCall(ThenTerm, mode, MutPtr, M);
    CI->eraseFromParent();
}

// Pass-wide expansion settings, constant across the whole module walk.
struct SafepointExpansionConfig {
    bool enableStackmap;
    SafepointExpansionMode expansionMode;
    Module& module;
};

// For the first eligible surviving prologue safepoint per block:
//   ON  (enableStackmap, precise-stackmap): EXPAND it into the inline fast/slow poll here.
//   OFF (!enableStackmap, shadow-stack)   : force-inline the bare prologue (legacy
//                                           `#ifndef ENABLE_STACKMAP` behaviour).
static void RemoveOrInlinePrologueSafepointInstructions(
    LLVMBasicBlockRef block, bool removeFirst, bool isSafepointInliningAllowed,
    const SafepointExpansionConfig& config) {
    LLVMValueRef toInline = nullptr;
    LLVMValueRef toExpand = nullptr;
    std::vector<LLVMValueRef> toErase;
    bool first = true;
    for (LLVMValueRef current = LLVMGetFirstInstruction(block); current;
         current = LLVMGetNextInstruction(current)) {
        // Only prologue-safepoint instructions are eligible; the guard keeps the
        // classification below from picking any other call in the block (which would
        // corrupt SSA / calling conventions across the module).
        if (!InstructionIsPrologueSafepoint(current)) {
            continue;
        }
        if (!first || removeFirst) {
            toErase.push_back(current);
        } else if (isSafepointInliningAllowed) {
            if (config.enableStackmap) {
                toExpand = current;
            } else if (!LLVMIsDeclaration(LLVMGetCalledValue(current))) {
                toInline = current;
            }
        }
        first = false;
    }
    // Perform actual modifications after iteration (each mutates the CFG).
    for (auto it = toErase.cbegin(); it != toErase.cend(); ++it) {
        LLVMInstructionEraseFromParent(*it);
    }
    if (toInline) {
        LLVMInlineCall(toInline);
    }
    if (toExpand) {
        // Prologue expansion site. Only plain calls are expanded; an invoke-form safepoint
        // is left as-is (keeps its ...PrologueStub trampoline call). expandSafepointCall
        // no-ops when the mode is SAFEPOINT_EXPANSION_NONE (RUNTIME_SWITCH / OFF).
        if (auto* CI = dyn_cast<CallInst>(unwrap(toExpand))) {
            expandSafepointCall(CI, config.expansionMode, config.module);
        }
    }
}

// `enableStackmap` (precise-stackmap dist): ON -> expand the surviving safepoint per block
// into the inline poll; OFF (shadow-stack) -> force-inline the first eligible bare prologue
// (legacy `#ifndef ENABLE_STACKMAP` behaviour). `safepointExpansionMode`: Native /
// CrtFastpath / CrtNoFastpath -> expand into the inline poll; None -> no expansion
// (OFF/shadow-stack or RUNTIME_SWITCH).
// Caller is RemoveRedundantSafepointsPass.kt.
void LLVMKotlinRemoveRedundantSafepoints(LLVMModuleRef module, int isSafepointInliningAllowed,
                                         int enableStackmap, SafepointExpansionMode safepointExpansionMode) {
    bool inliningAllowed = isSafepointInliningAllowed != 0;
    bool stackmapEnabled = enableStackmap != 0;
    Module& M = *unwrap(module);
    SafepointExpansionConfig config{stackmapEnabled, safepointExpansionMode, M};

    // ===== STABILITY INVARIANT — IR expansion here is PERFORMANCE-ONLY =====
    // Whether this pass expands ALL, SOME, or NONE of the safepoints, the slow path is always
    // a SINGLE K2R boundary, so stability must never depend on the expansion running:
    //   * expanded   : `kfunc -> SafePointSlowPathStub` (CRT) / `-> slowPathStub` (NATIVE)
    //                  — direct, no trampoline; the lowered stub is the lone boundary.
    //   * un-expanded: `kfunc -> <...Stub trampoline>(K2R) -> C++ runtime -> mm::safePoint()
    //                  -> C++ SafePointSlowPath` — the trampoline is the lone boundary.
    // The single load-bearing requirement for the un-expanded path is that mm::safePoint()'s
    // cold edge calls the plain C++ SafePointSlowPath, NEVER the asm SafePointSlowPathStub
    // (a second asm K2R stub there would make the walker misclassify the intervening C++
    // frames -> bogus stackmap -> GC SIGSEGV). The asm `...Stub` cold edges are reachable ONLY
    // from expanded IR (no trampoline above them), so they too stay single-boundary.
    // Consequence: this pass may be safely skipped, partially applied, or fully applied.
    // =======================================================================

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
        RemoveOrInlinePrologueSafepointInstructions(firstBlock, false, inliningAllowed, config);
        for (LLVMBasicBlockRef currentBlock = LLVMGetNextBasicBlock(firstBlock); currentBlock;
             currentBlock = LLVMGetNextBasicBlock(currentBlock)) {
            RemoveOrInlinePrologueSafepointInstructions(currentBlock, firstBlockHasSafepoint,
                                                        inliningAllowed, config);
        }
    }
}
