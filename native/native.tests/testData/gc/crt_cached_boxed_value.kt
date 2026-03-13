// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issue 004 (cached boxed value barrier crash).
// Frequently boxes small-range values (Int 0..127, Boolean, Byte) and assigns them to
// object fields and array elements. Concurrently triggers GC and reads back values,
// verifying correctness and no SIGSEGV.

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.runtime.GC

class BoxHolder(
    var intVal: Any? = null,
    var boolVal: Any? = null,
    var byteVal: Any? = null
)

const val BOX_NUM_ROUNDS = 100
const val BOX_NUM_WORKERS = 2

val boxReadyCount = AtomicInt(0)
val boxStartSignal = AtomicInt(0)

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testCachedBoxedValue() {
    val holders = Array(128) { i ->
        BoxHolder(
            intVal = i,
            boolVal = (i % 2 == 0),
            byteVal = i.toByte()
        )
    }

    val boxedArray = Array<Any>(128) { it }

    for (round in 1..BOX_NUM_ROUNDS) {
        boxReadyCount.value = 0
        boxStartSignal.value = 0

        val workers = Array(BOX_NUM_WORKERS) { Worker.start() }
        val futures = workers.map { worker ->
            worker.execute(TransferMode.SAFE, { Pair(holders, boxedArray) }) { (h, arr) ->
                boxReadyCount.incrementAndGet()
                while (boxStartSignal.value == 0) {}

                var allOk = true
                for (i in h.indices) {
                    val intV = h[i].intVal as Int
                    val boolV = h[i].boolVal as Boolean
                    val byteV = h[i].byteVal as Byte

                    if (intV != i) allOk = false
                    if (boolV != (i % 2 == 0)) allOk = false
                    if (byteV != i.toByte()) allOk = false

                    val arrV = arr[i] as Int
                    if (arrV != i) allOk = false
                }
                allOk
            }
        }

        while (boxReadyCount.value < BOX_NUM_WORKERS) {}
        boxStartSignal.value = 1

        GC.collect()

        for (i in 0 until 128) {
            holders[i].intVal = i
            holders[i].boolVal = (i % 2 == 0)
            boxedArray[i] = i
        }

        for (future in futures) {
            assertTrue(future.result, "Cached boxed value check failed in round $round")
        }

        for (w in workers) {
            w.requestTermination().result
        }
    }

    println("PASS")
}
