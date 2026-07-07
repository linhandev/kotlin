/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "ThreadData.hpp"
#include "ThreadSuspension.hpp"

#include <condition_variable>

#include "CallsChecker.hpp"
#include "Logging.hpp"
#include "Porting.h"
#include "SafePoint.hpp"

#include "StackTrace.hpp"
#include "CRTFastpathUtils.hpp"
#include <iostream>
#include <cstring>
#include "MemoryManagerSwitch.hpp"
#ifdef ENABLE_CRT
#include "common_interfaces/thread/thread_holder.h"
#include "common_interfaces/thread/thread_holder-inl.h"
#endif

using namespace kotlin;

#define DUMP_DEBUG_INFO 0

namespace {

[[clang::no_destroy]] thread_local std::optional<mm::SafePointActivator> gSafePointActivator = std::nullopt;
[[clang::no_destroy]] std::mutex gSuspensionRequestMutex;
[[clang::no_destroy]] std::condition_variable gSuspensionCondVar;

} // namespace

std::atomic<mm::internal::SuspensionReason> mm::internal::gSuspensionRequestReason = nullptr;

PERFORMANCE_INLINE mm::ThreadSuspensionData::MutatorPauseHandle::MutatorPauseHandle(const char* reason, mm::ThreadData& threadData) noexcept
    : reason_(reason), threadData_(threadData), pauseStartTimeMicros_(konan::getTimeMicros())
{
    assertNotCRT();
    auto prevState = threadData_.suspensionData().setStateNoSafePoint(ThreadState::kNative);
    // no special reason, fill free to implement pause from native if needed
    RuntimeAssert(prevState == ThreadState::kRunnable, "Expected runnable state");
    RuntimeLogDebug({logging::Tag::kPause}, "Suspending mutation (%s)", reason_);
}

PERFORMANCE_INLINE mm::ThreadSuspensionData::MutatorPauseHandle::~MutatorPauseHandle() noexcept {
    if (!resumed) resume();
}

PERFORMANCE_INLINE void mm::ThreadSuspensionData::MutatorPauseHandle::resume() noexcept {
    RuntimeAssert(!resumed, "Must not be resumed yet");
    auto prevState = threadData_.suspensionData().setStateNoSafePoint(ThreadState::kRunnable);
    RuntimeAssert(prevState == ThreadState::kNative, "Expected native state");
    auto pauseTimeMicros = konan::getTimeMicros() - pauseStartTimeMicros_;
    RuntimeLogInfo({logging::Tag::kPause}, "Resuming mutation after %" PRIu64 " microseconds of suspension (%s)", pauseTimeMicros, reason_);
    resumed = true;
}

// TODO: Rename, see below
kotlin::ThreadState kotlin::mm::ThreadSuspensionData::setState(kotlin::ThreadState newState) noexcept {
    // CheckMode can't be Fast as it is reachable from places where x28 isn't set,
    // e.g. from `Kotlin_deinitRuntimeCallback`.
    // Moreover, it's used for kNative -> kRunnable state switch and x28 value is not guaranteed in kNative.
    return checkUseCRT<CheckMode::Slow>([&] {
        if (newState == ThreadState::kRunnable) {
            // Ported from mpcore/crt_fp_unwind 7e581cd. Capture this frame
            // before TransferToRunning, which can block on STW: the GC walker
            // needs a valid lastFrame to unwind through this transition path.
            threadData_.RuntimeSetLastFrame();
            auto* th = threadData_.GetThreadHolder();
            th->TransferToRunning();
            common::RestoreThreadLocalDataReg(&threadData_);
        } else {
            threadData_.GetThreadHolder()->TransferToNative();
        }
        return state_.exchange(newState, std::memory_order_acq_rel);
    }, [&] {
        ThreadState oldState = state_.exchange(newState);
        if (oldState == ThreadState::kNative && newState == ThreadState::kRunnable) {
            // Must use already acquired `ThreadData` because TLS may be in invalid state e.g. during thread detach.
            // Also, this must load SP in sequentially consistent order, because GC
            // may have touched this thread's data, and we must synchronize before
            // continuing.
            // GC would have either changed stored SP handler (with seq_cst),
            // or would have changed `internal::gSuspensionRequested` (with seq_cst),
            // so, loading SP here, or checking `internal::gSuspensionRequested` in
            // `suspendIfRequested` is enough.
            safePoint(threadData_, std::memory_order_seq_cst);
            common::ZeroThreadLocalDataReg();
        }
        return oldState;
    });
}

kotlin::ThreadState kotlin::mm::ThreadSuspensionData::setStateNoSafePoint(ThreadState newState) noexcept
{
    return state_.exchange(newState, std::memory_order_acq_rel);
}

NO_EXTERNAL_CALLS_CHECK void kotlin::mm::ThreadSuspensionData::suspendIfRequested() noexcept {
    if (IsThreadSuspensionRequested()) {
        auto pauseHandle = pauseMutationInScope(internal::gSuspensionRequestReason.load(std::memory_order_relaxed));
        threadData_.gc().OnSuspendForGC();
        std::unique_lock lock(gSuspensionRequestMutex);
        gSuspensionCondVar.wait(lock, []() { return !IsThreadSuspensionRequested(); });

        // Must return to running state under the lock.
        pauseHandle.resume();
    }
}

void mm::ThreadSuspensionData::requestThreadsSuspension(const char* reason) noexcept {
    assertNotCRT();
    RuntimeAssert(state() == ThreadState::kRunnable, "Requesting thread suspension from the Native state may lead to a deadlock");

    while (!TryRequestThreadsSuspension(reason)) {
        mm::safePoint(threadData_);
    }
}

PERFORMANCE_INLINE mm::ThreadSuspensionData::MutatorPauseHandle mm::ThreadSuspensionData::pauseMutationInScope(const char* reason) noexcept {
    return MutatorPauseHandle(reason, threadData_);
}

void kotlin::mm::RequestThreadsSuspension(internal::SuspensionReason reason) noexcept {
    assertNotCRT(); // NOTE: This function should not be called with CRT GC

    RuntimeAssert(!mm::ThreadRegistry::Instance().IsCurrentThreadRegistered(),
                  "Registered thread must properly handle concurrent suspension requests (suspend if requested)");

    while (!TryRequestThreadsSuspension(reason)) {
        std::unique_lock lock(gSuspensionRequestMutex);
        gSuspensionCondVar.wait(lock, []() { return !IsThreadSuspensionRequested(); });
    }
}

bool kotlin::mm::TryRequestThreadsSuspension(internal::SuspensionReason reason) noexcept {
    assertNotCRT(); // TODO: CRT does not support thread suspension yet, or we still need this?

    CallsCheckerIgnoreGuard guard;

    RuntimeAssert(gSafePointActivator == std::nullopt, "Current thread already suspended threads.");
    {
        std::unique_lock lock(gSuspensionRequestMutex);
        // Someone else has already suspended threads.
        if (internal::gSuspensionRequestReason.load(std::memory_order_relaxed) != nullptr) {
            return false;
        }
        gSafePointActivator = mm::SafePointActivator();
        internal::gSuspensionRequestReason.store(reason);
    }

    return true;
}

void kotlin::mm::WaitForThreadsSuspension() noexcept {
    assertNotCRT(); // TODO: CRT does not support thread suspension yet, or we still need this?

    auto& threadRegistry = kotlin::mm::ThreadRegistry::Instance();
    auto* currentThread = (threadRegistry.IsCurrentThreadRegistered()) ? threadRegistry.CurrentThreadData() : nullptr;
    // Spin waiting for threads to suspend. Ignore Native threads.
    threadRegistry.waitAllThreads([currentThread](mm::ThreadData& thread) noexcept {
        return &thread == currentThread || thread.suspensionData().suspendedOrNative();
    });
}

void kotlin::mm::ResumeThreads() noexcept {
    assertNotCRT(); // TODO: CRT does not support thread suspension yet, or we still need this?
    RuntimeAssert(gSafePointActivator != std::nullopt, "Current thread must have suspended threads");
    gSafePointActivator = std::nullopt;

    // From the std::condition_variable docs:
    // Even if the shared variable is atomic, it must be modified under
    // the mutex in order to correctly publish the modification to the waiting thread.
    // https://en.cppreference.com/w/cpp/thread/condition_variable
    {
        std::unique_lock lock(gSuspensionRequestMutex);
        internal::gSuspensionRequestReason.store(nullptr);
    }
    gSuspensionCondVar.notify_all();
}
