// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests mass WeakReference clearing under concurrent GC pressure (200K weak refs).
// Risk: Processing thousands of weak references during GC mark phase
// may be O(n²) or have ordering bugs; concurrent clearing + reading
// may expose race conditions in weak reference table.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class WeakTarget(val id: Int, val data: String) {
    fun verify(): Boolean = data == "wt-$id"
}

@Test fun testWeakRefMassClearing() {
    // === Test 1: Create and clear 10000 weak references ===
    val weakRefs = Array(10000) { i ->
        val target = WeakTarget(i, "wt-$i")
        WeakReference(target)
        // target goes out of scope each iteration
    }

    // Force collection
    repeat(5) { GC.collect() }

    // Count how many were cleared vs still alive
    var cleared = 0
    var alive = 0
    var dangling = 0
    for (wr in weakRefs) {
        val ref = wr.get()
        when {
            ref == null -> cleared++
            ref.verify() -> alive++
            else -> dangling++
        }
    }
    assertEquals(0, dangling, "T1: Dangling weak references detected: $dangling")

    // === Test 2: Batch create → GC → verify cycle ===
    for (batch in 0 until 10) {
        val batchRefs = Array(1000) { i ->
            val id = batch * 1000 + i
            val target = WeakTarget(id, "wt-$id")
            WeakReference(target)
        }

        GC.collect()

        var batchDangling = 0
        for (wr in batchRefs) {
            val ref = wr.get()
            if (ref != null && !ref.verify()) batchDangling++
        }
        assertEquals(0, batchDangling, "T2: Batch $batch has dangling refs")
    }

    // === Test 3: Mix of live and dead weak references ===
    val liveTargets = Array(500) { WeakTarget(it + 20000, "wt-${it + 20000}") }
    val mixedRefs = Array(1000) { i ->
        if (i < 500) {
            // These targets stay alive via liveTargets array
            WeakReference(liveTargets[i])
        } else {
            // These targets go out of scope immediately
            val temp = WeakTarget(i + 20000, "wt-${i + 20000}")
            WeakReference(temp)
        }
    }

    repeat(5) { GC.collect() }

    var liveCount = 0
    var deadCount = 0
    var mixDangling = 0
    for (i in mixedRefs.indices) {
        val ref = mixedRefs[i].get()
        if (ref == null) {
            deadCount++
        } else if (ref.verify()) {
            liveCount++
        } else {
            mixDangling++
        }
    }
    assertEquals(0, mixDangling, "T3: Mixed refs have dangling pointers")
    // Explicitly use liveTargets to prevent dead code elimination
    var liveVerifyErrors = 0
    for (lt in liveTargets) {
        if (!lt.verify()) liveVerifyErrors++
    }
    assertEquals(0, liveVerifyErrors, "T3: liveTargets array itself is corrupted")
    // First 500 should still be alive (held by liveTargets).
    // Some temporary targets may also survive due to regional GC not collecting all regions each cycle.
    assertTrue(liveCount >= 500, "T3: Live targets were incorrectly cleared: expected >= 500, got $liveCount")

    // === Test 4: Concurrent WeakRef creation + GC clearing ===
    val concurrentErrors = AtomicInt(0)
    val workers = Array(6) { Worker.start() }

    // Workers 0-3: create weak refs and check them
    val futures = (0 until 4).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, concurrentErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 100) {
                val refs = Array(100) { i ->
                    val target = WeakTarget(workerIdx * 100000 + round * 100 + i,
                        "wt-${workerIdx * 100000 + round * 100 + i}")
                    WeakReference(target)
                }

                // Check: must return null or valid object
                for (wr in refs) {
                    val ref = wr.get()
                    if (ref != null && !ref.verify()) {
                        errors.incrementAndGet()
                    }
                }
            }
            0
        }
    }

    // Workers 4-5: trigger GC continuously
    val gcFutures = (4 until 6).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Unit }) {
            for (round in 0 until 200) {
                GC.collect()
            }
            0
        }
    }

    for (f in futures) f.result
    for (f in gcFutures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, concurrentErrors.value, "T4: Concurrent WeakRef errors")

    // === Test 5: WeakRef to same object from multiple refs ===
    val shared = WeakTarget(99999, "wt-99999")
    val multiRefs = Array(100) { WeakReference(shared) }
    GC.collect()

    // All must return the same object (or all null)
    val first = multiRefs[0].get()
    for (i in 1 until multiRefs.size) {
        val ref = multiRefs[i].get()
        if (first == null) {
            assertNull(ref, "T5: Inconsistent clearing — ref 0 is null but ref $i is not")
        } else {
            assertNotNull(ref, "T5: Inconsistent clearing — ref 0 is alive but ref $i is null")
        }
    }
    // Keep shared alive for this check
    assertTrue(shared.verify(), "T5: Shared target corrupted")

    println("PASS")
}
