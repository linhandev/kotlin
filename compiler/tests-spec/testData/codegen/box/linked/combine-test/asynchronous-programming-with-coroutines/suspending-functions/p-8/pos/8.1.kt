// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: suspend function reference can be assigned to a suspend function type
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

suspend fun s56108(): Int = 1
val f56108: suspend () -> Int = ::s56108
suspend fun use56108(): Int = f56108()

fun box(): String {
    if (runSuspend561 { use56108() } != 1) return "NOK"
    return "OK"
}
