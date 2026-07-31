// FILECHECK_STAGE: CStubs

// FILE: main.kt

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
// CHECK-STACKMAP-NOT: call ptr addrspace(1) @"kfun:kotlin.Enum#<get-name>(){}kotlin.String"
// CHECK-NOSTACKMAP-NOT: call ptr @"kfun:kotlin.Enum#<get-name>(){}kotlin.String"
fun box() = Base1.OK.name

// FILE: lib.kt
enum class Base1 { OK }
