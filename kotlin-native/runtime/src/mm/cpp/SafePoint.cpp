/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "SafePoint.hpp"

#include <atomic>

#define _DARWIN_C_SOURCE
#include <pthread.h>

#include "GCScheduler.hpp"
#include "KAssert.h"
#include "Logging.hpp"
#include "ThreadData.hpp"
#include "ThreadState.hpp"
#include "DisallowSafepointScope.h"

#include "macros.h"
#include "CRTFastpathUtils.hpp"
#include "crt/cpp/HeapInterface.hpp"
#include "crt/cpp/KNRootVisitor.hpp"
#include "MemoryManagerSwitch.hpp"

// TODO: Remove after the bootstrap that brings changes in ClangArgs.kt
#ifndef KONAN_SUPPORTS_SIGNPOSTS
#define KONAN_SUPPORTS_SIGNPOSTS KONAN_MACOSX || KONAN_IOS || KONAN_WATCHOS || KONAN_TVOS
#endif

#if KONAN_SUPPORTS_SIGNPOSTS
#include <os/log.h>
#include <os/signpost.h>
#endif

namespace kotlin {

// Ported from mpcore/crt_fp_unwind 7e581cd: RuntimeSetLastFrame1() at the very
// top so the GC walker can find the caller's frame when STW kicks in via the
// safe-point slow path; NO_INLINE so callee-saved regs are spilled at the
// call boundary and become visible to the walker. Shadow-stack
// EnterFrame/LeaveFrame is dropped — under fp-unwind the precise stack walker
// reads roots directly from the suspended mutator's stack via stackmaps, so
// the shadow-stack book-keeping is dead weight and adds an extra slot the
// walker would have to skip anyway.
static NO_INLINE void SafePointSlowPath(void* mutatorPtr) {
    RuntimeSetLastFrame1();
    assertUseCRT();

    common::MutatorBase* mutator = reinterpret_cast<common::MutatorBase*>(mutatorPtr);
    mutator->DoLeaveSaferegion();
    common::UpdateThreadLocalDataReg(mutator);
};
} // namespace kotlin

using namespace kotlin;

namespace {

[[clang::no_destroy]] std::mutex safePointActionMutex;
int64_t activeCount = 0;
std::atomic<void (*)(mm::ThreadData&)> safePointAction = nullptr;

#if KONAN_SUPPORTS_SIGNPOSTS

#define SAFEPOINT_SIGNPOST_NAME "Safepoint" // signpost API requires strings be literals

class SafePointSignpostInterval : private Pinned {
public:
    explicit SafePointSignpostInterval(mm::ThreadData& threadData) noexcept :
        id_(os_signpost_id_make_with_pointer(logObject, &threadData))
    {
        os_signpost_interval_begin(logObject, id_, SAFEPOINT_SIGNPOST_NAME, "thread id: %" PRIuPTR, threadData.threadId());
    }

    ~SafePointSignpostInterval() {
        os_signpost_interval_end(logObject, id_, SAFEPOINT_SIGNPOST_NAME);
    }

private:
    static os_log_t logObject;
    uint64_t id_;
};

#undef SAFEPOINT_SIGNPOST_NAME

// static
os_log_t SafePointSignpostInterval::logObject = os_log_create("org.kotlinlang.native.runtime", "safepoint");
#else
class SafePointSignpostInterval : private Pinned {
public:
    explicit SafePointSignpostInterval(mm::ThreadData& threadData) noexcept {}
};
#endif

void safePointActionImpl(mm::ThreadData& threadData) noexcept {
    static thread_local bool recursion = false;
    RuntimeAssert(!recursion, "Recursive safepoint");
    AutoReset guard(&recursion, true);

    std::optional<SafePointSignpostInterval> signpost;
    if (compiler::enableSafepointSignposts()) {
        signpost.emplace(threadData);
    }
    // 下面的逻辑考虑直接删掉.
    threadData.gcScheduler().safePoint();
    threadData.gc().safePoint();
    threadData.suspensionData().suspendIfRequested();
}

ALWAYS_INLINE void slowPathImpl(mm::ThreadData& threadData) noexcept {
    assertNotCRT(); // NOTE: When CRT is enabled this function should not be called.

    // reread an action to avoid register pollution outside the function
    auto action = safePointAction.load(std::memory_order_seq_cst);
    if (action != nullptr) {
        action(threadData);
    }
}

NO_INLINE void slowPath() noexcept {
    RuntimeSetLastFrame1();
    slowPathImpl(*mm::ThreadRegistry::Instance().CurrentThreadData());
}

NO_INLINE void slowPath(mm::ThreadData& threadData) noexcept {
    threadData.RuntimeSetLastFrame();
    slowPathImpl(threadData);
}

void incrementActiveCount() noexcept {
    std::unique_lock guard{safePointActionMutex};
    ++activeCount;
    RuntimeAssert(activeCount >= 1, "Unexpected activeCount: %" PRId64, activeCount);
    if (activeCount == 1) {
        RuntimeLogDebug({kTagMM}, "Enabling safe points");
        auto prev = safePointAction.exchange(safePointActionImpl, std::memory_order_seq_cst);
        RuntimeAssert(prev == nullptr, "Action cannot have been set. Was %p", prev);
    }
}

void decrementActiveCount() noexcept {
    std::unique_lock guard{safePointActionMutex};
    --activeCount;
    RuntimeAssert(activeCount >= 0, "Unexpected activeCount: %" PRId64, activeCount);
    if (activeCount == 0) {
        auto prev = safePointAction.exchange(nullptr, std::memory_order_seq_cst);
        RuntimeAssert(prev == safePointActionImpl, "Action must have been %p. Was %p", safePointActionImpl, prev);
        RuntimeLogDebug({kTagMM}, "Disabled safe points");
    }
}

} // namespace

extern "C" void slowPathStub();

extern "C" NO_INLINE RUNTIME_EXPORT void CslowPath() {
    slowPath();
}

// Ported from mpcore/crt_fp_unwind 7e581cd. The asm trampoline in
// aarch64_*_stubs/K2RStub.s spills all callee-saved registers (x19-x27, x28 is
// preserved separately by CRT) onto its own frame, then calls
// `_CSafePointSlowPath`. This is the *critical* mechanism that makes
// Kotlin object pointers held in callee-saved registers visible to the GC
// walker during STW. Without it, when `safePoint()` hits the slow path while
// inlined into a Kotlin function, the live refs in x19-x27 stay only in
// registers and the walker can't see them — after compaction they become
// stale and the next access crashes.
extern "C" void SafePointSlowPathStub(void*);

extern "C" NO_INLINE RUNTIME_EXPORT void CSafePointSlowPath(void* mutatorPtr) {
    SafePointSlowPath(mutatorPtr);
}

ALWAYS_INLINE void mm::safePointStub(std::memory_order fastPathOrder) noexcept {
    AssertThreadState(ThreadState::kRunnable);
    checkUseCRT<CheckMode::Fast>([&] {
#ifdef ENABLE_GC_FASTPATH
        uintptr_t tls;
        FixedRegToLocalVar(tls);
        auto mutatorPtr = reinterpret_cast<common::MutatorBase**>(tls + common::TLS_MUTATOR_OFF);
        uint32_t IsSafePointActive = *reinterpret_cast<uint32_t*>(*mutatorPtr);
        if (UNLIKELY(IsSafePointActive)) {
            SafePointSlowPathStub(*mutatorPtr);
        }
#else
        auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        void* tls = common::LoadCachedCRTTLS(threadData->allocator().impl());
        if (UNLIKELY(common::IsSafePointActive(tls))) {
            SafePointSlowPathStub(threadData->GetThreadHolder()->GetMutator());
        }
#endif
    }, [&] {
        auto action = safePointAction.load(fastPathOrder);
        if (__builtin_expect(action != nullptr, false)) {
            slowPathStub();
        }
    });
}

mm::SafePointActivator::SafePointActivator() noexcept : active_(true) {
    incrementActiveCount();
}

mm::SafePointActivator::~SafePointActivator() {
    if (active_) {
        decrementActiveCount();
    }
}

PERFORMANCE_INLINE void mm::safePoint(std::memory_order fastPathOrder) noexcept
{
    mm::DisallowSafepointScope::AssertAllowSafepoint(GetMemoryState());
    AssertThreadState(ThreadState::kRunnable);
    checkUseCRT<CheckMode::Fast>([&] {
#ifdef ENABLE_GC_FASTPATH
        // CRT fastpath: x28 holds the TLS pointer.
        uintptr_t tls;
        FixedRegToLocalVar(tls);
        auto mutatorPtr = reinterpret_cast<common::MutatorBase**>(tls + common::TLS_MUTATOR_OFF);
        uint32_t IsSafePointActive = *reinterpret_cast<uint32_t*>(*mutatorPtr);
        if (UNLIKELY(IsSafePointActive)) {
            // Go through the asm stub so callee-saved registers holding
            // Kotlin refs become visible to the GC walker. See comment on
            // SafePointSlowPathStub above.
            SafePointSlowPathStub(*mutatorPtr);
        }
#else
        auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        // avoid using `common::ThreadLocal::GetThreadLocalData` if CRT dynamic link
        void* tls = common::LoadCachedCRTTLS(threadData->allocator().impl());
        if (UNLIKELY(common::IsSafePointActive(tls))) {
            SafePointSlowPathStub(threadData->GetThreadHolder()->GetMutator());
        }
#endif
    }, [&] {
        // v3 CMS path: fp-unwind asm stubs handle frame transitions automatically.
        auto action = safePointAction.load(fastPathOrder);
        if (__builtin_expect(action != nullptr, false)) {
            slowPath();
        }
    });
}

// When calling safepoint with threadData, one must not use the TLS information
// instead because TLS might already be freed.
ALWAYS_INLINE void mm::safePoint(mm::ThreadData& threadData, std::memory_order fastPathOrder) noexcept
{
    assertNotCRT();
    mm::DisallowSafepointScope::AssertAllowSafepoint(threadData);

    AssertThreadState(&threadData, ThreadState::kRunnable);
    auto action = safePointAction.load(fastPathOrder);
    if (__builtin_expect(action != nullptr, false)) {
        slowPath(threadData);
    }
}

bool mm::test_support::safePointsAreActive() noexcept {
    return safePointAction.load(std::memory_order_seq_cst) != nullptr;
}

void mm::test_support::setSafePointAction(void (*action)(mm::ThreadData&)) noexcept {
    safePointAction.store(action, std::memory_order_seq_cst);
}
