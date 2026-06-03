/*
 * Copyright 2010-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#include "Common.h"
#include "ExternalRCRef.hpp"
#include "TypeInfo.h"
#include "Memory.h"
#include "Types.h"
#include "Runtime.h"
#include "Exceptions.h"
#include "Natives.h"
#include "EnterKotlinFromCpp.h"
#include "KString.h"

#define touchType(type) RUNTIME_EXPORT type touch##type;
#define touchFunction(function) RUNTIME_EXPORT void* touch##function() { return reinterpret_cast<void*>(&::function); }

// Types and functions used by the compiler (at Runtime.kt and ContextUtils.kt)
#ifdef __cplusplus
extern "C" {
#endif

touchType(InitNode);

touchType(TypeInfo)
touchType(ExtendedTypeInfo)
touchType(InterfaceTableRecord)
touchType(AssociatedObjectTableRecord)

touchType(ObjHeader)
touchType(ArrayHeader)
touchType(StringHeader)
touchType(FrameOverlay)

touchFunction(AllocInstance)
touchFunction(AllocArrayInstance)
touchFunction(AllocInstanceForCI)
touchFunction(AllocArrayInstanceForCI)
touchFunction(InitAndRegisterGlobal)
touchFunction(UpdateHeapRef)
touchFunction(UpdateStackRef)
touchFunction(UpdateVolatileHeapRef)
touchFunction(CompareAndSwapVolatileHeapRef)
touchFunction(CompareAndSetVolatileHeapRef)
touchFunction(GetAndSetVolatileHeapRef)
touchFunction(UpdateReturnRef)
touchFunction(ZeroHeapRef)
touchFunction(ZeroArrayRefs)
touchFunction(ReadHeapRef)
touchFunction(ReadVolatileHeapRef)

// Static (global) ref ops. Ported from upstream 33af2848b3c — the Kotlin compiler imports these
// by name from compiler_interface.bc (see ContextUtils.kt::CodegenLlvmHelpers). Without these
// `touchFunction` references the LLVM bitcode wouldn't carry an external declaration and the
// `importRtFunction("ReadStaticRef")` lookup throws "function ReadStaticRef not found".
touchFunction(ReadStaticRef)
touchFunction(ReadVolatileStaticRef)
touchFunction(UpdateStaticRef)
touchFunction(UpdateVolatileStaticRef)
touchFunction(CompareAndSwapVolatileStaticRef)
touchFunction(CompareAndSetVolatileStaticRef)
touchFunction(GetAndSetVolatileStaticRef)

touchFunction(EnterFrame)
touchFunction(LeaveFrame)
touchFunction(SetCurrentFrame)
touchFunction(CheckCurrentFrame)

touchFunction(LookupInterfaceTableRecord)
touchFunction(IsSubtype)
touchFunction(IsSubclassFast)

touchFunction(ThrowException)
touchFunction(Kotlin_getExceptionObject)

touchFunction(AppendToInitializersTail)
touchFunction(CallInitGlobalPossiblyLock)
touchFunction(CallInitThreadLocal)

touchFunction(AddTLSRecord)
touchFunction(LookupTLS)

touchFunction(Kotlin_initRuntimeIfNeeded)
touchFunction(SetLastFrameReliable)
touchFunction(EnterKotlinFromCppStub)

touchFunction(Kotlin_mm_switchThreadStateNative)
touchFunction(Kotlin_mm_switchThreadStateNative_debug)
touchFunction(Kotlin_mm_switchThreadStateRunnable)
touchFunction(Kotlin_mm_switchThreadStateRunnable_debug)
touchFunction(Kotlin_mm_safePointFunctionPrologue)
touchFunction(Kotlin_mm_safePointWhileLoopBody)

touchFunction(Kotlin_processObjectInMark)
touchFunction(Kotlin_processArrayInMark)
touchFunction(Kotlin_processEmptyObjectInMark)

touchFunction(Kotlin_arrayGetElementAddress)
touchFunction(Kotlin_intArrayGetElementAddress)
touchFunction(Kotlin_longArrayGetElementAddress)

touchFunction(Kotlin_mm_createRetainedExternalRCRef)
touchFunction(Kotlin_mm_releaseExternalRCRef)
touchFunction(Kotlin_mm_disposeExternalRCRef)

// CRT-specific x28 register save/restore (not part of fp-unwind).
touchFunction(SaveX28)
touchFunction(RestoreX28)

#ifdef __cplusplus
} // extern "C"
#endif
