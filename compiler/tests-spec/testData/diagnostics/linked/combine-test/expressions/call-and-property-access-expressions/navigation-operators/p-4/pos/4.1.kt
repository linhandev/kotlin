// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 4 -> sentence 4
 *                type-inference, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: safe call result type is nullable even when the accessed member has non-null type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    checkSubtype<Int?>(s?.length)
}
