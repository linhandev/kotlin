// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 *                expressions, multiplicative-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: class member operator fun times desugars to member call in multiplicative expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun times(scalar: Int) = Vector(x * scalar)
}

fun test(): Vector = Vector(2) * 3

fun box(): String {
    if (test().x != 6) return "NOK"
    return "OK"
}
