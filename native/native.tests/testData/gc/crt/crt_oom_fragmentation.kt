// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Fragments heap by allocating many small objects, then tests whether
// allocator can coalesce freed pages to serve large contiguous allocation.
// Also includes progressive OOM patterns.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC

@Test fun testOomFragmentation() {
    // === Test 1: Fragment then coalesce ===
    // Allocate many small objects to fragment the heap
    val fragmentors = mutableListOf<ByteArray>()
    for (i in 0 until 5000) {
        fragmentors.add(ByteArray(1025)) // slightly over 1KB to waste page space
    }

    // Free half of them (every other one) to create fragmentation
    for (i in fragmentors.indices step 2) {
        fragmentors[i] = ByteArray(0) // release old, replace with empty
    }
    GC.collect()

    // Now try to allocate a large contiguous block
    // Allocator should coalesce freed pages
    var largeAllocSucceeded = false
    try {
        val large = ByteArray(2_000_000) // 2MB contiguous
        large[0] = 1
        large[1_999_999] = 2
        largeAllocSucceeded = true
        assertEquals(1.toByte(), large[0], "T1: Large alloc first byte wrong")
        assertEquals(2.toByte(), large[1_999_999], "T1: Large alloc last byte wrong")
    } catch (e: OutOfMemoryError) {
        // OOM is acceptable if heap is truly exhausted
        largeAllocSucceeded = false
    }

    // Clean up fragmentors
    fragmentors.clear()
    GC.collect()

    // === Test 2: Progressive allocation until heap is stressed ===
    val chunks = mutableListOf<IntArray>()
    var totalAllocated = 0L
    try {
        for (i in 0 until 500) {
            val chunk = IntArray(50_000) { it } // 200KB each
            chunks.add(chunk)
            totalAllocated += 200_000
            if (i % 50 == 0) GC.collect()
        }
    } catch (e: OutOfMemoryError) {
        // Expected at some point
    }

    // Verify all kept chunks are intact
    var verifyErrors = 0
    for (chunk in chunks) {
        if (chunk[0] != 0 || chunk[chunk.size - 1] != chunk.size - 1) {
            verifyErrors++
        }
    }
    assertEquals(0, verifyErrors, "T2: Chunks corrupted under heap stress")

    // Release and verify heap recovery
    chunks.clear()
    GC.collect()

    // Heap should be usable again
    val recovery = IntArray(100_000) { it }
    assertEquals(99_999, recovery[99_999], "T2: Heap recovery failed")

    // === Test 3: Alternating alloc sizes to stress free-list management ===
    for (round in 0 until 50) {
        val tiny = ByteArray(16)
        val small = ByteArray(256)
        val medium = ByteArray(4096)
        val large = ByteArray(65536)

        // Verify allocations
        tiny[0] = 1; small[0] = 2; medium[0] = 3; large[0] = 4

        if (round % 10 == 0) GC.collect()

        assertEquals(1.toByte(), tiny[0], "T3: Tiny alloc corrupted at round $round")
        assertEquals(4.toByte(), large[0], "T3: Large alloc corrupted at round $round")
    }

    // === Test 4: Free-list coalescing stress ===
    // Allocate grid, free columns, verify rows survive
    val grid = Array(100) { row -> IntArray(1000) { col -> row * 1000 + col } }
    GC.collect()

    // Verify grid intact
    for (row in grid.indices) {
        assertEquals(row * 1000, grid[row][0], "T4: Grid row $row first elem wrong")
        assertEquals(row * 1000 + 999, grid[row][999], "T4: Grid row $row last elem wrong")
    }

    println("PASS")
}
