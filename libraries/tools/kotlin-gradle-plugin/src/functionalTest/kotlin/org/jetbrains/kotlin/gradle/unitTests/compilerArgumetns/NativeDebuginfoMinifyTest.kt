/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("FunctionName")

package org.jetbrains.kotlin.gradle.unitTests.compilerArgumetns

import org.jetbrains.kotlin.cli.common.arguments.K2NativeCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.parseCommandLineArguments
import org.jetbrains.kotlin.gradle.dsl.NativeBuildType
import org.jetbrains.kotlin.gradle.dsl.multiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.CreateCompilerArgumentsContext.Companion.lenient
import org.jetbrains.kotlin.gradle.plugin.PropertiesProvider
import org.jetbrains.kotlin.gradle.plugin.PropertiesProvider.Companion.kotlinPropertiesProvider
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import org.jetbrains.kotlin.gradle.util.buildProjectWithMPP
import org.jetbrains.kotlin.gradle.util.kotlin
import org.jetbrains.kotlin.gradle.util.propertiesExtension
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NativeDebuginfoMinifyTest {

    @Test
    fun `PropertiesProvider - reads kotlin native debuginfo minify property`() {
        val project = buildProjectWithMPP {
            propertiesExtension.set(PropertiesProvider.PropertyNames.KOTLIN_NATIVE_DEBUGINFO_MINIFY, "true")
        }
        assertEquals(true, project.kotlinPropertiesProvider.nativeDebuginfoMinify)

        project.propertiesExtension.set(PropertiesProvider.PropertyNames.KOTLIN_NATIVE_DEBUGINFO_MINIFY, "false")
        assertEquals(false, project.kotlinPropertiesProvider.nativeDebuginfoMinify)
    }

    @Test
    fun `PropertiesProvider - returns null when kotlin native debuginfo minify is not set`() {
        val project = buildProjectWithMPP()
        assertNull(project.kotlinPropertiesProvider.nativeDebuginfoMinify)
    }

    @Test
    fun `KotlinNativeLink - passes nativeDebuginfoMinify to compiler arguments for ohos target`() {
        val project = buildProjectWithMPP {
            propertiesExtension.set(PropertiesProvider.PropertyNames.KOTLIN_NATIVE_DEBUGINFO_MINIFY, "false")
        } {
            kotlin {
                ohosArm64 {
                    binaries.sharedLib("main", listOf(NativeBuildType.DEBUG))
                }
            }
        }
        project.evaluate()

        val arguments = project.tasks
            .named(LINK_DEBUG_MAIN_OHOS_ARM64, KotlinNativeLink::class.java)
            .get()
            .createCompilerArguments(lenient)

        assertEquals(false, arguments.nativeDebuginfoMinify)
    }

    @Test
    fun `KotlinNativeLink - passes nativeDebuginfoMinify to compiler arguments for non-ohos target`() {
        val project = buildProjectWithMPP {
            propertiesExtension.set(PropertiesProvider.PropertyNames.KOTLIN_NATIVE_DEBUGINFO_MINIFY, "true")
        } {
            kotlin {
                linuxX64 {
                    binaries.sharedLib("main", listOf(NativeBuildType.DEBUG))
                }
            }
        }
        project.evaluate()

        val arguments = project.tasks
            .named(LINK_DEBUG_MAIN_LINUX_X64, KotlinNativeLink::class.java)
            .get()
            .createCompilerArguments(lenient)

        assertEquals(true, arguments.nativeDebuginfoMinify)
    }

    @Test
    fun `K2NativeCompilerArguments - parses Xnative-debuginfo-minify flag`() {
        val arguments = K2NativeCompilerArguments()
        parseCommandLineArguments(listOf("-Xnative-debuginfo-minify=false"), arguments)
        assertEquals(false, arguments.nativeDebuginfoMinify)

        parseCommandLineArguments(listOf("-Xnative-debuginfo-minify=true"), arguments)
        assertEquals(true, arguments.nativeDebuginfoMinify)
    }

    @Test
    fun `K2NativeCompilerArguments - keeps nativeDebuginfoMinify unset when flag is absent`() {
        val arguments = K2NativeCompilerArguments()
        parseCommandLineArguments(emptyList(), arguments)
        assertNull(arguments.nativeDebuginfoMinify)
    }

    @Test
    fun `KotlinNativeLink - passes cli flag from freeCompilerArgs`() {
        val project = buildProjectWithMPP {
            kotlin {
                ohosArm64 {
                    binaries.sharedLib("main", listOf(NativeBuildType.DEBUG)) {
                        freeCompilerArgs += "-Xnative-debuginfo-minify=false"
                    }
                }
            }
        }
        project.evaluate()

        val linkTask = project.tasks.named(LINK_DEBUG_MAIN_OHOS_ARM64, KotlinNativeLink::class.java).get()
        assertTrue(linkTask.toolOptions.freeCompilerArgs.get().any { it.startsWith("-Xnative-debuginfo-minify") })
    }

    companion object {
        private const val LINK_DEBUG_MAIN_OHOS_ARM64 = "linkDebugMainOhosArm64"
        private const val LINK_DEBUG_MAIN_LINUX_X64 = "linkDebugMainLinuxX64"
    }
}
