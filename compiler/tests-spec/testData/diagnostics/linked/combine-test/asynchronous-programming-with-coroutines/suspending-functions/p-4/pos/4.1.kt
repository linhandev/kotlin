// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 4 -> sentence 4
 *                declarations, function-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: suspend extension function can be declared and called from a suspend context type inference
 * HELPERS: checkType
 */

suspend fun String.twice56104(): String = this + this
suspend fun s56104(): String = "a".twice56104()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> String>(::s56104)
}
