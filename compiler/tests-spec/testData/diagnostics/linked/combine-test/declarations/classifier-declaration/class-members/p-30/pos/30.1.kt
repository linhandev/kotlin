// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 30 -> sentence 30
 *                expressions, additive-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: plus result assigned to explicit Vector local matches return type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Vector(val x: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x)
}

fun test(): Vector {
    val v: Vector = Vector(1) + Vector(2)
    return v
}

fun case1() {
    checkSubtype<Vector>(test())
}
