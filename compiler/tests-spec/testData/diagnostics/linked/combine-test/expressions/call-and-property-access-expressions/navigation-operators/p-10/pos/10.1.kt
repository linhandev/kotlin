// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 10 -> sentence 10
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 10 -> sentence 10
 *                expressions, not-null-assertion-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: safe call with non-null assertion infers non-null result type Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    checkSubtype<Int>(s?.length!!)
}
