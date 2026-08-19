// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 *                expressions, comparison-expressions -> paragraph 1 -> sentence 1
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: closed-range in/contains/boundary infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>((5 in 1..10) == (1..10).contains(5))
    checkSubtype<Boolean>(1 in 1..10 && 10 in 1..10 && 11 !in 1..10)
}
