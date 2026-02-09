/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.driver.phases

import llvm.LLVMDumpModule
import llvm.LLVMModuleRef
import llvm.LLVMWriteBitcodeToFile
import org.jetbrains.kotlin.config.LoggingContext
import org.jetbrains.kotlin.backend.common.phaser.PhaseEngine
import org.jetbrains.kotlin.backend.common.phaser.createSimpleNamedCompilerPhase
import org.jetbrains.kotlin.backend.konan.*
import org.jetbrains.kotlin.backend.konan.driver.BasicPhaseContext
import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.backend.konan.driver.utilities.LlvmIrHolder
import org.jetbrains.kotlin.backend.konan.driver.utilities.getDefaultLlvmModuleActions
import org.jetbrains.kotlin.backend.konan.llvm.verifyModule
import org.jetbrains.kotlin.backend.konan.optimizations.RemoveRedundantSafepointsPass
import org.jetbrains.kotlin.backend.konan.optimizations.removeMultipleThreadDataLoads
import org.jetbrains.kotlin.konan.target.SanitizerKind
import java.io.File
import kotlin.coroutines.*
import kotlinx.coroutines.*
import java.io.IOException
import llvm.*
import org.jetbrains.kotlin.backend.konan.driver.utilities.createTempFiles
import org.jetbrains.kotlin.backend.konan.llvm.parseBitcodeFile
import kotlinx.cinterop.*

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

internal val RemoveRedundantSafepointsPhase = createSimpleNamedCompilerPhase<BitcodePostProcessingContext, Unit>(
        name = "RemoveRedundantSafepoints",
        postactions = getDefaultLlvmModuleActions(),
        op = { context, _ ->
            RemoveRedundantSafepointsPass().runOnModule(
                    module = context.llvm.module,
                    isSafepointInliningAllowed = context.shouldInlineSafepoints()
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
    useContext(OptimizationState(context.config, optimizationConfig)) {
        val module = this@runBitcodePostProcessing.context.llvmModule
        it.runPhase(StackProtectorPhase, module)
        it.runPhase(MandatoryBitcodeLLVMPostprocessingPhase, module)
        it.runPhase(ModuleBitcodeOptimizationPhase, module)
        it.runPhase(LTOBitcodeOptimizationPhase, module)
        when (context.config.sanitizer) {
            SanitizerKind.THREAD -> it.runPhase(ThreadSanitizerPhase, module)
            SanitizerKind.ADDRESS -> context.reportCompilationError("Address sanitizer is not supported yet")
            null -> {}
        }
    }
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
                    SanitizerKind.ADDRESS -> context.reportCompilationError("Address sanitizer is not supported yet")
                    null -> {}
                }
            }
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