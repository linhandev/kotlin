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

#include "VerifyKotlinStack.hpp"

// To break circular dependency with ThreadData.hpp
#include "Memory.h"
#include "ThreadData.hpp"
#include "GlobalData.hpp"

namespace kotlin::mm {

constexpr size_t FRAME_PAIR_SIZE = 2;

void VerifyKotlinStack::OnPushFrameImpl(ThreadData& threadData, FrameKind kind) noexcept
{
    auto& fpStack = threadData.GetLastKotlinFrame().fpStack_;
    size_t size = fpStack.size();

    // Checked AFTER push.
    if (IsEntryFrame(kind)) {
        // Pushed Entry: Stack should now be [..., Entry] (Odd size).
        if (size % FRAME_PAIR_SIZE == 0) {
            RuntimeLogInfo(
                {kTagGC},
                "VerifyKotlinStack: Parity Error on Push (Entry)!"
                " Expected ODD size after pushing Entry frame %d, got %zu",
                (int)kind, size);
            threadData.PrintLastKotlinFrameLog();
            abort();
        }
    } else if (IsExitFrame(kind)) {
        // Pushed Exit: Stack should now be [..., Entry, Exit] (Even size).
        if (size % FRAME_PAIR_SIZE != 0) {
            RuntimeLogInfo(
                {kTagGC},
                "VerifyKotlinStack: Parity Error on Push (Exit)!"
                " Expected EVEN size after pushing Exit frame %d, got %zu",
                (int)kind, size);
            threadData.PrintLastKotlinFrameLog();
            abort();
        }

        // Immediate stack scan on Exit (when pair is closed)
        ScanStackForTag(threadData);
    } else {
        // Should not happen if kind is valid
        RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Unknown FrameKind %d on Push", (int)kind);
        abort();
    }

    // Check: Verify Tag consistency based on FrameKind
    uint64_t* fp = fpStack.back();
    if (IsKotlinFrame(kind)) {
        if (!IsKotlinFrameTag(fp)) {
            RuntimeLogInfo({kTagGC},
                "VerifyKotlinStack: Pushed Kotlin Frame %p"
                " (kind %d) Missing/Invalid Tag! Found 0x%llx",
                fp, (int)kind, (unsigned long long)*(fp - FRAME_TAG_SLOT));
            threadData.PrintLastKotlinFrameLog();
            abort();
        }
    }
}

void VerifyKotlinStack::OnPopFrameImpl(ThreadData& threadData, FrameKind kind) noexcept
{
    auto& kindStack = threadData.GetLastKotlinFrame().kindStack_;

    if (kindStack.empty()) {
        RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Pop on empty stack!");
        abort();
    }

    // Check: LIFO Match (Restore type must match Save type)
    FrameKind topKind = static_cast<FrameKind>(kindStack.back());
    if (topKind != kind) {
        RuntimeLogInfo({kTagGC},
            "VerifyKotlinStack: FrameKind Mismatch on Pop!"
            " Expected %d (Top), got %d (Arg)",
            (int)topKind, (int)kind);
        threadData.PrintLastKotlinFrameLog();
        abort();
    }
    // Parity check removed as per request (checked on push/scan).
}

void VerifyKotlinStack::ScanStackForTag(ThreadData& threadData) noexcept
{
    // Only scan when balanced (size is even)
    auto& fpStack = threadData.GetLastKotlinFrame().fpStack_;
    auto& kindStack = threadData.GetLastKotlinFrame().kindStack_;
    size_t size = fpStack.size();
    if (size % FRAME_PAIR_SIZE != 0 || size < FRAME_PAIR_SIZE) {
        abort();
    }

    // Check the last closed pair (Top of stack)
    // fpStack has: [EntryFP, ExitFP, EntryFP, ExitFP ...]
    // Last pair is at [size-2] (Entry) and [size-1] (Exit)

    uint64_t* exitFp = fpStack[size - 1];
    uint64_t* entryFp = fpStack[size - 2];

    // Walk frames between Exit (inclusive) and Entry (exclusive)
    uint64_t* fp = exitFp;
    // Skip Cpp frame
    if (!IsKotlinFrame(static_cast<FrameKind>(kindStack.back()))) {
        fp = reinterpret_cast<uint64_t*>(*fp);
    }

    int limit = 1000;
    while (fp != entryFp && fp != nullptr && limit-- > 0) {
        // Verify frame tag at *(fp - 2)
        if (!IsKotlinFrameTag(fp)) {
            RuntimeLogInfo({kTagGC},
                "VerifyKotlinStack: Missing/Invalid Tag on frame %p"
                " between Exit(%p) and Entry(%p). Found 0x%llx",
                fp, exitFp, entryFp, (unsigned long long)*(fp - FRAME_TAG_SLOT));
            threadData.PrintLastKotlinFrameLog();
            TryUnwindAggresively(threadData);
            abort();
        }
        fp = reinterpret_cast<uint64_t*>(*fp);
    }

    if (limit <= 0) {
        RuntimeLogInfo({kTagGC},
            "VerifyKotlinStack: Stack walk limit exceeded"
            " or loop detected between %p and %p",
            exitFp, entryFp);
        abort();
    }

    // Check 2: Verify Entry Frame tag if it should be marked (i.e. is Kotlin)
    FrameKind entryKind = static_cast<FrameKind>(kindStack[size - 2]);
    if (IsKotlinFrame(entryKind)) {
        // Entry is Kotlin, verify its tag at *(entryFp - 2)
        if (!IsKotlinFrameTag(entryFp)) {
            RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Entry Frame %p (kind %d) Missing Tag! Found 0x%llx",
                entryFp, (int)entryKind, (unsigned long long)*(entryFp - FRAME_TAG_SLOT));
            threadData.PrintLastKotlinFrameLog();
            abort();
        }
    }
}

void VerifyKotlinStack::TryUnwindAggresively(ThreadData& threadData) noexcept
{
    auto& fpStack = threadData.GetLastKotlinFrame().fpStack_;
    if (fpStack.size() < FRAME_PAIR_SIZE) {
        return ;
    }
    uint64_t *firstFp = fpStack[0];
    uint64_t *lastFp = fpStack[fpStack.size() - 1];
    uint32_t *pc = threadData.GetLastKotlinFrame().pcStack_[fpStack.size() - 1];
    int idx = 0;

    // Try unwind
    while (lastFp != firstFp && lastFp != nullptr) {
        if (IsKotlinFrameTag(lastFp)) {
            uint64_t offsetIndex = GetKotlinFrameOffsetIndex(lastFp);
            uint64_t pcOffset = GetKotlinFramePcOffset(lastFp, pc);

            RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: #%d Kotlin offset index 0x%llx, pc offset: 0x%llx, pc at %p",
                idx, (unsigned long long)(offsetIndex), (unsigned long long)(pcOffset), pc);
        } else {
            RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: #%d is R/N frame. pc at %p", idx, pc);
        }

        pc = reinterpret_cast<uint32_t*>(*(lastFp + 1));
        lastFp = reinterpret_cast<uint64_t*>(*lastFp);
        ++idx;
    }
}

} // namespace kotlin::mm
