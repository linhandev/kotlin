// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests HashMap/HashSet internal resize operations under GC pressure.
// Risk: HashMap.put triggers internal array resize (rehash) which creates
// new backing array and copies entries — if GC fires during rehash,
// old and new backing arrays both exist with references to same entries.
// Also tests hashCode() stability after GC-induced object moves.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class MapKey(val id: Int) {
    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean = other is MapKey && other.id == id
    override fun toString(): String = "key-$id"
}

class MapValue(val id: Int, val data: String) {
    fun verify(): Boolean = data == "val-$id"
}

@Test fun testHashMapResizeGC() {
    // === Test 1: HashMap grow through multiple resize thresholds + GC ===
    val map = HashMap<MapKey, MapValue>()
    for (i in 0 until 10000) {
        map[MapKey(i)] = MapValue(i, "val-$i")
        // GC at powers of 2 (near resize points)
        if (i > 0 && (i and (i - 1)) == 0) GC.collect()
    }

    // Verify all entries survived
    var t1Errors = 0
    for (i in 0 until 10000) {
        val v = map[MapKey(i)]
        if (v == null || !v.verify()) t1Errors++
    }
    assertEquals(0, t1Errors, "T1: HashMap entries corrupted after resize+GC")
    assertEquals(10000, map.size, "T1: HashMap size wrong")

    // === Test 2: HashMap iteration + GC ===
    var iterCount = 0
    var iterErrors = 0
    for ((k, v) in map) {
        if (!v.verify()) iterErrors++
        if (k.id != v.id) iterErrors++
        iterCount++
        if (iterCount % 500 == 0) GC.collect()
    }
    assertEquals(10000, iterCount, "T2: Iterator count wrong")
    assertEquals(0, iterErrors, "T2: Iterator saw corrupted entries")

    // === Test 3: HashMap remove + GC ===
    for (i in 0 until 5000) {
        map.remove(MapKey(i))
        if (i % 500 == 0) GC.collect()
    }
    assertEquals(5000, map.size, "T3: Size after removal wrong")
    for (i in 5000 until 10000) {
        val v = map[MapKey(i)]
        assertNotNull(v, "T3: Missing entry $i after partial removal")
        assertTrue(v.verify(), "T3: Entry $i corrupted after removal+GC")
    }

    // === Test 4: HashSet with many collisions + GC ===
    // All keys hash to same bucket — worst-case chain traversal
    class CollidingKey(val id: Int) {
        override fun hashCode(): Int = 42 // all same hash
        override fun equals(other: Any?): Boolean = other is CollidingKey && other.id == id
    }

    val set = HashSet<CollidingKey>()
    for (i in 0 until 500) {
        set.add(CollidingKey(i))
        if (i % 50 == 0) GC.collect()
    }
    assertEquals(500, set.size, "T4: Collision set size wrong")
    for (i in 0 until 500) {
        assertTrue(set.contains(CollidingKey(i)), "T4: Missing collision entry $i")
    }

    // === Test 5: LinkedHashMap (insertion order) + GC ===
    val linked = LinkedHashMap<MapKey, MapValue>()
    for (i in 0 until 5000) {
        linked[MapKey(i)] = MapValue(i, "val-$i")
        if (i % 500 == 0) GC.collect()
    }
    // Verify insertion order preserved
    var idx = 0
    for ((k, v) in linked) {
        assertEquals(idx, k.id, "T5: LinkedHashMap order broken at position $idx")
        assertTrue(v.verify(), "T5: Entry $idx corrupted")
        idx++
    }
    assertEquals(5000, idx, "T5: LinkedHashMap iteration count wrong")

    // === Test 6: Map.toList / entries.toList — snapshot during GC ===
    val snapshot = linked.entries.toList()
    GC.collect()
    assertEquals(5000, snapshot.size, "T6: Snapshot size wrong")
    for (i in snapshot.indices) {
        assertTrue(snapshot[i].value.verify(), "T6: Snapshot entry $i corrupted after GC")
    }

    println("PASS")
}
