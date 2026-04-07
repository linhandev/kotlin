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

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class BluetoothTest {

    private fun logLine(message: String) {
        println("[stdout] BluetoothTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- Bluetooth_SwitchState ---")
        val stateOff = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_OFF
        val stateTurningOn = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_TURNING_ON
        val stateOn = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_ON
        val stateTurningOff = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_TURNING_OFF
        val stateBleTurningOn = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_BLE_TURNING_ON
        val stateBleOn = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_BLE_ON
        val stateBleTurningOff = platform.ConnectivityKit.Bluetooth.BLUETOOTH_STATE_BLE_TURNING_OFF
        logLine("BLUETOOTH_STATE_OFF=$stateOff, BLUETOOTH_STATE_TURNING_ON=$stateTurningOn, BLUETOOTH_STATE_ON=$stateOn")
        logLine("BLUETOOTH_STATE_TURNING_OFF=$stateTurningOff, BLUETOOTH_STATE_BLE_TURNING_ON=$stateBleTurningOn, BLUETOOTH_STATE_BLE_ON=$stateBleOn, BLUETOOTH_STATE_BLE_TURNING_OFF=$stateBleTurningOff")
        assertNotEquals(stateOff, stateOn, "STATE_OFF != STATE_ON")
        assertNotEquals(stateOn, stateBleOn, "STATE_ON != STATE_BLE_ON")

        logLine("--- Bluetooth_ResultCode ---")
        val resultSuccess = platform.ConnectivityKit.Bluetooth.BLUETOOTH_SUCCESS
        val resultInvalidParam = platform.ConnectivityKit.Bluetooth.BLUETOOTH_INVALID_PARAM
        logLine("BLUETOOTH_SUCCESS=$resultSuccess, BLUETOOTH_INVALID_PARAM=$resultInvalidParam")
        assertNotEquals(resultSuccess, resultInvalidParam, "SUCCESS != INVALID_PARAM")
    }

    @Test
    fun testGetBluetoothSwitchState() {
        logLine("--- OH_Bluetooth_GetBluetoothSwitchState ---")
        val stateVar = nativeHeap.alloc<platform.ConnectivityKit.Bluetooth.Bluetooth_SwitchStateVar>()
        val result = platform.ConnectivityKit.Bluetooth.OH_Bluetooth_GetBluetoothSwitchState(stateVar.ptr)
        logLine("OH_Bluetooth_GetBluetoothSwitchState(ptr) result: $result, state: ${stateVar.value}")
        assertNotNull(result)
        nativeHeap.free(stateVar)
    }
}
