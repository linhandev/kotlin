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
class CodecBaseTest {

    private fun logLine(message: String) {
        println(message)
    }

    @Test
    fun testMediaTypeEnum() {
        logLine("--- OH_MediaType ---")
        val aud = platform.AVCodecKit.CodecBase.MEDIA_TYPE_AUD
        val vid = platform.AVCodecKit.CodecBase.MEDIA_TYPE_VID
        val subtitle = platform.AVCodecKit.CodecBase.MEDIA_TYPE_SUBTITLE
        val timedMeta = platform.AVCodecKit.CodecBase.MEDIA_TYPE_TIMED_METADATA
        val aux = platform.AVCodecKit.CodecBase.MEDIA_TYPE_AUXILIARY
        logLine("MEDIA_TYPE_AUD=$aud MEDIA_TYPE_VID=$vid MEDIA_TYPE_SUBTITLE=$subtitle MEDIA_TYPE_TIMED_METADATA=$timedMeta MEDIA_TYPE_AUXILIARY=$aux")
        assertNotEquals(aud, vid)
    }

    @Test
    fun testAACProfileEnum() {
        logLine("--- OH_AACProfile ---")
        logLine("AAC_PROFILE_LC=${platform.AVCodecKit.CodecBase.AAC_PROFILE_LC} AAC_PROFILE_HE=${platform.AVCodecKit.CodecBase.AAC_PROFILE_HE} AAC_PROFILE_HE_V2=${platform.AVCodecKit.CodecBase.AAC_PROFILE_HE_V2}")
        assertNotEquals(platform.AVCodecKit.CodecBase.AAC_PROFILE_LC, platform.AVCodecKit.CodecBase.AAC_PROFILE_HE)
    }

    @Test
    fun testAVCProfileEnum() {
        logLine("--- OH_AVCProfile ---")
        logLine("AVC_PROFILE_BASELINE=${platform.AVCodecKit.CodecBase.AVC_PROFILE_BASELINE} AVC_PROFILE_HIGH=${platform.AVCodecKit.CodecBase.AVC_PROFILE_HIGH} AVC_PROFILE_MAIN=${platform.AVCodecKit.CodecBase.AVC_PROFILE_MAIN}")
        assertNotEquals(platform.AVCodecKit.CodecBase.AVC_PROFILE_BASELINE, platform.AVCodecKit.CodecBase.AVC_PROFILE_MAIN)
    }

    @Test
    fun testHEVCProfileEnum() {
        logLine("--- OH_HEVCProfile ---")
        logLine("HEVC_PROFILE_MAIN=${platform.AVCodecKit.CodecBase.HEVC_PROFILE_MAIN} HEVC_PROFILE_MAIN_10=${platform.AVCodecKit.CodecBase.HEVC_PROFILE_MAIN_10} HEVC_PROFILE_MAIN_STILL=${platform.AVCodecKit.CodecBase.HEVC_PROFILE_MAIN_STILL}")
        assertNotEquals(platform.AVCodecKit.CodecBase.HEVC_PROFILE_MAIN, platform.AVCodecKit.CodecBase.HEVC_PROFILE_MAIN_10)
    }

    @Test
    fun testVVCProfileEnum() {
        logLine("--- OH_VVCProfile ---")
        logLine("VVC_PROFILE_MAIN_10=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_10} VVC_PROFILE_MAIN_12=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12} VVC_PROFILE_MAIN_12_INTRA=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12_INTRA}")
        logLine("VVC_PROFILE_MULTI_MAIN_10=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MULTI_MAIN_10} VVC_PROFILE_MAIN_10_444=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_10_444} VVC_PROFILE_MAIN_12_444=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12_444}")
        logLine("VVC_PROFILE_MAIN_16_444=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_16_444} VVC_PROFILE_MAIN_12_444_INTRA=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12_444_INTRA} VVC_PROFILE_MAIN_16_444_INTRA=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_16_444_INTRA}")
        logLine("VVC_PROFILE_MULTI_MAIN_10_444=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MULTI_MAIN_10_444} VVC_PROFILE_MAIN_10_STILL=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_10_STILL} VVC_PROFILE_MAIN_12_STILL=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12_STILL}")
        logLine("VVC_PROFILE_MAIN_10_444_STILL=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_10_444_STILL} VVC_PROFILE_MAIN_12_444_STILL=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12_444_STILL} VVC_PROFILE_MAIN_16_444_STILL=${platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_16_444_STILL}")
        assertNotEquals(platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_10, platform.AVCodecKit.CodecBase.VVC_PROFILE_MAIN_12)
    }

    @Test
    fun testMPEG2ProfileEnum() {
        logLine("--- OH_MPEG2Profile ---")
        logLine("MPEG2_PROFILE_SIMPLE=${platform.AVCodecKit.CodecBase.MPEG2_PROFILE_SIMPLE} MPEG2_PROFILE_MAIN=${platform.AVCodecKit.CodecBase.MPEG2_PROFILE_MAIN} MPEG2_PROFILE_HIGH=${platform.AVCodecKit.CodecBase.MPEG2_PROFILE_HIGH}")
        assertNotEquals(platform.AVCodecKit.CodecBase.MPEG2_PROFILE_SIMPLE, platform.AVCodecKit.CodecBase.MPEG2_PROFILE_MAIN)
    }

    @Test
    fun testMPEG4ProfileEnum() {
        logLine("--- OH_MPEG4Profile ---")
        logLine("MPEG4_PROFILE_SIMPLE=${platform.AVCodecKit.CodecBase.MPEG4_PROFILE_SIMPLE} MPEG4_PROFILE_MAIN=${platform.AVCodecKit.CodecBase.MPEG4_PROFILE_MAIN} MPEG4_PROFILE_ADVANCED_SIMPLE=${platform.AVCodecKit.CodecBase.MPEG4_PROFILE_ADVANCED_SIMPLE}")
        assertNotEquals(platform.AVCodecKit.CodecBase.MPEG4_PROFILE_SIMPLE, platform.AVCodecKit.CodecBase.MPEG4_PROFILE_MAIN)
    }

    @Test
    fun testH263ProfileEnum() {
        logLine("--- OH_H263Profile ---")
        logLine("H263_PROFILE_BASELINE=${platform.AVCodecKit.CodecBase.H263_PROFILE_BASELINE} H263_PROFILE_VERSION_1_BACKWARD_COMPATIBILITY=${platform.AVCodecKit.CodecBase.H263_PROFILE_VERSION_1_BACKWARD_COMPATIBILITY}")
        assertNotEquals(platform.AVCodecKit.CodecBase.H263_PROFILE_BASELINE, platform.AVCodecKit.CodecBase.H263_PROFILE_VERSION_1_BACKWARD_COMPATIBILITY)
    }

    @Test
    fun testVC1ProfileEnum() {
        logLine("--- OH_VC1Profile ---")
        logLine("VC1_PROFILE_SIMPLE=${platform.AVCodecKit.CodecBase.VC1_PROFILE_SIMPLE} VC1_PROFILE_MAIN=${platform.AVCodecKit.CodecBase.VC1_PROFILE_MAIN} VC1_PROFILE_ADVANCED=${platform.AVCodecKit.CodecBase.VC1_PROFILE_ADVANCED}")
        assertNotEquals(platform.AVCodecKit.CodecBase.VC1_PROFILE_SIMPLE, platform.AVCodecKit.CodecBase.VC1_PROFILE_MAIN)
    }

    @Test
    fun testWMV3ProfileEnum() {
        logLine("--- OH_WMV3Profile ---")
        logLine("WMV3_PROFILE_SIMPLE=${platform.AVCodecKit.CodecBase.WMV3_PROFILE_SIMPLE} WMV3_PROFILE_MAIN=${platform.AVCodecKit.CodecBase.WMV3_PROFILE_MAIN}")
        assertNotEquals(platform.AVCodecKit.CodecBase.WMV3_PROFILE_SIMPLE, platform.AVCodecKit.CodecBase.WMV3_PROFILE_MAIN)
    }

    @Test
    fun testAVOutputFormatEnum() {
        logLine("--- OH_AVOutputFormat ---")
        logLine("AV_OUTPUT_FORMAT_DEFAULT=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_DEFAULT} AV_OUTPUT_FORMAT_MPEG_4=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4} AV_OUTPUT_FORMAT_M4A=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_M4A}")
        logLine("AV_OUTPUT_FORMAT_AMR=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_AMR} AV_OUTPUT_FORMAT_MP3=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MP3} AV_OUTPUT_FORMAT_WAV=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_WAV}")
        logLine("AV_OUTPUT_FORMAT_AAC=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_AAC} AV_OUTPUT_FORMAT_FLAC=${platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_FLAC}")
        assertNotEquals(platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_DEFAULT, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
    }

    @Test
    fun testAVSeekModeEnum() {
        logLine("--- OH_AVSeekMode ---")
        logLine("SEEK_MODE_NEXT_SYNC=${platform.AVCodecKit.CodecBase.SEEK_MODE_NEXT_SYNC} SEEK_MODE_PREVIOUS_SYNC=${platform.AVCodecKit.CodecBase.SEEK_MODE_PREVIOUS_SYNC} SEEK_MODE_CLOSEST_SYNC=${platform.AVCodecKit.CodecBase.SEEK_MODE_CLOSEST_SYNC}")
        assertNotEquals(platform.AVCodecKit.CodecBase.SEEK_MODE_NEXT_SYNC, platform.AVCodecKit.CodecBase.SEEK_MODE_CLOSEST_SYNC)
    }

    @Test
    fun testScalingModeEnum() {
        logLine("--- OH_ScalingMode ---")
        logLine("SCALING_MODE_SCALE_TO_WINDOW=${platform.AVCodecKit.CodecBase.SCALING_MODE_SCALE_TO_WINDOW} SCALING_MODE_SCALE_CROP=${platform.AVCodecKit.CodecBase.SCALING_MODE_SCALE_CROP}")
        assertNotEquals(platform.AVCodecKit.CodecBase.SCALING_MODE_SCALE_TO_WINDOW, platform.AVCodecKit.CodecBase.SCALING_MODE_SCALE_CROP)
    }

    @Test
    fun testBitsPerSampleEnum() {
        logLine("--- OH_BitsPerSample ---")
        logLine("SAMPLE_U8=${platform.AVCodecKit.CodecBase.SAMPLE_U8} SAMPLE_S16LE=${platform.AVCodecKit.CodecBase.SAMPLE_S16LE} SAMPLE_S24LE=${platform.AVCodecKit.CodecBase.SAMPLE_S24LE}")
        logLine("SAMPLE_S32LE=${platform.AVCodecKit.CodecBase.SAMPLE_S32LE} SAMPLE_F32LE=${platform.AVCodecKit.CodecBase.SAMPLE_F32LE} SAMPLE_U8P=${platform.AVCodecKit.CodecBase.SAMPLE_U8P}")
        logLine("SAMPLE_S16P=${platform.AVCodecKit.CodecBase.SAMPLE_S16P} SAMPLE_S24P=${platform.AVCodecKit.CodecBase.SAMPLE_S24P} SAMPLE_S32P=${platform.AVCodecKit.CodecBase.SAMPLE_S32P} SAMPLE_F32P=${platform.AVCodecKit.CodecBase.SAMPLE_F32P} INVALID_WIDTH=${platform.AVCodecKit.CodecBase.INVALID_WIDTH}")
        assertNotEquals(platform.AVCodecKit.CodecBase.SAMPLE_U8, platform.AVCodecKit.CodecBase.SAMPLE_S16LE)
    }

    @Test
    fun testColorPrimaryEnum() {
        logLine("--- OH_ColorPrimary ---")
        logLine("COLOR_PRIMARY_BT709=${platform.AVCodecKit.CodecBase.COLOR_PRIMARY_BT709} COLOR_PRIMARY_BT2020=${platform.AVCodecKit.CodecBase.COLOR_PRIMARY_BT2020} COLOR_PRIMARY_P3D65=${platform.AVCodecKit.CodecBase.COLOR_PRIMARY_P3D65}")
        assertNotEquals(platform.AVCodecKit.CodecBase.COLOR_PRIMARY_BT709, platform.AVCodecKit.CodecBase.COLOR_PRIMARY_BT2020)
    }

    @Test
    fun testTransferCharacteristicEnum() {
        logLine("--- OH_TransferCharacteristic ---")
        logLine("TRANSFER_CHARACTERISTIC_BT709=${platform.AVCodecKit.CodecBase.TRANSFER_CHARACTERISTIC_BT709} TRANSFER_CHARACTERISTIC_PQ=${platform.AVCodecKit.CodecBase.TRANSFER_CHARACTERISTIC_PQ} TRANSFER_CHARACTERISTIC_HLG=${platform.AVCodecKit.CodecBase.TRANSFER_CHARACTERISTIC_HLG}")
        assertNotEquals(platform.AVCodecKit.CodecBase.TRANSFER_CHARACTERISTIC_BT709, platform.AVCodecKit.CodecBase.TRANSFER_CHARACTERISTIC_PQ)
    }

    @Test
    fun testMatrixCoefficientEnum() {
        logLine("--- OH_MatrixCoefficient ---")
        logLine("MATRIX_COEFFICIENT_IDENTITY=${platform.AVCodecKit.CodecBase.MATRIX_COEFFICIENT_IDENTITY} MATRIX_COEFFICIENT_BT709=${platform.AVCodecKit.CodecBase.MATRIX_COEFFICIENT_BT709} MATRIX_COEFFICIENT_BT2020_NCL=${platform.AVCodecKit.CodecBase.MATRIX_COEFFICIENT_BT2020_NCL}")
        assertNotEquals(platform.AVCodecKit.CodecBase.MATRIX_COEFFICIENT_IDENTITY, platform.AVCodecKit.CodecBase.MATRIX_COEFFICIENT_BT709)
    }

    @Test
    fun testAVCLevelEnum() {
        logLine("--- OH_AVCLevel ---")
        logLine("AVC_LEVEL_1=${platform.AVCodecKit.CodecBase.AVC_LEVEL_1} AVC_LEVEL_3=${platform.AVCodecKit.CodecBase.AVC_LEVEL_3} AVC_LEVEL_5=${platform.AVCodecKit.CodecBase.AVC_LEVEL_5}")
        assertNotEquals(platform.AVCodecKit.CodecBase.AVC_LEVEL_1, platform.AVCodecKit.CodecBase.AVC_LEVEL_5)
    }

    @Test
    fun testHEVCLevelEnum() {
        logLine("--- OH_HEVCLevel ---")
        logLine("HEVC_LEVEL_1=${platform.AVCodecKit.CodecBase.HEVC_LEVEL_1} HEVC_LEVEL_3=${platform.AVCodecKit.CodecBase.HEVC_LEVEL_3} HEVC_LEVEL_5=${platform.AVCodecKit.CodecBase.HEVC_LEVEL_5}")
        assertNotEquals(platform.AVCodecKit.CodecBase.HEVC_LEVEL_1, platform.AVCodecKit.CodecBase.HEVC_LEVEL_5)
    }

    @Test
    fun testVVCLevelEnum() {
        logLine("--- OH_VVCLevel ---")
        logLine("VVC_LEVEL_1=${platform.AVCodecKit.CodecBase.VVC_LEVEL_1} VVC_LEVEL_2=${platform.AVCodecKit.CodecBase.VVC_LEVEL_2} VVC_LEVEL_5=${platform.AVCodecKit.CodecBase.VVC_LEVEL_5}")
        assertNotEquals(platform.AVCodecKit.CodecBase.VVC_LEVEL_1, platform.AVCodecKit.CodecBase.VVC_LEVEL_2)
    }

    @Test
    fun testMPEG2LevelEnum() {
        logLine("--- OH_MPEG2Level ---")
        logLine("MPEG2_LEVEL_LOW=${platform.AVCodecKit.CodecBase.MPEG2_LEVEL_LOW} MPEG2_LEVEL_MAIN=${platform.AVCodecKit.CodecBase.MPEG2_LEVEL_MAIN} MPEG2_LEVEL_HIGH=${platform.AVCodecKit.CodecBase.MPEG2_LEVEL_HIGH}")
        assertNotEquals(platform.AVCodecKit.CodecBase.MPEG2_LEVEL_LOW, platform.AVCodecKit.CodecBase.MPEG2_LEVEL_MAIN)
    }

    @Test
    fun testMPEG4LevelEnum() {
        logLine("--- OH_MPEG4Level ---")
        logLine("MPEG4_LEVEL_0=${platform.AVCodecKit.CodecBase.MPEG4_LEVEL_0} MPEG4_LEVEL_1=${platform.AVCodecKit.CodecBase.MPEG4_LEVEL_1} MPEG4_LEVEL_5=${platform.AVCodecKit.CodecBase.MPEG4_LEVEL_5}")
        assertNotEquals(platform.AVCodecKit.CodecBase.MPEG4_LEVEL_0, platform.AVCodecKit.CodecBase.MPEG4_LEVEL_5)
    }

    @Test
    fun testH263LevelEnum() {
        logLine("--- OH_H263Level ---")
        logLine("H263_LEVEL_10=${platform.AVCodecKit.CodecBase.H263_LEVEL_10} H263_LEVEL_50=${platform.AVCodecKit.CodecBase.H263_LEVEL_50}")
        assertNotEquals(platform.AVCodecKit.CodecBase.H263_LEVEL_10, platform.AVCodecKit.CodecBase.H263_LEVEL_50)
    }

    @Test
    fun testVC1LevelEnum() {
        logLine("--- OH_VC1Level ---")
        logLine("VC1_LEVEL_L0=${platform.AVCodecKit.CodecBase.VC1_LEVEL_L0} VC1_LEVEL_HIGH=${platform.AVCodecKit.CodecBase.VC1_LEVEL_HIGH}")
        assertNotEquals(platform.AVCodecKit.CodecBase.VC1_LEVEL_L0, platform.AVCodecKit.CodecBase.VC1_LEVEL_HIGH)
    }

    @Test
    fun testWMV3LevelEnum() {
        logLine("--- OH_WMV3Level ---")
        logLine("WMV3_LEVEL_LOW=${platform.AVCodecKit.CodecBase.WMV3_LEVEL_LOW} WMV3_LEVEL_MEDIUM=${platform.AVCodecKit.CodecBase.WMV3_LEVEL_MEDIUM} WMV3_LEVEL_HIGH=${platform.AVCodecKit.CodecBase.WMV3_LEVEL_HIGH}")
        assertNotEquals(platform.AVCodecKit.CodecBase.WMV3_LEVEL_LOW, platform.AVCodecKit.CodecBase.WMV3_LEVEL_HIGH)
    }

    @Test
    fun testTemporalGopReferenceModeEnum() {
        logLine("--- OH_TemporalGopReferenceMode ---")
        logLine("ADJACENT_REFERENCE=${platform.AVCodecKit.CodecBase.ADJACENT_REFERENCE} JUMP_REFERENCE=${platform.AVCodecKit.CodecBase.JUMP_REFERENCE} UNIFORMLY_SCALED_REFERENCE=${platform.AVCodecKit.CodecBase.UNIFORMLY_SCALED_REFERENCE}")
        assertNotEquals(platform.AVCodecKit.CodecBase.ADJACENT_REFERENCE, platform.AVCodecKit.CodecBase.JUMP_REFERENCE)
    }

    @Test
    fun testBitrateModeEnum() {
        logLine("--- OH_BitrateMode ---")
        logLine("BITRATE_MODE_CBR=${platform.AVCodecKit.CodecBase.BITRATE_MODE_CBR} BITRATE_MODE_VBR=${platform.AVCodecKit.CodecBase.BITRATE_MODE_VBR} BITRATE_MODE_CQ=${platform.AVCodecKit.CodecBase.BITRATE_MODE_CQ} BITRATE_MODE_SQR=${platform.AVCodecKit.CodecBase.BITRATE_MODE_SQR}")
        assertNotEquals(platform.AVCodecKit.CodecBase.BITRATE_MODE_CBR, platform.AVCodecKit.CodecBase.BITRATE_MODE_VBR)
    }

    @Test
    fun testStructTypes() {
        memScoped {
            logLine("--- OH_AVCodecAsyncCallback / OH_AVCodecCallback / OH_AVDataSource / OH_AVDataSourceExt ---")
            val asyncCallback = alloc<platform.AVCodecKit.CodecBase.OH_AVCodecAsyncCallback>().apply {
                onError = null
                onStreamChanged = null
                onNeedInputData = null
                onNeedOutputData = null
            }
            logLine("OH_AVCodecAsyncCallback allocated")
            val callback = alloc<platform.AVCodecKit.CodecBase.OH_AVCodecCallback>().apply {
                onError = null
                onStreamChanged = null
                onNeedInputBuffer = null
                onNewOutputBuffer = null
            }
            logLine("OH_AVCodecCallback allocated")
            val dataSource = alloc<platform.AVCodecKit.CodecBase.OH_AVDataSource>().apply {
                size = 0L
                readAt = null
            }
            logLine("OH_AVDataSource size=${dataSource.size}")
            try {
                val dataSourceExt = alloc<platform.AVCodecKit.CodecBase.OH_AVDataSourceExt>().apply {
                    size = 0L
                    readAt = null
                }
                logLine("OH_AVDataSourceExt size=${dataSourceExt.size} (API 20)")
            } catch (e: Throwable) {
                logLine("OH_AVDataSourceExt (API 20) exception: $e")
            }
        }
    }

    @Test
    fun testAudioChannelSetEnum() {
        logLine("--- AudioChannelSet ---")
        logLine("FRONT_LEFT=${platform.AVCodecKit.CodecBase.FRONT_LEFT} FRONT_RIGHT=${platform.AVCodecKit.CodecBase.FRONT_RIGHT} FRONT_CENTER=${platform.AVCodecKit.CodecBase.FRONT_CENTER}")
        logLine("LOW_FREQUENCY=${platform.AVCodecKit.CodecBase.LOW_FREQUENCY} BACK_LEFT=${platform.AVCodecKit.CodecBase.BACK_LEFT} BACK_RIGHT=${platform.AVCodecKit.CodecBase.BACK_RIGHT}")
        logLine("AMBISONICS_ACN0=${platform.AVCodecKit.CodecBase.AMBISONICS_ACN0} AMBISONICS_W=${platform.AVCodecKit.CodecBase.AMBISONICS_W}")
        assertNotEquals(platform.AVCodecKit.CodecBase.FRONT_LEFT, platform.AVCodecKit.CodecBase.FRONT_RIGHT)
    }

    @Test
    fun testAudioChannelLayoutEnum() {
        logLine("--- AudioChannelLayout ---")
        logLine("UNKNOWN_CHANNEL_LAYOUT=${platform.AVCodecKit.CodecBase.UNKNOWN_CHANNEL_LAYOUT} MONO=${platform.AVCodecKit.CodecBase.MONO} STEREO=${platform.AVCodecKit.CodecBase.STEREO}")
        logLine("CH_2POINT1=${platform.AVCodecKit.CodecBase.CH_2POINT1} SURROUND=${platform.AVCodecKit.CodecBase.SURROUND} CH_5POINT1=${platform.AVCodecKit.CodecBase.CH_5POINT1} CH_7POINT1=${platform.AVCodecKit.CodecBase.CH_7POINT1}")
        logLine("HOA_FIRST=${platform.AVCodecKit.CodecBase.HOA_FIRST} HOA_SECOND=${platform.AVCodecKit.CodecBase.HOA_SECOND} HOA_THIRD=${platform.AVCodecKit.CodecBase.HOA_THIRD}")
        assertNotEquals(platform.AVCodecKit.CodecBase.MONO, platform.AVCodecKit.CodecBase.STEREO)
    }
}
