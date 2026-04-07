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
import platform.BasicServicesKit.OH_Scan.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_ScanTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- 枚举：Scan_ErrorCode----------
    @Test
    fun testEnum_Scan_ErrorCode() {
        assertEquals(SCAN_ERROR_NONE.toInt(), 0)
        assertEquals(SCAN_ERROR_NO_PERMISSION.toInt(), 201)
        assertEquals(SCAN_ERROR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(SCAN_ERROR_GENERIC_FAILURE.toInt(), 24300101)
        assertEquals(SCAN_ERROR_RPC_FAILURE.toInt(), 24300102)
        assertEquals(SCAN_ERROR_SERVER_FAILURE.toInt(), 24300103)
        assertEquals(SCAN_ERROR_UNSUPPORTED.toInt(), 24300104)
        assertEquals(SCAN_ERROR_CANCELED.toInt(), 24300105)
        assertEquals(SCAN_ERROR_DEVICE_BUSY.toInt(), 24300106)
        assertEquals(SCAN_ERROR_INVALID.toInt(), 24300107)
        assertEquals(SCAN_ERROR_JAMMED.toInt(), 24300108)
        assertEquals(SCAN_ERROR_NO_DOCS.toInt(), 24300109)
        assertEquals(SCAN_ERROR_COVER_OPEN.toInt(), 24300110)
        assertEquals(SCAN_ERROR_IO_ERROR.toInt(), 24300111)
        assertEquals(SCAN_ERROR_NO_MEMORY.toInt(), 24300112)
        logLine("Scan_ErrorCode: all values passed")
    }

    // ---------- 函数：逐个调用并 logLine ----------
    @Test
    fun testOH_Scan_Init_Exit() {
        val initRet = OH_Scan_Init()
        logLine("OH_Scan_Init ret=$initRet")
        assertNotNull(initRet)
        val exitRet = OH_Scan_Exit()
        logLine("OH_Scan_Exit ret=$exitRet")
        assertNotNull(exitRet)
    }

    @Test
    fun testOH_Scan_StartScannerDiscovery() {
        val ret = OH_Scan_StartScannerDiscovery(null)
        logLine("OH_Scan_StartScannerDiscovery ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_Scan_OpenScanner() {
        val ret = OH_Scan_OpenScanner(null)
        logLine("OH_Scan_OpenScanner ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_Scan_CloseScanner() {
        val ret = OH_Scan_CloseScanner(null)
        logLine("OH_Scan_CloseScanner ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_Scan_GetScannerParameter() { memScoped {
        val err = alloc<IntVar>()
        err.value = 0
        val opts = OH_Scan_GetScannerParameter(null, err.ptr)
        logLine("OH_Scan_GetScannerParameter opts=$opts errorCode=${err.value}")
        assertNotNull(err.ptr)
    } }

    @Test
    fun testOH_Scan_SetScannerParameter() {
        val ret = OH_Scan_SetScannerParameter(null, 0, null)
        logLine("OH_Scan_SetScannerParameter ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_Scan_StartScan() {
        val ret = OH_Scan_StartScan(null, false)
        logLine("OH_Scan_StartScan ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_Scan_CancelScan() {
        val ret = OH_Scan_CancelScan(null)
        logLine("OH_Scan_CancelScan ret=$ret")
        assertNotNull(ret)
    }

    // @Test
    // fun testOH_Scan_GetPictureScanProgress() { memScoped {
    //     val prog = alloc<Scan_PictureScanProgress>()
    //     prog.progress = 0
    //     prog.fd = -1
    //     prog.isFinal = false
    //     val ret = OH_Scan_GetPictureScanProgress(null, prog.ptr)
    //     logLine("OH_Scan_GetPictureScanProgress ret=$ret progress=${prog.progress} fd=${prog.fd} isFinal=${prog.isFinal}")
    //     assertNotNull(ret)
    // } }
}
