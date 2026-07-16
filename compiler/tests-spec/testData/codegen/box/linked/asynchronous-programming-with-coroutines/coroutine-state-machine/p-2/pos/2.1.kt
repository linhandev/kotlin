// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutine-state-machine -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: local variable is preserved across suspend call at runtime
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

suspend fun suspendPoint18111(): Int = suspendCoroutineUninterceptedOrReturn { continuation ->
    continuation.resume(1)
    COROUTINE_SUSPENDED
}

fun builder18111(c: suspend () -> Int): Int {
    var result: Int? = null
    val value = c.startCoroutineUninterceptedOrReturn(object : Continuation<Int> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(res: Result<Int>) {
            result = res.getOrThrow()
        }
    })
    if (value === COROUTINE_SUSPENDED) return result!!
    return value as Int
}

fun box(): String {
    val value = builder18111 {
        var local = 10
        local += suspendPoint18111()
        local
    }
    return if (value == 11) "OK" else "fail: $value"
}
