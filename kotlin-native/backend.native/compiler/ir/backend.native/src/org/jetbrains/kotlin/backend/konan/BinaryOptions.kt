/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan

import org.jetbrains.kotlin.config.CompilerConfigurationKey
import org.jetbrains.kotlin.konan.target.SanitizerKind
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

// Note: options defined in this class are a part of user interface, including the names:
// users can pass these options using a -Xbinary=name=value compiler argument or corresponding Gradle DSL.
object BinaryOptions : BinaryOptionRegistry() {
    val runtimeAssertionsMode by option<RuntimeAssertsMode>()

    val runtimeSwitchMemoryManager by booleanOption()

    val checkStateAtExternalCalls by booleanOption()

    // Toggle the precise-stackmap codegen path (emit `addrspace(1)` heap
    // pointers, phi cast across AS0/AS1 boundary). Must match the runtime build
    // property `kotlin.native.precise.stackmap` (which controls cpp
    // `-DENABLE_STACKMAP=1`). Default = unset, which the consumer
    // (KonanConfig.enableStackmap) resolves per-target: ON only for ohos_arm64,
    // OFF for every other target. Pass `-Xbinary=enableStackmap=true|false` to
    // konanc to force a specific mode (must match the dist's per-target flavour).
    val enableStackmap by booleanOption()

    val splitBCfile by uintOption()

    val llvmSplitPath by stringOption()

    val moduleIncludeOnly by listStringOption()

    val emitRuntimeOpt by option<RuntimeEmissionMode>()

    val emitRuntime by booleanOption()

    val emitStdlib by booleanOption()

    val moduleIncludes by dictStringOption()

    val runtimeName by stringOption()

    val stdlibName by stringOption()

    val outputModule by stringOption()

    val memoryModel by option<MemoryModel>()

    val freezing by option<Freezing>()

    val stripDebugInfoFromNativeLibs by booleanOption()

    val sourceInfoType by option<SourceInfoType>()
    val coreSymbolicationImageListType by option<CoreSymbolicationImageListType>()

    val androidProgramType by option<AndroidProgramType>()

    val printToOhosHiLog by booleanOption()

    val unitSuspendFunctionObjCExport by option<UnitSuspendFunctionObjCExport>()

    val objcExportSuspendFunctionLaunchThreadRestriction by option<ObjCExportSuspendFunctionLaunchThreadRestriction>()

    val objcExportDisableSwiftMemberNameMangling by booleanOption()

    val objcExportIgnoreInterfaceMethodCollisions by booleanOption()

    val objcExportReportNameCollisions by booleanOption()

    val objcExportErrorOnNameCollisions by booleanOption()

    val objcExportEntryPointsPath by stringOption()

    val objcExportExplicitMethodFamily by booleanOption()

    val objcExportBlockExplicitParameterNames by booleanOption()

    val dumpObjcSelectorToSignatureMapping by stringOption()

    val gc by option<GC>(shortcut = { it.shortcut })

    val gcSchedulerType by option<GCSchedulerType>(hideValue = { it.deprecatedWithReplacement != null })

    val gcMarkSingleThreaded by booleanOption()

    val fixedBlockPageSize by uintOption()

    val concurrentWeakSweep by booleanOption()

    val concurrentMarkMaxIterations by uintOption()

    val gcMutatorsCooperate by booleanOption()

    val auxGCThreads by uintOption()

    val linkRuntime by option<RuntimeLinkageStrategy>()

    val bundleId by stringOption()
    val bundleShortVersionString by stringOption()
    val bundleVersion by stringOption()

    val appStateTracking by option<AppStateTracking>()

    val sanitizer by option<SanitizerKind>()

    val compileBitcodeWithXcodeLlvm by booleanOption()

    val objcDisposeOnMain by booleanOption()

    val objcDisposeWithRunLoop by booleanOption()

    val disableMmap by booleanOption()

    val mmapTag by uintOption()

    val enableSafepointSignposts by booleanOption()

    val forceNativeThreadStateForFunctions by listOption(StringValueParser)

    val packFields by booleanOption()

    val cInterfaceMode by option<CInterfaceGenerationMode>()

    val globalDataLazyInit by booleanOption()

    val swiftExport by booleanOption()

    val genericSafeCasts by booleanOption()

    val smallBinary by booleanOption()

    val preCodegenInlineThreshold by uintOption()

    val enableDebugTransparentStepping by booleanOption()

    val debugCompilationDir by stringOption()

    val pagedAllocator by booleanOption()

    val latin1Strings by booleanOption()

    val stackProtector by option<StackProtectorMode>()

    val minidumpLocation by stringOption()
}

open class BinaryOption<T : Any>(
        val name: String,
        val valueParser: ValueParser<T>,
        val compilerConfigurationKey: CompilerConfigurationKey<T> = CompilerConfigurationKey.create(name)
) {
    interface ValueParser<T : Any> {
        fun parse(value: String): T?
        val validValuesHint: String?
    }
}

open class BinaryOptionRegistry {
    private val registeredOptionsByName = mutableMapOf<String, BinaryOption<*>>()

    protected fun register(option: BinaryOption<*>) {
        val previousOption = registeredOptionsByName[option.name]
        if (previousOption != null) {
            error("option '${option.name}' is registered twice")
        }
        registeredOptionsByName[option.name] = option
    }

    fun getByName(name: String): BinaryOption<*>? = registeredOptionsByName[name]

    protected fun booleanOption(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<Boolean>>> =
            PropertyDelegateProvider { _, property ->
                val option = BinaryOption(property.name, BooleanValueParser)
                register(option)
                ReadOnlyProperty { _, _ ->
                    option.compilerConfigurationKey
                }
            }

    protected fun uintOption(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<UInt>>> =
            PropertyDelegateProvider { _, property ->
                val option = BinaryOption(property.name, UIntValueParser)
                register(option)
                ReadOnlyProperty { _, _ ->
                    option.compilerConfigurationKey
                }
            }

    protected fun stringOption(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<String>>> =
            PropertyDelegateProvider { _, property ->
                val option = BinaryOption(property.name, StringValueParser)
                register(option)
                ReadOnlyProperty { _, _ ->
                    option.compilerConfigurationKey
                }
            }

    protected fun <T : Any> listOption(
            elementValueParser: BinaryOption.ValueParser<T>
    ): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<List<T>>>> = PropertyDelegateProvider { _, property ->
        val option = BinaryOption(property.name, ListValueParser(elementValueParser))
        register(option)
        ReadOnlyProperty { _, _ ->
            option.compilerConfigurationKey
        }
    }

    protected inline fun <reified T : Enum<T>> option(noinline shortcut : (T) -> String? = { null }, noinline hideValue: (T) -> Boolean = { false }): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<T>>> =
            PropertyDelegateProvider { _, property ->
                val option = BinaryOption(property.name, EnumValueParser(enumValues<T>().toList(), shortcut, hideValue))
                register(option)
                ReadOnlyProperty { _, _ ->
                    option.compilerConfigurationKey
                }
            }

    protected fun listStringOption(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<List<String>>>> =
            PropertyDelegateProvider { _, property ->
                val option = BinaryOption(property.name, object : BinaryOption.ValueParser<List<String>> {
                override fun parse(value: String): List<String> {
                    val content = value.trim().removePrefix("[").removeSuffix("]").trim()
                    if (content.isEmpty()) return emptyList()
                    return content.split(";", " ").map { it.trim() }.filter { it.isNotEmpty() }
                }
                override val validValuesHint: String?
                    get() = "semicolon-separated list, optionally wrapped in brackets: [a;b;c] or a;b;c"
                })
                register(option)
                ReadOnlyProperty { _, _ ->
                    option.compilerConfigurationKey
                }
            }
            
    protected fun dictStringOption(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, CompilerConfigurationKey<Map<String, List<String>>>>> =
            PropertyDelegateProvider { _, property ->
                val option = BinaryOption(property.name, object : BinaryOption.ValueParser<Map<String, List<String>>> {
                override fun parse(value: String): Map<String, List<String>> {
                    val content = value.trim().removePrefix("{").removeSuffix("}").trim()
                    if (content.isEmpty()) return emptyMap()
                    val entries = mutableListOf<String>()
                    var depth = 0
                    var start = 0
                    for (index in content.indices) {
                        when (content[index]) {
                            '[' -> depth++
                            ']' -> if (depth > 0) depth--
                            ';' -> if (depth == 0) {
                                entries += content.substring(start, index).trim()
                                start = index + 1
                            }
                        }
                    }
                    entries += content.substring(start).trim()

                    val result = linkedMapOf<String, List<String>>()
                    for (entry in entries) {
                        if (entry.isEmpty()) continue
                        val colonIndex = entry.indexOf(':')
                        if (colonIndex <= 0) return emptyMap()

                        val key = entry.substring(0, colonIndex).trim()
                        if (key.isEmpty()) return emptyMap()

                        val rawValue = entry.substring(colonIndex + 1).trim()
                        if (!rawValue.startsWith("[") || !rawValue.endsWith("]")) return emptyMap()

                        val valueStr = rawValue.removePrefix("[").removeSuffix("]").trim()
                        val values = if (valueStr.isEmpty()) {
                            emptyList()
                        } else {
                            valueStr.split(";").map { it.trim() }.filter { it.isNotEmpty() }
                        }
                        result[key] = values
                    }

                    return result
                }
                override val validValuesHint: String?
                    get() = "dictionary format: {A:[a;b;c];B:[d;e]}"
                })
                register(option)
                ReadOnlyProperty { _, _ ->
                    option.compilerConfigurationKey
                }
            }
}

private object BooleanValueParser : BinaryOption.ValueParser<Boolean> {
    override fun parse(value: String): Boolean? = value.toBooleanStrictOrNull()

    override val validValuesHint: String?
        get() = "true|false"
}

private object UIntValueParser : BinaryOption.ValueParser<UInt> {
    override fun parse(value: String): UInt? = value.toUIntOrNull()

    override val validValuesHint: String?
        get() = "non-negative-number"
}

private object StringValueParser : BinaryOption.ValueParser<String> {
    override fun parse(value: String) = value
    override val validValuesHint: String?
        get() = "string"
}

private class ListValueParser<T : Any>(
        val elementValueParser: BinaryOption.ValueParser<T>
) : BinaryOption.ValueParser<List<T>> {
    override fun parse(value: String): List<T>? {
        if (value == "") return emptyList()

        return value.split(";").map {
            elementValueParser.parse(it) ?: return null
        }
    }

    override val validValuesHint: String?
        get() = "semicolon-separated list of ${elementValueParser.validValuesHint}"

}

@PublishedApi
internal class EnumValueParser<T : Enum<T>>(
    val values: List<T>,
    val shortcut: (T) -> String?,
    val hideValue: (T) -> Boolean,
) : BinaryOption.ValueParser<T> {
    override fun parse(value: String): T? = values.firstOrNull {
        // TODO: should we really ignore case here?
        it.name.equals(value, ignoreCase = true) || (shortcut(it)?.equals(value, ignoreCase = true) ?: false)
    }

    override val validValuesHint: String?
        get() = values.filter { !hideValue(it) }.map {
            val fullName = "$it".lowercase()
            shortcut(it)?.let { short ->
                "$fullName (or: $short)"
            } ?: fullName
        }.joinToString("|")
}
