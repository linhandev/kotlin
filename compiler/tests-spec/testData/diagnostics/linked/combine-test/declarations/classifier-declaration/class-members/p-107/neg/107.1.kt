// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 107 -> sentence 107
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 107 -> sentence 107
 *                expressions, prefix-expressions -> paragraph 107 -> sentence 107
 * NUMBER: 1
 * DESCRIPTION: non-operator unaryMinus does not desugar unary expression on class instance
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    fun unaryMinus() = Vector(-x)
}

fun test() = <!OPERATOR_MODIFIER_REQUIRED!>-<!>Vector(1)
