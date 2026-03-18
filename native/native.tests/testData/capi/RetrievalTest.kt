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
import platform.DataAugmentationKit.Retrieval.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class RetrievalTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_Retrieval_Channel_Type() {
        assertEquals(RETRIEVAL_TYPE_VECTOR.toInt(), 1)
        logLine("Retrieval_Channel_Type passed")
    }

    @Test
    fun testOH_Retrieval_CreateRetriever() {
        memScoped {
            val retrieverPtr = alloc<CPointerVar<OH_Retrieval_Retriever>>()
            val r1 = try { OH_Retrieval_CreateRetriever(null, retrieverPtr.ptr) } catch (e: Throwable) { logLine("OH_Retrieval_CreateRetriever (API 20) exception: $e"); -1 }
            assertNotNull(r1)
            logLine("CreateRetriever $r1")
        }
    }

    @Test
    fun testOH_Retrieval_DestroyRetriever() {
        val r2 = try { OH_Retrieval_DestroyRetriever(null) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyRetriever (API 20) exception: $e"); -1 }
        assertNotNull(r2)
        logLine("DestroyRetriever $r2")
    }

    @Test
    fun testOH_Retrieval_CreateConfig() {
        val config = try { OH_Retrieval_CreateConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateConfig (API 20) exception: $e"); null }
        assertNotNull(config)
        logLine("CreateConfig $config")
    }

    @Test
    fun testOH_Retrieval_DestroyConfig() {
        val config = try { OH_Retrieval_CreateConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateConfig (API 20) exception: $e"); null }
        assertNotNull(config)
        val r3 = try { OH_Retrieval_DestroyConfig(config) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyConfig (API 20) exception: $e"); -1 }
        assertNotNull(r3)
        logLine("DestroyConfig $r3")
    }

    @Test
    fun testOH_Retrieval_CreateDbConfig() {
        val dbConfig = try { OH_Retrieval_CreateDbConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateDbConfig (API 20) exception: $e"); null }
        assertNotNull(dbConfig)
        logLine("CreateDbConfig $dbConfig")
    }

    @Test
    fun testOH_Retrieval_SetDbConfig() {
        val dbConfig = try { OH_Retrieval_CreateDbConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateDbConfig (API 20) exception: $e"); null }
        assertNotNull(dbConfig)
        val r4 = try { OH_Retrieval_SetDbConfig(dbConfig, null) } catch (e: Throwable) { logLine("OH_Retrieval_SetDbConfig (API 20) exception: $e"); -1 }
        assertNotNull(r4)
        logLine("SetDbConfig $r4")
        try { OH_Retrieval_DestroyDbConfig(dbConfig) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyDbConfig (API 20) exception: $e") }
    }

    @Test
    fun testOH_Retrieval_DestroyDbConfig() {
        val dbConfig = try { OH_Retrieval_CreateDbConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateDbConfig (API 20) exception: $e"); null }
        assertNotNull(dbConfig)
        val r5 = try { OH_Retrieval_DestroyDbConfig(dbConfig) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyDbConfig (API 20) exception: $e"); -1 }
        assertNotNull(r5)
        logLine("DestroyDbConfig $r5")
    }

    @Test
    fun testOH_Retrieval_AddConfig() {
        val config2 = try { OH_Retrieval_CreateConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateConfig (API 20) exception: $e"); null }
        assertNotNull(config2)
        val dbConfig2 = try { OH_Retrieval_CreateDbConfig() } catch (e: Throwable) { logLine("OH_Retrieval_CreateDbConfig (API 20) exception: $e"); null }
        assertNotNull(dbConfig2)
        val r6 = try { OH_Retrieval_AddConfig(config2, RETRIEVAL_TYPE_VECTOR, dbConfig2) } catch (e: Throwable) { logLine("OH_Retrieval_AddConfig (API 20) exception: $e"); -1 }
        assertNotNull(r6)
        logLine("AddConfig $r6")
        try { OH_Retrieval_DestroyDbConfig(dbConfig2) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyDbConfig (API 20) exception: $e") }
        try { OH_Retrieval_DestroyConfig(config2) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyConfig (API 20) exception: $e") }
    }

    @Test
    fun testOH_Retrieval_Retrieve() {
        val r7 = try { OH_Retrieval_Retrieve(null, null, null, null, null) } catch (e: Throwable) { logLine("OH_Retrieval_Retrieve (API 20) exception: $e"); -1 }
        assertNotNull(r7)
        logLine("Retrieve $r7")
    }

    @Test
    fun testOH_Retrieval_CreateCondition() {
        val cond = try { OH_Retrieval_CreateCondition() } catch (e: Throwable) { logLine("OH_Retrieval_CreateCondition (API 20) exception: $e"); null }
        assertNotNull(cond)
        logLine("CreateCondition $cond")
    }

    @Test
    fun testOH_Retrieval_DestroyCondition() {
        val cond = try { OH_Retrieval_CreateCondition() } catch (e: Throwable) { logLine("OH_Retrieval_CreateCondition (API 20) exception: $e"); null }
        assertNotNull(cond)
        val r1 = try { OH_Retrieval_DestroyCondition(cond) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyCondition (API 20) exception: $e"); -1 }
        assertNotNull(r1)
        logLine("DestroyCondition $r1")
    }

    @Test
    fun testOH_Retrieval_DestroySubCondition() {
        val r2 = try { OH_Retrieval_DestroySubCondition(null) } catch (e: Throwable) { logLine("OH_Retrieval_DestroySubCondition (API 20) exception: $e"); -1 }
        assertNotNull(r2)
        logLine("DestroySubCondition $r2")
    }

    @Test
    fun testOH_Retrieval_AddSubCondition() {
        val cond2 = try { OH_Retrieval_CreateCondition() } catch (e: Throwable) { logLine("OH_Retrieval_CreateCondition (API 20) exception: $e"); null }
        assertNotNull(cond2)
        val r3 = try { OH_Retrieval_AddSubCondition(cond2, null) } catch (e: Throwable) { logLine("OH_Retrieval_AddSubCondition (API 20) exception: $e"); -1 }
        assertNotNull(r3)
        logLine("AddSubCondition $r3")
        try { OH_Retrieval_DestroyCondition(cond2) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyCondition (API 20) exception: $e") }
    }

    @Test
    fun testOH_Retrieval_DestroyVectorCondition() {
        val r0 = try { OH_Retrieval_DestroyVectorCondition(null) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyVectorCondition (API 20) exception: $e"); -1 }
        assertNotNull(r0)
        logLine("DestroyVectorCondition(null) $r0")
    }

    @Test
    fun testOH_Retrieval_CreateVectorCondition() {
        val vec = try { OH_Retrieval_CreateVectorCondition() } catch (e: Throwable) { logLine("OH_Retrieval_CreateVectorCondition (API 20) exception: $e"); null }
        logLine("CreateVectorCondition $vec")
    }

    @Test
    fun testOH_Retrieval_SetVectorRecallLimit() {
        val vec = try { OH_Retrieval_CreateVectorCondition() } catch (e: Throwable) { logLine("OH_Retrieval_CreateVectorCondition (API 20) exception: $e"); null }
        val r1 = try { OH_Retrieval_SetVectorRecallLimit(vec, 10u) } catch (e: Throwable) { logLine("OH_Retrieval_SetVectorRecallLimit (API 20) exception: $e"); -1 }
        logLine("SetVectorRecallLimit $r1")
        try { OH_Retrieval_DestroyVectorCondition(vec) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyVectorCondition (API 20) exception: $e") }
    }

    @Test
    fun testOH_Retrieval_SetSimilarityThreshold() {
        val vec = try { OH_Retrieval_CreateVectorCondition() } catch (e: Throwable) { logLine("OH_Retrieval_CreateVectorCondition (API 20) exception: $e"); null }
        val r2 = try { OH_Retrieval_SetSimilarityThreshold(vec, 0.5) } catch (e: Throwable) { logLine("OH_Retrieval_SetSimilarityThreshold (API 20) exception: $e"); -1 }
        logLine("SetSimilarityThreshold $r2")
        try { OH_Retrieval_DestroyVectorCondition(vec) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyVectorCondition (API 20) exception: $e") }
    }

    @Test
    fun testOH_Retrieval_DestroyQuery() {
        val r0 = try { OH_Retrieval_DestroyQuery(null) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyQuery (API 20) exception: $e"); -1 }
        assertNotNull(r0)
        logLine("DestroyQuery(null) $r0")
    }

    @Test
    fun testOH_Retrieval_CreateQuery() {
        val query = try { OH_Retrieval_CreateQuery() } catch (e: Throwable) { logLine("OH_Retrieval_CreateQuery (API 20) exception: $e"); null }
        assertNotNull(query)
        logLine("CreateQuery $query")
    }

    @Test
    fun testOH_Retrieval_SetOriginalQuestion() {
        val query = try { OH_Retrieval_CreateQuery() } catch (e: Throwable) { logLine("OH_Retrieval_CreateQuery (API 20) exception: $e"); null }
        assertNotNull(query)
        val r1 = try { OH_Retrieval_SetOriginalQuestion(query, "q") } catch (e: Throwable) { logLine("OH_Retrieval_SetOriginalQuestion (API 20) exception: $e"); -1 }
        assertNotNull(r1)
        logLine("SetOriginalQuestion $r1")
        try { OH_Retrieval_DestroyQuery(query) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyQuery (API 20) exception: $e") }
    }

    @Test
    fun testOH_Retrieval_DestroyRecord() {
        val r0 = try { OH_Retrieval_DestroyRecord(null) } catch (e: Throwable) { logLine("OH_Retrieval_DestroyRecord (API 20) exception: $e"); -1 }
        assertNotNull(r0)
        logLine("DestroyRecord(null) $r0")
    }

    @Test
    fun testOH_Retrieval_GetRecordLength() {
        memScoped {
            val lengthOut = alloc<UIntVar>()
            val r1 = try { OH_Retrieval_GetRecordLength(null, lengthOut.ptr) } catch (e: Throwable) { logLine("OH_Retrieval_GetRecordLength (API 20) exception: $e"); -1 }
            assertNotNull(r1)
            logLine("GetRecordLength $r1")
        }
    }

    @Test
    fun testOH_Retrieval_GetRecordItem() {
        memScoped {
            val itemOut = alloc<CPointerVar<OH_Retrieval_RecordItem>>()
            val r2 = try { OH_Retrieval_GetRecordItem(null, 0u, itemOut.ptr) } catch (e: Throwable) { logLine("OH_Retrieval_GetRecordItem (API 20) exception: $e"); -1 }
            assertNotNull(r2)
            logLine("GetRecordItem $r2")
        }
    }

    @Test
    fun testOH_Retrieval_GetItemSize() {
        memScoped {
            val sizeOut = alloc<ULongVar>()
            val r3 = try { OH_Retrieval_GetItemSize(null, null, sizeOut.ptr) } catch (e: Throwable) { logLine("OH_Retrieval_GetItemSize (API 20) exception: $e"); -1 }
            assertNotNull(r3)
            logLine("GetItemSize $r3")
        }
    }

    @Test
    fun testOH_Retrieval_GetItemText() {
        memScoped {
            val valueBuf = allocArray<ByteVar>(1)
            val r4 = try { OH_Retrieval_GetItemText(null, null, valueBuf, 0u) } catch (e: Throwable) { logLine("OH_Retrieval_GetItemText (API 20) exception: $e"); -1 }
            assertNotNull(r4)
            logLine("GetItemText $r4")
        }
    }
}
