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

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class FileIOTest {

    private fun logLine(message: String) = println(message)

    // ---------- enums: error_code.h (FileManagement_ErrCode) ----------
    @Test
    fun testEnum_FileManagement_ErrCode() {
        fun p(name: String, v: Int, expected: Int) {
            logLine("$name=$v")
            assert(v == expected)
        }
        p("ERR_OK", platform.CoreFileKit.FileIO.ERR_OK.toInt(), 0)
        p("ERR_PERMISSION_ERROR", platform.CoreFileKit.FileIO.ERR_PERMISSION_ERROR.toInt(), 201)
        p("ERR_INVALID_PARAMETER", platform.CoreFileKit.FileIO.ERR_INVALID_PARAMETER.toInt(), 401)
        p("ERR_DEVICE_NOT_SUPPORTED", platform.CoreFileKit.FileIO.ERR_DEVICE_NOT_SUPPORTED.toInt(), 801)
        p("ERR_EPERM", platform.CoreFileKit.FileIO.ERR_EPERM.toInt(), 13900001)
        p("ERR_ENOENT", platform.CoreFileKit.FileIO.ERR_ENOENT.toInt(), 13900002)
        p("ERR_ENOMEM", platform.CoreFileKit.FileIO.ERR_ENOMEM.toInt(), 13900011)
        p("ERR_UNKNOWN", platform.CoreFileKit.FileIO.ERR_UNKNOWN.toInt(), 13900042)
    }

    // ---------- enums: oh_fileio.h (FileIO_FileLocation) ----------
    @Test
    fun testEnum_FileIO_FileLocation() {
        val v1 = platform.CoreFileKit.FileIO.LOCAL
        val v2 = platform.CoreFileKit.FileIO.CLOUD
        val v3 = platform.CoreFileKit.FileIO.LOCAL_AND_CLOUD
        logLine("LOCAL=$v1"); assert(v1.toInt() == 1)
        logLine("CLOUD=$v2"); assert(v2.toInt() == 2)
        logLine("LOCAL_AND_CLOUD=$v3"); assert(v3.toInt() == 3)
    }


    // ---------- oh_fileio.h: OH_FileIO_GetFileLocation ----------
    @Test
    fun testOH_FileIO_GetFileLocation() {
        memScoped {
            val uri = "file:///data/test.txt".encodeToByteArray().toCValues()
            val location = alloc<platform.CoreFileKit.FileIO.FileIO_FileLocationVar>()
            val rc = platform.CoreFileKit.FileIO.OH_FileIO_GetFileLocation(
                uri.ptr.reinterpret(),
                20,
                location.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileIO_GetFileLocation=$rc")
        }
    }
}
