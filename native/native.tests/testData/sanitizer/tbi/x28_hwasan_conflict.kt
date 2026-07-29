// KIND: STANDALONE_NO_TR
// EXIT_CODE: 0
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi
//
// Scenario: multi-Worker allocate/link/traverse + GC.collect stress under suite defaults.
//   Product-correct expectation is exit 0 (no intentional OOB/UAF). C05 (x28 bit62 ×
//   HWASAN FP) is a runtime bug if it still fires under crt+cmc+HWADDRESS.

@file:OptIn(
    kotlin.native.runtime.NativeRuntimeApi::class,
    kotlin.experimental.ExperimentalNativeApi::class,
)

import kotlin.native.concurrent.Worker
import kotlin.native.runtime.GC

class Node(val value: Int, var next: Node? = null)

private fun stressPass(pass: Int, n: Int): Long {
    val nodes = ArrayList<Node>(n)
    for (i in 0 until n) {
        nodes.add(Node(i + pass * 17))
    }
    for (i in 0 until nodes.size - 1) {
        nodes[i].next = nodes[i + 1]
    }
    for (i in 0 until nodes.size - 2 step 3) {
        nodes[i].next = nodes[i + 2]
    }
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

private fun workerStress(args: Pair<Int, Pair<Int, Int>>): Long {
    val (id, pn) = args
    val (localPasses, n) = pn
    var acc = 0L
    for (p in 0 until localPasses) {
        acc += stressPass(id * 1000 + p, n)
    }
    return acc
}

fun main() {
    val passes = 64
    val nodesPerPass = 10000
    val workers = 4
    val perWorker = passes / 2

    val futures = Array(workers) { wi ->
        Worker.start().execute(
            kotlin.native.concurrent.TransferMode.SAFE,
            { Pair(wi, Pair(perWorker, nodesPerPass)) },
            ::workerStress,
        )
    }

    var mainSum = 0L
    for (p in 0 until passes) {
        mainSum += stressPass(p, nodesPerPass)
        if (p % 4 == 3) GC.collect()
    }

    var workerSum = 0L
    for (f in futures) {
        workerSum += f.result
    }

    println(
        "C05_STRESS_OK mainSum=$mainSum workerSum=$workerSum " +
            "passes=$passes n=$nodesPerPass workers=$workers perWorker=$perWorker"
    )
}
