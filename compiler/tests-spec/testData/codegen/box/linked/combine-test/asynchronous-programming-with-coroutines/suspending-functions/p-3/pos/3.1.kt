// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 *                expressions, call-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: suspend fun may freely call a regular non-suspending function
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

fun plain56103(): Int = 1
suspend fun s56103(): Int = plain56103()

fun box(): String {
    if (runSuspend561 { s56103() } != 1) return "NOK"
    return "OK"
}
