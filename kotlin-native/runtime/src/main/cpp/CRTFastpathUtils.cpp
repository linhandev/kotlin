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

#include "Memory.h"
#include "Allocator.hpp"
#include "ThreadData.hpp"
#include "ThreadRegistry.hpp"
#include "MemoryManagerSwitch.hpp"
#ifdef ENABLE_CRT
#include "crt/cpp/HeapInterface.hpp"
#include "common_interfaces/thread/mutator_base.h"
#include "common_interfaces/thread/thread_holder.h"
#include "common_components/mutator/mutator.h"
#endif

#ifdef ENABLE_CRT
namespace {
ALWAYS_INLINE void UpdateThreadLocalDataRegImpl(common::GCPhase phase)
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    // Only enable read barrier slowpath for phases that require forwarding (PRECOPY/COPY/FIX).
    // ENUM/MARK/REMARK/POST_MARK phases have trivial ReadRefField (direct memory load),
    // identical to IdleBarrier, so they don't need the slowpath.
    uintptr_t maskBits = phase >= common::GCPhase::GC_PHASE_PRECOPY ? 1 : 0;
    __asm__ volatile("bfi x28, %0, #" TLS_ACTIVE_READ_BARRIER_BIT ", #1" : : "r"(maskBits));
#endif
}
}

ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const MutatorBase* mutator)
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    UpdateThreadLocalDataRegImpl(mutator->GetMutatorPhase());
#endif
}

ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const ThreadHolder* th)
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    UpdateThreadLocalDataRegImpl(th->GetMutatorPhase());
#endif
}

ALWAYS_INLINE void common::UpdateThreadLocalDataReg()
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    UpdateThreadLocalDataReg(reinterpret_cast<MutatorBase*>(GetMutatorOrNull()));
#endif
}

ALWAYS_INLINE void common::RestoreThreadLocalDataReg(kotlin::mm::ThreadData* threadData)
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    void* tls = LoadCachedCRTTLS(threadData->allocator().impl());
    RuntimeAssert(tls != nullptr, "CRT TLS must not be null");
    SetThreadLocalDataToFixedReg(tls);
    UpdateThreadLocalDataReg(threadData->GetThreadHolder());
#endif
}

ALWAYS_INLINE void common::RestoreThreadLocalDataReg()
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    RestoreThreadLocalDataReg(kotlin::mm::ThreadRegistry::Instance().CurrentThreadData());
#endif
}

ALWAYS_INLINE void common::ZeroThreadLocalDataReg()
{
    assertNotCRT();
#ifdef ENABLE_GC_FASTPATH
    CallToFFixedX28::Verify();
    __asm__ volatile("mov x28, xzr" ::: "x28");
#endif
}

ALWAYS_INLINE common::Mutator* common::GetMutatorOrNull()
{
    assertUseCRT();
#ifdef ENABLE_GC_FASTPATH
    kotlin::AssertThreadState(kotlin::ThreadState::kRunnable);
    uintptr_t tls;
    FixedRegToLocalVar(tls);
    if (AssertionsEnabled) {
        auto td = kotlin::mm::ThreadRegistry::Instance().CurrentThreadData();
        auto crt_tls = reinterpret_cast<uintptr_t>(LoadCachedCRTTLS(td->allocator().impl()));
        RuntimeAssert((tls & TLS_DATA_MASK) == crt_tls,
                      "%" PRIxPTR " != %" PRIxPTR, tls & TLS_DATA_MASK, crt_tls);
    }
    return *reinterpret_cast<Mutator**>((tls & TLS_DATA_MASK) + TLS_MUTATOR_OFF);
#else
    return nullptr;
#endif
}
#else
ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const MutatorBase*) {}
ALWAYS_INLINE void common::UpdateThreadLocalDataReg(const ThreadHolder*) {}
ALWAYS_INLINE void common::UpdateThreadLocalDataReg() {}
ALWAYS_INLINE void common::RestoreThreadLocalDataReg(kotlin::mm::ThreadData*) {}
ALWAYS_INLINE void common::RestoreThreadLocalDataReg() {}
ALWAYS_INLINE void common::ZeroThreadLocalDataReg() {}
ALWAYS_INLINE common::Mutator* common::GetMutatorOrNull() { return nullptr; }
#endif
