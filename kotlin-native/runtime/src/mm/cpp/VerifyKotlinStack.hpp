#ifndef RUNTIME_MM_VERIFY_KOTLIN_STACK_H
#define RUNTIME_MM_VERIFY_KOTLIN_STACK_H

#include "Memory.h"
#include "GlobalData.hpp"

// Conditional compilation for stack verification
#define ENABLE_VERIFY_STACK 1

namespace kotlin::mm {

class ThreadData;

class VerifyKotlinStack {
public:
    // Magic number to look for in the stack.
    // 0xBEEF is a placeholder; needs to match what the compiler emits.
    static constexpr uint64_t KOTLIN_STACK_TAG = 0xBEEF;
    static constexpr uint64_t PAYLOAD_MASK = (1ULL << 48) - 1;

    static constexpr bool IsKotlinFrameTag(uint64_t* fp) noexcept {
        uint64_t val = *(fp - 2);
        return (val >> 48) == KOTLIN_STACK_TAG;
    }

    static uint64_t GetKotlinFrameOffsetIndex(uint64_t* fp) noexcept {
        uint64_t val = *(fp - 2);
        return val & PAYLOAD_MASK;
    }

    static uint64_t GetKotlinFramePcOffset(uint64_t* fp, uint32_t *pc) noexcept {
        uint32_t *funcStartPC = (uint32_t *)*(fp - 1);
        return reinterpret_cast<uintptr_t>(pc) - reinterpret_cast<uintptr_t>(funcStartPC);
    }

    // Called on SaveStackFrame (Entry into Kotlin)
    static inline void OnPushFrame(ThreadData& threadData, FrameKind kind) noexcept {
        if (__builtin_expect(!mm::GlobalData::Instance().gcScheduler().config().verifyKotlinStack.load(), 1)) {
            return;
        }
        OnPushFrameImpl(threadData, kind);
    }

    // Called on RestoreStackFrame (Exit from Kotlin)
    static inline void OnPopFrame(ThreadData& threadData, FrameKind kind) noexcept {
        if (__builtin_expect(!mm::GlobalData::Instance().gcScheduler().config().verifyKotlinStack.load(), 1)) {
            return;
        }
        OnPopFrameImpl(threadData, kind);
    }

    // Unwind and print stack offset and pc offset, it may crash
    // Only use it before abort.
    static void TryUnwindAggresively(ThreadData& threadData) noexcept;

private:
    static void OnPushFrameImpl(ThreadData& threadData, FrameKind kind) noexcept;
    static void OnPopFrameImpl(ThreadData& threadData, FrameKind kind) noexcept;
    // Scans the stack for the Magic Number when parity is even (balanced).
    static void ScanStackForTag(ThreadData& threadData) noexcept;
};

} // namespace kotlin::mm

#endif // RUNTIME_MM_VERIFY_KOTLIN_STACK_H
