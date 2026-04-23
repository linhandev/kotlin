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
package kotlin.native.runtime

import kotlin.native.internal.GCUnsafeCall

@GCUnsafeCall("Kotlin_ArkTSConfig_getArkToKotlinEnabled")
private external fun getArkToKotlinEnabled(): Boolean

@GCUnsafeCall("Kotlin_ArkTSConfig_setArkToKotlinEnabled")
private external fun setArkToKotlinEnabled(enable: Boolean)

@GCUnsafeCall("Kotlin_ArkTSConfig_getKotlinToArkEnabled")
private external fun getKotlinToArkEnabled(): Boolean

@GCUnsafeCall("Kotlin_ArkTSConfig_setKotlinToArkEnabled")
private external fun setKotlinToArkEnabled(enable: Boolean)

@GCUnsafeCall("Kotlin_ArkTSConfig_getMinLengthForArkString")
private external fun getMinLengthForArkString(): Int

@GCUnsafeCall("Kotlin_ArkTSConfig_setMinLengthForArkString")
private external fun setMinLengthForArkString(length: Int)

@GCUnsafeCall("Kotlin_ArkTSConfig_getMinLengthForKotlinString")
private external fun getMinLengthForKotlinString(): Int

@GCUnsafeCall("Kotlin_ArkTSConfig_setMinLengthForKotlinString")
private external fun setMinLengthForKotlinString(length: Int)

@GCUnsafeCall("Kotlin_ArkTSConfig_getNapiRefCleanupBatchSize")
private external fun getNapiRefCleanupBatchSize(): Int

@GCUnsafeCall("Kotlin_ArkTSConfig_setNapiRefCleanupBatchSize")
private external fun setNapiRefCleanupBatchSize(value: Int)

@GCUnsafeCall("Kotlin_ArkTSConfig_getNapiRefCleanupIntervalSec")
private external fun getNapiRefCleanupIntervalSec(): Int

@GCUnsafeCall("Kotlin_ArkTSConfig_setNapiRefCleanupIntervalSec")
private external fun setNapiRefCleanupIntervalSec(value: Int)

public object StringProxyConfig {
    /**
     * Whether to enable string proxy from ArkTS to Kotlin. Default is false.
     *
     * NOTE: Even if this option is true, strings will use proxy mode only when the following conditions are met:
     *   1. API level of HarmonyOS devices >= 23.
     *   2. use `StringFromNapiValue` function to convert ArkTS string(aka napi_value) to Kotlin String.
     *   3. ArkTS string length >= minArkStringLength.
     */
    public var isArkToKotlinEnabled: Boolean
        get() = getArkToKotlinEnabled()
        set(value) = setArkToKotlinEnabled(value)

    /**
     * Whether to enable string proxy from Kotlin to ArkTS. Default is false.
     *
     * NOTE: Even if this option is true, strings will use proxy mode only when the following conditions are met:
     *   1. API level of HarmonyOS devices >= 22.
     *   2. use `StringToNapiValue` function to convert Kotlin String to ArkTS string(aka napi_value).
     *   3. Kotlin string length >= minKotlinStringLength.
     */
    public var isKotlinToArkEnabled: Boolean
        get() = getKotlinToArkEnabled()
        set(value) = setKotlinToArkEnabled(value)

    /**
     * Minimum ArkTS string length to enable string proxy, default is 100.
     * If string length less than this value, string passing uses copying rather than proxy.
     *
     * Note: If the input value is less than or equal to zero, no action is taken.
     */
    public var minArkStringLength: Int
        get() = getMinLengthForArkString()
        set(value) {
            if (value > 0) {
                setMinLengthForArkString(value)
            }
        }

    /**
     * Minimum Kotlin string length to enable string proxy, default is 300.
     * If string length less than this value, string passing uses copying rather than proxy.
     *
     * Note: If the input value is less than or equal to zero, no action is taken.
     */
    public var minKotlinStringLength: Int
        get() = getMinLengthForKotlinString()
        set(value) {
            if (value > 0) {
                setMinLengthForKotlinString(value)
            }
        }

    /**
     * Number of ArkTS string references (aka napi_ref) to delete in batches, default is 500.
     * When the number of napi_ref awaiting delete reaches this value, immediately execute a batch delete.
     *
     * Note: If the input value is less than or equal to zero, no action is taken.
     */
    public var napiRefCleanupBatchSize: Int
        get() = getNapiRefCleanupBatchSize()
        set(value) {
            if (value > 0) {
                setNapiRefCleanupBatchSize(value)
            }
        }

    /**
     * Maximum interval for batch deletion of napi_ref (Unit: second).
     * value must > 0, default is 7.
     *
     * Note: If the input value is less than or equal to zero, no action is taken.
     */
    public var napiRefCleanupIntervalSec: Int
        get() = getNapiRefCleanupIntervalSec()
        set(value) {
            if (value > 0) {
                setNapiRefCleanupIntervalSec(value)
            }
        }
}
