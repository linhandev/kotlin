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
import kotlin.test.assertTrue
import kotlinx.cinterop.*
import platform.egl.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class EglTest {

    private fun logLine(msg: String) = println(msg)

    // EGL/egl.h
    @Test
    fun testEgl_h() {
        assertEquals(0x3000, EGL_SUCCESS)
        assertEquals(0, EGL_FALSE)
        assertEquals(1, EGL_TRUE)
        val err = eglGetError()
        assertNotNull(err)
        logLine("eglGetError()=$err EGL_SUCCESS=$EGL_SUCCESS")
    }

    // EGL/eglext.h
    @Test
    fun testEglext_h() {
        assertTrue(EGL_EGLEXT_VERSION > 0)
        assertEquals(0x3042, EGL_CONFORMANT_KHR)
        logLine("EGL_EGLEXT_VERSION=$EGL_EGLEXT_VERSION EGL_CONFORMANT_KHR=$EGL_CONFORMANT_KHR")
    }

    // EGL/eglplatform.h — 平台类型（EGLint 等，无函数）
    @Test
    fun testEglplatform_h() {
        val x: EGLint = 0
        assertEquals(0, x)
        logLine("EGLint=$x")
    }
}
