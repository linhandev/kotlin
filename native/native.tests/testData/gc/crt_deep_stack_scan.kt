// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests stackmap scanning correctness with deep recursion (5000 frames).
// Risk: stackmap scanning 5000 frames may hit performance/correctness issues;
// LLVM optimizations may place variables in registers, evading stack scanning.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.identityHashCode

class TaggedObj(val tag: Int, val data: String) {
    fun verify(): Boolean = data == "obj-$tag"
}

// Array to collect identity hash codes at each recursion level
val expectedHashes = IntArray(5000)
val collectedObjects = arrayOfNulls<TaggedObj>(5000)
var deepScanErrors = 0

fun deepRecurse(depth: Int, maxDepth: Int) {
    // Each frame holds a unique object that must survive GC
    val obj = TaggedObj(depth, "obj-$depth")
    collectedObjects[depth] = obj
    expectedHashes[depth] = obj.identityHashCode()

    if (depth < maxDepth - 1) {
        deepRecurse(depth + 1, maxDepth)
    } else {
        // At the bottom of recursion — trigger GC
        // All 5000 objects on the stack must be scanned and kept alive
        GC.collect()
        GC.collect()
    }

    // After returning from deeper frames (or after GC at bottom), verify our object
    // is still valid — not collected or corrupted
    if (!obj.verify()) {
        deepScanErrors++
    }
    if (obj.identityHashCode() != expectedHashes[depth]) {
        deepScanErrors++
    }
}

@Test fun testDeepStackScan() {
    val maxDepth = 5000
    deepScanErrors = 0

    // === Test 1: Single deep recursion with GC at bottom ===
    deepRecurse(0, maxDepth)

    // Verify all objects survived GC
    var verifyErrors = 0
    for (i in 0 until maxDepth) {
        val obj = collectedObjects[i]
        if (obj == null) {
            verifyErrors++
            continue
        }
        if (!obj.verify()) verifyErrors++
        if (obj.identityHashCode() != expectedHashes[i]) verifyErrors++
    }

    assertEquals(0, deepScanErrors, "Deep stack scan errors during recursion: $deepScanErrors")
    assertEquals(0, verifyErrors, "Post-recursion verification errors: $verifyErrors")

    // Clear references for next test
    for (i in collectedObjects.indices) collectedObjects[i] = null

    // === Test 2: Multiple GC cycles during deep recursion ===
    deepRecurseMultiGC(0, 2000)
    assertEquals(0, multiGcErrors, "Multi-GC deep recursion errors: $multiGcErrors")

    // === Test 3: Deep recursion with allocation pressure at each level ===
    val result = deepRecurseWithAlloc(0, 1000)
    assertTrue(result, "Deep recursion with allocation failed")

    println("PASS")
}

var multiGcErrors = 0

fun deepRecurseMultiGC(depth: Int, maxDepth: Int) {
    val obj = TaggedObj(depth, "obj-$depth")

    if (depth < maxDepth - 1) {
        // Trigger GC at multiple levels, not just the bottom
        if (depth % 500 == 0) {
            GC.collect()
        }
        deepRecurseMultiGC(depth + 1, maxDepth)
    } else {
        GC.collect()
    }

    if (!obj.verify()) {
        multiGcErrors++
    }
}

fun deepRecurseWithAlloc(depth: Int, maxDepth: Int): Boolean {
    // Each frame allocates additional garbage to create GC pressure
    val obj = TaggedObj(depth, "obj-$depth")
    val garbage = Array(10) { "garbage-$depth-$it" }

    if (depth < maxDepth - 1) {
        if (depth % 200 == 0) GC.collect()
        if (!deepRecurseWithAlloc(depth + 1, maxDepth)) return false
    } else {
        GC.collect()
    }

    // Verify both the tagged object and garbage survived
    if (!obj.verify()) return false
    for (i in garbage.indices) {
        if (garbage[i] != "garbage-$depth-$i") return false
    }
    return true
}
