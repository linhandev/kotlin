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
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

/**
 * Unit tests for [OhosLinker.finalLinkCommands]: architecture-specific paths and sanitizer runtime libs.
 */
class OhosLinkerTest {

    @Test
    fun `ohos arm64 link command uses aarch64 lib dirs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()))
        assertTrue(args.any { it.contains("aarch64-linux-ohos") })
        assertFalse(args.any { it.contains("x86_64-linux-ohos") })
    }

    @Test
    fun `ohos x64 link command uses x86_64 lib dirs and builtins`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile()))
        assertTrue(args.any { it.contains("x86_64-linux-ohos") })
        assertTrue(args.any { it.endsWith("libclang_rt.builtins.a") })
    }

    @Test
    fun `ohos link command includes dynamic linker for target architecture`(@TempDir tempDir: Path) {
        val arm64Args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()))
        val x64Args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile()))

        assertTrue(arm64Args.contains("/lib/ld-musl-aarch64.so.1"))
        assertTrue(x64Args.contains("/lib/ld-musl-x86_64.so.1"))
    }

    @Test
    fun `ohos link without sanitizer omits runtime sanitizer libs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()))
        assertFalse(args.any { it.contains("libclang_rt.asan") })
        assertFalse(args.any { it.contains("libclang_rt.hwasan") })
        assertFalse(args.any { it.contains("libclang_rt.tsan") })
    }

    @Test
    fun `ohos arm64 link without sanitizer omits builtins`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()))
        assertFalse(args.any { it.endsWith("libclang_rt.builtins.a") })
    }

    @Test
    fun `ohos x64 link without sanitizer links builtins once`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile()))
        assertEquals(1, args.count { it.endsWith("libclang_rt.builtins.a") })
        assertTrue(args.any { it.contains("x86_64-linux-ohos/libclang_rt.builtins.a") })
    }

    @Test
    fun `ohos link adds hms sysroot lib search path when configured`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(
                KonanTarget.OHOS_ARM64,
                tempDir.toFile(),
                additionalTargetSysRoot = "hms-sysroot",
            )
        )
        assertTrue(args.any { it.startsWith("-L") && it.contains("hms-sysroot/usr/lib/aarch64-linux-ohos") })
    }

    @Test
    fun `ohos x64 link adds hms sysroot x86_64 lib search path`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(
                KonanTarget.OHOS_X64,
                tempDir.toFile(),
                additionalTargetSysRoot = "hms-sysroot",
            )
        )
        assertTrue(args.any { it.startsWith("-L") && it.contains("hms-sysroot/usr/lib/x86_64-linux-ohos") })
    }

    @Test
    fun `ohos linker filters unix static libraries only`(@TempDir tempDir: Path) {
        val linker = OhosLinker(StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()))
        assertEquals(
            listOf("libfoo.a"),
            linker.filterStaticLibraries(listOf("libfoo.a", "libbar.so", "not-a-lib.txt")),
        )
    }

    @Test
    fun `static library linking - uses qcs for object files and qsL for nested archives`(@TempDir tempDir: Path) {
        assumeTrue(HostManager.hostIsLinux || HostManager.hostIsMac || HostManager.hostIsMingw)

        val configurables = StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile())
        val commands = ohosLinkCommands(
            configurables,
            kind = LinkerOutputKind.STATIC_LIBRARY,
            executable = "out.a",
            objectFiles = listOf("obj1.o", "obj2.o"),
            libraries = listOf("lib1.a", "lib2.a"),
        )

        assertEquals(2, commands.size)
        assertEquals(
            listOf("${configurables.absoluteTargetToolchain}/bin/llvm-ar", "qcs", "out.a", "obj1.o", "obj2.o"),
            commands[0].argsWithExecutable,
        )
        assertEquals(
            listOf("${configurables.absoluteTargetToolchain}/bin/llvm-ar", "qsL", "out.a", "lib1.a", "lib2.a"),
            commands[1].argsWithExecutable,
        )
    }

    @Test
    fun `ohos arm64 asan link includes asan runtime libs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()),
            sanitizer = SanitizerKind.ADDRESS,
        )
        assertTrue(args.any { it.endsWith("libclang_rt.asan.so") })
        assertTrue(args.any { it.endsWith("libclang_rt.asan-preinit.a") })
        assertTrue(args.any { it.endsWith("clang_rt.crtend.o") })
        assertFalse(args.any { it.endsWith("libclang_rt.builtins.a") })
    }

    @Test
    fun `ohos x64 asan link uses x86_64 runtime libs without duplicate builtins`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile()),
            sanitizer = SanitizerKind.ADDRESS,
        )
        assertTrue(args.any { it.contains("x86_64-linux-ohos/libclang_rt.asan.so") })
        assertEquals(1, args.count { it.endsWith("libclang_rt.builtins.a") })
    }

    @Test
    fun `ohos x64 hwasan link uses x86_64 runtime libs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile()),
            sanitizer = SanitizerKind.HWADDRESS,
        )
        assertTrue(args.any { it.contains("x86_64-linux-ohos/libclang_rt.hwasan.so") })
        assertTrue(args.any { it.endsWith("libclang_rt.hwasan-preinit.a") })
        assertTrue(args.any { it.endsWith("clang_rt.crtend.o") })
        assertFalse(args.any { it.contains("libclang_rt.asan") })
        assertFalse(args.any { it.contains("libclang_rt.tsan") })
    }

    @Test
    fun `ohos x64 tsan link uses x86_64 runtime libs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_X64, tempDir.toFile()),
            sanitizer = SanitizerKind.THREAD,
        )
        assertTrue(args.any { it.contains("x86_64-linux-ohos/libclang_rt.tsan.so") })
        assertTrue(args.any { it.endsWith("libclang_rt.tsan_cxx.a") })
        assertTrue(args.any { it.endsWith("clang_rt.crtend.o") })
        assertTrue(args.count { it.endsWith("libclang_rt.builtins.a") } >= 1)
    }

    @Test
    fun `ohos arm64 hwasan link includes hwasan runtime libs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()),
            sanitizer = SanitizerKind.HWADDRESS,
        )
        assertTrue(args.any { it.endsWith("libclang_rt.hwasan.so") })
        assertTrue(args.any { it.endsWith("libclang_rt.hwasan-preinit.a") })
        assertTrue(args.any { it.endsWith("clang_rt.crtend.o") })
        assertFalse(args.any { it.contains("libclang_rt.asan") })
        assertFalse(args.any { it.contains("libclang_rt.tsan") })
    }

    @Test
    fun `ohos arm64 tsan link includes tsan runtime libs`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()),
            sanitizer = SanitizerKind.THREAD,
        )
        assertTrue(args.any { it.endsWith("libclang_rt.tsan.so") })
        assertTrue(args.any { it.endsWith("libclang_rt.tsan_cxx.a") })
        assertTrue(args.any { it.endsWith("libclang_rt.builtins.a") })
        assertTrue(args.any { it.endsWith("clang_rt.crtend.o") })
        assertFalse(args.any { it.contains("libclang_rt.hwasan") })
    }

    @Test
    fun `ohos static library link uses llvm-ar`(@TempDir tempDir: Path) {
        val commands = ohosLinkCommands(
            StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()),
            kind = LinkerOutputKind.STATIC_LIBRARY,
            executable = "/tmp/out/libtest.a",
        )
        assertTrue(commands.any { it.args.first().endsWith("llvm-ar") })
    }

    @Test
    fun `ohos link with many libraries uses response file`(@TempDir tempDir: Path) {
        val libraries = (1..17).map { "lib$it.a" }
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()),
            libraries = libraries,
        )
        assertTrue(args.any { it.startsWith("@") })
        assertFalse(args.containsAll(libraries))
    }

    @Test
    fun `dynamic library linking - uses response file when there are more than 16 libraries`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile())
        val libraries = (1..17).map { "/libs/lib$it.a" }
        val args = ohosLinkCommandArgs(
            configurables,
            libraries = libraries,
            kind = LinkerOutputKind.DYNAMIC_LIBRARY,
            executable = "out.so",
        )

        val responseFileArgument = args.single { it.startsWith("@") }
        val responseFileLines = File(responseFileArgument.removePrefix("@")).readLines()
        assertEquals(libraries, responseFileLines)
    }

    @Test
    fun `dynamic library linking - passes libraries directly when there are at most 16 libraries`(@TempDir tempDir: Path) {
        val libraries = (1..16).map { "/libs/lib$it.a" }
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile()),
            libraries = libraries,
            kind = LinkerOutputKind.DYNAMIC_LIBRARY,
            executable = "out.so",
        )

        assertTrue(args.containsAll(libraries))
        assertFalse(args.any { it.startsWith("@") })
    }

    @Test
    fun `ohos dynamic library link sets soname`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(
                KonanTarget.OHOS_ARM64,
                tempDir.toFile(),
                linkerDynamicFlags = listOf("-shared"),
            ),
            kind = LinkerOutputKind.DYNAMIC_LIBRARY,
            executable = "/tmp/out/libfoo.so",
        )
        assertTrue(args.contains("--soname=libfoo.so"))
        assertTrue(args.contains("-shared"))
    }

    @Test
    fun `dynamic debug linking - includes gc-sections and pack-relative-relocs flags`(@TempDir tempDir: Path) {
        val args = ohosLinkCommandArgs(
            StubOhosConfigurables(
                KonanTarget.OHOS_ARM64,
                tempDir.toFile(),
                linkerDynamicFlags = listOf("-shared", "--gc-sections", "-z", "pack-relative-relocs"),
            ),
            kind = LinkerOutputKind.DYNAMIC_LIBRARY,
            executable = "out.so",
        )

        assertTrue(args.contains("-shared"))
        assertTrue(args.contains("--gc-sections"))
        assertTrue(args.contains("pack-relative-relocs"))
    }

    @Test
    fun `linker factory returns OhosLinker for ohos configurables`(@TempDir tempDir: Path) {
        val configurables = StubOhosConfigurables(KonanTarget.OHOS_ARM64, tempDir.toFile())
        assertTrue(linker(configurables) is OhosLinker)
    }
}
