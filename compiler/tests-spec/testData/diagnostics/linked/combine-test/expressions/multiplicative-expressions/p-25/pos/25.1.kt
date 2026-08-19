// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                expressions, additive-expressions -> paragraph 25 -> sentence 25
 *                type-system, built-in-integer-types -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: built-in Long mixed multiplicative and additive expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 1L + 2L * 3L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): Long = (1L + 2L) * 3L

fun case_2_check() {
    checkSubtype<Long>(case_2())
}

fun case_3(): Long = 1L + 2L * 3L + 4L

fun case_3_check() {
    checkSubtype<Long>(case_3())
}
