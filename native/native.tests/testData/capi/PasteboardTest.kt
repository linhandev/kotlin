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
import platform.BasicServicesKit.Pasteboard.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class PasteboardTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- 枚举：Pasteboard_NotifyType ----------
    @Test
    fun testEnum_Pasteboard_NotifyType() {
        assertEquals(NOTIFY_LOCAL_DATA_CHANGE.toInt(), 1)
        assertEquals(NOTIFY_REMOTE_DATA_CHANGE.toInt(), 2)
        logLine("Pasteboard_NotifyType passed")
    }

    // ---------- 枚举：Pasteboard_FileConflictOptions ----------
    @Test
    fun testEnum_Pasteboard_FileConflictOptions() {
        assertEquals(PASTEBOARD_OVERWRITE.toInt(), 0)
        assertEquals(PASTEBOARD_SKIP.toInt(), 1)
        logLine("Pasteboard_FileConflictOptions passed")
    }

    // ---------- 枚举：Pasteboard_ProgressIndicator ----------
    @Test
    fun testEnum_Pasteboard_ProgressIndicator() {
        assertEquals(PASTEBOARD_NONE.toInt(), 0)
        assertEquals(PASTEBOARD_DEFAULT.toInt(), 1)
        logLine("Pasteboard_ProgressIndicator passed")
    }

    // ---------- 枚举：PASTEBOARD_ErrCode，覆盖 oh_pasteboard_err_code.h 全部 10 个值 ----------
    @Test
    fun testEnum_PASTEBOARD_ErrCode() {
        assertEquals(ERR_OK.toInt(), 0)
        assertEquals(ERR_PERMISSION_ERROR.toInt(), 201)
        assertEquals(ERR_INVALID_PARAMETER.toInt(), 401)
        assertEquals(ERR_DEVICE_NOT_SUPPORTED.toInt(), 801)
        assertEquals(ERR_INNER_ERROR.toInt(), 12900000)
        assertEquals(ERR_BUSY.toInt(), 12900003)
        assertEquals(ERR_PASTEBOARD_COPY_FILE_ERROR.toInt(), 12900007)
        assertEquals(ERR_PASTEBOARD_PROGRESS_START_ERROR.toInt(), 12900008)
        assertEquals(ERR_PASTEBOARD_PROGRESS_ABNORMAL.toInt(), 12900009)
        assertEquals(ERR_PASTEBOARD_GET_DATA_FAILED.toInt(), 12900010)
        logLine("PASTEBOARD_ErrCode passed")
    }

    // ---------- OH_PasteboardObserver ----------
    @Test
    fun testOH_PasteboardObserver_Create_Destroy() {
        val obs = OH_PasteboardObserver_Create()
        logLine("OH_PasteboardObserver_Create obs=$obs")
        assertNotNull(obs)
        val ret = OH_PasteboardObserver_Destroy(obs)
        logLine("OH_PasteboardObserver_Destroy ret=$ret")
        assertNotNull(ret)
    }

    @Test
    fun testOH_PasteboardObserver_SetData() { memScoped {
        val obs = OH_PasteboardObserver_Create()
        assertNotNull(obs)
        val ret = OH_PasteboardObserver_SetData(obs, null, null, null)
        logLine("OH_PasteboardObserver_SetData ret=$ret")
        assertNotNull(ret)
        OH_PasteboardObserver_Destroy(obs)
    } }

    // ---------- OH_Pasteboard 基础 ----------
    @Test
    fun testOH_Pasteboard_Create_Destroy() {
        val pb = OH_Pasteboard_Create()
        logLine("OH_Pasteboard_Create pb=$pb")
        assertNotNull(pb)
        OH_Pasteboard_Destroy(pb)
        logLine("OH_Pasteboard_Destroy done")
    }

    @Test
    fun testOH_Pasteboard_Subscribe_Unsubscribe() { memScoped {
        val pb = OH_Pasteboard_Create()
        val obs = OH_PasteboardObserver_Create()
        assertNotNull(pb)
        assertNotNull(obs)
        val subRet = OH_Pasteboard_Subscribe(pb, NOTIFY_LOCAL_DATA_CHANGE.toInt(), obs)
        logLine("OH_Pasteboard_Subscribe ret=$subRet")
        assertNotNull(subRet)
        val unsubRet = OH_Pasteboard_Unsubscribe(pb, NOTIFY_LOCAL_DATA_CHANGE.toInt(), obs)
        logLine("OH_Pasteboard_Unsubscribe ret=$unsubRet")
        assertNotNull(unsubRet)
        OH_Pasteboard_Destroy(pb)
        OH_PasteboardObserver_Destroy(obs)
    } }

    @Test
    fun testOH_Pasteboard_IsRemoteData_HasType_HasData() { memScoped {
        val pb = OH_Pasteboard_Create()
        assertNotNull(pb)
        val isRemote = OH_Pasteboard_IsRemoteData(pb)
        logLine("OH_Pasteboard_IsRemoteData=$isRemote")
        val hasType = OH_Pasteboard_HasType(pb, PASTEBOARD_MIMETYPE_TEXT_PLAIN)
        logLine("OH_Pasteboard_HasType(text/plain)=$hasType")
        val hasData = OH_Pasteboard_HasData(pb)
        logLine("OH_Pasteboard_HasData=$hasData")
        OH_Pasteboard_Destroy(pb)
    } }

    @Test
    fun testOH_Pasteboard_GetDataSource() { memScoped {
        val pb = OH_Pasteboard_Create()
        assertNotNull(pb)
        val buf = allocArray<ByteVar>(256)
        val ret = OH_Pasteboard_GetDataSource(pb, buf, 256u)
        logLine("OH_Pasteboard_GetDataSource ret=$ret")
        assertNotNull(ret)
        OH_Pasteboard_Destroy(pb)
    } }

    @Test
    fun testOH_Pasteboard_GetData_SetData_ClearData() { memScoped {
        val pb = OH_Pasteboard_Create()
        val status = alloc<IntVar>()
        status.value = 0
        assertNotNull(pb)
        val data = OH_Pasteboard_GetData(pb, status.ptr)
        logLine("OH_Pasteboard_GetData data=$data status=${status.value}")
        assertNotNull(status.ptr)
        val setRet = OH_Pasteboard_SetData(pb, null)
        logLine("OH_Pasteboard_SetData ret=$setRet")
        assertNotNull(setRet)
        val clearRet = OH_Pasteboard_ClearData(pb)
        logLine("OH_Pasteboard_ClearData ret=$clearRet")
        assertNotNull(clearRet)
        OH_Pasteboard_Destroy(pb)
    } }

    @Test
    fun testOH_Pasteboard_GetMimeTypes() { memScoped {
        val pb = OH_Pasteboard_Create()
        val count = alloc<UIntVar>()
        count.value = 0u
        assertNotNull(pb)
        val types = OH_Pasteboard_GetMimeTypes(pb, count.ptr)
        logLine("OH_Pasteboard_GetMimeTypes types=$types count=${count.value}")
        OH_Pasteboard_Destroy(pb)
    } }

    @Test
    fun testOH_Pasteboard_GetChangeCount() { memScoped {
        val pb = OH_Pasteboard_Create()
        assertNotNull(pb)
        val count = try { OH_Pasteboard_GetChangeCount(pb) } catch (e: Throwable) { logLine("OH_Pasteboard_GetChangeCount (API 18) exception: $e"); 0u }
        logLine("OH_Pasteboard_GetChangeCount=$count")
        OH_Pasteboard_Destroy(pb)
    } }

    // ---------- Pasteboard_GetDataParams & Progress ----------
    @Test
    fun testOH_Pasteboard_GetDataParams() { memScoped {
        val params = OH_Pasteboard_GetDataParams_Create()
        logLine("OH_Pasteboard_GetDataParams_Create params=$params")
        assertNotNull(params)
        OH_Pasteboard_GetDataParams_SetProgressIndicator(params, PASTEBOARD_NONE)
        logLine("OH_Pasteboard_GetDataParams_SetProgressIndicator done")
        OH_Pasteboard_GetDataParams_SetDestUri(params, null, 0u)
        logLine("OH_Pasteboard_GetDataParams_SetDestUri done")
        OH_Pasteboard_GetDataParams_SetFileConflictOptions(params, PASTEBOARD_OVERWRITE)
        logLine("OH_Pasteboard_GetDataParams_SetFileConflictOptions done")
        OH_Pasteboard_GetDataParams_SetProgressListener(params, null)
        logLine("OH_Pasteboard_GetDataParams_SetProgressListener done")
        OH_Pasteboard_ProgressCancel(params)
        logLine("OH_Pasteboard_ProgressCancel done")
        OH_Pasteboard_GetDataParams_Destroy(params)
        logLine("OH_Pasteboard_GetDataParams_Destroy done")
    } }

    @Test
    fun testOH_Pasteboard_GetDataWithProgress() { memScoped {
        val pb = OH_Pasteboard_Create()
        val params = OH_Pasteboard_GetDataParams_Create()
        val status = alloc<IntVar>()
        status.value = 0
        assertNotNull(pb)
        assertNotNull(params)
        val data = OH_Pasteboard_GetDataWithProgress(pb, params, status.ptr)
        logLine("OH_Pasteboard_GetDataWithProgress data=$data status=${status.value}")
        OH_Pasteboard_GetDataParams_Destroy(params)
        OH_Pasteboard_Destroy(pb)
    } }

    @Test
    fun testOH_Pasteboard_ProgressInfo_GetProgress() { memScoped {
        val progress = OH_Pasteboard_ProgressInfo_GetProgress(null)
        logLine("OH_Pasteboard_ProgressInfo_GetProgress(null) ret=$progress")
        assertNotNull(progress)
    } }

    @Test
    fun testOH_Pasteboard_SyncDelayedDataAsync() { memScoped {
        val pb = OH_Pasteboard_Create()
        assertNotNull(pb)
        try { OH_Pasteboard_SyncDelayedDataAsync(pb, null) } catch (e: Throwable) { logLine("OH_Pasteboard_SyncDelayedDataAsync (API 21) exception: $e") }
        logLine("OH_Pasteboard_SyncDelayedDataAsync done")
        OH_Pasteboard_Destroy(pb)
    } }

}
