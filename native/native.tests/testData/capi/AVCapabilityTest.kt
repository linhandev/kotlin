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
class AVCapabilityTest {

    private fun logLine(message: String) = println("[stdout] AVCapabilityTest $message")

    // ---------- Enums ----------
    @Test
    fun testEnum_AVCapability_Category() {
        val hardware = platform.AVCodecKit.AVCapability.HARDWARE
        val software = platform.AVCodecKit.AVCapability.SOFTWARE
        logLine("OH_AVCodecCategory: HARDWARE=$hardware, SOFTWARE=$software")
        assertNotEquals(hardware, software, "HARDWARE and SOFTWARE should be distinct")
    }

    @Test
    fun testEnum_AVCapability_Feature() {
        val temporalScalability = platform.AVCodecKit.AVCapability.VIDEO_ENCODER_TEMPORAL_SCALABILITY
        val longTermReference = platform.AVCodecKit.AVCapability.VIDEO_ENCODER_LONG_TERM_REFERENCE
        val lowLatency = platform.AVCodecKit.AVCapability.VIDEO_LOW_LATENCY
        val bFrame = platform.AVCodecKit.AVCapability.VIDEO_ENCODER_B_FRAME
        logLine("OH_AVCapabilityFeature: TEMPORAL_SCALABILITY=$temporalScalability, LONG_TERM_REFERENCE=$longTermReference, LOW_LATENCY=$lowLatency, B_FRAME=$bFrame")
        assertNotEquals(temporalScalability, longTermReference, "Feature enums should be distinct")
        assertNotEquals(temporalScalability, lowLatency, "Feature enums should be distinct")
        assertNotEquals(temporalScalability, bFrame, "Feature enums should be distinct")
    }

    @Test
    fun testOH_AVCodec_GetCapability() {
        val audioMpegMime = "audio/mp4a-latm"
        val audioEncoderCapability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        logLine("OH_AVCodec_GetCapability(\"$audioMpegMime\", true) result: ${if (audioEncoderCapability != null) "non-null" else "null"}")
    }

    @Test
    fun testOH_AVCodec_GetCapabilityByCategory_Hardware() {
        val audioMpegMime = "audio/mp4a-latm"
        val hardwareEncoderCapability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapabilityByCategory(
            audioMpegMime,
            true,
            platform.AVCodecKit.AVCapability.HARDWARE
        )
        logLine("OH_AVCodec_GetCapabilityByCategory(\"$audioMpegMime\", true, HARDWARE) result: ${if (hardwareEncoderCapability != null) "non-null" else "null"}")
    }

    @Test
    fun testOH_AVCodec_GetCapabilityByCategory_Software() {
        val audioMpegMime = "audio/mp4a-latm"
        val softwareEncoderCapability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapabilityByCategory(
            audioMpegMime,
            true,
            platform.AVCodecKit.AVCapability.SOFTWARE
        )
        logLine("OH_AVCodec_GetCapabilityByCategory(\"$audioMpegMime\", true, SOFTWARE) result: ${if (softwareEncoderCapability != null) "non-null" else "null"}")
    }

    @Test
    fun testOH_AVCapability_IsHardware() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val isHardware = platform.AVCodecKit.AVCapability.OH_AVCapability_IsHardware(capability)
        logLine("OH_AVCapability_IsHardware(capability) result: $isHardware")
    }

    @Test
    fun testOH_AVCapability_GetName() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val name = platform.AVCodecKit.AVCapability.OH_AVCapability_GetName(capability)
        logLine("OH_AVCapability_GetName(capability) result: ${name?.toKString() ?: "null"}")
    }

    @Test
    fun testOH_AVCapability_GetMaxSupportedInstances() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val maxInstances = platform.AVCodecKit.AVCapability.OH_AVCapability_GetMaxSupportedInstances(capability)
        logLine("OH_AVCapability_GetMaxSupportedInstances(capability) result: $maxInstances")
    }

    @Test
    fun testOH_AVCapability_GetEncoderBitrateRange() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val bitrateRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val bitrateRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetEncoderBitrateRange(
            capability,
            bitrateRange.ptr
        )
        logLine("OH_AVCapability_GetEncoderBitrateRange(capability, ptr) result: $bitrateRangeResult")
    }

    @Test
    fun testOH_AVCapability_GetEncoderQualityRange() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val qualityRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val qualityRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetEncoderQualityRange(
            capability,
            qualityRange.ptr
        )
        logLine("OH_AVCapability_GetEncoderQualityRange(capability, ptr) result: $qualityRangeResult")
    }

    @Test
    fun testOH_AVCapability_GetEncoderComplexityRange() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val complexityRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val complexityRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetEncoderComplexityRange(
            capability,
            complexityRange.ptr
        )
        logLine("OH_AVCapability_GetEncoderComplexityRange(capability, ptr) result: $complexityRangeResult")
    }

    @Test
    fun testOH_AVCapability_IsEncoderBitrateModeSupported() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val isCbrSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_IsEncoderBitrateModeSupported(
            capability,
            platform.AVCodecKit.CodecBase.BITRATE_MODE_CBR
        )
        logLine("OH_AVCapability_IsEncoderBitrateModeSupported(capability, CBR) result: $isCbrSupported")
    }

    @Test
    fun testOH_AVCapability_GetVideoSupportedNativeBufferFormats() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        try {
            val formatNum = nativeHeap.alloc<UIntVar>()
            val result = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoSupportedNativeBufferFormats(
                capability,
                null,
                formatNum.ptr
            )
            logLine("OH_AVCapability_GetVideoSupportedNativeBufferFormats(capability, null, ptr) result: $result, num: ${formatNum.value}")
        } catch (e: Throwable) {
            logLine("OH_AVCapability_GetVideoSupportedNativeBufferFormats (API 22) exception: $e")
        }
    }

    @Test
    fun testOH_AVCapability_GetAudioSupportedSampleRates() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val sampleRateNum = nativeHeap.alloc<UIntVar>()
        val sampleRatesResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetAudioSupportedSampleRates(
            capability,
            null,
            sampleRateNum.ptr
        )
        logLine("OH_AVCapability_GetAudioSupportedSampleRates(capability, null, ptr) result: $sampleRatesResult, sampleRateNum: ${sampleRateNum.value}")
    }

    @Test
    fun testOH_AVCapability_GetAudioSupportedSampleRateRanges() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        try {
            val rangesNum = nativeHeap.alloc<UIntVar>()
            val sampleRateRangesResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetAudioSupportedSampleRateRanges(
                capability,
                null,
                rangesNum.ptr
            )
            logLine("OH_AVCapability_GetAudioSupportedSampleRateRanges(capability, null, ptr) result: $sampleRateRangesResult, rangesNum: ${rangesNum.value}")
        } catch (e: Throwable) {
            logLine("OH_AVCapability_GetAudioSupportedSampleRateRanges (API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVCapability_GetAudioChannelCountRange() {
        val audioMpegMime = "audio/mp4a-latm"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(audioMpegMime, true)
        val channelCountRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val channelCountRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetAudioChannelCountRange(
            capability,
            channelCountRange.ptr
        )
        logLine("OH_AVCapability_GetAudioChannelCountRange(capability, ptr) result: $channelCountRangeResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoWidthAlignment() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val widthAlignment = nativeHeap.alloc<IntVar>()
        val widthAlignmentResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoWidthAlignment(
            capability,
            widthAlignment.ptr
        )
        logLine("OH_AVCapability_GetVideoWidthAlignment(capability, ptr) result: $widthAlignmentResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoHeightAlignment() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val heightAlignment = nativeHeap.alloc<IntVar>()
        val heightAlignmentResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoHeightAlignment(
            capability,
            heightAlignment.ptr
        )
        logLine("OH_AVCapability_GetVideoHeightAlignment(capability, ptr) result: $heightAlignmentResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoWidthRange() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val widthRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val widthRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoWidthRange(
            capability,
            widthRange.ptr
        )
        logLine("OH_AVCapability_GetVideoWidthRange(capability, ptr) result: $widthRangeResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoHeightRange() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val heightRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val heightRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoHeightRange(
            capability,
            heightRange.ptr
        )
        logLine("OH_AVCapability_GetVideoHeightRange(capability, ptr) result: $heightRangeResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoWidthRangeForHeight() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val widthRangeForHeight = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val widthRangeForHeightResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoWidthRangeForHeight(
            capability,
            720,
            widthRangeForHeight.ptr
        )
        logLine("OH_AVCapability_GetVideoWidthRangeForHeight(capability, 720, ptr) result: $widthRangeForHeightResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoHeightRangeForWidth() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val heightRangeForWidth = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val heightRangeForWidthResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoHeightRangeForWidth(
            capability,
            1280,
            heightRangeForWidth.ptr
        )
        logLine("OH_AVCapability_GetVideoHeightRangeForWidth(capability, 1280, ptr) result: $heightRangeForWidthResult")
    }

    @Test
    fun testOH_AVCapability_IsVideoSizeSupported() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val isSizeSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_IsVideoSizeSupported(
            capability,
            1280,
            720
        )
        logLine("OH_AVCapability_IsVideoSizeSupported(capability, 1280, 720) result: $isSizeSupported")
    }

    @Test
    fun testOH_AVCapability_GetVideoFrameRateRange() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val frameRateRange = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val frameRateRangeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoFrameRateRange(
            capability,
            frameRateRange.ptr
        )
        logLine("OH_AVCapability_GetVideoFrameRateRange(capability, ptr) result: $frameRateRangeResult")
    }

    @Test
    fun testOH_AVCapability_GetVideoFrameRateRangeForSize() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val frameRateRangeForSize = nativeHeap.alloc<platform.AVCodecKit.AVCapability.OH_AVRange>()
        val frameRateRangeForSizeResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoFrameRateRangeForSize(
            capability,
            1280,
            720,
            frameRateRangeForSize.ptr
        )
        logLine("OH_AVCapability_GetVideoFrameRateRangeForSize(capability, 1280, 720, ptr) result: $frameRateRangeForSizeResult")
    }

    @Test
    fun testOH_AVCapability_AreVideoSizeAndFrameRateSupported() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val areSizeAndFrameRateSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_AreVideoSizeAndFrameRateSupported(
            capability,
            1280,
            720,
            30
        )
        logLine("OH_AVCapability_AreVideoSizeAndFrameRateSupported(capability, 1280, 720, 30) result: $areSizeAndFrameRateSupported")
    }

    @Test
    fun testOH_AVCapability_GetVideoSupportedPixelFormats() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val pixelFormatNum = nativeHeap.alloc<UIntVar>()
        val pixelFormatsResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetVideoSupportedPixelFormats(
            capability,
            null,
            pixelFormatNum.ptr
        )
        logLine("OH_AVCapability_GetVideoSupportedPixelFormats(capability, null, ptr) result: $pixelFormatsResult, pixelFormatNum: ${pixelFormatNum.value}")
    }

    @Test
    fun testOH_AVCapability_GetSupportedProfiles() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val profileNum = nativeHeap.alloc<UIntVar>()
        val profilesResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetSupportedProfiles(
            capability,
            null,
            profileNum.ptr
        )
        logLine("OH_AVCapability_GetSupportedProfiles(capability, null, ptr) result: $profilesResult, profileNum: ${profileNum.value}")
    }

    @Test
    fun testOH_AVCapability_GetSupportedLevelsForProfile() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val levelNum = nativeHeap.alloc<UIntVar>()
        val levelsResult = platform.AVCodecKit.AVCapability.OH_AVCapability_GetSupportedLevelsForProfile(
            capability,
            0,
            null,
            levelNum.ptr
        )
        logLine("OH_AVCapability_GetSupportedLevelsForProfile(capability, 0, null, ptr) result: $levelsResult, levelNum: ${levelNum.value}")
    }

    @Test
    fun testOH_AVCapability_AreProfileAndLevelSupported() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val areProfileAndLevelSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_AreProfileAndLevelSupported(
            capability,
            0,
            0
        )
        logLine("OH_AVCapability_AreProfileAndLevelSupported(capability, 0, 0) result: $areProfileAndLevelSupported")
    }

    @Test
    fun testOH_AVCapability_IsFeatureSupported_TemporalScalability() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val isFeatureSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_IsFeatureSupported(
            capability,
            platform.AVCodecKit.AVCapability.VIDEO_ENCODER_TEMPORAL_SCALABILITY
        )
        logLine("OH_AVCapability_IsFeatureSupported(capability, TEMPORAL_SCALABILITY) result: $isFeatureSupported")
    }

    @Test
    fun testOH_AVCapability_IsFeatureSupported_LongTermReference() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val isFeatureSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_IsFeatureSupported(
            capability,
            platform.AVCodecKit.AVCapability.VIDEO_ENCODER_LONG_TERM_REFERENCE
        )
        logLine("OH_AVCapability_IsFeatureSupported(capability, LONG_TERM_REFERENCE) result: $isFeatureSupported")
    }

    @Test
    fun testOH_AVCapability_IsFeatureSupported_LowLatency() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val isFeatureSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_IsFeatureSupported(
            capability,
            platform.AVCodecKit.AVCapability.VIDEO_LOW_LATENCY
        )
        logLine("OH_AVCapability_IsFeatureSupported(capability, LOW_LATENCY) result: $isFeatureSupported")
    }

    @Test
    fun testOH_AVCapability_IsFeatureSupported_BFrame() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        try {
            val isFeatureSupported = platform.AVCodecKit.AVCapability.OH_AVCapability_IsFeatureSupported(
                capability,
                platform.AVCodecKit.AVCapability.VIDEO_ENCODER_B_FRAME
            )
            logLine("OH_AVCapability_IsFeatureSupported(capability, B_FRAME) result: $isFeatureSupported")
        } catch (e: Throwable) {
            logLine("OH_AVCapability_IsFeatureSupported(capability, VIDEO_ENCODER_B_FRAME, API 20) exception: $e")
        }
    }

    @Test
    fun testOH_AVCapability_GetFeatureProperties_TemporalScalability() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val featureProperties = platform.AVCodecKit.AVCapability.OH_AVCapability_GetFeatureProperties(
            capability,
            platform.AVCodecKit.AVCapability.VIDEO_ENCODER_TEMPORAL_SCALABILITY
        )
        logLine("OH_AVCapability_GetFeatureProperties(capability, TEMPORAL_SCALABILITY) result: ${if (featureProperties != null) "non-null" else "null"}")
    }

    @Test
    fun testOH_AVCapability_GetFeatureProperties_LongTermReference() {
        val videoAvcMime = "video/avc"
        val capability = platform.AVCodecKit.AVCapability.OH_AVCodec_GetCapability(videoAvcMime, true)
        val featureProperties = platform.AVCodecKit.AVCapability.OH_AVCapability_GetFeatureProperties(
            capability,
            platform.AVCodecKit.AVCapability.VIDEO_ENCODER_LONG_TERM_REFERENCE
        )
        logLine("OH_AVCapability_GetFeatureProperties(capability, LONG_TERM_REFERENCE) result: ${if (featureProperties != null) "non-null" else "null"}")
    }
}
