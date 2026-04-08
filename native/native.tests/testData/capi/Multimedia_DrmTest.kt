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
import platform.AVCodecKit.Multimedia_Drm.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Multimedia_DrmTest {

    private fun logLine(msg: String) = println(msg)

    @Test
    fun testEnum_DrmCencAlgorithm() {
        assertEquals(DRM_ALG_CENC_UNENCRYPTED.toInt(), 0x0)
        assertEquals(DRM_ALG_CENC_AES_CTR.toInt(), 0x1)
        assertEquals(DRM_ALG_CENC_AES_WV.toInt(), 0x2)
        assertEquals(DRM_ALG_CENC_AES_CBC.toInt(), 0x3)
        assertEquals(DRM_ALG_CENC_SM4_CBC.toInt(), 0x4)
        assertEquals(DRM_ALG_CENC_SM4_CTR.toInt(), 0x5)
        logLine("testEnum_DrmCencAlgorithm passed")
    }

    @Test
    fun testEnum_DrmCencInfoMode() {
        assertEquals(DRM_CENC_INFO_KEY_IV_SUBSAMPLES_SET.toInt(), 0x0)
        assertEquals(DRM_CENC_INFO_KEY_IV_SUBSAMPLES_NOT_SET.toInt(), 0x1)
        logLine("testEnum_DrmCencInfoMode passed")
    }

    @Test
    fun testOH_AVCencInfo_Create() {
        val cencInfo = OH_AVCencInfo_Create()
        logLine("OH_AVCencInfo_Create result=$cencInfo")
        assertNotNull(cencInfo)
        val destroyRet = OH_AVCencInfo_Destroy(cencInfo)
        assertNotNull(destroyRet)
    }

    @Test
    fun testOH_AVCencInfo_SetAlgorithm() {
        memScoped {
            val cencInfo = OH_AVCencInfo_Create()
            val ret = OH_AVCencInfo_SetAlgorithm(cencInfo, DRM_ALG_CENC_AES_CTR)
            assertNotNull(ret)
            logLine("OH_AVCencInfo_SetAlgorithm ret=$ret")
            OH_AVCencInfo_Destroy(cencInfo)
        }
    }

    @Test
    fun testOH_AVCencInfo_SetKeyIdAndIv() {
        memScoped {
            val cencInfo = OH_AVCencInfo_Create()
            val keyId = allocArray<UByteVar>(16)
            val iv = allocArray<UByteVar>(16)
            val ret = OH_AVCencInfo_SetKeyIdAndIv(cencInfo, keyId, 16u, iv, 16u)
            assertNotNull(ret)
            logLine("OH_AVCencInfo_SetKeyIdAndIv ret=$ret")
            OH_AVCencInfo_Destroy(cencInfo)
        }
    }

    @Test
    fun testOH_AVCencInfo_SetSubsampleInfo() {
        memScoped {
            val cencInfo = OH_AVCencInfo_Create()
            val subsamples = alloc<DrmSubsample>().apply { clearHeaderLen = 0u; payLoadLen = 0u }.ptr
            val ret = OH_AVCencInfo_SetSubsampleInfo(cencInfo, 0u, 0u, 0u, 1u, subsamples)
            assertNotNull(ret)
            logLine("OH_AVCencInfo_SetSubsampleInfo ret=$ret")
            OH_AVCencInfo_Destroy(cencInfo)
        }
    }

    @Test
    fun testOH_AVCencInfo_SetMode() {
        memScoped {
            val cencInfo = OH_AVCencInfo_Create()
            val ret = OH_AVCencInfo_SetMode(cencInfo, DRM_CENC_INFO_KEY_IV_SUBSAMPLES_NOT_SET)
            assertNotNull(ret)
            logLine("OH_AVCencInfo_SetMode ret=$ret")
            OH_AVCencInfo_Destroy(cencInfo)
        }
    }

    @Test
    fun testOH_AVCencInfo_SetAVBuffer() {
        memScoped {
            val cencInfo = OH_AVCencInfo_Create()
            val ret = OH_AVCencInfo_SetAVBuffer(cencInfo, null)
            assertNotNull(ret)
            logLine("OH_AVCencInfo_SetAVBuffer ret=$ret")
            OH_AVCencInfo_Destroy(cencInfo)
        }
    }
}
