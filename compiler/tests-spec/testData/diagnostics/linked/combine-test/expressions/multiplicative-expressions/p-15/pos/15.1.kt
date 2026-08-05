// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 15 -> sentence 15
 *                type-system, built-in-integer-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: Long division by zero expression type inference remains Long at compile time
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun divide(a: Long, b: Long): Long = a / b

fun remainder(a: Long, b: Long): Long = a % b

fun case_1_check() {
    checkSubtype<Long>(divide(1L, 0L))
}

fun case_2_check() {
    checkSubtype<Long>(remainder(1L, 0L))
}
