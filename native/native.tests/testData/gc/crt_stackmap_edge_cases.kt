// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Stackmap coverage gap tests targeting HIGH-risk issues from the audit:
//   A1: Derived Pointer Index Desync — mixed base-only + base+derived at same callsite
//   A2: RegTable Unused — values likely held in registers across GC safepoint
//   A8: FP Chain Walk No Guard — deep K↔C nesting stressing FP chain traversal
//
// If A1 is triggered, derivedPtrIdx in CompressedStackMap.hpp goes out of sync,
// reading garbage from DerivedPtrTable → SIGSEGV or object corruption.

// MODULE: cinterop
// FILE: stackmap_bridge.def
headerFilter = NOTHING
---
#include <stdint.h>

typedef int64_t (*kotlin_cb_t)(int64_t);

static int64_t c_call(kotlin_cb_t cb, int64_t arg) {
    return cb(arg);
}

// Deep C-side recursion for FP chain stress (A8)
static int64_t c_recurse(kotlin_cb_t cb, int64_t depth, int64_t arg) {
    if (depth <= 0) return cb(arg);
    return c_recurse(cb, depth - 1, arg + 1);
}

// Double callback — both create K2N boundary frames on the same C call stack
static int64_t c_double(kotlin_cb_t cb1, kotlin_cb_t cb2, int64_t arg) {
    int64_t r1 = cb1(arg);
    int64_t r2 = cb2(arg + r1);
    return r1 + r2;
}

// Triple nesting: C calls K which calls C which calls K
static int64_t c_nest(kotlin_cb_t outer, kotlin_cb_t inner, int64_t arg) {
    // outer will call c_call(inner, ...) inside Kotlin
    return outer(arg);
}

// MODULE: main(cinterop)
// FILE: main.kt

@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

import stackmap_bridge.*
import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlinx.cinterop.*

val errors = AtomicInt(0)

// ============================================================
// Payload classes
// ============================================================
class Payload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "p-$id"
}

class Wide(
    val a: Long, val b: Long, val c: Long, val d: Long,
    val e: Long, val f: Long, val g: Long, val h: Long
) {
    fun verify(): Boolean =
        a == 1L && b == 2L && c == 3L && d == 4L &&
        e == 5L && f == 6L && g == 7L && h == 8L
    fun sum(): Long = a + b + c + d + e + f + g + h
}

class Nested(val child: Nested?, val value: Int) {
    fun depth(): Int = if (child == null) 1 else 1 + child.depth()
    fun verify(): Boolean {
        if (child == null) return value >= 0
        return value > child.value && child.verify()
    }
}

fun makeChain(n: Int): Nested {
    var node = Nested(null, 0)
    for (i in 1 until n) {
        node = Nested(node, i)
    }
    return node
}

// ============================================================
// A1 Test 1: Simple — 1 base-only + 1 base+derived
// ============================================================
// At the GC.collect() safepoint:
//   'direct' is a base-only slot (no derived pointer)
//   'arr[42]' requires derived pointer (base=arr, derived=element offset)
// If derivedPtrIdx increments for 'direct' too, the derived entry for arr
// will read from wrong index → wrong offset → corruption
val cbA1Simple = staticCFunction<Long, Long> { _ ->
    val direct = Payload(1, "p-1")              // base-only
    val arr = Array(100) { Payload(it, "p-$it") }  // base+derived (element access)
    val elem = arr[42]                          // derived pointer from arr
    GC.collect()                                // safepoint with mixed base types
    if (!direct.verify()) errors.incrementAndGet()
    if (!elem.verify()) errors.incrementAndGet()
    if (elem.id != 42) errors.incrementAndGet()
    0L
}

// ============================================================
// A1 Test 2: Interleaved — 5 base-only + 3 base+derived
// ============================================================
// More complex mix to stress derivedPtrIdx tracking
val cbA1Interleaved = staticCFunction<Long, Long> { _ ->
    // 5 base-only references
    val p1 = Payload(1, "p-1")
    val p2 = Payload(2, "p-2")
    val p3 = Payload(3, "p-3")
    val p4 = Payload(4, "p-4")
    val p5 = Payload(5, "p-5")

    // 3 base+derived references (array element access)
    val arr1 = Array(50) { it.toLong() }
    val arr2 = Array(80) { "s-$it" }
    val arr3 = Array(30) { Payload(it + 100, "p-${it + 100}") }

    // Access derived pointers to ensure they're live
    val v1 = arr1[25]
    val v2 = arr2[60]
    val v3 = arr3[15]

    GC.collect()  // safepoint: 5 base-only + 3 base+derived on stack

    // Verify all references survived GC correctly
    var ok = true
    if (!p1.verify() || !p2.verify() || !p3.verify() || !p4.verify() || !p5.verify()) ok = false
    if (v1 != 25L) ok = false
    if (v2 != "s-60") ok = false
    if (!v3.verify() || v3.id != 115) ok = false
    if (!ok) errors.incrementAndGet()
    0L
}

// ============================================================
// A1 Test 3: Mixed types — IntArray, LongArray, Array<String>, Array<Obj>
// ============================================================
val cbA1MixedTypes = staticCFunction<Long, Long> { _ ->
    val obj = Payload(99, "p-99")                // base-only
    val wide = Wide(1, 2, 3, 4, 5, 6, 7, 8)     // base-only
    val chain = makeChain(5)                     // base-only (deep object)

    val intArr = IntArray(100) { it * 3 }         // primitive array (base+derived on element access)
    val longArr = LongArray(200) { it.toLong() }  // primitive array
    val strArr = Array(50) { "str-$it" }          // object array
    val objArr = Array(40) { Payload(it, "p-$it") } // object array

    // Force derived pointer creation by reading elements
    val i1 = intArr[77]
    val l1 = longArr[150]
    val s1 = strArr[33]
    val o1 = objArr[20]

    GC.collect()

    var ok = true
    if (!obj.verify()) ok = false
    if (!wide.verify()) ok = false
    if (!chain.verify() || chain.depth() != 5) ok = false
    if (i1 != 77 * 3) ok = false
    if (l1 != 150L) ok = false
    if (s1 != "str-33") ok = false
    if (!o1.verify() || o1.id != 20) ok = false
    if (!ok) errors.incrementAndGet()
    0L
}

// ============================================================
// A1 Test 4: Deep nesting — arr[i][j].field.subfield
// ============================================================
class Inner(val value: Long, val tag: String) {
    fun verify(): Boolean = tag == "t-$value"
}
class Middle(val inner: Inner, val extra: Long) {
    fun verify(): Boolean = inner.verify() && extra == inner.value * 2
}

val cbA1DeepNesting = staticCFunction<Long, Long> { _ ->
    val directRef = Payload(42, "p-42")  // base-only

    // 2D array: arr[i][j] = Middle(Inner(...), ...)
    val arr = Array(10) { i ->
        Array(10) { j ->
            val v = (i * 10 + j).toLong()
            Middle(Inner(v, "t-$v"), v * 2)
        }
    }

    // Access deep: arr[3][7].inner.value — multiple derived levels
    val mid = arr[3][7]
    val innerVal = mid.inner.value
    val innerTag = mid.inner.tag

    GC.collect()  // safepoint: directRef(base-only), arr+mid+inner (derived chain)

    var ok = true
    if (!directRef.verify()) ok = false
    if (innerVal != 37L) ok = false
    if (innerTag != "t-37") ok = false
    if (!mid.verify()) ok = false

    // Verify entire array survived
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (!arr[i][j].verify()) { ok = false; break }
        }
        if (!ok) break
    }
    if (!ok) errors.incrementAndGet()
    0L
}

// ============================================================
// A1 Test 5: Many GC cycles with mixed roots accumulating
// ============================================================
val cbA1Repeated = staticCFunction<Long, Long> { _ ->
    val stableRefs = Array(10) { Payload(it, "p-$it") }  // base-only array
    var ok = true
    for (round in 0 until 30) {
        val tempArr = Array(50) { it.toLong() + round }  // base+derived
        val tempElem = tempArr[round % 50]
        val tempDirect = Payload(round + 100, "p-${round + 100}")  // base-only

        GC.collect()

        // Verify stable refs
        for (i in stableRefs.indices) {
            if (!stableRefs[i].verify()) { ok = false; break }
        }
        // Verify temp derived
        if (tempElem != (round % 50).toLong() + round) ok = false
        // Verify temp base-only
        if (!tempDirect.verify()) ok = false

        if (!ok) break
    }
    if (!ok) errors.incrementAndGet()
    0L
}

// ============================================================
// A2 Test 1: Value likely in register — small function, few locals
// ============================================================
// LLVM is more likely to keep a value in register when:
// - Function is small, few live values → low spill pressure
// - Value is used immediately after safepoint
// If GC misses a register-held root, the object may be collected.
val cbA2Register = staticCFunction<Long, Long> { _ ->
    val obj = Payload(77, "p-77")
    // Use identityHashCode to pin the object identity before GC
    val hash = obj.hashCode()
    GC.collect()
    // After GC, if obj was only in a register and GC missed it,
    // obj might be collected or corrupted
    if (!obj.verify()) errors.incrementAndGet()
    hash.toLong()
}

// ============================================================
// A2 Test 2: Loop with object used repeatedly — register pressure
// ============================================================
val cbA2Loop = staticCFunction<Long, Long> { _ ->
    val p = Payload(55, "p-55")
    var sum = 0
    // Hot loop — LLVM may keep p in register across iterations
    for (i in 0 until 100) {
        sum += p.id  // use p.id repeatedly — keeps p live in register
        if (i == 50) GC.collect()  // GC in middle of loop
    }
    if (!p.verify()) errors.incrementAndGet()
    if (sum != 55 * 100) errors.incrementAndGet()
    sum.toLong()
}

// ============================================================
// A2 Test 3: Multiple values competing for registers
// ============================================================
val cbA2MultiReg = staticCFunction<Long, Long> { _ ->
    // ARM64 has many registers — fill them with GC roots
    val a = Payload(1, "p-1")
    val b = Payload(2, "p-2")
    val c = Payload(3, "p-3")
    val d = Payload(4, "p-4")
    val e = Payload(5, "p-5")
    val f = Payload(6, "p-6")
    val g = Payload(7, "p-7")
    val h = Payload(8, "p-8")

    // Use all of them to keep them live
    val sum = a.id + b.id + c.id + d.id + e.id + f.id + g.id + h.id
    GC.collect()
    // After GC, any register-only root would be corrupted
    val sum2 = a.id + b.id + c.id + d.id + e.id + f.id + g.id + h.id
    if (sum != sum2) errors.incrementAndGet()
    if (!a.verify() || !b.verify() || !c.verify() || !d.verify()) errors.incrementAndGet()
    if (!e.verify() || !f.verify() || !g.verify() || !h.verify()) errors.incrementAndGet()
    sum.toLong()
}

// ============================================================
// A2 Test 4: Inline function — value crosses no call boundary
// ============================================================
// Inline functions don't create call frames, so LLVM has more freedom
// to keep values in registers
@Suppress("NOTHING_TO_INLINE")
inline fun verifyInline(p: Payload): Boolean {
    return p.verify() && p.id > 0
}

val cbA2Inline = staticCFunction<Long, Long> { _ ->
    val p = Payload(33, "p-33")
    GC.collect()
    if (!verifyInline(p)) errors.incrementAndGet()
    p.id.toLong()
}

// ============================================================
// A8 Test 1: Deep K→C→K→C→K nesting (FP chain stress)
// ============================================================
// Each K→C and C→K transition adds frames to the FP chain.
// Deep nesting stresses the FP chain walker in KNRootVisitor.
// If FP chain is corrupted or walk has no guard, this hangs or crashes.

val a8Depth = AtomicInt(0)

// Each level: K allocates objects → calls C → C calls K callback → GC at leaf
// This creates a K→C→K→C→...→K chain. We use c_recurse for C-side depth
// and the callback allocates + triggers GC at the bottom.
val cbA8Deep = staticCFunction<Long, Long> { arg ->
    val level = a8Depth.addAndGet(1)
    val localData = Array(20) { Payload(it + level * 100, "p-${it + level * 100}") }

    GC.collect()  // GC with deep FP chain above us

    var ok = true
    for (p in localData) {
        if (!p.verify()) { ok = false; break }
    }
    if (!ok) errors.incrementAndGet()
    arg
}

// ============================================================
// A8 Test 2: Very deep C-side recursion then K callback
// ============================================================
val cbA8LeafAlloc = staticCFunction<Long, Long> { arg ->
    // Allocate at the bottom of deep C stack
    val data = Array(50) { Wide(1, 2, 3, 4, 5, 6, 7, 8) }
    GC.collect()
    var ok = true
    for (w in data) {
        if (!w.verify()) { ok = false; break }
    }
    if (!ok) errors.incrementAndGet()
    data.sumOf { it.sum() }
}

// ============================================================
// A8 Test 3: Alternating K→C→K with objects at each level
// ============================================================
// Inner callback: called by c_nest → creates objects + GC
var nestInnerCb: CPointer<CFunction<(Long) -> Long>>? = null

val cbA8NestInner = staticCFunction<Long, Long> { arg ->
    val inner = Array(30) { "nest-inner-$it-$arg" }
    GC.collect()
    for (i in inner.indices) {
        if (inner[i] != "nest-inner-$i-$arg") {
            errors.incrementAndGet()
            break
        }
    }
    arg * 2
}

val cbA8NestOuter = staticCFunction<Long, Long> { arg ->
    val outer = Array(30) { "nest-outer-$it-$arg" }
    // Outer calls C which calls inner — creating K→C→K→C→K chain
    val innerResult = c_call(cbA8NestInner, arg)
    GC.collect()
    for (i in outer.indices) {
        if (outer[i] != "nest-outer-$i-$arg") {
            errors.incrementAndGet()
            break
        }
    }
    innerResult + arg
}

// ============================================================
// Combined: A1 + A8 — mixed roots at deep nesting
// ============================================================
// Uses c_recurse for C-side depth, then callback has mixed base/derived roots
val cbCombined = staticCFunction<Long, Long> { arg ->
    // Mix base-only and base+derived at deep C nesting level
    val direct1 = Payload(1, "p-1")    // base-only
    val direct2 = Wide(1, 2, 3, 4, 5, 6, 7, 8)  // base-only
    val arr = Array(60) { it.toLong() * 7 }  // base+derived
    val idx = arg.toInt().coerceIn(0, 59)
    val elem = arr[idx]

    GC.collect()

    var ok = true
    if (!direct1.verify()) ok = false
    if (!direct2.verify()) ok = false
    if (elem != idx.toLong() * 7) ok = false
    // Verify array integrity
    for (i in arr.indices) {
        if (arr[i] != i.toLong() * 7) { ok = false; break }
    }
    if (!ok) errors.incrementAndGet()
    elem
}

// ============================================================
// A1+A2 Combined: many short-lived objects + register pressure + mixed roots
// ============================================================
val cbA1A2Combined = staticCFunction<Long, Long> { _ ->
    // Create conditions for both A1 (mixed base/derived) and A2 (register roots)
    val regCandidate = Payload(88, "p-88")  // may stay in register
    val arr = Array(100) { it.toLong() }     // base+derived

    // Hot loop using regCandidate — pressure to keep in register
    var sum = 0
    for (i in 0 until 50) {
        sum += regCandidate.id
    }

    val elem = arr[42]  // derived pointer

    GC.collect()  // safepoint: regCandidate (possibly register), arr+elem (derived)

    if (!regCandidate.verify()) errors.incrementAndGet()
    if (sum != 88 * 50) errors.incrementAndGet()
    if (elem != 42L) errors.incrementAndGet()
    sum.toLong()
}

// ============================================================
// Runner
// ============================================================
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testCrt_stackmap_edge_cases() {
    errors.value = 0

    // --- A1: Derived Pointer Index Desync ---
    println("A1.1: Simple base-only + base+derived mix")
    for (r in 0 until 20) c_call(cbA1Simple, r.toLong())
    assertEquals(0, errors.value, "A1.1: Simple mixed base/derived failed")

    println("A1.2: Interleaved 5 base-only + 3 base+derived")
    for (r in 0 until 20) c_call(cbA1Interleaved, r.toLong())
    assertEquals(0, errors.value, "A1.2: Interleaved mixed roots failed")

    println("A1.3: Mixed array types (int/long/string/obj)")
    for (r in 0 until 15) c_call(cbA1MixedTypes, r.toLong())
    assertEquals(0, errors.value, "A1.3: Mixed type arrays failed")

    println("A1.4: Deep nesting arr[i][j].field.subfield")
    for (r in 0 until 10) c_call(cbA1DeepNesting, r.toLong())
    assertEquals(0, errors.value, "A1.4: Deep nested derived pointers failed")

    println("A1.5: Repeated GC with accumulating mixed roots")
    for (r in 0 until 5) c_call(cbA1Repeated, r.toLong())
    assertEquals(0, errors.value, "A1.5: Repeated GC mixed roots failed")

    // --- A2: RegTable Unused (register-held roots) ---
    println("A2.1: Single value likely in register")
    for (r in 0 until 50) c_call(cbA2Register, r.toLong())
    assertEquals(0, errors.value, "A2.1: Register-held root failed")

    println("A2.2: Loop with register-held object")
    for (r in 0 until 20) c_call(cbA2Loop, r.toLong())
    assertEquals(0, errors.value, "A2.2: Loop register root failed")

    println("A2.3: Multiple register candidates")
    for (r in 0 until 20) c_call(cbA2MultiReg, r.toLong())
    assertEquals(0, errors.value, "A2.3: Multi-register roots failed")

    println("A2.4: Inline function register root")
    for (r in 0 until 30) c_call(cbA2Inline, r.toLong())
    assertEquals(0, errors.value, "A2.4: Inline register root failed")

    // --- A8: FP Chain Walk No Guard ---
    println("A8.1: Deep C recursion with K callback (10 levels)")
    a8Depth.value = 0
    c_recurse(cbA8Deep, 10, 0L)  // 10 levels of C recursion, each calls K callback
    assertEquals(0, errors.value, "A8.1: Deep nesting FP chain failed")

    println("A8.2: Very deep C recursion (50 levels) then K callback")
    c_recurse(cbA8LeafAlloc, 50, 0L)
    assertEquals(0, errors.value, "A8.2: Deep C recursion + K callback failed")

    println("A8.3: Alternating K→C→K→C→K nesting")
    for (r in 0 until 10) c_nest(cbA8NestOuter, cbA8NestInner, r.toLong())
    assertEquals(0, errors.value, "A8.3: Alternating nest failed")

    // --- Combined: A1 + A8 ---
    println("Combined: Mixed roots at deep K↔C nesting (5 levels)")
    c_recurse(cbCombined, 5, 0L)  // 5 levels C recursion, callback has mixed roots
    assertEquals(0, errors.value, "Combined A1+A8: Mixed roots + deep nesting failed")

    // --- Combined: A1 + A2 ---
    println("Combined: Register pressure + mixed base/derived")
    for (r in 0 until 20) c_call(cbA1A2Combined, r.toLong())
    assertEquals(0, errors.value, "Combined A1+A2: Register + mixed derived failed")

    println("PASS")
}
