// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 17 -> sentence 17
 *                expressions, jump-expressions, return-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: bare return inside for-loop of a suspend fun still exits the outer suspend function
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

suspend fun s56117(xs: List<Int>): Int {
    for (x in xs) {
        if (x < 0) return -1
    }
    return 0
}

fun box(): String {
    if (runSuspend561 { s56117(listOf(1, -2, 3)) } != -1) return "NOK"
    if (runSuspend561 { s56117(listOf(1, 2)) } != 0) return "NOK"
    return "OK"
}
