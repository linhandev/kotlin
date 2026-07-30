// KIND: STANDALONE_NO_TR
// EXIT_CODE: !0
// OUTPUT_REGEX: (?i).*sanitizer.*
// FREE_COMPILER_ARGS: -opt-in=kotlinx.cinterop.ExperimentalForeignApi
//
//   Problem type: stack-use-after-return.
//   Allocator: C stack (local array in a returned frame).
//   Corrupter: user C direct store after the allocating frame returned.
//   Memory: C stack buffer that is no longer live.
//   Pointer: noinline helper returns &local[0]; caller writes through the dangling pointer.
//   Note: OhosExecutor sets ASAN detect_stack_use_after_return=1. True shape is SUAR.
//   On OHOS with ROM libclang_rt.asan.so + host-clang-instrumented cinterop C, ADDRESS currently
//   reports SEGV (fake-stack / shadow ABI mismatch) rather than the named diagnosis — accept SEGV
//   until a matching compiler-rt is used. DEBUG/NO enable KN runtime assertions that abort on this
//   device before main (napi_create_external_string_utf16), so OPT is the runnable path.

// MODULE: cinterop
// FILE: cStack_cDefinition_stackUseAfterReturn.def
---
static volatile char suar_sink;
__attribute__((noinline)) static char* leak_stack_buf(void) {
    char local[64];
    local[0] = 'A';
    suar_sink = local[0];
    return &local[0]; // local is out of scope after return
}
__attribute__((noinline)) static void use_dangling(char* p) {
    p[0] = 'Z';
    suar_sink = p[0];
}
static void run_suar(void) {
    char* p = leak_stack_buf();
    use_dangling(p);
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
import cStack_cDefinition_stackUseAfterReturn.*

fun main() {
    run_suar()
}
// 问题