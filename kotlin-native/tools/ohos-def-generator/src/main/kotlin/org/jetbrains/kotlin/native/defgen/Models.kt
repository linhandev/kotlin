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

/**
 * Module info
 */
data class ModuleInfo(
    val moduleName: String,
    val kitName: String?,
    val libraryName: String?,
    val headerFiles: MutableList<String> = mutableListOf(),
    val dependencies: MutableSet<String> = mutableSetOf(),
    val isFromHarmonySDK: Boolean = false,
    /** All @library from headers in this module, merged into linkerOpts */
    val libraryNames: MutableSet<String> = mutableSetOf()
) {
    /**
     * Get linkerOpts: prefer libraryNames from all headers, else fallback to single libraryName
     */
    fun getLinkerOpts(): String? {
        val libs = if (libraryNames.isNotEmpty()) {
            libraryNames.map { lib ->
                val name = lib.removePrefix("lib")
                    .removeSuffix(".so")
                    .removeSuffix(".z")
                "-l$name"
            }.distinct().sorted().joinToString(" ")
        } else {
            libraryName?.let { lib ->
                val name = lib.removePrefix("lib")
                    .removeSuffix(".so")
                    .removeSuffix(".z")
                "-l$name"
            }
        }
        return if (libs.isNullOrEmpty()) null else libs
    }
}

/**
 * Header file info
 */
data class HeaderFileInfo(
    val filePath: Path,
    val relativePath: String,
    val moduleName: String?,
    val kitName: String?,
    val libraryName: String?,
    val includes: List<String>,
    val isFromHarmonySDK: Boolean = false
)

/**
 * .def file config
 */
data class DefConfig(
    val moduleName: String,
    val packageName: String,
    val headers: List<String>,
    val headerFilter: String,
    val depends: List<String>,
    val linkerOpts: String?,
    val language: String = "C++",
    val compilerOpts: String = "-std=c++17",
    val enableUndefinedApiProtection: Boolean = true,
    val isFromHarmonySDK: Boolean = false
)

/**
 * Validation result
 */
data class ValidationResult(
    val circularDependencies: List<List<String>> = emptyList(),
    val missingDependencies: Map<String, List<String>> = emptyMap(),
    val largeHeaderFilters: Map<String, Int> = emptyMap(),
    val incompleteConfigs: List<String> = emptyList()
) {
    fun hasIssues(): Boolean {
        return circularDependencies.isNotEmpty() ||
                missingDependencies.isNotEmpty() ||
                largeHeaderFilters.isNotEmpty() ||
                incompleteConfigs.isNotEmpty()
    }
}

/**
 * Statistics
 */
data class Statistics(
    var totalHeadersScanned: Int = 0,
    var validHeadersFound: Int = 0,
    var modulesGenerated: Int = 0,
    var defFilesGenerated: Int = 0,
    var ohosHeadersScanned: Int = 0,
    var hmsHeadersScanned: Int = 0,
    var startTime: Long = System.currentTimeMillis(),
    var endTime: Long = 0
) {
    fun getDuration(): Long = endTime - startTime
}
