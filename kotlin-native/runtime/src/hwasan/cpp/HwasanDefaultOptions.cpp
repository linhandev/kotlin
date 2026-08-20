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

#include "Common.h"

// Weak so a developer can supply a strong definition.
// used/retain + default visibility so --gc-sections still exports the hook:
// ROM libclang_rt.hwasan.so has a weak undef; the linker will not pull an
// unreferenced hidden symbol. Linked only when sanitizer==HWADDRESS
// (see CompilerOutput.kt); non-HWASan binaries must not contain this symbol.
extern "C" RUNTIME_WEAK RUNTIME_EXPORT __attribute__((visibility("default")))
const char* __hwasan_default_options() {
    return "heap_quarantine_min=0:heap_quarantine_max=4096:heap_quarantine_thread_max_count=128";
}
