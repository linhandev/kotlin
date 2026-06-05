/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.driver.phases

import llvm.*
import kotlinx.cinterop.*
import org.jetbrains.kotlin.backend.common.phaser.PhaseEngine
import org.jetbrains.kotlin.backend.common.phaser.createSimpleNamedCompilerPhase
import org.jetbrains.kotlin.backend.konan.*
import org.jetbrains.kotlin.backend.konan.driver.BasicPhaseContext
import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.config.LoggingContext
import org.jetbrains.kotlin.backend.konan.driver.utilities.LlvmIrHolder
import org.jetbrains.kotlin.backend.konan.driver.utilities.getDefaultLlvmModuleActions
import org.jetbrains.kotlin.backend.konan.driver.utilities.createTempFiles
import org.jetbrains.kotlin.backend.konan.llvm.verifyModule
import org.jetbrains.kotlin.backend.konan.llvm.parseBitcodeFile
import org.jetbrains.kotlin.backend.konan.optimizations.RemoveRedundantSafepointsPass
import org.jetbrains.kotlin.backend.konan.optimizations.removeMultipleThreadDataLoads
import org.jetbrains.kotlin.konan.target.SanitizerKind
import java.io.File
import java.io.IOException
import kotlin.coroutines.*
import kotlinx.coroutines.*

internal fun addAlwaysInline(module: LLVMModuleRef) {
    var globalAnnotations = LLVMGetNamedGlobal(module, "llvm.global.annotations")
    if (globalAnnotations == null) {
        return
    }
    var initializer = LLVMGetInitializer(globalAnnotations)
    if (LLVMIsAConstantArray(initializer) == null) {
        return
    }
    var numElements = LLVMGetNumOperands(initializer)
    var i = 0
    while (i < numElements) {
        var element = LLVMGetOperand(initializer, i)
        if (LLVMIsAConstantStruct(element) == null) {
            i++
            continue
        }
        var functionField = LLVMGetOperand(element, 0)
        val function = when {
            LLVMIsAConstantExpr(functionField) != null &&
                    LLVMGetConstOpcode(functionField) == LLVMOpcode.LLVMBitCast ->
                LLVMGetOperand(functionField, 0)
            else -> functionField
        }
        var annotationField = LLVMGetOperand(element, 1)

        if (LLVMIsAConstantExpr(annotationField) != null) {
            var opcode = LLVMGetConstOpcode(annotationField)
            if (opcode != LLVMOpcode.LLVMGetElementPtr) {
                i++
                continue
            }
        }
        var globalVar = LLVMGetOperand(annotationField, 0);
        if (globalVar != null && LLVMIsAGlobalVariable(globalVar) != null) {
            // Get the initial value of the global variable (string)
            var initializer = LLVMGetInitializer(globalVar)

            if (LLVMIsAConstantDataArray(initializer) != null) {
                memScoped {
                    val lengthVar = alloc<size_tVar>()
                    var annotation = LLVMGetAsString(initializer, lengthVar.ptr)
                    if (annotation?.toKString() == "K2RStub") {
                        val context = LLVMGetModuleContext(module)
                        val delayInlineAttr = LLVMGetStringAttributeAtIndex(function, LLVMAttributeFunctionIndex, "delayinline", 11)
                        if (delayInlineAttr != null) {
                            val noinlineID = LLVMGetEnumAttributeKindForName("noinline", 8)
                            LLVMRemoveEnumAttributeAtIndex(function, LLVMAttributeFunctionIndex, noinlineID)

                            LLVMRemoveStringAttributeAtIndex(function, LLVMAttributeFunctionIndex, "delayinline", 11)
                            val alwaysinlineID = LLVMGetEnumAttributeKindForName("alwaysinline", 12)
                            val alwaysinlineAttr = LLVMCreateEnumAttribute(context, alwaysinlineID, 0)
                            LLVMAddAttributeAtIndex(function, LLVMAttributeFunctionIndex, alwaysinlineAttr)
                        }
                    }
                }
            }
        }
        i++
    }
}

internal fun disableBoundryFunctionInline(module: LLVMModuleRef) {
    var currentFunction = LLVMGetFirstFunction(module);

    while (currentFunction != null) {
        val context = LLVMGetModuleContext(module)
        if (LLVMIsDeclaration(currentFunction) != 0) {
            currentFunction = LLVMGetNextFunction(currentFunction);
            continue;
        }
        val funcName = LLVMGetValueName(currentFunction)?.toKString()
        // n2k callee
        if (funcName?.startsWith("_konan_function_") == true) {
            val noinlineID = LLVMGetEnumAttributeKindForName("noinline", 8)
            val alwaysinlineID = LLVMGetEnumAttributeKindForName("alwaysinline", 12)
            val alwaysinlineAttr = LLVMGetEnumAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, alwaysinlineID)
            if (alwaysinlineAttr != null) {
                LLVMRemoveEnumAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, alwaysinlineID)
            }
            val noinlineAttr = LLVMCreateEnumAttribute(context, noinlineID, 1)
            LLVMAddAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, noinlineAttr)
        }
        // export_for_cpp_runtime_k, n2k callee
        val stubTypeAttr = LLVMGetStringAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, "stubtype", 8)
        if (stubTypeAttr == null) {
            currentFunction = LLVMGetNextFunction(currentFunction);
            continue;
        }
        memScoped {
            val attrLength = alloc<IntVar>()
            val attrValue = LLVMGetStringAttributeValue(stubTypeAttr, attrLength.ptr)

            if (attrValue != null) {
                val len = attrLength.value.toInt()
                val value = attrValue.readBytes(len).toKString()
                if (value == "export_for_cpp_runtime_k") {
                    val noinlineID = LLVMGetEnumAttributeKindForName("noinline", 8)
                    val alwaysinlineID = LLVMGetEnumAttributeKindForName("alwaysinline", 12)
                    val alwaysinlineAttr = LLVMGetEnumAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, alwaysinlineID)
                    if (alwaysinlineAttr != null) {
                        LLVMRemoveEnumAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, alwaysinlineID)
                    }
                    val noinlineAttr = LLVMCreateEnumAttribute(context, noinlineID, 1)
                    LLVMAddAttributeAtIndex(currentFunction, LLVMAttributeFunctionIndex, noinlineAttr)
                }
            }
        }
        currentFunction = LLVMGetNextFunction(currentFunction);
    }
}

private fun getAnnotationString(element: LLVMValueRef?): String? {
    if (element == null || LLVMIsAConstantStruct(element) == null) return null
    var annotationField = LLVMGetOperand(element, 1) ?: return null
    if (LLVMIsAConstantExpr(annotationField) != null) {
        if (LLVMGetConstOpcode(annotationField) != LLVMOpcode.LLVMGetElementPtr) return null
    }
    val globalVar = LLVMGetOperand(annotationField, 0) ?: return null
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

internal fun addDelayInline(module: LLVMModuleRef) {
    var globalAnnotations = LLVMGetNamedGlobal(module, "llvm.global.annotations")
    if (globalAnnotations == null) {
        return
    }
    var initializer = LLVMGetInitializer(globalAnnotations)
    if (LLVMIsAConstantArray(initializer) == null) {
        return
    }
    var numElements = LLVMGetNumOperands(initializer)

    // Indices of entries to keep (non-k2n) vs remove (k2n)
    val keepIndices = mutableListOf<Int>()

    var i = 0
    while (i < numElements) {
        var element = LLVMGetOperand(initializer, i)
        val annotation = getAnnotationString(element)
        val function = getAnnotatedFunction(element)

        if (annotation != null && function != null) {
            val context = LLVMGetModuleContext(module)
            if (annotation == "K2RStub") {
                val alwaysinlineID = LLVMGetEnumAttributeKindForName("alwaysinline", 12)
                val alwaysinlineAttr = LLVMGetEnumAttributeAtIndex(function, LLVMAttributeFunctionIndex, alwaysinlineID)
                if (alwaysinlineAttr != null) {
                    LLVMRemoveEnumAttributeAtIndex(function, LLVMAttributeFunctionIndex, alwaysinlineID)
                    val delayInlineAttr = LLVMCreateStringAttribute(context, "delayinline", 11, "true", 4)
                    LLVMAddAttributeAtIndex(function, LLVMAttributeFunctionIndex, delayInlineAttr)
                }
                val noinlineID = LLVMGetEnumAttributeKindForName("noinline", 8)
                val noinlineAttr = LLVMCreateEnumAttribute(context, noinlineID, 0)
                LLVMAddAttributeAtIndex(function, LLVMAttributeFunctionIndex, noinlineAttr)
                keepIndices.add(i)
            } else if (annotation == "ktstub" || annotation == "k2n") {
                val alwaysinlineID = LLVMGetEnumAttributeKindForName("alwaysinline", 12)
                val alwaysinlineAttr = LLVMGetEnumAttributeAtIndex(function, LLVMAttributeFunctionIndex, alwaysinlineID)
                if (alwaysinlineAttr != null) {
                    LLVMRemoveEnumAttributeAtIndex(function, LLVMAttributeFunctionIndex, alwaysinlineID)
                }
                val noinlineID = LLVMGetEnumAttributeKindForName("noinline", 8)
                val noinlineAttr = LLVMCreateEnumAttribute(context, noinlineID, 1)
                LLVMAddAttributeAtIndex(function, LLVMAttributeFunctionIndex, noinlineAttr)
                // Transfer k2n annotation to function attribute for LLVM 15 backend,
                // then exclude this entry from annotations to unblock GlobalDCE.
                if (annotation == "k2n") {
                    val k2nAttr = LLVMCreateStringAttribute(context, "k2n", 3, "true", 4)
                    LLVMAddAttributeAtIndex(function, LLVMAttributeFunctionIndex, k2nAttr)
                } else {
                    // ktstub: keep in annotations (needed by other passes)
                    keepIndices.add(i)
                }
            } else {
                // Unknown annotation, keep it
                keepIndices.add(i)
            }
        } else {
            keepIndices.add(i)
        }
        i++
    }

    // Rebuild @llvm.global.annotations without k2n entries to unblock GlobalDCE
    if (keepIndices.size < numElements) {
        if (keepIndices.isEmpty()) {
            LLVMDeleteGlobal(globalAnnotations)
        } else {
            val elementType = LLVMTypeOf(LLVMGetOperand(initializer, 0))
            memScoped {
                val kept = allocArray<LLVMValueRefVar>(keepIndices.size)
                for ((idx, origIdx) in keepIndices.withIndex()) {
                    kept[idx] = LLVMGetOperand(initializer, origIdx)
                }
                val newArray = LLVMConstArray(elementType, kept, keepIndices.size)
                LLVMSetInitializer(globalAnnotations, newArray)
            }
        }
    }
}

internal data class WriteBitcodeFileInput(
        override val llvmModule: LLVMModuleRef,
        val outputFile: File,
) : LlvmIrHolder

/**
 * Write in-memory LLVM module to filesystem as a bitcode.
 */
internal val WriteBitcodeFilePhase = createSimpleNamedCompilerPhase<PhaseContext, WriteBitcodeFileInput>(
        "WriteBitcodeFile",
) { context, (llvmModule, outputFile) ->
    // Insert `_main` after pipeline, so we won't worry about optimizations corrupting entry point.
    insertAliasToEntryPoint(context, llvmModule)
    LLVMWriteBitcodeToFile(llvmModule, outputFile.canonicalPath)
}

internal val CheckExternalCallsPhase = createSimpleNamedCompilerPhase<NativeGenerationState, Unit>(
        name = "CheckExternalCalls",
        postactions = getDefaultLlvmModuleActions(),
) { context, _ ->
    checkLlvmModuleExternalCalls(context)
}

/**
 * Rewrites globals for external calls checker after optimizer run.
 */
internal val RewriteExternalCallsCheckerGlobals = createSimpleNamedCompilerPhase<NativeGenerationState, Unit>(
        name = "RewriteExternalCallsCheckerGlobals",
        postactions = getDefaultLlvmModuleActions(),
) { context, _ ->
    addFunctionsListSymbolForChecker(context)
}

internal data class RewriteExternalCallsCheckerGlobalsInput(
        val generationState: NativeGenerationState,
        val module: LLVMModuleRef
)

internal val RewriteExternalCallsCheckerGlobalsWithModule = createSimpleNamedCompilerPhase<PhaseContext, RewriteExternalCallsCheckerGlobalsInput>(
        name = "RewriteExternalCallsCheckerGlobalsWithModule",
) { _, input ->
    addFunctionsListSymbolForCheckerCoroutines(input.generationState, input.module)
}

internal class OptimizationState(
        konanConfig: KonanConfig,
        val llvmConfig: LlvmPipelineConfig
) : BasicPhaseContext(konanConfig)

internal fun optimizationPipelinePass(name: String, pipeline: (LlvmPipelineConfig, LoggingContext) -> LlvmOptimizationPipeline) =
        createSimpleNamedCompilerPhase<OptimizationState, LLVMModuleRef>(
                name = name,
                postactions = getDefaultLlvmModuleActions(),
        ) { context, module ->
            pipeline(context.llvmConfig, context).use {
                it.execute(module)
            }
        }

internal val MandatoryBitcodeLLVMPostprocessingPhase = optimizationPipelinePass(
        name = "MandatoryBitcodeLLVMPostprocessingPhase",
        pipeline = ::MandatoryOptimizationPipeline,
)

internal val ModuleBitcodeOptimizationPhase = optimizationPipelinePass(
        name = "ModuleBitcodeOptimization",
        pipeline = ::ModuleOptimizationPipeline,
)

internal val LTOBitcodeOptimizationPhase = optimizationPipelinePass(
        name = "LTOBitcodeOptimization",
        pipeline = ::LTOOptimizationPipeline
)

internal val ThreadSanitizerPhase = optimizationPipelinePass(
        name = "ThreadSanitizerPhase",
        pipeline = ::ThreadSanitizerPipeline
)

internal val StackProtectorPhase = createSimpleNamedCompilerPhase<OptimizationState, LLVMModuleRef>(
        name = "StackProtectorPhase",
        postactions = getDefaultLlvmModuleActions(),
        op = ::applySspAttributes
)

internal val AddressSanitizerPhase = optimizationPipelinePass(
        name = "AddressSanitizerPhase",
        pipeline = ::AddressSanitizerPipeline
)

internal val HWASanSanitizerPhase = optimizationPipelinePass(
        name = "HWASanSanitizerPhase",
        pipeline = ::HWASanSanitizerPipeline
)

internal val RemoveRedundantSafepointsPhase = createSimpleNamedCompilerPhase<BitcodePostProcessingContext, Unit>(
        name = "RemoveRedundantSafepoints",
        postactions = getDefaultLlvmModuleActions(),
        op = { context, _ ->
            RemoveRedundantSafepointsPass().runOnModule(
                    module = context.llvm.module,
                    isSafepointInliningAllowed = context.shouldInlineSafepoints(),
                    // Pass !enableStackmap to libllvmext: OFF mode (shadow-stack
                    // baseline) re-enables the force-inline of the first eligible
                    // safepoint per basic block. ON mode does not. This replaces
                    // the legacy compile-time `#ifndef ENABLE_STACKMAP` gate and
                    // is now per-target via KonanConfig.enableStackmap.
                    forceInlineFirstEligible = !context.config.enableStackmap,
            )
        }
)

internal val OptimizeTLSDataLoadsPhase = createSimpleNamedCompilerPhase<BitcodePostProcessingContext, Unit>(
        name = "OptimizeTLSDataLoads",
        postactions = getDefaultLlvmModuleActions(),
        op = { context, _ -> removeMultipleThreadDataLoads(context) }
)

internal val CStubsPhase = createSimpleNamedCompilerPhase<NativeGenerationState, Unit>(
        name = "CStubs",
        postactions = getDefaultLlvmModuleActions(),
        op = { context, _ -> produceCStubs(context) }
)

internal val LinkBitcodeDependenciesPhase = createSimpleNamedCompilerPhase<NativeGenerationState, List<File>>(
        name = "LinkBitcodeDependencies",
        postactions = getDefaultLlvmModuleActions(),
        op = { context, input -> linkBitcodeDependencies(context, input) }
)

internal val VerifyBitcodePhase = createSimpleNamedCompilerPhase<PhaseContext, LLVMModuleRef>(
        name = "VerifyBitcode",
        op = { _, llvmModule -> verifyModule(llvmModule) }
)

internal val PrintBitcodePhase = createSimpleNamedCompilerPhase<PhaseContext, LLVMModuleRef>(
        name = "PrintBitcode",
        op = { _, llvmModule -> LLVMDumpModule(llvmModule) }
)

internal fun <T : BitcodePostProcessingContext> PhaseEngine<T>.runBitcodePostProcessing() {
    val optimizationConfig = createLTOFinalPipelineConfig(
            context,
            context.llvm.targetTriple,
            closedWorld = context.config.isFinalBinary,
            timePasses = context.config.phaseConfig.needProfiling,
    )
    mergeLlvmCompilerUsedIntoLlvmUsed(this@runBitcodePostProcessing.context.llvmModule)
    useContext(OptimizationState(context.config, optimizationConfig)) {
        val module = this@runBitcodePostProcessing.context.llvmModule
        it.runPhase(StackProtectorPhase, module)
        it.runPhase(MandatoryBitcodeLLVMPostprocessingPhase, module)
        it.runPhase(ModuleBitcodeOptimizationPhase, module)
        it.runPhase(LTOBitcodeOptimizationPhase, module)
        when (context.config.sanitizer) {
            SanitizerKind.THREAD -> it.runPhase(ThreadSanitizerPhase, module)
            SanitizerKind.ADDRESS -> it.runPhase(AddressSanitizerPhase, module)
            SanitizerKind.HWADDRESS -> it.runPhase(HWASanSanitizerPhase, module)
            null -> {}
        }
    }
    // RemoveRedundantSafepointsPhase erases redundant prologue safepoint calls
    // and (under libllvmext built with `-DENABLE_STACKMAP=0`) force-inlines the
    // first eligible safepoint. Run it unconditionally for both ON and OFF: it
    // operates on AS0 IR with no `cast<Ty>` assertion, and gating it off
    // regresses throughput significantly.
    runPhase(RemoveRedundantSafepointsPhase)
    if (context.config.optimizationsEnabled) {
        runPhase(OptimizeTLSDataLoadsPhase)
    }
}

private data class SavedLlvmUsed(
    val arrayType: LLVMTypeRef,
    val constantArray: LLVMValueRef,
    val numElements: Int
)

private fun collectUsedSymbolNames(module: LLVMModuleRef, globalName: String): List<String> {
    val usedGlobal = LLVMGetNamedGlobal(module, globalName) ?: return emptyList()
    val initializer = LLVMGetInitializer(usedGlobal) ?: return emptyList()
    val arrayType = LLVMTypeOf(initializer) ?: return emptyList()
    val numElements = LLVMGetArrayLength(arrayType)

    return (0 until numElements).mapNotNull { i ->
        var operand = LLVMGetOperand(initializer, i) ?: return@mapNotNull null
        while (LLVMIsAConstantExpr(operand) != null) {
            operand = LLVMGetOperand(operand, 0) ?: return@mapNotNull null
        }
        LLVMGetValueName(operand)?.toKString()?.takeIf { it.isNotEmpty() }
    }
}

private fun mergeLlvmCompilerUsedIntoLlvmUsed(module: LLVMModuleRef) {
    val mergedNames = linkedSetOf<String>().apply {
        addAll(collectUsedSymbolNames(module, "llvm.used"))
        addAll(collectUsedSymbolNames(module, "llvm.compiler.used"))
    }

    if (mergedNames.isEmpty()) return

    val context = LLVMGetModuleContext(module) ?: return
    val ptrType = LLVMPointerTypeInContext(context, 0) ?: return
    val elements = mergedNames.mapNotNull { symbolName ->
        val value = LLVMGetNamedGlobal(module, symbolName)
            ?: LLVMGetNamedFunction(module, symbolName)
            ?: return@mapNotNull null
        LLVMConstBitCast(value, ptrType)
    }
    if (elements.isEmpty()) return

    val existingUsed = LLVMGetNamedGlobal(module, "llvm.used")
    if (existingUsed != null) {
        LLVMDeleteGlobal(existingUsed)
    }

    val arrayType = LLVMArrayType(ptrType, elements.size) ?: return
    memScoped {
        val elementsArray = allocArrayOf(elements)
        val newArrayValue = LLVMConstArray(ptrType, elementsArray, elements.size) ?: return@memScoped
        val newUsed = LLVMAddGlobal(module, arrayType, "llvm.used") ?: return@memScoped
        LLVMSetInitializer(newUsed, newArrayValue)
        LLVMSetLinkage(newUsed, LLVMLinkage.LLVMAppendingLinkage)
        LLVMSetSection(newUsed, "llvm.metadata")
    }
}

private fun saveLlvmUsedComplete(module: LLVMModuleRef): SavedLlvmUsed? {
    val llvmUsed = LLVMGetNamedGlobal(module, "llvm.used") ?: return null

    val initializer = LLVMGetInitializer(llvmUsed) ?: return null
    val arrayType = LLVMTypeOf(initializer) ?: return null
    val numElements = LLVMGetArrayLength(arrayType)
    return SavedLlvmUsed(arrayType, initializer, numElements)
}

private fun restoreLlvmUsedComplete(module: LLVMModuleRef, savedUsed: SavedLlvmUsed?) {
    if (savedUsed == null) {
        println("  No saved llvm.used to restore")
        return
    }

    // delete ori llvm.used
    val existingUsed = LLVMGetNamedGlobal(module, "llvm.used")
    if (existingUsed != null) {
        LLVMDeleteGlobal(existingUsed)
    }

    val context = LLVMGetModuleContext(module) ?: throw RuntimeException("Cannot get module context")

    val ptrType = LLVMPointerTypeInContext(context, 0) ?: throw RuntimeException("Cannot create ptr type")

    // get elements from llvm.used
    val elements = (0 until savedUsed.numElements).mapNotNull { i ->
        var operand = LLVMGetOperand(savedUsed.constantArray, i) ?: return@mapNotNull null

        //  bitcast/constantexpr，get symbol
        while (LLVMIsAConstantExpr(operand) != null) {
            operand = LLVMGetOperand(operand, 0) ?: return@mapNotNull null
        }

        val symbolName = LLVMGetValueName(operand)?.toKString()
        if (symbolName.isNullOrEmpty()) {
            println("    Warning: Empty symbol name at index $i")
            return@mapNotNull null
        }

        // find symbol in new module
        val newValue = LLVMGetNamedGlobal(module, symbolName)
                       ?: LLVMGetNamedFunction(module, symbolName)

        if (newValue == null) {
            println("    Warning: Symbol not found in new module: $symbolName")
            return@mapNotNull null
        }

        LLVMConstBitCast(newValue, ptrType)
    }

    if (elements.isEmpty()) {
        println("  Error: No elements could be restored")
        return
    }

    val arrayType = LLVMArrayType(ptrType, elements.size) ?: run {
        println("  Error: Cannot create array type")
        return
    }

    memScoped {
        val elementsArray = allocArrayOf(elements)
        val newArrayValue = LLVMConstArray(ptrType, elementsArray, elements.size) ?: run {
            println("  Error: Cannot create constant array")
            return@memScoped
        }

        val newUsed = LLVMAddGlobal(module, arrayType, "llvm.used") ?: run {
            println("  Error: Cannot add llvm.used global")
            return@memScoped
        }
        LLVMSetInitializer(newUsed, newArrayValue)
        LLVMSetLinkage(newUsed, LLVMLinkage.LLVMAppendingLinkage)
        LLVMSetSection(newUsed, "llvm.metadata")
    }

    // check
   val verifyUsed = LLVMGetNamedGlobal(module, "llvm.used")
}

internal fun linkBitcodeFilesWithLlvmLink(context: PhaseContext, inputFiles: List<String>, outputFile: String) {
    val platform = context.config.platform
    val llvmLinkPath = "${platform.absoluteLlvmHome}/bin/llvm-link"
    val command = mutableListOf<String>().apply {
        add(llvmLinkPath)
        add("-o")
        add(outputFile)
        addAll(inputFiles)
    }
    val processBuilder = ProcessBuilder(command)
    processBuilder.redirectErrorStream(true)
    val process = processBuilder.start()
    val output = process.inputStream.bufferedReader().readText()
    val exitCode = process.waitFor()

    if (exitCode != 0) {
        throw RuntimeException("llvm-link failed with exit code $exitCode: $output")
    }

    val outputExists = File(outputFile).exists()
    val outputSize = if (outputExists) File(outputFile).length() else 0

    if (!outputExists || outputSize == 0L) {
        throw RuntimeException("llvm-link produced empty or missing output file")
    }
}

internal fun splitBitcodeFile(context: PhaseContext, inputBitcodePath: String, numPartitions: UInt, outputPrefix: String) {
    val command = listOf(
        context.config.llvmSplitPath,
        "-j=$numPartitions",
        "-o=$outputPrefix",
        "--preserve-locals",
        inputBitcodePath
    )
    println("llvm-split command: ${command.joinToString(" ")}")
    val processBuilder = ProcessBuilder(command)
    processBuilder.redirectErrorStream(true)

    try {
        val process = processBuilder.start()
        val exitCode = process.waitFor()

        if (exitCode != 0) {
            val errorOutput = process.inputStream.bufferedReader().readText()
            throw RuntimeException("llvm-split failed with exit code $exitCode: $errorOutput")
        }
    } catch (e: IOException) {
        throw RuntimeException("Failed to execute llvm-split: ${e.message}", e)
    }
}

internal fun <T : BitcodePostProcessingContext> PhaseEngine<T>.runBitcodePostProcessingCoroutines(bitcodeFileOri: java.io.File) {
    var bitcodeFile: File? = null
    var savedUsed: SavedLlvmUsed? = null
    val tempFiles = createTempFiles(context.config, null)
    val bitcodeFiletmp = tempFiles.create(context.config.shortModuleName ?: "tmp_ori", ".bc")
    val originalModule = this@runBitcodePostProcessingCoroutines.context.llvmModule
    bitcodeFile = File(bitcodeFiletmp.toString())
    savedUsed = saveLlvmUsedComplete(originalModule)
    LLVMWriteBitcodeToFile(originalModule, bitcodeFile!!.absolutePath)
    val outputPrefix = bitcodeFile!!.absolutePath.removeSuffix(".bc") + "_part_"
    splitBitcodeFile(context, bitcodeFile!!.absolutePath, context.config.splitBCfile, outputPrefix)
    for (i in 0 until context.config.splitBCfile.toInt()) {
        val partFile = "${bitcodeFile!!.absolutePath.removeSuffix(".bc")}_part_$i"
    }

    val processedModules = runBlocking {
        val jobs = (0 until context.config.splitBCfile.toInt()).map { i ->
            async(Dispatchers.Default) {
                val partFile = "${bitcodeFile?.absolutePath?.removeSuffix(".bc") ?: "unknown"}_part_$i"
                val independentContext = LLVMContextCreate() ?: throw OutOfMemoryError("Failed to create LLVM context")
                try {
                    val optimizationConfig = createLTOFinalPipelineConfig(
                            context,
                            context.llvm.targetTriple,
                            closedWorld = false,
                            timePasses = context.config.phaseConfig.needProfiling,
                    )
                    useContext(OptimizationState(context.config, optimizationConfig)) { bitcodeEngine ->
                        if (File(partFile).exists()) {
                            val partModule = parseBitcodeFile(context, context.messageCollector, independentContext, partFile)
                            bitcodeEngine.runPhase(MandatoryBitcodeLLVMPostprocessingPhase, partModule)
                            bitcodeEngine.runPhase(ModuleBitcodeOptimizationPhase, partModule)
                            val tempFile = "${bitcodeFile!!.absolutePath.removeSuffix(".bc")}_opt_temp_$i.bc"
                            LLVMWriteBitcodeToFile(partModule, tempFile)
                            tempFile
                        } else {
                            null
                        }
                    }
                } finally {
                    LLVMContextDispose(independentContext)
                }
            }
        }
        jobs.awaitAll().filterNotNull()
    }

    if (processedModules.isNotEmpty()) {
        println("=== Success using parallel ===")

        val tempBitcodeFiles = mutableListOf<String>()
        processedModules.forEachIndexed { index, processedModule ->
            val tempFile = "${bitcodeFile!!.absolutePath.removeSuffix(".bc")}_opt_temp_$index.bc"
            tempBitcodeFiles.add(tempFile)
        }

        var bitcodeFileOriFinal: File? = null
        bitcodeFileOriFinal = File(bitcodeFileOri.toString())
        linkBitcodeFilesWithLlvmLink(context, tempBitcodeFiles, bitcodeFileOriFinal.absolutePath)

        val tmpContext = LLVMContextCreate() ?: throw RuntimeException("Failed to create context for merged module")
        var moduleTmp = parseBitcodeFile(context, context.messageCollector, tmpContext, bitcodeFileOriFinal.absolutePath)
                ?: throw RuntimeException("Failed to parse merged bitcode file")
        // restore llvm.used
        restoreLlvmUsedComplete(moduleTmp, savedUsed)
        mergeLlvmCompilerUsedIntoLlvmUsed(moduleTmp)

        // Create a new BitcodePostProcessingContext for moduleTmp which makes the phases operate on the correct module
        val tmpBitcodeContext = BitcodePostProcessingContextImpl(
            context.config,
            moduleTmp,
            tmpContext
        )

        useContext(tmpBitcodeContext) { tmpPhaseEngine ->
            val ltoOptimizationConfig = createLTOFinalPipelineConfig(
                context,
                tmpBitcodeContext.llvm.targetTriple,
                closedWorld = context.config.isFinalBinary,
                timePasses = context.config.phaseConfig.needProfiling,
            )
            tmpPhaseEngine.useContext(OptimizationState(context.config, ltoOptimizationConfig)) { bitcodeEngine ->
                bitcodeEngine.runPhase(LTOBitcodeOptimizationPhase, moduleTmp)
                when (context.config.sanitizer) {
                    SanitizerKind.THREAD -> bitcodeEngine.runPhase(ThreadSanitizerPhase, moduleTmp)
                    SanitizerKind.ADDRESS -> bitcodeEngine.runPhase(AddressSanitizerPhase, moduleTmp)
                    SanitizerKind.HWADDRESS -> bitcodeEngine.runPhase(HWASanSanitizerPhase, moduleTmp)
                    null -> {}
                }
            }
            // Run RemoveRedundantSafepointsPhase unconditionally for both ON and
            // OFF: the OFF baseline runs the phase safely and gating it off
            // regresses throughput.
            tmpPhaseEngine.runPhase(RemoveRedundantSafepointsPhase)
            if (context.config.optimizationsEnabled) {
                tmpPhaseEngine.runPhase(OptimizeTLSDataLoadsPhase)
            }

            // Use the new phase that accepts module as explicit parameter
            val originalNativeState = this@runBitcodePostProcessingCoroutines.context as NativeGenerationState
            val checkExternalCalls = context.config.checkStateAtExternalCalls
            if (checkExternalCalls) {
                tmpPhaseEngine.runPhase(
                    RewriteExternalCallsCheckerGlobalsWithModule,
                    RewriteExternalCallsCheckerGlobalsInput(originalNativeState, moduleTmp)
                )
            }
        }

        if (context.config.produce.isFullCache) {
            val originalNativeState = context as NativeGenerationState
            newEngine(originalNativeState) { nativeEngine ->
                nativeEngine.runPhase(SaveAdditionalCacheInfoPhase)
            }
        }

        runPhase(WriteBitcodeFilePhase, WriteBitcodeFileInput(moduleTmp, bitcodeFileOriFinal))

        // clear temp file
        tempBitcodeFiles.forEach { tempFile ->
            try {
                File(tempFile).delete()
            } catch (e: Exception) {
                println("Warning: Failed to clean up $tempFile: ${e.message}")
            }
        }

        for (i in 0 until context.config.splitBCfile.toInt()) {
           val partFile = "${bitcodeFile!!.absolutePath.removeSuffix(".bc")}_part_$i"
           File(partFile).delete()
        }
    } else {
        println("No processed modules to link, using original module")
    }
}
