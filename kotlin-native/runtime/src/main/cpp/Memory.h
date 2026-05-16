/*
 * Copyright 2010-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef RUNTIME_MEMORY_H
#define RUNTIME_MEMORY_H

#include <utility>
#include <std_support/Atomic.hpp>

#include "Alignment.hpp"
#include "DisallowSafepointScope.h"
#include "KAssert.h"
#include "Common.h"
#include "TypeInfo.h"
#include "PointerBits.h"
#include "MemoryManagerSwitch.hpp"
#include "CRTFastpathUtils.hpp"
#include "Utils.hpp"

#ifdef KONAN_OHOS
#include "memory_trace.h"
#include <deviceinfo.h>
#define OHOS_RESTRACE_MIN_API 21
#endif

#if KONAN_NEED_SMALL_BINARY
  // Currently, codegen places a lot of unnecessary calls to MM functions.
  // By forcing NO_INLINE on these functions we keep binaries from growing too big.
  #define CODEGEN_INLINE_POLICY NO_INLINE
#else
  #define CODEGEN_INLINE_POLICY ALWAYS_INLINE
#endif

constexpr uint64_t kImmTypeInfoMask = 0x0000fffffffffffc; // CRT places language and forwarding tags in higher bits
typedef enum : uint64_t {
    OBJECT_TAG_HEAP = 0,
    OBJECT_TAG_PERMANENT = 1, // Must match to permanentTag() in Kotlin.
    OBJECT_TAG_STACK = 3,
    KOTLIN_OBJECT_TAG_MASK = (1 << 2) - 1,
    // Keep in sync with immTypeInfoMask in Kotlin.
    OBJECT_TAG_MASK = ~kImmTypeInfoMask
} ObjectTag;

struct ArrayHeader;
struct MetaObjHeader;

template<typename T> class KHandle;

// Header of every object.
#define OBJ_HEADER_FIELDS \
  TypeInfo* typeInfoOrMeta_;

struct ObjHeader {
  OBJ_HEADER_FIELDS

  // Returns `nullptr` if it's not a meta object.
  static MetaObjHeader* AsMetaObject(TypeInfo* typeInfo) noexcept {
      auto* typeInfoOrMeta = clearPointerBits(typeInfo, OBJECT_TAG_MASK);
      if (typeInfoOrMeta != typeInfoOrMeta->typeInfo_) {
          return reinterpret_cast<MetaObjHeader*>(typeInfoOrMeta);
      } else {
          return nullptr;
      }
  }

  TypeInfo* typeInfoOrMetaRelaxed() const { return kotlin::std_support::atomic_ref{typeInfoOrMeta_}.load(std::memory_order_relaxed);}
  TypeInfo* typeInfoOrMetaAcquire() const { return kotlin::std_support::atomic_ref{typeInfoOrMeta_}.load(std::memory_order_acquire);}

  /**
   * Formally, this code data races with installing ExtraObject. Even though, we are okey, with reading
   * both typeInfo and meta-object pointer, llvm memory model doesn't guarantee, that if we are able to
   * see metaObject, written by other thread, we would be able to see metaObject->typeInfo.
   *
   * To make this correct with llvm memory model we need to use [LLVMAtomicOrdering.LLVMAtomicOrderingAcquire] here.
   * Unfortunately, this is dramatically harmful for performance on arm architecture. So, we are using
   * [LLVMAtomicOrdering.LLVMAtomicOrderingMonotonic] for both this read and following load of metaObject->typeInfo.
   * At this point, we have no data race, but llvm memory model allows uninitialized value to be read from metaObject->typeInfo.
   *
   * Hardware guaranties on many supported platforms doesn't allow this to happen.
   */
  const TypeInfo* type_info() const {
      auto typeInfoOrMeta = clearPointerBits(typeInfoOrMetaRelaxed(), OBJECT_TAG_MASK);
      auto atomicTypeInfoPtr = kotlin::std_support::atomic_ref{typeInfoOrMeta->typeInfo_};
      const TypeInfo* typeInfo = atomicTypeInfoPtr.load(std::memory_order_relaxed);
      RuntimeAssert(typeInfo != nullptr, "TypeInfo ptr in object %p in null", this);
      return typeInfo;
  }

  bool has_meta_object() const {
      return meta_object_or_null() != nullptr;
  }

  MetaObjHeader* meta_object() {
      if (auto* metaObject = AsMetaObject(typeInfoOrMetaAcquire())) {
          return metaObject;
      }
      return createMetaObject(this);
  }

  MetaObjHeader* meta_object_or_null() const noexcept { return AsMetaObject(typeInfoOrMetaAcquire()); }

#if defined(KONAN_OBJC_INTEROP) || defined(KONAN_OHOS)
  void* GetAssociatedObject() const;
  void SetAssociatedObject(void* obj);
  void* CasAssociatedObject(void* expectedObj, void* obj);
#endif

  inline bool stack() const {
    return getPointerBits(typeInfoOrMetaRelaxed(), KOTLIN_OBJECT_TAG_MASK) == OBJECT_TAG_STACK;
  }

  // Unsafe cast to ArrayHeader. Use carefully!
  // TODO: RuntimeAssert on type_info()->IsArray()?
  ArrayHeader* array() { return reinterpret_cast<ArrayHeader*>(this); }
  const ArrayHeader* array() const { return reinterpret_cast<const ArrayHeader*>(this); }

  inline bool permanent() const {
    return getPointerBits(typeInfoOrMetaRelaxed(), KOTLIN_OBJECT_TAG_MASK) == OBJECT_TAG_PERMANENT;
  }

  inline bool heap() const {
    return getPointerBits(typeInfoOrMetaRelaxed(), KOTLIN_OBJECT_TAG_MASK) == OBJECT_TAG_HEAP;
  }

  inline bool local() const {
    return getPointerBits(typeInfoOrMetaRelaxed(), KOTLIN_OBJECT_TAG_MASK) == OBJECT_TAG_STACK;
  }

  static MetaObjHeader* createMetaObject(ObjHeader* object);
  static void destroyMetaObject(ObjHeader* object);
};
static_assert(alignof(ObjHeader) <= kotlin::kObjectAlignment);

// Header of value type array objects.
// Element size is stored in instanceSize_ field of TypeInfo, negated.
#define ARRAY_HEADER_FIELDS \
  OBJ_HEADER_FIELDS \
  uint32_t count_;

struct ArrayHeader {
    ARRAY_HEADER_FIELDS

    const TypeInfo* type_info() const
    {
        return obj()->type_info(); // Array hashCode is identityHashCode so arrays might have ExtraObject installed.
    }

    ObjHeader* obj() { return reinterpret_cast<ObjHeader*>(this); }
    const ObjHeader* obj() const { return reinterpret_cast<const ObjHeader*>(this); }
};
static_assert(alignof(ArrayHeader) <= kotlin::kObjectAlignment);

static inline ObjHeader* const kInitializingSingleton = reinterpret_cast<ObjHeader*>(1);
ALWAYS_INLINE inline bool isNullOrMarker(const ObjHeader* obj) noexcept {
    return reinterpret_cast<uintptr_t>(obj) <= 1;
}

ALWAYS_INLINE bool isPermanentOrFrozen(const ObjHeader* obj);

struct FrameOverlay;
namespace kotlin {
enum class ThreadState {
    kRunnable, kNative
};
} // namespace kotlin

#ifdef __cplusplus
extern "C" {
#endif
// Stackmap requires AS1 (address space 1) on pointer types in function signatures
// to correctly identify heap references. However, applying AS1 throughout the runtime would require pervasive type changes.
// We use AS1 when declaring the signatures and in the actual definition uses regular pointer
typedef AS1 ObjHeader * HeapObjPtr;
typedef const AS1 ObjHeader * ConstHeapObjPtr;
typedef AS1 ObjHeader * AS1 * HeapDerivedPtr;

#define OBJ_RESULT __result__
#define OBJ_GETTER0(name) HeapObjPtr name(HeapObjPtr* OBJ_RESULT)
#define OBJ_GETTER(name, ...) HeapObjPtr name(__VA_ARGS__, HeapObjPtr* OBJ_RESULT)
#define RETURN_OBJ(value) { HeapObjPtr __obj = value; \
    UpdateReturnRef(OBJ_RESULT, __obj);               \
    return __obj; }
#define RETURN_RESULT_OF0(name) {       \
    HeapObjPtr __obj = name(OBJ_RESULT);  \
    return __obj;                         \
  }
#define RETURN_RESULT_OF(name, ...) {                   \
    HeapObjPtr __result = name(__VA_ARGS__, OBJ_RESULT);  \
    return __result;                                      \
  }

struct MemoryState;

MemoryState* InitMemory();
void DeinitMemory(MemoryState*, bool destroyRuntime);
void ClearMemoryForTests(MemoryState*);

//
// Object allocation.
//
// Allocation can happen in either GLOBAL, FRAME or ARENA scope. Depending on that,
// Alloc* or ArenaAlloc* is called. Regular alloc means allocation happens in the heap,
// and each object gets its individual container. Otherwise, allocator uses aux slot in
// an implementation-defined manner, current behavior is to keep arena pointer there.
// Arena containers are not reference counted, and is explicitly freed when leaving
// its owner frame.
// Escape analysis algorithm is the provider of information for decision on exact aux slot
// selection, and comes from upper bound estimation of object lifetime.
//
OBJ_GETTER(AllocInstance, const TypeInfo* type_info) RUNTIME_NOTHROW;

OBJ_GETTER(AllocArrayInstance, const TypeInfo* type_info, int32_t elements);

// Followings are the APIs used by the compiler, differences from the runtime counterpart, they have the enterFrame operations
OBJ_GETTER(AllocInstanceForCI, const TypeInfo* type_info) RUNTIME_NOTHROW;

OBJ_GETTER(AllocArrayInstanceForCI, const TypeInfo* type_info, int32_t elements);


// `initialValue` may be `nullptr`, which signifies that the appropriate initial value was already
// set by static initialization.
// TODO: When global initialization becomes lazy, this signature won't do.
void InitAndRegisterGlobal(HeapObjPtr* location, ConstHeapObjPtr initialValue) RUNTIME_NOTHROW;

//
// Object reference management.
//
// Reference management scheme we use assumes significant degree of flexibility, so that
// one could implement either pure reference counting scheme, or tracing collector without
// much ado.
// Most important primitive is Update*Ref() API, which modifies location to use new
// object reference. In pure reference counted scheme it will check old value,
// decrement reference, increment counter on the new value, and store it into the field.
// In tracing collector-like scheme, only field updates counts, and all other operations are
// essentially no-ops.
//
// On codegeneration phase we adopt following approaches:
//  - every stack frame has several slots, holding object references (allRefs)
//  - those are known by compiler (and shall be grouped together)
//  - it keeps all locally allocated objects in such slot
//  - all local variables keeping an object also allocate a slot
//  - most manipulations on objects happens in SSA variables and do no affect slots
//  - exception handlers knowns slot locations for every function, and can update references
//    in intermediate frames when throwing
//

// NOTE: Must match `MemoryModel` in `Platform.kt`
enum class MemoryModel {
    kStrict = 0,
    kRelaxed = 1,
    kExperimental = 2,
};

// Controls the current memory model, is compile-time constant.
extern const MemoryModel CurrentMemoryModel;

// Reads heap/static data location. thisPtr is the owning object (used by CRT read barrier; nullptr for CMS).
HeapObjPtr ReadHeapRef(HeapDerivedPtr location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
// Reads volatile heap/static data location.
HeapObjPtr ReadVolatileHeapRef(HeapDerivedPtr location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
// Zeroes heap location.
void ZeroHeapRef(HeapDerivedPtr location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
// Zeroes an array.
void ZeroArrayRefs(ObjHeader* array) RUNTIME_NOTHROW;
// Zeroes stack location.
void ZeroStackRef(HeapObjPtr* location) RUNTIME_NOTHROW;
// Updates stack location.
void UpdateStackRef(HeapObjPtr* location, ConstHeapObjPtr object) RUNTIME_NOTHROW;
// Updates heap/static data location.
void UpdateHeapRef(HeapDerivedPtr location, ConstHeapObjPtr object, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
// Updates volatile heap/static data location.
void UpdateVolatileHeapRef(HeapDerivedPtr location, ConstHeapObjPtr object, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
// OBJ_GETTER macros append an implicit HeapObjPtr* OBJ_RESULT param, so thisPtr cannot carry a default arg.
OBJ_GETTER(CompareAndSwapVolatileHeapRef, HeapDerivedPtr location, HeapObjPtr expectedValue,
           HeapObjPtr newValue, ObjHeader* thisPtr) RUNTIME_NOTHROW;
bool CompareAndSetVolatileHeapRef(HeapDerivedPtr location, HeapObjPtr expectedValue,
                                  HeapObjPtr newValue, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
OBJ_GETTER(GetAndSetVolatileHeapRef, HeapDerivedPtr location, HeapObjPtr newValue, ObjHeader* thisPtr) RUNTIME_NOTHROW;

// Updates location if it is null, atomically.
// Updates reference in return slot.
void UpdateReturnRef(HeapObjPtr* returnSlot, ConstHeapObjPtr object) RUNTIME_NOTHROW;
OBJ_GETTER(ReadHeapRefNoLock, HeapObjPtr object, int32_t index);
// Called on frame enter, if it has object slots.
void EnterFrame(HeapObjPtr* start, int parameters, int count) RUNTIME_NOTHROW;
// Called on frame leave, if it has object slots.
void LeaveFrame(HeapObjPtr* start, int parameters, int count) RUNTIME_NOTHROW;
// Set current frame in case if exception caught.
void SetCurrentFrame(HeapObjPtr* start) RUNTIME_NOTHROW;
FrameOverlay* getCurrentFrame() RUNTIME_NOTHROW;
ALWAYS_INLINE void CheckCurrentFrame(HeapObjPtr* frame) RUNTIME_NOTHROW;
// Add TLS object storage, called by the generated code.
void AddTLSRecord(MemoryState* memory, void** key, int size) RUNTIME_NOTHROW;
// Allocate storage for TLS. `AddTLSRecord` cannot be called after this.
void CommitTLSStorage(MemoryState* memory) RUNTIME_NOTHROW;
// Clear TLS object storage.
void ClearTLS(MemoryState* memory) RUNTIME_NOTHROW;
// Lookup element in TLS object storage.
HeapObjPtr* LookupTLS(void** key, int index) RUNTIME_NOTHROW;

// APIs for GC pin, used only internally by Runtime
void CRT_Pin(const void* obj);
void CRT_UnPin(const void* obj);

void Kotlin_native_internal_GC_collect(HeapObjPtr);
void Kotlin_native_internal_GC_setTuneThreshold(HeapObjPtr, bool value);
bool Kotlin_native_internal_GC_getTuneThreshold(HeapObjPtr);
RUNTIME_NOTHROW bool Kotlin_native_runtime_Debugging_dumpMemory(HeapObjPtr, int fd);
void PerformFullGC(MemoryState* memory) RUNTIME_NOTHROW;

// Sets state of the current thread to NATIVE (used by the new MM).
CODEGEN_INLINE_POLICY RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateNative();
CODEGEN_INLINE_POLICY RUNTIME_NOTHROW RUNTIME_EXPORT void Kotlin_mm_switchThreadStateNativeWithoutUpdateLastFrame();
// Sets state of the current thread to RUNNABLE (used by the new MM).
CODEGEN_INLINE_POLICY RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateRunnable();
// No-inline versions of the functions above are used in debug mode to workaround KT-67567
// by outlining certain CAS instructions from user code:
NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateNative_debug();
NO_INLINE RUNTIME_NOTHROW void Kotlin_mm_switchThreadStateRunnable_debug();

// Safe point callbacks from Kotlin code generator.
CODEGEN_INLINE_POLICY void Kotlin_mm_safePointFunctionPrologue() RUNTIME_NOTHROW;
CODEGEN_INLINE_POLICY void Kotlin_mm_safePointWhileLoopBody() RUNTIME_NOTHROW;

RUNTIME_NOTHROW void DisposeRegularWeakReferenceImpl(HeapObjPtr counter);
NO_INLINE RUNTIME_NOTHROW void RuntimeSetLastFrame(MemoryState* thread, kotlin::ThreadState state);
NO_INLINE RUNTIME_NOTHROW void RuntimeSetLastFrame1();
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void SetLastFrameReliable();

RUNTIME_NOTHROW ALWAYS_INLINE void SaveX28();
RUNTIME_NOTHROW ALWAYS_INLINE void RestoreX28();
#ifdef __cplusplus
}
#endif

struct FrameOverlay {
  FrameOverlay* previous;
  // As they go in pair, sizeof(FrameOverlay) % sizeof(void*) == 0 is always held.
  int32_t parameters;
  int32_t count;
};

// Class holding reference to an object, holding object during C++ scope.
// TODO adopt ref accessors
class ObjHolder {
public:
    ObjHolder() : obj_(nullptr) {
        EnterFrame(frame(), 0, sizeof(*this)/sizeof(void*));
    }

    explicit ObjHolder(ConstHeapObjPtr obj)
    {
        EnterFrame(frame(), 0, sizeof(*this)/sizeof(void*));
        ::UpdateStackRef(slot(), obj);
    }

    ~ObjHolder() {
        LeaveFrame(frame(), 0, sizeof(*this)/sizeof(void*));
    }

    HeapObjPtr obj() { return obj_; }

    ConstHeapObjPtr obj() const { return obj_; }

    HeapObjPtr* slot()
    {
        return &obj_;
    }

    void clear() { ::ZeroStackRef(&obj_); }

private:
    HeapObjPtr* frame() { return reinterpret_cast<HeapObjPtr*>(&frame_); }

    FrameOverlay frame_;
    HeapObjPtr obj_;
};

class ExceptionObjHolder {
public:
    static void Throw(HeapObjPtr exception) RUNTIME_NORETURN;

    HeapObjPtr GetExceptionObject() noexcept;

    // Exceptions are not on a hot path, so having virtual dispatch is fine.
    virtual ~ExceptionObjHolder() = default;
};

namespace kotlin {
namespace mm {

// Returns the MemoryState for the current thread.
// The current thread must be attached to the runtime.
// Try not to use it very often, as (1) thread local access can be slow on some platforms,
// (2) TLS gets deallocated before our thread destruction hooks run.
MemoryState* GetMemoryState() noexcept;

// TODO: Replace with direct access to ThreadRegistry.
// Checks if the current thread is attached to the runtime.
// This function accesses a TLS variable, so it must not be called from a thread destructor.
bool IsCurrentThreadRegistered() noexcept;

} // namespace mm

ThreadState GetThreadState(MemoryState* thread) noexcept;

inline ThreadState GetThreadState() noexcept {
    return GetThreadState(mm::GetMemoryState());
}

// Switches the state of the given thread to `newState` and returns the previous thread state.
ThreadState SwitchThreadState(MemoryState* thread, ThreadState newState, bool reentrant = false) noexcept;

// Asserts that the given thread is in the given state.
void AssertThreadState(MemoryState* thread, ThreadState expected) noexcept;
void AssertThreadState(MemoryState* thread, std::initializer_list<ThreadState> expected) noexcept;

// Asserts that the current thread is in the the given state.
ALWAYS_INLINE inline void AssertThreadState(ThreadState expected) noexcept {
    // Avoid redundant TLS access in GetMemoryState if runtime asserts are disabled.
    if (compiler::runtimeAssertsMode() != compiler::RuntimeAssertsMode::kIgnore) {
        AssertThreadState(mm::GetMemoryState(), expected);
    }
}

ALWAYS_INLINE inline void AssertThreadState(std::initializer_list<ThreadState> expected) noexcept {
    // Avoid redundant TLS access in GetMemoryState if runtime asserts are disabled.
    if (compiler::runtimeAssertsMode() != compiler::RuntimeAssertsMode::kIgnore) {
        AssertThreadState(mm::GetMemoryState(), expected);
    }
}

// Scopely sets the given thread state for the given thread.
class ThreadStateGuard final : private MoveOnly {
public:
    // Do not set any state. Useful to create a variable to move another guard into.
    ThreadStateGuard() : thread_(nullptr), oldState_(ThreadState::kNative), reentrant_(false) {}

    // Set the state for the given thread.
    ThreadStateGuard(MemoryState* thread, ThreadState state, bool reentrant = false) noexcept : thread_(thread), reentrant_(reentrant) {
        oldState_ = SwitchThreadState(thread_, state, reentrant_);
    }

    // Sets the state for the current thread.
    explicit ThreadStateGuard(ThreadState state, bool reentrant = false) noexcept
        : ThreadStateGuard(mm::GetMemoryState(), state, reentrant) {};

    ThreadStateGuard(ThreadStateGuard&& other) noexcept
        : thread_(other.thread_), oldState_(other.oldState_), reentrant_(other.reentrant_) {
        other.thread_ = nullptr;
    }

    ~ThreadStateGuard() noexcept {
        if (thread_ != nullptr) {
            SwitchThreadState(thread_, oldState_, reentrant_);
        }
    }

    ThreadStateGuard& operator=(ThreadStateGuard&& other) noexcept {
        thread_ = other.thread_;
        oldState_ = other.oldState_;
        reentrant_ = other.reentrant_;
        other.thread_ = nullptr;
        return *this;
    }

private:
    MemoryState* thread_;
    ThreadState oldState_;
    bool reentrant_;
    common::CallToFFixedX28 guard{};
};

// Scopely sets the kRunnable thread state for the current thread,
// and initializes runtime if needed for new MM.
// No-op for old GC.
class CalledFromNativeGuard final : private Pinned {
public:
    CalledFromNativeGuard(bool reentrant = false) noexcept;

    ~CalledFromNativeGuard() noexcept {
        SwitchThreadState(thread_, oldState_, reentrant_);
    }
private:
    MemoryState* thread_;
    ThreadState oldState_;
    bool reentrant_;
};

class CurrentFrameGuard : Pinned {
public:
    CurrentFrameGuard() : frame_(getCurrentFrame()) {}
    ~CurrentFrameGuard() { SetCurrentFrame(reinterpret_cast<HeapObjPtr*>(frame_)); }
private:
    FrameOverlay* frame_;
};

template <ThreadState state, typename R, typename... Args>
ALWAYS_INLINE inline R CallWithThreadState(R(*function)(Args...), Args... args) {
    ThreadStateGuard guard(state);
    return function(std::forward<Args>(args)...);
}

class NativeOrUnregisteredThreadGuard final : private MoveOnly {
public:
    explicit NativeOrUnregisteredThreadGuard(bool reentrant = false) noexcept {
        // The default ctor of ThreadStateGuard doesn't set the state.
        // So the actual state switching is performed only if the thread is registered.
        if (kotlin::mm::IsCurrentThreadRegistered()) {
            backingGuard_ = kotlin::ThreadStateGuard(kotlin::ThreadState::kNative, reentrant);
        }
    }
    ~NativeOrUnregisteredThreadGuard() noexcept {
    }

private:
    ThreadStateGuard backingGuard_;
};

void initGlobalMemory() noexcept;

void StartFinalizerThreadIfNeeded() noexcept;
bool FinalizersThreadIsRunning() noexcept;

void OnMemoryAllocation(size_t totalAllocatedBytes) noexcept;

void initObjectPool() noexcept;
void compactObjectPoolInCurrentThread() noexcept;

} // namespace kotlin

RUNTIME_NOTHROW ALWAYS_INLINE extern "C" void Kotlin_processObjectInMark(void* state, HeapObjPtr object);
RUNTIME_NOTHROW ALWAYS_INLINE extern "C" void Kotlin_processArrayInMark(void* state, HeapObjPtr object);
RUNTIME_NOTHROW ALWAYS_INLINE extern "C" void Kotlin_processEmptyObjectInMark(void* state, HeapObjPtr object);

#endif // RUNTIME_MEMORY_H
