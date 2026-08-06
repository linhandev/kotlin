// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: OptimizeTLSDataLoads

class Wrapper(x: Int)

// CHECK-STACKMAP-LABEL: define internal fastcc ptr addrspace(1) @"kfun:#f(kotlin.Int;kotlin.String){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define internal fastcc ptr @"kfun:#f(kotlin.Int;kotlin.String){}kotlin.String"
fun f(x: Int, s: String): String {
    // https://youtrack.jetbrains.com/issue/KT-64880/K-N-EnterFrame-runtime-function-should-be-always-inlined-in-OPT-mode
    // Remove `|call fastcc void @EnterFrame` below, after KT-64880 is fixed
    // `call .. @EnterFrame` may or may not be inlined.
    // - in case it would be inlined, several `load .. currentThreadDataNode` would happen, and only first of them must stay
    // after `OptimizeTLSDataLoads` optimizaion phase.
    // - in case of no-inline, several `call .. @EnterFrame` may remain in function code.

    // Stack-map mode does not imply that the x28 GC fast path is enabled.
    // CHECK-NOSTACKMAP: {{_ZN6kotlin2mm14ThreadRegistry22currentThreadDataNode_E|call fastcc void @EnterFrame}}
    // CHECK-NOSTACKMAP-NOT: _ZN6kotlin2mm14ThreadRegistry22currentThreadDataNode_E
    // macOS uses precise stack maps without the x28 GC fast path in this lane.
    // CHECK-MACOS_ARM64: {{_ZN6kotlin2mm14ThreadRegistry22currentThreadDataNode_E|call fastcc void @EnterFrame}}
    // CHECK-MACOS_ARM64-NOT: _ZN6kotlin2mm14ThreadRegistry22currentThreadDataNode_E
    // OHOS keeps thread data in x28, so no TLS load or EnterFrame is needed.
    // CHECK-OHOS_ARM64-NOT: {{_ZN6kotlin2mm14ThreadRegistry22currentThreadDataNode_E|call fastcc void @EnterFrame}}
    if (x < 0) throw IllegalStateException()
    if (x > 0) return f(x - 1, s)
    val b = Wrapper(2)
    val a = listOf(x, x, Wrapper(1), 2, x)
    return buildString {
        for (i in a) { appendLine("$s i") }
    }
// CHECK-LABEL: epilogue:
}

fun box(): String {
    val result = f(10, "123456")
    return if (result == "123456 i\n" +
        "123456 i\n" +
        "123456 i\n" +
        "123456 i\n" +
        "123456 i\n")
        "OK"
    else "FAIL: $result"
}
