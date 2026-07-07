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
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Unit tests for HarmonyOS native targets: PR #1 (`ohos_arm64`) and PR #11 (`ohos_x64`).
 */
class OhosX64TargetTest {

    @Test
    fun `ohos arm64 is registered as predefined konan target`() {
        assertEquals(KonanTarget.OHOS_ARM64, KonanTarget.predefinedTargets["ohos_arm64"])
    }

    @Test
    fun `ohos arm64 has ohos family and arm64 architecture`() {
        assertEquals(Family.OHOS, KonanTarget.OHOS_ARM64.family)
        assertEquals(Architecture.ARM64, KonanTarget.OHOS_ARM64.architecture)
        assertEquals("ohos_arm64", KonanTarget.OHOS_ARM64.name)
    }

    @Test
    fun `ohos_x64 is registered as predefined konan target`() {
        assertEquals(KonanTarget.OHOS_X64, KonanTarget.predefinedTargets["ohos_x64"])
    }

    @Test
    fun `ohos_x64 has ohos family and x64 architecture`() {
        assertEquals(Family.OHOS, KonanTarget.OHOS_X64.family)
        assertEquals(Architecture.X64, KonanTarget.OHOS_X64.architecture)
        assertEquals("ohos_x64", KonanTarget.OHOS_X64.name)
    }

    @Test
    fun `ohos_x64 differs from ohos_arm64`() {
        assertNotEquals(KonanTarget.OHOS_ARM64.architecture, KonanTarget.OHOS_X64.architecture)
        assertEquals(Family.OHOS, KonanTarget.OHOS_ARM64.family)
    }

    @Test
    fun `host manager resolves ohos_arm64 by name`() {
        assertEquals(KonanTarget.OHOS_ARM64, HostManager().targetByName("ohos_arm64"))
    }

    @Test
    fun `host manager resolves ohos_x64 by name`() {
        assertEquals(KonanTarget.OHOS_X64, HostManager().targetByName("ohos_x64"))
    }

    @Test
    fun `ohos family uses same binary extensions as linux`() {
        assertEquals(Family.LINUX.exeSuffix, Family.OHOS.exeSuffix)
        assertEquals(Family.LINUX.dynamicPrefix, Family.OHOS.dynamicPrefix)
        assertEquals(Family.LINUX.dynamicSuffix, Family.OHOS.dynamicSuffix)
        assertEquals(Family.LINUX.staticPrefix, Family.OHOS.staticPrefix)
        assertEquals(Family.LINUX.staticSuffix, Family.OHOS.staticSuffix)
    }

    @Test
    fun `ohos targets use elf binary format and 64-bit pointers`() {
        assertEquals(BinaryFormat.ELF, KonanTarget.OHOS_ARM64.binaryFormat())
        assertEquals(BinaryFormat.ELF, KonanTarget.OHOS_X64.binaryFormat())
        assertEquals(64, KonanTarget.OHOS_ARM64.pointerBits())
        assertEquals(64, KonanTarget.OHOS_X64.pointerBits())
    }

    @Test
    fun `ohos family is not apple family`() {
        assertFalse(Family.OHOS.isAppleFamily)
    }

    @Test
    fun `ohos targets are enabled on current host`() {
        val hostManager = HostManager()
        assertTrue(hostManager.isEnabled(KonanTarget.OHOS_ARM64))
        assertTrue(hostManager.isEnabled(KonanTarget.OHOS_X64))
    }

    @Test
    fun `ohos targets support gcc unwind`() {
        assertTrue(KonanTarget.OHOS_ARM64.supportsGccUnwind())
        assertTrue(KonanTarget.OHOS_X64.supportsGccUnwind())
    }
}
