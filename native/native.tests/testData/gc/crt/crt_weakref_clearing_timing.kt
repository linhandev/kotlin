// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests WeakReference clearing timing consistency under concurrent GC.
// Key invariant: once WeakReference.get() returns null, it must never return
// non-null again (monotonicity). Also, get() must never return a dangling pointer.
// Extends crt_weakref_gc.kt with stronger timing/monotonicity checks.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class TimingObj(val id: Int, val data: String) {
    fun verify(): Boolean = data == "timing-$id"
}

val timingErrors = AtomicInt(0)
val monotonicityViolations = AtomicInt(0)

@Test fun testWeakRefClearingTiming() {
    // === Test 1: Monotonicity — once null, always null ===
    timingErrors.value = 0
    monotonicityViolations.value = 0

    val weakRefs = mutableListOf<WeakReference<TimingObj>>()
    for (i in 0 until 100) {
        run {
            val obj = TimingObj(i, "timing-$i")
            weakRefs.add(WeakReference(obj))
        }
        // obj goes out of scope each iteration
    }

    // Check monotonicity over multiple GC cycles
    val wasNull = BooleanArray(100)
    for (gcRound in 0 until 20) {
        GC.collect()
        for (i in weakRefs.indices) {
            val ref = weakRefs[i].get()
            if (wasNull[i]) {
                // Once null, must stay null
                if (ref != null) {
                    monotonicityViolations.incrementAndGet()
                }
            } else if (ref == null) {
                wasNull[i] = true
            } else {
                // Still alive — must be valid, not dangling
                if (!ref.verify()) {
                    timingErrors.incrementAndGet()
                }
            }
        }
    }

    assertEquals(0, monotonicityViolations.value, "T1: WeakRef monotonicity violations")
    assertEquals(0, timingErrors.value, "T1: WeakRef dangling pointer detected")

    // === Test 2: Concurrent get() + GC — multi-threaded monotonicity ===
    timingErrors.value = 0
    monotonicityViolations.value = 0

    // Create weak refs to objects that will become unreachable
    val sharedWeaks = Array(50) { i ->
        var wr: WeakReference<TimingObj>? = null
        run {
            val obj = TimingObj(1000 + i, "timing-${1000 + i}")
            wr = WeakReference(obj)
        }
        wr!!
    }

    val numWorkers = 6
    val workers = Array(numWorkers) { Worker.start() }

    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, sharedWeaks) }) { (workerIdx, weaks) ->
            var errors = 0
            var monoViolations = 0
            val localWasNull = BooleanArray(weaks.size)

            for (round in 0 until 100) {
                if (workerIdx == 0 && round % 5 == 0) {
                    GC.collect()
                }

                for (i in weaks.indices) {
                    val ref = weaks[i].get()
                    if (localWasNull[i]) {
                        if (ref != null) monoViolations++
                    } else if (ref == null) {
                        localWasNull[i] = true
                    } else {
                        if (!ref.verify()) errors++
                    }
                }
            }

            errors * 1000 + monoViolations  // pack both counts
        }
    }

    var totalErrors = 0
    var totalMonoViolations = 0
    for (f in futures) {
        val packed = f.result
        totalErrors += packed / 1000
        totalMonoViolations += packed % 1000
    }
    for (w in workers) w.requestTermination().result

    assertEquals(0, totalErrors, "T2: Concurrent dangling pointer: $totalErrors")
    assertEquals(0, totalMonoViolations, "T2: Concurrent monotonicity violations: $totalMonoViolations")

    // === Test 3: WeakRef to long-lived object must not be cleared prematurely ===
    val longLived = TimingObj(9999, "timing-9999")
    val longLivedWeak = WeakReference(longLived)

    repeat(10) { GC.collect() }

    val ref = longLivedWeak.get()
    assertNotNull(ref, "T3: WeakRef to live object was cleared prematurely")
    assertTrue(ref.verify(), "T3: Live object corrupted")
    // Keep longLived alive past this assertion
    assertEquals(9999, longLived.id)

    println("PASS")
}
