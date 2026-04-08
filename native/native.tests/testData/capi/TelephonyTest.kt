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
import platform.TelephonyKit.Telephony.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class TelephonyTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Telephony_RadioResult() {
        assertEquals<Int>(0, TEL_RADIO_SUCCESS.toInt())
        assertEquals<Int>(201, TEL_RADIO_PERMISSION_DENIED.toInt())
        assertEquals<Int>(401, TEL_RADIO_ERR_INVALID_PARAM.toInt())
        assertEquals<Int>(8300001, TEL_RADIO_ERR_MARSHALLING_FAILED.toInt())
        assertEquals<Int>(8300002, TEL_RADIO_ERR_SERVICE_CONNECTION_FAILED.toInt())
        assertEquals<Int>(8300003, TEL_RADIO_ERR_OPERATION_FAILED.toInt())
        logLine("Telephony_RadioResult passed")
    }

    @Test
    fun testEnum_Telephony_RegState() {
        assertEquals<Int>(0, TEL_REG_STATE_NO_SERVICE.toInt())
        assertEquals<Int>(1, TEL_REG_STATE_IN_SERVICE.toInt())
        assertEquals<Int>(2, TEL_REG_STATE_EMERGENCY_CALL_ONLY.toInt())
        assertEquals<Int>(3, TEL_REG_STATE_POWER_OFF.toInt())
        logLine("Telephony_RegState passed")
    }

    @Test
    fun testEnum_Telephony_RadioTechnology() {
        assertEquals<Int>(0, TEL_RADIO_TECHNOLOGY_UNKNOWN.toInt())
        assertEquals<Int>(1, TEL_RADIO_TECHNOLOGY_GSM.toInt())
        assertEquals<Int>(2, TEL_RADIO_TECHNOLOGY_1XRTT.toInt())
        assertEquals<Int>(3, TEL_RADIO_TECHNOLOGY_WCDMA.toInt())
        assertEquals<Int>(4, TEL_RADIO_TECHNOLOGY_HSPA.toInt())
        assertEquals<Int>(5, TEL_RADIO_TECHNOLOGY_HSPAP.toInt())
        assertEquals<Int>(6, TEL_RADIO_TECHNOLOGY_TD_SCDMA.toInt())
        assertEquals<Int>(7, TEL_RADIO_TECHNOLOGY_EVDO.toInt())
        assertEquals<Int>(8, TEL_RADIO_TECHNOLOGY_EHRPD.toInt())
        assertEquals<Int>(9, TEL_RADIO_TECHNOLOGY_LTE.toInt())
        assertEquals<Int>(10, TEL_RADIO_TECHNOLOGY_LTE_CA.toInt())
        assertEquals<Int>(11, TEL_RADIO_TECHNOLOGY_IWLAN.toInt())
        assertEquals<Int>(12, TEL_RADIO_TECHNOLOGY_NR.toInt())
        logLine("Telephony_RadioTechnology passed")
    }

    @Test
    fun testEnum_Telephony_NsaState() {
        assertEquals<Int>(1, TEL_NSA_STATE_NOT_SUPPORTED.toInt())
        assertEquals<Int>(2, TEL_NSA_STATE_NO_DETECTED.toInt())
        assertEquals<Int>(3, TEL_NSA_STATE_CONNECTED_DETECTED.toInt())
        assertEquals<Int>(4, TEL_NSA_STATE_IDLE_DETECTED.toInt())
        assertEquals<Int>(5, TEL_NSA_STATE_DUAL_CONNECTED.toInt())
        assertEquals<Int>(6, TEL_NSA_STATE_SA_ATTACHED.toInt())
        logLine("Telephony_NsaState passed")
    }

    @Test
    fun testOH_Telephony_GetNetworkState() {
        memScoped {
            val state = alloc<Telephony_NetworkState>()
            val ret = OH_Telephony_GetNetworkState(state.ptr)
            assertNotNull(ret)
            logLine("OH_Telephony_GetNetworkState=$ret")
        }
    }

    @Test
    fun testOH_Telephony_GetDefaultCellularDataSlotId() {
        val slotId = OH_Telephony_GetDefaultCellularDataSlotId()
        assertNotNull(slotId)
        logLine("OH_Telephony_GetDefaultCellularDataSlotId=$slotId")
    }

    @Test
    fun testOH_Telephony_GetNetworkStateForSlot() {
        memScoped {
            val state = alloc<Telephony_NetworkState>()
            val ret = OH_Telephony_GetNetworkStateForSlot(0, state.ptr)
            assertNotNull(ret)
            logLine("OH_Telephony_GetNetworkStateForSlot=$ret")
        }
    }
}
