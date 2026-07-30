// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Int == in if condition infers Int result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(a: Int, b: Int) {
    checkSubtype<Int>(if (a == b) a + b else -1)
}
