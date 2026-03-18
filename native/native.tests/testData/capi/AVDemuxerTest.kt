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
class AVDemuxerTest {

    private fun logLine(msg: String) = println("[stdout] AVDemuxerTest $msg")

    @Test
    fun testOH_AVDemuxer_CreateWithSource() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            logLine("OH_AVDemuxer_CreateWithSource(null) demuxer=$demuxer")
            val demuxerNull = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            logLine("OH_AVDemuxer_CreateWithSource(null) demuxerNull=$demuxerNull")
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_Destroy() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val destroyResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
            logLine("OH_AVDemuxer_Destroy(demuxer) result=$destroyResult")
            assertNotNull(destroyResult)
            val destroyNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(null)
            logLine("OH_AVDemuxer_Destroy(null) result=$destroyNullResult")
            assertNotNull(destroyNullResult)
        }
    }

    @Test
    fun testOH_AVDemuxer_SelectTrackByID() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val selectTrackResult1 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SelectTrackByID(demuxer, 0u)
            logLine("OH_AVDemuxer_SelectTrackByID(0) result=$selectTrackResult1")
            assertNotNull(selectTrackResult1)
            val selectTrackResult2 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SelectTrackByID(demuxer, 1u)
            logLine("OH_AVDemuxer_SelectTrackByID(1) result=$selectTrackResult2")
            assertNotNull(selectTrackResult2)
            val selectTrackNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SelectTrackByID(null, 0u)
            logLine("OH_AVDemuxer_SelectTrackByID(null, 0) result=$selectTrackNullResult")
            assertNotNull(selectTrackNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_UnselectTrackByID() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val unselectTrackResult1 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_UnselectTrackByID(demuxer, 0u)
            logLine("OH_AVDemuxer_UnselectTrackByID(0) result=$unselectTrackResult1")
            assertNotNull(unselectTrackResult1)
            val unselectTrackResult2 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_UnselectTrackByID(demuxer, 1u)
            logLine("OH_AVDemuxer_UnselectTrackByID(1) result=$unselectTrackResult2")
            assertNotNull(unselectTrackResult2)
            val unselectTrackNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_UnselectTrackByID(null, 0u)
            logLine("OH_AVDemuxer_UnselectTrackByID(null, 0) result=$unselectTrackNullResult")
            assertNotNull(unselectTrackNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_ReadSample() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val readSampleResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_ReadSample(
                demuxer,
                0u,
                null,
                null,
            )
            logLine("OH_AVDemuxer_ReadSample result=$readSampleResult")
            assertNotNull(readSampleResult)
            val readSampleNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_ReadSample(null, 0u, null, null)
            logLine("OH_AVDemuxer_ReadSample(null, ...) result=$readSampleNullResult")
            assertNotNull(readSampleNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_ReadSampleBuffer() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val readSampleBufferResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_ReadSampleBuffer(demuxer, 0u, null)
            logLine("OH_AVDemuxer_ReadSampleBuffer(0) result=$readSampleBufferResult")
            assertNotNull(readSampleBufferResult)
            val readSampleBufferResult2 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_ReadSampleBuffer(demuxer, 1u, null)
            logLine("OH_AVDemuxer_ReadSampleBuffer(1) result=$readSampleBufferResult2")
            assertNotNull(readSampleBufferResult2)
            val readSampleBufferNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_ReadSampleBuffer(null, 0u, null)
            logLine("OH_AVDemuxer_ReadSampleBuffer(null, ...) result=$readSampleBufferNullResult")
            assertNotNull(readSampleBufferNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_SeekToTime() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val seekResult1 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SeekToTime(demuxer, 1000L, 0u)
            logLine("OH_AVDemuxer_SeekToTime(1000ms) result=$seekResult1")
            assertNotNull(seekResult1)
            val seekResult2 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SeekToTime(demuxer, 5000L, 1u)
            logLine("OH_AVDemuxer_SeekToTime(5000ms) result=$seekResult2")
            assertNotNull(seekResult2)
            val seekResult3 = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SeekToTime(demuxer, 0L, 2u)
            logLine("OH_AVDemuxer_SeekToTime(0ms) result=$seekResult3")
            assertNotNull(seekResult3)
            val seekNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SeekToTime(null, 1000L, 0u)
            logLine("OH_AVDemuxer_SeekToTime(null, ...) result=$seekNullResult")
            assertNotNull(seekNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_SetMediaKeySystemInfoCallback() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val setCallbackResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SetMediaKeySystemInfoCallback(demuxer, null)
            logLine("OH_AVDemuxer_SetMediaKeySystemInfoCallback result=$setCallbackResult")
            assertNotNull(setCallbackResult)
            val setCallbackNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SetMediaKeySystemInfoCallback(null, null)
            logLine("OH_AVDemuxer_SetMediaKeySystemInfoCallback(null, null) result=$setCallbackNullResult")
            assertNotNull(setCallbackNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_SetDemuxerMediaKeySystemInfoCallback() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val setDemuxerCallbackResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SetDemuxerMediaKeySystemInfoCallback(demuxer, null)
            logLine("OH_AVDemuxer_SetDemuxerMediaKeySystemInfoCallback result=$setDemuxerCallbackResult")
            assertNotNull(setDemuxerCallbackResult)
            val setDemuxerCallbackNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_SetDemuxerMediaKeySystemInfoCallback(null, null)
            logLine("OH_AVDemuxer_SetDemuxerMediaKeySystemInfoCallback(null, null) result=$setDemuxerCallbackNullResult")
            assertNotNull(setDemuxerCallbackNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }

    @Test
    fun testOH_AVDemuxer_GetMediaKeySystemInfo() {
        memScoped {
            val demuxer = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_CreateWithSource(null)
            val getMediaKeySystemInfoResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_GetMediaKeySystemInfo(demuxer, null)
            logLine("OH_AVDemuxer_GetMediaKeySystemInfo result=$getMediaKeySystemInfoResult")
            assertNotNull(getMediaKeySystemInfoResult)
            val getMediaKeySystemInfoNullResult = platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_GetMediaKeySystemInfo(null, null)
            logLine("OH_AVDemuxer_GetMediaKeySystemInfo(null, null) result=$getMediaKeySystemInfoNullResult")
            assertNotNull(getMediaKeySystemInfoNullResult)
            platform.AVCodecKit.AVDemuxer.OH_AVDemuxer_Destroy(demuxer)
        }
    }
}
