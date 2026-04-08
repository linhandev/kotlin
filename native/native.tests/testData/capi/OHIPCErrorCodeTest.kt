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
import platform.IPCKit.OHIPCErrorCode.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHIPCErrorCodeTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_OH_IPC_ErrorCode() {
        assertEquals(OH_IPC_SUCCESS.toInt(), 0)
        assertEquals(OH_IPC_ERROR_CODE_BASE.toInt(), 1901000)
        assertEquals(OH_IPC_CHECK_PARAM_ERROR.toInt(), 1901000)
        assertEquals(OH_IPC_PARCEL_WRITE_ERROR.toInt(), 1901001)
        assertEquals(OH_IPC_PARCEL_READ_ERROR.toInt(), 1901002)
        assertEquals(OH_IPC_MEM_ALLOCATOR_ERROR.toInt(), 1901003)
        assertEquals(OH_IPC_CODE_OUT_OF_RANGE.toInt(), 1901004)
        assertEquals(OH_IPC_DEAD_REMOTE_OBJECT.toInt(), 1901005)
        assertEquals(OH_IPC_INVALID_USER_ERROR_CODE.toInt(), 1901006)
        assertEquals(OH_IPC_INNER_ERROR.toInt(), 1901007)
        assertEquals(OH_IPC_ERROR_CODE_MAX.toInt(), 1902000)
        assertEquals(OH_IPC_USER_ERROR_CODE_MIN.toInt(), 1909000)
        assertEquals(OH_IPC_USER_ERROR_CODE_MAX.toInt(), 1909999)
        logLine("OH_IPC_ErrorCode passed")
    }
}
