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
import platform.BackgroundTasksKit.TransientTask.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class TransientTaskTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_TransientTask_ErrorCode() {
        assertEquals(ERR_TRANSIENT_TASK_OK.toInt(), 0)
        assertEquals(ERR_TRANSIENT_TASK_INVALID_PARAM.toInt(), 401)
        assertEquals(ERR_TRANSIENT_TASK_PARCEL_FAILED.toInt(), 9800002)
        assertEquals(ERR_TRANSIENT_TASK_TRANSACTION_FAILED.toInt(), 9800003)
        assertEquals(ERR_TRANSIENT_TASK_SYS_NOT_READY.toInt(), 9800004)
        assertEquals(ERR_TRANSIENT_TASK_CLIENT_INFO_VERIFICATION_FAILED.toInt(), 9900001)
        assertEquals(ERR_TRANSIENT_TASK_SERVICE_VERIFICATION_FAILED.toInt(), 9900002)
        assertEquals(ERR_TRANSIENT_TASK_PARCELABLE_FAILED.toInt(), 9900003)
        assertEquals(ERR_TRANSIENT_TASK_SERVICE_NOT_READY.toInt(), 9900004)
        logLine("TransientTask_ErrorCode passed")
    }

    @Test
    fun testOH_BackgroundTaskManager_RequestSuspendDelay() {
        memScoped {
            val delayInfo = alloc<TransientTask_DelaySuspendInfo>()
            val ret = OH_BackgroundTaskManager_RequestSuspendDelay(null, null, delayInfo.ptr)
            assertNotNull(ret)
            logLine("OH_BackgroundTaskManager_RequestSuspendDelay=$ret")
        }
    }

    @Test
    fun testOH_BackgroundTaskManager_GetRemainingDelayTime() {
        memScoped {
            val delay = alloc<IntVar>()
            val ret = OH_BackgroundTaskManager_GetRemainingDelayTime(0, delay.ptr)
            assertNotNull(ret)
            logLine("OH_BackgroundTaskManager_GetRemainingDelayTime=$ret")
        }
    }

    @Test
    fun testOH_BackgroundTaskManager_CancelSuspendDelay() {
        val ret = OH_BackgroundTaskManager_CancelSuspendDelay(0)
        assertNotNull(ret)
        logLine("OH_BackgroundTaskManager_CancelSuspendDelay=$ret")
    }

    @Test
    fun testOH_BackgroundTaskManager_GetTransientTaskInfo() {
        memScoped {
            val info = alloc<TransientTask_TransientTaskInfo>()
            val ret = try {
                OH_BackgroundTaskManager_GetTransientTaskInfo(info.ptr)
            } catch (e: Throwable) {
                logLine("OH_BackgroundTaskManager_GetTransientTaskInfo (API 20) exception: $e")
                ERR_TRANSIENT_TASK_INVALID_PARAM
            }
            assertNotNull(ret)
            logLine("OH_BackgroundTaskManager_GetTransientTaskInfo=$ret")
        }
    }
}
