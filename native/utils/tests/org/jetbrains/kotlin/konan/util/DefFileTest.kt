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

package org.jetbrains.kotlin.konan.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

/**
 * Unit tests for PR #1 (`feature/add ohosArm64 target`): `.def` parsing extensions for OHOS platform libs.
 */
class DefFileTest {

    @Test
    fun `enableUndefinedApiProtection defaults to false when property is absent`(@TempDir tempDir: Path) {
        val defFile = tempDir.resolve("sample.def").toFile().apply {
            writeText("headers = sample.h\n")
        }

        assertFalse(DefFile(defFile, emptyMap()).config.enableUndefinedApiProtection)
    }

    @Test
    fun `enableUndefinedApiProtection is true when property is set to true`(@TempDir tempDir: Path) {
        val defFile = tempDir.resolve("sample.def").toFile().apply {
            writeText(
                """
                headers = sample.h
                enableUndefinedApiProtection = true
                """.trimIndent()
            )
        }

        assertTrue(DefFile(defFile, emptyMap()).config.enableUndefinedApiProtection)
    }

    @Test
    fun `enableUndefinedApiProtection is false when property is set to false`(@TempDir tempDir: Path) {
        val defFile = tempDir.resolve("sample.def").toFile().apply {
            writeText(
                """
                headers = sample.h
                enableUndefinedApiProtection = false
                """.trimIndent()
            )
        }

        assertFalse(DefFile(defFile, emptyMap()).config.enableUndefinedApiProtection)
    }

    @Test
    fun `def file name is derived from file name without extension`(@TempDir tempDir: Path) {
        val defFile = tempDir.resolve("MyModule.def").toFile().apply {
            writeText("headers = sample.h\n")
        }

        assertEquals("MyModule", DefFile(defFile, emptyMap()).name)
    }
}
