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

// Stub layer for common-rt / crt symbols referenced by the Kotlin runtime.
//
// When ENABLE_CRT is defined, this header is empty; the real common-rt and
// kotlin-side crt/cpp/* headers must be included by the consumer.
//
// When ENABLE_CRT is not defined (every non-OHOS target), common-rt is not on
// the include path and the crt/cpp/* sources are not compiled. To keep the
// `checkUseCRT` callsites
// compiling unchanged, this header provides forward-declarations and inline
// no-op definitions for every common::* and kotlin-side crt helper that
// appears inside a CRT lambda body. The CRT branch is never executed at
// runtime on these targets (MemoryManagerSwitch::IsEnabled() returns
// constexpr-ish false and checkUseCRT short-circuits to else_f), so the
// stubs only need to compile; they are dead code post-DCE.

#ifndef KOTLIN_NATIVE_CRT_STUBS_HPP
#define KOTLIN_NATIVE_CRT_STUBS_HPP

#ifndef ENABLE_CRT

#include <atomic>
#include <cstddef>
#include <cstdint>

inline bool UNLIKELY(bool x) noexcept { return __builtin_expect(x, 0); }
inline bool LIKELY(bool x) noexcept { return __builtin_expect(x, 1); }

struct ObjHeader;
struct ArrayHeader;
struct TypeInfo;
struct MemoryState;

namespace kotlin {
namespace mm { class ThreadData; class ExtraObjectData; }
namespace alloc { class Allocator; }
} // namespace kotlin

namespace common {

enum class GCReason { GC_REASON_USER };
enum class GCType { GC_TYPE_FULL };
enum class GCPhase { GC_PHASE_IDLE, GC_PHASE_PRECOPY, GC_PHASE_COPY, GC_PHASE_FIX };

// Match libcrt HeapInterface.hpp values so static_assert / arithmetic stays well-formed.
constexpr std::size_t REF_FIELD_TAG_WEAK = 1;
constexpr std::size_t REF_FIELD_REF_UNDEFINED = 2;
constexpr std::size_t WEAK_REF_TAGS_MASK = ~(REF_FIELD_TAG_WEAK | REF_FIELD_REF_UNDEFINED);
constexpr std::size_t TLS_MUTATOR_OFF = sizeof(void*);

class BaseObject {};

class KNBaseObject : public BaseObject {
public:
    void SetWeakRefImplObjectFlag(bool) noexcept {}
};

class MutatorBase {
public:
    void DoLeaveSaferegion() noexcept {}
    GCPhase GetMutatorPhase() const noexcept { return GCPhase::GC_PHASE_IDLE; }
    void SetThread(void*, void (*)(void*, bool) = nullptr) noexcept {}
    void* GetThread() const noexcept { return nullptr; }
};

class Mutator : public MutatorBase {};

class ThreadHolder {
public:
    static ThreadHolder* CreateAndRegisterNewThreadHolder(MutatorBase**) noexcept { return nullptr; }
    static void DestroyThreadHolder(ThreadHolder*) noexcept {}
    void* GetMutator() const noexcept { return nullptr; }
    GCPhase GetMutatorPhase() const noexcept { return GCPhase::GC_PHASE_IDLE; }
    void ReleaseAllocBuffer() noexcept {}
    void BindMutator() noexcept {}
    void UnbindMutator() noexcept {}
    void TransferToRunning() noexcept {}
    void TransferToNative() noexcept {}
};

class BaseRuntime {
public:
    static void RequestGC(GCReason, bool, GCType) noexcept {}
    static void WriteRoot(void*) noexcept {}
    static void WriteBarrier(void*, void*, void*, Mutator* = nullptr) noexcept {}
    static void* ReadBarrier(void*) noexcept { return nullptr; }
    static void* ReadBarrier(void*, void*) noexcept { return nullptr; }
    static void* AtomicReadBarrier(void*, void*, std::memory_order) noexcept { return nullptr; }
    static BaseRuntime* GetInstance() noexcept { return nullptr; }
};

class BaseFinalizerProcessor {
public:
    static void RegisterFinalizableObject(BaseObject*) noexcept {}
};

class KNFinalizationInterface {
public:
    static bool FinalizerThreadIsRunning() noexcept { return false; }
};

class ThreadLocal {
public:
    static void* GetThreadLocalData() noexcept { return nullptr; }
};

inline bool IsHeapAddress(const void*) noexcept { return false; }
inline void BaseObjectPinned(BaseObject*) noexcept {}
inline void BaseObjectUnPinned(BaseObject*) noexcept {}
inline bool IsSafePointActive(void*) noexcept { return false; }

// LoadCachedCRTTLS is declared/defined as a non-template in alloc/custom/cpp/AllocatorImpl.cpp;
// the body uses Impl::crt_alloc() which is satisfied by the stub CRTAllocator below in non-CRT
// builds, so no template stub is needed.

} // namespace common

namespace kotlin {
// Stubs for crt/cpp/CRTRuntime.hpp (declarations there are normally satisfied by
// crt/cpp/CRTRuntime.cpp, which is not compiled when ENABLE_CRT is off).
inline bool InitCRTRuntime() noexcept { return false; }
inline void DestroyCRTRuntime(::MemoryState*) noexcept {}
} // namespace kotlin

namespace kotlin::alloc {
// Stub for crt/cpp/CRTAllocator.hpp. The real CRTAllocator (from common-rt) is unavailable
// on non-OHOS, so callers that try to compile `impl_->crt_alloc().CreateObject(...)` inside
// a checkUseCRT lambda need a type with these public method signatures. The lambda is
// never invoked at runtime here, so every body returns a trivial value.
class CRTAllocator {
public:
    CRTAllocator() noexcept = default;
    ~CRTAllocator() = default;
    ObjHeader* CreateObject(const TypeInfo*) noexcept{ return nullptr; }
    ArrayHeader* CreateArray(const TypeInfo*, std::uint32_t) noexcept { return nullptr; }
    kotlin::mm::ExtraObjectData*
        CreateExtraObjectDataForObject(ObjHeader*, const TypeInfo*) noexcept { return nullptr; }
    static std::size_t GetAllocatedHeapSize(ObjHeader*) noexcept { return 0; }
    void* GetCrtTls() noexcept { return nullptr; }
};
} // namespace kotlin::alloc

#endif // !ENABLE_CRT
#endif // KOTLIN_NATIVE_CRT_STUBS_HPP
