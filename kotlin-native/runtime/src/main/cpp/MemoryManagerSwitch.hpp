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

#include "CRTFastpathUtils.hpp"
#include "CRTStubs.hpp"
#include "KAssert.h"

namespace MemoryManagerSwitch {
    inline bool IsEnabled()
    {
#ifndef ENABLE_CRT
        return false;
#else
        const char* v = std::getenv("USE_CRT");
        if (v && v[0] == '0' && v[1] == '\0') {
            return false;
        }
        return true;
#endif
    }
    inline const bool useCRT = IsEnabled();
};

// not ALWAYS_INLINE to ensure that inline happens both in debug and release mode
#define FORCE_INLINE __attribute__((always_inline)) inline

/// `Slow` mode can be used in any place, but it will load a value to check from a global variable.
/// `Fast` is only available in kRunnable state. In runtime-switch mode, the CMS
/// branch is selected from `useCRT` without reading x28; the CRT branch still
/// relies on x28 being initialized and consistent with that global state.
enum class CheckMode { Slow, Fast };

/// If currently selected MemoryManager is CRT then execute given `crt_f`, otherwise execute `else_f`.
template<CheckMode mode, typename F, typename G>
FORCE_INLINE auto checkUseCRT(F crt_f, G else_f)
{
#ifndef ENABLE_CRT
    (void)crt_f;
    return else_f();
#else
    using namespace kotlin;

    // First check if the memory manager to use is defined via compile-time option.
    if (compiler::memoryManagerMode() == compiler::MemoryManagerMode::kCRT) { return crt_f(); }
    if (compiler::memoryManagerMode() == compiler::MemoryManagerMode::kNative) { return else_f(); }

    // Otherwise we are to select proper MM based on the run-time option.
    RuntimeAssert(compiler::memoryManagerMode() == compiler::MemoryManagerMode::kRuntimeSwitch,
        "Unexpected memory manager mode %d", compiler::memoryManagerMode());

#ifdef ENABLE_GC_FASTPATH
    if constexpr (mode == CheckMode::Fast) {
        if (!MemoryManagerSwitch::useCRT) {
            return else_f();
        }
        RuntimeAssert(common::ThreadLocalRegisterData() != common::CallToFFixedX28::MAGIC_MARKER,
            "Value of x28 is a magic marker, check that there is a switch to kRunnable "
            "prior to checkUseCRT<Fast>");
        FAST_CHECK_MM_SWITCH(else_l); // fallthrough if x28 != 0 signifying that CRT MM is enabled, otherwise jump to `else_l`.
        RuntimeAssert(MemoryManagerSwitch::useCRT, "Value of x28 is inconsistent with the useCRT flag");
        return crt_f();
    }
#endif

    if (__builtin_expect(MemoryManagerSwitch::useCRT, true)) { return crt_f(); }

else_l:
#ifdef ENABLE_GC_FASTPATH
    if constexpr (mode == CheckMode::Fast) {
        RuntimeAssert(!MemoryManagerSwitch::useCRT, "Value of x28 is inconsistent with the useCRT flag");
    }
#endif
    return else_f();
#endif
}

/// If currently selected MemoryManager is CRT then execute given `crt_f`, otherwise do nothing.
template<CheckMode mode, typename F>
FORCE_INLINE void checkUseCRT(F crt_f)
{
    checkUseCRT<mode>(crt_f, [] {});
}

/// If currently selected MemoryManager is CRT then do nothing, otherwise execute given `else_f`.
template<CheckMode mode, typename G>
FORCE_INLINE void checkNotCRT(G else_f)
{
    checkUseCRT<mode>([]{}, else_f);
}

/// Assert that currently selected MemoryManager is not CRT.
FORCE_INLINE void assertNotCRT()
{
    checkUseCRT<CheckMode::Slow>([] {
        RuntimeAssert(false, "Reached a statement which should only be reachable when CRT is disabled");
        if (kotlin::compiler::memoryManagerMode() != kotlin::compiler::MemoryManagerMode::kRuntimeSwitch) {
            // Only abort if the MM mode can be determined in compile-time to facilitate UCE below the std::abort(),
            // otherwise if assertions are disabled and the MM is not known in compile-time this lambda will be empty
            // so the run-time check won't be generated.
            std::abort();
        }
    });
}

/// Assert that currently selected MemoryManager is CRT.
FORCE_INLINE void assertUseCRT()
{
    checkNotCRT<CheckMode::Slow>([] {
        RuntimeAssert(false, "Reached a statement which should only be reachable when CRT is enabled");
        if (kotlin::compiler::memoryManagerMode() != kotlin::compiler::MemoryManagerMode::kRuntimeSwitch) {
            // Only abort if the MM mode can be determined in compile-time to facilitate UCE below the std::abort(),
            // otherwise if assertions are disabled and the MM is not known in compile-time this lambda will be empty
            // so the run-time check won't be generated.
            std::abort();
        }
    });
}

#undef FORCE_INLINE
#endif //KOTLIN_NATIVE_MMSWITCH_HPP
