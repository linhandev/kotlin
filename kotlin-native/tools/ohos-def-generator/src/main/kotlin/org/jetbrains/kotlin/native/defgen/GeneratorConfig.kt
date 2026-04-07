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

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Path

/**
 * Generator rules config (loaded from def-generator-rules.json)
 */
data class GeneratorRulesConfig(
    val excludedHeaders: Set<String> = emptySet(),
    val headerToModuleOverride: Map<String, String> = emptyMap(),
    val moduleRemap: Map<String, String> = emptyMap(),
    val moduleNameNormalize: Map<String, String> = emptyMap(),
    /** Module name (.def filename, e.g. Notification) -> full package, overrides generated package line */
    val packageOverride: Map<String, String> = emptyMap(),
    val libraryLinkerOptsMap: Map<String, String> = emptyMap(),
    val modulesWithoutStandardConfig: Set<String> = emptySet(),
    val moduleHeaderExclude: Map<String, Set<String>> = emptyMap(),
    val moduleLinkerOptsOverride: Map<String, String> = emptyMap(),
    /** Extra headers per module: key is .def filename without extension, e.g. Netstack, WindowManager */
    val moduleHeadersExtra: Map<String, List<String>> = emptyMap(),
    /** Full headers list (exact order); when non-empty, replaces scanned headers for that module */
    val moduleHeadersOverride: Map<String, List<String>> = emptyMap(),
    /** Exact headerFilter string; when set, replaces derived filter for that module */
    val moduleHeaderFilterOverride: Map<String, String> = emptyMap(),
    val moduleHeaderSkipLibrary: Map<String, Set<String>> = emptyMap(),
    val moduleFixedDependencies: Map<String, List<String>> = emptyMap(),
    /**
     * Extra `depends` entries merged with scanned dependencies (key = .def basename, e.g. NetConnection).
     * Values are written verbatim to match platform .def names (e.g. `posix`).
     */
    val moduleDefaultDependencies: Map<String, List<String>> = emptyMap(),
    /**
     * `depends` targets that exist in kotlin-native platformLibs but are never emitted by this generator;
     * excluded from "missing dependency" validation.
     */
    val dependencyAllowlist: Set<String> = emptySet(),
    val moduleKitOverride: Map<String, String> = emptyMap()
) {
    fun getModuleHeaderExclude(moduleName: String): Set<String> =
        moduleHeaderExclude[moduleName] ?: emptySet()

    fun getModuleHeadersExtra(moduleName: String): List<String> =
        moduleHeadersExtra[moduleName] ?: emptyList()

    fun getModuleHeadersOverride(defFileName: String): List<String> =
        moduleHeadersOverride[defFileName] ?: emptyList()

    fun getModuleHeaderSkipLibrary(moduleName: String): Set<String> =
        moduleHeaderSkipLibrary[moduleName] ?: emptySet()

    fun getModuleDefaultDependencies(defFileName: String): List<String> =
        moduleDefaultDependencies[defFileName] ?: emptyList()
}

object GeneratorConfigLoader {
    private const val RESOURCE_NAME = "def-generator-rules.json"

    fun loadRulesFromFile(path: Path): GeneratorRulesConfig {
        return Files.newBufferedReader(path).use { loadFromReader(it) }
    }

    fun loadRules(configDir: Path? = null): GeneratorRulesConfig {
        val externalPath = configDir?.resolve(RESOURCE_NAME)
        if (externalPath != null && Files.isRegularFile(externalPath)) {
            return Files.newBufferedReader(externalPath).use { loadFromReader(it) }
        }
        val fallback = GeneratorConfigLoader::class.java.getResourceAsStream("/$RESOURCE_NAME")
            ?: throw IllegalStateException("Built-in config not found: $RESOURCE_NAME")
        return fallback.reader().use { loadFromReader(it) }
    }

    fun loadFromReader(reader: Reader): GeneratorRulesConfig {
        val root = com.google.gson.JsonParser.parseReader(reader).asJsonObject
        return GeneratorRulesConfig(
            excludedHeaders = root.getStringSet("excludedHeaders"),
            headerToModuleOverride = root.getStringMap("headerToModuleOverride"),
            moduleRemap = root.getStringMap("moduleRemap"),
            moduleNameNormalize = root.getStringMap("moduleNameNormalize"),
            packageOverride = root.getStringMap("packageOverride"),
            libraryLinkerOptsMap = root.getStringMap("libraryLinkerOptsMap"),
            modulesWithoutStandardConfig = root.getStringSet("modulesWithoutStandardConfig"),
            moduleHeaderExclude = root.getMapOfStringSet("moduleHeaderExclude"),
            moduleLinkerOptsOverride = root.getStringMap("moduleLinkerOptsOverride"),
            moduleHeadersExtra = root.getMapOfStringList("moduleHeadersExtra"),
            moduleHeadersOverride = root.getMapOfStringList("moduleHeadersOverride"),
            moduleHeaderFilterOverride = root.getStringMap("moduleHeaderFilterOverride"),
            moduleHeaderSkipLibrary = root.getMapOfStringSet("moduleHeaderSkipLibrary"),
            moduleFixedDependencies = root.getMapOfStringList("moduleFixedDependencies"),
            moduleDefaultDependencies = root.getMapOfStringList("moduleDefaultDependencies"),
            dependencyAllowlist = root.getStringSet("dependencyAllowlist"),
            moduleKitOverride = root.getStringMap("moduleKitOverride")
        )
    }

    private fun JsonObject.getStringSet(key: String): Set<String> {
        val arr = getAsJsonArray(key) ?: return emptySet()
        return arr.asSequence().mapNotNull { (it as? JsonPrimitive)?.asString }.toSet()
    }

    private fun JsonObject.getStringMap(key: String): Map<String, String> {
        val obj = getAsJsonObject(key) ?: return emptyMap()
        return obj.entrySet().associate { entry ->
            entry.key to ((entry.value as? JsonPrimitive)?.asString ?: "").trim()
        }.filterValues { s -> s.isNotEmpty() }
    }

    private fun JsonObject.getMapOfStringSet(key: String): Map<String, Set<String>> {
        val obj = getAsJsonObject(key) ?: return emptyMap()
        return obj.entrySet().associate { (moduleName, value) ->
            val set = when (value) {
                is JsonArray -> value.asSequence().mapNotNull { (it as? JsonPrimitive)?.asString }.toSet()
                is JsonPrimitive -> setOf(value.asString)
                else -> emptySet<String>()
            }
            moduleName to set
        }
    }

    private fun JsonObject.getMapOfStringList(key: String): Map<String, List<String>> {
        val obj = getAsJsonObject(key) ?: return emptyMap()
        return obj.entrySet().associate { (moduleName, value) ->
            val list = when (value) {
                is JsonArray -> value.asSequence().mapNotNull { (it as? JsonPrimitive)?.asString }.toList()
                is JsonPrimitive -> listOf(value.asString)
                else -> emptyList<String>()
            }
            moduleName to list
        }
    }
}
