// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: fun plus without operator modifier is not invoked by binary plus
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    fun plus(o: Vec): Vec = Vec(x + o.x)
}

fun case_1() = Vec(1) <!OPERATOR_MODIFIER_REQUIRED!>+<!> Vec(2)
