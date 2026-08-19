// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 *                expressions, comparison-expressions -> paragraph 7 -> sentence 7
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: reversed empty range infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(5 !in 10..1 && 1.5 !in 2.0..1.0)
}
