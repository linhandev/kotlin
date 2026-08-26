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

#include "MemoryManagerSwitch.hpp"

// Defined in exactly one C++ TU so the dynamic initializer is compiled with
// __gxx_personality_v0. Keeping `inline const bool useCRT = IsEnabled()` in the
// header caused every .mm (ObjC++) TU that includes Memory.h to emit a competing
// linkonce_odr/comdat copy with __gnu_objc_personality_v0; llvm-link's comdat
// `any` then nondeterministically picked the ObjC++ copy, leaving an undefined
// __gnu_objc_personality_v0 when linking OHOS executables (no libobjc).
namespace MemoryManagerSwitch {
const bool useCRT = IsEnabled();
}
