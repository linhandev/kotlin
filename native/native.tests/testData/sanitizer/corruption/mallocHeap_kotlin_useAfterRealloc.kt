// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
// Scenario: realloc invalidates old pointer (use-after-realloc).
//   Problem type: use-after-free.
//   Allocator: platform C malloc via cinterop; resized with realloc in fixture C.
//   Corrupter: Kotlin write through the pre-realloc pointer after C realloc moves the block.
//   Memory: C heap.
//   Pointer: malloc -> C realloc to a much larger size; retry until the block moves, then
//   write via the old pointer (same-pointer realloc is still valid — would exit 0).

// MODULE: cinterop
// FILE: mallocHeap_kotlin_useAfterRealloc.def
---
#include <stdlib.h>
/* Grow far enough that a move is typical; always return the new pointer. */
static char* c_realloc_large(char* p) {
    return (char*)realloc(p, (size_t)1 << 20);
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import mallocHeap_kotlin_useAfterRealloc.*

fun main() {
    // Must observe a moved block: same-pointer realloc leaves old valid (HWASAN exit 0).
    repeat(64) {
        val old = platform.posix.malloc(16.convert())!!.reinterpret<ByteVar>()
        val neu = c_realloc_large(old)!!
        if (neu.rawValue != old.rawValue) {
            old[0] = 0x41
            platform.posix.free(neu)
            return
        }
        platform.posix.free(neu)
    }
    error("realloc never moved; cannot exercise use-after-realloc")
}
