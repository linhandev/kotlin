// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-interception -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: interceptContinuation return type must match continuation type parameter
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun <T> badIntercept18132(continuation: Continuation<T>): Continuation<T> {
        val wrong: Continuation<String> = object : Continuation<String> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<String>) {}
        }
        return <!TYPE_MISMATCH!>wrong<!>
    }
}
