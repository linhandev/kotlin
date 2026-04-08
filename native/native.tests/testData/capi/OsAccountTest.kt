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
import platform.BasicServicesKit.OsAccount.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OsAccountTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OsAccount_ErrCode() {
        assertEquals(OS_ACCOUNT_ERR_OK.toInt(), 0)
        assertEquals(OS_ACCOUNT_ERR_INTERNAL_ERROR.toInt(), 12300001)
        assertEquals(OS_ACCOUNT_ERR_INVALID_PARAMETER.toInt(), 12300002)
        logLine("OsAccount_ErrCode passed")
    }

    @Test
    fun testOH_OsAccount_GetName() { memScoped {
        val buf = ByteArray(256)
        val ret = OH_OsAccount_GetName(buf.refTo(0), 256u)
        assertNotNull(ret)
        logLine("OH_OsAccount_GetName=$ret")
    } }
}
