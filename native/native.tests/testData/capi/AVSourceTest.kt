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
class AVSourceTest {

    @Test
    fun testCreateWithURI() {
        logLine("--- Testing OH_AVSource_CreateWithURI ---")
        val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithURI(null)
        logLine("OH_AVSource_CreateWithURI(null) result: $source")
        val destroyResult = platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
        logLine("OH_AVSource_Destroy(result) $destroyResult")
    }

    @Test
    fun testCreateWithFD() {
        logLine("--- Testing OH_AVSource_CreateWithFD ---")
        val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithFD(0, 0L, 0L)
        logLine("OH_AVSource_CreateWithFD(0, 0, 0) result: $source")
        val destroyResult = platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
        logLine("OH_AVSource_Destroy(result) $destroyResult")
    }

    @Test
    fun testCreateWithDataSource() {
        logLine("--- Testing OH_AVSource_CreateWithDataSource ---")
        val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithDataSource(null)
        logLine("OH_AVSource_CreateWithDataSource(null) result: $source")
        val destroyResult = platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
        logLine("OH_AVSource_Destroy(result) $destroyResult")
    }

    @Test
    fun testCreateWithDataSourceExt() {
        logLine("--- Testing OH_AVSource_CreateWithDataSourceExt (API 20) ---")
        try {
            val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithDataSourceExt(null, null)
            logLine("OH_AVSource_CreateWithDataSourceExt(null, null) result: $source")
            val destroyResult = platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
            logLine("OH_AVSource_Destroy(result) $destroyResult")
        } catch (e: Throwable) {
            logLine("OH_AVSource_CreateWithDataSourceExt (API 20) exception: $e")
        }
    }

    @Test
    fun testDestroy() {
        logLine("--- Testing OH_AVSource_Destroy ---")
        val destroyResult = platform.AVCodecKit.AVSource.OH_AVSource_Destroy(null)
        logLine("OH_AVSource_Destroy(null) result: $destroyResult")
    }

    @Test
    fun testGetSourceFormat() {
        logLine("--- Testing OH_AVSource_GetSourceFormat ---")
        val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithFD(0, 0L, 0L)
        val format = platform.AVCodecKit.AVSource.OH_AVSource_GetSourceFormat(source)
        logLine("OH_AVSource_GetSourceFormat(source) result: $format")
        platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
    }

    @Test
    fun testGetTrackFormat() {
        logLine("--- Testing OH_AVSource_GetTrackFormat ---")
        val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithFD(0, 0L, 0L)
        val format = platform.AVCodecKit.AVSource.OH_AVSource_GetTrackFormat(source, 0u)
        logLine("OH_AVSource_GetTrackFormat(source, 0) result: $format")
        platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
    }

    @Test
    fun testGetCustomMetadataFormat() {
        logLine("--- Testing OH_AVSource_GetCustomMetadataFormat (API 18) ---")
        val source = platform.AVCodecKit.AVSource.OH_AVSource_CreateWithFD(0, 0L, 0L)
        try {
            val format = platform.AVCodecKit.AVSource.OH_AVSource_GetCustomMetadataFormat(source)
            logLine("OH_AVSource_GetCustomMetadataFormat(source) result: $format")
        } catch (e: Throwable) {
            logLine("OH_AVSource_GetCustomMetadataFormat (API 18) exception: $e")
        }
        platform.AVCodecKit.AVSource.OH_AVSource_Destroy(source)
    }

    private fun logLine(message: String) {
        println("[stdout] AVSourceTest $message")
    }
}
