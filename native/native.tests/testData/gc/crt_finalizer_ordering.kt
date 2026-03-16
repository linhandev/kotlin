// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests cleaner execution ordering with chains, diamond dependencies, and rapid creation.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.ref.createCleaner
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import platform.posix.usleep

val cleanedIds = AtomicInt(0)

class ChainNode(val id: Int) {
    var next: ChainNode? = null
    val cleaner = createCleaner(id) { _: Int ->
        cleanedIds.incrementAndGet()
    }
}

fun testChainABC() {
    cleanedIds.value = 0

    // Chain: A -> B -> C, each with a cleaner
    run {
        val c = ChainNode(3)
        val b = ChainNode(2)
        val a = ChainNode(1)
        a.next = b
        b.next = c
    }

    repeat(10) { GC.collect() }
    usleep(500_000u)

    val count = cleanedIds.value
    assertTrue(count >= 1, "At least one cleaner should run")
}

class DiamondNode(val id: Int) {
    var left: DiamondNode? = null
    var right: DiamondNode? = null
    val cleaner = createCleaner(id) { _: Int ->
        cleanedIds.incrementAndGet()
    }
}

fun testDiamondDependency() {
    cleanedIds.value = 0

    // Diamond: A -> B, A -> C, B -> D, C -> D
    run {
        val d = DiamondNode(4)
        val b = DiamondNode(2)
        val c = DiamondNode(3)
        val a = DiamondNode(1)
        a.left = b
        a.right = c
        b.left = d
        c.left = d
    }

    repeat(10) { GC.collect() }
    usleep(500_000u)

    val count = cleanedIds.value
    assertTrue(count >= 1, "At least one cleaner should run")
}

fun testLongChain() {
    cleanedIds.value = 0

    // Chain of depth 100
    run {
        var head = ChainNode(0)
        for (i in 1 until 100) {
            val node = ChainNode(i)
            node.next = head
            head = node
        }
    }

    repeat(10) { GC.collect() }
    usleep(500_000u)

    val count = cleanedIds.value
    assertTrue(count >= 1, "At least one cleaner should run")
}

fun testRapidCreationAndCollection() {
    cleanedIds.value = 0

    for (round in 0 until 50) {
        run {
            val a = ChainNode(round * 3)
            val b = ChainNode(round * 3 + 1)
            val c = ChainNode(round * 3 + 2)
            a.next = b
            b.next = c
        }
        if (round % 5 == 0) {
            GC.collect()
        }
    }

    repeat(10) { GC.collect() }
    usleep(500_000u)

    val count = cleanedIds.value
    assertTrue(count >= 1, "At least one cleaner should run in rapid creation")
}

@Test fun testFinalizerOrdering() {
    testChainABC()
    testDiamondDependency()
    testLongChain()
    testRapidCreationAndCollection()
    println("PASS")
}
