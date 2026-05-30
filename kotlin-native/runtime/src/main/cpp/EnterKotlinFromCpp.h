/*
 * Copyright 2010-2026 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

#ifndef RUNTIME_MAIN_ENTER_KOTLIN_FROM_CPP_H
#define RUNTIME_MAIN_ENTER_KOTLIN_FROM_CPP_H

#include "Common.h"

/**
 * Independent N2K asm trampoline used by C++ runtime to call a Kotlin function
 * through a fn-ptr (4 call sites: findAssociatedObject / Init*Global* /
 * CallInitThreadLocal).  Builds a real stub frame whose K2CSlotData slot is
 * preserved across the call and addressable by the walker (so the runtime no
 * longer needs the per-call-site unwindPCStartFor<XXX> PC ranges).
 *
 * Arity covers the actual call sites (max is currentNode->init(int, MemoryState*),
 * 2 args).  Unused arg slots are passed as nullptr.  Return value is whatever
 * the target Kotlin fn returns in x0; cast at the call site as needed.
 *
 * Defined in:
 *   src/main/cpp/aarch64_linux_ohos_stubs/EnterKotlinFromCppStub.s
 *   src/main/cpp/aarch64_macos_stubs/EnterKotlinFromCppStub.s
 *
 * Limitation: on the C++ exception unwind path the post-blr restore is skipped
 * (the asm frame has no dtor).  This leaves lfi as the safe placeholder
 * {null, Reliable, null} that the entry wrote; the GC walker treats this as
 * "no managed top frame" and skips this thread until the next safepoint poll
 * re-publishes lfi.  Short missed-scan window; no SIGSEGV / unbounded walk.
 */
extern "C" RUNTIME_NOTHROW void* EnterKotlinFromCppStub(
        void* fn_ptr, void* a0, void* a1) noexcept;

#endif // RUNTIME_MAIN_ENTER_KOTLIN_FROM_CPP_H