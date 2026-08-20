// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 *                expressions, prefix-expressions, unary-minus-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: class member operator fun unaryMinus in unary minus expression infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun unaryMinus() = Vector(-x)
}

fun case1() {
    checkSubtype<Vector>(-Vector(5))
}
