// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 30 -> sentence 30
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: times(Long) overload cannot accept custom type operand on both sides
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    operator fun times(k: Long): W = W(v * k)
}

fun case_1() = W(1L) * <!TYPE_MISMATCH!>W(2L)<!>
