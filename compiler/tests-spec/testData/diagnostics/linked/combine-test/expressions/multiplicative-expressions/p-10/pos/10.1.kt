// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                 type-system, built-in-integer-types -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Long literal multiplicative expression with Int operand type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 10L * 2

fun case_1_check() {
    checkSubtype<Long>(case_1())
}
