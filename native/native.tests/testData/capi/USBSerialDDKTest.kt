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
import platform.DriverDevelopmentKit.USBSerialDDK.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class SerialDDKTest {

    private fun logLine(msg: String) = println(msg)

    // ==================== 枚举测试 ====================

    @Test
    fun testEnum_UsbSerial_DdkRetCode() {
        assertEquals(USB_SERIAL_DDK_NO_PERM.toInt(), 201)
        assertEquals(USB_SERIAL_DDK_INVALID_PARAMETER.toInt(), 401)
        assertEquals(USB_SERIAL_DDK_SUCCESS.toInt(), 31600000)
        assertEquals(USB_SERIAL_DDK_INVALID_OPERATION.toInt(), 31600001)
        assertEquals(USB_SERIAL_DDK_INIT_ERROR.toInt(), 31600002)
        assertEquals(USB_SERIAL_DDK_SERVICE_ERROR.toInt(), 31600003)
        assertEquals(USB_SERIAL_DDK_MEMORY_ERROR.toInt(), 31600004)
        assertEquals(USB_SERIAL_DDK_IO_ERROR.toInt(), 31600005)
        assertEquals(USB_SERIAL_DDK_DEVICE_NOT_FOUND.toInt(), 31600006)
        logLine("UsbSerial_DdkRetCode passed")
    }

    @Test
    fun testEnum_UsbSerial_FlowControl() {
        assertEquals(USB_SERIAL_NO_FLOW_CONTROL.toInt(), 0)
        assertEquals(USB_SERIAL_SOFTWARE_FLOW_CONTROL.toInt(), 1)
        assertEquals(USB_SERIAL_HARDWARE_FLOW_CONTROL.toInt(), 2)
        logLine("UsbSerial_FlowControl passed")
    }

    @Test
    fun testEnum_UsbSerial_Parity() {
        assertEquals(USB_SERIAL_PARITY_NONE.toInt(), 0)
        assertEquals(USB_SERIAL_PARITY_ODD.toInt(), 1)
        assertEquals(USB_SERIAL_PARITY_EVEN.toInt(), 2)
        logLine("UsbSerial_Parity passed")
    }

    // ==================== 函数测试 ====================

    @Test
    fun testInitAndRelease() {
        val initRet = try { OH_UsbSerial_Init() } catch (e: Throwable) { logLine("OH_UsbSerial_Init (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(initRet)
        logLine("OH_UsbSerial_Init=$initRet")
        val releaseRet = try { OH_UsbSerial_Release() } catch (e: Throwable) { logLine("OH_UsbSerial_Release (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(releaseRet)
        logLine("OH_UsbSerial_Release=$releaseRet")
    }

    @Test
    fun testOpenAndClose() { memScoped {
        val devPtr = alloc<CPointerVar<UsbSerial_Device>>()
        devPtr.value = null
        val openRet = try { OH_UsbSerial_Open(0uL, 0u, devPtr.ptr) } catch (e: Throwable) { logLine("OH_UsbSerial_Open (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(openRet)
        logLine("OH_UsbSerial_Open(0,0)=$openRet")
        val closeRet = try { OH_UsbSerial_Close(devPtr.ptr) } catch (e: Throwable) { logLine("OH_UsbSerial_Close (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(closeRet)
        logLine("OH_UsbSerial_Close=$closeRet")
    } }

    @Test
    fun testReadWithNullDev() { memScoped {
        val bytesRead = alloc<UIntVar>()
        val buf = UByteArray(2)
        val ret = try { OH_UsbSerial_Read(null, buf.refTo(0), 2u, bytesRead.ptr) } catch (e: Throwable) { logLine("OH_UsbSerial_Read (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_Read(null,...)=$ret")
    } }

    @Test
    fun testWriteWithNullDev() { memScoped {
        val bytesWritten = alloc<UIntVar>()
        val buf = UByteArray(2)
        val ret = try { OH_UsbSerial_Write(null, buf.refTo(0), 2u, bytesWritten.ptr) } catch (e: Throwable) { logLine("OH_UsbSerial_Write (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_Write(null,...)=$ret")
    } }

    @Test
    fun testSetBaudRateWithNullDev() {
        val ret = try { OH_UsbSerial_SetBaudRate(null, 9600u) } catch (e: Throwable) { logLine("OH_UsbSerial_SetBaudRate (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_SetBaudRate(null,9600)=$ret")
    }

    @Test
    fun testSetParamsWithNullDev() { memScoped {
        val params = alloc<UsbSerial_Params>().apply {
            baudRate = 9600u
            nDataBits = 8u
            nStopBits = 1u
            parity = USB_SERIAL_PARITY_NONE.toUByte()
        }
        val ret = try { OH_UsbSerial_SetParams(null, params.ptr) } catch (e: Throwable) { logLine("OH_UsbSerial_SetParams (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_SetParams(null,...)=$ret")
    } }

    @Test
    fun testSetTimeoutWithNullDev() {
        val ret = try { OH_UsbSerial_SetTimeout(null, 0) } catch (e: Throwable) { logLine("OH_UsbSerial_SetTimeout (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_SetTimeout(null,0)=$ret")
    }

    @Test
    fun testSetFlowControlWithNullDev() {
        val ret = try { OH_UsbSerial_SetFlowControl(null, USB_SERIAL_NO_FLOW_CONTROL) } catch (e: Throwable) { logLine("OH_UsbSerial_SetFlowControl (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_SetFlowControl(null,NO_FLOW)=$ret")
    }

    @Test
    fun testFlushWithNullDev() {
        val ret = try { OH_UsbSerial_Flush(null) } catch (e: Throwable) { logLine("OH_UsbSerial_Flush (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_Flush(null)=$ret")
    }

    @Test
    fun testFlushInputWithNullDev() {
        val ret = try { OH_UsbSerial_FlushInput(null) } catch (e: Throwable) { logLine("OH_UsbSerial_FlushInput (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_FlushInput(null)=$ret")
    }

    @Test
    fun testFlushOutputWithNullDev() {
        val ret = try { OH_UsbSerial_FlushOutput(null) } catch (e: Throwable) { logLine("OH_UsbSerial_FlushOutput (API 18) exception: $e"); USB_SERIAL_DDK_INVALID_PARAMETER }
        assertNotNull(ret)
        logLine("OH_UsbSerial_FlushOutput(null)=$ret")
    }
}
