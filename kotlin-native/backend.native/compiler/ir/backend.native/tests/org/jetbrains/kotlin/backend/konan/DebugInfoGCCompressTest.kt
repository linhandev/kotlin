/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan

import com.intellij.mock.MockProject
import org.jetbrains.kotlin.backend.konan.driver.BasicPhaseContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.phaser.PhaseConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.jetbrains.kotlin.konan.target.KonanTestFixtures.findKonanHome
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path
import org.jetbrains.kotlin.konan.target.HostManager
import org.jetbrains.kotlin.konan.target.KonanTarget

class DebugInfoGCCompressTest {

    @Test
    fun `getGCCommandLine - returns llvm-dwarfutil command when tool exists in target toolchain`(@TempDir tempDir: Path) {
        val toolchainDir = tempDir.resolve("toolchain")
        val llvmHomeDir = tempDir.resolve("llvm")
        createExecutable(toolchainDir.resolve("bin/llvm-dwarfutil"))

        val context = createPhaseContext(
            konanHome = findKonanHome(),
            toolchainDir = toolchainDir.toString(),
            llvmHomeDir = llvmHomeDir.toString(),
        )
        val command = DebugInfoGCCompress(context).getGCCommandLine("input.so", "output.so")

        assertEquals(
            listOf(
                toolchainDir.resolve("bin/llvm-dwarfutil").toString(),
                "--garbage-collection",
                "--odr-deduplication",
                "--build-accelerator=DWARF",
                "input.so",
                "output.so",
            ),
            command?.argsWithExecutable,
        )
    }

    @Test
    fun `getGCCommandLine - falls back to llvm home when tool is missing in target toolchain`(@TempDir tempDir: Path) {
        val toolchainDir = tempDir.resolve("toolchain")
        val llvmHomeDir = tempDir.resolve("llvm")
        createExecutable(llvmHomeDir.resolve("bin/llvm-dwarfutil"))

        val context = createPhaseContext(
            konanHome = findKonanHome(),
            toolchainDir = toolchainDir.toString(),
            llvmHomeDir = llvmHomeDir.toString(),
        )
        val command = DebugInfoGCCompress(context).getGCCommandLine("input.so", "output.so")

        assertEquals(
            llvmHomeDir.resolve("bin/llvm-dwarfutil").toString(),
            command?.argsWithExecutable?.first(),
        )
    }

    @Test
    fun `getCompressCommandLine - returns llvm-objcopy command when tool exists`(@TempDir tempDir: Path) {
        val toolchainDir = tempDir.resolve("toolchain")
        val llvmHomeDir = tempDir.resolve("llvm")
        createExecutable(toolchainDir.resolve("bin/llvm-objcopy"))

        val context = createPhaseContext(
            konanHome = findKonanHome(),
            toolchainDir = toolchainDir.toString(),
            llvmHomeDir = llvmHomeDir.toString(),
        )
        val command = DebugInfoGCCompress(context).getCompressCommandLine("input.so")

        assertEquals(
            listOf(
                toolchainDir.resolve("bin/llvm-objcopy").toString(),
                "--compress-debug-sections",
                "input.so",
            ),
            command?.argsWithExecutable,
        )
    }

    @Test
    fun `getGCCommandLine - returns null when llvm-dwarfutil is missing`(@TempDir tempDir: Path) {
        val context = createPhaseContext(
            konanHome = findKonanHome(),
            toolchainDir = tempDir.resolve("toolchain").toString(),
            llvmHomeDir = tempDir.resolve("llvm").toString(),
        )

        assertNull(DebugInfoGCCompress(context).getGCCommandLine("input.so", "output.so"))
    }

    @Test
    fun `splitBCfile - defaults to 1 for ohos arm64 when not configured`() {
        val configuration = CompilerConfiguration().apply {
            phaseConfig = PhaseConfig()
            put(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)
            put(KonanConfigKeys.TARGET, KonanTarget.OHOS_ARM64.name)
            put(KonanConfigKeys.KONAN_HOME, findKonanHome())
        }

        val config = KonanConfig(MockProject(null), configuration)
        assertEquals(1u, config.splitBCfile)
    }

    @Test
    fun `splitBCfile - stays 1 for non-ohos arm64 targets`() {
        val configuration = CompilerConfiguration().apply {
            phaseConfig = PhaseConfig()
            put(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)
            put(KonanConfigKeys.TARGET, KonanTarget.LINUX_X64.name)
            put(KonanConfigKeys.KONAN_HOME, findKonanHome())
        }

        val config = KonanConfig(MockProject(null), configuration)
        assertEquals(1u, config.splitBCfile)
    }

    private fun createPhaseContext(
        konanHome: String,
        toolchainDir: String,
        llvmHomeDir: String,
    ): BasicPhaseContext {
        val host = HostManager.host.name
        val target = KonanTarget.OHOS_ARM64.name
        val configuration = CompilerConfiguration().apply {
            phaseConfig = PhaseConfig()
            put(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)
            put(KonanConfigKeys.TARGET, target)
            put(KonanConfigKeys.KONAN_HOME, konanHome)
            put(
                KonanConfigKeys.OVERRIDE_KONAN_PROPERTIES,
                mapOf(
                    "targetToolchain.$host-$target" to toolchainDir,
                    "llvmHome.$host" to llvmHomeDir,
                ),
            )
        }
        return BasicPhaseContext(KonanConfig(MockProject(null), configuration))
    }

    private fun createExecutable(path: Path) {
        path.parent.toFile().mkdirs()
        val file = path.toFile()
        file.writeText("#!/bin/sh\nexit 0\n")
        file.setExecutable(true)
    }
}
