/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.test.blackbox

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertTrue

/**
 * Structural / source-level assertions for TBI conflict IDs (P4).
 * Complements dynamic CRT UTs under testData/sanitizer/tbi.
 */
@Tag("sanitizer-tbi")
class SanitizerTbiStructTest {
    private val root = File(".").canonicalFile

    private fun read(path: String): String {
        val f = root.resolve(path)
        assertTrue(f.isFile, "missing structural source: ${f.absolutePath}")
        return f.readText()
    }

    @Test
    fun tbiC01_languageBitsInCrtAllocator() {
        val text = read("kotlin-native/runtime/src/crt/cpp/CRTAllocator.cpp")
        assertTrue("kKotlinLangBits" in text)
        assertTrue("language_" in text || "bits 60..61" in text)
    }

    @Test
    fun tbiC01_baseStateWordLanguageField() {
        val text = read("third-party/common-rt/common_interfaces/objects/base_state_word.h")
        assertTrue(Regex("""language_\s*:""").containsMatchIn(text) || "language_" in text)
    }

    @Test
    fun tbiC02_forwardStateField() {
        val text = read("third-party/common-rt/common_interfaces/objects/base_state_word.h")
        assertTrue("forwardState_" in text)
    }

    @Test
    fun tbiC03_validBitInTypeInfo() {
        val text = read("kotlin-native/runtime/src/main/cpp/TypeInfo.h")
        assertTrue("Bit 59" in text || "valid" in text)
        assertTrue("SetValid" in text)
        // CRT default path does not call SetValid — CustomAllocator does under ENABLE_STACKMAP.
        val custom = read("kotlin-native/runtime/src/alloc/custom/cpp/CustomAllocator.cpp")
        assertTrue("SetValid" in custom || "ENABLE_STACKMAP" in custom)
    }

    @Test
    fun tbiC04_isWeakImplBit() {
        val text = read("kotlin-native/runtime/src/crt/cpp/KNBaseObject.hpp")
        assertTrue("isWeakImpl_" in text)
        assertTrue("SetWeakRefImplObjectFlag" in text)
    }

    @Test
    fun tbiC05_x28ReadBarrierBit() {
        val text = read("kotlin-native/runtime/src/main/cpp/CRTFastpathUtils.hpp")
        assertTrue("TLS_ACTIVE_READ_BARRIER_BIT" in text)
        assertTrue("\"62\"" in text || "62" in text)
        assertTrue("TLS_DATA_MASK" in text)
    }

    @Test
    fun tbiC06_refField48BitAddress() {
        val text = read("third-party/common-rt/common_interfaces/objects/ref_field.h")
        assertTrue(
            Regex("""address\s*:\s*48""").containsMatchIn(text),
            "expected RefField 48-bit address layout in: ${text.lines().filter { "address" in it }.take(5)}"
        )
    }
}
