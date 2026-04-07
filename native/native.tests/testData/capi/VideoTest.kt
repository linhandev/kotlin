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
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.cinterop.*
import platform.video.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class VideoTest {

    private fun logLine(msg: String) = println(msg)

    // video/edid.h
    @Test
    fun testEdid_h() {
        memScoped {
            val info = alloc<edid_info>()
            assertNotNull(info.dummy)
            logLine("edid_info allocated")
        }
    }

    // video/sisfb.h
    @Test
    fun testSisfb_h() {
        assertEquals(0x00000001, CRT2_DEFAULT)
        assertEquals(0x00000002, CRT2_LCD)
        assertEquals(0x00000004, CRT2_TV)
        logLine("CRT2_DEFAULT=$CRT2_DEFAULT CRT2_LCD=$CRT2_LCD CRT2_TV=$CRT2_TV")
    }

    // video/uvesafb.h
    @Test
    fun testUvesafb_h() {
        assertEquals(0x01, TF_VBEIB)
        assertEquals(0x02, TF_BUF_ESDI)
        assertEquals(0x01, VBE_CAP_CAN_SWITCH_DAC)
        logLine("TF_VBEIB=$TF_VBEIB TF_BUF_ESDI=$TF_BUF_ESDI VBE_CAP_CAN_SWITCH_DAC=$VBE_CAP_CAN_SWITCH_DAC")
    }
}
