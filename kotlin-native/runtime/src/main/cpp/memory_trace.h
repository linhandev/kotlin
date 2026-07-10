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

#ifndef _MEMORY_TRACE_H
#define _MEMORY_TRACE_H

#include <stdbool.h>
#include <unistd.h>

#ifdef __cplusplus
extern "C" {
#endif

#define TAG_RES_KMP_HEAP_MASK      "RES_KMP_HEAP_MASK"
#define RES_KMP_HEAP_MASK          (1 << 19)

__attribute__((weak)) void restrace(unsigned long long mask,
    void* addr, size_t size, const char* tag, bool isUsing);

// resTraceMove / resTraceFreeRegion: API 21 未导出,API 23+ 才有
// __attribute__((weak)):找不到时函数指针 = NULL,不 abort libkn.so 加载;调用点需配合 NULL 检查
__attribute__((weak)) void resTraceMove(unsigned long long mask, void* from, void* to, size_t size);

__attribute__((weak)) void resTraceFreeRegion(unsigned long long mask, void* start, size_t size);
#ifdef __cplusplus
}
#endif
#endif
