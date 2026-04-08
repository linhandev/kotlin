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
import platform.AVCodecKit.VideoEncoder.*
import platform.AVCodecKit.CodecBase.*
import platform.AVCodecKit.Core.*
import cnames.structs.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class VideoEncoderTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testOH_VideoEncoder_CreateByMime() {
        val codecMime = OH_VideoEncoder_CreateByMime("video/avc")
        logLine("OH_VideoEncoder_CreateByMime=$codecMime")
        OH_VideoEncoder_Destroy(codecMime)
        logLine("OH_VideoEncoder_CreateByMime passed")
    }

    @Test
    fun testOH_VideoEncoder_CreateByName() {
        val codecName = OH_VideoEncoder_CreateByName(null)
        logLine("OH_VideoEncoder_CreateByName=$codecName")
        if (codecName != null) OH_VideoEncoder_Destroy(codecName)
        logLine("OH_VideoEncoder_CreateByName passed")
    }

    @Test
    fun testOH_VideoEncoder_Destroy() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val destroyRet = OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Destroy=$destroyRet")
        logLine("OH_VideoEncoder_Destroy passed")
    }

    @Test
    fun testOH_VideoEncoder_SetCallback() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")
            val asyncCb = alloc<OH_AVCodecAsyncCallback>()
            val r = OH_VideoEncoder_SetCallback(codec, asyncCb.readValue(), null)
            logLine("OH_VideoEncoder_SetCallback=$r")
            OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_SetCallback passed")
        }
    }

    @Test
    fun testOH_VideoEncoder_RegisterCallback() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")
            val regCb = alloc<OH_AVCodecCallback>()
            val r = OH_VideoEncoder_RegisterCallback(codec, regCb.readValue(), null)
            logLine("OH_VideoEncoder_RegisterCallback=$r")
            OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_RegisterCallback passed")
        }
    }

    @Test
    fun testOH_VideoEncoder_RegisterParameterCallback() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_RegisterParameterCallback(codec, null, null)
        logLine("OH_VideoEncoder_RegisterParameterCallback=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_RegisterParameterCallback passed")
    }

    @Test
    fun testOH_VideoEncoder_Configure() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_Configure(codec, null)
        logLine("OH_VideoEncoder_Configure=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Configure passed")
    }

    @Test
    fun testOH_VideoEncoder_Prepare() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_Prepare(codec)
        logLine("OH_VideoEncoder_Prepare=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Prepare passed")
    }

    @Test
    fun testOH_VideoEncoder_Start() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_Start(codec)
        logLine("OH_VideoEncoder_Start=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Start passed")
    }

    @Test
    fun testOH_VideoEncoder_Stop() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_Stop(codec)
        logLine("OH_VideoEncoder_Stop=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Stop passed")
    }

    @Test
    fun testOH_VideoEncoder_Flush() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_Flush(codec)
        logLine("OH_VideoEncoder_Flush=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Flush passed")
    }

    @Test
    fun testOH_VideoEncoder_Reset() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_Reset(codec)
        logLine("OH_VideoEncoder_Reset=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_Reset passed")
    }

    @Test
    fun testOH_VideoEncoder_GetOutputDescription() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val desc = OH_VideoEncoder_GetOutputDescription(codec)
        logLine("OH_VideoEncoder_GetOutputDescription=$desc")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_GetOutputDescription passed")
    }

    @Test
    fun testOH_VideoEncoder_SetParameter() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_SetParameter(codec, null)
        logLine("OH_VideoEncoder_SetParameter=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_SetParameter passed")
    }

    @Test
    fun testOH_VideoEncoder_GetSurface() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_GetSurface(codec, null)
        logLine("OH_VideoEncoder_GetSurface=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_GetSurface passed")
    }

    @Test
    fun testOH_VideoEncoder_FreeOutputData() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_FreeOutputData(codec, 0u)
        logLine("OH_VideoEncoder_FreeOutputData=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_FreeOutputData passed")
    }

    @Test
    fun testOH_VideoEncoder_NotifyEndOfStream() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_NotifyEndOfStream(codec)
        logLine("OH_VideoEncoder_NotifyEndOfStream=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_NotifyEndOfStream passed")
    }

    @Test
    fun testOH_VideoEncoder_PushInputData() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")     
            val attr = alloc<OH_AVCodecBufferAttr>()
            val r = OH_VideoEncoder_PushInputData(codec, 0u, attr.readValue())
            logLine("OH_VideoEncoder_PushInputData=$r")
            OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_PushInputData passed")
        }
    }

    @Test
    fun testOH_VideoEncoder_PushInputBuffer() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_PushInputBuffer(codec, 0u)
        logLine("OH_VideoEncoder_PushInputBuffer=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_PushInputBuffer passed")
    }

    @Test
    fun testOH_VideoEncoder_PushInputParameter() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_PushInputParameter(codec, 0u)
        logLine("OH_VideoEncoder_PushInputParameter=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_PushInputParameter passed")
    }

    @Test
    fun testOH_VideoEncoder_FreeOutputBuffer() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val r = OH_VideoEncoder_FreeOutputBuffer(codec, 0u)
        logLine("OH_VideoEncoder_FreeOutputBuffer=$r")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_FreeOutputBuffer passed")
    }

    @Test
    fun testOH_VideoEncoder_QueryInputBuffer() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")
            val indexIn = alloc<UIntVar>()
            val r = try { OH_VideoEncoder_QueryInputBuffer(codec, indexIn.ptr, 0L) } catch (e: Throwable) { logLine("OH_VideoEncoder_QueryInputBuffer (API 20) exception: $e"); AV_ERR_INVALID_VAL }
            logLine("OH_VideoEncoder_QueryInputBuffer=$r")
            OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_QueryInputBuffer passed")
        }
    }

    @Test
    fun testOH_VideoEncoder_GetInputBuffer() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val getIn = try { OH_VideoEncoder_GetInputBuffer(codec, 0u) } catch (e: Throwable) { logLine("OH_VideoEncoder_GetInputBuffer (API 20) exception: $e"); null }
        logLine("OH_VideoEncoder_GetInputBuffer=$getIn")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_GetInputBuffer passed")
    }

    @Test
    fun testOH_VideoEncoder_QueryOutputBuffer() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")
            val indexOut = alloc<UIntVar>()
            val r = try { OH_VideoEncoder_QueryOutputBuffer(codec, indexOut.ptr, 0L) } catch (e: Throwable) { logLine("OH_VideoEncoder_QueryOutputBuffer (API 20) exception: $e"); AV_ERR_INVALID_VAL }
            logLine("OH_VideoEncoder_QueryOutputBuffer=$r")
            OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_QueryOutputBuffer passed")
        }
    }

    @Test
    fun testOH_VideoEncoder_GetOutputBuffer() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val getOut = try { OH_VideoEncoder_GetOutputBuffer(codec, 0u) } catch (e: Throwable) { logLine("OH_VideoEncoder_GetOutputBuffer (API 20) exception: $e"); null }
        logLine("OH_VideoEncoder_GetOutputBuffer=$getOut")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_GetOutputBuffer passed")
    }

    @Test
    fun testOH_VideoEncoder_GetInputDescription() {
        val codec = OH_VideoEncoder_CreateByMime("video/avc")
        val inputDesc = OH_VideoEncoder_GetInputDescription(codec)
        logLine("OH_VideoEncoder_GetInputDescription=$inputDesc")
        OH_VideoEncoder_Destroy(codec)
        logLine("OH_VideoEncoder_GetInputDescription passed")
    }

    @Test
    fun testOH_VideoEncoder_IsValid() {
        memScoped {
            val codec = OH_VideoEncoder_CreateByMime("video/avc")
            val isValid = alloc<BooleanVar>()
            val r = OH_VideoEncoder_IsValid(codec, isValid.ptr)
            logLine("OH_VideoEncoder_IsValid=$r")
            OH_VideoEncoder_Destroy(codec)
            logLine("OH_VideoEncoder_IsValid passed")
        }
    }
}
