/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.targets.native.internal

import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinProjectStructureMetadata
import org.jetbrains.kotlin.gradle.plugin.mpp.MetadataDependencyResolution.ChooseVisibleSourceSets
import org.jetbrains.kotlin.gradle.plugin.mpp.kotlinVariantNameFromPublishedVariantName
import org.jetbrains.kotlin.gradle.plugin.sources.DefaultKotlinSourceSet
import org.jetbrains.kotlin.gradle.utils.filesProvider
import org.jetbrains.kotlin.gradle.utils.future
import java.io.File

internal suspend fun Project.createCInteropMetadataDependencyClasspath(
    sourceSet: DefaultKotlinSourceSet,
    transitive: Boolean,
): FileCollection {
    return createCInteropMetadataDependencyClasspath(sourceSet, forIde = false, transitive = transitive)
}

internal suspend fun Project.createCInteropMetadataDependencyClasspathForIde(sourceSet: DefaultKotlinSourceSet): FileCollection {
    return createCInteropMetadataDependencyClasspath(sourceSet, forIde = true, transitive = true)
}

/**
 * @param forIde A different task for dependency transformation will be used. This task will not use the regular 'build' directory
 * as transformation output to ensure IDE still being able to resolve the dependencies even when the project is cleaned.
 * @param transitive Specifies whether it should contain classpath from other related source sets or not
 */
private suspend fun Project.createCInteropMetadataDependencyClasspath(
    sourceSet: DefaultKotlinSourceSet,
    forIde: Boolean,
    transitive: Boolean,
): FileCollection {
    val dependencyTransformationTask = if (forIde) locateOrRegisterCInteropMetadataDependencyTransformationTaskForIde(sourceSet)
    else locateOrRegisterCInteropMetadataDependencyTransformationTask(sourceSet)
    if (dependencyTransformationTask == null) return project.files()

    val dependencyTransformationTaskOutputs = project.files(dependencyTransformationTask.map { it.outputLibraryFiles })

    val ownClasspath = dependencyTransformationTaskOutputs + createCommonizedCInteropDependencyConfigurationView(sourceSet)
    return if (transitive) {
        ownClasspath + createCInteropMetadataDependencyClasspathFromAssociatedCompilations(sourceSet, forIde, true)
    } else {
        ownClasspath
    }
}

private fun Project.createCInteropMetadataDependencyClasspathFromAssociatedCompilations(
    sourceSet: DefaultKotlinSourceSet,
    forIde: Boolean,
    transitive: Boolean,
): FileCollection {
    return filesProvider files@{
        val commonizerTarget = sourceSet.sharedCommonizerTarget.getOrThrow() ?: return@files emptySet<File>()

        /*
        We will find the 'most suitable' / 'closest matching' source set
        (like 'nativeTest' -> 'nativeMain', 'appleTest' -> 'appleMain', ...).
        If no source set is found that matches the commonizer target explicitly, the next "bigger" source set shall be chosen
         */
        val (associatedSourceSet, _) = sourceSet.getAdditionalVisibleSourceSets()
            .filterIsInstance<DefaultKotlinSourceSet>()
            .mapNotNull { other -> other to (other.sharedCommonizerTarget.getOrThrow() ?: return@mapNotNull null) }
            .filter { (_, otherCommonizerTarget) -> otherCommonizerTarget.targets.containsAll(commonizerTarget.targets) }
            .minByOrNull { (_, otherCommonizerTarget) -> otherCommonizerTarget.targets.size } ?: return@files emptySet<File>()

        project.future { createCInteropMetadataDependencyClasspath(associatedSourceSet, forIde, transitive) }.getOrThrow()
    }
}

/**
 * Names of all source sets that may potentially provide necessary cinterops for this resolution.
 * This will select 'the most bottom' source sets in [ChooseVisibleSourceSets.allVisibleSourceSetNames].
 *
 * When [compilingKonanTargetNames] is non-empty, only bottom source sets whose published Gradle variants
 * cover **all** of those targets are considered.
 */
internal fun ChooseVisibleSourceSets.visibleSourceSetProvidingCInterops(
    compilingKonanTargetNames: Set<String> = emptySet(),
): String? {
    val projectStructureMetadata = projectStructureMetadata ?: return null
    return projectStructureMetadata.selectVisibleSourceSetProvidingCInterops(
        allVisibleSourceSetNames = allVisibleSourceSetNames,
        compilingKonanTargetNames = compilingKonanTargetNames,
    )
}

internal fun KotlinProjectStructureMetadata.selectVisibleSourceSetProvidingCInterops(
    allVisibleSourceSetNames: Set<String>,
    compilingKonanTargetNames: Set<String>,
): String? {
    val dependsOnSourceSets = allVisibleSourceSetNames
        .flatMap { sourceSetsDependsOnRelation[it].orEmpty() }
        .toSet()

    val bottomSourceSets = allVisibleSourceSetNames.filter { it !in dependsOnSourceSets }.toSet()

    val candidates = if (compilingKonanTargetNames.isNotEmpty()) {
        bottomSourceSets.filter { sourceSetName ->
            compilingKonanTargetNames.all { targetName ->
                targetName in variantsForSourceSet(sourceSetName)
            }
        }.toSet().takeIf { it.isNotEmpty() } ?: return null
    } else {
        bottomSourceSets
    }

    /* Select the source set participating in the least amount of variants (the most special one) */
    return candidates.minByOrNull { sourceSetName ->
        sourceSetNamesByVariantName.count { (_, sourceSetNames) -> sourceSetName in sourceSetNames }
    }
}

internal fun kotlinTargetNameFromPsmVariantName(variantName: String): String {
    val normalized = kotlinVariantNameFromPublishedVariantName(variantName)
    return when {
        normalized.endsWith("ApiElements") -> normalized.removeSuffix("ApiElements")
        normalized.endsWith("RuntimeElements") -> normalized.removeSuffix("RuntimeElements")
        else -> normalized
    }
}

private fun KotlinProjectStructureMetadata.variantsForSourceSet(sourceSetName: String): Set<String> =
    sourceSetNamesByVariantName
        .filterValues { sourceSetName in it }
        .keys
        .map(::kotlinTargetNameFromPsmVariantName)
        .toSet()
