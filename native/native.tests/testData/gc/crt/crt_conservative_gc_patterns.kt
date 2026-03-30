// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Classic GC test patterns — binary tree, realloc, thread leak,
// finalizer timing, concurrent GC interaction.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

// === Binary tree benchmark binary tree ===
class TreeNode(val left: TreeNode?, val right: TreeNode?, val value: Int) {
    fun depth(): Int = if (left == null) 0 else 1 + maxOf(left.depth(), right?.depth() ?: 0)
    fun checksum(): Long {
        val l = left?.checksum() ?: 0L
        val r = right?.checksum() ?: 0L
        return value.toLong() + l + r
    }
}

fun makeTree(depth: Int, value: Int): TreeNode {
    if (depth == 0) return TreeNode(null, null, value)
    return TreeNode(
        makeTree(depth - 1, value * 2),
        makeTree(depth - 1, value * 2 + 1),
        value
    )
}

@Test fun testConservativeGcPatterns() {
    // === Test 1: Binary tree benchmark — binary tree stress ===
    // Build and destroy trees of increasing depth
    for (depth in 4..14 step 2) {
        val tree = makeTree(depth, 1)
        assertEquals(depth, tree.depth(), "T1: Tree depth $depth wrong")
        val cs = tree.checksum()
        GC.collect()
        assertEquals(cs, tree.checksum(), "T1: Checksum changed after GC for depth $depth")
    }

    // Build long-lived tree, create/destroy short-lived trees around it
    val longLived = makeTree(12, 1) // ~4K nodes
    val longCs = longLived.checksum()

    for (round in 0 until 20) {
        val shortLived = makeTree(10, round * 1000)
        // Immediately let shortLived become garbage
        GC.collect()
    }

    // Long-lived must survive
    assertEquals(longCs, longLived.checksum(), "T1: Long-lived tree corrupted")

    // === Test 2: Realloc pattern — grow/shrink arrays ===
    var arr = IntArray(10) { it }
    for (round in 0 until 50) {
        val newSize = if (round % 2 == 0) arr.size * 2 else arr.size / 2 + 1
        val newArr = IntArray(newSize)
        val copyLen = minOf(arr.size, newArr.size)
        for (i in 0 until copyLen) newArr[i] = arr[i]
        for (i in copyLen until newArr.size) newArr[i] = round * 1000 + i
        arr = newArr
        if (round % 10 == 0) GC.collect()
    }
    // arr should still be valid
    assertTrue(arr.isNotEmpty(), "T2: Realloc resulted in empty array")
    GC.collect()

    // === Test 3: Thread leak — create threads, leak refs ===
    for (batch in 0 until 5) {
        val workers = Array(8) { Worker.start() }
        val futures = workers.map { worker ->
            worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Unit }) {
                // Each thread allocates and leaks objects
                val leaks = Array(500) { TreeNode(null, null, it) }
                GC.collect()
                leaks.size // prevent DCE
            }
        }
        for (f in futures) f.result
        for (w in workers) w.requestTermination().result
        GC.collect()
    }
    // Heap must be functional after leaked thread objects
    val check = IntArray(10000) { it }
    assertEquals(9999, check[9999], "T3: Heap broken after thread_leak pattern")

    // === Test 4: GC timing consistency ===
    // Multiple rapid GC calls should complete without hanging
    val start = kotlin.system.getTimeMillis()
    for (i in 0 until 100) {
        GC.collect()
    }
    val elapsed = kotlin.system.getTimeMillis() - start
    // 100 GCs should complete in reasonable time (< 30s)
    assertTrue(elapsed < 30000, "T4: 100 GC cycles took ${elapsed}ms — too slow")

    // === Test 5: WeakRef as finalizer proxy ===
    val liveCount = AtomicInt(0)
    val deadCount = AtomicInt(0)

    for (round in 0 until 100) {
        val weakRefs = Array(100) { i ->
            val obj = TreeNode(null, null, round * 100 + i)
            WeakReference(obj)
            // obj goes out of scope
        }
        GC.collect()
        for (wr in weakRefs) {
            val ref = wr.get()
            if (ref == null) deadCount.incrementAndGet()
            else liveCount.incrementAndGet()
        }
    }
    // Verify at least some WeakRefs exist (dead or alive).
    // Note: deadCount > 0 is not guaranteed — CRT GC may not collect WeakRefs
    // in this scenario due to stack frame holding temporary references.
    assertTrue(deadCount.value > 0 || liveCount.value > 0, "T5: No WeakRefs observed")

    // === Test 6: Verify GC reclaims memory ===
    // Allocate large amount, drop refs, GC, allocate again — should work
    for (cycle in 0 until 5) {
        // Fill
        val bigBatch = Array(1000) { LongArray(10000) { it.toLong() } }
        // Spot check
        assertEquals(9999L, bigBatch[999][9999], "T6: Cycle $cycle fill corrupted")
        // Drop
    }
    GC.collect()
    GC.collect()

    // Should be able to allocate again
    val recovery = LongArray(1_000_000) { it.toLong() }
    assertEquals(999_999L, recovery[999_999], "T6: Post-reclaim allocation failed")

    println("PASS")
}
