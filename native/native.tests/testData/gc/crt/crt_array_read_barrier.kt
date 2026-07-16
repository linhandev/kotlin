// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlin.ExperimentalStdlibApi -Xbinary=gc=cmc -Xallocator=crt

// Tests array read correctness during concurrent GC-triggered object relocation.
// Creates a large array with many elements, then concurrently reads array elements from multiple
// workers while main thread triggers GC (causing potential object moves). Verifies data consistency.
// Test includes both regular array access and AtomicArray access.

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicArray
import kotlin.native.concurrent.*
import kotlin.native.runtime.GC

class Element(val index: Int, val value: Long) {
    fun verify(): Boolean = value == index.toLong() * 31 + 7
}

const val ARRAY_SIZE = 10000
const val ARB_NUM_WORKERS = 4
const val ARB_NUM_ROUNDS = 100

val arbReadyCount = AtomicInt(0)
val arbStartSignal = AtomicInt(0)
val arbDoneCount = AtomicInt(0)

// Test 1: Regular array access with concurrent GC
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun regularArrayTest(round: Int): Boolean {
    val array = Array(ARRAY_SIZE) { i -> Element(i, i.toLong() * 31 + 7) }

    arbReadyCount.value = 0
    arbStartSignal.value = 0
    arbDoneCount.value = 0

    val workers = Array(ARB_NUM_WORKERS) { Worker.start() }
    val futures = workers.mapIndexed { index, worker ->
        worker.execute(TransferMode.SAFE, { Pair(array, index) }) { (arr, idx) ->
            arbReadyCount.incrementAndGet()
            while (arbStartSignal.value == 0) {}

            val start = (ARRAY_SIZE / ARB_NUM_WORKERS) * idx
            val end = if (idx == ARB_NUM_WORKERS - 1) ARRAY_SIZE else start + (ARRAY_SIZE / ARB_NUM_WORKERS)

            var allOk = true
            for (i in start until end) {
                val elem = arr[i]
                if (!elem.verify()) allOk = false
                if (elem.index != i) allOk = false
            }

            arbDoneCount.incrementAndGet()
            allOk
        }
    }

    while (arbReadyCount.value < ARB_NUM_WORKERS) {}
    arbStartSignal.value = 1
    GC.collect()

    var allOk = true
    for (future in futures) {
        if (!future.result) allOk = false
    }
    for (w in workers) {
        w.requestTermination().result
    }

    for (i in 0 until ARRAY_SIZE) {
        if (!array[i].verify()) allOk = false
    }

    return allOk
}

// Separate sync vars for atomic array test to avoid interference
val aarbReadyCount = AtomicInt(0)
val aarbStartSignal = AtomicInt(0)

// Test 2: AtomicArray access with concurrent GC (exercises ReadVolatileHeapRef)
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class)
fun atomicArrayTest(round: Int): Boolean {
    val atomicArr = AtomicArray(1000) { i -> Element(i, i.toLong() * 31 + 7) }

    aarbReadyCount.value = 0
    aarbStartSignal.value = 0

    val workers = Array(ARB_NUM_WORKERS) { Worker.start() }
    val futures = workers.mapIndexed { index, worker ->
        worker.execute(TransferMode.SAFE, { Pair(atomicArr, index) }) { (arr, idx) ->
            aarbReadyCount.incrementAndGet()
            while (aarbStartSignal.value == 0) {}

            val start = (1000 / ARB_NUM_WORKERS) * idx
            val end = if (idx == ARB_NUM_WORKERS - 1) 1000 else start + (1000 / ARB_NUM_WORKERS)

            var allOk = true
            for (i in start until end) {
                // AtomicArray.get() -> atomicGet() -> ReadVolatileHeapRef
                val elem = arr[i]
                if (!elem.verify()) allOk = false
                if (elem.index != i) allOk = false
            }
            allOk
        }
    }

    while (aarbReadyCount.value < ARB_NUM_WORKERS) {}
    aarbStartSignal.value = 1
    GC.collect()

    var allOk = true
    for (future in futures) {
        if (!future.result) allOk = false
    }
    for (w in workers) {
        w.requestTermination().result
    }
    return allOk
}

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testArrayReadBarrier() {
    for (round in 1..ARB_NUM_ROUNDS) {
        assertTrue(regularArrayTest(round), "Regular array read barrier failed in round $round")
        assertTrue(atomicArrayTest(round), "Atomic array read barrier failed in round $round")
    }

    println("PASS")
}
