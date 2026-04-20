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

#ifdef KONAN_OHOS

#include <mutex>
#include <ArkTSInit.h>
#include "ArkTSStringRef.h"

extern "C" void Kotlin_ArkTS_initialize() {
    static std::once_flag initFlag;
    std::call_once(initFlag, []() {
        Kotlin_ArkTS_initStringRef();
    });
}

#endif // KONAN_OHOS
