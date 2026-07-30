// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds write.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap).
//   Corrupter: C memset via interop (fixture c_oob_write).
//   Memory: cinterop native memory (interop malloc).
//   Pointer: nativeHeap.allocArray handed to C; C out-of-bounds write (Kotlin-to-C).

// MODULE: cinterop
// FILE: nativeHeap_cDefinition_overflowWrite.def
---
#include <string.h>
static void c_oob_write(char* p, int n) {
    // intentionally write past n bytes
    memset(p, 0xCC, (size_t)(n + 16));
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import nativeHeap_cDefinition_overflowWrite.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    c_oob_write(p, 8)
}
