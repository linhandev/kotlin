// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: use-after-free.
//   Allocator: C malloc (libc).
//   Corrupter: Kotlin in C callback (CPointer write after C already freed).
//   Memory: C heap (malloc), freed before the callback runs.
//   Pointer: C malloc, free, then callback with the dangling pointer; Kotlin writes (callback use-after-free).

// MODULE: cinterop
// FILE: mallocHeap_kotlinCallback_useAfterFree.def
---
#include <stdlib.h>
typedef void (*writer_cb)(char* p);
static char* c_malloc_n(int n) { return (char*)malloc((size_t)n); }
static void free_then_cb(char* p, writer_cb cb) {
    free(p);
    cb(p);
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import mallocHeap_kotlinCallback_useAfterFree.*

fun main() {
    val p = c_malloc_n(8)!!
    free_then_cb(p, staticCFunction { q ->
        q!![0] = 1
    })
}
