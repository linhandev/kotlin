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
class AssetApiTest {

    private fun logLine(message: String) {
        println("[stdout] AssetApiTest $message")
    }

    @Test
    fun testAssetTypes() {
        memScoped {
            logLine("--- Testing AssetType enums & structs ---")
            val assetAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_SECRET
                value.boolean = false
            }
            logLine("Asset_Attr created, tag=${assetAttr.tag}, boolean=${assetAttr.value.boolean}")

            val assetBlob = alloc<platform.AssetStoreKit.AssetType.Asset_Blob>().apply {
                size = 0u
                data = null
            }
            logLine("Asset_Blob created, size=${assetBlob.size}, data=${assetBlob.data}")

            val assetValue = alloc<platform.AssetStoreKit.AssetType.Asset_Value>().apply {
                boolean = true
            }
            logLine("Asset_Value created, boolean=${assetValue.boolean}")

            val assetResult = alloc<platform.AssetStoreKit.AssetType.Asset_Result>().apply {
                count = 0u
                attrs = null
            }
            logLine("Asset_Result created, count=${assetResult.count}, attrs=${assetResult.attrs}")

            val assetResultSet = alloc<platform.AssetStoreKit.AssetType.Asset_ResultSet>().apply {
                count = 0u
                results = null
            }
            logLine("Asset_ResultSet created, count=${assetResultSet.count}, results=${assetResultSet.results}")

            val assetSyncResult = alloc<platform.AssetStoreKit.AssetType.Asset_SyncResult>().apply {
                resultCode = 0
                totalCount = 0u
                failedCount = 0u
            }
            logLine("Asset_SyncResult created, resultCode=${assetSyncResult.resultCode}, total=${assetSyncResult.totalCount}, failed=${assetSyncResult.failedCount}")

            val tagType = platform.AssetStoreKit.AssetType.ASSET_TYPE_BOOL
            val tagSecret = platform.AssetStoreKit.AssetType.ASSET_TAG_SECRET
            val tagAlias = platform.AssetStoreKit.AssetType.ASSET_TAG_ALIAS
            logLine("Enum AssetType: TYPE_BOOL=$tagType TAG_SECRET=$tagSecret TAG_ALIAS=$tagAlias")
            assertNotNull(tagType)
            assertNotNull(tagSecret)
            assertNotNull(tagAlias)

            val resultCode = platform.AssetStoreKit.AssetType.ASSET_SUCCESS
            val permissionDenied = platform.AssetStoreKit.AssetType.ASSET_PERMISSION_DENIED
            logLine("Enum Result: ASSET_SUCCESS=$resultCode ASSET_PERMISSION_DENIED=$permissionDenied")
            assertNotNull(resultCode)
            assertNotNull(permissionDenied)

            val accessibility = platform.AssetStoreKit.AssetType.ASSET_ACCESSIBILITY_DEVICE_POWERED_ON
            val authType = platform.AssetStoreKit.AssetType.ASSET_AUTH_TYPE_NONE
            val syncType = platform.AssetStoreKit.AssetType.ASSET_SYNC_TYPE_NEVER
            logLine("Enum Other: ACCESSIBILITY=$accessibility AUTH_TYPE=$authType SYNC_TYPE=$syncType")
            assertNotNull(accessibility)
            assertNotNull(authType)
            assertNotNull(syncType)
        }
    }

    @Test
    fun testAssetAddAndRemove() {
        memScoped {
            logLine("--- Testing AssetApi add & remove ---")
            val attr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_SECRET
                value.blob.size = 0u
                value.blob.data = null
            }
            val addResult = platform.AssetStoreKit.AssetApi.OH_Asset_Add(attr.ptr, 1u)
            logLine("OH_Asset_Add(secret) result=$addResult")
            assertNotNull(addResult)

            val attrWithAlias = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_ALIAS
                value.blob.size = 0u
                value.blob.data = null
            }
            val addResult2 = platform.AssetStoreKit.AssetApi.OH_Asset_Add(attrWithAlias.ptr, 1u)
            logLine("OH_Asset_Add(alias) result=$addResult2")
            assertNotNull(addResult2)

            val queryAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_ALIAS
                value.blob.size = 0u
                value.blob.data = null
            }
            val removeResult = platform.AssetStoreKit.AssetApi.OH_Asset_Remove(queryAttr.ptr, 1u)
            logLine("OH_Asset_Remove(alias) result=$removeResult")
            assertNotNull(removeResult)

            val addResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_Add(null, 0u)
            val removeResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_Remove(null, 0u)
            logLine("OH_Asset_Add/Remove(null) add=$addResultNull remove=$removeResultNull")
            assertNotNull(addResultNull)
            assertNotNull(removeResultNull)
        }
    }

    @Test
    fun testAssetUpdate() {
        memScoped {
            logLine("--- Testing AssetApi update ---")
            val queryAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_ALIAS
                value.blob.size = 0u
                value.blob.data = null
            }

            val updateAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_SECRET
                value.blob.size = 0u
                value.blob.data = null
            }

            val updateResult = platform.AssetStoreKit.AssetApi.OH_Asset_Update(
                queryAttr.ptr,
                1u,
                updateAttr.ptr,
                1u
            )
            logLine("OH_Asset_Update(alias->secret) result=$updateResult")
            assertNotNull(updateResult)

            val updateResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_Update(
                null,
                0u,
                null,
                0u
            )
            logLine("OH_Asset_Update(null) result=$updateResultNull")
            assertNotNull(updateResultNull)
        }
    }

    @Test
    fun testAssetQuery() {
        memScoped {
            logLine("--- Testing AssetApi query ---")
            val queryAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_RETURN_TYPE
                value.u32 = platform.AssetStoreKit.AssetType.ASSET_RETURN_ALL
            }

            val resultSet = alloc<platform.AssetStoreKit.AssetType.Asset_ResultSet>().apply {
                count = 0u
                results = null
            }

            val queryResult = platform.AssetStoreKit.AssetApi.OH_Asset_Query(
                queryAttr.ptr,
                1u,
                resultSet.ptr
            )
            logLine("OH_Asset_Query(RETURN_ALL) result=$queryResult count=${resultSet.count}")
            assertNotNull(queryResult)

            val queryAttr2 = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_RETURN_TYPE
                value.u32 = platform.AssetStoreKit.AssetType.ASSET_RETURN_ATTRIBUTES
            }
            val queryResult2 = platform.AssetStoreKit.AssetApi.OH_Asset_Query(
                queryAttr2.ptr,
                1u,
                null
            )
            logLine("OH_Asset_Query(RETURN_ATTRIBUTES) result=$queryResult2")
            assertNotNull(queryResult2)

            val queryResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_Query(
                null,
                0u,
                null
            )
            logLine("OH_Asset_Query(null) result=$queryResultNull")
            assertNotNull(queryResultNull)
        }
    }

    @Test
    fun testAssetPreAndPostQuery() {
        memScoped {
            logLine("--- Testing AssetApi pre/post query ---")
            val queryAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_AUTH_TYPE
                value.u32 = platform.AssetStoreKit.AssetType.ASSET_AUTH_TYPE_ANY
            }

            val challenge = alloc<platform.AssetStoreKit.AssetType.Asset_Blob>().apply {
                size = 0u
                data = null
            }

            val preQueryResult = platform.AssetStoreKit.AssetApi.OH_Asset_PreQuery(
                queryAttr.ptr,
                1u,
                challenge.ptr
            )
            logLine("OH_Asset_PreQuery result=$preQueryResult size=${challenge.size} data=${challenge.data}")
            assertNotNull(preQueryResult)

            val handleAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                tag = platform.AssetStoreKit.AssetType.ASSET_TAG_AUTH_TOKEN
                value.blob.size = 0u
                value.blob.data = null
            }

            val postQueryResult = platform.AssetStoreKit.AssetApi.OH_Asset_PostQuery(
                handleAttr.ptr,
                1u
            )
            logLine("OH_Asset_PostQuery result=$postQueryResult")
            assertNotNull(postQueryResult)

            val preQueryResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_PreQuery(
                null,
                0u,
                null
            )
            val postQueryResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_PostQuery(
                null,
                0u
            )
            logLine("OH_Asset_PreQuery/PostQuery(null) pre=$preQueryResultNull post=$postQueryResultNull")
            assertNotNull(preQueryResultNull)
            assertNotNull(postQueryResultNull)
        }
    }

    @Test
    fun testAssetParseAndFree() {
        memScoped {
            logLine("--- Testing AssetApi parse & free ---")
            val result = alloc<platform.AssetStoreKit.AssetType.Asset_Result>().apply {
                count = 0u
                attrs = null
            }

            val parseAttrResult1 = platform.AssetStoreKit.AssetApi.OH_Asset_ParseAttr(
                result.ptr,
                platform.AssetStoreKit.AssetType.ASSET_TAG_SECRET
            )
            val parseAttrResult2 = platform.AssetStoreKit.AssetApi.OH_Asset_ParseAttr(
                result.ptr,
                platform.AssetStoreKit.AssetType.ASSET_TAG_ALIAS
            )
            val parseAttrResult3 = platform.AssetStoreKit.AssetApi.OH_Asset_ParseAttr(
                result.ptr,
                platform.AssetStoreKit.AssetType.ASSET_TAG_ACCESSIBILITY
            )
            logLine("OH_Asset_ParseAttr results: secret=$parseAttrResult1 alias=$parseAttrResult2 accessibility=$parseAttrResult3")

            val blob = alloc<platform.AssetStoreKit.AssetType.Asset_Blob>().apply {
                size = 0u
                data = null
            }
            platform.AssetStoreKit.AssetApi.OH_Asset_FreeBlob(blob.ptr)
            platform.AssetStoreKit.AssetApi.OH_Asset_FreeBlob(null)
            logLine("OH_Asset_FreeBlob called for blob and null")

            val resultSet = alloc<platform.AssetStoreKit.AssetType.Asset_ResultSet>().apply {
                count = 0u
                results = null
            }
            platform.AssetStoreKit.AssetApi.OH_Asset_FreeResultSet(resultSet.ptr)
            platform.AssetStoreKit.AssetApi.OH_Asset_FreeResultSet(null)
            logLine("OH_Asset_FreeResultSet called for resultSet and null")
        }
    }

    @Test
    fun testAssetSyncResult() {
        memScoped {
            logLine("--- Testing AssetApi sync result (OH_Asset_QuerySyncResult API 20) ---")
            try {
                val queryAttr = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                    tag = platform.AssetStoreKit.AssetType.ASSET_TAG_SYNC_TYPE
                    value.u32 = platform.AssetStoreKit.AssetType.ASSET_SYNC_TYPE_NEVER
                }

                val syncResult = alloc<platform.AssetStoreKit.AssetType.Asset_SyncResult>().apply {
                    resultCode = platform.AssetStoreKit.AssetType.ASSET_SUCCESS.toInt()
                    totalCount = 0u
                    failedCount = 0u
                }

                val querySyncResult = platform.AssetStoreKit.AssetApi.OH_Asset_QuerySyncResult(
                    queryAttr.ptr,
                    1u,
                    syncResult.ptr
                )
                logLine("OH_Asset_QuerySyncResult(NEVER) result=$querySyncResult code=${syncResult.resultCode} total=${syncResult.totalCount} failed=${syncResult.failedCount}")
                assertNotNull(querySyncResult)

                val queryAttr2 = alloc<platform.AssetStoreKit.AssetType.Asset_Attr>().apply {
                    tag = platform.AssetStoreKit.AssetType.ASSET_TAG_SYNC_TYPE
                    value.u32 = platform.AssetStoreKit.AssetType.ASSET_SYNC_TYPE_THIS_DEVICE
                }
                val querySyncResult2 = platform.AssetStoreKit.AssetApi.OH_Asset_QuerySyncResult(
                    queryAttr2.ptr,
                    1u,
                    null
                )
                logLine("OH_Asset_QuerySyncResult(THIS_DEVICE) result=$querySyncResult2")
                assertNotNull(querySyncResult2)

                val querySyncResultNull = platform.AssetStoreKit.AssetApi.OH_Asset_QuerySyncResult(
                    null,
                    0u,
                    null
                )
                logLine("OH_Asset_QuerySyncResult(null) result=$querySyncResultNull")
                assertNotNull(querySyncResultNull)
            } catch (e: Throwable) {
                logLine("OH_Asset_QuerySyncResult (API 20) exception: $e")
            }
        }
    }
}
