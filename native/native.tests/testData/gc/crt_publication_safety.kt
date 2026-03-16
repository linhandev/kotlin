// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests object publication safety — a thread publishes a newly-constructed
// object via AtomicReference, another thread reads it. The reader must never
// see a partially-constructed object (torn read) or a dangling pointer.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

// Object with multiple fields — all must be visible atomically
class Published(val a: Int, val b: Int, val c: String, val d: Long) {
    val checksum = a + b + d
    fun verify(): Boolean = c == "pub-$a" && checksum == a.toLong() + b + d
}

// Wide object — many fields, more likely to see partial construction
class WideObject(val id: Int) {
    val f0 = id; val f1 = id + 1; val f2 = id + 2; val f3 = id + 3
    val f4 = id + 4; val f5 = id + 5; val f6 = id + 6; val f7 = id + 7
    val f8 = id + 8; val f9 = id + 9; val f10 = id + 10; val f11 = id + 11
    val f12 = id + 12; val f13 = id + 13; val f14 = id + 14; val f15 = id + 15
    val s0 = "w-$id"; val s1 = "w-${id+1}"; val s2 = "w-${id+2}"; val s3 = "w-${id+3}"
    val arr = IntArray(16) { id + it }

    fun verify(): Boolean {
        if (f0 != id || f15 != id + 15) return false
        if (s0 != "w-$id") return false
        if (arr[0] != id || arr[15] != id + 15) return false
        return true
    }
}

val pubErrors = AtomicInt(0)

@Test fun testPublicationSafety() {
    // === Test 1: Publish simple object via AtomicReference ===
    val slot = AtomicReference<Published?>(null)
    pubErrors.value = 0

    val workers = Array(6) { Worker.start() }

    // Workers 0-2: publishers
    val pubFutures = (0 until 3).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, slot) }) { (workerIdx, s) ->
            for (round in 0 until 500) {
                val obj = Published(
                    a = workerIdx * 10000 + round,
                    b = round * 2,
                    c = "pub-${workerIdx * 10000 + round}",
                    d = round.toLong() * 3
                )
                s.value = obj
                if (round % 50 == 0) GC.collect()
            }
            0
        }
    }

    // Workers 3-5: readers
    val readFutures = (3 until 6).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(slot, pubErrors) }) { (s, errors) ->
            for (round in 0 until 1000) {
                val obj = s.value
                if (obj != null && !obj.verify()) {
                    errors.incrementAndGet()
                }
                if (round % 100 == 0) GC.collect()
            }
            0
        }
    }

    for (f in pubFutures) f.result
    for (f in readFutures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, pubErrors.value, "T1: Publication safety violation — saw torn object")

    // === Test 2: Wide object publication ===
    val wideSlot = AtomicReference<WideObject?>(null)
    val wideErrors = AtomicInt(0)

    val workers2 = Array(4) { Worker.start() }
    val widePubFuture = workers2[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { wideSlot }) { s ->
        for (round in 0 until 300) {
            s.value = WideObject(round)
            if (round % 30 == 0) GC.collect()
        }
        0
    }
    val wideReadFutures = (1 until 4).map { idx ->
        workers2[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(wideSlot, wideErrors) }) { (s, errors) ->
            for (round in 0 until 500) {
                val obj = s.value
                if (obj != null && !obj.verify()) {
                    errors.incrementAndGet()
                }
                if (round % 50 == 0) GC.collect()
            }
            0
        }
    }
    widePubFuture.result
    for (f in wideReadFutures) f.result
    for (w in workers2) w.requestTermination().result
    assertEquals(0, wideErrors.value, "T2: Wide object publication safety violation")

    // === Test 3: Array element publication ===
    val sharedArray = Array(100) { AtomicReference<Published?>(null) }
    val arrErrors = AtomicInt(0)

    val workers3 = Array(4) { Worker.start() }
    val arrPubFuture = workers3[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { sharedArray }) { arr ->
        for (round in 0 until 200) {
            val idx = round % arr.size
            arr[idx].value = Published(round, round * 2, "pub-$round", round.toLong() * 3)
            if (round % 20 == 0) GC.collect()
        }
        0
    }
    val arrReadFutures = (1 until 4).map { idx ->
        workers3[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(sharedArray, arrErrors) }) { (arr, errors) ->
            for (round in 0 until 500) {
                val idx = round % arr.size
                val obj = arr[idx].value
                if (obj != null && !obj.verify()) {
                    errors.incrementAndGet()
                }
            }
            0
        }
    }
    arrPubFuture.result
    for (f in arrReadFutures) f.result
    for (w in workers3) w.requestTermination().result
    assertEquals(0, arrErrors.value, "T3: Array element publication safety violation")

    println("PASS")
}
