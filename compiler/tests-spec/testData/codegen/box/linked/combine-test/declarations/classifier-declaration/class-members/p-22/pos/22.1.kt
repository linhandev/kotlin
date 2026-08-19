// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 22 -> sentence 22
 *                expressions, additive-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: overloaded plus for Vector and Int mixed in chained additive expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
    operator fun plus(scalar: Int) = Vector(x + scalar)
}

fun test(): Vector = Vector(1) + Vector(2) + 3

fun box(): String {
    if (test().x != 6) return "NOK"
    return "OK"
}
