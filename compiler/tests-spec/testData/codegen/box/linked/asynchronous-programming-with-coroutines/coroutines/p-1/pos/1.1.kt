// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutines -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: builder bootstraps nested suspend calls that suspend and resume with a chained result
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

suspend fun bump18041(): Int = suspendCoroutineUninterceptedOrReturn { continuation ->
    continuation.resume(1)
    COROUTINE_SUSPENDED
}

suspend fun finish18041(prefix: Int): String = suspendCoroutineUninterceptedOrReturn { continuation ->
    continuation.resume("v$prefix")
    COROUTINE_SUSPENDED
}

fun builder18041(c: suspend () -> String): String {
    var result: String? = null
    val value = c.startCoroutineUninterceptedOrReturn(object : Continuation<String> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(res: Result<String>) {
            result = res.getOrThrow()
        }
    })
    if (value === COROUTINE_SUSPENDED) return result!!
    return value as String
}

fun box(): String {
    val result = builder18041 {
        val prefix = bump18041()
        finish18041(prefix)
    }
    return if (result == "v1") "OK" else "fail: $result"
}
