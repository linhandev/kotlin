// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 *                expressions, multiplicative-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: class member operator fun div in multiplicative expression infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun div(scalar: Int) = Vector(x / scalar)
}

fun case1() {
    checkSubtype<Vector>(Vector(6) / 2)
}
