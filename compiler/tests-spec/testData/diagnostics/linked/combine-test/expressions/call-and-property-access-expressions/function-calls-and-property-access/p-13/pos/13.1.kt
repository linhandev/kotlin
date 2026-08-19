// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 13 -> sentence 13
 *                type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: trailing lambda parameter type inference from call context
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<List<String>>(listOf("a").map { it.uppercase() })
}
