// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, coroutine-intrinsics -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: suspendCoroutineUninterceptedOrReturn cannot be called from non-suspending function
 */

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun regular18164() {
        <!ILLEGAL_SUSPEND_FUNCTION_CALL!>suspendCoroutineUninterceptedOrReturn<!> { _: Continuation<Unit> -> }
    }
}
