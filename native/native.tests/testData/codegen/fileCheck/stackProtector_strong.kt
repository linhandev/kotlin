// TARGET_BACKEND: NATIVE
// DISABLE_NATIVE: targetFamily=MINGW
// FILECHECK_STAGE: StackProtectorPhase
// FREE_COMPILER_ARGS: -Xbinary=stackProtector=STRONG

// CHECK-STACKMAP: Function Attrs: sspstrong{{[[:space:]].*}}define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP: Function Attrs: sspstrong{{[[:space:]].*}}define ptr @"kfun:#box(){}kotlin.String"
fun box() = "OK"