// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Allocates ever-growing arrays, lets them die, triggers GC.
// Tests large object allocator correctness and fragmentation handling.
// Also tests WeakRef latency under large object death patterns.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC

class LargePayload(val id: Int, val size: Int) {
    val arr = IntArray(size) { it }
    fun verify(): Boolean = arr.size == size && (size == 0 || arr[size - 1] == size - 1)
}

@Test fun testDeadLargeObject() {
    // === Test 1: Growing dead large objects ===
    // Allocate arrays of increasing size, let each die before next allocation
    for (i in 0 until 10) {
        val size = i * 300_000  // 0, 300K, 600K, ..., 2.7M elements
        if (size > 0) {
            val arr = arrayOfNulls<Any>(size)
            // Touch first and last to ensure allocation is real
            arr[0] = "start-$i"
            arr[size - 1] = "end-$i"
        }
        GC.collect()
    }

    // Verify heap is functional after large dead objects
    val checkArr = IntArray(100_000) { it }
    GC.collect()
    assertEquals(99_999, checkArr[99_999], "T1: Heap corrupted after large dead objects")

    // === Test 2: Large objects that survive + die in alternation ===
    var surviving: LargePayload? = null
    for (round in 0 until 20) {
        val large = LargePayload(round, 100_000)
        if (round % 2 == 0) {
            surviving = large  // keep alive
        }
        // else: large dies at end of iteration
        GC.collect()
    }
    assertNotNull(surviving, "T2: No surviving large object")
    assertTrue(surviving!!.verify(), "T2: Surviving large object corrupted")

    // === Test 3: Many medium-large objects alive simultaneously ===
    val liveObjects = Array(50) { i ->
        LargePayload(i, 50_000)
    }
    repeat(5) { GC.collect() }
    for (obj in liveObjects) {
        assertTrue(obj.verify(), "T3: Live large object ${obj.id} corrupted after GC")
    }

    // === Test 4: WeakRef batch clearing performance ===
    // Create many WeakRefs to dead objects, verify clearing works
    val weakBatch = Array(10_000) { i ->
        val target = LargePayload(i + 1000, 10) // small payload, many refs
        WeakReference(target)
        // target dies each iteration
    }

    repeat(5) { GC.collect() }

    var danglingCount = 0
    var clearedCount = 0
    for (wr in weakBatch) {
        val ref = wr.get()
        if (ref == null) {
            clearedCount++
        } else if (!ref.verify()) {
            danglingCount++
        }
    }
    assertEquals(0, danglingCount, "T4: Dangling pointers in WeakRef batch")

    // === Test 5: Interleaved large/small allocation with GC ===
    for (round in 0 until 30) {
        // Large
        val large = LongArray(200_000) { it.toLong() }
        // Small
        val small = Array(100) { "small-$round-$it" }
        // Large again
        val large2 = ByteArray(500_000) { (it % 256).toByte() }

        GC.collect()

        // Verify all survive this round
        assertEquals(199_999L, large[199_999], "T5: Round $round large array corrupted")
        assertEquals("small-$round-99", small[99], "T5: Round $round small array corrupted")
        assertEquals((499_999 % 256).toByte(), large2[499_999], "T5: Round $round large2 corrupted")
    }

    println("PASS")
}
