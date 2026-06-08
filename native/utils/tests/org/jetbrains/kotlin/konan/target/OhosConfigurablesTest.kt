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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import java.util.Properties

/** Unit tests for `OhosConfigurablesImpl` wiring and property loading (PR #1 arm64, PR #11 x64). */
class OhosConfigurablesTest {

    @Test
    fun `loadConfigurables returns OhosConfigurables for ohos_arm64`(@TempDir tempDir: Path) {
        val host = HostManager.host.name
        val depsRoot = tempDir.resolve("deps").toFile().apply { mkdirs() }
        depsRoot.resolve("toolchain/bin/ld.lld").apply { parentFile!!.mkdirs(); writeText("") }
        depsRoot.resolve("sysroot").mkdirs()
        depsRoot.resolve("hms-sysroot").mkdirs()

        val props = Properties().apply {
            setProperty("targetTriple.ohos_arm64", "aarch64-linux-ohos")
            setProperty("dynamicLinker.ohos_arm64", "/lib/ld-musl-aarch64.so.1")
            setProperty("crtFilesLocation.ohos_arm64", "usr/lib/aarch64-linux-ohos")
            setProperty("abiSpecificLibraries.ohos_arm64", "usr/lib/aarch64-linux-ohos")
            setProperty("targetSysRoot.ohos_arm64", "sysroot")
            setProperty("additionalTargetSysRoot.ohos", "hms-sysroot")
            setProperty("targetToolchain.$host-ohos_arm64", "toolchain")
            setProperty("linker.$host-ohos_arm64", "toolchain/bin/ld.lld")
        }

        val configurables = loadConfigurables(KonanTarget.OHOS_ARM64, props, depsRoot.absolutePath)

        assertTrue(configurables is OhosConfigurables)
        val ohos = configurables as OhosConfigurables
        assertEquals("/lib/ld-musl-aarch64.so.1", ohos.dynamicLinker)
        assertEquals("hms-sysroot", ohos.additionalTargetSysRoot)
        assertEquals("aarch64-linux-ohos", ohos.targetTriple.toString())
    }

    @Test
    fun `loadConfigurables returns OhosConfigurables for ohos_x64`(@TempDir tempDir: Path) {
        val host = HostManager.host.name
        val depsRoot = tempDir.resolve("deps").toFile().apply { mkdirs() }
        depsRoot.resolve("toolchain/bin/ld.lld").apply { parentFile!!.mkdirs(); writeText("") }
        depsRoot.resolve("sysroot").mkdirs()

        val props = Properties().apply {
            setProperty("targetTriple.ohos_x64", "x86_64-linux-ohos")
            setProperty("dynamicLinker.ohos_x64", "/lib/ld-musl-x86_64.so.1")
            setProperty("crtFilesLocation.ohos_x64", "usr/lib/x86_64-linux-ohos")
            setProperty("abiSpecificLibraries.ohos_x64", "usr/lib/x86_64-linux-ohos")
            setProperty("targetSysRoot.ohos_x64", "sysroot")
            setProperty("targetToolchain.$host-ohos_x64", "toolchain")
            setProperty("linker.$host-ohos_x64", "toolchain/bin/ld.lld")
        }

        val configurables = loadConfigurables(KonanTarget.OHOS_X64, props, depsRoot.absolutePath)

        assertTrue(configurables is OhosConfigurables)
        val ohos = configurables as OhosConfigurables
        assertEquals("/lib/ld-musl-x86_64.so.1", ohos.dynamicLinker)
        assertEquals("x86_64-linux-ohos", ohos.targetTriple.toString())
        assertEquals("usr/lib/x86_64-linux-ohos", ohos.crtFilesLocation)
    }
}
