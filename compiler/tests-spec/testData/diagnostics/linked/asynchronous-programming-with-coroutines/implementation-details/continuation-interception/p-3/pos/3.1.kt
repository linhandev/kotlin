// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-interception -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: continuation interception via context ContinuationInterceptor or original continuation
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun <T> interceptIfNeeded18151(continuation: Continuation<T>): Continuation<T> {
        return continuation.context[ContinuationInterceptor]?.interceptContinuation(continuation) ?: continuation
    }
}
