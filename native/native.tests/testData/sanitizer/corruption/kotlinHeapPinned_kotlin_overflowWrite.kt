// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: alloc=CUSTOM
//
//   Problem type: out-of-bounds write.
//   Allocator: Kotlin garbage-collected heap (ByteArray).
//   Corrupter: Kotlin (CPointer index write).
//   Memory: garbage-collected heap object interior (array elements).
//   Pointer: ByteArray.usePinned -> Pinned.addressOf(0); Kotlin writes past array length.
//   CUSTOM: IGNORE — SafeAlloc is raw mmap with no ASAN poison / HWASAN tags; ADDRESS and
//   HWADDRESS both exit 0. STD allocator catches the same OOB.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val arr = ByteArray(8)
    arr.usePinned { pinned ->
        val p = pinned.addressOf(0)
        p[8] = 0x41
    }
}
