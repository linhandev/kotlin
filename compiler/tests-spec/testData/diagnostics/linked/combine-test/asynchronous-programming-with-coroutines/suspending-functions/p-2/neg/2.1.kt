// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 2 -> sentence 2
 *                expressions, call-expressions -> paragraph 2 -> sentence 2
 *                expressions, when-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: non-suspending function cannot call suspend fun from a when branch
 */

suspend fun s56102(): Int = 1

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean): Int = when {
    flag -> <!ILLEGAL_SUSPEND_FUNCTION_CALL!>s56102<!>()
    else -> 0
}
