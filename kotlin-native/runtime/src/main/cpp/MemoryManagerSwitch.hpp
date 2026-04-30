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

#ifndef KOTLIN_NATIVE_MMSWITCH_HPP
#define KOTLIN_NATIVE_MMSWITCH_HPP

#include "KAssert.h"

// not ALWAYS_INLINE to ensure that inline happens both in debug and release mode
#define FORCE_INLINE __attribute__((always_inline)) inline

/// `Slow` mode can be used in any place, but it will load a value to check from a global variable.
/// `Fast` is only available in kRunnable state and relies on the value of x28 register
/// being consistent with the global var.
enum class CheckMode { Slow, Fast };

/// If currently selected MemoryManager is CRT then execute given `crt_f`, otherwise execute `else_f`.
template<CheckMode mode, typename F, typename G>
FORCE_INLINE auto checkUseCRT(F crt_f, G else_f)
{
    using namespace kotlin;

    // First check if the memory manager to use is defined via compile-time option.
    if (compiler::memoryManagerMode() == compiler::MemoryManagerMode::kCRT) { return crt_f(); }
    if (compiler::memoryManagerMode() == compiler::MemoryManagerMode::kNative) { return else_f(); }

    // Otherwise we are to select proper MM based on the run-time option.
    RuntimeAssert(compiler::memoryManagerMode() == compiler::MemoryManagerMode::kRuntimeSwitch,
        "Unexpected memory manager mode %d", compiler::memoryManagerMode());

    TODO("support run-time check for the MM mode");
}

/// If currently selected MemoryManager is CRT then execute given `crt_f`, otherwise do nothing.
template<CheckMode mode, typename F>
FORCE_INLINE void checkUseCRT(F crt_f)
{
    checkUseCRT<mode>(crt_f, [] {});
}

/// If currently selected MemoryManager is CRT then crash, otherwise fall through to the next line in caller.
template<CheckMode mode = CheckMode::Slow>
FORCE_INLINE void assertNotCRT()
{
    checkUseCRT<mode>([] {
        RuntimeAssert(false, "Reached a statement which should only be reachable when CRT is disabled");
        std::abort();
    });
}

/// If currently selected MemoryManager is CRT then fall through to the next line in caller, otherwise crash.
template<CheckMode mode = CheckMode::Slow>
FORCE_INLINE void assertUseCRT()
{
    checkUseCRT<mode>([] {}, [] {
        RuntimeAssert(false, "Reached a statement which should only be reachable when CRT is enabled");
        std::abort();
    });
}

#undef FORCE_INLINE
#endif //KOTLIN_NATIVE_MMSWITCH_HPP
