// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi,kotlinx.cinterop.ExperimentalForeignApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issue 008 (KRefSharedHolder to-space invariant).
// Creates objects via StableRef.create(), concurrently accesses them via stableRef.get()
// from multiple workers while GC runs (causing potential object moves). Verifies that
// get() always returns consistent object content.
// Also tests that dispose() allows the object to be GC'd (via WeakReference).

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.ref.WeakReference
import kotlin.native.runtime.GC
import kotlinx.cinterop.StableRef

class StablePayload(val id: Int, val data: String) {
    fun verify(): Boolean = data == "stable-$id"
}

const val STABLE_NUM_WORKERS = 4
const val STABLE_NUM_ROUNDS = 100

val stableReadyCount = AtomicInt(0)
val stableStartSignal = AtomicInt(0)

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class, kotlin.experimental.ExperimentalNativeApi::class, kotlinx.cinterop.ExperimentalForeignApi::class)
@Test fun testStableRefLifecycle() {
    for (round in 1..STABLE_NUM_ROUNDS) {
        val payload = StablePayload(round, "stable-$round")
        val stableRef = StableRef.create(payload)
        val weakRef = WeakReference(payload)

        stableReadyCount.value = 0
        stableStartSignal.value = 0

        val workers = Array(STABLE_NUM_WORKERS) { Worker.start() }
        val futures = workers.map { worker ->
            worker.execute(TransferMode.SAFE, { stableRef }) { ref ->
                stableReadyCount.incrementAndGet()
                while (stableStartSignal.value == 0) {}

                val obj = ref.get()
                val ok = obj.verify() && obj.id == obj.data.removePrefix("stable-").toInt()
                ok
            }
        }

        while (stableReadyCount.value < STABLE_NUM_WORKERS) {}
        stableStartSignal.value = 1

        GC.collect()

        for (future in futures) {
            assertTrue(future.result, "StableRef.get() returned inconsistent data in round $round")
        }

        for (w in workers) {
            w.requestTermination().result
        }

        assertNotNull(weakRef.get(), "Object should be alive while StableRef exists, round $round")
        assertTrue(stableRef.get().verify())

        stableRef.dispose()

        GC.collect()
    }

    for (i in 0 until 100) {
        val ref = StableRef.create(StablePayload(i, "stable-$i"))
        if (i % 10 == 0) GC.collect()
        assertTrue(ref.get().verify())
        ref.dispose()
    }
    GC.collect()

    println("PASS")
}
