// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 29 -> sentence 29
 *                declarations, declarations-with-type-parameters -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: generic suspend fun <T> preserves the type argument type inference
 * HELPERS: checkType
 */

suspend fun <T> id56129(x: T): T = x
suspend fun s56129(): Int = id56129(1)

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::s56129)
}
