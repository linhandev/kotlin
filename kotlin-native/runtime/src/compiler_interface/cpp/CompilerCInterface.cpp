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
#ifdef USE_CRT
touchFunction(ReadHeapRef)
#endif

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
touchFunction(SaveStackFrameR2KExportForCppRuntime)
touchFunction(RestoreStackFrameR2KExportForCppRuntime)

touchFunction(SaveStackFrameR2KInitGlobals)
touchFunction(RestoreStackFrameR2KInitGlobals)

touchFunction(SaveStackFrameR2KGlobalInitAdapter)
touchFunction(RestoreStackFrameR2KGlobalInitAdapter)

touchFunction(SaveStackFrameR2KWorkerJob)
touchFunction(RestoreStackFrameR2KWorkerJob)

touchFunction(SaveStackFrameK2RK2X)
touchFunction(RestoreStackFrameK2RK2X)

touchFunction(SaveStackFrameK2NNativeState)
touchFunction(RestoreStackFrameK2NNativeState)

touchFunction(SaveStackFrameN2KBoxing)
touchFunction(RestoreStackFrameN2KBoxing)

touchFunction(SaveStackFrameN2KDisposeStableRef)
touchFunction(RestoreStackFrameN2KDisposeStableRef)

touchFunction(SaveStackFrameN2KIsInstance)
touchFunction(RestoreStackFrameN2KIsInstance)

touchFunction(SaveStackFrameN2KUnboxing)
touchFunction(RestoreStackFrameN2KUnboxing)

touchFunction(SaveStackFrameN2KClassInstance)
touchFunction(RestoreStackFrameN2KClassInstance)

touchFunction(SaveStackFrameN2KEnumEntry)
touchFunction(RestoreStackFrameN2KEnumEntry)

touchFunction(SaveStackFrameN2KCExport)
touchFunction(RestoreStackFrameN2KCExport)
touchFunction(RestoreStackFrameN2KCExportCatch)

#ifdef __cplusplus
} // extern "C"
#endif
