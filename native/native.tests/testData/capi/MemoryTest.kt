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
import platform.KernelEnhanceKit.Memory.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class MemoryTest {

    private fun logLine(msg: String) = println("[stdout] MemoryTest $msg")

    @Test
    fun testOH_PurgeableMemory_Create() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(0u, null, null)
            logLine("OH_PurgeableMemory_Create=$purg")
            if (purg != null) OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_Destroy() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(0u, null, null)
            val rc = OH_PurgeableMemory_Destroy(purg)
            logLine("OH_PurgeableMemory_Destroy=$rc")
        }
    }

    @Test
    fun testOH_PurgeableMemory_BeginRead() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            val rc = OH_PurgeableMemory_BeginRead(purg)
            logLine("OH_PurgeableMemory_BeginRead=$rc")
            OH_PurgeableMemory_EndRead(purg)
            OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_EndRead() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            OH_PurgeableMemory_BeginRead(purg)
            OH_PurgeableMemory_EndRead(purg)
            logLine("OH_PurgeableMemory_EndRead=called")
            OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_BeginWrite() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            val rc = OH_PurgeableMemory_BeginWrite(purg)
            logLine("OH_PurgeableMemory_BeginWrite=$rc")
            OH_PurgeableMemory_EndWrite(purg)
            OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_EndWrite() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            OH_PurgeableMemory_BeginWrite(purg)
            OH_PurgeableMemory_EndWrite(purg)
            logLine("OH_PurgeableMemory_EndWrite=called")
            OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_GetContent() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            val content = OH_PurgeableMemory_GetContent(purg)
            logLine("OH_PurgeableMemory_GetContent=$content")
            OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_ContentSize() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            val size = OH_PurgeableMemory_ContentSize(purg)
            logLine("OH_PurgeableMemory_ContentSize=$size")
            OH_PurgeableMemory_Destroy(purg)
        }
    }

    @Test
    fun testOH_PurgeableMemory_AppendModify() {
        memScoped {
            val purg = OH_PurgeableMemory_Create(64u, null, null)
            val rc = OH_PurgeableMemory_AppendModify(purg, null, null)
            logLine("OH_PurgeableMemory_AppendModify=$rc")
            OH_PurgeableMemory_Destroy(purg)
        }
    }
}
