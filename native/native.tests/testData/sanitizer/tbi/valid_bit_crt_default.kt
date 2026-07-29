// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi
// Documents: CRT default path does NOT SetValid() (bit59); full dynamic SetValid needs Custom+STACKMAP.
// (gc/alloc/sanitizer follow suite defaults — not forced to crt+cmc here.)

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun main() {
    val xs = List(64) { "c03-$it" }
    println("TBI-C03-CRT-DEFAULT OK size=${xs.size}")
}
