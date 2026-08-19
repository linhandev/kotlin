// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 20 -> sentence 20
 *                expressions, additive-expressions -> paragraph 20 -> sentence 20
 *                expressions, multiplicative-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: overloaded plus and times follow multiplicative-over-additive precedence
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
    operator fun times(scalar: Int) = Vector(x * scalar)
}

fun parenthesized(): Vector = (Vector(2) + Vector(3)) * 2
fun byPrecedence(): Vector = Vector(2) + Vector(3) * 2

fun box(): String {
    if (parenthesized().x != 10) return "NOK: parenthesized"
    if (byPrecedence().x != 8) return "NOK: precedence"
    return "OK"
}
