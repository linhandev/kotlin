// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Tests StableRef (pinning) correctness under concurrent GC compaction.
// Risk: Pinned objects must not be moved during compaction; concurrent
// create/dispose of StableRef may race with GC marking.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class, kotlinx.cinterop.ExperimentalForeignApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker
import kotlinx.cinterop.StableRef

class PinnedObj(val id: Int, val data: String) {
    fun verify(): Boolean = data == "pinned-$id"
}

val pinErrors = AtomicInt(0)

@Test fun testPinningStress() {
    val numWorkers = 6
    val rounds = 50
    val pinsPerRound = 50

    val workers = Array(numWorkers) { Worker.start() }

    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, rounds, pinsPerRound) }) { (workerIdx, rounds, pinsPerRound) ->
            var errors = 0

            for (round in 0 until rounds) {
                // Create objects and pin them via StableRef
                val objs = Array(pinsPerRound) { i ->
                    PinnedObj(workerIdx * 100000 + round * 1000 + i, "pinned-${workerIdx * 100000 + round * 1000 + i}")
                }
                val refs = Array(pinsPerRound) { i ->
                    StableRef.create(objs[i])
                }

                // Trigger GC while objects are pinned
                if (round % 3 == 0) GC.collect()

                // Allocate garbage to cause heap pressure
                val garbage = Array(100) { "garbage-$workerIdx-$round-$it" }

                // Verify pinned objects through StableRef
                for (i in 0 until pinsPerRound) {
                    val retrieved = refs[i].get()
                    if (!retrieved.verify()) {
                        errors++
                    }
                    if (retrieved.id != objs[i].id) {
                        errors++
                    }
                }

                // Dispose half the refs, GC, then verify remaining
                for (i in 0 until pinsPerRound / 2) {
                    refs[i].dispose()
                }

                if (round % 5 == 0) GC.collect()

                // Remaining refs must still be valid
                for (i in pinsPerRound / 2 until pinsPerRound) {
                    val retrieved = refs[i].get()
                    if (!retrieved.verify()) {
                        errors++
                    }
                }

                // Dispose remaining
                for (i in pinsPerRound / 2 until pinsPerRound) {
                    refs[i].dispose()
                }
            }
            errors
        }
    }

    var totalErrors = 0
    for (f in futures) totalErrors += f.result
    for (w in workers) w.requestTermination().result

    assertEquals(0, totalErrors, "Pinning stress errors: $totalErrors")

    // === Test 2: Rapid pin/unpin cycle with GC ===
    var rapidErrors = 0
    for (round in 0 until 200) {
        val obj = PinnedObj(round, "pinned-$round")
        val ref = StableRef.create(obj)
        if (round % 10 == 0) GC.collect()
        val got = ref.get()
        if (!got.verify()) rapidErrors++
        ref.dispose()
    }
    assertEquals(0, rapidErrors, "Rapid pin/unpin errors: $rapidErrors")

    println("PASS")
}
