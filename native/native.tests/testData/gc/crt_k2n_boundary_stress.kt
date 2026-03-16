// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Tests K2N boundary behavior under high-frequency GC pressure.
// Simulates tight K→N→K "sandwich" patterns where thread state transitions
// (Runnable↔Native) happen rapidly while concurrent GC attempts STW pauses.
// Risk: STW may not correctly pause threads at K2N/N2K boundaries,
// or thread state transitions may race with GC phase changes.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class, kotlinx.cinterop.ExperimentalForeignApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker
import kotlinx.cinterop.StableRef

val lockErrors = AtomicInt(0)

class BoundaryObj(val id: Int, val payload: String) {
    fun verify(): Boolean = payload == "boundary-$id"
}

@Test fun testK2NBoundaryStress() {
    val numWorkers = 6
    val rounds = 100

    val workers = Array(numWorkers) { Worker.start() }

    // Workers 0-3: simulate tight K2N boundary crossings using StableRef
    // (StableRef.create/get/dispose crosses managed/native boundary)
    // Workers 4-5: trigger GC continuously
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, rounds) }) { (workerIdx, rounds) ->
            var errors = 0

            if (workerIdx < 4) {
                // Boundary worker: rapid StableRef create/access/dispose cycle
                for (round in 0 until rounds) {
                    val objects = Array(20) { i ->
                        BoundaryObj(workerIdx * 100000 + round * 100 + i,
                            "boundary-${workerIdx * 100000 + round * 100 + i}")
                    }

                    // Create StableRefs (K→N transition: object becomes externally rooted)
                    val refs = Array(20) { StableRef.create(objects[it]) }

                    // Access through StableRef (N→K transition: deref native pointer)
                    for (ref in refs) {
                        val obj = ref.get()
                        if (!obj.verify()) errors++
                    }

                    // Interleave: create new objects while old StableRefs exist
                    val moreObjs = Array(20) { "temp-$workerIdx-$round-$it" }

                    // Dispose (N→K transition: remove external root)
                    for (ref in refs) ref.dispose()

                    // Verify temp objects survived the boundary crossings
                    for (i in moreObjs.indices) {
                        if (moreObjs[i] != "temp-$workerIdx-$round-$i") {
                            errors++
                        }
                    }
                }
            } else {
                // GC worker: trigger collection continuously
                for (round in 0 until rounds * 2) {
                    GC.collect()
                    // Also allocate to compete for heap space
                    val garbage = Array(50) { "gc-$workerIdx-$round-$it" }
                }
            }
            errors
        }
    }

    var totalErrors = 0
    for (f in futures) totalErrors += f.result
    for (w in workers) w.requestTermination().result

    assertEquals(0, totalErrors, "K2N boundary stress errors: $totalErrors")

    // === Test 2: Nested boundary crossings ===
    var nestedErrors = 0
    for (round in 0 until 50) {
        val outer = BoundaryObj(round, "boundary-$round")
        val outerRef = StableRef.create(outer)

        // Create inner StableRef while outer exists
        val inner = BoundaryObj(round + 10000, "boundary-${round + 10000}")
        val innerRef = StableRef.create(inner)

        GC.collect()

        // Access both through native refs
        if (!outerRef.get().verify()) nestedErrors++
        if (!innerRef.get().verify()) nestedErrors++

        innerRef.dispose()
        GC.collect()

        // Outer should still be valid after inner is disposed
        if (!outerRef.get().verify()) nestedErrors++
        outerRef.dispose()
    }
    assertEquals(0, nestedErrors, "Nested boundary crossing errors: $nestedErrors")

    println("PASS")
}
