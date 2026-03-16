// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests cleaner execution on objects with complex reference graphs and interleaved GC.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.ref.createCleaner
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import platform.posix.usleep

val cleanedCount = AtomicInt(0)

class Node(val id: Int) {
    var left: Node? = null
    var right: Node? = null
    val cleaner = createCleaner(id) { _: Int ->
        cleanedCount.incrementAndGet()
    }
}

fun buildTree(depth: Int, startId: Int): Node {
    if (depth == 0) return Node(startId)
    val node = Node(startId)
    node.left = buildTree(depth - 1, startId * 2)
    node.right = buildTree(depth - 1, startId * 2 + 1)
    return node
}

fun testComplexReferenceGraph() {
    cleanedCount.value = 0

    // Build and discard trees with complex reference graphs
    run {
        for (i in 0 until 5) {
            buildTree(depth = 4, startId = i * 100)
        }
    }

    repeat(10) { GC.collect() }
    usleep(500_000u)

    assertTrue(cleanedCount.value >= 1, "At least one cleaner should run after complex graph")
}

fun testInterleavedCleanerAndGC() {
    cleanedCount.value = 0

    for (round in 0 until 20) {
        // Create a small batch of objects
        run {
            for (i in 0 until 10) {
                Node(round * 10 + i)
            }
        }
        // Trigger GC between creation rounds
        GC.collect()
        usleep(10_000u)
    }

    repeat(5) { GC.collect() }
    usleep(500_000u)

    assertTrue(cleanedCount.value >= 1, "At least one cleaner should run after interleaved GC")
}

fun testCleanerWithCycles() {
    cleanedCount.value = 0

    run {
        // Create objects with cyclic references
        for (i in 0 until 10) {
            val a = Node(i * 3)
            val b = Node(i * 3 + 1)
            val c = Node(i * 3 + 2)
            a.left = b
            b.left = c
            c.left = a  // cycle
        }
    }

    repeat(10) { GC.collect() }
    usleep(500_000u)

    assertTrue(cleanedCount.value >= 1, "At least one cleaner should run after cycles")
}

@Test fun testCleanerComplexGraph() {
    testComplexReferenceGraph()
    testInterleavedCleanerAndGC()
    testCleanerWithCycles()
    println("PASS")
}
