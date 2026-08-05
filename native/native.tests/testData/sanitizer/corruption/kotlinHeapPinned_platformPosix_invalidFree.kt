// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// DISABLE_NATIVE: sanitizer=HWADDRESS
//
// Scenario: ownership-transfer invalid free (C model of bytesNoCopy / ObjC free on pin).
//   Problem type: invalid free.
//   Allocator: Kotlin garbage-collected heap (ByteArray), temporarily pinned.
//   Corrupter: platform C free via interop (system libc.so).
//   Memory: garbage-collected heap object interior (not a malloc allocation).
//   Pointer: ByteArray.usePinned -> addressOf(0) passed to free — models NSData/C taking
//   ownership of a pinned buffer and freeing it (Darwin bytesNoCopy anti-pattern).
//   HWADDRESS: DISABLE — same miss class as free(p+1): pure-C + CPF -fsanitize=hwaddress on
//   device yields Signal 11 / memory-map dump with no "HWAddressSanitizer:" / "sanitizer"
//   banner (and no stable invalid-free report). IGNORE is unsafe here: some cells
//   intermittently report invalid-free (exit != 0), which flips expected-failure to
//   "did not fail as expected". ADDRESS still runs.
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import platform.posix.free

fun main() {
    val arr = ByteArray(16)
    arr.usePinned { pinned ->
        free(pinned.addressOf(0))
    }
}
