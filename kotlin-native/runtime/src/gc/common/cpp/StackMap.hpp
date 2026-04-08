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
  uint8_t version_;
  uint8_t reserved1_;
  uint16_t reserved2_;
  void print() const {
    std::cout << "_LLVM_StackMaps version: "
              << static_cast<uint32_t>(version_) << "\n";
  }
};

struct StkMapSizeRecord {
  /** Byte offset of the function record from the start of the LLVM stack map section. */
  uint64_t funcAddrOffset_;
  uint64_t stackSize_;
  uint64_t recordCount_;
  void print() const {
#if KONAN_LINUX || KONAN_OHOS
    uintptr_t funcAddr = (int64_t)funcAddrOffset_ + reinterpret_cast<uint64_t>(&__LLVM_StackMaps);
#else
    uintptr_t funcAddr = (int64_t)funcAddrOffset_ + reinterpret_cast<uint64_t>(&_LLVM_StackMaps);
#endif
    std::cout << "function address: 0x" << std::hex << funcAddr
              << "  stackSize: " << stackSize_
              << "  recordCount: " << recordCount_ << "\n";
  }
};

struct Constant {
  int64_t constant_;
  void print() const {
    std::cout << "constant: 0x" << std::hex << constant_;
  }
};

struct StkMapRecordHeader {
  uint64_t patchPointID_;
  uint32_t instructionOffset_;
  uint16_t reserved_;
  uint16_t numLocations_;
  void print() const {
    std::cout << "CallsiteRecord id: " << patchPointID_ << "\n";
    std::cout << "CallsiteRecord offset: " << instructionOffset_ << "\n";
    std::cout << "CallsiteRecord reserved: " << reserved_ << "\n";
    std::cout << "CallsiteRecord numLocations: " << numLocations_ << "\n";
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
  static constexpr int CONSTANT_FIRST_ELEMENT_INDEX = 3;
  static constexpr int CONSTANT_DEOPT_CNT_INDEX = 2;

  Kind location_;
  uint8_t reserved0_;
  uint16_t locationSize_;
  uint16_t dwarfRegNum_;
  uint16_t reserved1_;
  int32_t offsetOrSmallConstant_;

  std::string kindToString() const;

  void print() const {
    std::cout << "location_: " << kindToString() << " "
              << "locationSize_: " << std::dec << locationSize_ << " "
              << "dwarfRegNum_: " << dwarfRegNum_ << " "
              << "offsetOrSmallConstant: " << offsetOrSmallConstant_ << "\n";

  }
};

struct LiveOuts {
  uint16_t dwarfRegNum_;
  uint8_t reserved_;
  uint8_t sizeInBytes_;
  void print() const {
    std::cout << "dwarfRegNum_: " << dwarfRegNum_ << " "
              << "reserved_: " << std::dec << reserved_ << " "
              << "sizeInBytes_: " << sizeInBytes_ << "\n";
  }
};

struct StkMapRecord {
  StkMapRecordHeader header_;
  std::vector<Location> locations_;
  std::vector<LiveOuts> liveOuts_;
  void print() const {
    header_.print();
    for (auto &location : locations_) {
      location.print();
    }
    for (auto liveOut : liveOuts_) {
      liveOut.print();
    }
  }
};

class DataInfo {
public:
  explicit DataInfo(const uint8_t *data) : data_(data), offset_(0) {
        if (reinterpret_cast<uint64_t>(data_) == 0) {
           RuntimeLogInfo({kTagGC}, "[CRT] Run in DataInfo(): the data_ is nullptr");
           std::abort();
        }
  }
  ~DataInfo() = default;

  template<class T>
  T read() {
    T value = *reinterpret_cast<const T*>(data_ + offset_);
    offset_ += sizeof(T);
    return value;
  }

  uint32_t getOffset() const {
    return offset_;
  }

private:
  const uint8_t *data_;
  uint32_t offset_;
};

struct StackMap {
public:
  using CallSiteInfo = std::vector<std::pair<uint16_t, int32_t>>;
  StackMap(uint8_t *llvmStackMaps) : llvmStackMaps_(llvmStackMaps) {}
  ~StackMap();

  void build();
  void print();
  void calcCallSite();
  std::unordered_map<uintptr_t, CallSiteInfo> &pc2CallSiteInfo() {
    return pc2CallSiteInfo_;
  }

private:
  DataInfo llvmStackMaps_;
  StackMapHeader header_;
  std::vector<StkMapSizeRecord> stkSizeRecords_;
  std::vector<Constant> constants_;
  std::vector<StkMapRecord> stkMapRecords_;
  std::unordered_map<uintptr_t, CallSiteInfo> pc2CallSiteInfo_;
};
} // namespace kotlin::stackMap
