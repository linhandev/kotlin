// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 19 -> sentence 19
 *                expressions, jump-expressions, return-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: bare return inside a suspend lambda cannot non-locally exit the outer suspend function
 */

suspend fun run56119(block: suspend () -> Unit) {
    block()
}

// TESTCASE NUMBER: 1
suspend fun case_1() {
    run56119 { <!RETURN_NOT_ALLOWED!>return<!> }
}
