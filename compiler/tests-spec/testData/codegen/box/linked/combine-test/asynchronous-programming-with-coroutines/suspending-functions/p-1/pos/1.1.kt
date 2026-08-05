// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 *                declarations, function-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: suspend fun may call another suspend fun in the same compilation unit
 */

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn

// TESTCASE NUMBER: 1
fun <T> runSuspend561(block: suspend () -> T): T {
    var result: Result<T>? = null
    val immediate = block.startCoroutineUninterceptedOrReturn(object : Continuation<T> {
        override val context = EmptyCoroutineContext
        override fun resumeWith(res: Result<T>) {
            result = res
        }
    })
    @Suppress("UNCHECKED_CAST")
    return if (immediate === COROUTINE_SUSPENDED) result!!.getOrThrow() else immediate as T
}

suspend fun a56101(): Int = b56101()
suspend fun b56101(): Int = 2

fun box(): String {
    val v = runSuspend561 { a56101() }
    if (v != 2) return "NOK"
    return "OK"
}
