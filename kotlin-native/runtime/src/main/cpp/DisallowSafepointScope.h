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
#ifndef RUNTIME_DISALLOW_SAFEPOINT_SCOPE_H
#define RUNTIME_DISALLOW_SAFEPOINT_SCOPE_H

#include "Utils.hpp"
#include <string>
#include <sstream>
#include <vector>

struct MemoryState;

// The annotated function is guaranteed to NOT cross safepoint
#define NO_SAFEPOINT __attribute__((annotate("no_safepoint")))

// The annotated function can potentially cross a safepoint.
// The `used` attribute MUST be unconditional. K2RStub.s asm objects reference
// these functions (via `_unwindPCForK2RStub*` data slots) regardless of build
// mode; without `used`, GlobalDCE in OFF builds (ENABLE_STACKMAP undefined)
// strips them and ld fails on undefined symbols. The `has_safepoint` and
// `K2RStub` annotations are stackmap-pipeline metadata and remain conditional
// on ENABLE_STACKMAP.
#ifdef ENABLE_STACKMAP
#define HAS_SAFEPOINT __attribute__((annotate("has_safepoint"), annotate("K2RStub"), used))
#define HAS_SAFEPOINT_THROW __attribute__((annotate("has_safepoint_throw"), annotate("K2RStub"), used))
#else
#define HAS_SAFEPOINT __attribute__((used))
#define HAS_SAFEPOINT_THROW __attribute__((used))
#endif

namespace kotlin::mm {
class ThreadData;

// A per-ThreadData unit, used to record the status of the current DisallowSafepointScope
class DisallowSafepointScopeData : private Pinned {
private:
    friend class DisallowSafepointScope;
    // Used for reporting stack when assertion fails
    struct StackEntry {
        const char* file;
        int line;
    };

    std::string ReportStack()
    {
        std::stringstream ss;
        for (const auto& entry : stack_) {
            ss << "DisallowSafepointScope set at " << entry.file << ":" << entry.line << "\n";
        }
        return ss.str();
    }

    void AssertAllowSafepoint() { RuntimeAssert(level_ == 0, "%s", ReportStack().c_str()); }

    int level_ = 0;
    std::vector<StackEntry> stack_;
};

// A scope used for debugging purpose, which disallows the given thread
// for entering a safepoint when the scope is active.
class DisallowSafepointScope : private Pinned {
public:
    explicit DisallowSafepointScope(
            MemoryState* thread,
            const char* file = __builtin_FILE(),
            int line = __builtin_LINE());
    explicit DisallowSafepointScope(
            const char* file = __builtin_FILE(),
            int line = __builtin_LINE());

    ~DisallowSafepointScope();

    ALWAYS_INLINE static void AssertAllowSafepoint(MemoryState* thread);
    ALWAYS_INLINE static void AssertAllowSafepoint(mm::ThreadData& threadData);

private:
    MemoryState* thread_;
};

}; // namespace kotlin::mm

#endif // RUNTIME_DISALLOW_SAFEPOINT_SCOPE_H
