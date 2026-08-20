// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 *                expressions, multiplicative-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: class member operator fun times in multiplicative expression infers Vector
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Vector(val x: Int) {
    operator fun times(scalar: Int) = Vector(x * scalar)
}

fun case1() {
    checkSubtype<Vector>(Vector(2) * 3)
}
