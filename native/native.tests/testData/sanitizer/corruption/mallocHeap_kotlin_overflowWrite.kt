// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds write.
//   Allocator: C malloc (libc).
//   Corrupter: Kotlin (CPointer index write).
//   Memory: C heap (malloc).
//   Pointer: C malloc returned as CPointer; Kotlin out-of-bounds write (C-to-Kotlin).

// MODULE: cinterop
// FILE: mallocHeap_kotlin_overflowWrite.def
---
#include <stdlib.h>
static char* c_malloc_n(int n) { return (char*)malloc((size_t)n); }

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import mallocHeap_kotlin_overflowWrite.*

fun main() {
    val p = c_malloc_n(8)!!
    p[8] = 0xEE.toByte()
}
