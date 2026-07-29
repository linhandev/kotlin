// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: use-after-free.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap).
//   Corrupter: Kotlin (CPointer write after free).
//   Memory: cinterop native memory (interop malloc, freed).
//   Pointer: nativeHeap.allocArray -> CPointer retained after nativeHeap.free; use-after-free write.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(16)
    nativeHeap.free(p)
    p[0] = 0x42
}
