// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 26 -> sentence 26
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: init block cannot call a suspend function
 */

suspend fun runSuspend56126() {}

// TESTCASE NUMBER: 1
class C56126 {
    init {
        <!ILLEGAL_SUSPEND_FUNCTION_CALL!>runSuspend56126<!>()
    }
}
