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
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_core
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_adec
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_aenc
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_codecbase
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_vdec
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_venc
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_avdemuxer
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_avmuxer
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_avsource
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_avcencinfo
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_media_acodec
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_avscreen_capture
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lavplayer
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_receiver
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_packer
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_source
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohimage
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lpixelmap
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lpicture
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_receiver_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lpixelmap_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_packer_ndk.z
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lvideo_processing
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_processing
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lohavsession
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_effect
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lnative_drm
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lmedia_asset_manager
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -limage_common
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.multimedia.*
import cnames.structs.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_multimediaTest {

    private fun logLine(msg: String) = println(msg)

    // native_avcodec_audiodecoder.h (API 9)
    @Test
    fun testNative_avcodec_audiodecoder_h() {
        memScoped {
            val codec = OH_AudioDecoder_CreateByMime("audio/mp4a-latm")
            if (codec != null) OH_AudioDecoder_Destroy(codec)
            logLine("OH_AudioDecoder_CreateByMime/Destroy ok")
        }
    }

    // native_avcodec_audioencoder.h (API 9)
    @Test
    fun testNative_avcodec_audioencoder_h() {
        memScoped {
            val codec = OH_AudioEncoder_CreateByMime("audio/mp4a-latm")
            if (codec != null) OH_AudioEncoder_Destroy(codec)
            logLine("OH_AudioEncoder_CreateByMime/Destroy ok")
        }
    }

    // native_avcodec_base.h（仅类型/枚举，无独立函数）
    @Test
    fun testNative_avcodec_base_h() {
        assertEquals<Int>(0, AV_OUTPUT_FORMAT_DEFAULT.toInt())
        logLine("AV_OUTPUT_FORMAT_DEFAULT=$AV_OUTPUT_FORMAT_DEFAULT")
    }

    // native_avcodec_videodecoder.h (API 9)
    @Test
    fun testNative_avcodec_videodecoder_h() {
        memScoped {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            if (codec != null) OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_CreateByMime/Destroy ok")
        }
    }

    // native_avcodec_videoencoder.h (API 9)
    @Test
    fun testNative_avcodec_videoencoder_h() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")
            if (codec != null) OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_CreateByMime/Destroy ok")
        }
    }

    // native_averrors.h
    @Test
    fun testNative_averrors_h() {
        assertEquals<Int>(0, AV_ERR_OK.toInt())
        logLine("AV_ERR_OK=$AV_ERR_OK")
    }

    // native_avformat.h (API 9)
    @Test
    fun testNative_avformat_h() {
        memScoped {
            val format = OH_AVFormat_Create()
            assertNotNull(format)
            if (format != null) OH_AVFormat_Destroy(format)
            logLine("OH_AVFormat_Create/Destroy ok")
        }
    }

    // native_avmemory.h (API 10)
    @Test
    fun testNative_avmemory_h() {
        memScoped {
            val mem = OH_AVMemory_Create(64)
            if (mem != null) OH_AVMemory_Destroy(mem)
            logLine("OH_AVMemory_Create(64)/Destroy ok")
        }
    }

    // image_mdk.h (API 10) — OH_Image_InitImageNative，传 null 仅做符号调用
    @Test
    fun testImage_mdk_h() {
        val nativePtr = OH_Image_InitImageNative(null, null)
        logLine("OH_Image_InitImageNative(null,null)=$nativePtr (expect null)")
    }

    // image_receiver_native.h (API 12)
    @Test
    fun testImage_receiver_native_h() {
        memScoped {
            val optionsPtr = alloc<CPointerVar<OH_ImageReceiverOptions>>()
            val rc = OH_ImageReceiverOptions_Create(optionsPtr.ptr)
            val options = optionsPtr.value
            if (options != null) OH_ImageReceiverOptions_Release(options)
            logLine("OH_ImageReceiverOptions_Create/Release rc=$rc")
        }
    }

    // image_packer_native.h (API 12)
    @Test
    fun testImage_packer_native_h() {
        memScoped {
            val optionsPtr = alloc<CPointerVar<OH_PackingOptions>>()
            val rc = OH_PackingOptions_Create(optionsPtr.ptr)
            val options = optionsPtr.value
            if (options != null) OH_PackingOptions_Release(options)
            logLine("OH_PackingOptions_Create/Release rc=$rc")
        }
    }

    // image_source_native.h (API 12)
    @Test
    fun testImage_source_native_h() {
        memScoped {
            val infoPtr = alloc<CPointerVar<OH_ImageSource_Info>>()
            val rc = OH_ImageSourceInfo_Create(infoPtr.ptr)
            val info = infoPtr.value
            if (info != null) OH_ImageSourceInfo_Release(info)
            logLine("OH_ImageSourceInfo_Create/Release rc=$rc")
        }
    }

    // image_native.h (API 12) — 本头文件函数 OH_ImageNative_GetImageSize
    @Test
    fun testImage_native_h() {
        memScoped {
            val size = alloc<Image_Size>()
            val rc = OH_ImageNative_GetImageSize(null, size.ptr)
            logLine("OH_ImageNative_GetImageSize(null) rc=$rc")
        }
    }

    // pixelmap_native.h（本头文件枚举）
    @Test
    fun testPixelmap_native_h() {
        assertEquals<Int>(0, PIXELMAP_ALPHA_TYPE_UNKNOWN.toInt())
        logLine("PIXELMAP_ALPHA_TYPE_UNKNOWN=$PIXELMAP_ALPHA_TYPE_UNKNOWN")
    }

    // picture_native.h (API 13)
    @Test
    fun testPicture_native_h() {
        OH_PictureNative_Release(null)
        logLine("OH_PictureNative_Release(null) ok")
    }

    // image_receiver_mdk.h (API 10)
    @Test
    fun testImage_receiver_mdk_h() {
        OH_Image_Receiver_Release(null)
        logLine("OH_Image_Receiver_Release(null) ok")
    }

    // image_pixel_map_mdk.h（需 napi，用本头文件相关常量/枚举）
    @Test
    fun testImage_pixel_map_mdk_h() {
        assertEquals<Int>(0, PIXELMAP_ALPHA_TYPE_UNKNOWN.toInt())
        logLine("image_pixel_map_mdk PIXELMAP_ALPHA_TYPE_UNKNOWN ok")
    }

    // image_packer_mdk.h (API 11)
    @Test
    fun testImage_packer_mdk_h() {
        OH_ImagePacker_Release(null)
        logLine("OH_ImagePacker_Release(null) ok")
    }

    // video_processing.h (API 12)
    @Test
    fun testVideo_processing_h() {
        val rc = OH_VideoProcessing_InitializeEnvironment()
        if (rc.toInt() == 0) OH_VideoProcessing_DeinitializeEnvironment()
        logLine("OH_VideoProcessing_InitializeEnvironment/DeinitializeEnvironment rc=$rc")
    }

    // image_processing_types.h (API 13，仅常量)
    @Test
    fun testImage_processing_types_h() {
        assertNotNull(IMAGE_PROCESSING_TYPE_COLOR_SPACE_CONVERSION)
        logLine("IMAGE_PROCESSING_TYPE_COLOR_SPACE_CONVERSION=$IMAGE_PROCESSING_TYPE_COLOR_SPACE_CONVERSION")
    }

    // video_processing_types.h (API 12，仅常量)
    @Test
    fun testVideo_processing_types_h() {
        assertNotNull(VIDEO_PROCESSING_TYPE_COLOR_SPACE_CONVERSION)
        logLine("VIDEO_PROCESSING_TYPE_COLOR_SPACE_CONVERSION=$VIDEO_PROCESSING_TYPE_COLOR_SPACE_CONVERSION")
    }

    // image_processing.h (API 13)
    @Test
    fun testImage_processing_h() {
        val rc = OH_ImageProcessing_InitializeEnvironment()
        if (rc.toInt() == 0) OH_ImageProcessing_DeinitializeEnvironment()
        logLine("OH_ImageProcessing_InitializeEnvironment/DeinitializeEnvironment rc=$rc")
    }

    // native_avsession.h (API 13)（本头文件枚举）
    @Test
    fun testNative_avsession_h() {
        assertEquals<Int>(0, SESSION_TYPE_AUDIO.toInt())
        logLine("SESSION_TYPE_AUDIO=$SESSION_TYPE_AUDIO")
    }

    // native_avmetadata.h (API 13)
    @Test
    fun testNative_avmetadata_h() {
        memScoped {
            val builderPtr = alloc<CPointerVar<OH_AVMetadataBuilderStruct>>()
            val rc = OH_AVMetadataBuilder_Create(builderPtr.ptr)
            val builder = builderPtr.value
            if (builder != null) OH_AVMetadataBuilder_Destroy(builder)
            logLine("OH_AVMetadataBuilder_Create/Destroy rc=$rc")
        }
    }

    // image_effect.h (API 12)
    @Test
    fun testImage_effect_h() {
        memScoped {
            OH_EffectFilter_ReleaseFilterNames()
            logLine("OH_EffectFilter_ReleaseFilterNames() ok")
        }
    }

    // image_effect_filter.h (API 12)
    @Test
    fun testImage_effect_filter_h() {
        memScoped {
            val info = OH_EffectFilterInfo_Create()
            if (info != null) {
                OH_EffectFilterInfo_Release(info)
            }
            logLine("OH_EffectFilterInfo_Create/Release ok")
        }
    }

    // image_effect_errors.h (API 12)
    @Test
    fun testImage_effect_errors_h() {
        assertEquals<Int>(0, EFFECT_SUCCESS.toInt())
        logLine("EFFECT_SUCCESS=$EFFECT_SUCCESS")
    }

    // native_audio_channel_layout.h (API 11)（本头文件枚举）
    @Test
    fun testNative_audio_channel_layout_h() {
        assertNotNull(CH_SET_FRONT_LEFT)
        logLine("CH_SET_FRONT_LEFT=$CH_SET_FRONT_LEFT")
    }

    // native_mediakeysession.h (API 11)（需 session 指针，用本模块枚举或 Destroy(null)）
    @Test
    fun testNative_mediakeysession_h() {
        OH_MediaKeySession_Destroy(null)
        logLine("OH_MediaKeySession_Destroy(null) ok")
    }

    // native_mediakeysystem.h (API 11)
    @Test
    fun testNative_mediakeysystem_h() {
        val supported = OH_MediaKeySystem_IsSupported("org.w3.clearkey")
        logLine("OH_MediaKeySystem_IsSupported=$supported")
    }

    // native_avdemuxer.h (API 10) — 本头文件 OH_AVDemuxer_CreateWithSource / OH_AVDemuxer_Destroy
    @Test
    fun testNative_avdemuxer_h() {
        memScoped {
            val source = OH_AVSource_CreateWithURI(null)
            val demuxer = if (source != null) OH_AVDemuxer_CreateWithSource(source) else null
            if (demuxer != null) OH_AVDemuxer_Destroy(demuxer)
            if (source != null) OH_AVSource_Destroy(source)
            logLine("OH_AVDemuxer_CreateWithSource/Destroy ok")
        }
    }

    // avcodec_audio_channel_layout.h（本头文件枚举）
    @Test
    fun testAvcodec_audio_channel_layout_h() {
        assertNotNull(CH_SET_FRONT_LEFT)
        logLine("CH_SET_FRONT_LEFT=$CH_SET_FRONT_LEFT")
    }

    // native_avsource.h (API 10)
    @Test
    fun testNative_avsource_h() {
        memScoped {
            val source = OH_AVSource_CreateWithURI(null)
            if (source != null) OH_AVSource_Destroy(source)
            logLine("OH_AVSource_CreateWithURI/Destroy ok")
        }
    }

    // native_avcapability.h (API 10)
    @Test
    fun testNative_avcapability_h() {
        memScoped {
            val cap = OH_AVCodec_GetCapability("video/avc", false)
            val isHw = if (cap != null) OH_AVCapability_IsHardware(cap) else false
            logLine("OH_AVCodec_GetCapability/OH_AVCapability_IsHardware isHw=$isHw")
        }
    }

    // native_cencinfo.h (API 12)
    @Test
    fun testNative_cencinfo_h() {
        memScoped {
            val cenc = OH_AVCencInfo_Create()
            if (cenc != null) OH_AVCencInfo_Destroy(cenc)
            logLine("OH_AVCencInfo_Create/Destroy ok")
        }
    }

    // native_avbuffer_info.h
    @Test
    fun testNative_avbuffer_info_h() {
        assertEquals<Int>(0, AVCODEC_BUFFER_FLAGS_NONE.toInt())
        logLine("AVCODEC_BUFFER_FLAGS_NONE=$AVCODEC_BUFFER_FLAGS_NONE")
    }

    // avplayer.h (API 11)
    @Test
    fun testAvplayer_h() {
        memScoped {
            val player = OH_AVPlayer_Create()
            if (player != null) OH_AVPlayer_Release(player)
            logLine("OH_AVPlayer_Create/Release ok")
        }
    }

    // native_avmuxer.h (API 10)
    @Test
    fun testNative_avmuxer_h() {
        memScoped {
            val muxer = OH_AVMuxer_Create(-1, AV_OUTPUT_FORMAT_DEFAULT)
            if (muxer != null) OH_AVMuxer_Destroy(muxer)
            logLine("OH_AVMuxer_Create/Destroy ok")
        }
    }

    // native_avscreen_capture_base.h（仅类型，错误码在 errors 头文件；用 capture 头文件函数覆盖类型）
    @Test
    fun testNative_avscreen_capture_base_h() {
        memScoped {
            val capture = OH_AVScreenCapture_Create()
            if (capture != null) OH_AVScreenCapture_Release(capture)
            logLine("OH_AVScreenCapture_Create/Release (type in base) ok")
        }
    }

    // native_avcodec_audiocodec.h (API 11)
    @Test
    fun testNative_avcodec_audiocodec_h() {
        memScoped {
            val codec = OH_AudioCodec_CreateByName("c2.ffmpeg.aac.decoder")
            if (codec != null) OH_AudioCodec_Destroy(codec)
            logLine("OH_AudioCodec_CreateByName/Destroy ok")
        }
    }

    // native_avscreen_capture_errors.h（本头文件枚举）
    @Test
    fun testNative_avscreen_capture_errors_h() {
        assertEquals<Int>(0, AV_SCREEN_CAPTURE_ERR_OK.toInt())
        logLine("AV_SCREEN_CAPTURE_ERR_OK=$AV_SCREEN_CAPTURE_ERR_OK")
    }

    // native_avbuffer.h (API 11)
    @Test
    fun testNative_avbuffer_h() {
        memScoped {
            val buf = OH_AVBuffer_Create(64)
            if (buf != null) OH_AVBuffer_Destroy(buf)
            logLine("OH_AVBuffer_Create/Destroy ok")
        }
    }

    // native_avscreen_capture.h (API 10)
    @Test
    fun testNative_avscreen_capture_h() {
        memScoped {
            val capture = OH_AVScreenCapture_Create()
            if (capture != null) OH_AVScreenCapture_Release(capture)
            logLine("OH_AVScreenCapture_Create/Release ok")
        }
    }

    // avplayer_base.h（本头文件仅 extern 常量）
    @Test
    fun testAvplayer_base_h() {
        assertNotNull(OH_PLAYER_STATE)
        logLine("OH_PLAYER_STATE=$OH_PLAYER_STATE")
    }

    // media_asset_base_capi.h（本头文件仅类型/枚举）
    @Test
    fun testMedia_asset_base_capi_h() {
        assertEquals<Int>(0, MEDIA_LIBRARY_OK.toInt())
        logLine("MEDIA_LIBRARY_OK=$MEDIA_LIBRARY_OK")
    }

    // media_access_helper_capi.h (API 12)
    @Test
    fun testMedia_access_helper_capi_h() {
        val rc = OH_MediaAccessHelper_ApplyChanges(null)
        logLine("OH_MediaAccessHelper_ApplyChanges(null) rc=$rc")
    }

    // moving_photo_capi.h (API 13)
    @Test
    fun testMoving_photo_capi_h() {
        OH_MovingPhoto_Release(null)
        logLine("OH_MovingPhoto_Release(null) ok")
    }

    // media_asset_manager_capi.h (API 12)
    @Test
    fun testMedia_asset_manager_capi_h() {
        memScoped {
            val mgr = OH_MediaAssetManager_Create()
            if (mgr != null) OH_MediaAssetManager_Release(mgr)
            logLine("OH_MediaAssetManager_Create/Release ok")
        }
    }

    // media_asset_change_request_capi.h (API 12)
    @Test
    fun testMedia_asset_change_request_capi_h() {
        OH_MediaAssetChangeRequest_Release(null)
        logLine("OH_MediaAssetChangeRequest_Release(null) ok")
    }

    // media_asset_capi.h (API 12)
    @Test
    fun testMedia_asset_capi_h() {
        OH_MediaAsset_Release(null)
        logLine("OH_MediaAsset_Release(null) ok")
    }
}
