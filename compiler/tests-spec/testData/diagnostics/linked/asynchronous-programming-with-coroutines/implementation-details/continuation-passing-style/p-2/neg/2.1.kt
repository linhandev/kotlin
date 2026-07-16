// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-passing-style -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: COROUTINE_SUSPENDED cannot be returned outside intrinsic callback
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun bad18093(): Int {
        suspendCoroutineUninterceptedOrReturn<Int> { continuation ->
            continuation.resume(1)
            1
        }
        return <!TYPE_MISMATCH!>COROUTINE_SUSPENDED<!>
    }
}
