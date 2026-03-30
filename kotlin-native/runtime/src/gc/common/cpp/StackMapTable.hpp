/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include <cstdint>
#include <vector>
#include <iostream>
#include "Macros.hpp"

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
    ATTR_NO_INLINE BitsManager GetNext(uint32_t bitsLen) const
    {
        uint32_t addrStep = bitsLen >> BITS_SHIFT_PER_BYTE;
        constexpr uint32_t bitsMask = (1 << BITS_SHIFT_PER_BYTE) - 1;
        uint32_t bitPosStep = bitsLen & bitsMask;
        uint8_t* nextAddr = addr + addrStep;
        uint32_t nextBitPos = bitPos + bitPosStep;
        if (nextBitPos >= BITS_NUM_PER_BYTE) {
            ++nextAddr;
            nextBitPos -= BITS_NUM_PER_BYTE;
        }
        return BitsManager(nextAddr, nextBitPos);
    }

private:
    // we don't use reinterpret_cast<uint64_t*> because of the effect of big-endien.
    uint64_t ConnectBytesToU64() const
    {
        constexpr uint32_t len = sizeof(uint32_t) / sizeof(uint8_t) + 1;
        uint64_t value = 0;
        uint32_t shiftSteps = 0;
        for (uint32_t i = 0; i < len; ++i, shiftSteps += BITS_NUM_PER_BYTE) {
            value |= static_cast<uint64_t>(addr[i]) << shiftSteps;
        }
        return value;
    }
    static constexpr uint32_t BITS_NUM_PER_BYTE = 8;
    static constexpr uint32_t BITS_SHIFT_PER_BYTE = 3;
    static constexpr uint32_t BITS_NUM_HALF_BYTE = BITS_NUM_PER_BYTE >> 1;
    static constexpr uint16_t HALF_BYTE_MASK = (1 << BITS_NUM_HALF_BYTE) - 1;
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
        MAX_VALID_VALUE = 11, // When the tag is no greater than 11, the valid value is tag.
        VAR_VALUE8 = 12,      // When the tag is equal to 12, the valid value is 8 bits of varValue.
        VAR_VALUE16 = 13,     // When the tag is equal to 13, the valid value is 16 bits of varValue.
        VAR_VALUE24 = 14,     // When the tag is equal to 14, the valid value is 24 bits of varValue.
        VAR_VALUE32 = 15,     // When the tag is equal to 15, the valid value is 32 bits of varValue.
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
    ATTR_NO_INLINE VarPair GetValue() const
    {
        uint8_t tag = static_cast<uint8_t>(bits.GetBits(TAG_LEN));
        if (tag <= MAX_VALID_VALUE) {
            return std::make_pair(static_cast<VarValue>(tag), TAG_LEN);
        }
        if (tag == VAR_VALUE8) {
            VarValue value = GetPayload(FIRST_STEP_VAR_BITS);
            return std::make_pair(value, TAG_LEN + FIRST_STEP_VAR_BITS);
        }
        if (tag == VAR_VALUE16) {
            VarValue value = GetPayload(SECOND_STEP_VAR_BITS);
            return std::make_pair(value, TAG_LEN + SECOND_STEP_VAR_BITS);
        }
        if (tag == VAR_VALUE24) {
            uint32_t value = GetPayload(THIRD_STEP_VAR_BITS);
            return std::make_pair(value, TAG_LEN + THIRD_STEP_VAR_BITS);
        }
        VarValue value = GetPayload(FORTH_STEP_VAR_BITS);
        return std::make_pair(value, TAG_LEN + FORTH_STEP_VAR_BITS);
    }

private:
    VarValue GetPayload(BitsLen bitsLen) const
    {
        BitsManager payload = bits.GetNext(TAG_LEN);
        return payload.GetBits(bitsLen);
    }
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
    ATTR_NO_INLINE BitsManager ResolveHeader(uint32_t headerInfo[], uint32_t size)
    {
        BitsManager cur(tableBits);
        for (uint32_t i = 0; i < size; ++i) {
            VarInt varInt(cur);
            VarPair headerPair = varInt.GetValue();
            VarValue value = headerPair.first;
            headerInfo[i] = value;
            cur = cur.GetNext(headerPair.second);
        }
        return cur;
    }
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
    void ResolvePrologue(const PrologueVisitor& visitor)
    {
        VarInt regBits(prologue);
        VarPair varPair = regBits.GetValue();
        uint32_t bitMap = varPair.first;
        uint32_t bitLen = varPair.second;
        uint32_t size = 0;
#if DUMP_DEBUG_INFO
        std::cout << "-----wzl log bitMap: " << bitMap;
#endif
        for (uint32_t i = 0; bitMap != 0; ++i, bitMap >>= 1) {
            constexpr uint32_t bitMask = 0x1;
            if ((bitMap & bitMask) == 0) {
                continue;
            }
            if (visitor != nullptr) {
                visitor(PrologueRegisterClosure::Type::CALLEE_REGISTER, i);
            }
            ++size;
        }
#if DUMP_DEBUG_INFO
        std::cout << ", size: " << size << std::endl;
#endif
        BitsManager offsetBitManager = prologue.GetNext(bitLen);
        for (uint32_t i = 0; i < size; ++i) {
            VarInt offsetVarInt(offsetBitManager);
            VarPair offsetPair = offsetVarInt.GetValue();
            if (visitor != nullptr) {
                visitor(PrologueRegisterClosure::Type::OFFSET, offsetPair.first);
            }
            offsetBitManager = offsetBitManager.GetNext(offsetPair.second);
        }
        nextTable = offsetBitManager;
    }
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

    uint32_t GetActiveRegBits(uint32_t row) const { return data.GetNext(row * rowBitsLen).GetBits(headerInfo[BITS_LEN]); }

private:
    void Init()
    {
        data = ResolveHeader(headerInfo, HEADER_COL_NUM);
        rowBitsLen = headerInfo[BITS_LEN];
        nextTable = data.GetNext(rowBitsLen * headerInfo[RECORD_NUM]);
    }
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
    SlotTable(uint8_t* tableAddrStart, uint32_t tableBitStart, uint32_t format) : TableAPI(tableAddrStart, tableBitStart),
        slotFormat(format) { Init(); }
    SlotTable(const BitsManager& bits, uint32_t format) : TableAPI(bits), slotFormat(format) { Init(); }
    SlotTable(BitsManager&& bits, uint32_t format) : TableAPI(bits), slotFormat(format) { Init(); }
    ~SlotTable() = default;

    int32_t GetBaseOffset(uint32_t row) const
    {
        uint32_t unsignedValue = data.GetNext(row * rowBitsLen).GetBits(headerInfo[BASE_OFF_BITS_LEN]);
        switch (headerInfo[BASE_OFF_BITS_LEN]) {
            case SIGNED8:
                return static_cast<int32_t>(static_cast<int8_t>(unsignedValue));
            case SIGNED16:
                return static_cast<int32_t>(static_cast<int16_t>(unsignedValue));
            case SIGNED32:
                return static_cast<int32_t>(static_cast<int32_t>(unsignedValue));
            default:
                std::cout << "wrong length of base offset bits length";
                return static_cast<int32_t>(unsignedValue);
        }
    }
    std::vector<SlotBits> GetSlotBitMap(uint32_t row) const
    {
        if (slotFormat != STACKMAP_BITMAP) {
            return GetWAHSlotBitMap(row);
        }
        // regular bits length : 32 bits
        constexpr uint32_t regularSlotBitsLen = 32;
        constexpr uint32_t regularShiftBits = 5;
        constexpr uint32_t bitMask = (1 << regularShiftBits) - 1;
        uint32_t bitMapLen = headerInfo[BIT_MAP_BITS_LEN];
        uint32_t size = bitMapLen >> regularShiftBits;
        uint32_t resident = bitMapLen & bitMask;
        if (resident != 0) {
            size++;
        }
        std::vector<SlotBits> buffer(size);
        BitsManager bitMapBits = data.GetNext(row * rowBitsLen + headerInfo[BASE_OFF_BITS_LEN]);
        for (uint32_t i = 0; i < size - 1; ++i, bitMapBits = bitMapBits.GetNext(regularSlotBitsLen)) {
            buffer[i] = bitMapBits.GetBits(regularSlotBitsLen);
        }
        if (resident == 0) {
            buffer[size - 1] = bitMapBits.GetBits(regularSlotBitsLen);
        } else {
            buffer[size - 1] = bitMapBits.GetBits(resident);
        }
        size_t validSize = size;
        for (uint32_t i = size - 1; i != 0; i--) {
            if (buffer[i] != 0) {
                break;
            }
            validSize--;
        }
        buffer.resize(validSize);
        return buffer;
    }
    uint32_t slotFormat;
private:
    void Init()
    {
        data = ResolveHeader(headerInfo, HEADER_COL_NUM);
        rowBitsLen = headerInfo[BASE_OFF_BITS_LEN] + headerInfo[BIT_MAP_BITS_LEN];
        nextTable = data.GetNext(headerInfo[RECORD_NUM] * rowBitsLen);
    }
    // WAHSlotBit is decompressed into a vector which value has 3 types:
    // | bit31 | bit30 | bit29...bit0 |
    // |   0   |   1   |    varInt    |  ====> (varInt * 31) bits of 1
    // |   0   |   0   |    varInt    |  ====> (varInt * 31) bits of 0
    // |   1   |       RawData        |  ====> 31 valid bits data.
    std::vector<SlotBits> GetWAHSlotBitMap(uint32_t row) const
    {
        uint32_t remainLen = headerInfo[BIT_MAP_BITS_LEN];
        BitsManager bitMapBits = data.GetNext(row * rowBitsLen + headerInfo[BASE_OFF_BITS_LEN]);
        std::vector<SlotBits> result;
        uint32_t isPureVal;
        uint32_t isAllRef;
        VarValue cnts;
        constexpr uint32_t PureValWidth = 31;
        constexpr uint32_t PureValMask = 1 << PureValWidth;
        constexpr uint32_t CompressTagBitPos = 30;
        while (remainLen != 0) {
            isPureVal = bitMapBits.GetBits(1);
            bitMapBits = bitMapBits.GetNext(1);
            remainLen--;
            if (isPureVal) {
                uint32_t pureValBits = std::min(remainLen, PureValWidth);
                result.push_back(bitMapBits.GetBits(pureValBits) | PureValMask);
                bitMapBits = bitMapBits.GetNext(pureValBits);
                remainLen -= pureValBits;
            } else {
                // bits are paddings when remain bits are less than shortest varInt bits + 1
                if (remainLen < VarInt::TAG_LEN + 1) {
                    break;
                }
                isAllRef = bitMapBits.GetBits(1);
                bitMapBits = bitMapBits.GetNext(1);
                remainLen--;

                VarInt varInt(bitMapBits);
                VarPair varIntPair = varInt.GetValue();
                cnts = varIntPair.first;
                bitMapBits = bitMapBits.GetNext(varIntPair.second);
                remainLen -= varIntPair.second;
                // Cnts == 0 means we are in paddings
                if (cnts == 0) {
                    break;
                }
                result.push_back(cnts |= (isAllRef << CompressTagBitPos));
            }
        }
        return result;
    }
    enum HeaderColTag {
        RECORD_NUM = 0,
        BASE_OFF_BITS_LEN,
        BIT_MAP_BITS_LEN,
        HEADER_COL_NUM,
    };

    // because the base offset is a signed integar, so the bits length must be 8 or 16 or 32.
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
    IdxSet GetIdxSet(Uptr startPC, Uptr framePC) const
    {
        uint32_t recordNum = headerInfo[RECORD_NUM];
        if (recordNum == 0) {
            return IdxSet();
        }
        // 32 bits is enough.
        uint32_t targetPCOff = static_cast<uint32_t>(framePC - startPC);
        uint32_t left = 0;
        uint32_t right = recordNum - 1;
        uint32_t leftPCOff = PCAt(left);
        if (leftPCOff == targetPCOff) {
            return IdxSet(RegIdxAt(left), SlotIdxAt(left), DerivePtrIdxAt(left));
        }
        uint32_t rightPCOff = PCAt(right);
        if (rightPCOff == targetPCOff) {
            return IdxSet(RegIdxAt(right), SlotIdxAt(right), DerivePtrIdxAt(right));
        }
        if (targetPCOff < leftPCOff || targetPCOff > rightPCOff) {
            return IdxSet();
        }
        while (left <= right) {
            // left + right won't exceed the limit of 32 bits.
            uint32_t mid = (left + right) >> 1;
            uint32_t midPCOff = PCAt(mid);
            if (midPCOff == targetPCOff) {
                return IdxSet(RegIdxAt(mid), SlotIdxAt(mid), DerivePtrIdxAt(mid));
            } else if (midPCOff < targetPCOff) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return IdxSet();
    }

    void CollectAllIdxSet(std::vector<IdxSet> &IdxSetVec) const
    {
        uint32_t recordNum = headerInfo[RECORD_NUM];
#if DUMP_DEBUG_INFO
        std::cout << "-------- wzl log callSiteNum recordNum: " << recordNum << std::endl;
#endif
        for (uint32_t i = 0; i < recordNum; ++i) {
            IdxSetVec.emplace_back(IdxSet(PCAt(i), RegIdxAt(i), SlotIdxAt(i), DerivePtrIdxAt(i)));
        }
    }

    uint32_t GetRegBitsLen() const { return headerInfo[REG_BITS_LEN]; }
    uint32_t GetSlotBitsLen() const { return headerInfo[SLOT_BITS_LEN]; }

private:
    void Init()
    {
        data = ResolveHeader(headerInfo, HEADER_COL_NUM - STACK_ITEM_NUM).GetNext(headerInfo[PADDING_BITS_LEN -
                                                                                                STACK_ITEM_NUM]);
        rowBitsLen = PC_OFF_BITS + headerInfo[REG_BITS_LEN] + headerInfo[SLOT_BITS_LEN] +
                     headerInfo[DERIVE_PTR_BITS_LEN];
        nextTable = data.GetNext(rowBitsLen * headerInfo[RECORD_NUM]);
    }
    uint32_t PCAt(uint32_t row) const
    {
        auto bitsManager = data.GetNext(row * rowBitsLen);
        return bitsManager.GetBits(PC_OFF_BITS);
    }
    uint32_t RegIdxAt(uint32_t row) const
    {
        auto bitsManager = data.GetNext(row * rowBitsLen + PC_OFF_BITS);
        return bitsManager.GetBits(headerInfo[REG_BITS_LEN]);
    }
    uint32_t SlotIdxAt(uint32_t row) const
    {
        auto bitsManager = data.GetNext(row * rowBitsLen + PC_OFF_BITS + headerInfo[REG_BITS_LEN]);
        return bitsManager.GetBits(headerInfo[SLOT_BITS_LEN]);
    }
    uint32_t DerivePtrIdxAt(uint32_t row) const
    {
        uint32_t skipBitsLen = row * rowBitsLen + PC_OFF_BITS + headerInfo[REG_BITS_LEN] + headerInfo[SLOT_BITS_LEN];
        auto bitsManager = data.GetNext(skipBitsLen);
        return bitsManager.GetBits(headerInfo[DERIVE_PTR_BITS_LEN]);
    }
    enum HeaderColTag : uint32_t {
        RECORD_NUM = 0,
        REG_BITS_LEN,
        SLOT_BITS_LEN,
        DERIVE_PTR_BITS_LEN,
        PADDING_BITS_LEN,
        HEADER_COL_NUM,
    };
    static constexpr uint32_t STACK_ITEM_NUM = 0;
    static constexpr uint32_t PC_OFF_BITS = 32;
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
    DerivedPtrPair GetDerivePair(uint32_t row) const
    {
        BitsManager rowBits = data.GetNext(row * rowBitsLen);
        return std::make_pair(rowBits.GetBits(regBitsLen), rowBits.GetNext(regBitsLen).GetBits(slotBitsLen));
    }

private:
    void Init()
    {
        data = ResolveHeader(headerInfo, HEADER_COL_NUM);
        rowBitsLen = regBitsLen + slotBitsLen;
    }
    enum HeaderTag {
        RECORD_NUM = 0,
        HEADER_COL_NUM,
    };
    uint32_t headerInfo[HEADER_COL_NUM];
    uint32_t regBitsLen = 0;
    uint32_t slotBitsLen = 0;
};
} // namespace kotlin::stackMap
