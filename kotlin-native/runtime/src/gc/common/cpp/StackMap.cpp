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

#ifdef ENABLE_STACKMAP
// Integral stackmap implementation file. The ON path includes the full
// implementation; the OFF path defines no symbols (all callers must be
// gated under the same macro).


#include "StackMap.hpp"

#include <cstring>
#include <iostream>
#include <cassert>

#define DUMP_DEBUG_INFO 0

#if KONAN_LINUX || KONAN_OHOS
extern "C" uint8_t __LLVM_StackMaps;
#else
extern "C" uint8_t _LLVM_StackMaps;
#endif

namespace kotlin {

constexpr uint32_t ALIGNMENT_8BYTE_MASK = 7;
stackMap::StackMap::~StackMap() {}

void stackMap::StackMap::Build()
{
    header = llvmStackMaps.Read<StackMapHeader>();
    uint32_t numFunctions = llvmStackMaps.Read<uint32_t>();
    uint32_t numConstants = llvmStackMaps.Read<uint32_t>();
    uint32_t numRecords = llvmStackMaps.Read<uint32_t>();

    for (uint32_t i = 0; i < numFunctions; ++i) {
        auto stkSizeRecord = llvmStackMaps.Read<StkMapSizeRecord>();
        stkSizeRecords_.push_back(stkSizeRecord);
    }

    for (uint32_t i = 0; i < numConstants; ++i) {
        auto constant = llvmStackMaps.Read<Constant>();
        constants_.push_back(constant);
    }

    for (uint32_t i = 0; i < numRecords; ++i) {
        StkMapRecord stkMapRecord;
        stkMapRecord.header = llvmStackMaps.Read<StkMapRecordHeader>();
        for (uint16_t i = 0; i < stkMapRecord.header.numLocations; ++i) {
            auto location = llvmStackMaps.Read<Location>();
            stkMapRecord.locations_.push_back(location);
        }
        while (llvmStackMaps.GetOffset() & ALIGNMENT_8BYTE_MASK) {
            llvmStackMaps.Read<uint16_t>();
        }
        uint32_t numLiveOuts = llvmStackMaps.Read<uint32_t>();
        for (uint32_t i = 0; i < numLiveOuts; ++i) {
            auto liveOut = llvmStackMaps.Read<LiveOuts>();
            stkMapRecord.liveOuts_.push_back(liveOut);
        }
        while (llvmStackMaps.GetOffset() & ALIGNMENT_8BYTE_MASK) {
            llvmStackMaps.Read<uint16_t>();
        }
        stkMapRecords_.push_back(stkMapRecord);
    }
    CalcCallSite();
}

void stackMap::StackMap::Print()
{
#if DUMP_DEBUG_INFO
    header.Print();
    for (auto &stkSizeRecord : stkSizeRecords_) {
        stkSizeRecord.Print();
    }
    for (auto &constant : constants_) {
        constant.Print();
    }
    for (auto &stkMapRecord : stkMapRecords_) {
        stkMapRecord.Print();
    }

    for (auto &pc2CallSiteInfo : pc2CallSiteInfo_) {
        std::cout << "function address: 0x" << std::hex << pc2CallSiteInfo.first << "\n";
        for (auto &callsite : pc2CallSiteInfo.second) {
            std::cout << std::dec << "  dwarfRegNum_: " << callsite.first << " "
                      << "offsetOrSmallConstant: " << callsite.second << "\n";
        }
    }
#endif
}

void stackMap::StackMap::CalcCallSite()
{
    uint64_t recordNum = 0;
    auto calc = [this, &recordNum](uintptr_t address, uint32_t recordId) {
        auto &record = stkMapRecords_[recordNum + recordId];
        auto &recordHeader = record.header;
        uintptr_t insnPC = address + recordHeader.instructionOffset;
        if (pc2CallSiteInfo_.find(insnPC) == pc2CallSiteInfo_.end()) {
            auto p = std::pair<uintptr_t, CallSiteInfo>(insnPC, {});
            pc2CallSiteInfo_.insert(p);
        }

        assert(recordHeader.numLocations > Location::constantDeoptCntIndex);
        const int lastDeoptIndex =
            record.locations_[Location::constantDeoptCntIndex].offsetOrSmallConstant +
            Location::constantDeoptCntIndex;
        assert(lastDeoptIndex == Location::constantDeoptCntIndex && "deopt count must be 0");
        CallSiteInfo &callsiteInfo = pc2CallSiteInfo_[insnPC];
        for (int i = Location::constantFirstElementIndex; i < recordHeader.numLocations; ++i) {
            auto &loc = record.locations_[i];
            if (i <= lastDeoptIndex) {
            } else {
                switch (loc.location) {
                    case Location::Kind::REGISTER:
                    case Location::Kind::DIRECT: {
                        assert(false && "not supported location kind currently");
                        std::pair<uint16_t, int32_t> info(loc.dwarfRegNum, loc.offsetOrSmallConstant);
                        callsiteInfo.emplace_back(info);
                        break;
                    }
                    case Location::Kind::INDIRECT:
                    case Location::Kind::CONSTANT:
                    case Location::Kind::CONSTANTINDEX: {
                        assert(loc.location == Location::Kind::INDIRECT &&
                            "only INDIRECT kind is supported currently");
                        if (i % 2 == 0) { // derived ptr
                            break;
                        }
                        std::pair<uint16_t, int32_t> info(loc.dwarfRegNum, loc.offsetOrSmallConstant);
                        callsiteInfo.emplace_back(info);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    };
    for (auto &stkSizeRecord : stkSizeRecords_) {
    #if KONAN_LINUX || KONAN_OHOS
        uintptr_t funcAddr = (int64_t)stkSizeRecord.funcAddrOffset + reinterpret_cast<uint64_t>(&__LLVM_StackMaps);
    #else
        uintptr_t funcAddr = (int64_t)stkSizeRecord.funcAddrOffset + reinterpret_cast<uint64_t>(&_LLVM_StackMaps);
    #endif
        uint64_t recordCount = stkSizeRecord.recordCount;
        for (uint64_t k = 0; k < recordCount; ++k) {
            calc(funcAddr, k);
        }
        recordNum += recordCount;
    }
}
} // namespace kotlin

std::string kotlin::stackMap::Location::KindToString() const
{
    switch (location) {
        case Kind::REGISTER:
            return "Register";
        case Kind::DIRECT:
            return "Direct";
        case Kind::INDIRECT:
            return "Indirect";
        case Kind::CONSTANT:
            return "Constant";
        case Kind::CONSTANTINDEX:
            return "ConstantIndex";
        default:
            return "unknown location";
    }
}

#endif // ENABLE_STACKMAP
