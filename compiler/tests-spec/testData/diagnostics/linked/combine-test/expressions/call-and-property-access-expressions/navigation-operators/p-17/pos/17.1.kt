// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 17 -> sentence 17
 *                expressions, indexing-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable IntArray infers nullable Int result for index access
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(a: IntArray?) {
    checkSubtype<Int?>(a?.get(0))
}
