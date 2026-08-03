// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

object A {
    const val x = 5
}

class B(val z:Int) {
    companion object {
        const val y = 7
    }
}

object C {
    val x = listOf(1, 2, 3)
}

// CHECK-LABEL: define i32 @"kfun:#f(){}kotlin.Int"()
// CHECK-NOT: EnterFrame
fun f() = A.x + B.y
// CHECK: {{^}}epilogue:

// Positive control: accessing a non-constant object requires GC root slots.
// Precise StackMap does not use EnterFrame here, so check the root slots directly.
// CHECK-LABEL: define void @"kfun:#g(){}"()
// CHECK-STACKMAP: alloca ptr addrspace(1)
// CHECK-NOSTACKMAP: EnterFrame
fun g() {
    val x = C.x
}
// CHECK: {{^}}epilogue:


// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
fun box(): String {
    val f = f()
    if (f != 12)
        return "FAIL: $f != 12"
    g()
    return "OK"
}
