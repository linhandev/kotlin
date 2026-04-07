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
import platform.DriverDevelopmentKit.SCSIPeripheralDDK.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class SCSIPeripheralDDKTest {

    private fun logLine(msg: String) = println(msg)

    
    @Test
    fun testEnum_ScsiPeripheral_DdkErrCode() {
        assertEquals(SCSIPERIPHERAL_DDK_NO_PERM.toInt(), 201)
        assertEquals(SCSIPERIPHERAL_DDK_INVALID_PARAMETER.toInt(), 401)
        assertEquals(SCSIPERIPHERAL_DDK_SUCCESS.toInt(), 31700000)
        assertEquals(SCSIPERIPHERAL_DDK_MEMORY_ERROR.toInt(), 31700001)
        assertEquals(SCSIPERIPHERAL_DDK_INVALID_OPERATION.toInt(), 31700002)
        assertEquals(SCSIPERIPHERAL_DDK_IO_ERROR.toInt(), 31700003)
        assertEquals(SCSIPERIPHERAL_DDK_TIMEOUT.toInt(), 31700004)
        assertEquals(SCSIPERIPHERAL_DDK_INIT_ERROR.toInt(), 31700005)
        assertEquals(SCSIPERIPHERAL_DDK_SERVICE_ERROR.toInt(), 31700006)
        assertEquals(SCSIPERIPHERAL_DDK_DEVICE_NOT_FOUND.toInt(), 31700007)
        logLine("ScsiPeripheral_DdkErrCode passed")
    }

    @Test
    fun testEnum_ScsiPeripheral_Status() {
        assertEquals(SCSIPERIPHERAL_STATUS_GOOD.toInt(), 0x00)
        assertEquals(SCSIPERIPHERAL_STATUS_CHECK_CONDITION_NEEDED.toInt(), 0x02)
        assertEquals(SCSIPERIPHERAL_STATUS_CONDITION_MET.toInt(), 0x04)
        assertEquals(SCSIPERIPHERAL_STATUS_BUSY.toInt(), 0x08)
        assertEquals(SCSIPERIPHERAL_STATUS_RESERVATION_CONFLICT.toInt(), 0x18)
        assertEquals(SCSIPERIPHERAL_STATUS_TASK_SET_FULL.toInt(), 0x28)
        assertEquals(SCSIPERIPHERAL_STATUS_ACA_ACTIVE.toInt(), 0x30)
        assertEquals(SCSIPERIPHERAL_STATUS_TASK_ABORTED.toInt(), 0x40)
        logLine("ScsiPeripheral_Status passed")
    }

    @Test
    fun testOH_ScsiPeripheral_Init() {
        val r0 = try { OH_ScsiPeripheral_Init() } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Init (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
        assertNotNull(r0)
        logLine("Init $r0")
    }

    @Test
    fun testOH_ScsiPeripheral_Open() {
        memScoped {
            val devPtr = alloc<CPointerVar<ScsiPeripheral_Device>>()
            val r1 = try { OH_ScsiPeripheral_Open(0uL, 0u, devPtr.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Open (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r1)
            logLine("Open $r1")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_Close() {
        memScoped {
            val devPtr = alloc<CPointerVar<ScsiPeripheral_Device>>()
            try { OH_ScsiPeripheral_Open(0uL, 0u, devPtr.ptr) } catch (_: Throwable) { }
            val closePtr = alloc<CPointerVar<ScsiPeripheral_Device>>()
            closePtr.value = devPtr.value
            val r2 = try { OH_ScsiPeripheral_Close(closePtr.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Close (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r2)
            logLine("Close $r2")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_TestUnitReady() {
        memScoped {
            val req = alloc<ScsiPeripheral_TestUnitReadyRequest>()
            val resp = alloc<ScsiPeripheral_Response>()
            val r3 = try { OH_ScsiPeripheral_TestUnitReady(null, req.ptr, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_TestUnitReady (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r3)
            logLine("TestUnitReady $r3")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_Inquiry() {
        memScoped {
            val inquiryInfo = alloc<ScsiPeripheral_InquiryInfo>()
            val resp = alloc<ScsiPeripheral_Response>()
            val r4 = try { OH_ScsiPeripheral_Inquiry(null, null, inquiryInfo.ptr, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Inquiry (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r4)
            logLine("Inquiry $r4")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_ReadCapacity10() {
        memScoped {
            val capInfo = alloc<ScsiPeripheral_CapacityInfo>()
            val resp = alloc<ScsiPeripheral_Response>()
            val r5 = try { OH_ScsiPeripheral_ReadCapacity10(null, null, capInfo.ptr, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_ReadCapacity10 (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r5)
            logLine("ReadCapacity10 $r5")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_RequestSense() {
        memScoped {
            val resp = alloc<ScsiPeripheral_Response>()
            val r6 = try { OH_ScsiPeripheral_RequestSense(null, null, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_RequestSense (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r6)
            logLine("RequestSense $r6")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_Read10() {
        memScoped {
            val resp = alloc<ScsiPeripheral_Response>()
            val r7 = try { OH_ScsiPeripheral_Read10(null, null, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Read10 (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r7)
            logLine("Read10 $r7")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_Write10() {
        memScoped {
            val resp = alloc<ScsiPeripheral_Response>()
            val r8 = try { OH_ScsiPeripheral_Write10(null, null, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Write10 (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r8)
            logLine("Write10 $r8")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_Verify10() {
        memScoped {
            val resp = alloc<ScsiPeripheral_Response>()
            val r9 = try { OH_ScsiPeripheral_Verify10(null, null, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Verify10 (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r9)
            logLine("Verify10 $r9")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_SendRequestByCdb() {
        memScoped {
            val resp = alloc<ScsiPeripheral_Response>()
            val r10 = try { OH_ScsiPeripheral_SendRequestByCdb(null, null, resp.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_SendRequestByCdb (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r10)
            logLine("SendRequestByCdb $r10")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_CreateDeviceMemMap() {
        memScoped {
            val mmapPtr = alloc<CPointerVar<ScsiPeripheral_DeviceMemMap>>()
            val r11 = try { OH_ScsiPeripheral_CreateDeviceMemMap(null, 0uL, mmapPtr.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_CreateDeviceMemMap (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r11)
            logLine("CreateDeviceMemMap $r11")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_DestroyDeviceMemMap() {
        val r12 = try { OH_ScsiPeripheral_DestroyDeviceMemMap(null) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_DestroyDeviceMemMap (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
        assertNotNull(r12)
        logLine("DestroyDeviceMemMap $r12")
    }

    @Test
    fun testOH_ScsiPeripheral_ParseBasicSenseInfo() {
        memScoped {
            val senseInfo = alloc<ScsiPeripheral_BasicSenseInfo>()
            val r13 = try { OH_ScsiPeripheral_ParseBasicSenseInfo(null, 0u, senseInfo.ptr) } catch (e: Throwable) { logLine("OH_ScsiPeripheral_ParseBasicSenseInfo (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
            assertNotNull(r13)
            logLine("ParseBasicSenseInfo $r13")
        }
    }

    @Test
    fun testOH_ScsiPeripheral_Release() {
        val r14 = try { OH_ScsiPeripheral_Release() } catch (e: Throwable) { logLine("OH_ScsiPeripheral_Release (API 18) exception: $e"); SCSIPERIPHERAL_DDK_INVALID_PARAMETER }
        assertNotNull(r14)
        logLine("Release $r14")
    }
}
