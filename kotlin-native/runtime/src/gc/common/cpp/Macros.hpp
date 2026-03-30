#pragma once
#include <cstdint>

namespace kotlin::stackMap {
#define ATTR_NO_INLINE __attribute__((noinline))
using SlotBits = uint32_t;
#if defined(__APPLE__)
    using Uptr = uint64_t;
#else
    using Uptr = uintptr_t;
#endif

using SlotBias = int32_t;
using BitsMapSize = uint32_t;

enum StackMapFormat : uint8_t {
  STACKMAP_BITMAP = 0,
  STACKMAP_COMPRESSED_BITMAP = 1,
};
} // namespace kotlin::stackMap