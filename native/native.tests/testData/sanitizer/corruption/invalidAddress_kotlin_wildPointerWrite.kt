// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: sanitizer=HWADDRESS
//
// Scenario: wild pointer write to an unmapped address.
//   Problem type: wild pointer write.
//   Allocator: none (fabricated address).
//   Corrupter: Kotlin (CPointer write through fabricated address).
//   Memory: not a valid allocation (wild / unmapped address).
//   Pointer: Long.toCPointer with a bogus address; write through the wild CPointer.
//   ADDRESS: ASAN deadly-signal handler wraps kernel SEGV as "AddressSanitizer: SEGV on
//   unknown address" (no shadow/redzone check of 0x1000) — matches OUTPUT_REGEX.
//   HWADDRESS: IGNORE — OHOS HWASAN typically delivers Signal 11 with no "sanitizer" banner
//   (same for pure C write to 0x1000). Neither detector uses its own mechanism here; do not
//   rewrite into mistagged-heap tag-mismatch.

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*

fun main() {
    val p = 0x1000L.toCPointer<ByteVar>()!!
    p[0] = 0x42
}
