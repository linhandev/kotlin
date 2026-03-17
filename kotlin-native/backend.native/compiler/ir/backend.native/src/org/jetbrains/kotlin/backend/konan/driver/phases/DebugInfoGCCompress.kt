/*
 * Copyright (C) 2026 Eazytec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.backend.konan.driver.phases

import org.jetbrains.kotlin.backend.common.phaser.createSimpleNamedCompilerPhase
import org.jetbrains.kotlin.backend.konan.*
import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.konan.KonanExternalToolFailure
import org.jetbrains.kotlin.konan.exec.Command
import java.io.File
import java.io.IOException

internal data class DebugInfoGCCompressInput(
        val binaryFile: String,
)

internal val DebugInfoGCCompressPhase = createSimpleNamedCompilerPhase<PhaseContext, DebugInfoGCCompressInput>(
        name = "DebugInfoGCCompress",
) { context, input ->

    var outputFile = input.binaryFile + ".debuginfo-gc-compress"

    try {
        val inputBinaryFile = File(input.binaryFile)
        if (inputBinaryFile.exists()) {
            val debugInfoOptimization = DebugInfoGCCompress(context)
            var command = debugInfoOptimization.getGCCommandLine(input.binaryFile, outputFile)
            if(command != null) {
                runDebugInfoOptimizationCommands(context, command)
            }

            if (!File(outputFile).exists()) {
                outputFile = input.binaryFile
            }

            command = debugInfoOptimization.getCompressCommandLine(outputFile)
            if(command != null) {
                runDebugInfoOptimizationCommands(context, command)
            }

            val outputBinaryFile = File(outputFile)
            if (outputBinaryFile.exists() && input.binaryFile != outputFile) {
                val backupBinaryFile = File(input.binaryFile + ".backup")
                if (inputBinaryFile.renameTo(backupBinaryFile) && outputBinaryFile.renameTo(inputBinaryFile)) {
                    backupBinaryFile.delete()
                } else {
                    if (backupBinaryFile.exists())
                    {
                        backupBinaryFile.delete()
                    }
                }
            }
        }
    } catch (e: IOException) {
        context.reportCompilationError("Debug info optimization IO failed: ${e.message}")

        val outputBinaryFile = File(outputFile)
        if (outputBinaryFile.exists() && input.binaryFile != outputFile) {
            outputBinaryFile.delete()
        }
    } catch (e: KonanExternalToolFailure) {
        context.reportCompilationError("Debug info optimization tool failed: ${e.message}")

        val outputBinaryFile = File(outputFile)
        if (outputBinaryFile.exists() && input.binaryFile != outputFile) {
            outputBinaryFile.delete()
        }
    }
}

internal fun runDebugInfoOptimizationCommands(context: PhaseContext, command: Command) = try {
    command.logWith(context::log)
            .execute()
} catch (e: KonanExternalToolFailure) {
    context.reportCompilationError("${e.toolName} invocation reported errors\n\n${e.message}")
}