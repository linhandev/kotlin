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

#include "common_components/mutator/mutator.h"

namespace common {

#ifdef ENABLE_GC_FASTPATH
struct ThreadLocalRegisterData {
    uintptr_t threadLocalData : 62;
    uintptr_t needBarrier : 1;
};
union ThreadLocalRegisterAccessor {
    uintptr_t raw;
    ThreadLocalRegisterData data;
};
static_assert(sizeof(ThreadLocalRegisterData) == sizeof(ThreadLocalRegisterAccessor::raw));

static inline uintptr_t ThreadLocalRegisterRawData()
{
    uintptr_t tlr;
    __asm__ volatile("mov %0, x28" : "=r"(tlr));
    return tlr;
}

#define SetThreadLocalDataToFixedReg(tls) __asm__ volatile("mov x28, %0" : : "r"(tls))

#define FixedRegtoLocalVar(var) \
    __asm__ volatile("mov %0, x28" : "=r"(var));

static inline void UpdateThreadLocalDataReg(common::MutatorBase* mutator) {
    uintptr_t maskBits = mutator->GetMutatorPhase() > 8 ? 1 : 0;
    __asm__ volatile("bfi x28, %0, #62, #1" : : "r"(maskBits));
}

static inline void ZeroThreadLocalDataReg() {
    __asm__ volatile("mov x28, #0" ::: "x28");
}

#define CHECK_READ_BARRIER_SLOW_PATH(slow_path) \
    __asm__ volatile goto("tbnz x28, #62, %l[" #slow_path "]" : : : : slow_path);

#endif
} // namespace common
#endif
