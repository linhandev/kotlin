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
#include "StackSizeVarInt.hpp"
#include "SlotRoot.hpp"
#include "DerivedPtr.hpp"
#include <cstdint>

#if KONAN_LINUX || KONAN_OHOS
extern "C" uint8_t __LLVM_StackMaps;
#else
extern "C" uint8_t _LLVM_StackMaps;
#endif

namespace kotlin::stackMap {

constexpr uint32_t FUNC_ADDRESS_FIELD_SIZE = 8;
constexpr uint32_t STACK_MAP_SIZE_FIELD_SIZE = 4;
constexpr uint16_t DWARF_FP_REG = 29;

using CallSiteInfo = std::vector<std::pair<uint16_t, int32_t>>;

class CompressedStackMapEntry {
public:
    CompressedStackMapEntry(const IdxSet& idx, const RegTable& reg, const SlotTable& slot,
                            const DerivedPtrTable derived)
        : idxSet(idx), regTable(reg), slotTable(slot),
          derivedPtrTable(derived) {}

    ~CompressedStackMapEntry() = default;

    SlotRoot BuildSlotRoot() const
    {
        uint32_t idx = idxSet.slotIdx;
        if (idx == 0) {
            return SlotRoot();
        }
        return SlotRoot(slotTable.GetBaseOffset(idx - 1), slotTable.GetSlotBitMap(idx - 1), slotTable.slotFormat);
    }

    // Streaming-visitor variant used by the precise CRT root visitor (KNRootVisitor).
    // Visits each base-slot offset via `rootVisitor(bias)`. If derived pointers exist
    // for the base, also visits each derived-slot offset via `derivedPtrVisitor(base, derived)`.
    // Base is always visited before its derived offsets so that derived-pointer fix-up
    // (during compaction) can read the live base.
    template <typename RootVisitor, typename DerivedPtrVisitor>
    void VisitBaseAndDerivedSlotOffsets(RootVisitor rootVisitor, DerivedPtrVisitor derivedPtrVisitor) const
    {
        uint32_t idx = idxSet.slotIdx;
        if (idx == 0) {
            return;
        }
        SlotRoot rootsSet(slotTable.GetBaseOffset(idx - 1), slotTable.GetSlotBitMap(idx - 1), slotTable.slotFormat);
        uint32_t derivedPtrIdx = idxSet.derivedPtrIdx;
        if (derivedPtrIdx == 0) {
            rootsSet.VisitSlotOffsets(rootVisitor);
            return;
        }
        rootsSet.VisitSlotOffsets([&](int32_t bias) {
            DerivedPtr derivedPtr(derivedPtrTable, regTable, slotTable, derivedPtrIdx);
            // Base must be handled before its derived pointers so that the derived
            // visitor can observe the live base value.
            rootVisitor(bias);
            derivedPtr.VisitDerivedPtrSlots([&](int32_t derived) { derivedPtrVisitor(bias, derived); });
            derivedPtrIdx++;
        });
    }

    void CollectBase2DerivedSlotOffsets(std::unordered_map<int32_t, std::vector<int32_t>> &base2DerivedSlotOffsets)
    {
        uint32_t idx = idxSet.slotIdx;
        if (idx == 0) {
            return;
        }
        std::vector<int32_t> basePtrSlotOffsets;
        SlotRoot(slotTable.GetBaseOffset(idx - 1), slotTable.GetSlotBitMap(idx - 1),
                 slotTable.slotFormat).CollectSlotOffsets(basePtrSlotOffsets);
        uint32_t derivedPtrIdx = idxSet.derivedPtrIdx;
        if (derivedPtrIdx != 0) {
            for (auto elem : basePtrSlotOffsets) {
                std::vector<int32_t> derivedSlotOffsets {};
                DerivedPtr derivedPtr(derivedPtrTable, regTable, slotTable, derivedPtrIdx);
                derivedPtr.CollectDerivedPtrSlots(derivedSlotOffsets);
                base2DerivedSlotOffsets[elem] = derivedSlotOffsets;
                derivedPtrIdx++;
            }
        } else {
            for (auto elem : basePtrSlotOffsets) {
                std::vector<int32_t> derivedSlotOffsets {};
                base2DerivedSlotOffsets[elem] = derivedSlotOffsets;
            }
        }
    }

    DerivedPtr BuildDerivedPtrRoot() const
    {
        uint32_t idx = idxSet.derivedPtrIdx;
        if (idx == 0) {
            return DerivedPtr();
        }
        return DerivedPtr(derivedPtrTable, regTable, slotTable, idx);
    }

private:
    IdxSet idxSet;
    RegTable regTable;
    SlotTable slotTable;
    DerivedPtrTable derivedPtrTable;
};

class CompressedStackMapHead {
public:
    CompressedStackMapHead(const BitsManager& prologueManager,
                           const PrologueVisitor& visitor, uint32_t format)
        : prologue_(prologueManager, visitor), slotFormat_(format) {}
    ~CompressedStackMapHead() = default;

    static CompressedStackMapHead GetStackMapHead(uint8_t *stackmapStart, const PrologueVisitor& visitor)
    {
        StackMapHeaderVarInt stacksizeVarInt(stackmapStart, 0);
        StackMapHeaderVarInt compressedFormatVarInt(stacksizeVarInt.GetNextTable());
        uint32_t format = compressedFormatVarInt.GetStacksize();
        return CompressedStackMapHead(compressedFormatVarInt.GetNextTable(), visitor, format);
    }

    bool HasStackMapEntry(uintptr_t startPC, uintptr_t curPC) const
    {
        StackMapTable stackMapTable(prologue_.GetNextTable());
        return stackMapTable.HasRecordForPC(startPC, curPC);
    }

    void CollectStackMapEntry(uintptr_t startPC, uintptr_t curPC,
        std::unordered_map<int32_t, std::vector<int32_t>> &base2DerivedOffsets) const
    {
        StackMapTable stackMapTable(prologue_.GetNextTable());
        std::vector<IdxSet> idxSetVec;
        IdxSet idxSet = stackMapTable.GetIdxSet(startPC, curPC);

        RegTable regTable(stackMapTable.GetNextTable());
        SlotTable slotTable(regTable.GetNextTable(), slotFormat_);
        DerivedPtrTable derivedTable(slotTable.GetNextTable(), stackMapTable.GetRegBitsLen(),
                                     stackMapTable.GetSlotBitsLen());
        CompressedStackMapEntry entry(idxSet, regTable, slotTable, derivedTable);
        entry.CollectBase2DerivedSlotOffsets(base2DerivedOffsets);

#if DUMP_DEBUG_INFO
        std::cout << "------wzl log start print base2derived info" << std::endl;
        for (auto &elem : base2DerivedOffsets) {
            if (elem.second.empty()) {
                std::cout << "    register: 29, offset: " << elem.first
                          << ", register: 29, offset: " << elem.first << std::endl;
            } else {
                for (auto derived : elem.second) {
                    std::cout << "    register: 29, offset: " << elem.first
                                << ", register: 29, offset: " << derived << std::endl;
                }
            }
        }
#endif
    }

private:
    PrologueVarInt prologue_;
    uint32_t slotFormat_;
};
using StackMapEntry = CompressedStackMapEntry;
using StackMapHead = CompressedStackMapHead;


class StackMapBuilder {
public:
    StackMapBuilder(uintptr_t start, uintptr_t frame, uintptr_t base)
        : startPC(start), framePC(frame), stackBase(base), funcStackMapAddr(nullptr) {}
    StackMapBuilder(uintptr_t start, uintptr_t frame, uintptr_t base, uint64_t *funcStackMapAddr)
        : startPC(start), framePC(frame), stackBase(base), funcStackMapAddr(funcStackMapAddr) {}
    StackMapBuilder(uintptr_t start, uintptr_t frame, uint64_t *funcStackMapAddr)
        : startPC(start), framePC(frame), funcStackMapAddr(funcStackMapAddr) {}
    StackMapBuilder(uint8_t *llvmStackMaps) : data_(llvmStackMaps) {}
    ~StackMapBuilder() = default;

    void collectHeapReferenceMap(std::unordered_map<int32_t, std::vector<int32_t>> &base2DerivedOffsets)
    {
        PrologueRegisterClosure closure;
        PrologueVisitor visitor = [&closure](PrologueRegisterClosure::Type type, uint32_t value) {
            switch (type) {
                case PrologueRegisterClosure::Type::CALLEE_REGISTER:
                    closure.calleeSaved.push_back(value);
                    break;
                case PrologueRegisterClosure::Type::OFFSET:
                    closure.offset.push_back(value);
                    break;
            }
        };
        auto head = CompressedStackMapHead::GetStackMapHead(reinterpret_cast<uint8_t*>(funcStackMapAddr), visitor);
        head.CollectStackMapEntry(startPC, framePC, base2DerivedOffsets);
    }

protected:
    uintptr_t startPC = 0;
    uintptr_t framePC = 0;
    uintptr_t stackBase = 0;
    uint64_t *funcStackMapAddr = nullptr;

    uint8_t *data_ = nullptr;
};
} // namespace kotlin::stackMap

#endif // ENABLE_STACKMAP
