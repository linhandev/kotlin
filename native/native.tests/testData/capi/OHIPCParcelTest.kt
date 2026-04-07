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
import platform.IPCKit.OHIPCParcel.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHIPCParcelTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testOH_IPCParcel_Create() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            logLine("OH_IPCParcel_Create=$parcel")
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_GetDataSize() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            val dataSize = OH_IPCParcel_GetDataSize(parcel)
            assertNotNull(dataSize)
            logLine("OH_IPCParcel_GetDataSize=$dataSize")
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_GetWritableBytes() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_GetWritableBytes(parcel))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_GetReadableBytes() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_GetReadableBytes(parcel))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_GetReadPosition() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_GetReadPosition(parcel))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_GetWritePosition() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_GetWritePosition(parcel))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_RewindReadPosition() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_RewindReadPosition(parcel, 0u))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_RewindWritePosition() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_RewindWritePosition(parcel, 0u))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteInt8_ReadInt8() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_WriteInt8(parcel, 1))
            val i8 = alloc<ByteVar>()
            assertNotNull(OH_IPCParcel_ReadInt8(parcel, i8.ptr))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteInt16_ReadInt16() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_WriteInt16(parcel, 2))
            val i16 = alloc<ShortVar>()
            assertNotNull(OH_IPCParcel_ReadInt16(parcel, i16.ptr))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteInt32_ReadInt32() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            val writeRet = OH_IPCParcel_WriteInt32(parcel, 42)
            assertNotNull(writeRet)
            logLine("OH_IPCParcel_WriteInt32 ret=$writeRet")
            val i32 = alloc<IntVar>()
            val readRet = OH_IPCParcel_ReadInt32(parcel, i32.ptr)
            assertNotNull(readRet)
            logLine("OH_IPCParcel_ReadInt32 ret=$readRet value=${i32.value}")
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteInt64_ReadInt64() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_WriteInt64(parcel, 100L))
            val i64 = alloc<LongVar>()
            assertNotNull(OH_IPCParcel_ReadInt64(parcel, i64.ptr))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteFloat_ReadFloat() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_WriteFloat(parcel, 3.14f))
            val f = alloc<FloatVar>()
            assertNotNull(OH_IPCParcel_ReadFloat(parcel, f.ptr))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteDouble_ReadDouble() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_WriteDouble(parcel, 2.718))
            val d = alloc<DoubleVar>()
            assertNotNull(OH_IPCParcel_ReadDouble(parcel, d.ptr))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteString_ReadString() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            val writeRet = OH_IPCParcel_WriteString(parcel, "hello")
            assertNotNull(writeRet)
            logLine("OH_IPCParcel_WriteString ret=$writeRet")
            val readStr = OH_IPCParcel_ReadString(parcel)
            assertNotNull(readStr)
            logLine("OH_IPCParcel_ReadString=$readStr")
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteBuffer_ReadBuffer() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            val buf = allocArray<UByteVar>(4)
            buf[0] = 1u; buf[1] = 2u; buf[2] = 3u; buf[3] = 4u
            assertNotNull(OH_IPCParcel_WriteBuffer(parcel, buf, 4))
            assertNotNull(OH_IPCParcel_ReadBuffer(parcel, 4))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteRemoteStub_ReadRemoteStub() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            OH_IPCParcel_WriteRemoteStub(parcel, null)
            OH_IPCParcel_ReadRemoteStub(parcel)
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteRemoteProxy_ReadRemoteProxy() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            OH_IPCParcel_WriteRemoteProxy(parcel, null)
            OH_IPCParcel_ReadRemoteProxy(parcel)
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteFileDescriptor_ReadFileDescriptor() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            assertNotNull(OH_IPCParcel_WriteFileDescriptor(parcel, -1))
            val fdVar = alloc<IntVar>()
            assertNotNull(OH_IPCParcel_ReadFileDescriptor(parcel, fdVar.ptr))
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_Append() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            val dataParcel = OH_IPCParcel_Create()
            assertNotNull(dataParcel)
            OH_IPCParcel_WriteInt32(dataParcel, 1)
            val appendRet = OH_IPCParcel_Append(parcel, dataParcel)
            assertNotNull(appendRet)
            logLine("OH_IPCParcel_Append ret=$appendRet")
            OH_IPCParcel_Destroy(dataParcel)
            OH_IPCParcel_Destroy(parcel)
        }
    }

    @Test
    fun testOH_IPCParcel_WriteInterfaceToken_ReadInterfaceToken() {
        memScoped {
            val tokenSlot = alloc<CPointerVar<ByteVar>>()
            val lenVar = alloc<IntVar>()
            val allocator = staticCFunction { _: Int -> null as COpaquePointer? }
            val tokenParcel = OH_IPCParcel_Create()
            assertNotNull(tokenParcel)
            assertNotNull(OH_IPCParcel_WriteInterfaceToken(tokenParcel, "x.y"))
            OH_IPCParcel_RewindReadPosition(tokenParcel, 0u)
            val readTokenRet = OH_IPCParcel_ReadInterfaceToken(tokenParcel, tokenSlot.ptr, lenVar.ptr, allocator)
            assertNotNull(readTokenRet)
            logLine("OH_IPCParcel_ReadInterfaceToken ret=$readTokenRet")
            OH_IPCParcel_Destroy(tokenParcel)
        }
    }

    @Test
    fun testOH_IPCParcel_Destroy() {
        memScoped {
            val parcel = OH_IPCParcel_Create()
            assertNotNull(parcel)
            OH_IPCParcel_Destroy(parcel)
            logLine("OH_IPCParcel_Destroy ok")
        }
    }
}
