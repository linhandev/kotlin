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

@file:Suppress("FunctionName")

package org.jetbrains.kotlin.gradle.unitTests

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.multiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.configurationResult
import org.jetbrains.kotlin.gradle.util.buildProjectWithMPP
import org.jetbrains.kotlin.gradle.util.runLifecycleAwareTest
import org.jetbrains.kotlin.konan.target.KonanTarget
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/** Unit tests for PR #11 Gradle DSL: `ohosArm64()` / `ohosX64()` target presets. */
class OhosGradleTargetTest {

    @Test
    fun ohosArm64AndX64TargetsAreRegistered() = buildProjectWithMPP().runLifecycleAwareTest {
        multiplatformExtension.apply {
            val arm64 = ohosArm64()
            val x64 = ohosX64()

            assertEquals(KonanTarget.OHOS_ARM64, arm64.konanTarget)
            assertEquals(KonanTarget.OHOS_X64, x64.konanTarget)
            assertEquals("ohosArm64", arm64.name)
            assertEquals("ohosX64", x64.name)
        }
    }

    @Test
    fun ohosSourceSetConventionsAreAvailable() = buildProjectWithMPP().runLifecycleAwareTest {
        multiplatformExtension.apply {
            ohosArm64()
            ohosX64()
            configurationResult.await()

            assertNotNull(sourceSets.findByName("ohosArm64Main"))
            assertNotNull(sourceSets.findByName("ohosArm64Test"))
            assertNotNull(sourceSets.findByName("ohosX64Main"))
            assertNotNull(sourceSets.findByName("ohosX64Test"))
        }
    }

    @Test
    fun ohosX64SupportsCustomTargetName() = buildProjectWithMPP().runLifecycleAwareTest {
        multiplatformExtension.apply {
            val custom = ohosX64("myOhosX64")
            assertEquals("myOhosX64", custom.name)
            assertEquals(KonanTarget.OHOS_X64, custom.konanTarget)
        }
    }

    @Test
    fun withOhosHierarchyIncludesBothOhosTargets() = buildProjectWithMPP().runLifecycleAwareTest {
        multiplatformExtension.apply {
            applyHierarchyTemplate {
                common {
                    group("ohos") {
                        withOhos()
                    }
                }
            }
            ohosArm64()
            ohosX64()
            configurationResult.await()

            assertEquals(
                ohosStringSetOf("ohosArm64Main", "ohosX64Main"),
                dependingSourceSetNames("ohosMain"),
            )
        }
    }

    @Test
    fun withOhosX64HierarchySelectsOnlyOhosX64Target() = buildProjectWithMPP().runLifecycleAwareTest {
        multiplatformExtension.apply {
            applyHierarchyTemplate {
                common {
                    group("ohos") {
                        withOhosX64()
                    }
                }
            }
            ohosArm64()
            ohosX64()
            configurationResult.await()

            assertEquals(ohosStringSetOf("ohosX64Main"), dependingSourceSetNames("ohosMain"))
        }
    }
}

private fun KotlinMultiplatformExtension.dependingSourceSetNames(sourceSetName: String): Set<String> {
    val sourceSet = sourceSets.getByName(sourceSetName)
    return sourceSets.filter { sourceSet in it.dependsOn }.map { it.name }.toSet()
}

private fun ohosStringSetOf(vararg values: String) = values.toSet()
