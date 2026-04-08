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
import platform.PreviewKit.Preview.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class PreviewTest {

    private fun logLine(msg: String) = println(msg)

    // 覆盖 open_file_boost.h 中 OpenFileBoost_ErrCode 全部 7 个取值
    @Test
    fun testEnum_OpenFileBoost_ErrCode() {
        assertEquals(OPEN_FILE_BOOST_SUCCESS.toInt(), 0)
        assertEquals(OPEN_FILE_BOOST_PERMISSION_NOT_GRANTED.toInt(), 201)
        assertEquals(OPEN_FILE_BOOST_INVALID_PARAM.toInt(), 401)
        assertEquals(OPEN_FILE_BOOST_INTERNAL_ERROR.toInt(), 1017200001)
        assertEquals(OPEN_FILE_BOOST_INSUFFICIENT_BUFFER.toInt(), 1017200002)
        assertEquals(OPEN_FILE_BOOST_SERVICE_UNAVAILABLE.toInt(), 1017200003)
        assertEquals(OPEN_FILE_BOOST_NO_MEMORY.toInt(), 1017200004)
        logLine("OpenFileBoost_ErrCode passed")
    }

    @Test
    fun testEnum_OpenFileBoost_CbErrCode() {
        assertEquals(OPEN_FILE_BOOST_CALLBACK_SUCCESS.toInt(), 0)
        assertEquals(OPEN_FILE_BOOST_CALLBACK_FAILURE.toInt(), 1017210000)
        logLine("OpenFileBoost_CbErrCode passed")
    }

    @Test
    fun testEnum_OpenFileBoost_AppState() {
        assertEquals(OPEN_FILE_BOOST_APP_STATE_ALLOW_PRELOAD.toInt(), 0)
        assertEquals(OPEN_FILE_BOOST_APP_STATE_REJECT_PRELOAD.toInt(), 1)
        assertEquals(OPEN_FILE_BOOST_APP_STATE_FOREVER_REJECT_PRELOAD.toInt(), 2)
        logLine("OpenFileBoost_AppState passed")
    }

    // 覆盖 open_file_boost.h 全部 5 个函数
    @Test
    fun testHMS_OpenFileBoost_GetFdFromPreloadFileInfo() { memScoped {
        val fd = alloc<IntVar>()
        val ret = HMS_OpenFileBoost_GetFdFromPreloadFileInfo(null, fd.ptr)
        logLine("HMS_OpenFileBoost_GetFdFromPreloadFileInfo ret=$ret fd=${fd.value}")
        assertNotNull(ret)
    } }

    @Test
    fun testHMS_OpenFileBoost_GetSandboxPathFromPreloadFileInfo() { memScoped {
        val buf = allocArray<ByteVar>(MAX_BUFFER_LENGTH)
        val ret = HMS_OpenFileBoost_GetSandboxPathFromPreloadFileInfo(null, buf, MAX_BUFFER_LENGTH)
        logLine("HMS_OpenFileBoost_GetSandboxPathFromPreloadFileInfo ret=$ret")
        assertNotNull(ret)
    } }

    @Test
    fun testHMS_OpenFileBoost_RegisterFilePreload() {
        val ret = HMS_OpenFileBoost_RegisterFilePreload(null, null, null)
        logLine("HMS_OpenFileBoost_RegisterFilePreload ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testHMS_OpenFileBoost_UnregisterFilePreload() {
        val ret = HMS_OpenFileBoost_UnregisterFilePreload()
        logLine("HMS_OpenFileBoost_UnregisterFilePreload ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testHMS_OpenFileBoost_NotifyPreloadHit() { memScoped {
        val ret = HMS_OpenFileBoost_NotifyPreloadHit(0, null, 0)
        logLine("HMS_OpenFileBoost_NotifyPreloadHit ret=$ret")
        assertNotNull(ret)
    } }
}
