/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "GCImpl.hpp"

#include <memory>

#include "CompilerConstants.hpp"
#include "GC.hpp"
#include "GCStatistics.hpp"
#include "MarkAndSweepUtils.hpp"
#include "ObjectOps.hpp"

#include "MemoryManagerSwitch.hpp"

using namespace kotlin;

gc::GC::ThreadData::ThreadData(GC& gc, mm::ThreadData& threadData) noexcept :
    impl_(checkUseCRT<CheckMode::Slow>([] {
        return std::unique_ptr<Impl>(nullptr);
    }, [&] {
        return std::make_unique<Impl>(gc.impl().mark_, threadData);
    })) {}

gc::GC::ThreadData::~ThreadData() = default;

void gc::GC::ThreadData::OnSuspendForGC() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->mark_.onSuspendForGC();
    });
}

void gc::GC::ThreadData::safePoint() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->mark_.onSafePoint();
    });
}

void gc::GC::ThreadData::onThreadRegistration() noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->barriers_.onThreadRegistration();
    });
}

PERFORMANCE_INLINE void gc::GC::ThreadData::onAllocation(ObjHeader* object) noexcept {
    checkNotCRT<CheckMode::Slow>([&] {
        impl_->barriers_.onAllocation(object);
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
        RuntimeLogInfo({kTagGC}, "%s GC initialized", internal::CmsGCTraits::kName);
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

// static
PERFORMANCE_INLINE void gc::GC::processObjectInMark(void* state, ObjHeader* object) noexcept {
    assertNotCRT();
    gc::internal::processObjectInMark<gc::mark::ConcurrentMark::MarkTraits>(state, object);
}

// static
PERFORMANCE_INLINE void gc::GC::processArrayInMark(void* state, ArrayHeader* array) noexcept {
    assertNotCRT();
    gc::internal::processArrayInMark<gc::mark::ConcurrentMark::MarkTraits>(state, array);
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

PERFORMANCE_INLINE void gc::beforeHeapRefUpdate(mm::DirectRefAccessor ref, ObjHeader* value, bool loadAtomic) noexcept {
    checkNotCRT<CheckMode::Fast>([&] {
        barriers::beforeHeapRefUpdate(ref, value, loadAtomic);
    });
}

PERFORMANCE_INLINE OBJ_GETTER(gc::weakRefReadBarrier, std_support::atomic_ref<ObjHeader*> weakReferee) noexcept {
    assertNotCRT();
    RETURN_OBJ(gc::barriers::weakRefReadBarrier(weakReferee));
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
        return false;
    });
}
PERFORMANCE_INLINE gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard(mm::DirectRefAccessor ref) noexcept : impl_(ref) {}
PERFORMANCE_INLINE gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard(ExternalRCRefReleaseGuard&& other) noexcept = default;
PERFORMANCE_INLINE gc::barriers::ExternalRCRefReleaseGuard::~ExternalRCRefReleaseGuard() noexcept = default;
PERFORMANCE_INLINE gc::barriers::ExternalRCRefReleaseGuard& gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard::operator=(
        ExternalRCRefReleaseGuard&&) noexcept = default;

// static
ALWAYS_INLINE uint64_t type_layout::descriptor<gc::GC::ObjectData>::type::size() noexcept {
    return checkUseCRT<CheckMode::Fast>([] {
        return size_t{0};
    }, [] {
        return sizeof(gc::GC::ObjectData);
    });
}

// static
ALWAYS_INLINE size_t type_layout::descriptor<gc::GC::ObjectData>::type::alignment() noexcept {
    return checkUseCRT<CheckMode::Fast>([] {
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
