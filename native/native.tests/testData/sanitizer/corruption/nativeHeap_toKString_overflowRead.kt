// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds read.
//   Allocator: kotlinx.cinterop.nativeHeap.
//   Corrupter: kotlinx.cinterop C-string conversion.
//   Memory: cinterop native memory.
//   Pointer: CPointer.toKString scans beyond an allocation that has no NUL terminator.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    for (i in 0 until 8) p[i] = 'A'.code.toByte()
    println(p.toKString())
}
