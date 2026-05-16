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
#include "macros.h"

namespace kotlin::alloc {

CRTAllocator::CRTAllocator() noexcept {
    crtTLS = common::GetThreadLocalData();
#ifdef ENABLE_GC_FASTPATH
    // Init fastpath state by moving crtTLS into x28
    SetThreadLocalDataToFixedReg(crtTLS);
    RuntimeLogInfo({kTagGC}, "fastpath initialized");
#endif
}

CRTAllocator::~CRTAllocator() {}

static NO_INLINE common::Address AllocFromCMCSlowPath(size_t size, uintptr_t tls) {
    auto allocPtr = common::HeapAllocator::Allocate(size, common::LanguageType::KOTLIN);

#ifdef ENABLE_GC_FASTPATH
    common::UpdateThreadLocalDataReg(*reinterpret_cast<common::MutatorBase**>(tls + common::TLS_MUTATOR_OFF));
#endif
    return allocPtr;
}

uint8_t* CRTAllocator::AllocFromCMC(size_t size) {
    size_t allocSize = common::HeapAllocateSize(size);
#ifdef ENABLE_GC_FASTPATH
    uintptr_t tls;
    FixedRegToLocalVar(tls);
#else
    auto tls = reinterpret_cast<uintptr_t>(crtTLS);
#endif
    auto buffer = *reinterpret_cast<uintptr_t*>(tls + common::TLS_ALLOC_BUFFER_OFF);
    auto regionAddr = *reinterpret_cast<uintptr_t*>(buffer + common::ALLOC_BUFFER_REGION_OFF);

    uintptr_t allocPtr = *reinterpret_cast<uintptr_t*>(regionAddr + common::REGION_DESC_ALLOC_OFF);
    uintptr_t regionEnd = *reinterpret_cast<uintptr_t*>(regionAddr + common::REGION_DESC_END_OFF);

    auto endOfAlloc = allocPtr + allocSize;
    if (UNLIKELY(endOfAlloc > regionEnd)) {
        // Ported from mpcore/crt_fp_unwind 7e581cd. Slow-path enters CRT C++
        // code which may trigger STW; capture this frame so the GC walker can
        // unwind from here back through the Kotlin caller.
        RuntimeSetLastFrame1();
        allocPtr = AllocFromCMCSlowPath(size, tls);
    } else {
        *reinterpret_cast<uintptr_t*>(regionAddr + common::REGION_DESC_ALLOC_OFF) = endOfAlloc;
    }
    return reinterpret_cast<uint8_t*>(allocPtr);
}

// Merge the typeInfo pointer with KOTLIN language tag in one 64-bit value so a single
// store makes the new object simultaneously typed AND tagged for the CRT GC. Doing
// these as two separate stores (write typeInfo, then SetLanguageBitAsKotlin()) opens
// a race: between the stores the object's BaseStateWord reads `language=DYNAMIC`,
// and any concurrent GC inspecting this object (e.g. via the finalizer queue during
// `DoResurrection`) dispatches through the unregistered DYNAMIC operator slot and
// crashes. The bits 60..61 of `typeInfoOrMeta_` overlay `BaseStateWord::language_`.
static constexpr uintptr_t kKotlinLangBits =
    static_cast<uintptr_t>(common::LanguageType::KOTLIN) << 60;

ALWAYS_INLINE ObjHeader* CRTAllocator::CreateObject(const TypeInfo* typeInfo) noexcept {
    RuntimeAssert(!typeInfo->IsArray(), "Must not be an array");
    auto descriptor = CRTHeapObject::descriptorFrom(typeInfo);
    auto& heapObject = *descriptor.construct(AllocFromCMC(descriptor.size()));
    auto* object = heapObject.object();
    object->typeInfoOrMeta_ = reinterpret_cast<TypeInfo*>(
        reinterpret_cast<uintptr_t>(typeInfo) | kKotlinLangBits);
    auto* kobj = reinterpret_cast<common::KNBaseObject*>(object);
    if (typeInfo->flags_ & TF_HAS_FINALIZER) {
        common::BaseFinalizerProcessor::RegisterFinalizableObject(kobj);
    }
    #ifdef KONAN_OHOS
    if (OH_GetSdkApiVersion() >= OHOS_RESTRACE_MIN_API) {
        restrace(RES_KMP_HEAP_MASK, (void*)object, object->typeInfoOrMeta_->instanceSize_, TAG_RES_KMP_HEAP_MASK, true);
    }
    #endif
    return object;
}

ALWAYS_INLINE ArrayHeader* CRTAllocator::CreateArray(const TypeInfo* typeInfo, uint32_t count) noexcept {
    RuntimeAssert(typeInfo->IsArray(), "Must be an array");
    auto descriptor = CRTHeapArray::descriptorFrom(typeInfo, count);
    uint8_t* memory = AllocFromCMC(descriptor.size());
    std::memset(memory, 0, descriptor.size());
    auto& heapArray = *descriptor.construct(memory);
    ArrayHeader* array = heapArray.array();
    array->typeInfoOrMeta_ = reinterpret_cast<TypeInfo*>(
        reinterpret_cast<uintptr_t>(typeInfo) | kKotlinLangBits);
    array->count_ = count;
    #ifdef KONAN_OHOS
    if (OH_GetSdkApiVersion() >= OHOS_RESTRACE_MIN_API) {
        restrace(RES_KMP_HEAP_MASK, (void*)array, array->typeInfoOrMeta_->instanceSize_, TAG_RES_KMP_HEAP_MASK, true);
    }
    #endif
    return array;
}

mm::ExtraObjectData* CRTAllocator::CreateExtraObjectDataForObject(ObjHeader* object, const TypeInfo* info) noexcept {
    // Ported from mpcore/crt_fp_unwind 7e581cd. The AllocateExtra call below can
    // trigger STW. Record this frame so the GC walker can unwind through it
    // back to the Kotlin caller.
    RuntimeSetLastFrame1();
    constexpr auto size = sizeof(mm::ExtraObjectData);
    static_assert(size % sizeof(uint64_t) == 0, "non-movable allocator requirement failed");
    // Use `AllocateExtra` (EXTRA_OBJECT region). The GC's marking / resurrection
    // paths special-case EXTRA_OBJECT regions: instead of calling `obj->GetSize()`
    // (which dispatches through `GetOperator()` and crashes on `language=DYNAMIC`),
    // they use the region-level fixed `GetMonoSizeRegionObjectSize()`. Pairing this
    // with `AllocateInNonmove` (NONMOVABLE_OBJECT region) caused DeepRecursiveTest's
    // SIGSEGV because the CRT-mode `ExtraObjectData` constructor's raw write to
    // `typeInfo_` (offset 0) clobbered the KOTLIN language bits that
    // `AllocateInNonmove`'s `SetLanguageType` had just placed; in EXTRA_OBJECT
    // region the GC skips that dispatch entirely.
    //
    // The allocation may step on a safe-point and the GC may move `object`. Publish
    // it as a GC root via ObjHolder, then refresh from the holder after the alloc.
    // Pass the refreshed `object` to the ExtraObjectData constructor so callers can
    // recover the live pointer via `weakReferenceOrBaseObject_`.
    // Mirrors mpcore/crt_fp_unwind's CRTAllocator::CreateExtraObjectDataForObject.
    ObjHolder holder{object};
    auto extraObjectMemory = reinterpret_cast<void*>(common::HeapAllocator::AllocateExtra(size, common::LanguageType::KOTLIN));
    object = holder.obj();
    auto* extraObject = new (extraObjectMemory) mm::ExtraObjectData(object, info);
    #ifdef KONAN_OHOS
    if (OH_GetSdkApiVersion() >= OHOS_RESTRACE_MIN_API) {
        restrace(RES_KMP_HEAP_MASK, (void*)extraObject, size, TAG_RES_KMP_HEAP_MASK, true);
    }
    #endif
    return extraObject;
}

// static
size_t CRTAllocator::GetAllocatedHeapSize(ObjHeader* object) noexcept {
    return CRTHeapObject::from(object).size();
}

} // namespace kotlin::alloc

RUNTIME_NOTHROW extern "C" KInt Kotlin_CRT_GetOrSetHashCode(ObjHeader* thiz)
{
    assertUseCRT();

    // Only object (i.e., non-primitive) can be hashed. Therefore if thiz does not belong to heap
    // it must be (when there is no Escape-analysis) a compiler-generated cached boxing value, which reside
    // in the text section of the program, which is not editable or movable.
    // We simply return the address as hash value in this case.
    // TODO: Add a testcase fot this.
    if (!common::Heap::IsHeapAddress(thiz)) {
        return reinterpret_cast<uintptr_t>(thiz);
    }

    return kotlin::mm::ExtraObjectData::GetOrInstall(thiz).hashCode();
}
