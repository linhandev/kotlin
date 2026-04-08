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
import kotlin.test.assertTrue
import kotlinx.cinterop.*
import platform.mtd.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class MtdTest {

    private fun logLine(msg: String) = println(msg)

    // mtd/mtd-abi.h — 常量与枚举
    @Test
    fun testMtd_abi_h() {
        assertTrue(MTD_WRITEABLE != 0)
        logLine("MTD_ABSENT=$MTD_ABSENT MTD_RAM=$MTD_RAM MTD_NORFLASH=$MTD_NORFLASH MTD_WRITEABLE=$MTD_WRITEABLE")
    }

    // mtd/mtd-user.h — typedef 类型（mtd_info_t, erase_info_t 等）
    @Test
    fun testMtd_user_h() {
        memScoped {
            val info = alloc<mtd_info_t>()
            info.type = 0.toUByte()
            info.flags = 0u
            info.size = 0u
            assertEquals(0.toUByte(), info.type)
            logLine("mtd_info_t type=${info.type} flags=${info.flags}")
        }
    }

    // mtd/inftl-user.h — 常量与结构体
    @Test
    fun testInftl_user_h() {
        assertEquals(0x5120, OSAK_VERSION)
        assertEquals(98, PERCENTUSED)
        assertEquals(512, SECTORSIZE)
        assertTrue(INFTL_BINARY != 0)
        assertTrue(INFTL_BDTL != 0)
        logLine("OSAK_VERSION=$OSAK_VERSION PERCENTUSED=$PERCENTUSED SECTORSIZE=$SECTORSIZE INFTL_BINARY=$INFTL_BINARY")
    }

    // mtd/nftl-user.h — 常量与结构体
    @Test
    fun testNftl_user_h() {
        assertEquals(0x3c69, ERASE_MARK)
        assertEquals(0xff, SECTOR_FREE)
        assertEquals(0x55, SECTOR_USED)
        assertTrue(MAX_ERASE_ZONES > 0)
        assertEquals(0xff, ZONE_GOOD)
        logLine("ERASE_MARK=$ERASE_MARK SECTOR_FREE=$SECTOR_FREE MAX_ERASE_ZONES=$MAX_ERASE_ZONES")
    }
}
