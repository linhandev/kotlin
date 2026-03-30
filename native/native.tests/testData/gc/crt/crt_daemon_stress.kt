// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests thread lifecycle stress: rapid creation of many short-lived workers,
// verifying GC handles thread registration/deregistration correctly during
// shutdown. Also includes allocation tracking patterns.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class DaemonPayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "daemon-$id"
}

val daemonErrors = AtomicInt(0)

@Test fun testDaemonStress() {
    daemonErrors.value = 0

    // === Test 1: Many short-lived worker threads ===
    for (batch in 0 until 10) {
        val workers = Array(10) { Worker.start() }
        val futures = workers.mapIndexed { idx, worker ->
            worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(batch * 10 + idx, daemonErrors) }) { (id, errors) ->
                // Brief work — allocate, verify, exit
                val payload = DaemonPayload(id, "daemon-$id")
                if (!payload.verify()) errors.incrementAndGet()
                // Some allocation to stress GC during thread lifecycle
                val arr = Array(100) { "thread-$id-$it" }
                for (s in arr) {
                    if (!s.startsWith("thread-")) errors.incrementAndGet()
                }
                0
            }
        }
        for (f in futures) f.result
        for (w in workers) w.requestTermination().result
        GC.collect()
    }
    assertEquals(0, daemonErrors.value, "T1: Daemon stress errors")

    // === Test 2: Overlapping worker lifecycles — new workers start while old terminate ===
    val activeWorkers = mutableListOf<Pair<Worker, kotlin.native.concurrent.Future<Int>>>()

    for (round in 0 until 50) {
        // Start new worker
        val worker = Worker.start()
        val future = worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(round, daemonErrors) }) { (id, errors) ->
            val obj = DaemonPayload(id, "daemon-$id")
            GC.collect()
            if (!obj.verify()) errors.incrementAndGet()
            0
        }
        activeWorkers.add(Pair(worker, future))

        // Terminate oldest if too many active
        if (activeWorkers.size > 5) {
            val (oldWorker, oldFuture) = activeWorkers.removeAt(0)
            oldFuture.result
            oldWorker.requestTermination().result
        }
    }

    // Drain remaining
    for ((worker, future) in activeWorkers) {
        future.result
        worker.requestTermination().result
    }
    assertEquals(0, daemonErrors.value, "T2: Overlapping worker lifecycle errors")

    // === Test 3: Allocation tracking with WeakRef under threads ===
    val trackingErrors = AtomicInt(0)
    val workers3 = Array(4) { Worker.start() }
    val futures3 = workers3.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, trackingErrors) }) { (workerIdx, errors) ->
            val weakRefs = mutableListOf<WeakReference<DaemonPayload>>()

            for (round in 0 until 100) {
                // Create and track
                val obj = DaemonPayload(workerIdx * 10000 + round, "daemon-${workerIdx * 10000 + round}")
                weakRefs.add(WeakReference(obj))

                // Periodically verify tracked refs
                if (round % 20 == 0) {
                    GC.collect()
                    for (wr in weakRefs) {
                        val ref = wr.get()
                        if (ref != null && !ref.verify()) {
                            errors.incrementAndGet()
                        }
                    }
                }
            }
            0
        }
    }
    for (f in futures3) f.result
    for (w in workers3) w.requestTermination().result
    assertEquals(0, trackingErrors.value, "T3: Allocation tracking errors")

    // === Test 4: Thread burst — many threads start simultaneously ===
    val burstSize = 20
    val burstWorkers = Array(burstSize) { Worker.start() }
    val burstFutures = burstWorkers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, daemonErrors) }) { (id, errors) ->
            // All threads allocate concurrently
            val objects = Array(200) { DaemonPayload(id * 200 + it, "daemon-${id * 200 + it}") }
            GC.collect()
            for (obj in objects) {
                if (!obj.verify()) errors.incrementAndGet()
            }
            0
        }
    }
    for (f in burstFutures) f.result
    for (w in burstWorkers) w.requestTermination().result
    assertEquals(0, daemonErrors.value, "T4: Thread burst errors")

    println("PASS")
}
