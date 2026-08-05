// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: fun times without operator modifier is not invoked by binary times
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    fun times(k: Long): W = W(v * k)
}

fun case_1() = W(1L) <!OPERATOR_MODIFIER_REQUIRED!>*<!> 2L
