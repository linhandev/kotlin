// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 10 -> sentence 10
 *                asynchronous-programming-with-coroutines, suspending-functions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: suspending lambda can be passed where suspend () -> T is expected
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

suspend fun run56110(block: suspend () -> Int): Int = block()
suspend fun s56110(): Int = run56110 { 4 }

fun box(): String {
    if (runSuspend561 { s56110() } != 4) return "NOK"
    return "OK"
}
