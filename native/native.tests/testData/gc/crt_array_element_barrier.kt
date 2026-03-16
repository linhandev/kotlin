// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests write barrier correctness specifically for array element stores.
// Array element writes (arr[i] = obj) use a different code path than field writes;
// the SATB barrier must fire for old values being overwritten in array slots.
// Also tests derived pointers: accessing arr[i].field creates a derived pointer
// that must be updated if arr[i] is moved by GC.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

class ArrElement(val id: Int, val payload: String) {
    fun verify(): Boolean = payload == "elem-$id"
}

@Test fun testArrayElementBarrier() {
    // === Test 1: Overwrite array elements with GC between writes ===
    val arr = Array(1000) { ArrElement(it, "elem-$it") }

    for (round in 0 until 20) {
        // Overwrite every element — old values must be SATB-logged
        for (i in arr.indices) {
            arr[i] = ArrElement(round * 1000 + i, "elem-${round * 1000 + i}")
        }
        GC.collect()
        // Verify current generation
        for (i in arr.indices) {
            assertTrue(arr[i].verify(), "T1: Round $round element $i corrupted")
        }
    }

    // === Test 2: Derived pointer — access arr[i].field after GC ===
    val arr2 = Array(2000) { ArrElement(it, "elem-$it") }
    GC.collect()
    GC.collect()

    // Access field through array element (derived pointer)
    var t2Errors = 0
    for (i in arr2.indices) {
        val payload = arr2[i].payload // derived pointer: base=arr2[i], offset=payload field
        if (payload != "elem-$i") t2Errors++
    }
    assertEquals(0, t2Errors, "T2: Derived pointer through array element failed")

    // === Test 3: Swap array elements (both old values need barrier) ===
    val arr3 = Array(500) { ArrElement(it, "elem-$it") }
    for (round in 0 until 50) {
        for (i in 0 until arr3.size - 1 step 2) {
            val temp = arr3[i]
            arr3[i] = arr3[i + 1]    // SATB must log old arr3[i]
            arr3[i + 1] = temp        // SATB must log old arr3[i+1]
        }
        if (round % 5 == 0) GC.collect()
    }
    // All elements must survive (just shuffled)
    val ids = arr3.map { it.id }.toSet()
    assertEquals(500, ids.size, "T3: Lost elements during swap")
    for (elem in arr3) {
        assertTrue(elem.verify(), "T3: Element ${elem.id} corrupted after swaps")
    }

    // === Test 4: 2D array — nested array element access ===
    val matrix = Array(100) { i -> Array(100) { j -> ArrElement(i * 100 + j, "elem-${i * 100 + j}") } }
    GC.collect()

    var t4Errors = 0
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (!matrix[i][j].verify()) t4Errors++
        }
    }
    assertEquals(0, t4Errors, "T4: 2D array elements corrupted after GC")

    // Overwrite diagonal
    for (i in 0 until 100) {
        matrix[i][i] = ArrElement(99999 - i, "elem-${99999 - i}")
    }
    GC.collect()
    for (i in 0 until 100) {
        assertTrue(matrix[i][i].verify(), "T4: Diagonal element $i corrupted")
    }

    // === Test 5: Concurrent array element writes + GC ===
    val sharedArr = Array(1000) { ArrElement(it, "elem-$it") }
    val arrErrors = AtomicInt(0)

    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, sharedArr, arrErrors) }) { (workerIdx, arr, errors) ->
            // Each worker writes to its own section
            val start = workerIdx * 250
            val end = start + 250
            for (round in 0 until 100) {
                for (i in start until end) {
                    arr[i] = ArrElement(workerIdx * 1000000 + round * 1000 + i,
                        "elem-${workerIdx * 1000000 + round * 1000 + i}")
                }
                if (round % 10 == 0) GC.collect()

                // Verify our section
                for (i in start until end) {
                    if (!arr[i].verify()) errors.incrementAndGet()
                }
            }
            0
        }
    }

    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, arrErrors.value, "T5: Concurrent array element write errors")

    // === Test 6: Null → non-null → null transitions in array ===
    val nullArr = arrayOfNulls<ArrElement>(500)
    for (round in 0 until 30) {
        // Fill
        for (i in nullArr.indices) {
            nullArr[i] = ArrElement(round * 500 + i, "elem-${round * 500 + i}")
        }
        GC.collect()
        // Verify
        for (i in nullArr.indices) {
            assertNotNull(nullArr[i], "T6: Element $i null after fill")
            assertTrue(nullArr[i]!!.verify(), "T6: Element $i corrupted")
        }
        // Clear
        for (i in nullArr.indices) {
            nullArr[i] = null
        }
        GC.collect()
    }

    println("PASS")
}
