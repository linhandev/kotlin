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

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
class BackgroundProcessManagerTest {

    private fun logLine(message: String) {
        println("[stdout] BackgroundProcessManagerTest $message")
    }

    @Test
    fun testEnums() {
        logLine("--- BackgroundProcessManager_ProcessPriority ---")
        val processBackground = platform.BackgroundTasksKit.BackgroundProcessManager.PROCESS_BACKGROUND
        val processInactive = platform.BackgroundTasksKit.BackgroundProcessManager.PROCESS_INACTIVE
        logLine("PROCESS_BACKGROUND=$processBackground, PROCESS_INACTIVE=$processInactive")
        assertNotEquals(processBackground, processInactive, "PROCESS_BACKGROUND != PROCESS_INACTIVE")

        logLine("--- BackgroundProcessManager_ErrorCode ---")
        val errSuccess = platform.BackgroundTasksKit.BackgroundProcessManager.ERR_BACKGROUND_PROCESS_MANAGER_SUCCESS
        val errInvalidParam = platform.BackgroundTasksKit.BackgroundProcessManager.ERR_BACKGROUND_PROCESS_MANAGER_INVALID_PARAM
        val errRemoteError = platform.BackgroundTasksKit.BackgroundProcessManager.ERR_BACKGROUND_PROCESS_MANAGER_REMOTE_ERROR
        logLine("ERR_BACKGROUND_PROCESS_MANAGER_SUCCESS=$errSuccess, ERR_BACKGROUND_PROCESS_MANAGER_INVALID_PARAM=$errInvalidParam, ERR_BACKGROUND_PROCESS_MANAGER_REMOTE_ERROR=$errRemoteError")
        assertNotEquals(errSuccess, errInvalidParam, "ERR_SUCCESS != ERR_INVALID_PARAM")
        assertNotEquals(errSuccess, errRemoteError, "ERR_SUCCESS != ERR_REMOTE_ERROR")
        assertNotEquals(errInvalidParam, errRemoteError, "ERR_INVALID_PARAM != ERR_REMOTE_ERROR")
    }

    @Test
    fun testSetProcessPriority() {
        logLine("--- OH_BackgroundProcessManager_SetProcessPriority ---")
        val priority = platform.BackgroundTasksKit.BackgroundProcessManager.PROCESS_BACKGROUND
        val result = platform.BackgroundTasksKit.BackgroundProcessManager.OH_BackgroundProcessManager_SetProcessPriority(0, priority)
        logLine("OH_BackgroundProcessMBluetooth.defanager_SetProcessPriority(0, PROCESS_BACKGROUND) result: $result")
        assertNotNull(result)
    }

    @Test
    fun testResetProcessPriority() {
        logLine("--- OH_BackgroundProcessManager_ResetProcessPriority ---")
        val result = platform.BackgroundTasksKit.BackgroundProcessManager.OH_BackgroundProcessManager_ResetProcessPriority(0)
        logLine("OH_BackgroundProcessManager_ResetProcessPriority(0) result: $result")
        assertNotNull(result)
    }
}
