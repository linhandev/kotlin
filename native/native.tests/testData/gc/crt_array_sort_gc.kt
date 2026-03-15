// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests GC safety during array sorting operations.
// Risk: Sort algorithms swap array elements creating temporary derived pointers;
// if GC fires mid-swap, element references may become stale.
// Also tests Array.copyInto which uses bulk memory operations.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class Sortable(val key: Int, val payload: String) : Comparable<Sortable> {
    override fun compareTo(other: Sortable): Int {
        // Trigger GC during comparison — this is the critical moment
        // where array elements are being read/swapped
        if (key % 100 == 0) GC.collect()
        return key.compareTo(other.key)
    }
    fun verify(): Boolean = payload == "sort-$key"
}

@Test fun testArraySortGC() {
    // === Test 1: Sort with GC in comparator ===
    val arr1 = Array(1000) { i ->
        val key = (i * 997) % 1000 // pseudo-random shuffle
        Sortable(key, "sort-$key")
    }
    arr1.sortWith(compareBy { it.key })

    // Verify sorted order and data integrity
    for (i in 0 until 999) {
        assertTrue(arr1[i].key <= arr1[i + 1].key, "T1: Not sorted at index $i")
        assertTrue(arr1[i].verify(), "T1: Element $i corrupted after sort")
    }
    assertTrue(arr1[999].verify(), "T1: Last element corrupted")

    // === Test 2: Array.copyInto during GC ===
    val src = Array(5000) { Sortable(it, "sort-$it") }
    val dst = arrayOfNulls<Sortable>(5000)
    GC.collect()
    src.copyInto(dst)
    GC.collect()

    for (i in src.indices) {
        assertNotNull(dst[i], "T2: copyInto produced null at $i")
        assertTrue(dst[i]!!.verify(), "T2: Copied element $i corrupted")
        assertEquals(src[i].key, dst[i]!!.key, "T2: Key mismatch at $i")
    }

    // === Test 3: Partial array copy with offset ===
    val partial = arrayOfNulls<Sortable>(100)
    src.copyInto(partial, destinationOffset = 0, startIndex = 2500, endIndex = 2600)
    GC.collect()
    for (i in 0 until 100) {
        assertNotNull(partial[i], "T3: Partial copy null at $i")
        assertEquals(2500 + i, partial[i]!!.key, "T3: Partial copy key wrong at $i")
    }

    // === Test 4: Sort IntArray (primitive) — different code path ===
    val intArr = IntArray(100000) { (it * 31337) % 100000 }
    intArr.sort()
    GC.collect()
    for (i in 0 until 99999) {
        assertTrue(intArr[i] <= intArr[i + 1], "T4: IntArray not sorted at $i")
    }

    // === Test 5: Concurrent sort + GC ===
    val sortErrors = AtomicInt(0)
    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, sortErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 20) {
                val localArr = Array(200) { i ->
                    val key = (i * 31 + workerIdx * 7 + round * 13) % 200
                    Sortable(key, "sort-$key")
                }
                localArr.sortBy { it.key }

                // Verify sorted
                for (i in 0 until 199) {
                    if (localArr[i].key > localArr[i + 1].key) {
                        errors.incrementAndGet()
                        break
                    }
                }
                // Verify data
                for (elem in localArr) {
                    if (!elem.verify()) {
                        errors.incrementAndGet()
                        break
                    }
                }
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, sortErrors.value, "T5: Concurrent sort errors")

    println("PASS")
}
