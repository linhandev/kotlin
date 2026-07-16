// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests stack scanning precision, concurrent WeakRef stress, and array copy under GC.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class PreciseObj(val id: Int) {
    val data = "precise-$id"
    fun verify(): Boolean = data == "precise-$id"
}

// Populate stack with objects, return weak refs
fun populateStack(): Array<WeakReference<PreciseObj>> {
    val obj0 = PreciseObj(0); val obj1 = PreciseObj(1)
    val obj2 = PreciseObj(2); val obj3 = PreciseObj(3)
    val obj4 = PreciseObj(4); val obj5 = PreciseObj(5)
    val obj6 = PreciseObj(6); val obj7 = PreciseObj(7)
    val obj8 = PreciseObj(8); val obj9 = PreciseObj(9)
    return arrayOf(
        WeakReference(obj0), WeakReference(obj1), WeakReference(obj2),
        WeakReference(obj3), WeakReference(obj4), WeakReference(obj5),
        WeakReference(obj6), WeakReference(obj7), WeakReference(obj8),
        WeakReference(obj9),
    )
}

val preciseErrors = AtomicInt(0)

@Test fun testPreciseGC() {
    // === Test 1: Stack precision — objects out of scope should be collectible ===
    val weakRefs = populateStack()
    repeat(5) { GC.collect() }

    var danglingCount = 0
    for (wr in weakRefs) {
        val ref = wr.get()
        if (ref != null && !ref.verify()) danglingCount++
    }
    assertEquals(0, danglingCount, "T1: Dangling pointers from stack objects")

    // === Test 2: Concurrent weak ref stress (each worker independent) ===
    preciseErrors.value = 0
    val workers = Array(5) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, preciseErrors) }) { (workerIdx, errors) ->
            for (iter in 0 until 200) {
                val refs = Array(1000) { i ->
                    WeakReference(PreciseObj(workerIdx * 1000000 + iter * 1000 + i))
                }
                if (iter % 20 == 0) GC.collect()
                for (wr in refs) {
                    val ref = wr.get()
                    if (ref != null && !ref.verify()) errors.incrementAndGet()
                }
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, preciseErrors.value, "T2: Concurrent WeakRef stress — dangling pointer")

    // === Test 3: Array copy under concurrent GC ===
    preciseErrors.value = 0
    val workers3 = Array(4) { Worker.start() }
    val copyFutures = workers3.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, preciseErrors) }) { (workerIdx, errors) ->
            val src = Array(2000) { PreciseObj(workerIdx * 100000 + it) }
            val dst = arrayOfNulls<PreciseObj>(2000)
            for (round in 0 until 50) {
                src.copyInto(dst)
                if (round % 5 == 0) GC.collect()
                for (i in 0 until 2000) {
                    val obj = dst[i]
                    if (obj == null || !obj.verify()) { errors.incrementAndGet(); break }
                }
                // Shift src
                for (i in 0 until 1999) src[i] = src[i + 1]
                src[1999] = PreciseObj(workerIdx * 100000 + round * 2000 + 1999)
            }
            0
        }
    }
    for (f in copyFutures) f.result
    for (w in workers3) w.requestTermination().result
    assertEquals(0, preciseErrors.value, "T3: Array copy under concurrent GC errors")

    println("PASS")
}
