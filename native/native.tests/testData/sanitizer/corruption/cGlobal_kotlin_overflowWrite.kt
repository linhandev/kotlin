// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
// IGNORE_NATIVE: sanitizer=HWADDRESS
//
//   Problem type: out-of-bounds write.
//   Allocator: C / ELF global (static) storage.
//   Corrupter: Kotlin (CPointer index write).
//   Memory: global / static array.
//   Pointer: cinterop-exported global array; Kotlin out-of-bounds write.
//   ADDRESS: reports (ASAN global redzone).
//   HWADDRESS: IGNORE — OHOS HWASAN does not report this even with a CPF-instrumented C TU
//   (exit 0); ROM globals weak.

// MODULE: cinterop
// FILE: cGlobal_kotlin_overflowWrite.def
---
char g_buf[8] = {0};

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import kotlinx.cinterop.*
import cGlobal_kotlin_overflowWrite.*

fun main() {
    g_buf[8] = 1
}