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

#include "FinalizerHooks.hpp"
#include "Runtime.h"
#include "Utils.hpp"
#include "common_interfaces/objects/base_finalization.h"
#include "ExternalRCRefRegistry.hpp"
#include <atomic>

namespace common {

class KNFinalizationInterface : public common::BaseFinalizationInterface, private kotlin::Pinned {
public:
    void attachCurrentThread() override {
        common::CallToFFixedX28 guard{}; // only guard x28
        RuntimeAssert(!finalizerThreadIsRunning_, "Finalizer thread is already running");
        // K/N GCs detect that finalizer thread is running by whether the thread is joinable,
        // which happens-before its corresponding runtime is inited, therefore the flag is set first and never dropped.
        finalizerThreadIsRunning_ = true; // atomic store is seq_cst with atomic increment of aliveRuntimesCount in init
        Kotlin_initRuntimeIfNeeded(); // must be below the atomic store to match the checks order during termination
    }
    void invokeFinalizer(BaseObject* obj) const override {
        kotlin::CalledFromNativeGuard guard{}; // guard both x28 and switch to kRunnable state
        kotlin::RunFinalizers(reinterpret_cast<ObjHeader*>(obj));
    }

    void onHeapGarbageReclamation() const override {
        // Iterating ExternalRCRefRegistry removes nodes whose stable refs have been disposed.
        // In upstream K/N, this happens via processWeaks() each GC cycle (by the side-effect of the iterator)
        // CRT doesn't call processWeaks, so we clean up here on the finalizer thread instead.
        for ([[maybe_unused]] auto _ : kotlin::mm::ExternalRCRefRegistry::instance().lockForIter()) {}
    }

    static KNFinalizationInterface& Instance()
    {
        static KNFinalizationInterface instance;
        return instance;
    }

    static inline std::atomic<bool> finalizerThreadIsRunning_ = false;
    static bool FinalizerThreadIsRunning() { return finalizerThreadIsRunning_.load(); }
};

}; // namespace common
