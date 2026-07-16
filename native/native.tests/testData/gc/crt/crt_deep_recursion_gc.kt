// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests deep recursion with GC interactions at each stack level.
// Risk: Very deep stacks with many GC roots stress stackmap scanning;
// GC at deep stack levels may corrupt frames or miss roots.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.identityHashCode

class StackPayload(val depth: Int, val data: String) {
    fun verify(): Boolean = data == "sp-$depth"
}

@Test fun testDeepRecursionGC() {
    // === Test 1: Deep recursion with GC at multiple levels ===
    // 3000 frames, each holding a live object, GC triggered every 500 frames
    val results = IntArray(3000)

    fun deepWithGC(depth: Int, maxDepth: Int) {
        val payload = StackPayload(depth, "sp-$depth")
        results[depth] = payload.identityHashCode()

        if (depth < maxDepth - 1) {
            if (depth % 500 == 0) GC.collect()
            deepWithGC(depth + 1, maxDepth)
        } else {
            GC.collect()
        }

        // Verify after return from deeper frames
        assertTrue(payload.verify(), "T1: Payload $depth corrupted")
        assertEquals(results[depth], payload.identityHashCode(), "T1: Hash changed at depth $depth")
    }

    deepWithGC(0, 3000)

    // === Test 2: Multiple deep recursion rounds — verify heap consistency ===
    for (round in 0 until 5) {
        fun roundRecurse(depth: Int, maxDepth: Int): Int {
            val obj = StackPayload(depth, "sp-$depth")
            return if (depth < maxDepth - 1) {
                val inner = roundRecurse(depth + 1, maxDepth)
                assertTrue(obj.verify(), "T2: Round $round depth $depth corrupted")
                inner + 1
            } else {
                GC.collect()
                1
            }
        }
        val totalFrames = roundRecurse(0, 2000)
        assertEquals(2000, totalFrames, "T2: Round $round frame count wrong")
    }

    // === Test 3: Deep recursion with heavy allocation at each level ===
    fun allocHeavy(depth: Int, maxDepth: Int): Boolean {
        // Each frame allocates an array — lots of GC pressure
        val localArray = Array(20) { "alloc-$depth-$it" }
        val payload = StackPayload(depth, "sp-$depth")

        if (depth < maxDepth - 1) {
            if (depth % 300 == 0) GC.collect()
            if (!allocHeavy(depth + 1, maxDepth)) return false
        } else {
            GC.collect()
        }

        // Verify both payload and array survived
        if (!payload.verify()) return false
        for (i in localArray.indices) {
            if (localArray[i] != "alloc-$depth-$i") return false
        }
        return true
    }

    assertTrue(allocHeavy(0, 1500), "T3: Deep recursion with heavy allocation failed")

    // === Test 4: Alternating deep/shallow recursion ===
    for (round in 0 until 10) {
        val depth = if (round % 2 == 0) 2000 else 100
        fun alternate(d: Int, max: Int): Int {
            val p = StackPayload(d, "sp-$d")
            return if (d < max - 1) {
                alternate(d + 1, max) + 1
            } else {
                GC.collect()
                assertTrue(p.verify(), "T4: Alternate round $round bottom payload corrupted")
                1
            }
        }
        val frames = alternate(0, depth)
        assertEquals(depth, frames, "T4: Round $round frame count wrong")
    }

    println("PASS")
}
