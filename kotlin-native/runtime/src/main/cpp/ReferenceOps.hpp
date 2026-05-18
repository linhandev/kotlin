/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#pragma once

#include <cstdlib>
#include "crt/cpp/HeapInterface.hpp"

#include "Memory.h"
#include "std_support/Atomic.hpp"
#include "MemoryManagerSwitch.hpp"


#if __has_feature(thread_sanitizer)
#include <sanitizer/tsan_interface.h>
#endif

// C++ memory model is in some sence stricter than the memmory model of real target CPUs.
// For example all the ptr-sized memory accesses on intel x86 and arm CPUs are atomic.
// Another case is the release-consume memory ordering, which can be achieved without additional memory fences on consume.
//
// However, LLVM often fails to properly optimize atomic operations.
// So we have to allow some imeplementation-defined UB here.
//
// Under this flag all tha operations with references in the kotlin heap
// are implemented in complete complience with C++ memory model.
#define STRICT_ATOMICS_IN_HEAP __has_feature(thread_sanitizer)

namespace kotlin::mm {

// TODO: Make sure these operations work with any kind of thread stopping: safepoints and signals.

// TODO: Consider adding some kind of an `Object` type (that wraps `ObjHeader*`) which
//       will have these operations for a friendlier API.

/**
 * Represents direct low-level operations on Koltin references.
 * No GC barriers are inserted. Should be used with care!
 */
class DirectRefAccessor {
public:
    DirectRefAccessor() = delete;
    DirectRefAccessor& operator=(const DirectRefAccessor&) = delete;

    // Bind to an `ObjHeader*` lvalue (used by ExternalRCRefImpl, which holds an
    // ObjHeader* member that GC needs to update). Restored from the pre-port API
    // — upstream commented this out and changed callers to pass `&fieldRef`, but
    // we keep it to avoid touching ExternalRCRef.cpp.
    explicit DirectRefAccessor(ObjHeader*& fieldRef) noexcept : refPtr_(&fieldRef) {}
    explicit DirectRefAccessor(ObjHeader** fieldPtr) noexcept : refPtr_(fieldPtr) {}
    DirectRefAccessor(const DirectRefAccessor& other) noexcept : DirectRefAccessor(other.refPtr_) {}

    ObjHeader** location() const noexcept { return refPtr_; }

    ALWAYS_INLINE operator ObjHeader*() const noexcept { return load(); }
    ALWAYS_INLINE ObjHeader* operator=(ObjHeader* desired) noexcept { store(desired); return desired; }

    ALWAYS_INLINE ObjHeader* load() const noexcept {
#if STRICT_ATOMICS_IN_HEAP
        // Consume stores in the object, that were released on the object's allocation
        // See `ObjectOps.cpp`
        auto loaded = loadAtomic(std::memory_order_consume);
#if __has_feature(thread_sanitizer)
        // The stores were released by an atomic_thread_fence, TSAN doesn't support fences.
        __tsan_acquire(loaded);
#endif
        return loaded;
#else
        return *refPtr_;
#endif
    }

    ALWAYS_INLINE void store(ObjHeader* desired) noexcept {
#if STRICT_ATOMICS_IN_HEAP
        storeAtomic(desired, std::memory_order_relaxed);
#else
        *refPtr_ = desired;
#endif
    }

    ALWAYS_INLINE auto atomic() noexcept {
        return std_support::atomic_ref{*refPtr_};
    }
    ALWAYS_INLINE auto atomic() const noexcept {
        return std_support::atomic_ref{*refPtr_};
    }

    ALWAYS_INLINE ObjHeader* loadAtomic(std::memory_order order) const noexcept {
        return atomic().load(order);
    }
    ALWAYS_INLINE void storeAtomic(ObjHeader* desired, std::memory_order order) noexcept {
        atomic().store(desired, order);
    }
    ALWAYS_INLINE ObjHeader* exchange(ObjHeader* desired, std::memory_order order) noexcept {
        return atomic().exchange(desired, order);
    }
    ALWAYS_INLINE bool compareAndExchange(ObjHeader*& expected, ObjHeader* desired, std::memory_order order) noexcept {
        return atomic().compare_exchange_strong(expected, desired, order);
    }

private:
    ObjHeader** refPtr_;
};

template<bool>
class RefHost {
protected:
    RefHost() = delete;
    RefHost(RefHost&&) = default;
    RefHost(const RefHost&) = default;
    // No assert on thisPtr_ — the Kotlin compiler emits `UpdateHeapRef(loc, val, NULL)`
    // for some `$init_global` initializers, so a null thisPtr is a valid call shape.
    // The Heap-specialized beforeStore handles the null case by routing to
    // `BaseRuntime::WriteRoot` (Global semantics) instead of WriteBarrier.
    explicit RefHost(ObjHeader* thisPtr) noexcept : thisPtr_(thisPtr) {}
    ObjHeader* thisPtr_;
};

template<> class RefHost<false> {};

enum class RefLocation { Stack, Global, Heap };

/**
 * Represents Koltin-level operations on Koltin references.
 * With all the necessary GC barriers etc.
 * Prefer using aliases below.
 */
template<RefLocation refLocation>
class RefAccessor : RefHost<refLocation == RefLocation::Heap> {
public:
    RefAccessor() = delete;
    RefAccessor& operator=(const RefAccessor&) = delete;

    template<bool nonHeap = refLocation != RefLocation::Heap, typename = std::enable_if_t<nonHeap>>
    explicit RefAccessor(ObjHeader** fieldPtr) noexcept : direct_(fieldPtr) {}

    template<bool onHeap = refLocation == RefLocation::Heap, typename = std::enable_if_t<onHeap>>
    RefAccessor(ObjHeader** fieldPtr, ObjHeader* thisPtr) noexcept : RefHost<onHeap>(thisPtr), direct_(fieldPtr) {}

    // Copy constructor is defaulted to avoid explicitly referring to RefHost,
    // its appropriate copy-constructor will be selected by compiler.
    RefAccessor(const RefAccessor& other) noexcept = default;

    DirectRefAccessor direct() const noexcept { return direct_; }

private:
    ObjHeader* loadWithBarrier() noexcept;
    ObjHeader* loadAtomicWithBarrier(std::memory_order order) noexcept;
    void afterLoad() noexcept;
    void beforeStore(ObjHeader* value) noexcept;
    void afterStore(ObjHeader* value) noexcept;

public:
    ALWAYS_INLINE operator ObjHeader*() noexcept { return load(); }

    ALWAYS_INLINE ObjHeader* load() noexcept {
        AssertThreadState(ThreadState::kRunnable);
        auto result = loadWithBarrier();
        afterLoad();
        return result;
    }

    ALWAYS_INLINE ObjHeader* loadAtomic(std::memory_order order) noexcept {
        AssertThreadState(ThreadState::kRunnable);
        auto result = loadAtomicWithBarrier(order);
        afterLoad();
        return result;
    }

    ALWAYS_INLINE ObjHeader* operator=(ObjHeader* desired) noexcept { store(desired); return desired; }

    ALWAYS_INLINE void store(ObjHeader* desired) noexcept {
        AssertThreadState(ThreadState::kRunnable);
        beforeStore(desired);
        direct_.store(desired);
        afterStore(desired);
    }

    ALWAYS_INLINE void storeAtomic(ObjHeader* desired, std::memory_order order) noexcept {
        AssertThreadState(ThreadState::kRunnable);
        beforeStore(desired);
        direct_.storeAtomic(desired, order);
        afterStore(desired);
    }

    ALWAYS_INLINE ObjHeader* exchange(ObjHeader* desired, std::memory_order order) noexcept {
        AssertThreadState(ThreadState::kRunnable);
        loadWithBarrier(); // canonicalize stored pointer
        beforeStore(desired);
        auto result = direct_.exchange(desired, order);
        afterStore(desired);
        afterLoad();
        return result;
    }

    ALWAYS_INLINE bool compareAndExchange(ObjHeader*& expected, ObjHeader* desired, std::memory_order order) noexcept {
        AssertThreadState(ThreadState::kRunnable);
        auto cur = loadWithBarrier(); // canonicalize stored pointer
        if (cur != expected) {
            // value has been moved, simply return false and update expected
            expected = cur;
            return false;
        }

        beforeStore(desired);
        bool result = direct_.compareAndExchange(expected, desired, order);
        afterStore(desired);
        afterLoad();
        return result;
    }

private:
    DirectRefAccessor direct_;
};

using RefFieldAccessor = RefAccessor<RefLocation::Heap>;
using GlobalRefAccessor = RefAccessor<RefLocation::Global>;
using StackRefAccessor = RefAccessor<RefLocation::Stack>;

class RefField : private Pinned {
public:
    auto accessor() noexcept {
        return mm::RefFieldAccessor(&value_, &header);
    }
    auto direct() noexcept {
        return accessor().direct();
    }
    // FIXME probably most of the uses should instead use accessor
    auto ptr() noexcept {
        return direct().location();
    }

    // TODO consider adding other operations
    ObjHeader* operator=(ObjHeader* value) noexcept {
        accessor() = value;
        return value_;
    }

    bool operator==(const RefField& other) const noexcept {
        return value_ == other.value_;
    }

    bool operator!=(const RefField& other) const noexcept {
        return !operator==(other);
    }

private:
    // Synthetic hosting object for `value_` so that `accessor()` can pass a
    // non-null `thisPtr` to the read/write barriers (CRT `BaseRuntime::*Barrier`
    // requires it; CMS barriers ignore it). Without this, the per-RefField
    // accessor would skip the barrier dispatch, breaking remembered-set updates
    // and read-barrier forwarding for stable refs.
    ObjHeader header {.typeInfoOrMeta_ = nullptr};
    ObjHeader* value_ = nullptr;
};

OBJ_GETTER(weakRefReadBarrier, std_support::atomic_ref<ObjHeader*> weakReferee) noexcept;

}
