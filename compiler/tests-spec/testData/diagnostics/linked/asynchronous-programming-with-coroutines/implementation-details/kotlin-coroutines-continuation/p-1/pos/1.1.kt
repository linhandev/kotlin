// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: custom Continuation implementation with resumeWith propagates Result
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    class CustomContinuation18051<T>(private val completion: Continuation<T>) : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            completion.resumeWith(result)
        }
    }
}
