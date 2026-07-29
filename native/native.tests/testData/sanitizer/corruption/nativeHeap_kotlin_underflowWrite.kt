// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
// Scenario: heap underflow write (front redzone / granule before allocation).
//   Problem type: out-of-bounds write.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory).
//   Corrupter: Kotlin (CPointer index write before the allocation base).
//   Memory: cinterop native memory.
//   Pointer: allocArray then write at index -1 (underflow; distinct from past-the-end overflow).
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    p[-1] = 0x41
}
