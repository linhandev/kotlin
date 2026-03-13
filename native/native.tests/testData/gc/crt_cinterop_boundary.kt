// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issue 002 (boundary frame scanning across C/Kotlin boundaries).
// Creates C→Kotlin callback boundaries via cinterop, allocates objects inside callbacks,
// triggers GC during the callback, and verifies objects on Kotlin stack survive.
// This exercises the FrameKind-based boundary traversal for kCExport entry frames.

// MODULE: cinterop
// FILE: boundary_bridge.def
headerFilter = NOTHING
---
#include <stdint.h>

typedef int64_t (*kotlin_callback_t)(int64_t);

// C calls Kotlin callback — creates C→Kotlin boundary frame
static int64_t call_kotlin(kotlin_callback_t callback, int64_t arg) {
    return callback(arg);
}

// Nested: Kotlin→C→Kotlin→C→Kotlin chain
static int64_t nested_call(kotlin_callback_t cb1, kotlin_callback_t cb2, int64_t arg) {
    int64_t r1 = cb1(arg);
    int64_t r2 = cb2(r1);
    return r2;
}

// Deep nesting: C calls Kotlin N times in a loop
static int64_t repeated_call(kotlin_callback_t callback, int64_t arg, int count) {
    int64_t result = arg;
    for (int i = 0; i < count; i++) {
        result = callback(result);
    }
    return result;
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

import boundary_bridge.*
import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.runtime.GC
import kotlinx.cinterop.*

class CIntPayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "payload-$id"
}

const val CINTEROP_ROUNDS = 100
const val CINTEROP_WORKERS = 4

// Global storage for verification inside staticCFunction callbacks
// (staticCFunction cannot capture closures)
val gcCallbackErrors = AtomicInt(0)

// Callback 1: Allocate objects, trigger GC, verify they survive
val gcCallback = staticCFunction<Long, Long> { arg ->
    val objs = Array(20) { i -> CIntPayload(i, "payload-$i") }

    GC.collect()

    var ok = true
    for (obj in objs) {
        if (!obj.verify()) { ok = false; break }
    }

    if (!ok) gcCallbackErrors.incrementAndGet()
    arg + 1
}

// Callback 2: Deeper allocation + verify
val verifyCallback = staticCFunction<Long, Long> { arg ->
    // Allocate a chain of objects
    val chain = Array(50) { i -> CIntPayload(i + 1000, "payload-${i + 1000}") }

    GC.collect()

    var ok = true
    for (c in chain) {
        if (!c.verify()) { ok = false; break }
    }

    if (!ok) gcCallbackErrors.incrementAndGet()
    arg + 1
}

// Test 1: Simple C→Kotlin boundary with GC
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testSimpleBoundary(): Boolean {
    gcCallbackErrors.value = 0

    for (i in 0 until CINTEROP_ROUNDS) {
        // Kotlin objects on stack before crossing into C
        val before = CIntPayload(i, "payload-$i")

        // C calls our Kotlin callback — creates C→Kotlin boundary frame
        val result = call_kotlin(gcCallback, i.toLong())

        // Verify Kotlin stack objects survived the C→Kotlin→GC roundtrip
        if (!before.verify()) return false
        if (result != i.toLong() + 1) return false
    }

    return gcCallbackErrors.value == 0
}

// Test 2: Nested Kotlin→C→Kotlin→C→Kotlin with GC at each level
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testNestedBoundary(): Boolean {
    gcCallbackErrors.value = 0

    for (i in 0 until CINTEROP_ROUNDS) {
        val before = CIntPayload(i, "payload-$i")

        // Creates: Kotlin→C→Kotlin(gcCallback)→C→Kotlin(verifyCallback) boundary chain
        val result = nested_call(gcCallback, verifyCallback, i.toLong())

        if (!before.verify()) return false
        if (result != i.toLong() + 2) return false
    }

    return gcCallbackErrors.value == 0
}

// Test 3: Repeated C→Kotlin callbacks creating many boundary frames
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testRepeatedBoundary(): Boolean {
    gcCallbackErrors.value = 0

    for (i in 0 until CINTEROP_ROUNDS) {
        val before = Array(10) { j -> CIntPayload(j, "payload-$j") }

        // C calls Kotlin callback 10 times in a loop — 10 boundary frame transitions
        val result = repeated_call(gcCallback, 0, 10)

        // Verify all stack objects survived
        for (obj in before) {
            if (!obj.verify()) return false
        }
        if (result != 10L) return false
    }

    return gcCallbackErrors.value == 0
}

// Test 4: Concurrent workers doing C→Kotlin boundary crossings with GC
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testConcurrentBoundary(): Boolean {
    gcCallbackErrors.value = 0
    val readyCount = AtomicInt(0)
    val startSignal = AtomicInt(0)

    val workers = Array(CINTEROP_WORKERS) { Worker.start() }
    val futures = workers.map { worker ->
        worker.execute(TransferMode.SAFE, { Pair(readyCount, startSignal) }) { (ready, start) ->
            ready.incrementAndGet()
            while (start.value == 0) {}

            var ok = true
            for (round in 0 until 50) {
                val objs = Array(10) { j -> CIntPayload(j, "payload-$j") }

                // Each worker does C→Kotlin boundary crossing
                val result = nested_call(gcCallback, verifyCallback, round.toLong())

                for (obj in objs) {
                    if (!obj.verify()) { ok = false; break }
                }
                if (result != round.toLong() + 2) ok = false
                if (!ok) break
            }
            ok
        }
    }

    while (readyCount.value < CINTEROP_WORKERS) {}
    startSignal.value = 1

    // Main thread triggers GC while workers are doing boundary crossings
    for (i in 0 until 20) {
        GC.collect()
    }

    var allOk = true
    for (future in futures) {
        if (!future.result) allOk = false
    }
    for (w in workers) {
        w.requestTermination().result
    }

    return allOk && gcCallbackErrors.value == 0
}

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testCinteropBoundary() {
    assertTrue(testSimpleBoundary(), "Simple C->Kotlin boundary test failed")
    assertTrue(testNestedBoundary(), "Nested boundary test failed")
    assertTrue(testRepeatedBoundary(), "Repeated boundary test failed")
    assertTrue(testConcurrentBoundary(), "Concurrent boundary test failed")

    println("PASS")
}
