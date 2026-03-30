/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include <cstdint>
#include "StackMapTable.hpp"
#include "SlotRoot.hpp"

namespace kotlin::stackMap {
class DerivedPtr {
public:
    DerivedPtr() = default;
    DerivedPtr(const DerivedPtrTable& derivePtr, const RegTable& reg, const SlotTable& slot, uint32_t startIdx)
        : derivePtrTable(derivePtr), regTable(reg), slotTable(slot), derivedPtrIdx(startIdx) {}
    ~DerivedPtr() = default;
    // bool VisitDerivedPtr(const DerivedPtrVisitor& visitor, const DerivedPtrDebugVisitor debugVisitor,
    //                      RegSlotsMap& regSlotsMap, Uptr basePtr, Uptr fp)
    // {
    //     if (LIKELY(derivedPtrIdx == 0)) {
    //         return false;
    //     }
    //     DerivedPtrPair idxPair = derivePtrTable.GetDerivePair(derivedPtrIdx - 1);
    //     U32 regIdx = idxPair.first;
    //     U32 slotIdx = idxPair.second;
    //     if (basePtr != 0) {
    //         if (regIdx != 0) {
    //             VisitRegDerivedPtr(visitor, debugVisitor, regSlotsMap, basePtr, regIdx - 1);
    //         }
    //         if (slotIdx != 0) {
    //             VisitSlotDerivedPtr(visitor, debugVisitor, basePtr, fp, slotIdx - 1);
    //         }
    //     }
    //     derivedPtrIdx++;
    //     return true;
    // }

    void CollectDerivedPtrSlots(std::vector<int32_t> &derivedSlotOffsets)
    {
        DerivedPtrPair idxPair = derivePtrTable.GetDerivePair(derivedPtrIdx - 1);
        uint32_t regIdx = idxPair.first;
        uint32_t slotIdx = idxPair.second;
        if (regIdx != 0) {
            std::cerr << "@@@@ something wrong, regIdx: " << regIdx << std::endl;
        }
        if (slotIdx != 0) {
            SlotRoot(slotTable.GetBaseOffset(slotIdx - 1), slotTable.GetSlotBitMap(slotIdx - 1),
                     slotTable.slotFormat).CollectSlotOffsets(derivedSlotOffsets);
        }
    }

private:
//     inline void VisitRegDerivedPtr(const DerivedPtrVisitor& visitor, const DerivedPtrDebugVisitor debugVisitor,
//                                    RegSlotsMap& regSlotsMap, Uptr basePtr, U32 regIdx) const
//     {
//         RegRoot regRoot(regTable.GetActiveRegBits(regIdx));
//         RootVisitor rootVisitor = [&visitor, basePtr](ObjectRef& derivedPtr) {
//             visitor(basePtr, reinterpret_cast<Uptr&>(derivedPtr));
//         };
//         RegDebugVisitor regDebug = nullptr;
//         (void)debugVisitor;
// #if defined(GCINFO_DEBUG) && GCINFO_DEBUG
//         if (debugVisitor != nullptr) {
//             regDebug = [&debugVisitor, basePtr](RegisterNum, const BaseObject* derivedPtr) {
//                 debugVisitor(basePtr, reinterpret_cast<Uptr>(derivedPtr));
//             };
//         }
// #endif
//         regRoot.VisitGCRoots(rootVisitor, regDebug, regSlotsMap);
//     }
//     inline void VisitSlotDerivedPtr(const DerivedPtrVisitor& visitor, const DerivedPtrDebugVisitor debugVisitor,
//                                     Uptr basePtr, Uptr fp, U32 slotIdx) const
//     {
//         SlotRoot slotRoot(slotTable.GetBaseOffset(slotIdx), slotTable.GetSlotBitMap(slotIdx), slotTable.slotFormat);
//         RootVisitor rootVisitor = [&visitor, basePtr](ObjectRef& derivedPtr) {
//             visitor(basePtr, reinterpret_cast<Uptr&>(derivedPtr.object));
//         };
//         SlotDebugVisitor slotDebug = nullptr;
//         (void)debugVisitor;
// #if defined(GCINFO_DEBUG) && GCINFO_DEBUG
//         if (debugVisitor != nullptr) {
//             slotDebug = [&debugVisitor, basePtr](SlotBias, BaseObject* derivedPtr) {
//                 debugVisitor(basePtr, reinterpret_cast<Uptr>(derivedPtr));
//             };
//         }
// #endif
//         slotRoot.VisitGCRoots(rootVisitor, slotDebug, fp);
//     }
    DerivedPtrTable derivePtrTable;
    RegTable regTable;
    SlotTable slotTable;
    uint32_t derivedPtrIdx = 0;
};
} // namespace kotlin::stackMap