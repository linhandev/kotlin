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
class AIPTest {
    @Test
    fun testErrorCodeValues() {
        println("[stdout] AIPTest ===== AIP error code test start =====")

        val ok = platform.DataAugmentationKit.AIP.AIP_OK
        val execErr = platform.DataAugmentationKit.AIP.AIP_E_EXEC_ERR
        val outOfRange = platform.DataAugmentationKit.AIP.AIP_E_OUT_OF_RANGE
        val noSuchField = platform.DataAugmentationKit.AIP.AIP_E_NO_SUCH_FIELD
        val overLimit = platform.DataAugmentationKit.AIP.AIP_E_OVER_LIMIT
        val conditionOverLimit = platform.DataAugmentationKit.AIP.AIP_E_CONDITION_OVER_LIMIT
        val invalidArgs = platform.DataAugmentationKit.AIP.AIP_E_INVALID_ARGS
        val embeddingErr = platform.DataAugmentationKit.AIP.AIP_E_EMBEDDING_ERR

        println("[stdout] AIPTest AIP_OK=$ok")
        println("[stdout] AIPTest AIP_E_EXEC_ERR=$execErr")
        println("[stdout] AIPTest AIP_E_OUT_OF_RANGE=$outOfRange")
        println("[stdout] AIPTest AIP_E_NO_SUCH_FIELD=$noSuchField")
        println("[stdout] AIPTest AIP_E_OVER_LIMIT=$overLimit")
        println("[stdout] AIPTest AIP_E_CONDITION_OVER_LIMIT=$conditionOverLimit")
        println("[stdout] AIPTest AIP_E_INVALID_ARGS=$invalidArgs")
        println("[stdout] AIPTest AIP_E_EMBEDDING_ERR=$embeddingErr")

        // AIP_OK 应该和所有错误码不同
        assertNotEquals(ok, execErr, "AIP_OK should differ from AIP_E_EXEC_ERR")
        assertNotEquals(ok, outOfRange, "AIP_OK should differ from AIP_E_OUT_OF_RANGE")
        assertNotEquals(ok, noSuchField, "AIP_OK should differ from AIP_E_NO_SUCH_FIELD")
        assertNotEquals(ok, overLimit, "AIP_OK should differ from AIP_E_OVER_LIMIT")
        assertNotEquals(ok, conditionOverLimit, "AIP_OK should differ from AIP_E_CONDITION_OVER_LIMIT")
        assertNotEquals(ok, invalidArgs, "AIP_OK should differ from AIP_E_INVALID_ARGS")
        assertNotEquals(ok, embeddingErr, "AIP_OK should differ from AIP_E_EMBEDDING_ERR")
    }

    @Test
    fun testErrorCodeBoundaries() {
        val ok = platform.DataAugmentationKit.AIP.AIP_OK
        val embeddingErr = platform.DataAugmentationKit.AIP.AIP_E_EMBEDDING_ERR

        println("[stdout] AIPTest Boundary: first=AIP_OK=$ok, last=AIP_E_EMBEDDING_ERR=$embeddingErr")
    }

    @Test
    fun testErrorCodeRelations() {
        val ok = platform.DataAugmentationKit.AIP.AIP_OK
        val execErr = platform.DataAugmentationKit.AIP.AIP_E_EXEC_ERR
        val outOfRange = platform.DataAugmentationKit.AIP.AIP_E_OUT_OF_RANGE
        val noSuchField = platform.DataAugmentationKit.AIP.AIP_E_NO_SUCH_FIELD
        val overLimit = platform.DataAugmentationKit.AIP.AIP_E_OVER_LIMIT
        val conditionOverLimit = platform.DataAugmentationKit.AIP.AIP_E_CONDITION_OVER_LIMIT
        val invalidArgs = platform.DataAugmentationKit.AIP.AIP_E_INVALID_ARGS
        val embeddingErr = platform.DataAugmentationKit.AIP.AIP_E_EMBEDDING_ERR

        val codes = listOf(
            "AIP_OK" to ok,
            "AIP_E_EXEC_ERR" to execErr,
            "AIP_E_OUT_OF_RANGE" to outOfRange,
            "AIP_E_NO_SUCH_FIELD" to noSuchField,
            "AIP_E_OVER_LIMIT" to overLimit,
            "AIP_E_CONDITION_OVER_LIMIT" to conditionOverLimit,
            "AIP_E_INVALID_ARGS" to invalidArgs,
            "AIP_E_EMBEDDING_ERR" to embeddingErr
        )

        // 打印成一行，方便肉眼检查顺序和取值
        val summary = codes.joinToString(
            prefix = "All AIP error codes: ",
            separator = ", "
        ) { (name, value) -> "$name=$value" }

        println("[stdout] AIPTest $summary")

        // 所有错误码彼此也应该不同
        for (i in codes.indices) {
            for (j in i + 1 until codes.size) {
                val (name1, value1) = codes[i]
                val (name2, value2) = codes[j]
                assertNotEquals(
                    value1,
                    value2,
                    "AIP error codes should be distinct: $name1 vs $name2"
                )
            }
        }

        println("[stdout] AIPTest ===== AIP error code test end =====")
    }
}

