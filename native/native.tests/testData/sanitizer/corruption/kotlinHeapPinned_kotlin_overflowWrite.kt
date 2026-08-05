// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: alloc=CUSTOM&&pagedAllocator=UNSPECIFIED
// IGNORE_NATIVE: alloc=CUSTOM&&pagedAllocator=TRUE
// IGNORE_NATIVE: gcType=CMC
//
//   Problem type: out-of-bounds write.
//   Allocator: Kotlin garbage-collected heap (ByteArray).
//   Corrupter: Kotlin (CPointer index write).
//   Memory: garbage-collected heap object interior (array elements).
//   Pointer: ByteArray.usePinned -> Pinned.addressOf(0); Kotlin writes past array length.
//   Expect detection under STD and CUSTOM+pagedAllocator=false only.
//   CUSTOM default/paged=true: IGNORE — SafeAlloc mmap has no ASAN poison / HWASAN tags.
//   CMC/CRT: IGNORE — same mmap-style heap; not expected to detect.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val arr = ByteArray(8)
    arr.usePinned { pinned ->
        val p = pinned.addressOf(0)
        p[8] = 0x41
    }
}
