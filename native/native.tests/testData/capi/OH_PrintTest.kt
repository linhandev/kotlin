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
import platform.BasicServicesKit.OH_Print.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_PrintTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- Enums (constant style) ----------
    @Test
    fun testEnum_Print_ErrorCode() {
        assertEquals(PRINT_ERROR_NONE.toInt(), 0)
        assertEquals(PRINT_ERROR_NO_PERMISSION.toInt(), 201)
        assertEquals(PRINT_ERROR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(PRINT_ERROR_GENERIC_FAILURE.toInt(), 24300001)
        assertEquals(PRINT_ERROR_RPC_FAILURE.toInt(), 24300002)
        assertEquals(PRINT_ERROR_SERVER_FAILURE.toInt(), 24300003)
        assertEquals(PRINT_ERROR_INVALID_EXTENSION.toInt(), 24300004)
        assertEquals(PRINT_ERROR_INVALID_PRINTER.toInt(), 24300005)
        assertEquals(PRINT_ERROR_INVALID_PRINT_JOB.toInt(), 24300006)
        assertEquals(PRINT_ERROR_FILE_IO.toInt(), 24300007)
        assertEquals(PRINT_ERROR_UNKNOWN.toInt(), 24300255)
        logLine("Print_ErrorCode passed")
    }

    @Test
    fun testEnum_Print_PrinterState() {
        assertEquals(Print_PrinterState.PRINTER_IDLE.value.toInt(), 0)
        assertEquals(Print_PrinterState.PRINTER_BUSY.value.toInt(), 1)
        assertEquals(Print_PrinterState.PRINTER_UNAVAILABLE.value.toInt(), 2)
        logLine("Print_PrinterState passed")
    }

    @Test
    fun testEnum_Print_DiscoveryEvent() {
        assertEquals(PRINTER_DISCOVERED.toInt(), 0)
        assertEquals(PRINTER_LOST.toInt(), 1)
        assertEquals(PRINTER_CONNECTING.toInt(), 2)
        assertEquals(PRINTER_CONNECTED.toInt(), 3)
        logLine("Print_DiscoveryEvent passed")
    }

    @Test
    fun testEnum_Print_PrinterEvent() {
        assertEquals(PRINTER_ADDED.toInt(), 0)
        assertEquals(PRINTER_DELETED.toInt(), 1)
        assertEquals(PRINTER_STATE_CHANGED.toInt(), 2)
        assertEquals(PRINTER_INFO_CHANGED.toInt(), 3)
        logLine("Print_PrinterEvent passed")
    }

    @Test
    fun testEnum_Print_DuplexMode() {
        assertEquals(DUPLEX_MODE_ONE_SIDED.toInt(), 0)
        assertEquals(DUPLEX_MODE_TWO_SIDED_LONG_EDGE.toInt(), 1)
        assertEquals(DUPLEX_MODE_TWO_SIDED_SHORT_EDGE.toInt(), 2)
        logLine("Print_DuplexMode passed")
    }

    @Test
    fun testEnum_Print_ColorMode() {
        assertEquals(COLOR_MODE_MONOCHROME.toInt(), 0)
        assertEquals(COLOR_MODE_COLOR.toInt(), 1)
        assertEquals(COLOR_MODE_AUTO.toInt(), 2)
        logLine("Print_ColorMode passed")
    }

    @Test
    fun testEnum_Print_OrientationMode() {
        assertEquals(ORIENTATION_MODE_PORTRAIT.toInt(), 0)
        assertEquals(ORIENTATION_MODE_LANDSCAPE.toInt(), 1)
        assertEquals(ORIENTATION_MODE_REVERSE_LANDSCAPE.toInt(), 2)
        assertEquals(ORIENTATION_MODE_REVERSE_PORTRAIT.toInt(), 3)
        assertEquals(ORIENTATION_MODE_NONE.toInt(), 4)
        logLine("Print_OrientationMode passed")
    }

    @Test
    fun testEnum_Print_Quality() {
        assertEquals(PRINT_QUALITY_DRAFT.toInt(), 3)
        assertEquals(PRINT_QUALITY_NORMAL.toInt(), 4)
        assertEquals(PRINT_QUALITY_HIGH.toInt(), 5)
        logLine("Print_Quality passed")
    }

    @Test
    fun testEnum_Print_DocumentFormat() {
        assertEquals(Print_DocumentFormat.DOCUMENT_FORMAT_AUTO.value.toInt(), 0)
        assertEquals(Print_DocumentFormat.DOCUMENT_FORMAT_JPEG.value.toInt(), 1)
        assertEquals(Print_DocumentFormat.DOCUMENT_FORMAT_PDF.value.toInt(), 2)
        assertEquals(Print_DocumentFormat.DOCUMENT_FORMAT_POSTSCRIPT.value.toInt(), 3)
        assertEquals(Print_DocumentFormat.DOCUMENT_FORMAT_TEXT.value.toInt(), 4)
        logLine("Print_DocumentFormat passed")
    }

    @Test
    fun testEnum_Print_JobDocAdapterState() {
        assertEquals(PRINT_DOC_ADAPTER_PREVIEW_ABILITY_DESTROY.toInt(), 0)
        assertEquals(PRINT_DOC_ADAPTER_PRINT_TASK_SUCCEED.toInt(), 1)
        assertEquals(PRINT_DOC_ADAPTER_PRINT_TASK_FAIL.toInt(), 2)
        assertEquals(PRINT_DOC_ADAPTER_PRINT_TASK_CANCEL.toInt(), 3)
        assertEquals(PRINT_DOC_ADAPTER_PRINT_TASK_BLOCK.toInt(), 4)
        assertEquals(PRINT_DOC_ADAPTER_PREVIEW_ABILITY_DESTROY_FOR_CANCELED.toInt(), 5)
        assertEquals(PRINT_DOC_ADAPTER_PREVIEW_ABILITY_DESTROY_FOR_STARTED.toInt(), 6)
        logLine("Print_JobDocAdapterState passed")
    }

    @Test
    fun testOH_Print_Init() {
        val initRet = OH_Print_Init()
        logLine("OH_Print_Init ret=$initRet")
        assertNotNull(initRet)
    }

    @Test
    fun testOH_Print_StartPrinterDiscovery() {
        val startRet = OH_Print_StartPrinterDiscovery(null)
        logLine("OH_Print_StartPrinterDiscovery ret=$startRet")
        assertNotNull(startRet)
    }

    @Test
    fun testOH_Print_StopPrinterDiscovery() {
        val stopRet = OH_Print_StopPrinterDiscovery()
        logLine("OH_Print_StopPrinterDiscovery ret=$stopRet")
        assertNotNull(stopRet)
    }

    @Test
    fun testOH_Print_ConnectPrinter() {
        val connectRet = OH_Print_ConnectPrinter(null)
        logLine("OH_Print_ConnectPrinter ret=$connectRet")
        assertNotNull(connectRet)
    }

    @Test
    fun testOH_Print_StartPrintJob() {
        val startJobRet = OH_Print_StartPrintJob(null)
        logLine("OH_Print_StartPrintJob ret=$startJobRet")
        assertNotNull(startJobRet)
    }

    @Test
    fun testOH_Print_RegisterPrinterChangeListener() {
        val regRet = OH_Print_RegisterPrinterChangeListener(null)
        logLine("OH_Print_RegisterPrinterChangeListener ret=$regRet")
        assertNotNull(regRet)
    }

    @Test
    fun testOH_Print_UnregisterPrinterChangeListener() {
        OH_Print_UnregisterPrinterChangeListener()
        logLine("OH_Print_UnregisterPrinterChangeListener done")
    }

    @Test
    fun testOH_Print_QueryPrinterList() {
        memScoped {
            val list = alloc<Print_StringList>()
            list.count = 0u
            list.list = null
            val queryListRet = OH_Print_QueryPrinterList(list.ptr)
            logLine("OH_Print_QueryPrinterList ret=$queryListRet count=${list.count}")
            assertNotNull(queryListRet)
        }
    }

    @Test
    fun testOH_Print_ReleasePrinterList() {
        memScoped {
            val list = alloc<Print_StringList>()
            list.count = 0u
            list.list = null
            OH_Print_ReleasePrinterList(list.ptr)
            logLine("OH_Print_ReleasePrinterList done")
        }
    }

    @Test
    fun testOH_Print_QueryPrinterInfo() {
        memScoped {
            val infoPtr = alloc<CPointerVar<Print_PrinterInfo>>()
            val queryInfoRet = OH_Print_QueryPrinterInfo(null, infoPtr.ptr)
            logLine("OH_Print_QueryPrinterInfo ret=$queryInfoRet info=${infoPtr.value}")
            assertNotNull(queryInfoRet)
        }
    }

    @Test
    fun testOH_Print_ReleasePrinterInfo() {
        OH_Print_ReleasePrinterInfo(null)
        logLine("OH_Print_ReleasePrinterInfo done")
    }

    @Test
    fun testOH_Print_LaunchPrinterManager() {
        val launchRet = OH_Print_LaunchPrinterManager()
        logLine("OH_Print_LaunchPrinterManager ret=$launchRet")
        assertNotNull(launchRet)
    }

    @Test
    fun testOH_Print_QueryPrinterProperties() {
        memScoped {
            val keyList = alloc<Print_StringList>()
            keyList.count = 0u
            keyList.list = null
            val propList = alloc<Print_PropertyList>()
            propList.count = 0u
            propList.list = null
            val queryPropRet = OH_Print_QueryPrinterProperties(null, keyList.ptr, propList.ptr)
            logLine("OH_Print_QueryPrinterProperties ret=$queryPropRet")
            assertNotNull(queryPropRet)
        }
    }

    @Test
    fun testOH_Print_ReleasePrinterProperties() {
        memScoped {
            val propList = alloc<Print_PropertyList>()
            propList.count = 0u
            propList.list = null
            OH_Print_ReleasePrinterProperties(propList.ptr)
            logLine("OH_Print_ReleasePrinterProperties done")
        }
    }

    @Test
    fun testOH_Print_UpdatePrinterProperties() {
        memScoped {
            val updatePropList = alloc<Print_PropertyList>()
            updatePropList.count = 0u
            updatePropList.list = null
            val updateRet = OH_Print_UpdatePrinterProperties(null, updatePropList.ptr)
            logLine("OH_Print_UpdatePrinterProperties ret=$updateRet")
            assertNotNull(updateRet)
        }
    }

    @Test
    fun testOH_Print_RestorePrinterProperties() {
        memScoped {
            val keyList = alloc<Print_StringList>()
            keyList.count = 0u
            keyList.list = null
            val restoreRet = OH_Print_RestorePrinterProperties(null, keyList.ptr)
            logLine("OH_Print_RestorePrinterProperties ret=$restoreRet")
            assertNotNull(restoreRet)
        }
    }

    @Test
    fun testOH_Print_StartPrintByNative() {
        memScoped {
            val docCb = alloc<Print_PrintDocCallback>()
            docCb.startLayoutWriteCb = null
            docCb.jobStateChangedCb = null
            val startNativeRet = OH_Print_StartPrintByNative("job", docCb.readValue(), null)
            logLine("OH_Print_StartPrintByNative ret=$startNativeRet")
            assertNotNull(startNativeRet)
        }
    }

    @Test
    fun testOH_Print_Release() {
        val releaseRet = OH_Print_Release()
        logLine("OH_Print_Release ret=$releaseRet")
        assertNotNull(releaseRet)
    }
}
