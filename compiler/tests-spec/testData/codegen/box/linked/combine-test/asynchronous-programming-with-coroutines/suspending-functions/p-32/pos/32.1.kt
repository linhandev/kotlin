// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 32 -> sentence 32
 *                declarations, function-declaration -> paragraph 32 -> sentence 32
 *                type-inference, introduction-1 -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: suspend function type can participate in inline generic type-argument inference
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

inline fun <reified T> select56132(x: T): T = x
suspend fun s56132(): Int {
    val block: suspend () -> Int = { 7 }
    return select56132(block)()
}

fun box(): String {
    if (runSuspend561 { s56132() } != 7) return "NOK"
    return "OK"
}
