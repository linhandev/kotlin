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
import platform.BasicServicesKit.TimeService.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class TimeServiceTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_TimeService_ErrCode() {
        assertEquals(TIMESERVICE_ERR_OK.toInt(), 0)
        assertEquals(TIMESERVICE_ERR_INTERNAL_ERROR.toInt(), 13000001)
        assertEquals(TIMESERVICE_ERR_INVALID_PARAMETER.toInt(), 13000002)
        logLine("TimeService_ErrCode passed")
    }

    @Test
    fun testGetTimeZone() { memScoped {
        val buf = ByteArray(32)
        val rc = OH_TimeService_GetTimeZone(buf.refTo(0), 32u)
        assertNotNull(rc)
        logLine("OH_TimeService_GetTimeZone=$rc")
    } }
}
