// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, asynchronous-programming-with-coroutines, suspending-functions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: asynchronous-programming-with-coroutines, suspending-functions -> paragraph 16 -> sentence 16
 *                expressions, try-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: try/catch inside a suspend fun may wrap suspend calls type inference
 * HELPERS: checkType
 */

suspend fun risky56116(): Int = 1
suspend fun safe56116(): Int = try {
    risky56116()
} catch (_: Exception) {
    0
}

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<suspend () -> Int>(::safe56116)
}
