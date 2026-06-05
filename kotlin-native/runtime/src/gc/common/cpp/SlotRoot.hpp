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
// Entire file is stackmap-only (kotlin::stackMap::SlotRoot).
// OFF mode: empty header.

#include <cstdint>
#include <vector>
#include "Macros.hpp"

namespace kotlin::stackMap {
class SlotRoot {
public:
    SlotRoot() : slotBias(0) {}
    SlotRoot(SlotBias bias, BitsMapSize size, const SlotBits bits[], uint32_t format) : slotBias(bias),
        slotBits(bits, bits + size), slotFormat(format) {}
    SlotRoot(SlotBias bias, const std::vector<SlotBits>& slotVec, uint32_t format) : slotBias(bias),
        slotBits(slotVec), slotFormat(format) {}
    SlotRoot(SlotBias bias, std::vector<SlotBits>&& slotVec, uint32_t format) : slotBias(bias),
        slotBits(slotVec), slotFormat(format) {}
    SlotRoot(const SlotRoot& other) : slotBias(other.slotBias), slotBits(other.slotBits),
        slotFormat(other.slotFormat) {}
    SlotRoot(SlotRoot&& other) : slotBias(other.slotBias), slotBits(std::move(other.slotBits)),
        slotFormat(other.slotFormat)
    {
        other.slotBias = 0;
        std::vector<SlotBits>().swap(other.slotBits);
    }

    SlotRoot& operator=(const SlotRoot& other)
    {
        if (this == &other) {
            return *this;
        }
        slotBias = other.slotBias;
        slotBits = other.slotBits;
        slotFormat = other.slotFormat;
        return *this;
    }
    SlotRoot& operator=(SlotRoot&& other)
    {
        if (this == &other) {
            return *this;
        }
        slotBias = other.slotBias;
        slotBits = std::move(other.slotBits);
        slotFormat = other.slotFormat;
        other.slotBias = 0;
        return *this;
    }

    void CollectSlotOffsets(std::vector<int32_t> &slotOffsets)
    {
        if (slotFormat != STACKMAP_BITMAP) {
            CollectWAHSlotOffsets(slotOffsets);
            return;
        }
        for (size_t i = 0; i < slotBits.size(); ++i) {
            SlotBits bit = slotBits[i];
            for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
                if ((bit & lowestBit) == 0) {
                    continue;
                }
                SlotBias bias = static_cast<int32_t>(i * bitSize + j) * bytesPerSlot + slotBias * biasCoef;
                slotOffsets.push_back(bias);
            }
        }
    }

    // Visitor-style overload used by the precise CRT root visitor. Mirrors
    // CollectSlotOffsets but invokes `visitor(bias)` instead of push_back.
    template <typename Visitor>
    void VisitSlotOffsets(Visitor visitor)
    {
        if (slotFormat != STACKMAP_BITMAP) {
            VisitWAHSlotOffsets(visitor);
            return;
        }
        for (size_t i = 0; i < slotBits.size(); ++i) {
            SlotBits bit = slotBits[i];
            for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
                if ((bit & lowestBit) == 0) {
                    continue;
                }
                SlotBias bias = static_cast<int32_t>(i * bitSize + j) * bytesPerSlot + slotBias * biasCoef;
                visitor(bias);
            }
        }
    }

    ~SlotRoot() { std::vector<SlotBits>().swap(slotBits); }

private:
    template <typename Visitor>
    void VisitWAHSlotOffsets(Visitor visitor) const
    {
        constexpr uint32_t pureValWidth = 31;
        constexpr uint32_t pureValBit = 1 << pureValWidth;
        constexpr uint32_t pureValMask = pureValBit - 1;
        constexpr uint32_t compressTagBit = 1 << 30;
        constexpr uint32_t compressCntMask = compressTagBit - 1;
        SlotBias baseBias = slotBias * biasCoef;

        auto visitOneSlot = [this, &baseBias, &visitor](int32_t idx) {
            SlotBias bias = baseBias + static_cast<int32_t>(idx) * bytesPerSlot;
            visitor(bias);
        };

        auto processPureValBits = [&visitOneSlot, &baseBias](SlotBits bit) {
            bit &= pureValMask;
            for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
                if ((bit & lowestBit) == 0) {
                    continue;
                }
                visitOneSlot(j);
            }
            baseBias += static_cast<int32_t>(pureValWidth) * bytesPerSlot;
        };

        auto processCompressedBits = [&visitOneSlot, &baseBias](SlotBits bit) {
            bool isAllRef = (bit & compressTagBit);
            uint32_t bitNums = (bit & compressCntMask) * pureValWidth;
            if (isAllRef) {
                for (uint32_t j = 0; j < bitNums; ++j) {
                    visitOneSlot(j);
                }
            }
            baseBias += static_cast<int32_t>(bitNums) * bytesPerSlot;
        };

        for (SlotBits bit : slotBits) {
            if (bit & pureValBit) {
                processPureValBits(bit);
            } else {
                processCompressedBits(bit);
            }
        }
    }

    void CollectWAHSlotOffsets(std::vector<int32_t> &slotOffsets) const
    {
        constexpr uint32_t pureValWidth = 31;
        constexpr uint32_t pureValBit = 1 << pureValWidth;
        constexpr uint32_t pureValMask = pureValBit - 1;
        constexpr uint32_t compressTagBit = 1 << 30;
        constexpr uint32_t compressCntMask = compressTagBit - 1;
        SlotBias baseBias = slotBias * biasCoef;

        auto visitOneSlot = [&](int32_t idx) {
            SlotBias bias = baseBias + static_cast<int32_t>(idx) * bytesPerSlot;
            slotOffsets.push_back(bias);
        };

        auto processPureValBits = [&visitOneSlot, &baseBias](SlotBits bit) {
            bit &= pureValMask;
            for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
                if ((bit & lowestBit) == 0) {
                    continue;
                }
                visitOneSlot(j);
            }
            baseBias += static_cast<int32_t>(pureValWidth) * bytesPerSlot;
        };

        auto processCompressedBits = [&visitOneSlot, &baseBias](SlotBits bit) {
            bool isAllRef = (bit & compressTagBit);
            uint32_t bitNums = (bit & compressCntMask) * pureValWidth;
            if (isAllRef) {
                for (uint32_t j = 0; j < bitNums; ++j) {
                    visitOneSlot(j);
                }
            }
            baseBias += static_cast<int32_t>(bitNums) * bytesPerSlot;
        };

        for (SlotBits bit : slotBits) {
            if (bit & pureValBit) {
                processPureValBits(bit);
            } else {
                processCompressedBits(bit);
            }
        }
    }
    SlotBias slotBias;
    std::vector<SlotBits> slotBits;
    uint32_t slotFormat;
    constexpr static uint32_t bitSize = 32;
    constexpr static SlotBits lowestBit = 0x1;
    constexpr static int32_t bytesPerSlot = -8;
    constexpr static int32_t biasCoef = 1;
};
} // namespace kotlin::stackMap

#endif // ENABLE_STACKMAP