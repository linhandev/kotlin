// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: resumeWithException requires Throwable argument
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun resumeWithBadException18074(continuation: Continuation<Int>) {
        continuation.resumeWithException(<!TYPE_MISMATCH!>""<!>)
    }
}
