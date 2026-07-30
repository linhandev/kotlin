// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                expressions, additive-expressions -> paragraph 24 -> sentence 24
 *                type-system, nullable-types -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: class member plus handles nullable Int operands with Elvis defaults
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int?) {
    operator fun plus(other: Vector) = Vector((x ?: 0) + (other.x ?: 0))
}

fun test(): Int? = (Vector(1) + Vector(null)).x

fun box(): String {
    if (test() != 1) return "NOK"
    if ((Vector(null) + Vector(null)).x != 0) return "NOK"
    return "OK"
}
