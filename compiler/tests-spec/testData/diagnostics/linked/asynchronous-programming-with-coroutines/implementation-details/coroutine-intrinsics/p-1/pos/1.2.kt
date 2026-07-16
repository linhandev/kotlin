// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-intrinsics -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: startCoroutineUninterceptedOrReturn starts coroutine from suspending lambda
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun work18162(): String = "OK"

    fun start18162(): Any? {
        return suspend { work18162() }.startCoroutineUninterceptedOrReturn(object : Continuation<String> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<String>) {}
        })
    }
}
