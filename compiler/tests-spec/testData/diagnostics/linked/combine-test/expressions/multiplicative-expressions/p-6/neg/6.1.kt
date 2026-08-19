// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: times operand types must match overload signature
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun times(k: Int): Vec = Vec(x * k)
}

fun case_1() = Vec(1) * <!TYPE_MISMATCH!>Vec(2)<!>
