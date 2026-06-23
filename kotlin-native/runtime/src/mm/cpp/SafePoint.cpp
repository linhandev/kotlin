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

#include "CRTFastpathUtils.hpp"
#ifdef ENABLE_CRT
// libpandabase/macros.h provides the UNLIKELY macro used in checkUseCRT
// fast-path lambdas (SafePoint::safePointStub / safePoint). On ENABLE_CRT=0
// CRTStubs.hpp provides an inline UNLIKELY stub instead. No common-rt header
// transitively pulls macros.h, so it has to be included explicitly here.
#include "macros.h"
#include "crt/cpp/HeapInterface.hpp"
#include "crt/cpp/KNRootVisitor.hpp"
#endif
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

// Ported from mpcore/crt_fp_unwind 7e581cd: safePoint() at the very
// top so the GC walker can find the caller's frame when STW kicks in via the
// safe-point slow path; NO_INLINE so callee-saved regs are spilled at the
// call boundary and become visible to the walker. Shadow-stack
// EnterFrame/LeaveFrame is dropped — under fp-unwind the precise stack walker
// reads roots directly from the suspended mutator's stack via stackmaps, so
// the shadow-stack book-keeping is dead weight and adds an extra slot the
// walker would have to skip anyway.
static NO_INLINE void SafePointSlowPath(void* mutatorPtr) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData->RuntimeSetLastFrame();
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
    auto& threadData = *mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData.RuntimeSetLastFrame();
    slowPathImpl(threadData);
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

// Exposed so the Kotlin backend's inlined safepoint poll (NATIVE memory manager) can read the
// safe-point flag without a runtime call. `safePointAction` itself has internal linkage (anonymous
// namespace), so we publish its address through this stable external symbol: at a safepoint the
// backend loads `*Kotlin_mm_safePointActionAddr` (== `safePointAction`) and takes the slow path iff
// it is non-null. Kept in sync with mm::safePoint()'s non-CRT fast path.
extern "C" RUNTIME_EXPORT std::atomic<void (*)(mm::ThreadData&)>* const Kotlin_mm_safePointActionAddr =
        &safePointAction;

extern "C" void SafePointSlowPathStub(void*);

extern "C" NO_INLINE RUNTIME_EXPORT void CSafePointSlowPath(void* mutatorPtr) {
    SafePointSlowPath(mutatorPtr);
}

// NATIVE-mode counterpart of CSafePointSlowPath: the asm K2R stub `slowPathStub`
// (K2RStub.s) spills the callee-saved registers and calls this no-arg C++ slow path.
extern "C" void slowPathStub();

extern "C" NO_INLINE RUNTIME_EXPORT void CslowPath() {
    slowPath();
}

#if defined(ENABLE_STACKMAP) && defined(ENABLE_CRT) && !defined(ENABLE_GC_FASTPATH)
// Lean CRT no-fastpath safepoint check, used by RemoveRedundantSafepoints' CrtNoFastpath
// expansion. Mirrors mm::safePoint()'s non-fastpath CRT branch but RETURNS the
// Mutator* iff a safepoint is pending (else nullptr), so the expanded poll can do
// the fast check here and call `SafePointSlowPathStub(mutator)` DIRECTLY on its cold
// edge. This keeps the slow chain a SINGLE K2R boundary: this helper is plain C++
// (not a K2R stub) and has already returned by the time SafePointSlowPathStub is on
// the stack, so only SafePointSlowPathStub crosses the boundary. Without fastpath x28
// is not the reserved TLS pointer, so the check must go through CurrentThreadData /
// LoadCachedCRTTLS rather than an inline `mov $0, x28`.
extern "C" RUNTIME_NOTHROW RUNTIME_EXPORT void* Kotlin_mm_safePointCheckCRT() noexcept {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    void* tls = common::LoadCachedCRTTLS(threadData->allocator().impl());
    if (UNLIKELY(common::IsSafePointActive(tls))) {
        return threadData->GetThreadHolder()->GetMutator();
    }
    return nullptr;
}
#endif

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
#ifdef ENABLE_STACKMAP
    // DisallowSafepointScope is a stackmap-pipeline debug helper. In OFF dist
    // (ENABLE_STACKMAP=0) AssertAllowSafepoint calls RuntimeAssert which may
    // throw — this gives safePoint() a personality (`__gxx_personality_v0`)
    // and an `invoke + landingpad` for the assert call. When
    // RemoveRedundantSafepointsPhase later inlines this body into a Kotlin
    // function (which uses Kotlin's exception personality, not the C++ one),
    // the EH contexts mismatch and any GC-time safepoint can crash (SEGFAULT
    // under heavy allocation in the OFF build).
    mm::DisallowSafepointScope::AssertAllowSafepoint(GetMemoryState());
#endif
    AssertThreadState(ThreadState::kRunnable);
    checkUseCRT<CheckMode::Fast>([&] {
        // CRT branch references SafePointSlowPathStub which only exists when ENABLE_STACKMAP=1.
        // stackmap=off implies CRT/CMC unused; checkUseCRT routes elsewhere at runtime, but the
        // lambda body still has to compile. Stub-out when stackmap=off.
#ifdef ENABLE_STACKMAP
#ifdef ENABLE_GC_FASTPATH
        // CRT fastpath: x28 holds the TLS pointer.
        uintptr_t tls;
        FixedRegToLocalVar(tls);
        auto mutatorPtr = reinterpret_cast<common::MutatorBase**>(tls + common::TLS_MUTATOR_OFF);
        uint32_t IsSafePointActive = *reinterpret_cast<uint32_t*>(*mutatorPtr);
        if (UNLIKELY(IsSafePointActive)) {
            // Plain C++ slow path (not the asm SafePointSlowPathStub): mm::safePoint() is
            // reached via the ...PrologueStub K2R trampoline, already the lone boundary
            // (it spilled x19-x28); a second asm stub would blur the K2R boundary.
            SafePointSlowPath(*mutatorPtr);
        }
#else
        auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        // avoid using `common::ThreadLocal::GetThreadLocalData` if CRT dynamic link
        void* tls = common::LoadCachedCRTTLS(threadData->allocator().impl());
        if (UNLIKELY(common::IsSafePointActive(tls))) {
            // Same single-K2R-boundary reasoning: plain C++, not the asm stub.
            SafePointSlowPath(threadData->GetThreadHolder()->GetMutator());
        }
#endif
#endif // ENABLE_STACKMAP
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
#ifdef ENABLE_STACKMAP
    // OFF mode drops AssertAllowSafepoint to avoid __gxx_personality_v0 being
    // attached to safePoint(memory_order), which conflicts with Kotlin EH
    // personality after RemoveRedundantSafepointsPhase inlines this body.
    mm::DisallowSafepointScope::AssertAllowSafepoint(threadData);
#endif

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
