// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class)
import kotlin.native.runtime.GC

class Node(var next: Node?, val v: Int)

fun main() {
    var head: Node? = Node(null, 0)
    for (i in 1 until 2000) {
        head = Node(head, i)
        if (i % 100 == 0) GC.collect()
    }
    var n = head
    var c = 0
    while (n != null) { c++; n = n.next }
    println("TBI-C02-DYNAMIC OK count=$c")
}
