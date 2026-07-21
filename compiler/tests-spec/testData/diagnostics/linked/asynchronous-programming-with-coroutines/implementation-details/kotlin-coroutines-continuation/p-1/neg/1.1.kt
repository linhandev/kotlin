// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: resumeWith requires Result with matching type parameter
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    class BadContinuation18052 : Continuation<Int> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            delegate.resumeWith(<!TYPE_MISMATCH!>Result.success("wrong")<!>)
        }

        private val delegate: Continuation<Int> = object : Continuation<Int> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<Int>) {}
        }
    }
}
