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
import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class EnvironmentTest {

    private fun logLine(message: String) = println(message)

    // ---------- oh_environment.h（def 中每个函数一个 @Test） ----------

    @Test
    fun testOH_Environment_GetUserDownloadDir() {
        logLine("--- OH_Environment_GetUserDownloadDir ---")
        memScoped {
            val out = alloc<CPointerVar<ByteVar>>()
            val result = platform.CoreFileKit.Environment.OH_Environment_GetUserDownloadDir(out.ptr)
            assertNotNull(result)
            logLine("OH_Environment_GetUserDownloadDir result: $result")
        }
    }

    @Test
    fun testOH_Environment_GetUserDesktopDir() {
        logLine("--- OH_Environment_GetUserDesktopDir ---")
        memScoped {
            val out = alloc<CPointerVar<ByteVar>>()
            val result = platform.CoreFileKit.Environment.OH_Environment_GetUserDesktopDir(out.ptr)
            assertNotNull(result)
            logLine("OH_Environment_GetUserDesktopDir result: $result")
        }
    }

    @Test
    fun testOH_Environment_GetUserDocumentDir() {
        logLine("--- OH_Environment_GetUserDocumentDir ---")
        memScoped {
            val out = alloc<CPointerVar<ByteVar>>()
            val result = platform.CoreFileKit.Environment.OH_Environment_GetUserDocumentDir(out.ptr)
            assertNotNull(result)
            logLine("OH_Environment_GetUserDocumentDir result: $result")
        }
    }
}
