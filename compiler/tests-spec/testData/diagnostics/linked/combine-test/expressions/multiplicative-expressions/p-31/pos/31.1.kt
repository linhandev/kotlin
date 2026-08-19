// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 31 -> sentence 31
 *                 type-system, built-in-integer-types -> paragraph 31 -> sentence 31
 *                expressions, prefix-expressions, unary-minus-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: built-in Long unary minus with multiplicative expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = -3L * 4L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): Long = -2L * 5L

fun case_2_check() {
    checkSubtype<Long>(case_2())
}
