// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: non-suspending function cannot directly call suspending function (function colouring)
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun suspendTarget18033() {}

    fun regular18033() {
        <!ILLEGAL_SUSPEND_FUNCTION_CALL!>suspendTarget18033<!>()
    }
}
