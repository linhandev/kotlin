// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 *                expressions, comparison-expressions -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: variable-bound range infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Int, lo: Int, hi: Int) {
    checkSubtype<Boolean>(x in lo..hi)
}
