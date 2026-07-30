// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
// Scenario: ECO-20..23 wrong-side free (C malloc then nativeHeap.free, then C free).
//   Problem type: double-free.
//   Allocator: platform C malloc via cinterop (system libc.so).
//   Corrupter: kotlinx.cinterop nativeHeap.free then platform C free on the same pointer.
//   Memory: C heap allocation.
//   Pointer: malloc; nativeHeap.free (wrong API ownership) then free again — models libraries
//   that release non-nativeHeap buffers through nativeHeap.free then also free on close.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import platform.posix.free
import platform.posix.malloc

fun main() {
    val p = malloc(16.convert())!!.reinterpret<ByteVar>()
    nativeHeap.free(p)
    free(p)
}
