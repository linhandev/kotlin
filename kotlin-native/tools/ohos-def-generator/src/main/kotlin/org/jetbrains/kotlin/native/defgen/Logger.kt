/**
   Eazytec is pleased to support the open source community by making CPF-KMP-CMP available.
   Copyright (C) 2026 Eazytec. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package org.jetbrains.kotlin.native.defgen

import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Logging system
 */
class Logger(private val logDir: Path) {
    private val logFile: File
    private val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
    
    init {
        logDir.toFile().mkdirs()
        logFile = logDir.resolve("ohos_def_generator_$timestamp.log").toFile()
        logFile.createNewFile()
    }
    
    fun info(message: String) {
        log("INFO", message)
    }
    
    fun warn(message: String) {
        log("WARN", message)
        println("⚠️  $message")
    }
    
    fun error(message: String) {
        log("ERROR", message)
        println("❌ $message")
    }
    
    fun success(message: String) {
        log("SUCCESS", message)
        println("✅ $message")
    }
    
    fun step(stepName: String, action: () -> Unit) {
        val startTime = System.currentTimeMillis()
        info("========== Start: $stepName ==========")
        println("\n▶️  $stepName")
        
        try {
            action()
            val duration = System.currentTimeMillis() - startTime
            info("========== Done: $stepName (${duration}ms) ==========")
            println("✅ $stepName done (${duration}ms)")
        } catch (e: Exception) {
            error("========== Failed: $stepName - ${e.message} ==========")
            throw e
        }
    }
    
    private fun log(level: String, message: String) {
        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        logFile.appendText("[$time] [$level] $message\n")
    }
    
    fun logStatistics(stats: Statistics) {
        info("========== Statistics ==========")
        info("Total headers scanned: ${stats.totalHeadersScanned}")
        info("Valid headers found: ${stats.validHeadersFound}")
        info("  - OHOS headers: ${stats.ohosHeadersScanned}")
        info("  - HMS headers: ${stats.hmsHeadersScanned}")
        info("Modules generated: ${stats.modulesGenerated}")
        info(".def files generated: ${stats.defFilesGenerated}")
        info("Total duration: ${stats.getDuration()}ms")
        info("==============================")
        
        println("\n📊 Statistics:")
        println("  Total headers scanned: ${stats.totalHeadersScanned}")
        println("  Valid headers found: ${stats.validHeadersFound}")
        println("    - OHOS headers: ${stats.ohosHeadersScanned}")
        println("    - HMS headers: ${stats.hmsHeadersScanned}")
        println("  Modules generated: ${stats.modulesGenerated}")
        println("  .def files generated: ${stats.defFilesGenerated}")
        println("  Total duration: ${stats.getDuration()}ms")
    }
    
    fun logValidationResult(result: ValidationResult) {
        info("========== Validation result ==========")
        
        if (result.circularDependencies.isNotEmpty()) {
            error("Found ${result.circularDependencies.size} circular dependencies:")
            result.circularDependencies.forEach { cycle ->
                error("  Circular: ${cycle.joinToString(" -> ")}")
            }
        }
        
        if (result.missingDependencies.isNotEmpty()) {
            error("Found ${result.missingDependencies.size} modules with missing dependencies:")
            result.missingDependencies.forEach { (module, deps) ->
                error("  Module '$module' missing deps: ${deps.joinToString(", ")}")
            }
        }
        
        // No longer warn about modules with large headerFilter
        
        if (result.incompleteConfigs.isNotEmpty()) {
            error("Found ${result.incompleteConfigs.size} incomplete configs:")
            result.incompleteConfigs.forEach { module ->
                error("  Module '$module' has incomplete config")
            }
        }
        
        if (!result.hasIssues()) {
            success("All validations passed!")
        }
        
        info("==============================")
    }
    
    fun getLogFilePath(): String = logFile.absolutePath
}
