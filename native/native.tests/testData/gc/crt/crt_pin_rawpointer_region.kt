// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Tests Pin/RawPointerRegion under CRT GC:
// - Pin/unpin lifecycle
// - Address stability across GC cycles
// - Native pointer read/write via addressOf
// - Concurrent pin + GC
// - usePinned block pattern
// - Multiple pins in same region
// - Generational GC survival
// - HashCode stability of pinned objects
// - Pin leak (no unpin)

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class, kotlinx.cinterop.ExperimentalForeignApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker
import kotlin.concurrent.AtomicInt
import kotlinx.cinterop.*

// T1: Basic Pin/Unpin + GC
// Pin a ByteArray, get addressOf, trigger GC, verify address unchanged and data intact.
@Test fun testPinBasicGC() {
    val arr = ByteArray(128) { it.toByte() }
    val pinned = arr.pin()
    val addr = pinned.addressOf(0).rawValue

    GC.collect()

    val addrAfter = pinned.addressOf(0).rawValue
    assertEquals(addr, addrAfter, "T1: Address changed after GC")

    for (i in arr.indices) {
        assertEquals(i.toByte(), arr[i], "T1: Data corrupted at index $i")
    }

    pinned.unpin()
    println("T1 OK")
}

// T2: Pin + addressOf data write/read
// Pin ByteArray, write data via native pointer, trigger GC, read back via Kotlin array, verify match.
@Test fun testPinWriteReadViaPointer() {
    val arr = ByteArray(64)
    val pinned = arr.pin()
    val ptr = pinned.addressOf(0)

    // Write via native pointer
    for (i in 0 until 64) {
        ptr[i] = (i * 2).toByte()
    }

    GC.collect()

    // Read back via Kotlin array
    for (i in 0 until 64) {
        assertEquals((i * 2).toByte(), arr[i], "T2: Mismatch at index $i")
    }

    // Also verify reading via pointer still works
    for (i in 0 until 64) {
        assertEquals((i * 2).toByte(), ptr[i], "T2: Pointer read mismatch at index $i")
    }

    pinned.unpin()
    println("T2 OK")
}

// T3: Pin survives multiple GC cycles
// Pin object, trigger 5+ GC cycles, verify address stable each time.
@Test fun testPinSurvivesMultipleGC() {
    val arr = ByteArray(256) { (it % 127).toByte() }
    val pinned = arr.pin()
    val addr = pinned.addressOf(0).rawValue

    for (cycle in 0 until 10) {
        // Allocate garbage to trigger real GC work
        val garbage = Array(100) { ByteArray(1024) }
        garbage.hashCode() // prevent optimization

        GC.collect()

        val currentAddr = pinned.addressOf(0).rawValue
        assertEquals(addr, currentAddr, "T3: Address changed after GC cycle $cycle")

        // Verify data integrity
        for (i in arr.indices) {
            assertEquals((i % 127).toByte(), arr[i], "T3: Data corrupted at cycle $cycle index $i")
        }
    }

    pinned.unpin()
    println("T3 OK")
}

// T4: Concurrent Pin + GC
// 4 workers each pin their own arrays + trigger GC simultaneously.
@Test fun testPinConcurrentGC() {
    val globalErrors = AtomicInt(0)
    val numWorkers = 4
    val rounds = 30

    val workers = Array(numWorkers) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, rounds) }) { (workerIdx, numRounds) ->
            var localErrors = 0

            for (round in 0 until numRounds) {
                val arr = ByteArray(128) { ((workerIdx * 1000 + round + it) % 127).toByte() }
                val pinned = arr.pin()
                val addr = pinned.addressOf(0).rawValue

                GC.collect()

                val addrAfter = pinned.addressOf(0).rawValue
                if (addr != addrAfter) localErrors++

                // Verify data
                for (i in arr.indices) {
                    if (arr[i] != ((workerIdx * 1000 + round + i) % 127).toByte()) {
                        localErrors++
                        break
                    }
                }

                pinned.unpin()
            }

            localErrors
        }
    }

    var totalErrors = 0
    for (f in futures) totalErrors += f.result
    for (w in workers) w.requestTermination().result

    assertEquals(0, totalErrors, "T4: Concurrent pin had $totalErrors errors")
    println("T4 OK")
}

// T5: Unpin + GC recovery
// Pin array, verify address, unpin, trigger multiple GCs, allocate new objects to verify heap.
@Test fun testUnpinGCRecovery() {
    val arr = ByteArray(4096) { it.toByte() }
    val pinned = arr.pin()
    val addr = pinned.addressOf(0).rawValue
    assertTrue(addr != kotlin.native.internal.NativePtr.NULL, "T5: Address should not be null")

    pinned.unpin()

    // Trigger multiple GCs — the unpinned region should be reclaimable
    for (i in 0 until 5) {
        GC.collect()
    }

    // Allocate new objects to verify heap is functional
    val newArrays = Array(100) { ByteArray(1024) { (it % 256).toByte() } }
    GC.collect()

    for (i in newArrays.indices) {
        for (j in newArrays[i].indices) {
            assertEquals((j % 256).toByte(), newArrays[i][j], "T5: New allocation corrupted at [$i][$j]")
        }
    }

    println("T5 OK")
}

// T6: Multiple pins in same region
// Allocate many small arrays, pin all, unpin some, GC, verify remaining pinned are stable.
@Test fun testMultiplePinsSameRegion() {
    val count = 50
    val arrays = Array(count) { ByteArray(64) { (it + count).toByte() } }
    val pinned = Array(count) { arrays[it].pin() }
    val addrs = LongArray(count) { pinned[it].addressOf(0).rawValue.toLong() }

    // Unpin first half
    for (i in 0 until count / 2) {
        pinned[i].unpin()
    }

    GC.collect()

    // Verify remaining pinned are stable
    for (i in count / 2 until count) {
        val currentAddr = pinned[i].addressOf(0).rawValue.toLong()
        assertEquals(addrs[i], currentAddr, "T6: Address changed for pinned[$i]")

        // Verify data
        for (j in arrays[i].indices) {
            assertEquals((j + count).toByte(), arrays[i][j], "T6: Data corrupted at [$i][$j]")
        }
    }

    // Clean up remaining pins
    for (i in count / 2 until count) {
        pinned[i].unpin()
    }

    println("T6 OK")
}

// T7: Pin + generational GC
// Pin young object, trigger multiple young GCs, verify address stable.
@Test fun testPinGenerationalGC() {
    // Create fresh (young) object and pin immediately
    val arr = ByteArray(512) { (it % 200).toByte() }
    val pinned = arr.pin()
    val addr = pinned.addressOf(0).rawValue

    // Trigger many GC cycles — young gen collections should not move pinned object
    for (cycle in 0 until 20) {
        // Allocate young garbage
        val garbage = Array(50) { ByteArray(256) }
        garbage.hashCode()

        GC.collect()

        val currentAddr = pinned.addressOf(0).rawValue
        assertEquals(addr, currentAddr, "T7: Address changed at cycle $cycle")
    }

    // Verify data still intact
    for (i in arr.indices) {
        assertEquals((i % 200).toByte(), arr[i], "T7: Data corrupted at index $i")
    }

    pinned.unpin()
    println("T7 OK")
}

// T8: Pinned object hashCode stability
// Pin object, get hashCode, trigger GC, verify hashCode unchanged.
@Test fun testPinnedHashCodeStability() {
    val arr = ByteArray(128) { it.toByte() }
    val pinned = arr.pin()

    val hashBefore = arr.hashCode()

    for (cycle in 0 until 5) {
        GC.collect()
        val hashAfter = arr.hashCode()
        assertEquals(hashBefore, hashAfter, "T8: HashCode changed after GC cycle $cycle")
    }

    pinned.unpin()

    // HashCode should still be stable after unpin + GC
    GC.collect()
    val hashFinal = arr.hashCode()
    assertEquals(hashBefore, hashFinal, "T8: HashCode changed after unpin + GC")

    println("T8 OK")
}

// T9: usePinned block + GC
// Use usePinned {} block pattern, trigger GC inside block, verify data integrity.
@Test fun testUsePinnedBlockGC() {
    val arr = IntArray(100) { it * 3 }

    arr.usePinned { pinned ->
        val ptr = pinned.addressOf(0)
        val addrBefore = ptr.rawValue

        // Write via pointer
        for (i in 0 until 100) {
            ptr[i] = i * 7
        }

        GC.collect()

        val addrAfter = pinned.addressOf(0).rawValue
        assertEquals(addrBefore, addrAfter, "T9: Address changed inside usePinned block after GC")

        // Read back via Kotlin array
        for (i in 0 until 100) {
            assertEquals(i * 7, arr[i], "T9: Data mismatch at index $i (Kotlin read)")
        }

        // Read back via pointer
        for (i in 0 until 100) {
            assertEquals(i * 7, ptr[i], "T9: Data mismatch at index $i (pointer read)")
        }
    }

    // After block, array should still hold data
    for (i in 0 until 100) {
        assertEquals(i * 7, arr[i], "T9: Data lost after usePinned block at index $i")
    }

    println("T9 OK")
}

// T10: Pin leak (no unpin, should not crash)
// Create Pinned but don't call unpin. Trigger GC. Should not crash.
@Test fun testPinLeakNoCrash() {
    for (round in 0 until 20) {
        val arr = ByteArray(256) { (it + round).toByte() }
        val pinned = arr.pin()
        val addr = pinned.addressOf(0).rawValue
        assertTrue(addr != kotlin.native.internal.NativePtr.NULL, "T10: Pinned address should not be null")
        // Intentionally NOT calling pinned.unpin()
    }

    // Multiple GC cycles — leaked pins should not cause crash
    for (i in 0 until 10) {
        val garbage = Array(100) { ByteArray(512) }
        garbage.hashCode()
        GC.collect()
    }

    // Heap should still be functional
    val arr = IntArray(1000) { it }
    GC.collect()
    for (i in arr.indices) {
        assertEquals(i, arr[i], "T10: Heap corrupted after pin leak")
    }

    println("T10 OK")
}

// Main aggregator
@Test fun testCrt_pin_rawpointer_region() {
    testPinBasicGC()
    testPinWriteReadViaPointer()
    testPinSurvivesMultipleGC()
    testPinConcurrentGC()
    testUnpinGCRecovery()
    testMultiplePinsSameRegion()
    testPinGenerationalGC()
    testPinnedHashCodeStability()
    testUsePinnedBlockGC()
    testPinLeakNoCrash()
    println("PASS")
}
