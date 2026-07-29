// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: out-of-bounds read.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — C-style native memory, not the Kotlin garbage-collected heap; source buffer).
//   Corrupter: C memcpy via Kotlin/Native runtime (Kotlin_CPointer_CopyMemory from MutableData.append).
//   Memory: cinterop native memory source (interop malloc); MutableData grows its destination first.
//   Pointer: deprecated MutableData.append(COpaquePointer, count) reads count bytes past the source allocation.

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
@file:Suppress("DEPRECATION", "DEPRECATION_ERROR")
import kotlinx.cinterop.*
import kotlin.native.concurrent.MutableData

fun main() {
    val src = nativeHeap.allocArray<ByteVar>(8)
    for (i in 0 until 8) src[i] = i.toByte()
    val md = MutableData(8)
    // Destination is resized to 64 bytes; CopyMemory over-reads the 8-byte src nativeHeap array.
    md.append(src, 64)
}
