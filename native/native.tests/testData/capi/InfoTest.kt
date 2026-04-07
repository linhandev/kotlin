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
import platform.info.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class InfoTest {

    private fun logLine(message: String) = println(message)

    // ---------- info/application_target_sdk_version.h：版本常量 ----------

    @Test
    fun testApplicationTargetSdkVersionMacros() {
        assertEquals(9999, SDK_VERSION_FUTURE.toInt())
        assertEquals(7, SDK_VERSION_7.toInt())
        assertEquals(8, SDK_VERSION_8.toInt())
        assertEquals(9, SDK_VERSION_9.toInt())
        assertEquals(10, OH_API_VERSION_10.toInt())
        assertEquals(11, OH_API_VERSION_11.toInt())
        assertEquals(12, OH_API_VERSION_12.toInt())
        assertEquals(13, OH_API_VERSION_13.toInt())
        assertEquals(14, OH_API_VERSION_14.toInt())
        assertEquals(15, OH_API_VERSION_15.toInt())
        assertEquals(16, OH_API_VERSION_16.toInt())
        assertEquals(17, OH_API_VERSION_17.toInt())
        assertEquals(18, OH_API_VERSION_18.toInt())
        assertEquals(19, OH_API_VERSION_19.toInt())
        assertEquals(20, OH_API_VERSION_20.toInt())
        assertEquals(21, OH_API_VERSION_21.toInt())
        assertEquals(22, OH_API_VERSION_22.toInt())
        assertEquals(OH_API_VERSION_22.toInt(), OH_CURRENT_API_VERSION.toInt())
        logLine("SDK_VERSION_FUTURE=$SDK_VERSION_FUTURE OH_CURRENT_API_VERSION=$OH_CURRENT_API_VERSION")
    }

    @Test
    fun testGetApplicationTargetSdkVersion() {
        val v = get_application_target_sdk_version()
        logLine("get_application_target_sdk_version()=$v")
        assertTrue(v >= 0, "target sdk version should be non-negative")
    }

    @Test
    fun testSetApplicationTargetSdkVersion() {
        val before = get_application_target_sdk_version()
        val target = OH_API_VERSION_12
        set_application_target_sdk_version(target)
        val after = get_application_target_sdk_version()
        logLine("set_application_target_sdk_version($target): before=$before after=$after")
        assertEquals(target.toInt(), after)
        set_application_target_sdk_version(before)
        assertEquals(before, get_application_target_sdk_version())
    }

    // ---------- info/device_api_version.h（头文件注明可能未实现 / 已废弃） ----------

    @Test
    fun testGetDeviceApiVersion() {
        logLine("--- get_device_api_version ---")
        try {
            val v = get_device_api_version()
            logLine("get_device_api_version()=$v")
        } catch (e: Throwable) {
            logLine("get_device_api_version exception (expected on some targets): $e")
        }
    }

    // ---------- info/fatal_message.h ----------

    @Test
    fun testSetFatalMessage() {
        val text = "InfoTest_fatal_probe"
        set_fatal_message(text)
        logLine("set_fatal_message(\"$text\") ok")
    }

    @Test
    fun testGetFatalMessage() {
        val text = "InfoTest_get_fatal"
        set_fatal_message(text)
        val p = get_fatal_message()
        assertNotNull(p)
        val sz = p.pointed.size.toLong()
        logLine("get_fatal_message size=$sz")
        assertTrue(sz > 0L, "fatal message size should reflect set string")
    }
}
