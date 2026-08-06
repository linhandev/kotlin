/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.test.blackbox

import com.intellij.testFramework.TestDataFile
import org.jetbrains.kotlin.konan.target.SanitizerKind
import org.jetbrains.kotlin.konan.target.supportedSanitizers
import org.jetbrains.kotlin.konan.test.blackbox.support.ClassLevelProperty
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.Allocator
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.GCType
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.KotlinNativeTargets
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.PagedAllocator
import org.jetbrains.kotlin.konan.test.blackbox.support.settings.Sanitizer
import org.junit.jupiter.api.Assumptions

/**
 * Sanitizer corruption / TBI blackbox suites only make sense with AddressSanitizer or
 * HWAddressSanitizer. Skip when sanitizer is NONE/THREAD (or unset), or when the current
 * test target does not advertise that sanitizer in [supportedSanitizers].
 *
 * Matrix suites pin gc/alloc/sanitizer via `@EnforcedProperty`. Optional `-P` values for
 * `gcType` / `alloc` / `sanitizer` act as filters only: if set, and they disagree with the
 * suite's resolved settings, the suite is skipped (they do not override EnforcedProperty).
 */
abstract class AbstractSanitizerBlackBoxTest : AbstractNativeBlackBoxTest() {
    override fun runTest(@TestDataFile testDataFilePath: String) {
        val sanitizer = testRunSettings.get<Sanitizer>()
        Assumptions.assumeTrue(
            sanitizer == Sanitizer.ADDRESS || sanitizer == Sanitizer.HWADDRESS
        ) { "Sanitizer suite requires sanitizer=ADDRESS|HWADDRESS (got ${sanitizer.name})" }

        val sanitizerKind = when (sanitizer) {
            Sanitizer.ADDRESS -> SanitizerKind.ADDRESS
            Sanitizer.HWADDRESS -> SanitizerKind.HWADDRESS
            Sanitizer.THREAD, Sanitizer.NONE -> error("unreachable: filtered above")
        }
        val testTarget = testRunSettings.get<KotlinNativeTargets>().testTarget
        Assumptions.assumeTrue(sanitizerKind in testTarget.supportedSanitizers()) {
            "${sanitizer.name} sanitizer is unsupported on ${testTarget.name}; " +
                    "supported=${testTarget.supportedSanitizers()}"
        }

        assumeMatchesOptionalFilter(ClassLevelProperty.SANITIZER, sanitizer.name)
        assumeMatchesOptionalFilter(ClassLevelProperty.GC_TYPE, testRunSettings.get<GCType>().name)
        assumeMatchesOptionalAllocFilter(testRunSettings.get<Allocator>())
        assumeMatchesOptionalPagedAllocatorFilter(testRunSettings.get<PagedAllocator>())

        super.runTest(testDataFilePath)
    }

    private fun assumeMatchesOptionalFilter(property: ClassLevelProperty, actual: String) {
        val requested = System.getProperty(property.propertyName) ?: return
        Assumptions.assumeTrue(requested == actual) {
            "Skipped by -P${property.propertyName}=$requested (suite has ${property.shortName}=$actual)"
        }
    }

    /**
     * When the suite leaves alloc UNSPECIFIED (CMC → CRT), any explicit `-Palloc=STD|CUSTOM`
     * is treated as a mismatch so those cells are filtered out.
     */
    private fun assumeMatchesOptionalAllocFilter(allocator: Allocator) {
        val requested = System.getProperty(ClassLevelProperty.ALLOCATOR.propertyName) ?: return
        val matches = when (allocator) {
            Allocator.UNSPECIFIED -> false
            else -> requested == allocator.name
        }
        Assumptions.assumeTrue(matches) {
            "Skipped by -P${ClassLevelProperty.ALLOCATOR.propertyName}=$requested " +
                    "(suite has alloc=${allocator.name})"
        }
    }

    /**
     * Suites that do not pin pagedAllocator (UNSPECIFIED) are skipped when the user
     * explicitly passes `-PpagedAllocator=TRUE|FALSE`.
     */
    private fun assumeMatchesOptionalPagedAllocatorFilter(pagedAllocator: PagedAllocator) {
        val requested = System.getProperty(ClassLevelProperty.PAGED_ALLOCATOR.propertyName) ?: return
        val matches = when (pagedAllocator) {
            PagedAllocator.UNSPECIFIED -> false
            else -> requested == pagedAllocator.name
        }
        Assumptions.assumeTrue(matches) {
            "Skipped by -P${ClassLevelProperty.PAGED_ALLOCATOR.propertyName}=$requested " +
                    "(suite has pagedAllocator=${pagedAllocator.name})"
        }
    }
}
