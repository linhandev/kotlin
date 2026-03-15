// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests lambda/closure capture correctness under GC.
// Risk: Lambda objects capture references to enclosing scope variables;
// after GC moves captured objects, lambda invocation must still see correct values.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC

class CapturedObj(val id: Int, val data: String) {
    fun verify(): Boolean = data == "cap-$id"
}

@Test fun testLambdaCaptureGC() {
    // === Test 1: Simple lambda capture survives GC ===
    val obj1 = CapturedObj(1, "cap-1")
    val lambda1: () -> String = { obj1.data }
    GC.collect()
    assertEquals("cap-1", lambda1(), "T1: Simple lambda capture broken after GC")

    // === Test 2: Multiple captures in one lambda ===
    val a = CapturedObj(10, "cap-10")
    val b = CapturedObj(20, "cap-20")
    val c = "constant-string"
    val multi: () -> String = { "${a.data}-${b.data}-$c" }
    GC.collect()
    assertEquals("cap-10-cap-20-constant-string", multi(), "T2: Multi-capture broken")

    // === Test 3: Lambda stored in array, invoked after GC ===
    val lambdas = Array<() -> String>(500) { i ->
        val captured = CapturedObj(i, "cap-$i")
        val fn: () -> String = { captured.data }
        fn
    }
    GC.collect()
    GC.collect()
    for (i in lambdas.indices) {
        assertEquals("cap-$i", lambdas[i](), "T3: Stored lambda $i broken after GC")
    }

    // === Test 4: Higher-order function with captured state ===
    fun makeAdder(base: CapturedObj): (Int) -> String {
        return { n: Int -> "${base.data}-plus-$n" }
    }

    val adders = Array(100) { makeAdder(CapturedObj(it, "cap-$it")) }
    GC.collect()
    for (i in adders.indices) {
        assertEquals("cap-$i-plus-42", adders[i](42), "T4: Higher-order function $i broken")
    }

    // === Test 5: Lambda capturing mutable var via wrapper ===
    var counter = 0
    val mutating = Array<() -> Boolean>(100) { i ->
        val snapshot = CapturedObj(i, "cap-$i")
        val fn: () -> Boolean = {
            counter++
            snapshot.verify()
        }
        fn
    }
    GC.collect()
    for (fn in mutating) {
        assertTrue(fn(), "T5: Mutable capture lambda broken")
    }
    assertEquals(100, counter, "T5: Counter wrong")

    // === Test 6: Lambda with receiver ===
    fun CapturedObj.makeDescriber(): () -> String {
        val self = this
        return { "Describing: ${self.data} (id=${self.id})" }
    }

    val describers = Array(200) { i ->
        CapturedObj(i, "cap-$i").makeDescriber()
    }
    GC.collect()
    for (i in describers.indices) {
        val result = describers[i]()
        assertTrue(result.contains("cap-$i"), "T6: Lambda with receiver $i broken: $result")
    }

    // === Test 7: Nested closure chain (10 levels) ===
    fun buildChain(depth: Int, maxDepth: Int): () -> Int {
        val obj = CapturedObj(depth, "cap-$depth")
        if (depth < maxDepth - 1) {
            val inner = buildChain(depth + 1, maxDepth)
            val fn: () -> Int = {
                assertTrue(obj.verify(), "T7: Chain depth $depth broken")
                inner() + 1
            }
            return fn
        } else {
            val fn: () -> Int = {
                assertTrue(obj.verify(), "T7: Chain leaf broken")
                1
            }
            return fn
        }
    }

    val chain = buildChain(0, 10)
    GC.collect()
    GC.collect()
    assertEquals(10, chain(), "T7: Closure chain result wrong")

    println("PASS")
}
