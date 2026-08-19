// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 16 -> sentence 16
 *                 type-system, built-in-integer-types -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: Long multiplication overflow expression type inference remains Long
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun multiply(a: Long, b: Long): Long = a * b

fun case_1_check() {
    checkSubtype<Long>(multiply(Long.MAX_VALUE, 2L))
}
