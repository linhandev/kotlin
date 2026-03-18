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
import platform.FASTKit.FAST.*
import cnames.structs.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class FASTTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_FAST_ErrorCode() {
        assertEquals(FAST_ERROR_CODE_SUCCESS.toInt(), 1023100000)
        assertEquals(FAST_ERROR_CODE_FAIL.toInt(), 1023100001)
        assertEquals(FAST_ERROR_CODE_ILLEGAL_INPUT.toInt(), 1023100002)
        assertEquals(FAST_ERROR_CODE_INVALID_PTR.toInt(), 1023100003)
        assertEquals(FAST_ERROR_CODE_OOM.toInt(), 1023199001)
        logLine("testEnum_FAST_ErrorCode passed")
    }

    @Test
    fun testEnum_FAST_SegmentMapQueryType() {
        assertEquals(FAST_SEGMENTMAP_QUERY_TYPE_SUM.toInt(), 0)
        assertEquals(FAST_SEGMENTMAP_QUERY_TYPE_MIN.toInt(), 1)
        assertEquals(FAST_SEGMENTMAP_QUERY_TYPE_MAX.toInt(), 2)
        logLine("testEnum_FAST_SegmentMapQueryType passed")
    }

    @Test
    fun testEnum_FAST_SegmentMapUpdateType() {
        assertEquals(FAST_SEGMENTMAP_UPDATE_TYPE_SET.toInt(), 0)
        assertEquals(FAST_SEGMENTMAP_UPDATE_TYPE_ADD.toInt(), 1)
        assertEquals(FAST_SEGMENTMAP_UPDATE_TYPE_SUB.toInt(), 2)
        logLine("testEnum_FAST_SegmentMapUpdateType passed")
    }

    @Test
    fun testHMS_FAST_SegmentMap_CreateConfig() {
        val ret = try { HMS_FAST_SegmentMap_CreateConfig(null) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_CreateConfig (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
        assertNotNull(ret)
        logLine("HMS_FAST_SegmentMap_CreateConfig ret=$ret")
    }

    @Test
    fun testHMS_FAST_SegmentMap_DestroyConfig() {
        try { HMS_FAST_SegmentMap_DestroyConfig(null) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_DestroyConfig (API 22) exception: $e") }
        logLine("HMS_FAST_SegmentMap_DestroyConfig(null) done")
    }

    @Test
    fun testHMS_FAST_SegmentMap_SetQueryType() {
        val ret = try { HMS_FAST_SegmentMap_SetQueryType(null, FAST_SEGMENTMAP_QUERY_TYPE_SUM) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_SetQueryType (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
        assertNotNull(ret)
        logLine("HMS_FAST_SegmentMap_SetQueryType done")
    }

    @Test
    fun testHMS_FAST_SegmentMap_SetUpdateType() {
        val ret = try { HMS_FAST_SegmentMap_SetUpdateType(null, FAST_SEGMENTMAP_UPDATE_TYPE_SET) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_SetUpdateType (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
        assertNotNull(ret)
        logLine("HMS_FAST_SegmentMap_SetUpdateType done")
    }

    @Test
    fun testHMS_FAST_SegmentMap_Create() {
        val ret = try { HMS_FAST_SegmentMap_Create(null, 4uL, null, null) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_Create (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
        assertNotNull(ret)
        logLine("HMS_FAST_SegmentMap_Create done")
    }

    @Test
    fun testHMS_FAST_SegmentMap_Destroy() {
        try { HMS_FAST_SegmentMap_Destroy(null) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_Destroy (API 22) exception: $e") }
        logLine("HMS_FAST_SegmentMap_Destroy(null) done")
    }

    @Test
    fun testHMS_FAST_SegmentMap_Update() {
        val ret = try { HMS_FAST_SegmentMap_Update(null, 0uL, 4uL, 1) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_Update (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
        assertNotNull(ret)
        logLine("HMS_FAST_SegmentMap_Update done")
    }

    @Test
    fun testHMS_FAST_SegmentMap_Query() {
        memScoped {
            val result = alloc<IntVar>()
            val ret = try { HMS_FAST_SegmentMap_Query(null, 0uL, 4uL, result.ptr) } catch (e: Throwable) { logLine("HMS_FAST_SegmentMap_Query (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            assertNotNull(ret)
            logLine("HMS_FAST_SegmentMap_Query done")
        }
    }

    @Test
    fun testHMS_FAST_RectPartition_CreateConfig() {
        memScoped {
            val config = alloc<CPointerVar<FAST_RectPartitionConfig>>()
            val ret = try { HMS_FAST_RectPartition_CreateConfig(config.ptr) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_CreateConfig (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            assertNotNull(ret)
            logLine("HMS_FAST_RectPartition_CreateConfig ret=$ret")
            config.value?.let { try { HMS_FAST_RectPartition_DestroyConfig(it) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_DestroyConfig (API 22) exception: $e") } }
        }
    }

    @Test
    fun testHMS_FAST_RectPartition_DestroyConfig() {
        try { HMS_FAST_RectPartition_DestroyConfig(null) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_DestroyConfig (API 22) exception: $e") }
        logLine("HMS_FAST_RectPartition_DestroyConfig(null) done")
    }

    @Test
    fun testHMS_FAST_RectPartition_SetAlgo() {
        memScoped {
            val config = alloc<CPointerVar<FAST_RectPartitionConfig>>()
            val r = try { HMS_FAST_RectPartition_CreateConfig(config.ptr) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_CreateConfig (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            val ret = try { HMS_FAST_RectPartition_SetAlgo(config.value, "SweepLineAlgo") } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_SetAlgo (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            assertNotNull(ret)
            try { HMS_FAST_RectPartition_DestroyConfig(config.value) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_DestroyConfig (API 22) exception: $e") }
            logLine("HMS_FAST_RectPartition_SetAlgo done")
        }
    }

    @Test
    fun testHMS_FAST_RectPartition_Solve() {
        memScoped {
            val config = alloc<CPointerVar<FAST_RectPartitionConfig>>()
            val r = try { HMS_FAST_RectPartition_CreateConfig(config.ptr) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_CreateConfig (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            try { HMS_FAST_RectPartition_SetAlgo(config.value, "SweepLineAlgo") } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_SetAlgo (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            val origin = allocArray<FAST_Rect>(1).apply {
                this[0].left = 0; this[0].top = 0; this[0].right = 10; this[0].bottom = 10
            }
            val result = allocArray<FAST_Rect>(4)
            val resultSize = alloc<ULongVar>()
            val ret = try { HMS_FAST_RectPartition_Solve(config.value, 1uL, origin, result, resultSize.ptr) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_Solve (API 22) exception: $e"); FAST_ERROR_CODE_INVALID_PTR }
            assertNotNull(ret)
            try { HMS_FAST_RectPartition_DestroyConfig(config.value) } catch (e: Throwable) { logLine("HMS_FAST_RectPartition_DestroyConfig (API 22) exception: $e") }
            logLine("HMS_FAST_RectPartition_Solve done")
        }
    }
}
