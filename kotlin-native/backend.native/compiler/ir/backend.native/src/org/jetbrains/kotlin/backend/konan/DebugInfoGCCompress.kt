/*
 * Copyright 2010-2021 bifrosteco
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
import java.io.File
import org.jetbrains.kotlin.konan.target.OhosConfigurables

data class DebugInfoGCCompress(
        private val config: KonanConfig
) {
    /**
     * 将命令转换为命令行参数列表
     */
    fun getGCCommandLine(inputFile: String, outputFile: String): Command? {
        val dwarfutilFile = "/bin/llvm-dwarfutil"
        var dwarfutilPath = config.platform.configurables .absoluteTargetToolchain + dwarfutilFile

        if (!File(dwarfutilPath).exists()) {
            dwarfutilPath = config.platform.configurables .absoluteLlvmHome + dwarfutilFile
        }

        if (File(dwarfutilPath).exists()) {
            return Command(dwarfutilPath).apply {
                +"--garbage-collection"
                +"--odr-deduplication"
                +"--build-accelerator=DWARF"
                +inputFile
                +outputFile
            }
        } else {
            println("Warning: llvm-dwarfutil not found at: $dwarfutilPath")

            return null
        }
    }

    fun getCompressCommandLine(inputFile: String, outputFile: String): Command? {
        val objcopy = "/bin/llvm-objcopy"
        var objcopyPath = config.platform.configurables.absoluteTargetToolchain + objcopy

        if (!File(objcopyPath).exists()) {
            objcopyPath = config.platform.configurables.absoluteLlvmHome + objcopy
        }

        if (File(objcopyPath).exists() && File(inputFile).exists()) {
            return Command(objcopyPath).apply {
                +"--compress-debug-sections"
                +inputFile
                +outputFile
            }
        } else {
            println("Warning: llvm-objcopy not found at: $objcopyPath")
            return null
        }
    }
}
