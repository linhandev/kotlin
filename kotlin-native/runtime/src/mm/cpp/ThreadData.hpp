/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#ifndef RUNTIME_MM_THREAD_DATA_H
#define RUNTIME_MM_THREAD_DATA_H

#include <cstdint>
#include <vector>
#include <stack>
#include <sstream>

#include "DisallowSafepointScope.h"
#include "HandleScope.h"
#include "GlobalData.hpp"
#include "GlobalsRegistry.hpp"
#include "GC.hpp"
#include "ShadowStack.hpp"
#include "ExternalRCRefRegistry.hpp"
#include "ThreadLocalStorage.hpp"
#include "Utils.hpp"
#include "ThreadSuspension.hpp"

#include "common_interfaces/thread/thread_holder.h"
#include "Runtime.h"
#include "MemoryManagerSwitch.hpp"

struct ObjHeader;

namespace kotlin {
namespace mm {


enum class FrameStatus : uint8_t {
    RISKY,
    RELIABLE
};

struct FrameAddress {
    FrameAddress* prevThreadState;
    const uint32_t* returnAddr;
};

struct LastFrameInfo {
    FrameAddress *lastFrame = nullptr;
    FrameStatus status = FrameStatus::RELIABLE;
    uint32_t *lastPC = nullptr;
};

// `ThreadData` is supposed to be thread local singleton.
// Pin it in memory to prevent accidental copying.
class ThreadData final : private Pinned {
public:
    explicit ThreadData(uintptr_t threadId) noexcept :
        threadId_(threadId),
        globalsThreadQueue_(GlobalsRegistry::Instance()),
        externalRCRefRegistry_(ExternalRCRefRegistry::instance()),
        gcScheduler_(GlobalData::Instance().gcScheduler(), *this),
        allocator_(GlobalData::Instance().allocator()),
        gc_(GlobalData::Instance().gc(), *this),
        suspensionData_(ThreadState::kNative, *this) {}

    ~ThreadData() = default;

    uintptr_t threadId() const noexcept { return threadId_; }

    GlobalsRegistry::ThreadQueue& globalsThreadQueue() noexcept { return globalsThreadQueue_; }

    ThreadLocalStorage& tls() noexcept { return tls_; }

    ExternalRCRefRegistry::ThreadQueue& externalRCRefRegistry() noexcept { return externalRCRefRegistry_; }

    ThreadState state() noexcept { return suspensionData_.state(); }

    ThreadState setState(ThreadState state) noexcept { return suspensionData_.setState(state); }

    ShadowStack& shadowStack() noexcept { return shadowStack_; }

    std::vector<std::pair<ObjHeader**, ObjHeader*>>& initializingSingletons() noexcept { return initializingSingletons_; }

    gcScheduler::GCScheduler::ThreadData& gcScheduler() noexcept { return gcScheduler_; }

    alloc::Allocator::ThreadData& allocator() noexcept { return allocator_; }

    gc::GC::ThreadData& gc() noexcept { return gc_; }

    ThreadSuspensionData& suspensionData() { return suspensionData_; }

    const LastFrameInfo &GetLastFrameInfo()
    {
        return lastFrameInfo_;
    }

    void SetLastFrameInfo(LastFrameInfo lastFrameInfo)
    {
        lastFrameInfo_ = lastFrameInfo;
    }

    // invoke before enter safe region in runtime
    ALWAYS_INLINE void RuntimeSetLastFrame()
    {
        if (lastFrameInfo_.status != FrameStatus::RISKY) {
            void* fa = __builtin_frame_address(0);
            lastFrameInfo_.lastFrame = static_cast<FrameAddress*>(fa)->prevThreadState;
            void* ip = __builtin_return_address(0);
            lastFrameInfo_.lastPC = static_cast<uint32_t*>(ip);
        }
    }

    DisallowSafepointScopeData& GetDisallowSafepointScopeData() { return disAllowSafepointScopeData_; }
    HandleScopeData& GetHandleScopeData() { return handleScopeData_; }

    void Publish() noexcept {
        // TODO: These use separate locks, which is inefficient.

        // TODO: This publishes:
        // 1. all global roots in thread-local to public
        // 2. All TLS special ref to public
        // Later we might be able to flip the mutator to do their own work
        globalsThreadQueue_.Publish();
        externalRCRefRegistry_.publish();
    }

    void ClearForTests() noexcept {
        globalsThreadQueue_.ClearForTests();
        externalRCRefRegistry_.clearForTests();
        allocator_.clearForTests();
    }

    common::ThreadHolder *GetThreadHolder() const
    {
        assertUseCRT();
        return threadHolder;
    }

    void SetThreadHolder(common::ThreadHolder *holder)
    {
        assertUseCRT();
        threadHolder = holder;
        void* mutator = threadHolder->GetMutator();
        reinterpret_cast<common::MutatorBase*>(mutator)->SetThread(this);
        RuntimeAssert(reinterpret_cast<common::MutatorBase*>(mutator)->GetThread() == this, "unknown error");
    }

    void ClearThreadHolder()
    {
        assertUseCRT();
        threadHolder->UnbindMutator();
        common::ThreadHolder::DestroyThreadHolder(threadHolder);
        threadHolder = nullptr;
    }

    static ThreadData* EvalKotlinThreadData(common::ThreadHolder* threadHolder)
    {
        assertUseCRT();
        auto* mutator = reinterpret_cast<common::MutatorBase*>(threadHolder->GetMutator());
        auto* result = reinterpret_cast<ThreadData*>(mutator->GetThread());
        if (result == nullptr) {
            return nullptr;
        }
        RuntimeAssert(result->threadHolder == threadHolder, "threadHolder must be bound correctly");
        return result;
    }

private:
    const uintptr_t threadId_;
    GlobalsRegistry::ThreadQueue globalsThreadQueue_;
    ThreadLocalStorage tls_;
    ExternalRCRefRegistry::ThreadQueue externalRCRefRegistry_;
    ShadowStack shadowStack_;
    gcScheduler::GCScheduler::ThreadData gcScheduler_;
    alloc::Allocator::ThreadData allocator_;
    gc::GC::ThreadData gc_;
    std::vector<std::pair<ObjHeader**, ObjHeader*>> initializingSingletons_;
    ThreadSuspensionData suspensionData_;

    DisallowSafepointScopeData disAllowSafepointScopeData_;
    HandleScopeData handleScopeData_;
    LastFrameInfo lastFrameInfo_ { nullptr, FrameStatus::RISKY, nullptr };
    // CRT-specific thread holder; nullptr in CMS mode.
    common::ThreadHolder *threadHolder = nullptr;
};

} // namespace mm
} // namespace kotlin

#endif // RUNTIME_MM_THREAD_DATA_H
