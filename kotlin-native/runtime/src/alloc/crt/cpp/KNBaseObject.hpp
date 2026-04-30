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

#include "common_interfaces/objects/base_object.h"
#include "common_interfaces/objects/base_state_word.h"
#include "common_interfaces/objects/base_finalization.h"

#include "Allocator.hpp"
#include "ObjectTraversal.hpp"
#include "gc/crt/cpp/CRTRuntime.hpp"
#include "HeapInterface.hpp"

namespace common {

class KNStateWord {
public:
    struct GCStateWord {
        common::StateWordType address_ : 58;
        common::StateWordType isWeakImpl_ : 1;
        common::StateWordType reserved_ : 1;
        common::StateWordType remainded_ : 4;
    };

    void SetForwardingPointerAfterExclusive(uintptr_t fwdPtr) { state_.address_ = fwdPtr; }

    uintptr_t GetForwardingPointerAfterExclusive() const { return state_.address_; }

    void SetWeakRefImplObjectFlag(bool v) { state_.isWeakImpl_ = v; }

    bool IsWeakRefImplObject() const { return state_.isWeakImpl_; }

private:
    union {
        GCStateWord state_;
        MAddress header_;
    };
};

class KNBaseObject : public BaseObject {
public:
    void SetForwardingPointerAfterExclusive(BaseObject* fwdPtr)
    {
        reinterpret_cast<KNStateWord*>(this)->SetForwardingPointerAfterExclusive(reinterpret_cast<uintptr_t>(fwdPtr));
    }
    BaseObject* GetForwardingPointerAfterExclusive() const
    {
        return reinterpret_cast<BaseObject*>(reinterpret_cast<const KNStateWord*>(this)->GetForwardingPointerAfterExclusive());
    }
    bool IsValid() const { return isValidKotlinObject(reinterpret_cast<const ObjHeader*>(this)); }
    void SetLanguageBitAsKotlin() { SetLanguageType(common::LanguageType::KOTLIN); }
    void SetWeakRefImplObjectFlag(bool valid) { reinterpret_cast<KNStateWord*>(this)->SetWeakRefImplObjectFlag(valid); }
    bool IsWeakRefImplObject() const { return reinterpret_cast<const KNStateWord*>(this)->IsWeakRefImplObject(); }

private:
    // This is used for distinguish potential Kotlin reference during root scanning on stack and registers
    static bool isValidKotlinObject(const ObjHeader* obj) noexcept
    {
        const uintptr_t addr = reinterpret_cast<uintptr_t>(obj);
        // We assume only base pointer here, so it must be 8-bytes aligned.
        // Dervived pointers are collected elsewhere
        if (!common::IsHeapAddress(addr) || (addr & 7ul) != 0) {
            return false;
        }
        auto commonObj = reinterpret_cast<const common::BaseObject*>(obj);
        // In case typeinfo is forwarded awawy
        if (commonObj->IsForwarded()) {
            return isValidKotlinObject(reinterpret_cast<ObjHeader*>(commonObj->GetForwardingPointer()));
        }
        auto typeInfo = *reinterpret_cast<const uintptr_t*>(obj) & kImmTypeInfoMask;
        if (typeInfo > kotlin::KEXE_ADDR_START_ && typeInfo < kotlin::KEXE_ADDR_END_) {
            return *reinterpret_cast<uintptr_t*>(typeInfo) == typeInfo;
        }
        return false;
    }
};

// helper function for KNBaseObjectOperator::ForEachRefField
static inline void processFieldInMark(const RefFieldVisitor& visitor, ObjHeader* object, ObjHeader*& field) noexcept
{
    if (common::IsHeapAddress(field)) {
        visitor(reinterpret_cast<common::RefField<>&>(field));
    }
}

class KNBaseObjectOperator : public BaseObjectOperatorInterfaces, private kotlin::Pinned {
public:
    virtual ~KNBaseObjectOperator() = default;

    KNBaseObjectOperator() {};

    // Get Object allocated size
    size_t GetSize(const BaseObject* object) const override
    {
        return kotlin::alloc::allocatedHeapSize(const_cast<ObjHeader*>(reinterpret_cast<const ObjHeader*>(object)));
    }

    bool IsValidObject(const BaseObject* object) const override
    {
        return reinterpret_cast<const KNBaseObject*>(object)->IsValid();
    }

    void ForEachRefField(const BaseObject* object, const RefFieldVisitor& visitor) const override
    {
        auto* objHeader = const_cast<ObjHeader*>(reinterpret_cast<const ObjHeader*>(object));

        if (reinterpret_cast<common::KNBaseObject*>(objHeader)->IsWeakRefImplObject()) {
            uintptr_t addr = reinterpret_cast<uintptr_t>(objHeader);
            ObjHeader** field = reinterpret_cast<ObjHeader**>(addr + sizeof(ObjHeader));
            // process weak ref ...
            processFieldInMark(visitor, objHeader, *field);
        } else {
            kotlin::traverseObjectFields(objHeader, [&](auto elemAccessor) noexcept {
                if (ObjHeader** elem = elemAccessor.direct().location()) {
                    if (*elem) {
                        processFieldInMark(visitor, objHeader, *elem);
                    }
                }
            });
        }
    }

    BaseObject* GetForwardingPointer(const BaseObject* object) const override
    {
        return reinterpret_cast<const KNBaseObject*>(object)->GetForwardingPointerAfterExclusive();
    }

    void SetForwardingPointerAfterExclusive(BaseObject* object, BaseObject* fwdPtr) override
    {
        reinterpret_cast<KNBaseObject*>(object)->SetForwardingPointerAfterExclusive(fwdPtr);
    }

    // Iterate object field, and skit the weak referent, ONLY used in interop.
    void ForEachRefFieldSkipReferent(const BaseObject* object, const RefFieldVisitor& visitor) const override {}

    // Does not used by Kotlin-Native
    void IterateXRef(const BaseObject* object, const RefFieldVisitor& visitor) const override {}

    // For extra object data
    void ForEachRefFieldInExtraObject(BaseObject* extraObj, size_t size, const RefFieldVisitor& visitor) const override {}

    // Global instance for CRT to operate on KN objects
    // Needs to be registered when runtime init
    static KNBaseObjectOperator& Instance()
    {
        static KNBaseObjectOperator instance;
        return instance;
    }
};

}; // namespace common
