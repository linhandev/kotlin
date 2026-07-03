/*
 * Copyright (c) 2025 Huawei Device Co., Ltd.
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

#include "CRTRuntime.hpp"

#include "MemoryPrivate.hpp"
#include "base_runtime.h"
#include "thread/thread_holder_manager.h"
#include "crt/cpp/KNRootVisitor.hpp"
#include "crt/cpp/KNBaseObject.hpp"
#include "crt/cpp/KNFinalizer.hpp"
#include <map>

#ifndef _WIN32
#include <dlfcn.h>
#ifdef __APPLE__
#include <mach-o/getsect.h>
#endif
#endif

namespace kotlin {
// TODO: remove after stack map ready
#if defined(__linux__) or defined(__unix__)
extern "C" char end;
#endif

void InitAddressScope()
{
#ifndef _WIN32
    Dl_info info;
    int succ = ::dladdr(reinterpret_cast<void*>(&InitAddressScope), &info);
    LOGF_CHECK(succ) << "dladdr fail";
    g_kexeAddrStart = reinterpret_cast<uintptr_t>(info.dli_fbase);

#ifdef __APPLE__
    size_t size = 0;
    auto start = reinterpret_cast<uintptr_t>(::getsegmentdata((mach_header_64*)info.dli_fbase, SEG_DATA, &size));
    g_kexeAddrEnd = start + size;
#else
    g_kexeAddrEnd = reinterpret_cast<uintptr_t>(&end);
#endif

#else
#warning "not implement to find section address on Windows"
    g_kexeAddrStart = 0;
    g_kexeAddrEnd = 0;
#endif
}

inline static bool IsEnableSTWGC()
{
    const char* env = std::getenv("CRT_GC_MODE");
    std::string mode = env != nullptr ? std::string(env) : "cmc";
    std::transform(mode.begin(), mode.end(), mode.begin(), ::tolower);
    return mode != "cmc";
}


bool InitCRTRuntime()
{
    static bool initialized = false;
    if (initialized) {
        __builtin_unreachable();
    }
    initialized = true;

    InitAddressScope();
    common::RuntimeParam param = common::DefaultRuntimeParam();
    // param.gcParam.enableGC = false;
    // param.gcParam.enableStwGC = true;
    // heapSize unit is KB (heuristic_gc_policy.cpp multiplies by KB), so 8*MB = 8GB actual.
    // Bumped from 4GB to 8GB: with the CMC semi-space split, only half the reservation
    // is available to the mutator. The previous 4GB → 2GB cap was tripping ForceThrowOOM
    // during COPY phase on burst-allocating benchmarks (3d_raytrace, TestAllocIntArrays).
    param.heapParam.heapSize = 8ULL * common::MB;
    // KMP lacks foreground/background awareness, so ChangeGCParams() is never called.
    // Set multiplier to foreground value (3.0) at init time.
    param.gcParam.multiplier = 3.0;
    // param.gcParam.gcInterval = 100000;
    // param.gcParam.garbageThreshold = 0.1;
    // param.gcParam.gcThreads = 1;
    // param.gcParam.gcThreshold = 1;
    common::BaseRuntime::GetInstance()->InitFromDynamic(param);
    common::BaseObject::RegisterKotlin(&common::KNBaseObjectOperator::Instance());
#ifdef ENABLE_STACKMAP
    // KNRootsVisitor is only declared when stackmap=on (see KNRootVisitor.hpp guard).
    // stackmap=off implies CRT/CMC unused; skip registering the Kotlin root visitor.
    common::BaseRoots::Register<common::LanguageType::KOTLIN>(&common::KNRootsVisitor::Instance());
#endif
    common::RegisterFinalizationInterface(&common::KNFinalizationInterface::Instance());
    return true;
}

void StopCRTGCWork()
{
    common::Heap::GetHeap().StopGCWork();
}

void DestroyCRTRuntime()
{
    common::BaseRuntime::GetInstance()->FiniFromDynamic();
    common::BaseRuntime::DestroyInstance();
}

} // namespace kotlin