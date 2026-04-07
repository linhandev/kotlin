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
import platform.LocalizationKit.RawFile.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class RawFileTest {

    private fun logLine(msg: String) = println("[stdout] RawFileTest $msg")

    @Test
    fun testOH_ResourceManager_InitNativeResourceManager() {
        val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
        logLine("OH_ResourceManager_InitNativeResourceManager(null,null)=$mgr")
        if (mgr != null) OH_ResourceManager_ReleaseNativeResourceManager(mgr)
    }

    @Test
    fun testOH_ResourceManager_ReleaseNativeResourceManager() {
        val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
        OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        logLine("OH_ResourceManager_ReleaseNativeResourceManager done")
    }

    @Test
    fun testOH_ResourceManager_OpenRawDir() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawDir = OH_ResourceManager_OpenRawDir(mgr, "")
            logLine("OH_ResourceManager_OpenRawDir=$rawDir")
            if (rawDir != null) OH_ResourceManager_CloseRawDir(rawDir)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileName() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawDir = OH_ResourceManager_OpenRawDir(mgr, "")
            val name = OH_ResourceManager_GetRawFileName(rawDir, 0)
            logLine("OH_ResourceManager_GetRawFileName=$name")
            if (rawDir != null) OH_ResourceManager_CloseRawDir(rawDir)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileCount() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawDir = OH_ResourceManager_OpenRawDir(mgr, "")
            val count = OH_ResourceManager_GetRawFileCount(rawDir)
            logLine("OH_ResourceManager_GetRawFileCount=$count")
            if (rawDir != null) OH_ResourceManager_CloseRawDir(rawDir)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_CloseRawDir() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawDir = OH_ResourceManager_OpenRawDir(mgr, "")
            if (rawDir != null) OH_ResourceManager_CloseRawDir(rawDir)
            logLine("OH_ResourceManager_CloseRawDir done")
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_OpenRawFile() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            logLine("OH_ResourceManager_OpenRawFile=$rawFile")
            if (rawFile != null) OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_OpenRawFile64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            logLine("OH_ResourceManager_OpenRawFile64=$rawFile64")
            if (rawFile64 != null) OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_IsRawDir() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val isDir = OH_ResourceManager_IsRawDir(mgr, "")
            logLine("OH_ResourceManager_IsRawDir=$isDir")
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_CloseRawFile() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            if (rawFile != null) OH_ResourceManager_CloseRawFile(rawFile)
            logLine("OH_ResourceManager_CloseRawFile done")
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_CloseRawFile64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            if (rawFile64 != null) OH_ResourceManager_CloseRawFile64(rawFile64)
            logLine("OH_ResourceManager_CloseRawFile64 done")
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_ReadRawFile() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val buf = allocArray<ByteVar>(2)
            val readRet = OH_ResourceManager_ReadRawFile(rawFile, buf, 2u)
            logLine("OH_ResourceManager_ReadRawFile=$readRet")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_SeekRawFile() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)  
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val seekRet = OH_ResourceManager_SeekRawFile(rawFile, 0L, 0)
            logLine("OH_ResourceManager_SeekRawFile=$seekRet")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileSize() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val size = OH_ResourceManager_GetRawFileSize(rawFile)
            logLine("OH_ResourceManager_GetRawFileSize=$size")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileRemainingLength() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val remaining = OH_ResourceManager_GetRawFileRemainingLength(rawFile)
            logLine("OH_ResourceManager_GetRawFileRemainingLength=$remaining")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileOffset() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val offset = OH_ResourceManager_GetRawFileOffset(rawFile)
            logLine("OH_ResourceManager_GetRawFileOffset=$offset")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileDescriptor() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val descriptor = alloc<RawFileDescriptor>()
            val ret = OH_ResourceManager_GetRawFileDescriptor(rawFile, descriptor.ptr)
            logLine("OH_ResourceManager_GetRawFileDescriptor=$ret")
            OH_ResourceManager_ReleaseRawFileDescriptor(descriptor.ptr)
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_ReleaseRawFileDescriptor() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val descriptor = alloc<RawFileDescriptor>()
            OH_ResourceManager_GetRawFileDescriptor(rawFile, descriptor.ptr)
            val ret = OH_ResourceManager_ReleaseRawFileDescriptor(descriptor.ptr)
            logLine("OH_ResourceManager_ReleaseRawFileDescriptor=$ret")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileDescriptorData() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val descriptor = alloc<RawFileDescriptor>()
            val ret = OH_ResourceManager_GetRawFileDescriptorData(rawFile, descriptor.ptr)
            logLine("OH_ResourceManager_GetRawFileDescriptorData=$ret")
            OH_ResourceManager_ReleaseRawFileDescriptorData(descriptor.ptr)
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_ReleaseRawFileDescriptorData() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile = OH_ResourceManager_OpenRawFile(mgr, "dummy.txt")
            val descriptor = alloc<RawFileDescriptor>()
            OH_ResourceManager_GetRawFileDescriptorData(rawFile, descriptor.ptr)
            val ret = OH_ResourceManager_ReleaseRawFileDescriptorData(descriptor.ptr)
            logLine("OH_ResourceManager_ReleaseRawFileDescriptorData=$ret")
            OH_ResourceManager_CloseRawFile(rawFile)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_ReadRawFile64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val buf = allocArray<ByteVar>(2)
            val readRet = OH_ResourceManager_ReadRawFile64(rawFile64, buf, 2L)
            logLine("OH_ResourceManager_ReadRawFile64=$readRet")
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_SeekRawFile64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val seekRet = OH_ResourceManager_SeekRawFile64(rawFile64, 0L, 0)
            logLine("OH_ResourceManager_SeekRawFile64=$seekRet")
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileSize64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val size = OH_ResourceManager_GetRawFileSize64(rawFile64)
            logLine("OH_ResourceManager_GetRawFileSize64=$size")
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileRemainingLength64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val remaining = OH_ResourceManager_GetRawFileRemainingLength64(rawFile64)
            logLine("OH_ResourceManager_GetRawFileRemainingLength64=$remaining")
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileOffset64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val offset = OH_ResourceManager_GetRawFileOffset64(rawFile64)
            logLine("OH_ResourceManager_GetRawFileOffset64=$offset")
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_GetRawFileDescriptor64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val descriptor64 = alloc<RawFileDescriptor64>()
            val ret = OH_ResourceManager_GetRawFileDescriptor64(rawFile64, descriptor64.ptr)
            logLine("OH_ResourceManager_GetRawFileDescriptor64=$ret")
            OH_ResourceManager_ReleaseRawFileDescriptor64(descriptor64.ptr)
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }

    @Test
    fun testOH_ResourceManager_ReleaseRawFileDescriptor64() {
        memScoped {
            val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
            val rawFile64 = OH_ResourceManager_OpenRawFile64(mgr, "dummy.txt")
            val descriptor64 = alloc<RawFileDescriptor64>()
            OH_ResourceManager_GetRawFileDescriptor64(rawFile64, descriptor64.ptr)
            val ret = OH_ResourceManager_ReleaseRawFileDescriptor64(descriptor64.ptr)
            logLine("OH_ResourceManager_ReleaseRawFileDescriptor64=$ret")
            OH_ResourceManager_CloseRawFile64(rawFile64)
            OH_ResourceManager_ReleaseNativeResourceManager(mgr)
        }
    }
}
