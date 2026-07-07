/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "Weak.hpp"

#include "ExternalRCRef.hpp"
#include "ExtraObjectData.hpp"
#include "ThreadState.hpp"
#include "Types.h"

#include "MemoryManagerSwitch.hpp"
#ifdef ENABLE_CRT
#include "CRTFastpathUtils.hpp"
#include "crt/cpp/KNBaseObject.hpp"
#endif

using namespace kotlin;

extern "C" {
#ifdef ENABLE_CRT
    RUNTIME_NOTHROW OBJ_GETTER(Konan_CRTWeakReferenceImpl_get, ObjHeader* weakRef)
    {
        auto addr = reinterpret_cast<uintptr_t>(weakRef);
        auto field = reinterpret_cast<ObjHeader**>(addr + sizeof(ObjHeader));
        auto ref = reinterpret_cast<uintptr_t>(ReadHeapRef(field, weakRef));
        RETURN_OBJ(reinterpret_cast<ObjHeader*>(ref & common::WEAK_REF_TAGS_MASK));
    }
    OBJ_GETTER0(makeCRTWeakReferenceImpl);
#endif

    OBJ_GETTER(makeRegularWeakReferenceImpl, KRef, void*);
}

namespace {

#ifdef ENABLE_CRT
static_assert(sizeof(ObjHeader*) == sizeof(KLong));
RUNTIME_NOTHROW ALWAYS_INLINE void initCRTWeakReferenceImpl(ObjHeader* weakRef, ObjHeader* referred)
{
    auto addr = reinterpret_cast<uintptr_t>(weakRef);
    auto field = reinterpret_cast<KLong*>(addr + sizeof(ObjHeader));
    // The raw tagged store bypasses RefAccessor<Heap>, so emit the write barrier
    // explicitly; otherwise SATB and remembered-set updates are missed.
    common::BaseRuntime::WriteBarrier(weakRef, field, referred, common::GetMutatorOrNull());
    *field = reinterpret_cast<KLong>(referred) | common::REF_FIELD_TAG_WEAK;
    reinterpret_cast<common::KNBaseObject*>(weakRef)->SetWeakRefImplObjectFlag(true);
}
#endif

struct RegularWeakReferenceImpl {
    ObjHeader header;
    mm::RawExternalRCRef* weakRef;
    void* referred;
};

RegularWeakReferenceImpl* asRegularWeakReferenceImpl(ObjHeader* weakRef) noexcept {
    return reinterpret_cast<RegularWeakReferenceImpl*>(weakRef);
}

} // namespace

OBJ_GETTER(mm::createRegularWeakReferenceImpl, ObjHeader* object) noexcept {
#ifdef ENABLE_CRT
    return checkUseCRT<CheckMode::Fast>([&] {
        auto holder = ObjHolder(object);
        auto* weakRef = makeCRTWeakReferenceImpl(OBJ_RESULT);
        initCRTWeakReferenceImpl(weakRef, holder.obj());
        return weakRef;
    }, [&] {
        auto* thread = mm::ThreadRegistry::Instance().CurrentThreadData();
        AssertThreadState(thread, ThreadState::kRunnable);

        auto& extraObject = mm::ExtraObjectData::GetOrInstall(object);
        if (auto* weakRef = extraObject.GetRegularWeakReferenceImpl()) {
            RETURN_OBJ(weakRef);
        }
        ObjHolder holder;
        auto* weakRef = makeRegularWeakReferenceImpl(object, object, holder.slot());
        auto* setWeakRef = extraObject.GetOrSetRegularWeakReferenceImpl(object, weakRef);
        RETURN_OBJ(setWeakRef);
    });
#else
    auto* thread = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(thread, ThreadState::kRunnable);

    auto& extraObject = mm::ExtraObjectData::GetOrInstall(object);
    if (auto* weakRef = extraObject.GetRegularWeakReferenceImpl()) {
        RETURN_OBJ(weakRef);
    }
    ObjHolder holder;
    auto* weakRef = makeRegularWeakReferenceImpl(object, object, holder.slot());
    auto* setWeakRef = extraObject.GetOrSetRegularWeakReferenceImpl(object, weakRef);
    RETURN_OBJ(setWeakRef);
#endif
}

void mm::disposeRegularWeakReferenceImpl(ObjHeader* weakRef) noexcept {
    mm::disposeExternalRCRef(asRegularWeakReferenceImpl(weakRef)->weakRef);
}

ObjHeader* mm::regularWeakReferenceImplBaseObjectUnsafe(ObjHeader* weakRef) noexcept {
    return static_cast<ObjHeader*>(asRegularWeakReferenceImpl(weakRef)->referred);
}
