// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
// Scenario: ECO-20 wrong-side free / double-free (ktor#1149-shaped path).
//   Problem type: double-free.
//   Allocator: kotlinx.cinterop.nativeHeap (Kotlin_interop_malloc — same underlying malloc as libc).
//   Corrupter: platform C free via interop, then kotlinx.cinterop nativeHeap.free on the same pointer.
//   Memory: cinterop native memory.
//   Pointer: nativeHeap.allocArray; free once via platform.posix.free, again via nativeHeap.free
//   (cross API surface; ASAN sees double-free — alloc_dealloc_mismatch is disabled on OHOS).
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import platform.posix.free

fun main() {
    val p = nativeHeap.allocArray<ByteVar>(16)
    free(p)
    nativeHeap.free(p)
}
