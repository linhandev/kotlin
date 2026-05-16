/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <stdio.h>
#include <string.h>

#include "KAssert.h"
#include "Exceptions.h"
#include "Memory.h"
#include "Natives.h"
#include "Types.h"

extern "C" void ThrowRangeIndexOutOfBoundsException(KInt from, KInt to, KInt size);
extern "C" void ThrowRangeIllegalArgumentException(KInt from, KInt to);

namespace {
// Duplicate range-check within runtime to avoid calling back to KT side which causes safepoint on non-throwing path.
// Keep sync with ArrayUtil.kt::checkRangeIndexes
static void checkRangeIndexes(KInt from, KInt to, KInt size) {
    if (from < 0 || to > size) {
        ThrowRangeIndexOutOfBoundsException(from, to, size);
    }
    if (from > to) {
        ThrowRangeIllegalArgumentException(from, to);
    }
}

HAS_SAFEPOINT_THROW
ALWAYS_INLINE inline void mutabilityCheck(KConstRef thiz) {
  // TODO: optimize it!
  if (!thiz->local() && isPermanentOrFrozen(thiz)) {
      ThrowInvalidMutabilityException(thiz);
  }
}

HAS_SAFEPOINT_THROW
ALWAYS_INLINE inline void boundsCheck(const ArrayHeader* array, KInt index) {
  // We couldn't have created an array bigger than max KInt value.
  // So if index is < 0, conversion to an unsigned value would make it bigger
  // than the array size.
  if (static_cast<uint32_t>(index) >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
}

template<typename T>
HAS_SAFEPOINT_THROW
inline void fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, T value) {
  ArrayHeader* array = thiz->array();
  checkRangeIndexes(fromIndex, toIndex, array->count_);
  mutabilityCheck(thiz);
  T* address = PrimitiveArrayAddressOfElementAt<T>(array, fromIndex);
  for (KInt index = fromIndex; index < toIndex; ++index) {
    *address++ = value;
  }
}

template<typename T>
HAS_SAFEPOINT_THROW
inline void copyImpl(KConstRef thiz, KInt fromIndex,
                     KRef destination, KInt toIndex, KInt count) {
  const ArrayHeader* array = thiz->array();
  ArrayHeader* destinationArray = destination->array();
  if (count < 0 ||
      fromIndex < 0 || static_cast<uint32_t>(count) + fromIndex > array->count_ ||
      toIndex < 0 || static_cast<uint32_t>(count) + toIndex > destinationArray->count_) {
      ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(destination);
  memmove(PrimitiveArrayAddressOfElementAt<T>(destinationArray, toIndex),
          PrimitiveArrayAddressOfElementAt<T>(array, fromIndex),
          count * sizeof(T));
}


template <class T, bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
inline void PrimitiveArraySet(KRef thiz, KInt index, T value) {
  ArrayHeader* array = thiz->array();
  if (BoundsCheck)
    boundsCheck(array, index);
  mutabilityCheck(thiz);
  *PrimitiveArrayAddressOfElementAt<T>(array, index) = value;
}

template <class T, bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
inline T PrimitiveArrayGet(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (BoundsCheck)
    boundsCheck(array, index);
  return *PrimitiveArrayAddressOfElementAt<T>(array, index);
}

template<bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
ALWAYS_INLINE KRef Kotlin_Array_get_value(KConstRef thiz, KInt index) {
  ArrayHeader* array = const_cast<ArrayHeader*>(thiz->array());
  if (BoundsCheck)
    boundsCheck(array, index);
  return ReadHeapRef(ArrayAddressOfElementAt(array, index), array->obj());
}

template<bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
ALWAYS_INLINE void Kotlin_Array_set_value(KRef thiz, KInt index, KConstRef value) {
  ArrayHeader* array = thiz->array();
  if (BoundsCheck)
    boundsCheck(array, index);
  mutabilityCheck(thiz);
  UpdateHeapRef(ArrayAddressOfElementAt(array, index), value, array->obj());
}

template<bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
ALWAYS_INLINE KByte Kotlin_ByteArray_get_value(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (BoundsCheck)
    boundsCheck(array, index);
  return *ByteArrayAddressOfElementAt(array, index);
}

template<bool BoundsCheck = true>
HAS_SAFEPOINT_THROW
ALWAYS_INLINE void Kotlin_ByteArray_set_value(KRef thiz, KInt index, KByte value) {
  ArrayHeader* array = thiz->array();
  if (BoundsCheck)
    boundsCheck(array, index);
  mutabilityCheck(thiz);
  *ByteArrayAddressOfElementAt(array, index) = value;
}

}  // namespace

extern "C" {

// Generated as part of Kotlin standard library.
extern const ObjHeader theEmptyArray;

// TODO: those must be compiler intrinsics afterwards.

// Array.kt
HAS_SAFEPOINT_THROW
OBJ_GETTER(Kotlin_Array_get, KConstRef thiz, KInt index) {
  RETURN_OBJ(Kotlin_Array_get_value(thiz, index));
}

HAS_SAFEPOINT_THROW
OBJ_GETTER(Kotlin_Array_get_without_BoundCheck, KConstRef thiz, KInt index){
  RETURN_OBJ(Kotlin_Array_get_value<false>(thiz, index));
}

HAS_SAFEPOINT_THROW
void Kotlin_Array_set(KRef thiz, KInt index, KConstRef value) {
  Kotlin_Array_set_value(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_Array_set_without_BoundCheck(KRef thiz, KInt index, KConstRef value) {
  Kotlin_Array_set_value<false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_Array_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

// TODO: Add regression test for missing write barrier fix in Array.fill()
HAS_SAFEPOINT_THROW
void Kotlin_Array_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KRef value) {
  ArrayHeader* array = thiz->array();
  checkRangeIndexes(fromIndex, toIndex, array->count_);
  mutabilityCheck(thiz);
  for (KInt index = fromIndex; index < toIndex; ++index) {
    UpdateHeapRef(ArrayAddressOfElementAt(array, index), value, array->obj());
  }
}

// TODO: xiaowen: Utilize GCPhase to enable fastpath here
HAS_SAFEPOINT_THROW
void Kotlin_Array_copyImpl(KConstRef thiz, KInt fromIndex,
                           KRef destination, KInt toIndex, KInt count) {
  // CRT readbarrier requires a non-const header TODO: rework the barrier?
  ArrayHeader* array = const_cast<ArrayHeader*>(thiz->array());
  ArrayHeader* destinationArray = destination->array();
  if (count < 0 ||
      fromIndex < 0 || static_cast<uint32_t>(count) + fromIndex > array->count_ ||
      toIndex < 0 || static_cast<uint32_t>(count) + toIndex > destinationArray->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(destination);
  if (fromIndex >= toIndex) {
    for (int index = 0; index < count; index++) {
      UpdateHeapRef(ArrayAddressOfElementAt(destinationArray, toIndex + index),
              ReadHeapRef(ArrayAddressOfElementAt(array, fromIndex + index), array->obj()),
              destinationArray->obj());
    }
  } else {
    for (int index = count - 1; index >= 0; index--) {
      UpdateHeapRef(ArrayAddressOfElementAt(destinationArray, toIndex + index),
                      ReadHeapRef(ArrayAddressOfElementAt(array, fromIndex + index), array->obj()),
                      destinationArray->obj());
    }
  }
}

// Arrays.kt
ALWAYS_INLINE OBJ_GETTER0(Kotlin_emptyArray) {
  RETURN_OBJ(const_cast<ObjHeader*>(&theEmptyArray));
}

HAS_SAFEPOINT_THROW
KByte Kotlin_ByteArray_get(KConstRef thiz, KInt index) {
  return Kotlin_ByteArray_get_value(thiz, index);
}

HAS_SAFEPOINT_THROW
KByte Kotlin_ByteArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return Kotlin_ByteArray_get_value<false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_set(KRef thiz, KInt index, KByte value) {
  Kotlin_ByteArray_set_value(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_set_without_BoundCheck(KRef thiz, KInt index, KByte value) {
  Kotlin_ByteArray_set_value<false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_ByteArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KChar Kotlin_ByteArray_getCharAt(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 1 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
#if KONAN_NO_UNALIGNED_ACCESS
  const uint8_t* address = reinterpret_cast<const uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  return (static_cast<KChar>(address[0]) << 0) | (static_cast<KChar>(address[1]) << 8);
#else
  auto result = *reinterpret_cast<const KChar*>(ByteArrayAddressOfElementAt(array, index));
#if __BIG_ENDIAN__
  return __builtin_bswap16(result);
#else
  return result;
#endif  // __BIG_ENDIAN__
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
KShort Kotlin_ByteArray_getShortAt(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 1 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
#if KONAN_NO_UNALIGNED_ACCESS
  const uint8_t* address = reinterpret_cast<const uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  return (static_cast<KShort>(address[0]) << 0) | (static_cast<KShort>(address[1]) << 8);
#else
  auto result = *reinterpret_cast<const KShort*>(ByteArrayAddressOfElementAt(array, index));
#if __BIG_ENDIAN__
  return __builtin_bswap16(result);
#else
  return result;
#endif  // __BIG_ENDIAN__
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
KInt Kotlin_ByteArray_getIntAt(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 3 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
#if KONAN_NO_UNALIGNED_ACCESS
  const uint8_t* address = reinterpret_cast<const uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  return (static_cast<KInt>(address[0]) << 0) | (static_cast<KInt>(address[1]) << 8) |
    (static_cast<KInt>(address[2]) << 16) | (static_cast<KInt>(address[3]) << 24);
#else
  auto result = *reinterpret_cast<const KInt*>(ByteArrayAddressOfElementAt(array, index));
#if __BIG_ENDIAN__
  return __builtin_bswap32(result);
#else
  return result;
#endif  //  __BIG_ENDIAN__
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
KLong Kotlin_ByteArray_getLongAt(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 7 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
#if KONAN_NO_UNALIGNED_ACCESS
  const uint8_t* address = reinterpret_cast<const uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  return (static_cast<KLong>(address[0]) << 0) | (static_cast<KLong>(address[1]) << 8) |
    (static_cast<KLong>(address[2]) << 16) | (static_cast<KLong>(address[3]) << 24) |
    (static_cast<KLong>(address[4]) << 32) | (static_cast<KLong>(address[5]) << 40) |
    (static_cast<KLong>(address[6]) << 48) | (static_cast<KLong>(address[7]) << 56);
#else
  auto result = *reinterpret_cast<const KLong*>(ByteArrayAddressOfElementAt(array, index));
#if __BIG_ENDIAN__
  return __builtin_bswap64(result);
#else
  return result;
#endif  // __BIG_ENDIAN__
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
KFloat Kotlin_ByteArray_getFloatAt(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 3 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
#if KONAN_NO_UNALIGNED_ACCESS
  const uint8_t* address = reinterpret_cast<const uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  union {
    KFloat f;
    uint8_t b[4];
  } u;
#if __BIG_ENDIAN__
  u.b[0] = address[3];
  u.b[1] = address[2];
  u.b[2] = address[1];
  u.b[3] = address[0];
#else
  u.b[0] = address[0];
  u.b[1] = address[1];
  u.b[2] = address[2];
  u.b[3] = address[3];
#endif  //  __BIG_ENDIAN__
  return u.f;
#else
  auto result = *reinterpret_cast<const KFloat*>(ByteArrayAddressOfElementAt(array, index));
  return result;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
KDouble Kotlin_ByteArray_getDoubleAt(KConstRef thiz, KInt index) {
  const ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 7 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
#if KONAN_NO_UNALIGNED_ACCESS
  const uint8_t* address = reinterpret_cast<const uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  union {
      KDouble d;
      uint8_t b[8];
  } u;
#if __BIG_ENDIAN__
  u.b[0] = address[7];
  u.b[1] = address[6];
  u.b[2] = address[5];
  u.b[3] = address[4];
  u.b[4] = address[3];
  u.b[5] = address[2];
  u.b[6] = address[1];
  u.b[7] = address[0];
#else
  u.b[0] = address[0];
  u.b[1] = address[1];
  u.b[2] = address[2];
  u.b[3] = address[3];
  u.b[4] = address[4];
  u.b[5] = address[5];
  u.b[6] = address[6];
  u.b[7] = address[7];
#endif  // __BIG_ENDIAN__
  return u.d;
#else
  return *reinterpret_cast<const KDouble*>(ByteArrayAddressOfElementAt(array, index));
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_setCharAt(KRef thiz, KInt index, KChar value) {
  ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 1 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(thiz);
#if KONAN_NO_UNALIGNED_ACCESS
  uint8_t* address = reinterpret_cast<uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  address[0] = (value >> 0) & 0xff;
  address[1] = (value >> 8) & 0xff;
#else
#if __BIG_ENDIAN__
   value = __builtin_bswap16(value);
#endif  // __BIG_ENDIAN__
  *reinterpret_cast<KChar*>(ByteArrayAddressOfElementAt(array, index)) = value;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_setShortAt(KRef thiz, KInt index, KShort value) {
  ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 1 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(thiz);
#if KONAN_NO_UNALIGNED_ACCESS
  uint8_t* address = reinterpret_cast<uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  address[0] = (value >> 0) & 0xff;
  address[1] = (value >> 8) & 0xff;
#else
#if __BIG_ENDIAN__
  value = __builtin_bswap16(value);
#endif
  *reinterpret_cast<KShort*>(ByteArrayAddressOfElementAt(array, index)) = value;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_setIntAt(KRef thiz, KInt index, KInt value) {
  ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 3 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(thiz);
#if KONAN_NO_UNALIGNED_ACCESS
  uint8_t* address = reinterpret_cast<uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  address[0] = (value >>  0) & 0xff;
  address[1] = (value >>  8) & 0xff;
  address[2] = (value >> 16) & 0xff;
  address[3] = (value >> 24) & 0xff;
#else
#if __BIG_ENDIAN__
  value = __builtin_bswap32(value);
#endif  // __BIG_ENDIAN__
  *reinterpret_cast<KInt*>(ByteArrayAddressOfElementAt(array, index)) = value;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_setLongAt(KRef thiz, KInt index, KLong value) {
  ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 7 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(thiz);
#if KONAN_NO_UNALIGNED_ACCESS
  uint8_t* address = reinterpret_cast<uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  address[0] = (value >>  0) & 0xff;
  address[1] = (value >>  8) & 0xff;
  address[2] = (value >> 16) & 0xff;
  address[3] = (value >> 24) & 0xff;
  address[4] = (value >> 32) & 0xff;
  address[5] = (value >> 40) & 0xff;
  address[6] = (value >> 48) & 0xff;
  address[7] = (value >> 56) & 0xff;
#else
#if __BIG_ENDIAN__
  value = __builtin_bswap64(value);
#endif // __BIG_ENDIAN__
  *reinterpret_cast<KLong*>(ByteArrayAddressOfElementAt(array, index)) = value;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_setFloatAt(KRef thiz, KInt index, KFloat value) {
  ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 3 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(thiz);
#if KONAN_NO_UNALIGNED_ACCESS
  uint8_t* address = reinterpret_cast<uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  union {
     KFloat f;
     uint8_t b[4];
  } u;
  u.f = value;
  address[0] = u.b[0];
  address[1] = u.b[1];
  address[2] = u.b[2];
  address[3] = u.b[3];
#else
  *reinterpret_cast<KFloat*>(ByteArrayAddressOfElementAt(array, index)) = value;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_setDoubleAt(KRef thiz, KInt index, KDouble value) {
  ArrayHeader* array = thiz->array();
  if (index < 0 || static_cast<uint32_t>(index) + 7 >= array->count_) {
    ThrowArrayIndexOutOfBoundsException();
  }
  mutabilityCheck(thiz);
#if KONAN_NO_UNALIGNED_ACCESS
  uint8_t* address = reinterpret_cast<uint8_t*>(ByteArrayAddressOfElementAt(array, index));
  union {
     KDouble d;
     uint8_t b[8];
  } u;
  u.d = value;
  address[0] = u.b[0];
  address[1] = u.b[1];
  address[2] = u.b[2];
  address[3] = u.b[3];
  address[4] = u.b[4];
  address[5] = u.b[5];
  address[6] = u.b[6];
  address[7] = u.b[7];
#else
  *reinterpret_cast<KDouble*>(ByteArrayAddressOfElementAt(array, index)) = value;
#endif  // KONAN_NO_UNALIGNED_ACCESS
}

HAS_SAFEPOINT_THROW
KChar Kotlin_CharArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KChar>(thiz, index);
}

HAS_SAFEPOINT_THROW
KChar Kotlin_CharArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KChar, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_CharArray_set(KRef thiz, KInt index, KChar value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_CharArray_set_without_BoundCheck(KRef thiz, KInt index, KChar value) {
  PrimitiveArraySet<KChar, false>(thiz, index, value);
}

HAS_SAFEPOINT
OBJ_GETTER(Kotlin_CharArray_copyOf, KConstRef thiz, KInt newSize) {
  if (newSize < 0) {
    ThrowIllegalArgumentException();
  }
  KInt oldSize = thiz->array()->count_;
  KInt toCopy = oldSize < newSize ? oldSize : newSize;
  auto holder = ObjHolder(thiz);
  auto dst = AllocArrayInstance(thiz->type_info(), newSize, OBJ_RESULT)->array();
  memcpy(
      PrimitiveArrayAddressOfElementAt<KChar>(dst, 0),
      PrimitiveArrayAddressOfElementAt<KChar>(holder.obj()->array(), 0),
      toCopy * sizeof(KChar));
  RETURN_OBJ(dst->obj());
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_CharArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KShort Kotlin_ShortArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KShort>(thiz, index);
}

HAS_SAFEPOINT_THROW
KShort Kotlin_ShortArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KShort, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_ShortArray_set(KRef thiz, KInt index, KShort value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_ShortArray_set_without_BoundCheck(KRef thiz, KInt index, KShort value) {
  PrimitiveArraySet<KShort, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_ShortArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KInt Kotlin_IntArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KInt>(thiz, index);
}

HAS_SAFEPOINT_THROW
KInt Kotlin_IntArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KInt, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_IntArray_set(KRef thiz, KInt index, KInt value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_IntArray_set_without_BoundCheck(KRef thiz, KInt index, KInt value) {
  PrimitiveArraySet<KInt, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_IntArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KByte value) {
  fillImpl<KByte>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_ShortArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KShort value) {
  fillImpl<KShort>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_CharArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KChar value) {
  fillImpl<KChar>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_IntArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KInt value) {
  fillImpl<KInt>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_LongArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KLong value) {
  fillImpl<KLong>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_FloatArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KFloat value) {
  fillImpl<KFloat>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_DoubleArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KDouble value) {
  fillImpl<KDouble>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_BooleanArray_fillImpl(KRef thiz, KInt fromIndex, KInt toIndex, KBoolean value) {
  fillImpl<KBoolean>(thiz, fromIndex, toIndex, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_ByteArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KByte>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_ShortArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KShort>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_CharArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KChar>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_IntArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KInt>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_LongArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KLong>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_FloatArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KFloat>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_DoubleArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KDouble>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
void Kotlin_BooleanArray_copyImpl(KConstRef thiz, KInt fromIndex,
                              KRef destination, KInt toIndex, KInt count) {
  copyImpl<KBoolean>(thiz, fromIndex, destination, toIndex, count);
}

HAS_SAFEPOINT_THROW
KLong Kotlin_LongArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KLong>(thiz, index);
}

HAS_SAFEPOINT_THROW
KLong Kotlin_LongArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KLong, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_LongArray_set(KRef thiz, KInt index, KLong value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_LongArray_set_without_BoundCheck(KRef thiz, KInt index, KLong value) {
  PrimitiveArraySet<KLong, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_LongArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KFloat Kotlin_FloatArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KFloat>(thiz, index);
}

HAS_SAFEPOINT_THROW
KFloat Kotlin_FloatArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KFloat, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_FloatArray_set(KRef thiz, KInt index, KFloat value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_FloatArray_set_without_BoundCheck(KRef thiz, KInt index, KFloat value) {
  PrimitiveArraySet<KFloat, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_FloatArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KDouble Kotlin_DoubleArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KDouble>(thiz, index);
}

HAS_SAFEPOINT_THROW
KDouble Kotlin_DoubleArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KDouble, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_DoubleArray_set(KRef thiz, KInt index, KDouble value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_DoubleArray_set_without_BoundCheck(KRef thiz, KInt index, KDouble value) {
  PrimitiveArraySet<KDouble, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_DoubleArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KBoolean Kotlin_BooleanArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KBoolean>(thiz, index);
}

HAS_SAFEPOINT_THROW
KBoolean Kotlin_BooleanArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KBoolean, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_BooleanArray_set(KRef thiz, KInt index, KBoolean value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_BooleanArray_set_without_BoundCheck(KRef thiz, KInt index, KBoolean value) {
  PrimitiveArraySet<KBoolean, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_BooleanArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_NativePtrArray_get(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KNativePtr>(thiz, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_NativePtrArray_get_without_BoundCheck(KConstRef thiz, KInt index) {
  return PrimitiveArrayGet<KNativePtr, false>(thiz, index);
}

HAS_SAFEPOINT_THROW
void Kotlin_NativePtrArray_set(KRef thiz, KInt index, KNativePtr value) {
  PrimitiveArraySet(thiz, index, value);
}

HAS_SAFEPOINT_THROW
void Kotlin_NativePtrArray_set_without_BoundCheck(KRef thiz, KInt index, KNativePtr value) {
  PrimitiveArraySet<KNativePtr, false>(thiz, index, value);
}

NO_SAFEPOINT
ALWAYS_INLINE KInt Kotlin_NativePtrArray_getArrayLength(KConstRef thiz) {
  const ArrayHeader* array = thiz->array();
  return array->count_;
}

HAS_SAFEPOINT
OBJ_GETTER(Kotlin_ImmutableBlob_toByteArray, KConstRef thiz, KInt startIndex, KInt endIndex) {
  KInt count = thiz->array()->count_;
  if (startIndex < 0 || endIndex > count || startIndex > endIndex) {
    ThrowArrayIndexOutOfBoundsException();
  }
  KInt len = endIndex - startIndex;
  auto holder = ObjHolder(thiz);
  auto dst = AllocArrayInstance(theByteArrayTypeInfo, len, OBJ_RESULT)->array();
  memcpy(PrimitiveArrayAddressOfElementAt<KByte>(dst, 0),
         PrimitiveArrayAddressOfElementAt<KByte>(holder.obj()->array(), startIndex),
         len);
  RETURN_OBJ(dst->obj());
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_ImmutableBlob_asCPointerImpl(KRef thiz, KInt offset) {
  ArrayHeader* array = thiz->array();
  // We couldn't have created an array bigger than max KInt value.
  // So if index is < 0, conversion to an unsigned value would make it bigger
  // than the array size.
  if (static_cast<uint32_t>(offset) > array->count_)  {
    ThrowArrayIndexOutOfBoundsException();
  }
  return PrimitiveArrayAddressOfElementAt<KByte>(array, offset);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getByteArrayAddressOfElement(KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return AddressOfElementAt<KByte>(array, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getCharArrayAddressOfElement (KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return CharArrayAddressOfElementAt(array, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getShortArrayAddressOfElement(KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return AddressOfElementAt<KShort>(array, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getIntArrayAddressOfElement(KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return AddressOfElementAt<KInt>(array, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getLongArrayAddressOfElement(KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return AddressOfElementAt<KLong>(array, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getFloatArrayAddressOfElement(KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return AddressOfElementAt<KFloat>(array, index);
}

HAS_SAFEPOINT_THROW
KNativePtr Kotlin_Arrays_getDoubleArrayAddressOfElement(KRef thiz, KInt index) {
  ArrayHeader* array = thiz->array();
  boundsCheck(array, index);

  return AddressOfElementAt<KDouble>(array, index);
}

}  // extern "C"
