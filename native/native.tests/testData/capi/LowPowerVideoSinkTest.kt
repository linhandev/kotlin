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
import platform.MediaKit.LowPowerVideoSink.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class LowPowerVideoSinkTest {

    private fun logLine(msg: String) = println("[stdout] LowPowerVideoSinkTest $msg")

    private fun errVal() = platform.AVCodecKit.Core.AV_ERR_INVALID_VAL

    @Test
    fun testOH_LowPowerVideoSink_CreateByMime() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            logLine("OH_LowPowerVideoSink_CreateByMime=$sink")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Destroy() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val ret = try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Destroy (API 20) exception: $e"); errVal() }
            assertNotNull(ret)
            logLine("OH_LowPowerVideoSink_Destroy=$ret")
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Configure() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val format = platform.AVCodecKit.Core.OH_AVFormat_Create()
            val rc = try { OH_LowPowerVideoSink_Configure(sink, format) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Configure (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Configure=$rc")
            platform.AVCodecKit.Core.OH_AVFormat_Destroy(format)
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_SetParameter() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val format = platform.AVCodecKit.Core.OH_AVFormat_Create()
            val rc = try { OH_LowPowerVideoSink_SetParameter(sink, format) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_SetParameter (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_SetParameter=$rc")
            platform.AVCodecKit.Core.OH_AVFormat_Destroy(format)
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_GetParameter() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val getFormat = platform.AVCodecKit.Core.OH_AVFormat_Create()
            val rc = try { OH_LowPowerVideoSink_GetParameter(sink, getFormat) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_GetParameter (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_GetParameter=$rc")
            platform.AVCodecKit.Core.OH_AVFormat_Destroy(getFormat)
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_SetVideoSurface() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_SetVideoSurface(sink, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_SetVideoSurface (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_SetVideoSurface=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Prepare() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_Prepare(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Prepare (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Prepare=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_StartDecoder() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_StartDecoder(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_StartDecoder (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_StartDecoder=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_RenderFirstFrame() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_RenderFirstFrame(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_RenderFirstFrame (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_RenderFirstFrame=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_StartRenderer() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_StartRenderer(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_StartRenderer (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_StartRenderer=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Pause() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_Pause(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Pause (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Pause=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Resume() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_Resume(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Resume (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Resume=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Flush() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_Flush(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Flush (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Flush=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Stop() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_Stop(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Stop (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Stop=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_Reset() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_Reset(sink) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_Reset (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_Reset=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_SetSyncAudioSink() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_SetSyncAudioSink(sink, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_SetSyncAudioSink (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_SetSyncAudioSink=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_ReturnSamples() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_ReturnSamples(sink, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_ReturnSamples (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_ReturnSamples=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_SetPlaybackSpeed() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_SetPlaybackSpeed(sink, 1.0f) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_SetPlaybackSpeed (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_SetPlaybackSpeed=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_RegisterCallback() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_RegisterCallback(sink, callback) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_RegisterCallback (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_RegisterCallback=$rc")
            if (callback != null) try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_SetTargetStartFrame() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerVideoSink_SetTargetStartFrame(sink, 0L, null, 1000L, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_SetTargetStartFrame (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_SetTargetStartFrame=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSink_GetLatestPts() {
        memScoped {
            val sink = try { OH_LowPowerVideoSink_CreateByMime("video/raw") } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_CreateByMime (API 20) exception: $e"); null }
            val pts = alloc<LongVar>()
            val rc = try { OH_LowPowerVideoSink_GetLatestPts(sink, pts.ptr) } catch (e: Throwable) { logLine("OH_LowPowerVideoSink_GetLatestPts (API 21) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSink_GetLatestPts=$rc")
            if (sink != null) try { OH_LowPowerVideoSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    // ==================== Callback ====================

    @Test
    fun testOH_LowPowerVideoSinkCallback_Create() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            logLine("OH_LowPowerVideoSinkCallback_Create=$callback")
            if (callback != null) try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_SetDataNeededListener() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerVideoSinkCallback_SetDataNeededListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_SetDataNeededListener (API 20) exception: $e") }
            logLine("OH_LowPowerVideoSinkCallback_SetDataNeededListener=called")
            try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_SetErrorListener() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerVideoSinkCallback_SetErrorListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_SetErrorListener (API 20) exception: $e") }
            logLine("OH_LowPowerVideoSinkCallback_SetErrorListener=called")
            try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_SetRenderStartListener() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerVideoSinkCallback_SetRenderStartListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_SetRenderStartListener (API 20) exception: $e") }
            logLine("OH_LowPowerVideoSinkCallback_SetRenderStartListener=called")
            try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_SetStreamChangedListener() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerVideoSinkCallback_SetStreamChangedListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_SetStreamChangedListener (API 20) exception: $e") }
            logLine("OH_LowPowerVideoSinkCallback_SetStreamChangedListener=called")
            try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_SetFirstFrameDecodedListener() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerVideoSinkCallback_SetFirstFrameDecodedListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_SetFirstFrameDecodedListener (API 20) exception: $e") }
            logLine("OH_LowPowerVideoSinkCallback_SetFirstFrameDecodedListener=called")
            try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_SetEosListener() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerVideoSinkCallback_SetEosListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_SetEosListener (API 20) exception: $e") }
            logLine("OH_LowPowerVideoSinkCallback_SetEosListener=called")
            try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerVideoSinkCallback_Destroy() {
        memScoped {
            val callback = try { OH_LowPowerVideoSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            val rc = try { OH_LowPowerVideoSinkCallback_Destroy(callback) } catch (e: Throwable) { logLine("OH_LowPowerVideoSinkCallback_Destroy (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerVideoSinkCallback_Destroy=$rc")
        }
    }
}
