// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issue 006 (CRT destruction order deadlock/abort).
// Starts multiple worker threads that continuously allocate objects and trigger GC.
// Main thread exits normally without waiting for all workers, verifying no abort or deadlock.

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.runtime.GC

class AllocPayload(val id: Int) {
    val data = IntArray(64) { it * id }
}

const val SHUTDOWN_WORKERS = 6
const val SHUTDOWN_ITERATIONS = 500

val shutdownCompleted = AtomicInt(0)

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testDaemonShutdown() {
    shutdownCompleted.value = 0

    val workers = Array(SHUTDOWN_WORKERS) { Worker.start() }
    val futures = workers.mapIndexed { index, worker ->
        worker.execute(TransferMode.SAFE, { index }) { idx ->
            for (i in 0 until SHUTDOWN_ITERATIONS) {
                val payload = AllocPayload(idx * SHUTDOWN_ITERATIONS + i)
                val sum = payload.data.sum()
                if (sum < 0 && i > SHUTDOWN_ITERATIONS) {
                    throw RuntimeException("unexpected")
                }

                if (i % 50 == 0) {
                    GC.collect()
                }
            }
            shutdownCompleted.incrementAndGet()
            true
        }
    }

    for (future in futures) {
        assertTrue(future.result, "Worker failed")
    }

    assertEquals(SHUTDOWN_WORKERS, shutdownCompleted.value)

    for (w in workers) {
        w.requestTermination().result
    }

    val w1 = Worker.start()
    val w2 = Worker.start()

    val f1 = w1.execute(TransferMode.SAFE, { null }) {
        val objs = Array(100) { AllocPayload(it) }
        GC.collect()
        for (obj in objs) {
            if (obj.data[0] != 0) throw RuntimeException("data corrupted")
        }
        true
    }

    val f2 = w2.execute(TransferMode.SAFE, { null }) {
        val objs = Array(100) { AllocPayload(it + 1000) }
        GC.collect()
        for (obj in objs) {
            if (obj.data.size != 64) throw RuntimeException("array size wrong")
        }
        true
    }

    assertTrue(f1.result)
    assertTrue(f2.result)
    w1.requestTermination().result
    w2.requestTermination().result

    println("PASS")
}
