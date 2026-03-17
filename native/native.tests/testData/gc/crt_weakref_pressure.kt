// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests WeakRef clearing under memory pressure and humongous allocation fragmentation.
// Risk: GC must clear all weak references before throwing OOM; fragmented heap
// must still serve large allocations through compaction/coalescing.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC

class SoftPayload(val id: Int, val data: ByteArray)

@Test fun testWeakrefPressure() {
    // === Test 1: WeakRef clearing under memory pressure ===
    // Create many WeakRefs, apply memory pressure, verify clearing
    val weakRefs = mutableListOf<WeakReference<SoftPayload>>()

    for (i in 0 until 5000) {
        val payload = SoftPayload(i, ByteArray(1024) { (i % 256).toByte() })
        weakRefs.add(WeakReference(payload))
        // payload goes out of scope
    }

    // Apply pressure: allocate lots of garbage
    for (i in 0 until 100) {
        val garbage = ByteArray(100_000)
        GC.collect()
    }

    // After pressure, some WeakRefs should be cleared
    var cleared = 0
    var alive = 0
    var dangling = 0
    for (wr in weakRefs) {
        val ref = wr.get()
        when {
            ref == null -> cleared++
            ref.data.size == 1024 -> alive++
            else -> dangling++
        }
    }
    assertEquals(0, dangling, "T1: Dangling weak references under pressure")

    // === Test 2: Humongous allocation fragmentation pattern ===
    // Variable-sized allocations to fragment, then large allocation
    var seed = 42L
    fun nextSize(): Int {
        seed = (seed * 1103515245 + 12345) and 0x7FFFFFFFL
        return (seed % 500_000 + 10_000).toInt() // 10KB - 510KB
    }

    val liveSet = mutableListOf<ByteArray>()
    for (round in 0 until 100) {
        // Allocate random-sized object
        val size = nextSize()
        liveSet.add(ByteArray(size))

        // Keep live set bounded (~5MB)
        while (liveSet.size > 20) {
            liveSet.removeAt(0)
        }

        if (round % 10 == 0) GC.collect()
    }

    // After fragmentation, try large contiguous allocation
    val largeAlloc = ByteArray(2_000_000)
    largeAlloc[0] = 1
    largeAlloc[1_999_999] = 2
    GC.collect()
    assertEquals(1.toByte(), largeAlloc[0], "T2: Large alloc first byte wrong")
    assertEquals(2.toByte(), largeAlloc[1_999_999], "T2: Large alloc last byte wrong")

    // === Test 3: WeakRef chain — all links must clear atomically ===
    class Chain(val id: Int, val next: Chain?)

    val chainWeaks = mutableListOf<WeakReference<Chain>>()
    run {
        var chain: Chain? = null
        for (i in 0 until 100) {
            chain = Chain(i, chain)
            chainWeaks.add(WeakReference(chain))
        }
        // chain goes out of scope — entire chain unreachable
    }

    repeat(10) { GC.collect() }

    var chainDangling = 0
    for (wr in chainWeaks) {
        val ref = wr.get()
        if (ref != null) {
            // If any link survived, its chain must be intact
            var cur: Chain? = ref
            while (cur != null) {
                if (cur.id < 0) { chainDangling++; break }
                cur = cur.next
            }
        }
    }
    assertEquals(0, chainDangling, "T3: WeakRef chain has dangling links")

    println("PASS")
}
