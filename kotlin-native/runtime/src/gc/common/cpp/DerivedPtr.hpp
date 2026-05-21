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
// Entire file is stackmap-only (kotlin::stackMap::DerivedPtr).
// OFF mode: empty header. All consumers (ConcurrentMark.cpp etc) must also be gated.

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

    // Visitor-style variant used by the precise CRT root visitor.
    template <typename Visitor>
    void VisitDerivedPtrSlots(Visitor visitor)
    {
        DerivedPtrPair idxPair = derivePtrTable.GetDerivePair(derivedPtrIdx - 1);
        uint32_t regIdx = idxPair.first;
        uint32_t slotIdx = idxPair.second;
        if (regIdx != 0) {
            std::cerr << "unexpected reg-derived pointer in VisitDerivedPtrSlots, regIdx: " << regIdx << std::endl;
        }
        if (slotIdx != 0) {
            SlotRoot(slotTable.GetBaseOffset(slotIdx - 1), slotTable.GetSlotBitMap(slotIdx - 1),
                     slotTable.slotFormat).VisitSlotOffsets(visitor);
        }
    }

private:
    DerivedPtrTable derivePtrTable;
    RegTable regTable;
    SlotTable slotTable;
    uint32_t derivedPtrIdx = 0;
};
} // namespace kotlin::stackMap

#endif // ENABLE_STACKMAP
