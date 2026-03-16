// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests collection iterators, sequences, and functional chains under GC.
// Risk: Iterators hold internal state (current position, backing collection ref);
// sequence chains create lazy evaluation chains that hold closure captures;
// GC during iteration may invalidate iterator state if references are moved.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC

class SeqPayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "seq-$id"
}

@Test fun testIteratorSequenceGC() {
    // === Test 1: List iterator with GC between iterations ===
    val list = List(1000) { SeqPayload(it, "seq-$it") }
    val iter = list.iterator()
    var count = 0
    while (iter.hasNext()) {
        val elem = iter.next()
        assertTrue(elem.verify(), "T1: Iterator element $count corrupted")
        if (count % 100 == 0) GC.collect()
        count++
    }
    assertEquals(1000, count, "T1: Iterator count wrong")

    // === Test 2: Map iteration with GC ===
    val map = (0 until 500).associate { it to SeqPayload(it, "seq-$it") }
    GC.collect()

    var mapErrors = 0
    for ((key, value) in map) {
        if (key != value.id || !value.verify()) mapErrors++
    }
    assertEquals(0, mapErrors, "T2: Map iteration errors after GC")

    // === Test 3: Sequence chain (lazy) + GC ===
    val seq = (0 until 5000).asSequence()
        .map { SeqPayload(it, "seq-$it") }
        .filter { it.id % 3 == 0 }
        .map { "${it.data}-filtered" }
        .take(500)

    GC.collect()

    val results = seq.toList()
    GC.collect()
    assertEquals(500, results.size, "T3: Sequence result size wrong")
    for (i in results.indices) {
        assertTrue(results[i].endsWith("-filtered"), "T3: Sequence element $i wrong")
    }

    // === Test 4: Nested flatMap sequence + GC ===
    val nested = (0 until 100).asSequence()
        .flatMap { outer ->
            (0 until 10).asSequence().map { inner ->
                SeqPayload(outer * 10 + inner, "seq-${outer * 10 + inner}")
            }
        }

    GC.collect()

    var nestedCount = 0
    var nestedErrors = 0
    for (p in nested) {
        if (!p.verify()) nestedErrors++
        nestedCount++
        if (nestedCount % 200 == 0) GC.collect()
    }
    assertEquals(1000, nestedCount, "T4: Nested flatMap count wrong")
    assertEquals(0, nestedErrors, "T4: Nested flatMap errors")

    // === Test 5: MutableList modification during iteration-like traversal + GC ===
    val mutable = (0 until 500).map { SeqPayload(it, "seq-$it") }.toMutableList()
    GC.collect()

    // Remove every other element
    var removeIdx = 0
    val removeIter = mutable.iterator()
    while (removeIter.hasNext()) {
        removeIter.next()
        if (removeIdx % 2 == 0) removeIter.remove()
        removeIdx++
        if (removeIdx % 50 == 0) GC.collect()
    }
    assertEquals(250, mutable.size, "T5: After removal size wrong")
    for (elem in mutable) {
        assertTrue(elem.verify(), "T5: Remaining element ${elem.id} corrupted")
    }

    // === Test 6: groupBy + GC ===
    val grouped = (0 until 1000).map { SeqPayload(it, "seq-$it") }
        .groupBy { it.id % 10 }
    GC.collect()

    assertEquals(10, grouped.size, "T6: Group count wrong")
    for ((key, values) in grouped) {
        assertEquals(100, values.size, "T6: Group $key size wrong")
        for (v in values) {
            assertTrue(v.verify(), "T6: Group $key element corrupted")
            assertEquals(key, v.id % 10, "T6: Wrong group for ${v.id}")
        }
    }

    // === Test 7: Zip + GC ===
    val left = List(500) { SeqPayload(it, "seq-$it") }
    val right = List(500) { "right-$it" }
    GC.collect()

    val zipped = left.zip(right)
    GC.collect()
    assertEquals(500, zipped.size, "T7: Zip size wrong")
    for (i in zipped.indices) {
        assertTrue(zipped[i].first.verify(), "T7: Zip left $i corrupted")
        assertEquals("right-$i", zipped[i].second, "T7: Zip right $i wrong")
    }

    // === Test 8: fold/reduce with accumulator surviving GC ===
    val items = List(200) { SeqPayload(it, "seq-$it") }
    var foldResult = items.fold("") { acc, elem ->
        if (elem.id % 50 == 0) GC.collect()
        acc + elem.id.toString() + ","
    }
    assertTrue(foldResult.startsWith("0,1,2,"), "T8: Fold result wrong start")
    assertTrue(foldResult.contains("199,"), "T8: Fold result missing last element")

    println("PASS")
}
