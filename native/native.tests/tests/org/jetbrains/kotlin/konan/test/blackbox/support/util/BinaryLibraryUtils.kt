/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.test.blackbox.support.util

import org.jetbrains.kotlin.konan.target.Family
import org.jetbrains.kotlin.konan.test.blackbox.support.compilation.TestCompilationArtifact
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.BinaryLibraryKind
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.GCType
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.KotlinNativeHome
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.KotlinNativeTargets
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.Settings
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.configurables
import org.junit.jupiter.api.Assumptions

fun Settings.getKindSpecificClangFlags(binaryLibrary: TestCompilationArtifact.BinaryLibrary): List<String> =
    when (get<BinaryLibraryKind>()) {
        BinaryLibraryKind.STATIC -> {
            val flags = configurables.linkerKonanFlags
            val systemLinkerFlags = flags.filterIndexed { index, value ->
                // Filter out linker option that defines __cxa_demangle because Konan_cxa_demangle is not defined in tests.
                if (value == "__cxa_demangle=Konan_cxa_demangle" && flags[index - 1] == "--defsym") {
                    false
                } else if (value == "--defsym" && flags[index + 1] == "__cxa_demangle=Konan_cxa_demangle") {
                    false
                } else {
                    true
                }
            }.flatMap { listOf("-Xlinker", it) }

            val crtLinkerFlags = if (get<GCType>() == GCType.CMC) {
                val testTarget = get<KotlinNativeTargets>().testTarget
                val libCrt = get<KotlinNativeHome>().dir
                    .resolve("konan/targets/${testTarget.name}/native/libcrt.so")
                check(libCrt.isFile) {
                    "libcrt.so not found at ${libCrt.absolutePath}. The Kotlin/Native distribution is incomplete."
                }
                buildList {
                    // Static Kotlin libraries contain unresolved CRT references. Link the CRT DSO
                    // as a separate input after the Kotlin archive has introduced those references.
                    add(libCrt.absolutePath)
                    if (testTarget.family.isAppleFamily) {
                        addAll(listOf("-rpath", libCrt.parentFile.absolutePath))
                    }
                }
            } else {
                emptyList()
            }

            crtLinkerFlags + systemLinkerFlags
        }
        BinaryLibraryKind.DYNAMIC -> {
            if (get<KotlinNativeTargets>().testTarget.family != Family.MINGW) {
                listOf("-rpath", binaryLibrary.libraryFile.parentFile.absolutePath)
            } else {
                // --allow-multiple-definition is needed because finalLinkCommands statically links a lot of MinGW-specific libraries,
                // that are already included in DLL produced by Kotlin/Native.
                listOf("-Wl,--allow-multiple-definition")
            }
        }
    }

fun Settings.assumeLibraryKindSupported() {
    when (get<BinaryLibraryKind>()) {
        BinaryLibraryKind.STATIC -> if (get<KotlinNativeTargets>().testTarget.family == Family.MINGW) {
            Assumptions.abort<Nothing>("Testing of static libraries is not supported for MinGW targets.")
        }
        BinaryLibraryKind.DYNAMIC -> {}
    }
}
