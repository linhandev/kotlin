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
#include "Common.h"
#include "ThreadData.hpp"
#include <cstdint>
#include <sstream>
#ifdef KONAN_OHOS
#include <hilog/log.h>
// region Tencent Code
#include <hitrace/trace.h>
// endregion
#endif

namespace kotlin {

enum class FrameType : uint8_t {
    R2K_STUB,
    KONAN_RUN_START_FRAME,
    WORKER_STUB,
    CALL_INIT_GLOBAL_POSSIIBLY_LOCK,
    INIT_OR_DEINIT_GLOBAL_VARIABLES,
    FIND_ASSOCIATED_OBJECT,
    INIT_THREAD_LOCAL,
    INVOKE_C_FUNCTION,
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