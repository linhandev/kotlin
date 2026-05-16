# CRT/CMC + fp-unwind merge resolution report

**Worktree**: `/Users/starunvs/mpcore/MPCore-kotlin-cpf-crt-v3-merge`
**Branch**: `lxy_cpf_introduce-crt-v3-merge` (off `lxy_cpf_introduce-crt-v3`)
**Merging in**: `feature/2.2.21-llvm19.1.4`
**Merge command**: `git merge feature/2.2.21-llvm19.1.4 --no-commit --no-ff`

Logs every conflict resolution with before/after snippets. Each file shows the conflict markers, then the resolution.

---

## Pre-merge

- **Stray empty `Task` file** committed on llvm19.1.4 (commit `157ec651b78`, blob `e69de29bb2d`): deleted via `git rm -f Task`. It was an empty 0-byte file at repo root, not referenced anywhere — clearly accidental.

## Auto-merged (no conflict)

- `kotlin-native/gradle.properties` — `llvm-1914-*-dev-9` toolchain preserved (v3's value wins via 3-way merge).
- `.gitmodules` + `third-party/common-rt` submodule entry — added cleanly.
- ~185 other files including all of `runtime/src/crt/`, `runtime/src/gc/crt/`, `runtime/src/alloc/crt/`, `MemoryManagerSwitch.hpp`, `CompilerConstants.hpp` enum.

---

## 1. `kotlin-native/konan/konan.properties` — 1 conflict block

**Before:**
```properties
<<<<<<< HEAD
targetCpuFeatures.macos_arm64 = +aes,...,+v8a,+zcm,+zcz
clangFlags.macos_arm64 = -cc1 -emit-obj -mllvm -enable-compressed-bitmap-stackmap=true -mllvm -enable-lazy-stackmap=true -x ir
=======
# on macos, fastpath reserve both x27 and x28 to bypass the MachO reserve issue, see ...
targetCpuFeatures.macos_arm64 = +aes,...,+v8a,+zcm,+zcz,+reserve-x28,+reserve-x27
clangFlags.macos_arm64 = -cc1 -emit-obj -mllvm -enable-compressed-bitmap-stackmap=true -mllvm -enable-lazy-stackmap=true -mllvm -aarch64-mark-kotlin-function=true -x ir
>>>>>>> feature/2.2.21-llvm19.1.4
```

**After:**
```properties
# on macos, fastpath reserves x27 and x28 for CRT (bypass MachO reserve issue)
# Note: v3 dropped -mllvm -aarch64-mark-kotlin-function=true; KotlinStubGenerator does the marking instead.
targetCpuFeatures.macos_arm64 = +aes,...,+v8a,+zcm,+zcz,+reserve-x28,+reserve-x27
clangFlags.macos_arm64 = -cc1 -emit-obj -mllvm -enable-compressed-bitmap-stackmap=true -mllvm -enable-lazy-stackmap=true -x ir
```

**Why:** Reserved registers are required for CRT fastpath (x28 holds CRT TLS). The `aarch64-mark-kotlin-function=true` LLVM pass flag was removed by v3 commit `840f91f3436` because v3's compiler-side `KotlinStubGenerator` performs the marking; the LLVM pass is redundant. Same applies to `ohos_arm64` (which auto-merged to v3's form with `-mllvm -global-isel=0` — kept as-is).

---

## 2. `kotlin-native/runtime/build.gradle.kts` — 1 conflict block

**Before:** v3 had a single-line `headersDirs.from(files(7 dirs))` + a v3-specific `compilerArgs.add("-DKOTLIN_NATIVE_HIAPPEVENT_FW_VERSION=$kotlinVersion")`. llvm19.1.4 had a multi-line list with 13 dirs (adds `third-party/common-rt/*`) but no compilerArgs line.

**After:** combined:
```kotlin
            headersDirs.from(files(
                "src/alloc/common/cpp", "src/gcScheduler/common/cpp", "src/gc/common/cpp",
                "src/mm/cpp", "src/externalCallsChecker/common/cpp", "src/objcExport/cpp",
                "src/main/cpp",
                "../../third-party/common-rt", "../../third-party/common-rt/common_interfaces",
                "../../third-party/common-rt/common_components", "../../third-party/common-rt/libpandabase",
                "../../third-party/common-rt/libpandabase/utils",
                "../../third-party/common-rt/third_party_bounds_checking_function/include"
            ))
            compilerArgs.add("-DKOTLIN_NATIVE_HIAPPEVENT_FW_VERSION=$kotlinVersion")
```

Verified `dependsOn(copyStubObjsToDist)` from commit `da2ad3826ab` survived at lines 869 (cross-dist tasks) and 974 (KonanCacheTask).

---

## 3. `kotlin-native/runtime/src/main/cpp/Arrays.cpp` — 37 conflict blocks

Too mechanical for manual resolution. Strategy:

1. `git checkout --theirs` to take llvm19.1.4's bodies wholesale (which contain CRT barrier calls via `ReadHeapRef(slot, array->obj())` and `mutabilityCheck` additions).
2. Run `/tmp/patch_arrays.py` to extract v3's `function_name → annotation` map and re-insert `HAS_SAFEPOINT_THROW` / `NO_SAFEPOINT` annotations from v3.
3. Post-pass to move annotations from before `template<...>` to after (matches v3 convention).
4. Manually add `HAS_SAFEPOINT_THROW` to the new `mutabilityCheck` helper.

**Example before/after for one function:**

Before (v3 side):
```cpp
template<bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
PERFORMANCE_INLINE const KRef* Kotlin_Array_get_value(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (BoundsCheck) boundsCheck(array, index);
  return ArrayAddressOfElementAt(array, index);
}
```
Before (llvm19.1.4 side):
```cpp
template<bool BoundsCheck = true>
ALWAYS_INLINE KRef Kotlin_Array_get_value(KConstRef thiz, KInt index) {
  ArrayHeader* array = const_cast<ArrayHeader*>(thiz->array());
  if (BoundsCheck) boundsCheck(array, index);
  return ReadHeapRef(ArrayAddressOfElementAt(array, index), array->obj());
}
```
After:
```cpp
template<bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
ALWAYS_INLINE KRef Kotlin_Array_get_value(KConstRef thiz, KInt index) {
  ArrayHeader* array = const_cast<ArrayHeader*>(thiz->array());
  if (BoundsCheck) boundsCheck(array, index);
  return ReadHeapRef(ArrayAddressOfElementAt(array, index), array->obj());
}
```

**Counts after patch**: 86 `HAS_SAFEPOINT_THROW`, 10 `NO_SAFEPOINT`, llvm19.1.4 CRT-barrier bodies retained.

**Design implication** (user-confirmed): `ReadHeapRef`/`UpdateHeapRef`/etc. now take an optional `thisPtr` (default `nullptr`). v3 callers still compile (use default); CRT callers pass `array->obj()` for object tracking. Drove the Memory.h signature change below.

---

## 4. `kotlin-native/runtime/src/main/cpp/Memory.h` — 4 conflict blocks

**Block 1 (lines 108-124, TypeInfo tag-strip):**
Before:
```cpp
<<<<<<< HEAD
      auto* cleaned = clearPointerBits(typeInfoOrMetaRelaxed(), OBJECT_TAG_MASK);
      cleaned = reinterpret_cast<TypeInfo*>(reinterpret_cast<uintptr_t>(cleaned) & 0xffffffffffff);
      auto atomicTypeInfoPtr = kotlin::std_support::atomic_ref{cleaned->typeInfo_};
=======
      auto typePtr = reinterpret_cast<uintptr_t>(typeInfoOrMetaRelaxed()) & kImmTypeInfoMask;
      auto atomicTypeInfoPtr = kotlin::std_support::atomic_ref{reinterpret_cast<TypeInfo*>(typePtr)->typeInfo_};
>>>>>>> feature/2.2.21-llvm19.1.4
```
After:
```cpp
      auto typePtr = reinterpret_cast<uintptr_t>(typeInfoOrMetaRelaxed()) & kImmTypeInfoMask;
      auto atomicTypeInfoPtr = kotlin::std_support::atomic_ref{reinterpret_cast<TypeInfo*>(typePtr)->typeInfo_};
```

Reason: both expressions compute the same mask `0x0000_FFFF_FFFF_FFFC`; took llvm19.1.4's concise form (uses the `kImmTypeInfoMask` constant). Kept v3's detailed comment about KNStateWord layout.

**Block 2 (lines 281-301, ReadHeapRef + MemoryModel enum):**
Before:
```cpp
<<<<<<< HEAD
// Zeroes heap location.
void ZeroHeapRef(HeapDerivedPtr location) RUNTIME_NOTHROW;
=======
// NOTE: Must match `MemoryModel` in `Platform.kt`
enum class MemoryModel { kStrict = 0, kRelaxed = 1, kExperimental = 2, };
extern const MemoryModel CurrentMemoryModel;
    ObjHeader *ReadHeapRef(ObjHeader** location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
    ObjHeader *ReadVolatileHeapRef(ObjHeader** location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
void ZeroHeapRef(ObjHeader** location, ObjHeader *thisPtr = nullptr) RUNTIME_NOTHROW;
>>>>>>> feature/2.2.21-llvm19.1.4
```
After:
```cpp
// NOTE: Must match `MemoryModel` in `Platform.kt`
enum class MemoryModel { kStrict = 0, kRelaxed = 1, kExperimental = 2, };
extern const MemoryModel CurrentMemoryModel;
// thisPtr is the owning object (used by CRT read barrier; nullptr for CMS).
HeapObjPtr ReadHeapRef(HeapDerivedPtr location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
HeapObjPtr ReadVolatileHeapRef(HeapDerivedPtr location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
void ZeroHeapRef(HeapDerivedPtr location, ObjHeader* thisPtr = nullptr) RUNTIME_NOTHROW;
```

Reason: keep v3's address-space-aware `HeapDerivedPtr`/`HeapObjPtr` typedefs (needed for LLVM IR generation); add llvm19.1.4's `ReadHeapRef`/`ReadVolatileHeapRef` with default `thisPtr=nullptr`. Bring in `MemoryModel` enum (it's referenced by `Memory.cpp` and pairs with Platform.kt's public enum).

**Block 3 (UpdateHeapRef et al.):** added `ObjHeader* thisPtr = nullptr` default to plain functions. For `OBJ_GETTER`-wrapped functions (`CompareAndSwapVolatileHeapRef`, `GetAndSetVolatileHeapRef`), removed `= nullptr` because `OBJ_GETTER` appends an implicit `HeapObjPtr* OBJ_RESULT` parameter and C++ disallows a default-arg parameter followed by a non-default-arg one. Added explanatory comment.

**Block 4 (fp-unwind helpers vs old-style ABI):** kept v3's `RuntimeSetLastFrame*`/`SetLastFrameReliable`; dropped all 14 pairs of `Save/RestoreStackFrame{R2KExportForCppRuntime, K2RK2X, K2NNativeState, K2RSafePoint, R2KInitGlobals, R2KGlobalInitAdapter, R2KWorkerJob, N2KBoxing, N2KDisposeStableRef, N2KIsInstance, N2KUnboxing, N2KClassInstance, N2KEnumEntry, N2KCExport}` declarations from llvm19.1.4. `SaveX28`/`RestoreX28` (outside the conflict, line 364-365) preserved — they're CRT-specific x28 register save, not old-style fp-unwind.

---

## 5. `kotlin-native/runtime/src/mm/cpp/Memory.cpp` — 7 conflict blocks

**Block 1**: inserted llvm19.1.4's `AllocInstanceForCI`/`AllocArrayInstanceForCI` as thin trampolines (deleted the `AllocInstanceFrameGuard` calls — fp-unwind's K2RStub annotation handles transitions). Restored v3's `NO_SAFEPOINT` before `InitAndRegisterGlobal`:

After:
```cpp
HAS_SAFEPOINT
extern "C" RUNTIME_NOTHROW OBJ_GETTER(AllocInstanceForCI, const TypeInfo* typeInfo) {
    // Trampoline to AllocInstance. CRT codegen emits calls to *ForCI; fp-unwind K2RStub
    // handles the K2N transition via HAS_SAFEPOINT, so no FrameGuard needed.
    RETURN_RESULT_OF(AllocInstance, typeInfo);
}

HAS_SAFEPOINT
extern "C" OBJ_GETTER(AllocArrayInstanceForCI, const TypeInfo* typeInfo, int32_t elements) {
    RETURN_RESULT_OF(AllocArrayInstance, typeInfo, elements);
}

NO_SAFEPOINT
extern "C" RUNTIME_NOTHROW void InitAndRegisterGlobal(...) { ... }
```

**Block 2 (ZeroHeapRef / ReadHeapRef + ZeroArrayRefs)**: kept llvm19.1.4's `CurrentMemoryModel` extern and `ReadHeapRef`/`ReadVolatileHeapRef` `checkUseCRT<>` bodies (with fastpath/slowpath); inside the non-CRT lambda, simplified to v3's plain `mm::RefAccessor<false>(location, thisPtr).load()`. Kept v3's `NO_SAFEPOINT` + `PERFORMANCE_INLINE` annotations on `ZeroHeapRef` and `ZeroArrayRefs`. Added comment in non-CRT lambda explaining fp-unwind handles transitions via K2RStub:

```cpp
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW ObjHeader *ReadHeapRef(ObjHeader** location, ObjHeader* thisPtr) {
    return checkUseCRT<CheckMode::Fast>([&] {
#ifdef ENABLE_GC_FASTPATH
        CHECK_READ_BARRIER_SLOW_PATH(rb_slow_path)
        return *location;
#endif
    rb_slow_path:
        return ReadHeapRefSlow<false>(location, thisPtr);
    }, [&] {
        // v3 CMS path: plain load. fp-unwind state transitions handled by K2RStub annotations.
        return mm::RefAccessor<false>(location, thisPtr).load();
    });
}
```

**Block 3 + Block 4 (UpdateHeapRef et al.)**: added `ObjHeader* thisPtr` to v3's signatures (no default in .cpp definitions; the default lives in the .h declaration). Kept `NO_SAFEPOINT` + `PERFORMANCE_INLINE`.

**Block 5 (Kotlin_mm_safePointWhileLoopBody)**: kept v3's HAS_SAFEPOINT + `Kotlin_mm_safePointWhileLoopBodyStub`; llvm19.1.4's drop of the `Stub` variant rejected.

**Block 6 (Kotlin_mm_switchThreadStateRunnable)**: kept v3's `HAS_SAFEPOINT` annotation + `NO_INLINE`.

**Block 7 (RuntimeSetLastFrame1 vs Save/RestoreStackFrameXXX implementations + SaveX28/RestoreX28)**: kept v3's `RuntimeSetLastFrame1`; dropped all 14 `Save/RestoreStackFrameXXX` C++ definitions (118 lines); preserved `SaveX28`/`RestoreX28` with their `globalX28Guard` stack. Added comment explaining x28 is CRT-specific, not fp-unwind.

---

## 6. `kotlin-native/runtime/src/mm/cpp/SafePoint.cpp` — 1 conflict block

Wrapped `safePoint()` body with `checkUseCRT<>([crt], [v3])`. Dropped `SaveStackFrameK2RSafePoint`/`RestoreStackFrameK2RSafePoint` calls from both lambdas (old-style ABI). Also dropped the `needSavedFrame` template/flag.

After:
```cpp
PERFORMANCE_INLINE void mm::safePoint(std::memory_order fastPathOrder) noexcept {
    mm::DisallowSafepointScope::AssertAllowSafepoint(GetMemoryState());
    AssertThreadState(ThreadState::kRunnable);
    checkUseCRT<CheckMode::Fast>([&] {
#ifdef ENABLE_GC_FASTPATH
        // CRT fastpath: x28 holds the TLS pointer.
        uintptr_t tls;
        FixedRegToLocalVar(tls);
        auto mutatorPtr = reinterpret_cast<common::MutatorBase**>(tls + common::TLS_MUTATOR_OFF);
        if (UNLIKELY(*reinterpret_cast<uint32_t*>(*mutatorPtr))) {
            SafePointSlowPath(*mutatorPtr);
        }
#else
        auto* threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        void* tls = common::LoadCachedCRTTLS(threadData->allocator().impl());
        if (UNLIKELY(common::IsSafePointActive(tls))) {
            SafePointSlowPath(threadData->GetThreadHolder()->GetMutator());
        }
#endif
    }, [&] {
        // v3 CMS path: fp-unwind asm stubs handle frame transitions automatically.
        auto action = safePointAction.load(fastPathOrder);
        if (__builtin_expect(action != nullptr, false)) {
            slowPath();
        }
    });
}
```

`mm::safePoint(ThreadData&)` overload preserved with v3's body (no CRT branch; only called outside CRT contexts).

---

## 7. `kotlin-native/runtime/src/main/cpp/Runtime.{cpp,h}` — 3 + 1 conflict blocks

**Runtime.cpp**: combined includes from both sides — kept v3's `ArkTSInit.h` / `KotlinCallScope.h` + OHOS-specific `hidebug/hilog/deviceinfo` and the `RegistDumpListenerIfNeeded` function (v3's HOS-specific dump listener); appended llvm19.1.4's `base/common.h`, `crt/cpp/CRTRuntime.hpp`, `MemoryManagerSwitch.hpp`, `<algorithm>` include, and the `NO_INLINE void initAddressScope()` forward declaration.

**Runtime.h**: dropped both `InitGlobalsFrameGuard` and `AllocInstanceFrameGuard` structs (they reference removed `Save/RestoreStackFrameXXX` symbols). The `AllocInstanceForCI` trampoline in Memory.cpp doesn't need them.

After (Runtime.h tail):
```cpp
extern const char* programName;
}
#endif // RUNTIME_RUNTIME_H
```

---

## 8. `kotlin-native/runtime/src/main/cpp/Natives.cpp` — 1 conflict block

Combined: kept llvm19.1.4's forward declaration of `Kotlin_CRT_GetOrSetHashCode` (needed for CRT mode) + v3's `HAS_SAFEPOINT` annotation on `Kotlin_Any_hashCode`. Dropped the "Fix later after rebase" comment.

---

## 9. `kotlin-native/runtime/src/mm/cpp/ExtraObjectData.cpp` — 1 conflict block

Combined include groups. Added llvm19.1.4's `MemoryManagerSwitch.hpp` and `crt/cpp/KNFinalizer.hpp` includes alongside v3's `ArkTSMMAPI.h` (OHOS-specific). Both sets are needed for runtime-switchable behavior.

---

## 10. `kotlin-native/runtime/src/compiler_interface/cpp/CompilerCInterface.cpp` — 1 conflict block

Dropped all 14 pairs of `touchFunction(Save/RestoreStackFrameXXX)` (old-style ABI). Kept `touchFunction(SaveX28)`/`touchFunction(RestoreX28)` (CRT-specific). Added new CRT entry-point touches:
```cpp
touchFunction(SaveX28)
touchFunction(RestoreX28)
touchFunction(ReadHeapRef)
touchFunction(ReadVolatileHeapRef)
touchFunction(AllocInstanceForCI)
touchFunction(AllocArrayInstanceForCI)
```

---

## 11. `kotlin-native/runtime/src/mm/cpp/ThreadData.hpp` — 2 conflict blocks

**Block 1 (includes)**: kept llvm19.1.4's `MemoryManagerSwitch.hpp` include; dropped `VerifyKotlinStack.hpp` (it's the old-style ABI header).

**Block 2 (private fields)**: kept v3's `disAllowSafepointScopeData_`, `handleScopeData_`, `lastFrameInfo_ { nullptr, FrameStatus::RISKY, nullptr }`. Kept llvm19.1.4's `threadHolder` field (CRT-only). Dropped `funcPCs_`, `lastKotlinFrame_` (old-style fpStack/pcStack tracking).

After:
```cpp
    std::vector<std::pair<ObjHeader**, ObjHeader*>> initializingSingletons_;
    ThreadSuspensionData suspensionData_;
    DisallowSafepointScopeData disAllowSafepointScopeData_;
    HandleScopeData handleScopeData_;
    LastFrameInfo lastFrameInfo_ { nullptr, FrameStatus::RISKY, nullptr };
    // CRT-specific thread holder; nullptr in CMS mode.
    common::ThreadHolder *threadHolder = nullptr;
```

---

## 12. `kotlin-native/runtime/src/mm/cpp/ThreadState.hpp` — 1 conflict block

Dropped llvm19.1.4's `IsSafePointFunctionProloguePc`, `SaveThreadLastKotlinFrame`, `RestoreThreadLastKotlinFrame` inline helpers. They push to v3-removed `pushLastKotlinFrame`/`popLastKotlinFrame` methods (old-style ABI).

---

## 13. `kotlin-native/backend.native/.../konan/llvm/ContextUtils.kt` — 2 conflict blocks

**Block 1**: kept v3's `importRtStubFunction` lazies (`allocInstanceFunctionStub`, `Kotlin_mm_safePointFunctionPrologueStub`, `Kotlin_mm_safePointWhileLoopBodyStub`); ADDED llvm19.1.4's CRT-only entries (`readHeapRefFunction`, `readVolatileHeapRefFunction`, `allocInstanceForCIFunction`, `allocArrayInstanceForCIFunction`).

**Block 2**: kept v3's `setLastFrameReliable` lazy; dropped llvm19.1.4's 26 `Save/RestoreStackFrameXXX` lazies. Kept `saveX28`/`restoreX28`.

---

## 14. `kotlin-native/backend.native/.../konan/llvm/CodeGenerator.kt` — 2 conflict blocks

**Block 1 (immTypeInfoMask)**: both expressions yield the same `0x0000_FFFF_FFFF_FFFC`. Took v3's `LLVMConstInt` form (matches v3's compiler-side mask handling), kept v3's concise comment.

**Block 2 (load function body)**: dispatches to llvm19.1.4's `loadFromCMC` for object fields (CRT read barrier), falls back to v3's plain `applyMemoryOrderAndAlignment(LLVMBuildLoad2(...))` otherwise. Dropped llvm19.1.4's complex opaque-pointer address-space handling (4-level nested if/else) — v3 doesn't need it because v3 callers pre-adjust addresses. Kept llvm19.1.4's `storeStackRef` to slot when `isObjectType && isVar`.

After:
```kotlin
        val isObjectField = isObjectType && thisPtr != codegen.kNullObjHeaderPtr
        val value = if (isObjectField) {
            // CRT read barrier path
            loadFromCMC(address, thisPtr, memoryOrder)
        } else {
            applyMemoryOrderAndAlignment(LLVMBuildLoad2(builder, type, address, name)!!, memoryOrder, alignment)
        }
        if (isObjectType && isVar) {
            val slot = resultSlot ?: alloca(type, isObjectType, variableLocation = null)
            storeStackRef(value, slot)
        }
        return value
```

---

## Next steps (in progress)

- Step 7 — prune residual old-style ABI references in auto-merged files (Worker.cpp, VerifyKotlinStack.hpp, CAdapter*.kt, IrToBitcode.kt, etc.).
- Step 8 — add CRT-variant stubs (`ReadHeapRefStub`, `AllocInstanceForCIStub`, `SafePointSlowPathStub`) to v3's K2RStub.s files.
- Step 9 — rewrite `runtime/src/crt/cpp/KNRootVisitor.cpp` to call `mm::GetStackFrame()` (v3 fp-unwind).
- Step 10 — audit every `checkUseCRT` site.
- Steps 11-13 — submodule init, build, commits.
