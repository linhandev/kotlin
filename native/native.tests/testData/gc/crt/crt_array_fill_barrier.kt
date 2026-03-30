// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for PR #87: Array.fill() missing write barrier (thisPtr not passed to UpdateHeapRef).
// Without the fix, Kotlin_Array_fillImpl calls UpdateHeapRef without the container pointer,
// so CRT GC cannot track the reference — filled objects may become dangling after relocation.

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class Payload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "payload-$id"
}

// ============================================================
// T1: Basic Array<Payload>.fill(obj) + GC + verify
// ============================================================
@Test fun testArrayFillBasic() {
    val size = 100
    val arr = arrayOfNulls<Payload>(size)
    val obj = Payload(42, "payload-42")

    @Suppress("UNCHECKED_CAST")
    (arr as Array<Payload?>).fill(obj)

    // Force GC — if write barrier was missing, obj may be relocated
    // but the array slots still point to the old address
    GC.collect()

    var errors = 0
    for (i in 0 until size) {
        val elem = arr[i]
        if (elem == null || !elem.verify() || elem.id != 42) {
            errors++
        }
    }
    assertEquals(0, errors, "T1: Array.fill basic — elements invalid after GC")

    // Multiple rounds to increase relocation probability
    for (round in 0 until 50) {
        val freshObj = Payload(round, "payload-$round")
        @Suppress("UNCHECKED_CAST")
        (arr as Array<Payload?>).fill(freshObj)
        // Allocate garbage to trigger GC pressure
        repeat(20) { ByteArray(1024) }
        GC.collect()
        for (i in 0 until size) {
            val elem = arr[i]
            if (elem == null || !elem.verify() || elem.id != round) {
                errors++
                break
            }
        }
    }
    assertEquals(0, errors, "T1: Array.fill multi-round — elements invalid after GC")
    println("T1 PASS")
}

// ============================================================
// T2: Partial fill with fill(value, fromIndex, toIndex) + GC
// ============================================================
@Test fun testArrayFillPartial() {
    val size = 200
    val arr = Array<Payload>(size) { i -> Payload(i, "payload-$i") }

    var errors = 0
    for (round in 0 until 30) {
        val from = round * 3
        val to = minOf(from + 50, size)
        val fillObj = Payload(round + 1000, "payload-${round + 1000}")
        arr.fill(fillObj, from, to)
        GC.collect()

        // Verify filled range
        for (i in from until to) {
            val elem = arr[i]
            if (!elem.verify() || elem.id != round + 1000) {
                errors++
                break
            }
        }
        // Verify unfilled range still intact
        for (i in to until size) {
            if (!arr[i].verify()) {
                errors++
                break
            }
        }
    }
    assertEquals(0, errors, "T2: Partial fill — elements invalid after GC")
    println("T2 PASS")
}

// ============================================================
// T3: Fill with different objects in sequence (write barrier for old values)
// ============================================================
@Test fun testArrayFillSequential() {
    val size = 100
    val arr = Array<Payload>(size) { i -> Payload(i, "payload-$i") }

    var errors = 0
    // Keep references to old values to verify they survive GC
    val oldValues = mutableListOf<Payload>()

    for (round in 0 until 40) {
        // Save references to current array contents before overwrite
        if (round > 0) {
            oldValues.add(arr[0]) // should be the previous fill object
        }
        val newObj = Payload(round + 5000, "payload-${round + 5000}")
        arr.fill(newObj)
        GC.collect()

        // Verify new values
        for (i in 0 until size) {
            if (!arr[i].verify() || arr[i].id != round + 5000) {
                errors++
                break
            }
        }
        // Verify old saved references are still valid (write barrier must have tracked them)
        for (old in oldValues) {
            if (!old.verify()) {
                errors++
                break
            }
        }
    }
    assertEquals(0, errors, "T3: Sequential fill — old or new values invalid after GC")
    println("T3 PASS")
}

// ============================================================
// T4: Concurrent fill + GC from different threads
// ============================================================
@Test fun testArrayFillConcurrent() {
    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { idx }) { threadIdx ->
            var localErrors = 0
            for (round in 0 until 50) {
                val arr = Array<Payload>(100) { i ->
                    Payload(threadIdx * 10000 + round * 100 + i,
                            "payload-${threadIdx * 10000 + round * 100 + i}")
                }
                val fillObj = Payload(threadIdx * 10000 + round,
                                      "payload-${threadIdx * 10000 + round}")
                arr.fill(fillObj)
                GC.collect()
                for (i in arr.indices) {
                    val elem = arr[i]
                    if (!elem.verify() || elem.id != threadIdx * 10000 + round) {
                        localErrors++
                        break
                    }
                }
            }
            localErrors
        }
    }

    var errors = 0
    for (f in futures) errors += f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, errors, "T4: Concurrent fill — elements invalid after GC")
    println("T4 PASS")
}

// ============================================================
// T5: Array.copyInto() defensive coverage (uses Kotlin_Array_copyImpl)
// ============================================================
@Test fun testArrayCopyInto() {
    var errors = 0

    for (round in 0 until 50) {
        val src = Array<Payload>(100) { i -> Payload(round * 100 + i, "payload-${round * 100 + i}") }
        val dst = arrayOfNulls<Payload>(100)

        src.copyInto(dst)
        GC.collect()

        for (i in 0 until 100) {
            val elem = dst[i]
            if (elem == null || !elem.verify()) {
                errors++
                break
            }
        }
    }
    assertEquals(0, errors, "T5: copyInto — elements invalid after GC")

    // Partial copyInto
    for (round in 0 until 30) {
        val src = Array<Payload>(100) { i -> Payload(i, "payload-$i") }
        val dst = Array<Payload>(100) { i -> Payload(i + 500, "payload-${i + 500}") }
        src.copyInto(dst, destinationOffset = 10, startIndex = 20, endIndex = 60)
        GC.collect()

        // Verify copied region
        for (i in 10 until 50) {
            val elem = dst[i]
            if (!elem.verify() || elem.id != i + 10) {
                errors++
                break
            }
        }
        // Verify non-copied region
        for (i in 0 until 10) {
            if (!dst[i].verify() || dst[i].id != i + 500) {
                errors++
                break
            }
        }
    }
    assertEquals(0, errors, "T5: partial copyInto — elements invalid after GC")
    println("T5 PASS")
}

// ============================================================
// T6: Fill large array (10000 elements) to stress barrier throughput
// ============================================================
@Test fun testArrayFillLarge() {
    var errors = 0

    for (round in 0 until 20) {
        val arr = arrayOfNulls<Payload>(10000)
        val fillObj = Payload(round, "payload-$round")

        @Suppress("UNCHECKED_CAST")
        (arr as Array<Payload?>).fill(fillObj)

        // Allocate extra objects to pressure GC
        val garbage = Array(50) { Array(100) { ByteArray(128) } }
        GC.collect()

        // Spot-check elements (checking all 10000 every round is slow)
        val checkIndices = intArrayOf(0, 1, 99, 500, 2500, 5000, 7500, 9999)
        for (i in checkIndices) {
            val elem = arr[i]
            if (elem == null || !elem.verify() || elem.id != round) {
                errors++
                break
            }
        }
        // Full verify on last round
        if (round == 19) {
            for (i in 0 until 10000) {
                val elem = arr[i]
                if (elem == null || !elem.verify() || elem.id != round) {
                    errors++
                    break
                }
            }
        }
    }
    assertEquals(0, errors, "T6: Large array fill — elements invalid after GC")
    println("T6 PASS")
}

