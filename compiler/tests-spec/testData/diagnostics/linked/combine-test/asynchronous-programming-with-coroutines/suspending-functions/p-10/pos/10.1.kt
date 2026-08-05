// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 10 -> sentence 10
 *                asynchronous-programming-with-coroutines, suspending-functions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: suspending lambda can be passed where suspend () -> T is expected type inference
 * HELPERS: checkType
 */

suspend fun run56110(block: suspend () -> Int): Int = block()
suspend fun s56110(): Int = run56110 { 4 }

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::s56110)
}
