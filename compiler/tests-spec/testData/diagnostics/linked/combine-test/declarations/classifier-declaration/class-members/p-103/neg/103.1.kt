// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 103 -> sentence 103
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 103 -> sentence 103
 *                expressions, additive-expressions -> paragraph 103 -> sentence 103
 * NUMBER: 1
 * DESCRIPTION: class member plus without operator does not desugar additive expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    fun plus(other: Vector) = Vector(x + other.x)
}

fun test() = Vector(1) <!OPERATOR_MODIFIER_REQUIRED!>+<!> Vector(2)
