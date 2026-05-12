/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "GCImpl.hpp"

#include <memory>

#include "Allocator.hpp"
#include "CallsChecker.hpp"
#include "CompilerConstants.hpp"
#include "GC.hpp"
#include "GCScheduler.hpp"
#include "GCStatistics.hpp"
#include "MarkAndSweepUtils.hpp"
#include "ObjectOps.hpp"

#include "MemoryManagerSwitch.hpp"
#include "crt/cpp/KNFinalizer.hpp"

using namespace kotlin;

gc::GC::ThreadData::ThreadData(GC& gc, mm::ThreadData& threadData) noexcept : 
    impl_(checkUseCRT<CheckMode::Slow>([] {
        return std::unique_ptr<Impl>(nullptr);
    }, [&] {
        return std::make_unique<Impl>(gc.impl(), threadData);
    })) {}

gc::GC::ThreadData::~ThreadData() = default;

void gc::GC::ThreadData::OnSuspendForGC() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        CallsCheckerIgnoreGuard guard;
        impl_->markDispatcher_.onSuspendForGC();
    });
}

void gc::GC::ThreadData::safePoint() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->barriers_.onSafePoint();
    });
}

void gc::GC::ThreadData::onThreadRegistration() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->barriers_.onThreadRegistration();
    });
}

PERFORMANCE_INLINE void gc::GC::ThreadData::onAllocation(ObjHeader* object) noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl().barriers_.onAllocation(object);
    });
}

gc::GC::GC(alloc::Allocator& allocator, gcScheduler::GCScheduler& gcScheduler) noexcept :
    impl_(checkUseCRT<CheckMode::Slow>([] {
        RuntimeLogInfo({kTagGC}, "CRT GC initialized");
        return std::unique_ptr<Impl>(nullptr);
    }, [&] {
        return std::make_unique<Impl>(allocator, gcScheduler, compiler::gcMutatorsCooperate(), compiler::auxGCThreads());
    })) {
    checkNotCRT<CheckMode::Slow>([&] {
        RuntimeLogDebug({kTagGC}, "%s GC initialized", internal::PmcsGCTraits::kName);
    });
}

gc::GC::~GC() {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->state_.shutdown();
    });
}

void gc::GC::ClearForTests() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        GCHandle::ClearForTests();
    });
}

void gc::GC::StartFinalizerThreadIfNeeded() noexcept {
    checkUseCRT<CheckMode::Slow>([] {
        RuntimeAssert(common::KNFinalizationInterface::FinalizerThreadIsRunning(),
            "CRT finalizer thread is expected to start during init");
    }, [&] {
        mm::GlobalData::Instance().allocator().startFinalizerThreadIfNeeded();
    });
}

void gc::GC::StopFinalizerThreadIfRunning() noexcept {
    assertNotCRT();
    mm::GlobalData::Instance().allocator().stopFinalizerThreadIfRunning();
}

bool gc::GC::FinalizersThreadIsRunning() noexcept {
    return checkUseCRT<CheckMode::Slow>([] {
        return common::KNFinalizationInterface::FinalizerThreadIsRunning();
    }, [&] {
        return mm::GlobalData::Instance().allocator().finalizersThreadIsRunning();
    });
}

// static
PERFORMANCE_INLINE void gc::GC::processObjectInMark(void* state, ObjHeader* object) noexcept {
    assertNotCRT();
    gc::internal::processObjectInMark<gc::mark::ParallelMark::MarkTraits>(state, object);
}

// static
PERFORMANCE_INLINE void gc::GC::processArrayInMark(void* state, ArrayHeader* array) noexcept {
    assertNotCRT();
    gc::internal::processArrayInMark<gc::mark::ParallelMark::MarkTraits>(state, array);
}

int64_t gc::GC::Schedule() noexcept {
    return checkUseCRT<CheckMode::Slow>([] {
        return int64_t{0};
    }, [&] {
        return impl_->state_.schedule();
    });
}

void gc::GC::WaitFinished(int64_t epoch) noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->state_.waitEpochFinished(epoch);
    });
}

void gc::GC::WaitFinalizers(int64_t epoch) noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->state_.waitEpochFinalized(epoch);
    });
}

ALWAYS_INLINE void gc::beforeHeapRefUpdate(mm::DirectRefAccessor ref, ObjHeader* value, bool loadAtomic) noexcept {
    checkNotCRT<CheckMode::Fast>([&] {});
}

PERFORMANCE_INLINE OBJ_GETTER(gc::weakRefReadBarrier, std_support::atomic_ref<ObjHeader*> weakReferee) noexcept {
    assertNotCRT();
    RETURN_RESULT_OF(gc::WeakRefRead, weakReferee);
}

PERFORMANCE_INLINE bool gc::isMarked(ObjHeader* object) noexcept {
    assertNotCRT();
    return alloc::objectDataForObject(object).marked();
}

PERFORMANCE_INLINE bool gc::tryResetMark(GC::ObjectData& objectData) noexcept {
    assertNotCRT();
    return objectData.tryResetMark();
}

ALWAYS_INLINE bool gc::barriers::ExternalRCRefReleaseGuard::isNoop() {
    return checkUseCRT<CheckMode::Fast>([] {
        return true;
    }, [] {
        return true;
    });
}
ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard(mm::DirectRefAccessor) noexcept {}
ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard(ExternalRCRefReleaseGuard&&) noexcept = default;
ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard::~ExternalRCRefReleaseGuard() noexcept = default;
ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard& gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard::operator=(
        ExternalRCRefReleaseGuard&&) noexcept = default;

// static
ALWAYS_INLINE uint64_t type_layout::descriptor<gc::GC::ObjectData>::type::size() noexcept {
    return checkUseCRT<CheckMode::Slow>([] { // can't be Fast unless GC threads set x28 properly
        return size_t{0};
    }, [] {
        return sizeof(gc::GC::ObjectData);
    });
}

// static
ALWAYS_INLINE size_t type_layout::descriptor<gc::GC::ObjectData>::type::alignment() noexcept {
    return checkUseCRT<CheckMode::Slow>([] { // can't be Fast unless GC threads set x28 properly
        return size_t{1};
    }, [] {
        return alignof(gc::GC::ObjectData);
    });
}

// static
ALWAYS_INLINE gc::GC::ObjectData* type_layout::descriptor<gc::GC::ObjectData>::type::construct(uint8_t* ptr) noexcept {
    return checkUseCRT<CheckMode::Fast>([&] {
        return reinterpret_cast<gc::GC::ObjectData*>(ptr);
    }, [&] {
        return new (ptr) gc::GC::ObjectData();
    });
}

void gc::GC::onEpochFinalized(int64_t epoch) noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        GCHandle::getByEpoch(epoch).finalizersDone();
        impl_->state_.finalized(epoch);
    });
}
