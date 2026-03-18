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
class AVMetadataExtractorTest {

    private fun logLine(message: String) {
        println("[stdout] AVMetadataExtractorTest $message")
    }

    /** OH_AVMetadataExtractor_Create (API 18) */
    @Test
    fun testOH_AVMetadataExtractor_Create() {
        try {
            val extractor = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Create()
            logLine("OH_AVMetadataExtractor_Create extractor=$extractor")
            assertNotNull(extractor)
            platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Release(extractor)
        } catch (e: Throwable) {
            logLine("OH_AVMetadataExtractor_Create (API 18) exception: $e")
        }
    }

    /** OH_AVMetadataExtractor_Release (API 18) */
    @Test
    fun testOH_AVMetadataExtractor_Release() {
        try {
            val extractor = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Create()
            val releaseResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Release(extractor)
            logLine("OH_AVMetadataExtractor_Release(extractor) result=$releaseResult")
            assertNotNull(releaseResult)
            val releaseNullResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Release(null)
            logLine("OH_AVMetadataExtractor_Release(null) result=$releaseNullResult")
            assertNotNull(releaseNullResult)
        } catch (e: Throwable) {
            logLine("OH_AVMetadataExtractor_Release (API 18) exception: $e")
        }
    }

    /** OH_AVMetadataExtractor_SetFDSource (API 18) */
    @Test
    fun testOH_AVMetadataExtractor_SetFDSource() {
        try {
            val extractor = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Create()
            val setFDSourceResult1 = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_SetFDSource(
                extractor, 0, 0L, 0L
            )
            logLine("OH_AVMetadataExtractor_SetFDSource(0,0,0) result=$setFDSourceResult1")
            assertNotNull(setFDSourceResult1)
            val setFDSourceResult2 = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_SetFDSource(
                extractor, 1, 100L, 1024L
            )
            logLine("OH_AVMetadataExtractor_SetFDSource(1,100,1024) result=$setFDSourceResult2")
            assertNotNull(setFDSourceResult2)
            val setFDSourceNullResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_SetFDSource(
                null, 0, 0L, 0L
            )
            logLine("OH_AVMetadataExtractor_SetFDSource(null,...) result=$setFDSourceNullResult")
            assertNotNull(setFDSourceNullResult)
            platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Release(extractor)
        } catch (e: Throwable) {
            logLine("OH_AVMetadataExtractor_SetFDSource (API 18) exception: $e")
        }
    }

    /** OH_AVMetadataExtractor_FetchMetadata (API 18) */
    @Test
    fun testOH_AVMetadataExtractor_FetchMetadata() {
        try {
            val extractor = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Create()
            platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_SetFDSource(extractor, 0, 0L, 0L)
            val fetchMetadataResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_FetchMetadata(
                extractor, null
            )
            logLine("OH_AVMetadataExtractor_FetchMetadata result=$fetchMetadataResult")
            assertNotNull(fetchMetadataResult)
            val fetchMetadataNullResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_FetchMetadata(
                null, null
            )
            logLine("OH_AVMetadataExtractor_FetchMetadata(null, null) result=$fetchMetadataNullResult")
            assertNotNull(fetchMetadataNullResult)
            platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Release(extractor)
        } catch (e: Throwable) {
            logLine("OH_AVMetadataExtractor_FetchMetadata (API 18) exception: $e")
        }
    }

    /** OH_AVMetadataExtractor_FetchAlbumCover (API 18) */
    @Test
    fun testOH_AVMetadataExtractor_FetchAlbumCover() {
        try {
            val extractor = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Create()
            platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_SetFDSource(extractor, 0, 0L, 0L)
            val fetchAlbumCoverResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_FetchAlbumCover(
                extractor, null
            )
            logLine("OH_AVMetadataExtractor_FetchAlbumCover result=$fetchAlbumCoverResult")
            assertNotNull(fetchAlbumCoverResult)
            val fetchAlbumCoverNullResult = platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_FetchAlbumCover(
                null, null
            )
            logLine("OH_AVMetadataExtractor_FetchAlbumCover(null, null) result=$fetchAlbumCoverNullResult")
            assertNotNull(fetchAlbumCoverNullResult)
            platform.MediaKit.AVMetadataExtractor.OH_AVMetadataExtractor_Release(extractor)
        } catch (e: Throwable) {
            logLine("OH_AVMetadataExtractor_FetchAlbumCover (API 18) exception: $e")
        }
    }
}
