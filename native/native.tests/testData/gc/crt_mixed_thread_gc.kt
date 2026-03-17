// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests GC safety during rapid thread creation/destruction.
// The bug: Between CreateAndRegisterNewThreadHolder()+BindMutator() and
// SetThreadHolder(), there's a window where the ThreadHolder is visible
// to GC but GetThread() returns NULL. If GC triggers STW in this window,
// VisitMutatorRoots dereferences NULL ThreadData -> SIGSEGV.
// Regression test for Issue #46.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker
import kotlin.concurrent.AtomicInt

val raceErrors = AtomicInt(0)

@Test fun testMixedThreadGC() {
    raceErrors.value = 0

    // Strategy: rapidly create and destroy Workers while another Worker
    // continuously triggers GC. This maximizes the chance of GC hitting
    // the window between BindMutator() and SetThreadHolder().

    // GC trigger worker — runs continuously
    val gcWorker = Worker.start()
    val gcRunning = AtomicInt(1)
    val gcFuture = gcWorker.execute(kotlin.native.concurrent.TransferMode.SAFE, { gcRunning }) { running ->
        while (running.value == 1) {
            GC.collect()
        }
        0
    }

    // Rapid worker creation/destruction — 500 cycles
    for (round in 0 until 500) {
        val w = Worker.start()
        val f = w.execute(kotlin.native.concurrent.TransferMode.SAFE, { round }) { r ->
            // Brief work
            val arr = Array(10) { "thread-$r-$it" }
            arr.size
        }
        f.result
        w.requestTermination().result
    }

    // Also try batch creation — many workers alive simultaneously
    for (batch in 0 until 10) {
        val workers = Array(8) { Worker.start() }
        val futures = workers.mapIndexed { idx, w ->
            w.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(batch, raceErrors) }) { (b, errors) ->
                val obj = Array(100) { "batch-$b-$it" }
                GC.collect()
                if (obj[0] != "batch-$b-0") errors.incrementAndGet()
                0
            }
        }
        for (f in futures) f.result
        for (w in workers) w.requestTermination().result
    }

    // Stop GC worker
    gcRunning.value = 0
    gcFuture.result
    gcWorker.requestTermination().result

    assertEquals(0, raceErrors.value, "Data corrupted during rapid thread creation + GC")
    println("PASS")
}
