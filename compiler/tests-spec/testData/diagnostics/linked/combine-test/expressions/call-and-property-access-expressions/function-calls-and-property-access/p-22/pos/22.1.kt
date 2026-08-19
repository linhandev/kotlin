// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 22 -> sentence 22
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: trailing lambda call expression statement type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun consume(block: () -> Unit): Unit = block()

fun case1() {
    checkSubtype<Unit>(consume { })
}
