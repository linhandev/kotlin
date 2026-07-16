// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: asynchronous-programming-with-coroutines, coroutines -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: coroutine builder accepts suspending lambda to bootstrap coroutine
 */

// TESTCASE NUMBER: 1
fun case_1() {
    fun builder18041(c: suspend () -> Unit) {}

    fun start18041() {
        builder18041 { }
    }
}
