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
import platform.MindSporeLiteKit.MindSpore.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class MindSporeTest {

    private fun logLine(msg: String) = println("[stdout] MindSporeTest $msg")

    @Test
    fun testEnum_OH_AI_CompCode() {
        assertEquals(OH_AI_COMPCODE_CORE.toInt(), 0x00000000.toInt())
        assertEquals(OH_AI_COMPCODE_MD.toInt(), 0x10000000.toInt())
        assertEquals(OH_AI_COMPCODE_ME.toInt(), 0x20000000.toInt())
        assertEquals(OH_AI_COMPCODE_MC.toInt(), 0x30000000.toInt())
        assertEquals(OH_AI_COMPCODE_LITE.toInt(), 0xF0000000.toInt())
        logLine("testEnum_OH_AI_CompCode passed")
    }

    @Test
    fun testEnum_OH_AI_Status() {
        assertEquals(OH_AI_STATUS_SUCCESS.toInt(), 0)
        assertEquals(OH_AI_STATUS_CORE_FAILED.toInt(), 1)
        logLine("testEnum_OH_AI_Status passed")
    }

    @Test
    fun testEnum_OH_AI_Types() {
        assertEquals(OH_AI_MODELTYPE_MINDIR.toInt(), 0)
        assertEquals(OH_AI_MODELTYPE_INVALID.toInt(), 0xFFFFFFFF.toInt())
        assertEquals(OH_AI_DEVICETYPE_CPU.toInt(), 0)
        assertEquals(OH_AI_DEVICETYPE_GPU.toInt(), 1)
        assertEquals(OH_AI_DEVICETYPE_KIRIN_NPU.toInt(), 2)
        assertEquals(OH_AI_DEVICETYPE_NNRT.toInt(), 60)
        assertEquals(OH_AI_DEVICETYPE_INVALID.toInt(), 100)
        assertEquals(OH_AI_NNRTDEVICE_OTHERS.toInt(), 0)
        assertEquals(OH_AI_NNRTDEVICE_CPU.toInt(), 1)
        assertEquals(OH_AI_NNRTDEVICE_GPU.toInt(), 2)
        assertEquals(OH_AI_NNRTDEVICE_ACCELERATOR.toInt(), 3)
        assertEquals(OH_AI_PERFORMANCE_NONE.toInt(), 0)
        assertEquals(OH_AI_PERFORMANCE_LOW.toInt(), 1)
        assertEquals(OH_AI_PERFORMANCE_MEDIUM.toInt(), 2)
        assertEquals(OH_AI_PERFORMANCE_HIGH.toInt(), 3)
        assertEquals(OH_AI_PERFORMANCE_EXTREME.toInt(), 4)
        assertEquals(OH_AI_PRIORITY_NONE.toInt(), 0)
        assertEquals(OH_AI_PRIORITY_LOW.toInt(), 1)
        assertEquals(OH_AI_PRIORITY_MEDIUM.toInt(), 2)
        assertEquals(OH_AI_PRIORITY_HIGH.toInt(), 3)
        assertEquals(OH_AI_KO0.toInt(), 0)
        assertEquals(OH_AI_KO2.toInt(), 2)
        assertEquals(OH_AI_KO3.toInt(), 3)
        assertEquals(OH_AI_KAUTO.toInt(), 4)
        assertEquals(OH_AI_KOPTIMIZATIONTYPE.toInt(), 0xFFFFFFFF.toInt())
        assertEquals(OH_AI_NO_QUANT.toInt(), 0)
        assertEquals(OH_AI_WEIGHT_QUANT.toInt(), 1)
        assertEquals(OH_AI_FULL_QUANT.toInt(), 2)
        assertEquals(OH_AI_UNKNOWN_QUANT_TYPE.toInt(), 0xFFFFFFFF.toInt())
        logLine("testEnum_OH_AI_Types passed")
    }

    @Test
    fun testEnum_OH_AI_Format() {
        assertEquals(OH_AI_FORMAT_NCHW.toInt(), 0)
        assertEquals(OH_AI_FORMAT_NHWC.toInt(), 1)
        assertEquals(OH_AI_FORMAT_NHWC4.toInt(), 2)
        assertEquals(OH_AI_FORMAT_HWKC.toInt(), 3)
        assertEquals(OH_AI_FORMAT_HWCK.toInt(), 4)
        assertEquals(OH_AI_FORMAT_KCHW.toInt(), 5)
        assertEquals(OH_AI_FORMAT_CKHW.toInt(), 6)
        assertEquals(OH_AI_FORMAT_KHWC.toInt(), 7)
        assertEquals(OH_AI_FORMAT_CHWK.toInt(), 8)
        assertEquals(OH_AI_FORMAT_HW.toInt(), 9)
        assertEquals(OH_AI_FORMAT_HW4.toInt(), 10)
        assertEquals(OH_AI_FORMAT_NC.toInt(), 11)
        assertEquals(OH_AI_FORMAT_NC4.toInt(), 12)
        assertEquals(OH_AI_FORMAT_NC4HW4.toInt(), 13)
        assertEquals(OH_AI_FORMAT_NCDHW.toInt(), 15)
        assertEquals(OH_AI_FORMAT_NWC.toInt(), 16)
        assertEquals(OH_AI_FORMAT_NCW.toInt(), 17)
        logLine("testEnum_OH_AI_Format passed")
    }

    @Test
    fun testEnum_OH_AI_DataType() {
        assertEquals(OH_AI_DATATYPE_UNKNOWN.toInt(), 0)
        assertEquals(OH_AI_DATATYPE_OBJECTTYPE_STRING.toInt(), 12)
        assertEquals(OH_AI_DATATYPE_OBJECTTYPE_LIST.toInt(), 13)
        assertEquals(OH_AI_DATATYPE_OBJECTTYPE_TUPLE.toInt(), 14)
        assertEquals(OH_AI_DATATYPE_OBJECTTYPE_TENSOR.toInt(), 17)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_BEGIN.toInt(), 29)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_BOOL.toInt(), 30)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_INT8.toInt(), 32)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_INT16.toInt(), 33)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_INT32.toInt(), 34)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_INT64.toInt(), 35)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_UINT8.toInt(), 37)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_UINT16.toInt(), 38)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_UINT32.toInt(), 39)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_UINT64.toInt(), 40)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_FLOAT16.toInt(), 42)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_FLOAT32.toInt(), 43)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_FLOAT64.toInt(), 44)
        assertEquals(OH_AI_DATATYPE_NUMBERTYPE_END.toInt(), 46)
        assertEquals(OH_AI_DataTypeInvalid.toInt(), Int.MAX_VALUE)
        logLine("testEnum_OH_AI_DataType passed")
    }

    // ==================== Context ====================

    @Test
    fun testOH_AI_ContextCreate() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            logLine("OH_AI_ContextCreate=$ctx")
        }
    }

    @Test
    fun testOH_AI_ContextSetThreadNum() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            OH_AI_ContextSetThreadNum(ctx, 2)
            logLine("OH_AI_ContextSetThreadNum=called")
        }
    }

    @Test
    fun testOH_AI_ContextGetThreadNum() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            OH_AI_ContextSetThreadNum(ctx, 2)
            val num = OH_AI_ContextGetThreadNum(ctx)
            assertNotNull(num)
            logLine("OH_AI_ContextGetThreadNum=$num")
        }
    }

    @Test
    fun testOH_AI_ContextSetThreadAffinityMode() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            OH_AI_ContextSetThreadAffinityMode(ctx, 0)
            logLine("OH_AI_ContextSetThreadAffinityMode=called")
        }
    }

    @Test
    fun testOH_AI_ContextGetThreadAffinityMode() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            OH_AI_ContextSetThreadAffinityMode(ctx, 0)
            val affinityMode = OH_AI_ContextGetThreadAffinityMode(ctx)
            logLine("OH_AI_ContextGetThreadAffinityMode=$affinityMode")
        }
    }

    @Test
    fun testOH_AI_ContextSetThreadAffinityCoreList() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            val coreList = allocArray<IntVar>(3).apply { this[0] = 0; this[1] = 1; this[2] = 2 }
            OH_AI_ContextSetThreadAffinityCoreList(ctx, coreList, 3u)
            logLine("OH_AI_ContextSetThreadAffinityCoreList=called")
        }
    }

    @Test
    fun testOH_AI_ContextGetThreadAffinityCoreList() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            val coreList = allocArray<IntVar>(3).apply { this[0] = 0; this[1] = 1; this[2] = 2 }
            OH_AI_ContextSetThreadAffinityCoreList(ctx, coreList, 3u)
            val coreNumPtr = alloc<ULongVar>()
            val rc = OH_AI_ContextGetThreadAffinityCoreList(ctx, coreNumPtr.ptr)
            logLine("OH_AI_ContextGetThreadAffinityCoreList=$rc coreNum=${coreNumPtr.value}")
        }
    }

    @Test
    fun testOH_AI_ContextSetEnableParallel() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            OH_AI_ContextSetEnableParallel(ctx, true)
            logLine("OH_AI_ContextSetEnableParallel=called")
        }
    }

    @Test
    fun testOH_AI_ContextGetEnableParallel() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            OH_AI_ContextSetEnableParallel(ctx, true)
            val parallel = OH_AI_ContextGetEnableParallel(ctx)
            logLine("OH_AI_ContextGetEnableParallel=$parallel")
        }
    }

    @Test
    fun testOH_AI_DeviceInfoCreate() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            logLine("OH_AI_DeviceInfoCreate=$deviceInfo")
            if (deviceInfo != null) {
                val ptr = alloc<COpaquePointerVar>()
                ptr.value = deviceInfo
                OH_AI_DeviceInfoDestroy(ptr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_ContextAddDeviceInfo() {
        memScoped {
            val ctx = OH_AI_ContextCreate()
            assertNotNull(ctx)
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_ContextAddDeviceInfo(ctx, deviceInfo)
            logLine("OH_AI_ContextAddDeviceInfo=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetProvider() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetProvider(deviceInfo, "CPU")
            logLine("OH_AI_DeviceInfoSetProvider=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetProvider() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetProvider(deviceInfo, "CPU")
            val provider = OH_AI_DeviceInfoGetProvider(deviceInfo)
            logLine("OH_AI_DeviceInfoGetProvider=$provider")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetProviderDevice() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetProviderDevice(deviceInfo, "CPU")
            logLine("OH_AI_DeviceInfoSetProviderDevice=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetProviderDevice() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetProviderDevice(deviceInfo, "CPU")
            val providerDevice = OH_AI_DeviceInfoGetProviderDevice(deviceInfo)
            logLine("OH_AI_DeviceInfoGetProviderDevice=$providerDevice")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetDeviceType() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            val deviceType = OH_AI_DeviceInfoGetDeviceType(deviceInfo)
            logLine("OH_AI_DeviceInfoGetDeviceType=$deviceType")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetEnableFP16() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetEnableFP16(deviceInfo, false)
            logLine("OH_AI_DeviceInfoSetEnableFP16=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetEnableFP16() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetEnableFP16(deviceInfo, false)
            val fp16 = OH_AI_DeviceInfoGetEnableFP16(deviceInfo)
            logLine("OH_AI_DeviceInfoGetEnableFP16=$fp16")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetFrequency() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetFrequency(deviceInfo, 3)
            logLine("OH_AI_DeviceInfoSetFrequency=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetFrequency() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetFrequency(deviceInfo, 3)
            val freq = OH_AI_DeviceInfoGetFrequency(deviceInfo)
            logLine("OH_AI_DeviceInfoGetFrequency=$freq")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetDeviceId() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetDeviceId(deviceInfo, 0uL)
            logLine("OH_AI_DeviceInfoSetDeviceId=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetDeviceId() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetDeviceId(deviceInfo, 0uL)
            val deviceId = OH_AI_DeviceInfoGetDeviceId(deviceInfo)
            logLine("OH_AI_DeviceInfoGetDeviceId=$deviceId")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetPerformanceMode() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetPerformanceMode(deviceInfo, OH_AI_PERFORMANCE_HIGH)
            logLine("OH_AI_DeviceInfoSetPerformanceMode=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetPerformanceMode() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetPerformanceMode(deviceInfo, OH_AI_PERFORMANCE_HIGH)
            val perfMode = OH_AI_DeviceInfoGetPerformanceMode(deviceInfo)
            logLine("OH_AI_DeviceInfoGetPerformanceMode=$perfMode")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoSetPriority() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetPriority(deviceInfo, OH_AI_PRIORITY_HIGH)
            logLine("OH_AI_DeviceInfoSetPriority=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoGetPriority() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            OH_AI_DeviceInfoSetPriority(deviceInfo, OH_AI_PRIORITY_HIGH)
            val priority = OH_AI_DeviceInfoGetPriority(deviceInfo)
            logLine("OH_AI_DeviceInfoGetPriority=$priority")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoAddExtension() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            val rc = OH_AI_DeviceInfoAddExtension(deviceInfo, null, null, 0uL)
            logLine("OH_AI_DeviceInfoAddExtension=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_DeviceInfoDestroy() {
        memScoped {
            val deviceInfo = OH_AI_DeviceInfoCreate(OH_AI_DEVICETYPE_CPU)
            assertNotNull(deviceInfo)
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = deviceInfo
            OH_AI_DeviceInfoDestroy(ptr.ptr)
            logLine("OH_AI_DeviceInfoDestroy=called")
        }
    }

    @Test
    fun testOH_AI_GetAllNNRTDeviceDescs() {
        memScoped {
            val nnrtNumPtr = alloc<ULongVar>()
            val nnrtDescs = OH_AI_GetAllNNRTDeviceDescs(nnrtNumPtr.ptr)
            logLine("OH_AI_GetAllNNRTDeviceDescs=$nnrtDescs num=${nnrtNumPtr.value}")
            if (nnrtDescs != null) {
                val nnrtDescPtr = alloc<CPointerVar<NNRTDeviceDesc>>()
                nnrtDescPtr.value = nnrtDescs
                OH_AI_DestroyAllNNRTDeviceDescs(nnrtDescPtr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_GetElementOfNNRTDeviceDescs() {
        memScoped {
            val nnrtNumPtr = alloc<ULongVar>()
            val nnrtDescs = OH_AI_GetAllNNRTDeviceDescs(nnrtNumPtr.ptr)
            if (nnrtDescs != null && nnrtNumPtr.value > 0uL) {
                val elem = OH_AI_GetElementOfNNRTDeviceDescs(nnrtDescs, 0uL)
                val devId = OH_AI_GetDeviceIdFromNNRTDeviceDesc(elem)
                val devName = OH_AI_GetNameFromNNRTDeviceDesc(elem)
                val devType = OH_AI_GetTypeFromNNRTDeviceDesc(elem)
                logLine("OH_AI_GetElementOfNNRTDeviceDescs devId=$devId name=$devName type=$devType")
                val nnrtDescPtr = alloc<CPointerVar<NNRTDeviceDesc>>()
                nnrtDescPtr.value = nnrtDescs
                OH_AI_DestroyAllNNRTDeviceDescs(nnrtDescPtr.ptr)
            } else {
                logLine("OH_AI_GetElementOfNNRTDeviceDescs skipped (no descs)")
            }
        }
    }

    @Test
    fun testOH_AI_GetDeviceIdFromNNRTDeviceDesc() {
        memScoped {
            val nnrtNumPtr = alloc<ULongVar>()
            val nnrtDescs = OH_AI_GetAllNNRTDeviceDescs(nnrtNumPtr.ptr)
            if (nnrtDescs != null && nnrtNumPtr.value > 0uL) {
                val elem = OH_AI_GetElementOfNNRTDeviceDescs(nnrtDescs, 0uL)
                val devId = OH_AI_GetDeviceIdFromNNRTDeviceDesc(elem)
                logLine("OH_AI_GetDeviceIdFromNNRTDeviceDesc=$devId")
                val nnrtDescPtr = alloc<CPointerVar<NNRTDeviceDesc>>()
                nnrtDescPtr.value = nnrtDescs
                OH_AI_DestroyAllNNRTDeviceDescs(nnrtDescPtr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_GetNameFromNNRTDeviceDesc() {
        memScoped {
            val nnrtNumPtr = alloc<ULongVar>()
            val nnrtDescs = OH_AI_GetAllNNRTDeviceDescs(nnrtNumPtr.ptr)
            if (nnrtDescs != null && nnrtNumPtr.value > 0uL) {
                val elem = OH_AI_GetElementOfNNRTDeviceDescs(nnrtDescs, 0uL)
                val devName = OH_AI_GetNameFromNNRTDeviceDesc(elem)
                logLine("OH_AI_GetNameFromNNRTDeviceDesc=$devName")
                val nnrtDescPtr = alloc<CPointerVar<NNRTDeviceDesc>>()
                nnrtDescPtr.value = nnrtDescs
                OH_AI_DestroyAllNNRTDeviceDescs(nnrtDescPtr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_GetTypeFromNNRTDeviceDesc() {
        memScoped {
            val nnrtNumPtr = alloc<ULongVar>()
            val nnrtDescs = OH_AI_GetAllNNRTDeviceDescs(nnrtNumPtr.ptr)
            if (nnrtDescs != null && nnrtNumPtr.value > 0uL) {
                val elem = OH_AI_GetElementOfNNRTDeviceDescs(nnrtDescs, 0uL)
                val devType = OH_AI_GetTypeFromNNRTDeviceDesc(elem)
                logLine("OH_AI_GetTypeFromNNRTDeviceDesc=$devType")
                val nnrtDescPtr = alloc<CPointerVar<NNRTDeviceDesc>>()
                nnrtDescPtr.value = nnrtDescs
                OH_AI_DestroyAllNNRTDeviceDescs(nnrtDescPtr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_DestroyAllNNRTDeviceDescs() {
        memScoped {
            val nnrtNumPtr = alloc<ULongVar>()
            val nnrtDescs = OH_AI_GetAllNNRTDeviceDescs(nnrtNumPtr.ptr)
            if (nnrtDescs != null) {
                val nnrtDescPtr = alloc<CPointerVar<NNRTDeviceDesc>>()
                nnrtDescPtr.value = nnrtDescs
                OH_AI_DestroyAllNNRTDeviceDescs(nnrtDescPtr.ptr)
                logLine("OH_AI_DestroyAllNNRTDeviceDescs=called")
            }
        }
    }

    @Test
    fun testOH_AI_CreateNNRTDeviceInfoByName() {
        memScoped {
            val nnrtByName = OH_AI_CreateNNRTDeviceInfoByName("CPU1")
            logLine("OH_AI_CreateNNRTDeviceInfoByName=$nnrtByName")
        }
    }

    @Test
    fun testOH_AI_CreateNNRTDeviceInfoByType() {
        memScoped {
            val nnrtByType = OH_AI_CreateNNRTDeviceInfoByType(OH_AI_NNRTDEVICE_CPU)
            logLine("OH_AI_CreateNNRTDeviceInfoByType=$nnrtByType")
            if (nnrtByType != null) {
                val ptr = alloc<COpaquePointerVar>()
                ptr.value = nnrtByType
                OH_AI_DeviceInfoDestroy(ptr.ptr)
            }
        }
    }

    // ==================== Tensor ====================

    @Test
    fun testOH_AI_TensorCreate() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            logLine("OH_AI_TensorCreate=$tensor")
            if (tensor != null) {
                val ptr = alloc<COpaquePointerVar>()
                ptr.value = tensor
                OH_AI_TensorDestroy(ptr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_TensorClone() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val clone = OH_AI_TensorClone(tensor)
            logLine("OH_AI_TensorClone=$clone")
            if (clone != null) { val p = alloc<COpaquePointerVar>(); p.value = clone; OH_AI_TensorDestroy(p.ptr) }
            val tensorPtr = alloc<COpaquePointerVar>()
            tensorPtr.value = tensor
            OH_AI_TensorDestroy(tensorPtr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetName() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            OH_AI_TensorSetName(tensor, "tensor1")
            logLine("OH_AI_TensorSetName=called")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetName() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            OH_AI_TensorSetName(tensor, "tensor1")
            val name = OH_AI_TensorGetName(tensor)
            logLine("OH_AI_TensorGetName=$name")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetDataType() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            OH_AI_TensorSetDataType(tensor, OH_AI_DATATYPE_NUMBERTYPE_INT32)
            logLine("OH_AI_TensorSetDataType=called")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetDataType() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            OH_AI_TensorSetDataType(tensor, OH_AI_DATATYPE_NUMBERTYPE_INT32)
            val dtype = OH_AI_TensorGetDataType(tensor)
            logLine("OH_AI_TensorGetDataType=$dtype")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetShape() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val shapeSet = allocArray<LongVar>(3).apply { this[0] = 2L; this[1] = 3L; this[2] = 4L }
            OH_AI_TensorSetShape(tensor, shapeSet, 3u)
            logLine("OH_AI_TensorSetShape=called")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetShape() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val shapeSet = allocArray<LongVar>(3).apply { this[0] = 2L; this[1] = 3L; this[2] = 4L }
            OH_AI_TensorSetShape(tensor, shapeSet, 3u)
            val shapeNumPtr = alloc<ULongVar>()
            val rc = OH_AI_TensorGetShape(tensor, shapeNumPtr.ptr)
            logLine("OH_AI_TensorGetShape=$rc shape_num=${shapeNumPtr.value}")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetFormat() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            OH_AI_TensorSetFormat(tensor, OH_AI_FORMAT_NCHW)
            logLine("OH_AI_TensorSetFormat=called")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetFormat() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            OH_AI_TensorSetFormat(tensor, OH_AI_FORMAT_NCHW)
            val format = OH_AI_TensorGetFormat(tensor)
            logLine("OH_AI_TensorGetFormat=$format")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetData() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val dataBuf = allocArray<ByteVar>(8)
            OH_AI_TensorSetData(tensor, dataBuf)
            logLine("OH_AI_TensorSetData=called")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetData() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val dataBuf = allocArray<ByteVar>(8)
            OH_AI_TensorSetData(tensor, dataBuf)
            val data = OH_AI_TensorGetData(tensor)
            logLine("OH_AI_TensorGetData=$data")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetMutableData() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val mutableData = OH_AI_TensorGetMutableData(tensor)
            logLine("OH_AI_TensorGetMutableData=$mutableData")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetElementNum() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val elemNum = OH_AI_TensorGetElementNum(tensor)
            logLine("OH_AI_TensorGetElementNum=$elemNum")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetDataSize() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val dataSize = OH_AI_TensorGetDataSize(tensor)
            logLine("OH_AI_TensorGetDataSize=$dataSize")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetUserData() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val userDataBuf = allocArray<ByteVar>(16)
            val rc = OH_AI_TensorSetUserData(tensor, userDataBuf, 16u)
            logLine("OH_AI_TensorSetUserData=$rc")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorGetAllocator() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val allocator = OH_AI_TensorGetAllocator(tensor)
            logLine("OH_AI_TensorGetAllocator=$allocator")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorSetAllocator() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val rc = OH_AI_TensorSetAllocator(tensor, null)
            logLine("OH_AI_TensorSetAllocator=$rc")
            val ptr = alloc<COpaquePointerVar>(); ptr.value = tensor; OH_AI_TensorDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TensorDestroy() {
        memScoped {
            val shapeArray = allocArray<LongVar>(2).apply { this[0] = 1L; this[1] = 2L }
            val tensor = OH_AI_TensorCreate(null, OH_AI_DATATYPE_NUMBERTYPE_FLOAT32, shapeArray, 2u, null, 0u)
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = tensor
            OH_AI_TensorDestroy(ptr.ptr)
            logLine("OH_AI_TensorDestroy=called")
        }
    }

    // ==================== Model ====================

    @Test
    fun testOH_AI_ModelCreate() {
        memScoped {
            val model = OH_AI_ModelCreate()
            logLine("OH_AI_ModelCreate=$model")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelBuild() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val ctx = OH_AI_ContextCreate()
            val rc = OH_AI_ModelBuild(model, null, 0uL, OH_AI_MODELTYPE_MINDIR, ctx)
            logLine("OH_AI_ModelBuild=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelBuildFromFile() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val ctx = OH_AI_ContextCreate()
            val rc = OH_AI_ModelBuildFromFile(model, null, OH_AI_MODELTYPE_MINDIR, ctx)
            logLine("OH_AI_ModelBuildFromFile=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelResize() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val emptyInputs = alloc<OH_AI_TensorHandleArray>().apply { handle_num = 0uL; handle_list = null }
            val shapeInfos = allocArray<OH_AI_ShapeInfo>(1)
            val rc = OH_AI_ModelResize(model, emptyInputs.readValue(), shapeInfos, 0uL)
            logLine("OH_AI_ModelResize=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetInputs() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val inputs = OH_AI_ModelGetInputs(model)
            logLine("OH_AI_ModelGetInputs handle_num=${inputs.useContents { handle_num }}")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetOutputs() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val outputs = OH_AI_ModelGetOutputs(model)
            logLine("OH_AI_ModelGetOutputs handle_num=${outputs.useContents { handle_num }}")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetInputByTensorName() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val inputByName = OH_AI_ModelGetInputByTensorName(model, null)
            logLine("OH_AI_ModelGetInputByTensorName=$inputByName")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetOutputByTensorName() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val outputByName = OH_AI_ModelGetOutputByTensorName(model, null)
            logLine("OH_AI_ModelGetOutputByTensorName=$outputByName")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelPredict() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val emptyInputs = alloc<OH_AI_TensorHandleArray>().apply { handle_num = 0uL; handle_list = null }
            val outputArray = alloc<OH_AI_TensorHandleArray>()
            val rc = OH_AI_ModelPredict(model, emptyInputs.readValue(), outputArray.ptr, null, null)
            logLine("OH_AI_ModelPredict=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelDestroy() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
            logLine("OH_AI_ModelDestroy=called")
        }
    }

    @Test
    fun testOH_AI_TrainCfgCreate() {
        memScoped {
            val trainCfg = OH_AI_TrainCfgCreate()
            logLine("OH_AI_TrainCfgCreate=$trainCfg")
            if (trainCfg != null) {
                val ptr = alloc<COpaquePointerVar>()
                ptr.value = trainCfg
                OH_AI_TrainCfgDestroy(ptr.ptr)
            }
        }
    }

    @Test
    fun testOH_AI_TrainCfgGetLossName() {
        memScoped {
            val trainCfg = OH_AI_TrainCfgCreate()
            val lossNumPtr = alloc<ULongVar>()
            val lossNames = OH_AI_TrainCfgGetLossName(trainCfg, lossNumPtr.ptr)
            logLine("OH_AI_TrainCfgGetLossName=$lossNames num=${lossNumPtr.value}")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = trainCfg
            OH_AI_TrainCfgDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TrainCfgSetLossName() {
        memScoped {
            val trainCfg = OH_AI_TrainCfgCreate()
            OH_AI_TrainCfgSetLossName(trainCfg, null, 0uL)
            logLine("OH_AI_TrainCfgSetLossName=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = trainCfg
            OH_AI_TrainCfgDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TrainCfgGetOptimizationLevel() {
        memScoped {
            val trainCfg = OH_AI_TrainCfgCreate()
            val optLevel = OH_AI_TrainCfgGetOptimizationLevel(trainCfg)
            logLine("OH_AI_TrainCfgGetOptimizationLevel=$optLevel")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = trainCfg
            OH_AI_TrainCfgDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TrainCfgSetOptimizationLevel() {
        memScoped {
            val trainCfg = OH_AI_TrainCfgCreate()
            OH_AI_TrainCfgSetOptimizationLevel(trainCfg, OH_AI_KO0)
            logLine("OH_AI_TrainCfgSetOptimizationLevel=called")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = trainCfg
            OH_AI_TrainCfgDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TrainModelBuild() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val ctx = OH_AI_ContextCreate()
            val trainCfg = OH_AI_TrainCfgCreate()
            val rc = OH_AI_TrainModelBuild(model, null, 0uL, OH_AI_MODELTYPE_MINDIR, ctx, trainCfg)
            logLine("OH_AI_TrainModelBuild=$rc")
            val tcPtr = alloc<COpaquePointerVar>()
            tcPtr.value = trainCfg
            OH_AI_TrainCfgDestroy(tcPtr.ptr)
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TrainModelBuildFromFile() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val ctx = OH_AI_ContextCreate()
            val trainCfg = OH_AI_TrainCfgCreate()
            val rc = OH_AI_TrainModelBuildFromFile(model, null, OH_AI_MODELTYPE_MINDIR, ctx, trainCfg)
            logLine("OH_AI_TrainModelBuildFromFile=$rc")
            val tcPtr = alloc<COpaquePointerVar>()
            tcPtr.value = trainCfg
            OH_AI_TrainCfgDestroy(tcPtr.ptr)
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_RunStep() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val rc = OH_AI_RunStep(model, null, null)
            logLine("OH_AI_RunStep=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelSetLearningRate() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val rc = OH_AI_ModelSetLearningRate(model, 0.001f)
            logLine("OH_AI_ModelSetLearningRate=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetLearningRate() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val lr = OH_AI_ModelGetLearningRate(model)
            logLine("OH_AI_ModelGetLearningRate=$lr")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetWeights() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val weights = OH_AI_ModelGetWeights(model)
            logLine("OH_AI_ModelGetWeights handle_num=${weights.useContents { handle_num }}")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelUpdateWeights() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val emptyInputs = alloc<OH_AI_TensorHandleArray>().apply { handle_num = 0uL; handle_list = null }
            val rc = OH_AI_ModelUpdateWeights(model, emptyInputs.readValue())
            logLine("OH_AI_ModelUpdateWeights=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelGetTrainMode() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val trainMode = OH_AI_ModelGetTrainMode(model)
            logLine("OH_AI_ModelGetTrainMode=$trainMode")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelSetTrainMode() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val rc = OH_AI_ModelSetTrainMode(model, false)
            logLine("OH_AI_ModelSetTrainMode=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_ModelSetupVirtualBatch() {
        memScoped {
            val model = OH_AI_ModelCreate()
            val rc = OH_AI_ModelSetupVirtualBatch(model, 1, 0.001f, 0.99f)
            logLine("OH_AI_ModelSetupVirtualBatch=$rc")
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = model
            OH_AI_ModelDestroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_AI_TrainCfgDestroy() {
        memScoped {
            val trainCfg = OH_AI_TrainCfgCreate()
            assertNotNull(trainCfg)
            val ptr = alloc<COpaquePointerVar>()
            ptr.value = trainCfg
            OH_AI_TrainCfgDestroy(ptr.ptr)
            logLine("OH_AI_TrainCfgDestroy=called")
        }
    }
}
