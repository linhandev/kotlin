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
class AbilityAccessControlTest {

    @Test
    fun testCheckSelfPermission() {
        println("[stdout] AbilityAccessControlTest OH_AT_CheckSelfPermission(ohos.permission.GET_BUNDLE_INFO)")
        val result = platform.AbilityKit.AbilityAccessControl.OH_AT_CheckSelfPermission("ohos.permission.GET_BUNDLE_INFO")
        println("[stdout] AbilityAccessControlTest result: $result")
        assertNotNull(result)
    }
}
