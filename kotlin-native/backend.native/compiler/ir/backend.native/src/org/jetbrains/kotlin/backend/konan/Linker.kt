package org.jetbrains.kotlin.backend.konan

import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.konan.KonanExternalToolFailure
import org.jetbrains.kotlin.konan.TempFiles
import org.jetbrains.kotlin.konan.exec.Command
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.library.KonanLibrary
import org.jetbrains.kotlin.konan.target.*
import org.jetbrains.kotlin.library.metadata.isCInteropLibrary
import org.jetbrains.kotlin.library.uniqueName

internal fun determineLinkerOutput(context: PhaseContext): LinkerOutputKind =
        when (context.config.produce) {
            CompilerOutputKind.FRAMEWORK -> {
                val staticFramework = context.config.produceStaticFramework
                if (staticFramework) LinkerOutputKind.STATIC_LIBRARY else LinkerOutputKind.DYNAMIC_LIBRARY
            }
            CompilerOutputKind.TEST_BUNDLE,
            CompilerOutputKind.DYNAMIC_CACHE,
            CompilerOutputKind.DYNAMIC -> LinkerOutputKind.DYNAMIC_LIBRARY
            CompilerOutputKind.STATIC_CACHE,
            CompilerOutputKind.STATIC -> LinkerOutputKind.STATIC_LIBRARY
            CompilerOutputKind.PROGRAM -> run {
                if (context.config.target.family == Family.ANDROID) {
                    val configuration = context.config.configuration
                    val androidProgramType = configuration.get(BinaryOptions.androidProgramType) ?: AndroidProgramType.Default
                    if (androidProgramType.linkerOutputKindOverride != null) {
                        return@run androidProgramType.linkerOutputKindOverride
                    }
                }
                LinkerOutputKind.EXECUTABLE
            }
            else -> TODO("${context.config.produce} should not reach native linker stage")
        }

// TODO: We have a Linker.kt file in the shared module.
internal class Linker(
        private val config: KonanConfig,
        private val linkerOutput: LinkerOutputKind,
        private val outputFiles: OutputFiles,
        private val tempFiles: TempFiles,
) {
    private val platform = config.platform
    private val linker = platform.linker
    private val target = config.target
    private val optimize = config.optimizationsEnabled
    private val debug = config.debug || config.lightDebug

    fun linkCommands(
            outputFile: String,
            objectFiles: List<ObjectFile>,
            dependenciesTrackingResult: DependenciesTrackingResult,
            caches: ResolvedCacheBinaries,
    ): List<Command> {
        val nativeDependencies = dependenciesTrackingResult.nativeDependenciesToLink

        val includedBinariesLibraries = config.libraryToCache?.let { listOf(it.klib) }
                ?: nativeDependencies.filterNot { config.cachedLibraries.isLibraryCached(it) }
        val includedBinaries = includedBinariesLibraries.map { (it as? KonanLibrary)?.includedPaths.orEmpty() }.flatten()

        val libraryProvidedLinkerFlags = dependenciesTrackingResult.allNativeDependencies.map { it.linkerOpts }.flatten()
        return runLinker(outputFile, objectFiles, includedBinaries, libraryProvidedLinkerFlags, caches)
    }

    private fun buildModuleIncludesLinkerFlags(): List<String> {
        val moduleIncludeOnly = config.moduleIncludeOnly
        val moduleIncludes = config.moduleIncludes
        val runtimeName = config.runtimeName
        val stdlibName = config.stdlibName
        val outputModule = config.outputModule
        if (moduleIncludeOnly.isEmpty()) return emptyList()

        val stubsDir = tempFiles.create("module_stub_libs")
        stubsDir.mkdirs()

        val stubModules = moduleIncludes.keys.toMutableSet()
        stubModules += runtimeName
        stubModules += stdlibName

        for (moduleName in stubModules) {
            val stubSo = File(stubsDir, "lib${moduleName}.so")
            if (!stubSo.exists) {
                val compilerCmd = config.clang.clangCXX(
                        "-shared", "-nostdlib",
                        "-o", stubSo.absolutePath,
                        "-x", "c", "/dev/null"
                )
                val result = Command(compilerCmd).getResult(withErrors = false, handleError = false)
            }
        }

        val flags = mutableListOf("-L${stubsDir.absolutePath}")
        for (moduleName in moduleIncludes.keys.filter { it != outputModule}) {
            flags += "-l${moduleName}"
        }
        flags += "-l${runtimeName}"
        flags += "-l${stdlibName}"
        return flags
    }

    private fun stubObjectsForTarget(): List<String> {
        if (target != KonanTarget.OHOS_ARM64 && target != KonanTarget.MACOS_ARM64) return emptyList()
        // The 4 asm stubs can't be dropped from the OFF link: pre-compiled klib
        // cstubs.bc files (platform.darwin/posix/zlib/iconv/builtin) bake in
        // `_Kotlin_KonanStartStub` references at klib generation time, so dropping
        // the stub objects breaks the link.
        //
        // For a proper follow-up, all platform klibs would need regeneration in
        // OFF mode (a large invasive change beyond scope). Asm stubs stay linked
        // unconditionally — they're small and harmless when not called (the
        // compiler emits non-stub paths in OFF per the CodeGenerator gate below,
        // so the asm trampolines are dead code in OFF binaries).
        val stubsDir = "${config.distribution.konanHome}/konan/targets/${target.name}/stubs_objs"
        return listOf("N2KStub.o", "K2NStub.o", "K2RStub.o", "KonanStartStub.o").map { "$stubsDir/$it" }
    }

    private fun asLinkerArgs(args: List<String>): List<String> {
        if (linker.useCompilerDriverAsLinker) {
            return args
        }

        val result = mutableListOf<String>()
        for (arg in args) {
            // If user passes compiler arguments to us - transform them to linker ones.
            if (arg.startsWith("-Wl,")) {
                result.addAll(arg.substring(4).split(','))
            } else {
                result.add(arg)
            }
        }
        return result
    }

    private fun asLinkerArgs(konanTarget: KonanTarget, binaries: List<String>): List<String> {
        val dynamicPrefix = konanTarget.family.dynamicPrefix
        val dynamicSuffix = konanTarget.family.dynamicSuffix
        val validLibraries = binaries.filter { it.endsWith(".${dynamicSuffix}") }
                .map { File(it) }
                .filter { it.name.startsWith(dynamicPrefix) }

        return validLibraries.flatMap {
            listOf(
                    "-L${it.parentFile.canonicalPath}",
                    "-l${it.name.removePrefix(dynamicPrefix).removeSuffix(".${dynamicSuffix}")}"
            )
        }
    }

    private fun runLinker(
            outputFile: String,
            objectFiles: List<ObjectFile>,
            includedBinaries: List<String>,
            libraryProvidedLinkerFlags: List<String>,
            caches: ResolvedCacheBinaries,
    ): List<Command> {
        val additionalLinkerArgs: List<String>
        val executable: String

        when (config.produce) {
            CompilerOutputKind.TEST_BUNDLE -> {
                val bundleDir = File(outputFile)
                val name = bundleDir.name.removeSuffix(config.produce.suffix())
                require(target.family.isAppleFamily)
                val bundleRelativePath = if (target.family == Family.OSX) "Contents/MacOS/$name" else name
                additionalLinkerArgs = listOf("-bundle", "-dead_strip")
                val bundlePath = bundleDir.child(bundleRelativePath)
                bundlePath.parentFile.mkdirs()
                executable = bundlePath.absolutePath
            }
            CompilerOutputKind.FRAMEWORK -> {
                val framework = File(outputFile)
                val dylibName = framework.name.removeSuffix(".framework")
                val dylibRelativePath = when (target.family) {
                    Family.IOS,
                    Family.TVOS,
                    Family.WATCHOS -> dylibName
                    Family.OSX -> "Versions/A/$dylibName"
                    else -> error(target)
                }
                additionalLinkerArgs = listOf("-dead_strip", "-install_name", "@rpath/${framework.name}/$dylibRelativePath")
                val dylibPath = framework.child(dylibRelativePath)
                dylibPath.parentFile.mkdirs()
                executable = dylibPath.absolutePath
            }
            else -> {
                additionalLinkerArgs = if (target.family.isAppleFamily) {
                    when (config.produce) {
                        CompilerOutputKind.DYNAMIC_CACHE ->
                            listOf("-install_name", outputFiles.dynamicCacheInstallName)
                        // The precise-stackmap path replaces "-dead_strip" with "" on the
                        // Apple non-DYNAMIC_CACHE path to protect the __LLVM_STACKMAPS section
                        // from dead-strip. OFF restores baseline `-dead_strip` for smaller binaries.
                        // Note: OHOS uses lld (not Apple ld), this branch is Apple-only.
                        else -> if (config.enableStackmap) listOf("") else listOf("-dead_strip")
                    }
                } else {
                    emptyList()
                }
                executable = outputFiles.nativeBinaryFile
            }
        }
        File(executable).delete()

        val moduleIncludesFlags = buildModuleIncludesLinkerFlags()
        val linkerArgsForDynamicLibs = asLinkerArgs(config.target, includedBinaries)
        
        var linkerArgs = asLinkerArgs(config.configuration.getNotNull(KonanConfigKeys.LINKER_ARGS)) +
                caches.dynamic +
                libraryProvidedLinkerFlags + additionalLinkerArgs + moduleIncludesFlags +
                linkerArgsForDynamicLibs
        
        var libraries = linker.linkStaticLibraries(includedBinaries) + caches.static
        
        if (config.allocationMode == AllocationMode.CRT || config.memoryManagerMode == MemoryManagerMode.RUNTIME_SWITCH) {
            // libcrt.so is shipped inside the kotlin-native dist:
            //   <konanHome>/konan/targets/<target>/native/libcrt.so
            val libcrtFile = File(config.distribution.defaultNatives(target)).child("libcrt.so")
            check(libcrtFile.exists) {
                "libcrt.so not found at ${libcrtFile.absolutePath}. " +
                        "The Kotlin/Native distribution is incomplete or was built without CRT support " +
                        "(-Pkotlin.native.crt=false). Rebuild the dist with CRT enabled, or compile without " +
                        "CRT (-Xallocator=crt / -Xbinary=runtimeSwitchMemoryManager=true)."
            }
            libraries += listOf(libcrtFile.absolutePath)
        }

        // Stub .o files (N2KStub / K2NStub / K2RStub / KonanStartStub) live under the runtime-resolved
        // Kotlin/Native distribution dir, not under any property-file constant. Resolve them here so
        // Linker (in native/utils) does not need to know about kotlinNativeHome.
        val stubObjects = stubObjectsForTarget()

        return with(linker) {
            LinkerArguments(
                    tempFiles = tempFiles,
                    objectFiles = objectFiles + stubObjects,
                    executable = executable,
                    libraries = libraries,
                    linkerArgs = linkerArgs,
                    optimize = optimize,
                    debug = debug,
                    kind = linkerOutput,
                    outputDsymBundle = outputFiles.symbolicInfoFile,
                    sanitizer = config.sanitizer,
            ).finalLinkCommands()
        }
    }
}

internal fun runLinkerCommands(context: PhaseContext, commands: List<Command>, cachingInvolved: Boolean) = try {
    commands.forEach {
        it.logWith(context::log)
        it.execute()
    }
} catch (e: KonanExternalToolFailure) {
    val extraUserInfo = if (cachingInvolved)
        """
                    Please try to disable compiler caches and rerun the build. To disable compiler caches, add the following line to the gradle.properties file in the project's root directory:
                        
                        kotlin.native.cacheKind.${context.config.target.presetName}=none
                        
                    Also, consider filing an issue with full Gradle log here: https://kotl.in/issue
                    """.trimIndent()
    else null

    val extraUserSetupInfo = run {
        context.config.resolvedLibraries.getFullResolvedList()
                .filter { it.library.isCInteropLibrary() }
                .mapNotNull { library ->
                    library.library.manifestProperties["userSetupHint"]?.let {
                        "From ${library.library.uniqueName}:\n$it".takeIf { it.isNotEmpty() }
                    }
                }
                .mapIndexed { index, message -> "$index. $message" }
                .takeIf { it.isNotEmpty() }
                ?.joinToString(separator = "\n\n")
                ?.let {
                    "It seems your project produced link errors.\nProposed solutions:\n\n$it\n"
                }
    }

    val extraInfo = listOfNotNull(extraUserInfo, extraUserSetupInfo).joinToString(separator = "\n")

    context.reportCompilationError("${e.toolName} invocation reported errors\n$extraInfo\n${e.message}")
}
