// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-state-machine -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: suspendCoroutineUninterceptedOrReturn intrinsic call is a suspension point in suspend lambda
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun builder18122(c: suspend () -> Int) {}

    fun test18122() {
        builder18122 {
            suspendCoroutineUninterceptedOrReturn<Int> { continuation ->
                continuation.resume(1)
                COROUTINE_SUSPENDED
            }
            2
        }
    }
}
