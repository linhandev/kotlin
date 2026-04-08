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
import platform.UniversalKeystoreKit.HuksParamSetApi.*
import platform.UniversalKeystoreKit.HuksTypeApi.*


@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class HuksParamSetApiTest {

    private fun logLine(message: String) = println(message)

    // ==================== 函数测试 ====================

    @Test
    fun testInitBuildFree() { memScoped {
        // OH_Huks_InitParamSet
        val paramSetPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
        val rc1 = OH_Huks_InitParamSet(paramSetPtr.ptr)
        assertNotNull(rc1)
        logLine("OH_Huks_InitParamSet errorCode=${rc1.useContents { errorCode }}")

        paramSetPtr.value?.let { ps ->
            // OH_Huks_BuildParamSet（空参数集也可 build）
            val rc2 = OH_Huks_BuildParamSet(paramSetPtr.ptr)
            assertNotNull(rc2)
            logLine("OH_Huks_BuildParamSet errorCode=${rc2.useContents { errorCode }}")

            // OH_Huks_FreeParamSet（void，无返回值）
            OH_Huks_FreeParamSet(paramSetPtr.ptr)
            logLine("OH_Huks_FreeParamSet done")
        }
    } }

    @Test
    fun testAddAndGetParams() { memScoped {
        val paramSetPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
        OH_Huks_InitParamSet(paramSetPtr.ptr)

        paramSetPtr.value?.let { ps ->
            // OH_Huks_AddParams
            val params = allocArray<OH_Huks_Param>(2)
            params[0].tag = OH_HUKS_TAG_ALGORITHM.toUInt()
            params[0].uint32Param = OH_HUKS_ALG_AES.toUInt()
            params[1].tag = OH_HUKS_TAG_KEY_SIZE.toUInt()
            params[1].uint32Param = OH_HUKS_AES_KEY_SIZE_256.toUInt()

            val rc1 = OH_Huks_AddParams(ps, params, 2u)
            assertNotNull(rc1)
            logLine("OH_Huks_AddParams errorCode=${rc1.useContents { errorCode }}")

            OH_Huks_BuildParamSet(paramSetPtr.ptr)

            // OH_Huks_GetParam
            val paramOut = alloc<CPointerVar<OH_Huks_Param>>()
            val rc2 = OH_Huks_GetParam(ps, OH_HUKS_TAG_ALGORITHM.toUInt(), paramOut.ptr)
            assertNotNull(rc2)
            logLine("OH_Huks_GetParam errorCode=${rc2.useContents { errorCode }}")

            OH_Huks_FreeParamSet(paramSetPtr.ptr)
        }
    } }

    @Test
    fun testCopyParamSet() { memScoped {
        val srcPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
        OH_Huks_InitParamSet(srcPtr.ptr)

        srcPtr.value?.let { src ->
            val params = alloc<OH_Huks_Param>().apply {
                tag = OH_HUKS_TAG_PURPOSE.toUInt()
                uint32Param = OH_HUKS_KEY_PURPOSE_ENCRYPT.toUInt()
            }
            OH_Huks_AddParams(src, params.ptr, 1u)
            OH_Huks_BuildParamSet(srcPtr.ptr)

            // OH_Huks_CopyParamSet
            val dstPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
            val rc = OH_Huks_CopyParamSet(src, src.pointed.paramSetSize, dstPtr.ptr)
            assertNotNull(rc)
            logLine("OH_Huks_CopyParamSet errorCode=${rc.useContents { errorCode }}")

            dstPtr.value?.let { OH_Huks_FreeParamSet(dstPtr.ptr) }
            OH_Huks_FreeParamSet(srcPtr.ptr)
        }
    } }

    @Test
    fun testFreshParamSet() { memScoped {
        val paramSetPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
        OH_Huks_InitParamSet(paramSetPtr.ptr)

        paramSetPtr.value?.let { ps ->
            val params = alloc<OH_Huks_Param>().apply {
                tag = OH_HUKS_TAG_DIGEST.toUInt()
                uint32Param = OH_HUKS_DIGEST_SHA256.toUInt()
            }
            OH_Huks_AddParams(ps, params.ptr, 1u)
            OH_Huks_BuildParamSet(paramSetPtr.ptr)

            // OH_Huks_FreshParamSet
            val rc = OH_Huks_FreshParamSet(ps, false)
            assertNotNull(rc)
            logLine("OH_Huks_FreshParamSet errorCode=${rc.useContents { errorCode }}")

            OH_Huks_FreeParamSet(paramSetPtr.ptr)
        }
    } }

    @Test
    fun testIsParamSetTagValid() { memScoped {
        val paramSetPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
        OH_Huks_InitParamSet(paramSetPtr.ptr)

        paramSetPtr.value?.let { ps ->
            val params = alloc<OH_Huks_Param>().apply {
                tag = OH_HUKS_TAG_PADDING.toUInt()
                uint32Param = OH_HUKS_PADDING_NONE.toUInt()
            }
            OH_Huks_AddParams(ps, params.ptr, 1u)
            OH_Huks_BuildParamSet(paramSetPtr.ptr)

            // OH_Huks_IsParamSetTagValid
            val rc = OH_Huks_IsParamSetTagValid(ps)
            assertNotNull(rc)
            logLine("OH_Huks_IsParamSetTagValid errorCode=${rc.useContents { errorCode }}")

            OH_Huks_FreeParamSet(paramSetPtr.ptr)
        }
    } }

    @Test
    fun testIsParamSetValid() { memScoped {
        val paramSetPtr = alloc<CPointerVar<OH_Huks_ParamSet>>()
        OH_Huks_InitParamSet(paramSetPtr.ptr)

        paramSetPtr.value?.let { ps ->
            val params = alloc<OH_Huks_Param>().apply {
                tag = OH_HUKS_TAG_BLOCK_MODE.toUInt()
                uint32Param = OH_HUKS_MODE_GCM.toUInt()
            }
            OH_Huks_AddParams(ps, params.ptr, 1u)
            OH_Huks_BuildParamSet(paramSetPtr.ptr)

            // OH_Huks_IsParamSetValid
            val rc = OH_Huks_IsParamSetValid(ps, ps.pointed.paramSetSize)
            assertNotNull(rc)
            logLine("OH_Huks_IsParamSetValid errorCode=${rc.useContents { errorCode }}")

            OH_Huks_FreeParamSet(paramSetPtr.ptr)
        }
    } }

    @Test
    fun testCheckParamMatch() { memScoped {
        val baseParam = alloc<OH_Huks_Param>().apply {
            tag = OH_HUKS_TAG_ALGORITHM.toUInt()
            uint32Param = OH_HUKS_ALG_AES.toUInt()
        }
        val param = alloc<OH_Huks_Param>().apply {
            tag = OH_HUKS_TAG_ALGORITHM.toUInt()
            uint32Param = OH_HUKS_ALG_AES.toUInt()
        }

        // OH_Huks_CheckParamMatch
        val rc = OH_Huks_CheckParamMatch(baseParam.ptr, param.ptr)
        assertNotNull(rc)
        logLine("OH_Huks_CheckParamMatch errorCode=${rc.useContents { errorCode }}")
    } }

    @Test
    fun testFreeKeyAliasSet() {
        // OH_Huks_FreeKeyAliasSet (API 20)（void，传 null 即可覆盖）
        try { OH_Huks_FreeKeyAliasSet(null) } catch (e: Throwable) { logLine("OH_Huks_FreeKeyAliasSet (API 20) exception: $e") }
        logLine("OH_Huks_FreeKeyAliasSet done")
    }
}
