// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: shared mutable state is updated across suspension points at runtime
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

var shared18023 = 0

suspend fun bump18023(): Unit = suspendCoroutineUninterceptedOrReturn { continuation ->
    shared18023++
    continuation.resume(Unit)
    COROUTINE_SUSPENDED
}

fun builder18023(c: suspend () -> Int): Int {
    var result: Int? = null
    val value = c.startCoroutineUninterceptedOrReturn(object : Continuation<Int> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(res: Result<Int>) {
            result = res.getOrThrow()
        }
    })
    if (value === COROUTINE_SUSPENDED) return result!!
    return value as Int
}

fun box(): String {
    shared18023 = 0
    val value = builder18023 {
        bump18023()
        bump18023()
        shared18023
    }
    return if (value == 2) "OK" else "fail: $value"
}
