// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, kotlin-coroutines-continuation -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: coroutineContext property cannot be accessed from non-suspending function
 */

import kotlin.coroutines.*

// TESTCASE NUMBER: 1
fun case_1() {

    fun regular18063() {
        <!ILLEGAL_SUSPEND_PROPERTY_ACCESS!>coroutineContext<!>
        kotlin.coroutines.<!ILLEGAL_SUSPEND_PROPERTY_ACCESS!>coroutineContext<!>
    }
}
