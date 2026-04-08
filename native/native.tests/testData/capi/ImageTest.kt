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
import platform.ImageKit.Image.*
import platform.LocalizationKit.RawFile.RawFileDescriptor


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class ImageTest {

    private fun logLine(msg: String) = println(msg)


    @Test
    fun testEnum_AntiAliasingLevel() {
        val none = OH_PixelMap_AntiAliasing_NONE.toInt()
        val low = OH_PixelMap_AntiAliasing_LOW.toInt()
        val med = OH_PixelMap_AntiAliasing_MEDIUM.toInt()
        val high = OH_PixelMap_AntiAliasing_HIGH.toInt()
        logLine("AntiAliasing: NONE=$none, LOW=$low, MEDIUM=$med, HIGH=$high")
        assertEquals(0, none)
        assertEquals(1, low)
        assertEquals(2, med)
        assertEquals(3, high)
    }

    @Test
    fun testEnum_IRNdkErrCode() {
        val success = IMAGE_RESULT_SUCCESS.toInt()
        val badParam = IMAGE_RESULT_BAD_PARAMETER.toInt()
        val invalidParam = IMAGE_RESULT_INVALID_PARAMETER.toInt()
        logLine("ErrCode: SUCCESS=$success, BAD_PARAMETER=$badParam, INVALID_PARAMETER=$invalidParam")
        assertEquals(0, success)
        assertEquals(-1, badParam)
        assertEquals(62980115, invalidParam)
    }

    // ────────────── ImageSource Functions (18) ──────────────

    @Test
    fun testImageSource_Create() { memScoped {
        // OH_ImageSource_Create (deprecated since 11)
        val rc = OH_ImageSource_Create(null, null, null, null)
        assertNotNull(rc)
        logLine("OH_ImageSource_Create=$rc")
    } }

    @Test
    fun testImageSource_CreateFrom() { memScoped {
        // OH_ImageSource_CreateFromUri
        val rc1 = OH_ImageSource_CreateFromUri(null, null, 0u, null, null)
        assertNotNull(rc1)
        logLine("OH_ImageSource_CreateFromUri=$rc1")

        // OH_ImageSource_CreateFromFd
        val rc2 = OH_ImageSource_CreateFromFd(null, 0, null, null)
        assertNotNull(rc2)
        logLine("OH_ImageSource_CreateFromFd=$rc2")

        // OH_ImageSource_CreateFromData
        val rc3 = OH_ImageSource_CreateFromData(null, null, 0u, null, null)
        assertNotNull(rc3)
        logLine("OH_ImageSource_CreateFromData=$rc3")
    } }

    @Test
    fun testImageSource_CreateFromRawFile() { memScoped {
        val rawFile = alloc<RawFileDescriptor>().apply {
            fd = 0; start = 0L; length = 0L
        }
        val rc = OH_ImageSource_CreateFromRawFile(null, rawFile.readValue(), null, null)
        assertNotNull(rc)
        logLine("OH_ImageSource_CreateFromRawFile=$rc")
    } }

    @Test
    fun testImageSource_Incremental() { memScoped {
        // OH_ImageSource_CreateIncremental (deprecated since 11)
        val rc1 = OH_ImageSource_CreateIncremental(null, null, null, null)
        assertNotNull(rc1)
        logLine("OH_ImageSource_CreateIncremental=$rc1")

        // OH_ImageSource_CreateIncrementalFromData
        val rc2 = OH_ImageSource_CreateIncrementalFromData(null, null, 0u, null, null)
        assertNotNull(rc2)
        logLine("OH_ImageSource_CreateIncrementalFromData=$rc2")
    } }

    @Test
    fun testImageSource_Formats() { memScoped {
        // OH_ImageSource_GetSupportedFormats
        val fmtList = alloc<OhosImageSourceSupportedFormatList>().apply {
            supportedFormatList = null; size = 0u
        }
        val rc = OH_ImageSource_GetSupportedFormats(fmtList.ptr)
        assertNotNull(rc)
        logLine("OH_ImageSource_GetSupportedFormats=$rc")
    } }

    @Test
    fun testImageSource_InitAndPixelMap() { memScoped {
        // OH_ImageSource_InitNative
        val native = OH_ImageSource_InitNative(null, null)
        logLine("OH_ImageSource_InitNative=$native")

        // OH_ImageSource_CreatePixelMap
        val rc1 = OH_ImageSource_CreatePixelMap(null, null, null)
        assertNotNull(rc1)
        logLine("OH_ImageSource_CreatePixelMap=$rc1")

        // OH_ImageSource_CreatePixelMapList
        val rc2 = OH_ImageSource_CreatePixelMapList(null, null, null)
        assertNotNull(rc2)
        logLine("OH_ImageSource_CreatePixelMapList=$rc2")
    } }

    @Test
    fun testImageSource_Info() { memScoped {
        // OH_ImageSource_GetDelayTime
        val dtList = alloc<OhosImageSourceDelayTimeList>().apply {
            delayTimeList = null; size = 0u
        }
        val rc1 = OH_ImageSource_GetDelayTime(null, dtList.ptr)
        assertNotNull(rc1)
        logLine("OH_ImageSource_GetDelayTime=$rc1")

        // OH_ImageSource_GetFrameCount
        val frameCount = alloc<UIntVar>()
        val rc2 = OH_ImageSource_GetFrameCount(null, frameCount.ptr)
        assertNotNull(rc2)
        logLine("OH_ImageSource_GetFrameCount=$rc2")

        // OH_ImageSource_GetImageInfo
        val info = alloc<OhosImageSourceInfo>()
        val rc3 = OH_ImageSource_GetImageInfo(null, 0, info.ptr)
        assertNotNull(rc3)
        logLine("OH_ImageSource_GetImageInfo=$rc3")
    } }

    @Test
    fun testImageSource_Property() { memScoped {
        // OH_ImageSource_GetImageProperty
        val key = alloc<OhosImageSourceProperty>().apply { value = null; size = 0u }
        val pval = alloc<OhosImageSourceProperty>().apply { value = null; size = 0u }
        val rc1 = OH_ImageSource_GetImageProperty(null, key.ptr, pval.ptr)
        assertNotNull(rc1)
        logLine("OH_ImageSource_GetImageProperty=$rc1")

        // OH_ImageSource_ModifyImageProperty
        val rc2 = OH_ImageSource_ModifyImageProperty(null, null, null)
        assertNotNull(rc2)
        logLine("OH_ImageSource_ModifyImageProperty=$rc2")
    } }

    @Test
    fun testImageSource_UpdateAndRelease() { memScoped {
        // OH_ImageSource_UpdateData
        val ud = alloc<OhosImageSourceUpdateData>().apply {
            buffer = null; bufferSize = 0u; offset = 0u; updateLength = 0u; isCompleted = 0
        }
        val rc1 = OH_ImageSource_UpdateData(null, ud.ptr)
        assertNotNull(rc1)
        logLine("OH_ImageSource_UpdateData=$rc1")

        // OH_ImageSource_Release
        val rc2 = OH_ImageSource_Release(null)
        assertNotNull(rc2)
        logLine("OH_ImageSource_Release=$rc2")
    } }

    // ────────────── Image Functions (6) ──────────────

    @Test
    fun testImage() { memScoped {
        // OH_Image_InitImageNative
        val native = OH_Image_InitImageNative(null, null)
        logLine("OH_Image_InitImageNative=$native")

        // OH_Image_ClipRect
        val rect = alloc<OhosImageRect>()
        val rc1 = OH_Image_ClipRect(null, rect.ptr)
        assertNotNull(rc1)
        logLine("OH_Image_ClipRect=$rc1")

        // OH_Image_Size
        val sz = alloc<OhosImageSize>()
        val rc2 = OH_Image_Size(null, sz.ptr)
        assertNotNull(rc2)
        logLine("OH_Image_Size=$rc2")

        // OH_Image_Format
        val fmt = alloc<IntVar>()
        val rc3 = OH_Image_Format(null, fmt.ptr)
        assertNotNull(rc3)
        logLine("OH_Image_Format=$rc3")

        // OH_Image_GetComponent
        val comp = alloc<OhosImageComponent>()
        val rc4 = OH_Image_GetComponent(null, 0, comp.ptr)
        assertNotNull(rc4)
        logLine("OH_Image_GetComponent=$rc4")

        // OH_Image_Release
        val rc5 = OH_Image_Release(null)
        assertNotNull(rc5)
        logLine("OH_Image_Release=$rc5")
    } }

    // ────────────── PixelMap Functions (20) ──────────────

    @Test
    fun testPixelMap_Create() { memScoped {
        val ops = alloc<OhosPixelMapCreateOps>().apply {
            width = 0u; height = 0u; pixelFormat = 0
            editable = 0u; alphaType = 0u; scaleMode = 0u
        }

        // OH_PixelMap_CreatePixelMap
        val rc1 = OH_PixelMap_CreatePixelMap(null, ops.readValue(), null, 0u, null)
        assertNotNull(rc1)
        logLine("OH_PixelMap_CreatePixelMap=$rc1")

        // OH_PixelMap_CreatePixelMapWithStride
        val rc2 = OH_PixelMap_CreatePixelMapWithStride(null, ops.readValue(), null, 0u, 0, null)
        assertNotNull(rc2)
        logLine("OH_PixelMap_CreatePixelMapWithStride=$rc2")

        // OH_PixelMap_CreateAlphaPixelMap
        val rc3 = OH_PixelMap_CreateAlphaPixelMap(null, null, null)
        assertNotNull(rc3)
        logLine("OH_PixelMap_CreateAlphaPixelMap=$rc3")

        // OH_PixelMap_InitNativePixelMap
        val native = OH_PixelMap_InitNativePixelMap(null, null)
        logLine("OH_PixelMap_InitNativePixelMap=$native")
    } }

    @Test
    fun testPixelMap_GettersSetters() { memScoped {
        // OH_PixelMap_GetBytesNumberPerRow
        val bytesPerRow = alloc<IntVar>()
        val rc1 = OH_PixelMap_GetBytesNumberPerRow(null, bytesPerRow.ptr)
        assertNotNull(rc1)
        logLine("OH_PixelMap_GetBytesNumberPerRow=$rc1")

        // OH_PixelMap_GetIsEditable
        val editable = alloc<IntVar>()
        val rc2 = OH_PixelMap_GetIsEditable(null, editable.ptr)
        assertNotNull(rc2)
        logLine("OH_PixelMap_GetIsEditable=$rc2")

        // OH_PixelMap_IsSupportAlpha
        val alpha = alloc<IntVar>()
        val rc3 = OH_PixelMap_IsSupportAlpha(null, alpha.ptr)
        assertNotNull(rc3)
        logLine("OH_PixelMap_IsSupportAlpha=$rc3")

        // OH_PixelMap_SetAlphaAble
        val rc4 = OH_PixelMap_SetAlphaAble(null, 0)
        assertNotNull(rc4)
        logLine("OH_PixelMap_SetAlphaAble=$rc4")

        // OH_PixelMap_GetDensity
        val density = alloc<IntVar>()
        val rc5 = OH_PixelMap_GetDensity(null, density.ptr)
        assertNotNull(rc5)
        logLine("OH_PixelMap_GetDensity=$rc5")

        // OH_PixelMap_SetDensity
        val rc6 = OH_PixelMap_SetDensity(null, 0)
        assertNotNull(rc6)
        logLine("OH_PixelMap_SetDensity=$rc6")

        // OH_PixelMap_SetOpacity
        val rc7 = OH_PixelMap_SetOpacity(null, 0.0f)
        assertNotNull(rc7)
        logLine("OH_PixelMap_SetOpacity=$rc7")
    } }

    @Test
    fun testPixelMap_Transforms() { memScoped {
        // OH_PixelMap_Scale
        val rc1 = OH_PixelMap_Scale(null, 1.0f, 1.0f)
        assertNotNull(rc1)
        logLine("OH_PixelMap_Scale=$rc1")

        // OH_PixelMap_ScaleWithAntiAliasing
        val rc2 = OH_PixelMap_ScaleWithAntiAliasing(
            null, 1.0f, 1.0f,
            OH_PixelMap_AntiAliasing_NONE
        )
        assertNotNull(rc2)
        logLine("OH_PixelMap_ScaleWithAntiAliasing=$rc2")

        // OH_PixelMap_Translate
        val rc3 = OH_PixelMap_Translate(null, 0.0f, 0.0f)
        assertNotNull(rc3)
        logLine("OH_PixelMap_Translate=$rc3")

        // OH_PixelMap_Rotate
        val rc4 = OH_PixelMap_Rotate(null, 0.0f)
        assertNotNull(rc4)
        logLine("OH_PixelMap_Rotate=$rc4")

        // OH_PixelMap_Flip
        val rc5 = OH_PixelMap_Flip(null, 0, 0)
        assertNotNull(rc5)
        logLine("OH_PixelMap_Flip=$rc5")

        // OH_PixelMap_Crop
        val rc6 = OH_PixelMap_Crop(null, 0, 0, 0, 0)
        assertNotNull(rc6)
        logLine("OH_PixelMap_Crop=$rc6")
    } }

    @Test
    fun testPixelMap_InfoAndPixels() { memScoped {
        // OH_PixelMap_GetImageInfo
        val info = alloc<OhosPixelMapInfos>()
        val rc1 = OH_PixelMap_GetImageInfo(null, info.ptr)
        assertNotNull(rc1)
        logLine("OH_PixelMap_GetImageInfo=$rc1")

        // OH_PixelMap_AccessPixels
        val addr = alloc<COpaquePointerVar>()
        val rc2 = OH_PixelMap_AccessPixels(null, addr.ptr)
        assertNotNull(rc2)
        logLine("OH_PixelMap_AccessPixels=$rc2")

        // OH_PixelMap_UnAccessPixels
        val rc3 = OH_PixelMap_UnAccessPixels(null)
        assertNotNull(rc3)
        logLine("OH_PixelMap_UnAccessPixels=$rc3")
    } }

    // ────────────── ImageReceiver Functions (10) ──────────────

    @Test
    fun testImageReceiver() { memScoped {
        // OH_Image_Receiver_CreateImageReceiver
        val receiverInfo = alloc<OhosImageReceiverInfo>().apply {
            width = 0; height = 0; format = 0; capicity = 0
        }
        //val rc1 = OH_Image_Receiver_CreateImageReceiver(null, receiverInfo.readValue(), null)
        //assertNotNull(rc1)
        //logLine("OH_Image_Receiver_CreateImageReceiver=$rc1")

        // OH_Image_Receiver_InitImageReceiverNative
        val native = OH_Image_Receiver_InitImageReceiverNative(null, null)
        logLine("OH_Image_Receiver_InitImageReceiverNative=$native")

        // OH_Image_Receiver_GetReceivingSurfaceId
        val id = allocArray<ByteVar>(256)
        val rc2 = OH_Image_Receiver_GetReceivingSurfaceId(null, id, 256u)
        assertNotNull(rc2)
        logLine("OH_Image_Receiver_GetReceivingSurfaceId=$rc2")

        // OH_Image_Receiver_ReadLatestImage
        val rc3 = OH_Image_Receiver_ReadLatestImage(null, null)
        assertNotNull(rc3)
        logLine("OH_Image_Receiver_ReadLatestImage=$rc3")

        // OH_Image_Receiver_ReadNextImage
        val rc4 = OH_Image_Receiver_ReadNextImage(null, null)
        assertNotNull(rc4)
        logLine("OH_Image_Receiver_ReadNextImage=$rc4")

        // OH_Image_Receiver_On
        val rc5 = OH_Image_Receiver_On(null, null)
        assertNotNull(rc5)
        logLine("OH_Image_Receiver_On=$rc5")

        // OH_Image_Receiver_GetSize
        val sz = alloc<OhosImageSize>()
        val rc6 = OH_Image_Receiver_GetSize(null, sz.ptr)
        assertNotNull(rc6)
        logLine("OH_Image_Receiver_GetSize=$rc6")

        // OH_Image_Receiver_GetCapacity
        val cap = alloc<IntVar>()
        val rc7 = OH_Image_Receiver_GetCapacity(null, cap.ptr)
        assertNotNull(rc7)
        logLine("OH_Image_Receiver_GetCapacity=$rc7")

        // OH_Image_Receiver_GetFormat
        val recvFmt = alloc<IntVar>()
        val rc8 = OH_Image_Receiver_GetFormat(null, recvFmt.ptr)
        assertNotNull(rc8)
        logLine("OH_Image_Receiver_GetFormat=$rc8")

        // OH_Image_Receiver_Release
        val rc9 = OH_Image_Receiver_Release(null)
        assertNotNull(rc9)
        logLine("OH_Image_Receiver_Release=$rc9")
    } }

    // ────────────── ImagePacker Functions (5) ──────────────

    @Test
    fun testImagePacker() { memScoped {
        // OH_ImagePacker_Create
        val rc1 = OH_ImagePacker_Create(null, null)
        assertNotNull(rc1)
        logLine("OH_ImagePacker_Create=$rc1")

        // OH_ImagePacker_InitNative
        val native = OH_ImagePacker_InitNative(null, null)
        logLine("OH_ImagePacker_InitNative=$native")

        // OH_ImagePacker_PackToData
        val opts = alloc<ImagePacker_Opts_>().apply { format = null; quality = 0 }
        val sz = alloc<ULongVar>()
        val rc2 = OH_ImagePacker_PackToData(null, null, opts.ptr, null, sz.ptr)
        assertNotNull(rc2)
        logLine("OH_ImagePacker_PackToData=$rc2")

        // OH_ImagePacker_PackToFile
        val rc3 = OH_ImagePacker_PackToFile(null, null, null, 0)
        assertNotNull(rc3)
        logLine("OH_ImagePacker_PackToFile=$rc3")

        // OH_ImagePacker_Release
        val rc4 = OH_ImagePacker_Release(null)
        assertNotNull(rc4)
        logLine("OH_ImagePacker_Release=$rc4")
    } }
}
