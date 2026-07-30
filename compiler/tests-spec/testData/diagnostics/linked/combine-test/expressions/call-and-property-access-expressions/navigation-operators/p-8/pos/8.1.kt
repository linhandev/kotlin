// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNNECESSARY_SAFE_CALL
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: safe call on non-null receiver promotes result type to nullable Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String) {
    checkSubtype<Int?>(s?.length)
}
