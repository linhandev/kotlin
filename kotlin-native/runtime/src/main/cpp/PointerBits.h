/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

#ifndef RUNTIME_POINTER_BITS_H
#define RUNTIME_POINTER_BITS_H

#include <cstdint>

#include "Common.h"

template <typename T>
ALWAYS_INLINE T* setPointerBits(T* ptr, uintptr_t bits) {
    return reinterpret_cast<T*>(reinterpret_cast<uintptr_t>(ptr) | bits);
}

template <typename T>
ALWAYS_INLINE T* clearPointerBits(T* ptr, uintptr_t bits) {
    return reinterpret_cast<T*>(reinterpret_cast<uintptr_t>(ptr) & ~bits);
}

template <typename T>
ALWAYS_INLINE uintptr_t getPointerBits(T* ptr, uintptr_t bits) {
    return reinterpret_cast<uintptr_t>(ptr) & bits;
}

template <typename T>
ALWAYS_INLINE bool hasPointerBits(T* ptr, uintptr_t bits) {
    return getPointerBits(ptr, bits) != 0;
}

#endif // RUNTIME_POINTER_BITS_H
