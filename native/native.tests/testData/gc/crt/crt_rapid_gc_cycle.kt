// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests rapid GC cycle triggering — back-to-back GC.collect() calls.
// Risk: Rapid GC cycles may not properly handle concurrent mutator activity;
// incomplete previous cycle may overlap with new cycle start;
// STW pause coordination may fail under rapid cycling.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC

class RapidPayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "rapid-$id"
}

@Test fun testRapidGCCycle() {
    // === Test 1: 1000 back-to-back GC.collect() ===
    val sentinel = RapidPayload(0, "rapid-0")
    for (i in 0 until 1000) {
        GC.collect()
    }
    assertTrue(sentinel.verify(), "T1: Sentinel corrupted after 1000 rapid GC cycles")

    // === Test 2: GC.collect() interleaved with single allocation ===
    var t2Errors = 0
    for (i in 0 until 500) {
        val obj = RapidPayload(i, "rapid-$i")
        GC.collect()
        if (!obj.verify()) t2Errors++
    }
    assertEquals(0, t2Errors, "T2: Allocation between rapid GC failed")

    // === Test 3: Rapid GC with alternating allocation sizes ===
    var t3Errors = 0
    for (i in 0 until 200) {
        val size = if (i % 2 == 0) 10 else 100
        val objs = Array(size) { j -> RapidPayload(i * 1000 + j, "rapid-${i * 1000 + j}") }
        GC.collect()
        for (obj in objs) {
            if (!obj.verify()) t3Errors++
        }
    }
    assertEquals(0, t3Errors, "T3: Alternating allocation rapid GC errors")

    // === Test 4: GC during live object graph mutation ===
    val nodes = Array(100) { RapidPayload(it + 5000, "rapid-${it + 5000}") }
    val refs = arrayOfNulls<RapidPayload>(100)

    for (round in 0 until 100) {
        // Rotate references
        refs[round] = nodes[(round * 7) % 100]
        GC.collect()
        // Verify both source and ref
        assertTrue(nodes[(round * 7) % 100].verify(), "T4: Source node corrupted at round $round")
        assertTrue(refs[round]!!.verify(), "T4: Ref corrupted at round $round")
    }

    // === Test 5: Rapid GC with growing live set ===
    val growing = mutableListOf<RapidPayload>()
    for (i in 0 until 200) {
        growing.add(RapidPayload(i + 8000, "rapid-${i + 8000}"))
        GC.collect()
    }
    // All objects must survive
    for (obj in growing) {
        assertTrue(obj.verify(), "T5: Growing set object ${obj.id} corrupted")
    }

    // === Test 6: Rapid GC with shrinking live set ===
    val shrinking = Array(500) { RapidPayload(it + 9000, "rapid-${it + 9000}") }.toMutableList()
    for (i in 0 until 400) {
        shrinking.removeAt(shrinking.size - 1) // remove last
        GC.collect()
    }
    assertEquals(100, shrinking.size, "T6: Shrinking set size wrong")
    for (obj in shrinking) {
        assertTrue(obj.verify(), "T6: Surviving object ${obj.id} corrupted")
    }

    println("PASS")
}
