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

#ifndef RUNTIME_KHANDLE_H
#define RUNTIME_KHANDLE_H

#include "Types.h"
#include "HandleScope.h"

struct MemoryState;

namespace kotlin::mm {
class ThreadData;
} // namespace kotlin::mm

// A Handle provides a indirect reference to an managed object, which ensure it
// survives relocation cross safepoint.
//
// Handles are only valid within a HandleScope.
template <typename T>
class KHandle {
public:
    // Allocate a new handle for the object. attaching to threadLocal
    explicit KHandle(T objRef) : KHandle(objRef, kotlin::mm::GetMemoryState()) {}
    // Allocate a new handle, but attach to given threadlocal
    KHandle(T objRef, kotlin::mm::ThreadData& threadData)
        : KHandle(objRef, HandleScope::CreateHandleSlot(threadData)) {}
    KHandle(T objRef, MemoryState* thread) : KHandle(objRef, HandleScope::CreateHandleSlot(thread)) {}
    explicit KHandle(T objRef, HandleScope& scope) : KHandle(objRef, scope.CreateHandleSlot()) {}
    // Allocate a new handle at given slot
    KHandle(T objRef, HandleSlot* slot) : slot_(slot)
    {
        static_assert(sizeof(T) == sizeof(HandleSlot), "Incompatible type size");
        *slot_ = reinterpret_cast<HandleSlot>(objRef);
    }

    // interfaces for creating empty handler
    static KHandle<T> null() { return KHandle<T>(nullptr); }
    explicit KHandle() : KHandle(nullptr) {}
    explicit KHandle(HandleScope& scope) : KHandle(nullptr, scope.CreateHandleSlot()) {}

    ~KHandle<T>() = default;

    T operator->() const { return *reinterpret_cast<T*>(slot_); }

    T get() const { return *reinterpret_cast<T*>(slot_); }

    // ---- Following is the API to adapt the usage of ObjHolder and OBJ_GETTER paradigm

    // Returns the underlying slot location, adapt the usage of KN's objHolder and OBJ_GETTER
    ObjHeader** slot()
    {
        return reinterpret_cast<ObjHeader**>(slot_);
    }

    ObjHeader* obj() { return this->get(); }
    const ObjHeader* obj() const { return this->get(); }
    void clear() { slot_ = nullptr; }
    // ---- End

private:
    HandleSlot* slot_;
};

#endif // RUNTIME_KHANDLE_H
