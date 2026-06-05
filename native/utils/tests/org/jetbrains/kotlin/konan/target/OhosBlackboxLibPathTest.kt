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
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

/**
 * Unit tests for PR #1 blackbox HMS sysroot lib path resolution
 * (mirrors logic in `TestCompilation.kt` without depending on native.tests).
 */
class OhosBlackboxLibPathTest {

    @Test
    fun `ohos arm64 hms lib path resolves under konan dependencies`(@TempDir tempDir: Path) {
        val konanData = tempDir.resolve(".konan").toFile()
        val sysrootName = "sysroot-hms-aarch64-6.0.2.640-02"
        val libDir = File(File(konanData, "dependencies"), sysrootName).resolve("usr/lib/aarch64-linux-ohos")
        libDir.mkdirs()

        val path = ohosBlackboxHmsLibSearchPath(konanData, sysrootName, KonanTarget.OHOS_ARM64)

        assertEquals(libDir.absolutePath, path)
    }

    @Test
    fun `ohos x64 hms lib path uses x86_64 lib dir`(@TempDir tempDir: Path) {
        val konanData = tempDir.resolve(".konan").toFile()
        val sysrootName = "sysroot-hms-x64"
        val libDir = File(File(konanData, "dependencies"), sysrootName).resolve("usr/lib/x86_64-linux-ohos")
        libDir.mkdirs()

        val path = ohosBlackboxHmsLibSearchPath(konanData, sysrootName, KonanTarget.OHOS_X64)

        assertEquals(libDir.absolutePath, path)
    }

    @Test
    fun `ohos hms lib path returns null for non-ohos targets`() {
        assertNull(ohosBlackboxHmsLibSearchPath(File("/tmp"), "sysroot", KonanTarget.LINUX_X64))
    }
}
