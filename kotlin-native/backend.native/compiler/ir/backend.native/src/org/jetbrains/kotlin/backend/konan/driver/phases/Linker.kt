/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.driver.phases

import org.jetbrains.kotlin.backend.common.phaser.createSimpleNamedCompilerPhase
import org.jetbrains.kotlin.backend.konan.*
import org.jetbrains.kotlin.backend.konan.Linker
import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.konan.TempFiles
import org.jetbrains.kotlin.konan.library.KonanLibrary
import org.jetbrains.kotlin.konan.target.LinkerOutputKind
import org.jetbrains.kotlin.library.impl.javaFile
import java.io.File

internal data class LinkerPhaseInput(
        val outputFile: String,
        val outputKind: LinkerOutputKind,
        val objectFiles: List<ObjectFile>,
        val dependenciesTrackingResult: DependenciesTrackingResult,
        val outputFiles: OutputFiles,
        val tempFiles: TempFiles,
        val resolvedCacheBinaries: ResolvedCacheBinaries,
)

internal val LinkerPhase = createSimpleNamedCompilerPhase<PhaseContext, LinkerPhaseInput>(
        name = "Linker",
) { context, input ->
    val linker = Linker(
            config = context.config,
            linkerOutput = input.outputKind,
            outputFiles = input.outputFiles,
            tempFiles = input.tempFiles,
    )
    val commands = linker.linkCommands(
            input.outputFile,
            input.objectFiles,
            input.dependenciesTrackingResult,
            input.resolvedCacheBinaries
    )
    runLinkerCommands(context, commands, cachingInvolved = !input.resolvedCacheBinaries.isEmpty())
}

internal data class CopyDynamicLibrariesPhaseInput(
        val outputKind: LinkerOutputKind,
        val outputFiles: OutputFiles,
        val dependenciesTrackingResult: DependenciesTrackingResult,
)

internal val CopyDynamicLibrariesPhase = createSimpleNamedCompilerPhase<PhaseContext, CopyDynamicLibrariesPhaseInput>(
        name = "CopyDynamicLibraries",
) { context, input ->
    when (input.outputKind) {
        LinkerOutputKind.DYNAMIC_LIBRARY, LinkerOutputKind.EXECUTABLE -> {
            val outputDir = input.outputFiles.mainFile.parentFile.child("include")
                    .apply { deleteRecursively() }.apply { mkdirs() }
            val nativeDependencies = input.dependenciesTrackingResult.nativeDependenciesToLink
            val includedBinariesLibraries = context.config.libraryToCache?.let { listOf(it.klib) }
                    ?: nativeDependencies.filterNot { context.config.cachedLibraries.isLibraryCached(it) }

            includedBinariesLibraries
                    .map { (it as? KonanLibrary)?.includedPaths.orEmpty() }
                    .flatten()
                    .filter { it.endsWith(".${context.config.target.family.dynamicSuffix}") }
                    .forEach {
                        val from = org.jetbrains.kotlin.konan.file.File(it)
                        val to = outputDir.child(from.name)
                        from.copyTo(to)
                    }
        }
        else -> {}
    }
}

internal data class PreLinkCachesInput(
        val objectFiles: List<File>,
        val caches: ResolvedCacheBinaries,
        val outputObjectFile: File,
)

internal val PreLinkCachesPhase = createSimpleNamedCompilerPhase<PhaseContext, PreLinkCachesInput>(
        name = "PreLinkCaches",
) { context, input ->
    val inputFiles = input.objectFiles.map { it.absoluteFile.normalize().path } + input.caches.static
    val commands = context.config.platform.linker.preLinkCommands(inputFiles, input.outputObjectFile.absoluteFile.normalize().path)
    runLinkerCommands(context, commands, cachingInvolved = true)
}
