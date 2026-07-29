// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
// Scenario: ownership-transfer invalid free (C model of bytesNoCopy / ObjC free on pin).
//   Problem type: invalid free.
//   Allocator: Kotlin garbage-collected heap (ByteArray), temporarily pinned.
//   Corrupter: platform C free via interop (system libc.so).
//   Memory: garbage-collected heap object interior (not a malloc allocation).
//   Pointer: ByteArray.usePinned -> addressOf(0) passed to free — models NSData/C taking
//   ownership of a pinned buffer and freeing it (Darwin bytesNoCopy anti-pattern).
//   HWADDRESS: addressOf(0) is an interior pointer into the object (not malloc base); same
//   miss class as free(p+1) on OHOS HWASAN. free(mmap) can CHECK-fail in C demos, but GC pin
//   typically exits 0. Keep HWADDRESS FAIL as detector gap (do not IGNORE).
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import platform.posix.free

fun main() {
    val arr = ByteArray(16)
    arr.usePinned { pinned ->
        free(pinned.addressOf(0))
    }
}
