/*
 * Copyright (C) 2025-2026 Huawei Device Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#pragma once

#ifdef ENABLE_STACKMAP
// Entire file is stackmap-only (kotlin::stackMap macros + types).
// OFF mode: empty header.

#include <cstdint>

namespace kotlin::stackMap {
#define ATTR_NO_INLINE __attribute__((noinline))
using SlotBits = uint32_t;
#if defined(__APPLE__)
    using Uptr = uint64_t;
#else
    using Uptr = uintptr_t;
#endif

using SlotBias = int32_t;
using BitsMapSize = uint32_t;

enum StackMapFormat : uint8_t {
    STACKMAP_BITMAP = 0,
    STACKMAP_COMPRESSED_BITMAP = 1,
};
} // namespace kotlin::stackMap

#endif // ENABLE_STACKMAP