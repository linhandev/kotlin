/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

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

    // void VisitGCRoots(const RootVisitor& visitor, const SlotDebugVisitor& debugFunc, uintptr_t base,
    //                   std::list<Uptr>* rootsList = nullptr) const
    // {
    //     if (slotFormat != PURE_COMPRESSED_STACKMAP) {
    //         VisitWAHGCRoots(visitor, debugFunc, base, rootsList);
    //         return;
    //     }
    //     for (size_t i = 0; i < slotBits.size(); ++i) {
    //         SlotBits bit = slotBits[i];
    //         for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
    //             if ((bit & LOWEST_BIT) == 0) {
    //                 continue;
    //             }
    //             SlotBias bias = static_cast<int32_t>(i * BIT_SIZE + j) * BYTES_PER_SLOT + slotBias * BIAS_COEF;
    //             SlotAddress slot = reinterpret_cast<SlotAddress>(static_cast<intptr_t>(base) + bias);
    //             if (debugFunc != nullptr) {
    //                 debugFunc(bias, slot->object);
    //             }
    //             if (rootsList != nullptr) {
    //                 rootsList->push_back(reinterpret_cast<Uptr>(slot->object));
    //             }
    //             visitor(*slot);
    //         }
    //     }
    // }

    void CollectSlotOffsets(std::vector<int32_t> &slotOffsets)
    {
        if (slotFormat != STACKMAP_BITMAP) {
            CollectWAHSlotOffsets(slotOffsets);
            return;
        }
        for (size_t i = 0; i < slotBits.size(); ++i) {
            SlotBits bit = slotBits[i];
            for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
                if ((bit & LOWEST_BIT) == 0) {
                    continue;
                }
                SlotBias bias = static_cast<int32_t>(i * BIT_SIZE + j) * BYTES_PER_SLOT + slotBias * BIAS_COEF;
                slotOffsets.push_back(bias);
            }
        }
    }

    ~SlotRoot() { std::vector<SlotBits>().swap(slotBits); }

private:
    // void VisitWAHGCRoots(const RootVisitor& visitor, const SlotDebugVisitor& debugFunc, uintptr_t base,
    //     std::list<Uptr>* rootsList = nullptr) const
    // {
    //     constexpr uint32_t PureValWidth = 31;
    //     constexpr uint32_t PureValBit = 1 << PureValWidth;
    //     constexpr uint32_t PureValMask = PureValBit - 1;
    //     constexpr uint32_t CompressTagBit = 1 << 30;
    //     constexpr uint32_t CompressCntMask = CompressTagBit - 1;
    //     SlotBias baseBias = slotBias * BIAS_COEF;

    //     auto VisitOneSlot = [&](int32_t Idx) {
    //         SlotBias bias = baseBias + static_cast<int32_t>(Idx) * BYTES_PER_SLOT;
    //         SlotAddress slot = reinterpret_cast<SlotAddress>(static_cast<intptr_t>(base) + bias);
    //         if (debugFunc != nullptr) {
    //             debugFunc(bias, slot->object);
    //         }
    //         if (rootsList != nullptr) {
    //             rootsList->push_back(reinterpret_cast<Uptr>(slot->object));
    //         }
    //         visitor(*slot);
    //     };

    //     auto ProcessOneSlotBits = [&](SlotBits bit) {
    //         if (bit & PureValBit) {
    //             bit &= PureValMask;
    //             for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
    //                 if ((bit & LOWEST_BIT) == 0) {
    //                     continue;
    //                 }
    //                 VisitOneSlot(j);
    //             }
    //             baseBias += static_cast<int32_t>(PureValWidth) * BYTES_PER_SLOT;
    //         } else {
    //             bool isAllRef = (bit & CompressTagBit);
    //             uint32_t bitNums = (bit & CompressCntMask) * PureValWidth;
    //             if (isAllRef) {
    //                 for (uint32_t j = 0; j < bitNums; ++j) {
    //                     VisitOneSlot(j);
    //                 }
    //             }
    //             baseBias += static_cast<int32_t>(bitNums) * BYTES_PER_SLOT;
    //         }
    //     };

    //     for (SlotBits bit : slotBits) {
    //         ProcessOneSlotBits(bit);
    //     }
    // }
    void CollectWAHSlotOffsets(std::vector<int32_t> &slotOffsets) const
    {
        constexpr uint32_t PureValWidth = 31;
        constexpr uint32_t PureValBit = 1 << PureValWidth;
        constexpr uint32_t PureValMask = PureValBit - 1;
        constexpr uint32_t CompressTagBit = 1 << 30;
        constexpr uint32_t CompressCntMask = CompressTagBit - 1;
        SlotBias baseBias = slotBias * BIAS_COEF;

        auto VisitOneSlot = [&](int32_t Idx) {
            SlotBias bias = baseBias + static_cast<int32_t>(Idx) * BYTES_PER_SLOT;
            slotOffsets.push_back(bias);
        };

        auto ProcessOneSlotBits = [&](SlotBits bit) {
            if (bit & PureValBit) {
                bit &= PureValMask;
                for (uint32_t j = 0; bit != 0; ++j, bit >>= 1) {
                    if ((bit & LOWEST_BIT) == 0) {
                        continue;
                    }
                    VisitOneSlot(j);
                }
                baseBias += static_cast<int32_t>(PureValWidth) * BYTES_PER_SLOT;
            } else {
                bool isAllRef = (bit & CompressTagBit);
                uint32_t bitNums = (bit & CompressCntMask) * PureValWidth;
                if (isAllRef) {
                    for (uint32_t j = 0; j < bitNums; ++j) {
                        VisitOneSlot(j);
                    }
                }
                baseBias += static_cast<int32_t>(bitNums) * BYTES_PER_SLOT;
            }
        };

        for (SlotBits bit : slotBits) {
            ProcessOneSlotBits(bit);
        }
    }
    SlotBias slotBias;
    std::vector<SlotBits> slotBits;
    uint32_t slotFormat;
    constexpr static uint32_t BIT_SIZE = 32;
    constexpr static SlotBits LOWEST_BIT = 0x1;
    constexpr static int32_t BYTES_PER_SLOT = -8;
    constexpr static int32_t BIAS_COEF = 1;
};
} // namespace kotlin::stackMap