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

#ifndef RUNTIME_MAIN_KOTLIN_CALL_SCOPE_H
#define RUNTIME_MAIN_KOTLIN_CALL_SCOPE_H

#include "Common.h"

// Opaque storage for saved frame info. Layout must match the definition in
// mm/cpp/FpUnwind.cpp, which is the sole reader/writer. Kept here as a POD
// so KotlinCallScope can allocate it on the stack without pulling in
// ThreadData.hpp.
struct SavedKotlinFrameInfo {
    void* fa;
    int status;
    const void* pc;
    int prevThreadState;  // ThreadState before the scope; restored on dtor
};

extern "C" RUNTIME_NOTHROW void SaveCurrentFrameInfoAndSetReliable(SavedKotlinFrameInfo* saved) noexcept;
extern "C" RUNTIME_NOTHROW void RestoreSavedFrameInfo(const SavedKotlinFrameInfo* saved) noexcept;

/**
 * RAII guard: save current frame info on construction, set Reliable for the Kotlin code
 * that is about to run, and restore the saved info on destruction.
 *
 * Use this in runtime C++ functions that indirectly call into Kotlin (via function pointer,
 * callback, or knbridge), to prevent the Reliable status from leaking beyond the call scope.
 */
class KotlinCallScope {
public:
    KotlinCallScope() noexcept { SaveCurrentFrameInfoAndSetReliable(&saved_); }
    ~KotlinCallScope() noexcept { RestoreSavedFrameInfo(&saved_); }
    KotlinCallScope(const KotlinCallScope&) = delete;
    KotlinCallScope& operator=(const KotlinCallScope&) = delete;
private:
    SavedKotlinFrameInfo saved_;
};

#endif // RUNTIME_MAIN_KOTLIN_CALL_SCOPE_H
