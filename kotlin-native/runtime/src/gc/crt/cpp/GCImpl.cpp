/*
 * Copyright 2010-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "GCImpl.hpp"

#include "GCStatistics.hpp"
#include "KAssert.h"
#include "Logging.hpp"
#include "crt/cpp/CRTUtils.hpp"
#include "crt/cpp/KNFinalizer.hpp"

using namespace kotlin;

gc::GC::ThreadData::ThreadData(GC& gc, mm::ThreadData& threadData) noexcept {}

gc::GC::ThreadData::~ThreadData() = default;

void gc::GC::ThreadData::OnSuspendForGC() noexcept {}

void gc::GC::ThreadData::safePoint() noexcept {}

void gc::GC::ThreadData::onThreadRegistration() noexcept {}

ALWAYS_INLINE void gc::GC::ThreadData::onAllocation(ObjHeader* object) noexcept {}

gc::GC::GC(alloc::Allocator&, gcScheduler::GCScheduler&) noexcept
{
    RuntimeLogInfo({kTagGC}, "CMC GC initialized (via Common Runtime)");
}

gc::GC::~GC() = default;

void gc::GC::ClearForTests() noexcept
{
    GCHandle::ClearForTests();
}

void gc::GC::StartFinalizerThreadIfNeeded() noexcept {
    RuntimeAssert(common::KNFinalizationInterface::FinalizerThreadIsRunning(),
        "CRT finalizer thread is expected to start during init");
}

void gc::GC::StopFinalizerThreadIfRunning() noexcept {
    assertNotCRT();
}

bool gc::GC::FinalizersThreadIsRunning() noexcept {
    return common::KNFinalizationInterface::FinalizerThreadIsRunning();
}

// static
ALWAYS_INLINE void gc::GC::processObjectInMark(void*, ObjHeader*) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

// static
ALWAYS_INLINE void gc::GC::processArrayInMark(void*, ArrayHeader*) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

int64_t gc::GC::Schedule() noexcept
{
    return 0;
}

void gc::GC::WaitFinished(int64_t epoch) noexcept {}

void gc::GC::WaitFinalizers(int64_t epoch) noexcept {}

ALWAYS_INLINE void gc::beforeHeapRefUpdate(mm::DirectRefAccessor ref, ObjHeader* value, bool loadAtomic) noexcept {}

ALWAYS_INLINE OBJ_GETTER(gc::weakRefReadBarrier, std_support::atomic_ref<ObjHeader*> weakReferee) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

bool gc::isMarked(ObjHeader*) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

ALWAYS_INLINE bool gc::tryResetMark(GC::ObjectData&) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

// static
ALWAYS_INLINE uint64_t type_layout::descriptor<gc::GC::ObjectData>::type::size() noexcept
{
    return 0;
}

// static
ALWAYS_INLINE size_t type_layout::descriptor<gc::GC::ObjectData>::type::alignment() noexcept
{
    return 1;
}

// static
ALWAYS_INLINE gc::GC::ObjectData* type_layout::descriptor<gc::GC::ObjectData>::type::construct(uint8_t* ptr) noexcept
{
    return reinterpret_cast<gc::GC::ObjectData*>(ptr);
}

ALWAYS_INLINE bool gc::barriers::ExternalRCRefReleaseGuard::isNoop()
{
    return true;
}

ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard(mm::DirectRefAccessor) noexcept {}

ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard::ExternalRCRefReleaseGuard(
    ExternalRCRefReleaseGuard&&) noexcept = default;

ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard::~ExternalRCRefReleaseGuard() noexcept = default;

ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard& gc::barriers::ExternalRCRefReleaseGuard::operator=(ExternalRCRefReleaseGuard&&) noexcept = default;
