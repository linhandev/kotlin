// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 30 -> sentence 30
 *                expressions, function-literals, lambda-literals -> paragraph 30 -> sentence 30
 *                asynchronous-programming-with-coroutines, suspending-functions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: crossinline non-suspend lambda cannot contain suspension points even inside a suspend caller
 */

inline fun call56130(crossinline block: () -> Unit) {
    block()
}
suspend fun inner56130() {}

// TESTCASE NUMBER: 1
suspend fun case_1() {
    call56130 { <!NON_LOCAL_SUSPENSION_POINT!>inner56130<!>() }
}
