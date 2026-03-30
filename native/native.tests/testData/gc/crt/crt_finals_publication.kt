// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests val field publication safety on AArch64 weak memory model under GC.
// On ARM64, without proper barriers, a reader can see partially constructed objects.
// KN val fields are effectively final — tests that GC doesn't break this guarantee.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

// Object with val fields (effectively final) — reader must see all or none
class FinalObj(v: Int) {
    val x1 = v
    val x2 = x1 + v
    val x3 = x2 + v
    val x4 = x3 + v
    fun verify(): Boolean = x2 == x1 * 2 && x3 == x1 * 3 && x4 == x1 * 4
}

// Singleton with internal state — racy double-check pattern
class Singleton private constructor() {
    val data = IntArray(10) { it * 42 }
    val tag = "singleton-initialized"

    companion object {
        val instance = AtomicReference<Singleton?>(null)

        fun getOrCreate(): Singleton {
            var s = instance.value
            if (s == null) {
                s = Singleton()
                instance.compareAndSet(null, s)
                // If CAS fails, another thread created it — use theirs
                s = instance.value!!
            }
            return s
        }
    }

    fun verify(): Boolean = tag == "singleton-initialized" && data[9] == 9 * 42
}

val finalsPubErrors = AtomicInt(0)
val partialConstruction = AtomicInt(0)

@Test fun testFinalsPublication() {
    finalsPubErrors.value = 0
    partialConstruction.value = 0

    // === Test 1: Finals publication — publish via AtomicReference ===
    // Writer publishes FinalObj, reader checks all fields are consistent
    val slot = AtomicReference<FinalObj?>(null)

    val workers = Array(4) { Worker.start() }

    // Writer
    val writerFuture = workers[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { slot }) { s ->
        for (i in 1..2000) {
            s.value = FinalObj(i)
            if (i % 200 == 0) GC.collect()
        }
        0
    }

    // Readers
    val readerFutures = (1 until 4).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(slot, finalsPubErrors, partialConstruction) }) { (s, errors, partial) ->
            for (i in 0 until 5000) {
                val obj = s.value
                if (obj != null) {
                    if (!obj.verify()) {
                        // Saw partially constructed object — FORBIDDEN with val fields
                        partial.incrementAndGet()
                    }
                }
                if (i % 500 == 0) GC.collect()
            }
            0
        }
    }

    writerFuture.result
    for (f in readerFutures) f.result
    for (w in workers) w.requestTermination().result

    // With val (final) fields, partial construction must NEVER be observed
    assertEquals(0, partialConstruction.value,
        "T1: Partial object construction observed ${partialConstruction.value} times — val field guarantee broken")

    // === Test 2: Singleton racy creation — all threads must see same instance ===
    Singleton.instance.value = null
    val singletonErrors = AtomicInt(0)

    val workers2 = Array(8) { Worker.start() }
    val futures2 = workers2.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { singletonErrors }) { errors ->
            for (round in 0 until 500) {
                val s = Singleton.getOrCreate()
                if (!s.verify()) {
                    errors.incrementAndGet()
                }
                if (round % 50 == 0) GC.collect()
            }
            0
        }
    }
    for (f in futures2) f.result
    for (w in workers2) w.requestTermination().result
    assertEquals(0, singletonErrors.value, "T2: Singleton partial construction observed")

    // === Test 3: val field stability after many GC cycles ===
    val objects = Array(1000) { FinalObj(it + 1) }
    repeat(20) { GC.collect() }

    var stableErrors = 0
    for (obj in objects) {
        if (!obj.verify()) stableErrors++
    }
    assertEquals(0, stableErrors, "T3: val fields corrupted after 20 GC cycles")

    // === Test 4: Rapid publication cycle — new object every iteration ===
    val rapidSlot = AtomicReference<FinalObj?>(null)
    val rapidErrors = AtomicInt(0)

    val workers4 = Array(2) { Worker.start() }
    val pubFuture = workers4[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { rapidSlot }) { s ->
        for (i in 1..10000) {
            s.value = FinalObj(i)
        }
        0
    }
    val readFuture = workers4[1].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(rapidSlot, rapidErrors) }) { (s, errors) ->
        for (i in 0 until 20000) {
            val obj = s.value
            if (obj != null && !obj.verify()) {
                errors.incrementAndGet()
            }
        }
        0
    }
    pubFuture.result
    readFuture.result
    for (w in workers4) w.requestTermination().result
    assertEquals(0, rapidErrors.value, "T4: Rapid publication saw partial object")

    println("PASS")
}
