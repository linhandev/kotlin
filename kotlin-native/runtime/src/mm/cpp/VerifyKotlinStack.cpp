#include "VerifyKotlinStack.hpp"

// To break circular dependency with ThreadData.hpp
#include "Memory.h"
#include "ThreadData.hpp"
#include "GlobalData.hpp"

namespace kotlin::mm {

void VerifyKotlinStack::OnPushFrameImpl(ThreadData& threadData, FrameKind kind) noexcept {
    auto& fpStack = threadData.getLastKotlinFrame().fpStack_;
    size_t size = fpStack.size();

    // Checked AFTER push.
    if (IsEntryFrame(kind)) {
        // Pushed Entry: Stack should now be [..., Entry] (Odd size).
        if (size % 2 == 0) {
            RuntimeLogInfo(
                    {kTagGC}, "VerifyKotlinStack: Parity Error on Push (Entry)! Expected ODD size after pushing Entry frame %d, got %zu",
                    (int)kind, size);
            threadData.printLastKotlinFrameLog();
            abort();
        }
    } else if (IsExitFrame(kind)) {
        // Pushed Exit: Stack should now be [..., Entry, Exit] (Even size).
        if (size % 2 != 0) {
            RuntimeLogInfo(
                    {kTagGC}, "VerifyKotlinStack: Parity Error on Push (Exit)! Expected EVEN size after pushing Exit frame %d, got %zu",
                    (int)kind, size);
            threadData.printLastKotlinFrameLog();
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
             RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Pushed Kotlin Frame %p (kind %d) Missing/Invalid Tag! Found 0x%llx",
                fp, (int)kind, (unsigned long long)*(fp - 2));
             threadData.printLastKotlinFrameLog();
             abort();
        }
    }
    // TODO: 退栈时未清理 fp + 2 的值，导致校验了已执行结束函数的栈帧
    // else {
    //     // Non-Kotlin frame should NOT have the tag
    //     if (IsKotlinFrameTag(fp)) {
    //          RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Pushed Non-Kotlin Frame %p (kind %d) Unexpectedly has Tag! Found 0x%llx",
    //             fp, (int)kind, (unsigned long long)*(fp - 2));
    //          threadData.printLastKotlinFrameLog();
    //          abort();
    //     }
    // }
}

void VerifyKotlinStack::OnPopFrameImpl(ThreadData& threadData, FrameKind kind) noexcept {
    auto& kindStack = threadData.getLastKotlinFrame().kindStack_;

    if (kindStack.empty()) {
        RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Pop on empty stack!");
        abort();
    }

    // Check: LIFO Match (Restore type must match Save type)
    FrameKind topKind = static_cast<FrameKind>(kindStack.back());
    if (topKind != kind) {
        RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: FrameKind Mismatch on Pop! Expected %d (Top), got %d (Arg)", (int)topKind, (int)kind);
        threadData.printLastKotlinFrameLog();
        abort();
    }
    // Parity check removed as per request (checked on push/scan).
}

void VerifyKotlinStack::ScanStackForTag(ThreadData& threadData) noexcept {
    // Only scan when balanced (size is even)
    auto& fpStack = threadData.getLastKotlinFrame().fpStack_;
    auto& kindStack = threadData.getLastKotlinFrame().kindStack_;
    size_t size = fpStack.size();
    if (size % 2 != 0 || size < 2) {
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
        fp = (uint64_t*)*fp;
    }

    int limit = 1000;
    while (fp != entryFp && fp != nullptr && limit-- > 0) {
        // Verify frame tag at *(fp - 2)
        if (!IsKotlinFrameTag(fp)) {
             RuntimeLogInfo(
                {kTagGC}, "VerifyKotlinStack: Missing/Invalid Tag on frame %p between Exit(%p) and Entry(%p). Found 0x%llx",
                fp, exitFp, entryFp, (unsigned long long)*(fp - 2));
             threadData.printLastKotlinFrameLog();
             TryUnwindAggresively(threadData);
             abort();
        }
        fp = (uint64_t*)*fp;
    }

    if (limit <= 0) {
        RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Stack walk limit exceeded or loop detected between %p and %p", exitFp, entryFp);
        abort();
    }

    // Check 2: Verify Entry Frame tag if it should be marked (i.e. is Kotlin)
    FrameKind entryKind = static_cast<FrameKind>(kindStack[size - 2]);
    if (IsKotlinFrame(entryKind)) {
        // Entry is Kotlin, verify its tag at *(entryFp - 2)
        if (!IsKotlinFrameTag(entryFp)) {
            RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: Entry Frame %p (kind %d) Missing Tag! Found 0x%llx",
                entryFp, (int)entryKind, (unsigned long long)*(entryFp - 2));
            threadData.printLastKotlinFrameLog();
            abort();
        }
    }
}

void VerifyKotlinStack::TryUnwindAggresively(ThreadData& threadData) noexcept{
    auto& fpStack = threadData.getLastKotlinFrame().fpStack_;
    if (fpStack.size() < 2) {
        return ;
    }
    uint64_t *firstFp = fpStack[0];
    uint64_t *lastFp = fpStack[fpStack.size() - 1];
    uint32_t *pc = threadData.getLastKotlinFrame().pcStack_[fpStack.size() - 1];
    int idx = 0;

    // Try unwind
    while(lastFp != firstFp && lastFp != nullptr) {
        if (IsKotlinFrameTag(lastFp)) {
            uint64_t offsetIndex = GetKotlinFrameOffsetIndex(lastFp);
            uint64_t pcOffset = GetKotlinFramePcOffset(lastFp, pc);

            RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: #%d Kotlin offset index 0x%llx, pc offset: 0x%llx, pc at %p",
                idx, (unsigned long long)(offsetIndex), (unsigned long long)(pcOffset), pc);
        } else {
            RuntimeLogInfo({kTagGC}, "VerifyKotlinStack: #%d is R/N frame. pc at %p", idx, pc);
        }

        pc = (uint32_t*)*(lastFp + 1);
        lastFp = (uint64_t *)*lastFp;
        ++idx;
    }
}

} // namespace kotlin::mm
