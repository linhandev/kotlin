// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: non-suspending function cannot call suspending function directly
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun target18017(): Int = 1

    fun caller18017() {
        <!ILLEGAL_SUSPEND_FUNCTION_CALL!>target18017<!>()
    }
}
