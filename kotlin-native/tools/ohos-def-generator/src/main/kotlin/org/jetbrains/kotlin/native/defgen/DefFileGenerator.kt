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

package org.jetbrains.kotlin.native.defgen

import java.nio.file.Path
import kotlin.io.path.*

/**
 * .def file generator
 */
class DefFileGenerator(
    private val logger: Logger,
    private val rules: GeneratorRulesConfig,
    private val language: String = "C++",
    private val compilerOpts: String = "-std=c++17"
) {
    
    /**
     * Apply configured library name mapping to each -lxxx in linkerOpts string
     */
    private fun applyLinkerOptsMapping(linkerOpts: String?): String? {
        if (linkerOpts.isNullOrBlank()) return linkerOpts
        val mapped = linkerOpts.trim().split(Regex("\\s+"))
            .map { token -> rules.libraryLinkerOptsMap[token] ?: token }
            .filter { it.isNotEmpty() }
        return mapped.joinToString(" ").ifEmpty { null }
    }
    
    /**
     * Generate .def files for all modules
     */
    fun generateDefFiles(modules: List<ModuleInfo>, outputDir: Path): List<DefConfig> {
        outputDir.toFile().mkdirs()
        
        val configs = modules.map { module ->
            createDefConfig(module)
        }
        
        configs.forEach { config ->
            writeDefFile(config, outputDir)
        }
        
        logger.success("Generated ${configs.size} .def files to: $outputDir")
        return configs
    }
    
    /**
     * Create .def config
     */
    private fun createDefConfig(module: ModuleInfo): DefConfig {
        // Build package name
        val packageName = buildPackageName(module)
        
        val excludeSet = rules.getModuleHeaderExclude(module.moduleName)
        val effectiveHeaderFiles = if (excludeSet.isEmpty()) module.headerFiles
        else module.headerFiles.filter { it !in excludeSet }
        
        val headerFilter = buildHeaderFilter(effectiveHeaderFiles)
        
        val sortedDeps = module.dependencies
            .map { normalizeModuleName(it) }
            .sorted()
        
        val defFileName = normalizeModuleName(module.moduleName)  // Same as xxx.def filename, for config key lookup
        val rawLinkerOpts = rules.moduleLinkerOptsOverride[defFileName] ?: rules.moduleLinkerOptsOverride[module.moduleName] ?: module.getLinkerOpts()
        val linkerOpts = applyLinkerOptsMapping(rawLinkerOpts)
        
        val extraHeaders = rules.getModuleHeadersExtra(defFileName)
        val headers = if (extraHeaders.isEmpty()) effectiveHeaderFiles.sorted()
        else (effectiveHeaderFiles.toList() + extraHeaders).sorted()
        
        return DefConfig(
            moduleName = module.moduleName,
            packageName = packageName,
            headers = headers,
            headerFilter = headerFilter,
            depends = sortedDeps,
            linkerOpts = linkerOpts,
            language = language,
            compilerOpts = compilerOpts,
            enableUndefinedApiProtection = true,
            isFromHarmonySDK = module.isFromHarmonySDK
        )
    }
    
    /**
     * Build package name: prefer packageOverride, else platform.Kit.Module.
     * packageOverride key is .def filename without extension (normalized module name), e.g. Notification.def → "Notification".
     */
    private fun buildPackageName(module: ModuleInfo): String {
        val defFileName = normalizeModuleName(module.moduleName)  // Same as written xxx.def
        val pkg = rules.packageOverride[defFileName]
        if (pkg != null) return pkg
        val kitPart = module.kitName ?: "UnknownKit"
        return "platform.${kitPart}.${defFileName}"
    }
    
    /**
     * Normalize module name: prefer moduleNameNormalize, else capitalize first letter
     */
    private fun normalizeModuleName(name: String): String {
        return rules.moduleNameNormalize[name] ?: name.replaceFirstChar { c -> c.uppercaseChar() }
    }

    /**
     * Build headerFilter: single file in dir uses that path; multiple files use dir/`**`; multiple dirs handled per dir then joined.
     */
    private fun buildHeaderFilter(headerFiles: List<String>): String {
        if (headerFiles.isEmpty()) return ""
        if (headerFiles.size == 1) return headerFiles.first()
        val byDir = headerFiles.groupBy { it.substringBeforeLast('/') }
        return byDir.keys.sorted().joinToString(" ") { dir ->
            val files = byDir[dir]!!.sorted()
            if (files.size == 1) files.single() else "$dir/" + "**"
        }
    }
    
    /**
     * Write .def file
     */
    private fun writeDefFile(config: DefConfig, outputDir: Path) {
        // Normalize file name: capitalize first letter
        val fileName = normalizeModuleName(config.moduleName)
        val defFile = outputDir.resolve("${fileName}.def")
        
        val needsStandardConfig = !rules.modulesWithoutStandardConfig.contains(config.moduleName)
        
        val content = buildString {
            // Add comment if HarmonySDK
            if (config.isFromHarmonySDK) {
                appendLine("#from harmonysdk")
            }
            
            // package
            appendLine("package = ${config.packageName}")
            
            // headers
            appendLine("headers = ${config.headers.joinToString(" ")}")
            
            // headerFilter
            appendLine("headerFilter = ${config.headerFilter}")
            
            // depends
            if (config.depends.isNotEmpty()) {
                appendLine("depends = ${config.depends.joinToString(" ")}")
            }
            
            // linkerOpts
            if (config.linkerOpts != null) {
                appendLine("linkerOpts = ${config.linkerOpts}")
            }
            
            // Output the following three options only for non-special modules
            if (needsStandardConfig) {
                // language
                appendLine("language = ${config.language}")
                
                // compilerOpts
                appendLine("compilerOpts = ${config.compilerOpts}")
                
                // enableUndefinedApiProtection
                if (config.enableUndefinedApiProtection) {
                    appendLine("enableUndefinedApiProtection = true")
                }
            }
        }
        
        defFile.writeText(content)
        logger.info("Generated .def file: ${defFile.fileName}")
    }
}
