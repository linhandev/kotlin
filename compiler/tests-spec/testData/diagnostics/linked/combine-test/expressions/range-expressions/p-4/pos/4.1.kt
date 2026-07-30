// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 *                expressions, comparison-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: rangeTo equivalent to .. infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>((1..10) == 1.rangeTo(10) && 5 in 1.rangeTo(10))
    checkSubtype<IntRange>(1.rangeTo(10))
}
