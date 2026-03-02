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

import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*

/**
 * Header file scanner
 */
class HeaderScanner(
    private val logger: Logger,
    private val rules: GeneratorRulesConfig
) {
    
    companion object {
        // System standard library headers to ignore
        private val SYSTEM_HEADERS = setOf(
            "string.h", "stdlib.h", "stdio.h", "stdint.h", "stdbool.h", "stddef.h",
            "math.h", "time.h", "errno.h", "limits.h", "float.h", "assert.h",
            "setjmp.h", "signal.h", "stdarg.h", "pthread.h", "unistd.h", "fcntl.h"
        )
        
        private val ADDTOGROUP_REGEX = Regex("""@addtogroup\s+(\w+)""")
        private val KIT_REGEX = Regex("""@kit\s+([\w]+)""")
        // Library name may contain hyphen, e.g. libtss2-mu.so
        private val LIBRARY_REGEX = Regex("""@library\s+(lib[\w.-]+\.so)""")
        private val INCLUDE_REGEX = Regex("""#include\s+["<]([^">]+)[">]""")
    }
    
    /**
     * Scan all header files under the given directory.
     * @param includeSubdir Optional; if set (e.g. "usr/include"), use sourceDir/includeSubdir as header root for Konan sysroot layout; else use ohos_include / hms_include.
     */
    fun scanHeaders(
        sourceDir: Path,
        isFromHarmonySDK: Boolean = false,
        includeSubdir: String? = null
    ): List<HeaderFileInfo> {
        val includeDir = if (includeSubdir != null) {
            sourceDir.resolve(includeSubdir)
        } else {
            sourceDir.resolve(if (isFromHarmonySDK) "hms_include" else "ohos_include")
        }
        
        if (!includeDir.exists()) {
            logger.warn("Directory does not exist: $includeDir")
            return emptyList()
        }
        
        logger.info("Scanning directory: $includeDir")
        
        val headerFiles = mutableListOf<HeaderFileInfo>()
        
        Files.walk(includeDir, java.nio.file.FileVisitOption.FOLLOW_LINKS)
            .filter { it.isRegularFile() && it.extension == "h" }
            .forEach { headerPath ->
                try {
                    // Check if in exclude list
                    val relativePath = includeDir.relativize(headerPath).toString()
                    if (rules.excludedHeaders.contains(relativePath)) {
                        logger.info("Skipping excluded header: $relativePath")
                        return@forEach
                    }
                    
                    val info = parseHeaderFile(headerPath, includeDir, isFromHarmonySDK, relativePath)
                    if (info != null) {
                        headerFiles.add(info)
                        logger.info("Valid header: ${info.relativePath} (module: ${info.moduleName}, Kit: ${info.kitName})")
                    }
                } catch (e: Exception) {
                    logger.warn("Failed to parse header: $headerPath - ${e.message}")
                }
            }
        
        logger.info("Scan done, found ${headerFiles.size} valid headers (with @addtogroup)")
        return headerFiles
    }
    
    /**
     * Parse a single header file.
     * @param relativePath Header path relative to baseDir (precomputed by caller for config lookup)
     */
    private fun parseHeaderFile(
        headerPath: Path,
        baseDir: Path,
        isFromHarmonySDK: Boolean,
        relativePath: String
    ): HeaderFileInfo? {
        val content = headerPath.readText()
        // Header path can be forced to a target module (including synthetic module headers without @addtogroup)
        val overrideModule = rules.headerToModuleOverride[relativePath]
        val moduleName = overrideModule ?: extractModuleName(content)
        if (moduleName == null) {
            return null
        }
        
        val kitName = extractKitName(content)
        val libraryName = extractLibraryName(content)
        val rawIncludes = extractIncludes(content)
        
        if (kitName == null) {
            logger.warn("Header '${headerPath.fileName}' missing @kit")
        }
        
        val normalizedIncludes = rawIncludes
            .filter { !isSystemHeader(it) }
            .map { normalizeIncludePath(it, headerPath, baseDir) }
            .filterNotNull()
        
        return HeaderFileInfo(
            filePath = headerPath,
            relativePath = relativePath,
            moduleName = moduleName,
            kitName = kitName,
            libraryName = libraryName,
            includes = normalizedIncludes,
            isFromHarmonySDK = isFromHarmonySDK
        )
    }
    
    /**
     * Extract module name
     */
    private fun extractModuleName(content: String): String? {
        val match = ADDTOGROUP_REGEX.find(content)
        return match?.groupValues?.get(1)
    }
    
    /**
     * Extract Kit name
     */
    private fun extractKitName(content: String): String? {
        val match = KIT_REGEX.find(content)
        return match?.groupValues?.get(1)
    }
    
    /**
     * Extract library name
     */
    private fun extractLibraryName(content: String): String? {
        val match = LIBRARY_REGEX.find(content)
        return match?.groupValues?.get(1)
    }
    
    /**
     * Extract all #include lines
     */
    private fun extractIncludes(content: String): List<String> {
        return INCLUDE_REGEX.findAll(content)
            .map { it.groupValues[1] }
            .toList()
    }
    
    /**
     * Check if system header
     */
    private fun isSystemHeader(headerName: String): Boolean {
        val fileName = headerName.substringAfterLast('/')
        return SYSTEM_HEADERS.contains(fileName)
    }
    
    /**
     * Normalize include path, resolve relative paths (e.g. ../rawfile/xxx.h).
     *
     * @param includePath Raw include path
     * @param currentFile Current header absolute path
     * @param baseDir Include base dir (ohos_include or hms_include)
     * @return Normalized relative path, or null if unresolvable
     */
    private fun normalizeIncludePath(includePath: String, currentFile: Path, baseDir: Path): String? {
        // No relative markers, return as-is
        if (!includePath.contains("..")) {
            return includePath
        }
        
        try {
            val currentDir = currentFile.parent
            val resolvedPath = currentDir.resolve(includePath).normalize()
            if (!resolvedPath.startsWith(baseDir)) {
                return null
            }
            return baseDir.relativize(resolvedPath).toString()
        } catch (e: Exception) {
            logger.warn("Cannot normalize include path: $includePath - ${e.message}")
            return null
        }
    }
    
    /**
     * Aggregate header infos into module infos
     */
    fun aggregateModules(
        headerInfos: List<HeaderFileInfo>,
        headerToModuleMap: Map<String, String>
    ): List<ModuleInfo> {
        val moduleMap = mutableMapOf<String, ModuleInfo>()
        
        // Group by module: header path override first, then module name remap
        headerInfos.forEach { headerInfo ->
            val moduleName = headerInfo.moduleName ?: return@forEach
            val targetModuleName = rules.headerToModuleOverride[headerInfo.relativePath]
                ?: rules.moduleRemap[moduleName] ?: moduleName
            
            val kitForModule = rules.moduleKitOverride[targetModuleName] ?: headerInfo.kitName
            val module = moduleMap.getOrPut(targetModuleName) {
                val libNames = mutableSetOf<String>()
                headerInfo.libraryName?.let { libNames.add(it) }
                ModuleInfo(
                    moduleName = targetModuleName,
                    kitName = kitForModule,
                    libraryName = headerInfo.libraryName,
                    isFromHarmonySDK = headerInfo.isFromHarmonySDK,
                    libraryNames = libNames
                )
            }
            
            module.headerFiles.add(headerInfo.relativePath)
            val skipLibrary = rules.getModuleHeaderSkipLibrary(targetModuleName).contains(headerInfo.relativePath)
            if (!skipLibrary) {
                headerInfo.libraryName?.let { module.libraryNames.add(it) }
            }
            
            if (module.kitName == null && kitForModule != null) {
                moduleMap[targetModuleName] = module.copy(kitName = kitForModule)
            }
            if (module.libraryName == null && headerInfo.libraryName != null) {
                moduleMap[targetModuleName] = module.copy(libraryName = headerInfo.libraryName)
            }
            
            headerInfo.includes.forEach { includePath ->
                val depModuleName = resolveDependencyModule(includePath, headerInfo.relativePath, headerToModuleMap)
                if (depModuleName != null && depModuleName != moduleName && depModuleName != targetModuleName) {
                    module.dependencies.add(depModuleName)
                }
            }
        }
        
        // Apply fixed dependency overrides (e.g. Image_NativeModule)
        for ((modName, deps) in rules.moduleFixedDependencies) {
            moduleMap[modName]?.let { m ->
                m.dependencies.clear()
                m.dependencies.addAll(deps)
            }
        }
        
        moduleMap.values.forEach { module ->
            module.dependencies.remove(module.moduleName)
        }
        
        return moduleMap.values.toList()
    }
    
    /**
     * Resolve dependency module name.
     * For simple filenames (no path), try same directory first.
     */
    private fun resolveDependencyModule(
        includePath: String,
        currentHeaderPath: String,
        headerToModuleMap: Map<String, String>
    ): String? {
        headerToModuleMap[includePath]?.let { return it }
        if (!includePath.contains('/')) {
            val currentDir = currentHeaderPath.substringBeforeLast('/', "")
            val sameDirPath = if (currentDir.isEmpty()) includePath else "$currentDir/$includePath"
            headerToModuleMap[sameDirPath]?.let { return it }
        }
        return null
    }
    
    /**
     * Build header-to-module map
     */
    fun buildHeaderToModuleMap(headerInfos: List<HeaderFileInfo>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        
        headerInfos.forEach { info ->
            if (info.moduleName != null) {
                val targetModule = rules.headerToModuleOverride[info.relativePath]
                    ?: rules.moduleRemap[info.moduleName] ?: info.moduleName
                map[info.relativePath] = targetModule
            }
        }
        
        return map
    }
}
