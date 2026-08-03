// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

class A(val x: Int)

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
fun box(): String {
    // CHECK-NOSTACKMAP-DEBUG: call ptr @AllocInstance
    // CHECK-STACKMAP-DEBUG: call ptr addrspace(1) @AllocInstance
    // CHECK-NOSTACKMAP-OPT: alloca %"kclassbody:A#internal"
    // CHECK-STACKMAP-OPT: call ptr addrspace(1) @AllocInstance
    val a = A(5)
    println(a.x)
// CHECK-LABEL: epilogue:
    return "OK"
}
