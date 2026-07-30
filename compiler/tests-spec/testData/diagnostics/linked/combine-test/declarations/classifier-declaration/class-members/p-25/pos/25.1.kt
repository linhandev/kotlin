// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                expressions, additive-expressions -> paragraph 25 -> sentence 25
 *                declarations, function-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: extension operator fun plus infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int)

operator fun Vector.plus(other: Vector) = Vector(x + other.x)

fun case1() {
    checkSubtype<Vector>(Vector(1) + Vector(2))
}
