// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: comparison && comparison infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Int) {
    checkSubtype<Boolean>(x > 0 && x < 10)
}
