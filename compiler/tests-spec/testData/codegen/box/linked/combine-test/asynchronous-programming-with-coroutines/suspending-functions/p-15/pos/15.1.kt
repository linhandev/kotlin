// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 15 -> sentence 15
 *                statements, loop-statements -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: for-loop body inside a suspend fun may call suspend functions
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

suspend fun step56115(x: Int): Int = x
suspend fun sum56115(xs: List<Int>): Int {
    var s = 0
    for (x in xs) {
        s += step56115(x)
    }
    return s
}

fun box(): String {
    if (runSuspend561 { sum56115(listOf(1, 2, 3)) } != 6) return "NOK"
    if (runSuspend561 { sum56115(emptyList()) } != 0) return "NOK"
    return "OK"
}
