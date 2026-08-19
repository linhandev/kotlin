// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 28 -> sentence 28
 *                expressions, additive-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: plus(Int) preferred over plus(Vector) when right operand is Int
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x + 10)
    operator fun plus(scalar: Int) = Vector(x + scalar)
}

fun test(): Vector = Vector(1) + 2

fun box(): String {
    if (test().x != 3) return "NOK: expected plus(Int)"
    if ((Vector(1) + Vector(2)).x != 13) return "NOK: expected plus(Vector)"
    return "OK"
}
