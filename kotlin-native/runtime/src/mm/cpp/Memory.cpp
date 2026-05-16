/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "Memory.h"
#include "Common.h"
#include "MemoryPrivate.hpp"

#include <stack>

#include "Allocator.hpp"
#include "CallsChecker.hpp"
#include "Exceptions.h"
#include "ExternalRCRef.hpp"
#include "ExtraObjectData.hpp"
#include "GC.hpp"
#include "GlobalsRegistry.hpp"
#include "KAssert.h"
#include "Natives.h"
#include "ObjectOps.hpp"
#include "Porting.h"
#include "ReferenceOps.hpp"
#include "Runtime.h"
#include "SafePoint.hpp"
#include "ThreadData.hpp"
#include "ThreadRegistry.hpp"
#include "ThreadState.hpp"

#include "MemoryManagerSwitch.hpp"
#include "CRTFastpathUtils.hpp"
#include "common_interfaces/base_runtime.h"
#include "common_interfaces/thread/mutator_base.h"

#include "MemoryDump.hpp"
#include "StackTrace.hpp"
#include "crt/cpp/CRTRuntime.hpp"

using namespace kotlin;

#if defined(KONAN_OBJC_INTEROP) || defined(KONAN_OHOS)

PERFORMANCE_INLINE void* ObjHeader::GetAssociatedObject() const {
    auto metaObject = meta_object_or_null();
    if (metaObject == nullptr) {
        return nullptr;
    }
    return mm::ExtraObjectData::FromMetaObjHeader(metaObject).AssociatedObject().load(std::memory_order_acquire);
}

PERFORMANCE_INLINE void ObjHeader::SetAssociatedObject(void* obj) {
    auto& extraObject = mm::ExtraObjectData::FromMetaObjHeader(meta_object());
#ifdef KONAN_OBJC_INTEROP
    // TODO: Consider additional filtering based on types:
    //       * have some kind of an allowlist that can be populated by the user
    //         to specify that objects of these types must be finalized only on
    //         the main thread.
    //       * prepopulate it for the system frameworks.
    //       * if that were to be done at runtime, library authors could register
    //         their types in a library initialization code.
    if (pthread_main_np() == 1) {
        extraObject.setFlag(mm::ExtraObjectData::FLAGS_RELEASE_ON_MAIN_QUEUE);
    }
#endif
    return extraObject.AssociatedObject().store(obj, std::memory_order_release);
}

PERFORMANCE_INLINE void* ObjHeader::CasAssociatedObject(void* expectedObj, void* obj) {
    auto& extraObject = mm::ExtraObjectData::FromMetaObjHeader(meta_object());
    bool success = extraObject.AssociatedObject().compare_exchange_strong(expectedObj, obj);
    (void)success;
#ifdef KONAN_OBJC_INTEROP
    // TODO: Consider additional filtering outlined above.
    if (success && pthread_main_np() == 1) {
        extraObject.setFlag(mm::ExtraObjectData::FLAGS_RELEASE_ON_MAIN_QUEUE);
    }
#endif
    return expectedObj;
}

#endif // defined(KONAN_OBJC_INTEROP) || defined(KONAN_OHOS)

// static
MetaObjHeader* ObjHeader::createMetaObject(ObjHeader* object) {
    return mm::ExtraObjectData::Install(object).AsMetaObjHeader();
}

// static
void ObjHeader::destroyMetaObject(ObjHeader* object) {
    RuntimeAssert(object->has_meta_object(), "Object must have a meta object set");
    auto &extraObject = *mm::ExtraObjectData::Get(object);
    alloc::destroyExtraObjectData(extraObject);
}

ALWAYS_INLINE bool isPermanentOrFrozen(const ObjHeader* obj)
{
    return obj->permanent();
}

extern "C" MemoryState* InitMemory() {
    mm::waitGlobalDataInitialized();
    return mm::ToMemoryState(mm::ThreadRegistry::Instance().RegisterCurrentThread());
}

void kotlin::initGlobalMemory() noexcept {
    mm::GlobalData::init();
}

extern "C" void DeinitMemory(MemoryState* state, bool destroyRuntime) {
    // We need the native state to avoid a deadlock on unregistering the thread.
    // The deadlock is possible if we are in the runnable state and the GC already locked
    // the thread registery and waits for threads to suspend or go to the native state.
    AssertThreadState(state, ThreadState::kNative);
    auto* node = mm::FromMemoryState(state);
    checkUseCRT<CheckMode::Slow>([&] {
        // First take lock of MutatorManager for gc, avoid dead lock while gc iterate ThreadRegistry
        // Kotlin::ThreadData is 1-1 corresponding to CRT ThreadHolder
        node->Get()->ClearThreadHolder();
        if (destroyRuntime) {
            // CRT will properly stop its own GC and Finalizer threads upon destruction.
            // No other running threads are expected to exist by this point,
            // so we pass `nullptr` instead of the `state` to avoid stopping the world before destruction.
            DestroyCRTRuntime(nullptr);
        }
    }, [&] {
        if (destroyRuntime) {
            ThreadStateGuard guard(state, ThreadState::kRunnable);
            mm::GlobalData::Instance().gcScheduler().scheduleAndWaitFinalized();
            // TODO: Why not just destruct `GC` object and its thread data counterpart entirely?
            mm::GlobalData::Instance().gc().StopFinalizerThreadIfRunning();
        }
    });
    if (!konan::isOnThreadExitNotSetOrAlreadyStarted()) {
        // we can clear reference in advance, as Unregister function can't use it anyway
        mm::ThreadRegistry::ClearCurrentThreadData();
    }
    mm::ThreadRegistry::Instance().Unregister(node);
}

extern "C" void ClearMemoryForTests(MemoryState* state) {
    state->GetThreadData()->ClearForTests();
}

HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW OBJ_GETTER(AllocInstance, const TypeInfo* typeInfo) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    RETURN_RESULT_OF(mm::AllocateObject, threadData, typeInfo);
}

HAS_SAFEPOINT
extern "C" OBJ_GETTER(AllocArrayInstance, const TypeInfo* typeInfo, int32_t elements) {
    if (elements < 0) {
        ThrowIllegalArgumentException();
    }
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    RETURN_RESULT_OF(mm::AllocateArray, threadData, typeInfo, static_cast<uint32_t>(elements));
}

HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW OBJ_GETTER(AllocInstanceForCI, const TypeInfo* typeInfo) {
    // Trampoline to AllocInstance. CRT codegen emits calls to *ForCI; fp-unwind K2RStub
    // mechanism handles the K2N transition via the HAS_SAFEPOINT annotation, so no FrameGuard needed.
    RETURN_RESULT_OF(AllocInstance, typeInfo);
}

HAS_SAFEPOINT
extern "C" OBJ_GETTER(AllocArrayInstanceForCI, const TypeInfo* typeInfo, int32_t elements) {
    RETURN_RESULT_OF(AllocArrayInstance, typeInfo, elements);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void InitAndRegisterGlobal(ObjHeader** location, const ObjHeader* initialValue) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    mm::GlobalsRegistry::Instance().RegisterStorageForGlobal(threadData, location);
}

template<bool IsVolatile=false>
NO_INLINE RUNTIME_NOTHROW ObjHeader *ReadHeapRefSlow(ObjHeader** location, ObjHeader* thisPtr) {
    if constexpr (IsVolatile) {
        return mm::RefAccessor<false>(location, thisPtr).loadAtomic(std::memory_order_seq_cst);
    } else {
        return mm::RefAccessor<false>(location, thisPtr).load();
    }
}

extern "C" const MemoryModel CurrentMemoryModel = MemoryModel::kExperimental;

HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW ObjHeader *ReadHeapRef(ObjHeader** location, ObjHeader* thisPtr) {
    return checkUseCRT<CheckMode::Fast>([&] {
        // TODO: combine with the fastpath barrier check after PR!22 is merged
#ifdef ENABLE_GC_FASTPATH
        CHECK_READ_BARRIER_SLOW_PATH(rb_slow_path)
        return *location;
#endif // ENABLE_GC_FASTPATH
    rb_slow_path:
        return ReadHeapRefSlow<false>(location, thisPtr);
    }, [&] {
        // v3 CMS path: plain load. fp-unwind state transitions handled by K2RStub annotations.
        return mm::RefAccessor<false>(location, thisPtr).load();
    });
}

HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW ObjHeader* ReadVolatileHeapRef(ObjHeader** location, ObjHeader* thisPtr) {
    return checkUseCRT<CheckMode::Fast>([&] {
        // TODO: combine with the fastpath barrier check after PR!22 is merged
#ifdef ENABLE_GC_FASTPATH
        CHECK_READ_BARRIER_SLOW_PATH(rb_slow_path)
        return std_support::atomic_ref(*location).load(std::memory_order_seq_cst);
#endif // ENABLE_GC_FASTPATH
    rb_slow_path:
        return ReadHeapRefSlow<true>(location, thisPtr);
    }, [&] {
        return mm::RefAccessor<false>(location, thisPtr).loadAtomic(std::memory_order_seq_cst);
    });
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW void ZeroHeapRef(ObjHeader** location, ObjHeader* thisPtr) {
    mm::RefAccessor<false>{location} = nullptr;
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void ZeroArrayRefs(ObjHeader* array_) {
    auto array = array_->array();
    for (uint32_t index = 0; index < array->count_; ++index) {
        ObjHeader** location = ArrayAddressOfElementAt(array, index);
        mm::RefFieldAccessor{location} = nullptr;
    }
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW void ZeroStackRef(ObjHeader** location) {
    mm::StackRefAccessor{location} = nullptr;
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW void UpdateStackRef(ObjHeader** location, const ObjHeader* object) {
    mm::StackRefAccessor{location} = const_cast<ObjHeader*>(object);
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW void UpdateHeapRef(ObjHeader** location, const ObjHeader* object, ObjHeader* thisPtr) {
    mm::RefAccessor<false>(location, thisPtr) = const_cast<ObjHeader*>(object);
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW void UpdateVolatileHeapRef(ObjHeader** location, const ObjHeader* object, ObjHeader* thisPtr) {
    mm::RefAccessor<false>(location, thisPtr).storeAtomic(const_cast<ObjHeader*>(object), std::memory_order_seq_cst);
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW OBJ_GETTER(CompareAndSwapVolatileHeapRef, ObjHeader** location, ObjHeader* expectedValue, ObjHeader* newValue, ObjHeader* thisPtr) {
    ObjHeader* actual = expectedValue;
    mm::RefAccessor<false>(location, thisPtr).compareAndExchange(actual, newValue, std::memory_order_seq_cst);
    RETURN_OBJ(actual);
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW bool CompareAndSetVolatileHeapRef(ObjHeader** location, ObjHeader* expectedValue, ObjHeader* newValue, ObjHeader* thisPtr) {
    return mm::RefAccessor<false>(location, thisPtr).compareAndExchange(expectedValue, newValue, std::memory_order_seq_cst);
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW OBJ_GETTER(GetAndSetVolatileHeapRef, ObjHeader** location, ObjHeader* newValue, ObjHeader* thisPtr) {
    RETURN_OBJ(mm::RefAccessor<false>(location, thisPtr).exchange(newValue, std::memory_order_seq_cst));
}

NO_SAFEPOINT
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW void UpdateReturnRef(ObjHeader** returnSlot, const ObjHeader* object) {
    UpdateStackRef(returnSlot, object);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void EnterFrame(ObjHeader** start, int parameters, int count) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    threadData->shadowStack().EnterFrame(start, parameters, count);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void LeaveFrame(ObjHeader** start, int parameters, int count) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    threadData->shadowStack().LeaveFrame(start, parameters, count);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void SetCurrentFrame(ObjHeader** start) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    threadData->shadowStack().SetCurrentFrame(start);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW FrameOverlay* getCurrentFrame() {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    return threadData->shadowStack().getCurrentFrame();
}

NO_SAFEPOINT
extern "C" PERFORMANCE_INLINE RUNTIME_NOTHROW void CheckCurrentFrame(ObjHeader** frame) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    return threadData->shadowStack().checkCurrentFrame(reinterpret_cast<FrameOverlay*>(frame));
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void AddTLSRecord(MemoryState* memory, void** key, int size) {
    auto* threadData = memory->GetThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    threadData->tls().AddRecord(key, size);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void CommitTLSStorage(MemoryState* memory) {
    auto* threadData = memory->GetThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    threadData->tls().Commit();
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void ClearTLS(MemoryState* memory) {
    auto* threadData = memory->GetThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    threadData->tls().Clear();
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW ObjHeader** LookupTLS(void** key, int index) {
    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    AssertThreadState(threadData, ThreadState::kRunnable);
    return threadData->tls().Lookup(key, index);
}

HAS_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_collect(ObjHeader*) {
    checkUseCRT<CheckMode::Slow>([] {
        RuntimeSetLastFrame1();
        common::BaseRuntime::RequestGC(common::GCReason::GC_REASON_USER, false, common::GCType::GC_TYPE_FULL);
#ifdef ENABLE_GC_FASTPATH
        // Refresh x28 — RequestGC may have run through STW which clobbers x28.
        auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        auto* mutator = reinterpret_cast<common::MutatorBase*>(threadData->GetThreadHolder()->GetMutator());
        common::UpdateThreadLocalDataReg(mutator);
#endif
    }, [] {
        auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        AssertThreadState(threadData, ThreadState::kRunnable);
        mm::GlobalData::Instance().gcScheduler().scheduleAndWaitFinalized();
    });
}

HAS_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_schedule(ObjHeader*) {
    mm::GlobalData::Instance().gcScheduler().schedule();
}

extern "C" RUNTIME_NOTHROW bool Kotlin_native_runtime_Debugging_dumpMemory(ObjHeader*, int fd) {
    auto mainGCLock = mm::GlobalData::Instance().gc().gcLock();

    auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData->suspensionData().requestThreadsSuspension("Memory dump");
    CallsCheckerIgnoreGuard guard;
    // We're in the runnable state, but everything else (including the GC thread) will be suspended.
    // It's fine to wait for that suspension and execute long-running operations (I/O) here.
    mm::WaitForThreadsSuspension();
    bool success = mm::DumpMemory(fd);
    mm::ResumeThreads();
    return success;
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setTuneThreshold(ObjHeader*, KBoolean value) {
    mm::GlobalData::Instance().gcScheduler().config().autoTune = value;
}

NO_SAFEPOINT
extern "C" KBoolean Kotlin_native_internal_GC_getTuneThreshold(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().autoTune.load();
}

NO_SAFEPOINT
extern "C" KLong Kotlin_native_internal_GC_getRegularGCIntervalMicroseconds(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().regularGcIntervalMicroseconds.load();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setRegularGCIntervalMicroseconds(ObjHeader*, KLong value) {
    RuntimeAssert(value >= 0, "Must be handled by the caller");
    mm::GlobalData::Instance().gcScheduler().config().regularGcIntervalMicroseconds = value;
}

NO_SAFEPOINT
extern "C" KLong Kotlin_native_internal_GC_getTargetHeapBytes(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().targetHeapBytes.load();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setTargetHeapBytes(ObjHeader*, KLong value) {
    RuntimeAssert(value >= 0, "Must be handled by the caller");
    mm::GlobalData::Instance().gcScheduler().config().targetHeapBytes = value;
}

NO_SAFEPOINT
extern "C" KDouble Kotlin_native_internal_GC_getTargetHeapUtilization(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().targetHeapUtilization.load();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setTargetHeapUtilization(ObjHeader*, KDouble value) {
    RuntimeAssert(value > 0 && value <= 1, "Must be handled by the caller");
    mm::GlobalData::Instance().gcScheduler().config().targetHeapUtilization = value;
}

NO_SAFEPOINT
extern "C" KLong Kotlin_native_internal_GC_getMaxHeapBytes(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().maxHeapBytes.load();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setMaxHeapBytes(ObjHeader*, KLong value) {
    RuntimeAssert(value >= 0, "Must be handled by the caller");
    mm::GlobalData::Instance().gcScheduler().config().maxHeapBytes = value;
}

NO_SAFEPOINT
extern "C" KLong Kotlin_native_internal_GC_getMinHeapBytes(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().minHeapBytes.load();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setMinHeapBytes(ObjHeader*, KLong value) {
    RuntimeAssert(value >= 0, "Must be handled by the caller");
    mm::GlobalData::Instance().gcScheduler().config().minHeapBytes = value;
}

NO_SAFEPOINT
extern "C" KDouble Kotlin_native_internal_GC_getHeapTriggerCoefficient(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().heapTriggerCoefficient.load();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setHeapTriggerCoefficient(ObjHeader*, KDouble value) {
    RuntimeAssert(value > 0 && value <= 1, "Must be handled by the caller");
    mm::GlobalData::Instance().gcScheduler().config().heapTriggerCoefficient = value;
}

NO_SAFEPOINT
extern "C" KBoolean Kotlin_native_internal_GC_getPauseOnTargetHeapOverflow(ObjHeader*) {
    return mm::GlobalData::Instance().gcScheduler().config().mutatorAssists();
}

NO_SAFEPOINT
extern "C" void Kotlin_native_internal_GC_setPauseOnTargetHeapOverflow(ObjHeader*, KBoolean value) {
    mm::GlobalData::Instance().gcScheduler().config().setMutatorAssists(value);
}

NO_SAFEPOINT
extern "C" KBoolean Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_isAvailable(ObjHeader* gc) {
    return mm::GlobalData::Instance().allocator().mainThreadFinalizerProcessorAvailable();
}

NO_SAFEPOINT
extern "C" KLong Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_getMaxTimeInTask(ObjHeader* gc) {
    KLong result;
    mm::GlobalData::Instance().allocator().configureMainThreadFinalizerProcessor([&](auto& config) noexcept -> void {
        result = std::chrono::duration_cast<std::chrono::microseconds>(config.maxTimeInTask).count();
    });
    return result;
}

NO_SAFEPOINT
extern "C" void Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_setMaxTimeInTask(ObjHeader* gc, KLong value) {
    mm::GlobalData::Instance().allocator().configureMainThreadFinalizerProcessor(
            [=](auto& config) noexcept -> void { config.maxTimeInTask = std::chrono::microseconds(value); });
}

NO_SAFEPOINT
extern "C" KLong Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_getMinTimeBetweenTasks(ObjHeader* gc) {
    KLong result;
    mm::GlobalData::Instance().allocator().configureMainThreadFinalizerProcessor([&](auto& config) noexcept -> void {
        result = std::chrono::duration_cast<std::chrono::microseconds>(config.minTimeBetweenTasks).count();
    });
    return result;
}

NO_SAFEPOINT
extern "C" void Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_setMinTimeBetweenTasks(ObjHeader* gc, KLong value) {
    mm::GlobalData::Instance().allocator().configureMainThreadFinalizerProcessor(
            [=](auto& config) noexcept -> void { config.minTimeBetweenTasks = std::chrono::microseconds(value); });
}

NO_SAFEPOINT
extern "C" KULong Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_getBatchSize(ObjHeader* gc) {
    KULong result;
    mm::GlobalData::Instance().allocator().configureMainThreadFinalizerProcessor(
            [&](auto& config) noexcept -> void { result = config.batchSize; });
    return result;
}

NO_SAFEPOINT
extern "C" void Kotlin_native_runtime_GC_MainThreadFinalizerProcessor_setBatchSize(ObjHeader* gc, KULong value) {
    mm::GlobalData::Instance().allocator().configureMainThreadFinalizerProcessor(
            [=](auto& config) noexcept -> void { config.batchSize = value; });
}

HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW void PerformFullGC(MemoryState* memory) {
    checkUseCRT<CheckMode::Slow>([] {
        common::BaseRuntime::RequestGC(common::GCReason::GC_REASON_USER , false, common::GCType::GC_TYPE_FULL);
    }, [] {
        mm::GlobalData::Instance().gcScheduler().scheduleAndWaitFinalized();
    });
}

// Used in C export.
NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW mm::RawExternalRCRef* CreateStablePointer(ObjHeader* object) {
    AssertThreadState(ThreadState::kRunnable);
    return mm::createRetainedExternalRCRef(object);
}

// Used in C export.
NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void DisposeStablePointer(mm::RawExternalRCRef* pointer) {
    // Can be safely called in any thread state.
    mm::releaseExternalRCRef(pointer);
    mm::disposeExternalRCRef(pointer);
}

// Used in C export.
NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW OBJ_GETTER(DerefStablePointer, mm::RawExternalRCRef* pointer) {
    AssertThreadState(ThreadState::kRunnable);
    RETURN_OBJ(mm::dereferenceExternalRCRef(pointer));
}

// it would be inlined manually in RemoveRedundantSafepointsPass
HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW NO_INLINE RUNTIME_EXPORT void Kotlin_mm_safePointFunctionPrologue() {
    mm::safePoint();
}

extern "C" RUNTIME_NOTHROW ALWAYS_INLINE RUNTIME_EXPORT void Kotlin_mm_safePointFunctionPrologueStub() {
    mm::safePointStub();
}

HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW CODEGEN_INLINE_POLICY RUNTIME_EXPORT void Kotlin_mm_safePointWhileLoopBody() {
    mm::safePoint();
}

extern "C" RUNTIME_NOTHROW CODEGEN_INLINE_POLICY RUNTIME_EXPORT void Kotlin_mm_safePointWhileLoopBodyStub() {
    mm::safePointStub();
}

HAS_SAFEPOINT
extern "C" NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateNative() {
    SwitchThreadState(mm::ThreadRegistry::Instance().CurrentThreadData(), ThreadState::kNative);
}

extern "C" NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateNativeWithoutUpdateLastFrame() {
    SwitchThreadState(mm::ThreadRegistry::Instance().CurrentThreadData(), ThreadState::kNative, false, false);
}

HAS_SAFEPOINT
extern "C" NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateNative_debug() {
    SwitchThreadState(mm::ThreadRegistry::Instance().CurrentThreadData(), ThreadState::kNative);
}

HAS_SAFEPOINT
extern "C" NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateRunnable() {
    SwitchThreadState(mm::ThreadRegistry::Instance().CurrentThreadData(), ThreadState::kRunnable);
}

HAS_SAFEPOINT
extern "C" NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateRunnable_debug() {
    SwitchThreadState(mm::ThreadRegistry::Instance().CurrentThreadData(), ThreadState::kRunnable);
}

extern "C" NO_INLINE RUNTIME_NOTHROW void RuntimeSetLastFrame(MemoryState* thread, ThreadState state) noexcept {
    if (state == thread->GetThreadData()->state()) {
        RuntimeAssert(0, "Can't save frame in the same state.");
        return;
    }
    thread->GetThreadData()->RuntimeSetLastFrame();
}

extern "C" NO_INLINE RUNTIME_NOTHROW void RuntimeSetLastFrame1() {
    auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData->RuntimeSetLastFrame();
}

// CRT-specific x28 register save/restore. x28 holds the CRT TLS pointer (fastpath).
// When a Kotlin call leaves the kRunnable state, x28 must be saved and restored on resume.
// Kept separate from fp-unwind's frame management.
static thread_local std::stack<common::CallToFFixedX28> globalX28Guard;
ALWAYS_INLINE extern "C" RUNTIME_NOTHROW void SaveX28() noexcept {
    globalX28Guard.emplace();
}

ALWAYS_INLINE extern "C" RUNTIME_NOTHROW void RestoreX28() noexcept {
    if (globalX28Guard.empty()) {
        RuntimeLogInfo({kTagGC}, "unmatched x28 restore");
        RuntimeAssert(false, "try to restore x28 with non-saved value");
        return;
    }
    globalX28Guard.pop();
}

MemoryState* kotlin::mm::GetMemoryState() noexcept {
    return ToMemoryState(ThreadRegistry::Instance().CurrentThreadDataNode());
}

bool kotlin::mm::IsCurrentThreadRegistered() noexcept {
    return ThreadRegistry::IsCurrentThreadRegistered();
}

PERFORMANCE_INLINE kotlin::CalledFromNativeGuard::CalledFromNativeGuard(bool reentrant) noexcept : reentrant_(reentrant) {
    Kotlin_initRuntimeIfNeeded();
    thread_ = mm::GetMemoryState();
    oldState_ = SwitchThreadState(thread_, ThreadState::kRunnable, reentrant_);
}

void kotlin::StartFinalizerThreadIfNeeded() noexcept {
    mm::GlobalData::Instance().allocator().startFinalizerThreadIfNeeded();
}

bool kotlin::FinalizersThreadIsRunning() noexcept {
    return mm::GlobalData::Instance().allocator().finalizersThreadIsRunning();
}

RUNTIME_NOTHROW extern "C" void Kotlin_processObjectInMark(void* state, ObjHeader* object) {
    gc::GC::processObjectInMark(state, object);
}

RUNTIME_NOTHROW extern "C" void Kotlin_processArrayInMark(void* state, ObjHeader* object) {
    gc::GC::processArrayInMark(state, object->array());
}

RUNTIME_NOTHROW extern "C" void Kotlin_processEmptyObjectInMark(void* state, ObjHeader* object) {
    // Empty object. Nothing to do.
    // TODO: Try to generate it in the code generator.
}

HAS_SAFEPOINT
extern "C" OBJ_GETTER(makePermanentWeakReferenceImpl, ObjHeader*);
extern "C" OBJ_GETTER(makeObjCWeakReferenceImpl, void*);

HAS_SAFEPOINT
RUNTIME_NOTHROW extern "C" OBJ_GETTER(Konan_getWeakReferenceImpl, ObjHeader* referred) {
    if (referred->permanent()) {
        RETURN_RESULT_OF(makePermanentWeakReferenceImpl, referred);
    }
#if KONAN_OBJC_INTEROP
    if (IsInstanceInternal(referred, theObjCObjectWrapperTypeInfo)) {
        RETURN_RESULT_OF(makeObjCWeakReferenceImpl, referred->GetAssociatedObject());
    }
#endif // KONAN_OBJC_INTEROP
    RETURN_RESULT_OF(mm::createRegularWeakReferenceImpl, referred);
}

NO_SAFEPOINT
RUNTIME_NOTHROW extern "C" void DisposeRegularWeakReferenceImpl(ObjHeader* weakRef) {
    mm::disposeRegularWeakReferenceImpl(weakRef);
}

void kotlin::OnMemoryAllocation(size_t totalAllocatedBytes) noexcept {
    mm::GlobalData::Instance().gcScheduler().setAllocatedBytes(totalAllocatedBytes);
}

void kotlin::initObjectPool() noexcept {
    alloc::initObjectPool();
}

void kotlin::compactObjectPoolInCurrentThread() noexcept {
    alloc::compactObjectPoolInCurrentThread();
}

RUNTIME_NOTHROW extern "C" void Kotlin_Pinned_GCPin(KRef thiz, KRef obj) {
    checkUseCRT<CheckMode::Slow>([&] {
        CRT_Pin(obj);
    });
}

RUNTIME_NOTHROW extern "C" void Kotlin_Pinned_GCUnpin(KRef thiz, KRef obj) {
    checkUseCRT<CheckMode::Slow>([&] {
        CRT_UnPin(obj);
    });
}

void CRT_Pin(const void* obj) {
    if (common::IsHeapAddress(obj)) {
        common::BaseObjectPinned(reinterpret_cast<common::BaseObject*>(const_cast<void*>(obj)));
    }
}

void CRT_UnPin(const void* obj) {
    if (common::IsHeapAddress(obj)) {
        common::BaseObjectUnPinned(reinterpret_cast<common::BaseObject*>(const_cast<void*>(obj)));
    }
}
