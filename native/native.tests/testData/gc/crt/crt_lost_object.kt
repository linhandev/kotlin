// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests the "lost object problem" in concurrent GC.
// The lost object problem occurs in concurrent GC when:
// 1. A white (unmarked) object is only referenced from a white/grey object
// 2. The white object's reference is moved to a black (already-scanned) object's field
// 3. If the card table is not properly dirtied, the moved reference is never traced
// 4. The white object gets collected despite being reachable → heap corruption
//
// This test shuffles array references to trigger old→new pointer stores that
// must dirty the card table for correct concurrent marking.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class ByteContainer(val id: Int) {
    var bytes: ByteArray? = null
    fun verify(): Boolean {
        val b = bytes
        return b == null || b.size > 0
    }
}

const val LOST_BUCKETS = 16384
const val LOST_BUF_SIZE = 1024

@Test fun testLostObject() {
    // === Test 1: Sequential shuffle + GC ===
    val containers = Array(LOST_BUCKETS) { ByteContainer(it) }

    // Pseudo-random number generator (deterministic)
    var seed = 123456L
    fun nextRand(bound: Int): Int {
        seed = (seed * 1103515245 + 12345) and 0x7FFFFFFFL
        return (seed % bound).toInt()
    }

    for (i in 0 until LOST_BUCKETS / 256) {
        val index = nextRand(LOST_BUCKETS)
        containers[index].bytes = ByteArray(LOST_BUF_SIZE) { (it % 256).toByte() }

        GC.collect()

        // Shuffle: swap references between containers
        // This is the critical operation that triggers the lost object problem
        for (j in containers.indices) {
            val a = containers.size - 1 - (j % containers.size)
            val b = nextRand(if (a > 0) a else 1)
            val temp = containers[a].bytes
            containers[a].bytes = containers[b].bytes
            containers[b].bytes = temp
        }
    }

    // Verify all containers are valid (no dangling pointers)
    var t1Errors = 0
    for (c in containers) {
        if (!c.verify()) t1Errors++
    }
    assertEquals(0, t1Errors, "T1: Lost object detected — container corrupted")

    // === Test 2: Concurrent shuffle + GC (amplified lost object scenario) ===
    val sharedContainers = Array(4096) { ByteContainer(it) }
    val lostErrors = AtomicInt(0)

    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedContainers, lostErrors) }) { (workerIdx, arr, errors) ->
            var localSeed = (workerIdx + 1) * 31337L
            fun localRand(bound: Int): Int {
                localSeed = (localSeed * 1103515245 + 12345) and 0x7FFFFFFFL
                return (localSeed % bound).toInt()
            }

            for (round in 0 until 100) {
                // Assign new buffer to random slot
                val idx = localRand(arr.size)
                arr[idx].bytes = ByteArray(256) { (it + workerIdx).toByte() }

                // Shuffle a section
                val start = localRand(arr.size - 100)
                for (j in 0 until 50) {
                    val a = start + j
                    val b = start + localRand(50)
                    val temp = arr[a].bytes
                    arr[a].bytes = arr[b].bytes
                    arr[b].bytes = temp
                }

                if (round % 10 == 0) GC.collect()
            }

            // Verify our section
            for (c in arr) {
                if (!c.verify()) errors.incrementAndGet()
            }
            0
        }
    }

    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, lostErrors.value, "T2: Concurrent lost object detected")

    // === Test 3: Object graph with cross-references + shuffle ===
    class GraphNode(val id: Int) {
        var child: GraphNode? = null
        var data: String = "node-$id"
        fun verify(): Boolean = data == "node-$id"
    }

    val graph = Array(2000) { GraphNode(it) }
    // Create cross-references
    for (i in graph.indices) {
        graph[i].child = graph[(i * 7 + 13) % graph.size]
    }

    // Shuffle cross-references while GC runs
    for (round in 0 until 50) {
        for (i in graph.indices) {
            val j = (i * 31 + round * 7) % graph.size
            // Swap children — this creates old→new references
            val temp = graph[i].child
            graph[i].child = graph[j].child
            graph[j].child = temp
        }
        GC.collect()
    }

    // Verify all nodes and their children are valid
    var t3Errors = 0
    for (node in graph) {
        if (!node.verify()) t3Errors++
        val child = node.child
        if (child != null && !child.verify()) t3Errors++
    }
    assertEquals(0, t3Errors, "T3: Graph shuffle lost object detected")

    println("PASS")
}
