/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.spec.utils.tasks

import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5
import org.jetbrains.kotlin.generators.impl.generateTestGroupSuite
import org.jetbrains.kotlin.spec.checkers.AbstractDiagnosticsTestSpec
import org.jetbrains.kotlin.spec.codegen.AbstractBlackBoxCodegenTestSpec
import org.jetbrains.kotlin.spec.parsing.AbstractParsingTestSpec
import org.jetbrains.kotlin.spec.utils.GeneralConfiguration.SPEC_TESTDATA_PATH
import org.jetbrains.kotlin.spec.utils.GeneralConfiguration.SPEC_TEST_PATH
import org.jetbrains.kotlin.spec.utils.GeneralConfiguration.TESTS_MAP_FILENAME
import org.jetbrains.kotlin.spec.utils.SectionsJsonMapGenerator
import org.jetbrains.kotlin.spec.utils.TestsJsonMapGenerator
import org.jetbrains.kotlin.test.runners.AbstractFirBlackBoxCodegenTestSpec
import org.jetbrains.kotlin.test.runners.AbstractFirLightTreeDiagnosticTestSpec
import org.jetbrains.kotlin.test.runners.AbstractFirPsiDiagnosticTestSpec
import org.jetbrains.kotlin.test.utils.CUSTOM_TEST_DATA_EXTENSION_PATTERN
import java.io.File
import java.nio.file.Files

// `baseDir` is used in Kotlin plugin from IJ infra
fun detectDirsWithTestsMapFileOnly(dirName: String, baseDir: String = "."): List<String> {
    val excludedDirs = mutableListOf<String>()
    val root = File("${baseDir}/$SPEC_TESTDATA_PATH/$dirName")

    root.walkTopDown().forEach { file ->
        if (!file.isDirectory) return@forEach

        // Files.walk must be closed; otherwise directory streams leak and can hit "Too many open files".
        val onlyTestsMap = Files.walk(file.toPath()).use { stream ->
            stream.filter(Files::isRegularFile).allMatch { it.endsWith(TESTS_MAP_FILENAME) }
        }

        if (onlyTestsMap) {
            val relativePath = file.relativeTo(root).path

            if (!excludedDirs.any { relativePath.startsWith(it) }) {
                excludedDirs.add(relativePath)
            }
        }
    }

    return excludedDirs.sorted().map { it.replace("\\", "/") }
}

fun generateTests() {
    // Cache once: the tree walk is expensive and used to be repeated for each test class.
    val diagnosticsExcludeDirs = listOf("helpers") + detectDirsWithTestsMapFileOnly("diagnostics")
    val psiExcludeDirs = listOf("helpers", "templates") + detectDirsWithTestsMapFileOnly("psi")
    val codegenBoxExcludeDirs = listOf("helpers", "templates") + detectDirsWithTestsMapFileOnly("codegen/box")

    generateTestGroupSuite {
        testGroup(SPEC_TEST_PATH, SPEC_TESTDATA_PATH) {
            testClass<AbstractDiagnosticsTestSpec> {
                model(
                    "diagnostics",
                    excludeDirs = diagnosticsExcludeDirs,
                    excludedPattern = CUSTOM_TEST_DATA_EXTENSION_PATTERN,
                )
            }

            testClass<AbstractParsingTestSpec> {
                model(
                    relativeRootPath = "psi",
                    testMethod = "doParsingTest",
                    excludeDirs = psiExcludeDirs
                )
            }
            testClass<AbstractBlackBoxCodegenTestSpec> {
                model(
                    relativeRootPath = "codegen/box",
                    excludeDirs = codegenBoxExcludeDirs,
                )
            }
        }
    }

    generateTestGroupSuiteWithJUnit5 {
        testGroup(testsRoot = "compiler/fir/analysis-tests/tests-gen", testDataRoot = SPEC_TESTDATA_PATH) {
            testClass<AbstractFirPsiDiagnosticTestSpec> {
                model(
                    "diagnostics",
                    excludeDirs = diagnosticsExcludeDirs,
                    excludedPattern = CUSTOM_TEST_DATA_EXTENSION_PATTERN
                )
            }
            testClass<AbstractFirLightTreeDiagnosticTestSpec> {
                model(
                    "diagnostics",
                    excludeDirs = diagnosticsExcludeDirs,
                    excludedPattern = CUSTOM_TEST_DATA_EXTENSION_PATTERN
                )
            }
        }

        testGroup(SPEC_TEST_PATH, SPEC_TESTDATA_PATH) {
            testClass<AbstractFirBlackBoxCodegenTestSpec> {
                model(
                    relativeRootPath = "codegen/box",
                    excludeDirs = codegenBoxExcludeDirs,
                )
            }
        }
    }
}

fun main() {
    TestsJsonMapGenerator.buildTestsMapPerSection()
    SectionsJsonMapGenerator.writeSectionsMapJsons()
    generateTests()
}
