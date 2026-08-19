// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 *                expressions, additive-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class member operator fun plus desugars to member call in additive expression
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
}

fun test(): Vector = Vector(1) + Vector(2)

fun box(): String {
    if (test().x != 3) return "NOK"
    return "OK"
}
