/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.test.blackbox

import com.intellij.testFramework.TestDataFile
import org.jetbrains.kotlin.konan.target.Family
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.KotlinNativeTargets
import org.junit.jupiter.api.Assumptions

/**
 * OHOS CAPI blackbox tests exercise `platform.*` kits that exist only in the OHOS platform klib.
 * Skip the suite on any non-OHOS target family.
 */
abstract class AbstractOhosCAPIBlackBoxTest : AbstractNativeBlackBoxTest() {
    override fun runTest(@TestDataFile testDataFilePath: String) {
        Assumptions.assumeTrue(
            testRunSettings.get<KotlinNativeTargets>().testTarget.family == Family.OHOS
        ) { "CAPI tests require targetFamily=OHOS" }
        super.runTest(testDataFilePath)
    }
}
