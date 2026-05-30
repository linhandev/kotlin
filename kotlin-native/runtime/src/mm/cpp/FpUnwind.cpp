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

// FpUnwind impl bodies stay defined in OFF (small overhead); see the note in
// FpUnwind.h on why they can't be #ifdef-gated out (klib cstubs.bc bake in
// references) until platform klibs are regenerated in OFF mode.
#include "FpUnwind.h"
#include "Common.h"
#include "ThreadData.hpp"
#include <cstdint>
#include <sstream>
#include <iostream>
#ifdef KONAN_OHOS
#include <hilog/log.h>
#endif

#ifdef ENABLE_STACKMAP
// unwindPC* are provided by the arm64 asm trampolines (K2RStub.s / N2KStub.s
// / KonanStartStub.s) and by inline-asm labels in Worker.cpp / Runtime.cpp /
// Types.cpp. On non-arm64 OFF targets none of these asm-stub PC anchors
// exist, so the FpUnwind-based precise stack walk is unreachable. The
// Is*Stub / IsAt* helpers below that read these globals are likewise gated.
//
// On macOS, unwindPCForN2KStub and unwindPCForKonanStartStub are .quad
// pointers in __DATA,__const (to avoid non-private labels inside CFI regions
// which cause compact-unwind encoding=0). Their *value* is the PC address.
// On OHOS/Linux, they are code labels whose *address* is the PC.
extern uintptr_t unwindPCForN2KStub;
extern uintptr_t unwindPCForKonanStartStub;
extern uintptr_t unwindPCForK2RStubStart;
extern uintptr_t unwindPCForK2RStubEnd;
// Independent N2K trampoline for C++-side fn-ptr calls into Kotlin
// (findAssociatedObject / Init*Global* / CallInitThreadLocal / Worker job).
// Recognised as an R2K_STUB frame; slot data is at stub_fp + OFFSET_K2C_SLOT_DATA,
// identical to the N2K stub layout.  Replaces the per-call-site PC ranges that
// used to live here.
extern uintptr_t unwindPCForEnterKotlinFromCppStub;
#endif // ENABLE_STACKMAP

namespace kotlin {

ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameAddress *GetLastFrameWithThreadData(mm::ThreadData& threadData) noexcept
{
    auto *lastFrame = threadData.GetLastFrameInfo().lastFrame;
    return lastFrame;
}

ALWAYS_INLINE RUNTIME_NOTHROW uint32_t *GetLastPC(mm::ThreadData& threadData) noexcept
{
    auto *lastFrame = threadData.GetLastFrameInfo().lastPC;
    return lastFrame;
}

ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameAddress *GetLastFrame() noexcept
{
    if (!mm::IsCurrentThreadRegistered()) {
        return nullptr;
    }
    auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    return threadData->GetLastFrameInfo().lastFrame;
}

ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameStatus GetFrameStatusWithThreadData(mm::ThreadData& threadData) noexcept
{
    return threadData.GetLastFrameInfo().status;
}

ALWAYS_INLINE RUNTIME_NOTHROW mm::FrameStatus GetFrameStatus() noexcept
{
    if (!mm::IsCurrentThreadRegistered()) {
        return mm::FrameStatus::RISKY;
    }
    auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    return threadData->GetLastFrameInfo().status;
}

constexpr int64_t OFFSET_K2C_SLOT_DATA = 1;

extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void SetLastFrameRisky(mm::FrameAddress *fp) noexcept
{
    if (!mm::IsCurrentThreadRegistered()) {
        return;
    }
    auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData->SetLastFrameInfo({ fp, mm::FrameStatus::RISKY, nullptr });
}

extern "C" ALWAYS_INLINE RUNTIME_NOTHROW RUNTIME_EXPORT void SetLastFrameReliable() noexcept
{
    if (!mm::IsCurrentThreadRegistered()) {
        return;
    }
    auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData->SetLastFrameInfo({ nullptr, mm::FrameStatus::RELIABLE, nullptr });
}

// invoke before enter kotlin (called from N2K stub entry, KonanStart stub)
//
// Fix for GC scan race: transition to Runnable BEFORE writing lastFrameInfo_.
// If the thread was Native, setState(Runnable) goes through safePoint; if GC has
// requested suspension, we block here until the scan completes, so the scanner never
// reads lastFrameInfo_ concurrently with our write.
extern "C" RUNTIME_NOTHROW RUNTIME_EXPORT void SaveLastFrameAndStatus(mm::FrameAddress *fp) noexcept
{
    K2CSlotData *data = reinterpret_cast<K2CSlotData*>(fp + OFFSET_K2C_SLOT_DATA);
    // Default to kNative for unregistered threads: they are not in a Kotlin-managed
    // Runnable state, so the conceptually correct "state to restore to" is Native.
    data->prevThreadState = static_cast<uint8_t>(ThreadState::kNative);
    if (mm::IsCurrentThreadRegistered()) {
        auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
        auto prev = threadData->suspensionData().setState(ThreadState::kRunnable);
        data->prevThreadState = static_cast<uint8_t>(prev);
    }
    data->fa = GetLastFrame();
    data->status = GetFrameStatus();
    SetLastFrameReliable();
}

// invoke after leave kotlin (called from N2K stub exit, KonanStart stub)
extern "C" RUNTIME_NOTHROW RUNTIME_EXPORT void RestoreLastFrameAndStatus(mm::FrameAddress *fp) noexcept
{
    if (!mm::IsCurrentThreadRegistered()) {
        return;
    }
    K2CSlotData *data = reinterpret_cast<K2CSlotData*>(fp + OFFSET_K2C_SLOT_DATA);
    auto *threadData = mm::ThreadRegistry::Instance().CurrentThreadData();
    threadData->SetLastFrameInfo({ data->fa, data->status, nullptr });
    // Roll ThreadState back to what it was before this stub entered Kotlin.
    auto prevState = static_cast<ThreadState>(data->prevThreadState);
    if (prevState == ThreadState::kNative) {
        threadData->suspensionData().setStateNoSafePoint(prevState);
    }
}

} // namespace kotlin

namespace kotlin { // re-open for the rest of the file

#ifdef ENABLE_STACKMAP
// All frame-type predicates + GetStackFrame are stackmap-pipeline only:
// every Is* helper reads an unwindPC* extern that exists only on arm64
// (provided by K2RStub.s / N2KStub.s / KonanStartStub.s / inline-asm labels).
// GetStackFrame is invoked only from ConcurrentMark.cpp:tryCollectRootSet and
// KNRootVisitor.cpp, both of which are themselves gated under ENABLE_STACKMAP,
// so dropping this block on non-arm64 OFF builds leaves no caller dangling.
static bool IsR2KStub(const uint32_t* ip)
{
    // EnterKotlinFromCppStub shares the K2CSlotData layout (slot at stub_fp+16),
    // so the walker treats its frame the same as a regular N2K stub frame.
#ifdef __APPLE__
    // On macOS the symbols are .quad pointers; compare against their values.
    auto ipv = reinterpret_cast<uintptr_t>(ip);
    return ipv == unwindPCForN2KStub
        || ipv == unwindPCForEnterKotlinFromCppStub;
#else
    auto ipv = reinterpret_cast<uintptr_t>(ip);
    return ipv == reinterpret_cast<uintptr_t>(&unwindPCForN2KStub)
        || ipv == reinterpret_cast<uintptr_t>(&unwindPCForEnterKotlinFromCppStub);
#endif
}

static bool IsK2RStub(const uint32_t* ip)
{
    return reinterpret_cast<uintptr_t>(ip) > reinterpret_cast<uintptr_t>(&unwindPCForK2RStubStart) &&
            reinterpret_cast<uintptr_t>(ip) < reinterpret_cast<uintptr_t>(&unwindPCForK2RStubEnd);
}

static bool IsKonanRunStartFrame(const uint32_t* ip)
{
#ifdef __APPLE__
    return reinterpret_cast<uintptr_t>(ip) == unwindPCForKonanStartStub;
#else
    return reinterpret_cast<uintptr_t>(ip) == reinterpret_cast<uintptr_t>(&unwindPCForKonanStartStub);
#endif
}

[[maybe_unused]]static std::string GetFrameTypeName(FrameType frameType)
{
    switch (frameType) {
        case FrameType::R2K_STUB:
            return "R2K_STUB";
        case FrameType::K2N_STUB:
            return "K2N_STUB";
        case FrameType::K2R_STUB:
            return "K2R_STUB";
        case FrameType::RUNTIME_FRAME:
            return "RUNTIME_FRAME";
        case FrameType::KOTLIN_FRAME:
            return "KOTLIN_FRAME";
        case FrameType::KONAN_RUN_START_FRAME:
            return "KONAN_RUN_START_FRAME";
        default:
            return "Unknown";
    }
}

#define DUMP_UNWIND_FRAME_INFO 0

static void PrintStackInfo(FrameType curFrameType, FrameInfo info)
{
#if DUMP_UNWIND_FRAME_INFO
#ifdef KONAN_OHOS
    std::stringstream ss;
    ss  << "      --- unwind log current frametype: " << GetFrameTypeName(curFrameType)
              << ", prevfp: " << std::hex << reinterpret_cast<uintptr_t>(info.fa)
              << ", previp: " << reinterpret_cast<uintptr_t>(info.ip) << std::dec << std::endl;
    OH_LOG_Print(LOG_APP, LOG_INFO, LOG_DOMAIN, "Konan_main", "%{public}s", ss.str().c_str());
#else // ~KONAN_OHOS
    std::cout << "      current frametype: " << GetFrameTypeName(curFrameType)
              << ", prevfp: " << std::hex << reinterpret_cast<uintptr_t>(info.fa)
              << ", previp: " << reinterpret_cast<uintptr_t>(info.ip) << std::dec << std::endl;
#endif // ~KONAN_OHOS
#endif // ~DUMP_UNWIND_FRAME_INFO
}

static inline void LogUnwindStart(mm::ThreadData& threadData, const FrameInfo& info)
{
#if DUMP_UNWIND_FRAME_INFO
#ifdef KONAN_OHOS
    std::stringstream ss;
    ss << "------unwind log unwind start, threadData: " << std::hex << reinterpret_cast<uintptr_t>(&threadData)
       << ", fp: " << reinterpret_cast<uintptr_t>(info.fa)
       << ", pc: " << reinterpret_cast<uintptr_t>(GetLastPC(threadData));
    OH_LOG_Print(LOG_APP, LOG_INFO, LOG_DOMAIN, "Konan_main", "%{public}s", ss.str().c_str());
#else // ~KONAN_OHOS
        std::cout << "------unwind start, threadData: " << std::hex
                << reinterpret_cast<uintptr_t>(&threadData)
                << ", fp: " << reinterpret_cast<uintptr_t>(info.fa)
                << ", pc: " << reinterpret_cast<uintptr_t>(pc)  << std::dec << std::endl;
#endif // ~KONAN_OHOS
#endif // ~DUMP_UNWIND_FRAME_INFO
}

static void UnwindR2KStub(FrameInfo& info, mm::FrameAddress* curFp)
{
    K2CSlotData* data = reinterpret_cast<K2CSlotData*>(curFp + OFFSET_K2C_SLOT_DATA);
    if (data->status == mm::FrameStatus::RELIABLE) {
        info.fa = curFp->prevThreadState;
        info.ip = curFp->returnAddr;
        info.type = IsK2RStub(info.ip) ? FrameType::K2R_STUB : FrameType::RUNTIME_FRAME;
    } else {
        info.type = FrameType::K2N_STUB;
        info.fa = data->fa;
        info.ip = nullptr;
    }
}

static void UnwindK2NOrK2RStub(FrameInfo& info, mm::FrameAddress* curFp, std::vector<FrameInfo>& stack)
{
    info.type = FrameType::KOTLIN_FRAME;
    info.fa = curFp->prevThreadState;
    info.ip = curFp->returnAddr;
    stack.push_back(info);
}

static void UnwindRuntimeFrame(FrameInfo& info, mm::FrameAddress* curFp)
{
    info.type = IsK2RStub(curFp->returnAddr) ? FrameType::K2R_STUB : FrameType::RUNTIME_FRAME;
    info.fa = curFp->prevThreadState;
    info.ip = curFp->returnAddr;
}

static void UnwindKotlinFrame(FrameInfo& info, mm::FrameAddress* curFp, std::vector<FrameInfo>& stack)
{
    info.fa = curFp->prevThreadState;
    info.ip = curFp->returnAddr;

    if (IsR2KStub(curFp->returnAddr)) {
        info.type = FrameType::R2K_STUB;
    } else if (IsKonanRunStartFrame(curFp->returnAddr)) {
        info.type = FrameType::KONAN_RUN_START_FRAME;
    } else {
        info.type = FrameType::KOTLIN_FRAME;
        stack.push_back(info);
    }
}

static void UnwindSpecialFrame(FrameInfo& info, FrameType currentType, std::vector<FrameInfo>& stack)
{
    switch (currentType) {
        case FrameType::KONAN_RUN_START_FRAME:
            if (!stack.empty()) stack.pop_back();
            info.fa = nullptr;
            info.type = FrameType::KONAN_RUN_START_FRAME;
            break;
        default:
            RuntimeAssert(0, "Unexpected special frame type");
    }
}

std::vector<FrameInfo> GetStackFrame(mm::ThreadData& threadData)
{
    std::vector<FrameInfo> stack;
    FrameInfo info;
    // Snapshot all three fields (lastFrame, lastPC, status) in ONE copy so that
    // GetLast*() reads can't straddle a mutator's piece-by-piece update of
    // lastFrameInfo_ (e.g., RuntimeSetLastFrame writes lastFrame then lastPC).
    mm::LastFrameInfo lastFrameInfo = threadData.GetLastFrameInfo();
    info.fa = lastFrameInfo.lastFrame;
    LogUnwindStart(threadData, info);
    if (info.fa == nullptr) return stack;

    if (lastFrameInfo.status == mm::FrameStatus::RELIABLE) {
        info.type = IsK2RStub(lastFrameInfo.lastPC) ? FrameType::K2R_STUB : FrameType::RUNTIME_FRAME;
    } else {
        info.type = FrameType::K2N_STUB;
    }

    FrameType currentFrameType = info.type;

    while (info.fa != nullptr) {
        currentFrameType = info.type;
        mm::FrameAddress* curFp = info.fa;

        switch (info.type) {
            case FrameType::R2K_STUB:
                UnwindR2KStub(info, curFp);
                break;
            case FrameType::K2N_STUB:
            case FrameType::K2R_STUB:
                UnwindK2NOrK2RStub(info, curFp, stack);
                break;
            case FrameType::RUNTIME_FRAME:
                UnwindRuntimeFrame(info, curFp);
                break;
            case FrameType::KOTLIN_FRAME:
                UnwindKotlinFrame(info, curFp, stack);
                break;
            case FrameType::KONAN_RUN_START_FRAME:
                UnwindSpecialFrame(info, currentFrameType, stack);
                break;
            default:
                RuntimeAssert(0, "Unknown frame type");
        }

        PrintStackInfo(currentFrameType, info);
    }

    return stack;
}
#endif // ENABLE_STACKMAP

} // namespace kotlin