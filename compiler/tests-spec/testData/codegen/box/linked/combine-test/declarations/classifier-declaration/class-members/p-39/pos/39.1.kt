// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 39 -> sentence 39
 *                expressions, additive-expressions -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: plus used as conditional return expression
 */

// TESTCASE NUMBER: 1

class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
}

fun test(flag: Boolean): Vector {
    if (flag) return Vector(1) + Vector(2)
    return Vector(0)
}

fun box(): String {
    if (test(true).x != 3) return "NOK"
    if (test(false).x != 0) return "NOK"
    return "OK"
}
