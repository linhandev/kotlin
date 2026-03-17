// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests concurrent data structure operations under GC pressure.
// Risk: Concurrent reads/writes to shared collections while GC compacts/moves
// objects may expose stale pointer bugs, missing write barriers on collection
// internals, or ABA problems in lock-free data structures.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class Entry(val key: Int, val value: String) {
    fun verify(): Boolean = value == "v-$key"
}

// Lock-free stack (Treiber stack) — tests AtomicReference CAS under GC
class LockFreeNode(val entry: Entry, val next: LockFreeNode?)

val stackErrors = AtomicInt(0)

@Test fun testConcurrentDataStructures() {
    // === Test 1: Concurrent AtomicReference CAS storm ===
    // Multiple workers do CAS on shared AtomicReference while GC runs
    val sharedRef = AtomicReference<Entry?>(Entry(0, "v-0"))
    val casErrors = AtomicInt(0)

    val workers1 = Array(6) { Worker.start() }
    val futures1 = workers1.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedRef, casErrors) }) { (workerIdx, ref, errors) ->
            for (round in 0 until 200) {
                // Read current value
                val current = ref.value
                if (current != null && !current.verify()) {
                    errors.incrementAndGet()
                }

                // CAS to new value
                val newEntry = Entry(workerIdx * 10000 + round, "v-${workerIdx * 10000 + round}")
                ref.compareAndSet(current, newEntry)

                // Trigger GC periodically
                if (round % 40 == 0) GC.collect()
            }
            0
        }
    }

    for (f in futures1) f.result
    for (w in workers1) w.requestTermination().result

    // Final value must be valid
    val finalVal = sharedRef.value
    if (finalVal != null) {
        assertTrue(finalVal.verify(), "T1: Final AtomicRef value corrupted")
    }
    assertEquals(0, casErrors.value, "T1: CAS read saw corrupted entry")

    // === Test 2: Lock-free stack (Treiber stack) push/pop under GC ===
    val stackHead = AtomicReference<LockFreeNode?>(null)
    stackErrors.value = 0

    val workers2 = Array(4) { Worker.start() }
    val futures2 = workers2.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, stackHead, stackErrors) }) { (workerIdx, head, errors) ->
            for (round in 0 until 300) {
                // Push
                val entry = Entry(workerIdx * 100000 + round, "v-${workerIdx * 100000 + round}")
                while (true) {
                    val oldHead = head.value
                    val newNode = LockFreeNode(entry, oldHead)
                    if (head.compareAndSet(oldHead, newNode)) break
                }

                // Pop
                while (true) {
                    val oldHead = head.value ?: break
                    if (head.compareAndSet(oldHead, oldHead.next)) {
                        if (!oldHead.entry.verify()) {
                            errors.incrementAndGet()
                        }
                        break
                    }
                }

                if (round % 50 == 0) GC.collect()
            }
            0
        }
    }

    for (f in futures2) f.result
    for (w in workers2) w.requestTermination().result
    assertEquals(0, stackErrors.value, "T2: Treiber stack saw corrupted entry")

    // Drain remaining nodes and verify
    var drainErrors = 0
    var node = stackHead.value
    while (node != null) {
        if (!node.entry.verify()) drainErrors++
        node = node.next
    }
    assertEquals(0, drainErrors, "T2: Remaining stack nodes corrupted")

    // === Test 3: Producer-consumer pattern with AtomicReference queue ===
    val queueSlot = AtomicReference<Entry?>(null)
    val produced = AtomicInt(0)
    val consumed = AtomicInt(0)
    val queueErrors = AtomicInt(0)

    val producers = Array(3) { Worker.start() }
    val consumers = Array(3) { Worker.start() }

    val producersDone = AtomicInt(0)
    val producerCount = producers.size

    data class QueueArgs(val slot: AtomicReference<Entry?>, val produced: AtomicInt, val consumed: AtomicInt, val errors: AtomicInt, val producersDone: AtomicInt, val producerCount: Int)
    val qArgs = QueueArgs(queueSlot, produced, consumed, queueErrors, producersDone, producerCount)

    val prodFutures = producers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, qArgs) }) { (workerIdx, args) ->
            for (round in 0 until 200) {
                val entry = Entry(workerIdx * 100000 + round, "v-${workerIdx * 100000 + round}")
                // Spin until we can publish
                while (!args.slot.compareAndSet(null, entry)) {
                    // slot is full, consumer hasn't taken it yet
                }
                args.produced.incrementAndGet()
                if (round % 30 == 0) GC.collect()
            }
            args.producersDone.incrementAndGet()
            0
        }
    }

    val consFutures = consumers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, qArgs) }) { (workerIdx, args) ->
            while (true) {
                val entry = args.slot.value
                if (entry != null && args.slot.compareAndSet(entry, null)) {
                    if (!entry.verify()) {
                        args.errors.incrementAndGet()
                    }
                    args.consumed.incrementAndGet()
                }
                // Exit when all producers are done and slot is drained
                if (args.producersDone.value == args.producerCount && args.slot.value == null) break
            }
            0
        }
    }

    for (f in prodFutures) f.result
    // Wait a bit for consumers to drain
    for (i in 0 until 10) GC.collect()
    for (f in consFutures) f.result
    for (w in producers) w.requestTermination().result
    for (w in consumers) w.requestTermination().result

    assertEquals(0, queueErrors.value, "T3: Consumer saw corrupted entry")

    // === Test 4: Shared array concurrent read + GC ===
    val sharedArray = Array(1000) { i -> Entry(i, "v-$i") }
    val readErrors = AtomicInt(0)

    val workers4 = Array(6) { Worker.start() }
    val futures4 = workers4.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedArray, readErrors) }) { (workerIdx, arr, errors) ->
            for (round in 0 until 100) {
                // Read random elements and verify
                for (j in 0 until 100) {
                    val index = (workerIdx * 137 + round * 31 + j * 7) % arr.size
                    val entry = arr[index]
                    if (!entry.verify()) {
                        errors.incrementAndGet()
                    }
                }
                if (round % 20 == 0) GC.collect()
            }
            0
        }
    }

    for (f in futures4) f.result
    for (w in workers4) w.requestTermination().result
    assertEquals(0, readErrors.value, "T4: Concurrent array read saw corrupted entry")

    println("PASS")
}
