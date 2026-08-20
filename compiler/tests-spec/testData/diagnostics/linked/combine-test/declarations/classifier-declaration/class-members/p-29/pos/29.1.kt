// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -EXTENSION_SHADOWED_BY_MEMBER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 29 -> sentence 29
 *                expressions, additive-expressions -> paragraph 29 -> sentence 29
 *                declarations, function-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: member-over-extension plus resolution infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x + 10)
}

operator fun Vector.plus(other: Vector) = Vector(x + other.x)

fun case1() {
    checkSubtype<Vector>(Vector(1) + Vector(2))
}
