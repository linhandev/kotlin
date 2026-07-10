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

// KMP CRT NativeHook 适配 - restrace 家族调用宏
//
// 使用方式:
//   #include "memory_trace_macros.h"
//   MEMORY_TRACE_ALLOCATE(obj, size);      // alloc 事件
//   MEMORY_TRACE_MOVE(from, to, size);     // GC 搬迁事件
//   MEMORY_TRACE_FREEREGION(start, size);  // region 释放事件
//
// Gate 组合(两侧都识别为"编 OHOS target"):
//   KMP 侧      -> KONAN_OHOS
//   common-rt 侧 -> PANDA_TARGET_OHOS
// 用元宏 KMP_TRACE_ENABLED 统一,下面 macro 只走一个分支。
//
// resTraceMove / resTraceFreeRegion 是 API 23+ 才有的符号,在 memory_trace.h 里
// 用 __attribute__((weak)) 声明。API 21 上函数指针为 NULL,配合 macro 里的 NULL 检查跳过。

#ifndef _MEMORY_TRACE_MACROS_H
#define _MEMORY_TRACE_MACROS_H

#include "memory_trace.h"

// 编译期总开关 HOOK_ENABLE(对齐 ArkTS CommonGC pattern)
// 平台 gate (KONAN_OHOS/PANDA_TARGET_OHOS) 必须同时 + HOOK_ENABLE 才展开插桩;否则宏为空
#if (defined(KONAN_OHOS) || defined(PANDA_TARGET_OHOS)) && defined(HOOK_ENABLE)
#define KMP_TRACE_ENABLED 1
#endif

#ifdef KMP_TRACE_ENABLED

#include <deviceinfo.h>

// Memory.h 里定义 OHOS_RESTRACE_MIN_API=21;这里 fallback 定义避免依赖 Memory.h
#ifndef OHOS_RESTRACE_MIN_API
#define OHOS_RESTRACE_MIN_API 21
#endif

inline bool KmpTraceSupported()
{
    static const bool supported = OH_GetSdkApiVersion() >= OHOS_RESTRACE_MIN_API;
    return supported;
}

// ALLOCATE:restrace 是 weak 声明,加 NULL 检查保持一致性
#define MEMORY_TRACE_ALLOCATE(obj, size)                                        \
    do {                                                                        \
        if (KmpTraceSupported() && restrace) {                                  \
            restrace(RES_KMP_HEAP_MASK, (void*)(obj), (size_t)(size),           \
                     TAG_RES_KMP_HEAP_MASK, true);                              \
        }                                                                       \
    } while (0)

// MOVE:resTraceMove 是 weak 声明,API 21 上可能为 NULL,加 NULL 检查
#define MEMORY_TRACE_MOVE(from, to, size)                                       \
    do {                                                                        \
        if (KmpTraceSupported() && resTraceMove) {                              \
            resTraceMove(RES_KMP_HEAP_MASK, (void*)(from), (void*)(to),         \
                         (size_t)(size));                                       \
        }                                                                       \
    } while (0)

// FREEREGION:resTraceFreeRegion 是 weak 声明,加 NULL 检查
#define MEMORY_TRACE_FREEREGION(start, size)                                    \
    do {                                                                        \
        if (KmpTraceSupported() && resTraceFreeRegion) {                        \
            resTraceFreeRegion(RES_KMP_HEAP_MASK, (void*)(start),               \
                               (size_t)(size));                                 \
        }                                                                       \
    } while (0)

#else // 非 OHOS 平台(mac / linux / ios / 独立单元测试):展开为空,零副作用

#define MEMORY_TRACE_ALLOCATE(obj, size)     ((void)(obj), (void)(size))
#define MEMORY_TRACE_MOVE(from, to, size)    ((void)(from), (void)(to), (void)(size))
#define MEMORY_TRACE_FREEREGION(start, size) ((void)(start), (void)(size))

#endif // KMP_TRACE_ENABLED

#endif // _MEMORY_TRACE_MACROS_H
