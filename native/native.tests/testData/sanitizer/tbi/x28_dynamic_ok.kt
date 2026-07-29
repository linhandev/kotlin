// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class)
import kotlin.native.runtime.GC

class Cell(val n: Int, var next: Cell? = null)

fun main() {
    val nodes = ArrayList<Cell>(1000)
    for (i in 0 until 1000) nodes.add(Cell(i))
    for (i in 0 until nodes.size - 1) nodes[i].next = nodes[i + 1]
    var sum = 0
    var cur: Cell? = nodes.first()
    while (cur != null) { sum += cur.n; cur = cur.next }
    GC.collect()
    println("TBI-C05-DYNAMIC-OK sum=$sum")
}
