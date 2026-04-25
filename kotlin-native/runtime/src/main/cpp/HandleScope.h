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

#ifndef RUNTIME_HANDLE_SCOPE_H
#define RUNTIME_HANDLE_SCOPE_H

#include "Utils.hpp"
#include "HandleStorage.h"

struct MemoryState;

namespace kotlin::mm {
class ThreadData;
}

// A per-ThreadData unit recording the information of the
// latest HandleScope information of the current thread.
class HandleScopeData {
public:
    HandleScopeData() {}

    template <typename Callback>
    void IterateHandles(Callback callback)
    {
        storage_.IterateHandles(callback, nextSlot_, limit_);
    }

    using Iterator = HandleStorage::Iterator;

    Iterator begin() { return Iterator(storage_, nextSlot_); }

    Iterator end() { return Iterator(storage_, nextSlot_, nullptr); }

    int GetLevel()
    {
        return level;
    }

private:
    friend class HandleScope;
    friend class HandleScopeDataTestSupport;

    HandleSlot* NextHandleSlot()
    {
        if (nextSlot_ == limit_) {
            auto [start, end] = storage_.ExtendBlock();
            nextSlot_ = start;
            limit_ = end;
            return NextHandleSlot();
        }
        return nextSlot_++;
    }

    void PopScope(HandleSlot* prevLimit, HandleSlot* prevNextSlot)
    {
        level -= 1;
        RuntimeAssert(level >= 0, "HandleScope underflow");
        limit_ = prevLimit;
        nextSlot_ = prevNextSlot;
        storage_.ClearUnusedBlocks(limit_);
    }

    HandleStorage storage_;
    HandleSlot* limit_ = nullptr;
    HandleSlot* nextSlot_ = nullptr;
    int level = 0;
};

class HandleScope : private kotlin::Pinned {
public:
    explicit HandleScope(MemoryState* thread);
    explicit HandleScope(kotlin::mm::ThreadData& threadData);
    explicit HandleScope();
    ~HandleScope();

    HandleSlot* CreateHandleSlot();

    template<typename T>
    KHandle<T> CreateKHandle(T obj)
    {
        return KHandle(obj, CreateHandleSlot());
    }

    template<typename T>
    KHandle<T> CreateKHandle()
    {
        auto slot = CreateHandleSlot();
        return KHandle<T>(nullptr, slot);
    }

    static HandleSlot* CreateHandleSlot(MemoryState* thread);
    static HandleSlot* CreateHandleSlot(kotlin::mm::ThreadData& threadData);

private:
    friend class HandleScopeTestSupport;
    HandleScopeData& data_;
    // Record the status of previous scope and restore when the current scope is poped.
    HandleSlot* prevLimit_;
    HandleSlot* prevNextSlot_;
};


// ---- Test Support Class ----
class HandleScopeDataTestSupport : public HandleScopeData {
public:
    using HandleScopeData::level;
    using HandleScopeData::nextSlot_;
    using HandleScopeData::limit_;
    using HandleScopeData::storage_;

    using HandleScopeData::NextHandleSlot;
    using HandleScopeData::PopScope;
};

#endif // RUNTIME_HANDLE_SCOPE_H
