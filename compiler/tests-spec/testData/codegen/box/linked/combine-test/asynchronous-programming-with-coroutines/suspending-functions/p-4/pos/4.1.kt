// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 4 -> sentence 4
 *                declarations, function-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: suspend extension function can be declared and called from a suspend context
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

suspend fun String.twice56104(): String = this + this
suspend fun s56104(): String = "a".twice56104()

fun box(): String {
    if (runSuspend561 { s56104() } != "aa") return "NOK"
    if (runSuspend561 { "xy".twice56104() } != "xyxy") return "NOK"
    return "OK"
}
