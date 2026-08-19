// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                expressions, additive-expressions -> paragraph 25 -> sentence 25
 *                declarations, function-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: extension operator fun plus enables additive expression on class without member plus
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int)

operator fun Vector.plus(other: Vector) = Vector(x + other.x)

fun test(): Vector = Vector(1) + Vector(2)

fun box(): String {
    if (test().x != 3) return "NOK"
    return "OK"
}
