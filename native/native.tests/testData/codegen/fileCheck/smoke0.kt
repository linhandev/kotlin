// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

// CHECK-LABEL: "kfun:#id(kotlin.Any?){}kotlin.Any?"
fun id(a: Any?): Any? {
    return a
// CHECK-LABEL: epilogue:
}

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
fun box(): String {
    // CHECK-STACKMAP: call ptr addrspace(1) @"kfun:#id(kotlin.Any?){}kotlin.Any?"
    // CHECK-NOSTACKMAP: call ptr @"kfun:#id(kotlin.Any?){}kotlin.Any?"
    val x = id("Hello")
    // CHECK-STACKMAP: call void @"kfun:kotlin.io#println(kotlin.Any?){}"(ptr addrspace(1) {{.*}})
    // CHECK-NOSTACKMAP: call void @"kfun:kotlin.io#println(kotlin.Any?){}"(ptr {{.*}})
    println(x)
// CHECK-LABEL: epilogue:
    return "OK"
}