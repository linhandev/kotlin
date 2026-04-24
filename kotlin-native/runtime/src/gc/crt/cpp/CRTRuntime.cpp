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

#include "log/log.h"
#include "common_components/common_runtime/base_runtime_param.h"
#include "CRTRuntime.hpp"
#include "Logging.hpp"
#include "../../alloc/crt/cpp/KNRootVisitor.hpp"
#include "../../alloc/crt/cpp/KNBaseObject.hpp"
#include "../../alloc/crt/cpp/KNFinalizer.hpp"
#include <map>

// Provide definitions for Log static members since they're not exported from libcrt.so
namespace common {
Level Log::level_ = Level::ERROR;
ComponentMark Log::components_ = static_cast<ComponentMark>(Component::ALL);
}

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

static void initAddressScope() {
#ifndef _WIN32
    Dl_info info;
    int succ = ::dladdr((void*)&initAddressScope, &info);
    LOGF_CHECK(succ) << "dladdr fail";
    KEXE_ADDR_START_ = reinterpret_cast<uintptr_t>(info.dli_fbase);

#ifdef __APPLE__
    size_t size = 0;
    auto start = reinterpret_cast<uintptr_t>(::getsegmentdata((mach_header_64*)info.dli_fbase, SEG_DATA, &size));
    KEXE_ADDR_END_ = start + size;
#else
    KEXE_ADDR_END_ = (uintptr_t)&end;
#endif

#else
#warning "not implement to find section address on Windows"
    KEXE_ADDR_START_ = 0;
    KEXE_ADDR_END_ = 0;
#endif
}

namespace crt {
static std::map<std::string, Level> logLevels = {
    {"debug", Level::DEBUG},
    {"info", Level::INFO},
    {"fatal", Level::FATAL},
    {"fatal_without_abort", Level::FATAL_WITHOUT_ABORT},
    {"verbose", Level::VERBOSE},
    {"warn", Level::WARN},
    {"error", Level::ERROR},
};
} // namespace crt

inline static void InitLog() {
  const char* env = std::getenv("CRT_LOG_LEVEL");
  std::string logLevelStr = env != nullptr ? std::string(env) : "error";
  std::transform(logLevelStr.begin(), logLevelStr.end(), logLevelStr.begin(), ::tolower);
  common::LogOptions options = {
    .level = crt::logLevels[logLevelStr],
    .component = static_cast<ComponentMark>(Component::ALL),
  };
  common::Log::Initialize(options);
}

inline static bool IsEnableSTWGC() {
  const char* env = std::getenv("CRT_GC_MODE");
  std::string mode = env != nullptr ? std::string(env) : "cmc";
  std::transform(mode.begin(), mode.end(), mode.begin(), ::tolower);
  return mode != "cmc";
}


bool InitCRTRuntime() {
    static bool initialized = false;
    if (initialized) {
        __builtin_unreachable();
    }
    initialized = true;

    initAddressScope();
    common::RuntimeParam param = common::BaseRuntimeParam::DefaultRuntimeParam();
    // param.gcParam.enableGC = false;
    // param.gcParam.enableStwGC = true;
    param.heapParam.heapSize = 4ULL * common::MB;
    // param.gcParam.gcInterval = 100000;
    // param.gcParam.garbageThreshold = 0.1;
    // param.gcParam.gcThreads = 1;
    // param.gcParam.gcThreshold = 1;
    common::BaseRuntime::GetInstance()->InitFromDynamic(param);
    common::BaseObject::RegisterKotlin(&common::KNBaseObjectOperator::Instance());
    common::BaseRoots::Register<common::LanguageType::KOTLIN>(&common::KNRootsVisitor::Instance());
    common::BaseFinalizerProcessor::RegisterFinalizationInterface(&common::KNFinalizationInterface::Instance());
    // InitLog();
    return true;
}

} // namespace kotlin
