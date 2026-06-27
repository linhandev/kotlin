/*
 * Copyright (C) 2026 Huawei Device Co., Ltd.
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

#ifndef RUNTIME_MM_FPUNWIND_H
#define RUNTIME_MM_FPUNWIND_H
// FpUnwind impl bodies are intentionally NOT #ifdef-gated out of the OFF
// build: the runtime is shipped compiled with ENABLE_STACKMAP (it is a
// per-target build macro, ON for ohos_arm64/macos_arm64), so the same runtime
// bitcode is linked into both ON and OFF apps. Rather than ship two runtime
// variants, the unwindPC* marker references below are declared *weak* (see the
// ENABLE_STACKMAP block): in OFF the Linker drops the asm stub .o (OHOS), the
// weak refs resolve to 0, and the Is*Stub predicates never match — identical
// runtime behaviour to ON-linked-but-never-executed stub code, with no asm
// stub objects pulled into the link.

#include "Common.h"
#include "ThreadData.hpp"
#include <cstdint>
#include <sstream>
#ifdef KONAN_OHOS
#include <hilog/log.h>
#endif

#ifdef ENABLE_STACKMAP
// unwindPC* are provided by the arm64 asm trampolines (K2RStub.s / N2KStub.s
// / KonanStartStub.s / EnterKotlinFromCppStub.s) and by inline-asm labels in
// Worker.cpp / Runtime.cpp / Types.cpp. On non-arm64 OFF targets none of these
// asm-stub PC anchors exist, so the FpUnwind-based precise stack walk is
// unreachable. The Is*Stub / IsAt* helpers below that read these globals are
// likewise gated.
//
// On macOS, unwindPCForN2KStub and unwindPCForKonanStartStub are .quad
// pointers in __DATA,__const (to avoid non-private labels inside CFI regions
// which cause compact-unwind encoding=0). Their *value* is the PC address.
// On OHOS/Linux, they are code labels whose *address* is the PC.
//
// Declared weak so the OFF link (which drops the asm stub .o on OHOS, see
// Linker.stubObjectsForTarget) resolves them to 0 instead of failing on
// undefined symbols. OHOS reads them address-form (&marker) so 0 is safe — the
// Is*Stub predicates just never match. macOS reads them value-form, so the
// Linker keeps the stub .o there and these stay strongly defined.
extern uintptr_t unwindPCForN2KStub __attribute__((weak));
extern uintptr_t unwindPCForKonanStartStub __attribute__((weak));
extern uintptr_t unwindPCForK2RStubStart __attribute__((weak));
extern uintptr_t unwindPCForK2RStubEnd __attribute__((weak));
extern uintptr_t unwindPCForEnterKotlinFromCppStub __attribute__((weak));
#endif // ENABLE_STACKMAP

namespace kotlin {

enum class FrameType : uint8_t {
    R2K_STUB,
    KONAN_RUN_START_FRAME,
    K2N_STUB,
    K2R_STUB,
    RUNTIME_FRAME,
    KOTLIN_FRAME
};

struct FrameInfo {
    FrameType type;
    mm::FrameAddress *fa;
    const uint32_t* ip;
};

struct K2CSlotData {
    const uint32_t* pc;
    mm::FrameAddress* fa;
    mm::FrameStatus status;
    // ThreadState before the N2K stub entered Kotlin, so we can roll it back on exit.
    // Lives in the padding that follows `status` (both uint8_t); struct size unchanged.
    uint8_t prevThreadState;
};

ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameAddress *GetLastFrameWithThreadData(mm::ThreadData& threadData);
ALWAYS_INLINE RUNTIME_NOTHROW uint32_t *GetLastPC(mm::ThreadData& threadData);
ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameAddress *GetLastFrame();
ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameStatus GetFrameStatusWithThreadData(mm::ThreadData& threadData);
ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameStatus GetFrameStatus();
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void SetLastFrameRisky(mm::FrameAddress *fp);
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void SetLastFrameReliable();
// invoke before enter kotlin
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void SaveLastFrameAndStatus(mm::FrameAddress *fp);
// invoke after leave kotlin
extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void RestoreLastFrameAndStatus(mm::FrameAddress *fp);
std::vector<FrameInfo> GetStackFrame(mm::ThreadData& threadData);
} // namespace kotlin

#endif // RUNTIME_MM_FPUNWIND_H