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
import platform.IPCKit.OHIPCSkeleton.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class OHIPCSkeletonTest {

    private fun logLine(msg: String) = println(msg)

    // @Test
    // fun testOH_IPCSkeleton_JoinWorkThread() {
    //     OH_IPCSkeleton_JoinWorkThread()
    //     logLine("OH_IPCSkeleton_JoinWorkThread done")
    // }

    @Test
    fun testOH_IPCSkeleton_StopWorkThread() {
        OH_IPCSkeleton_StopWorkThread()
        logLine("OH_IPCSkeleton_StopWorkThread done")
    }

    @Test
    fun testOH_IPCSkeleton_GetCallingTokenId() {
        val callingTokenId = OH_IPCSkeleton_GetCallingTokenId()
        logLine("OH_IPCSkeleton_GetCallingTokenId=$callingTokenId")
    }

    @Test
    fun testOH_IPCSkeleton_GetFirstTokenId() {
        val firstTokenId = OH_IPCSkeleton_GetFirstTokenId()
        logLine("OH_IPCSkeleton_GetFirstTokenId=$firstTokenId")
    }

    @Test
    fun testOH_IPCSkeleton_GetSelfTokenId() {
        val selfTokenId = OH_IPCSkeleton_GetSelfTokenId()
        logLine("OH_IPCSkeleton_GetSelfTokenId=$selfTokenId")
    }

    @Test
    fun testOH_IPCSkeleton_GetCallingPid() {
        val callingPid = OH_IPCSkeleton_GetCallingPid()
        logLine("OH_IPCSkeleton_GetCallingPid=$callingPid")
    }

    @Test
    fun testOH_IPCSkeleton_GetCallingUid() {
        val callingUid = OH_IPCSkeleton_GetCallingUid()
        logLine("OH_IPCSkeleton_GetCallingUid=$callingUid")
    }

    @Test
    fun testOH_IPCSkeleton_IsLocalCalling() {
        val local = OH_IPCSkeleton_IsLocalCalling()
        assertNotNull(local)
        logLine("OH_IPCSkeleton_IsLocalCalling=$local")
    }

    @Test
    fun testOH_IPCSkeleton_SetMaxWorkThreadNum() {
        val setRet = OH_IPCSkeleton_SetMaxWorkThreadNum(4)
        assertNotNull(setRet)
        logLine("OH_IPCSkeleton_SetMaxWorkThreadNum ret=$setRet")
    }

    @Test
    fun testOH_IPCSkeleton_ResetCallingIdentity() {
        memScoped {
            val identitySlot = alloc<CPointerVar<ByteVar>>()
            val lenVar = alloc<IntVar>()
            val allocator = staticCFunction { _: Int -> null as COpaquePointer? }
            val resetRet = OH_IPCSkeleton_ResetCallingIdentity(identitySlot.ptr, lenVar.ptr, allocator)
            assertNotNull(resetRet)
            logLine("OH_IPCSkeleton_ResetCallingIdentity ret=$resetRet")
        }
    }

    @Test
    fun testOH_IPCSkeleton_SetCallingIdentity() {
        val setIdentityRet = OH_IPCSkeleton_SetCallingIdentity(null)
        assertNotNull(setIdentityRet)
        logLine("OH_IPCSkeleton_SetCallingIdentity ret=$setIdentityRet")
    }

    @Test
    fun testOH_IPCSkeleton_IsHandlingTransaction() {
        val handling = OH_IPCSkeleton_IsHandlingTransaction()
        assertNotNull(handling)
        logLine("OH_IPCSkeleton_IsHandlingTransaction=$handling")
    }
}
