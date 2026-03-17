/*
 * Copyright (C) 2026 Eazytec Co., Ltd. All rights reserved.
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

package org.jetbrains.kotlin.backend.konan

import org.jetbrains.kotlin.konan.exec.Command
import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.backend.common.reportCompilationWarning
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

internal data class DebugInfoGCCompress(
        private val context: PhaseContext
) {

    fun getGCCommandLine(inputFile: String, outputFile: String): Command? {
        val toolchainDir = context.config.platform.configurables.absoluteTargetToolchain
        val llvmHomeDir = context.config.platform.configurables.absoluteLlvmHome
        var dwarfUtilPath: Path = Paths.get(toolchainDir).resolve("bin").resolve("llvm-dwarfutil")

        if (!Files.exists(dwarfUtilPath)) {
            dwarfUtilPath = Paths.get(llvmHomeDir).resolve("bin").resolve("llvm-dwarfutil")
        }

        dwarfUtilPath = dwarfUtilPath.toAbsolutePath()
        if (Files.exists(dwarfUtilPath)) {
            return Command(dwarfUtilPath.toString()).apply {
                +"--garbage-collection"
                +"--odr-deduplication"
                +"--build-accelerator=DWARF"
                +inputFile
                +outputFile
            }
        } else {
            context.reportCompilationWarning("llvm-dwarfutil not found at: ${dwarfUtilPath}")
            return null
        }
    }

    fun getCompressCommandLine(inputFile: String): Command? {
        val toolchainDir = context.config.platform.configurables.absoluteTargetToolchain
        val llvmHomeDir = context.config.platform.configurables.absoluteLlvmHome
        var objCopyPath: Path = Paths.get(toolchainDir).resolve("bin").resolve("llvm-objcopy")

        if (!Files.exists(objCopyPath)) {
            objCopyPath = Paths.get(llvmHomeDir).resolve("bin").resolve("llvm-objcopy")
        }

        objCopyPath = objCopyPath.toAbsolutePath()
        if (Files.exists(objCopyPath)) {
            return Command(objCopyPath.toString()).apply {
                +"--compress-debug-sections"
                +inputFile
            }
        } else {
            context.reportCompilationWarning("llvm-objcopy not found at: ${objCopyPath}")
            return null
        }
    }
}
