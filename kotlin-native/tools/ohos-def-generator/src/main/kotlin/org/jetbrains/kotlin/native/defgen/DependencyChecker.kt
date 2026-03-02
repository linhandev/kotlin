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

/**
 * Dependency checker
 */
class DependencyChecker(
    private val logger: Logger,
    private val rules: GeneratorRulesConfig
) {
    
    companion object {
        private const val HEADER_FILTER_WARNING_THRESHOLD = 10
    }
    
    private fun normalizeModuleName(name: String): String =
        rules.moduleNameNormalize[name] ?: name.replaceFirstChar { it.uppercaseChar() }
    
    /**
     * Validate all .def configs
     */
    fun validate(configs: List<DefConfig>): ValidationResult {
        logger.info("Validating ${configs.size} .def configs...")
        
        val moduleNames = configs.flatMap { config ->
            listOf(config.moduleName, normalizeModuleName(config.moduleName))
        }.toSet()
        
        // Check circular dependencies
        val circularDeps = findCircularDependencies(configs)
        
        // Check missing dependencies
        val missingDeps = findMissingDependencies(configs, moduleNames)
        
        // Check config completeness
        val incompleteConfigs = findIncompleteConfigs(configs)
        
        return ValidationResult(
            circularDependencies = circularDeps,
            missingDependencies = missingDeps,
            largeHeaderFilters = emptyMap(),  // No longer checked
            incompleteConfigs = incompleteConfigs
        )
    }
    
    /**
     * Find circular dependencies
     */
    private fun findCircularDependencies(configs: List<DefConfig>): List<List<String>> {
        val graph = buildDependencyGraph(configs)
        val cycles = mutableListOf<List<String>>()
        val visited = mutableSetOf<String>()
        val recursionStack = mutableSetOf<String>()
        
        fun dfs(node: String, path: MutableList<String>): Boolean {
            if (recursionStack.contains(node)) {
                // Found cycle
                val cycleStart = path.indexOf(node)
                if (cycleStart >= 0) {
                    val cycle = path.subList(cycleStart, path.size) + listOf(node)
                    cycles.add(cycle)
                }
                return true
            }
            
            if (visited.contains(node)) {
                return false
            }
            
            visited.add(node)
            recursionStack.add(node)
            path.add(node)
            
            graph[node]?.forEach { neighbor ->
                dfs(neighbor, path)
            }
            
            path.removeAt(path.size - 1)
            recursionStack.remove(node)
            
            return false
        }
        
        graph.keys.forEach { node ->
            if (!visited.contains(node)) {
                dfs(node, mutableListOf())
            }
        }
        
        // Deduplicate
        return cycles.distinctBy { it.sorted() }
    }
    
    /**
     * Find missing dependencies
     */
    private fun findMissingDependencies(
        configs: List<DefConfig>,
        moduleNames: Set<String>
    ): Map<String, List<String>> {
        val missingDeps = mutableMapOf<String, MutableList<String>>()
        
        configs.forEach { config ->
            config.depends.forEach { dep ->
                if (!moduleNames.contains(dep)) {
                    missingDeps.getOrPut(config.moduleName) { mutableListOf() }.add(dep)
                }
            }
        }
        
        return missingDeps
    }
    
    /**
     * Find modules with oversized headerFilter config
     */
    private fun findLargeHeaderFilters(configs: List<DefConfig>): Map<String, Int> {
        val largeFilters = mutableMapOf<String, Int>()
        
        configs.forEach { config ->
            val headerCount = config.headers.size
            if (headerCount > HEADER_FILTER_WARNING_THRESHOLD) {
                largeFilters[config.moduleName] = headerCount
            }
        }
        
        return largeFilters
    }
    
    /**
     * Find incomplete configs
     */
    private fun findIncompleteConfigs(configs: List<DefConfig>): List<String> {
        val incompleteConfigs = mutableListOf<String>()
        
        configs.forEach { config ->
            val issues = mutableListOf<String>()
            
            if (config.headers.isEmpty()) {
                issues.add("missing headers")
            }
            
            if (config.headerFilter.isEmpty()) {
                issues.add("missing headerFilter")
            }
            
            if (config.packageName.contains("UnknownKit")) {
                issues.add("missing Kit info")
            }
            
            if (issues.isNotEmpty()) {
                incompleteConfigs.add("${config.moduleName}: ${issues.joinToString(", ")}")
            }
        }
        
        return incompleteConfigs
    }
    
    /**
     * Build dependency graph
     */
    private fun buildDependencyGraph(configs: List<DefConfig>): Map<String, List<String>> {
        val graph = mutableMapOf<String, List<String>>()
        
        configs.forEach { config ->
            graph[config.moduleName] = config.depends
        }
        
        return graph
    }
    
    /**
     * Generate dependency graph in DOT format (optional, for visualization)
     */
    fun generateDotGraph(configs: List<DefConfig>): String {
        val sb = StringBuilder()
        sb.appendLine("digraph DefDependencies {")
        sb.appendLine("  rankdir=LR;")
        sb.appendLine("  node [shape=box];")
        
        configs.forEach { config ->
            config.depends.forEach { dep ->
                sb.appendLine("  \"${config.moduleName}\" -> \"$dep\";")
            }
        }
        
        sb.appendLine("}")
        return sb.toString()
    }
}
