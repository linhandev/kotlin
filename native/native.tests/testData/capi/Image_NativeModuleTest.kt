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
import platform.ImageKit.Image_NativeModule.*
import cnames.structs.*

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class Image_NativeModuleTest {

    private fun logLine(msg: String) = println(msg)

    // ==================== image_common.h ====================

    @Test
    fun testEnum_Image_ErrorCode() {
        assertEquals(IMAGE_SUCCESS.toInt(), 0)
        assertEquals(IMAGE_BAD_PARAMETER.toInt(), 401)
        assertEquals(IMAGE_UNSUPPORTED_MIME_TYPE.toInt(), 7600101)
        assertEquals(IMAGE_UNKNOWN_MIME_TYPE.toInt(), 7600102)
        assertEquals(IMAGE_TOO_LARGE.toInt(), 7600103)
        assertEquals(IMAGE_DMA_NOT_EXIST.toInt(), 7600173)
        assertEquals(IMAGE_DMA_OPERATION_FAILED.toInt(), 7600174)
        assertEquals(IMAGE_UNSUPPORTED_OPERATION.toInt(), 7600201)
        assertEquals(IMAGE_UNSUPPORTED_METADATA.toInt(), 7600202)
        assertEquals(IMAGE_UNSUPPORTED_CONVERSION.toInt(), 7600203)
        assertEquals(IMAGE_INVALID_REGION.toInt(), 7600204)
        assertEquals(IMAGE_UNSUPPORTED_MEMORY_FORMAT.toInt(), 7600205)
        assertEquals(IMAGE_INVALID_PARAMETER.toInt(), 7600206)
        assertEquals(IMAGE_UNSUPPORTED_DATA_FORMAT.toInt(), 7600207)
        assertEquals(IMAGE_ALLOC_FAILED.toInt(), 7600301)
        assertEquals(IMAGE_COPY_FAILED.toInt(), 7600302)
        assertEquals(IMAGE_LOCK_UNLOCK_FAILED.toInt(), 7600303)
        assertEquals(IMAGE_INIT_FAILED.toInt(), 7600304)
        assertEquals(IMAGE_CREATE_PIXELMAP_FAILED.toInt(), 7600305)
        assertEquals(IMAGE_ALLOCATOR_MODE_UNSUPPORTED.toInt(), 7600501)
        assertEquals(IMAGE_UNKNOWN_ERROR.toInt(), 7600901)
        assertEquals(IMAGE_BAD_SOURCE.toInt(), 7700101)
        assertEquals(IMAGE_SOURCE_UNSUPPORTED_MIME_TYPE.toInt(), 7700102)
        assertEquals(IMAGE_SOURCE_TOO_LARGE.toInt(), 7700103)
        assertEquals(IMAGE_SOURCE_UNSUPPORTED_ALLOCATOR_TYPE.toInt(), 7700201)
        assertEquals(IMAGE_SOURCE_UNSUPPORTED_OPTIONS.toInt(), 7700203)
        assertEquals(IMAGE_SOURCE_INVALID_PARAMETER.toInt(), 7700204)
        assertEquals(IMAGE_DECODE_FAILED.toInt(), 7700301)
        assertEquals(IMAGE_SOURCE_ALLOC_FAILED.toInt(), 7700302)
        assertEquals(IMAGE_PACKER_INVALID_PARAMETER.toInt(), 7800202)
        assertEquals(IMAGE_ENCODE_FAILED.toInt(), 7800301)
        assertEquals(IMAGE_RECEIVER_INVALID_PARAMETER.toInt(), 7900201)
        logLine("Image_ErrorCode all passed")
    }

    @Test
    fun testEnum_Image_MetadataType() {
        assertEquals(EXIF_METADATA.toInt(), 1)
        assertEquals(FRAGMENT_METADATA.toInt(), 2)
        assertEquals(GIF_METADATA.toInt(), 5)
        logLine("Image_MetadataType passed")
    }

    @Test
    fun testEnum_IMAGE_ALLOCATOR_MODE() {
        assertEquals(IMAGE_ALLOCATOR_MODE_AUTO.toInt(), 0)
        assertEquals(IMAGE_ALLOCATOR_MODE_DMA.toInt(), 1)
        assertEquals(IMAGE_ALLOCATOR_MODE_SHARED_MEMORY.toInt(), 2)
        logLine("IMAGE_ALLOCATOR_MODE passed")
    }

    @Test
    fun testOH_PictureMetadata_Create() {
        memScoped {
            val metadata = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureMetadata_Create(EXIF_METADATA, metadata.ptr.reinterpret())
            logLine("OH_PictureMetadata_Create(EXIF_METADATA,&metadata)=$ret")
        }
    }

    @Test
    fun testOH_PictureMetadata_GetProperty() {
        memScoped {
            val key = alloc<Image_String>()
            val value = alloc<Image_String>()
            val ret = OH_PictureMetadata_GetProperty(null, key.ptr, value.ptr)
            logLine("OH_PictureMetadata_GetProperty(null,&key,&value)=$ret")
        }
    }

    @Test
    fun testOH_PictureMetadata_SetProperty() {
        memScoped {
            val key = alloc<Image_String>()
            val value = alloc<Image_String>()
            val ret = OH_PictureMetadata_SetProperty(null, key.ptr, value.ptr)
            logLine("OH_PictureMetadata_SetProperty(null,&key,&value)=$ret")
        }
    }

    @Test
    fun testOH_PictureMetadata_GetPropertyWithNull() {
        memScoped {
            val key = alloc<Image_String>()
            val value = alloc<Image_String>()
            val ret = try { OH_PictureMetadata_GetPropertyWithNull(null, key.ptr, value.ptr) } catch (e: Throwable) { logLine("OH_PictureMetadata_GetPropertyWithNull (API 19) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PictureMetadata_GetPropertyWithNull(null,&key,&value)=$ret")
        }
    }

    @Test
    fun testOH_PictureMetadata_Release() {
        val ret = OH_PictureMetadata_Release(null)
        logLine("OH_PictureMetadata_Release(null)=$ret")
    }

    @Test
    fun testOH_PictureMetadata_Clone() {
        memScoped {
            val newMetadata = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureMetadata_Clone(null, newMetadata.ptr.reinterpret())
            logLine("OH_PictureMetadata_Clone(null,&newMetadata)=$ret")
        }
    }

    // ==================== image_receiver_native.h ====================

    @Test
    fun testOH_ImageReceiverOptions_Create() {
        memScoped {
            val options = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageReceiverOptions_Create(options.ptr.reinterpret())
            logLine("OH_ImageReceiverOptions_Create(&options)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverOptions_GetSize() {
        memScoped {
            val size = alloc<Image_Size>()
            val ret = OH_ImageReceiverOptions_GetSize(null, size.ptr)
            logLine("OH_ImageReceiverOptions_GetSize(null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverOptions_SetSize() {
        memScoped {
            val ret = OH_ImageReceiverOptions_SetSize(null, cValue<Image_Size> { })
            logLine("OH_ImageReceiverOptions_SetSize(null,size)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverOptions_GetCapacity() {
        memScoped {
            val capacity = alloc<IntVar>()
            val ret = OH_ImageReceiverOptions_GetCapacity(null, capacity.ptr)
            logLine("OH_ImageReceiverOptions_GetCapacity(null,&capacity)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverOptions_SetCapacity() {
        val ret = OH_ImageReceiverOptions_SetCapacity(null, 0)
        logLine("OH_ImageReceiverOptions_SetCapacity(null,0)=$ret")
    }

    @Test
    fun testOH_ImageReceiverOptions_Release() {
        val ret = OH_ImageReceiverOptions_Release(null)
        logLine("OH_ImageReceiverOptions_Release(null)=$ret")
    }

    @Test
    fun testOH_ImageReceiverNative_Create() {
        memScoped {
            val receiver = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageReceiverNative_Create(null, receiver.ptr.reinterpret())
            logLine("OH_ImageReceiverNative_Create(null,&receiver)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverNative_GetReceivingSurfaceId() {
        memScoped {
            val surfaceId = alloc<ULongVar>()
            val ret = OH_ImageReceiverNative_GetReceivingSurfaceId(null, surfaceId.ptr)
            logLine("OH_ImageReceiverNative_GetReceivingSurfaceId(null,&surfaceId)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverNative_ReadLatestImage() {
        memScoped {
            val image = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageReceiverNative_ReadLatestImage(null, image.ptr.reinterpret())
            logLine("OH_ImageReceiverNative_ReadLatestImage(null,&image)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverNative_ReadNextImage() {
        memScoped {
            val image = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageReceiverNative_ReadNextImage(null, image.ptr.reinterpret())
            logLine("OH_ImageReceiverNative_ReadNextImage(null,&image)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverNative_On() {
        val callback = staticCFunction { _: CPointer<OH_ImageReceiverNative>? -> }
        val ret = OH_ImageReceiverNative_On(null, callback)
        logLine("OH_ImageReceiverNative_On(null,callback)=$ret")
    }

    @Test
    fun testOH_ImageReceiverNative_Off() {
        val ret = OH_ImageReceiverNative_Off(null)
        logLine("OH_ImageReceiverNative_Off(null)=$ret")
    }

    @Test
    fun testOH_ImageReceiverNative_OnImageArrive() {
        val callback = staticCFunction { _: CPointer<OH_ImageReceiverNative>?, _: CPointer<out CPointed>? -> }
        val ret = try { OH_ImageReceiverNative_OnImageArrive(null, callback, null) } catch (e: Throwable) { logLine("OH_ImageReceiverNative_OnImageArrive (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_ImageReceiverNative_OnImageArrive(null,callback,null)=$ret")
    }

    @Test
    fun testOH_ImageReceiverNative_OffImageArrive() {
        val callback = staticCFunction { _: CPointer<OH_ImageReceiverNative>?, _: CPointer<out CPointed>? -> }
        val ret = try { OH_ImageReceiverNative_OffImageArrive(null, callback) } catch (e: Throwable) { logLine("OH_ImageReceiverNative_OffImageArrive (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_ImageReceiverNative_OffImageArrive(null,callback)=$ret")
    }

    @Test
    fun testOH_ImageReceiverNative_GetSize() {
        memScoped {
            val size = alloc<Image_Size>()
            val ret = OH_ImageReceiverNative_GetSize(null, size.ptr)
            logLine("OH_ImageReceiverNative_GetSize(null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverNative_GetCapacity() {
        memScoped {
            val capacity = alloc<IntVar>()
            val ret = OH_ImageReceiverNative_GetCapacity(null, capacity.ptr)
            logLine("OH_ImageReceiverNative_GetCapacity(null,&capacity)=$ret")
        }
    }

    @Test
    fun testOH_ImageReceiverNative_Release() {
        val ret = OH_ImageReceiverNative_Release(null)
        logLine("OH_ImageReceiverNative_Release(null)=$ret")
    }

    // ==================== image_source_native.h ====================

    @Test
    fun testEnum_IMAGE_DYNAMIC_RANGE() {
        assertEquals(IMAGE_DYNAMIC_RANGE_AUTO.toInt(), 0)
        assertEquals(IMAGE_DYNAMIC_RANGE_SDR.toInt(), 1)
        assertEquals(IMAGE_DYNAMIC_RANGE_HDR.toInt(), 2)
        logLine("IMAGE_DYNAMIC_RANGE passed")
    }

    @Test
    fun testEnum_IMAGE_ALLOCATOR_TYPE() {
        assertEquals(IMAGE_ALLOCATOR_TYPE_AUTO.toInt(), 0)
        assertEquals(IMAGE_ALLOCATOR_TYPE_DMA.toInt(), 1)
        assertEquals(IMAGE_ALLOCATOR_TYPE_SHARE_MEMORY.toInt(), 2)
        logLine("IMAGE_ALLOCATOR_TYPE passed")
    }

    @Test
    fun testEnum_Image_CropAndScaleStrategy() {
        assertEquals(IMAGE_CROP_AND_SCALE_STRATEGY_SCALE_FIRST.toInt(), 1)
        assertEquals(IMAGE_CROP_AND_SCALE_STRATEGY_CROP_FIRST.toInt(), 2)
        logLine("Image_CropAndScaleStrategy passed")
    }

    // OH_ImageSource_Info
    @Test
    fun testOH_ImageSourceInfo_Create() {
        memScoped {
            val info = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceInfo_Create(info.ptr.reinterpret())
            logLine("OH_ImageSourceInfo_Create(&info)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceInfo_GetWidth() {
        memScoped {
                val width = alloc<UIntVar>()
            val ret = OH_ImageSourceInfo_GetWidth(null, width.ptr)
            logLine("OH_ImageSourceInfo_GetWidth(null,&width)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceInfo_GetHeight() {
        memScoped {
                val height = alloc<UIntVar>()
            val ret = OH_ImageSourceInfo_GetHeight(null, height.ptr)
            logLine("OH_ImageSourceInfo_GetHeight(null,&height)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceInfo_GetDynamicRange() {
        memScoped {
            val isHdr = alloc<BooleanVar>()
            val ret = OH_ImageSourceInfo_GetDynamicRange(null, isHdr.ptr)
            logLine("OH_ImageSourceInfo_GetDynamicRange(null,&isHdr)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceInfo_GetMimeType() {
        val ret = try { OH_ImageSourceInfo_GetMimeType(null, null) } catch (e: Throwable) { logLine("OH_ImageSourceInfo_GetMimeType (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_ImageSourceInfo_GetMimeType(null,null)=$ret")
    }

    @Test
    fun testOH_ImageSourceInfo_Release() {
        val ret = OH_ImageSourceInfo_Release(null)
        logLine("OH_ImageSourceInfo_Release(null)=$ret")
    }

    // OH_DecodingOptions
    @Test
    fun testOH_DecodingOptions_Create() {
        memScoped {
            val options = alloc<CPointerVar<ByteVar>>()
            val ret = OH_DecodingOptions_Create(options.ptr.reinterpret())
            logLine("OH_DecodingOptions_Create(&options)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_GetPixelFormat() {
        memScoped {
                val pixelFormat = alloc<IntVar>()
            val ret = OH_DecodingOptions_GetPixelFormat(null, pixelFormat.ptr)
            logLine("OH_DecodingOptions_GetPixelFormat(null,&pixelFormat)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetPixelFormat() {
        val ret = OH_DecodingOptions_SetPixelFormat(null, 0)
        logLine("OH_DecodingOptions_SetPixelFormat(null,0)=$ret")
    }

    @Test
    fun testOH_DecodingOptions_GetIndex() {
        memScoped {
            val index = alloc<UIntVar>()
            val ret = OH_DecodingOptions_GetIndex(null, index.ptr)
            logLine("OH_DecodingOptions_GetIndex(null,&index)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetIndex() {
        val ret = OH_DecodingOptions_SetIndex(null, 0u)
        logLine("OH_DecodingOptions_SetIndex(null,0)=$ret")
    }

    @Test
    fun testOH_DecodingOptions_GetRotate() {
        memScoped {
            val rotate = alloc<FloatVar>()
            val ret = OH_DecodingOptions_GetRotate(null, rotate.ptr)
            logLine("OH_DecodingOptions_GetRotate(null,&rotate)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetRotate() {
        val ret = OH_DecodingOptions_SetRotate(null, 0f)
        logLine("OH_DecodingOptions_SetRotate(null,0)=$ret")
    }

    @Test
    fun testOH_DecodingOptions_GetDesiredSize() {
        memScoped {
            val desiredSize = alloc<Image_Size>()
            val ret = OH_DecodingOptions_GetDesiredSize(null, desiredSize.ptr)
            logLine("OH_DecodingOptions_GetDesiredSize(null,&desiredSize)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetDesiredSize() {
        memScoped {
            val desiredSize = alloc<Image_Size>()
            val ret = OH_DecodingOptions_SetDesiredSize(null, desiredSize.ptr)
            logLine("OH_DecodingOptions_SetDesiredSize(null,&desiredSize)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_GetDesiredRegion() {
        memScoped {
            val desiredRegion = alloc<Image_Region>()
            val ret = OH_DecodingOptions_GetDesiredRegion(null, desiredRegion.ptr)
            logLine("OH_DecodingOptions_GetDesiredRegion(null,&desiredRegion)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetDesiredRegion() {
        memScoped {
            val desiredRegion = alloc<Image_Region>()
            val ret = OH_DecodingOptions_SetDesiredRegion(null, desiredRegion.ptr)
            logLine("OH_DecodingOptions_SetDesiredRegion(null,&desiredRegion)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_GetDesiredDynamicRange() {
        memScoped {
            val desiredDynamicRange = alloc<IntVar>()
            val ret = OH_DecodingOptions_GetDesiredDynamicRange(null, desiredDynamicRange.ptr)
            logLine("OH_DecodingOptions_GetDesiredDynamicRange(null,&desiredDynamicRange)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetCropAndScaleStrategy() {
        val ret = try { OH_DecodingOptions_SetCropAndScaleStrategy(null, IMAGE_CROP_AND_SCALE_STRATEGY_SCALE_FIRST.toInt()) } catch (e: Throwable) { logLine("OH_DecodingOptions_SetCropAndScaleStrategy (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_DecodingOptions_SetCropAndScaleStrategy(null,...)=$ret")
    }

    @Test
    fun testOH_DecodingOptions_GetCropAndScaleStrategy() {
        memScoped {
            val cropAndScaleStrategy = alloc<IntVar>()
            val ret = try { OH_DecodingOptions_GetCropAndScaleStrategy(null, cropAndScaleStrategy.ptr) } catch (e: Throwable) { logLine("OH_DecodingOptions_GetCropAndScaleStrategy (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_DecodingOptions_GetCropAndScaleStrategy(null,&cropAndScaleStrategy)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetDesiredDynamicRange() {
        val ret = OH_DecodingOptions_SetDesiredDynamicRange(null, IMAGE_DYNAMIC_RANGE_AUTO.toInt())
        logLine("OH_DecodingOptions_SetDesiredDynamicRange(null,...)=$ret")
    }

    @Test
    fun testOH_DecodingOptions_GetDesiredColorSpace() {
        memScoped {
            val colorSpace = alloc<IntVar>()
            val ret = try { OH_DecodingOptions_GetDesiredColorSpace(null, colorSpace.ptr) } catch (e: Throwable) { logLine("OH_DecodingOptions_GetDesiredColorSpace (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_DecodingOptions_GetDesiredColorSpace(null,&colorSpace)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_SetDesiredColorSpace() {
        val ret = try { OH_DecodingOptions_SetDesiredColorSpace(null, 0) } catch (e: Throwable) { logLine("OH_DecodingOptions_SetDesiredColorSpace (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_DecodingOptions_SetDesiredColorSpace(null,0)=$ret")
    }

    @Test
    fun testOH_DecodingOptions_SetCropRegion() {
        memScoped {
            val cropRegion = alloc<Image_Region>()
            val ret = try { OH_DecodingOptions_SetCropRegion(null, cropRegion.ptr) } catch (e: Throwable) { logLine("OH_DecodingOptions_SetCropRegion (API 19) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_DecodingOptions_SetCropRegion(null,&cropRegion)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_GetCropRegion() {
        memScoped {
            val cropRegion = alloc<Image_Region>()
            val ret = try { OH_DecodingOptions_GetCropRegion(null, cropRegion.ptr) } catch (e: Throwable) { logLine("OH_DecodingOptions_GetCropRegion (API 19) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_DecodingOptions_GetCropRegion(null,&cropRegion)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptions_Release() {
        val ret = OH_DecodingOptions_Release(null)
        logLine("OH_DecodingOptions_Release(null)=$ret")
    }

    // OH_ImageSourceNative create
    @Test
    fun testOH_ImageSourceNative_CreateFromUri() {
        memScoped {
            val res = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreateFromUri(null, 0uL, res.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreateFromUri(null,0,&res)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreateFromFd() {
        memScoped {
            val res = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreateFromFd(-1, res.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreateFromFd(-1,&res)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreateFromData() {
        memScoped {
            val res = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreateFromData(null, 0uL, res.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreateFromData(null,0,&res)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreateFromDataWithUserBuffer() {
        memScoped {
            val imageSource = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ImageSourceNative_CreateFromDataWithUserBuffer(null, 0uL, imageSource.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ImageSourceNative_CreateFromDataWithUserBuffer (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImageSourceNative_CreateFromDataWithUserBuffer(null,0,&imageSource)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreateFromRawFile() {
        memScoped {
            val res = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreateFromRawFile(null, res.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreateFromRawFile(null,&res)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreatePixelmap() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreatePixelmap(null, null, pixelmap.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreatePixelmap(null,null,&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreatePixelmapUsingAllocator() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreatePixelmapUsingAllocator(null, null, IMAGE_ALLOCATOR_TYPE_AUTO, pixelmap.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreatePixelmapUsingAllocator(null,null,AUTO,&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreatePixelmapList() {
        memScoped {
            val slot = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreatePixelmapList(null, null, slot.ptr.reinterpret(), 1uL)
            logLine("OH_ImageSourceNative_CreatePixelmapList(null,null,arr,1)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreatePicture() {
        memScoped {
            val picture = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageSourceNative_CreatePicture(null, null, picture.ptr.reinterpret())
            logLine("OH_ImageSourceNative_CreatePicture(null,null,&picture)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_CreatePictureAtIndex() {
        memScoped {
            val picture = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ImageSourceNative_CreatePictureAtIndex(null, 0u, picture.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_ImageSourceNative_CreatePictureAtIndex (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImageSourceNative_CreatePictureAtIndex(null,0,&picture)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_GetDelayTimeList() {
        memScoped {
            val delayTimeList = allocArray<IntVar>(1)
            val ret = OH_ImageSourceNative_GetDelayTimeList(null, delayTimeList, 1uL)
            logLine("OH_ImageSourceNative_GetDelayTimeList(null,delayTimeList,1)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_GetImageInfo() {
        val ret = OH_ImageSourceNative_GetImageInfo(null, 0, null)
        logLine("OH_ImageSourceNative_GetImageInfo(null,0,null)=$ret")
    }

    @Test
    fun testOH_ImageSourceNative_GetImageProperty() {
        memScoped {
            val key = alloc<Image_String>()
            val value = alloc<Image_String>()
            val ret = OH_ImageSourceNative_GetImageProperty(null, key.ptr, value.ptr)
            logLine("OH_ImageSourceNative_GetImageProperty(null,&key,&value)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_GetImagePropertyWithNull() {
        memScoped {
            val key = alloc<Image_String>()
            val value = alloc<Image_String>()
            val ret = try { OH_ImageSourceNative_GetImagePropertyWithNull(null, key.ptr, value.ptr) } catch (e: Throwable) { logLine("OH_ImageSourceNative_GetImagePropertyWithNull (API 19) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImageSourceNative_GetImagePropertyWithNull(null,&key,&value)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_ModifyImageProperty() {
        memScoped {
            val key = alloc<Image_String>()
            val value = alloc<Image_String>()
            val ret = OH_ImageSourceNative_ModifyImageProperty(null, key.ptr, value.ptr)
            logLine("OH_ImageSourceNative_ModifyImageProperty(null,&key,&value)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_GetFrameCount() {
        memScoped {
            val frameCount = alloc<UIntVar>()
            val ret = OH_ImageSourceNative_GetFrameCount(null, frameCount.ptr)
            logLine("OH_ImageSourceNative_GetFrameCount(null,&frameCount)=$ret")
        }
    }

    @Test
    fun testOH_ImageSourceNative_Release() {
        val ret = OH_ImageSourceNative_Release(null)
        logLine("OH_ImageSourceNative_Release(null)=$ret")
    }

    // OH_DecodingOptionsForPicture
    @Test
    fun testOH_DecodingOptionsForPicture_Create() {
        memScoped {
            val options = alloc<CPointerVar<ByteVar>>()
            val ret = OH_DecodingOptionsForPicture_Create(options.ptr.reinterpret())
            logLine("OH_DecodingOptionsForPicture_Create(&options)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptionsForPicture_GetDesiredAuxiliaryPictures() {
        memScoped {
            val desiredAuxiliaryPictures = alloc<CPointerVar<ByteVar>>()
            val length = alloc<ULongVar>()
            val ret = OH_DecodingOptionsForPicture_GetDesiredAuxiliaryPictures(null, desiredAuxiliaryPictures.ptr.reinterpret(), length.ptr)
            logLine("OH_DecodingOptionsForPicture_GetDesiredAuxiliaryPictures(null,&desired,&length)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptionsForPicture_SetDesiredAuxiliaryPictures() {
        memScoped {
            val arr = allocArray<UIntVar>(1)
            val ret = OH_DecodingOptionsForPicture_SetDesiredAuxiliaryPictures(null, arr, 0uL)
            logLine("OH_DecodingOptionsForPicture_SetDesiredAuxiliaryPictures(null,arr,0)=$ret")
        }
    }

    @Test
    fun testOH_DecodingOptionsForPicture_Release() {
        val ret = OH_DecodingOptionsForPicture_Release(null)
        logLine("OH_DecodingOptionsForPicture_Release(null)=$ret")
    }

    @Test
    fun testOH_ImageSourceNative_GetSupportedFormats() {
        memScoped {
            val supportedFormats = alloc<CPointerVar<ByteVar>>()
            val length = alloc<ULongVar>()
            val ret = try { OH_ImageSourceNative_GetSupportedFormats(supportedFormats.ptr.reinterpret(), length.ptr) } catch (e: Throwable) { logLine("OH_ImageSourceNative_GetSupportedFormats (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImageSourceNative_GetSupportedFormats(&supportedFormats,&length)=$ret")
        }
    }

    // ==================== image_native.h ====================

    @Test
    fun testOH_ImageNative_GetImageSize() {
        memScoped {
            val size = alloc<Image_Size>()
            val ret = OH_ImageNative_GetImageSize(null, size.ptr)
            logLine("OH_ImageNative_GetImageSize(null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_GetComponentTypes() {
        memScoped {
            val types = alloc<CPointerVar<ByteVar>>()
            val typeSize = alloc<ULongVar>()
            val ret = OH_ImageNative_GetComponentTypes(null, types.ptr.reinterpret(), typeSize.ptr)
            logLine("OH_ImageNative_GetComponentTypes(null,&types,&typeSize)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_GetByteBuffer() {
        memScoped {
            val nativeBuffer = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImageNative_GetByteBuffer(null, 0u, nativeBuffer.ptr.reinterpret())
            logLine("OH_ImageNative_GetByteBuffer(null,0,&nativeBuffer)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_GetBufferSize() {
        memScoped {
            val size = alloc<ULongVar>()
            val ret = OH_ImageNative_GetBufferSize(null, 0u, size.ptr)
            logLine("OH_ImageNative_GetBufferSize(null,0,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_GetRowStride() {
        memScoped {
            val rowStride = alloc<IntVar>()
            val ret = OH_ImageNative_GetRowStride(null, 0u, rowStride.ptr)
            logLine("OH_ImageNative_GetRowStride(null,0,&rowStride)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_GetPixelStride() {
        memScoped {
            val pixelStride = alloc<IntVar>()
            val ret = OH_ImageNative_GetPixelStride(null, 0u, pixelStride.ptr)
            logLine("OH_ImageNative_GetPixelStride(null,0,&pixelStride)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_GetTimestamp() {
        memScoped {
            val timestamp = alloc<LongVar>()
            val ret = OH_ImageNative_GetTimestamp(null, timestamp.ptr)
            logLine("OH_ImageNative_GetTimestamp(null,&timestamp)=$ret")
        }
    }

    @Test
    fun testOH_ImageNative_Release() {
        val ret = OH_ImageNative_Release(null)
        logLine("OH_ImageNative_Release(null)=$ret")
    }

    // ==================== image_packer_native.h ====================

    @Test
    fun testEnum_IMAGE_PACKER_DYNAMIC_RANGE() {
        assertEquals(IMAGE_PACKER_DYNAMIC_RANGE_AUTO.toInt(), 0)
        assertEquals(IMAGE_PACKER_DYNAMIC_RANGE_SDR.toInt(), 1)
        logLine("IMAGE_PACKER_DYNAMIC_RANGE passed")
    }

    // OH_PackingOptions
    @Test
    fun testOH_PackingOptions_Create() {
        memScoped {
            val options = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PackingOptions_Create(options.ptr.reinterpret())
            logLine("OH_PackingOptions_Create(&options)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptions_GetMimeType() {
        val ret = OH_PackingOptions_GetMimeType(null, null)
        logLine("OH_PackingOptions_GetMimeType(null,null)=$ret")
    }

    @Test
    fun testOH_PackingOptions_GetMimeTypeWithNull() {
        val ret = try { OH_PackingOptions_GetMimeTypeWithNull(null, null) } catch (e: Throwable) { logLine("OH_PackingOptions_GetMimeTypeWithNull (API 19) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PackingOptions_GetMimeTypeWithNull(null,null)=$ret")
    }

    @Test
    fun testOH_PackingOptions_SetMimeType() {
        val ret = OH_PackingOptions_SetMimeType(null, null)
        logLine("OH_PackingOptions_SetMimeType(null,null)=$ret")
    }

    @Test
    fun testOH_PackingOptions_GetQuality() {
        memScoped {
            val quality = alloc<UIntVar>()
            val ret = OH_PackingOptions_GetQuality(null, quality.ptr)
            logLine("OH_PackingOptions_GetQuality(null,&quality)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptions_SetQuality() {
        val ret = OH_PackingOptions_SetQuality(null, 0u)
        logLine("OH_PackingOptions_SetQuality(null,0)=$ret")
    }

    @Test
    fun testOH_PackingOptions_GetNeedsPackProperties() {
        memScoped {
            val needsPackProperties = alloc<BooleanVar>()
            val ret = OH_PackingOptions_GetNeedsPackProperties(null, needsPackProperties.ptr)
            logLine("OH_PackingOptions_GetNeedsPackProperties(null,&needsPackProperties)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptions_SetNeedsPackProperties() {
        val ret = OH_PackingOptions_SetNeedsPackProperties(null, false)
        logLine("OH_PackingOptions_SetNeedsPackProperties(null,false)=$ret")
    }

    @Test
    fun testOH_PackingOptions_GetDesiredDynamicRange() {
        memScoped {
            val desiredDynamicRange = alloc<IntVar>()
            val ret = OH_PackingOptions_GetDesiredDynamicRange(null, desiredDynamicRange.ptr)
            logLine("OH_PackingOptions_GetDesiredDynamicRange(null,&desiredDynamicRange)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptions_SetDesiredDynamicRange() {
        val ret = OH_PackingOptions_SetDesiredDynamicRange(null, IMAGE_PACKER_DYNAMIC_RANGE_AUTO.toInt())
        logLine("OH_PackingOptions_SetDesiredDynamicRange(null,AUTO)=$ret")
    }

    @Test
    fun testOH_PackingOptions_Release() {
        val ret = OH_PackingOptions_Release(null)
        logLine("OH_PackingOptions_Release(null)=$ret")
    }

    // OH_PackingOptionsForSequence
    @Test
    fun testOH_PackingOptionsForSequence_Create() {
        memScoped {
            val options = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PackingOptionsForSequence_Create(options.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_Create (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_Create(&options)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_SetFrameCount() {
        val ret = try { OH_PackingOptionsForSequence_SetFrameCount(null, 0u) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_SetFrameCount (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PackingOptionsForSequence_SetFrameCount(null,0)=$ret")
    }

    @Test
    fun testOH_PackingOptionsForSequence_GetFrameCount() {
        memScoped {
            val frameCount = alloc<UIntVar>()
            val ret = try { OH_PackingOptionsForSequence_GetFrameCount(null, frameCount.ptr) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_GetFrameCount (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_GetFrameCount(null,&frameCount)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_SetDelayTimeList() {
        memScoped {
            val delayTimeList = allocArray<IntVar>(1)
            val ret = try { OH_PackingOptionsForSequence_SetDelayTimeList(null, delayTimeList, 1uL) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_SetDelayTimeList (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_SetDelayTimeList(null,delayTimeList,1)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_GetDelayTimeList() {
        memScoped {
            val delayTimeList = allocArray<IntVar>(1)
            val ret = try { OH_PackingOptionsForSequence_GetDelayTimeList(null, delayTimeList, 1uL) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_GetDelayTimeList (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_GetDelayTimeList(null,delayTimeList,1)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_SetDisposalTypes() {
        memScoped {
            val disposalTypes = allocArray<UIntVar>(1)
            val ret = try { OH_PackingOptionsForSequence_SetDisposalTypes(null, disposalTypes, 1uL) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_SetDisposalTypes (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_SetDisposalTypes(null,disposalTypes,1)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_GetDisposalTypes() {
        memScoped {
            val disposalTypes = allocArray<UIntVar>(1)
            val ret = try { OH_PackingOptionsForSequence_GetDisposalTypes(null, disposalTypes, 1uL) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_GetDisposalTypes (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_GetDisposalTypes(null,disposalTypes,1)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_SetLoopCount() {
        val ret = try { OH_PackingOptionsForSequence_SetLoopCount(null, 0u) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_SetLoopCount (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PackingOptionsForSequence_SetLoopCount(null,0)=$ret")
    }

    @Test
    fun testOH_PackingOptionsForSequence_GetLoopCount() {
        memScoped {
            val loopCount = alloc<UIntVar>()
            val ret = try { OH_PackingOptionsForSequence_GetLoopCount(null, loopCount.ptr) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_GetLoopCount (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PackingOptionsForSequence_GetLoopCount(null,&loopCount)=$ret")
        }
    }

    @Test
    fun testOH_PackingOptionsForSequence_Release() {
        val ret = try { OH_PackingOptionsForSequence_Release(null) } catch (e: Throwable) { logLine("OH_PackingOptionsForSequence_Release (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PackingOptionsForSequence_Release(null)=$ret")
    }

    // OH_ImagePackerNative
    @Test
    fun testOH_ImagePackerNative_Create() {
        memScoped {
            val imagePacker = alloc<CPointerVar<ByteVar>>()
            val ret = OH_ImagePackerNative_Create(imagePacker.ptr.reinterpret())
            logLine("OH_ImagePackerNative_Create(&imagePacker)=$ret")
        }
    }

    @Test
    fun testOH_ImagePackerNative_PackToDataFromImageSource() {
        memScoped {
            val size = alloc<ULongVar>()
            val ret = OH_ImagePackerNative_PackToDataFromImageSource(null, null, null, null, size.ptr)
            logLine("OH_ImagePackerNative_PackToDataFromImageSource(null,null,null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImagePackerNative_PackToDataFromPixelmap() {
        memScoped {
            val size = alloc<ULongVar>()
            val ret = OH_ImagePackerNative_PackToDataFromPixelmap(null, null, null, null, size.ptr)
            logLine("OH_ImagePackerNative_PackToDataFromPixelmap(null,null,null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImagePackerNative_PackToDataFromPicture() {
        memScoped {
            val size = alloc<ULongVar>()
            val ret = OH_ImagePackerNative_PackToDataFromPicture(null, null, null, null, size.ptr)
            logLine("OH_ImagePackerNative_PackToDataFromPicture(null,null,null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_ImagePackerNative_PackToDataFromPixelmapSequence() {
        memScoped {
            val slot = alloc<CPointerVar<ByteVar>>()
            val outDataSize = alloc<ULongVar>()
            val ret = try { OH_ImagePackerNative_PackToDataFromPixelmapSequence(null, null, slot.ptr.reinterpret(), 0uL, null, outDataSize.ptr) } catch (e: Throwable) { logLine("OH_ImagePackerNative_PackToDataFromPixelmapSequence (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImagePackerNative_PackToDataFromPixelmapSequence(...)=$ret")
        }
    }

    @Test
    fun testOH_ImagePackerNative_PackToFileFromImageSource() {
        val ret = OH_ImagePackerNative_PackToFileFromImageSource(null, null, null, -1)
        logLine("OH_ImagePackerNative_PackToFileFromImageSource(null,null,null,-1)=$ret")
    }

    @Test
    fun testOH_ImagePackerNative_PackToFileFromPixelmap() {
        val ret = OH_ImagePackerNative_PackToFileFromPixelmap(null, null, null, -1)
        logLine("OH_ImagePackerNative_PackToFileFromPixelmap(null,null,null,-1)=$ret")
    }

    @Test
    fun testOH_ImagePackerNative_PackToFileFromPicture() {
        val ret = OH_ImagePackerNative_PackToFileFromPicture(null, null, null, -1)
        logLine("OH_ImagePackerNative_PackToFileFromPicture(null,null,null,-1)=$ret")
    }

    @Test
    fun testOH_ImagePackerNative_PackToFileFromPixelmapSequence() {
        memScoped {
            val slot = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_ImagePackerNative_PackToFileFromPixelmapSequence(null, null, slot.ptr.reinterpret(), 0uL, -1) } catch (e: Throwable) { logLine("OH_ImagePackerNative_PackToFileFromPixelmapSequence (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImagePackerNative_PackToFileFromPixelmapSequence(...)=$ret")
        }
    }

    @Test
    fun testOH_ImagePackerNative_Release() {
        val ret = OH_ImagePackerNative_Release(null)
        logLine("OH_ImagePackerNative_Release(null)=$ret")
    }

    @Test
    fun testOH_ImagePackerNative_GetSupportedFormats() {
        memScoped {
            val supportedFormats = alloc<CPointerVar<ByteVar>>()
            val length = alloc<ULongVar>()
            val ret = try { OH_ImagePackerNative_GetSupportedFormats(supportedFormats.ptr.reinterpret(), length.ptr) } catch (e: Throwable) { logLine("OH_ImagePackerNative_GetSupportedFormats (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_ImagePackerNative_GetSupportedFormats(&supportedFormats,&length)=$ret")
        }
    }

    // ==================== pixelmap_native.h ====================

    @Test
    fun testEnum_PIXELMAP_ALPHA_TYPE() {
        assertEquals(PIXELMAP_ALPHA_TYPE_UNKNOWN.toInt(), 0)
        assertEquals(PIXELMAP_ALPHA_TYPE_OPAQUE.toInt(), 1)
        assertEquals(PIXELMAP_ALPHA_TYPE_PREMULTIPLIED.toInt(), 2)
        assertEquals(PIXELMAP_ALPHA_TYPE_UNPREMULTIPLIED.toInt(), 3)
        logLine("PIXELMAP_ALPHA_TYPE passed")
    }

    @Test
    fun testEnum_PIXEL_FORMAT() {
        assertEquals(PIXEL_FORMAT_UNKNOWN.toInt(), 0)
        assertEquals(PIXEL_FORMAT_RGB_565.toInt(), 2)
        assertEquals(PIXEL_FORMAT_RGBA_8888.toInt(), 3)
        assertEquals(PIXEL_FORMAT_BGRA_8888.toInt(), 4)
        assertEquals(PIXEL_FORMAT_RGB_888.toInt(), 5)
        assertEquals(PIXEL_FORMAT_ALPHA_8.toInt(), 6)
        assertEquals(PIXEL_FORMAT_RGBA_F16.toInt(), 7)
        assertEquals(PIXEL_FORMAT_NV21.toInt(), 8)
        assertEquals(PIXEL_FORMAT_NV12.toInt(), 9)
        assertEquals(PIXEL_FORMAT_RGBA_1010102.toInt(), 10)
        assertEquals(PIXEL_FORMAT_YCBCR_P010.toInt(), 11)
        assertEquals(PIXEL_FORMAT_YCRCB_P010.toInt(), 12)
        logLine("PIXEL_FORMAT passed")
    }

    @Test
    fun testEnum_OH_PixelmapNative_AntiAliasingLevel() {
        assertEquals(OH_PixelmapNative_AntiAliasing_NONE.toInt(), 0)
        assertEquals(OH_PixelmapNative_AntiAliasing_LOW.toInt(), 1)
        assertEquals(OH_PixelmapNative_AntiAliasing_MEDIUM.toInt(), 2)
        assertEquals(OH_PixelmapNative_AntiAliasing_HIGH.toInt(), 3)
        logLine("OH_PixelmapNative_AntiAliasingLevel passed")
    }

    @Test
    fun testEnum_OH_Pixelmap_HdrMetadataKey() {
        assertEquals(HDR_METADATA_TYPE.toInt(), 0)
        assertEquals(HDR_STATIC_METADATA.toInt(), 1)
        assertEquals(HDR_DYNAMIC_METADATA.toInt(), 2)
        assertEquals(HDR_GAINMAP_METADATA.toInt(), 3)
        logLine("OH_Pixelmap_HdrMetadataKey passed")
    }

    @Test
    fun testEnum_OH_Pixelmap_HdrMetadataType() {
        assertEquals(HDR_METADATA_TYPE_NONE.toInt(), 0)
        assertEquals(HDR_METADATA_TYPE_BASE.toInt(), 1)
        assertEquals(HDR_METADATA_TYPE_GAINMAP.toInt(), 2)
        assertEquals(HDR_METADATA_TYPE_ALTERNATE.toInt(), 3)
        logLine("OH_Pixelmap_HdrMetadataType passed")
    }

    @Test
    fun testEnum_Image_AuxiliaryPictureType() {
        assertEquals(AUXILIARY_PICTURE_TYPE_GAINMAP.toInt(), 1)
        assertEquals(AUXILIARY_PICTURE_TYPE_DEPTH_MAP.toInt(), 2)
        assertEquals(AUXILIARY_PICTURE_TYPE_UNREFOCUS_MAP.toInt(), 3)
        assertEquals(AUXILIARY_PICTURE_TYPE_LINEAR_MAP.toInt(), 4)
        assertEquals(AUXILIARY_PICTURE_TYPE_FRAGMENT_MAP.toInt(), 5)
        logLine("Image_AuxiliaryPictureType passed")
    }

    // OH_Pixelmap_InitializationOptions
    @Test
    fun testOH_PixelmapInitializationOptions_Create() {
        memScoped {
            val options = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapInitializationOptions_Create(options.ptr.reinterpret())
            logLine("OH_PixelmapInitializationOptions_Create(&options)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetWidth() {
        memScoped {
            val width = alloc<UIntVar>()
            val ret = OH_PixelmapInitializationOptions_GetWidth(null, width.ptr)
            logLine("OH_PixelmapInitializationOptions_GetWidth(null,&width)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetWidth() {
        val ret = OH_PixelmapInitializationOptions_SetWidth(null, 0u)
        logLine("OH_PixelmapInitializationOptions_SetWidth(null,0)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetHeight() {
        memScoped {
            val height = alloc<UIntVar>()
            val ret = OH_PixelmapInitializationOptions_GetHeight(null, height.ptr)
            logLine("OH_PixelmapInitializationOptions_GetHeight(null,&height)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetHeight() {
        val ret = OH_PixelmapInitializationOptions_SetHeight(null, 0u)
        logLine("OH_PixelmapInitializationOptions_SetHeight(null,0)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetPixelFormat() {
        memScoped {
            val pixelFormat = alloc<IntVar>()
            val ret = OH_PixelmapInitializationOptions_GetPixelFormat(null, pixelFormat.ptr)
            logLine("OH_PixelmapInitializationOptions_GetPixelFormat(null,&pixelFormat)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetPixelFormat() {
        val ret = OH_PixelmapInitializationOptions_SetPixelFormat(null, PIXEL_FORMAT_RGBA_8888.toInt())
        logLine("OH_PixelmapInitializationOptions_SetPixelFormat(null,...)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetSrcPixelFormat() {
        memScoped {
            val srcpixelFormat = alloc<IntVar>()
            val ret = OH_PixelmapInitializationOptions_GetSrcPixelFormat(null, srcpixelFormat.ptr)
            logLine("OH_PixelmapInitializationOptions_GetSrcPixelFormat(null,&src)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetSrcPixelFormat() {
        val ret = OH_PixelmapInitializationOptions_SetSrcPixelFormat(null, 0)
        logLine("OH_PixelmapInitializationOptions_SetSrcPixelFormat(null,0)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetRowStride() {
        memScoped {
            val rowStride = alloc<IntVar>()
            val ret = OH_PixelmapInitializationOptions_GetRowStride(null, rowStride.ptr)
            logLine("OH_PixelmapInitializationOptions_GetRowStride(null,&rowStride)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetRowStride() {
        val ret = OH_PixelmapInitializationOptions_SetRowStride(null, 0)
        logLine("OH_PixelmapInitializationOptions_SetRowStride(null,0)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetAlphaType() {
        memScoped {
            val alphaType = alloc<IntVar>()
            val ret = OH_PixelmapInitializationOptions_GetAlphaType(null, alphaType.ptr)
            logLine("OH_PixelmapInitializationOptions_GetAlphaType(null,&alphaType)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetAlphaType() {
        val ret = OH_PixelmapInitializationOptions_SetAlphaType(null, PIXELMAP_ALPHA_TYPE_OPAQUE.toInt())
        logLine("OH_PixelmapInitializationOptions_SetAlphaType(null,...)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_GetEditable() {
        memScoped {
            val editable = alloc<BooleanVar>()
            val ret = try { OH_PixelmapInitializationOptions_GetEditable(null, editable.ptr) } catch (e: Throwable) { logLine("OH_PixelmapInitializationOptions_GetEditable (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapInitializationOptions_GetEditable(null,&editable)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapInitializationOptions_SetEditable() {
        val ret = try { OH_PixelmapInitializationOptions_SetEditable(null, false) } catch (e: Throwable) { logLine("OH_PixelmapInitializationOptions_SetEditable (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PixelmapInitializationOptions_SetEditable(null,false)=$ret")
    }

    @Test
    fun testOH_PixelmapInitializationOptions_Release() {
        val ret = OH_PixelmapInitializationOptions_Release(null)
        logLine("OH_PixelmapInitializationOptions_Release(null)=$ret")
    }

    // OH_Pixelmap_ImageInfo
    @Test
    fun testOH_PixelmapImageInfo_Create() {
        memScoped {
            val info = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapImageInfo_Create(info.ptr.reinterpret())
            logLine("OH_PixelmapImageInfo_Create(&info)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetWidth() {
        memScoped {
            val width = alloc<UIntVar>()
            val ret = OH_PixelmapImageInfo_GetWidth(null, width.ptr)
            logLine("OH_PixelmapImageInfo_GetWidth(null,&width)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetHeight() {
        memScoped {
            val height = alloc<UIntVar>()
            val ret = OH_PixelmapImageInfo_GetHeight(null, height.ptr)
            logLine("OH_PixelmapImageInfo_GetHeight(null,&height)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetAlphaMode() {
        memScoped {
            val alphaMode = alloc<IntVar>()
            val ret = try { OH_PixelmapImageInfo_GetAlphaMode(null, alphaMode.ptr) } catch (e: Throwable) { logLine("OH_PixelmapImageInfo_GetAlphaMode (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapImageInfo_GetAlphaMode(null,&alphaMode)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetRowStride() {
        memScoped {
            val rowStride = alloc<UIntVar>()
            val ret = OH_PixelmapImageInfo_GetRowStride(null, rowStride.ptr)
            logLine("OH_PixelmapImageInfo_GetRowStride(null,&rowStride)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetPixelFormat() {
        memScoped {
            val pixelFormat = alloc<IntVar>()
            val ret = OH_PixelmapImageInfo_GetPixelFormat(null, pixelFormat.ptr)
            logLine("OH_PixelmapImageInfo_GetPixelFormat(null,&pixelFormat)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetAlphaType() {
        memScoped {
            val alphaType = alloc<IntVar>()
            val ret = OH_PixelmapImageInfo_GetAlphaType(null, alphaType.ptr)
            logLine("OH_PixelmapImageInfo_GetAlphaType(null,&alphaType)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_GetDynamicRange() {
        memScoped {
            val isHdr = alloc<BooleanVar>()
            val ret = OH_PixelmapImageInfo_GetDynamicRange(null, isHdr.ptr)
            logLine("OH_PixelmapImageInfo_GetDynamicRange(null,&isHdr)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapImageInfo_Release() {
        val ret = OH_PixelmapImageInfo_Release(null)
        logLine("OH_PixelmapImageInfo_Release(null)=$ret")
    }

    // OH_PixelmapNative - create/read/write
    @Test
    fun testOH_PixelmapNative_CreatePixelmap() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_CreatePixelmap(null, 0uL, null, pixelmap.ptr.reinterpret())
            logLine("OH_PixelmapNative_CreatePixelmap(null,0,null,&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_CreatePixelmapUsingAllocator() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreatePixelmapUsingAllocator(null, 0uL, null, IMAGE_ALLOCATOR_MODE_AUTO, pixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreatePixelmapUsingAllocator (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreatePixelmapUsingAllocator(...)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_ConvertPixelmapNativeToNapi() {
        memScoped {
            val pixelmapNapi = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_ConvertPixelmapNativeToNapi(null, null, pixelmapNapi.ptr.reinterpret())
            logLine("OH_PixelmapNative_ConvertPixelmapNativeToNapi(null,null,&napi)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_ConvertPixelmapNativeFromNapi() {
        memScoped {
            val pixelmapNative = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_ConvertPixelmapNativeFromNapi(null, null, pixelmapNative.ptr.reinterpret())
            logLine("OH_PixelmapNative_ConvertPixelmapNativeFromNapi(null,null,&native)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_ReadPixels() {
        memScoped {
            val bufferSize = alloc<ULongVar>()
            val ret = OH_PixelmapNative_ReadPixels(null, null, bufferSize.ptr)
            logLine("OH_PixelmapNative_ReadPixels(null,null,&bufferSize)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_WritePixels() {
        val ret = OH_PixelmapNative_WritePixels(null, null, 0uL)
        logLine("OH_PixelmapNative_WritePixels(null,null,0)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_ReadPixelsFromArea() {
        val ret = try { OH_PixelmapNative_ReadPixelsFromArea(null, null) } catch (e: Throwable) { logLine("OH_PixelmapNative_ReadPixelsFromArea (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PixelmapNative_ReadPixelsFromArea(null,null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_WritePixelsToArea() {
        val ret = try { OH_PixelmapNative_WritePixelsToArea(null, null) } catch (e: Throwable) { logLine("OH_PixelmapNative_WritePixelsToArea (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
        logLine("OH_PixelmapNative_WritePixelsToArea(null,null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_GetArgbPixels() {
        memScoped {
            val bufferSize = alloc<ULongVar>()
            val ret = OH_PixelmapNative_GetArgbPixels(null, null, bufferSize.ptr)
            logLine("OH_PixelmapNative_GetArgbPixels(null,null,&bufferSize)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_ToSdr() {
        val ret = OH_PixelmapNative_ToSdr(null)
        logLine("OH_PixelmapNative_ToSdr(null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_GetImageInfo() {
        val ret = OH_PixelmapNative_GetImageInfo(null, null)
        logLine("OH_PixelmapNative_GetImageInfo(null,null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_Opacity() {
        val ret = OH_PixelmapNative_Opacity(null, 1f)
        logLine("OH_PixelmapNative_Opacity(null,1)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_Scale() {
        val ret = OH_PixelmapNative_Scale(null, 1f, 1f)
        logLine("OH_PixelmapNative_Scale(null,1,1)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_ScaleWithAntiAliasing() {
        val ret = OH_PixelmapNative_ScaleWithAntiAliasing(null, 1f, 1f, OH_PixelmapNative_AntiAliasing_NONE)
        logLine("OH_PixelmapNative_ScaleWithAntiAliasing(null,1,1,NONE)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_CreateScaledPixelMap() {
        memScoped {
            val dstPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreateScaledPixelMap(null, dstPixelmap.ptr.reinterpret(), 1f, 1f) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreateScaledPixelMap (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreateScaledPixelMap(null,&dst,1,1)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_CreateScaledPixelMapWithAntiAliasing() {
        memScoped {
            val dstPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreateScaledPixelMapWithAntiAliasing(null, dstPixelmap.ptr.reinterpret(), 1f, 1f, OH_PixelmapNative_AntiAliasing_NONE) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreateScaledPixelMapWithAntiAliasing (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreateScaledPixelMapWithAntiAliasing(...)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_Translate() {
        val ret = OH_PixelmapNative_Translate(null, 0f, 0f)
        logLine("OH_PixelmapNative_Translate(null,0,0)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_CreateAlphaPixelmap() {
        memScoped {
            val dstPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreateAlphaPixelmap(null, dstPixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreateAlphaPixelmap (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreateAlphaPixelmap(null,&dst)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_Clone() {
        memScoped {
            val dstPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_Clone(null, dstPixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_Clone (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_Clone(null,&dst)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_CreateCroppedAndScaledPixelMap() {
        memScoped {
            val region = alloc<Image_Region>()
            val scale = alloc<Image_Scale>()
            val dstPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreateCroppedAndScaledPixelMap(null, region.ptr, scale.ptr, OH_PixelmapNative_AntiAliasing_NONE, dstPixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreateCroppedAndScaledPixelMap (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreateCroppedAndScaledPixelMap(...)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_Rotate() {
        val ret = OH_PixelmapNative_Rotate(null, 0f)
        logLine("OH_PixelmapNative_Rotate(null,0)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_Flip() {
        val ret = OH_PixelmapNative_Flip(null, false, false)
        logLine("OH_PixelmapNative_Flip(null,false,false)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_Crop() {
        memScoped {
            val region = alloc<Image_Region>()
            val ret = OH_PixelmapNative_Crop(null, region.ptr)
            logLine("OH_PixelmapNative_Crop(null,&region)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_Release() {
        val ret = OH_PixelmapNative_Release(null)
        logLine("OH_PixelmapNative_Release(null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_Destroy() {
        memScoped {
            val slot = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_Destroy(slot.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_Destroy (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_Destroy(&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_ConvertAlphaFormat() {
        val ret = OH_PixelmapNative_ConvertAlphaFormat(null, null, false)
        logLine("OH_PixelmapNative_ConvertAlphaFormat(null,null,false)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_CreateEmptyPixelmap() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_CreateEmptyPixelmap(null, pixelmap.ptr.reinterpret())
            logLine("OH_PixelmapNative_CreateEmptyPixelmap(null,&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_CreateEmptyPixelmapUsingAllocator() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreateEmptyPixelmapUsingAllocator(null, IMAGE_ALLOCATOR_MODE_AUTO, pixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreateEmptyPixelmapUsingAllocator (API 20) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreateEmptyPixelmapUsingAllocator(...)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_CreatePixelmapFromSurface() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreatePixelmapFromSurface(null, 0uL, pixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreatePixelmapFromSurface (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreatePixelmapFromSurface(null,0,&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_CreatePixelmapFromNativeBuffer() {
        memScoped {
            val pixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = try { OH_PixelmapNative_CreatePixelmapFromNativeBuffer(null, pixelmap.ptr.reinterpret()) } catch (e: Throwable) { logLine("OH_PixelmapNative_CreatePixelmapFromNativeBuffer (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_CreatePixelmapFromNativeBuffer(null,&pixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_GetMetadata() {
        memScoped {
            val value = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_GetMetadata(null, HDR_METADATA_TYPE, value.ptr.reinterpret())
            logLine("OH_PixelmapNative_GetMetadata(null,key,&value)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_SetMetadata() {
        val ret = OH_PixelmapNative_SetMetadata(null, HDR_METADATA_TYPE, null)
        logLine("OH_PixelmapNative_SetMetadata(null,key,null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_GetNativeBuffer() {
        memScoped {
            val nativeBuffer = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_GetNativeBuffer(null, nativeBuffer.ptr.reinterpret())
            logLine("OH_PixelmapNative_GetNativeBuffer(null,&nativeBuffer)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_GetColorSpaceNative() {
        memScoped {
            val colorSpaceNative = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_GetColorSpaceNative(null, colorSpaceNative.ptr.reinterpret())
            logLine("OH_PixelmapNative_GetColorSpaceNative(null,&colorSpace)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_SetColorSpaceNative() {
        val ret = OH_PixelmapNative_SetColorSpaceNative(null, null)
        logLine("OH_PixelmapNative_SetColorSpaceNative(null,null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_SetMemoryName() {
        memScoped {
            val size = alloc<ULongVar>()
            val ret = OH_PixelmapNative_SetMemoryName(null, null, size.ptr)
            logLine("OH_PixelmapNative_SetMemoryName(null,null,&size)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_GetByteCount() {
        memScoped {
            val byteCount = alloc<UIntVar>()
            val ret = try { OH_PixelmapNative_GetByteCount(null, byteCount.ptr) } catch (e: Throwable) { logLine("OH_PixelmapNative_GetByteCount (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_GetByteCount(null,&byteCount)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_GetAllocationByteCount() {
        memScoped {
            val allocationByteCount = alloc<UIntVar>()
            val ret = try { OH_PixelmapNative_GetAllocationByteCount(null, allocationByteCount.ptr) } catch (e: Throwable) { logLine("OH_PixelmapNative_GetAllocationByteCount (API 18) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_GetAllocationByteCount(null,&count)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_AccessPixels() {
        memScoped {
            val addr = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PixelmapNative_AccessPixels(null, addr.ptr.reinterpret())
            logLine("OH_PixelmapNative_AccessPixels(null,&addr)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_UnaccessPixels() {
        val ret = OH_PixelmapNative_UnaccessPixels(null)
        logLine("OH_PixelmapNative_UnaccessPixels(null)=$ret")
    }

    @Test
    fun testOH_PixelmapNative_GetUniqueId() {
        memScoped {
            val uniqueId = alloc<UIntVar>()
            val ret = try { OH_PixelmapNative_GetUniqueId(null, uniqueId.ptr) } catch (e: Throwable) { logLine("OH_PixelmapNative_GetUniqueId (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_GetUniqueId(null,&uniqueId)=$ret")
        }
    }

    @Test
    fun testOH_PixelmapNative_IsReleased() {
        memScoped {
            val released = alloc<BooleanVar>()
            val ret = try { OH_PixelmapNative_IsReleased(null, released.ptr) } catch (e: Throwable) { logLine("OH_PixelmapNative_IsReleased (API 22) exception: $e"); IMAGE_BAD_PARAMETER }
            logLine("OH_PixelmapNative_IsReleased(null,&released)=$ret")
        }
    }

    // ==================== picture_native.h ====================

    @Test
    fun testOH_PictureNative_CreatePicture() {
        memScoped {
            val picture = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureNative_CreatePicture(null, picture.ptr.reinterpret())
            logLine("OH_PictureNative_CreatePicture(null,&picture)=$ret")
        }
    }

    @Test
    fun testOH_PictureNative_GetMainPixelmap() {
        memScoped {
            val mainPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureNative_GetMainPixelmap(null, mainPixelmap.ptr.reinterpret())
            logLine("OH_PictureNative_GetMainPixelmap(null,&mainPixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PictureNative_GetHdrComposedPixelmap() {
        memScoped {
            val hdrPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureNative_GetHdrComposedPixelmap(null, hdrPixelmap.ptr.reinterpret())
            logLine("OH_PictureNative_GetHdrComposedPixelmap(null,&hdrPixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PictureNative_GetGainmapPixelmap() {
        memScoped {
            val gainmapPixelmap = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureNative_GetGainmapPixelmap(null, gainmapPixelmap.ptr.reinterpret())
            logLine("OH_PictureNative_GetGainmapPixelmap(null,&gainmapPixelmap)=$ret")
        }
    }

    @Test
    fun testOH_PictureNative_SetAuxiliaryPicture() {
        val ret = OH_PictureNative_SetAuxiliaryPicture(null, AUXILIARY_PICTURE_TYPE_GAINMAP, null)
        logLine("OH_PictureNative_SetAuxiliaryPicture(null,GAINMAP,null)=$ret")
    }

    @Test
    fun testOH_PictureNative_GetAuxiliaryPicture() {
        memScoped {
            val auxiliaryPicture = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureNative_GetAuxiliaryPicture(null, AUXILIARY_PICTURE_TYPE_GAINMAP, auxiliaryPicture.ptr.reinterpret())
            logLine("OH_PictureNative_GetAuxiliaryPicture(null,GAINMAP,&auxiliaryPicture)=$ret")
        }
    }

    @Test
    fun testOH_PictureNative_GetMetadata() {
        memScoped {
            val metadata = alloc<CPointerVar<ByteVar>>()
            val ret = OH_PictureNative_GetMetadata(null, EXIF_METADATA, metadata.ptr.reinterpret())
            logLine("OH_PictureNative_GetMetadata(null,EXIF,&metadata)=$ret")
        }
    }

    @Test
    fun testOH_PictureNative_SetMetadata() {
        val ret = OH_PictureNative_SetMetadata(null, EXIF_METADATA, null)
        logLine("OH_PictureNative_SetMetadata(null,EXIF,null)=$ret")
    }

    @Test
    fun testOH_PictureNative_Release() {
        val ret = OH_PictureNative_Release(null)
        logLine("OH_PictureNative_Release(null)=$ret")
    }

    // OH_AuxiliaryPictureNative
    @Test
    fun testOH_AuxiliaryPictureNative_Create() {
        memScoped {
            val size = alloc<Image_Size>()
            val auxiliaryPicture = alloc<CPointerVar<ByteVar>>()
            val ret = OH_AuxiliaryPictureNative_Create(null, 0uL, size.ptr, AUXILIARY_PICTURE_TYPE_GAINMAP, auxiliaryPicture.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureNative_Create(null,0,&size,GAINMAP,&auxiliaryPicture)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureNative_WritePixels() {
        val ret = OH_AuxiliaryPictureNative_WritePixels(null, null, 0uL)
        logLine("OH_AuxiliaryPictureNative_WritePixels(null,null,0)=$ret")
    }

    @Test
    fun testOH_AuxiliaryPictureNative_ReadPixels() {
        memScoped {
            val bufferSize = alloc<ULongVar>()
            val ret = OH_AuxiliaryPictureNative_ReadPixels(null, null, bufferSize.ptr)
            logLine("OH_AuxiliaryPictureNative_ReadPixels(null,null,&bufferSize)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureNative_GetType() {
        memScoped {
            val type = alloc<IntVar>()
            val ret = OH_AuxiliaryPictureNative_GetType(null, type.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureNative_GetType(null,&type)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureNative_GetInfo() {
        memScoped {
            val info = alloc<CPointerVar<ByteVar>>()
            val ret = OH_AuxiliaryPictureNative_GetInfo(null, info.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureNative_GetInfo(null,&info)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureNative_SetInfo() {
        val ret = OH_AuxiliaryPictureNative_SetInfo(null, null)
        logLine("OH_AuxiliaryPictureNative_SetInfo(null,null)=$ret")
    }

    @Test
    fun testOH_AuxiliaryPictureNative_GetMetadata() {
        memScoped {
            val metadata = alloc<CPointerVar<ByteVar>>()
            val ret = OH_AuxiliaryPictureNative_GetMetadata(null, EXIF_METADATA, metadata.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureNative_GetMetadata(null,EXIF,&metadata)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureNative_SetMetadata() {
        val ret = OH_AuxiliaryPictureNative_SetMetadata(null, EXIF_METADATA, null)
        logLine("OH_AuxiliaryPictureNative_SetMetadata(null,EXIF,null)=$ret")
    }

    @Test
    fun testOH_AuxiliaryPictureNative_Release() {
        val ret = OH_AuxiliaryPictureNative_Release(null)
        logLine("OH_AuxiliaryPictureNative_Release(null)=$ret")
    }

    // OH_AuxiliaryPictureInfo
    @Test
    fun testOH_AuxiliaryPictureInfo_Create() {
        memScoped {
            val info = alloc<CPointerVar<ByteVar>>()
            val ret = OH_AuxiliaryPictureInfo_Create(info.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureInfo_Create(&info)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_GetType() {
        memScoped {
            val type = alloc<IntVar>()
            val ret = OH_AuxiliaryPictureInfo_GetType(null, type.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureInfo_GetType(null,&type)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_SetType() {
        val ret = OH_AuxiliaryPictureInfo_SetType(null, AUXILIARY_PICTURE_TYPE_GAINMAP)
        logLine("OH_AuxiliaryPictureInfo_SetType(null,GAINMAP)=$ret")
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_GetSize() {
        memScoped {
            val size = alloc<Image_Size>()
            val ret = OH_AuxiliaryPictureInfo_GetSize(null, size.ptr)
            logLine("OH_AuxiliaryPictureInfo_GetSize(null,&size)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_SetSize() {
        memScoped {
            val size = alloc<Image_Size>()
            val ret = OH_AuxiliaryPictureInfo_SetSize(null, size.ptr)
            logLine("OH_AuxiliaryPictureInfo_SetSize(null,&size)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_GetRowStride() {
        memScoped {
            val rowStride = alloc<UIntVar>()
            val ret = OH_AuxiliaryPictureInfo_GetRowStride(null, rowStride.ptr)
            logLine("OH_AuxiliaryPictureInfo_GetRowStride(null,&rowStride)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_SetRowStride() {
        val ret = OH_AuxiliaryPictureInfo_SetRowStride(null, 0u)
        logLine("OH_AuxiliaryPictureInfo_SetRowStride(null,0)=$ret")
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_GetPixelFormat() {
        memScoped {
            val pixelFormat = alloc<IntVar>()
            val ret = OH_AuxiliaryPictureInfo_GetPixelFormat(null, pixelFormat.ptr.reinterpret())
            logLine("OH_AuxiliaryPictureInfo_GetPixelFormat(null,&pixelFormat)=$ret")
        }
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_SetPixelFormat() {
        val ret = OH_AuxiliaryPictureInfo_SetPixelFormat(null, PIXEL_FORMAT_RGBA_8888)
        logLine("OH_AuxiliaryPictureInfo_SetPixelFormat(null,RGBA_8888)=$ret")
    }

    @Test
    fun testOH_AuxiliaryPictureInfo_Release() {
        val ret = OH_AuxiliaryPictureInfo_Release(null)
        logLine("OH_AuxiliaryPictureInfo_Release(null)=$ret")
    }
}
