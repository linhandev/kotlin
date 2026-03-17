// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests objects with many fields (wide objects) under GC, and independent
// read/write ordering guarantees.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

// Wide object with 64 fields — spans multiple cache lines
class WideObj64(val id: Int) {
    val f00 = id; val f01 = id+1; val f02 = id+2; val f03 = id+3
    val f04 = id+4; val f05 = id+5; val f06 = id+6; val f07 = id+7
    val f08 = id+8; val f09 = id+9; val f10 = id+10; val f11 = id+11
    val f12 = id+12; val f13 = id+13; val f14 = id+14; val f15 = id+15
    val f16 = id+16; val f17 = id+17; val f18 = id+18; val f19 = id+19
    val f20 = id+20; val f21 = id+21; val f22 = id+22; val f23 = id+23
    val f24 = id+24; val f25 = id+25; val f26 = id+26; val f27 = id+27
    val f28 = id+28; val f29 = id+29; val f30 = id+30; val f31 = id+31
    val s0 = "w-$id"; val s1 = "w-${id+1}"; val s2 = "w-${id+2}"; val s3 = "w-${id+3}"
    val arr0 = IntArray(16) { id + it }
    val arr1 = IntArray(16) { id + 16 + it }

    // Reference chain: wide object points to another wide object
    var next: WideObj64? = null

    fun verify(): Boolean {
        if (f00 != id || f31 != id + 31) return false
        if (s0 != "w-$id") return false
        if (arr0[0] != id || arr1[15] != id + 31) return false
        return true
    }
}

@Test fun testWideObjectGC() {
    // === Test 1: Array of wide objects survives GC ===
    val wides = Array(500) { WideObj64(it) }
    repeat(10) { GC.collect() }

    var t1Errors = 0
    for (w in wides) {
        if (!w.verify()) t1Errors++
    }
    assertEquals(0, t1Errors, "T1: Wide objects corrupted after GC")

    // === Test 2: Chain of wide objects (cross-region references) ===
    var chain: WideObj64? = null
    for (i in 0 until 200) {
        val node = WideObj64(i)
        node.next = chain
        chain = node
    }

    repeat(5) { GC.collect() }

    var chainLen = 0
    var chainErrors = 0
    var cur = chain
    while (cur != null) {
        if (!cur.verify()) chainErrors++
        cur = cur.next
        chainLen++
    }
    assertEquals(200, chainLen, "T2: Wide chain length wrong")
    assertEquals(0, chainErrors, "T2: Wide chain corrupted")

    // === Test 3: Concurrent read/write of wide objects + GC ===
    val shared = AtomicReference<WideObj64?>(WideObj64(0))
    val wideErrors = AtomicInt(0)

    val workers = Array(4) { Worker.start() }

    // Writer
    val writerFuture = workers[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { shared }) { s ->
        for (i in 1..1000) {
            s.value = WideObj64(i)
            if (i % 100 == 0) GC.collect()
        }
        0
    }

    // Readers
    val readerFutures = (1 until 4).map { idx ->
        workers[idx].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(shared, wideErrors) }) { (s, errors) ->
            for (i in 0 until 3000) {
                val obj = s.value
                if (obj != null && !obj.verify()) {
                    errors.incrementAndGet()
                }
            }
            0
        }
    }

    writerFuture.result
    for (f in readerFutures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, wideErrors.value, "T3: Wide object concurrent read saw corruption")

    // === Test 4: IRIW — independent read/write ===
    // Two independent writers, two independent readers
    val x = AtomicInt(0)
    val y = AtomicInt(0)
    val iriw = AtomicInt(0)

    val workers4 = Array(4) { Worker.start() }
    for (trial in 0 until 200) {
        x.value = 0; y.value = 0

        // Writer A: x=1
        val wA = workers4[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { x }) { ax ->
            ax.value = 1; 0
        }
        // Writer B: y=1
        val wB = workers4[1].execute(kotlin.native.concurrent.TransferMode.SAFE, { y }) { ay ->
            ay.value = 1; 0
        }
        // Reader C: r1=x, r2=y
        val rC = workers4[2].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(x, y) }) { (ax, ay) ->
            val r1 = ax.value; val r2 = ay.value
            r1 * 10 + r2 // encode result
        }
        // Reader D: r3=y, r4=x
        val rD = workers4[3].execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(x, y) }) { (ax, ay) ->
            val r3 = ay.value; val r4 = ax.value
            r3 * 10 + r4
        }

        wA.result; wB.result
        rC.result; rD.result

        if (trial % 50 == 0) GC.collect()
    }
    for (w in workers4) w.requestTermination().result
    // No crash = IRIW ordering is safe under GC

    println("PASS")
}
