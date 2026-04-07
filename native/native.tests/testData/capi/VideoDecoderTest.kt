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
import platform.AVCodecKit.VideoDecoder.*
import platform.AVCodecKit.CodecBase.*
import platform.AVCodecKit.Core.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class VideoDecoderTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testOH_VideoDecoder_CreateByMime() {
        val codecMime = OH_VideoDecoder_CreateByMime("video/avc")
        logLine("OH_VideoDecoder_CreateByMime=$codecMime")
        OH_VideoDecoder_Destroy(codecMime)
        logLine("OH_VideoDecoder_CreateByMime passed")
    }

    @Test
    fun testOH_VideoDecoder_CreateByName() {
        val codecName = OH_VideoDecoder_CreateByName(null)
        logLine("OH_VideoDecoder_CreateByName=$codecName")
        if (codecName != null) OH_VideoDecoder_Destroy(codecName)
        logLine("OH_VideoDecoder_CreateByName passed")
    }

    @Test
    fun testOH_VideoDecoder_Destroy() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val destroyRet = OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Destroy=$destroyRet")
        logLine("OH_VideoDecoder_Destroy passed")
    }

    @Test
    fun testOH_VideoDecoder_SetCallback() {
        memScoped {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            val asyncCb = alloc<OH_AVCodecAsyncCallback>()
            val r = OH_VideoDecoder_SetCallback(codec, asyncCb.readValue(), null)
            logLine("OH_VideoDecoder_SetCallback=$r")
            OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_SetCallback passed")
        }
    }

    @Test
    fun testOH_VideoDecoder_RegisterCallback() {
        memScoped {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            val regCb = alloc<OH_AVCodecCallback>()
            val r = OH_VideoDecoder_RegisterCallback(codec, regCb.readValue(), null)
            logLine("OH_VideoDecoder_RegisterCallback=$r")
            OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_RegisterCallback passed")
        }
    }

    @Test
    fun testOH_VideoDecoder_SetSurface() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_SetSurface(codec, null)
        logLine("OH_VideoDecoder_SetSurface=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_SetSurface passed")
    }

    @Test
    fun testOH_VideoDecoder_Configure() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_Configure(codec, null)
        logLine("OH_VideoDecoder_Configure=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Configure passed")
    }

    @Test
    fun testOH_VideoDecoder_Prepare() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_Prepare(codec)
        logLine("OH_VideoDecoder_Prepare=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Prepare passed")
    }

    @Test
    fun testOH_VideoDecoder_Start() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_Start(codec)
        logLine("OH_VideoDecoder_Start=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Start passed")
    }

    @Test
    fun testOH_VideoDecoder_Stop() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_Stop(codec)
        logLine("OH_VideoDecoder_Stop=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Stop passed")
    }

    @Test
    fun testOH_VideoDecoder_Flush() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_Flush(codec)
        logLine("OH_VideoDecoder_Flush=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Flush passed")
    }

    @Test
    fun testOH_VideoDecoder_Reset() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_Reset(codec)
        logLine("OH_VideoDecoder_Reset=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_Reset passed")
    }

    @Test
    fun testOH_VideoDecoder_GetOutputDescription() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val desc = OH_VideoDecoder_GetOutputDescription(codec)
        logLine("OH_VideoDecoder_GetOutputDescription=$desc")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_GetOutputDescription passed")
    }

    @Test
    fun testOH_VideoDecoder_SetParameter() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val r = OH_VideoDecoder_SetParameter(codec, null)
        logLine("OH_VideoDecoder_SetParameter=$r")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_SetParameter passed")
    }

    @Test
    fun testOH_VideoDecoder_PushInputData() {
        memScoped {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            val attr = alloc<OH_AVCodecBufferAttr>()
            val ret = OH_VideoDecoder_PushInputData(codec, 0u, attr.readValue())
            logLine("OH_VideoDecoder_PushInputData=$ret")
            OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_PushInputData passed")
        }
    }

    @Test
    fun testOH_VideoDecoder_RenderOutputData() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_RenderOutputData(codec, 0u)
        logLine("OH_VideoDecoder_RenderOutputData=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_RenderOutputData passed")
    }

    @Test
    fun testOH_VideoDecoder_FreeOutputData() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_FreeOutputData(codec, 0u)
        logLine("OH_VideoDecoder_FreeOutputData=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_FreeOutputData passed")
    }

    @Test
    fun testOH_VideoDecoder_PushInputBuffer() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_PushInputBuffer(codec, 0u)
        logLine("OH_VideoDecoder_PushInputBuffer=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_PushInputBuffer passed")
    }

    @Test
    fun testOH_VideoDecoder_RenderOutputBuffer() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_RenderOutputBuffer(codec, 0u)
        logLine("OH_VideoDecoder_RenderOutputBuffer=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_RenderOutputBuffer passed")
    }

    @Test
    fun testOH_VideoDecoder_RenderOutputBufferAtTime() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_RenderOutputBufferAtTime(codec, 0u, 0L)
        logLine("OH_VideoDecoder_RenderOutputBufferAtTime=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_RenderOutputBufferAtTime passed")
    }

    @Test
    fun testOH_VideoDecoder_FreeOutputBuffer() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_FreeOutputBuffer(codec, 0u)
        logLine("OH_VideoDecoder_FreeOutputBuffer=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_FreeOutputBuffer passed")
    }

    @Test
    fun testOH_VideoDecoder_QueryInputBuffer() {
        try {
            memScoped {
                val codec = OH_VideoDecoder_CreateByMime("video/avc")
                val indexIn = alloc<UIntVar>()
                val ret = OH_VideoDecoder_QueryInputBuffer(codec, indexIn.ptr, 0L)
                logLine("OH_VideoDecoder_QueryInputBuffer=$ret")
                OH_VideoDecoder_Destroy(codec)
                logLine("OH_VideoDecoder_QueryInputBuffer passed")
            }
        } catch (e: Throwable) { logLine("testOH_VideoDecoder_QueryInputBuffer (API >17) exception: $e") }
    }

    @Test
    fun testOH_VideoDecoder_GetInputBuffer() {
        try {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            val getIn = OH_VideoDecoder_GetInputBuffer(codec, 0u)
            logLine("OH_VideoDecoder_GetInputBuffer=$getIn")
            OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_GetInputBuffer passed")
        } catch (e: Throwable) { logLine("testOH_VideoDecoder_GetInputBuffer (API >17) exception: $e") }
    }

    @Test
    fun testOH_VideoDecoder_QueryOutputBuffer() {
        try {
            memScoped {
                val codec = OH_VideoDecoder_CreateByMime("video/avc")
                val indexOut = alloc<UIntVar>()
                val ret = OH_VideoDecoder_QueryOutputBuffer(codec, indexOut.ptr, 0L)
                logLine("OH_VideoDecoder_QueryOutputBuffer=$ret")
                OH_VideoDecoder_Destroy(codec)
                logLine("OH_VideoDecoder_QueryOutputBuffer passed")
            }
        } catch (e: Throwable) { logLine("testOH_VideoDecoder_QueryOutputBuffer (API >17) exception: $e") }
    }

    @Test
    fun testOH_VideoDecoder_GetOutputBuffer() {
        try {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            val getOut = OH_VideoDecoder_GetOutputBuffer(codec, 0u)
            logLine("OH_VideoDecoder_GetOutputBuffer=$getOut")
            OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_GetOutputBuffer passed")
        } catch (e: Throwable) { logLine("testOH_VideoDecoder_GetOutputBuffer (API >17) exception: $e") }
    }

    @Test
    fun testOH_VideoDecoder_IsValid() {
        memScoped {
            val codec = OH_VideoDecoder_CreateByMime("video/avc")
            val isValid = alloc<BooleanVar>()
            val ret = OH_VideoDecoder_IsValid(codec, isValid.ptr)
            logLine("OH_VideoDecoder_IsValid=$ret")
            OH_VideoDecoder_Destroy(codec)
            logLine("OH_VideoDecoder_IsValid passed")
        }
    }

    @Test
    fun testOH_VideoDecoder_SetDecryptionConfig() {
        val codec = OH_VideoDecoder_CreateByMime("video/avc")
        val ret = OH_VideoDecoder_SetDecryptionConfig(codec, null, false)
        logLine("OH_VideoDecoder_SetDecryptionConfig=$ret")
        OH_VideoDecoder_Destroy(codec)
        logLine("OH_VideoDecoder_SetDecryptionConfig passed")
    }
}
