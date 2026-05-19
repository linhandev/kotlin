/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#ifndef RUNTIME_POINTER_BITS_H
#define RUNTIME_POINTER_BITS_H

#include <cstdint>

#include "Common.h"

template <typename T>
ALWAYS_INLINE T* SetPointerBits(T* ptr, uintptr_t bits) {
    return reinterpret_cast<T*>(reinterpret_cast<uintptr_t>(ptr) | bits);
}

template <typename T>
ALWAYS_INLINE T* ClearPointerBits(T* ptr, uintptr_t bits) {
    return reinterpret_cast<T*>(reinterpret_cast<uintptr_t>(ptr) & ~bits);
}

template <typename T>
ALWAYS_INLINE uintptr_t GetPointerBits(T* ptr, uintptr_t bits) {
    return reinterpret_cast<uintptr_t>(ptr) & bits;
}

template <typename T>
ALWAYS_INLINE bool HasPointerBits(T* ptr, uintptr_t bits) {
    return GetPointerBits(ptr, bits) != 0;
}

#endif // RUNTIME_POINTER_BITS_H
