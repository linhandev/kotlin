/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

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