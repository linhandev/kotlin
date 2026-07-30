// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: alloc=CUSTOM
//
//   Problem type: out-of-bounds write.
//   Allocator: Kotlin garbage-collected heap (ByteArray).
//   Corrupter: C memset via interop (fixture c_pin_oob).
//   Memory: garbage-collected heap object interior while pinned.
//   Pointer: ByteArray.usePinned -> addressOf passed into C; C writes out of bounds.
//   CUSTOM: IGNORE — SafeAlloc is raw mmap with no ASAN poison / HWASAN tags; ADDRESS and
//   HWADDRESS both exit 0. STD allocator catches the same OOB.

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
