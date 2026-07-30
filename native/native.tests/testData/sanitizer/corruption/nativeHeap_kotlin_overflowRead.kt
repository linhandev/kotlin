// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds read.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap).
//   Corrupter: Kotlin (CPointer index read).
//   Memory: cinterop native memory (interop malloc).
//   Pointer: nativeHeap.allocArray -> CPointer; out-of-bounds read via unbounded index.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    // OOB read
    println(p[8])
}
