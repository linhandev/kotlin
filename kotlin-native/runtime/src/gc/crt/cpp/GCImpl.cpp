/*
 * Copyright (c) 2026 Huawei Device Co., Ltd.
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
    AssertNotCrt();
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

ALWAYS_INLINE void gc::beforeHeapRefUpdate(mm::DirectRefAccessor ref, ObjHeader* value, bool loadAtomic) noexcept {
    // Match upstream 33af2848b3c: in CRT mode this path is unreachable; the CRT
    // barrier is invoked from `RefAccessor<Heap>::beforeStore` directly. Our
    // previous empty stub silently swallowed any wrong-branch routing (e.g.
    // when checkUseCRT picks the else-branch because x28 isn't yet set), which
    // would lose RSet entries and leave fields stale after compaction. Abort
    // loudly so we can identify the offending call site instead.
    NOT_SUPPORTED_BY_CRT();
}

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

ALWAYS_INLINE gc::barriers::ExternalRCRefReleaseGuard& gc::barriers::ExternalRCRefReleaseGuard::operator=(
    ExternalRCRefReleaseGuard&&) noexcept = default;
