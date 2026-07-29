// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: double-free.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap).
//   Corrupter: C free via interop (nativeHeap.free twice).
//   Memory: cinterop native memory (interop malloc).
//   Pointer: nativeHeap.allocArray; nativeHeap.free called twice on the same block (double-free).
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    nativeHeap.free(p)
    nativeHeap.free(p)
}
