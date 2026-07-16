// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests large object allocation and collection under CRT GC.
// Risk: Large objects may use different allocation paths; mixing large and small
// objects can cause fragmentation; forwarding pointers for large arrays may fail.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

var largeObjectSink: LongArray? = null

@Test fun testLargeObjectStress() {
    // === Test 1: Large array allocation + GC ===
    for (round in 0 until 5) {
        val bigArray = LongArray(1_000_000) { it.toLong() }
        GC.collect()
        // Verify first, middle, and last elements
        assertEquals(0L, bigArray[0], "T1: Round $round first element wrong")
        assertEquals(500_000L, bigArray[500_000], "T1: Round $round middle element wrong")
        assertEquals(999_999L, bigArray[999_999], "T1: Round $round last element wrong")
    }

    // === Test 2: Many large objects alive simultaneously ===
    val largeArrays = Array(20) { i ->
        IntArray(100_000) { j -> i * 100_000 + j }
    }
    GC.collect()
    for (i in largeArrays.indices) {
        assertEquals(i * 100_000, largeArrays[i][0], "T2: Array $i first element wrong")
        assertEquals(i * 100_000 + 99_999, largeArrays[i][99_999], "T2: Array $i last element wrong")
    }

    // === Test 3: Alternating large and small allocations ===
    var t3Errors = 0
    for (round in 0 until 30) {
        // Small objects
        val small = Array(100) { "small-$round-$it" }
        // Large object
        val large = ByteArray(500_000) { (it % 256).toByte() }
        // More small objects
        val small2 = Array(100) { round * 100 + it }

        if (round % 5 == 0) GC.collect()

        // Verify all survived
        for (i in small.indices) {
            if (small[i] != "small-$round-$i") t3Errors++
        }
        if (large[0] != 0.toByte()) t3Errors++
        if (large[499_999] != (499_999 % 256).toByte()) t3Errors++
        for (i in small2.indices) {
            if (small2[i] != round * 100 + i) t3Errors++
        }
    }
    assertEquals(0, t3Errors, "T3: Mixed size allocation errors: $t3Errors")

    // === Test 4: Large object with internal references ===
    val bigObjArray = Array(50_000) { i -> IntArray(10) { i * 10 + it } }
    GC.collect()
    var t4Errors = 0
    for (i in 0 until 50_000 step 1000) {
        for (j in 0 until 10) {
            if (bigObjArray[i][j] != i * 10 + j) t4Errors++
        }
    }
    assertEquals(0, t4Errors, "T4: Large array of arrays corrupted")

    // === Test 5: Concurrent large allocation + GC ===
    val allocErrors = AtomicInt(0)
    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, allocErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 10) {
                val arr = LongArray(200_000) { it.toLong() + workerIdx * 1_000_000L }
                if (round % 3 == 0) GC.collect()
                if (arr[0] != workerIdx * 1_000_000L) errors.incrementAndGet()
                if (arr[199_999] != 199_999L + workerIdx * 1_000_000L) errors.incrementAndGet()
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, allocErrors.value, "T5: Concurrent large allocation errors")

    // === Test 6: Rapid large alloc/dealloc cycle (fragmentation stress) ===
    for (round in 0 until 50) {
        // Allocate large objects; assign to global to prevent compiler optimization
        largeObjectSink = LongArray(100_000) { round.toLong() }
        if (round % 10 == 0) GC.collect()
    }
    largeObjectSink = null
    // After heavy fragmentation, verify allocation still works
    val postFragArray = LongArray(500_000) { it.toLong() }
    GC.collect()
    assertEquals(499_999L, postFragArray[499_999], "T6: Post-fragmentation allocation failed")

    println("PASS")
}
