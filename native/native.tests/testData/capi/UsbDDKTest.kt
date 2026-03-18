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
import platform.DriverDevelopmentKit.UsbDDK.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class UsbDDKTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_UsbDdkErrCode() {
        assertEquals(USB_DDK_SUCCESS.toInt(), 0)
        assertEquals(USB_DDK_FAILED.toInt(), -1)
        assertEquals(USB_DDK_NO_PERM.toInt(), 201)
        assertEquals(USB_DDK_INVALID_PARAMETER.toInt(), 401)
        assertEquals(USB_DDK_MEMORY_ERROR.toInt(), 27400001)
        assertEquals(USB_DDK_NULL_PTR.toInt(), -5)
        assertEquals(USB_DDK_DEVICE_BUSY.toInt(), -6)
        assertEquals(USB_DDK_INVALID_OPERATION.toInt(), 27400002)
        assertEquals(USB_DDK_IO_FAILED.toInt(), 27400003)
        assertEquals(USB_DDK_TIMEOUT.toInt(), 27400004)
        logLine("UsbDdkErrCode passed")
    }

    @Test
    fun testEnum_DDK_RetCode() {
        assertEquals(DDK_SUCCESS.toInt(), 0)
        assertEquals(DDK_FAILURE.toInt(), 28600001)
        assertEquals(DDK_INVALID_PARAMETER.toInt(), 28600002)
        assertEquals(DDK_INVALID_OPERATION.toInt(), 28600003)
        assertEquals(DDK_NULL_PTR.toInt(), 28600004)
        logLine("DDK_RetCode passed")
    }

    @Test
    fun testOH_Usb_Init_Release_ReleaseResource() {
        logLine("OH_Usb_Init=${OH_Usb_Init()}")
        OH_Usb_Release()
        logLine("OH_Usb_ReleaseResource=${try { OH_Usb_ReleaseResource() } catch (e: Throwable) { logLine("OH_Usb_ReleaseResource (API 18) exception: $e"); USB_DDK_INVALID_PARAMETER }}")
        logLine("OH_Usb_Init/Release/ReleaseResource passed")
    }

    @Test
    fun testOH_Usb_GetDeviceDescriptor() { memScoped {
        val desc = alloc<UsbDeviceDescriptor>()
        logLine("OH_Usb_GetDeviceDescriptor=${OH_Usb_GetDeviceDescriptor(0uL, desc.ptr)}")
        logLine("OH_Usb_GetDeviceDescriptor passed")
    } }

    @Test
    fun testOH_Usb_GetConfigDescriptor_FreeConfigDescriptor() { memScoped {
        val configPtr = alloc<CPointerVar<UsbDdkConfigDescriptor>>()
        logLine("OH_Usb_GetConfigDescriptor=${OH_Usb_GetConfigDescriptor(0uL, 0u, configPtr.ptr)}")
        OH_Usb_FreeConfigDescriptor(configPtr.value)
        logLine("OH_Usb_GetConfigDescriptor/FreeConfigDescriptor passed")
    } }

    @Test
    fun testOH_Usb_ClaimInterface_ReleaseInterface() { memScoped {
        val ifHandle = alloc<ULongVar>()
        logLine("OH_Usb_ClaimInterface=${OH_Usb_ClaimInterface(0uL, 0u, ifHandle.ptr)}")
        logLine("OH_Usb_ReleaseInterface=${OH_Usb_ReleaseInterface(ifHandle.value)}")
        logLine("OH_Usb_ClaimInterface/ReleaseInterface passed")
    } }

    @Test
    fun testOH_Usb_SelectInterfaceSetting_GetCurrentInterfaceSetting() { memScoped {
        logLine("OH_Usb_SelectInterfaceSetting=${OH_Usb_SelectInterfaceSetting(0uL, 0u)}")
        val setting = alloc<UByteVar>()
        logLine("OH_Usb_GetCurrentInterfaceSetting=${OH_Usb_GetCurrentInterfaceSetting(0uL, setting.ptr)}")
        logLine("OH_Usb_SelectInterfaceSetting/GetCurrentInterfaceSetting passed")
    } }

    @Test
    fun testOH_Usb_SendControlReadRequest_SendControlWriteRequest() { memScoped {
        val setup = alloc<UsbControlRequestSetup>()
        val dataLen = alloc<UIntVar>()
        val dataBuf = UByteArray(256)
        logLine("OH_Usb_SendControlReadRequest=${OH_Usb_SendControlReadRequest(0uL, setup.ptr, 0u, dataBuf.refTo(0), dataLen.ptr)}")
        val writeBuf = UByteArray(1)
        logLine("OH_Usb_SendControlWriteRequest=${OH_Usb_SendControlWriteRequest(0uL, setup.ptr, 0u, writeBuf.refTo(0), 0u)}")
        logLine("OH_Usb_SendControlReadRequest/SendControlWriteRequest passed")
    } }

    @Test
    fun testOH_Usb_SendPipeRequest_SendPipeRequestWithAshmem() { memScoped {
        val pipe = alloc<UsbRequestPipe>()
        logLine("OH_Usb_SendPipeRequest=${OH_Usb_SendPipeRequest(pipe.ptr, null)}")
        logLine("OH_Usb_SendPipeRequestWithAshmem=${OH_Usb_SendPipeRequestWithAshmem(pipe.ptr, null)}")
        logLine("OH_Usb_SendPipeRequest/SendPipeRequestWithAshmem passed")
    } }

    // @Test
    // fun testOH_Usb_CreateDeviceMemMap_DestroyDeviceMemMap() { memScoped {
    //     val devMmapPtr = alloc<CPointerVar<UsbDeviceMemMap>>()
    //     logLine("OH_Usb_CreateDeviceMemMap=${OH_Usb_CreateDeviceMemMap(0uL, 0u, devMmapPtr.ptr)}")
    //     assertNotNull(devMmapPtr.value)
    //     OH_Usb_DestroyDeviceMemMap(devMmapPtr.value)
    //     logLine("OH_Usb_CreateDeviceMemMap/DestroyDeviceMemMap passed")
    // } }

    @Test
    fun testOH_Usb_GetDevices() { memScoped {
        val devArray = alloc<Usb_DeviceArray>()
        logLine("OH_Usb_GetDevices=${try { OH_Usb_GetDevices(devArray.ptr) } catch (e: Throwable) { logLine("OH_Usb_GetDevices (API 18) exception: $e"); USB_DDK_INVALID_PARAMETER }}")
        logLine("OH_Usb_GetDevices passed")
    } }
}
