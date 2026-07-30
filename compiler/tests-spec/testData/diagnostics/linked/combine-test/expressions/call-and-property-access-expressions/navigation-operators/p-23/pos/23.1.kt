// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable Int infers nullable String? result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Int?) {
    checkSubtype<String?>(x?.toString())
}
