// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 20 -> sentence 20
 *                expressions, additive-expressions -> paragraph 20 -> sentence 20
 *                expressions, multiplicative-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: overloaded plus and times precedence expressions infer Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
    operator fun times(scalar: Int) = Vector(x * scalar)
}

fun case1() {
    checkSubtype<Vector>((Vector(2) + Vector(3)) * 2)
    checkSubtype<Vector>(Vector(2) + Vector(3) * 2)
}
