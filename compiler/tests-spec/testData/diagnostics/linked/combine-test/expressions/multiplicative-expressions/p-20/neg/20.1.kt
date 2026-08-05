// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 20 -> sentence 20
 *                type-system, built-in-integer-types -> paragraph 20 -> sentence 20
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: times(Int) overload cannot accept Long operand in multiplication
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    operator fun times(k: Int): W = W(v * k)
}

fun case_1() = W(1L) * <!CONSTANT_EXPECTED_TYPE_MISMATCH!>3L<!>
