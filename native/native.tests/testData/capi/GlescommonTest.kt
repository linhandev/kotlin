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
import platform.glescommon.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class GlescommonTest {

    private fun logLine(msg: String) = println(msg)

    // KHR/khrplatform.h
    @Test
    fun testKhrplatform_h() {
        assertNotNull(KHRONOS_SUPPORT_FLOAT)
        logLine("KHRONOS_FALSE=$KHRONOS_FALSE KHRONOS_TRUE=$KHRONOS_TRUE KHRONOS_SUPPORT_FLOAT=$KHRONOS_SUPPORT_FLOAT")
    }
}
