// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 13 -> sentence 13
 *                expressions, function-literals, lambda-literals -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: non-inline higher-order lambda cannot call a suspend function even from a suspend caller
 */

fun bridge56113(block: () -> Int): Int = block()
suspend fun inner56113(): Int = 1

// TESTCASE NUMBER: 1
suspend fun case_1(): Int = bridge56113 { <!NON_LOCAL_SUSPENSION_POINT!>inner56113<!>() }
