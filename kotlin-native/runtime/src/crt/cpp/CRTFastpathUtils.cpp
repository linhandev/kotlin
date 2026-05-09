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

#include "CRTFastpathUtils.hpp"
#include "common_components/mutator/mutator.h"

#ifdef ENABLE_GC_FASTPATH
// separate implementation from the header to avoid including mutator.h everywhere
ALWAYS_INLINE void common::UpdateThreadLocalDataReg(common::MutatorBase* mutator) {
    // Only enable read barrier slowpath for phases that require forwarding (PRECOPY/COPY/FIX).
    // ENUM/MARK/REMARK/POST_MARK phases have trivial ReadRefField (direct memory load),
    // identical to IdleBarrier, so they don't need the slowpath.
    uintptr_t maskBits = mutator->GetMutatorPhase() >= GCPhase::GC_PHASE_PRECOPY ? 1 : 0;
    __asm__ volatile("bfi x28, %0, #62, #1" : : "r"(maskBits));
}
#endif
