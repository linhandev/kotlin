// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: use-after-free.
//   Allocator: memScoped (representing kotlinx.cinterop Arena-managed memory). Storage is still
//   kotlinx.cinterop.nativeHeap / Kotlin_interop_malloc — not the Kotlin garbage-collected heap,
//   not the CPU stack. Also public (less common): kotlinx.cinterop.Arena with explicit Arena.clear().
//   Corrupter: Kotlin (CPointer write after scope clear).
//   Memory: cinterop native memory with lifetime owned by memScoped/Arena.
//   Pointer: allocArray inside memScoped; pointer escapes the scope; write after automatic clear.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val escaped = memScoped {
        allocArray<ByteVar>(8)
    }
    escaped[0] = 1
}
