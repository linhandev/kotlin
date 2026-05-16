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

#ifndef CRT_ALLOC_CPP_ALLOCATOR_HPP_
#define CRT_ALLOC_CPP_ALLOCATOR_HPP_

#include <cstring>

#include "Memory.h"
#include "GC.hpp"
#include "HeapObject.hpp"

namespace kotlin::alloc {

using CRTHeapObject = HeapObject<gc::GC::ObjectData>;
using CRTHeapArray = HeapArray<gc::GC::ObjectData>;

class CRTAllocator {
public:
    explicit CRTAllocator() noexcept;

    ~CRTAllocator();

    ObjHeader* CreateObject(const TypeInfo* typeInfo) noexcept;

    ArrayHeader* CreateArray(const TypeInfo* typeInfo, uint32_t count) noexcept;

    mm::ExtraObjectData* CreateExtraObjectDataForObject(ObjHeader* object, const TypeInfo* info) noexcept;

    static size_t GetAllocatedHeapSize(ObjHeader* object) noexcept;

    // Cache CRT TLS address so that we don't have to call GetThreadLocalData every time.
    void* getCrtTls() noexcept { return crtTLS; }

private:
    ALWAYS_INLINE uint8_t* AllocFromCMC(size_t size);

    void* crtTLS;
};

} // namespace kotlin::alloc

#endif
