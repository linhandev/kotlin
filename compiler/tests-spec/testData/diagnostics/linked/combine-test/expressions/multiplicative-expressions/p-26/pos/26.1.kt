// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 26 -> sentence 26
 *                 type-system, built-in-integer-types -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: chained built-in Long multiplication type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 2L * 3L * 4L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): Long = (2L * 3L) * 4L

fun case_2_check() {
    checkSubtype<Long>(case_2())
}
