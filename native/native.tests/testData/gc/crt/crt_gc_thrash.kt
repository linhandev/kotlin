// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests three concurrent allocation strategies that stress-test write barriers under load:
// - Robin: round-robin array slot replacement (card table stress)
// - Deep: recursive stack allocation with strong/weak ref pairing
// - Large: large byte array allocation (LOH stress)

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

const val THRASH_ROUNDS = 200
const val ROBIN_ARRAY_SIZE = 4096

val thrashErrors = AtomicInt(0)

@Test fun testGcThrash() {
    val workers = Array(3) { Worker.start() }

    // Worker 0: Robin — round-robin replacement in large array
    val robinFuture = workers[0].execute(kotlin.native.concurrent.TransferMode.SAFE, { thrashErrors }) { errors ->
        val strings = arrayOfNulls<String>(ROBIN_ARRAY_SIZE)
        var idx = 0
        for (round in 0 until THRASH_ROUNDS) {
            for (i in 0 until ROBIN_ARRAY_SIZE / 10) {
                strings[idx] = "Robin-$round-$idx"
                idx = (idx + 1) % ROBIN_ARRAY_SIZE
            }
            if (round % 20 == 0) GC.collect()
        }
        // Verify surviving entries
        for (i in strings.indices) {
            val s = strings[i]
            if (s != null && !s.startsWith("Robin-")) {
                errors.incrementAndGet()
            }
        }
        0
    }

    // Worker 1: Deep — recursive allocation with strong/weak pairing
    val deepFuture = workers[1].execute(kotlin.native.concurrent.TransferMode.SAFE, { thrashErrors }) { errors ->
        val maxDepth = 40
        val strong = arrayOfNulls<String>(maxDepth)
        val weak = arrayOfNulls<WeakReference<String>>(maxDepth)

        for (iter in 0 until THRASH_ROUNDS / 5) {
            // Build deep stack with paired strong/weak refs
            fun dive(depth: Int, iteration: Int) {
                val str = "Deep-${iteration + depth}"
                strong[depth] = str
                weak[depth] = WeakReference(str)
                if (depth + 1 < maxDepth) {
                    dive(depth + 1, iteration + 1)
                } else {
                    GC.collect()
                }
            }
            dive(0, iter * maxDepth)

            // Verify strong == weak.get() for all entries
            for (i in 0 until maxDepth) {
                val w = weak[i]?.get()
                if (w != null && w != strong[i]) {
                    errors.incrementAndGet()
                }
            }

            // Wipe strong, GC, verify weak gets cleared
            for (i in 0 until maxDepth) strong[i] = null
            GC.collect()
            for (i in 0 until maxDepth) {
                val w = weak[i]?.get()
                if (w != null && !w.startsWith("Deep-")) {
                    // If still alive, must be valid (not dangling)
                    errors.incrementAndGet()
                }
            }
        }
        0
    }

    // Worker 2: Large — big buffer allocation
    val largeFuture = workers[2].execute(kotlin.native.concurrent.TransferMode.SAFE, { thrashErrors }) { errors ->
        for (round in 0 until THRASH_ROUNDS) {
            val chunk = ByteArray(100_000) { (it % 256).toByte() }
            // Spot-check
            if (chunk[0] != 0.toByte() || chunk[99_999] != (99_999 % 256).toByte()) {
                errors.incrementAndGet()
            }
            if (round % 50 == 0) GC.collect()
        }
        0
    }

    robinFuture.result
    deepFuture.result
    largeFuture.result
    for (w in workers) w.requestTermination().result

    assertEquals(0, thrashErrors.value, "GC thrash errors detected")
    println("PASS")
}
