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

/** Unit tests for PR #212 [GccBasedLinker] HWASan / ASan / TSan runtime library wiring on Linux. */
class LinuxSanitizerLinkerTest {

    @Test
    fun `linux x64 hwasan link includes hwasan compiler-rt libs`(@TempDir tempDir: Path) {
        val args = linuxLinkCommandArgs(StubLinuxX64Configurables(tempDir.toFile()), SanitizerKind.HWADDRESS)
        assertTrue(args.contains("-lrt"))
        assertTrue(args.any { it.endsWith("libclang_rt.hwasan.a") })
        assertTrue(args.any { it.endsWith("libclang_rt.hwasan_cxx.a") })
        assertFalse(args.any { it.contains("libclang_rt.asan") })
        assertFalse(args.any { it.contains("libclang_rt.tsan") })
    }

    @Test
    fun `linux x64 asan link includes asan compiler-rt libs`(@TempDir tempDir: Path) {
        val args = linuxLinkCommandArgs(StubLinuxX64Configurables(tempDir.toFile()), SanitizerKind.ADDRESS)
        assertTrue(args.contains("-lrt"))
        assertTrue(args.any { it.endsWith("libclang_rt.asan.a") })
        assertTrue(args.any { it.endsWith("libclang_rt.asan_cxx.a") })
        assertFalse(args.any { it.contains("libclang_rt.hwasan") })
    }

    @Test
    fun `linux x64 tsan link includes tsan compiler-rt libs`(@TempDir tempDir: Path) {
        val args = linuxLinkCommandArgs(StubLinuxX64Configurables(tempDir.toFile()), SanitizerKind.THREAD)
        assertTrue(args.contains("-lrt"))
        assertTrue(args.any { it.endsWith("libclang_rt.tsan.a") })
        assertTrue(args.any { it.endsWith("libclang_rt.tsan_cxx.a") })
        assertFalse(args.any { it.contains("libclang_rt.hwasan") })
    }

    @Test
    fun `linker factory returns GccBasedLinker for linux configurables`(@TempDir tempDir: Path) {
        val configurables = StubLinuxX64Configurables(tempDir.toFile())
        assertTrue(linker(configurables) is GccBasedLinker)
    }
}
