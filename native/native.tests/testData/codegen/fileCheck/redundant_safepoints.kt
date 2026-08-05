// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: RemoveRedundantSafepoints

// This test checks:
// - there is only one safepoint per function
// - safepoint function is inlined in OPT mode, unless SMALLBINARY is needed (for ex, watchos_arm32)
// Might fail under -Xbinary=gc=stwms and -Xbinary=gc=noop. In this case, just add ignore clause.

@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
import kotlin.native.Retain

class C

fun f(): Any {
    return C()
}

fun g() = f()

// CHECK-STACKMAP-LABEL: define {{(noundef )?}}ptr addrspace(1) @"kfun:#h(kotlin.Boolean){}kotlin.Any"
// CHECK-NOSTACKMAP-LABEL: define {{(noundef )?}}ptr @"kfun:#h(kotlin.Boolean){}kotlin.Any"
@Retain
fun h(cond: Boolean): Any {
    // We have to check actual _call_ to a function, not just callee mention.
    // CHECK-SMALLBINARY: {{call .*Kotlin_mm_safePointFunctionPrologue\(\)}}
    // CHECK-NOSTACKMAP-BIGBINARY-OPT: _ZN12_GLOBAL__N_115safePointActionE
    // CHECK-STACKMAP-BIGBINARY-OPT: Kotlin_mm_safePointActionAddr

    // CHECK-NOSTACKMAP-SMALLBINARY-NOT: _ZN12_GLOBAL__N_115safePointActionE
    // CHECK-STACKMAP-SMALLBINARY-NOT: Kotlin_mm_safePointActionAddr
    // CHECK-BIGBINARY-OPT-NOT: {{call .*Kotlin_mm_safePointFunctionPrologue\(\)}}

    // CHECK-SMALLBINARY-NOT: {{call .*Kotlin_mm_safePointFunctionPrologue\(\)}}
    // CHECK-NOSTACKMAP-BIGBINARY-OPT-NOT: _ZN12_GLOBAL__N_115safePointActionE
    // CHECK-STACKMAP-BIGBINARY-OPT-NOT: Kotlin_mm_safePointActionAddr
    if (cond) {
        return listOf(C(), C())
    } else {
        return listOf(C(), C(), C())
    }
// CHECK-LABEL: ret
}

// CHECK-STACKMAP-LABEL: define {{(noundef )?}}{{(nonnull )?}}ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define {{(noundef )?}}{{(nonnull )?}}ptr @"kfun:#box(){}kotlin.String"
@Retain
fun box(): String {
    // CHECK-SMALLBINARY: {{call .*Kotlin_mm_safePointFunctionPrologue\(\)}}
    // CHECK-NOSTACKMAP-BIGBINARY-OPT: _ZN12_GLOBAL__N_115safePointActionE
    // CHECK-STACKMAP-BIGBINARY-OPT: Kotlin_mm_safePointActionAddr

    // CHECK-NOSTACKMAP-SMALLBINARY-NOT: _ZN12_GLOBAL__N_115safePointActionE
    // CHECK-STACKMAP-SMALLBINARY-NOT: Kotlin_mm_safePointActionAddr
    // CHECK-BIGBINARY-OPT-NOT: {{call .*Kotlin_mm_safePointFunctionPrologue\(\)}}

    // CHECK-SMALLBINARY-NOT: {{call .*Kotlin_mm_safePointFunctionPrologue\(\)}}
    // CHECK-NOSTACKMAP-BIGBINARY-OPT-NOT: _ZN12_GLOBAL__N_115safePointActionE
    // CHECK-STACKMAP-BIGBINARY-OPT-NOT: Kotlin_mm_safePointActionAddr
    println(g())
    println(h(true))
    return "OK"
// CHECK-LABEL: ret
}
