// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: when branches inside a suspend fun may call suspend functions
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

suspend fun zero56114(): Int = 0
suspend fun pick56114(n: Int): Int = when (n) {
    0 -> zero56114()
    else -> n
}

fun box(): String {
    if (runSuspend561 { pick56114(0) } != 0) return "NOK"
    if (runSuspend561 { pick56114(9) } != 9) return "NOK"
    return "OK"
}
