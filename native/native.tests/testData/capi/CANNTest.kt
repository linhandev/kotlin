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
class CANNTest {

    private fun logLine(message: String) {
        println("[stdout] CANNTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- HiAI_Compatibility ---")
        logLine("HIAI_COMPATIBILITY_COMPATIBLE=${platform.CANNKit.CANN.HIAI_COMPATIBILITY_COMPATIBLE}")
        logLine("HIAI_COMPATIBILITY_INCOMPATIBLE=${platform.CANNKit.CANN.HIAI_COMPATIBILITY_INCOMPATIBLE}")

        logLine("--- HiAI_ImageFormat ---")
        logLine("HIAI_YUV420SP_U8=${platform.CANNKit.CANN.HIAI_YUV420SP_U8}, HIAI_XRGB8888_U8=${platform.CANNKit.CANN.HIAI_XRGB8888_U8}, HIAI_YUV400_U8=${platform.CANNKit.CANN.HIAI_YUV400_U8}, HIAI_ARGB8888_U8=${platform.CANNKit.CANN.HIAI_ARGB8888_U8}, HIAI_IMAGE_FORMAT_INVALID=${platform.CANNKit.CANN.HIAI_IMAGE_FORMAT_INVALID}")

        logLine("--- HiAI_ImageColorSpace ---")
        logLine("HIAI_JPEG=${platform.CANNKit.CANN.HIAI_JPEG}, HIAI_BT_601_NARROW=${platform.CANNKit.CANN.HIAI_BT_601_NARROW}, HIAI_BT_601_FULL=${platform.CANNKit.CANN.HIAI_BT_601_FULL}, HIAI_BT_709_NARROW=${platform.CANNKit.CANN.HIAI_BT_709_NARROW}, HIAI_IMAGE_COLOR_SPACE_INVALID=${platform.CANNKit.CANN.HIAI_IMAGE_COLOR_SPACE_INVALID}")

        logLine("--- HiAI_FormatMode ---")
        logLine("HIAI_FORMAT_MODE_NCHW=${platform.CANNKit.CANN.HIAI_FORMAT_MODE_NCHW}, HIAI_FORMAT_MODE_ORIGIN=${platform.CANNKit.CANN.HIAI_FORMAT_MODE_ORIGIN}")

        logLine("--- HiAI_DynamicShapeStatus ---")
        logLine("HIAI_DYNAMIC_SHAPE_DISABLED=${platform.CANNKit.CANN.HIAI_DYNAMIC_SHAPE_DISABLED}, HIAI_DYNAMIC_SHAPE_ENABLED=${platform.CANNKit.CANN.HIAI_DYNAMIC_SHAPE_ENABLED}")

        logLine("--- HiAI_DynamicShapeCacheMode ---")
        logLine("HIAI_DYNAMIC_SHAPE_CACHE_BUILT_MODEL=${platform.CANNKit.CANN.HIAI_DYNAMIC_SHAPE_CACHE_BUILT_MODEL}, HIAI_DYNAMIC_SHAPE_CACHE_LOADED_MODEL=${platform.CANNKit.CANN.HIAI_DYNAMIC_SHAPE_CACHE_LOADED_MODEL}")

        logLine("--- HiAI_ExecuteDevice ---")
        logLine("HIAI_EXECUTE_DEVICE_NPU=${platform.CANNKit.CANN.HIAI_EXECUTE_DEVICE_NPU}, HIAI_EXECUTE_DEVICE_CPU=${platform.CANNKit.CANN.HIAI_EXECUTE_DEVICE_CPU}, HIAI_EXECUTE_DEVICE_GPU=${platform.CANNKit.CANN.HIAI_EXECUTE_DEVICE_GPU}")

        logLine("--- HiAI_SingleOpDataType ---")
        logLine("HIAI_SINGLEOP_DT_FLOAT=${platform.CANNKit.CANN.HIAI_SINGLEOP_DT_FLOAT}, HIAI_SINGLEOP_DT_FLOAT16=${platform.CANNKit.CANN.HIAI_SINGLEOP_DT_FLOAT16}, HIAI_SINGLEOP_DT_UNDEFINED=${platform.CANNKit.CANN.HIAI_SINGLEOP_DT_UNDEFINED}")

        logLine("--- HiAI_SingleOpFormat ---")
        logLine("HIAI_SINGLEOP_FORMAT_NCHW=${platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_NCHW}, HIAI_SINGLEOP_FORMAT_NHWC=${platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_NHWC}, HIAI_SINGLEOP_FORMAT_ND=${platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_ND}, HIAI_SINGLEOP_FORMAT_NC1HWC0=${platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_NC1HWC0}, HIAI_SINGLEOP_FORMAT_RESERVED=${platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_RESERVED}")
    }

    @Test
    fun testGetVersion() {
        logLine("--- HMS_HiAI_GetVersion ---")
        val version = platform.CANNKit.CANN.HMS_HiAI_GetVersion()
        logLine("HMS_HiAI_GetVersion result: ${version?.toKString()}")
    }

    @Test
    fun testCompatibilityCheckFromFile() {
        logLine("--- HMS_HiAICompatibility_CheckFromFile ---")
        val result = platform.CANNKit.CANN.HMS_HiAICompatibility_CheckFromFile(null)
        logLine("HMS_HiAICompatibility_CheckFromFile(null) result: $result")
    }

    @Test
    fun testCompatibilityCheckFromBuffer() {
        logLine("--- HMS_HiAICompatibility_CheckFromBuffer ---")
        val result = platform.CANNKit.CANN.HMS_HiAICompatibility_CheckFromBuffer(null, 0uL)
        logLine("HMS_HiAICompatibility_CheckFromBuffer(null, 0) result: $result")
    }

    @Test
    fun testTensorGetSizeWithImageFormat() {
        logLine("--- HMS_HiAITensor_GetSizeWithImageFormat ---")
        val size = platform.CANNKit.CANN.HMS_HiAITensor_GetSizeWithImageFormat(null, platform.CANNKit.CANN.HIAI_YUV420SP_U8)
        logLine("HMS_HiAITensor_GetSizeWithImageFormat(null, YUV420SP_U8) result: $size")
    }

    @Test
    fun testTensorSetAippParams() {
        logLine("--- HMS_HiAITensor_SetAippParams ---")
        val result = platform.CANNKit.CANN.HMS_HiAITensor_SetAippParams(null, null, 0uL)
        logLine("HMS_HiAITensor_SetAippParams(null, null, 0) result: $result")
    }

    @Test
    fun testAippParamCreateAndGetters() {
        logLine("--- HMS_HiAIAippParam Create/Get/Set/Destroy ---")
        val aippParam = platform.CANNKit.CANN.HMS_HiAIAippParam_Create(1u)
        logLine("HMS_HiAIAippParam_Create(1) result: $aippParam")
        val data = platform.CANNKit.CANN.HMS_HiAIAippParam_GetData(aippParam)
        logLine("HMS_HiAIAippParam_GetData result: $data")
        val dataSize = platform.CANNKit.CANN.HMS_HiAIAippParam_GetDataSize(aippParam)
        logLine("HMS_HiAIAippParam_GetDataSize result: $dataSize")
        val inputIndex = platform.CANNKit.CANN.HMS_HiAIAippParam_GetInputIndex(aippParam)
        logLine("HMS_HiAIAippParam_GetInputIndex result: $inputIndex")
        val setInputIndexResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetInputIndex(aippParam, 0u)
        logLine("HMS_HiAIAippParam_SetInputIndex result: $setInputIndexResult")
        val inputAippIndex = platform.CANNKit.CANN.HMS_HiAIAippParam_GetInputAippIndex(aippParam)
        logLine("HMS_HiAIAippParam_GetInputAippIndex result: $inputAippIndex")
        val setInputAippIndexResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetInputAippIndex(aippParam, 0u)
        logLine("HMS_HiAIAippParam_SetInputAippIndex result: $setInputAippIndexResult")
        val inputFormat = platform.CANNKit.CANN.HMS_HiAIAippParam_GetInputFormat(aippParam)
        logLine("HMS_HiAIAippParam_GetInputFormat result: $inputFormat")
        val setInputFormatResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetInputFormat(aippParam, platform.CANNKit.CANN.HIAI_YUV420SP_U8)
        logLine("HMS_HiAIAippParam_SetInputFormat result: $setInputFormatResult")
        val batchCount = platform.CANNKit.CANN.HMS_HiAIAippParam_GetBatchCount(aippParam)
        logLine("HMS_HiAIAippParam_GetBatchCount result: $batchCount")
        val singleBatchMultiCrop = platform.CANNKit.CANN.HMS_HiAIAippParam_GetSingleBatchMultiCrop(aippParam)
        logLine("HMS_HiAIAippParam_GetSingleBatchMultiCrop result: $singleBatchMultiCrop")
        val setInputShapeResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetInputShape(aippParam, 256u, 256u)
        logLine("HMS_HiAIAippParam_SetInputShape(256,256) result: $setInputShapeResult")
        memScoped {
            val w = alloc<UIntVar>()
            val h = alloc<UIntVar>()
            val getInputShapeResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetInputShape(aippParam, w.ptr, h.ptr)
            logLine("HMS_HiAIAippParam_GetInputShape result: $getInputShapeResult w=${w.value} h=${h.value}")
        }
        val setCscResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetCscConfig(aippParam, platform.CANNKit.CANN.HIAI_YUV420SP_U8, platform.CANNKit.CANN.HIAI_RGB888_U8, platform.CANNKit.CANN.HIAI_BT_601_NARROW)
        logLine("HMS_HiAIAippParam_SetCscConfig result: $setCscResult")
        memScoped {
            val inFmt = alloc<platform.CANNKit.CANN.HiAI_ImageFormatVar>()
            val outFmt = alloc<platform.CANNKit.CANN.HiAI_ImageFormatVar>()
            val space = alloc<platform.CANNKit.CANN.HiAI_ImageColorSpaceVar>()
            val getCscResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetCscConfig(aippParam, inFmt.ptr, outFmt.ptr, space.ptr)
            logLine("HMS_HiAIAippParam_GetCscConfig result: $getCscResult")
        }
        val setChannelSwapResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetChannelSwapConfig(aippParam, false, false)
        logLine("HMS_HiAIAippParam_SetChannelSwapConfig result: $setChannelSwapResult")
        memScoped {
            val rbuv = alloc<BooleanVar>()
            val ax = alloc<BooleanVar>()
            val getChannelSwapResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetChannelSwapConfig(aippParam, rbuv.ptr, ax.ptr)
            logLine("HMS_HiAIAippParam_GetChannelSwapConfig result: $getChannelSwapResult")
        }
        val setSingleBatchResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetSingleBatchMultiCrop(aippParam, false)
        logLine("HMS_HiAIAippParam_SetSingleBatchMultiCrop result: $setSingleBatchResult")
        val setCropResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetCropConfig(aippParam, 0u, 0u, 0u, 256u, 256u)
        logLine("HMS_HiAIAippParam_SetCropConfig result: $setCropResult")
        memScoped {
            val sw = alloc<UIntVar>()
            val sh = alloc<UIntVar>()
            val cw = alloc<UIntVar>()
            val ch = alloc<UIntVar>()
            val getCropResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetCropConfig(aippParam, 0u, sw.ptr, sh.ptr, cw.ptr, ch.ptr)
            logLine("HMS_HiAIAippParam_GetCropConfig result: $getCropResult")
        }
        val setResizeResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetResizeConfig(aippParam, 0u, 224u, 224u)
        logLine("HMS_HiAIAippParam_SetResizeConfig result: $setResizeResult")
        memScoped {
            val rw = alloc<UIntVar>()
            val rh = alloc<UIntVar>()
            val getResizeResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetResizeConfig(aippParam, 0u, rw.ptr, rh.ptr)
            logLine("HMS_HiAIAippParam_GetResizeConfig result: $getResizeResult")
        }
        val setPadResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetPadConfig(aippParam, 0u, 0u, 0u, 0u, 0u)
        logLine("HMS_HiAIAippParam_SetPadConfig result: $setPadResult")
        memScoped {
            val left = alloc<UIntVar>()
            val right = alloc<UIntVar>()
            val top = alloc<UIntVar>()
            val bottom = alloc<UIntVar>()
            val getPadResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetPadConfig(aippParam, 0u, left.ptr, right.ptr, top.ptr, bottom.ptr)
            logLine("HMS_HiAIAippParam_GetPadConfig result: $getPadResult")
        }
        val setChannelPadResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetChannelPadding(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_SetChannelPadding result: $setChannelPadResult")
        val getChannelPadResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetChannelPadding(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_GetChannelPadding result: $getChannelPadResult")
        val setRotationResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetRotationAngle(aippParam, 0u, 0f)
        logLine("HMS_HiAIAippParam_SetRotationAngle result: $setRotationResult")
        memScoped {
            val angle = alloc<FloatVar>()
            val getRotationResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetRotationAngle(aippParam, 0u, angle.ptr)
            logLine("HMS_HiAIAippParam_GetRotationAngle result: $getRotationResult")
        }
        val setDtcMeanResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetDtcMeanPixel(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_SetDtcMeanPixel result: $setDtcMeanResult")
        val getDtcMeanResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetDtcMeanPixel(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_GetDtcMeanPixel result: $getDtcMeanResult")
        val setDtcMinResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetDtcMinPixel(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_SetDtcMinPixel result: $setDtcMinResult")
        val getDtcMinResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetDtcMinPixel(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_GetDtcMinPixel result: $getDtcMinResult")
        val setDtcVarResult = platform.CANNKit.CANN.HMS_HiAIAippParam_SetDtcVarReciPixel(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_SetDtcVarReciPixel result: $setDtcVarResult")
        val getDtcVarResult = platform.CANNKit.CANN.HMS_HiAIAippParam_GetDtcVarReciPixel(aippParam, 0u, null, 4u)
        logLine("HMS_HiAIAippParam_GetDtcVarReciPixel result: $getDtcVarResult")
        platform.CANNKit.CANN.HMS_HiAIAippParam_Destroy(null)
        logLine("HMS_HiAIAippParam_Destroy(null) called")
    }

    @Test
    fun testAippParamGetDataNull() {
        logLine("--- HMS_HiAIAippParam_GetData(null) ---")
        val result = platform.CANNKit.CANN.HMS_HiAIAippParam_GetData(null)
        logLine("HMS_HiAIAippParam_GetData(null) result: $result")
    }

    @Test
    fun testOptionsSetters() {
        logLine("--- HMS_HiAIOptions Set* (null compilation) ---")
        val r1 = platform.CANNKit.CANN.HMS_HiAIOptions_SetInputTensorShapes(null, null, 0uL)
        logLine("SetInputTensorShapes(null,null,0) result: $r1")
        val r2 = platform.CANNKit.CANN.HMS_HiAIOptions_SetFormatMode(null, platform.CANNKit.CANN.HIAI_FORMAT_MODE_NCHW)
        logLine("SetFormatMode(null,NCHW) result: $r2")
        val r3 = platform.CANNKit.CANN.HMS_HiAIOptions_SetDynamicShapeStatus(null, platform.CANNKit.CANN.HIAI_DYNAMIC_SHAPE_DISABLED)
        logLine("SetDynamicShapeStatus(null,DISABLED) result: $r3")
        val r4 = platform.CANNKit.CANN.HMS_HiAIOptions_SetDynamicShapeMaxCache(null, 1uL)
        logLine("SetDynamicShapeMaxCache(null,1) result: $r4")
        val r5 = platform.CANNKit.CANN.HMS_HiAIOptions_SetDynamicShapeCacheMode(null, platform.CANNKit.CANN.HIAI_DYNAMIC_SHAPE_CACHE_BUILT_MODEL)
        logLine("SetDynamicShapeCacheMode(null,CACHE_BUILT_MODEL) result: $r5")
        val r6 = platform.CANNKit.CANN.HMS_HiAIOptions_SetFallbackMode(null, platform.CANNKit.CANN.HIAI_FALLBACK_ENABLED)
        logLine("SetFallbackMode(null,ENABLED) result: $r6")
        val r7 = platform.CANNKit.CANN.HMS_HiAIOptions_SetDeviceMemoryReusePlan(null, platform.CANNKit.CANN.HIAI_DEVICE_MEMORY_REUSE_PLAN_UNSET)
        logLine("SetDeviceMemoryReusePlan(null,UNSET) result: $r7")
        val r8 = platform.CANNKit.CANN.HMS_HiAIOptions_SetTuningStrategy(null, platform.CANNKit.CANN.HIAI_TUNING_STRATEGY_OFF)
        logLine("SetTuningStrategy(null,OFF) result: $r8")
        val r9 = platform.CANNKit.CANN.HMS_HiAIOptions_SetQuantConfig(null, null, 0uL)
        logLine("SetQuantConfig(null,null,0) result: $r9")
        val r10 = platform.CANNKit.CANN.HMS_HiAIOptions_SetTuningMode(null, platform.CANNKit.CANN.HIAI_TUNING_MODE_UNSET)
        logLine("SetTuningMode(null,UNSET) result: $r10")
        val r11 = platform.CANNKit.CANN.HMS_HiAIOptions_SetTuningCacheDir(null, null)
        logLine("SetTuningCacheDir(null,null) result: $r11")
        val r12 = platform.CANNKit.CANN.HMS_HiAIOptions_SetBandMode(null, platform.CANNKit.CANN.HIAI_BANDMODE_UNSET)
        logLine("SetBandMode(null,UNSET) result: $r12")
        val r13 = platform.CANNKit.CANN.HMS_HiAIOptions_SetOperatorDeviceOrder(null, null, null, 0uL)
        logLine("SetOperatorDeviceOrder(null,null,null,0) result: $r13")
        val r14 = platform.CANNKit.CANN.HMS_HiAIOptions_SetModelDeviceOrder(null, null, 0uL)
        logLine("SetModelDeviceOrder(null,null,0) result: $r14")
        try {
            val r15 = platform.CANNKit.CANN.HMS_HiAIOptions_SetOmOptions(null, platform.CANNKit.CANN.HIAI_OM_TYPE_OFF, null)
            logLine("SetOmOptions(null,OM_TYPE_OFF,null) result: $r15 (API 18)")
        } catch (e: Throwable) {
            logLine("HMS_HiAIOptions_SetOmOptions (API 18) exception: $e")
        }
    }

    @Test
    fun testOptionsGetters() {
        logLine("--- HMS_HiAIOptions Get* ---")
        val shapeSize = platform.CANNKit.CANN.HMS_HiAIOptions_GetInputTensorShapeSize(null)
        logLine("GetInputTensorShapeSize(null) result: $shapeSize")
        val shape = platform.CANNKit.CANN.HMS_HiAIOptions_GetInputTensorShape(null, 0uL)
        logLine("GetInputTensorShape(null,0) result: $shape")
        val formatMode = platform.CANNKit.CANN.HMS_HiAIOptions_GetFormatMode(null)
        logLine("GetFormatMode(null) result: $formatMode")
        val dynamicShapeStatus = platform.CANNKit.CANN.HMS_HiAIOptions_GetDynamicShapeStatus(null)
        logLine("GetDynamicShapeStatus(null) result: $dynamicShapeStatus")
        val maxCache = platform.CANNKit.CANN.HMS_HiAIOptions_GetDynamicShapeMaxCache(null)
        logLine("GetDynamicShapeMaxCache(null) result: $maxCache")
        val cacheMode = platform.CANNKit.CANN.HMS_HiAIOptions_GetDynamicShapeCacheMode(null)
        logLine("GetDynamicShapeCacheMode(null) result: $cacheMode")
        val deviceCount = platform.CANNKit.CANN.HMS_HiAIOptions_GetOperatorDeviceCount(null, null)
        logLine("GetOperatorDeviceCount(null,null) result: $deviceCount")
        val modelDeviceCount = platform.CANNKit.CANN.HMS_HiAIOptions_GetModelDeviceCount(null)
        logLine("GetModelDeviceCount(null) result: $modelDeviceCount")
        val fallbackMode = platform.CANNKit.CANN.HMS_HiAIOptions_GetFallbackMode(null)
        logLine("GetFallbackMode(null) result: $fallbackMode")
        val memoryReusePlan = platform.CANNKit.CANN.HMS_HiAIOptions_GetDeviceMemoryReusePlan(null)
        logLine("GetDeviceMemoryReusePlan(null) result: $memoryReusePlan")
        val tuningStrategy = platform.CANNKit.CANN.HMS_HiAIOptions_GetTuningStrategy(null)
        logLine("GetTuningStrategy(null) result: $tuningStrategy")
        val quantConfigData = platform.CANNKit.CANN.HMS_HiAIOptions_GetQuantConfigData(null)
        logLine("GetQuantConfigData(null) result: $quantConfigData")
        val quantConfigSize = platform.CANNKit.CANN.HMS_HiAIOptions_GetQuantConfigSize(null)
        logLine("GetQuantConfigSize(null) result: $quantConfigSize")
        val tuningMode = platform.CANNKit.CANN.HMS_HiAIOptions_GetTuningMode(null)
        logLine("GetTuningMode(null) result: $tuningMode")
        val tuningCacheDir = platform.CANNKit.CANN.HMS_HiAIOptions_GetTuningCacheDir(null)
        logLine("GetTuningCacheDir(null) result: ${tuningCacheDir?.toKString()}")
        val bandMode = platform.CANNKit.CANN.HMS_HiAIOptions_GetBandMode(null)
        logLine("GetBandMode(null) result: $bandMode")
        val operatorDeviceOrder = platform.CANNKit.CANN.HMS_HiAIOptions_GetOperatorDeviceOrder(null, null)
        logLine("GetOperatorDeviceOrder(null,null) result: $operatorDeviceOrder")
        val modelDeviceOrder = platform.CANNKit.CANN.HMS_HiAIOptions_GetModelDeviceOrder(null)
        logLine("GetModelDeviceOrder(null) result: $modelDeviceOrder")
    }

    @Test
    fun testSingleOpTensorDescAndBuffer() {
        logLine("--- HMS_HiAISingleOpTensorDesc / Buffer ---")
        val dimCount = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_GetDimensionCount(null)
        logLine("HMS_HiAISingleOpTensorDesc_GetDimensionCount(null) result: $dimCount")
        val dimension = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_GetDimension(null, 0uL)
        logLine("HMS_HiAISingleOpTensorDesc_GetDimension(null,0) result: $dimension")
        val dataType = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_GetDataType(null)
        logLine("HMS_HiAISingleOpTensorDesc_GetDataType(null) result: $dataType")
        val format = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_GetFormat(null)
        logLine("HMS_HiAISingleOpTensorDesc_GetFormat(null) result: $format")
        val isVirtual = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_IsVirtual(null)
        logLine("HMS_HiAISingleOpTensorDesc_IsVirtual(null) result: $isVirtual")
        val byteSize = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_GetByteSize(null)
        logLine("HMS_HiAISingleOpTensorDesc_GetByteSize(null) result: $byteSize")
        val bufferSize = platform.CANNKit.CANN.HMS_HiAISingleOpBuffer_GetSize(null)
        logLine("HMS_HiAISingleOpBuffer_GetSize(null) result: $bufferSize")
        val bufferData = platform.CANNKit.CANN.HMS_HiAISingleOpBuffer_GetData(null)
        logLine("HMS_HiAISingleOpBuffer_GetData(null) result: $bufferData")
        val tensorDesc = platform.CANNKit.CANN.HMS_HiAISingleOpTensor_GetTensorDesc(null)
        logLine("HMS_HiAISingleOpTensor_GetTensorDesc(null) result: $tensorDesc")
        val tensorBuffer = platform.CANNKit.CANN.HMS_HiAISingleOpTensor_GetBuffer(null)
        logLine("HMS_HiAISingleOpTensor_GetBuffer(null) result: $tensorBuffer")
        val workspaceSize = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_GetWorkspaceSize(null)
        logLine("HMS_HiAISingleOpExecutor_GetWorkspaceSize(null) result: $workspaceSize")
    }

    @Test
    fun testSingleOpTensorDescCreateAndDestroy() {
        logLine("--- HMS_HiAISingleOpTensorDesc Create/Destroy ---")
        val descNull = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_Create(null, 0uL, platform.CANNKit.CANN.HIAI_SINGLEOP_DT_FLOAT, platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_NCHW, false)
        logLine("HMS_HiAISingleOpTensorDesc_Create(null,0,...) result: $descNull")
        val desc = platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_Create(null, 4uL, platform.CANNKit.CANN.HIAI_SINGLEOP_DT_FLOAT, platform.CANNKit.CANN.HIAI_SINGLEOP_FORMAT_NCHW, false)
        logLine("HMS_HiAISingleOpTensorDesc_Create(null,4,...) result: $desc")
        platform.CANNKit.CANN.HMS_HiAISingleOpTensorDesc_Destroy(null)
        logLine("HMS_HiAISingleOpTensorDesc_Destroy(null) called")
    }

    @Test
    fun testSingleOpBufferCreateAndDestroy() {
        logLine("--- HMS_HiAISingleOpBuffer Create/Destroy ---")
        val bufNull = platform.CANNKit.CANN.HMS_HiAISingleOpBuffer_Create(0uL)
        logLine("HMS_HiAISingleOpBuffer_Create(0) result: $bufNull")
        val buf = platform.CANNKit.CANN.HMS_HiAISingleOpBuffer_Create(64uL)
        logLine("HMS_HiAISingleOpBuffer_Create(64) result: $buf")
        val destroyResult = platform.CANNKit.CANN.HMS_HiAISingleOpBuffer_Destroy(null)
        logLine("HMS_HiAISingleOpBuffer_Destroy(null) result: $destroyResult")
    }

    @Test
    fun testSingleOpTensorCreateAndDestroy() {
        logLine("--- HMS_HiAISingleOpTensor Create/Destroy ---")
        val t1 = platform.CANNKit.CANN.HMS_HiAISingleOpTensor_CreateFromTensorDesc(null)
        logLine("HMS_HiAISingleOpTensor_CreateFromTensorDesc(null) result: $t1")
        val t2 = platform.CANNKit.CANN.HMS_HiAISingleOpTensor_CreateFromSingleOpBuffer(null, null, 0uL)
        logLine("HMS_HiAISingleOpTensor_CreateFromSingleOpBuffer(null,null,0) result: $t2")
        val t3 = platform.CANNKit.CANN.HMS_HiAISingleOpTensor_CreateFromConst(null, null, 0uL)
        logLine("HMS_HiAISingleOpTensor_CreateFromConst(null,null,0) result: $t3")
        platform.CANNKit.CANN.HMS_HiAISingleOpTensor_Destroy(null)
        logLine("HMS_HiAISingleOpTensor_Destroy(null) called")
    }

    @Test
    fun testSingleOpOptionsCreateAndDestroy() {
        logLine("--- HMS_HiAISingleOpOptions Create/Destroy ---")
        val options = platform.CANNKit.CANN.HMS_HiAISingleOpOptions_Create()
        logLine("HMS_HiAISingleOpOptions_Create result: $options")
        platform.CANNKit.CANN.HMS_HiAISingleOpOptions_Destroy(null)
        logLine("HMS_HiAISingleOpOptions_Destroy(null) called")
    }

    @Test
    fun testSingleOpDescriptorCreateAndDestroy() {
        logLine("--- HMS_HiAISingleOpDescriptor Create/Destroy ---")
        memScoped {
            val convParam = cValue<platform.CANNKit.CANN.HiAISingleOpDescriptor_ConvolutionParam> {
                convMode = platform.CANNKit.CANN.HIAI_SINGLEOP_CONV_MODE_COMMON
                strides[0] = 1L
                strides[1] = 1L
                dilations[0] = 1L
                dilations[1] = 1L
                pads[0] = 0L
                pads[1] = 0L
                pads[2] = 0L
                pads[3] = 0L
                groups = 1L
                padMode = platform.CANNKit.CANN.HIAI_SINGLEOP_PAD_MODE_SPECIFIC
            }
            val convDesc = platform.CANNKit.CANN.HMS_HiAISingleOpDescriptor_CreateConvolution(convParam)
            logLine("HMS_HiAISingleOpDescriptor_CreateConvolution result: $convDesc")
        }
        val actDesc = platform.CANNKit.CANN.HMS_HiAISingleOpDescriptor_CreateActivation(platform.CANNKit.CANN.HIAI_SINGLEOP_ACTIVATION_TYPE_RELU, 0f)
        logLine("HMS_HiAISingleOpDescriptor_CreateActivation(RELU,0) result: $actDesc")
        platform.CANNKit.CANN.HMS_HiAISingleOpDescriptor_Destroy(null)
        logLine("HMS_HiAISingleOpDescriptor_Destroy(null) called")
    }


    @Test
    fun testSingleOpExecutorPreCheckAndCreateAndOthers() {
        logLine("--- HMS_HiAISingleOpExecutor PreCheck/Create/Update/Init/Execute/Destroy ---")
        memScoped {
            val convParam = cValue<platform.CANNKit.CANN.HiAI_SingleOpExecutorConvolutionParam> {
                options = null
                opDesc = null
                input = null
                output = null
                filter = null
                bias = null
            }
            val preCheckConv = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_PreCheckConvolution(convParam)
            logLine("HMS_HiAISingleOpExecutor_PreCheckConvolution(null param) result: $preCheckConv")
        }
        memScoped {
            val fusedParam = cValue<platform.CANNKit.CANN.HiAI_SingleOpExecutorFusedConvolutionActivationParam> {
                options = null
                convOpDesc = null
                actOpDesc = null
                input = null
                output = null
                filter = null
                bias = null
            }
            val preCheckFused = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_PreCheckFusedConvolutionActivation(fusedParam)
            logLine("HMS_HiAISingleOpExecutor_PreCheckFusedConvolutionActivation(null param) result: $preCheckFused")
        }
        memScoped {
            val convParam = cValue<platform.CANNKit.CANN.HiAI_SingleOpExecutorConvolutionParam> {
                options = null
                opDesc = null
                input = null
                output = null
                filter = null
                bias = null
            }
            val execConv = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_CreateConvolution(convParam)
            logLine("HMS_HiAISingleOpExecutor_CreateConvolution(null param) result: $execConv")
        }
        memScoped {
            val fusedParam = cValue<platform.CANNKit.CANN.HiAI_SingleOpExecutorFusedConvolutionActivationParam> {
                options = null
                convOpDesc = null
                actOpDesc = null
                input = null
                output = null
                filter = null
                bias = null
            }
            val execFused = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_CreateFusedConvolutionActivation(fusedParam)
            logLine("HMS_HiAISingleOpExecutor_CreateFusedConvolutionActivation(null param) result: $execFused")
        }
        val updateResult = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_UpdateOutputTensorDesc(null, 0u, null)
        logLine("HMS_HiAISingleOpExecutor_UpdateOutputTensorDesc(null,0,null) result: $updateResult")
        val initResult = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_Init(null, null, 0uL)
        logLine("HMS_HiAISingleOpExecutor_Init(null,null,0) result: $initResult")
        val executeResult = platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_Execute(null, null, 0, null, 0)
        logLine("HMS_HiAISingleOpExecutor_Execute(null,null,0,null,0) result: $executeResult")
        platform.CANNKit.CANN.HMS_HiAISingleOpExecutor_Destroy(null)
        logLine("HMS_HiAISingleOpExecutor_Destroy(null) called")
    }
}
