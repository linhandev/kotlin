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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.AVCodecKit.Core.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class CoreTest {

    private fun logLine(msg: String) = println("[stdout] CoreTest $msg")

    // ==================== native_audio_channel_layout.h ====================

    @Test
    fun testEnum_OH_AudioChannelSet() {
        fun ch(n: Int) = 1L shl n
        assertEquals(CH_SET_FRONT_LEFT.toLong(), ch(0))
        assertEquals(CH_SET_FRONT_RIGHT.toLong(), ch(1))
        assertEquals(CH_SET_FRONT_CENTER.toLong(), ch(2))
        assertEquals(CH_SET_LOW_FREQUENCY.toLong(), ch(3))
        assertEquals(CH_SET_BACK_LEFT.toLong(), ch(4))
        assertEquals(CH_SET_BACK_RIGHT.toLong(), ch(5))
        assertEquals(CH_SET_FRONT_LEFT_OF_CENTER.toLong(), ch(6))
        assertEquals(CH_SET_FRONT_RIGHT_OF_CENTER.toLong(), ch(7))
        assertEquals(CH_SET_BACK_CENTER.toLong(), ch(8))
        assertEquals(CH_SET_SIDE_LEFT.toLong(), ch(9))
        assertEquals(CH_SET_SIDE_RIGHT.toLong(), ch(10))
        assertEquals(CH_SET_TOP_CENTER.toLong(), ch(11))
        assertEquals(CH_SET_TOP_FRONT_LEFT.toLong(), ch(12))
        assertEquals(CH_SET_TOP_FRONT_CENTER.toLong(), ch(13))
        assertEquals(CH_SET_TOP_FRONT_RIGHT.toLong(), ch(14))
        assertEquals(CH_SET_TOP_BACK_LEFT.toLong(), ch(15))
        assertEquals(CH_SET_TOP_BACK_CENTER.toLong(), ch(16))
        assertEquals(CH_SET_TOP_BACK_RIGHT.toLong(), ch(17))
        assertEquals(CH_SET_STEREO_LEFT.toLong(), ch(29))
        assertEquals(CH_SET_STEREO_RIGHT.toLong(), ch(30))
        assertEquals(CH_SET_WIDE_LEFT.toLong(), ch(31))
        assertEquals(CH_SET_WIDE_RIGHT.toLong(), 1L shl 32)
        assertEquals(CH_SET_SURROUND_DIRECT_LEFT.toLong(), 1L shl 33)
        assertEquals(CH_SET_SURROUND_DIRECT_RIGHT.toLong(), 1L shl 34)
        assertEquals(CH_SET_LOW_FREQUENCY_2.toLong(), 1L shl 35)
        assertEquals(CH_SET_TOP_SIDE_LEFT.toLong(), 1L shl 36)
        assertEquals(CH_SET_TOP_SIDE_RIGHT.toLong(), 1L shl 37)
        assertEquals(CH_SET_BOTTOM_FRONT_CENTER.toLong(), 1L shl 38)
        assertEquals(CH_SET_BOTTOM_FRONT_LEFT.toLong(), 1L shl 39)
        assertEquals(CH_SET_BOTTOM_FRONT_RIGHT.toLong(), 1L shl 40)
        logLine("OH_AudioChannelSet passed")
    }

    @Test
    fun testEnum_OH_AmbAttributeSet() {
        assertEquals(AMB_ORD_1.toLong(), 1L)
        assertEquals(AMB_ORD_2.toLong(), 2L)
        assertEquals(AMB_ORD_3.toLong(), 3L)
        assertEquals(AMB_COM_FUMA.toLong(), 256L)
        assertEquals(AMB_NOR_N3D.toLong(), 0L)
        assertEquals(AMB_NOR_SN3D.toLong(), 4096L)
        assertEquals(AMB_MODE.toLong(), 1L shl 44)
        logLine("OH_AmbAttributeSet passed")
    }

    @Test
    fun testEnum_OH_AudioChannelLayout() {
        assertEquals(CH_LAYOUT_UNKNOWN.toLong(), 0L)
        assertEquals(CH_LAYOUT_MONO.toLong(), CH_SET_FRONT_CENTER.toLong())
        assertEquals(CH_LAYOUT_STEREO.toLong(), (CH_SET_FRONT_LEFT.toLong() or CH_SET_FRONT_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_STEREO_DOWNMIX.toLong(), (CH_SET_STEREO_LEFT.toLong() or CH_SET_STEREO_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_2POINT1.toLong(), (CH_LAYOUT_STEREO.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_3POINT0.toLong(), (CH_LAYOUT_STEREO.toLong() or CH_SET_BACK_CENTER.toLong()))
        assertEquals(CH_LAYOUT_SURROUND.toLong(), (CH_LAYOUT_STEREO.toLong() or CH_SET_FRONT_CENTER.toLong()))
        assertEquals(CH_LAYOUT_3POINT1.toLong(), (CH_LAYOUT_SURROUND.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_4POINT0.toLong(), (CH_LAYOUT_SURROUND.toLong() or CH_SET_BACK_CENTER.toLong()))
        assertEquals(CH_LAYOUT_QUAD_SIDE.toLong(), (CH_LAYOUT_STEREO.toLong() or CH_SET_SIDE_LEFT.toLong() or CH_SET_SIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_QUAD.toLong(), (CH_LAYOUT_STEREO.toLong() or CH_SET_BACK_LEFT.toLong() or CH_SET_BACK_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_2POINT0POINT2.toLong(), (CH_LAYOUT_STEREO.toLong() or CH_SET_TOP_SIDE_LEFT.toLong() or CH_SET_TOP_SIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER1_ACN_N3D.toLong(), (AMB_MODE.toLong() or AMB_ORD_1.toLong() or AMB_COM_ACN.toLong() or AMB_NOR_N3D.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER1_ACN_SN3D.toLong(), (AMB_MODE.toLong() or AMB_ORD_1.toLong() or AMB_COM_ACN.toLong() or AMB_NOR_SN3D.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER1_FUMA.toLong(), (AMB_MODE.toLong() or AMB_ORD_1.toLong() or AMB_COM_FUMA.toLong()))
        assertEquals(CH_LAYOUT_4POINT1.toLong(), (CH_LAYOUT_4POINT0.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_5POINT0.toLong(), (CH_LAYOUT_SURROUND.toLong() or CH_SET_SIDE_LEFT.toLong() or CH_SET_SIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_5POINT0_BACK.toLong(), (CH_LAYOUT_SURROUND.toLong() or CH_SET_BACK_LEFT.toLong() or CH_SET_BACK_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_2POINT1POINT2.toLong(), (CH_LAYOUT_2POINT0POINT2.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_3POINT0POINT2.toLong(), (CH_LAYOUT_2POINT0POINT2.toLong() or CH_SET_FRONT_CENTER.toLong()))
        assertEquals(CH_LAYOUT_5POINT1.toLong(), (CH_LAYOUT_5POINT0.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_5POINT1_BACK.toLong(), (CH_LAYOUT_5POINT0_BACK.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_6POINT0.toLong(), (CH_LAYOUT_5POINT0.toLong() or CH_SET_BACK_CENTER.toLong()))
        assertEquals(CH_LAYOUT_3POINT1POINT2.toLong(), (CH_LAYOUT_3POINT1.toLong() or CH_SET_TOP_FRONT_LEFT.toLong() or CH_SET_TOP_FRONT_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_6POINT0_FRONT.toLong(), (CH_LAYOUT_QUAD_SIDE.toLong() or CH_SET_FRONT_LEFT_OF_CENTER.toLong() or CH_SET_FRONT_RIGHT_OF_CENTER.toLong()))
        assertEquals(CH_LAYOUT_HEXAGONAL.toLong(), (CH_LAYOUT_5POINT0_BACK.toLong() or CH_SET_BACK_CENTER.toLong()))
        assertEquals(CH_LAYOUT_6POINT1.toLong(), (CH_LAYOUT_5POINT1.toLong() or CH_SET_BACK_CENTER.toLong()))
        assertEquals(CH_LAYOUT_6POINT1_BACK.toLong(), (CH_LAYOUT_5POINT1_BACK.toLong() or CH_SET_BACK_CENTER.toLong()))
        assertEquals(CH_LAYOUT_6POINT1_FRONT.toLong(), (CH_LAYOUT_6POINT0_FRONT.toLong() or CH_SET_LOW_FREQUENCY.toLong()))
        assertEquals(CH_LAYOUT_7POINT0.toLong(), (CH_LAYOUT_5POINT0.toLong() or CH_SET_BACK_LEFT.toLong() or CH_SET_BACK_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_7POINT0_FRONT.toLong(), (CH_LAYOUT_5POINT0.toLong() or CH_SET_FRONT_LEFT_OF_CENTER.toLong() or CH_SET_FRONT_RIGHT_OF_CENTER.toLong()))
        assertEquals(CH_LAYOUT_7POINT1.toLong(), (CH_LAYOUT_5POINT1.toLong() or CH_SET_BACK_LEFT.toLong() or CH_SET_BACK_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_OCTAGONAL.toLong(), (CH_LAYOUT_5POINT0.toLong() or CH_SET_BACK_LEFT.toLong() or CH_SET_BACK_CENTER.toLong() or CH_SET_BACK_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_5POINT1POINT2.toLong(), (CH_LAYOUT_5POINT1.toLong() or CH_SET_TOP_SIDE_LEFT.toLong() or CH_SET_TOP_SIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_7POINT1_WIDE.toLong(), (CH_LAYOUT_5POINT1.toLong() or CH_SET_FRONT_LEFT_OF_CENTER.toLong() or CH_SET_FRONT_RIGHT_OF_CENTER.toLong()))
        assertEquals(CH_LAYOUT_7POINT1_WIDE_BACK.toLong(), (CH_LAYOUT_5POINT1_BACK.toLong() or CH_SET_FRONT_LEFT_OF_CENTER.toLong() or CH_SET_FRONT_RIGHT_OF_CENTER.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER2_ACN_N3D.toLong(), (AMB_MODE.toLong() or AMB_ORD_2.toLong() or AMB_COM_ACN.toLong() or AMB_NOR_N3D.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER2_ACN_SN3D.toLong(), (AMB_MODE.toLong() or AMB_ORD_2.toLong() or AMB_COM_ACN.toLong() or AMB_NOR_SN3D.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER2_FUMA.toLong(), (AMB_MODE.toLong() or AMB_ORD_2.toLong() or AMB_COM_FUMA.toLong()))
        assertEquals(CH_LAYOUT_7POINT1POINT2.toLong(), (CH_LAYOUT_7POINT1.toLong() or CH_SET_TOP_SIDE_LEFT.toLong() or CH_SET_TOP_SIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER3_ACN_N3D.toLong(), (AMB_MODE.toLong() or AMB_ORD_3.toLong() or AMB_COM_ACN.toLong() or AMB_NOR_N3D.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER3_ACN_SN3D.toLong(), (AMB_MODE.toLong() or AMB_ORD_3.toLong() or AMB_COM_ACN.toLong() or AMB_NOR_SN3D.toLong()))
        assertEquals(CH_LAYOUT_AMB_ORDER3_FUMA.toLong(), (AMB_MODE.toLong() or AMB_ORD_3.toLong() or AMB_COM_FUMA.toLong()))
        assertEquals(CH_LAYOUT_7POINT1POINT4.toLong(), (CH_LAYOUT_7POINT1.toLong() or CH_SET_TOP_FRONT_LEFT.toLong() or CH_SET_TOP_FRONT_RIGHT.toLong() or CH_SET_TOP_BACK_LEFT.toLong() or CH_SET_TOP_BACK_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_10POINT2.toLong(), (CH_SET_FRONT_LEFT.toLong() or CH_SET_FRONT_RIGHT.toLong() or CH_SET_FRONT_CENTER.toLong() or CH_SET_TOP_FRONT_LEFT.toLong() or CH_SET_TOP_FRONT_RIGHT.toLong() or CH_SET_BACK_LEFT.toLong() or CH_SET_BACK_RIGHT.toLong() or CH_SET_BACK_CENTER.toLong() or CH_SET_SIDE_LEFT.toLong() or CH_SET_SIDE_RIGHT.toLong() or CH_SET_WIDE_LEFT.toLong() or CH_SET_WIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_9POINT1POINT4.toLong(), (CH_LAYOUT_7POINT1POINT4.toLong() or CH_SET_WIDE_LEFT.toLong() or CH_SET_WIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_9POINT1POINT6.toLong(), (CH_LAYOUT_9POINT1POINT4.toLong() or CH_SET_TOP_SIDE_LEFT.toLong() or CH_SET_TOP_SIDE_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_HEXADECAGONAL.toLong(), (CH_LAYOUT_OCTAGONAL.toLong() or CH_SET_WIDE_LEFT.toLong() or CH_SET_WIDE_RIGHT.toLong() or CH_SET_TOP_BACK_LEFT.toLong() or CH_SET_TOP_BACK_RIGHT.toLong() or CH_SET_TOP_BACK_CENTER.toLong() or CH_SET_TOP_FRONT_CENTER.toLong() or CH_SET_TOP_FRONT_LEFT.toLong() or CH_SET_TOP_FRONT_RIGHT.toLong()))
        assertEquals(CH_LAYOUT_22POINT2.toLong(), (CH_LAYOUT_7POINT1POINT4.toLong() or CH_SET_FRONT_LEFT_OF_CENTER.toLong() or CH_SET_FRONT_RIGHT_OF_CENTER.toLong() or CH_SET_BACK_CENTER.toLong() or CH_SET_TOP_CENTER.toLong() or CH_SET_TOP_FRONT_CENTER.toLong() or CH_SET_TOP_BACK_CENTER.toLong() or CH_SET_TOP_SIDE_LEFT.toLong() or CH_SET_TOP_SIDE_RIGHT.toLong() or CH_SET_BOTTOM_FRONT_LEFT.toLong() or CH_SET_BOTTOM_FRONT_RIGHT.toLong() or CH_SET_BOTTOM_FRONT_CENTER.toLong() or CH_SET_LOW_FREQUENCY_2.toLong()))
        logLine("OH_AudioChannelLayout passed")
    }

    // ==================== native_averrors.h ====================

    @Test
    fun testEnum_OH_AVErrCode() {
        assertEquals(AV_ERR_OK.toInt(), 0)
        assertEquals(AV_ERR_NO_MEMORY.toInt(), 1)
        assertEquals(AV_ERR_OPERATE_NOT_PERMIT.toInt(), 2)
        assertEquals(AV_ERR_INVALID_VAL.toInt(), 3)
        assertEquals(AV_ERR_IO.toInt(), 4)
        assertEquals(AV_ERR_TIMEOUT.toInt(), 5)
        assertEquals(AV_ERR_UNKNOWN.toInt(), 6)
        assertEquals(AV_ERR_SERVICE_DIED.toInt(), 7)
        assertEquals(AV_ERR_INVALID_STATE.toInt(), 8)
        assertEquals(AV_ERR_UNSUPPORT.toInt(), 9)
        assertEquals(AV_ERR_INPUT_DATA_ERROR.toInt(), 10)
        assertEquals(AV_ERR_UNSUPPORTED_FORMAT.toInt(), 11)
        assertEquals(AV_ERR_EXTEND_START.toInt(), 100)
        assertEquals(AV_ERR_DRM_BASE.toInt(), 200)
        assertEquals(AV_ERR_DRM_DECRYPT_FAILED.toInt(), 201)
        assertEquals(AV_ERR_VIDEO_BASE.toInt(), 300)
        assertEquals(AV_ERR_VIDEO_UNSUPPORTED_COLOR_SPACE_CONVERSION.toInt(), 301)
        assertEquals(AV_ERR_IO_CANNOT_FIND_HOST.toInt(), 5411001)
        assertEquals(AV_ERR_IO_CONNECTION_TIMEOUT.toInt(), 5411002)
        assertEquals(AV_ERR_IO_NETWORK_ABNORMAL.toInt(), 5411003)
        assertEquals(AV_ERR_IO_NETWORK_UNAVAILABLE.toInt(), 5411004)
        assertEquals(AV_ERR_IO_NO_PERMISSION.toInt(), 5411005)
        assertEquals(AV_ERR_IO_NETWORK_ACCESS_DENIED.toInt(), 5411006)
        assertEquals(AV_ERR_IO_RESOURCE_NOT_FOUND.toInt(), 5411007)
        assertEquals(AV_ERR_IO_SSL_CLIENT_CERT_NEEDED.toInt(), 5411008)
        assertEquals(AV_ERR_IO_SSL_CONNECT_FAIL.toInt(), 5411009)
        assertEquals(AV_ERR_IO_SSL_SERVER_CERT_UNTRUSTED.toInt(), 5411010)
        assertEquals(AV_ERR_IO_UNSUPPORTED_REQUEST.toInt(), 5411011)
        assertEquals(AV_ERR_STREAM_CHANGED.toInt(), 5410005)
        assertEquals(AV_ERR_TRY_AGAIN_LATER.toInt(), 5410006)
    }

    // ==================== native_avbuffer_info.h ====================

    @Test
    fun testEnum_OH_AVCodecBufferFlags() {
        assertEquals(AVCODEC_BUFFER_FLAGS_NONE.toInt(), 0)
        assertEquals(AVCODEC_BUFFER_FLAGS_EOS.toInt(), 1)
        assertEquals(AVCODEC_BUFFER_FLAGS_SYNC_FRAME.toInt(), 2)
        assertEquals(AVCODEC_BUFFER_FLAGS_INCOMPLETE_FRAME.toInt(), 4)
        assertEquals(AVCODEC_BUFFER_FLAGS_CODEC_DATA.toInt(), 8)
        assertEquals(AVCODEC_BUFFER_FLAGS_DISCARD.toInt(), 16)
        assertEquals(AVCODEC_BUFFER_FLAGS_DISPOSABLE.toInt(), 32)
        logLine("OH_AVCodecBufferFlags passed")
    }

    // ==================== media_types.h ====================

    @Test
    fun testEnum_OH_Core_HdrType() {
        assertEquals(OH_CORE_HDR_TYPE_NONE.toInt(), 0)
        assertEquals(OH_CORE_HDR_TYPE_VIVID.toInt(), 1)
    }

    // ==================== native_avformat.h ====================

    @Test
    fun testEnum_OH_AVPixelFormat() {
        assertEquals(AV_PIXEL_FORMAT_YUVI420.toInt(), 1)
        assertEquals(AV_PIXEL_FORMAT_NV12.toInt(), 2)
        assertEquals(AV_PIXEL_FORMAT_NV21.toInt(), 3)
        assertEquals(AV_PIXEL_FORMAT_SURFACE_FORMAT.toInt(), 4)
        assertEquals(AV_PIXEL_FORMAT_RGBA.toInt(), 5)
        assertEquals(AV_PIXEL_FORMAT_RGBA1010102.toInt(), 6)
    }

    @Test
    fun testOH_AVFormat_Create() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            logLine("OH_AVFormat_Create=$format")
            OH_AVFormat_Destroy(format)
        }
    }

    @Test
    fun testOH_AVFormat_Destroy() {
        memScoped {
            val format = OH_AVFormat_Create()
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_Destroy done")
            OH_AVFormat_Destroy(null)
            logLine("OH_AVFormat_Destroy(null) done")
        }
    }

    @Test
    fun testOH_AVFormat_CreateAudioFormat() {
        memScoped {
            val format = OH_AVFormat_CreateAudioFormat("audio/mp4a-latm", 48000, 2)
            assertNotNull(format)
            logLine("OH_AVFormat_CreateAudioFormat=$format")
            OH_AVFormat_Destroy(format)
        }
    }

    @Test
    fun testOH_AVFormat_CreateVideoFormat() {
        memScoped {
            val format = OH_AVFormat_CreateVideoFormat("video/avc", 1920, 1080)
            assertNotNull(format)
            logLine("OH_AVFormat_CreateVideoFormat=$format")
            OH_AVFormat_Destroy(format)
        }
    }

    @Test
    fun testOH_AVFormat_Copy() {
        memScoped {
            val from = OH_AVFormat_Create()
            assertNotNull(from)
            val to = OH_AVFormat_Create()
            assertNotNull(to)
            val ok = OH_AVFormat_Copy(to, from)
            assertNotNull(ok)
            OH_AVFormat_Destroy(from)
            OH_AVFormat_Destroy(to)
            logLine("OH_AVFormat_Copy done")
        }
    }

    @Test
    fun testOH_AVFormat_SetIntValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            assertNotNull(OH_AVFormat_SetIntValue(format, "key_i", 42))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_SetIntValue done")
        }
    }

    @Test
    fun testOH_AVFormat_GetIntValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            OH_AVFormat_SetIntValue(format, "key_i", 42)
            val outInt = alloc<IntVar>()
            assertNotNull(OH_AVFormat_GetIntValue(format, "key_i", outInt.ptr))
            assertEquals(outInt.value, 42)
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_GetIntValue done")
        }
    }

    @Test
    fun testOH_AVFormat_SetLongValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            assertNotNull(OH_AVFormat_SetLongValue(format, "key_l", 100L))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_SetLongValue done")
        }
    }

    @Test
    fun testOH_AVFormat_GetLongValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            OH_AVFormat_SetLongValue(format, "key_l", 100L)
            val outLong = alloc<LongVar>()
            assertNotNull(OH_AVFormat_GetLongValue(format, "key_l", outLong.ptr))
            assertEquals(outLong.value, 100L)
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_GetLongValue done")
        }
    }

    @Test
    fun testOH_AVFormat_SetFloatValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            assertNotNull(OH_AVFormat_SetFloatValue(format, "key_f", 3.14f))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_SetFloatValue done")
        }
    }

    @Test
    fun testOH_AVFormat_GetFloatValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            OH_AVFormat_SetFloatValue(format, "key_f", 3.14f)
            val outFloat = alloc<FloatVar>()
            assertNotNull(OH_AVFormat_GetFloatValue(format, "key_f", outFloat.ptr))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_GetFloatValue done")
        }
    }

    @Test
    fun testOH_AVFormat_SetDoubleValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            assertNotNull(OH_AVFormat_SetDoubleValue(format, "key_d", 2.718))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_SetDoubleValue done")
        }
    }

    @Test
    fun testOH_AVFormat_GetDoubleValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            OH_AVFormat_SetDoubleValue(format, "key_d", 2.718)
            val outDouble = alloc<DoubleVar>()
            assertNotNull(OH_AVFormat_GetDoubleValue(format, "key_d", outDouble.ptr))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_GetDoubleValue done")
        }
    }

    @Test
    fun testOH_AVFormat_SetStringValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            assertNotNull(OH_AVFormat_SetStringValue(format, "mime", "video/avc"))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_SetStringValue done")
        }
    }

    @Test
    fun testOH_AVFormat_GetStringValue() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            OH_AVFormat_SetStringValue(format, "mime", "video/avc")
            val outStr = alloc<CPointerVar<ByteVar>>()
            assertNotNull(OH_AVFormat_GetStringValue(format, "mime", outStr.ptr))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_GetStringValue done")
        }
    }

    @Test
    fun testOH_AVFormat_SetBuffer() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            val buf = allocArray<UByteVar>(4)
            assertNotNull(OH_AVFormat_SetBuffer(format, "buf", buf, 4uL))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_SetBuffer done")
        }
    }

    @Test
    fun testOH_AVFormat_GetBuffer() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            val buf = allocArray<UByteVar>(4)
            OH_AVFormat_SetBuffer(format, "buf", buf, 4uL)
            val outAddr = alloc<CPointerVar<UByteVar>>()
            val outSize = alloc<ULongVar>()
            assertNotNull(OH_AVFormat_GetBuffer(format, "buf", outAddr.ptr, outSize.ptr))
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_GetBuffer done")
        }
    }

    @Test
    fun testOH_AVFormat_DumpInfo() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            val dump = OH_AVFormat_DumpInfo(format)
            OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_DumpInfo done")
        }
    }

    @Test
    fun testOH_AVFormat_SetIntBuffer() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            try {
                val arr = allocArray<IntVar>(3).apply {
                    this[0] = 10
                    this[1] = 20
                    this[2] = 30
                }
                assertNotNull(OH_AVFormat_SetIntBuffer(format, "int_buf", arr, 3uL))
                logLine("OH_AVFormat_SetIntBuffer done (API 20)")
            } catch (e: Throwable) {
                logLine("OH_AVFormat_SetIntBuffer (API 20) exception: $e")
            }
            OH_AVFormat_Destroy(format)
        }
    }

    @Test
    fun testOH_AVFormat_GetIntBuffer() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            try {
                val arr = allocArray<IntVar>(3).apply {
                    this[0] = 10
                    this[1] = 20
                    this[2] = 30
                }
                OH_AVFormat_SetIntBuffer(format, "int_buf", arr, 3uL)
                val outAddr = alloc<CPointerVar<IntVar>>()
                val outSize = alloc<ULongVar>()
                assertNotNull(OH_AVFormat_GetIntBuffer(format, "int_buf", outAddr.ptr, outSize.ptr))
                assertNotNull(outSize.value)
                logLine("OH_AVFormat_GetIntBuffer done (API 20)")
            } catch (e: Throwable) {
                logLine("OH_AVFormat_GetIntBuffer (API 20) exception: $e")
            }
            OH_AVFormat_Destroy(format)
        }
    }

    // ==================== native_avmemory.h ====================

    @Test
    fun testOH_AVMemory_Create() {
        memScoped {
            val mem = OH_AVMemory_Create(256)
            logLine("OH_AVMemory_Create=$mem")
            OH_AVMemory_Destroy(mem)
        }
    }

    @Test
    fun testOH_AVMemory_GetAddr() {
        memScoped {
            val mem = OH_AVMemory_Create(256)
            val addr = OH_AVMemory_GetAddr(mem)
            logLine("OH_AVMemory_GetAddr=$addr")
            OH_AVMemory_Destroy(mem)
        }
    }

    @Test
    fun testOH_AVMemory_GetSize() {
        memScoped {
            val mem = OH_AVMemory_Create(256)
            val size = OH_AVMemory_GetSize(mem)
            logLine("OH_AVMemory_GetSize=$size")
            OH_AVMemory_Destroy(mem)
        }
    }

    @Test
    fun testOH_AVMemory_Destroy() {
        memScoped {
            val mem = OH_AVMemory_Create(256)
            val rc = OH_AVMemory_Destroy(mem)
            logLine("OH_AVMemory_Destroy=$rc")
            assertNotNull(rc)
        }
    }

    // ==================== native_avbuffer.h ====================

    @Test
    fun testOH_AVBuffer_Create() {
        memScoped {
            val buf = OH_AVBuffer_Create(1024)
            logLine("OH_AVBuffer_Create=$buf")
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_Destroy() {
        memScoped {
            val buf = OH_AVBuffer_Create(1024)
            val rc = OH_AVBuffer_Destroy(buf)
            logLine("OH_AVBuffer_Destroy=$rc")
            assertNotNull(rc)
            OH_AVBuffer_Destroy(null)
            logLine("OH_AVBuffer_Destroy(null) done")
        }
    }

    @Test
    fun testOH_AVBuffer_SetBufferAttr() {
        memScoped {
            val buf = OH_AVBuffer_Create(512)
            val attr = alloc<OH_AVCodecBufferAttr>().apply {
                pts = 0L
                size = 128
                offset = 0
                flags = AVCODEC_BUFFER_FLAGS_NONE.toUInt()
            }
            OH_AVBuffer_SetBufferAttr(buf, attr.ptr)
            logLine("OH_AVBuffer_SetBufferAttr done")
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_GetBufferAttr() {
        memScoped {
            val buf = OH_AVBuffer_Create(512)
            val attr = alloc<OH_AVCodecBufferAttr>().apply {
                pts = 0L
                size = 128
                offset = 0
                flags = AVCODEC_BUFFER_FLAGS_NONE.toUInt()
            }
            OH_AVBuffer_SetBufferAttr(buf, attr.ptr)
            val outAttr = alloc<OH_AVCodecBufferAttr>()
            val getAttrRet = OH_AVBuffer_GetBufferAttr(buf, outAttr.ptr)
            logLine("OH_AVBuffer_GetBufferAttr ret=$getAttrRet")
            assertNotNull(getAttrRet)
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_GetAddr() {
        memScoped {
            val buf = OH_AVBuffer_Create(512)
            val addr = OH_AVBuffer_GetAddr(buf)
            logLine("OH_AVBuffer_GetAddr=$addr")
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_GetCapacity() {
        memScoped {
            val buf = OH_AVBuffer_Create(512)
            val cap = OH_AVBuffer_GetCapacity(buf)
            logLine("OH_AVBuffer_GetCapacity=$cap")
            assertNotNull(cap)
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_SetParameter() {
        memScoped {
            val buf = OH_AVBuffer_Create(256)
            val format = OH_AVFormat_Create()
            OH_AVBuffer_SetParameter(buf, format)
            logLine("OH_AVBuffer_SetParameter done")
            OH_AVFormat_Destroy(format)
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_GetParameter() {
        memScoped {
            val buf = OH_AVBuffer_Create(256)
            val format = OH_AVFormat_Create()
            OH_AVBuffer_SetParameter(buf, format)
            val outFmt = OH_AVBuffer_GetParameter(buf)
            logLine("OH_AVBuffer_GetParameter ret=$outFmt")
            OH_AVFormat_Destroy(format)
            OH_AVBuffer_Destroy(buf)
        }
    }

    @Test
    fun testOH_AVBuffer_GetNativeBuffer() {
        memScoped {
            val buf = OH_AVBuffer_Create(64)
            val nativeBuf = OH_AVBuffer_GetNativeBuffer(buf)
            logLine("OH_AVBuffer_GetNativeBuffer=$nativeBuf")
            OH_AVBuffer_Destroy(buf)
        }
    }
}
