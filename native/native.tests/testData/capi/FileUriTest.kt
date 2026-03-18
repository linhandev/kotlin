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
import kotlinx.cinterop.*

/**
 * FileUri C API 测试：仅覆盖 filemanagement/file_uri/oh_file_uri.h。
 */
@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class FileUriTest {

    private fun logLine(message: String) = println(message)

    // ---------- oh_file_uri.h ----------

    @Test
    fun testOH_FileUri_GetUriFromPath() {
        memScoped {
            val path = "/data/test.txt"
            val result = alloc<CPointerVar<ByteVar>>()
            val rc = platform.CoreFileKit.FileUri.OH_FileUri_GetUriFromPath(
                path,
                path.length.toUInt(),
                result.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileUri_GetUriFromPath=$rc")
        }
    }

    @Test
    fun testOH_FileUri_GetPathFromUri() {
        memScoped {
            val uri = "file:///data/test.txt"
            val result = alloc<CPointerVar<ByteVar>>()
            val rc = platform.CoreFileKit.FileUri.OH_FileUri_GetPathFromUri(
                uri,
                uri.length.toUInt(),
                result.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileUri_GetPathFromUri=$rc")
        }
    }

    @Test
    fun testOH_FileUri_GetFullDirectoryUri() {
        memScoped {
            val uri = "file:///data/test.txt"
            val result = alloc<CPointerVar<ByteVar>>()
            val rc = platform.CoreFileKit.FileUri.OH_FileUri_GetFullDirectoryUri(
                uri,
                uri.length.toUInt(),
                result.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileUri_GetFullDirectoryUri=$rc")
        }
    }

    @Test
    fun testOH_FileUri_IsValidUri() {
        memScoped {
            val uri = "file:///data/test.txt"
            val ok = platform.CoreFileKit.FileUri.OH_FileUri_IsValidUri(
                uri,
                uri.length.toUInt()
            )
            logLine("OH_FileUri_IsValidUri=$ok")
        }
    }

    @Test
    fun testOH_FileUri_GetFileName() {
        memScoped {
            val uri = "file:///data/test.txt"
            val result = alloc<CPointerVar<ByteVar>>()
            val rc = platform.CoreFileKit.FileUri.OH_FileUri_GetFileName(
                uri,
                uri.length.toUInt(),
                result.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileUri_GetFileName=$rc")
        }
    }
}
