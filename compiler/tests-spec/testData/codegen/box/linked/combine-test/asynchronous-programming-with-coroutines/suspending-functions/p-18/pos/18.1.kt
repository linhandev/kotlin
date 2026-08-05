// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 *                expressions, jump-expressions, return-expressions -> paragraph 18 -> sentence 18
 *                declarations, function-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: bare return inside inline forEach lambda may non-locally exit the enclosing suspend function
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

suspend fun s56118(xs: List<Int>): Int {
    xs.forEach {
        if (it < 0) return -1
    }
    return 0
}

fun box(): String {
    if (runSuspend561 { s56118(listOf(1, -5, 2)) } != -1) return "NOK"
    if (runSuspend561 { s56118(listOf(1, 2)) } != 0) return "NOK"
    return "OK"
}
