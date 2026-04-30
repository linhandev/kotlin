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
#pragma once

#include <map>
#include "RootSet.hpp"
#include "common_interfaces/objects/base_object.h"
#include "common_interfaces/objects/base_state_word.h"
#include "common_interfaces/heap/base_roots.h"
#include "common_interfaces/objects/base_finalization.h"
#include "KNBaseObject.hpp"

#include "Memory.h"
#include "Utils.hpp"
#include "ThreadData.hpp"

namespace common {

class KNRootsVisitor : public common::RootsRegistryInterface, private kotlin::Pinned {
public:
    void VisitGlobalRoots(const RefFieldVisitor& visitor)
    {
        auto phase = common::GetGCPhase();
        if (phase == common::GCPhase::GC_PHASE_FINAL_MARK) {
            CollectRootSetAndFixDerivedPtr(visitor);
            return;
        }
        TraverseAllRoots(ObjectVisitor{visitor});
    }

    void VisitConcurrentRoots(const RefFieldVisitor& visitor)
    {
        // TODO:
    }

    void VisitMutatorRoots(const RefFieldVisitor& visitor, ThreadHolder* th)
    {
        // TODO:
    }

    void VisitGlobalWeakRoots(const WeakRefFieldVisitor& visitor, bool isYoung)
    {
        // TODO:
    }

    void VisitMutatorWeakRoots(const WeakRefFieldVisitor& visitor, ThreadHolder* th, bool isYoung)
    {
        // TODO:
    }

    void VisitGlobalPreforwardRoots(const RefFieldVisitor& visitor)
    {
        // TODO:
        KNRootsVisitor::VisitGlobalRoots(visitor);
    }

    void VisitMutatorPreforwardRoots(const RefFieldVisitor& visitor, ThreadHolder* th)
    {
        // TODO:
    }

    static KNRootsVisitor& Instance()
    {
        static KNRootsVisitor instance;
        return instance;
    }

private:
    // Used to determine whether a given ptr is a pointer to an valid object header.
    // Will be removed once stackmap is ready.
    static bool isValidObjHeader(void* ptr) noexcept
    {
        auto& refField = reinterpret_cast<common::RefField<>&>(ptr);
        return common::IsHeapAddress(ptr) &&
                common::KNBaseObjectOperator::Instance().IsValidObject(refField.GetTargetObject());
    }

    // A map used to record all base pointer during roots traversal, this will later be used to help identify dervied ptr
    // Will be removed once stackmap is ready
    class ForwardedRootMap {
    public:
        using Map = std::map<uintptr_t, size_t>;
        void Insert(uintptr_t from, size_t size) { roots_[from] = size; }
        bool Find(uintptr_t addr) const { return roots_.find(addr) != roots_.end(); }
        uintptr_t TryForwardDerivedPtr(uintptr_t addr)
        {
            auto iter = roots_.lower_bound(addr);
            if (iter == roots_.begin()) {
                return 0;
            }
            --iter;
            uintptr_t old = iter->first;
            if (addr >= (old + iter->second)) {
                return 0;
            }
            uintptr_t base = reinterpret_cast<common::KNStateWord*>(old)->GetForwardingPointerAfterExclusive();
            ASSERT(isValidObjHeader(reinterpret_cast<void*>(base)));
            return base + (addr - old);
        }

    private:
        Map roots_;
    };

    // If the object is valid, apply RefFieldVisitor on the object
    struct ObjectVisitor {
        const common::RefFieldVisitor& visitorFunc_;

        bool operator()(ObjHeader*& object) const
        {
            auto& refField = reinterpret_cast<common::RefField<>&>(object);
            if (!common::IsHeapAddress(object)) {
                return false;
            }
            // In ambiguous stack scan we can see dirty data from last GC cycle, skip those
            if (refField.GetTargetObject()->IsForwarded()) {
                return false;
            }
            if (!common::KNBaseObjectOperator::Instance().IsValidObject(refField.GetTargetObject())) {
                return false;
            }
            visitorFunc_(refField);
            return true;
        }
    };

    // Before applying RefFieldVisitor on the given object, this operator also records the base ptr in the root map.
    struct RecordingObjectVisitor {
        ForwardedRootMap* rootMap_;
        const common::RefFieldVisitor& visitorFunc_;

        void operator()(ObjHeader*& object) const
        {
            if (!isValidObjHeader(object)) {
                return;
            }
            common::RefField<>& refField = reinterpret_cast<common::RefField<>&>(object);
            common::BaseObject* old = refField.GetTargetObject();
            size_t size = old->GetSize();

            visitorFunc_(refField);

            if (old != refField.GetTargetObject()) {
                rootMap_->Insert(reinterpret_cast<uintptr_t>(old), size);
            }
        }
    };

    // If the given object is a dervied ptr, fix it and return true, false otherwise.
    struct FixDerivedPtrVisitor {
        ForwardedRootMap* rootMap_;

        bool operator()(ObjHeader*& stackRef) const
        {
            if (!common::IsHeapAddress(stackRef)) {
                return false;
            }
            uintptr_t addr = reinterpret_cast<uintptr_t>(stackRef);
            auto newAddr = rootMap_->TryForwardDerivedPtr(addr);
            if (newAddr) {
                // update stack ref
                stackRef = reinterpret_cast<ObjHeader*>(newAddr);
            }
            return true;
        }
    };

    // Returns a pair of values which represent the estimated range for ambiguous stack scan based on our heuristic
    // first > second if no frame should be sacnned
    std::pair<size_t, size_t> StackRange(kotlin::mm::ThreadData& thread);

    // Traverse all roots
    template <typename Visitor>
    void TraverseAllRoots(const Visitor& visitorFunc)
    {
        for (auto& thread : kotlin::mm::GlobalData::Instance().threadRegistry().LockForIter()) {
            thread.Publish();
            TraverseRootsOnThread(visitorFunc, thread);
        }
        TraverseGlobalRoots(visitorFunc);
    }

    // Traverse all roots on thread
    template <typename Visitor>
    void TraverseRootsOnThread(const Visitor& visitorFunc, kotlin::mm::ThreadData& thread)
    {
        TraverseRootsOnThreadStack(visitorFunc, thread);
        for (ObjHeader** tmpObj : thread.tls()) {
            visitorFunc(*tmpObj);
        }
    }

    // A subset of TraverseRootsOnThread, visit only thread's stack
    template <typename Visitor>
    void TraverseRootsOnThreadStack(const Visitor& visitorFunc, kotlin::mm::ThreadData& thread)
    {
        auto [minFrame, maxFrame] = StackRange(thread);
        for (uintptr_t i = minFrame; i < maxFrame; i += sizeof(uintptr_t)) {
            ObjHeader** tmpObj = (reinterpret_cast<ObjHeader**>(i));
            visitorFunc(*tmpObj);
        }
    }

    template <typename Visitor>
    void TraverseGlobalRoots(const Visitor& visitorFunc)
    {
        // TODO: Remove useless mm::GlobalRootSet abstraction.
        for (auto value : kotlin::mm::GlobalRootSet()) {
            visitorFunc(value.object);
        }
    }

    // Collect rootset and fix derived ptr on the way
    void CollectRootSetAndFixDerivedPtr(const common::RefFieldVisitor& visitorFunc);
};

} // namespace common
