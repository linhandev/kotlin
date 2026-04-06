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
#include "Logging.hpp"

#include "Runtime.h"
#include "VerifyKotlinStack.hpp"

struct ObjHeader;

namespace kotlin {
namespace mm {

struct KotlinFrame {
    std::vector<uint64_t*> fpStack_;
    std::vector<uint32_t*> pcStack_;
    std::vector<uint8_t> kindStack_;
    std::vector<size_t> logIndex;
    uint64_t counter = 0;
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

    void setFuncPCs(std::vector<void*>& funcPCs) { funcPCs_ = funcPCs; }
    std::vector<void*>& getFuncPCs() { return funcPCs_; }
    void pushLastKotlinFrame(uint32_t* pc, uint64_t* fp, FrameKind kind)
    {
        lastKotlinFrame_.pcStack_.emplace_back(pc);
        lastKotlinFrame_.fpStack_.emplace_back(fp);
        lastKotlinFrame_.kindStack_.emplace_back(static_cast<uint8_t>(kind));
        lastKotlinFrame_.logIndex.emplace_back(lastKotlinFrame_.counter);
        lastKotlinFrame_.counter++;

#if ENABLE_VERIFY_STACK
        VerifyKotlinStack::OnPushFrame(*this, kind);
#endif
    }

    void popLastKotlinFrame(FrameKind kind)
    {
        lastKotlinFrame_.counter++;
        if (lastKotlinFrame_.pcStack_.empty() || lastKotlinFrame_.fpStack_.empty()) {
            PrintLastKotlinFrameLog();
            RuntimeLogInfo({kTagGC},
                "[KotlinFrame] try to pop from empty"
                " lastKotlinFrame_ for thread %" PRIuPTR,
                threadId_);
            abort();
        }
#if ENABLE_VERIFY_STACK
        VerifyKotlinStack::OnPopFrame(*this, kind);
#endif
        lastKotlinFrame_.pcStack_.pop_back();
        lastKotlinFrame_.fpStack_.pop_back();
        lastKotlinFrame_.logIndex.pop_back();
        lastKotlinFrame_.kindStack_.pop_back();
    }

    void PrintLastKotlinFrameLog()
    {
        RuntimeLogInfo({kTagGC}, "[KotlinFrame] lastKotlinFrame_ log for thread %" PRIuPTR ":", threadId_);
        std::stringstream logStream;
        logStream << "[KotlinFrame] kindStack_ content: ";

        for (size_t i = 0; i < lastKotlinFrame_.kindStack_.size(); i++) {
            uint8_t value = lastKotlinFrame_.kindStack_[i];
            FrameKind kind = static_cast<FrameKind>(value);
            bool isUnmanaged = (value & static_cast<uint8_t>(FrameKind::K_UNMANAGED_MASK)) != 0;
            const char* stackType = isUnmanaged ? "Unmanaged" : "Managed";

            const char* kindName = "Unknown";
            switch (kind) {
                case FrameKind::K_K2X: kindName = "K2X"; break;
                case FrameKind::K_WEAK_REF: kindName = "WeakRef"; break;
                case FrameKind::K_SAFE_POINT: kindName = "SafePoint"; break;
                case FrameKind::K_NATIVE_STATE: kindName = "NativeState"; break;
                case FrameKind::K_RUNTIME_TO_KOTLIN: kindName = "RuntimeToKotlin"; break;
                case FrameKind::K_INIT_GLOBALS: kindName = "InitGlobals"; break;
                case FrameKind::K_WORKER_JOB: kindName = "WorkerJob"; break;
                case FrameKind::K_GLOBAL_INIT_ADAPTER: kindName = "GlobalInitAdapter"; break;
                case FrameKind::K_C_EXPORT: kindName = "CExport"; break;
                case FrameKind::K_BOXING: kindName = "Boxing"; break;
                case FrameKind::K_UNBOXING: kindName = "Unboxing"; break;
                case FrameKind::K_DISPOSE_STABLE_REF: kindName = "DisposeStableRef"; break;
                case FrameKind::K_IS_INSTANCE: kindName = "IsInstance"; break;
                case FrameKind::K_CLASS_INSTANCE: kindName = "ClassInstance"; break;
                case FrameKind::K_ENUM_ENTRY: kindName = "EnumEntry"; break;
                default: kindName = "Unknown"; break;
            }

            logStream << "[" << i << "]:" << kindName << "(" << stackType << ")"
                      << " fp=" << lastKotlinFrame_.fpStack_[i]
                      << " pc=" << lastKotlinFrame_.pcStack_[i]
                      << (i < lastKotlinFrame_.kindStack_.size()-1 ? ", " : "");
        }

        RuntimeLogInfo({kTagGC}, "%s", logStream.str().c_str());

        RuntimeLogInfo({kTagGC}, "[KotlinFrame] logIndex and corresponding frames for thread %" PRIuPTR ":", threadId_);
        for (size_t i = 0; i < lastKotlinFrame_.logIndex.size(); i++) {
            size_t index = lastKotlinFrame_.logIndex[i];
            RuntimeLogInfo({kTagGC}, "[KotlinFrame] logIndex[%zu] = %zu", i, index);
        }
#if ENABLE_VERIFY_STACK
        VerifyKotlinStack::TryUnwindAggresively(*this);
#endif
    }

    const KotlinFrame& GetLastKotlinFrame() const { return lastKotlinFrame_; }

    DisallowSafepointScopeData& GetDisallowSafepointScopeData() { return disAllowSafepointScopeData_; }
    HandleScopeData& GetHandleScopeData() { return handleScopeData_; }

    void Publish() noexcept {
        // TODO: These use separate locks, which is inefficient.
        globalsThreadQueue_.Publish();
        externalRCRefRegistry_.publish();
    }

    void ClearForTests() noexcept {
        globalsThreadQueue_.ClearForTests();
        externalRCRefRegistry_.clearForTests();
        allocator_.clearForTests();
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
    // save all function pc in this thread
    std::vector<void*> funcPCs_;

    KotlinFrame lastKotlinFrame_{};

    DisallowSafepointScopeData disAllowSafepointScopeData_;
    HandleScopeData handleScopeData_;
};

} // namespace mm
} // namespace kotlin

#endif // RUNTIME_MM_THREAD_DATA_H
