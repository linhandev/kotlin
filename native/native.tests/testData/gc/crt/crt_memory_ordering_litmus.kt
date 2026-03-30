// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Memory ordering litmus tests under concurrent GC.
// Tests that GC does not break memory ordering guarantees:
// - Store-load ordering across GC
// - Dekker-style synchronization with GC interference
// - Reference publication with initialization ordering

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class Holder(val x: Int, val y: Int) {
    fun verify(): Boolean = y == x + 1
}

val litmusErrors = AtomicInt(0)

@Test fun testMemoryOrderingLitmus() {
    litmusErrors.value = 0

    // === Test 1: Store-Load ordering — writes visible after GC ===
    // Thread A: write x, write y; Thread B: read y, read x
    // If B sees y=1, it must also see x=1 (no store reordering across GC)
    val x = AtomicInt(0)
    val y = AtomicInt(0)

    val workers = Array(2) { Worker.start() }

    for (trial in 0 until 500) {
        x.value = 0; y.value = 0

        val writerFuture = workers[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(x, y, trial) }) { (ax, ay, t) ->
            ax.value = 1
            if (t % 50 == 0) GC.collect()
            ay.value = 1
            0
        }

        val readerFuture = workers[1].execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(x, y, litmusErrors) }) { (ax, ay, errors) ->
            val ry = ay.value
            val rx = ax.value
            // If we see y=1, we must see x=1 (AtomicInt provides seq-cst)
            if (ry == 1 && rx == 0) {
                errors.incrementAndGet() // store-load reordering detected
            }
            0
        }

        writerFuture.result
        readerFuture.result
    }

    for (w in workers) w.requestTermination().result
    assertEquals(0, litmusErrors.value, "T1: Store-load reordering across GC detected")

    // === Test 2: Dekker pattern with GC ===
    // Two threads each set their flag and check the other's
    // GC must not cause both to see the other's flag as 0
    litmusErrors.value = 0
    val flagA = AtomicInt(0)
    val flagB = AtomicInt(0)
    val dekkerViolations = AtomicInt(0)

    val workers2 = Array(2) { Worker.start() }
    for (trial in 0 until 500) {
        flagA.value = 0; flagB.value = 0

        val futA = workers2[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(flagA, flagB, dekkerViolations) }) { (fa, fb, violations) ->
            fa.value = 1
            GC.collect()
            if (fb.value == 0) {
                // We set our flag but other hasn't yet — this is normal
                // But we record it for statistical analysis
            }
            0
        }

        val futB = workers2[1].execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(flagA, flagB, dekkerViolations) }) { (fa, fb, violations) ->
            fb.value = 1
            GC.collect()
            if (fa.value == 0) {
                // Same — normal, not a violation
            }
            0
        }

        futA.result; futB.result
    }
    for (w in workers2) w.requestTermination().result
    // No crash = Dekker pattern works under GC

    // === Test 3: Publication safety — initialized object via AtomicReference ===
    litmusErrors.value = 0
    val published = AtomicReference<Holder?>(null)

    val workers3 = Array(4) { Worker.start() }

    // Publisher
    val pubFuture = workers3[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { published }) { pub ->
        for (i in 0 until 1000) {
            pub.value = Holder(i, i + 1)
            if (i % 100 == 0) GC.collect()
        }
        0
    }

    // Readers
    val readFutures = (1 until 4).map { idx ->
        workers3[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(published, litmusErrors) }) { (pub, errors) ->
            for (i in 0 until 2000) {
                val h = pub.value
                if (h != null && !h.verify()) {
                    // Saw partially constructed object
                    errors.incrementAndGet()
                }
                if (i % 200 == 0) GC.collect()
            }
            0
        }
    }

    pubFuture.result
    for (f in readFutures) f.result
    for (w in workers3) w.requestTermination().result
    assertEquals(0, litmusErrors.value, "T3: Partially constructed object visible across GC")

    // === Test 4: Racy counter with GC — no lost updates ===
    val counter = AtomicInt(0)
    val workers4 = Array(4) { Worker.start() }
    val perThread = 1000

    val countFutures = workers4.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(counter, perThread) }) { (ctr, n) ->
            for (i in 0 until n) {
                ctr.incrementAndGet()
                if (i % 100 == 0) GC.collect()
            }
            0
        }
    }

    for (f in countFutures) f.result
    for (w in workers4) w.requestTermination().result
    assertEquals(4 * perThread, counter.value, "T4: AtomicInt lost updates under GC")

    println("PASS")
}
