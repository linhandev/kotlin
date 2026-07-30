// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: double-free.
//   Allocator: C malloc (libc).
//   Corrupter: C free via interop (fixture c_free_p twice).
//   Memory: C heap (malloc).
//   Pointer: C malloc; double-free via c_free (not allocator mismatch — OHOS ASAN_OPTIONS disables alloc_dealloc_mismatch).

// MODULE: cinterop
// FILE: mallocHeap_cDefinition_doubleFree.def
---
#include <stdlib.h>
static char* c_malloc_n(int n) { return (char*)malloc((size_t)n); }
static void c_free_p(char* p) { free(p); }

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import mallocHeap_cDefinition_doubleFree.*

fun main() {
    val p = c_malloc_n(8)!!
    // true double-free (allocator mismatch is disabled via ASAN_OPTIONS=alloc_dealloc_mismatch=0)
    c_free_p(p)
    c_free_p(p)
}
