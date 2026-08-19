// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 24 -> sentence 24
 *                inheritance, inheriting -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: abstract suspend fun is implemented by a suspending override in a subclass
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

abstract class A56124 {
    abstract suspend fun f56124(): Int
}

class Impl56124 : A56124() {
    override suspend fun f56124(): Int = 1
}

fun box(): String {
    if (runSuspend561 { Impl56124().f56124() } != 1) return "NOK"
    return "OK"
}
