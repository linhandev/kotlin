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

#ifndef CRT_ALLOC_CPP_HEAP_LIMITS_H
#define CRT_ALLOC_CPP_HEAP_LIMITS_H

#include <cstddef>
#include <cstdint>
#include <type_traits>

#include "common_interfaces/thread/mutator_base.h"
#include "common_interfaces/thread/mutator_base-inl.h"
#include "common_interfaces/base_runtime.h"
#include "common_interfaces/objects/base_finalization.h"

/**
 * TODO: should these variable be used ? should add into common_interfaces ?
 */
/**
 * TODO: decouple this dependency"
 */
#define LIBCRT_STANDALONE
#include "common_components/heap/heap.h"
#undef LIBCRT_STANDALONE

#include "common_components/common_runtime/base_runtime_param.h"
#include "common_interfaces/objects/base_finalization.h"
#include "common_components/mutator/thread_local.h"

namespace common {

// this interface is not "common_interfaces" !!!
static inline void RegisterFinalizationInterface(BaseFinalizationInterface* impl)
{
    BaseFinalizerProcessor::RegisterFinalizationInterface(impl);
}

// `RuntimeParam` is "common_interfaces" but the default initializer not !!!
static inline RuntimeParam DefaultRuntimeParam()
{
    return BaseRuntimeParam::DefaultRuntimeParam();
}

constexpr size_t BASE_OBJECT_ALIGNED = 8;
constexpr size_t HEADER_SIZE = 0; // no header for arkcommon object

// tags from CRT common::RefField
constexpr size_t REF_FIELD_TAG_WEAK = 1;
constexpr size_t REF_FIELD_REF_UNDEFINED = 2;

// not simply ~WEAK_REF_TAG as upon clearing weakref CRT will write REF_UNDEFINED instead of 0
constexpr size_t WEAK_REF_TAGS_MASK = ~(REF_FIELD_TAG_WEAK | REF_FIELD_REF_UNDEFINED);

constexpr size_t TLS_ALLOC_BUFFER_OFF = 0; // reinterpret_cast<uintptr_t>(&((ThreadLocalData*)0)->buffer);
constexpr size_t TLS_MUTATOR_OFF = sizeof(void*); // reinterpret_cast<uintptr_t>(&((ThreadLocalData*)0)->mutator);

constexpr size_t ALLOC_BUFFER_REGION_OFF = 0; // AllocationBuffer::GetTLRegionOffset();
constexpr size_t REGION_DESC_ALLOC_OFF = 0; // RegionDesc::GetAllocPtrOffset();
constexpr size_t REGION_DESC_END_OFF = sizeof(uintptr_t); // RegionDesc::GetRegionEndOffset();

template <size_t M>
constexpr size_t RoundDown(size_t n)
{
    return ((n / M) * M);
}

template <size_t M>
constexpr size_t RoundUp(size_t n)
{
    size_t res = RoundDown<M>(n);
    return res + (res == n ? 0 : M);
}

// used to allocation fast path optimize
constexpr size_t HeapAllocateSize(size_t objSize)
{
    constexpr size_t minAllocSize = 16;
    constexpr size_t allocThreshold = 8;
    size_t size = objSize + HEADER_SIZE;
    size = RoundUp<BASE_OBJECT_ALIGNED>(size);
    // TODO: Only Cangjie and kotlin Used
    if (size <= allocThreshold) {
        size = minAllocSize;
    }
    return size;
}

// used to barrier fast path optimize
template <typename T, typename = std::enable_if_t<std::is_integral_v<T> || std::is_pointer_v<T>>>
static inline size_t IsHeapAddress(T addr)
{
    uintptr_t address = reinterpret_cast<uintptr_t>(addr);
    if (address == 0) {
        return false;
    }
    return Heap::IsHeapAddress(address);
}

// used to safepoint fast path optimize
static inline bool IsSafePointActive(void* tls)
{
    void* addr = reinterpret_cast<uint8_t*>(tls) + TLS_MUTATOR_OFF;
    return (*reinterpret_cast<MutatorBase**>(addr))->GetSafepointActiveState();
}

// TODO: shouldn't dependent on GC phase ?
// roots visit need it, barrier fast path need it
static inline GCPhase GetGCPhase()
{
    return Heap::GetHeap().GetGCPhase();
}

// used to safepoint, allocation and barrier fast path optimize
static inline void* GetThreadLocalData()
{
    return ThreadLocal::GetThreadLocalData();
}

// TODO: new interface that mark object is nonmoveable
static inline void BaseObjectPinned(BaseObject* obj)
{
    common::Heap::GetHeap().GetCollector().AddRawPointerObject(obj);
}
// TODO: new interface that mark object is moveable
static inline void BaseObjectUnPinned(BaseObject* obj)
{
    common::Heap::GetHeap().GetCollector().RemoveRawPointerObject(obj);
}

} // namespace common
#endif
