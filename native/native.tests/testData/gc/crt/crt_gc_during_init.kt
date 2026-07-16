// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests GC triggering during class/object initialization.
// Risk: Static initializers run in a partially-constructed state;
// GC during init may see half-initialized objects or cause deadlocks
// if init locks conflict with GC STW.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.identityHashCode

// Object with expensive initialization that triggers GC
object HeavyInit {
    val data: Array<String>
    val hash: Int
    init {
        // Allocate heavily during init to trigger GC
        val temp = Array(10000) { "init-$it" }
        GC.collect()
        data = Array(100) { "heavy-$it" }
        GC.collect()
        hash = data[0].hashCode()
    }
}

// Chain of objects that reference each other during init
object ChainA {
    val value = "chainA"
    val ref: String
    init {
        GC.collect()
        ref = ChainB.value
    }
}

object ChainB {
    val value = "chainB"
    val data: Array<String>
    init {
        data = Array(1000) { "b-init-$it" }
        GC.collect()
    }
}

// Object with lazy properties that allocate during first access
class LazyHolder(val id: Int) {
    val expensiveField: Array<String> by lazy {
        Array(500) { "lazy-$id-$it" }
    }

    val anotherLazy: Int by lazy {
        id * 42
    }
}

// Class with complex init block
class ComplexInit(val id: Int) {
    val children: Array<ComplexInit?>
    val data: String

    init {
        data = "complex-$id"
        children = if (id > 0) {
            GC.collect()
            Array(3) { i ->
                if (id > 1) ComplexInit(id - 1) else null
            }
        } else {
            emptyArray()
        }
    }

    fun verify(): Boolean {
        if (data != "complex-$id") return false
        for (child in children) {
            if (child != null && !child.verify()) return false
        }
        return true
    }
}

@Test fun testGCDuringInit() {
    // === Test 1: Singleton object init with GC ===
    val d = HeavyInit.data
    assertEquals(100, d.size, "T1: HeavyInit data size wrong")
    assertEquals("heavy-0", d[0], "T1: HeavyInit data[0] wrong")
    assertEquals("heavy-99", d[99], "T1: HeavyInit data[99] wrong")

    // Access again after GC — singleton must be stable
    GC.collect()
    assertSame(d, HeavyInit.data, "T1: HeavyInit.data reference changed after GC")

    // === Test 2: Object init chain ===
    assertEquals("chainB", ChainA.ref, "T2: ChainA.ref wrong")
    assertEquals(1000, ChainB.data.size, "T2: ChainB.data size wrong")
    GC.collect()
    assertEquals("chainB", ChainA.ref, "T2: ChainA.ref changed after GC")

    // === Test 3: Lazy initialization with GC ===
    val holders = Array(50) { LazyHolder(it) }
    GC.collect()

    // Trigger lazy init in sequence — no concurrent GC during lazy eval
    for (h in holders) {
        val field = h.expensiveField
        assertEquals(500, field.size, "T3: Lazy field size wrong for id=${h.id}")
        assertEquals("lazy-${h.id}-0", field[0], "T3: Lazy field[0] wrong for id=${h.id}")
    }

    // Verify anotherLazy separately (simpler lazy, less GC pressure)
    for (h in holders) {
        val expected = h.id * 42
        val actual = h.anotherLazy
        assertEquals(expected, actual, "T3: anotherLazy wrong for id=${h.id}. Expected <$expected>, actual <$actual>.")
    }

    GC.collect()

    // Verify lazy fields are stable
    for (h in holders) {
        assertEquals(500, h.expensiveField.size, "T3: Lazy field size changed after GC")
    }

    // === Test 4: Recursive object construction with GC ===
    val root = ComplexInit(3) // depth 3, branching 3 = ~40 nodes
    GC.collect()
    assertTrue(root.verify(), "T4: ComplexInit tree corrupted after GC")

    // Multiple trees
    val trees = Array(10) { ComplexInit(2) }
    GC.collect()
    for (i in trees.indices) {
        assertTrue(trees[i].verify(), "T4: Tree $i corrupted after GC")
    }

    // === Test 5: Init + GC + identity hash stability ===
    val obj = ComplexInit(2)
    val hash = obj.identityHashCode()
    GC.collect()
    GC.collect()
    assertEquals(hash, obj.identityHashCode(), "T5: Identity hash changed after GC during init")

    println("PASS")
}
