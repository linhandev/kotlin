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

#include "CRTAllocator.hpp"

#include <atomic>
#include <cstdint>
#include <cstdlib>
#include <cstring>
#include <unistd.h>

#include "Common.h"
#include "KAssert.h"
#include "Memory.h"
#include "CRTFastpathUtils.hpp"

#include "common_interfaces/heap/heap_allocator.h"
#include "HeapInterface.hpp"
#include "KNBaseObject.hpp"
#include "MemoryManagerSwitch.hpp"

namespace kotlin::alloc {

CRTAllocator::CRTAllocator() noexcept {
    crtTls = common::GetThreadLocalData();
#ifdef ENABLE_GC_FASTPATH
    common::SetThreadLocalDataToFixedReg(reinterpret_cast<uintptr_t>(crtTls));
#endif
}

CRTAllocator::~CRTAllocator() {}

static NO_INLINE common::Address AllocFromCMCSlowPath(size_t size, void* tls) {
    auto allocPtr = common::HeapAllocator::Allocate(size, common::LanguageType::KOTLIN);

#ifdef ENABLE_GC_FASTPATH
    common::UpdateThreadLocalDataReg(*reinterpret_cast<common::MutatorBase**>((char*)tls + common::TLS_MUTATOR_OFF));
#endif
    return allocPtr;
}

uint8_t* CRTAllocator::AllocFromCMC(size_t size) {
    size_t allocSize = common::HeapAllocateSize(size);
    // TODO: maybe cause an extra instruction compared to directly loading from x28. Review later
    auto tls = reinterpret_cast<uintptr_t>(crtTls);
    auto buffer = *reinterpret_cast<uintptr_t*>(tls + common::TLS_ALLOC_BUFFER_OFF);
    auto regionAddr = *reinterpret_cast<uintptr_t*>(buffer + common::ALLOC_BUFFER_REGION_OFF);

    uintptr_t allocPtr = *reinterpret_cast<uintptr_t*>(regionAddr + common::REGION_DESC_ALLOC_OFF);
    uintptr_t regionEnd = *reinterpret_cast<uintptr_t*>(regionAddr + common::REGION_DESC_END_OFF);

    auto endOfAlloc = allocPtr + allocSize;
    if (UNLIKELY(endOfAlloc > regionEnd)) {
        allocPtr = AllocFromCMCSlowPath(size, crtTls);
    } else {
        RuntimeAssert(allocPtr == common::HeapAllocator::Allocate(size, common::LanguageType::KOTLIN), "FastAlloc mismatch");
        *reinterpret_cast<uintptr_t*>(regionAddr + common::REGION_DESC_ALLOC_OFF) = endOfAlloc;
    }
    return reinterpret_cast<uint8_t*>(allocPtr);
}

ALWAYS_INLINE ObjHeader* CRTAllocator::CreateObject(const TypeInfo* typeInfo) noexcept {
    RuntimeAssert(!typeInfo->IsArray(), "Must not be an array");
    auto descriptor = CRTHeapObject::descriptorFrom(typeInfo);
    auto& heapObject = *descriptor.construct(AllocFromCMC(descriptor.size()));
    auto* object = heapObject.object();
    object->typeInfoOrMeta_ = const_cast<TypeInfo*>(typeInfo);
    auto* kobj = reinterpret_cast<common::KNBaseObject*>(object);
    if (typeInfo->flags_ & TF_HAS_FINALIZER) {
        common::BaseFinalizerProcessor::RegisterFinalizableObject(kobj);
    }
    kobj->SetLanguageBitAsKotlin();
    return object;
}

ALWAYS_INLINE ArrayHeader* CRTAllocator::CreateArray(const TypeInfo* typeInfo, uint32_t count) noexcept {
    RuntimeAssert(typeInfo->IsArray(), "Must be an array");
    auto descriptor = CRTHeapArray::descriptorFrom(typeInfo, count);
    uint8_t* memory = AllocFromCMC(descriptor.size());
    std::memset(memory, 0, descriptor.size());
    auto& heapArray = *descriptor.construct(memory);
    ArrayHeader* array = heapArray.array();
    array->typeInfoOrMeta_ = const_cast<TypeInfo*>(typeInfo);
    array->count_ = count;
    reinterpret_cast<common::KNBaseObject*>(array)->SetLanguageBitAsKotlin();
    return array;
}

// static
size_t CRTAllocator::GetAllocatedHeapSize(ObjHeader* object) noexcept {
    return CRTHeapObject::from(object).size();
}

} // namespace kotlin::alloc

// TODO: CRT hash code implementation
RUNTIME_NOTHROW extern "C" KInt Kotlin_CRT_GetOrSetHashCode(ObjHeader* thiz)
{
    assertUseCRT();

    static std::atomic<KInt> CRTGlobalHashIndex = 0xc0000001;
    // Only object (i.e., non-primitive) can be hashed. Therefore if thiz does not belong to heap
    // it must be (when there is no Escape-analysis) a compiler-generated cached boxing value, which reside
    // in the text section of the program, which is not editable or movable.
    // We simply return the address as hash value in this case.
    // TODO: Add a testcase fot this.
    if (!common::Heap::IsHeapAddress(thiz)) {
        return reinterpret_cast<uintptr_t>(thiz);
    }

    using ObjectDescriptor = kotlin::alloc::CRTHeapObject::descriptor::FieldDescriptor<1>;
    using ArrayDescriptor = kotlin::alloc::CRTHeapArray::descriptor::FieldDescriptor<1>;
    static_assert(std::is_same<ObjectDescriptor::value_type, kotlin::KObject>::value, "hash code set on KObject");
    static_assert(std::is_same<ArrayDescriptor::value_type, kotlin::KArray>::value, "hash code set on KObject");

    const auto* typeInfo = thiz->type_info();
    uintptr_t addr;
    if (!typeInfo->IsArray()) {
        addr = reinterpret_cast<uintptr_t>(kotlin::KObject::from(thiz));
        addr += ObjectDescriptor(typeInfo).size() - sizeof(kotlin::CRTHash);
    } else {
        addr = reinterpret_cast<uintptr_t>(kotlin::KArray::from(thiz->array()));
        addr += ArrayDescriptor(typeInfo, thiz->array()->count_).size() - sizeof(kotlin::CRTHash);
    }
    KInt* hash = reinterpret_cast<kotlin::CRTHash*>(addr);
    if (*hash == 0) {
        *hash = CRTGlobalHashIndex.fetch_add(1, std::memory_order_relaxed);
    }
    return *hash;
}
