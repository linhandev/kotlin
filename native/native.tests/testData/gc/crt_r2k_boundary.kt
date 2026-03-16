// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.native.internal.InternalForKotlinNative,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt -friend-modules=kotlin-native/dist/klib/common/stdlib
// NOTE: -friend-modules uses a relative path (resolved from project root). StandardTestCaseGroupProvider does not
// support $KOTLIN_NATIVE_DISTRIBUTION$ substitution. Gradle sets cwd to project root, so this works for ./gradlew runs.

// Regression test for CRT GC issue 002 (kRuntimeToKotlin entry boundary frame scanning).
// Combines cinterop (C wrapper) + @ExportForCppRuntime (genuine kRuntimeToKotlin frame).
// Flow: Kotlin -> C wrapper (creates Exit frame) -> @ExportForCppRuntime function (creates Entry frame)
// This creates a valid Exit/Entry pair on the frame stack.
// Inside the @ExportForCppRuntime function, objects are allocated and GC is triggered.
// Without the entry boundary scan fix, objects on the entry boundary frame may be missed.

// MODULE: cinterop
// FILE: r2k_bridge.def
headerFilter = NOTHING
---
#include <stdint.h>

// @ExportForCppRuntime functions (defined in Kotlin main module, resolved at link time)
extern int8_t TestR2K_allocateAndGC(void);
extern int8_t TestR2K_nestedGC(void);

// Thread state management (from KN runtime Memory.cpp)
// @ExportForCppRuntime expects RUNNABLE state; C code runs in NATIVE state.
extern void Kotlin_mm_switchThreadStateRunnable(void);
extern void Kotlin_mm_switchThreadStateNative(void);

// C wrapper: switches to RUNNABLE, calls @ExportForCppRuntime function, switches back.
// Frame pairing: Kotlin->C creates Exit(kK2X), @ExportForCppRuntime creates Entry(kRuntimeToKotlin).
static int8_t call_r2k_allocate(void) {
    Kotlin_mm_switchThreadStateRunnable();
    int8_t result = TestR2K_allocateAndGC();
    Kotlin_mm_switchThreadStateNative();
    return result;
}

static int8_t call_r2k_nested(void) {
    Kotlin_mm_switchThreadStateRunnable();
    int8_t result = TestR2K_nestedGC();
    Kotlin_mm_switchThreadStateNative();
    return result;
}

// Repeated calls to stress the boundary
static int call_r2k_repeated(int count) {
    int errors = 0;
    for (int i = 0; i < count; i++) {
        if (!call_r2k_allocate()) errors++;
        if (!call_r2k_nested()) errors++;
    }
    return errors;
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

import r2k_bridge.*
import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.internal.ExportForCppRuntime
import kotlin.native.runtime.GC

class R2KPayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "r2k-$id"
}

const val R2K_ROUNDS = 200
const val R2K_WORKERS = 4

// Genuine kRuntimeToKotlin frame: compiler inserts SaveStackFrameR2KExportForCppRuntime.
// Called from C code (via call_r2k_allocate), so frame pairing is correct:
// Exit(kK2X) from Kotlin->C + Entry(kRuntimeToKotlin) from C->this function.
@ExportForCppRuntime("TestR2K_allocateAndGC")
fun r2kAllocateAndGC(): Boolean {
    // Objects allocated here sit on the kRuntimeToKotlin entry boundary frame.
    // Without the fix (IsKotlinFrame(entryKind) check), GC skips scanning this frame.
    val obj1 = R2KPayload(1, "r2k-1")
    val obj2 = R2KPayload(2, "r2k-2")
    val arr = Array(50) { i -> R2KPayload(i + 10, "r2k-${i + 10}") }

    GC.collect()

    if (!obj1.verify()) return false
    if (!obj2.verify()) return false
    for (elem in arr) {
        if (!elem.verify()) return false
    }
    return true
}

@ExportForCppRuntime("TestR2K_nestedGC")
fun r2kNestedGC(): Boolean {
    val locals = Array(50) { i -> R2KPayload(i + 200, "r2k-${i + 200}") }

    // Call non-exported function that triggers GC.
    // Our locals are on the entry boundary frame.
    triggerGCWithPressure()

    for (elem in locals) {
        if (!elem.verify()) return false
    }
    return true
}

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun triggerGCWithPressure() {
    val temps = Array(200) { i -> R2KPayload(i + 500, "r2k-${i + 500}") }
    GC.collect()
    for (t in temps) t.verify()
}

// Test 1: Simple R2K boundary via C wrapper
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testSimpleR2K(): Boolean {
    for (i in 0 until R2K_ROUNDS) {
        // Kotlin -> C -> @ExportForCppRuntime Kotlin (genuine kRuntimeToKotlin frame)
        if (call_r2k_allocate().toInt() == 0) return false
    }
    return true
}

// Test 2: Nested R2K via C wrapper
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testNestedR2K(): Boolean {
    for (i in 0 until R2K_ROUNDS) {
        if (call_r2k_nested().toInt() == 0) return false
    }
    return true
}

// Test 3: Repeated calls via C loop
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testRepeatedR2K(): Boolean {
    val errors = call_r2k_repeated(R2K_ROUNDS)
    return errors == 0
}

// Test 4: Concurrent workers doing R2K boundary crossings
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testConcurrentR2K(): Boolean {
    val readyCount = AtomicInt(0)
    val startSignal = AtomicInt(0)

    val workers = Array(R2K_WORKERS) { Worker.start() }
    val futures = workers.map { worker ->
        worker.execute(TransferMode.SAFE, { Pair(readyCount, startSignal) }) { (ready, start) ->
            ready.incrementAndGet()
            while (start.value == 0) {}

            var ok = true
            for (i in 0 until 50) {
                if (call_r2k_allocate().toInt() == 0) { ok = false; break }
                if (call_r2k_nested().toInt() == 0) { ok = false; break }
            }
            ok
        }
    }

    while (readyCount.value < R2K_WORKERS) {}
    startSignal.value = 1

    for (i in 0 until 30) {
        GC.collect()
    }

    var allOk = true
    for (future in futures) {
        if (!future.result) allOk = false
    }
    for (w in workers) {
        w.requestTermination().result
    }

    return allOk
}

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testR2KBoundary() {
    assertTrue(testSimpleR2K(), "Simple R2K boundary test failed")
    assertTrue(testNestedR2K(), "Nested R2K boundary test failed")
    assertTrue(testRepeatedR2K(), "Repeated R2K boundary test failed")
    assertTrue(testConcurrentR2K(), "Concurrent R2K boundary test failed")

    println("PASS")
}
