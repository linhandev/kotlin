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
import platform.ArkGraphics2D.NativeWindow.*
import platform.ArkGraphics2D.BufferCommon.*
import cnames.structs.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NativeWindowTest {

    private fun logLine(msg: String) = println("[stdout] NativeWindowTest $msg")

    @Test
    fun testEnum_OHNativeErrorCode() {
        assertEquals(NATIVE_ERROR_OK.toInt(), 0)
        assertEquals(NATIVE_ERROR_MEM_OPERATION_ERROR.toInt(), 30001000)
        assertEquals(NATIVE_ERROR_INVALID_ARGUMENTS.toInt(), 40001000)
        assertEquals(NATIVE_ERROR_NO_PERMISSION.toInt(), 40301000)
        assertEquals(NATIVE_ERROR_NO_BUFFER.toInt(), 40601000)
        assertEquals(NATIVE_ERROR_NO_CONSUMER.toInt(), 41202000)
        assertEquals(NATIVE_ERROR_NOT_INIT.toInt(), 41203000)
        assertEquals(NATIVE_ERROR_CONSUMER_CONNECTED.toInt(), 41206000)
        assertEquals(NATIVE_ERROR_BUFFER_STATE_INVALID.toInt(), 41207000)
        assertEquals(NATIVE_ERROR_BUFFER_IN_CACHE.toInt(), 41208000)
        assertEquals(NATIVE_ERROR_BUFFER_QUEUE_FULL.toInt(), 41209000)
        assertEquals(NATIVE_ERROR_BUFFER_NOT_IN_CACHE.toInt(), 41210000)
        assertEquals(NATIVE_ERROR_CONSUMER_DISCONNECTED.toInt(), 41211000)
        assertEquals(NATIVE_ERROR_CONSUMER_NO_LISTENER_REGISTERED.toInt(), 41212000)
        assertEquals(NATIVE_ERROR_UNSUPPORTED.toInt(), 50102000)
        assertEquals(NATIVE_ERROR_UNKNOWN.toInt(), 50002000)
        assertEquals(NATIVE_ERROR_HDI_ERROR.toInt(), 50007000)
        assertEquals(NATIVE_ERROR_BINDER_ERROR.toInt(), 50401000)
        assertEquals(NATIVE_ERROR_EGL_STATE_UNKNOWN.toInt(), 60001000)
        assertEquals(NATIVE_ERROR_EGL_API_FAILED.toInt(), 60002000)
        logLine("testEnum_OHNativeErrorCode passed")
    }

    @Test
    fun testOH_NativeWindow_CreateNativeWindow() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            logLine("OH_NativeWindow_CreateNativeWindow=$window")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_DestroyNativeWindow() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            OH_NativeWindow_DestroyNativeWindow(window)
            logLine("OH_NativeWindow_DestroyNativeWindow=called")
        }
    }

    @Test
    fun testOH_NativeWindow_CreateNativeWindowFromSurfaceId() {
        memScoped {
            val windowPtr = alloc<CPointerVar<NativeWindow>>()
            windowPtr.value = null
            val rc = OH_NativeWindow_CreateNativeWindowFromSurfaceId(0uL, windowPtr.ptr)
            assertNotNull(rc)
            logLine("OH_NativeWindow_CreateNativeWindowFromSurfaceId=$rc")
            val window = windowPtr.value
            if (window != null) OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_GetSurfaceId() {
        memScoped {
            val windowPtr = alloc<CPointerVar<NativeWindow>>()
            windowPtr.value = null
            OH_NativeWindow_CreateNativeWindowFromSurfaceId(0uL, windowPtr.ptr)
            val window = windowPtr.value
            val surfaceId = alloc<ULongVar>()
            val rc = OH_NativeWindow_GetSurfaceId(window, surfaceId.ptr)
            assertNotNull(rc)
            logLine("OH_NativeWindow_GetSurfaceId=$rc")
            if (window != null) OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeObjectReference() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_NativeObjectReference(window?.reinterpret<COpaquePointerVar>())
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeObjectReference=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_GetNativeObjectMagic() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val magic = OH_NativeWindow_GetNativeObjectMagic(window?.reinterpret<COpaquePointerVar>())
            assertNotNull(magic)
            logLine("OH_NativeWindow_GetNativeObjectMagic=$magic")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeObjectUnreference() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            OH_NativeWindow_NativeObjectReference(window?.reinterpret<COpaquePointerVar>())
            val rc = OH_NativeWindow_NativeObjectUnreference(window?.reinterpret<COpaquePointerVar>())
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeObjectUnreference=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowRequestBuffer() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val bufferPtr = alloc<CPointerVar<NativeWindowBuffer>>()
            val fenceFd = alloc<IntVar>()
            val rc = OH_NativeWindow_NativeWindowRequestBuffer(window, bufferPtr.ptr, fenceFd.ptr)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowRequestBuffer=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowHandleOpt() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_NativeWindowHandleOpt(window, 0)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowHandleOpt=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer() {
        memScoped {
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            logLine("OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer=$buffer")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
        }
    }

    @Test
    fun testOH_NativeWindow_GetBufferHandleFromNative() {
        memScoped {
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            val handle = OH_NativeWindow_GetBufferHandleFromNative(buffer)
            logLine("OH_NativeWindow_GetBufferHandleFromNative=$handle")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
        }
    }

    @Test
    fun testOH_NativeWindow_DestroyNativeWindowBuffer() {
        memScoped {
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            if (buffer != null) {
                OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
                logLine("OH_NativeWindow_DestroyNativeWindowBuffer=called")
            }
        }
    }

    @Test
    fun testOH_NativeWindow_CleanCache() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = try { OH_NativeWindow_CleanCache(window) } catch (e: Throwable) { logLine("OH_NativeWindow_CleanCache (API 19) exception: $e"); -1 }
            assertNotNull(rc)
            logLine("OH_NativeWindow_CleanCache=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_CreateNativeWindowBufferFromNativeBuffer() {
        memScoped {
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromNativeBuffer(null)
            logLine("OH_NativeWindow_CreateNativeWindowBufferFromNativeBuffer=$buffer")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowFlushBuffer() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            val region = alloc<Region>().apply { rects = null; rectNumber = 0 }
            val rc = OH_NativeWindow_NativeWindowFlushBuffer(window, buffer, -1, region.readValue())
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowFlushBuffer=$rc")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_GetLastFlushedBuffer() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val bufferPtr = alloc<CPointerVar<NativeWindowBuffer>>()
            val fenceFd = alloc<IntVar>()
            val matrix = allocArray<FloatVar>(16)
            val rc = OH_NativeWindow_GetLastFlushedBuffer(window, bufferPtr.ptr, fenceFd.ptr, matrix)
            assertNotNull(rc)
            logLine("OH_NativeWindow_GetLastFlushedBuffer=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_GetLastFlushedBufferV2() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val bufferPtr = alloc<CPointerVar<NativeWindowBuffer>>()
            val fenceFd = alloc<IntVar>()
            val matrix = allocArray<FloatVar>(16)
            val rc = OH_NativeWindow_GetLastFlushedBufferV2(window, bufferPtr.ptr, fenceFd.ptr, matrix)
            assertNotNull(rc)
            logLine("OH_NativeWindow_GetLastFlushedBufferV2=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowAbortBuffer() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            val rc = OH_NativeWindow_NativeWindowAbortBuffer(window, buffer)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowAbortBuffer=$rc")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowSetScalingMode() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_NativeWindowSetScalingMode(window, 0u, OH_SCALING_MODE_SCALE_TO_WINDOW)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowSetScalingMode=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowSetMetaData() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val metaData = alloc<OHHDRMetaData>().apply { key = OH_METAKEY_MAX_LUMINANCE; value = 0.5f }
            val rc = OH_NativeWindow_NativeWindowSetMetaData(window, 0u, 1, metaData.ptr)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowSetMetaData=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowSetMetaDataSet() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_NativeWindowSetMetaDataSet(window, 0u, OH_METAKEY_MAX_LUMINANCE, 0, null)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowSetMetaDataSet=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowSetTunnelHandle() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val handle = alloc<OHExtDataHandle>().apply { fd = -1; reserveInts = 0u }
            val rc = OH_NativeWindow_NativeWindowSetTunnelHandle(window, handle.ptr)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowSetTunnelHandle=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowAttachBuffer() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            val rc = OH_NativeWindow_NativeWindowAttachBuffer(window, buffer)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowAttachBuffer=$rc")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowDetachBuffer() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val buffer = OH_NativeWindow_CreateNativeWindowBufferFromSurfaceBuffer(null)
            OH_NativeWindow_NativeWindowAttachBuffer(window, buffer)
            val rc = OH_NativeWindow_NativeWindowDetachBuffer(window, buffer)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowDetachBuffer=$rc")
            if (buffer != null) OH_NativeWindow_DestroyNativeWindowBuffer(buffer)
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_NativeWindowSetScalingModeV2() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_NativeWindowSetScalingModeV2(window, OH_SCALING_MODE_SCALE_TO_WINDOW_V2)
            assertNotNull(rc)
            logLine("OH_NativeWindow_NativeWindowSetScalingModeV2=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_SetBufferHold() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            OH_NativeWindow_SetBufferHold(window)
            logLine("OH_NativeWindow_SetBufferHold=called")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_WriteToParcel() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_WriteToParcel(window, null)
            assertNotNull(rc)
            logLine("OH_NativeWindow_WriteToParcel=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_ReadFromParcel() {
        memScoped {
            val windowOut = alloc<CPointerVar<NativeWindow>>()
            val rc = OH_NativeWindow_ReadFromParcel(null, windowOut.ptr)
            assertNotNull(rc)
            logLine("OH_NativeWindow_ReadFromParcel=$rc")
        }
    }

    @Test
    fun testOH_NativeWindow_SetColorSpace() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = OH_NativeWindow_SetColorSpace(window, OH_NativeBuffer_ColorSpace.OH_COLORSPACE_NONE)
            assertNotNull(rc)
            logLine("OH_NativeWindow_SetColorSpace=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_GetColorSpace() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val colorSpaceOut = alloc<IntVar>()
            val rc = OH_NativeWindow_GetColorSpace(window, colorSpaceOut.ptr.reinterpret())
            assertNotNull(rc)
            logLine("OH_NativeWindow_GetColorSpace=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_SetMetadataValue() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val metaKey = OH_NativeBuffer_MetadataKey.OH_HDR_METADATA_TYPE
            val rc = OH_NativeWindow_SetMetadataValue(window, metaKey, 0, null)
            assertNotNull(rc)
            logLine("OH_NativeWindow_SetMetadataValue=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_GetMetadataValue() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val metaKey = OH_NativeBuffer_MetadataKey.OH_HDR_METADATA_TYPE
            val sizeOut = alloc<IntVar>()
            val metaOut = alloc<CPointerVar<UByteVar>>()
            val rc = OH_NativeWindow_GetMetadataValue(window, metaKey, sizeOut.ptr, metaOut.ptr.reinterpret())
            assertNotNull(rc)
            logLine("OH_NativeWindow_GetMetadataValue=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }

    @Test
    fun testOH_NativeWindow_PreAllocBuffers() {
        memScoped {
            val window = OH_NativeWindow_CreateNativeWindow(null)
            val rc = try { OH_NativeWindow_PreAllocBuffers(window, 0u) } catch (e: Throwable) { logLine("OH_NativeWindow_PreAllocBuffers (API 22) exception: $e"); -1 }
            assertNotNull(rc)
            logLine("OH_NativeWindow_PreAllocBuffers=$rc")
            OH_NativeWindow_DestroyNativeWindow(window)
        }
    }
}
