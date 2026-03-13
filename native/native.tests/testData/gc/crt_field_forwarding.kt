// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi -Xbinary=gc=cmc -Xallocator=crt

// Regression test for CRT GC issue 005 (derived pointer after object move).
// Creates deep nested object chains and concurrently traverses them while GC runs,
// verifying that derived pointers (field offsets) remain valid after object relocation.

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.concurrent.*
import kotlin.native.runtime.GC

class NodeD(val depth: Int, val value: Long, val child: NodeD?) {
    fun verify(): Boolean {
        if (value != depth.toLong() * 17 + 3) return false
        return if (child != null) child.verify() else true
    }

    fun leafValue(): Long {
        return if (child != null) child.leafValue() else value
    }
}

fun buildChain(depth: Int): NodeD {
    var node = NodeD(0, 0L * 17 + 3, null)
    for (d in 1..depth) {
        node = NodeD(d, d.toLong() * 17 + 3, node)
    }
    return node
}

const val CHAIN_DEPTH = 20
const val FWD_NUM_WORKERS = 4
const val FWD_NUM_ROUNDS = 100

val fwdReadyCount = AtomicInt(0)
val fwdStartSignal = AtomicInt(0)

@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
@Test fun testFieldForwarding() {
    val chains = Array(FWD_NUM_WORKERS) { buildChain(CHAIN_DEPTH) }

    for (round in 1..FWD_NUM_ROUNDS) {
        fwdReadyCount.value = 0
        fwdStartSignal.value = 0

        val workers = Array(FWD_NUM_WORKERS) { Worker.start() }
        val futures = workers.mapIndexed { index, worker ->
            worker.execute(TransferMode.SAFE, { chains[index] }) { chain ->
                fwdReadyCount.incrementAndGet()
                while (fwdStartSignal.value == 0) {}

                val ok = chain.verify()
                val leaf = chain.leafValue()
                ok && leaf == 0L * 17 + 3
            }
        }

        while (fwdReadyCount.value < FWD_NUM_WORKERS) {}
        fwdStartSignal.value = 1

        GC.collect()

        for (future in futures) {
            assertTrue(future.result, "Field forwarding check failed in round $round")
        }

        for (w in workers) {
            w.requestTermination().result
        }
    }

    for (chain in chains) {
        assertTrue(chain.verify(), "Chain corrupted after all rounds")
    }

    println("PASS")
}
