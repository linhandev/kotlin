// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests enum classes, delegated properties, and companion objects under GC.
// Risk: Enum entries are singletons stored in static arrays; delegated properties
// create hidden delegate objects; companion objects have lazy init.
// GC must not collect these even though they may not have explicit root paths.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// === Enum with complex data ===
enum class Color(val rgb: Int, val label: String) {
    RED(0xFF0000, "red"),
    GREEN(0x00FF00, "green"),
    BLUE(0x0000FF, "blue"),
    YELLOW(0xFFFF00, "yellow"),
    CYAN(0x00FFFF, "cyan"),
    MAGENTA(0xFF00FF, "magenta"),
    WHITE(0xFFFFFF, "white"),
    BLACK(0x000000, "black");

    val data = "color-$label-$rgb"
    fun verify(): Boolean = data == "color-$label-$rgb"
}

// === Enum with abstract method ===
enum class Shape {
    CIRCLE {
        override fun area(size: Double): Double = 3.14159 * size * size
        override fun name(): String = "circle"
    },
    SQUARE {
        override fun area(size: Double): Double = size * size
        override fun name(): String = "square"
    },
    TRIANGLE {
        override fun area(size: Double): Double = 0.5 * size * size
        override fun name(): String = "triangle"
    };

    abstract fun area(size: Double): Double
    abstract fun name(): String
}

// === Delegated property ===
class CachedProperty<T>(private val initializer: () -> T) : ReadOnlyProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) value = initializer()
        return value!!
    }
}

class DelegateHolder(val id: Int) {
    val expensive: String by CachedProperty { "computed-$id-${id * 42}" }
    val array: IntArray by lazy { IntArray(100) { id + it } }
    val nested: DelegateHolder? by lazy { if (id > 0) DelegateHolder(id - 1) else null }
}

// === Companion object with state ===
class WithCompanion(val id: Int) {
    companion object {
        val registry = mutableListOf<String>()
        val counter = AtomicInt(0)

        fun create(id: Int): WithCompanion {
            counter.incrementAndGet()
            registry.add("created-$id")
            return WithCompanion(id)
        }
    }

    val data = "wc-$id"
    fun verify(): Boolean = data == "wc-$id"
}

@Test fun testEnumDelegateGC() {
    // === Test 1: Enum entries survive GC ===
    GC.collect()
    for (color in Color.values()) {
        assertTrue(color.verify(), "T1: Enum ${color.name} corrupted after GC")
    }
    assertEquals(Color.RED, Color.valueOf("RED"), "T1: valueOf broken after GC")
    assertEquals(8, Color.values().size, "T1: Enum values count wrong")

    // Multiple GC cycles
    repeat(10) { GC.collect() }
    assertEquals(0xFF0000, Color.RED.rgb, "T1: RED rgb wrong after 10 GC cycles")
    assertEquals("blue", Color.BLUE.label, "T1: BLUE label wrong")

    // === Test 2: Enum with abstract methods after GC ===
    GC.collect()
    val areas = Shape.values().map { it.area(5.0) }
    assertEquals(3, areas.size, "T2: Shape values count wrong")
    assertTrue(areas[0] > 78.0, "T2: CIRCLE area wrong")
    assertEquals(25.0, areas[1], "T2: SQUARE area wrong")
    assertEquals(12.5, areas[2], "T2: TRIANGLE area wrong")

    // === Test 3: Delegated properties survive GC ===
    val holders = Array(100) { DelegateHolder(it) }

    // Force lazy init on all
    for (h in holders) {
        val exp = h.expensive // triggers CachedProperty
        val arr = h.array     // triggers lazy
    }

    GC.collect()
    GC.collect()

    for (i in holders.indices) {
        assertEquals("computed-$i-${i * 42}", holders[i].expensive,
            "T3: CachedProperty $i wrong after GC")
        assertEquals(i, holders[i].array[0], "T3: Lazy array $i wrong after GC")
        assertEquals(i + 99, holders[i].array[99], "T3: Lazy array $i last wrong")
    }

    // === Test 4: Nested lazy delegate chain ===
    val deep = DelegateHolder(5)
    var cur: DelegateHolder? = deep
    var depth = 0
    while (cur != null) {
        GC.collect()
        val exp = cur.expensive
        assertTrue(exp.startsWith("computed-"), "T4: Nested delegate $depth broken")
        cur = cur.nested
        depth++
    }
    assertEquals(6, depth, "T4: Nested chain depth wrong") // 5,4,3,2,1,0

    // === Test 5: Companion object state survives GC ===
    WithCompanion.registry.clear()
    WithCompanion.counter.value = 0

    val objects = Array(50) { WithCompanion.create(it) }
    GC.collect()

    assertEquals(50, WithCompanion.counter.value, "T5: Companion counter wrong")
    assertEquals(50, WithCompanion.registry.size, "T5: Companion registry size wrong")
    for (obj in objects) {
        assertTrue(obj.verify(), "T5: WithCompanion ${obj.id} corrupted after GC")
    }

    // === Test 6: Concurrent enum access + GC ===
    val enumErrors = AtomicInt(0)
    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, enumErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 200) {
                for (color in Color.values()) {
                    if (!color.verify()) errors.incrementAndGet()
                }
                for (shape in Shape.values()) {
                    shape.area(round.toDouble())
                }
                if (round % 30 == 0) GC.collect()
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, enumErrors.value, "T6: Concurrent enum access errors")

    println("PASS")
}
