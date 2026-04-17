/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#ifndef RUNTIME_MM_THREAD_STATE_H
#define RUNTIME_MM_THREAD_STATE_H

#include <Common.h>
#include <Utils.hpp>

#include "ThreadData.hpp"
#include "ThreadSuspension.hpp"
#include "StackTrace.hpp"

namespace kotlin {

namespace internal {

ALWAYS_INLINE inline bool isStateSwitchAllowed(ThreadState oldState, ThreadState newState, bool reentrant) noexcept  {
    return oldState != newState || reentrant;
}

std::string statesToString(std::initializer_list<ThreadState> states) noexcept;

} // namespace internal

const char* ThreadStateName(ThreadState state) noexcept;

// Switches the state of the given thread to `newState` and returns the previous thread state.
ALWAYS_INLINE inline ThreadState SwitchThreadState(
    mm::ThreadData* threadData,
    ThreadState newState,
    bool reentrant = false) noexcept
{
    RuntimeAssert(threadData != nullptr, "threadData must not be nullptr");

    auto oldState = threadData->setState(newState);
    // TODO(perf): Mesaure the impact of this assert in debug and opt modes.
    RuntimeAssert(internal::isStateSwitchAllowed(oldState, newState, reentrant),
                  "Illegal thread state switch. Old state: %s. New state: %s.",
                  ThreadStateName(oldState), ThreadStateName(newState));
    return oldState;
}

ALWAYS_INLINE inline bool IsSafePointFunctionProloguePc(const uint32_t* pc) noexcept
{
    constexpr uintptr_t kMaxPrologueSize = 256;
    auto address = reinterpret_cast<uintptr_t>(pc);
    auto start = reinterpret_cast<uintptr_t>(&Kotlin_mm_safePointFunctionPrologue);
    return address >= start && address < start + kMaxPrologueSize;
}

ALWAYS_INLINE inline void SaveThreadLastKotlinFrame(mm::ThreadData* threadData, FrameKind kind) noexcept
{
    RuntimeAssert(threadData != nullptr, "threadData must not be nullptr");
    uint64_t* fp = (uint64_t*)__builtin_frame_address(0);
    uint32_t* pc = (uint32_t*)*(fp + 1);
    fp = (uint64_t*)*fp;
    if (kind == FrameKind::K_SAFE_POINT && IsSafePointFunctionProloguePc(pc)) {
        pc = (uint32_t*)*(fp + 1);
        fp = (uint64_t*)*fp;
    }
    threadData->pushLastKotlinFrame(pc, fp, kind);
}

ALWAYS_INLINE inline void RestoreThreadLastKotlinFrame(mm::ThreadData* threadData, FrameKind kind) noexcept
{
    RuntimeAssert(threadData != nullptr, "threadData must not be nullptr");
    if (threadData == reinterpret_cast<mm::ThreadData*>(0x8)) return;
    threadData->popLastKotlinFrame(kind);
}

// Asserts that the given thread is in the given state.
ALWAYS_INLINE inline void AssertThreadState(mm::ThreadData* threadData, ThreadState expected) noexcept {
    // The read of the thread state is atomic, thus the compiler cannot eliminate it
    // even if its result is unused due to disabled runtime asserts.
    // So we explicitly avoid the read if asserts are disabled.
    if (compiler::runtimeAssertsMode() != compiler::RuntimeAssertsMode::kIgnore) {
        RuntimeAssert(threadData != nullptr, "threadData must not be nullptr");
        auto actual = threadData->state();
        RuntimeAssert(
                actual == expected, "Unexpected thread state. Expected: %s. Actual: %s.", ThreadStateName(expected),
                ThreadStateName(actual));
    }
}

ALWAYS_INLINE inline void AssertThreadState(mm::ThreadData* threadData, std::initializer_list<ThreadState> expected) noexcept {
    // The read of the thread state is atomic, thus the compiler cannot eliminate it
    // even if its result is unused due to disabled runtime asserts.
    // So we explicitly avoid the read if asserts are disabled.
    if (compiler::runtimeAssertsMode() != compiler::RuntimeAssertsMode::kIgnore) {
        RuntimeAssert(threadData != nullptr, "threadData must not be nullptr");
        auto actual = threadData->state();
        RuntimeAssert(
                std::any_of(expected.begin(), expected.end(), [actual](ThreadState expected) { return expected == actual; }),
                "Unexpected thread state. Expected one of: %s. Actual: %s", internal::statesToString(expected).c_str(),
                ThreadStateName(actual));
    }
}

} // namespace kotlin

#endif // RUNTIME_MM_THREAD_STATE_H
