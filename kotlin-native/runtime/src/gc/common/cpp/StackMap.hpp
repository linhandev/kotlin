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

#include <stdint.h>
#include <vector>
#include <unordered_map>
#include <iostream>
#include "Logging.hpp"
#include "Runtime.h"

#if KONAN_LINUX || KONAN_OHOS
extern "C" uint8_t __LLVM_StackMaps;
#else
extern "C" uint8_t _LLVM_StackMaps;
#endif
namespace kotlin::stackMap {

struct StackMapHeader {
    uint8_t version;
    uint8_t reserved1;
    uint16_t reserved2;
    void Print() const {
        std::cout << "_LLVM_StackMaps version: " <<
                  static_cast<uint32_t>(version) << "\n";
    }
};

struct StkMapSizeRecord {
    /** Byte offset of the function record from the start of the LLVM stack map section. */
    uint64_t funcAddrOffset;
    uint64_t stackSize;
    uint64_t recordCount;
    void Print() const {
#if KONAN_LINUX || KONAN_OHOS
        uintptr_t funcAddr = (int64_t)funcAddrOffset + reinterpret_cast<uint64_t>(&__LLVM_StackMaps);
#else
        uintptr_t funcAddr = (int64_t)funcAddrOffset + reinterpret_cast<uint64_t>(&_LLVM_StackMaps);
#endif
        std::cout << "function address: 0x" << std::hex << funcAddr <<
                  "  stackSize: " << stackSize <<
                  "  recordCount: " << recordCount << "\n";
    }
};

struct Constant {
    int64_t constant;
    void Print() const {
        std::cout << "constant: 0x" << std::hex << constant;
    }
};

struct StkMapRecordHeader {
    uint64_t patchPointId;
    uint32_t instructionOffset;
    uint16_t reserved;
    uint16_t numLocations;
    void Print() const {
        std::cout << "CallsiteRecord id: " << patchPointId << "\n";
        std::cout << "CallsiteRecord offset: " << instructionOffset << "\n";
        std::cout << "CallsiteRecord reserved: " << reserved << "\n";
        std::cout << "CallsiteRecord numLocations: " << numLocations << "\n";
    }
};

struct Location {
    enum class Kind: uint8_t {
        REGISTER = 1,
        DIRECT = 2,
        INDIRECT = 3,
        CONSTANT = 4,
        CONSTANTINDEX = 5,
    };
    static constexpr int constantFirstElementIndex = 3;
    static constexpr int constantDeoptCntIndex = 2;

    Kind location;
    uint8_t reserved0;
    uint16_t locationSize;
    uint16_t dwarfRegNum;
    uint16_t reserved1;
    int32_t offsetOrSmallConstant;

    std::string KindToString() const;

    void Print() const {
        std::cout << "location_: " << KindToString() << " " <<
                  "locationSize_: " << std::dec << locationSize << " " <<
                  "dwarfRegNum_: " << dwarfRegNum << " " <<
                  "offsetOrSmallConstant: " << offsetOrSmallConstant << "\n";
    }
};

struct LiveOuts {
    uint16_t dwarfRegNum;
    uint8_t reserved;
    uint8_t sizeInBytes;
    void Print() const {
        std::cout << "dwarfRegNum_: " << dwarfRegNum << " " <<
                  "reserved_: " << std::dec << reserved << " " <<
                  "sizeInBytes_: " << sizeInBytes << "\n";
    }
};

struct StkMapRecord {
    StkMapRecordHeader header;
    std::vector<Location> locations_;
    std::vector<LiveOuts> liveOuts_;
    void Print() const
    {
        header.Print();
        for (auto &location : locations_) {
            location.Print();
        }
        for (auto liveOut : liveOuts_) {
            liveOut.Print();
        }
    }
};

class DataInfo {
public:
    explicit DataInfo(const uint8_t *data) :
        data_(data), offset_(0)
    {
        if (reinterpret_cast<uint64_t>(data_) == 0) {
            RuntimeLogInfo({kTagGC}, "[CRT] Run in DataInfo(): the data_ is nullptr");
            std::abort();
        }
    }
    ~DataInfo() = default;

    template<class T>
    T Read()
    {
        T value = *reinterpret_cast<const T*>(data_ + offset_);
        offset_ += sizeof(T);
        return value;
    }

    uint32_t GetOffset() const
    {
        return offset_;
    }

private:
    const uint8_t *data_;
    uint32_t offset_;
};

struct StackMap {
public:
    using CallSiteInfo = std::vector<std::pair<uint16_t, int32_t>>;
    StackMap(uint8_t *llvmStackMaps) :
        llvmStackMaps(llvmStackMaps) {}
    ~StackMap();

    void Build();
    void Print();
    void CalcCallSite();
    std::unordered_map<uintptr_t, CallSiteInfo> &pc2CallSiteInfo()
    {
        return pc2CallSiteInfo_;
    }

private:
    DataInfo llvmStackMaps;
    StackMapHeader header;
    std::vector<StkMapSizeRecord> stkSizeRecords_;
    std::vector<Constant> constants_;
    std::vector<StkMapRecord> stkMapRecords_;
    std::unordered_map<uintptr_t, CallSiteInfo> pc2CallSiteInfo_;
};
} // namespace kotlin::stackMap
