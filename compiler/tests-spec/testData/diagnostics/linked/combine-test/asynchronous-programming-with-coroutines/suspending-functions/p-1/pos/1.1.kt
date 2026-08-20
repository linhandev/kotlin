// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 1 -> sentence 1
 *                declarations, function-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: suspend fun may call another suspend fun in the same compilation unit type inference
 * HELPERS: checkType
 */

suspend fun a56101(): Int = b56101()
suspend fun b56101(): Int = 2

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::a56101)
}
