// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi
//
// Scenario: allocate/link/traverse + GC.collect stress, single-threaded.
//   gc/alloc/sanitizer follow suite defaults (no per-test override).

@file:OptIn(
    kotlin.native.runtime.NativeRuntimeApi::class,
    kotlin.experimental.ExperimentalNativeApi::class,
)

import kotlin.native.runtime.GC

class Node(val value: Int, var next: Node? = null)

private fun stressPass(pass: Int, n: Int): Long {
    val nodes = ArrayList<Node>(n)
    for (i in 0 until n) nodes.add(Node(i + pass * 17))
    for (i in 0 until nodes.size - 1) nodes[i].next = nodes[i + 1]
    for (i in 0 until nodes.size - 2 step 3) nodes[i].next = nodes[i + 2]
    var sum = 0L
    var cur: Node? = nodes.firstOrNull()
    var steps = 0
    while (cur != null && steps < n * 2) {
        sum += cur.value
        cur = cur.next
        steps++
    }
    nodes.clear()
    GC.collect()
    return sum
}

fun main() {
    var sum = 0L
    for (p in 0 until 64) {
        sum += stressPass(p, 10000)
        if (p % 4 == 3) GC.collect()
    }
    println("C05_STD_SINGLE_OK sum=$sum")
}
