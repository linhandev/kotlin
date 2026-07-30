// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds write.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap).
//   Corrupter: Kotlin in C callback (CPointer index write).
//   Memory: cinterop native memory (interop malloc).
//   Pointer: nativeHeap.allocArray passed through C invoke into Kotlin callback; out-of-bounds write in callback.

// MODULE: cinterop
// FILE: nativeHeap_kotlinCallback_overflowWrite.def
---
typedef void (*writer_cb)(char* p);
static void invoke_writer(char* p, writer_cb cb) { cb(p); }

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import nativeHeap_kotlinCallback_overflowWrite.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    invoke_writer(p, staticCFunction { q ->
        q!![8] = 9
    })
}
