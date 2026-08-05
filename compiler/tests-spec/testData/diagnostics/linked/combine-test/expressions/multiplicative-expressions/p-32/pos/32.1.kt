// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 32 -> sentence 32
 *                 type-system, built-in-integer-types -> paragraph 32 -> sentence 32
 *                expressions, constant-literals, the-types-for-integer-literals -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: Long literal with numeric separator in remainder expression type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 10_00L % 3L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): Long = 1_000L % 7L

fun case_2_check() {
    checkSubtype<Long>(case_2())
}
