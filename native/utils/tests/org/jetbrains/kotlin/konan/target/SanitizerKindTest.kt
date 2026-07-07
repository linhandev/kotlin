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
import org.junit.jupiter.api.Test

/**
 * Unit tests for PR #212 (ASan / HWASan / TSan): sanitizer kinds, target matrix, and build flags.
 */
class SanitizerKindTest {

    @Test
    fun `enum contains address thread and hwaddress`() {
        assertEquals(
            setOf(SanitizerKind.ADDRESS, SanitizerKind.THREAD, SanitizerKind.HWADDRESS),
            enumValues<SanitizerKind>().toSet(),
        )
    }

    @Test
    fun `targetSuffix maps each sanitizer to konan target name suffix`() {
        assertEquals("", null.targetSuffix)
        assertEquals("_asan", SanitizerKind.ADDRESS.targetSuffix)
        assertEquals("_tsan", SanitizerKind.THREAD.targetSuffix)
        assertEquals("_hwasan", SanitizerKind.HWADDRESS.targetSuffix)
    }

    @Test
    fun `ohos targets support address thread and hwaddress sanitizers`() {
        val expected = listOf(
            SanitizerKind.ADDRESS,
            SanitizerKind.HWADDRESS,
            SanitizerKind.THREAD,
        )
        assertEquals(expected, KonanTarget.OHOS_ARM64.supportedSanitizers())
        assertEquals(expected, KonanTarget.OHOS_X64.supportedSanitizers())
    }

    @Test
    fun `linux x64 supports address and thread but not hwaddress`() {
        val supported = KonanTarget.LINUX_X64.supportedSanitizers()
        assertTrue(SanitizerKind.ADDRESS in supported)
        assertTrue(SanitizerKind.THREAD in supported)
        assertFalse(SanitizerKind.HWADDRESS in supported)
    }

    @Test
    fun `macos targets support thread sanitizer only`() {
        val expected = listOf(SanitizerKind.THREAD)
        assertEquals(expected, KonanTarget.MACOS_X64.supportedSanitizers())
        assertEquals(expected, KonanTarget.MACOS_ARM64.supportedSanitizers())
        assertFalse(SanitizerKind.ADDRESS in KonanTarget.MACOS_ARM64.supportedSanitizers())
        assertFalse(SanitizerKind.HWADDRESS in KonanTarget.MACOS_ARM64.supportedSanitizers())
    }

    @Test
    fun `ohos targets use elf binary format for sanitizer tooling`() {
        assertEquals(BinaryFormat.ELF, KonanTarget.OHOS_ARM64.binaryFormat())
        assertEquals(BinaryFormat.ELF, KonanTarget.OHOS_X64.binaryFormat())
    }

    @Test
    fun `sanitized konan target name is base name plus suffix`() {
        val base = KonanTarget.OHOS_ARM64.name
        assertEquals("${base}_asan", base + SanitizerKind.ADDRESS.targetSuffix)
        assertEquals("${base}_tsan", base + SanitizerKind.THREAD.targetSuffix)
        assertEquals("${base}_hwasan", base + SanitizerKind.HWADDRESS.targetSuffix)
    }

    @Test
    fun `hwaddress is not advertised for android targets`() {
        assertFalse(SanitizerKind.HWADDRESS in KonanTarget.ANDROID_ARM64.supportedSanitizers())
    }

    @Test
    fun `android arm64 supports address and thread but not hwaddress`() {
        val supported = KonanTarget.ANDROID_ARM64.supportedSanitizers()
        assertTrue(SanitizerKind.ADDRESS in supported)
        assertTrue(SanitizerKind.THREAD in supported)
        assertFalse(SanitizerKind.HWADDRESS in supported)
    }

    @Test
    fun `ohos arm64 advertises all three sanitizers after pr 212 expansion`() {
        assertTrue(SanitizerKind.HWADDRESS in KonanTarget.OHOS_ARM64.supportedSanitizers())
        assertTrue(SanitizerKind.THREAD in KonanTarget.OHOS_ARM64.supportedSanitizers())
    }

    @Test
    fun `ohos x64 sanitized target names use distinct suffixes`() {
        val base = KonanTarget.OHOS_X64.name
        val names = setOf(
            base + SanitizerKind.ADDRESS.targetSuffix,
            base + SanitizerKind.THREAD.targetSuffix,
            base + SanitizerKind.HWADDRESS.targetSuffix,
        )
        assertEquals(3, names.size)
        assertEquals("ohos_x64_asan", base + SanitizerKind.ADDRESS.targetSuffix)
        assertEquals("ohos_x64_hwasan", base + SanitizerKind.HWADDRESS.targetSuffix)
    }
}
