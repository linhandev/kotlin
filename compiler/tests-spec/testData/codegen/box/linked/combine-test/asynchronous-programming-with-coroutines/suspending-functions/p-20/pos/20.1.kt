// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 *                expressions, jump-expressions, return-expressions -> paragraph 20 -> sentence 20
 *                expressions, when-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: suspend lambda may use labeled return inside when for a local return
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

suspend fun run56120(block: suspend () -> Int): Int = block()
suspend fun s56120(): Int = run56120 {
    when {
        true -> return@run56120 2
        else -> 0
    }
}

fun box(): String {
    if (runSuspend561 { s56120() } != 2) return "NOK"
    return "OK"
}
