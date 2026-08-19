// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 38 -> sentence 38
 *                expressions, additive-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: class member plus evaluated inside lambda/run block
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
}

fun test(v1: Vector, v2: Vector): Vector = run { v1 + v2 }

fun box(): String {
    if (test(Vector(1), Vector(2)).x != 3) return "NOK"
    return "OK"
}
