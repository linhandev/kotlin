// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests interface dispatch (vtable/itable) correctness after GC compaction.
// Risk: After object relocation, TypeInfo pointers must still resolve correctly;
// interface method dispatch tables must point to correct implementations;
// type checks (is/as) must work after object moves.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

interface Shape {
    fun area(): Double
    fun name(): String
    fun verify(): Boolean
}

interface Drawable {
    fun draw(): String
}

class Circle(val radius: Double) : Shape, Drawable {
    val tag = "circle-$radius"
    override fun area(): Double = 3.14159 * radius * radius
    override fun name(): String = "Circle(r=$radius)"
    override fun verify(): Boolean = tag == "circle-$radius"
    override fun draw(): String = "Drawing ${name()}"
}

class Rect(val w: Double, val h: Double) : Shape, Drawable {
    val tag = "rect-$w-$h"
    override fun area(): Double = w * h
    override fun name(): String = "Rect(${w}x${h})"
    override fun verify(): Boolean = tag == "rect-$w-$h"
    override fun draw(): String = "Drawing ${name()}"
}

class Triangle(val base: Double, val height: Double) : Shape {
    val tag = "tri-$base-$height"
    override fun area(): Double = 0.5 * base * height
    override fun name(): String = "Triangle(b=$base,h=$height)"
    override fun verify(): Boolean = tag == "tri-$base-$height"
}

sealed class SealedEvent {
    class Click(val x: Int, val y: Int) : SealedEvent()
    class Scroll(val delta: Int) : SealedEvent()
    class Key(val code: String) : SealedEvent()
}

@Test fun testInterfaceDispatchGC() {
    // === Test 1: Interface dispatch after GC ===
    val shapes: Array<Shape> = arrayOf(
        Circle(5.0), Rect(3.0, 4.0), Triangle(6.0, 8.0),
        Circle(10.0), Rect(7.0, 2.0), Triangle(3.0, 4.0),
    )

    val areasBefore = shapes.map { it.area() }
    val namesBefore = shapes.map { it.name() }
    GC.collect()
    GC.collect()

    // Dispatch must still work correctly after GC
    for (i in shapes.indices) {
        assertEquals(areasBefore[i], shapes[i].area(), "T1: Area changed after GC at $i")
        assertEquals(namesBefore[i], shapes[i].name(), "T1: Name changed after GC at $i")
        assertTrue(shapes[i].verify(), "T1: Shape $i corrupted after GC")
    }

    // === Test 2: Type check (is/as) after GC ===
    val mixed: Array<Any> = arrayOf(
        Circle(1.0), Rect(2.0, 3.0), "not a shape", 42,
        Triangle(4.0, 5.0), Circle(6.0), listOf(1, 2, 3),
    )
    GC.collect()

    var circles = 0; var rects = 0; var triangles = 0; var others = 0
    for (obj in mixed) {
        when (obj) {
            is Circle -> { circles++; assertTrue(obj.verify()) }
            is Rect -> { rects++; assertTrue(obj.verify()) }
            is Triangle -> { triangles++; assertTrue(obj.verify()) }
            else -> others++
        }
    }
    assertEquals(2, circles, "T2: Circle count wrong")
    assertEquals(1, rects, "T2: Rect count wrong")
    assertEquals(1, triangles, "T2: Triangle count wrong")
    assertEquals(3, others, "T2: Other count wrong")

    // === Test 3: Multiple interface dispatch (diamond) ===
    val drawables: Array<Drawable> = arrayOf(Circle(3.0), Rect(4.0, 5.0))
    GC.collect()
    for (d in drawables) {
        val result = d.draw()
        assertTrue(result.startsWith("Drawing"), "T3: Drawable dispatch failed: $result")
    }
    // Same objects via Shape interface
    for (d in drawables) {
        val s = d as Shape
        assertTrue(s.area() > 0, "T3: Shape dispatch on Drawable failed")
    }

    // === Test 4: Large polymorphic array ===
    val bigArray = Array<Shape>(3000) { i ->
        when (i % 3) {
            0 -> Circle(i.toDouble())
            1 -> Rect(i.toDouble(), (i + 1).toDouble())
            else -> Triangle(i.toDouble(), (i + 1).toDouble())
        }
    }

    repeat(5) { GC.collect() }

    var t4Errors = 0
    for (i in bigArray.indices) {
        if (!bigArray[i].verify()) t4Errors++
        // Verify dispatch still works
        val area = bigArray[i].area()
        if (area < 0) t4Errors++ // should never happen for positive inputs
    }
    assertEquals(0, t4Errors, "T4: Large polymorphic array errors")

    // === Test 5: Concurrent interface dispatch + GC ===
    val dispatchErrors = AtomicInt(0)
    val sharedShapes = Array<Shape>(100) { i ->
        when (i % 3) {
            0 -> Circle((i + 1).toDouble())
            1 -> Rect((i + 1).toDouble(), (i + 2).toDouble())
            else -> Triangle((i + 1).toDouble(), (i + 2).toDouble())
        }
    }

    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedShapes, dispatchErrors) }) { (workerIdx, shapes, errors) ->
            for (round in 0 until 100) {
                for (s in shapes) {
                    if (!s.verify()) errors.incrementAndGet()
                    s.area() // dispatch call
                    s.name() // another dispatch
                }
                if (round % 20 == 0) GC.collect()
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, dispatchErrors.value, "T5: Concurrent dispatch errors")

    // === Test 6: Sealed class hierarchy + when exhaustive ===
    val events = Array<SealedEvent>(300) { i ->
        when (i % 3) {
            0 -> SealedEvent.Click(i, i * 2)
            1 -> SealedEvent.Scroll(i)
            else -> SealedEvent.Key("key-$i")
        }
    }
    GC.collect()

    var t6Errors = 0
    for (i in events.indices) {
        when (val e = events[i]) {
            is SealedEvent.Click -> if (e.x != i || e.y != i * 2) t6Errors++
            is SealedEvent.Scroll -> if (e.delta != i) t6Errors++
            is SealedEvent.Key -> if (e.code != "key-$i") t6Errors++
        }
    }
    assertEquals(0, t6Errors, "T6: Sealed class events corrupted after GC")

    println("PASS")
}
