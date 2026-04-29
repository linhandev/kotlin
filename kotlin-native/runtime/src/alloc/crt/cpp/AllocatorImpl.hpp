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

#pragma once

#include "Allocator.hpp"

#include "CRTAllocator.hpp"

namespace kotlin::alloc {

class Allocator::Impl : private Pinned {
public:
    Impl() noexcept = default;

private:
};

class Allocator::ThreadData::Impl : private Pinned {
public:
    explicit Impl(Allocator::Impl& allocator) noexcept : alloc_() {}

    alloc::CRTAllocator& alloc() noexcept { return alloc_; }

private:
    CRTAllocator alloc_;
};

} // namespace kotlin::alloc
