// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs
// FREE_COMPILER_ARGS: -Xbinary=genericSafeCasts=true

value class Foo(val value: Int)
// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:Foo#$<bridge-NU>toString(){}kotlin.String(){}kotlin.String
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:Foo#$<bridge-NU>toString(){}kotlin.String(){}kotlin.String
// CHECK-DEBUG-NOT: {{call|call zeroext}} i1 @IsSubtype
// CHECK-OPT-NOT: {{call|call zeroext}} i1 @IsSubclassFast
// CHECK-STACKMAP-LABEL: call ptr addrspace(1) @"kfun:Foo#toString(){}kotlin.String
// CHECK-NOSTACKMAP-LABEL: call ptr @"kfun:Foo#toString(){}kotlin.String
// CHECK-LABEL: epilogue:

// CHECK-LABEL: define i32 @"kfun:#foo(kotlin.Any){}kotlin.Int
fun foo(x: Any) = x as Int
// CHECK-DEBUG: {{call|call zeroext}} i1 @IsSubtype
// CHECK-OPT: {{call|call zeroext}} i1 @IsSubclassFast
// CHECK-DEBUG-NOT: {{call|call zeroext}} i1 @IsSubtype
// CHECK-OPT-NOT: {{call|call zeroext}} i1 @IsSubclassFast
// CHECK-DEBUG: call i32 @"kfun:kotlin#<Int-unbox>(kotlin.Any){}kotlin.Int
// CHECK-STACKMAP-OPT: getelementptr inbounds %"kclassbody:kotlin.Int#internal", ptr addrspace(1) {{%[0-9]+}}, i32 0, i32 1
// CHECK-NOSTACKMAP-OPT: getelementptr inbounds %"kclassbody:kotlin.Int#internal", ptr {{%[0-9]+}}, i32 0, i32 1
// CHECK-LABEL: epilogue:

open class A(val x: Int)

open class B : A(42)

fun bar() = B()

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#baz(){}A
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#baz(){}A
// CHECK-DEBUG-NOT: {{call|call zeroext}} i1 @IsSubtype
// CHECK-OPT-NOT: {{call|call zeroext}} i1 @IsSubclassFast
// CHECK-LABEL: epilogue:
fun baz(): A = bar()

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
fun box(): String {
    println(Foo(42))
    println(foo(42))
    println(baz())
    return "OK"
}
