// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 28 -> sentence 28
 *                declarations, function-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: tailrec suspend fun can be declared and executed
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

tailrec suspend fun fact56128(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc else fact56128(n - 1, acc * n)

fun box(): String {
    if (runSuspend561 { fact56128(5) } != 120) return "NOK"
    if (runSuspend561 { fact56128(1) } != 1) return "NOK"
    return "OK"
}
