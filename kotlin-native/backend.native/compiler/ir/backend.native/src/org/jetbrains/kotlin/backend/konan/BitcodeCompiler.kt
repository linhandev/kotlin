/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

package org.jetbrains.kotlin.backend.konan

import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.konan.exec.Command
import org.jetbrains.kotlin.konan.target.*
import java.io.File

typealias ObjectFile = String

internal class BitcodeCompiler(
        private val context: PhaseContext,
) {

    private val config = context.config
    private val platform = config.platform
    private val optimize = context.shouldOptimize()
    private val debug = config.debug

    private val overrideClangOptions =
            config.configuration.getList(KonanConfigKeys.OVERRIDE_CLANG_OPTIONS)

    private fun MutableList<String>.addNonEmpty(elements: List<String>) {
        addAll(elements.filter { it.isNotEmpty() })
    }

    private fun runTool(vararg command: String) =
            Command(*command)
                    .logWith(context::log)
                    .execute()

    private fun targetTool(tool: String, vararg arg: String) {
        val absoluteToolName = "${platform.absoluteTargetToolchain}/bin/$tool"
        runTool(absoluteToolName, *arg)
    }

    private fun hostLlvmTool(tool: String, vararg arg: String) {
        val absoluteToolName = "${platform.absoluteLlvmHome}/bin/$tool"
        runTool(absoluteToolName, *arg)
    }

    private fun ohosLlvmTool(tool: String, vararg arg: String) {
        val absoluteToolName = "${platform.absoluteLlvmHome}/bin/$tool"
        runTool(absoluteToolName, *arg)
    }

    private fun clang(configurables: ClangFlags, bitcodeFile: File, objectFile: File) {
        val targetTriple = if (configurables is AppleConfigurables) {
            platform.targetTriple.withOSVersion(configurables.osVersionMin)
        } else {
            platform.targetTriple
        }
        // The stackmap-pipeline opt-in flags
        // (`-mllvm -enable-compressed-bitmap-stackmap=true`,
        //  `-mllvm -enable-lazy-stackmap=true`) live directly in konan.properties'
        // `clangFlags.<target>`. In OFF mode these activate the custom LLVM
        // stackmap passes, which then SIGSEGV inside clang++ because the IR
        // (built without precise stackmap support) has no compatible metadata.
        // Filter the flags out instead of editing konan.properties so the ON
        // path stays byte-identical to the precise-stackmap dist.
        fun List<String>.stripStackmapMllvmFlags(): List<String> {
            if (config.enableStackmap) return this
            val drop = setOf(
                "-enable-compressed-bitmap-stackmap=true",
                "-enable-lazy-stackmap=true",
            )
            val out = mutableListOf<String>()
            var i = 0
            while (i < size) {
                val cur = this[i]
                val nxt = if (i + 1 < size) this[i + 1] else ""
                if (cur == "-mllvm" && nxt in drop) { i += 2; continue }
                out.add(cur); i++
            }
            return out
        }
        val flags = overrideClangOptions.takeIf(List<String>::isNotEmpty)
                ?: mutableListOf<String>().apply {
                    addNonEmpty(configurables.clangFlags.stripStackmapMllvmFlags())
                    addNonEmpty(listOf("-triple", targetTriple.toString()))
                    addNonEmpty(when {
                        optimize -> configurables.clangOptFlags
                        debug -> configurables.clangDebugFlags
                        else -> configurables.clangNooptFlags
                    })
                    addNonEmpty(configurables.currentRelocationMode(context).translateToClangCc1Flag())
                }
        val bitcodePath = bitcodeFile.absoluteFile.normalize().path
        val objectPath = objectFile.absoluteFile.normalize().path
        if (configurables is AppleConfigurables && config.configuration.get(BinaryOptions.compileBitcodeWithXcodeLlvm) == true) {
            targetTool("clang++", *flags.toTypedArray(), bitcodePath, "-o", objectPath)
        } else {
            ohosLlvmTool("clang++", *flags.toTypedArray(), bitcodePath, "-o", objectPath)
        }
    }

    private fun RelocationModeFlags.Mode.translateToClangCc1Flag() = when (this) {
        RelocationModeFlags.Mode.PIC -> listOf("-mrelocation-model", "pic")
        RelocationModeFlags.Mode.STATIC -> listOf("-mrelocation-model", "static")
        RelocationModeFlags.Mode.DEFAULT -> emptyList()
    }

    /**
     * Compile [bitcodeFile] to [objectFile].
     */
    fun makeObjectFile(bitcodeFile: File, objectFile: File) =
            when (val configurables = platform.configurables) {
                is ClangFlags -> clang(configurables, bitcodeFile, objectFile)
                else -> error("Unsupported configurables kind: ${configurables::class.simpleName}!")
            }
}
