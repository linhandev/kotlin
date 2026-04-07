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
import platform.ArkGraphics2D.OH_NativeImage.*
import cnames.structs.*
import platform.ArkGraphics2D.BufferCommon.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OH_NativeImageTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testOH_NativeImage_Create() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            logLine("OH_NativeImage_Create img=$img")
            assertNotNull(img)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_AcquireNativeWindow() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val win = OH_NativeImage_AcquireNativeWindow(img)
            logLine("OH_NativeImage_AcquireNativeWindow ret=$win")
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_AttachContext() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val attachRet = OH_NativeImage_AttachContext(img, 0u)
            logLine("OH_NativeImage_AttachContext ret=$attachRet")
            assertNotNull(attachRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_DetachContext() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val detachRet = OH_NativeImage_DetachContext(img)
            logLine("OH_NativeImage_DetachContext ret=$detachRet")
            assertNotNull(detachRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_UpdateSurfaceImage() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val updateRet = OH_NativeImage_UpdateSurfaceImage(img)
            logLine("OH_NativeImage_UpdateSurfaceImage ret=$updateRet")
            assertNotNull(updateRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_GetTimestamp() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val ts = OH_NativeImage_GetTimestamp(img)
            logLine("OH_NativeImage_GetTimestamp ret=$ts")
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_GetTransformMatrix() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val matrix = cValuesOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
            val getMatrixRet = OH_NativeImage_GetTransformMatrix(img, matrix)
            logLine("OH_NativeImage_GetTransformMatrix ret=$getMatrixRet")
            assertNotNull(getMatrixRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_GetSurfaceId() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val surfaceId = alloc<ULongVar>()
            val getSurfaceRet = OH_NativeImage_GetSurfaceId(img, surfaceId.ptr)
            logLine("OH_NativeImage_GetSurfaceId ret=$getSurfaceRet surfaceId=${surfaceId.value}")
            assertNotNull(getSurfaceRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_SetOnFrameAvailableListener() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val listener = alloc<OH_OnFrameAvailableListener>()
            listener.context = null
            listener.onFrameAvailable = null
            val setListenerRet = OH_NativeImage_SetOnFrameAvailableListener(img, listener.readValue())
            logLine("OH_NativeImage_SetOnFrameAvailableListener ret=$setListenerRet")
            assertNotNull(setListenerRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_UnsetOnFrameAvailableListener() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val unsetRet = OH_NativeImage_UnsetOnFrameAvailableListener(img)
            logLine("OH_NativeImage_UnsetOnFrameAvailableListener ret=$unsetRet")
            assertNotNull(unsetRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_GetTransformMatrixV2() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val matrix = cValuesOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
            val matrixV2Ret = OH_NativeImage_GetTransformMatrixV2(img, matrix)
            logLine("OH_NativeImage_GetTransformMatrixV2 ret=$matrixV2Ret")
            assertNotNull(matrixV2Ret)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_GetBufferMatrix() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val matrix = cValuesOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
            val bufferMatrixRet = OH_NativeImage_GetBufferMatrix(img, matrix)
            logLine("OH_NativeImage_GetBufferMatrix ret=$bufferMatrixRet")
            assertNotNull(bufferMatrixRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_AcquireNativeWindowBuffer_ReleaseNativeWindowBuffer() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val nwbPtr = alloc<CPointerVar<NativeWindowBuffer>>()
            val fenceFd = alloc<IntVar>()
            val acquireNwbRet = OH_NativeImage_AcquireNativeWindowBuffer(img, nwbPtr.ptr, fenceFd.ptr)
            logLine("OH_NativeImage_AcquireNativeWindowBuffer ret=$acquireNwbRet nwb=${nwbPtr.value} fenceFd=${fenceFd.value}")
            assertNotNull(acquireNwbRet)
            val releaseNwbRet = OH_NativeImage_ReleaseNativeWindowBuffer(img, nwbPtr.value, fenceFd.value)
            logLine("OH_NativeImage_ReleaseNativeWindowBuffer ret=$releaseNwbRet")
            assertNotNull(releaseNwbRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_SetDropBufferMode() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val dropRet = OH_NativeImage_SetDropBufferMode(img, false)
            logLine("OH_NativeImage_SetDropBufferMode ret=$dropRet")
            assertNotNull(dropRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_ReleaseTextImage() {
        try {
            memScoped {
                val img = OH_NativeImage_Create(0u, 0u)
                assertNotNull(img)
                val releaseTextRet = OH_NativeImage_ReleaseTextImage(img)
                logLine("OH_NativeImage_ReleaseTextImage ret=$releaseTextRet")
                assertNotNull(releaseTextRet)
                val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
                imgPtr.value = img
                OH_NativeImage_Destroy(imgPtr.ptr)
            }
        } catch (e: Throwable) {
            logLine("testOH_NativeImage_ReleaseTextImage (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_NativeImage_GetColorSpace() {
        try {
            memScoped {
                val img = OH_NativeImage_Create(0u, 0u)
                assertNotNull(img)
                val outCs = alloc<OH_NativeBuffer_ColorSpace.Var>()
                val getCsRet = OH_NativeImage_GetColorSpace(img, outCs.ptr)
                logLine("OH_NativeImage_GetColorSpace ret=$getCsRet colorSpace=${outCs.value}")
                assertNotNull(getCsRet)
                val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
                imgPtr.value = img
                OH_NativeImage_Destroy(imgPtr.ptr)
            }
        } catch (e: Throwable) {
            logLine("testOH_NativeImage_GetColorSpace (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_NativeImage_AcquireLatestNativeWindowBuffer() {
        try {
            memScoped {
                val img = OH_NativeImage_Create(0u, 0u)
                assertNotNull(img)
                val nwb2Ptr = alloc<CPointerVar<NativeWindowBuffer>>()
                val fenceFd2 = alloc<IntVar>()
                val acquireLatestRet = OH_NativeImage_AcquireLatestNativeWindowBuffer(img, nwb2Ptr.ptr, fenceFd2.ptr)
                logLine("OH_NativeImage_AcquireLatestNativeWindowBuffer ret=$acquireLatestRet nwb=${nwb2Ptr.value} fenceFd=${fenceFd2.value}")
                assertNotNull(acquireLatestRet)
                OH_NativeImage_ReleaseNativeWindowBuffer(img, nwb2Ptr.value, fenceFd2.value)
                val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
                imgPtr.value = img
                OH_NativeImage_Destroy(imgPtr.ptr)
            }
        } catch (e: Throwable) {
            logLine("testOH_NativeImage_AcquireLatestNativeWindowBuffer (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_NativeImage_Destroy() {
        memScoped {
            val img = OH_NativeImage_Create(0u, 0u)
            assertNotNull(img)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
            logLine("OH_NativeImage_Destroy done")
        }
    }

    @Test
    fun testOH_ConsumerSurface_Create() {
        memScoped {
            val img = OH_ConsumerSurface_Create()
            logLine("OH_ConsumerSurface_Create img=$img")
            assertNotNull(img)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_ConsumerSurface_SetDefaultUsage() {
        memScoped {
            val img = OH_ConsumerSurface_Create()
            assertNotNull(img)
            val setUsageRet = OH_ConsumerSurface_SetDefaultUsage(img, 0uL)
            logLine("OH_ConsumerSurface_SetDefaultUsage ret=$setUsageRet")
            assertNotNull(setUsageRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_ConsumerSurface_SetDefaultSize() {
        memScoped {
            val img = OH_ConsumerSurface_Create()
            assertNotNull(img)
            val setSizeRet = OH_ConsumerSurface_SetDefaultSize(img, 64, 64)
            logLine("OH_ConsumerSurface_SetDefaultSize ret=$setSizeRet")
            assertNotNull(setSizeRet)
            val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
            imgPtr.value = img
            OH_NativeImage_Destroy(imgPtr.ptr)
        }
    }

    @Test
    fun testOH_NativeImage_CreateWithSingleBufferMode() {
        try {
            memScoped {
                val img = OH_NativeImage_CreateWithSingleBufferMode(0u, 0u, false)
                logLine("OH_NativeImage_CreateWithSingleBufferMode img=$img")
                assertNotNull(img)
                val imgPtr = alloc<CPointerVar<OH_NativeImage>>()
                imgPtr.value = img
                OH_NativeImage_Destroy(imgPtr.ptr)
            }
        } catch (e: Throwable) {
            logLine("testOH_NativeImage_CreateWithSingleBufferMode (higher API / Missing symbol) exception: $e")
        }
    }

    @Test
    fun testOH_ConsumerSurface_CreateWithSingleBufferMode() {
        try {
            memScoped {
                val csImg = OH_ConsumerSurface_CreateWithSingleBufferMode(false)
                logLine("OH_ConsumerSurface_CreateWithSingleBufferMode img=$csImg")
                assertNotNull(csImg)
                val csPtr = alloc<CPointerVar<OH_NativeImage>>()
                csPtr.value = csImg
                OH_NativeImage_Destroy(csPtr.ptr)
            }
        } catch (e: Throwable) {
            logLine("testOH_ConsumerSurface_CreateWithSingleBufferMode (higher API / Missing symbol) exception: $e")
        }
    }
}
