/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

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

using CallSiteInfo = std::vector<std::pair<uint16_t, int32_t>>;

class CompressedStackMapEntry {
public:
    CompressedStackMapEntry(const IdxSet& idx, const RegTable& reg, const SlotTable& slot,
                            const DerivedPtrTable derived)
        : idxSet(idx), regTable(reg), slotTable(slot),
          derivedPtrTable(derived) {}
    // explicit CompressedStackMapEntry(bool valid) {}

    ~CompressedStackMapEntry() = default;

    // bool IsValid() const { return isValid; }
    SlotRoot BuildSlotRoot() const
    {
        uint32_t idx = idxSet.slotIdx;
        if (idx == 0) {
            return SlotRoot();
        }
        return SlotRoot(slotTable.GetBaseOffset(idx - 1), slotTable.GetSlotBitMap(idx - 1), slotTable.slotFormat);
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

    // void CollectDerivedPtrSlots(std::vector<int32_t> &derivedSlotOffsets) const
    // {
    //     uint32_t idx = idxSet.derivedPtrIdx;
    //     if (idx == 0) {
    //         return;
    //     }
    //     DerivedPtr(derivedPtrTable, regTable, slotTable, idx).CollectDerivedPtrSlots(derivedSlotOffsets);
    // }

    // SlotRoot BuildStackSlotRoot() const
    // {
    //     uint32_t idx = idxSet.stackSlotIdx;
    //     if (idx == 0) {
    //         return SlotRoot();
    //     }
    //     return SlotRoot(slotTable.GetBaseOffset(idx - 1), slotTable.GetSlotBitMap(idx - 1), slotTable.slotFormat);
    // }

private:
    IdxSet idxSet;
    RegTable regTable;
    SlotTable slotTable;
    DerivedPtrTable derivedPtrTable;
    // bool isValid = false;
};

class CompressedStackMapHead {
public:
    // CompressedStackMapHead(uint8_t* ptr, uint32_t bitPos, const PrologueVisitor& visitor, uint32_t format)
    //     : prologue(ptr, bitPos, visitor), slotFormat(format) {}
    CompressedStackMapHead(const BitsManager& prologueManager,
                           const PrologueVisitor& visitor, uint64_t funcAddress, uint32_t format)
        : prologue_(prologueManager, visitor), funcAddress_(funcAddress), slotFormat_(format) {}
    ~CompressedStackMapHead() = default;
//     static CompressedStackMapHead GetStackMapHead(Uptr addr, const PrologueVisitor& visitor,
//                                                   uint64_t* funcStackMapAddr = nullptr)
//     {
//         uint8_t *stackmapStart = nullptr;
//         if (funcStackMapAddr)
//             stackmapStart = reinterpret_cast<uint8_t*>(reinterpret_cast<FuncDescRef>(funcStackMapAddr)->GetStackMap());
//         else {
// #if defined(__APPLE__)
//             FuncDescRef desc = MFuncDesc::GetFuncDesc(reinterpret_cast<FrameAddress*>(addr));
// #else
//             FuncDescRef desc = MFuncDesc::GetFuncDesc(addr);
// #endif
//             stackmapStart = reinterpret_cast<uint8_t*>(desc->GetStackMap());
//         }
//         StackMapHeaderVarInt stacksizeVarInt(stackmapStart, 0);
//         StackMapHeaderVarInt compressedFormatVarInt(stacksizeVarInt.GetNextTable());
//         uint32_t format = compressedFormatVarInt.GetStacksize();
//         return CompressedStackMapHead(compressedFormatVarInt.GetNextTable(), visitor, format);
//     }

    static CompressedStackMapHead GetStackMapHead(uint8_t *stackmapStart, const PrologueVisitor& visitor)
    {
        uint64_t llvmStackMapSymbolStart = 0;
  #if KONAN_LINUX || KONAN_OHOS
        llvmStackMapSymbolStart = reinterpret_cast<uint64_t>(&__LLVM_StackMaps);
  #else
        llvmStackMapSymbolStart = reinterpret_cast<uint64_t>(&_LLVM_StackMaps);
  #endif
        uint64_t funcAddress = static_cast<uint64_t>(
            *reinterpret_cast<int64_t*>(stackmapStart) + static_cast<int64_t>(llvmStackMapSymbolStart));
        stackmapStart += 8; // skip funcAddress
        stackmapStart += 4; // skip stackMapSize
        StackMapHeaderVarInt stacksizeVarInt(stackmapStart, 0);
        StackMapHeaderVarInt compressedFormatVarInt(stacksizeVarInt.GetNextTable());
        uint32_t format = compressedFormatVarInt.GetStacksize();
#if DUMP_DEBUG_INFO
        uint32_t stackSize = stacksizeVarInt.GetStacksize();
        std::cout << "------- wzl log funcAddress: " << std::hex << funcAddress
                  << std::dec << ", stackSize: " << stackSize << ", format: " << format << std::endl;
#endif
        return CompressedStackMapHead(compressedFormatVarInt.GetNextTable(), visitor, funcAddress, format);
    }

    // CompressedStackMapEntry GetStackMapEntry(Uptr startPC, Uptr framePC) const
    // {
        // StackMapTable stackMapTable(prologue.GetNextTable());
        // auto idxSet = stackMapTable.GetIdxSet(startPC, framePC);
        // if (idxSet.slotIdx == 0 && idxSet.regIdx == 0 && idxSet.lineNumIdx == 0 &&
        //         idxSet.stackRegIdx == 0 && idxSet.stackSlotIdx == 0) {
        //     return CompressedStackMapEntry(false);
        // }
        // RegTable regTable(stackMapTable.GetNextTable());
        // SlotTable slotTable(regTable.GetNextTable(), slotFormat);
        // DerivedPtrTable derivedTable(lineTable.GetNextTable(), stackMapTable.GetRegBitsLen(),
        //                              stackMapTable.GetSlotBitsLen());
        // return CompressedStackMapEntry(idxSet, regTable, slotTable, lineTable, derivedTable, true);
    // }

    void CollectAllStackMapEntry(std::unordered_map<uintptr_t, CallSiteInfo> &pc2CallSiteInfo) const
    {
        StackMapTable stackMapTable(prologue_.GetNextTable());
        std::vector<IdxSet> IdxSetVec;
        stackMapTable.CollectAllIdxSet(IdxSetVec);

        RegTable regTable(stackMapTable.GetNextTable());
        SlotTable slotTable(regTable.GetNextTable(), slotFormat_);
        DerivedPtrTable derivedTable(slotTable.GetNextTable(), stackMapTable.GetRegBitsLen(),
                                     stackMapTable.GetSlotBitsLen());
        for (auto idxSet : IdxSetVec) {
            CompressedStackMapEntry entry(idxSet, regTable, slotTable, derivedTable);
#if DUMP_DEBUG_INFO
            std::cout << "----wzl log funcAddress: " << funcAddress_ + idxSet.pc << std::endl;
#endif
            std::unordered_map<int32_t, std::vector<int32_t>> base2DerivedOffsets;
            entry.CollectBase2DerivedSlotOffsets(base2DerivedOffsets);
            uintptr_t curPC = funcAddress_ + idxSet.pc;
            CallSiteInfo callSiteInfo {};
            for (auto &elem : base2DerivedOffsets) {
                callSiteInfo.emplace_back(std::pair<uint16_t, int32_t>(29, elem.first));
#if DUMP_DEBUG_INFO
                if (elem.second.empty()) {
                    std::cout << "    register: 29, offset: " << elem.first
                              << ", register: 29, offset: " << elem.first << std::endl;
                } else {
                    for (auto derived : elem.second) {
                        std::cout << "    register: 29, offset: " << elem.first
                                  << ", register: 29, offset: " << derived << std::endl;
                    }
                }
#endif
            }
            pc2CallSiteInfo[curPC] = callSiteInfo;
        }
    }

    void CollectStackMapEntry(uintptr_t startPC, uintptr_t curPC,
        std::unordered_map<int32_t, std::vector<int32_t>> &base2DerivedOffsets) const
    {
        StackMapTable stackMapTable(prologue_.GetNextTable());
        std::vector<IdxSet> IdxSetVec;
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
    uint64_t funcAddress_;
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

//     template<class MapType>
//     MapType Build() const
//     {
//         PrologueRegisterClosure closure;
//         PrologueVisitor visitor = [&closure](PrologueRegisterClosure::Type type, uint32_t value) {
//             switch (type) {
//                 case PrologueRegisterClosure::Type::CALLEE_REGISTER:
//                     closure.calleeSaved.push_back(value);
//                     break;
//                 case PrologueRegisterClosure::Type::OFFSET:
//                     closure.offset.push_back(value);
//                     break;
//             }
//         };
// #ifdef __APPLE__
//         auto head = CompressedStackMapHead::GetStackMapHead(stackBase, visitor);
// #else
//         auto head = CompressedStackMapHead::GetStackMapHead(startPC, visitor);
// #endif
//         auto entry = head.GetStackMapEntry(startPC, framePC);
//         if (!entry.IsValid()) {
//             return MapType(stackBase, std::move(closure));
//         }
//         return MapType(true, stackBase, entry, std::move(closure));
//     }

    void build() {
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
        uint64_t funcCount = *reinterpret_cast<const uint64_t*>(data_);
#if DUMP_DEBUG_INFO
        std::cout << "-----wzl log funcCount: " << funcCount << std::endl;
#endif
        data_ += 8; // skip funcCount
        for (uint64_t i = 0; i < funcCount; ++i) {
            auto head = CompressedStackMapHead::GetStackMapHead(data_, visitor);
            head.CollectAllStackMapEntry(pc2CallSiteInfo_);

            uint32_t stackMapSize = *reinterpret_cast<const uint32_t*>(data_ + 8); // 8: skip funcAddress
            data_ = data_ + stackMapSize;
        }
    }

    void collectHeapReferenceMap(std::unordered_map<int32_t, std::vector<int32_t>> &base2DerivedOffsets) {
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

    void print() {
// #if DUMP_DEBUG_INFO
        for (auto &pc2CallSiteInfo : pc2CallSiteInfo_) {
            std::cout << "function address: 0x" << std::hex << pc2CallSiteInfo.first << "\n";
            for (auto &callsite : pc2CallSiteInfo.second) {
            std::cout << std::dec << "  dwarfRegNum_: " << callsite.first << " "
                        << "offsetOrSmallConstant: " << callsite.second << "\n";
            }
        }
// #endif
    }
    void calcCallSite();
    std::unordered_map<uintptr_t, CallSiteInfo> &pc2CallSiteInfo() {
        return pc2CallSiteInfo_;
    }

protected:
    uintptr_t startPC = 0;
    uintptr_t framePC = 0;
    uintptr_t stackBase = 0;
    uint64_t *funcStackMapAddr = nullptr;


    uint8_t *data_ = nullptr;
    std::unordered_map<uintptr_t, CallSiteInfo> pc2CallSiteInfo_;
};
} // namespace kotlin::stackMap
