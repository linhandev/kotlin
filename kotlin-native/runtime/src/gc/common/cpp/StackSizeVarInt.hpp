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
// Integral stackmap implementation header. The ON path exposes the full API;
// the OFF path defines no symbols (all callers must be gated under the same
// macro).

#include "StackMapTable.hpp"

namespace kotlin::stackMap {
class StackMapHeaderVarInt {
public:
    StackMapHeaderVarInt(uint8_t* ptr, uint32_t bitPos) : value(ptr, bitPos) { ResolveVarInt(); }
    explicit StackMapHeaderVarInt(const BitsManager& bitsManager) : value(bitsManager) { ResolveVarInt(); }
    ~StackMapHeaderVarInt() = default;

    BitsManager GetNextTable() const { return nextTable; }

    uint32_t GetStacksize() const { return stacksize; }

private:
    void ResolveVarInt()
    {
        VarInt sizeBits(value);
        VarPair varPair = sizeBits.GetValue();
        stacksize = varPair.first;
        nextTable = value.GetNext(varPair.second);
    }
    BitsManager value;
    BitsManager nextTable;
    uint32_t stacksize{ 0 };
};
} // namespace kotlin::stackMap
#endif // ENABLE_STACKMAP
