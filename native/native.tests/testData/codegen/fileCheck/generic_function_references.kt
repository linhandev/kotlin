// TARGET_BACKEND: NATIVE
// FILECHECK_STAGE: CStubs

import kotlin.reflect.KFunction2

fun <StringifyTP> stringify(collection: StringifyTP, size: (StringifyTP) -> Int, get: StringifyTP.(Int) -> Any?): String {
    var res = "["
    for (i in 0 until size(collection)) {
        if (i > 0) res += ", "
        res += collection.get(i).toString()
    }
    res += "]"
    return res
}

interface I

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#stringifyArray(kotlin.Array<0:0>){0\C2\A7<I>}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#stringifyArray(kotlin.Array<0:0>){0\C2\A7<I>}kotlin.String"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr {{%[0-9]+}})
fun <StringifyArrayTP : I> stringifyArray(array: Array<StringifyArrayTP>) =
        // CHECK-STACKMAP: call ptr addrspace(1) @"kfun:#stringify(0:0;kotlin.Function1<0:0,kotlin.Int>;kotlin.Function2<0:0,kotlin.Int,kotlin.Any?>){0\C2\A7<kotlin.Any?>}kotlin.String"
        // CHECK-NOSTACKMAP: call ptr @"kfun:#stringify(0:0;kotlin.Function1<0:0,kotlin.Int>;kotlin.Function2<0:0,kotlin.Int,kotlin.Any?>){0\C2\A7<kotlin.Any?>}kotlin.String"
        stringify(
                array,
                { it.size }, // stringifyArray$1
                Array<*>::get // stringifyArray$$FUNCTION_REFERENCE_FOR$get$0
        )

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#stringifyIntArray(kotlin.Array<kotlin.Int>){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#stringifyIntArray(kotlin.Array<kotlin.Int>){}kotlin.String"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr {{%[0-9]+}})
fun stringifyIntArray(array: Array<Int>) =
        // CHECK-STACKMAP: call ptr addrspace(1) @"kfun:#stringify(0:0;kotlin.Function1<0:0,kotlin.Int>;kotlin.Function2<0:0,kotlin.Int,kotlin.Any?>){0\C2\A7<kotlin.Any?>}kotlin.String"
        // CHECK-NOSTACKMAP: call ptr @"kfun:#stringify(0:0;kotlin.Function1<0:0,kotlin.Int>;kotlin.Function2<0:0,kotlin.Int,kotlin.Any?>){0\C2\A7<kotlin.Any?>}kotlin.String"
        stringify(
                array,
                { it.size }, // stringifyIntArray$1
                Array<Int>::get // stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1
        )

class N(val v: Int) : I {
    override fun toString() = v.toString()
}

@Suppress("UNUSED_PARAMETER")
fun <BazTP0, BazTP1> foo(p1: BazTP0, p2: BazTP1) {}

fun <QuxTP> bar() {
    val ref: KFunction2<QuxTP, QuxTP, Unit> = ::foo // bar$ref$$FUNCTION_REFERENCE_FOR$foo$2
    println(ref)
}

// CHECK-STACKMAP-LABEL: define ptr addrspace(1) @"kfun:#box(){}kotlin.String"
// CHECK-NOSTACKMAP-LABEL: define ptr @"kfun:#box(){}kotlin.String"
fun box(): String {
    println(stringifyArray(arrayOf(N(2), N(14))))
    println(stringifyIntArray(arrayOf(1, 2, 3)))

    bar<Int>()
    bar<String>()

    val ref: KFunction2<Int, Int, Unit> = ::foo // box$ref$$FUNCTION_REFERENCE_FOR$foo$3
    println(ref)
    return "OK"
}

// CHECK-LABEL: define internal void @"kfun:stringifyArray$1.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}})

// CHECK-LABEL: define internal i32 @"kfun:stringifyArray$1.invoke#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr {{%[0-9]+}})


// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:stringifyArray$1.$<bridge-UNN>invoke(kotlin.Array<1:0>){}kotlin.Int#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:stringifyArray$1.$<bridge-UNN>invoke(kotlin.Array<1:0>){}kotlin.Int#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) [[array:%[0-9]+]], ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr [[array:%[0-9]+]], ptr {{%[0-9]+}})
// CHECK-STACKMAP-OPT: call i32 @"kfun:stringifyArray$1.invoke#internal"(ptr addrspace(1) [[this]], ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-OPT: call i32 @"kfun:stringifyArray$1.invoke#internal"(ptr [[this]], ptr {{%[0-9]+}})
// CHECK-STACKMAP-DEBUG: call i32 @"kfun:stringifyArray$1.invoke#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call i32 @"kfun:stringifyArray$1.invoke#internal"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}})

// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.invoke#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.invoke#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) [[array:%[0-9]+]], i32 [[index:%[0-9]+]], ptr [[ret:%[0-9]+]])
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr [[array:%[0-9]+]], i32 [[index:%[0-9]+]], ptr [[ret:%[0-9]+]])
// CHECK-STACKMAP-OPT: call ptr addrspace(1) @Kotlin_Array_get(ptr addrspace(1) [[array]], i32 [[index]], ptr [[ret]])
// CHECK-NOSTACKMAP-OPT: call ptr @Kotlin_Array_get(ptr [[array]], i32 [[index]], ptr [[ret]])
// CHECK-STACKMAP-DEBUG: call ptr addrspace(1) @Kotlin_Array_get(ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call ptr @Kotlin_Array_get(ptr {{%[0-9]+}}, i32 {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.$<bridge-NNNU>invoke(kotlin.Array<*>;kotlin.Int){}kotlin.Any?#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.$<bridge-NNNU>invoke(kotlin.Array<*>;kotlin.Int){}kotlin.Any?#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) [[array:%[0-9]+]], ptr addrspace(1) [[boxedIndex:%[0-9]+]], ptr [[ret:%[0-9]+]])
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr [[array:%[0-9]+]], ptr [[boxedIndex:%[0-9]+]], ptr [[ret:%[0-9]+]])
// CHECK-STACKMAP-OPT: call ptr addrspace(1) @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.invoke#internal"(ptr addrspace(1) [[this]], ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}}, ptr [[ret]])
// CHECK-NOSTACKMAP-OPT: call ptr @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.invoke#internal"(ptr [[this]], ptr {{%[0-9]+}}, i32 {{%[0-9]+}}, ptr [[ret]])
// CHECK-STACKMAP-DEBUG: call ptr addrspace(1) @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.invoke#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call ptr @"kfun:stringifyArray$$FUNCTION_REFERENCE_FOR$get$0.invoke#internal"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}}, i32 {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:stringifyIntArray$1.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}})

// CHECK-LABEL: define internal i32 @"kfun:stringifyIntArray$1.invoke#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:stringifyIntArray$1.$<bridge-UNN>invoke(kotlin.Array<kotlin.Int>){}kotlin.Int#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:stringifyIntArray$1.$<bridge-UNN>invoke(kotlin.Array<kotlin.Int>){}kotlin.Int#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) [[array:%[0-9]+]], ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr [[array:%[0-9]+]], ptr {{%[0-9]+}})
// CHECK-STACKMAP-OPT: call i32 @"kfun:stringifyIntArray$1.invoke#internal"(ptr addrspace(1) [[this]], ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-OPT: call i32 @"kfun:stringifyIntArray$1.invoke#internal"(ptr [[this]], ptr {{%[0-9]+}})
// CHECK-STACKMAP-DEBUG: call i32 @"kfun:stringifyIntArray$1.invoke#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call i32 @"kfun:stringifyIntArray$1.invoke#internal"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}})

// CHECK-LABEL: define internal i32 @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.invoke#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr {{%[0-9]+}}, i32 {{%[0-9]+}})


// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.$<bridge-UNNU>invoke(kotlin.Array<kotlin.Int>;kotlin.Int){}kotlin.Int#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.$<bridge-UNNU>invoke(kotlin.Array<kotlin.Int>;kotlin.Int){}kotlin.Int#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) [[array:%[0-9]+]], ptr addrspace(1) {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr [[array:%[0-9]+]], ptr {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-STACKMAP-OPT: call i32 @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.invoke#internal"(ptr addrspace(1) [[this]], ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-OPT: call i32 @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.invoke#internal"(ptr [[this]], ptr {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-STACKMAP-DEBUG: call i32 @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.invoke#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call i32 @"kfun:stringifyIntArray$$FUNCTION_REFERENCE_FOR$get$1.invoke#internal"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}}, i32 {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.invoke#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) [[p1:%[0-9]+]], ptr addrspace(1) [[p2:%[0-9]+]])
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, ptr [[p1:%[0-9]+]], ptr [[p2:%[0-9]+]])
// CHECK-STACKMAP-OPT: call void @"kfun:#foo(0:0;0:1){0\C2\A7<kotlin.Any?>;1\C2\A7<kotlin.Any?>}"(ptr addrspace(1) [[p1]], ptr addrspace(1) [[p2]])
// CHECK-NOSTACKMAP-OPT: call void @"kfun:#foo(0:0;0:1){0\C2\A7<kotlin.Any?>;1\C2\A7<kotlin.Any?>}"(ptr [[p1]], ptr [[p2]])
// CHECK-STACKMAP-DEBUG: call void @"kfun:#foo(0:0;0:1){0\C2\A7<kotlin.Any?>;1\C2\A7<kotlin.Any?>}"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call void @"kfun:#foo(0:0;0:1){0\C2\A7<kotlin.Any?>;1\C2\A7<kotlin.Any?>}"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}})


// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.$<bridge-DNNN>invoke(1:0;1:0){}#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.$<bridge-DNNN>invoke(1:0;1:0){}#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) [[p1:%[0-9]+]], ptr addrspace(1) [[p2:%[0-9]+]], ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr [[p1:%[0-9]+]], ptr [[p2:%[0-9]+]], ptr {{%[0-9]+}})
// CHECK-STACKMAP-OPT: call void @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.invoke#internal"(ptr addrspace(1) [[this]], ptr addrspace(1) [[p1]], ptr addrspace(1) [[p2]])
// CHECK-NOSTACKMAP-OPT: call void @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.invoke#internal"(ptr [[this]], ptr [[p1]], ptr [[p2]])
// CHECK-STACKMAP-DEBUG: call void @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.invoke#internal"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call void @"kfun:bar$ref$$FUNCTION_REFERENCE_FOR$foo$2.invoke#internal"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.<init>#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}})

// CHECK-LABEL: define internal void @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.invoke#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr {{%[0-9]+}}, i32 {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-STACKMAP: call void @"kfun:#foo(0:0;0:1){0\C2\A7<kotlin.Any?>;1\C2\A7<kotlin.Any?>}"(ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}})
// CHECK-NOSTACKMAP: call void @"kfun:#foo(0:0;0:1){0\C2\A7<kotlin.Any?>;1\C2\A7<kotlin.Any?>}"(ptr {{%[0-9]+}}, ptr {{%[0-9]+}})

// CHECK-STACKMAP-LABEL: define internal ptr addrspace(1) @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.$<bridge-DNUU>invoke(kotlin.Int;kotlin.Int){}#internal"
// CHECK-NOSTACKMAP-LABEL: define internal ptr @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.$<bridge-DNUU>invoke(kotlin.Int;kotlin.Int){}#internal"
// CHECK-STACKMAP-SAME: (ptr addrspace(1) [[this:%[0-9]+]], ptr addrspace(1) {{%[0-9]+}}, ptr addrspace(1) {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-NOSTACKMAP-SAME: (ptr [[this:%[0-9]+]], ptr {{%[0-9]+}}, ptr {{%[0-9]+}}, ptr {{%[0-9]+}})
// CHECK-STACKMAP-OPT: call void @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.invoke#internal"(ptr addrspace(1) [[this]], i32 {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-OPT: call void @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.invoke#internal"(ptr [[this]], i32 {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-STACKMAP-DEBUG: call void @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.invoke#internal"(ptr addrspace(1) {{%[0-9]+}}, i32 {{%[0-9]+}}, i32 {{%[0-9]+}})
// CHECK-NOSTACKMAP-DEBUG: call void @"kfun:box$ref$$FUNCTION_REFERENCE_FOR$foo$3.invoke#internal"(ptr {{%[0-9]+}}, i32 {{%[0-9]+}}, i32 {{%[0-9]+}})
