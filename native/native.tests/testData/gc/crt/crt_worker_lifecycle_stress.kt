// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests rapid Worker thread creation and destruction under GC.
// Risk: Thread registration/deregistration races with GC STW pauses;
// TLS cleanup may miss objects; thread-local allocator state may leak.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

val lifecycleErrors = AtomicInt(0)

class TLSPayload(val threadId: Int, val round: Int) {
    val data = "tls-$threadId-$round"
    fun verify(): Boolean = data == "tls-$threadId-$round"
}

@Test fun testWorkerLifecycleStress() {
    lifecycleErrors.value = 0

    // === Test 1: Rapid create-execute-terminate cycle ===
    for (round in 0 until 50) {
        val worker = Worker.start()
        val future = worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { round }) { r ->
            // Each short-lived worker allocates objects
            val objects = Array(100) { TLSPayload(r, it) }
            var errors = 0
            for (obj in objects) {
                if (!obj.verify()) errors++
            }
            errors
        }
        val errors = future.result
        worker.requestTermination().result
        if (errors > 0) lifecycleErrors.incrementAndGet()

        if (round % 10 == 0) GC.collect()
    }
    assertEquals(0, lifecycleErrors.value, "T1: Rapid worker lifecycle errors")

    // === Test 2: Many workers created simultaneously, then all terminated ===
    val batchSize = 20
    for (batch in 0 until 5) {
        val workers = Array(batchSize) { Worker.start() }
        val futures = workers.mapIndexed { idx, worker ->
            worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(batch, idx) }) { (b, i) ->
                val payload = TLSPayload(b * 100 + i, 0)
                GC.collect()
                if (!payload.verify()) 1 else 0
            }
        }

        var batchErrors = 0
        for (f in futures) batchErrors += f.result
        for (w in workers) w.requestTermination().result

        GC.collect()
        assertEquals(0, batchErrors, "T2: Batch $batch worker errors")
    }

    // === Test 3: Workers that outlive objects they created ===
    val longWorkers = Array(4) { Worker.start() }
    val longFutures = longWorkers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { idx }) { workerIdx ->
            var errors = 0
            // Create many generations of objects
            for (gen in 0 until 100) {
                val objects = Array(50) { TLSPayload(workerIdx, gen * 50 + it) }
                // Let previous generation become garbage
                if (gen % 10 == 0) GC.collect()
                // Verify current generation
                for (obj in objects) {
                    if (!obj.verify()) errors++
                }
            }
            errors
        }
    }

    var t3Errors = 0
    for (f in longFutures) t3Errors += f.result
    for (w in longWorkers) w.requestTermination().result
    assertEquals(0, t3Errors, "T3: Long-lived worker object errors")

    // === Test 4: Interleaved worker start/stop with GC ===
    val activeWorkers = mutableListOf<Worker>()
    for (round in 0 until 30) {
        // Start a new worker
        activeWorkers.add(Worker.start())

        // If we have enough, terminate oldest
        if (activeWorkers.size > 5) {
            val oldest = activeWorkers.removeAt(0)
            oldest.requestTermination().result
        }

        GC.collect()
    }
    // Cleanup remaining
    for (w in activeWorkers) w.requestTermination().result

    // === Test 5: Worker executing during GC from another thread ===
    val gcWorker = Worker.start()
    val computeWorkers = Array(4) { Worker.start() }

    // GC worker: trigger GC continuously
    val gcFuture = gcWorker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Unit }) {
        for (i in 0 until 50) {
            GC.collect()
        }
        0
    }

    // Compute workers: allocate during GC
    val computeErrors = AtomicInt(0)
    val computeFutures = computeWorkers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, computeErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 200) {
                val obj = TLSPayload(workerIdx, round)
                if (!obj.verify()) errors.incrementAndGet()
            }
            0
        }
    }

    gcFuture.result
    for (f in computeFutures) f.result
    gcWorker.requestTermination().result
    for (w in computeWorkers) w.requestTermination().result
    assertEquals(0, computeErrors.value, "T5: Allocation during concurrent GC errors")

    println("PASS")
}
