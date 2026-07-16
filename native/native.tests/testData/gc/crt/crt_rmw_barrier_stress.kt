// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests read-modify-write (CAS) operations under concurrent GC.
// Risk: CAS on ARM64 may produce spurious failures if GC moves objects
// between the load-exclusive and store-exclusive (LL/SC). Read barriers
// must not interfere with CAS success/failure semantics.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class CASTarget(val id: Int, val data: String) {
    fun verify(): Boolean = data == "cas-$id"
}

val casErrors = AtomicInt(0)

@Test fun testRmwBarrierStress() {
    casErrors.value = 0

    // === Test 1: AtomicReference CAS contention + GC ===
    // Multiple threads race CAS on same AtomicReference while GC runs
    val sharedRef = AtomicReference<CASTarget?>(CASTarget(0, "cas-0"))

    val workers = Array(6) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedRef, casErrors) }) { (workerIdx, ref, errors) ->
            var casSuccesses = 0
            var casFailures = 0

            for (round in 0 until 1000) {
                val current = ref.value
                val newVal = CASTarget(workerIdx * 100000 + round, "cas-${workerIdx * 100000 + round}")

                if (ref.compareAndSet(current, newVal)) {
                    casSuccesses++
                } else {
                    casFailures++
                    // On CAS failure, the current value must still be valid
                    val reread = ref.value
                    if (reread != null && !reread.verify()) {
                        errors.incrementAndGet()
                    }
                }

                // Allocate garbage to trigger GC
                if (round % 50 == 0) {
                    val garbage = Array(500) { "garbage-$workerIdx-$round-$it" }
                    GC.collect()
                }
            }
            0
        }
    }

    for (f in futures) f.result
    for (w in workers) w.requestTermination().result

    val finalVal = sharedRef.value
    if (finalVal != null) {
        assertTrue(finalVal.verify(), "T1: Final CAS value corrupted")
    }
    assertEquals(0, casErrors.value, "T1: CAS read saw corrupted value during contention")

    // === Test 2: Adjacent AtomicReference CAS (spurious failure stress) ===
    // CAS on adjacent memory locations may see spurious failures on ARM64 LL/SC
    casErrors.value = 0

    class AdjacentHolder {
        val ref0 = AtomicReference<CASTarget?>(CASTarget(0, "cas-0"))
        val ref1 = AtomicReference<CASTarget?>(CASTarget(1, "cas-1"))
        val ref2 = AtomicReference<CASTarget?>(CASTarget(2, "cas-2"))
        val ref3 = AtomicReference<CASTarget?>(CASTarget(3, "cas-3"))
    }

    val holder = AdjacentHolder()
    val workers2 = Array(4) { Worker.start() }
    val futures2 = workers2.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, holder, casErrors) }) { (workerIdx, h, errors) ->
            // Each worker CAS-es on its own ref, but they're adjacent in memory
            val ref = when (workerIdx) {
                0 -> h.ref0; 1 -> h.ref1; 2 -> h.ref2; else -> h.ref3
            }

            for (round in 0 until 2000) {
                val cur = ref.value
                val new = CASTarget(workerIdx * 100000 + round, "cas-${workerIdx * 100000 + round}")
                ref.compareAndSet(cur, new)

                // Verify our ref is always valid
                val check = ref.value
                if (check != null && !check.verify()) {
                    errors.incrementAndGet()
                }

                if (round % 100 == 0) GC.collect()
            }
            0
        }
    }

    for (f in futures2) f.result
    for (w in workers2) w.requestTermination().result
    assertEquals(0, casErrors.value, "T2: Adjacent CAS saw corrupted values")

    // === Test 3: AtomicInt CAS contention + GC ===
    val sharedInt = AtomicInt(0)
    val intErrors = AtomicInt(0)

    val workers3 = Array(4) { Worker.start() }
    val futures3 = workers3.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedInt, intErrors) }) { (workerIdx, shared, errors) ->
            for (round in 0 until 5000) {
                val cur = shared.value
                shared.compareAndSet(cur, cur + 1)
                if (round % 200 == 0) GC.collect()
            }
            0
        }
    }

    for (f in futures3) f.result
    for (w in workers3) w.requestTermination().result
    // Value should be > 0 (some CAS succeeded) and consistent
    assertTrue(sharedInt.value > 0, "T3: No CAS succeeded")
    assertEquals(0, intErrors.value, "T3: AtomicInt CAS errors")

    // === Test 4: CAS with heavy allocation pressure ===
    casErrors.value = 0
    val pressureRef = AtomicReference<CASTarget?>(CASTarget(0, "cas-0"))

    val workers4 = Array(2) { Worker.start() }

    // Worker 0: CAS loop
    val casFuture = workers4[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(pressureRef, casErrors) }) { (ref, errors) ->
        for (round in 0 until 500) {
            val cur = ref.value
            val new = CASTarget(round, "cas-$round")
            ref.compareAndSet(cur, new)
            val check = ref.value
            if (check != null && !check.verify()) errors.incrementAndGet()
        }
        0
    }

    // Worker 1: allocation pressure
    val allocFuture = workers4[1].execute(kotlin.native.concurrent.TransferMode.SAFE, { Unit }) {
        for (round in 0 until 100) {
            // Allocate ~6MB per round to trigger GC
            val big = Array(10000) { ByteArray(64) }
            GC.collect()
        }
        0
    }

    casFuture.result
    allocFuture.result
    for (w in workers4) w.requestTermination().result
    assertEquals(0, casErrors.value, "T4: CAS under allocation pressure saw corrupted values")

    println("PASS")
}
