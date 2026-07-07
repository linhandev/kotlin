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

package org.jetbrains.kotlin.konan.target

import java.io.File

/** Minimal [OhosConfigurables] for unit tests; paths are rooted at [baseDir]. */
internal class StubOhosConfigurables(
    override val target: KonanTarget,
    private val baseDir: File,
    override val additionalTargetSysRoot: String? = null,
    override val linkerDynamicFlags: List<String> = emptyList(),
) : OhosConfigurables {
    val libDir = when (target) {
        KonanTarget.OHOS_X64 -> "x86_64-linux-ohos"
        else -> "aarch64-linux-ohos"
    }

    init {
        File(baseDir, "toolchain/bin").mkdirs()
        File(baseDir, "toolchain/bin/ld.lld").writeText("")
        File(baseDir, "sysroot").mkdirs()
        additionalTargetSysRoot?.let { File(baseDir, it).mkdirs() }
    }

    override val targetTriple: TargetTriple
        get() = TargetTriple.fromString(
            when (target) {
                KonanTarget.OHOS_X64 -> "x86_64-linux-ohos"
                else -> "aarch64-linux-ohos"
            }
        )

    override val llvmHome: String = "llvm"
    override val llvmVersion: String = "19"
    override val libffiDir: String = "libffi"
    override val cacheableTargets: List<String> = emptyList()
    override val additionalCacheFlags: List<String> = emptyList()
    override val linkerOptimizationFlags: List<String> = emptyList()
    override val linkerKonanFlags: List<String> = emptyList()
    override val linkerNoDebugFlags: List<String> = emptyList()
    override val targetSysRoot: String = "sysroot"
    override val targetToolchain: String = "toolchain"
    override val absoluteTargetSysRoot: String get() = File(baseDir, targetSysRoot).absolutePath
    override val absoluteTargetToolchain: String get() = File(baseDir, targetToolchain).absolutePath
    override val absoluteLlvmHome: String get() = File(baseDir, llvmHome).absolutePath
    override val targetCpu: String? = "generic"
    override val targetCpuFeatures: String? = null
    override val llvmInlineThreshold: String? = null
    override val runtimeDefinitions: List<String> = emptyList()
    override val clangFlags: List<String> = emptyList()
    override val clangNooptFlags: List<String> = emptyList()
    override val clangOptFlags: List<String> = emptyList()
    override val clangDebugFlags: List<String> = emptyList()
    override val gccToolchain: String? = null
    override val absoluteGccToolchain: String get() = File(baseDir, "gcc").absolutePath
    override val libClangArgs: List<String> = emptyList()
    override val dynamicLinker: String
        get() = when (target) {
            KonanTarget.OHOS_X64 -> "/lib/ld-musl-x86_64.so.1"
            else -> "/lib/ld-musl-aarch64.so.1"
        }
    override val abiSpecificLibraries: List<String> = listOf("usr/lib/$libDir")
    override val crtFilesLocation: String = "usr/lib/$libDir"
    override val linker: String = "toolchain/bin/ld.lld"
    override val linkerHostSpecificFlags: List<String> = emptyList()
    override val absoluteLinker: String get() = File(baseDir, linker).absolutePath

    override fun targetString(key: String): String? = null
    override fun targetList(key: String): List<String> = emptyList()
    override fun hostString(key: String): String? = null
    override fun hostList(key: String): List<String> = emptyList()
    override fun hostTargetString(key: String): String? = null
    override fun hostTargetList(key: String): List<String> = emptyList()
    override fun absolute(value: String?): String = File(baseDir, value!!).absolutePath
    override fun downloadDependencies() {}
}

internal fun ohosLinkCommandArgs(
    configurables: OhosConfigurables,
    sanitizer: SanitizerKind? = null,
    linkerArgs: List<String> = emptyList(),
    kind: LinkerOutputKind = LinkerOutputKind.EXECUTABLE,
    libraries: List<String> = emptyList(),
    objectFiles: List<String> = listOf("/tmp/test.o"),
    executable: String = "/tmp/out/test.kexe",
): List<String> {
    val linker = OhosLinker(configurables)
    return linker.finalLinkCommands(
        objectFiles = objectFiles,
        executable = executable,
        libraries = libraries,
        linkerArgs = linkerArgs,
        optimize = false,
        debug = true,
        kind = kind,
        outputDsymBundle = "",
        mimallocEnabled = false,
        sanitizer = sanitizer,
    ).single().args
}

internal fun ohosLinkCommands(
    configurables: OhosConfigurables,
    sanitizer: SanitizerKind? = null,
    linkerArgs: List<String> = emptyList(),
    kind: LinkerOutputKind = LinkerOutputKind.EXECUTABLE,
    libraries: List<String> = emptyList(),
    objectFiles: List<String> = listOf("/tmp/test.o"),
    executable: String = "/tmp/out/test.kexe",
) = OhosLinker(configurables).finalLinkCommands(
    objectFiles = objectFiles,
    executable = executable,
    libraries = libraries,
    linkerArgs = linkerArgs,
    optimize = false,
    debug = true,
    kind = kind,
    outputDsymBundle = "",
    mimallocEnabled = false,
    sanitizer = sanitizer,
)

/**
 * Mirrors HMS sysroot lib path resolution in blackbox [TestCompilation] (PR #1).
 * Kept in tests so native.tests compilation logic stays covered without pulling in that module.
 */
internal fun ohosBlackboxHmsLibSearchPath(
    konanDataDir: File,
    sysrootName: String,
    target: KonanTarget,
): String? = when (target) {
    KonanTarget.OHOS_ARM64 -> File(File(konanDataDir, "dependencies"), sysrootName)
        .resolve("usr/lib/aarch64-linux-ohos")
        .absolutePath
    KonanTarget.OHOS_X64 -> File(File(konanDataDir, "dependencies"), sysrootName)
        .resolve("usr/lib/x86_64-linux-ohos")
        .absolutePath
    else -> null
}
