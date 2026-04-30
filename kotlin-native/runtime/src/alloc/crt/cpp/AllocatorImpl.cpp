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

#include "AllocatorImpl.hpp"
#include "gc/crt/cpp/CRTUtils.hpp"

namespace kotlin {
// TODO: Get the cache TLS from ThreadData, should be renamed
void* EvalCRTTLS(alloc::Allocator::ThreadData::Impl& impl)
{
    return impl.alloc().getCrtTls();
}
} // namespace kotlin

using namespace kotlin;

alloc::Allocator::ThreadData::ThreadData(Allocator& allocator) noexcept
    : impl_(std::make_unique<Impl>(allocator.impl())) {}

alloc::Allocator::ThreadData::~ThreadData() = default;

ALWAYS_INLINE ObjHeader* alloc::Allocator::ThreadData::allocateObject(const TypeInfo* typeInfo) noexcept
{
    return impl_->alloc().CreateObject(typeInfo);
}

ALWAYS_INLINE ArrayHeader* alloc::Allocator::ThreadData::allocateArray(const TypeInfo* typeInfo,
    uint32_t elements) noexcept
    {
        return impl_->alloc().CreateArray(typeInfo, elements);
    }

ALWAYS_INLINE mm::ExtraObjectData& alloc::Allocator::ThreadData::allocateExtraObjectData(
    ObjHeader* object, const TypeInfo* typeInfo) noexcept
    {
        NOT_SUPPORTED_BY_CRT();
    }

ALWAYS_INLINE void alloc::Allocator::ThreadData::destroyUnattachedExtraObjectData(
    mm::ExtraObjectData& extraObject) noexcept
    {
        NOT_SUPPORTED_BY_CRT();
    }

void alloc::Allocator::ThreadData::prepareForGC() noexcept {}

void alloc::Allocator::ThreadData::clearForTests() noexcept {}

alloc::Allocator::Allocator() noexcept : impl_(std::make_unique<Impl>()) {}

alloc::Allocator::~Allocator() = default;

void alloc::Allocator::prepareForGC() noexcept {}

void alloc::Allocator::clearForTests() noexcept {}

void alloc::initObjectPool() noexcept {}

void alloc::compactObjectPoolInCurrentThread() noexcept {}

gc::GC::ObjectData& alloc::objectDataForObject(ObjHeader* object) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

ObjHeader* alloc::objectForObjectData(gc::GC::ObjectData& objectData) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

size_t alloc::allocatedHeapSize(ObjHeader* object) noexcept
{
    return CRTAllocator::GetAllocatedHeapSize(object);
}

size_t alloc::allocatedBytes() noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

void alloc::destroyExtraObjectData(mm::ExtraObjectData& extraObject) noexcept
{
    NOT_SUPPORTED_BY_CRT();
}

void alloc::Allocator::startFinalizerThreadIfNeeded() noexcept {}

void alloc::Allocator::stopFinalizerThreadIfRunning() noexcept {}

bool alloc::Allocator::finalizersThreadIsRunning() noexcept { return false; }
