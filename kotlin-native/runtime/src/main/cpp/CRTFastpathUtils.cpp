/*
* Copyright (c) 2025 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "CRTFastpathUtils.hpp"
#include "alloc/common/cpp/Allocator.hpp"
#include "mm/cpp/ThreadData.hpp"
#include "mm/cpp/ThreadRegistry.hpp"
#include "MemoryManagerSwitch.hpp"
#include "crt/cpp/HeapInterface.hpp"
#include "common_components/mutator/mutator.h"

#ifdef ENABLE_GC_FASTPATH
// separate implementation from the header to avoid including mutator.h everywhere
ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const common::MutatorBase* mutator) {
    // Only enable read barrier slowpath for phases that require forwarding (PRECOPY/COPY/FIX).
    // ENUM/MARK/REMARK/POST_MARK phases have trivial ReadRefField (direct memory load),
    // identical to IdleBarrier, so they don't need the slowpath.
    uintptr_t maskBits = mutator->GetMutatorPhase() >= GCPhase::GC_PHASE_PRECOPY ? 1 : 0;
    __asm__ volatile("bfi x28, %0, #62, #1" : : "r"(maskBits));
}

ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const common::ThreadHolder* threadHolder) {
    uintptr_t maskBits = threadHolder->GetMutatorPhase() >= GCPhase::GC_PHASE_PRECOPY ? 1 : 0;
    __asm__ volatile("bfi x28, %0, #62, #1" : : "r"(maskBits));
}

ALWAYS_INLINE void common::UpdateThreadLocalDataReg() {
    auto* td = kotlin::mm::ThreadRegistry::Instance().CurrentThreadData();
    if (td) {
        UpdateThreadLocalDataReg(td->GetThreadHolder());
    }
}

ALWAYS_INLINE void common::RestoreThreadLocalDataReg(kotlin::mm::ThreadData* threadData) {
    auto* holder = threadData->GetThreadHolder();
    auto* tls = common::LoadCachedCRTTLS(threadData->allocator().impl());
    SetThreadLocalDataToFixedReg(reinterpret_cast<uintptr_t>(tls));
    UpdateThreadLocalDataReg(holder);
}

ALWAYS_INLINE void common::RestoreThreadLocalDataReg() {
    auto* td = kotlin::mm::ThreadRegistry::Instance().CurrentThreadData();
    if (td) {
        RestoreThreadLocalDataReg(td);
    }
}

ALWAYS_INLINE void common::ZeroThreadLocalDataReg() {
    CallToFFixedX28::Verify();
    __asm__ volatile("mov x28, #0" ::: "x28");
}

// Ported from upstream 33af2848b3c: read the Mutator pointer directly from the
// CRT TLS block in x28 instead of taking the slow `Mutator::GetMutator()` path.
// The barrier callers (RefAccessor<Heap>::beforeStore) are kRunnable-state, so
// x28 holds a valid TLS pointer; if x28 isn't set (non-CRT mode), they wouldn't
// reach this function at all.
ALWAYS_INLINE common::Mutator* common::GetMutatorOrNull() {
    assertUseCRT();
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    uintptr_t tls;
    FixedRegToLocalVar(tls);
    if (AssertionsEnabled) {
        auto* td = kotlin::mm::ThreadRegistry::Instance().CurrentThreadData();
        auto crt_tls = reinterpret_cast<uintptr_t>(common::LoadCachedCRTTLS(td->allocator().impl()));
        RuntimeAssert((tls & common::TLS_DATA_MASK) == crt_tls,
                      "%" PRIxPTR " != %" PRIxPTR, tls & common::TLS_DATA_MASK, crt_tls);
    }
    return *reinterpret_cast<Mutator**>((tls & common::TLS_DATA_MASK) + common::TLS_MUTATOR_OFF);
}
#else
// Non-ENABLE_GC_FASTPATH implementations (stub versions)
ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const common::MutatorBase*) {}
ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const common::ThreadHolder*) {}
ALWAYS_INLINE void common::UpdateThreadLocalDataReg() {}
ALWAYS_INLINE void common::RestoreThreadLocalDataReg(kotlin::mm::ThreadData*) {}
ALWAYS_INLINE void common::RestoreThreadLocalDataReg() {}
ALWAYS_INLINE void common::ZeroThreadLocalDataReg() {}
ALWAYS_INLINE common::Mutator* common::GetMutatorOrNull() { return nullptr; }
#endif