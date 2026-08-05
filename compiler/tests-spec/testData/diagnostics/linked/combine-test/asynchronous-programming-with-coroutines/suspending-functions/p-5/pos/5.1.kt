// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 5 -> sentence 5
 *                declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: local suspend fun can be called from the enclosing suspend function body type inference
 * HELPERS: checkType
 */

suspend fun outer56105(): Int {
    suspend fun inner56105(): Int = 3
    return inner56105()
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::outer56105)
}
