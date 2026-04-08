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
import platform.SensorServiceKit.Vibrator.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class VibratorTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Vibrator_ErrorCode() {
        assertEquals(PERMISSION_DENIED.toInt(), 201)
        assertEquals(PARAMETER_ERROR.toInt(), 401)
        assertEquals(UNSUPPORTED.toInt(), 801)
        assertEquals(DEVICE_OPERATION_FAILED.toInt(), 14600101)
        logLine("Vibrator_ErrorCode passed")
    }

    @Test
    fun testEnum_Vibrator_Usage() {
        assertEquals(VIBRATOR_USAGE_UNKNOWN.toInt(), 0)
        assertEquals(VIBRATOR_USAGE_ALARM.toInt(), 1)
        assertEquals(VIBRATOR_USAGE_RING.toInt(), 2)
        assertEquals(VIBRATOR_USAGE_NOTIFICATION.toInt(), 3)
        assertEquals(VIBRATOR_USAGE_COMMUNICATION.toInt(), 4)
        assertEquals(VIBRATOR_USAGE_TOUCH.toInt(), 5)
        assertEquals(VIBRATOR_USAGE_MEDIA.toInt(), 6)
        assertEquals(VIBRATOR_USAGE_PHYSICAL_FEEDBACK.toInt(), 7)
        assertEquals(VIBRATOR_USAGE_SIMULATED_REALITY.toInt(), 8)
        assertEquals(VIBRATOR_USAGE_MAX.toInt(), 9)
        logLine("Vibrator_Usage passed")
    }

    @Test
    fun testPlayVibration() { memScoped {
        val attr = alloc<Vibrator_Attribute>().apply {
            vibratorId = 0
            usage = VIBRATOR_USAGE_NOTIFICATION
        }
        val ret = OH_Vibrator_PlayVibration(100, attr.readValue())
        assertNotNull(ret)
        logLine("OH_Vibrator_PlayVibration=$ret")
    } }

    @Test
    fun testPlayVibrationCustom() { memScoped {
        val fileDesc = alloc<Vibrator_FileDescription>().apply {
            fd = -1
            offset = 0L
            length = 0L
        }
        val attr = alloc<Vibrator_Attribute>().apply {
            vibratorId = 0
            usage = VIBRATOR_USAGE_UNKNOWN
        }
        val ret = OH_Vibrator_PlayVibrationCustom(fileDesc.readValue(), attr.readValue())
        assertNotNull(ret)
        logLine("OH_Vibrator_PlayVibrationCustom=$ret")
    } }

    @Test
    fun testVibratorCancel() {
        val ret = OH_Vibrator_Cancel()
        assertNotNull(ret)
        logLine("OH_Vibrator_Cancel=$ret")
    }
}
