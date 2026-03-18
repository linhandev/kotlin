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

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class AVMuxerTest {

    @Test
    fun testCreateAndDestroy() {
        logLine("--- Testing OH_AVMuxer_Create / OH_AVMuxer_Destroy ---")
        val fd = 1
        val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(fd, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
        logLine("OH_AVMuxer_Create(fd=$fd, MPEG_4) result: ${if (muxer != null) "non-null" else "null"}")
        val destroyResult = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
        logLine("OH_AVMuxer_Destroy(muxer) result: $destroyResult")
    }

    @Test
    fun testSetRotation() {
        logLine("--- Testing OH_AVMuxer_SetRotation ---")
        val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
        val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_SetRotation(muxer, 90)
        logLine("OH_AVMuxer_SetRotation(muxer, 90) result: $result")
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
    }

    @Test
    fun testSetFormat() {
        logLine("--- Testing OH_AVMuxer_SetFormat ---")
        val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
        val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_SetFormat(muxer, null)
        logLine("OH_AVMuxer_SetFormat(muxer, null) result: $result")
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
    }

    @Test
    fun testAddTrack() {
        memScoped {
            logLine("--- Testing OH_AVMuxer_AddTrack ---")
            val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
            val trackIndex = alloc<IntVar>()
            val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_AddTrack(muxer, trackIndex.ptr, null)
            logLine("OH_AVMuxer_AddTrack(muxer, ptr, null) result: $result, trackIndex: ${trackIndex.value}")
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
        }
    }

    @Test
    fun testStart() {
        logLine("--- Testing OH_AVMuxer_Start ---")
        val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
        memScoped {
            val trackIndex = alloc<IntVar>()
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_AddTrack(muxer, trackIndex.ptr, null)
        }
        val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Start(muxer)
        logLine("OH_AVMuxer_Start(muxer) result: $result")
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
    }

    @Test
    fun testWriteSample() {
        memScoped {
            logLine("--- Testing OH_AVMuxer_WriteSample ---")
            val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
            val trackIndex = alloc<IntVar>()
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_AddTrack(muxer, trackIndex.ptr, null)
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_Start(muxer)
            val sample = platform.AVCodecKit.Core.OH_AVMemory_Create(1024)
            val bufferAttr = alloc<platform.AVCodecKit.Core.OH_AVCodecBufferAttr>().apply {
                pts = 0L
                size = 0
                offset = 0
                flags = 0u
            }
            val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_WriteSample(
                muxer,
                0u,
                sample,
                bufferAttr.readValue()
            )
            logLine("OH_AVMuxer_WriteSample(muxer, 0, sample, attr) result: $result")
            platform.AVCodecKit.Core.OH_AVMemory_Destroy(sample)
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_Stop(muxer)
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
        }
    }

    @Test
    fun testWriteSampleBuffer() {
        logLine("--- Testing OH_AVMuxer_WriteSampleBuffer ---")
        val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
        memScoped {
            val trackIndex = alloc<IntVar>()
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_AddTrack(muxer, trackIndex.ptr, null)
        }
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Start(muxer)
        val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_WriteSampleBuffer(muxer, 0u, null)
        logLine("OH_AVMuxer_WriteSampleBuffer(muxer, 0, null) result: $result")
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Stop(muxer)
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
    }

    @Test
    fun testStop() {
        logLine("--- Testing OH_AVMuxer_Stop ---")
        val muxer = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Create(1, platform.AVCodecKit.CodecBase.AV_OUTPUT_FORMAT_MPEG_4)
        memScoped {
            val trackIndex = alloc<IntVar>()
            platform.AVCodecKit.AVMuxer.OH_AVMuxer_AddTrack(muxer, trackIndex.ptr, null)
        }
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Start(muxer)
        val result = platform.AVCodecKit.AVMuxer.OH_AVMuxer_Stop(muxer)
        logLine("OH_AVMuxer_Stop(muxer) result: $result")
        platform.AVCodecKit.AVMuxer.OH_AVMuxer_Destroy(muxer)
    }

    private fun logLine(message: String) {
        println("[stdout] AVMuxerTest $message")
    }
}
