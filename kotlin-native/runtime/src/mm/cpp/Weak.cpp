/*
 * Copyright 2010-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "Weak.hpp"

#include "ExternalRCRef.hpp"
#include "ExtraObjectData.hpp"
#include "ThreadState.hpp"
#include "Types.h"

#ifdef USE_CRT
#include "alloc/crt/cpp/KNBaseObject.hpp"
#endif

#ifdef USE_CRT
static_assert(sizeof(ObjHeader*) == sizeof(KLong));
RUNTIME_NOTHROW extern "C" void Konan_initWeakReferenceCRTImpl(ObjHeader* weakRef, ObjHeader* referred) {
    uintptr_t addr = reinterpret_cast<uintptr_t>(weakRef);
    KLong* field = reinterpret_cast<KLong*>(addr + sizeof(ObjHeader));
    *field = reinterpret_cast<KLong>(referred) | common::WEAK_REF_TAG;
    reinterpret_cast<common::KNBaseObject*>(weakRef)->SetWeakRefImplObjectFlag(true);
}

RUNTIME_NOTHROW extern "C" OBJ_GETTER(Konan_derefWeakReferenceCRTImpl, ObjHeader* weakRef) {
    uintptr_t addr = reinterpret_cast<uintptr_t>(weakRef);
    ObjHeader** field = reinterpret_cast<ObjHeader**>(addr + sizeof(ObjHeader));
    addr = reinterpret_cast<uintptr_t>(ReadHeapRef(field, weakRef));
    RETURN_OBJ(reinterpret_cast<ObjHeader*>(addr & ~0b111));
}
#endif

using namespace kotlin;

extern "C" {
OBJ_GETTER(makeRegularWeakReferenceImpl, KRef, void*);
}

namespace {

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
#ifdef USE_CRT
    assert(false && "WeakRef creation is hijacked in CRT, should not be called");
#endif
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
}

void mm::disposeRegularWeakReferenceImpl(ObjHeader* weakRef) noexcept {
    mm::disposeExternalRCRef(asRegularWeakReferenceImpl(weakRef)->weakRef);
}

ObjHeader* mm::regularWeakReferenceImplBaseObjectUnsafe(ObjHeader* weakRef) noexcept {
    return static_cast<ObjHeader*>(asRegularWeakReferenceImpl(weakRef)->referred);
}
