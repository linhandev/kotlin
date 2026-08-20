// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 11 -> sentence 11
 *                asynchronous-programming-with-coroutines, suspending-functions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: regular non-inline lambda cannot call a suspend function
 */

fun run56111(block: () -> Int): Int = block()
suspend fun inner56111(): Int = 1

// TESTCASE NUMBER: 1
suspend fun case_1(): Int = run56111 { <!NON_LOCAL_SUSPENSION_POINT!>inner56111<!>() }
