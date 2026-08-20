// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 38 -> sentence 38
 *                expressions, additive-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: plus inside run lambda infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
}

fun case1(v1: Vector, v2: Vector) {
    checkSubtype<Vector>(run { v1 + v2 })
}
