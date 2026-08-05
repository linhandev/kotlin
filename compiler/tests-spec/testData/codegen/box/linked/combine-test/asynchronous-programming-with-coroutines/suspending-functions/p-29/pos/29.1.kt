// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 29 -> sentence 29
 *                declarations, declarations-with-type-parameters -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: generic suspend fun <T> preserves the type argument
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

suspend fun <T> id56129(x: T): T = x
suspend fun s56129(): Int = id56129(1)

fun box(): String {
    if (runSuspend561 { s56129() } != 1) return "NOK"
    if (runSuspend561 { id56129("ok") } != "ok") return "NOK"
    return "OK"
}
