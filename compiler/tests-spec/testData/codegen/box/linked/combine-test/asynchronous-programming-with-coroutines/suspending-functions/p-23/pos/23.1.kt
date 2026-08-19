// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 23 -> sentence 23
 *                inheritance, inheriting -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: suspending open member can be overridden by a suspending override
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

open class B56123 {
    open suspend fun f56123(): Int = 1
}

class D56123 : B56123() {
    override suspend fun f56123(): Int = 2
}

fun box(): String {
    if (runSuspend561 { D56123().f56123() } != 2) return "NOK"
    if (runSuspend561 { (D56123() as B56123).f56123() } != 2) return "NOK"
    return "OK"
}
