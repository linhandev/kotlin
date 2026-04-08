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
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.cinterop.*

/**
 * FileShare C API 测试：仅覆盖 filemanagement/fileshare/oh_file_share.h。
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class FileShareTest {

    private fun logLine(message: String) = println(message)

    // ---------- enums: oh_file_share.h ----------

    @Test
    fun testEnum_FileShare_OperationMode() {
        val v0 = platform.CoreFileKit.FileShare.READ_MODE
        val v1 = platform.CoreFileKit.FileShare.WRITE_MODE
        logLine("READ_MODE=$v0"); assert(v0.toInt() == 1)
        logLine("WRITE_MODE=$v1"); assert(v1.toInt() == 2)
    }

    @Test
    fun testEnum_FileShare_PolicyErrorCode() {
        val v1 = platform.CoreFileKit.FileShare.PERSISTENCE_FORBIDDEN
        val v2 = platform.CoreFileKit.FileShare.INVALID_MODE
        val v3 = platform.CoreFileKit.FileShare.INVALID_PATH
        val v4 = platform.CoreFileKit.FileShare.PERMISSION_NOT_PERSISTED
        logLine("PERSISTENCE_FORBIDDEN=$v1"); assert(v1.toInt() == 1)
        logLine("INVALID_MODE=$v2"); assert(v2.toInt() == 2)
        logLine("INVALID_PATH=$v3"); assert(v3.toInt() == 3)
        logLine("PERMISSION_NOT_PERSISTED=$v4"); assert(v4.toInt() == 4)
    }

    // ---------- functions: oh_file_share.h ----------

    @Test
    fun testOH_FileShare_PersistPermission() {
        memScoped {
            val uriBytes = "file:///data/test.txt".encodeToByteArray().toCValues()
            val policy = alloc<platform.CoreFileKit.FileShare.FileShare_PolicyInfo>().apply {
                uri = uriBytes.ptr.reinterpret()
                length = 20u
                operationMode = platform.CoreFileKit.FileShare.READ_MODE.toInt().toUInt()
            }
            val errorResult = alloc<CPointerVar<platform.CoreFileKit.FileShare.FileShare_PolicyErrorResult>>()
            val resultNum = alloc<UIntVar>()
            val rc = platform.CoreFileKit.FileShare.OH_FileShare_PersistPermission(
                policy.ptr,
                1u,
                errorResult.ptr,
                resultNum.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileShare_PersistPermission=$rc")
            if (errorResult.value != null && resultNum.value > 0u)
                platform.CoreFileKit.FileShare.OH_FileShare_ReleasePolicyErrorResult(errorResult.value, resultNum.value)
        }
    }

    @Test
    fun testOH_FileShare_RevokePermission() {
        memScoped {
            val uriBytes = "file:///data/test2.txt".encodeToByteArray().toCValues()
            val policy = alloc<platform.CoreFileKit.FileShare.FileShare_PolicyInfo>().apply {
                uri = uriBytes.ptr.reinterpret()
                length = 21u
                operationMode = platform.CoreFileKit.FileShare.WRITE_MODE.toInt().toUInt()
            }
            val errorResult = alloc<CPointerVar<platform.CoreFileKit.FileShare.FileShare_PolicyErrorResult>>()
            val resultNum = alloc<UIntVar>()
            val rc = platform.CoreFileKit.FileShare.OH_FileShare_RevokePermission(
                policy.ptr,
                1u,
                errorResult.ptr,
                resultNum.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileShare_RevokePermission=$rc")
            if (errorResult.value != null && resultNum.value > 0u)
                platform.CoreFileKit.FileShare.OH_FileShare_ReleasePolicyErrorResult(errorResult.value, resultNum.value)
        }
    }

    @Test
    fun testOH_FileShare_ActivatePermission() {
        memScoped {
            val uriBytes = "file:///data/test3.txt".encodeToByteArray().toCValues()
            val policy = alloc<platform.CoreFileKit.FileShare.FileShare_PolicyInfo>().apply {
                uri = uriBytes.ptr.reinterpret()
                length = 21u
                operationMode = (platform.CoreFileKit.FileShare.READ_MODE.toInt() or
                    platform.CoreFileKit.FileShare.WRITE_MODE.toInt()).toUInt()
            }
            val errorResult = alloc<CPointerVar<platform.CoreFileKit.FileShare.FileShare_PolicyErrorResult>>()
            val resultNum = alloc<UIntVar>()
            val rc = platform.CoreFileKit.FileShare.OH_FileShare_ActivatePermission(
                policy.ptr,
                1u,
                errorResult.ptr,
                resultNum.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileShare_ActivatePermission=$rc")
            if (errorResult.value != null && resultNum.value > 0u)
                platform.CoreFileKit.FileShare.OH_FileShare_ReleasePolicyErrorResult(errorResult.value, resultNum.value)
        }
    }

    @Test
    fun testOH_FileShare_DeactivatePermission() {
        memScoped {
            val uriBytes = "file:///data/test4.txt".encodeToByteArray().toCValues()
            val policy = alloc<platform.CoreFileKit.FileShare.FileShare_PolicyInfo>().apply {
                uri = uriBytes.ptr.reinterpret()
                length = 21u
                operationMode = platform.CoreFileKit.FileShare.READ_MODE.toInt().toUInt()
            }
            val errorResult = alloc<CPointerVar<platform.CoreFileKit.FileShare.FileShare_PolicyErrorResult>>()
            val resultNum = alloc<UIntVar>()
            val rc = platform.CoreFileKit.FileShare.OH_FileShare_DeactivatePermission(
                policy.ptr,
                1u,
                errorResult.ptr,
                resultNum.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileShare_DeactivatePermission=$rc")
            if (errorResult.value != null && resultNum.value > 0u)
                platform.CoreFileKit.FileShare.OH_FileShare_ReleasePolicyErrorResult(errorResult.value, resultNum.value)
        }
    }

    @Test
    fun testOH_FileShare_CheckPersistentPermission() {
        memScoped {
            val uriBytes = "file:///data/test5.txt".encodeToByteArray().toCValues()
            val policy = alloc<platform.CoreFileKit.FileShare.FileShare_PolicyInfo>().apply {
                uri = uriBytes.ptr.reinterpret()
                length = 21u
                operationMode = platform.CoreFileKit.FileShare.READ_MODE.toInt().toUInt()
            }
            val boolResult = alloc<CPointerVar<BooleanVar>>()
            val resultNum = alloc<UIntVar>()
            val rc = platform.CoreFileKit.FileShare.OH_FileShare_CheckPersistentPermission(
                policy.ptr,
                1u,
                boolResult.ptr,
                resultNum.ptr
            )
            assertNotNull(rc)
            logLine("OH_FileShare_CheckPersistentPermission=$rc")
        }
    }

    @Test
    fun testOH_FileShare_ReleasePolicyErrorResult() {
        platform.CoreFileKit.FileShare.OH_FileShare_ReleasePolicyErrorResult(null, 0u)
        logLine("OH_FileShare_ReleasePolicyErrorResult(null,0)=called")
    }
}
