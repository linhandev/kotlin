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

#ifndef RUNTIME_ARKTS_CONFIG_H
#define RUNTIME_ARKTS_CONFIG_H

#include "Common.h"
#include "Types.h"

extern "C" {
RUNTIME_EXPORT ALWAYS_INLINE bool Kotlin_ArkTSConfig_getArkToKotlinEnabled();
RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setArkToKotlinEnabled(bool enable);

RUNTIME_EXPORT ALWAYS_INLINE bool Kotlin_ArkTSConfig_getKotlinToArkEnabled();
RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setKotlinToArkEnabled(bool enable);

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getMinLengthForArkString();
RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setMinLengthForArkString(KInt length);

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getMinLengthForKotlinString();
RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setMinLengthForKotlinString(KInt length);

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getNapiRefCleanupBatchSize();
RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setNapiRefCleanupBatchSize(KInt count);

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getNapiRefCleanupIntervalSec();
RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setNapiRefCleanupIntervalSec(KInt seconds);
} // extern "C"

#endif // RUNTIME_ARKTS_CONFIG_H
