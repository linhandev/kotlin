/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.target

import org.jetbrains.kotlin.konan.properties.resolvablePropertyList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class KonanPropertiesOhosDebugOptimizationTest {

    @Test
    fun `ohos debug clang flags include function and data sections`() {
        val distribution = Distribution(KonanTestFixtures.findKonanHome())

        listOf(KonanTarget.OHOS_ARM64, KonanTarget.OHOS_X64).forEach { target ->
            val clangDebugFlags = distribution.properties
                .resolvablePropertyList("clangDebugFlags", target.name)
            assertTrue(
                clangDebugFlags.contains("-ffunction-sections"),
                "Expected -ffunction-sections in clangDebugFlags for $target, got: $clangDebugFlags",
            )
            assertTrue(
                clangDebugFlags.contains("-fdata-sections"),
                "Expected -fdata-sections in clangDebugFlags for $target, got: $clangDebugFlags",
            )
        }
    }

    @Test
    fun `ohos dynamic linker flags include gc-sections and pack-relative-relocs`() {
        val distribution = Distribution(KonanTestFixtures.findKonanHome())

        listOf(KonanTarget.OHOS_ARM64, KonanTarget.OHOS_X64).forEach { target ->
            val linkerDynamicFlags = distribution.properties
                .resolvablePropertyList("linkerDynamicFlags", target.name)
            assertTrue(
                linkerDynamicFlags.contains("--gc-sections"),
                "Expected --gc-sections in linkerDynamicFlags for $target, got: $linkerDynamicFlags",
            )
            assertTrue(
                linkerDynamicFlags.any { it.contains("pack-relative-relocs") },
                "Expected pack-relative-relocs in linkerDynamicFlags for $target, got: $linkerDynamicFlags",
            )
        }
    }

    @Test
    fun `cacheableTargets for mingw host does not include ohos targets`() {
        val distribution = Distribution(KonanTestFixtures.findKonanHome())
        val cacheableTargets = distribution.properties
            .resolvablePropertyList("cacheableTargets", KonanTarget.MINGW_X64.name)

        assertTrue(
            cacheableTargets.isEmpty(),
            "Expected cacheableTargets.mingw_x64 to be empty, got: $cacheableTargets",
        )
    }

    @Test
    fun `cacheableTargets for linux host includes ohos targets`() {
        val distribution = Distribution(KonanTestFixtures.findKonanHome())
        val cacheableTargets = distribution.properties
            .resolvablePropertyList("cacheableTargets", KonanTarget.LINUX_X64.name)
            .toSet()

        assertEquals(
            setOf("linux_x64", "ohos_arm64", "ohos_x64"),
            cacheableTargets,
        )
    }

    @Test
    fun `cacheableTargets for macos hosts include ohos targets`() {
        val distribution = Distribution(KonanTestFixtures.findKonanHome())

        listOf(KonanTarget.MACOS_X64, KonanTarget.MACOS_ARM64).forEach { hostTarget ->
            val cacheableTargets = distribution.properties
                .resolvablePropertyList("cacheableTargets", hostTarget.name)
                .toSet()
            assertTrue(
                cacheableTargets.containsAll(setOf("ohos_arm64", "ohos_x64")),
                "Expected ohos targets in cacheableTargets for $hostTarget, got: $cacheableTargets",
            )
        }
    }
}
