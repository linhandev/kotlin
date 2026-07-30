// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 *                type-system, introduction-1 -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: a == true && b infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(a: Boolean?, b: Boolean) {
    checkSubtype<Boolean>((a == true) && b)
}
