// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, continuation-passing-style -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: suspending function returns actual result at runtime after CPS transformation
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

suspend fun answer18082(): Int = 42

fun builder18082(c: suspend () -> Int): Int {
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

fun box(): String = if (builder18082 { answer18082() } == 42) "OK" else "fail"
