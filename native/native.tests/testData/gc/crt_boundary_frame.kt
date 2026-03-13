// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issue 002 (boundary frame scanning).
// Tests that objects held on stack across frame boundaries (Worker tasks, nested GC)
// survive GC. Worker.execute creates R2K (Runtime→Kotlin) boundary frames, which
// exercises the FrameKind-based boundary traversal system.

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.runtime.GC

class BoundaryPayload(val depth: Int, val tag: String) {
    fun verify(): Boolean = tag == "depth-$depth"
}

const val MAX_DEPTH = 30
const val BOUNDARY_ROUNDS = 50
const val BF_NUM_WORKERS = 4

val bfReadyCount = AtomicInt(0)
val bfStartSignal = AtomicInt(0)

// Test 1: Deep recursion with GC at max depth — basic stack scanning
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun callAtDepth(depth: Int, maxDepth: Int, payloads: Array<BoundaryPayload?>): Boolean {
    val local = BoundaryPayload(depth, "depth-$depth")
    payloads[depth] = local

    if (depth < maxDepth) {
        val ok = callAtDepth(depth + 1, maxDepth, payloads)
        if (!ok) return false
    } else {
        GC.collect()
    }

    if (!local.verify()) return false
    if (payloads[depth] !== local) return false
    return true
}

// Test 2: GC at every depth level
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun gcAtEachDepth(current: Int, maxDepth: Int): Boolean {
    val local = BoundaryPayload(current, "depth-$current")

    if (current < maxDepth) {
        GC.collect()
        if (!local.verify()) return false

        val ok = gcAtEachDepth(current + 1, maxDepth)
        if (!ok) return false

        GC.collect()
        if (!local.verify()) return false
    }

    return local.verify()
}

// Test 3: Worker boundary frames — Worker.execute creates R2K frame boundaries.
// Objects allocated in main thread are accessed in worker after GC.
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun workerBoundaryTest(round: Int): Boolean {
    // Allocate objects on main thread stack, pass to workers via array
    val payloads = Array(BF_NUM_WORKERS * MAX_DEPTH) { i ->
        BoundaryPayload(i, "depth-$i")
    }

    bfReadyCount.value = 0
    bfStartSignal.value = 0

    val workers = Array(BF_NUM_WORKERS) { Worker.start() }
    val futures = workers.mapIndexed { index, worker ->
        worker.execute(TransferMode.SAFE, { Pair(payloads, index) }) { (p, idx) ->
            bfReadyCount.incrementAndGet()
            while (bfStartSignal.value == 0) {}

            // Inside worker: R2K boundary frame is active.
            // Allocate locals that must survive GC.
            val workerLocals = Array(MAX_DEPTH) { d ->
                BoundaryPayload(d, "depth-$d")
            }

            // Trigger GC from within worker boundary frame
            GC.collect()

            // Verify worker-local objects survived GC
            var ok = true
            for (d in 0 until MAX_DEPTH) {
                if (!workerLocals[d].verify()) { ok = false; break }
            }

            // Verify shared objects are accessible across boundary
            val base = idx * MAX_DEPTH
            for (d in 0 until MAX_DEPTH) {
                if (!p[base + d].verify()) { ok = false; break }
            }

            ok
        }
    }

    while (bfReadyCount.value < BF_NUM_WORKERS) {}
    bfStartSignal.value = 1

    // Main thread also triggers GC while workers are running
    GC.collect()

    var allOk = true
    for (future in futures) {
        if (!future.result) allOk = false
    }

    for (w in workers) {
        w.requestTermination().result
    }

    // Verify main thread's payloads survived
    for (p in payloads) {
        if (!p.verify()) allOk = false
    }

    return allOk
}

// Test 4: Nested worker tasks — multiple R2K boundary frames on same thread.
// Worker creates sub-objects at different depths, each requiring frame boundary handling.
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun nestedWorkerGCTest(round: Int): Boolean {
    val worker = Worker.start()
    val future = worker.execute(TransferMode.SAFE, { round }) { r ->
        // Level 1: Worker task boundary (R2K)
        val l1 = BoundaryPayload(1, "depth-1")

        // Allocate a chain of objects that must all survive concurrent GC
        val chain = Array(20) { i -> BoundaryPayload(i, "depth-$i") }

        GC.collect()

        // Verify everything survived after GC
        if (!l1.verify()) return@execute false

        for (obj in chain) {
            if (!obj.verify()) return@execute false
        }

        // Do multiple rounds of allocate-GC-verify within the same boundary frame
        for (i in 0 until 10) {
            val temp = Array(50) { j -> BoundaryPayload(j, "depth-$j") }
            GC.collect()
            for (t in temp) {
                if (!t.verify()) return@execute false
            }
        }

        true
    }

    val result = future.result
    worker.requestTermination().result
    return result
}

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testBoundaryFrame() {
    for (round in 1..BOUNDARY_ROUNDS) {
        // Test 1: GC at deepest frame, verify all stack locals survive
        val payloads = arrayOfNulls<BoundaryPayload>(MAX_DEPTH + 1)
        assertTrue(callAtDepth(0, MAX_DEPTH, payloads), "callAtDepth failed in round $round")

        for (i in 0..MAX_DEPTH) {
            assertNotNull(payloads[i], "Payload at depth $i is null in round $round")
            assertTrue(payloads[i]!!.verify(), "Payload at depth $i corrupted in round $round")
        }

        // Test 2: GC at every depth level
        assertTrue(gcAtEachDepth(0, MAX_DEPTH), "gcAtEachDepth failed in round $round")

        // Test 3: Worker boundary frames with concurrent GC
        assertTrue(workerBoundaryTest(round), "workerBoundaryTest failed in round $round")

        // Test 4: Nested GC within worker boundary frame
        assertTrue(nestedWorkerGCTest(round), "nestedWorkerGCTest failed in round $round")
    }

    println("PASS")
}
