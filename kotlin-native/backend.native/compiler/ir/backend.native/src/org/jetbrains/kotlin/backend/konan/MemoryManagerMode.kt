/*
 * Copyright 2026 Huawei Device Co., Ltd.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan

// Must match `MemoryManagerMode` in CompilerConstants.hpp
enum class MemoryManagerMode(val value: Int) {
    NATIVE(0),
    CRT(1),
    RUNTIME_SWITCH(2),
}