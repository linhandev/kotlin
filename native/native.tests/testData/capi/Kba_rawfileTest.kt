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
// FREE_COMPILER_ARGS: -linker-option
// FREE_COMPILER_ARGS: -lrawfile.z
import kotlin.test.Test
import kotlinx.cinterop.*
import platform.rawfile.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class Kba_rawfileTest {

    // ---------- rawfile/raw_file_manager.h (API 8) ----------
    @Test
    fun testRaw_file_manager_h() {
        val mgr = OH_ResourceManager_InitNativeResourceManager(null, null)
    }

    // ---------- rawfile/raw_dir.h (API 8) ----------
    @Test
    fun testRaw_dir_h() {
        OH_ResourceManager_GetRawFileCount(null)
    }

    // ---------- rawfile/raw_file.h (API 8) ----------
    @Test
    fun testRaw_file_h() {
        OH_ResourceManager_GetRawFileSize(null)
    }
}
