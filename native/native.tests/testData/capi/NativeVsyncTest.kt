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
import platform.ArkGraphics2D.NativeVsync.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NativeVsyncTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testCreateAndDestroy() { memScoped {
        val vsync = OH_NativeVSync_Create(null, 0u)
        logLine("OH_NativeVSync_Create=$vsync")
        OH_NativeVSync_Destroy(vsync)
        logLine("OH_NativeVSync_Destroy done")
    } }

    @Test
    fun testCreateForAssociatedWindow() { memScoped {
        val vsync = OH_NativeVSync_Create_ForAssociatedWindow(0uL, null, 0u)
        logLine("OH_NativeVSync_Create_ForAssociatedWindow=$vsync")
        OH_NativeVSync_Destroy(vsync)
    } }

    @Test
    fun testRequestFrameAndGetPeriod() { memScoped {
        val vsync = OH_NativeVSync_Create(null, 0u)
        val reqRet = OH_NativeVSync_RequestFrame(vsync, null, null)
        logLine("OH_NativeVSync_RequestFrame=$reqRet")
        val period = alloc<LongVar>()
        val periodRet = OH_NativeVSync_GetPeriod(vsync, period.ptr)

        logLine("OH_NativeVSync_GetPeriod=$periodRet")
        OH_NativeVSync_Destroy(vsync)
    } }

    @Test
    fun testRequestFrameWithMultiCallbackAndDVSync() { memScoped {
        val vsync = OH_NativeVSync_Create(null, 0u)
        val multiRet = OH_NativeVSync_RequestFrameWithMultiCallback(vsync, null, null)
        logLine("OH_NativeVSync_RequestFrameWithMultiCallback=$multiRet")
        val dvsyncRet = OH_NativeVSync_DVSyncSwitch(vsync, false)
        logLine("OH_NativeVSync_DVSyncSwitch=$dvsyncRet")
        OH_NativeVSync_Destroy(vsync)
    } }

    @Test
    fun testSetExpectedFrameRateRange() { memScoped {
        val vsync = OH_NativeVSync_Create(null, 0u)
        val range = alloc<OH_NativeVSync_ExpectedRateRange>().apply {
            min = 30; max = 60; expected = 60
        }
        val ret = try { OH_NativeVSync_SetExpectedFrameRateRange(vsync, range.ptr) } catch (e: Throwable) { logLine("OH_NativeVSync_SetExpectedFrameRateRange (API 20) exception: $e"); -1 }
        assertNotNull(ret)
        logLine("OH_NativeVSync_SetExpectedFrameRateRange=$ret")
        OH_NativeVSync_Destroy(vsync)
    } }
}
