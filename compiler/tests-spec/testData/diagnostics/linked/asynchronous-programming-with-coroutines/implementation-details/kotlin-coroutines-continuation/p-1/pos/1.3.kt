// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: Continuation implementation exposes CoroutineContext via context property
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    class ContextContinuation18053<T>(private val completion: Continuation<T>) : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            completion.resumeWith(result)
        }
    }

    fun useContext18053(continuation: Continuation<Int>) {
        val wrapped = ContextContinuation18053(continuation)
        wrapped.context
    }
}
