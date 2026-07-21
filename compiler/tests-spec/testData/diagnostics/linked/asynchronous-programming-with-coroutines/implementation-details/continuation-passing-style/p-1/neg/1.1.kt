// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, implementation-details, continuation-passing-style -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: user cannot manually return COROUTINE_SUSPENDED from suspending function
 */

import kotlin.coroutines.intrinsics.*

// TESTCASE NUMBER: 1
fun case_1() {

    suspend fun bad18082(): Int {
        return <!TYPE_MISMATCH!>COROUTINE_SUSPENDED<!>
    }
}
