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

#include "HandleScope.h"
#include "MemoryPrivate.hpp"

HandleScope::HandleScope(MemoryState* thread)
    : data_(thread->GetThreadData()->GetHandleScopeData())
{
    data_.level += 1;
    prevLimit_ = data_.limit_;
    prevNextSlot_ = data_.nextSlot_;
}

HandleScope::HandleScope(kotlin::mm::ThreadData& threadData)
    : data_(threadData.GetHandleScopeData())
{
    data_.level += 1;
    prevLimit_ = data_.limit_;
    prevNextSlot_ = data_.nextSlot_;
}

HandleScope::HandleScope() : HandleScope(mm::GetMemoryState()) {}

HandleScope::~HandleScope() {
    data_.PopScope(prevLimit_, prevNextSlot_);
}

HandleSlot* HandleScope::CreateHandleSlot() {
    return data_.NextHandleSlot();
}

HandleSlot* HandleScope::CreateHandleSlot(MemoryState* thread) {
    auto& data = thread->GetThreadData()->GetHandleScopeData();
    return data.NextHandleSlot();
}

HandleSlot* HandleScope::CreateHandleSlot(mm::ThreadData& threadData) {
    auto& data = threadData.GetHandleScopeData();
    return data.NextHandleSlot();
}
