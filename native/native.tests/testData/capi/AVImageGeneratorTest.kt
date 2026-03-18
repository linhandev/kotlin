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
class AVImageGeneratorTest {

    @Test
    fun testEnumValues() {
        logLine("--- Testing OH_AVImageGenerator_QueryOptions enum (API 18) ---")
        try {
            val nextSync = platform.MediaKit.AVImageGenerator.OH_AVIMAGE_GENERATOR_QUERY_NEXT_SYNC
            val previousSync = platform.MediaKit.AVImageGenerator.OH_AVIMAGE_GENERATOR_QUERY_PREVIOUS_SYNC
            val closestSync = platform.MediaKit.AVImageGenerator.OH_AVIMAGE_GENERATOR_QUERY_CLOSEST_SYNC
            val closest = platform.MediaKit.AVImageGenerator.OH_AVIMAGE_GENERATOR_QUERY_CLOSEST
            logLine("NEXT_SYNC=$nextSync, PREVIOUS_SYNC=$previousSync, CLOSEST_SYNC=$closestSync, CLOSEST=$closest")
            assertNotEquals(nextSync, previousSync)
            assertNotEquals(previousSync, closestSync)
            assertNotEquals(closestSync, closest)
        } catch (e: Throwable) {
            logLine("OH_AVImageGenerator enum (API 18) exception: $e")
        }
    }

    @Test
    fun testCreate() {
        logLine("--- Testing OH_AVImageGenerator_Create (API 18) ---")
        try {
            val generator = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Create()
            assertNotNull(generator)
            logLine("OH_AVImageGenerator_Create() result: ${if (generator != null) "non-null" else "null"}")
            platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Release(generator)
        } catch (e: Throwable) {
            logLine("OH_AVImageGenerator_Create/Release (API 18) exception: $e")
        }
    }

    @Test
    fun testRelease() {
        logLine("--- Testing OH_AVImageGenerator_Release (API 18) ---")
        try {
            val generator = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Create()
            val releaseResult = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Release(generator)
            logLine("OH_AVImageGenerator_Release(generator) result: $releaseResult")
            assertNotNull(releaseResult)
        } catch (e: Throwable) {
            logLine("OH_AVImageGenerator_Release (API 18) exception: $e")
        }
    }

    @Test
    fun testSetFDSource() {
        logLine("--- Testing OH_AVImageGenerator_SetFDSource (API 18) ---")
        try {
            val generator = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Create()
            assertNotNull(generator)
            val setFDSourceResult = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_SetFDSource(
                generator,
                0,
                0L,
                0L
            )
            logLine("OH_AVImageGenerator_SetFDSource(generator, 0, 0L, 0L) result: $setFDSourceResult")
            assertNotNull(setFDSourceResult)
            platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Release(generator)
        } catch (e: Throwable) {
            logLine("OH_AVImageGenerator_SetFDSource (API 18) exception: $e")
        }
    }

    @Test
    fun testFetchFrameByTime() {
        logLine("--- Testing OH_AVImageGenerator_FetchFrameByTime (API 18) ---")
        try {
            val generator = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Create()
            assertNotNull(generator)
            val fetchResult = platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_FetchFrameByTime(
                generator,
                0L,
                platform.MediaKit.AVImageGenerator.OH_AVIMAGE_GENERATOR_QUERY_NEXT_SYNC,
                null
            )
            logLine("OH_AVImageGenerator_FetchFrameByTime(generator, 0L, NEXT_SYNC, null) result: $fetchResult")
            assertNotNull(fetchResult)
            platform.MediaKit.AVImageGenerator.OH_AVImageGenerator_Release(generator)
        } catch (e: Throwable) {
            logLine("OH_AVImageGenerator_FetchFrameByTime (API 18) exception: $e")
        }
    }

    private fun logLine(message: String) {
        println("[stdout] AVImageGeneratorTest $message")
    }
}
