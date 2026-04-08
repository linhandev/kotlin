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
import platform.NeuralNetworkRuntimeKit.NeuralNetworkRuntime.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NeuralNetworkRuntimeTest {

    fun logLine(msg: String) = println("[stdout] NeuralNetworkRuntimeTest $msg")

    // ==================== 枚举：OH_NN_ReturnCode ====================
    @Test
    fun testEnum_OH_NN_ReturnCode() {
        assertEquals(OH_NN_SUCCESS.toInt(), 0)
        assertEquals(OH_NN_FAILED.toInt(), 1)
        assertEquals(OH_NN_INVALID_PARAMETER.toInt(), 2)
        assertEquals(OH_NN_MEMORY_ERROR.toInt(), 3)
        assertEquals(OH_NN_OPERATION_FORBIDDEN.toInt(), 4)
        assertEquals(OH_NN_NULL_PTR.toInt(), 5)
        assertEquals(OH_NN_INVALID_FILE.toInt(), 6)
        assertEquals(OH_NN_UNAVALIDABLE_DEVICE.toInt(), 7)
        assertEquals(OH_NN_INVALID_PATH.toInt(), 8)
        assertEquals(OH_NN_TIMEOUT.toInt(), 9)
        assertEquals(OH_NN_UNSUPPORTED.toInt(), 10)
        assertEquals(OH_NN_CONNECTION_EXCEPTION.toInt(), 11)
        assertEquals(OH_NN_SAVE_CACHE_EXCEPTION.toInt(), 12)
        assertEquals(OH_NN_DYNAMIC_SHAPE.toInt(), 13)
        assertEquals(OH_NN_UNAVAILABLE_DEVICE.toInt(), 14)
        logLine("OH_NN_ReturnCode passed")
    }

    @Test
    fun testEnum_OH_NN_PerformanceMode() {
        assertEquals(OH_NN_PERFORMANCE_NONE.toInt(), 0)
        assertEquals(OH_NN_PERFORMANCE_LOW.toInt(), 1)
        assertEquals(OH_NN_PERFORMANCE_MEDIUM.toInt(), 2)
        assertEquals(OH_NN_PERFORMANCE_HIGH.toInt(), 3)
        assertEquals(OH_NN_PERFORMANCE_EXTREME.toInt(), 4)
        logLine("OH_NN_PerformanceMode passed")
    }

    @Test
    fun testEnum_OH_NN_Priority() {
        assertEquals(OH_NN_PRIORITY_NONE.toInt(), 0)
        assertEquals(OH_NN_PRIORITY_LOW.toInt(), 1)
        assertEquals(OH_NN_PRIORITY_MEDIUM.toInt(), 2)
        assertEquals(OH_NN_PRIORITY_HIGH.toInt(), 3)
        logLine("OH_NN_Priority passed")
    }

    @Test
    fun testEnum_OH_NN_FusedType() {
        assertEquals(OH_NN_FUSED_NONE.toInt(), 0)
        assertEquals(OH_NN_FUSED_RELU.toInt(), 1)
        assertEquals(OH_NN_FUSED_RELU6.toInt(), 2)
        logLine("OH_NN_FusedType passed")
    }

    @Test
    fun testEnum_OH_NN_Format() {
        assertEquals(OH_NN_FORMAT_NONE.toInt(), 0)
        assertEquals(OH_NN_FORMAT_NCHW.toInt(), 1)
        assertEquals(OH_NN_FORMAT_NHWC.toInt(), 2)
        assertEquals(OH_NN_FORMAT_ND.toInt(), 3)
        logLine("OH_NN_Format passed")
    }

    @Test
    fun testEnum_OH_NN_DeviceType() {
        assertEquals(OH_NN_OTHERS.toInt(), 0)
        assertEquals(OH_NN_CPU.toInt(), 1)
        assertEquals(OH_NN_GPU.toInt(), 2)
        assertEquals(OH_NN_ACCELERATOR.toInt(), 3)
        logLine("OH_NN_DeviceType passed")
    }

    @Test
    fun testEnum_OH_NN_DataType() {
        assertEquals(OH_NN_UNKNOWN.toInt(), 0)
        assertEquals(OH_NN_BOOL.toInt(), 1)
        assertEquals(OH_NN_INT8.toInt(), 2)
        assertEquals(OH_NN_INT16.toInt(), 3)
        assertEquals(OH_NN_INT32.toInt(), 4)
        assertEquals(OH_NN_INT64.toInt(), 5)
        assertEquals(OH_NN_UINT8.toInt(), 6)
        assertEquals(OH_NN_UINT16.toInt(), 7)
        assertEquals(OH_NN_UINT32.toInt(), 8)
        assertEquals(OH_NN_UINT64.toInt(), 9)
        assertEquals(OH_NN_FLOAT16.toInt(), 10)
        assertEquals(OH_NN_FLOAT32.toInt(), 11)
        assertEquals(OH_NN_FLOAT64.toInt(), 12)
        logLine("OH_NN_DataType passed")
    }

    @Test
    fun testEnum_OH_NN_OperationType() {
        assertEquals(OH_NN_OPS_ADD.toInt(), 1)
        assertEquals(OH_NN_OPS_AVG_POOL.toInt(), 2)
        assertEquals(OH_NN_OPS_BATCH_NORM.toInt(), 3)
        assertEquals(OH_NN_OPS_BATCH_TO_SPACE_ND.toInt(), 4)
        assertEquals(OH_NN_OPS_BIAS_ADD.toInt(), 5)
        assertEquals(OH_NN_OPS_CAST.toInt(), 6)
        assertEquals(OH_NN_OPS_CONCAT.toInt(), 7)
        assertEquals(OH_NN_OPS_CONV2D.toInt(), 8)
        assertEquals(OH_NN_OPS_CONV2D_TRANSPOSE.toInt(), 9)
        assertEquals(OH_NN_OPS_DEPTHWISE_CONV2D_NATIVE.toInt(), 10)
        assertEquals(OH_NN_OPS_DIV.toInt(), 11)
        assertEquals(OH_NN_OPS_ELTWISE.toInt(), 12)
        assertEquals(OH_NN_OPS_EXPAND_DIMS.toInt(), 13)
        assertEquals(OH_NN_OPS_FILL.toInt(), 14)
        assertEquals(OH_NN_OPS_FULL_CONNECTION.toInt(), 15)
        assertEquals(OH_NN_OPS_GATHER.toInt(), 16)
        assertEquals(OH_NN_OPS_HSWISH.toInt(), 17)
        assertEquals(OH_NN_OPS_LESS_EQUAL.toInt(), 18)
        assertEquals(OH_NN_OPS_MATMUL.toInt(), 19)
        assertEquals(OH_NN_OPS_MAXIMUM.toInt(), 20)
        assertEquals(OH_NN_OPS_MAX_POOL.toInt(), 21)
        assertEquals(OH_NN_OPS_MUL.toInt(), 22)
        assertEquals(OH_NN_OPS_ONE_HOT.toInt(), 23)
        assertEquals(OH_NN_OPS_PAD.toInt(), 24)
        assertEquals(OH_NN_OPS_POW.toInt(), 25)
        assertEquals(OH_NN_OPS_SCALE.toInt(), 26)
        assertEquals(OH_NN_OPS_SHAPE.toInt(), 27)
        assertEquals(OH_NN_OPS_SIGMOID.toInt(), 28)
        assertEquals(OH_NN_OPS_SLICE.toInt(), 29)
        assertEquals(OH_NN_OPS_SOFTMAX.toInt(), 30)
        assertEquals(OH_NN_OPS_SPACE_TO_BATCH_ND.toInt(), 31)
        assertEquals(OH_NN_OPS_SPLIT.toInt(), 32)
        assertEquals(OH_NN_OPS_SQRT.toInt(), 33)
        assertEquals(OH_NN_OPS_SQUARED_DIFFERENCE.toInt(), 34)
        assertEquals(OH_NN_OPS_SQUEEZE.toInt(), 35)
        assertEquals(OH_NN_OPS_STACK.toInt(), 36)
        assertEquals(OH_NN_OPS_STRIDED_SLICE.toInt(), 37)
        assertEquals(OH_NN_OPS_SUB.toInt(), 38)
        assertEquals(OH_NN_OPS_TANH.toInt(), 39)
        assertEquals(OH_NN_OPS_TILE.toInt(), 40)
        assertEquals(OH_NN_OPS_TRANSPOSE.toInt(), 41)
        assertEquals(OH_NN_OPS_REDUCE_MEAN.toInt(), 42)
        assertEquals(OH_NN_OPS_RESIZE_BILINEAR.toInt(), 43)
        assertEquals(OH_NN_OPS_RSQRT.toInt(), 44)
        assertEquals(OH_NN_OPS_RESHAPE.toInt(), 45)
        assertEquals(OH_NN_OPS_PRELU.toInt(), 46)
        assertEquals(OH_NN_OPS_RELU.toInt(), 47)
        assertEquals(OH_NN_OPS_RELU6.toInt(), 48)
        assertEquals(OH_NN_OPS_LAYER_NORM.toInt(), 49)
        assertEquals(OH_NN_OPS_REDUCE_PROD.toInt(), 50)
        assertEquals(OH_NN_OPS_REDUCE_ALL.toInt(), 51)
        assertEquals(OH_NN_OPS_QUANT_DTYPE_CAST.toInt(), 52)
        assertEquals(OH_NN_OPS_TOP_K.toInt(), 53)
        assertEquals(OH_NN_OPS_ARG_MAX.toInt(), 54)
        assertEquals(OH_NN_OPS_UNSQUEEZE.toInt(), 55)
        assertEquals(OH_NN_OPS_GELU.toInt(), 56)
        assertEquals(OH_NN_OPS_UNSTACK.toInt(), 57)
        assertEquals(OH_NN_OPS_ABS.toInt(), 58)
        assertEquals(OH_NN_OPS_ERF.toInt(), 59)
        assertEquals(OH_NN_OPS_EXP.toInt(), 60)
        assertEquals(OH_NN_OPS_LESS.toInt(), 61)
        assertEquals(OH_NN_OPS_SELECT.toInt(), 62)
        assertEquals(OH_NN_OPS_SQUARE.toInt(), 63)
        assertEquals(OH_NN_OPS_FLATTEN.toInt(), 64)
        assertEquals(OH_NN_OPS_DEPTH_TO_SPACE.toInt(), 65)
        assertEquals(OH_NN_OPS_RANGE.toInt(), 66)
        assertEquals(OH_NN_OPS_INSTANCE_NORM.toInt(), 67)
        assertEquals(OH_NN_OPS_CONSTANT_OF_SHAPE.toInt(), 68)
        assertEquals(OH_NN_OPS_BROADCAST_TO.toInt(), 69)
        assertEquals(OH_NN_OPS_EQUAL.toInt(), 70)
        assertEquals(OH_NN_OPS_GREATER.toInt(), 71)
        assertEquals(OH_NN_OPS_NOT_EQUAL.toInt(), 72)
        assertEquals(OH_NN_OPS_GREATER_EQUAL.toInt(), 73)
        assertEquals(OH_NN_OPS_LEAKY_RELU.toInt(), 74)
        assertEquals(OH_NN_OPS_LSTM.toInt(), 75)
        assertEquals(OH_NN_OPS_CLIP.toInt(), 76)
        assertEquals(OH_NN_OPS_ALL.toInt(), 77)
        assertEquals(OH_NN_OPS_ASSERT.toInt(), 78)
        assertEquals(OH_NN_OPS_COS.toInt(), 79)
        assertEquals(OH_NN_OPS_LOG.toInt(), 80)
        assertEquals(OH_NN_OPS_LOGICAL_AND.toInt(), 81)
        assertEquals(OH_NN_OPS_LOGICAL_NOT.toInt(), 82)
        assertEquals(OH_NN_OPS_MOD.toInt(), 83)
        assertEquals(OH_NN_OPS_NEG.toInt(), 84)
        assertEquals(OH_NN_OPS_RECIPROCAL.toInt(), 85)
        assertEquals(OH_NN_OPS_SIN.toInt(), 86)
        assertEquals(OH_NN_OPS_WHERE.toInt(), 87)
        assertEquals(OH_NN_OPS_SPARSE_TO_DENSE.toInt(), 88)
        assertEquals(OH_NN_OPS_LOGICAL_OR.toInt(), 89)
        assertEquals(OH_NN_OPS_CEIL.toInt(), 90)
        assertEquals(OH_NN_OPS_CROP.toInt(), 91)
        assertEquals(OH_NN_OPS_DETECTION_POST_PROCESS.toInt(), 92)
        assertEquals(OH_NN_OPS_FLOOR.toInt(), 93)
        assertEquals(OH_NN_OPS_L2_NORMALIZE.toInt(), 94)
        assertEquals(OH_NN_OPS_LOG_SOFTMAX.toInt(), 95)
        assertEquals(OH_NN_OPS_LRN.toInt(), 96)
        assertEquals(OH_NN_OPS_MINIMUM.toInt(), 97)
        assertEquals(OH_NN_OPS_RANK.toInt(), 98)
        assertEquals(OH_NN_OPS_REDUCE_MAX.toInt(), 99)
        assertEquals(OH_NN_OPS_REDUCE_MIN.toInt(), 100)
        assertEquals(OH_NN_OPS_REDUCE_SUM.toInt(), 101)
        assertEquals(OH_NN_OPS_ROUND.toInt(), 102)
        assertEquals(OH_NN_OPS_SCATTER_ND.toInt(), 103)
        assertEquals(OH_NN_OPS_SPACE_TO_DEPTH.toInt(), 104)
        assertEquals(OH_NN_OPS_SWISH.toInt(), 105)
        assertEquals(OH_NN_OPS_REDUCE_L2.toInt(), 106)
        assertEquals(OH_NN_OPS_HARD_SIGMOID.toInt(), 107)
        assertEquals(OH_NN_OPS_GATHER_ND.toInt(), 108)
        logLine("OH_NN_OperationType passed")
    }

    @Test
    fun testEnum_OH_NN_TensorType() {
        assertEquals(OH_NN_TENSOR.toInt(), 0)
        assertEquals(OH_NN_ADD_ACTIVATIONTYPE.toInt(), 1)
        assertEquals(OH_NN_AVG_POOL_KERNEL_SIZE.toInt(), 2)
        assertEquals(OH_NN_AVG_POOL_STRIDE.toInt(), 3)
        assertEquals(OH_NN_AVG_POOL_PAD_MODE.toInt(), 4)
        assertEquals(OH_NN_AVG_POOL_PAD.toInt(), 5)
        assertEquals(OH_NN_AVG_POOL_ACTIVATION_TYPE.toInt(), 6)
        assertEquals(OH_NN_BATCH_NORM_EPSILON.toInt(), 7)
        assertEquals(OH_NN_BATCH_TO_SPACE_ND_BLOCKSIZE.toInt(), 8)
        assertEquals(OH_NN_BATCH_TO_SPACE_ND_CROPS.toInt(), 9)
        assertEquals(OH_NN_CONCAT_AXIS.toInt(), 10)
        assertEquals(OH_NN_CONV2D_STRIDES.toInt(), 11)
        assertEquals(OH_NN_CONV2D_PAD.toInt(), 12)
        assertEquals(OH_NN_CONV2D_DILATION.toInt(), 13)
        assertEquals(OH_NN_CONV2D_PAD_MODE.toInt(), 14)
        assertEquals(OH_NN_CONV2D_ACTIVATION_TYPE.toInt(), 15)
        assertEquals(OH_NN_CONV2D_GROUP.toInt(), 16)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_STRIDES.toInt(), 17)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_PAD.toInt(), 18)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_DILATION.toInt(), 19)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_OUTPUT_PADDINGS.toInt(), 20)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_PAD_MODE.toInt(), 21)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_ACTIVATION_TYPE.toInt(), 22)
        assertEquals(OH_NN_CONV2D_TRANSPOSE_GROUP.toInt(), 23)
        assertEquals(OH_NN_DEPTHWISE_CONV2D_NATIVE_STRIDES.toInt(), 24)
        assertEquals(OH_NN_DEPTHWISE_CONV2D_NATIVE_PAD.toInt(), 25)
        assertEquals(OH_NN_DEPTHWISE_CONV2D_NATIVE_DILATION.toInt(), 26)
        assertEquals(OH_NN_DEPTHWISE_CONV2D_NATIVE_PAD_MODE.toInt(), 27)
        assertEquals(OH_NN_DEPTHWISE_CONV2D_NATIVE_ACTIVATION_TYPE.toInt(), 28)
        assertEquals(OH_NN_DIV_ACTIVATIONTYPE.toInt(), 29)
        assertEquals(OH_NN_ELTWISE_MODE.toInt(), 30)
        assertEquals(OH_NN_FULL_CONNECTION_AXIS.toInt(), 31)
        assertEquals(OH_NN_FULL_CONNECTION_ACTIVATIONTYPE.toInt(), 32)
        assertEquals(OH_NN_MATMUL_TRANSPOSE_A.toInt(), 33)
        assertEquals(OH_NN_MATMUL_TRANSPOSE_B.toInt(), 34)
        assertEquals(OH_NN_MATMUL_ACTIVATION_TYPE.toInt(), 35)
        assertEquals(OH_NN_MAX_POOL_KERNEL_SIZE.toInt(), 36)
        assertEquals(OH_NN_MAX_POOL_STRIDE.toInt(), 37)
        assertEquals(OH_NN_MAX_POOL_PAD_MODE.toInt(), 38)
        assertEquals(OH_NN_MAX_POOL_PAD.toInt(), 39)
        assertEquals(OH_NN_MAX_POOL_ACTIVATION_TYPE.toInt(), 40)
        assertEquals(OH_NN_MUL_ACTIVATION_TYPE.toInt(), 41)
        assertEquals(OH_NN_ONE_HOT_AXIS.toInt(), 42)
        assertEquals(OH_NN_PAD_CONSTANT_VALUE.toInt(), 43)
        assertEquals(OH_NN_SCALE_ACTIVATIONTYPE.toInt(), 44)
        assertEquals(OH_NN_SCALE_AXIS.toInt(), 45)
        assertEquals(OH_NN_SOFTMAX_AXIS.toInt(), 46)
        assertEquals(OH_NN_SPACE_TO_BATCH_ND_BLOCK_SHAPE.toInt(), 47)
        assertEquals(OH_NN_SPACE_TO_BATCH_ND_PADDINGS.toInt(), 48)
        assertEquals(OH_NN_SPLIT_AXIS.toInt(), 49)
        assertEquals(OH_NN_SPLIT_OUTPUT_NUM.toInt(), 50)
        assertEquals(OH_NN_SPLIT_SIZE_SPLITS.toInt(), 51)
        assertEquals(OH_NN_SQUEEZE_AXIS.toInt(), 52)
        assertEquals(OH_NN_STACK_AXIS.toInt(), 53)
        assertEquals(OH_NN_STRIDED_SLICE_BEGIN_MASK.toInt(), 54)
        assertEquals(OH_NN_STRIDED_SLICE_END_MASK.toInt(), 55)
        assertEquals(OH_NN_STRIDED_SLICE_ELLIPSIS_MASK.toInt(), 56)
        assertEquals(OH_NN_STRIDED_SLICE_NEW_AXIS_MASK.toInt(), 57)
        assertEquals(OH_NN_STRIDED_SLICE_SHRINK_AXIS_MASK.toInt(), 58)
        assertEquals(OH_NN_SUB_ACTIVATIONTYPE.toInt(), 59)
        assertEquals(OH_NN_REDUCE_MEAN_KEEP_DIMS.toInt(), 60)
        assertEquals(OH_NN_RESIZE_BILINEAR_NEW_HEIGHT.toInt(), 61)
        assertEquals(OH_NN_RESIZE_BILINEAR_NEW_WIDTH.toInt(), 62)
        assertEquals(OH_NN_RESIZE_BILINEAR_PRESERVE_ASPECT_RATIO.toInt(), 63)
        assertEquals(OH_NN_RESIZE_BILINEAR_COORDINATE_TRANSFORM_MODE.toInt(), 64)
        assertEquals(OH_NN_RESIZE_BILINEAR_EXCLUDE_OUTSIDE.toInt(), 65)
        assertEquals(OH_NN_LAYER_NORM_BEGIN_NORM_AXIS.toInt(), 66)
        assertEquals(OH_NN_LAYER_NORM_EPSILON.toInt(), 67)
        assertEquals(OH_NN_LAYER_NORM_BEGIN_PARAM_AXIS.toInt(), 68)
        assertEquals(OH_NN_LAYER_NORM_ELEMENTWISE_AFFINE.toInt(), 69)
        assertEquals(OH_NN_REDUCE_PROD_KEEP_DIMS.toInt(), 70)
        assertEquals(OH_NN_REDUCE_ALL_KEEP_DIMS.toInt(), 71)
        assertEquals(OH_NN_QUANT_DTYPE_CAST_SRC_T.toInt(), 72)
        assertEquals(OH_NN_QUANT_DTYPE_CAST_DST_T.toInt(), 73)
        assertEquals(OH_NN_TOP_K_SORTED.toInt(), 74)
        assertEquals(OH_NN_ARG_MAX_AXIS.toInt(), 75)
        assertEquals(OH_NN_ARG_MAX_KEEPDIMS.toInt(), 76)
        assertEquals(OH_NN_UNSQUEEZE_AXIS.toInt(), 77)
        assertEquals(OH_NN_UNSTACK_AXIS.toInt(), 78)
        assertEquals(OH_NN_FLATTEN_AXIS.toInt(), 79)
        assertEquals(OH_NN_DEPTH_TO_SPACE_BLOCK_SIZE.toInt(), 80)
        assertEquals(OH_NN_DEPTH_TO_SPACE_MODE.toInt(), 81)
        assertEquals(OH_NN_RANGE_START.toInt(), 82)
        assertEquals(OH_NN_RANGE_LIMIT.toInt(), 83)
        assertEquals(OH_NN_RANGE_DELTA.toInt(), 84)
        assertEquals(OH_NN_CONSTANT_OF_SHAPE_DATA_TYPE.toInt(), 85)
        assertEquals(OH_NN_CONSTANT_OF_SHAPE_VALUE.toInt(), 86)
        assertEquals(OH_NN_BROADCAST_TO_SHAPE.toInt(), 87)
        assertEquals(OH_NN_INSTANCE_NORM_EPSILON.toInt(), 88)
        assertEquals(OH_NN_EXP_BASE.toInt(), 89)
        assertEquals(OH_NN_EXP_SCALE.toInt(), 90)
        assertEquals(OH_NN_EXP_SHIFT.toInt(), 91)
        assertEquals(OH_NN_LEAKY_RELU_NEGATIVE_SLOPE.toInt(), 92)
        assertEquals(OH_NN_LSTM_BIDIRECTIONAL.toInt(), 93)
        assertEquals(OH_NN_LSTM_HAS_BIAS.toInt(), 94)
        assertEquals(OH_NN_LSTM_INPUT_SIZE.toInt(), 95)
        assertEquals(OH_NN_LSTM_HIDDEN_SIZE.toInt(), 96)
        assertEquals(OH_NN_LSTM_NUM_LAYERS.toInt(), 97)
        assertEquals(OH_NN_LSTM_NUM_DIRECTIONS.toInt(), 98)
        assertEquals(OH_NN_LSTM_DROPOUT.toInt(), 99)
        assertEquals(OH_NN_LSTM_ZONEOUT_CELL.toInt(), 100)
        assertEquals(OH_NN_LSTM_ZONEOUT_HIDDEN.toInt(), 101)
        assertEquals(OH_NN_LSTM_PROJ_SIZE.toInt(), 102)
        assertEquals(OH_NN_CLIP_MAX.toInt(), 103)
        assertEquals(OH_NN_CLIP_MIN.toInt(), 104)
        assertEquals(OH_NN_ALL_KEEP_DIMS.toInt(), 105)
        assertEquals(OH_NN_ASSERT_SUMMARIZE.toInt(), 106)
        assertEquals(OH_NN_POW_SCALE.toInt(), 107)
        assertEquals(OH_NN_POW_SHIFT.toInt(), 108)
        assertEquals(OH_NN_AVG_POOL_ROUND_MODE.toInt(), 109)
        assertEquals(OH_NN_AVG_POOL_GLOBAL.toInt(), 110)
        assertEquals(OH_NN_FULL_CONNECTION_HAS_BIAS.toInt(), 111)
        assertEquals(OH_NN_FULL_CONNECTION_USE_AXIS.toInt(), 112)
        assertEquals(OH_NN_GELU_APPROXIMATE.toInt(), 113)
        assertEquals(OH_NN_MAX_POOL_ROUND_MODE.toInt(), 114)
        assertEquals(OH_NN_MAX_POOL_GLOBAL.toInt(), 115)
        assertEquals(OH_NN_PAD_PADDING_MODE.toInt(), 116)
        assertEquals(OH_NN_REDUCE_MEAN_REDUCE_TO_END.toInt(), 117)
        assertEquals(OH_NN_REDUCE_MEAN_COEFF.toInt(), 118)
        assertEquals(OH_NN_REDUCE_PROD_REDUCE_TO_END.toInt(), 119)
        assertEquals(OH_NN_REDUCE_PROD_COEFF.toInt(), 120)
        assertEquals(OH_NN_REDUCE_ALL_REDUCE_TO_END.toInt(), 121)
        assertEquals(OH_NN_REDUCE_ALL_COEFF.toInt(), 122)
        assertEquals(OH_NN_TOP_K_AXIS.toInt(), 123)
        assertEquals(OH_NN_ARG_MAX_TOP_K.toInt(), 124)
        assertEquals(OH_NN_ARG_MAX_OUT_MAX_VALUE.toInt(), 125)
        assertEquals(OH_NN_QUANT_DTYPE_CAST_AXIS.toInt(), 126)
        assertEquals(OH_NN_SLICE_AXES.toInt(), 127)
        assertEquals(OH_NN_TILE_DIMS.toInt(), 128)
        assertEquals(OH_NN_CROP_AXIS.toInt(), 129)
        assertEquals(OH_NN_CROP_OFFSET.toInt(), 130)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_INPUT_SIZE.toInt(), 131)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_SCALE.toInt(), 132)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_NMS_IOU_THRESHOLD.toInt(), 133)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_NMS_SCORE_THRESHOLD.toInt(), 134)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_MAX_DETECTIONS.toInt(), 135)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_DETECTIONS_PER_CLASS.toInt(), 136)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_MAX_CLASSES_PER_DETECTION.toInt(), 137)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_NUM_CLASSES.toInt(), 138)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_USE_REGULAR_NMS.toInt(), 139)
        assertEquals(OH_NN_DETECTION_POST_PROCESS_OUT_QUANTIZED.toInt(), 140)
        assertEquals(OH_NN_L2_NORMALIZE_AXIS.toInt(), 141)
        assertEquals(OH_NN_L2_NORMALIZE_EPSILON.toInt(), 142)
        assertEquals(OH_NN_L2_NORMALIZE_ACTIVATION_TYPE.toInt(), 143)
        assertEquals(OH_NN_LOG_SOFTMAX_AXIS.toInt(), 144)
        assertEquals(OH_NN_LRN_DEPTH_RADIUS.toInt(), 145)
        assertEquals(OH_NN_LRN_BIAS.toInt(), 146)
        assertEquals(OH_NN_LRN_ALPHA.toInt(), 147)
        assertEquals(OH_NN_LRN_BETA.toInt(), 148)
        assertEquals(OH_NN_LRN_NORM_REGION.toInt(), 149)
        assertEquals(OH_NN_SPACE_TO_DEPTH_BLOCK_SIZE.toInt(), 150)
        assertEquals(OH_NN_REDUCE_MAX_KEEP_DIMS.toInt(), 151)
        assertEquals(OH_NN_REDUCE_MAX_REDUCE_TO_END.toInt(), 152)
        assertEquals(OH_NN_REDUCE_MAX_COEFF.toInt(), 153)
        assertEquals(OH_NN_REDUCE_MIN_KEEP_DIMS.toInt(), 154)
        assertEquals(OH_NN_REDUCE_MIN_REDUCE_TO_END.toInt(), 155)
        assertEquals(OH_NN_REDUCE_MIN_COEFF.toInt(), 156)
        assertEquals(OH_NN_REDUCE_SUM_KEEP_DIMS.toInt(), 157)
        assertEquals(OH_NN_REDUCE_SUM_REDUCE_TO_END.toInt(), 158)
        assertEquals(OH_NN_REDUCE_SUM_COEFF.toInt(), 159)
        assertEquals(OH_NN_REDUCE_L2_KEEP_DIMS.toInt(), 160)
        assertEquals(OH_NN_REDUCE_L2_REDUCE_TO_END.toInt(), 161)
        assertEquals(OH_NN_REDUCE_L2_COEFF.toInt(), 162)
        logLine("OH_NN_TensorType passed")
    }

    // ==================== Model ====================

    @Test
    fun testOH_NNModel_Construct() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            logLine("OH_NNModel_Construct=$model")
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_Destroy() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
            logLine("OH_NNModel_Destroy=called")
        }
    }

    @Test
    fun testOH_NNModel_AddTensorToModel() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1, 2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 2uL)
            val ret = OH_NNModel_AddTensorToModel(model, desc)
            assertNotNull(ret)
            logLine("OH_NNModel_AddTensorToModel=$ret")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_Finish() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ret = OH_NNModel_Finish(model)
            assertNotNull(ret)
            logLine("OH_NNModel_Finish=$ret")
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_SetTensorData() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNModel_AddTensorToModel(model, desc)
            val ret = OH_NNModel_SetTensorData(model, 0u, null, 0u)
            assertNotNull(ret)
            logLine("OH_NNModel_SetTensorData=$ret")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_SetTensorQuantParams() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNModel_AddTensorToModel(model, desc)
            val q = OH_NNQuantParam_Create()
            assertNotNull(q)
            val scales = doubleArrayOf(1.0)
            OH_NNQuantParam_SetScales(q, scales.refTo(0), 1u)
            OH_NNModel_SetTensorQuantParams(model, 0u, q)
            logLine("OH_NNModel_SetTensorQuantParams=called")
            val qp = alloc<CPointerVar<NN_QuantParam>>()
            qp.value = q
            OH_NNQuantParam_Destroy(qp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_SetTensorType() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNModel_AddTensorToModel(model, desc)
            val ret = OH_NNModel_SetTensorType(model, 0u, OH_NN_TENSOR)
            assertNotNull(ret)
            logLine("OH_NNModel_SetTensorType=$ret")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_AddOperation() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            val ret = OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            assertNotNull(ret)
            logLine("OH_NNModel_AddOperation=$ret")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_SpecifyInputsAndOutputs() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            val ret = OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            assertNotNull(ret)
            logLine("OH_NNModel_SpecifyInputsAndOutputs=$ret")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_GetAvailableOperations() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val isSupported = alloc<CPointerVar<BooleanVar>>()
            isSupported.value = null
            val opCount = alloc<UIntVar>()
            val ret = OH_NNModel_GetAvailableOperations(model, 0uL, isSupported.ptr, opCount.ptr)
            assertNotNull(ret)
            logLine("OH_NNModel_GetAvailableOperations=$ret")
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNModel_AddTensor() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            OH_NNModel_AddTensor(model, null)
            logLine("OH_NNModel_AddTensor=called")
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    // ==================== QuantParam ====================

    @Test
    fun testOH_NNQuantParam_Create() {
        memScoped {
            val q = OH_NNQuantParam_Create()
            assertNotNull(q)
            logLine("OH_NNQuantParam_Create=$q")
            val qp = alloc<CPointerVar<NN_QuantParam>>()
            qp.value = q
            OH_NNQuantParam_Destroy(qp.ptr)
        }
    }

    @Test
    fun testOH_NNQuantParam_SetScales() {
        memScoped {
            val q = OH_NNQuantParam_Create()
            assertNotNull(q)
            val scales = doubleArrayOf(1.0)
            OH_NNQuantParam_SetScales(q, scales.refTo(0), 1u)
            logLine("OH_NNQuantParam_SetScales=called")
            val qp = alloc<CPointerVar<NN_QuantParam>>()
            qp.value = q
            OH_NNQuantParam_Destroy(qp.ptr)
        }
    }

    @Test
    fun testOH_NNQuantParam_SetZeroPoints() {
        memScoped {
            val q = OH_NNQuantParam_Create()
            assertNotNull(q)
            val zps = intArrayOf(0)
            OH_NNQuantParam_SetZeroPoints(q, zps.refTo(0), 1u)
            logLine("OH_NNQuantParam_SetZeroPoints=called")
            val qp = alloc<CPointerVar<NN_QuantParam>>()
            qp.value = q
            OH_NNQuantParam_Destroy(qp.ptr)
        }
    }

    @Test
    fun testOH_NNQuantParam_SetNumBits() {
        memScoped {
            val q = OH_NNQuantParam_Create()
            assertNotNull(q)
            val bits = uintArrayOf(8u)
            OH_NNQuantParam_SetNumBits(q, bits.refTo(0), 1u)
            logLine("OH_NNQuantParam_SetNumBits=called")
            val qp = alloc<CPointerVar<NN_QuantParam>>()
            qp.value = q
            OH_NNQuantParam_Destroy(qp.ptr)
        }
    }

    @Test
    fun testOH_NNQuantParam_Destroy() {
        memScoped {
            val q = OH_NNQuantParam_Create()
            assertNotNull(q)
            val qp = alloc<CPointerVar<NN_QuantParam>>()
            qp.value = q
            val ret = OH_NNQuantParam_Destroy(qp.ptr)
            assertNotNull(ret)
            logLine("OH_NNQuantParam_Destroy=$ret")
        }
    }

    // ==================== Compilation ====================

    @Test
    fun testOH_NNCompilation_Construct() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            logLine("OH_NNCompilation_Construct=$comp")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_SetDevice() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            logLine("OH_NNCompilation_SetDevice=called")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_SetPerformanceMode() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetPerformanceMode(comp, OH_NN_PERFORMANCE_NONE)
            logLine("OH_NNCompilation_SetPerformanceMode=called")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_SetPriority() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetPriority(comp, OH_NN_PRIORITY_NONE)
            logLine("OH_NNCompilation_SetPriority=called")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_EnableFloat16() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_EnableFloat16(comp, false)
            logLine("OH_NNCompilation_EnableFloat16=called")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_Build() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            val ret = OH_NNCompilation_Build(comp)
            assertNotNull(ret)
            logLine("OH_NNCompilation_Build=$ret")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_Destroy() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            logLine("OH_NNCompilation_Destroy=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_ConstructWithOfflineModelFile() {
        memScoped {
            val comp = OH_NNCompilation_ConstructWithOfflineModelFile(null)
            logLine("OH_NNCompilation_ConstructWithOfflineModelFile=$comp")
        }
    }

    @Test
    fun testOH_NNCompilation_ConstructWithOfflineModelBuffer() {
        memScoped {
            val comp = OH_NNCompilation_ConstructWithOfflineModelBuffer(null, 0u)
            logLine("OH_NNCompilation_ConstructWithOfflineModelBuffer=$comp")
        }
    }

    @Test
    fun testOH_NNCompilation_ConstructForCache() {
        memScoped {
            val comp = OH_NNCompilation_ConstructForCache()
            assertNotNull(comp)
            logLine("OH_NNCompilation_ConstructForCache=$comp")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_ExportCacheToBuffer() {
        memScoped {
            val comp = OH_NNCompilation_ConstructForCache()
            assertNotNull(comp)
            val sizeVar = alloc<ULongVar>()
            val ret = OH_NNCompilation_ExportCacheToBuffer(comp, null, 0u, sizeVar.ptr)
            assertNotNull(ret)
            logLine("OH_NNCompilation_ExportCacheToBuffer=$ret")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_ImportCacheFromBuffer() {
        memScoped {
            val comp = OH_NNCompilation_ConstructForCache()
            assertNotNull(comp)
            val ret = OH_NNCompilation_ImportCacheFromBuffer(comp, null, 0u)
            assertNotNull(ret)
            logLine("OH_NNCompilation_ImportCacheFromBuffer=$ret")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_AddExtensionConfig() {
        memScoped {
            val comp = OH_NNCompilation_ConstructForCache()
            assertNotNull(comp)
            val ret = OH_NNCompilation_AddExtensionConfig(comp, "key", null, 0u)
            assertNotNull(ret)
            logLine("OH_NNCompilation_AddExtensionConfig=$ret")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
        }
    }

    @Test
    fun testOH_NNCompilation_SetCache() {
        memScoped {
            val comp = OH_NNCompilation_ConstructForCache()
            assertNotNull(comp)
            val ret = OH_NNCompilation_SetCache(comp, "/tmp", 1u)
            assertNotNull(ret)
            logLine("OH_NNCompilation_SetCache=$ret")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
        }
    }

    // ==================== TensorDesc ====================

    @Test
    fun testOH_NNTensorDesc_Create() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            logLine("OH_NNTensorDesc_Create=$desc")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_SetName() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetName(desc, "t")
            logLine("OH_NNTensorDesc_SetName=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_GetName() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetName(desc, "t")
            val namePtr = alloc<CPointerVar<ByteVar>>()
            OH_NNTensorDesc_GetName(desc, namePtr.ptr)
            logLine("OH_NNTensorDesc_GetName=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_SetDataType() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            logLine("OH_NNTensorDesc_SetDataType=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_GetDataType() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val dt = alloc<UIntVar>()
            OH_NNTensorDesc_GetDataType(desc, dt.ptr)
            logLine("OH_NNTensorDesc_GetDataType=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_SetShape() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            val shapeArr = intArrayOf(1, 2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 2uL)
            logLine("OH_NNTensorDesc_SetShape=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_GetShape() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            val shapeArr = intArrayOf(1, 2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 2uL)
            val shapeOut = alloc<CPointerVar<IntVar>>()
            val len = alloc<ULongVar>()
            OH_NNTensorDesc_GetShape(desc, shapeOut.ptr, len.ptr)
            logLine("OH_NNTensorDesc_GetShape=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_SetFormat() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetFormat(desc, OH_NN_FORMAT_NCHW)
            logLine("OH_NNTensorDesc_SetFormat=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_GetFormat() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetFormat(desc, OH_NN_FORMAT_NCHW)
            val fmt = alloc<UIntVar>()
            OH_NNTensorDesc_GetFormat(desc, fmt.ptr)
            logLine("OH_NNTensorDesc_GetFormat=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_GetElementCount() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            val shapeArr = intArrayOf(1, 2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 2uL)
            val elemCount = alloc<ULongVar>()
            OH_NNTensorDesc_GetElementCount(desc, elemCount.ptr)
            logLine("OH_NNTensorDesc_GetElementCount=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_GetByteSize() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            val shapeArr = intArrayOf(1, 2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 2uL)
            val byteSize = alloc<ULongVar>()
            OH_NNTensorDesc_GetByteSize(desc, byteSize.ptr)
            logLine("OH_NNTensorDesc_GetByteSize=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensorDesc_Destroy() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            logLine("OH_NNTensorDesc_Destroy=called")
        }
    }

    // ==================== Tensor ====================

    @Test
    fun testOH_NNTensor_CreateWithSize() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            logLine("OH_NNTensor_CreateWithSize=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_GetTensorDesc() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            val gotDesc = OH_NNTensor_GetTensorDesc(tensor)
            logLine("OH_NNTensor_GetTensorDesc=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_GetDataBuffer() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            val buf = OH_NNTensor_GetDataBuffer(tensor)
            logLine("OH_NNTensor_GetDataBuffer=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_GetFd() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            val fd = alloc<IntVar>()
            OH_NNTensor_GetFd(tensor, fd.ptr)
            logLine("OH_NNTensor_GetFd=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_GetSize() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            
            val sizeVar = alloc<ULongVar>()
            OH_NNTensor_GetSize(tensor, sizeVar.ptr)
            logLine("OH_NNTensor_GetSize=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_GetOffset() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            
            val offsetVar = alloc<ULongVar>()
            OH_NNTensor_GetOffset(tensor, offsetVar.ptr)
            logLine("OH_NNTensor_GetOffset=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_Destroy() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(4)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithSize(0u, desc, 16u)
            
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            logLine("OH_NNTensor_Destroy=called")
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_Create() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_Create(0u, desc)
            
            logLine("OH_NNTensor_Create=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    @Test
    fun testOH_NNTensor_CreateWithFd() {
        memScoped {
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(2)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            val tensor = OH_NNTensor_CreateWithFd(0u, desc, -1, 0u, 0u)
            
            logLine("OH_NNTensor_CreateWithFd=called")
            val tp = alloc<CPointerVar<NN_Tensor>>()
            tp.value = tensor
            OH_NNTensor_Destroy(tp.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
        }
    }

    // ==================== Executor ====================

    @Test
    fun testOH_NNExecutor_Construct() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            logLine("OH_NNExecutor_Construct passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_GetInputCount() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            val inputCount = alloc<ULongVar>()
            OH_NNExecutor_GetInputCount(exec, inputCount.ptr)
            logLine("OH_NNExecutor_GetInputCount passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_GetOutputCount() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val outputCount = alloc<ULongVar>()
            OH_NNExecutor_GetOutputCount(exec, outputCount.ptr)
            logLine("OH_NNExecutor_GetOutputCount passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_GetOutputShape() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val shapePtr = alloc<CPointerVar<IntVar>>()
            val outShapeLen = alloc<UIntVar>()
            OH_NNExecutor_GetOutputShape(exec, 0u, shapePtr.ptr, outShapeLen.ptr)
            logLine("OH_NNExecutor_GetOutputShape passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_CreateInputTensorDesc() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val inputDesc = OH_NNExecutor_CreateInputTensorDesc(exec, 0u)
            logLine("OH_NNExecutor_CreateInputTensorDesc=$inputDesc")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_CreateOutputTensorDesc() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val outputDesc = OH_NNExecutor_CreateOutputTensorDesc(exec, 0u)
            logLine("OH_NNExecutor_CreateOutputTensorDesc=$outputDesc")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_GetInputDimRange() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            val minDims = alloc<CPointerVar<ULongVar>>()
            minDims.value = null
            val maxDims = alloc<CPointerVar<ULongVar>>()
            maxDims.value = null
            val dimShapeLen = alloc<ULongVar>()
            OH_NNExecutor_GetInputDimRange(exec, 0u, minDims.ptr, maxDims.ptr, dimShapeLen.ptr)
            logLine("OH_NNExecutor_GetInputDimRange passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_SetOnRunDone() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_SetOnRunDone(exec, null)
            logLine("OH_NNExecutor_SetOnRunDone passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_SetOnServiceDied() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_SetOnServiceDied(exec, null)
            logLine("OH_NNExecutor_SetOnServiceDied passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_RunSync() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_RunSync(exec, null, 0u, null, 0u)
            logLine("OH_NNExecutor_RunSync passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_RunAsync() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_RunAsync(exec, null, 0u, null, 0u, 0, null)
            logLine("OH_NNExecutor_RunAsync passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_SetInput() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_SetInput(exec, 0u, null, null, 0u)
            logLine("OH_NNExecutor_SetInput passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_SetOutput() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_SetOutput(exec, 0u, null, 0u)
            logLine("OH_NNExecutor_SetOutput passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_Run() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_Run(exec)
            logLine("OH_NNExecutor_Run passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_AllocateInputMemory() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val inMem = OH_NNExecutor_AllocateInputMemory(exec, 0u, 0u)
            logLine("OH_NNExecutor_AllocateInputMemory=$inMem")
            val inMemPtr = alloc<CPointerVar<OH_NN_Memory>>()
            inMemPtr.value = inMem
            OH_NNExecutor_DestroyInputMemory(exec, 0u, inMemPtr.ptr)
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_AllocateOutputMemory() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val outMem = OH_NNExecutor_AllocateOutputMemory(exec, 0u, 0u)
            logLine("OH_NNExecutor_AllocateOutputMemory=$outMem")
            val outMemPtr = alloc<CPointerVar<OH_NN_Memory>>()
            outMemPtr.value = outMem
            OH_NNExecutor_DestroyOutputMemory(exec, 0u, outMemPtr.ptr)
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_DestroyInputMemory() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val inMem = OH_NNExecutor_AllocateInputMemory(exec, 0u, 0u)
            val inMemPtr = alloc<CPointerVar<OH_NN_Memory>>()
            inMemPtr.value = inMem
            OH_NNExecutor_DestroyInputMemory(exec, 0u, inMemPtr.ptr)
            logLine("OH_NNExecutor_DestroyInputMemory passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_DestroyOutputMemory() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val outMem = OH_NNExecutor_AllocateOutputMemory(exec, 0u, 0u)
            val outMemPtr = alloc<CPointerVar<OH_NN_Memory>>()
            outMemPtr.value = outMem
            OH_NNExecutor_DestroyOutputMemory(exec, 0u, outMemPtr.ptr)
            logLine("OH_NNExecutor_DestroyOutputMemory passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_SetInputWithMemory() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_SetInputWithMemory(exec, 0u, null, null)
            logLine("OH_NNExecutor_SetInputWithMemory passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_SetOutputWithMemory() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            OH_NNExecutor_SetOutputWithMemory(exec, 0u, null)
            logLine("OH_NNExecutor_SetOutputWithMemory passed")
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    @Test
    fun testOH_NNExecutor_Destroy() {
        memScoped {
            val model = OH_NNModel_Construct()
            assertNotNull(model)
            val desc = OH_NNTensorDesc_Create()
            assertNotNull(desc)
            OH_NNTensorDesc_SetDataType(desc, OH_NN_FLOAT32)
            val shapeArr = intArrayOf(1)
            OH_NNTensorDesc_SetShape(desc, shapeArr.refTo(0), 1uL)
            OH_NNModel_AddTensorToModel(model, desc)
            val paramArr = alloc<OH_NN_UInt32Array>().apply { data = null; size = 0u }
            val inputData = alloc<UIntVar>().apply { value = 0u }
            val inputArr = alloc<OH_NN_UInt32Array>().apply { data = inputData.ptr; size = 1u }
            val outputData = alloc<UIntVar>().apply { value = 0u }
            val outputArr = alloc<OH_NN_UInt32Array>().apply { data = outputData.ptr; size = 1u }
            OH_NNModel_AddOperation(model, OH_NN_OPS_RELU, paramArr.ptr, inputArr.ptr, outputArr.ptr)
            OH_NNModel_SpecifyInputsAndOutputs(model, inputArr.ptr, outputArr.ptr)
            OH_NNModel_Finish(model)
            val comp = OH_NNCompilation_Construct(model)
            assertNotNull(comp)
            OH_NNCompilation_SetDevice(comp, 0u)
            OH_NNCompilation_Build(comp)
            val exec = OH_NNExecutor_Construct(comp)
            
            val execPtr = alloc<CPointerVar<OH_NNExecutor>>()
            execPtr.value = exec
            OH_NNExecutor_Destroy(execPtr.ptr)
            logLine("OH_NNExecutor_Destroy passed")
            val compPtr = alloc<CPointerVar<OH_NNCompilation>>()
            compPtr.value = comp
            OH_NNCompilation_Destroy(compPtr.ptr)
            val descPtr = alloc<CPointerVar<NN_TensorDesc>>()
            descPtr.value = desc
            OH_NNTensorDesc_Destroy(descPtr.ptr)
            val ptr = alloc<CPointerVar<OH_NNModel>>()
            ptr.value = model
            OH_NNModel_Destroy(ptr.ptr)
        }
    }

    // ==================== Device ====================

    @Test
    fun testOH_NNDevice_GetAllDevicesID() {
        memScoped {
            val ids = alloc<CPointerVar<ULongVar>>()
            val count = alloc<UIntVar>()
            val ret = OH_NNDevice_GetAllDevicesID(ids.ptr, count.ptr)
            assertNotNull(ret)
            logLine("OH_NNDevice_GetAllDevicesID passed")
        }
    }

    @Test
    fun testOH_NNDevice_GetName() {
        memScoped {
            val namePtr = alloc<CPointerVar<ByteVar>>()
            OH_NNDevice_GetName(0uL, namePtr.ptr)
            logLine("OH_NNDevice_GetName passed")
        }
    }

    @Test
    fun testOH_NNDevice_GetType() {
        memScoped {
            val typeVar = alloc<UIntVar>()
            OH_NNDevice_GetType(0uL, typeVar.ptr)
            logLine("OH_NNDevice_GetType passed")
        }
    }
}
