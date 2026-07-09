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

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

/** Unit tests for [ClangArgs] OHOS-specific include paths and preprocessor flags (PR #11). */
class OhosClangArgsTest {

    @Test
    fun `ohos arm64 clang args use target triple in sysroot include path`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile())
        val args = ClangArgs.Native(configurables).clangArgs.toList()

        assertTrue(args.any { it.contains("usr/include/aarch64-linux-ohos") })
        assertTrue(args.any { it.startsWith("--sysroot=") })
        assertTrue(args.contains("-DKONAN_LINUX=1"))
        assertTrue(args.contains("-DUSE_ELF_SYMBOLS=1"))
        assertTrue(args.contains("-DUSE_GCC_UNWIND=1"))
    }

    @Test
    fun `ohos x64 clang args use x86_64 include path`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile())
        val args = ClangArgs.Native(configurables).clangArgs.toList()

        assertTrue(args.any { it.contains("usr/include/x86_64-linux-ohos") })
        assertFalse(args.any { it.contains("usr/include/aarch64-linux-ohos") })
    }

    @Test
    fun `ohos arm64 libclang args include cxx and clang resource includes`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile())
        val args = ClangArgs.Native(configurables).libclangArgs

        assertTrue(args.any { it.contains("include/libcxx-ohos/include/c++/v1") })
        assertTrue(args.any { it.contains("/lib/clang/19/include") })
        assertTrue(args.any { it.contains("usr/include/aarch64-linux-ohos") })
    }

    @Test
    fun `ohos x64 libclang args use target triple in sysroot include path`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile())
        val args = ClangArgs.Native(configurables).libclangArgs

        assertTrue(args.any { it.contains("usr/include/x86_64-linux-ohos") })
        assertFalse(args.any { it.contains("usr/include/aarch64-linux-ohos") })
        assertTrue(args.any { it.contains("include/libcxx-ohos/include/c++/v1") })
    }

    @Test
    fun `ohos x64 native clang args use same preprocessor flags as arm64`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile())
        val args = ClangArgs.Native(configurables).clangArgs.toList()

        assertTrue(args.contains("-DKONAN_LINUX=1"))
        assertTrue(args.contains("-DUSE_ELF_SYMBOLS=1"))
        assertTrue(args.contains("-DUSE_GCC_UNWIND=1"))
    }
}
