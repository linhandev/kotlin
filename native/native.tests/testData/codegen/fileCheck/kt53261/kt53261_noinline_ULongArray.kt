// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

// CHECK-STACKMAP-AAPCS-OPT-LABEL: define i1 @"kfun:kotlin.ULongArray#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-AAPCS-OPT-LABEL: define i1 @"kfun:kotlin.ULongArray#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-DEFAULTABI-OPT-LABEL: define zeroext i1 @"kfun:kotlin.ULongArray#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-DEFAULTABI-OPT-LABEL: define zeroext i1 @"kfun:kotlin.ULongArray#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-WINDOWSX64-OPT-LABEL: define zeroext i1 @"kfun:kotlin.ULongArray#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-WINDOWSX64-OPT-LABEL: define zeroext i1 @"kfun:kotlin.ULongArray#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"

// CHECK-STACKMAP-OPT: call ptr addrspace(1) @"kfun:kotlin#<ULongArray-unbox>(kotlin.Any?){}kotlin.ULongArray?"
// CHECK-NOSTACKMAP-OPT: call ptr @"kfun:kotlin#<ULongArray-unbox>(kotlin.Any?){}kotlin.ULongArray?"

// CHECK-LABEL: epilogue:

fun box(): String {
    val arr1 = ULongArray(10) { it.toULong() }
    val arr2 = ULongArray(10) { (it / 2).toULong() }
    return if (arr1 == arr2) "FAIL" else "OK"
}
