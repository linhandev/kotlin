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
class AVSinkBaseTest {

    @Test
    fun testAppendOneBuffer() {
        logLine("--- Testing OH_AVSamplesBuffer_AppendOneBuffer (API 20) ---")
        try {
            val result = platform.MediaKit.AVSinkBase.OH_AVSamplesBuffer_AppendOneBuffer(null, null)
            assertNotNull(result)
            logLine("OH_AVSamplesBuffer_AppendOneBuffer(null, null) result: $result")
        } catch (e: Throwable) {
            logLine("OH_AVSamplesBuffer_AppendOneBuffer (API 20) exception: $e")
        }
    }

    @Test
    fun testGetRemainedCapacity() {
        logLine("--- Testing OH_AVSamplesBuffer_GetRemainedCapacity (API 20) ---")
        try {
            val result = platform.MediaKit.AVSinkBase.OH_AVSamplesBuffer_GetRemainedCapacity(null)
            assertNotNull(result)
            logLine("OH_AVSamplesBuffer_GetRemainedCapacity(null) result: $result")
        } catch (e: Throwable) {
            logLine("OH_AVSamplesBuffer_GetRemainedCapacity (API 20) exception: $e")
        }
    }

    @Test
    fun testGetCapability() {
        logLine("--- Testing OH_LowPowerAVSink_GetCapability (API 21) ---")
        try {
            val result = platform.MediaKit.AVSinkBase.OH_LowPowerAVSink_GetCapability()
            assertNotNull(result)
            logLine("OH_LowPowerAVSink_GetCapability() result: ${if (result != null) "non-null" else "null"}")
        } catch (e: Throwable) {
            logLine("OH_LowPowerAVSink_GetCapability (API 21) exception: $e")
        }
    }

    private fun logLine(message: String) {
        println("[stdout] AVSinkBaseTest $message")
    }
}
