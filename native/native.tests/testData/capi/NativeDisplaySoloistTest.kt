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
import platform.ArkGraphics2D.NativeDisplaySoloist.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NativeDisplaySoloistTest {

    private fun logLine(msg: String) = println(msg)

    private val noOpFrameCallback = staticCFunction { _timestamp: Long, _targetTimestamp: Long, _data: COpaquePointer? -> }

    @Test
    fun testCreateAndDestroy() { memScoped {
        val soloist = OH_DisplaySoloist_Create(true)
        assertNotNull(soloist)
        logLine("OH_DisplaySoloist_Create=$soloist")
        val ret = OH_DisplaySoloist_Destroy(soloist)
        assertNotNull(ret)
        logLine("OH_DisplaySoloist_Destroy=$ret")
    } }

    @Test
    fun testSetExpectedFrameRateRange() { memScoped {
        val soloist = OH_DisplaySoloist_Create(true)
        assertNotNull(soloist)
        logLine("OH_DisplaySoloist_Create=$soloist")
        val range = alloc<DisplaySoloist_ExpectedRateRange>().apply {
            min = 0
            max = 60
            expected = 60
        }
        val rangeRet = OH_DisplaySoloist_SetExpectedFrameRateRange(soloist, range.ptr)
        assertNotNull(rangeRet)
        logLine("OH_DisplaySoloist_SetExpectedFrameRateRange=$rangeRet")
        OH_DisplaySoloist_Destroy(soloist)
    } }

    @Test
    fun testStartStop() { memScoped {
        val soloist = OH_DisplaySoloist_Create(true)
        assertNotNull(soloist)
        logLine("OH_DisplaySoloist_Create=$soloist")
        val startRet = OH_DisplaySoloist_Start(soloist, noOpFrameCallback, null)
        assertNotNull(startRet)
        logLine("OH_DisplaySoloist_Start=$startRet")
        val stopRet = OH_DisplaySoloist_Stop(soloist)
        assertNotNull(stopRet)
        logLine("OH_DisplaySoloist_Stop=$stopRet")
        OH_DisplaySoloist_Destroy(soloist)
    } }
}
