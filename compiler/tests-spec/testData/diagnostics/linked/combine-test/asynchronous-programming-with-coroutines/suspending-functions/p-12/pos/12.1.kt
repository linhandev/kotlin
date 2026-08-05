// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 12 -> sentence 12
 *                declarations, function-declaration -> paragraph 12 -> sentence 12
 *                expressions, function-literals, lambda-literals -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: inline non-suspend lambda argument of a call from suspend context may contain suspension points type inference
 * HELPERS: checkType
 */

inline fun bridge56112(block: () -> Int): Int = block()
suspend fun inner56112(): Int = 5
suspend fun s56112(): Int = bridge56112 { inner56112() }

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::s56112)
}
