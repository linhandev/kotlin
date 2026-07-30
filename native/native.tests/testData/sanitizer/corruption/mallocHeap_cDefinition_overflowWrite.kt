// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds write.
//   Allocator: C malloc (libc).
//   Corrupter: user C via cinterop (direct indexed store past length).
//   Memory: C heap (malloc).
//   Pointer: malloc inside C; pure C out-of-bounds write (baseline).
//   Note: volatile index store (not memset) so ADDRESS/HWADDRESS prove KN's
//   final sanitizer pass instrumented the cinterop C body, not a libc interceptor.

// MODULE: cinterop
// FILE: mallocHeap_cDefinition_overflowWrite.def
---
#include <stdlib.h>
static volatile char clc_sink;
static void c_only_oob(void) {
    char* p = (char*)malloc(8);
    volatile size_t i = 16;
    p[i] = 0xAA;
    clc_sink = p[0];
    free(p);
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import mallocHeap_cDefinition_overflowWrite.*

fun main() {
    c_only_oob()
}
