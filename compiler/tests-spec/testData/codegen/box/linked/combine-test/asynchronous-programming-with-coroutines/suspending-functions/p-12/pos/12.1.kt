// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 12 -> sentence 12
 *                declarations, function-declaration -> paragraph 12 -> sentence 12
 *                expressions, function-literals, lambda-literals -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: inline non-suspend lambda argument of a call from suspend context may contain suspension points
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

inline fun bridge56112(block: () -> Int): Int = block()
suspend fun inner56112(): Int = 5
suspend fun s56112(): Int = bridge56112 { inner56112() }

fun box(): String {
    if (runSuspend561 { s56112() } != 5) return "NOK"
    return "OK"
}
