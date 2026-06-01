/*
 * Copyright (c) 2026 Huawei Device Co., Ltd.
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
        void* fnPtr, void* a0, void* a1) noexcept;

#endif // RUNTIME_MAIN_ENTER_KOTLIN_FROM_CPP_H