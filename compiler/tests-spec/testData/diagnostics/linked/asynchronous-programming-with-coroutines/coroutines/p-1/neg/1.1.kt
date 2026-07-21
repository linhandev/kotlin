// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutines -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: suspending function cannot be called from non-suspending context without builder
 */

// TESTCASE NUMBER: 1
fun case_1() {
    suspend fun suspendTarget18044() {}

    fun regular18044() {
        <!ILLEGAL_SUSPEND_FUNCTION_CALL!>suspendTarget18044<!>()
    }
}
