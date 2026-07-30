// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

fun <T> T.foo() { println(this) }

// CHECK-LABEL: define void @"kfun:#bar(0:0){0\C2\A7<kotlin.Any?>}"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[x:%[0-9]+]])
// CHECK-NOSTACKMAP-SAME: (ptr [[x:%[0-9]+]])
fun <BarTP> bar(x: BarTP) {
    // CHECK-STACKMAP-OPT: call void @"kfun:bar$$FUNCTION_REFERENCE_FOR$foo$0.<init>#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr [[x]])
    // CHECK-NOSTACKMAP-OPT: call void @"kfun:bar$$FUNCTION_REFERENCE_FOR$foo$0.<init>#internal"(ptr {{%[0-9]+}}, ptr [[x]])
    // CHECK-STACKMAP-DEBUG: call void @"kfun:bar$$FUNCTION_REFERENCE_FOR$foo$0.<init>#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr {{%[0-9]+}})
    // CHECK-NOSTACKMAP-DEBUG: call void @"kfun:bar$$FUNCTION_REFERENCE_FOR$foo$0.<init>#internal"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}})
    println(x::foo)
}

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
fun box(): String {
    // CHECK-STACKMAP: call void @"kfun:box$$FUNCTION_REFERENCE_FOR$foo$1.<init>#internal"(ptr addrspace(1) {{%[0-9]+}}, i32 5)
    // CHECK-NOSTACKMAP: call void @"kfun:box$$FUNCTION_REFERENCE_FOR$foo$1.<init>#internal"(ptr {{%[0-9]+}}, i32 5)
    println(5::foo)

    bar("hello")
    bar(42)
    return "OK"
// CHECK-LABEL: epilogue:
}

// CHECK-LABEL: define internal void @"kfun:bar$$FUNCTION_REFERENCE_FOR$foo$0.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:box$$FUNCTION_REFERENCE_FOR$foo$1.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, i32 {{%[0-9]+}})
