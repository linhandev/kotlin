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
import platform.DriverDevelopmentKit.BaseDdk.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class BaseDdkTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_DDK_RetCode() {
        assertEquals(DDK_SUCCESS.toInt(), 0)
        assertEquals(DDK_FAILURE.toInt(), 28600001)
        assertEquals(DDK_INVALID_PARAMETER.toInt(), 28600002)
        assertEquals(DDK_INVALID_OPERATION.toInt(), 28600003)
        assertEquals(DDK_NULL_PTR.toInt(), 28600004)
        logLine("testEnum_DDK_RetCode passed")
    }

    @Test
    fun testOH_DDK_CreateAshmem() {
        memScoped {
            val nameBytes = "test_ashmem".encodeToByteArray()
            val name = allocArray<UByteVar>(nameBytes.size + 1).also { arr ->
                nameBytes.forEachIndexed { i, b -> arr[i] = b.toUByte() }
                arr[nameBytes.size] = 0u
            }
            val size = 4096u
            val ashmemPtr = alloc<CPointerVar<DDK_Ashmem>>()
            val ret = OH_DDK_CreateAshmem(name, size, ashmemPtr.ptr)
            assertNotNull(ret)
            logLine("OH_DDK_CreateAshmem ret=$ret")
            OH_DDK_DestroyAshmem(ashmemPtr.value)
        }
        logLine("OH_DDK_CreateAshmem passed")
    }

    @Test
    fun testOH_DDK_DestroyAshmem() {
        memScoped {
            val nameBytes = "test_ashmem_destroy".encodeToByteArray()
            val name = allocArray<UByteVar>(nameBytes.size + 1).also { arr ->
                nameBytes.forEachIndexed { i, b -> arr[i] = b.toUByte() }
                arr[nameBytes.size] = 0u
            }
            val ashmemPtr = alloc<CPointerVar<DDK_Ashmem>>()
            OH_DDK_CreateAshmem(name, 4096u, ashmemPtr.ptr)
            val destroyRet = OH_DDK_DestroyAshmem(ashmemPtr.value)
            assertNotNull(destroyRet)
            logLine("OH_DDK_DestroyAshmem ret=$destroyRet")
        }
        logLine("OH_DDK_DestroyAshmem passed")
    }

    @Test
    fun testOH_DDK_MapAshmem() {
        memScoped {
            val nameBytes = "test_ashmem_map".encodeToByteArray()
            val name = allocArray<UByteVar>(nameBytes.size + 1).also { arr ->
                nameBytes.forEachIndexed { i, b -> arr[i] = b.toUByte() }
                arr[nameBytes.size] = 0u
            }
            val ashmemPtr = alloc<CPointerVar<DDK_Ashmem>>()
            OH_DDK_CreateAshmem(name, 1024u, ashmemPtr.ptr)
            val mapRet = OH_DDK_MapAshmem(ashmemPtr.value, 0.toUByte())
            assertNotNull(mapRet)
            logLine("OH_DDK_MapAshmem ret=$mapRet")
            OH_DDK_UnmapAshmem(ashmemPtr.value)
            OH_DDK_DestroyAshmem(ashmemPtr.value)
        }
        logLine("OH_DDK_MapAshmem passed")
    }

    @Test
    fun testOH_DDK_UnmapAshmem() {
        memScoped {
            val nameBytes = "test_ashmem_unmap".encodeToByteArray()
            val name = allocArray<UByteVar>(nameBytes.size + 1).also { arr ->
                nameBytes.forEachIndexed { i, b -> arr[i] = b.toUByte() }
                arr[nameBytes.size] = 0u
            }
            val ashmemPtr = alloc<CPointerVar<DDK_Ashmem>>()
            OH_DDK_CreateAshmem(name, 1024u, ashmemPtr.ptr)
            OH_DDK_MapAshmem(ashmemPtr.value, 0.toUByte())
            val unmapRet = OH_DDK_UnmapAshmem(ashmemPtr.value)
            assertNotNull(unmapRet)
            logLine("OH_DDK_UnmapAshmem ret=$unmapRet")
            OH_DDK_DestroyAshmem(ashmemPtr.value)
        }
        logLine("OH_DDK_UnmapAshmem passed")
    }
}
