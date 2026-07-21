// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-passing-style -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: continuation object can be stored inside suspendCoroutineUninterceptedOrReturn block
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    var stored18092: Continuation<Int>? = null

    suspend fun store18092(): Int = suspendCoroutineUninterceptedOrReturn { continuation ->
        stored18092 = continuation
        COROUTINE_SUSPENDED
    }
}
