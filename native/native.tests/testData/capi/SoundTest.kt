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
import platform.sound.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class SoundTest {

    private fun logLine(msg: String) = println(msg)

    // sound/asound.h
    @Test
    fun testAsound_h() {
        assertNotNull(SNDRV_PCM_ACCESS_MMAP_INTERLEAVED)
        assertEquals(0, SNDRV_HWDEP_IFACE_OPL2.toInt())
        assertEquals(1, SNDRV_HWDEP_IFACE_OPL3.toInt())
        logLine("SNDRV_PCM_ACCESS_MMAP_INTERLEAVED=$SNDRV_PCM_ACCESS_MMAP_INTERLEAVED SNDRV_HWDEP_IFACE_OPL2=$SNDRV_HWDEP_IFACE_OPL2")
    }

    // sound/asound_fm.h
    @Test
    fun testAsound_fm_h() {
        assertEquals(0x00, SNDRV_DM_FM_MODE_OPL2.toInt())
        assertEquals(0x01, SNDRV_DM_FM_MODE_OPL3.toInt())
        logLine("SNDRV_DM_FM_MODE_OPL2=$SNDRV_DM_FM_MODE_OPL2 SNDRV_DM_FM_MODE_OPL3=$SNDRV_DM_FM_MODE_OPL3")
    }
}
