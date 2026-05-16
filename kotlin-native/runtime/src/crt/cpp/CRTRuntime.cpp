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

void initAddressScope()
{
#ifndef _WIN32
    Dl_info info;
    int succ = ::dladdr(reinterpret_cast<void*>(&initAddressScope), &info);
    LOGF_CHECK(succ) << "dladdr fail";
    KEXE_ADDR_START_ = reinterpret_cast<uintptr_t>(info.dli_fbase);

#ifdef __APPLE__
    size_t size = 0;
    auto start = reinterpret_cast<uintptr_t>(::getsegmentdata((mach_header_64*)info.dli_fbase, SEG_DATA, &size));
    KEXE_ADDR_END_ = start + size;
#else
    KEXE_ADDR_END_ = reinterpret_cast<uintptr_t>(&end);;
#endif

#else
#warning "not implement to find section address on Windows"
    KEXE_ADDR_START_ = 0;
    KEXE_ADDR_END_ = 0;
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

    initAddressScope();
    common::RuntimeParam param = common::DefaultRuntimeParam();
    // param.gcParam.enableGC = false;
    // param.gcParam.enableStwGC = true;
    // TODO: heapSize unit is KB (regional_heap.cpp multiplies by KB), so 4*MB = 4GB actual.
    // The code reads as "4MB" but allocates 4GB. Clarify intent and fix value.
    param.heapParam.heapSize = 4ULL * common::MB;
    // KMP lacks foreground/background awareness, so ChangeGCParams() is never called.
    // Set multiplier to foreground value (3.0) at init time.
    param.gcParam.multiplier = 3.0;
    // param.gcParam.gcInterval = 100000;
    // param.gcParam.garbageThreshold = 0.1;
    // param.gcParam.gcThreads = 1;
    // param.gcParam.gcThreshold = 1;
    common::BaseRuntime::GetInstance()->InitFromDynamic(param);
    common::BaseObject::RegisterKotlin(&common::KNBaseObjectOperator::Instance());
    common::BaseRoots::Register<common::LanguageType::KOTLIN>(&common::KNRootsVisitor::Instance());
    common::RegisterFinalizationInterface(&common::KNFinalizationInterface::Instance());
    return true;
}

void DestroyCRTRuntime(MemoryState* currentThread) {
    if (currentThread) {
        // Stop all GC threads before stopping the world to avoid a deadlock:
        // it will wait for all GC threads to terminate, but some might get stuck waiting on stwMutex if the world is stopped already.
        common::Heap::GetHeap().StopGCWork();
        // Avoid still-running threads to access anything we're about to destroy.
        common::BaseRuntime::GetInstance()->GetThreadHolderManager().SuspendAll(currentThread->GetThreadData()->GetThreadHolder());
    }
    common::BaseRuntime::GetInstance()->FiniFromDynamic();
    common::BaseRuntime::DestroyInstance();
}

} // namespace kotlin