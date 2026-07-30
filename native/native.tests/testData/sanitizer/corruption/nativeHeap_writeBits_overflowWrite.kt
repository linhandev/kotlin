// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds write.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap).
//   Corrupter: writeBits via interop (kotlinx.cinterop bit poke into native memory).
//   Memory: cinterop native memory (interop malloc).
//   Pointer: nativeHeap.allocArray raw NativePtr; writeBits with bit offset past the allocation.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(8)
    // 64-bit offset past 8-byte allocation
    writeBits(p.rawValue, offset = 64L, size = 8, value = 0xFF)
}
