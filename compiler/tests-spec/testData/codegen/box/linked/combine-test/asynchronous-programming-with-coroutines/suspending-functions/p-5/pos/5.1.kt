// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 5 -> sentence 5
 *                declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: local suspend fun can be called from the enclosing suspend function body
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

suspend fun outer56105(): Int {
    suspend fun inner56105(): Int = 3
    return inner56105()
}

fun box(): String {
    if (runSuspend561 { outer56105() } != 3) return "NOK"
    return "OK"
}
