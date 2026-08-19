// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 35 -> sentence 35
 *                expressions, range-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: in operator on IntRange uses range contains convention and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(5 in 1..10)
    checkSubtype<Boolean>(11 in 1..10)
    checkSubtype<Boolean>(5 in 1..10 == (1..10).contains(5))
}
