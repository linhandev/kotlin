// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 21 -> sentence 21
 *                inheritance, inheriting -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: interface suspend member must be implemented with suspend override
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

interface I56121 {
    suspend fun f56121(): Int
}

class C56121 : I56121 {
    override suspend fun f56121(): Int = 1
}

fun box(): String {
    if (runSuspend561 { C56121().f56121() } != 1) return "NOK"
    return "OK"
}
