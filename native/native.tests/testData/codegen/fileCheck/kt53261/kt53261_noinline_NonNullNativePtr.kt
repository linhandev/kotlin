// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs
// DISABLE_IR_VISIBILITY_CHECKS: ANY
// IGNORE_NATIVE: optimizationMode=DEBUG
// IGNORE_NATIVE: optimizationMode=NO

import kotlinx.cinterop.*

// CHECK-STACKMAP-AAPCS-OPT-LABEL: define i1 @"kfun:kotlin.native.internal.NonNullNativePtr#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-AAPCS-OPT-LABEL: define i1 @"kfun:kotlin.native.internal.NonNullNativePtr#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-DEFAULTABI-OPT-LABEL: define zeroext i1 @"kfun:kotlin.native.internal.NonNullNativePtr#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-DEFAULTABI-OPT-LABEL: define zeroext i1 @"kfun:kotlin.native.internal.NonNullNativePtr#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-WINDOWSX64-OPT-LABEL: define zeroext i1 @"kfun:kotlin.native.internal.NonNullNativePtr#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-WINDOWSX64-OPT-LABEL: define zeroext i1 @"kfun:kotlin.native.internal.NonNullNativePtr#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-OPT: call ptr @"kfun:kotlin.native.internal#<NonNullNativePtr-unbox>(kotlin.Any?){}kotlin.native.internal.NonNullNativePtr?"

@kotlinx.cinterop.ExperimentalForeignApi
fun box(): String = memScoped {
    val var1: IntVar = alloc()
    val var2: IntVar = alloc()
    // The first one is K1, the second one is K2.
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    return if (var1.ptr.value as Any == var2.ptr.value as Any) "FAIL" else "OK"
}
