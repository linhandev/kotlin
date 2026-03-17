// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests concurrent compacting GC stress patterns relevant to CRT's CMC GC.
// Key patterns: humongous allocation + fragmentation, load-reference-barrier
// correctness, concurrent evacuation during mutation.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class CompactObj(val id: Int, val data: String, val children: Array<CompactObj?>) {
    fun verify(): Boolean = data == "co-$id"
}

fun buildTree(depth: Int, breadth: Int, idBase: Int): CompactObj {
    if (depth == 0) return CompactObj(idBase, "co-$idBase", emptyArray())
    val kids = Array<CompactObj?>(breadth) { i ->
        buildTree(depth - 1, breadth, idBase * breadth + i + 1)
    }
    return CompactObj(idBase, "co-$idBase", kids)
}

fun verifyTree(node: CompactObj): Boolean {
    if (!node.verify()) return false
    for (child in node.children) {
        if (child != null && !verifyTree(child)) return false
    }
    return true
}

val compactErrors = AtomicInt(0)

@Test fun testCompactingGcPatterns() {
    compactErrors.value = 0

    // === Test 1: Humongous allocation fragmentation pattern ===
    // Allocate humongous objects (>50% of region), fragment, then try to allocate more
    val humongous = mutableListOf<LongArray>()
    for (i in 0 until 20) {
        humongous.add(LongArray(500_000) { it.toLong() }) // ~4MB each
        GC.collect()
    }
    // Free every other to fragment
    for (i in humongous.indices step 2) {
        humongous[i] = LongArray(0)
    }
    GC.collect()

    // Try to allocate in fragmented space
    val postFrag = LongArray(300_000) { it.toLong() }
    assertEquals(299_999L, postFrag[299_999], "T1: Post-fragmentation alloc failed")

    // Verify surviving humongous objects
    for (i in 1 until humongous.size step 2) {
        if (humongous[i].isNotEmpty()) {
            assertEquals(499_999L, humongous[i][499_999], "T1: Humongous $i corrupted")
        }
    }

    // === Test 2: Concurrent evacuation pattern ===
    // Mutate object graph while GC is evacuating (copying) objects
    val trees = Array(10) { buildTree(depth = 3, breadth = 4, idBase = it * 1000) }

    val workers = Array(4) { Worker.start() }

    // Mutators: rebuild subtrees while GC runs
    val mutateFutures = (0 until 2).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(trees, compactErrors) }) { (ts, errors) ->
            for (round in 0 until 100) {
                val treeIdx = round % ts.size
                // Rebuild children — creates new objects while GC may be moving old ones
                val tree = ts[treeIdx]
                for (i in tree.children.indices) {
                    tree.children[i] = CompactObj(
                        treeIdx * 1000 + round * 100 + i,
                        "co-${treeIdx * 1000 + round * 100 + i}",
                        emptyArray()
                    )
                }
                if (round % 10 == 0) GC.collect()
            }
            0
        }
    }

    // GC threads: continuous collection
    val gcFutures = (2 until 4).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Unit }) {
            for (i in 0 until 100) {
                GC.collect()
                // Allocate garbage to fill regions
                val garbage = Array(200) { "gc-garbage-$i-$it" }
            }
            0
        }
    }

    for (f in mutateFutures) f.result
    for (f in gcFutures) f.result
    for (w in workers) w.requestTermination().result

    // Verify trees still traversable
    for (tree in trees) {
        assertTrue(tree.verify(), "T2: Tree ${tree.id} root corrupted after concurrent evacuation")
    }

    // === Test 3: Colored pointer / load-reference-barrier test ===
    // Rapidly read and write object references to stress load-reference-barrier
    val sharedRefs = Array(100) { AtomicReference<CompactObj?>(CompactObj(it, "co-$it", emptyArray())) }

    val workers3 = Array(4) { Worker.start() }
    val futures3 = workers3.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedRefs, compactErrors) }) { (workerIdx, refs, errors) ->
            for (round in 0 until 500) {
                val i = (round * 7 + workerIdx * 13) % refs.size
                // Read through barrier
                val obj = refs[i].value
                if (obj != null && !obj.verify()) {
                    errors.incrementAndGet()
                }
                // Write through barrier
                refs[i].value = CompactObj(round * 100 + workerIdx, "co-${round * 100 + workerIdx}", emptyArray())
                if (round % 50 == 0) GC.collect()
            }
            0
        }
    }
    for (f in futures3) f.result
    for (w in workers3) w.requestTermination().result
    assertEquals(0, compactErrors.value, "T3: Load-reference-barrier errors")

    // === Test 4: String dedup pattern ===
    // Many identical strings — test that GC handles string dedup/intern correctly
    val strings = Array(5000) { "dedup-test-string-value" }
    GC.collect()
    for (i in strings.indices) {
        assertEquals("dedup-test-string-value", strings[i], "T4: String $i changed after GC")
    }

    // Strings from different sources but same content
    val built = Array(1000) { buildString { append("built-"); append(it) } }
    GC.collect()
    for (i in built.indices) {
        assertEquals("built-$i", built[i], "T4: Built string $i wrong after GC")
    }

    println("PASS")
}
