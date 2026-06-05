/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.abi.tools.api.v2

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class KlibTargetNameTest {
    @Test
    fun parse() {
        assertEquals("a.b", KlibTarget("a", "b").toString())
        assertEquals("a", KlibTarget("a").toString())
        assertEquals("a", KlibTarget("a", "a").toString())

        assertFailsWith<IllegalArgumentException> { KlibTarget.parse("") }
        assertFailsWith<IllegalArgumentException> { KlibTarget.parse(" ") }
        assertFailsWith<IllegalArgumentException> { KlibTarget.parse("a.b.c") }
        assertFailsWith<IllegalArgumentException> { KlibTarget.parse("a.") }
        assertFailsWith<IllegalArgumentException> { KlibTarget.parse(".a") }

        KlibTarget.parse("a.b").also {
            assertEquals("b", it.configurableName)
            assertEquals("a", it.targetName)
        }

        KlibTarget.parse("a.a").also {
            assertEquals("a", it.configurableName)
            assertEquals("a", it.targetName)
        }

        KlibTarget.parse("a").also {
            assertEquals("a", it.configurableName)
            assertEquals("a", it.targetName)
        }
    }

    @Test
    fun validate() {
        assertFailsWith<IllegalArgumentException> {
            KlibTarget("a.b", "c")
        }
        assertFailsWith<IllegalArgumentException> {
            KlibTarget("a", "b.c")
        }
    }

    @Test
    fun targetsEqual() {
        assertEquals(KlibTarget.parse("androidNativeArm64"), KlibTarget.parse("androidNativeArm64"))
        assertNotEquals(KlibTarget.parse("androidNativeArm64"), KlibTarget.parse("androidNativeArm32"))

        assertEquals(
            KlibTarget.parse("androidNativeArm64.android"), KlibTarget.parse("androidNativeArm64.android")
        )
        assertNotEquals(
            KlibTarget.parse("androidNativeArm64.android"), KlibTarget.parse("androidNativeArm64")
        )

        assertEquals(
            KlibTarget.parse("androidNativeArm64.androidNativeArm64"),
            KlibTarget.parse("androidNativeArm64")
        )
    }

    @Test
    fun ohosKonanTargetsAreMapped() {
        assertEquals(KlibTarget.parse("ohosArm64"), KlibTarget.fromKonanTargetName("ohos_arm64"))
        assertEquals(KlibTarget.parse("ohosX64"), KlibTarget.fromKonanTargetName("ohos_x64"))
    }

    @Test
    fun ohosKonanTargetsAreAdvertisedAsSupported() {
        assertTrue("ohos_arm64" in KlibTarget.supportedKonanNames())
        assertTrue("ohos_x64" in KlibTarget.supportedKonanNames())
    }

    @Test
    fun ohosX64KonanTargetMapsToConfigurableName() {
        val klib = KlibTarget.fromKonanTargetName("ohos_x64")
        assertEquals("ohosX64", klib.configurableName)
        assertEquals("ohos_x64", klib.targetName)
        assertEquals(KlibTarget.parse("ohosX64"), klib)
    }

    @Test
    fun targetHashCode() {
        assertEquals(
            KlibTarget.parse("androidNativeArm64").hashCode(),
            KlibTarget.parse("androidNativeArm64").hashCode()
        )
        assertNotEquals(
            KlibTarget.parse("androidNativeArm64").hashCode(),
            KlibTarget.parse("androidNativeArm32").hashCode()
        )

        assertEquals(
            KlibTarget.parse("androidNativeArm64.android").hashCode(),
            KlibTarget.parse("androidNativeArm64.android").hashCode()
        )
        assertNotEquals(
            KlibTarget.parse("androidNativeArm64.android").hashCode(),
            KlibTarget.parse("androidNativeArm64").hashCode()
        )

        assertEquals(
            KlibTarget.parse("androidNativeArm64.androidNativeArm64").hashCode(),
            KlibTarget.parse("androidNativeArm64").hashCode()
        )
    }
}
