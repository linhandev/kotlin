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
import platform.MediaLibraryKit.MediaAssetManager.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class MediaAssetManagerTest {

    private fun logLine(msg: String) = println("[stdout] MediaAssetManagerTest $msg")

    @Test
    fun testEnum_MediaAssetBase() {
        assertEquals(MEDIA_LIBRARY_OK.toInt(), 0)
        assertEquals(MEDIA_LIBRARY_PERMISSION_DENIED.toInt(), 201)
        assertEquals(MEDIA_LIBRARY_PARAMETER_ERROR.toInt(), 401)
        assertEquals(MEDIA_LIBRARY_NO_SUCH_FILE.toInt(), 23800101)
        assertEquals(MEDIA_LIBRARY_INVALID_DISPLAY_NAME.toInt(), 23800102)
        assertEquals(MEDIA_LIBRARY_INVALID_ASSET_URI.toInt(), 23800103)
        assertEquals(MEDIA_LIBRARY_INVALID_PHOTO_KEY.toInt(), 23800104)
        assertEquals(MEDIA_LIBRARY_OPERATION_NOT_SUPPORTED.toInt(), 23800201)
        assertEquals(MEDIA_LIBRARY_INTERNAL_SYSTEM_ERROR.toInt(), 23800301)
        assertEquals(MEDIA_LIBRARY_FAST_MODE.toInt(), 0)
        assertEquals(MEDIA_LIBRARY_HIGH_QUALITY_MODE.toInt(), 1)
        assertEquals(MEDIA_LIBRARY_BALANCED_MODE.toInt(), 2)
        assertEquals(MEDIA_LIBRARY_IMAGE.toInt(), 1)
        assertEquals(MEDIA_LIBRARY_VIDEO.toInt(), 2)
        assertEquals(MEDIA_LIBRARY_DEFAULT.toInt(), 0)
        assertEquals(MEDIA_LIBRARY_MOVING_PHOTO.toInt(), 3)
        assertEquals(MEDIA_LIBRARY_BURST.toInt(), 4)
        assertEquals(MEDIA_LIBRARY_IMAGE_RESOURCE.toInt(), 1)
        assertEquals(MEDIA_LIBRARY_VIDEO_RESOURCE.toInt(), 2)
        assertEquals(MEDIA_LIBRARY_IMAGE_JPEG.toInt(), 1)
        assertEquals(MEDIA_LIBRARY_FILE_VIDEO.toInt(), 3)
        assertEquals(MEDIA_LIBRARY_QUALITY_FAST.toInt(), 1)
        assertEquals(MEDIA_LIBRARY_QUALITY_FULL.toInt(), 2)
        assertEquals(MEDIA_LIBRARY_COMPRESSED.toInt(), 1)
        assertEquals(MEDIA_LIBRARY_PICTURE_OBJECT.toInt(), 2)
        logLine("testEnum_MediaAssetBase passed")
    }

    // ==================== MovingPhoto ====================

    @Test
    fun testOH_MovingPhoto_GetUri() {
        memScoped {
            val uriPtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_MovingPhoto_GetUri(null, uriPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MovingPhoto_GetUri=$rc")
        }
    }

    @Test
    fun testOH_MovingPhoto_RequestContentWithUris() {
        memScoped {
            val rc = OH_MovingPhoto_RequestContentWithUris(null, null, null)
            assertNotNull(rc)
            logLine("OH_MovingPhoto_RequestContentWithUris=$rc")
        }
    }

    @Test
    fun testOH_MovingPhoto_RequestContentWithUri() {
        memScoped {
            val rc = OH_MovingPhoto_RequestContentWithUri(null, MEDIA_LIBRARY_IMAGE_RESOURCE, null)
            assertNotNull(rc)
            logLine("OH_MovingPhoto_RequestContentWithUri=$rc")
        }
    }

    @Test
    fun testOH_MovingPhoto_RequestContentWithBuffer() {
        memScoped {
            val bufferPtr = alloc<CPointerVar<UByteVar>>()
            val sizePtr = alloc<UIntVar>()
            val rc = OH_MovingPhoto_RequestContentWithBuffer(null, MEDIA_LIBRARY_IMAGE_RESOURCE, bufferPtr.ptr, sizePtr.ptr)
            assertNotNull(rc)
            logLine("OH_MovingPhoto_RequestContentWithBuffer=$rc")
        }
    }

    @Test
    fun testOH_MovingPhoto_Release() {
        memScoped {
            val rc = OH_MovingPhoto_Release(null)
            assertNotNull(rc)
            logLine("OH_MovingPhoto_Release=$rc")
        }
    }

    // ==================== MediaAssetManager ====================

    @Test
    fun testOH_MediaAssetManager_Create() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            logLine("OH_MediaAssetManager_Create=$manager")
            OH_MediaAssetManager_Release(manager)
        }
    }

    @Test
    fun testOH_MediaAssetManager_RequestImageForPath() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            val requestOptions = alloc<MediaLibrary_RequestOptions>()
            val reqId = OH_MediaAssetManager_RequestImageForPath(manager, null, requestOptions.readValue(), null, null)
            logLine("OH_MediaAssetManager_RequestImageForPath=$reqId")
            OH_MediaAssetManager_Release(manager)
        }
    }

    @Test
    fun testOH_MediaAssetManager_RequestVideoForPath() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            val requestOptions = alloc<MediaLibrary_RequestOptions>()
            val reqVideoId = OH_MediaAssetManager_RequestVideoForPath(manager, null, requestOptions.readValue(), null, null)
            logLine("OH_MediaAssetManager_RequestVideoForPath=$reqVideoId")
            OH_MediaAssetManager_Release(manager)
        }
    }

    @Test
    fun testOH_MediaAssetManager_CancelRequest() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            val requestId = alloc<MediaLibrary_RequestId>()
            val rc = OH_MediaAssetManager_CancelRequest(manager, requestId.readValue())
            assertNotNull(rc)
            logLine("OH_MediaAssetManager_CancelRequest=$rc")
            OH_MediaAssetManager_Release(manager)
        }
    }

    @Test
    fun testOH_MediaAssetManager_RequestImage() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            val requestOptions = alloc<MediaLibrary_RequestOptions>()
            val requestId = alloc<MediaLibrary_RequestId>()
            val rc = OH_MediaAssetManager_RequestImage(manager, null, requestOptions.readValue(), requestId.ptr, null)
            assertNotNull(rc)
            logLine("OH_MediaAssetManager_RequestImage=$rc")
            OH_MediaAssetManager_Release(manager)
        }
    }

    @Test
    fun testOH_MediaAssetManager_RequestMovingPhoto() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            val requestOptions = alloc<MediaLibrary_RequestOptions>()
            val movingRequestId = alloc<MediaLibrary_RequestId>()
            val rc = OH_MediaAssetManager_RequestMovingPhoto(manager, null, requestOptions.readValue(), movingRequestId.ptr, null)
            assertNotNull(rc)
            logLine("OH_MediaAssetManager_RequestMovingPhoto=$rc")
            OH_MediaAssetManager_Release(manager)
        }
    }

    @Test
    fun testOH_MediaAssetManager_Release() {
        memScoped {
            val manager = OH_MediaAssetManager_Create()
            assertNotNull(manager)
            val rc = OH_MediaAssetManager_Release(manager)
            assertNotNull(rc)
            logLine("OH_MediaAssetManager_Release=$rc")
        }
    }

    // ==================== MediaAssetChangeRequest / MediaAccessHelper ====================

    @Test
    fun testOH_MediaAssetChangeRequest_Create() {
        memScoped {
            val changeRequest = OH_MediaAssetChangeRequest_Create(null)
            logLine("OH_MediaAssetChangeRequest_Create=$changeRequest")
        }
    }

    @Test
    fun testOH_MediaAccessHelper_ApplyChanges() {
        memScoped {
            val changeRequest = OH_MediaAssetChangeRequest_Create(null)
            val rc = OH_MediaAccessHelper_ApplyChanges(changeRequest)
            assertNotNull(rc)
            logLine("OH_MediaAccessHelper_ApplyChanges=$rc")
        }
    }

    @Test
    fun testOH_MediaAssetChangeRequest_AddResourceWithUri() {
        memScoped {
            val rc = OH_MediaAssetChangeRequest_AddResourceWithUri(null, MEDIA_LIBRARY_IMAGE_RESOURCE, null)
            assertNotNull(rc)
            logLine("OH_MediaAssetChangeRequest_AddResourceWithUri=$rc")
        }
    }

    @Test
    fun testOH_MediaAssetChangeRequest_AddResourceWithBuffer() {
        memScoped {
            val rc = OH_MediaAssetChangeRequest_AddResourceWithBuffer(null, MEDIA_LIBRARY_IMAGE_RESOURCE, null, 0u)
            assertNotNull(rc)
            logLine("OH_MediaAssetChangeRequest_AddResourceWithBuffer=$rc")
        }
    }

    @Test
    fun testOH_MediaAssetChangeRequest_GetWriteCacheHandler() {
        memScoped {
            val fdPtr = alloc<IntVar>()
            val rc = OH_MediaAssetChangeRequest_GetWriteCacheHandler(null, fdPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAssetChangeRequest_GetWriteCacheHandler=$rc")
        }
    }

    @Test
    fun testOH_MediaAssetChangeRequest_SaveCameraPhoto() {
        memScoped {
            val rc = OH_MediaAssetChangeRequest_SaveCameraPhoto(null, MEDIA_LIBRARY_IMAGE_JPEG)
            assertNotNull(rc)
            logLine("OH_MediaAssetChangeRequest_SaveCameraPhoto=$rc")
        }
    }

    @Test
    fun testOH_MediaAssetChangeRequest_DiscardCameraPhoto() {
        memScoped {
            val rc = OH_MediaAssetChangeRequest_DiscardCameraPhoto(null)
            assertNotNull(rc)
            logLine("OH_MediaAssetChangeRequest_DiscardCameraPhoto=$rc")
        }
    }

    @Test
    fun testOH_MediaAssetChangeRequest_Release() {
        memScoped {
            val rc = OH_MediaAssetChangeRequest_Release(null)
            assertNotNull(rc)
            logLine("OH_MediaAssetChangeRequest_Release=$rc")
        }
    }

    // ==================== MediaAsset ====================

    @Test
    fun testOH_MediaAsset_GetUri() {
        memScoped {
            val uriPtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_MediaAsset_GetUri(null, uriPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetUri=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetMediaType() {
        memScoped {
            val mediaTypePtr = alloc<IntVar>()
            val rc = OH_MediaAsset_GetMediaType(null, mediaTypePtr.ptr.reinterpret())
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetMediaType=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetMediaSubType() {
        memScoped {
            val mediaSubTypePtr = alloc<IntVar>()
            val rc = OH_MediaAsset_GetMediaSubType(null, mediaSubTypePtr.ptr.reinterpret())
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetMediaSubType=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDisplayName() {
        memScoped {
            val displayNamePtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_MediaAsset_GetDisplayName(null, displayNamePtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDisplayName=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetSize() {
        memScoped {
            val sizePtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetSize(null, sizePtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetSize=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDateAdded() {
        memScoped {
            val dateAddedPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetDateAdded(null, dateAddedPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDateAdded=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDateModified() {
        memScoped {
            val dateModifiedPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetDateModified(null, dateModifiedPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDateModified=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDateTaken() {
        memScoped {
            val dateTakenPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetDateTaken(null, dateTakenPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDateTaken=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDateAddedMs() {
        memScoped {
            val dateAddedMsPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetDateAddedMs(null, dateAddedMsPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDateAddedMs=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDateModifiedMs() {
        memScoped {
            val dateModifiedMsPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetDateModifiedMs(null, dateModifiedMsPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDateModifiedMs=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetDuration() {
        memScoped {
            val durationPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetDuration(null, durationPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetDuration=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetWidth() {
        memScoped {
            val widthPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetWidth(null, widthPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetWidth=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetHeight() {
        memScoped {
            val heightPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetHeight(null, heightPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetHeight=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetOrientation() {
        memScoped {
            val orientationPtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_GetOrientation(null, orientationPtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetOrientation=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_IsFavorite() {
        memScoped {
            val favoritePtr = alloc<UIntVar>()
            val rc = OH_MediaAsset_IsFavorite(null, favoritePtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_IsFavorite=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_GetTitle() {
        memScoped {
            val titlePtr = alloc<CPointerVar<ByteVar>>()
            val rc = OH_MediaAsset_GetTitle(null, titlePtr.ptr)
            assertNotNull(rc)
            logLine("OH_MediaAsset_GetTitle=$rc")
        }
    }

    @Test
    fun testOH_MediaAsset_Release() {
        memScoped {
            val rc = OH_MediaAsset_Release(null)
            assertNotNull(rc)
            logLine("OH_MediaAsset_Release=$rc")
        }
    }
}
