// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 22 -> sentence 22
 *                expressions, additive-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: mixed Vector and Int plus overloads infer Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
    operator fun plus(scalar: Int) = Vector(x + scalar)
}

fun case1() {
    checkSubtype<Vector>(Vector(1) + Vector(2) + 3)
}
