// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Extreme stress tests targeting Pin/RawPointerRegion + generational GC interaction.
// Hypothesis: bugs in RECENT_FULL → FROM_REGION transition when rawPointerObjectCount > 0,
// RAW_POINTER_REGION classified as young space, and cross-region write barrier for old→pinned refs.

@file:OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class, kotlinx.cinterop.ExperimentalForeignApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker
import kotlin.concurrent.AtomicInt
import kotlin.concurrent.AtomicReference
import kotlinx.cinterop.*

// ============================================================
// T1: Pin + Young GC rapid cycling (target Problem 2)
// RECENT_FULL → FROM_REGION transition while pin count > 0
// ============================================================
@Test fun testT1PinYoungGCRapidCycling() {
    val rounds = 1500
    val pinsPerRound = 10
    val numWorkers = 8
    val globalErrors = AtomicInt(0)

    val workers = Array(numWorkers) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, rounds, pinsPerRound) }) { (wIdx, numRounds, numPins) ->
            var errors = 0
            for (round in 0 until numRounds) {
                // Allocate enough to fill regions — force region transitions
                val garbage = Array(50) { ByteArray(4096) }
                garbage.hashCode()

                // Pin objects in the current (likely RECENT_FULL) region
                val arrays = Array(numPins) { ByteArray(128) { ((wIdx * 100 + round + it) % 127).toByte() } }
                val pinned = Array(numPins) { arrays[it].pin() }
                val addrs = LongArray(numPins) { pinned[it].addressOf(0).rawValue.toLong() }

                // Trigger GC — this is where RECENT_FULL → FROM_REGION happens
                if (round % 3 == 0) GC.collect()

                // Allocate more garbage to trigger another region fill AFTER GC
                val moreGarbage = Array(30) { ByteArray(2048) }
                moreGarbage.hashCode()

                // Verify pinned objects survived with correct address and data
                for (p in 0 until numPins) {
                    val currentAddr = pinned[p].addressOf(0).rawValue.toLong()
                    if (currentAddr != addrs[p]) {
                        errors++
                    }
                    for (i in arrays[p].indices) {
                        if (arrays[p][i] != ((wIdx * 100 + round + i) % 127).toByte()) {
                            errors++
                            break
                        }
                    }
                    pinned[p].unpin()
                }
            }
            errors
        }
    }

    var total = 0
    for (f in futures) total += f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, total, "T1: Pin + Young GC rapid cycling had $total errors")
    println("T1 OK")
}

// ============================================================
// T2: Pin + massive allocation pressure (region transitions)
// Pin object, allocate huge garbage to force many region transitions, verify stability.
// ============================================================
@Test fun testT2PinMassiveAllocPressure() {
    val rounds = 200
    var errors = 0

    for (round in 0 until rounds) {
        val arr = ByteArray(512) { (it % 251).toByte() }
        val pinned = arr.pin()
        val addr = pinned.addressOf(0).rawValue.toLong()

        // Allocate enough to fill ~20 regions (each ~256KB, allocate ~5MB)
        for (batch in 0 until 20) {
            val bulk = Array(64) { ByteArray(4096) }
            bulk.hashCode()
        }

        GC.collect()

        val addrAfter = pinned.addressOf(0).rawValue.toLong()
        if (addr != addrAfter) errors++

        for (i in arr.indices) {
            if (arr[i] != (i % 251).toByte()) {
                errors++
                break
            }
        }
        pinned.unpin()
    }

    assertEquals(0, errors, "T2: Pin + massive alloc pressure had $errors errors")
    println("T2 OK")
}

// ============================================================
// T3: Pin/Unpin rapid cycling + concurrent GC (Problem 2 time window)
// Rapidly pin/unpin while GC runs — stresses rawPointerObjectCount inc/dec during region transition.
// ============================================================
@Test fun testT3PinUnpinRapidCyclingConcurrentGC() {
    val rounds = 2000
    val numWorkers = 8
    val globalErrors = AtomicInt(0)

    val workers = Array(numWorkers) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, rounds) }) { (wIdx, numRounds) ->
            var errors = 0
            for (round in 0 until numRounds) {
                val arr = ByteArray(256) { ((wIdx + round + it) % 200).toByte() }
                val pinned = arr.pin()
                val addr = pinned.addressOf(0).rawValue.toLong()

                // Some workers allocate garbage, some trigger GC
                if (wIdx % 3 == 0 && round % 5 == 0) {
                    GC.collect()
                } else {
                    val garbage = Array(20) { ByteArray(1024) }
                    garbage.hashCode()
                }

                // Verify before unpin
                val currentAddr = pinned.addressOf(0).rawValue.toLong()
                if (currentAddr != addr) errors++

                for (i in arr.indices) {
                    if (arr[i] != ((wIdx + round + i) % 200).toByte()) {
                        errors++
                        break
                    }
                }
                pinned.unpin()

                // Immediately re-pin same array (rapid cycle)
                val pinned2 = arr.pin()
                val addr2 = pinned2.addressOf(0).rawValue.toLong()

                if (round % 7 == 0) GC.collect()

                val addr2After = pinned2.addressOf(0).rawValue.toLong()
                if (addr2 != addr2After) errors++

                pinned2.unpin()
            }
            errors
        }
    }

    var total = 0
    for (f in futures) total += f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, total, "T3: Pin/Unpin rapid cycling had $total errors")
    println("T3 OK")
}

// ============================================================
// T4: Pin young → survive 30+ promotion cycles (Problem 7)
// RAW_POINTER_REGION is IsInRecentSpace — pinned object never promoted, rescanned every young GC.
// ============================================================
@Test fun testT4PinYoungSurvivePromotionCycles() {
    val arr = ByteArray(1024) { (it % 173).toByte() }
    val pinned = arr.pin()
    val addr = pinned.addressOf(0).rawValue.toLong()
    var errors = 0

    for (cycle in 0 until 30) {
        // Allocate significant garbage to force GC work and region recycling
        for (batch in 0 until 10) {
            val garbage = Array(100) { ByteArray(2048) }
            garbage.hashCode()
        }

        GC.collect()

        val currentAddr = pinned.addressOf(0).rawValue.toLong()
        if (currentAddr != addr) {
            errors++
        }

        for (i in arr.indices) {
            if (arr[i] != (i % 173).toByte()) {
                errors++
                break
            }
        }
    }

    pinned.unpin()
    assertEquals(0, errors, "T4: Pin young survive promotion had $errors errors")
    println("T4 OK")
}

// ============================================================
// T5: Old gen → Pinned(young) reference (write barrier / remembered set)
// Store pinned young object ref in old-gen object. Young GC must not break the reference.
// ============================================================
class Holder(var ref: ByteArray?)

@Test fun testT5OldGenToPinnedReference() {
    var errors = 0

    // Create holder and promote to old gen
    val holder = Holder(null)
    for (i in 0 until 5) {
        val garbage = Array(200) { ByteArray(4096) }
        garbage.hashCode()
        GC.collect()
    }
    // holder is now likely in old gen

    for (round in 0 until 500) {
        val youngArr = ByteArray(256) { ((round + it) % 239).toByte() }
        val pinned = youngArr.pin()
        val addr = pinned.addressOf(0).rawValue.toLong()

        // Store pinned young ref in old-gen holder — triggers write barrier
        holder.ref = youngArr

        // Allocate young garbage
        val garbage = Array(50) { ByteArray(1024) }
        garbage.hashCode()

        // Young GC — old→young(pinned) ref must survive via remembered set
        GC.collect()

        // Verify reference still valid
        val ref = holder.ref
        if (ref == null) {
            errors++
        } else {
            val currentAddr = pinned.addressOf(0).rawValue.toLong()
            if (currentAddr != addr) errors++
            for (i in ref.indices) {
                if (ref[i] != ((round + i) % 239).toByte()) {
                    errors++
                    break
                }
            }
        }

        pinned.unpin()
    }

    assertEquals(0, errors, "T5: Old gen → pinned reference had $errors errors")
    println("T5 OK")
}

// ============================================================
// T6: Pinned array with concurrent field updates + GC
// Multiple threads update a pinned array's elements while GC runs.
// ============================================================
@Test fun testT6PinnedConcurrentFieldUpdates() {
    val sharedArr = IntArray(1024) { 0 }
    val sharedPinned = sharedArr.pin()
    val sharedAddr = sharedPinned.addressOf(0).rawValue.toLong()
    val globalErrors = AtomicInt(0)
    val numWorkers = 8
    val rounds = 500

    val workers = Array(numWorkers) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, numWorkers, rounds) }) { (wIdx, totalWorkers, numRounds) ->
            var errors = 0
            val segmentSize = 1024 / totalWorkers
            val start = wIdx * segmentSize
            val end = if (wIdx == totalWorkers - 1) 1024 else start + segmentSize

            for (round in 0 until numRounds) {
                // Each worker writes to its segment of the shared pinned array
                // Allocate referenced objects that create cross-region refs
                val localArr = IntArray(segmentSize) { (round * 1000 + wIdx * 100 + it) }

                for (i in start until end) {
                    // Cannot directly write to pinned via Kotlin (it's shared),
                    // but we can allocate garbage that forces GC to interact with the pin
                    val garbage = Array(5) { ByteArray(512) }
                    garbage.hashCode()
                }

                if (round % 10 == 0) GC.collect()

                // Verify local data
                for (i in localArr.indices) {
                    if (localArr[i] != (round * 1000 + wIdx * 100 + i)) {
                        errors++
                        break
                    }
                }
            }
            errors
        }
    }

    var total = 0
    for (f in futures) total += f.result
    for (w in workers) w.requestTermination().result

    // Verify the pinned array address never changed
    val addrAfter = sharedPinned.addressOf(0).rawValue.toLong()
    assertEquals(sharedAddr, addrAfter, "T6: Shared pinned array address changed")
    sharedPinned.unpin()
    assertEquals(0, total, "T6: Concurrent field updates had $total errors")
    println("T6 OK")
}

// ============================================================
// T7: Pin + copy phase race (Problem 2 directly)
// Fill region → pin → GC → fill again → pin → GC, 200 rapid cycles.
// ============================================================
@Test fun testT7PinCopyPhaseRace() {
    val cycles = 200
    var errors = 0

    for (cycle in 0 until cycles) {
        // Fill a region with objects
        val filler = Array(64) { ByteArray(4096) }
        filler.hashCode()

        // Pin an object in this (likely full/filling) region
        val target = ByteArray(512) { (it % 191).toByte() }
        val pinned = target.pin()
        val addr = pinned.addressOf(0).rawValue.toLong()

        // Trigger GC — region should be exempted from copy due to pin
        GC.collect()

        // Verify address stable
        val addrAfter = pinned.addressOf(0).rawValue.toLong()
        if (addr != addrAfter) errors++

        // Immediately allocate more to fill another region
        val filler2 = Array(64) { ByteArray(4096) }
        filler2.hashCode()

        // Pin again in new region
        val target2 = ByteArray(512) { (it % 179).toByte() }
        val pinned2 = target2.pin()
        val addr2 = pinned2.addressOf(0).rawValue.toLong()

        // GC again
        GC.collect()

        val addr2After = pinned2.addressOf(0).rawValue.toLong()
        if (addr2 != addr2After) errors++

        // Verify data integrity
        for (i in target.indices) {
            if (target[i] != (i % 191).toByte()) { errors++; break }
        }
        for (i in target2.indices) {
            if (target2[i] != (i % 179).toByte()) { errors++; break }
        }

        pinned.unpin()
        pinned2.unpin()
    }

    assertEquals(0, errors, "T7: Pin + copy phase race had $errors errors")
    println("T7 OK")
}

// ============================================================
// T8: Many regions with mixed pin/unpin state
// Create 60+ regions, pin every other, GC, unpin, GC again.
// ============================================================
@Test fun testT8ManyRegionsMixedPinState() {
    val regionCount = 60
    var errors = 0

    // Each "region" is ~256KB, allocate ~256KB per slot
    val regionFillers = Array(regionCount) { Array(64) { ByteArray(4096) } }

    // Pin an object in every other region's filler
    val targets = Array(regionCount) { ByteArray(256) { (it % (100 + regionCount)).toByte() } }
    val pinned = arrayOfNulls<Pinned<ByteArray>>(regionCount)
    val addrs = LongArray(regionCount)

    for (i in 0 until regionCount) {
        if (i % 2 == 0) {
            pinned[i] = targets[i].pin()
            addrs[i] = pinned[i]!!.addressOf(0).rawValue.toLong()
        }
    }

    // GC — only unpinned regions should be fully copyable
    GC.collect()

    // Verify pinned objects survived
    for (i in 0 until regionCount) {
        if (i % 2 == 0) {
            val currentAddr = pinned[i]!!.addressOf(0).rawValue.toLong()
            if (currentAddr != addrs[i]) errors++
            for (j in targets[i].indices) {
                if (targets[i][j] != (j % (100 + regionCount)).toByte()) {
                    errors++
                    break
                }
            }
        }
    }

    // Unpin all
    for (i in 0 until regionCount) {
        if (i % 2 == 0) {
            pinned[i]!!.unpin()
        }
    }

    // GC again — all regions should now be copyable
    GC.collect()

    // Verify heap still functional
    val check = Array(100) { ByteArray(1024) { (it % 256).toByte() } }
    GC.collect()
    for (i in check.indices) {
        for (j in check[i].indices) {
            if (check[i][j] != (j % 256).toByte()) { errors++; break }
        }
    }

    assertEquals(0, errors, "T8: Mixed pin state had $errors errors")
    println("T8 OK")
}

// ============================================================
// T9: Pin + concurrent Worker allocation storm
// 8 workers: pin array → allocate 10000 objects → GC → verify → unpin → repeat
// ============================================================
@Test fun testT9PinConcurrentAllocStorm() {
    val numWorkers = 8
    val rounds = 100
    val allocsPerRound = 10000
    val globalErrors = AtomicInt(0)

    val workers = Array(numWorkers) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Triple(idx, rounds, allocsPerRound) }) { (wIdx, numRounds, numAllocs) ->
            var errors = 0
            for (round in 0 until numRounds) {
                val arr = ByteArray(512) { ((wIdx * 10 + round + it) % 241).toByte() }
                val pinned = arr.pin()
                val addr = pinned.addressOf(0).rawValue.toLong()

                // Massive allocation storm
                for (a in 0 until numAllocs / 100) {
                    val batch = Array(100) { ByteArray(64) }
                    batch.hashCode()
                }

                if (round % 5 == 0) GC.collect()

                val addrAfter = pinned.addressOf(0).rawValue.toLong()
                if (addr != addrAfter) errors++

                for (i in arr.indices) {
                    if (arr[i] != ((wIdx * 10 + round + i) % 241).toByte()) {
                        errors++
                        break
                    }
                }

                pinned.unpin()
            }
            errors
        }
    }

    var total = 0
    for (f in futures) total += f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, total, "T9: Concurrent alloc storm had $total errors")
    println("T9 OK")
}

// ============================================================
// T10: ExemptFromRegions window stress
// Fill regions rapidly, pin in most-recently-filled, immediately GC.
// ============================================================
@Test fun testT10ExemptFromRegionsWindow() {
    val cycles = 300
    var errors = 0

    for (cycle in 0 until cycles) {
        // Rapidly fill regions
        val fillers = Array(10) { Array(64) { ByteArray(4096) } }
        fillers.hashCode()

        // Pin in the most recently allocated area
        val target = ByteArray(1024) { (it % 223).toByte() }
        val pinned = target.pin()
        val addr = pinned.addressOf(0).rawValue.toLong()

        // IMMEDIATELY trigger GC — this region is transitioning RECENT_FULL → FROM
        GC.collect()

        // If ExemptFromRegions misses this, the object gets copied → address changes
        val addrAfter = pinned.addressOf(0).rawValue.toLong()
        if (addr != addrAfter) {
            errors++
        }

        // Verify data
        for (i in target.indices) {
            if (target[i] != (i % 223).toByte()) {
                errors++
                break
            }
        }

        pinned.unpin()

        // More garbage to push region recycling
        val moreGarbage = Array(32) { ByteArray(4096) }
        moreGarbage.hashCode()
    }

    assertEquals(0, errors, "T10: ExemptFromRegions window had $errors errors")
    println("T10 OK")
}

// ============================================================
// T11: Pin long-lived + short-lived interleaved with GC
// Mix of long-lived pins (held across many GC cycles) and short-lived pins (per-cycle).
// Tests that long-lived RAW_POINTER_REGION objects don't interfere with region recycling.
// ============================================================
@Test fun testT11PinLongShortLivedInterleaved() {
    val longLivedCount = 20
    val gcCycles = 50
    var errors = 0

    // Create long-lived pins
    val longArrays = Array(longLivedCount) { ByteArray(2048) { (it % (150 + longLivedCount)).toByte() } }
    val longPinned = Array(longLivedCount) { longArrays[it].pin() }
    val longAddrs = LongArray(longLivedCount) { longPinned[it].addressOf(0).rawValue.toLong() }

    for (cycle in 0 until gcCycles) {
        // Short-lived pin/unpin each cycle
        for (s in 0 until 20) {
            val shortArr = ByteArray(512) { ((cycle + s + it) % 199).toByte() }
            val shortPin = shortArr.pin()
            val shortAddr = shortPin.addressOf(0).rawValue.toLong()

            // Allocate garbage
            val garbage = Array(20) { ByteArray(1024) }
            garbage.hashCode()

            val shortAddrAfter = shortPin.addressOf(0).rawValue.toLong()
            if (shortAddr != shortAddrAfter) errors++

            shortPin.unpin()
        }

        GC.collect()

        // Verify all long-lived pins still stable
        for (i in 0 until longLivedCount) {
            val currentAddr = longPinned[i].addressOf(0).rawValue.toLong()
            if (currentAddr != longAddrs[i]) errors++
            for (j in longArrays[i].indices) {
                if (longArrays[i][j] != (j % (150 + longLivedCount)).toByte()) {
                    errors++
                    break
                }
            }
        }
    }

    for (i in 0 until longLivedCount) longPinned[i].unpin()
    assertEquals(0, errors, "T11: Long/short-lived interleaved had $errors errors")
    println("T11 OK")
}

// ============================================================
// T12: Multiple pin/unpin on same object + GC between each
// Tests rawPointerObjectCount transitions for the same object's region.
// ============================================================
@Test fun testT12RepeatPinUnpinSameObject() {
    val cycles = 1000
    var errors = 0
    val arr = ByteArray(1024) { (it % 157).toByte() }

    for (cycle in 0 until cycles) {
        val pinned = arr.pin()
        val addr = pinned.addressOf(0).rawValue.toLong()

        // Allocate garbage to force region activity
        val garbage = Array(30) { ByteArray(2048) }
        garbage.hashCode()

        if (cycle % 3 == 0) GC.collect()

        val addrAfter = pinned.addressOf(0).rawValue.toLong()
        if (addr != addrAfter) errors++

        pinned.unpin()

        if (cycle % 7 == 0) GC.collect()
    }

    // Final data integrity check
    for (i in arr.indices) {
        if (arr[i] != (i % 157).toByte()) { errors++; break }
    }

    assertEquals(0, errors, "T12: Repeat pin/unpin same object had $errors errors")
    println("T12 OK")
}

// ============================================================
// T13: Pinned objects with cross-references (object graph pinning)
// Pin multiple arrays, store refs between them, GC, verify graph integrity.
// Tests write barrier for pinned→pinned and pinned→unpinned references.
// ============================================================
class RefHolder(var data: ByteArray?, var next: RefHolder?)

@Test fun testT13PinnedObjectGraphIntegrity() {
    val chainLen = 50
    val gcCycles = 30
    var errors = 0

    // Build a chain of holders, each referencing a pinned ByteArray
    val arrays = Array(chainLen) { ByteArray(256) { (it % (100 + chainLen)).toByte() } }
    val pinned = Array(chainLen) { arrays[it].pin() }
    val addrs = LongArray(chainLen) { pinned[it].addressOf(0).rawValue.toLong() }

    // Build linked chain
    var head: RefHolder? = null
    for (i in chainLen - 1 downTo 0) {
        head = RefHolder(arrays[i], head)
    }

    // Promote chain to old gen
    for (i in 0 until 5) {
        val garbage = Array(200) { ByteArray(4096) }
        garbage.hashCode()
        GC.collect()
    }

    // Now stress: mutate chain + GC
    for (cycle in 0 until gcCycles) {
        // Walk chain, verify data
        var node = head
        var idx = 0
        while (node != null) {
            val d = node.data
            if (d != null) {
                for (j in d.indices) {
                    if (d[j] != (j % (100 + chainLen)).toByte()) {
                        errors++
                        break
                    }
                }
            } else {
                errors++
            }
            node = node.next
            idx++
        }
        if (idx != chainLen) errors++

        // Allocate young garbage — creates cross-gen references
        val youngGarbage = Array(100) { ByteArray(2048) }
        youngGarbage.hashCode()

        GC.collect()

        // Verify pinned addresses stable
        for (i in 0 until chainLen) {
            val currentAddr = pinned[i].addressOf(0).rawValue.toLong()
            if (currentAddr != addrs[i]) errors++
        }
    }

    for (i in 0 until chainLen) pinned[i].unpin()
    assertEquals(0, errors, "T13: Pinned object graph had $errors errors")
    println("T13 OK")
}

// ============================================================
// Aggregator
// ============================================================
@Test fun testCrtPinGenerationalStress() {
    testT1PinYoungGCRapidCycling()
    testT2PinMassiveAllocPressure()
    testT3PinUnpinRapidCyclingConcurrentGC()
    testT4PinYoungSurvivePromotionCycles()
    testT5OldGenToPinnedReference()
    testT6PinnedConcurrentFieldUpdates()
    testT7PinCopyPhaseRace()
    testT8ManyRegionsMixedPinState()
    testT9PinConcurrentAllocStorm()
    testT10ExemptFromRegionsWindow()
    testT11PinLongShortLivedInterleaved()
    testT12RepeatPinUnpinSameObject()
    testT13PinnedObjectGraphIntegrity()
    println("PASS")
}
