// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                type-system, built-in-integer-types -> paragraph 27 -> sentence 27
 *                statements, assignments, operator-assignments -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: built-in Long augmented assignment times-equals type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long {
    var x = 2L
    x *= 3L
    return x
}

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2() {
    var x = 2L
    x *= 3L
    checkSubtype<Long>(x)
}
