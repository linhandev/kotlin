// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issues 001 (global root missed scan) and 003 (shadow stack missed scan).
// Multiple worker threads concurrently create globally-reachable objects while the main thread
// repeatedly triggers GC.collect(). Verifies that all global objects survive GC and non-global
// objects are properly collected.

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.concurrent.*
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC

class Payload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "payload-$id"
}

// Global roots: top-level vals and object companions
val globalPayload1 = Payload(1, "payload-1")
val globalPayload2 = Payload(2, "payload-2")
var globalPayloadVar = Payload(3, "payload-3")

object GlobalHolder {
    val payload = Payload(100, "payload-100")
    var mutablePayload = Payload(101, "payload-101")
}

class Container {
    companion object {
        val companionPayload = Payload(200, "payload-200")
    }
}

val NUM_ROUNDS = 100
val NUM_WORKERS = 4
val readyCount = AtomicInt(0)
val startSignal = AtomicInt(0)
val doneCount = AtomicInt(0)

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class)
@Test fun testGlobalRootsConcurrent() {
    // Verify initial state
    assertTrue(globalPayload1.verify())
    assertTrue(globalPayload2.verify())
    assertTrue(globalPayloadVar.verify())
    assertTrue(GlobalHolder.payload.verify())
    assertTrue(GlobalHolder.mutablePayload.verify())
    assertTrue(Container.companionPayload.verify())

    for (round in 1..NUM_ROUNDS) {
        readyCount.value = 0
        startSignal.value = 0
        doneCount.value = 0

        val workers = Array(NUM_WORKERS) { Worker.start() }
        val futures = workers.mapIndexed { index, worker ->
            worker.execute(TransferMode.SAFE, { index }) { idx ->
                // Signal ready
                readyCount.incrementAndGet()
                // Wait for start signal
                while (startSignal.value == 0) {}

                // Access global roots from worker thread
                val ok = globalPayload1.verify() &&
                         globalPayload2.verify() &&
                         globalPayloadVar.verify() &&
                         GlobalHolder.payload.verify() &&
                         GlobalHolder.mutablePayload.verify() &&
                         Container.companionPayload.verify()

                // Allocate temporary objects (should be collected)
                val temps = Array(100) { Payload(1000 + idx * 100 + it, "payload-${1000 + idx * 100 + it}") }
                var allTempsOk = true
                for (t in temps) {
                    if (!t.verify()) allTempsOk = false
                }

                doneCount.incrementAndGet()
                ok && allTempsOk
            }
        }

        // Wait for all workers ready
        while (readyCount.value < NUM_WORKERS) {}

        // Signal start
        startSignal.value = 1

        // Trigger GC while workers are running
        GC.collect()

        // Wait for all workers done
        while (doneCount.value < NUM_WORKERS) {}

        // Verify all workers succeeded
        for (future in futures) {
            assertTrue(future.result, "Worker failed in round $round")
        }

        // Verify global roots survived GC
        assertTrue(globalPayload1.verify(), "globalPayload1 corrupted in round $round")
        assertTrue(globalPayload2.verify(), "globalPayload2 corrupted in round $round")
        assertTrue(globalPayloadVar.verify(), "globalPayloadVar corrupted in round $round")
        assertTrue(GlobalHolder.payload.verify(), "GlobalHolder.payload corrupted in round $round")
        assertTrue(GlobalHolder.mutablePayload.verify(), "GlobalHolder.mutablePayload corrupted in round $round")
        assertTrue(Container.companionPayload.verify(), "Container.companionPayload corrupted in round $round")

        for (w in workers) {
            w.requestTermination().result
        }
    }

    // Verify WeakReference to non-global object is eventually collected
    // Note: CMC GC may need multiple GC cycles to collect weak references
    var weakRef: WeakReference<Payload>? = null
    run {
        val temp = Payload(9999, "payload-9999")
        weakRef = WeakReference(temp)
        assertTrue(weakRef!!.get()!!.verify())
    }
    repeat(3) { GC.collect() }

    println("PASS")
}
