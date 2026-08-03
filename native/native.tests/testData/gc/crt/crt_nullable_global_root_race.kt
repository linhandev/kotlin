// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for a concurrent root-scan TOCTOU in CMC GC.
// Writers repeatedly change volatile Kotlin global-root slots from heap objects to null
// while the test forces GC. A null root must be rejected before querying its RegionDesc.

import kotlin.concurrent.AtomicInt
import kotlin.concurrent.Volatile
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.runtime.GC
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private class NullableGlobalRootPayload(val sequence: Int) {
    private val checksum = sequence.toLong() * 31L + 7L

    fun isValid(): Boolean = checksum == sequence.toLong() * 31L + 7L
}

@Volatile
private var concurrentNullableRoot0: NullableGlobalRootPayload? = null

@Volatile
private var concurrentNullableRoot1: NullableGlobalRootPayload? = null

@Volatile
private var concurrentNullableRoot2: NullableGlobalRootPayload? = null

@Volatile
private var concurrentNullableRoot3: NullableGlobalRootPayload? = null

private const val NULLABLE_ROOT_WRITER_COUNT = 4
private const val NULLABLE_ROOT_GC_CYCLES = 10_000

private fun publishThenClearNullableRoots(sequence: Int) {
    concurrentNullableRoot0 = NullableGlobalRootPayload(sequence)
    concurrentNullableRoot1 = NullableGlobalRootPayload(sequence + 1)
    concurrentNullableRoot2 = NullableGlobalRootPayload(sequence + 2)
    concurrentNullableRoot3 = NullableGlobalRootPayload(sequence + 3)

    // KNRootsVisitor may have already passed IsHeapAddress for one of these slots.
    // Clearing it here races with the collector visitor's second read.
    concurrentNullableRoot0 = null
    concurrentNullableRoot1 = null
    concurrentNullableRoot2 = null
    concurrentNullableRoot3 = null
}

private fun nullableRootsAreValid(): Boolean {
    val root0 = concurrentNullableRoot0
    val root1 = concurrentNullableRoot1
    val root2 = concurrentNullableRoot2
    val root3 = concurrentNullableRoot3
    return (root0 == null || root0.isValid()) &&
        (root1 == null || root1.isValid()) &&
        (root2 == null || root2.isValid()) &&
        (root3 == null || root3.isValid())
}

@Test
fun testConcurrentNullableGlobalRootRace() {
    val running = AtomicInt(1)
    val readyCount = AtomicInt(0)
    val updateCount = AtomicInt(0)
    val errorCount = AtomicInt(0)

    val writers = Array(NULLABLE_ROOT_WRITER_COUNT) { Worker.start() }
    val writerFutures = writers.mapIndexed { writerIndex, worker ->
        worker.execute(
            TransferMode.SAFE,
            { Triple(running, readyCount, Pair(updateCount, writerIndex)) }
        ) { (run, ready, updateAndIndex) ->
            val (updates, index) = updateAndIndex
            var sequence = index * 1_000_000
            ready.incrementAndGet()
            while (run.value == 1) {
                publishThenClearNullableRoots(sequence)
                sequence += 4
                updates.incrementAndGet()
            }
        }
    }

    val reader = Worker.start()
    val readerFuture = reader.execute(
        TransferMode.SAFE,
        { Triple(running, readyCount, errorCount) }
    ) { (run, ready, errors) ->
        ready.incrementAndGet()
        while (run.value == 1) {
            if (!nullableRootsAreValid()) {
                errors.incrementAndGet()
                break
            }
        }
    }

    while (readyCount.value < NULLABLE_ROOT_WRITER_COUNT + 1) {}

    repeat(NULLABLE_ROOT_GC_CYCLES) { cycle ->
        val garbage = Array(128) { NullableGlobalRootPayload(cycle * 128 + it) }
        assertTrue(garbage[garbage.lastIndex].isValid())
        GC.collect()
    }

    running.value = 0
    for (future in writerFutures) {
        future.result
    }
    readerFuture.result

    for (worker in writers) {
        worker.requestTermination().result
    }
    reader.requestTermination().result

    concurrentNullableRoot0 = null
    concurrentNullableRoot1 = null
    concurrentNullableRoot2 = null
    concurrentNullableRoot3 = null

    assertTrue(updateCount.value > 0, "Writer workers made no global-root updates")
    assertEquals(0, errorCount.value, "Observed corrupted global-root payload")
    println("PASS: ${updateCount.value} root updates, $NULLABLE_ROOT_GC_CYCLES GCs")
}
