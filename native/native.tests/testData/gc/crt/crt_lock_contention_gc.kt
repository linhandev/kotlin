// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests GC safety under heavy atomic reference contention and critical section scenarios.
// Risk: GC STW may interfere with threads in tight compute loops; threads in critical
// sections must be properly handled during STW.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class LockPayload(val id: Int, val data: IntArray) {
    fun verify(): Boolean = data.size > 0 && data[0] == id
}

val contentionErrors = AtomicInt(0)

@Test fun testLockContentionGC() {
    contentionErrors.value = 0

    // === Test 1: Heavy atomic reference contention + GC ===
    // Multiple workers compete for same AtomicReference
    val shared = AtomicReference<LockPayload?>(LockPayload(0, intArrayOf(0)))

    val workers = Array(6) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, shared, contentionErrors) }) { (workerIdx, ref, errors) ->
            for (round in 0 until 500) {
                // Read-modify-write cycle (simulates synchronized block)
                val current = ref.value
                val newPayload = LockPayload(
                    workerIdx * 10000 + round,
                    IntArray(100) { workerIdx * 10000 + round }
                )
                ref.compareAndSet(current, newPayload)

                // Verify what we read is valid
                val check = ref.value
                if (check != null && !check.verify()) {
                    errors.incrementAndGet()
                }

                // Allocate garbage to trigger GC during contention
                if (round % 20 == 0) {
                    val garbage = Array(100) { IntArray(50) }
                    GC.collect()
                }
            }
            0
        }
    }

    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, contentionErrors.value, "T1: Atomic reference contention + GC errors")

    // === Test 2: Long critical section + GC ===
    // One worker holds "critical section" (tight loop), others allocate + GC
    contentionErrors.value = 0

    val criticalDone = AtomicInt(0)
    val workers2 = Array(4) { Worker.start() }

    // Worker 0: critical section — tight compute loop (no allocation)
    val critFuture = workers2[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { criticalDone }) { done ->
        // Simulate critical section — no GC allowed
        var sum = 0L
        for (i in 0 until 10_000_000) {
            sum += i
        }
        done.value = 1
        sum.toInt() // prevent DCE
    }

    // Workers 1-3: allocate and trigger GC
    val allocFutures = (1 until 4).map { idx ->
        workers2[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, criticalDone, contentionErrors) }) { (workerIdx, done, errors) ->
            while (done.value == 0) {
                val obj = LockPayload(workerIdx, intArrayOf(workerIdx))
                if (!obj.verify()) errors.incrementAndGet()
                GC.collect()
            }
            0
        }
    }

    critFuture.result
    for (f in allocFutures) f.result
    for (w in workers2) w.requestTermination().result
    assertEquals(0, contentionErrors.value, "T2: Critical section + concurrent GC errors")

    println("PASS")
}
