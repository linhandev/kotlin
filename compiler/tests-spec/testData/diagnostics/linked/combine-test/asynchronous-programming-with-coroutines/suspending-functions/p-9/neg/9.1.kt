// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 9 -> sentence 9
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: non-suspending context cannot invoke a suspend function-typed value
 */

suspend fun s56109(): Int = 1
val f56109: suspend () -> Int = ::s56109

// TESTCASE NUMBER: 1
fun case_1(): Int = <!ILLEGAL_SUSPEND_FUNCTION_CALL!>f56109<!>()
