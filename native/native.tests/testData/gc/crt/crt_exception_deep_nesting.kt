// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests exception handling correctness under deep nesting and GC pressure.
// Risk: Deep try-catch-finally chains create many frames; exception unwinding
// must correctly restore frame pointers while GC scans the stack.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.identityHashCode

class ExPayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "ex-$id"
}

@Test fun testExceptionDeepNesting() {
    // === Test 1: Deep try-catch nesting (100 levels) ===
    // Deep recursion: exception thrown at bottom, re-thrown up through 100 levels
    // Each catch block verifies its local payload survived the unwind
    fun deepTryCatch(depth: Int, maxDepth: Int) {
        val payload = ExPayload(depth, "ex-$depth")
        try {
            if (depth < maxDepth - 1) {
                deepTryCatch(depth + 1, maxDepth)
            } else {
                GC.collect()
                throw RuntimeException("bottom-$depth")
            }
        } catch (e: RuntimeException) {
            assertTrue(payload.verify(), "T1: Payload $depth corrupted during unwind")
            throw e // always re-throw
        }
    }

    var deepResult = ""
    try {
        deepTryCatch(0, 100)
    } catch (e: RuntimeException) {
        deepResult = e.message ?: "null"
    }
    assertEquals("bottom-99", deepResult, "T1: Deep try-catch result mismatch")

    // === Test 2: Try-finally chain — objects survive finally blocks + GC ===
    val finallyOrder = mutableListOf<Int>()
    fun deepFinally(depth: Int, maxDepth: Int, order: MutableList<Int>) {
        val payload = ExPayload(depth, "ex-$depth")
        try {
            if (depth < maxDepth - 1) {
                deepFinally(depth + 1, maxDepth, order)
            } else {
                GC.collect()
                throw RuntimeException("finally-bottom")
            }
        } finally {
            assertTrue(payload.verify(), "T2: Finally payload $depth corrupted")
            order.add(depth)
            if (depth % 10 == 0) GC.collect()
        }
    }

    try {
        deepFinally(0, 50, finallyOrder)
    } catch (e: RuntimeException) {
        // expected
    }

    // Finally blocks should execute in reverse order (deepest first)
    assertEquals(50, finallyOrder.size, "T2: Not all finally blocks executed")
    for (i in finallyOrder.indices) {
        assertEquals(49 - i, finallyOrder[i], "T2: Finally order wrong at position $i")
    }

    // === Test 3: Catch + allocate + rethrow — GC during exception propagation ===
    var rethrowErrors = 0
    fun catchAllocRethrow(depth: Int, maxDepth: Int): ExPayload {
        val localObj = ExPayload(depth, "ex-$depth")
        return try {
            if (depth < maxDepth - 1) {
                catchAllocRethrow(depth + 1, maxDepth)
            } else {
                throw RuntimeException("rethrow-test")
            }
        } catch (e: RuntimeException) {
            // Allocate during catch to trigger GC
            val tempArr = Array(100) { "temp-$depth-$it" }
            GC.collect()
            // Verify local object survived
            if (!localObj.verify()) rethrowErrors++
            if (depth > 0) throw e
            localObj
        }
    }

    try {
        catchAllocRethrow(0, 30)
    } catch (e: RuntimeException) {
        // expected
    }
    assertEquals(0, rethrowErrors, "T3: Objects corrupted during catch+alloc+rethrow")

    // === Test 4: Multiple exception types at different depths ===
    class CustomException1(msg: String) : Exception(msg)
    class CustomException2(msg: String) : Exception(msg)

    var typeErrors = 0
    fun multiTypeException(depth: Int) {
        val payload = ExPayload(depth, "ex-$depth")
        try {
            when {
                depth > 20 -> throw CustomException1("type1-$depth")
                depth > 10 -> throw CustomException2("type2-$depth")
                depth > 0 -> multiTypeException(depth + 1)
                else -> {
                    multiTypeException(depth + 1)
                    return
                }
            }
        } catch (e: CustomException1) {
            GC.collect()
            if (!payload.verify()) typeErrors++
            throw e
        } catch (e: CustomException2) {
            GC.collect()
            if (!payload.verify()) typeErrors++
            throw e
        }
    }

    try { multiTypeException(0) } catch (e: Exception) { /* expected */ }
    assertEquals(0, typeErrors, "T4: Multi-type exception payload corrupted")

    // === Test 5: Exception + identity hash stability ===
    val obj = ExPayload(888, "ex-888")
    val hashBefore = obj.identityHashCode()

    for (round in 0 until 20) {
        try {
            GC.collect()
            throw RuntimeException("hash-test-$round")
        } catch (e: RuntimeException) {
            GC.collect()
        }
    }

    assertEquals(hashBefore, obj.identityHashCode(), "T5: Hash changed after exception+GC cycles")
    assertTrue(obj.verify(), "T5: Object corrupted after exception+GC cycles")

    println("PASS")
}
