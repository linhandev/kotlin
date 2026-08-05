// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 3 -> sentence 3
 *                expressions, call-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: suspend fun may freely call a regular non-suspending function type inference
 * HELPERS: checkType
 */

fun plain56103(): Int = 1
suspend fun s56103(): Int = plain56103()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::s56103)
}
