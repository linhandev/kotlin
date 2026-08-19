// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 9 -> sentence 9
 *                expressions, elvis-operator-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: safe call with Elvis operator infers non-null result type Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    checkSubtype<Int>(s?.length ?: 0)
}
