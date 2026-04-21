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

#include <atomic>

#include "ArkTSConfig.h"
#include "Logging.hpp"

struct ArkTSStringProxyConfig {
    // Whether to enable the string proxy from ArkTS to Kotlin.
    std::atomic<bool> enableArkToKotlin = false;
    // Whether to enable the string proxy from Kotlin to ArkTS.
    std::atomic<bool> enableKotlinToArk = false;
    // Minimum length of ArkTS strings to use proxy.
    std::atomic<KInt> minLengthForArkString = 300;
    // Minimum length of Kotlin strings to use proxy.
    std::atomic<KInt> minLengthForKotlinString = 300;
    // Threshold for the number of napi_ref to delete in batch.
    std::atomic<KInt> napiRefCleanupBatchSize = 500;
    // Interval seconds for batch deletion of napi_ref.
    std::atomic<KInt> napiRefCleanupIntervalSec = 7;
};

#ifdef KONAN_OHOS
static ArkTSStringProxyConfig g_arktsStringProxyConfig;
#endif

extern "C" {
RUNTIME_EXPORT ALWAYS_INLINE bool Kotlin_ArkTSConfig_getArkToKotlinEnabled() {
#ifdef KONAN_OHOS
    return g_arktsStringProxyConfig.enableArkToKotlin.load();
#else
    return false;
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setArkToKotlinEnabled(bool enable) {
#ifdef KONAN_OHOS
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] Kotlin_ArkTSConfig_setArkToKotlinEnabled: enable = %d",
                   enable);
    g_arktsStringProxyConfig.enableArkToKotlin.store(enable);
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE bool Kotlin_ArkTSConfig_getKotlinToArkEnabled() {
#ifdef KONAN_OHOS
    return g_arktsStringProxyConfig.enableKotlinToArk.load();
#else
    return false;
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setKotlinToArkEnabled(bool enable) {
#ifdef KONAN_OHOS
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] Kotlin_ArkTSConfig_setKotlinToArkEnabled: enable = %d",
                   enable);
    g_arktsStringProxyConfig.enableKotlinToArk.store(enable);
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getMinLengthForArkString() {
#ifdef KONAN_OHOS
    return g_arktsStringProxyConfig.minLengthForArkString.load();
#else
    return 0;
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setMinLengthForArkString(KInt length) {
#ifdef KONAN_OHOS
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] Kotlin_ArkTSConfig_setMinLengthForArkString: length = %d",
                   length);
    g_arktsStringProxyConfig.minLengthForArkString.store(length);
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getMinLengthForKotlinString() {
#ifdef KONAN_OHOS
    return g_arktsStringProxyConfig.minLengthForKotlinString.load();
#else
    return 0;
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setMinLengthForKotlinString(KInt length) {
#ifdef KONAN_OHOS
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] Kotlin_ArkTSConfig_setMinLengthForKotlinString: length = %d",
                   length);
    g_arktsStringProxyConfig.minLengthForKotlinString.store(length);
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getNapiRefCleanupBatchSize() {
#ifdef KONAN_OHOS
    return g_arktsStringProxyConfig.napiRefCleanupBatchSize.load();
#else
    return 0;
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setNapiRefCleanupBatchSize(KInt count) {
#ifdef KONAN_OHOS
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] Kotlin_ArkTSConfig_setNapiRefCleanupBatchSize: count = %d",
                   count);
    g_arktsStringProxyConfig.napiRefCleanupBatchSize.store(count);
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE KInt Kotlin_ArkTSConfig_getNapiRefCleanupIntervalSec() {
#ifdef KONAN_OHOS
    return g_arktsStringProxyConfig.napiRefCleanupIntervalSec.load();
#else
    return 0;
#endif
}

RUNTIME_EXPORT ALWAYS_INLINE void Kotlin_ArkTSConfig_setNapiRefCleanupIntervalSec(KInt seconds) {
#ifdef KONAN_OHOS
    RuntimeLogInfo({ kotlin::logging::Tag::kRT },
                   "[String0Copy] Kotlin_ArkTSConfig_setNapiRefCleanupIntervalSec: seconds = %d",
                   seconds);
    g_arktsStringProxyConfig.napiRefCleanupIntervalSec.store(seconds);
#endif
}
} // extern "C"
