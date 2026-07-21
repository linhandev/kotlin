// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-intrinsics -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: completion Continuation type parameter must match suspend function result type
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun work18167(): Int = 1

    fun start18167() {
        suspend { work18167() }.startCoroutineUninterceptedOrReturn(<!TYPE_MISMATCH!>object : Continuation<String><!> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<String>) {}
        })
    }
}
