// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -SUSPENSION_CALL_MUST_BE_USED_AS_RETURN_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-passing-style -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: suspendCoroutineUninterceptedOrReturn with COROUTINE_SUSPENDED inside callback suspends coroutine
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun manual18091(): String = suspendCoroutineUninterceptedOrReturn { continuation ->
        continuation.resume("OK")
        COROUTINE_SUSPENDED
    }
}
