// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: suspend lambda literal may be used in coroutine builder parameter
 */

// TESTCASE NUMBER: 1
fun case_1() {
    fun builder18004(block: suspend () -> Unit) {}

    fun use18004() {
        builder18004 { }
    }
}
