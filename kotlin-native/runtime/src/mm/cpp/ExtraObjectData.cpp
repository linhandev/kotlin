/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "ExtraObjectData.hpp"

#include "PointerBits.h"
#include "ThreadData.hpp"

#ifdef KONAN_OBJC_INTEROP
#include "ObjCMMAPI.h"
#endif

#ifdef KONAN_OHOS
#include "ArkTSMMAPI.h"
#endif
#include "MemoryManagerSwitch.hpp"
#include "crt/cpp/KNFinalizer.hpp"

using namespace kotlin;

// static
mm::ExtraObjectData& mm::ExtraObjectData::Install(ObjHeader* object) noexcept {
    // TODO: Consider extracting initialization scheme with speculative load.
    // `object->typeInfoOrMeta_` is assigned at most once. If we read some old value (i.e. not a meta object),
    // we will fail at CAS below. If we read the new value, we will immediately return it.
    auto* curHeader = object->typeInfoOrMetaAcquire();

    if (auto* metaObject = ObjHeader::AsMetaObject(curHeader)) {
        // ExtraObject already installed, just return it.
        return mm::ExtraObjectData::FromMetaObjHeader(metaObject);
    }

    // Try to allocate and install a new ExtraObject.
    auto* cleanTypeInfo = clearPointerBits(curHeader, OBJECT_TAG_MASK);
    auto& allocator = mm::ThreadRegistry::Instance().CurrentThreadData()->allocator();
    auto& extraObj = allocator.allocateExtraObjectData(object, cleanTypeInfo);

    // Refresh the pointer to `object` after possible safe-point during allocation.
    // CreateExtraObjectDataForObject's ObjHolder published `object` as a GC root,
    // refreshed it after the alloc, and passed the refreshed pointer to the
    // ExtraObjectData constructor, which stored it in `weakReferenceOrBaseObject_`.
    object = extraObj.weakReferenceOrBaseObject_.load(std::memory_order_relaxed);

    // Ensure that `object` header would still have the same `tags` (low Kotlin tag bits
    // and high CRT BaseStateWord bits, e.g. language_/forwardState_).
    const auto tags = getPointerBits(curHeader, OBJECT_TAG_MASK);
    auto* newHeader = setPointerBits(reinterpret_cast<TypeInfo*>(&extraObj), tags);

    std_support::atomic_ref objectAtomicTypeInfo{object->typeInfoOrMeta_};
    if (!objectAtomicTypeInfo.compare_exchange_strong(curHeader, newHeader)) {
        // CAS failure can only mean that somebody else created `mm::ExtraObjectData` for this object.
        allocator.destroyUnattachedExtraObjectData(extraObj);
        auto* anotherExtraObj = clearPointerBits(curHeader, OBJECT_TAG_MASK);
        return *reinterpret_cast<mm::ExtraObjectData*>(anotherExtraObj);
    }

    // We have successfully installed the `extraObj`.
    checkUseCRT<CheckMode::Fast>([&] {
        // Object with an ExtraObj installed has to be finalized
        // to ensure that possible associated object is released and original `object` header is restored.
        if (!(cleanTypeInfo->flags_ & TF_HAS_FINALIZER)) { // avoid registering finalizable objects twice
            common::BaseFinalizerProcessor::RegisterFinalizableObject(reinterpret_cast<common::BaseObject*>(object));
        }
    });

    return extraObj;
}

void mm::ExtraObjectData::UnlinkFromBaseObject() noexcept {
    assertNotCRT();
    auto* object = weakReferenceOrBaseObject_.exchange(nullptr);
    RuntimeAssert(
            !hasPointerBits(object, WEAK_REF_TAG), "ExtraObjectData %p has uncleared weak reference %p during unlink", this,
            clearPointerBits(object, WEAK_REF_TAG));
    std_support::atomic_ref{object->typeInfoOrMeta_}.store(const_cast<TypeInfo*>(typeInfo_), std::memory_order_release);
    RuntimeAssert(
            !object->has_meta_object(), "Object %p has metaobject %p after removing metaobject %p", object, object->meta_object_or_null(),
            this);
}

void mm::ExtraObjectData::ReleaseAssociatedObject() noexcept {
#ifdef KONAN_OHOS
    if (void* associatedObject = associatedObject_) {
        Kotlin_ArkTS_releaseAssociatedObject(associatedObject);
        associatedObject_ = nullptr;
    }
#endif
#ifdef KONAN_OBJC_INTEROP
    if (void* associatedObject = associatedObject_) {
        Kotlin_ObjCExport_releaseAssociatedObject(associatedObject);
        associatedObject_ = nullptr;
    }
#endif
}

void mm::ExtraObjectData::Uninstall() noexcept {
    assertNotCRT();
    UnlinkFromBaseObject();
    ReleaseAssociatedObject();
}

bool mm::ExtraObjectData::HasAssociatedObject() noexcept {
#if defined(KONAN_OBJC_INTEROP) || defined(KONAN_OHOS)
    return associatedObject_ != nullptr;
#else
    return false;
#endif
}

void mm::ExtraObjectData::ClearRegularWeakReferenceImpl() noexcept {
    assertNotCRT();
    auto *object = GetBaseObject();
    // Not using `mm::SetHeapRef here`, because this code is called during sweep phase by the GC thread,
    // and so cannot affect marking.
    // TODO: Asserts on the above?
    weakReferenceOrBaseObject_ = object;
}

mm::ExtraObjectData::~ExtraObjectData() {
    auto* weakReference = weakReferenceOrBaseObject_.load(std::memory_order_relaxed);
    if (hasPointerBits(weakReference, WEAK_REF_TAG)) {
        weakReference = clearPointerBits(weakReference, WEAK_REF_TAG);
    } else {
        weakReference = nullptr;
    }
    RuntimeAssert(weakReference == nullptr, "ExtraObjectData %p must have cleared weak reference %p", this, weakReference);

#if defined(KONAN_OBJC_INTEROP) || defined(KONAN_OHOS)
    auto* associatedObject = associatedObject_.load(std::memory_order_relaxed);
    RuntimeAssert(associatedObject == nullptr, "ExtraObjectData %p must have cleared associated object %p", this, associatedObject);
#endif
}
