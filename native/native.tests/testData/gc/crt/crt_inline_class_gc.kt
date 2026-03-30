// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests inline/value class boxing behavior under GC.
// Risk: Inline classes may be boxed/unboxed at call boundaries;
// boxed inline class instances are heap objects that GC must track;
// unboxing after GC may return stale values if box was moved.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

value class UserId(val id: Int)

value class Token(val value: String)

value class Wrapper<T>(val inner: T)

// Force boxing by using as Any or in generic context
fun boxUserId(u: UserId): Any = u
fun unboxUserId(a: Any): UserId = a as UserId

fun boxToken(t: Token): Any = t
fun unboxToken(a: Any): Token = a as Token

@Test fun testInlineClassGC() {
    // === Test 1: Box → GC → Unbox ===
    val original = UserId(42)
    val boxed = boxUserId(original)
    GC.collect()
    val unboxed = unboxUserId(boxed)
    assertEquals(42, unboxed.id, "T1: UserId unbox after GC failed")

    // === Test 2: Many boxed inline classes in array ===
    val boxedArray = Array<Any>(1000) { boxUserId(UserId(it)) }
    GC.collect()
    for (i in boxedArray.indices) {
        val u = unboxUserId(boxedArray[i])
        assertEquals(i, u.id, "T2: Array element $i wrong after GC")
    }

    // === Test 3: String-backed inline class ===
    val tokens = Array<Any>(500) { boxToken(Token("token-$it")) }
    GC.collect()
    for (i in tokens.indices) {
        val t = unboxToken(tokens[i])
        assertEquals("token-$i", t.value, "T3: Token $i wrong after GC")
    }

    // === Test 4: Generic inline class ===
    val wrappers = Array<Any>(500) { i ->
        val w: Wrapper<String> = Wrapper("wrapped-$i")
        w as Any // box
    }
    GC.collect()
    for (i in wrappers.indices) {
        @Suppress("UNCHECKED_CAST")
        val w = wrappers[i] as Wrapper<String>
        assertEquals("wrapped-$i", w.inner, "T4: Wrapper $i wrong after GC")
    }

    // === Test 5: Inline class in collection ===
    val list = mutableListOf<UserId>()
    for (i in 0 until 2000) {
        list.add(UserId(i))
        if (i % 200 == 0) GC.collect()
    }
    for (i in list.indices) {
        assertEquals(i, list[i].id, "T5: List element $i wrong")
    }

    // === Test 6: Inline class as Map key ===
    val map = HashMap<UserId, String>()
    for (i in 0 until 1000) {
        map[UserId(i)] = "value-$i"
    }
    GC.collect()
    for (i in 0 until 1000) {
        assertEquals("value-$i", map[UserId(i)], "T6: Map lookup failed for key $i")
    }

    // === Test 7: Concurrent boxing/unboxing + GC ===
    val inlineErrors = AtomicInt(0)
    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, inlineErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 200) {
                val u = UserId(workerIdx * 10000 + round)
                val boxed = boxUserId(u)
                if (round % 30 == 0) GC.collect()
                val back = unboxUserId(boxed)
                if (back.id != workerIdx * 10000 + round) {
                    errors.incrementAndGet()
                }
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, inlineErrors.value, "T7: Concurrent inline class errors")

    // === Test 8: Nullable inline class ===
    val nullables = Array<UserId?>(500) { if (it % 3 == 0) null else UserId(it) }
    GC.collect()
    for (i in nullables.indices) {
        if (i % 3 == 0) {
            assertNull(nullables[i], "T8: Expected null at $i")
        } else {
            assertEquals(i, nullables[i]!!.id, "T8: Non-null entry $i wrong")
        }
    }

    println("PASS")
}
