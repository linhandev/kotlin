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

#include <cstddef>
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
    // Upstream 33af2848b3c made this a no-op assuming all root scanning runs via VisitConcurrentRoots.
    // But our libcrt (cc-kotlin-dev) still has STW paths (ark_collector.cpp's EnumRootsImpl<VisitGlobalRoots>
    // at line 1025 and STW collection at line 479) that only call VisitGlobalRoots. Empty here loses
    // Kotlin globals + stable refs during STW GCs — GlobalRootsStress / StableRefStress / StableRef
    // benchmark regress from 0 fails to 3 fails. Delegate to VisitConcurrentRoots so both STW and
    // concurrent paths cover the same set of roots.
    void VisitGlobalRoots(const RefFieldVisitor& visitor) { VisitConcurrentRoots(visitor); }

    void VisitConcurrentRoots(const RefFieldVisitor& visitor);

    void VisitMutatorRoots(const RefFieldVisitor& visitor, ThreadHolder* th);

    void VisitGlobalWeakRoots(const WeakRefFieldVisitor& visitor, bool isYoung) {
        // TODO:
    }

    void VisitMutatorWeakRoots(const WeakRefFieldVisitor& visitor, ThreadHolder* th, bool isYoung) {
        // TODO:
    }

    void VisitGlobalPreforwardRoots(const RefFieldVisitor& visitor) {
        // TODO:
    }

    void VisitMutatorPreforwardRoots(const RefFieldVisitor& visitor, ThreadHolder* th) {
        // TODO:
    }

    static KNRootsVisitor& Instance() {
        static KNRootsVisitor instance;
        return instance;
    }

private:
    // Iterate global Kotlin roots. Ported from upstream 33af2848b3c — splits the iteration
    // into globalsRegistry().LockForIter() (Kotlin object globals) and the externalRCRefRegistry
    // roots (stable refs) so the visitor receives a true `ObjHeader*&` (`RefField<>&`) reference
    // that the concurrent COPY/FIX phases can rewrite in place. The previous code went through
    // `mm::GlobalRootSet` which after the *& → * change in RootSet.hpp can no longer bind a
    // RefField<>& to the value-typed `Value::object` field.
    template <typename Visitor>
    static void TraverseGlobalRoots(const Visitor& visitorFunc) {
        for (auto globalField : kotlin::mm::GlobalData::Instance().globalsRegistry().LockForIter()) {
            visitorFunc(*globalField);
        }
        for (auto& specialRef : kotlin::mm::GlobalData::Instance().externalRCRefRegistry().roots()) {
            visitorFunc(specialRef);
        }
    }
};

} // namespace common

namespace kotlin {

// Walks a single thread's Kotlin frames using v3 fp-unwind (`GetStackFrame`) plus
// the LLVM stackmaps to enumerate base object references and derived pointers at
// every Kotlin call site live on that thread's stack. Replaces the prior
// conservative shadow-stack scan (which has been broken since LLVM 19.1.4 stopped
// emitting shadow-stack pushes).
class StackMapHelper {
public:
    using RootVisitor = void (*)(void* closure, ObjHeader**);
    using DerivedPtrVisitor = void (*)(void* closure, uintptr_t*, ObjHeader*, ptrdiff_t);
    StackMapHelper(mm::ThreadData& thread) : currentThread(thread) {}

    void traverseBaseRoots(const common::RefFieldVisitor* visitor);
    void traverseBaseAndDerived(RootVisitor v1, DerivedPtrVisitor v2, void* colsure);

private:
    bool skipHandleDerivedPointer() { return visitDerived == nullptr; }
    void collectRoots(ObjHeader** address);
    void handleDerivedPointer(uintptr_t* address, ObjHeader* base, ptrdiff_t offset);
    std::pair<void*, void*> GetStackMapInfo();

    void resolveBase2DerivedOffset(const std::pair<const int32_t, std::vector<int32_t>>& pair);
    void collectStackMapBaseRoot();
    // thread data must be published
    void tryCollectRootSet();

    mm::ThreadData& currentThread;
    void* visitorClosure;
    RootVisitor visitRoot;
    DerivedPtrVisitor visitDerived;
    void* currentFP;
    void* currentPC;
};
} // namespace kotlin
