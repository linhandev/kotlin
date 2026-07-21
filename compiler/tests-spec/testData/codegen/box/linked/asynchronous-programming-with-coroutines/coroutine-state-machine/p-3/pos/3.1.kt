// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutine-state-machine -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: early return yields early at runtime; intrinsic suspension point resumes to late when early return is disabled
 */
// TESTCASE NUMBER: 1

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun builder18122(c: suspend () -> String): String {
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

var earlyReturn18122 = true

fun runStateMachine18122(): String {
    return builder18122 {
        if (earlyReturn18122) return@builder18122 "early"
        suspendCoroutineUninterceptedOrReturn<String> { continuation ->
            continuation.resume("late")
            COROUTINE_SUSPENDED
        }
    }
}

fun box(): String {
    earlyReturn18122 = true
    val early = runStateMachine18122()
    if (early != "early") return "fail early: $early"
    earlyReturn18122 = false
    val late = runStateMachine18122()
    return if (late == "late") "OK" else "fail late: $late"
}
