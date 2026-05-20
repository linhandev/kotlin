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

#include <cstdint>
#include <vector>
#include <iostream>
#include "Macros.hpp"
#include <functional>

namespace kotlin::stackMap {
using VarValue = uint32_t;
using BitLen = uint32_t;
using VarPair = std::pair<VarValue, BitLen>;
using DerivedPtrPair = std::pair<uint32_t, uint32_t>;

#define DUMP_DEBUG_INFO 0

class BitsManager {
public:
    BitsManager() = default;
    BitsManager(uint8_t* ptr, uint32_t pos) : addr(ptr), bitPos(pos) {}
    BitsManager(const BitsManager& other) : addr(other.addr), bitPos(other.bitPos) {}
    BitsManager& operator=(const BitsManager& other)
    {
        if (this == &other) {
            return *this;
        }
        addr = other.addr;
        bitPos = other.bitPos;
        return *this;
    }
    BitsManager(BitsManager&& other) : addr(other.addr), bitPos(other.bitPos)
    {
        other.addr = nullptr;
        other.bitPos = 0;
    }
    BitsManager& operator=(BitsManager&& other)
    {
        if (this == &other) {
            return *this;
        }
        addr = other.addr;
        bitPos = other.bitPos;
        other.addr = nullptr;
        other.bitPos = 0;
        return *this;
    }
    ~BitsManager() { addr = nullptr; }

    uint32_t GetBits(uint32_t bitLen) const
    {
        uint32_t bitsMask = static_cast<uint32_t>((1ULL << bitLen) - 1);
        return ((ConnectBytesToU64() >> bitPos) & bitsMask);
    }

    void* getAddr() const { return addr; }
    ATTR_NO_INLINE BitsManager GetNext(uint32_t bitsLen) const;

private:
    // we don't use reinterpret_cast<uint64_t*> because of the effect of big-endien.
    uint64_t ConnectBytesToU64() const;
    static constexpr uint32_t bitsNumPerByte = 8;
    static constexpr uint32_t bitsShiftPerByte = 3;
    static constexpr uint32_t bitsNumHalfByte = bitsNumPerByte >> 1;
    static constexpr uint16_t halfByteMask = (1 << bitsNumHalfByte) - 1;
    uint8_t* addr{ nullptr };
    uint32_t bitPos{ 0 };
};

// VarInt has two section
// |   tag  |         payload               |
// | 4 bits | 8/16/24/32 bits depends on tag|
// e.g  varInt = 0b0101 GetValue() = {3, 4}
// varInt = 0b1100 00010001 GetValue() = {0x11, 12}
// varInt = 0b1101 00010001 00010001 GetValue() = {0x1111, 20}
// varInt = 0b1110 00010001 00010001 00010001 GetValue() = {0x111111, 28}
// varInt = 0b1111 00010001 00010001 00010001 00010001 GetValue() = {0x11111111, 36}
class VarInt {
public:
    enum TagType : uint8_t {
        MAX_VALID_VALUE = 11,
        VAR_VALUE8 = 12,
        VAR_VALUE16 = 13,
        VAR_VALUE24 = 14,
        VAR_VALUE32 = 15,
    };
    enum BitsLen : uint32_t {
        TAG_LEN = 4,
        FIRST_STEP_VAR_BITS = 8,
        SECOND_STEP_VAR_BITS = 16,
        THIRD_STEP_VAR_BITS = 24,
        FORTH_STEP_VAR_BITS = 32,
    };
    VarInt() = delete;
    VarInt(uint8_t* ptr, uint32_t pos) : bits(ptr, pos) {}
    explicit VarInt(const BitsManager& bitsManager) : bits(bitsManager) {}
    explicit VarInt(BitsManager&& bitsManager) : bits(bitsManager) {}
    ~VarInt() = default;
    ATTR_NO_INLINE VarPair GetValue() const;

private:
    VarValue GetPayload(BitsLen bitsLen) const;
    BitsManager bits;
};

class TableAPI {
public:
    TableAPI() = default;
    TableAPI(uint8_t* tableAddrStart, uint32_t tableBitStart) : tableBits(tableAddrStart, tableBitStart) {}
    explicit TableAPI(const BitsManager& bits) : tableBits(bits) {}
    explicit TableAPI(BitsManager&& bits) : tableBits(bits) {}
    virtual ~TableAPI() = default;
    BitsManager GetNextTable() const { return nextTable; };

protected:
    ATTR_NO_INLINE BitsManager ResolveHeader(uint32_t headerInfo[], uint32_t size);
    BitsManager tableBits;
    uint32_t rowBitsLen{ 0 };
    BitsManager data;
    BitsManager nextTable;
};

struct PrologueRegisterClosure {
    PrologueRegisterClosure() = default;
    PrologueRegisterClosure(PrologueRegisterClosure&& other)
        : calleeSaved(std::move(other.calleeSaved)), offset(std::move(other.offset)) {}
    PrologueRegisterClosure& operator=(PrologueRegisterClosure&& other)
    {
        if (this == &other) {
            return *this;
        }
        calleeSaved = std::move(other.calleeSaved);
        offset = std::move(other.offset);
        return *this;
    }

    enum class Type : uint8_t { CALLEE_REGISTER, OFFSET };
    ~PrologueRegisterClosure() = default;
    std::vector<uint32_t> calleeSaved;
    std::vector<uint32_t> offset;
};

using PrologueVisitor = std::function<void(PrologueRegisterClosure::Type, uint32_t)>;
// Prologue Table : 1 columns
// | VarInt   |  the first row is a varInt that records the bit map of callee-saved register.
// | VarInt[] |  the next n rows is a varInt matrix that records the offset of the slot saving callee-saved register.
class PrologueVarInt {
public:
    PrologueVarInt(uint8_t* ptr, uint32_t bitPos, const PrologueVisitor& visitor) : prologue(ptr, bitPos)
    {
        ResolvePrologue(visitor);
    }
    explicit PrologueVarInt(const BitsManager& bitsManager, const PrologueVisitor& visitor) : prologue(bitsManager)
    {
        ResolvePrologue(visitor);
    }

    ~PrologueVarInt() = default;

    BitsManager GetNextTable() const { return nextTable; }

private:
    void ResolvePrologue(const PrologueVisitor& visitor);
    BitsManager prologue;
    BitsManager nextTable;
};

// Register Table Header : 2 columns
// | VarInt num |  VarInt bitLength |
// |     @1     |        @2         |
// ----------------------------------
// Register Table : 1 columns
// | num * bitMap |
// bitMap represents the active registers, and it is encoded in different ways according to the hardware platform.
// Get the encoding ways in stackMap_aarch64.h and stackMap_X86.h
// num is from @1, the bit length of bitMap is from @2
class RegTable : public TableAPI {
public:
    RegTable() = default;
    RegTable(uint8_t* tableAddrStart, uint32_t tableBitStart) : TableAPI(tableAddrStart, tableBitStart) { Init(); }
    explicit RegTable(const BitsManager& bits) : TableAPI(bits) { Init(); }
    explicit RegTable(BitsManager&& bits) : TableAPI(bits) { Init(); }
    ~RegTable() = default;

    uint32_t GetActiveRegBits(uint32_t row) const
    {
        return data.GetNext(row * rowBitsLen).GetBits(headerInfo[BITS_LEN]);
    }

private:
    void Init();
    enum HeaderColTag {
        RECORD_NUM = 0,
        BITS_LEN,
        HEADER_COL_NUM,
    };
    uint32_t headerInfo[HEADER_COL_NUM]{ 0 };
};

// Slot Table Header : 3 columns
// | VarInt num | VarInt bitLength | VarInt bitLength |
// |      @1    |        @2        |        @3        |
// ----------------------------------------------------
// Slot Table : 2 columns
// | baseOffset | bitmap |
// baseOffset is compressed signed integer which represents the basic offset of all slots with respect to stack bottom.
// bitmap represents the slot in stack
// e.g. baseOffset = 0b10111000 (-72) bitmap = 0b11
// then we resolve them as :
// ((-72) + 0) * 8 = -576, ((-72) + 1) * 8 = -568
class SlotTable : public TableAPI {
public:
    SlotTable() = default;
    SlotTable(uint8_t* tableAddrStart, uint32_t tableBitStart, uint32_t format)
        : TableAPI(tableAddrStart, tableBitStart), slotFormat(format) { Init(); }
    SlotTable(const BitsManager& bits, uint32_t format) : TableAPI(bits), slotFormat(format) { Init(); }
    SlotTable(BitsManager&& bits, uint32_t format) : TableAPI(bits), slotFormat(format) { Init(); }
    ~SlotTable() = default;

    int32_t GetBaseOffset(uint32_t row) const;
    std::vector<SlotBits> GetSlotBitMap(uint32_t row) const;
    uint32_t slotFormat;

private:
    void Init();
    // WAHSlotBit is decompressed into a vector which value has 3 types:
    // | bit31 | bit30 | bit29...bit0 |
    // |   0   |   1   |    varInt    |  ====> (varInt * 31) bits of 1
    // |   0   |   0   |    varInt    |  ====> (varInt * 31) bits of 0
    // |   1   |       RawData        |  ====> 31 valid bits data.
    std::vector<SlotBits> GetWAHSlotBitMap(uint32_t row) const;
    enum HeaderColTag {
        RECORD_NUM = 0,
        BASE_OFF_BITS_LEN,
        BIT_MAP_BITS_LEN,
        HEADER_COL_NUM,
    };

    enum BaseOffsetType {
        SIGNED8 = 8,
        SIGNED16 = 16,
        SIGNED32 = 32,
    };
    uint32_t headerInfo[HEADER_COL_NUM]{ 0 };
};

struct IdxSet {
    IdxSet() : pc(0), regIdx(0), slotIdx(0), derivedPtrIdx(0) {}
    IdxSet(uint32_t pc, uint32_t reg, uint32_t slot, uint32_t derivePtr)
        : pc(pc), regIdx(reg), slotIdx(slot), derivedPtrIdx(derivePtr) {}
    IdxSet(uint32_t reg, uint32_t slot, uint32_t derivePtr)
        : regIdx(reg), slotIdx(slot), derivedPtrIdx(derivePtr) {}
    uint32_t pc = 0;
    uint32_t regIdx;
    uint32_t slotIdx;
    uint32_t derivedPtrIdx;
};

class StackMapTable : public TableAPI {
public:
    StackMapTable(uint8_t* tableAddrStart, uint32_t tableBitStart) : TableAPI(tableAddrStart, tableBitStart) { Init(); }
    explicit StackMapTable(const BitsManager& bits) : TableAPI(bits) { Init(); }
    explicit StackMapTable(BitsManager&& bits) : TableAPI(bits) { Init(); }
    ~StackMapTable() = default;
    IdxSet GetIdxSet(Uptr startPC, Uptr framePC) const;

    void CollectAllIdxSet(std::vector<IdxSet> &idxSetVec) const;

    // Iterator over all IdxSet records, used by the precise CRT root visitor.
    class IdxSetIterator {
    public:
        IdxSetIterator(const StackMapTable& table, uint32_t pos) : table(table), i(pos) {}
        IdxSetIterator& operator++() { return (++i, *this); }
        bool operator!=(const IdxSetIterator& other) const { return i != other.i; }
        IdxSet operator*() const
        {
            return IdxSet(table.PCAt(i), table.RegIdxAt(i), table.SlotIdxAt(i), table.DerivePtrIdxAt(i));
        }
        const StackMapTable& table;
        uint32_t i;
    };
    IdxSetIterator IdxSetBegin() const { return IdxSetIterator(*this, 0); }
    IdxSetIterator IdxSetEnd() const { return IdxSetIterator(*this, headerInfo[RECORD_NUM]); }

    uint32_t GetRegBitsLen() const { return headerInfo[REG_BITS_LEN]; }
    uint32_t GetSlotBitsLen() const { return headerInfo[SLOT_BITS_LEN]; }

private:
    void Init();
    uint32_t PCAt(uint32_t row) const;
    uint32_t RegIdxAt(uint32_t row) const;
    uint32_t SlotIdxAt(uint32_t row) const;
    uint32_t DerivePtrIdxAt(uint32_t row) const;
    enum HeaderColTag : uint32_t {
        RECORD_NUM = 0,
        REG_BITS_LEN,
        SLOT_BITS_LEN,
        DERIVE_PTR_BITS_LEN,
        PADDING_BITS_LEN,
        HEADER_COL_NUM,
    };
    static constexpr uint32_t stackItemNum = 0;
    static constexpr uint32_t pcOffBits = 32;
    uint32_t headerInfo[HEADER_COL_NUM]{ 0 };
};

// DerivedPtrTable doesn't have header info, the bits length is the same as stack map table.
class DerivedPtrTable : public TableAPI {
public:
    DerivedPtrTable() = default;
    explicit DerivedPtrTable(const BitsManager& bits, uint32_t regBits, uint32_t slotBits)
        : TableAPI(bits), regBitsLen(regBits), slotBitsLen(slotBits) { Init(); }
    explicit DerivedPtrTable(BitsManager&& bits, uint32_t regBits, uint32_t slotBits)
        : TableAPI(bits), regBitsLen(regBits), slotBitsLen(slotBits) { Init(); }
    ~DerivedPtrTable() = default;
    DerivedPtrPair GetDerivePair(uint32_t row) const;

private:
    void Init();
    enum HeaderTag {
        RECORD_NUM = 0,
        HEADER_COL_NUM,
    };
    uint32_t headerInfo[HEADER_COL_NUM];
    uint32_t regBitsLen = 0;
    uint32_t slotBitsLen = 0;
};
} // namespace kotlin::stackMap