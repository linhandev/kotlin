// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests CRT write barrier correctness during concurrent object graph mutation.
// Risk: write barrier may miss old-value tracking, causing subgraphs to be
// reclaimed while still reachable.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

// Linked list node — next pointer is the mutation target
class LNode(val tag: Int) {
    var next: AtomicReference<LNode?> = AtomicReference(null)
}

// Build a linked list of given length
fun buildList(length: Int, tagBase: Int): LNode {
    val head = LNode(tagBase)
    var cur = head
    for (i in 1 until length) {
        val node = LNode(tagBase + i)
        cur.next.value = node
        cur = node
    }
    return head
}

// Count reachable nodes from head
fun countNodes(head: LNode): Int {
    var count = 0
    var cur: LNode? = head
    while (cur != null) {
        count++
        cur = cur.next.value
    }
    return count
}

// Verify all reachable nodes have valid tags (non-zero, field accessible)
fun verifyList(head: LNode): Boolean {
    var cur: LNode? = head
    while (cur != null) {
        if (cur.tag < 0) return false  // corrupted tag
        cur = cur.next.value
    }
    return true
}

val mutationErrors = AtomicInt(0)

// Constants at top level so Worker lambdas can reference without capturing
const val NUM_WORKERS = 6
const val MUTATION_ROUNDS = 80
const val LIST_LENGTH = 100

@Test fun testConcurrentMutation() {
    // Shared lists — each worker operates on its own list but all share heap
    // Plus a shared list that multiple workers mutate concurrently
    val sharedHead = AtomicReference<LNode?>(buildList(LIST_LENGTH, 0))

    val workers = Array(NUM_WORKERS) { Worker.start() }

    // Workers 0-3: mutate shared list (splice in new nodes, break and reconnect links)
    // Workers 4-5: allocate heavily to trigger GC cycles
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, sharedHead) }) { (workerIdx, sharedRef) ->
            var errors = 0

            if (workerIdx < 4) {
                // Mutator: walk the shared list, splice in new nodes, break old links
                for (round in 0 until MUTATION_ROUNDS) {
                    val head = sharedRef.value ?: continue
                    var cur: LNode? = head
                    var steps = 0

                    while (cur != null && steps < 50) {
                        val oldNext = cur.next.value

                        // Splice: insert a new node between cur and oldNext
                        // This is the critical mutation: overwriting cur.next (old value = oldNext)
                        // Write barrier must track oldNext before overwrite
                        val newNode = LNode(10000 + workerIdx * 10000 + round * 100 + steps)
                        newNode.next.value = oldNext
                        cur.next.value = newNode  // <-- write barrier fires here

                        // Sometimes also disconnect and reconnect
                        if (steps % 7 == 0 && oldNext != null) {
                            // Break the new node's link and point directly to oldNext's successor
                            val skip = oldNext.next.value
                            newNode.next.value = skip  // <-- another barrier-critical write
                        }

                        cur = newNode.next.value
                        steps++
                    }

                    // Periodically rebuild the shared list to keep it from growing unbounded
                    if (round % 20 == 0) {
                        sharedRef.value = buildList(LIST_LENGTH, round * 1000)
                    }
                }

                // Verify the current list is traversable without crash
                val finalHead = sharedRef.value
                if (finalHead != null && !verifyList(finalHead)) {
                    errors++
                }
            } else {
                // Allocator: create garbage to trigger GC cycles
                for (round in 0 until MUTATION_ROUNDS) {
                    // Allocate trees to create cross-generation references
                    val nodes = Array(200) { i -> LNode(90000 + round * 200 + i) }
                    // Link them into a chain
                    for (i in 0 until nodes.size - 1) {
                        nodes[i].next.value = nodes[i + 1]
                    }
                    // Force GC during mutation
                    if (round % 5 == 0) {
                        GC.collect()
                    }
                    // Verify our local chain survived
                    val reachable = countNodes(nodes[0])
                    if (reachable != 200) {
                        errors++
                    }
                }
            }
            errors
        }
    }

    var totalErrors = 0
    for (f in futures) totalErrors += f.result
    for (w in workers) w.requestTermination().result

    // Final verification: the shared list should still be traversable
    val finalHead = sharedHead.value
    if (finalHead != null) {
        assertTrue(verifyList(finalHead), "Final shared list verification failed")
    }

    assertEquals(0, totalErrors, "Concurrent mutation errors detected: $totalErrors")
    println("PASS")
}
