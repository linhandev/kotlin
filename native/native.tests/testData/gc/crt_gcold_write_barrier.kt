// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Classic write barrier benchmark. Tests:
// 1. Long-lived balanced trees (old generation pressure)
// 2. Short-lived young garbage (young generation throughput)
// 3. Pointer mutation on old trees (write barrier correctness)
// 4. Subtree swapping between old trees (cross-region references)

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC

class OldGenNode {
    var left: OldGenNode? = null
    var right: OldGenNode? = null
    var height: Int = 0
}

const val WB_TREE_HEIGHT = 10  // ~1023 nodes per tree
const val WB_NUM_TREES = 20
const val WB_STEPS = 50
const val WB_MUTATIONS_PER_STEP = 20

fun makeOldGenTree(h: Int): OldGenNode? {
    if (h == 0) return null
    val node = OldGenNode()
    node.height = h
    node.left = makeOldGenTree(h - 1)
    node.right = makeOldGenTree(h - 1)
    return node
}

fun treeHeight(t: OldGenNode?): Int {
    if (t == null) return 0
    return 1 + maxOf(treeHeight(t.left), treeHeight(t.right))
}

fun shortestPath(t: OldGenNode?): Int {
    if (t == null) return 0
    return 1 + minOf(shortestPath(t.left), shortestPath(t.right))
}

// Replace a subtree in full with partial (pointer mutation pattern)
fun replaceSubtree(full: OldGenNode, partial: OldGenNode, goLeft: Boolean) {
    val canGoLeft = full.left != null && full.left!!.height > partial.height
    val canGoRight = full.right != null && full.right!!.height > partial.height
    when {
        canGoLeft && canGoRight -> {
            if (goLeft) replaceSubtree(full.left!!, partial, !goLeft)
            else replaceSubtree(full.right!!, partial, !goLeft)
        }
        !canGoLeft && !canGoRight -> {
            if (goLeft) full.left = partial else full.right = partial
        }
        !canGoLeft -> full.left = partial
        else -> full.right = partial
    }
}

// Swap subtrees between two trees at random depth (critical write barrier operation)
fun swapSubtrees(t1: OldGenNode, t2: OldGenNode, depth: Int, path: Int) {
    var node1 = t1
    var node2 = t2
    var p = path
    for (i in 0 until depth) {
        if (p and 1 == 0) {
            if (node1.left == null || node2.left == null) break
            node1 = node1.left!!
            node2 = node2.left!!
        } else {
            if (node1.right == null || node2.right == null) break
            node1 = node1.right!!
            node2 = node2.right!!
        }
        p = p shr 1
    }
    // Swap children — this is the critical write barrier operation
    val tempLeft = node1.left
    node1.left = node2.left   // old→new reference, must trigger write barrier
    node2.left = tempLeft     // old→new reference, must trigger write barrier
}

@Test fun testWriteBarrierBenchmark() {
    // === Phase 1: Initialize long-lived trees ===
    val trees = Array(WB_NUM_TREES) { makeOldGenTree(WB_TREE_HEIGHT)!! }

    // Verify initial structure
    for (i in trees.indices) {
        assertEquals(WB_TREE_HEIGHT, treeHeight(trees[i]), "Init: Tree $i height wrong")
        assertEquals(WB_TREE_HEIGHT, shortestPath(trees[i]), "Init: Tree $i not balanced")
    }

    // === Phase 2: Main loop — young garbage + old mutation + subtree swaps ===
    var seed = 42
    fun nextRand(bound: Int): Int {
        seed = (seed * 1103515245 + 12345) and 0x7FFFFFFF
        return seed % bound
    }

    for (step in 0 until WB_STEPS) {
        // Young garbage: allocate short-lived arrays (simulates young gen pressure)
        for (j in 0 until 100) {
            val garbage = IntArray(100) { step * 100 + j }
            // Don't hold reference — becomes garbage immediately
        }

        // Old generation allocation: replace one tree with fresh tree
        if (step % 10 == 0) {
            val idx = nextRand(WB_NUM_TREES)
            trees[idx] = makeOldGenTree(WB_TREE_HEIGHT)!!
        }

        // Pointer mutation: replace subtrees in old trees
        for (m in 0 until WB_MUTATIONS_PER_STEP) {
            val treeIdx = nextRand(WB_NUM_TREES)
            val subtreeHeight = nextRand(WB_TREE_HEIGHT - 2) + 1
            val newSubtree = makeOldGenTree(subtreeHeight)!!
            val goLeft = (newSubtree.height % 2) == 0
            replaceSubtree(trees[treeIdx], newSubtree, goLeft)
        }

        // Subtree swaps between different trees (cross-region references)
        for (s in 0 until 5) {
            val idx1 = nextRand(WB_NUM_TREES)
            val idx2 = nextRand(WB_NUM_TREES)
            if (idx1 != idx2) {
                val depth = nextRand(WB_TREE_HEIGHT - 2) + 1
                val path = nextRand(1 shl depth)
                swapSubtrees(trees[idx1], trees[idx2], depth, path)
            }
        }

        // Explicit GC every few steps
        if (step % 5 == 0) GC.collect()
    }

    // === Phase 3: Verify trees are still traversable ===
    var verifyErrors = 0
    for (i in trees.indices) {
        val h = treeHeight(trees[i])
        if (h < 1) verifyErrors++ // tree should have some height
        // Note: after mutations, trees are no longer perfectly balanced,
        // but they must still be traversable without crash
    }
    assertEquals(0, verifyErrors, "Trees corrupted after ${WB_STEPS} steps")

    // === Phase 4: Final verification — bulk GC then check ===
    repeat(5) { GC.collect() }

    for (i in trees.indices) {
        val h = treeHeight(trees[i])
        assertTrue(h >= 1, "Final: Tree $i collapsed to height $h")
    }

    println("PASS")
}
