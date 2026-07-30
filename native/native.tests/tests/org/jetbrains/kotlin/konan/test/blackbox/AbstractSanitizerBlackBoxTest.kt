/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.test.blackbox

import com.intellij.testFramework.TestDataFile
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.Sanitizer
import org.junit.jupiter.api.Assumptions

/**
 * Sanitizer corruption / TBI blackbox suites only make sense with AddressSanitizer or
 * HWAddressSanitizer. Skip the whole suite when sanitizer is NONE/THREAD (or unset).
 */
abstract class AbstractSanitizerBlackBoxTest : AbstractNativeBlackBoxTest() {
    override fun runTest(@TestDataFile testDataFilePath: String) {
        val sanitizer = testRunSettings.get<Sanitizer>()
        Assumptions.assumeTrue(
            sanitizer == Sanitizer.ADDRESS || sanitizer == Sanitizer.HWADDRESS
        ) { "Sanitizer suite requires sanitizer=ADDRESS|HWADDRESS (got ${sanitizer.name})" }
        super.runTest(testDataFilePath)
    }
}
