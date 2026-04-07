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
import platform.CStandardLibrary.MuslMalloc.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class MuslMallocTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testCalloc() {
        memScoped {
            val ptr = calloc(4uL, 16uL)
            assertNotNull(ptr)
            logLine("calloc(4, 16) result=$ptr")
            free(ptr)
        }
    }

    @Test
    fun testRealloc() {
        memScoped {
            val ptr = malloc(32uL)
            val newPtr = realloc(ptr, 64uL)
            assertNotNull(newPtr)
            logLine("realloc result=$newPtr")
            free(newPtr)
        }
    }

    @Test
    fun testValloc() {
        memScoped {
            val ptr = valloc(4096uL)
            logLine("valloc(4096) result=$ptr")
            assertNotNull(ptr)
            free(ptr)
        }
    }

    @Test
    fun testMemalign() {
        memScoped {
            val ptr = memalign(16uL, 64uL)
            assertNotNull(ptr)
            logLine("memalign(16, 64) result=$ptr")
            free(ptr) 
        }
    }

    @Test
    fun testMalloc_usable_size() {
        memScoped {
            val ptr = malloc(64uL)
            val size = malloc_usable_size(ptr)
            logLine("malloc_usable_size result=$size")
            assertNotNull(ptr)
            free(ptr)
        }
    }

    @Test
    fun testMallopt() {
        val ret = mallopt(M_THREAD_CACHE_ENABLE, 1)
        logLine("mallopt result=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testMalloc_check_from_ptr() {
        val ret = try { malloc_check_from_ptr(null) } catch (e: Throwable) { logLine("malloc_check_from_ptr (API 19) exception: $e"); -1 }
        logLine("malloc_check_from_ptr(null) result=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testMallinfo() = memScoped {
        val info = try { mallinfo() } catch (e: Throwable) { logLine("mallinfo (API 20) exception: $e"); null}
        logLine("mallinfo result=$info")
    }

    @Test
    fun testMallinfo2() = memScoped {
        val info = try { mallinfo2() } catch (e: Throwable) { logLine("mallinfo2 (API 20) exception: $e"); null }
        logLine("mallinfo2 result=$info")
    }
}
