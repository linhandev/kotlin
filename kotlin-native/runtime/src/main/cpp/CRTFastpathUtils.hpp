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
#ifndef CRT_ALLOC_CPP_CRTFASTPATHUTILS_HPP_
#define CRT_ALLOC_CPP_CRTFASTPATHUTILS_HPP_

#include "KAssert.h"

namespace kotlin::mm {
class ThreadData;
}

namespace common {
class Mutator;
class MutatorBase;
class ThreadHolder;

#ifdef ENABLE_GC_FASTPATH

#define FixedRegToLocalVar(var) \
    __asm__ volatile("mov %0, x28" : "=r"(var));

#define LocalVarToFixedReg_UNCHECKED(var) \
    __asm__ volatile("mov x28, %0" : : "r"(var)); \

#define AssertionsEnabled \
    (::kotlin::compiler::runtimeAssertsMode() != ::kotlin::compiler::RuntimeAssertsMode::kIgnore)

#endif

/// An instance of this type must be created at the earliest point reachable from code
/// which is NOT compiled with -ffixed-x28.
struct CallToFFixedX28 {
    static constexpr uintptr_t MAGIC_MARKER = 0x28EE01505E70DEAD; // "x28 EE is set dead"

    CallToFFixedX28(CallToFFixedX28&&) = delete;
    CallToFFixedX28(const CallToFFixedX28&) = delete;
    CallToFFixedX28& operator=(const CallToFFixedX28&) = delete;
    CallToFFixedX28& operator=(CallToFFixedX28&&) = delete;
#ifndef ENABLE_GC_FASTPATH
    CallToFFixedX28() = default;
    ~CallToFFixedX28() = default;
#else
    ALWAYS_INLINE CallToFFixedX28() noexcept
    {
        // Save the current value of x28:
        // code compiled without -ffixed-x28 expects that the value in x28 survives
        // any calls,
        // but our code compiled with -ffixed-x28 relies on compiler NOT generating prologue/epilogue code to preserve x28,
        // so we must do it manually at the points where foreign code can call us.
        uintptr_t val;
        FixedRegToLocalVar(val);
        saved = val;

        if (AssertionsEnabled) {
            verify = true;
            next = top;
            top = this;
            val = MAGIC_MARKER;
            LocalVarToFixedReg_UNCHECKED(val);
        }
    }

    ALWAYS_INLINE ~CallToFFixedX28() noexcept
    {
        // Restore saved value of x28 upon returning back to the foreign caller.
        uintptr_t val = saved;
        LocalVarToFixedReg_UNCHECKED(val);
        if (AssertionsEnabled) {
            top = next;
        }
    }

    ALWAYS_INLINE static void Verify()
    {
        if (AssertionsEnabled) {
            RuntimeAssert(top != nullptr,
                "Ensure that an instance of CallToFFixedX28 is created at the point native code calls to runtime.");
            top->VerifyImpl();
        }
    }
private:
    void VerifyImpl()
    {
        if (AssertionsEnabled && verify) {
            uintptr_t val;
            FixedRegToLocalVar(val);
            RuntimeAssert(val == CallToFFixedX28::MAGIC_MARKER,
                "Ensure that an instance of CallToFFixedX28 is created at the point native code calls to runtime.");
            verify = false; // only verify on the first write since this instance is created.
        }
    }

    // saved value of x28
    uintptr_t saved;

    // the following is for verification when assertions are enabled, should get eliminated by compiler in release mode
    bool verify;
    CallToFFixedX28* next;
    inline static thread_local CallToFFixedX28* top = nullptr;
#endif
};

#ifdef ENABLE_GC_FASTPATH
#define SetThreadLocalDataToFixedReg(tls) \
    do { \
    common::CallToFFixedX28::Verify(); \
    LocalVarToFixedReg_UNCHECKED(tls); \
    } while (false);

#define TLS_ACTIVE_READ_BARRIER_BIT "62" // string, to simplify uses in __asm__, keep in sync with TLS_DATA_MASK
constexpr uintptr_t TLS_DATA_MASK = (1L << 62) - 1; // keep in sync with TLS_ACTIVE_READ_BARRIER_BIT

static inline uintptr_t ThreadLocalRegisterData()
{
    uintptr_t tlr;
    FixedRegToLocalVar(tlr);
    // Usually we access data pointed by x28 value without masking
    // relying on TBI, masking is only required for equality checks.
    return tlr & TLS_DATA_MASK;
}
static_assert(CallToFFixedX28::MAGIC_MARKER == (CallToFFixedX28::MAGIC_MARKER & TLS_DATA_MASK));

#define CHECK_READ_BARRIER_SLOW_PATH(slow_path) \
    __asm__ volatile goto("tbnz x28, #" TLS_ACTIVE_READ_BARRIER_BIT ", %l[" #slow_path "]" : : : : slow_path);

#define FAST_CHECK_MM_SWITCH(slow_path) \
    __asm__ volatile goto("cbz x28, %l[" #slow_path "]" : : : : slow_path);
#endif

/// UpdateThreadLocalDataReg functions sync the TLS_ACTIVE_READ_BARRIER_BIT value in x28 to the current GC phase.
/// These functions should be called at the earliest possible point below a call to CRT runtime
/// which might step on a safe-point and thus trigger GC phase change.
ALWAYS_INLINE void UpdateThreadLocalDataReg(const MutatorBase*);
ALWAYS_INLINE void UpdateThreadLocalDataReg(const ThreadHolder*);
ALWAYS_INLINE void UpdateThreadLocalDataReg();

/// RestoreThreadLocalDataReg functions update the full x28 register with CRT TLS pointer and current GC phase.
/// These functions should be called to complete the switch to kRunnable state,
/// e.g. ensure proper x28 value in a K/N runtime function called from CRT after it performs a switch to safe state.
ALWAYS_INLINE void RestoreThreadLocalDataReg(kotlin::mm::ThreadData*);
ALWAYS_INLINE void RestoreThreadLocalDataReg();

/// Ensures x28 == 0, which is its proper value when both -ffixed-x28 and non-CRT MM are enabled.
ALWAYS_INLINE void ZeroThreadLocalDataReg();

/// Obtains the current Mutator pointer from the CRT TLS stored in x28, or nullptr if x28 is not used.
/// Ported from upstream 33af2848b3c: write barriers want the mutator pointer so they can route
/// directly to that thread's SATB buffer rather than re-resolving it via `Mutator::GetMutator()`.
ALWAYS_INLINE Mutator* GetMutatorOrNull();
} // namespace common
#endif
