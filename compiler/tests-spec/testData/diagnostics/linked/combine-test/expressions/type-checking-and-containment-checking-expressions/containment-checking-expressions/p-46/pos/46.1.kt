// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 46 -> sentence 46
 *                expressions, range-expressions -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: in operator on ClosedFloatingPointRange uses range contains convention and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(1.5 in 1.0..2.0)
    checkSubtype<Boolean>(0.5 in 1.0..2.0)
    checkSubtype<Boolean>(1.5 in 1.0..2.0 == (1.0..2.0).contains(1.5))
}
