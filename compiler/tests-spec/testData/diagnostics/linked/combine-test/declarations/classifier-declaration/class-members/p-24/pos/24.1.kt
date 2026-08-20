// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                expressions, additive-expressions -> paragraph 24 -> sentence 24
 *                type-system, nullable-types -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: class member plus with nullable Int operands infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int?) {
    operator fun plus(other: Vector) = Vector((x ?: 0) + (other.x ?: 0))
}

fun case1() {
    checkSubtype<Vector>(Vector(1) + Vector(null))
}
