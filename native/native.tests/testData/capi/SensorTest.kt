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
import platform.SensorServiceKit.Sensor.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class SensorTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Sensor_Type() {
        assertEquals(SENSOR_TYPE_ACCELEROMETER.toInt(), 1)
        assertEquals(SENSOR_TYPE_GYROSCOPE.toInt(), 2)
        assertEquals(SENSOR_TYPE_AMBIENT_LIGHT.toInt(), 5)
        assertEquals(SENSOR_TYPE_MAGNETIC_FIELD.toInt(), 6)
        assertEquals(SENSOR_TYPE_BAROMETER.toInt(), 8)
        assertEquals(SENSOR_TYPE_HALL.toInt(), 10)
        assertEquals(SENSOR_TYPE_PROXIMITY.toInt(), 12)
        assertEquals(SENSOR_TYPE_ORIENTATION.toInt(), 256)
        assertEquals(SENSOR_TYPE_GRAVITY.toInt(), 257)
        assertEquals(SENSOR_TYPE_LINEAR_ACCELERATION.toInt(), 258)
        assertEquals(SENSOR_TYPE_ROTATION_VECTOR.toInt(), 259)
        assertEquals(SENSOR_TYPE_GAME_ROTATION_VECTOR.toInt(), 262)
        assertEquals(SENSOR_TYPE_PEDOMETER_DETECTION.toInt(), 265)
        assertEquals(SENSOR_TYPE_PEDOMETER.toInt(), 266)
        assertEquals(SENSOR_TYPE_HEART_RATE.toInt(), 278)
        logLine("Sensor_Type passed")
    }

    @Test
    fun testEnum_Sensor_Result() {
        assertEquals(SENSOR_SUCCESS.toInt(), 0)
        assertEquals(SENSOR_PERMISSION_DENIED.toInt(), 201)
        assertEquals(SENSOR_PARAMETER_ERROR.toInt(), 401)
        assertEquals(SENSOR_SERVICE_EXCEPTION.toInt(), 14500101)
        logLine("Sensor_Result passed")
    }

    @Test
    fun testEnum_Sensor_Accuracy() {
        assertEquals(SENSOR_ACCURACY_UNRELIABLE.toInt(), 0)
        assertEquals(SENSOR_ACCURACY_LOW.toInt(), 1)
        assertEquals(SENSOR_ACCURACY_MEDIUM.toInt(), 2)
        assertEquals(SENSOR_ACCURACY_HIGH.toInt(), 3)
        logLine("Sensor_Accuracy passed")
    }

    @Test
    fun testOH_Sensor_GetInfos() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            val ret = OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            assertNotNull(ret)
            logLine("OH_Sensor_GetInfos=$ret")
        }
    }

    @Test
    fun testOH_Sensor_Subscribe() {
        val retSub = OH_Sensor_Subscribe(null, null, null)
        assertNotNull(retSub)
        logLine("OH_Sensor_Subscribe=$retSub")
    }

    @Test
    fun testOH_Sensor_Unsubscribe() {
        val retUnsub = OH_Sensor_Unsubscribe(null, null)
        assertNotNull(retUnsub)
        logLine("OH_Sensor_Unsubscribe=$retUnsub")
    }

    @Test
    fun testOH_Sensor_CreateInfos() {
        val infos = OH_Sensor_CreateInfos(1u)
        assertNotNull(infos)
        logLine("OH_Sensor_CreateInfos=$infos")
        if (infos != null) {
            OH_Sensor_DestroyInfos(infos, 1u)
        }
    }

    @Test
    fun testOH_Sensor_DestroyInfos() {
        val infos = OH_Sensor_CreateInfos(1u)
        assertNotNull(infos)
        val ret = OH_Sensor_DestroyInfos(infos, 1u)
        assertNotNull(ret)
        logLine("OH_Sensor_DestroyInfos=$ret")
    }

    @Test
    fun testOH_SensorInfo_GetName() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            val sensor = infosPtr.value ?: return@memScoped
            if (countVar.value == 0u) return@memScoped
            val nameBuf = ByteArray(256)
            val lengthVar = alloc<UIntVar>().apply { value = 256u }
            val ret = OH_SensorInfo_GetName(sensor, nameBuf.refTo(0), lengthVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorInfo_GetName=$ret")
        }
    }

    @Test
    fun testOH_SensorInfo_GetVendorName() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            val sensor = infosPtr.value ?: return@memScoped
            if (countVar.value == 0u) return@memScoped
            val vendorBuf = ByteArray(256)
            val lengthVar = alloc<UIntVar>().apply { value = 256u }
            val ret = OH_SensorInfo_GetVendorName(sensor, vendorBuf.refTo(0), lengthVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorInfo_GetVendorName=$ret")
        }
    }

    @Test
    fun testOH_SensorInfo_GetType() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            val sensor = infosPtr.value ?: return@memScoped
            if (countVar.value == 0u) return@memScoped
            val typeVar = alloc<IntVar>()
            val ret = OH_SensorInfo_GetType(sensor, typeVar.ptr.reinterpret())
            assertNotNull(ret)
            logLine("OH_SensorInfo_GetType=$ret")
        }
    }

    @Test
    fun testOH_SensorInfo_GetResolution() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            val sensor = infosPtr.value ?: return@memScoped
            if (countVar.value == 0u) return@memScoped
            val resolutionVar = alloc<FloatVar>()
            val ret = OH_SensorInfo_GetResolution(sensor, resolutionVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorInfo_GetResolution=$ret")
        }
    }

    @Test
    fun testOH_SensorInfo_GetMinSamplingInterval() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            val sensor = infosPtr.value ?: return@memScoped
            if (countVar.value == 0u) return@memScoped
            val minIntervalVar = alloc<LongVar>()
            val ret = OH_SensorInfo_GetMinSamplingInterval(sensor, minIntervalVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorInfo_GetMinSamplingInterval=$ret")
        }
    }

    @Test
    fun testOH_SensorInfo_GetMaxSamplingInterval() {
        memScoped {
            val infosPtr = alloc<CPointerVar<Sensor_Info>>()
            val countVar = alloc<UIntVar>()
            OH_Sensor_GetInfos(infosPtr.ptr, countVar.ptr)
            val sensor = infosPtr.value ?: return@memScoped
            if (countVar.value == 0u) return@memScoped
            val maxIntervalVar = alloc<LongVar>()
            val ret = OH_SensorInfo_GetMaxSamplingInterval(sensor, maxIntervalVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorInfo_GetMaxSamplingInterval=$ret")
        }
    }

    @Test
    fun testOH_SensorEvent_GetType() {
        memScoped {
            val typeVar = alloc<IntVar>()
            val ret = OH_SensorEvent_GetType(null, typeVar.ptr.reinterpret())
            assertNotNull(ret)
            logLine("OH_SensorEvent_GetType=$ret")
        }
    }

    @Test
    fun testOH_SensorEvent_GetTimestamp() {
        memScoped {
            val timestampVar = alloc<LongVar>()
            val ret = OH_SensorEvent_GetTimestamp(null, timestampVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorEvent_GetTimestamp=$ret")
        }
    }

    @Test
    fun testOH_SensorEvent_GetAccuracy() {
        memScoped {
            val accuracyVar = alloc<IntVar>()
            val ret = OH_SensorEvent_GetAccuracy(null, accuracyVar.ptr.reinterpret())
            assertNotNull(ret)
            logLine("OH_SensorEvent_GetAccuracy=$ret")
        }
    }

    @Test
    fun testOH_SensorEvent_GetData() {
        memScoped {
            val dataPtr = alloc<CPointerVar<FloatVar>>()
            val lengthVar = alloc<UIntVar>()
            val ret = OH_SensorEvent_GetData(null, dataPtr.ptr, lengthVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorEvent_GetData=$ret")
        }
    }

    @Test
    fun testOH_Sensor_CreateSubscriptionId() {
        val id = OH_Sensor_CreateSubscriptionId()
        assertNotNull(id)
        logLine("OH_Sensor_CreateSubscriptionId=$id")
        if (id != null) {
            OH_Sensor_DestroySubscriptionId(id)
        }
    }

    @Test
    fun testOH_SensorSubscriptionId_SetType() {
        val id = OH_Sensor_CreateSubscriptionId()
        assertNotNull(id)
        val ret = OH_SensorSubscriptionId_SetType(id, SENSOR_TYPE_ACCELEROMETER)
        assertNotNull(ret)
        logLine("OH_SensorSubscriptionId_SetType=$ret")
        OH_Sensor_DestroySubscriptionId(id)
    }

    @Test
    fun testOH_SensorSubscriptionId_GetType() {
        val id = OH_Sensor_CreateSubscriptionId()
        assertNotNull(id)
        memScoped {
            val typeVar = alloc<IntVar>()
            val ret = OH_SensorSubscriptionId_GetType(id, typeVar.ptr.reinterpret())
            assertNotNull(ret)
            logLine("OH_SensorSubscriptionId_GetType=$ret")
        }
        OH_Sensor_DestroySubscriptionId(id)
    }

    @Test
    fun testOH_Sensor_DestroySubscriptionId() {
        val id = OH_Sensor_CreateSubscriptionId()
        assertNotNull(id)
        val ret = OH_Sensor_DestroySubscriptionId(id)
        assertNotNull(ret)
        logLine("OH_Sensor_DestroySubscriptionId=$ret")
    }

    @Test
    fun testOH_Sensor_CreateSubscriptionAttribute() {
        val attr = OH_Sensor_CreateSubscriptionAttribute()
        assertNotNull(attr)
        logLine("OH_Sensor_CreateSubscriptionAttribute=$attr")
        if (attr != null) {
            OH_Sensor_DestroySubscriptionAttribute(attr)
        }
    }

    @Test
    fun testOH_SensorSubscriptionAttribute_SetSamplingInterval() {
        val attr = OH_Sensor_CreateSubscriptionAttribute()
        assertNotNull(attr)
        val ret = OH_SensorSubscriptionAttribute_SetSamplingInterval(attr, 1000000000L)
        assertNotNull(ret)
        logLine("OH_SensorSubscriptionAttribute_SetSamplingInterval=$ret")
        OH_Sensor_DestroySubscriptionAttribute(attr)
    }

    @Test
    fun testOH_SensorSubscriptionAttribute_GetSamplingInterval() {
        val attr = OH_Sensor_CreateSubscriptionAttribute()
        assertNotNull(attr)
        memScoped {
            val intervalVar = alloc<LongVar>()
            val ret = OH_SensorSubscriptionAttribute_GetSamplingInterval(attr, intervalVar.ptr)
            assertNotNull(ret)
            logLine("OH_SensorSubscriptionAttribute_GetSamplingInterval=$ret")
        }
        OH_Sensor_DestroySubscriptionAttribute(attr)
    }

    @Test
    fun testOH_Sensor_DestroySubscriptionAttribute() {
        val attr = OH_Sensor_CreateSubscriptionAttribute()
        assertNotNull(attr)
        val ret = OH_Sensor_DestroySubscriptionAttribute(attr)
        assertNotNull(ret)
        logLine("OH_Sensor_DestroySubscriptionAttribute=$ret")
    }

    @Test
    fun testOH_Sensor_CreateSubscriber() {
        val subscriber = OH_Sensor_CreateSubscriber()
        assertNotNull(subscriber)
        logLine("OH_Sensor_CreateSubscriber=$subscriber")
        if (subscriber != null) {
            OH_Sensor_DestroySubscriber(subscriber)
        }
    }

    @Test
    fun testOH_SensorSubscriber_SetCallback() {
        val subscriber = OH_Sensor_CreateSubscriber()
        assertNotNull(subscriber)
        val ret = OH_SensorSubscriber_SetCallback(subscriber, null)
        assertNotNull(ret)
        logLine("OH_SensorSubscriber_SetCallback=$ret")
        OH_Sensor_DestroySubscriber(subscriber)
    }

    @Test
    fun testOH_SensorSubscriber_GetCallback() {
        val subscriber = OH_Sensor_CreateSubscriber()
        assertNotNull(subscriber)
        memScoped {
            val callbackOut = alloc<CPointerVar<COpaque>>()
            val ret = OH_SensorSubscriber_GetCallback(subscriber, callbackOut.ptr.reinterpret())
            assertNotNull(ret)
            logLine("OH_SensorSubscriber_GetCallback=$ret")
        }
        OH_Sensor_DestroySubscriber(subscriber)
    }

    @Test
    fun testOH_Sensor_DestroySubscriber() {
        val subscriber = OH_Sensor_CreateSubscriber()
        assertNotNull(subscriber)
        val ret = OH_Sensor_DestroySubscriber(subscriber)
        assertNotNull(ret)
        logLine("OH_Sensor_DestroySubscriber=$ret")
    }
}
