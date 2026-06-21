/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.llvm

import llvm.*
import kotlinx.cinterop.*

// ============================================================================
// Kotlin-side KotlinStubGenerator (KSG)
// ============================================================================
//
// The fn-level half of LLVM's KotlinStubGenerator.cpp, ported into the Kotlin/Native
// backend so its output (function attributes) travels with the defs through
// llvm-split/llvm-link. The AsmPrinter-only GV/decl plumbing (the Kotlin_*Stub decls
// and per-fn `@<F>.KotlinStubGV`) stays in the clang-stage LLVM KSG
// (`-mllvm -enable-kotlin-stub-generator`), which re-materialises it from those attrs.
//
// Steps (runKotlinStubGenerator):
//   1. lowerInteropBridgeAnnotationsToAttrs — k2n/ktstub annotations -> fn attrs (+ strip).
//   2. markN2kCalleesFromCaller            — tag native->kotlin boundary callees "n2k".
//   3. applyStubBoundaryNoInline           — pin boundary functions noinline (for fp-unwind).
//
// The old kfunc -> <helper>Stub callsite rewrite is gone: IR codegen emits the Stub
// call directly (ContextUtils.importRtFunction / IrToBitcode.generateGCUnsafeCallStub),
// which also stays correct under incremental compilation (cached per-file .bc).
//
// Gated on enableStackmap (entry: runKsgPhase).

// ---------------------------------------------------------------------------
// Annotation parsing helpers (internal).
// ---------------------------------------------------------------------------

private fun getAnnotationString(element: LLVMValueRef?): String? {
    if (element == null || LLVMIsAConstantStruct(element) == null) return null
    val annotationField = LLVMGetOperand(element, 1) ?: return null
    // Typed-pointer IR wraps the string global in a getelementptr ConstantExpr;
    // opaque-pointer IR uses the global pointer directly.
    val globalVar = if (LLVMIsAConstantExpr(annotationField) != null) {
        if (LLVMGetConstOpcode(annotationField) != LLVMOpcode.LLVMGetElementPtr) return null
        LLVMGetOperand(annotationField, 0) ?: return null
    } else {
        annotationField
    }
    if (LLVMIsAGlobalVariable(globalVar) == null) return null
    val init = LLVMGetInitializer(globalVar) ?: return null
    if (LLVMIsAConstantDataArray(init) == null) return null
    return memScoped {
        val lengthVar = alloc<size_tVar>()
        LLVMGetAsString(init, lengthVar.ptr)?.toKString()
    }
}

private fun getAnnotatedFunction(element: LLVMValueRef?): LLVMValueRef? {
    if (element == null || LLVMIsAConstantStruct(element) == null) return null
    val functionField = LLVMGetOperand(element, 0) ?: return null
    return when {
        LLVMIsAConstantExpr(functionField) != null &&
                LLVMGetConstOpcode(functionField) == LLVMOpcode.LLVMBitCast ->
            LLVMGetOperand(functionField, 0)
        else -> functionField
    }
}

// ---------------------------------------------------------------------------
// Callsite helper
// ---------------------------------------------------------------------------

private fun stripBitcastConstantExpr(value: LLVMValueRef?): LLVMValueRef? {
    var v = value ?: return null
    while (LLVMIsAConstantExpr(v) != null && LLVMGetConstOpcode(v) == LLVMOpcode.LLVMBitCast) {
        v = LLVMGetOperand(v, 0) ?: return null
    }
    return v
}

// ---------------------------------------------------------------------------
// Phase entry.
// ---------------------------------------------------------------------------
//
// Called from TopLevelPhases.kt (compileModule / runBitcodeBackend) BEFORE the
// split=1/N branch. Pre-split is mandatory: @llvm.global.annotations is
// appending-linkage and llvm-split attaches it to a single part, so a per-part KSG
// would early-return on the others.
internal fun runKsgPhase(module: LLVMModuleRef, enableStackmap: Boolean) {
    // KSG only matters with precise stackmaps: every attr it reads is itself
    // enableStackmap-gated where applied, so OFF is a no-op anyway. Skip outright to
    // avoid the full-module walks (and Step 2's Konan_start name-fallback, the one
    // input not gated at its source).
    if (!enableStackmap) return
    runKotlinStubGenerator(module)
}

// ---------------------------------------------------------------------------
// Entry point — Steps 1 / 2 / 3. Called only from runKsgPhase.
// ---------------------------------------------------------------------------

internal fun runKotlinStubGenerator(module: LLVMModuleRef) {
    val context = LLVMGetModuleContext(module) ?: return

    // Step 1: lower k2n / ktstub bridge annotations to fn attrs and strip them
    // from @llvm.global.annotations (idempotent).
    lowerInteropBridgeAnnotationsToAttrs(module)

    // Step 2: tag native->kotlin boundary callees "n2k", per defined function.
    var fn = LLVMGetFirstFunction(module)
    while (fn != null) {
        if (LLVMIsDeclaration(fn) == 0) markN2kCalleesFromCaller(fn, context)
        fn = LLVMGetNextFunction(fn)
    }

    // Step 3: pin stub-boundary functions noinline so fp-unwind can see the Stub
    // frame edges.
    applyStubBoundaryNoInline(module)
}

// Step 2: tag the native->kotlin boundary callees of one function — a CAdapter
// bridge ("konan-fn-bridge", from CAdapterCodegen.kt) called from a ktstub wrapper,
// or the runtime entry `Konan_start`. Applied in place, deduped via the attr itself.
private fun markN2kCalleesFromCaller(caller: LLVMValueRef, context: LLVMContextRef) {
    val callerHasKtstub = hasStringFnAttr(caller, "ktstub")
    var bb = LLVMGetFirstBasicBlock(caller)
    while (bb != null) {
        var inst = LLVMGetFirstInstruction(bb)
        while (inst != null) {
            if (LLVMIsACallInst(inst) != null || LLVMIsAInvokeInst(inst) != null) {
                val callee = stripBitcastConstantExpr(LLVMGetCalledValue(inst))
                if (callee != null && LLVMIsAFunction(callee) != null && !hasStringFnAttr(callee, "n2k")) {
                    val isBridgeFromKtstub = callerHasKtstub && hasStringFnAttr(callee, "konan-fn-bridge")
                    val isKonanStart = LLVMGetValueName(callee)?.toKString() == "Konan_start"
                    if (isBridgeFromKtstub || isKonanStart) {
                        val attrKey = "n2k"
                        LLVMAddAttributeAtIndex(callee, LLVMAttributeFunctionIndex,
                                LLVMCreateStringAttribute(context, attrKey, attrKey.length, "", 0))
                    }
                }
            }
            inst = LLVMGetNextInstruction(inst)
        }
        bb = LLVMGetNextBasicBlock(bb)
    }
}

// ---------------------------------------------------------------------------
// Function-attribute helpers.
// ---------------------------------------------------------------------------

internal fun hasStringFnAttr(fn: LLVMValueRef, name: String): Boolean {
    return LLVMGetStringAttributeAtIndex(fn, LLVMAttributeFunctionIndex, name, name.length) != null
}

private fun addStringFnAttrIfMissing(fn: LLVMValueRef, context: LLVMContextRef, name: String) {
    if (hasStringFnAttr(fn, name)) return
    LLVMAddAttributeAtIndex(fn, LLVMAttributeFunctionIndex,
            LLVMCreateStringAttribute(context, name, name.length, "", 0))
}

// Step 1. Walks @llvm.global.annotations once: consumes the k2n / ktstub bridge
// entries into fn attrs and drops them from the array (other tags — has_safepoint,
// current_thread_tlv, … — are kept; downstream passes read them). Fused because
// nothing downstream reads k2n/ktstub: every reader keys off the fn attrs, which
// travel with the defs through llvm-split/llvm-link.
internal fun lowerInteropBridgeAnnotationsToAttrs(module: LLVMModuleRef) {
    val context = LLVMGetModuleContext(module) ?: return
    val annotations = LLVMGetNamedGlobal(module, "llvm.global.annotations") ?: return
    val initializer = LLVMGetInitializer(annotations) ?: return
    if (LLVMIsAConstantArray(initializer) == null) return

    val numEntries = LLVMGetNumOperands(initializer)
    val keepEntries = mutableListOf<LLVMValueRef>()
    var consumed = 0
    for (i in 0 until numEntries) {
        val entry = LLVMGetOperand(initializer, i) ?: continue
        val annotation = getAnnotationString(entry)
        if (annotation != null && annotation in CONSUMED_ANNOTATION_TAGS) {
            // Consume into a fn attr (attr name == annotation tag) and drop the entry.
            getAnnotatedFunction(entry)?.let { addStringFnAttrIfMissing(it, context, annotation) }
            consumed++
        } else {
            keepEntries.add(entry)
        }
    }
    if (consumed == 0) return

    // Rebuild @llvm.global.annotations without the consumed entries (or delete it
    // outright if nothing is left). Appending-linkage globals have no IR uses other
    // than the GV itself, so deleting/replacing is safe.
    val elementType = LLVMGetElementType(LLVMTypeOf(initializer) ?: return) ?: return
    if (keepEntries.isEmpty()) {
        LLVMDeleteGlobal(annotations)
        return
    }
    val newArrayType = LLVMArrayType(elementType, keepEntries.size) ?: return
    val newInit = memScoped {
        val refs = allocArray<LLVMValueRefVar>(keepEntries.size)
        keepEntries.forEachIndexed { idx, ref -> refs[idx] = ref }
        LLVMConstArray2(elementType, refs, keepEntries.size.toLong())
    } ?: return
    // Rename the old GV first so LLVMAddGlobal can claim the canonical name.
    val tmpName = "llvm.global.annotations.kn_strip_tmp"
    LLVMSetValueName2(annotations, tmpName, tmpName.length.toLong())
    val newGV = LLVMAddGlobal(module, newArrayType, "llvm.global.annotations") ?: return
    LLVMSetInitializer(newGV, newInit)
    LLVMSetLinkage(newGV, LLVMLinkage.LLVMAppendingLinkage)
    LLVMSetSection(newGV, "llvm.metadata")
    LLVMDeleteGlobal(annotations)
}

// Shared helper (used by Step 3).
private fun isSubTypeN2K(fn: LLVMValueRef): Boolean {
    val attr = LLVMGetStringAttributeAtIndex(fn, LLVMAttributeFunctionIndex, "stubtype", 8)
            ?: return false
    return memScoped {
        val lenVar = alloc<IntVar>()
        val valuePtr = LLVMGetStringAttributeValue(attr, lenVar.ptr) ?: return@memScoped false
        val len = lenVar.value.toInt()
        valuePtr.readBytes(len).toKString() == "export_for_cpp_runtime_k"
    }
}

// ---------------------------------------------------------------------------
// Step 3: pin stub-boundary functions noinline.
// ---------------------------------------------------------------------------
//
// fp-unwind recognises Stub frame edges in the emitted code; if the inliner folds a
// boundary function into its caller, the edge vanishes and the walker loses the
// kotlin<->native transition. So strip alwaysinline and force noinline on the four
// boundary categories:
//   * konan-fn-bridge                   — CAdapter bridge (CAdapterCodegen.kt:markKonanFnBridge).
//   * stubtype=export_for_cpp_runtime_k — SubTypeN2K runtime exports (LlvmFunctionPrototype.kt).
//   * k2n                               — Kotlin->native C bridge (CBridgeGen.kt).
//   * ktstub                            — native->kotlin C bridge wrapper (CAdapterGenerator.kt).
//
// K2RStub helpers are NOT pinned here — IR codegen emits kfunc -> helperStub calls
// directly, so inlining the bare helper can't erase a Stub edge.
private fun applyStubBoundaryNoInline(module: LLVMModuleRef) {
    val context = LLVMGetModuleContext(module) ?: return
    val alwaysinlineID = LLVMGetEnumAttributeKindForName("alwaysinline", 12)
    val noinlineID = LLVMGetEnumAttributeKindForName("noinline", 8)

    var fn = LLVMGetFirstFunction(module)
    while (fn != null) {
        if (LLVMIsDeclaration(fn) == 0 &&
                (hasStringFnAttr(fn, "konan-fn-bridge") ||
                        hasStringFnAttr(fn, "k2n") ||
                        hasStringFnAttr(fn, "ktstub") ||
                        isSubTypeN2K(fn))) {
            val alwaysinlineAttr = LLVMGetEnumAttributeAtIndex(fn, LLVMAttributeFunctionIndex, alwaysinlineID)
            if (alwaysinlineAttr != null) {
                LLVMRemoveEnumAttributeAtIndex(fn, LLVMAttributeFunctionIndex, alwaysinlineID)
            }
            if (LLVMGetEnumAttributeAtIndex(fn, LLVMAttributeFunctionIndex, noinlineID) == null) {
                val noinlineAttr = LLVMCreateEnumAttribute(context, noinlineID, 1)
                LLVMAddAttributeAtIndex(fn, LLVMAttributeFunctionIndex, noinlineAttr)
            }
        }
        fn = LLVMGetNextFunction(fn)
    }
}

// Bridge tags that Step 1 lowers to fn attrs and strips from @llvm.global.annotations
// (runtime tags has_safepoint / current_thread_tlv / … are kept). K2RStub is not here —
// helpers come from the canonical `K2RStubFunctions.names`.
private val CONSUMED_ANNOTATION_TAGS = setOf("k2n", "ktstub")
