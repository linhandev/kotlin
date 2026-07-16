// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: resumeWith propagates failure via Result.failure
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun resumeFailure18075(continuation: Continuation<Int>) {
        continuation.resumeWith(Result.failure(IllegalStateException("fail")))
    }
}
