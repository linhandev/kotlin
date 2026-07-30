// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: sanitizer=HWADDRESS
//
//   Problem type: invalid free.
//   Allocator: platform C malloc via cinterop (system libc.so).
//   Corrupter: platform C free via cinterop (system libc.so).
//   Memory: C heap allocation.
//   Pointer: Kotlin offsets the malloc result by one byte and passes the interior pointer to free.
//   ADDRESS: reports invalid free.
//   HWADDRESS: IGNORE — pure C + CPF -fsanitize=hwaddress on device also exits 0 after free(p+1)
//   (system libclang_rt.hwasan.so does not flag interior free).
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import platform.posix.free
import platform.posix.malloc

fun main() {
    val p = malloc(16.convert())!!.reinterpret<ByteVar>()
    free(p + 1)
}
