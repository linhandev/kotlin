// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests circular reference handling under CRT GC.
// Risk: Circular references are the classic GC challenge; CRT with copying
// collector must correctly handle forwarding pointers in cycles;
// doubly-linked lists create complex pointer update requirements.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class CycleNode(val id: Int) {
    var next: CycleNode? = null
    var prev: CycleNode? = null
    var crossRef: CycleNode? = null
    val data = "cycle-$id"
    fun verify(): Boolean = data == "cycle-$id"
}

fun buildCircularList(size: Int, idBase: Int): CycleNode {
    val head = CycleNode(idBase)
    var cur = head
    for (i in 1 until size) {
        val node = CycleNode(idBase + i)
        cur.next = node
        node.prev = cur
        cur = node
    }
    // Close the cycle
    cur.next = head
    head.prev = cur
    return head
}

fun verifyCycle(head: CycleNode, expectedSize: Int): Boolean {
    var cur = head
    var count = 0
    do {
        if (!cur.verify()) return false
        // Verify back-pointer
        if (cur.next?.prev !== cur) return false
        if (cur.prev?.next !== cur) return false
        cur = cur.next!!
        count++
        if (count > expectedSize + 1) return false // infinite loop guard
    } while (cur !== head)
    return count == expectedSize
}

@Test fun testCircularReferenceStress() {
    // === Test 1: Simple cycle survives GC ===
    val cycle1 = buildCircularList(100, 0)
    repeat(10) { GC.collect() }
    assertTrue(verifyCycle(cycle1, 100), "T1: Simple cycle corrupted after GC")

    // === Test 2: Many independent cycles ===
    val cycles = Array(50) { i -> buildCircularList(20, i * 1000) }
    repeat(10) { GC.collect() }
    for (i in cycles.indices) {
        assertTrue(verifyCycle(cycles[i], 20), "T2: Cycle $i corrupted after GC")
    }

    // === Test 3: Interconnected cycles (cycle of cycles) ===
    val ring = Array(10) { i -> buildCircularList(10, i * 100) }
    // Connect the rings: last node of ring[i] also points to first node of ring[i+1]
    for (i in ring.indices) {
        var cur = ring[i]
        // Walk to a middle node
        repeat(5) { cur = cur.next!! }
        // Add cross-link (doesn't break the main cycle, just adds extra reference)
        val crossTarget = ring[(i + 1) % ring.size]
        // Create cross-ring reference to stress inter-cycle pointer updates
        cur.crossRef = crossTarget
    }
    repeat(10) { GC.collect() }
    for (i in ring.indices) {
        assertTrue(verifyCycle(ring[i], 10), "T3: Interconnected ring $i corrupted")
    }

    // === Test 4: Create and discard cycles rapidly ===
    for (round in 0 until 100) {
        val temp = buildCircularList(50, round * 10000)
        if (round % 10 == 0) GC.collect()
        // temp goes out of scope — cycle must be collectible
    }
    // Allocate after heavy cycle creation — heap must be functional
    val postCycle = buildCircularList(100, 999000)
    GC.collect()
    assertTrue(verifyCycle(postCycle, 100), "T4: Post-discard cycle corrupted")

    // === Test 5: Concurrent cycle traversal + GC ===
    val sharedCycle = AtomicReference(buildCircularList(50, 500000))
    val cycleErrors = AtomicInt(0)

    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedCycle, cycleErrors) }) { (workerIdx, cycleRef, errors) ->
            for (round in 0 until 50) {
                val head = cycleRef.value
                if (head != null) {
                    // Walk the cycle and verify
                    var cur = head
                    var steps = 0
                    do {
                        if (!cur.verify()) {
                            errors.incrementAndGet()
                            break
                        }
                        cur = cur.next ?: break
                        steps++
                    } while (cur !== head && steps < 100)
                }

                if (round % 10 == 0) GC.collect()
            }
            0
        }
    }

    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, cycleErrors.value, "T5: Concurrent cycle verification errors")

    // === Test 6: Large cycle (10000 nodes) ===
    val largeCycle = buildCircularList(10000, 800000)
    repeat(5) { GC.collect() }
    assertTrue(verifyCycle(largeCycle, 10000), "T6: Large cycle (10000 nodes) corrupted")

    println("PASS")
}
