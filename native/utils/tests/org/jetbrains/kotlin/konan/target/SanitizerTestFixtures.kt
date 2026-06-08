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

/** Minimal [GccConfigurables] for PR #212 [GccBasedLinker] sanitizer tests. */
internal class StubLinuxX64Configurables(
    private val baseDir: File,
) : GccConfigurables {
    init {
        File(baseDir, "toolchain/bin/ld.lld").apply { parentFile!!.mkdirs(); writeText("") }
        File(baseDir, "sysroot").mkdirs()
        val compilerRtDir = File(baseDir, "llvm/lib/clang/19/lib/linux").apply { mkdirs() }
        listOf("asan", "asan_cxx", "hwasan", "hwasan_cxx", "tsan", "tsan_cxx").forEach { name ->
            File(compilerRtDir, "libclang_rt.$name.a").writeText("")
        }
    }

    override val target: KonanTarget = KonanTarget.LINUX_X64
    override val targetTriple: TargetTriple = TargetTriple.fromString("x86_64-unknown-linux-gnu")
    override val llvmHome: String = "llvm"
    override val llvmVersion: String = "19"
    override val libffiDir: String = "libffi"
    override val cacheableTargets: List<String> = emptyList()
    override val additionalCacheFlags: List<String> = emptyList()
    override val linkerOptimizationFlags: List<String> = emptyList()
    override val linkerKonanFlags: List<String> = emptyList()
    override val linkerNoDebugFlags: List<String> = emptyList()
    override val linkerDynamicFlags: List<String> = emptyList()
    override val targetSysRoot: String = "sysroot"
    override val targetToolchain: String = "toolchain"
    override val absoluteTargetSysRoot: String get() = File(baseDir, targetSysRoot).absolutePath
    override val absoluteTargetToolchain: String get() = File(baseDir, targetToolchain).absolutePath
    override val absoluteLlvmHome: String get() = File(baseDir, llvmHome).absolutePath
    override val targetCpu: String? = "x86-64"
    override val targetCpuFeatures: String? = null
    override val llvmInlineThreshold: String? = null
    override val runtimeDefinitions: List<String> = emptyList()
    override val clangFlags: List<String> = emptyList()
    override val clangNooptFlags: List<String> = emptyList()
    override val clangOptFlags: List<String> = emptyList()
    override val clangDebugFlags: List<String> = emptyList()
    override val gccToolchain: String? = null
    override val absoluteGccToolchain: String get() = File(baseDir, "gcc").absolutePath
    override val libGcc: String = "usr/lib/gcc/x86_64-linux-gnu/11"
    override val dynamicLinker: String = "/lib64/ld-linux-x86-64.so.2"
    override val abiSpecificLibraries: List<String> = listOf("usr/lib")
    override val crtFilesLocation: String = "usr/lib"
    override val linker: String = "toolchain/bin/ld.lld"
    override val linkerHostSpecificFlags: List<String> = emptyList()
    override val absoluteLinker: String get() = File(baseDir, linker).absolutePath
    override val linkerGccFlags: List<String> = emptyList()

    override fun targetString(key: String): String? = null
    override fun targetList(key: String): List<String> = emptyList()
    override fun hostString(key: String): String? = null
    override fun hostList(key: String): List<String> = emptyList()
    override fun hostTargetString(key: String): String? = null
    override fun hostTargetList(key: String): List<String> = emptyList()
    override fun absolute(value: String?): String = File(baseDir, value!!).absolutePath
    override fun downloadDependencies() {}
}

internal fun linuxLinkCommandArgs(
    configurables: GccConfigurables,
    sanitizer: SanitizerKind? = null,
): List<String> = GccBasedLinker(configurables).finalLinkCommands(
    objectFiles = listOf("/tmp/test.o"),
    executable = "/tmp/out/test.kexe",
    libraries = emptyList(),
    linkerArgs = emptyList(),
    optimize = false,
    debug = true,
    kind = LinkerOutputKind.EXECUTABLE,
    outputDsymBundle = "",
    mimallocEnabled = false,
    sanitizer = sanitizer,
).single().args
