// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: non-suspending function cannot call suspending function
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun suspendTarget18023() {}

    fun regular18023() {
        <!ILLEGAL_SUSPEND_FUNCTION_CALL!>suspendTarget18023<!>()
    }
}
