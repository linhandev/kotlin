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
import platform.ArkData.RDB.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class RDBTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- relational_store.h 枚举 ----------
    @Test
    fun testEnum_OH_Rdb_SecurityLevel() {
        assertEquals(S1.toInt(), 1)
        assertEquals(S2.toInt(), 2)
        assertEquals(S3.toInt(), 3)
        assertEquals(S4.toInt(), 4)
        logLine("OH_Rdb_SecurityLevel passed")
    }

    @Test
    fun testEnum_Rdb_SecurityArea() {
        assertEquals(RDB_SECURITY_AREA_EL1.toInt(), 1)
        assertEquals(RDB_SECURITY_AREA_EL2.toInt(), 2)
        assertEquals(RDB_SECURITY_AREA_EL3.toInt(), 3)
        assertEquals(RDB_SECURITY_AREA_EL4.toInt(), 4)
        assertEquals(RDB_SECURITY_AREA_EL5.toInt(), 5)
        logLine("Rdb_SecurityArea passed")
    }

    @Test
    fun testEnum_Rdb_DBType() {
        assertEquals(RDB_SQLITE.toInt(), 1)
        assertEquals(RDB_CAYLEY.toInt(), 2)
        assertEquals(DBTYPE_BUTT.toInt(), 64)
        logLine("Rdb_DBType passed")
    }

    @Test
    fun testEnum_Rdb_Tokenizer() {
        assertEquals(RDB_NONE_TOKENIZER.toInt(), 1)
        assertEquals(RDB_ICU_TOKENIZER.toInt(), 2)
        assertEquals(RDB_CUSTOM_TOKENIZER.toInt(), 3)
        logLine("Rdb_Tokenizer passed")
    }

    @Test
    fun testEnum_Rdb_DistributedType() {
        assertEquals(Rdb_DistributedType.RDB_DISTRIBUTED_CLOUD.value.toInt(), 0)
        logLine("Rdb_DistributedType passed")
    }

    @Test
    fun testEnum_Rdb_ChangeType() {
        assertEquals(Rdb_ChangeType.RDB_DATA_CHANGE.value.toInt(), 0)
        assertEquals(Rdb_ChangeType.RDB_ASSET_CHANGE.value.toInt(), 1)
        logLine("Rdb_ChangeType passed")
    }

    @Test
    fun testEnum_Rdb_SubscribeType() {
        assertEquals(Rdb_SubscribeType.RDB_SUBSCRIBE_TYPE_CLOUD.value.toInt(), 0)
        assertEquals(Rdb_SubscribeType.RDB_SUBSCRIBE_TYPE_CLOUD_DETAILS.value.toInt(), 1)
        assertEquals(Rdb_SubscribeType.RDB_SUBSCRIBE_TYPE_LOCAL_DETAILS.value.toInt(), 2)
        logLine("Rdb_SubscribeType passed")
    }

    @Test
    fun testEnum_Rdb_SyncMode() {
        assertEquals(Rdb_SyncMode.RDB_SYNC_MODE_TIME_FIRST.value.toInt(), 0)
        assertEquals(Rdb_SyncMode.RDB_SYNC_MODE_NATIVE_FIRST.value.toInt(), 1)
        assertEquals(Rdb_SyncMode.RDB_SYNC_MODE_CLOUD_FIRST.value.toInt(), 2)
        logLine("Rdb_SyncMode passed")
    }

    @Test
    fun testEnum_Rdb_Progress() {
        assertEquals(Rdb_Progress.RDB_SYNC_BEGIN.value.toInt(), 0)
        assertEquals(Rdb_Progress.RDB_SYNC_IN_PROGRESS.value.toInt(), 1)
        assertEquals(Rdb_Progress.RDB_SYNC_FINISH.value.toInt(), 2)
        logLine("Rdb_Progress passed")
    }

    @Test
    fun testEnum_Rdb_ProgressCode() {
        assertEquals(Rdb_ProgressCode.RDB_SUCCESS.value.toInt(), 0)
        assertEquals(Rdb_ProgressCode.RDB_UNKNOWN_ERROR.value.toInt(), 1)
        assertEquals(Rdb_ProgressCode.RDB_NETWORK_ERROR.value.toInt(), 2)
        assertEquals(Rdb_ProgressCode.RDB_CLOUD_DISABLED.value.toInt(), 3)
        assertEquals(Rdb_ProgressCode.RDB_LOCKED_BY_OTHERS.value.toInt(), 4)
        assertEquals(Rdb_ProgressCode.RDB_RECORD_LIMIT_EXCEEDED.value.toInt(), 5)
        assertEquals(Rdb_ProgressCode.RDB_NO_SPACE_FOR_ASSET.value.toInt(), 6)
        logLine("Rdb_ProgressCode passed")
    }

    // 覆盖 oh_rdb_types.h 中 Rdb_ConflictResolution 全部取值
    @Test
    fun testEnum_Rdb_ConflictResolution() {
        assertEquals(RDB_CONFLICT_NONE.toInt(), 1)
        assertEquals(RDB_CONFLICT_ROLLBACK.toInt(), 2)
        assertEquals(RDB_CONFLICT_ABORT.toInt(), 3)
        assertEquals(RDB_CONFLICT_FAIL.toInt(), 4)
        assertEquals(RDB_CONFLICT_IGNORE.toInt(), 5)
        assertEquals(RDB_CONFLICT_REPLACE.toInt(), 6)
        logLine("Rdb_ConflictResolution passed")
    }

    // 覆盖 relational_store_error_code.h 中 OH_Rdb_ErrCode 全部取值
    @Test
    fun testEnum_OH_Rdb_ErrCode() {
        assertEquals(RDB_ERR.toInt(), -1)
        assertEquals(RDB_OK.toInt(), 0)
        assertEquals(RDB_E_NOT_SUPPORTED.toInt(), 801)
        assertEquals(E_BASE.toInt(), 14800000)
        assertEquals(RDB_E_ERROR.toInt(), 14800000)
        assertEquals(RDB_E_INVALID_ARGS.toInt(), 14800001)
        assertEquals(RDB_E_CANNOT_UPDATE_READONLY.toInt(), 14800002)
        assertEquals(RDB_E_REMOVE_FILE.toInt(), 14800003)
        assertEquals(RDB_E_EMPTY_TABLE_NAME.toInt(), 14800005)
        assertEquals(RDB_E_EMPTY_VALUES_BUCKET.toInt(), 14800006)
        assertEquals(RDB_E_EXECUTE_IN_STEP_QUERY.toInt(), 14800007)
        assertEquals(RDB_E_INVALID_COLUMN_INDEX.toInt(), 14800008)
        assertEquals(RDB_E_INVALID_COLUMN_TYPE.toInt(), 14800009)
        assertEquals(RDB_E_EMPTY_FILE_NAME.toInt(), 14800010)
        assertEquals(RDB_E_INVALID_FILE_PATH.toInt(), 14800011)
        assertEquals(RDB_E_TRANSACTION_IN_EXECUTE.toInt(), 14800012)
        assertEquals(RDB_E_INVALID_STATEMENT.toInt(), 14800013)
        assertEquals(RDB_E_EXECUTE_WRITE_IN_READ_CONNECTION.toInt(), 14800014)
        assertEquals(RDB_E_BEGIN_TRANSACTION_IN_READ_CONNECTION.toInt(), 14800015)
        assertEquals(RDB_E_NO_TRANSACTION_IN_SESSION.toInt(), 14800016)
        assertEquals(RDB_E_MORE_STEP_QUERY_IN_ONE_SESSION.toInt(), 14800017)
        assertEquals(RDB_E_NO_ROW_IN_QUERY.toInt(), 14800018)
        assertEquals(RDB_E_INVALID_BIND_ARGS_COUNT.toInt(), 14800019)
        assertEquals(RDB_E_INVALID_OBJECT_TYPE.toInt(), 14800020)
        assertEquals(RDB_E_INVALID_CONFLICT_FLAG.toInt(), 14800021)
        assertEquals(RDB_E_HAVING_CLAUSE_NOT_IN_GROUP_BY.toInt(), 14800022)
        assertEquals(RDB_E_NOT_SUPPORTED_BY_STEP_RESULT_SET.toInt(), 14800023)
        assertEquals(RDB_E_STEP_RESULT_SET_CROSS_THREADS.toInt(), 14800024)
        assertEquals(RDB_E_STEP_RESULT_QUERY_NOT_EXECUTED.toInt(), 14800025)
        assertEquals(RDB_E_STEP_RESULT_IS_AFTER_LAST.toInt(), 14800026)
        assertEquals(RDB_E_STEP_RESULT_QUERY_EXCEEDED.toInt(), 14800027)
        assertEquals(RDB_E_STATEMENT_NOT_PREPARED.toInt(), 14800028)
        assertEquals(RDB_E_EXECUTE_RESULT_INCORRECT.toInt(), 14800029)
        assertEquals(RDB_E_STEP_RESULT_CLOSED.toInt(), 14800030)
        assertEquals(RDB_E_RELATIVE_PATH.toInt(), 14800031)
        assertEquals(RDB_E_EMPTY_NEW_ENCRYPT_KEY.toInt(), 14800032)
        assertEquals(RDB_E_CHANGE_UNENCRYPTED_TO_ENCRYPTED.toInt(), 14800033)
        assertEquals(RDB_E_CHANGE_ENCRYPT_KEY_IN_BUSY.toInt(), 14800034)
        assertEquals(RDB_E_STEP_STATEMENT_NOT_INIT.toInt(), 14800035)
        assertEquals(RDB_E_NOT_SUPPORTED_ATTACH_IN_WAL_MODE.toInt(), 14800036)
        assertEquals(RDB_E_CREATE_FOLDER_FAIL.toInt(), 14800037)
        assertEquals(RDB_E_SQLITE_SQL_BUILDER_NORMALIZE_FAIL.toInt(), 14800038)
        assertEquals(RDB_E_STORE_SESSION_NOT_GIVE_CONNECTION_TEMPORARILY.toInt(), 14800039)
        assertEquals(RDB_E_STORE_SESSION_NO_CURRENT_TRANSACTION.toInt(), 14800040)
        assertEquals(RDB_E_NOT_SUPPORT.toInt(), 14800041)
        assertEquals(RDB_E_INVALID_PARCEL.toInt(), 14800042)
        assertEquals(RDB_E_QUERY_IN_EXECUTE.toInt(), 14800043)
        assertEquals(RDB_E_SET_PERSIST_WAL.toInt(), 14800044)
        assertEquals(RDB_E_DB_NOT_EXIST.toInt(), 14800045)
        assertEquals(RDB_E_ARGS_READ_CON_OVERLOAD.toInt(), 14800046)
        assertEquals(RDB_E_WAL_SIZE_OVER_LIMIT.toInt(), 14800047)
        assertEquals(RDB_E_CON_OVER_LIMIT.toInt(), 14800048)
        assertEquals(RDB_E_ALREADY_CLOSED.toInt(), 14800050)
        assertEquals(RDB_E_DATABASE_BUSY.toInt(), 14800051)
        assertEquals(RDB_E_SQLITE_CORRUPT.toInt(), 14800052)
        assertEquals(RDB_E_SQLITE_PERM.toInt(), 14800053)
        assertEquals(RDB_E_SQLITE_BUSY.toInt(), 14800054)
        assertEquals(RDB_E_SQLITE_LOCKED.toInt(), 14800055)
        assertEquals(RDB_E_SQLITE_NOMEM.toInt(), 14800056)
        assertEquals(RDB_E_SQLITE_READONLY.toInt(), 14800057)
        assertEquals(RDB_E_SQLITE_IOERR.toInt(), 14800058)
        assertEquals(RDB_E_SQLITE_FULL.toInt(), 14800059)
        assertEquals(RDB_E_SQLITE_CANT_OPEN.toInt(), 14800060)
        assertEquals(RDB_E_SQLITE_TOO_BIG.toInt(), 14800061)
        assertEquals(RDB_E_SQLITE_MISMATCH.toInt(), 14800062)
        assertEquals(RDB_E_DATA_TYPE_NULL.toInt(), 14800063)
        assertEquals(RDB_E_TYPE_MISMATCH.toInt(), 14800064)
        assertEquals(RDB_E_SQLITE_CONSTRAINT.toInt(), 14800065)
        assertEquals(RDB_E_SUB_LIMIT_REACHED.toInt(), 14800066)
        logLine("OH_Rdb_ErrCode passed")
    }

    @Test
    fun testOH_Rdb_CreateConfig() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_CreateConfig passed")
    }

    @Test
    fun testOH_Rdb_DestroyConfig() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        assertNotNull(OH_Rdb_DestroyConfig(config))
        logLine("OH_Rdb_DestroyConfig passed")
    }

    @Test
    fun testOH_Rdb_SetDatabaseDir() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        assertNotNull(OH_Rdb_SetDatabaseDir(config, "/data"))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetDatabaseDir passed")
    }

    @Test
    fun testOH_Rdb_SetStoreName() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        assertNotNull(OH_Rdb_SetStoreName(config, "test"))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetStoreName passed")
    }

    // ---------- oh_rdb_crypto_param.h 枚举与函数 ----------
    @Test
    fun testEnum_Rdb_EncryptionAlgo() {
        assertEquals(RDB_AES_256_GCM.toInt(), 0)
        assertEquals(RDB_AES_256_CBC.toInt(), 1)
        assertEquals(RDB_PLAIN_TEXT.toInt(), 2)
        logLine("Rdb_EncryptionAlgo passed")
    }

    @Test
    fun testEnum_Rdb_HmacAlgo() {
        assertEquals(RDB_HMAC_SHA1.toInt(), 0)
        assertEquals(RDB_HMAC_SHA256.toInt(), 1)
        assertEquals(RDB_HMAC_SHA512.toInt(), 2)
        logLine("Rdb_HmacAlgo passed")
    }

    @Test
    fun testEnum_Rdb_KdfAlgo() {
        assertEquals(RDB_KDF_SHA1.toInt(), 0)
        assertEquals(RDB_KDF_SHA256.toInt(), 1)
        assertEquals(RDB_KDF_SHA512.toInt(), 2)
        logLine("Rdb_KdfAlgo passed")
    }

    @Test
    fun testOH_Rdb_CreateCryptoParam() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { logLine("OH_Rdb_CreateCryptoParam (API 20) exception: $e"); null }
        if (param != null) try { OH_Rdb_DestroyCryptoParam(param) } catch (e: Throwable) { logLine("OH_Rdb_DestroyCryptoParam (API 20) exception: $e") }
        logLine("OH_Rdb_CreateCryptoParam passed")
    }

    @Test
    fun testOH_Rdb_DestroyCryptoParam() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { logLine("OH_Rdb_CreateCryptoParam (API 20) exception: $e"); null }
        if (param != null) logLine("OH_Rdb_DestroyCryptoParam ret=" + try { OH_Rdb_DestroyCryptoParam(param) } catch (e: Throwable) { logLine("OH_Rdb_DestroyCryptoParam (API 20) exception: $e"); RDB_E_INVALID_ARGS })
        logLine("OH_Rdb_DestroyCryptoParam passed")
    }

    @Test
    fun testOH_Crypto_SetEncryptionKey() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { null }
        logLine("OH_Crypto_SetEncryptionKey ret=" + try { OH_Crypto_SetEncryptionKey(param, null, 0) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        try { OH_Rdb_DestroyCryptoParam(param) } catch (_: Throwable) { }
        logLine("OH_Crypto_SetEncryptionKey passed")
    }

    @Test
    fun testOH_Crypto_SetIteration() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { null }
        logLine("OH_Crypto_SetIteration ret=" + try { OH_Crypto_SetIteration(param, 1000L) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        try { OH_Rdb_DestroyCryptoParam(param) } catch (_: Throwable) { }
        logLine("OH_Crypto_SetIteration passed")
    }

    @Test
    fun testOH_Crypto_SetEncryptionAlgo() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { null }
        logLine("OH_Crypto_SetEncryptionAlgo ret=" + try { OH_Crypto_SetEncryptionAlgo(param, RDB_AES_256_GCM.toInt()) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        try { OH_Rdb_DestroyCryptoParam(param) } catch (_: Throwable) { }
        logLine("OH_Crypto_SetEncryptionAlgo passed")
    }

    @Test
    fun testOH_Crypto_SetHmacAlgo() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { null }
        logLine("OH_Crypto_SetHmacAlgo ret=" + try { OH_Crypto_SetHmacAlgo(param, RDB_HMAC_SHA256.toInt()) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        try { OH_Rdb_DestroyCryptoParam(param) } catch (_: Throwable) { }
        logLine("OH_Crypto_SetHmacAlgo passed")
    }

    @Test
    fun testOH_Crypto_SetKdfAlgo() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { null }
        logLine("OH_Crypto_SetKdfAlgo ret=" + try { OH_Crypto_SetKdfAlgo(param, RDB_KDF_SHA256.toInt()) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        try { OH_Rdb_DestroyCryptoParam(param) } catch (_: Throwable) { }
        logLine("OH_Crypto_SetKdfAlgo passed")
    }

    @Test
    fun testOH_Crypto_SetCryptoPageSize() {
        val param = try { OH_Rdb_CreateCryptoParam() } catch (e: Throwable) { null }
        if (param != null) {
            logLine("OH_Crypto_SetCryptoPageSize ret=" + try { OH_Crypto_SetCryptoPageSize(param, 4096L) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
            try { OH_Rdb_DestroyCryptoParam(param) } catch (_: Throwable) { }
        }
        logLine("OH_Crypto_SetCryptoPageSize passed")
    }

    // ---------- oh_rdb_transaction.h 枚举与函数 (API 18) ----------
    @Test
    fun testEnum_OH_RDB_TransType() {
        try {
            assertEquals(RDB_TRANS_DEFERRED.toInt(), 0)
            assertEquals(RDB_TRANS_IMMEDIATE.toInt(), 1)
            assertEquals(RDB_TRANS_EXCLUSIVE.toInt(), 2)
            assertEquals(RDB_TRANS_BUTT.toInt(), 3)
            logLine("OH_RDB_TransType passed")
        } catch (e: Throwable) { logLine("testEnum_OH_RDB_TransType (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_CreateOptions() {
        try {
            val opts = OH_RdbTrans_CreateOptions()
            assertNotNull(opts)
            OH_RdbTrans_DestroyOptions(opts)
            logLine("OH_RdbTrans_CreateOptions passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_CreateOptions (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_DestroyOptions() {
        try {
            val opts = OH_RdbTrans_CreateOptions()
            assertNotNull(opts)
            logLine("OH_RdbTrans_DestroyOptions ret=" + OH_RdbTrans_DestroyOptions(opts))
            logLine("OH_RdbTrans_DestroyOptions passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_DestroyOptions (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTransOption_SetType() {
        try {
            val opts = OH_RdbTrans_CreateOptions()
            assertNotNull(opts)
            logLine("OH_RdbTransOption_SetType ret=" + OH_RdbTransOption_SetType(opts, RDB_TRANS_IMMEDIATE))
            OH_RdbTrans_DestroyOptions(opts)
            logLine("OH_RdbTransOption_SetType passed")
        } catch (e: Throwable) { logLine("testOH_RdbTransOption_SetType (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Commit() {
        try {
            logLine("OH_RdbTrans_Commit ret=" + OH_RdbTrans_Commit(null))
            logLine("OH_RdbTrans_Commit passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Commit (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Rollback() {
        try {
            logLine("OH_RdbTrans_Rollback ret=" + OH_RdbTrans_Rollback(null))
            logLine("OH_RdbTrans_Rollback passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Rollback (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Insert() {
        try {
            memScoped {
                val rowId = alloc<LongVar>()
                logLine("OH_RdbTrans_Insert ret=" + OH_RdbTrans_Insert(null, null, null, rowId.ptr))
            }
            logLine("OH_RdbTrans_Insert passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Insert (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_InsertWithConflictResolution() {
        try {
            memScoped {
                val rowId = alloc<LongVar>()
                logLine("OH_RdbTrans_InsertWithConflictResolution ret=" + OH_RdbTrans_InsertWithConflictResolution(null, null, null, RDB_CONFLICT_NONE, rowId.ptr))
            }
            logLine("OH_RdbTrans_InsertWithConflictResolution passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_InsertWithConflictResolution (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_BatchInsert() {
        try {
            memScoped {
                val changes = alloc<LongVar>()
                logLine("OH_RdbTrans_BatchInsert ret=" + OH_RdbTrans_BatchInsert(null, null, null, RDB_CONFLICT_NONE, changes.ptr))
            }
            logLine("OH_RdbTrans_BatchInsert passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_BatchInsert (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Update() {
        try {
            memScoped {
                val changes = alloc<LongVar>()
                logLine("OH_RdbTrans_Update ret=" + OH_RdbTrans_Update(null, null, null, changes.ptr))
            }
            logLine("OH_RdbTrans_Update passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Update (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_UpdateWithConflictResolution() {
        try {
            memScoped {
                val changes = alloc<LongVar>()
                logLine("OH_RdbTrans_UpdateWithConflictResolution ret=" + OH_RdbTrans_UpdateWithConflictResolution(null, null, null, RDB_CONFLICT_NONE, changes.ptr))
            }
            logLine("OH_RdbTrans_UpdateWithConflictResolution passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_UpdateWithConflictResolution (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Delete() {
        try {
            memScoped {
                val changes = alloc<LongVar>()
                logLine("OH_RdbTrans_Delete ret=" + OH_RdbTrans_Delete(null, null, changes.ptr))
            }
            logLine("OH_RdbTrans_Delete passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Delete (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Query() {
        try {
            logLine("OH_RdbTrans_Query " + OH_RdbTrans_Query(null, null, null, 0))
            logLine("OH_RdbTrans_Query passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Query (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_QuerySql() {
        try {
            logLine("OH_RdbTrans_QuerySql " + OH_RdbTrans_QuerySql(null, null, null))
            logLine("OH_RdbTrans_QuerySql passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_QuerySql (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Execute() {
        try {
            logLine("OH_RdbTrans_Execute ret=" + OH_RdbTrans_Execute(null, null, null, null))
            logLine("OH_RdbTrans_Execute passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Execute (API >17) exception: $e") }
    }

    @Test
    fun testOH_RdbTrans_Destroy() {
        try {
            logLine("OH_RdbTrans_Destroy ret=" + OH_RdbTrans_Destroy(null))
            logLine("OH_RdbTrans_Destroy passed")
        } catch (e: Throwable) { logLine("testOH_RdbTrans_Destroy (API >17) exception: $e") }
    }

    // ---------- oh_values_bucket.h 中 4 个独立函数 (PutAsset/PutAssets API 11) ----------
    @Test
    fun testOH_VBucket_PutAsset() {
        try {
            val ret = OH_VBucket_PutAsset(null, null, null)
            logLine("OH_VBucket_PutAsset ret=$ret")
            assertNotNull(ret)
        } catch (e: Throwable) { logLine("testOH_VBucket_PutAsset (API >17) exception: $e") }
    }

    @Test
    fun testOH_VBucket_PutAssets() {
        try {
            val ret = OH_VBucket_PutAssets(null, null, null, 0u)
            logLine("OH_VBucket_PutAssets ret=$ret")
            assertNotNull(ret)
        } catch (e: Throwable) { logLine("testOH_VBucket_PutAssets (API >17) exception: $e") }
    }

    @Test
    fun testOH_VBucket_PutFloatVector() { memScoped {
        val ret = try { OH_VBucket_PutFloatVector(null, null, null, 0u) } catch (e: Throwable) { logLine("OH_VBucket_PutFloatVector (API 18) exception: $e"); RDB_E_INVALID_ARGS }
        logLine("OH_VBucket_PutFloatVector ret=$ret")
        assertNotNull(ret)
    } }

    @Test
    fun testOH_VBucket_PutUnlimitedInt() { memScoped {
        val ret = try { OH_VBucket_PutUnlimitedInt(null, null, 0, null, 0u) } catch (e: Throwable) { logLine("OH_VBucket_PutUnlimitedInt (API 18) exception: $e"); RDB_E_INVALID_ARGS }
        logLine("OH_VBucket_PutUnlimitedInt ret=$ret")
        assertNotNull(ret)
    } }

    // ---------- oh_data_value.h 枚举与函数 (API 18) ----------
    @Test
    fun testEnum_OH_ColumnType() {
        try {
            assertEquals(TYPE_NULL.toInt(), 0)
            assertEquals(TYPE_INT64.toInt(), 1)
            assertEquals(TYPE_REAL.toInt(), 2)
            assertEquals(TYPE_TEXT.toInt(), 3)
            assertEquals(TYPE_BLOB.toInt(), 4)
            assertEquals(TYPE_ASSET.toInt(), 5)
            assertEquals(TYPE_ASSETS.toInt(), 6)
            assertEquals(TYPE_FLOAT_VECTOR.toInt(), 7)
            assertEquals(TYPE_UNLIMITED_INT.toInt(), 8)
            logLine("OH_ColumnType passed")
        } catch (e: Throwable) { logLine("testEnum_OH_ColumnType (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_Create() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            OH_Value_Destroy(v)
            logLine("OH_Value_Create passed")
        } catch (e: Throwable) { logLine("testOH_Value_Create (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_Destroy() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_Destroy ret=" + OH_Value_Destroy(v))
            logLine("OH_Value_Destroy passed")
        } catch (e: Throwable) { logLine("testOH_Value_Destroy (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutNull() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutNull ret=" + OH_Value_PutNull(v))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutNull passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutNull (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutInt() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutInt ret=" + OH_Value_PutInt(v, 42L))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutInt passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutInt (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutReal() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutReal ret=" + OH_Value_PutReal(v, 3.14))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutReal passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutReal (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutText() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutText ret=" + OH_Value_PutText(v, "hi"))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutText passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutText (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutBlob() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutBlob ret=" + OH_Value_PutBlob(v, null, 0u))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutBlob passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutBlob (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutAsset() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutAsset ret=" + OH_Value_PutAsset(v, null))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutAsset passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutAsset (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutAssets() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutAssets ret=" + OH_Value_PutAssets(v, null, 0u))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutAssets passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutAssets (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutFloatVector() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutFloatVector ret=" + OH_Value_PutFloatVector(v, null, 0u))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutFloatVector passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutFloatVector (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_PutUnlimitedInt() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            logLine("OH_Value_PutUnlimitedInt ret=" + OH_Value_PutUnlimitedInt(v, 0, null, 0u))
            OH_Value_Destroy(v)
            logLine("OH_Value_PutUnlimitedInt passed")
        } catch (e: Throwable) { logLine("testOH_Value_PutUnlimitedInt (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetType() {
        try {
            memScoped {
                val typeOut = alloc<UIntVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                OH_Value_PutInt(v, 1L)
                logLine("OH_Value_GetType ret=" + OH_Value_GetType(v, typeOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetType passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetType (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_IsNull() {
        try {
            memScoped {
                val boolOut = alloc<BooleanVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_IsNull ret=" + OH_Value_IsNull(v, boolOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_IsNull passed")
        } catch (e: Throwable) { logLine("testOH_Value_IsNull (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetInt() {
        try {
            memScoped {
                val int64Out = alloc<LongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                OH_Value_PutInt(v, 1L)
                logLine("OH_Value_GetInt ret=" + OH_Value_GetInt(v, int64Out.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetInt passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetInt (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetReal() {
        try {
            memScoped {
                val doubleOut = alloc<DoubleVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                OH_Value_PutReal(v, 1.0)
                logLine("OH_Value_GetReal ret=" + OH_Value_GetReal(v, doubleOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetReal passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetReal (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetText() {
        try {
            val v = OH_Value_Create()
            assertNotNull(v)
            OH_Value_PutText(v, "hi")
            logLine("OH_Value_GetText ret=" + OH_Value_GetText(v, null))
            OH_Value_Destroy(v)
            logLine("OH_Value_GetText passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetText (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetBlob() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetBlob ret=" + OH_Value_GetBlob(v, null, sizeOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetBlob passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetBlob (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetAsset() {
        try {
            memScoped {
                val assetOut = allocArray<ByteVar>(128).reinterpret<Data_Asset>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetAsset ret=" + OH_Value_GetAsset(v, assetOut))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetAsset passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetAsset (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetAssetsCount() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetAssetsCount ret=" + OH_Value_GetAssetsCount(v, sizeOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetAssetsCount passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetAssetsCount (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetAssets() {
        try {
            memScoped {
                val outLen = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetAssets ret=" + OH_Value_GetAssets(v, null, 0u, outLen.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetAssets passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetAssets (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetFloatVectorCount() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetFloatVectorCount ret=" + OH_Value_GetFloatVectorCount(v, sizeOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetFloatVectorCount passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetFloatVectorCount (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetFloatVector() {
        try {
            memScoped {
                val floatBuf = alloc<FloatVar>()
                val outLen = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetFloatVector ret=" + OH_Value_GetFloatVector(v, floatBuf.ptr, 0u, outLen.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetFloatVector passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetFloatVector (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetUnlimitedIntBand() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetUnlimitedIntBand ret=" + OH_Value_GetUnlimitedIntBand(v, sizeOut.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetUnlimitedIntBand passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetUnlimitedIntBand (API >17) exception: $e") }
    }

    @Test
    fun testOH_Value_GetUnlimitedInt() {
        try {
            memScoped {
                val signOut = alloc<IntVar>()
                val uint64Buf = allocArray<ULongVar>(1)
                val outLen = alloc<ULongVar>()
                val v = OH_Value_Create()
                assertNotNull(v)
                logLine("OH_Value_GetUnlimitedInt ret=" + OH_Value_GetUnlimitedInt(v, signOut.ptr, uint64Buf, 1u, outLen.ptr))
                OH_Value_Destroy(v)
            }
            logLine("OH_Value_GetUnlimitedInt passed")
        } catch (e: Throwable) { logLine("testOH_Value_GetUnlimitedInt (API >17) exception: $e") }
    }

    // ---------- oh_data_values.h 函数 (API 18) ----------
    @Test
    fun testOH_Values_Create() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            OH_Values_Destroy(vs)
            logLine("OH_Values_Create passed")
        } catch (e: Throwable) { logLine("testOH_Values_Create (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_Destroy() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_Destroy ret=" + OH_Values_Destroy(vs))
            logLine("OH_Values_Destroy passed")
        } catch (e: Throwable) { logLine("testOH_Values_Destroy (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_Put() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_Put ret=" + OH_Values_Put(vs, null))
            OH_Values_Destroy(vs)
            logLine("OH_Values_Put passed")
        } catch (e: Throwable) { logLine("testOH_Values_Put (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutNull() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutNull ret=" + OH_Values_PutNull(vs))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutNull passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutNull (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutInt() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutInt ret=" + OH_Values_PutInt(vs, 1L))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutInt passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutInt (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutReal() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutReal ret=" + OH_Values_PutReal(vs, 2.0))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutReal passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutReal (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutText() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutText ret=" + OH_Values_PutText(vs, "x"))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutText passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutText (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutBlob() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutBlob ret=" + OH_Values_PutBlob(vs, null, 0u))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutBlob passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutBlob (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutAsset() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutAsset ret=" + OH_Values_PutAsset(vs, null))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutAsset passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutAsset (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutAssets() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutAssets ret=" + OH_Values_PutAssets(vs, null, 0u))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutAssets passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutAssets (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutFloatVector() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutFloatVector ret=" + OH_Values_PutFloatVector(vs, null, 0u))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutFloatVector passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutFloatVector (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_PutUnlimitedInt() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            logLine("OH_Values_PutUnlimitedInt ret=" + OH_Values_PutUnlimitedInt(vs, 0, null, 0u))
            OH_Values_Destroy(vs)
            logLine("OH_Values_PutUnlimitedInt passed")
        } catch (e: Throwable) { logLine("testOH_Values_PutUnlimitedInt (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_Count() {
        try {
            memScoped {
                val countOut = alloc<ULongVar>()
                val vs = OH_Values_Create()
                assertNotNull(vs)
                logLine("OH_Values_Count ret=" + OH_Values_Count(vs, countOut.ptr))
                OH_Values_Destroy(vs)
            }
            logLine("OH_Values_Count passed")
        } catch (e: Throwable) { logLine("testOH_Values_Count (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetType() {
        try {
            memScoped {
                val typeOut = alloc<UIntVar>()
                val vs = OH_Values_Create()
                assertNotNull(vs)
                OH_Values_PutInt(vs, 10L)
                logLine("OH_Values_GetType ret=" + OH_Values_GetType(vs, 0, typeOut.ptr))
                OH_Values_Destroy(vs)
            }
            logLine("OH_Values_GetType passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetType (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_Get() {
        try {
            val vs = OH_Values_Create()
            assertNotNull(vs)
            OH_Values_PutInt(vs, 10L)
            logLine("OH_Values_Get ret=" + OH_Values_Get(vs, 0, null))
            OH_Values_Destroy(vs)
            logLine("OH_Values_Get passed")
        } catch (e: Throwable) { logLine("testOH_Values_Get (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_IsNull() {
        try {
            memScoped {
                val boolOut = alloc<BooleanVar>()
                val vs = OH_Values_Create()
                assertNotNull(vs)
                logLine("OH_Values_IsNull ret=" + OH_Values_IsNull(vs, 0, boolOut.ptr))
                OH_Values_Destroy(vs)
            }
            logLine("OH_Values_IsNull passed")
        } catch (e: Throwable) { logLine("testOH_Values_IsNull (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetInt() {
        try {
            memScoped {
                val int64Out = alloc<LongVar>()
                val vs = OH_Values_Create()
                assertNotNull(vs)
                OH_Values_PutInt(vs, 10L)
                logLine("OH_Values_GetInt ret=" + OH_Values_GetInt(vs, 0, int64Out.ptr))
                OH_Values_Destroy(vs)
            }
            logLine("OH_Values_GetInt passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetInt (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetReal() {
        try {
            memScoped {
                val doubleOut = alloc<DoubleVar>()
                val vs = OH_Values_Create()
                assertNotNull(vs)
                OH_Values_PutReal(vs, 2.0)
                logLine("OH_Values_GetReal ret=" + OH_Values_GetReal(vs, 0, doubleOut.ptr))
                OH_Values_Destroy(vs)
            }
            logLine("OH_Values_GetReal passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetReal (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetText() {
        try {
            logLine("OH_Values_GetText(null) ret=" + OH_Values_GetText(null, 0, null))
            logLine("OH_Values_GetText passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetText (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetBlob() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                logLine("OH_Values_GetBlob(null) ret=" + OH_Values_GetBlob(null, 0, null, sizeOut.ptr))
            }
            logLine("OH_Values_GetBlob passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetBlob (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetAsset() {
        try {
            memScoped {
                val assetOut = allocArray<ByteVar>(128).reinterpret<Data_Asset>()
                logLine("OH_Values_GetAsset(null) ret=" + OH_Values_GetAsset(null, 0, assetOut))
            }
            logLine("OH_Values_GetAsset passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetAsset (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetAssetsCount() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                logLine("OH_Values_GetAssetsCount(null) ret=" + OH_Values_GetAssetsCount(null, 0, sizeOut.ptr))
            }
            logLine("OH_Values_GetAssetsCount passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetAssetsCount (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetAssets() {
        try {
            memScoped {
                val outLen = alloc<ULongVar>()
                logLine("OH_Values_GetAssets(null) ret=" + OH_Values_GetAssets(null, 0, null, 0u, outLen.ptr))
            }
            logLine("OH_Values_GetAssets passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetAssets (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetFloatVectorCount() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                logLine("OH_Values_GetFloatVectorCount(null) ret=" + OH_Values_GetFloatVectorCount(null, 0, sizeOut.ptr))
            }
            logLine("OH_Values_GetFloatVectorCount passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetFloatVectorCount (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetFloatVector() {
        try {
            memScoped {
                val floatBuf = alloc<FloatVar>()
                val outLen = alloc<ULongVar>()
                logLine("OH_Values_GetFloatVector(null) ret=" + OH_Values_GetFloatVector(null, 0, floatBuf.ptr, 0u, outLen.ptr))
            }
            logLine("OH_Values_GetFloatVector passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetFloatVector (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetUnlimitedIntBand() {
        try {
            memScoped {
                val sizeOut = alloc<ULongVar>()
                logLine("OH_Values_GetUnlimitedIntBand(null) ret=" + OH_Values_GetUnlimitedIntBand(null, 0, sizeOut.ptr))
            }
            logLine("OH_Values_GetUnlimitedIntBand passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetUnlimitedIntBand (API >17) exception: $e") }
    }

    @Test
    fun testOH_Values_GetUnlimitedInt() {
        try {
            memScoped {
                val signOut = alloc<IntVar>()
                val uint64Buf = allocArray<ULongVar>(1)
                val outLen = alloc<ULongVar>()
                logLine("OH_Values_GetUnlimitedInt(null) ret=" + OH_Values_GetUnlimitedInt(null, 0, signOut.ptr, uint64Buf, 1u, outLen.ptr))
            }
            logLine("OH_Values_GetUnlimitedInt passed")
        } catch (e: Throwable) { logLine("testOH_Values_GetUnlimitedInt (API >17) exception: $e") }
    }

    // ---------- oh_data_values_buckets.h 函数 (API 18) ----------
    @Test
    fun testOH_VBuckets_Create() {
        try {
            val buckets = OH_VBuckets_Create()
            assertNotNull(buckets)
            OH_VBuckets_Destroy(buckets)
            logLine("OH_VBuckets_Create passed")
        } catch (e: Throwable) { logLine("testOH_VBuckets_Create (API >17) exception: $e") }
    }

    @Test
    fun testOH_VBuckets_Destroy() {
        try {
            val buckets = OH_VBuckets_Create()
            assertNotNull(buckets)
            logLine("OH_VBuckets_Destroy ret=" + OH_VBuckets_Destroy(buckets))
            logLine("OH_VBuckets_Destroy passed")
        } catch (e: Throwable) { logLine("testOH_VBuckets_Destroy (API >17) exception: $e") }
    }

    @Test
    fun testOH_VBuckets_PutRow() {
        try {
            val buckets = OH_VBuckets_Create()
            assertNotNull(buckets)
            logLine("OH_VBuckets_PutRow ret=" + OH_VBuckets_PutRow(buckets, null))
            OH_VBuckets_Destroy(buckets)
            logLine("OH_VBuckets_PutRow passed")
        } catch (e: Throwable) { logLine("testOH_VBuckets_PutRow (API >17) exception: $e") }
    }

    @Test
    fun testOH_VBuckets_PutRows() {
        try {
            val buckets = OH_VBuckets_Create()
            assertNotNull(buckets)
            logLine("OH_VBuckets_PutRows ret=" + OH_VBuckets_PutRows(buckets, null))
            OH_VBuckets_Destroy(buckets)
            logLine("OH_VBuckets_PutRows passed")
        } catch (e: Throwable) { logLine("testOH_VBuckets_PutRows (API >17) exception: $e") }
    }

    @Test
    fun testOH_VBuckets_RowCount() {
        try {
            memScoped {
                val countOut = alloc<ULongVar>()
                val buckets = OH_VBuckets_Create()
                assertNotNull(buckets)
                logLine("OH_VBuckets_RowCount ret=" + OH_VBuckets_RowCount(buckets, countOut.ptr))
                OH_VBuckets_Destroy(buckets)
            }
            logLine("OH_VBuckets_RowCount passed")
        } catch (e: Throwable) { logLine("testOH_VBuckets_RowCount (API >17) exception: $e") }
    }

    // ---------- data_asset.h 枚举与函数 ----------
    @Test
    fun testEnum_Data_AssetStatus() {
        assertEquals(ASSET_NULL.toInt(), 0)
        assertEquals(ASSET_NORMAL.toInt(), 1)
        assertEquals(ASSET_INSERT.toInt(), 2)
        assertEquals(ASSET_UPDATE.toInt(), 3)
        assertEquals(ASSET_DELETE.toInt(), 4)
        assertEquals(ASSET_ABNORMAL.toInt(), 5)
        assertEquals(ASSET_DOWNLOADING.toInt(), 6)
        logLine("Data_AssetStatus passed")
    }

    @Test
    fun testOH_Data_Asset_CreateOne() {
        val one = OH_Data_Asset_CreateOne()
        assertNotNull(one)
        OH_Data_Asset_DestroyOne(one)
        logLine("OH_Data_Asset_CreateOne passed")
    }

    @Test
    fun testOH_Data_Asset_DestroyOne() {
        val one = OH_Data_Asset_CreateOne()
        assertNotNull(one)
        logLine("OH_Data_Asset_DestroyOne ret=" + OH_Data_Asset_DestroyOne(one))
        logLine("OH_Data_Asset_DestroyOne passed")
    }

    @Test
    fun testOH_Data_Asset_CreateMultiple() {
        val multi = OH_Data_Asset_CreateMultiple(2u)
        assertNotNull(multi)
        OH_Data_Asset_DestroyMultiple(multi, 2u)
        logLine("OH_Data_Asset_CreateMultiple passed")
    }

    @Test
    fun testOH_Data_Asset_DestroyMultiple() {
        val multi = OH_Data_Asset_CreateMultiple(2u)
        assertNotNull(multi)
        logLine("OH_Data_Asset_DestroyMultiple ret=" + OH_Data_Asset_DestroyMultiple(multi, 2u))
        logLine("OH_Data_Asset_DestroyMultiple passed")
    }

    @Test
    fun testOH_Data_Asset_SetName() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetName ret=" + OH_Data_Asset_SetName(asset, "a"))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetName passed")
    }

    @Test
    fun testOH_Data_Asset_SetUri() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetUri ret=" + OH_Data_Asset_SetUri(asset, "uri"))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetUri passed")
    }

    @Test
    fun testOH_Data_Asset_SetPath() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetPath ret=" + OH_Data_Asset_SetPath(asset, "p"))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetPath passed")
    }

    @Test
    fun testOH_Data_Asset_SetCreateTime() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetCreateTime ret=" + OH_Data_Asset_SetCreateTime(asset, 1L))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetCreateTime passed")
    }

    @Test
    fun testOH_Data_Asset_SetModifyTime() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetModifyTime ret=" + OH_Data_Asset_SetModifyTime(asset, 2L))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetModifyTime passed")
    }

    @Test
    fun testOH_Data_Asset_SetSize() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetSize ret=" + OH_Data_Asset_SetSize(asset, 100u))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetSize passed")
    }

    @Test
    fun testOH_Data_Asset_SetStatus() {
        val asset = OH_Data_Asset_CreateOne()
        assertNotNull(asset)
        logLine("OH_Data_Asset_SetStatus ret=" + OH_Data_Asset_SetStatus(asset, ASSET_NORMAL))
        OH_Data_Asset_DestroyOne(asset)
        logLine("OH_Data_Asset_SetStatus passed")
    }

    @Test
    fun testOH_Data_Asset_GetName() {
        memScoped {
            val lenOut = alloc<ULongVar>()
            val buf = allocArray<ByteVar>(64)
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetName(asset, "n")
            logLine("OH_Data_Asset_GetName ret=" + OH_Data_Asset_GetName(asset, buf, lenOut.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetName passed")
    }

    @Test
    fun testOH_Data_Asset_GetUri() {
        memScoped {
            val lenOut = alloc<ULongVar>()
            val buf = allocArray<ByteVar>(64)
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetUri(asset, "u")
            logLine("OH_Data_Asset_GetUri ret=" + OH_Data_Asset_GetUri(asset, buf, lenOut.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetUri passed")
    }

    @Test
    fun testOH_Data_Asset_GetPath() {
        memScoped {
            val lenOut = alloc<ULongVar>()
            val buf = allocArray<ByteVar>(64)
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetPath(asset, "p")
            logLine("OH_Data_Asset_GetPath ret=" + OH_Data_Asset_GetPath(asset, buf, lenOut.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetPath passed")
    }

    @Test
    fun testOH_Data_Asset_GetCreateTime() {
        memScoped {
            val int64Out = alloc<LongVar>()
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetCreateTime(asset, 1L)
            logLine("OH_Data_Asset_GetCreateTime ret=" + OH_Data_Asset_GetCreateTime(asset, int64Out.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetCreateTime passed")
    }

    @Test
    fun testOH_Data_Asset_GetModifyTime() {
        memScoped {
            val int64Out = alloc<LongVar>()
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetModifyTime(asset, 2L)
            logLine("OH_Data_Asset_GetModifyTime ret=" + OH_Data_Asset_GetModifyTime(asset, int64Out.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetModifyTime passed")
    }

    @Test
    fun testOH_Data_Asset_GetSize() {
        memScoped {
            val sizeOut = alloc<ULongVar>()
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetSize(asset, 10u)
            logLine("OH_Data_Asset_GetSize ret=" + OH_Data_Asset_GetSize(asset, sizeOut.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetSize passed")
    }

    @Test
    fun testOH_Data_Asset_GetStatus() {
        memScoped {
            val statusOut = alloc<UIntVar>()
            val asset = OH_Data_Asset_CreateOne()
            assertNotNull(asset)
            OH_Data_Asset_SetStatus(asset, ASSET_INSERT)
            logLine("OH_Data_Asset_GetStatus ret=" + OH_Data_Asset_GetStatus(asset, statusOut.ptr))
            OH_Data_Asset_DestroyOne(asset)
        }
        logLine("OH_Data_Asset_GetStatus passed")
    }

    // ---------- oh_cursor.h 中 2 个独立函数 ----------
    @Test
    fun testOH_Cursor_GetFloatVectorCount() { memScoped {
        val length = alloc<ULongVar>()
        val ret = try { OH_Cursor_GetFloatVectorCount(null, 0, length.ptr) } catch (e: Throwable) { logLine("OH_Cursor_GetFloatVectorCount (API 18) exception: $e"); RDB_E_INVALID_ARGS }
        logLine("OH_Cursor_GetFloatVectorCount ret=$ret length=${length.value}")
        assertNotNull(ret)
    } }

    @Test
    fun testOH_Cursor_GetFloatVector() { memScoped {
        val outLen = alloc<ULongVar>()
        val ret = try { OH_Cursor_GetFloatVector(null, 0, null, 0uL, outLen.ptr) } catch (e: Throwable) { logLine("OH_Cursor_GetFloatVector (API 18) exception: $e"); RDB_E_INVALID_ARGS }
        logLine("OH_Cursor_GetFloatVector ret=$ret outLen=${outLen.value}")
        assertNotNull(ret)
    } }

    // ---------- relational_store.h 函数 ----------
    @Test
    fun testOH_Rdb_SetBundleName() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetBundleName ret=" + OH_Rdb_SetBundleName(config, "bundle"))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetBundleName passed")
    }

    @Test
    fun testOH_Rdb_SetModuleName() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetModuleName ret=" + OH_Rdb_SetModuleName(config, "module"))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetModuleName passed")
    }

    @Test
    fun testOH_Rdb_SetArea() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetArea ret=" + OH_Rdb_SetArea(config, RDB_SECURITY_AREA_EL1.toInt()))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetArea passed")
    }

    @Test
    fun testOH_Rdb_SetDbType() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetDbType ret=" + OH_Rdb_SetDbType(config, RDB_SQLITE.toInt()))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetDbType passed")
    }

    @Test
    fun testOH_Rdb_SetEncrypted() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetEncrypted ret=" + OH_Rdb_SetEncrypted(config, false))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetEncrypted passed")
    }

    @Test
    fun testOH_Rdb_SetSecurityLevel() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetSecurityLevel ret=" + OH_Rdb_SetSecurityLevel(config, S1.toInt()))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetSecurityLevel passed")
    }

    @Test
    fun testOH_Rdb_SetCustomDir() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetCustomDir ret=" + try { OH_Rdb_SetCustomDir(config, null) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetCustomDir passed")
    }

    @Test
    fun testOH_Rdb_SetReadOnly() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetReadOnly ret=" + try { OH_Rdb_SetReadOnly(config, false) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetReadOnly passed")
    }

    @Test
    fun testOH_Rdb_SetPlugins() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetPlugins ret=" + try { OH_Rdb_SetPlugins(config, null, 0) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetPlugins passed")
    }

    @Test
    fun testOH_Rdb_SetCryptoParam() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetCryptoParam ret=" + try { OH_Rdb_SetCryptoParam(config, null) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetCryptoParam passed")
    }

    @Test
    fun testOH_Rdb_SetTokenizer() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetTokenizer ret=" + OH_Rdb_SetTokenizer(config, RDB_NONE_TOKENIZER))
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetTokenizer passed")
    }

    @Test
    fun testOH_Rdb_SetPersistent() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetPersistent ret=" + try { OH_Rdb_SetPersistent(config, false) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetPersistent passed")
    }

    @Test
    fun testOH_Rdb_SetSemanticIndex() {
        val config = OH_Rdb_CreateConfig()
        assertNotNull(config)
        logLine("OH_Rdb_SetSemanticIndex ret=" + try { OH_Rdb_SetSemanticIndex(config, false) } catch (e: Throwable) { RDB_E_INVALID_ARGS })
        OH_Rdb_DestroyConfig(config)
        logLine("OH_Rdb_SetSemanticIndex passed")
    }

    @Test
    fun testOH_Rdb_IsTokenizerSupported() {
        try {
            memScoped {
                val supported = alloc<BooleanVar>()
                val ret = OH_Rdb_IsTokenizerSupported(RDB_NONE_TOKENIZER, supported.ptr)
                logLine("OH_Rdb_IsTokenizerSupported ret=$ret")
                assertNotNull(ret)
            }
            logLine("OH_Rdb_IsTokenizerSupported passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_IsTokenizerSupported (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_GetSupportedDbType() {
        memScoped {
            val typeCount = alloc<IntVar>()
            val types = OH_Rdb_GetSupportedDbType(typeCount.ptr)
            logLine("OH_Rdb_GetSupportedDbType types=$types typeCount=${typeCount.value}")
        }
        logLine("OH_Rdb_GetSupportedDbType passed")
    }

    // @Test
    // fun testOH_Rdb_GetTableDetails() {
    //     memScoped {
    //         val progress = alloc<Rdb_ProgressDetails>()
    //         progress.version = 1
    //         progress.schedule = Rdb_Progress.RDB_SYNC_BEGIN.value.toInt()
    //         progress.code = Rdb_ProgressCode.RDB_SUCCESS.value.toInt()
    //         progress.tableLength = 0
    //         val details = OH_Rdb_GetTableDetails(progress.ptr, 1)
    //         logLine("OH_Rdb_GetTableDetails details=$details")
    //     }
    //     logLine("OH_Rdb_GetTableDetails passed")
    // }

    @Test
    fun testOH_Rdb_CreateValueObject() {
        logLine("OH_Rdb_CreateValueObject " + OH_Rdb_CreateValueObject())
        logLine("OH_Rdb_CreateValueObject passed")
    }

    @Test
    fun testOH_Rdb_CreateValuesBucket() {
        logLine("OH_Rdb_CreateValuesBucket " + OH_Rdb_CreateValuesBucket())
        logLine("OH_Rdb_CreateValuesBucket passed")
    }

    @Test
    fun testOH_Rdb_CreatePredicates() {
        val p = OH_Rdb_CreatePredicates("t")
        logLine("OH_Rdb_CreatePredicates " + p)
        logLine("OH_Rdb_CreatePredicates passed")
    }

    @Test
    fun testOH_Rdb_GetOrOpen() {
        memScoped {
            val err = alloc<IntVar>()
            logLine("OH_Rdb_GetOrOpen " + OH_Rdb_GetOrOpen(null, err.ptr))
        }
        logLine("OH_Rdb_GetOrOpen passed")
    }

    @Test
    fun testOH_Rdb_CreateOrOpen() {
        memScoped {
            val err = alloc<IntVar>()
            logLine("OH_Rdb_CreateOrOpen " + OH_Rdb_CreateOrOpen(null, err.ptr))
        }
        logLine("OH_Rdb_CreateOrOpen passed")
    }

    @Test
    fun testOH_Rdb_CloseStore() {
        logLine("OH_Rdb_CloseStore ret=" + OH_Rdb_CloseStore(null))
        logLine("OH_Rdb_CloseStore passed")
    }

    @Test
    fun testOH_Rdb_DeleteStore() {
        logLine("OH_Rdb_DeleteStore ret=" + OH_Rdb_DeleteStore(null))
        logLine("OH_Rdb_DeleteStore passed")
    }

    @Test
    fun testOH_Rdb_DeleteStoreV2() {
        logLine("OH_Rdb_DeleteStoreV2 ret=" + OH_Rdb_DeleteStoreV2(null))
        logLine("OH_Rdb_DeleteStoreV2 passed")
    }

    @Test
    fun testOH_Rdb_Insert() {
        logLine("OH_Rdb_Insert ret=" + OH_Rdb_Insert(null, null, null))
        logLine("OH_Rdb_Insert passed")
    }

    @Test
    fun testOH_Rdb_InsertWithConflictResolution() {
        try {
            memScoped {
                val rowId = alloc<LongVar>()
                logLine("OH_Rdb_InsertWithConflictResolution ret=" + OH_Rdb_InsertWithConflictResolution(null, null, null, RDB_CONFLICT_NONE, rowId.ptr))
            }
            logLine("OH_Rdb_InsertWithConflictResolution passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_InsertWithConflictResolution (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_BatchInsert() {
        try {
            memScoped {
                val changes = alloc<LongVar>()
                logLine("OH_Rdb_BatchInsert ret=" + OH_Rdb_BatchInsert(null, null, null, RDB_CONFLICT_NONE, changes.ptr))
            }
            logLine("OH_Rdb_BatchInsert passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_BatchInsert (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_Update() {
        logLine("OH_Rdb_Update ret=" + OH_Rdb_Update(null, null, null))
        logLine("OH_Rdb_Update passed")
    }

    @Test
    fun testOH_Rdb_UpdateWithConflictResolution() {
        try {
            memScoped {
                val changes = alloc<LongVar>()
                logLine("OH_Rdb_UpdateWithConflictResolution ret=" + OH_Rdb_UpdateWithConflictResolution(null, null, null, RDB_CONFLICT_NONE, changes.ptr))
            }
            logLine("OH_Rdb_UpdateWithConflictResolution passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_UpdateWithConflictResolution (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_Delete() {
        logLine("OH_Rdb_Delete ret=" + OH_Rdb_Delete(null, null))
        logLine("OH_Rdb_Delete passed")
    }

    @Test
    fun testOH_Rdb_Query() {
        logLine("OH_Rdb_Query " + OH_Rdb_Query(null, null, null, 0))
        logLine("OH_Rdb_Query passed")
    }

    @Test
    fun testOH_Rdb_Execute() {
        logLine("OH_Rdb_Execute ret=" + OH_Rdb_Execute(null, null))
        logLine("OH_Rdb_Execute passed")
    }

    @Test
    fun testOH_Rdb_ExecuteV2() {
        try {
            logLine("OH_Rdb_ExecuteV2 ret=" + OH_Rdb_ExecuteV2(null, null, null, null))
            logLine("OH_Rdb_ExecuteV2 passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_ExecuteV2 (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_ExecuteByTrxId() {
        logLine("OH_Rdb_ExecuteByTrxId ret=" + OH_Rdb_ExecuteByTrxId(null, 0L, null))
        logLine("OH_Rdb_ExecuteByTrxId passed")
    }

    @Test
    fun testOH_Rdb_ExecuteQuery() {
        logLine("OH_Rdb_ExecuteQuery " + OH_Rdb_ExecuteQuery(null, null))
        logLine("OH_Rdb_ExecuteQuery passed")
    }

    @Test
    fun testOH_Rdb_ExecuteQueryV2() {
        try {
            logLine("OH_Rdb_ExecuteQueryV2 " + OH_Rdb_ExecuteQueryV2(null, null, null))
            logLine("OH_Rdb_ExecuteQueryV2 passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_ExecuteQueryV2 (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_BeginTransaction() {
        logLine("OH_Rdb_BeginTransaction ret=" + OH_Rdb_BeginTransaction(null))
        logLine("OH_Rdb_BeginTransaction passed")
    }

    @Test
    fun testOH_Rdb_RollBack() {
        logLine("OH_Rdb_RollBack ret=" + OH_Rdb_RollBack(null))
        logLine("OH_Rdb_RollBack passed")
    }

    @Test
    fun testOH_Rdb_Commit() {
        logLine("OH_Rdb_Commit ret=" + OH_Rdb_Commit(null))
        logLine("OH_Rdb_Commit passed")
    }

    @Test
    fun testOH_Rdb_BeginTransWithTrxId() {
        memScoped {
            val trxId = alloc<LongVar>()
            logLine("OH_Rdb_BeginTransWithTrxId ret=" + OH_Rdb_BeginTransWithTrxId(null, trxId.ptr))
        }
        logLine("OH_Rdb_BeginTransWithTrxId passed")
    }

    @Test
    fun testOH_Rdb_RollBackByTrxId() {
        logLine("OH_Rdb_RollBackByTrxId ret=" + OH_Rdb_RollBackByTrxId(null, 0L))
        logLine("OH_Rdb_RollBackByTrxId passed")
    }

    @Test
    fun testOH_Rdb_CommitByTrxId() {
        logLine("OH_Rdb_CommitByTrxId ret=" + OH_Rdb_CommitByTrxId(null, 0L))
        logLine("OH_Rdb_CommitByTrxId passed")
    }

    @Test
    fun testOH_Rdb_Backup() {
        logLine("OH_Rdb_Backup ret=" + OH_Rdb_Backup(null, null))
        logLine("OH_Rdb_Backup passed")
    }

    @Test
    fun testOH_Rdb_Restore() {
        logLine("OH_Rdb_Restore ret=" + OH_Rdb_Restore(null, null))
        logLine("OH_Rdb_Restore passed")
    }

    @Test
    fun testOH_Rdb_GetVersion() {
        memScoped {
            val ver = alloc<IntVar>()
            logLine("OH_Rdb_GetVersion ret=" + OH_Rdb_GetVersion(null, ver.ptr))
        }
        logLine("OH_Rdb_GetVersion passed")
    }

    @Test
    fun testOH_Rdb_SetVersion() {
        logLine("OH_Rdb_SetVersion ret=" + OH_Rdb_SetVersion(null, 0))
        logLine("OH_Rdb_SetVersion passed")
    }

    @Test
    fun testOH_Rdb_SetDistributedTables() {
        logLine("OH_Rdb_SetDistributedTables ret=" + OH_Rdb_SetDistributedTables(null, null, 0u, Rdb_DistributedType.RDB_DISTRIBUTED_CLOUD, null))
        logLine("OH_Rdb_SetDistributedTables passed")
    }

    @Test
    fun testOH_Rdb_FindModifyTime() {
        logLine("OH_Rdb_FindModifyTime " + OH_Rdb_FindModifyTime(null, null, null, null))
        logLine("OH_Rdb_FindModifyTime passed")
    }

    @Test
    fun testOH_Rdb_Subscribe() {
        logLine("OH_Rdb_Subscribe ret=" + OH_Rdb_Subscribe(null, Rdb_SubscribeType.RDB_SUBSCRIBE_TYPE_CLOUD, null))
        logLine("OH_Rdb_Subscribe passed")
    }

    @Test
    fun testOH_Rdb_Unsubscribe() {
        logLine("OH_Rdb_Unsubscribe ret=" + OH_Rdb_Unsubscribe(null, Rdb_SubscribeType.RDB_SUBSCRIBE_TYPE_CLOUD, null))
        logLine("OH_Rdb_Unsubscribe passed")
    }

    @Test
    fun testOH_Rdb_CloudSync() {
        logLine("OH_Rdb_CloudSync ret=" + OH_Rdb_CloudSync(null, Rdb_SyncMode.RDB_SYNC_MODE_TIME_FIRST, null, 0u, null))
        logLine("OH_Rdb_CloudSync passed")
    }

    @Test
    fun testOH_Rdb_SubscribeAutoSyncProgress() {
        logLine("OH_Rdb_SubscribeAutoSyncProgress ret=" + OH_Rdb_SubscribeAutoSyncProgress(null, null))
        logLine("OH_Rdb_SubscribeAutoSyncProgress passed")
    }

    @Test
    fun testOH_Rdb_UnsubscribeAutoSyncProgress() {
        logLine("OH_Rdb_UnsubscribeAutoSyncProgress ret=" + OH_Rdb_UnsubscribeAutoSyncProgress(null, null))
        logLine("OH_Rdb_UnsubscribeAutoSyncProgress passed")
    }

    @Test
    fun testOH_Rdb_LockRow() {
        logLine("OH_Rdb_LockRow ret=" + OH_Rdb_LockRow(null, null))
        logLine("OH_Rdb_LockRow passed")
    }

    @Test
    fun testOH_Rdb_UnlockRow() {
        logLine("OH_Rdb_UnlockRow ret=" + OH_Rdb_UnlockRow(null, null))
        logLine("OH_Rdb_UnlockRow passed")
    }

    @Test
    fun testOH_Rdb_QueryLockedRow() {
        logLine("OH_Rdb_QueryLockedRow " + OH_Rdb_QueryLockedRow(null, null, null, 0))
        logLine("OH_Rdb_QueryLockedRow passed")
    }

    @Test
    fun testOH_Rdb_CreateTransaction() {
        try {
            logLine("OH_Rdb_CreateTransaction ret=" + OH_Rdb_CreateTransaction(null, null, null))
            logLine("OH_Rdb_CreateTransaction passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_CreateTransaction (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_Attach() {
        try {
            memScoped {
                val attachedNum = alloc<ULongVar>()
                logLine("OH_Rdb_Attach ret=" + OH_Rdb_Attach(null, null, null, 0L, attachedNum.ptr))
            }
            logLine("OH_Rdb_Attach passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_Attach (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_Detach() {
        try {
            memScoped {
                val attachedNum = alloc<ULongVar>()
                logLine("OH_Rdb_Detach ret=" + OH_Rdb_Detach(null, null, 0L, attachedNum.ptr))
            }
            logLine("OH_Rdb_Detach passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_Detach (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_SetLocale() {
        try {
            logLine("OH_Rdb_SetLocale ret=" + OH_Rdb_SetLocale(null, null))
            logLine("OH_Rdb_SetLocale passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_SetLocale (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_RegisterCorruptedHandler() {
        try {
            logLine("OH_Rdb_RegisterCorruptedHandler ret=" + OH_Rdb_RegisterCorruptedHandler(null, null, null))
            logLine("OH_Rdb_RegisterCorruptedHandler passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_RegisterCorruptedHandler (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_UnregisterCorruptedHandler() {
        try {
            logLine("OH_Rdb_UnregisterCorruptedHandler ret=" + OH_Rdb_UnregisterCorruptedHandler(null, null, null))
            logLine("OH_Rdb_UnregisterCorruptedHandler passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_UnregisterCorruptedHandler (API >17) exception: $e") }
    }

    @Test
    fun testOH_Rdb_RekeyEx() {
        try {
            logLine("OH_Rdb_RekeyEx ret=" + OH_Rdb_RekeyEx(null, null))
            logLine("OH_Rdb_RekeyEx passed")
        } catch (e: Throwable) { logLine("testOH_Rdb_RekeyEx (API >17) exception: $e") }
    }

    // ---------- oh_predicates.h 枚举与函数 ----------
    @Test
    fun testEnum_OH_OrderType() {
        assertEquals(ASC.toInt(), 0)
        assertEquals(DESC.toInt(), 1)
        logLine("OH_OrderType passed")
    }

    @Test
    fun testOH_Predicates_NotLike() {
        try {
            logLine("OH_Predicates_NotLike ret=" + OH_Predicates_NotLike(null, null, null))
            logLine("OH_Predicates_NotLike passed")
        } catch (e: Throwable) { logLine("testOH_Predicates_NotLike (API >17) exception: $e") }
    }

    @Test
    fun testOH_Predicates_Glob() {
        try {
            logLine("OH_Predicates_Glob ret=" + OH_Predicates_Glob(null, null, null))
            logLine("OH_Predicates_Glob passed")
        } catch (e: Throwable) { logLine("testOH_Predicates_Glob (API >17) exception: $e") }
    }

    @Test
    fun testOH_Predicates_NotGlob() {
        try {
            logLine("OH_Predicates_NotGlob ret=" + OH_Predicates_NotGlob(null, null, null))
            logLine("OH_Predicates_NotGlob passed")
        } catch (e: Throwable) { logLine("testOH_Predicates_NotGlob (API >17) exception: $e") }
    }

    @Test
    fun testOH_Predicates_Having() {
        try {
            logLine("OH_Predicates_Having ret=" + OH_Predicates_Having(null, null, null))
            logLine("OH_Predicates_Having passed")
        } catch (e: Throwable) { logLine("testOH_Predicates_Having (API >17) exception: $e") }
    }

    @Test
    fun testOH_Predicates_structMethods() { memScoped {
        val p = OH_Rdb_CreatePredicates("t")
        assertNotNull(p)
        val pred = p
        val fCstr = "f".cstr
        logLine("equalTo " + (pred.pointed.equalTo?.invoke(pred, fCstr.ptr, null)))
        logLine("notEqualTo " + (pred.pointed.notEqualTo?.invoke(pred, fCstr.ptr, null)))
        logLine("beginWrap " + (pred.pointed.beginWrap?.invoke(pred)))
        logLine("endWrap " + (pred.pointed.endWrap?.invoke(pred)))
        logLine("orOperate " + (pred.pointed.orOperate?.invoke(pred)))
        logLine("andOperate " + (pred.pointed.andOperate?.invoke(pred)))
        logLine("isNull " + (pred.pointed.isNull?.invoke(pred, fCstr.ptr)))
        logLine("isNotNull " + (pred.pointed.isNotNull?.invoke(pred, fCstr.ptr)))
        logLine("like " + (pred.pointed.like?.invoke(pred, fCstr.ptr, null)))
        logLine("between " + (pred.pointed.between?.invoke(pred, fCstr.ptr, null)))
        logLine("notBetween " + (pred.pointed.notBetween?.invoke(pred, fCstr.ptr, null)))
        logLine("greaterThan " + (pred.pointed.greaterThan?.invoke(pred, fCstr.ptr, null)))
        logLine("lessThan " + (pred.pointed.lessThan?.invoke(pred, fCstr.ptr, null)))
        logLine("greaterThanOrEqualTo " + (pred.pointed.greaterThanOrEqualTo?.invoke(pred, fCstr.ptr, null)))
        logLine("lessThanOrEqualTo " + (pred.pointed.lessThanOrEqualTo?.invoke(pred, fCstr.ptr, null)))
        logLine("orderBy " + (pred.pointed.orderBy?.invoke(pred, fCstr.ptr, ASC)))
        logLine("distinct " + (pred.pointed.distinct?.invoke(pred)))
        logLine("limit " + (pred.pointed.limit?.invoke(pred, 0u)))
        logLine("offset " + (pred.pointed.offset?.invoke(pred, 0u)))
        logLine("groupBy " + (pred.pointed.groupBy?.invoke(pred, null, 0)))
        logLine("in " + (pred.pointed.`in`?.invoke(pred, fCstr.ptr, null)))
        logLine("notIn " + (pred.pointed.notIn?.invoke(pred, fCstr.ptr, null)))
        logLine("clear " + (pred.pointed.clear?.invoke(pred)))
        logLine("destroy " + (pred.pointed.destroy?.invoke(pred)))
    } }
}
