/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "ReferenceOps.hpp"
#include "ThreadData.hpp"
#include "MemoryManagerSwitch.hpp"
#include "CRTFastpathUtils.hpp"

using namespace kotlin;

// Stack reference, doesn't require any barriers.
template<> ALWAYS_INLINE ObjHeader* mm::RefAccessor<mm::RefLocation::Stack>::loadWithBarrier() noexcept {
    return direct_.load();
}
template<> ALWAYS_INLINE ObjHeader* mm::RefAccessor<mm::RefLocation::Stack>::loadAtomicWithBarrier(std::memory_order order) noexcept {
    return direct_.loadAtomic(order);
}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Stack>::afterLoad() noexcept {}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Stack>::beforeStore(ObjHeader*) noexcept {}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Stack>::afterStore(ObjHeader*) noexcept {}

namespace {
/// If read barriers are used and currently enabled, execute given `readBarrier`, otherwise execute `fastPath`.
/// CRT mode: gated by x28 bit 62 (set during PRECOPY/COPY/FIX phases via UpdateThreadLocalDataReg).
/// CMS mode: barriers always go through the slow path here; their own gating is internal.
template<typename F, typename G>
ALWAYS_INLINE auto checkReadBarrier(F readBarrier, G fastPath) {
#ifdef ENABLE_GC_FASTPATH
    // x28 is either zero (non-CRT MM) or has bit 62 set when CRT barriers are active
    // (UpdateThreadLocalDataReg sets it for phase >= GC_PHASE_PRECOPY). Either way,
    // checking bit 62 alone gates correctly: if zero we don't need the barrier;
    // if set we route through CRT's BaseRuntime::ReadBarrier which dispatches to the
    // phase-appropriate Heap::GetBarrier() implementation.
    CHECK_READ_BARRIER_SLOW_PATH(slow_path)
    return fastPath();
slow_path:
    return readBarrier();
#else
    // No fastpath gating available; always defer to checkUseCRT.
    return checkUseCRT<CheckMode::Slow>(readBarrier, fastPath);
#endif
}
}

// Global reference, e.g. a static variable.
template<> ALWAYS_INLINE ObjHeader* mm::RefAccessor<mm::RefLocation::Global>::loadWithBarrier() noexcept {
    return checkReadBarrier([&] {
        return reinterpret_cast<ObjHeader*>(common::BaseRuntime::ReadBarrier(direct_.location()));
    }, [&] {
        return direct_.load();
    });
}
template<> ALWAYS_INLINE ObjHeader* mm::RefAccessor<mm::RefLocation::Global>::loadAtomicWithBarrier(std::memory_order order) noexcept {
    return checkReadBarrier([&] {
        return reinterpret_cast<ObjHeader*>(common::BaseRuntime::AtomicReadBarrier(nullptr, direct_.location(), order));
    }, [&] {
        return direct_.loadAtomic(order);
    });
}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Global>::afterLoad() noexcept {}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Global>::beforeStore(ObjHeader* value) noexcept {
    checkUseCRT<CheckMode::Fast>([&] {
        common::BaseRuntime::WriteRoot(value);
    }, [&] {
        gc::beforeHeapRefUpdate(direct_, value, false);
    });
}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Global>::afterStore(ObjHeader*) noexcept {}

// Heap reference, e.g. field of an object.
//
// thisPtr_ may be null when the Kotlin compiler emits `ReadHeapRef(loc, NULL)`
// for global / $init_global readers. In that case the barrier still works:
// `BaseRuntime::ReadBarrier(NULL, field)` dispatches to `ReadStaticRef`-style
// forwarding via `Heap::GetBarrier()` (the implementations tolerate null obj
// for read-side barriers; only the write side dereferences obj for RSet).
template<> ALWAYS_INLINE ObjHeader* mm::RefAccessor<mm::RefLocation::Heap>::loadWithBarrier() noexcept {
    return checkReadBarrier([&] {
        return reinterpret_cast<ObjHeader*>(common::BaseRuntime::ReadBarrier(this->thisPtr_, direct_.location()));
    }, [&] {
        return direct_.load();
    });
}
template<> ALWAYS_INLINE ObjHeader* mm::RefAccessor<mm::RefLocation::Heap>::loadAtomicWithBarrier(std::memory_order order) noexcept {
    return checkReadBarrier([&] {
        return reinterpret_cast<ObjHeader*>(common::BaseRuntime::AtomicReadBarrier(this->thisPtr_, direct_.location(), order));
    }, [&] {
        return direct_.loadAtomic(order);
    });
}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Heap>::afterLoad() noexcept {}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Heap>::beforeStore(ObjHeader* value) noexcept {
    checkUseCRT<CheckMode::Fast>([&] {
        // Upstream 33af2848b3c: always route through WriteBarrier with the current mutator. The
        // thisPtr_==nullptr special-case is no longer needed — the Kotlin codegen now uses
        // RefAccessor<Global> (via storeGlobalRef / UpdateStaticRef) for global writes, so
        // RefAccessor<Heap>::beforeStore is only invoked with a real object thisPtr.
        common::BaseRuntime::WriteBarrier(this->thisPtr_, direct_.location(), value,
                                          common::GetMutatorOrNull());
    }, [&] {
        gc::beforeHeapRefUpdate(direct_, value, false);
    });
}
template<> ALWAYS_INLINE void mm::RefAccessor<mm::RefLocation::Heap>::afterStore(ObjHeader*) noexcept {}

ALWAYS_INLINE OBJ_GETTER(mm::weakRefReadBarrier, std_support::atomic_ref<ObjHeader*> weakReferee) noexcept {
    RETURN_RESULT_OF(gc::weakRefReadBarrier, weakReferee);
}
