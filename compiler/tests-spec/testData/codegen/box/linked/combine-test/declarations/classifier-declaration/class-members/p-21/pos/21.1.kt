// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                expressions, additive-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: chained class member operator fun plus left-associative evaluation
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
}

fun test(): Vector = Vector(1) + Vector(2) + Vector(3)

fun box(): String {
    if (test().x != 6) return "NOK"
    return "OK"
}
