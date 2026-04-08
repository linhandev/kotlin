/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include <cstdint>
#include <iostream>
#include "StackMapTable.hpp"
#include "SlotRoot.hpp"

namespace kotlin::stackMap {
class DerivedPtr {
public:
    DerivedPtr() = default;
    DerivedPtr(const DerivedPtrTable& derivePtr, const RegTable& reg, const SlotTable& slot, uint32_t startIdx)
        : derivePtrTable(derivePtr), regTable(reg), slotTable(slot), derivedPtrIdx(startIdx) {}
    ~DerivedPtr() = default;

    void CollectDerivedPtrSlots(std::vector<int32_t> &derivedSlotOffsets)
    {
        DerivedPtrPair idxPair = derivePtrTable.GetDerivePair(derivedPtrIdx - 1);
        uint32_t regIdx = idxPair.first;
        uint32_t slotIdx = idxPair.second;
        if (regIdx != 0) {
            std::cerr << "unexpected reg-derived pointer in CollectDerivedPtrSlots, regIdx: " << regIdx << std::endl;
        }
        if (slotIdx != 0) {
            SlotRoot(slotTable.GetBaseOffset(slotIdx - 1), slotTable.GetSlotBitMap(slotIdx - 1),
                     slotTable.slotFormat).CollectSlotOffsets(derivedSlotOffsets);
        }
    }

private:
    DerivedPtrTable derivePtrTable;
    RegTable regTable;
    SlotTable slotTable;
    uint32_t derivedPtrIdx = 0;
};
} // namespace kotlin::stackMap
