// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs
// FREE_COMPILER_ARGS: -Xbinary=preCodegenInlineThreshold=40
// FREE_COMPILER_ARGS: -opt-in=kotlin.experimental.ExperimentalNativeApi
import kotlin.native.NoInline

// CHECK-STACKMAP-OPT-NOT: define ptr addrspace(1) @"kfun:#foo(){}kotlin.String"
// CHECK-NOSTACKMAP-OPT-NOT: define ptr @"kfun:#foo(){}kotlin.String"
fun foo(): String {
    return "O"
}

// CHECK-STACKMAP: define ptr addrspace(1) @"kfun:#bar(){}kotlin.String"
// CHECK-NOSTACKMAP: define ptr @"kfun:#bar(){}kotlin.String"
@NoInline
fun bar(): String {
    return "K"
}

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
@NoInline
fun box(): String {
    // CHECK-NOT: {call|invoke} ptr @"kfun:#foo(){}kotlin.String"
    // CHECK-STACKMAP: call ptr addrspace(1) @"kfun:#bar(){}kotlin.String"
    // CHECK-NOSTACKMAP: call ptr @"kfun:#bar(){}kotlin.String"
    return foo() + bar()
}
