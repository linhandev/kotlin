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
import org.jetbrains.kotlin.backend.konan.llvm.K2RStubFunctions
import org.jetbrains.kotlin.backend.konan.optimizations.RemoveRedundantSafepointsPass
import org.jetbrains.kotlin.backend.konan.optimizations.removeMultipleThreadDataLoads
import org.jetbrains.kotlin.konan.target.CompilerOutputKind
import org.jetbrains.kotlin.konan.target.SanitizerKind
import java.io.File
import java.io.IOException
import kotlin.coroutines.*
import kotlinx.coroutines.*

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
            // Expand surviving safepoints into the inline fast/slow poll in the precise-
            // stackmap dist. RewriteStatepointsForGC is gated off in the konanc pipeline (it
            // runs only at the clang stage), so safepoints are still plain calls here — both
            // the dedup and this expansion act on the simple form; clang's RSP then wraps the
            // cold slow call into a gc.statepoint and does the relocation/SSA fixup.
            //
            // Expansion is a PERFORMANCE optimization, NOT a correctness requirement: it turns
            // the per-hit `...PrologueStub` asm trampoline (unconditional x19-x28 spill) into a
            // straight-line fast check + cold call. Correctness (a single K2R boundary) holds
            // whether or not a safepoint is expanded, because mm::safePoint()'s cold edge calls
            // the plain C++ SafePointSlowPath — so the un-expanded chain `kfunc ->
            // PrologueStub(K2R) -> mm::safePoint(C++) -> SafePointSlowPath(C++)` has the
            // trampoline as its lone K2R boundary. (See the STABILITY INVARIANT in
            // RemoveRedundantSafepoints.cpp.)
            //
            // Mode selects the expanded fast check + DIRECT (no-trampoline) cold edge:
            //   CrtFastpath  : CRT + ENABLE_GC_FASTPATH — inline `mov x0, x28` TLS read; cold SafePointSlowPathStub.
            //   CrtNoFastpath: CRT, no fastpath — x28 is not the reserved TLS pointer, so the check
            //                  goes via the lean C++ helper Kotlin_mm_safePointCheckCRT(); cold SafePointSlowPathStub.
            //   Native       : global *Kotlin_mm_safePointActionAddr != null; cold slowPathStub.
            //   None         : RUNTIME_SWITCH / OFF — keep the surviving stub call (still single-boundary;
            //                  it just keeps paying the trampoline spill).
            val expansionMode = if (context.config.enableStackmap)
                when (context.config.memoryManagerMode) {
                    MemoryManagerMode.CRT ->
                        if (context.config.enableGcFastpath) SafepointExpansionCrtFastpath
                        else SafepointExpansionCrtNoFastpath
                    MemoryManagerMode.NATIVE -> SafepointExpansionNative
                    else -> SafepointExpansionNone
                }
            else SafepointExpansionNone
            RemoveRedundantSafepointsPass().runOnModule(
                    module = context.llvm.module,
                    isSafepointInliningAllowed = context.shouldInlineSafepoints(),
                    // ON (precise-stackmap) -> expand the surviving safepoint into the inline
                    // poll; OFF (shadow-stack baseline) -> force-inline the first eligible bare
                    // prologue. Replaces the legacy compile-time `#ifndef ENABLE_STACKMAP` gate;
                    // per-target via KonanConfig.enableStackmap.
                    enableStackmap = context.config.enableStackmap,
                    safepointExpansionMode = expansionMode,
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
    // KSG ran in the caller (TopLevelPhases.kt `compileModule` / `runBitcodeBackend`,
    // pre split=1/N branch). The module reaching here is post-KSG.
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
    // regresses throughput significantly. Must run AFTER the opt block so that
    // inlining has already merged callee prologues into the caller — only then
    // can the per-function dedup see and erase the redundant safepoints.
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

// Augments the given module's @llvm.used with any of `names` that resolve to a
// global/function in this module. Symbol names not present in the part are
// skipped silently (their definition lives in another part; this part doesn't
// need to keep them). Pre-existing @llvm.used entries are preserved.
//
// Purpose: counteract llvm-split distributing appending metadata globals
// (@llvm.used, @llvm.compiler.used) to only one part. Without this, the other
// parts' GlobalDCE drops the K2RStub runtime helpers (whose only references
// come from K2RStub.s assembly outside the bitcode world), which then shows
// up as link-time `ld.lld: undefined symbol: ...` referenced from K2RStub.o
// under `-Xbinary=splitBCfile=N -produce program -opt`.
//
// The set of names to pin is sourced from `K2RStubFunctions.linkRootSet`
// (canonical Kotlin-side list, build-time-verified against K2RStub.s by the
// `verifyK2RStubFunctions` Gradle task). Previously sourced from a runtime-cpp
// `__attribute__((annotate("K2RStub")))` walk via `collectAnnotatedFunctionNames`,
// which produced an incomplete set (only HAS_SAFEPOINT-marked functions, ~8 of
// 157), and worked only by coincidence — most K2RStub helpers got pinned via
// other per-function `used` attributes that survived split only on the recipient
// part. Switching to the canonical list closes that gap.
private fun pinK2RStubCalleesInLlvmUsed(module: LLVMModuleRef, names: Collection<String>) {
    if (names.isEmpty()) return
    val context = LLVMGetModuleContext(module) ?: return
    val ptrType = LLVMPointerTypeInContext(context, 0) ?: return

    val existingNames = collectUsedSymbolNames(module, "llvm.used").toMutableSet()

    val newEntries = names.mapNotNull { name ->
        if (name in existingNames) return@mapNotNull null
        val value = LLVMGetNamedGlobal(module, name)
                ?: LLVMGetNamedFunction(module, name)
                ?: return@mapNotNull null
        existingNames.add(name)
        LLVMConstBitCast(value, ptrType)
    }
    if (newEntries.isEmpty()) return

    // Snapshot existing entries before deleting the global.
    val carriedOver = mutableListOf<LLVMValueRef>()
    val existingUsed = LLVMGetNamedGlobal(module, "llvm.used")
    if (existingUsed != null) {
        val init = LLVMGetInitializer(existingUsed)
        if (init != null && LLVMIsAConstantArray(init) != null) {
            val n = LLVMGetArrayLength(LLVMTypeOf(init))
            for (i in 0 until n) {
                LLVMGetOperand(init, i)?.let { carriedOver.add(it) }
            }
        }
        LLVMDeleteGlobal(existingUsed)
    }

    val allEntries = carriedOver + newEntries
    val arrayType = LLVMArrayType(ptrType, allEntries.size) ?: return
    memScoped {
        val arr = allocArrayOf(allEntries)
        val newArrayValue = LLVMConstArray(ptrType, arr, allEntries.size) ?: return@memScoped
        val newUsed = LLVMAddGlobal(module, arrayType, "llvm.used") ?: return@memScoped
        LLVMSetInitializer(newUsed, newArrayValue)
        LLVMSetLinkage(newUsed, LLVMLinkage.LLVMAppendingLinkage)
        LLVMSetSection(newUsed, "llvm.metadata")
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
    // KSG ran in the caller (TopLevelPhases.kt `compileModule` / `runBitcodeBackend`,
    // pre split=1/N branch) on this same `context.llvmModule` (=== originalModule),
    // BEFORE llvm-split. Running pre-split is mandatory: @llvm.global.annotations
    // is appending-linkage and llvm-split attaches it to a single part; once split,
    // the other parts can't see the annotation set and per-part KSG would
    // early-return. The module reaching here is post-KSG.
    // The standalone disableBoundryFunctionInline + addDelayInline + the
    // post-merge addAlwaysInline trio used to live here; their work is now
    // folded into KSG step 6 (applyStubBoundaryNoInline) above. K2RStub
    // helpers are unaffected by per-part inlining because KSG step 1 has
    // already retargeted kfunc → helper calls to kfunc → helperStub, so
    // inlining the helper bodies in per-part LTO can't erase a Stub edge.
    // Collect (as strings, safe to ferry across LLVMContext) the names of all
    // symbols that must be kept alive: existing @llvm.used / @llvm.compiler.used
    // entries plus the canonical K2RStub.o link-root set (Kotlin-side authoritative
    // list, build-time-verified against K2RStub.s by the `verifyK2RStubFunctions`
    // Gradle task — see K2RStubFunctions.kt).
    // Two reasons to do this:
    //  (a) llvm-split hands appending metadata globals to a single part, so the
    //      other parts' GlobalDCE would drop these functions. We re-pin them
    //      into every part's @llvm.used after parse (per-part injection below).
    //  (b) saveLlvmUsedComplete / restoreLlvmUsedComplete snapshot the
    //      pre-split llvm.used and overwrite the post-merge one. If we only
    //      did the per-part injection, the restore step at stage B would wipe
    //      our additions before clang++ sees them. So we also augment the
    //      original module's @llvm.used here, before saveLlvmUsedComplete, so
    //      the snapshot itself contains everything. clang++'s O3 GlobalDCE
    //      then preserves these K2RStub runtime helpers whose only references
    //      come from the K2RStub.s assembly stub object.
    val symbolsToPinPerPart: Set<String> = linkedSetOf<String>().apply {
        addAll(collectUsedSymbolNames(originalModule, "llvm.used"))
        addAll(collectUsedSymbolNames(originalModule, "llvm.compiler.used"))
        addAll(K2RStubFunctions.linkRootSet)
    }
    // Only augment @llvm.used for static-binary outputs (`-produce program`).
    // For shared-library outputs (DYNAMIC, FRAMEWORK, etc.) the runtime is
    // pulled in via whole-archive at link time, so K2RStub.o references resolve
    // without keeping every K2RStub helper alive in user.bc.
    // Augmenting in dynamic mode pins extra symbols whose .llvm_stackmaps
    // entries then carry PREL64 relocations against externally-visible symbols
    // ("R_AARCH64_PREL64 cannot be used against symbol 'TheEmptyString'; recompile with -fPIC").
    if (context.config.produce == CompilerOutputKind.PROGRAM) {
        pinK2RStubCalleesInLlvmUsed(originalModule, symbolsToPinPerPart)
    }
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
                            // Re-pin symbols that the original @llvm.used /
                            // @llvm.compiler.used / K2RStubFunctions.linkRootSet
                            // wanted alive but llvm-split routed to a different
                            // part. Names not present in this part are no-ops.
                            pinK2RStubCalleesInLlvmUsed(partModule, symbolsToPinPerPart)
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

        // The `Kotlin_{K2N,N2K,KonanStart}Stub` decls + per-function `<F>.KotlinStubGV`
        // globals that per-part LTO + llvm-link strip from the merged module are NO LONGER
        // re-established here. They are AsmPrinter-only (no IR uses) and are re-materialised
        // from fn attrs by the clang-stage LLVM KotlinStubGenerator
        // (`-mllvm -enable-kotlin-stub-generator`), which runs on this very out.bc right
        // before codegen — so the split strip is harmless. Steps 1/3/4 (callsite rewrite /
        // attr / n2k marking) are fn-level and already survive split/merge.
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
