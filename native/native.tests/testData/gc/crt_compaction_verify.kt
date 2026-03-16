// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests reference integrity after CRT compaction (From→To-space copy).
// Risk: CRT forwarding pointer update may miss derived pointers, array elements,
// or cross-region references, causing stale pointers after compaction.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.identityHashCode

// Mesh node: each node has a unique tag and references to other nodes
class MeshNode(val tag: Int) {
    val neighbors = mutableListOf<MeshNode>()
    val data = "node-$tag"

    fun verify(): Boolean = data == "node-$tag"
}

// Build a mesh graph: N nodes, each connected to K random neighbors
fun buildMesh(size: Int, connectionsPerNode: Int): Array<MeshNode> {
    val nodes = Array(size) { MeshNode(it) }

    // Connect nodes in a deterministic pattern (not truly random, but creates complex graph)
    for (i in nodes.indices) {
        for (j in 0 until connectionsPerNode) {
            val target = (i * 7 + j * 13 + 3) % size  // deterministic pseudo-random
            if (target != i) {
                nodes[i].neighbors.add(nodes[target])
            }
        }
    }
    return nodes
}

// Verify entire mesh: all tags match, all neighbor references point to valid nodes
fun verifyMesh(nodes: Array<MeshNode>): Int {
    var errors = 0
    val tagsSeen = mutableSetOf<Int>()

    for (node in nodes) {
        // Check node's own data
        if (!node.verify()) {
            errors++
            continue
        }
        tagsSeen.add(node.tag)

        // Check all neighbor references
        for (neighbor in node.neighbors) {
            if (!neighbor.verify()) {
                errors++
            }
            // Verify neighbor's tag is in valid range
            if (neighbor.tag < 0 || neighbor.tag >= nodes.size) {
                errors++
            }
        }
    }

    // All tags should be present
    if (tagsSeen.size != nodes.size) {
        errors++
    }
    return errors
}

@Test fun testCompactionVerify() {
    // === Test 1: Small mesh, many GC cycles ===
    val smallMesh = buildMesh(200, 5)
    val initialHashes = IntArray(200) { smallMesh[it].identityHashCode() }

    repeat(20) { GC.collect() }

    var errors = verifyMesh(smallMesh)
    assertEquals(0, errors, "T1: Small mesh verification failed after 20 GC cycles")

    // After compaction, identity hash codes should still be stable
    // (KN computes hash from object address or stores it — either way, must be consistent)
    var hashErrors = 0
    for (i in smallMesh.indices) {
        if (smallMesh[i].identityHashCode() != initialHashes[i]) {
            hashErrors++
        }
    }
    assertEquals(0, hashErrors, "T1: Identity hash codes changed after compaction")

    // === Test 2: Large mesh with interleaved allocation + GC ===
    val largeMesh = buildMesh(1000, 8)

    for (round in 0 until 10) {
        // Allocate garbage between GC cycles to cause heap fragmentation
        val garbage = Array(500) { MeshNode(10000 + round * 500 + it) }
        // Link garbage to create references that will become dangling after collection
        for (i in 0 until garbage.size - 1) {
            garbage[i].neighbors.add(garbage[i + 1])
        }
        GC.collect()

        // Verify the main mesh is still intact after each GC
        errors = verifyMesh(largeMesh)
        assertEquals(0, errors, "T2: Large mesh corrupted at round $round")
    }

    // === Test 3: Array elements — derived pointers into arrays ===
    val arrayHolder = Array(500) { MeshNode(2000 + it) }
    // Cross-reference: array elements point to mesh nodes
    for (i in 0 until 500) {
        arrayHolder[i].neighbors.add(largeMesh[i % 1000])
    }

    repeat(10) { GC.collect() }

    var arrayErrors = 0
    for (i in 0 until 500) {
        if (!arrayHolder[i].verify()) arrayErrors++
        for (n in arrayHolder[i].neighbors) {
            if (!n.verify()) arrayErrors++
        }
    }
    assertEquals(0, arrayErrors, "T3: Array element references corrupted after compaction")

    // === Test 4: Deep reference chain — forwarding must propagate ===
    var head = MeshNode(9000)
    var cur = head
    for (i in 1 until 500) {
        val next = MeshNode(9000 + i)
        cur.neighbors.add(next)
        cur = next
    }

    repeat(10) { GC.collect() }

    // Walk the chain and verify
    cur = head
    var chainLength = 1
    while (cur.neighbors.isNotEmpty()) {
        assertTrue(cur.verify(), "T4: Chain node ${cur.tag} corrupted")
        cur = cur.neighbors[0]
        chainLength++
    }
    assertEquals(500, chainLength, "T4: Chain length changed after compaction")

    println("PASS")
}
