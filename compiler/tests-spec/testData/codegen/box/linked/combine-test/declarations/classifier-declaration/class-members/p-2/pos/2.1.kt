// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 *                expressions, additive-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: class member operator fun minus desugars to member call in additive expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun minus(other: Vector) = Vector(x - other.x)
}

fun test(): Vector = Vector(5) - Vector(3)

fun box(): String {
    if (test().x != 2) return "NOK"
    return "OK"
}
