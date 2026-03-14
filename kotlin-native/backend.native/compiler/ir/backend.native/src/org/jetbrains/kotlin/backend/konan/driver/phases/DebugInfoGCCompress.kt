/*
 * Copyright 2010-2022 bifrosteco. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.driver.phases

import org.jetbrains.kotlin.backend.common.phaser.createSimpleNamedCompilerPhase
import org.jetbrains.kotlin.backend.konan.*
import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.konan.KonanExternalToolFailure
import org.jetbrains.kotlin.konan.exec.Command
import java.io.File
import kotlin.collections.forEach

internal data class DebugInfoGCCompressInput(
        val binaryFile: String,                  // 链接器产出的 .so 文件
)

internal val DebugInfoGCCompressPhase = createSimpleNamedCompilerPhase<PhaseContext, DebugInfoGCCompressInput>(
        name = "DebugInfoGCCompress",
) { context, input ->

    var outputFile = input.binaryFile + ".debuginfo-gc-compress"

    try {
        val binaryFile = File(input.binaryFile)
        if (binaryFile.exists()) {
            var debugInfoOptimization = DebugInfoGCCompress(context.config)
            var command = debugInfoOptimization.getGCCommandLine(input.binaryFile, outputFile)
            if(command != null) {
                runDebugInfoOptimizationCommands(context, command)
            }

            if (!File(outputFile).exists()) {
                outputFile = input.binaryFile
            }

            command = debugInfoOptimization.getCompressCommandLine(outputFile, outputFile)
            if(command != null) {
                runDebugInfoOptimizationCommands(context, command)
            }
            val outputBinaryFile = File(outputFile)
            if (outputBinaryFile.exists() && input.binaryFile != outputFile) {
                binaryFile.delete()
                outputBinaryFile.renameTo(binaryFile)
            }
        }
    } catch (e: Exception) {
        context.reportCompilationError("${e.message}")
        val outputBinaryFile = File(outputFile)
        if (outputBinaryFile.exists()) {
            outputBinaryFile.delete()
        }
    }
}

internal fun runDebugInfoOptimizationCommands(context: PhaseContext, command: Command) = try {
    command.logWith(context::log)
            .execute()
} catch (e: KonanExternalToolFailure) {
    context.reportCompilationError("${e.toolName} invocation reported errors\n$ \n${e.message}")
}