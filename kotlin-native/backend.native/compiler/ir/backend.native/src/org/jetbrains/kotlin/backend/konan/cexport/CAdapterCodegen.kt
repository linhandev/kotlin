/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.cexport

import llvm.*
import org.jetbrains.kotlin.backend.konan.NativeGenerationState
import org.jetbrains.kotlin.backend.konan.llvm.*
import org.jetbrains.kotlin.backend.konan.llvm.CodeGenerator
import org.jetbrains.kotlin.backend.konan.llvm.ContextUtils
import org.jetbrains.kotlin.backend.konan.llvm.ExceptionHandler
import org.jetbrains.kotlin.backend.konan.llvm.Lifetime
import org.jetbrains.kotlin.backend.konan.lower.getLoweredConstructorFunction
import org.jetbrains.kotlin.backend.konan.lower.getObjectClassInstanceFunction
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.util.isOverridable
import org.jetbrains.kotlin.resolve.DescriptorUtils

/**
 * Second phase of C Export: build bitcode bridges from C wrappers to Kotlin functions.
 */
internal class CAdapterCodegen(
    private val codegen: CodeGenerator,
    override val generationState: NativeGenerationState,
) : ContextUtils {

    fun buildAllAdaptersRecursively(elements: CAdapterExportedElements) {
        val top = elements.scopes.single()
        assert(top.kind == ScopeKind.TOP)
        top.generateCAdapters(this::buildCAdapter)
    }

    private fun ExportedElementScope.generateCAdapters(builder: (ExportedElement) -> Unit) {
        this.elements.forEach { builder(it) }
        this.scopes.forEach { it.generateCAdapters(builder) }
    }

    // Tag a CAdapter bridge function (the LLVM-side `_konan_function_*` target
    // of the C-side `_impl` wrapper) with the "konan-fn-bridge" fn attr so
    // KSG step 4 (markStubFunction) can dispatch off the attribute instead of
    // doing a brittle `calleeName.startsWith("_konan_function_")` name match.
    // Gated on enableStackmap to keep the OFF dist attribute-set-equivalent
    // to the pre-stackmap baseline.
    private fun markKonanFnBridge(funcName: String) {
        if (!context.config.enableStackmap) return
        val fn = LLVMGetNamedFunction(llvm.module, funcName) ?: return
        val attr = LLVMCreateStringAttribute(llvm.llvmContext, "konan-fn-bridge", 15, "", 0)
        LLVMAddAttributeAtIndex(fn, LLVMAttributeFunctionIndex, attr)
    }

    private fun buildCAdapter(exportedElement: ExportedElement): Unit = with(exportedElement) {
        when {
            isFunction -> {
                val function = declaration as FunctionDescriptor
                val irFunction = irSymbol.owner.let {
                    it as? IrSimpleFunction ?: context.getLoweredConstructorFunction(it as IrConstructor)
                }
                cname = "_konan_function_${owner.nextFunctionIndex()}"
                val signature = LlvmFunctionSignature(irFunction, this@CAdapterCodegen)
                val bridgeFunctionProto = signature.toProto(cname, null, LLVMLinkage.LLVMExternalLinkage)
                // If function is virtual, we need to resolve receiver properly.
                generateFunction(codegen, bridgeFunctionProto) {
                    val callee = if (!DescriptorUtils.isTopLevelDeclaration(function) && irFunction.isOverridable) {
                        codegen.getVirtualFunctionTrampoline(irFunction)
                    } else {
                        // KT-45468: Alias insertion may not be handled by LLVM properly, in case callee is in the cache.
                        // Hence, insert not an alias but a wrapper, hoping it will be optimized out later.
                        codegen.llvmFunction(irFunction)
                    }

                    val args = signature.parameterTypes.indices.map { param(it) }
                    val result = call(callee, args, exceptionHandler = ExceptionHandler.Caller, verbatim = true)
                    ret(result)
                }
                markKonanFnBridge(cname)
            }
            isClass -> {
                val irClass = irSymbol.owner as IrClass
                cname = "_konan_function_${owner.nextFunctionIndex()}"
                // Produce type getter.
                // OHOS fix: simple type-getter (just `ret ptr @typeinfo`) has no gc.statepoint,
                // so it must not use the kotlin-native stackmap protocol on OHOS ELF.
                // Otherwise OHOS LLVM's compressed-stackmap + mark-kotlin-function passes emit
                // inline asm referencing `.Lstackmap_start._konan_function_XX_type` which is never defined.
                val getTypeFunction = kGetTypeFuncType.toProto(
                        "${cname}_type",
                        null,
                        LLVMLinkage.LLVMExternalLinkage
                ).createLlvmFunction(context, llvm.module).also { it.clearGcCollector() }
                val builder = LLVMCreateBuilderInContext(llvm.llvmContext)!!
                val bb = getTypeFunction.addBasicBlock(llvm.llvmContext)
                LLVMPositionBuilderAtEnd(builder, bb)
                LLVMBuildRet(builder, irClass.typeInfoPtr.llvm)
                LLVMDisposeBuilder(builder)
                // ${cname}_type intentionally NOT tagged with konan-fn-bridge:
                // it's just `ret @typeinfo` with `clearGcCollector()` applied
                // above (line 92). No gc.statepoint is emitted, no GC root scan
                // happens through this frame, and fp-unwind doesn't need to
                // recognize a Stub boundary here. Adding the attr would route
                // every type-getter call through `bl Kotlin_N2KStub` for zero
                // benefit.
                // Produce instance getter if needed.
                if (isSingletonObject) {
                    val functionProto = kGetObjectFuncType.toProto(
                            "${cname}_instance",
                            null,
                            LLVMLinkage.LLVMExternalLinkage
                    )
                    generateFunction(codegen, functionProto) {
                        val value = call(
                            codegen.llvmFunction(context.getObjectClassInstanceFunction(irClass)),
                            emptyList(),
                            Lifetime.GLOBAL,
                            ExceptionHandler.Caller,
                            false,
                            returnSlot
                        )
                        ret(value)
                    }
                    markKonanFnBridge("${cname}_instance")
                }
            }
            isEnumEntry -> {
                // Produce entry getter.
                cname = "_konan_function_${owner.nextFunctionIndex()}"
                val functionProto = kGetObjectFuncType.toProto(
                        cname,
                        null,
                        LLVMLinkage.LLVMExternalLinkage
                )
                generateFunction(codegen, functionProto) {
                    val irEnumEntry = irSymbol.owner as IrEnumEntry
                    val value = getEnumEntry(irEnumEntry, ExceptionHandler.Caller)
                    ret(value)
                }
                markKonanFnBridge(cname)
            }
        }
    }

    private val kGetTypeFuncType = LlvmFunctionSignature(LlvmRetType(codegen.kTypeInfoPtr, isObjectType = false))

    // Abstraction leak for slot :(.
    private val kGetObjectFuncType = LlvmFunctionSignature(codegen.kObjHeaderPtrReturnType, listOf(LlvmParamType(codegen.kObjHeaderPtrPtr)))
}