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
#include "StackMapTable.hpp"

namespace kotlin::stackMap {

uint64_t BitsManager::ConnectBytesToU64() const
{
    constexpr uint32_t len = sizeof(uint32_t) / sizeof(uint8_t) + 1;
    uint64_t value = 0;
    uint32_t shiftSteps = 0;
    for (uint32_t i = 0; i < len; ++i, shiftSteps += bitsNumPerByte) {
        value |= static_cast<uint64_t>(addr[i]) << shiftSteps;
    }
    return value;
}

BitsManager BitsManager::GetNext(uint32_t bitsLen) const
{
    uint32_t addrStep = bitsLen >> bitsShiftPerByte;
    constexpr uint32_t bitsMask = (1 << bitsShiftPerByte) - 1;
    uint32_t bitPosStep = bitsLen & bitsMask;
    uint8_t* nextAddr = addr + addrStep;
    uint32_t nextBitPos = bitPos + bitPosStep;
    if (nextBitPos >= bitsNumPerByte) {
        ++nextAddr;
        nextBitPos -= bitsNumPerByte;
    }
    return BitsManager(nextAddr, nextBitPos);
}

VarPair VarInt::GetValue() const
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

VarValue VarInt::GetPayload(BitsLen bitsLen) const
{
    BitsManager payload = bits.GetNext(TAG_LEN);
    return payload.GetBits(bitsLen);
}

BitsManager TableAPI::ResolveHeader(uint32_t headerInfo[], uint32_t size)
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

void PrologueVarInt::ResolvePrologue(const PrologueVisitor& visitor)
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

void RegTable::Init()
{
    data = ResolveHeader(headerInfo, HEADER_COL_NUM);
    rowBitsLen = headerInfo[BITS_LEN];
    nextTable = data.GetNext(rowBitsLen * headerInfo[RECORD_NUM]);
}

int32_t SlotTable::GetBaseOffset(uint32_t row) const
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

std::vector<SlotBits> SlotTable::GetSlotBitMap(uint32_t row) const
{
    if (slotFormat != STACKMAP_BITMAP) {
        return GetWAHSlotBitMap(row);
    }
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

void SlotTable::Init()
{
    data = ResolveHeader(headerInfo, HEADER_COL_NUM);
    rowBitsLen = headerInfo[BASE_OFF_BITS_LEN] + headerInfo[BIT_MAP_BITS_LEN];
    nextTable = data.GetNext(headerInfo[RECORD_NUM] * rowBitsLen);
}

std::vector<SlotBits> SlotTable::GetWAHSlotBitMap(uint32_t row) const
{
    uint32_t remainLen = headerInfo[BIT_MAP_BITS_LEN];
    BitsManager bitMapBits = data.GetNext(row * rowBitsLen + headerInfo[BASE_OFF_BITS_LEN]);
    std::vector<SlotBits> result;
    uint32_t isPureVal;
    uint32_t isAllRef;
    VarValue cnts;
    constexpr uint32_t pureValWidth = 31;
    constexpr uint32_t pureValMask = 1 << pureValWidth;
    constexpr uint32_t compressTagBitPos = 30;
    while (remainLen != 0) {
        isPureVal = bitMapBits.GetBits(1);
        bitMapBits = bitMapBits.GetNext(1);
        remainLen--;
        if (isPureVal) {
            uint32_t pureValBits = std::min(remainLen, pureValWidth);
            result.push_back(bitMapBits.GetBits(pureValBits) | pureValMask);
            bitMapBits = bitMapBits.GetNext(pureValBits);
            remainLen -= pureValBits;
        } else {
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
            if (cnts == 0) {
                break;
            }
            result.push_back(cnts |= (isAllRef << compressTagBitPos));
        }
    }
    return result;
}

void StackMapTable::Init()
{
    data = ResolveHeader(headerInfo, HEADER_COL_NUM - stackItemNum).GetNext(headerInfo[PADDING_BITS_LEN - stackItemNum]);
    rowBitsLen = pcOffBits + headerInfo[REG_BITS_LEN] + headerInfo[SLOT_BITS_LEN] + headerInfo[DERIVE_PTR_BITS_LEN];
    nextTable = data.GetNext(rowBitsLen * headerInfo[RECORD_NUM]);
}

uint32_t StackMapTable::PCAt(uint32_t row) const
{
    auto bitsManager = data.GetNext(row * rowBitsLen);
    return bitsManager.GetBits(pcOffBits);
}

uint32_t StackMapTable::RegIdxAt(uint32_t row) const
{
    auto bitsManager = data.GetNext(row * rowBitsLen + pcOffBits);
    return bitsManager.GetBits(headerInfo[REG_BITS_LEN]);
}

uint32_t StackMapTable::SlotIdxAt(uint32_t row) const
{
    auto bitsManager = data.GetNext(row * rowBitsLen + pcOffBits + headerInfo[REG_BITS_LEN]);
    return bitsManager.GetBits(headerInfo[SLOT_BITS_LEN]);
}

uint32_t StackMapTable::DerivePtrIdxAt(uint32_t row) const
{
    uint32_t skipBitsLen = row * rowBitsLen + pcOffBits + headerInfo[REG_BITS_LEN] + headerInfo[SLOT_BITS_LEN];
    auto bitsManager = data.GetNext(skipBitsLen);
    return bitsManager.GetBits(headerInfo[DERIVE_PTR_BITS_LEN]);
}

IdxSet StackMapTable::GetIdxSet(Uptr startPC, Uptr framePC) const
{
    uint32_t recordNum = headerInfo[RECORD_NUM];
    if (recordNum == 0) {
        return IdxSet();
    }
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

void StackMapTable::CollectAllIdxSet(std::vector<IdxSet> &idxSetVec) const
{
    uint32_t recordNum = headerInfo[RECORD_NUM];
#if DUMP_DEBUG_INFO
    std::cout << "-------- wzl log callSiteNum recordNum: " << recordNum << std::endl;
#endif
    for (uint32_t i = 0; i < recordNum; ++i) {
        idxSetVec.emplace_back(IdxSet(PCAt(i), RegIdxAt(i), SlotIdxAt(i), DerivePtrIdxAt(i)));
    }
}

void DerivedPtrTable::Init()
{
    data = ResolveHeader(headerInfo, HEADER_COL_NUM);
    rowBitsLen = regBitsLen + slotBitsLen;
}

DerivedPtrPair DerivedPtrTable::GetDerivePair(uint32_t row) const
{
    BitsManager rowBits = data.GetNext(row * rowBitsLen);
    return std::make_pair(rowBits.GetBits(regBitsLen), rowBits.GetNext(regBitsLen).GetBits(slotBitsLen));
}

} // namespace kotlin::stackMap

#endif // ENABLE_STACKMAP