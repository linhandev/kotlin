// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: suspending function return type maps to Continuation type parameter
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun produce18052(): String = "OK"

    fun start18052() {
        suspend { produce18052() }.startCoroutineUninterceptedOrReturn(object : Continuation<String> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<String>) {}
        })
    }
}
