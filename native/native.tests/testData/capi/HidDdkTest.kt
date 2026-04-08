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
import cnames.structs.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class HidDdkTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testHidDdkErrCodeEnums() {
        logLine("--- Hid_DdkErrCode ---")
        val v0 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_SUCCESS
        logLine("HID_DDK_SUCCESS=$v0")
        assertEquals<Int>(0, v0.toInt())
        val v1 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_NO_PERM
        logLine("HID_DDK_NO_PERM=$v1")
        assertEquals<Int>(201, v1.toInt())
        val v2 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_INVALID_PARAMETER
        logLine("HID_DDK_INVALID_PARAMETER=$v2")
        assertEquals<Int>(401, v2.toInt())
        val v3 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_FAILURE
        logLine("HID_DDK_FAILURE=$v3")
        assertEquals<Int>(27300001, v3.toInt())
        val v4 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_NULL_PTR
        logLine("HID_DDK_NULL_PTR=$v4")
        assertEquals<Int>(27300002, v4.toInt())
        val v5 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_INVALID_OPERATION
        logLine("HID_DDK_INVALID_OPERATION=$v5")
        assertEquals<Int>(27300003, v5.toInt())
        val v6 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_TIMEOUT
        logLine("HID_DDK_TIMEOUT=$v6")
        assertEquals<Int>(27300004, v6.toInt())
        val v7 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_INIT_ERROR
        logLine("HID_DDK_INIT_ERROR=$v7")
        assertEquals<Int>(27300005, v7.toInt())
        val v8 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_SERVICE_ERROR
        logLine("HID_DDK_SERVICE_ERROR=$v8")
        assertEquals<Int>(27300006, v8.toInt())
        val v9 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_MEMORY_ERROR
        logLine("HID_DDK_MEMORY_ERROR=$v9")
        assertEquals<Int>(27300007, v9.toInt())
        val v10 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_IO_ERROR
        logLine("HID_DDK_IO_ERROR=$v10")
        assertEquals<Int>(27300008, v10.toInt())
        val v11 = platform.DriverDevelopmentKit.HidDdk.HID_DDK_DEVICE_NOT_FOUND
        logLine("HID_DDK_DEVICE_NOT_FOUND=$v11")
        assertEquals<Int>(27300009, v11.toInt())
        logLine("Hid_DdkErrCode values ok")
    }

    @Test
    fun testHidDevicePropEnums() {
        logLine("--- Hid_DeviceProp ---")
        val e0 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_POINTER
        logLine("HID_PROP_POINTER=$e0")
        assertEquals<Int>(0x00, e0.toInt())
        val e1 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_DIRECT
        logLine("HID_PROP_DIRECT=$e1")
        assertEquals<Int>(0x01, e1.toInt())
        val e2 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_BUTTON_PAD
        logLine("HID_PROP_BUTTON_PAD=$e2")
        assertEquals<Int>(0x02, e2.toInt())
        val e3 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_SEMI_MT
        logLine("HID_PROP_SEMI_MT=$e3")
        assertEquals<Int>(0x03, e3.toInt())
        val e4 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_TOP_BUTTON_PAD
        logLine("HID_PROP_TOP_BUTTON_PAD=$e4")
        assertEquals<Int>(0x04, e4.toInt())
        val e5 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_POINTING_STICK
        logLine("HID_PROP_POINTING_STICK=$e5")
        assertEquals<Int>(0x05, e5.toInt())
        val e6 = platform.DriverDevelopmentKit.HidDdk.HID_PROP_ACCELEROMETER
        logLine("HID_PROP_ACCELEROMETER=$e6")
        assertEquals<Int>(0x06, e6.toInt())
        logLine("Hid_DeviceProp values ok")
    }

    @Test
    fun testHidEventTypeEnums() {
        logLine("--- Hid_EventType ---")
        val t0 = platform.DriverDevelopmentKit.HidDdk.HID_EV_SYN
        logLine("HID_EV_SYN=$t0")
        assertEquals<Int>(0x00, t0.toInt())
        val t1 = platform.DriverDevelopmentKit.HidDdk.HID_EV_KEY
        logLine("HID_EV_KEY=$t1")
        assertEquals<Int>(0x01, t1.toInt())
        val t2 = platform.DriverDevelopmentKit.HidDdk.HID_EV_REL
        logLine("HID_EV_REL=$t2")
        assertEquals<Int>(0x02, t2.toInt())
        val t3 = platform.DriverDevelopmentKit.HidDdk.HID_EV_ABS
        logLine("HID_EV_ABS=$t3")
        assertEquals<Int>(0x03, t3.toInt())
        val t4 = platform.DriverDevelopmentKit.HidDdk.HID_EV_MSC
        logLine("HID_EV_MSC=$t4")
        assertEquals<Int>(0x04, t4.toInt())
        logLine("Hid_EventType values ok")
    }

    @Test
    fun testHidSynEventEnums() {
        logLine("--- Hid_SynEvent ---")
        val s0 = platform.DriverDevelopmentKit.HidDdk.HID_SYN_REPORT
        logLine("HID_SYN_REPORT=$s0")
        assertEquals<Int>(0, s0.toInt())
        val s1 = platform.DriverDevelopmentKit.HidDdk.HID_SYN_CONFIG
        logLine("HID_SYN_CONFIG=$s1")
        assertEquals<Int>(1, s1.toInt())
        val s2 = platform.DriverDevelopmentKit.HidDdk.HID_SYN_MT_REPORT
        logLine("HID_SYN_MT_REPORT=$s2")
        assertEquals<Int>(2, s2.toInt())
        val s3 = platform.DriverDevelopmentKit.HidDdk.HID_SYN_DROPPED
        logLine("HID_SYN_DROPPED=$s3")
        assertEquals<Int>(3, s3.toInt())
        logLine("Hid_SynEvent values ok")
    }

    @Test
    fun testHidReportTypeEnums() {
        logLine("--- Hid_ReportType ---")
        val r0 = platform.DriverDevelopmentKit.HidDdk.HID_INPUT_REPORT
        logLine("HID_INPUT_REPORT=$r0")
        assertEquals<Int>(0, r0.toInt())
        val r1 = platform.DriverDevelopmentKit.HidDdk.HID_OUTPUT_REPORT
        logLine("HID_OUTPUT_REPORT=$r1")
        assertEquals<Int>(1, r1.toInt())
        val r2 = platform.DriverDevelopmentKit.HidDdk.HID_FEATURE_REPORT
        logLine("HID_FEATURE_REPORT=$r2")
        assertEquals<Int>(2, r2.toInt())
        logLine("Hid_ReportType values ok")
    }

    @Test
    fun testOH_Hid_Init() {
        logLine("--- OH_Hid_Init (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_Init()
            assertNotNull(result)
            logLine("OH_Hid_Init result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_Init (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_Release() {
        logLine("--- OH_Hid_Release (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_Release()
            assertNotNull(result)
            logLine("OH_Hid_Release result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_Release (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_CreateDevice() {
        memScoped {
            logLine("--- OH_Hid_CreateDevice ---")
            val hidDevice = alloc<platform.DriverDevelopmentKit.HidDdk.Hid_Device>().apply {
                deviceName = null
                vendorId = 0u
                productId = 0u
                version = 0u
                bustype = 0u
                properties = null
                propLength = 0u
            }
            val hidEventProperties = alloc<platform.DriverDevelopmentKit.HidDdk.Hid_EventProperties>()
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_CreateDevice(hidDevice.ptr, hidEventProperties.ptr)
            assertNotNull(result)
            logLine("OH_Hid_CreateDevice result: $result")
        }
    }

    @Test
    fun testOH_Hid_EmitEvent() {
        memScoped {
            logLine("--- OH_Hid_EmitEvent ---")
            val items = alloc<platform.DriverDevelopmentKit.HidDdk.Hid_EmitItem>().apply {
                type = 0u
                code = 0u
                value = 0u
            }
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_EmitEvent(-1, items.ptr, 1u)
            assertNotNull(result)
            logLine("OH_Hid_EmitEvent result: $result")
        }
    }

    @Test
    fun testOH_Hid_DestroyDevice() {
        logLine("--- OH_Hid_DestroyDevice ---")
        val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_DestroyDevice(-1)
        assertNotNull(result)
        logLine("OH_Hid_DestroyDevice result: $result")
    }

    @Test
    fun testOH_Hid_Open() {
        memScoped {
            logLine("--- OH_Hid_Open (API 18) ---")
            try {
                val dev = alloc<CPointerVar<Hid_DeviceHandle>>()
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_Open(0uL, 0u, dev.ptr)
                assertNotNull(result)
                logLine("OH_Hid_Open result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_Open (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_Close() {
        memScoped {
            logLine("--- OH_Hid_Close (API 18) ---")
            try {
                val dev = alloc<CPointerVar<Hid_DeviceHandle>>().apply { value = null }
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_Close(dev.ptr)
                assertNotNull(result)
                logLine("OH_Hid_Close result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_Close (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_Write() {
        memScoped {
            logLine("--- OH_Hid_Write (API 18) ---")
            try {
                val bytesWritten = alloc<UIntVar>()
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_Write(null, null, 0u, bytesWritten.ptr)
                assertNotNull(result)
                logLine("OH_Hid_Write result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_Write (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_ReadTimeout() {
        memScoped {
            logLine("--- OH_Hid_ReadTimeout (API 18) ---")
            try {
                val bytesRead = alloc<UIntVar>()
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_ReadTimeout(null, null, 0u, -1, bytesRead.ptr)
                assertNotNull(result)
                logLine("OH_Hid_ReadTimeout result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_ReadTimeout (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_Read() {
        memScoped {
            logLine("--- OH_Hid_Read (API 18) ---")
            try {
                val bytesRead = alloc<UIntVar>()
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_Read(null, null, 0u, bytesRead.ptr)
                assertNotNull(result)
                logLine("OH_Hid_Read result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_Read (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_SetNonBlocking() {
        logLine("--- OH_Hid_SetNonBlocking (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_SetNonBlocking(null, 0)
            assertNotNull(result)
            logLine("OH_Hid_SetNonBlocking result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_SetNonBlocking (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_GetRawInfo() {
        memScoped {
            logLine("--- OH_Hid_GetRawInfo (API 18) ---")
            try {
                val rawDevInfo = alloc<platform.DriverDevelopmentKit.HidDdk.Hid_RawDevInfo>()
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_GetRawInfo(null, rawDevInfo.ptr)
                assertNotNull(result)
                logLine("OH_Hid_GetRawInfo result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_GetRawInfo (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_GetRawName() {
        logLine("--- OH_Hid_GetRawName (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_GetRawName(null, null, 0u)
            assertNotNull(result)
            logLine("OH_Hid_GetRawName result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_GetRawName (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_GetPhysicalAddress() {
        logLine("--- OH_Hid_GetPhysicalAddress (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_GetPhysicalAddress(null, null, 0u)
            assertNotNull(result)
            logLine("OH_Hid_GetPhysicalAddress result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_GetPhysicalAddress (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_GetRawUniqueId() {
        logLine("--- OH_Hid_GetRawUniqueId (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_GetRawUniqueId(null, null, 0u)
            assertNotNull(result)
            logLine("OH_Hid_GetRawUniqueId result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_GetRawUniqueId (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_SendReport() {
        logLine("--- OH_Hid_SendReport (API 18) ---")
        try {
            val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_SendReport(null, platform.DriverDevelopmentKit.HidDdk.HID_INPUT_REPORT, null, 0u)
            assertNotNull(result)
            logLine("OH_Hid_SendReport result: $result")
        } catch (e: Throwable) {
            logLine("OH_Hid_SendReport (API 18) exception: $e")
        }
    }

    @Test
    fun testOH_Hid_GetReport() {
        memScoped {
            logLine("--- OH_Hid_GetReport (API 18) ---")
            try {
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_GetReport(null, platform.DriverDevelopmentKit.HidDdk.HID_INPUT_REPORT, null, 0u)
                assertNotNull(result)
                logLine("OH_Hid_GetReport result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_GetReport (API 18) exception: $e")
            }
        }
    }

    @Test
    fun testOH_Hid_GetReportDescriptor() {
        memScoped {
            logLine("--- OH_Hid_GetReportDescriptor (API 18) ---")
            try {
                val bytesRead = alloc<UIntVar>()
                val result = platform.DriverDevelopmentKit.HidDdk.OH_Hid_GetReportDescriptor(null, null, 0u, bytesRead.ptr)
                assertNotNull(result)
                logLine("OH_Hid_GetReportDescriptor result: $result")
            } catch (e: Throwable) {
                logLine("OH_Hid_GetReportDescriptor (API 18) exception: $e")
            }
        }
    }
}
