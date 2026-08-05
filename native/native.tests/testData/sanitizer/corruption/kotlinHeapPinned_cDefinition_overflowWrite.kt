// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: alloc=CUSTOM&&pagedAllocator=UNSPECIFIED
// IGNORE_NATIVE: alloc=CUSTOM&&pagedAllocator=TRUE
// IGNORE_NATIVE: gcType=CMC
//
//   Problem type: out-of-bounds write.
//   Allocator: Kotlin garbage-collected heap (ByteArray).
//   Corrupter: C memset via interop (fixture c_pin_oob).
//   Memory: garbage-collected heap object interior while pinned.
//   Pointer: ByteArray.usePinned -> addressOf passed into C; C writes out of bounds.
//   Expect detection under STD and CUSTOM+pagedAllocator=false only.
//   CUSTOM default/paged=true: IGNORE — SafeAlloc mmap has no ASAN poison / HWASAN tags.
//   CMC/CRT: IGNORE — same mmap-style heap; not expected to detect.

// MODULE: cinterop
// FILE: kotlinHeapPinned_cDefinition_overflowWrite.def
---
#include <string.h>
static void c_pin_oob(char* p, int n) {
    memset(p, 0xCC, (size_t)(n + 16));
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import kotlinHeapPinned_cDefinition_overflowWrite.*

fun main() {
    val arr = ByteArray(8)
    arr.usePinned { pinned ->
        c_pin_oob(pinned.addressOf(0), 8)
    }
}
