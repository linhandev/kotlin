// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 15 -> sentence 15
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: chained trailing lambda calls type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<List<String>>(listOf(-1, 2, 3).filter { it > 0 }.map { it.toString() })
}
