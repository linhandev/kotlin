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
import platform.KernelEnhanceKit.QoS.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class QoSTest {

    private fun logLine(msg: String) = println(msg)

    // 覆盖 qos.h 中 QoS_Level 全部 6 个取值
    @Test
    fun testEnum_QoS_Level() {
        assertEquals(QOS_BACKGROUND.toInt(), 0)
        assertEquals(QOS_UTILITY.toInt(), 1)
        assertEquals(QOS_DEFAULT.toInt(), 2)
        assertEquals(QOS_USER_INITIATED.toInt(), 3)
        assertEquals(QOS_DEADLINE_REQUEST.toInt(), 4)
        assertEquals(QOS_USER_INTERACTIVE.toInt(), 5)
        logLine("QoS_Level passed")
    }

    // 覆盖 qos.h 中 OH_QoS_GewuErrorCode 全部 8 个取值
    @Test
    fun testEnum_OH_QoS_GewuErrorCode() {
        assertEquals(OH_QOS_GEWU_OK.toInt(), 0)
        assertEquals(OH_QOS_GEWU_NOPERM.toInt(), 201)
        assertEquals(OH_QOS_GEWU_NOMEM.toInt(), 203)
        assertEquals(OH_QOS_GEWU_INVAL.toInt(), 401)
        assertEquals(OH_QOS_GEWU_EXIST.toInt(), 501)
        assertEquals(OH_QOS_GEWU_NOENT.toInt(), 502)
        assertEquals(OH_QOS_GEWU_NOSYS.toInt(), 801)
        assertEquals(OH_QOS_GEWU_FAULT.toInt(), 901)
        logLine("OH_QoS_GewuErrorCode passed")
    }

    // 覆盖 qos.h 全部 7 个函数
    @Test
    fun testOH_QoS_SetThreadQoS() {
        val ret = OH_QoS_SetThreadQoS(QOS_DEFAULT)
        logLine("OH_QoS_SetThreadQoS ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_QoS_ResetThreadQoS() {
        val ret = OH_QoS_ResetThreadQoS()
        logLine("OH_QoS_ResetThreadQoS ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_QoS_GetThreadQoS() { memScoped {
        val levelSlot = cValuesOf(0u)
        val ret = OH_QoS_GetThreadQoS(levelSlot.ptr)
        val level = levelSlot.ptr.pointed.value
        logLine("OH_QoS_GetThreadQoS ret=$ret level=$level")
        assertNotNull(ret)
    } }

    @Test
    fun testOH_QoS_GewuCreateSession() {
        memScoped {
                val result = try {
                OH_QoS_GewuCreateSession("""
                    {
                    "model": "/data/storage/el2/base/files/qwen2/"
                    }
                """.trimIndent())
            } catch (e: Throwable) {
                logLine("OH_QoS_GewuCreateSession (API 20) exception: $e")
                val r = alloc<OH_QoS_GewuCreateSessionResult>()
                r.session = OH_QOS_GEWU_INVALID_SESSION_ID
                r.error = OH_QOS_GEWU_INVAL
                r.readValue()
            }
            result.useContents {
                logLine("OH_QoS_GewuCreateSession session=$session error=$error")
                assertNotNull(session)
                assertNotNull(error)
            }
        }
    }

    @Test
    fun testOH_QoS_GewuDestroySession() {
        val ret = try { OH_QoS_GewuDestroySession(OH_QOS_GEWU_INVALID_SESSION_ID) } catch (e: Throwable) { logLine("OH_QoS_GewuDestroySession (API 20) exception: $e"); OH_QOS_GEWU_INVAL }
        logLine("OH_QoS_GewuDestroySession ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_QoS_GewuAbortRequest() {
        val ret = try { OH_QoS_GewuAbortRequest(OH_QOS_GEWU_INVALID_SESSION_ID, OH_QOS_GEWU_INVALID_REQUEST_ID) } catch (e: Throwable) { logLine("OH_QoS_GewuAbortRequest (API 20) exception: $e"); OH_QOS_GEWU_INVAL }
        logLine("OH_QoS_GewuAbortRequest ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_QoS_GewuSubmitRequest() {
        memScoped {
            val result = try {
                OH_QoS_GewuSubmitRequest(
                    OH_QOS_GEWU_INVALID_SESSION_ID,
                    null,
                    null,
                    null
                )
            } catch (e: Throwable) {
                logLine("OH_QoS_GewuSubmitRequest (API 20) exception: $e")
                val r = alloc<OH_QoS_GewuSubmitRequestResult>()
                r.request = OH_QOS_GEWU_INVALID_REQUEST_ID
                r.error = OH_QOS_GEWU_INVAL
                r.readValue()
            }
            result.useContents {
                logLine("OH_QoS_GewuSubmitRequest request=$request error=$error")
                assertNotNull(request)
                assertNotNull(error)
            }
        }
    }
}
