// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable List infers nullable String result from firstOrNull
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(xs: List<String>?) {
    checkSubtype<String?>(xs?.firstOrNull())
}
