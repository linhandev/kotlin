/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include "Allocator.hpp"

#include "CustomAllocator.hpp"
#include "CustomFinalizerProcessor.hpp"
#include "GlobalData.hpp"
#include "Heap.hpp"
#include "SegregatedFinalizerProcessor.hpp"

#ifdef ENABLE_CRT
#include "crt/cpp/CRTAllocator.hpp"
#endif
#include "MemoryManagerSwitch.hpp"

namespace kotlin::alloc {

class Allocator::Impl : private Pinned {
public:
    Impl() noexcept : finalizerProcessor_([](int64_t epoch) noexcept { mm::GlobalData::Instance().gc().onEpochFinalized(epoch); }) {}

    Heap& heap() noexcept { return heap_; }
    FinalizerQueue& pendingFinalizers() noexcept { return pendingFinalizers_; }
    SegregatedFinalizerProcessor<FinalizerQueueSingle, FinalizerQueueTraits>& finalizerProcessor() noexcept { return finalizerProcessor_; }

private:
    Heap heap_;
    // Preallocated place for finalizers for avoiding memory allocation during GC.
    FinalizerQueue pendingFinalizers_;
    SegregatedFinalizerProcessor<FinalizerQueueSingle, FinalizerQueueTraits> finalizerProcessor_;
};

class Allocator::ThreadData::Impl : private Pinned {
public:
    explicit Impl(Allocator::Impl& allocator) noexcept
    {
        // Note: placement-new of union member is used to avoid referencing
        // CRTAllocator if CRT is disabled at compile-time.
        checkUseCRT<CheckMode::Slow>([&] {
            new (&alloc_.crt_) CRTAllocator();
        }, [&]() {
            new (&alloc_.custom_) CustomAllocator(allocator.heap());
        });
    }

    CRTAllocator& crt_alloc() noexcept {
        assertUseCRT();
        return alloc_.crt_;
    }
    CustomAllocator& alloc() noexcept {
        assertNotCRT();
        return alloc_.custom_;
    }

private:
    union Alloc { // Note: not a std::variant to avoid referencing ~CRTAllocator if CRT is disabled at compile-time.
        Alloc() {}
        ~Alloc()
        {
            checkUseCRT<CheckMode::Slow>([&] {
                crt_.~CRTAllocator();
            }, [&] {
                custom_.~CustomAllocator();
            });
        }

        CRTAllocator crt_;
        CustomAllocator custom_;
    } alloc_;
};

} // namespace kotlin::alloc
