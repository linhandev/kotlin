/*
 * Copyright 2025-2025 Huawei Technologies Co., Ltd. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

#if KONAN_OHOS

#include <cstdint>
#include <limits>

#include "Exceptions.h"
#include "KAssert.h"
#include "KString.h"
#include "Memory.h"
#include "Natives.h"
#include "Types.h"

#include <hilog/log.h>
#include <napi/native_api.h>

#define NAPI_LOG_DOMAIN 0x1001
#define NAPI_LOG_TAG "NapiInterface"

using namespace kotlin;

// ArkTS safe integer range: [-(2^53-1), 2^53-1] = [-9007199254740991, 9007199254740991]
static constexpr long long kArkTSSafeIntegerMax = (1LL << 53) - 1;
static constexpr long long kArkTSSafeIntegerMin = -kArkTSSafeIntegerMax;

extern "C" {
    /**
     * @brief Int conversion: Kotlin Int -> ArkTS number.
     * @param env Current running virtual machine context.
     * @param value The 32-bit signed integer value to be represented in ArkTS.
     * @return A napi_value representing an ArkTS number.
     */
    KNativePtr Kotlin_napi_create_int32(KNativePtr env, KInt value) {
        napi_value result;
        napi_status status = napi_create_int32((napi_env)env, value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_create_int32 failed in createValueFromInt32, status: %{public}d",
                status);
            return nullptr;
        }
        return (KNativePtr)result;
    }

    /**
     * @brief Int conversion: ArkTS number -> Kotlin Int.
     * @param env Current running virtual machine context.
     * @param value The ArkTS number to get the value from.
     * @return The 32-bit signed integer value.
     */
    KInt Kotlin_napi_get_value_int32(KNativePtr env, KNativePtr value) {
        int32_t result;
        napi_status status = napi_get_value_int32((napi_env)env, (napi_value) value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_get_value_int32 failed in getInt32FromValue, status: %{public}d",
                status);
            return 0;
        }
        return result;
    }

    /**
     * @brief UInt conversion: Kotlin UInt -> ArkTS number.
     * @param env Current running virtual machine context.
     * @param value The 32-bit unsigned integer value to be represented in ArkTS.
     * @return A napi_value representing an ArkTS number.
     */
    KNativePtr Kotlin_napi_create_uint32(KNativePtr env, KUInt value) {
        napi_value result;
        napi_status status = napi_create_uint32((napi_env)env, value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_create_uint32 failed in createValueFromUInt32, status: %{public}d",
                status);
            return nullptr;
        }
        return (KNativePtr)result;
    }

    /**
     * @brief UInt conversion: ArkTS number -> Kotlin UInt.
     * @param env Current running virtual machine context.
     * @param value The ArkTS number to get the value from.
     * @return The 32-bit unsigned integer value.
     */
    KUInt Kotlin_napi_get_value_uint32(KNativePtr env, KNativePtr value) {
        uint32_t result;
        napi_status status = napi_get_value_uint32((napi_env)env, (napi_value) value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_get_value_uint32 failed in getUInt32FromValue, status: %{public}d",
                status);
            return 0;
        }
        return static_cast<KUInt>(result);
    }

    /**
     * @brief Long conversion: Kotlin Long -> ArkTS number.
     *
     * Note: Input must be in [-9007199254740991, 9007199254740991] (safe integer range for ArkTS/JS)
     *
     * @param env Current running virtual machine context.
     * @param value The 64-bit signed integer value to be represented in ArkTS.
     * @return A napi_value representing an ArkTS number.
     */
    KNativePtr Kotlin_napi_create_int64(KNativePtr env, KLong value) {
        // Check if it is within the ArkTS safe integer range
        if (value < kArkTSSafeIntegerMin || value > kArkTSSafeIntegerMax) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "Kotlin Long value out of ArkTS safe integer range [%{public}lld, %{public}lld] "
                "in createValueFromLong, Value: %{public}lld",
                kArkTSSafeIntegerMin, kArkTSSafeIntegerMax, (long long)value);
            return nullptr;
        }

        napi_value result;
        napi_status status = napi_create_int64((napi_env)env, value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_create_int64 failed in createValueFromLong, status: %{public}d",
                status);
            return nullptr;
        }
        return (KNativePtr)result;
    }

    /**
     * @brief Long conversion: ArkTS number -> Kotlin Long.
     * @param env Current running virtual machine context.
     * @param value The ArkTS number to get the value from.
     * @return The 64-bit signed integer value.
     */
    KLong Kotlin_napi_get_value_int64(KNativePtr env, KNativePtr value) {
        int64_t result;
        napi_status status = napi_get_value_int64((napi_env)env, (napi_value) value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_get_value_int64 failed in getLongFromValue, status: %{public}d",
                status);
            return 0;
        }
        return result;
    }

    /**
     * @brief Boolean conversion: Kotlin Boolean -> ArkTS Boolean.
     * @param env Current running virtual machine context.
     * @param value The Kotlin Boolean value to be represented in ArkTS.
     * @return A napi_value representing an ArkTS Boolean.
     */
    KNativePtr Kotlin_napi_create_bool(KNativePtr env, KBoolean value) {
        napi_value result;
        napi_status status = napi_get_boolean((napi_env)env, value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_get_boolean failed in createValueFromBoolean, status: %{public}d",
                status);
            return nullptr;
        }
        return (KNativePtr)result;
    }

    /**
     * @brief Boolean conversion: ArkTS Boolean -> Kotlin Boolean.
     * @param env Current running virtual machine context.
     * @param value The ArkTS Boolean to get the value from.
     * @return The boolean value.
     */
    KBoolean Kotlin_napi_get_value_bool(KNativePtr env, KNativePtr value) {
        bool result;
        napi_status status = napi_get_value_bool((napi_env)env, (napi_value) value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_get_value_bool failed in getBooleanFromValue, status: %{public}d",
                status);
            return false;
        }
        return result;
    }

    /**
     * @brief Double conversion: Kotlin Double -> ArkTS number.
     * @param env Current running virtual machine context.
     * @param value The double value to be represented in ArkTS.
     * @return A napi_value representing an ArkTS number.
     */
    KNativePtr Kotlin_napi_create_double(KNativePtr env, KDouble value) {
        napi_value result;
        napi_status status = napi_create_double((napi_env)env, value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_create_double failed in createValueFromDouble, status: %{public}d",
                status);
            return nullptr;
        }
        return (KNativePtr)result;
    }

    /**
     * @brief Double conversion: ArkTS number -> Kotlin Double.
     * @param env Current running virtual machine context.
     * @param value The ArkTS number to get the value from.
     * @return The double value.
     */
    KDouble Kotlin_napi_get_value_double(KNativePtr env, KNativePtr value) {
        double result;
        napi_status status = napi_get_value_double((napi_env)env, (napi_value) value, &result);
        if (status != napi_ok) {
            OH_LOG_Print(LOG_APP, LOG_ERROR, NAPI_LOG_DOMAIN, NAPI_LOG_TAG,
                "napi_get_value_double failed in getDoubleFromValue, status: %{public}d",
                status);
            return 0.0;
        }
        return (KDouble)result;
    }
}

#endif
