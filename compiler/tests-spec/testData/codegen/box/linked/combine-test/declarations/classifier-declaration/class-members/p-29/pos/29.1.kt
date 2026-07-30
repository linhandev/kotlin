// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 29 -> sentence 29
 *                expressions, additive-expressions -> paragraph 29 -> sentence 29
 *                declarations, function-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: member operator plus takes precedence over extension operator plus
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x + 10)
}

operator fun Vector.plus(other: Vector) = Vector(x + other.x)

fun test(): Vector = Vector(1) + Vector(2)

fun box(): String {
    if (test().x != 13) return "NOK: expected member plus"
    return "OK"
}
