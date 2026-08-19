// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 *                expressions, comparison-expressions -> paragraph 3 -> sentence 3
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: built-in multi-type range contains infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(1.5 in 1.0..2.0 && 2.0 in 1.0..2.0)
    checkSubtype<Boolean>(5L in 1L..10L)
    checkSubtype<Boolean>(1.5f in 1.0f..2.0f)
}
