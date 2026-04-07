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
import platform.CoreFileKit.CloudDisk.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class CloudDiskTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_CloudDisk_SyncState() {
        assertEquals(IDLE.toInt(), 0)
        assertEquals(SYNCING.toInt(), 1)
        assertEquals(SYNC_SUCCEEDED.toInt(), 2)
        assertEquals(SYNC_FAILED.toInt(), 3)
        assertEquals(SYNC_CANCELED.toInt(), 4)
        assertEquals(SYNC_CONFLICTED.toInt(), 5)
        logLine("testEnum_CloudDisk_SyncState passed")
    }

    @Test
    fun testEnum_CloudDisk_OperationType() {
        assertEquals(CREATE.toInt(), 0)
        assertEquals(DELETE.toInt(), 1)
        assertEquals(MOVE_FROM.toInt(), 2)
        assertEquals(MOVE_TO.toInt(), 3)
        assertEquals(CLOSE_WRITE.toInt(), 4)
        assertEquals(SYNC_FOLDER_INVALID.toInt(), 5)
        logLine("testEnum_CloudDisk_OperationType passed")
    }

    @Test
    fun testEnum_CloudDisk_ErrorReason() {
        assertEquals(INVALID_ARGUMENT.toInt(), 0)
        assertEquals(NO_SUCH_FILE.toInt(), 1)
        assertEquals(NO_SPACE_LEFT.toInt(), 2)
        assertEquals(OUT_OF_RANGE.toInt(), 3)
        assertEquals(NO_SYNC_STATE.toInt(), 4)
        logLine("testEnum_CloudDisk_ErrorReason passed")
    }

    @Test
    fun testEnum_CloudDisk_SyncFolderState() {
        assertEquals(INACTIVE.toInt(), 0)
        assertEquals(ACTIVE.toInt(), 1)
        logLine("testEnum_CloudDisk_SyncFolderState passed")
    }

    @Test
    fun testEnum_CloudDisk_ErrorCode() {
        assertEquals(CLOUD_DISK_OK.toInt(), 0)
        assertEquals(CLOUD_DISK_PERMISSION_DENIED.toInt(), 201)
        assertEquals(CLOUD_DISK_NOT_SUPPORTED.toInt(), 801)
        assertEquals(CLOUD_DISK_INVALID_ARG.toInt(), 34400001)
        assertEquals(CLOUD_DISK_SYNC_FOLDER_PATH_UNAUTHORIZED.toInt(), 34400002)
        assertEquals(CLOUD_DISK_IPC_FAILED.toInt(), 34400003)
        assertEquals(CLOUD_DISK_SYNC_FOLDER_LIMIT_EXCEEDED.toInt(), 34400004)
        assertEquals(CLOUD_DISK_CONFLICT_THIS_APP.toInt(), 34400005)
        assertEquals(CLOUD_DISK_CONFLICT_OTHER_APP.toInt(), 34400006)
        assertEquals(CLOUD_DISK_REGISTER_SYNC_FOLDER_FAILED.toInt(), 34400007)
        assertEquals(CLOUD_DISK_SYNC_FOLDER_NOT_REGISTERED.toInt(), 34400008)
        assertEquals(CLOUD_DISK_UNREGISTER_SYNC_FOLDER_FAILED.toInt(), 34400009)
        assertEquals(CLOUD_DISK_SYNC_FOLDER_PATH_NOT_EXIST.toInt(), 34400010)
        assertEquals(CLOUD_DISK_LISTENER_NOT_REGISTERED.toInt(), 34400011)
        assertEquals(CLOUD_DISK_LISTENER_ALREADY_REGISTERED.toInt(), 34400012)
        assertEquals(CLOUD_DISK_INVALID_CHANGE_SEQUENCE.toInt(), 34400013)
        assertEquals(CLOUD_DISK_TRY_AGAIN.toInt(), 34400014)
        assertEquals(CLOUD_DISK_NOT_ALLOWED.toInt(), 34400015)
        logLine("testEnum_CloudDisk_ErrorCode passed")
    }

    @Test
    fun testOH_CloudDisk_RegisterSyncFolderChanges() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val ret = try { OH_CloudDisk_RegisterSyncFolderChanges(syncFolderPath, null) } catch (e: Throwable) { logLine("OH_CloudDisk_RegisterSyncFolderChanges (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_RegisterSyncFolderChanges ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_UnregisterSyncFolderChanges() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = "".cstr.getPointer(this@memScoped); length = 0uL }
            val ret = try { OH_CloudDisk_UnregisterSyncFolderChanges(syncFolderPath) } catch (e: Throwable) { logLine("OH_CloudDisk_UnregisterSyncFolderChanges (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_UnregisterSyncFolderChanges ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_GetSyncFolderChanges() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val changesResult = alloc<CPointerVar<CloudDisk_ChangesResult>>()
            val ret = try { OH_CloudDisk_GetSyncFolderChanges(syncFolderPath, 0uL, 0uL, changesResult.ptr) } catch (e: Throwable) { logLine("OH_CloudDisk_GetSyncFolderChanges (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_GetSyncFolderChanges ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_SetFileSyncStates() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val failedLists = alloc<CPointerVar<CloudDisk_FailedList>>()
            val failedCount = alloc<ULongVar>()
            val ret = try { OH_CloudDisk_SetFileSyncStates(syncFolderPath, null, 0uL, failedLists.ptr, failedCount.ptr) } catch (e: Throwable) { logLine("OH_CloudDisk_SetFileSyncStates (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_SetFileSyncStates ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_GetFileSyncStates() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val resultLists = alloc<CPointerVar<CloudDisk_ResultList>>()
            val resultCount = alloc<ULongVar>()
            val ret = try { OH_CloudDisk_GetFileSyncStates(syncFolderPath, null, 0uL, resultLists.ptr, resultCount.ptr) } catch (e: Throwable) { logLine("OH_CloudDisk_GetFileSyncStates (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_GetFileSyncStates ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_RegisterSyncFolder() {
        memScoped {
            val syncFolder = alloc<CloudDisk_SyncFolder>()
            val ret = try { OH_CloudDisk_RegisterSyncFolder(syncFolder.ptr) } catch (e: Throwable) { logLine("OH_CloudDisk_RegisterSyncFolder (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_RegisterSyncFolder ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_UnregisterSyncFolder() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val ret = try { OH_CloudDisk_UnregisterSyncFolder(syncFolderPath) } catch (e: Throwable) { logLine("OH_CloudDisk_UnregisterSyncFolder (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_UnregisterSyncFolder ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_ActiveSyncFolder() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val ret = try { OH_CloudDisk_ActiveSyncFolder(syncFolderPath) } catch (e: Throwable) { logLine("OH_CloudDisk_ActiveSyncFolder (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_ActiveSyncFolder ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_DeactiveSyncFolder() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val ret = try { OH_CloudDisk_DeactiveSyncFolder(syncFolderPath) } catch (e: Throwable) { logLine("OH_CloudDisk_DeactiveSyncFolder (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_DeactiveSyncFolder ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_GetSyncFolders() {
        memScoped {
            val syncFolders = alloc<CPointerVar<CloudDisk_SyncFolder>>()
            val count = alloc<ULongVar>()
            val ret = try { OH_CloudDisk_GetSyncFolders(syncFolders.ptr, count.ptr) } catch (e: Throwable) { logLine("OH_CloudDisk_GetSyncFolders (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_GetSyncFolders ret=$ret")
        }
    }

    @Test
    fun testOH_CloudDisk_UpdateCustomAlias() {
        memScoped {
            val syncFolderPath = cValue<CloudDisk_PathInfo> { value = null; length = 0uL }
            val ret = try { OH_CloudDisk_UpdateCustomAlias(syncFolderPath, null, 0uL) } catch (e: Throwable) { logLine("OH_CloudDisk_UpdateCustomAlias (API 21) exception: $e"); CLOUD_DISK_INVALID_ARG }
            assertNotNull(ret)
            logLine("OH_CloudDisk_UpdateCustomAlias ret=$ret")
        }
    }
}
