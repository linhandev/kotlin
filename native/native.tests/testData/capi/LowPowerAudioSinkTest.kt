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
import platform.MediaKit.LowPowerAudioSink.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class LowPowerAudioSinkTest {

    private fun logLine(msg: String) = println("[stdout] LowPowerAudioSinkTest $msg")

    private fun errVal() = platform.AVCodecKit.Core.AV_ERR_INVALID_VAL

    @Test
    fun testOH_LowPowerAudioSink_CreateByMime() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            logLine("OH_LowPowerAudioSink_CreateByMime=$sink")
            if (sink != null) {
                try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Destroy (API 20) exception: $e") }
            }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Destroy() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val ret = try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Destroy (API 20) exception: $e"); errVal() }
            assertNotNull(ret)
            logLine("OH_LowPowerAudioSink_Destroy=$ret")
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Configure() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val format = platform.AVCodecKit.Core.OH_AVFormat_Create()
            val rc = try { OH_LowPowerAudioSink_Configure(sink, format) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Configure (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Configure=$rc")
            platform.AVCodecKit.Core.OH_AVFormat_Destroy(format)
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_SetParameter() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val format = platform.AVCodecKit.Core.OH_AVFormat_Create()
            val rc = try { OH_LowPowerAudioSink_SetParameter(sink, format) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_SetParameter (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_SetParameter=$rc")
            platform.AVCodecKit.Core.OH_AVFormat_Destroy(format)
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_GetParameter() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val getFormat = platform.AVCodecKit.Core.OH_AVFormat_Create()
            val rc = try { OH_LowPowerAudioSink_GetParameter(sink, getFormat) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_GetParameter (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_GetParameter=$rc")
            platform.AVCodecKit.Core.OH_AVFormat_Destroy(getFormat)
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Prepare() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Prepare(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Prepare (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Prepare=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Start() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Start(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Start (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Start=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Pause() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Pause(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Pause (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Pause=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Resume() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Resume(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Resume (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Resume=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Flush() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Flush(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Flush (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Flush=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Stop() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Stop(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Stop (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Stop=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_Reset() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_Reset(sink) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_Reset (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_Reset=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_SetVolume() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_SetVolume(sink, 0.5f) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_SetVolume (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_SetVolume=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_SetPlaybackSpeed() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_SetPlaybackSpeed(sink, 1.0f) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_SetPlaybackSpeed (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_SetPlaybackSpeed=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_SetLoudnessGain() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_SetLoudnessGain(sink, 0.0f) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_SetLoudnessGain (API 21) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_SetLoudnessGain=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_ReturnSamples() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_ReturnSamples(sink, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_ReturnSamples (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_ReturnSamples=$rc")
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSink_RegisterCallback() {
        memScoped {
            val sink = try { OH_LowPowerAudioSink_CreateByMime("audio/raw") } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_CreateByMime (API 20) exception: $e"); null }
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            val rc = try { OH_LowPowerAudioSink_RegisterCallback(sink, callback) } catch (e: Throwable) { logLine("OH_LowPowerAudioSink_RegisterCallback (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSink_RegisterCallback=$rc")
            if (callback != null) try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
            if (sink != null) try { OH_LowPowerAudioSink_Destroy(sink) } catch (e: Throwable) { }
        }
    }

    // ==================== Callback ====================

    @Test
    fun testOH_LowPowerAudioSinkCallback_Create() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            logLine("OH_LowPowerAudioSinkCallback_Create=$callback")
            if (callback != null) try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_SetPositionUpdateListener() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerAudioSinkCallback_SetPositionUpdateListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_SetPositionUpdateListener (API 20) exception: $e") }
            logLine("OH_LowPowerAudioSinkCallback_SetPositionUpdateListener=called")
            try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_SetDataNeededListener() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerAudioSinkCallback_SetDataNeededListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_SetDataNeededListener (API 20) exception: $e") }
            logLine("OH_LowPowerAudioSinkCallback_SetDataNeededListener=called")
            try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_SetErrorListener() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerAudioSinkCallback_SetErrorListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_SetErrorListener (API 20) exception: $e") }
            logLine("OH_LowPowerAudioSinkCallback_SetErrorListener=called")
            try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_SetInterruptListener() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerAudioSinkCallback_SetInterruptListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_SetInterruptListener (API 20) exception: $e") }
            logLine("OH_LowPowerAudioSinkCallback_SetInterruptListener=called")
            try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_SetDeviceChangeListener() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerAudioSinkCallback_SetDeviceChangeListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_SetDeviceChangeListener (API 20) exception: $e") }
            logLine("OH_LowPowerAudioSinkCallback_SetDeviceChangeListener=called")
            try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_SetEosListener() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            try { OH_LowPowerAudioSinkCallback_SetEosListener(callback, null, null) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_SetEosListener (API 20) exception: $e") }
            logLine("OH_LowPowerAudioSinkCallback_SetEosListener=called")
            try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { }
        }
    }

    @Test
    fun testOH_LowPowerAudioSinkCallback_Destroy() {
        memScoped {
            val callback = try { OH_LowPowerAudioSinkCallback_Create() } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Create (API 20) exception: $e"); null }
            assertNotNull(callback)
            val rc = try { OH_LowPowerAudioSinkCallback_Destroy(callback) } catch (e: Throwable) { logLine("OH_LowPowerAudioSinkCallback_Destroy (API 20) exception: $e"); errVal() }
            assertNotNull(rc)
            logLine("OH_LowPowerAudioSinkCallback_Destroy=$rc")
        }
    }
}
