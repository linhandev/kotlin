// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs
// IGNORE_NATIVE: optimizationMode=DEBUG
// IGNORE_NATIVE: optimizationMode=NO

import kotlinx.cinterop.*

// CHECK-STACKMAP-AAPCS-OPT-LABEL: define i1 @"kfun:kotlinx.cinterop.StableRef#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-AAPCS-OPT-LABEL: define i1 @"kfun:kotlinx.cinterop.StableRef#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-DEFAULTABI-OPT-LABEL: define zeroext i1 @"kfun:kotlinx.cinterop.StableRef#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-DEFAULTABI-OPT-LABEL: define zeroext i1 @"kfun:kotlinx.cinterop.StableRef#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)
// CHECK-STACKMAP-WINDOWSX64-OPT-LABEL: define zeroext i1 @"kfun:kotlinx.cinterop.StableRef#equals(kotlin.Any?){}kotlin.Boolean"(ptr addrspace(1) %0, ptr addrspace(1) %1)
// CHECK-NOSTACKMAP-WINDOWSX64-OPT-LABEL: define zeroext i1 @"kfun:kotlinx.cinterop.StableRef#equals(kotlin.Any?){}kotlin.Boolean"(ptr %0, ptr %1)

// CHECK-STACKMAP-OPT: call ptr addrspace(1) @"kfun:kotlinx.cinterop#<StableRef-unbox>(kotlin.Any?){}kotlinx.cinterop.StableRef<-1:0>?"
// CHECK-NOSTACKMAP-OPT: call ptr @"kfun:kotlinx.cinterop#<StableRef-unbox>(kotlin.Any?){}kotlinx.cinterop.StableRef<-1:0>?"

// CHECK-OPT-LABEL: epilogue:

@kotlinx.cinterop.ExperimentalForeignApi
fun box(): String {
    val ref1 = StableRef.create(Any())
    val ref2 = StableRef.create(Any())
    val isEqual = ref1 == ref2
    ref2.dispose()
    ref1.dispose()
    return if (!isEqual) "OK" else "FAIL"
}
