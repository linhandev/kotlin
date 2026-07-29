// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: allocation size overflow to 1, followed by out-of-bounds write to index 1.
//   Allocator: kotlinx.cinterop.nativeHeap.
//   Corrupter: Kotlin (CPointer index write).
//   Memory: cinterop native memory.
//   Pointer: allocArray<LongVar> multiplies an unchecked length by 8; the product wraps to 8 bytes.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val wrappedLength = (1L shl 61) + 1L
    val p = nativeHeap.allocArray<LongVar>(wrappedLength)
    p[1] = 0x41
}
