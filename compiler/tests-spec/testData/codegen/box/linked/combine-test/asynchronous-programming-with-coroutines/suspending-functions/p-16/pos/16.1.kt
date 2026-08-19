// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 16 -> sentence 16
 *                expressions, try-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: try/catch inside a suspend fun may wrap suspend calls
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

suspend fun risky56116(): Int = 1
suspend fun safe56116(): Int = try {
    risky56116()
} catch (_: Exception) {
    0
}

fun box(): String {
    if (runSuspend561 { safe56116() } != 1) return "NOK"
    return "OK"
}
