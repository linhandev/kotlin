// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: two coroutines interleave on a single thread when resumed alternately at suspension points
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

val order18033 = mutableListOf<String>()
val pending18033 = ArrayDeque<Continuation<Unit>>()

suspend fun checkpoint18033(label: String): Unit = suspendCoroutineUninterceptedOrReturn { continuation ->
    order18033 += label
    pending18033.addLast(continuation)
    COROUTINE_SUSPENDED
}

fun startCoroutine18033(block: suspend () -> Unit) {
    block.startCoroutineUninterceptedOrReturn(object : Continuation<Unit> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(result: Result<Unit>) {
            result.getOrThrow()
        }
    })
}

fun drainPending18033() {
    while (pending18033.isNotEmpty()) {
        pending18033.removeFirst().resume(Unit)
    }
}

fun box(): String {
    order18033.clear()
    pending18033.clear()
    startCoroutine18033 {
        checkpoint18033("A1")
        checkpoint18033("A2")
    }
    startCoroutine18033 {
        checkpoint18033("B1")
        checkpoint18033("B2")
    }
    drainPending18033()
    return if (order18033 == listOf("A1", "B1", "A2", "B2")) "OK" else "fail: $order18033"
}
