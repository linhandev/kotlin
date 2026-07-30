// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 5 -> sentence 5
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: explicit lambda and trailing lambda call forms share the same result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val xs = listOf(1, 2)
    checkSubtype<List<Int>>(xs.map({ it * 2 }))
    checkSubtype<List<Int>>(xs.map { it * 2 })
}
