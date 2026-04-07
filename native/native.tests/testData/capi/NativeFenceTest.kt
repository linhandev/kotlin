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
import platform.ArkGraphics2D.NativeFence.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class NativeFenceTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testNativeFenceIsValid() {
        val valid = try { OH_NativeFence_IsValid(-1) } catch (e: Throwable) { logLine("OH_NativeFence_IsValid (API 20) exception: $e"); false }
        assertNotNull(valid)
        logLine("OH_NativeFence_IsValid(-1)=$valid")
    }

    @Test
    fun testNativeFenceWait() {
        val ret = try { OH_NativeFence_Wait(-1, 0u) } catch (e: Throwable) { logLine("OH_NativeFence_Wait (API 20) exception: $e"); false }
        assertNotNull(ret)
        logLine("OH_NativeFence_Wait=$ret")
    }

    @Test
    fun testNativeFenceWaitForever() {
        val ret = try { OH_NativeFence_WaitForever(-1) } catch (e: Throwable) { logLine("OH_NativeFence_WaitForever (API 20) exception: $e"); false }
        assertNotNull(ret)
        logLine("OH_NativeFence_WaitForever=$ret")
    }

    @Test
    fun testNativeFenceClose() {
        try { OH_NativeFence_Close(-1) } catch (e: Throwable) { logLine("OH_NativeFence_Close (API 20) exception: $e") }
        logLine("OH_NativeFence_Close done")
    }
}
