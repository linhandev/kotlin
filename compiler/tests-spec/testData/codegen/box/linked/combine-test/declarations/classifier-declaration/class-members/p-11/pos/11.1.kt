// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 *                expressions, prefix-expressions, unary-minus-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: class member operator fun unaryMinus desugars to member call in unary minus expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun unaryMinus() = Vector(-x)
}

fun test(): Vector = -Vector(5)

fun box(): String {
    if (test().x != -5) return "NOK"
    return "OK"
}
