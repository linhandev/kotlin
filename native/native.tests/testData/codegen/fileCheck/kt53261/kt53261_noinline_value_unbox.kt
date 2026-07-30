// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

// CHECK-STACKMAP-AAPCS-LABEL: define i1 @"kfun:C#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-AAPCS-LABEL: define i1 @"kfun:C#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-DEFAULTABI-LABEL: define zeroext i1 @"kfun:C#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-DEFAULTABI-LABEL: define zeroext i1 @"kfun:C#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-WINDOWSX64-LABEL: define zeroext i1 @"kfun:C#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-WINDOWSX64-LABEL: define zeroext i1 @"kfun:C#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP: call ptr addrspace(1) @"kfun:#<C-unbox>(kotlin.Any?){}C?"
// CHECK-NOSTACKMAP: call ptr @"kfun:#<C-unbox>(kotlin.Any?){}C?"
value class C(val x: Any)
// Note: <C-unbox> is also called from bridges for equals, hashCode and toString.

fun box() =
    if (C(42) == C(13)) "FAIL" else "OK"
