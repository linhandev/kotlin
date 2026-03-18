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
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertEquals
import kotlinx.cinterop.*
import platform.gles32.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Gles32Test {

    private fun logLine(msg: String) = println(msg)

    // GLES3/gl32.h
    @Test
    fun testGl32_h() {
        assertEquals(0, GL_NO_ERROR)
        assertEquals(0, GL_FALSE)
        assertEquals(1, GL_TRUE)
        assertEquals(0x0004, GL_TRIANGLES)
        val err = glGetError()
        assertNotNull(err)
        logLine("glGetError()=$err GL_NO_ERROR=$GL_NO_ERROR GL_TRIANGLES=$GL_TRIANGLES")
    }
}
