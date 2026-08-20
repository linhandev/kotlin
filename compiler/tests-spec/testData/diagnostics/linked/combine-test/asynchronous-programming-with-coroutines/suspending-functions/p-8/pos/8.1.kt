// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: suspend function reference can be assigned to a suspend function type type inference
 * HELPERS: checkType
 */

suspend fun s56108(): Int = 1

// TESTCASE NUMBER: 1
fun case_1() {
    val f: suspend () -> Int = ::s56108
    checkSubtype<suspend () -> Int>(f)
}
